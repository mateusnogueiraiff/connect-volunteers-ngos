package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.oportunidade;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.user.Voluntario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Inscricao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Voluntario voluntario;

    @ManyToOne
    private Vaga vaga;

    private String status;
    private LocalDateTime dataInscricao;

    public Inscricao() {}

    public Inscricao(Long id, Voluntario voluntario, Vaga vaga, String status, LocalDateTime dataInscricao) {
        this.id = id;
        this.voluntario = voluntario;
        this.vaga = vaga;
        this.status = status;
        this.dataInscricao = dataInscricao;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }
    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Vaga getVaga() {
        return vaga;
    }
    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }
    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
}
