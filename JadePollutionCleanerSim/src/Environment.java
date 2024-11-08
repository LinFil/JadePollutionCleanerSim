// Environment.java
public class Environment {
    private int[][] grid;
    private int gridSize;

    public Environment(int size) {
        gridSize = size;
        grid = new int[size][size];
    }

    public void markAsPolluted(int x, int y) {
        grid[x][y] = 1;
        System.out.println("Cell [" + x + "," + y + "] marked as polluted.");
        printGrid();
    }

    public void markAsClean(int x, int y) {
        grid[x][y] = 0;
        System.out.println("Cell [" + x + "," + y + "] marked as clean.");
        printGrid(); 
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getCellCleanliness(int x, int y) {
        return grid[x][y];
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}

