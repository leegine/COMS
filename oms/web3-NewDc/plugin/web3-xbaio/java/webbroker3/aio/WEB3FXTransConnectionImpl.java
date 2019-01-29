head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransConnectionImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐ڑ�Impl(WEB3FXTransConnectionImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/16 �����F (���u) �V�K�쐬 �d�l�ύX���f��1195 1228
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�U�֐ڑ�Impl)<BR>
 * �U�֐ڑ�Impl<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FXTransConnectionImpl extends WEB3FXConnCommonServiceImpl
    implements WEB3FXTransConnection
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransConnectionImpl.class);

    /**
     * (updateGFT�U�֏�)<BR>
     * ��M���ʂ�GFT�U�֏󋵃e�[�u���̃f�[�^�ɔ��f����B <BR>
     * <BR>
     * �P�jFX�f�[�^����T�[�r�X.getGFT�U�֏󋵁i�j���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.���X�R�[�h <BR>
     * ���ʃR�[�h�F ����.���ʃR�[�h <BR>
     * <BR>
     * �Q�jFX�f�[�^����T�[�r�X.getGFT�U�֏󋵁i�j�̖߂�l�̐U�֏󋵋敪 ���� <BR>
     * �u3�F����v�̏ꍇ�A <BR>
     * �@@�u���̑���FX�V�X�e���G���[�v(BUSINESS_ERROR_01800)�Ƃ��ė�O��throw����B <BR>
     * <BR>
     * �R�jFX�f�[�^����T�[�r�X.updateGFT�U�֏󋵁i�j���R�[������B <BR>
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
    public void updateGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strResultCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateGFTTransferStatus(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�jFX�f�[�^����T�[�r�X.getGFT�U�֏󋵁i�j���R�[������B
        //[����]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //���ʃR�[�h�F ����.���ʃR�[�h
        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        GftTransferStatusParams l_gftTransferStatusParams =
            l_fxDataControlService.getGFTTransferStatus(l_strInstitutionCode, l_strBranchCode, l_strRequestNumber);

        //�Q�jFX�f�[�^����T�[�r�X.getGFT�U�֏󋵁i�j�̖߂�l�̐U�֏󋵋敪 ����
        //�u3�F����v�̏ꍇ�A
        //�@@�u���̑���FX�V�X�e���G���[�v(BUSINESS_ERROR_01800)�Ƃ��ė�O��throw����B
        if (WEB3TransferStatusDivDef.CANCEL.equals(l_gftTransferStatusParams.getTransferStatusDiv()))
        {
            log.debug("���̑���FX�V�X�e���G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���̑���FX�V�X�e���G���[�B");
        }

        //�R�jFX�f�[�^����T�[�r�X.updateGFT�U�֏󋵁i�j���R�[������B
        //[����]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //���ʃR�[�h�F ����.���ʃR�[�h
        //��t���ʃR�[�h�F ����.��t���ʃR�[�h
        l_fxDataControlService.updateGFTTransferStatus(
            l_strInstitutionCode, l_strBranchCode, l_strRequestNumber, l_strResultCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (do�U�֎��s)<BR>
     * �U�֎��s���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �udo�U�֎��s�v�Q��<BR>
     * @@param l_compFxConditionParams  - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@param l_fXGftAskingTelegramUnit - (GFT�˗��d������)<BR>
     * GFT�˗��d������<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXGftResultNoticeTelegramUnit doTransfer(
        CompFxConditionParams l_compFxConditionParams, WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "doTransfer(CompFxConditionParams, WEB3FXGftAskingTelegramUnit)";
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
        //��t���ʃR�[�h�F �ԋp�����O���ڑ�.getResult(�O���ڑ�.CONNECT_RESULT)
        this.updateGFTTransferStatus(
            l_fXGftAskingTelegramUnit.institutionCode,
            l_fXGftAskingTelegramUnit.branchCode,
            l_fXGftAskingTelegramUnit.requestNumber,
            (String)l_extConnection.getResult(WEB3ExtConnection.CONNECT_RESULT));

        //createGFT���ʒʒm�d������(GFT�˗��d������, FX�������[], String)
        //[����]
        //GFT�˗��d�����ׁF ����.GFT�˗��d������
        //FX�������ꗗ�F  null
        //��t���ʃR�[�h�F �ԋp�����O���ڑ�.getResult(�O���ڑ�.RESULT_CODE)�̖߂�l
        String l_strResultCode = (String)l_extConnection.getResult(WEB3ExtConnection.RESULT_CODE);
        WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
            this.createGftResultNoticeTelegramUnit(l_fXGftAskingTelegramUnit, null, l_strResultCode);

        log.exiting(STR_METHOD_NAME);
        return l_fXGftResultNoticeTelegramUnit;
    }
}
@
