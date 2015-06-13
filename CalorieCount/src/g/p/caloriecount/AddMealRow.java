package g.p.caloriecount;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class AddMealRow extends LinearLayout {

	public AddMealRow(Context context) {
		this(context, null);
	}
	public AddMealRow(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.add_meal_row_view, this, true);
	}

}
