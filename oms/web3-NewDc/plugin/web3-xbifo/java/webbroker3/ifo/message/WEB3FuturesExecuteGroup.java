head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文約定照会注文単位クラス(WEB3FuturesExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李強(中訊) 新規作成
                   2006/07/28 張騰宇　@仕様変更　@モデル454,457,470,488
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.640
*/
package webbroker3.ifo.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (株価指数先物注文約定照会注文単位)<BR>
 * 株価指数先物注文約定照会注文単位クラス<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesExecuteGroup extends Message
{
    /**
     * (ID)<BR>
     * 注文ＩＤ<BR>
     */
    public String id;
    
    /**
     * (銘柄名)<BR>
     */
    public String futProductName;
    
    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪<BR>
     */
    public String marketCode;
    
    /**
     * (建日)<BR>
     */
    public Date openDate;
    
    /**
     * (建単価)<BR>
     */
    public String contractPrice;
    
    /**
     * (取引区分)<BR>
     * 3：新規買建注文　@4：新規売建注文<BR>
     * 5：買建返済注文（売返済）　@6：売建返済注文（買返済）<BR>
     */
    public String tradingType;
   
    /**
     * (注文時間)<BR>
     */
    public Date orderDate;
   
    /**
     * (発注日)<BR>
     */
    public Date orderBizDate;
    
    /**
     * (注文数量)<BR>
     */
    public String futOrderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * 注文単価区分が「指値」の場合設定<BR>
     */
    public String limitPrice;
    
    /**
     * (執行条件)<BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;

    /**
     * (逆指値用発注条件単価)<BR>
     * <BR>
     * 発注条件区分「逆指値」の場合設定<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (逆指値用発注条件演算子)<BR>
     * <BR>
     * 1：以下　@2：以上<BR>
     * 発注条件区分「逆指値」の場合設定<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (W指値用発注条件単価)<BR>
     * <BR>
     * 発注条件区分「W指値」の場合設定<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (W指値用発注条件演算子)<BR>
     * <BR>
     * 1：以下　@2：以上<BR>
     * 発注条件区分「W指値」の場合設定<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (W指値用注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値<BR>
     * 発注条件区分「W指値」の場合設定<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W指値用注文単価)<BR>
     * <BR>
     * 発注条件区分「W指値」の場合設定<BR>
     */
    public String wLimitPrice;
    
    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行 <BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効<BR>
     * 2：ストップ注文失効済 <BR>
     * <BR>
     * 発注条件区分または元発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitEnableStatusDiv;
    
    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、<BR>
     * 設定される<BR>
     */
    public String wlimitBefChgLimitPrice;
    
    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行 <BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、<BR>
     * 設定される<BR>
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
     * (約定数量)<BR>
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     */
    public String execPrice;
    
    /**
     * (概算建代金(決済損益))<BR>
     */
    public String estimatedContractPrice;

    /**
     * (建代金(決済損益))<BR>
     */
    public String contractExecPrice;

    /**
     * (処理状況)<BR>
     * ※コード値はメッセージ定義フォルダ以下の <BR>
     * 「ﾒｯｾｰｼﾞ定義書_株価指数先物(共通).xls」の処理状況区分定義を参照<BR>
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
     * (注文有効期限)<BR>
     */
    public Date expirationDate;
    
    /**
     * (注文チャネル)<BR>
     * 0：営業店　@1：インターネット　@2：コールセンタ　@3：モバイル<BR>
     * <BR>
     * ログインユーザがコールセンターの場合設定<BR>
     */
    public String orderChannel;
    
    /**
     * (注文経路区分)<BR>
     * 1：コールセンター　@2：ＰＣ　@3:スリングショット　@4：i-mode　@5：Vodafone　@6：AU　@9：HOST<BR>
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
     * (注文約定照会約定)<BR>
     */
    public WEB3FuturesExecuteUnit[] futExecuteUnits;
    
    /**
     * (訂正可能フラグ)<BR>
     * true：訂正可能　@　@false：訂正不可<BR>
     */
    public boolean changeFlag;
    
    /**
     * (取消可能フラグ)<BR>
     * true：取消可能　@　@false：取消不可<BR>
     */
    public boolean cancelFlag;
    
    /**
     * (銘柄コード)
     */
    public String futProductCode;

    /**
     * (指数種別)<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     */
    public String targetProductCode;

    /**
     * (限月)<BR>
     * YYYYMM形式<BR>
     */
    public String delivaryMonth;

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
     * @@roseuid 40F7AE10009C
     */
    public WEB3FuturesExecuteGroup() 
    {
     
    }
}
@
