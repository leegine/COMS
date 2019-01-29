head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ロック登録解除受付１件サービ(WEB3AccInfoLockRegistReleaseAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountinfo.data.HostLockRegiAcceptParams;

/**
 * (ロック登録解除受付１件サービス)<BR>
 * ロック登録解除受付１件サービス<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AccInfoLockRegistReleaseAcceptUnitService extends Service  
{
    /**
     * ロック登録解除受付１件を実施する。<BR>
     * @@param l_params <BR>
     * @@return String<BR>
     */
    public String notifyLockRegistReleaseAccep(HostLockRegiAcceptParams l_params);
}
@
