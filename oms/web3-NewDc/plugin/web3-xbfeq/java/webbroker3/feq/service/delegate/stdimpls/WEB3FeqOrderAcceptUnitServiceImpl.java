head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付１件サービスImpl(WEB3FeqOrderAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 徐大方 (中訊) 新規作成
                   2006/10/17 徐大方(中訊) モデル　@No.289対応
                   2006/11/20 徐大方(中訊) モデル　@No.299,300対応
                   2006/11/22 徐大方(中訊) モデル　@No.306対応
                   2006/12/14 齊珂(中訊) モデル　@No.311対応
                   2006/12/15 齊珂(中訊) モデル　@No.312対応                      
                   2006/12/19 徐宏偉(中訊) モデル　@No.313,318対応
                   2006/12/19 徐宏偉(中訊) モデル　@No.322対応
                   2007/05/17 沢田（SRA） モデル H00149（No.350）対応
Revesion History : 2008/02/26 馮海濤(中訊) モデル　@No.401-No.409対応
                   2008/07/08 黒釜(SRA) モデル　@No.450対応
*/

package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptUnitService;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.utils.SleGLRejectTypeDef;
import webbroker3.slegateway.define.WEB3SleCallbackConstantsDef.AckdCommand;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文受付１件サービスImpl)<BR>
 * 外国株式注文受付１件サービス実装クラス<BR>
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptUnitServiceImpl implements WEB3FeqOrderAcceptUnitService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptUnitServiceImpl.class);
        
    /**
     * @@roseuid 42D0CED203D8
     */
    public WEB3FeqOrderAcceptUnitServiceImpl() 
    {
     
    }
    
    /**
     * (notify注文受付)<BR>
     * 注文受付１件処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外国株式注文受付サービス）注文受付１件処理」参照。<BR>
     * ========================================================<BR>
     * シーケンス図(「（外国株式注文受付サービス）注文受付１件処理」)<BR>
     * 　@　@1.3分岐フロー：　@出来終了後（is出来終了() == true）場合、<BR> 
     * 　@　@「出来終了後の注文の場合は、注文受付不可」例外をスローする<BR> 
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02166<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図(「（外国株式注文受付サービス）注文受付１件処理」)<BR>
     * 　@　@1.5分岐フロー：　@受付不可（is注文受付可能() == false）場合、
     * 　@　@「注文受付不可の状態」例外をスローする<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02649<BR>
     * ==========================================================<BR>
     * <BR> 
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_cancelUnit - (外国株式注文受付取消情報)<BR>
     * 外国株式注文受付取消情報<BR>
     * @@param l_sleRvcdQParams - (RCVD_Qデータ)<BR>
     * SLE_ECVD_Q　@Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public void notifyOrderAccept(
        WEB3FeqOrderUnit l_orderUnit,
        WEB3FeqOrderAcceptCancelUnit l_cancelUnit,
        SleRcvdQParams l_sleRvcdQParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOrderAccept(WEB3FeqOrderUnit, WEB3FeqOrderAcceptCancelUnit, SleRcvdQParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null || l_cancelUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //1.1 getOrderUnit(long)
        //注文単位を再取得する。 
        //※口座ロックする前に、別のトランザクションによって注文データが 
        //更新されている可能性があるため。
        //[getOrderUnit()に指定する引数]  
        //注文単位ID：　@引数.注文単位.getOrderUnitId()の戻り値
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager(); 
        WEB3FeqOrderUnit l_feqOrderUnit = null;
        try
        {
            l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        
        //1.2 is出来終了()
        //出来終了後の注文かを判定する。
        if (l_feqOrderUnit.isExecEnd())
        {
            //1.3 （分岐フロー：　@出来終了後（is出来終了() == true）場合、
            //出来終了後の注文の場合は、注文受付不可」例外をスローする）
            log.debug("出来終了後の注文は、注文受付結果アップロード不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02166, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "出来終了後の注文は、注文受付結果アップロード不可です。");
        }

        //RCVD_Qデータ.拒否原因コードの1文字目 == "M"） の場合、
        //外国株式注文受付取消情報.変更後受付区分を’失効’に設定する
        if (l_sleRvcdQParams.getRejectCode() != null &&
            SleGLRejectTypeDef.FIRST_CHAR_SUSPEND.equals(l_sleRvcdQParams.getRejectCode().substring(0,1)))
        {
            l_cancelUnit.aftChangeAcceptDiv = WEB3FeqAcceptTypeDef.NOT_EXECUTED;
        }

        //分岐フロー：引数.RCVD_Qデータ.入力経路区分が 3：注文受付の場合
        if (WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT.equals(l_sleRvcdQParams.getRouteDiv()))
        {
            //注文状態が注文受付処理を行ってよい状態かどうかをチェックする
            //[validate注文状態()に指定する引数]
            //　@注文単位：　@引数の注文単位
            //　@拒否原因コード：　@引数.RCVD_Qデータ．拒否原因コード

            this.validateOrderStatus(l_orderUnit, l_sleRvcdQParams.getRejectCode());

            //（分岐フロー：　@エラー通知（変更後受付区分==注文受付エラー
            //or変更後受付区分==訂正受付エラーor変更後受付区分==取消受付エラー）の場合
            if (WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(l_cancelUnit.aftChangeAcceptDiv)
                || WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(l_cancelUnit.aftChangeAcceptDiv)
                || WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(l_cancelUnit.aftChangeAcceptDiv))
            {
                //注文認証取消処理実行(外国株式注文単位)
                l_feqOrderManager.executeOrderAcceptCancel(l_feqOrderUnit);
       
                //getOrderUnit(arg0 : long)
                try
                {
                    l_feqOrderUnit = 
                        (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }        
            }
        }

        //（注文単位.注文有効状態 == "クローズ"）かつ（
        //RCVD_Qデータ.拒否原因コードの1文字目 == "M"）の場合
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
            l_sleRvcdQParams.getRejectCode() != null &&
            SleGLRejectTypeDef.FIRST_CHAR_SUSPEND.equals(l_sleRvcdQParams.getRejectCode().substring(0,1)))
        {
            //注文単位.注文状態 != "新規注文エラー"　@の場合
            if (!OrderStatusEnum.NOT_ORDERED.equals(l_orderUnit.getOrderStatus()) )
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }

        //is注文受付可能
        //受付可能かを判定する。
        //[is注文受付可能()に指定する引数]
        //変更後受付区分：　@引数.外国株式注文受付取消情報.変更後受付区分
        boolean l_blnOrderAcceptPoss =
            l_feqOrderUnit.isOrderAcceptPoss(l_cancelUnit.aftChangeAcceptDiv);

        if (!l_blnOrderAcceptPoss)
        {
            //（分岐フロー：　@受付不可（is注文受付可能() == false）場合、
            //注文受付不可の状態」例外をスローする）
            log.debug("注文受付不可の状態");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02649,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文受付不可の状態");
        }

        //(（RCVD_Qデータ．ACK コマンドタイプ == "変更承認"）かつ
        //（外国株式注文受付取消情報.変更後受付区分 == "訂正済"）)もしくは or
        //(（RCVD_Qデータ．ACK コマンドタイプ == "新規承認"）かつ
        //（外国株式注文受付取消情報.変更後受付区分 == "注文受付済"）)の場合
        if ((AckdCommand.MODIFY_ACK == l_sleRvcdQParams.getAckdCommand() &&
            WEB3FeqAcceptTypeDef.CHANGED.equals(l_cancelUnit.aftChangeAcceptDiv))
            || (AckdCommand.NEW_ORDER_ACK == l_sleRvcdQParams.getAckdCommand() &&
            WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(l_cancelUnit.aftChangeAcceptDiv)))
        {
            //validate受付電文(外国株取引RCVD_Q, 外国株式注文単位)
            //[引数]
            //外国株取引RCVD_Q：　@リクエストデータ.RCVD_Qデータ
            //注文単位：　@再取得した注文単位（getOrderUnitの戻り値）
            //※当シーケンス内で取得し直した注文単位
            //（より新しく取得したものを使用する）
            try
            {
                l_feqOrderUnit =
                    (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            this.validateAcceptTelegram(l_sleRvcdQParams, l_feqOrderUnit);
        }

        //get市場レスポンスメッセージ
        //変更後受付区分に対応する市場レスポンスメッセージを取得する。
        //[get市場レスポンスメッセージ()に指定する引数]
        //注文単位：　@引数.注文Id
        //変更後受付区分：　@引数.外国株式注文受付取消情報.変更後受付区分
        WEB3FeqAcceptUpdateService l_feqAcceptUpdateService =
            (WEB3FeqAcceptUpdateService)Services.getService(
                WEB3FeqAcceptUpdateService.class);
        MarketResponseMessage l_marketResponseMessage =
            l_feqAcceptUpdateService.getMarketResponseMessage(
                l_orderUnit.getOrderId(),
                l_cancelUnit.aftChangeAcceptDiv);

        //update受付(MarketResponseMessage)
        //受付更新処理を行う。
        //[update受付()に指定する引数]
        //市場レスポンスメッセージ：　@this.get市場レスポンスメッセージ()の戻り値
        l_feqAcceptUpdateService.updateAccept(l_marketResponseMessage);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate注文状態)<BR>
     * 対象注文が、注文受付を行って良い状態であるかチェックする。<BR>
     * <BR>
     * （チェック内容）<BR>
     * １．引数.拒否原因コード=='GL 007'　@の時以下の処理を行う。<BR>
     * 　@　@　@　@（引数.注文単位.注文状態==訂正中　@かつ <BR>
     * 　@　@　@　@isPartiallyExecuted()==true（：一部約定））　@以外の場合、<BR>
     * 　@　@　@　@「処理対象外データ」の例外をthrowする。<BR>
     * <BR>
     * ２．引数.拒否原因コード=='GL 022'　@の時以下の処理を行う。<BR>
     * 　@　@　@　@（引数.注文単位.注文状態==訂正中　@かつ <BR>
     * 　@　@　@　@isFullyExecuted()==true（：全約定））　@　@　@　@以外の場合 <BR>
     * 　@　@　@　@「処理対象外データ」の例外をthrowする。<BR>
     * <BR>
     * ３．新規注文発注中、訂正注文発注中、取消注文発注中の場合でかつ
     *     引数.拒否原因コードの1文字目=='M' の時以下の処理を行う。 <BR>
     * 　@  「該当注文は受付未済／変更の受付済／発注中の状態」の例外をthrowする。<BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_01975<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_strRejectCode - (拒否原因コード)<BR>
     * 拒否原因コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public void validateOrderStatus(
        WEB3FeqOrderUnit l_orderUnit,
        String l_strRejectCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderStatus(WEB3FeqOrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        //１．引数.拒否原因コード=='GL 007'　@の時以下の処理を行う。
        if (SleGLRejectTypeDef.CHANGE_REJECT_AF_PART_EXEC.equals(l_strRejectCode))
        {
            //（引数.注文単位.注文状態==訂正中　@かつ
            // isPartiallyExecuted()==true（：一部約定））　@以外の場合、
            if (!(OrderStatusEnum.MODIFYING.equals(l_orderUnit.getOrderStatus()) 
                && l_orderUnit.isPartiallyExecuted()))
            {
                //「処理対象外データ」の例外をthrowする。
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                    getClass().getName() + STR_METHOD_NAME,
                    "処理対象外データ。");
            }
        }

        //２．引数.拒否原因コード=='GL 022'　@の時以下の処理を行う。
        if (SleGLRejectTypeDef.CHANGE_REJECT_AF_ALL_EXEC.equals(l_strRejectCode))
        {
            //（引数.注文単位.注文状態==訂正中　@かつ
            // isFullyExecuted()==true（：全約定））　@　@　@　@以外の場合
            if (!(OrderStatusEnum.MODIFYING.equals(l_orderUnit.getOrderStatus()) 
                && l_orderUnit.isFullyExecuted()))
            {
                //「処理対象外データ」の例外をthrowする。
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                    getClass().getName() + STR_METHOD_NAME,
                    "処理対象外データ。");
            }
        }

        //３．新規注文発注中、訂正注文発注中、取消注文発注中の場合でかつ
        //    引数.拒否原因コードの1文字目=='M' の時以下の処理を行う。
        //　@　@「該当注文は受付未済／変更の受付済／発注中の状態」の例外をthrowする。
        if ((OrderStatusEnum.ORDERING.equals(l_orderUnit.getOrderStatus())
            || OrderStatusEnum.MODIFYING.equals(l_orderUnit.getOrderStatus())
            || OrderStatusEnum.CANCELLING.equals(l_orderUnit.getOrderStatus()))
            && l_strRejectCode != null
            && SleGLRejectTypeDef.FIRST_CHAR_SUSPEND.equals(l_strRejectCode.substring(0,1)))
        {
            log.debug("該当注文は受付未済／変更の受付済／発注中の状態。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当注文は受付未済／変更の受付済／発注中の状態。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate受付電文)<BR>
     * 注文受付電文の注文数量・価格が、<BR>
     * システム（注文単位）で把握している注文数量・価格と相違ないかチェックする。<BR>
     * <BR>
     * １．価格チェック<BR>
     * 　@　@引数.外国株取引RCVD_Q．指値価格（price） != 引数.注文単位．指値（limit_price）の場合、<BR>
     * 　@　@データ不整合エラーをthrowする。<BR>
     * 　@　@　@　@class : WEB3SystemLayerException<BR>
     * 　@　@　@　@tag   : SYSTEM_ERROR_80006<BR>
     * <BR>
     * ２．数量チェック<BR>
     * 　@　@引数.外国株取引RCVD_Q．注文数量（quantity） != 引数.注文単位．注文数量（quantity）の場合、<BR>
     * 　@　@データ不整合エラーをthrowする。<BR>
     * 　@　@　@　@class : WEB3SystemLayerException<BR>
     * 　@　@　@　@tag   : SYSTEM_ERROR_80006<BR>
     * <BR>
     * @@param l_sleRvcdQParams - (外国株取引RCVD_Q)<BR>
     * SLE_RCVD_Q　@Params<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4214980A032E
     */
    private void validateAcceptTelegram(
        SleRcvdQParams l_sleRvcdQParams,
        WEB3FeqOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAcceptTelegram(SleRcvdQParams, WEB3FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //１．価格チェック
        //　@　@引数.外国株取引RCVD_Q．指値価格（price） != 引数.注文単位．指値
        //　@　@（limit_price）の場合、データ不整合エラーをthrowする。
        if (l_sleRvcdQParams.getPrice() != l_orderUnit.getLimitPrice())
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        //２．数量チェック
        //引数.外国株取引RCVD_Q．注文数量（quantity） != 引数.注文単位．注文数量
        //（quantity）　@の場合、データ不整合エラーをthrowする。
        if (l_sleRvcdQParams.getQuantity() != l_orderUnit.getQuantity())
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
