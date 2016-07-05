package com.testcompany.app.customerdata.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.NotFoundException;
import com.testcompany.app.customerdata.business.exception.BusinessLogicException;
import com.testcompany.app.customerdata.business.exception.ValidationException;

/**
 * Generate HTTP responses for different type exceptions
 */
@Service
@Provider
public class RestExceptionMapper
        implements ExceptionMapper<Exception> {

    private Logger logger = LoggerFactory.getLogger(RestExceptionMapper.class);

    @Override
    public Response toResponse(final Exception exception) {
        logger.error(exception.getMessage(), exception);

        if (exception instanceof NotFoundException) {
            // this exception thrown from jersey when access undefined url
            final String uri = ((NotFoundException) exception).getNotFoundUri() == null ? null : ((NotFoundException) exception).getNotFoundUri().toString();
            return Response.status(Response.Status.NOT_FOUND).entity("Not found: " + uri).type(MediaType.TEXT_PLAIN).build();
        } else if (exception instanceof ValidationException) {
            final ValidationException validationException = (ValidationException) exception;
            return Response.status(400).entity(validationException.getMessage()).type(MediaType.TEXT_PLAIN).build();
        } else if (exception instanceof BusinessLogicException) {
            final BusinessLogicException businessLogicException = (BusinessLogicException) exception;
            return Response.status(businessLogicException.getErrorCode()).entity(businessLogicException.getMessage()).type(MediaType.TEXT_PLAIN).build();
        } else if (exception instanceof JsonMappingException) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad payload").type(MediaType.TEXT_PLAIN).build();
        } else if (exception instanceof WebApplicationException && ((WebApplicationException) exception).getResponse() != null) {
            return ((WebApplicationException) exception).getResponse();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").type(MediaType.TEXT_PLAIN).build();
        }
    }

    public void setRootLogger(final String rootLogger) {
        if (StringUtils.isNotBlank(rootLogger)) {
            logger = LoggerFactory.getLogger(rootLogger + "." + this.getClass().getSimpleName());
        }
    }
}
