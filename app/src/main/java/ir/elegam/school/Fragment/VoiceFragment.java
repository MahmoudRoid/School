package ir.elegam.school.Fragment;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.elegam.school.AsyncTask.PostMessage;
import ir.elegam.school.AsyncTask.PostVoice;
import ir.elegam.school.Interface.IWebservice;
import ir.elegam.school.R;

/**
 * Created by Droid on 8/15/2016.
 */
public class VoiceFragment extends Fragment implements IWebservice {
    @BindView(R.id.record_around)
    ImageView recordAround;
    @BindView(R.id.record)
    ImageView record;
    @BindView(R.id.relative_for_voice)
    RelativeLayout relativeForVoice;
    @BindView(R.id.sub_play_voice)
    ImageView subPlayVoice;
    @BindView(R.id.play_voice)
    RelativeLayout playVoice;
    @BindView(R.id.sub_send_voice)
    ImageView subSendVoice;
    @BindView(R.id.send_voice)
    RelativeLayout sendVoice;
    @BindView(R.id.bottom_bar)
    LinearLayout bottomBar;


    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    private Animation animrotate;
    boolean record_status = false;

    public VoiceFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.voice_fragment, container, false);
        ButterKnife.bind(this, view);
        animrotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        animrotate.setRepeatCount(Animation.INFINITE);
        animrotate.setFillAfter(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.mp3";
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);
    }

    @OnClick({R.id.record, R.id.play_voice, R.id.send_voice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record:
                record_voice();
                break;
            case R.id.play_voice:
                record_play();
                break;
            case R.id.send_voice:
                record_send();
                break;
        }
    }


    private void record_voice() {

        if (record_status == false) {
            // shoro e zabt
            recordAround.startAnimation(animrotate);
            try {
                if (myAudioRecorder == null) {
                    myAudioRecorder = new MediaRecorder();
                    myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                    myAudioRecorder.setOutputFile(outputFile);
                }
                myAudioRecorder.prepare();
                myAudioRecorder.start();

//        record.setEnabled(false);
//                subRecordVoice.setImageResource(R.drawable.ic_micro_off);
//        stop.setEnabled(true);
                Toast.makeText(getActivity(), "شروع ضبط...", Toast.LENGTH_SHORT).show();

            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // set record status=true
            record_status = true;
        }
        else {
            // etmame zabt
            myAudioRecorder.stop();
            myAudioRecorder.release();
            myAudioRecorder = null;



          //  subRecordVoice.setImageResource(R.drawable.ic_micro_on);
            animrotate.cancel();
            animrotate.reset();
//            sendVoice.setVisibility(View.VISIBLE);
//            playVoice.setVisibility(View.VISIBLE);
            bottomBar.setVisibility(View.VISIBLE);
            subPlayVoice.setImageResource(R.drawable.ic_play_on);
//            play.setEnabled(true);
//            send_btn.setEnabled(true);

            Toast.makeText(getActivity(), "پیام شما با موفقیت ضبط شد", Toast.LENGTH_SHORT).show();
            // set record status== false
            record_status = false;
        }
    }
    private void record_play() {
        if (record_status==false) {
            try {
                MediaPlayer m = new MediaPlayer();

                try {
                    m.setDataSource(outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    m.prepare();
                    m.start();
                    Toast.makeText(getActivity(), "پخش صدا", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        else {
        }
    }
    private void record_send() {
        // convert mp3 to base64
        try {
            String base64 = convert_mp3tobase64(outputFile);
            PostVoice b64 = new PostVoice(getActivity(), VoiceFragment.this, base64);
            b64.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convert_mp3tobase64(String masir) throws IOException {
        InputStream inputStream = null;//You can get an inputStream using any IO API
        inputStream = new FileInputStream(masir);
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        output64.close();

        return output.toString();
    }

    @Override
    public void getResult(Object result) throws Exception {
        Snackbar snackbar = Snackbar
                .make(getView(), "با موفقیت ارسال شد", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void getError(String ErrorCodeTitle) throws Exception {
        Snackbar snackbar = Snackbar
                .make(getView(), "متاسفانه ارسال نشد", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
