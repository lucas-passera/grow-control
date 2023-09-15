package com.fg.growcontrol.service;

import com.fg.growcontrol.entity.OptimalGrowingParameter;
import com.fg.growcontrol.repository.OptimalGrowingParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class OptimalGrowingParameterService {
    @Autowired
    private OptimalGrowingParameterRepository optimalGrowingParameterRepository;
    @Transactional
    public OptimalGrowingParameter createOrUpdateGrowStageType(OptimalGrowingParameter optimalGrowingParameter) {

        return optimalGrowingParameterRepository.save(optimalGrowingParameter);
    }
    public OptimalGrowingParameter getOptimalGrowingParameterById(Long id) throws EntityNotFoundException {
        Optional<OptimalGrowingParameter> response = optimalGrowingParameterRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con el ID: " + id);
        }
    }
    @Transactional
    public void deleteOptimalGrowingParameter(Long id) throws EntityNotFoundException {
        Optional<OptimalGrowingParameter> response = optimalGrowingParameterRepository.findById(id);
        if (response.isPresent()) {
            optimalGrowingParameterRepository.delete(response.get());
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con el ID: " + id);
        }
    }
}
