head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文明細(WEB3SuccOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 呉艶飛(中訊) 新規作成
Revesion History : 2006/08/24 唐性峰(中訊) モデルNo.163 對應
Revesion History : 2006/08/30 柴雙紅(中訊) 仕様変更モデル165
Revesion History : 2006/11/23 徐宏偉(中訊) 仕様変更モデル183
Revesion History : 2007/06/05 柴双紅(中訊) 仕様変更モデル235
Revesion History : 2008/03/24 楊夫志(中訊) 仕様変更モデル289
*/
package webbroker3.triggerorder.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (連続注文明細)<BR>
 * 連続注文共通明細<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3SuccOrderUnit extends Message 
{
    
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     * <BR>
     * 親注文の場合、親注文の注文ID。<BR>
     * 子注文の場合、子注文の注文ID。<BR>
     */
    public String orderId;
    
    /**
     * (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     */
    public String commodityType;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (指数種別)<BR>
     * 指数種別<BR>
     * <BR>
     * 0005：　@TOPIX<BR>
     * 0018：　@日経225<BR>
     * 0016：　@日経300<BR>
     * 0019：　@ミニ日経225<BR>
     * <BR>
     * ※先物・オプションの場合セット。<BR>
     */
    public String targetProductCode = null;
    
    /**
     * (限月)<BR>
     * 限月<BR>
     * <BR>
     * ※先物・オプションの場合セット。<BR>
     */
    public String delivaryMonth = null;
    
    /**
     * (オプション商品区分)<BR>
     * オプション商品区分<BR>
     * <BR>
     * P：　@プットオプション<BR>
     * C：　@コールオプション<BR>
     * <BR>
     * ※オプションの場合セット。<BR>
     */
    public String opProductType = null;
    
    /**
     * (行使価格)<BR>
     * 行使価格<BR>
     * <BR>
     * ※オプションの場合セット。<BR>
     */
    public String strikePrice = null;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@一般<BR>
     * 1：　@特定<BR>
     * 5：　@ストックオプション<BR>
     * <BR>
     * ※株・信用の場合セット。<BR>
     */
    public String taxType = null;
    
    /**
     * (取引区分)<BR>
     * 取引区分<BR>
     * (注文単位.注文種別に相当)<BR>
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
     * (弁済区分)<BR>
     * 弁済区分<BR>
     * <BR>
     * 1：　@制度信用<BR>
     * 2：　@一般信用<BR>
     * <BR>
     * ※信用注文の場合セット。<BR>
     */
    public String repaymentDiv = null;
    
    /**
     * (弁済期限値)<BR>
     * 弁済期限値<BR>
     * <BR>
     * 月指定。<BR>
     * 無期限の場合”9999999”<BR>
     * <BR>
     * ※信用注文の場合セット。<BR>
     */
    public String repaymentTimeLimit = null;
    
    /**
     * (値段条件)<BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値<BR>
     * 7:成行残数取消<BR>
     * <BR>
     * ※株・信用の場合セット。<BR>
     */
    public String priceCondType = null;
    
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
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * (逆指値用プレミアム/原資産価格)<BR>
     * 0：原資産価格　@1：プレミアム<BR>
     * <BR>
     * ※発注条件区分が「逆指値」かつ先物・OPの場合にセット<BR>
     */
    public String stopPremium_underlyingAssets;
    
    /**
     * (逆指値用発注条件単価)<BR>
     * 0：原資産価格　@1：プレミアム<BR>
     * <BR>
     * ※発注条件区分が「逆指値」の場合にセット<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (逆指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * <BR>
     * ※発注条件区分が「逆指値」の場合にセット<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (Ｗ指値用プレミアム/原資産価格)<BR>
     * 0：原資産価格　@1：プレミアム<BR>
     * <BR>
     * ※発注条件区分が「W指値」かつ先物・OPの場合にセット<BR>
     */
    public String wlimitPremium_underlyingAssets;
    
    /**
     * (Ｗ指値用発注条件単価)<BR>
     * 0：原資産価格　@1：プレミアム<BR>
     * <BR>
     * ※発注条件区分が「W指値」の場合にセット<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * <BR>
     * ※発注条件区分が「W指値」の場合にセット<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * <BR>
     * ※発注条件区分が「W指値」の場合にセット<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (Ｗ指値用注文単価)<BR>
     * <BR>
     * ※W指値用注文単価区分が「指値」の場合にセット<BR>
     */
    public String wLimitPrice;

    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：　@無条件<BR>
     * 3：　@寄付<BR>
     * 4：　@引け<BR>
     * 7：　@不出来引け成行<BR>
     * <BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
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
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (W指値用切替前執行条件)<BR>
     * 1：　@無条件 <BR>
     * 3：　@寄付 <BR>
     * 4：　@引け <BR>
     * 7：　@不出来引け成行 <BR>
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
     * 1：以上　@2：以下<BR>
     */
    public String orgOrderCondOperator;

    /**
     * (元Ｗ指値用注文単価区分)
     * 0：成行　@1：指値 
     * ※元発注条件区分が2：Ｗ指値の場合
     */
    public String orgWlimitOrderPriceDiv;

    /**
     * (元Ｗ指値用注文単価)<BR>
     * ※元Ｗ指値用注文単価区分が、1：指値の場合設定される。<BR>
     */
    public String orgWlimitPrice;

    /**
     * (元Ｗ指値用執行条件)<BR>
     * 1：　@無条件 <BR>
     * 3：　@寄付 <BR>
     * 4：　@引け <BR>
     * 7：　@不出来引け成行<BR>
     * <BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWlimitExecCondType;

    /**
     * (元プレミアム／原資産価格)<BR>
     * 0：原資産価格　@1：プレミアム<BR>
     * <BR>
     * 発注条件区分「0：DEFAULT」以外の場合設定<BR>
     * ※先物OPの場合のみ<BR>
     */
    public String orgPremium_underlyingAssets;

    /**
     * (注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     */
    public String orderQuantity;
    
    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     * <BR>
     * ※成行の場合、0をセット。<BR>
     */
    public String orderPrice;
    
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
     * (注文有効期限)<BR>
     * 注文有効期限<BR>
     * <BR>
     * ※出来るまで注文の場合セット。<BR>
     */
    public Date expirationDate = null;
    
    /**
     * (受渡代金)<BR>
     * 受渡代金<BR>
     * <BR>
     * ※約定ありの場合セット。<BR>
     */
    public String deliveryPrice = null;
    
    /**
     * (処理状況)<BR>
     * 処理状況<BR>
     * <BR>
     * ※設定内容は以下のファ@イルの<BR>
     * 　@処理状況区分定義シートを参照。<BR>
     * 　@現物：「ﾒｯｾｰｼﾞ定義書_現物株式(共通).xls」<BR>
     * 　@信用：「ﾒｯｾｰｼﾞ定義書_信用取引(共通).xls」<BR>
     * 　@先物：「ﾒｯｾｰｼﾞ定義書_株価指数先物(共通).xls」<BR>
     * 　@OP：「ﾒｯｾｰｼﾞ定義書_株価指数オプション(共通).xls」
     */
    public String transactionStateType;
    
    /**
     * (連続注文可能フラグ)<BR>
     * 連続注文可能フラグ<BR>
     * <BR>
     * true：　@連続注文可能<BR>
     * false：　@連続注文不可<BR>
     */
    public boolean succOrderFlag = true;
    
    /**
     * (訂正可能フラグ)<BR>
     * 訂正可能フラグ<BR>
     * <BR>
     * true：　@訂正可能<BR>
     * false：　@訂正不可<BR>
     */
    public boolean changeFlag = true;
    
    /**
     * (取消可能フラグ)<BR>
     * 取消可能フラグ<BR>
     * <BR>
     * true：　@取消可能<BR>
     * false：　@取消不可<BR>
     * <BR>
     * 現引現渡については訂正フラグと非同期で設定される。<BR>
     */
    public boolean cancelFlag = true;
    
    /**
     * (予約注文一覧)<BR>
     * 予約注文一覧
     */
    public WEB3SuccReservationOrderUnit[] reservationOrderList = null;

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
     * <BR>
     * 0：　@オープン<BR>
     * 1：　@強制失効済<BR>
     */
    public String forcedLapseDiv = null;

    /**
     * (夕場前繰越対象フラグ)<BR>
     * false：夕場前繰越なし<BR>
     * true：夕場前繰越あり<BR>
     */
    public boolean eveningSessionCarryoverFlag = false;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType = null;

    /**
     * (連続注文明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 431F846F0265
     */
    public WEB3SuccOrderUnit() 
    {
     
    }
}
@
