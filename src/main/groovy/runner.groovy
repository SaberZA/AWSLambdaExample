import interfaces.*
import services.DogeService

/**
 * Created by Sab3r on 2016/02/07.
 */
def hello = new Hello()


def map = hello.myHandler(new Person("Steven", "van der Merwe"), new myContext() )

println(map.greeting)
println(map.test)

def dogeService = new DogeService();
def customers = dogeService.getAllCustomers();

//dogeService.addCustomer([fullName: "Divan", password: "123", email: "divan.visagie@kaleidocode.co.za"])

//(1..20).each {
//    dogeService.addProduct([id: it.toString(), description: "bootie $it", price: 100 + it])
//}

//dogeService.getProducts()
def cust1 = dogeService.getCustomer("divan.visagie@kaleidocode.co.za")
println "$cust1.id - $cust1.fullName - $cust1.password - $cust1.email"

//dogeService.createOrder(new Order())




