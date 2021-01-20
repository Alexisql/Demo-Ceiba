package co.com.ceiba.mobile.pruebadeingreso.bd.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.Company;

@Dao
public interface CompanyDao {

    @Insert
    Long insert(Company company);

}
