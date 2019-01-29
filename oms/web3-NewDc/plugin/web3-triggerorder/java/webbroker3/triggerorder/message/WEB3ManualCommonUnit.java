head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ManualCommonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手動発注共通Unit(WEB3ManualCommonUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
                 : 2006/02/22　@沈迪(中訊) 仕様変更・モデル102
                 : 2006/08/24  唐性峰(中訊) モデルNo.156,162 對應 
                 : 2006/10/24  唐性峰(中訊) モデルNo.179 對應 
                 : 2006/11/10  唐性峰(中訊)　@モデルNo.187對應
                   2006/12/14 柴双紅(中訊) モデル205
Revesion History : 2007/06/29 孫洪江 (中訊) 仕様変更モデルNo.238 241
*/

package webbroker3.triggerorder.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (手動発注共通Unit)<BR>
 * 手動発注共通Unitクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3ManualCommonUnit extends Message 
{
    
    /**
     * (ID)<BR>
     * 注文ID<BR>
     */
    public String id;
    
    /**
     * (条件注文種別)<BR>
     * 条件注文種別<BR>
     * <BR>
     * 1：　@連続注文<BR>
     * 2：　@OCO注文<BR>
     * 3：　@IFD注文<BR>
     * 4：　@逆指値注文<BR>
     * 5：　@W指値注文<BR>
     */
    public String triggerOrderType;
    
    /**
     * (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * <BR>
     * 1：　@基準値以上<BR>
     * 2：　@基準値以下<BR>
     * <BR>
     * ※条件注文種別が"逆指値注文"の場合設定<BR>
     */
    public String condOperator = null;
    
    /**
     * (発注条件単価)<BR>
     * 発注条件単価<BR>
     * <BR>
     * ※条件注文種別が"逆指値注文"の場合設定<BR>
     */
    public String orderCondPrice = null;
    
    /**
     * (Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値 <BR>
     * <BR>
     * ※条件注文種別が"W指値注文"の場合設定<BR>
     */
    public String wLimitOrderPriceDiv = null;
    
    /**
     * (Ｗ指値用注文単価)<BR>
     * Ｗ指値用注文単価区分「1：指値」の場合設定<BR>
     */
    public String wLimitPrice = null;
    
    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行 <BR>
     * <BR>
     * ※条件注文種別が"W指値注文"の場合設定<BR>
     */
    public String wlimitExecCondType = null;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName = null;
    
    /**
     * (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     */
    public String productDiv;
    
    /**
     * (取引区分)<BR>
     * 取引区分<BR>
     * <BR>
     * 1：　@現物買付注文<BR>
     * 2：　@現物売付注文<BR>
     * 3：　@新規買建注文<BR>
     * 4：　@新規売建注文<BR>
     * 5：　@買建返済注文<BR>
     * 6：　@売建返済注文<BR>
     * 7：　@現引注文<BR>
     * 8：　@現渡注文<BR>
     * 601：　@指数先物新規買建注文<BR>
     * 602：　@指数先物新規売建注文<BR>
     * 603：　@指数先物売建返済注文<BR>
     * 604：　@指数先物買建返済注文<BR>
     * 605：　@指数オプション新規買建注文<BR>
     * 606：　@指数オプション新規売建注文<BR>
     * 607：　@指数オプション売建返済注文<BR>
     * 608：　@指数オプション買建返済注文<BR>
     */
    public String tradingType;
    
    /**
     * (執行条件)<BR>
     * 執行条件<BR>
     * <BR>
     * 1：　@無条件<BR>
     * 3：　@寄付<BR>
     * 4：　@引け<BR>
     * 7：　@不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (注文期限区分)<BR>
     * 注文期限区分<BR>
     * <BR>
     * 1：当日限り<BR>
     * 2：出来るまで注文<BR>
     * 3：夕場まで注文<BR>
     */
    public String expirationDateType;
    
    /**
     * (注文有効期限)<BR>
     * 注文有効期限<BR>
     * <BR>
     * ※注文期限区分が「出来るまで注文」の場合に設定<BR>
     */
    public Date expirationDate = null;
    
    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     */
    public String orderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 注文単価区分<BR>
     * <BR>
     * 0：　@成行<BR>
     * 1：　@指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     * <BR>
     * ※注文単価区分が「指値」の場合に設定<BR>
     */
    public String limitPrice = null;
    
    /**
     * (注文状態区分)<BR>
     * 注文状態区分<BR>
     * <BR>
     * 0：　@その他<BR>
     * 1：　@受付済（新規注文）<BR>
     * 2：　@発注中（新規注文）<BR>
     * 3：　@発注済（新規注文）<BR>
     * 6：　@発注失敗（新規注文）<BR>
     * 7：　@受付済（変更注文）<BR>
     * 8：　@発注中（変更注文）<BR>
     * 10：　@発注済（変更注文）<BR>
     * 11：　@発注失敗（変更注文）<BR>
     * 12：　@受付済（取消注文）<BR>
     * 13：　@発注中（取消注文）<BR>
     * 14：　@発注済（取消注文）<BR>
     * 15：　@発注失敗（取消注文）<BR>
     * 20：　@一部失効<BR>
     * 21：　@全部失効<BR>
     * 22：　@無効<BR>
     * 24：　@切替注文<BR>
     * 25：　@切替受付<BR> 
     * 26：　@切替完了<BR>
     * 27：　@切替注文(失敗)<BR> 
     * 50：　@繰越済<BR>
     * 51：　@繰越失敗<BR>
     */
    public String orderState;
    
    /**
     * (約定状態区分)<BR>
     * 約定状態区分<BR>
     * <BR>
     * 0：　@未約定<BR>
     * 1：　@一部成立<BR>
     * 2：　@全部成立<BR>
     */
    public String execType;
    
    /**
     * (訂正取消区分 )<BR>
     * 訂正取消区分<BR>
     * <BR>
     * 0：　@初期値<BR>
     * 1：　@取消中<BR>
     * 2：　@一部取消完了<BR>
     * 3：　@全部取消完了<BR>
     * 4：　@取消失敗<BR>
     * 5：　@訂正中<BR>
     * 6：　@一部訂正完了<BR>
     * 7：　@全部訂正完了<BR>
     * 8：　@訂正失敗<BR>
     * 9：　@エラー<BR>
     */
    public String changeCancelDiv = null;
    
    /**
     * (注文時間 )<BR>
     * 注文時間<BR>
     */
    public Date orderDate;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate;
    
    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;
    
    /**
     * (発注状況区分)<BR>
     * 発注状況区分<BR>
     * <BR>
     * 1：　@待機@中<BR>
     * 2：　@発注中<BR>
     * 3：　@発注完了<BR>
     * 8：　@発注審査エラー<BR>
     * 9：　@発注遅延エラー<BR>
     * 13：　@ストップ注文失効<BR>
     * 99：　@その他<BR>
     */
    public String triggerOrderState;
    
    /**
     * (概算受渡代金)<BR>
     * 概算受渡代金<BR>
     */
    public String estimatedPrice;
    
    /**
     * (決済順序)<BR>
     * 決済順序<BR>
     * <BR>
     * 0：ランダム<BR>
     * 1：単価益順<BR>
     * 2：単価損順<BR>
     * 3：建日順<BR>
     */
    public String closingOrder = null;
    
    /**
     * (時価区分)<BR>
     * 時価区分<BR>
     * <BR>
     * 1:現在値<BR> 
     * 2:売気配値 <BR>
     * 3:買気配値 <BR>
     * 4:前日終値 <BR>
     * <BR>
     * ※IFOの場合、nullをセット<BR>
     */
    public String currentPriceDiv = null;
    
    /**
     * (時価（現在値）)<BR>
     * 時価（現在値）<BR>
     */
    public String currentPrice = null;
    
    /**
     * (前日比)<BR>
     * 前日比<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (取引時間（時価発表時間）)<BR>
     * 取引時間（時価発表時間）<BR>
     */
    public Date currentPriceTime = null;

    /**
     * (現在値)
     */
    public String boardCurrentPrice = null;

    /**
     * (現在値時刻)
     */
    public Date boardCurrentPriceTime = null;

    /**
     * (現在値区分)
     */
    public String boardCurrentPriceDiv = null;

    /**
     * (現在値前日比)
     */
    public String boardChange = null;

    /**
     * (出来高)
     */
    public String volume = null;

    /**
     * (出来高時刻)
     */
    public Date volumeTime = null;

    /**
     * (買気配値タイトル区分)
     */
    public String askPriceTitle = null;

    /**
     * (買気配値)
     */
    public String askPrice = null;

    /**
     * (買気配値時刻)
     */
    public Date askPriceTime = null;

    /**
     * (売気配値タイトル区分)
     */
    public String bidPriceTitle = null;

    /**
     * (売気配値)
     */
    public String bidPrice = null;

    /**
     * (売気配値時刻)
     */
    public Date bidPriceTime = null;

    /**
     * (基準値段)
     */
    public String basePrice = null;

    /**
     * (手動発注エラーコード)<BR>
     * 手動発注エラーコード<BR>
     * <BR>
     * 00：　@正常<BR>
     * 01：　@取消済エラー<BR>
     * 02：　@発注済エラー<BR>
     * 03：　@発注失敗<BR>
     * 04：　@約定済エラー <BR>
     * 05：　@失効済エラー<BR>
     * 90：　@該当注文なし<BR>
     * 99：　@その他エラー<BR>
     */
    public String manualOrderErrorCode;
    
    /**
     * (時価情報受信時間)
     * 時価情報受信時間
     */
    public Date currentPriceInfoAcceptTime = null;
    
    /**
     * (トリガー起動時間)
     * トリガー起動時間 
     */
    public Date triggerStartTime = null;
    
    /**
     * (発注完了時間)
     * 発注完了時間
     */ 
    public Date orderCompleteTime = null;
    
    /**
     * (処理状況区分)
     * 処理状況区分
     */
    public String transactionStateType = null;
    
    /**
     * (単価調整値)
     * 単価調整値
     */
    public String priceAdjustmentValue = null;
    
    /**
     * (手数料情報)<BR>
     * 手動発注手数料情報<BR>
     */
    public WEB3ManualCommissionInfoUnit commissionInfo;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@null ： その他<BR>
     */
    public String sessionType;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F48892038A
     */
    public WEB3ManualCommonUnit() 
    {
     
    }
}
@
