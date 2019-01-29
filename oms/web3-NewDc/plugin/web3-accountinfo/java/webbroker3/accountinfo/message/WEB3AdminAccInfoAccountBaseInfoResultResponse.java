head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccountBaseInfoResultResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報顧客基本情報問合せレスポンス(WEB3AdminAccInfoAccountBaseInfoResultResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報顧客基本情報問合せレスポンス)<BR>
 * 管理者お客様情報顧客基本情報問合せレスポンス<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoAccountBaseInfoResultResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_accountBaseInfoResult";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082149L;

    /**
     * (顧客基本情報)<BR>
     * 顧客基本情報<BR>
     */
    public WEB3AccInfoAccountBaseInfo accountBaseInfo;

    /**
     * @@roseuid 418F38530290
     */
    public WEB3AdminAccInfoAccountBaseInfoResultResponse()
    {

    }

    /**
     * (管理者お客様情報顧客基本情報問合せレスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultResponse
     * @@roseuid 41368E7102A6
     */
    public WEB3AdminAccInfoAccountBaseInfoResultResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
