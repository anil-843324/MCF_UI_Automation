package base;


import java.util.List;

import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;

import com.microsoft.playwright.BrowserContext;

import com.microsoft.playwright.BrowserType;

import com.microsoft.playwright.Page;

import com.microsoft.playwright.Playwright;


public class BaseTest {


    private Playwright playwright;

    public Browser browser;

    public Page page;

    public BrowserContext browserContext;

    private Logger log = Logger.getLogger(this.getClass());


    private static ThreadLocal<Playwright> pw = new ThreadLocal<>();

    private static ThreadLocal<Browser> br = new ThreadLocal<>();

    private static ThreadLocal<Page> pg = new ThreadLocal<>();

    private static ThreadLocal<BrowserContext> bc = new ThreadLocal<>();


    public static Playwright getPlaywright() {

        return pw.get();

    }


    public static Browser getBrowser() {

        return br.get();

    }


    public static Page getPage() {

        return pg.get();

    }


    public static BrowserContext getBrowserContext() {

        return bc.get();

    }


    @BeforeSuite

    public void setUp() {

        PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");

        log.info("Test Execution started !!!");

    }


    public Browser getBrowser(String browserName) {

        playwright = Playwright.create();

        pw.set(playwright);


        Browser browser;

        switch (browserName) {

            case "chrome":

                log.info("Launching Chrome browser");

                browser = getPlaywright().chromium().launch(

                    new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setArgs(List.of("--start-maximized\", \"--window-size=1920,1080"))

                );

                break;

            case "headless":

                log.info("Launching Headless Mode");

                browser = getPlaywright().chromium().launch(

                    new BrowserType.LaunchOptions().setHeadless(true).setArgs(List.of("--start-maximized"))

                );

                break;

            case "firefox":

                log.info("Launching Firefox browser");

                browser = getPlaywright().firefox().launch(

                    new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false).setArgs(List.of("--start-maximized"))

                );

                break;

            case "webkit":

                log.info("Launching Webkit browser");

                browser = getPlaywright().webkit().launch(

                    new BrowserType.LaunchOptions().setHeadless(false).setArgs(List.of("--start-maximized"))

                );

                break;

            case "edge":

                log.info("Launching Edge browser");

                browser = getPlaywright().webkit().launch(

                    new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false).setArgs(List.of("--start-maximized"))

                );

                break;

            default:

                throw new IllegalArgumentException("Unsupported browser: " + browserName);

        }


        br.set(browser);

        browserContext = browser.newContext();
        bc.set(browserContext);


        return browser;

    }


    public void navigate(Browser browser, String url) {

        this.browser = browser;

        br.set(browser);


        browserContext = getBrowser().newContext();

        bc.set(browserContext);


        page = getBrowserContext().newPage();

        pg.set(page);


        getPage().navigate(url);


        log.info("Navigated to : " + url);


        getPage().onDialog(dialog -> {

            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            dialog.accept();

            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            System.out.println(dialog.message());

        });

    }


    @AfterMethod

    public void quit() {

        if (getPage() != null) {

            getPage().close();

            getBrowserContext().close();

            getBrowser().close();

        }

    }


    @AfterSuite

    public void quitPlaywright() {

        if (getPlaywright() != null) {

            getPlaywright().close();

        }

    }

}


























