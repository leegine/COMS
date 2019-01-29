head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託管理者余力調整取消完了レスポンス(WEB3AdminMutualTPACancelCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 黄建 (中訊) 新規作成
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 *(投資信託管理者余力調整取消完了レスポンス)<BR>
 *投資信託管理者余力調整取消完了レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3AdminMutualTPACancelCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_tpa_cancel_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191632L;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminMutualTPACancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
