package edu.arf.liceo.model;

public class Usuario {
    private int idUsuario;
    private String nickname;
    private String password;
    private String email;
    private double saldo;
    private boolean esAdmin;

    public Usuario(int idUsuario, String nickname, String password, String email, double saldo, boolean esAdmin) {
        this.idUsuario = idUsuario;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.saldo = saldo;
        this.esAdmin = esAdmin;
    }

    public Usuario() {}

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public boolean isEsAdmin() { return esAdmin; }
    public void setEsAdmin(boolean esAdmin) { this.esAdmin = esAdmin; }
}