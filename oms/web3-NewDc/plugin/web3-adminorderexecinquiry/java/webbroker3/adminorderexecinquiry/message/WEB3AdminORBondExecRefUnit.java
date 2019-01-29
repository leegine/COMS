head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会行(WEB3AdminORBondExecRefUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏(中訊) 新規作成    
Revesion History : 2007/07/9 劉立峰(中訊) 仕様変更モデルNo.100
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (債券管理者注文約定照会行)<BR>
 * 債券管理者注文約定照会表示するときの行データを保存
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefUnit extends Message
{
    
    /**
     * (ロック解除ボタン区分)<BR>
     * ロック解除ボタン区分<BR>
     * <BR>
     * 0：非表示 1：解除ボタン 2：ロックボタン
     */
    public String lockDiv;
    
    /**
     * (約定変更ボタン区分)<BR>
     * 約定変更ボタン区分<BR>
     * <BR>
     * 0：非表示　@1：約定ボタン　@2：変更ボタン
     */
    public String execChgDiv;
    
    /**
     * (取消ボタン区分)<BR>
     * 取消ボタン区分<BR>
     * <BR>
     * 0：非表示　@1：取消ボタン
     */
    public String cancelDiv;
    
    /**
     * (注文ID)<BR>
     * 注文ID
     */
    public String id;
    
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
     * (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名
     */
    public String productName;
    
    /**
     * (_注文種別)<BR>
     * 注文種別<BR>
     * <BR>
     * 401：買付　@402：売却　@404：応募
     */
    public String tradingType;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 1：円貨　@2：外貨
     */
    public String settleDiv;
    
    /**
     * (注文数量)<BR>
     * 注文数量
     */
    public String orderQuantity;
    
    /**
     * (約定数量)<BR>
     * 約定数量
     */
    public String execQuantity;
    
    /**
     * (指値)<BR>
     * 指値
     */
    public String limitOrderPrice;
    
    /**
     * (約定単価)<BR>
     * 約定単価
     */
    public String execPrice;
    
    /**
     * (売買代金（円貨）)<BR>
     * 売買代金（円貨）
     */
    public String yenTradePrice;
    
    /**
     * (経過利子（円貨）)<BR>
     * 経過利子（円貨）
     */
    public String yenAccruedInterest;
    
    /**
     * (受渡代金（円貨）)<BR>
     * 受渡代金（円貨）
     */
    public String yenDeliveryPrice;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;
    
    /**
     * (為替レート)<BR>
     * 為替レート
     */
    public String fxRate;
    
    /**
     * (売買代金（外貨）)<BR>
     * 売買代金（外貨）
     */
    public String foreignTradePrice;
    
    /**
     * (経過利子（外貨）)<BR>
     * 経過利子（外貨）
     */
    public String foreignAccruedInterest;
    
    /**
     * (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）
     */
    public String foreignDeliveryPrice;
    
    /**
     * (受注日時)<BR>
     * 受注日時
     */
    public Date acceptOrderTimeStamp;
    
    /**
     * (発注日)<BR>
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * (約定日)<BR>
     * 約定日
     */
    public Date domesticExecutionDate;
    
    /**
     * (現地約定日)<BR>
     * 現地約定日
     */
    public Date foreignExecutionDate;
    
    /**
     * (受渡日)<BR>
     * 受渡日
     */
    public Date domesticDeliveryDate;
    
    /**
     * (現地受渡日)<BR>
     * 現地受渡日
     */
    public Date foreignDeliveryDate;
    
    /**
     * (注文約定区分)<BR>
     * 注文約定区分<BR>
     * <BR>
     * 0：未約定（注文済）　@1：約定済　@2：取消済
     */
    public String executionState;
    
    /**
     * (注文経路区分)<BR>
     * 注文経路区分<BR>
     * <BR>
     * 1：コールセンター　@2：ＰＣ　@9：HOST A：管理者
     */
    public String orderRootDiv;
    
    /**
     * (初回注文の注文チャネル)<BR>
     * 初回注文の注文チャネル<BR>
     * <BR>
     * 0:営業店　@1：インターネット　@2：コールセンター
     */
    public String orderChannel;
    
    /**
     * (オペレータコード)<BR>
     * オペレータコード
     */
    public String operatorCode;
    
    /**
     * (扱者コード（SONAR）)<BR>
     * 扱者コード（SONAR）
     */
    public String sonarTraderCode;
    
    /**
     * (紹介店区分)<BR>
     * 紹介店区分
     */
    public String introduceStoreDiv;
    
    /**
     * (紹介店コード)<BR>
     * 紹介店コード
     */
    public String introduceStoreCode;
    
    /**
     * (管理者コード)<BR>
     * 管理者コード
     */
    public String administratorCode;
    
    /**
     * (債券管理者注文約定照会行)<BR>
     * コンストラクタ。
     * @@roseuid 44CFF7FF007D
     */
    public void WEB3AdminBondExecRefUnit() 
    {
     
    }
}
@
