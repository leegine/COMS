head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.08.07.44.35;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6184d9ebcdd780c;
filename	WEB3FXDataControlServiceImplForMock.java;

1.1
date	2011.04.07.01.41.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXDataControlServiceImplForMock.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FXDataControlServiceImplForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 金傑（中訊）新規作成
*/
package webbroker3.aio;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXDataControlServiceImplForMock extends WEB3FXDataControlServiceImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FXDataControlServiceImplForMock.class);
    
    public CompFxConditionParams getCompFxCondition(String l_strInstitutionCode, String l_strBranchCode)
            throws WEB3BaseException, NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3FXDataControlServiceImpl",
                "getCompFxCondition", new Class[]
                { String.class, String.class }, new Object[]
                { l_strInstitutionCode, l_strBranchCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3FXDataControlServiceImpl",
                "getCompFxCondition", new Class[]
                { String.class, String.class }))
        {
            log.debug("webbroker3.aio.WEB3FXDataControlServiceImplForMock.getCompFxCondition()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "getCompFxCondition", new Class[]
                    { String.class, String.class }).asWEB3BaseException();
            return (CompFxConditionParams) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", "getCompFxCondition", new Class[]
                    { String.class, String.class }).asObject();
        }
        return super.getCompFxCondition(l_strInstitutionCode, l_strBranchCode);
    }

    public String sendSoapMessage(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        SoapConnectPrefRpcParams l_rpcParams) throws WEB3BaseException
    {

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3FXDataControlServiceImpl",
            "sendSoapMessage",
            new Class[]
                { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class },
                    new Object[]
                        { l_fxGftAskingTelegramUnit, l_rpcParams });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3FXDataControlServiceImpl",
            "sendSoapMessage", new Class[]
            { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class }))
        {
            log.debug("webbroker3.aio.WEB3FXDataControlServiceImplForMock.sendSoapMessage()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.aio.WEB3FXDataControlServiceImpl",
                "sendSoapMessage", new Class[]
                { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class }).asWEB3BaseException(); 
    
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", "sendSoapMessage", new Class[]
                    { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class }).asObject();
    
        }
        return super.sendSoapMessage(l_fxGftAskingTelegramUnit, l_rpcParams);
    }


    public String getSoapTFXAcceptResultCode(String l_strAcceptResultCode)
    {

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.aio.WEB3FXDataControlServiceImpl",
            "getSoapTFXAcceptResultCode",
            new Class[]
                { String.class },
                    new Object[]
                        { l_strAcceptResultCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.aio.WEB3FXDataControlServiceImpl",
            "getSoapTFXAcceptResultCode", new Class[]
            { String.class }))
        {
            log.debug("webbroker3.aio.WEB3FXDataControlServiceImplForMock.sendSoapMessage()");
    
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", "getSoapTFXAcceptResultCode", new Class[]
                    { String.class }).asObject();
        }
        return super.getSoapTFXAcceptResultCode(l_strAcceptResultCode);
    }
    
    public void updateGFTTransferStatus(
            String l_strInstitutionCode, String l_strBranchCode,
            String l_strRequestCode, String l_strResultCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3FXDataControlServiceImpl",
            "updateGFTTransferStatus",
            new Class[]{ String.class, String.class, String.class, String.class },
            new Object[]{ l_strInstitutionCode, l_strBranchCode,l_strRequestCode ,l_strResultCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "updateGFTTransferStatus",
                new Class[]{  String.class, String.class, String.class, String.class }))
            {
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", new Class[]
                    {  String.class, String.class, String.class, String.class}).asWEB3BaseException();
            
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", new Class[]
                    {  String.class, String.class, String.class, String.class}).asVoid();
            
                return;
            }
        super.updateGFTAccountOpenStatus(l_strInstitutionCode, l_strBranchCode,l_strRequestCode ,l_strResultCode);
    }
    
    public void validateSetup(SoapConnectPrefRpcParams l_rpcParams) 
        throws WEB3BaseException
    {
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "validateSetup",
                new Class[]{ SoapConnectPrefRpcParams.class},
                new Object[]{ l_rpcParams});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "validateSetup",
                new Class[]{SoapConnectPrefRpcParams.class}))
            {
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "validateSetup", 
                    new Class[]{SoapConnectPrefRpcParams.class}).asWEB3BaseException();
            
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "validateSetup",
                    new Class[]{SoapConnectPrefRpcParams.class}).asVoid();
                return;
            }
        super.validateSetup(l_rpcParams);
    }

    public ArrayList getGFTFxSystemCodeLists(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "getGFTFxSystemCodeLists",
                new Class[]{ String.class, String.class },
                new Object[]{ l_strInstitutionCode, l_strBranchCode });
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "getGFTFxSystemCodeLists",
                new Class[]{ String.class, String.class }))
            {
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "getGFTFxSystemCodeLists", 
                    new Class[]{ String.class, String.class }).asWEB3BaseException();
            
                return (ArrayList)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "getGFTFxSystemCodeLists",
                    new Class[]{ String.class, String.class }).asObject();
            }
        return super.getGFTFxSystemCodeLists(l_strInstitutionCode, l_strBranchCode);
    }

    public boolean isGFTAccOpen(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        ArrayList l_lisFxSystemCodeLists) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "isGFTAccOpen",
                new Class[]{ String.class, String.class, String.class, ArrayList.class },
                new Object[]{ l_strInstitutionCode, l_strBranchCode, l_strAccountCode, l_lisFxSystemCodeLists });
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "isGFTAccOpen",
                new Class[]{ String.class, String.class }))
            {
                TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "isGFTAccOpen", 
                    new Class[]{ String.class, String.class, String.class, ArrayList.class }).asWEB3BaseException();
            
                return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "isGFTAccOpen",
                    new Class[]{ String.class, String.class, String.class, ArrayList.class }).asBoolean();
            }
        return super.isGFTAccOpen(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode,
            l_lisFxSystemCodeLists);
    }
    
    public void validateChangePoss(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangePoss(SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.WEB3FXDataControlServiceImpl",
            "validateChangePoss",
            new Class[]{ SubAccount.class, CompFxConditionParams.class},
            new Object[]{ l_subAccount,l_compFxConditionParams});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.WEB3FXDataControlServiceImpl",
            "validateChangePoss",
            new Class[]{ SubAccount.class, CompFxConditionParams.class}))
        {
        
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "validateChangePoss",
                new Class[]{ SubAccount.class, CompFxConditionParams.class}).asVoid();
            return;
        }
        super.validateChangePoss(l_subAccount, l_compFxConditionParams);
    }
}
@


1.1
log
@*** empty log message ***
@
text
@a16 1
import com.gftforex.soap.api.SendSyncRequestResponse;
@

