package djamelfel.gala;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by djamel on 20/10/15.
 */
public class ListViewAdapter extends ArrayAdapter<Key_List> {

        private ArrayList<Key_List> items;
        private int layoutRow;
        private Context context;

        public ListViewAdapter(Context context, int layoutRow,
                               ArrayList<Key_List> items) {
            super(context, layoutRow, items);
            this.layoutRow = layoutRow;
            this.context = context;
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View row = inflater.inflate(layoutRow, parent, false);

            KeyListHolder holder = new KeyListHolder();
            holder.keyList = items.get(position);
            holder.remove_key = (ImageButton)row.findViewById(R.id.remove_key);
            holder.remove_key.setTag(holder.keyList);

            holder.id = (TextView)row.findViewById(R.id.textViewCol1);
            holder.key = (TextView)row.findViewById(R.id.textViewCol2);

            row.setTag(holder);

            setupItem(holder);
            return row;
        }

        private void setupItem(KeyListHolder holder) {
            holder.id.setText(String.valueOf(holder.keyList.getId()));
            holder.key.setText(holder.keyList.getKey());
        }

        public static class KeyListHolder {
            Key_List keyList;
            TextView id;
            TextView key;
            ImageButton remove_key;
        }
}
