package nl.fontys.smpt42_1.fontysswipe.domain.result;

import nl.fontys.smpt42_1.fontysswipe.domain.Prize;

/**
 * @author SMPT42-1
 */
public final class PrizeResult extends Result {

    private Prize prize;

    public PrizeResult(String title, Prize prize) {
        super(title);

        this.prize = prize;
    }

    public Prize getPrize() {
        return prize;
    }

}
