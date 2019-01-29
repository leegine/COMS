head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSExecEndNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)株式出来終了通知サービス(WEB3EquityPTSExecEndNotifyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 趙林鵬(中訊) 新規作成 モデルNo.1285
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * ((PTS)株式出来終了通知サービス)<BR>
 * ((PTS)株式出来終了通知サービスインタフェース<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3EquityPTSExecEndNotifyService extends WEB3BackBusinessService
{
    /**
     * (PTS)株式出来終了通知サービス処理を実施する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056EB8E03D5
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
