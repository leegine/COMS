head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeDaemonTriggerManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : デーモントリガーマネージャークラス(WEB3DaemonTriggerManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/09 劉(FLJ) 新規作成
 */
package webbroker3.gentrade;


import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (デーモントリガーマネージャークラス。)<BR>
 *<BR>
 * トリガー番号を条件に対して<BR>
 * 検索、挿入、更新などの手続きを実装するクラス<BR>
 *<BR>
 * @@author 劉(FLJ)
 * @@version 1.0
 */
public class WEB3GentradeDaemonTriggerManager
{

    /**
     * (ログユーティリティ0<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeDaemonTriggerManager.class);

    /**
     * (コンストラクタ。)<BR>
     *<BR>
     * @@roseuid 403403E7027E
     */
    public WEB3GentradeDaemonTriggerManager()
    {

    }

    /**
     * (スレッド開始)<BR>
     * ［処理概要］<BR>
     * 指定されたスレッドの処理状態を１：処理中に更新する。 <BR>
     *<BR>
     * １）スレッドNo条件に、処理状態を１：処理中に更新する。 <BR>
     *<BR>
     * 更新対象スレッド情報の更新に失敗した場合<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80003<BR>
     *<BR>
     * @@param l_strThreadNo - (スレッドNo)
     * @@return 更新対象スレッド情報を取得できた場合はtrueを、取得できなかった場合はfalseを返す。
     * @@roseuid 4018A1D101D4
     */
    public boolean startThread(
        long l_lngThreadNo) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "startThread(String)";
        final int UPDATE_SUCCESS = 0; // 更新処理が正常終了した値
        final int UPDATE_FAIL = -1; // 更新対象スレッド情報の更新に失敗した場合の値
        final int NO_ROWS = -2; // 更新対象スレッド情報を取得できなかった場合の値

        log.entering(STR_METHOD_NAME);

        try
        {
            final HashMap l_changes = new HashMap();
            l_changes.put("trigger_status",
                          WEB3DaemonTriggerStatusDef.THREAD_PROCESSING);
            l_changes.put("trigger_date",
                          GtlUtils.getSystemTimestamp());

            final String l_strWhere =
                "thread_no = ? and " +
                "trigger_status = ? ";
            final String l_strCondition = "for update";

            final Object l_bindVars[] =
                {
                new Long(l_lngThreadNo),
                WEB3DaemonTriggerStatusDef.THREAD_API_CALL
            };

            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW,
                                               new TransactionCallback()
            {
                public Object process() throws DataNetworkException,
                    DataQueryException,
                    DataCallbackException
                {
                    Integer l_intResult = null;
                    List l_lisRows = l_queryProcesser.doFindAllQuery(
                        DaemonTriggerRow.TYPE,
                        l_strWhere, l_strCondition, l_bindVars);
                    if (l_lisRows.size() > 0)
                    {
                        DaemonTriggerRow l_row =
                            (DaemonTriggerRow) l_lisRows.get(0);
                        WEB3DataAccessUtility.updateRow(l_row, l_changes);
                        l_intResult = new Integer(UPDATE_SUCCESS);
                    }
                    else
                    {
                        l_intResult = new Integer(NO_ROWS);
                    }
                    return l_intResult;
                }
            }
            );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );
            }
            else if (l_intResult.intValue() == NO_ROWS)
            {
                return false;
            }
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(), de);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (スレッド開放)<BR>
     * ［処理概要］<BR>
     * １）引数で指定されたスレッドNoを条件にして対象レコードの処理状態を<BR>
     * 0（：未稼動）する。<BR>
     *<BR>
     * 更新対象スレッド情報を取得できなかった場合<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80003<BR>
     *<BR>
     * @@param l_strThreadNo - (スレッドNo)
     * @@roseuid 4018A1D60261
     */
    public void releaseThread(long l_lngThreadNo) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "releaseThread(String)";
        final int UPDATE_SUCCESS = 0; // 更新処理が正常終了した値
        final int UPDATE_FAIL = -1; // 更新対象スレッド情報を取得できなかった場合の値

        log.entering(STR_METHOD_NAME);

        try
        {
            final HashMap l_changes = new HashMap();
            l_changes.put("trigger_status",
                          WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            l_changes.put("trigger_date",
                          GtlUtils.getSystemTimestamp());

            final String l_strWhere = " thread_no = ? ";
            final String l_strCondition = "for update";
            final Object l_bindVars[] =
                {
                new Long(l_lngThreadNo)
            };

            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW,
                                               new TransactionCallback()
            {
                public Object process() throws DataNetworkException,
                    DataQueryException,
                    DataCallbackException
                {
                    Integer l_intResult = null;
                    List l_lisRows = l_queryProcesser.doFindAllQuery(
                        DaemonTriggerRow.TYPE,
                        l_strWhere, l_strCondition, l_bindVars);
                    if (l_lisRows.size() != 0)
                    {
                        DaemonTriggerRow l_row =
                            (DaemonTriggerRow) l_lisRows.get(0);
                        WEB3DataAccessUtility.updateRow(l_row, l_changes);
                        l_intResult = new Integer(UPDATE_SUCCESS);
                    }
                    else
                    {
                        l_intResult = new Integer(UPDATE_FAIL);
                    }
                    return l_intResult;
                }
            }
            );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );
            }
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(), de);
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
