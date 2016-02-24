package com.hotelquickly.niroshan.hotelquickly;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.hotelquickly.niroshan.hotelquickly.beans.BeanObjectList;
import com.hotelquickly.niroshan.hotelquickly.adapters.CardViewDataAdapter;
import com.hotelquickly.niroshan.hotelquickly.constant.AppConst;
import com.hotelquickly.niroshan.hotelquickly.utils.AppUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * com.hotelquickly.niroshan.hotelquickly
 * <p/>
 * Created by Niroshan Rathnayake.
 */

public class MainListActivity extends ActionBarActivity {

	private static final String TAG = "Http Connection";

	private Toolbar toolbar;
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private LinearLayoutManager mLayoutManager;

	private ArrayList<BeanObjectList> fetchedList = new ArrayList<>();
	public static boolean backPressedToExitOnce = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.toolbar = (Toolbar) findViewById(R.id.toolbar);

		new AsyncHttpTask().execute(AppConst.MAIN_URL);

		if (toolbar != null) {
			setSupportActionBar(toolbar);
			getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

		}

		this.mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

		mRecyclerView.setHasFixedSize(true);

		mLayoutManager = new LinearLayoutManager(this);

		// use a linear layout manager
		mRecyclerView.setLayoutManager(mLayoutManager);

		mRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(
				mLayoutManager) {
			@Override
			public void onLoadMore(int current_page) {

			}

		});

	}

	public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

		@Override
		protected Integer doInBackground(String... params) {
			InputStream inputStream = null;

			HttpURLConnection urlConnection = null;

			Integer result = 0;
			try {
                /* forming th java.net.URL object */
				URL url = new URL(params[0]);

				urlConnection = (HttpURLConnection) url.openConnection();

                 /* optional request header */
				urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
				urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
				urlConnection.setRequestMethod("GET");

				Log.d("URL ", url.toString());

				int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
				if (statusCode ==  200) {

					inputStream = new BufferedInputStream(urlConnection.getInputStream());

					String response = AppUtil.convertInputStreamToString(inputStream);

					fetchedList = AppUtil.parseResult(response);

					result = 1; // Successful

				}else{
					result = 0; //"Failed to fetch data!";
				}

			} catch (Exception e) {
				Log.d(TAG, e.getLocalizedMessage());
			}

			return result; //"Failed to fetch data!";
		}

		@Override
		protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

			if(result == 1){

				mAdapter = new CardViewDataAdapter(fetchedList, MainListActivity.this);
				// set the adapter object to the Recyclerview
				mRecyclerView.setAdapter(mAdapter);

			}else{
				Log.e(TAG, "Failed to fetch data!");
			}
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		new AsyncHttpTask().cancel(true);
	}

	@Override
	public void onBackPressed() {

		Log.d(TAG, "onBackPressed() " + getSupportFragmentManager().getBackStackEntryCount());

			if (backPressedToExitOnce) {
				MainListActivity.this.finish();
				super.onBackPressed();
			} else {
				this.backPressedToExitOnce = true;
				Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						backPressedToExitOnce = false;
					}
				}, AppConst.BACK_PRESSED_DELAY);
			}


	}
}
