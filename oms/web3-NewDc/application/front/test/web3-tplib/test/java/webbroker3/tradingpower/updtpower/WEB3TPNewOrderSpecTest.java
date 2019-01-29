head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPNewOrderSpecTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現注文内容
Author Name      : Daiwa Institute of Research
Revesion History : 孟亞南
*/
package webbroker3.tradingpower.updtpower;

import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TraderImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.ordersubmitter.io.AioNewOrderSpec;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPNewOrderSpecTest extends TestBaseForMock
{
	public WEB3TPNewOrderSpecTest(String name)
	{
		super(name);
	}
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPNewOrderSpecTest.class);
    
    /**
     * 1020：振替注文（預り金からオリックスクレジット）
     *
     */
	public void test_toWEB3AioTPNewOrderSpec_0001()
	{
		final String STR_METHOD_NAME = "test_toWEB3AioTPNewOrderSpec_0001()";
		log.entering(STR_METHOD_NAME);

	    Method method;
		try
		{
			TestDBUtility.deleteAll(TraderParams.TYPE);
			TraderParams l_trader = TestDBUtility.getTraderRow();
			l_trader.setTraderId(33L);
			l_trader.setLoginId(33L);
			TestDBUtility.insertWithDel(l_trader);
			
			TestDBUtility.deleteAll(SubAccountParams.TYPE);
			SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
	        l_subAccountParams.setAccountId(12L);
	        l_subAccountParams.setSubAccountId(28L);
	        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
			TestDBUtility.insertWithDel(l_subAccountParams);
			
			TestDBUtility.deleteAll(MainAccountParams.TYPE);
			MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
			l_mainAccountParams.setAccountId(12L);
			TestDBUtility.insertWithDel(l_mainAccountParams);
			
			TestDBUtility.deleteAll(ProductParams.TYPE);
			ProductParams l_productParams = TestDBUtility.getProductRow();
			l_productParams.setProductId(9L);
			TestDBUtility.insertWithDel(l_productParams);
			
			TestDBUtility.deleteAll(InstitutionParams.TYPE);
			InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
			l_institutionParams.setInstitutionId(33L);
			TestDBUtility.insertWithDel(l_institutionParams);
			
			TraderImpl trader = new TraderImpl(33L,false);
			
			WEB3TPNewOrderSpec l_spec = new WEB3TPNewOrderSpec();
			WEB3AioCashTransUpdateInterceptor l_interceptor = new WEB3AioCashTransUpdateInterceptor();
			AioNewOrderSpec l_aioNewOrderSpec = new AioNewOrderSpec(trader,
					OrderTypeEnum.TO_ORIX_CREDIT,
					AssetTransferTypeEnum.CASH_OUT,
					9L,
					8D,
					"12",
					new Date());
			SubAccountImpl l_subAccount = new SubAccountImpl(12L,28L);
			method = WEB3TPNewOrderSpec.class.getDeclaredMethod("toWEB3AioTPNewOrderSpec", new Class[]{SubAccount.class, Object.class, Object.class});
			method.setAccessible(true);
			l_spec = (WEB3TPNewOrderSpec)method.invoke(l_spec, new Object[]{l_subAccount, l_interceptor, l_aioNewOrderSpec});
			assertEquals("28", "" + l_spec.getSubAccountId());
		}
		catch (Exception e)
		{
		    fail();
		}
	    
		log.exiting(STR_METHOD_NAME);
	}
}
@
