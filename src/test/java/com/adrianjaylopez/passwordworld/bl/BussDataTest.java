package com.adrianjaylopez.passwordworld.bl;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class BussDataTest {
	BussData snore;

	@Test
	public void testLogin() {
		fail("Not yet implemented");
		try {
			snore = new BussData("blah", "kablah".toCharArray());
		} catch (Exception e) {

			if (((SQLException) e).getSQLState().equals("08006")) {
				// do nothing
			} else {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
		try {
			snore = new BussData("blah", "kablah".toCharArray());

			snore.update("blah", "I feel like", "crapola", "right now",
					"www.feelsick.com", "i need to go home to my family");
		} catch (Exception e) {
			if (((SQLException) e).getSQLState().equals("08006")) {
				// do nothing
			} else {
				e.printStackTrace();
			}

		}

		String[] blah = new String[6];
		// iterates through jw collection
		blah = snore.search("blah", "I feel like");

		Assert.assertEquals("blah", blah[0]);
		Assert.assertEquals("I feel like", blah[1]);
		Assert.assertEquals("crapola", blah[2]);
	}

	// run only after testupdate
	@Test
	public void testUpdate2() {
		fail("Not yet implemented");
		try {
			snore = new BussData("blah", "kablah".toCharArray());

			snore.update("blah", "I feel like", "blah", "I feeqwl",
					"crapasdfola", "right er ewnow",
					"www.fewqerqwreelsick.com", "i needqwrfamily");
		} catch (Exception e) {

			e.printStackTrace();

		}

		// iterates through jw collection
		String[] blah = new String[6];
		blah = snore.search("blah", "I feeqwl");

		Assert.assertEquals("blah", blah[0]);
		Assert.assertEquals("I feeqwl", blah[1]);
		Assert.assertEquals("crapasdfola", blah[2]);

	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
		try {
			snore = new BussData("blah", "kablah".toCharArray());

			snore.delete("blah", "I feel like");

		} catch (Exception e) {

			e.printStackTrace();

		}
		String[] blah = new String[6];
		blah = snore.search("blah", "I feel like");
		Assert.assertNull("", blah[0]);
		Assert.assertNull("", blah[1]);
		Assert.assertNull("", blah[2]);
	}

	@Test
	public void testSearch() {
		fail("Not yet implemented");

		try {
			snore = new BussData("blah", "kablah".toCharArray());

			snore.update("blah", "I feel like", "crapola", "right now",
					"www.feelsick.com", "i need to go home to my family");
		} catch (Exception e) {

			if (((SQLException) e).getSQLState().equals("08006")) {
				// do nothing
			} else {
				e.printStackTrace();
			}
		}
		String[] blah = new String[6];
		blah = snore.search("blah", "I feel like");
		Assert.assertEquals("blah", blah[0]);
		Assert.assertEquals("I feel like", blah[1]);
		Assert.assertEquals("crapola", blah[2]);
		System.out.println(blah[5]);

	}

}
