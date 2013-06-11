package pack.bi.adapters;

import pack.bi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<String> elements;

    public ItemsAdapter(Context context, List<String> elems) {
        inflater = LayoutInflater.from(context);
        elements = elems;
    }

    public int getCount() {
        if (elements == null) {
            return 0;
        }
        return elements.size();
    }

    public Object getItem(int position) {
        return elements.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.simple_row, null);
        }

        String product = elements.get(position);

        TextView pairingTxt = (TextView) view.findViewById(R.id.contentText);
        pairingTxt.setText(product);

        return view;
    }
}
