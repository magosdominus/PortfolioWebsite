package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,63);
if (RapidSub.canDelegate("activity_create")) return main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 63;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 65;BA.debugLine="Activity.LoadLayout(\"Main\")";
Debug.ShouldStop(1);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Main")),main.mostCurrent.activityBA);
 BA.debugLineNum = 66;BA.debugLine="spiDate.Add(\"Append Date\")";
Debug.ShouldStop(2);
main.mostCurrent._spidate.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("Append Date")));
 BA.debugLineNum = 67;BA.debugLine="spiDate.Add(\"Do Not Append Date\")";
Debug.ShouldStop(4);
main.mostCurrent._spidate.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("Do Not Append Date")));
 BA.debugLineNum = 69;BA.debugLine="btnReadFileName.Text = \"Read filename\"";
Debug.ShouldStop(16);
main.mostCurrent._btnreadfilename.runMethod(true,"setText",RemoteObject.createImmutable(("Read filename")));
 BA.debugLineNum = 70;BA.debugLine="timerProgress.Initialize(\"timerProgress\", 200)";
Debug.ShouldStop(32);
main._timerprogress.runVoidMethod ("Initialize",main.processBA,(Object)(BA.ObjectToString("timerProgress")),(Object)(BA.numberCast(long.class, 200)));
 BA.debugLineNum = 71;BA.debugLine="radDark.Checked = True";
Debug.ShouldStop(64);
main.mostCurrent._raddark.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 72;BA.debugLine="radDark.Visible = False";
Debug.ShouldStop(128);
main.mostCurrent._raddark.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 73;BA.debugLine="btnBeep.Initialize(30, 700)";
Debug.ShouldStop(256);
main.mostCurrent._btnbeep.runVoidMethod ("Initialize",(Object)(BA.numberCast(int.class, 30)),(Object)(BA.numberCast(int.class, 700)));
 BA.debugLineNum = 75;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,91);
if (RapidSub.canDelegate("activity_pause")) return main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 91;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 92;BA.debugLine="camera1.Release";
Debug.ShouldStop(134217728);
main.mostCurrent._camera1.runVoidMethod ("Release");
 BA.debugLineNum = 93;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,77);
if (RapidSub.canDelegate("activity_resume")) return main.remoteMe.runUserSub(false, "main","activity_resume");
 BA.debugLineNum = 77;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(4096);
 BA.debugLineNum = 80;BA.debugLine="btnTakePicture.Enabled = False";
Debug.ShouldStop(32768);
main.mostCurrent._btntakepicture.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 81;BA.debugLine="camera1.Initialize(pnlPreview, \"Camera1\")";
Debug.ShouldStop(65536);
main.mostCurrent._camera1.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)((main.mostCurrent._pnlpreview.getObject())),(Object)(RemoteObject.createImmutable("Camera1")));
 BA.debugLineNum = 85;BA.debugLine="If TTS1.IsInitialized = False Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",main._tts1.runMethod(true,"IsInitialized"),main.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 86;BA.debugLine="TTS1.Initialize(\"TTS1\")";
Debug.ShouldStop(2097152);
main._tts1.runVoidMethod ("Initialize",main.processBA,(Object)(RemoteObject.createImmutable("TTS1")));
 };
 BA.debugLineNum = 89;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnreadfilename_click() throws Exception{
try {
		Debug.PushSubsStack("btnReadFileName_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,175);
if (RapidSub.canDelegate("btnreadfilename_click")) return main.remoteMe.runUserSub(false, "main","btnreadfilename_click");
 BA.debugLineNum = 175;BA.debugLine="Sub btnReadFileName_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 178;BA.debugLine="If fileName.Length > 0 Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean(">",main.mostCurrent._filename.runMethod(true,"length"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 181;BA.debugLine="TTS1.Speak(fileName, True)";
Debug.ShouldStop(1048576);
main._tts1.runVoidMethod ("Speak",(Object)(main.mostCurrent._filename),(Object)(main.mostCurrent.__c.getField(true,"True")));
 };
 BA.debugLineNum = 184;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btntakepicture_click() throws Exception{
try {
		Debug.PushSubsStack("btnTakePicture_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,186);
if (RapidSub.canDelegate("btntakepicture_click")) return main.remoteMe.runUserSub(false, "main","btntakepicture_click");
 BA.debugLineNum = 186;BA.debugLine="Sub btnTakePicture_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 187;BA.debugLine="btnTakePicture.Enabled = False";
Debug.ShouldStop(67108864);
main.mostCurrent._btntakepicture.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 188;BA.debugLine="camera1.TakePicture";
Debug.ShouldStop(134217728);
main.mostCurrent._camera1.runVoidMethod ("TakePicture");
 BA.debugLineNum = 189;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _camera1_picturetaken(RemoteObject _data) throws Exception{
try {
		Debug.PushSubsStack("Camera1_PictureTaken (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,104);
if (RapidSub.canDelegate("camera1_picturetaken")) return main.remoteMe.runUserSub(false, "main","camera1_picturetaken", _data);
RemoteObject _out = RemoteObject.declareNull("anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper");
RemoteObject _mydate = RemoteObject.createImmutable("");
Debug.locals.put("Data", _data);
 BA.debugLineNum = 104;BA.debugLine="Sub Camera1_PictureTaken (Data() As Byte)";
Debug.ShouldStop(128);
 BA.debugLineNum = 105;BA.debugLine="camera1.StartPreview";
Debug.ShouldStop(256);
main.mostCurrent._camera1.runVoidMethod ("StartPreview");
 BA.debugLineNum = 107;BA.debugLine="Dim out As OutputStream";
Debug.ShouldStop(1024);
_out = RemoteObject.createNew ("anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper");Debug.locals.put("out", _out);
 BA.debugLineNum = 109;BA.debugLine="errorMessage = \"\"";
Debug.ShouldStop(4096);
main.mostCurrent._errormessage = BA.ObjectToString("");
 BA.debugLineNum = 112;BA.debugLine="cameraBeep.Initialize(300, 500)";
Debug.ShouldStop(32768);
main.mostCurrent._camerabeep.runVoidMethod ("Initialize",(Object)(BA.numberCast(int.class, 300)),(Object)(BA.numberCast(int.class, 500)));
 BA.debugLineNum = 115;BA.debugLine="If (txtFileName.Text.Length > 0) Then";
Debug.ShouldStop(262144);
if ((RemoteObject.solveBoolean(">",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"length"),BA.numberCast(double.class, 0)))) { 
 BA.debugLineNum = 118;BA.debugLine="If (txtFileName.Text.Contains(\"?\") Or txtFileNam";
Debug.ShouldStop(2097152);
if ((RemoteObject.solveBoolean(".",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"contains",(Object)(RemoteObject.createImmutable("?")))) || RemoteObject.solveBoolean(".",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"contains",(Object)(RemoteObject.createImmutable(":")))) || RemoteObject.solveBoolean(".",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"contains",(Object)(RemoteObject.createImmutable("\\")))) || RemoteObject.solveBoolean(".",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"contains",(Object)(RemoteObject.createImmutable("*")))) || RemoteObject.solveBoolean(".",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"contains",(Object)(RemoteObject.createImmutable("|")))) || RemoteObject.solveBoolean(".",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"contains",(Object)(RemoteObject.createImmutable("/")))) || RemoteObject.solveBoolean(".",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"contains",(Object)(RemoteObject.createImmutable("\\")))) || RemoteObject.solveBoolean(".",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"contains",(Object)(RemoteObject.createImmutable("<")))) || RemoteObject.solveBoolean(".",main.mostCurrent._txtfilename.runMethod(true,"getText").runMethod(true,"contains",(Object)(RemoteObject.createImmutable(">")))))) { 
 BA.debugLineNum = 121;BA.debugLine="errorMessage = \"Error! Invalid filename charact";
Debug.ShouldStop(16777216);
main.mostCurrent._errormessage = BA.ObjectToString("Error! Invalid filename characters.");
 }else {
 BA.debugLineNum = 126;BA.debugLine="fileName = txtFileName.Text";
Debug.ShouldStop(536870912);
main.mostCurrent._filename = main.mostCurrent._txtfilename.runMethod(true,"getText");
 BA.debugLineNum = 129;BA.debugLine="If (spiDate.SelectedIndex = 0) Then";
Debug.ShouldStop(1);
if ((RemoteObject.solveBoolean("=",main.mostCurrent._spidate.runMethod(true,"getSelectedIndex"),BA.numberCast(double.class, 0)))) { 
 BA.debugLineNum = 131;BA.debugLine="Dim myDate As String = DateTime.Now";
Debug.ShouldStop(4);
_mydate = BA.NumberToString(main.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"));Debug.locals.put("myDate", _mydate);Debug.locals.put("myDate", _mydate);
 BA.debugLineNum = 132;BA.debugLine="fileName = fileName & \"_\" & myDate";
Debug.ShouldStop(8);
main.mostCurrent._filename = RemoteObject.concat(main.mostCurrent._filename,RemoteObject.createImmutable("_"),_mydate);
 };
 };
 }else {
 BA.debugLineNum = 141;BA.debugLine="fileName = \"default\"";
Debug.ShouldStop(4096);
main.mostCurrent._filename = BA.ObjectToString("default");
 };
 BA.debugLineNum = 146;BA.debugLine="If (errorMessage = \"\") Then";
Debug.ShouldStop(131072);
if ((RemoteObject.solveBoolean("=",main.mostCurrent._errormessage,RemoteObject.createImmutable("")))) { 
 BA.debugLineNum = 148;BA.debugLine="fileName = fileName & \".jpg\"";
Debug.ShouldStop(524288);
main.mostCurrent._filename = RemoteObject.concat(main.mostCurrent._filename,RemoteObject.createImmutable(".jpg"));
 BA.debugLineNum = 151;BA.debugLine="out = File.OpenOutput(File.DirRootExternal, file";
Debug.ShouldStop(4194304);
_out = main.mostCurrent.__c.getField(false,"File").runMethod(false,"OpenOutput",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirRootExternal")),(Object)(main.mostCurrent._filename),(Object)(main.mostCurrent.__c.getField(true,"False")));Debug.locals.put("out", _out);
 BA.debugLineNum = 152;BA.debugLine="out.WriteBytes(Data, 0, Data.Length)";
Debug.ShouldStop(8388608);
_out.runVoidMethod ("WriteBytes",(Object)(_data),(Object)(BA.numberCast(int.class, 0)),(Object)(_data.getField(true,"length")));
 BA.debugLineNum = 153;BA.debugLine="out.Close";
Debug.ShouldStop(16777216);
_out.runVoidMethod ("Close");
 BA.debugLineNum = 155;BA.debugLine="btnTakePicture.Enabled = True";
Debug.ShouldStop(67108864);
main.mostCurrent._btntakepicture.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 158;BA.debugLine="cameraBeep.Beep";
Debug.ShouldStop(536870912);
main.mostCurrent._camerabeep.runVoidMethod ("Beep");
 BA.debugLineNum = 161;BA.debugLine="tickNum = 0";
Debug.ShouldStop(1);
main._ticknum = BA.numberCast(int.class, 0);
 BA.debugLineNum = 162;BA.debugLine="timerProgress.Enabled = True";
Debug.ShouldStop(2);
main._timerprogress.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 163;BA.debugLine="ToastMessageShow(\"Image saved: \" & File.Combine(";
Debug.ShouldStop(4);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(RemoteObject.concat(RemoteObject.createImmutable("Image saved: "),main.mostCurrent.__c.getField(false,"File").runMethod(true,"Combine",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirRootExternal")),(Object)(main.mostCurrent._filename)))),(Object)(main.mostCurrent.__c.getField(true,"True")));
 }else {
 BA.debugLineNum = 168;BA.debugLine="ToastMessageShow(errorMessage, True)";
Debug.ShouldStop(128);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(main.mostCurrent._errormessage),(Object)(main.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 169;BA.debugLine="btnTakePicture.Enabled = True";
Debug.ShouldStop(256);
main.mostCurrent._btntakepicture.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 };
 BA.debugLineNum = 173;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _camera1_ready(RemoteObject _success) throws Exception{
try {
		Debug.PushSubsStack("Camera1_Ready (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,95);
if (RapidSub.canDelegate("camera1_ready")) return main.remoteMe.runUserSub(false, "main","camera1_ready", _success);
Debug.locals.put("Success", _success);
 BA.debugLineNum = 95;BA.debugLine="Sub Camera1_Ready (Success As Boolean)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 96;BA.debugLine="If Success Then";
Debug.ShouldStop(-2147483648);
if (_success.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 97;BA.debugLine="camera1.StartPreview";
Debug.ShouldStop(1);
main.mostCurrent._camera1.runVoidMethod ("StartPreview");
 BA.debugLineNum = 98;BA.debugLine="btnTakePicture.Enabled = True";
Debug.ShouldStop(2);
main.mostCurrent._btntakepicture.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 }else {
 BA.debugLineNum = 100;BA.debugLine="ToastMessageShow(\"Cannot open camera.\", True)";
Debug.ShouldStop(8);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToString("Cannot open camera.")),(Object)(main.mostCurrent.__c.getField(true,"True")));
 };
 BA.debugLineNum = 102;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 29;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 33;BA.debugLine="Dim camera1 As Camera";
main.mostCurrent._camera1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.CameraW");
 //BA.debugLineNum = 34;BA.debugLine="Dim btnTakePicture As Button";
main.mostCurrent._btntakepicture = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Dim pnlPreview As Panel";
main.mostCurrent._pnlpreview = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Dim lblFileName As Label";
main.mostCurrent._lblfilename = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Dim lblAppendDate As Label";
main.mostCurrent._lblappenddate = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Dim txtFileName As EditText";
main.mostCurrent._txtfilename = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Dim spiDate As Spinner";
main.mostCurrent._spidate = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Dim btnReadFileName As Button";
main.mostCurrent._btnreadfilename = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 43;BA.debugLine="Dim pbProgress As ProgressBar";
main.mostCurrent._pbprogress = RemoteObject.createNew ("anywheresoftware.b4a.objects.ProgressBarWrapper");
 //BA.debugLineNum = 45;BA.debugLine="Public includeDate As Boolean";
main._includedate = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 46;BA.debugLine="Public fileName As String";
main.mostCurrent._filename = RemoteObject.createImmutable("");
 //BA.debugLineNum = 47;BA.debugLine="Public currentDate As String";
main.mostCurrent._currentdate = RemoteObject.createImmutable("");
 //BA.debugLineNum = 48;BA.debugLine="Public errorMessage As String = \"\"";
main.mostCurrent._errormessage = BA.ObjectToString("");
 //BA.debugLineNum = 50;BA.debugLine="Public cameraBeep As Beeper";
main.mostCurrent._camerabeep = RemoteObject.createNew ("anywheresoftware.b4a.audio.Beeper");
 //BA.debugLineNum = 52;BA.debugLine="Public tickNum As Int";
main._ticknum = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 55;BA.debugLine="Private radLight As RadioButton";
main.mostCurrent._radlight = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 56;BA.debugLine="Private radDark As RadioButton";
main.mostCurrent._raddark = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
 //BA.debugLineNum = 58;BA.debugLine="Public btnBeep As Beeper";
main.mostCurrent._btnbeep = RemoteObject.createNew ("anywheresoftware.b4a.audio.Beeper");
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 23;BA.debugLine="Dim TTS1 As TTS";
main._tts1 = RemoteObject.createNew ("anywheresoftware.b4a.obejcts.TTS");
 //BA.debugLineNum = 24;BA.debugLine="Dim timerProgress As Timer";
main._timerprogress = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _raddark_checkedchange(RemoteObject _checked) throws Exception{
try {
		Debug.PushSubsStack("radDark_CheckedChange (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,237);
if (RapidSub.canDelegate("raddark_checkedchange")) return main.remoteMe.runUserSub(false, "main","raddark_checkedchange", _checked);
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 237;BA.debugLine="Sub radDark_CheckedChange(Checked As Boolean)";
Debug.ShouldStop(4096);
 BA.debugLineNum = 239;BA.debugLine="Activity.Color = Colors.Black";
Debug.ShouldStop(16384);
main.mostCurrent._activity.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 241;BA.debugLine="btnBeep.Beep";
Debug.ShouldStop(65536);
main.mostCurrent._btnbeep.runVoidMethod ("Beep");
 BA.debugLineNum = 243;BA.debugLine="radLight.Visible = True";
Debug.ShouldStop(262144);
main.mostCurrent._radlight.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 244;BA.debugLine="radDark.Visible = False";
Debug.ShouldStop(524288);
main.mostCurrent._raddark.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 246;BA.debugLine="lblAppendDate.TextColor = Colors.White";
Debug.ShouldStop(2097152);
main.mostCurrent._lblappenddate.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 247;BA.debugLine="lblFileName.TextColor = Colors.White";
Debug.ShouldStop(4194304);
main.mostCurrent._lblfilename.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 249;BA.debugLine="spiDate.DropdownBackgroundColor = Colors.Black";
Debug.ShouldStop(16777216);
main.mostCurrent._spidate.runMethod(true,"setDropdownBackgroundColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 250;BA.debugLine="spiDate.DropdownTextColor = Colors.White";
Debug.ShouldStop(33554432);
main.mostCurrent._spidate.runMethod(true,"setDropdownTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 254;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _radlight_checkedchange(RemoteObject _checked) throws Exception{
try {
		Debug.PushSubsStack("radLight_CheckedChange (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,211);
if (RapidSub.canDelegate("radlight_checkedchange")) return main.remoteMe.runUserSub(false, "main","radlight_checkedchange", _checked);
Debug.locals.put("Checked", _checked);
 BA.debugLineNum = 211;BA.debugLine="Sub radLight_CheckedChange(Checked As Boolean)";
Debug.ShouldStop(262144);
 BA.debugLineNum = 213;BA.debugLine="Activity.Color = Colors.White";
Debug.ShouldStop(1048576);
main.mostCurrent._activity.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 215;BA.debugLine="btnBeep.Beep";
Debug.ShouldStop(4194304);
main.mostCurrent._btnbeep.runVoidMethod ("Beep");
 BA.debugLineNum = 217;BA.debugLine="radDark.Visible = True";
Debug.ShouldStop(16777216);
main.mostCurrent._raddark.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 218;BA.debugLine="radLight.Visible = False";
Debug.ShouldStop(33554432);
main.mostCurrent._radlight.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 220;BA.debugLine="lblAppendDate.TextColor = Colors.Black";
Debug.ShouldStop(134217728);
main.mostCurrent._lblappenddate.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 221;BA.debugLine="lblFileName.TextColor = Colors.Black";
Debug.ShouldStop(268435456);
main.mostCurrent._lblfilename.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 223;BA.debugLine="spiDate.DropdownBackgroundColor = Colors.White";
Debug.ShouldStop(1073741824);
main.mostCurrent._spidate.runMethod(true,"setDropdownBackgroundColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 224;BA.debugLine="spiDate.DropdownTextColor = Colors.Black";
Debug.ShouldStop(-2147483648);
main.mostCurrent._spidate.runMethod(true,"setDropdownTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 226;BA.debugLine="spiDate.TextColor = Colors.White";
Debug.ShouldStop(2);
main.mostCurrent._spidate.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 233;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _timerprogress_tick() throws Exception{
try {
		Debug.PushSubsStack("timerProgress_tick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,191);
if (RapidSub.canDelegate("timerprogress_tick")) return main.remoteMe.runUserSub(false, "main","timerprogress_tick");
 BA.debugLineNum = 191;BA.debugLine="Sub timerProgress_tick";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 193;BA.debugLine="btnTakePicture.Enabled = False";
Debug.ShouldStop(1);
main.mostCurrent._btntakepicture.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 194;BA.debugLine="tickNum = tickNum + 5";
Debug.ShouldStop(2);
main._ticknum = RemoteObject.solve(new RemoteObject[] {main._ticknum,RemoteObject.createImmutable(5)}, "+",1, 1);
 BA.debugLineNum = 195;BA.debugLine="pbProgress.Enabled = True";
Debug.ShouldStop(4);
main.mostCurrent._pbprogress.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 196;BA.debugLine="pbProgress.Progress = tickNum";
Debug.ShouldStop(8);
main.mostCurrent._pbprogress.runMethod(true,"setProgress",main._ticknum);
 BA.debugLineNum = 197;BA.debugLine="pbProgress.Visible = True";
Debug.ShouldStop(16);
main.mostCurrent._pbprogress.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 199;BA.debugLine="If tickNum > 100 Then";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean(">",main._ticknum,BA.numberCast(double.class, 100))) { 
 BA.debugLineNum = 201;BA.debugLine="btnTakePicture.Enabled = True";
Debug.ShouldStop(256);
main.mostCurrent._btntakepicture.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 202;BA.debugLine="pbProgress.Enabled = False";
Debug.ShouldStop(512);
main.mostCurrent._pbprogress.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 203;BA.debugLine="pbProgress.Visible = False";
Debug.ShouldStop(1024);
main.mostCurrent._pbprogress.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"False"));
 };
 BA.debugLineNum = 207;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
}