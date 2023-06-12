package br.com.fiap.cidade.service;

import br.com.fiap.cidade.model.Order;
import br.com.fiap.cidade.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        // Lógica para criar uma nova ordem
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, Order order) {
        // Lógica para atualizar uma ordem existente
        Order existingOrder = getOrderById(orderId);
        existingOrder.setStatus(order.getStatus());
        existingOrder.setCustomer(order.getCustomer());
        // Atualize os outros atributos conforme necessário

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        // Lógica para excluir uma ordem existente
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        // Lógica para obter uma ordem pelo ID
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Ordem não encontrada"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}