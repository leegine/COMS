head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託海外市場カレンダー登録完了レスポンス(WEB3AdminMutualFrgncalCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 王蘭芬(中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 投資信託海外市場カレンダー登録完了レスポンスクラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalCompleteResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_mutual_frgncal_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408151343L;

    /**
     * デフォルトコンストラクタ
     * @@roseuid 40CED0890193
     */
    public WEB3AdminMutualFrgncalCompleteResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminMutualFrgncalCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
