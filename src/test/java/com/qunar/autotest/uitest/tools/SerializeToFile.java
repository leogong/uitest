package com.qunar.autotest.uitest.tools;

import com.qunar.autotest.uitest.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerializeToFile {

	public static User getUser() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(
                "/opt/chromedriver/myPerson.output");
        ObjectInputStream ois = new ObjectInputStream(fis);
		return (User) ois.readObject();
	}
}
