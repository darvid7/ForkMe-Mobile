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
        datetimeText.setText(repo.getUpdatedAt());

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
