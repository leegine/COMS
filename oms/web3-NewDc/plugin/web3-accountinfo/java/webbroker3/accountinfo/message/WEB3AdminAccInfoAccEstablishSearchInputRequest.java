head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索入力リクエスト(WEB3AdminAccInfoAccEstablishSearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  何文敏(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索入力リクエスト)<BR>
 * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索入力リクエスト<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0 
 */

public class WEB3AdminAccInfoAccEstablishSearchInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082163L;

    /**
     * @@roseuid 418F39EF0290
     */
    public WEB3AdminAccInfoAccEstablishSearchInputRequest()
    {

    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoAccEstablishSearchInputResponse(this);
    }
}
@
