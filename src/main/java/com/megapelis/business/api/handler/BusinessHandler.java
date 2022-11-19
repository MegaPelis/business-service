package com.megapelis.business.api.handler;

import com.megapelis.business.model.dto.request.generic.Request;
import com.megapelis.business.model.dto.response.Response;
import com.megapelis.business.model.enums.BusinessStatusEnum;
import com.megapelis.business.util.BusinessCommon;
import com.megapelis.business.util.BusinessException;

public abstract class BusinessHandler extends BusinessGenericCommon {
    /**
     * Metodo que permite ejecutar la logica del handler.
     * @param request
     * @return {@link Response}
     */
    public Response execute(Request request) {
        Response response;
        try {
            Object object = validatePayload(request);

            response = BusinessCommon.buildResponse(request, BusinessStatusEnum.SUCCESS);
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
    public abstract Object validatePayload(Request request) throws BusinessException;

}
