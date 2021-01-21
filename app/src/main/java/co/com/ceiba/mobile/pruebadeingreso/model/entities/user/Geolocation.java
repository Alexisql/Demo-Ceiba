package co.com.ceiba.mobile.pruebadeingreso.model.entities.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Geolocation implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int idGeo;

    @ColumnInfo(name = "lat")
    @SerializedName("lat")
    @Expose
    public String lat;

    @ColumnInfo(name = "lng")
    @SerializedName("lng")
    @Expose
    public String lng;

}
