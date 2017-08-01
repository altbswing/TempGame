package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Í¼Æ¬¼ô²ÃÆ÷
 * */
public class ImageClipper {
	
	public static List<ImageIcon> clipImageIconToList(
			String path, int w, int h) throws IOException {
		BufferedImage imgBuf = ImageIO.read(new File(path));
		int maxX = imgBuf.getWidth() / w;
		int maxY = imgBuf.getHeight() / h;
		List<ImageIcon> imgList = new ArrayList<ImageIcon>();
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				imgList.add(new ImageIcon(imgBuf.getSubimage(
						x * w, y * h, w, h)));
			}
		}
		return imgList;
	}
	
	public static List<Image> clipImageToList(
			String path, int w, int h) throws IOException {
		BufferedImage imgBuf = ImageIO.read(new File(path));
		int maxX = imgBuf.getWidth() / w;
		int maxY = imgBuf.getHeight() / h;
		List<Image> imgList = new ArrayList<Image>();
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				imgList.add(new ImageIcon(imgBuf.getSubimage(
						x * w, y * h, w, h)).getImage());
			}
		}
		return imgList;
	}
	
	public static Image[][] clipImageToArray(
			String path, int w, int h) throws IOException {
		BufferedImage imgBuf = ImageIO.read(new File(path));
		int maxX = imgBuf.getWidth() / w;
		int maxY = imgBuf.getHeight() / h;
		Image[][] imgArray = new Image[maxX][maxY];
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				imgArray[x][y] = new ImageIcon(imgBuf.getSubimage(
						x * w, y * h, w, h)).getImage();
			}
		}
		return imgArray;
	}
	
}
