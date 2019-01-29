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
filename	WEB3EquityPTSChangeOrderInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文訂正入力サービス（WEB3EquityPTSChangeOrderInputService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 トウ鋒鋼 (中訊) 新規作成　@モデル1217
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * ((PTS)現物株式注文訂正入力サービス)<BR>
 * (PTS)現物株式注文訂正入力サービス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public interface WEB3EquityPTSChangeOrderInputService extends WEB3BusinessService
{

    /**
     * (PTS)株式注文訂正入力サービスを実施する。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 473BD335005E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
