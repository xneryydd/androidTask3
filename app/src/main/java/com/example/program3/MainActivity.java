package com.example.program3;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        TextView selectedText = findViewById(R.id.selected_text);

        // 数据准备
        String[] names = {"Lion", "Cat", "Dog", "Elephant", "Monkey", "Tiger"};
        int[] images = {
                R.drawable.lion,
                R.drawable.cat,
                R.drawable.dog,
                R.drawable.elephant,
                R.drawable.monkey,
                R.drawable.tiger
        };

        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("text", names[i]);
            item.put("image", images[i]);
            data.add(item);
        }

        // 适配器
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item,
                new String[]{"text", "image"},
                new int[]{R.id.item_text, R.id.item_image}
        );
        listView.setAdapter(adapter);

        // 点击事件：更新下方 TextView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedName = names[position];
                selectedText.setText("你选择了：" + selectedName);
            }
        });
    }
}
