head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄削除確認リクエスト(WEB3AdminIPOProductDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李頴淵 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄削除確認リクエスト)<BR>
 * 管理者IPO銘柄削除確認リクエストデータクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIPOProductDeleteConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productDeleteConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161024L;
    
    /**
     * (ＩＤ)<BR>
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * @@roseuid 4112E3390279
     */
    public WEB3AdminIPOProductDeleteConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E339028D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductDeleteConfirmResponse(this);
    }
}
@
