head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ロック登録解除受付リクエスト(WEB3AccInfoLockRegistReleaseAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (ロック登録解除受付リクエスト)<BR>
 * ロック登録解除受付リクエスト<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockRegistReleaseAcceptRequest extends WEB3BackRequest 
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
     * (コンストラクタ)
     * コンストラクタ
     */
    public WEB3AccInfoLockRegistReleaseAcceptRequest()
    {

    }
    
    /**
     * ロック登録解除受付レスポンスオブジェクトを生成して返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AccInfoLockRegistReleaseAcceptResponse(this);
    }
}
@
