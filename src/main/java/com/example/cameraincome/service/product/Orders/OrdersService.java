package com.example.cameraincome.service.product.Orders;

import com.example.cameraincome.model.product.Orders;
import com.example.cameraincome.repo.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersService implements IOrdersService{

    @Autowired
    IOrdersRepository ordersRepository;


    @Override
    public Iterable<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public void remove(Long id) {
        ordersRepository.deleteById(id);
    }
}
