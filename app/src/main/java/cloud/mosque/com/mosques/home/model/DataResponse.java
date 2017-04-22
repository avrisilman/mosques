package cloud.mosque.com.mosques.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by domikado on 4/5/17.
 */

public class DataResponse {

    @SerializedName("data")
    public List<Data> data = new ArrayList<>();

}
