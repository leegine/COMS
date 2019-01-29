head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション当日注文約定詳細レスポンスクラス(WEB3OptionsExecuteDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 呉艶飛 新規作成
              001: 2006/07/12 張騰宇　@(中訊)　@仕様変更　@454,457,470,488,497,527
Revesion History : 2007/06/08 孫洪江  (中訊)  仕様変更モデルNo.640
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;


/**
 * (株価指数オプション当日注文約定詳細レスポンス)<BR>
 * 株価指数オプション当日注文約定詳細レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsExecuteDetailsResponse extends WEB3GenResponse 
{
    
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "options_executeDetails";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406101530L;
    /**
     * (銘柄名)
     */
    public String opProductName;
    
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
     * (オプション商品区分)<BR>
     * <BR>
     * P：プットオプション C：コールオプション<BR>
     */
    public String opProductType;
    
    /**
     * (行使価格)
     */
    public String strikePrice;
    
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
    public java.util.Date openDate;
    
    /**
     * (建単価)<BR>
     * モバイルで使用<BR>
     */
    public String contractPrice;
    
    /**
     * (注文数量)
     */
    public String opOrderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)
     */
    public String limitPrice;
    
    /**
     * (概算受渡代金)
     */
    public String estimatedPrice;
    
    /**
     * (注文有効期限)
     */
    public java.util.Date expirationDate;
    
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
     * (逆指値用プレミアム/原資産価格)<BR>
     * <BR>
     * 0：原資産価格　@1：プレミアム<BR>
     * <BR>
     * 発注条件区分「1：逆指値」の場合設定<BR>
     */
    public String stopPremium_underlyingAssets;
    
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
     * (Ｗ指値用プレミアム／原資産価格)<BR>
     * <BR>
     * 0：原資産価格　@1：プレミアム<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定
     */
    public String wlimitPremium_underlyingAssets;
    
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
     * (W指値用執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitExecCondType;     
    
    /**
     * (W指値用有効状態区分)<BR>
     * <BR>
     * 0：リミット注文有効　@1：ストップ注文有効 <BR>
     * 2：ストップ注文失効済 <BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitEnableStatusDiv; 
    
    /**
     * (W指値用切替前注文単価)<BR>
     * <BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgLimitPrice; 
    
    /**
     * (W指値用切替前執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgExecCondType; 
    
    /**
     * (元発注条件区分)<BR>
     * <BR>
     * 0：指定なし　@1：逆指値　@2：W指値 <BR>
     * <BR>
     * ※発注条件執行後の場合に設定<BR>
     */
    public String orgOrderCondType; 
    
    /**
     * (元プレミアム/原資産価格)<BR>
     * <BR>
     * 0：プレミアム　@1：原資産価格 <BR>
     * <BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合に設定<BR>
     */
    public String orgPremium_underlyingAssets; 
    
    /**
     * (元発注条件単価)<BR>
     * <BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合に設定<BR>
     */
    public String orgOrderCondPrice; 
    
    /**
     * (元発注条件演算子)<BR>
     * <BR>
     * 1：以上　@2：以下 <BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合に設定<BR>
     */
    public String orgCondOperator; 
    
    /**
     * (元W指値用注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値 <BR>
     * ※元発注条件区分が2：Ｗ指値の場合に設定<BR>
     */
    public String orgWLimitOrderPriceDiv; 
    
    /**
     * (元W指値用注文単価)<BR>
     * ※元Ｗ指値用注文単価区分が、1：指値の場合に設定<BR>
     */
    public String orgWLimitPrice; 
    
    /**
     * (元W指値用執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行 <BR>
     * ※元発注条件区分が2：Ｗ指値の場合に設定<BR>
     */
    public String orgWlimitExecCondType;
        
    /**
     * (注文受付日)
     */
    public java.util.Date orderDate;
    
    /**
     * (注文状態区分)<BR>
     * <BR>
     * ※コード値はメッセージ定義フォルダ以下の <BR> 
     * 「ﾒｯｾｰｼﾞ定義書_株価指数オプション(約定照会).xls」の<BR> 
     * 「指数OP当日注文約定詳細」シートの注文状態区分定義を参照<BR>
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
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * (約定数量)<BR>
     * <BR>
     * 約定ありの場合設定<BR>
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     * <BR>
     * 約定ありの場合設定<BR>
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
     * (受渡金額)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public String deliveryPrice;
    
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
     * (注文約定詳細約定)<BR>
     * <BR>
     * 約定ありの場合設定
     */
    public WEB3OptionsExecuteUnit[] opExecuteUnits;

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
     * 訂正取消区分<BR>
     * 0：初期値 1：取消中 2：一部取消完了 <BR>
     * 3：全部取消完了4：取消失敗<BR>
     * 5：訂正中 6：一部訂正完了 <BR>
     * 7：全部訂正完了 8：訂正失敗 9：エラー<BR>
     */
    public String changeCancelDiv;
            
    /**
     * 注文経路区分<BR>
     * 1：コールセンター　@<BR>
     * 2：ＰＣ   3：スリングショット <BR>
     * 4：i-mode 5：Vodafone <BR>
     * 6：AU 9：HOST A：管理者<BR>
     */
    public String orderRootDiv;

    /**
     * 処理状況<BR>
     * ※コード値はメッセージ定義フォルダ以下の <BR>
     * 「ﾒｯｾｰｼﾞ定義書_株価指数オプション(共通).xls」の処理状況区分定義を参照<BR>
     */
    public String transactionStateType;
    
    /**
     * (遅延区分)<BR>
     * <BR>
     * 0：正常　@1：遅延 <BR>
     * <BR>
     * 発注条件区分が、「1：逆指値」または「2：W指値」の場合設定される<BR>
     */
    public String delayDiv;
    
    /**
     * (手動発注可能フラグ)<BR>
     * <BR>
     * true：手動発注可能　@　@false：手動発注不可 <BR>
     * <BR>
     * 発注条件区分が、「1：逆指値」または「2：W指値」で、 <BR>
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
    public WEB3OptionsExecuteDetailsResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsExecuteDetailsResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
