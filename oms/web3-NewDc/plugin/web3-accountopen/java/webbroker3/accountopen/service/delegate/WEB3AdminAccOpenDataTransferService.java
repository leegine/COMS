head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設データ移管サービス(WEB3AdminAccOpenDataTransferService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 孟亞南(中訊) 新規作成 モデル 181
*/
package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者口座開設データ移管サービス)<BR>
 * 管理者口座開設データ移管サービスインタフェース<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public interface WEB3AdminAccOpenDataTransferService extends WEB3BusinessService
{
    /**
     * 口座開設データ移管処理を実施する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4A822362009C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
