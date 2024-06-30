package med.voll.api.controller.endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable //tem que colocar esta anotação para o JPA deixar os atributos desta tabela como campos na tabela de medicos.
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(EnderecoRequestDTO enderecoRequestDTO) {
        this.logradouro = enderecoRequestDTO.logradouro();
        this.bairro = enderecoRequestDTO.bairro();
        this.cep = enderecoRequestDTO.cep();
        this.cidade = enderecoRequestDTO.cidade();
        this.uf = enderecoRequestDTO.uf();
        this.numero = enderecoRequestDTO.numero();
        this.complemento = enderecoRequestDTO.complemento();
    }
}
