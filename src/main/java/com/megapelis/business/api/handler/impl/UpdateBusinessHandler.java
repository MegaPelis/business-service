package com.megapelis.business.api.handler.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.megapelis.business.api.handler.BusinessHandler;
import com.megapelis.business.model.dto.request.UpdateBusinessRQ;
import com.megapelis.business.model.dto.request.generic.Request;
import com.megapelis.business.model.dto.response.Response;
import com.megapelis.business.model.entity.Business;
import com.megapelis.business.model.enums.BusinessStatusEnum;
import com.megapelis.business.util.BusinessCommon;
import com.megapelis.business.util.BusinessException;

public class UpdateBusinessHandler extends BusinessHandler implements RequestHandler<Request, Response> {

    private AmazonDynamoDB db;
    private DynamoDBMapper mapper;

    @Override
    public Response execute(Request request) {
        Response response;
        try {
            Object object = validatePayload(request);
            response = handleRequest(request, null);
        } catch (BusinessException exception) {
            response = BusinessCommon.buildResponse(request, exception.getStatus());
        }
        return response;
    }
    @Override
    public Object validatePayload(Request request) throws BusinessException {
        UpdateBusinessRQ updateBusinessRQ = convertPayload(request, UpdateBusinessRQ.class);
        if (!BusinessCommon.isValidString(updateBusinessRQ.getId(), updateBusinessRQ.getName(), updateBusinessRQ.getDescription()))
            throw new BusinessException(BusinessStatusEnum.ERROR_FORMAT_REQUEST);
        return updateBusinessRQ;
    }

    @Override
    public Response handleRequest(Request request, Context context) {
        db = AmazonDynamoDBClientBuilder.defaultClient();
        mapper = new DynamoDBMapper(db);
        Business business = BusinessCommon.convertObjectToClass(request.getData(), Business.class);
        System.out.println("Business to update: " + business.getId());
        Business businessDB = mapper.load(Business.class, business.getId());
        businessDB.setName(business.getName());
        businessDB.setDescription(business.getDescription());
        businessDB.setLastModifiedDate(BusinessCommon.getDateTime());
        mapper.save(businessDB);
        return BusinessCommon.buildResponse(request, BusinessStatusEnum.SUCCESS, businessDB);
    }
}
