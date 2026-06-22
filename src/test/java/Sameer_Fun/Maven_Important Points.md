```text
Maven Test Execution Notes

Command:
mvn test

How Maven Works:
1. Maven compiles the project.
2. Maven executes the TestNG suite using the Surefire Plugin.
3. Maven checks the test results.
4. Based on the results, Maven marks the build as SUCCESS or FAILURE.

Scenario 1: All Test Cases Pass

Tests run: 10
Failures: 0
Errors: 0
Skipped: 0

Result:
BUILD SUCCESS

Reason:
- All test cases passed successfully.
- Exit Code = 0
- CI/CD tools (Jenkins, GitHub Actions, Azure DevOps) mark the build as Passed.

--------------------------------------------------

Scenario 2: One or More Test Cases Fail

Tests run: 10
Failures: 2
Errors: 0
Skipped: 0

Result:
BUILD FAILURE

Reason:
- At least one test case failed.
- Exit Code ≠ 0
- CI/CD tools mark the build as Failed.

--------------------------------------------------

Important Interview Point:

BUILD FAILURE can happen due to:

1. Compilation Failure

Example:
System.out.println("Hello")

(Missing semicolon)

Result:
COMPILATION ERROR
BUILD FAILURE

--------------------------------------------------

2. Test Failure

Example:
Assert.assertEquals(actual, expected);

or

Selenium Exception:
ElementNotInteractableException
NoSuchElementException
TimeoutException

Result:
Tests run: 10
Failures: 2

BUILD FAILURE

--------------------------------------------------

Default Maven Behavior:

✔ All tests pass  -> BUILD SUCCESS
✘ Even one test fails -> BUILD FAILURE

--------------------------------------------------

To Ignore Test Failures (Not Recommended)

<configuration>
    <testFailureIgnore>true</testFailureIgnore>
</configuration>

Result:
Even if tests fail, Maven shows:

BUILD SUCCESS

Note:
Generally not recommended in Automation Frameworks because CI/CD pipelines should fail when test cases fail.
```
