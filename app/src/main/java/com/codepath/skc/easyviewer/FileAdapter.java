package com.codepath.skc.easyviewer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder>  {

    public static final String TAG = "FileAdapter";
    private Context context;
    private List<ViewFile> files;
    private OnFileListener onFileListener;


    public FileAdapter(Context context, List<ViewFile> files,OnFileListener onFileListener){
        this.context=context;
        this.files=files;
        this.onFileListener=onFileListener;
        Log.i(TAG,"Inside constructor");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_item, parent, false);
        return new ViewHolder(view,onFileListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewFile file=files.get(position);
        Log.i(TAG,"Inside Bind");
        holder.bind(file);
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView etFileName;
        private TextView etfileCreated;
        private TextView etfileDescription;
        OnFileListener onFileListener;

        public ViewHolder(@NonNull View itemView,OnFileListener onFileListener) {
            super(itemView);
            etFileName=itemView.findViewById(R.id.filename);
            etfileCreated=itemView.findViewById(R.id.uploadedDate);
            etfileDescription=itemView.findViewById(R.id.fileDescription);
            this.onFileListener=onFileListener;
            itemView.setOnClickListener(this);
        }


        public void bind(ViewFile file) {
            Log.i(TAG,"From the call bind method");
            Log.i(TAG,file.getFilename());
            etFileName.setText(file.getFilename());
            etfileCreated.setText(file.getFilecreated());
            etfileDescription.setText(file.getDescription());
        }

        @Override
        public void onClick(View v) {
            onFileListener.onFileClick(getAdapterPosition());
        }
    }

    public interface  OnFileListener{
        void onFileClick(int position);
    }
}
