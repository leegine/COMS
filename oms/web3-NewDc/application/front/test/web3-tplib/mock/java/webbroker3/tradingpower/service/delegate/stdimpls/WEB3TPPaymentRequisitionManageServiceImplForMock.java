head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPPaymentRequisitionManageServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPPaymentRequisitionManageServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/01 金傑（中訊）新規作成
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionAccountDetailInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPPaymentRequisitionManageServiceImplForMock extends WEB3TPPaymentRequisitionManageServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPPaymentRequisitionManageServiceImplForMock.class);
    
    public String getLackCashOccurSituation(MainAccount l_mainAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getLackCashOccurSituation", new Class[]
                { MainAccount.class }, new Object[]
                { l_mainAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getLackCashOccurSituation", new Class[]
                { MainAccount.class }))
        {
            log.debug("webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImplForMock.getDepositInfo()");

                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                        "getLackCashOccurSituation", new Class[]
                        { MainAccount.class }).asWEB3BaseException();

            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getLackCashOccurSituation", new Class[]
                    { MainAccount.class }).asObject();
        }
        return super.getLackCashOccurSituation(l_mainAccount);
    }
    
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo(MainAccount l_mainAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getShortfallGenerationInfo", new Class[]
                { MainAccount.class }, new Object[]
                { l_mainAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getShortfallGenerationInfo", new Class[]
                { MainAccount.class }))
        {
            log.debug("webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImplForMock.getDepositInfo()");

                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                        "getShortfallGenerationInfo", new Class[]
                        { MainAccount.class }).asWEB3BaseException();

            return (WEB3TPShortfallOccurInfo) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getShortfallGenerationInfo", new Class[]
                    { MainAccount.class }).asObject();
        }
        return super.getShortfallGenerationInfo(l_mainAccount);
    }
    
    public String getAdditionalMarginSituation(MainAccount l_mainAccount)  throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdditionalMarginSituation", new Class[]
                { MainAccount.class }, new Object[]
                { l_mainAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdditionalMarginSituation", new Class[]
                { MainAccount.class }))
        {
            log.debug("webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImplForMock.getDepositInfo()");

                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                        "getAdditionalMarginSituation", new Class[]
                        { MainAccount.class }).asWEB3BaseException();

            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdditionalMarginSituation", new Class[]
                    { MainAccount.class }).asObject();
        }
        return super.getAdditionalMarginSituation(l_mainAccount);
    }
    
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(MainAccount l_mainAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdddepositGenerationInfo", new Class[]
                { MainAccount.class }, new Object[]
                { l_mainAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdddepositGenerationInfo", new Class[]
                { MainAccount.class }))
        {
            log.debug("webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImplForMock.getDepositInfo()");

                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                        "getAdddepositGenerationInfo", new Class[]
                        { MainAccount.class }).asWEB3BaseException();

            return (WEB3TPAdddepositGenerationInfo) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo", new Class[]
                    { MainAccount.class }).asObject();
        }
        return super.getAdddepositGenerationInfo(l_mainAccount);
    }
    
    public String getAdditionalMarginSituation(MainAccount l_mainAccount, boolean l_blnAdditionalMargin)  throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdditionalMarginSituation", new Class[]
                { MainAccount.class, boolean.class }, new Object[]
                { l_mainAccount, new Boolean(l_blnAdditionalMargin) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdditionalMarginSituation", new Class[]
                { MainAccount.class, boolean.class }))
        {
            log.debug("webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImplForMock.getDepositInfo()");

                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                        "getAdditionalMarginSituation", new Class[]
                        { MainAccount.class, boolean.class }).asWEB3BaseException();

            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdditionalMarginSituation", new Class[]
                    { MainAccount.class, boolean.class }).asObject();
        }
        return super.getAdditionalMarginSituation(l_mainAccount, l_blnAdditionalMargin);
    }
    
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(MainAccount l_mainAccount, boolean l_blnAdditionalMargin)  throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdddepositGenerationInfo", new Class[]
                { MainAccount.class, boolean.class }, new Object[]
                { l_mainAccount, new Boolean(l_blnAdditionalMargin) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getAdddepositGenerationInfo", new Class[]
                { MainAccount.class, boolean.class }))
        {
            log.debug("webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImplForMock.getDepositInfo()");

                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                        "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                        "getAdddepositGenerationInfo", new Class[]
                        { MainAccount.class, boolean.class }).asWEB3BaseException();

            return (WEB3TPAdddepositGenerationInfo) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo", new Class[]
                    { MainAccount.class, boolean.class }).asObject();
        }
        return super.getAdddepositGenerationInfo(l_mainAccount, l_blnAdditionalMargin);
    }
    
    public WEB3TPPaymentRequisitionAccountDetailInfo getPaymentRequisitionAccountDetailInfo(MainAccount l_mainAccount)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo", new Class[]
                {MainAccount.class}, new Object[]
                {l_mainAccount});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo", new Class[]
                {MainAccount.class}))
        {
            log.debug("webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImplForMock.getPaymentRequisitionAccountDetailInfo()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getPaymentRequisitionAccountDetailInfo", new Class[]
                    {MainAccount.class}).asWEB3BaseException();
            return (WEB3TPPaymentRequisitionAccountDetailInfo) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getPaymentRequisitionAccountDetailInfo", new Class[]
                    {MainAccount.class}).asObject();
        }
        return super.getPaymentRequisitionAccountDetailInfo(l_mainAccount);
    }
    
}
@
