import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class MtsPageTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Iri\\IdeaProjects\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();

        try {
            driver.get("https://mts.by");
            MtsPage mtsPage = new MtsPage(driver);



            // Шаг 1: Закрыть окно с куки
            mtsPage.acceptCookies();

            // Шаг 2: Проверить отображение заголовка "Онлайн пополнение без комиссии"
            if (mtsPage.isOnlineTopUpHeaderDisplayed()) {
                System.out.println("Заголовок 'Онлайн пополнение без комиссии' найден.");
            } else {
                System.err.println("Заголовок 'Онлайн пополнение без комиссии' не найден.");
            }

            // Шаг 3: Проверить отображение логотипов
            if (mtsPage.isLogoDisplayed()) {
                System.out.println("Логотип Visa отображается.");
            } else {
                System.err.println("Логотип Visa не найден.");
            }

            /*
            // Шаг 4: Заполнить поля (телефон и сумма) и нажать "Продолжить"
            mtsPage.fillPaymentDetails("297777777", "100");
            mtsPage.clickContinue();
            System.out.println("3. Поля успешно заполнены и кнопка 'Продолжить' нажата.");
            */
            //driver.get("https://mts.by");

            // Шаг 2: Проверить placeholder в поле "Номер телефона"
            System.out.println("Выбрали услуги связи");
            if (mtsPage.isConnectionPhonePlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'Номер телефона' корректен.");
            } else {
                System.err.println("Placeholder для поля 'Номер телефона' некорректен.");
            }

            // Шаг 3: Проверить placeholder в поле "Сумма"
            if (mtsPage.isConnectionSumPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'Сумма' корректен.");
            } else {
                System.err.println("Placeholder для поля 'Сумма' некорректен.");
            }

            // Шаг 4: Проверить placeholder в поле "E-mail для отправки чека"
            if (mtsPage.isConnectionEmailPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'E-mail для отправки чека' корректен.");
            } else {
                System.err.println("Placeholder для поля 'E-mail для отправки чека' некорректен.");
            }


            System.out.println("Выбрали домашний интернет");
            mtsPage.selectInternetOption();

            // Шаг 2: Проверить placeholder в поле "Номер абонента"
            if (mtsPage.isInternetPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'Номер абонента' корректен.");
            } else {
                System.err.println("Placeholder для поля 'Номер абонента' некорректен.");
            }

            if (mtsPage.isInternetSumPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'Сумма' корректен.");
            } else {
                System.err.println("Placeholder для поля 'Сумма' некорректен.");
            }

            if (mtsPage.isInternetEmailPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'E-mail для отправки чека' корректен.");
            } else {
                System.err.println("Placeholder для поля 'E-mail для отправки чека' некорректен.");
            }

            System.out.println("Выбрали рассрочку");
            mtsPage.selectInstalmentOption();

            // Шаг 2: Проверить placeholder в поле "Рассрочка"
            if (mtsPage.isInstalmentPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'Номер счета на 44' корректен.");
            } else {
                System.err.println("Placeholder для поля 'Номер счета на 44' некорректен.");
            }

            if (mtsPage.isInstalmentSumPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'Сумма' корректен.");
            } else {
                System.err.println("Placeholder для поля 'Сумма' некорректен.");
            }

            if (mtsPage.isInstalmentEmailPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'E-mail для отправки чека' корректен.");
            } else {
                System.err.println("Placeholder для поля 'E-mail для отправки чека' некорректен.");
            }

            System.out.println("Выбрали задолженность");
            mtsPage.selectArrearsOption();

            // Шаг 2: Проверить placeholder в поле "Задолженность"
            if (mtsPage.isArrearsPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'Номер счета на 2073' корректен.");
            } else {
                System.err.println("Placeholder для поля 'Номер счета на 2073' некорректен.");
            }

            if (mtsPage.isArrearsSumPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'Сумма' корректен.");
            } else {
                System.err.println("Placeholder для поля 'Сумма' некорректен.");
            }

            if (mtsPage.isArrearsEmailPlaceholderCorrect()) {
                System.out.println("Placeholder для поля 'E-mail для отправки чека' корректен.");
            } else {
                System.err.println("Placeholder для поля 'E-mail для отправки чека' некорректен.");
            }
            //Выбрать услуги связи и заполнить данные
            mtsPage.selectConnectionOption();

            // Шаг 4: Заполнить поля (телефон и сумма) и нажать "Продолжить"
            mtsPage.fillPaymentDetails("297777777", "100");
            mtsPage.clickContinue();
            System.out.println("Поля успешно заполнены и кнопка 'Продолжить' нажата.");

            // Проверка фрейма
            if (mtsPage.isIframeLoaded()) {
                System.out.println("Платежный iframe загрузился");
            } else {
                System.err.println("Платежный iframe не загрузился");
            }

            if (mtsPage.isCostDisplayedOnHeader()) {
                System.out.println("Сумма оплаты 100 рублей отображается корректно в заголовке");
            } else {
                System.err.println("Сумма оплаты 100 рублей не отображается в заголовке");
            }

            if (mtsPage.isCostDisplayedOnButton()) {
                System.out.println("Сумма оплаты 100 рублей отображается корректно на кнопке");
            } else {
                System.err.println("Сумма оплаты 100 рублей не отображается на кнопке");
            }

            if (mtsPage.isCardNumberLabelCorrect()) {
                System.out.println("Подсказка в поле номер карты отображается");
            } else {
                System.err.println("Подсказка в поле номер карты не отображается");
            }

            if (mtsPage.isPhoneNumberShownCorrect()) {
                System.out.println("Номер телефона отображается корректно");
            } else {
                System.err.println("Номер телефона не отображается");
            }
            if (mtsPage.isExpirationDateLabelCorrect()) {
                System.out.println("Подсказка в поле Срок действия отображается");
            } else {
                System.err.println("Подсказка в поле Срок действия не отображается");
            }

            if (mtsPage.isCvcLabelCorrect()) {
                System.out.println("Подсказка в поле CVC отображается");
            } else {
                System.err.println("Подсказка в поле CVC не отображается");
            }
            if (mtsPage.isCardHolderLabelCorrect()) {
                System.out.println("Подсказка в поле Имя держателя (как на карте) отображается");
            } else {
                System.err.println("Подсказка в поле Имя держателя (как на карте) не отображается");
            }

            if (mtsPage.isLogoDisplayedIframe()) {
                System.out.println("Логотип Visa отображается.");
            } else {
                System.err.println("Логотип Visa не найден.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

