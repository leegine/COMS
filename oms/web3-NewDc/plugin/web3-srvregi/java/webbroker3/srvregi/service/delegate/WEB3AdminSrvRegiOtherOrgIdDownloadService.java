head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ(WEB3AdminSrvRegiOtherOrgIdDownloadService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 柴双紅(中訊) 新規作成 モデルNo.336
*/

package webbroker3.srvregi.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ)<BR>
 * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽインタフェイスクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3AdminSrvRegiOtherOrgIdDownloadService extends WEB3BusinessService
{

    /**
     * サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞ処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B9483D013E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
