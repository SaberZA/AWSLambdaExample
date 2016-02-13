package services

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.ItemCollection
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome
import com.amazonaws.services.dynamodbv2.document.ScanOutcome
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec
import com.amazonaws.services.dynamodbv2.document.utils.NameMap
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.ScanRequest
import com.amazonaws.services.dynamodbv2.model.ScanResult
import com.amazonaws.services.dynamodbv2.xspec.GetItemExpressionSpec
import interfaces.Customer

/**
 * Created by stevenv on 2016-02-10.
 */

class DogeService {
    BasicAWSCredentials awsCreds = new BasicAWSCredentials('AKIAJ7FNOOSJFKE4LGDA', 'IIRQo5ZM4haVvzHUtFAi6WKjOo5/6cuP+QK3bblj')
    AmazonDynamoDBClient client = new AmazonDynamoDBClient(awsCreds)
            .withEndpoint("http://dynamodb.eu-west-1.amazonaws.com")
    DynamoDB dynamoDB = new DynamoDB(client)
    DynamoDBMapper mapper = new DynamoDBMapper(client);

    PutItemOutcome outcome

    def addCustomer(customer) {
        def existingCustomer = getCustomer(customer.email)
        if (existingCustomer != null)
            return

        try {
            Table table = dynamoDB.getTable("Customer")
            def id = UUID.randomUUID()

            outcome = table.putItem(new Item()
                    .withPrimaryKey("id", id.toString())
                    .withString("fullName", customer.fullName.toString())
                    .withString("password", customer.password.toString())
                    .withString("email", customer.email.toString()))

        } catch (Exception e) {
            System.err.println("Unable to add item: $customer.fullName - $customer.email")
            System.err.println(e.getMessage())
        }
    }

    def getAllCustomers() {
        ScanSpec scanSpec = new ScanSpec()
                .withProjectionExpression("id, fullName, password, email")

        def customerList = []
        try {
            Table table = dynamoDB.getTable("Customer")
            ItemCollection<ScanOutcome> items = table.scan(scanSpec)

            Iterator<Item> iter = items.iterator()
            while (iter.hasNext()) {
                Item item = iter.next()
                customerList.add(item)
//                println(item.fullName)
            }

        } catch (Exception e) {
            System.err.println("Unable to scan the table:")
            System.err.println(e.getMessage())
        }

        return customerList
    }

    def createOrder(order) {
        def orderId = UUID.randomUUID()
        def orderLineId = UUID.randomUUID()
        try {
            Table orderTable = dynamoDB.getTable("Order")
            Table orderLineTable = dynamoDB.getTable("Order_Line")

            def outcomeOrder = orderTable.putItem(new Item()
                    .withPrimaryKey("id", orderId.toString())
                    .withString("customerId", order.customerId.toString())
                    .withString("date", order.date.toString()))

            for(def product in order.products) {
                def outcomeOrderLine = orderLineTable.putItem(new Item()
                        .withPrimaryKey("id", orderLineId.toString())
                        .withString("customerId", order.customerId.toString())
                        .withString("productId", product.productId.toString()))
            }
        }
        catch (Exception e) {
            System.err.println("Unable to add order for customer: $order.customerId")
            System.err.println(e.getMessage())
        }


    }

    def getCustomer(email) {
        try {
            Table table = dynamoDB.getTable("Customer")

            Map<String, Object> eav = new HashMap<String, Object>();
            eav.put(":email", email.toString());

            def customerList = []

            ScanSpec scanSpec = new ScanSpec()
                .withFilterExpression("email = :email")
                .withValueMap(eav)
                .withProjectionExpression("id, fullName, password, email")

            ItemCollection<ScanOutcome> items = table.scan(scanSpec)

            Iterator<Item> iter = items.iterator()
            while (iter.hasNext()) {
                Item item = iter.next()
                customerList.add(item)
//                println(item.id)
            }

            if (customerList.size() < 1)
                return null
            return customerList[0]

        } catch (Exception e) {
            System.err.println("Unable to get item: $email")
            System.err.println(e.getMessage())
        }

        return null
    }

    def login(email, password) {
        def customer = getCustomer(email)

        if (customer == null)
            return null

        if (password == '' || password == null) {
            return null
        }

        if (password == customer.password) {
            return customer
        }
        else
            return null
    }

    def register(customer) {
        addCustomer(customer)
    }

    def getProducts() {
        Table table = dynamoDB.getTable("Product")
        ScanSpec scanSpec = new ScanSpec()
                .withProjectionExpression("id, description, price")

        def productList = []
        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec)

            Iterator<Item> iter = items.iterator()
            while (iter.hasNext()) {
                Item item = iter.next()
                productList.add(item)
                println(item.description)
            }

        } catch (Exception e) {
            System.err.println("Unable to scan the table: Products")
            System.err.println(e.getMessage())
        }

        return productList
    }

    def addProduct(product) {
        try {
            Table table = dynamoDB.getTable("Product")
            def id = UUID.randomUUID()

            outcome = table.putItem(new Item()
                    .withPrimaryKey("id", product.id)
                    .withString("description", product.description.toString())
                    .withString("price", product.price.toString()))

        } catch (Exception e) {
            System.err.println("Unable to add item: $product.id - $product.description")
            System.err.println(e.getMessage())
        }
    }

}
