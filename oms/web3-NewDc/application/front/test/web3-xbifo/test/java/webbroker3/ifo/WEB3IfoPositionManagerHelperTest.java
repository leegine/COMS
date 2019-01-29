head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoPositionManagerHelperTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3IfoPositionManagerHelperTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/15 肖志偉 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.WEB3IfoPositionManagerHelper.WEB3IfoPersistentDataManager;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * XXXXXXクラス//TODO
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoPositionManagerHelperTest extends TestBaseForMock
{

    public WEB3IfoPositionManagerHelperTest(String arg0)
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

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderManagerImplTest.class);
    /*
     * Test method for 'webbroker3.ifo.WEB3IfoPositionManagerHelper.updateContractOpenFromMarketOrderedTrans(IfoContractParams, IfoFinTransactionParams)'
     */
//    public void testUpdateContractOpenFromMarketOrderedTransIfoContractParamsIfoFinTransactionParams()
//    {
//        WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
//        Date l_dat = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
//        Date l_dat1 = WEB3DateUtility.getDate("20070102", "yyyyMMdd");
//        Date l_dat2 = WEB3DateUtility.getDate("20070103", "yyyyMMdd");
//        
//        try 
//        {
//            TestDBUtility.deleteAll(IfoContractRow.TYPE);
//            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
//            IfoContractParams l_ifoContractParams = new IfoContractParams();
//            
//            IfoFinTransactionParams l_ifoFinTransactionParams = new  IfoFinTransactionParams();
//            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.OTHER);
//            l_ifoFinTransactionParams.setFinTransactionTimestamp(l_dat);
//            l_ifoFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
//            l_ifoFinTransactionParams.setNetAmount(9);
//            l_ifoFinTransactionParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoFinTransactionParams.setQuantity(21);
//            l_ifoFinTransactionParams.setCreatedTimestamp(l_dat);
//            l_ifoFinTransactionParams.setLastUpdatedTimestamp(l_dat1);
//            l_ifoFinTransactionParams.setOrderExecutionId(123);
//            l_ifoFinTransactionParams.setSubAccountId(2);
//            l_ifoFinTransactionParams.setAccountId(1);
//            l_ifoFinTransactionParams.setMarketId(3);
//            l_ifoFinTransactionParams.setProductId(4);
//            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.DEBIT);
//            l_ifoFinTransactionParams.setPrice(1);
//            l_ifoFinTransactionParams.setOrderUnitId(3333);
//            //l_ifoFinTransactionParams.setExecTimestamp(l_dat);
//            //l_ifoFinTransactionParams.setCloseDate(l_dat);
//            l_ifoFinTransactionParams.setDeliveryDate(l_dat2);
//            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
//            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(3333);
//            l_ifoOrderUnitParams.setSessionType("1");
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            
//            l_helper.updateContractOpenFromMarketOrderedTrans(l_ifoContractParams, l_ifoFinTransactionParams);
//            
//            assertEquals("1", l_ifoContractParams.getSessionType());
//            assertEquals(l_dat2, l_ifoContractParams.getDeliveryDate());
//            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
//            TestDBUtility.deleteAll(IfoContractRow.TYPE);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//    }

    /*
     * Test method for 'webbroker3.ifo.WEB3IfoPositionManagerHelper.getContract(IfoFinTransactionParams)'
     */
    public void testGetContractIfoFinTransactionParams()
    {
        WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
        
        Date l_dat = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
        Date l_dat1 = WEB3DateUtility.getDate("20070102", "yyyyMMdd");
        
        
        
        try 
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSubAccountId(2);
            l_ifoContractParams.setAccountId(1);
            l_ifoContractParams.setMarketId(3);
            l_ifoContractParams.setProductId(4);
            l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
            l_ifoContractParams.setContractPrice(1);
            l_ifoContractParams.setOpenDate(l_dat);
            l_ifoContractParams.setCloseDate(l_dat);
            l_ifoContractParams.setDeliveryDate(l_dat1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            IfoFinTransactionParams l_ifoFinTransactionParams = new  IfoFinTransactionParams();
            l_ifoFinTransactionParams.setFinTransactionCateg(new FinTransactionCateg(1, "Categ"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(l_dat);
            l_ifoFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
            l_ifoFinTransactionParams.setNetAmount(9);
            l_ifoFinTransactionParams.setProductType(ProductTypeEnum.IFO);
            l_ifoFinTransactionParams.setQuantity(21);
            l_ifoFinTransactionParams.setCreatedTimestamp(l_dat);
            l_ifoFinTransactionParams.setLastUpdatedTimestamp(l_dat1);
            l_ifoFinTransactionParams.setOrderExecutionId(123);
            l_ifoFinTransactionParams.setSubAccountId(2);
            l_ifoFinTransactionParams.setAccountId(1);
            l_ifoFinTransactionParams.setMarketId(3);
            l_ifoFinTransactionParams.setProductId(4);
            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.ASSET_IN);
            l_ifoFinTransactionParams.setPrice(1);
            //l_ifoFinTransactionParams.setExecTimestamp(l_dat);
            //l_ifoFinTransactionParams.setCloseDate(l_dat);
            l_ifoFinTransactionParams.setDeliveryDate(l_dat1);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            IfoOrderExecutionParams l_ifoOrderExecutionParams = new IfoOrderExecutionParams();
            l_ifoOrderExecutionParams.setAccountId(1);
            l_ifoOrderExecutionParams.setSubAccountId(2);
            l_ifoOrderExecutionParams.setBranchId(12);
            l_ifoOrderExecutionParams.setOrderId(12);
            l_ifoOrderExecutionParams.setOrderUnitId(12);
            l_ifoOrderExecutionParams.setOrderType(OrderTypeEnum.ASSET_IN);
            l_ifoOrderExecutionParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderExecutionParams.setDeliveryDate(l_dat1);
            l_ifoOrderExecutionParams.setExecSerialNo(1);
            l_ifoOrderExecutionParams.setExecQuantity(12);
            l_ifoOrderExecutionParams.setExecTimestamp(l_dat);
            l_ifoOrderExecutionParams.setBizDate("20070203");
            l_ifoOrderExecutionParams.setProductId(3);
            l_ifoOrderExecutionParams.setCreatedTimestamp(l_dat);
            l_ifoOrderExecutionParams.setLastUpdatedTimestamp(l_dat1);
            l_ifoOrderExecutionParams.setOrderExecutionId(123);
            l_ifoOrderExecutionParams.setExecTimestamp(l_dat);
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(4);
            l_ifoTradedProductParams.setMarketId(3);
            l_ifoTradedProductParams.setLastTradingDate(l_dat);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            IfoContractParams l_retIfoContractParams = 
                l_helper.getContract(l_ifoFinTransactionParams);
            
            assertEquals(1, l_retIfoContractParams.getAccountId());
            assertEquals(2, l_retIfoContractParams.getSubAccountId());
            assertEquals(ContractTypeEnum.UNDEFINED, l_retIfoContractParams.getContractType());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /*
     * Test method for 'com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoPositionManagerHelper.getContract(IfoFinTransactionParams)'
     */
    public void testGetContractIfoFinTransactionParams1_case1()
    {
        WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
        WEB3IfoPersistentDataManager l_ifoPersistentDatamanager = l_helper.new WEB3IfoPersistentDataManager();

        Date l_dat = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ret = l_ifoPersistentDatamanager.getContract(
                1, 1, 1, 1, ContractTypeEnum.UNDEFINED, 1, l_dat, l_dat, l_dat);
            
            assertNull(l_ret);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetContractIfoFinTransactionParams1_case2()
    {
        WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
        WEB3IfoPersistentDataManager l_ifoPersistentDatamanager = l_helper.new WEB3IfoPersistentDataManager();

        Date l_dat = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
        Date l_dat1 = WEB3DateUtility.getDate("20070102", "yyyyMMdd");
        
       
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSubAccountId(2);
            l_ifoContractParams.setAccountId(1);
            l_ifoContractParams.setMarketId(3);
            l_ifoContractParams.setProductId(4);
            l_ifoContractParams.setContractType(new ContractTypeEnum(1, "abc"));
            l_ifoContractParams.setContractPrice(1);
            l_ifoContractParams.setOpenDate(l_dat);
            l_ifoContractParams.setCloseDate(l_dat);
            l_ifoContractParams.setDeliveryDate(l_dat1);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            IfoContractParams l_ret = l_ifoPersistentDatamanager.getContract(
                1, 2, 3, 4, new ContractTypeEnum(1, "abc"), 1, l_dat, l_dat, l_dat1);
            
            assertEquals(1, l_ret.getAccountId());
            assertEquals(2, l_ret.getSubAccountId());
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    //連續注文對應
    //get建玉ListBy注文単位
    public void testGetContractListByOrderUnit()
    {
        final String STR_METHOD_NAME = "testGetContractListByOrderUnit()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
        WEB3IfoPersistentDataManager l_ifoPersistentDatamanager = l_helper.new WEB3IfoPersistentDataManager();

        Date l_dat = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
        Date l_dat1 = WEB3DateUtility.getDate("20070102", "yyyyMMdd");
        
       
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = new  IfoFinTransactionParams();
            l_ifoFinTransactionParams.setFinTransactionCateg(new FinTransactionCateg(0, "Categ"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(l_dat);
            l_ifoFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
            l_ifoFinTransactionParams.setNetAmount(9);
            l_ifoFinTransactionParams.setProductType(ProductTypeEnum.IFO);
            l_ifoFinTransactionParams.setQuantity(21);
            l_ifoFinTransactionParams.setCreatedTimestamp(l_dat);
            l_ifoFinTransactionParams.setLastUpdatedTimestamp(l_dat1);
            l_ifoFinTransactionParams.setOrderExecutionId(123);
            l_ifoFinTransactionParams.setSubAccountId(2);
            l_ifoFinTransactionParams.setAccountId(1);
            l_ifoFinTransactionParams.setMarketId(3);
            l_ifoFinTransactionParams.setProductId(4);
            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.ASSET_IN);
            l_ifoFinTransactionParams.setPrice(1.0);
            //l_ifoFinTransactionParams.setExecTimestamp(l_dat);
            //l_ifoFinTransactionParams.setCloseDate(l_dat);
            l_ifoFinTransactionParams.setDeliveryDate(l_dat1);
            l_ifoFinTransactionParams.setContractId(123456L);
            l_ifoFinTransactionParams.setOrderId(123L);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSubAccountId(2);
            l_ifoContractParams.setAccountId(1);
            l_ifoContractParams.setMarketId(3);
            l_ifoContractParams.setProductId(4);
            l_ifoContractParams.setContractType(new ContractTypeEnum(1, "abc"));
            l_ifoContractParams.setContractPrice(1);
            l_ifoContractParams.setOpenDate(l_dat);
            l_ifoContractParams.setCloseDate(l_dat);
            l_ifoContractParams.setDeliveryDate(l_dat1);
            l_ifoContractParams.setContractId(123456);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            List l_lisResults = l_ifoPersistentDatamanager.getContractListByOrderUnit(123L);
            IfoContractParams l_ifoContractParams1 = (IfoContractParams)l_lisResults.get(0);
            assertEquals(l_ifoContractParams1.getContractId(), 123456);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get取引勘定明細ListBy注文単位Plus建玉
    public void testGetTransactionsListByOrderUnitPlusContract()
    {
        final String STR_METHOD_NAME = "testGetTransactionsListByOrderUnitPlusContract()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
        WEB3IfoPersistentDataManager l_ifoPersistentDatamanager = l_helper.new WEB3IfoPersistentDataManager();

        Date l_dat = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
        Date l_dat1 = WEB3DateUtility.getDate("20070102", "yyyyMMdd");
        
       
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = new  IfoFinTransactionParams();
            l_ifoFinTransactionParams.setFinTransactionCateg(new FinTransactionCateg(0, "Categ"));
            l_ifoFinTransactionParams.setFinTransactionTimestamp(l_dat);
            l_ifoFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
            l_ifoFinTransactionParams.setNetAmount(9);
            l_ifoFinTransactionParams.setProductType(ProductTypeEnum.IFO);
            l_ifoFinTransactionParams.setQuantity(21);
            l_ifoFinTransactionParams.setCreatedTimestamp(l_dat);
            l_ifoFinTransactionParams.setLastUpdatedTimestamp(l_dat1);
            l_ifoFinTransactionParams.setOrderExecutionId(123);
            l_ifoFinTransactionParams.setSubAccountId(2);
            l_ifoFinTransactionParams.setAccountId(1);
            l_ifoFinTransactionParams.setMarketId(3);
            l_ifoFinTransactionParams.setProductId(4);
            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.ASSET_IN);
            l_ifoFinTransactionParams.setPrice(1.0);
            //l_ifoFinTransactionParams.setExecTimestamp(l_dat);
            //l_ifoFinTransactionParams.setCloseDate(l_dat);
            l_ifoFinTransactionParams.setDeliveryDate(l_dat1);
            l_ifoFinTransactionParams.setContractId(123456L);
            l_ifoFinTransactionParams.setOrderUnitId(123L);
            l_ifoFinTransactionParams.setOrderId(123L);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setSubAccountId(2);
            l_ifoContractParams.setAccountId(1);
            l_ifoContractParams.setMarketId(3);
            l_ifoContractParams.setProductId(4);
            l_ifoContractParams.setContractType(new ContractTypeEnum(1, "abc"));
            l_ifoContractParams.setContractPrice(1);
            l_ifoContractParams.setOpenDate(l_dat);
            l_ifoContractParams.setCloseDate(l_dat);
            l_ifoContractParams.setDeliveryDate(l_dat1);
            l_ifoContractParams.setContractId(123456);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            List l_lisResults = l_ifoPersistentDatamanager.getTransactionsListByOrderUnitPlusContract(
                    123L, 123456L);
            IfoFinTransactionParams l_ifoFinTransactionParams1 = (IfoFinTransactionParams)l_lisResults.get(0);
            assertEquals(l_ifoFinTransactionParams1.getOrderId(), 123);
            assertEquals(l_ifoFinTransactionParams1.getContractId(), 123456);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //受渡代金、評価損益（決済損益）の再計算を行う失敗。
    public void testSetContractAmount_C0001()
    {
        try 
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setCommissionFee(10);
            l_ifoFinTransactionParams.setCommissionFeeTax(20);
            
            IfoFinTransactionParams l_ifoFinTransactionParams1 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams1.setFinTransactionId(1L);
            l_ifoFinTransactionParams1.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
            l_ifoFinTransactionParams1.setContractId(1001);
            l_ifoFinTransactionParams1.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams1.setCommissionFee(10);
            l_ifoFinTransactionParams1.setCommissionFeeTax(20);
            l_ifoFinTransactionParams1.setAccountId(333812512203L);
            l_ifoFinTransactionParams1.setOrderId(2001);
            l_ifoFinTransactionParams1.setOrderUnitId(3001);
            
            IfoFinTransactionParams l_ifoFinTransactionParams2 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams2.setFinTransactionId(2L);
            l_ifoFinTransactionParams2.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            l_ifoFinTransactionParams2.setContractId(1001);
            l_ifoFinTransactionParams2.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams2.setQuantity(30);
            l_ifoFinTransactionParams2.setAccountId(333812512204L);
            l_ifoFinTransactionParams2.setOrderId(2002);
            l_ifoFinTransactionParams2.setOrderUnitId(3001);

            TestDBUtility.insertWithDel(l_ifoFinTransactionParams1);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams2);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            
            WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
            l_helper.setContractAmount(l_ifoContractParams, l_ifoFinTransactionParams);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    //トランザクション（取引勘定明細）行を元に、トランザクション（顧客勘定明細）を更新する失敗。
    public void testSetContractAmount_C0002()
    {
        try 
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setCommissionFee(10);
            l_ifoFinTransactionParams.setCommissionFeeTax(20);
            
            IfoFinTransactionParams l_ifoFinTransactionParams1 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams1.setFinTransactionId(1L);
            l_ifoFinTransactionParams1.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
            l_ifoFinTransactionParams1.setContractId(1001);
            l_ifoFinTransactionParams1.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams1.setCommissionFee(10);
            l_ifoFinTransactionParams1.setCommissionFeeTax(20);
            l_ifoFinTransactionParams1.setAccountId(333812512203L);
            l_ifoFinTransactionParams1.setOrderId(2001);
            l_ifoFinTransactionParams1.setOrderUnitId(3001);
            
            IfoFinTransactionParams l_ifoFinTransactionParams2 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams2.setFinTransactionId(2L);
            l_ifoFinTransactionParams2.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            l_ifoFinTransactionParams2.setContractId(1001);
            l_ifoFinTransactionParams2.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams2.setQuantity(30);
            l_ifoFinTransactionParams2.setAccountId(333812512204L);
            l_ifoFinTransactionParams2.setOrderId(2002);
            l_ifoFinTransactionParams2.setOrderUnitId(3001);

            TestDBUtility.insertWithDel(l_ifoFinTransactionParams1);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams2);
            
            WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelperForTestA(ProductTypeEnum.IFO);
            l_helper.setContractAmount(l_ifoContractParams, l_ifoFinTransactionParams);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    //取引勘定明細を更新する驗證
    public void testSetContractAmount_C0003()
    {
        try 
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setCommissionFee(10);
            l_ifoFinTransactionParams.setCommissionFeeTax(20);
            
            IfoFinTransactionParams l_ifoFinTransactionParams1 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams1.setFinTransactionId(1L);
            l_ifoFinTransactionParams1.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
            l_ifoFinTransactionParams1.setContractId(1001);
            l_ifoFinTransactionParams1.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams1.setCommissionFee(10);
            l_ifoFinTransactionParams1.setCommissionFeeTax(20);
            l_ifoFinTransactionParams1.setAccountId(333812512203L);
            l_ifoFinTransactionParams1.setOrderId(2001);
            l_ifoFinTransactionParams1.setOrderUnitId(3001);
            
            IfoFinTransactionParams l_ifoFinTransactionParams2 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams2.setFinTransactionId(2L);
            l_ifoFinTransactionParams2.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            l_ifoFinTransactionParams2.setContractId(1001);
            l_ifoFinTransactionParams2.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams2.setQuantity(30);
            l_ifoFinTransactionParams2.setAccountId(333812512204L);
            l_ifoFinTransactionParams2.setOrderId(2002);
            l_ifoFinTransactionParams2.setOrderUnitId(3001);

            TestDBUtility.insertWithDel(l_ifoFinTransactionParams1);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams2);
            
            WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelperForTestB(ProductTypeEnum.IFO);
            l_helper.setContractAmount(l_ifoContractParams, l_ifoFinTransactionParams);
            
            long l_lngFinTransactionId = 2L;
            IfoFinTransactionRow l_returnRow = IfoFinTransactionDao.findRowByPk(l_lngFinTransactionId);

            assertEquals(new BigDecimal("600.0"), new BigDecimal(l_returnRow.getImportedSetupFee() + ""));
            assertEquals(new BigDecimal("1200.0"), new BigDecimal(l_returnRow.getImportedSetupFeeTax() + ""));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testSetContractAmount_C0004()
    {
        try 
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001);
            l_ifoContractParams.setOriginalQuantity(30);//
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setCommissionFee(100);
            l_ifoFinTransactionParams.setCommissionFeeTax(200);
            
            IfoFinTransactionParams l_ifoFinTransactionParams1 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams1.setFinTransactionId(1L);
            l_ifoFinTransactionParams1.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);
            l_ifoFinTransactionParams1.setContractId(1001);
            l_ifoFinTransactionParams1.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams1.setCommissionFee(10);//
            l_ifoFinTransactionParams1.setCommissionFeeTax(20);//
            l_ifoFinTransactionParams1.setAccountId(333812512203L);
            l_ifoFinTransactionParams1.setOrderId(2001);
            l_ifoFinTransactionParams1.setOrderUnitId(3001);
            
            IfoFinTransactionParams l_ifoFinTransactionParams2 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams2.setFinTransactionId(2L);
            l_ifoFinTransactionParams2.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            l_ifoFinTransactionParams2.setContractId(1001);
            l_ifoFinTransactionParams2.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams2.setQuantity(3);//
            l_ifoFinTransactionParams2.setAccountId(333812512204L);
            l_ifoFinTransactionParams2.setOrderId(2002);
            l_ifoFinTransactionParams2.setOrderUnitId(3001);
            
            IfoFinTransactionParams l_ifoFinTransactionParams3 = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams3.setFinTransactionId(3L);
            l_ifoFinTransactionParams3.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);
            l_ifoFinTransactionParams3.setContractId(1001);
            l_ifoFinTransactionParams3.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams3.setQuantity(5);//
            l_ifoFinTransactionParams3.setAccountId(333812512204L);
            l_ifoFinTransactionParams3.setOrderId(2003);
            l_ifoFinTransactionParams3.setOrderUnitId(3002);

            TestDBUtility.insertWithDel(l_ifoFinTransactionParams1);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams2);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams3);
            
            WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelperForTestB(ProductTypeEnum.IFO);
            l_helper.setContractAmount(l_ifoContractParams, l_ifoFinTransactionParams);
            
            assertEquals(81, l_ifoContractParams.getSetupFee(), 0);
            assertEquals(162, l_ifoContractParams.getSetupFeeTax(), 0);
            
            long l_lngFinTransactionId1 = 2L;
            IfoFinTransactionRow l_returnRow1 = IfoFinTransactionDao.findRowByPk(l_lngFinTransactionId1);
            assertEquals(11, l_returnRow1.getImportedSetupFee(), 0);
            assertEquals(22, l_returnRow1.getImportedSetupFeeTax(), 0);
            
            long l_lngFinTransactionId2 = 3L;
            IfoFinTransactionRow l_returnRow2 = IfoFinTransactionDao.findRowByPk(l_lngFinTransactionId2);
            assertEquals(18, l_returnRow2.getImportedSetupFee(), 0);
            assertEquals(36, l_returnRow2.getImportedSetupFeeTax(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testSetMarginNetAmount_C0001()
    {
        final String STR_METHOD_NAME = "testSetMarginNetAmount_C0001()";
        log.entering(STR_METHOD_NAME);

        try 
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setQuantity(3.0d);
            l_ifoFinTransactionParams.setPrice(1.1);
            l_ifoFinTransactionParams.setProductId(1006160060005L);
            l_ifoFinTransactionParams.setMarketId(1001L);
            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE);//if
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);//if

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setTargetMarketId(1001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setPrimaryMarketId(1001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(1001L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setMarketId(1001L);
            l_IfoTradedProductParams.setProductId(1006160060005L);
            l_IfoTradedProductParams.setUnitSize(2.0);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcDeliveryAmount",
                new Class[]
                {
                    SideEnum.class, 
                    double.class, 
                    double.class, 
                    double.class
                },
                new Double(555.005D));
            
            WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
            l_helper.setMarginNetAmount(l_ifoFinTransactionParams);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcDeliveryAmount",
                new Class[]
                {
                    SideEnum.class, 
                    double.class, 
                    double.class, 
                    double.class
                });
            assertEquals(new Double(6.6D), l_paramsValue.getCalled(0)[1]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testSetMarginNetAmount_C0002()
    {
        final String STR_METHOD_NAME = "testSetMarginNetAmount_C0002()";
        log.entering(STR_METHOD_NAME);

        try 
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setQuantity(3.0d);
            l_ifoFinTransactionParams.setPrice(1.1);
            l_ifoFinTransactionParams.setProductId(1006160060005L);
            l_ifoFinTransactionParams.setMarketId(1001L);
            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE);//if
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);//if
          
            l_ifoFinTransactionParams.setCommissionFee(0.3);
            l_ifoFinTransactionParams.setCommissionFeeTax(0.2);
            
            l_ifoFinTransactionParams.setImportedPaidValue(3.9d);
            l_ifoFinTransactionParams.setImportedSetupFee(0.6d);
            l_ifoFinTransactionParams.setImportedSetupFeeTax(0.3d);
      
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setTargetMarketId(1001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setPrimaryMarketId(1001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(1001L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setMarketId(1001L);
            l_IfoTradedProductParams.setProductId(1006160060005L);
            l_IfoTradedProductParams.setUnitSize(2.0);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
        
            WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
            l_helper.setMarginNetAmount(l_ifoFinTransactionParams);
            
            assertEquals(1.3, l_ifoFinTransactionParams.getCapitalGain(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testSetMarginNetAmount_C0003()
    {
        final String STR_METHOD_NAME = "testSetMarginNetAmount_C0003()";
        log.entering(STR_METHOD_NAME);

        try 
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setQuantity(3.0d);
            l_ifoFinTransactionParams.setPrice(1.1);
            l_ifoFinTransactionParams.setProductId(1006160060005L);
            l_ifoFinTransactionParams.setMarketId(1001L);
            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE);//if
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE);//if
          
            l_ifoFinTransactionParams.setCommissionFee(0.3);
            l_ifoFinTransactionParams.setCommissionFeeTax(0.2);
            
            l_ifoFinTransactionParams.setImportedPaidValue(9.3d);
            l_ifoFinTransactionParams.setImportedSetupFee(0.6d);
            l_ifoFinTransactionParams.setImportedSetupFeeTax(0.3d);
      
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setTargetMarketId(1001L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setPrimaryMarketId(1001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(1001L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setMarketId(1001L);
            l_IfoTradedProductParams.setProductId(1006160060005L);
            l_IfoTradedProductParams.setUnitSize(2.0);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
        
            WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
            l_helper.setMarginNetAmount(l_ifoFinTransactionParams);
            
            assertEquals(1.3, l_ifoFinTransactionParams.getCapitalGain(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testSetMarginNetAmount_C0004()
    {
        final String STR_METHOD_NAME = "testSetMarginNetAmount_C0004()";
        log.entering(STR_METHOD_NAME);

        try 
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcDeliveryAmount",
                new Class[]
                {
                    SideEnum.class, 
                    double.class, 
                    double.class, 
                    double.class
                },
                new Double(13.6D));
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setQuantity(3.0d);
            l_ifoFinTransactionParams.setPrice(1.1);
            l_ifoFinTransactionParams.setProductId(1006160060005L);
            l_ifoFinTransactionParams.setMarketId(1001L);
            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE);//if
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN);//if
          
            l_ifoFinTransactionParams.setCommissionFee(0.3);
            l_ifoFinTransactionParams.setCommissionFeeTax(0.2);
            
            l_ifoFinTransactionParams.setImportedPaidValue(9.3d);
            l_ifoFinTransactionParams.setImportedSetupFee(0.6d);
            l_ifoFinTransactionParams.setImportedSetupFeeTax(0.3d);
      
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setTargetMarketId(1001L);
            l_ifoProductParams.setFutureOptionDiv("2");//2 : オプション
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setPrimaryMarketId(1001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(1001L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setMarketId(1001L);
            l_IfoTradedProductParams.setProductId(1006160060005L);
            l_IfoTradedProductParams.setUnitSize(2.0);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
        
            WEB3IfoPositionManagerHelper l_helper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
            l_helper.setMarginNetAmount(l_ifoFinTransactionParams);
            
            assertEquals(-13.6, l_ifoFinTransactionParams.getNetAmount(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    private class WEB3IfoPositionManagerHelperForTestA extends WEB3IfoPositionManagerHelper
    {

        public WEB3IfoPositionManagerHelperForTestA(ProductTypeEnum l_tradingModuleType)
        {
            super(l_tradingModuleType);
            // TODO Auto-generated constructor stub
        }
        protected void setMarginNetAmount(IfoFinTransactionParams l_ifoFinTransactionParams) throws DataException
        {
            
        }
        protected void notifyUpdateGtl(IfoFinTransactionParams l_ifoFinTransactionParams)
        throws RuntimeSystemException, DataFindException, DataNetworkException, DataQueryException, WEB3BaseException
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "");
        }
    }
    
    private class WEB3IfoPositionManagerHelperForTestB extends WEB3IfoPositionManagerHelper
    {

        public WEB3IfoPositionManagerHelperForTestB(ProductTypeEnum l_tradingModuleType)
        {
            super(l_tradingModuleType);
            // TODO Auto-generated constructor stub
        }
        protected void setMarginNetAmount(IfoFinTransactionParams l_ifoFinTransactionParams) throws DataException
        {
            
        }
        protected void notifyUpdateGtl(IfoFinTransactionParams l_ifoFinTransactionParams)
        throws RuntimeSystemException, DataFindException, DataNetworkException, DataQueryException, WEB3BaseException
        {
            
        }
    }
    
}
@
