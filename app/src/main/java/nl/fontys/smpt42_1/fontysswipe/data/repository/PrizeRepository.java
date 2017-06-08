package nl.fontys.smpt42_1.fontysswipe.data.repository;

import nl.fontys.smpt42_1.fontysswipe.data.contract.PrizeContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnPrizeImageLinkReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnPrizeReceivedCallback;

/**
 * @author SMPT42-1
 */
public class PrizeRepository {

    private final PrizeContract context;

    public PrizeRepository() {
        this.context = RepositoryConstants.PRIZE_CONTEXT;
    }

    public void getPrize(OnPrizeReceivedCallback callback) {
        context.getPrize(callback);
    }

    public void getPrizeImageUri(String image, OnPrizeImageLinkReceivedCallback callback) {
        context.getPrizeImageUri(image, callback);
    }

}
