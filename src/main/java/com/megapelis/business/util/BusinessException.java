package com.megapelis.business.util;

import com.megapelis.business.model.enums.BusinessStatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase {@link BusinessException}
 * @author yadir.garcia.
 */
@Getter
@Setter
public class BusinessException extends Exception {
    private BusinessStatusEnum status;

    public BusinessException(BusinessStatusEnum status) {
        super(status.getMessageBackend());
        this.status = status;
    }
}

