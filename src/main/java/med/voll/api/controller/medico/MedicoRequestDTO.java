package med.voll.api.controller.medico;

import med.voll.api.controller.endereco.EnderecoRequestDTO;

public record MedicoRequestDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        EnderecoRequestDTO endereco
) {
}
