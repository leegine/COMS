head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.33.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistUnitCreateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者登録情報作成サービス(WEB3AdminMCAdminRegistUnitCreateService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 屈陽 (中訊) 新規作成 
*/

package webbroker3.adminmc.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
/**
 * (管理者登録情報作成サービス)<BR>
 * 管理者登録情報作成サービスインタフェイス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public interface WEB3AdminMCAdminRegistUnitCreateService extends Service 
{
    
    /**
     * (create管理者登録情報)<BR>
     * 管理者オブジェクトより、管理者登録情報メッセージデータオブジェクトを作成する。<BR>
     * <BR>
     * <BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit
     * @@roseuid 417DD0750069
     */
    public WEB3AdminMCAdminRegistUnit createAdminRegistUnit(WEB3Administrator l_administrator)
        throws WEB3BaseException;
    
    /**
     * (create管理者登録情報)<BR>
     * 管理者オブジェクトの配列より、管理者登録情報メッセージデータオブジェクトの配列を作成する。<BR>
     * <BR>
     * <BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクトの配列<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit[]
     * @@roseuid 417DD0750088
     */
    public WEB3AdminMCAdminRegistUnit[] createAdminRegistUnit(WEB3Administrator[] l_administrator)
        throws WEB3BaseException;
}
@
