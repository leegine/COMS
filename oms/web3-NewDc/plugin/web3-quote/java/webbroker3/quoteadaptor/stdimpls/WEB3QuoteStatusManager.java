head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteStatusManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価サーバへの接続状態を管理するためのクラス(WEB3QuoteStatusManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.quoteadaptor.stdimpls.data.QuoteStatusDao;
import webbroker3.quoteadaptor.stdimpls.data.QuoteStatusParams;
import webbroker3.quoteadaptor.stdimpls.data.QuoteStatusRow;
import webbroker3.util.WEB3LogUtility;


/**
 * 時価サーバへの接続状態を管理するためのクラス 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3QuoteStatusManager
{
    
    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3QuoteStatusManager.class);
    
    /**
     * デバックフラグ
     */
    private static final boolean DBG = log.ison();
    
    /**
     * このクラスのインスタンス
     */
    private static final WEB3QuoteStatusManager instance = 
        new WEB3QuoteStatusManager();
    
    /**
     * コンストラクタ
     */
    private WEB3QuoteStatusManager()
    {
    }

    /**
     * WEB3QuoteStatusManagerのインスタンスを取得する。
     * 
     * @@return WEB3QuoteStatusManagerのインスタンス
     */
    public static WEB3QuoteStatusManager getInstance()
    {
        return instance;
    }
    
    /**
     * 現在の接続状況を取得する。<BR>
     * <BR>
     * 接続状況が登録されていない場合は、
     * <code>WEB3QuoteStatusEnum.CLOSED</code>を返す。<BR>
     * 
     * @@return 現在の接続状況
     */
    public QuoteStatus getStatus()
    {
        String hostName = getHostName();
        QuoteStatusRow row = getCurrentStatus(hostName);
        QuoteStatus currentStatus = (row != null) ? row.getStatus()
                : QuoteStatus.CLOSED;

        if (DBG)
        {
            log.debug("Quote Service current status is " + currentStatus);
        }
        
        return currentStatus;
    }
    
    /**
     * 接続状況を指定した接続状況に変更する。<BR>
     * 
     * @@param status 変更後の接続状況
     */
    public void modifyStatus(final QuoteStatus status)
    {
        
        if (status == null)
        {
            throw new IllegalArgumentException("status must be not null.");
        }
        
        final String hostName = getHostName();
        try
        {
            final QueryProcessor qp = Processors.getDefaultProcessor();
            qp.doTransaction(QueryProcessor.TX_CREATE_NEW, new TransactionCallback() {

                public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                {
                    QuoteStatusRow currentStatus = getCurrentStatus(hostName);
                    Timestamp now = GtlUtils.getSystemTimestamp();
                    if (currentStatus != null)
                    {
                        Map changes = new HashMap();
                        changes.put("status", status);
                        changes.put("last_updated_timestamp", now);
                        qp.doUpdateQuery(currentStatus.getPrimaryKey(), changes);
                    } else
                    {
                        QuoteStatusParams newRow = new QuoteStatusParams();
                        newRow.setHostName(hostName);
                        newRow.setStatus(status);
                        newRow.setCreatedTimestamp(now);
                        newRow.setLastUpdatedTimestamp(now);
                        qp.doInsertQuery(newRow);
                    }
                    return null;
                }
                });
        } catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass() + ".modifyStatus(WEB3QuoteStatusEnum)",
                    de);
        }
        
        if (DBG)
        {
            log.debug("Quote Service modified to [" + status + "]");
        }
    }
    
    /**
     * ホスト名を取得する
     * 
     * @@return ホスト名
     */
    private String getHostName()
    {
        try
        {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostName();
        } catch (UnknownHostException uhe)
        {
            log.error(uhe.getMessage(), uhe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    getClass() + ".getHostName()",
                    uhe);
        }
    }
    
    /**
     * 指定したホスト名のQuoteStatusRowを取得する。
     * 
     * @@param hostName ホスト名
     * @@return QuoteStatusRow
     */
    private QuoteStatusRow getCurrentStatus(String hostName)
    {
        try
        {
            return QuoteStatusDao.findRowByHostName(hostName);
        } catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004, 
                    getClass() + ".getStatus()", 
                    dfe);
        } catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass() + ".getStatus()", 
                    de);
        }
    }
    
}
@
