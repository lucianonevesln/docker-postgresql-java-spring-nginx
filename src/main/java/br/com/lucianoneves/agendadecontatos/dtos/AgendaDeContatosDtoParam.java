package br.com.lucianoneves.agendadecontatos.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AgendaDeContatosDtoParam {
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @Email
    private String email;
    @NotBlank
    @Size(max = 11)
    @Column(nullable = false)
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
