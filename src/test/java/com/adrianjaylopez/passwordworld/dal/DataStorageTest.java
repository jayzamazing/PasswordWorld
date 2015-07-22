package com.adrianjaylopez.passwordworld.dal;

public class DataStorageTest {
	private DataStorage dataLayer;
	private PasswordClass query;
	private PWClass blah;
	private String[] oldInfo;

/**
	@Test
	public void testDBInsert() {
		fail("Not yet implemented");
		try {
			dataLayer = new DataStorage();
			dataLayer = new DataStorage("blah", "kablah".toCharArray(),
					dataLayer.getPage());

			query = new PasswordClass("something special", "fishing",
					"really bored", "sudfha", "www.blah.com", "ZZZzzzzzzzzzz" );
			dataLayer.DBInsert(query);
			blah =  dataLayer.getPass();

			while (blah.next()) {
				if (psCache.getString(2).equals("fishing")) {
					Assert.assertEquals("something special",
							psCache.getString(1));
					Assert.assertEquals("fishing", psCache.getString(2));
					Assert.assertEquals("really bored", psCache.getString(3));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testDBUpdate() {
		fail("Not yet implemented");
		try {

			dataLayer = new DataStorage();
			dataLayer = new DataStorage("blah", "kablah".toCharArray(),
					dataLayer.getPage());
			query = new PasswordClass( "something", "fishg", "rdf", "some",
					"www.this.com", "ZZZzzzzzzzzzz" );
			oldInfo = new String[] { "something special", "fishing" };
			dataLayer.DBUpdate(oldInfo, query);
			psCache = new CachedRowSetImpl();
			psCache = dataLayer.getPass();

			while (psCache.next()) {
				if (psCache.getString(2).equals("fishg")) {
					Assert.assertEquals("something", psCache.getString(1));
					Assert.assertEquals("fishg", psCache.getString(2));
					Assert.assertEquals("rdf", psCache.getString(3));
				}

			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}

	@Test
	public void testDBDelete() {
		fail("Not yet implemented");
		try {
			dataLayer = new DataStorage();
			dataLayer = new DataStorage("blah", "kablah".toCharArray(),
					dataLayer.getPage());

			query = new String[6];
			query[0] = ("something");
			query[1] = ("fishg");
			query[2] = ("rdf");
			query[3] = ("some");
			query[4] = ("www.this.com");
			query[5] = ("ZZZzzzzzzzzzz");
			oldInfo = new String[] { "something special", "fishing" };

			dataLayer.DBDelete(query);
			psCache = new CachedRowSetImpl();
			psCache = dataLayer.getPass();

			while (psCache.next()) {
				Assert.assertFalse(psCache.getString(2).equals("fishg"));

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testGetPage() {
		fail("Not yet implemented");
		try {
			dataLayer = new DataStorage();
		} catch (Exception e) {

			e.printStackTrace();
		}
		Assert.assertEquals("01", dataLayer.getPage());
	}

	@Test
	public void testGetPass() {

		// fail("Not yet implemented");

		try {
			dataLayer = new DataStorage();
			dataLayer = new DataStorage("blah", "kablah".toCharArray(),
					dataLayer.getPage());
			query = new String[] { "something special", "fishing",
					"really bored", "sudfha", "www.blah.com", "ZZZzzzzzzzzzz" };
			dataLayer.DBInsert(query);
			psCache = new CachedRowSetImpl();
			psCache = dataLayer.getPass();

			while (psCache.next()) {
				System.out.println(psCache.getString(1));
				System.out.println(psCache.getString(2));
				System.out.println(psCache.getString(3));
				System.out.println(psCache.getString(4));
				System.out.println(psCache.getString(5));
				System.out.println(psCache.getString(6));
				System.out.println(psCache.getString(7));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
**/
}
