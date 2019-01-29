head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleMarketAdapterUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleMarketAdapterUtilsクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 李 新規作成
 Revision History : 2006/05/31 李(FLJ)　@SLE接続状態チェック機@能対応、CircuiteBrakerフラグ設定機@能対応
 Revision History : 2006/06/08 李(FLJ)　@ソース精査
 */
package webbroker3.slegateway;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.slebase.data.SleConnStatusChangesRow;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleConnectionStatusEnum;
import webbroker3.slebase.utils.SleConstants;
import webbroker3.slegateway.WEB3SleProcessors;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SehkSleRequestPreparer;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SeszSleRequestPreparer;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SeshSleRequestPreparer;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleRequestPreparer;

/**
 * SLE送信処理に関するユーティリティ機@能を提供するクラス
 */
public class WEB3SleMarketAdapterUtils
{

    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility m_log =WEB3LogUtility.getInstance(WEB3SleMarketAdapterUtils.class);
    
    /**
     * DBプロセッサ
     */
    private static WEB3SleProcessors m_wsp = new WEB3SleProcessorsImpl();
    
    /**
     * コンストラクタ
     */
    private WEB3SleMarketAdapterUtils() {
        ;
    }
    
    /**
     * DBプロセッサ設定
     * @@param wsp　@DBプロセッサインスタンス
     */
    public static void setWeb3SleProcessors(WEB3SleProcessors wsp)
    {
        m_wsp = wsp;
    }

    //xBlocks市場コードからGlIDのへマッピング
    private static HashMap m_xblockmarketcode2glid = new HashMap();

    // SEND_Qスレッド中止するか開始するか制御するフラグ
    private static final String SYSTEMPREF_NAME = "GenMultiThreadedRowQProc.sle_send_q";

    static
    {
        //市場の対応するGLIDマッピング(香港市場)
        m_xblockmarketcode2glid.put(
            SleConstants.Markets.SEHK.MARKET_CODE,
            SleConstants.Markets.SEHK.GLID);
            
        //市場の対応するGLIDマッピング(深セン市場)
        m_xblockmarketcode2glid.put(
            SleConstants.Markets.SESZ.MARKET_CODE,
            SleConstants.Markets.SESZ.GLID);            

        //市場の対応するGLIDマッピング(上海市場)
        m_xblockmarketcode2glid.put(
            SleConstants.Markets.SESH.MARKET_CODE,
            SleConstants.Markets.SESH.GLID);
    }
    
    
    //↓シンセン市場対応2007/10/23
    //市場別のWEB3SleRequestPreparerオブジェクトをキャッシュする。
    private static Map instances = Collections.synchronizedMap(new HashMap());
    
    static
    {
        instances.put(SleConstants.Markets.SEHK.MARKET_CODE,WEB3SehkSleRequestPreparer.getInstance());
        instances.put(SleConstants.Markets.SESZ.MARKET_CODE,WEB3SeszSleRequestPreparer.getInstance());
        instances.put(SleConstants.Markets.SESH.MARKET_CODE,WEB3SeshSleRequestPreparer.getInstance());
    }
    
    /**
     * 市場別のSLE送信準備処理オブジェクトを取得する
     * 
     * @@param marketCode　@xtrade市場コード
     * 
     * @@return WEB3SleRequestPreparer 市場別の準備処理オブジェクト
     */
    public static WEB3SleRequestPreparer getSleRequestPreparer(String marketCode)
    {
        return (WEB3SleRequestPreparer)instances.get(marketCode);
    }
    //↑シンセン市場対応2007/10/23
    
    /**
     *  注文送信をする前に
     *  現在時刻は市場の取引時間帯であるかの確認をします。
     * 
     * @@return 市場の取引時間帯であるかと判断された場合はtrue、そうでなければfalse
     *          注意：処理例外が発生する場合、市場の取引時間帯ではないと判断する
     */
    public static boolean isOk2SendOrdersOnAvailableMarketTimeZone()
    {

        m_log.entering("isOk2SendOrders(SleSendQRow)");

        //web3-gentrade のWEB3GentradeTradingTimeManagementで実装
        boolean l_blnIsTradeOpenTimeZone = false;
        try {
            l_blnIsTradeOpenTimeZone =
//                WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                //2006/11/30で発生した障害に対応するため使用APIを変更しました。
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);
        } catch (WEB3BaseException wbe) {
            m_log.error(wbe.getMessage(), wbe);
            m_log.exiting("isOk2SendOrders(SleSendQRow)");
            return false;
        }

        m_log.exiting("isOk2SendOrders(SleSendQRow)");
        return l_blnIsTradeOpenTimeZone;
    }

//    /**
//     * 注文送信をする前に市場状態チェックする
//     * @@param l_sendqRow SEND_Q注文メッセージ
//     * @@return 送信できると判断された場合はtrue、そうでなければfalse
//     * 
//     * '手動スイッチ'を取り消すため、2006/5/23　@改修：李(FLJ)
//     */
//    public static boolean isOk2SendOrdersOnAvailableMarketstatus(SleSendQRow l_sendqRow)
//        throws DataException 
//    {
//        final QueryProcessor l_qp;
//        final Row l_rows;
////        final SleMarketStatusRow l_row;
//        String l_strGlid;
////        SleMarketStatusPK l_statusPK;
//
//        int l_intStatus;
//        //市場状態の取得
//        m_log.entering("isOk2SendOrders_status(SleSendQRow)");
//
//        l_strGlid =
//            (String) m_xblockmarketcode2glid.get(l_sendqRow.getMarketCode());
//        if (l_strGlid == null)
//        {
//            m_log.error(
//                WEB3SleMarketAdapterErrorCatalog
//                    .MARKET_GLID_NOT_EXISTED
//                    .toString());
//            m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//            return false;
//        }
//
//        try {
//            l_qp = m_wsp.getDefaultProcessor();
//            l_statusPK = new SleMarketStatusPK(l_strGlid);
//            l_rows = l_qp.doFindByPrimaryKeyQuery(l_statusPK);
//            l_row = (SleMarketStatusRow) l_rows;
//            l_intStatus = l_row.getStatus().intValue();
//            
//            /*
//             市場ステータス管理テーブルを調べ、
//             市場の最新状態が‘オフライン’
//             あるい’リンク失い‘の場合、
//             エラーメッセ^時をログに出力しfalseを返す
//             */
//            if ((l_intStatus == SleConstants.SLEMARKETSTATUS.STATUS_OFFLINE)
//                || (l_intStatus == SleConstants.SLEMARKETSTATUS.STATUS_NOLINK))
//            {
//                m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//                return false;
//            }
//            else
//            {
//                m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//                return true;
//            }
//
//        } catch (DataFindException dfe) {
//            /*市場ステータス情報を取得できない場合
//              送信できない市場状態と見なされる*/
//            m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//            
//            return false;
//        } catch (DataException ex) {
//            m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//            
//            /*市場へ大量無効注文を発行するのを防止するため
//              上位処理へ例外をそのまましスローし、上位処理から送信を中止する*/
//            throw ex;
//        }
//    }
    /**
     * SLEコネクタとSLE直結エンジン間の接続状態が送信可能な状態であるかチェックする。
     * 
     * @@param l_sendqRow SEND_Q注文メッセージ
     * @@param l_iActiveDiv SLEコネクタサーバであるか表す区分値⇒(2006/9/20でDB変更に従って追加)
     *         0: Active サーバ 1:Standbyサーバ
     * @@return 送信できると判断された場合はtrue、そうでなければfalse
     */
    public static boolean isSleConnectorOK(SleSendQRow l_sendqRow,int l_iActiveDiv)
        throws DataException 
    {
        boolean l_bResult = false;

        m_log.entering("isSleConnectorOK(SleSendQRow)");

        final String l_strWhere = "market_code = ? and sle_conn_div = ?";
        final String l_strOrderBy = "created_timestamp desc";//2007/2/5  last_updated_timestamp⇒created_timestamp へ変更
        final Object[] l_ObindVars = { l_sendqRow.getMarketCode(), new Integer(l_iActiveDiv), };

        try {
            final QueryProcessor qp = m_wsp.getDefaultProcessor();
            final List rows =
                qp.doFindAllQuery(
                    SleConnStatusChangesRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_ObindVars);
            if (rows.size() > 0)
            {
                SleConnStatusChangesRow row =
                    (SleConnStatusChangesRow) (rows.get(0));
                SleConnectionStatusEnum status = row.getNewStatus();
                /* 
                 　@'SLEエンジンへ再接続成功' または'SLEコネクタ正常に起動'の場合、
                   SLE接続状況をOKとする
                 */
                if (status.equals(SleConnectionStatusEnum.START_SUCCESS)
                    || status.equals(
                        SleConnectionStatusEnum.RECONNECTION_SUCCESS))
                {
                    l_bResult = true;
                }
            }
            m_log.exiting("isSleConnectorOK(SleSendQRow)");
        } catch (DataException de) {
            /*
             市場へ大量無効注文を発行するのを防止するため
             上位処理へ例外をそのまましスローし、上位処理から送信を中止する
             */
            m_log.exiting("isSleConnectorOK(SleSendQRow)");
            throw de;
        }
        return l_bResult;
    }

    /**
     * 指定したインデックス番号に対応するObject 要素をクラスタ配列に最後に移動する.
     * 
     * @@param cluster クラスタ配列
     * @@param index インデックス番号
     */
    public static void moveElementToEnd(Object[] l_cluster, int index)
    {

        final Object l_Otmp = l_cluster[index];
        for (int i = index + 1; i < l_cluster.length; i++)
        {
            l_cluster[i - 1] = l_cluster[i];
        }

        l_cluster[l_cluster.length - 1] = l_Otmp;
        return;
    }

    /**
     * SYSTEM_PREFERENCES テーブルのSEND_Q送信スレッド制御フラグを更新する
     *
     * @@param 市場コード
     * @@param SEND_Q送信スレッドを開始するか中止するか制御するフラグ
     */
    public static void setSystemPreferencesStatus(final String l_markeCode, final boolean l_bStatus)
    {

        final String l_systemPrefName = SYSTEMPREF_NAME + "." + l_markeCode;
        
        try {

            final QueryProcessor l_qp = m_wsp.getDefaultProcessor();
            
            //送信処理と別に他の単独トランザクションでCircuiteBrakerフラグを設定する
            l_qp.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    new TransactionCallback()
            {
                public Object process()
                    throws
                        DataNetworkException,
                        DataQueryException,
                        DataCallbackException {

                    l_qp.lockAccount(0, true);

                    final String l_strNewValue = l_bStatus ? "true" : "false";

                    final Map l_hmChanges = new HashMap();
                    l_hmChanges.put("value", l_strNewValue);
                    l_hmChanges.put(
                        "last_updated_timestamp",
                        GtlUtils.getSystemTimestamp());
                    // アップデート
                    final int l_intUpdatedCount =
                        l_qp.doUpdateQuery(
                            new SystemPreferencesPK(l_systemPrefName),
                            l_hmChanges);
                    if (l_intUpdatedCount == 0)
                    {
                        // インサート
                        final SystemPreferencesParams sysPrefParams =
                            new SystemPreferencesParams();
                        sysPrefParams.setName(l_systemPrefName);
                        sysPrefParams.setValue(l_strNewValue);
                        l_qp.doInsertQuery(sysPrefParams);
                    }

                    // return something
                    return null;
                }
            });
        } catch (DataException de) {

            final String msg =
                "Exception Updating system_preferences with name:"
                    + l_systemPrefName
                    + ", with value:"
                    + l_bStatus;
            m_log.error(msg, de);
            //SEND_Q送信スレッドを中止する時、DBエラーが発生する場合、制御フラグ更新失敗で、SEND_Q送信処理が即時中止する
            throw new RuntimeException(msg, de);
        }
    }

    /**
     * SEND_Q送信スレッドの制御フラグを返す.
     *
     * @@param  市場コード
     * @@return 中止状態:trueを返す 開始状態:falseを返す
     */
    public static boolean isStopRequested(final String l_markeCode)
    {

//        final String l_strVal =
//            GtlUtils.getTradingSystem().getPreference(m_systemPrefName);
//        return !"true".equals(l_strVal);

        m_log.entering("isStopRequested");
        
        final String l_strName = SYSTEMPREF_NAME + "." + l_markeCode;
        
        final String l_strValue = GtlUtils.getTradingSystem().getPreference(l_strName);
        if (l_strValue == null)
        {
            final String msg = l_strName
                            + " not defined in the SYSTEM_PREFERENCES with  name:"
                            + l_strName;
            m_log.warn(msg);
            m_log.exiting("isStopRequested");
            return false;
        }
        
        m_log.exiting("isStopRequested()");
        return !"true".equals(l_strValue);
        
        
    }
    
    /**
     * 市場送信スケジュール有効フラグを返す.
     *
     * @@return 市場送信スケジュール有効:trueを返す 市場送信スケジュール無効:falseを返す
     *          デフォルト設定:true(市場送信スケジュール有効)
     */
    public static boolean isMarketScheduleOnUsed()
    {

        m_log.entering("isMarketScheduleOnUsed");
        
        final String l_strName = "sle.market.sendSchedule.used";
        
        final String l_strValue = GtlUtils.getTradingSystem().getPreference(l_strName);
        if (l_strValue == null)
        {
            final String msg =
                        "sle.market.sendSchedule.used not defined in the SYSTEM_PREFERENCES with  name:"
                            + l_strName;
            m_log.warn(msg);
            m_log.exiting("isMarketScheduleOnUsed");
            return true;
        }
        
        m_log.exiting("isMarketScheduleOnUsed");
        return "true".equals(l_strValue);
        
        
    }
    
    /**
     * WEBIII の発注日設定を返す.
     *
     * @@return SYSTEM_PREFERENCESに name=system.bizdate の設定値を返す
     */
    public static String getBizDate()
    {

        m_log.entering("getBizDate");
        
        final String l_strName = "system.bizdate";
        
        final String l_strValue = GtlUtils.getTradingSystem().getPreference(l_strName);
        if (l_strValue == null)
        {
            final String msg =
                        "system.bizdate not defined in the SYSTEM_PREFERENCES.";
            m_log.warn(msg);
            m_log.exiting("isMarketScheduleOnUsed");
            String l_today = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date()).toString() ;
            return l_today;
        }
        
        m_log.exiting("getBizDate");
        return l_strValue;
        
        
    }    

}@
