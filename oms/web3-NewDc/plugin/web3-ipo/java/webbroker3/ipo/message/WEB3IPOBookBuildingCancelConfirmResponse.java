head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング取消確認レスポンスクラス(WEB3IPOBookBuildingCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * IPOブックビルディング取消確認レスポンスクラス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOBookBuildingCancelConfirmResponse extends WEB3IPODemandCommonResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171845L;
    
    /**
     * 申告数量
     */
    public String demandQuantity;
    
    /**
     * 申告価格区分<BR>
     * <BR>
     * 0：　@成行<BR>
     * 1：　@指値
     */
    public String demandPriceDiv;
    
    /**
     * 申告価格
     */
    public String demandPrice;
    
    /**
     * 申告相当額
     */
    public String demandEquivalentPrice;
    
    /**
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4112EDC4025C
     */
    public WEB3IPOBookBuildingCancelConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D26D6303DF
     */
    public WEB3IPOBookBuildingCancelConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@
