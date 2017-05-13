package com.le.bc.service.gateway.test;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.le.bc.commons.UIDUtil;
import com.le.bc.http.HttpPostUtil;

public class ProductRemoteGatewayTest {
	
	@Test
	public void testRemoteCreateAccount() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://api.bc.le.com/gateway/V1";//http://10.110.176.65:8080/gateway/V1";
		requestData.put("serviceID", "createAccount");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://api.bc.le.com/gateway/callback");
		requestData.put("UID", "001");
		
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	@Test
	public void testRemoteActiveAccount() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://api.bc.le.com/gateway/V1";
		requestData.put("serviceID", "activeAccount");
		requestData.put("address", "rwjGeRj1r6xmhzmvwNFdtqKgrjbEp7RhiV");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://api.bc.le.com/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	@Test
	public void testRemoteSetTrust() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://api.bc.le.com/gateway/V1";
		requestData.put("serviceID", "setTrust");
		requestData.put("sourceAddress", "rwjGeRj1r6xmhzmvwNFdtqKgrjbEp7RhiV");
		requestData.put("sourceKey", "snCihkRmQ7oiciHuqpm7nmdztUQ4M");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://api.bc.le.com/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	@Test
	public void testRemoteAccountXRPQuery() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://api.bc.le.com/gateway/V1";//"http://10.110.176.65:8080/gateway/V1";
		requestData.put("serviceID", "accountXRPQuery");
		requestData.put("address", "rwjGeRj1r6xmhzmvwNFdtqKgrjbEp7RhiV");
//		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://api.bc.le.com/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	
	
	@Test
	public void testRemoteTransfer() {
		Map<String, String> requestData = new HashMap<String, String>();
		//
		String localGatewayURL = "http://api.bc.le.com/gateway/V1";
		requestData.put("serviceID", "transfer");
		requestData.put("sourceAddress", "rUxC1rvD9ZHe5GQFaiL5iJ7oJXGTPUm17e");
		requestData.put("sourceKey", "snKjekGFwrUqzRT4ucF1gVmrxo71P");
		requestData.put("targetAddress", "rwjGeRj1r6xmhzmvwNFdtqKgrjbEp7RhiV");
		requestData.put("amount", "0.01");
		requestData.put("productCode", "1000000002");
		requestData.put("transferNO", "20161001053");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);
		//
		requestData.put("notifyURL", "http://api.bc.le.com/gateway/callback");

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	
	
	
	
	
	@Test
	public void testRemoteTransactionQuery() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://api.bc.le.com/gateway/V1";//"http://10.110.126.159:8080/gateway/transactionQuery";
		requestData.put("serviceID", "transactionQuery");
		requestData.put("transactionID", "3041C26751B88283858192A7918B15992E0309BA5752E9EC08A772C0D165A4FA");//77902A3C3AA3F40ECF58F7BCF5401B7442DBDEFCF3CAD0F528A39A3009E39F47");
		requestData.put("notifyURL", "http://api.bc.le.com/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	@Test
	public void testRemoteAccountQuery() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://api.bc.le.com/gateway/V1";//"http://10.110.176.65:8080/gateway/V1";
		requestData.put("serviceID", "accountQuery");
		requestData.put("address", "rwjGeRj1r6xmhzmvwNFdtqKgrjbEp7RhiV");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://api.bc.le.com/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	
}
