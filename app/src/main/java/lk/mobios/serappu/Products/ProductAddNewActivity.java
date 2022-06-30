package lk.mobios.serappu.Products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.zelory.compressor.Compressor;
import pub.devrel.easypermissions.EasyPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import lk.mobios.serappu.BuildConfig;
import lk.mobios.serappu.R;
import lk.mobios.serappu.services.BitmapUtils;

public class ProductAddNewActivity extends AppCompatActivity {
    File photoFile;
    Uri imageUri;

    private static final int PICK_IMAGE = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_STORAGE_PERMISSION = 1;
    private static final String FILE_PROVIDER_AUTHORITY = "${applicationId}.provider";

    private String mTempPhotoPath, encodedImage;
    private Bitmap mResultsBitmap;
    private Bitmap compressedImageBitmap;


    boolean image = false;
    Bitmap selectedImage;

    ImageView imageView;
    TextView btn_gallery, btn_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add_new);

        btn_gallery = findViewById(R.id.btn_gallery);
        btn_camera = findViewById(R.id.btn_camera);
        imageView = findViewById(R.id.imageView);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera();
            }
        });

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });


    }

    private void launchCamera() {

        // Create the capture image intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the temporary File where the photo should go
            photoFile = null;
            try {
                photoFile = BitmapUtils.createTempImageFile(this);
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                // Get the path of the temporary file
                mTempPhotoPath = photoFile.getAbsolutePath();

                // Get the content URI for the image file
                Uri photoURI = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID,
                        photoFile);


                // Add the URI so the camera can store the image
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                // Launch the camera activity
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // If the image capture activity was called and was successful
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Process the image and set it to the TextView
            processAndSetImage();
        } else if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

            final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                encodedImage = encodeImage(selectedImage);
                image = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            photoFile = null;
            compressedImageBitmap = null;

            // Otherwise, delete the temporary image file
            BitmapUtils.deleteImageFile(this, mTempPhotoPath);
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);


        // Called when you request permission to read and write to external storage
        switch (requestCode) {
            case REQUEST_STORAGE_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // If you get permission, launch the camera

                } else {
                    // If you do not get permission, show a Toast
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void processAndSetImage() {

        // Resample the saved image to fit the ImageView
        mResultsBitmap = BitmapUtils.resamplePic(this, mTempPhotoPath);

        // Set the new bitmap to the ImageView
        try {
            compressedImageBitmap = new Compressor(ProductAddNewActivity.this).compressToBitmap(photoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setBackgroundResource(0);
        imageView.setImageBitmap(compressedImageBitmap);


        encodedImage = encodeImage(compressedImageBitmap);


    }


    public void grantAppPermissions() {
        int permsnaccessNetwork = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        int permsnInternet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        int permsnReadPhoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int permsnAccessCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permsnWriteExternal = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);


        if (permsnaccessNetwork != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 2);
        }

        if (permsnInternet != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 3);
        }
//
//        if (permsnReadPhoneState != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},4);
//        }

        if (permsnAccessCamera != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        }

//        if (permsnWriteExternal != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
//        }
    }
}
