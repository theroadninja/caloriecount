package g.p.caloriecount.data;

import java.util.UUID;

public class Food {

	String id;
	
	String name;
	
	int calories;
	
	boolean caloriesUnknown;
	
	public Food(){
		this.id = null;
		this.name = null;
		this.calories = 0;
		this.caloriesUnknown = false;
	}
	
	
	public Food(String name, int calories, boolean caloriesUnknown){
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.calories = calories;
		this.caloriesUnknown = caloriesUnknown;
	}
	
	@PayloadField(fieldName = "id")
	public void setId(String id){
		this.id = id;
	}
	
	@PayloadField(fieldName = "id")
	public String getId(){
		return this.id;
	}
	
	@PayloadField(fieldName = "name")
	public void setName(String name){
		this.name = name;
	}
	
	@PayloadField(fieldName = "name")
	public String getName(){
		return this.name;
	}
	
	@PayloadField(fieldName = "calories")
	public void setCalories(int i){
		this.calories = i;
	}
	
	@PayloadField(fieldName = "calories")
	public int getCalories(){
		return this.calories;
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
