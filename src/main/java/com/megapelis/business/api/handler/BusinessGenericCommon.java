package com.megapelis.business.api.handler;

import com.megapelis.business.model.dto.request.generic.Request;
import com.megapelis.business.model.enums.BusinessStatusEnum;
import com.megapelis.business.util.BusinessCommon;
import com.megapelis.business.util.BusinessException;

/**
 * Clase {@link BusinessGenericCommon}
 * @author yadir.garcia.
 */
public class BusinessGenericCommon {

    public BusinessGenericCommon(){
    }

    /**
     * Metodo que permite parsear el payload.
     * @param request
     * @param clazz
     * @return {@link T}
     * @param <T>
     * @throws BusinessException
     */
    protected <T> T convertPayload(Request request, Class<T> clazz) throws BusinessException {
        if(null == request || null == request.getData())
            throw new BusinessException(BusinessStatusEnum.ERROR_FORMAT_REQUEST);
        return BusinessCommon.convertObjectToClass(request.getData(), clazz);
    }
}
