package com.example.launcherdemorajendra.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.launcherdemorajendra.Adapter.InstalledAppsAdapter;
import com.example.launcherdemorajendra.Utils.AppsManager;
import com.example.launcherdemorajendra.Models.AppInfo;
import com.example.launcherdemorajendra.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<AppInfo> installedApps;
    private AppsManager appManager;
    GridLayoutManager recyclerViewLayoutManager;
    Button btnExoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnExoPlayer=findViewById(R.id.btnClick);
        installedApps = new ArrayList<AppInfo>();
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycler);
       // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
       // mRecyclerView.setLayoutManager(layoutManager);
        btnExoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(mIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        new MyTask().execute("my string parameter");
        super.onResume();
    }

    private class MyTask extends AsyncTask<String, Integer, String> {
        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {
            installedApps.clear();
            super.onPreExecute();
            // Do something like display a progress bar
        }
        @Override
        protected String doInBackground(String... params) {

            appManager = new AppsManager(MainActivity.this);
            installedApps = appManager.getApps();

            // Initialize a new adapter for RecyclerView
            mAdapter = new InstalledAppsAdapter(
                    getApplicationContext(), installedApps
            );
            return "this string is passed to onPostExecute";
        }
        // This is called from background thread but runs in UI
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // Do things like update the progress bar
        }
        // This runs in UI when background thread finishes
        @Override
        protected void onPostExecute(String result) {
            // check if there is no installed app found
            if(installedApps.size()==0) {
                Toast.makeText(MainActivity.this, "No User Installed App Found !!", Toast.LENGTH_SHORT).show();
            }
            mRecyclerView.setAdapter(mAdapter);
            super.onPostExecute(result);
            // Do things like hide the progress bar or change a TextView
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Play_Video) {
            Intent mIntent = new Intent(MainActivity.this,VideoActivity.class);
            startActivity(mIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
