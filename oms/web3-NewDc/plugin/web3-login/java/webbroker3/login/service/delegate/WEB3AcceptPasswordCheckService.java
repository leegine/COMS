head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.22.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客パスワードチェックサービスのエントリーポイント(WEB3AcceptPasswordCheckService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/28 齋藤　@栄三(FLJ) 新規作成
*/

package webbroker3.login.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * 顧客パスワードチェックサービスのエントリーポイント<BR>
 * <BR>
 * @@author  Eizo Saito(FLJ)
 * @@version 1.0
 */
public interface WEB3AcceptPasswordCheckService extends WEB3BusinessService
{

    /**
     * 顧客パスワードチェックサービス実行<BR>
     * @@param    WEB3GenRequest
     * @@param l_request
     * @@return   WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
