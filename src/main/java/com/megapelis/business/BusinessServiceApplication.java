package com.megapelis.business;


import com.megapelis.business.api.factory.BusinessFactory;
import com.megapelis.business.model.dto.request.generic.Request;
import com.megapelis.business.model.dto.response.Response;

/**
 * Clase {@link BusinessServiceApplication}
 * @author yadir.garcia.
 */
public class BusinessServiceApplication {

    /**
     * Metodo que permite realizar el llamado de los servicios.
     * @param request
     * @return {@link Response}
     */

    public Response handler(Request request){
        return BusinessFactory.handler(request);
    }


}
