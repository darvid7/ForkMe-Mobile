package dlei.forkme.gui.adapter;

import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import dlei.forkme.R;
import dlei.forkme.model.Repository;
import dlei.forkme.state.LanguageColor;

import java.util.List;
import java.util.Locale;

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
        if (position > mDeck.size()) {
            Log.w("SwipeDeckAdapater: ", "position: " + position);
            return null;
        }
        return mDeck.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.repository_swipe_card, parent, false);
        }

        Repository repo = mDeck.get(position);
        String repoFullName = repo.getFullName();
        String repoDescription = repo.getDescription();

        // Set repo full name.
        // TODO: Split up into ownername/reponame and make it clickable?
        AppCompatTextView repoCardFullNameText = (AppCompatTextView) convertView.findViewById(R.id.repositoryCardFullNameText);
        repoCardFullNameText.setText(repoFullName);

        // Set repo description.
        AppCompatTextView repoCardDescriptionText = (AppCompatTextView) convertView.findViewById(R.id.repositoryCardDescriptionText);
        repoCardDescriptionText.setText(repoDescription);

        // Set owner avatar icon.
        AppCompatImageView ownerIcon = (AppCompatImageView) convertView.findViewById(R.id.repoUserIconImageView);
        String url = repo.getAvatarUrl();
        if (url != null) {
            Picasso.with(convertView.getContext()).load(url).into(ownerIcon);
        }

        // Set updated date time.
        AppCompatTextView datetimeText = (AppCompatTextView) convertView.findViewById(R.id.updatedAtText);
        datetimeText.setText(repo.getUpdatedAt());

        // Set language and language circle color.
        AppCompatTextView languageText = (AppCompatTextView) convertView.findViewById(R.id.languageText);
        AppCompatImageView languageCircleImage = (AppCompatImageView) convertView.findViewById(R.id.languageCircleImageView);
        String language = repo.getLanguage();
        String languageColorAsHex = LanguageColor.getColor(language);
        // TODO: Refactor this into a fragment as lots of duplicate code to set this up across different activities?
        if (languageColorAsHex != null) {
            int languageColorAsInt = Color.parseColor(languageColorAsHex);
            // TODO: Only draw on the inside of the circle.
            languageCircleImage.setColorFilter(languageColorAsInt);
        }
        languageText.setText(language);

        // Set fork count.
        AppCompatTextView forkCountText = (AppCompatTextView) convertView.findViewById(R.id.forkCountText);
        forkCountText.setText(String.format(Locale.getDefault(), "%d", repo.getForkCount()));

        // Set star count.
        AppCompatTextView starCountText = (AppCompatTextView) convertView.findViewById(R.id.starCountText);
        starCountText.setText(String.format(Locale.getDefault(), "%d", repo.getStargazerCount()));

        return convertView;
    }

}
