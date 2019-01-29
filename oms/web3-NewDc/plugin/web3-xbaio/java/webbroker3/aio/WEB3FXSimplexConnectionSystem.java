head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXSimplexConnectionSystem.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Simplex�ڑ��V�X�e��(WEB3FXSimplexConnectionSystem.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/17 �����F (���u) �V�K�쐬�E���f��1200 1212 1213 1218 1221 1226 1230 1237
Revision History : 2009/10/14 �����F (���u) ���f��1240
*/
package webbroker3.aio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioFXSendRecieveDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXSimplexAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3GftSoapResultCodeDef;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Simplex�ڑ��V�X�e��) <BR>
 * Simplex�ڑ��V�X�e�������N���X <BR>
 * ���ۃN���X<BR>
 * @@author �����F(���u)
 * @@version 1.0
 */
public abstract class WEB3FXSimplexConnectionSystem extends WEB3FXExtSystemCommon implements WEB3ExtConnection
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSimplexConnectionSystem.class);
    
    /**
     * ���ۃ��\�b�h�iabstract�j <BR>
     * <BR>
     * �����J�݂̏ꍇ�ASimplex�����J�ݐڑ��V�X�e���N���X��createHash <BR>
     * �U�։\�z�̏ꍇ�ASimplex�U�։\�z�ڑ��V�X�e����createHash���Ăт����B<BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex�˗��d������)<BR>
     * Simplex�˗��d������<BR>
     * @@param l_strOperationName - (�I�y���[�V������)<BR>
     * �I�y���[�V������<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     */
    protected abstract WEB3FXSimplexAskingTelegramUnit createHash(
        WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit, String l_strOperationName) throws WEB3BaseException;
    
    /**
     * ���ۃ��\�b�h�iabstract�j <BR>
     * <BR>
     * �����J�݂̏ꍇ�ASimplex�����J�ݐڑ��V�X�e���N���X��createURL <BR>
     * �U�։\�z�̏ꍇ�ASimplex�U�։\�z�ڑ��V�X�e����createURL���Ăт����B<BR>
     * @@param l_strEndpointName - (�G���h�|�C���g��)<BR>
     * �G���h�|�C���g��<BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex�˗��d������)<BR>
     * Simplex�˗��d������<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     */
    protected abstract String createURL(
        String l_strEndpointName, WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit) throws WEB3BaseException;
    
    /**
     * (createSimplex�˗��d������)<BR>
     * ���ۃ��\�b�h�iabstract�j<BR>
     * <BR>
     * �����J�݂̏ꍇ�ASimplex�����J�ݐڑ��V�X�e���N���X��createSimplex�˗��d������<BR>
     * �U�։\�z�̏ꍇ�ASimplex�U�։\�z�ڑ��V�X�e����createSimplex�˗��d�����ׂ��Ăт����B<BR>
     * @@param l_message - (GFT�d�����b�Z�[�W)<BR>
     * GFT�d�����b�Z�[�W<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     * @@throws WEB3BaseException
     */
    protected abstract WEB3FXSimplexAskingTelegramUnit createSimplexAskingTelegramUnit(
        Message l_message) throws WEB3BaseException;

    /**
     * (saveSOAP���b�Z�[�W)<BR>
     * SOAP���b�Z�[�W�ۑ��e�[�u���ɁA���b�Z�[�W���i�[����B <BR>
     * <BR>
     * FX�f�[�^����T�[�r�XImpl�ɂ́AinsertSOAP���b�Z�[�W()���Ăяo���B <BR>
     * DB�X�V�d�l�uSimplex�ڑ�_SOAP���b�Z�[�W�ۑ��e�[�u��.xls�v���Q�Ƃ��Ă��������B <BR>
     * [����]�F <BR>
     * �@@���XID�F����.���XID <BR>
     * �@@�ڑ��敪�F����.�ڑ��敪 <BR>
     * �@@����M�敪�F����.����M�敪 <BR>
     * �@@���b�Z�[�W�F����.���b�Z�[�W <BR>
     * @@param l_lngBranchID - (���XID)<BR>
     * ���XID<BR>
     * @@param l_strConnectionDiv - (�ڑ��敪)<BR>
     * �ڑ��敪<BR>
     * @@param l_strSendRcvDiv - (����M�敪)<BR>
     * ����M�敪<BR>
     * <BR>
     * 0�F���M<BR>
     * 1�F��M<BR>
     * @@param l_strMessage - (���b�Z�[�W)<BR>
     * ���b�Z�[�W<BR>
     */
    protected void saveSOAPMessage(
        long l_lngBranchID, String l_strConnectionDiv, String l_strSendRcvDiv, String l_strMessage)
    {
        final String STR_METHOD_NAME = "saveSOAPMessage(long, String, String, String) ";
        log.entering (STR_METHOD_NAME );

        WEB3FXDataControlService l_service =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        l_service.insertSOAPMessage(l_lngBranchID, l_strConnectionDiv, l_strSendRcvDiv, l_strMessage);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (sendURL���b�Z�[�W )<BR>
     * sendURL���b�Z�[�W���s���B <BR>
     * <BR>
     * �P�jURL�N���X�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j�ڑ��𐶐�����B <BR>
     * <BR>
     * �R�j�ڑ�������ݒ肷��B <BR>
     * <BR>
     * �S�j�ڑ����s�B <BR>
     * <BR>
     * �T�j�o�̓X�g���[�����擾�B <BR>
     * �@@�T�|�P�jhttp�R�[�h��Ԃ�����ł���ꍇ�Ahttp�����̖߂�l��StringBuffer�Ɋi�[����B <BR>
     * <BR>
     * �U�jStringBuffer.toString()��ԋp����B<BR>
     * <BR>
     * ���ȏ㏈���Ɉُ킪��������ꍇ�A<BR>
     * WEB3�̃G���[�uBUSINESS_ERROR_02398�v���X���[����B<BR>
     * @@param l_strConnectionURL - (�ڑ�URL)<BR>
     * �ڑ�URL<BR>
     * @@return String
     */
    protected String sendURLMessage(String l_strConnectionURL) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendURLMessage(String)";
        log.entering (STR_METHOD_NAME );

        HttpURLConnection l_connection = null;
        BufferedReader l_brResult = null;
        String l_strResult= null;
        try
        {
        	
            String l_urlArr[] = l_strConnectionURL.split(";");
            String l_soapUrl = "";
            
            if (l_urlArr.length == 3)
            {
            	l_soapUrl = l_urlArr[2];
            	if (l_soapUrl.indexOf("https://") >= 0)
            	{
                    this.registerMyHostnameVerifier();
                    URL l_postURL = new URL(null, l_strConnectionURL, new sun.net.www.protocol.https.Handler());
                    l_connection = (HttpURLConnection)l_postURL.openConnection();
                    l_connection.setRequestMethod("GET");
                    l_connection.setRequestProperty("Content-Type", "text/html;charset=Windows-31J");
                    l_connection.setRequestProperty("Connection", "Keep - Alive");
                    l_connection.connect();
            	}
            	else
            	{
                	URL l_postURL = new URL(null, l_strConnectionURL);
                	l_connection = (HttpURLConnection)l_postURL.openConnection();
                	l_connection.setRequestMethod("GET");
                	l_connection.setRequestProperty("Content-Type", "text/html;charset=Windows-31J");
                	l_connection.setRequestProperty("Connection", "Keep - Alive");
                	l_connection.connect();
            	}
            }
            else if (l_urlArr.length == 1)
            {    
            	
            	if (l_strConnectionURL.indexOf("https://") >= 0)
            	{
                    this.registerMyHostnameVerifier();
                    URL l_postURL = new URL(null, l_strConnectionURL, new sun.net.www.protocol.https.Handler());
                    l_connection = (HttpURLConnection)l_postURL.openConnection();
                    l_connection.setRequestMethod("GET");
                    l_connection.setRequestProperty("Content-Type", "text/html;charset=Windows-31J");
                    l_connection.setRequestProperty("Connection", "Keep - Alive");
                    l_connection.connect();
            	}
            	else
            	{
                	URL l_postURL = new URL(null, l_strConnectionURL);
                	l_connection = (HttpURLConnection)l_postURL.openConnection();
                	l_connection.setRequestMethod("GET");
                	l_connection.setRequestProperty("Content-Type", "text/html;charset=Windows-31J");
                	l_connection.setRequestProperty("Connection", "Keep - Alive");
                	l_connection.connect();
            	}
            }

            // �o�̓X�g���[�����擾
            String l_strResponseCode = l_connection.getHeaderField(0);
            // http�R�[�h��Ԃ�����ł���ꍇ�Ahttp�����̖߂�l��StringBuffer�Ɋi�[����B
            if (l_strResponseCode != null && l_strResponseCode.indexOf("200") >= 0)
            {
                l_brResult = new BufferedReader(new InputStreamReader(l_connection.getInputStream(), "Windows-31J"));
                StringBuffer l_sbResult = new StringBuffer();
                String l_strLine = null;
                while ((l_strLine = l_brResult.readLine()) != null)
                {
                    l_sbResult.append(l_strLine);
                }
                l_strResult = l_sbResult.toString();
            }
        }
        catch (Exception l_ex)
        {
            if (l_connection != null)
            {
                l_connection.disconnect();
            }
            //���ȏ㏈���Ɉُ킪��������ꍇ�AWEB3�̃G���[�uBUSINESS_ERROR_02398�v���X���[����B
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        finally
        {
            if (l_brResult != null)
            {
                try
                {
                    l_brResult.close();
                }
                catch (IOException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strResult;
    }

    /**
     * (getURLSimplex��t���ʃR�[�h)<BR>
     * sendURL���b�Z�[�W�̌��ʂ���͂���B <BR>
     * <BR>
     * �P�jsendURL���b�Z�[�W�̌��ʃp�����[�^��errorID������΁AconnectResult�ɐ擪�̈�ڂ�errorID���Z�b�g<BR>
     * ����ȊO�͂O���Z�b�g<BR>
     * �@@�@@key�F�@@�O���ڑ�.CONNECT_RESULT<BR>
     * �@@�@@value�F�@@sendURL���b�Z�[�W�̌��ʃp�����[�^��errorID�@@or �O<BR>
     * <BR>
     * �Q�jsendURL���b�Z�[�W�̌��ʃp�����[�^��errorID������΁A<BR>
     * �擪�̈�ڂ�errorID��WEB�V�G���[�R�[�h�ɕϊ����A�ԋp����B <BR>
     * ����ȊO�̏ꍇ�A"00000000"��ԋp����<BR>
     * @@param l_strsendURLMessageResult - (sendURL���b�Z�[�W�̌���)<BR>
     * sendURL���b�Z�[�W�̌���<BR>
     * @@return String
     */
    protected String getURLSimplexResultCode(String l_strSendURLMessageResult)
    {
        final String STR_METHOD_NAME = "getURLSimplexResultCode(String)";
        log.entering (STR_METHOD_NAME );
        //�P�jsendURL���b�Z�[�W�̌��ʃp�����[�^��errorID������΁AconnectResult�ɐ擪�̈�ڂ�errorID���Z�b�g
        String[] l_strURLMessages = l_strSendURLMessageResult.split("<errorId>");
        if (l_strURLMessages != null && l_strURLMessages.length > 1)
        {
            String[] l_strMessages = l_strURLMessages[1].split("</errorId>");
            connectionResultDetails.put(CONNECT_RESULT, l_strMessages[0].trim());
            log.exiting(STR_METHOD_NAME);
            return this.changeErrorCode(l_strMessages[0].trim());
        }
        connectionResultDetails.put(CONNECT_RESULT, "0");
        log.exiting(STR_METHOD_NAME);
        return WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
    }

    /**
     * Simplex�ڑ��̃V�X�e���ֈ˗��d���̑��t���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �usendMessage(Simplex)�v���Q�Ƃ���<BR>
     * @@param l_message - (GFT�d�����b�Z�[�W)<BR>
     * GFT�d�����b�Z�[�W<BR>
     * @@param l_prefRpcParams - (�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`��)<BR>
     * �O���V�X�e��SOAP�v���t�@@�����X�iRPC�`��)<BR>
     * @@throws WEB3BaseException
     */
    public void sendMessage(
        Message l_message, SoapConnectPrefRpcParams l_prefRpcParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sendMessage(Message, SoapConnectPrefRpcParams)";
        log.entering(STR_METHOD_NAME);

        String l_strEndpointName;

        if (l_message == null || l_prefRpcParams == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        if (!(l_message instanceof WEB3FXGftAskingTelegramUnit))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
        //createSimplex�˗��d������
        WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit =
            this.createSimplexAskingTelegramUnit(l_message);

        //createHash(Simplex�˗��d������ : Simplex�˗��d������, �I�y���[�V������ : String)
        WEB3FXSimplexAskingTelegramUnit l_fxSimplexAskingTelegramUnit =
            this.createHash(l_simplexAskingTelegramUnit, l_prefRpcParams.getOperationName());

        //createURL(�G���h�|�C���g�� : String, Simplex�˗��d������ : Simplex�˗��d������)
        String[] l_strEndPointNames = l_prefRpcParams.getEndpointName().split(";");

        //����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j.�G���h�|�C���g���̕����� == 3�̏ꍇ
        if (l_strEndPointNames.length == 3)
        {
        	//����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j.�G���h�|�C���g���̔z���3�v�f
        	l_strEndpointName = l_strEndPointNames[2];
        }

        //����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j.�G���h�|�C���g���̕����� == 1�̏ꍇ
        else if (l_strEndPointNames.length == 1)
        {
        	//����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j.�G���h�|�C���g���̔z���1�v�f
        	l_strEndpointName = l_strEndPointNames[0];
        }

        //����L�̏����ȊO�̏ꍇ�A�O���V�X�e���ڑ��G���[���X���[����
        else
        {
            //����L�̏����ȊO�̏ꍇ�A�O���V�X�e���ڑ��G���[���X���[����
            log.debug("�O���V�X�e���ڑ��G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O���V�X�e���ڑ��G���[�B");
        }

        String l_strURL = this.createURL(l_strEndpointName, l_fxSimplexAskingTelegramUnit);

        //saveSOAP���b�Z�[�W(���XID : long, �ڑ��敪 : string, ����M�敪 : string, ���b�Z�[�W : string)
        //[����]
        //�@@���XID�F����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j.���XID
        //�@@�ڑ��敪�F����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j.�ڑ��敪
        //�@@����M�敪�F0�F���M
        //�@@���b�Z�[�W�F�쐬����URL
        this.saveSOAPMessage(
            l_prefRpcParams.getBranchId(), l_prefRpcParams.getConnectDiv(), WEB3AioFXSendRecieveDivDef.SEND, l_strURL);

        String l_strURLMessage = null;
        String l_strSimplexResultCode = null;
        try
        {
            //sendURL���b�Z�[�W(�ڑ�URL : string)
            l_strURLMessage = this.sendURLMessage(l_strURL);
            
            //getURLSimplex��t���ʃR�[�h(sendURL���b�Z�[�W�̌��� : string)
            l_strSimplexResultCode = this.getURLSimplexResultCode(l_strURLMessage);
        }
        catch (WEB3BaseException l_ex)
        {
            if (WEB3ErrorCatalog.BUSINESS_ERROR_02398.equals(l_ex.getErrorInfo()))
            {
                //�hGFT�ڑ��G���[�h(00000990)�A
                l_strSimplexResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990;
                // �h�ڑ��G���[�i�V�X�e���G���[�j�h(9991)
                this.connectionResultDetails.put(CONNECT_RESULT, WEB3GftSoapResultCodeDef.CONNECT_ERROR);
            }
            else
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getErrorMessage(),
                    l_ex);
            }
        }

        //saveSOAP���b�Z�[�W(���XID : long, �ڑ��敪 : string, ����M�敪 : string, ���b�Z�[�W : string)
        //[����]
        //�@@���XID�F����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j.���XID
        //�@@�ڑ��敪�F����.�O���V�X�e��SOAP�v���t�@@�����X�iRPC�`���j.�ڑ��敪
        //�@@����M�敪�F1�F��M 
        //�@@���b�Z�[�W�F�ԋp����Xml
        this.saveSOAPMessage(
            l_prefRpcParams.getBranchId(),
            l_prefRpcParams.getConnectDiv(),
            WEB3AioFXSendRecieveDivDef.RECEIVE,
            l_strURLMessage);

        //resultCode��ݒ肷��
        //this.�ڑ����ʖ��ׂɂ́A�Ώۂ�ǉ�����B
        //�@@key:�@@�O���ڑ�.RESULT_CODE
        //�@@value:�@@�擾������t���ʃR�[�h
        this.connectionResultDetails.put(RESULT_CODE, l_strSimplexResultCode);

        WEB3FXGftAskingTelegramUnit l_gftAskingTelegramUnit = (WEB3FXGftAskingTelegramUnit)l_message;
        String l_strOperationDiv = l_gftAskingTelegramUnit.gftOperationDiv;
        //��t���ʃR�[�h = 00000000(��������)�̏ꍇ
        if (WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000000.equals(l_strSimplexResultCode))
        {
            //����.GFT�˗��d������.�����敪 = 01�F�����J�݂̏ꍇ
            if (WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN.equals( l_strOperationDiv))
            {
                //fx_acc_01��ݒ肷��
                this.connectionResultDetails.put(FX_ACC_01, new Long("111111"));
            }

            //����.GFT�˗��d������.�����敪 = 07�F�U�։\�z�̏ꍇ
            if (WEB3AdminAioGftOperationDivDef.TRANSFER_ABLE_AMT.equals( l_strOperationDiv))
            {
                //amount��ݒ肷��
                HashMap l_hmAmount = new HashMap();
                String[] l_strURLMessages = l_strURLMessage.split("<withdrawalLimitAmount>");
                if (l_strURLMessages != null && l_strURLMessages.length > 0)
                {
                    String[] l_strMessages = l_strURLMessages[1].split("</withdrawalLimitAmount>");
                    l_hmAmount.put("111111", l_strMessages[0].trim());
                }
                this.connectionResultDetails.put(AMOUNT, l_hmAmount);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
            
    /**
     * Simplex�ڑ��V�X�e���̌��ʒʒm����A�w�肵�����ږ���Value���擾����B <BR>
     * <BR>
     * this.�ڑ����ʖ��ׂ���A�w�肵�����ږ���Key�Ƃ��āAValue��ԋp����B<BR>
     * @@param l_strName - (Simplex���ږ�)<BR>
     * Simplex���ږ�<BR>
     * @@return Object
     */
    public Object getResult(String l_strName)
    {
        return this.connectionResultDetails.get(l_strName);
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �ڑ����ʖ��ׂ�����������B<BR>
     */
    public WEB3FXSimplexConnectionSystem()
    {
        this.connectionResultDetails = new HashMap();
    }

    /**
     * WEB�V�G���[�R�[  �h�ɕϊ�����B<BR>
     * @@param l_strErrorID - (errorID)<BR>
     * @@return String
     */
    private String changeErrorCode(String l_strErrorID)
    {
        final String STR_METHOD_NAME = "changeErrorCode(String)";
        log.entering (STR_METHOD_NAME );
        String l_strWeb3ErrorCode = "";
        
        if ("MI-05201".equals(l_strErrorID)
            || "MI-05202".equals(l_strErrorID)
            || "MI-05203".equals(l_strErrorID)
            || "MI-05204".equals(l_strErrorID)
            || "MI-05205".equals(l_strErrorID)
            || "MI-05206".equals(l_strErrorID)
            || "MI-05207".equals(l_strErrorID)
            || "MI-05208".equals(l_strErrorID)
            || "MI-05209".equals(l_strErrorID)
            || "MI-05210".equals(l_strErrorID)
            || "MI-05211".equals(l_strErrorID)
            || "MI-05212".equals(l_strErrorID)
            || "MI-05213".equals(l_strErrorID)
            || "MI-05216".equals(l_strErrorID)
            || "MI-05217".equals(l_strErrorID)
            || "MI-03402".equals(l_strErrorID)
            || "MI-03406".equals(l_strErrorID)
            || "MI-03407".equals(l_strErrorID)
            || "MI-03401".equals(l_strErrorID)
            || "MI-03403".equals(l_strErrorID)
            || "MI-03404".equals(l_strErrorID)
            || "MI-03405".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000602;
        }
        else if ("MI-05226".equals(l_strErrorID)
            || "MI-03423".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000909;
        }
        else if ("MI-05220".equals(l_strErrorID)
            || "MI-03410".equals(l_strErrorID)
            || "MI-03420".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105;
        }
        else if ("MI-05221".equals(l_strErrorID)
            || "MI-03413".equals(l_strErrorID)
            || "MI-03415".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801;
        }
        else if ("MI-03411".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501;
        }
        else if ("MI-03412".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000299;
        }
        else if ("MI-03414".equals(l_strErrorID))
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204;
        }        
        else
        {
            l_strWeb3ErrorCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000901;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strWeb3ErrorCode;
    }

    /* �ؖ����G���[���폜����B
     * */ 
    private void registerMyHostnameVerifier()
    {
        javax.net.ssl.HostnameVerifier myHv = new javax.net.ssl.HostnameVerifier()
        {
           public boolean verify(String hostName,javax.net.ssl.SSLSession session)
           {
                    return true;
           }
        };
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(myHv);
    }
}
@
