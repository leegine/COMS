head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToIfoOrderReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminToIfoOrderReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/05 孫洪江 (中訊) 新規作成
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitParams;
import webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitRow;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderRefSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminToIfoOrderReferenceServiceImplTest extends TestBaseForMock
{
    private IfoOrderUnit[] l_orderUnitList = null;
    private WEB3AdminToIfoOrderRefRefRequest l_request = null;
    private WEB3AdminToIfoOrderReferenceServiceImpl l_service = null;
    private WEB3AdminToIfoOrderRefUnit[] l_orderRefUnit = null;
    private long l_orderUnitId = 1001;
    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminToIfoOrderReferenceServiceImplTest.class);

    public WEB3AdminToIfoOrderReferenceServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.initData();
        this.l_orderUnitList = new IfoOrderUnit[1];
        this.l_orderUnitList[0] = new IfoContractOpenOrderUnitImpl(l_orderUnitId);
        
        this.l_request = new WEB3AdminToIfoOrderRefRefRequest();
        this.l_service = new WEB3AdminToIfoOrderReferenceServiceImpl();
        
    }

    protected void tearDown() throws Exception
    {
        this.l_orderUnitList = null;
        this.l_request = null;
        this.l_service = null;
//        super.checkMethodValue();
        super.tearDown();
    }

    
    /**
     * l_strExpirationDateType == null
     */
    public void testCreateIfoOrderRefUnitList_0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoOrderRefUnitList_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.l_orderRefUnit = this.l_service.createIfoOrderRefUnitList(this.l_orderUnitList, this.l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /*
     * l_strExpirationDateType == WEB3OrderExpirationDateTypeDef.CARRIED_ORDER
     */
    public void testCreateIfoOrderRefUnitList_0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoOrderRefUnitList_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isCarriedOrderUnit", new Class[]
                { IfoOrderUnit.class },
                new Boolean(true));
            
            this.l_orderRefUnit = this.l_service.createIfoOrderRefUnitList(this.l_orderUnitList, this.l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", new Class[]{ IfoOrderUnit.class });
            assertEquals(IfoContractOpenOrderUnitImpl.class, l_paramsValue1.getFirstCalled()[0].getClass());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);        
    }
    
    /**
     * １） 以下のパラメータが全てnullの場合は、nullを返却する。
     * 　@・限月
     * 　@・行使価格
     * 　@・オプション商品区分
     */
    public void testGetProductIdListt_C0001()
    {
        final String STR_METHOD_NAME = "testGetProductIdListt_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strInstitutionCode = "0D";
            String l_targetProduct = null;
            String l_deliveryMonth = null;
            String l_strikePrice = null;
            String l_opProductType = null;

            String[] l_strReturns = l_service.getProductIdList(
                l_strInstitutionCode,
                l_targetProduct,
                l_deliveryMonth,
                l_strikePrice,
                l_opProductType);

            assertEquals(null, l_strReturns);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /**
     * 以下のパラメータが非全てnullの場合
     * 　@・限月
     */
    public void testGetProductIdListt_C0002()
    {
        final String STR_METHOD_NAME = "testGetProductIdListt_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strInstitutionCode = "0D";
            String l_targetProduct = "0005";
            String l_deliveryMonth = "200903";
            String l_strikePrice = null;
            String l_opProductType = null;

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(1006160060005L);
            l_ifoProductParams1.setProductCode("160030005");
            l_ifoProductParams1.setUnderlyingProductCode("0005");
            l_ifoProductParams1.setSplitType("123");
            l_ifoProductParams1.setInstitutionCode("0D");
            l_ifoProductParams1.setMonthOfDelivery("200903");
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            IfoProductParams l_ifoProductParams2 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams2.setProductId(1006160060006L);
            l_ifoProductParams2.setProductCode("160030006");
            l_ifoProductParams2.setUnderlyingProductCode("0005");
            l_ifoProductParams1.setSplitType("321");
            l_ifoProductParams2.setInstitutionCode("0D");
            l_ifoProductParams2.setMonthOfDelivery("200903");
            TestDBUtility.insertWithDel(l_ifoProductParams2);

            String[] l_strReturns = l_service.getProductIdList(
                l_strInstitutionCode,
                l_targetProduct,
                l_deliveryMonth,
                l_strikePrice,
                l_opProductType);

            assertEquals(2, l_strReturns.length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * パラメータ.銘柄ID一覧 = null　@且つ　@パラメータ.リクエストデータ.指数種別 != nullの場合、
     */
    public void testCreateQueryString_C0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminToIfoOrderRefRefRequest l_request =
                new WEB3AdminToIfoOrderRefRefRequest();
            l_request.targetProductCode = "0005";

            String l_strReturn = l_service.createQueryString(l_request, null);

            assertEquals("and product_id like ? ", l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * パラメータ.銘柄ID一覧 = null　@且つ　@パラメータ.リクエストデータ.指数種別 != nullの場合、
     */
    public void testCreateQueryDataContainer_C0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminToIfoOrderRefRefRequest l_request =
                new WEB3AdminToIfoOrderRefRefRequest();
            l_request.targetProductCode = "0005";

            String[] l_strReturns = l_service.createQueryDataContainer(l_request, null);

            assertEquals(1, l_strReturns.length);
            assertEquals("%05", l_strReturns[0]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * normal case
     */
    public void testGetReferenceScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginInfoImpl l_loginInfo = new LoginInfoImplForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            WEB3AdminToIfoOrderRefRefRequest l_request = new WEB3AdminToIfoOrderRefRefRequest();
            l_request.branchCode = new String[1];
            l_request.branchCode[0] = new String();
            l_request.branchCode[0] = "000";
            l_request.triggerOrderType = "1";

            l_request.sortKeys = new WEB3AdminToOrderRefSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminToOrderRefSortKey();
            l_request.sortKeys[0].keyItem = "branchCode"; 
            l_request.sortKeys[0].ascDesc = "A"; 

            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            l_request.targetProductCode = "0005";
            l_request.delivaryMonth = "200903";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0302");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setMonthOfDelivery("200903");
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("000");
            TestDBUtility.insertWithDel(l_branchParams);

            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setBizDate("20090308");
            l_ifoOrderUnitParams.setOrderId(1001);
            IfoOrderUnitParams[] l_IfoOrderUnitParams0 = new IfoOrderUnitParams[1];
            l_IfoOrderUnitParams0[0] = new IfoOrderUnitParams();
            l_IfoOrderUnitParams0[0] = l_ifoOrderUnitParams;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "removeCarryOverOriginalOrderUnit",
                    new Class[]{IfoOrderUnitParams[].class},
                    l_IfoOrderUnitParams0);

            WEB3AdminToIfoOrderReferenceServiceImpl l_serviceImpl =
                new WEB3AdminToIfoOrderReferenceServiceImplForTest();

            WEB3AdminToIfoOrderRefRefResponse l_response = l_serviceImpl.getReferenceScreen(l_request);

            assertEquals("1",l_response.totalPages);
            assertEquals("1",l_response.totalRecords);
            assertEquals("1",l_response.pageIndex);
            assertEquals("111",l_response.ifoOrderList[0].accountCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * LoginInfoImplForTest
     */
    private class LoginInfoImplForTest extends LoginInfoImpl
    {
        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 33381330003L;
        }
    }

    /**
     * WEB3AdminToIfoOrderReferenceServiceImplForTest*
     */
    private class WEB3AdminToIfoOrderReferenceServiceImplForTest extends WEB3AdminToIfoOrderReferenceServiceImpl
    {
        protected IfoOrderUnitParams[] getOrderUnitList(
                String l_strQueryString, 
                String[] l_strQueryDataContainer,
                String l_strSortCond) throws WEB3BaseException
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setBizDate("20090308");
            l_ifoOrderUnitParams.setOrderId(1001);

            IfoOrderUnitParams[] l_IfoOrderUnitParams0 = new IfoOrderUnitParams[1];
            l_IfoOrderUnitParams0[0] = new IfoOrderUnitParams();
            l_IfoOrderUnitParams0[0] = l_ifoOrderUnitParams;
            return l_IfoOrderUnitParams0;
        }

        protected WEB3AdminToIfoOrderRefUnit[] createIfoOrderRefUnitList(
                IfoOrderUnit[] l_orderUnitList,
                WEB3AdminToIfoOrderRefRefRequest l_request) throws WEB3BaseException
        {
            WEB3AdminToIfoOrderRefUnit[] l_refUnits = new WEB3AdminToIfoOrderRefUnit[1];
            l_refUnits[0] = new WEB3AdminToIfoOrderRefUnit();
            l_refUnits[0].accountCode = "111";
            return l_refUnits;
        }
    }

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //IfoOrderUnitRow
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001);
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setLimitPrice(0);
            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setOrderConditionType("0");
            l_ifoOrderUnitParams.setOrderCondOperator(null);
            l_ifoOrderUnitParams.setStopPriceType(null);
            l_ifoOrderUnitParams.setStopOrderPrice(null);
            l_ifoOrderUnitParams.setWLimitPrice(null);
            l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_ifoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderRequestNumber("000003006");
            l_ifoOrderUnitParams.setConfirmedOrderRev("2");
            l_ifoOrderUnitParams.setOrderRev("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setSonarTraderCode("11124");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setFamilyName("内藤　@四郎");
            l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
            l_mainAccountParams.setGivenNameAlt1("Siro");
            l_mainAccountParams.setZipCode("1001238");
            l_mainAccountParams.setSubZipCode("1001238");
            l_mainAccountParams.setAddressLine1("東京都");
            l_mainAccountParams.setAddressLine2("江東区");
            l_mainAccountParams.setAddressLine3("深川５");
            l_mainAccountParams.setTelephone("38201115");
            l_mainAccountParams.setMobile("901115");
            l_mainAccountParams.setFax("38202226");
            l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setResident("0");
            l_mainAccountParams.setNewAccountDiv("0");
            l_mainAccountParams.setViaTrustBankDiv("0");
            l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams.setPersonIdentify("1");
            l_mainAccountParams.setEraBorn("3");
            l_mainAccountParams.setBornDate("101013");
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setHtSettlementWay("0");
            l_mainAccountParams.setBankAccountRegi("0");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setMngLockFlag("0");
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setOrderPermission("0");
            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setRuitoAccOpenDiv("0");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_mainAccountParams.setFxAccOpenDiv("0");
            l_mainAccountParams.setFeqConAccOpenDiv("0");
            l_mainAccountParams.setTopPageId("0");
            l_mainAccountParams.setQuotoType("0");
            l_mainAccountParams.setTradingReportDiv("1");
            l_mainAccountParams.setPositionReportDiv("9");
            l_mainAccountParams.setPositionReportCycleDiv("1");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEmailLastUpdater("2512246");
            l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setTradingPasswordUpdater("2512246");
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMbOffLastUpdater("2512246");
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMrfFundCode("1");
            l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("SP");
            l_marketParams.setSonarMarketCode("G");
            l_marketParams.setMarketName("シンガポール");
            l_marketParams.setOpenTime("09:00");
            l_marketParams.setCloseTime("15:00");
            l_marketParams.setSuspension("0");
            l_marketParams.setChangeableType("1");
            l_marketParams.setFeqOrderEmpDiv("7");
            l_marketParams.setFeqOrderRequestDiv("1");
            l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setLotSize(0.000000D);
            l_productParams.setCalcSize(1.000000D);
            l_productParams.setEstimationPrice(0.000000D);
            l_productParams.setMarginRatio(0.000000D);
            l_productParams.setSecuritiesEstimationRatio(0.000000D);
            l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_productParams);
            
            //IfoProductRow
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("05");//10
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(0);
            l_ifoProductParams.setMonthOfDelivery("200503");
            l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
            l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            //RlsConOrderHitNotifyRow
            TestDBUtility.deleteAll(RlsConOrderHitNotifyRow.TYPE);
            RlsConOrderHitNotifyParams l_rlsConOrderHitNotifyParams = TestDBUtility.getRlsConOrderHitNotifyRow();
            l_rlsConOrderHitNotifyParams.setAccountId(333812512246L);
            l_rlsConOrderHitNotifyParams.setSubAccountId(10100101001007L);
            l_rlsConOrderHitNotifyParams.setOrderId(1001L);
            l_rlsConOrderHitNotifyParams.setProductType(ProductTypeEnum.IFO);
            l_rlsConOrderHitNotifyParams.setSerialNoInParent(0);
            l_rlsConOrderHitNotifyParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rlsConOrderHitNotifyParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rlsConOrderHitNotifyParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@
