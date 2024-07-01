package med.voll.api.controller.medico;

public record MedicoResponseDTO( String nome, String email, String crm, Especialidade especialidade) {

    public MedicoResponseDTO(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }


}
