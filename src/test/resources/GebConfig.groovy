/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/#configuration
*/

import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver

waiting {
	timeout = 15
	retryInterval = 1
}

atCheckWaiting = [20, 1]

environments {
	
	// run via “./gradlew chromeTest”
	// See: https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
	chrome {
		driver = { new ChromeDriver() }
	}

	// run via “./gradlew chromeHeadlessTest”
	// See: https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
	chromeHeadless {
		driver = {
			ChromeOptions o = new ChromeOptions()
			o.addArguments('headless')
			o.addArguments('disable-gpu') 
			o.addArguments('no-sandbox')
			new ChromeDriver(o)
		}
	}
	
	// run via “./gradlew firefoxTest”
	// See: https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver
	firefox {
		atCheckWaiting = 1
		driver = { new FirefoxDriver() }
	}
}

// To run the tests with all browsers just run “./gradlew test”

baseNavigatorWaiting = true
baseUrl = "http://gebish.org"

println "BaseURL: ${baseUrl}"
println "--------------------------"

cacheDriverPerThread = true
quitCachedDriverOnShutdown = true

