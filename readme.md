# BDDStack setup and sample tests

## Description

This is an example of incorporating Geb into a Gradle build. It shows the use of Spock and JUnit 4 tests.

The build is setup to work with a variety of browsers and we aim to add as many as possible.
Work has been progressing on the pipeline to replace phantomJs with chromeHeadless. Please see the Dockerfile setup: https://github.com/agehlers/openshift-tools/blob/master/provisioning/jenkins-slaves/chrome/Dockerfile


## Usage

The following commands will launch the tests with the individual browsers:

    ./gradlew chromeTest
    ./gradlew chromeHeadlessTest
    ./gradlew firefoxTest
    ./gradlew firefoxHeadlessTest
    ./gradlew edgeTest
    ./gradlew ieTest
    
To run with all, you can run:

    ./gradlew test

Replace `./gradlew` with `gradlew.bat` in the above examples if you're on Windows.

## Questions and issues

Please ask questions on our Slack Channel: [https://devopspathfinder.slack.com/messages/C7J72K1MG] and raise issues in [Geb issue tracker][issue_tracker].

[issue_tracker]: https://github.com/rstens/BDDStack/issues/issues
