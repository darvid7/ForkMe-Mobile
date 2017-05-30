package dlei.forkme.model;

import android.util.Base64;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;

public class Readme {
    private String name;
    private String path;
    private String url;
    @SerializedName("download_url")
    private String downloadUrl;
    private String content;
    private String encoding;

    // Default constructor.
    public Readme() {}

    public String getEncoding() {
        return this.encoding;
    }


    public String getCodedContent() {
        return this.content;
    }

    public String getDecodedContent()  {
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


//
//    {
//        "name": "README.md",
//            "path": "README.md",
//            "sha": "ca39f87df1f53fb0e51360a69f7c2bad03f1aa2b",
//            "size": 10723,
//            "url": "https://api.github.com/repos/atlassian/commonmark-java/contents/README.md?ref=master",
//            "html_url": "https://github.com/atlassian/commonmark-java/blob/master/README.md",
//            "git_url": "https://api.github.com/repos/atlassian/commonmark-java/git/blobs/ca39f87df1f53fb0e51360a69f7c2bad03f1aa2b",
//            "download_url": "https://raw.githubusercontent.com/atlassian/commonmark-java/master/README.md",
//            "type": "file",
//            "content": "Y29tbW9ubWFyay1qYXZhCj09PT09PT09PT09PT09PQoKSmF2YSBsaWJyYXJ5\nIGZvciBwYXJzaW5nIGFuZCByZW5kZXJpbmcgW01hcmtkb3duXSB0ZXh0IGFj\nY29yZGluZyB0byB0aGUKW0NvbW1vbk1hcmtdIHNwZWNpZmljYXRpb24gKGFu\nZCBzb21lIGV4dGVuc2lvbnMpLgoKUHJvdmlkZXMgY2xhc3NlcyBmb3IgcGFy\nc2luZyBpbnB1dCB0byBhbiBhYnN0cmFjdCBzeW50YXggdHJlZSBvZiBub2Rl\ncwooQVNUKSwgdmlzaXRpbmcgYW5kIG1hbmlwdWxhdGluZyBub2RlcywgYW5k\nIHJlbmRlcmluZyB0byBIVE1MLiBJdApzdGFydGVkIG91dCBhcyBhIHBvcnQg\nb2YgW2NvbW1vbm1hcmsuanNdLCBidXQgaGFzIHNpbmNlIGV2b2x2ZWQgaW50\nbyBhCmZ1bGwgbGlicmFyeSB3aXRoIGEgbmljZSBBUEkgYW5kIHRoZSBmb2xs\nb3dpbmcgZmVhdHVyZXM6CgoqIFNtYWxsIChtaW5pbWFsIGRlcGVuZGVuY2ll\ncykKKiBGYXN0ICgxMC0yMCB0aW1lcyBmYXN0ZXIgdGhhbiBwZWdkb3duLCBz\nZWUgYmVuY2htYXJrcyBpbiByZXBvKQoqIEZsZXhpYmxlIChtYW5pcHVsYXRl\nIHRoZSBBU1QgYWZ0ZXIgcGFyc2luZywgY3VzdG9taXplIEhUTUwgcmVuZGVy\naW5nKQoqIEV4dGVuc2libGUgKHRhYmxlcywgc3RyaWtldGhyb3VnaCwgYXV0\nb2xpbmtpbmcgYW5kIG1vcmUsIHNlZSBiZWxvdykKClJlcXVpcmVtZW50czoK\nCiogSmF2YSA3IG9yIGFib3ZlCiogV29ya3Mgb24gQW5kcm9pZCwgbWluaW11\nbSBBUEkgbGV2ZWwgMTUgKHNlZSBbY29tbW9ubWFyay1hbmRyb2lkLXRlc3Rd\nKGNvbW1vbm1hcmstYW5kcm9pZC10ZXN0KSBkaXJlY3RvcnkpCiogVGhlIGNv\ncmUgaGFzIG5vIGRlcGVuZGVuY2llczsgZm9yIGV4dGVuc2lvbnMsIHNlZSBi\nZWxvdwoKQ29vcmRpbmF0ZXMgZm9yIGNvcmUgbGlicmFyeSAoc2VlIGFsbCBv\nbiBbTWF2ZW4gQ2VudHJhbF0pOgoKYGBgeG1sCjxkZXBlbmRlbmN5PgogICAg\nPGdyb3VwSWQ+Y29tLmF0bGFzc2lhbi5jb21tb25tYXJrPC9ncm91cElkPgog\nICAgPGFydGlmYWN0SWQ+Y29tbW9ubWFyazwvYXJ0aWZhY3RJZD4KICAgIDx2\nZXJzaW9uPjAuOS4wPC92ZXJzaW9uPgo8L2RlcGVuZGVuY3k+CmBgYAoKTm90\nZSB0aGF0IGZvciAwLnggcmVsZWFzZXMgb2YgdGhpcyBsaWJyYXJ5LCB0aGUg\nQVBJIGlzIG5vdCBjb25zaWRlcmVkIHN0YWJsZQp5ZXQgYW5kIG1heSBicmVh\nayBiZXR3ZWVuIG1pbm9yIHJlbGVhc2VzLiBBZnRlciAxLjAsIFtTZW1hbnRp\nYyBWZXJzaW9uaW5nXSB3aWxsCmJlIGZvbGxvd2VkLgoKU2VlIHRoZSBbc3Bl\nYy50eHRdKGNvbW1vbm1hcmstdGVzdC11dGlsL3NyYy9tYWluL3Jlc291cmNl\ncy9zcGVjLnR4dCkKZmlsZSBpZiB5b3UncmUgd29uZGVyaW5nIHdoaWNoIHZl\ncnNpb24gb2YgdGhlIHNwZWMgaXMgY3VycmVudGx5CmltcGxlbWVudGVkLiBB\nbHNvIGNoZWNrIG91dCB0aGUgW0NvbW1vbk1hcmsgZGluZ3VzXSBmb3IgZ2V0\ndGluZyBmYW1pbGlhcgp3aXRoIHRoZSBzeW50YXggb3IgdHJ5aW5nIG91dCBl\nZGdlIGNhc2VzLgoKWyFbQnVpbGQgc3RhdHVzXShodHRwczovL3RyYXZpcy1j\naS5vcmcvYXRsYXNzaWFuL2NvbW1vbm1hcmstamF2YS5zdmc/YnJhbmNoPW1h\nc3RlcildKGh0dHBzOi8vdHJhdmlzLWNpLm9yZy9hdGxhc3NpYW4vY29tbW9u\nbWFyay1qYXZhKQpbIVtDb3ZlcmFnZSBzdGF0dXNdKGh0dHBzOi8vY292ZXJh\nbGxzLmlvL3JlcG9zL2dpdGh1Yi9hdGxhc3NpYW4vY29tbW9ubWFyay1qYXZh\nL2JhZGdlLnN2Zz9icmFuY2g9bWFzdGVyKV0oaHR0cHM6Ly9jb3ZlcmFsbHMu\naW8vZ2l0aHViL2F0bGFzc2lhbi9jb21tb25tYXJrLWphdmE/YnJhbmNoPW1h\nc3RlcikKWyFbTWF2ZW4gQ2VudHJhbCBzdGF0dXNdKGh0dHBzOi8vaW1nLnNo\naWVsZHMuaW8vbWF2ZW4tY2VudHJhbC92L2NvbS5hdGxhc3NpYW4uY29tbW9u\nbWFyay9jb21tb25tYXJrLnN2ZyldKGh0dHBzOi8vc2VhcmNoLm1hdmVuLm9y\nZy8jc2VhcmNoJTdDZ2ElN0MxJTdDZyUzQSUyMmNvbS5hdGxhc3NpYW4uY29t\nbW9ubWFyayUyMikKCgpVc2FnZQotLS0tLQoKIyMjIyBQYXJzZSBhbmQgcmVu\nZGVyIHRvIEhUTUwKCmBgYGphdmEKaW1wb3J0IG9yZy5jb21tb25tYXJrLm5v\nZGUuKjsKaW1wb3J0IG9yZy5jb21tb25tYXJrLnBhcnNlci5QYXJzZXI7Cmlt\ncG9ydCBvcmcuY29tbW9ubWFyay5yZW5kZXJlci5odG1sLkh0bWxSZW5kZXJl\ncjsKClBhcnNlciBwYXJzZXIgPSBQYXJzZXIuYnVpbGRlcigpLmJ1aWxkKCk7\nCk5vZGUgZG9jdW1lbnQgPSBwYXJzZXIucGFyc2UoIlRoaXMgaXMgKlNwYXJ0\nYSoiKTsKSHRtbFJlbmRlcmVyIHJlbmRlcmVyID0gSHRtbFJlbmRlcmVyLmJ1\naWxkZXIoKS5idWlsZCgpOwpyZW5kZXJlci5yZW5kZXIoZG9jdW1lbnQpOyAg\nLy8gIjxwPlRoaXMgaXMgPGVtPlNwYXJ0YTwvZW0+PC9wPlxuIgpgYGAKClRo\naXMgdXNlcyB0aGUgcGFyc2VyIGFuZCByZW5kZXJlciB3aXRoIGRlZmF1bHQg\nb3B0aW9ucy4gQm90aCBidWlsZGVycyBoYXZlCm1ldGhvZHMgZm9yIGNvbmZp\nZ3VyaW5nIHRoZWlyIGJlaGF2aW9yLCBlLmcuIGNhbGxpbmcgYGVzY2FwZUh0\nbWwodHJ1ZSlgIG9uCmBIdG1sUmVuZGVyZXJgIHdpbGwgZXNjYXBlIHJhdyBI\nVE1MIHRhZ3MgYW5kIGJsb2Nrcy4gRm9yIGFsbCBhdmFpbGFibGUKb3B0aW9u\ncywgc2VlIG1ldGhvZHMgb24gdGhlIGJ1aWxkZXJzLgoKTm90ZSB0aGF0IHRo\naXMgbGlicmFyeSBkb2Vzbid0IHRyeSB0byBzYW5pdGl6ZSB0aGUgcmVzdWx0\naW5nIEhUTUw7IHRoYXQgaXMKdGhlIHJlc3BvbnNpYmlsaXR5IG9mIHRoZSBj\nYWxsZXIuCgpGb3IgcmVuZGVyaW5nIHRvIHBsYWluIHRleHQsIHRoZXJlJ3Mg\nYWxzbyBhIGBUZXh0Q29udGVudFJlbmRlcmVyYCB3aXRoCmEgdmVyeSBzaW1p\nbGFyIEFQSS4KCiMjIyMgVXNlIGEgdmlzaXRvciB0byBwcm9jZXNzIHBhcnNl\nZCBub2RlcwoKQWZ0ZXIgdGhlIHNvdXJjZSB0ZXh0IGhhcyBiZWVuIHBhcnNl\nZCwgdGhlIHJlc3VsdCBpcyBhIHRyZWUgb2Ygbm9kZXMuClRoYXQgdHJlZSBj\nYW4gYmUgbW9kaWZpZWQgYmVmb3JlIHJlbmRlcmluZywgb3IganVzdCBpbnNw\nZWN0ZWQgd2l0aG91dApyZW5kZXJpbmc6CgpgYGBqYXZhCk5vZGUgbm9kZSA9\nIHBhcnNlci5wYXJzZSgiRXhhbXBsZVxuPT09PT09PVxuXG5Tb21lIG1vcmUg\ndGV4dCIpOwpXb3JkQ291bnRWaXNpdG9yIHZpc2l0b3IgPSBuZXcgV29yZENv\ndW50VmlzaXRvcigpOwpub2RlLmFjY2VwdCh2aXNpdG9yKTsKdmlzaXRvci53\nb3JkQ291bnQ7ICAvLyA0CgpjbGFzcyBXb3JkQ291bnRWaXNpdG9yIGV4dGVu\nZHMgQWJzdHJhY3RWaXNpdG9yIHsKICAgIGludCB3b3JkQ291bnQgPSAwOwoK\nICAgIEBPdmVycmlkZQogICAgcHVibGljIHZvaWQgdmlzaXQoVGV4dCB0ZXh0\nKSB7CiAgICAgICAgLy8gVGhpcyBpcyBjYWxsZWQgZm9yIGFsbCBUZXh0IG5v\nZGVzLiBPdmVycmlkZSBvdGhlciB2aXNpdCBtZXRob2RzIGZvciBvdGhlciBu\nb2RlIHR5cGVzLgoKICAgICAgICAvLyBDb3VudCB3b3JkcyAodGhpcyBpcyBq\ndXN0IGFuIGV4YW1wbGUsIGRvbid0IGFjdHVhbGx5IGRvIGl0IHRoaXMgd2F5\nIGZvciB2YXJpb3VzIHJlYXNvbnMpLgogICAgICAgIHdvcmRDb3VudCArPSB0\nZXh0LmdldExpdGVyYWwoKS5zcGxpdCgiXFxXKyIpLmxlbmd0aDsKCiAgICAg\nICAgLy8gRGVzY2VuZCBpbnRvIGNoaWxkcmVuIChjb3VsZCBiZSBvbWl0dGVk\nIGluIHRoaXMgY2FzZSBiZWNhdXNlIFRleHQgbm9kZXMgZG9uJ3QgaGF2ZSBj\naGlsZHJlbikuCiAgICAgICAgdmlzaXRDaGlsZHJlbih0ZXh0KTsKICAgIH0K\nfQpgYGAKCiMjIyMgQWRkIG9yIGNoYW5nZSBhdHRyaWJ1dGVzIG9mIEhUTUwg\nZWxlbWVudHMKClNvbWV0aW1lcyB5b3UgbWlnaHQgd2FudCB0byBjdXN0b21p\nemUgaG93IEhUTUwgaXMgcmVuZGVyZWQuIElmIGFsbCB5b3UKd2FudCB0byBk\nbyBpcyBhZGQgb3IgY2hhbmdlIGF0dHJpYnV0ZXMgb24gc29tZSBlbGVtZW50\ncywgdGhlcmUncyBhCnNpbXBsZSB3YXkgdG8gZG8gdGhhdC4KCkluIHRoaXMg\nZXhhbXBsZSwgd2UgcmVnaXN0ZXIgYSBmYWN0b3J5IGZvciBhbiBgQXR0cmli\ndXRlUHJvdmlkZXJgIG9uIHRoZQpyZW5kZXJlciB0byBzZXQgYSBgY2xhc3M9\nImJvcmRlciJgIGF0dHJpYnV0ZSBvbiBgaW1nYCBlbGVtZW50cy4KCmBgYGph\ndmEKUGFyc2VyIHBhcnNlciA9IFBhcnNlci5idWlsZGVyKCkuYnVpbGQoKTsK\nSHRtbFJlbmRlcmVyIHJlbmRlcmVyID0gSHRtbFJlbmRlcmVyLmJ1aWxkZXIo\nKQogICAgICAgIC5hdHRyaWJ1dGVQcm92aWRlckZhY3RvcnkobmV3IEF0dHJp\nYnV0ZVByb3ZpZGVyRmFjdG9yeSgpIHsKICAgICAgICAgICAgcHVibGljIEF0\ndHJpYnV0ZVByb3ZpZGVyIGNyZWF0ZShBdHRyaWJ1dGVQcm92aWRlckNvbnRl\neHQgY29udGV4dCkgewogICAgICAgICAgICAgICAgcmV0dXJuIG5ldyBJbWFn\nZUF0dHJpYnV0ZVByb3ZpZGVyKCk7CiAgICAgICAgICAgIH0KICAgICAgICB9\nKQogICAgICAgIC5idWlsZCgpOwoKTm9kZSBkb2N1bWVudCA9IHBhcnNlci5w\nYXJzZSgiIVt0ZXh0XSgvdXJsLnBuZykiKTsKcmVuZGVyZXIucmVuZGVyKGRv\nY3VtZW50KTsKLy8gIjxwPjxpbWcgc3JjPVwiL3VybC5wbmdcIiBhbHQ9XCJ0\nZXh0XCIgY2xhc3M9XCJib3JkZXJcIiAvPjwvcD5cbiIKCmNsYXNzIEltYWdl\nQXR0cmlidXRlUHJvdmlkZXIgaW1wbGVtZW50cyBBdHRyaWJ1dGVQcm92aWRl\nciB7CiAgICBAT3ZlcnJpZGUKICAgIHB1YmxpYyB2b2lkIHNldEF0dHJpYnV0\nZXMoTm9kZSBub2RlLCBTdHJpbmcgdGFnTmFtZSwgTWFwPFN0cmluZywgU3Ry\naW5nPiBhdHRyaWJ1dGVzKSB7CiAgICAgICAgaWYgKG5vZGUgaW5zdGFuY2Vv\nZiBJbWFnZSkgewogICAgICAgICAgICBhdHRyaWJ1dGVzLnB1dCgiY2xhc3Mi\nLCAiYm9yZGVyIik7CiAgICAgICAgfQogICAgfQp9CmBgYAoKIyMjIyBDdXN0\nb21pemUgSFRNTCByZW5kZXJpbmcKCklmIHlvdSB3YW50IHRvIGRvIG1vcmUg\ndGhhbiBqdXN0IGNoYW5nZSBhdHRyaWJ1dGVzLCB0aGVyZSdzIGFsc28gYSB3\nYXkKdG8gdGFrZSBjb21wbGV0ZSBjb250cm9sIG92ZXIgaG93IEhUTUwgaXMg\ncmVuZGVyZWQuCgpJbiB0aGlzIGV4YW1wbGUsIHdlJ3JlIGNoYW5naW5nIHRo\nZSByZW5kZXJpbmcgb2YgaW5kZW50ZWQgY29kZSBibG9ja3MgdG8Kb25seSB3\ncmFwIHRoZW0gaW4gYHByZWAgaW5zdGVhZCBvZiBgcHJlYCBhbmQgYGNvZGVg\nOgoKYGBgamF2YQpQYXJzZXIgcGFyc2VyID0gUGFyc2VyLmJ1aWxkZXIoKS5i\ndWlsZCgpOwpIdG1sUmVuZGVyZXIgcmVuZGVyZXIgPSBIdG1sUmVuZGVyZXIu\nYnVpbGRlcigpCiAgICAgICAgLm5vZGVSZW5kZXJlckZhY3RvcnkobmV3IEh0\nbWxOb2RlUmVuZGVyZXJGYWN0b3J5KCkgewogICAgICAgICAgICBwdWJsaWMg\nTm9kZVJlbmRlcmVyIGNyZWF0ZShIdG1sTm9kZVJlbmRlcmVyQ29udGV4dCBj\nb250ZXh0KSB7CiAgICAgICAgICAgICAgICByZXR1cm4gbmV3IEluZGVudGVk\nQ29kZUJsb2NrTm9kZVJlbmRlcmVyKGNvbnRleHQpOwogICAgICAgICAgICB9\nCiAgICAgICAgfSkKICAgICAgICAuYnVpbGQoKTsKCk5vZGUgZG9jdW1lbnQg\nPSBwYXJzZXIucGFyc2UoIkV4YW1wbGU6XG5cbiAgICBjb2RlIik7CnJlbmRl\ncmVyLnJlbmRlcihkb2N1bWVudCk7Ci8vICI8cD5FeGFtcGxlOjwvcD5cbjxw\ncmU+Y29kZVxuPC9wcmU+XG4iCgpjbGFzcyBJbmRlbnRlZENvZGVCbG9ja05v\nZGVSZW5kZXJlciBpbXBsZW1lbnRzIE5vZGVSZW5kZXJlciB7CgogICAgcHJp\ndmF0ZSBmaW5hbCBIdG1sV3JpdGVyIGh0bWw7CgogICAgSW5kZW50ZWRDb2Rl\nQmxvY2tOb2RlUmVuZGVyZXIoSHRtbE5vZGVSZW5kZXJlckNvbnRleHQgY29u\ndGV4dCkgewogICAgICAgIHRoaXMuaHRtbCA9IGNvbnRleHQuZ2V0V3JpdGVy\nKCk7CiAgICB9CgogICAgQE92ZXJyaWRlCiAgICBwdWJsaWMgU2V0PENsYXNz\nPD8gZXh0ZW5kcyBOb2RlPj4gZ2V0Tm9kZVR5cGVzKCkgewogICAgICAgIC8v\nIFJldHVybiB0aGUgbm9kZSB0eXBlcyB3ZSB3YW50IHRvIHVzZSB0aGlzIHJl\nbmRlcmVyIGZvci4KICAgICAgICByZXR1cm4gQ29sbGVjdGlvbnMuPENsYXNz\nPD8gZXh0ZW5kcyBOb2RlPj5zaW5nbGV0b24oSW5kZW50ZWRDb2RlQmxvY2su\nY2xhc3MpOwogICAgfQoKICAgIEBPdmVycmlkZQogICAgcHVibGljIHZvaWQg\ncmVuZGVyKE5vZGUgbm9kZSkgewogICAgICAgIC8vIFdlIG9ubHkgaGFuZGxl\nIG9uZSB0eXBlIGFzIHBlciBnZXROb2RlVHlwZXMsIHNvIHdlIGNhbiBqdXN0\nIGNhc3QgaXQgaGVyZS4KICAgICAgICBJbmRlbnRlZENvZGVCbG9jayBjb2Rl\nQmxvY2sgPSAoSW5kZW50ZWRDb2RlQmxvY2spIG5vZGU7CiAgICAgICAgaHRt\nbC5saW5lKCk7CiAgICAgICAgaHRtbC50YWcoInByZSIpOwogICAgICAgIGh0\nbWwudGV4dChjb2RlQmxvY2suZ2V0TGl0ZXJhbCgpKTsKICAgICAgICBodG1s\nLnRhZygiL3ByZSIpOwogICAgICAgIGh0bWwubGluZSgpOwogICAgfQp9CmBg\nYAoKIyMjIyBBZGQgeW91ciBvd24gbm9kZSB0eXBlcwoKSW4gY2FzZSB5b3Ug\nd2FudCB0byBzdG9yZSBhZGRpdGlvbmFsIGRhdGEgaW4gdGhlIGRvY3VtZW50\nIG9yIGhhdmUgY3VzdG9tCmVsZW1lbnRzIGluIHRoZSByZXN1bHRpbmcgSFRN\nTCwgeW91IGNhbiBjcmVhdGUgeW91ciBvd24gc3ViY2xhc3Mgb2YKYEN1c3Rv\nbU5vZGVgIGFuZCBhZGQgaW5zdGFuY2VzIGFzIGNoaWxkIG5vZGVzIHRvIGV4\naXN0aW5nIG5vZGVzLgoKVG8gZGVmaW5lIHRoZSBIVE1MIHJlbmRlcmluZyBm\nb3IgdGhlbSwgeW91IGNhbiB1c2UgYSBgTm9kZVJlbmRlcmVyYCBhcwpleHBs\nYWluZWQgYWJvdmUuCgojIyMgQVBJIGRvY3VtZW50YXRpb24KCkphdmFkb2Nz\nIGFyZSBhdmFpbGFibGUgb25saW5lIG9uCltqYXZhZG9jLmlvXShodHRwOi8v\nd3d3LmphdmFkb2MuaW8vZG9jL2NvbS5hdGxhc3NpYW4uY29tbW9ubWFyay9j\nb21tb25tYXJrKS4KCgpFeHRlbnNpb25zCi0tLS0tLS0tLS0KCkV4dGVuc2lv\nbnMgbmVlZCB0byBleHRlbmQgdGhlIHBhcnNlciwgb3IgdGhlIEhUTUwgcmVu\nZGVyZXIsIG9yIGJvdGguIFRvCnVzZSBhbiBleHRlbnNpb24sIHRoZSBidWls\nZGVyIG9iamVjdHMgY2FuIGJlIGNvbmZpZ3VyZWQgd2l0aCBhIGxpc3Qgb2YK\nZXh0ZW5zaW9ucy4gQmVjYXVzZSBleHRlbnNpb25zIGFyZSBvcHRpb25hbCwg\ndGhleSBsaXZlIGluIHNlcGFyYXRlCmFydGlmYWN0cywgc28gYWRkaXRpb25h\nbCBkZXBlbmRlbmNpZXMgbmVlZCB0byBiZSBhZGRlZCBhcyB3ZWxsLgoKTGV0\nJ3MgbG9vayBhdCBob3cgdG8gZW5hYmxlIHRhYmxlcyBmcm9tIEdpdEh1YiBG\nbGF2b3JlZCBNYXJrZG93bi4KRmlyc3QsIGFkZCBhbiBhZGRpdGlvbmFsIGRl\ncGVuZGVuY3kgKHNlZSBbTWF2ZW4gQ2VudHJhbF0gZm9yIG90aGVycyk6Cgpg\nYGB4bWwKPGRlcGVuZGVuY3k+CiAgICA8Z3JvdXBJZD5jb20uYXRsYXNzaWFu\nLmNvbW1vbm1hcms8L2dyb3VwSWQ+CiAgICA8YXJ0aWZhY3RJZD5jb21tb25t\nYXJrLWV4dC1nZm0tdGFibGVzPC9hcnRpZmFjdElkPgogICAgPHZlcnNpb24+\nMC45LjA8L3ZlcnNpb24+CjwvZGVwZW5kZW5jeT4KYGBgCgpUaGVuLCBjb25m\naWd1cmUgdGhlIGV4dGVuc2lvbiBvbiB0aGUgYnVpbGRlcnM6CgpgYGBqYXZh\nCmltcG9ydCBvcmcuY29tbW9ubWFyay5leHQuZ2ZtLnRhYmxlcy5UYWJsZXNF\neHRlbnNpb247CgpMaXN0PEV4dGVuc2lvbj4gZXh0ZW5zaW9ucyA9IEFycmF5\ncy5hc0xpc3QoVGFibGVzRXh0ZW5zaW9uLmNyZWF0ZSgpKTsKUGFyc2VyIHBh\ncnNlciA9IFBhcnNlci5idWlsZGVyKCkKICAgICAgICAuZXh0ZW5zaW9ucyhl\neHRlbnNpb25zKQogICAgICAgIC5idWlsZCgpOwpIdG1sUmVuZGVyZXIgcmVu\nZGVyZXIgPSBIdG1sUmVuZGVyZXIuYnVpbGRlcigpCiAgICAgICAgLmV4dGVu\nc2lvbnMoZXh0ZW5zaW9ucykKICAgICAgICAuYnVpbGQoKTsKYGBgCgpUbyBj\nb25maWd1cmUgYW5vdGhlciBleHRlbnNpb24gaW4gdGhlIGFib3ZlIGV4YW1w\nbGUsIGp1c3QgYWRkIGl0IHRvIHRoZSBsaXN0LgoKVGhlIGZvbGxvd2luZyBl\neHRlbnNpb25zIGFyZSBkZXZlbG9wZWQgd2l0aCB0aGlzIGxpYnJhcnksIGVh\nY2ggaW4gdGhlaXIKb3duIGFydGlmYWN0LgoKIyMjIEF1dG9saW5rCgpUdXJu\ncyBwbGFpbiBsaW5rcyBzdWNoIGFzIFVSTHMgYW5kIGVtYWlsIGFkZHJlc3Nl\ncyBpbnRvIGxpbmtzIChiYXNlZCBvbiBbYXV0b2xpbmstamF2YV0pLgoKVXNl\nIGNsYXNzIGBBdXRvbGlua0V4dGVuc2lvbmAgZnJvbSBhcnRpZmFjdCBgY29t\nbW9ubWFyay1leHQtYXV0b2xpbmtgLgoKIyMjIFN0cmlrZXRocm91Z2gKCkVu\nYWJsZXMgc3RyaWtldGhyb3VnaCBvZiB0ZXh0IGJ5IGVuY2xvc2luZyBpdCBp\nbiBgfn5gLiBGb3IgZXhhbXBsZSwgaW4KYGhleSB+fnlvdX5+YCwgYHlvdWAg\nd2lsbCBiZSByZW5kZXJlZCBhcyBzdHJpa2V0aHJvdWdoIHRleHQuCgpVc2Ug\nY2xhc3MgYFN0cmlrZXRocm91Z2hFeHRlbnNpb25gIGluIGFydGlmYWN0IGBj\nb21tb25tYXJrLWV4dC1nZm0tc3RyaWtldGhyb3VnaGAuCgojIyMgVGFibGVz\nCgpFbmFibGVzIHRhYmxlcyB1c2luZyBwaXBlcyBhcyBpbiBbR2l0SHViIEZs\nYXZvcmVkIE1hcmtkb3duXVtnZm0tdGFibGVzXS4KClVzZSBjbGFzcyBgVGFi\nbGVzRXh0ZW5zaW9uYCBpbiBhcnRpZmFjdCBgY29tbW9ubWFyay1leHQtZ2Zt\nLXRhYmxlc2AuCgojIyMgSGVhZGluZyBhbmNob3IKCkVuYWJsZXMgYWRkaW5n\nIGF1dG8gZ2VuZXJhdGVkICJpZCIgYXR0cmlidXRlcyB0byBoZWFkaW5nIHRh\nZ3MuIFRoZSAiaWQiCmlzIGJhc2VkIG9uIHRoZSB0ZXh0IG9mIHRoZSBoZWFk\naW5nLgoKYCMgSGVhZGluZ2Agd2lsbCBiZSByZW5kZXJlZCBhczoKCmBgYAo8\naDEgaWQ9ImhlYWRpbmciPkhlYWRpbmc8L2gxPgpgYGAKClVzZSBjbGFzcyBg\nSGVhZGluZ0FuY2hvckV4dGVuc2lvbmAgaW4gYXJ0aWZhY3QgYGNvbW1vbm1h\ncmstZXh0LWhlYWRpbmctYW5jaG9yYC4KCkluIGNhc2UgeW91IHdhbnQgY3Vz\ndG9tIHJlbmRlcmluZyBvZiB0aGUgaGVhZGluZyBpbnN0ZWFkLCB5b3UgY2Fu\nIHVzZQp0aGUgYElkR2VuZXJhdG9yYCBjbGFzcyBkaXJlY3RseSB0b2dldGhl\nciB3aXRoIGEKYEh0bWxOb2RlUmVuZGVyZXJGYWN0b3J5YCAoc2VlIGV4YW1w\nbGUgYWJvdmUpLgoKIyMjIElucwoKRW5hYmxlcyB1bmRlcmxpbmluZyBvZiB0\nZXh0IGJ5IGVuY2xvc2luZyBpdCBpbiBgKytgLiBGb3IgZXhhbXBsZSwgaW4K\nYGhleSArK3lvdSsrYCwgYHlvdWAgd2lsbCBiZSByZW5kZXJlZCBhcyB1bmRl\ncmxpbmUgdGV4dC4gVXNlcyB0aGUgJmx0O2lucyZndDsgdGFnLgoKVXNlIGNs\nYXNzIGBJbnNFeHRlbnNpb25gIGluIGFydGlmYWN0IGBjb21tb25tYXJrLWV4\ndC1pbnNgLgoKIyMjIFlBTUwgZnJvbnQgbWF0dGVyCgpBZGRzIHN1cHBvcnQg\nZm9yIG1ldGFkYXRhIHRocm91Z2ggYSBZQU1MIGZyb250IG1hdHRlciBibG9j\nay4gVGhpcyBleHRlbnNpb24gb25seSBzdXBwb3J0cyBhIHN1YnNldCBvZiBZ\nQU1MIHN5bnRheC4gSGVyZSdzIGFuIGV4YW1wbGUgb2Ygd2hhdCdzIHN1cHBv\ncnRlZDoKCmBgYAotLS0Ka2V5OiB2YWx1ZQpsaXN0OgogIC0gdmFsdWUgMQog\nIC0gdmFsdWUgMgpsaXRlcmFsOiB8CiAgdGhpcyBpcyBsaXRlcmFsIHZhbHVl\nLgoKICBsaXRlcmFsIHZhbHVlcyAyCi0tLQoKZG9jdW1lbnQgc3RhcnQgaGVy\nZQpgYGAKClVzZSBjbGFzcyBgWWFtbEZyb250TWF0dGVyRXh0ZW5zaW9uYCBp\nbiBhcnRpZmFjdCBgY29tbW9ubWFyay1leHQteWFtbC1mcm9udC1tYXR0ZXJg\nLiBUbyBmZXRjaCBtZXRhZGF0YSwgdXNlIGBZYW1sRnJvbnRNYXR0ZXJWaXNp\ndG9yYC4KCkNvbnRyaWJ1dGluZwotLS0tLS0tLS0tLS0KClB1bGwgcmVxdWVz\ndHMsIGlzc3VlcyBhbmQgY29tbWVudHMgd2VsY29tZSDimLouIEZvciBwdWxs\nIHJlcXVlc3RzOgoKKiBBZGQgdGVzdHMgZm9yIG5ldyBmZWF0dXJlcyBhbmQg\nYnVnIGZpeGVzCiogRm9sbG93IHRoZSBleGlzdGluZyBzdHlsZSAoYWx3YXlz\nIHVzZSBicmFjZXMsIDQgc3BhY2UgaW5kZW50KQoqIFNlcGFyYXRlIHVucmVs\nYXRlZCBjaGFuZ2VzIGludG8gbXVsdGlwbGUgcHVsbCByZXF1ZXN0cwoKU2Vl\nIHRoZSBleGlzdGluZyAiaGVscCB3YW50ZWQiIGlzc3VlcyBmb3IgdGhpbmdz\nIHRvIHN0YXJ0IGNvbnRyaWJ1dGluZy4KCkZvciBiaWdnZXIgY2hhbmdlcywg\nbWFrZSBzdXJlIHlvdSBzdGFydCBhIGRpc2N1c3Npb24gZmlyc3QgYnkgY3Jl\nYXRpbmcKYW4gaXNzdWUgYW5kIGV4cGxhaW5pbmcgdGhlIGludGVuZGVkIGNo\nYW5nZS4KCgpMaWNlbnNlCi0tLS0tLS0KCkNvcHlyaWdodCAoYykgMjAxNS0y\nMDE2IEF0bGFzc2lhbiBhbmQgb3RoZXJzLgoKQlNEICgyLWNsYXVzZSkgbGlj\nZW5zZWQsIHNlZSBMSUNFTlNFLnR4dCBmaWxlLgoKW0NvbW1vbk1hcmtdOiBo\ndHRwOi8vY29tbW9ubWFyay5vcmcvCltNYXJrZG93bl06IGh0dHBzOi8vZGFy\naW5nZmlyZWJhbGwubmV0L3Byb2plY3RzL21hcmtkb3duLwpbY29tbW9ubWFy\nay5qc106IGh0dHBzOi8vZ2l0aHViLmNvbS9qZ20vY29tbW9ubWFyay5qcwpb\nQ29tbW9uTWFyayBEaW5ndXNdOiBodHRwOi8vc3BlYy5jb21tb25tYXJrLm9y\nZy9kaW5ndXMvCltNYXZlbiBDZW50cmFsXTogaHR0cHM6Ly9zZWFyY2gubWF2\nZW4ub3JnLyNzZWFyY2h8Z2F8MXxnJTNBJTIyY29tLmF0bGFzc2lhbi5jb21t\nb25tYXJrJTIyCltTZW1hbnRpYyBWZXJzaW9uaW5nXTogaHR0cDovL3NlbXZl\nci5vcmcvClthdXRvbGluay1qYXZhXTogaHR0cHM6Ly9naXRodWIuY29tL3Jv\nYmluc3QvYXV0b2xpbmstamF2YQpbZ2ZtLXRhYmxlc106IGh0dHBzOi8vaGVs\ncC5naXRodWIuY29tL2FydGljbGVzL29yZ2FuaXppbmctaW5mb3JtYXRpb24t\nd2l0aC10YWJsZXMvCg==\n",
//            "encoding": "base64",
//            "_links": {
//        "self": "https://api.github.com/repos/atlassian/commonmark-java/contents/README.md?ref=master",
//                "git": "https://api.github.com/repos/atlassian/commonmark-java/git/blobs/ca39f87df1f53fb0e51360a69f7c2bad03f1aa2b",
//                "html": "https://github.com/atlassian/commonmark-java/blob/master/README.md"
//    }
//    }

}