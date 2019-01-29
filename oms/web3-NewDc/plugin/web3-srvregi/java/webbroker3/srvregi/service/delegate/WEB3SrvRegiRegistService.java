head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\���o�^�T�[�r�X(WEB3SrvRegiRegistService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 ���o�� �V�K�쐬
Revesion History : 2007/06/20 �����Q(sinocom) �d�l�ύX���f��No.249
Revesion History : 2007/06/26 �����Q(sinocom) �d�l�ύX���f��No.274
*/

package webbroker3.srvregi.service.delegate;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;

/**
 * (�T�[�r�X���p�\���o�^�T�[�r�X)<BR>
 * @@author ���o��
 * @@version 1.0
 *
 * �T�[�r�X���p�\���o�^�T�[�r�X�C���^�[�t�F�C�X<BR>
 * �i�g�����U�N�V���������F�����Z�b�g���Ȃ��j<BR>
 */
public interface WEB3SrvRegiRegistService extends Service
{

    /**
     * (validate�K�p����)<BR>
     * �w�肳�ꂽ�K�p���Ԃ����������ǂ����𔻒肷��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strMainAccountCode - (�ڋq�R�[�h)<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@param l_lngRegistId - (�\���o�^ID)<BR>
     * @@roseuid 413E8E3702E3
     */
    public void validateAppliPeriod(String l_strInstitutionCode, String l_strSrvDiv, String l_strBranchCode, String l_strMainAccountCode, Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate, Long l_lngRegistId) throws WEB3BaseException;

    /**
     * (calc�K�p�I����)<BR>
     * �w�肳�ꂽ�K�p�J�n������A�K�p�I�������Z�o���ĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@param l_lngSrvUsePeriodId - (���p����ID)<BR>
     * @@param l_strSpecialDiv - (����\���敪)<BR>
     * @@param l_strFreeAttributeApplyDiv - (���������\���敪)<BR>
     * @@return Timestamp
     * @@roseuid 413E8E370322
     */
    public Timestamp calcAppliEndDate(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, Timestamp l_tsAppliStartDate, long l_lngSrvUsePeriodId, String l_strSpecialDiv, String l_strFreeAttributeApplyDiv) throws WEB3BaseException;

    /**
     * (submit�T�[�r�X�\���o�^)<BR>
     * �T�[�r�X�\���o�^�̍X�V�������s���B<BR>
     * @@param l_newAppliSpec - (�\�����e)<BR>
     * �T�[�r�X���p�V�K�\�����e<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * @@roseuid 413E8E370360
     */
    public void submitServiceRegist(WEB3SrvRegiNewAppliSpec l_newAppliSpec, Long l_lngOrderId) throws WEB3BaseException;

    /**
     * (submit�T�[�r�X�\���ύX)<BR>
     * �T�[�r�X�\���o�^�̍X�V�������s���B<BR>
     * @@param l_changeAppliSpec - (�\�����e)<BR>
     * �T�[�r�X���p�ύX�\�����e�I�u�W�F�N�g<BR>
     * @@roseuid 413E8E37039F
     */
    public void submitServiceRegistChange(WEB3SrvRegiChangeAppliSpec l_changeAppliSpec) throws WEB3BaseException;

    /**
     * (submit�]�͍S��)<BR>
     * �T�[�r�X�̗��p�\���ɔ����o���ׁ̈A���o�������P�ʂ��쐬���A<BR>
     * ���̍ۂɍ쐬��������ID��ԋp����B<BR>
     * @@param l_subAccount - �⏕����<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎҃I�u�W�F�N�g<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strOrderChannel - (�����`���l��)<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * @@return long
     * @@roseuid 413E8E3703DD
     */
    public long submitRemainingPowerRestraint(WEB3GentradeSubAccount l_subAccount, Trader l_trader, double l_dblUseAmt, Timestamp l_tsPaymentDate, String l_strSrvDiv, String l_strOrderChannel, String l_strPassword) throws WEB3BaseException;

    /**
     * (submit�]�͉��)<BR>
     * �T�[�r�X�̗��p�\���̎���Ȃǂɔ����]�͉���ׁ̈A<BR>
     * ���o�������P�ʂɎ��������������{����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * @@roseuid 413E8E380024
     */
    public void submitRemainingPowerRelease(WEB3GentradeSubAccount l_subAccount, long l_lngOrderId, String l_strPassword) throws WEB3BaseException;

    /**
     * (get�T�[�r�X�\���o�^)<BR>
     * �w�肳�ꂽ�T�[�r�X�\���o�^���擾���A������T�[�r�X�\���o�^�I�u�W�F�N�g��
     * �쐬���ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_lngRegistId - (�\���o�^ID)<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�<BR>
     * @@return WEB3GentradeSrvRegiApplication
     * @@roseuid 413E8E380063
     */
    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId, boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (get�T�[�r�X�\���o�^)<BR>
     * ���ݗL���ȃT�[�r�X�\���o�^���擾���A���������<BR>
     * �T�[�r�X�\���o�^�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_strCancelDiv - (����敪)<BR>
     * 0:�ʏ�@@1:����@@null:�w�薳<BR>
     * @@param l_strEffectiveDiv - (�L���敪)<BR>
     * 0:�L���@@1:�����@@null:�w�薳<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�<BR>
     * @@return WEB3GentradeSrvRegiApplication
     * @@roseuid 413E8E3800A1
     */
    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, String l_strCancelDiv, String l_strEffectiveDiv, boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (get�T�[�r�X�\���o�^�ꗗ)<BR>
     * �w�肳�ꂽ�����ɍ��v����T�[�r�X�\���o�^�ꗗ���������A<BR>
     * ���̌��ʂ��T�[�r�X�\���o�^Params�I�u�W�F�N�g�̔z��ɂ��ĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�i�K�{�j<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�i�K�{�j<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * 0:�L���@@1:�����@@2:�S��<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * @@param l_tsAppliStartDateFrom - (�K�p�J�n���i���j)<BR>
     * @@param l_tsAppliStartDateTo - (�K�p�J�n���i���j)<BR>
     * @@param l_tsAppliDateFrom - (�\�����i���j)<BR>
     * @@param l_tsAppliDateTo - (�\�����i���j)<BR>
     * @@param l_sortConds - (�\�[�g����)<BR>
     * �Ώۍ���:<BR>
     * �ᒊ�I���̏ꍇ��<BR>
     * "���X","�ڋq","�K�p�J�n��","�K�p�I����","�o�^�敪","���p����"<BR>
     * ,"�ŏI�X�V��","�ŏI�X�V��"<BR>
     * �ᒊ�I�L�̏ꍇ��<BR>
     * "���X","�ڋq","�\�����I�敪","�\����","�K�p�J�n��","�K�p�I����"<BR>
     * ,"�o�^�敪","���p����","�ŏI�X�V��","�ŏI�X�V��"<BR>
     * @@return webbroker3.gentrade.data.SrvRegiApplicationParams[ ]
     * @@roseuid 413E8E3800E0
     */
    public SrvRegiApplicationParams[] getServiceRegistLists(String l_strInstitutionCode, String[] l_strBranchCodes, String l_strSrvDiv, String l_strAccountCode, String l_strPaymentDiv, String l_strAppliLotDiv, Timestamp l_tsAppliStartDateFrom, Timestamp l_tsAppliStartDateTo, Timestamp l_tsAppliDateFrom, Timestamp l_tsAppliDateTo, WEB3SrvRegiSortKey[] l_sortConds) throws WEB3BaseException;

    /**
     * (get�T�[�r�X�\���o�^�ꗗ)<BR>
     * ���ݗL���ȃT�[�r�X�\���o�^���擾���A�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_blnIsAppliEndDateDiv - (�K�p�I�����敪)<BR>
     * @@return webbroker3.gentrade.data.SrvRegiApplicationParams[ ]
     * @@roseuid 413E8E38011E
     */
    public SrvRegiApplicationParams[] getServiceRegistLists(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, boolean l_blnIsAppliEndDateDiv) throws WEB3BaseException;

    /**
     * (is���p�\)<BR>
     * ���Y�T�[�r�X�����p�\���ǂ������肷��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_lngRegistId - (�\���o�^ID)<BR>
     * @@return boolean
     * @@roseuid 416B72AE007A
     */
    public boolean isUsePossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId) throws WEB3BaseException;

    /**
     * (is����\)<BR>
     * ���Y�̃T�[�r�X�\���o�^������\�Ȃ��̂��ǂ����𔻒肷��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@param l_lngRegistId - (�\���o�^ID)<BR>
     * @@return boolean
     * @@roseuid 416B72AE0099
     */
    public boolean isCancelPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId) throws WEB3BaseException;

    /**
     * (validate����]��)<BR>
     * ����]�͎c�����\�����邩�𔻒肷��B <BR>
     *<BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jvalidate����]�́v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strOrderChannel - (�����`���l��)<BR>
     * @@return boolean
     * @@roseuid 416B72A802FA
     */
    public void validateTradingPower(WEB3GentradeSubAccount l_subAccount, Trader l_trader, double l_dblUseAmt, Timestamp l_tsPaymentDate, String l_strSrvDiv, String l_strOrderChannel) throws WEB3BaseException;

    /**
     * (get�����\���敪)<BR>
     * �����ɂĎw�肳�ꂽ�T�[�r�X�A�ڋq�̑g�ݍ��킹�ŉߋ��P�x�ł��\��������������<BR>
     * ���肵�A���茋��(*)��ԋp����B<BR>
     * <BR>
     * <BR>
     * (*) [�ԋp�l���e] <BR>
     * �i�T�[�r�X���p�ڋq�T�[�r�X���ꗗ���ʏ��.�����\���敪�A <BR>
     * �T�[�r�X���p�Ǘ��҃A�b�v���[�h�m�F���X�|���X.�����\���敪�̃R�[�h��`�Ɠ���<BR>
     * �������Anull�͂��肦�Ȃ��B�j<BR>
     * 0:�� <BR>
     * 1:�L<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * @@return String<BR>
     * @@roseuid 416B72A802FA
     */
    public String getInitializeAppliDiv(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode)
        throws WEB3BaseException;
	
	/**
	 * (get�T�[�r�X�\���o�^����Ώ�)<BR>
	 * ����\�ȃT�[�r�X�\���o�^���擾���A
	 * ��������ɃT�[�r�X�\���o�^�I�u�W�F�N�g��ԋp����B<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
	 * @@param l_strBranchCode - (���X�R�[�h)<BR>
	 * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
	 * @@param l_strAccountCode - (�����R�[�h)<BR>
	 * @@param l_blnIsRowLock - (is�s���b�N)<BR>
	 * true : �s���b�N���s��   false : �s���b�N���s��Ȃ�<BR>
	 * @@return WEB3GentradeSrvRegiApplication
	 * @@roseuid 41130761012E
	 */
	public WEB3GentradeSrvRegiApplication getServiceRegistCancelUnit(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, boolean l_blnIsRowLock)
		throws WEB3BaseException;
        
    /**
     * (get����p�X���[�h)<BR>
     * ����p�X���[�h���擾����B
     * 
     * OpLoginSecurityService���A���O�C���^�C�v�������擾����B
     * �|���O�C���^�C�v����.������ == ����p�X���[�h�ݒ�i�FTRADING_PWD_ENV�j�̑����l�� 
     *   �h0�FDEFAULT�i����p�X���[�h���ڂ��g�p���Ȃ��j�h�̏ꍇ�A����.�Ïؔԍ���ԋp���� �B
     * �|���O�C���^�C�v����.������ == ����p�X���[�h�ݒ�i�FTRADING_PWD_ENV�j�̑����l�� 
     * �@@�h1�F����p�X���[�h�g�p�h�̏ꍇ�A����.�⏕�������ڋq�I�u�W�F�N�g.����p�X���[�h���擾���A 
     *�@@�ԋp����B��
     *
     * ���ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ����������� 
     * 
     * @@param l_subAccount (�⏕�����j<BR>
     * @@param l_strPassword (�Ïؔԍ��j<BR>
     * @@return String<BR>
     * @@author sra518
     */
    public String getTradingPassword(SubAccount l_subAccount, String l_strPassword);

    /**
     * (get�T�[�r�X�\�������ꗗ)<BR>
     * �w�肳�ꂽ�����ɍ��v����T�[�r�X�\�������ꗗ���������A<BR> 
     * ���̌��ʂ��T�[�r�X�\���o�^Params�I�u�W�F�N�g�̔z��ɂ��ĕԋp����B<BR> 
     * <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�i�K�{�j<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�ꗗ<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�i�K�{�j<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * 7:�����Ώہ@@8:�\���s�@@9:�S��<BR>
     * @@param l_tsAppDate - (�K�p��)<BR>
     * �K�p��<BR>
     * @@param l_sortCondition - (�\�[�g����)<BR>
     * �Ώۍ���:<BR> 
     * "���X","�ڋq","�\������","�K�p�J�n��","�K�p�I����","�ŏI�X�V��","�ŏI�X�V��"<BR> 
     * <BR>
     * @@return ���� �T�[�r�X�\������Params[]
     */
    public SrvAppliAttributeParams[] getServiceAttributeLists(String l_strInstitutionCode, String[] l_strBranchCodes,
        String l_strSrvDiv, String l_strAccountCode, String l_strAppliLotDiv, Timestamp l_tsAppDate,
        WEB3SrvRegiSortKey[] l_sortConditions) throws WEB3BaseException;
}
@
