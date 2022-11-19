package com.megapelis.business.api.handler.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.megapelis.business.api.handler.BusinessHandler;
import com.megapelis.business.model.dto.request.CreateBusinessRQ;
import com.megapelis.business.model.dto.request.generic.Request;
import com.megapelis.business.model.dto.response.Response;
import com.megapelis.business.model.entity.Business;
import com.megapelis.business.model.enums.BusinessStatusEnum;
import com.megapelis.business.util.BusinessCommon;
import com.megapelis.business.util.BusinessException;

/**
 * Clase {@link CreateBusinessHandler}
 *
 * @author yadir.garcia.
 */
public class CreateBusinessHandler extends BusinessHandler implements RequestHandler<Request, Response> {

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

    /**
     * Metodo que permite validar el payload.
     *
     * @param request
     * @return {@link Object}
     * @throws BusinessException
     */
    @Override
    public Object validatePayload(Request request) throws BusinessException {
        CreateBusinessRQ createBusinessRQ = convertPayload(request, CreateBusinessRQ.class);
        if (!BusinessCommon.isValidString(createBusinessRQ.getName(), createBusinessRQ.getDescription()))
            throw new BusinessException(BusinessStatusEnum.ERROR_FORMAT_REQUEST);
        return createBusinessRQ;
    }

    @Override
    public Response handleRequest(Request request, Context context) {
        db = AmazonDynamoDBClientBuilder.defaultClient();
        mapper = new DynamoDBMapper(db);
        Business business = BusinessCommon.convertObjectToClass(request.getData(), Business.class);
        business.setCreatedDate(BusinessCommon.getDateTime());
        business.setLastModifiedDate(null);
        mapper.save(business);
        return BusinessCommon.buildResponse(request, BusinessStatusEnum.SUCCESS);
    }
}
