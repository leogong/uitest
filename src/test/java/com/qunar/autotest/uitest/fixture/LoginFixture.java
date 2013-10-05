package com.qunar.autotest.uitest.fixture;

import com.qunar.autotest.uitest.pages.LoginShoeHomePage;
import com.qunar.autotest.uitest.pages.LoginUUU9HomePage;
import com.qunar.autotest.uitest.pages.PageFactory;
import cucumber.annotation.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class LoginFixture {

    @Autowired
    protected PageFactory pageFactory;

    @When("^作为管理员登录系统$")
    public void loginShoeHome() throws InterruptedException, IOException, ClassNotFoundException {
        LoginShoeHomePage loginPage = pageFactory.gotoPage(LoginShoeHomePage.class);
        loginPage.login();
    }
}
