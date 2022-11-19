package com.megapelis.business.model.enums;

import com.megapelis.business.util.BusinessException;
import lombok.Getter;

import java.util.Arrays;

/**
 * Clase {@link BusinessOperationEnum}
 *
 * @author yadir.garcia.
 */
@Getter
public enum BusinessOperationEnum {

    CREATE("create"),
    FIND_ALL("findAll"),
    DELETE("delete"),
    UPDATE("update");

    private String name;

    BusinessOperationEnum(String name) {
        this.name = name;
    }

    /**
     * Metodo que permite validar si existe esa operación
     * @param name
     * @return {@link BusinessOperationEnum}
     * @throws BusinessException
     */
    public static BusinessOperationEnum isValid(String name) throws BusinessException {
        return Arrays.stream(BusinessOperationEnum.values())
                .filter(operationEnum -> operationEnum.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new BusinessException(BusinessStatusEnum.ERROR_SERVICE_OR_OPERATION));
    }

}
