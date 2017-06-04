package nl.fontys.smpt42_1.fontysswipe.domain.result;

/**
 * @author SMPT42-1
 */
public abstract class Result {

    private String title;

    Result(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
