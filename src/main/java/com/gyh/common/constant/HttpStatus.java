package com.gyh.common.constant;

/**
 * @author guoyh
 * @Date 2019/7/23 13:58
 */
public class HttpStatus {

    /**
     * 状态码(100)，指示客户端可以继续。
     */
    public static final int SC_CONTINUE = 100;

    /**
     * 状态码(101)，根据升级头指示服务器正在切换协议。
     */
    public static final int SC_SWITCHING_PROTOCOLS = 101;

    /**
     * 状态码(200)，指示请求正常成功。
     */
    public static final int SC_OK = 200;

    /**
     * 状态码(201)，指示请求成功并在服务器上创建了一个新资源。
     */
    public static final int SC_CREATED = 201;

    /**
     * 状态码(202)，指示接受请求进行处理，但尚未完成。
     */
    public static final int SC_ACCEPTED = 202;

    /**
     * 状态码(203)，表示客户机提供的元信息不是来自服务器。
     */
    public static final int SC_NON_AUTHORITATIVE_INFORMATION = 203;

    /**
     * 状态码(204)，指示请求成功，但没有要返回的新信息。
     */
    public static final int SC_NO_CONTENT = 204;

    /**
     * 状态码(205)指示代理 "SHOULD" 重置导致发送请求的文档视图。
     */
    public static final int SC_RESET_CONTENT = 205;

    /**
     * 状态码(206)，表示服务器已经完成了对资源的部分GET请求。
     */
    public static final int SC_PARTIAL_CONTENT = 206;

    /**
     * 状态码(300)，指示所请求的资源对应于一组表示中的任何一种，每种表示都具有自己的特定位置。
     */
    public static final int SC_MULTIPLE_CHOICES = 300;

    /**
     * 状态代码(301)，指示资源已永久移动到一个新位置，并且未来的引用应该在其请求中使用一个新的URI。
     */
    public static final int SC_MOVED_PERMANENTLY = 301;

    /**
     * 状态码(302)，指示资源已临时移动到另一个位置，但未来的引用仍应使用原始URI访问资源。
     * 为了向后兼容，保留了这个定义。SC_FOUND现在是首选的定义。
     */
    public static final int SC_MOVED_TEMPORARILY = 302;

    /**
     * 状态码(302)，指示资源暂时驻留在一个不同的URI下。
     * 由于重定向有时可能会改变，所以客户机应该继续为将来的请求使用Request-URI (HTTP/1.1)来表示状态代码(302)，
     * 建议使用这个变量。
     */
    public static final int SC_FOUND = 302;

    /**
     * 状态码(303)，指示可以在不同的URI下找到对请求的响应。
     */
    public static final int SC_SEE_OTHER = 303;

    /**
     * 状态码(304)，指示条件GET操作发现资源可用且没有修改。
     */
    public static final int SC_NOT_MODIFIED = 304;

    /**
     * 状态码(305)，指示必须通过<code><em>Location</em></code>字段提供的代理访问所请求的资源<em> </em>。
     */
    public static final int SC_USE_PROXY = 305;

    /**
     * 状态码(307)，指示所请求的资源驻留
     * 临时使用不同的URI。临时URI <em>应该</em> be
     * 由响应中的<code><em>Location</em></code> field给出。
     */
    public static final int SC_TEMPORARY_REDIRECT = 307;

    /**
     * Status code (400) indicating the request sent by the client was
     * syntactically incorrect.
     */
    public static final int SC_BAD_REQUEST = 400;

    /**
     * 状态码(401)，指示请求需要HTTP身份验证。
     */
    public static final int SC_UNAUTHORIZED = 401;

    /**
     * 状态码(402)保留以备将来使用。
     */
    public static final int SC_PAYMENT_REQUIRED = 402;

    /**
     * 状态码(403)，指示服务器理解请求，但拒绝执行请求。
     */
    public static final int SC_FORBIDDEN = 403;

    /**
     * 状态码(404)，指示请求的资源不可用。
     */
    public static final int SC_NOT_FOUND = 404;

    /**
     * 状态码(405)表示不允许<code><em>Request-Line</em></code> >
     * 中指定的方法用于<code><em>Request-URI</em></code>所标识的资源。
     */
    public static final int SC_METHOD_NOT_ALLOWED = 405;

    /**
     * 状态代码(406)，指示由请求标识的资源只能生成响应实体，这些实体具有根据请求中发送的accept头不能接受的内容特征。
     */
    public static final int SC_NOT_ACCEPTABLE = 406;

    /**
     * 状态码(407)，指示客户机<em>必须</em>首先使用代理对自己进行身份验证。
     */
    public static final int SC_PROXY_AUTHENTICATION_REQUIRED = 407;

    /**
     * 状态码(408)，指示客户机在服务器准备等待的时间内没有生成请求。
     */
    public static final int SC_REQUEST_TIMEOUT = 408;

    /**
     * 状态码(409)，指示由于与资源的当前状态发生冲突，请求无法完成。
     */
    public static final int SC_CONFLICT = 409;

    /**
     * 状态码(410)，指示该资源在服务器上不再可用，且不知道转发地址。这个条件<em>SHOULD</em>被认为是永久性的。
     */
    public static final int SC_GONE = 410;

    /**
     * 状态码(411)，指示在没有定义的情况下无法处理请求 <code><em>Content-Length</em></code>.
     */
    public static final int SC_LENGTH_REQUIRED = 411;

    /**
     * 状态代码(412)，指示在服务器上测试时，一个或多个请求头字段中给出的先决条件被评估为false。
     */
    public static final int SC_PRECONDITION_FAILED = 412;

    /**
     * 状态码(413)，指示服务器拒绝处理请求，因为请求实体比服务器愿意或能够处理的大。
     */
    public static final int SC_REQUEST_ENTITY_TOO_LARGE = 413;

    /**
     * 状态码(414)表示服务器拒绝服务请求，因为<code><em> request - uri </em></code> >比服务器愿意解释的长。
     */
    public static final int SC_REQUEST_URI_TOO_LONG = 414;

    /**
     * 状态码(415)，指示服务器拒绝为请求提供服务，因为请求的实体的格式不受请求方法的请求资源的支持。
     */
    public static final int SC_UNSUPPORTED_MEDIA_TYPE = 415;

    /**
     * 状态码(416)，指示服务器不能提供所请求的字节范围。
     */
    public static final int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;

    /**
     * 状态码(417)，指示服务器不能满足Expect请求头中给出的期望。
     */
    public static final int SC_EXPECTATION_FAILED = 417;

    /**
     * 状态码(500)，指示HTTP服务器内部的一个错误，阻止它执行请求。
     */
    public static final int SC_INTERNAL_SERVER_ERROR = 500;

    /**
     * 状态代码(501)，指示HTTP服务器不支持满足请求所需的功能。
     */
    public static final int SC_NOT_IMPLEMENTED = 501;

    /**
     * 状态码(502)，指示HTTP服务器在充当代理或网关时从它所咨询的服务器接收到无效响应。
     */
    public static final int SC_BAD_GATEWAY = 502;

    /**
     * 状态码(503)，指示HTTP服务器临时过载，无法处理请求。
     */
    public static final int SC_SERVICE_UNAVAILABLE = 503;

    /**
     * 状态码(504)，表示服务器在充当网关或代理时没有从上游服务器接收到及时响应。
     */
    public static final int SC_GATEWAY_TIMEOUT = 504;

    /**
     * 状态码(505)，指示服务器不支持或拒绝支持请求消息中使用的HTTP协议版本。
     */
    public static final int SC_HTTP_VERSION_NOT_SUPPORTED = 505;


}
