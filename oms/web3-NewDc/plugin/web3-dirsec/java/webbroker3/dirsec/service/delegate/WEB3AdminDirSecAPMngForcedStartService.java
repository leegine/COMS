head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者下り処理強制起動サービス(WEB3AdminDirSecAPMngForcedStartService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/21 劉剣 (中訊) 新規作成モデル 132
*/

package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者下り処理強制起動サービス)<BR>
 * 管理者下り処理強制起動サービスインタフェイス。<BR>
 *
 * @@author 劉剣
 * @@version 1.0
 */
public interface WEB3AdminDirSecAPMngForcedStartService extends WEB3BusinessService
{
    
    /**
     * 管理者・下り処理強制起動を開始する。<BR>
     * @@param l_request - リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4875966D01F8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
