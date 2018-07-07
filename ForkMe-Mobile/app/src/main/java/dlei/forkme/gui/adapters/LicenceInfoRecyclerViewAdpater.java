package dlei.forkme.gui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import dlei.forkme.R;
import dlei.forkme.model.Licence;

public class LicenceInfoRecyclerViewAdpater extends RecyclerView.Adapter<LicenceInfoViewHolder>{
    private ArrayList<Licence> mLicenceList;

    public LicenceInfoRecyclerViewAdpater(ArrayList<Licence> licences) {
        mLicenceList = licences; // Just a pointer, not a deep copy.
    }

    /**
     * Get number of items in mLicenceList.
     * @return size of mLicenceList.
     */
    @Override
    public int getItemCount() {
        return mLicenceList.size();
    }

    /**
     * Inflate a view to be used to display an item in the adapter.
     * @param parent parent view.
     * @param viewType type of view.
     * @return LicenceInfoViewHolder, the view to display.
     */
    @Override
    public LicenceInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.licence_list_card, parent, false);
        LicenceInfoViewHolder licenceCard = new LicenceInfoViewHolder(itemView);
        return licenceCard;
    }

    /**
     * For each view item displayed by the adapter, this is called to bind the licence data to the view
     * so the view item displays the right information.
     * Also sets listener for buttons by calling methods in LicenceInfoViewHolder.
     * @param licenceCard, LicenceInfoViewHolder view to display for each Licence in mLicenceList.
     * @param position, int position of Licence.
     */
    @Override
    public void onBindViewHolder(LicenceInfoViewHolder licenceCard, int position) {

        final Licence licence = mLicenceList.get(position);

        // Set UI elements.
        licenceCard.setLibraryButtonOnClick(licence.getLibraryUrl());
        licenceCard.setLicenceButtonOnClick(licence.getLicenceUrl());
        licenceCard.setLicenceText(licence.getLicence());
        licenceCard.setLibraryText(licence.getLibraryName());
        licenceCard.setPublisherIcon(licence.getIconUrl());
        licenceCard.setUsedForText(licence.getUsedFor());

    }
}
