package dlei.forkme.gui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.ArrayList;

import dlei.forkme.R;
import dlei.forkme.state.AppSettings;

/** Just need this to show
 * StarNotificationDialog s = new StarNotificationDialog();
 s.show(getFragmentManager(), "test");
 */

/**
 * Fragment to set a notification.
 */
public class StarNotificationDialog extends DialogFragment {
    private int times[] = {1, 2, 3};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogSpinnerView = inflater.inflate(R.layout.diaglog_spinner, null);
        final Spinner s = (Spinner) dialogSpinnerView.findViewById(R.id.dialogTimeSpinner);

        builder
                .setTitle("Remind me to look at this again later in:")
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
