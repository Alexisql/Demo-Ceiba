package co.com.ceiba.mobile.pruebadeingreso.model.entities.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Address implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int idAddress;

    @ColumnInfo(name = "street")
    @SerializedName("street")
    @Expose
    public String street;

    @ColumnInfo(name = "suite")
    @SerializedName("suite")
    @Expose
    public String suite;

    @ColumnInfo(name = "city")
    @SerializedName("city")
    @Expose
    public String city;

    @Embedded
    @SerializedName("geo")
    @Expose
    public Geolocation geo;

    public Geolocation getGeo() {
        return geo;
    }
}
