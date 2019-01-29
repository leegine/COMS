head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AcceptLoginServiceImplForTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.login.service.delegate.stdimpls;

import java.util.Map;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3ServiceImpState;
import webbroker3.login.message.WEB3AcceptInfo;
import webbroker3.login.message.WEB3BranchInfo;
import webbroker3.login.message.WEB3InstitutionInfo;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.util.WEB3LogUtility;

public class WEB3AcceptLoginServiceImplForTest extends WEB3AcceptLoginServiceImpl
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptLoginServiceImplForTest.class);

    protected WEB3AcceptInfo getAcceptInfo(String l_acceptCode, MainAccountRow l_mainAccountRow, LoginInfo l_loginInfo, String l_orderRootDiv)
    throws WEB3BaseException
    {
        log.entering("getAcceptInfo(String)");
        WEB3AcceptInfo l_acceptInfo = new WEB3AcceptInfo();
        log.exiting("getAcceptInfo(String)");
        return l_acceptInfo;
    }
    
    public String getPasswordForIVR(String l_strPassword, Map l_loginAttributes) 
    throws NotFoundException
    {
        final String STR_METHOD_NAME = "getPasswordForIVR(String, Map)";
        log.entering(STR_METHOD_NAME);
        
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
//    protected void insertLoginHistory(
//        long l_lngBranchID,
//        String l_strInstitutionCode,
//        String l_strBranchCode,
//        String l_strAccountCode,
//        String l_strOrderActionId,
//        long l_lngAccountID,
//        String l_strIpAddress,
//        String l_strOrderRootDiv,
//        String l_strOrderChannel,
//        Date l_datMachineTime,
//        ErrorInfo l_errorInfo) throws WEB3BaseException
//    {
//        final String STR_METHOD_NAME = "insertLoginHistory(long, String, String, String, String," +
//                "long, String, String, String, Date, ErrorInfo)";
//        log.entering(STR_METHOD_NAME);
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    protected boolean isRejectIp(
        long l_lngBranchId,
        String l_strInstitutionCode,
        String l_strOrderRootDiv,
        String l_strIpAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isRejectIp(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        if (WEB3AcceptLoginServiceImplTest_xiexuan.l_expIsRejectIp_BranchIdValue != l_lngBranchId
            || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expIsRejectIp_InstitutionCodeValue.equals(l_strInstitutionCode)
            || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expIsRejectIp_OrderRootDivValue.equals(l_strOrderRootDiv)
            || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expIsRejectIp_IpAddressValue.equals(l_strIpAddress))
        {
            WEB3AcceptLoginServiceImplTest_xiexuan.l_blnIsRejectIp_ParamCheck = true;
        }
        
        if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnIsRejectIpFlag)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    protected ProcessingResult validateDiscriminationCode(
        String l_strDiscriminationCode,
        String l_strOrderRootDiv,
        Map l_mapLoginTypeAttributes,
        Map l_mapLoginAttributes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDiscriminationCode(String, String, Map, Map)";
        log.entering(STR_METHOD_NAME);

        if (!WEB3AcceptLoginServiceImplTest_xiexuan.l_expValidateDiscriminationCode_DiscriminationCodeValue.equals(l_strDiscriminationCode)
            || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expValidateDiscriminationCode_OrderRootDivValue.equals(l_strOrderRootDiv)
            || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expValidateDiscriminationCode_LoginTypeAttributesValue.equals(l_mapLoginTypeAttributes)
            )
        {
            WEB3AcceptLoginServiceImplTest_xiexuan.l_blnValidateDiscriminationCode__ParamCheck = true;
        }
        
        if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnProcessingResultFlag)
        {
            log.exiting(STR_METHOD_NAME);
            return ProcessingResult.SUCCESS_RESULT;
        }
        ErrorInfo errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90221;

        log.exiting(STR_METHOD_NAME);
        return ProcessingResult.newFailedResultInstance(errorInfo);
    }
    
    protected boolean isLoginStop(BranchRow l_branchRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isLoginStop()";
        log.entering(STR_METHOD_NAME);
     
        if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnIsLoginStopFlag)
        {
            return true;
        }
        return false;
    }
    
    protected boolean checkOrderChannel(String l_orderChannel)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkOrderChannel(String)";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);

        if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnCheckOrderChannelFlag)
        {
            return true;
        }
        return false;
    }
    
    protected boolean checkAcceptCode(String l_inputCode, BranchRow l_branchRow)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkAcceptCode(String)";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);

        if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnCheckAcceptCodeFlag)
        {
            return true;
        }
        return false;
    }
    
    protected boolean isEnabledUser(LoginInfo l_loginInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isEnabledUser()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);

        if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnIsEnabledUserFlag)
        {
            return true;
        }
        return false;
    }
    
    protected void setSessionAttribute(
        String l_sessionID,
        WEB3SetSessionRequest l_request)
        throws WEB3BaseException
    {
        
    }
    
    protected WEB3AcceptInfo getAcceptInfo(
        String l_acceptCode,
        MainAccountRow l_mainAccountRow,
        LoginInfo l_loginInfo)
        throws WEB3BaseException
    {
        return null;
    }
    
    protected WEB3InstitutionInfo getInstitutionInfo(
        InstitutionRow l_institutionRow)
        throws WEB3BaseException
    {
        return null;
    }
    
    protected WEB3BranchInfo getBranchInfo(
        BranchRow l_branchRow)
        throws WEB3BaseException
    {
        return null;
    }
    
    protected WEB3ServiceImpState getServiceImpState(
        InstitutionRow l_institutionRow,
        BranchRow l_branchRow,
        MainAccountRow l_mainAccountRow)
        throws WEB3BaseException
    {
        return null;
    }
}
@
