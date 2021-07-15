package linkedList;

public class Node {
	int num;
	String name;
	Node link;
	
	public void setNode(Node link) {
		this.link = link;
	}
	
	public void printNode() {
		System.out.println(num + ": " + name);
	}
}
