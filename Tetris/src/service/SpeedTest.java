package service;

public class SpeedTest {
	public static void main(String[] args) {
		for (int level = 1; level < 200; level++) {
			System.out.println(level+"����\tsleepʱ�䣺 "+5000/(level+4)+"MS");
		}
	}
}
