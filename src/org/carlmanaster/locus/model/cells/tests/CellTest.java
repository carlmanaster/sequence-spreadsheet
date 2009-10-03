package org.carlmanaster.locus.model.cells.tests;

import org.carlmanaster.locus.model.cells.Cell;

import junit.framework.TestCase;

public class CellTest extends TestCase {
	public void testToString() throws Exception {
		assertEquals("A5", new Cell(4, 0).toString());
		assertEquals("B1", new Cell(0, 1).toString());
		assertEquals("Z1", new Cell(0, 25).toString());
		assertEquals("AA1", new Cell(0, 26).toString());
		assertEquals("AB1", new Cell(0, 27).toString());
		assertEquals("BA1", new Cell(0, 52).toString());
	}
	
	public void testStringConstructor() throws Exception {
		Cell a5 = new Cell("A5");
		assertEquals(4, a5.getRow());
		assertEquals(0, a5.getColumn());
		String s = "BZ2257";
		assertEquals(s, new Cell(s).toString());
		s = "IV65536";
		assertEquals(s, new Cell(s).toString());
	}
	
	public void testEquals() throws Exception {
		assertEquals(new Cell("AB1"), new Cell(0,27));
	}
	
	public void testCruft() throws Exception {
		assertEquals(new Cell("AB7"), new Cell("$AB$7"));
		assertEquals(new Cell("AB7"), new Cell("%AB%7"));
	}
	public void testLowercase() throws Exception {
		assertEquals(new Cell("AB7"), new Cell("ab7"));
	}
	public void testBadStrings() throws Exception {
		cantMakeCell("");
		cantMakeCell("a");
		cantMakeCell("5");
		cantMakeCell("aaa5");
		// IV65536 is the last cell
		cantMakeCell("a0");
		cantMakeCell("a65537");
		cantMakeCell("iw1");
	}
	

	private void cantMakeCell(String string) throws Exception{
		try {
			new Cell(string);
			fail("was able to create a cell from: " + string);
		} catch (IllegalArgumentException e) {
		}
	}
}
