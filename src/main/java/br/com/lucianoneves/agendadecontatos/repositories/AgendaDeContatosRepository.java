package br.com.lucianoneves.agendadecontatos.repositories;

import br.com.lucianoneves.agendadecontatos.entities.AgendaDeContatosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaDeContatosRepository extends JpaRepository<AgendaDeContatosEntity, Integer> {
    public boolean existsByEmail(String email);
    public boolean existsByTelefone(String telefone);
}
