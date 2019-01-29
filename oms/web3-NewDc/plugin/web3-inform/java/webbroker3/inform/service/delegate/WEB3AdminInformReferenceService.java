head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索サービスインターフェイス(WEB3AdminInformReferenceService.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
*/

package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (連絡情報検索サービス)<BR>
 * 連絡情報検索サービスインターフェイス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3AdminInformReferenceService extends WEB3BusinessService 
{
    /**
     * 連絡情報検索サービス処理を行う。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41BD7F5C014F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
