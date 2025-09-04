package com.NetworkInventoryBackend.Backend.service;

import com.NetworkInventoryBackend.Backend.dto.DeviceDto;
import com.NetworkInventoryBackend.Backend.model.DeletedDevice;
import com.NetworkInventoryBackend.Backend.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    public DeviceDto createDevice(DeviceDto deviceDto);
    DeviceDto deleteDeviceByIp(String ip_address);
    public DeviceDto restoreDeviceByIp(String ip_address);
    public  DeviceDto updateDevice(String ipAddress, DeviceDto updatedDevice);
    public DeviceDto searchDevice(String ipAddress);
    public List<DeviceDto> listOfDevices();
    public List<DeviceDto> listOfRecycledDevices();
    public List<DeletedDevice>  listOfDeletedDevices();


}
