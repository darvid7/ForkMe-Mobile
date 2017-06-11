package dlei.forkme.gui.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dlei.forkme.R;
import dlei.forkme.gui.activities.github.UserViewActivity;
import dlei.forkme.model.DeveloperContactInfo;

public class DeveloperContactRecyclerViewAdapter extends RecyclerView.Adapter<DeveloperContactViewHolder>{

    private ArrayList<DeveloperContactInfo> mDeveloperList;

    public DeveloperContactRecyclerViewAdapter(ArrayList<DeveloperContactInfo> developers) {
        mDeveloperList = developers; // Just a pointer, not a deep copy.
    }

    @Override
    public int getItemCount() {
        return mDeveloperList.size();
    }

    /**
     * Inflate a view to be used to display an item in the adapter.
     * @param parent parent view.
     * @param viewType type of view.
     * @return DeveloperContactViewHolder, the view to display.
     */
    @Override
    public DeveloperContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developer_contact_list_card, parent, false);
        DeveloperContactViewHolder developerCard = new DeveloperContactViewHolder(itemView);
        return developerCard;
    }

    @Override
    public void onBindViewHolder(DeveloperContactViewHolder developerCard, int position) {

        final DeveloperContactInfo developerInfo = mDeveloperList.get(position);

        // Set UI elements.
        developerCard.setDevIconImage(developerInfo.getAvatarUrl());
        developerCard.setDevLoginText(developerInfo.getLogin());
        developerCard.setDevNameText(developerInfo.getName());
        developerCard.setDevMessage(developerInfo.getMsg());

        developerCard.setEmailIconOnClickListener(developerInfo.getEmail(), "Hi from ForkMe",
                "Hi " + developerInfo.getName() + ",\nI saw you on the App ForkMe!\n" +
                "Regarding " + developerInfo.getMsg() + "\n");

        developerCard.itemView.setOnClickListener(new View.OnClickListener() {
            // TODO: Intent to user view??
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UserViewActivity.class);
                i.putExtra("user", developerInfo.getLogin());
                v.getContext().startActivity(i);
            }
        });

    }
}
