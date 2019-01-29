head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecuteResultUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO��������茋�ʱ���۰�ރT�[�r�XImpl(WEB3AdminFeqExecuteResultUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 �A�C��(���u) �V�K�쐬
                   2006/09/18 �����(���u) �d�l�ύX�E���f��243  
                   2006/12/21 ���G��(���u) �c�a�X�V�d�l 83  
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2009/08/03 �Ԑi(���u) ���f��No.513
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqExecuteResultInputCSV;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.define.WEB3FeqOpenAtOrderDLDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.define.WEB3FeqRateDivDef;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadStopRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadStopResponse;
import webbroker3.feq.message.WEB3FeqExchangeUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecuteResultUploadService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.slebase.data.SleRcvdQDao;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO��������茋�ʱ���۰�ރT�[�r�XImpl)<BR>
 * �Ǘ��ҊO��������茋�ʱ���۰�ރT�[�r�X�����N���X<BR>
 * <BR>
 * �Ǘ��ҊO�������A�b�v���[�h�T�[�r�X�C���^�Z�v�^��Plugin����B<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqExecuteResultUploadServiceImpl implements WEB3AdminFeqExecuteResultUploadService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqExecuteResultUploadServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F302FD
     */
    public WEB3AdminFeqExecuteResultUploadServiceImpl() 
    {
     
    }
    
    /**
     * �O��������茋�ʃA�b�v���[�h���������{����B<BR>
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
     * @@roseuid 429AE05C01BD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqExecuteResultUploadInputRequest)
        {
            //get�A�b�v���[�h���
            l_response = 
                this.getUploadScreen((WEB3AdminFeqExecuteResultUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecuteResultUploadConfirmRequest)
        {
            //validate�A�b�v���[�h
            l_response = 
                this.validateUpload((WEB3AdminFeqExecuteResultUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecuteResultUploadCompleteRequest)
        {
            //submit�A�b�v���[�h
            l_response = 
                this.submitUpload((WEB3AdminFeqExecuteResultUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecuteResultUploadStopRequest)
        {
            //undo�A�b�v���[�h
            l_response = 
                this.undoUpload((WEB3AdminFeqExecuteResultUploadStopRequest)l_request);
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
     * �V�[�P���X�}�u�i(��)���ꊇ���́jget�A�b�v���[�h��ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������茋�ʃA�b�v���[�h���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42975BB1005D
     */
    protected WEB3AdminFeqExecuteResultUploadInputResponse getUploadScreen(
        WEB3AdminFeqExecuteResultUploadInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminFeqExecuteResultUploadInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
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

        //1.2 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException

        //1.3 createResponse()
        WEB3AdminFeqExecuteResultUploadInputResponse l_response = 
            (WEB3AdminFeqExecuteResultUploadInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�A�b�v���[�h)<BR>
     * �A�b�v���[�h�t�@@�C���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)���ꊇ���́jvalidate�A�b�v���[�h�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������茋�ʃA�b�v���[�h�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42975BBC0271
     */
    protected WEB3AdminFeqExecuteResultUploadConfirmResponse validateUpload(
        WEB3AdminFeqExecuteResultUploadConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpload(WEB3AdminFeqExecuteResultUploadConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
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

        //1.4 �O��������茋�ʈꊇ����CSV( )
        WEB3FeqExecuteResultInputCSV l_feqExecuteResultInputCsv = new WEB3FeqExecuteResultInputCSV();

        //1.5 validate�����A�b�v���[�h(long)
        l_feqExecuteResultInputCsv.validateSameTimeUpload(null);//WEB3BaseException
        
        //1.6 get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.7 save�A�b�v���[�h�J�n(long, String, String, String, String)
        l_feqExecuteResultInputCsv.saveUpLoadStart(
            0,
            null,
            null,
            null,
            l_strAdministratorCode);//WEB3SystemLayerException

        //1.8 ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
        if (l_request.uploadFileList == null || l_request.uploadFileList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�̃A�b�v���[�h�t�@@�C�������w��(null)�ł��B");
        }
        ArrayList l_arrayList = new ArrayList();
        int  l_intFileCount = l_request.uploadFileList.length;
        for (int i = 0; i < l_intFileCount; i++)
        {
            int l_intIndex = 0;
            try
            {
                //1.8.1 add���׍s(String)
                l_intIndex = l_feqExecuteResultInputCsv.addRow(
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
                l_feqExecuteResultInputCsv.saveUploadError(l_ex.getErrorInfo());//WEB3SystemLayerException
                
                //1.8.3.2 �i��O�j
                throw l_ex;
            }
            
            try
            {
                //1.8.4 validate���׍s(int)
                int l_intValidateIndex = l_feqExecuteResultInputCsv.validateDetailLine(
                    l_intIndex, l_strFeqOrderEmpCodeManageDiv);//WEB3BaseException
            
                //1.8.5 �����Ώۍs�łȂ��ꍇ�ivalidate���׍s()�̖߂�l < 0�j
                if (l_intValidateIndex < 0)
                {
                    //1.8.5.1 delete���׍s(�s�ԍ� : int)
                    //QA:WEB3-XBFEQ-A-�eT-0166
                    l_feqExecuteResultInputCsv.deleteRow(l_intIndex);

                    //1.8.5.2 ���Y�v�f�̏����𒆒f�icontinue;�j
                    continue;
                }

                //1.8.6 validate�d���s(int)
                l_feqExecuteResultInputCsv.validateRepeatLine(l_intIndex);//WEB3BaseException
            }
            //1.8.7 validate���׍s()�܂��́Avalidate�d���s()����O���X���[�����ꍇ
            catch (WEB3BaseException l_ex)
            {
                String l_strMessage = l_feqExecuteResultInputCsv.getOrderEmpCode(l_intIndex)
                + ":"
                + l_ex.getErrorInfo().getErrorMessage();
                log.error(l_strMessage, l_ex);
                
                //1.8.7.1 save�A�b�v���[�h�G���[(ErrorInfo)
                l_feqExecuteResultInputCsv.saveUploadError(l_ex.getErrorInfo());//WEB3SystemLayerException
                
                //1.8.7.2 �i��O�j
                if (l_ex instanceof WEB3BusinessLayerException)
                {
                    //1.8.7.2.1 delete���׍s(String)
                    l_feqExecuteResultInputCsv.deleteRow(l_intIndex);
                    l_arrayList.add(l_strMessage);
                    
                }
                else if (l_ex instanceof WEB3SystemLayerException)
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
        int l_intRowCount = l_feqExecuteResultInputCsv.getRowCount();
        
        //1.10 �t�k�Ώۍs���Ȃ��ꍇ�i���׍s�� == 0�j�A��O���X���[����B
        if (l_intRowCount == 0)
        {
            //1.10.1 save�A�b�v���[�h�G���[(ErrorInfo)
            l_feqExecuteResultInputCsv.saveUploadError(WEB3ErrorCatalog.BUSINESS_ERROR_02188);//WEB3SystemLayerException
            
            //1.10.2 �i��O�j
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02188,
                this.getClass().getName() + STR_METHOD_NAME,
                "�t�k�Ώۍs�����݂ł��܂���B");
        }
        
        //1.11 get�A�b�v���[�h�h�c( )
        long l_lngUploadId = l_feqExecuteResultInputCsv.getAdministratorUploadId();
        
        //1.12 save�A�b�v���[�hTemp( )
        l_feqExecuteResultInputCsv.saveUploadTemp();//WEB3SystemLayerException
        
        //1.14 get�ʉ�(String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "�O�������v���_�N�g�}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3GentradeCurrency[] l_genCurrencys = 
            l_productManager.getCurrency(l_strInstitutionCode);//WEB3BaseException
        
        //1.15 get�ב�()�߂�l�̗v�f����LOOP����
        int l_intUnitCount = 0;
        if (l_genCurrencys != null)
        {
            l_intUnitCount = l_genCurrencys.length;
        }
        WEB3FeqExchangeUnit[] l_feqExchangeUnits = new WEB3FeqExchangeUnit[l_intUnitCount];
        for (int i = 0; i < l_intUnitCount; i++)
        {
            WEB3GentradeCurrency l_genCurrency = l_genCurrencys[i];
            GenCurrencyParams l_currencyParmas = (GenCurrencyParams)l_genCurrency.getDataSourceObject();
            //1.15.1 �O�������ב֏��( )
            WEB3FeqExchangeUnit l_feqExchangeUnit = new WEB3FeqExchangeUnit();
        
            //1.15.2 �v���p�e�B�Z�b�g
            //�ʉ݃R�[�h
            l_feqExchangeUnit.currencyCode = l_currencyParmas.getCurrencyCode();
            //���[�g�敪
            l_feqExchangeUnit.rateDiv = WEB3FeqRateDivDef.EXECUTED_EXCHANGE;
            //���t�בփ��[�g
            l_feqExchangeUnit.sellExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getSellExecRate());
            //���t�בփ��[�g
            l_feqExchangeUnit.buyExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getBuyExecRate());
            //�o�^����
            l_feqExchangeUnit.registDatetime = l_currencyParmas.getExecRateUpdateTimestamp();
            
            l_feqExchangeUnits[i] = l_feqExchangeUnit;
        }
        
        //1.16 createResponse()
        WEB3AdminFeqExecuteResultUploadConfirmResponse l_response = 
            (WEB3AdminFeqExecuteResultUploadConfirmResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���X�|���X��null�ł��B");
        }    

        String[] l_strErrInfoList = new String[l_arrayList.size()];
        l_arrayList.toArray(l_strErrInfoList);
        //1.17 �v���p�e�B�Z�b�g
        l_response.uploadNumber = l_intRowCount + "";
        l_response.uploadId = l_lngUploadId + "";
        l_response.feqExchangeUnit = l_feqExchangeUnits;
        l_response.errorInfoList = l_strErrInfoList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�A�b�v���[�h)<BR>
     * �A�b�v���[�h�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)���ꊇ���́jsubmit�A�b�v���[�h�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������茋�ʃA�b�v���[�h�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42975BD40399
     */
    protected WEB3AdminFeqExecuteResultUploadCompleteResponse submitUpload(
        WEB3AdminFeqExecuteResultUploadCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitUpload(WEB3AdminFeqExecuteResultUploadCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();       
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
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);

        //1.5 �O��������茋�ʈꊇ����CSV( )
        WEB3FeqExecuteResultInputCSV l_feqExecuteResultInputCsv = new WEB3FeqExecuteResultInputCSV();

        //1.6 validate�����A�b�v���[�h(long)
        if (WEB3StringTypeUtility.isEmpty(l_request.uploadId))
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
        l_feqExecuteResultInputCsv.validateSameTimeUpload(
            new Long(l_lngUploadId));//WEB3BaseException

        //1.7 setDataFrom�A�b�v���[�hTemp(long)
        l_feqExecuteResultInputCsv.setDataFromUploadTemp(l_lngUploadId);//WEB3SystemLayerException
        
        //1.8 get���׍s��( )
        int l_intRowCount = l_feqExecuteResultInputCsv.getRowCount();
        
        //1.9 ���׍s�̐���LOOP����
        for (int i = 0; i < l_intRowCount; i++)
        {   
            //1.9.1 get�����P��(int)
            WEB3FeqOrderUnit l_orderUnit = l_feqExecuteResultInputCsv.getOrderUnit(i);
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
            
            //�����R�[�h: �O��������茋�ʈꊇ����CSV.get�����R�[�h()�̖߂�l
            l_rcvdQParams.setStockCode(l_feqExecuteResultInputCsv.getProductCode(i));
            
            //�����敪:
            if (WEB3FeqOpenAtOrderDLDef.BUY.equals(l_feqExecuteResultInputCsv.getTrade(i)))
            {
                //�O��������茋�ʈꊇ����CSV.get����()�̖߂�l��"B"�̏ꍇ�́A1:���t
                l_rcvdQParams.setSide(SideEnum.BUY.intValue());
            }
            else if (WEB3FeqOpenAtOrderDLDef.SELL.equals(l_feqExecuteResultInputCsv.getTrade(i)))
            {
                //�O��������茋�ʈꊇ����CSV.get����()�̖߂�l��"S"�̏ꍇ�́A2:���t
                l_rcvdQParams.setSide(SideEnum.SELL.intValue());
            }
            
            //���P��: �O��������茋�ʈꊇ����CSV.get���P��()�̖߂�l
            l_rcvdQParams.setExecPrice(l_feqExecuteResultInputCsv.getExecPrice(i));
            
            //��芔��: �O��������茋�ʈꊇ����CSV.get��芔��()�̖߂�l
            l_rcvdQParams.setExecQty(l_feqExecuteResultInputCsv.getExecQuantity(i));
            
            //������: �O��������茋�ʈꊇ����CSV.get������()�̖߂�l
            l_rcvdQParams.setExecTimestamp(l_feqExecuteResultInputCsv.getExecTimestamp(i));
            
            //�^�p�R�[�h: �O�������P��.�^�p�R�[�h
            l_rcvdQParams.setOrderEmpCode(l_orderUnit.getOrderEmpCode());

            //�ȉ��̒l���E�l�O0���i3���j�ɕҏW���ăZ�b�g
            //�O��������茋�ʈꊇ����CSV.get���No.()�̖߂�l
            String l_strExecNo = l_feqExecuteResultInputCsv.getExecNo(i) + "";
            while (l_strExecNo.length() < 3)
            {
                l_strExecNo = "0" + l_strExecNo;
            }
            l_rcvdQParams.setExecSerialNo(l_strExecNo);
            
            //�o�H�敪: 2:��茋�ʈꊇ����
            l_rcvdQParams.setRouteDiv(WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD);
            
            //�A�J�E���gID: �O�������P��.����ID
            l_rcvdQParams.setAccountId(l_orderUnit.getAccountId());
            
            //�I�y���[�^�^�C�v: 0:�V�K
            l_rcvdQParams.setOpType(SleSendqOpTypeEnum.NEW_ORDER);
            
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
        l_feqExecuteResultInputCsv.saveUploadEnd();//WEB3SystemLayerException
        
        //1.11 delete�A�b�v���[�hTemp( )
        l_feqExecuteResultInputCsv.deleteUploadTemp();//WEB3SystemLayerException
        
        //1.12 createResponse()
        WEB3AdminFeqExecuteResultUploadCompleteResponse l_response = 
            (WEB3AdminFeqExecuteResultUploadCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undo�A�b�v���[�h)<BR>
     * �A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)���ꊇ���́jundo�A�b�v���[�h�v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������茋�ʃA�b�v���[�h���~���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadStopResponse
     * @@throws WEB3BaseException
     * @@roseuid 42975BDC0138
     */
    protected WEB3AdminFeqExecuteResultUploadStopResponse undoUpload(
        WEB3AdminFeqExecuteResultUploadStopRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " undoUpload(WEB3AdminFeqExecuteResultUploadStopRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 �O��������茋�ʈꊇ����CSV(long)
        if (WEB3StringTypeUtility.isEmpty(l_request.uploadId))
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
        WEB3FeqExecuteResultInputCSV l_feqExecuteResultInputCsv = 
            new WEB3FeqExecuteResultInputCSV(l_lngUploadId);
            
        //1.2 delete�A�b�v���[�hTemp( )
        l_feqExecuteResultInputCsv.deleteUploadTemp();//WEB3SystemLayerException 
        
        //1.3 save�A�b�v���[�h���~( )
        l_feqExecuteResultInputCsv.saveUploadStop(); //WEB3SystemLayerException
               
        //1.4 createResponse()
        WEB3AdminFeqExecuteResultUploadStopResponse l_response = 
            (WEB3AdminFeqExecuteResultUploadStopResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
