package com.fg.growcontrol.service;

import com.fg.growcontrol.entity.GrowingEventType;
import com.fg.growcontrol.repository.GrowingEventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GrowingEventTypeService {
    @Autowired
    private GrowingEventTypeRepository growingEventTypeRepository;
    @Transactional
    public GrowingEventType createOrUpdateGrowingEventType(GrowingEventType growingEventType) {

        return growingEventTypeRepository.save(growingEventType);
    }
    public GrowingEventType getGrowingEventTypeById(Long id) throws EntityNotFoundException {
        Optional<GrowingEventType> response = growingEventTypeRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con el ID: " + id);
        }
    }
    public List<GrowingEventType> getAllGrowingEventType() {
        return growingEventTypeRepository.findAll();
    }
    @Transactional
    public void deleteGrowingEventType(Long id) throws EntityNotFoundException {
        Optional<GrowingEventType> response = growingEventTypeRepository.findById(id);
        if (response.isPresent()) {
            growingEventTypeRepository.delete(response.get());
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con el ID: " + id);
        }
    }
}
