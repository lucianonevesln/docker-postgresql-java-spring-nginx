package br.com.lucianoneves.agendadecontatos.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "agenda_de_contatos")
public class AgendaDeContatosEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDateTime dataDoCadastro;
    private LocalDateTime dataDaAlteracao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getDataDoCadastro() {
        return dataDoCadastro;
    }

    public void setDataDoCadastro(LocalDateTime dataDoCadastro) {
        this.dataDoCadastro = dataDoCadastro;
    }

    public LocalDateTime getDataDaAlteracao() {
        return dataDaAlteracao;
    }

    public void setDataDaAlteracao(LocalDateTime dataDaAlteracao) {
        this.dataDaAlteracao = dataDaAlteracao;
    }
}
