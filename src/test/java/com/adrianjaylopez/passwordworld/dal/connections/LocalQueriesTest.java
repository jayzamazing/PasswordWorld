package com.adrianjaylopez.passwordworld.dal.connections;

import com.sun.rowset.CachedRowSetImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import static org.junit.Assert.fail;

public class LocalQueriesTest {
	// declare variables
	private CachedRowSet psCache;
	Method appIt = null;
	Object appObj = null;
	Class<?> appCls;
	Constructor<?> constructor;
	String[] query;

	@Test
	public void testDbCreate() {
		// fail("Not yet implemented");

		// creates a connection to the database
		try {
			appCls = Class
					.forName("com.adrianjaylopez.passwordworld.dal.connections.LocalQueries");
			constructor = appCls.getDeclaredConstructor();
			constructor.setAccessible(true);
			appObj = constructor.newInstance();

			appIt = appCls.getDeclaredMethod("setCredentials", String.class,
					char[].class);
			appIt.setAccessible(true);
			appIt.invoke(appObj, "blah", "kablah".toCharArray());

			appIt = appCls.getDeclaredMethod("dbCreate");
			appIt.setAccessible(true);
			appIt.invoke(appObj);

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {

			e.printStackTrace();
		}

	}

	@Test
	public void testDbQueryAll() {
		fail("Not yet implemented");
		try {

			appCls = Class
					.forName("com.adrianjaylopez.passwordworld.dal.connections.LocalQueries");
			constructor = appCls.getDeclaredConstructor();
			constructor.setAccessible(true);
			appObj = constructor.newInstance();

			appIt = appCls.getDeclaredMethod("setCredentials", String.class,
					char[].class);
			appIt.setAccessible(true);
			appIt.invoke(appObj, "blah", "kablah".toCharArray());

			appIt = appCls.getDeclaredMethod("dbQueryAll");
			appIt.setAccessible(true);
			appIt.invoke(appObj);

			query = new String[] { "Wow", "I r Bored", "really", "bored",
					"ZZzzzz", "ZZZZzz", "1960-01-01 23:03:20" };

			appIt = appCls.getDeclaredMethod("dbInsert", String[].class);
			appIt.setAccessible(true);
			appIt.invoke(appObj, (Object) query);

			psCache = new CachedRowSetImpl();

			appIt = appCls.getDeclaredMethod("getRowSet");
			appIt.setAccessible(true);

			psCache = (CachedRowSet) appIt.invoke(appObj);

			while (psCache.next()) {
				if (psCache.getString(4).compareTo("bored") == 0) {
					Assert.assertEquals("Wow", psCache.getString(1));
					Assert.assertEquals("I r Bored", psCache.getString(2));
					break;
				}
			}

		} catch (SQLException | InstantiationException | IllegalAccessException
				| ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();

		}
	}

	@Test
	public void testDbInsert() {
		fail("Not yet implemented");
		try {

			appCls = Class
					.forName("com.adrianjaylopez.passwordworld.dal.connections.LocalQueries");
			constructor = appCls.getDeclaredConstructor();
			constructor.setAccessible(true);
			appObj = constructor.newInstance();

			appIt = appCls.getDeclaredMethod("setCredentials", String.class,
					char[].class);
			appIt.setAccessible(true);
			appIt.invoke(appObj, "blah", "kablah".toCharArray());

			appIt = appCls.getDeclaredMethod("dbQueryAll");
			appIt.setAccessible(true);
			appIt.invoke(appObj);

			query = new String[] { "Hello", "hope this", "kablah", "something",
					"WWW.Something", "I was flying throu" };

			appIt = appCls.getDeclaredMethod("dbInsert", String[].class);
			appIt.setAccessible(true);
			appIt.invoke(appObj, (Object) query);

			psCache = new CachedRowSetImpl();

			appIt = appCls.getDeclaredMethod("getRowSet");
			appIt.setAccessible(true);
			psCache = (CachedRowSet) appIt.invoke(appObj);

			while (psCache.next()) {
				if (psCache.getString(2).compareTo("hope this") == 0) {
					Assert.assertEquals("Hello", psCache.getString(1));
					Assert.assertEquals("hope this", psCache.getString(2));
					break;
				}
			}

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {

			e.printStackTrace();
		}

	}

	@Test
	public void testDbUpdate() {
		fail("Not yet implemented");
		try {
			appCls = Class
					.forName("com.adrianjaylopez.passwordworld.dal.connections.LocalQueries");
			constructor = appCls.getDeclaredConstructor();
			constructor.setAccessible(true);
			appObj = constructor.newInstance();

			appIt = appCls.getDeclaredMethod("setCredentials", String.class,
					char[].class);
			appIt.setAccessible(true);
			appIt.invoke(appObj, "blah", "kablah".toCharArray());

			appIt = appCls.getDeclaredMethod("dbQueryAll");
			appIt.setAccessible(true);
			appIt.invoke(appObj);

			String[] oldInfo = new String[] { "Hello", "hope this" };

			query = new String[] { "Super blah", "crapola", "ZZzzzzz",
					"I am flying", "www.huh.com", "strange stuff happens" };

			appIt = appCls.getDeclaredMethod("dbUpdate", String[].class,
					String[].class);
			appIt.setAccessible(true);
			appIt.invoke(appObj, oldInfo, query);

			appIt = appCls.getDeclaredMethod("getRowSet");
			appIt.setAccessible(true);
			psCache = (CachedRowSet) appIt.invoke(appObj);

			while (psCache.next()) {
				if (psCache.getString(2).compareTo("crapola") == 0) {
					Assert.assertEquals("Super blah", psCache.getString(1));
					Assert.assertEquals("crapola", psCache.getString(2));
				}

			}
		} catch (SQLException | InstantiationException | IllegalAccessException
				| ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testDbDelete() {
		fail("Not yet implemented");
		try {

			appCls = Class
					.forName("com.adrianjaylopez.passwordworld.dal.connections.LocalQueries");
			constructor = appCls.getDeclaredConstructor();
			constructor.setAccessible(true);
			appObj = constructor.newInstance();

			appIt = appCls.getDeclaredMethod("setCredentials", String.class,
					char[].class);
			appIt.setAccessible(true);
			appIt.invoke(appObj, "blah", "kablah".toCharArray());

			appIt = appCls.getDeclaredMethod("dbQueryAll");
			appIt.setAccessible(true);
			appIt.invoke(appObj);

			query = new String[] { "Super blah", "crapola", "ZZzzzzz",
					"I am flying", "www.huh.com", "strange stuff happens" };

			appIt = appCls.getDeclaredMethod("dbDelete", String[].class);
			appIt.setAccessible(true);
			appIt.invoke(appObj, (Object) query);

			appIt = appCls.getDeclaredMethod("getRowSet");
			appIt.setAccessible(true);

			psCache = (CachedRowSet) appIt.invoke(appObj);

			while (psCache.next()) {
				if (psCache.getString(2).equals("crapola"))
					Assert.assertFalse(psCache.getString(2).equals("crapola"));
			}

		} catch (SQLException | InstantiationException | IllegalAccessException
				| ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {

			e.printStackTrace();
		}
	}

}
