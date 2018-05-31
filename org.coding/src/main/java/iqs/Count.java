package iqs;

import java.util.Comparator;

public class Count {

	int room;
	int value;

	public Count() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Count(int room, int value) {
		super();
		this.room = room;
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + room;
		result = prime * result + value;
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
		Count other = (Count) obj;
		if (room != other.room)
			return false;
		else {
			if (value != other.value)
				return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Count [room=" + room + ", value=" + value + "]";
	}

	public static Comparator<Count> roomComparator = new Comparator<Count>() {
		public int compare(Count s1, Count s2) {

			int room1 = s1.room;
			int room2 = s2.room;
			int value1 = s1.value;
			int value2 = s2.value;

			if (value1 < value2) {
				return 1;
			} else if (value1 > value2) {
				return -1;
			} else {
				if (room1 < room2) {
					return -1;
				} else if (room1 > room2) {
					return 1;
				} else {
					return 0;
				}
			}

		}
	};

}
