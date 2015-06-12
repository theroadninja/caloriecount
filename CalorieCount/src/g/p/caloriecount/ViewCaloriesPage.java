package g.p.caloriecount;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewCaloriesPage extends LinearLayout {

	Button addButton = null;
	
	public ViewCaloriesPage(Context context) {
		this(context, null);
	}
	
	public ViewCaloriesPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setOrientation(LinearLayout.VERTICAL);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.merge_view_calories, this, true);
		
		addButton = (Button)findViewById(R.id.button_add_meal);
	}
	
	public void setOnNewButton(View.OnClickListener listener){
		addButton.setOnClickListener(listener);
	}

}
