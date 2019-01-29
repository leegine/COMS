head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteCacheStoreImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteRecordFormatクラス(WEB3QuoteRecordFormat.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 山田　@卓司(FLJ) 新規作成
                 : 2004/05/19 山田　@卓司(FLJ) 時価情報がキャッシュされていない場合、現在値はDouble.NaNを返す。
                 : 2009/02/05 許　@　@競(FLJ) CSK時価情報チェックの強化対応のため、メソッド追加
 */
package webbroker3.quoteadaptor.stdimpls;

import java.util.Hashtable;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

import webbroker3.quoteadaptor.*;
import webbroker3.util.WEB3LogUtility;

/**
 * WEB3QuoteCacheStoreとWEB3QuoteEventHandlerの実装クラス。
 * メモリ上にキャッシュされた時価情報を管理するためのクラス。
 *
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3QuoteCacheStoreImpl
    implements WEB3QuoteCacheStore, WEB3QuoteEventHandler
{

    /**
     * ログ・ユーティリティ
     */
    private static final WEB3LogUtility _log =
        WEB3LogUtility.getInstance(WEB3QuoteCacheStoreImpl.class);

    /**
     * DEBUG表示フラグ
     */
    private static final boolean DBG = _log.ison();

    /**
     * ロック用オブジェクト
     */
    private final Object putLock = new Object();

    /**
     * 時価データをキャッシュするハッシュテーブル
     */
    private Hashtable hashTable;

    /**
     * コンストラクタ
     * 
     * @@param size ハッシュテーブルのデフォルトサイズ
     */
    WEB3QuoteCacheStoreImpl(int size)
    {
        hashTable = new Hashtable(size);
    }

    /**
     * 株式の時価情報を取得する。<br>
     * <br>
     * キャッシュに時価指定した銘柄の時価情報が存在しない場合、
     * 取引銘柄から取得した前日終値を現在値にセットした時価情報を
     * 生成し、それを返す。
     * 
     * @@param tradedProduct 株式取引銘柄
     * @@param realType リアル区分
     * @@return 時価情報
     * @@see webbroker3.quoteadaptor.WEB3QuoteCacheStore#get(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct, webbroker3.quoteadaptor.RealType)
     */
    public WEB3EqTypeQuoteData get(
        EqTypeTradedProduct tradedProduct,
        RealType realType)
    {
        
        WEB3EqTypeQuoteDataImpl quoteData =
            new WEB3EqTypeQuoteDataImpl(tradedProduct, realType);
        String key = quoteData.createKey();
        WEB3CacheData cache = (WEB3CacheData) hashTable.get(key);
        if (cache != null)
        {
            // キャッシュに登録されている場合
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);

        } else
        {
            // キャッシュに登録されていない場合
            if (_log.ison())
            {
                _log.debug("No entry in cache. key : " + key);
            }

            cache = new WEB3CacheData();
            cache.setParams(quoteData);

            synchronized (putLock)
            {
                if (hashTable.get(key) == null)
                {
                    hashTable.put(key, cache);
                }
            }

            quoteData.currentPrice = cache.currentPrice;

        }

        return quoteData;
    }

    /**
     * 指数先物・オプションの時価情報を取得する。<br>
     * <br>
     * キャッシュに時価指定した銘柄の時価情報が存在しない場合、
     * 取引銘柄から取得した前日終値を現在値にセットした時価情報を
     * 生成し、それを返す。
     * 
     * @@param tradedProduct 指数先物・オプション取引銘柄
     * @@param realType リアル区分
     * @@return 時価情報
     * @@see webbroker3.quoteadaptor.WEB3QuoteCacheStore#get(com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct, webbroker3.quoteadaptor.RealType)
     */
    public WEB3IfoQuoteData get(
        IfoTradedProduct tradedProduct,
        RealType realType)
    {
        
        WEB3IfoQuoteDataImpl quoteData =
            new WEB3IfoQuoteDataImpl(tradedProduct, realType);
        String key = quoteData.createKey();
        WEB3CacheData cache = (WEB3CacheData) hashTable.get(key);
        
        if (cache != null)
        {
            // キャッシュに登録されている場合
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);

        } else
        {
            // キャッシュに登録されていない場合
            if (_log.ison())
            {
                _log.debug("No entry in cache. key : " + key);
            }

            cache = new WEB3CacheData();
            cache.setParams(quoteData);

            synchronized (putLock)
            {
                if (hashTable.get(key) == null)
                {
                    hashTable.put(key, cache);
                }
            }

            quoteData.currentPrice = cache.currentPrice;

        }

        return quoteData;
        
    }
    
    /**
     * 株式の時価情報を取得する。<br>
     * <br>
     * キャッシュに時価指定した銘柄の時価情報が存在しない場合、
     * nullを返す。
     * 
     * @@param tradedProduct 株式取引銘柄
     * @@param realType リアル区分
     * @@return 時価情報
     */
    public WEB3EqTypeQuoteData getQuote(
        EqTypeTradedProduct tradedProduct,
        RealType realType)
    {
        
        WEB3EqTypeQuoteDataImpl quoteData =
            new WEB3EqTypeQuoteDataImpl(tradedProduct, realType);
        String key = quoteData.createKey();
        WEB3CacheData cache = (WEB3CacheData) hashTable.get(key);
        if (cache != null)
        {
            // キャッシュに登録されている場合
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);
        } else
        {
            // キャッシュに登録されていない場合
            if (_log.ison())
            {
                _log.debug("No entry in cache. key : " + key);
            }
            return null;
        }

        return quoteData;
    }

    /**
     * 指数先物・オプションの時価情報を取得する。<br>
     * <br>
     * キャッシュに時価指定した銘柄の時価情報が存在しない場合、
     * nullを返す。
     * 
     * @@param tradedProduct 指数先物・オプション取引銘柄
     * @@param realType リアル区分
     * @@return 時価情報
     */
    public WEB3IfoQuoteData getQuote(
        IfoTradedProduct tradedProduct,
        RealType realType)
    {
        
        WEB3IfoQuoteDataImpl quoteData =
            new WEB3IfoQuoteDataImpl(tradedProduct, realType);
        String key = quoteData.createKey();
        WEB3CacheData cache = (WEB3CacheData) hashTable.get(key);
        
        if (cache != null)
        {
            // キャッシュに登録されている場合
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);

        } else
        {
            // キャッシュに登録されていない場合
            if (_log.ison())
            {
                _log.debug("No entry in cache. key : " + key);
            }
            return null;
        }

        return quoteData;
        
    }

    /**
    /**
     * 指数の時価情報を取得する。<br>
     * <br>
     * キャッシュに時価指定した銘柄の時価情報が存在しない場合、
     * 空の時価情報を生成し、それを返す。
     * 
     * @@param productCode 指数銘柄コード
     * @@param realType リアル区分
     * @@return 時価情報
     * @@see webbroker3.quoteadaptor.WEB3QuoteCacheStore#get(java.lang.String, webbroker3.quoteadaptor.RealType)
     */
    public WEB3IndexQuoteData get(IndexType indexType, RealType realType)
    {
        
        WEB3IndexQuoteDataImpl quoteData =
            new WEB3IndexQuoteDataImpl(indexType, realType);
        String key = quoteData.createKey();
        WEB3CacheData cache = (WEB3CacheData) hashTable.get(key);
        
        if (cache != null)
        {
            // キャッシュに登録されている場合
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);

        } else
        {
            // キャッシュに登録されていない場合
            if (_log.ison())
            {
                _log.debug("No entry in cache. key : " + key);
            }

            cache = new WEB3CacheData();
            cache.setParams(quoteData);

            synchronized (putLock)
            {
                if (hashTable.get(key) == null)
                {
                    hashTable.put(key, cache);
                }
            }

        }

        return quoteData;
        
    }

    /**
     * 時価情報通知イベントをキャッシュに追加する。
     * 
     * @@param qe 時価情報通知イベント。
     */
    public void push(WEB3QuoteEvent qe)
    {

        WEB3QuoteEventImpl event = (WEB3QuoteEventImpl) qe;

        synchronized (putLock)
        {

            String key = event.createKey();
            WEB3CacheData old = (WEB3CacheData) hashTable.get(key);

            // キャッシュされていない場合、データを追加
            if (old == null)
            {
                if (DBG)
                {
                    _log.debug("CashStore#push: add new entry: " + key
                            + ", cache store size: " + hashTable.size());
                }

                WEB3CacheData newEntry = new WEB3CacheData();
                newEntry.setParams(event);
                hashTable.put(key, newEntry);

                // キャッシュされている場合はデータを更新
            } else
            {
                if (DBG)
                {
                    _log.debug(
                        "CashStore#push: overwrite existing entry: " + key
                            + ", cache store size: " + hashTable.size());
                }

                old.setParams(event);

            }
            
        }

    }
    
    /**
     * キャッシュされている時価情報をファ@イルに出力する。
     */
    void dump()
    {
        synchronized (putLock)
        {
            WEB3QuoteUtil.dump(hashTable);
        }
    }

    // WebBroker3でメモリ上にキャッシュされる時価情報
    private static class WEB3CacheData extends AbstractQuoteData
    {

        WEB3CacheData()
        {
            super();
        }

    }

}
@
