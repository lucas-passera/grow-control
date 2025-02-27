package com.fg.grow_control.service;

import io.github.feddericovonwernich.spring_ai.function_calling_service.annotations.AssistantToolProvider;
import io.github.feddericovonwernich.spring_ai.function_calling_service.annotations.FunctionDefinition;
import com.fg.grow_control.entity.GrowStageType;
import com.fg.grow_control.repository.GrowStageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AssistantToolProvider
public class GrowStageTypeService extends BasicService<GrowStageType, Long, GrowStageTypeRepository> {

    @Autowired
    public GrowStageTypeService(GrowStageTypeRepository repository) {
        super(repository);
    }

    @Override
    @FunctionDefinition(name = "GrowStageTypeService_createOrUpdate",
            description = "Creates or updates a GrowStageType object.",
            parameterClass = GrowStageType.class)
    public GrowStageType createOrUpdate(GrowStageType growStageType) {
        return super.createOrUpdate(growStageType);
    }

    @Override
    @FunctionDefinition(name = "GrowStageTypeService_getById", description = "Retrieves a GrowStageType object by its ID.", parameters = """
            {
                "type": "object",
                "properties": {
                    "id": {
                        "type": "number",
                        "description": "The ID of the GrowStageType object to retrieve."
                    }
                },
                "required": ["id"]
            }
        """)
    public GrowStageType getById(Long id) {
        return super.getById(id);
    }

    @Override
    @FunctionDefinition(name = "GrowStageTypeService_getAll", description = "Retrieves all GrowStageType objects.", parameters = "{}")
    public Page<GrowStageType> getAll(int pageNumber, int pageSize) {
        return super.getAll(pageNumber, pageSize);  // Llama al método getAll del servicio base con los parámetros
    }

    @Override
    @FunctionDefinition(name = "GrowStageTypeService_deleteById", description = "Deletes a GrowStageType object by its ID.", parameters = """
            {
                "type": "object",
                "properties": {
                    "id": {
                      "type": "number",
                      "description": "The ID of the GrowStageType object to delete."
                    }
                },
                "required": ["id"]
            }
        """)
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    public Optional<GrowStageType> findByName(String stringForTestRegisterReading) {
        return repository.findByName(stringForTestRegisterReading);
    }
}
