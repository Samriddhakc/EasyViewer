package com.codepath.skc.easyviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ViewActivity extends AppCompatActivity {

    private static final String TAG = "PdfView";
    private PdfRenderer renderer;
    AssetManager assetManager;
    private PdfRenderer.Page currentPage;
    private ImageView imgPdf;
    private Button btnNextPage;
    private Button btnPrevPage;
    private ParcelFileDescriptor parcelFileDescriptor;
    ViewFile file;
    private InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent i = getIntent();
        file = Parcels.unwrap(i.getParcelableExtra("f"));
        Log.i(TAG,"The file name is supposed to be"+file.getFilename());
        imgPdf=findViewById(R.id.imgPdf);
        btnNextPage=findViewById(R.id.btnNextPage);
        btnPrevPage=findViewById(R.id.btnPrevPage);
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (renderer!=null){
                    renderPage(currentPage.getIndex()+1);
                }
            }
        });

        btnPrevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (renderer!=null){
                    renderPage(currentPage.getIndex()-1);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initRenderer();
        renderPage(0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    private void renderPage(int pageIndex) {
        if (currentPage!=null){
            currentPage.close();
        }

        currentPage=renderer.openPage(pageIndex);
        Bitmap bitmap=Bitmap.createBitmap(currentPage.getWidth(),currentPage.getHeight(),Bitmap.Config.ARGB_8888);
        currentPage.render(bitmap,null,null,PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        imgPdf.setImageBitmap(bitmap);
        btnPrevPage.setEnabled(currentPage.getIndex()>0);
        btnNextPage.setEnabled(currentPage.getIndex()+1<renderer.getPageCount());

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    private void initRenderer() {
        try {
            File temp = new File(getCacheDir(), "tempPdf.pdf");
            FileOutputStream fos = new FileOutputStream(temp);
            //assetManager=getAssets();

            if (file.getFilename().equals("file1"))
            {
                is=getResources().openRawResource(R.raw.test);
            }
            else if (file.getFilename().equals("file2"))
            {
                is=getResources().openRawResource(R.raw.test1);
            }

            else if (file.getFilename().equals("file3"))
            {
                is=getResources().openRawResource(R.raw.test2);
            }

            byte[] buffer=new byte[1024];
            int readBytes;
            while ((readBytes=is.read(buffer))!=-1)
            {
                fos.write(buffer,0,readBytes);
            }
            fos.close();
            is.close();
            parcelFileDescriptor=ParcelFileDescriptor.open(temp,ParcelFileDescriptor.MODE_READ_ONLY);
            renderer=new PdfRenderer(parcelFileDescriptor);
        }
        catch (IOException e)
        {
            Log.e(TAG,e.getMessage(),e);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}

