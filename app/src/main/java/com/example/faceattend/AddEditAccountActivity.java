package com.example.faceattend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;

public class AddEditAccountActivity extends AppCompatActivity {

    private ImageView btnBack, imgAvatarPreview;
    private CardView btnPickImage;
    private TextView tvFormTitle, tvPasswordLabel;
    private EditText edtFullName, edtEmpCode, edtPosition, edtEmail, edtPassword;
    private MaterialButton btnSaveAccount;

    private Uri selectedImageUri = null;
    private boolean isEditMode = false;

    // Bộ xử lý kết quả chọn ảnh từ Gallery
    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        // Hiển thị ảnh đã chọn lên ImageView
                        imgAvatarPreview.setImageURI(selectedImageUri);
                        imgAvatarPreview.setColorFilter(null); // Bỏ màu cyan overlay nếu có
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_add_edit_account);

        initViews();
        checkIntentMode();
        setupListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        imgAvatarPreview = findViewById(R.id.imgAvatarPreview);
        btnPickImage = findViewById(R.id.btnPickImage);
        tvFormTitle = findViewById(R.id.tvFormTitle);
        tvPasswordLabel = findViewById(R.id.tvPasswordLabel);

        edtFullName = findViewById(R.id.edtFullName);
        edtEmpCode = findViewById(R.id.edtEmpCode);
        edtPosition = findViewById(R.id.edtPosition);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnSaveAccount = findViewById(R.id.btnSaveAccount);
    }

    private void checkIntentMode() {
        // Kiểm tra intent xem là Sửa hay Thêm mới
        Intent intent = getIntent();
        if (intent.hasExtra("EMP_CODE")) {
            isEditMode = true;
            tvFormTitle.setText("CHỈNH SỬA TÀI KHOẢN");
            btnSaveAccount.setText("CẬP NHẬT TÀI KHOẢN");

            // Fill dữ liệu cũ vào form
            edtFullName.setText(intent.getStringExtra("EMP_NAME"));
            edtEmpCode.setText(intent.getStringExtra("EMP_CODE"));
            edtEmpCode.setEnabled(false); // Mã NV không cho sửa
            edtPosition.setText(intent.getStringExtra("EMP_POSITION"));
            edtEmail.setText(intent.getStringExtra("EMP_EMAIL"));

            // Ẩn/Đổi mật khẩu khi ở chế độ chỉnh sửa
            tvPasswordLabel.setText("Mật khẩu mới (Để trống nếu giữ nguyên)");
        }
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        // Chọn ảnh từ bộ nhớ thiết bị
        btnPickImage.setOnClickListener(v -> openGallery());

        // Lưu dữ liệu
        btnSaveAccount.setOnClickListener(v -> saveAccountData());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    private void saveAccountData() {
        String name = edtFullName.getText().toString().trim();
        String code = edtEmpCode.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (name.isEmpty() || code.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ các thông tin bắt buộc (*)", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEditMode) {
            // Logic Cập nhật API / Database
            Toast.makeText(this, "Đã cập nhật thông tin nhân viên: " + name, Toast.LENGTH_SHORT).show();
        } else {
            // Logic Thêm mới API / Database
            Toast.makeText(this, "Đã tạo tài khoản mới thành công!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}