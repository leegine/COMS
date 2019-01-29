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
 * WEB3QuoteDataSupplierService�̕W�������N���X�B<br>
 * <BR>
 * WEB3QuoteDataSupplierService�ɒ�`����Ă��鎞���擾�̂��߂̃��\�b�h��
 * �Ǘ��p�̃��\�b�h����`����Ă���B<BR>
 * �Ǘ��p�̃��\�b�h�̓A�v���P�[�V����������͎g�p�ł��Ȃ��悤�ɁA�p�b�P�[�W�X�R�[�v
 * �Œ�`����Ă���B<BR>
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
     * �R���X�g���N�^�B
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
     * ������񂪑��݂��邩�ǂ����`�F�b�N�p�̃��\�b�h<BR>
     * �������Ȃ��ꍇ�Anull��Ԃ�<BR>
     * 
     * @@param tradedProduct ���������擾��������̎�������B
     * @@param realType ���A���敪
     * @@return �������
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
     * ������񂪑��݂��邩�ǂ����`�F�b�N�p�̃��\�b�h<BR>
     * �������Ȃ��ꍇ�Anull��Ԃ�<BR>
     * 
     * @@param tradedProduct ���������擾���銔�������̎�������B
     * @@param realType ���A���敪
     * @@return �������
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
     * ������񂪑��݂��邩�ǂ����`�F�b�N�p�̃��\�b�h<BR>
     * �������Ȃ��ꍇ�Anull��Ԃ�<BR>
     * 
     * @@param tradedProduct ���������擾����敨OP�����̎�������B
     * @@param realType ���A���敪
     * @@return �������
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
     * WEB3QuoteCasheStore�N���X�̃C���X�^���X��o�^����B
     *
     * @@param cs QuoteCasheStore�N���X�̃C���X�^���X�B
     */
    void setCacheStore(WEB3QuoteCacheStore cs)
    {
        lock.getExclusiveLock();
        this.cacheStore = cs;
        lock.releaseLock();
    }

    /**
     * �o�^���ꂽQuoteCasheStore�N���X�̃C���X�^���X���擾����B
     *
     * @@return QuoteCasheStore�N���X�̃C���X�^���X�B
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
     * �w�肵��ID�̎����T�[�r�X��ǉ�����B
     *
     * @@param id �T�[�r�XID�B
     * @@param src QuoteDataSource�N���X�̃C���X�^���X�B
     * @@param handler QuoteEventHandler�N���X�̃C���X�^���X�B
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
     * �w�肵��ID�̎����T�[�r�X���N������B
     *
     * @@param id �T�[�r�XID�B
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
     * �o�^���ꂽ�S�Ă̎����T�[�r�X���N������B
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
     * �w�肵��ID�̎����T�[�r�X���~����B
     *
     * @@param id �T�[�r�XID�B
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
     * �o�^���ꂽ�S�Ă̎����T�[�r�X���~����B
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
     * �L���b�V������Ă��鎞�������t�@@�C���ɏo�͂���B
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
     * RMM�f�[�^�\�[�X�ꗗ���擾����B
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
     * TCP�f�[�^�\�[�X�ꗗ���擾����B
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
     * ���b�N�Ǘ����[�e�B���e�B�N���X<br>
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
         * ���L���b�N
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
         * �r�����b�N
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
         * ���b�N����
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
