package com.probal.exceldownload.service;


import com.probal.exceldownload.helper.ExcelHelper;
import com.probal.exceldownload.model.DataModel;
import com.probal.exceldownload.repo.DataModelrepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class DataModelService {

    public final DataModelrepo dataModelrepo;

    public ByteArrayInputStream load() {
        List<DataModel> modelList = dataModelrepo.findAll();
        return ExcelHelper.exportToExcel(modelList);
    }
}
