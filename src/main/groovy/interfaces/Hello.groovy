package interfaces

import com.amazonaws.services.lambda.runtime.Context

class Hello {

    // Our lambda function handler
    Map myHandler(data, Context context) {
        context.logger.log "received in groovy: $data"
        [greeting: "interfaces.Hello ${data?.firstName} ${data?.lastName}".toString(),
        test: "Testing Groovy Map"]
    }

}