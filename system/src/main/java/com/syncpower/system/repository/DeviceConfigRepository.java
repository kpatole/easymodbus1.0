package com.syncpower.system.repository;

import com.syncpower.system.beans.DeviceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceConfigRepository extends JpaRepository<DeviceConfig, Long> {

}
