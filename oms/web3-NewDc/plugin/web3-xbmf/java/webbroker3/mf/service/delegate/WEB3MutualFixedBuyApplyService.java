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
filename	WEB3MutualFixedBuyApplyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付新規申込サービス(WEB3MutualFixedBuyApplyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/23 肖志偉 (中訊) 新規作成
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * 投信定時定額買付新規申込サービス
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public interface WEB3MutualFixedBuyApplyService extends WEB3BusinessService 
{
    /**
     * 投資定時定額買付新規申込サービス処理を実施する。
     * @@param l_request - (リクエストデータ) <BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
