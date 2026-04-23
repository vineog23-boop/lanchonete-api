package br.com.centrallanches.lanchonete_api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(name = "id_cliente", columnDefinition = "VARCHAR(36)") // Renomeado de 'id' para 'idCliente' e configurado como UUID
    private UUID idCliente;

    // Campos existentes que não foram mencionados para alteração ou remoção
    @Column(name = "nome", nullable = false) // Assumindo que nome, celular, email existiam e foram mantidos
    private String nome;

    @Column(name = "celular")
    private String celular;

    @Column(name = "email", unique = true) // Assumindo que email é único
    private String email;

    // Campo adicionado para substituir 'idade'
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;


}
