head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptResultUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�XImpl(WEB3AdminFeqOrderAcceptResultUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �A�C��(���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
                   2006/09/18 �����(���u) �d�l�ύX�E���f��245 
Revesion History : 2009/08/03 �Ԑi(���u) �d�l�ύX�E���f��515,522
*/

package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderAcceptResultUploadCSV;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadStopRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadStopResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptResultUploadService;
import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.slebase.data.SleRcvdQDao;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�XImpl)<BR>
 * �Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X�����N���X<BR>
 * <BR>
 * �Ǘ��ҊO�������A�b�v���[�h�T�[�r�X�C���^�Z�v�^��Plugin����B<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptResultUploadServiceImpl implements WEB3AdminFeqOrderAcceptResultUploadService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptResultUploadServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F1031C
     */
    public WEB3AdminFeqOrderAcceptResultUploadServiceImpl() 
    {
     
    }
    
    /**
     * �O������������t���ʃA�b�v���[�h���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B<BR>
     * <BR>
     * �|get�A�b�v���[�h���()<BR>
     * �|validate�A�b�v���[�h�t�@@�C��()<BR>
     * �|submit�A�b�v���[�h�t�@@�C��()<BR>
     * �|undo�A�b�v���[�h�t�@@�C��()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD3140158
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqOrderAcceptResultUploadInputRequest)
        {
            //get�A�b�v���[�h���()
            l_response = 
                this.getUploadScreen((WEB3AdminFeqOrderAcceptResultUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptResultUploadConfirmRequest)
        {
            //validate�A�b�v���[�h()
            l_response = 
                this.validateUpload((WEB3AdminFeqOrderAcceptResultUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptResultUploadCompleteRequest)
        {
            //submit�A�b�v���[�h()
            l_response = 
                this.submitUpload((WEB3AdminFeqOrderAcceptResultUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptResultUploadStopRequest)
        {
            //undo�A�b�v���[�h()
            l_response = 
                this.undoUpload((WEB3AdminFeqOrderAcceptResultUploadStopRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�A�b�v���[�h���)<BR>
     * �A�b�v���[�h��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)��t�ꊇ���́jget�A�b�v���[�h��ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO������������t���ʃA�b�v���[�h���̓��N�G�X�g���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD3140167
     */
    protected WEB3AdminFeqOrderAcceptResultUploadInputResponse getUploadScreen(
        WEB3AdminFeqOrderAcceptResultUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminFeqOrderAcceptResultUploadInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.3 createResponse( )
        WEB3AdminFeqOrderAcceptResultUploadInputResponse l_response = 
            (WEB3AdminFeqOrderAcceptResultUploadInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�A�b�v���[�h)<BR>
     * �A�b�v���[�h�t�@@�C���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)��t�ꊇ���́jvalidate�A�b�v���[�h�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO������������t���ʃA�b�v���[�h�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD3140169
     */
    protected WEB3AdminFeqOrderAcceptResultUploadConfirmResponse validateUpload(
        WEB3AdminFeqOrderAcceptResultUploadConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpload(WEB3AdminFeqOrderAcceptResultUploadConfirmRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException

        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //�O�������^�p�R�[�h�̔ԋ敪���擾����B
        //[getPREFIX()�Ɏw�肷�����]
        //�،���ЃR�[�h�Fget�،���ЃR�[�h( )�̖߂�l
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService) Services.getService(
            WEB3FeqOrderEmpCodeGettingService.class);

        String l_strFeqOrderEmpCodeManageDiv =
            l_feqOrderEmpCodeGettingService.getPREFIX(l_strInstitutionCode);

        //1.4 �O�������������ʈꊇ����CSV()
        WEB3FeqOrderAcceptResultUploadCSV l_feqOrderAcceptResultUploadCsv = 
            new WEB3FeqOrderAcceptResultUploadCSV();
        
        //1.5 validate�����A�b�v���[�h(long)
        l_feqOrderAcceptResultUploadCsv.validateSameTimeUpload(null);//WEB3BaseException
        
        //1.6 get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();

        //1.7 save�A�b�v���[�h�J�n(long, String, String, String, String)
        l_feqOrderAcceptResultUploadCsv.saveUpLoadStart(
            0,
            null,
            null,
            null,
            l_strAdministratorCode);//WEB3SystemLayerException

        //1.8 ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
        if (l_request.uploadFileList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�̃A�b�v���[�h�t�@@�C�������w��(null)�ł��B");
        }
        int  l_intFileCount = l_request.uploadFileList.length;
        for (int i = 0; i < l_intFileCount; i++)
        {
            int l_intIndex = 0;
            try
            {
                //1.8.1 add���׍s(String)
                l_intIndex = l_feqOrderAcceptResultUploadCsv.addRow(
                    l_request.uploadFileList[i]);//WEB3SystemLayerException
                    
                //1.8.2 ��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A���Y�v�f�̏����𒆒f�icontinue;�j
                if (l_intIndex < 0)
                {
                    continue;
                }
            }
            //1.8.3 add���׍s()����O���X���[�����ꍇ
            catch(WEB3SystemLayerException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                
                //1.8.3.1 save�A�b�v���[�h�G���[(ErrorInfo)
                l_feqOrderAcceptResultUploadCsv.saveUploadError(l_ex.getErrorInfo());//WEB3SystemLayerException
                
                //1.8.3.2 �i��O�j
                throw l_ex;
            }

            try
            {
                // ��t���ς̐V�K�����ȊO�̏ꍇ
                WEB3FeqOrderUnit l_orderUnit =
                    (WEB3FeqOrderUnit)l_feqOrderAcceptResultUploadCsv.getOrderUnit(l_intIndex);
                FeqOrderUnitRow l_orderUnitRow =
                    (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                if (!((l_orderUnit.getOrderStatus().equals(OrderStatusEnum.ORDERING) ||
                        l_orderUnit.getOrderStatus().equals(OrderStatusEnum.MODIFIED))
                        && l_orderUnitRow.getConfirmedQuantity() == 0D))
                {
                    // delete���׍s()
                    l_feqOrderAcceptResultUploadCsv.deleteRow(l_intIndex);
                    continue;
                }

                //validate���׍s(int,String)
            	//�ǉ��������׍s�̃`�F�b�N���s���B
            	//[validate���׍s()�Ɏw�肷�����]
                //�s�ԍ��F�@@�iadd���׍s()�̖߂�l�j
            	//�O�������^�p�R�[�h�̔ԋ敪�F�@@getPREFIX�i�j�̖߂�l
                l_feqOrderAcceptResultUploadCsv.validateDetailLine(
                    l_intIndex,
                    l_strFeqOrderEmpCodeManageDiv);
            }
            //1.8.5 validate���׍s()����O���X���[�����ꍇ
            catch (WEB3BaseException l_ex)
            {
                String l_strMessage = l_ex.getErrorMessage()
                    + l_feqOrderAcceptResultUploadCsv.getEmpCode(i)
                    + "."
                    + l_feqOrderAcceptResultUploadCsv.getProductCode(i);
                log.error(l_strMessage, l_ex);
                
                //1.8.5.1 save�A�b�v���[�h�G���[(ErrorInfo)
                l_feqOrderAcceptResultUploadCsv.saveUploadError(l_ex.getErrorInfo());//WEB3SystemLayerException
                
                //1.8.5.2 �i��O�j
                if (l_ex instanceof WEB3BusinessLayerException)
                {
                    throw new WEB3BusinessLayerException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_strMessage,
                        l_ex);
                }
                if (l_ex instanceof WEB3SystemLayerException)
                {
                    throw new WEB3SystemLayerException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_strMessage,
                        l_ex);
                }
                else
                {
                    throw new WEB3BaseException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_strMessage,
                        l_ex);
                }
            }
        }

        //1.9 get���׍s��( )
        int l_intRowCount = l_feqOrderAcceptResultUploadCsv.getRowCount();

        //1.10 get�A�b�v���[�h�h�c( )
        long l_lngUploadId = l_feqOrderAcceptResultUploadCsv.getAdministratorUploadId();

        //1.11 save�A�b�v���[�hTemp( )
        l_feqOrderAcceptResultUploadCsv.saveUploadTemp();//WEB3SystemLayerException

        //1.12 createResponse( )
        WEB3AdminFeqOrderAcceptResultUploadConfirmResponse l_response = 
            (WEB3AdminFeqOrderAcceptResultUploadConfirmResponse)l_request.createResponse();
            
        //1.13 �v���p�e�B�Z�b�g
        //�A�b�v���[�h����
        l_response.uploadNumber = WEB3StringTypeUtility.formatNumber(l_intRowCount);
        //�A�b�v���[�hID
        l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngUploadId);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�A�b�v���[�h)<BR>
     * �A�b�v���[�h�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)��t�ꊇ���́jsubmit�A�b�v���[�h�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO������������t���ʃA�b�v���[�h�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD314016B
     */
    protected WEB3AdminFeqOrderAcceptResultUploadCompleteResponse submitUpload(
        WEB3AdminFeqOrderAcceptResultUploadCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitUpload(WEB3AdminFeqOrderAcceptResultUploadCompleteRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.4 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        //1.5 �O�������������ʈꊇ����CSV()
        WEB3FeqOrderAcceptResultUploadCSV l_feqOrderAcceptResultUploadCsv = 
            new WEB3FeqOrderAcceptResultUploadCSV();
        
        //1.6 validate�����A�b�v���[�h(long)
        if (l_request.uploadId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�̃A�b�v���[�hID�����w��(null)�ł��B");
        }
        if (!WEB3StringTypeUtility.isDigit(l_request.uploadId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�̃A�b�v���[�hID�^�C�v�s���B");
        }
        long l_lngUploadId = Long.parseLong(l_request.uploadId);
        l_feqOrderAcceptResultUploadCsv.validateSameTimeUpload(
            new Long(l_lngUploadId));//WEB3BaseException
            
        //1.7 setDataFrom�A�b�v���[�hTemp(long)
        l_feqOrderAcceptResultUploadCsv.setDataFromUploadTemp(l_lngUploadId);//WEB3SystemLayerException
            
        //1.8 get���׍s��( )
        int l_intRowCount = l_feqOrderAcceptResultUploadCsv.getRowCount();

        //1.9 ���׍s�̐���LOOP����
        WEB3FeqAcceptUpdateService l_feqAcceptUpdateService = 
            (WEB3FeqAcceptUpdateService )Services.getService(
                WEB3FeqAcceptUpdateService.class);
        if (l_feqAcceptUpdateService == null)
        {
            String l_strMessage = "�O��������t�X�V�T�[�r�X�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);                
        }            
        for (int i = 0; i < l_intRowCount; i++)
        {
            //1.9.1 get�����P��(int)
            WEB3FeqOrderUnit l_orderUnit = l_feqOrderAcceptResultUploadCsv.getOrderUnit(i);//WEB3BaseException
            if (l_orderUnit == null)
            {
                String l_strMessage = "�O�����������P�ʂ����݂��Ȃ��B";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage);
            }
            
            //1.9.2 (������t�o���ʒm�L���[�f�[�^�̓o�^���s�Ȃ�)
            SleRcvdQParams l_rcvdQParams = new SleRcvdQParams();
            try
            {
                //�L���[ID: �����̔Ԃ����l
                l_rcvdQParams.setQueueId(SleRcvdQDao.newPkValue());
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
            
            //�����^�C�v: 4:�O����
            l_rcvdQParams.setXblocksProductType(ProductTypeEnum.FOREIGN_EQUITY);
            
            //����ID: �O�������P��.����ID
            l_rcvdQParams.setInternalRef(l_orderUnit.getOrderId() + "");
            
            //2008/07/07 �[�Z���s�꒼���Ή��@@��Q�Ή�
            //�w�l���i:  �O�������P��.�w�l
            l_rcvdQParams.setPrice(l_orderUnit.getLimitPrice());
            
            //2008/07/07 �[�Z���s�꒼���Ή��@@��Q�Ή�
            //��������: �O�������P��.��������
            l_rcvdQParams.setQuantity(l_orderUnit.getQuantity());
            
            //�^�p�R�[�h: �O�������P��.�^�p�R�[�h
            l_rcvdQParams.setOrderEmpCode(l_orderUnit.getOrderEmpCode());
            
            //�o�H�敪: 5:������t���ʈꊇ����
            l_rcvdQParams.setRouteDiv(WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD);
            
            //�A�J�E���gID: �O�������P��.����ID
            l_rcvdQParams.setAccountId(l_orderUnit.getAccountId());
            
            //�I�y���[�^�^�C�v: 
            String l_strOrderStatus = l_orderUnit.getOrderStatus().intValue() + "";
            if (WEB3OrderStatusDef.MODIFYING.equals(l_strOrderStatus))
            {
                //�O�������P��.�������==2:������(�V�K����)�̏ꍇ�́A0:�V�K
                l_rcvdQParams.setOpType(SleSendqOpTypeEnum.NEW_ORDER);
            }
            else if (WEB3OrderStatusDef.MODIFYED_CHANGEORDER.equals(l_strOrderStatus))
            {
                //�O�������P��.�������==10:������(�ύX����)�̏ꍇ�́A�P:����
                l_rcvdQParams.setOpType(SleSendqOpTypeEnum.CHANGE_ORDER);
            }
            
            //��t�敪: �O�������������ʈꊇ����CSV.get��t�敪()�̖߂�l
            l_rcvdQParams.setAcceptDiv(l_feqOrderAcceptResultUploadCsv.getAcceptDiv(i));
            
            //�،���ЃR�[�h: �O�������P��.�،���ЃR�[�h
            l_rcvdQParams.setInstitutionCode(l_orderUnit.getInstitutionCode());
            
            //���X�R�[�h: �O�������P��.���XID�ɊY�����镔�X.���X�R�[�h
            l_rcvdQParams.setBranchCode(l_orderUnit.getBranchCode());
            
            //���ʃR�[�h: �O�������P��.���ʃR�[�h
            FeqOrderUnitRow l_feqOrderUnitRow = 
                (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
            l_rcvdQParams.setOrderRequestNumber(l_feqOrderUnitRow.getOrderRequestNumber());
            
            //�X�V�҃R�[�h: �Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l
            l_rcvdQParams.setLastUpdater(l_admin.getAdministratorCode());
            
            //�����敪: 0:������
            l_rcvdQParams.setStatus(SleRcvdqProcStatusEnum.TODO); 
            
            //�쐬���t: �o�^����sysdate ���ݒ肳���
            l_rcvdQParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�X�V���t: �o�^����sysdate ���ݒ肳���
            l_rcvdQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_rcvdQParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
        }

        //1.10 save�A�b�v���[�h�I��( )
        l_feqOrderAcceptResultUploadCsv.saveUploadEnd();//WEB3SystemLayerException
        
        //1.11 delete�A�b�v���[�hTemp( )
        l_feqOrderAcceptResultUploadCsv.deleteUploadTemp();//WEB3SystemLayerException
        
        //1.12 createResponse( )
        WEB3AdminFeqOrderAcceptResultUploadCompleteResponse l_response = 
            (WEB3AdminFeqOrderAcceptResultUploadCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undo�A�b�v���[�h)<BR>
     * �A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)��t�ꊇ���́jundo�A�b�v���[�h�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO������������t���ʃA�b�v���[�h���~���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadStopResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD314016D
     */
    protected WEB3AdminFeqOrderAcceptResultUploadStopResponse undoUpload(
        WEB3AdminFeqOrderAcceptResultUploadStopRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " undoUpload(WEB3AdminFeqOrderAcceptResultUploadStopRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        if (l_request.uploadId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�̃A�b�v���[�hID�����w��(null)�ł��B");
        }
        if (!WEB3StringTypeUtility.isDigit(l_request.uploadId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�̃A�b�v���[�hID�^�C�v�s���B");
        }
        long l_lngUploadId = Long.parseLong(l_request.uploadId);

        //1.1 �O�������������ʈꊇ����CSV()
        WEB3FeqOrderAcceptResultUploadCSV l_feqOrderAcceptResultUploadCsv = 
            new WEB3FeqOrderAcceptResultUploadCSV(l_lngUploadId);

        //1.2 delete�A�b�v���[�hTemp( )
        l_feqOrderAcceptResultUploadCsv.deleteUploadTemp();//WEB3SystemLayerException 
        
        //1.3 save�A�b�v���[�h���~( )
        l_feqOrderAcceptResultUploadCsv.saveUploadStop(); //WEB3SystemLayerException

        //1.4 createResponse( )
        WEB3AdminFeqOrderAcceptResultUploadStopResponse l_response = 
            (WEB3AdminFeqOrderAcceptResultUploadStopResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
