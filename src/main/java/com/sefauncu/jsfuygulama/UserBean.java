package com.sefauncu.jsfuygulama;

import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "giris")
@SessionScoped

public class UserBean {

    private String username;
    private String password;
    private boolean oturum = false;
    private String mesaj;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOturum() {
        return oturum;
    }

    public void setOturum(boolean oturum) {
        this.oturum = oturum;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String oturumac() throws SQLException {
        Database db = new Database();
        boolean res = db.checkUser(username, password);
        if (res) {
            oturum = true;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("swal('Giriş Başarılı!','Tebrikler! Giriş Yaptın','success')");
            return "index.xhtml?faces-redirect=true";
        } else {
            oturum = false;
            mesaj = "kullanıcı adı veya şifre yanlış";
            return "login.xhtml";

        }

    }

}
