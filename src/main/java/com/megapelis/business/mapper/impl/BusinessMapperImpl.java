package com.megapelis.business.mapper.impl;

import com.megapelis.business.mapper.BusinessMapper;
import com.megapelis.business.model.dto.response.Response;
import com.megapelis.business.model.entity.Business;
import com.megapelis.business.util.BusinessCommon;

import java.util.ArrayList;
import java.util.List;

public class BusinessMapperImpl implements BusinessMapper {

    @Override
    public Response toDto(Business business) {
        if (business == null)
            return null;
        Response response = new Response();
        response.setData(BusinessCommon.convertObjectToClass(business, Business.class));
        return response;
    }

    @Override
    public List<Response> toDtoList(List<Business> businessList) {
        List<Response> responses = new ArrayList<>();
        for (Business business : businessList)
            responses.add(toDto(business));
        return responses;
    }
}
