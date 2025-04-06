package com.arpico.inventory.controller;

import com.arpico.inventory.model.Inventory;
import com.arpico.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    InventoryService service;

    @GetMapping("/")
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = service.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getASingleInventory(@PathVariable int id) {
        Optional<Inventory> inventory = service.getASingleInventory(id);
        if(inventory.isPresent()) {
            return new ResponseEntity<>(inventory.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Inventory> createANewInventory(@RequestBody Inventory inventory) {

        Inventory savedInventory = service.createANewInventory(inventory);
        return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventoryData(@PathVariable int id, @RequestBody Inventory inventory) {
        Inventory updatedInventory = service.updateInventoryDetails(id, inventory);
        if(updatedInventory != null) {
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable int id) {
        service.deleteAInventory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
