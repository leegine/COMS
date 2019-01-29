head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferOrderUploadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替注文アップロードサービス(WEB3AdminFXTransferOrderUploadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 鄭徳懇(中訊) 新規作成
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (FX振替注文アップロードサービス)<BR>
 * FX振替注文アップロードサービスインターフェイス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public interface WEB3AdminFXTransferOrderUploadService extends WEB3BusinessService 
{
    
    /**
     * FX振替注文アップロードサービス処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43C5FA660246
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
