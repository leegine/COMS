head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 新規口座開設検索ｻｰﾋﾞｽ(WEB3AdminAccInfoAccEstablishSearchService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09 何文敏 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ｻｰﾋﾞｽ)<BR>
 * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ｻｰﾋﾞｽインタフェイス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */

public interface WEB3AdminAccInfoAccEstablishSearchService extends WEB3BusinessService
{
    /**
     * 新規口座開設・口座移管・ログインロック顧客検索処理を実施する。<BR>
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
  
}
@
