package com.rotai.res_art2.chapter2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rotai.res_art2.R;
import com.rotai.res_art2.utils.LogUtil;
import com.rotai.res_art2.utils.MyConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        LogUtil.e(LogUtil.TAG,"UserManager.sUserId="+UserManager.sUserId);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        recoverFromFile();
    }

    private void recoverFromFile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user=null;
                File cacheFile = new File(MyConstants.CACHE_FILE_PATH);
                if(cacheFile.exists()){
                    ObjectInputStream objectInputStream=null;
                    try {
                        objectInputStream=new ObjectInputStream(new FileInputStream(cacheFile));
                        user= (User) objectInputStream.readObject();
                        LogUtil.e(LogUtil.TAG,"recover user:"+user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(objectInputStream!=null){
                            try {
                                objectInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
