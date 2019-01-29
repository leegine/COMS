head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄訂正完了レスポンス(WEB3AdminIPOProductChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 彭巍 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄訂正完了レスポンス)<BR>
 * 管理者IPO銘柄訂正完了レスポンスデータクラス
 * 
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminIPOProductChangeCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_productChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408161024L;
    
    /**
     * @@roseuid 4112E37F032E
     */
    public WEB3AdminIPOProductChangeCompleteResponse() 
    {
     
    }
    
    /**
     * (管理者IPO銘柄訂正完了レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D1423F0277
     */
    public WEB3AdminIPOProductChangeCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
     
    }
}
@
