head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteMonitorStatistics.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 市場ごと時価情報監視統計(WEB3QuoteMonitorStatistics.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/01/28 許　@　@競(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.quoteadaptor.WEB3QuoteData;
import webbroker3.util.WEB3LogUtility;


public class WEB3QuoteMonitorStatistics {

    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3QuoteMonitorStatistics.class);
    
    /**
     * デバック出力フラグ
     */
    private static final boolean DBG = LOG.ison();
    
    /**
     * 市場コード
     */
    private String marketCode;
    
    /**
     * ログ出力制御フラグ
     */
    private boolean logFlg;
    
    /**
     * 取引銘柄Map
     */
    private Map tradedProducts = new HashMap();
    
    /**
     * エラー銘柄リスト
     */
    private List errorProductList= new ArrayList();
    
    /**
     * 銘柄ごとカウントMap
     */
    private Map productCountMap = new HashMap();
    
    /**
     * カウント閾値
     */
    private int countThreshold;
    
    /**
     * タイム閾値
     */
    private int timeThreshold;
    
    /**
     * 銘柄タイプ
     */
    private ProductTypeEnum productType = null;
    
    
    WEB3QuoteMonitorStatistics(String l_marketCode, int l_countThreshold)
    {
        this.marketCode = l_marketCode;
        this.countThreshold = l_countThreshold;
        
        this.timeThreshold = WEB3QuoteMonitorSettings.getInstance().getWarningThreshold();
        this.logFlg = true;
    }
    
    /**
     * 取引銘柄をマップに追加
     *
     * @@param  l_tradedProduct TradedProduct
     */
    public void addTradedProduct(TradedProduct l_tradedProduct)
    {
        tradedProducts.put(l_tradedProduct, null);
        productCountMap.put(l_tradedProduct, new ProductCount());
        if (productType == null)
        {
            productType = l_tradedProduct.getProduct().getProductType();
        }
    }
    
    /**
     * 取引銘柄一覧を取得
     */
    public Set getTradedProducts()
    {
        return tradedProducts.keySet();
    }
    
    /**
     * 時価更新チェック処理
     */
    public void checkQuote(TradedProduct l_tradedProduct, WEB3QuoteData l_currentQuoteData)
    {
        boolean isUpdated = false;
        WEB3QuoteData l_quoteData = (WEB3QuoteData)tradedProducts.get(l_tradedProduct);
        
        if (l_quoteData == null)
        {
            tradedProducts.put(l_tradedProduct, l_currentQuoteData);
            return;
        }

        ProductCount l_productCount;

        //買気配値チェック
        Timestamp lastUpdatedTimestamp = l_quoteData.getAskPriceTime();
        Timestamp currentTimestamp = l_currentQuoteData.getAskPriceTime();
        if (lastUpdatedTimestamp != null)
        {
            if (currentTimestamp != null 
                && (lastUpdatedTimestamp.compareTo(currentTimestamp) < 0))
            {
                isUpdated = true;
            }
        } 
        else
        {
            if (currentTimestamp != null)
            {
                isUpdated = true;
            }
        }
        
        if (!isUpdated)
        {
            //売気配値チェック
            lastUpdatedTimestamp = l_quoteData.getBidPriceTime();
            currentTimestamp = l_currentQuoteData.getBidPriceTime();
            if (lastUpdatedTimestamp != null)
            {
                if (currentTimestamp != null 
                    && (lastUpdatedTimestamp.compareTo(currentTimestamp) < 0))
                {
                    isUpdated = true;
                }
            } 
            else
            {
                if (currentTimestamp != null)
                {
                    isUpdated = true;
                }
            }
        }
        
        if (isUpdated)
        {
            //該当銘柄カウントをクリアする
            l_productCount = (ProductCount) productCountMap.get(l_tradedProduct);
            l_productCount.clear();
            //エラー銘柄リストから該当銘柄を削除する
            errorProductList.remove(l_tradedProduct);
            //時価更新
            tradedProducts.put(l_tradedProduct, l_currentQuoteData);
        }
        else
        {
            //該当銘柄カウントを増加する
            l_productCount = (ProductCount) productCountMap.get(l_tradedProduct);
            l_productCount.add();
            
            //タイム閾値以上の場合、エラー銘柄リストに該当銘柄を追加する
            if (l_productCount.get() >= this.timeThreshold && 
                errorProductList.contains(l_tradedProduct) == false)
            {
                errorProductList.add(l_tradedProduct);
            }
        }

    }
    
    /**
     * 市場カウント閾値比較処理
     */
    public void checkCount()
    {
        String l_productType = "";
        if(ProductTypeEnum.EQUITY.equals(productType))
        {
            l_productType = "（株式）";
        }
        else if(ProductTypeEnum.IFO.equals(productType))
        {
            l_productType = "（先物OP）";
        }
        
        if (DBG)
        {
            //配信漏れ状況出力
            if (errorProductList.size() > 0)
            {
                LOG.debug(l_productType+
                        "場中配信漏れ状況(missing quote products)は　@市場コード："+marketCode+
                        ",漏れ件数："+errorProductList.size()+"");
            }
        }
        
        //市場カウント閾値以上の場合
        if (errorProductList.size() >= this.countThreshold)
        {
            if (logFlg == true)
            {
                List l_list = new ArrayList();
                for (int i = 0; i < errorProductList.size(); i++)
                {
                    if (i >= WEB3QuoteConstants.MISSING_QUOTE_MAX_NUMBER)
                    {
                        break;
                    }
                    TradedProduct l_tradedProduct = (TradedProduct)errorProductList.get(i);
                    l_list.add(toProductKeyword(l_tradedProduct));
                }
                
                //時価配信漏れログ出力
                LOG.error(l_productType+
                        "場中時価配信漏れが発生. 市場コード："+marketCode+
                        ",件数(amount of missing quote products)："+errorProductList.size());
                LOG.info(l_productType+
                        "場中配信漏れ銘柄(missing quote products)は　@市場コード："+marketCode+
                        ",銘柄情報："+l_list.toString()+"");

                logFlg = false;
            }
            return;
        }
        
        //市場カウント閾値が０の場合（復旧時）
        if (errorProductList.size() == 0)
        {
            if (logFlg == false)
            {
                //時価配信復旧ログ出力
                LOG.info(l_productType+
                        "場中時価配信復旧されました.市場コード："+marketCode+
                        " (missing quote recovery)");
                logFlg = true;
            }
        }
    }
    
    public String getMarketCode()
    {
        return marketCode;
    }
    
    private String toProductId(long l_productId)
    {
        String l_strProductId = "" + l_productId;
        return l_strProductId.substring(WEB3QuoteConstants.PRODUCT_HEAD_SIZE,
                WEB3QuoteConstants.PRODUCT_HEAD_SIZE + WEB3QuoteConstants.PRODUCT_CODE_LENGTH);
    }
    
    private String toProductKeyword(TradedProduct l_tradedProduct)
    {
        String l_productKeyword = "";
        if(ProductTypeEnum.EQUITY.equals(productType))
        {
            l_productKeyword = toProductId(l_tradedProduct.getProduct().getProductId());
        }
        else if(ProductTypeEnum.IFO.equals(productType))
        {
            IfoProductRow l_ifoProductRow = ((WEB3IfoProductFakeImpl)l_tradedProduct.getProduct()).getIfoProductRow();
            l_productKeyword = l_ifoProductRow.getUnderlyingProductCode() 
                                    + "-"
                                    + l_ifoProductRow.getMonthOfDelivery();
            if(WEB3FuturesOptionDivDef.OPTION.equals(l_ifoProductRow.getFutureOptionDiv()))
            {
                l_productKeyword = l_productKeyword 
                    + "-"
                    + l_ifoProductRow.getDerivativeType().intValue()
                    + "-"
                    + (long)l_ifoProductRow.getStrikePrice();
            }
        }
        
        return l_productKeyword;
    }

    /**
     * 銘柄ごとカウントクラス
     */
    private class ProductCount
    {
        int count = 0;
        
        void clear()
        {
            count = 0;
        }
        
        void add()
        {
            count++;
        }
        
        int get()
        {
            return count;
        }
    }
}
@
