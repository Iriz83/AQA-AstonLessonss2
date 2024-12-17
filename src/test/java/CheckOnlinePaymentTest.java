import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;



public class CheckOnlinePaymentTest {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Iri\\IdeaProjects\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://mts.by");
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            try {
                // Ожидаем кликабельность кнопки "Принять" и кликаем
                WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Принять')]")));
                acceptCookiesButton.click();
                System.out.println("Окно с куки успешно закрыто.");
            } catch (Exception e) {
                System.out.println("Окно с куки не появилось.");
            }

            // ======= 1. Проверка названия указанного блока =======
            try{
                WebElement header = driver.findElement(By.xpath("//h2[contains(., 'Онлайн пополнение') and contains(., 'без комиссии')]"));
                System.out.println("1. Блок с названием найден.");
            }
            catch (NoSuchElementException e){
                System.err.println("1. Ошибка: блок с названием не найден.");
            }

            // ======= 2. Проверка наличия логотипов платёжных систем =======
            try {
                WebElement visaLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Visa']")));
                System.out.println("2. Логотип Visa найден и отображается.");
            } catch (Exception e) {
                System.err.println("2. Логотип Visa не найден.");
            }

            try {
                WebElement visaLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Verified By Visa']")));
                System.out.println("2. Логотип Verified By Visa найден и отображается.");
            } catch (Exception e) {
                System.err.println("2. Логотип Verified By Visa не найден.");
            }

            try {
                WebElement masterCardLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='MasterCard']")));
                System.out.println("2. Логотип MasterCard найден и отображается.");
            } catch (Exception e) {
                System.err.println("2. Логотип MasterCard не найден.");
            }

            try {
                WebElement masterCardLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='MasterCard Secure Code']")));
                System.out.println("2. Логотип MasterCard Secure Code найден и отображается.");
            } catch (Exception e) {
                System.err.println("2. Логотип MasterCard Secure Code не найден.");
            }

            try {
                WebElement masterCardLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Белкарт']")));
                System.out.println("2. Логотип Белкарт найден и отображается.");
            } catch (Exception e) {
                System.err.println("2. Логотип Белкарт не найден.");
            }

            // ======= 3. Проверка работы ссылки "Подробнее о сервисе" =======
            try {

                WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/' and contains(text(), 'Подробнее о сервисе')]")
                ));
                System.out.println("3. Ссылка найдена и отображается: " + link.getText());

                link.click();

                // Ждем заголовок на новой странице
                WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(text(), 'Оплата банковской картой')]")
                ));
                System.out.println("3. Заголовок найден: " + header.getText());
            }
            catch (TimeoutException e) {
                System.err.println("3. Ошибка: Ссылка не найдена или не отображается на странице.");
            }
            catch (NoSuchElementException e) {
                System.err.println("Ошибка: Элемент не найден.");
                e.printStackTrace();
            }

            // ======= 4. Заполнение поля и проверка работы кнопки "Продолжить" =======
            driver.get("https://mts.by");

            try {
                // Ввод телефона в поле id="connection-phone"
                WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
                phoneField.sendKeys("297777777");

                // Ввод суммы в поле id="connection-sum"
                WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
                sumField.sendKeys("100");

                // Нажатие на кнопку с CSS-селектором #pay-connection > button:nth-child(4)
                WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#pay-connection > button:nth-child(4)")));
                continueButton.click();

                System.out.println("4. Данные успешно введены, кнопка нажата!");

                // дальнейшая проверка предполагает проверку
                // открывшегося платежного окна (но эта проверка не возможна,
                // тк это iframe с другого домена (банк), Селениум его не видит в целях безопасности


            } catch (Exception e) {
                System.err.println("4. Произошла ошибка: " + e.getMessage());
                e.printStackTrace();            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрытие браузера
            driver.quit();
            System.out.println("Тест завершён.");
        }
    }
}