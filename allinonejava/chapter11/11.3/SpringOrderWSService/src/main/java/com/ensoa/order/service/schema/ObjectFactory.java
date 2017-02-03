//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.7 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2014.09.01 시간 11:58:28 AM KST 
//


package com.ensoa.order.service.schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ensoa.order.service.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetOrderRequest_QNAME = new QName("http://service.order.ensoa.com/schema", "getOrderRequest");
    private final static QName _GetOrderResponse_QNAME = new QName("http://service.order.ensoa.com/schema", "getOrderResponse");
    private final static QName _GetOrdersResponse_QNAME = new QName("http://service.order.ensoa.com/schema", "getOrdersResponse");
    private final static QName _PurchaseOrderRequest_QNAME = new QName("http://service.order.ensoa.com/schema", "purchaseOrderRequest");
    private final static QName _GetOrdersRequest_QNAME = new QName("http://service.order.ensoa.com/schema", "getOrdersRequest");
    private final static QName _PurchaseOrderResponse_QNAME = new QName("http://service.order.ensoa.com/schema", "purchaseOrderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ensoa.order.service.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetOrdersResponse }
     * 
     */
    public GetOrdersResponse createGetOrdersResponse() {
        return new GetOrdersResponse();
    }

    /**
     * Create an instance of {@link PurchaseOrderRequest }
     * 
     */
    public PurchaseOrderRequest createPurchaseOrderRequest() {
        return new PurchaseOrderRequest();
    }

    /**
     * Create an instance of {@link GetOrdersRequest }
     * 
     */
    public GetOrdersRequest createGetOrdersRequest() {
        return new GetOrdersRequest();
    }

    /**
     * Create an instance of {@link PurchaseOrderResponse }
     * 
     */
    public PurchaseOrderResponse createPurchaseOrderResponse() {
        return new PurchaseOrderResponse();
    }

    /**
     * Create an instance of {@link GetOrderRequest }
     * 
     */
    public GetOrderRequest createGetOrderRequest() {
        return new GetOrderRequest();
    }

    /**
     * Create an instance of {@link GetOrderResponse }
     * 
     */
    public GetOrderResponse createGetOrderResponse() {
        return new GetOrderResponse();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link OrderItem }
     * 
     */
    public OrderItem createOrderItem() {
        return new OrderItem();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.order.ensoa.com/schema", name = "getOrderRequest")
    public JAXBElement<GetOrderRequest> createGetOrderRequest(GetOrderRequest value) {
        return new JAXBElement<GetOrderRequest>(_GetOrderRequest_QNAME, GetOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.order.ensoa.com/schema", name = "getOrderResponse")
    public JAXBElement<GetOrderResponse> createGetOrderResponse(GetOrderResponse value) {
        return new JAXBElement<GetOrderResponse>(_GetOrderResponse_QNAME, GetOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.order.ensoa.com/schema", name = "getOrdersResponse")
    public JAXBElement<GetOrdersResponse> createGetOrdersResponse(GetOrdersResponse value) {
        return new JAXBElement<GetOrdersResponse>(_GetOrdersResponse_QNAME, GetOrdersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PurchaseOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.order.ensoa.com/schema", name = "purchaseOrderRequest")
    public JAXBElement<PurchaseOrderRequest> createPurchaseOrderRequest(PurchaseOrderRequest value) {
        return new JAXBElement<PurchaseOrderRequest>(_PurchaseOrderRequest_QNAME, PurchaseOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.order.ensoa.com/schema", name = "getOrdersRequest")
    public JAXBElement<GetOrdersRequest> createGetOrdersRequest(GetOrdersRequest value) {
        return new JAXBElement<GetOrdersRequest>(_GetOrdersRequest_QNAME, GetOrdersRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PurchaseOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.order.ensoa.com/schema", name = "purchaseOrderResponse")
    public JAXBElement<PurchaseOrderResponse> createPurchaseOrderResponse(PurchaseOrderResponse value) {
        return new JAXBElement<PurchaseOrderResponse>(_PurchaseOrderResponse_QNAME, PurchaseOrderResponse.class, null, value);
    }

}
