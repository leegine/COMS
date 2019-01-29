head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductDeleteConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄削除確認レスポンス(WEB3AdminIPOProductDeleteConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李頴淵 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄削除確認レスポンス)<BR>
 * 管理者IPO銘柄削除確認レスポンスデータクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIPOProductDeleteConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productDeleteConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161025L;
    
    /**
     * (銘柄情報)<BR>
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112E339020A
     */
    public WEB3AdminIPOProductDeleteConfirmResponse() 
    {
     
    }
    
    /**
     * (管理者IPO銘柄削除確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D141B60371
     */
    public WEB3AdminIPOProductDeleteConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
