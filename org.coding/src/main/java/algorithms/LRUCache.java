package algorithms;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private DoublyLinkedList pageList;
	private Map<Integer, Node> pageMap;
	private final int cacheSize;

	public LRUCache(int cacheSize) {
		this.cacheSize = cacheSize;
		pageList = new DoublyLinkedList(cacheSize);
		pageMap = new HashMap<Integer, Node>();
	}

	public void accessPage(int pageNumber) {
		Node pageNode = null;
		// 1.if already exists..bring to head
		if (pageMap.containsKey(pageNumber)) {
			pageNode = pageMap.get(pageNumber);
			pageList.movePageToHead(pageNode);
		} else {
			if (pageList.getCurrSize() == pageList.getSize()) {
				pageMap.remove(pageList.getTail().getPageNumber());
			}
			pageNode = pageList.addPageToList(pageNumber);
			pageMap.put(pageNumber, pageNode);

		}
	}

	public void printCacheState() {
		pageList.printList();
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cacheSize = 4;
		LRUCache cache = new LRUCache(cacheSize);
		cache.accessPage(4);
		cache.printCacheState();
		cache.accessPage(2);
		cache.printCacheState();
		cache.accessPage(1);
		cache.printCacheState();
		cache.accessPage(1);
		cache.printCacheState();
		cache.accessPage(4);
		cache.printCacheState();
		cache.accessPage(3);
		cache.printCacheState();
		cache.accessPage(7);
		cache.printCacheState();
		cache.accessPage(8);
		cache.printCacheState();
		cache.accessPage(3);
		cache.printCacheState();

	}

}

// https://www.ideserve.co.in/learn/lru-cache-implementation
class DoublyLinkedList {
	private final int size;
	private int currSize;
	Node head;
	Node tail;

	public DoublyLinkedList(int size) {
		this.size = size;
		currSize = 0;
	}

	public Node getTail() {
		return tail;
	}

	public int getCurrSize() {
		return currSize;
	}

	public void setCurrSize(int currSize) {
		this.currSize = currSize;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}

	public void printList() {
		if (head == null) {
			return;
		}
		Node tmp = head;
		while (tmp != null) {
			System.out.print(tmp.pageNumber + "->");
			tmp = tmp.getNext();
		}
	}

	public Node addPageToList(int pageNumber) {
		Node pageNode = new Node(pageNumber);
		if (head == null) {
			head = pageNode;
			tail = pageNode;
			currSize = 1;
			return pageNode;
		} else if (currSize < size) {
			currSize++;
		} else {
			tail = tail.getPrev();
			tail.setNext(null);
		}
		pageNode.setNext(head);
		head.setPrev(pageNode);
		head = pageNode;
		return pageNode;
	}

	public void movePageToHead(Node pageNode) {
		if (pageNode == null || pageNode == head) {
			return;
		}

		if (pageNode == tail) {
			tail = tail.getPrev();
			tail.setNext(null);
		}

		Node prev = pageNode.getPrev();
		Node next = pageNode.getNext();
		prev.setNext(next);

		if (next != null) {
			next.setPrev(prev);
		}

		pageNode.setPrev(null);
		pageNode.setNext(head);
		head.setPrev(pageNode);
		head = pageNode;
	}

}

class Node {
	int pageNumber;

	Node prev;
	Node next;

	public Node(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public Node getPrev() {
		return prev;
	}

	public Node getNext() {
		return next;
	}

	public void setPageNumber(int pageNumber) {
		pageNumber = pageNumber;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}
