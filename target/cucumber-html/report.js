$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/FeatureFiles/Sentinel.feature");
formatter.feature({
  "line": 1,
  "name": "Validate the values in Sentinel application and validate sum logic for page TCO sum and TCO sums.",
  "description": "",
  "id": "validate-the-values-in-sentinel-application-and-validate-sum-logic-for-page-tco-sum-and-tco-sums.",
  "keyword": "Feature"
});
formatter.before({
  "duration": 27702507,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Verify that there are no null values in table in every page.",
  "description": "",
  "id": "validate-the-values-in-sentinel-application-and-validate-sum-logic-for-page-tco-sum-and-tco-sums.;verify-that-there-are-no-null-values-in-table-in-every-page.",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@tag1"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "Browser is launched",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Navigate to the sentinel application",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Enter login credentials and click on login button",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Select business segment and assets group from toggle menu",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "Move to the Whiteboard tab",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "Move through all the pages and validate that there no null values",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "Logout from the application",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "Close browser",
  "keyword": "Then "
});
formatter.match({
  "location": "Sentinel.this_is_a_test_step()"
});
formatter.result({
  "duration": 8542196221,
  "status": "passed"
});
formatter.match({
  "location": "Sentinel.navigate_to_the_sentinel_application()"
});
formatter.result({
  "duration": 6381403115,
  "status": "passed"
});
formatter.match({
  "location": "Sentinel.enter_login_credentials_and_click_on_login_button()"
});
formatter.result({
  "duration": 126557467574,
  "status": "passed"
});
formatter.match({
  "location": "Sentinel.select_business_segment_and_assets_group_from_toggle_menu()"
});
formatter.result({
  "duration": 89459099,
  "error_message": "org.openqa.selenium.NoSuchWindowException: no such window: target window already closed\nfrom unknown error: web view not found\n  (Session info: chrome\u003d74.0.3729.157)\n  (Driver info: chromedriver\u003d2.40.565498 (ea082db3280dd6843ebfb08a625e3eb905c4f5ab),platform\u003dWindows NT 10.0.17134 x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027LHTU05CG7300QRY\u0027, ip: \u0027192.168.56.1\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_181\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, acceptSslCerts: false, applicationCacheEnabled: false, browserConnectionEnabled: false, browserName: chrome, chrome: {chromedriverVersion: 2.40.565498 (ea082db3280dd6..., userDataDir: C:\\Users\\p28\\AppData\\Local\\...}, cssSelectorsEnabled: true, databaseEnabled: false, handlesAlerts: true, hasTouchScreen: false, javascriptEnabled: true, locationContextEnabled: true, mobileEmulationEnabled: false, nativeEvents: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: XP, platformName: XP, rotatable: false, setWindowRect: true, takesHeapSnapshot: true, takesScreenshot: true, unexpectedAlertBehaviour: , unhandledPromptBehavior: , version: 74.0.3729.157, webStorageEnabled: true}\nSession ID: 0ca1c91da721b65024215393f905a9da\n*** Element info: {Using\u003dxpath, value\u003d//*[@id \u003d \u0027menu-toggle\u0027]}\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat java.lang.reflect.Constructor.newInstance(Unknown Source)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:214)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:166)\r\n\tat org.openqa.selenium.remote.http.JsonHttpResponseCodec.reconstructValue(JsonHttpResponseCodec.java:40)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:80)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:44)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\r\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\r\n\tat org.openqa.selenium.support.pagefactory.DefaultElementLocator.findElement(DefaultElementLocator.java:69)\r\n\tat org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler.invoke(LocatingElementHandler.java:38)\r\n\tat com.sun.proxy.$Proxy19.click(Unknown Source)\r\n\tat com.optum.acoe.WebFunctionLib.WebFunctionLib.Click(WebFunctionLib.java:226)\r\n\tat com.optum.acoe.PageObjects.SentinelApplication.SentinelHome.clickTogglebtn(SentinelHome.java:65)\r\n\tat com.optum.acoe.StepDefinitions.Sentinel.select_business_segment_and_assets_group_from_toggle_menu(Sentinel.java:92)\r\n\tat ✽.Then Select business segment and assets group from toggle menu(src/test/resources/FeatureFiles/Sentinel.feature:8)\r\n",
  "status": "failed"
});
formatter.match({
  "location": "Sentinel.move_to_the_Whiteboard_tab()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "Sentinel.move_through_all_the_pages_and_validate_that_there_no_null_values()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "Sentinel.logout_from_the_application()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "Sentinel.close_browser()"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 24221079,
  "error_message": "java.lang.NullPointerException\r\n\tat gherkin.deps.net.iharder.Base64.encodeBytes(Base64.java:720)\r\n\tat gherkin.formatter.JSONFormatter.embedding(JSONFormatter.java:130)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n\tat java.lang.reflect.Method.invoke(Unknown Source)\r\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\r\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\r\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\r\n\tat cucumber.runtime.RuntimeOptions$1.invoke(RuntimeOptions.java:294)\r\n\tat com.sun.proxy.$Proxy15.embedding(Unknown Source)\r\n\tat cucumber.runtime.junit.JUnitReporter.embedding(JUnitReporter.java:85)\r\n\tat cucumber.runtime.ScenarioImpl.embed(ScenarioImpl.java:61)\r\n\tat com.optum.acoe.StepDefinitions.CucumberHooks.tearDown(CucumberHooks.java:42)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n\tat java.lang.reflect.Method.invoke(Unknown Source)\r\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\r\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\r\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\r\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\r\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\r\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\r\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\r\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\r\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\r\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\r\n\tat org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)\r\n\tat org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\r\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\r\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\r\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\r\n",
  "status": "failed"
});
});