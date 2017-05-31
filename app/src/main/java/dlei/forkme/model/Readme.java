package dlei.forkme.model;

import android.util.Base64;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;

public class Readme {
    private String name;
    private String path;
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("download_url")
    private String downloadUrl;
    private String content;
    private String encoding;

    private static final String renderFailImageUrl = "http://4.bp.blogspot.com/-80e_nFiG00s/T_phyO7ZJ8I/AAAAAAAAETA/p5wOuH3jaxM/w1200-h630-p-k-no-nu/Pug+Dog+Hd+Wallpapers_.jpg";
    private static final String readmeNotFoundImageUrl = "http://photo.elsoar.com/wp-content/images/Dog-looking-for-something.jpg";

    public String getReadmeFailedToRenderHtml() {
        return "<h3>Error Loading readme</h3><p>url: <a href="
                + this.getHtmlUrl() + ">" + this.getHtmlUrl() + "</a>" +
                " could not be rendered properly :(</p><br/>" +
                "<center><img src=" + renderFailImageUrl + " alt=:( ></center>";
    }

    public static String getReadmeNotFoundHtml() {
        return "<h3>Readme not found for this repository</h3>" +
                "<center><img src=" + readmeNotFoundImageUrl + " alt=:( ></center>" +
                "<p>Maybe prompt the owner to make a readme?</p>";
    }


    // Default constructor.
    public Readme() {
    }

    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public String getCodedContent() {
        return this.content;
    }

    public String getDecodedContent() {
        Log.d("Readme: ", "getDecodedContent: Before: " + this.content.substring(0, this.content.length() > 201 ? 200 : 0));
        String decodedMarkdown;

        try {
            decodedMarkdown = new String(Base64.decode(this.content.getBytes("UTF-8"), Base64.NO_WRAP));
        } catch (UnsupportedEncodingException e) {
            Log.w("Readme: ", "getDecodedContent: Decoding string: " + e.getMessage());
            decodedMarkdown = "";
        }

        Log.d("Readme: ", "getDecodedContent: After (should be markdown):\n" + decodedMarkdown);

        return decodedMarkdown;

    }
}