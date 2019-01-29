head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLProductRegistControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^����T�[�r�X(WEB3AdminSLProductRegistControlService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 ��іQ(���u) �V�K�쐬 ���f��No.760
Revision History : 2007/10/26 ����(���u)�@@�d�l�ύX���f��No.816
*/

package webbroker3.aio;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLProductSearchConditions;
import webbroker3.common.WEB3BaseException;

/**
 * (�S�ۖ����o�^����T�[�r�X)<BR>
 * �S�ۖ����o�^����T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public interface WEB3AdminAioSLProductRegistControlService extends Service
{
    /**
     * (insert�S�ۖ������)<BR>
     * �S�ۖ����e�[�u���ɒS�ۖ�������insrt����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_stockLoanProductInfo - (�����o�^���)<BR>
     * �S�ۖ����o�^���I�u�W�F�N�g<BR>
     * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D7C5FC016C
     */
    public void insertSecurityProductInfo(
        String l_strInstitutionCode,
        WEB3SLProductInfoUnit l_stockLoanProductInfo,
        String l_strAdministratorCode) throws WEB3BaseException;

    /**
     * (validate�S�ۖ����������)<BR>
     * ��������ŁA��������ԓ��̖��������݂��邩�`�F�b�N���s���B<BR>
     * ���݂���ꍇ�A��O���X���[����B<BR>
     * @@param l_lisSecurityProductInfos - (�S�ۖ������)<BR>
     * �S�ۖ����o�^���<BR>
     * @@param l_datTargetPeriodFrom - (�K�p����from)<BR>
     * �K�p����from<BR>
     * @@param l_datTargetPeriodTo - (�K�p����to)<BR>
     * �K�p����to<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DFBBC101AB
     */
    public void validateSecurityProductSameTerm(
        List l_lisSecurityProductInfos,
        Date l_datTargetPeriodFrom,
        Date l_datTargetPeriodTo)
        throws WEB3BaseException;

    /**
     * (get�S�ۖ����s)<BR>
     * ��L�[����S�ۖ����s���擾����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_datTargetPeriodFrom - (�K�p����from)<BR>
     * �K�p����from<BR>
     * @@return SecurityProductRow
     * @@throws WEB3BaseException
     * @@roseuid 46DFBBD301DA
     */
    public SecurityProductRow getSecurityProductRow(
        long l_lngProductId,
        Date l_datTargetPeriodFrom)
        throws WEB3BaseException;

    /**
     * (compare�ύX���)<BR>
     * �ύX�O�ƕύX��̏�Ԃ��r����B<BR>
     * @@param l_changeBeforeSecurityProductInfo - (�ύX�O�S�ۖ������)<BR>
     * �ύX�O�S�ۖ������<BR>
     * @@param l_changeAfterSecurityProductInfo - (�ύX��S�ۖ������)<BR>
     * �ύX��S�ۖ������<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 46DFBBE60370
     */
    public int compareChangeInfo(
        SecurityProductRow l_changeBeforeSecurityProductInfo,
        WEB3SLProductInfoUnit l_changeAfterSecurityProductInfo)
        throws WEB3BaseException;

    /**
     * (update�S�ۖ������)<BR>
     * ��L�[���X�V�����ɒS�ۖ����e�[�u���̃��R�[�h���X�V����B<BR>
     * @@param l_searchKeyConditions - (�����L�[���)<BR>
     * �S�ۖ��������L�[���<BR>
     * @@param l_securityProductRow - (�S�ۖ���Row)<BR>
     * �S�ۖ���Row<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DFBC010285
     */
    public void updateSecurityProductInfo(
        WEB3SLProductSearchConditions l_searchKeyConditions,
        SecurityProductRow l_securityProductRow)
        throws WEB3BaseException;

    /**
     * (delete�S�ۖ������)<BR>
     * ��L�[��ΏۂɒS�ۖ����e�[�u���̃��R�[�h���폜����B<BR>
     * @@param l_deleteObjectKey - (�폜�ΏۃL�[)<BR>
     * �폜���R�[�h�ΏۃL�[<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DFBC0A015D
     */
    public void deleteSecurityProductInfo(WEB3SLProductSearchConditions l_deleteObjectKey)
        throws WEB3BaseException;

    /**
     * (get�S�ۖ������)<BR>
     * ����ID���L�[�ɒS�ۖ����e�[�u���̃��R�[�h���擾���A�ԋp����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 46DFBC110370
     */
    public List getSecurityProductInfo(long l_lngProductId)
        throws WEB3BaseException;

    /**
     * (is���ڕύX)<BR>
     * ���ڂ��ύX����Ă��邩�𔻕ʂ���B<BR>
     * @@param l_strChangeBeforeItem - (�ύX�O����)<BR>
     * �ύX�O����<BR>
     * @@param l_strChangeAfterItem - (�ύX�㍀��)<BR>
     * �ύX�㍀��<BR>
     * @@return boolean
     * @@roseuid 46DFBC1B0285
     */
    public boolean isItemChange(String l_strChangeBeforeItem, String l_strChangeAfterItem);
}
@
