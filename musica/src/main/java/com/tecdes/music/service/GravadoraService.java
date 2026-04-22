package com.tecdes.music.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tecdes.music.dto.GravadoraDTO;
import com.tecdes.music.model.Gravadora;
import com.tecdes.music.repository.GravadoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GravadoraService {

    private final GravadoraRepository gravadoraRepository;

    public Gravadora salvar(Gravadora gravadora) {

        if (gravadora.faturamentoAnual() < 0) {
            throw new RuntimeException("Faturamento não pode ser negativo!");
        }

        return gravadoraRepository.save(gravadora);
    }


    public List<Gravadora> listarTodas() {
        return gravadoraRepository.findAll();
    }

    public Gravadora buscarPorId(Long id) {
        return gravadoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gravadora não encontrada!"));
    }


    public void deletar(Long id) {
        gravadoraRepository.deleteById(id);
    }


    public Gravadora atualizar(Long id, GravadoraDTO dto) {

        buscarPorId(id);

        if (dto.faturamentoAnual() < 0) {
            throw new RuntimeException("Faturamento não pode ser negativo!");
        }

        Gravadora atualizada = new Gravadora(
                id,
                dto.nomeFantasia(),
                dto.cnpj(),
                dto.cidade(),
                dto.faturamentoAnual()
        );

        return gravadoraRepository.save(atualizada);
    }

    public Gravadora atualizarParcial(Long id, GravadoraDTO dto) {

        Gravadora existente = buscarPorId(id);

        Double faturamento = dto.faturamentoAnual() != null
                ? dto.faturamentoAnual()
                : existente.faturamentoAnual();

        if (faturamento < 0) {
            throw new RuntimeException("Faturamento não pode ser negativo!");
        }

        Gravadora atualizada = new Gravadora(
                id,
                dto.nomeFantasia() != null ? dto.nomeFantasia() : existente.nomeFantasia(),
                dto.cnpj() != null ? dto.cnpj() : existente.cnpj(),
                dto.cidade() != null ? dto.cidade() : existente.cidade(),
                faturamento
        );

        return gravadoraRepository.save(atualizada);
    }
}