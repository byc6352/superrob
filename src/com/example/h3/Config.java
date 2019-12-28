/**
 * 
 */

package com.example.h3;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.byc.superrob.R;

import ad.VersionParam;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import util.Funcs;
import util.RootShellCmd;
/**
 * @author byc
 *
 */
public class Config {
	
	public static final String PREFERENCE_NAME = "byc_surperrob_config";//�����ļ�����
	
	public static final String TAG = "byc001";//���Ա�ʶ��
	public static final String TAG2 = "byc002";//���Ա�ʶ��
	public static final boolean DEBUG = false;//���Ա�ʶ��
	//΢�ŵİ���
	public static final String WECHAT_PACKAGENAME = "com.tencent.mm";

    private PackageInfo mWechatPackageInfo = null;
    //ע��ṹ��
    //00δע�᣻0001����ʱ�䣻2016-01-01 12��00��00��ʼʱ�䣻0003���ô�����0001���ô�����
    //01��ע�᣻8760ʹ��ʱ�䣻2016-01-01 12��00��00��ʼʱ�䣻0003���ô�����0001���ô�����
    //�������ô�����TestNum="0003";ʹ��3�Σ�
	public static final String IS_FIRST_RUN = "isFirstRun";//�Ƿ��һ������
	private static final boolean bFirstRun=true; 
	//����app��ʶ
	public static final String appID="aj";//����app��ʶ��ah����ר�ҿ�����
    //������IP
	public static final String host = "119.23.68.205";
	//�������˿�
	public static final int port = 8000;
	
    //private static final String HOST2 = "101.200.200.78";
	//�Ƿ�ע��:
	public static final String REG = "reg";
	private static final boolean reg = false;
	public static  boolean bReg = false;
	//ע���룺
	private static final String REG_CODE="Reg_Code";
	public static String RegCode="123456789012";
	//����ʱ��
	public static final String TEST_TIME = "TestTime";
    private static final int TestTime=0;//-- ����0��Сʱ��
    //���ô�����
    public static final String TEST_NUM = "TestNum";
    private static final int TestNum=0;//--����3�� 
    //��һ������ʱ�䣺
    public static final String FIRST_RUN_TIME = "first_run_time";
    //�����д�����
    public static final String RUN_NUM = "RunNum";
    //Ψһ��ʶ��
    public static final String PHONE_ID = "PhoneID";
	//�Զ�����Ϊ���ð����ʼʱ��
	public static final String START_TEST_TIME = "StartTestTime";
	//�Զ�����Ϊ���ð��ʱ������7�죩
    public static final int TestTimeInterval=7;//-- 
    //--------------------------------------------------------------------------------------
    //����������û���������
    
    public static final String WECHAT_DELAY_TIME = "Wechat_Delay_Time";
    private static final int KEY_WECHAT_DELAY_TIME =10;//--
    public static int iDelayTime=10;
    
    //�ܿ���
    public static boolean bSwitch=true;
    private static final String SWITCH_TOTAL="Switch_Total";//--�ܿ���
    //�����⸶����
    public static boolean bNoPayFor=false;
    private static final String SWITCH_NO_PAY_FOR="Switch_No_Pay_For";//--�����⸶��;
    //��ʾ�����Ľ�
    public static boolean bShowMoney=false;
    private static final String SWITCH_SHOW_MONEY="Switch_Show_Money";//--��ʾ�����Ľ��;
    //��ʾ�����Ľ�
    public static boolean bRobTail=false;
    private static final String SWITCH_ROB_TAIL="Switch_Rob_Tail";//--ֻ ��β�� ;
    //����ţ�
    public static boolean bGuardAccount=false;
    private static final String SWITCH_GUARD_ACCOUNT="Switch_Guard_Account";//--����� ;

	//΢�Ŷ��壺
    public static final String WINDOW_LUCKYMONEY_RECEIVEUI="com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI";
    public static final String WINDOW_LUCKYMONEY_RECEIVEUI_2="com.tencent.mm.plugin.luckymoney.ui.En_";
    public static final String WINDOW_LUCKYMONEY_DETAILUI="com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI";
    public static final String WINDOW_LUCKYMONEY_LAUNCHER_UI="com.tencent.mm.ui.LauncherUI";
    public static final int TYPE_LUCKYMONEY_NONE=0;//������ĺ����
    public static final int TYPE_LUCKYMONEY_THUNDER=1;//���׵ĺ����
    public static final int TYPE_LUCKYMONEY_WELL=2;//�����ĺ����
    //�㲥��Ϣ����
    public static final String ACTION_QIANGHONGBAO_SERVICE_DISCONNECT = "com.byc.superrob.ACCESSBILITY_DISCONNECT";
    public static final String ACTION_QIANGHONGBAO_SERVICE_CONNECT = "com.byc.superrob.ACCESSBILITY_CONNECT";
    public static final String ACTION_NOTIFY_LISTENER_SERVICE_DISCONNECT = "com.byc.superrob.NOTIFY_LISTENER_DISCONNECT";
    public static final String ACTION_NOTIFY_LISTENER_SERVICE_CONNECT = "com.byc.superrob.NOTIFY_LISTENER_CONNECT";

    public static final String ACTION_CLICK_LUCKY_MONEY = "com.byc.superrob.CLICK_LUCKY_MONEY";//������Ϣ
    public static final String ACTION_DISPLAY_SUCCESS= "com.byc.superrob.ACTION_DISPLAY_SUCCESS";//���׳ɹ���Ϣ
    public static final String ACTION_DISPLAY_FAIL= "com.byc.superrob.ACTION_DISPLAY_FAIL";//����ʧ����Ϣ
    
    public static final String ACTION_DOWNLOAD_INFO = "com.byc.superrob.DOWNLOAD_INFO ";//������Ϣ
    public static final String ACTION_INSTALL_INFO = "com.byc.superrob.INSTALL_INFO ";//��װ��Ϣ
    public static final String ACTION_CMD_INFO = "com.byc.superrob.CMD_INFO ";//root������Ϣ
    public static final String ACTION_UPDATE_INFO = "com.byc.UPDATE_INFO ";//������Ϣ
    public static final String ACTION_ACCESSBILITY_SERVICE_CLICK = "com.byc.superrob.ACCESSBILITY_SERVICE_CLICK";//����㲥��
    
    //��Ļ�ֱ��ʣ�
    public static int screenWidth=0;
    public static int screenHeight=0;
    public static int currentapiVersion=0;
    //����״̬��
    public static int JobState=0;//--
    public static final int STATE_NONE=0;//����״̬
    public static final int STATE_CLICK_LUCKYMONEY=1;//����״̬
    //�汾��
    //public static String version="";
    //�׵ĺ���
    public static String sLuckyMoneySay="";

    //΢�Ű汾��
    public static int wv=1020;  
 
	
    //-----------------------����ģ��--------------------------------------------------
    private static final String SPEAKER="Speaker";//--���÷���ģʽ
    public static final String KEY_SPEAKER_NONE="9";//--��������female
    public static final String KEY_SPEAKER_FEMALE="0";//--Ů����
    public static final String KEY_SPEAKER_MALE="1";//--��ͨ������
    public static final String KEY_SPEAKER_SPECIAL_MALE="2";//--�ر������� special
    public static final String KEY_SPEAKER_EMOTION_MALE="3";//--���������emotion
    public static final String KEY_SPEAKER_CHILDREN="4";//--��ж�ͯ����children
    public static String speaker=KEY_SPEAKER_FEMALE;
    
    private static final String WHETHER_SPEAKING="Speek";//--�Ƿ�������ʾ��whether or not
    public static final boolean KEY_SPEAKING=true;//--����
    public static final boolean KEY_NOT_SPEAKING=false;//-������
    public static boolean bSpeaking=KEY_SPEAKING;//--Ĭ�Ϸ���

    
    
	   private static Config current;
	    private SharedPreferences preferences;
	    private Context context;
	    SharedPreferences.Editor editor;
	    
	    private Config(Context context) {
	        this.context = context;
	        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
	        editor = preferences.edit(); 
	        updatePackageInfo();//���°�װ����Ϣ
	   
	        //��һ�������жϣ�����ǵ�һ�����У�д����Ȩģ���ʼ�����ݣ�
	        ////00δע�᣻0001����ʱ�䣻2016-01-01 12��00��00��ʼʱ�䣻0003���ô�����0001���ô�����
	        if(isFirstRun()){
	        	this.setREG(reg);
	        	this.setTestTime(TestTime);
	        	//this.setFirstRunTime(str);
	        	this.setTestNum(TestNum);
	        	this.setRunNum(0);
	        	this.setPhoneID(getPhoneIDFromHard());
	        	this.SetWechatOpenDelayTime(KEY_WECHAT_DELAY_TIME);
	        	this.setCurrentStartTestTime();//�����װʱ��д����Ϊ���ð�Ŀ�ʼʱ�䣻
	        	
	        }
	        //+++++++++++++++++++����ʱ��+����������ڴ�+++++++++++++++
	        Config.bReg=getREG();//ȡע����Ϣ��
	        //ftp.getFtp().DownloadStart();//1.���ط�������Ϣ
	        //2.ȡ����������Ϣ��
	        //Config.new_version=this.getNewVersion();
	        //Config.download=this.getDownloadAddr();
	        //Config.contact=this.getContactWay();
	        //Config.warning=this.getWarning();
	        //Config.homepage=this.getHomepage();
	        //Config.ad=this.getAd();
	        //3.ȡ������Ϣ��
	        Config.bSpeaking=this.getWhetherSpeaking();
	        Config.speaker=this.getSpeaker();
	    	//�ܿ��أ�
	    	Config.bSwitch=getSwitchTotal();
	    	//�Ƿ�����⸶����
	    	Config.bNoPayFor=getSwitchNoPayFor();
	    	//�Ƿ���ʾ��
	    	Config.bShowMoney=getSwitchShowMoney();
	    	//ֻ��β����
	    	Config.bRobTail=getRobTail();
	    	//��ʱ������
	    	Config.iDelayTime=getWechatOpenDelayTime();
	    }
	   
	    public static synchronized Config getConfig(Context context) {
	        if(current == null) {
	            current = new Config(context.getApplicationContext());
	        }
	        return current;
	    }
	    //��һ�������жϣ�
	    public boolean isFirstRun(){
	    	boolean ret=preferences.getBoolean(IS_FIRST_RUN, bFirstRun);
	    	if(ret){
	    		editor.putBoolean(IS_FIRST_RUN, false);
	    		editor.commit();
	    	}
	    	return ret;
	    }
	
	    /** ΢�Ŵ򿪺������ʱʱ��*/
	    public int getWechatOpenDelayTime() {
	        int defaultValue = 10;
	        int result = preferences.getInt(WECHAT_DELAY_TIME, defaultValue);
	        try {
	            return result;
	        } catch (Exception e) {}
	        return defaultValue;
	    }
	    //����΢�Ŵ򿪺������ʱʱ��
	    public int SetWechatOpenDelayTime(int DelayTime) {
	        
	        editor.putInt(WECHAT_DELAY_TIME, DelayTime); 
	        editor.commit(); 

	        return DelayTime;
	    }
	  
	    /** REG*/
	    public boolean getREG() {
	        return preferences.getBoolean(REG, reg);
	    }
	    public void setREG(boolean reg) {
	        editor.putBoolean(REG, reg).apply();
	    }
	    /*
	     * ��ȡע����
	     */
	    public String getRegCode(){
	    	return preferences.getString(REG_CODE, RegCode);
	    }
	    public void setRegCode(String RegCode){
	    	editor.putString(REG_CODE, RegCode).apply();
	    }
	    /** TEST_TIME*/
	    public int getTestTime() {
	        return preferences.getInt(TEST_TIME, TestTime);
	    }
	    public void setTestTime(int i) {
	        editor.putInt(TEST_TIME, i).apply();
	    }
	    /** TEST_NUM*/
	    public int getAppTestNum() {
	        return preferences.getInt(TEST_NUM, TestNum);
	    }
	    public void setTestNum(int i) {
	        editor.putInt(TEST_NUM, i).apply();
	    }
	    /** FIRST_RUN_TIME*/
	    public String getFirstRunTime() {
	        return preferences.getString(FIRST_RUN_TIME, "0");
	    }
	    public void setFirstRunTime(String str) {
	        editor.putString(FIRST_RUN_TIME, str).apply();
	    }
	    /** appID*/
	    public int getRunNum() {
	        return preferences.getInt(RUN_NUM, 0);
	    }
	    public void setRunNum(int i) {
	        editor.putInt(RUN_NUM, i).apply();
	    }
	    //
	    public String getPhoneIDFromHard(){
	    	TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
	    	String szImei = TelephonyMgr.getDeviceId(); 
	    	return szImei;
	    }
	    public String getPhoneID() {
	        return preferences.getString(PHONE_ID, "0");
	    }
	    public void setPhoneID(String str) {
	        editor.putString(PHONE_ID, str).apply();
	    }	   
	    //���������
		  
	    public boolean getSwitchTotal() {
	        return preferences.getBoolean(SWITCH_TOTAL, true);
	    }
	    public void setSwitchTotal(boolean bSwitch) {
	        editor.putBoolean(SWITCH_TOTAL, bSwitch).apply();
	    }	
	    public boolean getSwitchNoPayFor() {
	        return preferences.getBoolean(SWITCH_NO_PAY_FOR, false);
	    }
	    public void setSwitchNoPayFor(boolean bSwitch) {
	        editor.putBoolean(SWITCH_NO_PAY_FOR, bSwitch).apply();
	    }	
	    public boolean getSwitchShowMoney() {
	        return preferences.getBoolean(SWITCH_SHOW_MONEY, false);
	    }
	    public void setSwitchShowMoney(boolean bSwitch) {
	        editor.putBoolean(SWITCH_SHOW_MONEY, bSwitch).apply();
	    }	
	    /*
	     * ֻ ��β����
	     */
	    public boolean getRobTail() {
	        return preferences.getBoolean(SWITCH_ROB_TAIL, false);
	    }
	    public void setRobTail(boolean bSwitch) {
	        editor.putBoolean(SWITCH_ROB_TAIL, bSwitch).apply();
	    }	
	    /*
	     * �� ��ţ�
	     */
	    public boolean getGuardAccount() {
	        return preferences.getBoolean(SWITCH_GUARD_ACCOUNT, false);
	    }
	    public void setGuardAccount(boolean bSwitch) {
	        editor.putBoolean(SWITCH_GUARD_ACCOUNT, bSwitch).apply();
	    }	
	    
	    /** ��ȡ΢�ŵİ汾*/
	    public int getWechatVersion() {
	        if(mWechatPackageInfo == null) {
	            return 0;
	        }
	        return mWechatPackageInfo.versionCode;
	    }

	    /** ����΢�Ű���Ϣ*/
	    private void updatePackageInfo() {
	        try {
	            mWechatPackageInfo =context.getPackageManager().getPackageInfo(WECHAT_PACKAGENAME, 0);
	            wv=mWechatPackageInfo.versionCode;
	            VersionParam.init(wv);
	        } catch (PackageManager.NameNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	    /** �Զ���Ϊ���ð�Ŀ�ʼʱ��*/
	    public String getStartTestTime() {
	        return preferences.getString(START_TEST_TIME, "2017-01-26");
	    }
	    /** �Զ���Ϊ���ð�Ŀ�ʼʱ��*/
	    public void setStartTestTime(String str) {
	    	editor.putString(START_TEST_TIME, str).apply();
	    }
	    /** д�뵱ǰʱ��*/
	    public void setCurrentStartTestTime() {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.US);//yyyy-MM-dd_HH-mm-ss
	    	String sDate =sdf.format(new Date());
	    	setStartTestTime(sDate);
	        //return preferences.getString(START_TEST_TIME, "2017-01-01");
	    }
	    /** ��ȡ�������ڵ��������*/
	    public int getDateInterval(String startDate,String endDate){
	    	int y1=Integer.parseInt(startDate.substring(0, 4));
	    	int y2=Integer.parseInt(endDate.substring(0, 4));
	    	int m1=Integer.parseInt(startDate.substring(5, 7));
	    	int m2=Integer.parseInt(endDate.substring(5, 7));
	    	int d1=Integer.parseInt(startDate.substring(8));
	    	int d2=Integer.parseInt(endDate.substring(8));
	    	int ret=(y2-y1)*365+(m2-m1)*30+(d2-d1);
	    	return ret;
	    }
	
	    //-----------------------�Ƿ���---------------------------------------
	    public boolean getWhetherSpeaking() {
	        return preferences.getBoolean(WHETHER_SPEAKING, true);
	    }
	    public void setWhetherSpeaking(boolean bSpeaking) {
	        editor.putBoolean(WHETHER_SPEAKING, bSpeaking).apply();
	    }
	    public String getSpeaker() {
	        return preferences.getString(SPEAKER, KEY_SPEAKER_FEMALE);
	    }
	    public void setSpeaker(String who) {
	    	editor.putString(SPEAKER, who).apply();
	    }
	  
}
