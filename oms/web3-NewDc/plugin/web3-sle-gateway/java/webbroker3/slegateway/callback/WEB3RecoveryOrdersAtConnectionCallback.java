head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3RecoveryOrdersAtConnectionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3RecoveryOrdersAtConnectionCallback( WEB3RecoveryOrdersAtConnectionCallback.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/1/30 李　@瀚(FLJ) 新規作成
 Revesion History : 2009/6/10 李　@瀚(FLJ) ステータス = 9:送信エラーのSEND_Qメッセージの復旧処理の検索条件には、BizDateの絞り条件を追加
 */

package webbroker3.slegateway.callback;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.sql.SQLException;
import org.apache.log4j.Category;
import com.fitechlabs.xbconnector.glbase.gldata.GlData;
import com.fitechlabs.xbconnector.glbase.admin.objs.ResultIterator;
import com.fitechlabs.xbconnector.sleconnector.impl.WEB3GlConnectorServer;
import webbroker3.slegateway.define.WEB3SleCallbackConstantsDef;


/**
 * SLEコネクションが再リンク時でのSEND_Q注文メッセージリカバリー処理
 * @@author FTL.Lee Han
 */
public class WEB3RecoveryOrdersAtConnectionCallback 
{
    
    /**
     * ログ出力オブジェクト
     */
    private static final Category m_log =  Category.getInstance( WEB3RecoveryOrdersAtConnectionCallback.class);


    
    /**
     * 銘柄別 ORDER_BOOK 情報を保存している
     */
    private Map m_productsOrderBookMap = new HashMap();
    
    /**
     * 銘柄コードに対応する処理フラグ
     */
    private Map m_recoveryCheckDoneProductCodesMap = new HashMap(); 
    
    /**
     * BizDateProvider
     */
    private WEB3CallbackBizDateProviderUtils m_DateProvider;
   
    /**
     * DBアクセサ
     */
    private WEB3CallBackDataAccessUtil m_web3CallBackDataAccessUtil;
    
    /**
     * SYSTEM_PREFERENCES設定を保持するAdaptor
     */
    private WEB3CallbackSystemPreferencesAdaptorUtils m_adaptor;
    
    /**
     * シングルトンインスタンス 
     */
    private static WEB3RecoveryOrdersAtConnectionCallback m_recoverier = null;
    
    /**
     * コンストラクタ
     */
    private WEB3RecoveryOrdersAtConnectionCallback()
    {    
    }
    
    /**
     * コンストラクタ
     * @@param l_web3CallBackDataAccessUtil DBアクセサ
     * @@param l_DateProvider BizDataProvider
     */
    
    private WEB3RecoveryOrdersAtConnectionCallback(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil,WEB3CallbackSystemPreferencesAdaptorUtils l_adaptor,WEB3CallbackBizDateProviderUtils l_DateProvider)
    {
        m_web3CallBackDataAccessUtil = l_web3CallBackDataAccessUtil;
        m_DateProvider = l_DateProvider;
        m_adaptor= l_adaptor;
    }
    
    /**
     * シングルトンインスタンスを取得
     * @@param l_web3CallBackDataAccessUtil DBアクセサ
     * @@param l_DateProvider BizDataProvider
     * @@return シングルトンインスタンスを返す
     */
    public static WEB3RecoveryOrdersAtConnectionCallback getInstance(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil,WEB3CallbackSystemPreferencesAdaptorUtils l_adaptor,WEB3CallbackBizDateProviderUtils l_DateProvider)
    {
        if (m_recoverier == null)
        { 
            m_recoverier = new WEB3RecoveryOrdersAtConnectionCallback(l_web3CallBackDataAccessUtil,l_adaptor,l_DateProvider);
        }
        return m_recoverier;
    }
   
    
    /**
     * SLEコネクションが再リンク時のSEND_Q注文メッセージリカバリーメイン処理
     * @@param l_sleconnQId SLE接続状態管理テーブル
     * @@param s_marketCode SLEコネクタ対応市場コード
     * @@return void
     * @@throws RuntimeException SQL例外発生時で、RuntimeException例外がスローされる
     */
    public void doRecoveryOrdersAtConnectorCallback(final String s_marketCode,final long l_queid)
    {
        m_log.info("||||| Starting orders message recovery processing while SLE connect status changed with ID: " + s_marketCode );
        
        try
        {
//            // 銘柄のORDER_BOOK情報を保持するメモリ領域をクーリアする
//            if ( m_productsOrderBookMap != null)m_productsOrderBookMap.clear();
//            // 銘柄のORDER_BOOK処理フラグを保持するメモリ領域をクーリアする
//            if (m_recoveryCheckDoneProductCodesMap != null)m_recoveryCheckDoneProductCodesMap.clear();
//            Hashメモリをクリアーする処理を呼び元側に移転
            
            m_web3CallBackDataAccessUtil.setAutoCommit(false);
            
            //SEND_Qに存在した受信未確認かつ送信済みの注文メッセージを取得する
            final List l_rows = getRows(s_marketCode);
            final int i_size = l_rows.size();
            if ( i_size == 0)
            {
                //リカバリー対象外の9:処理エラーのSEND_Qメッセージの処理区分を 9⇒0に更新
                int i_updCount = updateSleSendqStatus(s_marketCode);
                m_log.info(i_updCount + "records of send_q messages have been updated status without recovery while reconnection or re-start.");
                m_web3CallBackDataAccessUtil.commit();
                return;
            }

            //取得した未受信確認かつ送信済みの注文メッセージに対し、１件注文メッセージずつ処理を繰り返す。
            for (int i = 0; i < i_size; i++)
            {
                Map map = (HashMap) l_rows.get(i);
                long queue_id = ((BigDecimal)(map.get("QUEUE_ID"))).longValue();
                
                processRow(queue_id);
            }

            //リカバリー処理の最後、SLE接続状態情報の処理区分を‘処理済み(1)’とする。
            updateSleConnStatus(l_queid,s_marketCode);
            
            //リカバリー処理の最後、SEND_Qに存在した全て'9:処理エラー'の注文メッセージ処理区分を　@9⇒0 に変更
            int i_updCount = updateSleSendqStatus(s_marketCode);
            m_log.info(i_updCount + "records of send_q messages have been updated status after the recovery while reconnection or re-start.");
            
            m_web3CallBackDataAccessUtil.commit();
        }
        catch(SQLException e)
        {
            m_log.error("Error while processing recovery of orders at connection callback:" + e);
            
            try
            {
                m_web3CallBackDataAccessUtil.rollback();
            }
            catch (SQLException ex)
            {
                m_log.error("DB Error while doing rollback at method  WEB3RecoveryOrdersAtConnectionCallback#doRecoveryOrdersAtConnectorCallback()");
            }
            
            final String eMsg = "DB Error while processing recovery of orders at connection callback";
            
            throw new RuntimeException(eMsg, e);
        }
        finally{
            try
            {
                m_web3CallBackDataAccessUtil.setAutoCommit(true);
                m_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        

    }
    
    /**
     * 一行目の注文メッセージの市場コード、銘柄コードに対応するORDER_BOOK問い合わせ、本当に送信したかどうかを判断する
     * 
     * @@param l_sleSendqId SEND_Q注文メッセージのキューID
     * @@throws SQLException
     */
    private void processRow(final long l_sleSendqId ) throws SQLException
    {
        m_log.info("processing recovery for order message data" + l_sleSendqId);
        
        String s_Productcode = null;
        String s_Marketcode = null;
        
        final String l_strSql = "select market_code,product_code from sle_send_q where queue_id = ? for update nowait";
    
        List l_lisParams = new ArrayList(1);
        l_lisParams.add( new Long(l_sleSendqId));

        List l_lisQueryResult = new ArrayList(1);
            
        try
        {
            m_web3CallBackDataAccessUtil.setAutoCommit(false);
            
            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
            Map map = (HashMap) l_lisQueryResult.get(0);
            s_Productcode = (String)map.get("PRODUCT_CODE");
            s_Marketcode = (String)map.get("MARKET_CODE"); 
            
            //注文メッセージの銘柄コードに対応する処理フラグをチェックし、‘ON’である場合、以降の処理をスキップする。
            Boolean b_recoveryCheckDone = (Boolean)m_recoveryCheckDoneProductCodesMap.get(s_Productcode);
            if ((b_recoveryCheckDone != null) && ( b_recoveryCheckDone.equals(Boolean.TRUE) ))
            {
                m_web3CallBackDataAccessUtil.commit();
                m_log.info("the order book flag is closed about product_code :" + s_Productcode);
                return;
            }
            //ORDER_BOOK問い合わせ結果を取得する。
            Map l_hmProduct = (Map) m_productsOrderBookMap.get(s_Productcode);//キャッシュから取得
            if (l_hmProduct == null) 
            {
                l_hmProduct = getOrderBook(s_Marketcode, s_Productcode);//キャッシュから取得できない場合、ORDER_BOOK問い合わせへ問い合わせ
                m_productsOrderBookMap.put(s_Productcode, l_hmProduct);//ORDER_BOOK取得した結果をキャッシュに保存
            }
            
            //注文メッセージに対応する電文を正しくSLEへ送信した否か判断する。
            final boolean l_blnIsSended = isAlreadySent(l_sleSendqId, l_hmProduct);
            if (l_blnIsSended == true)
            {
                m_recoveryCheckDoneProductCodesMap.put(s_Productcode, Boolean.TRUE);//対応する銘柄コードの処理フラグを‘ON’にする。
            }
            else
            {
               recoverySleSendqStatus(l_sleSendqId);//リカバリー操作
            }
            
            m_web3CallBackDataAccessUtil.commit();
//            m_web3CallBackDataAccessUtil.setAutoCommit(true);
            return;
            
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute processRow() :"
                             + e);
            
            try
            {
                m_web3CallBackDataAccessUtil.rollback();
            }
            catch (SQLException ex)
            {
                m_log.error("DB Error while doing rollback at method  WEB3RecoveryOrdersAtConnectionCallback#processRow()");
            }
            throw e;
        }
        finally
        {
            try
            {
                m_web3CallBackDataAccessUtil.setAutoCommit(true);
                m_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
    }
    
    /**
     * リカバリー対象のSEND_Qメッセージを抽出(SEND_Qメッセージ生成時間の逆順)
     * @@param s_marketCode 市場コード
     * @@return List 送信対象のSEND_Qメッセージリスト
     * @@throws SQLException
     */
    private List getRows(final String s_marketCode) throws SQLException
    {

        List l_lisResult = null;
        
    
        final String l_strSql = "select queue_id ,created_timestamp from sle_send_q where status = ? and confirmed_by_sle_rcvd_q = ? and market_code = ? and biz_date = ? order by created_timestamp desc";
        
        List l_lisParams = new ArrayList(1);
        l_lisParams.add(new Integer(1));
        l_lisParams.add(new Integer(0));
        l_lisParams.add(s_marketCode);
        l_lisParams.add(m_DateProvider.getBizDate());

        List l_lisQueryResult = new ArrayList(1);
            
        try
        {
            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
            return l_lisQueryResult;
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute getRows() :"
                             + e);
            throw e;
        }
        finally
        {
            try
            {
                m_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
    
    }
    
    /**
     * SLEへ正しく送信されなかった注文メッセージに対し処理区分を‘処理待ち(0)’に変更する。 (本当のリカバリープロセス)
     * @@param l_sleSendqId SEND_QキューID
     * @@throws SQLException
     */
    protected void recoverySleSendqStatus(final long l_sleSendqId) throws SQLException
    {
        
        final String l_strSql = "update sle_send_q set status = ?,last_updated_timestamp = ?  where queue_id =?";
        List l_lisParams = new ArrayList(3);
        l_lisParams.add(new Integer(0));
        l_lisParams.add(new Timestamp(m_DateProvider.getCurrentTimeMillis()));
        l_lisParams.add(new Long(l_sleSendqId));            
            
        m_web3CallBackDataAccessUtil.executeUpdate(l_strSql, l_lisParams);
    
    }

    /**
     * SLE接続状態情報の処理区分を‘処理済み(1)’とする
     * @@param l_sleconnQId SLE接続状態キューID,s_marketCode リカバリー全注文の市場コード
     * 
     * @@throws SQLException
     */
    private void updateSleConnStatus(final long l_sleconnQId,final String s_marketCode) throws SQLException
    {
        
        final String l_strSql = "update sle_conn_status_changes set proc_status = ?,last_updated_timestamp = sysdate  where que_id = ?";
        
        List l_lisParams = new ArrayList(3);
        l_lisParams.add(new Integer(1));
//        l_lisParams.add(new Timestamp(m_DateProvider.getCurrentTimeMillis()));
        l_lisParams.add(new Long(l_sleconnQId));
//        l_lisParams.add(s_marketCode);            
//        l_lisParams.add(new Integer(0));//未処理のSLE接続状態レコードのみ
            
        m_web3CallBackDataAccessUtil.executeUpdate(l_strSql, l_lisParams);
    }
    
    /**
     * SEND_Qに全て処理区分=9:処理エラーの注文メッセージの処理区分を 0:未処理とする
     * @@param s_marketCode SLEコネクタに対応する市場コード
     * @@return 更新レコード件数
     * @@throws SQLException
     */
    private int updateSleSendqStatus(final String s_marketCode) throws SQLException
    {
        
        final String l_strSql = "update sle_send_q set status = ?,last_updated_timestamp = ? where status = ? and market_code = ? and biz_date = ?";
        
        List l_lisParams = new ArrayList(5);

        l_lisParams.add(new Integer(0));
        l_lisParams.add(new Timestamp(m_DateProvider.getCurrentTimeMillis()));
        l_lisParams.add(new Integer(9));
        l_lisParams.add(s_marketCode);
        //他市場対応するには、Update SQLの絞り条件に市場コードを追加
        l_lisParams.add(m_DateProvider.getBizDate());
        //注文誤り発注を避止するため、BizDate検索条件を追加            
        return m_web3CallBackDataAccessUtil.executeUpdate(l_strSql, l_lisParams);
    }
    
    /**
     * SLEコネクタより市場コード、銘柄コードに対応するORDER BOOK問い合わせ結果取得(シリアル化問い合わせを行わないこと)
     * @@param marketCode 市場コード
     * @@param productCode　@銘柄コード
     * @@return　@ORDER BOOK の問い合わせ結果とInternalRef (注文ID)のマッピング対応関係を保持するMap
     * @@throws RuntimeException ORDER BOOK問い合わせ失敗時、RuntimeException例外がスローされる
     */
    private Map getOrderBook(String l_strMarketCode, String l_strProductCode)
        throws RuntimeException
    {


        final long startTime = System.currentTimeMillis();
        m_log.info(
            ":::: start of getOrderBook for marketCode,productCode :"
                + l_strMarketCode
                + ","
                + l_strProductCode
            );
        
        //2004問い合わせ結果を保持するIterator
        
        ResultIterator itr_2004QueryResults = null;    
        //2004 Order Reference問い合わせを行い
        try
        {
            
            // SLE2004電文を生成
            final GlData l_orderConsReqData = new GlData("2004.ORDER");

            m_log.info(
                "Will send 2004 request for marketCode,productCode:"
                    + l_strMarketCode
                    + ","
                    + l_strProductCode);

            l_orderConsReqData.putString("question_type", "2");
            l_orderConsReqData.putString("order_category", "O");
            
            /**銘柄コードを指定*/
            l_orderConsReqData.putString(
                "stock_code",
                l_strProductCode == null ? "" : l_strProductCode);
                
            l_orderConsReqData.putBigDecimal(
                "index",
                new BigDecimal("000000"));
            l_orderConsReqData.putBigDecimal(
                "number_of_requested_replies",
                new BigDecimal("00000"));
            
            m_log.info("Sending Order Book Request to SLE:" + l_orderConsReqData);    
            try {
                itr_2004QueryResults =
                    WEB3GlConnectorServer
                        .getGlConnectorInstance()
                        .sendOrderConsultationRequest(
                        l_orderConsReqData);
            }
            catch(Exception e){
                e.printStackTrace();
                m_log.error(e.getMessage(),e);    
            }

            /** 注文毎でOrder References 問い合わせ結果を保持するMap*/
            final Map l_hmOrderbookEntriesTable = new HashMap();
            
            while (itr_2004QueryResults.hasNext())
            {
                final Object l_result = itr_2004QueryResults.next();
                final GlData l_respData = (GlData) l_result;
            
                m_log.info("Received 2004 Response data from Order Book: " + l_respData );
                    
                final String l_strInternalRef =
                            l_respData.getString("internal_reference");
                if (l_strInternalRef != null)
                {
                    l_hmOrderbookEntriesTable.put(l_strInternalRef, l_respData);
                }
                
            }
            return Collections.unmodifiableMap(l_hmOrderbookEntriesTable);        
        }
//        catch ( Exception ge)
//        {
//            final String errMsg =
//                        "the query request for sle order book failed";
//            m_log.error(errMsg);
//            throw new RuntimeException(errMsg,ge);    
//        }
        catch (Exception e)
        {
            final String errMsg =
                        "the fatal error happened while Querying Order Book";
            m_log.error(errMsg);
            throw new RuntimeException(errMsg,e);    
                
        }

    }
    
    /**
     * SEND_Q注文メッセージが既にSLEに正しく送信されるかチェック
     * @@param l_sleSendqId SEND_Q注文メッセージキューID
     * @@param l_mOrderBook 取得した注文ORDER_BOOK
     * @@return 送信済み:trueを返す　@他:falseを返す。
     */
    private boolean isAlreadySent(long l_sleSendqId, Map l_mOrderBook )
        throws SQLException
    {

        BigDecimal i_OpType = null;
        BigDecimal l_OrderId = null;
        boolean b_result = true;
//        String s_orderstatus_0 = null;
//        String s_orderstatus_1 = null;
        
        final String l_strSql = "select order_id,op_type,created_timestamp from sle_send_q where queue_id = ?";

        List l_lisParams = new ArrayList(1);
        l_lisParams.add( new BigDecimal(l_sleSendqId));

        List l_lisQueryResult = new ArrayList(1);
        
        try
        {
            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
            Map map = (HashMap) l_lisQueryResult.get(0);
//            Object obj_t=map.get("CREATED_TIMESTAMP");
//            m_log.debug(obj_t.getClass());
            i_OpType = (BigDecimal)map.get("OP_TYPE");
            l_OrderId = (BigDecimal)map.get("ORDER_ID");
//            s_orderstatus_0 = (String)map.get("order_status0");
//            s_orderstatus_1 = (String)map.get("order_status1");
            
            final boolean isNewOrder = new BigDecimal(0).equals(i_OpType);

            final String orderIdStr = "" + l_OrderId.toString();

            m_log.info(
                "Checking whether sle_send_row is already sent or not. Row:"
                    + l_sleSendqId);

            final GlData l_gldata = (GlData) l_mOrderBook.get(orderIdStr);

            Boolean l_bRetResult = null;

//            if (isNewOrder)
//            {

            // 送信済み
            l_bRetResult = Boolean.valueOf(
                                 (l_gldata != null) 
                                 && ( (Long.toString(l_sleSendqId)).equals(l_gldata.getString("memo")))
                                 );
//            }
//            else if (l_gldata != null)
//            {
//
//                final String memo = l_gldata.getString("memo");
//
//                final Timestamp createTimestampOfOrderMessageInTheOrderBook = getCreatedTimeByMemo(l_gldata.getString("memo")); 
//                l_bRetResult = 
//                    Boolean.valueOf(createTimestampOfOrderMessageInTheOrderBook.after(t_Created)
//                            || createTimestampOfOrderMessageInTheOrderBook.equals(t_Created));
//            }

//            if (l_gldata != null)
//            {
//                //order book レスポンスありの場合、order book status tableとのマッチした結果より、判断
//                l_bRetResult = Boolean.valueOf(isMatchedWithOrderbookStatusTable(i_OpType.intValue(),s_orderstatus_0,s_orderstatus_1));
//            }
//            else
//            {
//                //Order book レスポンスデータがないの場合
//                l_bRetResult = Boolean.valueOf(false);
//            }

            final boolean l_bFinalReturnValue = Boolean.TRUE.equals(l_bRetResult);
            m_log.info(
                "sle_send_q  already sent:"
                    + l_bFinalReturnValue
                    + " - for row:"
                    + l_sleSendqId);

            return l_bFinalReturnValue;
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute isAlreadySent() :"
                             + e);
            m_log.error("SQL Excution while processing isAlreadySent  for queue_id:" + l_sleSendqId);
                
            throw e;
            
        }
        finally
        {
            try
            {
                m_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
    }
    
//    /**
//     * gl_data.memo電文項目に対応するsend_q注文メッセージのcreated_timestampを返す
//     * @@return 'memo'対応するsend_q注文メッセージのcreated_timestampを返す
//     * @@throws SQLException
//     */
//    private java.sql.Date getCreatedTimeByMemo(String s_Memo) throws SQLException
//    {
//
//
//        
//        final String l_strSql = "select created_timestamp from sle_send_q where queue_id = ?";
//        
//        List l_lisParams = new ArrayList(1);
//        l_lisParams.add( s_Memo);
//
//        List l_lisQueryResult = new ArrayList(1);
//            
//        try
//        {
//            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
//            Map map = (HashMap) l_lisQueryResult.get(0);
//            return (java.sql.Date)map.get("CREATED_TIMESTAMP");
//        }
//        catch (SQLException e)
//        {
//            m_log.debug("SQLException Catched! when excute getCreatedTimeByMemo() :"
//                             + e);
//            throw e;
//        }
//        finally
//        {
//            try
//            {
//                m_web3CallBackDataAccessUtil.releaseResource(false);
//            }
//            catch(SQLException sqle)
//            {
//                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
//            }
//        }
//    }
    
//    /**
//     * gl_data.memo電文項目に対応するsend_q注文メッセージのオペレタータイプ(op_type)を返す
//     * @@return 'memo'対応するsend_q注文メッセージのop_typeを返す
//     */
//    private int getOptypeByMemo(String s_Memo) throws SQLException
//    {
//
//
//        
//        final String l_strSql = "select op_type from sle_send_q where queue_id = ?";
//        
//        List l_lisParams = new ArrayList(1);
//        l_lisParams.add( s_Memo);
//
//        List l_lisQueryResult = new ArrayList(1);
//            
//        try
//        {
//            l_lisQueryResult = m_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
//            Map map = (HashMap) l_lisQueryResult.get(0);
//            return ((BigDecimal)(map.get("op_type"))).intValue();
//        }
//        catch (SQLException e)
//        {
//            m_log.debug("SQLException Catched! when excute getOpTypeTimeByMemo() :"
//                             + e);
//            throw e;
//        }
//        finally
//        {
//            try
//            {
//                m_web3CallBackDataAccessUtil.releaseResource(false);
//            }
//            catch(SQLException sqle)
//            {
//                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
//            }
//        }
//
//    }
    
    /**
     * order book問い合わせ結果のstatus はorder book status table とマッチし、マッチした場合のみtrueを返すように
     * @@param l_optype 注文のオペレータタイプ 0:新規 1:訂正 2:取消し
     *         s_1st_orderstatus order book問い合わせ結果のorder_status0(30)
     *         s_1st_orderstatus order book問い合わせ結果のorder_status1(41)
     * @@return order book status tableとマッチした場合: true 返す マッチしない場合、false
     */
    private boolean isMatchedWithOrderbookStatusTable(final int l_optype,final String s_1st_orderstatus,final String s_2nd_orderstatus)
    {

        //新規注文の場合
        if ( l_optype == WEB3SleCallbackConstantsDef.OrderOpType.NEW_ORDER_OP)
        {
            if ( (s_1st_orderstatus != null) && s_1st_orderstatus.equals("E") && (s_2nd_orderstatus != null ) && s_2nd_orderstatus.equals("000"))return true;
        }
        //訂正注文の場合
        else if( l_optype == WEB3SleCallbackConstantsDef.OrderOpType.MODIFY_ORDER_OP)
        {
            if (  (s_1st_orderstatus != null) && s_1st_orderstatus.equals("N") && (s_2nd_orderstatus != null ) && s_2nd_orderstatus.startsWith("5"))return true;
        }
        //取消し注文の場合
        else if ( l_optype == WEB3SleCallbackConstantsDef.OrderOpType.CANCEL_ORDER_OP)
        {
            if (( s_1st_orderstatus.equals(" ") || (s_1st_orderstatus.length() == 0) || s_1st_orderstatus == null) && (s_2nd_orderstatus != null ) && (s_2nd_orderstatus.startsWith("6")))return true;
        }
            
        return false;
    }
    
    
    /**
     * 銘柄別 ORDER_BOOK 情報を取得する
     */
    public Map getProductsOrderBookMap()
    {
            
        return m_productsOrderBookMap;
    }

    /**
     * 銘柄別 ORDER_BOOK 情報をSet
     */
    public void setProductsOrderBookMap(String s_productCode,Map m_orderbook)
    {        
        m_productsOrderBookMap.put(s_productCode, m_orderbook);
    }

    /**
     * 銘柄別 ORDER_BOOK 情報をreset
     */
    public void reSetProductsOrderBookMap()
    {        
        m_productsOrderBookMap.clear();
    }
      
    /**
     * 銘柄コードに対応する処理フラグを取得
     */
    public Map getRecoveryCheckDoneProductCodesMap()
    { 
        return m_recoveryCheckDoneProductCodesMap;
    }
    
    /**
     * 銘柄コードに対応する処理フラグをSet
     */
    public void setRecoveryCheckDoneProductCodesMap(String s_productCode, Boolean b_on)
    { 
        m_recoveryCheckDoneProductCodesMap.put(s_productCode, b_on);
    }
    
    /**
     * 銘柄コードに対応する処理フラグをreSet
     */
    public void reSetRecoveryCheckDoneProductCodesMap()
    { 
        m_recoveryCheckDoneProductCodesMap.clear();
    }    
    
}

@
