package com.NetworkInventoryBackend.Backend.controller;

import com.NetworkInventoryBackend.Backend.dto.DeviceDto;
import com.NetworkInventoryBackend.Backend.service.DeviceService;
import com.NetworkInventoryBackend.Backend.service.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceServiceimpl;

    @GetMapping("/hello")
    public String Hello(){
        return "hello from device controller";
    }

    @GetMapping("/devices")
    public List<DeviceDto> ListofDevices(){
        return deviceServiceimpl.listOfDevices();
    }
    @PostMapping("/add-device")
    public DeviceDto AddDevice(@RequestBody DeviceDto deviceDto){
        return deviceServiceimpl.createDevice(deviceDto);
    }

    @DeleteMapping("/delete-device/{ipAddress}")
    public DeviceDto deleteDevice(@PathVariable String ipAddress) {
        return deviceServiceimpl.deleteDeviceByIp(ipAddress);
    }

    @PostMapping("/restore-device/{ip_address}")
    public DeviceDto restoreDevice(@PathVariable String ip_address) {
        return deviceServiceimpl.restoreDeviceByIp(ip_address);
    }

    @GetMapping("/recycled-device")
    public List<DeviceDto> recycledDevice()
    {
        return deviceServiceimpl.listOfRecycledDevices();
    }

    @PutMapping("/update-device/{ip_address}")
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable String ip_address, @RequestBody DeviceDto updatedDevice) {
        DeviceDto result = deviceServiceimpl.updateDevice(ip_address, updatedDevice);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search-device/{ip_address}")
    public DeviceDto searchDevice(@PathVariable String ip_address)
    {

          DeviceDto result = deviceServiceimpl.searchDevice(ip_address);
          return result;

    }


}
