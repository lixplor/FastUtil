package cn.fantasymaker.anutildemo;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.fantasymaker.fmutils.utils.builders.DirectoryUtil;
import cn.fantasymaker.fmutils.utils.builders.HandlerBuilder;
import cn.fantasymaker.fmutils.utils.builders.IntentUtil;
import cn.fantasymaker.fmutils.utils.calculate.EncryptUtil;
import cn.fantasymaker.fmutils.utils.control.AlarmUtil;
import cn.fantasymaker.fmutils.utils.control.BatteryUtil;
import cn.fantasymaker.fmutils.utils.control.BrightnessUtil;
import cn.fantasymaker.fmutils.utils.control.CalendarUtil;
import cn.fantasymaker.fmutils.utils.control.CameraUtil;
import cn.fantasymaker.fmutils.utils.control.ClipboardUtil;
import cn.fantasymaker.fmutils.utils.control.DeviceUtil;
import cn.fantasymaker.fmutils.utils.control.FeatureUtil;
import cn.fantasymaker.fmutils.utils.control.FlashlightUtil;
import cn.fantasymaker.fmutils.utils.control.GpsUtil;
import cn.fantasymaker.fmutils.utils.control.KeyboardUtil;
import cn.fantasymaker.fmutils.utils.control.NetworkUtil;
import cn.fantasymaker.fmutils.utils.control.NotificationUtil;
import cn.fantasymaker.fmutils.utils.control.SensorUtil;
import cn.fantasymaker.fmutils.utils.control.SettingUtil;
import cn.fantasymaker.fmutils.utils.control.ShareUtil;
import cn.fantasymaker.fmutils.utils.control.SmsUtil;
import cn.fantasymaker.fmutils.utils.control.ToastUtil;
import cn.fantasymaker.fmutils.utils.control.VibratorUtil;
import cn.fantasymaker.fmutils.utils.control.VolumeUtil;
import cn.fantasymaker.fmutils.utils.develop.AsyncTaskUtil;
import cn.fantasymaker.fmutils.utils.develop.LogUtil;
import cn.fantasymaker.fmutils.utils.runtime.AppUtil;
import cn.fantasymaker.fmutils.utils.runtime.DatabaseUtil;
import cn.fantasymaker.fmutils.utils.runtime.ProcessUtil;
import cn.fantasymaker.fmutils.utils.runtime.ShellCmdUtil;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLlRoot;
    private TextView mTv;
    private Button mBtnRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mTv = (TextView) findViewById(R.id.tv);
        mBtnRun = (Button) findViewById(R.id.btn_run);
        mBtnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testMethods();
            }
        });
    }

    private void testMethods() {
        LogUtil.showLog = true;
        LogUtil.i("Start running test methods");
//        testIntentUtil();

//        flashLight();


//        testVibratorUtil();

//        testSensor();

//        LogUtil.d("Network type=" + SystemUtil.getNetworkType()
//                + "\nLocal ip=" + SystemUtil.getLocalIP()
//                + "\nWifi ip=" + SystemUtil.getWifiIP()
//        );

//        LogUtil.d(DeviceUtil.getProductName());

//        testBatteryUtil();

//        testBrightness();

//        testVolume();

//        testProcess();

//        LogUtil.d("root=" + ShellCmdUtil.checkRootPermission());

//        testNotification();

//        testAsyncTaskUtil();

//        testDatabaseUtil();

//        testFeatureUtil();

        /*String sign = AppUtil.getMD5Sign(getPackageName());
        LogUtil.d(sign);*/


//        test3DesEnc();

//        testPbeEnc();


//        testRsaSign();


//        testRsaEnc();

//        testMorseEncode();

//        testAlarm();

//        testCalendar();

//        testClipboard();

//        testTakePhoto();

//        testTimelapse();

//        testCamera();

//        testHandlerUtil();

//        testKeyboardUtil();

//        testNetworkUtil();

//        testSettingUtil();

//        testGpsUtil();

//        testShareUtil();

//        testSmsUtil();

//        testScreenRecord();

        ShellCmdUtil.CommandResult commandResult = ShellCmdUtil.execCommand(ShellCmdUtil.COMMAND_ADB_STOP, false);
        LogUtil.d(commandResult.toString());
        ToastUtil.toastLong(commandResult.toString());

//        ShellCmdUtil.CommandResult commandResult = ShellCmdUtil.execCommand("echo hello", false);
//        LogUtil.d("result=" + commandResult);

//        List<ContactUtil.ContactBean> allContact = ContactUtil.getAllContact();
//        LogUtil.d("contacts size =" + allContact.size());

    }

    private void testScreenRecord() {
        //        String path = DirectoryUtil.getExternalDownloadDirPath() + "/screen-record.mp4";
        String path = DirectoryUtil.getExternalDownloadDirPath() + "/screen-shot.png";
//        final String cmd = String.format(ShellCmdUtil.COMMAND_SCREENRECORD, "10", "720x1280", "6000000", path);
        final String cmd = String.format(ShellCmdUtil.COMMAND_SCREENSHOT, path);
        LogUtil.d("cmd=" + cmd);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShellCmdUtil.CommandResult commandResult = ShellCmdUtil.execCommand(cmd, false);
                LogUtil.d(commandResult.toString());
            }
        }).start();
    }

    private void testVibratorUtil() {
        VibratorUtil.vibrate(new long[]{0L, 50L, 1000L, 100L, 1000L, 1000L, 1000L}, -1);
    }

    private void testIntentUtil() {
        startActivity(IntentUtil.getDialIntent(""));
        startActivity(IntentUtil.getCallIntent("13311112222"));
        startActivity(IntentUtil.getCallIntent("11"));
        startActivity(IntentUtil.getContactIntent());
        startActivity(IntentUtil.getBrowserIntent("hahah", "http://www.baidu.com"));
        startActivity(IntentUtil.getMapIntent("map", new double[]{116.420366, 39.976667}));
        startActivity(IntentUtil.getBaiduMapIntent("map", new double[]{116.420366, 39.976667}));
        startActivity(IntentUtil.getWebSearchIntent("hello"));
        startActivity(IntentUtil.getEmailIntent(null, new String[]{"me@fantasymaker.cn"}, null, "test email", "hello world!"));

        startActivity(IntentUtil.getUserDictSettingIntent());

        String phone1Number = DeviceUtil.getPhone1Number();
        startActivity(IntentUtil.getSmsIntent("没有填写电话号码的短信"));
        startActivity(IntentUtil.getSmsIntent(phone1Number, "填写了电话号码的短信"));

        startActivity(IntentUtil.getShareIntent("分享哦", "这是要分享的文字"));

        startActivity(IntentUtil.getMarketIntent(this.getPackageName()));

        startActivity(IntentUtil.getAudioRecordIntent());
        startActivity(IntentUtil.getVideoCaptureIntent(null));
        startActivity(IntentUtil.getImageCaptureIntent(null));
        startActivity(IntentUtil.getGalleryIntent());
        startActivity(IntentUtil.getGalleryChooserIntent(""));

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/1461596291674.jpg";
        startActivity(IntentUtil.getCropIntent(Uri.fromFile(new File(filePath))));
    }

    private void testSmsUtil() {
//        String line1Number = DeviceUtil.getPhone1Number();
//        SmsUtil.sendSmsSilently(line1Number, "现在时间是" + System.currentTimeMillis());
//        SmsUtil.registerSmsReceiver(new SmsUtil.SmsReceiver.OnSmsReceivedListener() {
//            @Override
//            public void onSmsReceived(SmsMessage[] smsMessages) {
//                String from = "";
//                String msg = "";
//                for(SmsMessage message : smsMessages){
//                    from = message.getOriginatingAddress();
//                    msg += message.getMessageBody();
//                }
//                LogUtil.i("from=" + from + " | msg=" + msg);
//            }
//        });

        List<SmsUtil.SmsBean> list = SmsUtil.getAllSms();
        SmsUtil.SmsBean smsBean = list.get(new Random().nextInt(list.size()));
        LogUtil.d("size=" + list.size() + "\n"
                + "random sms="
                + smsBean.getAddress() + " | "
                + smsBean.getDate() + " | "
                + smsBean.getBody() + " | "
                + smsBean.getType()
        );
    }

    private void testShareUtil() {
        List<PackageInfo> installedPackages = AppUtil.getInstalledPackages(0);
//        for(PackageInfo packageInfo : installedPackages){
//            try {
//                LogUtil.d(
//                        "name=" + AppUtil.getApplicationName(packageInfo.packageName) + "\n"
//                        + " | path=" + AppUtil.getInstallPath(packageInfo.packageName) + "\n"
//                        + " | size=" + AppUtil.getApkSize(packageInfo.packageName) + "\n"
//                );
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
        Random random = new Random();
        String packageName = installedPackages.get(random.nextInt(installedPackages.size())).packageName;
        try {
            ShareUtil.shareApk("分享应用", this, packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testGpsUtil() {
//        GpsUtil.goToGpsSetting();
        GpsUtil.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1f, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LogUtil.d("onLocationChanged" + "\n"
                        + " | provider=" + location.getProvider() + "\n"
                        + " | time=" + location.getTime() + "\n"
                        + " | elapsedRealtimeNanos=" + location.getElapsedRealtimeNanos() + "\n"
                        + " | accuracy=" + location.getAccuracy() + "\n"
                        + " | bearing=" + location.getBearing() + "\n"
                        + " | altitude=" + location.getAltitude() + "\n"
                        + " | latitude=" + location.getLatitude() + "\n"
                        + " | longitude=" + location.getLongitude() + "\n"
                        + " | speed=" + location.getSpeed() + "\n"
                );
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                LogUtil.d("onStatusChanged=" + provider + " | status=" + status);
            }

            @Override
            public void onProviderEnabled(String provider) {
                LogUtil.d("onProviderEnabled=" + provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                LogUtil.d("onProviderEnabled=" + provider);
            }
        });
    }

    private void testSettingUtil() {
        SettingUtil.putInt(Settings.Global.MODE_RINGER, 2);
        int mode = SettingUtil.getInt(Settings.Global.MODE_RINGER, -999);
        LogUtil.d("mode=" + mode);
    }

    private void testNetworkUtil() {
//        NetworkUtil.enableWifi(!NetworkUtil.isWifiEnabled());
//        NetworkUtil.enableMobileNetwork(!NetworkUtil.isMobileNetworkEnabled());
        LogUtil.d("wifi ip=" + NetworkUtil.getWifiIP() + " | local ip=" + NetworkUtil.getLocalIP());
    }

    private void testKeyboardUtil() {
        KeyboardUtil.show(mTv);
        KeyboardUtil.hide(mTv);
    }

    private void testHandlerUtil() {
        HandlerBuilder<MainActivity> handlerBuilder = new HandlerBuilder<>(this);
        handlerBuilder.setOnHandlerMessageListener(new HandlerBuilder.OnHandlerMessageListener<MainActivity>() {
            @Override
            public void onHandleMessage(Message msg, MainActivity mainActivity) {
                LogUtil.d(msg.what + " | " + msg.obj);
            }
        });
        Message message = Message.obtain();
        message.what = 999;
        message.obj = "测试消息";
        handlerBuilder.sendMessageDelayed(message, 5000);
    }

    private void testCamera() {
        try {
            CameraUtil.record(DirectoryUtil.getExternalDownloadDirPath() + "/video.mp4",
                    1280,
                    720,
                    CameraUtil.BITRATE_AUDIO_16K_TEL,
                    1024 * 512,
                    30,
                    new Surface(new SurfaceTexture(0))
            );
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1000 * 10);
                    CameraUtil.releaseMediaRecoder();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testTimelapse() {
        try {
            CameraUtil.recordTimelapse(DirectoryUtil.getExternalDownloadDirPath() + "/video.mp4",
                    new Surface(new SurfaceTexture(0)),
                    CameraUtil.CAMERA_ID_FRONT,
                    1f,
                    CamcorderProfile.QUALITY_TIME_LAPSE_HIGH
            );
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1000 * 60 * 1);
                    CameraUtil.releaseMediaRecoder();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testTakePhoto() {
        SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        if (CameraUtil.openCamera(CameraUtil.CAMERA_ID_BACK) != null) {
            if (CameraUtil.canDisableSound(CameraUtil.getCameraInfo(CameraUtil.getBackCameraId()))) {
                CameraUtil.enableSound(false);
                LogUtil.d("允许关闭声音");
            } else {
                LogUtil.d("不允许关闭声音");
            }
            try {
                CameraUtil.takePicture(
                        surfaceTexture,
                        new Camera.ShutterCallback() {
                            @Override
                            public void onShutter() {
                                LogUtil.d("onShutter");
                            }
                        },
                        new Camera.PictureCallback() {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera) {
                                LogUtil.d("PictureCallback");

                            }
                        },
                        new Camera.PictureCallback() {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera) {
                                LogUtil.d("postview");

                            }
                        },
                        new Camera.PictureCallback() {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera) {
                                LogUtil.d("jpeg");
                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                ImageView imageView = new ImageView(MainActivity.this);
                                imageView.setImageBitmap(bitmap);
                                mLlRoot.addView(imageView);
                                CameraUtil.releaseCamera();
                            }
                        }
                );
            } catch (Exception e) {

            }
        } else {
            LogUtil.d("front fail");
        }
    }

    private void testClipboard() {
        ClipboardUtil.addChangeListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                String type = ClipboardUtil.getMimeType(0);
                CharSequence text = ClipboardUtil.getHtml();
                LogUtil.d("type=" + type + " | clipboard=" + text);
            }
        });
    }

    private void testCalendar() {
        ContentValues values = null;
        CalendarUtil.addEvent(values);
    }

    private void testAlarm() {
        Date date = new Date(2016 - 1900, 8 - 1, 22, 20, 24, 0);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_ONE_SHOT);
        AlarmUtil.set(AlarmManager.RTC_WAKEUP, date.getTime(), pendingIntent);
    }

    private void testMorseEncode() {
        String text = "TestMorseEncode";
        String morse = EncryptUtil.morseEncode(text, "/");
        LogUtil.d("ori=" + text + "\n"
                + "morse=" + morse + "\n"
                + "decode=" + EncryptUtil.morseDecode(morse, "/")
        );
    }

    private void testRsaSign() {
        String text = "测试rsa: 私钥加密, 公钥解密 - 签名";
        EncryptUtil.XsaKeyPair keyPair = null;
        try {
            keyPair = EncryptUtil.genRsaKeypair(1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] privateKey = keyPair.getPrivateKeyBytes();
        byte[] publicKey = keyPair.getPublicKeyBytes();
        byte[] encText = null;
        try {
            encText = EncryptUtil.rsaPrivateEncrypt(privateKey, text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String base64Text = EncryptUtil.base64EncodeToString(encText);
        String base64PublicKey = EncryptUtil.base64EncodeToString(publicKey);
        //发送方发出密文和公钥

        //接收方解密
        byte[] decPublicKey = EncryptUtil.base64Decode(base64PublicKey);
        byte[] decText = EncryptUtil.base64Decode(base64Text);
        byte[] decbytes = null;
        try {
            decbytes = EncryptUtil.rsaPublicDecrypt(decPublicKey, decText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String dec = new String(decbytes);

        LogUtil.d(
                "O txt=" + text + "\n"
                        + "S txt=" + base64Text + "\n"
                        + "S pub=" + base64PublicKey + "\n"
                        + "R txt=" + dec + "\n"
        );
    }

    private void testRsaEnc() {
        String text = "测试rsa: 公钥加密, 私钥解密 - 加密";
        EncryptUtil.XsaKeyPair keyPair = null;
        try {
            keyPair = EncryptUtil.genRsaKeypair(1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] privateKey = keyPair.getPrivateKeyBytes();
        byte[] publicKey = keyPair.getPublicKeyBytes();
        //(接收方)解密方生成密钥对, 私钥自己留, 公钥公开发给别人, 别人只能加密, 不能解密, 起到加密作用
        //接收方将公钥发给发送方
        String base64PublicKey = EncryptUtil.base64EncodeToString(publicKey);

        //发送方收到公钥, 使用公钥加密
        byte[] decPublicKey = EncryptUtil.base64Decode(base64PublicKey);
        byte[] encText = null;
        try {
            encText = EncryptUtil.rsaPublicEncrypt(decPublicKey, text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String base64Text = EncryptUtil.base64EncodeToString(encText);
        //发送方发出密文

        //接收方用私钥解密
        byte[] decText = EncryptUtil.base64Decode(base64Text);
        byte[] decbytes = null;
        try {
            decbytes = EncryptUtil.rsaPrivateDecrypt(privateKey, decText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String dec = new String(decbytes);

        LogUtil.d(
                "R pub=" + base64PublicKey + "\n"
                        + "S ori=" + text + "\n"
                        + "S txt=" + base64Text + "\n"
                        + "R txt=" + dec + "\n"
        );
    }

    private void test3DesEnc() {
        String text = "测试3des加密";
        byte[] keyBytes = null;
        byte[] enc = null;
        byte[] dec = null;
        //发送方生成key, 加密原文得到密文
        try {
            keyBytes = EncryptUtil.genKey(EncryptUtil.TYPE_3DES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            enc = EncryptUtil.tripleDesEncrypt(
                    EncryptUtil.CIPHER_3DES_ECB_PKCS5,
                    keyBytes,
                    text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String base64Key = EncryptUtil.base64EncodeToString(keyBytes);
        String base64Text = EncryptUtil.base64EncodeToString(enc);
        //--发送方发出key和密文


        //接收方收到key和密文
        byte[] base64DecKey = EncryptUtil.base64Decode(base64Key);
        byte[] base64DecText = EncryptUtil.base64Decode(base64Text);
        try {
            dec = EncryptUtil.tripleDesDecrypt(
                    EncryptUtil.CIPHER_3DES_ECB_PKCS5,
                    base64DecKey,
                    base64DecText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.d("O txt=" + text + "\n"
                + "S key=" + base64Key + "\n"
                + "S txt=" + base64Text + "\n"
                + "R txt=" + new String(dec));
    }

    private void testPbeEnc() {
        String text = "测试PBE加密";

        //创建密码和盐
        String pwd = "密码";
        byte[] saltBytes = EncryptUtil.genSalt(8);

        byte[] encTextBytes = null;
        try {
            encTextBytes = EncryptUtil.pbeEncrypt(
                    EncryptUtil.TYPE_PBE_MD5_RC2,
                    saltBytes,
                    pwd.toCharArray(),
                    text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String base64EncSalt = EncryptUtil.base64EncodeToString(saltBytes);
        String base64EncText = EncryptUtil.base64EncodeToString(encTextBytes);
        //发送密码, 盐, 密文给对方

        //接收方
        byte[] decSalt = EncryptUtil.base64Decode(base64EncSalt);
        byte[] decText = EncryptUtil.base64Decode(base64EncText);
        char[] decPwd = pwd.toCharArray();
        byte[] dec = null;
        try {
            dec = EncryptUtil.pbeDecrypt(
                    EncryptUtil.TYPE_PBE_MD5_RC2,
                    decSalt,
                    decPwd,
                    decText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.d("O txt=" + text + "\n"
                + "S salt=" + base64EncSalt + "\n"
                + "S txt=" + base64EncText + "\n"
                + "R txt=" + new String(dec));
    }

    private void testFeatureUtil() {
        FeatureInfo[] allFeatures = FeatureUtil.getAllFeatures();
        for (FeatureInfo featureInfo : allFeatures) {
            LogUtil.d(featureInfo.toString());
        }
    }

    private void testDatabaseUtil() {
        try {
            String sql = DatabaseUtil.generateSql(TestBean.class);
            LogUtil.d(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testAsyncTaskUtil() {
        AsyncTaskUtil.run(new AsyncTaskUtil.SimpleAsyncTask() {
            @Override
            public void preThreadTask() {
                LogUtil.d("preThreadTask");
            }

            @Override
            public void inThreadTask() {
                LogUtil.d("inThreadTask");
            }

            @Override
            public void afterThreadTask() {
                LogUtil.d("afterThreadTask");
            }
        });
    }

    private void testNotification() {
        NotificationUtil.startNotificationListenerService(new NotificationUtil.OnNotificationEventListener() {
            @Override
            public void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationUtil.CustomNotification customNotification) {
                LogUtil.d(customNotification.toString());
            }

            @Override
            public void onNotificationRemoved(StatusBarNotification statusBarNotification) {

            }
        });
        ArrayList<NotificationUtil.CustomNotification> activeNotificationBeans = NotificationUtil.getActiveNotificationBeans();
        for (NotificationUtil.CustomNotification customNotification : activeNotificationBeans) {
            LogUtil.d(customNotification.toString());
        }
    }

    private void testProcess() {
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = ProcessUtil.getRunningProcesses();
        ArrayList<String> pnList = new ArrayList<>();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningProcesses) {
            LogUtil.d(runningAppProcessInfo.processName
                    + " | importance=" + ProcessUtil.getProcessImportanceName(runningAppProcessInfo)
                    + " | lru=" + ProcessUtil.getProcessLru(runningAppProcessInfo)
            );
        }
    }

    private void testVolume() {
        VolumeUtil.enableSoundEffect(true);
        VolumeUtil.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN, 1);
    }

    private void testBrightness() {
        LogUtil.d("brightness=" + BrightnessUtil.getBrightness(this) + "\n"
                + "isAutoBrightness=" + BrightnessUtil.isAutoBrightness(this)
        );
        BrightnessUtil.setBrightness(this, 0.3f);
    }

    private void testBatteryUtil() {
        BatteryUtil.registerBatteryReceiver(this);
        LogUtil.d("level=" + BatteryUtil.getLevel() + "\n"
                + " | scale=" + BatteryUtil.getScale() + "\n"
                + " | percent=" + BatteryUtil.getPercent() + "\n"
                + " | isLow=" + BatteryUtil.isBatteryLow() + "\n"
                + " | health=" + BatteryUtil.getHealthName() + "\n"
                + " | plug=" + BatteryUtil.getPlugTypeName() + "\n"
                + " | status=" + BatteryUtil.getStatusName() + "\n"
                + " | isPresent=" + BatteryUtil.isPresent() + "\n"
                + " | tech=" + BatteryUtil.getTechnology() + "\n"
                + " | iconId=" + BatteryUtil.getIconResId() + "\n"
                + " | temp=" + BatteryUtil.getTemperature() + "\n"
                + " | V=" + BatteryUtil.getVoltage());
        BatteryUtil.unregisterBatteryReceiver(this);
    }

    private void flashLight() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean on = true;
                for (int i = 0; i < 20; i++) {
                    FlashlightUtil.enableFlashLight(on);
                    if (on) {
                        SystemClock.sleep(1);
                    } else {
                        SystemClock.sleep(2);
                    }
                    on = !on;
                }
            }
        }).start();
    }

    private void testSensor() {
        Sensor sensor = SensorUtil.getSensor(Sensor.TYPE_TEMPERATURE);
        if (sensor == null) {
            LogUtil.e("sensor is null");
        }
        SensorUtil.registerSensorListener(sensor, SensorManager.SENSOR_DELAY_NORMAL, new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                String sensorName = sensor.getName();
                int accuracy = event.accuracy;
                long timestamp = event.timestamp;
                float[] values = event.values;
                LogUtil.d(sensorName + " | accuracy=" + accuracy + " | timestamp=" + timestamp + "\nvalues=" + Arrays.toString(values));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                String sensorName = sensor.getName();
                LogUtil.w(sensorName + " | accuracy=" + accuracy);
            }
        });
    }
}
