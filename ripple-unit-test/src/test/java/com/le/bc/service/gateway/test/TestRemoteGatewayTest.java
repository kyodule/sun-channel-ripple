package com.le.bc.service.gateway.test;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.le.bc.commons.UIDUtil;
import com.le.bc.http.HttpPostUtil;

public class TestRemoteGatewayTest {
	
	@Test
	public void testRemoteCreateAccount() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://10.110.126.159:8088/gateway/createAccount";
		requestData.put("serviceID", "createAccount");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://10.110.126.159:8088/gateway/callback");
		requestData.put("UID", "001");
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
		String localGatewayURL = "http://10.110.126.159:8088/gateway/accountQuery";
		requestData.put("serviceID", "accountQuery");
		requestData.put("address", "rKgHhdGPKWgNDikMu6cdRYPG8ApfvF7fE5");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://10.75.168.48:8080/gateway/callback");
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
		String localGatewayURL = "http://10.110.126.159:8088/gateway/transfer";
		requestData.put("serviceID", "transfer");
		requestData.put("sourceAddress", "rUxC1rvD9ZHe5GQFaiL5iJ7oJXGTPUm17e");
		requestData.put("sourceKey", "snKjekGFwrUqzRT4ucF1gVmrxo71P");
		requestData.put("targetAddress", "rnwDpX4WBWnhYztJDu2599GqAxPpg5htjV");
		requestData.put("amount", "0.01");
		requestData.put("productCode", "1000000002");
		requestData.put("transferNO", "20161001053");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);
		requestData.put("notifyURL", "http://10.110.126.159:8088/gateway/callback");

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	@Test
	public void testAccountXRPQuery() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://10.110.126.159:8088/gateway/accountXRPQuery";
		requestData.put("serviceID", "accountXRPQuery");
		requestData.put("address", "rUhkoFgPEJXEN7yYxYqkTZA3HUCJm422Mj");
		requestData.put("notifyURL", "http://10.75.168.48:8080/gateway/callback");
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
		String localGatewayURL = "http://10.110.126.159:8088/service-gateway/activeAccount";
		requestData.put("serviceID", "activeAccount");
		requestData.put("address", "rpJB2tF2vjPxDiiGAbVuWYRxTwTDEyvAgg");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://10.110.126.159:8088/service-gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
	
	
	@Test
	public void testRemoteTransactionQuery() {
		Map<String, String> requestData = new HashMap<String, String>();
		String localGatewayURL = "http://10.110.126.159:8088/gateway/transactionQuery";//"http://10.110.126.159:8088/service-gateway/transactionQuery";
		requestData.put("serviceID", "transactionQuery");
		requestData.put("transactionID", "CB31953004E9DE7256F924690FBE4B8646398244B34B383EFA2BE3B2EF3BF1DC");//77902A3C3AA3F40ECF58F7BCF5401B7442DBDEFCF3CAD0F528A39A3009E39F47");
		requestData.put("notifyURL", "http://10.110.126.159:8088/gateway/callback");
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
		String localGatewayURL = "http://10.110.126.159:8088/gateway/setTrust";
		requestData.put("serviceID", "setTrust");
		requestData.put("sourceAddress", "rnwDpX4WBWnhYztJDu2599GqAxPpg5htjV");
		requestData.put("sourceKey", "shL19fFkDBeTSNa4ccygigrWM1L9z");
		requestData.put("productCode", "1000000002");
		requestData.put("notifyURL", "http://10.110.126.159:8088/service-gateway/callback");
		String req = UIDUtil.genRequestSequenceNO();
		System.out.println(req);
		requestData.put("requestID", req);

		String result = HttpPostUtil.callHttpPOST(localGatewayURL, requestData);

		assertTrue(result.length() > 1);
		System.out.println(result);
	}
}
