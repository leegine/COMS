head	1.10;
access;
symbols;
locks; strict;
comment	@// @;


1.10
date	2011.05.23.05.28.12;	author zhang-tengyu;	state Exp;
branches;
next	1.9;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8bc4dd9f06c4bb9;
filename	WEB3GFTConnectionSystem.java;

1.9
date	2011.04.13.07.07.46;	author zhang-tengyu;	state Exp;
branches;
next	1.8;
deltatype	text;
kopt	kv;
permissions	666;
commitid	44c4da54bc2629c;
filename	WEB3GFTConnectionSystem.java;

1.8
date	2011.04.13.06.41.44;	author zhang-tengyu;	state Exp;
branches;
next	1.7;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004da545a74eac;
filename	WEB3GFTConnectionSystem.java;

1.7
date	2011.04.08.05.39.28;	author zhang-tengyu;	state Exp;
branches;
next	1.6;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6704d9e9f90185d;
filename	WEB3GFTConnectionSystem.java;

1.6
date	2011.04.08.05.00.23;	author zhang-tengyu;	state Exp;
branches;
next	1.5;
deltatype	text;
kopt	kv;
permissions	666;
commitid	61c4d9e96677a73;
filename	WEB3GFTConnectionSystem.java;

1.5
date	2011.04.08.04.56.02;	author zhang-tengyu;	state Exp;
branches;
next	1.4;
deltatype	text;
kopt	kv;
permissions	666;
commitid	61c4d9e9562771e;
filename	WEB3GFTConnectionSystem.java;

1.4
date	2011.03.30.08.23.13;	author zhang-tengyu;	state Exp;
branches;
next	1.3;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4044d92e871567e;
filename	WEB3GFTConnectionSystem.java;

1.3
date	2011.03.30.07.00.22;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3ac4d92d5051712;
filename	WEB3GFTConnectionSystem.java;

1.2
date	2011.03.28.06.21.47;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3f04d9028fa5d46;
filename	WEB3GFTConnectionSystem.java;

1.1
date	2011.03.16.02.33.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3GFTConnectionSystem.java;


desc
@@


1.10
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : GFT�ڑ��V�X�e��(WEB3GFTConnectionSystem.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 �đo�g (���u) �V�K�쐬 ���f��1170,1181,1184,1189
Revision History : 2009/09/16 �И��� (���u) �d�l�ύX�E���f��1202,1203,1211,1214,1217,1225
Revision History : 2009/10/14 �����F (���u) �d�l�ύX�E���f��1238,1241
*/

package webbroker3.aio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.gftforex.soap_api.AuthToken;
import com.gftforex.soap_api.CommandAddAccount;
import com.gftforex.soap_api.CommandBase;
import com.gftforex.soap_api.CommandCreateUser;
import com.gftforex.soap_api.CommandDeposit;
import com.gftforex.soap_api.CommandLookupUser;
import com.gftforex.soap_api.CommandWithdraw;
import com.gftforex.soap_api.CreateAccountInfo;
import com.gftforex.soap_api.CreateUserInfo;
import com.gftforex.soap_api.InternalErrorException;
import com.gftforex.soap_api.LookupAccountInfo;
import com.gftforex.soap_api.LookupUserInfo;
import com.gftforex.soap_api.RejectedCommand;
import com.gftforex.soap_api.ResultInfoAddAccount;
import com.gftforex.soap_api.ResultInfoBase;
import com.gftforex.soap_api.ResultInfoCreateUser;
import com.gftforex.soap_api.ResultInfoLookupUser;
import com.gftforex.soap_api.SendSyncRequestResponse;
import com.gftforex.soap_api.UserPersonalInfo;
import com.gftforex.soap_api.UserSystemInfo;

import com.gftforex.soap_api.AdministrativeAPI;
import com.gftforex.soap_api.AdministrativeAPIPort;
import com.gftforex.soap_api.AuthorizationFailedException;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.handler.WEB3FXSOAPMsgHandler;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3GftSoapResultCodeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (GFT�ڑ��V�X�e��)<BR>
 * GFT�ڑ��V�X�e�������N���X<BR>
 *
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3GFTConnectionSystem extends WEB3FXExtSystemCommon
    implements WEB3ExtConnection 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GFTConnectionSystem.class);

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3GFTConnectionSystem()
    {
        this.connectionResultDetails = new HashMap();
    }

    /**
     * GFT�ڑ��̃V�X�e���ֈ˗��d���̑��t���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usendMessage(GFT)�v���Q�Ƃ���<BR>
     * <BR>
     * @@param l_message - (GFT�˗��d������)<BR>
     * GFT�˗��d������<BR>
     * @@param l_prefRpcParams - (�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`��)<BR>
     * �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@throws WEB3BaseException
     */
    public void sendMessage(
        Message l_message,
        SoapConnectPrefRpcParams l_prefRpcParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendMessage(Message, SoapConnectPrefRpcParams)";
        log.entering(STR_METHOD_NAME);

        //GFT�˗��d������.�����敪 != 07�F�U�։\�z�̏ꍇ
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit = (WEB3FXGftAskingTelegramUnit)l_message;
        if (!WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
        	try
        	{
                this.validateConnect(l_prefRpcParams);
        	}
        	catch(WEB3BaseException l_ex)
        	{
        	    this.connectionResultDetails.put(RESULT_CODE, WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000199);
                this.connectionResultDetails.put(CONNECT_RESULT, WEB3GftSoapResultCodeDef.SOAP_CONN_CONFIRM_ERROR);
                return;
        	}
        }
        //this.getGMTtime()���R�[������
        String l_strGMT = this.getGMTtime();

        //this.getHash�l()���R�[������
        //[����]
        //���E�W�����ԁF�擾�������E�W������
        //���O�C���F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���j.�|�[�g�^�C�v��
        //�V�[�N���b�g�L�[�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���j.�I�y���[�V������
        String l_strHashValue = this.getHashValue(l_strGMT, l_prefRpcParams.getPortTypeName(), l_prefRpcParams.getOperationName());

        String l_strReturnValue = null;
        String l_strReturnDetailValue = null;
        String l_strSoapGFTResultCode;
        Holder<RejectedCommand> l_rejectedCommand = new Holder<RejectedCommand>();
        Holder<ResultInfoBase> l_sendSyncRequestResult = new Holder<ResultInfoBase>();
        try
        {
            //this.sendSOAP���b�Z�[�W()���R�[������B
            //[����]
            //�@@GFT�˗��d�����ׁF����.GFT�d�����b�Z�[�W
            //�@@�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���j�F
            //�@@�@@�@@����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j
            //�@@���E�W�����ԁF�P�j�Ŏ擾�������E�W������
            //�@@�n�b�V���l�F�Q�j�Ŏ擾�����n�b�V���l
            this.sendSoapMessage(
                    (WEB3FXGftAskingTelegramUnit)l_message,
                    l_prefRpcParams,
                    l_strGMT,
                    l_strHashValue,
                    l_rejectedCommand,
                    l_sendSyncRequestResult);
            
            // ��sendSOAP���b�Z�[�W()�̖߂�l.getRejectedCommand() != null�̏ꍇ�A
            // ��t���ʃR�[�h�FsendSOAP���b�Z�[�W()�̖߂�l.getRejectedCommand().getMajorErrorCode()
            // ��t���ʏڍ׃R�[�h�FsendSOAP���b�Z�[�W()�̖߂�l.getRejectedCommand().getMinorErrorCode()
            if (l_rejectedCommand.value != null)
            {
                l_strReturnValue = l_rejectedCommand.value.getMajorErrorCode() + "";

                if (l_rejectedCommand.value.getMinorErrorCode() != null)
                {
                   l_strReturnDetailValue = l_rejectedCommand.value.getMinorErrorCode().intValue() + "";
                }
                else
                {
                    l_strReturnDetailValue = null;
                }
            }
            // ��sendSOAP���b�Z�[�W()�̖߂�l.getRejectedCommand() == null�̏ꍇ�A
            // ��t���ʃR�[�h�FsendSOAP���b�Z�[�W()�̖߂�l.getSendSyncRequestResult().getMajorStatusCode()
            // ��t���ʏڍ׃R�[�h�FsendSOAP���b�Z�[�W()�̖߂�l.getSendSyncRequestResult().getMinorStatusCode()
            else
            {
                l_strReturnValue = l_sendSyncRequestResult.value.getMajorStatusCode() + "";
               
                if (l_sendSyncRequestResult.value.getMinorStatusCode() != null)
                {
                    l_strReturnDetailValue = l_sendSyncRequestResult.value.getMinorStatusCode().intValue() + "";
                }
                else
                {
                    l_strReturnDetailValue = null;
                }
            }
            //getSOAPGFT��t���ʃR�[�h(��t���ʃR�[�h : String, ��t���ʏڍ׃R�[�h : String)
            l_strSoapGFTResultCode = this.getSoapAcceptResultCode(l_strReturnValue, l_strReturnDetailValue);
        }
        catch (WEB3BaseException l_ex)
        {
            //�h�O���V�X�e���ڑ��G���[�h�̗�O���X���[�����ꍇ�́A
            if (WEB3ErrorCatalog.BUSINESS_ERROR_02398.equals(l_ex.getErrorInfo()))
            {
                //�hGFT�ڑ��G���[�h(00000990)�A
                l_strSoapGFTResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990;
                // �h�ڑ��G���[�i�V�X�e���G���[�j�h(9991)
                l_strReturnValue = WEB3GftSoapResultCodeDef.CONNECT_ERROR;
            }
            //�h�ʐM�G���[�h�̗�O���X���[�����ꍇ�́A
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_01802.equals(l_ex.getErrorInfo()))
            {
                //�hGFT�V�X�e���N���G���[�h(00000199)�A
                l_strSoapGFTResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000199;
                //�hGFT�C���^�[�i���G���[�i�V�X�e���G���[�j�h(9993)
                l_strReturnValue = WEB3GftSoapResultCodeDef.GFT_INTERNAL_ERROR;
            }
            //�h�n�b�V���F�؃G���[�h�̗�O���X���[�����ꍇ�́A
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_90224.equals(l_ex.getErrorInfo()))
            {
                //�h�n�b�V���l�G���[�h(00000909)�A
                l_strSoapGFTResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000909;
                // �h�n�b�V���F�؃G���[�h(9994)
                l_strReturnValue = WEB3GftSoapResultCodeDef.HASH_AUTH_ERROR;
            }
            else
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //resultCode��ݒ肷��
        //this.�ڑ����ʖ��ׂɂ́A�Ώۂ�ǉ�����B
        //�@@key:�@@�O���ڑ�.RESULT_CODE
        //�@@value:�@@getSOAPGFT��t���ʃR�[�h()�̖߂�l
        this.connectionResultDetails.put(RESULT_CODE, l_strSoapGFTResultCode);

        //connectResult��ݒ肷��
        //this.�ڑ����ʖ��ׂɂ́A�Ώۂ�ǉ�����B
        //�@@key:�@@�O���ڑ�.CONNECT_RESULT
        //�@@value:�@@sendSOAP���b�Z�[�W()�̖߂�l�̎�t���ʃR�[�h ��
        //  ����t���ʏڍ׃R�[�h�ɒl������ꍇ�́A�������t���ʃR�[�h�Ƃ���
        if (l_strReturnDetailValue != null  && !WEB3GftSoapResultCodeDef.NO_ERROR_CODE.equals(l_strReturnDetailValue))
        {
            this.connectionResultDetails.put(CONNECT_RESULT, l_strReturnDetailValue);
        }
        else
        {
            this.connectionResultDetails.put(CONNECT_RESULT, l_strReturnValue);
        }
        
        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //get�����J��FX�V�X�e���R�[�h(�،���ЃR�[�h : String)
        String l_strValue = l_fxDataControlService.getSameTimeFxSystemCode(l_fxGftAskingTelegramUnit.institutionCode);
        
        //get��Е�FX�V�X�e������(�،���ЃR�[�h : String, ���X�R�[�h : String, FX�V�X�e���R�[�h : String)
        //[����]
        //�،���ЃR�[�h�F����.GFT�˗��d������.�،���ЃR�[�h
        //���X�R�[�h�F����.GFT�˗��d������.�،����X�R�[�h
        //FX�V�X�e���R�[�h�F����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j.�ڑ��敪
        CompFxConditionParams l_compFxConditionParams;
        try
        {
            l_compFxConditionParams = l_fxDataControlService.getCompFxCondition(
                l_fxGftAskingTelegramUnit.institutionCode,
                l_fxGftAskingTelegramUnit.branchCode,
                l_prefRpcParams.getConnectDiv());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        
        //��t���ʃR�[�h = 00000000(��������)�̏ꍇ
        if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(l_strSoapGFTResultCode))
        {
            //����.GFT�˗��d������.�����敪 = 01�F�����J�݂̏ꍇ
            if (WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                ResultInfoCreateUser l_resultInfoCreateUser =
                    (ResultInfoCreateUser)l_sendSyncRequestResult.value;
                if (l_resultInfoCreateUser != null && l_resultInfoCreateUser.getAccountIds() != null)
                {
                    //�����J�݂���FX�V�X�e���R�[�h != null�̏ꍇ
                    if (l_strValue != null)
                    {
                        //fx_acc_01��ݒ肷��
                        this.connectionResultDetails.put(FX_ACC_01, l_resultInfoCreateUser.getAccountIds().get(0));
                        //fx_acc_10��ݒ肷��
                        this.connectionResultDetails.put(FX_ACC_10, l_resultInfoCreateUser.getAccountIds().get(1));
                        //cfd_acc��ݒ肷��
                        this.connectionResultDetails.put(CFD_ACC, l_resultInfoCreateUser.getAccountIds().get(2));
                    }
                    else
                    {
                        //�����J�݂���FX�V�X�e���R�[�h = null�̏ꍇ
                        //�擾������Е�FX�V�X�e������Params.FX�V�X�e���敪��null�������ꍇ
                        if (l_compFxConditionParams.getFxSystemDiv() == null)
                        {
                            //fx_acc_01��ݒ肷��
                            this.connectionResultDetails.put(FX_ACC_01, l_resultInfoCreateUser.getAccountIds().get(0));
                            //fx_acc_10��ݒ肷��
                            this.connectionResultDetails.put(FX_ACC_10, l_resultInfoCreateUser.getAccountIds().get(1));
                        }
                        //�擾������Е�FX�V�X�e������Params.FX�V�X�e���敪==�h2�h�������ꍇ
                        else if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_compFxConditionParams.getFxSystemDiv()))
                        {
                            //cfd_acc��ݒ肷��
                            this.connectionResultDetails.put(CFD_ACC, l_resultInfoCreateUser.getAccountIds().get(0));
                        }
                    }
                }
            }
            //����.GFT�˗��d������.�����敪 = 03�F�����ǉ��̏ꍇ
            else if (WEB3AdminAioGftOperationDivDef.ADD_ACCOUNT.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                ResultInfoAddAccount l_resultInfoAddAccount =
                    (ResultInfoAddAccount)l_sendSyncRequestResult.value;
                if (l_resultInfoAddAccount != null && l_resultInfoAddAccount.getAccountId() != null)
                {
                    //�擾������Е�FX�V�X�e������Params.FX�V�X�e���敪��null�������ꍇ
                    if (l_compFxConditionParams.getFxSystemDiv() == null)
                    {
                        //fx_acc_01��ݒ肷��
                        this.connectionResultDetails.put(FX_ACC_01, l_resultInfoAddAccount.getAccountId().get(0));
                        //fx_acc_10��ݒ肷��
                        this.connectionResultDetails.put(FX_ACC_10, l_resultInfoAddAccount.getAccountId().get(1));
                    }
                    //�擾������Е�FX�V�X�e������Params.FX�V�X�e���敪==�h2�h�������ꍇ
                    else if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_compFxConditionParams.getFxSystemDiv()))
                    {
                        //cfd_acc��ݒ肷��
                        this.connectionResultDetails.put(CFD_ACC, l_resultInfoAddAccount.getAccountId().get(0));
                    }
                }
            }
            //����.GFT�˗��d������.�����敪 = 07�F�U�։\�z�̏ꍇ
            else if (WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals(l_fxGftAskingTelegramUnit.gftOperationDiv))
            {
                //SendSyncRequestResponse����ALookupAccountInfo�����[�v�Ŏ擾����
                ResultInfoBase l_resultInfoBase = l_sendSyncRequestResult.value;
                ResultInfoLookupUser l_resultInfoLookupUser = (ResultInfoLookupUser)l_resultInfoBase;
                if (l_resultInfoLookupUser != null)
                {
                    LookupUserInfo l_lookupUserInfo = l_resultInfoLookupUser.getUserInfo();
                    List<LookupAccountInfo> l_lookUpAccInfos = l_lookupUserInfo.getAccounts();
                    if (l_lookUpAccInfos != null && l_lookUpAccInfos.size() > 0)
                    {
                        HashMap l_hmAmount = new HashMap();
                        String l_strAccountId;
                        for (int i = 0; i < l_lookUpAccInfos.size(); i++)
                        {
                            l_strAccountId = l_lookUpAccInfos.get(i).getAccountId() + "";
                            l_hmAmount.put(l_strAccountId, WEB3StringTypeUtility.formatNumber(l_lookUpAccInfos.get(i).getWithdrawableAmount()));
                        }
                        this.connectionResultDetails.put(AMOUNT, l_hmAmount);
                    }
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**<BR>
     * GFT�ڑ��V�X�e���̌��ʒʒm����A�w�肵�����ږ���Value���擾����B<BR>
     * <BR>
     * this.�ڑ����ʖ��ׂ���A�w�肵�����ږ���Key�Ƃ��āAValue��ԋp����B<BR>
     * <BR>
     * @@param l_strName - (GFT���ږ�)<BR>
     * GFT�d���̍��ږ�<BR>
     * @@return Object
     */
    public Object getResult(String l_strName)
    {
        final String STR_METHOD_NAME = "getResult(String)";
        log.entering(STR_METHOD_NAME);

        Object l_result = null;
        //this.�ڑ����ʖ��ׂ���A�w�肵�����ږ���Key�Ƃ��āAValue��ԋp����
        if (this.connectionResultDetails != null)
        {
            l_result = this.connectionResultDetails.get(l_strName);
        }

        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * ���E�W�����Ԃ��擾����<BR>
     * <BR>
     * �P�jjava.util.Date�̃C���X�^���X�𐶐�����B<BR>
     * �@@TimeZone��"GMT+0000"�̌^��ݒ�<BR>
     * �@@SimpleDateFormat��"yyyyMMddHHmmssSSS"�̌^��ݒ�<BR>
     * <BR>
     * �Q�jString�̌^�Ő��E�W�����Ԃ�ԋp����B<BR>
     * <BR>
     * @@return String
     */
    protected String getGMTtime()
    {
        final String STR_METHOD_NAME = "getGMTtime()";
        log.entering(STR_METHOD_NAME);

        //java.util.Date�̃C���X�^���X�𐶐�����
        Date l_date = new Date();

        //TimeZone��"GMT+0000"�̌^��ݒ�
        TimeZone l_timeZone = TimeZone.getTimeZone("GMT+0000");

        //SimpleDateFormat��"yyyyMMddHHmmssSSS"�̌^��ݒ�
        SimpleDateFormat l_simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        l_simpleDateFormat.setTimeZone(l_timeZone);
        String l_strResultTime = l_simpleDateFormat.format(l_date);

        //String�̌^�Ő��E�W�����Ԃ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strResultTime;
    }
    
    /**
     * (validate�ڑ��\)<BR>
     * SOAP�ڑ��m�F���s���B<BR>
     * <BR>
     * 1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���𕪊����A�ڑ�������擾����B<BR>
     * �@@ getEndpointName().split(arg0 : String)<BR>
     * �@@�@@[����]<BR>
     * �@@�@@arg0�F ";"<BR>
     * 2�j�v���L�V�ݒ���s���B <BR>
     * ���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 3�̏ꍇ�� <BR>
     * �@@���������f�[�^��3���ڂ̕������"https"���܂܂��ꍇ���� <BR>
     * �@@�@@ ���L�̒ʂ�v���p�e�B�Z�b�g���s���B <BR>
     * �@@�@@�i1�jkey : "https.proxyHost" <BR>
     * �@@�@@ �@@ value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f <BR>
     * �@@�@@�i2�jkey : "https.proxyPort" <BR>
     * �@@�@@ �@@ value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f <BR>
     * �@@������L�̏����ȊO���� <BR>
     * �@@�@@ ���L�̒ʂ�v���p�e�B�Z�b�g���s���B <BR>
     * �@@�@@�i1�jkey : "http.proxyHost" <BR>
     * �@@�@@ �@@ value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f <BR>
     * �@@�@@�i2�jkey : "http.proxyPort" <BR>
     * �@@�@@ �@@ value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f <BR>
     * ���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 1�̏ꍇ�� <BR>
     * �@@ �����Ȃ� <BR>
     * ����L�̏����ȊO�� <BR>
     * �@@ ��O���X���[����B <BR>
     * <BR>
     * 3�j�T�[�r�X�����N���X�̃C���X�^���X�𐶐�����B <BR>
     * AdministrativeAPI_Impl() <BR>
     * <BR>
     * 4�j�T�[�r�X�X�^�u�𐶐�����B <BR>
     * service.getAdministrativeAPIPort(arg0 : String) <BR>
     * [����] <BR>
     * arg0 : ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�T�[�r�X��<BR> 
     * <BR>
     * 5�j�T�[�r�X�X�^�u�ɃG���h�|�C���g��ݒ肷��B <BR>
     * _setProperty(arg0 : String, arg1 : Object) <BR>
     * [����] <BR>
     * arg0�F "javax.xml.rpc.service.endpoint.address" <BR>
     * arg1�F �|�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 3�̏ꍇ <BR>
     * 1�j�Ŏ擾�����ڑ�����̔z���3�v�f <BR>
     * �|�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 1�̏ꍇ <BR>
     * 1�j�Ŏ擾�����ڑ�����̔z���1�v�f <BR>
     * <BR>
     * 6�j�T�[�r�X�X�^�u�ɐڑ��^�C���A�E�g���Ԃ��Z�b�g����B <BR>
     * _setProperty(arg0 : String, arg1 : Object) <BR>
     * [����] <BR>
     * <BR>
     * 7�j�T�[�r�X�X�^�u���AisOK()���R�[������B <BR>
     * adminAPI_Stub.isOk() <BR>
     * <BR>
     * 8) isOK()�̖߂�l��false�̏ꍇ�A"�ʐM�G���["���X���[����B <BR>
     * <BR>
     * @@param l_prefRpcParams - (�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`��)params)<BR>
     * �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`��)<BR>
     * @@throws WEB3BaseException
     */
    protected void validateConnect(SoapConnectPrefRpcParams l_rpcParams) 
        throws WEB3BaseException{

      String STR_METHOD_NAME = 
        "validateConnect(SoapConnectPrefRpcParams)";
      log.entering(STR_METHOD_NAME);

      boolean l_checkFlg = false;      
      
      
      // 1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���𕪊����A�ڑ�������擾����B
      // �@@ getEndpointName().split(arg0 : String)
      // �@@�@@[����]
      // �@@�@@arg0�F ";"
      String l_urlArr[] = l_rpcParams.getEndpointName().split(";");
      String l_protocolStr = "";
      
      String l_logMessage = "";
      
      // 2�j�v���L�V�ݒ���s���B
      //�@@���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 3�̏ꍇ��
      //   �@@���������f�[�^��3���ڂ̕������"https"���܂܂��ꍇ����
      // �@@�@@ ���L�̒ʂ�v���p�e�B�Z�b�g���s���B
      // �@@�@@�i1�jkey   : "https.proxyHost"
      // �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
      // �@@�@@�i2�jkey   : "https.proxyPort"
      // �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
      //   �@@������L�̏����ȊO����
      // �@@�@@ ���L�̒ʂ�v���p�e�B�Z�b�g���s���B
      // �@@�@@�i1�jkey   : "http.proxyHost"
      // �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
      // �@@�@@�i2�jkey   : "http.proxyPort"
      // �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
      //�@@���O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g�̕����� == 1�̏ꍇ��
      //�@@�@@�����Ȃ�
      //  ����L�̏����ȊO��
      //�@@�@@��O���X���[����B
      if(l_urlArr.length == 3)
      {
      
        l_protocolStr = l_urlArr[2];
        
          if(l_protocolStr.trim().indexOf("https") >= 0)
          {
            System.setProperty("https.proxyHost", l_urlArr[0].trim());
            System.setProperty("https.proxyPort", l_urlArr[1].trim());
            l_logMessage = 
                "\nhttps.proxyHost (" + l_urlArr[0] + ")" +
                "\nhttps.proxyPort (" + l_urlArr[1] + ")" ;
          }
          else
          {
            System.setProperty("http.proxyHost", l_urlArr[0].trim());
            System.setProperty("http.proxyPort", l_urlArr[1].trim());
            l_logMessage = 
                "\nhttp.proxyHost (" + l_urlArr[0] + ")" +
                "\nhttp.proxyPort (" + l_urlArr[1] + ")";
          }
      
          log.debug(l_logMessage);
          
      }
      else if (l_urlArr.length == 1)
      {
      }
      else
      {
          l_logMessage = 
              "�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��).�G���h�|�C���g��" + 
              "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
              "�uproxy-host;proxy-port;protocol�v�ŃZ�b�g���Ă��������B";
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02398,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_logMessage
              );
      }

        // 3�j�T�[�r�X�����N���X�̃C���X�^���X�𐶐�����B
        //    AdministrativeAPI_Impl()
    	  AdministrativeAPI service = new AdministrativeAPI();
        
        // 4�j�T�[�r�X�X�^�u�𐶐�����B
        //    service.getAdministrativeAPIPort(arg0 : String)
        //     [����]
        //      arg0 : ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�T�[�r�X��
    	  AdministrativeAPIPort adminAPI_Stub = 
              (AdministrativeAPIPort) service.getAdministrativeAPIPort(l_rpcParams.service_name);
        
        
        // 5�j�T�[�r�X�X�^�u�ɃG���h�|�C���g��ݒ肷��B
        String l_strParameter = null;
        if (l_urlArr.length == 3)
        {
            l_strParameter = l_urlArr[2];
        }
        else if (l_urlArr.length == 1)
        {
            l_strParameter = l_urlArr[0];
        }
        BindingProvider bp = (BindingProvider)adminAPI_Stub;
        
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, l_strParameter);

        // 6�j�ڑ��^�C���A�E�g���Ԃ��Z�b�g����B
        //    setProperty(arg0 : String, arg1 : Object)
        //     [����] 
        //1 minute for connection ((BindingProvider)
        bp.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", l_rpcParams.response_timeout);
        //3 minutos for request ((BindingProvider)
        bp.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", l_rpcParams.response_timeout);

        // 7�j�T�[�r�X�X�^�u���AisOK()���R�[������B
        //    adminAPI_Stub.isOk()
        l_checkFlg = adminAPI_Stub.isOk();
        

      // 8) isOK()�̖߂�l��false�̏ꍇ�A"�ʐM�G���["���X���[����B
      if ( l_checkFlg ) {
          log.debug("\n" + "isOK() �`�F�b�N�t���O = true");
          log.exiting(STR_METHOD_NAME);
      }else{
          log.debug("\n" + "isOK() �`�F�b�N�t���O = false");
          log.exiting(STR_METHOD_NAME);
          // �ʐM�G���[
          throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_01802,
              this.getClass().getName() + STR_METHOD_NAME);
      }
      
    }

    /**
     * (getHash�l) <BR>
     * SOAP�ڑ��Ɉ����n�����߂̃n�b�V���l���擾����B <BR>
     * <BR>
     * �P�j�@@64�o�C�g�̗̈���m�ۂ���B<BR>
     * �Q�j�@@�ȉ��̂悤�ɕ������A������B <BR>
     *       ����.���E�W�����ԁ{����.���O�C�� <BR>
     * �R�j�@@��L�̌��ʂ�16�i���o�C�g�z��ɕϊ����A�P�j�Ŋm�ۂ����̈�Ɋi�[����B <BR>
     * �S�j�@@��L�̌��ʂɑ΂���SHA_1��p���ăn�b�V���v�Z����B <BR>
     * �T�j�@@����.�V�[�N���b�g�L�[��BASE64�Ńf�R�[�h���A�o�C�g�z��Ɋi�[����B <BR>
     * �U�j�@@��L���ʂɂS�j�̌��ʂ𑫂��B <BR>
     * �V�j�@@��L�̌��ʂɑ΂���SHA_1��p���ăn�b�V���v�Z����B <BR>
     * �W�j�@@��L�̌��ʂ�BASE64�ŃG���R�[�h���A�ԋp����B <BR>
     * <BR>
     * @@param l_worldTime ���E�W������
     * @@param l_login ���O�C��
     * @@param l_secretKey �V�[�N���b�g�L�[ 
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getHashValue(
            String l_worldTime,
            String l_login, 
            String l_secretKey) throws WEB3BaseException {
        
        String STR_METHOD_NAME = 
            "getHashValue(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@64�o�C�g�̗̈���m�ۂ���B 
        byte l_timeLoginByte[] = new byte[64];
        byte l_timeLoginByte64[] = new byte[64];
        
        // �Q�j�@@�ȉ��̂悤�ɕ������A������B 
        //     �@@����.���E�W�����ԁ{����.���O�C�� 
        String l_timeLogin = l_worldTime.concat(l_login);
        
        log.debug("\n" + "getHashValue() - �A��������i���E�W������+���O�C���j�F" + l_timeLogin);
        
        // �R�j�@@��L�̌��ʂ�16�i���o�C�g�z��ɕϊ����A�P�j�Ŋm�ۂ����̈�Ɋi�[����B 
        l_timeLoginByte = l_timeLogin.getBytes();
        
        for(int i=0;i<64;i++){
            if(i<l_timeLoginByte.length){
                l_timeLoginByte64[i] = l_timeLoginByte[i];
            }
        }
        
        // �S�j�@@��L�̌��ʂɑ΂���SHA_1��p���ăn�b�V���v�Z����B 
        MessageDigest l_messageDigest = null;
        try
        {
            // SHA_1���w��
            l_messageDigest = MessageDigest.getInstance(WEB3AioHashAlgorithmDef.SHA_1);
        }
        catch(NoSuchAlgorithmException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //MessageDigest�I�u�W�F�N�g.update()���R�[��
        l_messageDigest.update(l_timeLoginByte64);
        
        //MessageDigest�I�u�W�F�N�g.digest()���R�[��
        byte[] l_byteResult = l_messageDigest.digest();
        
        // �T�j�@@����.�V�[�N���b�g�L�[��BASE64�Ńf�R�[�h���A�o�C�g�z��Ɋi�[����B
        BASE64Decoder l_decoder = new BASE64Decoder();
        byte[] l_decodedSecretKey = new byte[64];
        
        try
        {
            l_decodedSecretKey = l_decoder.decodeBuffer(l_secretKey);
        }
        catch (IOException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // �U�j�@@��L���ʂɂS�j�̌��ʂ𑫂��B 
        for(int i=0;i<l_byteResult.length;i++){
            l_decodedSecretKey[i] += l_byteResult[i];
        }
        
        // �V�j�@@��L�̌��ʂɑ΂���SHA_1��p���ăn�b�V���v�Z����B
        //MessageDigest�I�u�W�F�N�g.update()���R�[��
        l_messageDigest.update(l_decodedSecretKey);
        
        //MessageDigest�I�u�W�F�N�g.digest()���R�[��
        byte[] l_hashResult = l_messageDigest.digest();
        
        // �W�j�@@��L�̌��ʂ�BASE64�ŃG���R�[�h���A�ԋp����B
        BASE64Encoder l_encoder = new BASE64Encoder();
        
        String l_hashResult64 = l_encoder.encode(l_hashResult);
        
        log.exiting(STR_METHOD_NAME);
              
        return l_hashResult64;
    }

    /**
     * (sendSOAP���b�Z�[�W) <BR>
     * SOAP�ڑ����s���B<BR>
     * <BR>
     * 1�j�،����ID���擾����B<BR>
     * <BR>
     * 2�jAuthToken�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@����������AuthToken�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B<BR>
     * �@@�@@�@@���O�C���F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v��<BR>
     * �@@�@@�@@�^�C���X�^���v�F����.���E�W������<BR>
     * �@@�@@�@@�n�b�V���F����.�n�b�V���l<BR>
     * <BR>
     * 3�j���N�G�X�g�f�[�^�𐶐�����B<BR>
     * <BR>
     * �@@�@@������.�d������.�����敪 == "01"(�V�K�J��)�̏ꍇ��<BR>
     * �@@�@@�@@�@@�@@�i1�jUserPersonalInfo�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@����������UserPersonalInfo�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���O(��)�F����.GFT�˗��d������.���O(��)<BR>
     * �@@�@@�@@�@@�@@�@@�@@���[���A�h���X�F����.GFT�˗��d������.���[���A�h���X<BR>
     * �@@�@@�@@�@@�@@�i2�jUserSystemInfo�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@����������UserSystemInfo�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@FX���O�C��ID�F����.GFT�˗��d������.�������O�C��ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�p�X���[�h�F����.GFT�˗��d������.�p�X���[�h<BR>
     * �@@�@@�@@�@@�@@�i3�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��)params.�p�����[�^�^�C�v���X�g�ɐݒ肳��Ă���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�e�A�J�E���g�e���v���[�g���C���X�^���X�փZ�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���L�������R������؂�Ŏw�肳��Ă���񐔎��{����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i3-1�jCreateAccountInfo�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����������CreateAccountInfo�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�A�J�E���g�e���v���[�gID�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�p�����[�^�^�C�v���X�g.��n����<BR>
     * �@@�@@�@@�@@�@@�i4�jCreateAccountInfo�̃I�u�W�F�N�g�z��𐶐����A<BR>
     * �@@�@@�@@�@@�@@�@@�@@(3)�Ő�������CreateAccountInfo�C���X�^���X���i�[����B<BR>
     * �@@�@@�@@�@@�@@�i5�jCreateUserInfo�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@CreateUserInfo(arg0�FString, arg1�FUserPersonalInfo, arg2�FUserSystemInfo, arg3�FCreateAccountInfo[])<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�p�����[�^���X�g<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg1�F�i1�j�Ő�������UserPersonalInfo�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg2�F�i2�j�Ő�������UserSystemInfo�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg3�F�i5�j�Ő�������CreateAccountInfo[]�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�i6�jCommandCreateUser�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@CommandCreateUser(arg0 : String, arg1 : CreateUserInfo)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg1�F ��������CreateUserInfo�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�i7�jSendSyncRequest�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F�i6�j�Ő�������CommandCreateUser�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg1�F 2)�Ő�������AuthToken�C���X�^���X<BR>
     * <BR>
     * �@@�@@������.�d������.�����敪 == "03"(�ǉ��J��)�̏ꍇ��<BR>
     * �@@�@@�@@�@@�@@�i1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��)params.�p�����[�^�^�C�v���X�g�ɐݒ肳��Ă���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�e�A�J�E���g�e���v���[�g���C���X�^���X�փZ�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���L�������R������؂�Ŏw�肳��Ă���񐔎��{����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i3-1�jCreateAccountInfo�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@����������CreateAccountInfo�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�A�J�E���g�e���v���[�gID�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�p�����[�^�^�C�v���X�g.��n����<BR>
     * �@@�@@�@@�@@�@@�i2�jCreateAccountInfo�̃I�u�W�F�N�g�z��𐶐����A<BR>
     * �@@�@@�@@�@@�@@�@@�@@(1)�Ő�������CreateAccountInfo�C���X�^���X���i�[����B<BR>
     * �@@�@@�@@�@@�@@�i3�jCommandAddAccount�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@CommandAddAccount(arg0 : String, arg1 : String, arg2 : CreateAccountInfo[])<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg1�F ����.GFT�˗��d������.�������O�C��ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg2�F (2)�Ő�������CreateAccountInfo[]�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�i4�jSendSyncRequest�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg0�F�i3�j�Ő�������CommandAddAccount�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@arg1�F 2)�Ő�������AuthToken�C���X�^���X<BR>
     * <BR>
     * �@@�@@������.�d������.�����敪 == "04�F�o��"�̏ꍇ(FX�ւ̐U�ւ̏ꍇ�j��<BR>
     * �@@�@@�@@�i1�jCommandDeposit�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@CommandDeposit(arg0 : String, arg1 : long, arg2 : double, arg3 : String)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg0�F 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg1�F ����.GFT�˗��d������.�ב֕ۏ؋������ԍ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg2�F ����.GFT�˗��d������.���o���z<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg3�F "JPY"<BR>
     * �@@�@@�@@�i2�jSendSyncRequest�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg0�F �i1�j�Ő�������CommandDeposit�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg1�F 2)�Ő�������AuthToken�C���X�^���X<BR>
     * <BR>
     * �@@�@@������.�d������.�����敪 == "02�F����"�̏ꍇ(FX����U�ւ̏ꍇ�j��<BR>
     * �@@�@@�@@�i1�jCommandWithdraw�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@CommandWithdraw(arg0 : String, arg1 : long, arg2 : double, arg3 : String)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg0�F 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg1�F ����.GFT�˗��d������.�ב֕ۏ؋������ԍ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg2�F ����.GFT�˗��d������.���o���z<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg3�F "JPY"<BR>
     * �@@�@@�@@�i2�jSendSyncRequest�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg0�F �i1�j�Ő�������CommandWithdraw�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg1�F 2)�Ő�������AuthToken�C���X�^���X<BR>
     * <BR>
     * �@@�@@������.�d������.�����敪 == "07�F�U�։\�z"�̏ꍇ��<BR>
     * �@@�@@�@@�i1�jCommandLookupUser�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@CommandLookupUser(arg0 : String, arg1 : String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg0�F 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg1�F ����.GFT�˗��d������.�������O�C��ID<BR>
     * �@@�@@�@@�i2�jSendSyncRequest�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg0�F �i1�j�Ő�������CommandLookupUser�C���X�^���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@arg1�F ��������AuthToken�C���X�^���X<BR>
     * <BR>
     * 4�j�T�[�r�X�����N���X�𐶐�����B<BR>
     * <BR>
     * �@@ AdministrativeAPI_Impl()<BR>
     * <BR>
     * 5�j�T�[�r�X�X�^�u�𐶐�����B<BR>
     * <BR>
     * �@@�@@service.getAdministrativeAPIPort(arg0 : String)<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@arg0 : ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�T�[�r�X��<BR>
     * <BR>
     * 6�j�T�[�r�X�X�^�u�ɃG���h�|�C���g��ݒ肷��B<BR>
     * �@@�@@_setProperty(arg0 : String, arg1 : Object)<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@arg0�F "javax.xml.rpc.service.endpoint.address"<BR>
     * �@@�@@�@@�@@arg1�F �|����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̕����� == 3�̏ꍇ<BR>
     * ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̔z���3�v�f<BR>
     * �|����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̕����� == 1�̏ꍇ<BR>
     * ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̔z���1�v�f<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@����L�̏����ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class�F�@@WEB3BusinessLayerException
     * �@@�@@�@@�@@tag:�@@�@@�@@BUSINESS_ERROR_02398<BR>
     * <BR>
     * 7�j�ڑ��^�C���A�E�g���Ԃ��Z�b�g����B<BR>
     * <BR>
     * �@@�@@_setProperty(arg0 : String, arg1 : Object)<BR>
     * �@@�@@�@@[����]<BR>
     * <BR>
     * 8�jSOAP���b�Z�[�W�n���h�����Z�b�g����B 
     * 
     * �@@�@@(1)Web �T�[�r�X �|�[�g�̏C�������i�[����I�u�W�F�N�g���쐬 
     * �@@�@@�@@�@@QName( arg0 : l_rpcParams.target_namespace_name, arg1 : l_rpcParams.service_name) 
     * �@@�@@�@@�@@�@@�@@[����] 
     * �@@�@@�@@�@@�@@�@@�@@arg0�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�^�[�Q�b�g�l�[���X�y�[�X�� 
     * �@@�@@�@@�@@�@@�@@�@@arg1�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�T�[�r�X�� 
     * <BR>
     * �@@�@@(2)HandlerRegistry �I�u�W�F�N�g���쐬 
     * <BR>
     * �@@�@@(3)HandlerInfo�ɐݒ肷��config(�FMap)�f�[�^�̍쐬 
     * <BR>
     * �@@�@@�@@�@@[put()�Ɏw�肷�����] 
     * �@@�@@�@@�@@�@@key�F"BranchId" 
     * �@@�@@�@@�@@�@@value�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams. ���XID 
     * �@@�@@�@@�@@[put()�Ɏw�肷�����] 
     * �@@�@@�@@�@@�@@key�F"ConnectDiv" 
     * �@@�@@�@@�@@�@@value�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams. �ڑ��敪 
     * <BR>
     * �@@�@@(4)SOAP���b�Z�[�W�̃n���h�� �`�F�[��(�FArrayList)���� 
     * <BR>
     * �@@�@@�@@�@@[add()�Ɏw�肷�����] 
     * �@@�@@�@@�@@�@@new HandlerInfo( arg0 : WEB3FXSOAPLogHandler.class, arg1 : map, arg2 : null ) 
     * �@@�@@�@@�@@�@@�@@[����] 
     * �@@�@@�@@�@@�@@�@@�@@arg0�F WEB3FXSOAPLogHandler.class 
     * �@@�@@�@@�@@�@@�@@�@@arg1�F (3)�Ő�������Map�I�u�W�F�N�g 
     * �@@�@@�@@�@@�@@�@@�@@arg2�F null 
     * <BR>
     * �@@�@@(5)�n���h�� �`�F�[����o�^ 
     * �@@�@@�@@�@@setHandlerChain( arg0 : portName, arg1 : handlerList ) 
     * �@@�@@�@@�@@�@@�@@[����] 
     * �@@�@@�@@�@@�@@�@@�@@arg0�F (1)�Ő��������I�u�W�F�N�g 
     * �@@�@@�@@�@@�@@�@@�@@arg1�F (4)�Ő�������ArrayList 
     * <BR>
     * 9�j���b�Z�[�W����M<BR>
     * <BR>
     * �@@�@@regSoap_Stub.sendSyncRequest(arg0 : SendSyncRequest)<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@arg0 : 3�j�Ő����������N�G�X�g�f�[�^<BR>
     * <BR>
     * 10�j9�j�Ŏ�M����SOAP�ڑ����X�|���X�f�[�^��ԋp����B<BR>
     * <BR>
     * @@param l_fxGftAskingTelegramUnit GFT�˗��d������
     * @@param l_rpcParams �O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams
     * @@param l_worldTime ���E�W������
     * @@param l_hashValue �n�b�V���l
     * @@return SendSyncRequestResponse
     * @@throws WEB3BaseException
     */
    protected void sendSoapMessage(
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
            SoapConnectPrefRpcParams l_rpcParams,
            String l_worldTime,
            String l_hashValue,
            Holder<RejectedCommand> l_rejectedCommand,
            Holder<ResultInfoBase> l_sendSyncRequestResult) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "sendSoapMessage(WEB3FXGftAskingTelegramUnit, " +
            "SoapConnectPrefRpcParams, String, String)";
        log.entering(STR_METHOD_NAME);

        //�g���A�J�E���g�}�l�[�W���擾����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //1�j�،����ID���擾����B
        String l_strInstitutionId = null;
        try{
            Institution l_Instituion = l_accountManager.getInstitution(
                l_fxGftAskingTelegramUnit.institutionCode);
            l_strInstitutionId = String.valueOf(l_Instituion.getInstitutionId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ���O�C�����擾
        String l_login = l_rpcParams.port_type_name;
        
        // 2�jAuthToken�C���X�^���X�𐶐�����B
        //    ����������AuthToken�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B
        //  �@@�@@���O�C���F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�|�[�g�^�C�v��
        //  �@@�@@�^�C���X�^���v�F����.���E�W������
        //�@@�@@�@@�n�b�V���F����.�n�b�V���l
        AuthToken l_token = new AuthToken();
        l_token.setLogin(l_login);
        l_token.setTimestamp(l_worldTime);
        l_token.setHash(l_hashValue);
        
        // �R�}���hID�쐬�i 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h�j
        String l_commandId = 
            l_strInstitutionId.concat(l_fxGftAskingTelegramUnit.requestNumber);
        
        CommandBase l_commandBase = new CommandBase();
        
        // 3�j���N�G�X�g�f�[�^�𐶐�����B
        // ������.�d������.�����敪 == "01"(�V�K�J��)�̏ꍇ��
        if(WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            
            // �i1�jUserPersonalInfo�C���X�^���X�𐶐�����B
            //      ����������UserPersonalInfo�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B
            //        ���O(��)�F����.GFT�˗��d������.���O(��)
            //        ���[���A�h���X�F����.GFT�˗��d������.���[���A�h���X
            UserPersonalInfo l_userPersonalInfo = new UserPersonalInfo();
            l_userPersonalInfo.setLastName(l_fxGftAskingTelegramUnit.fxLastName);
            l_userPersonalInfo.setEmail(l_fxGftAskingTelegramUnit.fxMailAddress);
            
            // �i2�jUserSystemInfo�C���X�^���X�𐶐�����B
            //      ����������UserSystemInfo�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B
            //        FX���O�C��ID�F����.GFT�˗��d������.�������O�C��ID
            //        �p�X���[�h�F����.GFT�˗��d������.�p�X���[�h
            UserSystemInfo l_userSystemInfo = new UserSystemInfo();
            l_userSystemInfo.setLogin(l_fxGftAskingTelegramUnit.fxFirstLoginId);
            l_userSystemInfo.setPassword(l_fxGftAskingTelegramUnit.fxFirstPassword);
            
            // �i3�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��)params.�p�����[�^�^�C�v���X�g�ɐݒ肳��Ă���
            //�e�A�J�E���g�e���v���[�g���C���X�^���X�փZ�b�g����B
            ArrayList l_lisCreateAccountInfos = new ArrayList();
            String l_strParameterTypeList = l_rpcParams.getParameterTypeList();
            String[] l_strParameterTypes = null;
            int l_intParameterTypesLength = 0;
            if (!WEB3StringTypeUtility.isEmpty(l_strParameterTypeList))
            {
                l_strParameterTypes =
                    l_strParameterTypeList.split(":");
                l_intParameterTypesLength = l_strParameterTypes.length;
            }

            //���L�������R������؂�Ŏw�肳��Ă���񐔎��{����B
            //�i3-1�jCreateAccountInfo�C���X�^���X�𐶐�����B
            //����������CreateAccountInfo�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B
            //�A�J�E���g�e���v���[�gID�F
            //����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�p�����[�^�^�C�v���X�g.��n����
            for (int i = 0; i< l_intParameterTypesLength; i++)
            {
                CreateAccountInfo l_createAccountInfo = new CreateAccountInfo();
                l_createAccountInfo.setAccountTemplateId(
                    l_strParameterTypes[i]);
                l_lisCreateAccountInfos.add(l_createAccountInfo);
            }

            // �i4�jCreateAccountInfo�̃I�u�W�F�N�g�z��𐶐���
            //(3)�Ő�������CreateAccountInfo�C���X�^���X���i�[����B
            CreateAccountInfo[] l_createAccountInfos =
                new CreateAccountInfo[l_intParameterTypesLength];
            l_lisCreateAccountInfos.toArray(l_createAccountInfos);
            
            // �i5�jCreateUserInfo�C���X�^���X�𐶐�����B
            //      CreateUserInfo(arg0�FString, arg1�FUserPersonalInfo, arg2�FUserSystemInfo, arg3�FCreateAccountInfo[])
            //       [����]
            //        arg0�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�p�����[�^���X�g
            //        arg1�F�i1�j�Ő�������UserPersonalInfo�C���X�^���X
            //        arg2�F�i2�j�Ő�������UserSystemInfo�C���X�^���X
            //        arg3�F�i5�j�Ő�������CreateAccountInfo[]�C���X�^���X
            CreateUserInfo l_createUserInfo = new CreateUserInfo();
            l_createUserInfo.setUserTemplateId(l_rpcParams.parameter_list);
            l_createUserInfo.setUserPersonalInfo(l_userPersonalInfo);
            l_createUserInfo.setUserSystemInfo(l_userSystemInfo);
            l_createUserInfo.setAccounts(l_lisCreateAccountInfos);
                    
            // �i6�jCommandCreateUser�C���X�^���X�𐶐�����B
            //      CommandCreateUser(arg0 : String, arg1 : CreateUserInfo)
            // �@@    [����] 
            //  �@@    arg0�F 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h
            //  �@@    arg1�F ��������CreateUserInfo�C���X�^���X
            CommandCreateUser l_commandCreateUser = new CommandCreateUser();
            l_commandCreateUser.setCommandID(l_commandId);
            l_commandCreateUser.setUserInfo(l_createUserInfo);
            
            // �i7�jSendSyncRequest�C���X�^���X�𐶐�����B
            //      SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            // �@@    [����]
            //  �@@    arg0�F �i6�j�Ő�������CommandCreateUser�C���X�^���X
            //�@@�@@    arg1�F 2)�Ő�������AuthToken�C���X�^���X
            l_commandBase = l_commandCreateUser;
            
            // debug
            log.debug("\n" + "*****CreateUser�p�����[�^*****" +
                      "\n" + "�R�}���hID(���ID+���ʃR�[�h)�F" + l_commandId +
                      "\n" + "�R�}���hID�F" + l_commandCreateUser.getCommandID() + 
                      "\n" + "���O(��)�F" + l_userPersonalInfo.getLastName() +
                      "\n" + "���[���A�h���X�F" + l_userPersonalInfo.getEmail() +
                      "\n" + "FX���O�C��ID�F" + l_fxGftAskingTelegramUnit.fxFirstLoginId +
                      "\n" + "Password�F" + l_fxGftAskingTelegramUnit.fxFirstPassword);
            
        }

        //������.�d������.�����敪 == "03"(�ǉ��J��)�̏ꍇ��
        else if (WEB3AdminAioGftOperationDivDef.ADD_ACCOUNT.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            //�i1�j����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X(RPC�`��)params.�p�����[�^�^�C�v���X�g�ɐݒ肳��Ă���
            //�e�A�J�E���g�e���v���[�g���C���X�^���X�փZ�b�g����B
            ArrayList l_lisCreateAccountInfos = new ArrayList();
            String l_strParameterTypeList = l_rpcParams.getParameterTypeList();
            String[] l_strParameterTypes = null;
            int l_intParameterTypesLength = 0;
            if (!WEB3StringTypeUtility.isEmpty(l_strParameterTypeList))
            {
                l_strParameterTypes =
                    l_strParameterTypeList.split(":");
                l_intParameterTypesLength = l_strParameterTypes.length;
            }

            //���L�������R������؂�Ŏw�肳��Ă���񐔎��{����B
            //�i3-1�jCreateAccountInfo�C���X�^���X�𐶐�����B
            //����������CreateAccountInfo�C���X�^���X�̍��ڂ��ȉ��̒ʂ�Z�b�g����B
            //�A�J�E���g�e���v���[�gID�F
            //����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�p�����[�^�^�C�v���X�g.��n����
            for (int i = 0; i< l_intParameterTypesLength; i++)
            {
                CreateAccountInfo l_createAccountInfo = new CreateAccountInfo();
                l_createAccountInfo.setAccountTemplateId(
                    l_strParameterTypes[i]);
                l_lisCreateAccountInfos.add(l_createAccountInfo);
            }

            //�i2�jCreateAccountInfo�̃I�u�W�F�N�g�z��𐶐����A
            //(1)�Ő�������CreateAccountInfo�C���X�^���X���i�[����B
            CreateAccountInfo[] l_createAccountInfos =
                new CreateAccountInfo[l_intParameterTypesLength];
            l_lisCreateAccountInfos.toArray(l_createAccountInfos);

            //�i3�jCommandAddAccount�C���X�^���X�𐶐�����B
            //CommandAddAccount(arg0 : String, arg1 : String, arg2 : CreateAccountInfoCreateAccountInfo)
            //[����]
            //arg0�F 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h
            //arg1�F ����.GFT�˗��d������.�������O�C��ID
            //arg2�F (2)�Ő�������CreateAccountInfo[]�C���X�^���X
            CommandAddAccount l_commandAddAccount = new CommandAddAccount();
            l_commandAddAccount.setCommandID(l_commandId);
            l_commandAddAccount.setLogin(l_fxGftAskingTelegramUnit.fxFirstLoginId);
            l_commandAddAccount.setAccount(l_lisCreateAccountInfos);

            //�i4�jSendSyncRequest�C���X�^���X�𐶐�����B
            //SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            //[����]
            //arg0�F�i3�j�Ő�������CommandAddAccount�C���X�^���X
            //arg1�F 2)�Ő�������AuthToken�C���X�^���X
            l_commandBase = l_commandAddAccount;
        }

        // ������.�d������.�����敪 == "04�F�o��"�̏ꍇ(FX�ւ̐U�ւ̏ꍇ�j��
        else if(WEB3AdminAioGftOperationDivDef.CASH_OUT.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            
            // �ב֕ۏ؋������ԍ����擾
            long l_accountId = Long.parseLong(l_fxGftAskingTelegramUnit.fxAccountCode);
            
            // ���o���z���擾
            double l_amount = Double.parseDouble(l_fxGftAskingTelegramUnit.cashinoutAmt);

            // �i1�jCommandDeposit�C���X�^���X�𐶐�����B
            //      CommandDeposit(arg0 : String, arg1 : long, arg2 : double, arg3 : String)
            // �@@    [����] 
            //      �@@arg0�F 1�j�Ŏ擾�����،����ID  + ����.GFT�˗��d������.���ʃR�[�h
            //  �@@  �@@arg1�F ����.GFT�˗��d������.�ב֕ۏ؋������ԍ�
            //�@@�@@  �@@arg2�F ����.GFT�˗��d������.���o���z
            //�@@�@@  �@@arg3�F "JPY"
            CommandDeposit l_commandDeposit = new CommandDeposit();
            //l_commandId
            l_commandDeposit.setCommandID(l_commandId);
            //l_accountId
            l_commandDeposit.setAccountID(l_accountId);
            //l_amount
            l_commandDeposit.setAmount(l_amount);
            //"JPY"
            l_commandDeposit.setCurrency("JPY");
            
            // �i2�jSendSyncRequest�C���X�^���X�𐶐�����B
            //      SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            // �@@    [����]
            //  �@@    arg0�F �i1�j�Ő�������CommandDeposit�C���X�^���X
            //�@@�@@    arg1�F 2)�Ő�������AuthToken�C���X�^���X
            l_commandBase = l_commandDeposit;
            
            // debug
            log.debug("\n" + "*****Deposit�p�����[�^*****" +
                      "\n" + "�R�}���hID(���ID+���ʃR�[�h)�F" + l_commandId + 
                      "\n" + "�R�}���hID�F" + l_commandDeposit.getCommandID() +
                      "\n" + "�ב֕ۏ؋������ԍ��F" + l_commandDeposit.getAccountID() +
                      "\n" + "���o���z�F" + l_commandDeposit.getAmount() +
                      "\n" + "�ʉ݁F" + l_commandDeposit.getCurrency());
            
        }
        
        // ������.�d������.�����敪 == "02�F����"�̏ꍇ(FX����U�ւ̏ꍇ�j��
        else if(WEB3AdminAioGftOperationDivDef.CASH_IN.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            
            // �ב֕ۏ؋������ԍ����擾
            long l_accountId = Long.parseLong(l_fxGftAskingTelegramUnit.fxAccountCode);
            
            // ���o���z���擾
            double l_amount = Double.parseDouble(l_fxGftAskingTelegramUnit.cashinoutAmt);
            
            // �i1�jCommandWithdraw�C���X�^���X�𐶐�����B
            //      CommandWithdraw(arg0 : String, arg1 : long, arg2 : double, arg3 : String)
            // �@@    [����] 
            //  �@@    arg0�F 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h
            //  �@@    arg1�F ����.GFT�˗��d������.�ב֕ۏ؋������ԍ�
            //�@@�@@    arg2�F ����.GFT�˗��d������.���o���z
            //�@@�@@    arg3�F "JPY"
            CommandWithdraw l_commandWithdraw = new CommandWithdraw();
            //l_commandId
            l_commandWithdraw.setCommandID(l_commandId);
            //l_accountId
            l_commandWithdraw.setAccountID(l_accountId);
            //l_amount
            l_commandWithdraw.setAmount(l_amount);
            //"JPY"
            l_commandWithdraw.setCurrency("JPY");
            
            // �i2�jSendSyncRequest�C���X�^���X�𐶐�����B
            //      SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            // �@@    [����]
            //  �@@    arg0�F �i1�j�Ő�������CommandWithdraw�C���X�^���X
            //�@@�@@    arg1�F 2)�Ő�������AuthToken�C���X�^���X
            l_commandBase = l_commandWithdraw;
            
            // debug
            log.debug("\n" + "*****Withdraw�p�����[�^*****" +
                      "\n" + "�R�}���hID(���ID+���ʃR�[�h)�F" + l_commandId +
                      "\n" + "�R�}���hID�F" + l_commandWithdraw.getCommandID() + 
                      "\n" + "�ב֕ۏ؋������ԍ��F" + l_commandWithdraw.getAccountID() +
                      "\n" + "���o���z�F" + l_commandWithdraw.getAmount() +
                      "\n" + "�ʉ݁F" + l_commandWithdraw.getCurrency());
            
        }
        //������.�d������.�����敪 == "07�F�U�։\�z"�̏ꍇ��
        else if (WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals(
            l_fxGftAskingTelegramUnit.gftOperationDiv))
        {
            //�i1�jCommandLookupUser�C���X�^���X�𐶐�����B
            //CommandLookupUser(arg0 : String, arg1 : String)
            //arg0�F 1�j�Ŏ擾�����،����ID + ����.GFT�˗��d������.���ʃR�[�h
            //arg1�F ����.GFT�˗��d������.�������O�C��ID
            CommandLookupUser l_commandLookupUser = new CommandLookupUser();
            l_commandLookupUser.setCommandID(l_commandId);
            l_commandLookupUser.setLogin(l_fxGftAskingTelegramUnit.fxFirstLoginId);

            //2�jSendSyncRequest�C���X�^���X�𐶐�����B
            //SendSyncRequest(arg0 : CommandBase, arg1 : AuthToken)
            //arg0�F �i1�j�Ő�������CommandLookupUser�C���X�^���X
            //arg1�F ��������AuthToken�C���X�^���X
            l_commandBase = l_commandLookupUser;
        }

        SendSyncRequestResponse l_sendSyncRequestResponse = null;
        
        try {
            
            // 4�j�T�[�r�X�����N���X�𐶐�����B
            //    AdministrativeAPI_Impl()
            AdministrativeAPI l_service = new AdministrativeAPI();
            
            // 5�j�T�[�r�X�X�^�u�𐶐�����B
            //    service.getAdministrativeAPIPort(arg0 : String)
            //     [����]
            //      arg0 : ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�T�[�r�X��
            AdministrativeAPIPort l_apiPort = l_service.getAdministrativeAPIPort(l_rpcParams.service_name);

            //6�j�T�[�r�X�X�^�u�ɃG���h�|�C���g��ݒ肷��B
            //_setProperty(arg0 : String, arg1 : Object)
            //[����]
            //arg0�F "javax.xml.rpc.service.endpoint.address"
            //arg1�F
            //�|����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̕����� == 3�̏ꍇ
            //����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̔z���3�v�f
            //�|����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̕����� == 1�̏ꍇ
            //����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�G���h�|�C���g���̔z���1�v�f
            String[] l_strEndpointNames = l_rpcParams.getEndpointName().split(";");
            int l_intEndpointNamesLength = l_strEndpointNames.length;
            String l_strParameter = null;
            if (l_intEndpointNamesLength == 3)
            {
                l_strParameter = l_strEndpointNames[2];
            }
            else if (l_intEndpointNamesLength == 1)
            {
                l_strParameter = l_strEndpointNames[0];
            }
            //����L�̏����ȊO�̏ꍇ�A��O���X���[����B
            else
            {
                log.debug("�O���V�X�e���ڑ��G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O���V�X�e���ڑ��G���[�B"
                    );
            }
            BindingProvider l_bp = (BindingProvider)l_apiPort;
            l_bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, l_strParameter);

            // 7�j�ڑ��^�C���A�E�g���Ԃ��Z�b�g����B
            l_bp.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", l_rpcParams.response_timeout);
            l_bp.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", l_rpcParams.response_timeout);

            ThreadLocalSystemAttributesRegistry.setAttribute("branch_id", String.valueOf(l_rpcParams.branch_id));
            ThreadLocalSystemAttributesRegistry.setAttribute("connect_div", String.valueOf(l_rpcParams.connect_div));
            
            Binding l_bd = l_bp.getBinding();
            List l_lisHandlerChain = l_bd.getHandlerChain();
            WEB3FXSOAPMsgHandler l_handler = new WEB3FXSOAPMsgHandler();
            l_lisHandlerChain.add(l_handler);
            l_bd.setHandlerChain(l_lisHandlerChain);
            
            // 9�j���b�Z�[�W����M
            //    regSoap_Stub.sendSyncRequest(arg0 : SendSyncRequest)
            //     [����]
            //      arg0 : 2�j�Ő����������N�G�X�g�f�[�^
            l_apiPort.sendSyncRequest(l_commandBase, l_token, l_rejectedCommand, l_sendSyncRequestResult);
        } catch (AuthorizationFailedException l_ex){
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // �n�b�V���F�؃G���[
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_90224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);            
          } catch (InternalErrorException l_ex) {
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // �v���I�ȃV�X�e���G���[
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        
        log.exiting(STR_METHOD_NAME);
        
        // 10�j9�j�Ŏ�M����SOAP�ڑ����X�|���X�f�[�^��ԋp����B
    }

    /**
     * (getSOAPGFT��t���ʃR�[�h) <BR>
     * ���ʒʒm�d���ɃZ�b�g�����t���ʃR�[�h���擾����B <BR>
     * <BR>
     * �P�j����.GFT��t���ʃR�[�h�̒l�ɂ��A�Ή������t���ʃR�[�h���Z�b�g����B<BR>
     * �E����.GFT��t���ʃR�[�h == �h100�h�iDone.�j �̏ꍇ�A<BR>
     * �@@��t���ʃR�[�h.�h00000000�h�i���������j<BR>
     * �E����.GFT��t���ʃR�[�h == �h200�h�iMissed parameter�j�̏ꍇ�A<BR>
     * �@@��t���ʃR�[�h.�h00000601�h�i�˗��d���̏����G���[�i�K�{���ږ����́j�j <BR> 
     * �E����.GFT��t���ʃR�[�h == �h201�h�iWrong parameter value�j�̏ꍇ�A<BR>
     * �@@(a).����.GFT��t���ʏڍ׃R�[�h == �h2014�h�iAmount is more than the real withdrawable cash�j�ł���΁A<BR>
     * �@@�@@��t���ʃR�[�h.�h00000204�h�i���[�U�[�̏؋��������c���s���ɂ��G���[�j<BR>
     * �@@(b).����.GFT��t���ʏڍ׃R�[�h == �h2015�h�iDuplicate login�j�̏ꍇ�A<BR>
     * �@@�@@��t���ʃR�[�h.�h00000801�h�i��d�����G���[�j<BR>
     * �@@(c).��L(a)�E(b)�ȊO�̏ꍇ�́A<BR>
     * �@@�@@��t���ʃR�[�h.�h00000602�h�i�˗��d���̏����G���[�i�s���ȕ����̎g�p�j�j<BR>
     * �E����.GFT��t���ʃR�[�h == �h202�h�iRestriction violation�j�̏ꍇ�A<BR>
     * �@@��t���ʃR�[�h.�h00000299�h�i��L�ȊO�Ń��[�U�[�ɋN������G���[�j<BR> 
     * �E����.GFT��t���ʃR�[�h == �h251�h�iDuplicate command registration�j�̏ꍇ�A<BR>
     * �@@��t���ʃR�[�h.�h00000609�h�i��L�ȊO�œd�������ɋN������G���[�j<BR>
     * �E����.GFT��t���ʃR�[�h == �h252�h�iEntity does not exist�j�̏ꍇ�A<BR>
     * �@@(a).����.GFT��t���ʏڍ׃R�[�h == �h2523�h�iAccount�j�̏ꍇ�A<BR>
     * �@@�@@��t���ʃR�[�h.�h00000501�h�i�Y������ב֕ۏ؋����������݂��Ȃ��j<BR>
     * �@@(b).��L(a)�ȊO�̏ꍇ�́A<BR>
     * �@@�@@��t���ʃR�[�h.�h00000602�h�i�˗��d���̏����G���[�i�s���ȕ����̎g�p�j�j<BR>
     * �E����.GFT��t���ʃR�[�h == �h253�h�iRestriction violation�j�̏ꍇ�A<BR>
     * �@@��t���ʃR�[�h.�h00000299�h�i��L�ȊO�Ń��[�U�[�ɋN������G���[�j<BR>
     * �E����.GFT��t���ʃR�[�h == �h300�h�iInternal service error. Please, contact support.�j�̏ꍇ�A<BR>
     * �@@��t���ʃR�[�h.�h00000199�h�i��L�ȊO�Ńz�X�g�V�X�e���ɋN������G���[�j<BR>
     * �E����.GFT��t���ʃR�[�h == �h350�h�iInternal service error. Please, contact support.�j�̏ꍇ�A<BR>
     * �@@��t���ʃR�[�h.�h00000199�h�i��L�ȊO�Ńz�X�g�V�X�e���ɋN������G���[�j<BR>
     * �E��L�ȊO�̏ꍇ�A��t���ʃR�[�h.�h00000901�h�i��L�A�y�щ��L�ȊO�̃G���[�j<BR>
     * �Q�j��t���ʃR�[�h��ԋp����B<BR>
     * <BR>
     * @@param l_acceptResultCode GFT��t���ʃR�[�h
     * @@param l_acceptResultDetailCode GFT��t���ʏڍ׃R�[�h
     * @@return String
     */
    protected String getSoapAcceptResultCode(String l_acceptResultCode,
                                           String l_acceptResultDetailCode)
    {

        String STR_METHOD_NAME = "getSoapAcceptResultCode(String, String)";
        log.entering(STR_METHOD_NAME);
        
        String l_soapAcceptResultCode = null;
        
        // �P�j����.GFT��t���ʃR�[�h�̒l�ɂ��A�Ή������t���ʃR�[�h���Z�b�g����B
        // �E����.GFT��t���ʃR�[�h == �h100�h�iDone.�j �̏ꍇ�A
        // �@@��t���ʃR�[�h.�h00000000�h�i���������j
        if (WEB3GftSoapResultCodeDef.NORMAL.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
        }
        // �E����.GFT��t���ʃR�[�h == �h200�h�iMissed parameter�j�̏ꍇ�A
        // �@@��t���ʃR�[�h.�h00000601�h�i�˗��d���̏����G���[�i�K�{���ږ����́j�j 
        else if (WEB3GftSoapResultCodeDef.MISSED_PARAMETER.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000601;
        }
        // �E����.GFT��t���ʃR�[�h == �h201�h�iWrong parameter value�j�̏ꍇ�A
        // �@@(a).����.GFT��t���ʏڍ׃R�[�h == �h2014�h�iAmount is more than the real withdrawable cash�j�ł���΁A
        // �@@�@@��t���ʃR�[�h.�h00000204�h�i���[�U�[�̏؋��������c���s���ɂ��G���[�j
        // �@@(b).����.GFT��t���ʏڍ׃R�[�h == �h2015�h�iDuplicate login�j�̏ꍇ�A
        // �@@�@@��t���ʃR�[�h.�h00000801�h�i��d�����G���[�j
        // �@@(c).��L(a)�E(b)�ȊO�̏ꍇ�́A
        // �@@�@@��t���ʃR�[�h.�h00000602�h�i�˗��d���̏����G���[�i�s���ȕ����̎g�p�j�j
        else if (WEB3GftSoapResultCodeDef.WRONG_PARAMETER_VALUE.equals(l_acceptResultCode))
        {
            if (WEB3GftSoapResultCodeDef.LACK_OF_BALANCE.equals(l_acceptResultDetailCode))
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204;
            }
            else if (WEB3GftSoapResultCodeDef.DUPLICATE_LOGIN.equals(l_acceptResultDetailCode))
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801;
            }
            else
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000602;
            }
        }
        // �E����.GFT��t���ʃR�[�h == �h202�h�iRestriction violation�j�̏ꍇ�A
        // �@@��t���ʃR�[�h.�h00000299�h�i��L�ȊO�Ń��[�U�[�ɋN������G���[�j
        else if (WEB3GftSoapResultCodeDef.RESTRICTION_VIORATION.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000299;
        }
        // �E����.GFT��t���ʃR�[�h == �h251�h�iDuplicate command registration�j�̏ꍇ�A
        // �@@��t���ʃR�[�h.�h00000609�h�i��L�ȊO�œd�������ɋN������G���[�j
        else if (WEB3GftSoapResultCodeDef.DUPLICATE_COMMAND_ERROR.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000609;
        }
        // �E����.GFT��t���ʃR�[�h == �h252�h�iEntity does not exist�j�̏ꍇ�A
        // �@@(a).����.GFT��t���ʏڍ׃R�[�h == �h2523�h�iAccount�j�̏ꍇ�A
        // �@@�@@��t���ʃR�[�h.�h00000501�h�i�Y������ב֕ۏ؋����������݂��Ȃ��j
        // �@@(b).��L(a)�ȊO�̏ꍇ�́A<BR>
        // �@@�@@��t���ʃR�[�h.�h00000602�h�i�˗��d���̏����G���[�i�s���ȕ����̎g�p�j�j
        else if (WEB3GftSoapResultCodeDef.NO_ENTITY_ERROR.equals(l_acceptResultCode))
        {
            if (WEB3GftSoapResultCodeDef.ACCOUNT_ERROR_OF_ENTITY.equals(l_acceptResultDetailCode))
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501;
            }
            else
            {
                l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000602;
            }
        }
        // �E����.GFT��t���ʃR�[�h == �h253�h�iRestriction violation�j�̏ꍇ�A
        // �@@��t���ʃR�[�h.�h00000299�h�i��L�ȊO�Ń��[�U�[�ɋN������G���[�j
        else if (WEB3GftSoapResultCodeDef.RESTRICTION_ERROR.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000299;
        }
        // �E����.GFT��t���ʃR�[�h == �h300�h�iInternal service error. Please, contact support.�j�̏ꍇ�A
        // �@@��t���ʃR�[�h.�h00000199�h�i��L�ȊO�Ńz�X�g�V�X�e���ɋN������G���[�j
        else if (WEB3GftSoapResultCodeDef.INTERNAL_SERVICE_ERROR.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199;
        }
        // �E����.GFT��t���ʃR�[�h == �h350�h�iInternal service error. Please, contact support.�j�̏ꍇ�A
        // �@@��t���ʃR�[�h.�h00000199�h�i��L�ȊO�Ńz�X�g�V�X�e���ɋN������G���[�j
        else if (WEB3GftSoapResultCodeDef.SERVICE_ERROR.equals(l_acceptResultCode))
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199;
        }
        // �E��L�ȊO�̏ꍇ�A��t���ʃR�[�h.�h00000901�h�i��L�A�y�щ��L�ȊO�̃G���[�j
        else
        {
            l_soapAcceptResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000901;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        // �Q�j��t���ʃR�[�h��ԋp����B
        return l_soapAcceptResultCode;
    }
}
@


1.9
log
@*** empty log message ***
@
text
@d178 8
a185 1
                l_strReturnDetailValue = l_rejectedCommand.value.getMinorErrorCode() + "";
d194 8
a201 2
                l_strReturnDetailValue = l_sendSyncRequestResult.value.getMinorStatusCode() + "";
               
@


1.8
log
@*** empty log message ***
@
text
@a58 1
import com.sun.xml.internal.ws.client.BindingProviderProperties;
@


1.7
log
@*** empty log message ***
@
text
@d594 1
a594 1
        bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", l_rpcParams.response_timeout);
d596 1
a596 1
        bp.getRequestContext().put("com.sun.xml.ws.request.timeout", l_rpcParams.response_timeout);
d1278 2
a1279 2
            l_bp.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, l_rpcParams.response_timeout);
            l_bp.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, l_rpcParams.response_timeout);
@


1.6
log
@*** empty log message ***
@
text
@d177 1
a177 2
                l_strReturnValue = 
                    Integer.toString(l_rejectedCommand.value.getMajorErrorCode());
d179 1
a179 2
                l_strReturnDetailValue = 
                    Integer.toString(l_rejectedCommand.value.getMinorErrorCode());
d186 1
a186 2
                l_strReturnValue = 
                    Integer.toString(l_sendSyncRequestResult.value.getMajorStatusCode());
d188 1
a188 2
                l_strReturnDetailValue = 
                    Integer.toString(l_sendSyncRequestResult.value.getMinorStatusCode());
@


1.5
log
@*** empty log message ***
@
text
@a149 1
//        SendSyncRequestResponse l_sendSyncRequestResponse = null;
d921 1
a921 1
    protected SendSyncRequestResponse sendSoapMessage(
a1324 1
        return l_sendSyncRequestResponse;  
@


1.4
log
@*** empty log message ***
@
text
@d150 1
a150 1
        SendSyncRequestResponse l_sendSyncRequestResponse = null;
d176 1
a176 1
            if (l_sendSyncRequestResponse.getRejectedCommand() != null)
d179 1
a179 1
                    Integer.toString(l_sendSyncRequestResponse.getRejectedCommand().getMajorErrorCode());
d182 1
a182 1
                    Integer.toString(l_sendSyncRequestResponse.getRejectedCommand().getMinorErrorCode());
d190 1
a190 1
                    Integer.toString(l_sendSyncRequestResponse.getSendSyncRequestResult().getMajorStatusCode());
d193 1
a193 1
                    Integer.toString(l_sendSyncRequestResponse.getSendSyncRequestResult().getMinorStatusCode());
@


1.3
log
@*** empty log message ***
@
text
@d59 1
d1283 2
a1284 6
            //    setProperty(arg0 : String, arg1 : Object)
            //     [����]
            //1 minute for connection ((BindingProvider)
            l_bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", l_rpcParams.response_timeout);
            //3 minutos for request ((BindingProvider)
            l_bp.getRequestContext().put("com.sun.xml.ws.request.timeout", l_rpcParams.response_timeout);
@


1.2
log
@*** empty log message ***
@
text
@a448 4
     * �@@�@@�i3�jkey : "weblogic.webservice.transport.https.proxy.host" <BR>
     * �@@�@@ �@@ value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f <BR>
     * �@@�@@�i4�jkey : "weblogic.webservice.transport.https.proxy.port" <BR>
     * �@@�@@ �@@ value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f <BR>
a454 4
     * �@@�@@�i3�jkey : "weblogic.webservice.transport.http.proxy.host" <BR>
     * �@@�@@ �@@ value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f <BR>
     * �@@�@@�i4�jkey : "weblogic.webservice.transport.http.proxy.port"<BR>
     * �@@�@@ �@@ value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f <BR>
a479 2
     * arg0�F "weblogic.webservice.rpc.timeoutsecs" <BR>
     * arg1�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�ڑ��^�C���A�E�g���� <BR>
a516 4
      // �@@�@@�i3�jkey   : "weblogic.webservice.transport.https.proxy.host"
      // �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
      // �@@�@@�i4�jkey   : "weblogic.webservice.transport.https.proxy.port"
      // �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
a522 4
      // �@@�@@�i3�jkey   : "weblogic.webservice.transport.http.proxy.host"
      // �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���1�v�f
      // �@@�@@�i4�jkey   : "weblogic.webservice.transport.http.proxy.port"
      // �@@�@@ �@@  value : 1�j�Ŏ擾�����ڑ�����̔z���2�v�f
a566 2
      
//      System.setProperty("weblogic.webservice.UseWebLogicURLStreamHandler", "true");
a596 2
        //      arg0�F "weblogic.webservice.rpc.timeoutsecs" 
        //      arg1�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�ڑ��^�C���A�E�g���� 
a601 3
        // debug
//        log.debug("\n" + "validateConnect() - adminAPI_Stub._getProperty() : " + adminAPI_Stub._getProperty("weblogic.webservice.rpc.timeoutsecs").toString());
        
a870 2
     * �@@�@@�@@�@@arg0�F "weblogic.webservice.rpc.timeoutsecs"<BR>
     * �@@�@@�@@�@@arg1�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�ڑ��^�C���A�E�g����<BR>
a1233 1
//        System.setProperty("weblogic.webservice.UseWebLogicURLStreamHandler", "true");
d1283 1
a1283 3
            //     [����] 
            //      arg0�F "weblogic.webservice.rpc.timeoutsecs" 
            //      arg1�F ����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams.�ڑ��^�C���A�E�g���� 
@


1.1
log
@*** empty log message ***
@
text
@a12 1
import java.rmi.RemoteException;
d22 3
a24 4
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;
d31 1
d35 24
a58 24
import com.gftforex.soap.api.AuthToken;
import com.gftforex.soap.api.CommandAddAccount;
import com.gftforex.soap.api.CommandCreateUser;
import com.gftforex.soap.api.CommandDeposit;
import com.gftforex.soap.api.CommandLookupUser;
import com.gftforex.soap.api.CommandWithdraw;
import com.gftforex.soap.api.CreateAccountInfo;
import com.gftforex.soap.api.CreateUserInfo;
import com.gftforex.soap.api.LookupAccountInfo;
import com.gftforex.soap.api.LookupUserInfo;
import com.gftforex.soap.api.ResultInfoAddAccount;
import com.gftforex.soap.api.ResultInfoBase;
import com.gftforex.soap.api.ResultInfoCreateUser;
import com.gftforex.soap.api.ResultInfoLookupUser;
import com.gftforex.soap.api.SendSyncRequest;
import com.gftforex.soap.api.SendSyncRequestResponse;
import com.gftforex.soap.api.UserPersonalInfo;
import com.gftforex.soap.api.UserSystemInfo;

import esupport.client.AdministrativeAPI;
import esupport.client.AdministrativeAPIPort_Stub;
import esupport.client.AdministrativeAPI_Impl;
import esupport.client.AuthorizationFailedException;
import esupport.client.InternalErrorException;
d153 2
d164 7
a170 5
            l_sendSyncRequestResponse = this.sendSoapMessage(
                (WEB3FXGftAskingTelegramUnit)l_message,
                l_prefRpcParams,
                l_strGMT,
                l_strHashValue);
d293 1
a293 1
                    (ResultInfoCreateUser)l_sendSyncRequestResponse.getSendSyncRequestResult();
d300 1
a300 1
                        this.connectionResultDetails.put(FX_ACC_01, new Long(l_resultInfoCreateUser.getAccountIds()[0]));
d302 1
a302 1
                        this.connectionResultDetails.put(FX_ACC_10, new Long(l_resultInfoCreateUser.getAccountIds()[1]));
d304 1
a304 1
                        this.connectionResultDetails.put(CFD_ACC, new Long(l_resultInfoCreateUser.getAccountIds()[2]));
d313 1
a313 1
                            this.connectionResultDetails.put(FX_ACC_01, new Long(l_resultInfoCreateUser.getAccountIds()[0]));
d315 1
a315 1
                            this.connectionResultDetails.put(FX_ACC_10, new Long(l_resultInfoCreateUser.getAccountIds()[1]));
d321 1
a321 1
                            this.connectionResultDetails.put(CFD_ACC, new Long(l_resultInfoCreateUser.getAccountIds()[0]));
d330 1
a330 1
                    (ResultInfoAddAccount)l_sendSyncRequestResponse.getSendSyncRequestResult();
d337 1
a337 1
                        this.connectionResultDetails.put(FX_ACC_01, new Long(l_resultInfoAddAccount.getAccountId()[0]));
d339 1
a339 1
                        this.connectionResultDetails.put(FX_ACC_10, new Long(l_resultInfoAddAccount.getAccountId()[1]));
d345 1
a345 1
                        this.connectionResultDetails.put(CFD_ACC, new Long(l_resultInfoAddAccount.getAccountId()[0]));
d353 1
a353 1
                ResultInfoBase l_resultInfoBase = l_sendSyncRequestResponse.getSendSyncRequestResult();
d358 2
a359 2
                    LookupAccountInfo[] l_lookUpAccInfos = l_lookupUserInfo.getAccounts();
                    if (l_lookUpAccInfos != null && l_lookUpAccInfos.length > 0)
d363 1
a363 1
                        for (int i = 0; i < l_lookUpAccInfos.length; i++)
d365 2
a366 2
                            l_strAccountId = l_lookUpAccInfos[i].getAccountId() + "";
                            l_hmAmount.put(l_strAccountId, WEB3StringTypeUtility.formatNumber(l_lookUpAccInfos[i].getWithdrawableAmount()));
a553 2
            System.setProperty("weblogic.webservice.transport.https.proxy.host", l_urlArr[0].trim());
            System.setProperty("weblogic.webservice.transport.https.proxy.port", l_urlArr[1].trim());
d556 1
a556 3
                "\nhttps.proxyPort (" + l_urlArr[1] + ")" +
                "\nweblogic.webservice.transport.https.proxy.host (" + l_urlArr[0] + ")" +
                "\nweblogic.webservice.transport.https.proxy.port (" + l_urlArr[1] + ")";
a561 2
            System.setProperty("weblogic.webservice.transport.http.proxy.host", l_urlArr[0].trim());
            System.setProperty("weblogic.webservice.transport.http.proxy.port", l_urlArr[1].trim());
d564 1
a564 3
                "\nhttp.proxyPort (" + l_urlArr[1] + ")" +
                "\nweblogic.webservice.transport.http.proxy.host (" + l_urlArr[0] + ")" +
                "\nweblogic.webservice.transport.http.proxy.port (" + l_urlArr[1] + ")";
a587 2
      try{
        
d590 1
a590 1
        AdministrativeAPI service = new AdministrativeAPI_Impl();
d596 2
a597 2
        AdministrativeAPIPort_Stub adminAPI_Stub = 
            (AdministrativeAPIPort_Stub) service.getAdministrativeAPIPort(l_rpcParams.service_name);
d610 3
a612 4

        adminAPI_Stub._setProperty(
            "javax.xml.rpc.service.endpoint.address",
            l_strParameter);
d619 4
a622 3
        adminAPI_Stub._setProperty("weblogic.webservice.rpc.timeoutsecs", l_rpcParams.response_timeout);
        

d625 1
a625 1
        log.debug("\n" + "validateConnect() - adminAPI_Stub._getProperty() : " + adminAPI_Stub._getProperty("weblogic.webservice.rpc.timeoutsecs").toString());
a630 34
      } catch (RemoteException l_ex) {
        
        log.error(l_ex.getMessage(), l_ex);
        log.exiting(STR_METHOD_NAME);
        // �v���I�ȃV�X�e���G���[
        throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
          
      } catch (ServiceException l_ex) {
        
        log.error(l_ex.getMessage(), l_ex);
        log.exiting(STR_METHOD_NAME);
        // �v���I�ȃV�X�e���G���[
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        
      } catch (IOException l_ex) {
        
        log.error(l_ex.getMessage(), l_ex);
        log.exiting(STR_METHOD_NAME);
        // �v���I�ȃV�X�e���G���[
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
        
    } 
d952 3
a954 1
            String l_hashValue) throws WEB3BaseException
d1001 1
a1001 1
        SendSyncRequest l_sendSyncRequest = null;
d1064 5
a1068 6
            CreateUserInfo l_createUserInfo = 
                new CreateUserInfo(
                    l_rpcParams.parameter_list,
                    l_userPersonalInfo,
                    l_userSystemInfo,
                    l_createAccountInfos);
d1075 3
a1077 2
            CommandCreateUser l_commandCreateUser = 
                new CommandCreateUser(l_commandId,l_createUserInfo);
d1084 1
a1084 2
            l_sendSyncRequest = 
                new SendSyncRequest(l_commandCreateUser,l_token);
d1139 4
a1142 5
            CommandAddAccount l_commandAddAccount =
                new CommandAddAccount(
                    l_commandId,
                    l_fxGftAskingTelegramUnit.fxFirstLoginId,
                    l_createAccountInfos);
d1149 1
a1149 2
            l_sendSyncRequest =
                new SendSyncRequest(l_commandAddAccount, l_token);
d1170 9
a1178 2
            CommandDeposit l_commandDeposit = 
                new CommandDeposit(l_commandId,l_accountId,l_amount,"JPY");
d1185 1
a1185 2
            l_sendSyncRequest = 
                new SendSyncRequest(l_commandDeposit,l_token);
d1215 9
a1223 2
            CommandWithdraw l_commandWithdraw = 
                new CommandWithdraw(l_commandId,l_accountId,l_amount,"JPY");
d1230 1
a1230 2
            l_sendSyncRequest = 
                new SendSyncRequest(l_commandWithdraw,l_token);
d1249 3
a1251 2
            CommandLookupUser l_commandLookupUser =
                new CommandLookupUser(l_commandId, l_fxGftAskingTelegramUnit.fxFirstLoginId);
d1257 1
a1257 2
            l_sendSyncRequest =
                new SendSyncRequest(l_commandLookupUser, l_token);
d1267 1
a1267 1
            AdministrativeAPI service = new AdministrativeAPI_Impl();
d1273 1
a1273 2
            AdministrativeAPIPort_Stub adminAPI_Stub = 
            (AdministrativeAPIPort_Stub) service.getAdministrativeAPIPort(l_rpcParams.service_name);
d1306 2
a1307 3
            adminAPI_Stub._setProperty(
                "javax.xml.rpc.service.endpoint.address",
                l_strParameter);
d1314 13
a1326 29
            adminAPI_Stub._setProperty("weblogic.webservice.rpc.timeoutsecs", l_rpcParams.response_timeout);
            
            // 8�jSOAP���b�Z�[�W�n���h�����Z�b�g����B 
            //    (1)Web �T�[�r�X �|�[�g�̏C�������i�[����I�u�W�F�N�g���쐬 
            QName portName = new QName( l_rpcParams.target_namespace_name, l_rpcParams.service_name);

            //    (2)HandlerRegistry �I�u�W�F�N�g���쐬 
            HandlerRegistry registry = service.getHandlerRegistry();
            
            //    (3)HandlerInfo�ɐݒ肷��config(�FMap)�f�[�^�̍쐬 
            //�@@�@@�@@�@@[put()�Ɏw�肷�����] 
            //�@@�@@�@@�@@�@@key�F"BranchId" 
            //�@@�@@�@@�@@�@@value�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams. ���XID
            //�@@�@@�@@�@@[put()�Ɏw�肷�����] 
            //�@@�@@�@@�@@�@@key�F"ConnectDiv" 
            //�@@�@@�@@�@@�@@value�F����.�O���V�X�e��SOAP�ڑ��v���t�@@�����X�iRPC�`���jparams. �ڑ��敪 
            HashMap map = new HashMap();
            
            String branch_id_str=String.valueOf(l_rpcParams.branch_id);
            
            map.put("BranchId",branch_id_str);
            map.put("ConnectDiv",l_rpcParams.connect_div);

            //    (4)SOAP���b�Z�[�W�̃n���h�� �`�F�[��(�FArrayList)���� 
            List handlerList = new ArrayList();
            handlerList.add( new HandlerInfo( WEB3FXSOAPMsgHandler.class, map, null ) );

            //�@@�@@(5)�n���h�� �`�F�[����o�^
            registry.setHandlerChain( portName, handlerList );          
d1332 1
a1332 3
            l_sendSyncRequestResponse = 
                adminAPI_Stub.sendSyncRequest(l_sendSyncRequest);
            
d1342 2
a1343 36
                l_ex);
            
        } catch (InternalErrorException l_ex){
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // �ʐM�G���[
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01802,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
          } catch (RemoteException l_ex) {
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // �O���V�X�e���ڑ��G���[
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
              
          } catch (ServiceException l_ex) {
            
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            // �v���I�ȃV�X�e���G���[
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
          } catch (IOException l_ex) {
@

