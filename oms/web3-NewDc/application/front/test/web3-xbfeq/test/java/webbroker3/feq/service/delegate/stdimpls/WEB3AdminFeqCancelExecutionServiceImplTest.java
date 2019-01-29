head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqCancelExecutionServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqCancelExecutionServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCancelExecutionServiceImplTest.class);
    WEB3AdminFeqCancelExecutionServiceImpl l_impl;
    public WEB3AdminFeqCancelExecutionServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        l_impl = new WEB3AdminFeqCancelExecutionServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateCancel_Case0001()
    {
        final String STR_METHOD_NAME = "testValidateCancel_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFeqCancelExecutionConfirmRequest l_request =
            new WEB3AdminFeqCancelExecutionConfirmRequest();
        l_request.managementCode = "34567";
        l_request.execNo = "12";
        l_request.orderBizDate = WEB3DateUtility.getDate("20080312","yyyyMMdd");
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                    true,
                    true);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setBizDate("20080312");
            l_feqOrderUnitParams.setOrderEmpCode("NW"+l_request.managementCode);
            l_feqOrderUnitParams.setInstitutionCode("0D");
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setConfirmedQuantity(120);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //FeqOrderExecutionRow
            TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            l_impl.validateCancel(l_request);
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03134,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
            
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidateCancel_Case0002()
    {
        final String STR_METHOD_NAME = "testValidateCancel_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFeqCancelExecutionConfirmRequest l_request =
            new WEB3AdminFeqCancelExecutionConfirmRequest();
        l_request.managementCode = "34567";
        l_request.execNo = "12";
        l_request.orderBizDate = WEB3DateUtility.getDate("20080312","yyyyMMdd");
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                    true,
                    true);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setBizDate("20080312");
            l_feqOrderUnitParams.setOrderEmpCode("NW"+l_request.managementCode);
            l_feqOrderUnitParams.setInstitutionCode("0D");
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //FeqOrderExecutionRow
            TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            l_impl.validateCancel(l_request);
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03134,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
            
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidateCancel_Case0003()
    {
        final String STR_METHOD_NAME = "testValidateCancel_Case0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFeqCancelExecutionConfirmRequest l_request =
            new WEB3AdminFeqCancelExecutionConfirmRequest();
        l_request.managementCode = "34567";
        l_request.execNo = "12";
        l_request.orderBizDate = WEB3DateUtility.getDate("20080312","yyyyMMdd");
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                    true,
                    true);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setBizDate("20080312");
            l_feqOrderUnitParams.setOrderEmpCode("NW"+l_request.managementCode);
            l_feqOrderUnitParams.setInstitutionCode("0D");
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setConfirmedQuantity(100);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //FeqOrderExecutionRow
            TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            l_impl.validateCancel(l_request);
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01321,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
            
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
