head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToOrderRefCommonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・注文照会共通Unit(WEB3AdminToOrderRefCommonUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16　@余新敏(中訊) 新規作成
                 : 2006/08/23　@肖志偉(中訊) 仕様変更No.66,71,080
                 : 2006/10/18  唐性峰(中訊) 仕様変更モデルNo.094
                 : 2006/11/10  柴双紅(中訊) 仕様変更モデルNo.109
Revesion History : 2007/06/30  孟亜南(中訊) 仕様変更モデルNo.129
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (トリガー注文管理者・注文照会共通Unit)<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToOrderRefCommonUnit extends Message
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
     * ※条件注文種別が"逆指値注文"または"Ｗ指値"の場合に設定<BR>
     */
    public String condOperator = null;
    
    /**
     * (発注条件単価)<BR>
     * 発注条件単価<BR>
     * <BR>
     * ※条件注文種別が"逆指値注文"または"Ｗ指値"の場合に設定<BR>
     */
    public String orderCondPrice = null;
    
    /**
     * (Ｗ指値用注文単価区分)<BR>
     * 0：　@成行 <BR>
     * 1：　@指値 <BR>
     * <BR>
     * ※条件注文種別が"Ｗ指値"の場合に設定<BR>
     */
    public String wLimitOrderPriceDiv = null;
    
    /**
     * (Ｗ指値用注文単価)<BR>
     * ※Ｗ指値用注文単価区分が"指値"の場合設定<BR>
     */
    public String wLimitPrice = null;
    
    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：　@無条件<BR> 
     * 3：　@寄付<BR> 
     * 4：　@引け<BR> 
     * 7：　@不出来引け成行 <BR>
     * <BR>
     * ※条件注文種別が"Ｗ指値"の場合に設定<BR>
     */
    public String wlimitExecCondType = null;
    
    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：　@リミット注文有効 <BR>
     * 1：　@ストップ注文有効 <BR>
     * 2：　@ストップ注文失効済 <BR> 
     * <BR>
     * ※条件注文種別が"Ｗ指値"の場合設定<BR>
     */
    public String wlimitEnableStatusDiv = null;
    
    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * ※条件注文種別が"Ｗ指値"の場合、設定<BR>
     */
    public String wlimitBefChgLimitPrice = null;
    
    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * 1：　@無条件　@<BR>
     * 3：　@寄付<BR>
     * 4：　@引け<BR> 
     * 7：　@不出来引け成行 <BR>
     * <BR>
     * ※条件注文種別が"Ｗ指値"の場合、設定<BR>
     */
    public String wlimitBefChgExecCondType = null;
    
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
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName = null;
    
    /**
     * (商品区分)<BR>
     * 商品区分 <BR>
     * <BR>
     * 1：　@現物株式 <BR>
     * 2：　@信用取引 <BR>
     * 3：　@先物 <BR>
     * 4：　@オプション <BR>
     */
    public String productDiv;
    
    /**
     * (取引区分)<BR>
     * 取引区分<BR>
     * <BR>
     * 1：　@現物買付注文 <BR>
     * 2：　@現物売付注文 <BR>
     * 3：　@新規買建注文 <BR>
     * 4：　@新規売建注文 <BR>
     * 5：　@買建返済注文 <BR>
     * 6：　@売建返済注文 <BR>
     * 7：　@現引注文 <BR>
     * 8：　@現渡注文 <BR>
     * 601：　@指数先物新規買建注文 <BR>
     * 602：　@指数先物新規売建注文 <BR>
     * 603：　@指数先物売建返済注文 <BR>
     * 604：　@指数先物買建返済注文 <BR>
     * 605：　@指数オプション新規買建注文 <BR>
     * 606：　@指数オプション新規売建注文 <BR>
     * 607：　@指数オプション売建返済注文 <BR>
     * 608：　@指数オプション買建返済注文 <BR>
     */
    public String tradingType;
    
    /**
     * (執行条件)<BR>
     * 執行条件<BR>
     * <BR>
     * 1：　@無条件　@<BR>
     * 3：　@寄付　@<BR>
     * 4：　@引け　@<BR>
     * 7：　@不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (注文有効期限)<BR>
     * 注文有効期限 <BR>
     * <BR>
     * ※出来るまで注文の場合設定<BR>
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
     * 0：　@成行　@<BR>
     * 1：　@指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     */
    public String limitPrice = null;
    
    /**
     * (注文状態区分)<BR>
     * 注文状態区分<BR>
     * <BR>
     * 0：　@その他 <BR>
     * 1：　@受付済（新規注文） <BR>
     * 2：　@発注中（新規注文） <BR>
     * 3：　@発注済（新規注文）  <BR>
     * 6：　@発注失敗（新規注文） <BR>
     * 7：　@受付済（変更注文） <BR>
     * 8：　@発注中（変更注文）  <BR>
     * 10：　@発注済（変更注文） <BR>
     * 11：　@発注失敗（変更注文） <BR>
     * 12：　@受付済（取消注文）  <BR>
     * 13：　@発注中（取消注文） <BR>
     * 14：　@発注済（取消注文） <BR>
     * 15：　@発注失敗（取消注文） <BR>
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
     * 0：　@未約定　@<BR>
     * 1：　@一部成立　@<BR>
     * 2：　@全部成立 <BR>
     */
    public String execType;
    
    /**
     * (訂正取消区分)<BR>
     * 訂正取消区分 <BR>
     * <BR>
     * 0：　@初期値 <BR>
     * 1：　@取消中 <BR>
     * 2：　@一部取消完了 <BR>
     * 3：　@全部取消完了 <BR>
     * 4：　@取消失敗 <BR>
     * 5：　@訂正中 <BR>
     * 6：　@一部訂正完了 <BR>
     * 7：　@全部訂正完了 <BR>
     * 8：　@訂正失敗 <BR>
     * 9：　@エラー <BR>
     * A：　@W指値注文切替中<BR> 
     * B：　@W指値注文一部切替完了<BR> 
     * C：　@W指値注文全部切替完了<BR> 
     * D：　@W指値注文切替失敗<BR>
     */
    public String changeCancelDiv;
    
    /**
     * (注文時間)<BR>
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
     * (時価情報受信時間)<BR>
     * 時価情報受信時間<BR>
     * <BR>
     * ※条件種別が"予約注文"の場合、または、<BR>
     * 　@時価情報を受信していない場合は、nullをセット。<BR>
     */
    public Date currentPriceInfoAcceptTime = null;
    
    /**
     * (トリガー起動時間t)<BR>
     * トリガー起動時間<BR>
     * <BR>
     * ※起動通知が行われていない場合、nullをセット。<BR>
     */
    public Date triggerStartTime = null;
    
    /**
     * (発注完了時間)<BR>
     * 発注完了時間<BR>
     * <BR>
     * ※発注処理が完了していない場合、nullをセット。<BR>
     */
    public Date orderCompleteTime = null;

    /**
     * (歩み値照合区分)<BR>
     * 歩み値照合区分 <BR>
     * <BR>
     * 0：　@正常 <BR>
     * 1：　@未発注疑い<BR>
     * 2：　@不正発注疑い <BR>
     * 3：　@発注遅延疑い <BR>
     * <BR>
     * ※「9：　@全てのエラー」は設定されない<BR>
     */
    public String tickMatchDiv = null;

    /**
     * (歩み値予想時間)<BR>
     * 歩み値予想時間<BR>
     */
    public Date tickExpectTime = null;
    
    /**
     * (手動発注可能フラグ)<BR>
     * 手動発注可能フラグ<BR>
     */
    public boolean manualFlag;

    /**
     * (夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグ<BR>
     * false：夕場前繰越なし <BR>
     * true：夕場前繰越あり <BR>
     */
    public boolean eveningSessionCarryoverFlag;

    /**
     * (立会区分)<BR>
     * 立会区分<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他 <BR>
     */
    public String sessionType = null;

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C9032C
     */
    public WEB3AdminToOrderRefCommonUnit() 
    {
     
    }
}
@
