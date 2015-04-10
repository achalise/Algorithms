package algo.week3;

import java.awt.Color;

import algs4.Picture;

public class SeamCarver {
	private Picture picture;

	private static final int BORDER_PIXEL_ENERGY = 195075;
    
	public SeamCarver(Picture picture) { // create a seam carver object based on
											// the given picture
		this.picture = picture;
	}

	public Picture picture() { // current picture
		return this.picture;
	}

	public int width() { // width of current picture
		return picture.width();
	}

	public int height() { // height of current picture
		return picture.height();
	}

	public double energy(int x, int y) { // energy of pixel at column x and row y
		int deltaX = deltaX(x,y);
		int deltaY = deltaY(x,y); 
		int energy = deltaX + deltaY;
		return energy;
	}

	private int deltaX(int x, int y) {
		Color color2 = picture.get(x + 1,y);
		Color color1 = picture.get(x - 1,y);
		int delRX = color2.getRed() - color1.getRed();
		int delGX = color2.getGreen() - color1.getGreen();
		int delBX = color2.getBlue() - color1.getBlue();
		int delX = delRX * delRX + delGX * delGX + delBX * delBX; 
		return delX;
	}

	private int deltaY(int x, int y) {
		Color color2 = picture.get(x, y + 1);
		Color color1 = picture.get(x, y - 1);
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
		Color color = seamCarver.picture().get(4, 4);
		//color.get
		System.out.println(color);
	}

	public int[] findHorizontalSeam() {
		return null;
	}

	public int[] findVerticalSeam() {
		return null;
	}

	public void removeHorizontalSeam(int[] horizontalSeam) {
		// TODO Auto-generated method stub
		
	}

	public void removeVerticalSeam(int[] verticalSeam) {
		// TODO Auto-generated method stub
		
	}

}
