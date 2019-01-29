head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.38.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会明細(WEB3BondBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (債券残高照会明細)<BR>
 * 債券残高照会明細<BR>
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceDetailUnit extends Message
{
    /**
     * 保有資産ID<BR>
     */
    public String id;
    
    /**
     * (債券タイプ )<BR>
     * 債券タイプ <BR>
     * <BR>
     * 0：国債<BR>
     * 4：外国債券<BR>
     * 8：WB<BR>
     * 9：CB<BR>
     * 10：国内債券<BR>
     */
    public String bondKind;
    
    /**
     * (種別)<BR>
     * 種別<BR>
     */
    public String bondCategCode;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (回号コード)<BR>
     * 回号コード<BR>
     */
    public String productIssueCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     */
    public String taxType;
    
    /**
     * (売却可能数量)<BR>
     * 売却可能数量<BR>
     */
    public String sellAbleQty;
    
    /**
     * (売却不能数量)<BR>
     * 売却不能数量<BR>
     */
    public String sellDisableQty;
    
    /**
     * (通貨)<BR>
     * 通貨<BR>
     */
    public String currencyCode;
    
    /**
     * (売却（評価）単価)<BR>
     * 売却（評価）単価<BR>
     */
    public String sellEvaluationPrice;
    
    /**
     * (概算評価額（円貨）)<BR>
     * 概算評価額（円貨）<BR>
     */
    public String yenEstimatedAsset;
    
    /**
     * (概算評価額（外貨）)<BR>
     * 概算評価額（外貨）<BR>
     */
    public String foreignEstimatedAsset;
    
    /**
     * (発行日)<BR>
     * 発行日<BR>
     */
    public Date issueDate;
    
    /**
     * (発行価格)<BR>
     * 発行価格<BR>
     */
    public String issuePrice;
    
    /**
     * (償還日)<BR>
     * 償還日<BR>
     */
    public Date maturityDate;
    
    /**
     * (年間利払い回数)<BR>
     * 年間利払い回数<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (利払日１)<BR>
     * 利払日１（MM/dd形式））<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (利払日２)<BR>
     * 利払日２（MM/dd形式））<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (年利率)<BR>
     * 年利率<BR>
     */
    public String coupon;
       
    /**
     * (売却可能区分)<BR>
     * 売却可能区分<BR>
     * <BR>
     * 0：不可<BR>
     * 1：可<BR>
     */
    public String sellPossDiv;
    
    /**
     * (債券残高照会明細)<BR>
     * コンストラクタ<BR>
     */
    public WEB3BondBalanceReferenceDetailUnit()
    {
        
    }

}
@
