package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.medico.Medico;
import med.voll.api.controller.medico.MedicoRepository;
import med.voll.api.controller.medico.MedicoRequestDTO;
import med.voll.api.controller.medico.MedicoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<MedicoResponseDTO> listar() {
        //map() mapeia de MedicoResponseDTO para Medico
        return medicoRepository.findAll().stream().map(MedicoResponseDTO::new).toList();


        /**
         * Function<String, Integer> funcao = new Function<String, Integer>() {
         *                        @Override
         *            public Integer apply(String s) {
         * 				return s.length();
         *            }        * 		};
         * 		Comparator<String> compardorDeStrings = Comparator.comparing(funcao);
         * 		palavras.sort(compardorDeStrings);
         */
    }
}
