package med.voll.api.controller.endereco;

public record EnderecoRequestDTO(
        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String uf,
        String numero,
        String complemento
) {
}
