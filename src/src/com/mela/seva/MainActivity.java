package com.mela.seva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener {
	TextView textView;
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.textView);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	private void getdata(){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.
                    ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy); 
            URL url = new URL("http://seva.djangostars.com/api/v1/technology/?format=json");
            HttpURLConnection con = (HttpURLConnection) url
              .openConnection();
            readStream(con.getInputStream());
            } catch (Exception e) {
            e.printStackTrace();
          }

    }
  private void readStream(InputStream in) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(in));
      StringBuilder builder = new StringBuilder();
      String line = "";
      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }
      
      try {
//			JSONObject jUsers = new JSONObject(users);
			JSONObject jTechnologies= new JSONObject(builder.toString());
			
			JSONArray objects = jTechnologies.getJSONArray("objects");
		
		// TODO nice representation
		StringBuilder result = new StringBuilder();
		for (int i=0;i<objects.length();i++) {
			result.append(objects.getJSONObject(i).getString("title"))
			.append(" - ")
			.append(objects.getJSONObject(i).getString("avg"))
			.append('\n');
		}
		textView.setText(result.toString());
      } catch (JSONException e) {
			e.printStackTrace();
			Toast.makeText(this, "Bad data recieed from server", Toast.LENGTH_LONG).show();
		}
		
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
          }
      }
    }
  }

@Override
public void onClick(View arg0) {
	getdata();
	
} 

}
