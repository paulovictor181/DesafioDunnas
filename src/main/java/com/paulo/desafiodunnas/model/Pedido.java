package com.paulo.desafiodunnas.model;

import com.paulo.desafiodunnas.enums.StatusPedido;
import com.paulo.desafiodunnas.enums.TipoCupom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor_total = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pedido_fornecedor"))
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "cupom_id",
            foreignKey = @ForeignKey(name = "fk_pedido_cupom"))
    private Cupom cupom;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPedido status;

    @Column(nullable = false)
    private LocalDateTime dataPedido;
}
