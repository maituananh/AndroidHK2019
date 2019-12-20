package com.example.myappandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dao.BookDao;
import com.example.model.Book;
import com.example.util.RandomFileNameImage;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class UpdateBook extends AppCompatActivity {
    Database database;
    BookDao bookDao;

    final private int REQUEST_CODE_CAMERA = 123;
    public static final int PICK_IMAGE = 1;

    private int idBook;
    EditText nameBook;
    EditText nameAuthor;
    EditText price;
    EditText quantity;
    EditText description;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookDao = new BookDao(database);

        Bundle extras = getIntent().getExtras();
        idBook = extras.getInt("idBook"); // LẤY LẠI ID TỪ Detail edit book
        mappingData(idBook);
    }

    // lấy dữ liệu cũ của book sau đó gán vào view
    public void mappingData(int idBook) {
        nameBook = findViewById(R.id.nameBookUpdate);
        nameAuthor = findViewById(R.id.nameAuthorUpdate);
        price = findViewById(R.id.priceUpdate);
        quantity = findViewById(R.id.quantityUpdate);
        description = findViewById(R.id.descriptionUpdate);
        image = findViewById(R.id.imageUpdate);

        Book book = bookDao.findBookById(idBook);
        nameBook.setText(book.getName());
        nameAuthor.setText(book.getAuthor());
        price.setText(book.getPrice());
        quantity.setText(book.getQuantity());
        description.setText(book.getDescription());

        // chuyển byte từ data sang bitmap và gắn vào imageView
        byte[] imageByte = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
        image.setImageBitmap(bitmap);
    }

    // khi click vào nút button camera thì cho chụp ảnh
    public void buttonCamera(View view) {
        // chỉ chạy được từ android <= 5.0
        Intent intent = actionImage();
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    public Intent actionImage() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        return intent;
    }

    // lấy kết quả sau khi chụp ảnh gắn vào image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // sau khi chụp ảnh xong
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CAMERA && data != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data"); // data là key mặc định
                image.setImageBitmap(bitmap);

                // save image to CD Phone
//                Intent intent = actionImage();
//                File dir = Environment.getExternalStorageDirectory();
//                dir.mkdirs();
//                String savePath = dir.getAbsolutePath() + "/AndroidImage/" + RandomFileNameImage.createFileName() + ".jpg";
//                File imageFile = new File(savePath);
//                Uri imageUri = Uri.fromFile(imageFile);
//                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        }

        // sau khi lựa chọn ảnh từ thư viện
        if (requestCode == PICK_IMAGE) {
            Uri selectedImage = data.getData();
            image.setImageURI(selectedImage);
            System.out.println(selectedImage);
        }
    }

    public void buttonSelectImageToPhone(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    public void buttonUpdate(View view) {
        Book book = new Book();
        book.setId(idBook);
        book.setName(nameBook.getText().toString());
        book.setAuthor(nameAuthor.getText().toString());
        book.setPrice(price.getText().toString());
        book.setQuantity(quantity.getText().toString());
        book.setDescription(description.getText().toString());

        // chuyen data image sang byte[]
        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] picture = byteArrayOutputStream.toByteArray();
        book.setImage(picture);

        bookDao.updateBookById(book);
        Intent intent = new Intent(this, HomeListBook.class);
        startActivity(intent);
    }
}
