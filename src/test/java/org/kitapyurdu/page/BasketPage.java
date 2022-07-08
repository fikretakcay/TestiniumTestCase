package org.kitapyurdu.page;

import org.apache.log4j.Logger;
import org.kitapyurdu.base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Random;


public class BasketPage extends BasePage {
    @FindBy(id = "button-cart")
    private WebElement addToBasketBtn;
    @FindBy(xpath = "//div[@id=\"sprite-cart-icon\"]")
    private WebElement basketProductNumber;
    @FindBy(xpath = "//*[@id=\"button-cart\"]/span[text()=\"Sepetinizde\"]")
    private WebElement basketCheck;
    @FindBy(id = "cart")
    private WebElement myBasket;
    @FindBy(id = "js-cart")
    private WebElement goToCart;
    @FindBy(xpath = "//*[@class=\"js-cart-update-product\"]/input[@name=\"quantity\"]")
    private WebElement basketIncrementInput;
    @FindBy(xpath = "//*[@title=\"Güncelle\"]")
    private WebElement updateBtn;
    @FindBy(xpath = "//*[@id=\"swal2-title\" and text()=\"Sepetiniz güncelleniyor!\"]")
    private WebElement yourCartUpdating;
    @FindBy(xpath = "//*[@title=\"Kaldır\"]")
    private WebElement basketRemove;
    @FindBy(xpath = "//*[@id=\"cart-items-empty\"]")
    private WebElement basketEmpty;
    @FindBy(xpath ="//div[@class=\"product-cr\"]//*[@class=\"name\"]" )
    private  WebElement productList;


    final static Logger LOG = Logger.getLogger(BasketPage.class);
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public void randomProductClick(){
        try {
            waitUntilElementVisible(productList);
            List<WebElement> product = driver.findElements(By.xpath("//div[@class=\"product-cr\"]//*[@class=\"name\"]"));
            Random rand = new Random();
            int rand_product = rand.nextInt(product.size());
            findScrollElementCenter(product.get(rand_product));
            wait(2);
            product.get(rand_product).click();
            waitUntilElementVisible(addToBasketBtn);
        }catch (Exception e){
            LOG.error("Random product was a problem add to Cart.");
            Assert.fail("Random product was a problem add to Cart.");
        }
    }
    public void addToBasketCartValueVerify(){
        try {
            int productNumber = Integer.valueOf(basketProductNumber.getText());
            findScrollElementCenter(addToBasketBtn);
            wait(1);
            addToBasketBtn.click();
            wait(3);
            int productNumberAddToBasket = Integer.parseInt(basketProductNumber.getText());
            if (productNumber + 1 == productNumberAddToBasket) {
                LOG.info("Number of items added to cart correctly..");
            } else {
                LOG.error("addToBasketCartValueVerify didn't work correctly.");
                Assert.fail("addToBasketCartValueVerify didn't work correctly.");
            }
        }catch (Exception e){
            LOG.error("addToBasketCartValueVerify an error occurred while making.");
            Assert.fail("addToBasketCartValueVerify an error occurred while making.");
        }
    }

    public void addToBaksetUpdateVerify(){
        try {
            myBasket.click();
            waitUntilElementVisible(goToCart);
            goToCart.click();
            wait(3);
            waitUntilElementVisible(basketIncrementInput);
            int arttırmaoncesi=Integer.valueOf(basketIncrementInput.getAttribute("value"));
            basketIncrementInput.clear();
            wait(1);
            basketIncrementInput.sendKeys(String.valueOf(arttırmaoncesi+1));
            wait(1);
            updateBtn.click();
            waitUntilElementVisible(yourCartUpdating);
        }catch (Exception e){
            LOG.error("An occurred error while checking update.");
            Assert.fail("An occurred error while checking update.");
        }
    }


    public void basketRemoveVerify(){
        try {
            basketRemove.click();
            waitUntilElementVisible(basketEmpty);
        }catch (Exception e){
            LOG.error("basketRemoveVerify an error occurred.");
            Assert.fail();
        }

    }
}
