package com.NetworkInventoryBackend.Backend.controller;

import com.NetworkInventoryBackend.Backend.dto.DeviceDto;
import com.NetworkInventoryBackend.Backend.model.DeletedDevice;
import com.NetworkInventoryBackend.Backend.service.DeviceActivityLogService;
import com.NetworkInventoryBackend.Backend.service.DeviceService;
import com.NetworkInventoryBackend.Backend.service.DeviceServiceImpl;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private DeviceActivityLogService deviceActivityLogService;

    @GetMapping("/hello")
    public String Hello(){
        return "hello from device controller";
    }

    @GetMapping("/devices")
    public List<DeviceDto> ListofDevices(){
        return deviceServiceimpl.listOfDevices();
    }

    @PostMapping("/add-device")
//    public DeviceDto AddDevice(@RequestBody DeviceDto deviceDto){
//        return deviceServiceimpl.createDevice(deviceDto);
//    }
    public DeviceDto addDevice(@RequestBody DeviceDto deviceDto, HttpSession session) {
        DeviceDto createdDevice = deviceServiceimpl.createDevice(deviceDto);

        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        String role = (String) session.getAttribute("role");

        //debug
//        System.out.println("UserId: " + session.getAttribute("userId"));
//        System.out.println("UserName: " + session.getAttribute("userName"));
//        System.out.println("Role: " + session.getAttribute("role"));

        deviceActivityLogService.logAction(
                userId,
                userName,
                role,
                "ADD_DEVICE",
                "Added device with IP: " + createdDevice.getIpAddress()
        );

        return createdDevice;
    }


    @DeleteMapping("/delete-device/{ipAddress}")
    public DeviceDto deleteDevice(@PathVariable String ipAddress, HttpSession session) {
        DeviceDto deletedDevice = deviceServiceimpl.deleteDeviceByIp(ipAddress);

        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        String role = (String) session.getAttribute("role");

        deviceActivityLogService.logAction(
                userId,
                userName,
                role,
                "DELETE_DEVICE",
                "Deleted device with IP: " + ipAddress
        );

        return deletedDevice;

    }

    @DeleteMapping("/restore-device/{ip_address}")
//    public DeviceDto restoreDevice(@PathVariable String ip_address) {
//        return deviceServiceimpl.restoreDeviceByIp(ip_address);
//    }
    public DeviceDto restoreDevice(
            @PathVariable String ip_address,
            HttpSession session) {

        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        String role = (String) session.getAttribute("role");

        DeviceDto restoredDevice = deviceServiceimpl.restoreDeviceByIp(ip_address);

        // Log the action
        deviceActivityLogService.logAction(
                userId,
                userName,
                role,
                "RESTORE_DEVICE",
                "Restored device with IP: " + ip_address
        );

        return restoredDevice;
    }


    @GetMapping("/recycled-device")
    public List<DeviceDto> recycledDevice()
    {
        return deviceServiceimpl.listOfRecycledDevices();
    }

    @PutMapping("/update-device/{ip_address}")
//    public ResponseEntity<DeviceDto> updateDevice(@PathVariable String ip_address, @RequestBody DeviceDto updatedDevice) {
//        DeviceDto result = deviceServiceimpl.updateDevice(ip_address, updatedDevice);
//        return ResponseEntity.ok(result);
//    }
    public ResponseEntity<DeviceDto> updateDevice(
            @PathVariable String ip_address,
            @RequestBody DeviceDto updatedDevice,
            HttpSession session) {

        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        String role = (String) session.getAttribute("role");

        DeviceDto result = deviceServiceimpl.updateDevice(ip_address, updatedDevice);

        // Log the action
        deviceActivityLogService.logAction(
                userId,
                userName,
                role,
                "UPDATE_DEVICE",
                "Updated device with IP: " + ip_address
        );

        return ResponseEntity.ok(result);
    }


    @GetMapping("/search-device/{ip_address}")
    public DeviceDto searchDevice(@PathVariable String ip_address)
    {

          DeviceDto result = deviceServiceimpl.searchDevice(ip_address);
          return result;

    }
    @GetMapping("/list-of-deleted-devices")
    public List<DeletedDevice> listOfDeviceDeleted(){
        return deviceServiceimpl.listOfDeletedDevices();
    }


}
