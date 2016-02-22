package ${packageName}

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.S3Event
import dagger.ObjectGraph
import org.apache.log4j.Logger
import ${packageName}.config.LambdaConfig

public class ${mainClassName} implements RequestHandler<S3Event, String> {

  private static final Logger LOG = Logger.getLogger(PrFilterRequestHandler)

  @Override
  public String handleRequest(final S3Event s3Event, final Context context) {

    final ObjectGraph objectGraph = ObjectGraph.create(new LambdaConfig(context))
    final EventHandler eventHandler = objectGraph.get(EventHandler)
    eventHandler.handleEvents(s3Event)

    LOG.info("Received S3 event \${s3Event.getRecords()}")

    return "Log stream = \${context.getLogStreamName()}".toString()
  }
}
