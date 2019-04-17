# The ECS Test!
- Ensure that line 26 of "Test_base.java" contains path to your config file in full, and line 45 has the path to your chromedriver executable - I have pushed a version of chromedriver to this repo but feel free to use another.
- Ensure Maven's auto-import is enabled, which should take care of the dependencies.
- Selenium is unfortunately not able to run docker containers so they will have to be spun up manually prior to the test run.
- config.properties has the default browser set to chrome, if you want to change to Firefox you'll need to point line 37 in Test_base.java to Geckodriver. Testing this functionality has been out of scope for the challenge however - it may not work as expected.
- The config file also does not point to "http://localhost:3000/" by default - as I'm using Windows 10 Home I'm restricted to Docker Toolbox rather than Docker. Change the "home" property to the valid URL if you're using Docker! 
- Run from "Cukes.java" and away it goes!
