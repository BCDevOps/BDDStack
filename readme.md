# Initial BDDStack setup and samples

## Description

This is an example of incorporating Geb into a Gradle build. It shows the use of Spock and JUnit 4 tests.

The build is setup to work with Firefox, Chrome and ChromeHeadless. We'll shortly add configurations to support IE and Safari Have a look at the `build.gradle` and the `src/test/resources/GebConfig.groovy` files.

## Usage

The following commands will launch the tests with the individual browsers:

    ./gradlew chromeTest
    ./gradlew chromeHeadlessTest
    ./gradlew firefoxTest

To run with all, you can run:

    ./gradlew test

Replace `./gradlew` with `gradlew.bat` in the above examples if you're on Windows.

## Questions and issues

Please ask questions on our Slack Channel and raise issues in [Geb issue tracker][issue_tracker].

[issue_tracker]: https://github.com/rstens/BDDStack/issues/issues
