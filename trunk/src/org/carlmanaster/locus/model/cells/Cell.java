package org.carlmanaster.locus.model.cells;

public class Cell {

	private static final int ALPHABET_SIZE = 26;

	private static int digits(String string) {
		StringBuffer sb = new StringBuffer();
		for (char c : string.toCharArray()) {
			if (!Character.isDigit(c))
				continue;
			sb.append(c);
		}
		return Integer.parseInt(sb.toString());
	}

	private static String letters(String string) {
		StringBuffer sb = new StringBuffer();
		for (char c : string.toCharArray()) {
			if (Character.isDigit(c))
				break;
			sb.append(c);
		}
		return sb.toString();
	}

	private static int toColumnIndex(String letters) {
		switch(letters.length()) {
		case 1:
			return letters.charAt(0) - 'A';
		case 2:
			int index = 0;
			index += letters.charAt(0) - 'A' + 1;
			index *= ALPHABET_SIZE;
			index += letters.charAt(1) - 'A';
			return index;
				}
		throw new IllegalArgumentException("invalid column specification: " + letters);
	}

	private final int row;

	private final int column;

	public Cell(int row, int column)  throws IllegalArgumentException {
		this.row = row;
		this.column = column;
		if (column < 0 || column > 255)
			throw new IllegalArgumentException("column out of range: " + column);
		if (row < 0 || row > 65535)
			throw new IllegalArgumentException("row out of range: " + row);
	}

	public Cell(String name) throws IllegalArgumentException {
		this(digits(name) - 1, toColumnIndex(letters(scrubbed(name.toUpperCase()))));
	}

	private static String scrubbed(String string) {
		return string.replaceAll("[%$]", "");
	}

	private String columnString() {
		if (column >= ALPHABET_SIZE)
			return toChar(column / ALPHABET_SIZE - 1)
					+ toChar(column % ALPHABET_SIZE);
		return toChar(column);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Cell other = (Cell) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	private String rowString() {
		return String.format("%s", 1 + row);
	}

	private String toChar(int n) {
		Character c = (char) ('A' + n);
		return c.toString();
	}

	public String toString() {
		return columnString() + rowString();
	}
}
