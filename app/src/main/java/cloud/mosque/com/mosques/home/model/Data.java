package cloud.mosque.com.mosques.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by domikado on 4/4/17.
 */

public class Data {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("calendar")
    @Expose
    public String calendar;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("admin")
    @Expose
    public Admin admin;
    @SerializedName("picture")
    @Expose
    public String picture;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("province")
    @Expose
    public Object province;
    @SerializedName("city")
    @Expose
    public Object city;
    @SerializedName("address")
    @Expose
    public String address;

}
