head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文約定照会明細(WEB3BondExecuteReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 周捷 (中訊) 新規作成
Revesion History : 2007/07/09 周墨洋 (中訊) モデルNo.191
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (債券注文約定照会明細)<BR>
 * 債券注文約定照会明細
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceDetailUnit extends Message
{
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String orderId;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (種別コード)<BR>
     * 種別コード<BR>
     */
    public String bondCategCode;
    
    /**
     * (取引区分)<BR>
     * 取引区分 <BR>
     * <BR>
     * 1：応募 <BR>
     * 2：買付 <BR>
     * 3：売却<BR>
     */
    public String stateDiv;
    
    /**
     * (決済区分)<BR>
     * 決済区分 <BR>
     * <BR>
     * 1：円貨 <BR>
     * 2：外貨<BR>
     */
    public String settleDiv;
    
    /**
     * (額面金額)<BR>
     * 額面金額<BR>
     */
    public String faceAmount;
    
    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     */
    public String orderPrice;
    
    /**
     * (売買代金（円貨）)<BR>
     * 売買代金（円貨）<BR>
     */
    public String yenTradePrice;
    
    /**
     * (経過利子（円貨）)<BR>
     * 経過利子（円貨）<BR>
     */
    public String yenAccruedInterest;
    
    /**
     * (受渡代金（円貨）)<BR>
     * 受渡代金（円貨）<BR>
     */
    public String yenDeliveryPrice;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (為替レート)<BR>
     * 為替レート<BR>
     */
    public String fxRate;
    
    /**
     * (売買代金（外貨）)<BR>
     * 売買代金（外貨）<BR>
     */
    public String foreignTradePrice;
    
    /**
     * (経過利子（外貨）)<BR>
     * 経過利子（外貨）<BR>
     */
    public String foreignAccruedInterest;
    
    /**
     * (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）<BR>
     */
    public String foreignDeliveryPrice;
    
    /**
     * (注文日時)<BR>
     * 注文日時<BR>
     */
    public Date orderDate;
    
    /**
     * (約定日)<BR>
     * 約定日<BR>
     */
    public Date domesticExecutionDate;
    
    /**
     * (現地約定日)<BR>
     * 現地約定日<BR>
     */
    public Date foreignExecutionDate;
    
    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date domesticDeliveryDate;
    
    /**
     * (現地受渡日)<BR>
     * 現地受渡日<BR>
     */
    public Date foreignDeliveryDate;
    
    /**
     * (注文状態)<BR>
     * 注文状態 <BR>
     * <BR>
     * 0：未約定（注文済）<BR>
     * 1：約定済<BR>
     * 2：取消済<BR>
     */
    public String executionState;
    
    /**
     * (注文経路区分)<BR>
     * 注文経路区分 <BR>
     * <BR>
     * 1：コールセンター <BR>
     * 2：ＰＣ <BR>
     * 3:スリングショット <BR>
     * 4：i-mode <BR>
     * 5：Vodafone <BR>
     * 6：AU <BR>
     * 9：HOST <BR>
     * A：管理者<BR>
     */
    public String orderRootDiv;
    
    /**
     * (注文チャネル)<BR>
     * 注文チャネル <BR>
     * <BR>
     * 0：営業店 <BR>
     * 1：インターネット <BR>
     * 2：コールセンタ <BR>
     * 3：モバイル<BR>
     */
    public String orderChannel;
    
    /**
     * (オペレータコード)<BR>
     * オペレータコード<BR>
     */
    public String operatorCode;
    
    /**
     * (取消可能フラグ)<BR>
     * 取消可能フラグ <BR>
     * <BR>
     * true：取消可能 <BR>
     * false：取消不可<BR>
     */
    public boolean cancelDiv;
    
    /**
     * (債券注文約定照会明細)<BR>
     * デフォルトコンストラクタ<BR> 
     */
    public WEB3BondExecuteReferenceDetailUnit()
    {
        
    }
}
@
