package com.le.bc.service.gateway.test;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.le.bc.commons.UIDUtil;
import com.le.bc.http.HttpPostUtil;

public class LocalGatewayTest {
	
	@Test
	public void testActiveAccount() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://localhost:8080/gateway/activeAccount";
		requestData.put("serviceID", "activeAccount");
		requestData.put("address", "rpJB2tF2vjPxDiiGAbVuWYRxTwTDEyvAgg");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://localhost:8080/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);
		
		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	@Test
	public void testSetTrust() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://localhost:8080/gateway/setTrust";
		requestData.put("serviceID", "setTrust");
		requestData.put("sourceAddress", "rnwDpX4WBWnhYztJDu2599GqAxPpg5htjV");
		requestData.put("sourceKey", "shL19fFkDBeTSNa4ccygigrWM1L9z");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://localhost:8080/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);
		
		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	@Test
	public void testTransfer() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://localhost:8080/gateway/transfer";
		requestData.put("serviceID", "transfer");
		requestData.put("sourceAddress", "rnwDpX4WBWnhYztJDu2599GqAxPpg5htjV");
		requestData.put("sourceKey", "shL19fFkDBeTSNa4ccygigrWM1L9z");
		requestData.put("targetAddress", "rraBDW7zYFUUcJoVyC6Dk9jZYQxf6V1Mkj");
		requestData.put("amount", "11");
		requestData.put("productCode", "1000000002");
		requestData.put("transferNO", "1");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);
		requestData.put("notifyURL", "http://localhost:8080/gateway/callback");

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	@Test
	public void testTransactionQuery() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://localhost:8080/gateway/V1";
		requestData.put("serviceID", "transactionQuery");
		requestData.put("transactionID", "21138B191F528892263D57D470F7FE7B7B5C91F2E5CCB804C74AD6F664C29B4F");
		requestData.put("notifyURL", "http://localhost:8080/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}

	@Test
	public void testLocalAccountQuery() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://localhost:8080/gateway/V1";
		requestData.put("serviceID", "accountQuery");
		requestData.put("address", "rUxC1rvD9ZHe5GQFaiL5iJ7oJXGTPUm17e");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://localhost:8080/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	
	
	@Test
	public void testV1() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://localhost:8080/gateway/V1";
		requestData.put("serviceID", "accountQuery");
		requestData.put("address", "rUxC1rvD9ZHe5GQFaiL5iJ7oJXGTPUm17e");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://localhost:8080/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}

	@Test
	public void testAccountXRPQuery() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://localhost:8080/gateway/accountXRPQuery";
		requestData.put("serviceID", "accountXRPQuery");
		requestData.put("address", "rpJB2tF2vjPxDiiGAbVuWYRxTwTDEyvAgg");
		requestData.put("notifyURL", "http://localhost:8080/gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}


	@Test
	public void testCreateAccount() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://localhost:8080/gateway/V1";
		requestData.put("serviceID", "createAccount");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://localhost:8080/gateway/callback");
		requestData.put("UID", "001");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
}
