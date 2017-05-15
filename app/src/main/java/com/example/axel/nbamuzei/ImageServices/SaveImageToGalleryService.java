package com.example.axel.nbamuzei.ImageServices;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import static com.example.axel.nbamuzei.ImageServices.BitmapUtils.getPath;

/**
 * Created by axel on 11/05/17.
 */

public class SaveImageToGalleryService {
    public void AddImageToGallery(Context context){
        Bitmap bitmap = new BitmapUtils().GetBitmapFromPath(getPath());
        MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap,"nba Muzei", "");
        OpenGallery(context);
    }

    private void OpenGallery(Context context){
        Intent galleryIntent = new Intent(Intent.ACTION_VIEW, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(galleryIntent);
    }
}
