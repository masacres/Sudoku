package com.corp;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

	    Integer[][] values = {
			    {9, null, 3,  null, 8, null,   null, null, null},
			    {null, 6, null,  1, null, null,   7, 3, null},
			    {null, null, null,  null, null, null,   null, null, 4},

			    {null, null, null,  4, null, null,   null, null, 6},
			    {null, null, 8,  null, 1, null,   5, 4, null},
			    {null, 5, null,  null, null, null,   null, null, 2},

			    {null, 7, null,  3, null, null,   1, 5, null},
			    {null, null, 6,  null, null, 7,   null, null, null},
			    {null, null, null,  null, null, null,   2, null, null},
	    };

	    Matrix matrix = new Matrix(values);

	    boolean sucess = matrix.solve();
	    printMatrix(matrix, false);
	    System.out.println("sucess = " + sucess);

	    //matrix.calculatePossibleValues();
	    //printMatrix(matrix, true);
	    //analyze(matrix.getRows(), "Row");
	    //analyze(matrix.getCols(), "Col");
	    //analyze(matrix.getQuads(), "Quad");
    }

	private static void analyze(Cell[][] matrix, String title) {
		for (int i = 0; i < matrix.length; i++) {
			analyzeCount(matrix[i], title + " " + (i + 1));
		}
		for (int i = 0; i < matrix.length; i++) {
			analyzeDuplets(matrix[i], title + " " + (i + 1));
		}
		for (int i = 0; i < matrix.length; i++) {
			analyzeTriplets(matrix[i], title + " " + (i + 1));
		}
		for (int i = 0; i < matrix.length; i++) {
			analyzeQuadruplets(matrix[i], title + " " + (i + 1));
		}
		for (int i = 0; i < matrix.length; i++) {
			analyzeQuintuplets(matrix[i], title + " " + (i + 1));
		}
	}

	private static void analyzeQuintuplets(Cell[] array, String title) {
		System.out.print(title + ": ");

		List<Cell> list = Arrays.stream(array).collect(Collectors.toList());

		if (list.stream().filter(x -> !x.isFixed()).count() <= 5) {
			System.out.println();
			return;
		}

		for (int a = 1; a <= 9; a++) {
			for (int b = a + 1; b <= 9; b++) {
				for (int c = b + 1; c <= 9; c++) {
					for (int d = c + 1; d <= 9; d++) {
						loop:
						for (int e = d + 1; e <= 9; e++) {
							for (Cell cell : list) {
								if (cell.isFixed()) {
									if (a == cell.getNumber()
											|| b == cell.getNumber()
											|| c == cell.getNumber()
											|| d == cell.getNumber()
											|| e == cell.getNumber()
									) {
										continue loop;
									}
								}
							}

							int a2 = a;
							int b2 = b;
							int c2 = c;
							int d2 = d;
							int e2 = e;
							long count = list.stream()
									.filter(x -> !x.isFixed())
									.filter(x -> x.getValues().contains(a2)
											|| x.getValues().contains(b2)
											|| x.getValues().contains(c2)
											|| x.getValues().contains(d2)
											|| x.getValues().contains(e2)
									)
									.map(x -> list.indexOf(x))
									.distinct()
									.count();
							if (count == 5) {
								System.out.print("(" + a + ", " + b + ", " + c + ", " + d + ", " + e + "), ");
							}
						}
					}
				}
			}
		}

		System.out.println();
	}

	private static void analyzeQuadruplets(Cell[] array, String title) {
		System.out.print(title + ": ");

		List<Cell> list = Arrays.stream(array).collect(Collectors.toList());

		if (list.stream().filter(x -> !x.isFixed()).count() <= 4) {
			System.out.println();
			return;
		}

		for (int a = 1; a <= 9; a++) {
			for (int b = a + 1; b <= 9; b++) {
				for (int c = b + 1; c <= 9; c++) {
					loop:
					for (int d = c + 1; d <= 9; d++) {
						for (Cell cell : list) {
							if (cell.isFixed()) {
								if (a == cell.getNumber()
										|| b == cell.getNumber()
										|| c == cell.getNumber()
										|| d == cell.getNumber()
								) {
									continue loop;
								}
							}
						}

						int a2 = a;
						int b2 = b;
						int c2 = c;
						int d2 = d;
						long count = list.stream()
								.filter(x -> !x.isFixed())
								.filter(x -> x.getValues().contains(a2)
										|| x.getValues().contains(b2)
										|| x.getValues().contains(c2)
										|| x.getValues().contains(d2)
								)
								.map(x -> list.indexOf(x))
								.distinct()
								.count();
						if (count == 4) {
							System.out.print("(" + a + ", " + b + ", " + c + ", " + d + "), ");
						}
					}
				}
			}
		}

		System.out.println();
	}

	private static void analyzeTriplets(Cell[] array, String title) {
		System.out.print(title + ": ");

		List<Cell> list = Arrays.stream(array).collect(Collectors.toList());

		for (int a = 1; a <= 9; a++) {
			for (int b = a + 1; b <= 9; b++) {
				loop:
				for (int c = b + 1; c <= 9; c++) {
					for (Cell cell : list) {
						if (cell.isFixed()) {
							if (a == cell.getNumber()
									|| b == cell.getNumber()
									|| c == cell.getNumber()
							) {
								continue loop;
							}
						}
					}

					int a2 = a;
					int b2 = b;
					int c2 = c;
					long count = list.stream()
							.filter(x -> !x.isFixed())
							.filter(x -> x.getValues().contains(a2)
									|| x.getValues().contains(b2)
									|| x.getValues().contains(c2)
							)
							.map(x -> list.indexOf(x))
							.distinct()
							.count();
					if (count == 3) {
						System.out.print("(" + a + ", " + b + ", " + c + "), ");
					}
				}
			}
		}

		System.out.println();
	}

	private static void analyzeDuplets(Cell[] array, String title) {
		System.out.print(title + ": ");

		List<Cell> list = Arrays.stream(array).collect(Collectors.toList());

		for (int a = 1; a <= 9; a++) {
			loop:
			for (int b = a + 1; b <= 9; b++) {
				for (Cell cell : list) {
					if (cell.isFixed()) {
						if (a == cell.getNumber()
								|| b == cell.getNumber()
						) {
							continue loop;
						}
					}
				}

				int a2 = a;
				int b2 = b;
				long count = list.stream()
						.filter(x -> !x.isFixed())
						.filter(x -> x.getValues().contains(a2)
								|| x.getValues().contains(b2)
						)
						.map(x -> list.indexOf(x))
						.distinct()
						.count();
				if (count == 2) {
					System.out.print("(" + a + ", " + b + "), ");
				}
			}
		}

		System.out.println();
	}

	/*private static void analyzeQuadruplets(Cell[] list, String title) {
		System.out.print(title + ": ");

		for (int a = 1; a <= 9; a++) {
			for (int b = a + 1; b <= 9; b++) {
				for (int c = b + 1; c <= 9; c++) {
					loop:
					for (int d = c + 1; d <= 9; d++) {
						int count = 0;
						for (Cell cell : list) {
							if (cell.isFixed()) {
								if (a == cell.getNumber()
										|| b == cell.getNumber()
										|| c == cell.getNumber()
										|| d == cell.getNumber()
								) {
									continue loop;
								}
							}
						}

						for (Cell cell : list) {
							if (cell.isFixed()) continue;

							List<Integer> values = cell.getValues();

							int a2 = a;
							int b2 = b;
							int c2 = c;
							int d2 = d;
							if ((values.size() <= 4)
									&& values.stream().noneMatch(x -> x != a2 && x != b2 && x != c2 && x != d2)
							) {
								count++;
							}
						}

						if (count >= 4) {
							System.out.print("(" + a + ", " + b + ", " + c + ", " + d + ")->" + count + ", ");
						}
					}
				}
			}
		}

		System.out.println();
	}*/

	/*private static void analyzeTriplets(Cell[] list, String title) {
		System.out.print(title + ": ");

		for (int a = 1; a <= 9; a++) {
			for (int b = a + 1; b <= 9; b++) {
				loop:
				for (int c = b + 1; c <= 9; c++) {
					int count = 0;
					for (Cell cell : list) {
						if (cell.isFixed()) {
							if (a == cell.getNumber()
									|| b == cell.getNumber()
									|| c == cell.getNumber()
							) {
								continue loop;
							}
						}
					}

					for (Cell cell : list) {
						if (cell.isFixed()) continue;

						List<Integer> values = cell.getValues();

						int a2 = a;
						int b2 = b;
						int c2 = c;
						if ((values.size() <= 3)
								&& values.stream().noneMatch(x -> x != a2 && x != b2 && x != c2)
								) {
							count++;
						}
					}

					if (count >= 3) {
						System.out.print("(" + a + ", " + b + ", " + c + ")->" + count + ", ");
					}
				}
			}
		}

		System.out.println();
	}*/

	/*private static void analyzeDuplets(Cell[] list, String title) {
		System.out.print(title + ": ");

		for (int a = 1; a <= 9; a++) {
			loop:
			for (int b = a + 1; b <= 9; b++) {
				int count = 0;
				for (Cell cell : list) {
					if (cell.isFixed()) {
						if (a == cell.getNumber()
								|| b == cell.getNumber()
						) {
							continue loop;
						}
					}
				}

				for (Cell cell : list) {
					if (cell.isFixed()) continue;

					List<Integer> values = cell.getValues();

					int a2 = a;
					int b2 = b;
					if ((values.size() <= 2)
							&& values.stream().noneMatch(x -> x != a2 && x != b2)
					) {
						count++;
					}
				}

				if (count >= 2) {
					System.out.print("(" + a + ", " + b + ")->" + count + ", ");
				}
			}
		}

		System.out.println();
	}*/

	private static void analyzeCount(Cell[] list, String title) {
		System.out.print(title + ": ");

		Map<Integer, Integer> counts = new TreeMap<>();
		for (int j = 1; j <= 9; j++) {
			counts.put(j, 0);
		}

		for (Cell cell : list) {
			if (cell.isFixed()) continue;
			for (Integer value : cell.getValues()) {
				counts.put(value, counts.get(value) + 1);
			}
		}

		for (Integer number : counts.keySet()) {
			Integer count = counts.get(number);
			if (count == 0 || count > 1) continue;
			System.out.print(number + "->" + count + ", ");
		}
		System.out.println();
	}

	private static void printMatrix(Matrix matrix, boolean all) {
		Cell[][] rows = matrix.getRows();
		for (int i = 0; i < rows.length; i++) {
			Cell[] row = rows[i];
			for (int j = 0; j < row.length; j++) {
				Cell cell = row[j];
				if (cell.isFixed()) {
					System.out.print(" " + cell.getNumber() + " ");
				} else if (all) {
					System.out.print(cell.getValues());
				} else {
					System.out.print("(" + cell.getSelectedValue() + ")");
				}

				if (((j + 1) % 3) == 0) {
					System.out.print(",\t");
				} else {
					System.out.print(",");
				}
			}
			System.out.println();
			if (((i + 1) % 3) == 0) {
				System.out.println();
			}
		}
	}
}
