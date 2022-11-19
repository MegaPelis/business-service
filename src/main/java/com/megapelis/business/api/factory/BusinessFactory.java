package com.megapelis.business.api.factory;

import com.megapelis.business.api.handler.BusinessHandler;
import com.megapelis.business.api.handler.impl.CreateBusinessHandler;
import com.megapelis.business.api.handler.impl.DeleteBusinessHandler;
import com.megapelis.business.api.handler.impl.FindAllBusinessHandler;
import com.megapelis.business.api.handler.impl.UpdateBusinessHandler;
import com.megapelis.business.model.dto.request.generic.Request;
import com.megapelis.business.model.dto.response.Response;
import com.megapelis.business.model.enums.BusinessOperationEnum;
import com.megapelis.business.model.enums.BusinessStatusEnum;
import com.megapelis.business.util.BusinessCommon;
import com.megapelis.business.util.BusinessException;

/**
 * Clase {@link BusinessFactory}
 *
 * @author yadir.garcia.
 */
public class BusinessFactory {
    private BusinessFactory() {
    }
    /**
     * Fabrica que permite ejecutar mediante la operacion.
     * @param request
     * @return {@link Response}
     */

    public static Response handler(Request request) {
        BusinessCommon.output(request);
        Response response = null;
        BusinessHandler handler = null;
        try {
            BusinessCommon.isValidRequest(request);
            switch (BusinessOperationEnum.isValid(request.getOperation())){
                case CREATE:
                    handler = new CreateBusinessHandler();
                    break;
                case FIND_ALL:
                    handler = new FindAllBusinessHandler();
                    break;
                case DELETE:
                    handler = new DeleteBusinessHandler();
                    break;
                case UPDATE:
                    handler = new UpdateBusinessHandler();
                    break;
                default:
                    response =  BusinessCommon.buildResponse(request, BusinessStatusEnum.ERROR);
                    break;
            }
        } catch (BusinessException e) {
            response =  BusinessCommon.buildResponse(request, e.getStatus());
        }
        if(null == response && null != handler)
            response = handler.execute(request);
        BusinessCommon.output(response);
        return response;
    }


}
