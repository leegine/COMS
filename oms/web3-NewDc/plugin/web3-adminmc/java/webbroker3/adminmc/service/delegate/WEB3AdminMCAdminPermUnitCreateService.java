head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.34.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermUnitCreateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ������쐬�T�[�r�X(WEB3AdminMCAdminPermUnitCreateService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ���z (���u) �V�K�쐬 
*/

package webbroker3.adminmc.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (�Ǘ��Ҍ������쐬�T�[�r�X)<BR>
 * �Ǘ��Ҍ������쐬�T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public interface WEB3AdminMCAdminPermUnitCreateService extends Service 
{
    
    /**
     * (update�����\�@@�\�J�e�S��)<BR>
     * �����̓��e�ŁA�Ǘ��Ҍ����e�[�u�����X�V����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strPermissionLevel - (�������x��)<BR>
     * �������x��<BR>
     * @@param l_operatePossibleTransactionCategory - (�����\�@@�\�J�e�S��)<BR>
     * �@@�\�J�e�S���R�[�h�ꗗ<BR>
     * <BR>
     * @@roseuid 41760BA00292
     */
    public void updateOperatePossibleTransactionCategory(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        WEB3AdminMCTransactionCategoryUnit[] l_operatePossibleTransactionCategory,
        String l_strAdministratorCode)
            throws WEB3BaseException;
    
    /**
     * (create�����\�@@�\�J�e�S���ꗗ)<BR>
     * �،���ЁC�������x���ɊY������@@�\�J�e�S������z����쐬����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strPermissionLevel - (�������x��)<BR>
     * �������x��<BR>
     * @@param l_strTransactionCategory - (�@@�\�J�e�S���R�[�h)<BR>
     * �@@�\�J�e�S���R�[�h�̔z��<BR>
     * <BR>
     * �� �w�肵�Ȃ��ꍇ��null<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit[]
     * @@roseuid 41760BA00273
     */
    public WEB3AdminMCTransactionCategoryUnit[] createOperatePossibleTransactionCategoryUnit(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String[] l_strTransactionCategory)
            throws WEB3BaseException;
    
    /**
     * (create�Ǘ��҃^�C�v���)<BR>
     * �Ǘ��҃^�C�v�I�u�W�F�N�g���A�Ǘ��҃^�C�v��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * <BR>
     * @@param l_adminType - (�Ǘ��҃^�C�v)<BR>
     * �Ǘ��҃^�C�v<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit
     * @@roseuid 417723210196
     */
    public WEB3AdminMCAdminTypeUnit createAdminTypeUnit(WEB3AdminMCAdminType l_adminType);
    
    /**
     * (create�Ǘ��҃^�C�v���)<BR>
     * �Ǘ��҃^�C�v�I�u�W�F�N�g�̔z����A�Ǘ��҃^�C�v��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g�̔z����쐬����B<BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_adminType - (�Ǘ��҃^�C�v)<BR>
     * �Ǘ��҃^�C�v�I�u�W�F�N�g�̔z��<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit[]
     * @@roseuid 417749B30196
     */
    public WEB3AdminMCAdminTypeUnit[] createAdminTypeUnit(WEB3AdminMCAdminType[] l_adminType);
}
@
