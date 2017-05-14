package dlei.forkme.gui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import dlei.forkme.R;
import dlei.forkme.model.Repository;

import java.util.List;

/**
 * Created by David on 15/05/2017.
 */

public class SwipeDeckAdapter extends BaseAdapter {

    private List<Repository> mDeck;

    public SwipeDeckAdapter(List<Repository> deck) {
        mDeck = deck;
    }

    @Override
    public int getCount() {
        return mDeck.size();
    }

    @Override
    public Repository getItem(int position) {
        return mDeck.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        // LayoutInflater inflator = LayoutInflater.from(this.context);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.repository_card, parent, false);
        }

        TextView textViewCard = (TextView) convertView.findViewById(R.id.repositoryCardText);
        textViewCard.setText(mDeck.get(position).toString());
        return convertView;
    }
}
