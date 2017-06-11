package dlei.forkme.gui.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dlei.forkme.R;
import dlei.forkme.gui.activities.github.RepositoryViewActivity;
import dlei.forkme.model.Repository;

public class RepositoryRecyclerViewAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {
    private ArrayList<Repository> mRepositoryList;

    public RepositoryRecyclerViewAdapter(ArrayList<Repository> repositories) {
        mRepositoryList = repositories; // Just a pointer, not a deep copy.
    }

    /**
     * Get number of items in mRepositoryList.
     * @return size of mRepositoryList.
     */
    @Override
    public int getItemCount() {
        return mRepositoryList.size();
    }

    /**
     * Inflate a view to be used to display an item in the adapter.
     * @param parent parent view.
     * @param viewType type of view.
     * @return RepositoryViewHolder, the view to display.
     */
    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_list_card, parent, false);
        RepositoryViewHolder repositoryCard = new RepositoryViewHolder(itemView);
        return repositoryCard;
    }

    /**
     * For each view item displayed by the adapter, this is called to bind the repository data to the view
     * so the view item displays the right information.
     * Also sets listener for clicking on the repository.
     * @param repositoryCard, RepositoryViewHolder view to display for each Repository in mRepositoryList.
     * @param position, int position of Repository.
     */
    @Override
    public void onBindViewHolder(RepositoryViewHolder repositoryCard, int position) {

        final Repository repository = mRepositoryList.get(position);

        // Set UI elements.
        repositoryCard.setLanguage(repository.getLanguage());
        repositoryCard.setOwnerUserNameText(repository.getOwnerName());
        repositoryCard.setRepoDescriptionText(repository.getDescription());
        repositoryCard.setRepoNameText(repository.getRepoName());
        repositoryCard.setForkCountText(repository.getForkCount());
        repositoryCard.setStarCountText(repository.getStargazerCount());

        repositoryCard.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RepositoryViewActivity.class);
                i.putExtra("repository", repository);
                v.getContext().startActivity(i);
            }
        });
    }

}
