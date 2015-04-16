package algo.week3;

import java.awt.Color;
import java.util.Random;

import algs4.Picture;

public class SeamCarver {
	private Color[][] image;
	private double[][] energyMatrix;	
	private boolean transposed = false;

	private static final int BORDER_PIXEL_ENERGY = 195075;
    
	public SeamCarver(Picture picture) { // create a seam carver object based on
										 // the given picture
		energyMatrix = new double[picture.height()][picture.width()];
		image = new Color[picture.height()][picture.width()];
		populateImage(picture);
		populateEnergy();
	}

	private void populateImage(Picture picture) {
		for (int h = 0; h < height(); h++) {
			for (int w = 0; w < width(); w++) {
				image[h][w] = picture.get(w, h);
			}
		}		
	}
	
	private void populateEnergy() {
		for (int h = 0; h < height(); h++) {
			for (int w = 0; w < width(); w++) {
				energyMatrix[h][w] = energy(w,  h);
			}
		}
	
	}

	public void printEnergy() {
        for (int j = 0; j < height(); j++)
        {
            for (int i = 0; i < width(); i++)
                System.out.printf("%9.0f ", energyMatrix[j][i]);

            System.out.println();
        }
	}
	
	public Picture picture() { // current picture
		Picture picture = new Picture(width(), height());
		for(int row = 0; row < height(); row++) {
			for (int col = 0; col < width(); col++) {
				picture.set(col, row, image[row][col]);
			}
		}
		return picture;
	}

	public int width() { // width of current picture
		return image[0].length;
	}

	public int height() { // height of current picture
		return image.length;
	}

	public double energy(int x, int y) { // energy of pixel at column x and row y
		if (x == 0 || x == (width() - 1)) {
			return BORDER_PIXEL_ENERGY;
		} else if (y == 0 || y == height() - 1) {
			return BORDER_PIXEL_ENERGY;
		}
		
		int deltaX = deltaX(x,y);
		int deltaY = deltaY(x,y); 
		int energy = deltaX + deltaY;
		return energy;
	}

	private int deltaX(int x, int y) {
		Color color2 = image[y][x + 1];
		Color color1 = image[y][x - 1];
		int delRX = color2.getRed() - color1.getRed();
		int delGX = color2.getGreen() - color1.getGreen();
		int delBX = color2.getBlue() - color1.getBlue();
		int delX = delRX * delRX + delGX * delGX + delBX * delBX; 
		return delX;
	}

	private int deltaY(int x, int y) {
		Color color2 = image[y+1][x];
		Color color1 = image[y-1][x];
		int delRY = color2.getRed() - color1.getRed();
		int delGY = color2.getGreen() - color1.getGreen();
		int delBY = color2.getBlue() - color1.getBlue();
		int delY = delRY * delRY + delGY * delGY + delBY * delBY; 
		return delY;
	}
	
	public static void main(String[] args) {
		String imageFileName = args[0];
		Picture picture = new Picture(imageFileName);
		SeamCarver seamCarver = new SeamCarver(picture);
		seamCarver.printEnergy();
		picture.show();
	}

	public int[] findHorizontalSeam() {
		transpose();
		int[] seam = findVerticalSeam();
		transpose();
		return seam;
		
	}

	private void transpose() {
		energyMatrix = transpose(energyMatrix);
		image = transpose(image);
	}

	public int[] findVerticalSeam() {
		double[] distTo = new double[height()];
		int[] pathTo = new int[height()];

		int startingVertex = 0;
		
		//Initialise initial values before traversal
		distTo[0] = 0;
		
		for (int row = 1; row < height(); row++) {
			distTo[row] = Double.MAX_VALUE;
		}
		double minEnergy = Double.MAX_VALUE;
		for (int col = 1; col < width(); col++) {
			if (energyMatrix[1][col] < minEnergy) {
				startingVertex = col;
				minEnergy = energyMatrix[1][col];
			}
		}
		
		pathTo[0] = startingVertex;
		
		//Now traverse from the source vertex to the bottom row
		//accumulating shortest path
		relaxEdges(distTo, pathTo);			
		return pathTo;
	}

	private void relaxEdges(double[] distTo, int[] pathTo) {
		int col;
		for (int row = 1; row < height(); row++) {
			col = pathTo[row - 1];
			if (col - 1 > 0) {
				if (distTo[row] > distTo[row - 1] + energyMatrix[row][col -1]) {
					distTo[row] = distTo[row - 1] + energyMatrix[row][col -1];
					pathTo[row] = col - 1;
				}
			}
			if (distTo[row] > distTo[row - 1] + energyMatrix[row][col]) {
				distTo[row] = distTo[row - 1] + energyMatrix[row][col];
				pathTo[row] = col;
			}
			if (col + 1 < width()) {
				if (distTo[row] > distTo[row - 1] + energyMatrix[row][col + 1]) {
					distTo[row] = distTo[row - 1] + energyMatrix[row][col + 1];
					pathTo[row] = col + 1;
				}				
			}
		}
	}

	public void removeHorizontalSeam(int[] horizontalSeam) {
		transpose();
		removeVerticalSeam(horizontalSeam);
		transpose();
	}

	public void removeVerticalSeam(int[] verticalSeam) {
		for (int row = 0; row < height(); row++) {
			int col = verticalSeam[row];
			for (; col < width() - 2; col++) {
				image[row][col] = image[row][col + 1];
			}
		}
		resizeArray();		
	}

	private void resizeArray() {
		double[][] energiesNewArray = new double[energyMatrix.length - 1][energyMatrix[0].length - 1];
		Color[][]  newImage = new Color[energyMatrix.length - 1][energyMatrix[0].length - 1];
		for (int row = 0; row < height() - 1; row++) {
			 System.arraycopy(image[row], 0, newImage[row], 0, width() - 1);
		}
		energyMatrix = energiesNewArray;
		image = newImage;
		populateEnergy();
	}
	
	private Color[][] transpose(Color[][] input) {
		Color[][] transposed = new Color[input[0].length][input.length];
		for(int col = 0; col < width(); col++) {
			for(int row = 0; row < height(); row++) {
				transposed[col][row] = input[row][col];
			}
		}
		return transposed;
	}

	private double[][] transpose(double[][] input) {
		double[][] transposed = new double[input[0].length][input.length];
		for(int col = 0; col < width(); col++) {
			for(int row = 0; row < height(); row++) {
				transposed[col][row] = input[row][col];
			}
		}
		return transposed;
	}
}
