package com.example.program3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.Color;
import android.util.TypedValue;
import androidx.appcompat.widget.PopupMenu;

public class MenuDemoActivity extends AppCompatActivity {

    private TextView tvTest;
    private Button btnShowMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_demo);

        tvTest = findViewById(R.id.tv_test);
        btnShowMenu = findViewById(R.id.btn_show_menu);

        // 点击按钮显示菜单
        btnShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });
    }

    private void showPopupMenu(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.menu_demo, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.font_small) {
                    tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                } else if (id == R.id.font_medium) {
                    tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                } else if (id == R.id.font_large) {
                    tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                } else if (id == R.id.color_red) {
                    tvTest.setTextColor(Color.RED);
                } else if (id == R.id.color_black) {
                    tvTest.setTextColor(Color.BLACK);
                } else if (id == R.id.normal_item) {
                    Toast.makeText(MenuDemoActivity.this, "你点击了普通菜单项", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        popup.show();
    }
}
