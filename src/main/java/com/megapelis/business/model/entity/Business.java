package com.megapelis.business.model.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.megapelis.business.util.BusinessConstant;
import lombok.*;

/**
 * @author yadir.garcia.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = BusinessConstant.TABLA_NAME_BUSINESS)
public class Business {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    private String createdDate;

    @DynamoDBAttribute
    private String lastModifiedDate;

}
