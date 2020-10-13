package com.syncpower.system.repository;

import com.syncpower.system.beans.CurrentDeviceConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentDeviceRepository extends JpaRepository<CurrentDeviceConsumption, Long> {

    @Query("SELECT o FROM CurrentDeviceConsumption o WHERE LOWER(o.deviceNo) = LOWER(:deviceNo) order by o.recordId desc")
    public List<CurrentDeviceConsumption> retriveByDeviceNo(@Param("deviceNo") String deviceNo);
    
    //SELECT DeptID, MAX(Salary) FROM EmpDetails GROUP BY DeptID
    @Query("SELECT o FROM CurrentDeviceConsumption o "
    		+ "WHERE o.totalEnergyConsumed= (Select MAX(o.totalEnergyConsumed) FROM CurrentDeviceConsumption o GROUP BY o.deviceNo)")
    public List<CurrentDeviceConsumption> retrieveByMaxEnergyConsumed();
}
