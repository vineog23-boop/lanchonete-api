package br.com.centrallanches.lanchonete_api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    @Column(name = "id_cliente", length = 36)
    private UUID idCliente;

    /*
     * cascade = CascadeType.ALL: operações no Cliente (salvar, deletar) se propagam para os Enderecos
     * orphanRemoval = true: Endereco removido da lista é deletado do banco
     * fetch = FetchType.LAZY: endereços carregados só quando acessados (performance)
     */

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();


    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "celular")
    private String celular;

    @Column(name = "email", unique = true)
    private String email;


    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;


}
