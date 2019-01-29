head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ロック登録解除受付レスポンス(WEB3AccInfoLockRegistReleaseAcceptResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (ロック登録解除受付レスポンス)<BR>
 * ロック登録解除受付レスポンス<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockRegistReleaseAcceptResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_lockRegistReleaseAccept";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509191350L;
    
    /**
     * (ロック登録解除受付レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputResponse
     * @@roseuid 416657DA0191
     */
    protected WEB3AccInfoLockRegistReleaseAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
