head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformAccSwElecDeliApplyCommonServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3InformAccSwElecDeliApplyCommonServiceImplForMock.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/01 金傑（中訊）新規作成
 */
package webbroker3.inform.service.delegate.stdimpls;


import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformAccSwElecDeliApplyCommonServiceImplForMock extends WEB3InformAccSwElecDeliApplyCommonServiceImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3InformAccSwElecDeliApplyCommonServiceImplForMock.class);

    public WEB3AdminInformAccSwitchElecDeliAppDtInfo createAccSwitchElecDeliAppDtInfo(String l_strTaxType,
            String l_strMarginTaxType) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "createAccSwitchElecDeliAppDtInfo", new Class[]
                { String.class, String.class }, new Object[]
                { l_strTaxType, l_strMarginTaxType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "createAccSwitchElecDeliAppDtInfo", new Class[]
                { String.class, String.class }))
        {
            log
                    .debug("webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImplForMock.createAccSwitchElecDeliAppDtInfo()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "createAccSwitchElecDeliAppDtInfo", new Class[]
                    { String.class, String.class }).asWEB3BaseException();
            return (WEB3AdminInformAccSwitchElecDeliAppDtInfo) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "createAccSwitchElecDeliAppDtInfo", new Class[]
                    { String.class, String.class }).asObject();
        }
        return super.createAccSwitchElecDeliAppDtInfo(l_strTaxType, l_strMarginTaxType);
    }

    public void validateAccSwitchElecDeliApplyInfo(WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo,
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "validateAccSwitchElecDeliApplyInfo", new Class[]
                { WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
                new Object[]
                { l_beforeChangedInfo, l_changedInfo });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "validateAccSwitchElecDeliApplyInfo", new Class[]
                { WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class }))
        {
            log
                    .debug("webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImplForMock.validateAccSwitchElecDeliApplyInfo()");
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                            "validateAccSwitchElecDeliApplyInfo",
                            new Class[]
                            { WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
                                    WEB3AdminInformAccSwitchElecDeliApplyInfo.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER
                    .getMethodReturnValue(
                            "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                            "validateAccSwitchElecDeliApplyInfo",
                            new Class[]
                            { WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
                                    WEB3AdminInformAccSwitchElecDeliApplyInfo.class }).asVoid();
            return;
        }
        super.validateAccSwitchElecDeliApplyInfo(l_beforeChangedInfo, l_changedInfo);
    }
    
    public void validateVoucherCancel(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "validateVoucherCancel", new Class[]
                { String.class, boolean.class }, new Object[]
                { l_strMakeStatus, new Boolean(l_blnSubmitMarketTriggerDiv) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "validateVoucherCancel", new Class[]
                { String.class, boolean.class }))
        {
            log
                    .debug("webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImplForMock.validateVoucherCancel()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateVoucherCancel", new Class[]
                    { String.class, boolean.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateVoucherCancel", new Class[]
                    { String.class, boolean.class }).asVoid();
            return;
        }
        super.validateVoucherCancel(l_strMakeStatus, l_blnSubmitMarketTriggerDiv);
    }
    
    /**
     * (トリガ発行)<BR>
     * トリガを発行する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strDataCode - (データコード)<BR>
     * データコード<BR>
     */
    public void submitMarketTrigger(String l_strInstitutionCode, String l_strDataCode)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "submitMarketTrigger", new Class[]
                { String.class, String.class }, new Object[]
                { l_strInstitutionCode, l_strDataCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "submitMarketTrigger", new Class[]
                { String.class, String.class }))
        {
            log
                    .debug("webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImplForMock.submitMarketTrigger()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "submitMarketTrigger", new Class[]
                    { String.class, String.class }).asVoid();
            return;
        }
        super.submitMarketTrigger(l_strInstitutionCode, l_strDataCode);
    }
    
    public WEB3AdminInformAccSwitchElecDeliApplyInfo createAccSwitchElecDeliApplyInfo(MainAccount l_mainAccount)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "createAccSwitchElecDeliApplyInfo", new Class[]
                { MainAccount.class }, new Object[]
                { l_mainAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "createAccSwitchElecDeliApplyInfo", new Class[]
                { MainAccount.class }))
        {
            log
                    .debug("webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImplForMock.createAccSwitchElecDeliApplyInfo()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "createAccSwitchElecDeliApplyInfo", new Class[]
                    { MainAccount.class }).asWEB3BaseException();
            return (WEB3AdminInformAccSwitchElecDeliApplyInfo) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "createAccSwitchElecDeliApplyInfo", new Class[]
                    { MainAccount.class }).asObject();
        }
        return super.createAccSwitchElecDeliApplyInfo(l_mainAccount);
    }
    
    public void validateVoucherChange(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "validateVoucherChange", new Class[]
                { String.class, boolean.class }, new Object[]
                { l_strMakeStatus, new Boolean(l_blnSubmitMarketTriggerDiv) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "validateVoucherChange", new Class[]
                { String.class, boolean.class }))
        {
            log
                    .debug("webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImplForMock.validateVoucherChange()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateVoucherChange", new Class[]
                    { String.class, boolean.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateVoucherChange", new Class[]
                    { String.class, boolean.class }).asVoid();
            return;
        }
        super.validateVoucherChange(l_strMakeStatus, l_blnSubmitMarketTriggerDiv);
    }
}
@
