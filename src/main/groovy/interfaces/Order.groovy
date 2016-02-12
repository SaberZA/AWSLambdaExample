package interfaces

import org.joda.time.DateTime

/**
 * Created by stevenv on 2016-02-12.
 */
class Order {
    def id
    def customerId
    def products
    def date

    Order() {
        this.id = UUID.randomUUID()
        this.customerId = UUID.randomUUID()
        this.products = [[productId: 1, description: 'bootie 1', price: 101]]
        this.date = new DateTime().toString()
    }
}
