head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXAccOpenManagementService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座開設管理サービスインタフェイス(WEB3AdminFXAccOpenManagementService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 于美麗 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者FX口座開設管理サービス) <BR>
 * 管理者FX口座開設管理サービスインタフェイス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public interface WEB3AdminFXAccOpenManagementService extends
    WEB3BusinessService
{
    
    /**
     * (execute) <BR>
     * 管理者FX口座開設管理処理を行う。
     * 
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BD547E03B5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}@
