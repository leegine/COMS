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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���b�N�o�^������t�P���T�[�r(WEB3AccInfoLockRegistReleaseAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountinfo.data.HostLockRegiAcceptParams;

/**
 * (���b�N�o�^������t�P���T�[�r�X)<BR>
 * ���b�N�o�^������t�P���T�[�r�X<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AccInfoLockRegistReleaseAcceptUnitService extends Service  
{
    /**
     * ���b�N�o�^������t�P�������{����B<BR>
     * @@param l_params <BR>
     * @@return String<BR>
     */
    public String notifyLockRegistReleaseAccep(HostLockRegiAcceptParams l_params);
}
@
