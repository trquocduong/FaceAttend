package com.example.faceattend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class AccountManagerActivity extends AppCompatActivity {

    private ImageView btnBack, btnMoreOptions;
    private TextView tvStatTotal, tvStatActive, tvStatLocked;
    private EditText edtSearch;
    private MaterialButton chipFilterAll, chipFilterActive, chipFilterLocked;
    private RecyclerView rvEmployeeList;
    private ExtendedFloatingActionButton btnFabAddAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_account_manager);

        initViews();
        setupRecyclerView();
        setupListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnMoreOptions = findViewById(R.id.btnMoreOptions);

        tvStatTotal = findViewById(R.id.tvStatTotal);
        tvStatActive = findViewById(R.id.tvStatActive);
        tvStatLocked = findViewById(R.id.tvStatLocked);

        edtSearch = findViewById(R.id.edtSearch);
        chipFilterAll = findViewById(R.id.chipFilterAll);
        chipFilterActive = findViewById(R.id.chipFilterActive);
        chipFilterLocked = findViewById(R.id.chipFilterLocked);

        rvEmployeeList = findViewById(R.id.rvEmployeeList);
        btnFabAddAccount = findViewById(R.id.btnFabAddAccount);
    }

    private void setupRecyclerView() {
        rvEmployeeList.setLayoutManager(new LinearLayoutManager(this));
        // Gắn Adapter của danh sách nhân viên ở đây
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnMoreOptions.setOnClickListener(v ->
                Toast.makeText(this, "Tùy chọn xuất file danh sách / Cấu hình phân quyền", Toast.LENGTH_SHORT).show()
        );

        // Mở Form Thêm Tài Khoản Nhanh khi nhấn nút FAB
        btnFabAddAccount.setOnClickListener(v -> {
            Intent intent = new Intent(AccountManagerActivity.this, AddEditAccountActivity.class);
            startActivity(intent);
        });

        // Xử lý chuyển trạng thái bộ lọc Chip
        chipFilterAll.setOnClickListener(v -> selectFilterChip(chipFilterAll));
        chipFilterActive.setOnClickListener(v -> selectFilterChip(chipFilterActive));
        chipFilterLocked.setOnClickListener(v -> selectFilterChip(chipFilterLocked));
    }

    private void selectFilterChip(MaterialButton selectedChip) {
        // Reset Style các Chip về mờ
        resetChipStyle(chipFilterAll);
        resetChipStyle(chipFilterActive);
        resetChipStyle(chipFilterLocked);

        // Active Chip được chọn (Chuyển mã màu Hex thành ColorStateList)
        selectedChip.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ACC1")));
        selectedChip.setTextColor(getResources().getColor(android.R.color.white, getTheme()));
        selectedChip.setStrokeWidth(0);
    }

    private void resetChipStyle(MaterialButton chip) {
        chip.setBackgroundTintList(getColorStateList(android.R.color.white));
        chip.setTextColor(getResources().getColor(android.R.color.darker_gray, getTheme()));
        chip.setStrokeWidth(1);
    }

    // Hàm gọi khi nhấn vào Chỉnh sửa nhân viên trong RecyclerView
    public void navigateToEditAccount(String empCode, String name, String role, String email) {
        Intent intent = new Intent(AccountManagerActivity.this, AddEditAccountActivity.class);
        intent.putExtra("EMP_CODE", empCode);
        intent.putExtra("EMP_NAME", name);
        intent.putExtra("EMP_POSITION", role);
        intent.putExtra("EMP_EMAIL", email);
        startActivity(intent);
    }
}