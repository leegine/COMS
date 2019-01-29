head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング訂正入力レスポンスクラス(WEB3IPOBookBuildingChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 劉江涛(中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * IPOブックビルディング訂正入力レスポンスクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingChangeInputResponse extends WEB3IPODemandCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171446L;
    
    /**
     * 出金余力
     */
    public String paymentPower;
    
    /**
     * 申告数量
     */
    public String demandQuantity;
    
    /**
     * 申告価格区分<BR>
     * <BR>
     * 0：　@成行<BR>
     * 1：　@指値<BR>
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
     * @@roseuid 4112EC1B00F2
     */
    public WEB3IPOBookBuildingChangeInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D16DD9016F
     */
    public WEB3IPOBookBuildingChangeInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
