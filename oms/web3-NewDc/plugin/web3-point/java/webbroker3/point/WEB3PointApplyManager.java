head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\���}�l�[�W��(WEB3PointApplyManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/30 ���w�� (���u) �V�K�쐬
*/

package webbroker3.point;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.data.PointApplyParams;

/**
 * (�|�C���g�\���}�l�[�W��)<BR>
 * �|�C���g�\���}�l�[�W���C���^�[�t�F�C�X<BR>
 * 
 * @@author ���w��
 * @@version 1.0 
 */
public interface WEB3PointApplyManager extends Service 
{
    
    /**
     * (get�\��)<BR>
     * �\���I�u�W�F�N�g���擾����B<BR>
     * @@param l_lngApplyId - (�\��ID)<BR>
     * �\��ID<BR>
     * 
     * @@return webbroker3.point.WEB3PointApply
     * @@roseuid 418F2BFB03D6
     */
    public WEB3PointApply getApply(long l_lngApplyId) throws WEB3BaseException;
    
    /**
     * (get�\��)<BR>
     * �Y���ڋq�̉ߋ�7���̐\���f�[�^���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@return webbroker3.point.data.PointApplyParams
     * @@roseuid 419DC5870019
     */
    public PointApplyParams[] getApply(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (get�\������)<BR>
     * �����̏،���ЃR�[�h�A�i�i�ԍ��ɊY������i�i�̃|�C���g�\�������Ă���<BR>�������擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@return long
     * @@roseuid 4193480A0240
     */
    public long getApplyNumber(String l_strInstitutionCode, String l_strPremiumNo) throws WEB3BaseException;
    
    /**
     * (get���p�\�|�C���g)<BR>
     * ���p�\�|�C���g���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@return long
     * @@roseuid 418F2A2B003C
     */
    public long getUsePossiblePoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (get�������Ӄ|�C���g)<BR>
     * �������Ӄ|�C���g���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@return long
     * @@roseuid 418F2A2B006B
     */
    public long getExpirationAttentionPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (get�\���|�C���g)<BR>
     * ����.�Ώی��ɐ\�����s�����|�C���g�̍��v���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_strObjectMonth - (�Ώی�)<BR>
     * �����ΏۂƂȂ�N���iYYYYMM�j<BR>
     * 
     * @@return long
     * @@roseuid 41B6864A0314
     */
    public long getApplyPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException;
    
    /**
     * (get�������σ|�C���g)<BR>
     * �L��������������ς̃|�C���g�̍��v���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@return long
     * @@roseuid 41AFE92F023A
     */
    public long getNotWithdrawnPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (get���W�v�����|�C���g)<BR>
     * �Ώی��̖��W�v�̒����|�C���g�̍��v���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_strObjectMonth - (�Ώی�)<BR>
     * �����ΏۂƂȂ�N���iYYYYMM�j<BR>
     * 
     * @@return long
     * @@roseuid 41B6705202F4
     */
    public long getNotTotalAdjustPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException;
    
    /**
     * (get�L��������)<BR>
     * �L�����������擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strObjectMonth - (�Ώی�)<BR>
     * �Z�o�ΏۂƂȂ�N���iYYYYMM�j<BR>
     * 
     * @@return String
     * @@roseuid 41AFEF0F02C7
     */
    public String getValidTermMon(String l_strInstitutionCode, String l_strObjectMonth) throws WEB3BaseException;
    
    /**
     * (validate�����|�C���g)<BR>
     * �����|�C���g�̃`�F�b�N���s���B<BR>
     * @@param l_strAdjustPoint - (�����|�C���g)<BR>
     * �����|�C���g<BR>
     * 
     * @@param l_lngUsePossiblePoint - (���p�\�|�C���g)<BR>
     * ���p�\�|�C���g<BR>
     * @@roseuid 419468B0004E
     */
    public void validateAdjustPoint(String l_strAdjustPoint, long l_lngUsePossiblePoint) throws WEB3BaseException;
    
    /**
     * (validate�|�C���g�]��)<BR>
     * �|�C���g�]�͂̃`�F�b�N���s���B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_premium - (�i�i)<BR>
     * �i�i�I�u�W�F�N�g<BR>
     * @@roseuid 418F2C7402EC
     */
    public void validatePointPower(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, WEB3PointPremium l_premium) throws WEB3BaseException;
    
    /**
     * (validate�|�C���g�]��)<BR>
     * �\���̎�������Ń|�C���g�|�C���g�\�����L���ɂȂ������̃|�C���g�]�͂̃`�F�b�N���s���B<BR>
     * @@param l_appyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * @@roseuid 419D86CA0393
     */
    public void validatePointPower(WEB3PointApply l_appyData) throws WEB3BaseException;
    
    /**
     * (saveNew����)<BR>
     * �����f�[�^��DB�ɓo�^����B<BR>
     * @@param l_adjustData - (�����f�[�^)<BR>
     * �|�C���g�����I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 419478CC0242
     */
    public void saveNewAdjust(WEB3PointAdjust l_adjustData, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (saveNew�\��)<BR>
     * �\���f�[�^��DB�ɓo�^����B<BR>
     * @@param l_applyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎҃I�u�W�F�N�g<BR>
     * @@roseuid 41A451200008
     */
    public void saveNewApply(WEB3PointApply l_applyData, Trader l_trader) throws WEB3BaseException;
    
    /**
     * (save�\����t)<BR>
     * �\����t��Ԃ�DB���X�V����B<BR>
     * @@param l_applyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 419C8FF20259
     */
    public void saveApplyAccept(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (save�\�����)<BR>
     * �\�������Ԃ�DB���X�V����B<BR>
     * @@param l_applyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 419D62FA0001
     */
    public void saveApplyCancel(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (save�\���������)<BR>
     * �\�����������Ԃ�DB���X�V����B<BR>
     * @@param l_applyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 419D746702F7
     */
    public void saveApplyCancelRelease(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (validate�\���\)<BR>
     * �|�C���g�\���\���ǂ����̃`�F�b�N���s���B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * @@roseuid 41A6E4160153
     */
    public void validateApplyPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strPremiumNo) throws WEB3BaseException;
}
@
