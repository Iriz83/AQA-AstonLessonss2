import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class MtsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By acceptCookiesButton = By.xpath("//button[contains(text(), 'Принять')]");
    //private By onlineTopUpHeader = By.xpath("//h2[contains(text(), 'Онлайн пополнение') and contains(., 'без комиссии')]");
    private By onlineTopUpHeader = By.xpath("//h2[normalize-space()='Онлайн пополнение без комиссии']");
    private By visaLogo = By.xpath("//img[@alt='Visa']");
    private By visaLogoIframe = By.xpath("//img[@class='ng-tns-c61-0 ng-star-inserted']");;
    private By connectionPhoneField = By.id("connection-phone");
    private By connectionSumField = By.id("connection-sum");
    private By connectionEmailField = By.id("connection-email");

    private By internetPhoneField = By.id("internet-phone");
    private By internetSumField = By.id("internet-sum");
    private By internetEmailField = By.id("internet-email");

    private By instalmentScoreField = By.id("score-instalment");
    private By instalmentSumField = By.id("instalment-sum");
    private By instalmentEmailField = By.id("instalment-email");

    private By arrearsScoreField = By.id("score-arrears");
    private By arrearsSumField = By.id("arrears-sum");
    private By arrearsEmailField = By.id("arrears-email");


    private By continueButton = By.cssSelector("#pay-connection > button:nth-child(4)");
    private By servicesDropdown = By.id("pay");

    private By payButton = By.xpath("//button[@class='colored disabled']");
    private By cardNumberInput = By.xpath("//label[@class='ng-tns-c46-1 ng-star-inserted']");
    private By expirationDateInput = By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']");
    private By cvcInput = By.xpath("//label[@class='ng-tns-c46-5 ng-star-inserted']");
    private By cardHolderlInput = By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']");
    private By phoneNumber = By.xpath("//div[@class='pay-description__text']");

    public MtsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void acceptCookies() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
            button.click();
            System.out.println("Окно с куки успешно закрыто.");
        } catch (Exception e) {
            System.out.println("Окно с куки не появилось.");
        }
    }

    public boolean isOnlineTopUpHeaderDisplayed() {
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(onlineTopUpHeader));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(visaLogo));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void fillPaymentDetails(String phoneNumber, String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(connectionPhoneField)).sendKeys(phoneNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(connectionSumField)).sendKeys(amount);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getPlaceholderText(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform(); // Переместиться на элемент для фокусировки
        return element.getAttribute("placeholder");
    }

    public boolean isConnectionPhonePlaceholderCorrect() {
        return getPlaceholderText(connectionPhoneField).equals("Номер телефона");
    }

    public boolean isConnectionSumPlaceholderCorrect() {
        return getPlaceholderText(connectionSumField).equals("Сумма");
    }

    public boolean isConnectionEmailPlaceholderCorrect() {
        return getPlaceholderText(connectionEmailField).equals("E-mail для отправки чека");
    }

    public boolean isInternetPlaceholderCorrect() {
        return getPlaceholderText(internetPhoneField).equals("Номер абонента");
    }

    public boolean isInternetSumPlaceholderCorrect() {
        return getPlaceholderText(internetSumField).equals("Сумма");
    }

    public boolean isInternetEmailPlaceholderCorrect() {
        return getPlaceholderText(internetEmailField).equals("E-mail для отправки чека");
    }

    public boolean isInstalmentPlaceholderCorrect() {
        return getPlaceholderText(instalmentScoreField).equals("Номер счета на 44");
    }

    public boolean isInstalmentSumPlaceholderCorrect() {
        return getPlaceholderText(instalmentSumField).equals("Сумма");
    }

    public boolean isInstalmentEmailPlaceholderCorrect() {
        return getPlaceholderText(instalmentEmailField).equals("E-mail для отправки чека");
    }

    public boolean isArrearsPlaceholderCorrect() {
        return getPlaceholderText(arrearsScoreField).equals("Номер счета на 2073");
    }

    public boolean isArrearsSumPlaceholderCorrect() {
        return getPlaceholderText(arrearsSumField).equals("Сумма");
    }

    public boolean isArrearsEmailPlaceholderCorrect() {
        return getPlaceholderText(arrearsEmailField).equals("E-mail для отправки чека");
    }

    public void selectInternetOption() {
        WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(servicesDropdown));

        // Используем класс Select для взаимодействия с выпадающим списком
        Select dropdown = new Select(dropdownElement);

        // Выбираем элемент по видимому тексту
        dropdown.selectByVisibleText("Домашний интернет");
    }

    public void selectInstalmentOption() {
        WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(servicesDropdown));

        // Используем класс Select для взаимодействия с выпадающим списком
        Select dropdown = new Select(dropdownElement);

        // Выбираем элемент по видимому тексту
        dropdown.selectByVisibleText("Рассрочка");
    }

    public void selectArrearsOption() {
        WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(servicesDropdown));

        // Используем класс Select для взаимодействия с выпадающим списком
        Select dropdown = new Select(dropdownElement);

        // Выбираем элемент по видимому тексту
        dropdown.selectByVisibleText("Задолженность");
    }

    public void selectConnectionOption() {
        WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(servicesDropdown));

        // Используем класс Select для взаимодействия с выпадающим списком
        Select dropdown = new Select(dropdownElement);

        // Выбираем элемент по видимому тексту
        dropdown.selectByVisibleText("Услуги связи");
    }


    public boolean isIframeLoaded() {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("bepaid-iframe")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='app-wrapper__content']")));

            return true;
/*
            List<WebElement> textElements = driver.findElements(By.xpath("//span[text()]"));
            for (WebElement element : textElements) {
                System.out.println("Текст: " + element.getText());
            }
*/
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке iframe': " + e.getMessage());
            return false;
        }
    }

    public boolean isCostDisplayedOnHeader() {

        WebElement phoneNumberFrame = driver.findElement(By.xpath("//div[@class='pay-description__cost']/span[1]"));
        return phoneNumberFrame.getText().equals("100.00 BYN");
    }

    public boolean isPhoneNumberShownCorrect() {

        WebElement phone = driver.findElement(phoneNumber);
        //System.out.println(cardNumber.getText());
        return phone.getText().contains("297777777");
    }

    public boolean isCardNumberLabelCorrect() {

        WebElement cardNumber = driver.findElement(cardNumberInput);
        //System.out.println(cardNumber.getText());
        return cardNumber.getText().equals("Номер карты");
    }

    public boolean isExpirationDateLabelCorrect() {

        WebElement expirationDate = driver.findElement(expirationDateInput);
        return expirationDate.getText().equals("Срок действия");
    }
    public boolean isCvcLabelCorrect() {

        WebElement cvcLabel = driver.findElement(cvcInput);
        return cvcLabel.getText().equals("CVC");
    }

    public boolean isCardHolderLabelCorrect() {

        WebElement cardHolderLabel = driver.findElement(cardHolderlInput);
        return cardHolderLabel.getText().equals("Имя держателя (как на карте)");
    }

    public boolean isCostDisplayedOnButton() {

        WebElement button = driver.findElement(payButton);
        //System.out.println(button.getText());
        return button.getText().equals("Оплатить 100.00 BYN");
    }

    public boolean isLogoDisplayedIframe() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(visaLogoIframe));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //private static void stepCheckLogoDisplayed(MtsPage mtsPage) {
   // }

    //private static void stepAcceptCookies(MtsPage mtsPage) {
   //     mtsPage.acceptCookies();
   // }







}

