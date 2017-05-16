package com.example.axel.nbamuzei.Permissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.axel.nbamuzei.ImageServices.SaveImageToGalleryService;
import com.example.axel.nbamuzei.R;

/**
 * Created by axel on 16/05/17.
 */

public class AskForPermissionsActivity extends Activity {

    static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AskForWriteStoragePermission();
    }

    private void AskForWriteStoragePermission(){
        int permission = ContextCompat.checkSelfPermission(AskForPermissionsActivity.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            //Request permission
            ActivityCompat.requestPermissions(AskForPermissionsActivity.this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }else{
            //Permission already granted
            OnPermissionGranted();
        }
    }

    private void OnPermissionGranted() {
       ShowMessage(getString(R.string.image_saved));
        new SaveImageToGalleryService().AddImageToGallery(getBaseContext());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission granted
                    OnPermissionGranted();
                } else {
                    //Permission denied
                    ShowMessage(getString(R.string.permission_denied));
                }
                finish();
                return;
            }
        }
    }

    private void ShowMessage(String message) {
        Toast.makeText(getBaseContext(),message,
                Toast.LENGTH_SHORT).show();
    }

}