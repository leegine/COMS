head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXSingleSignOnServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.message.WEB3FXSingleSignOnRequest;
import webbroker3.aio.message.WEB3FXTradeAgreementRequest;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OtherOrgOutOfSrvDateRow;
import webbroker3.gentrade.data.OtherOrgOutOfSrvWeekRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXSingleSignOnServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSingleSignOnServiceImplTest.class);
    
    forTestWEB3FXSingleSignOnServiceImpl l_mpl = new forTestWEB3FXSingleSignOnServiceImpl();

    public WEB3FXSingleSignOnServiceImplTest(String arg0)
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
    
    public class forTestWEB3FXSingleSignOnServiceImpl extends WEB3FXSingleSignOnServiceImpl
    {
        public SubAccount getSubAccount(SubAccountTypeEnum l_subAccountType)
        throws WEB3SystemLayerException
        {
            SubAccount l_subAccount = null;
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                MainAccountParams l_mainAccountParams =
                    TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setAccountId(333812512203L);
                l_mainAccountParams.setInstitutionId(33381251220301L);
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setAccountId(333812512203L);
                l_subAccountParams.setInstitutionId(33381251220301L);
                TestDBUtility.insertWithDel(l_subAccountParams);
                
                 l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
                 
                
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                
                fail();
            }
            
            return l_subAccount;
        }
    }
    
    
    public void testGetFXTradeAgreementScreen_Case001()
    {
        final String STR_METHOD_NAME = "testGetFXTradeAgreementScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3FXTradeAgreementRequest l_request = new WEB3FXTradeAgreementRequest();
            l_request.fxSystemCode = "10";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33381251220301L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("10");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(OtherOrgOutOfSrvDateRow.TYPE);
            TestDBUtility.deleteAll(OtherOrgOutOfSrvWeekRow.TYPE);
            TestDBUtility.deleteAll(FxAccountRow.TYPE);

            l_mpl.getFXTradeAgreementScreen(l_request);
            fail();
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01866, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

       log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitDisplayExterFxSystem_Case001()
    {
        final String STR_METHOD_NAME = "testSubmitDisplayExterFxSystem_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3FXSingleSignOnRequest l_request = new WEB3FXSingleSignOnRequest();
            l_request.batoCheckFlag = false;
            l_request.fxSystemCode = "10";
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33381251220301L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("10");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(OtherOrgOutOfSrvWeekRow.TYPE);
            TestDBUtility.deleteAll(OtherOrgOutOfSrvDateRow.TYPE);
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            
            l_mpl.submitDisplayExterFxSystem(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01866, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
