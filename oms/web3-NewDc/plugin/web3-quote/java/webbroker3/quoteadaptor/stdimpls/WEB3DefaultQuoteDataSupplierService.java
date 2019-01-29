head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3DefaultQuoteDataSupplierService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteData;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoQuoteData;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

import webbroker3.quoteadaptor.*;
import webbroker3.util.WEB3LogUtility;

/**
 * WEB3QuoteDataSupplierServiceの標準実装クラス。<br>
 * <BR>
 * WEB3QuoteDataSupplierServiceに定義されている時価取得のためのメソッドと
 * 管理用のメソッドが定義されている。<BR>
 * 管理用のメソッドはアプリケーション側からは使用できないように、パッケージスコープ
 * で定義されている。<BR>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3DefaultQuoteDataSupplierService
    implements WEB3QuoteDataSupplierService
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3DefaultQuoteDataSupplierService.class);
    private WEB3QuoteCacheStore cacheStore;
    private final HashMap sourceList = new HashMap(23);
    private static final LockManager lock = new LockManager();
    private List tcpQuoteDataSource = new ArrayList();
    private List rmmQuoteDataSource = new ArrayList();

    /**
     * コンストラクタ。
     *
     */
    public WEB3DefaultQuoteDataSupplierService()
    {
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getEqTypeQuote(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct)
     */
    public EqTypeQuoteData getEqTypeQuote(EqTypeTradedProduct tradedProduct)
        throws NotFoundException
    {
        return getEqTypeQuote(tradedProduct, RealType.REAL);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getEqTypeQuote(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct, webbroker3.quoteadaptor.RealType)
     */
    public WEB3EqTypeQuoteData getEqTypeQuote(
        EqTypeTradedProduct tradedProduct,
        RealType realType)
        throws NotFoundException
    {
        EqTypeQuoteData quote = null;
        lock.getSharedLock();
        quote = cacheStore.get(tradedProduct, realType);
        lock.releaseLock();
        return (WEB3EqTypeQuoteData) quote;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getIfoQuote(com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct)
     */
    public IfoQuoteData getIfoQuote(IfoTradedProduct tradedProduct)
        throws NotFoundException
    {
        return getIfoQuote(tradedProduct, RealType.REAL);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getIfoQuote(com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct, webbroker3.quoteadaptor.RealType)
     */
    public WEB3IfoQuoteData getIfoQuote(
        IfoTradedProduct tradedProduct,
        RealType realType)
        throws NotFoundException
    {
        IfoQuoteData quote = null;
        lock.getSharedLock();
        quote = cacheStore.get(tradedProduct, realType);
        lock.releaseLock();
        return (WEB3IfoQuoteData) quote;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getQuote(com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct)
     */
    public QuoteData getQuote(TradedProduct tradedProduct)
        throws NotFoundException
    {
        return getQuote(tradedProduct, RealType.REAL);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getQuote(com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct, webbroker3.quoteadaptor.RealType)
     */
    public QuoteData getQuote(TradedProduct tradedProduct, RealType realType)
        throws NotFoundException
    {
        if (tradedProduct instanceof EqTypeTradedProduct)
        {
            return getEqTypeQuote(
                (EqTypeTradedProduct) tradedProduct,
                realType);
        } else if (tradedProduct instanceof IfoTradedProduct)
        {
            return getIfoQuote((IfoTradedProduct) tradedProduct, realType);
        } else
        {
            String message =
                "Unsupported TradedProduct instance. [" + tradedProduct.getClass().getName() + "]";
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * 時価情報が存在するかどうかチェック用のメソッド<BR>
     * 時価がない場合、nullを返す<BR>
     * 
     * @@param tradedProduct 時価情報を取得する銘柄の取引銘柄。
     * @@param realType リアル区分
     * @@return 時価情報
     * @@throws NotFoundException
     */
    public QuoteData getQuoteForCheck(TradedProduct tradedProduct, RealType realType)
        throws NotFoundException
    {
        if (tradedProduct instanceof EqTypeTradedProduct)
        {
            return getEqTypeQuoteForCheck(
                (EqTypeTradedProduct) tradedProduct,
                realType);
        } else if (tradedProduct instanceof IfoTradedProduct)
        {
            return getIfoQuoteForCheck((IfoTradedProduct) tradedProduct, realType);
        } else
        {
            String message =
                "Unsupported TradedProduct instance. [" + tradedProduct.getClass().getName() + "]";
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getIndexQuote(webbroker3.quoteadaptor.IndexType, webbroker3.quoteadaptor.RealType)
     */
    public WEB3IndexQuoteData getIndexQuote(IndexType indexType, RealType realType)
        throws NotFoundException
    {
        WEB3IndexQuoteData quote = null;
        lock.getSharedLock();
        quote = cacheStore.get(indexType, realType);
        lock.releaseLock();
        return quote;
    }
    
    /**
     * 時価情報が存在するかどうかチェック用のメソッド<BR>
     * 時価がない場合、nullを返す<BR>
     * 
     * @@param tradedProduct 時価情報を取得する株式銘柄の取引銘柄。
     * @@param realType リアル区分
     * @@return 時価情報
     * @@throws NotFoundException
     */
    private WEB3EqTypeQuoteData getEqTypeQuoteForCheck(
        EqTypeTradedProduct tradedProduct,
        RealType realType)
        throws NotFoundException
    {
        EqTypeQuoteData quote = null;
        lock.getSharedLock();
        quote = cacheStore.getQuote(tradedProduct, realType);
        lock.releaseLock();
        return (WEB3EqTypeQuoteData) quote;
    }
    
    /**
     * 時価情報が存在するかどうかチェック用のメソッド<BR>
     * 時価がない場合、nullを返す<BR>
     * 
     * @@param tradedProduct 時価情報を取得する先物OP銘柄の取引銘柄。
     * @@param realType リアル区分
     * @@return 時価情報
     * @@throws NotFoundException
     */
    private WEB3IfoQuoteData getIfoQuoteForCheck(
        IfoTradedProduct tradedProduct,
        RealType realType)
        throws NotFoundException
    {
        IfoQuoteData quote = null;
        lock.getSharedLock();
        quote = cacheStore.getQuote(tradedProduct, realType);
        lock.releaseLock();
        return (WEB3IfoQuoteData) quote;
    }

    /**
     * WEB3QuoteCasheStoreクラスのインスタンスを登録する。
     *
     * @@param cs QuoteCasheStoreクラスのインスタンス。
     */
    void setCacheStore(WEB3QuoteCacheStore cs)
    {
        lock.getExclusiveLock();
        this.cacheStore = cs;
        lock.releaseLock();
    }

    /**
     * 登録されたQuoteCasheStoreクラスのインスタンスを取得する。
     *
     * @@return QuoteCasheStoreクラスのインスタンス。
     */
    WEB3QuoteCacheStore getCacheStore()
    {
        WEB3QuoteCacheStore qcs = null;
        lock.getSharedLock();
        qcs = this.cacheStore;
        lock.releaseLock();
        return qcs;

    }

    /**
     * 指定したIDの時価サービスを追加する。
     *
     * @@param id サービスID。
     * @@param src QuoteDataSourceクラスのインスタンス。
     * @@param handler QuoteEventHandlerクラスのインスタンス。
     */
    void addService(
        String id,
        WEB3QuoteDataSource src,
        WEB3QuoteEventHandler handler)
    {
        lock.getExclusiveLock();
        if (sourceList.containsKey(id))
        {
            throw new IllegalStateException("Specified service id already exists.");
        }

        src.registerHandler(handler);
        sourceList.put(id, src);
        if(src instanceof WEB3QuoteRMMDataSourceImpl)
        {
            rmmQuoteDataSource.add(src);
        }
        else
        {
            tcpQuoteDataSource.add(src);
        }
        lock.releaseLock();
    }

    /**
     * 指定したIDの時価サービスを起動する。
     *
     * @@param id サービスID。
     */
    void startService(String id)
    {
        lock.getSharedLock();
        if (!sourceList.containsKey(id))
        {
            throw new IllegalStateException("Specified service id does not exists.");
        }
        ((WEB3QuoteDataSource) (sourceList.get(id))).start();
        lock.releaseLock();
    }

    /**
     * 登録された全ての時価サービスを起動する。
     *
     */
    void startAllServices()
    {
        lock.getSharedLock();
        Set entrySet = sourceList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            ((WEB3QuoteDataSource) entry.getValue()).start();
        }
        lock.releaseLock();
    }

    /**
     * 指定したIDの時価サービスを停止する。
     *
     * @@param id サービスID。
     */
    void stopService(String id)
    {
        lock.getSharedLock();
        if (!sourceList.containsKey(id))
        {
            throw new IllegalStateException("Specified service id does not exists.");
        }
        ((WEB3QuoteDataSource) (sourceList.get(id))).stop();
        lock.releaseLock();
    }

    /**
     * 登録された全ての時価サービスを停止する。
     *
     */
    void stopAllServices()
    {
        lock.getSharedLock();
        Set entrySet = sourceList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            ((WEB3QuoteDataSource) entry.getValue()).stop();
        }
        lock.releaseLock();
    }
    
    /**
     * キャッシュされている時価情報をファ@イルに出力する。
     */
    void dump()
    {
        lock.getSharedLock();
        WEB3QuoteCacheStoreImpl impl = 
            (WEB3QuoteCacheStoreImpl) cacheStore;
        impl.dump();
        lock.releaseLock();
    }
    
    /**
     * RMMデータソース一覧を取得する。
     */
    List getRMMDsList()
    {
        List l_rmmDsList = null;
        lock.getSharedLock();
        l_rmmDsList = Collections.unmodifiableList(this.rmmQuoteDataSource);
        lock.releaseLock();
        return l_rmmDsList;
    }
    
    /**
     * TCPデータソース一覧を取得する。
     */
    List getTCPDsList()
    {
        List l_tcpDsList = null;
        lock.getSharedLock();
        l_tcpDsList = Collections.unmodifiableList(this.tcpQuoteDataSource);
        lock.releaseLock();
        return l_tcpDsList;
    }

    /**
     * ロック管理ユーティリティクラス<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    private static class LockManager
    {

        private int state;
        private int prefered;
        private final Object mutex = new Object();

        /**
         * 共有ロック
         */
        void getSharedLock()
        {
            synchronized (mutex)
            {
                while (state < 0 || prefered > 0)
                {
                    try
                    {
                        mutex.wait();
                    } catch (InterruptedException ie)
                    {
                        log.warn("Interrupted while waiting for lock.", ie);
                    }
                }
                state++;
            }
        }

        /**
         * 排他ロック
         */
        void getExclusiveLock()
        {
            synchronized (mutex)
            {
                prefered++;
                while (state != 0)
                {
                    try
                    {
                        mutex.wait();
                    } catch (InterruptedException ie)
                    {
                        log.warn("Interrupted while waiting for lock.", ie);
                    }
                }
                prefered--;
                state = -1;
            }
        }

        /**
         * ロック解除
         */
        void releaseLock()
        {
            synchronized (mutex)
            {
                if (state == 0)
                {
                    return;
                }
                if (state > 0)
                {
                    state--;
                } else if (state == -1)
                {
                    state = 0;
                } else
                {
                    throw new RuntimeSystemException(
                        "Illegal state value: " + state);
                }
                mutex.notifyAll();
            }
        }

    }

}
@
