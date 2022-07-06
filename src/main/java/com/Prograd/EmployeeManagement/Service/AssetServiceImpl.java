package com.Prograd.EmployeeManagement.Service;

import com.Prograd.EmployeeManagement.Exceptions.AssetNotFound;
import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Asset;
import com.Prograd.EmployeeManagement.Modals.Organisation;
import com.Prograd.EmployeeManagement.Repository.AssetRepository;
import com.Prograd.EmployeeManagement.Repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    private AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository AssetRepository) {
        this.assetRepository = AssetRepository;
    }

    @Autowired
    private OrganisationRepository organisationRepository;

    @Override
    public Asset saveAsset(Asset asset) throws OrganisationNotFound {
        int quantity = asset.getQuantity();
        float cost_per_asset = asset.getCost_per_asset();
        float total_cost = cost_per_asset*quantity;
        asset.setTotal_cost(total_cost);
        Organisation organisation = organisationRepository.findById(10).orElseThrow(()->new OrganisationNotFound("Organisation not exist"));
        asset.setOrganisation(organisation);
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(int id) throws AssetNotFound {
        Asset asset = assetRepository.findById(id).orElseThrow(()->new AssetNotFound("Asset not exist"));
        return asset;
    }

    @Override
    public Asset updateAsset(Asset asset, int id) throws AssetNotFound {
        Asset existingAsset = assetRepository.findById(id).orElseThrow(()->new AssetNotFound(("Asset not exist")));
        int quantity = asset.getQuantity();
        float cost_per_asset = asset.getCost_per_asset();
        float total_cost = cost_per_asset*quantity;

        existingAsset.setAsset_name(asset.getAsset_name());
        existingAsset.setQuantity(asset.getQuantity());
        existingAsset.setCost_per_asset(asset.getCost_per_asset());
        existingAsset.setTotal_cost(total_cost);

        return assetRepository.save(existingAsset);
    }

    @Override
    public void deleteAsset(int id) {
        assetRepository.deleteById(id);
    }
}

