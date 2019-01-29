head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3BaseSleRepliesCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3BaseSleRepliesCallbackクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/18 孫(FLJ) 実装作成
                    2006/05/2  李(FLJ) 新規作成
 */
package webbroker3.slegateway.callback;
import com.fitechlabs.xbconnector.glbase.gldata.*;

import webbroker3.slegateway.define.WEB3SleCallbackAcceptTypeDef;
import webbroker3.slegateway.define.WEB3SleCallbackOrderExecRouteDivDef;
import webbroker3.slegateway.define.WEB3SleCallbackConstantsDef;
import webbroker3.slegateway.define.WEB3SleCallbackGLRejectTypeDef;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Category;

/**
 * SLE受信コールバック処理の基本機@能APIを提供するクラス
 * 
 * @@author      孫（FLJ）
 * @@version     V1.0  
 */
public class WEB3BaseSleRepliesCallback{
    
    /**
     * ログ出力オブジェクト
     */
    private static final Category m_log =  Category.getInstance( WEB3BaseSleRepliesCallback.class);


    /**外国株銘柄タイプ*/
    public static Integer PRODUCT_TYPE = new Integer(4);
    
    /**処理内容：デフォルト処理*/
    public static final int OP_DEFAULT = 0;

    /**処理内容：注文受付済み処理*/
    public static final int OP_ORDER_ACCEPTED = 1;

    /**処理内容：訂正済み処理*/
    public static final int OP_CHANGE_ACCEPTED = 2;

    /**処理内容：市場状態更新処理*/
    public static final int OP_STATUS_CHANGE = 3;  
    
    public static final String ERROR_SENDQ_NOT_EXSIT = "市場レスポンスに対応する上りキューの注文メッセージが存在しませんでした。";
    
    /**RCVD_Q'処理区分':処理待ち状態をあらわします */
    public static final Integer STATUS_TODO = new Integer(0);

    /**RCVD_Q'処理区分':処理済をあらわします。 */
    public static final Integer STATUS_PROCESSED = new Integer(1);
    
    /**RCVD_Q'処理区分':処理が無視されたことをあらわします。 */
    public static final Integer STATUS_SKIP_PROCESSING_IGNORE = new Integer(8);
    
    /**RCVD_Q'処理区分':エラーのため処理が省略されたことをあらわします。 */
    public static final Integer STATUS_SKIP_PROCESSING_ERROR = new Integer(9);
    
    /**SEND_Q'受信確認フラグ':受信確認フラグ更新を表します。⇒(仕様変更のため2006/10/3追加) */
    public static final Integer SENDQ_CONFIRMED_BY_RCVD = new Integer(1);

    /**SEND_Q'受信確認フラグ':受信確認フラグ未更新を表します。⇒(仕様変更のため2006/10/3追加) */
    public static final Integer SENDQ_NOCONFIRMED_BY_RCVD = new Integer(0);        
    
    /**オフライン状態をあらわします。 */
    public static final Integer MARKET_OFFLINE = new Integer(0);

    /**オンライン状態をあらわします。 */
    public static final Integer MARKET_ONLINE = new Integer(1);
    
    /** リンク復旧をあらわします。 */
    public static final Integer MARKET_LINK_RESTORED   = new Integer(2);

    /** リンク失いをあらわします。 */
    public static final Integer MARKET_LINK_LOST = new Integer(3);
    
    /**
     * 注文IDを取得する。
     * @@param l_glData　@電文オブジェクト
     * @@return 注文ID（取得できない場合、-1を返す）
     */
    public static long getOrderId (GlData l_glData){
        if(l_glData.getBigDecimal("internal_reference") !=null)//電文項目名を訂正 2006/11/29
        {
            return l_glData.getBigDecimal("internal_reference").longValue();
        }
        
        return -1;
    }
    
    /**
     * 対応する注文単位テーブルの注文単位IDを取得する。
     * 
     * @@param l_strKey 検索キー（internal_reference）
     */
    public static Object getOrderUnitId(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, final String l_strKey) throws SQLException
    {
        Object l_result = null;
        final String l_strSql = "select ORDER_UNIT_ID from feq_order_unit where order_id = ?";

        List l_lisParams = new ArrayList(1);
        l_lisParams.add( new BigDecimal(l_strKey));
        //unix環境に発生したタイムアウトエラーを対応するにはBigDecimalへ変換 2006/11/8

        List l_lisQueryResult = new ArrayList(1);
        try
        {
            l_lisQueryResult = l_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute getOrderUnitFieldByInternalReference() :"
                            + e);
            throw e;
        }
        finally
        {
            try
            {
                l_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        if (l_lisQueryResult.size() > 0)
        {
            final Map map = (HashMap) l_lisQueryResult.get(0);
            l_result = map.get("ORDER_UNIT_ID");
        }

        return l_result;
    }
    
    /**
     * 対応する注文単位テーブルの運用コード、証券会社コード、部店コード、識別コードを取得する。
     * 
     * @@param l_strKey 検索キー（internal_reference）
     * @@param l_hmUserData 取得した値を保存するオブジェクト
     */
    public static void getOrderUnitFields(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, final String l_strKey, Map l_hmUserData) throws SQLException
    {
        final String l_strSql = "select a.ORDER_EMP_CODE," +
                                   "a.INSTITUTION_CODE," +
                                   "b.BRANCH_CODE, " +
                                   "a.ORDER_REQUEST_NUMBER " +
                                   "from feq_order_unit a,branch b" +
                                   " where a.order_id = ? and a.branch_id = b.branch_id(+) ";

        List l_lisParams = new ArrayList(1); 
        l_lisParams.add( new BigDecimal(l_strKey));
        //unix環境に発生したタイムアウトエラーを対応するにはBigDecimalへ変換 2006/11/8

        List l_lisQueryResult = new ArrayList(1);
        try
        {
            l_lisQueryResult = l_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute getOrderUnitFieldByInternalReference() :" + e);
            throw e;
        }
        finally
        {
            try
            {
                l_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        if (l_lisQueryResult.size() > 0)
        {
            final Map map = (HashMap) l_lisQueryResult.get(0);
            l_hmUserData.put("order_emp_code", map.get("ORDER_EMP_CODE"));
            l_hmUserData.put("institution_code", map.get("INSTITUTION_CODE"));
            l_hmUserData.put("branch_code", map.get("BRANCH_CODE"));
            l_hmUserData.put("order_request_number", map.get("ORDER_REQUEST_NUMBER"));
        }

    }    
    
    /**
     * 市場レスポンスからの電文項目によって、レスポンスコールバックの処理内容を区分し、
     * ‘受付区分’、‘処理区分’、‘経路区分’を設定する。
     * @@param l_glData SLEレスポンス電文
     * @@param l_hmUserData ‘受付区分’、‘処理区分’、‘経路区分’をこのパラメータに設定する。
     * ↑l_hmUserDataに'受信更新フラグ'のパラメータを追加
     * @@return 処理内容
     */
    public static int getOrderStatusProcByGlData(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, GlData l_glData, Map l_hmUserData)
    {

        final String l_strReplyType = l_glData.getString("replies_type");
//        final String l_strSubStatus = l_glData.getString("sub_status");//いらない変数を削除、2006/11/29
        final BigDecimal l_bdAckCommand = l_glData.getBigDecimal("type_of_acknowledged_command");
        final BigDecimal l_bdRejCommand = l_glData.getBigDecimal("type_of_rejected_command");      
        final BigDecimal l_bdActType = l_glData.getBigDecimal("acknowledgement_type");
        final String l_strEliminationMsg = l_glData.getString("elimination_message");//仕様変更のため追加(2006/10/9)
        final BigDecimal l_bdOpType = (BigDecimal)l_hmUserData.get("op_type");//仕様変更のため追加(2006/10/9)
        
        
        String l_strRouteDiv = null;
        String l_strAcceptDiv = null;
        Integer l_intStatus = STATUS_SKIP_PROCESSING_IGNORE;//'処理区分'初期値について'処理省略(8)'を設定する　@バグ改修 2006/10/5
        Integer l_intRcvdConf = SENDQ_NOCONFIRMED_BY_RCVD;//⇒仕様変更のため、2006/10/3追加
                                                          //初期値に　@受信更新フラグを未更新に設定する
        int l_intResult = 0;//初期値にデフォルト値を設定
        
        //////////////////////////////////////////////////////////
        // ACKレスポンス受信
        //////////////////////////////////////////////////////////
        
        if(WEB3SleCallbackConstantsDef.RepliesType.ACK.equals(l_strReplyType))
        {

            //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
            //                          Ack command    Rejected command    
            //    A           <brank>        0               -                2        a.デフォルト処理   31: 出来ず(失効)    ０：処理待ち    市場に中止
            
            if(l_bdActType!=null && WEB3SleCallbackConstantsDef.AckType.ORDER_ELIMINATED == l_bdActType.intValue())
            {
                if(l_strEliminationMsg != null){ 
                    //失効注文として処理                    
                    l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.NOT_EXECUTED;
                    l_intStatus = STATUS_SKIP_PROCESSING_IGNORE;
                    l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)    
                    l_intResult = OP_DEFAULT;
                }
                else{ 
                    //市場reject注文として処理追加(2006/10/9)
                    l_intStatus = STATUS_TODO;
                    l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                    l_intResult = OP_DEFAULT;
                    if ( WEB3SleCallbackConstantsDef.OrderOpType.NEW_ORDER_OP == l_bdOpType.intValue() ){//オペレータタイプを判断するには,
                                                                                                     //WEB3で管理するオペレータタイプ番号で判断するように
                                                                                                     //⇒2006/11/2改修
                        //新規注文市場拒否
                        l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.ORDER_ACCEPT_ERROR;         
                    }
                    else if (WEB3SleCallbackConstantsDef.OrderOpType.MODIFY_ORDER_OP== l_bdOpType.intValue()){
                        //訂正注文市場拒否
                        l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CHANGE_ERROR;
                    }
                    else{
                        //取消注文市場拒否
                        l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CANCEL_ERROR;
                    }
                    
                }
            }
            else if(l_bdAckCommand!=null && WEB3SleCallbackConstantsDef.AckdCommand.NEW_ORDER_ACK == l_bdAckCommand.intValue())
            {

                //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
                //                          Ack command    Rejected command    
                //    A            <brank>        0               -              4        b.注文受付済み処理  01：注文受付済      ０：処理待ち  市場に承認 
                // 市場新規注文受付済み                         
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.ORDER_ACCEPT_COMPLETE;
                l_intStatus = STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                l_intResult = OP_ORDER_ACCEPTED;                        

            }
            //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
            //                          Ack command    Rejected command    
            //    A          <brank>         1                 -               -         a.デフォルト処理   21：取消済         ０：処理待ち  市場に承認                      
            // 市場注文取消済み
            else if(l_bdAckCommand!=null && WEB3SleCallbackConstantsDef.AckdCommand.CANCEL_ACK == l_bdAckCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CANCEL;
                l_intStatus = STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                l_intResult = OP_DEFAULT;                    
            }
            //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
            //                          Ack command    Rejected command    
            //    A           <brank>         2                -              -          c.訂正済み処理    11：訂正済          ０：処理待ち  市場に承認                      
            // 市場注文訂正済み
            else if(l_bdAckCommand!=null && WEB3SleCallbackConstantsDef.AckdCommand.MODIFY_ACK == l_bdAckCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CHANGED;
                l_intStatus = STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                l_intResult = OP_CHANGE_ACCEPTED;                    
            }            
        }
        
        /////////////////////////////////////////////////////////////////////////
        // MT またはOMS REJECT(現地証券会社OMS拒否)レスポンス受信(FIX電文の場合でも変更無し⇒2006/10/5)
        /////////////////////////////////////////////////////////////////////////
        
        else if(WEB3SleCallbackConstantsDef.RepliesType.EXCHANGE_REJECT.equals(l_strReplyType))
        {
            
            //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
            //                          Ack command    Rejected command    
            //  C           -           -                 0              -         a.デフォルト処理   02：注文受付エラー  ０：処理待ち  OG、市場、またはSLEに拒否      
            // 新規注文をMTに拒否され
            if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_NEW_ORDER == l_bdRejCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.ORDER_ACCEPT_ERROR;
                l_intStatus = STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                l_intResult = OP_DEFAULT;                
            }
            //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
            //                          Ack command    Rejected command    
            //    C         -           -                 1              -         a.デフォルト処理   22：取消エラー    ０：処理待ち  OG、市場、またはSLEに拒否      
            // 取消注文をMTに拒否され
            else if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_CANCEL== l_bdRejCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CANCEL_ERROR;
                l_intStatus =  STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                l_intResult = OP_DEFAULT;           
            }
            //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
            //                          Ack command    Rejected command    
            //    C         -           -                2              -          a.デフォルト処理   12：訂正エラー    ０：処理待ち  OG、市場、またはSLEに拒否      
            // 訂正注文をMTに拒否され
            else if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_MODIFY == l_bdRejCommand.intValue())
            {
                
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CHANGE_ERROR;
                l_intStatus =  STATUS_TODO;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                l_intResult = OP_DEFAULT;           
            }
            
            final String l_strRejectCode  = l_glData.getString("reject_code");
            //広発GF-FIX両系障害による拒否され
            //GF-FIX より'No Channel is available for this message' エラーを返す
            //GL reject_code = 'No Chann'
            if ( l_strRejectCode != null 
                 && (l_strRejectCode.indexOf(WEB3SleCallbackGLRejectTypeDef.GF_FIX_FAILOVER_FAIL) >= 0 )
               )
            {
                l_intStatus = STATUS_SKIP_PROCESSING_ERROR;
            }
            // 広発（OMS）切替最中障害による拒否され
            // GF-FIX より 'Communication fail with CTS Gateway' エラー を返す
            //GL reject_code = ' Communi'
            if ( l_strRejectCode != null
                 && (l_strRejectCode.indexOf(WEB3SleCallbackGLRejectTypeDef.GF_OMS_FAILOVER_FAIL) >= 0 )
               )
            {
                l_intStatus = STATUS_SKIP_PROCESSING_ERROR;
            }            
            // 国泰君安（OMS）NW二重注文発注による拒否され
            // 国泰君安-OMS より '-150906090' エラーコード を返す
            if ( l_strRejectCode != null
                 && (l_strRejectCode.indexOf(WEB3SleCallbackGLRejectTypeDef.GJS_NW_CODE_DUPLI) >= 0 )
               )
            {
                l_intStatus = STATUS_SKIP_PROCESSING_ERROR;
            } 
        }
        
        /////////////////////////////////////////////////////////////////////////
        // SLE REJECTレスポンス受信(FIX電文の場合でも変更無し⇒2006/10/5)
        /////////////////////////////////////////////////////////////////////////
        
        else if(WEB3SleCallbackConstantsDef.RepliesType.GL_REJECT.equals(l_strReplyType))
        {
            String l_strRej = l_glData.getString("reject_code");
            
            //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
            //                          Ack command    Rejected command    
            //  G           -           -                 0              -         a.デフォルト処理   02：注文受付エラー  ０：処理待ち  OG、市場、またはSLEに拒否      
            // 新規注文を　@SLE拒否され
            if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_NEW_ORDER == l_bdRejCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.ORDER_ACCEPT_ERROR;
                l_intStatus = STATUS_SKIP_PROCESSING_ERROR;                
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                l_intResult = OP_DEFAULT;                
            }
            //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
            //                          Ack command    Rejected command    
            //    G         -           -                 1              -         a.デフォルト処理   22：取消エラー    ０：処理待ち  OG、市場、またはSLEに拒否      
            // 取消注文を　@SLEに拒否され
            else if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_CANCEL== l_bdRejCommand.intValue())
            {
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CANCEL_ERROR;
                l_intStatus =  STATUS_SKIP_PROCESSING_ERROR;
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                l_intResult = OP_DEFAULT;           
            }
            //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
            //                          Ack command    Rejected command    
            //    G         -           -                2              -          a.デフォルト処理   12：訂正エラー    ０：処理待ち  OG、市場、またはSLEに拒否      
            // 訂正注文を　@SLEに拒否され
            else if(l_bdRejCommand!=null && WEB3SleCallbackConstantsDef.RejectedCommandType.REJECT_MODIFY == l_bdRejCommand.intValue())
            {
                
                l_strAcceptDiv = WEB3SleCallbackAcceptTypeDef.CHANGE_ERROR;
                if( ( l_strRej.equals(WEB3SleCallbackGLRejectTypeDef.CHANGE_REJECT_AF_ALL_EXEC)) || (l_strRej.equals(WEB3SleCallbackGLRejectTypeDef.CHANGE_REJECT_AF_PART_EXEC)))
                {
                    /**SLE AP：
                       戻りの訂正、取消電文のうちGL(SLE)から戻ってくるエラー電文はSLE AP側でエラー
                       ステータスとせず処理待ちとさせる 2006/12/14
                     **/
                    l_intStatus = STATUS_TODO;
                }
                else
                {
                    l_intStatus = STATUS_SKIP_PROCESSING_ERROR;
                }
                l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
                l_intResult = OP_DEFAULT;           
            }
        }
        
        //reply_type    Sub_status    Type of          Type　@of       Ack_type        処理内容           受付区分           処理区分       説明
        //                          Ack command    Rejected command    
        //    R               -           -                -              -         a.デフォルト処理       NULL           ０：処理待ち  市場に約定 
        //　@約定
        /////////////////////////////////////////////////////////////////////////
        // 約定レスポンス受信(FIX電文の場合でも変更無し⇒2006/10/5)
        /////////////////////////////////////////////////////////////////////////     
        else if(WEB3SleCallbackConstantsDef.RepliesType.TRADE_EXEC.equals(l_strReplyType))
        {
            l_strAcceptDiv = null;
            l_intStatus = STATUS_TODO;
            l_intRcvdConf = SENDQ_NOCONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
            l_intResult = OP_DEFAULT;
        }

        
        //‘経路区分’について、‘受け区分’が設定された場合、”3:注文受付”が設定され；“市場に約定”の場合、”0: 出来通知“が設定され；他の場合、設定されない。
        if(l_strAcceptDiv != null)
        {
            l_strRouteDiv = WEB3SleCallbackOrderExecRouteDivDef.ORDER_ACCEPT;
        }
        else if(WEB3SleCallbackConstantsDef.RepliesType.TRADE_EXEC.equals(l_strReplyType)) 
        {
            l_strRouteDiv = WEB3SleCallbackOrderExecRouteDivDef.EXEC_NOTIFY;
        }
        
        //‘処理区分’について、該当注文の市場・外国株式市場連動区分= ‘0’(MAIL)の場合、全ての市場レスポンスに対し、‘８：(処理省略)’の処理区分が設定される。
//        if(getFeqOrderRequestDiv(l_web3CallBackDataAccessUtil, l_glData.getString("internal_reference"))==0)
        if(getFeqOrderRequestDiv(l_web3CallBackDataAccessUtil,l_hmUserData.get("internal_ref").toString() )==0)//仕様改修対応漏れ 2006/11/10
        {
            l_intStatus = STATUS_SKIP_PROCESSING_IGNORE;
            l_intRcvdConf = SENDQ_CONFIRMED_BY_RCVD;//⇒(2006/10/3追加)
        }
        
        //各種区分値をキーで取得し、Gldataに設定
        l_hmUserData.put("route_div", l_strRouteDiv);
        l_hmUserData.put("accept_div", l_strAcceptDiv);
        l_hmUserData.put("rcvd_confirmed",l_intRcvdConf);//仕様変更のため(追加 2006/10/3)
        l_hmUserData.put("status", l_intStatus);

        return l_intResult;
    }
    
    
    /**
     * 対応する上りキューSEND_Qの注文メッセージからアカウントID、オペレータタイプを取得する。 userDataに保存する。
     * 
     * @@param l_glData 電文オブジェクト
     * @@param l_hmUserData アカウントID、オペレータタイプをこのパラメーターに保存
     *         ↑仕様変更のため、注文IDをパラメータリストに追加(2006/10/3)
     * @@return 対応しているSEND_Qメッセージがあるかどうか、ある場合true
     */
    public static boolean getSendQMessage(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, GlData l_glData, Map l_hmUserData) throws SQLException,RuntimeException    {
        boolean l_blnResult = true;

        final String l_strSql = "select ACCOUNT_ID,OP_TYPE,ORDER_ID from SLE_SEND_Q where queue_id = ?";//仕様変更ため(2006/10/3)変更

        List l_lisParams = new ArrayList(1);
        if ( l_glData.getString("memo") == null){
            throw new RuntimeException("the 'memo' field of response data is null");
        }
        //l_lisParams.add(l_glData.getString("memo"));
        l_lisParams.add(new BigDecimal(l_glData.getString("memo")));
        //unix環境に発生したタイムアウトエラー対応するため、BigDecimal型へ変換する
        List l_lisQueryResult = new ArrayList(0);
        
        try
        {
            m_log.debug(" l_lisParams = " + l_lisParams);
            l_lisQueryResult = l_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute setSendqByQueueId() :" + e);
            throw e;
        }
        finally
        {
            try
            {
                l_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        
        //同じキューIDに対応する上りキューのSEND_Q注文メッセージが存在しない場合、エラーメッセージをログに出力
        if (l_lisQueryResult.size() == 0)
        {
            m_log.error(ERROR_SENDQ_NOT_EXSIT);
            l_blnResult = false;
        }
        else
        {
            final Map map = (HashMap) l_lisQueryResult.get(0);
            l_hmUserData.put("account_id", map.get("ACCOUNT_ID"));
            l_hmUserData.put("op_type", map.get("OP_TYPE"));
            l_hmUserData.put("internal_ref",map.get("ORDER_ID"));//仕様変更ため、追加(2006/10/3)
        }

        return l_blnResult;

    }
    
    /**
     * ある注文IDに対応している市場の外国株式市場連動区分を取得する。
     * @@param l_web3CallBackDataAccessUtil データベースの接続
     * @@param l_strKey 注文ID
     * @@return 外国株式市場連動区分値
     */
    private static int getFeqOrderRequestDiv(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil, final String l_strKey)
    {
        int l_intResult = -1;
        final String l_strSql = "select b.FEQ_ORDER_REQUEST_DIV from feq_order_unit a,market b where a.order_id = ? and a.market_id = b.market_id";

        List l_lisParams = new ArrayList(1);
        //l_lisParams.add(l_strKey);
        l_lisParams.add(new BigDecimal(l_strKey));
        //unix環境に発生したタイムアウトエラーを対応するには、BigDecimalへ変換(2006/11/8)

        List l_lisResult = new ArrayList(1);
        try
        {
            l_lisResult = l_web3CallBackDataAccessUtil.executeQuery(l_strSql, l_lisParams);
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when excute getFeqOrderRequestDiv() :" + e);
        }
        finally
        {
            try
            {
                l_web3CallBackDataAccessUtil.releaseResource(false);
            }
            catch (SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        if (l_lisResult.size() > 0)
        {
            final Map map = (HashMap) l_lisResult.get(0);
            String div = (String)map.get("FEQ_ORDER_REQUEST_DIV");
            if( div != null && !"".equals(div))
            {
                l_intResult = Integer.parseInt(div);                
            }
        }
        return l_intResult;
    }    
    
}
@
