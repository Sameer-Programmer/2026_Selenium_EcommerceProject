package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext) {

        /*
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date df = new Date();
        String currentDatetimeStamp = df.format(dt);
        */

        String timeStamp =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Report location
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        // Report configuration
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        // Attach reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System Information
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));// this is not from config.properties file this is predefined method
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        // Groups Information
        List<String> includedGroups =
                testContext.getCurrentXmlTest().getIncludedGroups();

        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.PASS,
                result.getMethod().getMethodName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL,
                result.getMethod().getMethodName() + " got failed");

        if (result.getThrowable() != null) {
            test.log(Status.INFO, result.getThrowable().getMessage());
        }

        try {
            BaseClass baseClass = (BaseClass) result.getInstance();

            String imgPath =
                    baseClass.captureScreen(result.getMethod().getMethodName());

            test.addScreenCaptureFromPath(imgPath);

        } catch (Exception e) {
            test.log(Status.WARNING,
                    "Unable to capture screenshot : " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.SKIP,
                result.getMethod().getMethodName() + " got skipped");

        if (result.getThrowable() != null) {
            test.log(Status.INFO, result.getThrowable().getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext testContext) {

        extent.flush();

        String pathOfExtentReport =
                System.getProperty("user.dir") + "\\reports\\" + repName;

        File extentReport = new File(pathOfExtentReport);

        try {

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(extentReport.toURI());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * try {
         *     URL url = new URL("file:///" + System.getProperty("user.dir")
         *             + "\\reports\\" + repName);
         *
         *     // Create the email message
         *     ImageHtmlEmail email = new ImageHtmlEmail();
         *     email.setDataSourceResolver(new DataSourceUrlResolver(url));
         *     email.setHostName("smtp.googlemail.com");
         *     email.setSmtpPort(465);
         *     email.setAuthenticator(
         *             new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
         *     email.setSSLOnConnect(true);
         *
         *     email.setFrom("pavanoltraining@gmail.com"); // Sender
         *     email.setSubject("Test Results");
         *     email.setMsg("Please find Attached Report....");
         *
         *     email.addTo("pavankumar.busyqa@gmail.com"); // Receiver
         *
         *     email.attach(url, "extent report", "please check report...");
         *
         *     email.send(); // send the email
         * }
         * catch (Exception e) {
         *     e.printStackTrace();
         * }
         */
    }
}

