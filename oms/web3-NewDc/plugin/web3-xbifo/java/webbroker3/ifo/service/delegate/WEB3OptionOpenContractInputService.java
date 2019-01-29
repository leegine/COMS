head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : OP新規建入力サービス(WEB3OptionOpenContractInputService.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/15 呉艶飛 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (OP新規建入力サービス)<BR>
 * <BR>
 * 株価指数オプション新規建入力サービスインタフェース<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public interface WEB3OptionOpenContractInputService extends WEB3BusinessService 
{
    
    /**
     * 株価指数オプション新規建入力サービス処理を実施する。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A51FD00CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
