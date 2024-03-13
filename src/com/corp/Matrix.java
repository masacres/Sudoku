package com.corp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Matrix {

	public static final int MAX = 9;

	private final Cell[][] _rows = new Cell[MAX][MAX];
	private final Cell[][] _cols = new Cell[MAX][MAX];
	private final Cell[][] _quads = new Cell[MAX][MAX];

	private final List<Cell> _unsolved = new ArrayList<>();

	public Matrix(Integer[][] values) {
		for (int r = 0; r < MAX; r++) {
			for (int c = 0; c < MAX; c++) {
				Cell cell = new Cell(values[r][c], r, c);

				_rows[r][c] = cell;
				_cols[c][r] = cell;
				_quads[getQuadFirstIndex(r, c)][getQuadSecondIndex(r, c)] = cell;

				if (!cell.isFixed()) {
					_unsolved.add(cell);
				}
			}
		}
	}

	public void calculatePossibleValues() {
		for (Cell cell : _unsolved) {
			calculatePossibleValues(cell);
		}
	}

	public boolean solve() {
		if (_unsolved.isEmpty()) return false;

		return solveAux(0);
	}

	private boolean solveAux(int index) {
		if (index >= _unsolved.size()) {
			return isValid();
		}

		Cell cell = _unsolved.get(index);
		calculatePossibleValues(cell);

		for (int i = 0; i < cell.getValues().size(); i++) {
			cell.setIndex(i);

			boolean success = solveAux(index + 1);
			if (success) return true;
		}

		cell.setIndex(null);

		return false;
	}

	public void calculatePossibleValues(Cell cell) {
		if (cell.isFixed()) return;

		List<Integer> values = cell.getValues();
		values.clear();
		for (int i = 1; i <= 9; i++) {
			values.add(i);
		}

		removeTaken(_rows[cell.getRow()], cell);
		removeTaken(_cols[cell.getCol()], cell);
		removeTaken(_quads[getQuadFirstIndex(cell.getRow(), cell.getCol())], cell);
	}

	private void removeTaken(Cell[] list, Cell cell) {
		List<Integer> taken = new ArrayList<>();
		for (int i = 0; i < MAX; i++) {
			Cell other = list[i];
			if (other == cell) continue;

			Integer value = other.getSelectedValue();
			if (value != null) {
				taken.add(value);
			}
		}
		cell.getValues().removeAll(taken);
	}

	private int getQuadFirstIndex(int r, int c) {
		return (r / 3) * 3 + c / 3;
	}

	private int getQuadSecondIndex(int r, int c) {
		return (r % 3) * 3 + c % 3;
	}

	public boolean isValid() {
		for (int i = 0; i < MAX; i++) {
			Set<Integer> rowSet = new HashSet<>();
			Set<Integer> colSet = new HashSet<>();
			Set<Integer> quadSet = new HashSet<>();
			for (int j = 0; j < MAX; j++) {
				if ((_rows[i][j].getSelectedValue() == null)
						|| (_cols[i][j].getSelectedValue() == null)
						|| (_quads[i][j].getSelectedValue() == null)) {
					return false;
				}
				if (rowSet.contains(_rows[i][j].getSelectedValue())
						|| colSet.contains(_cols[i][j].getSelectedValue())
						|| quadSet.contains(_quads[i][j].getSelectedValue())) {
					return false;
				}
				rowSet.add(_rows[i][j].getSelectedValue());
				colSet.add(_cols[i][j].getSelectedValue());
				quadSet.add(_quads[i][j].getSelectedValue());
			}
		}
		return true;
	}

	public Cell[][] getRows() {
		return _rows;
	}

	public Cell[][] getCols() {
		return _cols;
	}

	public Cell[][] getQuads() {
		return _quads;
	}
}
