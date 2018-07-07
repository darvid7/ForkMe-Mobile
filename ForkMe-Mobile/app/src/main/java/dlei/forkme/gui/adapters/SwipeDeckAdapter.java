package dlei.forkme.gui.adapters;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import dlei.forkme.R;
import dlei.forkme.model.Repository;
import dlei.forkme.helpers.LanguageColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Adapter for swipe deck in TrendingRepositoryActivity.
 */
public class SwipeDeckAdapter extends BaseAdapter {

    private List<Repository> mDeck;

    public SwipeDeckAdapter(List<Repository> deck) {
        mDeck = deck;
    }

    /**
     * Get number of items in mDeck.
     * @return size of mDeck.
     */
    @Override
    public int getCount() {
        return mDeck.size();
    }

    /**
     * Get Repository object from mDeck at index position.
     * @param position position deck is currently up to (an index)
     * @return Repository at that position.
     */
    @Override
    public Repository getItem(int position) {
        if (position > mDeck.size()) {
            // Should not happen.
            Log.w("SwipeDeckAdapter: ", "position: " + position);
            return null;
        }
        return mDeck.get(position);
    }

    /**
     * Get it of an item at index position.
     * @param position position the deck is currently up to.
     * @return id of item at index position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Set view for the topmost cards on the deck.
     * @param position position deck is up to.
     * @param convertView view to inflate.
     * @param parent parent view group.
     * @return inflated view.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.repository_swipe_card, parent, false);
        }

        Repository repo = mDeck.get(position);

        // Set up topics.
        // Bullet from: https://stackoverflow.com/questions/4992794/how-to-add-bulleted-list-to-android-application

        ArrayList<String> topics = repo.getTopics();

        AppCompatTextView topic1 = (AppCompatTextView) convertView.findViewById(R.id.topic1);
        String bullet = (String) convertView.getResources().getText(R.string.bullet);

        if (topics.size() > 1) {
            topic1.setText(String.format("%s %s", bullet, topics.get(0)));
        } else {
            topic1.setText(String.format("%s %s", bullet,
                    convertView.getResources().getText(R.string.no_topics)));
        }

        if (topics.size() > 2) {
            AppCompatTextView topic2 = (AppCompatTextView) convertView.findViewById(R.id.topic2);
            topic2.setText(String.format("%s %s", bullet, topics.get(1)));
        }

        if (topics.size() > 3) {
            AppCompatTextView topic3 = (AppCompatTextView) convertView.findViewById(R.id.topic3);
            topic3.setText(String.format("%s %s", bullet, topics.get(2)));
        }

        if (topics.size() > 4) {
            AppCompatTextView topic4 = (AppCompatTextView) convertView.findViewById(R.id.topic4);
            topic4.setText(String.format("%s %s", bullet, topics.get(3)));
        }

        if (topics.size() > 5) {
            AppCompatTextView topic5 = (AppCompatTextView) convertView.findViewById(R.id.topic5);
            topic5.setText(String.format("%s %s", bullet, topics.get(4)));
        }

        String repoFullName = repo.getFullName();
        String repoDescription = repo.getDescription();

        // Set repo full name.
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
        String nicerDate = repo.getUpdatedAt().substring(0, 10);
        datetimeText.setText(nicerDate);

        // Set language and language circle color.
        AppCompatTextView languageText = (AppCompatTextView) convertView.findViewById(R.id.languageText);
        AppCompatImageView languageCircleImage = (AppCompatImageView) convertView.findViewById(R.id.languageCircleImageView);
        String language = repo.getLanguage();
        LanguageColor.setLanguageOnView(language, languageCircleImage, languageText);

        // Set fork count.
        AppCompatTextView forkCountText = (AppCompatTextView) convertView.findViewById(R.id.forkCountText);
        forkCountText.setText(String.format(Locale.getDefault(), "%d", repo.getForkCount()));

        // Set star count.
        AppCompatTextView starCountText = (AppCompatTextView) convertView.findViewById(R.id.starCountText);
        starCountText.setText(String.format(Locale.getDefault(), "%d", repo.getStargazerCount()));

        return convertView;
    }

}
