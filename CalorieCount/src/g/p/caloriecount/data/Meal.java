package g.p.caloriecount.data;

import java.util.Date;
import java.util.UUID;

/** an instance of eating some food */
public class Meal {
	
	String id;
	
	Date date;
	String foodId;
	Integer quantity;
	
	boolean caloriesUnknown;
	
	public Meal(){
		this.id = null;
		this.date = null;
		this.foodId = null;
		this.quantity = null;
		this.caloriesUnknown = false;
	}
	
	public Meal(Date mealDate, String foodId, Integer quantity, boolean caloriesUnknown){
		this.id = UUID.randomUUID().toString();
		this.date = mealDate;
		this.foodId = foodId;
		this.quantity = quantity;
		this.caloriesUnknown = caloriesUnknown;
	}
	
	@PayloadField(fieldName = "caloriesUnknown")
	public void setCaloriesUnknown(boolean b){
		this.caloriesUnknown = b;
	}
	
	@PayloadField(fieldName = "caloriesUnknown")
	public boolean getCaloriesUnknown(){
		return this.caloriesUnknown;
	}

}
