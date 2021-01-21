package co.com.ceiba.mobile.pruebadeingreso.utils;

import android.app.AlertDialog;
import android.content.Context;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.bd.AppDataBase;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.Address;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.Company;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.Geolocation;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.user.User;
import dmax.dialog.SpotsDialog;

public class Util {

    private Util() {
    }

    private static AlertDialog dialog;

    public static void showDialog(Context context) {
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(context).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
    }

    public static void dismissDailog() {
        dialog.dismiss();
    }

    public static AlertDialog getDialog() {
        return dialog;
    }

    public static void saveUserBD(Context context, List<User> userList) {
        AppDataBase appDataBase = AppDataBase.getInstance(context);
        for (User user : userList) {
            Address address = user.getAddress();
            saveAddressBD(appDataBase, user.getAddress());
            saveGeoBD(appDataBase, address.getGeo());
            saveCompanyBD(appDataBase, user.getCompany());
            appDataBase.userDao().insert(user);
        }
    }

    public static void saveAddressBD(AppDataBase appDataBase, Address address) {
        appDataBase.addressDao().insert(address);
    }

    public static void saveGeoBD(AppDataBase appDataBase, Geolocation geolocation) {
        appDataBase.geolocationDao().insert(geolocation);
    }

    public static void saveCompanyBD(AppDataBase appDataBase, Company company) {
        appDataBase.companyDao().insert(company);
    }

    public static List<User> getUsersBD(Context context) {
        return AppDataBase.getInstance(context).userDao().getAll();
    }

}
