package entity;

public class Node {
	private int x;
	private int y;
	
	public Node(){}
	public Node(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Node){
			Node n = (Node)obj;
			return n.x == x && n.y == y;
		}
		return false;
	}
	
}
