package cloud.mosque.com.mosques.testmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {
    @SerializedName("id")
    public int id;
    @SerializedName("outlet_name")
    public String outletName;
    @SerializedName("outlet_pic")
    public String outletPic;
    @SerializedName("outlet_logo")
    public String outletLogo;
}
