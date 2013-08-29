package com.qunar.autotest.uitest.stepdefs;

import com.qunar.autotest.uitest.context.DataContext;
import com.qunar.autotest.uitest.model.User;
import com.qunar.autotest.uitest.model.UserRowMapper;
import com.qunar.autotest.uitest.pages.Loginou99HomePage;
import com.qunar.autotest.uitest.pages.PageFactory;
import com.qunar.autotest.uitest.pages.Ou99bbsPage;
import cucumber.annotation.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;

public class Ou99MessageManagementStepsdef {
    @Autowired
    protected PageFactory pageFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private User user;

    @When("^登录ou99论坛$")
    public void loginou99() throws InterruptedException, IOException, ClassNotFoundException {
        user = (User) jdbcTemplate.queryForObject("select * from bbs_info where type='ou99'", new UserRowMapper());
        System.out.println("----------------------------------------------");
        Loginou99HomePage loginPage = pageFactory.gotoPage(Loginou99HomePage.class);
        loginPage.login(user);
    }

    @When("^ou99随机进入一个列表页面$")
    public void goToListPage() {
        Ou99bbsPage page = pageFactory.getPage(Ou99bbsPage.class);
        page.goToListPage();
    }

    @When("^ou99随机进入一个内容页面$")
    public void goToContentPage() {
        Ou99bbsPage page = pageFactory.getPage(Ou99bbsPage.class);
        page.goToContentPage();
    }

    @When("^ou99随机选择关键字留言$")
    public void leaveMessage() {
        Ou99bbsPage page = pageFactory.getPage(Ou99bbsPage.class);
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
        jdbcTemplate.update("update bbs_info set totalMessage= ? ,awayFromLeaveURL= ?  where type='ou99'",totalMessage,awayFromLeaveURL);
    }

    public void doAgain() {
        Ou99bbsPage page = pageFactory.getPage(Ou99bbsPage.class);
        page.returnHomePage();
        goToListPage();
        goToContentPage();
        leaveMessage();
    }
}
