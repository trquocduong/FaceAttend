package com.example.faceattend;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.button.MaterialButton;

public class ManagerReportsActivity extends AppCompatActivity {

    private ImageView btnBack, btnFilterDepartment;
    private MaterialButton btnExportExcel, btnExportPDF;
    private TextView tvTotalEmployees, tvOnTimeRate, tvTotalOT, tvLateCount, tvViewAll;
    private CardView btnFloatingQR;
    private ImageView navHome, navSchedule, navReports, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_reports);

        initViews();
        setupListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnFilterDepartment = findViewById(R.id.btnFilterDepartment);
        btnExportExcel = findViewById(R.id.btnExportExcel);
        btnExportPDF = findViewById(R.id.btnExportPDF);

        tvTotalEmployees = findViewById(R.id.tvTotalEmployees);
        tvOnTimeRate = findViewById(R.id.tvOnTimeRate);
        tvTotalOT = findViewById(R.id.tvTotalOT);
        tvLateCount = findViewById(R.id.tvLateCount);
        tvViewAll = findViewById(R.id.tvViewAll);

        btnFloatingQR = findViewById(R.id.btnFloatingQR);
        navHome = findViewById(R.id.navHome);
        navSchedule = findViewById(R.id.navSchedule);
        navReports = findViewById(R.id.navReports);
        navProfile = findViewById(R.id.navProfile);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnFilterDepartment.setOnClickListener(v ->
                Toast.makeText(this, "Lọc danh sách theo Phòng ban / Chi nhánh", Toast.LENGTH_SHORT).show()
        );

        btnExportExcel.setOnClickListener(v ->
                Toast.makeText(this, "Đang khởi tạo và xuất file Excel (.xlsx)...", Toast.LENGTH_LONG).show()
        );

        btnExportPDF.setOnClickListener(v ->
                Toast.makeText(this, "Đang xuất dữ liệu ra file PDF...", Toast.LENGTH_LONG).show()
        );

        tvViewAll.setOnClickListener(v ->
                Toast.makeText(this, "Mở danh sách đầy đủ tài khoản nhân viên", Toast.LENGTH_SHORT).show()
        );

        btnFloatingQR.setOnClickListener(v ->
                Toast.makeText(this, "Mở trình quét AI / QR Check-in", Toast.LENGTH_SHORT).show()
        );

        navHome.setOnClickListener(v -> finish());
    }
}