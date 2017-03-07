/*
 *  Copyright 2016 Lixplor
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.lixplor.fastutil.utils.control;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

import com.lixplor.fastutil.utils.FastUtil;

/**
 * Created :  2016-08-10
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class VolumeUtil {
    
    /*
    todo
    x调整音量
    x获取音量
     */

    private static Context sContext = FastUtil.getContext();
    private static AudioManager sAudioManager = (AudioManager) sContext.getSystemService(Context.AUDIO_SERVICE);

    private VolumeUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    public static AudioManager getAudioManager() {
        return sAudioManager;
    }

    private static int getVolume(int streamType) {
        return getAudioManager().getStreamVolume(streamType);
    }

    public static int getSystemVolume() {
        return getVolume(AudioManager.STREAM_SYSTEM);
    }

    public static int getVoiceVolume() {
        return getVolume(AudioManager.STREAM_VOICE_CALL);
    }

    public static int getAlarmVolume() {
        return getVolume(AudioManager.STREAM_ALARM);
    }

    public static int getDTMFVolume() {
        return getVolume(AudioManager.STREAM_DTMF);
    }

    public static int getMusicVolume() {
        return getVolume(AudioManager.STREAM_MUSIC);
    }

    public static int getNofificationVolume() {
        return getVolume(AudioManager.STREAM_NOTIFICATION);
    }

    public static int getRingVolume() {
        return getVolume(AudioManager.STREAM_RING);
    }

    private static int getMaxVolume(int streamType) {
        return getAudioManager().getStreamMaxVolume(streamType);
    }

    public static int getSystemMaxVolume() {
        return getMaxVolume(AudioManager.STREAM_SYSTEM);
    }

    public static int getVoiceMaxVolume() {
        return getMaxVolume(AudioManager.STREAM_VOICE_CALL);
    }

    public static int getAlarmMaxVolume() {
        return getMaxVolume(AudioManager.STREAM_ALARM);
    }

    public static int getDTMFMaxVolume() {
        return getMaxVolume(AudioManager.STREAM_DTMF);
    }

    public static int getMusicMaxVolume() {
        return getMaxVolume(AudioManager.STREAM_MUSIC);
    }

    public static int getNofificationMaxVolume() {
        return getMaxVolume(AudioManager.STREAM_NOTIFICATION);
    }

    public static int getRingMaxVolume() {
        return getMaxVolume(AudioManager.STREAM_RING);
    }

    public static void setVolume(int streamType, int index, int flag) {
        getAudioManager().setStreamVolume(streamType, index, flag);
    }

    public static void setSystemVolume(int index) {
        setVolume(AudioManager.STREAM_SYSTEM, index, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void setVoiceVolume(int index) {
        setVolume(AudioManager.STREAM_VOICE_CALL, index, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void setAlarmVolume(int index) {
        setVolume(AudioManager.STREAM_ALARM, index, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void setDTMFVolume(int index) {
        setVolume(AudioManager.STREAM_DTMF, index, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void setMusicVolume(int index) {
        setVolume(AudioManager.STREAM_MUSIC, index, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void setNofificationVolume(int index) {
        setVolume(AudioManager.STREAM_NOTIFICATION, index, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void setRingVolume(int index) {
        setVolume(AudioManager.STREAM_RING, index, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void adjustVolume(int streamType, int direction, int flag) {
        getAudioManager().adjustStreamVolume(streamType, direction, flag);
    }

    public static void adjustSystemVolume(int direction) {
        adjustVolume(AudioManager.STREAM_SYSTEM, direction, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void adjustVoiceVolume(int direction) {
        adjustVolume(AudioManager.STREAM_VOICE_CALL, direction, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void adjustAlarmVolume(int direction) {
        adjustVolume(AudioManager.STREAM_ALARM, direction, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void adjustDTMFVolume(int direction) {
        adjustVolume(AudioManager.STREAM_DTMF, direction, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void adjustMusicVolume(int direction) {
        adjustVolume(AudioManager.STREAM_MUSIC, direction, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void adjustNofificationVolume(int direction) {
        adjustVolume(AudioManager.STREAM_NOTIFICATION, direction, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static void adjustRingVolume(int direction) {
        adjustVolume(AudioManager.STREAM_RING, direction, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public static int getMode() {
        return getAudioManager().getMode();
    }

    public static int getRingerMode() {
        return getAudioManager().getRingerMode();
    }

    public static String getRingerModeName() {
        String modeName;
        switch (getRingerMode()) {
            case AudioManager.RINGER_MODE_NORMAL:
                modeName = "NORMAL";
                break;
            case AudioManager.RINGER_MODE_SILENT:
                modeName = "SILENT";
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                modeName = "VIBRATE";
                break;
            default:
                modeName = "UNKNOWN";
                break;
        }
        return modeName;
    }

    public static void setRingerMode(int ringerMode) {
        getAudioManager().setRingerMode(ringerMode);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isStreamMute(int streamType) {
        return getAudioManager().isStreamMute(streamType);
    }

    public static boolean isMicrophoneMute() {
        return getAudioManager().isMicrophoneMute();
    }

    public static void setMicrophoneMute(boolean mute) {
        getAudioManager().setMicrophoneMute(mute);
    }

    public static boolean isMusicActive() {
        return getAudioManager().isMusicActive();
    }

    public static boolean isSpeakerphoneOn() {
        return getAudioManager().isSpeakerphoneOn();
    }

    public static void enableSpeakerphone(boolean enable) {
        getAudioManager().setSpeakerphoneOn(enable);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static boolean isVolumeFixed() {
        return getAudioManager().isVolumeFixed();
    }

    public static boolean isBluetoothScoOffCallSupport() {
        return getAudioManager().isBluetoothScoAvailableOffCall();
    }

    public static boolean isUseBluetoothSco() {
        return getAudioManager().isBluetoothScoOn();
    }

    public static void enableBluetoothSco(boolean enable) {
        AudioManager audioManager = getAudioManager();
        if (enable) {
            audioManager.startBluetoothSco();
        } else {
            audioManager.stopBluetoothSco();
        }
    }

    public static boolean isBluetoothHeadsetOn() {
        return getAudioManager().isBluetoothA2dpOn();
    }

    public static void enableSoundEffect(boolean enable) {
        AudioManager audioManager = getAudioManager();
        if (enable) {
            audioManager.loadSoundEffects();
        } else {
            audioManager.unloadSoundEffects();
        }
    }

    /**
     * Play a sound effect of clicking. Sound volume will use system default. If setting disable click effect, this will be mute
     *
     * @param effectType
     */
    public static void playSoundEffect(int effectType) {
        getAudioManager().playSoundEffect(effectType);
    }

    /**
     * Play a sound effect of clicking with specific volume no matter whether setting disable click effect or not
     *
     * @param effectType
     * @param volume
     */
    public static void playSoundEffect(int effectType, float volume) {
        getAudioManager().playSoundEffect(effectType, volume);
    }

    public static int requestAudioFocus(int streamType, int durationHint, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        return getAudioManager().requestAudioFocus(onAudioFocusChangeListener, streamType, durationHint);
    }

    public static int abandonAudioFocus(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        return getAudioManager().abandonAudioFocus(onAudioFocusChangeListener);
    }
}
