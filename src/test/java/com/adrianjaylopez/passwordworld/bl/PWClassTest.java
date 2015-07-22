package com.adrianjaylopez.passwordworld.bl;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.fail;

public class PWClassTest {

	@Test
	public void testSetPw() {
		fail("Not yet implemented");
		try {
			Calendar.getInstance();
			Date date = new Date(Calendar.DAY_OF_WEEK_IN_MONTH);
			
			@SuppressWarnings("unchecked")
			Class<String> pwClass = (Class<String>) Class.forName("com.adrianjaylopez.passwordworld.bl.PWClass");
			Constructor<?> constructor = pwClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object t = constructor.newInstance();
			Method doIt = pwClass.getDeclaredMethod("setPw", Object.class, Object.class, Object.class, 
					Object.class, Object.class, Object.class, Date.class);
			doIt.setAccessible(true);
			doIt.invoke(t, "blah", "bored", "ZZZZZ", "need work", "hawaii", "good", 
					date);
			Method doIt2 = pwClass.getDeclaredMethod("search", Object.class, Object.class);
			doIt2.setAccessible(true);
			String[] blah = (String[]) doIt2.invoke(t, "blah", "bored");
			Assert.assertTrue(blah[0].equalsIgnoreCase("blah"));
			Assert.assertTrue(blah[1].equalsIgnoreCase("bored"));
			Assert.assertTrue(blah[2].equalsIgnoreCase("ZZZZZ"));
			
			
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateTTTTTT() {
		fail("Not yet implemented");
		
		try {
			
			@SuppressWarnings("unchecked")
			Class<String> pwClass = (Class<String>) Class.forName("com.adrianjaylopez.passwordworld.bl.PWClass");
			Constructor<?> constructor = pwClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object t = constructor.newInstance();
			Method doIt = pwClass.getDeclaredMethod("update", Object.class, Object.class, Object.class, 
					Object.class, Object.class, Object.class);
			doIt.setAccessible(true);
			doIt.invoke(t, "hungry", "cookies", "pizza", "glue", "gummy", "hungry");
			Method doIt2 = pwClass.getDeclaredMethod("search", Object.class, Object.class);
			doIt2.setAccessible(true);
			String[] blah = (String[]) doIt2.invoke(t, "blah", "bored");
			Assert.assertTrue(blah[0] == "hungry");
			Assert.assertTrue(blah[1] == "cookies");
			Assert.assertTrue(blah[2] == "pizza");
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateTTTTTTTT() {
		fail("Not yet implemented");
		
		try {
			
			@SuppressWarnings("unchecked")
			Class<String> pwClass = (Class<String>) Class.forName("com.adrianjaylopez.passwordworld.bl.PWClass");
			Constructor<?> constructor = pwClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object t = constructor.newInstance();
			Method doIt = pwClass.getDeclaredMethod("update", Object.class, Object.class, Object.class, 
					Object.class, Object.class, Object.class);
			doIt.setAccessible(true);
			doIt.invoke(t, "hungry", "cookies", "pizza", "glue", "gummy", "hungry");
			Method doIt2 = pwClass.getDeclaredMethod("update", Object.class, Object.class, Object.class, Object.class, Object.class, 
					Object.class, Object.class, Object.class);
			doIt2.setAccessible(true);
			doIt2.invoke(t, "hungry", "cookies", "eat me", "coolio", "sugar", "help me", "tree", "julious");
			Method doIt3 = pwClass.getDeclaredMethod("search", Object.class, Object.class);
			doIt3.setAccessible(true);
			String[] blah = (String[]) doIt3.invoke(t, "eat me", "coolio");
			Assert.assertTrue(blah[0] == "eat me");
			Assert.assertTrue(blah[1] == "coolio");
			Assert.assertTrue(blah[3] == "help me");
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
		
try {
			
			@SuppressWarnings("unchecked")
			Class<String> pwClass = (Class<String>) Class.forName("com.adrianjaylopez.passwordworld.bl.PWClass");
			Constructor<?> constructor = pwClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object t = constructor.newInstance();
			Method doIt = pwClass.getDeclaredMethod("update", Object.class, Object.class, Object.class, 
					Object.class, Object.class, Object.class);
			doIt.setAccessible(true);
			doIt.invoke(t, "eat me", "coolio", "sugar", "help me", "tree", "julious");
			
			Method doIt2 = pwClass.getDeclaredMethod("delete", Object.class, Object.class);
			doIt2.setAccessible(true);
			doIt2.invoke(t, "eat me", "coolio");
			Method doIt3 = pwClass.getDeclaredMethod("search", Object.class, Object.class);
			doIt3.setAccessible(true);
			String[] blah = (String[]) doIt3.invoke(t, "eat me", "coolio");
			Assert.assertFalse(blah[0] == "eat me");
			Assert.assertFalse(blah[1] == "coolio");
			Assert.assertFalse(blah[3] == "help me");
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("unchecked")
	@Test
	public void getFolderNTitle() {
		fail("Not yet implemented");
		try {
			Calendar.getInstance();
			Date date = new Date(Calendar.DAY_OF_WEEK_IN_MONTH);
			Class<String> pwClass = (Class<String>) Class.forName("com.adrianjaylopez.passwordworld.bl.PWClass");
			Constructor<?> constructor = pwClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object t = constructor.newInstance();
			Method doIt = pwClass.getDeclaredMethod("setPw", Object.class, Object.class, 
					Object.class, Object.class, Object.class, Object.class, Date.class);
			doIt.setAccessible(true);
			doIt.invoke(t, "blah", "bored", "ZZZZZ", "need work", "hawaii", "good", 
					date);
			//TODO continue from here
			Method doIt2 = pwClass.getDeclaredMethod("update", Object.class, Object.class, Object.class, 
					Object.class, Object.class, Object.class);
			doIt2.setAccessible(true);
			doIt2.invoke(t, "hungry", "cookies", "pizza", "glue", "gummy", "tummy");
			doIt2.invoke(t, "eat me", "coolio", "sugar", "help me", "tree", "julious");

			
			Method doIt3 = pwClass.getDeclaredMethod("getFolderNTitle");
			doIt3.setAccessible(true);
			List<String[]> blah = new ArrayList<>();
			blah.addAll((Collection<? extends String[]>) doIt3.invoke(t));
			for(String[] go: blah){
				for(String now: go){
					System.out.println(now);
				}
			}
			
			
						
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testSearch() {
		//fail("Not yet implemented");
		
		try {
			Calendar.getInstance();
			Date date = new Date(Calendar.DAY_OF_WEEK_IN_MONTH);
			@SuppressWarnings("unchecked")
			Class<String> pwClass = (Class<String>) Class.forName("com.adrianjaylopez.passwordworld.bl.PWClass");
			Constructor<?> constructor = pwClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object t = constructor.newInstance();
			Method doIt = pwClass.getDeclaredMethod("setPw", Object.class, Object.class, 
					Object.class, Object.class, Object.class, Object.class, Date.class);
			doIt.setAccessible(true);
			doIt.invoke(t, "blah", "bored", "ZZZZZ", "need work", "hawaii", "good", 
					date);
			//TODO continue from here
			Method doIt2 = pwClass.getDeclaredMethod("update", Object.class, Object.class, Object.class, 
					Object.class, Object.class, Object.class);
			doIt2.setAccessible(true);
			doIt2.invoke(t, "hungry", "cookies", "pizza", "glue", "gummy", "tummy");
			doIt2.invoke(t, "eat me", "coolio", "sugar", "help me", "tree", "julious");
			
			
			Method doIt3 = pwClass.getDeclaredMethod("search", Object.class, Object.class);
			doIt3.setAccessible(true);
			String[] blah = (String[]) doIt3.invoke(t, "hungry", "cookies");
			for (String kaboom: blah)
				System.out.println(kaboom);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
