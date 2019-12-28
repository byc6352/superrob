/**
 * 
 */
package com.example.h3.job;


import com.example.h3.BackgroundMusic;
import com.example.h3.Config;
import accessibility.QiangHongBaoService;
import ad.VersionParam;
import notification.IStatusBarNotification;
import notification.NotifyHelper;
import util.SpeechUtil;
import accessibility.AccessibilityHelper;
import accessibility.BaseAccessibilityJob;

import com.example.h3.FloatingWindowPic;
import com.byc.superrob.R;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

/**
 * @author byc
 *
 */
public class WechatAccessibilityJob extends BaseAccessibilityJob  {
	
	private static WechatAccessibilityJob current;
	//-------------------------------�����ʱ---------------------------------------------
	private int mWechatOpenDelayTime=0;//�����ʱ
	private SpeechUtil speeker ;
	private FloatingWindowPic fwp;
	private BackgroundMusic mBackgroundMusic;

	public int mLuckyMoneyType=0;//�������Ѳ���������������װ���
	private LuckyMoneyReceiveJob mReceiveJob;
	private LuckyMoneyDetailJob mDetailJob;
	private LuckyMoneyLauncherJob mLauncherJob;
	private LuckyMoney mLuckyMoney;//�������
	private String mCurrentUI="";
	private AccessibilityNodeInfo mRootNode; 
	public WechatAccessibilityJob(){
		super(new String[]{Config.WECHAT_PACKAGENAME});
	}
	


    @Override
    public void onCreateJob(QiangHongBaoService service) {
        super.onCreateJob(service);
        EventStart();
        mReceiveJob=LuckyMoneyReceiveJob.getLuckyMoneyReceiveJob(context);
        mDetailJob=LuckyMoneyDetailJob.getLuckyMoneyDetailJob(context);
        mLauncherJob=LuckyMoneyLauncherJob.getLuckyMoneyLauncherJob(context);
        mLuckyMoney=LuckyMoney.getLuckyMoney(context);//�������
        speeker=SpeechUtil.getSpeechUtil(context);
        mBackgroundMusic=BackgroundMusic.getInstance(context);
        
		fwp=FloatingWindowPic.getFloatingWindowPic(context,R.layout.float_click_delay_show);
		int w=Config.screenWidth-200;
		int h=Config.screenHeight-200;
		fwp.SetFloatViewPara(100, 200,w,h);
		//���չ㲥��Ϣ
        IntentFilter filter = new IntentFilter();
        filter.addAction(Config.ACTION_CLICK_LUCKY_MONEY);
        context.registerReceiver(ClickLuckyMoneyReceiver, filter);

       
    }
   
    @Override
    public void onStopJob() {
    	super.onStopJob();
    	//mVolumeChange.onDestroy();
    }
    public static synchronized WechatAccessibilityJob getJob() {
        if(current == null) {
            current = new WechatAccessibilityJob();
        }
        return current;
    }
    /*
	 * (ˢ�´�������)
	 * @see accessbility.AccessbilityJob#onWorking()
	 */
	@Override
	public void onWorking(){
		//Log.i(TAG2, "onWorking");
		//installApp.onWorking();
	}
    //----------------------------------------------------------------------------------------
    @Override
    public void onReceiveJob(AccessibilityEvent event) {
    	super.onReceiveJob(event);
		if(!mIsEventWorking)return;
		if(!mIsTargetPackageName)return;
		
    	final int eventType = event.getEventType();
    	if(event.getClassName()==null)return;
    	String sClassName=event.getClassName().toString();    	
    

    	//��������
    	//if(!event.getPackageName().toString().equals(Config.WECHAT_PACKAGENAME))return;
		if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
			mCurrentUI=sClassName;
			Log.i(TAG, sClassName);
			mRootNode=event.getSource();
			if(mRootNode==null)return;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)service.closeClick();
			//-------------------------��������----------------------------------------------------

					if(mCurrentUI.indexOf(VersionParam.WINDOW_LUCKYMONEY_RECEIVEUI)!=-1){
						if(UnpackLuckyMoney(mRootNode))
							Config.JobState=Config.STATE_CLICK_LUCKYMONEY;
						else
							Config.JobState=Config.STATE_NONE;
					}

			//-------------------------��ʾ��ϸ��Ϣ����----------------------------------------------------
			if(mCurrentUI.equals(Config.WINDOW_LUCKYMONEY_DETAILUI)){
				//if(mLuckyMoneyType==Config.TYPE_LUCKYMONEY_THUNDER){
				if(Config.bShowMoney){
					UnpackLuckyMoneyShow(mRootNode) ;
				}
				mLuckyMoneyType=Config.TYPE_LUCKYMONEY_NONE;
				Config.JobState=Config.STATE_NONE;
				AccessibilityHelper.performBack(service);
			}
		}//if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) 
		if (eventType == AccessibilityEvent.TYPE_WINDOWS_CHANGED) {
			//mRootNode=event.getSource();
		}
		//+++++++++++++++++++++++++++++++++���ݸı�+++++++++++++++++++++++++++++++++++++++++++++++
		if (eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {

			if(Config.JobState==Config.STATE_CLICK_LUCKYMONEY)return;
			if(mCurrentUI.equals(Config.WINDOW_LUCKYMONEY_LAUNCHER_UI)){
				mRootNode=event.getSource();
				if(mRootNode==null)return;
				//AccessibilityHelper.printParentInfo(mRootNode);
				mRootNode=AccessibilityHelper.getRootNode(mRootNode);
				//AccessibilityHelper.recycle(mRootNode);
				//if(mLauncherJob.isMemberChatUi(mRootNode)){
			
					if(!Config.bSwitch)return;//�ܿ��عرգ�
					if(clickLuckyMoney(mRootNode))Config.JobState=Config.STATE_CLICK_LUCKYMONEY;			
				//}
				//if(Config.getConfig(context).bAutoClearThunder)clickLuckyMoney();
			}//if(mCurrentUI.equals(Config.WINDOW_LUCKYMONEY_LAUNCHER_UI)){
		}//if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) 
		//}//if(WECHAT_PACKAGENAME.equals(pkn))
    }
  
    //������ˣ������
    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean clickLuckyMoney(AccessibilityNodeInfo rootNode){

    	if(mCurrentUI.equals(Config.WINDOW_LUCKYMONEY_LAUNCHER_UI)){
    		
    		mLuckyMoney.LuckyMoneyNode=mLuckyMoney.getLastLuckyMoneyNode(rootNode);
    		if(mLuckyMoney.LuckyMoneyNode==null)return false;
    		boolean bNewLuckyMoney=true;
    		if(Config.wv<1120){
    			mLuckyMoney.RobbedLuckyMoneyInfoNode=mLuckyMoney.getLastReceivedLuckyMoneyInfoNode(rootNode);
    			bNewLuckyMoney=mLuckyMoney.isNewLuckyMoney(mLuckyMoney.LuckyMoneyNode, mLuckyMoney.RobbedLuckyMoneyInfoNode);
    		}
    		if(bNewLuckyMoney){
    			if(Config.bNoPayFor)//�����⸶����
    			if(!mLuckyMoney.isLuckyMoneyLei(mLuckyMoney.LuckyMoneyNode))return false;
  
    					//��������״̬��
    					Config.JobState=Config.STATE_CLICK_LUCKYMONEY;
    					ClickLuckyMoneyDelay();//�����ʱ��ʾ��
    					return true;
    			//}
    		}
    	}
        return false;
    }

  
    //����
    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private boolean UnpackLuckyMoney(AccessibilityNodeInfo rootNode) {
    	 if (rootNode == null) return false;
         speeker.speak("����Ϊ��������");
         mLuckyMoneyType=Config.TYPE_LUCKYMONEY_THUNDER;
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
         	Rect outBounds=new Rect();
         	rootNode.getBoundsInScreen(outBounds);
     		Point position=new Point();
     		position.x=outBounds.right/2;
     		position.y=outBounds.bottom*3/5;
     		QiangHongBaoService.getQiangHongBaoService().startClick(position);
     		return true;
         }
        
        mLuckyMoneyType=mReceiveJob.IsLuckyMoneyReceive(rootNode);
        if(mLuckyMoneyType==Config.TYPE_LUCKYMONEY_THUNDER){
        	//mLauncherJob.preLuckyMoneyNode=null;
        	UnpackLuckyMoneyDelay();//�����ʱ��ʾ��
        	return mReceiveJob.OpenLuckyMoney(rootNode);//���
        }
        if(mLuckyMoneyType==Config.TYPE_LUCKYMONEY_WELL){
        	if(Config.bNoPayFor){//�����⸶����
        		speeker.speak("����������");
        	}else{
            	UnpackLuckyMoneyDelay();//�����ʱ��ʾ��
            	return mReceiveJob.OpenLuckyMoney(rootNode);//���
        	}
        	//mLauncherJob.preLuckyMoneyNode=null;
        	return false;
        }
        return false;
    }
    //�����ʱ��
    private void ClickLuckyMoneyDelay() {
        //7.���ű������֣�
        //mBackgroundMusic=BackgroundMusic.getInstance(context);
        //mBackgroundMusic.playBackgroundMusic( "bg_music.mp3", true);
    	speeker.speak("���ڽ������ݼ��㣺");
    	mBackgroundMusic.playBackgroundMusic( "ml.wav", true);
		fwp.ShowFloatingWindow(); 
    	fwp.c=Config.getConfig(context).getWechatOpenDelayTime();
    	fwp.mSendMsg=Config.ACTION_CLICK_LUCKY_MONEY;
    	fwp.mShowPicType=FloatingWindowPic.SHOW_PIC_GREEN;
    	fwp.StartSwitchPics();
    	//fwp.StopSwitchPics();
    	//fwp.RemoveFloatingWindowPic();
    	//mBackgroundMusic.stopBackgroundMusic();
    }
    //�����ʱ��
    private void UnpackLuckyMoneyDelay() {
    	
    	//float f=0;
    	mWechatOpenDelayTime=Config.getConfig(context).getWechatOpenDelayTime();
    	//for(int i=0;i<mWechatOpenDelayTime;i++){
    		//handler.postDelayed(run, 10);
    	//}
    	float f=(float) (Math.random()*10000);
    	String s=String.valueOf(f);
    	Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
	    try{
	    	  Thread.sleep(10*mWechatOpenDelayTime);
	    }catch(Exception e){
	    } 
	    if(mWechatOpenDelayTime>0){
	    	f=(float) (Math.random()*10000);
	    	s=String.valueOf(f);
	    	Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
	    }

    }
    //�����Ϣչʾ��
    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void UnpackLuckyMoneyShow(AccessibilityNodeInfo rootNode) {
        if (rootNode == null) {return;}
        
        mDetailJob.LuckyMoneyDetailShow(rootNode);
        
    }
	private BroadcastReceiver ClickLuckyMoneyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            //Log.d(TAG, "receive-->" + action);
            if(Config.ACTION_CLICK_LUCKY_MONEY.equals(action)) {
            	mBackgroundMusic.stopBackgroundMusic();
            	//��������
            	if(mLuckyMoney.ClickLuckyMoney(mLuckyMoney.LuckyMoneyNode))
            		Config.JobState=Config.STATE_CLICK_LUCKYMONEY;
            	else
            		Config.JobState=Config.TYPE_LUCKYMONEY_NONE;
            }
            if(Config.ACTION_ACCESSBILITY_SERVICE_CLICK.equals(action)) {//����¼�;
    			//-------------------------��������----------------------------------------------------
            	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
				if(mCurrentUI.indexOf(VersionParam.WINDOW_LUCKYMONEY_RECEIVEUI)!=-1){
					if(Config.JobState==Config.STATE_CLICK_LUCKYMONEY){
						int count=intent.getIntExtra("count", 0);
						if(count>10){//������ˣ�û������
							speeker.speak("������ˣ�û������");
				        	AccessibilityHelper.performBack(service);
				        	Config.JobState=Config.STATE_NONE;
				        	service.closeClick();
						}
					}
				}
            }
        }
    };
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void onNotificationPosted(IStatusBarNotification sbn) {
        Notification nf = sbn.getNotification();
        String text = String.valueOf(sbn.getNotification().tickerText);
        notificationEvent(text, nf);
    }
    /** ֪ͨ���¼�*/
    private void notificationEvent(String ticker, Notification nf) {
        String text = ticker;
        int index = text.indexOf(":");
        if(index != -1) {
            text = text.substring(index + 1);
        }
        text = text.trim();
        //transferAccounts.notificationEvent(ticker, nf);
        //if(text.contains(TransferAccounts.WX_TRANSFER_ACCOUNTS_ORDER)) { //�����Ϣ
        //    newHongBaoNotification(nf);
        //}
    }

    /**��֪ͨ����Ϣ*/
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void newHongBaoNotification(Notification notification) {
    	//TransferAccounts.mWorking = true;
        //�����Ǿ�������΢�ŵ�֪ͨ����Ϣ��
        PendingIntent pendingIntent = notification.contentIntent;
        boolean lock = NotifyHelper.isLockScreen(getContext());

        if(!lock) {
            NotifyHelper.send(pendingIntent);
        } else {
            //NotifyHelper.showNotify(getContext(), String.valueOf(notification.tickerText), pendingIntent);
        }

        if(lock) {
           // NotifyHelper.playEffect(getContext(), getConfig());
        }
    }

}

