package g.p.caloriecount.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

public class DynamoDbAdapter {
	private static final String TAG = "DynamoDbAdapter";

	public static PutItemRequest toPutItemRequest(String tableName, Object object){
		
		if(object == null){
			return null;
		}
		
		PutItemRequest request = new PutItemRequest();
		request.setTableName(tableName);
		
		for(Method method : object.getClass().getMethods()){
			if(method.isAnnotationPresent(PayloadField.class)){
				
				try{
					String fieldName = method.getAnnotation(PayloadField.class).fieldName();
					
					if(method.getReturnType().equals(Void.TYPE)){
						//it is a setter
					}else if(Integer.TYPE == method.getReturnType()){
						
						AttributeValue v = new AttributeValue();
						v.setN(Integer.toString((Integer)method.invoke(object, (Object[])null)));
						request.addItemEntry(fieldName, v);
						
					}else if(String.class.isAssignableFrom(method.getReturnType())){
						
						AttributeValue v = new AttributeValue();
						v.setS((String)method.invoke(object, (Object[])null));
						request.addItemEntry(fieldName, v);
						
					}else if(Boolean.TYPE == method.getReturnType()){
						
						AttributeValue v = new AttributeValue();
						v.setBOOL((Boolean)method.invoke(object, (Object[])null));
						request.addItemEntry(fieldName, v);
					}else{
						Log.e(TAG, "unknown type while writing to dynamo db: " + method.getReturnType());
					}
					
				}catch(Exception ex){
					Log.e(TAG, "error invoking method", ex);
					ex.printStackTrace();
				}
				
			}
		}
		
		
		return request;
	}
	
}
