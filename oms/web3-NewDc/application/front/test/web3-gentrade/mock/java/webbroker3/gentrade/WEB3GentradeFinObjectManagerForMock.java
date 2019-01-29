head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.17.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeFinObjectManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3GentradeFinObjectManagerForMock.java
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16　@金傑(中訊) 新規作成
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeFinObjectManagerForMock extends WEB3GentradeFinObjectManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    		WEB3GentradeFinObjectManagerForMock.class);
    
	public Market getMarket(long l_lngMarketId) throws NotFoundException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeFinObjectManager",
                "getMarket", new Class[]
                { long.class }, 
                new Object[]{ new Long(l_lngMarketId)});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeFinObjectManager", "getMarket",
				new Class[] { long.class }))
		{
			log.debug("webbroker3.gentrade.WEB3GentradeFinObjectManagerForMock.getMarket(long)");
			return (Market) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.gentrade.WEB3GentradeFinObjectManager", "getMarket", new Class[] { long.class })
					.asObject();

		}
		return super.getMarket(l_lngMarketId);
	}
	
    public Trader getTrader(long l_lngTraderId) throws NotFoundException
	{
		if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.gentrade.WEB3GentradeFinObjectManager",
				"getTrader",
				new Class[] { long.class }))
		{
			log.debug("webbroker3.gentrade.WEB3GentradeFinObjectManagerForMock.getTrader(long)");
			return (Trader) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.gentrade.WEB3GentradeFinObjectManager", "getTrader", new Class[] { long.class })
					.asObject();
		}
    	return super.getTrader(l_lngTraderId);
	}
    
    public Market getMarketBySONAR(String l_strInstitutionCode, String l_strMarketCodeSONAR)
            throws WEB3SystemLayerException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.gentrade.WEB3GentradeFinObjectManager",
                "getMarketBySONAR", new Class[]
                { String.class, String.class }, new Object[]
                { l_strInstitutionCode, l_strMarketCodeSONAR });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.gentrade.WEB3GentradeFinObjectManager",
                "getMarketBySONAR", new Class[]
                { String.class, String.class }))
        {
            log.debug("webbroker3.gentrade.WEB3GentradeFinObjectManagerForMock.getMarketBySONAR()");
            return (Market) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", "getMarketBySONAR", new Class[]
                    { String.class, String.class }).asObject();
        }
        return super.getMarketBySONAR(l_strInstitutionCode, l_strMarketCodeSONAR);
    }

    public Market getMarket(String l_strInstitutionCode, String l_strMarketCode) throws NotFoundException
	{
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
        	"webbroker3.gentrade.WEB3GentradeFinObjectManager",
            "getMarket",
            new Class[]{ String.class,String.class }, 
            new Object[]{l_strInstitutionCode,l_strMarketCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.gentrade.WEB3GentradeFinObjectManager",
            "getMarket",
            new Class[]{ String.class,String.class }))
		{
			log.debug("webbroker3.gentrade.WEB3GentradeFinObjectManagerForMock.getMarket(String,String)");
			return (Market) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.gentrade.WEB3GentradeFinObjectManager",
					"getMarket",
					new Class[]{ String.class,String.class })
					.asObject();
		}
		return super.getMarket(l_strInstitutionCode, l_strMarketCode);
	}
	
}
@
