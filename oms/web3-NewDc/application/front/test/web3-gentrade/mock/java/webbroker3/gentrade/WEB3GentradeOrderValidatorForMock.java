head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.17.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeOrderValidatorForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeOrderValidatorForMock extends WEB3GentradeOrderValidator
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3GentradeOrderValidatorForMock.class);

    public OrderValidationResult validateSubAccountForTrading(SubAccount l_subAccount)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                new Object[]{l_subAccount});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateSubAccountForTrading",
            new Class[] {SubAccount.class}))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeOrderValidator.validateSubAccountForTrading(SubAccount)");
            return (OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class}).asObject();
        }
        return super.validateSubAccountForTrading(l_subAccount);
    }

    public OrderValidationResult validateAccountForTrading(WEB3GentradeMainAccount l_mainAccount)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateAccountForTrading",
                new Class[] {WEB3GentradeMainAccount.class},
                new Object[]{l_mainAccount});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class}))
        {
            log.debug(
                "webbroker3.gentrade.WEB3GentradeOrderValidator.validateSubAccountTrading(WEB3GentradeMainAccount)");
            return (OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateAccountForTrading",
                new Class[] {WEB3GentradeMainAccount.class}).asObject();
        }
        return super.validateAccountForTrading(l_mainAccount);
    }

    /**
     * (validate取引可能顧客(Mock)) <BR>
     *（validateAccountForTrading）<BR>
     * @@param l_genMainAccount - (顧客) <BR>
     * @@param l_tsBizDate - (発注日)<BR>
     * @@return 
     */
    public OrderValidationResult validateAccountForTrading(
        WEB3GentradeMainAccount l_genMainAccount,
        Timestamp l_tsBizDate)
    {
        final String STR_METHOD_NAME = 
            "validateSubAccountForTrading(WEB3GentradeMainAccount, Timestamp)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
            new Object[]{l_genMainAccount, l_tsBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateAccountForTrading",
            new Class[] {WEB3GentradeMainAccount.class, Timestamp.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //1）MockFor --〉 asWEB3BaseException

            //2)MockFor --〉 asObject
            return(OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateAccountForTrading",
                new Class[] {WEB3GentradeMainAccount.class, Timestamp.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.validateAccountForTrading(l_genMainAccount, l_tsBizDate);
    }
    
    public OrderValidationResult validateTradingPassword(WEB3GentradeMainAccount l_proxyInputPerson, String l_strTradingPassword)
	{

		//1）參數驗證
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
				"validateTradingPassword", new Class[] { WEB3GentradeMainAccount.class, String.class }, new Object[] {
						l_proxyInputPerson, l_strTradingPassword });

		if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeOrderValidator",
				"validateTradingPassword", new Class[] { WEB3GentradeMainAccount.class,  String.class }))
		{
			log.debug("webbroker3.gentrade.WEB3GentradeOrderValidatorForMock.validateTradingPassword(Trader, String)");

			return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
					new Class[] { WEB3GentradeMainAccount.class, String.class }).asObject();
		}
		return super.validateTradingPassword(l_proxyInputPerson, l_strTradingPassword);
	}
    
    public OrderValidationResult validateTradingPassword(Trader l_proxyInputPerson, SubAccount l_subAccount,
            String l_strTradingPassword)
    {

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword", new Class[] { Trader.class, SubAccount.class, String.class }, new Object[] {
                        l_proxyInputPerson, l_subAccount, l_strTradingPassword });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword", new Class[] { Trader.class, SubAccount.class, String.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeOrderValidatorForMock.validateTradingPassword(Trader,SubAccount,String)");

            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                    new Class[] { Trader.class, SubAccount.class, String.class }).asObject();
        }
        return super.validateTradingPassword(l_proxyInputPerson, l_subAccount, l_strTradingPassword);
    }
    
    
    public OrderValidationResult validateAccountForTrading(
            WEB3GentradeMainAccount l_genMainAccount,
            Timestamp l_tsBizDate,
            String l_strProcessFlag)
    {
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateAccountForTrading", new Class[] { WEB3GentradeMainAccount.class, Timestamp.class, String.class }, new Object[] {
                l_genMainAccount, l_tsBizDate, l_strProcessFlag });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateAccountForTrading", new Class[] { WEB3GentradeMainAccount.class, Timestamp.class, String.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeOrderValidatorForMock.validateAccountForTrading(Trader,SubAccount,String)");

            return (OrderValidationResult) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateAccountForTrading",
                    new Class[] { WEB3GentradeMainAccount.class, Timestamp.class, String.class }).asObject();
        }
        return super.validateAccountForTrading(l_genMainAccount, l_tsBizDate, l_strProcessFlag );
    }
}
@
