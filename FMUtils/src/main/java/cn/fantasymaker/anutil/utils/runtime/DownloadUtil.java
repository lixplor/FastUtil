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

package cn.fantasymaker.anutil.utils.runtime;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;

import cn.fantasymaker.anutil.utils.FMUtils;

/**
 * Created :  2016-07-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class DownloadUtil {

    private static Context sContext = FMUtils.getContext();

    private DownloadUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Download a file using download manager which shows a notification bar
     *
     * @param url        url of remote file
     * @param targetFile File to be saved in local
     * @param title      String shows in notification bar as a title
     * @return download id
     */
    public static long download(String url, File targetFile, String title) {
        DownloadManager downloadManager = (DownloadManager) sContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request downloadRequest = new DownloadManager.Request(uri);
        downloadRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadRequest.setAllowedOverRoaming(false);
        downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        downloadRequest.setVisibleInDownloadsUi(true);
        downloadRequest.setTitle(title);
        downloadRequest.setDestinationUri(Uri.fromFile(targetFile));
        return downloadManager.enqueue(downloadRequest);
    }

    /**
     * Query dowload state
     *
     * @param downloadId Download id
     * @return state
     */
    public static int queryDownloadState(long downloadId) {
        DownloadManager downloadManager = (DownloadManager) sContext.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();
        DownloadManager.Query filteredQuery = query.setFilterById(downloadId);
        downloadManager.query(filteredQuery);
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            return c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
        } else {
            return -1;
        }
    }

    /**
     * Install apk
     *
     * @param path local path
     */
    public static void installApk(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sContext.startActivity(intent);
    }

    /**
     * Create file in dir<br/>
     * e.g. Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
     *
     * @param fileDirPath dir path where file exists. Must ends with "/"
     * @param fileName    file name
     * @return a File
     */
    public static File createFile(String fileDirPath, String fileName) {
        if (!fileDirPath.endsWith("/")) {
            fileDirPath += "/";
        }
        String path = fileDirPath + fileName;
        File file;
        try {
            file = new File(path);
            if (file.exists()) {
                //if file already exists, delete and recreate it
                if (file.delete()) {
                    file = new File(path);
                } else {
                    return null;
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return null;
        }
        return file;
    }
}
