package com.kma.warehouseManagement.service;

import com.kma.warehouseManagement.entity.Storage;
import com.kma.warehouseManagement.repository.StorageRepository;
import com.kma.warehouseManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storeRepository;
    @Autowired
    private UserRepository userRepository;

    public void addStorage(Storage storage) {

        storeRepository.save(storage);
    }

    public void updateStorage(Integer id, Storage storage) {
        storeRepository.updateStorageById(id, storage.getName(),storage.getMarketId(), storage.getAddress(),
                storage.getLatitude(), storage.getLongtitude());
    }

    public void deleteStorage(Integer id) {
        storeRepository.deleteStorageById(id);
    }


}
