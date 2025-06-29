package com.mahmoud.graphqlPOC.exception;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError( Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof AuthorNotFoundException || ex instanceof BookNotFoundException){
            return GraphQLError.newError()
                               .errorType(ErrorType.NOT_FOUND)
                               .message(ex.getMessage())
                               .location(env.getField().getSourceLocation())
                               .path(env.getExecutionStepInfo().getPath())
                               .build();
        }
        else{
            return null;
        }
    }
}
