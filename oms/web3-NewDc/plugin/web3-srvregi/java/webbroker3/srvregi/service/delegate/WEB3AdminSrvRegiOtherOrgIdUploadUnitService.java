head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitService(WEB3AdminSrvRegiOtherOrgIdUploadUnitService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 �đo�g(���u) �V�K�쐬 ���f��No.337
*/

package webbroker3.srvregi.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;


/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitService)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖﱯ��۰��UnitService�@@�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public interface WEB3AdminSrvRegiOtherOrgIdUploadUnitService extends Service
{

    /**
     * (insert�O���A�g���)<BR>
     * insert�O���A�g��񏈗����s���B<BR>
     * @@param l_lngSequenceNumber - (�ʔ�)<BR>
     * �ʔ�<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�i�K�{�j<BR>
     * @@param l_strId - (ID)<BR>
     * ID<BR>
     * @@param l_strPassword - (�p�X���[�h)<BR>
     * �p�X���[�h<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C3D6AE024B
     */
    public void insertOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strSrvDiv,
        String l_strId,
        String l_strPassword,
        String l_strStatus,
        String l_strInstitutionCode) throws WEB3BaseException;

    /**
     * (update�O���A�g���)<BR>
     * update�O���A�g��񏈗����s���B<BR>
     * @@param l_lngSequenceNumber - (�ʔ�)<BR>
     * �ʔ�<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�i�K�{�j<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C3D6AE025A
     */
    public void updateOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strSrvDiv,
        String l_strStatus) throws WEB3BaseException;
}
@
