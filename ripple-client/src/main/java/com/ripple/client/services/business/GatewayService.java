package com.ripple.client.services.business;

import com.le.bc.constants.Constants;
import com.ripple.client.services.SubmitService;
import com.ripple.core.coretypes.AccountID;
import com.ripple.core.coretypes.Amount;
import com.ripple.core.coretypes.hash.Hash128;
import com.ripple.core.coretypes.uint.UInt32;
import com.ripple.core.types.known.tx.signed.SignedTransaction;
import com.ripple.core.types.known.tx.txns.AccountSet;

/**
 * Document Start 
 * 网关服务
 * Document End 
 * Author: 扶摇直上 songwenpeng@le.com
 * Time: 2016年7月23日 上午12:08:43
 */
public class GatewayService {
	private SubmitService service = new SubmitService();
	/**
	 * Document Start 
	 * 设置指定账户的头像 使用Gravatar服务
	 * Gravatar是Globally Recognized Avatar的缩写,
	 * 是gravatar推出的一项服务，意为“全球通用头像”。
	 * 如果在Gravatar的服务器上放置了你自己的头像，
	 * 那么在任何支持Gravatar的blog或者留言本上留言时，只要提供你与这个头像关联的email地址，
	 * 就能够显示出你的Gravatar头像来。
	 * Document End 
	 * Author: 扶摇直上 songwenpeng@le.com
	 * Time: 2016年7月23日 上午2:44:14
	 * @param address 账户
	 * @param emailHash 头像对应的Email地址Hash
	 */
	public void setIcon(String address, String addressKey,String emailHash) {
//		// 创建AccountSet交易请求
		AccountSet accountSet = new AccountSet();
		// 设置地址
		accountSet.account(AccountID.fromAddress(address));
		accountSet.emailHash(Hash128.translate.fromString(emailHash));
		// 设置sequence
		accountSet.sequence(new UInt32(28));
		//设置费率
		accountSet.fee(Amount.fromString(Constants.RIPPLE_GATEWAY_FEE_AMOUNT));
		// 构造一个签名交易
		SignedTransaction signed = accountSet.sign(addressKey);
		
		this.service.submitOnlyMode(signed.tx_blob, false);
		
		
	}
}
