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
filename	WEB3AdminEquityAttentionInfoReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注意情報照会サービス(WEB3AdminEquityAttentionInfoReferenceService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 李キョウ(中訊) 新規作成 仕様変更モデルNo.216
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者注意情報照会サービス)<BR>
 * 管理者注意情報照会サービスインターフェイス<BR>
 * <BR>
 * @@author 李キョウ
 * @@version 1.0
 */
public interface WEB3AdminEquityAttentionInfoReferenceService extends WEB3BusinessService
{

    /**
     * 注意情報照会サービスを行う。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4942266F037A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
