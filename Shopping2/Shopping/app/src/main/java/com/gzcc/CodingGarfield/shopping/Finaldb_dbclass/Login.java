package com.gzcc.CodingGarfield.shopping.Finaldb_dbclass;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

import java.util.Date;

/**
 * Created by wicker on 2016/6/16.
 */
@Table(name="LoginStatusUser")
public class Login {
    @Id(column = "status")

    private String status;
    private Date LoginDate;

    public String getAuto() {
        return Auto;
    }

    public void setAuto(String auto) {
        Auto = auto;
    }

    private String Auto;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLoginDate() {
        return LoginDate;
    }

    public void setLoginDate(Date LoginDate) {
        this.LoginDate = LoginDate;
    }
}
