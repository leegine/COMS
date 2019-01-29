head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleSendqProcessorServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleSendqProcessorServiceImplクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 李 新規作成
 Revision History : 2006/05/31 李(FLJ) 通信エラーハンドル処理対応
 Revision History : 2006/06/98 李(FLJ) ソース精査
                    2006/09/04 孫(FLJ) 市場状態に関する処理を削除 
 */
package webbroker3.slegateway.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.slebase.data.SleSendQErrorsParams;
import webbroker3.slebase.data.SleSendQErrorsPK;
import webbroker3.slebase.enums.SleOpenStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.utils.SleConnStatusChangesDbConstants;
import webbroker3.slegateway.WEB3SleConnectorClientFactory;
import webbroker3.slegateway.define.WEB3SleMarketAdapterErrorMessageDef;
import webbroker3.slegateway.WEB3SleMarketAdapterUtils;
import webbroker3.slegateway.WEB3SleProcessors;
import webbroker3.slegateway.service.delegate.WEB3SleSendqProcessorService;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientException;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientInitialException;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientRequestSendStatusUnknownException;

/**
 * 指定したSEND_Q注文メッセージを送信するためのサービスクラス
 */
public class WEB3SleSendqProcessorServiceImpl implements WEB3SleSendqProcessorService
{
    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3SleSendqProcessorServiceImpl.class);//←2006/10/10 訂正
    
    /**
     * デバッグログ出力フラグ
     */
    private static final boolean DBG = m_log.ison();
    
    /**
     * コンストラクタ
     */
    public WEB3SleSendqProcessorServiceImpl(){}

    /**
     * 指定したSEND_Q メッセージをGlSleRequestオブジェクトへ変換し、 SLEコネクタへ転送する
     * @@param l_row
     *          SEND_Q メッセージ
     * @@param l_wsp
     *          送信処理中で使用するDBプロセッサインスタンス
     * @@param l_connectorfactory
     *          送信処理中で使用するSLEコネクタインスタンス
     * @@throws DataException
     */
    public boolean processRow(Row l_row,final WEB3SleProcessors l_wsp, final WEB3SleConnectorClientFactory l_connectorfactory) throws DataException
    {

        m_log.entering("processRow(Row) ");
        final QueryProcessor l_qp = l_wsp.getDefaultProcessor();

        final SleSendQRow l_sleSendqRow = (SleSendQRow) l_row;
        
        //処理区分をチェック 　@0:処理待ち or 7:未送信 以外の処理区分なら、処理をスキップして、returnする
        if ( (! SleSendqProcStatusEnum.TODO.equals(l_sleSendqRow.getStatus())) && (  ! SleSendqProcStatusEnum.NOT_PROCESSED.equals(l_sleSendqRow.getStatus()) )  )
        {
            final String warnMsg = "there been invalid status for sending sle_send_q message";
            m_log.warn(warnMsg);
            return false;
        }
        
        //市場受付可能な時間帯チェック
        if (WEB3SleMarketAdapterUtils.isMarketScheduleOnUsed()) //⇒市場送信スケジュール有効フラグをチェック
        {
            final boolean l_bIsMarketOnline = WEB3SleMarketAdapterUtils.isOk2SendOrdersOnAvailableMarketTimeZone();
            if ( !l_bIsMarketOnline  ) 
            {
                final String errMsg = WEB3SleMarketAdapterErrorMessageDef.MARKET_STATUS_NOVALID_PERIOD;
                m_log.error(errMsg);
                
                if ( l_sleSendqRow.getStatus().equals(SleSendqProcStatusEnum.TODO))
                {  
                    try
                    {
                        final QueryProcessor l_qp2 = l_wsp.getDefaultProcessor();
        
                
                        //単独トランザクションでSEND_Qの処理区分を強制で設定する
                        l_qp2.doTransaction(
                                QueryProcessor.TX_CREATE_NEW,
                                new TransactionCallback()
                        {
                            final SleSendQRow ll_sleSendqRow = l_sleSendqRow;
                            public Object process()
                                throws
                                    DataNetworkException,
                                    DataQueryException,
                                    DataCallbackException {
                                
                                    l_qp2.doFindByPrimaryKeyQuery(ll_sleSendqRow.getPrimaryKey(),null,"FOR UPDATE NOWAIT",null);
                                    setToSleSendqUnprocess(l_sleSendqRow,SleSendqProcStatusEnum.NOT_PROCESSED,l_wsp);
                            
                                    return null;
        
                            }
                        });
                    }
                    catch (DataQueryException dqe)
                    {
                
                        m_log.warn("Could not acquire lock on sle_send_q row:" + l_sleSendqRow);
                        m_log.exiting("processRow(Row) ");
                        return false;
                    }
                }
                m_log.exiting("processRow(Row) ");
        
                return false;
            }      
            
        }
       

        /*
         SLEコネクタとSLE直結エンジン間の接続状態が送信可能な状態であるかチェックする。
         ※「SLE接続状態管理テーブル」を調べ、‘SLEエンジンへ接続失い’または‘SLEコネクタ停止’である場合、
         下記のエラーメッセージをログに出力し、SEND_Qの注文メッセージの処理区分ステータス’を‘送信エラーフラグ’に更新すること。
         “SLEコネクタが停止または接続失いのため、送信失敗でした。”
         */
        if (
            (!WEB3SleMarketAdapterUtils.isSleConnectorOK(l_sleSendqRow,SleConnStatusChangesDbConstants.CONN_DIV.ACTIVE_CONN))
            && (!WEB3SleMarketAdapterUtils.isSleConnectorOK(l_sleSendqRow,SleConnStatusChangesDbConstants.CONN_DIV.STANDBY_CONN))
        )   
        {
            final String errMsg =
                WEB3SleMarketAdapterErrorMessageDef.SLE_ADAPTER_STOP;
            m_log.error(errMsg);
            
            if ( l_sleSendqRow.getStatus().equals(SleSendqProcStatusEnum.NOT_PROCESSED)) 
            {
            
                try
                {
                    final QueryProcessor l_qp2 = l_wsp.getDefaultProcessor();
        
                    //単独トランザクションでSEND_Qの処理区分を強制で設定する
                    l_qp2.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            new TransactionCallback()
                    {
                        final SleSendQRow ll_sleSendqRow = l_sleSendqRow;
                        public Object process()
                            throws
                                DataNetworkException,
                                DataQueryException,
                                DataCallbackException {
                                
                                l_qp2.doFindByPrimaryKeyQuery(ll_sleSendqRow.getPrimaryKey(),"status = ?","FOR UPDATE NOWAIT",new Object[]{SleSendqProcStatusEnum.NOT_PROCESSED});
                                setToSleSendqUnprocess(l_sleSendqRow,SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,l_wsp);
                            
                                return null;
        
                        }
                    });
            
                }
                catch (DataQueryException dqe)
                {
                
                    m_log.warn("Could not acquire lock on sle_send_q row:" + l_sleSendqRow);
                    m_log.exiting("processRow(Row) ");
                    return false;
                }
            }      
            else
            {
                setToSleSendqUnprocess(l_sleSendqRow,SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,l_wsp);
            }
            m_log.exiting("processRow(Row) ");
            return false;
        }

        try {
            Boolean procStatus =(
                Boolean) l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW,new TransactionCallback() 
                {
                    public Object process() throws
                        DataNetworkException,
                        DataCallbackException,
                        DataQueryException {

                    SleSendQRow l_rlockedRow;
                    
                    //更新前のSLE_SEND_Qの処理区分を取得
                    SleSendqProcStatusEnum l_preStatus = l_sleSendqRow.getStatus();

                    try {
                
                        final String where = "0 = (select count(*) from sle_send_q where product_code = ? and account_id = ? and market_code = ? and (status = ? or status = ?) and created_timestamp < ?)";
                        //送信時で送信メッセージの発生時刻の順番のとおり送信することを保障するため、
                        //送信対象のsend_qメッセージの発生時刻より以前で発生した未送信のsend_q有無かチェックする必要がある 2006/10/31
                        //final String where = "(status = ? or status = ?)";//'未送信'処理区分の絞り条件を追加 ⇒ 2006/10/20
                        final Object[] bvs = { 
                                                l_sleSendqRow.getProductCode(),//add at 2006/10/31
                                                new Long(l_sleSendqRow.getAccountId()),//add at 2006/10/31
                                                l_sleSendqRow.getMarketCode(),//2007/10/23 深セン市場対応
                                                SleSendqProcStatusEnum.TODO, 
                                                SleSendqProcStatusEnum.NOT_PROCESSED,//added at 2006/10/20
                                                l_sleSendqRow.getCreatedTimestamp(),//added at 2006/10/31
                                                };
                        l_rlockedRow =
                            (SleSendQRow) l_qp.doFindByPrimaryKeyQuery(
                                l_sleSendqRow.getPrimaryKey(),
                                where,
                                null,//lock必要がなし 2007/11/7
                                bvs);

                        if ( SleSendqProcStatusEnum.TODO.equals(l_rlockedRow.getStatus()) || SleSendqProcStatusEnum.NOT_PROCESSED.equals(l_rlockedRow.getStatus()) )//'未送信'処理区分の絞り条件を追加 ⇒ 2006/10/20
                        {
                            ;
                        }
                        else
                        {
                            throw new DataCallbackException("Already processed,hence skip.");
                        }
                    } catch (DataFindException dfe) {
                        if (DBG)
                        {
                            m_log.debug(
                                "Could not acquire lock on sle_send_q row:"
                                    + l_sleSendqRow,dfe);
                        }
                        throw new DataCallbackException("Could not lock the row");
                    } catch (DataQueryException dqe) {
                        if (DBG)
                        {
                            m_log.debug(
                                "Could not acquire lock on sle_send_q row:"
                                    + l_sleSendqRow,dqe);
                        }
                        throw new DataCallbackException("Could not lock the row.");
                    }

                    try 
                    {                        
                        //ステータスを送信前Prepare状態に更新　@APダウン時障害を対応するには処理追加 2007/11/7
                        //コールバックされないよう、独自のトランザクションとされる。
                        try{
                            setToSleSendqUnprocessByTransaction(
                                l_rlockedRow,
                                SleSendqProcStatusEnum.PREPARE_PROCESSED,
                                l_wsp,
                                true);
                        }
                        catch (DataQueryException dce) 
                        {
                             m_log.exiting("processRow(Row) ");                            
                             return Boolean.FALSE;                             
                        }
                           
                        // SLEへ送信
                        WEB3SleRequestSender.send(
                            l_rlockedRow,
                        l_connectorfactory);

                        /*
                          SEND_Qの注文メッセージ処理が成功である場合
                          対応する注文に対し外国株注文単位テーブルの’注文状態’を’発注中’に更新する。
                          DataExceptionエラ-発生する時で、全体処理中止すること
                         */
                        setToSleOrderUnitOrderStatus(l_rlockedRow,l_wsp);

                        //ステータスを送信済みに更新
                        setToSleSendqUnprocess(
                            l_rlockedRow,
                            SleSendqProcStatusEnum.PROCESSED,
                            l_wsp);

                        m_log.exiting("processRow(Row) ");

                        return Boolean.TRUE;
                    }  
                    catch (GlSleConnectorClientRequestSendStatusUnknownException sendStatusUnknownEx)
                    {
                        //SLEコネクタサーバへ送信する時で未明エラーが発生
                        final String errMsg =
                            "Unknown Error while sending to SLE. Will auto-check and retry later";
                        m_log.error(errMsg, sendStatusUnknownEx);
                        
                        
                        //SLE Unknownエラー時、処理区分を0:TODOへ戻り、　@APダウン時障害を対応するには処理追加 2007/11/7
                        setToSleSendqUnprocess(
                            l_rlockedRow,
                            l_preStatus,
                            l_wsp);
                        
                        /*
                         後でORDER_BOOKへ問い合わせ、正しく送信した否か確認する必要があるので
                         キューID情報をsle_send_q_errorsに保持して置く
                        */
                        addToSleSendqErrors(
                            l_rlockedRow,
                            l_wsp);
                        
                        m_log.exiting("processRow(Row)");
                        
                        return Boolean.FALSE;
                    } 
                    catch (GlSleConnectorClientException sleConnEx)
                    {
                        
                        try{
                            //再送
                            retrySendQ(l_rlockedRow,l_connectorfactory,l_wsp);
                            m_log.exiting("processRow(Row) ");
                            return Boolean.TRUE;
                        }
                        catch(GlSleConnectorClientRequestSendStatusUnknownException unknownge)
                        {
                            //SLEコネクタサーバへ送信する時で未明エラーが発生
                            final String errMsg =
                                "Unknown Error while sending to SLE. Will auto-check and retry later";
                            m_log.error(errMsg, unknownge);
                        
                            //SLE Unknownエラー時、処理区分を0:TODOへ戻り、　@APダウン時障害を対応するには処理追加 2007/11/7
                            setToSleSendqUnprocess(
                                l_rlockedRow,
                                l_preStatus,
                                l_wsp);

                            addToSleSendqErrors(
                                l_rlockedRow,
                                l_wsp);
                        
                            m_log.exiting("processRow(Row)");
                        
                            return Boolean.FALSE;       
                        }
                        catch (GlSleConnectorClientInitialException initialConnectEx)
                        {
                            //SLEコネクタへ送信していない状態
                            final String errMsg =
                                "Initial connection with SLE Connector failed. ";
                            m_log.error(errMsg, initialConnectEx);
                            //処理区分ステータス'9'にして、管理画面から再送する
                            setToSleSendqUnprocess(
                                l_rlockedRow,
                                SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,
                                l_wsp);
                            m_log.exiting("processRow(Row) ");
                        
                            return Boolean.FALSE;
                        }
                        catch(GlSleConnectorClientException ge)
                        {
                            //送信前でSLEコネクタクライアント側でのエラー(ex:MFD変換エラー)
                            final String errMsg =
                                "Some kind of client side exception before attempting to send to the SLE Connector. ";
                            m_log.error(errMsg, sleConnEx);
                            //処理区分ステータス'9'にして、管理画面から再送する
                            setToSleSendqUnprocess(
                                l_rlockedRow,
                                SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,
                                l_wsp);
                        
                            m_log.exiting("processRow(Row) ");
                            return Boolean.FALSE;
                        }
                    }
                    catch (Exception ex) {
                        /*
                         他のエラー(DBエラーなど)
                         SLEコネクタへの送信処理を中止し
                         (発注処理なので間違って大量に発注してしまうのは大変危険なので)
                         SEND_Q注文メッセージのステータスを'送信エラーフラグ(9)’にし
                         後で手動で注文メッセージを確認し
                         メールなどで送信する
                         set the circuit breaker (stop sendq threads)
                         Circuit Breaker を実行し
                         全ての実行中のSEND_Qスレッドを停止する
                        */
                        enableSleSendqThreadsCircuitBreaker(l_rlockedRow.getMarketCode(), ex);
                        final String errMSg =
                            "Some kind of exception (for example:DB error) while processing sle_send_q row:we have to interrupte the whole send_q process: "
                                + l_rlockedRow;
                        m_log.error(errMSg, ex);
                        
                        setToSleSendqUnprocess(
                            l_rlockedRow,
                            SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR,
                            l_wsp);
                            
                        m_log.exiting("processRow(Row) ");
                        
                        return Boolean.FALSE;
                    }
                }
            });

            if (Boolean.FALSE.equals(procStatus))
            {
                //SLEコネクタには、エラーまたは異常状態で
                //送信中止し、状態によってその後リトライする。
                m_log.exiting("processRow(Row) ");
                return false;
            }

        }catch(DataCallbackException cde){
            ;
            m_log.error("this is a datacallbackexception happened!");
            
            return false;
        }catch (DataException de){
            /**
             市場へ大量無効注文を発行するのを防止するため
             上位処理へ例外をスローし、上位処理から送信を中止する
             */
            m_log.error(de.getMessage(), de);
            throw new RuntimeException(de.getMessage(), de);
        }catch (Exception e){
            /**
             市場へ大量無効注文を発行するのを防止するため
             上位処理へ例外をスローし、上位処理から送信を中止する
             */
            m_log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }

        m_log.exiting("processRow(Row) ");
        return true;
    }

    /**
     * SEND_Q注文メッセージの処理区分ステータスを更新
     * 
     * @@param sleSendqRow
     *     SEND_Q注文メッセージ
     * @@param status
     *     指定した処理区分ステータス
     * @@param l_wsp
     *     DBプロセッサ
     * @@param b_forceFlag
     *     強制Update実行フラグ
     */
    private void setToSleSendqUnprocess(
        SleSendQRow l_sleSendqRow,
        SleSendqProcStatusEnum l_status,
        WEB3SleProcessors l_wsp)
    {

        final String STR_METHOD_NAME =
            "setToSleSendqUnprocess(SleSendQRow,SleSendqProcStatusEnum,WEB3SleProcessors)";
        m_log.entering(STR_METHOD_NAME);

        try {
            final QueryProcessor l_qp = l_wsp.getDefaultProcessor();
            final Map l_hmChange = new HashMap();
            l_hmChange.put("status", l_status);
            final Timestamp now = GtlUtils.getSystemTimestamp();
            l_hmChange.put("last_updated_timestamp", now);
            //ステータス更新
            l_qp.doUpdateQuery(l_sleSendqRow.getPrimaryKey(), l_hmChange);

        } catch (DataException de) {

            final String msg =
                "Exception while set a sle_send_q message as not processed message:"
                    + l_sleSendqRow.getQueueId();
            m_log.error(msg, de);
            /*
             市場へ大量無効注文を発行するのを防止するため
             送信処理即時中止する
             */
            throw new RuntimeException(de.getMessage(), de);

        }
        m_log.exiting(STR_METHOD_NAME);

    }

    /**
     * SLEへ正しく送信した否か確認対象のSEND_Q注文メッセージのキューIDをsle_send_q_errorsに入れる
     * @@param l_rSendqRow
     *         SLEへ正しく送信した否か確認対象のSEND_Q注文メッセージRowオブジェクト
     */
    private void addToSleSendqErrors(SleSendQRow l_rSendqRow,
                                    final WEB3SleProcessors l_wsp)
    {

        final String STR_METHOD_NAME = "addToSleSendqErrors(SleSendQRow,WEB3SleProcessors)";
        m_log.entering(STR_METHOD_NAME);

        try {
            final QueryProcessor l_qp = l_wsp.getDefaultProcessor();

            final Map l_hmChanges = new HashMap();
            final Timestamp now = GtlUtils.getSystemTimestamp();
            l_hmChanges.put("open_status", SleOpenStatusEnum.OPEN);
            l_hmChanges.put(
                "last_updated_timestamp",
                GtlUtils.getSystemTimestamp());
            //DB仕様変更のため、上りエラーキューに注文IDを追加(2006/9/15 FTL李)
            l_hmChanges.put("order_id",new Long(l_rSendqRow.getOrderId()));
            //アップデータ
            final int l_intUpdateCount =
                l_qp.doUpdateQuery(new SleSendQErrorsPK(l_rSendqRow.getQueueId()), l_hmChanges);

            //既に存在しない場合、インサート
            if (l_intUpdateCount == 0)
            {
                final SleSendQErrorsParams l_errParams =
                    new SleSendQErrorsParams();
                l_errParams.setQueueId(l_rSendqRow.getQueueId());
                //DB仕様変更のため、上りエラーキューに注文IDを追加(2006/9/15 FTL李)
                l_errParams.setOrderId(l_rSendqRow.getOrderId());
                l_errParams.setOpenStatus(SleOpenStatusEnum.OPEN);
                l_errParams.setCreatedTimestamp(now);
                l_errParams.setLastUpdatedTimestamp(now);
                // sle_send_q_errorsにインサート
                l_qp.doInsertQuery(l_errParams);
            }
        }catch (DataException de) {
            final String msg =
                "Exception while adding a row to sle_send_q_errors table with queue_id:"
                    + l_rSendqRow.getQueueId();
            m_log.error(msg, de);
            /*
             市場へ大量無効注文を発行するのを防止するため
             送信処理即時中止する
             */
            throw new RuntimeException(de.getMessage(), de);
        }
        m_log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * トランザクションでSEND_Q注文メッセージの処理区分ステータスを更新
     * 
     * @@param sleSendqRow
     *     SEND_Q注文メッセージ
     * @@param status
     *     指定した処理区分ステータス
     * @@param l_wsp
     *     DBプロセッサインスタンス
     * @@param l_isNewTransaction
     *     トランザクションを新規で作成するか
     * 
     */
    private void setToSleSendqUnprocessByTransaction(
        SleSendQRow l_sleSendqRow,
        SleSendqProcStatusEnum l_status,
        WEB3SleProcessors l_wsp,
        boolean l_isNewTransaction) throws DataQueryException
    {

        final String STR_METHOD_NAME =
            "setToSleSendqUnprocessByTransaction(SleSendQRow,SleSendqProcStatusEnum,WEB3SleProcessors,boolean)";
        m_log.entering(STR_METHOD_NAME);
        
        
        final SleSendqProcStatusEnum  ll_status = l_status;
        final SleSendQRow ll_sleSendqRow = l_sleSendqRow;
        
        try
        {
            final QueryProcessor l_qp = l_wsp.getDefaultProcessor();
        
            if (l_isNewTransaction){    
            
                //単独トランザクションでSEND_Qの処理区分を強制で設定する
                l_qp.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        new TransactionCallback()
                {
                    public Object process()
                        throws
                            DataNetworkException,
                            DataQueryException,
                            DataCallbackException {
                                
                            l_qp.doFindByPrimaryKeyQuery(
                                          ll_sleSendqRow.getPrimaryKey(),
                                          null,
                                          "FOR UPDATE NOWAIT",
                                          null);
                            
                            final Map l_hmChange = new HashMap();
                            l_hmChange.put("status", ll_status);
                            final Timestamp now = GtlUtils.getSystemTimestamp();
                            l_hmChange.put("last_updated_timestamp", now);
                            //ステータス更新
                            l_qp.doUpdateQuery(ll_sleSendqRow.getPrimaryKey(), l_hmChange);             
                            return null;
        
                    }
                });
            } 
            else
            {       
                final Map l_hmChange = new HashMap();
                l_hmChange.put("status", l_status);
                final Timestamp now = GtlUtils.getSystemTimestamp();
                l_hmChange.put("last_updated_timestamp", now);
                //ステータス更新
                l_qp.doUpdateQuery(l_sleSendqRow.getPrimaryKey(), l_hmChange);
        
            }
        
        }
        catch (DataQueryException dqe)
        {
            
            m_log.warn(
            "Could not acquire lock on sle_send_q row:"
                + ll_sleSendqRow);

            throw dqe;
        }        
        catch (DataException de)
        {

            final String msg =
                "Exception while set a sle_send_q message as not processed message:"
                    + l_sleSendqRow.getQueueId();
            m_log.error(msg, de);
            m_log.exiting(STR_METHOD_NAME);
            /*
                市場へ大量無効注文を発行するのを防止するため
                送信処理即時中止する
             */
            throw new RuntimeException(de.getMessage(), de);
            
        }
        
        m_log.exiting(STR_METHOD_NAME);
    }

    /**
     * 送信したSEND_Qの注文メッセージの注文状態を'発注中'に更新する
     * ↑シナリオテスト中で発生した障害を対応するため更新 2006/12/1
     * @@param slesendqRow SEND_Q注文メッセージ
     */
    private void setToSleOrderUnitOrderStatus(SleSendQRow l_slesendqRow,
                                            final WEB3SleProcessors l_wsp)
    {

        final String STR_METHOD_NAME = "setToSleOrderUnitOrderStatus(long,WEB3SleProcessors)";
        m_log.entering(STR_METHOD_NAME);

        //注文単位ID
        final long l_lngOrderUnitId = l_slesendqRow.getOrderUnitId();
        //オペレータタイプ
        final SleSendqOpTypeEnum l_optype = l_slesendqRow.getOpType();
        //処理区分
        final SleSendqProcStatusEnum l_proctype = l_slesendqRow.getStatus();
        //注文単位Row
        FeqOrderUnitRow l_row = null;

        try {
            final QueryProcessor l_qp = l_wsp.getDefaultProcessor();

            //注文単位情報を取得             
            l_row =
                (FeqOrderUnitRow) l_qp.doFindByPrimaryKeyQuery(
                    new FeqOrderUnitPK(l_lngOrderUnitId));
            OrderStatusEnum l_orderstatus = l_row.getOrderStatus();

            m_log.debug("*** 注文単位 オブジェクト **** = " + l_row);
            m_log.debug("*** 注文単位 *** 注文状態 = " + l_orderstatus);
            m_log.debug("*** SEND_Q *** 処理区分 = " + l_proctype);
            m_log.debug("*** SEND_Q *** オペレータタイプ = " + l_optype);


            final Map change = new HashMap();

            //新規注文の場合
            if (l_optype.equals(SleSendqOpTypeEnum.NEW_ORDER))
            {
                if (l_orderstatus.equals(OrderStatusEnum.ACCEPTED))
                {
                    change.put("order_status", OrderStatusEnum.ORDERING);
                    change.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_qp.doUpdateQuery(new FeqOrderUnitPK(l_lngOrderUnitId), change);
                    
                }
                //閉局時間帯の訂正注文 閉局時間帯の訂正注文を新規注文として処理されるよう
                //↑修正 2006/12/26
                else if (l_orderstatus.equals(OrderStatusEnum.MODIFIED))
                {
                    change.put("order_status", OrderStatusEnum.ORDERING);
                    change.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_qp.doUpdateQuery(
                    new FeqOrderUnitPK(l_lngOrderUnitId),
                    change);
                }
            }
            //更新注文の場合
            else if (l_optype.equals(SleSendqOpTypeEnum.CHANGE_ORDER))
            {
                //開局時間帯の更新注文
                if (l_orderstatus.equals(OrderStatusEnum.MODIFY_ACCEPTED))
                {
                    change.put("order_status", OrderStatusEnum.MODIFYING);
                    change.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_qp.doUpdateQuery(
                        new FeqOrderUnitPK(l_lngOrderUnitId),
                        change);
                }
            }
            //取消し注文
            else
            {
                if (l_orderstatus.equals(OrderStatusEnum.CANCEL_ACCEPTED))
                {
                    change.put("order_status", OrderStatusEnum.CANCELLING);
                    change.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_qp.doUpdateQuery(new FeqOrderUnitPK(l_lngOrderUnitId), change);
                }
            }
        }catch (DataException de){
            final String msg =
                "Exception while updatting a row to feq_order_unit table with order_unit_id:"
                    + l_lngOrderUnitId;
            m_log.error(msg, de);
            /*
             市場へ大量無効注文を発行するのを防止するため
             送信処理即時中止する
            */
            throw new RuntimeException(de.getMessage(), de);
        }
        m_log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 1度送信失敗のSEND_Qメッセージを再送する
     * @@param l_slesendqRow 再送するSEND_Qメッセージ
     * @@param l_connectorfactory 再送用SLEコネクタファ@クトリ 
     * @@param l_wsp DBプロセッサ
     */
    
    private void retrySendQ(SleSendQRow l_slesendqRow,final WEB3SleConnectorClientFactory l_connectorfactory,final WEB3SleProcessors l_wsp) throws GlSleConnectorClientException
    {
        final String STR_METHOD_NAME =
            "retrySendQ(SleSendQRow,WEB3SleConnectorClientFactory,WEB3SleProcessors)";
        m_log.entering(STR_METHOD_NAME);
        
        //SLEへ再送信
        WEB3SleRequestSender.send(l_slesendqRow,l_connectorfactory);
        
        //注文ステータス更新
        setToSleOrderUnitOrderStatus(l_slesendqRow,l_wsp);

        //ステータスを送信済みに更新
        setToSleSendqUnprocess(l_slesendqRow,SleSendqProcStatusEnum.PROCESSED,l_wsp);
        
        m_log.exiting(STR_METHOD_NAME);
        
        return;
    }
    
    /**
     * Circuit Breaker を実行し、不明エラーの発生する時の注文送信を停止する。
     * 
     * @@param Circuit Breakerを発生する原因を表す例外
     */
    private void enableSleSendqThreadsCircuitBreaker(final String marketCode, final Throwable reason)
    {
        m_log.entering("enableSleSendqThreadsCircuitBreaker(Throwable)");

        m_log.error(
            "*********** Unexpected situation. Dont know how to handle. Will try to stop the sle_send_q threads as a precautionary measure.Reason:"
                + reason,
            reason);
        WEB3SleMarketAdapterUtils.setSystemPreferencesStatus(marketCode, false);
        
        m_log.exiting("enableSleSendqThreadsCircuitBreaker(Throwable)");
    }

    /* (非 Javadoc)
     * @@see webbroker3.common.service.delegate.WEB3BusinessService#execute(webbroker3.common.message.WEB3GenRequest)
     */
    public WEB3GenResponse execute(WEB3GenRequest arg0) throws WEB3BaseException
    {
        return null;
    }

}@
