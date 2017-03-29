package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        anywheresoftware.b4a.keywords.Common.ToastMessageShow("This application was developed with B4A trial version and should not be distributed.", true);
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = main.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.obejcts.TTS _tts1 = null;
public static anywheresoftware.b4a.objects.Timer _timerprogress = null;
public anywheresoftware.b4a.objects.CameraW _camera1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btntakepicture = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlpreview = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblfilename = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblappenddate = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtfilename = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spidate = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnreadfilename = null;
public anywheresoftware.b4a.objects.ProgressBarWrapper _pbprogress = null;
public static boolean _includedate = false;
public static String _filename = "";
public static String _currentdate = "";
public static String _errormessage = "";
public anywheresoftware.b4a.audio.Beeper _camerabeep = null;
public static int _ticknum = 0;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _radlight = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _raddark = null;
public anywheresoftware.b4a.audio.Beeper _btnbeep = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create"))
	return (String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime});
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="Activity.LoadLayout(\"Main\")";
mostCurrent._activity.LoadLayout("Main",mostCurrent.activityBA);
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="spiDate.Add(\"Append Date\")";
mostCurrent._spidate.Add("Append Date");
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="spiDate.Add(\"Do Not Append Date\")";
mostCurrent._spidate.Add("Do Not Append Date");
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="btnReadFileName.Text = \"Read filename\"";
mostCurrent._btnreadfilename.setText((Object)("Read filename"));
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="timerProgress.Initialize(\"timerProgress\", 200)";
_timerprogress.Initialize(processBA,"timerProgress",(long) (200));
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="radDark.Checked = True";
mostCurrent._raddark.setChecked(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="radDark.Visible = False";
mostCurrent._raddark.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="btnBeep.Initialize(30, 700)";
mostCurrent._btnbeep.Initialize((int) (30),(int) (700));
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_pause"))
	return (String) Debug.delegate(mostCurrent.activityBA, "activity_pause", new Object[] {_userclosed});
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="camera1.Release";
mostCurrent._camera1.Release();
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume"))
	return (String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null);
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196611;
 //BA.debugLineNum = 196611;BA.debugLine="btnTakePicture.Enabled = False";
mostCurrent._btntakepicture.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=196612;
 //BA.debugLineNum = 196612;BA.debugLine="camera1.Initialize(pnlPreview, \"Camera1\")";
mostCurrent._camera1.Initialize(mostCurrent.activityBA,(android.view.ViewGroup)(mostCurrent._pnlpreview.getObject()),"Camera1");
RDebugUtils.currentLine=196616;
 //BA.debugLineNum = 196616;BA.debugLine="If TTS1.IsInitialized = False Then";
if (_tts1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=196617;
 //BA.debugLineNum = 196617;BA.debugLine="TTS1.Initialize(\"TTS1\")";
_tts1.Initialize(processBA,"TTS1");
 };
RDebugUtils.currentLine=196620;
 //BA.debugLineNum = 196620;BA.debugLine="End Sub";
return "";
}
public static String  _btnreadfilename_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnreadfilename_click"))
	return (String) Debug.delegate(mostCurrent.activityBA, "btnreadfilename_click", null);
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub btnReadFileName_Click";
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="If fileName.Length > 0 Then";
if (mostCurrent._filename.length()>0) { 
RDebugUtils.currentLine=786438;
 //BA.debugLineNum = 786438;BA.debugLine="TTS1.Speak(fileName, True)";
_tts1.Speak(mostCurrent._filename,anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=786441;
 //BA.debugLineNum = 786441;BA.debugLine="End Sub";
return "";
}
public static String  _btntakepicture_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btntakepicture_click"))
	return (String) Debug.delegate(mostCurrent.activityBA, "btntakepicture_click", null);
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub btnTakePicture_Click";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="btnTakePicture.Enabled = False";
mostCurrent._btntakepicture.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="camera1.TakePicture";
mostCurrent._camera1.TakePicture();
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="End Sub";
return "";
}
public static String  _camera1_picturetaken(byte[] _data) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "camera1_picturetaken"))
	return (String) Debug.delegate(mostCurrent.activityBA, "camera1_picturetaken", new Object[] {_data});
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
String _mydate = "";
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub Camera1_PictureTaken (Data() As Byte)";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="camera1.StartPreview";
mostCurrent._camera1.StartPreview();
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
RDebugUtils.currentLine=720901;
 //BA.debugLineNum = 720901;BA.debugLine="errorMessage = \"\"";
mostCurrent._errormessage = "";
RDebugUtils.currentLine=720904;
 //BA.debugLineNum = 720904;BA.debugLine="cameraBeep.Initialize(300, 500)";
mostCurrent._camerabeep.Initialize((int) (300),(int) (500));
RDebugUtils.currentLine=720907;
 //BA.debugLineNum = 720907;BA.debugLine="If (txtFileName.Text.Length > 0) Then";
if ((mostCurrent._txtfilename.getText().length()>0)) { 
RDebugUtils.currentLine=720910;
 //BA.debugLineNum = 720910;BA.debugLine="If (txtFileName.Text.Contains(\"?\") Or txtFileNam";
if ((mostCurrent._txtfilename.getText().contains("?") || mostCurrent._txtfilename.getText().contains(":") || mostCurrent._txtfilename.getText().contains("\\") || mostCurrent._txtfilename.getText().contains("*") || mostCurrent._txtfilename.getText().contains("|") || mostCurrent._txtfilename.getText().contains("/") || mostCurrent._txtfilename.getText().contains("\\") || mostCurrent._txtfilename.getText().contains("<") || mostCurrent._txtfilename.getText().contains(">"))) { 
RDebugUtils.currentLine=720913;
 //BA.debugLineNum = 720913;BA.debugLine="errorMessage = \"Error! Invalid filename charact";
mostCurrent._errormessage = "Error! Invalid filename characters.";
 }else {
RDebugUtils.currentLine=720918;
 //BA.debugLineNum = 720918;BA.debugLine="fileName = txtFileName.Text";
mostCurrent._filename = mostCurrent._txtfilename.getText();
RDebugUtils.currentLine=720921;
 //BA.debugLineNum = 720921;BA.debugLine="If (spiDate.SelectedIndex = 0) Then";
if ((mostCurrent._spidate.getSelectedIndex()==0)) { 
RDebugUtils.currentLine=720923;
 //BA.debugLineNum = 720923;BA.debugLine="Dim myDate As String = DateTime.Now";
_mydate = BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
RDebugUtils.currentLine=720924;
 //BA.debugLineNum = 720924;BA.debugLine="fileName = fileName & \"_\" & myDate";
mostCurrent._filename = mostCurrent._filename+"_"+_mydate;
 };
 };
 }else {
RDebugUtils.currentLine=720933;
 //BA.debugLineNum = 720933;BA.debugLine="fileName = \"default\"";
mostCurrent._filename = "default";
 };
RDebugUtils.currentLine=720938;
 //BA.debugLineNum = 720938;BA.debugLine="If (errorMessage = \"\") Then";
if (((mostCurrent._errormessage).equals(""))) { 
RDebugUtils.currentLine=720940;
 //BA.debugLineNum = 720940;BA.debugLine="fileName = fileName & \".jpg\"";
mostCurrent._filename = mostCurrent._filename+".jpg";
RDebugUtils.currentLine=720943;
 //BA.debugLineNum = 720943;BA.debugLine="out = File.OpenOutput(File.DirRootExternal, file";
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),mostCurrent._filename,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=720944;
 //BA.debugLineNum = 720944;BA.debugLine="out.WriteBytes(Data, 0, Data.Length)";
_out.WriteBytes(_data,(int) (0),_data.length);
RDebugUtils.currentLine=720945;
 //BA.debugLineNum = 720945;BA.debugLine="out.Close";
_out.Close();
RDebugUtils.currentLine=720947;
 //BA.debugLineNum = 720947;BA.debugLine="btnTakePicture.Enabled = True";
mostCurrent._btntakepicture.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=720950;
 //BA.debugLineNum = 720950;BA.debugLine="cameraBeep.Beep";
mostCurrent._camerabeep.Beep();
RDebugUtils.currentLine=720953;
 //BA.debugLineNum = 720953;BA.debugLine="tickNum = 0";
_ticknum = (int) (0);
RDebugUtils.currentLine=720954;
 //BA.debugLineNum = 720954;BA.debugLine="timerProgress.Enabled = True";
_timerprogress.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=720955;
 //BA.debugLineNum = 720955;BA.debugLine="ToastMessageShow(\"Image saved: \" & File.Combine(";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Image saved: "+anywheresoftware.b4a.keywords.Common.File.Combine(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),mostCurrent._filename),anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=720960;
 //BA.debugLineNum = 720960;BA.debugLine="ToastMessageShow(errorMessage, True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(mostCurrent._errormessage,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=720961;
 //BA.debugLineNum = 720961;BA.debugLine="btnTakePicture.Enabled = True";
mostCurrent._btntakepicture.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=720965;
 //BA.debugLineNum = 720965;BA.debugLine="End Sub";
return "";
}
public static String  _camera1_ready(boolean _success) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "camera1_ready"))
	return (String) Debug.delegate(mostCurrent.activityBA, "camera1_ready", new Object[] {_success});
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub Camera1_Ready (Success As Boolean)";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="If Success Then";
if (_success) { 
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="camera1.StartPreview";
mostCurrent._camera1.StartPreview();
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="btnTakePicture.Enabled = True";
mostCurrent._btntakepicture.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=655365;
 //BA.debugLineNum = 655365;BA.debugLine="ToastMessageShow(\"Cannot open camera.\", True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("Cannot open camera.",anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=655367;
 //BA.debugLineNum = 655367;BA.debugLine="End Sub";
return "";
}
public static String  _raddark_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "raddark_checkedchange"))
	return (String) Debug.delegate(mostCurrent.activityBA, "raddark_checkedchange", new Object[] {_checked});
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub radDark_CheckedChange(Checked As Boolean)";
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="Activity.Color = Colors.Black";
mostCurrent._activity.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="btnBeep.Beep";
mostCurrent._btnbeep.Beep();
RDebugUtils.currentLine=1048582;
 //BA.debugLineNum = 1048582;BA.debugLine="radLight.Visible = True";
mostCurrent._radlight.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048583;
 //BA.debugLineNum = 1048583;BA.debugLine="radDark.Visible = False";
mostCurrent._raddark.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1048585;
 //BA.debugLineNum = 1048585;BA.debugLine="lblAppendDate.TextColor = Colors.White";
mostCurrent._lblappenddate.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=1048586;
 //BA.debugLineNum = 1048586;BA.debugLine="lblFileName.TextColor = Colors.White";
mostCurrent._lblfilename.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=1048588;
 //BA.debugLineNum = 1048588;BA.debugLine="spiDate.DropdownBackgroundColor = Colors.Black";
mostCurrent._spidate.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=1048589;
 //BA.debugLineNum = 1048589;BA.debugLine="spiDate.DropdownTextColor = Colors.White";
mostCurrent._spidate.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=1048593;
 //BA.debugLineNum = 1048593;BA.debugLine="End Sub";
return "";
}
public static String  _radlight_checkedchange(boolean _checked) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "radlight_checkedchange"))
	return (String) Debug.delegate(mostCurrent.activityBA, "radlight_checkedchange", new Object[] {_checked});
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Sub radLight_CheckedChange(Checked As Boolean)";
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="Activity.Color = Colors.White";
mostCurrent._activity.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=983044;
 //BA.debugLineNum = 983044;BA.debugLine="btnBeep.Beep";
mostCurrent._btnbeep.Beep();
RDebugUtils.currentLine=983046;
 //BA.debugLineNum = 983046;BA.debugLine="radDark.Visible = True";
mostCurrent._raddark.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=983047;
 //BA.debugLineNum = 983047;BA.debugLine="radLight.Visible = False";
mostCurrent._radlight.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=983049;
 //BA.debugLineNum = 983049;BA.debugLine="lblAppendDate.TextColor = Colors.Black";
mostCurrent._lblappenddate.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=983050;
 //BA.debugLineNum = 983050;BA.debugLine="lblFileName.TextColor = Colors.Black";
mostCurrent._lblfilename.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=983052;
 //BA.debugLineNum = 983052;BA.debugLine="spiDate.DropdownBackgroundColor = Colors.White";
mostCurrent._spidate.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=983053;
 //BA.debugLineNum = 983053;BA.debugLine="spiDate.DropdownTextColor = Colors.Black";
mostCurrent._spidate.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=983055;
 //BA.debugLineNum = 983055;BA.debugLine="spiDate.TextColor = Colors.White";
mostCurrent._spidate.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=983062;
 //BA.debugLineNum = 983062;BA.debugLine="End Sub";
return "";
}
public static String  _timerprogress_tick() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timerprogress_tick"))
	return (String) Debug.delegate(mostCurrent.activityBA, "timerprogress_tick", null);
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub timerProgress_tick";
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="btnTakePicture.Enabled = False";
mostCurrent._btntakepicture.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="tickNum = tickNum + 5";
_ticknum = (int) (_ticknum+5);
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="pbProgress.Enabled = True";
mostCurrent._pbprogress.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=917509;
 //BA.debugLineNum = 917509;BA.debugLine="pbProgress.Progress = tickNum";
mostCurrent._pbprogress.setProgress(_ticknum);
RDebugUtils.currentLine=917510;
 //BA.debugLineNum = 917510;BA.debugLine="pbProgress.Visible = True";
mostCurrent._pbprogress.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=917512;
 //BA.debugLineNum = 917512;BA.debugLine="If tickNum > 100 Then";
if (_ticknum>100) { 
RDebugUtils.currentLine=917514;
 //BA.debugLineNum = 917514;BA.debugLine="btnTakePicture.Enabled = True";
mostCurrent._btntakepicture.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=917515;
 //BA.debugLineNum = 917515;BA.debugLine="pbProgress.Enabled = False";
mostCurrent._pbprogress.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917516;
 //BA.debugLineNum = 917516;BA.debugLine="pbProgress.Visible = False";
mostCurrent._pbprogress.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=917520;
 //BA.debugLineNum = 917520;BA.debugLine="End Sub";
return "";
}
}