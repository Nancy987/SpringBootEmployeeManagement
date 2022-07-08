package com.Prograd.EmployeeManagement.Controllers;

import com.Prograd.EmployeeManagement.Exceptions.AssetNotFound;
import com.Prograd.EmployeeManagement.Exceptions.NoAccessException;
import com.Prograd.EmployeeManagement.Exceptions.OrganisationNotFound;
import com.Prograd.EmployeeManagement.Modals.Asset;
import com.Prograd.EmployeeManagement.Service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("assets")
public class AssetController {

    private AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }
    @PostMapping
    public ResponseEntity<Asset> saveAsset(@Valid @RequestBody Asset asset) throws OrganisationNotFound, NoAccessException {
        return new ResponseEntity<Asset>(assetService.saveAsset(asset), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Asset> getAllAssets() throws OrganisationNotFound {
        return assetService.getAllAssets();
    }
    @GetMapping("{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable("id") int id) throws AssetNotFound, NoAccessException, OrganisationNotFound {
        return new ResponseEntity<Asset>(assetService.getAssetById(id), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable("id") int id,@Valid @RequestBody Asset Asset) throws AssetNotFound, NoAccessException, OrganisationNotFound {
        return new ResponseEntity<Asset>(assetService.updateAsset(Asset,id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable("id") int id) throws NoAccessException, OrganisationNotFound {
        assetService.deleteAsset(id);
        return new ResponseEntity<String>("Asset is deleted successfully.",HttpStatus.OK);
    }
}
