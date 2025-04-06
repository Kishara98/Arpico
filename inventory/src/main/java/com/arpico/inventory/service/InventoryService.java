package com.arpico.inventory.service;

import com.arpico.inventory.model.Inventory;
import com.arpico.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository repository;

    public List<Inventory> getAllInventories() {
        List<Inventory> inventories = repository.findAll();
        return inventories;
    }

    public Optional<Inventory> getASingleInventory(int id) {
        Optional<Inventory> inventory = repository.findById(id);
        return inventory;
    }

    public Inventory createANewInventory(Inventory inventory) {
        return repository.save(inventory);
    }

    public Inventory updateInventoryDetails(int id, Inventory inventory) {
       Optional<Inventory> existingInventory = repository.findById(id);
       if(existingInventory.isPresent()) {
           Inventory inventoryData = existingInventory.get();
           inventoryData.setProductId(inventory.getProductId());
           inventoryData.setItemId(inventory.getItemId());
           inventoryData.setQuantity(inventory.getQuantity());
           return repository.save(inventoryData);
       } else {
           throw new RuntimeException("Inventory not found with ID: " + id);
       }
    }

    public void deleteAInventory(int id) {
        Optional<Inventory> existingInventory = repository.findById(id);
        if(existingInventory.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Inventory not found with ID: " + id);
        }
    }
}
