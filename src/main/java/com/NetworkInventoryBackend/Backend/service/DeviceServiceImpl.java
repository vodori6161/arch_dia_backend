package com.NetworkInventoryBackend.Backend.service;

import com.NetworkInventoryBackend.Backend.dto.DeviceDto;
import com.NetworkInventoryBackend.Backend.model.DeletedDevice;
import com.NetworkInventoryBackend.Backend.model.Device;
import com.NetworkInventoryBackend.Backend.model.RecycledDevice;
import com.NetworkInventoryBackend.Backend.repository.DeletedDeviceRepository;
import com.NetworkInventoryBackend.Backend.repository.DeviceRepository;
import com.NetworkInventoryBackend.Backend.repository.RecycledDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    DeletedDeviceRepository deletedDeviceRepository;
    @Autowired
    RecycledDeviceRepository recycledDeviceRepository;
    @Override
    public DeviceDto createDevice(DeviceDto deviceDto) {
        Device device = new Device();
        device.setDeviceId(UUID.randomUUID().toString());
        device.setDeviceName(deviceDto.getDeviceName());
        device.setDescription(deviceDto.getDescription());
        device.setTimeUp(deviceDto.getTimeUp());
        device.setIpAddress(deviceDto.getIpAddress());
        device.setStatus(deviceDto.getStatus());
        device.setLocation(deviceDto.getLocation()); // added

        deviceRepository.save(device);
        return new DeviceDto(
                device.getDeviceId(),
                device.getDeviceName(),
                device.getDescription(),
                device.getTimeUp(),
                device.getIpAddress(),
                device.getStatus(),
                device.getLocation() //added
        );
    }

    @Override
    public DeviceDto deleteDeviceByIp(String ip_address) {
        Optional<Device> optionalDevice = deviceRepository.findByIpAddress(ip_address);
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            deviceRepository.findByIpAddress(ip_address);
            DeletedDevice deletedDevice = new DeletedDevice(
                    device.getDeviceId(),
                    device.getDeviceName(),
                    device.getDescription(),
                    device.getTimeUp(),
                    device.getIpAddress(),
                    device.getStatus(),
                    device.getLocation(), //added

                    LocalDateTime.now().toString()

            );
            deletedDeviceRepository.save(deletedDevice);
            deviceRepository.delete(device);
            return new DeviceDto(
                    device.getDeviceId(),
                    device.getDeviceName(),
                    device.getDescription(),
                    device.getTimeUp(),
                    device.getIpAddress(),
                    device.getStatus(),
                    device.getLocation() //added

            );
        } else {
            throw new RuntimeException("Device with IP address " + ip_address + " not found.");
        }
    }

    @Override
    public DeviceDto restoreDeviceByIp(String ip_address) {
        DeletedDevice deletedDevice = deletedDeviceRepository.findByIpAddress(ip_address)
                .orElseThrow(() -> new RuntimeException("No deleted device found with IP address " + ip_address));

        RecycledDevice restoredDevice = new RecycledDevice();
        restoredDevice.setDeviceId(deletedDevice.getDeviceId());
        restoredDevice.setDeviceName(deletedDevice.getDeviceName());
        restoredDevice.setDescription(deletedDevice.getDescription());
        restoredDevice.setTimeUp(deletedDevice.getTimeUp());
        restoredDevice.setIpAddress(deletedDevice.getIpAddress());
        restoredDevice.setStatus(deletedDevice.getStatus());
        restoredDevice.setLocation(deletedDevice.getLocation()); //added

        recycledDeviceRepository.save(restoredDevice);

        // Delete from recycle bin by ip (reliable for Mongo)
        deletedDeviceRepository.deleteByIpAddress(ip_address);

        return new DeviceDto(
                restoredDevice.getDeviceId(),
                restoredDevice.getDeviceName(),
                restoredDevice.getDescription(),
                restoredDevice.getTimeUp(),
                restoredDevice.getIpAddress(),
                restoredDevice.getStatus(),
                restoredDevice.getLocation() //added
        );
    }
    @Override
    public DeviceDto updateDevice(String ip_address, DeviceDto updatedDevice) {
        Optional<Device> optionalDevice = deviceRepository.findByIpAddress(ip_address);

        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            device.setDeviceName(updatedDevice.getDeviceName());
            device.setDescription(updatedDevice.getDescription());
            device.setIpAddress((updatedDevice.getIpAddress()));
            device.setStatus(updatedDevice.getStatus());
            device.setLocation(updatedDevice.getLocation()); //added

            deviceRepository.save(device);

            return new DeviceDto(
                    device.getDeviceId(),
                    device.getDeviceName(),
                    device.getDescription(),
                    device.getTimeUp(),
                    device.getIpAddress(),
                    device.getStatus(),
                    device.getLocation() //added

            );
        } else {
            throw new RuntimeException("Device not found with IP: " + ip_address);
        }
    }

    @Override
    public DeviceDto searchDevice(String ipAddress) {
        Optional<Device> deviceOptional = deviceRepository.findByIpAddress(ipAddress);
            Device device = deviceOptional.get();
            DeviceDto dto = new DeviceDto();
            dto.setDeviceId(device.getDeviceId());
            dto.setDeviceName(device.getDeviceName());
            dto.setDescription(device.getDescription());
            dto.setTimeUp(device.getTimeUp());
            dto.setIpAddress(device.getIpAddress());
            dto.setStatus(device.getStatus());
            dto.setLocation(device.getLocation()); //added
            return dto;

    }

    @Override
    public List<DeviceDto> listOfDevices() {
        List<Device> devices = deviceRepository.findAll();

        return devices.stream().map(device -> {
            DeviceDto dto = new DeviceDto();
            dto.setDeviceId(device.getDeviceId());
            dto.setDeviceName(device.getDeviceName());
            dto.setDescription(device.getDescription());
            dto.setTimeUp(device.getTimeUp());
            dto.setIpAddress(device.getIpAddress());
            dto.setStatus(device.getStatus());
            dto.setLocation(device.getLocation()); //added

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<DeviceDto> listOfRecycledDevices(){
        List<DeletedDevice> deletedDevices = deletedDeviceRepository.findAll();
        System.out.print(deletedDevices);
        return deletedDevices.stream().map(device -> {
            DeviceDto dto = new DeviceDto();
            dto.setDeviceId(device.getDeviceId());
            dto.setDeviceName(device.getDeviceName());
            dto.setDescription(device.getDescription());
            dto.setTimeUp(device.getTimeUp());
            dto.setIpAddress(device.getIpAddress());
            dto.setStatus(device.getStatus());
            dto.setLocation(device.getLocation()); //added

            return dto;
        }).collect(Collectors.toList());
    }



}
