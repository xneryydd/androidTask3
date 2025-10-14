package com.example.program3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DialogDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);

        Button btnShow = findViewById(R.id.btn_show_dialog);

        btnShow.setOnClickListener(v -> showLoginDialog());
    }

    private void showLoginDialog() {
        // 1. 加载自定义布局
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_login, null);

        // 2. 获取布局内的控件
        EditText editUsername = dialogView.findViewById(R.id.edit_username);
        EditText editPassword = dialogView.findViewById(R.id.edit_password);

        // 3. 创建 AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录");
        builder.setView(dialogView);
        builder.setCancelable(false); // 不允许点击外部取消

        // 4. 设置按钮
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = editUsername.getText().toString().trim();
                String pass = editPassword.getText().toString().trim();
                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(DialogDemoActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DialogDemoActivity.this, "账号：" + user + "\n密码：" + pass, Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());

        // 5. 显示对话框
        builder.create().show();
    }
}
