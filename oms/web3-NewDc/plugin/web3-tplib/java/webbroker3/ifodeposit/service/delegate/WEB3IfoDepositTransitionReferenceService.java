head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTransitionReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金推移参照画面表示サービスクラス(WEB3IfoDepositTransitionReferenceService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.ifodeposit.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * (証拠金推移参照画面表示サービス)<BR>
 * 証拠金推移参照画面表示サービスインターフェイス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public interface WEB3IfoDepositTransitionReferenceService
    extends WEB3BusinessService
{

    /**
     * (execute)<BR>
     * 証拠金推移参照画面表示サービス処理を実施する。<BR>
     * @@param l_request - リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41458DEE0034
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;

}
@
