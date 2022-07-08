package org.kitapyurdu.scenarios;

import org.apache.log4j.Logger;
import org.kitapyurdu.base.BaseTest;
import org.kitapyurdu.page.BasketPage;
import org.kitapyurdu.page.HomePage;
import org.kitapyurdu.page.SearchPage;

public class Test extends BaseTest {

    final static Logger LOG = Logger.getLogger(Test.class);


    @org.junit.Test
    public void test(){
        new HomePage(driver).checkHomePageOpen();
        String aranacakKelime=new HomePage(driver).readExel(0,0);
        new SearchPage(driver).searchWriteTextAndPressEnter(aranacakKelime);
        new BasketPage(driver).randomProductClick();
        new BasketPage(driver).addToBasketCartValueVerify();
        new BasketPage(driver).addToBaksetUpdateVerify();
        new BasketPage(driver).basketRemoveVerify();
    }
}
