head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualExpireMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効メインサービス (WEB3AdminIfoManualExpireMainService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/

package webbroker3.ifoadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (管理者・先物OP手動失効メインサービス )<BR>
 * 管理者・先物OP手動失効メインサービス インターフェイス <BR>
 *（非同期処理を行う為のエントリークラス） <BR>
 *（トランザクション属性：TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */

public interface WEB3AdminIfoManualExpireMainService extends WEB3BackBusinessService 
{
    /**
     * （非同期）先物OP手動失効処理を起動する。
     * @@param l_request - リクエスト
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 44693554034E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
