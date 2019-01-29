head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3SleRecoveryProcessorManagerServiceImplTransactionCallbackクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/26 孫(FLJ) 新規作成
 */
package webbroker3.slegateway.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.slebase.data.SleConnStatusChangesParams;
import webbroker3.slebase.data.SleConnStatusChangesRow;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleConnectionStatusEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.slebase.utils.SleConnStatusChangesDbConstants;
import webbroker3.slebase.utils.SleConstants;
import webbroker3.slegateway.define.WEB3SleMarketAdapterErrorMessageDef;
import webbroker3.slegateway.WEB3SleOrderBookStatusChecker;
import webbroker3.slegateway.WEB3SleProcessors;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

/**
 * リカバリー一連処理のトランザクションです。
 * 
 * @@author      孫（FLJ）
 * @@version     V1.0  
 */
public class WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback implements TransactionCallback
{
    
    private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback.class);

    private static final boolean  DBG   = m_log.ison();
    
    private WEB3SleProcessors m_wsp;

    /**
     * Thread No <BR>
     */
    private Long m_threadNo;

    /**
     * (From口座ID)
     */
    private long m_fromAccountId;

    /**
     * (To口座ID)
     */
    private long m_toAccountId;
    
    /**
     * 銘柄別 ORDER_BOOK 情報を保存している
     */
    private Map m_productsOrderBookMap = new HashMap();
    
    private PrimaryKey m_sleConn;
    
    /**
     * 銘柄コードに対応する処理フラグ
     */
    private Map m_recoveryCheckDoneProductCodesMap = new HashMap(); 
   
    private static final String METHOD_PROCESS = "process()";
    private static final String METHOD_LOCK = "lockThread(final long)";
    private static final String METHOD_GET_ROWS = "getRows()";
    private static final String METHOD_IS_RECOVERYAVAILABLE = "isRecoveryAvailable (String marketCode)"; 
    private static final String METHOD_UPDATE_SLECONNSTATUS ="updateSleConnStatus(String marketCode)";
    private static final String METHOD_PROCESS_ROW = "processRow(final SleSendQRow sleSendqRow)";
    private static final String METHOD_RECOVERY_SLE_SENDQ_STATUS = "recoverySleSendqStatus (final SleSendQRow sleSendqRow)";

    public WEB3SleRecoveryProcessorManagerServiceImplTransactionCallback(WEB3SleProcessors wsp)
    {
        m_wsp = wsp;
    }
    
    
    /**
     * From口座ID Setter
     * @@param l_lngFromAccountId
     */
    public void setFromAccountId(long l_lngFromAccountId) {
      m_fromAccountId = l_lngFromAccountId;
    }

    /**
     * (To口座ID) Setter
     * @@param l_lngToAccountId
     */
    public void setToAccountId(long l_lngToAccountId) {
      m_toAccountId = l_lngToAccountId;
    }

    /**
     * (ThreadNo) Setter
     * @@param l_lngThreadNo
     */
    public void setThreadNo(Long l_lngThreadNo) {
      m_threadNo = l_lngThreadNo;
    }    
   
    
    /**
     * (process) <BR>
     * <BR>
     * トランザクション処理を実行する。 <BR>
     * 
     * @@return Object
     * @@throws DataNetworkException,
     *             DataQueryException, DataCallbackException
     */
    public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
    {
        m_log.entering(METHOD_PROCESS);

        try
        {
            /*
             * リカバリー処理のトランザクションを開始する前で、処理ロックを取得する。
             * 処理ロックを取得できない場合、下記のエラーメッセージをログに出力する。 ‘Thread No = %d
             * のスレッドに対し、処理スレッド専用ロック取得できないため、処理中止。” ※xTrade
             * のAPサーバ毎に、処理スレッドを識別するためのスレッドNoを指定する。
             */
            if (m_threadNo==null || !lockThread(m_threadNo.longValue()))
            {
                final String l_strMsgErr = WEB3SleMarketAdapterErrorMessageDef.SEND_THREAD_LOCKED_STATUS;
                m_log.error(l_strMsgErr);
                m_log.exiting(METHOD_PROCESS);
                return null;
            }

            /*
             * SLE接続状態管理テーブルから、SLE接続の最新状態を取得し、リカバリー処理が可能であるか判断する。
             * 不可能であれば、リカバリー処理を中止する。
             */
            if (!isRecoveryAvailable(SleConstants.Markets.SEHK.MARKET_CODE))
            {
                final String l_strMsgErr = WEB3SleMarketAdapterErrorMessageDef.SLE_RECOVERY_NOT_AVAILABLE;
                m_log.error(l_strMsgErr);
                updateSleConnStatus();
                m_log.exiting(METHOD_PROCESS);
                return null;
            }

            //SEND_Qに存在した受信未確認かつ送信済みの注文メッセージを取得する
            final List rows = getRows();
            final int size = rows.size();

            //取得した未受信確認かつ送信済みの注文メッセージに対し、１件注文メッセージずつ処理を繰り返す。
            for (int i = 0; i < size; i++)
            {
                processRow((SleSendQRow) rows.get(i));
            }

            //リカバリー処理の最後、SLE接続状態情報の処理区分を‘処理済み(1)’とする。
            updateSleConnStatus();

        }
        catch (DataException e)
        {
            final String msgErr = "Error while processing the rows.";
            m_log.error(msgErr + e);
            throw new DataCallbackException(msgErr+e);
        }

        m_log.exiting(METHOD_PROCESS);
        return null;
    }
    


    /**
     * スレッドロックを取得する <BR>
     * <BR>
     * Thread番号 <BR>
     * 
     * @@param l_lngThreadNo
     *            取得したいスレッド番号
     * @@return boolean ロック取得できるかどうか <BR>
     */
    public boolean lockThread(final long l_lngThreadNo)
    {

        m_log.entering(METHOD_LOCK);

        boolean l_blnResult = false;
        
        try
        {
            String l_strWhere = "thread_no = ? ";
            String l_strCondition = "for update nowait";
            Object[] l_aryBindVars = { new Long(l_lngThreadNo) };
            final QueryProcessor qp = m_wsp.getDefaultProcessor();
            List l_lisQueryResult = qp.doFindAllQuery(DaemonTriggerRow.TYPE, l_strWhere, l_strCondition,l_aryBindVars);
            if (l_lisQueryResult.size() > 0)
            {
                l_blnResult = true;
            }
        }
        catch (DataException e)
        {
            m_log.error("Error while locking the thread."+e, e);
        }

        m_log.exiting(METHOD_LOCK);
        return l_blnResult;
    }
    
    /**
     * SLE接続状態管理テーブルから、SLE接続の最新状態を取得し、リカバリー処理が可能であるか判断する。
     * 
     * @@param l_strMarketCode 市場コード
     * @@return 接続状態は正常かどうか
     */
    protected boolean isRecoveryAvailable(String l_strMarketCode)
    {
        m_log.entering(METHOD_IS_RECOVERYAVAILABLE);

        boolean l_blnResult = true;

        try
        {

            final QueryProcessor qp = m_wsp.getDefaultProcessor();
            /*
             * SLE接続状態管理テーブルに対し、検索処理を行い、‘未処理(0)’のSLE接続状態レコードを取得する。 ◎絞り条件
             * 処理区分(未処理：0) ◎ソーティング順 市場コード、キューID(降順)
             */
            List l_lisQueryResult = qp.doFindAllQuery(SleConnStatusChangesParams.TYPE, "market_code = ?",
                    "market_code,que_id desc", null, new Object[] {l_strMarketCode });

            //検索結果がない場合、リカバリー処理中止
            if (l_lisQueryResult.size() <= 0)
            {
                final String l_strMsgErr = "the status of SLE connection can not be found.";
                m_log.error(l_strMsgErr);
                l_blnResult = false;
            }
            else
            {

                //検索結果から、一番若いSLE接続状態レコードのみ抜き出し
                SleConnStatusChangesRow l_sleConnStatusChangesRow = (SleConnStatusChangesRow) l_lisQueryResult.get(0);
                m_sleConn = l_sleConnStatusChangesRow.getPrimaryKey();
                //取得したSLE接続状態レコードからキューIDが一番若いSLE接続状態レコードのみ抜き出し、
                //SLE接続状態が‘0：SLEエンジンへ接続が失い’ 、‘3：SLEコネクタ停止’である場合、リカバリー処理を不可能として、
                //リカバリー処理を中止する。
                if ((l_sleConnStatusChangesRow.getNewStatus().equals(SleConnectionStatusEnum.CONNECTION_LOSE) || l_sleConnStatusChangesRow.getNewStatus().equals(
                        SleConnectionStatusEnum.CONNECTION_STOP)))
                {
                    final String l_strMsgErr = "SLE connection is losed or stopped.";
                    m_log.error(l_strMsgErr);
                    l_blnResult = false;
                }
            }
        }
        catch (DataException e)
        {
            m_log.error("Error while searching the status of SLE connection." + e, e);
            l_blnResult = false;
        }

        m_log.exiting(METHOD_IS_RECOVERYAVAILABLE);
        return l_blnResult;
    }

    
    /**
     * 送信対象のSEND_Qメッセージを抽出
     * 
     * @@return List 送信対象のSEND_Qメッセージリスト
     * @@throws DataException
     */
    protected List getRows() throws DataException
    {

        m_log.entering(METHOD_GET_ROWS);
        List l_lisResult = new ArrayList();

        try
        {

            // 送信対象を抽出
            final String l_strWhere = "status = ? and confirmed_by_sle_rcvd_q = ? and  account_id >= ? and account_id <= ?";
            final String l_strOrderBy = "queue_id desc";
            final Object[] l_strBindVars = {  SleSendqProcStatusEnum.PROCESSED,
                                          BooleanEnum.FALSE,
                                          Long.valueOf(String.valueOf(m_fromAccountId)),
                                          Long.valueOf(String.valueOf(m_toAccountId))
                                       };

            final QueryProcessor qp = m_wsp.getDefaultProcessor();
            l_lisResult = qp.doFindAllQuery(SleSendQRow.TYPE, l_strWhere, l_strOrderBy, null, l_strBindVars);
        }
        catch (DataException e)
        {
            m_log.error("Error while getting the rows of the send_q."+e, e);
            throw e;
        }

        m_log.exiting(METHOD_GET_ROWS);
        return l_lisResult;
    }
    
    /**
     * 一行目の注文メッセージの市場コード、銘柄コードに対応するORDER_BOOK問い合わせ、本当に送信したかどうかを判断する
     * 
     * @@param l_sleSendqRow
     *            上りメッセージ
     * @@throws DataException
     */
    protected void processRow(final SleSendQRow l_sleSendqRow)
    {
        m_log.entering(METHOD_PROCESS_ROW);
        try
        {
            final QueryProcessor qp = m_wsp.getDefaultProcessor();

            qp.doTransaction(QueryProcessor.TX_CREATE_NEW, new TransactionCallback()
            {

                public Object process() throws DataNetworkException, DataCallbackException, DataQueryException
                {
                    SleSendQRow l_lockedSleSendQRow = (SleSendQRow)qp.doFindByPrimaryKeyQuery(l_sleSendqRow.getPrimaryKey(),"for update nowait");
                    
                    //注文メッセージの銘柄コードに対応する処理フラグをチェックし、‘ON’である場合、以降の処理をスキップする。
                    if (m_recoveryCheckDoneProductCodesMap.get(l_lockedSleSendQRow.getProductCode()) != null)
                    {
                        return null;
                    }

                    //ORDER_BOOK問い合わせ結果を取得する。
                    Map l_hmProduct = (Map) m_productsOrderBookMap.get(l_lockedSleSendQRow.getProductCode());//キャッシュから取得
                    if (l_hmProduct == null)
                    {
                        l_hmProduct = WEB3SleOrderBookStatusChecker.getInstance().getOrderBook(l_sleSendqRow.getMarketCode(), l_lockedSleSendQRow
                                .getProductCode());//キャッシュから取得できない場合、ORDER_BOOK問い合わせへ問い合わせ
                        m_productsOrderBookMap.put(l_lockedSleSendQRow.getProductCode(), l_hmProduct);//ORDER_BOOK取得した結果をキャッシュに保存
                    }

                    //注文メッセージに対応する電文を正しくSLEへ送信した否か判断する。
                    final boolean l_blnIsSended = WEB3SleOrderBookStatusChecker.getInstance().isAlreadySent(l_lockedSleSendQRow, l_hmProduct);
                    if (l_blnIsSended == true)
                    {
                        m_recoveryCheckDoneProductCodesMap.put(l_lockedSleSendQRow.getProductCode(), "ON");//対応する銘柄コードの処理フラグを‘ON’にする。
                    }
                    else
                    {
                       recoverySleSendqStatus(l_lockedSleSendQRow);//リカバリー操作
                    }
                    
                    return null;

                }
            });
        }
        catch (Exception e)
        {
            m_log.error("Error while Processing the row." + l_sleSendqRow, e);
        }

        m_log.exiting(METHOD_PROCESS_ROW);
    }
    
    /**
     * SLEへ正しく送信されなかった注文メッセージに対し処理区分を‘処理待ち(0)’に変更する。 (本当のリカバリープロセス)
     * 
     * @@param l_sleSendqRow
     *            処理する行
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataException
     */
    protected void recoverySleSendqStatus(final SleSendQRow l_sleSendqRow) throws DataQueryException,
            DataNetworkException
    {
        m_log.entering(METHOD_RECOVERY_SLE_SENDQ_STATUS);

        final QueryProcessor qp = m_wsp.getDefaultProcessor();

        //新しい条件を指定
        Map l_hmParam = new HashMap();
        l_hmParam.put("status", SleSendqProcStatusEnum.TODO);
        l_hmParam.put("last_updated_timestamp", new Timestamp(System.currentTimeMillis()));

        //更新する
        qp.doUpdateQuery(l_sleSendqRow.getPrimaryKey(), l_hmParam);

        m_log.exiting(METHOD_RECOVERY_SLE_SENDQ_STATUS);
    }    
    
    
    /**
     * SLE接続状態情報の処理区分を‘処理済み(1)’とする。 isRecoveryAvailableメソッド取得したレコードにたいして。ですから、
     * 必ずisRecoveryAvailableメソッドを実行した後、このメソッドを呼び出してください
     * 
     * @@throws DataException
     */
    private void updateSleConnStatus() throws DataException //本当の宣言
    {
        m_log.entering(METHOD_UPDATE_SLECONNSTATUS);

        try
        {
            if (m_sleConn != null)
            {
                final QueryProcessor qp = m_wsp.getDefaultProcessor();

                //新しい条件を指定
                Map l_hmParam = new HashMap();
                l_hmParam.put("proc_status", new Integer(SleConnStatusChangesDbConstants.PROC_STATUS.PROCESSED));
                l_hmParam.put("last_updated_timestamp", new Timestamp(System.currentTimeMillis()));
                
                //更新する
                qp.doUpdateQuery(m_sleConn, l_hmParam);
            }
        }
        catch (DataException e)
        {
            m_log.error("Error while updating the status of SLE connection." + e, e);
            throw e;
        }

        m_log.exiting(METHOD_UPDATE_SLECONNSTATUS);

    }

}
@
