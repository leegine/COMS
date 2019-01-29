head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.50.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽ(WEB3AdminSrvRegiOtherOrgIdListService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 柴双紅(中訊) 新規作成 モデルNo.335
*/

package webbroker3.srvregi.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽ)<BR>
 * サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽインタフェイスクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3AdminSrvRegiOtherOrgIdListService extends WEB3BusinessService
{

    /**
     * サービス利用管理者外部連携ID一覧照会処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B9484B034C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
