package br.com.lucianoneves.agendadecontatos.dtos;

public class AgendaDeContatosDto {
    private String nome;
    private String email;
    private String telefone;
    private String dataDoCadastro;
    private String dataDaAlteracao;

    public AgendaDeContatosDto() {
    }

    public AgendaDeContatosDto(
            String nome,
            String email,
            String telefone,
            String dataDoCadastro,
            String dataDaAlteracao
    ) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataDoCadastro = dataDoCadastro;
        this.dataDaAlteracao = dataDaAlteracao;
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

    public String getDataDoCadastro() {
        return dataDoCadastro;
    }

    public void setDataDoCadastro(String dataDoCadastro) {
        this.dataDoCadastro = dataDoCadastro;
    }

    public String getDataDaAlteracao() {
        return dataDaAlteracao;
    }

    public void setDataDaAlteracao(String dataDaAlteracao) {
        this.dataDaAlteracao = dataDaAlteracao;
    }
}
