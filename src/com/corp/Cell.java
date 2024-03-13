package com.corp;

import java.util.ArrayList;
import java.util.List;

public class Cell {

	private final List<Integer> _values = new ArrayList<>();

	private final Integer _number;
	private final boolean _fixed;

	private final int _row;
	private final int _col;

	private Integer _index;

    public Cell(Integer number, int row, int col) {
        _number = number;
        _fixed = (number != null);
        _row = row;
        _col = col;
    }

    public Integer getSelectedValue() {
	    if (_number != null) {
		    return _number;
	    }
	    if ((!_values.isEmpty()) && (_index != null)) {
		    return _values.get(_index);
	    }
	    return null;
    }

	public Integer getNumber() {
		return _number;
	}

	public boolean isFixed() {
		return _fixed;
	}

	public Cell setIndex(Integer index) {
		_index = index;
		return this;
	}

	public List<Integer> getValues() {
		return _values;
	}

	public int getRow() {
		return _row;
	}

	public int getCol() {
		return _col;
	}
}
