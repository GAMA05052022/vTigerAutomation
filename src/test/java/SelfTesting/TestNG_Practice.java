package SelfTesting;

import org.testng.annotations.Test;

public class TestNG_Practice {

	@Test(priority = 2)
	public void login() {
		int a = 10 % 0;
		System.out.println(a);
	}

	@Test
	public void test() {
		System.out.println("Test");
	}

	@Test(dependsOnMethods = "login", priority = 1)
	public void logOut() {
		System.out.println("LogOut");
	}

}
