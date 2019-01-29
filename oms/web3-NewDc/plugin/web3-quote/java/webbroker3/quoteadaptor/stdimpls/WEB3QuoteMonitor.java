head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteMonitor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価情報を監視するクラス(WEB3QuoteMonitor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/26 山田　@卓司(FLJ) 新規作成
                 : 2005/05/24 山田　@卓司(FLJ) 監視の設定をWEB3QuoteMonitorSettingsから取得するように変更
                 : 2005/05/24 山田　@卓司(FLJ) 監視サービスの有効時間をWEB3QuoteMonitorSettingsから取得するように変更
                 : 2009/01/28 許　@　@競(FLJ) CSK時価情報チェックの強化対応
 */
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import webbroker3.quoteadaptor.IndexType;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3EqTypeQuoteData;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3IndexQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteCacheStore;
import webbroker3.quoteadaptor.stdimpls.data.QuoteMonitorProductRow;
import webbroker3.util.WEB3LogUtility;


/**
 * 時価情報を監視するクラス。
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3QuoteMonitor
{
    
    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3QuoteMonitor.class);
    
    /**
     * デバック出力フラグ
     */
    private static final boolean DBG = LOG.ison();
    
    /**
     * 時価情報キャッシュストア
     */
    private final WEB3QuoteCacheStore cacheStore;
    
    /**
     * 時価情報監視の設定
     */
    private final WEB3QuoteMonitorSettings settings;
    
    /**
     * 時価情報を監視するスレッドを管理するタイマー
     */
    private Timer timer;
    
    /**
     * 時価情報の最終更新時間
     */
    private Timestamp lastUpdatedTimestamp;
    
    /**
     * 時価情報が更新されなかった回数
     */
    private int count;
    
    /**
     * 時価監視DBマネージャ
     */
    private WEB3QuoteMonitorDBManager quoteMonitorDBManager = new WEB3QuoteMonitorDBManager();
    
    /**
     * 市場ごと株式時価情報監視統計Map
     */
    private HashMap quoteEqtypeStatisticsMap = new HashMap();
    
    /**
     * 市場ごと先物OP時価情報監視統計Map
     */
    private HashMap quoteIfoStatisticsMap = new HashMap();
    
    WEB3QuoteMonitor(WEB3QuoteCacheStore cacheStore,
            WEB3QuoteMonitorSettings settings)
    {
        this.cacheStore = cacheStore;
        this.settings = settings;
    }
            
    
    /**
     * 監視を開始する。
     */
    synchronized void start()
    {
        
        if (isRunning())
        {
            LOG.warn("QuoteMonitor has already started.");
            return;
        }
        
        loadMonitorProduct();
            
        TimerTask task = new QuoteMonitorTask();
        timer = new Timer(true);
        timer.schedule(task, 0, settings.getInterval());
        LOG.info("QuoteMonitor started.");
        
    }
    
    /**
     * 監視を終了する。
     */
    synchronized void stop()
    {
        
        if (!isRunning())
        {
            return;
        }
        
        timer.cancel();
        timer = null;
        lastUpdatedTimestamp = null;
        count = 0;
        
        quoteEqtypeStatisticsMap.clear();
        quoteIfoStatisticsMap.clear();
            
        LOG.info("QuoteMonitor stoped.");
            
    }
    
    /**
     * 監視中か判定する。
     */
    synchronized boolean isRunning()
    {
        return (timer != null);
    }
    
    /**
     * 監視対象銘柄ロード処理
     */
    private void loadMonitorProduct()
    {
        LOG.debug("Start loading MonitorProduct.");
        
        try
        {
            //時価監視銘柄テーブルを検索する
            List l_quoteMonitorProductResult = quoteMonitorDBManager.getQuoteMonitorProducts();

            QuoteMonitorProductRow l_quoteMonitorProductRow;
            //株式の監視対象銘柄を取得する
            for (int i = 0; i < l_quoteMonitorProductResult.size(); i++)
            {
                l_quoteMonitorProductRow = (QuoteMonitorProductRow)l_quoteMonitorProductResult.get(i);
                //株式銘柄以外の場合
                if (!ProductTypeEnum.EQUITY.equals(l_quoteMonitorProductRow.getProductType()))
                {
                    continue;
                }
                
                //株式銘柄の場合
                try
                {
                    //株式取引銘柄マスタテーブルを検索する
                    String l_marketCode  = l_quoteMonitorProductRow.getMarketCode();
                    String l_productCode = l_quoteMonitorProductRow.getProductCode();
                    List l_eqtypeTradedProductsResult = 
                        quoteMonitorDBManager.getEqtypeTradedProducts(l_marketCode, l_productCode);
                    if (l_eqtypeTradedProductsResult == null)
                    {
                        continue;
                    }
                    
                    EqtypeTradedProductRow l_tradedProductRow = 
                        (EqtypeTradedProductRow)l_eqtypeTradedProductsResult.get(0);
                    
                    //株式時価情報監視統計Mapから、市場ごと時価情報監視統計オブジェクトを取得する
                    WEB3QuoteMonitorStatistics l_statistics = 
                        (WEB3QuoteMonitorStatistics)quoteEqtypeStatisticsMap.get(l_marketCode);
                    //取得できない場合、オブジェクト生成し、Mapに追加する
                    if (l_statistics == null)
                    {
                        int l_countThreshold = settings.getCountThreshold(l_marketCode);
                        l_statistics = new WEB3QuoteMonitorStatistics(l_marketCode, l_countThreshold);
                        quoteEqtypeStatisticsMap.put(l_marketCode, l_statistics);
                    }
                    
                    try
                    {
                        //取引銘柄オブジェクトを取得する
                        TradedProduct l_tradedProduct = 
                            quoteMonitorDBManager.getEqTypeTradedProduct(l_tradedProductRow);
                        //監視対象として、取引銘柄を市場ごと時価情報監視統計オブジェクトに追加する
                        l_statistics.addTradedProduct(l_tradedProduct);
                    }
                    catch(Exception l_ex)
                    {
                        LOG.warn("Error happened while turning EqTypeTradedProductRow to EqTypeTradedProduct."
                                + "ProductId:" + l_tradedProductRow.getProductId());
                    }
                }
                catch(Exception l_ex)
                {
                    LOG.warn("Error happened while searching EqtypeTradedProductRow.",l_ex);
                }
            }
            
            //先物OPの監視対象銘柄を取得する
            for (int i = 0; i < l_quoteMonitorProductResult.size(); i++)
            {
                l_quoteMonitorProductRow = (QuoteMonitorProductRow)l_quoteMonitorProductResult.get(i);
                //先物OP以外の場合
                if (!ProductTypeEnum.IFO.equals(l_quoteMonitorProductRow.getProductType()))
                {
                    continue;
                }
                
                //先物OPの場合
                try
                {
                    //直近の限月を取得する
                    String l_marketCode  = l_quoteMonitorProductRow.getMarketCode();
                    String l_productCode = l_quoteMonitorProductRow.getProductCode();
                    List l_ifoDeliveryMonthList = 
                        quoteMonitorDBManager.getIfoLatestDeliveryMonth(l_productCode);
                    if (l_ifoDeliveryMonthList == null)
                    {
                        continue;
                    }

                    for (int j = 0; j < l_ifoDeliveryMonthList.size(); j++)
                    {
                        //先物OP取引銘柄マスタテーブルを検索する
                        IfoProductRow l_ifoDeliveryMonthRow = 
                            (IfoProductRow)l_ifoDeliveryMonthList.get(j);
                        IfoTradedProductRow[] l_ifoTradedProductsList = 
                            quoteMonitorDBManager.getIfoTradedProducts(l_ifoDeliveryMonthRow);
                        if (l_ifoTradedProductsList == null || l_ifoTradedProductsList.length == 0)
                        {
                            continue;
                        }
                        for (int k = 0; k < l_ifoTradedProductsList.length; k++)
                        {
                            //先物OP時価情報監視統計Mapから、市場ごと時価情報監視統計オブジェクトを取得する
                            WEB3QuoteMonitorStatistics l_statistics = 
                                (WEB3QuoteMonitorStatistics)quoteIfoStatisticsMap.get(l_marketCode);
                            //取得できない場合、オブジェクト生成し、Mapに追加する
                            if (l_statistics == null)
                            {
                                int l_countThreshold = settings.getCountThreshold(settings.IFO);
                                l_statistics = new WEB3QuoteMonitorStatistics(l_marketCode, l_countThreshold);
                                quoteIfoStatisticsMap.put(l_marketCode, l_statistics);
                            }
                            
                            try
                            {
                                //取引銘柄オブジェクトを取得する
                                TradedProduct l_tradedProduct = 
                                    WEB3QuoteMonitorDBManager.getInstance().getIfoTradedProduct(l_ifoTradedProductsList[k]);
                                //監視対象として、取引銘柄を市場ごと時価情報監視統計オブジェクトに追加する
                                l_statistics.addTradedProduct(l_tradedProduct);
                            }
                            catch(Exception l_ex)
                            {
                                LOG.warn("Error happened while turning IfoTradedProductRow to IfoTradedProduct."
                                        + "ProductId:" + l_ifoTradedProductsList[k].getProductId());
                            }
                        }
                    }
                }
                catch(Exception l_ex)
                {
                    LOG.warn("Unexpected Error happened.",l_ex);
                }
            }
        }
        catch(Exception l_ex)
        {
            LOG.error("Unexpected Error happened.", l_ex);
        }
        
        if (DBG)
        {
            printMonitorProductInfo();
        }
        
        LOG.debug("Loading MonitorProduct finished.");
    }

    /**
     * 監視対象銘柄をプリントする
     */
    private void printMonitorProductInfo()
    {
        for (Iterator it = quoteEqtypeStatisticsMap.entrySet().iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            WEB3QuoteMonitorStatistics l_quoteMonitorStatistics = (WEB3QuoteMonitorStatistics) entry.getValue();
            
            String l_buff = 
                "getTradedProducts() marketCode:" + l_quoteMonitorStatistics.getMarketCode() + ",ProductId:";
            EqTypeTradedProduct[] l_tradedProductList =
                new EqTypeTradedProduct[l_quoteMonitorStatistics.getTradedProducts().size()];;
            l_tradedProductList = 
                (EqTypeTradedProduct[])l_quoteMonitorStatistics.getTradedProducts().toArray(l_tradedProductList);
            for (int m = 0; m < l_tradedProductList.length; m++)
            {
                l_buff = l_buff + l_tradedProductList[m].getProduct().getProductId() + ",";
            }
            LOG.debug(l_buff);
        }
        
        for (Iterator it = quoteIfoStatisticsMap.entrySet().iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            WEB3QuoteMonitorStatistics l_quoteMonitorStatistics = (WEB3QuoteMonitorStatistics) entry.getValue();
            
            String l_buff = 
                "getTradedProducts() marketCode:" + l_quoteMonitorStatistics.getMarketCode() + ",ProductId:";
            IfoTradedProduct[] l_tradedProductList =
                new IfoTradedProduct[l_quoteMonitorStatistics.getTradedProducts().size()];
            l_tradedProductList = 
                (IfoTradedProduct[])l_quoteMonitorStatistics.getTradedProducts().toArray(l_tradedProductList);
            for (int m = 0; m < l_tradedProductList.length; m++)
            {
                l_buff = l_buff + l_tradedProductList[m].getProduct().getProductId() + ",";
            }
            LOG.debug(l_buff);
        }
    }
    
    /**
     * 監視処理
     */
    private synchronized void process()
    {
        Timestamp systemTimestamp = GtlUtils.getSystemTimestamp();
        if (!settings.isActiveTimezone(systemTimestamp))
        {
            LOG.debug("Out of a setting range of monitor time.");
            return;
        }
        
        LOG.debug("Process is starting.");
        
        if (quoteEqtypeStatisticsMap != null && quoteEqtypeStatisticsMap.size() > 0)
        {
            for (Iterator it = quoteEqtypeStatisticsMap.entrySet().iterator(); it.hasNext();)
            {
                Map.Entry entry = (Map.Entry) it.next();
                WEB3QuoteMonitorStatistics l_quoteMonitorStatistics = (WEB3QuoteMonitorStatistics) entry.getValue();
                
                EqTypeTradedProduct[] l_tradedProductList =
                    new EqTypeTradedProduct[l_quoteMonitorStatistics.getTradedProducts().size()];;
                l_tradedProductList = 
                    (EqTypeTradedProduct[])l_quoteMonitorStatistics.getTradedProducts().toArray(l_tradedProductList);
                for (int i = 0; i < l_tradedProductList.length; i++)
                {
                    EqTypeTradedProduct l_tradedProduct = (EqTypeTradedProduct) l_tradedProductList[i];
                    
                    WEB3EqTypeQuoteData l_quoteData = 
                        cacheStore.get(l_tradedProduct, RealType.REAL);
                    l_quoteMonitorStatistics.checkQuote(l_tradedProduct, l_quoteData);
                }
                l_quoteMonitorStatistics.checkCount();
            }
        }
        
        if (quoteIfoStatisticsMap != null && quoteIfoStatisticsMap.size() > 0)
        {
            for (Iterator it = quoteIfoStatisticsMap.entrySet().iterator(); it.hasNext();)
            {
                Map.Entry entry = (Map.Entry) it.next();
                WEB3QuoteMonitorStatistics l_quoteMonitorStatistics = (WEB3QuoteMonitorStatistics) entry.getValue();
                
                IfoTradedProduct[] l_tradedProductList =
                    new IfoTradedProduct[l_quoteMonitorStatistics.getTradedProducts().size()];;
                l_tradedProductList = 
                    (IfoTradedProduct[])l_quoteMonitorStatistics.getTradedProducts().toArray(l_tradedProductList);
                for (int i = 0; i < l_tradedProductList.length; i++)
                {
                    IfoTradedProduct l_tradedProduct = (IfoTradedProduct) l_tradedProductList[i];
                    
                    WEB3IfoQuoteData l_quoteData = 
                        cacheStore.get(l_tradedProduct, RealType.REAL);
                    l_quoteMonitorStatistics.checkQuote(l_tradedProduct, l_quoteData);
                }
                l_quoteMonitorStatistics.checkCount();
            }
        }
        
        monitorIndexQuote();
        
        LOG.debug("Process finished.");
    }
    
    /**
     * 指数監視処理
     */
    private void monitorIndexQuote()
    {
        boolean isUpdated = false;
        WEB3IndexQuoteData quoteData = 
            cacheStore.get(IndexType.NIKKEI225_INDEX, RealType.REAL);
        Timestamp currentTimestamp = quoteData.getCurrentPriceTime();
        
        if (lastUpdatedTimestamp != null)
        {
            if (currentTimestamp != null 
                && (lastUpdatedTimestamp.compareTo(currentTimestamp) < 0))
            {
                isUpdated = true;
            }
        } else
        {
            if (currentTimestamp != null)
            {
                isUpdated = true;
            }
        }
        
        if (isUpdated)
        {
            
            lastUpdatedTimestamp = currentTimestamp;
            count = 0;
            
            if (DBG)
            {
                LOG.debug("QuoteData was updated. [LastUpdatedTimestamp=" + lastUpdatedTimestamp + "]");
            }
            
        } else
        {
            
            count++;
            
            if (DBG)
            {
                LOG.debug("QuoteData is not updated." 
                        + " [LastUpdatedTimestamp=" + lastUpdatedTimestamp 
                        + ", filureCount=" + count + "]");
            }
            
        }
        
        if (count > settings.getWarningThreshold())
        {
            LOG.error("（指数）場中時価配信漏れが発生.(missing quote of INDEX-NIKKEI225 is found.)");
        }
        
    }
    
    /**
     * 時価情報を監視するTimerTask
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class QuoteMonitorTask extends TimerTask
    {

        /**
         * @@see java.util.TimerTask#run()
         */
        public void run()
        {
            process();
        }
        
    }

}
@
