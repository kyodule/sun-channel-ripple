package com.ripple.client.services;

import org.json.JSONObject;

import com.ripple.client.enums.Command;
import com.ripple.client.requests.Request;
import com.ripple.client.responses.Response;

/**
 * Document Start 
 * 获取指定账户的信息
 * account_info
 * Document End 
 * songwenpeng@letv.com
 * 乐视控股（北京）有限公司 
 * 2016年7月5日 下午2:37:09
 */
public class AccountObjectsService extends Service {

	/**
	 * Document Start 
	 * 获取指定账户的信息
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月21日 下午10:37:27
	 * @param accountAddress
	 */
	public void getAccountObjects(String accountAddress) {
		Request request = this.getClient().newRequest(Command.account_objects);
		request.json("account", accountAddress);
		logger.info(">>getAccountObjects>>请求开始,请求数据为" + request.json().toString());
		//调用成功 回调
		request.once(Request.OnSuccess.class, new Request.OnSuccess() {
			@Override
			public void called(Response response) {
				result = response.message;
				logger.info(">>getAccountObjects>>本次请求结束,响应数据为" + result.toString());
				doSuccess(result);
			}
		});
		request.request();
	}

	/**
	 * Document Start 
	 * 返回的数据格式如下所示
//	{
//	    "result": {
//	        "ledger_current_index": 22764380,
//	        "validated": false,
//	        "account_objects": [
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22709074,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "300000",
//	                    "issuer": "rraBDW7zYFUUcJoVyC6Dk9jZYQxf6V1Mkj"
//	                },
//	                "index": "5EB49286361998D5CF39AEE1C627D71C4F2ABAB5C488D69CE5DDA1E9467D1FA9",
//	                "PreviousTxnID": "C80044E184695BB2A3CCD81C1BE8FB9F84840A1D642CA0B53EDB824CAFAD054F",
//	                "Flags": 196608,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "43028.64364364365",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "300000",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22708087,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                },
//	                "index": "A8296949B9F23C8F453E7A3794B8D0B9FAD97363FA2ACE9A176A6E69C9AEDE15",
//	                "PreviousTxnID": "32C06BE7726CEFA607DFF82299304233EE3974885FE8D931B1BD05B637C88C38",
//	                "Flags": 131072,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "-20.74",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "10000",
//	                    "issuer": "r48rFNfaPJX7Rau7bXMyK1gc3zCMYe4qSe"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22606921,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                },
//	                "index": "AC96ACEE4DB70D84B942AE1BA9CEC426F0893E395D4A7CB3D2F3ED0C20D00F63",
//	                "PreviousTxnID": "5D308369F06DD5049E6FC5895AE4CD89A01EB02EDBF6C000786231089563C594",
//	                "Flags": 3276800,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "-1.96996996996997",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rP1vykjP7o1WBcXjdgiKyyLi2PT4cfS3FF"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22568651,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rpJB2tF2vjPxDiiGAbVuWYRxTwTDEyvAgg"
//	                },
//	                "index": "397A1C5F7EFE00AD2C7017CE560CE3EE7C513E8C44F2A2A7B6BFA4DC7BEC9EB7",
//	                "PreviousTxnID": "EF2408AEE0B572E30071645ACCDCCB8E88EBEB8946F4D86DCD9F8CDB2A93014F",
//	                "Flags": 3276800,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22565685,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rHa3trZg2MzGTkGA4PLmsTPo9azHUGbhUV"
//	                },
//	                "index": "0BFAA390A81C772E8AE6036368C5024E20FBCF860B97D8D92CD4F7AB7B5ECD44",
//	                "PreviousTxnID": "F7BD43E955B39AC516BC15AE63C200A4174D7E1F925DC5C8F0A97EF178615C5C",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22578154,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rKhytnCTzDvd9cfoqGZ3BdHKpjU65oSZaQ"
//	                },
//	                "index": "089783F8E5FD4D00C53B0CCF5D2178192AE16347DBC59B2C3B1FAB2783F2849E",
//	                "PreviousTxnID": "4BF1BDF009C803CCDBA3588C53ECBAE8A624E34AEFAD695E0DBA6544236744B7",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "851",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22656117,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "r9YsnpXzYhAbuX8jBJoCeaMqhRShcScGSq"
//	                },
//	                "index": "38FF385868BA1AA527F6FFED1DAE620EB8A8E952E9DD06077E02171A2BD36EF6",
//	                "PreviousTxnID": "006E82040F9379338F664F912E627AEF7BCFF3998055329E325E6CC61D21ED80",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "1061",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22571198,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "CNY",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                },
//	                "index": "A831B57786045DECD842F42CC3836F5037F8EAB212F03CC2338B299EB2E758FD",
//	                "PreviousTxnID": "735CFE203A49D781C84A34E4F66D9216AC3710CFDAA789D1D32A6ABA894C8844",
//	                "Flags": 131072,
//	                "Balance": {
//	                    "currency": "CNY",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "CNY",
//	                    "value": "10000",
//	                    "issuer": "r48rFNfaPJX7Rau7bXMyK1gc3zCMYe4qSe"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22708087,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "10000",
//	                    "issuer": "rTaVGCHrhBwQyLCM9UjzFDetnCgfGDESN"
//	                },
//	                "index": "C394EC46A68EA89DCE5D47AC9CD03D2E9A34EFA966E8E3D0331D4781F12B84F5",
//	                "PreviousTxnID": "32C06BE7726CEFA607DFF82299304233EE3974885FE8D931B1BD05B637C88C38",
//	                "Flags": 65536,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "1067.05005005005",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22576470,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "CNY",
//	                    "value": "1000",
//	                    "issuer": "rTaVGCHrhBwQyLCM9UjzFDetnCgfGDESN"
//	                },
//	                "index": "23BDC4B0702AC9977276BFED4A9BE6B6FD5C41714556CA05C1228BFF1A111FDB",
//	                "PreviousTxnID": "0C64CCCA3EFF8C4CAEF3B41B260C80EED79CF52298128AE7183FD467FC30FB75",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "CNY",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "CNY",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22583545,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                },
//	                "index": "7C7EF6ACCB66ED421C3AE2BABFB966DF153FC5DB5883473DFFACCE0A4EDF42A3",
//	                "PreviousTxnID": "C0FA480F0A77912AA956AF93AAE89047BAA9FE247DE480BA52122B2AF6BA487D",
//	                "Flags": 131072,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "-100",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "200000",
//	                    "issuer": "r48rFNfaPJX7Rau7bXMyK1gc3zCMYe4qSe"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22584880,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "300000000",
//	                    "issuer": "rraBDW7zYFUUcJoVyC6Dk9jZYQxf6V1Mkj"
//	                },
//	                "index": "F9007A8B8F1A2D3E91628BFD16C3084195E907669D99F7AD3155902D0D7E2A4F",
//	                "PreviousTxnID": "6109B380F753DCA624A577A20DD35834D3EC11D45B5C7ED69C4D64FEA106A3BC",
//	                "Flags": 196608,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "10000000",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "300000000",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22585496,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "CNY",
//	                    "value": "200000",
//	                    "issuer": "rraBDW7zYFUUcJoVyC6Dk9jZYQxf6V1Mkj"
//	                },
//	                "index": "E325ECE50BAA7573EBF83709B5D737704CDEF6B16409245A80053821C0E6F66A",
//	                "PreviousTxnID": "E0163D69539B02796B9A8016E9D783FA612543919EF2C146452C9C5D0F873B79",
//	                "Flags": 196608,
//	                "Balance": {
//	                    "currency": "CNY",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "CNY",
//	                    "value": "50000",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22579206,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "USD",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                },
//	                "index": "E3E8892D960304B3C84B84E22AE66E464B9DC2F72BDD443A683481324CB0DA63",
//	                "PreviousTxnID": "FBC89857272D93872A3B94FC95F03A3B58B973F0B71130DF3A2080E864EE43D0",
//	                "Flags": 131072,
//	                "Balance": {
//	                    "currency": "USD",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "USD",
//	                    "value": "1",
//	                    "issuer": "r48rFNfaPJX7Rau7bXMyK1gc3zCMYe4qSe"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22680017,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "300000000",
//	                    "issuer": "rTaVGCHrhBwQyLCM9UjzFDetnCgfGDESN"
//	                },
//	                "index": "A62440EBC8E07560DFFF5DE9E513DCA89A5BD585C2938ECDA0824ACF5515459C",
//	                "PreviousTxnID": "1F8C4F051D5072558AE8BDDE8D5434EE8CFCAB10B2B112E6F6E4909D89CDDEC9",
//	                "Flags": 65536,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "189999792.9999999",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22753775,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "200000",
//	                    "issuer": "rJrd583hyiL8kCk8dKgN77Z5ZiTTjU1Upz"
//	                },
//	                "index": "CDAD65DF3DEB5980D9F66061214D27954E258F2AE18DD61CB2D8352CA7C98EF3",
//	                "PreviousTxnID": "30BA379521E10FF18DF625457D37CFFC02017CB5C07C4DA6F0A0679E667B32D5",
//	                "Flags": 65536,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "194067.7144544544",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22753775,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "100000000",
//	                    "issuer": "rJrd583hyiL8kCk8dKgN77Z5ZiTTjU1Upz"
//	                },
//	                "index": "60BD4248A6646655A4D9D6090D53B48972336DB0692225BF55A7CA3D6D328D95",
//	                "PreviousTxnID": "30BA379521E10FF18DF625457D37CFFC02017CB5C07C4DA6F0A0679E667B32D5",
//	                "Flags": 65536,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "96863833",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22586291,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "rD9Y7EvgByV1vHBdhWW9hJUqdaGw2RHPyk"
//	                },
//	                "index": "3E268BFA2764553459C909DE983A53EFF5439462424237998B159B35151C735B",
//	                "PreviousTxnID": "DCF3614CBED3AE3C80B304BC996025BB498B252226C0F26097552564CE443D40",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "121231",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22586043,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "rn9DJ3dMMa8mYp8cFLE8a7hJNSVMaYRvqu"
//	                },
//	                "index": "73F502536807FD5350932963EF0797299732864754F4E1A96B95D545CC4FE72F",
//	                "PreviousTxnID": "A3512398A9983D69C530E2AA5755A16A7C117FA069316167E748EAD27EC6F5CA",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "570",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22583810,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rn9DJ3dMMa8mYp8cFLE8a7hJNSVMaYRvqu"
//	                },
//	                "index": "D6EFB5EC573C76434983A964CF65B89D82AAD004E3D80A2639306B74BEC22510",
//	                "PreviousTxnID": "22A6265E13C70F6EBF5E90255C219F703530CD6791E7525350489595B8BE6B73",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "998.998998998999",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22753775,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "rarc5z5BipNG82uNgt6cmGAcoPgMThD1mT"
//	                },
//	                "index": "2443432D03FA6F0C71DD6A501353A330656DBC6C337E62C99FF30D8921476182",
//	                "PreviousTxnID": "30BA379521E10FF18DF625457D37CFFC02017CB5C07C4DA6F0A0679E667B32D5",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "894328.998998999",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22707299,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "rrpGVhxPNy56Wryi2iLLQm1E7jhduDdxSg"
//	                },
//	                "index": "78E10C8925A3B5317F7426B6FE956DB7B449635F032A704F606634E077BFE932",
//	                "PreviousTxnID": "DC0276F641224233CAE53C4EC620F680FA0B3E65D45EC129B2B63717123BF894",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "1010000",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22709074,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rrpGVhxPNy56Wryi2iLLQm1E7jhduDdxSg"
//	                },
//	                "index": "6F75D7E5C955F45930749CF8759C3014D1D408AB09A71A3566E72B952696069D",
//	                "PreviousTxnID": "C80044E184695BB2A3CCD81C1BE8FB9F84840A1D642CA0B53EDB824CAFAD054F",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "1012.98998998999",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22584880,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                },
//	                "index": "19747DF8F89130EE4EFDDEAD8C1D19E02FF54BFF1FAEB93640A0501FD4152322",
//	                "PreviousTxnID": "6109B380F753DCA624A577A20DD35834D3EC11D45B5C7ED69C4D64FEA106A3BC",
//	                "Flags": 2228224,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "-1100000",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "r4kxoBDRatgQxF1FyzQZQUtvS3SWj2B6hY"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22680017,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "rHgwoNhXPqFVQohPCzqosuJDxMkQAV6b3i"
//	                },
//	                "index": "BDEEB311D240FBB1E23A588763886C49E49CF7848A150B1EBC3FFFC523B9DBBD",
//	                "PreviousTxnID": "1F8C4F051D5072558AE8BDDE8D5434EE8CFCAB10B2B112E6F6E4909D89CDDEC9",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "100103.0010010011",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22683780,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rHgwoNhXPqFVQohPCzqosuJDxMkQAV6b3i"
//	                },
//	                "index": "60636359D265F0FDC24C3D7ACD25AC8669DAB069542A581FAF6AC7F95DBAFC9A",
//	                "PreviousTxnID": "20CF2E51FAA3D6491907FE95787267E460A73019C37D524D833E5EB87604A225",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "898.8958958958959",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22583427,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rwy5mhBbmrv8xiZwbHCu8SPnxR3r7znjq5"
//	                },
//	                "index": "B00B3A7F1A7B84BDD862A9BA7D30F4F95B1CCE02F77D6F873BDC29AFAAF07EEF",
//	                "PreviousTxnID": "1081B6D373BD60730953CF9B20DB32FC69894E0803C04DA3C763F7FEA5EC8685",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22583438,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "rwy5mhBbmrv8xiZwbHCu8SPnxR3r7znjq5"
//	                },
//	                "index": "486CD758E66CA70EA213E3E49B8094C9E5380E1811BDB1FBDFB42C250C2FB95D",
//	                "PreviousTxnID": "2F10BDC489F717085619C6D98E49DF61886BD6EE929B7693DEDC4819B2410517",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22584315,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "rESqf9vjqCEMe3pw8RFdAoPSUwuTUqFqES"
//	                },
//	                "index": "E91FE86D27CB3B7F06C8C41DD2627898E41EE94314AE1CEA332860B66F71778D",
//	                "PreviousTxnID": "D1AFA14856A8D16F7BA7A80102315C1E1FAAB4B6CD038510BB4F81F0A405FD85",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "11",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22583779,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "CNY",
//	                    "value": "20000",
//	                    "issuer": "rJrd583hyiL8kCk8dKgN77Z5ZiTTjU1Upz"
//	                },
//	                "index": "64537C202AC9526FDF85E45E8F062D7BEE6AD6C4AA1B44460C1770F609AE1555",
//	                "PreviousTxnID": "A5D3C748E0ABDDE04A57BCA0796C378446A31A4EEEB5A6AAA159375E7489BCF5",
//	                "Flags": 65536,
//	                "Balance": {
//	                    "currency": "CNY",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "CNY",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22753775,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rarc5z5BipNG82uNgt6cmGAcoPgMThD1mT"
//	                },
//	                "index": "13DB5A54883B4E1706CD4158BDF2212CEF20C37945D110BC41A6415CB48332DB",
//	                "PreviousTxnID": "30BA379521E10FF18DF625457D37CFFC02017CB5C07C4DA6F0A0679E667B32D5",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "103.996996996997",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22584438,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "rGXTKkF9AEfVr2v1dvsC56NxTMynuN93bX"
//	                },
//	                "index": "9F6E5F0E936EF56EA9D78E5B9FF11930EB8103C861EDCF148F74C15E79454865",
//	                "PreviousTxnID": "CF84D66716D9CB39B0441D7C10D38B4322661CFD75B9C2CB532CF305857F3F0B",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000001",
//	                "PreviousTxnLgrSeq": 22585705,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                },
//	                "index": "02E9B79817D90CA51C69BA78704A07C496796E8895F3E0DCC33E04A7E18A7F91",
//	                "PreviousTxnID": "FF99F59DCD28FE2DD384A44D8FBA43C3902ECD496F61AF6E7F083313C279A33B",
//	                "Flags": 2228224,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "r4W1j1x3T71tCexng9432MXcw5qL5jyLrk"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000001",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22586013,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rGXTKkF9AEfVr2v1dvsC56NxTMynuN93bX"
//	                },
//	                "index": "FA2981CACC79B35D0B637EDFC9BE75CB9A049173C7216AF8CC1FA40E78BDF2AC",
//	                "PreviousTxnID": "1C5300C093A33F09B06570814637F6E083EC4156E20B5192F235DC668BFBB451",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000000",
//	                "LowNode": "0000000000000001",
//	                "PreviousTxnLgrSeq": 22586105,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                },
//	                "index": "98FDA8C790AF6B135A5C95B20C9E6F0A443341A4C06C0E1DB95FE7E7355A6DCD",
//	                "PreviousTxnID": "A417AB598D86EC668C009F55412604F9F93D90EF997F436CFFED6564DEFD6BFC",
//	                "Flags": 2228224,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "r4W1j1x3T71tCexng9432MXcw5qL5jyLrk"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000001",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22606989,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rD7ewsQgi3JH6HYGYEsVwVveSvD94UqrvA"
//	                },
//	                "index": "089A4366E58C3FC3AE9B6167B54FD22850D48BFC68396274F45A218DBF3E197F",
//	                "PreviousTxnID": "783D22F8536082F6F021A44CC142B9E57F992A4D9D175398A5C81FA49DD5C04F",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "50",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000001",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22608913,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "MMB",
//	                    "value": "1000000000",
//	                    "issuer": "rnSzd4WVPY1vYDPC8M49Bk9LNqVygUyMrU"
//	                },
//	                "index": "6545828F904911A32FD706DBBA0BF8170A585E03B2177BE122B7DDC77C263988",
//	                "PreviousTxnID": "7C40045E7C18FBC72AE63641B101689BA672D24FEEAD36C257AB0FAF465F2311",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "MMB",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            },
//	            {
//	                "HighNode": "0000000000000001",
//	                "LowNode": "0000000000000000",
//	                "PreviousTxnLgrSeq": 22608959,
//	                "LedgerEntryType": "RippleState",
//	                "LowLimit": {
//	                    "currency": "NBI",
//	                    "value": "1000000000",
//	                    "issuer": "rnSzd4WVPY1vYDPC8M49Bk9LNqVygUyMrU"
//	                },
//	                "index": "674ED8149322045C8E87AC74DBD67C8B2411378CAFBA60A25F08E9BBC8544C57",
//	                "PreviousTxnID": "60021432B1033ABE2112C1F9DAB0D2F82AAA06736F2DD6A22DB79EF4FE158B96",
//	                "Flags": 1114112,
//	                "Balance": {
//	                    "currency": "NBI",
//	                    "value": "1",
//	                    "issuer": "rrrrrrrrrrrrrrrrrrrrBZbvji"
//	                },
//	                "HighLimit": {
//	                    "currency": "NBI",
//	                    "value": "0",
//	                    "issuer": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	                }
//	            }
//	        ],
//	        "account": "rLDykZiJ2Zm6UB937jfZPiRnnh85X7CBCS"
//	    },
//	    "id": 0,
//	    "type": "response",
//	    "status": "success"
//	}
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月17日 下午1:26:25
	 * validated : If true, this data comes from a validated ledger.
	 * @see com.ripple.client.services.Service#doSuccess(org.json.JSONObject)
	 */
	@Override
	public void doSuccess(JSONObject result) {
		// TODO Auto-generated method stub
		super.doSuccess(result);
	}

}
