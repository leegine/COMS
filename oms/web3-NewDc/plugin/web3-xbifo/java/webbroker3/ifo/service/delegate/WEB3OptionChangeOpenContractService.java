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
filename	WEB3OptionChangeOpenContractService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正新規建サービス(WEB3OptionChangeOpenContractService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李頴淵 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (OP訂正新規建サービス) <BR>
 * <BR>
 * 株価指数オプション訂正新規建サービスインタフェース<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3OptionChangeOpenContractService extends WEB3BusinessService 
{
    
    /**
     * 株価指数オプション訂正新規建サービス処理を実施する。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056BA4F026E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
