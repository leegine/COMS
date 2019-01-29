head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券売却確認レスポンス(WEB3BondSellConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (債券売却確認レスポンス)<BR>
 * 債券売却確認レスポンス<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondSellConfirmResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_sellConfirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;  
    
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
     * (概算受渡代金（外貨）)<BR>
     * 概算受渡代金（外貨）<BR>
     */
    public String foreignEstDeliveryPrice;
    
    /**
     * (概算受渡代金（円貨）)<BR>
     * 概算受渡代金（円貨）<BR>
     */
    public String yenEstDeliveryPrice;
    
    /**
     * (注文ID)<BR>
     * 注文ＩＤ<BR>
     */
    public String orderId;
    
    /**
     * (確認時発注日)<BR>
     * 確認時発注日<BR>
     */
    public Date checkDate;
    
    /**
     * (債券売却確認レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D7FE880269
     */
    public WEB3BondSellConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondSellConfirmResponse(WEB3GenRequest l_request)
    {   
        super(l_request);   
    }
}
@
