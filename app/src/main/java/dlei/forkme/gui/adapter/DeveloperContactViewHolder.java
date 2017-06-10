package dlei.forkme.gui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

import dlei.forkme.R;

public class DeveloperContactViewHolder extends RecyclerView.ViewHolder {
    private AppCompatImageView mDevIconImage;
    private AppCompatTextView mDevNameText;
    private AppCompatTextView mDevLogin;
    private AppCompatTextView mDevMessage;
    private AppCompatImageView mEmailIcon;

    public DeveloperContactViewHolder(View view) {
        super(view);
        mDevIconImage = (AppCompatImageView) view.findViewById(R.id.devIconImageView);
        mDevNameText = (AppCompatTextView) view.findViewById(R.id.devNameText);
        mDevLogin = (AppCompatTextView) view.findViewById(R.id.devLoginText);
        mDevMessage = (AppCompatTextView) view.findViewById(R.id.devMessageText);
        mEmailIcon = (AppCompatImageView) view.findViewById(R.id.emailIconImageView);

    }

    public void setDevIconImage(String avatarUrl) {
        if (avatarUrl != null) {

            Picasso.with(mDevIconImage.getContext()).load(avatarUrl).into(mDevIconImage);
        } else {
            Log.wtf("DevContactVH: ", "setDevIconImage: image url is null");
        }
    }

    public void setDevNameText(String name) {
        mDevNameText.setText(name);
    }

    public void setDevLoginText(String login) {
        mDevLogin.setText(login);
    }

    public void setDevMessage(String msg) {
        mDevMessage.setText(msg);
    }

    public void setEmailIconOnClickListener(final String toEmailAddress, final String subject, final String body) {
        mEmailIcon.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_VIEW);
                        emailIntent.setType("message/rfc822");
                        Uri data = Uri.parse("mailto:?subject=" + subject + "&body=" + body + "&to=" + toEmailAddress);
                        emailIntent.setData(data);
                        mEmailIcon.getContext().startActivity(emailIntent);
                    }
                }
        );
    }
}
