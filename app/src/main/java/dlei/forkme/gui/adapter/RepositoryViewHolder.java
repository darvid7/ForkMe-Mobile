package dlei.forkme.gui.adapter;

import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Locale;

import dlei.forkme.R;
import dlei.forkme.helpers.LanguageColor;

/**
 * ViewHolder for Repositories shown in RecyclerView.
 */
public class RepositoryViewHolder extends RecyclerView.ViewHolder {
    private AppCompatImageView mLanguageCircleImage;
    private AppCompatTextView mOwnerUserNameText;
    private AppCompatTextView mRepoNameText;
    private AppCompatTextView mRepoDescriptionText;
    private AppCompatTextView mLanguageText;
    private AppCompatTextView mForkCountText;
    private AppCompatTextView mStarCountText;

    public RepositoryViewHolder(View view) {
        super(view);
        // Instantiate UI elements.
        mLanguageCircleImage = (AppCompatImageView) view.findViewById(R.id.languageCircleImageView);
        mOwnerUserNameText = (AppCompatTextView) view.findViewById(R.id.ownerUserNameText);
        mRepoNameText = (AppCompatTextView) view.findViewById(R.id.repositoryNameText);
        mRepoDescriptionText = (AppCompatTextView) view.findViewById(R.id.repositoryDescriptionText);
        mLanguageText = (AppCompatTextView) view.findViewById(R.id.languageText);
        mForkCountText = (AppCompatTextView) view.findViewById(R.id.forkCountText);
        mStarCountText = (AppCompatTextView) view.findViewById(R.id.starCountText);
    }

    public void setOwnerUserNameText(String owner) {
        mOwnerUserNameText.setText(owner + "/");
    }

    public void setRepoNameText(String repoName) {
        mRepoNameText.setText(repoName);
    }

    public void setRepoDescriptionText(String repoDescription) {
        mRepoDescriptionText.setText(repoDescription);
    }

    /**
     * Sets the language and the languageCircleImage color.
     * @param language programming language for the Github repository.
     */
    public void setLanguage(String language) {
        int languageColorAsInt = Color.parseColor("#ffffff");
        if (language != null) {
            String languageColorAsHex = LanguageColor.getColor(language);
            // TODO: Refactor this into a fragment as lots of duplicate code to set this up across different activities?
            if (languageColorAsHex != null) {
                languageColorAsInt = Color.parseColor(languageColorAsHex);
                // TODO: Only draw on the inside of the circle.
            }
        } else {
            language = "None";
        }
        mLanguageCircleImage.setColorFilter(languageColorAsInt);
        mLanguageText.setText(language);
    }

    public void setForkCountText(int forkCount) {
        mForkCountText.setText(String.format(Locale.getDefault(), "%d", forkCount));
    }

    public void setStarCountText(int starCount) {
        mStarCountText.setText(String.format(Locale.getDefault(), "%d", starCount));
    }



}
