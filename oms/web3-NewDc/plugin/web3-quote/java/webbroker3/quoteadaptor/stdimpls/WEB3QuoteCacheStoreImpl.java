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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteRecordFormat�N���X(WEB3QuoteRecordFormat.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2004/05/19 �R�c�@@��i(FLJ) ������񂪃L���b�V������Ă��Ȃ��ꍇ�A���ݒl��Double.NaN��Ԃ��B
                 : 2009/02/05 ���@@�@@��(FLJ) CSK�������`�F�b�N�̋����Ή��̂��߁A���\�b�h�ǉ�
 */
package webbroker3.quoteadaptor.stdimpls;

import java.util.Hashtable;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

import webbroker3.quoteadaptor.*;
import webbroker3.util.WEB3LogUtility;

/**
 * WEB3QuoteCacheStore��WEB3QuoteEventHandler�̎����N���X�B
 * ��������ɃL���b�V�����ꂽ���������Ǘ����邽�߂̃N���X�B
 *
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3QuoteCacheStoreImpl
    implements WEB3QuoteCacheStore, WEB3QuoteEventHandler
{

    /**
     * ���O�E���[�e�B���e�B
     */
    private static final WEB3LogUtility _log =
        WEB3LogUtility.getInstance(WEB3QuoteCacheStoreImpl.class);

    /**
     * DEBUG�\���t���O
     */
    private static final boolean DBG = _log.ison();

    /**
     * ���b�N�p�I�u�W�F�N�g
     */
    private final Object putLock = new Object();

    /**
     * �����f�[�^���L���b�V������n�b�V���e�[�u��
     */
    private Hashtable hashTable;

    /**
     * �R���X�g���N�^
     * 
     * @@param size �n�b�V���e�[�u���̃f�t�H���g�T�C�Y
     */
    WEB3QuoteCacheStoreImpl(int size)
    {
        hashTable = new Hashtable(size);
    }

    /**
     * �����̎��������擾����B<br>
     * <br>
     * �L���b�V���Ɏ����w�肵�������̎�����񂪑��݂��Ȃ��ꍇ�A
     * �����������擾�����O���I�l�����ݒl�ɃZ�b�g������������
     * �������A�����Ԃ��B
     * 
     * @@param tradedProduct �����������
     * @@param realType ���A���敪
     * @@return �������
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
            // �L���b�V���ɓo�^����Ă���ꍇ
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);

        } else
        {
            // �L���b�V���ɓo�^����Ă��Ȃ��ꍇ
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
     * �w���敨�E�I�v�V�����̎��������擾����B<br>
     * <br>
     * �L���b�V���Ɏ����w�肵�������̎�����񂪑��݂��Ȃ��ꍇ�A
     * �����������擾�����O���I�l�����ݒl�ɃZ�b�g������������
     * �������A�����Ԃ��B
     * 
     * @@param tradedProduct �w���敨�E�I�v�V�����������
     * @@param realType ���A���敪
     * @@return �������
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
            // �L���b�V���ɓo�^����Ă���ꍇ
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);

        } else
        {
            // �L���b�V���ɓo�^����Ă��Ȃ��ꍇ
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
     * �����̎��������擾����B<br>
     * <br>
     * �L���b�V���Ɏ����w�肵�������̎�����񂪑��݂��Ȃ��ꍇ�A
     * null��Ԃ��B
     * 
     * @@param tradedProduct �����������
     * @@param realType ���A���敪
     * @@return �������
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
            // �L���b�V���ɓo�^����Ă���ꍇ
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);
        } else
        {
            // �L���b�V���ɓo�^����Ă��Ȃ��ꍇ
            if (_log.ison())
            {
                _log.debug("No entry in cache. key : " + key);
            }
            return null;
        }

        return quoteData;
    }

    /**
     * �w���敨�E�I�v�V�����̎��������擾����B<br>
     * <br>
     * �L���b�V���Ɏ����w�肵�������̎�����񂪑��݂��Ȃ��ꍇ�A
     * null��Ԃ��B
     * 
     * @@param tradedProduct �w���敨�E�I�v�V�����������
     * @@param realType ���A���敪
     * @@return �������
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
            // �L���b�V���ɓo�^����Ă���ꍇ
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);

        } else
        {
            // �L���b�V���ɓo�^����Ă��Ȃ��ꍇ
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
     * �w���̎��������擾����B<br>
     * <br>
     * �L���b�V���Ɏ����w�肵�������̎�����񂪑��݂��Ȃ��ꍇ�A
     * ��̎������𐶐����A�����Ԃ��B
     * 
     * @@param productCode �w�������R�[�h
     * @@param realType ���A���敪
     * @@return �������
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
            // �L���b�V���ɓo�^����Ă���ꍇ
            if (_log.ison())
            {
                _log.debug("Entry in cache. key : " + key);
            }
            quoteData.setParams(cache);

        } else
        {
            // �L���b�V���ɓo�^����Ă��Ȃ��ꍇ
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
     * �������ʒm�C�x���g���L���b�V���ɒǉ�����B
     * 
     * @@param qe �������ʒm�C�x���g�B
     */
    public void push(WEB3QuoteEvent qe)
    {

        WEB3QuoteEventImpl event = (WEB3QuoteEventImpl) qe;

        synchronized (putLock)
        {

            String key = event.createKey();
            WEB3CacheData old = (WEB3CacheData) hashTable.get(key);

            // �L���b�V������Ă��Ȃ��ꍇ�A�f�[�^��ǉ�
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

                // �L���b�V������Ă���ꍇ�̓f�[�^���X�V
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
     * �L���b�V������Ă��鎞�������t�@@�C���ɏo�͂���B
     */
    void dump()
    {
        synchronized (putLock)
        {
            WEB3QuoteUtil.dump(hashTable);
        }
    }

    // WebBroker3�Ń�������ɃL���b�V������鎞�����
    private static class WEB3CacheData extends AbstractQuoteData
    {

        WEB3CacheData()
        {
            super();
        }

    }

}
@
