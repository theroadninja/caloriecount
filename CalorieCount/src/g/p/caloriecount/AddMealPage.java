package g.p.caloriecount;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class AddMealPage extends LinearLayout {
	
	public AddMealPage(Context context) {
		this(context, null);
	}
	
	public AddMealPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setOrientation(LinearLayout.VERTICAL);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.merge_add_meal_page, this, true);
		
	}

}
