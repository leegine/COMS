head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.50.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitService(WEB3AdminSrvRegiAccountDataUploadUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 �A�C��(���u) �V�K�쐬
Revesion History : 2007/06/06 ��іQ (���u) ���f��254
Revesion History : 2007/07/11 �Ј���(���u) ���f��280
*/

package webbroker3.srvregi.service.delegate;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;


/**
 * (�T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitService)<BR>
 * �T�[�r�X���p�ڋq�f�[�^�A�b�v���[�hUnitService�@@�C���^�[�t�F�C�X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public interface WEB3AdminSrvRegiAccountDataUploadUnitService extends Service 
{
    /**
     * (update�\���o�^)<BR>
     * update�\���o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(�T�[�r�X���p)�ڋq�f�[�^�A�b�v���[�h�Eupdate�\���o�^�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strUploadDiv - (�A�b�v���[�h�敪)<BR>
     * �o�^�^�ύX�^���I���ʃA�b�v���[�h<BR>
     * @@param l_registId - (�\���o�^ID)<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@param l_tsAppliDate - (�\����)<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * ���p�^�\���^���I�i�{�\���j�^���I�^���<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * �����^�L��<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * @@roseuid 41109351035E
     */
    public void updateAppliRegist(
        WEB3GentradeSubAccount l_subAccount, 
        String l_strUploadDiv, 
        Long   l_registId, 
        String l_strInstitutionCode, 
        String l_strSrvDiv, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        Timestamp l_tsAppliStartDate, 
        Timestamp l_tsAppliEndDate, 
        Timestamp l_tsAppliDate, 
        String l_strAppliLotDiv, 
        String l_strPaymentDiv, 
        Double l_dblUseAmt, 
        Timestamp l_tsPaymentDate, 
        String l_strPassword) throws WEB3BaseException; 

    /**
     * (insert�T�[�r�X�\������)<BR>
     * insert�T�[�r�X�\�������������s���B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strMainAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * ���p�^�\���^���I�i�{�\���j�^���I�^���<BR>
     * <BR>
     * ���A�b�v���[�h�敪 = 3�F�\������ �̏ꍇ<BR>
     * �����^���p�s��<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * �K�p�I����<BR>
     * @@throws WEB3BaseException
     */
    public void insertSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv,
        String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate)
        throws WEB3BaseException;

    /**
     * (update�T�[�r�X�\������)<BR>
     * update�T�[�r�X�\�������������s���B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strMainAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * ���p�^�\���^���I�i�{�\���j�^���I�^���<BR>
     * <BR>
     * ���A�b�v���[�h�敪 = 3�F�\������ �̏ꍇ<BR>
     * �����^���p�s��<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * �K�p�I����<BR>
     * @@throws WEB3BaseException
     */
    public void updateSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv,
        String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate)
        throws WEB3BaseException;
    
    /**
     * (delete�T�[�r�X�\������)<BR>
     * delete�T�[�r�X�\�������������s���B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strMainAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@throws WEB3BaseException
     */
    public int deleteSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv)
        throws WEB3BaseException;
}
@
