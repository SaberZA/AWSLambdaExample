package interfaces

import com.amazonaws.services.lambda.runtime.ClientContext
import com.amazonaws.services.lambda.runtime.CognitoIdentity
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger

/**
 * Created by Sab3r on 2016/02/07.
 */
class myContext
        implements Context {

    @Override
    String getAwsRequestId() {
        return null
    }

    @Override
    String getLogGroupName() {
        return null
    }

    @Override
    String getLogStreamName() {
        return null
    }

    @Override
    String getFunctionName() {
        return null
    }

    @Override
    CognitoIdentity getIdentity() {
        return null
    }

    @Override
    ClientContext getClientContext() {
        return null
    }

    @Override
    int getRemainingTimeInMillis() {
        return 0
    }

    @Override
    int getMemoryLimitInMB() {
        return 0
    }

    @Override
    LambdaLogger getLogger() {
        return new LambdaLogger() {
            @Override
            void log(String string) {

            }
        }
    }
}
