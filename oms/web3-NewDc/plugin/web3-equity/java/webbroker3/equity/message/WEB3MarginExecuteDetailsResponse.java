head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文約定詳細レスポンスクラス(WEB3MarginExecuteDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
Revesion History : 2006/07/05 肖志偉 (中訊) 仕様変更モデル941
Revesion History : 2006/11/02 張騰宇(中訊) モデル 948,999
Revesion History : 2006/11/20 張騰宇(中訊) モデル 1056
Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更・モデル1164
Revesion History : 2007/07/24 何文敏 (中訊) 仕様変更・モデル1184
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引注文約定詳細レスポンス）。<br>
 * <br>
 * 信用取引注文約定詳細レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginExecuteDetailsResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_executeDetails";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101804L;        
    /**
     * (注文No)<BR>
     */
    public String orderActionId;
    
    /**
     * (銘柄コード)<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     */
    public String productName;
    
    /**
     * (市場コード)<BR>
     */
    public String marketCode;
    
    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定<BR>
     */
    public String taxType;
    
    /**
     * (取引区分)<BR>
     * 3：新規買建注文　@4：新規売建注文<BR>
     * 5：買建返済注文（売返済）　@6：売建返済注文（買返済）<BR>
     * 7：現引注文　@8：現渡注文<BR>
     * <BR>
     * 決済建玉一覧画面へのハイパーリンクの有無判定にも使用<BR>
     */
    public String tradingType;
    
    /**
     * (信用取引弁済)<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (値段条件)<BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値　@ 7:成行残数取消 <BR>
     */
    public String priceCondType;
    
    /**
     * (執行条件)<BR>
     * 1：無条件　@3：寄付　@4：引け　@7：不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * (逆指値用発注条件単価)<BR>
     * 発注条件区分「1：逆指値」の場合設定<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (逆指値用発注条件演算子)<BR>
     * 1：以下　@2：以上<BR>
     * <BR>
     * 発注条件区分「1：逆指値」の場合設定<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (Ｗ指値用発注条件単価)<BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * 1：以下　@2：以上<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (Ｗ指値用注文単価区分「1：指値」の場合設定)<BR>
     */
    public String wLimitPrice;
    
    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効<BR>
     * 2：ストップ注文失効済<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合設定される <BR>
     */
    public String wlimitEnableStatusDiv;
    
    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgLimitPrice;
    
    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行 <BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgExecCondType;

    /**
     * (元発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orgOrderCondType;
    
    /**
     * (元発注条件単価)<BR>
     * 元発注条件単価<BR>
     */
    public String orgOrderCondPrice;
    
    /**
     * (元発注条件演算子)<BR>
     * 1：以上　@2：以下 <BR>
     */
    public String orgOrderCondOperator;
    
    /**
     * (元Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWlimitOrderPriceDiv;
    
    /**
     * (元Ｗ指値用注文単価)<BR>
     * ※元Ｗ指値用注文単価区分が、1：指値の場合設定される。<BR>
     */
    public String orgWlimitPrice;
    
    /**
     * (元Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWlimitExecCondType;
    
    /**
     * (注文株数)<BR>
     */
    public String orderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     */
    public String limitPrice;
    
    /**
     * (概算受渡代金)<BR>
     * 新規建　@　@：　@概算建代金<BR>
     * 返済　@　@　@：　@概算決済損益代金<BR>
     * 現引現渡　@：　@概算受渡代金<BR>
     */
    public String estimatedPrice;
    
    /**
     * (注文有効期限)
     */
    public Date expirationDate;
    
    /**
     * (注文受付日)<BR>
     */
    public Date orderDate;
    
    /**
     * (注文状態区分)<BR>
     * 0：その他 1：受付済（新規注文） 2：発注中（新規注文） <BR>
     * 3：発注済（新規注文） 6：発注失敗（新規注文） <BR>
     * 7：受付済（変更注文） 8：発注中（変更注文） <BR>
     * 10：発注済（変更注文） 11：発注失敗（変更注文） 12：受付済（取消注文） <BR>
     * 13：発注中（取消注文） 14：発注済（取消注文） 15：発注失敗（取消注文） <BR>
     * 20：一部失効 21：全部失効 22：無効 <BR>
     * 24:切替注文 25:切替受付 26:切替完了 27:切替注文(失敗)<BR>
     * 50：繰越済 51：繰越失敗<BR>
     */
    public String orderState;
    
    /**
     * (繰越エラーコード)<BR>
     * 0001：値幅エラー 0002：預り金不足エラー 0003：株式残高不足エラー<BR>
     * 0004：保証金不足エラー 0005：建株残高不足エラー 0006：売買停止銘柄エラー<BR>
     * 0007：市場変更銘柄エラー 0008：買付余力エラー 0009：売付可能数<BR>
     * 量エラー 0010：特定口座エラー 0011：注文繰越スキップ銘柄エラー　@0012：<BR>
     * 二階建チェックエラー　@0014：呼値チェックエラー　@0015：空売りチェックエラー<BR>
     * 0016：決済期日到来済エラー　@9001：その他エラー<BR>
     * <BR>
     * 注文状態区分が「51：繰越失敗」の場合設定<BR>
     * <BR>
     * 注文繰越でエラーが発生した場合の、エラー理由のコード。<BR>
     * 上記以外の場合はnullをセット。<BR>
     */
    public String transferErrCode;
    
    /**
     * (発注日)<BR>
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * (受渡日)<BR>
     * 約定ありの場合設定<BR>
     */
    public Date deliveryDate;
    
    /**
     * (約定株数)<BR>
     * 約定ありの場合設定<BR>
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     * 約定ありの場合設定<BR>
     */
    public String execPrice;
    
    /**
     * (約定代金)<BR>
     * <BR>
     * 約定ありの場合設定<BR>
     */
    public String execTotalPrice;
    
    /**
     * (約定状態区分)<BR>
     * 0：未約定　@1：一部成立　@2：全部成立　@<BR>
     * <BR>
     * 約定ありの場合設定<BR>
     */
    public String execType;
    
    /**
     * (受渡代金)<BR>
     * 新規建　@　@ ：　@建代金<BR>
     * 返済　@　@ 　@ ：　@決済損益代金<BR>
     * 現引現渡　@：　@受渡代金<BR>
     */
    public String deliveryPrice;
    
    /**
     * (信用取引手数料情報)<BR>
     */
    public WEB3MarginCommissionInfoUnit commissionInfo;
    
    /**
     * (分割約定一覧)<BR>
     * 信用取引分割約定<BR>
     */
    public WEB3MarginExecuteUnit[] executeUnits;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;
    
    /**
     * (訂正取消区分)<BR>
     * 訂正取消区分 <BR>
     *（0:初期値 1:取消中 2:一部取消完了 3:全部取消完了 4:取消失敗 <BR>
　@   *  5:訂正中 6:一部訂正完了 7:全部訂正完了 8:訂正失敗 9:エラー）
     */
    public String changeCancelDiv;
    
    /**
     * (注文経路区分)<BR>
     * 注文経路区分<BR>
     * （1：コールセンター　@2：ＰＣ　@3:スリングショット　@4：i-mode　@5：Vodafone<BR>
　@   * 　@6：AU　@７：スリングショット(無料)　@9：HOST　@A：管理者<BR>
　@   * 　@B：保証金自動振替バッチ　@C：リッチクライアント　@F：IVR（自動応答電話）<BR>
　@   * 　@G：強制決済）<BR>
     */
    public String orderRootDiv;
    
    /**
     * (処理状況)<BR>
     * 「ﾒｯｾｰｼﾞ定義書_信用取引(共通).xls」<BR> 
     * 【処理状況区分定義】sheet参照。 <BR>
     */
    public String transactionStateType;
    
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
     * (強制決済理由)<BR>
     * 強制決済理由<BR>
     * <BR>
     * 0：　@決済期日到来<BR>
     * 1：　@保証金維持率割（オンライン開始前・軽度）<BR>
     * 2：　@保証金維持率割（オンライン開始前・重度）<BR>
     * 3：　@保証金維持率割（場間）<BR>
     * 9：　@手動強制決済<BR>
     * <BR>
     * ※強制決済注文でない場合はnullがセットされる。<BR>
     */
    public String forcedSettleReason = null;

    /**
     * (強制失効区分)<BR>
     * 強制失効区分<BR>
     * <BR>
     * 0：　@オープン<BR>
     * 1：　@強制失効済<BR>
     */
    public String forcedLapseDiv;

    /**
     * @@roseuid 4140485A037D
     */
    public WEB3MarginExecuteDetailsResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginExecuteDetailsResponse(WEB3MarginExecuteDetailsRequest l_request)
    {
        super(l_request);
    }
}
@
