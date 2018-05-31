package iqs;

import java.util.Comparator;

public class Slot {

	int start;
	int end;

	public Slot(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Slot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
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

		if (start != other.start)
			return false;
		else {
			if (end != other.end)
				return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Slot [start=" + start + ", end=" + end + "]";
	}

	public static Comparator<Slot> slotComparator = new Comparator<Slot>() {
		public int compare(Slot s1, Slot s2) {

			int start1 = s1.start;
			int start2 = s2.start;
			int end1 = s1.end;
			int end2 = s2.end;

			if (start1 < start2) {
				return -1;
			} else if (start1 > start2) {
				return 1;
			} else {
				if (end1 < end2) {
					return -1;
				} else if (end1 > end2) {
					return 1;
				} else {
					return 0;
				}
			}

		}
	};

}
