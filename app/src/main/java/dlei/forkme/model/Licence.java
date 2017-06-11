package dlei.forkme.model;

public class Licence {
    private String iconUrl;
    private String licenceUrl;
    private String libraryName;
    private String libraryUrl;
    private String associated[];
    private String licence;
    private String usedFor;

    public String getUsedFor() {
        return this.usedFor;
    }

    public void setUsedFor(String usedFor) {
        this.usedFor = usedFor;
    }

    public String getLicence() {
        return this.licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String url) {
        this.iconUrl = url;
    }

    public void setLicenceUrl(String licenceUrl) {
        this.licenceUrl = licenceUrl;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public void setLibraryUrl(String libraryUrl) {
        this.libraryUrl = libraryUrl;
    }

    public void setAssociated(String[] associated) {
        this.associated = associated;
    }

    public String getLicenceUrl() {
        return licenceUrl;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public String getLibraryUrl() {
        return libraryUrl;
    }

    public String[] getAssociated() {
        return associated;
    }
}
