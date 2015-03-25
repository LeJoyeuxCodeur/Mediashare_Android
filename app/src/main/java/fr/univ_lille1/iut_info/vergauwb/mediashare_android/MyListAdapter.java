package fr.univ_lille1.iut_info.vergauwb.mediashare_android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<BinaryList>{
    private Context c;
    private List<BinaryList> list;

    public MyListAdapter(Context context, int resource, int textViewResourceId, List<BinaryList> objects) {
        super(context, resource, textViewResourceId, objects);
        this.c = context;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View maVue = super.getView(position, convertView, parent);
        ImageView myImage = (ImageView)maVue.findViewById(R.id.imageList);
        TextView pseudo = (TextView)maVue.findViewById(R.id.pseudo);
        TextView description = (TextView)maVue.findViewById(R.id.description);

        myImage.setImageDrawable(list.get(position).getImage());
        pseudo.setText(list.get(position).getPseudo());
        description.setText(list.get(position).getDescription());

        return maVue;
    }
}
