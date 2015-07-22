package com.adrianjaylopez.passwordworld.dal;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.fail;


public class AppDataStorageTest {
	

	@Test
	public void testSetGetAppData() {
		fail("Not yet implemented");
		try {
			Class<?> dataStorage = Class.forName("com.adrianjaylopez.passwordworld.dal.AppDataStorage");
			Constructor<?> constructor = dataStorage.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object t = constructor.newInstance();
			Method doIt = dataStorage.getDeclaredMethod("getAppData");
			doIt.setAccessible(true);
			Assert.assertTrue(doIt.invoke(t) != null);
			
			Method doIt2 = dataStorage.getDeclaredMethod("closeAppData");
			doIt2.setAccessible(true);
			doIt2.invoke(t);
			
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSetAppData(){
		fail("Not yet implemented");
		try {
			Class<?> dataStorage = Class.forName("com.adrianjaylopez.passwordworld.dal.AppDataStorage");
			Constructor<?> constructor = dataStorage.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object t = constructor.newInstance();
			Method doIt = dataStorage.getDeclaredMethod("setAppData", String.class);
			doIt.setAccessible(true);
			doIt.invoke(t, "01");
			
			Method doIt2 = dataStorage.getDeclaredMethod("closeAppData");
			doIt2.setAccessible(true);
			doIt2.invoke(t);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
			
			e.printStackTrace();
		}
		
	}


}
