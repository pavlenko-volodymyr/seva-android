package com.mela.seva;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.textView);
		
		String users = "{\"meta\": {\"limit\": 20, \"next\": null, \"offset\": 0, \"previous\": null, \"total_count\": 1}, \"objects\": [{\"avg_level\": 4.0, \"bio\": \"the best\", \"email\": \"mel@mail.com\", \"evaluations\": [{\"created\": \"2013-09-11T19:53:27.878332\", \"is_favorite\": true, \"level\": 4, \"logo\": \"\", \"technology\": \"PostgreSQL\", \"technology_slug\": \"postgresq\", \"updated\": \"2013-09-11T19:53:27.878391\"}], \"favorites\": [\"PostgreSQL\"], \"full_name\": \"\", \"id\": 1, \"is_active\": true, \"last_login\": \"2013-09-11T19:47:06.625023\", \"resource_uri\": \"/api/v1/users/mel/\", \"username\": \"mel\"}]}";
		String technology = "{\"meta\": {\"limit\": 20, \"next\": null, \"offset\": 0, \"previous\": null, \"total_count\": 2}, \"objects\": [{\"avg\": null, \"resource_uri\": \"/api/v1/technology/1/\", \"slug\": \"django\", \"title\": \"Django\"}, {\"avg\": 4.0, \"resource_uri\": \"/api/v1/technology/2/\", \"slug\": \"postgresq\", \"title\": \"PostgreSQL\"}]}";
		
		try {
			JSONObject jUsers = new JSONObject(users);
			JSONObject jTechnologies= new JSONObject(technology);
			
			JSONArray objects = jTechnologies.getJSONArray("objects");
			
			// TODO nice representation
			textView.setText(objects.getJSONObject(0).getString("slug"));
			
		} catch (JSONException e) {
			e.printStackTrace();
			Toast.makeText(this, "Bad data recieed from server", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
