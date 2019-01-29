head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報通知サービス(WEB3AdminEquityAttentionInfoNotifyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 劉剣 (中訊) 新規作成 モデルNo.219
*/

package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (注意情報通知サービス)<BR>
 * 注意情報通知サービスインターフェイス<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public interface WEB3AdminEquityAttentionInfoNotifyService extends WEB3BackBusinessService
{

    /**
     * 注意情報通知サービス処理を実施する。<BR>
     * @@param l_request - リクエストデータオブジェクト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 49377C740007
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}@
