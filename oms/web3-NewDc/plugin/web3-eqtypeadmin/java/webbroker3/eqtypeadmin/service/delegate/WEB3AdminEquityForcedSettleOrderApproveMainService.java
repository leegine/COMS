head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認メインサービス(WEB3AdminEquityForcedSettleOrderApproveMainService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデルNo.129
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (管理者・強制決済仮注文承認／非承認メインサービス)<BR>
 * 管理者・強制決済仮注文承認／非承認メインサービスインターフェイス<BR>
 * <BR>
 * （非同期処理を行う為のエントリークラス） <BR>
 * （トランザクション属性：TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleOrderApproveMainService extends WEB3BackBusinessService
{

    /**
     * （非同期）強制決済仮注文承認／非承認処理を起動する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 460327F902DE
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
