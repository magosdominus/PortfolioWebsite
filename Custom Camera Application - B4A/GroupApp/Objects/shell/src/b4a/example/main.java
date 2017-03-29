
package b4a.example;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class main implements IRemote{
	public static main mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public main() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("main"), "b4a.example.main");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, main.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _tts1 = RemoteObject.declareNull("anywheresoftware.b4a.obejcts.TTS");
public static RemoteObject _timerprogress = RemoteObject.declareNull("anywheresoftware.b4a.objects.Timer");
public static RemoteObject _camera1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.CameraW");
public static RemoteObject _btntakepicture = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _pnlpreview = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _lblfilename = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _lblappenddate = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _txtfilename = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _spidate = RemoteObject.declareNull("anywheresoftware.b4a.objects.SpinnerWrapper");
public static RemoteObject _btnreadfilename = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _pbprogress = RemoteObject.declareNull("anywheresoftware.b4a.objects.ProgressBarWrapper");
public static RemoteObject _includedate = RemoteObject.createImmutable(false);
public static RemoteObject _filename = RemoteObject.createImmutable("");
public static RemoteObject _currentdate = RemoteObject.createImmutable("");
public static RemoteObject _errormessage = RemoteObject.createImmutable("");
public static RemoteObject _camerabeep = RemoteObject.declareNull("anywheresoftware.b4a.audio.Beeper");
public static RemoteObject _ticknum = RemoteObject.createImmutable(0);
public static RemoteObject _radlight = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
public static RemoteObject _raddark = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper");
public static RemoteObject _btnbeep = RemoteObject.declareNull("anywheresoftware.b4a.audio.Beeper");
public static b4a.example.starter _starter = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",main.mostCurrent._activity,"btnBeep",main.mostCurrent._btnbeep,"btnReadFileName",main.mostCurrent._btnreadfilename,"btnTakePicture",main.mostCurrent._btntakepicture,"camera1",main.mostCurrent._camera1,"cameraBeep",main.mostCurrent._camerabeep,"currentDate",main.mostCurrent._currentdate,"errorMessage",main.mostCurrent._errormessage,"fileName",main.mostCurrent._filename,"includeDate",main._includedate,"lblAppendDate",main.mostCurrent._lblappenddate,"lblFileName",main.mostCurrent._lblfilename,"pbProgress",main.mostCurrent._pbprogress,"pnlPreview",main.mostCurrent._pnlpreview,"radDark",main.mostCurrent._raddark,"radLight",main.mostCurrent._radlight,"spiDate",main.mostCurrent._spidate,"Starter",Debug.moduleToString(b4a.example.starter.class),"tickNum",main._ticknum,"timerProgress",main._timerprogress,"TTS1",main._tts1,"txtFileName",main.mostCurrent._txtfilename};
}
}