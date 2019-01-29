head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOrderManagerImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文マネージャテスト(WEB3OptionOrderManagerImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/06 趙林鵬 (中訊) 新規作成
*/

package webbroker3.ifo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum.IntValues;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.HostOrderexecutionEndParams;
import webbroker3.gentrade.data.HostOrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.data.HostFotypeOrderAllDao;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.data.IfoOrderExecSendMailRow;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （OP注文マネージャテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3OptionOrderManagerImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3OptionOrderManagerImplTest.class);
    
    public WEB3OptionOrderManagerImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testGetAcceptNumberOrderUnitCase1()
    {
        final String STR_METHOD_NAME = " testGetAcceptNumberOrderUnitCase1";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManager = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setBranchId(101);
            l_IfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_IfoOrderUnitParams.setAcceptNumber("10");
            l_IfoOrderUnitParams.setProductCode("1");
            l_IfoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("101");
            l_BranchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_BranchParams);
            
            l_optionOrderManager.getAcceptNumberOrderUnit("0D","101",ProductTypeEnum.IFO,"12","1","1");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetAcceptNumberOrderUnitCase2()
    {
        final String STR_METHOD_NAME = " testGetAcceptNumberOrderUnitCase2";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManager = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setBranchId(101);
            l_IfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_IfoOrderUnitParams.setAcceptNumber("10");
            l_IfoOrderUnitParams.setProductCode("1");
            l_IfoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("101");
            l_BranchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_BranchParams);
            
            OrderUnit l_orderUnit = l_optionOrderManager.getAcceptNumberOrderUnit("0D","101",ProductTypeEnum.IFO,"10","1","1");
            
            assertEquals(l_orderUnit.getAccountId(), 101001010010L);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetAcceptNumberOrderUnitCase3()
    {
        final String STR_METHOD_NAME = " testGetAcceptNumberOrderUnitCase3";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManager = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setBranchId(101);
            l_IfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_IfoOrderUnitParams.setAcceptNumber("10");
            l_IfoOrderUnitParams.setProductCode("1");
            l_IfoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setOrderUnitId(1002);
            l_IfoOrderUnitParams1.setAccountId(101001010011L);
            l_IfoOrderUnitParams1.setBranchId(101);
            l_IfoOrderUnitParams1.setProductType(ProductTypeEnum.IFO);
            l_IfoOrderUnitParams1.setAcceptNumber("10");
            l_IfoOrderUnitParams1.setProductCode("1");
            l_IfoOrderUnitParams1.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("101");
            l_BranchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_BranchParams);
            
            l_optionOrderManager.getAcceptNumberOrderUnit("0D","101",ProductTypeEnum.IFO,"10","1","1");
            
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80004, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetAcceptNumberOrderUnitCase4()
    {
        final String STR_METHOD_NAME = " testGetAcceptNumberOrderUnitCase4";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManager = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setBranchId(101);
            l_IfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_IfoOrderUnitParams.setAcceptNumber("10");
            l_IfoOrderUnitParams.setProductCode("1");
            l_IfoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("101");
            l_BranchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_BranchParams);
            
             l_optionOrderManager.getAcceptNumberOrderUnit("0D","101",null,"10","1","1");
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetAcceptNumberOrderUnitCase5()
    {
        final String STR_METHOD_NAME = " testGetAcceptNumberOrderUnitCase5";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManager = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setBranchId(101);
            l_IfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_IfoOrderUnitParams.setAcceptNumber("10");
            l_IfoOrderUnitParams.setProductCode("1");
            l_IfoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("101");
            l_BranchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_BranchParams);
            
             l_optionOrderManager.getAcceptNumberOrderUnit("0D","101",ProductTypeEnum.IFO,null,"1","1");
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetAcceptNumberOrderUnitCase6()
    {
        final String STR_METHOD_NAME = " testGetAcceptNumberOrderUnitCase6";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManager = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setBranchId(101);
            l_IfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_IfoOrderUnitParams.setAcceptNumber("10");
            l_IfoOrderUnitParams.setProductCode("1");
            l_IfoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33);
//            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("101");
            l_BranchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_BranchParams);
            
             l_optionOrderManager.getAcceptNumberOrderUnit("0D","101",ProductTypeEnum.IFO,"10","1","1");
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetAcceptNumberOrderUnitCase7()
    {
        final String STR_METHOD_NAME = " testGetAcceptNumberOrderUnitCase7";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManager = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setBranchId(101);
            l_IfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_IfoOrderUnitParams.setAcceptNumber("10");
            l_IfoOrderUnitParams.setProductCode("1");
            l_IfoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("101");
            l_BranchParams.setBranchId(101);
//            TestDBUtility.insertWithDel(l_BranchParams);
            
             l_optionOrderManager.getAcceptNumberOrderUnit("0D","101",ProductTypeEnum.IFO,"10","1","1");
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetAcceptNumberOrderUnitCase8()
    {
        final String STR_METHOD_NAME = " testGetAcceptNumberOrderUnitCase8";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_optionOrderManager = 
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams.setBranchId(101);
            l_IfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_IfoOrderUnitParams.setAcceptNumber("10");
            l_IfoOrderUnitParams.setProductCode("1");
            l_IfoOrderUnitParams.setBuySellDiv("1");
//            TestDBUtility.insertWithDel(l_IfoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            l_BranchParams.setBranchCode("101");
            l_BranchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_BranchParams);
            
             l_optionOrderManager.getAcceptNumberOrderUnit("0D","101",ProductTypeEnum.IFO,"10","1","1");
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
//    
//    public void est_validateChangeSettleContractOrder_C0001()
//    {
//        final String STR_METHOD_NAME = " test_validateChangeSettleContractOrder_C0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderForChangeability",
//            new Class[] {Order.class, boolean.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateHandlingIndex",
//            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderCond",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, boolean.class, 
//                WEB3IfoTradedProductImpl.class, boolean.class,
//                boolean.class, Date.class, Date.class, String.class, 
//                IfoOrderExecutionConditionType.class, String.class, Long.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderUnitPrice",
//            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateWLimitPriceOrder",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, double.class, 
//                String.class, double.class,
//                String.class,
//                IfoOrderExecutionConditionType.class, String.class,
//                WEB3IfoTradedProductImpl.class,
//                boolean.class, boolean.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderChangeSpec",
//            new Class[]{
//                OrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class, String.class,
//                String.class, String.class, double.class, double.class, 
//                IfoOrderExecutionConditionType.class, Date.class, String.class,
//                SettleContractEntry[].class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateChangeOrderRevLimit",
//            new Class[]{
//                IfoOrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class},
//                null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
//            
//            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
//            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
//            
//            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
//                new WEB3IfoChangeSettleContractOrderSpec(
//                    1001, 1001, 200, l_SettleContractEntry);
//            
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeSettleContractOrder(
//                l_subAccount,
//                l_changeSettleContractOrderSpec,
//                l_blnIsSkipDelayStatusCheck);
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_validateChangeSettleContractOrder_C0002()
//    {
//        final String STR_METHOD_NAME = " test_validateChangeSettleContractOrder_C0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderForChangeability",
//            new Class[] {Order.class, boolean.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateHandlingIndex",
//            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderCond",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, boolean.class, 
//                WEB3IfoTradedProductImpl.class, boolean.class,
//                boolean.class, Date.class, Date.class, String.class, 
//                IfoOrderExecutionConditionType.class, String.class, Long.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderUnitPrice",
//            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateWLimitPriceOrder",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, double.class, 
//                String.class, double.class,
//                String.class,
//                IfoOrderExecutionConditionType.class, String.class,
//                WEB3IfoTradedProductImpl.class,
//                boolean.class, boolean.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderChangeSpec",
//            new Class[]{
//                OrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class, String.class,
//                String.class, String.class, double.class, double.class, 
//                IfoOrderExecutionConditionType.class, Date.class, String.class,
//                SettleContractEntry[].class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateChangeOrderRevLimit",
//            new Class[]{
//                IfoOrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class},
//                null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20061010");
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);  
//            
//            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
//            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
//            
//            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
//                new WEB3IfoChangeSettleContractOrderSpec(
//                    1001, 1001, 200, l_SettleContractEntry);
//            
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeSettleContractOrder(
//                l_subAccount,
//                l_changeSettleContractOrderSpec,
//                l_blnIsSkipDelayStatusCheck);
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_validateChangeSettleContractOrder_C0003()
//    {
//        final String STR_METHOD_NAME = " test_validateChangeSettleContractOrder_C0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1002);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20061010");
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
// 
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
//            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
//            
//            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
//                new WEB3IfoChangeSettleContractOrderSpec(
//                    1002, 1001, 200, l_SettleContractEntry);
//            
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeSettleContractOrder(
//                l_subAccount,
//                l_changeSettleContractOrderSpec,
//                l_blnIsSkipDelayStatusCheck);
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            
//            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
//                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    public void est_validateChangeSettleContractOrder_C0004()
//    {
//        final String STR_METHOD_NAME = " test_validateChangeSettleContractOrder_C0004";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderForChangeability",
//            new Class[] {Order.class, boolean.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateHandlingIndex",
//            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderCond",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, boolean.class, 
//                WEB3IfoTradedProductImpl.class, boolean.class,
//                boolean.class, Date.class, Date.class, String.class, 
//                IfoOrderExecutionConditionType.class, String.class, Long.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderUnitPrice",
//            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateWLimitPriceOrder",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, double.class, 
//                String.class, double.class,
//                String.class,
//                IfoOrderExecutionConditionType.class, String.class,
//                WEB3IfoTradedProductImpl.class,
//                boolean.class, boolean.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderChangeSpec",
//            new Class[]{
//                OrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class, String.class,
//                String.class, String.class, double.class, double.class, 
//                IfoOrderExecutionConditionType.class, Date.class, String.class,
//                SettleContractEntry[].class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateChangeOrderRevLimit",
//            new Class[]{
//                IfoOrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class},
//            new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20061010");
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
//            
//            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
//            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
//            
//            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
//                new WEB3IfoChangeSettleContractOrderSpec(
//                    1001, 1001, 200, l_SettleContractEntry);
//            
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeSettleContractOrder(
//                l_subAccount,
//                l_changeSettleContractOrderSpec,
//                l_blnIsSkipDelayStatusCheck);
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            
//            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
//                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    public void est_validateChangeOrder_C0001()
//    {
//        final String STR_METHOD_NAME = " test_validateChangeOrder_C0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderForChangeability",
//            new Class[] {Order.class, boolean.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateHandlingIndex",
//            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderCond",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, boolean.class, 
//                WEB3IfoTradedProductImpl.class, boolean.class,
//                boolean.class, Date.class, Date.class, String.class, 
//                IfoOrderExecutionConditionType.class, String.class, Long.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderUnitPrice",
//            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateWLimitPriceOrder",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, double.class, 
//                String.class, double.class,
//                String.class,
//                IfoOrderExecutionConditionType.class, String.class,
//                WEB3IfoTradedProductImpl.class,
//                boolean.class, boolean.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderChangeSpec",
//            new Class[]{
//                OrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class, String.class,
//                String.class, String.class, double.class, double.class, 
//                IfoOrderExecutionConditionType.class, Date.class, String.class,
//                SettleContractEntry[].class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateChangeOrderRevLimit",
//            new Class[]{
//                IfoOrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class},
//                null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.BOND_BUY);
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
//            
//            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
//                new WEB3IfoOpenContractChangeSpec(1001, 1001, 200, 200);
//
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeOrder(
//                l_subAccount,
//                l_ifoOpenContractChangeSpec,
//                l_blnIsSkipDelayStatusCheck);
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_validateChangeOrder_C0002()
//    {
//        final String STR_METHOD_NAME = " test_validateChangeOrder_C0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderForChangeability",
//            new Class[] {Order.class, boolean.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateHandlingIndex",
//            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderCond",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, boolean.class, 
//                WEB3IfoTradedProductImpl.class, boolean.class,
//                boolean.class, Date.class, Date.class, String.class, 
//                IfoOrderExecutionConditionType.class, String.class, Long.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderUnitPrice",
//            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateWLimitPriceOrder",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, double.class, 
//                String.class, double.class,
//                String.class,
//                IfoOrderExecutionConditionType.class, String.class,
//                WEB3IfoTradedProductImpl.class,
//                boolean.class, boolean.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderChangeSpec",
//            new Class[]{
//                OrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class, String.class,
//                String.class, String.class, double.class, double.class, 
//                IfoOrderExecutionConditionType.class, Date.class, String.class,
//                SettleContractEntry[].class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateChangeOrderRevLimit",
//            new Class[]{
//                IfoOrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class},
//                null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.BOND_SELL);
//            l_ifoOrderUnitParams.setBizDate("20061010");
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);      
//            
//            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
//                new WEB3IfoOpenContractChangeSpec(1001, 1001, 200, 200);
//
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeOrder(
//                l_subAccount,
//                l_ifoOpenContractChangeSpec,
//                l_blnIsSkipDelayStatusCheck);
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_validateChangeOrder_C0003()
//    {
//        final String STR_METHOD_NAME = " test_validateChangeOrder_C0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1002);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20061010");
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
// 
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
//                new WEB3IfoOpenContractChangeSpec(1002, 1001, 200, 200);
//
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeOrder(
//                l_subAccount,
//                l_ifoOpenContractChangeSpec,
//                l_blnIsSkipDelayStatusCheck);
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            
//            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
//                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    public void est_validateChangeOrder_C0004()
//    {
//        final String STR_METHOD_NAME = " test_validateChangeOrder_C0004";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderForChangeability",
//            new Class[] {Order.class, boolean.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateHandlingIndex",
//            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderCond",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, boolean.class, 
//                WEB3IfoTradedProductImpl.class, boolean.class,
//                boolean.class, Date.class, Date.class, String.class, 
//                IfoOrderExecutionConditionType.class, String.class, Long.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderUnitPrice",
//            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateWLimitPriceOrder",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, double.class, 
//                String.class, double.class,
//                String.class,
//                IfoOrderExecutionConditionType.class, String.class,
//                WEB3IfoTradedProductImpl.class,
//                boolean.class, boolean.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderChangeSpec",
//            new Class[]{
//                OrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class, String.class,
//                String.class, String.class, double.class, double.class, 
//                IfoOrderExecutionConditionType.class, Date.class, String.class,
//                SettleContractEntry[].class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateChangeOrderRevLimit",
//            new Class[]{
//                IfoOrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class},
//            new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20061010");
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);   
//            
//            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
//                new WEB3IfoOpenContractChangeSpec(1001, 1001, 200, 200);
//
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeOrder(
//                l_subAccount,
//                l_ifoOpenContractChangeSpec,
//                l_blnIsSkipDelayStatusCheck);
// 
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            
//            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
//                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_insertOpenContractHostOrder_C0001()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
//            l_ifoOrderUnitParams.setFutureOptionDiv("1");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);     
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            long l_lngOrderId = 1001;
//
//            l_optionOrderManager.insertOpenContractHostOrder(l_lngOrderId);
//            
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_insertOpenContractHostOrder_C0002()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("1");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);    
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            long l_lngOrderId = 1001;
//
//            l_optionOrderManager.insertOpenContractHostOrder(l_lngOrderId);
//            
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_insertOpenContractHostOrder_C0003()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            long l_lngOrderId = 1001;
//
//            l_optionOrderManager.insertOpenContractHostOrder(l_lngOrderId);
//            
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_insertOpenContractHostOrder_C0004()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0004";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070102");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.LIMIT_PRICE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(1001);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//                 
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            long l_lngOrderId = 1001;
//
//            l_optionOrderManager.insertOpenContractHostOrder(l_lngOrderId);
//            
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_insertOpenContractHostOrder_C0005()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0005";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070102");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.LIMIT_PRICE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);  
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            long l_lngOrderId = 1001;
//
//            l_optionOrderManager.insertOpenContractHostOrder(l_lngOrderId);
//            
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void est_insertSettleContractHostOrder_C0001()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0005";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {        
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
//            l_ifoOrderUnitParams.setFutureOptionDiv("1");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
//            
//            long l_lngOrderId = 1001;
//           
//            l_optionOrderManager.insertSettleContractHostOrder(l_lngOrderId);
//
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void est_insertSettleContractHostOrder_C0002()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("1");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
//            
//            long l_lngOrderId = 1001;
//           
//            l_optionOrderManager.insertSettleContractHostOrder(l_lngOrderId);
//
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void est_insertSettleContractHostOrder_C0003()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
//            
//            long l_lngOrderId = 1001;
//           
//            l_optionOrderManager.insertSettleContractHostOrder(l_lngOrderId);
//
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void est_insertSettleContractHostOrder_C0004()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0004";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070102");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.LIMIT_PRICE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(1001);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
//            
//            long l_lngOrderId = 1001;
//           
//            l_optionOrderManager.insertSettleContractHostOrder(l_lngOrderId);
//
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void est_insertSettleContractHostOrder_C0005()
//    {
//        final String STR_METHOD_NAME = " test_insertOpenContractHostOrder_C0005";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070102");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.LIMIT_PRICE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
//            
//            long l_lngOrderId = 1001;
//           
//            l_optionOrderManager.insertSettleContractHostOrder(l_lngOrderId);
//
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//
//            assertEquals(101001010010L, l_hostFotypeOrderAllRow.getAccountId());
//            assertEquals("1", l_hostFotypeOrderAllRow.getSubmitOrderRouteDiv());
//            assertTrue(l_hostFotypeOrderAllRow.getStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getWLimitPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeQuantityIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeLimitPriceIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getChangeExecutionCondition());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeStopOrderPriceIsNull());
//            assertTrue(l_hostFotypeOrderAllRow.getChangeWLimitPriceIsNull());
//            assertEquals("0", l_hostFotypeOrderAllRow.getCancelDiv());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderExchangeCode());
//            assertEquals("2",l_hostFotypeOrderAllRow.getFrontOrderSystemCode());
//            assertEquals("1",l_hostFotypeOrderAllRow.getFrontOrderTradeCode());
//            assertEquals("0",l_hostFotypeOrderAllRow.getTradeauditCode());
//            assertEquals("3338160000030061999",l_hostFotypeOrderAllRow.getCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getOrgCorpCode());
//            assertNull(l_hostFotypeOrderAllRow.getVirtualServerNumberJsoes());
//            assertTrue(l_hostFotypeOrderAllRow.getMarketOrderNumberIsNull());
//            assertNull(l_hostFotypeOrderAllRow.getAmgSendTime());
//            assertNull(l_hostFotypeOrderAllRow.getAmgAckTime());
//            assertNull(l_hostFotypeOrderAllRow.getMarketAckTime());       
//            assertEquals("0",l_hostFotypeOrderAllRow.getAllOrderChangeDiv());
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    
//    public void estIsDayTradeC0001()
//    {
//        final String STR_METHOD_NAME = " testIsDayTradeC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        SettleContractEntry[] l_settleContractEntrys = null;
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            
//            l_optionOrderManager.isDayTrade(l_settleContractEntrys);
//            
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//  
//    }
//    
//    public void estIsDayTradeC0002()
//    {
//        final String STR_METHOD_NAME = " testIsDayTradeC0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        SettleContractEntry[] l_settleContractEntrys = new SettleContractEntry[1];
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            this.deleteAll();
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            long l_lngContractId = 1001;
//            double l_dblQuantity = 200;
//            SettleContractEntry l_SettleContractEntry = new SettleContractEntry(l_lngContractId, l_dblQuantity);
//            l_settleContractEntrys[0] = l_SettleContractEntry;
//            
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070519", "yyyyMMdd"));
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040710","yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070519", "yyyyMMdd"));
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);      
//
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//
//            boolean l_blnIsDayTrade = l_optionOrderManager.isDayTrade(l_settleContractEntrys);
//            
//            assertTrue(l_blnIsDayTrade);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estIsDayTradeC0003()
//    {
//        final String STR_METHOD_NAME = " testIsDayTradeC0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        SettleContractEntry[] l_settleContractEntrys = new SettleContractEntry[1];
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20050710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            this.deleteAll();
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            long l_lngContractId = 1001;
//            double l_dblQuantity = 200;
//            SettleContractEntry l_SettleContractEntry = new SettleContractEntry(l_lngContractId, l_dblQuantity);
//            l_settleContractEntrys[0] = l_SettleContractEntry;
//            
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070519", "yyyyMMdd"));
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040710","yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070519", "yyyyMMdd"));
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);      
//
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//
//            boolean l_blnIsDayTrade = l_optionOrderManager.isDayTrade(l_settleContractEntrys);
//            
//            assertFalse(l_blnIsDayTrade);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estGetDayTradeTypeC0001()
//    {
//        final String STR_METHOD_NAME = " testGetDayTradeTypeC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        SettleContractEntry[] l_settleContractEntrys = new SettleContractEntry[1];
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            this.deleteAll();
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            long l_lngContractId = 1001;
//            double l_dblQuantity = 200;
//            SettleContractEntry l_SettleContractEntry = new SettleContractEntry(l_lngContractId, l_dblQuantity);
//            l_settleContractEntrys[0] = l_SettleContractEntry;
//            
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070519", "yyyyMMdd"));
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040710","yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070519", "yyyyMMdd"));
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);      
//
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//
//            String l_strGetDayTradeType = l_optionOrderManager.getDayTradeType(l_settleContractEntrys);
//            
//            assertEquals("5", l_strGetDayTradeType);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estGetDayTradeTypeC0002()
//    {
//        final String STR_METHOD_NAME = " testGetDayTradeTypeC0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        SettleContractEntry[] l_settleContractEntrys = new SettleContractEntry[1];
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20050710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            this.deleteAll();
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            long l_lngContractId = 1001;
//            double l_dblQuantity = 200;
//            SettleContractEntry l_SettleContractEntry = new SettleContractEntry(l_lngContractId, l_dblQuantity);
//            l_settleContractEntrys[0] = l_SettleContractEntry;
//            
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070519", "yyyyMMdd"));
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040710","yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070519", "yyyyMMdd"));
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);      
//
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//
//            String l_strGetDayTradeType = l_optionOrderManager.getDayTradeType(l_settleContractEntrys);
//            
//            assertNull(l_strGetDayTradeType);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estIsEveningSessionOrderC0001()
//    {
//        final String STR_METHOD_NAME = " testIsEveningSessionOrderC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            IfoOrderUnit l_ifoOrderUnit = null;
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//  
//            l_optionOrderManager.isEveningSessionOrder(l_ifoOrderUnit);
//   
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estIsEveningSessionOrderC0002()
//    {
//        final String STR_METHOD_NAME = " testIsEveningSessionOrderC0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderManager =
//                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
//
//            boolean l_blnIsEveningSessionOrder = l_optionOrderManager.isEveningSessionOrder(l_orderUnit);
//            
//            assertTrue(l_blnIsEveningSessionOrder);
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estIsEveningSessionOrderC0003()
//    {
//        final String STR_METHOD_NAME = " testIsEveningSessionOrderC0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderManager =
//                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
//
//            boolean l_blnIsEveningSessionOrder = l_optionOrderManager.isEveningSessionOrder(l_orderUnit);
//            
//            assertFalse(l_blnIsEveningSessionOrder);
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    
//    public void estInsertSettleContractHostOrderC01()
//    {
//        final String STR_METHOD_NAME = " testInsertSettleContractHostOrderC01";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("1");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setDayTradeType(null);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070301","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
//            
//            long l_lngOrderId = 1001;
//           
//            l_optionOrderManager.insertSettleContractHostOrder(l_lngOrderId);
//
//            List l_lstRows = HostFotypeOrderAllDao.findRowsByAccountIdOrderRequestNumber(
//                    new Long(l_ifoOrderUnitParams.getAccountId()), l_ifoOrderUnitParams.getOrderRequestNumber());
//            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstRows.get(0);
//      
//            assertEquals("1",l_hostFotypeOrderAllRow.getContractCheck());
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//
////    public void testGetTodayOpenOrderUnitsC0001()
////    {
////        final String STR_METHOD_NAME = " testGetTodayOpenOrderUnitsC0001";
////        log.entering(TEST_START + STR_METHOD_NAME);
////        
////        try
////        {
////            this.deleteAll();
////            BranchParams l_branchParams = TestDBUtility.getBranchRow();
////            l_branchParams.setBranchId(33381);
////            TestDBUtility.insertWithDel(l_branchParams);
////            
////            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
////            l_ifoOrderUnitParams.setOrderUnitId(1001);
////            l_ifoOrderUnitParams.setBranchId(33381);
////            l_ifoOrderUnitParams.setFutureOptionDiv("1");
////            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
////            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
////            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
////
////            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
////            WEB3OptionOrderManagerImpl l_optionOrderManager = 
////                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
////            
////            String l_strFuturesOptionDivision = "1";
////            Branch l_branch = l_finApp.getAccountManager().getBranch(l_branchParams.getBranchId());
////            
////            OrderUnit[] l_orderUnit = l_optionOrderManager.getTodayOpenOrderUnits(l_strFuturesOptionDivision, l_branch);
////            
////            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit[0].getDataSourceObject();
////            
////            assertEquals("1001", l_ifoOrderUnitRow.getOrderUnitId() + "");
////            
////        }
////        catch (Exception l_ex)
////        {
////            log.error(l_ex.getMessage(), l_ex);
////            fail();
////        }
////        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
////        log.exiting(TEST_END + STR_METHOD_NAME);
////    }
//
////    public void testGetTodayOpenOrderUnitsC0002()
////    {
////        final String STR_METHOD_NAME = " testGetTodayOpenOrderUnitsC0002";
////        log.entering(TEST_START + STR_METHOD_NAME);
////        
////        try
////        {
////            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
////            WEB3OptionOrderManagerImpl l_optionOrderManager = 
////                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
////            
////            String l_strFuturesOptionDivision = "1";
////            Branch l_branch = null;
////            
////            l_optionOrderManager.getTodayOpenOrderUnits(l_strFuturesOptionDivision, l_branch);
////
////        }
////        catch(WEB3BaseException l_ex)
////        {
////            log.debug(STR_METHOD_NAME, l_ex);
////            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
////            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
////
////        }
////        catch (Exception l_ex)
////        {
////            log.error(l_ex.getMessage(), l_ex);
////            fail();
////        }
////        log.exiting(TEST_END + STR_METHOD_NAME);
////    }
//    
//    public void testGetTodayOpenOrderUnitsC0001()
//    {
//        final String STR_METHOD_NAME = " testGetTodayOpenOrderUnitsC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            l_optionOrderManager.getTodayOpenOrderUnits("", null, 0L, 0L);
//            fail();
//        }
//        catch (WEB3SystemLayerException l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    public void testGetTodayOpenOrderUnitsC0002()
//    {
//        final String STR_METHOD_NAME = " testGetTodayOpenOrderUnitsC0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            this.deleteAll();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            l_optionOrderManager.getTodayOpenOrderUnits(null, new InstitutionImpl(l_institutionParams), 0L, 0L);
//            fail();
//        }
//        catch (WEB3SystemLayerException l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    public void testGetTodayOpenOrderUnitsC0003()
//    {
//        final String STR_METHOD_NAME = " testGetTodayOpenOrderUnitsC0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            this.deleteAll();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams1.setBizDate(
//                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
//            
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            OrderUnit[] l_orderUnits = 
//                l_optionOrderManager.getTodayOpenOrderUnits(
//                    "2", new InstitutionImpl(l_institutionParams), 101001010009L, 101001010011L);
//            assertEquals(l_orderUnits.length, 0); 
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    public void testGetTodayOpenOrderUnitsC0004()
//    {
//        final String STR_METHOD_NAME = " testGetTodayOpenOrderUnitsC0004";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            this.deleteAll();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams1.setBizDate(
//                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
//            
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            OrderUnit[] l_orderUnits = 
//                l_optionOrderManager.getTodayOpenOrderUnits(
//                    "1", new InstitutionImpl(l_institutionParams), 101001010009L, 101001010011L);
//            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject(); 
//            assertEquals(l_ifoOrderUnitParams1.getOrderUnitId(), l_ifoOrderUnitRow.getOrderUnitId());
//            assertEquals(l_ifoOrderUnitParams1.getAccountId(), l_ifoOrderUnitRow.getAccountId());
//            assertEquals(l_ifoOrderUnitParams1.getSubAccountId(), l_ifoOrderUnitRow.getSubAccountId());
//            assertEquals(l_ifoOrderUnitParams1.getBranchId(), l_ifoOrderUnitRow.getBranchId());
//            assertEquals(l_ifoOrderUnitParams1.getExecutionConditionType(), l_ifoOrderUnitRow.getExecutionConditionType());
//            assertEquals(l_ifoOrderUnitParams1.getOrderRequestNumber(), l_ifoOrderUnitRow.getOrderRequestNumber());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    public void testGetTodayOpenOrderUnitsC0005()
//    {
//        final String STR_METHOD_NAME = " testGetTodayOpenOrderUnitsC0005";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            this.deleteAll();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            l_branchParams.setBranchId(33382);
//            l_branchParams.setBranchCode("382");
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams1.setBizDate(
//                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
//            IfoOrderUnitParams l_ifoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams2.setOrderUnitId(1002);
//            l_ifoOrderUnitParams2.setAccountId(101001010011L);
//            l_ifoOrderUnitParams2.setOrderId(1002);
//            l_ifoOrderUnitParams2.setOrderRequestNumber("000003007");
//            l_ifoOrderUnitParams2.setBranchId(33382);
//            l_ifoOrderUnitParams2.setBizDate(
//                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams2);
//            
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            OrderUnit[] l_orderUnits = 
//                l_optionOrderManager.getTodayOpenOrderUnits(
//                    "1", new InstitutionImpl(l_institutionParams), 101001010009L, 101001010011L);
//            IfoOrderUnitRow l_ifoOrderUnitRow1 = (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject(); 
//            IfoOrderUnitRow l_ifoOrderUnitRow2 = (IfoOrderUnitRow)l_orderUnits[1].getDataSourceObject();
//
//            assertEquals(l_ifoOrderUnitParams1.getOrderUnitId(), l_ifoOrderUnitRow1.getOrderUnitId());
//            assertEquals(l_ifoOrderUnitParams1.getAccountId(), l_ifoOrderUnitRow1.getAccountId());
//            assertEquals(l_ifoOrderUnitParams1.getSubAccountId(), l_ifoOrderUnitRow1.getSubAccountId());
//            assertEquals(l_ifoOrderUnitParams1.getBranchId(), l_ifoOrderUnitRow1.getBranchId());
//            assertEquals(l_ifoOrderUnitParams1.getExecutionConditionType(), l_ifoOrderUnitRow1.getExecutionConditionType());
//            assertEquals(l_ifoOrderUnitParams1.getOrderRequestNumber(), l_ifoOrderUnitRow1.getOrderRequestNumber());
//
//            assertEquals(l_ifoOrderUnitParams2.getOrderUnitId(), l_ifoOrderUnitRow2.getOrderUnitId());
//            assertEquals(l_ifoOrderUnitParams2.getAccountId(), l_ifoOrderUnitRow2.getAccountId());
//            assertEquals(l_ifoOrderUnitParams2.getSubAccountId(), l_ifoOrderUnitRow2.getSubAccountId());
//            assertEquals(l_ifoOrderUnitParams2.getBranchId(), l_ifoOrderUnitRow2.getBranchId());
//            assertEquals(l_ifoOrderUnitParams2.getExecutionConditionType(), l_ifoOrderUnitRow2.getExecutionConditionType());
//            assertEquals(l_ifoOrderUnitParams2.getOrderRequestNumber(), l_ifoOrderUnitRow2.getOrderRequestNumber());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    public void testGetExpirationDateC0001()
//    {
//        final String STR_METHOD_NAME = " testGetExpirationDateC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            Date l_date = l_optionOrderManager.getExpirationDate(null, "", "", "");
//            assertNull(l_date);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//
//    /**
//     * 『最終日のみ指定可』の場合
//     * 先物OP取引銘柄.売買最終日＜＝パラメータ.注文有効期限の場合、
//     * 先物OP取引銘柄.売買最終日を返却する。
//     */
//    public void testGetExpirationDateC0002()
//    {
//        final String STR_METHOD_NAME = " testGetExpirationDateC0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getAccountId",
//            new Class[] {},
//            new Long(4L));
//        try
//        {
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(1002L);
//            l_marketParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_marketParams);
//            
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002L);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
//            
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setInstitutionCode("0D");
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_productParams);
//            
//            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
//            l_IfoProductParams.setFutureOptionDiv("2");
//            l_IfoProductParams.setProductId(1006169090018L);
//            l_IfoProductParams.setProductCode("160030005");
//            l_IfoProductParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(IfoProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_IfoProductParams);
//            
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(4);
//            l_subAccountParams.setInstitutionId(33);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(4);
//            l_mainAccountParams.setBranchCode("381");
//            
//            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
//            
//            HostOrderexecutionEndParams l_hostOrderexecutionEndParams =
//                this.getHostOrderexecutionEndRow();
//            l_hostOrderexecutionEndParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(HostOrderexecutionEndRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_hostOrderexecutionEndParams);
//            
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            l_institutionParams.setInstitutionId(33);
//            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setInstitutionCode("3B");
//            l_branchParams.setInstitutionId(33);
//            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_branchParams);
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setBranchId(33381);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setAccountId(4);
//            l_ifoOrderUnitParams.setRequestType("0");
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            
//            
//            l_ifoOrderUnitParams.setRequestType("3");
//            l_ifoOrderUnitParams.setFirstOrderUnitId(1);
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            TestDBUtility.deleteAllAndCommit(IfoOrderExecSendMailRow.TYPE);
//            
//            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
//            
//            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
//            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
//            l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
//            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
//            
//            TestDBUtility.insertWithDelAndCommit(l_enableOrderConditionParams);
//            
//            IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
//            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
////            l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            l_IfoTradedProductParams.setInstitutionCode("0D");
//            l_IfoTradedProductParams.setMarketId(1002);
//            l_IfoTradedProductParams.setProductId(1006169090018L);
//            l_IfoTradedProductParams.setUnitSize(10000L);
//            l_IfoTradedProductParams.setUnitMargin(0L);
//            l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
//            l_IfoTradedProductParams.setOrderCloseTime("");
//            l_IfoTradedProductParams.setLastClosingPrice(8D);
//            l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
//            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
//            l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
//            l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//            l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//            TestDBUtility.deleteAllAndCommit(IfoTradedProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_IfoTradedProductParams);
//            
//            
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
//            TestDBUtility.deleteAllAndCommit(IfoTradedProductUpdqParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_ifoTradedProductUpdqParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            Date l_dateParam = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
//            Date l_dateResult = l_optionOrderManager.getExpirationDate(l_dateParam, "160030005", "SP", "1");
//            assertEquals(WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20040718","yyyyMMdd"), l_dateResult), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//
//    /**
//     * 『最終日のみ指定可』の場合
//     * 先物OP取引銘柄.売買最終日＝パラメータ.注文有効期限の場合、
//     * 先物OP取引銘柄.売買最終日を返却する。
//     */
//    public void testGetExpirationDateC0003()
//    {
//        final String STR_METHOD_NAME = " testGetExpirationDateC0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getAccountId",
//            new Class[] {},
//            new Long(4L));
//        try
//        {
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(1002L);
//            l_marketParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_marketParams);
//            
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002L);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
//            
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setInstitutionCode("0D");
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_productParams);
//            
//            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
//            l_IfoProductParams.setFutureOptionDiv("2");
//            l_IfoProductParams.setProductId(1006169090018L);
//            l_IfoProductParams.setProductCode("160030005");
//            l_IfoProductParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(IfoProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_IfoProductParams);
//            
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(4);
//            l_subAccountParams.setInstitutionId(33);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(4);
//            l_mainAccountParams.setBranchCode("381");
//            
//            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
//            
//            HostOrderexecutionEndParams l_hostOrderexecutionEndParams =
//                this.getHostOrderexecutionEndRow();
//            l_hostOrderexecutionEndParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(HostOrderexecutionEndRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_hostOrderexecutionEndParams);
//            
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            l_institutionParams.setInstitutionId(33);
//            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setInstitutionCode("3B");
//            l_branchParams.setInstitutionId(33);
//            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_branchParams);
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setBranchId(33381);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setAccountId(4);
//            l_ifoOrderUnitParams.setRequestType("0");
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            
//            
//            l_ifoOrderUnitParams.setRequestType("3");
//            l_ifoOrderUnitParams.setFirstOrderUnitId(1);
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            TestDBUtility.deleteAllAndCommit(IfoOrderExecSendMailRow.TYPE);
//            
//            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
//            
//            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
//            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
//            l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
//            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
//            
//            TestDBUtility.insertWithDelAndCommit(l_enableOrderConditionParams);
//            
//            IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
//            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
////            l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            l_IfoTradedProductParams.setInstitutionCode("0D");
//            l_IfoTradedProductParams.setMarketId(1002);
//            l_IfoTradedProductParams.setProductId(1006169090018L);
//            l_IfoTradedProductParams.setUnitSize(10000L);
//            l_IfoTradedProductParams.setUnitMargin(0L);
//            l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
//            l_IfoTradedProductParams.setOrderCloseTime("");
//            l_IfoTradedProductParams.setLastClosingPrice(8D);
//            l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
//            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
//            l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
//            l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//            l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//            TestDBUtility.deleteAllAndCommit(IfoTradedProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_IfoTradedProductParams);
//            
//            
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
//            TestDBUtility.deleteAllAndCommit(IfoTradedProductUpdqParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_ifoTradedProductUpdqParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            Date l_dateParam = WEB3DateUtility.getDate("20040718", "yyyyMMdd");
//            Date l_dateResult = l_optionOrderManager.getExpirationDate(l_dateParam, "160030005", "SP", "1");
//            assertEquals(WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20040718","yyyyMMdd"), l_dateResult), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//    
//    /**
//     * 『最終日のみ指定可』の場合
//     * 先物OP取引銘柄.売買最終日 > パラメータ.注文有効期限の場合、
//     * パラメータ.注文有効期限を返却する。
//     */
//    public void testGetExpirationDateC0004()
//    {
//        final String STR_METHOD_NAME = " testGetExpirationDateC0004";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getAccountId",
//            new Class[] {},
//            new Long(4L));
//        try
//        {
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(1002L);
//            l_marketParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_marketParams);
//            
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002L);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
//            
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setInstitutionCode("0D");
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_productParams);
//            
//            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
//            l_IfoProductParams.setFutureOptionDiv("2");
//            l_IfoProductParams.setProductId(1006169090018L);
//            l_IfoProductParams.setProductCode("160030005");
//            l_IfoProductParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(IfoProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_IfoProductParams);
//            
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(4);
//            l_subAccountParams.setInstitutionId(33);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(4);
//            l_mainAccountParams.setBranchCode("381");
//            
//            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
//            
//            HostOrderexecutionEndParams l_hostOrderexecutionEndParams =
//                this.getHostOrderexecutionEndRow();
//            l_hostOrderexecutionEndParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(HostOrderexecutionEndRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_hostOrderexecutionEndParams);
//            
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            l_institutionParams.setInstitutionId(33);
//            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setInstitutionCode("3B");
//            l_branchParams.setInstitutionId(33);
//            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_branchParams);
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setBranchId(33381);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setAccountId(4);
//            l_ifoOrderUnitParams.setRequestType("0");
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            
//            
//            l_ifoOrderUnitParams.setRequestType("3");
//            l_ifoOrderUnitParams.setFirstOrderUnitId(1);
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            TestDBUtility.deleteAllAndCommit(IfoOrderExecSendMailRow.TYPE);
//            
//            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
//            
//            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
//            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
//            l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
//            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
//            
//            TestDBUtility.insertWithDelAndCommit(l_enableOrderConditionParams);
//            
//            IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
//            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
////            l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            l_IfoTradedProductParams.setInstitutionCode("0D");
//            l_IfoTradedProductParams.setMarketId(1002);
//            l_IfoTradedProductParams.setProductId(1006169090018L);
//            l_IfoTradedProductParams.setUnitSize(10000L);
//            l_IfoTradedProductParams.setUnitMargin(0L);
//            l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
//            l_IfoTradedProductParams.setOrderCloseTime("");
//            l_IfoTradedProductParams.setLastClosingPrice(8D);
//            l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
//            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
//            l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
//            l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//            l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//            TestDBUtility.deleteAllAndCommit(IfoTradedProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_IfoTradedProductParams);
//            
//            
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
//            TestDBUtility.deleteAllAndCommit(IfoTradedProductUpdqParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_ifoTradedProductUpdqParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            Date l_dateParam = WEB3DateUtility.getDate("20040618", "yyyyMMdd");
//            Date l_dateResult = l_optionOrderManager.getExpirationDate(l_dateParam, "160030005", "SP", "1");
//            assertEquals(WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20040618","yyyyMMdd"), l_dateResult), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//    
//    /**
//     * 『最終日のみ指定可』以外の場合
//     * パラメータ.注文有効期限を返却する。
//     */
//    public void testGetExpirationDateC0005()
//    {
//        final String STR_METHOD_NAME = " testGetExpirationDateC0005";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getAccountId",
//            new Class[] {},
//            new Long(4L));
//        try
//        {
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(1002L);
//            l_marketParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_marketParams);
//            
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002L);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
//            
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setInstitutionCode("0D");
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_productParams);
//            
//            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
//            l_IfoProductParams.setFutureOptionDiv("2");
//            l_IfoProductParams.setProductId(1006169090018L);
//            l_IfoProductParams.setProductCode("160030005");
//            l_IfoProductParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(IfoProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_IfoProductParams);
//            
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(4);
//            l_subAccountParams.setInstitutionId(33);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
//            
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(4);
//            l_mainAccountParams.setBranchCode("381");
//            
//            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
//            
//            HostOrderexecutionEndParams l_hostOrderexecutionEndParams =
//                this.getHostOrderexecutionEndRow();
//            l_hostOrderexecutionEndParams.setInstitutionCode("0D");
//            TestDBUtility.deleteAllAndCommit(HostOrderexecutionEndRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_hostOrderexecutionEndParams);
//            
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            l_institutionParams.setInstitutionId(33);
//            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setInstitutionCode("3B");
//            l_branchParams.setInstitutionId(33);
//            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_branchParams);
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setBranchId(33381);
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setAccountId(4);
//            l_ifoOrderUnitParams.setRequestType("0");
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            
//            
//            l_ifoOrderUnitParams.setRequestType("3");
//            l_ifoOrderUnitParams.setFirstOrderUnitId(1);
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            TestDBUtility.deleteAllAndCommit(IfoOrderExecSendMailRow.TYPE);
//            
//            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
//            
//            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
//            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
//            l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
//            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
//            
//            TestDBUtility.insertWithDelAndCommit(l_enableOrderConditionParams);
//            
//            IfoTradedProductParams l_IfoTradedProductParams = new IfoTradedProductParams();
//            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
////            l_IfoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
//            l_IfoTradedProductParams.setInstitutionCode("0D");
//            l_IfoTradedProductParams.setMarketId(1002);
//            l_IfoTradedProductParams.setProductId(1006169090018L);
//            l_IfoTradedProductParams.setUnitSize(10000L);
//            l_IfoTradedProductParams.setUnitMargin(0L);
//            l_IfoTradedProductParams.setPerOrderMaxUnits(1000L);
//            l_IfoTradedProductParams.setOrderCloseTime("");
//            l_IfoTradedProductParams.setLastClosingPrice(8D);
//            l_IfoTradedProductParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
//            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040718","yyyyMMdd")); 
//            l_IfoTradedProductParams.setTradeStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
//            l_IfoTradedProductParams.setListFlag(BooleanEnum.TRUE);
//            l_IfoTradedProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//            l_IfoTradedProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//            TestDBUtility.deleteAllAndCommit(IfoTradedProductParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_IfoTradedProductParams);
//            
//            
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = getIfoTradedProductUpdqRow();
//            TestDBUtility.deleteAllAndCommit(IfoTradedProductUpdqParams.TYPE);
//            TestDBUtility.insertWithDelAndCommit(l_ifoTradedProductUpdqParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            Date l_dateParam = WEB3DateUtility.getDate("20040718", "yyyyMMdd");
//            Date l_dateResult = l_optionOrderManager.getExpirationDate(l_dateParam, "160030005", "SP", "1");
//            assertEquals(WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20040718","yyyyMMdd"), l_dateResult), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//    
//    public void estIsManualOrderPossibleC0001()
//    {
//        final String STR_METHOD_NAME = " testIsManualOrderPossibleC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            this.deleteAll();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1001);
//            l_ifoOrderUnitParams.setOrderConditionType("1");
//            l_ifoOrderUnitParams.setRequestType("0");
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(1001);
//            
//            boolean l_blnIsManualOrderPossible = l_optionOrderManager.isManualOrderPossible(l_orderUnit);
//            
//            assertTrue(l_blnIsManualOrderPossible);
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estIsManualOrderPossibleC0002()
//    {
//        final String STR_METHOD_NAME = " testIsManualOrderPossibleC0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            this.deleteAll();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1001);
//            l_ifoOrderUnitParams.setOrderConditionType("2");
//            l_ifoOrderUnitParams.setRequestType("0");
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(1001);
//            
//            boolean l_blnIsManualOrderPossible = l_optionOrderManager.isManualOrderPossible(l_orderUnit);
//            
//            assertTrue(l_blnIsManualOrderPossible);
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estCreateContractUnitByOrderC0001()
//    {
//        final String STR_METHOD_NAME = " testCreateContractUnitByOrderC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("1");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setProductId(1006169090018L);
//            l_ifoOrderUnitParams.setMarketId(1002);
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070201","yyyyMMdd"));
//            l_ifoContractParams.setProductId(1006169090018L);
//            l_ifoContractParams.setSessionType("1");
//            
//            l_tradedProductParams.setTradedProductId(l_ifoTradedProductParams.getTradedProductId());
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);      
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            long l_lngOrderId = 1001;
//           
//            WEB3FuturesOptionsContractUnit[] l_FuturesOptionsContractUnits=
//                l_optionOrderManager.createContractUnitByOrder(l_lngOrderId);
//            
//            assertEquals("1", l_FuturesOptionsContractUnits[0].sessionType);
//           
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void estCreateContractUnitByOrderC0002()
//    {
//        final String STR_METHOD_NAME = " testCreateContractUnitByOrderC0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("1");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setProductId(1006169090018L);
//            l_ifoOrderUnitParams.setMarketId(1002);
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070201","yyyyMMdd"));
//            l_ifoContractParams.setProductId(1006169090018L);
//            l_ifoContractParams.setSessionType("1");
//            
//            l_tradedProductParams.setTradedProductId(l_ifoTradedProductParams.getTradedProductId());
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);      
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            long l_lngOrderId = 1001;
//           
//            WEB3FuturesOptionsContractUnit[] l_FuturesOptionsContractUnits=
//                l_optionOrderManager.createContractUnitByOrder(l_lngOrderId);
//            
//            assertEquals("-207900", l_FuturesOptionsContractUnits[0].contractCommission);
//           
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void testCalcChangeEstimateDeliveryAmount_C0001()
//    {
//        final String STR_METHOD_NAME = "testCalcChangeEstimateDeliveryAmount_C0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoBizLogicProvider", 
//            "calcRestraintTurnOver",
//            new Class[] 
//            {
//                double.class, 
//                double.class, 
//                long.class, 
//                String.class, 
//                boolean.class, 
//                WEB3IfoTradedProductImpl.class
//            },
//            new Double(2.2D));
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoBizLogicProvider", 
//            "calcCommission",
//            new Class[] {WEB3GentradeCommission.class, SubAccount.class},
//            null);
//            
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoBizLogicProvider", 
//            "calcSalesTax",
//            new Class[] {double.class, Timestamp.class, SubAccount.class},
//            new Double(0.0D));
//        
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();  
//            
//            this.deleteAll();
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(33381L);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            //參數設定
//            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
//            l_commission.setBranchId(33381L);
//            
//            double l_dblLimitPrice = 5.0d;
//            WEB3GentradeSubAccount l_subAccount = null;
//            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct = null;
//            double l_dblQuantity = 2.4d;
//            SideEnum l_dealing = null;
//            boolean l_blnIsClosingContractOrder = false;
//            double l_dblExecQuantity = 1.1d;
//            double l_dblSumTransferredAssetBookValue = 4.4d;
//            boolean l_blnIsSkipPriceCheck = true;
//            
//            WEB3IfoEstimateDeliveryAmountCalcResult l_result =
//                l_optionOrderManager.calcChangeEstimateDeliveryAmount(
//                    l_commission,
//                    l_dblLimitPrice,
//                    l_subAccount,
//                    l_futuresOptionTradedProduct,
//                    l_dblQuantity,
//                    l_dealing,
//                    l_blnIsClosingContractOrder,
//                    l_dblExecQuantity,
//                    l_dblSumTransferredAssetBookValue,
//                    l_blnIsSkipPriceCheck);
//            
//            assertEquals(6.6, l_commission.getExpensesCalcAmount(), 0); 
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void testCalcChangeEstimateDeliveryAmount_C0002()
//    {
//        final String STR_METHOD_NAME = "testCalcChangeEstimateDeliveryAmount_C0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoBizLogicProvider", 
//            "calcTurnOver",
//            new Class[] 
//            {
//                double.class, 
//                double.class,  
//                WEB3IfoTradedProductImpl.class
//            },
//            new Double(2.2D));
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoBizLogicProvider", 
//            "calcCommission",
//            new Class[] {WEB3GentradeCommission.class, SubAccount.class},
//            null);
//            
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoBizLogicProvider", 
//            "calcSalesTax",
//            new Class[] {double.class, Timestamp.class, SubAccount.class},
//            new Double(0.0D));
//        
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();  
//            
//            this.deleteAll();
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(33381L);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            //參數設定
//            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
//            l_commission.setBranchId(33381L);
//            
//            double l_dblLimitPrice = 5.0d;
//            WEB3GentradeSubAccount l_subAccount = null;
//            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct = null;
//            double l_dblQuantity = 2.4d;
//            SideEnum l_dealing = null;
//            boolean l_blnIsClosingContractOrder = true;
//            double l_dblExecQuantity = 1.1d;
//            double l_dblSumTransferredAssetBookValue = 4.4d;
//            boolean l_blnIsSkipPriceCheck = true;
//            
//            WEB3IfoEstimateDeliveryAmountCalcResult l_result =
//                l_optionOrderManager.calcChangeEstimateDeliveryAmount(
//                    l_commission,
//                    l_dblLimitPrice,
//                    l_subAccount,
//                    l_futuresOptionTradedProduct,
//                    l_dblQuantity,
//                    l_dealing,
//                    l_blnIsClosingContractOrder,
//                    l_dblExecQuantity,
//                    l_dblSumTransferredAssetBookValue,
//                    l_blnIsSkipPriceCheck);
//            
//            assertEquals(6.6, l_commission.getExpensesCalcAmount(), 0); 
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void estValidateChangeOrderC0001()
//    {
//        final String STR_METHOD_NAME = " testValidateChangeOrderC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderForChangeability",
//            new Class[] {Order.class, boolean.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateHandlingIndex",
//            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderCond",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, boolean.class, 
//                WEB3IfoTradedProductImpl.class, boolean.class,
//                boolean.class, Date.class, Date.class, String.class, 
//                IfoOrderExecutionConditionType.class, String.class, Long.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderUnitPrice",
//            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateWLimitPriceOrder",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, double.class, 
//                String.class, double.class,
//                String.class,
//                IfoOrderExecutionConditionType.class, String.class,
//                WEB3IfoTradedProductImpl.class,
//                boolean.class, boolean.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderChangeSpec",
//            new Class[]{
//                OrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class, String.class,
//                String.class, String.class, double.class, double.class, 
//                IfoOrderExecutionConditionType.class, Date.class, String.class,
//                SettleContractEntry[].class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateChangeOrderRevLimit",
//            new Class[]{
//                IfoOrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class},
//                null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.BOND_BUY);
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);  
//            
//            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
//                new WEB3IfoOpenContractChangeSpec(1001, 1001, 200, 200);
//
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeOrder(
//                l_subAccount,
//                l_ifoOpenContractChangeSpec,
//                l_blnIsSkipDelayStatusCheck);
//            
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//                "validateOrderCond",
//                new Class[]{
//                    WEB3GentradeSubAccount.class,
//                    long.class, boolean.class, 
//                    WEB3IfoTradedProductImpl.class, boolean.class,
//                    boolean.class, Date.class, Date.class, String.class, 
//                    IfoOrderExecutionConditionType.class, String.class, Long.class});
//            assertEquals("1001", l_paramsValue1.getFirstCalled()[11] + "");
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    
//    public void estValidateChangeSettleContractOrderC1()
//    {
//        final String STR_METHOD_NAME = " testValidateChangeSettleContractOrderC1";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderForChangeability",
//            new Class[] {Order.class, boolean.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateHandlingIndex",
//            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderCond",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, boolean.class, 
//                WEB3IfoTradedProductImpl.class, boolean.class,
//                boolean.class, Date.class, Date.class, String.class, 
//                IfoOrderExecutionConditionType.class, String.class, Long.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderUnitPrice",
//            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
//            null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateWLimitPriceOrder",
//            new Class[]{
//                WEB3GentradeSubAccount.class,
//                long.class, double.class, 
//                String.class, double.class,
//                String.class,
//                IfoOrderExecutionConditionType.class, String.class,
//                WEB3IfoTradedProductImpl.class,
//                boolean.class, boolean.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateOrderChangeSpec",
//            new Class[]{
//                OrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class, String.class,
//                String.class, String.class, double.class, double.class, 
//                IfoOrderExecutionConditionType.class, Date.class, String.class,
//                SettleContractEntry[].class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//            "validateChangeOrderRevLimit",
//            new Class[]{
//                IfoOrderUnit.class,
//                double.class, double.class, 
//                IfoOrderExecutionConditionType.class},
//                null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//     
//            l_ifoOrderParams.setOrderId(1001);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);  
//            
//            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
//            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
//            
//            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
//                new WEB3IfoChangeSettleContractOrderSpec(
//                    1001, 1001, 200, l_SettleContractEntry);
//            
//            boolean l_blnIsSkipDelayStatusCheck = false;
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateChangeSettleContractOrder(
//                l_subAccount,
//                l_changeSettleContractOrderSpec,
//                l_blnIsSkipDelayStatusCheck);
//            
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//                "validateOrderCond",
//                new Class[]{
//                    WEB3GentradeSubAccount.class,
//                    long.class, boolean.class, 
//                    WEB3IfoTradedProductImpl.class, boolean.class,
//                    boolean.class, Date.class, Date.class, String.class, 
//                    IfoOrderExecutionConditionType.class, String.class, Long.class});
//                assertEquals("1001", l_paramsValue1.getFirstCalled()[11] + "");
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estRemoveCarryOverOriginalOrderUnitC0001()
//    {
//        final String STR_METHOD_NAME = " testRemoveCarryOverOriginalOrderUnitC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            this.deleteAll();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1001);
//            l_ifoOrderUnitParams.setOrderConditionType("2");
//            l_ifoOrderUnitParams.setRequestType("0");
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnitParams[] l_ifoOrderUnit = new IfoOrderUnitParams[1];
//            
//            l_ifoOrderUnit[0] = l_ifoOrderUnitParams;
//            
//            IfoOrderUnitParams[] l_IfoOrderUnits = 
//                l_optionOrderManager.removeCarryOverOriginalOrderUnit(l_ifoOrderUnit);
//            
//            assertEquals("1001", l_IfoOrderUnits[0].getOrderUnitId() + "");
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estRemoveCarryOverOriginalOrderUnitC0002()
//    {
//        final String STR_METHOD_NAME = " testRemoveCarryOverOriginalOrderUnitC0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            this.deleteAll();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1001);
//            l_ifoOrderUnitParams.setOrderConditionType("2");
//            l_ifoOrderUnitParams.setRequestType("0");
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setOrderRequestNumber("2");
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams1.setOrderUnitId(1002);
//            l_ifoOrderUnitParams1.setOrderConditionType("2");
//            l_ifoOrderUnitParams1.setRequestType("0");
//            l_ifoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams1.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            l_ifoOrderUnitParams1.setFirstOrderUnitId(1001);
//            l_ifoOrderUnitParams1.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams1.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams1.setOrderRequestNumber("1");
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnitParams[] l_ifoOrderUnit = new IfoOrderUnitParams[2];
//            
//            l_ifoOrderUnit[0] = l_ifoOrderUnitParams;
//            l_ifoOrderUnit[1] = l_ifoOrderUnitParams1;
//            
//            IfoOrderUnitParams[] l_IfoOrderUnits = 
//                l_optionOrderManager.removeCarryOverOriginalOrderUnit(l_ifoOrderUnit);
//            
//            log.debug("l_IfoOrderUnitslength ==========" + l_IfoOrderUnits.length);
//            assertEquals("1002", l_IfoOrderUnits[0].getOrderUnitId() + "");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void estRemoveCarryOverOriginalOrderUnitC0003()
//    {
//        final String STR_METHOD_NAME = " testRemoveCarryOverOriginalOrderUnitC0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnitParams[] l_ifoOrderUnit = null;
//
//            IfoOrderUnitParams[] l_IfoOrderUnits = 
//                l_optionOrderManager.removeCarryOverOriginalOrderUnit(l_ifoOrderUnit);
//
//            assertNull(l_IfoOrderUnits);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estIsCarryoverOrderC0001()
//    {
//        final String STR_METHOD_NAME = " testIsCarryoverOrderC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
//            l_context.setInstitutionCode("0D");
//            l_context.setBranchCode("381");
//            l_context.setProductCode("N8080");
//            l_context.setBizDateType("1");
//            l_context.setMarketCode("1");
//            l_context.setTradingTimeType("01");
//            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
//                l_context);
//            
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            
//            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
//            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
//            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
//            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
//            l_tradingTimeParams.setProductCode(l_context.getProductCode());
//            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
//            l_tradingTimeParams.setSessionType("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setAccountId(101001010010L);
//            l_ifoOrderUnitParams.setSessionType(null);
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            
//            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
//            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(1001);
//            
//            boolean l_blnIsCarryoverOrder = l_optionOrderManager.isCarryoverOrder(l_ifoOrderUnit);
//            
//            assertFalse(l_blnIsCarryoverOrder);
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estIsCarryoverOrderC0002()
//    {
//        final String STR_METHOD_NAME = " testIsCarryoverOrderC0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
//            l_context.setInstitutionCode("0D");
//            l_context.setBranchCode("381");
//            l_context.setProductCode("N8080");
//            l_context.setBizDateType("1");
//            l_context.setMarketCode("1");
//            l_context.setTradingTimeType("01");
//            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
//                l_context);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getBizDate",
//                    new Class[] {},
//                    new Date());
//            
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
//            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
//            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
//            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
//            l_tradingTimeParams.setProductCode(l_context.getProductCode());
//            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
//            l_tradingTimeParams.setSessionType("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setAccountId(101001010010L);
//            l_ifoOrderUnitParams.setSessionType(null);
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20070215","yyyyMMdd"));
//            
//            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
//            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(1001);
//            
//            boolean l_blnIsCarryoverOrder = l_optionOrderManager.isCarryoverOrder(l_ifoOrderUnit);
//            
//            assertFalse(l_blnIsCarryoverOrder);
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void estIsCarryoverOrderC0003()
//    {
//        final String STR_METHOD_NAME = " testIsCarryoverOrderC0003";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
//            l_context.setInstitutionCode("0D");
//            l_context.setBranchCode("381");
//            l_context.setProductCode("N8080");
//            l_context.setBizDateType("1");
//            l_context.setMarketCode("1");
//            l_context.setTradingTimeType("01");
//            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
//                l_context);
//            
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
//            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
//            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
//            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
//            l_tradingTimeParams.setProductCode(l_context.getProductCode());
//            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
//            l_tradingTimeParams.setSessionType("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFutureOptionDiv("2");
//            l_ifoOrderUnitParams.setAccountId(101001010010L);
//            l_ifoOrderUnitParams.setSessionType("1");
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            
//            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
//            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(1001);
//            
//            boolean l_blnIsCarryoverOrder = l_optionOrderManager.isCarryoverOrder(l_ifoOrderUnit);
//            
//            assertTrue(l_blnIsCarryoverOrder);
//
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    //連続注文対応//TODO
//    //notify親注文全部約定
//    //１－１）　@予約注文確認要否の判定 
//    //　@this.is予約注文確認要()をコールする。
//    //戻り値がfalseの場合、予約注文が設定されていないため処理を終了する（return)
//    public void testNotifyParentOrderFullyExecutedCase1()
//    {
//        final String STR_METHOD_NAME = "testNotifyParentOrderFullyExecutedCase1()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
//            
//            l_optionOrderManager.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.FILL_ORDER);
//        }
//        catch (Exception l_ex)
//        {
//            fail();
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//        }
//    }
//    
//    //2）全部約定かどうかの判定 
//    //引数の注文単位.isFullyExecuted()をコールする。 
//    //戻り値がfalseの場合、全部約定でないため処理を終了する（return) 
//    public void testNotifyParentOrderFullyExecutedCase2()
//    {
//        final String STR_METHOD_NAME = "testNotifyParentOrderFullyExecutedCase2()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
//            
//            l_optionOrderManager.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.FILL_ORDER);
//        }
//        catch (Exception l_ex)
//        {
//            fail();
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//        }
//    }
//
//    //３）　@有効予約注文の確認 
//    //　@有効な予約注文の一覧を取得する。 
//    //　@先物OP予約注文更新サービスImpl.get有効予約注文単位一覧()をコールする
//    public void testNotifyParentOrderFullyExecutedCase3()
//    {
//        final String STR_METHOD_NAME = "testNotifyParentOrderFullyExecutedCase3()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
//            l_ifoOrderUnitParams.setExecutedQuantity(10.0);
//            l_ifoOrderUnitParams.setConfirmedQuantity(10.0);
//            
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            SubAccountParams l_subaccountParams = TestDBUtility.getSubAccountRow();
//            l_subaccountParams.setAccountId(101001010010L);
//            l_subaccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_subaccountParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
//            
//            l_optionOrderManager.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.FILL_ORDER);
//        }
//        catch (Exception l_ex)
//        {
//            fail();
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//        }
//    }
//    
//    //４）　@全部約定の通知 
//    //ルールエンジンサーバに親注文の全部約定を通知する。 
//    //WEB3RlsRequestSenderService.sendConOrderExecuteMessage()をコールする。 
//    public void testNotifyParentOrderFullyExecutedCase4()
//    {
//        final String STR_METHOD_NAME = "testNotifyParentOrderFullyExecutedCase4()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            MOCK_MANAGER.setIsMockUsed(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "notifyRLS",
//                    new Class[]{ IfoOrderUnit.class, OrderManagerPersistenceContext.class },
//                    null);
//                    
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
//            l_ifoOrderUnitParams.setExecutedQuantity(10.0);
//            l_ifoOrderUnitParams.setConfirmedQuantity(10.0);
//            l_ifoOrderUnitParams.setOrderId(56);
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = this.getRsvIfoOrderUnitParams();
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
//            
//            l_optionOrderManager.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.FILL_ORDER);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//            
//        }
//    }
//    
//    //notify連続注文
//    //１）　@予約注文単位かどうかの判定　@ 
//    //　@引数の注文単位.getDataSourceObject()をコールする。　@ 
//    //　@メソッドの戻り値の型が、 
//    //　@先物OP予約注文単位Rowでない場合、 
//    //　@処理対象外である為、処理を終了する。（return）
//    public void testNotifyToSuccOrderCase1()
//    {
//        final String STR_METHOD_NAME = "testNotifyToSuccOrderCase1()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
//            l_ifoOrderUnitParams.setExecutedQuantity(10.0);
//            l_ifoOrderUnitParams.setConfirmedQuantity(10.0);
//            l_ifoOrderUnitParams.setOrderId(56);
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = this.getRsvIfoOrderUnitParams();
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
//            
//            l_optionOrderManager.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.ASSET_TRANSFER_DONE);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//    
//    //新規登録（１）の戻り値.注文状態 == "受付済（新規注文）"）の場合
//    public void testNotifyToSuccOrderCase2()
//    {
//        final String STR_METHOD_NAME = "testNotifyToSuccOrderCase2()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            MOCK_MANAGER.setIsMockUsed(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "notifyRLS",
//                    new Class[]{ IfoOrderUnit.class, OrderManagerPersistenceContext.class },
//                    null);
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
//            l_ifoOrderUnitParams.setExecutedQuantity(10.0);
//            l_ifoOrderUnitParams.setConfirmedQuantity(10.0);
//            l_ifoOrderUnitParams.setOrderId(56);
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = this.getRsvIfoOrderUnitParams();
//            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl =
//                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
//            
//            l_optionOrderManager.notifyRLS(l_ifoOrderUnitImpl, OrderManagerPersistenceContext.ASSET_TRANSFER_DONE);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//    
//    //新規登録（１）の戻り値.注文状態 ！= "受付済（新規注文）"）の場合
//    public void testNotifyToSuccOrderCase3()
//    {
//        final String STR_METHOD_NAME = "testNotifyToSuccOrderCase3()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
//            l_ifoOrderUnitParams.setExecutedQuantity(10.0);
//            l_ifoOrderUnitParams.setConfirmedQuantity(10.0);
//            l_ifoOrderUnitParams.setOrderId(56);
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = this.getRsvIfoOrderUnitParams();
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//            
//            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl =
//                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
//            
//            l_optionOrderManager.notifyRLS(l_ifoOrderUnitImpl, OrderManagerPersistenceContext.ASSET_TRANSFER_DONE);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//    
//    //is予約注文確認要
//    //引数の注文単位.予約注文設定フラグ ≠ "1：設定の可能性あり"の場合
//    public void testIsReserveOrderExistCase1()
//    {
//        final String STR_METHOD_NAME = "testIsReserveOrderExistCase1()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
//            l_ifoOrderUnitParams.setExecutedQuantity(10.0);
//            l_ifoOrderUnitParams.setConfirmedQuantity(10.0);
//            l_ifoOrderUnitParams.setOrderId(56);
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("2");
//            
//            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = this.getRsvIfoOrderUnitParams();
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
//           
//            boolean l_blnIsReserveOrderExist = l_optionOrderManager.isReserveOrderExist(l_orderUnit);
//            
//            assertFalse(l_blnIsReserveOrderExist);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//    
//    //引数の注文単位.予約注文設定フラグ == "1：設定の可能性あり"の場合
//    public void testIsReserveOrderExistCase2()
//    {
//        final String STR_METHOD_NAME = "testIsReserveOrderExistCase2()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
//            l_ifoOrderUnitParams.setExecutedQuantity(10.0);
//            l_ifoOrderUnitParams.setConfirmedQuantity(10.0);
//            l_ifoOrderUnitParams.setOrderId(56);
//            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
//            
//            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = this.getRsvIfoOrderUnitParams();
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_optionOrderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
//           
//            boolean l_blnIsReserveOrderExist = l_optionOrderManager.isReserveOrderExist(l_orderUnit);
//            
//            assertTrue(l_blnIsReserveOrderExist);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }
//    
//    
//    //createSettleContractEntry
//    //訂正注文（注文単位ID > 0）の場合
//    public void testCreateSettleContractEntryCase1()
//    {
//        final String STR_METHOD_NAME = "testCreateSettleContractEntryCase1()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnits =
//                new WEB3FuturesOptionsCloseMarginContractUnit[1];
//            WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
//            
//            l_contractUnits[0] = l_contractUnit;
//            l_optionOrderManager.createSettleContractEntry(11, 1.0, l_contractUnits);//注文單位ID 11
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
//        }
//        catch (NumberFormatException l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//          fail();
//        }
//    }
//    
//    //訂正注文（注文単位ID< 0）の場合
//    public void testCreateSettleContractEntryCase2()
//    {
//        final String STR_METHOD_NAME = "testCreateSettleContractEntryCase2()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnits =
//                new WEB3FuturesOptionsCloseMarginContractUnit[1];
//            WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
//            
//            l_contractUnits[0] = l_contractUnit;
//            l_optionOrderManager.createSettleContractEntry(-1, 1.0, l_contractUnits);//注文單位ID -1
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
//            fail();
//        }
//        catch (NumberFormatException l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//          fail();
//        }
//    }
//    
//    //訂正注文（注文単位ID= 0）の場合
//    public void testCreateSettleContractEntryCase3()
//    {
//        final String STR_METHOD_NAME = "testCreateSettleContractEntryCase3()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnits =
//                new WEB3FuturesOptionsCloseMarginContractUnit[1];
//            WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
//            
//            l_contractUnits[0] = l_contractUnit;
//            l_optionOrderManager.createSettleContractEntry(0, 1.0, l_contractUnits);//注文單位ID 0
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
//            fail();
//        }
//        catch (NumberFormatException l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//          fail();
//        }
//    }
//    
//    /**
//     * validate返済注文
//     * OP返済注文発注審査を行う。 
//     this.validate返済注文()に処理を委譲（delegate）する。 
//     */
//    public void testValidateSettleContractOrderCase0001()
//    {
//        final String STR_METHOD_NAME = "testValidateSettleContractOrderCase0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
//            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
//
//            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
//                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
//                        null,null,0.0,null,null,l_SettleContractEntry,null,0.0,0.0,null,null,null,true);
//
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateSettleContractOrder(
//                l_subAccount,
//                l_settleContractOrderSpec);
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003,
//                l_orderValidationResult.getProcessingResult().getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * validate返済注文
//     * OP返済注文発注審査を行う。 
//     */
//    public void testValidateSettleContractOrderCase0002()
//    {
//        final String STR_METHOD_NAME = "testValidateSettleContractOrderCase0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
//            "validateOrder",
//            new Class[] {SubAccount.class,String.class},
//            null);
//
//        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//
//        try
//        {
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//                      
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//
//            l_marketParams.setMarketId(1002);
//
//            l_ifoContractParams.setAccountId(101001010010L);
//            l_ifoContractParams.setSubAccountId(10100101001007L);
//            l_ifoContractParams.setProductId(1006169090018L);
//            
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
//
//            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
//            l_SettleContractEntry[0] = new SettleContractEntry(1003, 200);
//
//            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
//                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
//                        null,null,0.0,null,null,l_SettleContractEntry,null,0.0,0.0,null,null,null,true);
//
//            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(1001);
//            
//            OrderValidationResult l_orderValidationResult = l_optionOrderManager.validateSettleContractOrder(
//                l_subAccount,
//                l_settleContractOrderSpec,
//                l_ifoContractImpl);
//            
//            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003,
//                l_orderValidationResult.getProcessingResult().getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testCreateContractUnitByOrderC0001()
//    {
//        final String STR_METHOD_NAME = " testCreateContractUnitByOrderC0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            IfoClosingContractSpecParams l_ifoClosingContractSpecParams = TestDBUtility.getIfoClosingContractSpecRow();
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            
//            l_mainAccountParams.setAccountId(101001010010L);
//
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setBizDate("20070101");
//            l_ifoOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
//            l_ifoOrderUnitParams.setFutureOptionDiv("1");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(null);
//            l_ifoOrderUnitParams.setProductId(1006169090018L);
//            l_ifoOrderUnitParams.setMarketId(1002);
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
//
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoProductParams.setInstitutionCode("0D");
//            l_ifoProductParams.setUnderlyingProductCode("1");
//            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
//            l_ifoProductParams.setStrikePrice(11);
//            l_ifoProductParams.setMonthOfDelivery("1111");
//            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
//            
//            l_marketParams.setMarketId(1002);
//            l_marketParams.setMarketCode("1");
//            
//            l_ifoTradedProductParams.setInstitutionCode("0D");
//            l_ifoTradedProductParams.setMarketId(1002);
//            l_ifoTradedProductParams.setProductId(1006169090018L);
//            
//            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
//            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070201","yyyyMMdd"));
//            l_ifoContractParams.setProductId(1006169090018L);
//            l_ifoContractParams.setSessionType("1");
//            l_ifoContractParams.setContractPrice(3);
//            
//            l_tradedProductParams.setTradedProductId(l_ifoTradedProductParams.getTradedProductId());
//            l_tradedProductParams.setProductId(1006169090018L);
//            l_tradedProductParams.setMarketId(1002);
//            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
//            
//            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//            l_ifoTradedProductUpdqParams.setMarketId(1002);
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);      
//            
//            this.deleteAll();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_marketParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            TestDBUtility.insertWithDel(l_branchParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//            TestDBUtility.commit();
//            
//            long l_lngOrderId = 1001;
//           
//            WEB3FuturesOptionsContractUnit[] l_FuturesOptionsContractUnits=
//                l_optionOrderManager.createContractUnitByOrder(l_lngOrderId);
//            
//            assertEquals("1", l_FuturesOptionsContractUnits[0].sessionType);
//            assertEquals("257900", l_FuturesOptionsContractUnits[0].incomeCost);
//           
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//    
//    public void testGetNetAmount_C0001()
//    {
//        final String STR_METHOD_NAME = " testGetNetAmount_C0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
//                "getNetAmount",
//                new Class[] {OrderUnit.class},
//                new Double(13.6));
//            
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            WEB3OptionOrderManagerImpl l_optionOrderManager = 
//                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
//
//            this.deleteAll();
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderType(new OrderTypeEnum(605, "IDX_OPTIONS_BUY_TO_OPEN"));
//            l_ifoOrderUnitParams.setOrderCateg(new OrderCategEnum(93, "IDX_OPTIONS_OPEN"));
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
//
//            double l_dblReturnValue = l_optionOrderManager.getNetAmount(l_ifoOrderUnit);
//              
//            assertEquals(-13.6, l_dblReturnValue, 1);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }
//
//    public RsvIfoOrderUnitParams getRsvIfoOrderUnitParams()
//    {
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
//            l_rsvIfoOrderUnitParams.setAccountId(101001010010L);
//            l_rsvIfoOrderUnitParams.setSubAccountId(10100101001007L);
//            l_rsvIfoOrderUnitParams.setBranchId(1234);
//            l_rsvIfoOrderUnitParams.setOrderId(21);
//            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
//            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
//            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
//            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
//            l_rsvIfoOrderUnitParams.setQuantity(23);
//            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
//            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
//            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
//            l_rsvIfoOrderUnitParams.setBizDate("20070117");
//            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
//            l_rsvIfoOrderUnitParams.setParentOrderId(56);
//            l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
//            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            
//            return l_rsvIfoOrderUnitParams;
//    }
//    public void deleteAll()
//    {
//        try
//        {
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            TestDBUtility.deleteAll(IfoContractRow.TYPE);
//            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
//            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
//            TestDBUtility.deleteAll(IfoContractRow.TYPE);
//            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
//            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info("*******************deleteAll***************** !!");
//    }
//    
//    public void estDeleAll()
//    {
//        this.deleteAll();
//    }
//    
//    public HostOrderexecutionEndParams getHostOrderexecutionEndRow()
//    {
//        HostOrderexecutionEndParams l_params = new HostOrderexecutionEndParams();
//        
//        l_params.setRequestCode("EI814");
//        l_params.setInstitutionCode("0D");
//        l_params.setProductType(ProductTypeEnum.IFO);
//        l_params.setFutureOptionDiv("1");
//        l_params.setStatus("0");
//        
//        return l_params;
//    }  
//    
//    /**
//     * 先物OP取引銘柄ﾏｽﾀ（一時ﾃｰﾌﾞﾙ）(ifo_traded_product_updq)
//     */
//    public static IfoTradedProductUpdqParams getIfoTradedProductUpdqRow()
//    {
//        Date l_datNextBizDate = null;
//        try
//        {
//            l_datNextBizDate = new WEB3GentradeBizDate(
//                    new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime())).roll(1);
//        }
//        catch (WEB3SystemLayerException e)
//        {
//            fail();
//        }
//        
//        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = new IfoTradedProductUpdqParams();
//        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
//        l_ifoTradedProductUpdqParams.setValidForBizDate(WEB3DateUtility.formatDate(l_datNextBizDate,"yyyyMMdd"));
//        l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
//        l_ifoTradedProductUpdqParams.setMarketId(1002);
//        l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
//        l_ifoTradedProductUpdqParams.setUnitSize(10000D);
//        l_ifoTradedProductUpdqParams.setUnitMargin(0L);
//        l_ifoTradedProductUpdqParams.setPerOrderMaxUnits(1000D);
//        l_ifoTradedProductUpdqParams.setOrderCloseTime("0");
//        l_ifoTradedProductUpdqParams.setLastClosingPrice(28);
//        l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040708","yyyyMMdd"));
//        l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
//        l_ifoTradedProductUpdqParams.setTradeStopFlag(BooleanEnum.FALSE);
//        l_ifoTradedProductUpdqParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
//        l_ifoTradedProductUpdqParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
//        l_ifoTradedProductUpdqParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
//        l_ifoTradedProductUpdqParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
//        l_ifoTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
//        l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//        l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040730","yyyyMMdd"));
//        l_ifoTradedProductUpdqParams.setExerciseStop(BooleanEnum.FALSE);
//        l_ifoTradedProductUpdqParams.setActualDelivaryStop(BooleanEnum.FALSE);
//        l_ifoTradedProductUpdqParams.setActualRecieveStop(BooleanEnum.FALSE);
//        l_ifoTradedProductUpdqParams.setReserveStop(BooleanEnum.FALSE);
//        l_ifoTradedProductUpdqParams.setIndicationPrice(30.8D);
//        l_ifoTradedProductUpdqParams.setLastLiquidationPrice(0D);
//        l_ifoTradedProductUpdqParams.setTargetSpotPrice(1212D);
//        l_ifoTradedProductUpdqParams.setLiquidationPrice(8.13D);
//        l_ifoTradedProductUpdqParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//        l_ifoTradedProductUpdqParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
//        return l_ifoTradedProductUpdqParams;
//    }  
}
@
