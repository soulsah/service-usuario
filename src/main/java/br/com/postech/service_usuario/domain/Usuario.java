package br.com.postech.service_usuario.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDbBean
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "usuarios")
public class Usuario {

    @DynamoDBHashKey
    private UUID id;

    @DynamoDBAttribute
    private String tipoUsuario;

    @DynamoDBAttribute
    private String nome;

    @DynamoDBAttribute
    private String documento;

    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute
    private String crm;

}
