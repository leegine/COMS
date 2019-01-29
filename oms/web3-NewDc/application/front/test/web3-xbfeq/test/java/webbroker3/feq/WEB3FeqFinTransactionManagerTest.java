head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqFinTransactionManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式トランザクションマネージャ(WEB3FeqFinTransactionManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/20 何文敏(中訊) 仕様変更 モデルNo.365
*/
package webbroker3.feq;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式トランザクションマネージャ) <BR>
 * 外国株式トランザクションマネージャ<BR>
 * <BR>
 * @@ author 何文敏 <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqFinTransactionManagerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqFinTransactionManagerTest.class);

    WEB3FeqFinTransactionManager l_manager = new WEB3FeqFinTransactionManager();
 
    public WEB3FeqFinTransactionManagerTest(String arg0)
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

    public void testGetFxDate_case0001()
    {
        final String STR_METHOD_NAME = "testGetFxDate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
            TestDBUtility.insertWithDel(l_feqOrderUnitRow);

            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = new FeqFinTransactionParams();
            l_feqFinTransactionParams.setFinTransactionId(2L);
            l_feqFinTransactionParams.setAccountId(1L);
            l_feqFinTransactionParams.setOrderUnitId(l_feqOrderUnitRow.getOrderUnitId());
            l_feqFinTransactionParams.setOrderId(l_feqOrderUnitRow.getOrderId());
            l_feqFinTransactionParams.setSubAccountId(1L);
            l_feqFinTransactionParams.setProductId(1L);
            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
            l_feqFinTransactionParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_feqFinTransactionParams.setBizDate("20070506");
            l_feqFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070506", "yyyyMMdd"));
            l_feqFinTransactionParams.setCurrencyCode("12");
            l_feqFinTransactionParams.setNetAmount(2D);
            l_feqFinTransactionParams.setNetAmountFc(1D);
            l_feqFinTransactionParams.setFxRate(0.5D);
            l_feqFinTransactionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqFinTransactionParams.setQuantity(1D);
            l_feqFinTransactionParams.setCommissionFee(0D);
            l_feqFinTransactionParams.setCommissionFeeTax(0D);
            l_feqFinTransactionParams.setCommissionFeeTaxFc(0D);
            l_feqFinTransactionParams.setCommissionFeeFc(0D);
            l_feqFinTransactionParams.setBalanceAmountFc(0D);
            l_feqFinTransactionParams.setForeignCommissionFee(0D);
            l_feqFinTransactionParams.setForeignTax(0D);
            l_feqFinTransactionParams.setForeignFeeExt1(0D);
            l_feqFinTransactionParams.setRegNo("2");
            l_feqFinTransactionParams.setForeignFeeExt2(0D);
            l_feqFinTransactionParams.setCapitalGain(0D);
            l_feqFinTransactionParams.setCapitalGainFc(0D);
            l_feqFinTransactionParams.setCapitalGainTax(0D);
            l_feqFinTransactionParams.setCapitalGainTaxFc(0D);
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            l_feqFinTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            l_feqFinTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);

            FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitRow);
            
            Double l_dblFxRate = l_manager.getFxRate(l_feqOrderUnit);
            double l_fxRate = l_dblFxRate.doubleValue();
            assertEquals(l_fxRate, 0.5D, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFxDate_case0002()
    {
        final String STR_METHOD_NAME = "testGetFxDate_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_manager.getFxRate(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFxDate_case0003()
    {
        final String STR_METHOD_NAME = "testGetFxDate_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
            TestDBUtility.insertWithDel(l_feqOrderUnitRow);

            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = new FeqFinTransactionParams();
            l_feqFinTransactionParams.setFinTransactionId(2L);
            l_feqFinTransactionParams.setAccountId(1L);
            l_feqFinTransactionParams.setOrderUnitId(l_feqOrderUnitRow.getOrderUnitId() + 1L);
            l_feqFinTransactionParams.setOrderId(l_feqOrderUnitRow.getOrderId() + 1L);
            l_feqFinTransactionParams.setSubAccountId(1L);
            l_feqFinTransactionParams.setProductId(1L);
            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
            l_feqFinTransactionParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_feqFinTransactionParams.setBizDate("20070506");
            l_feqFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070506", "yyyyMMdd"));
            l_feqFinTransactionParams.setCurrencyCode("12");
            l_feqFinTransactionParams.setNetAmount(2D);
            l_feqFinTransactionParams.setNetAmountFc(1D);
            l_feqFinTransactionParams.setFxRate(0.5D);
            l_feqFinTransactionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqFinTransactionParams.setQuantity(1D);
            l_feqFinTransactionParams.setCommissionFee(0D);
            l_feqFinTransactionParams.setCommissionFeeTax(0D);
            l_feqFinTransactionParams.setCommissionFeeTaxFc(0D);
            l_feqFinTransactionParams.setCommissionFeeFc(0D);
            l_feqFinTransactionParams.setBalanceAmountFc(0D);
            l_feqFinTransactionParams.setForeignCommissionFee(0D);
            l_feqFinTransactionParams.setForeignTax(0D);
            l_feqFinTransactionParams.setForeignFeeExt1(0D);
            l_feqFinTransactionParams.setRegNo("2");
            l_feqFinTransactionParams.setForeignFeeExt2(0D);
            l_feqFinTransactionParams.setCapitalGain(0D);
            l_feqFinTransactionParams.setCapitalGainFc(0D);
            l_feqFinTransactionParams.setCapitalGainTax(0D);
            l_feqFinTransactionParams.setCapitalGainTaxFc(0D);
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            l_feqFinTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            l_feqFinTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);

            FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitRow);
            
            l_manager.getFxRate(l_feqOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
