package linkedList;

import java.util.Scanner;

public class LinkedList {
	Node head = null;
	Node tail = null;
	static Scanner in = new Scanner(System.in);
	
	void push(int num, String name) {
		Node temp = new Node();
		temp.num = num;
		temp.name = name;
		if(head == null) {
			head = temp;
			tail = temp;
		}
		else {
			tail.link = temp;
			tail = temp;
		}
		temp = null;
	}
	
	public void pop() {
		if(head != null) {
			Node temp = head;
			head = head.link;
			temp.printNode();
		}
		else {
			System.out.println("Error nothing in list");
		}
	}
	
	public void printList() {
		Node temp = head;
		while(temp != null) {
			temp.printNode();
			temp = temp.link;
		}
	}
	
	public static void main(String[] args) {
		
		String input = "";
		int num = 1;
		LinkedList list = new LinkedList();
		boolean run = true;
		
		while(run) {
			System.out.print("Enter action: ");
			input = in.nextLine();
			String[] parse = input.split(" ");
			if(parse[0] == "push") {
				list.push(num,  parse[1]);
			}
			else if(parse[0] == "print") {
				list.printList();
			}
			else if(parse[0] == "quit")
				run = false;
			for(String s: parse) {
				System.out.println(s);
			}
			list.printList();
		}
	}
}
