head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.28.06.22.26;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2f84d9029215dc5;
filename	WEB3FXDataControlService.java;

1.1
date	2011.03.16.02.22.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXDataControlService.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�f�[�^����T�[�r�X(WEB3FXDataControlService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 ���E (���u) �V�K�쐬
                 : 2006/02/08 �A���� (���u) �d�l�ύX�E���f��469�A471
                 : 2006/02/24 ���_�O (���u) �d�l�ύX�E���f��446�A459�A465�A510
                 : 2006/04/27 �юu�� (���u) �d�l�ύX�E���f��546
                 : 2006/05/08 ���� (���u) �d�l�ύX�E���f��550
                 : 2006/05/08 ���� (���u) �d�l�ύX�E���f��549�A557�A559
                 : 2006/05/12 �s�p (���u) �d�l�ύX�E���f��571
                 : 2006/05/16 ���� (���u) �d�l�ύX�E���f��583 
                 : 2006/07/12 ��O�� (���u) �d�l�ύX�E���f��598
                 : 2006/08/23 ��� (SCS) �d�l�ύX�E���f��631
Revesion History : 2008/04/08 ���n�m (���u) �d�l�ύX�E���f��838
                 : 2008/05/08 ���� (SCS) �d�l�ύX�E���f��838
                 : 2008/05/23 �O�� (SCS) 
Revesion History : 2008/05/20 �đo�g (���u) �d�l�ύX�E���f��851�A856�A867�A872�A873
Revesion History : 2008/06/17 �đo�g (���u) �d�l�ύX�E���f��899
Revesion History : 2008/06/23 �đo�g (���u) �d�l�ύX�E���f��904�A905
Revesion History : 2008/09/08 ���u�� (���u) �d�l�ύX�E���f��973
Revesion History : 2008/10/02 �|�� (SCS) �d�l�ύX�E���f��1046
Revesion History : 2008/10/07 ���u�� (���u) �d�l�ύX�E���f��990,1002,1027,1050,1065,1072
Revesion History : 2008/11/14 ���� (SCS) �d�l�ύX�E���f��1084
Revesion History : 2008/12/16 �哈 (SCS) �d�l�ύX�E���f��1088,1089
Revesion History : 2009/01/22 �g�� (SCS) �d�l�ύX�E���f��1092,1093,1094
Revesion History : 2009/03/12 �đo�g (���u) �d�l�ύX�E���f��1108�A1151
Revesion History : 2009/03/19 �Ԑi (���u) �d�l�ύX�E���f��1124�A1135�A1136�A1157
Revesion History : 2009/04/20 �Ԑi (���u) �d�l�ύX�E���f��1161
Revesion History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1214
*/
package webbroker3.aio;

import java.util.ArrayList;
import java.util.Date;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftMessageParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.gftforex.soap_api.ResultInfoAddAccount;
import com.gftforex.soap_api.ResultInfoCreateUser;

/**
 * (FX�f�[�^����T�[�r�X) <BR>
 * FX�f�[�^����T�[�r�X�C���^�[�t�F�C�X�B <BR>
 * @@author ���E(���u)
 * @@version 1.0
 */
public interface WEB3FXDataControlService extends Service
{
    /**
     * (getFX�ڋq) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�AFX�V�X�e���R�[�h�A�ڋq�R�[�h��<BR>
     *      �Y������FX�ڋqParams���擾����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return webbroker3.aio.data.FxAccountParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41CFFC340100
     */
    public FxAccountParams getFXAccount(String l_strInstitutionCode,
        String l_strBranchCode, String l_strFxSystemCode,
        String l_strAccountCode) throws WEB3BaseException, NotFoundException;

    /**
     * (getFX�ڋq) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�ɊY������FX�ڋqParams���擾����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return webbroker3.aio.data.FxAccountParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41BEA852035B
     */
    public FxAccountParams getFXAccount(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException, NotFoundException;

    /**
     * (getFX�ڋq) <BR>
     * �����̏����ɊY������FX�ڋqParams�̈ꗗ��ԋp����B <BR>
     * 
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@return webbroker3.aio.data.FxAccountParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BECC490223
     */
    public FxAccountParams[] getFXAccounts(String l_strQueryString,
        String[] l_queryContainer, String l_strSortCond) throws WEB3BaseException;

    /**
     * (getFX�����J�݋敪)<BR>
     * �����̏����ɊY������FX�����J�݋敪���擾����B<BR>
     * @@param l_strUpdatedAccOpenDiv - (�X�V������J�ݏ󋵋敪)<BR>
     * �X�V������J�ݏ󋵋敪<BR>
     * @@return String
     * @@roseuid 41BECC490223
     */
    public String getFXAccountOpenDiv(String l_strUpdatedAccOpenDiv);

    /**
     * (getFX�����ԍ�) <BR>
     * �����̏����ɊY������FX�����ԍ����擾����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strCourseDiv - �R�[�X�敪
     * 
     * 0�F DEFAULT 1�F 1���ʉ݃R�[�X 2�F 10���ʉ݃R�[�X
     * @@return webbroker3.aio.data.FxAccountCodeParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41C936E801D6
     */
    public FxAccountCodeParams getFXAccountCode(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode, String l_strCourseDiv) 
        throws WEB3BaseException, NotFoundException;

    /**
     * (getFX�����ԍ�) <BR>
     * �����̏����ɊY������FX�����ԍ��̈ꗗ���擾����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return webbroker3.aio.data.FxAccountCodeParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7F0B20087
     */
    public FxAccountCodeParams[] getFXAccountCodes(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException;

    /**
     * (getFX�����ԍ�)<BR>
     * �����̏����ɊY������FX�����ԍ��̈ꗗ���擾����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_strCourseDiv - (�R�[�X�敪)<BR>
     * �R�[�X�敪<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@return FxAccountCodeParams[]
     * @@throws WEB3BaseException
     */
    public FxAccountCodeParams getFXAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strCourseDiv,
        String l_strFxSystemCode) throws WEB3BaseException, NotFoundException;

    /**
     * (get��Е�FX�V�X�e������) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�Ɉ�v�����Е�FX�V�X�e������Params��ԋp����B <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@return webbroker3.aio.data.CompFxConditionParams
     * @@throws WEB3BaseException
     * @@throws NotFoundException
     * @@roseuid 41BEA85203C9
     */
    public CompFxConditionParams getCompFxCondition(
        String l_strInstitutionCode, String l_strBranchCode) throws WEB3BaseException, NotFoundException;

    /**
     * (get����) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�AFX�V�X�e���R�[�h�Ɉ�v����<BR>
     * ����Params��ԋp����B
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@return QuestionParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41C94FB5035C
     */
    public QuestionParams[] getQuestions(String l_strInstitutionCode,
        String l_strBranchCode, String l_strFxSystemCode) throws WEB3BaseException;

    /**
     * (get�����) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�Ɉ�v���� <BR>
     * �����Params��ԋp����B
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strQuestionDiv - ����敪
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return QuestionAnswerParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BEA853006D
     */
    public QuestionAnswerParams[] getQuestionAnswers(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strQuestionDiv, String l_strRequestNumber) throws WEB3BaseException;

    /**
     * (getGFT�����J�ݏ�) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�� <BR>
     * �Y������GFT�����J�ݏ�Params��ԋp����B
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return webbroker3.aio.data.GftAccountOpenStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41BEA853000F
     */
    public GftAccountOpenStatusParams getGFTAccountOpenStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestNumber) throws WEB3BaseException;

    /**
     * (getGFT�����J�ݏ�) <BR>
     * �����̏����ɊY������GFT�����J�ݏ�Params�̈ꗗ��ԋp����B <BR>
     * 
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@return webbroker3.aio.data.GftAccountOpenStatusParams[]
     * @@throws WEB3BaseException
     * @@roseuid 41BEA853003E
     */
    public GftAccountOpenStatusParams[] getGFTAccountOpenStatuses(
        String l_strQueryString, String[] l_queryContainer, String l_strSortCond) 
        throws WEB3BaseException;

    /**
     * (getGFT�U�֏�) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�� <BR>
     * �Y������GFT�U�֏�Params��ԋp����B
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return webbroker3.aio.data.GftTransferStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41C130DC001E
     */
    public GftTransferStatusParams getGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestNumber) throws WEB3BaseException;

    /**
     * (getGFT�U�֏�) <BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A���o���ԍ��� <BR>
     * �Y������GFT�U�֏�Params��ԋp����B<BR>
     * �Q�j�������ʂ�ԋp����B <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strCashInOutNumber - ���o���ԍ�
     * @@return webbroker3.aio.data.GftTransferStatusParams
     * @@throws WEB3BaseException
     * @@roseuid 41C130DC001E
     */
    public GftTransferStatusParams getGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode, String l_strCashInOutNumber) throws WEB3BaseException;

    /**
     * (getGFT�d���ۑ�) <BR>
     * �����̓d����ʋ敪�A�����敪�A�،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�� <BR>
     *  �Y������GFT�d���ۑ�Params��ԋp����B
     * 
     * @@param l_strMessageDiv - �d����ʋ敪
     * @@param l_strOperationDiv - �����敪
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return webbroker3.aio.data.GftMessageParams
     * @@throws WEB3BaseException
     * @@roseuid 41C15A1D0389
     */
    public GftMessageParams getGFTMessage(String l_strMessageDiv,
        String l_strOperationDiv, String l_strInstitutionCode,
        String l_strBranchCode, String l_strRequestNumber) throws WEB3BaseException;

    /**
     * (insertFX�ڋq) <BR>
     * GFT�����J�ݏ�Params�̓��e���A <BR>
     * FX�ڋq�e�[�u��(fx_account)�ɍs��insert���s���B
     * 
     * @@param l_gftAccountOpenStatusParams - GFT�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C8120402A5
     */
    public void insertFXAccount(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (insertFX�ڋq) <BR>
     * GFT���ʒʒm�d���̓��e��FX�ڋq�e�[�u��(fx_account)�ɍs��insert���s���B
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@param l_gftAccontOpenStatusParams - GFT�����J�ݏ�Params
     * @@throws WEB3BaseException
     * @@roseuid 41C9726F03AA
     */
    public void insertFXAccount(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit, 
        GftAccountOpenStatusParams l_gftAccontOpenStatusParams)
        throws WEB3BaseException;

    /**
     * (insertFX�����ԍ�) <BR>
     * GFT�����J�ݏ�Params�AFX�������̓��e���A <BR>
     * FX�����ԍ��e�[�u��(fx_account_code)�ɍs��insert���s���B
     * 
     * @@param l_gftAccountOpenStatusParams - GFT�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_fxAccInformation - FX�������I�u�W�F�N�g
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C8120402C5
     */
    public void insertFXAccountCode(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXAccInformationUnit l_fxAccInformation, String l_strUpdaterCode)
        throws WEB3BaseException;

    /**
     * (insertFX�����ԍ�)<BR>
     * GFT���ʒʒm�d���̓��e��FX�V�X�e���R�[�h�ł�FX�����ԍ��e�[�u��(fx_account_code)<BR>
     * �@@�ɍs��insert���s���B<BR>
     * ���ב֕ۏ؋������ԍ��ʂ�2��insert���s���B<BR>
     * <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT���ʒʒm�d������)<BR>
     * GFT���ʒʒm�d������<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@param l_strSimultaneousFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void insertFXAccountCode(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strFxSystemCode,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException;

    /**
     * (insert�����) <BR>
     * FX������ӎ�����̓��e�Ŏ���񓚃e�[�u��(question_answer)�ɍs��insert���s���B<BR>
     * ������.FX������ӎ�����ꗗ�̗v�f������Loop�������s���A<BR>
     * �v�f���Ƃɍs��insert���s���B
     * 
     * @@param l_strInstitution - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_fxTradeAgreementList - FX������ӎ�����̈ꗗ
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41CF662701CF
     */
    public void insertQuestionAnswer(String l_strInstitution,
        String l_strBranchCode, String l_strRequestNumber,
        WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList,
        String l_strFxSystemCode) throws WEB3BaseException;

    /**
     * (insertGFT�����J�ݏ�) <BR>
     * GFT�˗��d���̓��e��FX�V�X�e���R�[�h��GFT�����J�ݏ󋵃e�[�u���ɍs��insert���s���B<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - GFT�˗��d������
     * @@param l_strAgreementDiv - (������敪)<BR>
     * ������敪<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C961DA03A3
     */
    public void insertGFTAccountOpenStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strAgreementDiv,
        String l_strFxSystemCode) throws WEB3BaseException;

    /**
     * (insertGFT�U�֏�) <BR>
     * GFT�˗��d���̓��e��GFT�U�֏󋵃e�[�u���ɍs��insert���s���B
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT�˗��d������
     * @@param l_strCourseDiv - �R�[�X�敪
     * @@param l_strTransferDate - ��n�\���
     * @@param l_strMrgTrnRequestNumber - �M�p�U�֗p���ʃR�[�h
     * <BR>
     * ���M�p��������̋����U�ւ��s��Ȃ��ꍇ�Anull
     * @@param l_compFxConditionParams - ��Е�FX�V�X�e������Params
     * @@param l_strIoListTradeDiv - ���o���ꗗ����敪
     * @@throws WEB3BaseException
     * @@roseuid 41BEA85300DB
     */
    public void insertGFTTransferStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strCourseDiv, String l_strTransferDate,
        String l_strMrgTrnRequestNumber,
        CompFxConditionParams l_compFxConditionParams,
        String l_strIoListTradeDiv) throws WEB3BaseException;

    /**
     * (insertGFT�U�֏�) <BR>
     * GFT�˗��d���̓��e��GFT�U�֏󋵃e�[�u��(gft_transfer_status)�ɍs��insert���s���B <BR>
     * @@param l_fxGftAskingTelegramUnit - GFT�˗��d������
     * @@param l_strCourseDiv - �R�[�X�敪
     * @@param l_strTransferDate - ��n�\���
     * @@param l_strMrgTrnRequestNumber - �M�p�U�֗p���ʃR�[�h
     * @@param l_strCashInOutNumber - ���o���ԍ�
     * @@param l_strIoListTradeDiv - ���o���ꗗ����敪
     * @@throws WEB3BaseException
     * @@roseuid 41BE98D10109
     */
    public void insertGFTTransferStatus(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        String l_strCourseDiv, String l_strTransferDate,
        String l_strMrgTrnRequestNumber,
        String l_strCashInOutNumber,
        String l_strIoListTradeDiv) throws WEB3BaseException;
    
    /**
     * (insertGFT�d���ۑ�) <BR>
     * GFT�˗��d���̓��e��GFT�d���ۑ��e�[�u���ɍs��insert���s���B
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT�˗��d������
     * @@throws WEB3BaseException
     * @@roseuid 41BEA8530129
     */
    public void insertGFTMessage(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit) throws WEB3BaseException;

    /**
     * (insertGFT�d���ۑ�) <BR>
     * GFT���ʒʒm�d���̓��e��GFT�d���ۑ��e�[�u��(gft_transfer_status)�ɍs��insert���s�� �B
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@throws WEB3BaseException
     * @@roseuid 41C10C4E032B
     */
    public void insertGFTMessage(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) 
        throws WEB3BaseException;

    /**
     * (updateFX�ڋq) <BR>
     * �X�V��̒l��FX�ڋq�e�[�u���̌����J�ݏ󋵋敪���X�V����B
     * 
     * @@param l_fxAccountParams - FX�ڋqParam�I�u�W�F�N�g
     * @@param l_strUpdatedAccOpenStatusDiv - �X�V������J�ݏ󋵋敪
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41BEEAF40018
     */
    public void updateFXAccount(FxAccountParams l_fxAccountParams,
        String l_strUpdatedAccOpenStatusDiv,
        String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (updateFX�ڋq) <BR>
     * �X�V��̒l��FX�ڋq�e�[�u���̃��[���A�h���X���X�V����B
     * 
     * @@param l_fxAccountParams - FX�ڋqParam�I�u�W�F�N�g
     * @@param l_strUpdatedMailAddress - �X�V�チ�[���A�h���X
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@param l_fxSystemCodeList - FX�V�X�e���R�[�h�ꗗ
     * @@throws WEB3BaseException
     * @@roseuid 41BEEAF40018
     */
    public void updateFXAccount(FxAccountParams l_fxAccountParams,
        String l_strUpdatedMailAddress, String l_strUpdaterCode, 
		ArrayList l_fxSystemCodeList) throws WEB3BaseException;

    /**
     * (updateFX�ڋq���[���A�h���X)<BR>
     * �X�V��̒l��FX�ڋq�e�[�u���̃��[���A�h���X���X�V����B<BR>
     * <BR>
     * @@param l_fxAccountParams - (FX�ڋqParams)<BR>
     * FX�ڋqParam�I�u�W�F�N�g<BR>
     * @@param l_strUpdatedMailAddress - (�X�V�チ�[���A�h���X)<BR>
     * �X�V�チ�[���A�h���X<BR>
     * @@param l_strUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BEEAF40018
     */
    public void updateFXAccountMailAddress(
        FxAccountParams l_fxAccountParams,
        String l_strUpdatedMailAddress,
        String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (updateFX�����ԍ�) <BR>
     * �X�V��̒l��FX�����ԍ��e�[�u�����X�V����B
     * 
     * @@param l_fxAccountCodeParams - FX�����ԍ�Param�I�u�W�F�N�g
     * @@param l_strUpdatedAccCode - �X�V���FX�����ԍ�
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C80EA000EC
     */
    public void updateFXAccountCode(FxAccountCodeParams l_fxAccountCodeParams,
        String l_strUpdatedAccCode, String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (updateGFT�����J�ݏ�) <BR>
     * ������GFT�����J�ݏ�Params��GFT�����J�ݏ󋵃e�[�u�����X�V����B
     * 
     * @@param l_gftAccountOpenStatusParams - GFT�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_strUpdatedStatusDiv - �X�V��X�e�[�^�X�敪
     * @@param l_updatedFxAccInformationUnits - �X�V���FX�������̔z��
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@param l_strUpdatedAgreementDiv - (�X�V�������敪)<BR>
     * �X�V�������敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BEA853009C
     */
    public void updateGFTAccountOpenStatus(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strUpdatedStatusDiv,
        WEB3FXAccInformationUnit[] l_updatedFxAccInformationUnits,
        String l_strUpdaterCode, 
        String l_strUpdatedAgreementDiv) throws WEB3BaseException;

    /**
     * (updateGFT�����J�ݏ�) <BR>
     * GFT���ʒʒm�̏�Ԃ�GFT�����J�ݏ󋵃e�[�u���̃f�[�^��update����B
     * 
     * @@param l_gftAccountOpenStatusParams - GFT�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@param l_strErrorReasonCode - �G���[���R�R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C97A830098
     */
    public void updateGFTAccountOpenStatus(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strErrorReasonCode) throws WEB3BaseException;

    /**
     * (updateGFT�U�֏�) <BR>
     * ������GFT�U�֏�Params��GFT�U�֏󋵃e�[�u�����X�V����B
     * 
     * @@param l_gftTransferStatusParams - GFT�U�֏�Params�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 41C130FA031B
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams) throws WEB3BaseException;

    /**
     * (updateGFT�U�֏�) <BR>
     * GFT���ʒʒm�̏�Ԃ�GFT�U�֏󋵃e�[�u���̃f�[�^��update����B
     * 
     * @@param l_gftTransferStatusParams - GFT�U�֏�Params�I�u�W�F�N�g
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@param l_strUpdatedTransferDate - YYYYMMDD
     * 
     * �X�V���n�\���
     * 
     * ����n�\������X�V���Ȃ��ꍇ��null
     * @@param l_strErrorReasonCode - �G���[���R�R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C9714E002C
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams,
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        String l_strUpdatedTransferDate, String l_strErrorReasonCode) 
        throws WEB3BaseException;

    /**
     * (updateGFT�U�֏�) <BR>
     * GFT�U�֏󋵃e�[�u���̐U�֏󋵋敪��update����B <BR>
     * @@param l_gftTransferStatusParams - GFT�U�֏�Params�I�u�W�F�N�g
     * @@param l_strUpdatedTransferStatusDiv - �X�V��̐U�֏󋵋敪
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41CFF87603C9
     */
    public void updateGFTTransferStatus(
        GftTransferStatusParams l_gftTransferStatusParams,
        String l_strUpdatedTransferStatusDiv, String l_strUpdaterCode)
        throws WEB3BaseException;

    /**
     * (updateGFT�U�֏�) <BR>
     * SOAP��M���ʂ�GFT�U�֏󋵃e�[�u���̃f�[�^�ɔ��f����B <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestCode - ���ʃR�[�h
     * @@param l_strResultCode - ��t���ʃR�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41CBC3DD00B3
     */
    public void updateGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strRequestCode, String l_strResultCode) throws WEB3BaseException;
    
    /**
     * (updateFX�����J�݋敪) <BR>
     * �ڋq�}�X�^�[�e�[�u����FX�����J�݋敪��update����B
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strUpdatedFxAccOpenDiv - �X�V��FX�����J�݋敪 0�FDEFAULT(�����Ȃ�) 1�F�����J��
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41CBF73000AF
     */
    public void updateFXAccountOpenDiv(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode,
        String l_strUpdatedFxAccOpenDiv, String l_strUpdaterCode) 
        throws WEB3BaseException;

    /**
     * (createFX�������ꗗ) <BR>
     * �����̏����ɊY������FX�������̈ꗗ���쐬����B
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return webbroker3.aio.message.WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7F0B20097
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException;

    /**
     * (get�V�KFX���O�C��ID) <BR>
     * FX���O�C��ID��t�Ԃ��ĕԋp����B
     * 
     * @@param l_strFXloginFirstChar - FX���O�C��ID������
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return String
     * @@roseuid 41C9666F0298
     */
    public String getNewFXLoginID(String l_strFXloginFirstChar,
        String l_strAccountCode);

    /**
     * (get�V�KFX�ڋqID) <BR>
     * FX�ڋqID��t�Ԃ��ĕԋp����B
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strFxSystemCode - FX�V�X�e���R�[�h
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C9753A02A1
     */
    public String getNewFXAccountID(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode, String l_strFxSystemCode)
        throws WEB3BaseException;

    /**
     * (validateFX������ӎ���) <BR>
     * FX������ӎ���ɑ΂���񓚂̐��������`�F�b�N����B
     * 
     * @@param l_fxTradeAgreementList - FX������ӎ�����̈ꗗ
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public void validateFXTradingAgreeQuestion(
        WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList) 
        throws WEB3BaseException;
    
    /**
     * (submit�������) <BR>
     * �U�֒����̎�������Ɨ]�͂̍X�V�������s���B
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_strMrgTrnRequestNumber - �M�p�U�֗p���ʃR�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public void submitCancelOrder(String l_strInstitutionCode, 
        String l_strBranchCode, String l_strAccountCode, 
        String l_strRequestNumber, String l_strMrgTrnRequestNumber) 
        throws WEB3BaseException;
    
    /**
     * (insert�����s�v�������� ) <BR>
     * �����J�ݐ\�����Ɂu�����s�v�v��I�������ꍇ�A<BR> 
     * FX�����s�v���������Ǘ��e�[�u��.�ɍs��insert���s���B<BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public void insertUnnecessaryExplanation(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode) 
            throws WEB3BaseException;
    
    /**
     * (validateFX�h�L�������g�{������) <BR>
     * �d�q���V�X�e���֐ڑ����A�Y���ڋq�̉{�����������݂���<BR>
     * ���̃`�F�b�N���s���B <BR>
     * 
     * @@param l_strTypeCode - ��ʃR�[�h
     * @@param l_strRequestCode - ���ʃR�[�h
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 41D0F49D02B3
     */
    public String[] validateFxDocReadHistory(
        String l_strTypeCode, 
        String[] l_strRequestCode) 
            throws WEB3BaseException;
    
    /**
     * (get��Е�FX�V�X�e������)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�AFX�V�X�e���R�[�h�Ɉ�v����<BR>
     * ��Е�FX�V�X�e������Params��ԋp����B<BR> 
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)
     * @@return webbroker3.aio.data.CompFxConditionParams
     * @@throws WEB3BaseException 
     * @@throws NotFoundException 
     */
    public CompFxConditionParams getCompFxCondition(
        String l_strInstitutionCode, String l_strBranchCode, String l_strFxSystemCode) 
            throws WEB3BaseException, NotFoundException;

    /**
     * (getFX�U�֏����}�X�^)<BR>
     * ������FX�V�X�e������ID�A�U�֋敪�Ɉ�v����<BR>
     * FX�U�֏����}�X�^Params��ԋp����<BR>
     * <BR>
     * @@param l_lngFxSystemId - (FX�V�X�e������ID)<BR>
     * FX�V�X�e������ID<BR>
     * @@param l_strTransferDiv - (�U�֋敪)<BR>
     * �U�֋敪<BR>
     * @@return FxTransferMasterParams
     * @@throws WEB3BaseException
     */
    public FxTransferMasterParams getFxTransferMasterParams(
        long l_lngFxSystemId,
        String l_strTransferDiv) throws WEB3BaseException;

    /**
     * (get��n��)<BR>
     * ��n�����擾���A�ԋp����B<BR> 
     * <BR>
     * @@param l_datOrderBizDate - (������)
     * @@param l_subAccount - (�⏕����)
     * @@param l_strDeliveryDateSetDiv - (��n���ݒ�敪)
     * @@return Date
     * @@throws WEB3BaseException 
     */
    public Date getDeliveryDate(Date l_datOrderBizDate, SubAccount l_subAccount,
        String l_strDeliveryDateSetDiv) throws WEB3BaseException;
    
    /**
     * (updateFX�����J�݋敪�X�V�҃R�[�h)<BR>
     * �ڋq�}�X�^�[�e�[�u����FX�����J�݋敪�X�V�҃R�[�h��update����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strUpdaterCode- (�X�V�҃R�[�h)
     * @@throws WEB3BaseException 
     */
    public void updateFXAccountOpenDivUpdaterCode(
    	String l_strInstitutionCode, String l_strBranchCode, 
        String l_strAccountCode, String l_strUpdaterCode) 
            throws WEB3BaseException;
    
    /**
     * (get�A�b�v���[�h�ŐV����)<BR>
     * �ŐV�̃A�b�v���[�h�������擾����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strUploadFileID - (�A�b�v���[�h�t�@@�C���h�c)
     * @@param l_strProductType - (�����^�C�v)
     * @@param l_lngDataKey - (�f�[�^�L�[)
     * @@return Object
     * @@throws WEB3BaseException 
     */
    public Object getUploadNewHistory(String l_strInstitutionCode, 
        String l_strUploadFileID, String l_strProductType, long l_lngDataKey) 
            throws WEB3BaseException;
    
    /**
     * (createFX�������ꗗ)<BR>
     * �����̏����ɊY������FX�������̈ꗗ���쐬����B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)
     * @@return WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException 
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException;
    
    /**
     * (getFX�����ԍ�)<BR>
     * �����̏����ɊY������FX�����ԍ��̈ꗗ���擾����B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)
     * @@return FxAccountCodeParams[]
     * @@throws WEB3BaseException 
     */
    public FxAccountCodeParams[] getFXAccountCodes(
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException;
    
    /**
     * (get���o���z�敪)<BR>
     * GFT�d������.���o���z���A�敪��ԋp����B<BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT���ʒʒm�d������)<BR>
     * GFT���ʒʒm�d������<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public String getCashInOutAmountDiv(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) throws WEB3BaseException;

    /**
     * (updateGFT�����J�ݏ�)<BR>
     * SOAP��M���ʂ�GFT�����J�ݏ󋵃e�[�u���̃f�[�^�ɔ��f����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * ���X�R�[�h<BR>
     * @@param l_strOrderRequestNumber - ���ʃR�[�h<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strResultCode - ��t���ʃR�[�h<BR>
     * ��t���ʃR�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void updateGFTAccountOpenStatus(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strOrderRequestNumber,
        String l_strResultCode)
            throws WEB3BaseException;
    
	/**
	 * (validate�ڑ�����) <BR>
	 * SOAP�ڑ��������s���B<BR>
	 * @@param l_rpcParams - �O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams<BR>
	 * �O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams<BR>
	 * @@throws WEB3BaseException
	 */
	public void validateSetup(
			SoapConnectPrefRpcParams l_rpcParams)
		    throws WEB3BaseException;

	/**
	 * (get�ڋq�R�[�h)<BR>
	 * �����̏����ɊY������ڋq�R�[�h���擾����B<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strFXAccountCode - (FX�����ԍ�)<BR>
     * FX�����ԍ�<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException, NotFoundException
	 */
    public String getAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strFXAccountCode,
        String l_strFxSystemCode) throws WEB3BaseException, NotFoundException;

	/**
	 * (getSOAPTFX��t���ʃR�[�h)<BR>
	 * SOAPTFX��t���ʃR�[�h���擾����B<BR>
	 * <BR>
	 * @@param l_strAcceptResultCode - (��t���ʃR�[�h)<BR>
	 * ��t���ʃR�[�h<BR>
	 * @@return String
	 */
	public String getSoapTFXAcceptResultCode(String l_strAcceptResultCode);

    /**
     * (sendSOAP���b�Z�[�W)<BR>
     * SOAP�ڑ����s���B<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - (�d������)<BR>
     * �d������<BR>
     * @@param l_rpcParams - (SOAP�v���t�@@�����X)<BR>
     * �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���jparams<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String sendSoapMessage(
		WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
		SoapConnectPrefRpcParams l_rpcParams) throws WEB3BaseException;

    /**
     * (validate�U�։\)<BR>
     * �����̏����Ŏ���\�����`�F�b�N����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@throws WEB3BaseException
     */
    public void validateChangePoss(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException;

    /**
     * (getGFTFX���[���A�h���X)<BR>
     * FX���[���A�h���X���擾����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����
     * @@param l_lisFxSystemCodeLists - (FX�V�X�e���R�[�h�ꗗ)
     * FX�V�X�e���R�[�h�ꗗ
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getGFTFxMailAddress(
        SubAccount l_subAccount,
        ArrayList l_lisFxSystemCodeLists) throws WEB3BaseException;

    /**
     * (isGFT�����J��)<BR>
     * �ڋq��GFT�����J�ݍςł��邩�`�F�b�N���s���B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_lisFxSystemCodeLists - (FX�V�X�e���R�[�h�ꗗ)<BR>
     * FX�V�X�e���R�[�h�ꗗ<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isGFTAccOpen(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        ArrayList l_lisFxSystemCodeLists) throws WEB3BaseException;

    /**
     * (createFX�������ꗗ)<BR>
     * �����̏����ɊY������FX�������̈ꗗ���쐬����B<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit - (�d������)<BR>
     * GFT�˗��d������<BR>
     * @@param l_resultInfoCreateUser - (�����J�݌���)<BR>
     * �����J�݌���<BR>
     * @@param l_strSameTimeFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@param l_resultInfoAddAccount - (�ǉ��J�݌���)<BR>
     * �ǉ��J�݌���<BR>
     * @@return WEB3FXAccInformationUnit[]
     */
    public WEB3FXAccInformationUnit[] createFXAccInformationUnits(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
        ResultInfoCreateUser l_resultInfoCreateUser,
        String l_strSameTimeFxSystemCode,
        CompFxConditionParams l_compFxConditionParams,
        ResultInfoAddAccount l_resultInfoAddAccount);

    /**
     * (getFX���[���A�h���X)<BR>
     * FX���[���A�h���X���擾����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getFxMailAddress(SubAccount l_subAccount) throws WEB3BaseException;

    /**
     * (get�����J��FX�V�X�e���R�[�h)<BR>
     * �����J�݂���FX�V�X�e���R�[�h���擾����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSameTimeFxSystemCode(String l_strInstitutionCode)
        throws WEB3BaseException;

    /**
     * (getGFTFX�V�X�e���R�[�h�ꗗ)<BR>
     * ����.FX�V�X�e���R�[�h���֘A����O���@@�ւ́A�S�Ă�FX�V�X�e���R�[�h���擾����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException
     */
    public ArrayList getGFTFxSystemCodeLists(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException;

    /**
     * (insert���������J��)<BR>
     * GFT���ʒʒm�d���̓��e��FX�ڋq�e�[�u��(fx_account)�ɍs��insert���s���B<BR>
     * <BR>
     * @@param l_fxGftResultNoticeTelegramUnit - (GFT���ʒʒm�d������)<BR>
     * GFT���ʒʒm�d������<BR>
     * @@param l_gftAccountOpenStatusParams - (GFT�����J�ݏ�Params)<BR>
     * GFT�����J�ݏ�Params<BR>
     * @@param l_strSimultaneousFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void insertSimultaneousAccountOpen(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit,
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException;

    /**
     * (insert���������J��)<BR>
     * GFT���ʒʒm�d���̓��e��FX�ڋq�e�[�u��(fx_account)�ɍs��insert���s���B<BR>
     * <BR>
     * @@param l_gftAccountOpenStatusParams - (GFT�����J�ݏ�Params)<BR>
     * GFT�����J�ݏ�Params<BR>
     * @@param l_strSimultaneousFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@param l_strUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void insertSimultaneousAccountOpen(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        String l_strSimultaneousFxSystemCode,
        String l_strUpdaterCode) throws WEB3BaseException;

    /**
     * (insertFX�����ԍ�)<BR>
     * GFT�����J�ݏ�Params�AFX�������̓��e���A<BR>
     * FX�����ԍ��e�[�u��(fx_account_code)�ɍs��insert���s���B<BR>
     * <BR>
     * @@param l_gftAccountOpenStatusParams - (GFT�����J�ݏ�Params)<BR>
     * GFT�����J�ݏ�Params<BR>
     * @@param l_fxAccInformation - (FX�������)<BR>
     * FX�������<BR>
     * @@param l_strUpdateCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@param l_strSimultaneousFxSystemCode - (�����J��FX�V�X�e���R�[�h)<BR>
     * �����J��FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void insertFXAccountCode(
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams,
        WEB3FXAccInformationUnit l_fxAccInformation,
        String l_strUpdateCode,
        String l_strSimultaneousFxSystemCode) throws WEB3BaseException;
    
    /**
     * (insertSOAPMessage)<BR>
     * SOAP���b�Z�[�W�ۑ��e�[�u����SOAP���b�Z�[�W��ۑ�����B<BR>
     * <BR>
     * �P�jSOAP���b�Z�[�W�ۑ�Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * ���XID�F ����.���XID<BR>
     * �ڑ��敪�F ����.�ڑ��敪<BR>
     * ����M�敪�F ����.����M�敪<BR>
     * ���b�Z�[�W�F ����.���b�Z�[�W<BR>
     * �쐬���t�F �V�X�e���^�C���X�^���v<BR>
     * <BR>
     * �R�j�e�[�u���ɃC���T�[�g����B<BR>
     * @@param l_lngBranchId - ���XID
     * @@param l_strConnectDiv - �ڑ��敪
     * @@param l_strSendReceiveDiv - ����M�敪
     * @@param l_strMessage - ���b�Z�[�W
     * 
     */
    
    public void insertSOAPMessage(
            long l_lngBranchId, 
            String l_strConnectDiv, 
            String l_strSendReceiveDiv,
            String l_strMessage);

    /**
     * (get�ϊ�FX���O�C��ID )<BR>
     * <BR>
     * FX���O�C��ID��ϊ����A�ԋp����B<BR>
     * @@param l_lngInstitutionID - (�،����ID)<BR>
     * �،����ID<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@param l_strFXLoginFirstChar - (FX���O�C��ID������)<BR>
     * FX���O�C��ID������<BR>
     * @@param l_lngFXLoginID - (FX���O�C���h�c)<BR>
     * FX���O�C���h�c<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangedFXLoginID(
        long l_lngInstitutionID,
        String l_strFxSystemCode,
        String l_strFXLoginFirstChar,
        long l_lngFXLoginID) throws WEB3BaseException;
}@


1.1
log
@*** empty log message ***
@
text
@a36 6
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.gftforex.soap.api.ResultInfoCreateUser;
import com.gftforex.soap.api.ResultInfoAddAccount;

d52 6
@

