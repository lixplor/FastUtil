/*
 *     Copyright © 2016 Fantasymaker
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy
 *     of this software and associated documentation files (the "Software"), to deal
 *     in the Software without restriction, including without limitation the rights
 *     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *     copies of the Software, and to permit persons to whom the Software is
 *     furnished to do so, subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all
 *     copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *     SOFTWARE.
 */

package cn.fantasymaker.fmutils.utils.control;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.view.Surface;

import java.io.IOException;
import java.util.List;

/**
 * Created :  2016-08-11
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class CameraUtil {

    /*
    todo
    x拍照
    ?静默拍照: 相机静音
    x延时摄像
    x录像
    x切换镜头
    x对焦
    x人脸识别
    获取图像帧
     */

    /*
    Need permissions:
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-feature android:name="android.hardware.camera"/>
    <uses-feature android:name="android.hardware.camera.autofocus"/>

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
     */

    public static final int CAMERA_ID_BACK = 0;
    public static final int CAMERA_ID_FRONT = 1;

    public static final int BITRATE_AUDIO_16K_TEL = 1024 * 16;
    public static final int BITRATE_AUDIO_24K_ATEL = 1024 * 24;
    public static final int BITRATE_AUDIO_56K_AM = 1024 * 56;
    public static final int BITRATE_AUDIO_64K_RINGTONE = 1024 * 64;
    public static final int BITRATE_AUDIO_112K_FM = 1024 * 112;
    public static final int BITRATE_AUDIO_128K_TAPE = 1024 * 128;
    public static final int BITRATE_AUDIO_160K_HIFI = 1024 * 160;
    public static final int BITRATE_AUDIO_192K_CD = 1024 * 192;
    public static final int BITRATE_AUDIO_256K_STUDIO = 1024 * 256;
    public static final int BITRATE_AUDIO_320K_REAL = 1024 * 320;
    public static final int BITRATE_VIDEO_16K_TEL = 1024 * 16;
    public static final int BITRATE_VIDEO_128K_MEETING = 1024 * 128;
    public static final int BITRATE_VIDEO_1M_VHS = 1024 * 1024;
    public static final int BITRATE_VIDEO_1280K_VCD = 1024 * 1280;
    public static final int BITRATE_VIDEO_5M_DVD = 1024 * 1024 * 5;
    public static final int BITRATE_VIDEO_8M_HDTV = 1024 * 1024 * 8;
    //belows are not available
//    public static final int BITRATE_VIDEO_30M_HDDVD = 1024 * 1024 * 30;
//    public static final int BITRATE_VIDEO_40M_BLU = 1024 * 1024 * 40;

    private static Camera sCamera;
    private static Parameters sParameters;
    private static MediaRecorder sMediaRecorder;

    private CameraUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static Camera openDefaultCamera() {
        sCamera = Camera.open();
        return sCamera;
    }

    public static Camera openCamera(int cameraId) {
        sCamera = Camera.open(cameraId);
        return sCamera;
    }

    public static int getCameraNumbers() {
        return Camera.getNumberOfCameras();
    }

    public static CameraInfo getCameraInfo(int cameraId) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(cameraId, cameraInfo);
        return cameraInfo;
    }

    public static int getFacing(CameraInfo cameraInfo) {
        return cameraInfo.facing;
    }

    public static int getOrientation(CameraInfo cameraInfo) {
        return cameraInfo.orientation;
    }

    public static boolean canDisableSound(CameraInfo cameraInfo) {
        return cameraInfo.canDisableShutterSound;
    }

    public static boolean enableSound(boolean enable) {
        return sCamera.enableShutterSound(enable);
    }

    public static int getFrontCameraId() {
        return CAMERA_ID_FRONT;
    }

    public static int getBackCameraId() {
        return CAMERA_ID_BACK;
    }

    public static Parameters getParameters() {
        if(sCamera == null){
            openDefaultCamera();
        }
        sParameters = sCamera.getParameters();
        return sParameters;
    }

    public static void setParameters(Parameters parameters) {
        sCamera.setParameters(parameters);
    }

    public static void enableFaceDetection(boolean enable) {
        if (enable) {
            sCamera.startFaceDetection();
        } else {
            sCamera.stopFaceDetection();
        }
    }

    public static void takePicture(SurfaceTexture surfaceTexture, final Camera.ShutterCallback shutterCallback, final Camera.PictureCallback pictureCallback, final Camera.PictureCallback postview, final Camera.PictureCallback jpeg) throws IOException {
        sCamera.setPreviewTexture(surfaceTexture);
        sCamera.startPreview();
        sCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    sCamera.takePicture(shutterCallback, pictureCallback, postview, jpeg);
                }
            }
        });
    }

    public static void releaseCamera() {
        sCamera.stopPreview();
        sCamera.release();
        sCamera = null;
    }

    public static void record(String outputFilePath, int width, int height, int audioBitRate, int videoBitRate, int frameRate, Surface preview) throws IOException {
        sMediaRecorder = new MediaRecorder();
        sMediaRecorder.reset();
//        sMediaRecorder.setCamera(openCamera(CAMERA_ID_FRONT));
        sMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        sMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        sMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        sMediaRecorder.setAudioEncodingBitRate(audioBitRate);
        sMediaRecorder.setVideoEncodingBitRate(videoBitRate);
        sMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        sMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        sMediaRecorder.setVideoSize(width, height);
        sMediaRecorder.setOutputFile(outputFilePath);
        sMediaRecorder.setVideoFrameRate(frameRate);
        sMediaRecorder.setPreviewDisplay(preview);
        sMediaRecorder.prepare();
        sMediaRecorder.start();
    }

    public static void record1(String outputFilePath, int audioBitRate, int videoBitRate, Surface preview) throws IOException {
        sMediaRecorder = new MediaRecorder();
        sMediaRecorder.reset();
//        sMediaRecorder.setCamera(openCamera(CAMERA_ID_FRONT));
        sMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        sMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        sMediaRecorder.setAudioEncodingBitRate(audioBitRate);
        sMediaRecorder.setVideoEncodingBitRate(videoBitRate);
        sMediaRecorder.setProfile(CamcorderProfile.get(CAMERA_ID_BACK, CamcorderProfile.QUALITY_HIGH));
        sMediaRecorder.setOutputFile(outputFilePath);
//        sMediaRecorder.setVideoFrameRate(30);
        sMediaRecorder.setPreviewDisplay(preview);
        sMediaRecorder.prepare();
        sMediaRecorder.start();
    }

    public static void recordTimelapse(String outputFilePath, Surface preview, int cameraId, double fps, int camcorderProfile) throws IOException {
        sMediaRecorder = new MediaRecorder();
        sMediaRecorder.reset();
        sMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        sMediaRecorder.setProfile(CamcorderProfile.get(cameraId, camcorderProfile));
        sMediaRecorder.setCaptureRate(fps);
        sMediaRecorder.setOutputFile(outputFilePath);
        sMediaRecorder.setPreviewDisplay(preview);
        sMediaRecorder.prepare();
        sMediaRecorder.start();
    }

    public static void releaseMediaRecoder() {
        sMediaRecorder.stop();
        sMediaRecorder.release();
        sMediaRecorder = null;
    }

    public static boolean setColorEffect(String colorEffect){
        List<String> supportedColorEffects = sParameters.getSupportedColorEffects();
        for(String effect : supportedColorEffects){
            if(effect.equalsIgnoreCase(colorEffect)){
                sParameters.setColorEffect(colorEffect);
                return true;
            }
        }
        return false;
    }

    public static boolean setSceneMode(String sceneMode){
        List<String> supportedSceneModes = sParameters.getSupportedSceneModes();
        for(String mode : supportedSceneModes){
            if(mode.equalsIgnoreCase(sceneMode)){
                sParameters.setSceneMode(sceneMode);
                return true;
            }
        }
        return false;
    }
}
