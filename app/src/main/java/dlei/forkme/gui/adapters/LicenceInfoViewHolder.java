package dlei.forkme.gui.adapters;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

import dlei.forkme.R;
import dlei.forkme.gui.activities.InAppWebView;


/**
 * ViewHolder for Licence cards shown in RecyclerView.
 * Shows the licence, the library, the owner and has buttons that when pressed takes the user to
 * the licence or library.
 */
public class LicenceInfoViewHolder extends RecyclerView.ViewHolder {
    private AppCompatImageView mPublisherIcon;
    private AppCompatTextView mLibraryText;
    private AppCompatTextView mLicenceText;
    private AppCompatTextView mUsedForText;
    private AppCompatTextView mAssociatedText;
    private AppCompatButton mLibraryButton;
    private AppCompatButton mLicenceButton;

    public LicenceInfoViewHolder(View view) {
        super(view);
        mPublisherIcon = (AppCompatImageView) view.findViewById(R.id.licencePublisherImage);
        mLibraryText = (AppCompatTextView) view.findViewById(R.id.libraryText);
        mLicenceText = (AppCompatTextView) view.findViewById(R.id.licenceText);
        mUsedForText = (AppCompatTextView) view.findViewById(R.id.usedForText);
        mLibraryButton = (AppCompatButton) view.findViewById(R.id.viewLibraryButton);
        mLicenceButton = (AppCompatButton) view.findViewById(R.id.viewLicenceButton);
    }

    // Setters.

    public void setUsedForText(String usedFor) {
        mUsedForText.setText(usedFor);
    }

    public void setLibraryText(String libraryText) {
        mLibraryText.setText(libraryText);
    }

    public void setLicenceText(String licence) {
        mLicenceText.setText(licence);
    }

    public void setPublisherIcon(String iconUrl) {
        if (iconUrl != null && !iconUrl.equals("")) {
            Picasso.with(mPublisherIcon.getContext()).load(iconUrl).into(mPublisherIcon);
        } else {
            Log.wtf("LicenceInfoBVH: ", "setPublisherIcon: image url is null");
        }
    }

    // OnCLickMethods for buttons.
    public void setLibraryButtonOnClick(final String libraryUrl) {
        mLibraryButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(mPublisherIcon.getContext(), InAppWebView.class);
                        i.putExtra("url", libraryUrl);
                        mPublisherIcon.getContext().startActivity(i);
                    }
                }
        );
    }

    public void setLicenceButtonOnClick(final String licenceUrl) {
        mLicenceButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(mPublisherIcon.getContext(), InAppWebView.class);
                        i.putExtra("url", licenceUrl);
                        mPublisherIcon.getContext().startActivity(i);
                    }
                }
        );

    }



}
