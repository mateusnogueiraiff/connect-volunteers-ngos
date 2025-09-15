package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;

import java.util.List;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    Voluntario findByNome(String nome);

    Voluntario findByEmail(String email);

    List<Voluntario> findAll();

    /* verificar como fazer o select de um tipo de usuario especifico, ex: voluntario

    @Query("SELECT u FROM Voluntario u WHERE u.funcao = "voluntario"")
    
    */
}

