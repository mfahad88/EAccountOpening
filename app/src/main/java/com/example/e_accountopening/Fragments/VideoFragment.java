package com.example.e_accountopening.Fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;


import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_accountopening.Helper.CameraPreview;
import com.example.e_accountopening.Helper.Utils;
import com.example.e_accountopening.R;
import com.example.e_accountopening.Services.ImageIntentService;
import com.example.e_accountopening.Services.VideoIntentService;

import java.io.File;
import java.io.IOException;

import static android.content.Context.WINDOW_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements View.OnClickListener,SurfaceHolder.Callback {
    private String VIDEO_PATH_NAME="";
    private View rootView;
    private Button btn_record, btn_next;
    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private TextView tv_timer;
    private boolean mInitSuccesful;
    private File file;
    public VideoFragment() {
        // Required empty public constructor
    }

    public String formatTime(long millis) {
        String output = "00:00";
        long seconds = millis / 1000;
        long minutes = seconds / 60;

        seconds = seconds % 60;
        minutes = minutes % 60;

        String sec = String.valueOf(seconds);
        String min = String.valueOf(minutes);

        if (seconds < 10)
            sec = "0" + seconds;
        if (minutes < 10)
            min= "0" + minutes;

        output = min + " : " + sec;
        return output;
    }//formatTime

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_video, container, false);
        VIDEO_PATH_NAME="/mnt/sdcard/"+Utils.getPreferences(rootView.getContext(),FormFragment.CNIC) +".mp4";

//        file = new File(VIDEO_PATH_NAME);
        /*try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        tv_timer=rootView.findViewById(R.id.tv_timer);
        btn_next=rootView.findViewById(R.id.btn_next);
        btn_record=rootView.findViewById(R.id.btn_record);
        mSurfaceView =rootView.findViewById(R.id.surface_camera);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        btn_next.setOnClickListener(this);
        btn_record.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_record){


            if(!mInitSuccesful){
                try {
                    initRecorder(mHolder.getSurface());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mMediaRecorder.start();
            btn_record.setEnabled(false);
            new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {
                    tv_timer.setText(""+formatTime(millisUntilFinished));
                    //here you can have your logic to set text to edittext
                }

                public void onFinish() {

                }

            }.start();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable()  {
                @Override
                public void run() {
                    //Display Data here
                    mInitSuccesful=false;
                    shutdown();
                    btn_record.setEnabled(true);
                    File file=new File(VIDEO_PATH_NAME);
                    if(file.exists()){
                        btn_next.setEnabled(true);
                    }

                }
            }, 10000);
          /*  try {
                Thread.sleep(10 * 1000); // This will recode for 10 seconds, if you don't want then just remove it.
            } catch (Exception e) {
                e.printStackTrace();
            }*/


        }if(view.getId()==R.id.btn_next){
            replaceFragment(new SummaryFragment());
            /*Intent intent=new Intent(rootView.getContext(), VideoIntentService.class);
            getActivity().startService(intent);*/

        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction ft=this.getFragmentManager().beginTransaction();
        ft.replace(R.id.main_container,fragment);
        ft.commit();
    }


    private void initRecorder(Surface surface) throws IOException {
        // It is very important to unlock the camera before doing setCamera
        // or it will results in a black preview
        if(mCamera == null) {
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
            mCamera.setDisplayOrientation(90);
            mCamera.unlock();

        }

        if(mMediaRecorder == null)  mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setPreviewDisplay(surface);
        mMediaRecorder.setCamera(mCamera);

        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        //       mMediaRecorder.setOutputFormat(8);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mMediaRecorder.setVideoEncodingBitRate(512 * 1000);
        mMediaRecorder.setVideoFrameRate(30);
        mMediaRecorder.setVideoSize(640, 480);
        mMediaRecorder.setMaxDuration(10000);
        mMediaRecorder.setOrientationHint(270);
        mMediaRecorder.setOutputFile(VIDEO_PATH_NAME);

        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            // This is thrown if the previous calls are not called with the
            // proper order
            e.printStackTrace();
        }

        mInitSuccesful = true;
    }

    private void shutdown() {
        // Release MediaRecorder and especially the Camera as it's a shared
        // object that can be used by other applications
        mMediaRecorder.reset();
        mMediaRecorder.release();
        mCamera.release();

        // once the objects have been released they can't be reused
        mMediaRecorder = null;
        mCamera = null;
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            if(!mInitSuccesful)

                initRecorder(mHolder.getSurface());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

        /*Camera.Parameters parameters = mCamera.getParameters();
        Display display = ((WindowManager)rootView.getContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        if(display.getRotation() == Surface.ROTATION_0) {
            parameters.setPreviewSize(height, width);
            mCamera.setDisplayOrientation(90);
        }

        if(display.getRotation() == Surface.ROTATION_90) {
            parameters.setPreviewSize(width, height);
        }

        if(display.getRotation() == Surface.ROTATION_180) {
            parameters.setPreviewSize(height, width);
        }

        if(display.getRotation() == Surface.ROTATION_270) {
            parameters.setPreviewSize(width, height);
            mCamera.setDisplayOrientation(180);
        }

        mCamera.setParameters(parameters);*/

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        shutdown();
    }
}
