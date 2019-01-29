head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.31.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXAccOpenConnectionImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐڑ�Impl(WEB3FXAccOpenConnectionImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/16 �����F (���u) �V�K�쐬 �d�l�ύX���f��1195
*/
package webbroker3.aio;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݐڑ�Impl)<BR>
 * �����J�ݐڑ�Impl<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FXAccOpenConnectionImpl extends WEB3FXConnCommonServiceImpl
    implements WEB3FXAccOpenConnection
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenConnectionImpl.class);

    /**
     * (do�����J�ݎ��s)<BR>
     * �����J�ݎ��s���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �udo�����J�ݎ��s�v�Q��<BR>
     * @@param l_compFxConditionParams  - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@param l_fXGftAskingTelegramUnit - (GFT�˗��d������)<BR>
     * GFT�˗��d������<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXGftResultNoticeTelegramUnit doAccountOpen(
        CompFxConditionParams l_compFxConditionParams, WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "doAccountOpen(CompFxConditionParams, WEB3FXGftAskingTelegramUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_fXGftAskingTelegramUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //send�O���ڑ��˗����b�Z�[�W(��Е�FX�V�X�e������Params, GFT�˗��d������)
        //[����]
        //       �@@��Е�FX�V�X�e������ = ����.��Е�FX�V�X�e������Params
        //       �@@GFT�˗��d������ = ����.GFT�˗��d������
        WEB3ExtConnection l_extConnection =
            this.sendExtConnAskingMessage(l_compFxConditionParams, l_fXGftAskingTelegramUnit);

        //updateGFT�����J�ݏ�
        //[����]
        //�،���ЃR�[�h�F ����.GFT�˗��d������.��ЃR�[�h
        //���X�R�[�h�F ����.GFT�˗��d������.���X�R�[�h
        //���ʃR�[�h�F ����.GFT�˗��d������.���ʃR�[�h
        //��t���ʃR�[�h�F 1.1�ŕԋp�����O���ڑ�.getResult(�O���ڑ�.CONNECT_RESULT)�̖߂�l
        this.updateGFTAccountOpenStatus(
            l_fXGftAskingTelegramUnit.institutionCode,
            l_fXGftAskingTelegramUnit.branchCode,
            l_fXGftAskingTelegramUnit.requestNumber,
            (String)l_extConnection.getResult(WEB3ExtConnection.CONNECT_RESULT));

         //FX�������ꗗ�𐶐�����
        //�ԋp�����O���ڑ�����AFX�������ꗗ���擾����F
        List l_lisFXAccInformationUnits = new ArrayList();

        //�O���ڑ�.getResult(�O���ڑ�.FX_ACC_01) != null�̏ꍇ�A
        //�@@FX�������FX�������z��ɒǉ�����B
        //�@@�@@�����ԍ�:�O���ڑ�.getResult�̖߂�l
        //�@@�@@�R�[�X�敪�F1�F1���ʉ݃R�[
        Long l_resultFXACC01 = (Long)l_extConnection.getResult(WEB3ExtConnection.FX_ACC_01);
        if (l_resultFXACC01 != null)
        {
            WEB3FXAccInformationUnit l_fXAccInformationUnit = new WEB3FXAccInformationUnit();
            l_fXAccInformationUnit.fxAccountCode = l_resultFXACC01 + "";
            l_fXAccInformationUnit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.ONE_COSE;
            l_lisFXAccInformationUnits.add(l_fXAccInformationUnit);
        }

        //�O���ڑ�.getResult(�O���ڑ�.FX_ACC_10) != null�̏ꍇ�A
        //�@@FX�������FX�������z��ɒǉ�����B
        //�@@�@@�����ԍ�:�O���ڑ�.getResult�̖߂�l
        //�@@�@@�R�[�X�敪�F2�F10���ʉ݃R�[�X
        Long l_resultFXACC10 = (Long)l_extConnection.getResult(WEB3ExtConnection.FX_ACC_10);
        if (l_resultFXACC10 != null)
        {
            WEB3FXAccInformationUnit l_fXAccInformationUnit = new WEB3FXAccInformationUnit();
            l_fXAccInformationUnit.fxAccountCode = l_resultFXACC10 + "";
            l_fXAccInformationUnit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.TEN_COSE;
            l_lisFXAccInformationUnits.add(l_fXAccInformationUnit);
        }

        //�O���ڑ�.getResult(�O���ڑ�.CFD_ACC) != null�̏ꍇ�A
        //�@@FX�������FX�������z��ɒǉ�����B
        //�@@�@@�����ԍ�:�O���ڑ�.getResult�̖߂�l
        //�@@�@@�R�[�X�敪�F3�FCFD�R�[�X
        Long l_resultCFDACC = (Long)l_extConnection.getResult(WEB3ExtConnection.CFD_ACC);
        if (l_resultCFDACC != null)
        {
            WEB3FXAccInformationUnit l_fXAccInformationUnit = new WEB3FXAccInformationUnit();
            l_fXAccInformationUnit.fxAccountCode = l_resultCFDACC + "";
            l_fXAccInformationUnit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE;
            l_lisFXAccInformationUnits.add(l_fXAccInformationUnit);
        }

        WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
            new WEB3FXAccInformationUnit[l_lisFXAccInformationUnits.size()];
        l_lisFXAccInformationUnits.toArray(l_fXAccInformationUnits);

        //createGFT���ʒʒm�d������(GFT�˗��d������, FX�������[], String)
        //[����]
        //GFT�˗��d�����ׁF ����.GFT�˗��d������
        //FX�������ꗗ�F 1.3�Ő�������FX�������ꗗ
        //��t���ʃR�[�h�F 1.1�ŕԋp�����O���ڑ�.getResult(�O���ڑ�.RESULT_CODE)�̖߂�l
        String l_strResultCode = (String)l_extConnection.getResult(WEB3ExtConnection.RESULT_CODE);
        WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
            this.createGftResultNoticeTelegramUnit(l_fXGftAskingTelegramUnit, l_fXAccInformationUnits, l_strResultCode);

        log.exiting(STR_METHOD_NAME);
        return l_fXGftResultNoticeTelegramUnit;
    }

    /**
     * (updateGFT�����J�ݏ�)<BR>
     * ��M���ʂ�GFT�����J�ݏ󋵃e�[�u���̃f�[�^�ɔ��f����B <BR>
     * <BR>
     * �P�jFX�f�[�^����T�[�r�X.getGFT�����J�ݏ󋵁i�j���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * ���ʃR�[�h�F ����.���ʃR�[�h <BR>
     * <BR>
     * �Q�jFX�f�[�^����T�[�r�X.getGFT�����J�ݏ󋵁i�j�̖߂�l�̌����J�ݏ󋵋敪 �I�� <BR>
     * �u0�F�����J�ݒ��v�̏ꍇ�A <BR>
     * �@@�u���̑���FX�V�X�e���G���[�v(BUSINESS_ERROR_01800)�Ƃ��ė�O��throw����B <BR>
     * <BR>
     * �R�jFX�f�[�^����T�[�r�X.updateGFT�����J�ݏ󋵁i�j���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * ���ʃR�[�h�F ����.���ʃR�[�h <BR>
     * ��t���ʃR�[�h�F ����.��t���ʃR�[�h <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strResultCode - (��t���ʃR�[�h)<BR>
     * ��t���ʃR�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void updateGFTAccountOpenStatus(
        String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strResultCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateGFTAccountOpenStatus(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�jFX�f�[�^����T�[�r�X.getGFT�����J�ݏ󋵁i�j���R�[������B
        //[����]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //���ʃR�[�h�F ����.���ʃR�[�h
        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
            l_fxDataControlService.getGFTAccountOpenStatus(l_strInstitutionCode, l_strBranchCode, l_strRequestNumber);

        //�Q�jFX�f�[�^����T�[�r�X.getGFT�����J�ݏ󋵁i�j�̖߂�l�̌����J�ݏ󋵋敪 �I��
        //�u0�F�����J�ݒ��v�̏ꍇ�A
        //�@@�u���̑���FX�V�X�e���G���[�v(BUSINESS_ERROR_01800)�Ƃ��ė�O��throw����B
        if (!WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(
            l_gftAccountOpenStatusParams.getAccountOpenStatusDiv()))
        {
            log.debug("���̑���FX�V�X�e���G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���̑���FX�V�X�e���G���[�B");
        }

        //�R�jFX�f�[�^����T�[�r�X.updateGFT�����J�ݏ󋵁i�j���R�[������B
        //[����]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //���ʃR�[�h�F ����.���ʃR�[�h
        //��t���ʃR�[�h�F ����.��t���ʃR�[�h
        l_fxDataControlService.updateGFTAccountOpenStatus(
            l_strInstitutionCode, l_strBranchCode, l_strRequestNumber, l_strResultCode);

        log.exiting(STR_METHOD_NAME);
    }
}
@
