package com.probal.exceldownload.request;


import com.probal.exceldownload.model.DataModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataModelRequest {

    String name;

    String nameBangla;

    String address;

    String addressBangla;

    public static DataModel from(DataModelRequest dataModelRequest) {

        DataModel dataModel = new DataModel();

        dataModel.setName(dataModelRequest.getName());
        dataModel.setNameInBangla(dataModelRequest.getNameBangla());
        dataModel.setAddress(dataModelRequest.getAddress());
        dataModel.setAddressInBangla(dataModelRequest.getAddressBangla());

        return dataModel;
    }
}
