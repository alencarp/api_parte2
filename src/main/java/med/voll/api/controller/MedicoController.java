package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.MedicoRequestDTO;
import med.voll.api.medico.MedicoResponseDTO;
import med.voll.api.medico.DadosAtualizaMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping
    public Page<MedicoResponseDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(MedicoResponseDTO::new);
    }

    //1 - Carregar esse médico do BD
    //2 - Sobrescrever os campos de acordo com as novas informações do dto, fazendo um update na sequência
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaMedico dadosAtualizaMedico){
        Medico medico = medicoRepository.getReferenceById(dadosAtualizaMedico.id()); //este medico está vindo do BD, portanto, está com as infs desatualizadas
        medico.atualizarInformacoes(dadosAtualizaMedico);  //Ex.: pego o nome do médico atual e substituo pelo que está chegando por parâmetro, no dto
    }
}
