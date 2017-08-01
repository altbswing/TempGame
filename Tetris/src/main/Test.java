package main;

public class Test {
	String str = new String("binbin");
	char[] ch = new char[]{'a','b','c'};
	
	public static void main(String[] args) {
		mn(6);
	}
	
	public static void mn(int n){
		loop:
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print( i * j + " ");
				if(i==j){
					System.out.println();
					continue loop;
				}
			}
		}
	}
	
}
