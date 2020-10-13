package com.syncpower.system.repository;

import com.syncpower.system.beans.DeviceStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStatusRepository extends JpaRepository<DeviceStatus, Long> {

    @Query("SELECT o FROM DeviceStatus o WHERE LOWER(o.deviceNo) = LOWER(:deviceNo) order by o.recordId desc")
    public DeviceStatus retriveByDeviceNo(@Param("deviceNo") String deviceNo);
}
