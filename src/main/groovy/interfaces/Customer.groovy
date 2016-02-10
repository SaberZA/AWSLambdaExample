package interfaces

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

/**
 * Created by stevenv on 2016-02-10.
 */
@DynamoDBTable(tableName="Customer")
class Customer {
    def id
    def fullName
    def password
    def email
}
