head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoDepositCalcResultSaveServiceクラス(WEB3IfoDepositCalcResultSaveService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/22 孫(FLJ) 新規作成
*/

package webbroker3.ifodeposit.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (証拠金計算結果保存サービス)<BR>
 * 証拠金計算結果保存サービスインターフェイス。<BR>
 * 
 * @@author 孫(FLJ)
 * @@version 1.0
 */
public interface WEB3IfoDepositCalcResultSaveService extends WEB3BusinessService
{
    
    /**
     * (execute)<BR>
     * 証拠金計算結果保存サービス処理を実施する。<BR>
     * @@param l_request - リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41458DEE0034
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;

}
@
