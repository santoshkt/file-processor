package biz.neustar.fp;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataTest {
	
	@Test
	public void testData(){
		Data d = new Data("PERSON", "Santosh");
		assertEquals("PERSON", d.getCategoryTypeStr());
		assertEquals("Santosh", d.getSubCategory());
	}

	@Test
	public void testDataEquals() {

		Data d1 = new Data("PERSON", "Santosh");
		Data d2 = new Data("PERSON", "Santosh");
		assertEquals(d1, d2);

		Data d3 = new Data("PERSON", "Tadikonda");
		assertNotEquals(d1, d3);

		Data d4 = new Data("ANIMAL", "Santosh");
		assertNotEquals(d1, d4);

		Data d5 = new Data("ANIMAL", "Tadikonda");
		assertNotEquals(d4, d5);

	}
	
	@Test
	public void testToString(){
		Data d = new Data("PERSON", "Santosh");
		assertEquals("PERSON Santosh", d.toString());
	}

	@Test
	public void testDataCategoryType() {
		boolean test = false;
		try {
			new Data("Animal", "Dog");
		} catch (IllegalArgumentException iae) {
			test = true;
		}
		assertTrue(test);
	}

}
