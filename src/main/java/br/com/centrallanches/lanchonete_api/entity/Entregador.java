package br.com.centrallanches.lanchonete_api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_entregador")

public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entregador")
    private Integer id;


    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "celular")
    private String celular;


}
