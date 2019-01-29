head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.18.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseRegistInfoCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�X(WEB3AccInfoCommissionCourseRegistInfoCreatedService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.common.WEB3BaseException;

/**
 * (���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�X)<BR>
 * ���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AccInfoCommissionCourseRegistInfoCreatedService extends Service 
{
    
    /**
     * (create�萔���R�[�X�ύX�\�����)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g���A<BR>
     * �萔���R�[�X�ύX�\����񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * @@param l_commissionCourseRegist - �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo
     * @@roseuid 41511E740136
     */
    public WEB3AccInfoCommissionCourseChangeInfo createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) throws WEB3BaseException ;
    
    /**
     * (create�萔���R�[�X�ύX�\�����)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�̔z����A<BR>
     * �萔���R�[�X�ύX�\����񃁃b�Z�[�W�f�[�^�̔z����쐬����B<BR>
     * @@param l_commissionCourseRegists - �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�̔z��
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 41511E740145
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists) throws WEB3BaseException;
    
    /**
     * (create�����萔���R�[�X�ύX�\�����)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�̔z����A<BR>
     * ��ꊔ���C�X�������̎萔���R�[�X�ύX�\����񃁃b�Z�[�W�f�[�^�̔z��<BR>
     * ���쐬����B<BR>
     * @@param l_commissionCourseRegists - �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�̔z��
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 41511E740147
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] createEquityCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists) throws WEB3BaseException;
}
@
