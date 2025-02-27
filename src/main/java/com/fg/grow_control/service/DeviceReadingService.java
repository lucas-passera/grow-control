package com.fg.grow_control.service;

import io.github.feddericovonwernich.spring_ai.function_calling_service.annotations.AssistantToolProvider;
import io.github.feddericovonwernich.spring_ai.function_calling_service.annotations.FunctionDefinition;
import com.fg.grow_control.dto.DeviceReadingDTO;
import com.fg.grow_control.entity.DeviceReading;
import com.fg.grow_control.entity.MeasurementDevice;
import com.fg.grow_control.repository.DeviceReadingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@AssistantToolProvider
public class DeviceReadingService extends BasicService<DeviceReading, Long, DeviceReadingRepository> {

    @Autowired
    private MeasurementDeviceService measurementDeviceService;

    @Autowired
    public DeviceReadingService(DeviceReadingRepository repository) {
        super(repository);
    }

    public DeviceReading registerReading(DeviceReadingDTO deviceReadingDTO) {

        MeasurementDevice measurementDevice = measurementDeviceService.getById(deviceReadingDTO.getDeviceId());

        DeviceReading newReading = DeviceReading.builder()
                .measurementDevice(measurementDevice)
                .reading(deviceReadingDTO.getMeasurementValue())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();

        return this.createOrUpdate(newReading);
    }


    @FunctionDefinition(name = "DeviceReadingService_getLastReadingForDevice",
            description = "Retrieves the last reading for a specified MeasurementDevice.",
            parameterClass = MeasurementDevice.class)
    public DeviceReading getLastReadingForDevice(MeasurementDevice measurementDevice) {
        // Utilizing custom repository method to find the last DeviceReading for a given MeasurementDevice
        return repository.findTopByMeasurementDeviceOrderByIdDesc(measurementDevice)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No readings found for device with ID: " + measurementDevice.getId()));
    }

    @Override
    public DeviceReading createOrUpdate(DeviceReading deviceReading) {
        return super.createOrUpdate(deviceReading);
    }

    @Override
    @FunctionDefinition(name = "DeviceReadingService_getById", description = "Retrieves a DeviceReading object by its Id.", parameters = """
                    {
                      "type": "object",
                      "properties": {
                        "id": {
                          "type": "number",
                          "description": "The ID of the DeviceReading object to retrieve."
                        }
                      },
                      "required": ["id"]
                    }
            """)
    public DeviceReading getById(Long id) throws EntityNotFoundException {
        return super.getById(id);
    }

    @Override
    @FunctionDefinition(name = "DeviceReadingService_getAll", description = "Retrieves all DeviceReading objects.", parameters = "{}")
    public Page<DeviceReading> getAll(int pageNumber, int pageSize) {
        return super.getAll(pageNumber, pageSize);
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        super.deleteById(id);
    }

}
