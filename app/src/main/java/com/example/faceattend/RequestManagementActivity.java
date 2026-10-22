package com.example.faceattend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RequestManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_management);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootRequestManagement), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        Spinner spinnerRequestType = findViewById(R.id.spinnerRequestType);
        if (spinnerRequestType != null) {
            String[] requestTypes = new String[]{
                    "Giải trình Đi muộn / Về sớm",
                    "Xác nhận Quên điểm danh",
                    "Xin nghỉ phép năm",
                    "Nghỉ ốm / Việc cá nhân"
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, requestTypes);
            spinnerRequestType.setAdapter(adapter);
        }

        ImageView navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                Intent intent = new Intent(RequestManagementActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        ImageView navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                Intent intent = new Intent(RequestManagementActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}