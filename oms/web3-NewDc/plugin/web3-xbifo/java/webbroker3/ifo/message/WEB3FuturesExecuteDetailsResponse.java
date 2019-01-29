head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物当日注文約定詳細レスポンスクラス(WEB3FuturesExecuteDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 李媛 新規作成
                   2006/07/28 張騰宇 仕様変更 モデル454,457,470,488
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.640
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;


/**
 * (株価指数先物当日注文約定詳細レスポンス)<BR>
 * 株価指数先物当日注文約定詳細レスポンスクラス
 * 
 * @@author 李媛
 * @@version 1.0
 */
public class WEB3FuturesExecuteDetailsResponse extends WEB3GenResponse 
{
    
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "futures_executeDetails";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201113L;
    /**
     * (銘柄名)
     */
    public String futProductName;
    
    /**
     * (指数種別)<BR>
     * <BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     */
    public String targetProductCode;
    
    /**
     * (限月)<BR>
     * YYYYMM形式<BR>
     */
    public String delivaryMonth;
    /**
     * (発注日)<BR>
     */
    public Date orderBizDate;

    /**
     * (訂正取消区分)<BR>
     * 0：初期値 1：取消中 2：一部取消完了 <BR>
     * 3：全部取消完了4：取消失敗<BR>
     * 5：訂正中 6：一部訂正完了 <BR>
     * 7：全部訂正完了 8：訂正失敗 9：エラー<BR>
     */
    public String changeCancelDiv;
        
    /**
     * (注文経路区分)<BR>
     * 1：コールセンター　@<BR>
     * 2：ＰＣ   3：スリングショット <BR>
     * 4：i-mode 5：Vodafone <BR>
     * 6：AU 9：HOST A：管理者<BR>
     */
    public String orderRootDiv;
    
    /**
     * (処理状況)<BR>
     * ※コード値はメッセージ定義フォルダ以下の <BR>
     * 「ﾒｯｾｰｼﾞ定義書_株価指数先物(共通).xls」の処理状況区分定義を参照<BR>
     */
    public String transactionStateType;
    
    /**
     * (取引市場)<BR>
     * <BR>
     * 1：東京　@2：大阪<BR>
     */
    public String marketCode;
    
    /**
     * (取引区分)<BR>
     * <BR>
     * 3：新規買建注文　@4：新規売建注文<BR>
     * 5：買建返済注文（売返済）　@6：売建返済注文（買返済）<BR>
     */
    public String tradingType;
    
    /**
     * (建日)<BR>
     * モバイルで使用<BR>
     */
    public Date openDate;
    
    /**
     * (建単価)<BR>
     * モバイルで使用<BR>
     */
    public String contractPrice;
    
    /**
     * (注文数量)
     */
    public String futOrderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文価格)
     */
    public String limitPrice;
    
    /**
     * (概算建代金（概算決済損益）)
     */
    public String estimatedContractPrice;
    
    /**
     * (注文有効期限)
     */
    public Date expirationDate;
    
    /**
     * (執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付　@4：引け　@7：不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (発注条件区分)<BR>
     * <BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * (逆指値用発注条件単価)<BR>
     * <BR>
     * 発注条件区分「1：逆指値」の場合設定<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (逆指値用発注条件演算子)<BR>
     * <BR>
     * 1：以下　@2：以上<BR>
     * <BR>
     * 発注条件区分「1：逆指値」の場合設定
     */
    public String stopOrderCondOperator;
    
    /**
     * (Ｗ指値用発注条件単価)<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * <BR>
     * 1：以下　@2：以上<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (Ｗ指値用注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (Ｗ指値用注文単価)<BR>
     * <BR>
     * Ｗ指値用注文単価区分「1：指値」の場合設定
     */
    public String wLimitPrice;
    
    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効<BR>
     * 2：ストップ注文失効済<BR>
     * <BR>
     * 発注条件区分または元発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitEnableStatusDiv;
    
    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgLimitPrice;
    
    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgExecCondType;
    
    /**
     * (元発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     * <BR>
     * ※発注条件執行後の場合に設定<BR>
     */
    public String orgOrderCondType;
    
    /**
     * (元発注条件単価)<BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合に設定<BR>
     */
    public String orgOrderCondPrice;
    
    /**
     * (元発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合に設定<BR>
     */
    public String orgCondOperator;
    
    /**
     * (元Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * ※元発注条件区分が2：Ｗ指値の場合に設定<BR>
     */
    public String orgWLimitOrderPriceDiv;
    
    /**
     * (元Ｗ指値用注文単価)<BR>
     * ※元Ｗ指値用注文単価区分が、1：指値の場合に設定<BR>
     */
    public String orgWLimitPrice;
    
    /**
     * (元Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * ※元発注条件区分が2：Ｗ指値の場合に設定<BR>
     */
    public String orgWlimitExecCondType;
    
    /**
     * (注文受付日)
     */
    public Date orderDate;
    
    /**
     * (注文状態区分)<BR>
     * 
     * 0：その他 1：受付済（新規注文） 2：発注中（新規注文） <BR>
     * 3：発注済（新規注文） 6：発注失敗（新規注文） 7：受付済（変更注文） <BR>
     * 8：発注中（変更注文） 10：発注済（変更注文） 11：発注失敗（変更注文） <BR>
     * 12：受付済（取消注文） 13：発注中（取消注文） 14：発注済（取消注文） <BR>
     * 15：発注失敗（取消注文） 20：一部失効 21：全部失効 22：無効 23：切替注文<BR>
     * 24：切替受付 25：切替完了 26：切替注文(失敗) 50：繰越済 51：繰越失敗<BR>
     */
    public String orderState;
    
    /**
     * (繰越エラーコード)<BR>
     * <BR>
     * 0001：値幅エラー 0002：預り金不足エラー 0003：株式残高不足エラー 0004：保証金不足エラー <BR>
     * 0005：建株残高不足エラー 0006：売買停止銘柄エラー 0007：市場変更銘柄エラー <BR>0008：買付余力エラー 
     * 0009：売付可能数量エラー 0010：特定口座エラー <BR>0011：出合停止銘柄エラー 9001：その他エラー <BR>
     * <BR>
     * 注文状態区分が「51：繰越失敗」の場合設定<BR>
     * <BR>
     * 注文繰越でエラーが発生した場合の、エラー理由のコード。<BR>
     * 上記以外の場合はnullをセット。
     */
    public String transferErrCode;
    
    /**
     * (受渡日)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public Date deliveryDate;
    
    /**
     * (約定数量)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public String execPrice;
    
    /**
     * (約定状態区分)<BR>
     * <BR>
     * 0：未約定　@1：一部成立　@2：全部成立<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public String execType;
    
    /**
     * (約定金額)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public String execTotalPrice;
    
    /**
     * (建代金（決済損益）)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public String contractExecPrice;
    
    /**
     * (手数料)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public String commission;
    
    /**
     * (消費税)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public String consumptionTax;
    
    /**
     * (部店コード)<BR>
     */ 
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     */ 
    public String accountCode;
    
    /**
     * (顧客名 )<BR>
     */ 
    public String accountName;
    
    /**
     * (注文約定詳細約定)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public WEB3FuturesExecuteUnit[] futExecuteUnits;
    
    /**
     * (遅延区分)<BR>
     * 0：正常　@1：遅延<BR>
     * <BR>
     * 発注条件区分が、「1：逆指値」または「2：W指値」の場合設定される<BR>
     */
    public String delayDiv;
    
    /**
     * (手動発注可能フラグ)<BR>
     * true：手動発注可能　@　@false：手動発注不可<BR>
     * <BR>
     * 発注条件区分が、「1：逆指値」または「2：W指値」で、<BR>
     * 手動発注が可能である場合、trueが設定される。<BR>
     */
    public boolean manualFlag;

    /**
     * (夕場前繰越対象フラグ)<BR>
     * false：夕場前繰越なし<BR>
     * true：夕場前繰越あり<BR>
     */
    public boolean eveningSessionCarryoverFlag;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;

    /**
     * デフォルトコンストラクタ
     */
    public WEB3FuturesExecuteDetailsResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3FuturesExecuteDetailsResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
