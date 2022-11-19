package com.megapelis.business.mapper;

import com.megapelis.business.model.dto.response.Response;
import com.megapelis.business.model.entity.Business;

import java.util.List;

public interface BusinessMapper {
    Response toDto(Business business);
    List<Response> toDtoList(List<Business> businessList);
}
