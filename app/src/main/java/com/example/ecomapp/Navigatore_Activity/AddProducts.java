package com.example.ecomapp.Navigatore_Activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.ecomapp.R;
import com.example.ecomapp.SQLIte.SQLitePhoneDetails;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProducts extends AppCompatActivity {
    EditText namedetails, pricedetails, product_details;
    ImageView image_view;
    Button btnchoose, btncancel, btnadd;
    final  int REQUEST_CODE_GALLERY =999;
    public static SQLitePhoneDetails sqLitePhoneDetails;
//    public static final int PICK_IMAGE_Gallery = 900;
//    public static final int PICK_IMAGE_CAMERA = 901;
//    private String mCurrentPhotoPath;
//    private Uri selectedImage;
//    int pixels;
//    public Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__products);

        inti();
        validation();
        sqLitePhoneDetails = new SQLitePhoneDetails(this, "PHONEDB.sqlite", null, 1);

        sqLitePhoneDetails.queryData("CREATE TABLE IF NOT EXISTS PHONE (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR, price VARCHAR, details VARCHAR, image BLOB)");


        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddProducts.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    sqLitePhoneDetails.insertData(
                            namedetails.getText().toString().trim(),
                            pricedetails.getText().toString().trim(),
                            product_details.getText().toString().trim(),
                            imageViewToByte(image_view));

                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    namedetails.setText("");
                    pricedetails.setText("");
                    product_details.setText("");
                    image_view.setImageResource(R.drawable.images_logo);
                }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddProducts.this, AdminSupport.class);
                finish();
            }
        });
    }
    public static byte[] imageViewToByte(ImageView image){

        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(),"You Don't have Permission to Access file Location! ",Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);

                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                image_view.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode,resultCode, data);
    }

//    public void askForCameraAndGallery() {
//        try {
//            PackageManager pm = this.getPackageManager();
//            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, this.getPackageName());
//            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
//                final CharSequence[] options = {"Take Photo From Camera", "Choose From Gallery", "Cancel"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Select Option");
//                builder.setItems(options, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                        if (options[item].equals("Take Photo From Camera")) {
//                            dialog.dismiss();
//                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            if (cameraIntent.resolveActivity(AddProducts.this.getPackageManager()) != null) {
//                                // Create the File where the photo should go
//                                File photoFile = null;
//                                try {
//                                    photoFile = createImageFile();
//                                } catch (IOException ex) {
//                                    // Error occurred while creating the File
//                                    Log.i("saif", "IOException");
//                                }
//                                // Continue only if the File was successfully created
//                                if (photoFile != null) {
//                                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                                    startActivityForResult(cameraIntent, PICK_IMAGE_CAMERA);
//                                }
//                            }
//
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
//                        } else if (options[item].equals("Choose From Gallery")) {
//                            dialog.dismiss();
//                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                            startActivityForResult(pickPhoto, PICK_IMAGE_Gallery);
//                        } else if (options[item].equals("Cancel")) {
//                            dialog.dismiss();
//                        }
//                    }
//                });
//                builder.show();
//            } else
//                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  // prefix
//                ".jpg",         // suffix
//                storageDir      // directory
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        return image;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//
//            // When  Image is picked form gallery
//            if (requestCode == PICK_IMAGE_Gallery && resultCode == RESULT_OK
//                    && null != data) {
//                cropImage(data.getData());
//
//            }
//
//            //when image picked from camera
//            if (requestCode == PICK_IMAGE_CAMERA) {
//                try {
//                    cropImage(Uri.parse(mCurrentPhotoPath));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            // Log.e("vkm", "onActivityResult: " );
//            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//                CropImage.ActivityResult result = CropImage.getActivityResult(data);
//                //  Log.e("vkm", "onActivityResult: " + result.getUri());
//                if (resultCode == RESULT_OK) {
//                    selectedImage = result.getUri();
////                    inputPicShow.setImageURI(selectedImage);
//                    Picasso.get()
//                            .load(selectedImage)
//                            .resize(pixels,pixels)
//                            .memoryPolicy(MemoryPolicy.NO_CACHE)
//                            .into(image_view);
//
//
//                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                    Exception error = result.getError();
//                    Log.e("vkm", "onActivityResult: " + error);
//                }
//            }
//
//        } catch (Exception e) {
//            Log.e("saif", "onActivityResult: " + e.toString());
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
//                    .show();
//        }
//    }

    public void inti(){
        namedetails=(EditText)findViewById(R.id.namedetails);
        pricedetails=(EditText)findViewById(R.id.pricedetails);
        product_details=(EditText)findViewById(R.id.product_details);
        image_view=(ImageView)findViewById(R.id.details_image);
        btnadd=(Button) findViewById(R.id.btnadd);
        btnchoose=(Button)findViewById(R.id.btnchoose);
        btncancel=(Button) findViewById(R.id.btnlist);
    }
    public  boolean validation(){
        boolean add=true;
        String name=namedetails.getText().toString();
        String price=pricedetails.getText().toString();
        String details=product_details.getText().toString();
        if(name.equals("")){
            add=false;
            namedetails.setError("Please Enter Name");
        }
        if (price.equals("")){
            add=false;
            pricedetails.setError("Please Enter Price of Product");
        }
        if (details.equals("")){
            add=false;
            product_details.setError("Please Enter Details");
        }
        return  add;

    }
//    public void cropImage(Uri path) {
//        // start picker to get image for cropping and then use the image in cropping activity
//
//        CropImage.activity(path)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .start(this);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//
//    private void uploadImage(Uri selectedImage) {
//        try {
//            String img_name = new File(getPath(this, selectedImage)).getName().trim();
//            String extension[] = img_name.split("\\.");
//            SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
//            Date myDate = new Date();
//            String filename = timeStampFormat.format(myDate);
//           /* uplaodImageEdit(getBytes(getActivity().getContentResolver().openInputStream(selectedImage)),
//                    new File(getPath(getActivity(), selectedImage)).getName(), selectedImage);*/
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public byte[] getBytes(InputStream is) throws IOException {
//        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();
//
//        int buffSize = 1024;
//        byte[] buff = new byte[buffSize];
//
//        int len = 0;
//        while ((len = is.read(buff)) != -1) {
//            byteBuff.write(buff, 0, len);
//        }
//
//        return byteBuff.toByteArray();
//    }
//
////    Upload   user   image
////    private void uplaodImageEdit(byte[] bytes, String name, final Uri selectedImage) {
////        //   this.commonhelper.ShowMesseage("please wait...");
////        final RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), bytes);
////
////        // MultipartBody.Part is used to send also the actual file name
////        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("uploaded_file", name, requestFile);
//////        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
////        RequestBody catId = RequestBody.create(MultipartBody.FORM, "" + tbl_id);
////        // commonhelper.show();
////       /* if (isEdit) {
////            this.apiInterface.superAdminImg_Upload(catId, fileToUpload).enqueue(new Callback<ResponseBody>() {
////                @Override
////                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                    //                commonhelper.dismiss();
////                    if (response.isSuccessful()) {
////
////                        fetch_data();
////                        //                    if (response.body().getData().get(0).getStatus().equalsIgnoreCase("true")) {
////                        //                        commonHelper.ShowMesseage("Successfully Upload");
////                        //                    } else {
////                        //                        commonHelper.ShowMesseage("Something wrong please try again");
////                        //                    }
////                    }
////                }
////
////                @Override
////                public void onFailure(Call<ResponseBody> call, Throwable t) {
////                    // commonhelper.dismiss();
////
////                }
////            });
////        } else {*/
////           /* this.apiInterface.addCatImg(catId, fileToUpload).enqueue(new Callback<ResponseBody>() {
////                @Override
////                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                    //                commonhelper.dismiss();
////                    if (response.isSuccessful()) {
////
////                        fetch_data();
////                        //                    if (response.body().getData().get(0).getStatus().equalsIgnoreCase("true")) {
////                        //                        commonHelper.ShowMesseage("Successfully Upload");
////                        //                    } else {
////                        //                        commonHelper.ShowMesseage("Something wrong please try again");
////                        //                    }
////                    }
////                }
////
////                @Override
////                public void onFailure(Call<ResponseBody> call, Throwable t) {
////                    // commonhelper.dismiss();
////
////                }
////            });*/
////        }
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public static String getPath(final Context context, final Uri uri) {
//
//        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
//
//        // DocumentProvider
//        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
//            // ExternalStorageProvider
//            if (isExternalStorageDocument(uri)) {
//                final String docId = DocumentsContract.getDocumentId(uri);
//                final String[] split = docId.split(":");
//                final String type = split[0];
//
//                if ("primary".equalsIgnoreCase(type)) {
//                    return Environment.getExternalStorageDirectory() + "/" + split[1];
//                }
//                // TODO handle non-primary volumes
//            }
//            // DownloadsProvider
//            else if (isDownloadsDocument(uri)) {
//
//                final String id = DocumentsContract.getDocumentId(uri);
//                final Uri contentUri = ContentUris.withAppendedId(
//                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
//
//                return getDataColumn(context, contentUri, null, null);
//            }
//            // MediaProvider
//            else if (isMediaDocument(uri)) {
//                final String docId = DocumentsContract.getDocumentId(uri);
//                final String[] split = docId.split(":");
//                final String type = split[0];
//
//                Uri contentUri = null;
//                if ("image".equals(type)) {
//                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                } else if ("video".equals(type)) {
//                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//                } else if ("audio".equals(type)) {
//                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                }
//                final String selection = "_id=?";
//                final String[] selectionArgs = new String[]{
//                        split[1]
//                };
//
//                return getDataColumn(context, contentUri, selection, selectionArgs);
//            }
//        }
//        // MediaStore (and general)
//        else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            return getDataColumn(context, uri, null, null);
//        }
//        // File
//        else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            return uri.getPath();
//        }
//        return null;
//    }
//    public static String getDataColumn(Context context, Uri uri, String selection,
//                                       String[] selectionArgs) {
//        Cursor cursor = null;
//        final String column = "_data";
//        final String[] projection = {
//                column
//        };
//
//        try {
//            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
//                    null);
//            if (cursor != null && cursor.moveToFirst()) {
//                final int column_index = cursor.getColumnIndexOrThrow(column);
//                return cursor.getString(column_index);
//            }
//        } finally {
//            if (cursor != null)
//                cursor.close();
//        }
//        return null;
//    }
//
//    /**
//     * @param uri The Uri to check.
//     * @return Whether the Uri authority is ExternalStorageProvider.
//     */
//    public static boolean isExternalStorageDocument(Uri uri) {
//        return "com.android.externalstorage.documents".equals(uri.getAuthority());
//    }
//
//    /**
//     * @param uri The Uri to check.
//     * @return Whether the Uri authority is DownloadsProvider.
//     */
//    public static boolean isDownloadsDocument(Uri uri) {
//        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
//    }
//
//    /**
//     * @param uri The Uri to check.
//     * @return Whether the Uri authority is MediaProvider.
//     */
//    public static boolean isMediaDocument(Uri uri) {
//        return "com.android.providers.media.documents".equals(uri.getAuthority());
//    }
}