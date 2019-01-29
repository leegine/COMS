head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoTradedProductImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP取引銘柄クラスForMock(先物OP取引銘柄クラスForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP取引銘柄クラスForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoTradedProductImplForMock extends WEB3IfoTradedProductImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoTradedProductImplForMock.class);
    
    public WEB3IfoTradedProductImplForMock(long l_lngTradedProductID)
        throws DataQueryException, DataNetworkException, DataFindException
    {
        super(l_lngTradedProductID);
        // TODO Auto-generated constructor stub
    }

    public WEB3IfoTradedProductImplForMock(TradedProductRow l_row) 
        throws DataQueryException, DataNetworkException, DataFindException
    {
        super(l_row);      
    }
    
    public Date getLastTradingDate()
    {
        final String STR_METHOD_NAME = "getLastTradingDate()-->ForMock";
        log.entering(STR_METHOD_NAME);

        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoTradedProductImpl", "getLastTradingDate",
        		null, null);

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoTradedProductImpl", "getLastTradingDate",
                null))
        {
            // 3)MockFor --〉 asObject
            log.exiting(STR_METHOD_NAME);
            return (Date) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
            		 "webbroker3.ifo.WEB3IfoTradedProductImpl",
                     "getLastTradingDate",
                     null).asObject();
        }
        log.exiting(STR_METHOD_NAME);
        return super.getLastTradingDate();
    }    
    
    public static void getIfoTradedProduct(long l_lngProductId,long l_lngMarketId,long l_lngTradedProductId)
    {
        final String STR_METHOD_NAME = "getIfoTradedProduct()";
        log.entering(STR_METHOD_NAME);
        try
        {

            clearIfoTraded();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_lngProductId);
            TestDBUtility.insertWithDel(l_productParams);
            
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_lngProductId);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_lngMarketId);
            TestDBUtility.insertWithDel(l_marketParams);
            

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(l_lngTradedProductId);
            l_tradedProductParams.setProductId(l_lngProductId);
            l_tradedProductParams.setMarketId(l_lngMarketId);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            

            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(l_lngProductId);
            l_IfoTradedProductParams.setTradedProductId(l_lngTradedProductId);
            l_IfoTradedProductParams.setMarketId(l_lngMarketId);
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040101","yyyyMMdd")); 
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            

            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(l_lngTradedProductId);
            l_ifoTradedProductUpdqParams.setMarketId(l_lngMarketId);
            
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(l_lngProductId);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public static void clearIfoTraded()
    {
        final String STR_METHOD_NAME = "clearIfoTraded()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
