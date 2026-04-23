package br.com.centrallanches.lanchonete_api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_endereco") // Nome da tabela conforme especificado
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_endereco", columnDefinition = "VARCHAR(36)") // Nome e tipo da coluna
    private UUID idEndereco;

    @Column(name = "logradouro", nullable = false) // NOT NULL
    private String logradouro;

    @Column(name = "numero", nullable = false) // NOT NULL
    private String numero;

    @Column(name = "bairro", nullable = false) // NOT NULL
    private String bairro;

    @Column(name = "cidade", nullable = false) // NOT NULL
    private String cidade;

    @Column(name = "estado", length = 2, nullable = false) // CHAR(2), NOT NULL
    private String estado;

    @Column(name = "cep", nullable = false) // NOT NULL
    private String cep;

    @Column(name = "complemento") // nullable
    private String complemento;


}
