head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminBondExecuteReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/14 �����Q �V�K�쐬
*/
package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;

import test.util.TestDBUtility;

import webbroker3.adminorderexecinquiry.WEB3AdminProductExecutionInfo;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORTradingProductUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityExecuteReferenceServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityExecuteReferenceServiceImplTest.class); 

    WEB3AdminEquityExecuteReferenceServiceImpl l_adminEquityExecuteReferenceServiceImpl = 
        new WEB3AdminEquityExecuteReferenceServiceImpl();

    public WEB3AdminEquityExecuteReferenceServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetInputScreen_C0001()
    {
        final String STR_METHOD_NAME = "test_createExecReferenceUnit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[]{},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[]{},
            new Long(33381330003L));
        try
        {
            //�X�^�e�B�b�N���\�b�h�̏���
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 0);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 3);
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_canlendar.getTimeInMillis()));

            //�f�[�^�x�[�X�փf�[�^���C���T�[�g
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0103");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_institutionParams.getRowType());
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(l_branchParams.getRowType());
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setMarginSysDiv("0");
            l_branchParams.setMarginGenDiv("0");
            l_branchParams.setMstkDiv("0");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams = new BranchMarketDealtCondParams();
            l_processor.doDeleteAllQuery(l_branchMarketDealtCondParams.getRowType());
            l_branchMarketDealtCondParams = TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMartCanDealtEquity("0");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);

            SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
            l_systemPreferencesParams.setName("system.bizdate");
            l_processor.doDeleteAllQuery(l_systemPreferencesParams.getRowType());

            EnableOrderConditionParams l_enableOrderConditionParams = new EnableOrderConditionParams();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_enableOrderConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            //���ۃ��\�b�h���R�[��
            WEB3AdminOREquityOrderExecutionRefInputRequest l_adminOREquityOrderExecutionRefInputRequest =
                new WEB3AdminOREquityOrderExecutionRefInputRequestForTest();
            l_adminOREquityOrderExecutionRefInputRequest.branchCode = new String[]{"381"};
            WEB3AdminOREquityOrderExecutionRefInputResponse l_response = l_adminEquityExecuteReferenceServiceImpl.
                getInputScreen(l_adminOREquityOrderExecutionRefInputRequest);

            //��r
            assertEquals(0, WEB3DateUtility.compareToDay(l_response.orderBizDateList[0],
                WEB3DateUtility.getDate("2007/01/02", "yyyy/MM/dd")));
            assertEquals(0, WEB3DateUtility.compareToDay(l_response.orderBizDateList[1],
                WEB3DateUtility.getDate("2007/01/03", "yyyy/MM/dd")));
            assertEquals(0, WEB3DateUtility.compareToDay(l_response.orderBizDateList[2],
                WEB3DateUtility.getDate("2007/01/04", "yyyy/MM/dd")));
            assertEquals(l_response.priceCondList[0], "0");
            assertEquals(l_response.execCondList[0], "1");
            assertEquals(l_response.expirationDateTypeList[0], "1");
            assertEquals(l_response.orderCondTypeList[0], "0");
            assertEquals("1", l_response.orderRootList[0]);
            assertEquals("2", l_response.orderRootList[1]);
            assertEquals("3", l_response.orderRootList[2]);
            assertEquals("4", l_response.orderRootList[3]);
            assertEquals("5", l_response.orderRootList[4]);
            assertEquals("6", l_response.orderRootList[5]);
            assertEquals("9", l_response.orderRootList[6]);
            assertEquals("C", l_response.orderRootList[7]);
            assertEquals("F", l_response.orderRootList[8]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetInputScreen_C0002()
    {
        final String STR_METHOD_NAME = "test_createExecReferenceUnit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[]{},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[]{},
            new Long(33381330003L));
        try
        {
            //�X�^�e�B�b�N���\�b�h�̏���
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 0);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 3);
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_canlendar.getTimeInMillis()));

            //�f�[�^�x�[�X�փf�[�^���C���T�[�g
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0103");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_institutionParams.getRowType());
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(l_branchParams.getRowType());
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setMarginSysDiv("0");
            l_branchParams.setMarginGenDiv("0");
            l_branchParams.setMstkDiv("0");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams = new BranchMarketDealtCondParams();
            l_processor.doDeleteAllQuery(l_branchMarketDealtCondParams.getRowType());
            l_branchMarketDealtCondParams = TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMartCanDealtEquity("0");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);

            SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
            l_systemPreferencesParams.setName("system.bizdate");
            l_processor.doDeleteAllQuery(l_systemPreferencesParams.getRowType());

            EnableOrderConditionParams l_enableOrderConditionParams = new EnableOrderConditionParams();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_enableOrderConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            //���ۃ��\�b�h���R�[��
            WEB3AdminOREquityOrderExecutionRefInputRequest l_adminOREquityOrderExecutionRefInputRequest =
                new WEB3AdminOREquityOrderExecutionRefInputRequestForTest();
            l_adminOREquityOrderExecutionRefInputRequest.branchCode = new String[]{"381"};
            WEB3AdminOREquityOrderExecutionRefInputResponse l_response = l_adminEquityExecuteReferenceServiceImpl.
                getInputScreen(l_adminOREquityOrderExecutionRefInputRequest);

            //��r
            assertEquals(0, WEB3DateUtility.compareToDay(l_response.orderBizDateList[0],
                WEB3DateUtility.getDate("2007/01/02", "yyyy/MM/dd")));
            assertEquals(0, WEB3DateUtility.compareToDay(l_response.orderBizDateList[1],
                WEB3DateUtility.getDate("2007/01/03", "yyyy/MM/dd")));
            assertEquals(0, WEB3DateUtility.compareToDay(l_response.orderBizDateList[2],
                WEB3DateUtility.getDate("2007/01/04", "yyyy/MM/dd")));
            assertEquals(l_response.priceCondList[0], "0");
            assertEquals(l_response.execCondList[0], "1");
            assertEquals(l_response.expirationDateTypeList[0], "1");
            assertEquals(l_response.orderCondTypeList[0], "0");
            assertEquals("1", l_response.orderRootList[0]);
            assertEquals("2", l_response.orderRootList[1]);
            assertEquals("3", l_response.orderRootList[2]);
            assertEquals("4", l_response.orderRootList[3]);
            assertEquals("5", l_response.orderRootList[4]);
            assertEquals("6", l_response.orderRootList[5]);
            assertEquals("9", l_response.orderRootList[6]);
            assertEquals("C", l_response.orderRootList[7]);
            assertEquals("F", l_response.orderRootList[8]);
            assertEquals("G", l_response.orderRootList[9]);
            assertFalse(l_response.forcedSettleEnforcementFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_C0003()
    {
        final String STR_METHOD_NAME = "test_createExecReferenceUnit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[]{},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[]{},
            new Long(33381330003L));
        try
        {
            //�X�^�e�B�b�N���\�b�h�̏���
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 0);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_canlendar.getTimeInMillis()));

            //�f�[�^�x�[�X�փf�[�^���C���T�[�g
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0103");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_institutionParams.getRowType());
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setForcedsettleorderDiv("1");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(l_branchParams.getRowType());
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setMarginSysDiv("1");
            l_branchParams.setMarginGenDiv("1");
            l_branchParams.setMstkDiv("0");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams = new BranchMarketDealtCondParams();
            l_processor.doDeleteAllQuery(l_branchMarketDealtCondParams.getRowType());
            l_branchMarketDealtCondParams = TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMartCanDealtEquity("0");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);

            SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
            l_systemPreferencesParams.setName("system.bizdate");
            l_processor.doDeleteAllQuery(l_systemPreferencesParams.getRowType());

            EnableOrderConditionParams l_enableOrderConditionParams = new EnableOrderConditionParams();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_enableOrderConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            //���ۃ��\�b�h���R�[��
            WEB3AdminOREquityOrderExecutionRefInputRequest l_adminOREquityOrderExecutionRefInputRequest =
                new WEB3AdminOREquityOrderExecutionRefInputRequestForTest();
            l_adminOREquityOrderExecutionRefInputRequest.branchCode = new String[]{"381"};
            WEB3AdminOREquityOrderExecutionRefInputResponse l_response = l_adminEquityExecuteReferenceServiceImpl.
                getInputScreen(l_adminOREquityOrderExecutionRefInputRequest);
            assertTrue(l_response.forcedSettleEnforcementFlag);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateEquityOrderExecutionRefReferenceUnitList_T01()
    {
        final String STR_METHOD_NAME = "test_createExecReferenceUnit_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            l_eqtypeOrderUnitParams.setSonarTradedCode("12");
            l_eqtypeOrderUnitParams.setTraderId(null);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setForcedExpireType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit =
                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            EqTypeOrderUnit[] l_orderUnits = new EqTypeOrderUnit[]{l_eqTypeOrderUnit};
            WEB3AdminOREquityOrderExecutionRefUnit[] l_resultUnit =
                l_adminEquityExecuteReferenceServiceImpl.createEquityOrderExecutionRefReferenceUnitList(
                    l_orderUnits);
            assertEquals(1, l_resultUnit.length);
            assertEquals("0", l_resultUnit[0].forcedSettleReason);
            assertEquals("1", l_resultUnit[0].forcedLapseDiv);
            log.debug(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.debug(l_exc.getMessage(), l_exc);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_case0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityExecuteReferenceServiceImpl l_impl =
                new WEB3AdminEquityExecuteReferenceServiceImpl();
            String l_strCreateQuetyString =
                l_impl.createQueryString(null, null, null, null, null, null, null, true);
            assertEquals(l_strCreateQuetyString, " and forced_settle_reason_type is not null ");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testCreateQueryString_case0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityExecuteReferenceServiceImpl l_impl =
                new WEB3AdminEquityExecuteReferenceServiceImpl();
            String l_strCreateQuetyString =
                l_impl.createQueryString(null, null, null, null, null, null, "sda", false);
            assertEquals(l_strCreateQuetyString, " and nvl(org_order_condition_type,order_condition_type) = ? ");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * get�戵�\�s�� 
        get�戵�\�s��
        �P�j(���X�s���)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B
        �Q�j(���X�s��ʁEPTS)�戵����.get�戵�\�s��()���R�[�����A�s��R�[�h�̔z����擾����B 
        �P�j�̌��ʂƂQ�j�̌��ʂ��}�[�W���Ďs��R�[�h�����Ń\�[�g����B
     */
    public void testGetHandlingPossibleMarketCase0001()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleMarketCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("6");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);
            
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams1.setMarketCode("5");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams2.setMarketCode("6");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);
            
            WEB3AdminEquityExecuteReferenceServiceImpl l_impl =
                new WEB3AdminEquityExecuteReferenceServiceImpl();
            
            String[] l_strHandlingPossibleMarkets =
                l_impl.getHandlingPossibleMarket("0D", ProductTypeEnum.EQUITY);

            assertEquals(l_strHandlingPossibleMarkets[0], "1");
            assertEquals(l_strHandlingPossibleMarkets[1], "2");
            assertEquals(l_strHandlingPossibleMarkets[2], "5");
            assertEquals(l_strHandlingPossibleMarkets[3], "6");
            assertEquals(l_strHandlingPossibleMarkets[4], "11");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����������Ɖ���͉�ʕ\���������s���B
     get�戵�\�s��(String, ProductTypeEnum)
     */
    public void testGetInputScreenCase0001()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[]{},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[]{},
            new Long(33381330003L));
        try
        {
            //�X�^�e�B�b�N���\�b�h�̏���
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 0);
            l_canlendar.set(Calendar.DAY_OF_YEAR, 2);
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_canlendar.getTimeInMillis()));

            //�f�[�^�x�[�X�փf�[�^���C���T�[�g
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0103");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_institutionParams.getRowType());
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(l_branchParams.getRowType());
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setMarginSysDiv("0");
            l_branchParams.setMarginGenDiv("0");
            l_branchParams.setMstkDiv("0");
            TestDBUtility.insertWithDel(l_branchParams);

            BranchMarketDealtCondParams l_branchMarketDealtCondParams = new BranchMarketDealtCondParams();
            l_processor.doDeleteAllQuery(l_branchMarketDealtCondParams.getRowType());
            l_branchMarketDealtCondParams = TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);

            SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
            l_systemPreferencesParams.setName("system.bizdate");
            l_processor.doDeleteAllQuery(l_systemPreferencesParams.getRowType());

            EnableOrderConditionParams l_enableOrderConditionParams = new EnableOrderConditionParams();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_enableOrderConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            //���ۃ��\�b�h���R�[��
            WEB3AdminOREquityOrderExecutionRefInputRequest l_adminOREquityOrderExecutionRefInputRequest =
                new WEB3AdminOREquityOrderExecutionRefInputRequestForTest();
            l_adminOREquityOrderExecutionRefInputRequest.branchCode = new String[]{"381"};
            WEB3AdminOREquityOrderExecutionRefInputResponse l_response = l_adminEquityExecuteReferenceServiceImpl.
                getInputScreen(l_adminOREquityOrderExecutionRefInputRequest);

            String[] l_strMarketCodes = l_response.marketCodeList;
            assertEquals(l_strMarketCodes[0], "1");
            assertEquals(l_strMarketCodes[1], "11");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * PTS�s��łȂ��ꍇ�igetMarket()�̖߂�l.isPTS�s��() == false�j
    �@@�E�o�����͉\�t���O
    �@@�E�o������\�t���O
       = false
     */
    public void testCreateEquityOrderExecutionRefReferenceUnitListCase0001()
    {
        final String STR_METHOD_NAME = "testCreateEquityOrderExecutionRefReferenceUnitListCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            l_eqtypeOrderUnitParams.setSonarTradedCode("12");
            l_eqtypeOrderUnitParams.setTraderId(null);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setForcedExpireType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit =
                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            EqTypeOrderUnit[] l_orderUnits = new EqTypeOrderUnit[]{l_eqTypeOrderUnit};
            WEB3AdminOREquityOrderExecutionRefUnit[] l_resultUnit =
                l_adminEquityExecuteReferenceServiceImpl.createEquityOrderExecutionRefReferenceUnitList(
                    l_orderUnits);
            assertEquals(1, l_resultUnit.length);
            
            assertFalse(l_resultUnit[0].inputExecFlag);
            assertFalse(l_resultUnit[0].cancelExecFlag);
        }
        catch(Exception l_exc)
        {
            log.debug(l_exc.getMessage(), l_exc);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
        PTS�s��łȂ��ꍇ�igetMarket()�̖߂�l.isPTS�s��() == true�j
        �S�����i�����P��.isFullyExecuted() == true�j�̏ꍇ
        �����i�����P��.isUnexecuted() == false�j�̏ꍇ
        �@@�E�o�����͉\�t���O
        �@@�E�o������\�t���O
        = false
     */
    public void testCreateEquityOrderExecutionRefReferenceUnitListCase0002()
    {
        final String STR_METHOD_NAME = "testCreateEquityOrderExecutionRefReferenceUnitListCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            l_eqtypeOrderUnitParams.setSonarTradedCode("12");
            l_eqtypeOrderUnitParams.setTraderId(null);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setForcedExpireType("1");
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit =
                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            EqTypeOrderUnit[] l_orderUnits = new EqTypeOrderUnit[]{l_eqTypeOrderUnit};
            WEB3AdminOREquityOrderExecutionRefUnit[] l_resultUnit =
                l_adminEquityExecuteReferenceServiceImpl.createEquityOrderExecutionRefReferenceUnitList(
                    l_orderUnits);
            assertEquals(1, l_resultUnit.length);
            
            assertFalse(l_resultUnit[0].inputExecFlag);
            assertTrue(l_resultUnit[0].cancelExecFlag);
        }
        catch(Exception l_exc)
        {
            log.debug(l_exc.getMessage(), l_exc);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
    PTS�s��łȂ��ꍇ�igetMarket()�̖߂�l.isPTS�s��() == true�j
    �S�����i�����P��.isFullyExecuted() == false�j�̏ꍇ
    �����i�����P��.isUnexecuted() == true�j�̏ꍇ
    �@@�E�o�����͉\�t���O
    �@@�E�o������\�t���O
    = false
     */
    public void testCreateEquityOrderExecutionRefReferenceUnitListCase0003()
    {
        final String STR_METHOD_NAME = "testCreateEquityOrderExecutionRefReferenceUnitListCase0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            l_eqtypeOrderUnitParams.setSonarTradedCode("12");
            l_eqtypeOrderUnitParams.setTraderId(null);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
            l_eqtypeOrderUnitParams.setForcedExpireType("1");
            l_eqtypeOrderUnitParams.setExecutedQuantity(0.0);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit =
                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());
    
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
    
            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            EqTypeOrderUnit[] l_orderUnits = new EqTypeOrderUnit[]{l_eqTypeOrderUnit};
            WEB3AdminOREquityOrderExecutionRefUnit[] l_resultUnit =
                l_adminEquityExecuteReferenceServiceImpl.createEquityOrderExecutionRefReferenceUnitList(
                    l_orderUnits);
            assertEquals(1, l_resultUnit.length);
            
            assertTrue(l_resultUnit[0].inputExecFlag);
            assertFalse(l_resultUnit[0].cancelExecFlag);
        }
        catch(Exception l_exc)
        {
            log.debug(l_exc.getMessage(), l_exc);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3AdminOREquityOrderExecutionRefInputRequestForTest
        extends WEB3AdminOREquityOrderExecutionRefInputRequest
    {
        public void validate() throws WEB3BaseException
        {
            return;
        }

        protected WEB3AdminORTradingProductUnit[] createTradingProductList(
            WEB3AdminProductExecutionInfo l_productExecutionInfo)
        {
            return null;
        }
    }
}
@
