package iqs;

import java.util.Comparator;

public class Slot1 {

	
	int start;
	int end;

	public Slot1(int start, int end) {
		super();
		this.end = end;
		this.start = start;
	}

	public Slot1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getend() {
		return end;
	}

	public void setend(int end) {
		this.end = end;
	}

	public int getstart() {
		return start;
	}

	public void setstart(int start) {
		this.start = start;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + start;
		result = prime * result + end;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slot other = (Slot) obj;

		if (end != other.end)
			return false;
		else {
			if (start != other.start)
				return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Slot [start=" + start + ", end=" + end + "]";
	}

	public static Comparator<Slot1> slot1Comparator = new Comparator<Slot1>() {
		public int compare(Slot1 s1, Slot1 s2) {

			int end1 = s1.end;
			int end2 = s2.end;
			int start1 = s1.start;
			int start2 = s2.start;

			if (end1 < end2) {
				return -1;
			} else if (end1 > end2) {
				return 1;
			} else {
				if (start1 < start2) {
					return -1;
				} else if (start1 > start2) {
					return 1;
				} else {
					return 0;
				}
			}

		}
	};

}
