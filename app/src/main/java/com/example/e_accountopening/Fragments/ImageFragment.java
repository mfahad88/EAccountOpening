package com.example.e_accountopening.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.e_accountopening.Interfaces.ScreenInterface;
import com.example.e_accountopening.R;
import com.example.e_accountopening.Services.ImageIntentService;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private ImageView img_cnic;
    private Button btn_take_img,btn_next;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    private ScreenInterface screenInterface;
    byte[] byteArrayImg;
    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            screenInterface = (ScreenInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_image, container, false);
        img_cnic=rootView.findViewById(R.id.img_cnic);
        btn_take_img=rootView.findViewById(R.id.btn_take_img);
        btn_next=rootView.findViewById(R.id.btn_next);
        btn_take_img.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        if(!hasNullOrEmptyDrawable(img_cnic)){
            btn_next.setEnabled(true);
        }
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_take_img){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,
                    CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        }

        if(view.getId()==R.id.btn_next){
            Intent intent=new Intent(rootView.getContext(), ImageIntentService.class);
            intent.putExtra("image",byteArrayImg);
            getActivity().startService(intent);
            replaceFragment(new VideoFragment(),byteArrayImg);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                byteArrayImg=byteArray;
                // convert byte array to Bitmap

                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);
                img_cnic.setScaleType(ImageView.ScaleType.FIT_CENTER);

                img_cnic.setImageBitmap(bitmap);
                if(!hasNullOrEmptyDrawable(img_cnic)){
                    btn_next.setEnabled(true);

                }

            }
        }
    }

    public static boolean hasNullOrEmptyDrawable(ImageView iv)
    {
        Drawable drawable = iv.getDrawable();
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable)drawable : null;

        return bitmapDrawable == null || bitmapDrawable.getBitmap() == null;
    }


    public void replaceFragment(Fragment fragment,byte[] byteArray){
        FragmentTransaction ft=this.getFragmentManager().beginTransaction();
        Bundle b = new Bundle();
        b.putByteArray("image",byteArray);
        fragment.setArguments(b);
        ft.replace(R.id.main_container,fragment);
        ft.commit();
    }

}
