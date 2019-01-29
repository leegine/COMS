head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyULService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込ULサービス(WEB3AdminAccOpenApplyULService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 武波(中訊) 新規作成 モデル No.148,No.152
*/

package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者口座開設申込ULサービス)<BR>
 * 管理者口座開設申込ULサービス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AdminAccOpenApplyULService extends WEB3BusinessService
{

    /**
     * 口座開設申込アップロード処理を行う。<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47299A18023F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
