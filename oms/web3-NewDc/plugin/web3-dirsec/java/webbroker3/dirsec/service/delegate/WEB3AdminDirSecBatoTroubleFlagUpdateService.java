head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecBatoTroubleFlagUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者電子鳩障害フラグ更新(WEB3AdminDirSecBatoTroubleFlagUpdateService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/25 許丹(中訊) 新規作成 モデルNo.117
*/
package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者電子鳩障害フラグ更新)<BR>
 * 管理者電子鳩障害フラグ更新サービスインターフェイス。<BR>
 * <BR>
 * @@author 許丹
 * @@version 1.0
 */
public interface WEB3AdminDirSecBatoTroubleFlagUpdateService extends WEB3BusinessService
{

    /**
     * 管理者電子鳩障害フラグ更新を開始する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47D7582A03C3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
