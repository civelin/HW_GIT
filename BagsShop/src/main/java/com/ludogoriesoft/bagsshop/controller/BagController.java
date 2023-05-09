package com.ludogoriesoft.bagsshop.controller;

import com.ludogoriesoft.bagsshop.entity.Bag;
import com.ludogoriesoft.bagsshop.service.BagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/bags")
public class BagController {

    private final BagService bagService;

    public BagController(BagService bagService) {
        this.bagService = bagService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Bag>> getAllBags() {
        return ResponseEntity.ok(bagService.getAllBags());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Bag> findBagById(@PathVariable Long id) {
        try {
            Bag bag = bagService.findBagById(id);
            return ResponseEntity.ok(bag);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/")
    public ResponseEntity<Bag> createBag(@RequestBody Bag bag, UriComponentsBuilder uriComponentsBuilder) {
        Bag savedBag = bagService.saveBag(bag);
        URI location = uriComponentsBuilder.path("/api/v1/bags").buildAndExpand(savedBag.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bag> deleteBagById(@PathVariable Long id) {
        try {
            bagService.deleteBagById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bag> updateBagById(@PathVariable Long id, @RequestBody Bag bag) {
        Bag bag1 = bagService.updateBagById(id, bag);
        if (bag1 == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();


    }

}
