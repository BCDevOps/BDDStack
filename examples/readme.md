## NavUnit
This is the code example from the previous version of the framework. (Currently not working)

## Angular
Angular testing is a challenge for BDDStack (and many other test frameworks). The web pages get rendered by Angular and content that needs to be tested might not be avaliable yet when the page is requested. BDDStack requires a mechanism that queries the readiness of Angular and once Angular is done with the page, it can then proceed with the tests.
You could potentially deal with this by just waiting for the page or elements to appear, but this has proven to be error-prone.
The example shows a preferred way of doing this.
