package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.users;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    // add buscas
}