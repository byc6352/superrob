/**
 * 
 */
package com.example.h3.job;

import com.example.h3.BackgroundMusic;
import com.example.h3.Config;
import com.example.h3.MainActivity;
import com.byc.superrob.R;
import accessibility.AccessibilityHelper;
import com.example.h3.FloatingWindowPic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import util.SpeechUtil;

/**
 * @author byc
 *
 */
public class LuckyMoneyDetailJob {
	private static LuckyMoneyDetailJob current;
	private Context context;
	private String[] mReceiveInfo={"","",""};//�����Ϣ��
	private int mIntInfo=0;//��Ϣ����
	private boolean bRecycled=false;//�Ƿ��˳�ѭ��
	private SpeechUtil speaker ;
	private FloatingWindowPic fwp;
	private BackgroundMusic mBackgroundMusic;
	private String mMoneyValue;//���������
	private String mSayThunder;//��ֵ��
	//private static String mLuckyMoneySay;//�����
	private LuckyMoneyLauncherJob LancherJob;
	private static final String WECHAT_GONG_XI="��ϲ���ƣ��󼪴�����";//
	
    public static synchronized LuckyMoneyDetailJob getLuckyMoneyDetailJob(Context context) {
    	
        if(current == null) {
            current = new LuckyMoneyDetailJob(context);
        }
        return current;
    }
    private LuckyMoneyDetailJob(Context context){
    	this.context = context;
    	speaker=SpeechUtil.getSpeechUtil(context);
        mBackgroundMusic=BackgroundMusic.getInstance(context);
        LancherJob=LuckyMoneyLauncherJob.getLuckyMoneyLauncherJob(context);
        
		fwp=FloatingWindowPic.getFloatingWindowPic(context,R.layout.float_click_delay_show);
		int w=Config.screenWidth-200;
		int h=Config.screenHeight-200;
		fwp.SetFloatViewPara(100, 200,w,h);
		//���չ㲥��Ϣ
        IntentFilter filter = new IntentFilter();
        filter.addAction(Config.ACTION_DISPLAY_SUCCESS);
        filter.addAction(Config.ACTION_DISPLAY_FAIL);
        context.registerReceiver(ClickLuckyMoneyReceiver, filter);
    }
    
    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void recycle(AccessibilityNodeInfo info) {
    	if(bRecycled)return;
		if (info.getChildCount() == 0) {
			//ȡ��Ϣ
			mReceiveInfo[mIntInfo]=info.getText().toString();

			//Log.i(TAG, "child widget----------------------------" + info.getClassName());
			//Log.i(TAG, "Text��" + info.getText());
			//Log.i(TAG, "windowId:" + info.getWindowId());
			if(mIntInfo==2){bRecycled=true;return;}
			mIntInfo=mIntInfo+1;
		} else {
			for (int i = 0; i < info.getChildCount(); i++) {
				if(info.getChild(i)!=null){
					recycle(info.getChild(i));
				}
			}
		}
	}
    public void LuckyMoneyDetailShow(AccessibilityNodeInfo info){
    	mIntInfo=0;
    	bRecycled=false;
    	recycle(info);
    	//��ܳɹ���������3.0Ԫ����ֵΪ��7�����׳ɹ���
    	//���ʧ�ܣ�δ��Ȩ�û�������͸�ӷ��񣡶��ʧ�ܣ�
    	String sMoneyValue=mReceiveInfo[2];
    	String sMoneySay=mReceiveInfo[1];
    	//String sMoneySay=mLuckyMoneySay;//01-05�޸ģ���Lancher�����ȡ����
    	Log.i(Config.TAG, "Detail-----"+sMoneySay);
    	String say="��ϲ�����������"+sMoneyValue+"Ԫ��͸�ӳɹ���";
    	Toast.makeText(context,say, Toast.LENGTH_LONG).show();
    	speaker.speak(say);
    
    	
    }
  
	private BroadcastReceiver ClickLuckyMoneyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            //Log.d(TAG, "receive-->" + action);
            if(Config.ACTION_DISPLAY_SUCCESS.equals(action)) {
            	
            }
            if(Config.ACTION_DISPLAY_FAIL.equals(action)) {
            	mBackgroundMusic.stopBackgroundMusic();
            }
        }
    };

}


/*
 *     //��ȡLancher���洫�ݹ����ĺ����
    public static void setLuckyMoneySay(String sLuckyMoneySay){
    	mLuckyMoneySay=sLuckyMoneySay;
    }
    */
