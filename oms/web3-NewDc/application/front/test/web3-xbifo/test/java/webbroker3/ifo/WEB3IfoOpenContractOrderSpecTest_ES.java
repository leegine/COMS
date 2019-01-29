head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOpenContractOrderSpecTest_ES.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 新規建注文内容
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/11　@孟亜南(中訊)
*/
package webbroker3.ifo;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;

/**
 * 新規建注文内容
 * @@author 孟亜南
 *
 */
public class WEB3IfoOpenContractOrderSpecTest_ES extends TestBaseForMock
{

    public WEB3IfoOpenContractOrderSpecTest_ES(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception 
    {
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setTradingTimeType("26");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("0");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("235959");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        l_tradingTimeParams.setEnableOrder("0");
        l_tradingTimeParams.setBizdateCalcParameter("1");
        l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.insertWithDel(l_tradingTimeParams);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 新規建注文内容
     *
     */
    public void test_createOpenContractOrderSpec_0001()
    {
        String l_strInstitutionCode = "00";
        Trader l_trader = null;
        boolean l_blnIsBuyToOpenOrder = true;
        String l_strMarket = "00";
        
        
        double l_dblQuantityAfterChange = 0D;
        double l_dblLimitPriceAfterChange = 0D;
        
        IfoOrderExecutionConditionType l_changeExecCondType = new IfoOrderExecutionConditionType(1,"");
        
        Date l_datChangeExpirationDate = new Date();
        
        String l_strOrderCond = "00";
        WEB3IfoProductImpl l_product = null;
        double l_dblStopOrderPrice = 0D;
        double l_dblWLimitPrice = 0D;
        
        IfoOrderExecutionConditionType l_wLimitExecCondType = new IfoOrderExecutionConditionType(1,"");
        String l_strWLimitEnableStatusDiv = "00";
        
        boolean l_blnEveningSessionCarryoverFlag = true;
        
        WEB3IfoOpenContractOrderSpec l_spec = null;
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            try
            {
                l_product = new WEB3IfoProductImpl(1006169090018L);
            }
            catch (DataFindException e)
            {
                fail();
            }
            catch (DataQueryException e)
            {
                fail();
            }
            catch (DataNetworkException e)
            {
                fail();
            }
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
        }
        
        try
        {
            l_spec = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_blnIsBuyToOpenOrder,
                    l_strMarket,
                    l_product,
                    l_dblQuantityAfterChange,
                    l_dblLimitPriceAfterChange,
                    l_changeExecCondType,
                    l_datChangeExpirationDate,
                    l_strOrderCond,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strWLimitEnableStatusDiv,
                    new Long(0),
                l_blnEveningSessionCarryoverFlag);
            assertTrue(l_spec.getEveningSessionCarryoverFlag());
            l_spec.setEveningSessionCarryoverFlag(false);
            
            assertFalse(l_spec.getEveningSessionCarryoverFlag());
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    
    /**
     * 銘柄Rowを作成.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(1006169090018L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        l_productParams.setStandardName("シンセンテルス");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_productParams;
    }
    
    /**
     * 先物OP銘柄テーブル (ifo_product)
     */
    public static IfoProductParams getIfoProductRow()
    {
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setInstitutionCode("10");
        l_ifoProductParams.setProductCode("160030005");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(0);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
        l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoProductParams;
    }
}
@
