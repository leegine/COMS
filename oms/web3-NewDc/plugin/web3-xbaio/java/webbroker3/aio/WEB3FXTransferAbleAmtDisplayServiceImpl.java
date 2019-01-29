head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.26.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransferAbleAmtDisplayServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�։\�z�\���T�[�r�XImpl(WEB3FXTransferAbleAmtDisplayServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 �����F (���u) �V�K�쐬 �d�l�ύX�E���f��1174,1183
Revision History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1208,1209,1210,1222,1229,1233
Revision History : 2009/10/27 �����F (���u) �d�l�ύX�E���f��1248
*/

package webbroker3.aio;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�U�։\�z�\���T�[�r�XImpl)<BR>
 * �U�։\�z�\���T�[�r�XImpl<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FXTransferAbleAmtDisplayServiceImpl implements WEB3FXTransferAbleAmtDisplayService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransferAbleAmtDisplayServiceImpl.class);

    /**
     * (getFX����U�։\�z�i�`�F�b�N�Ȃ��j)<BR>
     * FX����U�։\�z�i�`�F�b�N�Ȃ��j���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �ugetFX����U�։\�z�i�`�F�b�N�Ȃ��j�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������)<BR>
     * ��Е�FX�V�X�e������<BR>
     * @@return WEB3FXTransferAbleAmtUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FXTransferAbleAmtUnit[] getFXTransferAbleAmtNoCheck(
        SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getFXTransferAbleAmtCheck(SubAccount, CompFxConditionParams)";
        log.entering (STR_METHOD_NAME);

        //create�U�։\�z�˗��d������
        //[����]
        //�⏕�����F�@@�@@�@@�@@�@@�@@�@@�@@�@@����.�⏕���� 
        //��Е�FX�V�X�e�������F�@@����.��Е�FX�V�X�e������
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
            (WEB3FXGftAskingTelegramUnit)this.createTransferAbleAmtAskingTelegramUnit(l_subAccount, l_compFxConditionParams);
        //send�O���ڑ��˗����b�Z�[�W
        //[����]
        //��Е�FX�V�X�e�������F�@@����.��Е�FX�V�X�e������
        //GFT�˗��d�����ׁF �擾����GFT�˗��d������
        WEB3FXConnCommonService l_service =
            (WEB3FXConnCommonService)Services.getService(
                WEB3FXConnCommonService.class);
        WEB3ExtConnection l_extConnection =
            l_service.sendExtConnAskingMessage(
                l_compFxConditionParams,
                l_fxGftAskingTelegramUnit);
        //�ԋp�����O���ڑ�����O���ڑ�.RESULT_CODE���擾����
        String l_strResultCode = (String)l_extConnection.getResult(WEB3ExtConnection.RESULT_CODE);

        //�O���ڑ�.RESULT_CODE = �hGFT�ڑ��G���[�h(00000990)�̏ꍇ
        if (WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990.equals(l_strResultCode))
        {
            log.debug("�O���V�X�e���ڑ��G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O���V�X�e���ڑ��G���[�B");
        }

        //createFX�������ꗗ(�،���ЃR�[�h : String, ���X�R�[�h : String,
        //  �ڋq�R�[�h : String, FX�V�X�e���R�[�h : String)
        //[����]
        // �،���ЃR�[�h�F����.��Е�FX�V�X�e������.�،���ЃR�[�h
        // ���X�R�[�h�F����.��Е�FX�V�X�e������.���X�R�[�h
        // �ڋq�R�[�h�F����.�⏕��������擾�����ڋq�R�[�h
        // FX�V�X�e���R�[�h�F����.��Е�FX�V�X�e������.FX�V�X�e���R�[�h
        WEB3FXDataControlService l_dataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);

        WEB3FXAccInformationUnit[] l_accInformationUnits =
            l_dataControlService.createFXAccInformationUnits(
                l_compFxConditionParams.getInstitutionCode(),
                l_compFxConditionParams.getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_compFxConditionParams.getFxSystemCode());

        //(*)�v���p�e�B�Z�b�g
        //FX����U�։\�z���[].FX������� = createFX�������ꗗ()�̖߂�l
        int l_intAccInformationUnitsLength = 0;
        if (l_accInformationUnits != null)
        {
            l_intAccInformationUnitsLength = l_accInformationUnits.length;
        }

        //FX����U�։\�z���( )
        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnits =
            new WEB3FXTransferAbleAmtUnit[l_intAccInformationUnitsLength];
        for (int i = 0; i < l_intAccInformationUnitsLength; i++)
        {
            l_transferAbleAmtUnits[i] = new WEB3FXTransferAbleAmtUnit();
            //FX����U�։\�z���.�R�[�X�敪 = createFX�������ꗗ()�̖߂�l�̗v�f.�R�[�X�敪
            l_transferAbleAmtUnits[i].fxCourseDiv = l_accInformationUnits[i].fxCourseDiv;

            //FX����U�։\�z���.�����ԍ� = createFX�������ꗗ()�̖߂�l�̗v�f.�����ԍ�
            l_transferAbleAmtUnits[i].fxAccountCode = l_accInformationUnits[i].fxAccountCode;

            //FX����U�։\�z���.�U�։\�z = null
            l_transferAbleAmtUnits[i].transferableAmt = null;
        }

        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnitsReturns = null;
        //get���ʃR�[�h�擾�������ʃR�[�h == 00000000�F���������̏ꍇ
        if (WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000000.equals(l_strResultCode))
        {
            //get�U�։\�z
            //[����]
            //�O���ڑ��Fsend�O���ڑ��˗����b�Z�[�W()�̖߂�l 
            //FX����U�։\�z���[]�F1.4�Ő�������FX����U�։\�z���[]
            l_transferAbleAmtUnitsReturns =
                this.getTransferAbleAmt(
                    l_extConnection, l_transferAbleAmtUnits);

            //FX����U�։\�z����ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_transferAbleAmtUnitsReturns;
        }

        //FX����U�։\�z����ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_transferAbleAmtUnits;
    }

    /**
     * (getFX����U�։\�z�i�`�F�b�N����j)<BR>
     * FX����U�։\�z�i�`�F�b�N����j���擾����B<BR>
     * <BR>
     * �P�j�@@this.getFX����U�։\�z�i�`�F�b�N�Ȃ��j���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�⏕�����F����.�⏕����<BR>
     * �@@�@@�@@��Е�FX�V�X�e�������F����.��Е�FX�V�X�e������<BR>
     * <BR>
     * �Q�j�@@FX����U�։\�z��񂪎擾�ł��Ȃ��ꍇ�A��O�uSYSTEM_ERROR_80005�v���X���[����B<BR>
     * <BR>
     * �R�j�@@�R�|�P�j�@@�P�j�Ŗ߂�l�������.�R�[�X�敪�ɂ���ĊY������e�w����<BR>
     * �@@�@@�@@�@@�@@�U�։\�z�����擾����B<BR>
     * �@@�@@�@@�R�|�Q�j�@@�w�肵���R�[�X�敪�ɂ���āA<BR>
     * �@@�@@�@@�@@�@@�Y������U�։\�z���.�R�[�X�敪�����݂��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O�u�w�肵���R�[�X�̉\�z���擾�ł��܂���v���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:   BUSINESS_ERROR_03162<BR>
     * �@@�@@�@@�R�|�R�j�@@�R�|�P�j�Ŏ擾�����U�։\�z != null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�@@�R�|�S�j�@@�R�|�P�j�Ŏ擾�����u�U�։\�z�v�͈���.�U�֋��z��菬�����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O�u�U�֋��z���\�z�𒴂��Ă��܂��v���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:   BUSINESS_ERROR_00761<BR>
     * �S�j�@@�R�|�P�j�Ŏ擾�����u�e�w����U�։\�z���v��ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������)<BR>
     * ��Е�FX�V�X�e������<BR>
     * @@param l_strTransferAmount - (�U�֋��z)<BR>
     * �U�֋��z<BR>
     * @@param l_strCourseDiv - (�R�[�X�敪)<BR>
     * �R�[�X�敪<BR>
     * @@return WEB3FXTransferAbleAmtUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXTransferAbleAmtUnit getFXTransferAbleAmtCheck(
        SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams,
        String l_strTransferAmount, String l_strCourseDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFXTransferAbleAmtNoCheck("
            + "SubAccount, CompFxConditionParams, String, String)";
        log.entering (STR_METHOD_NAME );

        //this.getFX����U�։\�z�i�`�F�b�N�Ȃ��j���R�[������
        //[����]
        // �⏕�����F����.�⏕����
        // ��Е�FX�V�X�e�������F����.��Е�FX�V�X�e������
        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnits =
            this.getFXTransferAbleAmtNoCheck(l_subAccount, l_compFxConditionParams);

        int l_intLength = 0;
        if (l_transferAbleAmtUnits == null || l_transferAbleAmtUnits.length == 0)
        {
            //�Q�j�@@FX����U�։\�z��񂪎擾�ł��Ȃ��ꍇ�A��O�uSYSTEM_ERROR_80005�v���X���[����
            log.debug("FX�������擾�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        else
        {
            l_intLength = l_transferAbleAmtUnits.length;
        }

        //�R�|�P�j�P�j�Ŗ߂�l�������.�R�[�X�敪�ɂ���ĊY������e�w����U�։\�z�����擾����B
        int l_intCount = 0;
        boolean l_blnHasFlag = false;
        for (int i = 0; i < l_intLength; i++)
        {
            if (WEB3Toolkit.isEquals(l_strCourseDiv, l_transferAbleAmtUnits[i].fxCourseDiv))
            {
                l_blnHasFlag = true;
                l_intCount = i;
                break;
            }
        }

        if (l_blnHasFlag)
        {
            if (l_transferAbleAmtUnits[l_intCount].transferableAmt != null)
            {
                //�R�|�S�j�@@�R�|�P�j�Ŏ擾�����U�։\�z != null�̏ꍇ�A�ȉ��̏������s��
                //�R�|�T�j�@@�R�|�P�j�Ŏ擾�����u�U�։\�z�v�͈���.�U�֋��z��菬�����ꍇ�A
                // ��O�u�U�֋��z���\�z�𒴂��Ă��܂��v���X���[����B
                if (Double.parseDouble(l_transferAbleAmtUnits[l_intCount].transferableAmt)
                    < Double.parseDouble(l_strTransferAmount))
                {
                    log.debug("�U�֋��z���\�z�𒴂��Ă��܂��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00761,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�U�֋��z���\�z�𒴂��Ă��܂��B");
                }
            }

            //�擾�����u�e�w����U�։\�z���v��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_transferAbleAmtUnits[l_intCount];
        }
        else
        {
            //FX����U�։\�z��񂪎擾�ł��Ȃ��ꍇ�A
            //�w�肵���R�[�X�敪�ɂ���āA�Y������U�։\�z���.�R�[�X�敪�����݂��Ȃ��ꍇ
            //��O�u�w�肵���R�[�X�̉\�z���擾�ł��܂���v���X���[����
            log.debug("�w�肵���R�[�X�̉\�z���擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03162,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w�肵���R�[�X�̉\�z���擾�ł��܂���B");
        }
    }
    
    /**
     * (get�U�։\�z)<BR>
     * <BR>
     * �U�։\�z���擾����B <BR>
     * <BR>
     * �P�j����.�O���ڑ�.getResult()���Ăяo���B<BR>
     * [����] <BR>
     * ���ږ��F �O���ڑ�.AMOUNT<BR>
     * <BR>
     * �Q�j�Ŏ擾�����U�։\�zhashmap����A�e�����ԍ���key�Ƃ��āA�ׂׂɒl��^����B<BR>
     * �@@�@@����.FX����U�։\�z���[��].�����ԍ� == �P�j�Ŏ擾�����v�f.�����ԍ��̏ꍇ�A<BR>
     * �@@�@@FX����U�։\�z���[��].�U�։\�z = �P�j�Ŏ擾�����v�f�̌����ԍ���key�Ƃ��āA<BR>
     * �@@�@@�擾����hashmap��value�B<BR>
     * �@@�@@�i*�j�U�։\�z���擾�ł��Ȃ��ꍇ�A�������͎擾�����l�����l�ȊO�̏ꍇ�Anull���Z�b�g����B<BR>
     * <BR>
     * �R�jFX����U�։\�z����ԋp����B<BR>
     * <BR>
     * @@param l_extConnection - (�O���ڑ�)<BR>
     * �O���ڑ�<BR>
     * @@param l_transferAbleAmtUnit - (FX����U�։\�z���)<BR>
     * FX����U�։\�z���<BR>
     * @@return WEB3FXTransferAbleAmtUnit[]
     */
    protected WEB3FXTransferAbleAmtUnit[] getTransferAbleAmt(
        WEB3ExtConnection l_extConnection,
        WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnit)
    {
        final String STR_METHOD_NAME = "getTransferAbleAmt(WEB3ExtConnection, WEB3FXTransferAbleAmtUnit)";
        log.entering (STR_METHOD_NAME );
        //����.�O���ڑ�.getResult()���Ăяo���B
        //[����]
        //���ږ��F �O���ڑ�.AMOUNT
        Object l_result = l_extConnection.getResult(WEB3ExtConnection.AMOUNT);

        HashMap l_hmAmount = null;
        if (l_result != null)
        {
            l_hmAmount = (HashMap)l_result;
        }
        else
        {
            l_hmAmount = new HashMap();
        }
        //�擾�����U�։\�zhashmap����A�e�����ԍ���key�Ƃ��āA�ׂׂɒl��^����
        //����.FX����U�։\�z���[��].�����ԍ� == �P�j�Ŏ擾�����v�f.�����ԍ��̏ꍇ�A
        //FX����U�։\�z���[��].�U�։\�z = �P�j�Ŏ擾�����v�f�̌����ԍ���key�Ƃ��āA
        //�擾����hashmap��value�B�@@
        for (int i = 0; i < l_transferAbleAmtUnit.length; i++)
        {
            if (l_hmAmount.containsKey(l_transferAbleAmtUnit[i].fxAccountCode))
            {
                String l_strTransferableAmt = (String)l_hmAmount.get(l_transferAbleAmtUnit[i].fxAccountCode);
                if (WEB3StringTypeUtility.isNumber(l_strTransferableAmt))
                {
                    l_transferAbleAmtUnit[i].transferableAmt = l_strTransferableAmt;
                }
                else 
                {
                    //�擾�����l�����l�ȊO�̏ꍇ�Anull���Z�b�g����B
                    l_transferAbleAmtUnit[i].transferableAmt = null;
                }
            }
            else
            {
                //�i*�j�U�։\�z���擾�ł��Ȃ��ꍇ�Anull���Z�b�g����B
                l_transferAbleAmtUnit[i].transferableAmt = null;
            }
        }

        //FX����U�։\�z����ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_transferAbleAmtUnit;
    }
    
    /**
     * (create�U�։\�z�˗��d������)<BR>
     * <BR>
     * �˗��d�����ׂ��쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �ucreate�U�։\�z�˗��d�����ׁv�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������)<BR>
     * ��Е�FX�V�X�e������<BR>
     * @@return Message
     * @@throws WEB3BaseException
     */
    protected Message createTransferAbleAmtAskingTelegramUnit(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createTransferAbleAmtAskingTelegramUnit("
            + "SubAccount, CompFxConditionParams)";
        log.entering (STR_METHOD_NAME );
        //getFX�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, FX�V�X�e���R�[�h : String, �ڋq�R�[�h : String)
        WEB3FXDataControlService l_dataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //�،���ЃR�[�h = �⏕��������擾����.�،���ЃR�[�h
        //���X�R�[�h = �⏕��������擾����.���X�R�[�h
        //FX�V�X�e���R�[�h = ��Е�FX�V�X�e������.FX�V�X�e���R�[�h
        //�ڋq�R�[�h = �⏕��������擾�����ڋq�R�[�h
        FxAccountParams l_fxAccountParams = null;
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        try
        {
            l_fxAccountParams = l_dataControlService.getFXAccount(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_compFxConditionParams.getFxSystemCode(),
                l_subAccount.getMainAccount().getAccountCode());
        } 
        catch (NotFoundException l_ex)
        {
            log.error(" �e�[�u���ɊY������f�[�^������܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //get�ϊ�FX���O�C��ID(�،����ID : long, FX�V�X�e���R�[�h : String, FX���O�C��ID������ : String, FX���O�C���h�c : long)
        //�،����ID�F�@@����.�⏕��������擾�����،����ID
        //FX�V�X�e���R�[�h�F�@@����.��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        //FX���O�C��ID�������F�@@����.��Е�FX�V�X�e������Params.FX���O�C��ID������
        //FX���O�C��ID�F�@@1.1�j�Ŏ擾����FX�ڋqParams.FX���O�C��ID
        String l_strChangedFXLoginID = l_dataControlService.getChangedFXLoginID(
            l_subAccount.getInstitution().getInstitutionId(),
            l_compFxConditionParams.getFxSystemCode(),
            l_compFxConditionParams.getFxHeadOfLoginId(),
            l_fxAccountParams.getFxLoginId());

        // get�V�K���ʃR�[�h(�،���ЃR�[�h : String, ���X�R�[�h : String, �����^�C�v : ProductTypeEnum)
        // [����] 
        // �،���ЃR�[�h�F  ����.��Е�FX�V�X�e������.�،���ЃR�[�h
        // ���X�R�[�h�F ����.��Е�FX�V�X�e������.���X�R�[�h
        // �����^�C�v�F 5�i�����j
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_compFxConditionParams.getInstitutionCode(), 
            l_compFxConditionParams.getBranchCode(), 
            ProductTypeEnum.CASH);

        //GFT�˗��d�����ׂ̃C���X�^���X�𐶐�����B 
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
        //�i*�j�v���p�e�B�Z�b�g
        //(*)GFT�˗��d�����ׂɕK�v�ȃv���p�e�B���Z�b�g����
        //�i���L�ȊO�̃v���p�e�B�͐ݒ肵�Ȃ��j
        //DIR��GFT���M���� �F���ݎ����i�V�X�e���^�C���X�^���v�j
        l_fxGftAskingTelegramUnit.dirSendTime =
            WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

        //�����敪 �F07�F�U�։\�z
        l_fxGftAskingTelegramUnit.gftOperationDiv = WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT;

        //�������O�C��ID�Fget�ϊ�FX���O�C��ID
        l_fxGftAskingTelegramUnit.fxFirstLoginId = l_strChangedFXLoginID;
        
        //��ЃR�[�h�F�⏕����.�،���ЃR�[�h
        l_fxGftAskingTelegramUnit.institutionCode = l_subAccount.getInstitution().getInstitutionCode();

        //���ʃR�[�h�Fget�V�K���ʃR�[�h()�̖߂�l
        l_fxGftAskingTelegramUnit.requestNumber = l_strNewNumber;

        //���X�R�[�h�F  ����.��Е�FX�V�X�e������.���X�R�[�h
        l_fxGftAskingTelegramUnit.branchCode = l_compFxConditionParams.getBranchCode();

        //GFT�˗��d�����ׂ�ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_fxGftAskingTelegramUnit;
    }
}
@
