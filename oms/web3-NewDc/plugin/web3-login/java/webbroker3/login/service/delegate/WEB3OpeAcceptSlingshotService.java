head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.22.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptSlingshotService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客成りすましSS遷移サービスのエントリーポイント(WEB3OpeAcceptSlingshotService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/09 菊地(SRA) 新規作成
*/

package webbroker3.login.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * 顧客成りすましSS遷移サービスのエントリーポイント<BR>
 * <BR>
 * @@author  菊地(SRA)
 * @@version 1.0
 */
public interface WEB3OpeAcceptSlingshotService extends WEB3BusinessService
{

    /**
     * 顧客成りすましSS遷移サービス実行<BR>
     * @@param    WEB3GenRequest
     * @@param l_request
     * @@return   WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406D24A20262
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
