package com.tecdes.music.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tecdes.music.model.Gravadora;

@Repository
public class GravadoraRepository {

    private List<Gravadora> banco = new ArrayList<>();
    private Long contadorId = 1L;

    // 🔹 salvar
    public Gravadora save(Gravadora gravadora) {

        // NOVO registro
        if (gravadora.id() == null) {

            Gravadora nova = new Gravadora(
                    contadorId++,
                    gravadora.nomeFantasia(),
                    gravadora.cnpj(),
                    gravadora.cidade(),
                    gravadora.faturamentoAnual()
            );

            banco.add(nova);
            return nova;
        }

        // ATUALIZAÇÃO
        deleteById(gravadora.id());
        banco.add(gravadora);

        return gravadora;
    }

    // 🔹 listar
    public List<Gravadora> findAll() {
        return banco;
    }

    // 🔹 buscar por id
    public Optional<Gravadora> findById(Long id) {
        return banco.stream()
                .filter(g -> g.id().equals(id))
                .findFirst();
    }

    // 🔹 deletar
    public void deleteById(Long id) {
        banco.removeIf(g -> g.id().equals(id));
    }
}