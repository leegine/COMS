head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\�����ʃT�[�r�X�C���^�[�t�F�C�X(WEB3InformAccSwElecDeliApplyCommonService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 �Ј���(���u) ���f���ENo.100
Revision History : 2007/08/30 ����(���u) ���f���ENo.107
Revision History : 2007/09/19 �����F(���u) ���f���ENo.109
*/
package webbroker3.inform.service.delegate;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;

/**
 * (�����ؑցE�d�q��t�\�����ʃT�[�r�X�C���^�[�t�F�C�X)<BR>
 * �����ؑցE�d�q��t�\�����ʃT�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author �Ј���
 * @@version 1.0
 */
public interface WEB3InformAccSwElecDeliApplyCommonService extends Service
{
    /**
     * (validate�����ؑցE�d�q��t�\�����)<BR>
     * �����ؑցE�d�q��t�\�����̕ύX���e���`�F�b�N����B<BR>
     * <BR>
     * @@param l_beforeChangedInfo - (�ύX�O���)<BR>
     * �����ؑցE�d�q��t�\�����I�u�W�F�N�g
     * @@param l_changedInfo - (�ύX����)<BR>
     * �����ؑցE�d�q��t�\�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void validateAccSwitchElecDeliApplyInfo(
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo,
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo) throws WEB3BaseException;

    /**
     * (create�e��A��)<BR>
     * �e��A���V�K�s�𐶐�����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@param l_changedInfo - (�ύX����)<BR>
     * �ύX����
     * @@param l_dateInfo - (���t���)<BR>
     * ���t���
     * @@param l_strInformType - (�A�����)<BR>
     * �A�����
     * @@return WEB3Inform
     * @@throws WEB3BaseException
     */
    public WEB3Inform createVariousInform(
        MainAccount l_mainAccount,
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo,
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_dateInfo,
        String l_strInformType) throws WEB3BaseException;

    /**
     * (create�����ؑցE�d�q��t�\�����t���)<BR>
     * �����ؑցE�d�q��t�\�����t��񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     *
     * @@param l_strTaxType - (�ŋ敪)<BR>
     * �ŋ敪
     * @@param l_strMarginTaxType - (�M�p����ŋ敪)<BR>
     * �M�p����ŋ敪
     * @@return WEB3AdminInformAccSwitchElecDeliAppDtInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo createAccSwitchElecDeliAppDtInfo(
        String l_strTaxType,
        String l_strMarginTaxType) throws WEB3BaseException;

    /**
     * (create�����ؑցE�d�q��t�\�����)<BR>
     * �����ؑցE�d�q��t�\����񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@return WEB3AdminInformAccSwitchElecDeliApplyInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo createAccSwitchElecDeliApplyInfo(
        MainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (create�����ؑցE�d�q��t�\�����)<BR>
     * �����ؑցE�d�q��t�\����񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s<BR>
     * @@return WEB3AdminInformAccSwitchElecDeliApplyInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo createAccSwitchElecDeliApplyInfo(
        VariousInformParams l_variousInformParams) throws WEB3BaseException;

    /**
     * (create���E��c�d�q��t�E��������o�^�s)<BR>
     * ���E��c�d�q��t�E��������o�^�V�K�s�𐶐�����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_variousInformParams - (�e��A���s)<BR>
     * �e��A���s<BR>
     * @@throws WEB3BaseException
     */
    public HostConditionRegVoucherParams createHostConditionRegVoucherParams(
        MainAccount l_mainAccount,
        VariousInformParams l_variousInformParams) throws WEB3BaseException;

    /**
     * (�g���K���s)<BR>
     * �g���K�𔭍s����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strDataCode - (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h<BR>
     */
    public void submitMarketTrigger(String l_strInstitutionCode, String l_strDataCode);

    /**
     * (validate�`�[�쐬)<BR>
     * �`�[�쐬�\���`�F�b�N���s���B<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherMake(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException;

    /**
     * (validate�`�[�쐬)<BR>
     * �`�[�쐬�\���`�F�b�N���s���B<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪<BR>
     * @@param l_datStartScheduleDate - (�J�n�\���)<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherMake(
        String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv, Date l_datStartScheduleDate)
            throws WEB3BaseException;

    /**
     * (validate�`�[�ύX)<BR>
     * �`�[�ύX���\���`�F�b�N���s���B<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherChange(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException;

    /**
     * (validate�`�[���)<BR>
     * �`�[����\���`�F�b�N���s���B<BR>
     * @@param l_strMakeStatus - (�쐬��)<BR>
     * �쐬��<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (�g���K�[���s�敪)<BR>
     * �g���K�[���s�敪<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherCancel(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException;

    /**
     * (get�e��A���ꗗ)<BR>
     * �ȉ��̌�����������擾�����e��A�����R�[�h��List�^�ŕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strInformType - (�A�����)<BR>
     * �A�����<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getVariousInformList(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strInformType,
        String l_strAccountCode) throws WEB3BaseException;
}
@
