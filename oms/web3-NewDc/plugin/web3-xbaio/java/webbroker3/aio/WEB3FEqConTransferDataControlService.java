head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FEqConTransferDataControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�֘A�g�f�[�^����T�[�r�X�N���X(WEB3FEqConTransferDataControlService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 ��O�� (���u) �V�K�쐬       
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.aio.data.UwgTransferStatusParams;
import webbroker3.aio.message.WEB3FEqConAccountOpenQuestionInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionParams;

/**
 * (�O���U�֘A�g�f�[�^����T�[�r�X)<BR>
 * �O���U�֘A�g�f�[�^����T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public interface WEB3FEqConTransferDataControlService extends Service 
{
    
    /**
     * (get�O�������ڋq)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�ɊY������<BR>
     * �O�������ڋqParams���擾����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return FeqAccountParams
     * @@roseuid 41E4B4E50020
     */
    public FeqAccountParams getFeqAccountByAccountCode(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode) 
        throws WEB3BaseException, NotFoundException;
    
    /**
     * (get�O�������ڋq)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A<BR>
     * �O�������ԍ��ɊY������O�������ڋqParams���擾����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strFeqAccountCode - �O�����������ԍ�
     * 
     * @@return FeqAccountParams
     * @@roseuid 41EF678B018D
     */
    public FeqAccountParams getFeqAccountByFeqAccountCode(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strFeqAccountCode) 
        throws WEB3BaseException, NotFoundException;
    
    /**
     * (get�O�������ڋq)<BR>
     * �����̊O�������ڋqID�ɊY������O�������ڋqParams���擾����B
     * @@param l_strFeqAccountId - �O�������ڋqID
     * 
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 41E61D970136
     */
    public FeqAccountParams getFeqAccountByAccountId(String l_strFeqAccountId)
        throws WEB3BaseException, NotFoundException;
    
    /**
     * (get����)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�Ɉ�v����<BR>
     * ����Params��ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@return QuestionParams[]
     * @@roseuid 41E4B4E500DC
     */
    public QuestionParams[] getQuestion(
            String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException;
    
    /**
     * (get�����)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�Ɉ�v����<BR>
     * �����Params��ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return QuestionAnswerParams[]
     * @@roseuid 41E4B4E500FB
     */
    public QuestionAnswerParams[] getQuestionAnswer(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) throws WEB3BaseException;
    
    /**
     * (getUWG�����J�ݏ�)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h��<BR>
     * �Y������UWG�����J�ݏ�Params��ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return UwgAccountOpenStatusParams
     * @@roseuid 41E4B4E5011A
     */
    public UwgAccountOpenStatusParams getUwgAccountOpenStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) throws WEB3BaseException;
    
    /**
     * (getUWG�����J�ݏ�)<BR>
     * �����̏����ɊY������UWG�����J�ݏ�Params��<BR>
     * �ꗗ��ԋp����B
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@return UwgAccountOpenStatusParams[]
     * @@roseuid 41EF5C7F0287
     */
    public UwgAccountOpenStatusParams[] getUwgAccountOpenStatus(
            String l_strQueryString, 
            String[] l_queryContainer, 
            String l_strSortCond) throws WEB3BaseException;
    
    /**
     * (getUWG�U�֏�)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h��<BR>
     * �Y������UWG�U�֏�Params��ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return UwgTransferStatusParams
     * @@roseuid 41E4B4E50159
     */
    public UwgTransferStatusParams getUwgTransferStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) throws WEB3BaseException;
    
    /**
     * (insert�O�������ڋq)<BR>
     * UWG�����J�ݏ�Params�̓��e���A<BR>
     * �O�������ڋq�e�[�u���ɍs��insert���s���B
     * @@param l_uwgAccOpenStatusParams - UWG�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 41E4B4E50197
     */
    public void insertFeqAccount(
            UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, 
            String l_strUpdaterCode) throws WEB3BaseException;
    
    /**
     * (insert�����)<BR>
     * �O�������J�ݎ�����̓��e��<BR>
     * ����񓚃e�[�u��(question_answer)�ɍs��insert���s���B<BR>
     * ������.������ꗗ�̗v�f������Loop�������s���A�v�f���Ƃɍs��insert���s���B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_feqAccOpenQuestionInfo - �O�������J�ݎ�����̈ꗗ
     * @@roseuid 41E4B4E50243
     */
    public void insertQuestionAnswer(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber, 
            WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo)
        throws WEB3BaseException;
        
    /**
     * (insertUWG�����J�ݏ�)<BR>
     * UWG�����J�ݏ󋵃e�[�u���ɍs��insert���s���B
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * @@param l_strPassword - UWG�p�p�X���[�h
     * @@param l_strOrderRequestNumber - ���ʃR�[�h
     * @@roseuid 41E4B4E50262
     */
    public void insertUwgAccountOpenStatus(
            MainAccount l_mainAccount, 
            String l_strPassword, 
            String l_strOrderRequestNumber)
        throws WEB3BaseException;
    
    /**
     * (insertUWG�U�֏�)<BR>
     * UWG�U�֏󋵃e�[�u���ɍs��insert���s���B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strTransferDate - ��n�\���
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_strMrgTrnRequestNumber - �M�p�U�֗p���ʃR�[�h
     * ���M�p��������̋����U�ւ��s��Ȃ��ꍇ�Anull
     * @@param l_strTransferAmount - �U�֋��z
     * @@roseuid 41E4B4E50281
     */
    public void insertUwgTransferStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strTransferDate, 
            String l_strRequestNumber, 
            String l_strMrgTrnRequestNumber, 
            String l_strTransferAmount) 
        throws WEB3BaseException;
    
    /**
     * (update�O�������ڋq)<BR>
     * �X�V������J�݋敪�̒l�ŊO�������ڋq�e�[�u�����X�V����B
     * @@param l_feqAccountParams - �O�������ڋqParam�I�u�W�F�N�g
     * @@param l_strUpdatedAccOpenDiv - �X�V������J�݋敪
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 41E4B4E502EF
     */
    public void updateFeqAccount(
            FeqAccountParams l_feqAccountParams, 
            String l_strUpdatedAccOpenDiv, 
            String l_strUpdaterCode)
        throws WEB3BaseException;
    
    /**
     * (updateUWG�����J�ݏ�)<BR>
     * ������UWG�����J�ݏ�Params��<BR>
     * UWG�����J�ݏ󋵃e�[�u�����X�V����B
     * @@param l_uwgAccOpenStatusParams - UWG�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_strUpdatedStatusDiv - �X�V��X�e�[�^�X�敪
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 41E4B4E5032D
     */
    public void updateUwgAccountOpenStatus(
            UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, 
            String l_strUpdatedStatusDiv, 
            String l_strUpdaterCode)
        throws WEB3BaseException;
    
    /**
     * (updateUWG�U�֏�)<BR>
     * ������UWG�U�֏�Params��UWG�U�֏󋵃e�[�u�����X�V����B
     * @@param l_uwgTransferStatusParams - UWG�U�֏�Params�I�u�W�F�N�g
     * @@param l_strUpdatedTransferStatusDiv - �X�V��U�֏󋵋敪
     * @@roseuid 41ECE02B02CE
     */
    public void updateUwgTransferStatus(
            UwgTransferStatusParams l_uwgTransferStatusParams, 
            String l_strUpdatedTransferStatusDiv)
        throws WEB3BaseException;
    
    /**
     * (update�O�������J�݋敪)<BR>
     * �ڋq�}�X�^�[�e�[�u���̊O���،������J�݋敪��update����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strUpdatedFeqAccOpenDiv - �X�V��O�������J�݋敪
     * 
     * 0�F�J��
     * 1�F���J��
     * 
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 41E4B4E503D9
     */
    public void updateFeqAccountOpenDiv(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strUpdatedFeqAccOpenDiv, 
            String l_strUpdaterCode)
        throws WEB3BaseException;
    
    /**
     * (validate�O�������J�ݎ���)<BR>
     * �O�������J�ݎ���ɑ΂���񓚂̐��������`�F�b�N����B
     * @@param l_feqAccOpenQuestionInfo - �O�������J�ݎ�����̈ꗗ
     * @@roseuid 41E4B4E6009D
     */
    public void validateFeqAccountOpenQuestion(
            WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo)
        throws WEB3BaseException;
    
    /**
     * (get�V�K�O�������ڋqID)<BR>
     * �O�������ڋqID��t�Ԃ��ĕԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return java.lang.String
     * @@roseuid 41E633DF027F
     */
    public String getNewFeqAccountId(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode)
        throws WEB3BaseException;
}
@
