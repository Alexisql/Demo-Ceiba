package co.com.ceiba.mobile.pruebadeingreso.bd.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.Geolocation;

@Dao
public interface GeolocationDao {

    @Insert
    Long insert(Geolocation geo);

}
