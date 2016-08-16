package ir.elegam.school.AsyncTask;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import ir.elegam.school.Classes.Variables;

public class Async_Video_Downloader extends AsyncTask<Object, Object, Object> {

    String status = "";
    public GetVideo mListener;

    public interface GetVideo {

        void onStratVideoDownload();
        void onFinishedVideoDownload(String res);
        void onProgressUpdate(int value);
    }

    @Override
    protected void onPreExecute() {
        if(mListener != null)
        {
            mListener.onStratVideoDownload();
        }
    }



    @Override
    protected String doInBackground(Object... f_url) {
        int count;
        try {
            String param1 = f_url[0].toString();
            URL url = new URL(param1);
            String name = f_url[1].toString();
            URLConnection conection = url.openConnection();
            conection.connect();

            File dir = new File(Variables.VIDEO);
            if (!dir.exists())
                dir.mkdirs();
            // this will be useful so that you can show a tipical 0-100% progress bar
            int lenghtOfFile = conection.getContentLength();

            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            OutputStream output = null;
            if (param1.toLowerCase().contains(".mp4"))
                output = new FileOutputStream(Variables.VIDEO + "/" + name + ".mp4");
            else if (param1.toLowerCase().contains(".3gp"))
                output = new FileOutputStream(Variables.VIDEO + "/" + name + ".3gp");

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();
            status = "true";

        } catch (Exception e) {
            status = "false";
        }

        return null;
    }// end doInBackground()

    protected void onProgressUpdate(String... progress) {
        if(mListener != null)
        {
            int s = Integer.parseInt(progress[0]);
            mListener.onProgressUpdate(s);
        }
    }

    protected void onPostExecute(Object result)
    {
        if(mListener != null)
        {
            String s = result.toString();
            mListener.onFinishedVideoDownload(s);
        }
    }

}
