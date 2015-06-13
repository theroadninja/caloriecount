package g.p.caloriecount;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddMealPage extends LinearLayout {
	
	String foodId = null;
	
	public AddMealPage(Context context) {
		this(context, null);
	}
	
	public AddMealPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setOrientation(LinearLayout.VERTICAL);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.merge_add_meal_page, this, true);
		
	}
	
	public void reset(){
		((TextView)findViewById(R.id.text_food)).setText("");
		((TextView)findViewById(R.id.text_calories)).setText("");
		((CheckBox)findViewById(R.id.checkbox_unknown)).setChecked(false);
		foodId = null;
	}
	
	public void setOnOkButton(View.OnClickListener v){
		findViewById(R.id.button_ok).setOnClickListener(v);
	}
	
	public Boolean getCaloriesUnknown(){
		return ((CheckBox)findViewById(R.id.checkbox_unknown)).isChecked();
	}
	
	public String getFoodId(){
		return foodId;
	}
	
	public String getFood(){
		return ((TextView)findViewById(R.id.text_food)).getText().toString();
	}
	
	public int getCalories(){
		try{
			return Integer.valueOf(((TextView)findViewById(R.id.text_calories)).getText().toString());
		}catch(NumberFormatException ex){
			return 0;
		}
	}
	

}
