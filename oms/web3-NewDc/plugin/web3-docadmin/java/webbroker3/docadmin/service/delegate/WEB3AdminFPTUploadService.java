head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTUploadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧アップロードサービス(WEB3AdminFPTUploadService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 武波(中訊) 新規作成 モデル No.012
*/

package webbroker3.docadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者金商法@交付閲覧アップロードサービス)<BR>
 * 管理者金商法@交付閲覧アップロードサービス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AdminFPTUploadService extends WEB3BusinessService
{

    /**
     * 金商法@交付閲覧アップロード処理を行う。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F204017F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
