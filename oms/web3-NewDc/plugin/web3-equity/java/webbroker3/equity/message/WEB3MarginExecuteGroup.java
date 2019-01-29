head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文約定照会注文単位クラス(WEB3MarginExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
Revesion History : 2006/07/05 肖志偉 (中訊) 仕様変更941
Revesion History : 2006/11/02 張騰宇(中訊) モデル 948,999
Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更・モデル1164
Revesion History : 2007/07/27 何文敏(中訊) モデル No.1184
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （信用取引注文約定照会注文単位）。<br>
 * <br>
 * 信用取引注文約定照会注文単位クラス
 * @@version 1.0
 */
public class WEB3MarginExecuteGroup extends Message  
{

    
    /**
     * (注文ＩＤ)<BR>
     */
    public String id;
    
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
     */
    public String tradingType;
    
    /**
     * (弁済)<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (建日)<BR>
     */
    public Date openDate;
    
    /**
     * (建単価)<BR>
     */
    public String contractPrice;
    
    /**
     * (値段条件)<BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値　@ 7:成行残数取消
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
     * （逆指値用発注条件単価）<BR>
     * 発注条件区分「1：逆指値」の場合設定<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * （逆指値用発注条件演算子）<BR>
     * 1：以下　@2：以上<BR>
     * 発注条件区分「1：逆指値」の場合設定<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * （Ｗ指値用発注条件単価）<BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * （Ｗ指値用発注条件演算子）<BR>
     * 1：以下　@2：以上<BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * （Ｗ指値用注文単価区分）<BR>
     * 0：成行　@1：指値 <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * （Ｗ指値用注文単価）<BR>
     * Ｗ指値用注文単価区分「1：指値」の場合設定<BR>
     */
    public String wLimitPrice;

    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分が、「2：W指値」の場合設定される <BR>
     */
    public String wlimitExecCondType;

    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効<BR>
     * 2：ストップ注文失効済<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される <BR>
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
     * ※元Ｗ指値用注文単価区分が、1：指値の場合設定される<BR>
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
     * (約定株数)<BR>
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     */
    public String execPrice;
    
    /**
     * (概算受渡代金)<BR>
     * 新規建　@　@ ：　@概算建代金<BR>
     * 返済　@　@　@　@：　@概算決済損益代金<BR>
     * 現引現渡　@：　@概算受渡代金<BR>
     */
    public String estimatedPrice;
    
    /**
     * (受渡代金)<BR>
     * 受渡代金<BR>
     */
    public String deliveryPrice;
    
    /**
     * (注文時間)<BR>
     */
    public Date orderDate;
    
    /**
     * (発注日)
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * (注文有効期限)<BR>
     */
    public Date expirationDate;
    
    /**
     * (処理状況)<BR>
     * 000：受付未済/未約定/訂正取消なし<BR>
     * 003：受付未済/未約定/全部取消完了<BR>
     * 007：受付未済/未約定/全部訂正完了<BR>
     * 100：受付済/未約定/訂正取消なし<BR>
     * 101：受付済/未約定/取消中<BR>
     * 103：受付済/未約定/全部取消完了<BR>
     * 104：受付済/未約定/取消失敗<BR>
     * 105：受付済/未約定/訂正中<BR>
     * 107：受付済/未約定/全部訂正完了<BR>
     * 108：受付済/未約定/訂正失敗<BR>
     * 109：受付済/未約定/訂正取消エラー<BR>
     * 110：受付済/一部約定/訂正取消なし<BR>
     * 111：受付済/一部約定/取消中<BR>
     * 114：受付済/一部約定/取消失敗<BR>
     * 115：受付済/一部約定/訂正中<BR>
     * 116：受付済/一部約定/一部訂正完了<BR>
     * 118：受付済/一部約定/訂正失敗<BR>
     * 119：受付済/一部約定/訂正取消エラー<BR>
     * 120：受付済/全部約定/訂正取消なし<BR>
     * 124：受付済/全部約定/取消失敗<BR>
     * 126：受付済/全部約定/一部訂正完了<BR>
     * 127：受付済/全部約定/全部訂正完了<BR>
     * 128：受付済/全部約定/訂正失敗<BR>
     * 129：受付済/全部約定/訂正取消エラー<BR>
     * 130：受付済/失効/訂正取消なし<BR>
     * 134：受付済/失効/取消失敗<BR>
     * 136：受付済/失効/一部訂正完了<BR>
     * 137：受付済/失効/全部訂正完了<BR>
     * 138：受付済/失効/訂正失敗<BR>
     * 139：受付済/失効/訂正取消エラー<BR>
     * 140：受付済/一部失効/訂正取消なし<BR>
     * 144：受付済/一部失効/取消失敗<BR>
     * 146：受付済/一部失効/一部訂正完了<BR>
     * 148：受付済/一部失効/訂正失敗<BR>
     * 149：受付済/一部失効/訂正取消エラー<BR>
     * 200：受付エラー/未約定/訂正取消なし<BR>
     * <BR>
     */
    public String transactionStateType;
    
    /**
     * (訂正可能フラグ)<BR>
     * true：訂正可能　@　@false：訂正不可<BR>
     */
    public boolean changeFlag;
    
    /**
     * (取消可能フラグ)<BR>
     * true：取消可能　@　@false：取消不可<BR>
     * <BR>
     * 現引現渡については訂正フラグと非同期で設定される。<BR>
     */
    public boolean cancelFlag;
    
    /**
     * (注文チャネル)<BR>
     * 0：営業店　@1：インターネット　@2：コールセンタ　@3：モバイル<BR>
     * <BR>
     * ログインユーザがコールセンターの場合設定<BR>
     */
    public String orderChannel;
    
    /**
     * (注文経路区分)<BR>
     * 1：コールセンター　@2：ＰＣ　@3:スリングショット　@4：i-mode　@<BR>
     * 5：Vodafone　@6：AU　@7：スリングショット（無料）　@9：HOST<BR>
     * A：管理者　@B：保証金自動振替バッチ　@C：リッチクライアント<BR>
     * F：IVR（自動応答電話）　@G：強制決済<BR>
     * <BR>
     * ログインユーザがコールセンターの場合設定<BR>
     */
    public String orderRootDiv;
    
    /**
     * (オペレータコード)<BR>
     * ログインユーザがコールセンターの場合設定<BR>
     */
    public String operatorCode;
    
    /**
     * (分割約定一覧)<BR>
     * 信用取引分割約定<BR>
     */
    public WEB3MarginExecuteUnit[] executeUnits;

    /**
     * (遅延区分)<BR>
     * 0：正常　@1：遅延<BR>
     * <BR>
     * 発注条件区分が、「1：逆指値」または「2：W指値」の場合設定される<BR>
     */
    public String delayDiv;

    /**
     * (手動発注可能フラグ)<BR>
     * true：手動発注可能　@　@false：手動発注不可 <BR>
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
     * @@roseuid 414048CA0067
     */
    public WEB3MarginExecuteGroup() 
    {
     
    }
}
@
