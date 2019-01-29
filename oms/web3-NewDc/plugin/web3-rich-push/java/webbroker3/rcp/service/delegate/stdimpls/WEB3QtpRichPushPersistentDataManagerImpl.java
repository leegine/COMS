head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushPersistentDataManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@QTPリッチクライアントデータプッシュデータベースアクセス管理サービス実装クラス(WEB3QtpRichPushPersistentDataManagerImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 孫(FLJ) 新規作成
                  : 2009/06/02 毛(FTL) 岩井対応
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelParams;
import webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptParams;
import webbroker3.rcp.data.qtp.QtpRichPushEquityContParams;
import webbroker3.rcp.data.qtp.QtpRichPushEquityLapseParams;
import webbroker3.rcp.data.qtp.QtpRichPushHistoryTopRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelParams;
import webbroker3.rcp.data.qtp.QtpRichPushIfoContParams;
import webbroker3.rcp.data.qtp.QtpRichPushIfoLapseParams;
import webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptParams;
import webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptParams;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager;
import webbroker3.util.WEB3LogUtility;

/**
 * （QTPリッチクライアントデータプッシュデータベースアクセス管理サービス実装クラス）。
 * @@author 孫
 * @@version 1.0
 */
public class WEB3QtpRichPushPersistentDataManagerImpl
    implements WEB3QtpRichPushPersistentDataManager
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushPersistentDataManagerImpl.class);

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpEquityOrderAcceptRichPushData(long, long)
     */
    public List getQtpEquityOrderAcceptRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "getQtpEquityOrderAcceptRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1. QTP株式注文受付リッチクライアントプッシュデータ読み込み
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            QtpRichPushEqOrderacceptParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. 取得したプッシュデータのレコードを返す
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("QTP株式注文受付リッチクライアントプッシュデータ存在しません.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpSwapOrderAcceptRichPushData(long, long)
     */
    public List getQtpSwapOrderAcceptRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "getQtpSwapOrderAcceptRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1. QTP株式現引現渡受付リッチクライアントプッシュデータ読み込み
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            QtpRichPushSwOrderacceptParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. 取得したプッシュデータのレコードを返す
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("QTP株式現引現渡受付リッチクライアントプッシュデータ存在しません.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpEquityChangeCancelRichPushData(long, long)
     */
    public List getQtpEquityChangeCancelRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "getQtpEquityChangeCancelRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1. QTP株式訂正取消通知リッチクライアントプッシュデータ読み込み
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            QtpRichPushEqChangecancelParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. 取得したプッシュデータのレコードを返す
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("QTP株式訂正取消通知リッチクライアントプッシュデータ存在しません.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpEquityContRichPushData(long, long)
     */
    public List getQtpEquityContRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "getQtpEquityContRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1. QTP株式出来通知リッチクライアントプッシュデータ読み込み
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy = null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
                QtpRichPushEquityContParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. 取得したプッシュデータのレコードを返す
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("QTP株式出来通知リッチクライアントプッシュデータ存在しません.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpEquityLapseRichPushData(long, long)
     */
    public List getQtpEquityLapseRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "getQtpEquityLapseRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.QTP株式失効通知リッチクライアントプッシュデータ読み込み
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            QtpRichPushEquityLapseParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. 取得したプッシュデータのレコードを返す
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("QTP株式失効通知リッチクライアントプッシュデータ存在しません.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpIfoOrderAcceptRichPushData(long, long)
     */
    public List getQtpIfoOrderAcceptRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "getQtpIfoOrderAcceptRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.QTP先物OP注文受付通知リッチクライアントプッシュデータ読み込み
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            QtpRichPushIfoOrderacceptParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. 取得したプッシュデータのレコードを返す
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("QTP先物OP注文受付通知リッチクライアントプッシュデータ存在しません.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpIfoChangeCancelRichPushData(long, long)
     */
    public List getQtpIfoChangeCancelRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "getQtpIfoChangeCancelRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.QTP先物OP訂正取消通知リッチクライアントプッシュデータ読み込み
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            QtpRichPushIfoChangecancelParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. 取得したプッシュデータのレコードを返す
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("QTP先物OP訂正取消通知リッチクライアントプッシュデータ存在しません.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpIfoContRichPushData(long, long)
     */
    public List getQtpIfoContRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "getQtpIfoContRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.QTP先物OP出来通知リッチクライアントプッシュデータ読み込み
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            QtpRichPushIfoContParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. 取得したプッシュデータのレコードを返す
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("QTP先物OP出来通知リッチクライアントプッシュデータ存在しません.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpIfoLapseRichPushData(long, long)
     */
    public List getQtpIfoLapseRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "getQtpIfoLapseRichPushData()";
        log.entering(STR_METHOD_NAME);

        // 1.1.QTP先物OP失効通知リッチクライアントプッシュデータ読み込み
        String l_strWhere = "account_id >= ? and account_id <= ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = new Long(l_lngFromAccountId);
        l_bindVars[1] = new Long(l_lngToAccountId);

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);

        String l_strOrderBy =null;

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            QtpRichPushIfoLapseParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars);

        // 1.2. 取得したプッシュデータのレコードを返す
        int l_intNum = l_lisSearchResult.size();

        if (l_intNum == 0)
        {
            log.debug("QTP先物OP失効通知リッチクライアントプッシュデータ存在しません.");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /* (non-Javadoc)
     * @@see webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager#getQtpRichPushHistoryTop()
     */
    public Map getQtpRichPushHistoryTop()
        throws DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "getQtpRichPushHistoryTop()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
                QtpRichPushHistoryTopRow.TYPE);

        Hashtable l_mapSearchResult = new Hashtable();
        if (l_lisSearchResult != null)
        {
            for (int i = 0; i < l_lisSearchResult.size(); i++)
            {
                QtpRichPushHistoryTopRow l_row = (QtpRichPushHistoryTopRow) l_lisSearchResult.
                    get(i);
                l_mapSearchResult.put(l_row.getPrimaryKey(), l_row);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_mapSearchResult;
    }

}
@
