head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPChangeCalcControlServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTPChangeCalcControlServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/01 金傑（中訊）新規作成
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.PaymentRequisitMngDao;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPCalcControlInfo;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlRequest;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTPChangeCalcControlServiceImplTest extends TestBaseForMock
{

    private WEB3AdminTPChangeCalcControlServiceImpl l_serviceImpl = null;
    
    private List l_lisSearchResults = null;

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPChangeCalcControlServiceImplTest.class);
    
    public WEB3AdminTPChangeCalcControlServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_serviceImpl = new WEB3AdminTPChangeCalcControlServiceImpl();
        this.initData();
    }

    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        super.tearDown();
    }
    
    public void testSubmitChangeTradingPowerCalcControl_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitChangeTradingPowerCalcControl_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7, 2);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_BranchParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_TradingpowerCalcConditionParams.setCalcConditionId(4521365);
            l_TradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondSettlementNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondSettlement1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            l_PaymentRequisitMngParams.setSecondSettlement2(1);
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
           
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_admin,"A0201",true,true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_admin,"381",true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123",true);
            
            WEB3AdminTPChangeCalcControlCompleteRequest l_request = new WEB3AdminTPChangeCalcControlCompleteRequest();
            l_request.additionalDepositStop = "0";
            l_request.calcConditionId = "4521365";
            l_request.paymentStop = "1";
            l_request.otherTradingStop = "0";
            l_request.tradingStop = "1";
            l_request.adminPassword = "123";
            this.l_serviceImpl.submitChangeTradingPowerCalcControl(l_request);
            
            PaymentRequisitMngRow l_paymentRequisitMngRow = PaymentRequisitMngDao.findRowByPk(333812512246L);
            assertEquals("1.0",l_paymentRequisitMngRow.getSecondDepositNonPay()+"");
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitChangeTradingPowerCalcControl_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitChangeTradingPowerCalcControl_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7, 2);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_BranchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_BranchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_BranchPreferencesParams.setBranchId(l_BranchParams.getBranchId());
            l_BranchPreferencesParams.setName("deposit.clear.div");
            l_BranchPreferencesParams.setValue("1");
            l_BranchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_BranchPreferencesParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_TradingpowerCalcConditionParams.setCalcConditionId(4521365);
            l_TradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_TradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondSettlementNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondSettlement1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            l_PaymentRequisitMngParams.setSecondSettlement2(1);
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
           
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_admin);
            WEB3AdministratorForMock.mockValidateAuthority(l_admin,"A0201",true,true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_admin,"381",true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123",true);
            
            WEB3AdminTPChangeCalcControlCompleteRequest l_request = new WEB3AdminTPChangeCalcControlCompleteRequest();
            l_request.additionalDepositStop = "0";
            l_request.calcConditionId = "4521365";
            l_request.paymentStop = "1";
            l_request.otherTradingStop = "0";
            l_request.tradingStop = "1";
            l_request.adminPassword = "123";
            this.l_serviceImpl.submitChangeTradingPowerCalcControl(l_request);
            
            PaymentRequisitMngRow l_paymentRequisitMngRow = PaymentRequisitMngDao.findRowByPk(333812512246L);
            assertEquals("0.0",l_paymentRequisitMngRow.getSecondDepositNonPay()+"");
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 余力制御機@能検索.追証未入金区分 != nullの場合
     * sql:additional_deposit_stop = ?
     *
     */
    public void testGetTradingPowerCalcConditionParamsList_C0001()
    {
        final String STR_METHOD_NAME = "testGetTradingPowerCalcConditionParamsList_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.additionalDepositStop = "1";
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
            List l_lisResults = this.l_serviceImpl.getTradingPowerCalcConditionParamsList(l_request,l_admin);
            assertNotNull(l_lisResults);
            assertEquals(1,l_lisResults.size());
            // 追証未入金区分
            assertEquals("1",((TradingpowerCalcConditionRow)l_lisResults.get(0)).getAdditionalDepositStop());
        }
        catch(WEB3BaseException l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 余力制御機@能検索.追証未入金区分 != nullの場合
     * sql:account_id in (?) and (additional_deposit_stop = ?) and branch_id in (?)
     *
     */
    public void testGetTradingPowerCalcConditionParamsList_C0002()
    {
        final String STR_METHOD_NAME = "testGetTradingPowerCalcConditionParamsList_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.accountCode = "2512246";
            l_request.branchCodes = new String[1];
            l_request.branchCodes[0] = "381";
            l_request.additionalDepositStop = "1";
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
            List l_lisResults = this.l_serviceImpl.getTradingPowerCalcConditionParamsList(l_request,l_admin);
            assertNotNull(l_lisResults);
            assertEquals(1,l_lisResults.size());
            // 追証未入金区分
            assertEquals("1",((TradingpowerCalcConditionRow)l_lisResults.get(0)).getAdditionalDepositStop());
            assertEquals(333812512246L,((TradingpowerCalcConditionRow)l_lisResults.get(0)).getAccountId());
            assertEquals(33381,((TradingpowerCalcConditionRow)l_lisResults.get(0)).getBranchId());
        }
        catch(WEB3BaseException l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 余力制御機@能検索.追証未入金区分 != nullの場合
     * sql:account_id in (?) and (trading_stop = ? or additional_deposit_stop = ?) and branch_id in (?)
     *
     */
    public void testGetTradingPowerCalcConditionParamsList_C0003()
    {
        final String STR_METHOD_NAME = "testGetTradingPowerCalcConditionParamsList_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.accountCode = "2512246";
            l_request.branchCodes = new String[1];
            l_request.branchCodes[0] = "381";
            l_request.tradingStop = "1";
            l_request.additionalDepositStop = "1";
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
            List l_lisResults = this.l_serviceImpl.getTradingPowerCalcConditionParamsList(l_request,l_admin);
            assertNotNull(l_lisResults);
            assertEquals(1,l_lisResults.size());
            // 追証未入金区分
            assertEquals("1",((TradingpowerCalcConditionRow)l_lisResults.get(0)).getAdditionalDepositStop());
            assertEquals(333812512246L,((TradingpowerCalcConditionRow)l_lisResults.get(0)).getAccountId());
            assertEquals(33381,((TradingpowerCalcConditionRow)l_lisResults.get(0)).getBranchId());
            assertEquals("1",((TradingpowerCalcConditionRow)l_lisResults.get(0)).getTradingStop());
        }
        catch(WEB3BaseException l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *更新結果成功 
     *
     */
    public void testUpdateTradingPowerCalcConditionParams_C0001()
    {
        final String STR_METHOD_NAME = "testUpdateTradingPowerCalcConditionParams_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 7, 2);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3AdminTPChangeCalcControlCompleteRequest l_request = new WEB3AdminTPChangeCalcControlCompleteRequest();
            l_request.calcConditionId = "4521365";
            l_request.paymentStop = "1";
            l_request.otherTradingStop = "0";
            l_request.tradingStop = "1";
            l_request.additionalDepositStop = "1";
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
            Timestamp l_tsResult = this.l_serviceImpl.updateTradingPowerCalcConditionParams(l_request,l_admin);
            
            assertEquals(l_tsAppliyDate.getTime(),l_tsResult.getTime());
            
            this.getSearchResult();
            assertNotNull(l_lisSearchResults);
            assertEquals(1,l_lisSearchResults.size());
            assertEquals(4521365,((TradingpowerCalcConditionRow)l_lisSearchResults.get(0)).getCalcConditionId());
            assertEquals("1",((TradingpowerCalcConditionRow)l_lisSearchResults.get(0)).getPaymentStop());
            assertEquals("0",((TradingpowerCalcConditionRow)l_lisSearchResults.get(0)).getOtherTradingStop());
            assertEquals("1",((TradingpowerCalcConditionRow)l_lisSearchResults.get(0)).getTradingStop());
            assertEquals("1",((TradingpowerCalcConditionRow)l_lisSearchResults.get(0)).getAdditionalDepositStop());
            
        }
        catch(WEB3BaseException l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 顧客.is信用口座開設
     * 0:指定なしの場合
     *
     */
    public void testCreateCalcControlInfo_C0001()
    {
        final String STR_METHOD_NAME = "testCreateCalcControlInfo_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TradingpowerCalcConditionParams l_params = TestDBUtility.getTradingpowerCalcConditionRow();
            l_params.setAdditionalDepositStop("0");
            TestDBUtility.insertWithDel(l_params);

            Method method =
                WEB3AdminTPChangeCalcControlServiceImpl.class.getDeclaredMethod(
                "createCalcControlInfo",
                 new Class[]{WEB3GentradeMainAccount.class, TradingpowerCalcConditionRow.class});
            method.setAccessible(true);
            WEB3AdminTPCalcControlInfo l_calcControlInfo =
                (WEB3AdminTPCalcControlInfo)method.invoke(this.l_serviceImpl, new Object[]{l_account, l_params});

            assertEquals("0", l_calcControlInfo.additionalDepositStop);

        }
        catch (Exception l_ex)
        {
            log.debug("" + l_ex);
            fail();
        }

        log.entering(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 顧客.is信用口座開設
     * それ以外の場合
     *
     */
    public void testCreateCalcControlInfo_C0002()
    {
        final String STR_METHOD_NAME = "testCreateCalcControlInfo_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(l_mainAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TradingpowerCalcConditionRow l_row = TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(l_row);

            Method method =
                WEB3AdminTPChangeCalcControlServiceImpl.class.getDeclaredMethod(
                "createCalcControlInfo",
                 new Class[]{WEB3GentradeMainAccount.class, TradingpowerCalcConditionRow.class});
            method.setAccessible(true);
            WEB3AdminTPCalcControlInfo l_calcControlInfo =
                (WEB3AdminTPCalcControlInfo)method.invoke(this.l_serviceImpl, new Object[]{l_account, l_row});

            assertEquals(null, l_calcControlInfo.additionalDepositStop);

        }
        catch (Exception l_ex)
        {
            log.debug("" + l_ex);
            fail();
        }

        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 顧客コード != nullの場合
     * 
     * sql:account_id in (?,?,?) and branch_id in (?,?,?)
     * 
     * 
     */
    public void testGetTradingPowerCalcConditionParamsList_C0004()
    {
        final String STR_METHOD_NAME = "testGetTradingPowerCalcConditionParamsList_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(100);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setInstitutionId(10);
            l_mainAccountParams1.setBranchCode("001");
            l_mainAccountParams1.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(200);
            l_mainAccountParams2.setInstitutionCode("0D");
            l_mainAccountParams2.setInstitutionId(20);
            l_mainAccountParams2.setBranchCode("002");
            l_mainAccountParams2.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams2);
            
            MainAccountParams l_mainAccountParams3 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams3.setAccountId(300);
            l_mainAccountParams3.setInstitutionCode("0D");
            l_mainAccountParams3.setInstitutionId(30);
            l_mainAccountParams3.setBranchCode("003");
            l_mainAccountParams3.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams3);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setInstitutionId(10);
            l_branchParams1.setBranchCode("001");
            l_branchParams1.setBranchId(1);
            
            TestDBUtility.insertWithDel(l_branchParams1);
            
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setInstitutionCode("0D");
            l_branchParams2.setInstitutionId(20);
            l_branchParams2.setBranchCode("002");
            l_branchParams2.setBranchId(2);
            
            TestDBUtility.insertWithDel(l_branchParams2);
            
            BranchParams l_branchParams3 = TestDBUtility.getBranchRow();
            l_branchParams3.setInstitutionCode("0D");
            l_branchParams3.setInstitutionId(30);
            l_branchParams3.setBranchCode("003");
            l_branchParams3.setBranchId(3);
            
            TestDBUtility.insertWithDel(l_branchParams3);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam1 =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam1.setCalcConditionId(1000);
            l_tradingpowerCalcConditionParam1.setAccountId(100);
            l_tradingpowerCalcConditionParam1.setBranchId(1);
            l_tradingpowerCalcConditionParam1.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParam1.setTradingStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam1);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam2 =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam2.setCalcConditionId(2000);
            l_tradingpowerCalcConditionParam2.setAccountId(200);
            l_tradingpowerCalcConditionParam2.setBranchId(2);
            l_tradingpowerCalcConditionParam2.setAdditionalDepositStop("2");
            l_tradingpowerCalcConditionParam2.setTradingStop("2");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam2);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam3 =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam3.setCalcConditionId(3000);
            l_tradingpowerCalcConditionParam3.setAccountId(300);
            l_tradingpowerCalcConditionParam3.setBranchId(3);
            l_tradingpowerCalcConditionParam3.setAdditionalDepositStop("3");
            l_tradingpowerCalcConditionParam3.setTradingStop("3");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam3);
            
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.accountCode = "11";
            l_request.branchCodes = new String[]{"001","002","003"};
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
            List l_lisResults = this.l_serviceImpl.getTradingPowerCalcConditionParamsList(l_request,l_admin);
            
            assertNotNull(l_lisResults);
            assertEquals(3,l_lisResults.size());
            assertEquals("300","" + ((TradingpowerCalcConditionRow)l_lisResults.get(0)).getAccountId());
            assertEquals("200","" + ((TradingpowerCalcConditionRow)l_lisResults.get(1)).getAccountId());
            assertEquals("100","" + ((TradingpowerCalcConditionRow)l_lisResults.get(2)).getAccountId());
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
        }
        catch(WEB3BaseException l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 顧客コード != nullの場合
     * 
     * sql:account_id in (?,?,?) and branch_id in (?,?,?)
     * 
     * WEB3ErrorCatalog.BUSINESS_ERROR_01987
     */
    public void testGetTradingPowerCalcConditionParamsList_C0005()
    {
        final String STR_METHOD_NAME = "testGetTradingPowerCalcConditionParamsList_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(100);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setInstitutionId(10);
            l_mainAccountParams1.setBranchCode("001");
            l_mainAccountParams1.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(200);
            l_mainAccountParams2.setInstitutionCode("0D");
            l_mainAccountParams2.setInstitutionId(20);
            l_mainAccountParams2.setBranchCode("002");
            l_mainAccountParams2.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams2);
            
            MainAccountParams l_mainAccountParams3 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams3.setAccountId(300);
            l_mainAccountParams3.setInstitutionCode("0D");
            l_mainAccountParams3.setInstitutionId(30);
            l_mainAccountParams3.setBranchCode("003");
            l_mainAccountParams3.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams3);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setInstitutionId(10);
            l_branchParams1.setBranchCode("001");
            l_branchParams1.setBranchId(1);
            
            TestDBUtility.insertWithDel(l_branchParams1);
            
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setInstitutionCode("0D");
            l_branchParams2.setInstitutionId(20);
            l_branchParams2.setBranchCode("002");
            l_branchParams2.setBranchId(2);
            
            TestDBUtility.insertWithDel(l_branchParams2);
            
            BranchParams l_branchParams3 = TestDBUtility.getBranchRow();
            l_branchParams3.setInstitutionCode("0D");
            l_branchParams3.setInstitutionId(30);
            l_branchParams3.setBranchCode("003");
            l_branchParams3.setBranchId(3);
            
            TestDBUtility.insertWithDel(l_branchParams3);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam1 =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam1.setCalcConditionId(1000);
            l_tradingpowerCalcConditionParam1.setAccountId(100);
            l_tradingpowerCalcConditionParam1.setBranchId(1);
            l_tradingpowerCalcConditionParam1.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParam1.setTradingStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam1);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam2 =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam2.setCalcConditionId(2000);
            l_tradingpowerCalcConditionParam2.setAccountId(200);
            l_tradingpowerCalcConditionParam2.setBranchId(2);
            l_tradingpowerCalcConditionParam2.setAdditionalDepositStop("2");
            l_tradingpowerCalcConditionParam2.setTradingStop("2");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam2);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam3 =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam3.setCalcConditionId(3000);
            l_tradingpowerCalcConditionParam3.setAccountId(300);
            l_tradingpowerCalcConditionParam3.setBranchId(3);
            l_tradingpowerCalcConditionParam3.setAdditionalDepositStop("3");
            l_tradingpowerCalcConditionParam3.setTradingStop("3");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam3);
            
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.accountCode = "22";
            l_request.branchCodes = new String[]{"001","002","003"};
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
            List l_lisResults = this.l_serviceImpl.getTradingPowerCalcConditionParamsList(l_request,l_admin);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01987,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 顧客コード != nullの場合
     * 
     * sql:account_id in (?,?) and branch_id in (?,?,?)
     * 
     * 
     */
    public void testGetTradingPowerCalcConditionParamsList_C0006()
    {
        final String STR_METHOD_NAME = "testGetTradingPowerCalcConditionParamsList_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(100);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setInstitutionId(10);
            l_mainAccountParams1.setBranchCode("001");
            l_mainAccountParams1.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(200);
            l_mainAccountParams2.setInstitutionCode("0D");
            l_mainAccountParams2.setInstitutionId(20);
            l_mainAccountParams2.setBranchCode("002");
            l_mainAccountParams2.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams2);
            
            MainAccountParams l_mainAccountParams3 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams3.setAccountId(300);
            l_mainAccountParams3.setInstitutionCode("0D");
            l_mainAccountParams3.setInstitutionId(30);
            l_mainAccountParams3.setBranchCode("003");
            l_mainAccountParams3.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams3);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setInstitutionId(10);
            l_branchParams1.setBranchCode("004");
            l_branchParams1.setBranchId(1);
            
            TestDBUtility.insertWithDel(l_branchParams1);
            
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setInstitutionCode("0D");
            l_branchParams2.setInstitutionId(20);
            l_branchParams2.setBranchCode("002");
            l_branchParams2.setBranchId(2);
            
            TestDBUtility.insertWithDel(l_branchParams2);
            
            BranchParams l_branchParams3 = TestDBUtility.getBranchRow();
            l_branchParams3.setInstitutionCode("0D");
            l_branchParams3.setInstitutionId(30);
            l_branchParams3.setBranchCode("003");
            l_branchParams3.setBranchId(3);
            
            TestDBUtility.insertWithDel(l_branchParams3);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam1 =TestDBUtility.getTradingpowerCalcConditionRow();
//            l_tradingpowerCalcConditionParam1.setCalcConditionId(1000);
//            l_tradingpowerCalcConditionParam1.setAccountId(100);
//            l_tradingpowerCalcConditionParam1.setBranchId(1);
//            l_tradingpowerCalcConditionParam1.setAdditionalDepositStop("1");
//            l_tradingpowerCalcConditionParam1.setTradingStop("1");
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam1);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam2 =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam2.setCalcConditionId(2000);
            l_tradingpowerCalcConditionParam2.setAccountId(200);
            l_tradingpowerCalcConditionParam2.setBranchId(2);
            l_tradingpowerCalcConditionParam2.setAdditionalDepositStop("2");
            l_tradingpowerCalcConditionParam2.setTradingStop("2");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam2);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam3 =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam3.setCalcConditionId(3000);
            l_tradingpowerCalcConditionParam3.setAccountId(300);
            l_tradingpowerCalcConditionParam3.setBranchId(3);
            l_tradingpowerCalcConditionParam3.setAdditionalDepositStop("3");
            l_tradingpowerCalcConditionParam3.setTradingStop("3");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam3);
            
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.accountCode = "11";
            l_request.branchCodes = new String[]{"004","002","003"};
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
            List l_lisResults = this.l_serviceImpl.getTradingPowerCalcConditionParamsList(l_request,l_admin);
            
            assertNotNull(l_lisResults);
            assertEquals(2,l_lisResults.size());
            assertEquals("300","" + ((TradingpowerCalcConditionRow)l_lisResults.get(0)).getAccountId());
            assertEquals("200","" + ((TradingpowerCalcConditionRow)l_lisResults.get(1)).getAccountId());
//            assertEquals("100","" + ((TradingpowerCalcConditionRow)l_lisResults.get(2)).getAccountId());
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
        }
        catch(WEB3BaseException l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 顧客コード != nullの場合
     * 
     * sql:account_id in (?) and branch_id in (?,?,?)
     * 
     * 
     */
    public void testGetTradingPowerCalcConditionParamsList_C0007()
    {
        final String STR_METHOD_NAME = "testGetTradingPowerCalcConditionParamsList_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(100);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setInstitutionId(10);
            l_mainAccountParams1.setBranchCode("001");
            l_mainAccountParams1.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(200);
            l_mainAccountParams2.setInstitutionCode("0D");
            l_mainAccountParams2.setInstitutionId(20);
            l_mainAccountParams2.setBranchCode("004");
            l_mainAccountParams2.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams2);
            
            MainAccountParams l_mainAccountParams3 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams3.setAccountId(300);
            l_mainAccountParams3.setInstitutionCode("0D");
            l_mainAccountParams3.setInstitutionId(30);
            l_mainAccountParams3.setBranchCode("005");
            l_mainAccountParams3.setAccountCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams3);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionCode("0D");
            l_branchParams1.setInstitutionId(10);
            l_branchParams1.setBranchCode("001");
            l_branchParams1.setBranchId(1);
            
            TestDBUtility.insertWithDel(l_branchParams1);
            
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setInstitutionCode("0D");
            l_branchParams2.setInstitutionId(20);
            l_branchParams2.setBranchCode("002");
            l_branchParams2.setBranchId(2);
            
            TestDBUtility.insertWithDel(l_branchParams2);
            
            BranchParams l_branchParams3 = TestDBUtility.getBranchRow();
            l_branchParams3.setInstitutionCode("0D");
            l_branchParams3.setInstitutionId(30);
            l_branchParams3.setBranchCode("003");
            l_branchParams3.setBranchId(3);
            
            TestDBUtility.insertWithDel(l_branchParams3);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam1 =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam1.setCalcConditionId(1000);
            l_tradingpowerCalcConditionParam1.setAccountId(100);
            l_tradingpowerCalcConditionParam1.setBranchId(1);
            l_tradingpowerCalcConditionParam1.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParam1.setTradingStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam1);
            
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam2 =TestDBUtility.getTradingpowerCalcConditionRow();
//            l_tradingpowerCalcConditionParam2.setCalcConditionId(2000);
//            l_tradingpowerCalcConditionParam2.setAccountId(200);
//            l_tradingpowerCalcConditionParam2.setBranchId(2);
//            l_tradingpowerCalcConditionParam2.setAdditionalDepositStop("2");
//            l_tradingpowerCalcConditionParam2.setTradingStop("2");
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam2);
            
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam3 =TestDBUtility.getTradingpowerCalcConditionRow();
//            l_tradingpowerCalcConditionParam3.setCalcConditionId(3000);
//            l_tradingpowerCalcConditionParam3.setAccountId(300);
//            l_tradingpowerCalcConditionParam3.setBranchId(3);
//            l_tradingpowerCalcConditionParam3.setAdditionalDepositStop("3");
//            l_tradingpowerCalcConditionParam3.setTradingStop("3");
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam3);
            
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.accountCode = "11";
            l_request.branchCodes = new String[]{"001","002","003"};
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
            List l_lisResults = this.l_serviceImpl.getTradingPowerCalcConditionParamsList(l_request,l_admin);
            
            assertNotNull(l_lisResults);
            assertEquals(1,l_lisResults.size());
//            assertEquals("300","" + ((TradingpowerCalcConditionRow)l_lisResults.get(0)).getAccountId());
//            assertEquals("200","" + ((TradingpowerCalcConditionRow)l_lisResults.get(1)).getAccountId());
            assertEquals("100","" + ((TradingpowerCalcConditionRow)l_lisResults.get(0)).getAccountId());
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
        }
        catch(WEB3BaseException l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        catch(Exception l_ex)
        {
           log.debug(""+l_ex);
           fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 余力制御機@能検索リクエスト.信用新規建余力区分のみにチェックがついている場合
     * 信用客である場合、信用新規建停止区分にtrueを設定する
     *
     */
    public void testIsShowableAccount_0001()
    {
        final String STR_METHOD_NAME = "testIsShowableAccount_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.marginOpenPositionStop = "1";

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccountForTest l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionRow l_row = TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(l_row);

            assertTrue(l_serviceImpl.isShowableAccount(l_request, l_account, l_row));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 余力制御機@能検索リクエスト.先物OP新規建余力区分のみ選択されている場合
     * オプション客である場合、オプション新規建停止区分にtrueを設定する
     *
     */
    public void testIsShowableAccount_0002()
    {
        final String STR_METHOD_NAME = "testIsShowableAccount_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.ifoOpenPositionStop = "1";

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.ifo_acc_open_div_nagoya = "1";
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccountForTest l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionRow l_row = TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(l_row);

            assertTrue(l_serviceImpl.isShowableAccount(l_request, l_account, l_row));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 余力制御機@能検索リクエスト.追証未入金区分のみにチェックがついている場合
     * 信用客である場合、追証未入金区分にtrueを設定する
     *
     */
    public void testIsShowableAccount_0003()
    {
        final String STR_METHOD_NAME = "testIsShowableAccount_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.additionalDepositStop = "1";

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccountForTest l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);

            assertTrue(l_serviceImpl.isShowableAccount(l_request, l_account, l_tradingpowerCalcConditionParam));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 余力制御機@能検索リクエスト.取引停止区分が選択されている場合
     * 余力停止区分にtrueを設定する
     *
     */
    public void testIsShowableAccount_0004()
    {
        final String STR_METHOD_NAME = "testIsShowableAccount_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.tradingStop = "1";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccountForTest l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionRow l_row = TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(l_row);
            
            assertTrue(l_serviceImpl.isShowableAccount(l_request, l_account, l_row));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 余力制御機@能検索リクエスト.出金余力区分が選択されている場合
     * 出金停止区分にtrueを設定する
     * 
     */
    public void testIsShowableAccount_0005()
    {
        final String STR_METHOD_NAME = "testIsShowableAccount_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.paymentStop = "1";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccountForTest l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionRow l_row = TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(l_row);
            
            assertTrue(l_serviceImpl.isShowableAccount(l_request, l_account, l_row));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 余力制御機@能検索リクエスト.その他商品買付区分が選択されている場合 
     * その他商品買付停止区分にtrueを設定する
     * 
     */
    public void testIsShowableAccount_0006()
    {
        final String STR_METHOD_NAME = "testIsShowableAccount_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.otherTradingStop = "1";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccountForTest l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionRow l_row = TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(l_row);
            
            assertTrue(l_serviceImpl.isShowableAccount(l_request, l_account, l_row));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsShowableAccount_0007()
    {
        final String STR_METHOD_NAME = "testIsShowableAccount_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.accountCode = "2200";
            l_request.branchCodes = null;
            l_request.marginOpenPositionStop = null;
            l_request.ifoOpenPositionStop = null;
            l_request.tradingStop = null;
            l_request.paymentStop = null;
            l_request.otherTradingStop = null;
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccountForTest l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionRow l_row = TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(l_row);

            assertTrue(l_serviceImpl.isShowableAccount(l_request, l_account, l_row));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsShowableAccount_0008()
    {
        final String STR_METHOD_NAME = "testIsShowableAccount_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.accountCode = null;
            l_request.branchCodes = new String[1];
            l_request.marginOpenPositionStop = null;
            l_request.ifoOpenPositionStop = null;
            l_request.tradingStop = null;
            l_request.paymentStop = null;
            l_request.otherTradingStop = null;

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccountForTest l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionRow l_row = TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(l_row);

            assertTrue(l_serviceImpl.isShowableAccount(l_request, l_account, l_row));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsShowableAccount_0009()
    {
        final String STR_METHOD_NAME = "testIsShowableAccount_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTPFindCalcControlRequest l_request = new WEB3AdminTPFindCalcControlRequest();
            l_request.accountCode = null;
            l_request.branchCodes = null;
            l_request.marginOpenPositionStop = null;
            l_request.ifoOpenPositionStop = null;
            l_request.tradingStop = null;
            l_request.paymentStop = null;
            l_request.otherTradingStop = null;

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccountForTest l_account = new WEB3GentradeMainAccountForTest(l_mainAccountParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionRow l_row = TestDBUtility.getTradingpowerCalcConditionRow();
            TestDBUtility.insertWithDel(l_row);

            assertFalse(l_serviceImpl.isShowableAccount(l_request, l_account, l_row));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParam.setTradingStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private List getSearchResult()
    {
        final String STR_METHOD_NAME = "getSearchResult()";
        log.entering(TEST_START + STR_METHOD_NAME);
        List lisSearchResult = null;
        try
        {
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" calc_condition_id = ? ");
            
            Object[] l_objWheres = {new Long(4521365L)};
            
            
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            this.l_lisSearchResults = l_queryProcesser.doFindAllQuery(
                TradingpowerCalcConditionRow.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
        return lisSearchResult;
    }
    
    private class WEB3GentradeMainAccountForTest extends WEB3GentradeMainAccount
    {
        public WEB3GentradeMainAccountForTest(MainAccountRow l_row)
        {
            super(l_row);
        }
        public boolean isMarginAccountEstablished(String l_strRepaymentType)
        {
            return true;
        }
    }
}
@
