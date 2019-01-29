head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddressRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設メールアドレス登録サービス(WEB3AccOpenMailAddressRegistService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 孟亞南 (中訊) 新規作成 モデル No.166
*/
package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (口座開設メールアドレス登録サービス)<BR>
 * 口座開設メールアドレス登録サービスインタフェイス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public interface WEB3AccOpenMailAddressRegistService extends WEB3BusinessService
{
    /**
     * 口座開設メールアドレス登録処理を実施する。<BR>
     * @@param l_request - (リクエスト)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
