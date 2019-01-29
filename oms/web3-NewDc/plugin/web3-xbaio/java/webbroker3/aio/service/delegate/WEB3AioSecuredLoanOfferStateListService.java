head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.14.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecuredLoanOfferStateListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保ローン申込状況一覧サービス(WEB3AioSecuredLoanOfferStateListService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.755
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (担保ローン申込状況一覧サービス)<BR>
 * 担保ローン申込状況一覧サービスインターフェイス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 *
 */
public interface WEB3AioSecuredLoanOfferStateListService extends WEB3BusinessService
{
    /**
     * 担保ローン申込状況一覧表示処理を行う。<BR>
     * <BR>
     * @@param l_request  - (リクエストデータ )<BR>
     * リクエストデータ <BR>
     * <BR>
     * ※DDLより自動生成
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}

@
