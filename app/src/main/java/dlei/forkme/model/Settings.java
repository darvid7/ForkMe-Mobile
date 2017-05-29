package dlei.forkme.model;


// Can either use this or shared preferences
public class Settings {
    private static int set;

    public static int getSetting() {
        return set;
    }

    public static void setSetting(int n) {
        set = n;
    }

}
