head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.42.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付確認レスポンス(WEB3BondApplyBuyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (債券応募/買付確認レスポンス)<BR>
 * 債券応募/買付確認レスポンス<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondApplyBuyConfirmResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyConfirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;          
    
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String id;
    
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
     * (確認時発注日)<BR>
     * 確認時発注日<BR>
     */
    public Date checkDate;
    
    /**
     * (債券応募/買付確認レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44C59BFA028C
     */
    public WEB3BondApplyBuyConfirmResponse() 
    {
     
    }
    
     /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondApplyBuyConfirmResponse(WEB3GenRequest l_request)
    {   
        super(l_request);   
    }       
}
@
