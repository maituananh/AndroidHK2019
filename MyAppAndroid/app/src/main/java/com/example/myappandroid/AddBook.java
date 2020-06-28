package com.example.myappandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dao.BookDao;
import com.example.model.Book;
import com.example.model.KeepInformation;
import com.example.util.RandomFileNameImage;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class AddBook extends AppCompatActivity {
    EditText nameBook;
    EditText nameAuthor;
    EditText price;
    EditText quantity;
    EditText description;
    ImageView image;

    Database database;
    BookDao bookDao;
    final private int REQUEST_CODE_CAMERA = 456;
    public static final int PICK_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookDao = new BookDao(database);
        mapping();
    }

    public void mapping() {
        nameBook = findViewById(R.id.nameBookAdd);
        nameAuthor = findViewById(R.id.nameAuthorAdd);
        price = findViewById(R.id.priceAdd);
        quantity = findViewById(R.id.quantityAdd);
        description = findViewById(R.id.descriptionAdd);
        image = findViewById(R.id.imageAdd);
    }

    public void buttonAddBook(View view){
        Book book = new Book();
        book.setName(nameBook.getText().toString());
        book.setAuthor(nameAuthor.getText().toString());
        book.setDescription(description.getText().toString());
        book.setQuantity(quantity.getText().toString());
        book.setPrice(price.getText().toString());

        // chuyen data image sang byte[]
        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] picture = byteArrayOutputStream.toByteArray();
        book.setImage(picture);

        // save image to CD Phone


        bookDao.addNewBook(book);
        Intent intent = new Intent(this, HomeListBook.class);
        startActivity(intent);
    }

    public Intent actionImage() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        return intent;
    }

    public void buttonCamera(View view) {
        // chỉ chạy được từ android <= 5.0
//        Intent intent = actionImage();
//        startActivityForResult(intent, REQUEST_CODE_CAMERA);

        // nếu > 5.0 thì phải xin quyền
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
    }

    public void buttonSelectImageToPhone(View view) {
        // chỉ chạy được từ android <= 5.0
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

        // nếu > 5.0 thì phải xin quyền
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE);
    }

    // PHẦN MỞ CAMERA CHO ANDROID > 5.0
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                } else {
                    Toast.makeText(this, "Bạn Không Cho Phép Mở CAMERA", Toast.LENGTH_SHORT);
                }
                break;
            case PICK_IMAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_IMAGE);
                } else {
                    Toast.makeText(this, "Bạn Không Cho Phép truy cập ảnh", Toast.LENGTH_SHORT);
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // lấy kết quả sau khi chụp ảnh gắn vào image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // sau khi chụp ảnh xong
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CAMERA && data != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data"); // data là key mặc định
                image.setImageBitmap(bitmap);
            }
        }

        // sau khi lựa chọn ảnh từ thư viện
        if (requestCode == PICK_IMAGE) {
            Uri selectedImage = data.getData();
            image.setImageURI(selectedImage);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // sử lý menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (KeepInformation.getRole().toUpperCase().equals("ADMIN")) {
            getMenuInflater().inflate(R.menu.layout_menu_admin, menu);
        } else {
            getMenuInflater().inflate(R.menu.layout_menu_user, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if (KeepInformation.getRole().toUpperCase().equals("ADMIN")) {
            switch (item.getItemId()) {
                case R.id.addBookAdmin:
                    intent = new Intent(this, AddBook.class);
                    startActivity(intent);
                    break;
                case R.id.listBookAdmin:
                    intent = new Intent(this, HomeListBook.class);
                    startActivity(intent);
                    break;
                case R.id.searchBookAdmin:
                    intent = new Intent(this, SearchBook.class);
                    startActivity(intent);
                    break;
                case R.id.bookBookedAdmin:
                    intent = new Intent(this, BookingListOfUser.class);
                    startActivity(intent);
                    break;
                case R.id.logoutAdmin:
                    KeepInformation.setIdUser(0);
                    KeepInformation.setRole("");
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        } else {
            switch (item.getItemId()) {
                case R.id.listBookUser:
                    intent = new Intent(this, HomeListBook.class);
                    startActivity(intent);
                    break;
                case R.id.searchBookUser:
                    intent = new Intent(this, SearchBook.class);
                    startActivity(intent);
                    break;
                case R.id.bookBookedUser:
                    intent = new Intent(this, BookingListOfUser.class);
                    startActivity(intent);
                    break;
                case R.id.logoutUser:
                    KeepInformation.setIdUser(0);
                    KeepInformation.setRole("");
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
