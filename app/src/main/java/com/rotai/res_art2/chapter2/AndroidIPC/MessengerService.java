package com.rotai.res_art2.chapter2.AndroidIPC;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import com.rotai.res_art2.utils.LogUtil;
import com.rotai.res_art2.utils.MyConstants;

import java.time.temporal.ValueRange;

public class MessengerService extends Service {
    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyConstants.MSG_FROM_CLIENT:
                    LogUtil.i(LogUtil.TAG,"receive msg from client:"+msg.getData().get("msg"));
                   break;
                   default:
                       super.handleMessage(msg);
            }
        }
    }
    private final Messenger mMessenger=new Messenger(new MessengerHandler());

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  mMessenger.getBinder();
    }
}
