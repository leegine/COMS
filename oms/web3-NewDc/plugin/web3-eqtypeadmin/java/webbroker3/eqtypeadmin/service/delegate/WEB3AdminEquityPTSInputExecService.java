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
filename	WEB3AdminEquityPTSInputExecService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式（PTS）出来入力サービス（WEB3AdminEquityPTSInputExecService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/22 金傑 (中訊) 新規作成モデル177
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者・株式（PTS）出来入力サービス)<BR>
 * 管理者・株式（PTS）出来入力サービスインターフェイス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public interface WEB3AdminEquityPTSInputExecService extends WEB3BusinessService
{

    /**
     * 株式（PTS）出来入力処理を行う。<BR>
     * @@param l_request - （リクエストデータ）
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4790433D0396
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
