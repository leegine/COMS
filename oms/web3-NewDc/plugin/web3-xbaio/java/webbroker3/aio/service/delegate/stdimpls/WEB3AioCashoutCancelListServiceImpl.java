head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消一覧サービスImpl(WEB3AioCashoutCancelListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 黄建 (中訊) 新規作成
                   2004/10/22 周勇(中訊) レビュー
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashoutCancelListResponse;
import webbroker3.aio.message.WEB3AioCashoutCancelUnit;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (出金取消一覧サービスImpl)<BR>
 * 出金取消一覧サービス実装クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutCancelListServiceImpl 
    extends WEB3ClientRequestService implements WEB3AioCashoutCancelListService 
{
    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCancelListServiceImpl.class);
    
    /**
     * 出金取消一覧サービス処理を実施する。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金取消一覧）一覧画面表示データ取得」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F63677016E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１.１）get補助口座(SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);  
        
        //１.２）validate注文(SubAccount)
        //以下のチェックを行う。 
        //－受付時間チェック 
        //－システム停止中チェック 
        //－顧客のチェック（Ｙ客、管理ロック等） 
        //[引数] 
        //補助口座： get補助口座()の戻り値     
                  
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //チェックを行う
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //１.３）get発注日( )
        //発注日を取得する。 
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("発注日を取得する " + l_datOrderBizDate);       
         
        try
        {
            //１.４）getDefaultProcessor( )
            //クエリプロセッサを取得する。
            //===========DataNetworkException======= DataFindException========
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();         
            
            //１.５)doFindAllQuery(RowType, String, String, String, Object[], int, int) 
            //注文単位テーブルから、以下の条件のレコードを取得する。 
            //[条件] 
            //補助口座ID = 補助口座.getSubAccountId()の戻り値 
            //注文種別 = 1001（出金注文） 
            //注文状態 = 1（受付済） 
            //注文有効状態 = 1（オープン） 
            //失効区分 = 1（オープン） 
            //出金申込区分 = null 
            //[引数] 
            //Rowタイプ： AioOrderUnitRow.TYPE 
            //Where： "sub_account_id=? and order_type=? 
            // and order_status=? and order_open_status=? and 
            // expiration_status=? and payment_application_div=null" 
            //orderBy： "received_date_time" 
            //condition： null 
            //リスト： 以下の項目のリスト 
            //補助口座.getSubAccountId()の戻り値 
            //1001（出金注文） 
            //1（受付済） 
            //1（オープン） 
            //1（オープン）
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" sub_account_id = ?");     
            l_strWhere.append(" and order_type = ?");  
            l_strWhere.append(" and order_status = ? ");     
            l_strWhere.append(" and order_open_status = ? ");   
            l_strWhere.append(" and expiration_status = ? ");   
            l_strWhere.append(" and payment_application_div is null ");   
            String l_strOrderBy = " received_date_time "; 
            Object[] l_objMutualFrgncalWhere =
            {
                new Long(l_subAccount.getSubAccountId()),           
                OrderTypeEnum.CASH_OUT,
                OrderStatusEnum.ACCEPTED,
                OrderOpenStatusEnum.OPEN,
                OrderExpirationStatusEnum.OPEN
            };
            List l_lisAioOrderUnitRows = 
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhere.toString(),
                    l_strOrderBy,
                    null,
                    l_objMutualFrgncalWhere);
            int l_intSize = 0;
            if (l_lisAioOrderUnitRows != null && !l_lisAioOrderUnitRows.isEmpty())
            {
                l_intSize = l_lisAioOrderUnitRows.size();
            }
            
            //１.６）ArrayList( )
            //ArrayListのインスタンスを生成する。
            List  l_lisCashoutCancelUnit = new Vector();

            //１.７）メッセージ 注文単位Paramsの要素毎にLoop
            for (int i= 0; i < l_intSize; i++)
            {
                //１.７.１）メッセージ 注文単位Paramsの要素毎にLoop
                //注文単位Params.MQステータス = "0"（未処理）の場合以下の処理を行う
                
                //注文単位Params
                AioOrderUnitRow l_aioOrderUnitRow = 
                       (AioOrderUnitRow) l_lisAioOrderUnitRows.get(i);
                AioOrderUnitParams l_aioOrderUnitParams =  
                    new AioOrderUnitParams(l_aioOrderUnitRow);
                log.debug("MQステータスを取得する " + l_aioOrderUnitParams.getMqStatus());
                
                if ((WEB3MqStatusDef.NOT_SEND_MAIL).equals(l_aioOrderUnitParams.getMqStatus()))
                {
                    //１.７.１.１）出金取消明細インスタンスを生成する。
                    WEB3AioCashoutCancelUnit l_aioCashoutCancelUnit = 
                        new WEB3AioCashoutCancelUnit(); 
                    
                    //１.７.１.２）(*2) プロパティセット
                    //(*2)以下のとおりに、プロパティをセットする。
                    
                    //出金取消明細.注文ID = 注文単位Params.注文ID
                    l_aioCashoutCancelUnit.orderId = 
                        String.valueOf(l_aioOrderUnitParams.getOrderId());
                    
                    //出金取消明細.受付日時 = 注文単位Params.受付日時
                    l_aioCashoutCancelUnit.receptionDate = 
                        l_aioOrderUnitParams.getReceivedDateTime();
                   
                    //出金取消明細.振込予定日 = 注文単位Params.振替予定日
                    l_aioCashoutCancelUnit.transScheduledDate = 
                        l_aioOrderUnitParams.getEstTransferDate();
                    
                    //出金取消明細.出金金額 = 注文単位Params.数量
                    l_aioCashoutCancelUnit.cashoutAmt = 
                        WEB3StringTypeUtility.formatNumber(
                            l_aioOrderUnitParams.getQuantity());
                    
                    //１.７.１.３）ArrayListに要素を追加する。 
                    //[引数] arg0： 出金取消明細オブジェクト
                    l_lisCashoutCancelUnit.add(l_aioCashoutCancelUnit);                     
                }
            }    
                
            //１.８）toArray( )
            //出金取消明細の配列を返却する。
            WEB3AioCashoutCancelUnit[] l_aioCashoutCancelUnit = 
                new WEB3AioCashoutCancelUnit[l_lisCashoutCancelUnit.size()];
            l_lisCashoutCancelUnit.toArray(l_aioCashoutCancelUnit);
                
            //１.９）createResponse( )レスポンスデータを生成する。
            WEB3AioCashoutCancelListResponse 
                l_aioCashoutCancelListResponse =
                    (WEB3AioCashoutCancelListResponse) l_request.createResponse(); 

            //１.１０）(*3) プロパティセット
            //(*3) 以下のとおりに、プロパティをセットする。
            //レスポンス.出金取消一覧 = 出金取消明細[]  
            l_aioCashoutCancelListResponse.cashoutCancelUnits = l_aioCashoutCancelUnit;
            
            log.exiting(STR_METHOD_NAME);
            return l_aioCashoutCancelListResponse;     
        }
        catch (DataException  l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
