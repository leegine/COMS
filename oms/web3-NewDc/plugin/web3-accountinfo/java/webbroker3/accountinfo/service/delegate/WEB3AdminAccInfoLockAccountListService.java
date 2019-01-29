head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ロック顧客登録問合せ一覧サービス (WEB3AdminAccInfoLockAccountListService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者お客様情報ロック顧客登録問合せ一覧サービス )<BR>
 * 管理者お客様情報ロック顧客登録問合せ一覧サービス <BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AdminAccInfoLockAccountListService extends WEB3BusinessService
{
    /**
     * 管理者お客様情報ロック顧客登録問合せ一覧を実施する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
