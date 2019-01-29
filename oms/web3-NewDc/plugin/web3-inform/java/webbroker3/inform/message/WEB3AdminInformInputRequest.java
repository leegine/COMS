head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索入力リクエストクラス(WEB3AdminInformInputRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (連絡情報検索入力リクエスト)<BR>
 * 連絡情報検索入力リクエストクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformInputResponse(this);
    }
    
    /**
     * @@roseuid 41EE625B0290
     */
    public WEB3AdminInformInputRequest() 
    {
     
    }
}
@
