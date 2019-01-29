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
filename	WEB3MstkCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文取消サービス(WEB3MstkCancelService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 カク寛新 (中訊) 新規作成
                   2004/12/29 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * （株式ミニ投資注文取消サービス）。<BR>
 * <BR>
 * 株式ミニ投資注文取消サービスインタフェイス
 * @@author カク寛新
 * @@version 1.0
 */
public interface WEB3MstkCancelService extends WEB3BusinessService 
{
    
    /**
     * （execute）。<BR>
     * <BR>
     * 株式ミニ投資注文取消処理を実施する
     * @@param l_request (リクエスト)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
