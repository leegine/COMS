head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRealUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J��UnitService(WEB3AccOpenRealUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/27 �����q(���u) �V�K�쐬 �d�l�ύX ���f�� No.113
*/
package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;

/**
 * (���������J��UnitService)<BR>
 * ���������J��UnitService�P���T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public interface WEB3AccOpenRealUnitService extends Service
{   
    /**
     * ���������J�݂P�����������{����B<BR>
     * <BR>
     * @@param l_accOpenAcceptParams - �����J�ݓ`�[�o�^��t�L���[
     */
    public String process(
        HostAccOpenAcceptParams l_accOpenAcceptParams) throws WEB3BaseException;
}
@
