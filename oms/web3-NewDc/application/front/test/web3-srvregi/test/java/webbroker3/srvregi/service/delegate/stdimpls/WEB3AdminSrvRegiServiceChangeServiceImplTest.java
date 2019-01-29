head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiServiceChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminSrvRegiServiceChangeServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/13 金傑 (中訊) 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteResponse;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiServiceChangeServiceImplTest extends TestBaseForMock
{
    
    private WEB3AdminSrvRegiServiceChangeServiceImpl l_serviceImpl = null;
    
    private WEB3AdminSrvRegiServiceChangeCommonRequestForTest l_request = null;
    
    private WEB3AdminSrvRegiServiceChangeCompleteResponse l_response = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminSrvRegiServiceChangeServiceImplTest.class);

    public WEB3AdminSrvRegiServiceChangeServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3AdminSrvRegiServiceChangeServiceImpl();
        this.l_request = new WEB3AdminSrvRegiServiceChangeCommonRequestForTest();
        this.initData();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_request = null;
        super.tearDown();
    }
    
    /**
     * 無料対象期間=2
     *
     */
    public void testSubmitChange_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.serviceStatus = "0";
            this.l_request.applyInfo = null;
            this.l_request.serviceDiv = "1234";
            this.l_request.password = "abcd";
            this.l_request.freeTargetPeriod = "2";
            this.l_request.trialPeriod = "3";
            this.l_request.applyAbleStartDate = "5";
            this.l_request.applyAbleEndDate = "10";
            this.l_request.noticeMailDate = "1";
            this.l_request.elePigeonDiv = true;
            this.l_request.serviceContent = "My Contents";
            this.l_request.explainURL = "http://";
            this.l_request.confirmMailDiv = "1";
            this.l_request.noticeMailDiv = "2";
            this.l_request.summary = "mySummary";
            this.l_response = this.l_serviceImpl.submitChange(this.l_request);
            
            assertNotNull(this.l_response);
            
           
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = " institution_code = ? and srv_div = ? ";
            Object[] l_obj = {"1D", "1234"};
            
            List l_lisApplicationRequiredServiceRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiSetupRow.TYPE, 
                l_strWhere, 
                l_obj);
            assertEquals(1,l_lisApplicationRequiredServiceRowList.size());
            assertEquals(2,((SrvRegiSetupRow)l_lisApplicationRequiredServiceRowList.get(0)).getFreeCoverageLength());
            
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail(); 
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail(); 
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("1D");
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator,"C0601",true,true);
            WEB3AdministratorForMock.mockIsDirAdministrator(l_expectAdministrator,false);
            WEB3AdministratorForMock.mockValidateTradingPassword("pass",true);
            
            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();       
            l_srvRegiMasterParams.setInstitutionCode("1D");
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setOfferingDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
            
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSupplyDiv("1");
            l_srvRegiSetupParams.setTrialPeriod(2);
            l_srvRegiSetupParams.setAppliDateFrom(2);
            l_srvRegiSetupParams.setAppliDateTo(3);
            l_srvRegiSetupParams.setSendMailInterval(10);
            l_srvRegiSetupParams.setFreeCoverageLength(2);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private class WEB3AdminSrvRegiServiceChangeCommonRequestForTest extends WEB3AdminSrvRegiServiceChangeCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3AdminSrvRegiServiceChangeCommonRequestForTest.validate()");
        }
    }

    public void testValidateWord_Case001()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%OTHER_SRV_REGI_STATUS%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case002()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%EQUITY_TAXTYPE%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case003()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%EQUITY_TAXTYPE_N%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case004()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%MARGIN_TAXTYPE%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case005()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%MARGIN_TAXTYPE_N%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case006()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%CD_KEY%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case007()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%jiddk%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertFalse(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case008()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%ACC_TRANS_VALUE%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateWord_Case009()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case009()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%ACCOUNT_NAME_KANA%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case010()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case010()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%ZIP_CODE%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case011()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case011()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%ADDRESS1%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case012()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case012()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%ADDRESS2%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case013()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case013()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%ADDRESS3%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case014()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case014()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%FX_LOGIN_ID%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateWord_Case015()
    {
        final String STR_METHOD_NAME = "testValidateWord_Case015()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminSrvRegiServiceChangeServiceImpl l_impl =
                new WEB3AdminSrvRegiServiceChangeServiceImpl();
            String l_strWord = "%%ACCOUNT_CODE_6DIGIT%%";
            Object[] obj = new Object[]{l_strWord};
            Method method = WEB3AdminSrvRegiServiceChangeServiceImpl.class.getDeclaredMethod(
                "validateWord",
                new Class[]{String.class});
            method.setAccessible(true);
            Object l_return = method.invoke(l_impl, obj);
            assertTrue(((Boolean)l_return).booleanValue());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
