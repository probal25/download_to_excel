package com.probal.exceldownload.controller;

import com.probal.exceldownload.model.DataModel;
import com.probal.exceldownload.repo.DataModelrepo;
import com.probal.exceldownload.request.DataModelRequest;
import com.probal.exceldownload.response.DataModelResponse;
import com.probal.exceldownload.service.DataModelService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/data_model")
public class DataController {

    private final DataModelrepo dataModelrepo;
    private final DataModelService dataModelService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllData() {
        List<DataModel> all = dataModelrepo.findAll();
        DataModelResponse response = DataModelResponse.from("200", "Data", all);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save_data")
    public ResponseEntity<?> saveData(@RequestBody DataModelRequest request) {

        try {
            DataModel dataModel = DataModelRequest.from(request);
            dataModelrepo.save(dataModel);
            DataModelResponse response = DataModelResponse.from("200", "Data saved successfully", dataModel);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            DataModelResponse response = DataModelResponse.from("400", "Error", e.getMessage());
            return ResponseEntity.ok(response);
        }

    }


    @PostMapping("/save_all_data")
    public ResponseEntity<?> saveAllData(@RequestBody List<DataModelRequest> requestList) {

        try {
            List<DataModel> modelList = requestList
                    .stream()
                    .map(DataModelRequest::from)
                    .collect(Collectors.toList());
            dataModelrepo.saveAll(modelList);
            DataModelResponse response = DataModelResponse.from("200", "All Data saved successfully",
                    modelList.stream()
                            .map(DataModel::getName)
                            .collect(Collectors.toList()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            DataModelResponse response = DataModelResponse.from("400", "Error", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "data.xlsx";
        InputStreamResource file = new InputStreamResource(dataModelService.load());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

}
