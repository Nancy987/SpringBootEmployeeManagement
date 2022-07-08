package com.Prograd.EmployeeManagement.Repository;

import com.Prograd.EmployeeManagement.Modals.Asset;
import com.Prograd.EmployeeManagement.Modals.Employee;
import com.Prograd.EmployeeManagement.Modals.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset,Integer> {
    @Transactional
    @Modifying
    @Query("delete FROM Asset u WHERE u.organisation =:orgId")
    public void deleteAllAssetByOrgId(@Param("orgId") Organisation orgId);

    @Query("select u FROM Asset u WHERE u.organisation =:orgId")
    public List<Asset> findAssetsByOrganisation(@Param("orgId") Organisation orgId);

    @Query("select u FROM Asset u WHERE u.id=:empId and u.organisation =:orgId")
    public Asset findOneAssetByOrganisation(@Param("empId") int empId,@Param("orgId") Organisation orgId);
}
