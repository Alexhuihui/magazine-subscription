package top.alexmmd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 *
 * <h1>在过滤器中存储客户端发起请求的时间戳</h1>
 * @author 汪永晖
 */
@Component
public class PreRequestFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //指定过滤器类型，由于记录时间是发生在最开始的，所以是PRE_TYPE
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤器优先级，数字越小优先级越高
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否启用当前的过滤器
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        //用于在过滤器之间传递消息
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime",  System.currentTimeMillis());

        return null;
    }
}
