head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOOfferStopResumeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO募集停止再開完了レスポンスクラス(WEB3AdminIPOOfferStopResumeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/


package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO募集停止再開完了レスポンスクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOOfferStopResumeCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_offerStopResumeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131152L;
    
    /**
     * @@roseuid 4112DAD30321
     */
    public WEB3AdminIPOOfferStopResumeCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D1413E0044
     */
    public WEB3AdminIPOOfferStopResumeCompleteResponse(WEB3GenRequest l_request) 
    {

        super(l_request);

    }
}
@
