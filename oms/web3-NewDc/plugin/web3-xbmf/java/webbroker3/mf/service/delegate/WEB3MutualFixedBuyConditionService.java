head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付銘柄条件登録サービス(WEB3MutualFixedBuyConditionService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 安陽(中訊) 新規作成 モデル608
*/

package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (投信定時定額買付銘柄条件登録サービス)<BR>
 * 投信定時定額買付銘柄条件登録サービス<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public interface WEB3MutualFixedBuyConditionService extends WEB3BusinessService
{
    /**
     * 投資定時定額買付銘柄条件登録サービス処理を実施する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851CB720293
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
