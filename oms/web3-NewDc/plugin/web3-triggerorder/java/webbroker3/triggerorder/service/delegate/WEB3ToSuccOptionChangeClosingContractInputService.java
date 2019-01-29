head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeClosingContractInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）OP訂正返済入力サービス（WEB3ToSuccOptionChangeClosingContractInputService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 孟亞南(中訊) 新規作成 モデルNo.306
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractInputService;


/**
 * (（連続）OP訂正返済入力サービス)<BR>
 * （連続）株価指数オプション訂正返済入力サービスインターフェース<BR>
 *
 * @@author 孟亞南(中訊)
 * @@version 1.0
 */
public interface WEB3ToSuccOptionChangeClosingContractInputService extends WEB3OptionChangeClosingContractInputService
{

    /**
     * （連続）株価指数オプション訂正返済入力サービス処理を実施する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A97D64004F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
