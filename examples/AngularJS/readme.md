# Angular
In order for BDDStack to work properly with AngularJS based applications, you need to add a feature to your test scripts.
More specifically, you need to add an extension, which helps BDDStack determine if Angular has finished rendering the page so that the regular test activities can take place.

Install/change the following:
* Place the angular-test-support.js file in your application's HTML head (for an alternative see below)
* Copy the /groovy/extensions in your BDDStack/src/test/groovy directory
* For each (AngularJS generated) page definition add use the following template:

~~~~
package pages.app
import geb.Page
import extensions.AngularJSAware

class <your page name>Page extends Page implements AngularJSAware {
	static at = { angularReady && title == "<your title>" }
	static url = "<url of page>"
	static content = {}
}
~~~~

Now every time this page is used, it will wait until AngularJS has completely finished rendering the page.

## Details of this extension

By adding the file below to your applications's HTML head, you will be able to query the window.MYAPP.APP_READY variable from BDDStack.
This method is utilizing AngularJS native functions.

angular-test-support.js
~~~~
// Include this file in the head of your main layout.

window.MYAPP = window.MYAPP || {};

window.MYAPP.waitForAngular = function() {

    window.MYAPP.APP_READY = false;

    function done(err) {
        if (err) {
            console.error('Waiting for Angular:', err);
            return;
        }
        window.MYAPP.APP_READY = true;
    }

    var el = document.querySelector('html');

    try {
        if (!window.angular) {
            throw new Error('Angular could not be found on the window');
        }
        if (angular.getTestability) {
            angular.getTestability(el).whenStable(done);
        } else {
            if (!angular.element(el).injector()) {
                throw new Error('Root element (html) has no injector. This may mean it is not inside ng-app.');
            }
            angular.element(el).injector().get('$browser').notifyWhenNoOutstandingRequests(done);
        }
    } catch (err) {
        done(err.message);
    }
};
~~~~

In the extensions directory we have one file:

AngularJSAware.groovy
~~~~
package extensions

trait AngularJSAware {
 
    boolean isAngularReady() {
        js.exec('window.MYAPP.waitForAngular();');
        waitFor {
            js.MYAPP.APP_READY == true
        }

    }
}
~~~~

This file creates a trait that can be connected to the page (see page template above). With this set up, we are able to inspect ***angularReady*** as a signal that we are now able to commence with our tests.

If you are unable to add the JS file to your application's head, you can include the following code before the js.exec statement in the above code.

~~~~
         if ($("script", src:"https://s3.ca-central-1.amazonaws.com/stens-angular-test/angular-test-support.js").size() == 0) {
            browser.driver.executeScript("document.body.appendChild(document.createElement(\'script\')).src=\'https://s3.ca-central-1.amazonaws.com/stens-angular-test/angular-test-support.js'")
        }  
~~~~

This code effectively inserts the test support JS file into the page under test. (***Caveat***: Find your own location for the file as the Amazon S3 location will be gome shortly.)  

This solution works, but better and consistent results were obtained by including the JS in your application's HTML head.

## AngularJS Testing Quirks
BDDSTack and Angular play together well but there are a few quirks:

* Sometimes you have to force scrolling an element into view. See the code in the HomePage.groovy included in this example.
* On occasion a link will not respond to a click but needs a [***to*** directive](http://www.gebish.org/manual/current/#content-dsl-to).
* It is advantageous to put unique IDs in every element as this is the most stable way of addressing elements in your pages. Be careful to keep the IDs unique in the page though as HTML5 does not allow duplicate IDs.
* Review the [BC Developer's Exchange Test code](https://github.com/BCDevExchange/devex/tree/develop/functional-tests) for some more examples of tweaks that the code needed to effectively work with the application.

## Conclusion
We are able to effectively test AngularJS based applications, but there are a few thinsg that you need to do in order to make this work well. (Or better: make this work at all.)
