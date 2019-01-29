head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOOfferStopResumeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO募集停止再開確認レスポンスクラス(WEB3AdminIPOOfferStopResumeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO募集停止再開確認レスポンスクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOOfferStopResumeConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_offerStopResumeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131155L;
    
    /**
     * IPO募集状態区分<BR>
     * 　@true：　@取扱中<BR>
     * 　@false：　@取扱停止中<BR>
     */
    public boolean offerStateDiv;
    
    /**
     * (銘柄情報)
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112DAD30227
     */
    public WEB3AdminIPOOfferStopResumeConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D14124016D
     */
    public WEB3AdminIPOOfferStopResumeConfirmResponse(WEB3GenRequest l_request) 
    {
        
        super(l_request); 
            
    }
}
@
