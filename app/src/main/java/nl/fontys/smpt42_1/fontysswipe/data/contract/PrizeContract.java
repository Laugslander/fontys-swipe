package nl.fontys.smpt42_1.fontysswipe.data.contract;

import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnPrizeImageLinkReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnPrizeReceivedCallback;

/**
 * @author SMPT42-1
 */
public interface PrizeContract {

    void getPrize(OnPrizeReceivedCallback callback);

    void getPrizeImageUri(String image, OnPrizeImageLinkReceivedCallback callback);

}
