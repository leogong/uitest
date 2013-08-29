package com.qunar.autotest.uitest.stepdefs;

import com.qunar.autotest.uitest.context.DataContext;
import com.qunar.autotest.uitest.model.PageBean;
import com.qunar.autotest.uitest.pages.PageFactory;
import com.qunar.autotest.uitest.pages.ShoeHomePage;
import com.qunar.autotest.uitest.tools.FileReadWrite;
import com.qunar.autotest.uitest.tools.StringConvert;
import cucumber.annotation.en.When;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Set;

public class ShoeHomeManagementStepsdef {
	@Autowired
	protected PageFactory pageFactory;

	@When("^进入软件管理页面$")
	public void goToSoftManagement() {
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
		page.topNav("软件管理");
	}

	@When("^进入软件添加页面$")
	public void gotoAddSoft() {
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
		page.topNav("添加软件");
	}

	@When("^从U9获取数据$")
	public void getSoftFromU9() throws Exception {
		String path="src/main/resources/com/qunar/autotest/uitest/filterKeywords.txt";
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
//		NodeList nList = page.getMapList(DataContext.getDataMap("oldDateString"), DataContext.getDataMap("name"), "http://war3.uuu9.com/Soft/List_22.shtml");
        NodeList nList = page.getMapList(DataContext.getDataMap("oldDateString"), DataContext.getDataMap("name"), "http://war3.uuu9.com/Soft/List_22_353.shtml");
        System.out.println("共" + nList.size() + "个");
		Set<String> set;
		boolean flag = false;
		for (int i = nList.size() - 1; i >= 0; i--) {
			do {
				flag = false;
				set = FileReadWrite.getKeyWordsList(path);
				Div div = (Div) nList.elementAt(i);
                String urlFromDiv = page.getURLFromDiv(div);
                System.out.println(urlFromDiv);
                PageBean pagebean = page.getPageBean(urlFromDiv);
				System.out.println("当前执行到第     " + i + " 个         " + pagebean.getSort()[2]);
				page.setSort(pagebean.getSort());
                String softName = StringConvert.filterKeyWords(StringConvert.stringFilter(pagebean.getSort()[2]), set);
                page.setSoftName(softName);
				page.setSoftSize(new Double(pagebean.getSoftSize()));
				page.setKBORMB("MB");
				page.setTags(softName);
                String desp = StringConvert.filterKeyWords(StringConvert.stringFilter(pagebean.getDesp()), set);
                page.setDesp(StringUtils.isEmpty(desp)? softName : desp);
				page.addMoreDLURL(pagebean.getDownload().length);
				page.setDownloadURL(pagebean.getDownload());
				page.setUpdateDate(pagebean.getUpdateDate());
				page.otherSettings(1);
				page.setPicURL(pagebean.getPicURL());
				page.save(2);
				try {
					Alert alert = page.getAlert();
					String alertString = alert.getText();
					String indexString="包含被禁止关键字被拦截:";
					if (alertString.contains(indexString)) {
						flag = true;
						String keyWord=alertString.substring(alertString.indexOf("“")+1, alertString.lastIndexOf("”"));
						FileReadWrite.writeKeyWords(path, keyWord.trim());
					}
					alert.accept();
				} catch (NoAlertPresentException ignored) {

				}
			} while (flag);
			page.goOn();
		}

	}

	@When("^获取最新软件更新时间$")
	public void getSoftLatestDate() throws ParseException {
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
		DataContext.setDataMap("oldDateString", page.getSoftLatestDate());
		DataContext.setDataMap("name", page.getSoftLatestName());
	}

	@When("^生成首页及生成地图$")
	public void createIndex() throws ParseException {
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
		page.topNav("生成首页");
		page.topNav("生成地图");
	}
	
	
	@When("^进入生成html页面进入操作$")
	public void gotoCreateHTML() throws ParseException {
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
		page.topNav("生成HTML");
		
	}
	
	@When("^生成站点地图$")
	public void createSiteMap() throws ParseException {
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
		page.createsiteMap();
	}
	
	@When("^生成所有列表页面$")
	public void createsortList() throws ParseException {
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
		page.createHTMLSort();
	}
	
	@When("^生成所有其它页面$")
	public void createotherpage() throws ParseException {
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
		page.creataOtherList();
	}
	
	@When("^生成所有内容页面$")
	public void createcontentpage() throws ParseException {
		ShoeHomePage page = pageFactory.getPage(ShoeHomePage.class);
		page.createHTMLContent();
	}
	
}
