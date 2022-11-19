package com.megapelis.business.api.handler.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.megapelis.business.api.handler.BusinessHandler;
import com.megapelis.business.model.dto.request.FindAllBusinessRQ;
import com.megapelis.business.model.dto.request.generic.Request;
import com.megapelis.business.model.dto.response.Response;
import com.megapelis.business.model.entity.Business;
import com.megapelis.business.model.enums.BusinessStatusEnum;
import com.megapelis.business.util.BusinessCommon;
import com.megapelis.business.util.BusinessConstant;
import com.megapelis.business.util.BusinessException;

import java.util.List;

/**
 * Clase {@link FindAllBusinessHandler}
 *
 * @author yadir.garcia.
 */

public class FindAllBusinessHandler extends BusinessHandler implements RequestHandler<Request, Response> {

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
        FindAllBusinessRQ findAllBusinessRQ = convertPayload(request, FindAllBusinessRQ.class);
        if(BusinessConstant.INTEGER_ONE > findAllBusinessRQ.getPage())
            findAllBusinessRQ.setPage(BusinessConstant.INTEGER_ONE);
        return findAllBusinessRQ;
    }

    @Override
    public Response handleRequest(Request request, Context context) {
        db = AmazonDynamoDBClientBuilder.defaultClient();
        mapper = new DynamoDBMapper(db);
        List<Business> businesses = mapper.scan(Business.class, new DynamoDBScanExpression());
        return BusinessCommon.buildResponse(request, BusinessStatusEnum.SUCCESS, businesses);
    }
}
