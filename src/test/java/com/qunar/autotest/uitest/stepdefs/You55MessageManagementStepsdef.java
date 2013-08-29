package com.qunar.autotest.uitest.stepdefs;

import com.qunar.autotest.uitest.context.DataContext;
import com.qunar.autotest.uitest.model.User;
import com.qunar.autotest.uitest.model.UserRowMapper;
import com.qunar.autotest.uitest.pages.Login55youHomePage;
import com.qunar.autotest.uitest.pages.PageFactory;
import com.qunar.autotest.uitest.pages.you55bbsPage;
import cucumber.annotation.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;

public class You55MessageManagementStepsdef {
    @Autowired
    protected PageFactory pageFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private User user;

    @When("^登录55you论坛$")
    public void login55you() throws InterruptedException, IOException, ClassNotFoundException {
        user = (User) jdbcTemplate.queryForObject("select * from bbs_info where type='55you'", new UserRowMapper());
        System.out.println("----------------------------------------------");
        Login55youHomePage loginPage = pageFactory.gotoPage(Login55youHomePage.class);
        loginPage.login(user);
    }

    @When("^55you随机进入一个列表页面$")
    public void goToListPage() {
        you55bbsPage page = pageFactory.getPage(you55bbsPage.class);
        page.goToListPage();
    }

    @When("^55you随机进入一个内容页面$")
    public void goToContentPage() {
        you55bbsPage page = pageFactory.getPage(you55bbsPage.class);
        page.goToContentPage();
    }

    @When("^55you随机选择关键字留言$")
    public void leaveMessage() {
        you55bbsPage page = pageFactory.getPage(you55bbsPage.class);
        StringBuilder sb = new StringBuilder(DataContext.getCommonMessage());
        if (user.getLeaveURL() && user.getAwayFromLeaveURL() == 0) {
            List<String> keyWords = DataContext.getKeyWords();
            sb.append("\n \n \n");
            sb.append(String.format(DataContext.getUrlMessage(), keyWords.get((int) (System.currentTimeMillis() % keyWords.size()))));
        }
        boolean flag = page.leveMessage(sb.toString());
        while (!flag) {
            System.out.printf("找不到留言框，重新再来");
            doAgain();
        }
        int totalMessage = user.getTotalMessage() + 1;
        int awayFromLeaveURL = user.getAwayFromLeaveURL() == 0 ? 0 : user.getAwayFromLeaveURL() - 1;
        jdbcTemplate.update("update bbs_info set totalMessage= ? ,awayFromLeaveURL= ?  where type='55you'",totalMessage,awayFromLeaveURL);
    }

    public void doAgain() {
        you55bbsPage page = pageFactory.getPage(you55bbsPage.class);
        page.returnHomePage();
        goToListPage();
        goToContentPage();
        leaveMessage();
    }
}
