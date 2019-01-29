head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券取消確認レスポンス(WEB3BondCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 周捷 (中訊) 新規作成
Revesion History : 2007/07/25 謝旋 (中訊) モデルNo.220
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (債券取消確認レスポンス)<BR>
 * 債券取消確認レスポンス
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondCancelConfirmResponse extends WEB3GenResponse 
{

    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_cancel_confirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;  
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (売買単価)<BR>
     * 売買単価<BR>
     */
    public String buySellPrice;
    
    /**
     * (利率)<BR>
     * 利率<BR>
     */
    public String coupon;
    
    /**
     * (発行日)<BR>
     * 発行日<BR>
     */
    public Date issueDate;
    
    /**
     * (年間利払回数)<BR>
     * 年間利払回数<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (利払日１)<BR>
     * 利払日１<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (利払日２)<BR>
     * 利払日２<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (償還日)<BR>
     * 償還日<BR>
     */
    public Date maturityDate;
    
    /**
     * (取引区分)<BR>
     * 取引区分 <BR>
     *<BR>
     *1：応募 <BR>
     *2：買付 <BR>
     *3：売却 <BR>
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
     * (為替レート)<BR>
     * 為替レート <BR>
     */
    public String fxRate;
    
    /**
     * (額面金額)<BR>
     * 額面金額<BR>
     */
    public String faceAmount;
    
    /**
     * (売買代金（外貨）)<BR>
     * 売買代金（外貨）<BR>
     */
    public String foreignTradePrice;
    
    /**
     * (売買代金（円貨）)<BR>
     * 売買代金（円貨）<BR>
     */
    public String yenTradePrice;
    
    /**
     * (経過利子（外貨）)<BR>
     * 経過利子（外貨）<BR>
     */
    public String foreignAccruedInterest;
    
    /**
     * (経過利子（円貨）)<BR>
     * 経過利子（円貨）<BR>
     */
    public String yenAccruedInterest;
    
    /**
     * (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）<BR>
     */
    public String foreignDeliveryPrice;
    
    /**
     * (受渡代金（円貨）)<BR>
     * 受渡代金（円貨）<BR>
     */
    public String yenDeliveryPrice;
    
    /**
     * (注文日時)<BR>
     * 注文日時<BR>
     */
    public Date orderDate;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate;

    /**
     * (約定日)<BR>
     * 約定日<BR>
     */
    public Date executionUpdateDate;

    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;

    /**
     * (紹介区分)<BR>
     * 紹介区分 <BR>
     * <BR>
     * １：直接取引 <BR>
     * ２：単純紹介 <BR>
     * ３：商品紹介 <BR>
     * ４：仲介取引<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * (紹介店コード)<BR>
     * 紹介店コード<BR>
     */
    public String introduceStoreCode;
    
    /**
     * (債券取消確認レスポンス)<BR>
     * コンストラクタ<BR> 
     */
    public WEB3BondCancelConfirmResponse()
    {
    	
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
    
}
@
