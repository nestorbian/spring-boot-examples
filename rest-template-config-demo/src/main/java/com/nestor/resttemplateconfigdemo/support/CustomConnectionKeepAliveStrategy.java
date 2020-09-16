package com.nestor.resttemplateconfigdemo.support;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

/**
 * 自定义keep-live策略
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/8/28
 */
public class CustomConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {

    private static final int DEFAULT_TIME_OUT = 30;

    /**
     * Returns the duration of time which this connection can be safely kept
     * idle. If the connection is left idle for longer than this period of time,
     * it MUST not reused. A value of 0 or less may be returned to indicate that
     * there is no suitable suggestion.
     * <p>
     * When coupled with a {@link ConnectionReuseStrategy}, if
     * {@link ConnectionReuseStrategy#keepAlive(
     *HttpResponse, HttpContext)} returns true, this allows you to control
     * how long the reuse will last. If keepAlive returns false, this should
     * have no meaningful impact
     *
     * @param response The last response received over the connection.
     * @param context  the context in which the connection is being used.
     * @return the duration in ms for which it is safe to keep the connection
     * idle, or &lt;=0 if no suggested duration.
     */
    @Override
    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        Args.notNull(response, "HTTP response");
        final HeaderElementIterator it = new BasicHeaderElementIterator(
                response.headerIterator(HTTP.CONN_KEEP_ALIVE));
        while (it.hasNext()) {
            final HeaderElement he = it.nextElement();
            final String param = he.getName();
            final String value = he.getValue();
            if (value != null && param.equalsIgnoreCase("timeout")) {
                try {
                    return Long.parseLong(value) * 1000;
                } catch(final NumberFormatException ignore) {
                }
            }
        }
        return DEFAULT_TIME_OUT * 1000;
    }
}
