head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文照会サービスImplテスト(WEB3AdminEquityForcedSettleReferenceServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/29 趙林鵬 (中訊) 新規作成
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
//import webbroker3.eqtypeadmin.data.ForcedSettleErrorOrderRow;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReasonUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleSortKeyUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （管理者・強制決済注文照会サービスImplテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleReferenceServiceImplTest.class);

    public WEB3AdminEquityForcedSettleReferenceServiceImplTest(String arg0)
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
    public void testExecuteCase0001()
    {
        
        final String STR_METHOD_NAME = " testexecuteCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {  
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();

            WEB3GenRequest l_request = null;

            l_impl.execute(l_request); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExecuteCase0002()
    {
        
        final String STR_METHOD_NAME = " testexecuteCase0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {  
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();

            WEB3AdminForcedSettleApproveConfirmRequest l_request =
                new WEB3AdminForcedSettleApproveConfirmRequest();

            l_impl.execute(l_request); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80018);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    public void testCreateForcedSettleCloseDateListCase0001()
    {
        
        final String STR_METHOD_NAME = " testCreateForcedSettleCloseDateListCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {  
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setBranchId(33382);
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setBranchCode("382");
            TestDBUtility.insertWithDel(l_branchParams1);

//            TestDBUtility.deleteAll(ForcedSettleErrorOrderRow.TYPE);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setBranchId(33381);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            l_eqTypeClosingContractSpecParams.setContractId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(1001);
            l_eqTypeContractParams.setContractPrice(100.11);
            l_eqTypeContractParams.setOriginalContractPrice(100.22);
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
      
            String l_strInstitutionCode = "0D";
            String[] l_strBranchCodeList = {"381", "382"};
            
            Date[] l_dat = l_impl.createForcedSettleCloseDateList(
                l_strInstitutionCode,
                l_strBranchCodeList);

            assertEquals("20070111", WEB3DateUtility.formatDate(l_dat[0], "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    public void testcreateForcedSettleReasonUnitListCase0001()
    {
        final String STR_METHOD_NAME = " testcreateForcedSettleReasonUnitListCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        { 
    
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setBranchId(33381);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);

            l_branch = l_accountManager.getBranch(l_eqtypeOrderUnitParams.getBranchId());
            
            
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();
            
            l_impl.createForcedSettleReasonUnitList(l_branch);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testcreateForcedSettleReasonUnitListCase0002()
    {
        final String STR_METHOD_NAME = " testcreateForcedSettleReasonUnitListCase0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        { 
    
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setBranchId(33381);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("margin.forcedsettleorder.compregulation.marginmaintenancerate");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("100");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branch = l_accountManager.getBranch(l_eqtypeOrderUnitParams.getBranchId());
            
            
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();
            
            l_impl.createForcedSettleReasonUnitList(l_branch);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testcreateForcedSettleReasonUnitListCase0003()
    {
        final String STR_METHOD_NAME = " testcreateForcedSettleReasonUnitListCase0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        { 
    
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setBranchId(33381);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("margin.forcedsettleorder.compregulation.marginmaintenancerate");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("100");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("margin.forcedsettleorder.compregulation.margincallelapseddays");
            l_branchPreferencesParams1.setNameSerialNo(1);
            l_branchPreferencesParams1.setValue("101");
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            l_branch = l_accountManager.getBranch(l_eqtypeOrderUnitParams.getBranchId());
            
            
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();
            
            l_impl.createForcedSettleReasonUnitList(l_branch);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testcreateForcedSettleReasonUnitListCase0004()
    {
        final String STR_METHOD_NAME = " testcreateForcedSettleReasonUnitListCase0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        { 
    
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setBranchId(33381);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("margin.forcedsettleorder.compregulation.marginmaintenancerate");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("margin.forcedsettleorder.compregulation.margincallelapseddays");
            l_branchPreferencesParams1.setNameSerialNo(1);
            l_branchPreferencesParams1.setValue("3");
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            BranchPreferencesParams l_branchPreferencesParams2 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams2.setBranchId(33381);
            l_branchPreferencesParams2.setName("margin.forcedsettleorder.legal.marginmaintenancerate");
            l_branchPreferencesParams2.setNameSerialNo(1);
            l_branchPreferencesParams2.setValue("4");
            TestDBUtility.insertWithDel(l_branchPreferencesParams2);
            
            l_branch = l_accountManager.getBranch(l_eqtypeOrderUnitParams.getBranchId());

            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();
            
            l_impl.createForcedSettleReasonUnitList(l_branch);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testcreateForcedSettleReasonUnitListCase0005()
    {
        final String STR_METHOD_NAME = " testcreateForcedSettleReasonUnitListCase0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setBranchId(33381);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("margin.forcedsettleorder.compregulation.marginmaintenancerate");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            BranchPreferencesParams l_branchPreferencesParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams1.setBranchId(33381);
            l_branchPreferencesParams1.setName("margin.forcedsettleorder.compregulation.margincallelapseddays");
            l_branchPreferencesParams1.setNameSerialNo(1);
            l_branchPreferencesParams1.setValue("3");
            TestDBUtility.insertWithDel(l_branchPreferencesParams1);
            
            BranchPreferencesParams l_branchPreferencesParams2 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams2.setBranchId(33381);
            l_branchPreferencesParams2.setName("margin.forcedsettleorder.legal.marginmaintenancerate");
            l_branchPreferencesParams2.setNameSerialNo(1);
            l_branchPreferencesParams2.setValue("4");
            TestDBUtility.insertWithDel(l_branchPreferencesParams2);
            
            BranchPreferencesParams l_branchPreferencesParams3 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams3.setBranchId(33381);
            l_branchPreferencesParams3.setName("margin.forcedsettleorder.legal.margincallelapseddays");
            l_branchPreferencesParams3.setNameSerialNo(1);
            l_branchPreferencesParams3.setValue("7");
            TestDBUtility.insertWithDel(l_branchPreferencesParams3);
            
            l_branch = l_accountManager.getBranch(l_eqtypeOrderUnitParams.getBranchId());

            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();
            
            l_impl.createForcedSettleReasonUnitList(l_branch);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testgetInputScreen0001()
    {
        final String STR_METHOD_NAME = " testgetInputScreen0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            this.deleteAll();
            WEB3AdminForcedSettleRefInputRequest l_request =
                new WEB3AdminForcedSettleRefInputRequest();
            
            l_request.branchCodeList = new String[]{"381"};
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setBranchId(33381);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            l_eqTypeClosingContractSpecParams.setContractId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(1001);
            l_eqTypeContractParams.setContractPrice(100.11);
            l_eqTypeContractParams.setOriginalContractPrice(100.22);
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "C0108", false, true);
            
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();
            
            WEB3AdminForcedSettleRefInputResponse l_response = l_impl.getInputScreen(l_request);
            
            log.debug("翌営業日 ===========" + l_response.orderBizDateList[0]);
            log.debug("当営業日 ===========" + l_response.orderBizDateList[1]);
            log.debug("前営業日 ===========" + l_response.orderBizDateList[2]);
            
            assertEquals(WEB3DateUtility.getDate("20070111", "yyyyMMdd"), l_response.settleTimeLimitList[0]);

            assertEquals("0", l_response.forcedSettleReasonList[0].forcedSettleReason);
            assertEquals("9", l_response.forcedSettleReasonList[1].forcedSettleReason);
            assertNull(l_response.forcedSettleReasonList[0].marginDepositRate);
            assertNull(l_response.forcedSettleReasonList[0].additionalElapsedDays);
            assertNull(l_response.forcedSettleReasonList[1].marginDepositRate);
            assertNull(l_response.forcedSettleReasonList[1].additionalElapsedDays);
            
            assertEquals("0005", l_response.errorReason[0]);
            assertEquals("0006", l_response.errorReason[1]);
            assertEquals("0016", l_response.errorReason[2]);
            assertEquals("0017", l_response.errorReason[3]);
            assertEquals("9001", l_response.errorReason[4]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testgetReferenceScreen0001()
    {
        final String STR_METHOD_NAME = " testgetInputScreen0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            this.deleteAll();
            WEB3AdminForcedSettleSortKeyUnit[] l_sortKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
            WEB3AdminForcedSettleSortKeyUnit l_sortKey = new WEB3AdminForcedSettleSortKeyUnit();
            l_sortKey.keyItem = "branchCode";
            l_sortKey.ascDesc = "A";
            l_sortKeys[0] = l_sortKey;
            WEB3AdminForcedSettleReferenceRequest l_request =
                new WEB3AdminForcedSettleReferenceRequest();
            l_request.pageIndex = "10";
            l_request.pageSize = "11";
            l_request.branchCodeList = new String[]{"381"};
            l_request.sortKeys = l_sortKeys;
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "C0108", false, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_expectAdministrator, "381", true);
            
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();
            
            l_impl.getReferenceScreen(l_request);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testgetReferenceScreen0002()
    {
        final String STR_METHOD_NAME = " testgetInputScreen0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            this.deleteAll();
            WEB3AdminForcedSettleSortKeyUnit[] l_sortKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
            WEB3AdminForcedSettleSortKeyUnit l_sortKey = new WEB3AdminForcedSettleSortKeyUnit();
            l_sortKey.keyItem = "branchCode";
            l_sortKey.ascDesc = "A";
            l_sortKeys[0] = l_sortKey;
            WEB3AdminForcedSettleReferenceRequest l_request =
                new WEB3AdminForcedSettleReferenceRequest();
            l_request.pageIndex = "10";
            l_request.pageSize = "11";
            l_request.branchCodeList = new String[]{"381"};
            l_request.sortKeys = l_sortKeys;
            l_request.approveType = "0";
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            l_eqtypeOrderUnitParams.setBranchId(33381);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setProductId(1006160060005L);
            l_eqtypeOrderUnitParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeClosingContractSpecParams l_eqTypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            l_eqTypeClosingContractSpecParams.setOrderUnitId(1001);
            l_eqTypeClosingContractSpecParams.setContractId(1001);
            TestDBUtility.insertWithDel(l_eqTypeClosingContractSpecParams);
            
            EqtypeContractParams l_eqTypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            l_eqTypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqTypeContractParams.setContractId(1001);
            l_eqTypeContractParams.setContractPrice(100.11);
            l_eqTypeContractParams.setOriginalContractPrice(100.22);
            l_eqTypeContractParams.setCloseDate(WEB3DateUtility.getDate("20070111","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqTypeContractParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setProductId(1006160060005L);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(1006160060005L);
            l_ProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "C0108", false, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_expectAdministrator, "381", true);
            
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();
            
            WEB3AdminForcedSettleReferenceResponse l_response = l_impl.getReferenceScreen(l_request);
            
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
            assertEquals("381", l_response.forcedSettleTemporaryOrderList[0].branchCode);
            assertEquals("N8080", l_response.forcedSettleTemporaryOrderList[0].productCode);
            assertEquals("SP", l_response.forcedSettleTemporaryOrderList[0].marketCode);
            log.debug("************** estimatedAsset = " + l_response.forcedSettleTemporaryOrderList[0].estimatedAsset);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
        */
    
    public void testCreateForcedSettleReasonUnitList()
    {
        final String STR_METHOD_NAME = "testCreateForcedSettleReasonUnitList()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferenceParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams.setBranchId(12345);
            l_branchPreferenceParams.setName("first.deposit.rate2");
            TestDBUtility.insertWithDel(l_branchPreferenceParams);
            
            BranchPreferencesParams l_branchPreferenceParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams1.setBranchId(12345);
            l_branchPreferenceParams1.setName("first.margin.pass.day2");
            TestDBUtility.insertWithDel(l_branchPreferenceParams1);
            
            BranchPreferencesParams l_branchPreferenceParams2 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams2.setBranchId(12345);
            l_branchPreferenceParams2.setName("first.deposit.rate1");
            TestDBUtility.insertWithDel(l_branchPreferenceParams2);
            
            BranchPreferencesParams l_branchPreferenceParams3 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams3.setBranchId(12345);
            l_branchPreferenceParams3.setName("first.margin.pass.day1");
            TestDBUtility.insertWithDel(l_branchPreferenceParams3);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            WEB3AdminEquityForcedSettleReferenceServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleReferenceServiceImpl();
            WEB3AdminForcedSettleReasonUnit[] l_units = l_impl.createForcedSettleReasonUnitList(l_branch);
            assertEquals(null, l_units[0].additionalElapsedDaysUpperLimit);
            assertEquals("0", l_units[0].forcedSettleReason);
            assertEquals(null, l_units[0].marginMaintenanceRate);
            assertEquals("1", l_units[1].additionalElapsedDaysUpperLimit);
            assertEquals("1", l_units[1].forcedSettleReason);
            assertEquals("1", l_units[1].marginMaintenanceRate);
            assertEquals("1", l_units[2].additionalElapsedDaysUpperLimit);
            assertEquals("2", l_units[2].forcedSettleReason);
            assertEquals("1", l_units[2].marginMaintenanceRate);
            
            assertEquals(null, l_units[3].additionalElapsedDaysUpperLimit);
            assertEquals("4", l_units[3].forcedSettleReason);
            assertEquals(null, l_units[3].marginMaintenanceRate);
            
            assertEquals(null, l_units[4].additionalElapsedDaysUpperLimit);
            assertEquals("3", l_units[4].forcedSettleReason);
            assertEquals(null, l_units[4].marginMaintenanceRate);
            
            assertEquals(null, l_units[5].additionalElapsedDaysUpperLimit);
            assertEquals("9", l_units[5].forcedSettleReason);
            assertEquals(null, l_units[5].marginMaintenanceRate);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
    }

    public void deleteAll()
    {
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info("*******************deleteAll***************** !!");
    }
}
@
