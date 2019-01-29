head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引決済一覧サービス(WEB3MarginCloseMarginListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/30 呉艶飛 (中訊) 新規作成
                   2005/01/05 岡村   (SRA) JavaDoc修正   
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * （信用取引決済一覧サービス）。<BR>
 * <BR>
 * 信用取引決済一覧サービスインタフェース
 * @@author 呉艶飛(中訊)
 * @@version 1.0
 */
public interface WEB3MarginCloseMarginListService extends WEB3BusinessService 
{
    
    /**
     * 信用取引決済一覧サービス処理を実施する。
     * @@param l_request - リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40F61B680233
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
