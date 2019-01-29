head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationDateListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文有効期限取得サービス(WEB3ExpirationDateListService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 于瀟(中訊) 新規作成モデル319
*/

package webbroker3.gentrade.service.delegate;


import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (注文有効期限取得サービス)<BR>
 * 注文有効期限取得サービスインターフェイス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public interface WEB3ExpirationDateListService extends WEB3BusinessService
{

    /**
     * 注文有効期限取得サービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A976830232
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
