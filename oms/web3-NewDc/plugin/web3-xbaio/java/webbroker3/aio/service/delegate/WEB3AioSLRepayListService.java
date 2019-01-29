head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSLRepayListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済一覧サービス(WEB3AioSLRepayListService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/12 何文敏 (中訊) 新規作成 仕様変更モデル757
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (証券担保ローン返済一覧サービス)<BR>
 * 証券担保ローン返済一覧サービスインターフェイス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public interface WEB3AioSLRepayListService extends WEB3BusinessService
{
    /**
     * 証券担保ローン返済一覧サービス処理を実施する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 46DE378E025A
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
