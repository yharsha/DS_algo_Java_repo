package iqs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hres {
	boolean[][] arr;
	int rooms,days;
	ArrayList<Count> list;

	Hres(int size, int days) {
		this.rooms = size;
		this.days = days;
		arr = new boolean[rooms][days];
		list=new ArrayList<Count>();
		for(int i=0;i<rooms;i++)
		{
			list.add(new Count(i,0));
		}
	}

	// continous block single room

	boolean checkBooking(int start, int end) {
		if (start < 0 | end < 0 | start > 365 | end > 365)
			return false;
		Collections.sort(list,Count.roomComparator);
		for(Count obj:list)
		{
//			System.out.println(obj.toString());
			int flag = 1;
			for (int j = start; j <= end; j++) {
				if (arr[obj.room][j]) {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				for (int j = start; j <= end; j++) {
					arr[obj.room][j] = true;
					obj.value++;
				}
				return true;
			}
		}
		
		
//		for (int i = 0; i < rooms; i++) {
//			int flag = 1;
//			for (int j = start; j <= end; j++) {
//				if (arr[i][j]) {
//					flag = 0;
//					break;
//				}
//			}
//			if (flag == 1) {
//				for (int j = start; j <= end; j++) {
//					arr[i][j] = true;
//				}
//				return true;
//			}
//		}

		return false;
	}

	void printRes(boolean[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("Room" + (i + 1) + "-> ");
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] == true ? 1 : 0);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hres hs = new Hres(2, 17);
	
		//first case
//		 System.out.println(hs.checkBooking(-4,2));
//		 System.out.println(hs.checkBooking(200,400));
		
		//secondcase
//		System.out.println(hs.checkBooking(0,5));
//		System.out.println(hs.checkBooking(7,13));
//		System.out.println(hs.checkBooking(3,9));
//		System.out.println(hs.checkBooking(5,7));
//		System.out.println(hs.checkBooking(6,6));
//		System.out.println(hs.checkBooking(0,4));
		
		//thirdcase
//		System.out.println(hs.checkBooking(1,3));
//		System.out.println(hs.checkBooking(2,5));
//		System.out.println(hs.checkBooking(1,9));
//		System.out.println(hs.checkBooking(0,15));
		
		//fourthcase
//		System.out.println(hs.checkBooking(1,3));
//		System.out.println(hs.checkBooking(0,15));
//		System.out.println(hs.checkBooking(1,9));
//		System.out.println(hs.checkBooking(2,5));
//		System.out.println(hs.checkBooking(4,9));
		
		//fifthcase...failing
		System.out.println("hs.checkBooking(1,3): "+hs.checkBooking(1,3));
		System.out.println("hs.checkBooking(0,4): "+hs.checkBooking(0,4));
		System.out.println("hs.checkBooking(2,3): "+hs.checkBooking(2,3));
		System.out.println("hs.checkBooking(5,5): "+hs.checkBooking(5,5));
		System.out.println("hs.checkBooking(4,10): "+hs.checkBooking(4,10));
		System.out.println("hs.checkBooking(10,10): "+hs.checkBooking(10,10));
		System.out.println("hs.checkBooking(6,7): "+hs.checkBooking(6,7));
		System.out.println("hs.checkBooking(8,10): "+hs.checkBooking(8,10));
		System.out.println("hs.checkBooking(8,9): "+hs.checkBooking(8,9));
		
	
		hs.printRes(hs.arr);
		
		
		// System.out.println(hs.checkBooking(1,3));
		// System.out.println(hs.checkBooking(0,4));
		// System.out.println(hs.checkBooking(2,3));
		// System.out.println(hs.checkBooking(5,5));
		// System.out.println(hs.checkBooking(4,10));
		// System.out.println(hs.checkBooking(10,10));
		// System.out.println(hs.checkBooking(6,7));
		// System.out.println(hs.checkBooking(8,10));
		// System.out.println(hs.checkBooking(8,9));

//		ArrayList<Slot> list = new ArrayList<Slot>();

		// first case
		// list.add(new Slot(-4,2));
		// list.add(new Slot(200,400));

		// second case
		// list.add(new Slot(0,5));
		// list.add(new Slot(7,13));
		// list.add(new Slot(3,9));
		// list.add(new Slot(5,7));
		// list.add(new Slot(6,6));
		// list.add(new Slot(0,4));

		// third case-failing
		// list.add(new Slot(1,3));
		// list.add(new Slot(2,5));
		// list.add(new Slot(1,9));
		// list.add(new Slot(0,15));

		// fourth case
		// list.add(new Slot(1,3));
		// list.add(new Slot(0,15));
		// list.add(new Slot(1,9));
		// list.add(new Slot(2,5));
		// list.add(new Slot(4,9));

		// fifth case
		// list.add(new Slot(1,3));
		// list.add(new Slot(0,4));
		// list.add(new Slot(2,3));
		// list.add(new Slot(5,5));
		// list.add(new Slot(4,10));
		// list.add(new Slot(10,10));
		// list.add(new Slot(6,7));
		// list.add(new Slot(8,10));
		// list.add(new Slot(8,9));
//		Hres hs = new Hres(3, 17);
//		Collections.sort(list, Slot.slotComparator);
//		for (Slot st : list) {
//			System.out.println(st.toString() + ":" + hs.checkBooking(st.start, st.end));
//			hs.printRes(hs.arr);
//		}

		ArrayList<Slot1> list1 = new ArrayList<Slot1>();
		// first case
//		list1.add(new Slot1(-4, 2));
//		list1.add(new Slot1(200, 400));

		// second case--failing
//		 list1.add(new Slot1(0,5));
//		 list1.add(new Slot1(7,13));
//		 list1.add(new Slot1(3,9));
//		 list1.add(new Slot1(5,7));
//		 list1.add(new Slot1(6,6));
//		 list1.add(new Slot1(0,4));

		// third case
//		 list1.add(new Slot1(1,3));
//		 list1.add(new Slot1(2,5));
//		 list1.add(new Slot1(1,9));
//		 list1.add(new Slot1(0,15));

		// fourth case..failing
//		 list1.add(new Slot1(1,3));
//		 list1.add(new Slot1(0,15));
//		 list1.add(new Slot1(1,9));
//		 list1.add(new Slot1(2,5));
//		 list1.add(new Slot1(4,9));

		// fifth case..failing
//		 list1.add(new Slot1(1,3));
//		 list1.add(new Slot1(0,4));
//		 list1.add(new Slot1(2,3));
//		 list1.add(new Slot1(5,5));
//		 list1.add(new Slot1(4,10));
//		 list1.add(new Slot1(10,10));
//		 list1.add(new Slot1(6,7));
//		 list1.add(new Slot1(8,10));
//		 list1.add(new Slot1(8,9));
//			Hres hs = new Hres(2, 17);
//		Collections.sort(list1, Slot1.slot1Comparator);
//		for (Slot1 st : list1) {
//			System.out.println(st.toString() + ":" + hs.checkBooking(st.start, st.end));
//			hs.printRes(hs.arr);
//		}

		// hs.printRes(hs.arr);

	}

}
