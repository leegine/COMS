head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.MailAddressRegiParams;
import webbroker3.accountopen.data.MailAddressRegiRow;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenRegistServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenRegistServiceImplTest.class);

    public WEB3AccOpenRegistServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenRegistServiceImpl.getInputScreen(WEB3AccOpenApplyInputRequest)'
     */
    public void testGetInputScreenCase1()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MailAddressRegiRow.TYPE);
            MailAddressRegiParams l_params = new MailAddressRegiParams();
            l_params.setMailAddressRegiId(1111);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("101");
            l_params.setEmailAddress("zhang@@sinocom.cn");
            l_params.setFamilyName("zhang");
            l_params.setAccountDiv("1");
            l_params.setDeleteFlag(BooleanEnum.FALSE);
            l_params.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_params.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_params);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImpl loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},loginInfo);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AccOpenRegistServiceImpl l_impl = new WEB3AccOpenRegistServiceImpl();
            WEB3AccOpenApplyInputRequest l_request = new WEB3AccOpenApplyInputRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.mailAddressID = "1111";
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01789, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInputScreenCase2()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MailAddressRegiRow.TYPE);
            MailAddressRegiParams l_params = new MailAddressRegiParams();
            l_params.setMailAddressRegiId(1111);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("101");
            l_params.setEmailAddress("yu@@sinocom.cn");
            l_params.setFamilyName("zhang");
            l_params.setAccountDiv("1");
            l_params.setDeleteFlag(BooleanEnum.FALSE);
            l_params.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_params.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_params);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImpl loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},loginInfo);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AccOpenRegistServiceImpl l_impl = new WEB3AccOpenRegistServiceImpl();
            WEB3AccOpenApplyInputRequest l_request = new WEB3AccOpenApplyInputRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.mailAddressID = "1111";
            WEB3AccOpenApplyInputResponse response =  (WEB3AccOpenApplyInputResponse)l_impl.getInputScreen(l_request);
            //　@姓　@　@　@　@　@　@　@　@
            //　@名　@　@　@　@　@　@　@　@
            //　@メールアドレス　@　@　@
            //　@仲介業扱者コード　@　@
            //　@口座区分　@　@　@　@　@　@
            //　@リンク元判別コード
            assertEquals(response.accountFamilyName ,"zhang");
            assertNull(response.accountName);
            assertEquals(response.mailAddress ,"yu@@sinocom.cn");
            assertNull(response.brokerageCode);
            assertEquals(response.accountType ,"1");
            assertNull(response.linkCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreenCase3()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MailAddressRegiRow.TYPE);
            MailAddressRegiParams l_params = new MailAddressRegiParams();
            l_params.setMailAddressRegiId(1111);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("101");
            l_params.setEmailAddress("yu@@sinocom.cn");
            l_params.setFamilyName("zhang");
            l_params.setAccountDiv("1");
            l_params.setDeleteFlag(BooleanEnum.FALSE);
            l_params.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_params.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_params);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImpl loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},loginInfo);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AccOpenRegistServiceImpl l_impl = new WEB3AccOpenRegistServiceImpl();
            WEB3AccOpenApplyInputRequest l_request = new WEB3AccOpenApplyInputRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
//            l_request.mailAddress = "yu@@sinocom.cn";
//            l_request.mailAddressID = "1111";
            WEB3AccOpenApplyInputResponse response =  (WEB3AccOpenApplyInputResponse)l_impl.getInputScreen(l_request);
            //　@姓　@　@　@　@　@　@　@　@
            //　@名　@　@　@　@　@　@　@　@
            //　@メールアドレス　@　@　@
            //　@仲介業扱者コード　@　@
            //　@口座区分　@　@　@　@　@　@
            //　@リンク元判別コード
            assertNull(response.accountFamilyName );
            assertNull(response.accountName);
            assertNull(response.mailAddress);
            assertNull(response.brokerageCode);
            assertNull(response.accountType);
            assertNull(response.linkCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
