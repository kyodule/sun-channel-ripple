package com.ripple.client.responses;

import com.ripple.client.enums.RPCErr;
import com.ripple.client.requests.Request;
import com.ripple.core.coretypes.uint.UInt32;
import com.ripple.core.serialized.enums.EngineResult;
import org.json.JSONObject;

/**
 * Document Start 
 * 响应数据处理
 * Document End 
 * Author: 扶摇直上 songwenpeng@le.com
 * Time: 2016年7月17日 下午2:01:28
 */
public class Response {
    public JSONObject message;
    public Request request;
    public JSONObject result;
    public boolean succeeded;
    public String status;
    public RPCErr rpcerr;
    public String error;
    public String error_message;

    public Response(Request request, JSONObject message) {
        this.message = message;
        this.request = request;
        status = message.getString("status");
        succeeded = status.equals("success");
        if (succeeded) {
            this.result = message.getJSONObject("result");
            rpcerr = null;
        } else {
            try {
                error = message.getString("error");
                this.rpcerr = RPCErr.valueOf(error);
            } catch (Exception e) {
                rpcerr = RPCErr.unknownError;
            }
        }
    }

    public EngineResult engineResult() {
        return EngineResult.valueOf(result.getString("engine_result"));
    }

    public UInt32 getSubmitSequence() {
        return new UInt32(result.optJSONObject("tx_json").optInt("Sequence"));
    }
}
