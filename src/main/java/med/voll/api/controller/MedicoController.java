package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.medico.Medico;
import med.voll.api.controller.medico.MedicoRepository;
import med.voll.api.controller.medico.MedicoRequestDTO;
import med.voll.api.controller.medico.MedicoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoRequestDTO medicoRequestDTO) {
        medicoRepository.save(new Medico(medicoRequestDTO));
    }

    //size e sort setados na url sobrepõem os valores que estão aqui no método.
    @GetMapping
    public Page<MedicoResponseDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(MedicoResponseDTO::new);
    }
}
