package com.probal.exceldownload.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataModelResponse {

    private String responseCode;

    private String responseMessage;

    private Object responseBody;

    public static DataModelResponse from(String code, String message, Object body) {
        return DataModelResponse
                .builder()
                .responseCode(code)
                .responseMessage(message)
                .responseBody(body)
                .build();
    }
}
