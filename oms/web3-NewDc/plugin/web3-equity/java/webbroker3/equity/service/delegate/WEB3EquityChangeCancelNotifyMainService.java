head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelNotifyMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消通知メインサービス(WEB3EquityChangeCancelNotifyMainService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 中尾寿彦（SRA） 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/02 唐性峰　@(中訊)モデルNo.1046
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * （株式訂正取消通知メインサービスインタフェース）。
 * @@author 中尾寿彦
 * @@version 1.0
 */
public interface WEB3EquityChangeCancelNotifyMainService extends WEB3BackBusinessService 
{
    /**
     * (execute)<BR>
     * 株式訂正取消通知メインサービスを実施する。 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
