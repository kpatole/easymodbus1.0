package com.syncpower.system.repository;

import com.syncpower.system.beans.CurrentMeterConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentMeterRepository extends JpaRepository<CurrentMeterConsumption, String> {

    @Query("SELECT o FROM CurrentMeterConsumption o WHERE LOWER(o.meterNo) = LOWER(:meterNo) order by o.recordId desc")
    public List<CurrentMeterConsumption> retriveByMeterNo(@Param("meterNo") String meterNo);
}
