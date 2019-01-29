head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索入力レスポンス(WEB3AdminAccInfoAccEstablishSearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  何文敏(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索入力レスポンス)<BR>
 * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索入力レスポンス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0 
 */

public class WEB3AdminAccInfoAccEstablishSearchInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_input";
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082152L;

    /**
     * @@roseuid 418F39F700AB
     */
    public WEB3AdminAccInfoAccEstablishSearchInputResponse()
    {

    }
    
    /**
     * (口座開設日（自）)<BR>
     * 口座開設日（自）<BR> 
     */
    public Date accountOpenDateFrom;
    
    /**
     * (口座開設日（至）)<BR>
     * 口座開設日（至）<BR> 
     */
    public Date accountOpenDateTo;
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccInfoAccEstablishSearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
