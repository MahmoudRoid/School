package ir.elegam.school.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import ir.elegam.school.Classes.Variables;

public class Async_GetSchedual extends AsyncTask <Object, Object, Object> {

    String res = "";
    public GetSchedual mListener;

    public interface GetSchedual {
        void onStartRequest();
        void onFinishedRequest(String res);
    }

    @Override
    protected Object doInBackground(Object... params) {
        BufferedReader reader = null;
        try {
            String data = URLEncoder.encode("Token", "UTF8")  +"="+ URLEncoder.encode(params[1].toString(),"UTF8");
            data += "&"+  URLEncoder.encode("Code", "UTF8")   +"="+ URLEncoder.encode(params[2].toString(),"UTF8");
            data += "&"+  URLEncoder.encode("Class", "UTF8")  +"="+ URLEncoder.encode(params[3].toString(),"UTF8");
            data += "&"+  URLEncoder.encode("Faction", "UTF8")+"="+ URLEncoder.encode(params[4].toString(),"UTF8");

            URL link = new URL(params[0].toString());
            HttpURLConnection connect = (HttpURLConnection) link.openConnection();
            connect.setRequestMethod("POST");

            connect.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connect.getOutputStream());
            wr.write(data);
            wr.flush();

            reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line=reader.readLine()) != null){
                sb.append(line);
            }

            res = sb.toString();
            Log.i(Variables.Tag, "my out is: " + res);

        }catch(Exception e){e.printStackTrace();e.toString();Log.i(Variables.Tag,"here");}
        finally
        {
            try{reader.close();}catch(Exception e) {}
        }
        return res;
    }// end doInBackground()

    protected void onPostExecute(Object result)
    {
        if(mListener != null)
        {
            String s = result.toString();
            mListener.onFinishedRequest(s);
        }
    }

    @Override
    protected void onPreExecute()
    {
        if(mListener != null)
        {
            mListener.onStartRequest();
        }
    }
}// end class
