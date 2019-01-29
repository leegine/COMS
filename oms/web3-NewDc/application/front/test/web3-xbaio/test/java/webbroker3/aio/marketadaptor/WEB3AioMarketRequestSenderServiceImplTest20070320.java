head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioMarketRequestSenderServiceImplTest20070320.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AioMarketRequestSenderServiceImplTest20070320.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/21 âΩï∂ïq (íÜêu) êVãKçÏê¨
*/
package webbroker3.aio.marketadaptor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostForeignCashTransOrderDao;
import webbroker3.aio.data.HostForeignCashTransOrderParams;
import webbroker3.aio.data.HostForeignCashTransOrderRow;
import webbroker3.aio.data.HostMrgsecTransferParams;
import webbroker3.aio.data.HostMrgsecTransferRow;
import webbroker3.gentrade.data.FinInstitutionParams;
import webbroker3.gentrade.data.FinInstitutionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 */
public class WEB3AioMarketRequestSenderServiceImplTest20070320 extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMarketRequestSenderServiceImplTest20070320.class);
    
    WEB3AioMarketRequestSenderServiceImpl l_mpl =
        new WEB3AioMarketRequestSenderServiceImpl();
    
    public WEB3AioMarketRequestSenderServiceImplTest20070320(String arg0)
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

    public void testCreateSecurityTransferOrderQueueData()
    {
        final String STR_METHOD_NAME = "testCreateSecurityTransferOrderQueueData()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES);
            l_aioOrderUnitParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestDBUtility.deleteAll(HostMrgsecTransferRow.TYPE);
            HostMrgsecTransferParams l_hostMrgsecTransferParams = this.getHostMrgsecTransferRows();
            l_hostMrgsecTransferParams.setTransferFlag("1");
            TestDBUtility.insertWithDel(l_hostMrgsecTransferParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(2605000000001L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundproductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundproductParams.setProductId(2605000000001L);
            l_mutualFundproductParams.setInputUnit("1");
            l_mutualFundproductParams.setFundType(MutualFundTypeEnum.FOREIGN);
            TestDBUtility.insertWithDel(l_mutualFundproductParams);
            
            long l_orderUnitId = 2000011L;
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_orderUnitId);
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnit;
            l_mpl.createSecurityTransferOrderQueueData(l_aioOrderUnit);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSecurityTransferOrderQueueData1()
    {
        final String STR_METHOD_NAME = "testCreateSecurityTransferOrderQueueData1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES);
            l_aioOrderUnitParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestDBUtility.deleteAll(HostMrgsecTransferRow.TYPE);
            HostMrgsecTransferParams l_hostMrgsecTransferParams = this.getHostMrgsecTransferRows();
            l_hostMrgsecTransferParams.setTransferFlag("1");
            TestDBUtility.insertWithDel(l_hostMrgsecTransferParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(2605000000001L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundproductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundproductParams.setProductId(2605000000001L);
            l_mutualFundproductParams.setInputUnit("2");
            l_mutualFundproductParams.setFundType(MutualFundTypeEnum.OTHER);
            TestDBUtility.insertWithDel(l_mutualFundproductParams);
            
            long l_orderUnitId = 2000011L;
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_orderUnitId);
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnit;
            l_mpl.createSecurityTransferOrderQueueData(l_aioOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSecurityTransferOrderQueueData2()
    {
        final String STR_METHOD_NAME = "testCreateSecurityTransferOrderQueueData2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES);
            l_aioOrderUnitParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestDBUtility.deleteAll(HostMrgsecTransferRow.TYPE);
            HostMrgsecTransferParams l_hostMrgsecTransferParams = this.getHostMrgsecTransferRows();
            l_hostMrgsecTransferParams.setTransferFlag("1");
            TestDBUtility.insertWithDel(l_hostMrgsecTransferParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(2605000000001L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundproductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundproductParams.setProductId(2605000000001L);
            l_mutualFundproductParams.setInputUnit("1");
            l_mutualFundproductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mutualFundproductParams.setCurrencyCode("f");
            TestDBUtility.insertWithDel(l_mutualFundproductParams);
            
            long l_orderUnitId = 2000011L;
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_orderUnitId);
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnit;
            l_mpl.createSecurityTransferOrderQueueData(l_aioOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSecurityTransferOrderQueueData3()
    {
        final String STR_METHOD_NAME = "testCreateSecurityTransferOrderQueueData3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES);
            l_aioOrderUnitParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestDBUtility.deleteAll(HostMrgsecTransferRow.TYPE);
            HostMrgsecTransferParams l_hostMrgsecTransferParams = this.getHostMrgsecTransferRows();
            l_hostMrgsecTransferParams.setTransferFlag("1");
            TestDBUtility.insertWithDel(l_hostMrgsecTransferParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(2605000000001L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundproductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundproductParams.setProductId(2605000000001L);
            l_mutualFundproductParams.setInputUnit("2");
            l_mutualFundproductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mutualFundproductParams.setCurrencyCode("â~");
            TestDBUtility.insertWithDel(l_mutualFundproductParams);
            
            long l_orderUnitId = 2000011L;
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_orderUnitId);
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnit;
            l_mpl.createSecurityTransferOrderQueueData(l_aioOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSecurityTransferOrderQueueData4()
    {
        final String STR_METHOD_NAME = "testCreateSecurityTransferOrderQueueData4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.BOND);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES);
            l_aioOrderUnitParams.setOrderRequestNumber("11");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestDBUtility.deleteAll(HostMrgsecTransferRow.TYPE);
            HostMrgsecTransferParams l_hostMrgsecTransferParams = this.getHostMrgsecTransferRows();
            l_hostMrgsecTransferParams.setTransferFlag("1");
            TestDBUtility.insertWithDel(l_hostMrgsecTransferParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(2605000000001L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(BondProductRow.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(2605000000001L);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            long l_orderUnitId = 2000011L;
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_orderUnitId);
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnit;
            l_mpl.createSecurityTransferOrderQueueData(l_aioOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateHostForeignCashinOrderData()
    {
        final String STR_METHOD_NAME = "testCreateCashoutOrderQueueData()";
        log.entering(STR_METHOD_NAME);
        
        long l_orderUnitId = 2000011L;
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.BOND);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES);
            l_aioOrderUnitParams.setDescription("07");
            l_aioOrderUnitParams.setOrderRequestNumber("1");
            l_aioOrderUnitParams.setOrderRootDiv("D");
            l_aioOrderUnitParams.setCurrencyCode("1");
            l_aioOrderUnitParams.setQuantity(1L);
            l_aioOrderUnitParams.setConvertAmount(1L);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(FinInstitutionRow.TYPE);
            FinInstitutionParams l_FinInstitutionParams = this.getFinInstitutionRows();
            TestDBUtility.insertWithDel(l_FinInstitutionParams);
            
            TestDBUtility.deleteAll(HostForeignCashTransOrderRow.TYPE);

            
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();
            AioOrderUnit l_orderUnit = (AioOrderUnit)l_orderManager.getOrderUnit(l_orderUnitId); 
            
            l_mpl.createHostForeignCashinOrderData(l_orderUnit);
            HostForeignCashTransOrderRow l_row =
                (HostForeignCashTransOrderRow)HostForeignCashTransOrderDao.findRowByRequestCodeInstitutionCodeBranchCodeAccountCodeOrderRequestNumber(
                        "GI804",
                        "0D",
                        "624",
                        "1",
                        "1");
            HostForeignCashTransOrderParams l_params = new HostForeignCashTransOrderParams(l_row);
            log.debug("***********************l_strCashTransDate = " + l_params.getCashTransDate());
            assertEquals("0716", l_params.getCashTransDate());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public static HostMrgsecTransferParams getHostMrgsecTransferRows()
    {
        HostMrgsecTransferParams l_hostMrgsecTransferParams = new HostMrgsecTransferParams();
        l_hostMrgsecTransferParams.setRequestCode("GI807");
        l_hostMrgsecTransferParams.setInstitutionCode("0D");
        l_hostMrgsecTransferParams.setBranchCode("624");
        l_hostMrgsecTransferParams.setAccountCode("01");
        l_hostMrgsecTransferParams.setTraderCode("1");
        l_hostMrgsecTransferParams.setTransferFlag("01");
        l_hostMrgsecTransferParams.setOriginalTransferDate("0102");
        l_hostMrgsecTransferParams.setCancelFlag("1");
        l_hostMrgsecTransferParams.setCommodity("2");
        l_hostMrgsecTransferParams.setProductCode("1");
        l_hostMrgsecTransferParams.setQuantity(3000);
        l_hostMrgsecTransferParams.setPrice(300);
        l_hostMrgsecTransferParams.setDelivType("3");
        l_hostMrgsecTransferParams.setOrderRequestNumber("12");
        l_hostMrgsecTransferParams.setStatus("0");
        l_hostMrgsecTransferParams.setSpecificAccountDiv("1");
        l_hostMrgsecTransferParams.setTransferFlag("1");
        
        return l_hostMrgsecTransferParams;
    }
    
    public static FinInstitutionParams getFinInstitutionRows()
    {
        FinInstitutionParams l_finInstitutionParams = new FinInstitutionParams();
        l_finInstitutionParams.setInstitutionCode("0D");
        l_finInstitutionParams.setFinInstitutionCode("07");
        l_finInstitutionParams.setCashTransferSonarCode("2");
        l_finInstitutionParams.setCashTransferType("1");
        l_finInstitutionParams.setFinInstitutionNameKana("a");
        l_finInstitutionParams.setFinInstitutionNameKanji("a");

        
        return l_finInstitutionParams;
    }
    
    public static HostForeignCashTransOrderParams getHostForeignCashTransOrderRow()
    {
        HostForeignCashTransOrderParams l_hostForeignCashTransOrderParams = new HostForeignCashTransOrderParams();
        l_hostForeignCashTransOrderParams.setOrderDiv("1");

        return l_hostForeignCashTransOrderParams;
    }
    
}
@
