package com.qunar.autotest.uitest.stepdefs;

import com.qunar.autotest.uitest.context.DataContext;
import com.qunar.autotest.uitest.model.User;
import com.qunar.autotest.uitest.model.UserRowMapper;
import com.qunar.autotest.uitest.pages.LoginUUU9HomePage;
import com.qunar.autotest.uitest.pages.PageFactory;
import com.qunar.autotest.uitest.pages.UuubbsPage;
import cucumber.annotation.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;

public class UuuMessageManagementStepsdef {
    @Autowired
    protected PageFactory pageFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private User user;

    @When("^登录UUU9论坛$")
    public void loginUUU9() throws InterruptedException, IOException, ClassNotFoundException {
        user = (User) jdbcTemplate.queryForObject("select * from bbs_info where type='uuu9'", new UserRowMapper());
        System.out.println("----------------------------------------------");
        LoginUUU9HomePage loginPage = pageFactory.gotoPage(LoginUUU9HomePage.class);
        loginPage.login(user);
    }

    @When("^UUU9随机进入一个列表页面$")
    public void goToListPage() {
        UuubbsPage page = pageFactory.getPage(UuubbsPage.class);
        boolean flag = page.goToListPage(System.currentTimeMillis() % 2, System.currentTimeMillis() % 26 + 4, System.currentTimeMillis() % 12);
        while (!flag) {
            page.returnHomePage();
            goToListPage();
        }
    }

    @When("^UUU9随机进入一个内容页面$")
    public void goToContentPage() {
        UuubbsPage page = pageFactory.getPage(UuubbsPage.class);
        page.goToContentPage();
    }

    @When("^UUU9随机选择关键字留言$")
    public void leaveMessage() {
        UuubbsPage page = pageFactory.getPage(UuubbsPage.class);
        StringBuffer sb = new StringBuffer(DataContext.getCommonMessage());
        List<String> keyWords = DataContext.getKeyWords();
        sb.append("\n \n \n");
        sb.append(String.format(DataContext.getUrlMessage(), keyWords.get((int) (System.currentTimeMillis() % keyWords.size()))));
        boolean flag = page.leveMessage(sb.toString());
        while (!flag) {
            doAgain();
        }
        int totalMessage = user.getTotalMessage() + 1;
        jdbcTemplate.update("update bbs_info set totalMessage=" + totalMessage + " where type='uuu9'");
    }

    public void doAgain() {
        UuubbsPage page = pageFactory.getPage(UuubbsPage.class);
        page.returnHomePage();
        goToListPage();
        goToContentPage();
        leaveMessage();
    }
}
