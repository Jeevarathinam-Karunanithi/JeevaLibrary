process.env.CHROME_BIN = require('puppeteer').executablePath()

module.exports = function(config) { config.set({
    "basePath": "src/main/webapp",
    "colors": true,
    "logLevel": "ALL", //ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, MARK, OFF
    "files": [
        "js/jquery.3.4.1.min.js",
        "lib/jasmine-3.6.0/jasmine-jquery.js",
        "lib/jasmine-3.6.0/jasmine-ajax.js",
        "lib/sinon-1.17.3.js",
        "test/initFixtures.js",

        "Javascript.js",

        "test/testSpec.js",
        
        {
            "pattern": "spec/javascripts/fixtures/register.jsp",
            "included": false,
            "served": true,
            "watched": true
        },
        
        {
            "pattern": "spec/javascripts/fixtures/library.jsp",
            "included": false,
            "served": true,
            "watched": true
        }

    ],
    "browsers": [
        "ChromeHeadless"
    ],
    "frameworks": [
        "jasmine"
    ],
    "reporters": [
        "junit",
        "progress",
        "coverage"
    ],
    "preprocessors": {
        "**/js/home.js": "coverage",
        "**/js/bookTicket.js": "coverage",
        "**/js/login.js": "coverage",
        "**/js/user.js": "coverage",
        "**/js/displayUserDetails.js": "coverage",
        "**/js/audienceEdit.js": "coverage",
        "**/js/bookings.js": "coverage",
        
    },
    "exclude": [
        
    ],
    "browserNoActivityTimeout": 50000,
    "junitReporter": {
        "outputDir": "jasmine-junit-results"
    },
    "coverageReporter": {
        "type": "html",
        "dir": "coverage",
        "includeAllSources": true,
        "reporters": [
            {
                "type": "html",
                "dir": "coverage/"
            }
        ]
    },
    "client": {
        "jasmine": {
          "random": false
        }
      }
}) };