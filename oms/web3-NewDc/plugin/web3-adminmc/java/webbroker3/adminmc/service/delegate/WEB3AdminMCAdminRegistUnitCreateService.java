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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғo�^���쐬�T�[�r�X(WEB3AdminMCAdminRegistUnitCreateService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 ���z (���u) �V�K�쐬 
*/

package webbroker3.adminmc.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
/**
 * (�Ǘ��ғo�^���쐬�T�[�r�X)<BR>
 * �Ǘ��ғo�^���쐬�T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public interface WEB3AdminMCAdminRegistUnitCreateService extends Service 
{
    
    /**
     * (create�Ǘ��ғo�^���)<BR>
     * �Ǘ��҃I�u�W�F�N�g���A�Ǘ��ғo�^��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * <BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit
     * @@roseuid 417DD0750069
     */
    public WEB3AdminMCAdminRegistUnit createAdminRegistUnit(WEB3Administrator l_administrator)
        throws WEB3BaseException;
    
    /**
     * (create�Ǘ��ғo�^���)<BR>
     * �Ǘ��҃I�u�W�F�N�g�̔z����A�Ǘ��ғo�^��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g�̔z����쐬����B<BR>
     * <BR>
     * <BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g�̔z��<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit[]
     * @@roseuid 417DD0750088
     */
    public WEB3AdminMCAdminRegistUnit[] createAdminRegistUnit(WEB3Administrator[] l_administrator)
        throws WEB3BaseException;
}
@
