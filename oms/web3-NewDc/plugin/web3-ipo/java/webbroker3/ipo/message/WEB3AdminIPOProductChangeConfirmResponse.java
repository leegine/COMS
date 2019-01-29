head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄訂正確認レスポンス(WEB3AdminIPOProductChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 彭巍 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄訂正確認レスポンス)<BR>
 * 管理者IPO銘柄訂正確認レスポンスデータクラス
 * 
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminIPOProductChangeConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_productChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408161034L;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * @@roseuid 4112E37F0215
     */
    public WEB3AdminIPOProductChangeConfirmResponse() 
    {
     
    }
    
    /**
     * (管理者IPO銘柄訂正確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D1423103DE
     */
    public WEB3AdminIPOProductChangeConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
     
    }
}
@
