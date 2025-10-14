package com.example.program3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.AbsListView;
import java.util.HashSet;

public class ListActionModeActivity extends AppCompatActivity {

    private TextView tvSelectedCount;
    private ListView listView;
    private String[] items = {"1", "2", "3", "4"};
    private HashSet<Integer> selectedPositions = new HashSet<>();
    private ActionMode actionMode;
    private MyAdapter adapter; // ✅ 保存 Adapter 引用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_actionmode);

        tvSelectedCount = findViewById(R.id.tv_selected_count);
        listView = findViewById(R.id.list_view);

        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        // 长按进入 ActionMode
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked) selectedPositions.add(position);
                else selectedPositions.remove(position);

                tvSelectedCount.setText("已选中 " + selectedPositions.size() + " 个");
                mode.setTitle(selectedPositions.size() + " selected");

                adapter.notifyDataSetChanged(); // ✅ 使用保存的 Adapter
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_context, menu);
                actionMode = mode;
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) { return false; }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    selectedPositions.clear();
                    tvSelectedCount.setText("已选中 0 个");
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                selectedPositions.clear();
                tvSelectedCount.setText("已选中 0 个");
                adapter.notifyDataSetChanged();
                actionMode = null;
            }
        });

        // 单击也可以选中
        listView.setOnItemClickListener((parent, view, position, id) -> {
            boolean checked = !selectedPositions.contains(position);
            listView.setItemChecked(position, checked);
        });
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() { return items.length; }
        @Override
        public Object getItem(int position) { return items[position]; }
        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.list_item_custom, parent, false);

            ImageView icon = convertView.findViewById(R.id.iv_android_icon);
            TextView text = convertView.findViewById(R.id.tv_number);

            text.setText(items[position]);
            icon.setImageResource(android.R.drawable.sym_def_app_icon);

            // 设置选中背景
            convertView.setActivated(listView.isItemChecked(position));

            return convertView;
        }
    }
}
