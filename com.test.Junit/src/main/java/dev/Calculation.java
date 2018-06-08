package dev;

import db.CalculationDao;

public class Calculation {
	CalculationDao repo;
	public Calculation()
	{
		repo= new  CalculationDao();
	}
	public int findMax(int arr[]) {
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (max < arr[i])
				max = arr[i];
		}
		//mock below two methods
		repo.save(arr);
		repo.checkEmpty();
		return max;
	}

}
