package br.com.centrallanches.lanchonete_api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_endereco")

public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_endereco", length = 36) // Nome e tipo da coluna
    private UUID idEndereco;

    @ManyToOne // Indica que muitos Enderecos se relacionam com um unico Cliente
   @JoinColumn(name = "id_cliente", nullable = false) // Define a coluna de chave estrangeira na tabela tb_endereco
   private Cliente cliente; // Atributo que referencia o Cliente ao qual este Endereco pertence

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "estado", length = 2, nullable = false)
    private String estado;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "complemento")
    private String complemento;




}
