package br.com.caelum.apigateway;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//@Component
public class RateLimitingZuulFilter extends ZuulFilter {

	private final RateLimiter rateLimiter = RateLimiter.create(1.0 / 30.0);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 100;
	}

	@Override
	public Object run() throws ZuulException {
		if (!this.rateLimiter.tryAcquire()) {
			try {
				RequestContext currentContext = RequestContext.getCurrentContext();
				HttpServletResponse response = currentContext.getResponse();
				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
				response.getWriter().append(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());

				currentContext.setSendZuulResponse(false);

			} catch (IOException e) {
				ReflectionUtils.rethrowRuntimeException(e);
			}
		}
		return null;
	}

}
