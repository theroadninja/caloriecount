package g.p.caloriecount;

import g.p.caloriecount.data.DynamoDbAdapter;
import g.p.caloriecount.data.Food;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	
	final String COGNITO_IDENTITY_POOL = "us-east-1:2b06b9f1-c945-4a8c-82f6-a1ec765a5798";
	
	public static final String TABLE_FOOD = "Food";

	CognitoCachingCredentialsProvider credentialsProvider = null;
	
	AmazonDynamoDBClient ddbClient = null;
	
	AddMealPage addMeal = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //following this tutorial:
        // http://docs.aws.amazon.com/mobile/sdkforandroid/developerguide/dynamodb_om.html
        
        Context context = this;
        
        addMeal = (AddMealPage)findViewById(R.id.page_add_meal);
        
        credentialsProvider = new CognitoCachingCredentialsProvider(
        		context,    // get the context for the current activity
        	    COGNITO_IDENTITY_POOL,    /* Identity Pool ID */
        	    Regions.US_EAST_1           /* Region for your identity pool*/
        	);
        
        
        //stupid object mapping shit
        ddbClient = new AmazonDynamoDBClient(credentialsProvider);
        
        
        final DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
        
        /*
        
        //create an object
        final Book book = new Book();
        book.setTitle("Great Expectations");
        book.setAuthor("Charles Dickens");
        book.setPrice(1299);
        book.setIsbn("1234567890");
        book.setHardCover(false);
        
        new Thread(new Runnable(){
        	public void run(){
        		//makes a direct network call
                mapper.save(book);
        	}
        }).start();
        */
        
        
        ((ViewCaloriesPage)findViewById(R.id.page_view_calories)).setOnNewButton(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				addMeal.reset();
				addMeal.setVisibility(View.VISIBLE);
				findViewById(R.id.page_view_calories).setVisibility(View.GONE);
			}
		});
        
        addMeal.setOnOkButton(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//TODO:  food id must be reset if calories are changed for existing food item
				
				if(addMeal.getFoodId() == null){
					
					Food food = new Food();
					food.setId();
					food.setCalories(addMeal.getCalories());
					food.setName(addMeal.getFood());
					PutItemRequest r = DynamoDbAdapter.toPutItemRequest(TABLE_FOOD, food);
					
					//TODO ddbClient.putItem(r);
					PutItemTask t = new PutItemTask();
					t.execute(r);
				}
				
				//Meal meal = new Meal();
				
				//addMeal.getFoodId();
				//
			}
		});
        
    }
    
    class PutItemTask extends AsyncTask<PutItemRequest, Void, Boolean> {

		@Override
		protected Boolean doInBackground(PutItemRequest... params) {
			
			try{
			
				for(PutItemRequest r : params){
					PutItemResult result = ddbClient.putItem(r);
					return Boolean.TRUE;
				}
				
				return null;
			}catch(ConditionalCheckFailedException ex){
				Log.e(TAG, "exception putting item to amazon", ex);
				
			}catch(AmazonServiceException ex){
				Log.e(TAG, "exception putting item to amazon", ex);
			}
			
			return Boolean.FALSE;
			
		}
		
		@Override
		protected void onPostExecute(Boolean result){
			Toast.makeText(MainActivity.this, "request sent success=" + result, Toast.LENGTH_SHORT).show();
		}
    	
    }
    
    
    
    
    @Override
    public void onBackPressed(){
    	if(findViewById(R.id.page_add_meal).getVisibility() == View.VISIBLE){
    		findViewById(R.id.page_add_meal).setVisibility(View.GONE);
    		findViewById(R.id.page_view_calories).setVisibility(View.VISIBLE);
    	}else{
    		super.onBackPressed();
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
