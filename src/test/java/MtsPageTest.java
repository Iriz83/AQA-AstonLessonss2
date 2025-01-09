import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import io.qameta.allure.*;


public class MtsPageTest {
    @Test
    @Epic("Процесс оплаты на сайте")
    @Feature("Проверка отображения заголовка")
    @Description("Проверка  корректности отображения заголовка (Онлайн пополнение без комиссии)")
    @Severity(SeverityLevel.NORMAL)

    public void testOnlineTopUpHeader() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Iri\\IdeaProjects\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();

        try {
            driver.get("https://mts.by");
            MtsPage mtsPage = new MtsPage(driver);

            stepAcceptCookies(mtsPage);
            stepCheckOnlineTopUpHeader(mtsPage);

        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", "image/png",
                    Arrays.toString(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    @Epic("Процесс оплаты на сайте")
    @Feature("Проверка отображения логотипа")
    @Description("Проверка  корректности отображения логотипа VISA в блоке Онлайн пополнение без комиссии")
    @Severity(SeverityLevel.NORMAL)

    public void testCheckLogoDisplayed() {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Iri\\IdeaProjects\\chromedriver-win64\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            driver.manage().window().maximize();

            try {
                driver.get("https://mts.by");
                MtsPage mtsPage = new MtsPage(driver);

                stepAcceptCookies(mtsPage);
                stepCheckLogoDisplayed(mtsPage);

            } catch (Exception e) {
                Allure.addAttachment("Error Screenshot", "image/png",
                        Arrays.toString(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                e.printStackTrace();
            } finally {
                driver.quit();
            }
    }

    @Test
    @Epic("Процесс оплаты на сайте")
    @Feature("Проверка отображения плейсхолдеров")
    @Description("Проверка  корректности отображения плейсхолдеров (номер телефона, сумма, Email) в полях (Услуги связи,Домашний интернет,Рассрочка,Задолженность) блока Онлайн поплнение без комиссии")
    @Severity(SeverityLevel.NORMAL)

    public void testCheckPlaceholders() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Iri\\IdeaProjects\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();

        try {
            driver.get("https://mts.by");
            MtsPage mtsPage = new MtsPage(driver);

            stepAcceptCookies(mtsPage);
            stepCheckConnectionPhonePlaceholders(mtsPage);
            stepCheckConnectionSumPlaceholders(mtsPage);
            stepCheckConnectionEmailPlaceholders(mtsPage);

            stepCheckInternetPhonePlaceholders(mtsPage);
            stepCheckInternetSumPlaceholders(mtsPage);
            stepCheckInternetEmailPlaceholders(mtsPage);

            stepCheckInstalmentPlaceholders(mtsPage);
            stepCheckInstalmentSumPlaceholders(mtsPage);
            stepCheckInstalmentEmailPlaceholders(mtsPage);

            stepCheckArrearsScorePlaceholders(mtsPage);
            stepCheckArrearsSumPlaceholders(mtsPage);
            stepCheckArrearsEmailPlaceholders(mtsPage);

        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", "image/png",
                    Arrays.toString(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    @Epic("Процесс оплаты на сайте")
    @Feature("Проверка заполнения полей номера и суммы с последующим появлением iframe оплаты")
    @Description("Проверка  заполнения номера телефона и суммы в блоке Онлайн пополнение без комиссии (Услуги связи) с последующим появлением iframe оплаты")
    @Severity(SeverityLevel.CRITICAL)

    public void testCheckFillPaymentDetailsAndIframeLoaded() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Iri\\IdeaProjects\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();

        try {
            driver.get("https://mts.by");
            MtsPage mtsPage = new MtsPage(driver);

            stepAcceptCookies(mtsPage);
            stepFillPaymentDetails(mtsPage);
            stepCheckIframeLoaded(mtsPage);
            stepCheckCostDisplayedOnHeader(mtsPage);
            stepCheckCostDisplayedOnButton(mtsPage);
            stepCheckCardNumberLabel(mtsPage);
            stepCheckPhoneNumberShown(mtsPage);
            stepCheckExpirationDateLabel(mtsPage);
            stepCheckCvcLabel(mtsPage);
            stepCheckCardHolderLabel(mtsPage);
            stepCheckLogoDisplayedInIframe(mtsPage);

        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", "image/png",
                    Arrays.toString(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    //Описание шагов
    @Step("Закрыть окно с куки")
    private static void stepAcceptCookies(MtsPage mtsPage) {
        mtsPage.acceptCookies();
    }

    @Step("Проверить отображение заголовка 'Онлайн пополнение без комиссии'")
    private static void stepCheckOnlineTopUpHeader(MtsPage mtsPage) {
        if (mtsPage.isOnlineTopUpHeaderDisplayed()) {
            Allure.step("Заголовок найден", Status.PASSED);
        } else {
            Allure.step("Заголовок отсутствует", Status.FAILED);
            throw new AssertionError("Заголовок отсутствует");
        }
    }

    @Step("Проверить отображение логотипа Visa")
    private static void stepCheckLogoDisplayed(MtsPage mtsPage) {
        if (mtsPage.isLogoDisplayed()) {
            Allure.step("Логотип отображается корректно", Status.PASSED);
        } else {
            Allure.step("Логотип не отображается", Status.FAILED);
            throw new AssertionError("Логотип не отображается");
        }
    }

    @Step("Проверить плейсхолдер (номер телефона) для поля Услуги связи")
    private static void stepCheckConnectionPhonePlaceholders(MtsPage mtsPage) {
        if (mtsPage.isConnectionPhonePlaceholderCorrect()) {
            Allure.step("Placeholder для номера телефона корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для номера телефона некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для номера телефона некорректен");
        }
    }

    @Step("Проверить плейсхолдер (сумма) для поля Услуги связи")
    private static void stepCheckConnectionSumPlaceholders(MtsPage mtsPage) {

        if (mtsPage.isConnectionSumPlaceholderCorrect()) {
            Allure.step("Placeholder для суммы корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для суммы некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для суммы некорректен");
        }
    }

    @Step("Проверить плейсхолдер (E-mail) для поля Услуги связи")
    private static void stepCheckConnectionEmailPlaceholders(MtsPage mtsPage) {

        if (mtsPage.isConnectionEmailPlaceholderCorrect()) {
            Allure.step("Placeholder для E-mail корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для E-mail некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для E-mail некорректен");
        }
    }

    @Step("Проверить плейсхолдер (номер телефона) для поля Домашний интернет")
    private static void stepCheckInternetPhonePlaceholders(MtsPage mtsPage) {
        if (mtsPage.isInternetPlaceholderCorrect()) {
            Allure.step("Placeholder для номера телефона корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для номера телефона некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для номера телефона некорректен");
        }
    }

    @Step("Проверить плейсхолдер (сумма) для поля Домашний интернет")
    private static void stepCheckInternetSumPlaceholders(MtsPage mtsPage) {
        if (mtsPage.isInternetSumPlaceholderCorrect()) {
            Allure.step("Placeholder для суммы корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для суммы некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для суммы некорректен");
        }
    }

    @Step("Проверить плейсхолдер (E-mail) для поля Домашний интернет")
    private static void stepCheckInternetEmailPlaceholders(MtsPage mtsPage) {
        if (mtsPage.isInternetEmailPlaceholderCorrect()) {
            Allure.step("Placeholder для E-mail корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для E-mail некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для E-mail некорректен");
        }
    }
    @Step("Проверить плейсхолдер (номер телефона) для поля Рассрочка")
    private static void stepCheckInstalmentPlaceholders(MtsPage mtsPage) {
        if (mtsPage.isInstalmentPlaceholderCorrect()) {
            Allure.step("Placeholder для номера телефона корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для номера телефона некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для номера телефона некорректен");
        }
    }

    @Step("Проверить плейсхолдер (сумма) для поля Рассрочка")
    private static void stepCheckInstalmentSumPlaceholders(MtsPage mtsPage) {
        if (mtsPage.isInstalmentSumPlaceholderCorrect()) {
            Allure.step("Placeholder для суммы корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для суммы некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для суммы некорректен");
        }
    }

    @Step("Проверить плейсхолдер (Email) для поля Рассрочка")
    private static void stepCheckInstalmentEmailPlaceholders(MtsPage mtsPage) {
        if (mtsPage.isInstalmentEmailPlaceholderCorrect()) {
            Allure.step("Placeholder для Email корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для Email некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для Email некорректен");
        }
    }

    @Step("Проверить плейсхолдер (номер телефона) для поля Задолженность")
    private static void stepCheckArrearsScorePlaceholders(MtsPage mtsPage) {
        if (mtsPage.isArrearsPlaceholderCorrect()) {
            Allure.step("Placeholder для номера телефона корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для номера телефона некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для номера телефона некорректен");
        }
    }

    @Step("Проверить плейсхолдер (сумма) для поля Задолженность")
    private static void stepCheckArrearsSumPlaceholders(MtsPage mtsPage) {
        if (mtsPage.isArrearsSumPlaceholderCorrect()) {
            Allure.step("Placeholder для суммы корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для суммы некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для суммы некорректен");
        }
    }

    @Step("Проверить плейсхолдер (Email) для поля Задолженность")
    private static void stepCheckArrearsEmailPlaceholders(MtsPage mtsPage) {
        if (mtsPage.isArrearsEmailPlaceholderCorrect()) {
            Allure.step("Placeholder для Email корректен", Status.PASSED);
        } else {
            Allure.step("Placeholder для Email некорректен", Status.FAILED);
            throw new AssertionError("Placeholder для Email некорректен");
        }
    }

    @Step("Заполнить поля платежной формы и продолжить")
    private static void stepFillPaymentDetails(MtsPage mtsPage) {
        try {
            mtsPage.selectConnectionOption();
            mtsPage.fillPaymentDetails("297777777", "100");
            mtsPage.clickContinue();
            // Отметить шаг как PASSED, если все прошло успешно
            Allure.step("Поля успешно заполнены и кнопка 'Продолжить' нажата.", Status.PASSED);
        } catch (Exception e) {
            // Отметить шаг как FAILED в случае ошибки
            Allure.step("Ошибка при заполнении полей платежной формы.", Status.FAILED);
            throw e; // Прокидываем исключение дальше
        }
    }
    @Step("Проверить загрузку платежного iframe")
    private void stepCheckIframeLoaded(MtsPage mtsPage) {
        if (mtsPage.isIframeLoaded()) {
            Allure.step("Платежный iframe загрузился", Status.PASSED);
        } else {
            Allure.step("Платежный iframe не загрузился", Status.FAILED);
            throw new AssertionError("Платежный iframe не загрузился");
        }
    }

    @Step("Проверить, что сумма оплаты отображается корректно в заголовке")
    private void stepCheckCostDisplayedOnHeader(MtsPage mtsPage) {
        if (mtsPage.isCostDisplayedOnHeader()) {
            Allure.step("Сумма оплаты 100 рублей отображается корректно в заголовке", Status.PASSED);
        } else {
            Allure.step("Сумма оплаты 100 рублей не отображается в заголовке", Status.FAILED);
            throw new AssertionError("Платежный iframe не загрузился");
        }
    }
    @Step("Проверить, что сумма оплаты отображается корректно на кнопке")
    private void stepCheckCostDisplayedOnButton(MtsPage mtsPage) {
        if (mtsPage.isCostDisplayedOnButton()) {
            Allure.step("Сумма оплаты 100 рублей отображается корректно на кнопке", Status.PASSED);
        } else {
            Allure.step("Сумма оплаты 100 рублей не отображается на кнопке", Status.FAILED);
            throw new AssertionError("Сумма оплаты 100 рублей не отображается на кнопке");
        }
    }
    @Step("Проверить, что подсказка в поле 'Номер карты' отображается")
    private void stepCheckCardNumberLabel(MtsPage mtsPage) {
        if (mtsPage.isCardNumberLabelCorrect()) {
            Allure.step("Подсказка в поле 'Номер карты' отображается", Status.PASSED);
        } else {
            Allure.step("Подсказка в поле 'Номер карты' не отображается", Status.FAILED);
            throw new AssertionError("Подсказка в поле 'Номер карты' не отображается");
        }
    }
    @Step("Проверить, что номер телефона отображается корректно")
    private void stepCheckPhoneNumberShown(MtsPage mtsPage) {
        if (mtsPage.isPhoneNumberShownCorrect()) {
            Allure.step("Номер телефона отображается корректно", Status.PASSED);
        } else {
            Allure.step("Номер телефона не отображается", Status.FAILED);
            throw new AssertionError("Номер телефона не отображается");
        }
    }
    @Step("Проверить, что подсказка в поле 'Срок действия' отображается")
    private void stepCheckExpirationDateLabel(MtsPage mtsPage) {
        if (mtsPage.isExpirationDateLabelCorrect()) {
            Allure.step("Подсказка в поле 'Срок действия' отображается", Status.PASSED);
        } else {
            Allure.step("Подсказка в поле 'Срок действия' не отображается", Status.FAILED);
            throw new AssertionError("Подсказка в поле 'Срок действия' не отображается");
        }
    }
    @Step("Проверить, что подсказка в поле 'CVC' отображается")
    private void stepCheckCvcLabel(MtsPage mtsPage) {
        if (mtsPage.isCvcLabelCorrect()) {
            Allure.step("Подсказка в поле 'CVC' отображается", Status.PASSED);
        } else {
            Allure.step("Подсказка в поле 'CVC' не отображается", Status.FAILED);
            throw new AssertionError("Подсказка в поле 'CVC' не отображается");
        }
    }
    @Step("Проверить, что подсказка в поле 'Имя держателя (как на карте)' отображается")
    private void stepCheckCardHolderLabel(MtsPage mtsPage) {
        if (mtsPage.isCardHolderLabelCorrect()) {
            Allure.step("Подсказка в поле 'Имя держателя (как на карте)' отображается", Status.PASSED);
        } else {
            Allure.step("Подсказка в поле 'Имя держателя (как на карте)' не отображается", Status.FAILED);
            throw new AssertionError("Подсказка в поле 'Имя держателя (как на карте)' не отображается");
        }
    }
    @Step("Проверить, что логотип Visa отображается в iframe")
    private void stepCheckLogoDisplayedInIframe(MtsPage mtsPage) {
        if (mtsPage.isLogoDisplayedIframe()) {
            Allure.step("Логотип Visa отображается в iframe", Status.PASSED);
        } else {
            Allure.step("Логотип Visa не найден в iframe", Status.FAILED);
            throw new AssertionError("Логотип Visa не найден в iframe");
        }
    }

}

