package com.codepath.skc.easyviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FileAdapter.OnFileListener {



    public static final String TAG = "MainActivity";
    RecyclerView rvFiles;
    public FileAdapter fileAdapter;
    protected List<ViewFile> allFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"atleast here!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvFiles = findViewById(R.id.rvFiles);
        allFiles = new ArrayList<>();
        rvFiles.setLayoutManager(new LinearLayoutManager(this));
        fileAdapter = new FileAdapter(this,allFiles,this);
        rvFiles.setAdapter(fileAdapter);
        populateWithFiles();
    }

    private void populateWithFiles() {
        ParseQuery<ViewFile> query = ParseQuery.getQuery(ViewFile.class);
        //query.include(Course.KEY_USER);
        //query.addDescendingOrder(Post.CREATED_AT);
        query.findInBackground(new FindCallback<ViewFile>() {
            @Override
            public void done(List<ViewFile> files, ParseException e) {

                if (e != null) {
                    Log.e(TAG, "Not getting Posts!", e);
                }

                allFiles.addAll(files);
                fileAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onFileClick(int position) {
        Log.i(TAG,"Here!");
        ViewFile file=allFiles.get(position);
        //Toast.makeText(this, file.getFilename(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("f", Parcels.wrap(file));
        startActivity(intent);
        finish();
    }
}
