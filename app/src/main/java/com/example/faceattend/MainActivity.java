package com.example.faceattend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // Quy đổi 24dp sang Pixel tương ứng với màn hình thiết bị
            int padding24dp = (int) (24 * v.getResources().getDisplayMetrics().density);

            // Cộng padding 24dp vào cùng với systemBars insets
            v.setPadding(
                    padding24dp + systemBars.left,
                    padding24dp + systemBars.top,
                    padding24dp + systemBars.right,
                    padding24dp + systemBars.bottom
            );
            return insets;
        });
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnFaceLogin = findViewById(R.id.btnFaceLogin);

        // Lắng nghe sự kiện chuyển sang HomeActivity
        View.OnClickListener openHomeScreen = v -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            // Bỏ comment dòng bên dưới nếu muốn đóng màn hình Login sau khi đăng nhập:
            // finish();
        };

        if (btnLogin != null) {
            btnLogin.setOnClickListener(openHomeScreen);
        }
        if (btnFaceLogin != null) {
            btnFaceLogin.setOnClickListener(openHomeScreen);
        }
    }
}