package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;

import java.util.List;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    Voluntario findByNome(String nome);

    Voluntario findByEmail(String email);

    List<Voluntario> findAll();

    // busca nomes que possuam a "parte" passada por parâmetro
    @Query("SELECT v FROM Voluntario v WHERE LOWER(v.nome) LIKE LOWER(CONCAT('%', :nomePart, '%'))")
    List<Voluntario> searchByNomeLike(@Param("nomePart") String nomePart);

    // busca exatamente o email passado por parâmetro
    @Query("SELECT v FROM Voluntario v WHERE v.email = :email")
    Voluntario findByEmailExact(@Param("email") String email);
}