package cloud.mosque.com.mosques.home;

import cloud.mosque.com.mosques.home.model.DataResponse;

public interface MenuView {
    void onError(String message);

    void onSuccess(DataResponse response);
}
