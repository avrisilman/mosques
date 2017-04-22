package cloud.mosque.com.mosques.testmodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Example {

    @SerializedName("success")
    public String success;

    @SerializedName("detail")
    public List<Detail> detail = new ArrayList<>();


}
