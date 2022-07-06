package com.Prograd.EmployeeManagement.Service;

import com.Prograd.EmployeeManagement.Exceptions.AssetNotFound;
import com.Prograd.EmployeeManagement.Exceptions.NoAccessException;
import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Asset;

import java.util.List;

public interface AssetService {
    Asset saveAsset(Asset asset) throws OrganisationNotFound, NoAccessException;
    List<Asset> getAllAssets();
    Asset getAssetById(int id) throws AssetNotFound;
    Asset updateAsset(Asset asset,int id) throws AssetNotFound;
    void deleteAsset(int id);
}
