package com.ludogoriesoft.bagsshop.service;

import com.ludogoriesoft.bagsshop.entity.Bag;
import com.ludogoriesoft.bagsshop.repository.BagRepo;
import org.springframework.stereotype.Service;
;import java.util.List;
import java.util.Optional;

@Service
public class BagService {

    private final BagRepo bagRepo;

    public BagService(BagRepo bagRepo) {
        this.bagRepo = bagRepo;
    }

    public List<Bag> getAllBags() {
        return bagRepo.findAll();
    }

    public Bag findBagById(Long id) {
        Optional<Bag> bag = bagRepo.findById(id);

        if (bag.isPresent()) {
            return bag.get();
        } else {
            throw new RuntimeException("Bag with id " + id + "was not found");

        }
    }

    public void deleteBagById(Long id) {
        Optional<Bag> bag = bagRepo.findById(id);

        if (bag.isPresent()) {
            bagRepo.deleteById(id);
        } else {
            throw new RuntimeException("Bag with id " + id + "was not found");
        }
    }

    public Bag saveBag(Bag bag) {
        return bagRepo.save(bag);
    }


    public Bag updateBagById(Long id, Bag bag) {
        Optional<Bag> existingBag = bagRepo.findById(id);

        if (existingBag.isPresent()) {
            existingBag.get().setBrand(bag.getBrand());
            existingBag.get().setModel(bag.getModel());
            existingBag.get().setPrice(bag.getPrice());

            bagRepo.save(existingBag.get());
            return existingBag.get();
        } else {
            return null;
        }
    }

}
