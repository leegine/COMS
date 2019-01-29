head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文約定照会サービス (WEB3BondExecuteReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 周捷 (中訊) 新規作成
*/
package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (債券注文約定照会サービス )<BR>
 * 債券注文約定照会サービスインタフェイス
 * 
 * @@author 周捷
 * @@version 1.0
 */
public interface WEB3BondExecuteReferenceService extends WEB3BusinessService
{
    /**
     * 債券注文約定照会処理を行なう。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
