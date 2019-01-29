head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券売却入力レスポンス(WEB3BondSellInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (債券売却入力レスポンス)<BR>
 * 債券売却入力レスポンス<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondSellInputResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_sellInput";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L; 
    
    /**
     * (売却可能額面金額)<BR>
     * 売却可能額面金額<BR>
     */
    public String sellAbleFaceAmount;
    
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
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (口座)<BR>
     * 口座<BR>
     */
    public String taxType;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (売却（評価）単価)<BR>
     * 売却（評価）単価<BR>
     */
    public String sellEvaluationPrice;
    
    /**
     * (発行日)<BR>
     * 発行日<BR>
     */
    public Date issueDate;
    
    /**
     * (償還日)<BR>
     * 償還日<BR>
     */
    public Date maturityDate;
    
    /**
     * (利払回数)<BR>
     * 利払回数<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (利払日１)<BR>
     * 利払日１<BR>
     * <BR>
     * mmdd<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (利払日２)<BR>
     * 利払日２<BR>
     * <BR>
     * mmdd<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (利率)<BR>
     * 利率<BR>
     */
    public String coupon;
    
    /**
     * (申込単位)<BR>
     * 申込単位<BR>
     */
    public String tradeUnit;
    
    /**
     * (売却基準為替)<BR>
     * 売却基準為替<BR>
     */
    public String sellBaseFx;
    
    /**
     * (決済区分一覧)<BR>
     * 決済区分一覧<BR>
     * <BR>
     * 1：円貨<BR>
     * 2：外貨<BR>
     */
    public String[] settleDivList;
    
    /**
     * (債券売却入力レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D7E6DC0233
     */
    public WEB3BondSellInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondSellInputResponse(WEB3GenRequest l_request)
    {   
        super(l_request);   
    } 
}
@
