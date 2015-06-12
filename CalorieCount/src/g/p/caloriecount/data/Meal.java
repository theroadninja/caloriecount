package g.p.caloriecount.data;

import java.util.Date;
import java.util.UUID;

/** an instance of eating some food */
public class Meal {
	
	String id;
	
	Date date;
	String foodId;
	Integer quantity;
	
	public Meal(){
		this.id = null;
		this.date = null;
		this.foodId = null;
		this.quantity = null; 
	}
	
	public Meal(Date mealDate, String foodId, Integer quantity){
		this.id = UUID.randomUUID().toString();
		this.date = mealDate;
		this.foodId = foodId;
		this.quantity = quantity;
	}

}
