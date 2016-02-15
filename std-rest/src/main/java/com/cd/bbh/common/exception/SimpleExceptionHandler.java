package com.cd.bbh.common.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;

public class SimpleExceptionHandler extends AbstractHandlerExceptionResolver implements InitializingBean {
	private HttpMessageConverter<?>[] messageConverters = null;

	private List<HttpMessageConverter<?>> allMessageConverters = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		ensureMessageConverters();
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ServletWebRequest webRequest = new ServletWebRequest(request, response);
		ModelAndView mav = null;
		try {
			if (ex instanceof ApplicationException) {
				logger.error(ex.getMessage() != null ? ex.getMessage() : ((ApplicationException) ex).getErrorMsg());
				Result result = new Result(((ApplicationException) ex).getErrorCode(), ((ApplicationException) ex).getErrorMsg());
				return handleResponseBody(result, webRequest);
			}
			if (ex instanceof MaxUploadSizeExceededException) {
				Result result = new Result(ResultEnum.FILE_SIZE_EXCEEDS_ERROR);
				logger.error(ex.getMessage(), ex);
				return handleResponseBody(result, webRequest);
			} else {
				logger.error(ex.getMessage(), ex);
				Result result = new Result(ResultEnum.UNKNOW_ERROR);
				return handleResponseBody(result, webRequest);
			}
		} catch (Exception exce) {
			logger.error("resolve exception failed.", exce);
		}

		return mav;
	}

	public void setMessageConverters(HttpMessageConverter<?>[] messageConverters) {
		this.messageConverters = messageConverters;
	}

	@SuppressWarnings("unchecked")
	private void ensureMessageConverters() {

		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();

		// user configured values take precedence:
		if (this.messageConverters != null && this.messageConverters.length > 0) {
			converters.addAll(CollectionUtils.arrayToList(this.messageConverters));
		}

		// defaults next:
		new HttpMessageConverterHelper().addDefaults(converters);

		this.allMessageConverters = converters;
	}

	// leverage Spring's existing default setup behavior:
	private static final class HttpMessageConverterHelper extends WebMvcConfigurationSupport {
		public void addDefaults(List<HttpMessageConverter<?>> converters) {
			addDefaultHttpMessageConverters(converters);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	private ModelAndView handleResponseBody(Object body, ServletWebRequest webRequest) throws ServletException, IOException {

		HttpInputMessage inputMessage = new ServletServerHttpRequest(webRequest.getRequest());

		List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
		if (acceptedMediaTypes.isEmpty()) {
			acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
		}

		MediaType.sortByQualityValue(acceptedMediaTypes);

		HttpOutputMessage outputMessage = new ServletServerHttpResponse(webRequest.getResponse());

		Class<?> bodyType = body.getClass();

		List<HttpMessageConverter<?>> converters = this.allMessageConverters;

		if (converters != null) {
			for (MediaType acceptedMediaType : acceptedMediaTypes) {
				for (HttpMessageConverter messageConverter : converters) {
					if (messageConverter.canWrite(bodyType, acceptedMediaType)) {
						messageConverter.write(body, acceptedMediaType, outputMessage);
						return new ModelAndView();
					}
				}
			}
		}

		if (logger.isWarnEnabled()) {
			logger.warn("Could not find HttpMessageConverter that supports return type [" + bodyType + "] and " + acceptedMediaTypes);
		}
		return null;
	}
}
