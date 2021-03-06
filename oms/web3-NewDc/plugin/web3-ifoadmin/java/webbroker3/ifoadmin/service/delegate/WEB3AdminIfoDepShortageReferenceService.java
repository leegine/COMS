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
filename	WEB3AdminIfoDepShortageReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・証拠金不足状況照会サービス(WEB3AdminIfoDepShortageReferenceService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 李玉玲(中訊) 新規作成 モデルNo.005
*/
package webbroker3.ifoadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者・証拠金不足状況照会サービス)<BR>
 * 管理者・証拠金不足状況照会サービスインタフェイス<BR>
 * <BR>
 * @@author 李玉玲(中訊)
 * @@version 1.0
 */
public interface WEB3AdminIfoDepShortageReferenceService extends WEB3BusinessService
{

    /**
     * 管理者・証拠金不足状況照会を行う。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 498FFDDF02C3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
