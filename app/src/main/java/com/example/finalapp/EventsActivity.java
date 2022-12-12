package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class EventsActivity extends AppCompatActivity {
    String urlForDescription;
    TextView title, date_and_time, venue_information;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        title = findViewById(R.id.title2);
        date_and_time = findViewById(R.id.dateandtime);
        venue_information = findViewById(R.id.venueandinformation);

        //urlForDescription = "http://www.omdbapi.com/?t=" + value + "&apikey=27fbd61d";
        //urlForDescription = "https://api.seatgeek.com/";
        urlForDescription = "https://api.seatgeek.com/2/events/739515?callback=fireEvent";

        // creating a new variable for our request queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlForDescription, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d("HELLO", "ghtg");
//                            title = response.getString("Title");
//                            imageurl = response.getString("Poster");
//                            titletxt.setText(response.getString("Title"));
//                            yearofreleasetxt.setText(response.getString("Year"));
//                            rating.setText(response.getString("imdbRating"));
//                            runningtime.setText(response.getString("Runtime"));
//                            genre.setText(response.getString("Genre"));
//                            website_link.setText(response.getString("Website"));
//                            website_link_string = response.getString("Website");
//                            Picasso.get().load(response.getString("Poster")).into(imageView);
                            title.setText(response.getString("title"));
                            date_and_time.setText(response.getString("datetime_local"));
                            venue_information.setText(response.getString("venue"));


                            Log.d("HELLO", "helle");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    //}
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EventsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE_ERROR", error.getMessage());
            }
        });
        MySingleton.getInstance(EventsActivity.this).addToRequestQueue(jsonObjectRequest);
    }
}