head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOrderNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionsOrderNotifyUnitServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/19 金傑 (中訊) 新規作成 仕様変更モデルNo.646
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （OP注文通知サービス実装クラステスト）<BR>
 * 
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3OptionsOrderNotifyUnitServiceImplTest extends TestBaseForMock
{

    private WEB3OptionsOrderNotifyUnitServiceImpl l_serviceImpl = null;
    
    private HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams = null;
    
    private SubAccount l_subAccount = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3OptionsOrderNotifyUnitServiceImplTest.class);
    
    public WEB3OptionsOrderNotifyUnitServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3OptionsOrderNotifyUnitServiceImplForTest();
        this.initData();
        this.setMockMethod();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        super.checkMethodValue();
        super.tearDown();
    }
    
    /**
     * インタセプタ.取引時間管理.get立会区分()の戻り値 = 1
     * インタセプタ.受渡日 = 當前日付
     *
     */
    public void testNotifyOpenContractOrder_C0001()
    {  
        final String STR_METHOD_NAME = "testNotifyOpenContractOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_hostFotypeOrderReceiptParams = new HostFotypeOrderReceiptParams();
            this.l_hostFotypeOrderReceiptParams.setInstitutionCode("1D");
            this.l_hostFotypeOrderReceiptParams.setSonarMarketCode("1001");
            //銘柄コード
            this.l_hostFotypeOrderReceiptParams.setProductCode("160030005");
            this.l_hostFotypeOrderReceiptParams.setExecutionCondition("1");
            //注文受付番号
            this.l_hostFotypeOrderReceiptParams.setAcceptNumber("100001");
            //売買区分
            this.l_hostFotypeOrderReceiptParams.setTradeType("2");
            this.l_subAccount = new WEB3GentradeSubAccountForMock(333812512266L,33381251220366L);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_deliveryAmount = new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                            l_deliveryAmount);

            WEB3IfoTradedProductImpl l_tradeProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_tradeProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[]{ IfoOrderManagerPersistenceEventInterceptor.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            this.l_serviceImpl.notifyOpenContractOrder(this.l_hostFotypeOrderReceiptParams,this.l_subAccount);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            log.error(l_web3MockObjectRuntimeException.getMessage(), l_web3MockObjectRuntimeException);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarketBySONAR", 
                    new Class[] { String.class, String.class });
            assertEquals("1D",(String)l_paramsValue1.getFirstCalled()[0]);
            assertEquals("1001",(String)l_paramsValue1.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getInstitution", 
                    new Class[] {String.class});
            assertEquals("1D",(String)l_paramsValue2.getFirstCalled()[0]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoProduct", 
                    new Class[] {Institution.class, String.class });
            assertEquals("1D",((Institution)l_paramsValue3.getFirstCalled()[0]).getInstitutionCode());
            assertEquals("160030005",(String)l_paramsValue3.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoTradedProduct", 
                    new Class[] { Institution.class, String.class, String.class });
            assertEquals("1D",((Institution)l_paramsValue4.getFirstCalled()[0]).getInstitutionCode());
            assertEquals("160030005",(String)l_paramsValue4.getFirstCalled()[1]);
            assertEquals("SP",(String)l_paramsValue4.getFirstCalled()[2]);
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "calcEstimateDeliveryAmount", 
                    new Class[] {WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class });
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[] {IfoOrderManagerPersistenceEventInterceptor.class});
            assertEquals("1",((WEB3IfoOpenContractOrderNotifyUpdateInterceptor)l_paramsValue6.getFirstCalled()[0]).getSessionType());
            assertEquals(0,WEB3DateUtility.compareToDay(new Date(),((WEB3IfoOpenContractOrderNotifyUpdateInterceptor)l_paramsValue6.getFirstCalled()[0]).getDeliveryDate()));
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    /**
     * create返済建玉エントリ()の戻り値を取得する
     *
     */
    public void testNotifySettleContractOrder_C0001()
    {  
        final String STR_METHOD_NAME = "testNotifySettleContractOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_hostFotypeOrderReceiptParams = new HostFotypeOrderReceiptParams();
            this.l_hostFotypeOrderReceiptParams.setInstitutionCode("1D");
            this.l_hostFotypeOrderReceiptParams.setSonarMarketCode("1001");
            this.l_hostFotypeOrderReceiptParams.setProductCode("160030005");
            this.l_hostFotypeOrderReceiptParams.setExecutionCondition("1");
            this.l_subAccount = new WEB3GentradeSubAccountForMock(333812512266L,33381251220366L);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getDayTradeType", new Class[]{ SettleContractEntry[].class },
                    new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
            
            SettleContractEntry[] l_contractEntry = new SettleContractEntry[1];
            l_contractEntry[0] = new SettleContractEntry(555,222);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_contractEntry);
            
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_optionsCloseMarginContractUnits = 
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", 
                    "createSettleContracts", 
                    new Class[]{ WEB3GentradeSubAccount.class, ContractTypeEnum.class, long.class, long.class },
                    l_optionsCloseMarginContractUnits);
            
            this.l_serviceImpl.notifySettleContractOrder(this.l_hostFotypeOrderReceiptParams,this.l_subAccount);
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarketBySONAR", 
                    new Class[] { String.class, String.class });
            assertEquals("1D",(String)l_paramsValue1.getFirstCalled()[0]);
            assertEquals("1001",(String)l_paramsValue1.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getInstitution", 
                    new Class[] {String.class});
            assertEquals("1D",(String)l_paramsValue2.getFirstCalled()[0]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoProduct", 
                    new Class[] {Institution.class, String.class });
            assertEquals("1D",((Institution)l_paramsValue3.getFirstCalled()[0]).getInstitutionCode());
            assertEquals("160030005",(String)l_paramsValue3.getFirstCalled()[1]);
                        
     
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", 
                    "createSettleContracts", 
                    new Class[] { WEB3GentradeSubAccount.class, ContractTypeEnum.class, long.class, long.class });
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[] { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getDayTradeType", 
                    new Class[] {SettleContractEntry[].class});
            
            assertEquals(555,((SettleContractEntry[])l_paramsValue6.getFirstCalled()[0])[0].getContractId());  
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }

    /**
     * インタセプタ.取引時間管理.get立会区分()の戻り値 = 1
     * インタセプタ.受渡日 = 當前日付
     *
     */
    public void testNotifySettleContractOrder_C0002()
    {  
        final String STR_METHOD_NAME = "testNotifySettleContractOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_hostFotypeOrderReceiptParams = new HostFotypeOrderReceiptParams();
            this.l_hostFotypeOrderReceiptParams.setInstitutionCode("1D");
            this.l_hostFotypeOrderReceiptParams.setSonarMarketCode("1001");
            //銘柄コード
            this.l_hostFotypeOrderReceiptParams.setProductCode("160030005");
            this.l_hostFotypeOrderReceiptParams.setExecutionCondition("1");
            //注文受付番号
            this.l_hostFotypeOrderReceiptParams.setAcceptNumber("100002");
            //売買区分
            this.l_hostFotypeOrderReceiptParams.setTradeType("1");
            this.l_subAccount = new WEB3GentradeSubAccountForMock(333812512266L,33381251220366L);
            
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_deliveryAmount = new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                            l_deliveryAmount);
            
            SettleContractEntry[] l_contractEntry = new SettleContractEntry[1];
            l_contractEntry[0] = new SettleContractEntry(555,222);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getDayTradeType", new Class[]{ SettleContractEntry[].class },
                    null);
            

            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_contractEntry);
            
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_optionsCloseMarginContractUnits = 
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", 
                    "createSettleContracts", 
                    new Class[]{ WEB3GentradeSubAccount.class, ContractTypeEnum.class, long.class, long.class },
                    l_optionsCloseMarginContractUnits);
            
            WEB3IfoTradedProductImpl l_tradeProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_tradeProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[]{ IfoOrderManagerPersistenceEventInterceptor.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            this.l_serviceImpl.notifySettleContractOrder(this.l_hostFotypeOrderReceiptParams,this.l_subAccount);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            log.error(l_web3MockObjectRuntimeException.getMessage(), l_web3MockObjectRuntimeException);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarketBySONAR", 
                    new Class[] { String.class, String.class });
            assertEquals("1D",(String)l_paramsValue1.getFirstCalled()[0]);
            assertEquals("1001",(String)l_paramsValue1.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getInstitution", 
                    new Class[] {String.class});
            assertEquals("1D",(String)l_paramsValue2.getFirstCalled()[0]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoProduct", 
                    new Class[] {Institution.class, String.class });
            assertEquals("1D",((Institution)l_paramsValue3.getFirstCalled()[0]).getInstitutionCode());
            assertEquals("160030005",(String)l_paramsValue3.getFirstCalled()[1]);
                        
     
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoPositionManagerImpl", 
                    "createSettleContracts", 
                    new Class[] { WEB3GentradeSubAccount.class, ContractTypeEnum.class, long.class, long.class });
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[] { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getDayTradeType", 
                    new Class[] {SettleContractEntry[].class});
            
            assertEquals(555,((SettleContractEntry[])l_paramsValue6.getFirstCalled()[0])[0].getContractId());
            
            WEB3MockObjectParamsValue l_paramsValue7 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoTradedProduct", 
                    new Class[] { Institution.class, String.class, String.class });
            
            WEB3MockObjectParamsValue l_paramsValue8 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "calcEstimateDeliveryAmount", 
                    new Class[] {WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class });
            
            WEB3MockObjectParamsValue l_paramsValue9 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[] {IfoOrderManagerPersistenceEventInterceptor.class});
            assertEquals("1",((WEB3IfoSettleContractOrderNotifyUpdateInterceptor)l_paramsValue9.getFirstCalled()[0]).getSessionType());
            assertEquals(0,WEB3DateUtility.compareToDay(new Date(),((WEB3IfoSettleContractOrderNotifyUpdateInterceptor)l_paramsValue9.getFirstCalled()[0]).getDeliveryDate()));
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
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
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512266L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220366L);
            l_subAccountParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33387);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            this.setExpectedDate(new Date(),"1");

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void setExpectedDate(Date l_expectDate,String l_sessionType)
    {
        final String STR_METHOD_NAME = "setExpectedDate()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_expectDate.getTime()), "1");
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setSessionType(l_sessionType);
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);  
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    } 

    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarketBySONAR", 
                    new Class[]{ String.class, String.class },
                    l_market);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("1D");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getInstitution",
                    new Class[] {String.class},
                    l_institution);
            
            WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(1006160060009L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_product);
            
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(333812512246L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccountForMock);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33387);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3OptionsOrderNotifyUnitServiceImplForTest extends WEB3OptionsOrderNotifyUnitServiceImpl
    {
        public IfoOrderExecutionConditionType getAPOrderExecCondType(String l_strExecCondTypeSonar) throws WEB3BaseException
        {
            return IfoOrderExecutionConditionType.NONE;
        }
    }
    
    private class WEB3IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    {

        public WEB3IfoTradedProductImplForTest(long l_lngTradedProductID) throws DataQueryException, DataNetworkException, DataFindException
        {
            super(l_lngTradedProductID);
        }
        
        public Date getDailyDeliveryDate()
        {
            return new Date();
        }
        
    }
}
@
