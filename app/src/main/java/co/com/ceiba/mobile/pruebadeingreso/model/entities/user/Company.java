package co.com.ceiba.mobile.pruebadeingreso.model.entities.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Company implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int idCompany;

    @ColumnInfo(name = "nameCompany")
    @SerializedName("name")
    @Expose
    public String nameCompany;

    @ColumnInfo(name = "catchPhrase")
    @SerializedName("catchPhrase")
    @Expose
    public String catchPhrase;

    @ColumnInfo(name = "bs")
    @SerializedName("bs")
    @Expose
    public String bs;

}
