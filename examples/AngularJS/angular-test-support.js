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