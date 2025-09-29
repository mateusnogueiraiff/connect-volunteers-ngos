package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Representante;

import java.util.List;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {
    Representante findByNome(String nome);

    Representante findByEmail(String email);

    List<Representante> findAll();

    // busca nomes que possuam a "parte" passada por parâmetro
    @Query("SELECT v FROM Representante v WHERE LOWER(v.nome) LIKE LOWER(CONCAT('%', :nomePart, '%'))")
    List<Representante> searchByNomeLike(@Param("nomePart") String nomePart);

    // busca exatamente o email passado por parâmetro
    @Query("SELECT v FROM Representante v WHERE v.email = :email")
    Representante findByEmailExact(@Param("email") String email);
}