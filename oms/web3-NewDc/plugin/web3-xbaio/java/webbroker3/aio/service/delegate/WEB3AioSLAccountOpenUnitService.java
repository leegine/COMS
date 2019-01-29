head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSLAccountOpenUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SL口座開設UnitService(WEB3AioSLAccountOpenUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 謝旋 (中訊) 新規作成 モデルNo.763
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (SL口座開設UnitService)<BR>
 * SL口座開設UnitServiceインターフェイス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public interface WEB3AioSLAccountOpenUnitService extends WEB3BusinessService
{
    /**
     * SL口座開設サービス処理を行う。
     * @@param l_request - リクエストデータ
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE071503C9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
