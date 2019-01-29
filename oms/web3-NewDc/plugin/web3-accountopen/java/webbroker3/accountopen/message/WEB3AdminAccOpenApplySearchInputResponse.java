head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplySearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込検索入力レスポンス(WEB3AdminAccOpenApplySearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設申込検索入力レスポンス)<BR>
 * 管理者口座開設申込検索入力レスポンス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplySearchInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_applySearchInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081619L;

    /**
     * (前営業日)<BR>
     * 前営業日<BR>
     */
    public Date previousBizDate;

    /**
     * (前日)<BR>
     * 前日<BR>
     */
    public Date previousDate;

    /**
     * @@roseuid 41B45E7B03B9
     */
    public WEB3AdminAccOpenApplySearchInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenApplySearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
