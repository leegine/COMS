head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteMonitorDBManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価監視DB管理サービス実装(WEB3QuoteMonitorDBManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/01/28 許　@　@競(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MarketImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductPK;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3QuoteMonitorDBManager {
    
    /**
     * l_instance
     */
    private static WEB3QuoteMonitorDBManager l_instance =
        new WEB3QuoteMonitorDBManager();

    /**
     * getInstance
     *
     * @@return WEB3QuoteMonitorDBManager
     */
    public static WEB3QuoteMonitorDBManager getInstance()
    {
        return l_instance;
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteMonitorDBManager.class);
    
    /**
     * デバック出力フラグ
     */
    private static final boolean DBG = log.ison();
    
    /**
     * 時価監視銘柄一覧取得
     *
     * @@throws Exception
     * @@return List
     */
    public List getQuoteMonitorProducts() throws Exception
    {
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            QuoteMonitorProductParams.TYPE);

        return l_lisSearchResult;
    }
    
    /**
     * 株式取引銘柄一覧取得
     *
     * @@param l_marketCode  Long
     * @@param l_productCode Long
     * @@throws Exception
     * @@return List
     */
    public List getEqtypeTradedProducts(String l_strMarketCode, String l_strProductCode) 
        throws Exception
    {
        String l_strWhere = 
            " institution_code in (select institution_code from market where market_code = ?)"
            + " and product_id in (select product_id from eqtype_product where institution_code in (select institution_code from market where market_code = ?) and product_code = ?) "
            + " and market_id in (select market_id from market where market_code = ?)"
            + " and valid_until_biz_date= ? and list_flag= ?"
            + " and (buy_cash_stop= ? or sell_cash_stop= ?)";

        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.
            getTradingSystem().getSystemTimestamp(), "yyyyMMdd");

        Object[] l_bindVars = new Object[8];
        l_bindVars[0] = l_strMarketCode;
        l_bindVars[1] = l_strMarketCode;
        l_bindVars[2] = l_strProductCode;
        l_bindVars[3] = l_strMarketCode;
        l_bindVars[4] = l_strBaseDate;
        l_bindVars[5] = new Long(BooleanEnum.TRUE.intValue());
        l_bindVars[6] = WEB3TradeStopDef.ACTIVE;
        l_bindVars[7] = WEB3TradeStopDef.ACTIVE;

        if (DBG)
        {
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            log.debug("BindVars[3]=" + l_bindVars[3]);
            log.debug("BindVars[4]=" + l_bindVars[4]);
            log.debug("BindVars[5]=" + l_bindVars[5]);
            log.debug("BindVars[6]=" + l_bindVars[6]);
            log.debug("BindVars[7]=" + l_bindVars[7]);
        }

        String l_strOrderBy = " product_id,market_id ";

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            EqtypeTradedProductParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars,
            new RowType[]{EqtypeProductParams.TYPE});
        
        //　@該当データなしの場合、
        if (l_lisSearchResult == null || l_lisSearchResult.size() == 0)
        {
            String l_tmp = "株式取引銘柄テーブルに該当するデータがありません。市場コード："+l_strMarketCode+",銘柄コード："+l_strProductCode;
            log.warn(l_tmp);
            return null;
        }

        return l_lisSearchResult;

    }
    
    /**
     * 取引銘柄を取得
     *
     * @@param  l_key Object
     * @@param  l_tradedProductRow TradedProductRow
     * @@throws Exception
     * @@return TradedProduct
     */
    public TradedProduct getEqTypeTradedProduct(EqtypeTradedProductRow l_tradedProductRow) throws Exception
    {
        long l_productId = l_tradedProductRow.getProductId();
        
        ProductRow l_productRow = 
            (ProductRow)getProduct(l_productId);
        EqtypeProductRow l_eqtypeProductRow = 
            (EqtypeProductRow)getEqtypeProduct(l_productId);
        EqTypeProduct l_eqTypeProduct = new WEB3EqTypeProductFakeImpl(l_productRow, l_eqtypeProductRow);
        
        Market l_market = new MarketImpl(l_tradedProductRow.getMarketId());
        TradedProduct l_tradedProduct = new WEB3EqTypeTradedProductFakeImpl(l_eqTypeProduct, l_market);
        
        return(l_tradedProduct);
    }
    
    /**
     * 直近限月取得
     *
     * @@param  l_productCode String
     * @@throws Exception
     * @@return List
     */
    public List getIfoLatestDeliveryMonth(String l_productCode) throws Exception
    {
        List l_latestIfoDeliveryMonthList = new ArrayList();
        
        String l_strWhere = 
            " product_id in " +
            "( select product_id from ifo_traded_product " +
            " where valid_for_biz_date = ?" +
            " and start_trading_date <= ? and last_trading_date >= ? )" +
            " and underlying_product_code = ?" +
            " and future_option_div = ?" +
            " and month_of_delivery <> ? ";

        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.
                getTradingSystem().getSystemTimestamp(), "yyyyMMdd");
        
        Object[] l_bindVars = new Object[6];
        l_bindVars[0] = l_strBaseDate;
        l_bindVars[1] = l_strBaseDate;
        l_bindVars[2] = l_strBaseDate;
        l_bindVars[3] = l_productCode;
        l_bindVars[4] = WEB3FuturesOptionDivDef.FUTURES;
        l_bindVars[5] = WEB3QuoteConstants.EXCEPT_MONTH_OF_DELIVERY;

        if (DBG)
        {
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            log.debug("BindVars[3]=" + l_bindVars[3]);
            log.debug("BindVars[4]=" + l_bindVars[4]);
            log.debug("BindVars[5]=" + l_bindVars[5]);
        }

        String l_strOrderBy = " underlying_product_code,derivative_type,month_of_delivery ";

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_result = l_queryProcessor.doFindAllQuery(
            IfoProductParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        if (l_result.size() == 0)
        {
            return null;
        }

        IfoProductRow l_row = (IfoProductRow)l_result.get(0);
        String l_underlyingProductCode = l_row.getUnderlyingProductCode();
        String l_futureOptionDiv = l_row.getFutureOptionDiv();
        l_latestIfoDeliveryMonthList.add(l_row);
            
        for (int i = 1; i < l_result.size(); i++)
        {
            l_row = (IfoProductRow)l_result.get(i);
            
            if (!l_underlyingProductCode.equals(l_row.getUnderlyingProductCode()) ||
                !l_futureOptionDiv.equals(l_row.getFutureOptionDiv()))
            {
                l_latestIfoDeliveryMonthList.add(l_row);
                l_underlyingProductCode = l_row.getUnderlyingProductCode();
                l_futureOptionDiv = l_row.getFutureOptionDiv();
            }
        }
        
        return l_latestIfoDeliveryMonthList;
    }
    
    /**
     * 先物OP取引銘柄一覧取得
     *
     * @@param l_row  IfoProductRow
     * @@throws Exception
     * @@return List
     */
    public IfoTradedProductRow[] getIfoTradedProducts(IfoProductRow l_row) 
        throws Exception
    {
        String l_productCode = l_row.getUnderlyingProductCode();
        String l_futureOptionDiv = l_row.getFutureOptionDiv();
        String l_deliveryMonth = l_row.getMonthOfDelivery();
        
        String l_strWhere = 
            " valid_for_biz_date = ?"
            + " and institution_code in (select institution_code from market)"
            + " and market_id in (select market_id from market)"
            + " and product_id in (select product_id from ifo_product where institution_code in (select institution_code from market) and underlying_product_code = ? ";
        
        l_strWhere = l_strWhere
            + " and derivative_type = ? and month_of_delivery = ?)"
            + " and list_flag= ?"
            + " and trade_stop_flag =?";

        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.
            getTradingSystem().getSystemTimestamp(), "yyyyMMdd");

        Object[] l_bindVars = new Object[6];
        l_bindVars[0] = l_strBaseDate;
        l_bindVars[1] = l_productCode;
        l_bindVars[2] = IfoDerivativeTypeEnum.FUTURES;
        l_bindVars[3] = l_deliveryMonth;
        l_bindVars[4] = new Long(BooleanEnum.TRUE.intValue());
        l_bindVars[5] = new Long(BooleanEnum.FALSE.intValue());

        if (DBG)
        {
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            log.debug("BindVars[3]=" + l_bindVars[3]);
            log.debug("BindVars[4]=" + l_bindVars[4]);
            log.debug("BindVars[5]=" + l_bindVars[5]);
        }

        String l_strOrderBy = " product_id,market_id ";

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            IfoTradedProductParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars,
            new RowType[]{IfoProductParams.TYPE});
        
        //　@該当データなしの場合、
        if (l_lisSearchResult == null || l_lisSearchResult.size() == 0)
        {
            String l_tmp = "先物OP取引銘柄テーブルに該当するデータがありません。銘柄コード："+l_productCode+",先物OP商品："+l_futureOptionDiv+",限月："+l_deliveryMonth;
            log.warn(l_tmp);
            return null;
        }
        
        //重複チェック
        Map l_ifoTradedProductRowMap = new HashMap();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            IfoTradedProductRow l_ifoTradedProductRow = (IfoTradedProductRow)l_lisSearchResult.get(i);
            
            IfoProductRow l_ifoProductRow = (IfoProductRow)getIfoProduct(l_ifoTradedProductRow.getProductId());
            
            String l_key = toMarketId(l_ifoTradedProductRow.getMarketId());
            l_key = l_key 
                + l_ifoProductRow.getUnderlyingProductCode()
                + l_ifoProductRow.getMonthOfDelivery();
            if(WEB3FuturesOptionDivDef.OPTION.equals(l_ifoProductRow.getFutureOptionDiv()))
            {
                l_key = l_key 
                    + l_ifoProductRow.getDerivativeType().intValue()
                    + l_ifoProductRow.getStrikePrice();
            }
            l_ifoTradedProductRowMap.put(l_key, l_ifoTradedProductRow);
        }

        IfoTradedProductRow[] ifoTradedProductRowList = 
            new IfoTradedProductRow[l_ifoTradedProductRowMap.entrySet().size()];
        ifoTradedProductRowList = 
            (IfoTradedProductRow[]) l_ifoTradedProductRowMap.values().toArray(ifoTradedProductRowList);
        return ifoTradedProductRowList;

    }
    
    /**
     * 取引銘柄を取得
     *
     * @@param  l_key Object
     * @@param  l_tradedProductRow TradedProductRow
     * @@throws Exception
     * @@return TradedProduct
     */
    public TradedProduct getIfoTradedProduct(IfoTradedProductRow l_tradedProductRow) throws Exception
    {
        long l_productId = l_tradedProductRow.getProductId();
        
        ProductRow l_productRow = 
            (ProductRow)getProduct(l_productId);
        IfoProductRow l_ifoProductRow = 
            (IfoProductRow)getIfoProduct(l_productId);
        IfoProduct l_ifoProduct = new WEB3IfoProductFakeImpl(l_productRow, l_ifoProductRow);
        
        Market l_market = new MarketImpl(l_tradedProductRow.getMarketId());
        TradedProduct l_tradedProduct = new WEB3IfoTradedProductFakeImpl(l_ifoProduct, l_market);
        
        return(l_tradedProduct);
    }
    
    /**
     * 株式銘柄Row取得
     *
     * @@param l_productId  long
     * @@throws Exception
     * @@return Row
     */
    private Row getEqtypeProduct(long l_productId) 
        throws Exception
    {
        EqtypeProductPK l_pk = new EqtypeProductPK(l_productId);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        return l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
    }
    
    /**
     * 先物OP銘柄Row取得
     *
     * @@param l_productId  long
     * @@throws Exception
     * @@return Row
     */
    private Row getIfoProduct(long l_productId) 
        throws Exception
    {
        IfoProductPK l_pk = new IfoProductPK(l_productId);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        return l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
    }
    
    /**
     * 銘柄Row取得
     *
     * @@param l_productId  long
     * @@throws Exception
     * @@return Row
     */
    private Row getProduct(long l_productId) 
        throws Exception
    {
        ProductPK l_pk = new ProductPK(l_productId);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

        return l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
    }
    
    private String toMarketId(long l_marketId)
    {
        String l_strMarketId = "" + l_marketId;
        return l_strMarketId.substring(WEB3QuoteConstants.INSTITUTION_CODE_SIZE);
    }

}
@
