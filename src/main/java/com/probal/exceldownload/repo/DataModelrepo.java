package com.probal.exceldownload.repo;

import com.probal.exceldownload.model.DataModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataModelrepo extends CrudRepository<DataModel, Long> {

    List<DataModel> findAll();
}
