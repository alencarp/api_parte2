package med.voll.api.controller.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.endereco.Endereco;
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded //EMBEDDABLE ATTRIBUTE da JPA => fica em uma classe separada, mas no BD ele considera que os campos da classe Endereco fazem parte da mesma tabela de medicos
    private Endereco endereco;

    public Medico(MedicoRequestDTO medicoRequestDTO) {
        this.nome = medicoRequestDTO.nome();
        this.email = medicoRequestDTO.email();
        this.telefone = medicoRequestDTO.telefone();
        this.crm = medicoRequestDTO.crm();
        this.especialidade = medicoRequestDTO.especialidade();
        this.endereco = new Endereco(medicoRequestDTO.endereco());
    }
}
