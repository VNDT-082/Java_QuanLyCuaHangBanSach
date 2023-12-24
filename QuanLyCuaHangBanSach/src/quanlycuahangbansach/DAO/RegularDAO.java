/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahangbansach.DAO;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author PC
 */
public class RegularDAO {

    public static boolean checkPhoneNumber(String phoneNumber) {
        String reg = "[0-9]{10}$";
        boolean kt = phoneNumber.matches(reg);
        return kt;
    }

    public static boolean checkPhone(String str) {
        // Bieu thuc chinh quy mo ta dinh dang so dien thoai
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        // Kiem tra dinh dang
        boolean kt = str.matches(reg);
        return kt;
    }

    public static boolean checkCCCD(String cccd) {
        String reg = " [0-9]^{12}$";
        boolean kt = cccd.matches(reg);
        return kt;
    }

    public static boolean checkUserName(String userName) {
        String reg = " [a-z0-9_-]$";
        boolean kt = userName.matches(reg);
        return kt;
    }

    public static boolean checkFullName(String fullName) {
        String reg = "[a-zA-Z]$";
        boolean kt = fullName.matches(reg);
        return kt;
    }

    public static void main(String[] args) {
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String text = date.format(formatters);
        System.out.println(text);
    }
}
