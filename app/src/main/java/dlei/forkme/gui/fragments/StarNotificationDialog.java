package dlei.forkme.gui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import dlei.forkme.R;

/** Just need this to show
 * StarNotificationDialog s = new StarNotificationDialog();
 s.show(getFragmentManager(), "test");
 */

/**
 * Fragment to set a notification, allows the user to set the notification for some time within 24 hours.
 */
public class StarNotificationDialog extends DialogFragment {

    /**
     * Create a new instance of StarNotificationDialog, providing "repositoryFullName" as an argument.
     * This is called before OnCreateDialog().
     */
    public static StarNotificationDialog newInstance(String repositoryFullName) {
        Log.d("StarNotifiDialog: ", "newInstance() called, param: " + repositoryFullName);

        StarNotificationDialog f = new StarNotificationDialog();
        // Supply repositoryFullName input as an argument.
        Bundle args = new Bundle();
        args.putString("repositoryFullName", repositoryFullName);
        f.setArguments(args);
        return f;
    }

    /**
     * Creates new StarNotificationDialog.
     * This is called after newInstance() so it has access to args.
     * @param savedInstanceState bundle passed in from newInstance containing args.
     * @return built Dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String repositoryFullName = getArguments().getString("repositoryFullName");
        Log.d("StarNotifiDialog: ", "onCreateDialog() Got Arg: " + repositoryFullName);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogSpinnerView = inflater.inflate(R.layout.diaglog_spinner, null);
        AppCompatTextView t = (AppCompatTextView) dialogSpinnerView.findViewById(R.id.messageText);
        // Concatenate new string with repo name.
        t.setText(getResources().getText(R.string.dialog_star_notification_message_first_half) +
                repositoryFullName +
                getResources().getText(R.string.dialog_star_notification_message_second_half));
        final Spinner s = (Spinner) dialogSpinnerView.findViewById(R.id.dialogTimeSpinner);

        builder
                .setView(dialogSpinnerView)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String selected = (String) s.getAdapter().getItem(s.getSelectedItemPosition());
                        Log.wtf("Selected: ", selected);
                        Log.wtf("index selected: ", "" +s.getSelectedItemPosition());
                        Log.wtf("Selected: ", (String) s.getSelectedItem());

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog.
                    }
                });
        // Create the AlertDialog object and return it.
        return builder.create();
    }
}
