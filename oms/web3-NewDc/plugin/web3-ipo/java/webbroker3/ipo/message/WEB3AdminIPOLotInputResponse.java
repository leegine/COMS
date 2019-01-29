head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選割当入力レスポンス(WEB3AdminIPOLotInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 鄭徳懇 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (管理者IPO抽選割当入力レスポンス)<BR>
 *  管理者IPO入力レスポンスクラス。<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminIPOLotInputResponse extends WEB3IPOLotCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512192100L;
    
    /**
     * 割当可能数量
     */
    public String allotAbleQuantity;
    
    /**
     * @@roseuid 4112DAD600B9
     */
    public WEB3AdminIPOLotInputResponse() 
    {
     
    }
    
    /**
     * (管理者IPO抽選割当入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D140A203AF
     */
    public WEB3AdminIPOLotInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
