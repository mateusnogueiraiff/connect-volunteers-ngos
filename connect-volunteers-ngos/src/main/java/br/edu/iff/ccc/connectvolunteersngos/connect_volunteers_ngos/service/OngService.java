package br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.connectvolunteersngos.connect_volunteers_ngos.entities.Ong;

@Service
public class OngService {
    private ArrayList<Ong> ongs = new ArrayList<>();
    private Long nextId = 4L; // já existem 3 ONGs criadas

    public OngService() {
        ongs.add(new Ong(1L, "Ong Teste", "12.345.678/9090-00", "Descrição da ONG", "(22) 97979-9797", "teste1@ong.com"));
        ongs.add(new Ong(2L, "Ong Animais", "98.765.432/0001-11", "Ajuda animais de rua", "(22) 98888-7777", "contato@animais.org"));
        ongs.add(new Ong(3L, "Ong Crianças", "98.765.432/0001-11", "Ajuda crianças orfãos", "(22) 98888-7777", "contato@criancas.org"));
    }

    public ArrayList<Ong> findAllOngs() {
        return ongs;
    }

    public Ong saveOng(Ong ong) {
        if (ong.getId() == null) {
            ong.setId(nextId++);
        }
        ongs.add(ong);
        return ong;
    }
}