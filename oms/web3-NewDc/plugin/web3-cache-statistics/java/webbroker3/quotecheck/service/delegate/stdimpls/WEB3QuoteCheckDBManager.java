head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.57.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckDBManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価情報定点チェックDB管理サービス実装(WEB3QuoteCheckDBManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/01/28 許　@　@競(FLJ) 新規作成
 */
package webbroker3.quotecheck.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;

import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductParams;
import webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductRow;
import webbroker3.quotecheck.data.PrimaryMarketCskParams;
import webbroker3.quotecheck.data.PrimaryMarketCskRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3QuoteCheckDBManager {
    
    /**
     * l_instance
     */
    private static WEB3QuoteCheckDBManager l_instance = new WEB3QuoteCheckDBManager();
    
    /**
     * 銘柄ID、市場IDに含まれる会社コードの桁数
     */
    static int INSTITUTION_CODE_SIZE = 2;
    
    /**
     * 銘柄IDに含まれる会社、商品コードの桁数
     */
    static int PRODUCT_HEAD_SIZE = 4;
    
    /**
     * 銘柄コードの桁数
     */
    static int PRODUCT_CODE_LENGTH = 5;
    
    /**
     * getInstance
     *
     * @@return WEB3QuoteCheckDBManager
     */
    public static WEB3QuoteCheckDBManager getInstance()
    {
        return l_instance;
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteCheckDBManager.class);
    
    
    /**
     * デバック出力フラグ
     */
    private static final boolean DBG = log.ison();
    
    /**
     * 時価監視銘柄テーブルに登録した市場一覧取得
     *
     * @@throws Exception
     * @@return Map
     */
    public Map getQuoteMonitorProducts() throws Exception
    {
        Map l_marketMap = new HashMap();
        
        String l_strWhere = " product_type= ?";
        
        Object[] l_bindVars = new Object[1];
        l_bindVars[0] = ProductTypeEnum.EQUITY;

        if(DBG)
        {
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
        }
        
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_quoteMonitorProductResult = l_queryProcessor.doFindAllQuery(
            QuoteMonitorProductParams.TYPE,
            l_strWhere.toString(),
            l_bindVars);
        
        //　@該当データなしの場合、
        if (l_quoteMonitorProductResult == null || l_quoteMonitorProductResult.size() == 0)
        {
            return null;
        }
        
        for (int i = 0; i < l_quoteMonitorProductResult.size(); i++)
        {
            QuoteMonitorProductRow l_row = 
                (QuoteMonitorProductRow)l_quoteMonitorProductResult.get(i);
            List l_productList = (List)l_marketMap.get(l_row.getMarketCode());
            if (l_productList == null || l_productList.size() == 0)
            {
                l_productList = new ArrayList();
                l_marketMap.put(l_row.getMarketCode(), l_productList);
            }
            l_productList.add(l_row);
        }

        return l_marketMap;
    }
    
    /**
     * 優先市場データ(CSK)テーブルに登録したデータ一覧取得
     *
     * @@throws Exception
     * @@return List
     */
    public List getPrimaryMarketCsks() throws Exception
    {
        List l_list = new ArrayList();
        
        String l_strWhere = " primary_market_code is not null";
        
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_result = l_queryProcessor.doFindAllQuery(
            PrimaryMarketCskParams.TYPE,
            l_strWhere.toString(),
            null);
        
        //　@該当データなしの場合、
        if (l_result == null || l_result.size() == 0)
        {
            return null;
        }
        
        for (int i = 0; i < l_result.size(); i++)
        {
            PrimaryMarketCskRow l_row = (PrimaryMarketCskRow)l_result.get(i);
            String l_key = l_row.getPrimaryMarketCode() + l_row.getFundCode();
            l_list.add(l_key);
        }

        return l_list;
    }
    
    /**
     * 株式取引銘柄一覧取得
     *
     * @@param なし
     * @@throws Exception
     * @@return Hashtable
     */
    public Hashtable getEqtypeTradedProducts() throws Exception
    {
        //時価監視銘柄テーブルに登録した市場一覧を取得する
        Map l_marketMap = getQuoteMonitorProducts();
        //　@該当データなしの場合、
        if (l_marketMap == null)
        {
            String l_tmp = "時価監視銘柄テーブルに株式銘柄データがありません。";
            log.warn(l_tmp);
            return null;
        }
        
        //優先市場データ(CSK)テーブルに登録したデータ一覧取得
        List l_primaryMarketCskList = getPrimaryMarketCsks();
        //　@該当データなしの場合、
        if (l_primaryMarketCskList == null)
        {
            String l_tmp = "優先市場データ(CSK)テーブルにCSK配信データがありません。";
            log.warn(l_tmp);
            return null;
        }
        
        String l_strWhere = 
              " valid_until_biz_date= ? and list_flag= ?"
            + " and product_id in (select product_id from eqtype_product where trade_stop = ?)"
            + " and (buy_cash_stop= ? or sell_cash_stop= ?)";

        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.
            getTradingSystem().getSystemTimestamp(), "yyyyMMdd");

        Object[] l_bindVars = new Object[5];
        l_bindVars[0] = l_strBaseDate;
        l_bindVars[1] = new Long(BooleanEnum.TRUE.intValue());
        l_bindVars[2] = WEB3TradeStopDef.ACTIVE;
        l_bindVars[3] = WEB3TradeStopDef.ACTIVE;
        l_bindVars[4] = WEB3TradeStopDef.ACTIVE;

        if(DBG)
        {
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            log.debug("BindVars[3]=" + l_bindVars[3]);
            log.debug("BindVars[4]=" + l_bindVars[4]);
        }

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            EqtypeTradedProductParams.TYPE,
            l_strWhere.toString(),
            l_bindVars);
        
        //　@該当データなしの場合、
        if (l_lisSearchResult == null || l_lisSearchResult.size() == 0)
        {
            String l_tmp = "株式取引銘柄テーブルにデータがありません。";
            log.warn(l_tmp);
            return null;
        }

        Hashtable l_tradedProductRowTbl = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_lisSearchResult.get(i);
            
            Long l_market = new Long(
                    toMarketId(l_tradedProductRow.getMarketId()));
            
            //時価監視銘柄テーブルに登録されていない市場の銘柄を除外する
            List l_productList = (List)l_marketMap.get(l_market.toString());
            if (l_productList == null || l_productList.size() == 0)
            {
                continue;
            }
            String l_key = l_market + toProductId(l_tradedProductRow.getProductId());
            
            //優先市場データ(CSK)テーブルに登録されていない市場・銘柄を除外する
            if (!l_primaryMarketCskList.contains(l_key))
            {
                continue;
            }
            l_tradedProductRowTbl.put(l_key, l_tradedProductRow);
        }

        return l_tradedProductRowTbl;
    }
       
    /**
     * 先物OP取引銘柄一覧取得
     *
     * @@param なし
     * @@throws Exception
     * @@return List
     */
    public List getIfoTradedProducts() throws Exception
    {
        String l_strWhere = 
              " valid_for_biz_date = ? and list_flag= ?"
            + " and start_trading_date <= ? and last_trading_date >= ?";

        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.
            getTradingSystem().getSystemTimestamp(), "yyyyMMdd");

        Object[] l_bindVars = new Object[4];
        l_bindVars[0] = l_strBaseDate;
        l_bindVars[1] = new Long(BooleanEnum.TRUE.intValue());
        l_bindVars[2] = l_strBaseDate;
        l_bindVars[3] = l_strBaseDate;

        if(DBG)
        {
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            log.debug("BindVars[3]=" + l_bindVars[3]);
        }

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            IfoTradedProductParams.TYPE,
            l_strWhere.toString(),
            l_bindVars);
        
        //　@該当データなしの場合、
        if (l_lisSearchResult == null || l_lisSearchResult.size() == 0)
        {
            return null;
        }
        
        return l_lisSearchResult;
    }
    
    public static String toMarketId(long l_marketId)
    {
        String l_strMarketId = "" + l_marketId;
        return l_strMarketId.substring(INSTITUTION_CODE_SIZE);
    }
    
    public static String toProductId(long l_productId)
    {
        String l_strProductId = "" + l_productId;
        return l_strProductId.substring(
                PRODUCT_HEAD_SIZE, PRODUCT_HEAD_SIZE + PRODUCT_CODE_LENGTH);
    }
    
    public static String toProductKeyword(TradedProduct l_tradedProduct)
    {
        String l_productKeyword = "";

        WEB3IfoProductFakeImpl l_ifoProduct = (WEB3IfoProductFakeImpl)l_tradedProduct.getProduct();
        l_productKeyword = l_ifoProduct.getUnderlyingProductCode() 
                                + "-"
                                + l_ifoProduct.getMonthOfDelivery();
        if(WEB3FuturesOptionDivDef.OPTION.equals(l_ifoProduct.getFutureOptionDiv()))
        {
            l_productKeyword = l_productKeyword 
                + "-"
                + l_ifoProduct.getDerivativeType().intValue()
                + "-"
                + (long)l_ifoProduct.getStrikePrice();
        }
        
        return l_productKeyword;
    }
    
    /**
     * 株式銘柄一覧を取得
     *
     * @@param なし
     * @@throws Exception
     * @@return Hashtable
     */
    public Hashtable getEqtypeProducts() throws Exception
    {
        String l_strWhere = "product_type = ?";
        Object[] l_bindVars = new Object[1];
        l_bindVars[0] = ProductTypeEnum.EQUITY;
        
        if(DBG)
        {
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
        }
        
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            EqtypeProductRow.TYPE,
            l_strWhere.toString(),
            l_bindVars);

        Hashtable l_tblEqtypeProduct = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            EqtypeProductRow l_row = (EqtypeProductRow)
                l_lisSearchResult.get(i);
            l_tblEqtypeProduct.put(new Long(l_row.getProductId()), l_row);
        }
        return l_tblEqtypeProduct;
    }
    
    /**
     * 先物銘柄一覧を取得
     * 
     * @@param なし
     * @@throws Exception
     * @@return Hashtable
     */
    public Hashtable getIfoProducts() throws Exception
    {
        String l_strWhere = "product_type = ?";
        Object[] l_bindVars = new Object[1];
        l_bindVars[0] = ProductTypeEnum.IFO;
        
        if(DBG)
        {
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
        }
        
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
                IfoProductRow.TYPE, 
                l_strWhere.toString(), 
                l_bindVars);

        Hashtable l_tblIfoProduct = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            IfoProductRow l_row = (IfoProductRow) l_lisSearchResult.get(i);
            l_tblIfoProduct.put(new Long(l_row.getProductId()), l_row);
        }
        return l_tblIfoProduct;
    }
    
    /**
     * 銘柄一覧を取得
     *
     * @@param l_productType ProductTypeEnum
     * @@throws Exception
     * @@return Hashtable
     */
    public Hashtable getProducts(ProductTypeEnum l_productType) throws Exception
    {
        String l_strWhere = "product_type = ?";
        Object[] l_bindVars = new Object[1];
        l_bindVars[0] = l_productType;
        
        if(DBG)
        {
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
        }

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            ProductRow.TYPE,
            l_strWhere.toString(),
            l_bindVars);

        Hashtable l_tblProduct = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            ProductRow l_row = (ProductRow)
                l_lisSearchResult.get(i);
            l_tblProduct.put(new Long(l_row.getProductId()), l_row);
        }
        return l_tblProduct;
    }
    
}
@
