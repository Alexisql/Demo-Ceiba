package co.com.ceiba.mobile.pruebadeingreso.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.bd.dao.AddressDao;
import co.com.ceiba.mobile.pruebadeingreso.bd.dao.CompanyDao;
import co.com.ceiba.mobile.pruebadeingreso.bd.dao.GeolocationDao;
import co.com.ceiba.mobile.pruebadeingreso.bd.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.Address;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.Company;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.Geolocation;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.User;

@Database(entities = {User.class, Address.class, Company.class, Geolocation.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;

    public abstract UserDao userDao();

    public abstract AddressDao addressDao();

    public abstract CompanyDao companyDao();

    public abstract GeolocationDao geolocationDao();

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDataBase.class, "db-ceiba").build();
        }
        return instance;
    }
}
