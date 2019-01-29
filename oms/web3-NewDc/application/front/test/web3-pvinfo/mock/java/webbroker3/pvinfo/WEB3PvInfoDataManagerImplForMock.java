head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3PvInfoDataManagerImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3PvInfoDataManagerImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/27 金傑 (中訊) 新規作成
 */
package webbroker3.pvinfo;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬImplForMock
 * 
 * @@author 金傑 (中訊)
 * @@version 1.0
 */
public class WEB3PvInfoDataManagerImplForMock extends WEB3PvInfoDataManagerImpl
{
	/**
	 * ログ出力ユーティリティ。
	 */
	private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoDataManagerImplForMock.class);

	public boolean isAccInfoCampaign(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
	{
		// 1）參數驗證
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
				"isAccInfoCampaign", new Class[] { WEB3GentradeMainAccount.class }, new Object[] { l_mainAccount });
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl", "isAccInfoCampaign",
				new Class[] { WEB3GentradeMainAccount.class }))
		{
			log.debug("webbroker3.pvinfo.WEB3PvInfoDataManagerImplForMock.isAccInfoCampaign(WEB3GentradeMainAccount)");

			TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
					"isAccInfoCampaign", new Class[] { WEB3GentradeMainAccount.class }).asWEB3BaseException();

			return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
					"isAccInfoCampaign", new Class[] { WEB3GentradeMainAccount.class }).asBoolean();
		}

		return super.isAccInfoCampaign(l_mainAccount);
	}
    
 //   public boolean isFromDepositRequest(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
//    {
//        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                "isFromDepositRequest", new Class[]
//                { WEB3GentradeMainAccount.class }, new Object[]
//                { l_mainAccount });
//        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                "isFromDepositRequest", new Class[]
//                { WEB3GentradeMainAccount.class }))
//        {
//            log.debug("webbroker3.pvinfo.WEB3PvInfoDataManagerImplForMock.isFromDepositRequest()");
//            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                    "isFromDepositRequest", new Class[]
//                    { WEB3GentradeMainAccount.class }).asWEB3BaseException();
//            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                    "isFromDepositRequest", new Class[]
//                    { WEB3GentradeMainAccount.class }).asBoolean();
//        }
//        return super.isFromDepositRequest(l_mainAccount);
//    }
    
 //   public boolean isAdditionalDepositRequest(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
//    {
//        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                "isAdditionalDepositRequest", new Class[]
//                { WEB3GentradeMainAccount.class }, new Object[]
//                { l_mainAccount });
//        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                "isAdditionalDepositRequest", new Class[]
//                { WEB3GentradeMainAccount.class }))
//        {
//            log.debug("webbroker3.pvinfo.WEB3PvInfoDataManagerImplForMock.isAdditionalDepositRequest()");
//            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                    "isAdditionalDepositRequest", new Class[]
//                    { WEB3GentradeMainAccount.class }).asWEB3BaseException();
//            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
//                    "isAdditionalDepositRequest", new Class[]
//                    { WEB3GentradeMainAccount.class }).asBoolean();
//        }
//        return super.isAdditionalDepositRequest(l_mainAccount);
//    }
	public String getShortfallGenerationStatus(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
	{
		// 1）參數驗證
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
				"getShortfallGenerationStatus",
				new Class[] { WEB3GentradeMainAccount.class },
				new Object[] { l_mainAccount });
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
				"getShortfallGenerationStatus",
				new Class[] { WEB3GentradeMainAccount.class }))
		{
			log.debug("webbroker3.pvinfo.WEB3PvInfoDataManagerImplForMock.getShortfallGenerationStatus(WEB3GentradeMainAccount)");
				TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
						"webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
						"getShortfallGenerationStatus",
						new Class[] { WEB3GentradeMainAccount.class }).asWEB3BaseException();
				
			return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
					"getShortfallGenerationStatus",
					new Class[] { WEB3GentradeMainAccount.class }).asObject();
		}
		return super.getShortfallGenerationStatus(l_mainAccount);

	}
	
	public String getAdddepositGenerationStatus(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
	{
		// 1）參數驗證
		TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
				"webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
				"getAdddepositGenerationStatus",
				new Class[] { WEB3GentradeMainAccount.class },
				new Object[] { l_mainAccount });
		if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
				"webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
				"getAdddepositGenerationStatus",
				new Class[] { WEB3GentradeMainAccount.class }))
		{
			log.debug("webbroker3.pvinfo.WEB3PvInfoDataManagerImplForMock.getAdditionalMarginSituation(WEB3GentradeMainAccount)");

				TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
						"webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
						"getAdddepositGenerationStatus",
						new Class[] { WEB3GentradeMainAccount.class }).asWEB3BaseException();

			return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
					"webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
					"getAdddepositGenerationStatus",
					new Class[] { WEB3GentradeMainAccount.class }).asObject();
		}

		return super.getAdddepositGenerationStatus(l_mainAccount);

	}
	
}
@
