head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferOrderUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֒����A�b�v���[�h�T�[�r�XImpl(WEB3AdminFXTransferOrderUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/27 �A����(���u) �V�K�쐬
                 : 2006/03/07 �A���� (���u) �d�l�ύX�E���f��513
                 : 2006/04/10 �R��(SRA)�@@�d�l�ύX�E���f��525          
Revesion History : 2008/09/05 ���u�� (���u) �d�l�ύX�E���f��970,971,972
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.define.WEB3UploadStateDef;
import webbroker3.aio.message.WEB3AdminFXDuplicateOrderUnit;
import webbroker3.aio.message.WEB3AdminFXErrorOrderUnit;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCancelRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCancelResponse;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXTransferUploadConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXTransferUploadInputRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadInputResponse;
import webbroker3.aio.message.WEB3FXUploadHistoryUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferOrderUnitService;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferOrderUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�U�֒����A�b�v���[�h�T�[�r�XImpl)<BR>
 * FX�U�֒����A�b�v���[�h�T�[�r�X�����N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXTransferOrderUploadServiceImpl implements WEB3AdminFXTransferOrderUploadService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderUploadServiceImpl.class);

   /**
    * @@roseuid 43FC2ED7035B
    */
   public WEB3AdminFXTransferOrderUploadServiceImpl() 
   {
    
   }
   
   /**
    * FX�U�֒����A�b�v���[�h�T�[�r�X�������s���B<BR>
    * <BR>
    * ���N�G�X�g�̌^�ɂ���āA<BR>
    * <BR>
    *   get�A�b�v���[�h���()<BR>
    *   validate�A�b�v���[�h�t�@@�C��()<BR>
    *   submit�A�b�v���[�h()<BR>
    *   undo�A�b�v���[�h()<BR>
    * <BR>
    * �̃��\�b�h���R�[������B<BR>
    * <BR>
    * @@param l_request - (���N�G�X�g�f�[�^)<BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C603C50063
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
       log.entering(STR_METHOD_NAME);

       if (l_request == null)
       {
           log.debug("�p�����[�^�l�s���B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + STR_METHOD_NAME, 
               "�p�����[�^�l�s���B");
       }

       WEB3GenResponse l_response = null;
       if (l_request instanceof WEB3AdminFXTransferUploadInputRequest)
       {
           l_response = this.getUploadScreen((WEB3AdminFXTransferUploadInputRequest) l_request);
       }
       else if (l_request instanceof WEB3AdminFXTransferUploadConfirmRequest)
       {
           l_response = this.validateUploadFile((WEB3AdminFXTransferUploadConfirmRequest) l_request);
       }
       else if (l_request instanceof WEB3AdminFXTransferUploadCompleteRequest)
       {
           l_response = this.submitUpload((WEB3AdminFXTransferUploadCompleteRequest) l_request);
       }
       else if (l_request instanceof WEB3AdminFXTransferUploadCancelRequest)
       {
           l_response = this.undoUpload((WEB3AdminFXTransferUploadCancelRequest) l_request);
       }
       else
       {
           log.debug("�p�����[�^�^�C�v�s���B");
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
    * �V�[�P���X�}<BR>
    * �u�ב֕ۏ؋��iFX�U�֒����t�k�jget�A�b�v���[�h��ʁv �Q��<BR>
    * @@param l_request - (���N�G�X�g�f�[�^)<BR>
    * @@return WEB3AdminFXTransferUploadInputResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C6051601EF
    */
   protected WEB3AdminFXTransferUploadInputResponse getUploadScreen(WEB3AdminFXTransferUploadInputRequest l_request)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminFXTransferUploadInputRequest)";
       log.entering(STR_METHOD_NAME);
       
       //1.1 getInstanceFrom���O�C�����( )
       WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
       //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
       l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE, true);
       
       //1.3 validate������t�\( )
       WEB3GentradeTradingTimeManagement.validateOrderAccept();
       
       //1.4 FX�U�֒����A�b�v���[�hCSV( )
       WEB3AdminFXTransferOrderUploadCsv l_orderUploadCsv = new WEB3AdminFXTransferOrderUploadCsv();
       
       //1.5 get�ŐV�A�b�v���[�h����(�f�[�^�L�[ : long)
       AdministratorUploadRow l_row = 
           l_orderUploadCsv.getLatestUploadAction(l_admin.getInstitution().getInstitutionId());
       
       //1.6 �A�b�v���[�h���������݂���ꍇ�������{
       WEB3FXUploadHistoryUnit l_unit = null;
       if (l_row != null)
       {
           //1.6.1  FX�A�b�v���[�h���𖾍�( )
           l_unit = new WEB3FXUploadHistoryUnit();
           
           //1.6.2 �i*1�j�v���p�e�B�Z�b�g
           //�i*1�jFX�A�b�v���[�h���𖾍׃��b�Z�[�W�I�u�W�F�N�g�v���p�e�B�Ɉȉ��̒l���Z�b�g����B
           //�A�b�v���[�h������ԋ敪�F
           //�@@�i�A�b�v���[�h�s.�A�b�v���[�h�I������ == null�̏ꍇ�j�@@�h�A�b�v���[�h���h
           //�@@�i�A�b�v���[�h�s.�A�b�v���[�h�I������ != null�̏ꍇ�j�@@�h�A�b�v���[�h�ρh
           if (l_row.getUploadEndTimestamp() == null)
           {
               l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOADING;
           }
           else
           {
               l_unit.uploadStateDiv = WEB3UploadStateDef.UPLOAD_COMPLETE;
           }
           
           //�A�b�v���[�h�����F�@@�A�b�v���[�h�s.�A�b�v���[�h����
           l_unit.uploadNumber = String.valueOf(l_row.getUploadCount());
           //�A�b�v���[�h�J�n�����F�@@�A�b�v���[�h�s.�A�b�v���[�h�J�n����
           l_unit.uploadStartDate = l_row.getUploadStartTimestamp();
           //�A�b�v���[�h�I�������F�@@�A�b�v���[�h�s.�A�b�v���[�h�I������
           l_unit.uploadEndDate = l_row.getUploadEndTimestamp();
           //FX�G���[�ԍ��F�@@�A�b�v���[�h�s.���b�Z�[�W�R�[�h
           l_unit.fxErrorId = l_row.getMessageCode();
       }
       
       //1.7 createResponse( )
       WEB3AdminFXTransferUploadInputResponse l_response = 
           (WEB3AdminFXTransferUploadInputResponse) l_request.createResponse();
       
       //1.8�i*2�j�v���p�e�B�Z�b�g
       //�i*2�j���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�A�l���Z�b�g����B
       //�A�b�v���[�h�����ꗗ�F
       //�@@�A�b�v���[�h���������݂���ꍇ�AFX�U�֒����A�b�v���[�h���𖾍׃I�u�W�F�N�g�B
       //�@@�A�b�v���[�h���������݂��Ȃ��ꍇ�Anull�B
       l_response.uploadHistoryUnit = l_unit;
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (validate�A�b�v���[�h�t�@@�C��)<BR>
    * �A�b�v���[�h�m�F�������s���B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u�ב֕ۏ؋��iFX�U�֒����t�k�jvalidate�A�b�v���[�h�t�@@�C���v �Q��<BR>
    * @@param l_request - (���N�G�X�g�f�[�^)<BR>
    * @@return WEB3AdminFXTransferUploadConfirmResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C6064C0223
    */
   protected WEB3AdminFXTransferUploadConfirmResponse validateUploadFile(
       WEB3AdminFXTransferUploadConfirmRequest l_request) throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminFXTransferUploadConfirmRequest)";
       log.entering(STR_METHOD_NAME);

       //1.1 validate( )
       l_request.validate();
       
       //1.2 getInstanceFrom���O�C�����( )
       WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
       //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
       l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE, true);
       
       //1.4 validate������t�\( )
       WEB3GentradeTradingTimeManagement.validateOrderAccept();
       
       //1.5 FX�U�֒����A�b�v���[�hCSV( )
       WEB3AdminFXTransferOrderUploadCsv l_orderUploadCsv = new WEB3AdminFXTransferOrderUploadCsv();
       
       //1.6 validate�����A�b�v���[�h(�A�b�v���[�h�h�c : long)
       l_orderUploadCsv.validateSameTimeUpload(null);
       
       //1.7 get�Ǘ��҃R�[�h( )
       String l_strAdminCode = l_admin.getAdministratorCode();
       
       //1.8 get������( )
       Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
       
       //1.9 ArrayList( )
       ArrayList l_lisDuplicateOrders = new ArrayList();
       
       //1.10 ArrayList( )
       ArrayList l_lisErrorOrders = new ArrayList();
       
       //1.11 save�A�b�v���[�h�J�n(�f�[�^�L�[ : long, 
       //���l�P : String, ���l�Q : String, 
       //���l�R : String, �X�V�҃R�[�h : String)
       l_orderUploadCsv.saveUpLoadStart(
           l_admin.getInstitution().getInstitutionId(), 
           null, 
           null, 
           null, 
           l_strAdminCode);
       
       //1.12 ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�̊e�v�f����LOOP����
       int l_intUploadFilelength = l_request.uploadFile.length;
       int l_intLineNumber = 0;
       for (int i = 0; i < l_intUploadFilelength; i++)
       {
           try
           {
               //1.12.1 add���׍s(���׍s������ : String)
               l_intLineNumber = l_orderUploadCsv.addRow(l_request.uploadFile[i]);
           }
           //1.12.2 add���׍s()�ŗ�O���X���[���ꂽ�ꍇ
           catch (WEB3SystemLayerException l_ex)
           {
               //1.12.2.1 save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
               l_orderUploadCsv.saveUploadError(l_ex.getErrorInfo());
               
               //1.12.2.2 catch������O����ʂɍăX���[����
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00992,
                       this.getClass().getName() + STR_METHOD_NAME,
                       l_request.uploadFile[i]);
           }
           
           //1.12.3 ��s�̏ꍇ�iadd���׍s()�̖߂�l < 0�j�A
           //���Y�v�f�̏����𒆒f�icontinue;�j
           if (l_intLineNumber < 0)
           {
               continue;
           }
           
           //1.12.4 validate���׍s(int, String)
           try
           {
               l_orderUploadCsv.validateDetailsLine(l_intLineNumber,l_request.uploadFile[i]);
           }
           //1.12.5 validate���׍s()�ŗ�O���X���[���ꂽ�ꍇ
           catch (WEB3BaseException l_ex)
           {
               //���p�҃R�[�h
               String l_strUserCode = l_orderUploadCsv.getUserCode(l_intLineNumber);
               //���o���ԍ�
               String l_strCashInOutdNumber = l_orderUploadCsv.getCashInOutNumber(l_intLineNumber);
      
               //1.12.5.1 save�A�b�v���[�h�G���[(�G���[��� : ErrorInfo)
               l_orderUploadCsv.saveUploadError(l_ex.getErrorInfo());
               
               //1.12.5.2 catch������O����ʂɍăX���[����
               log.error(l_ex.getErrorMessage(), l_ex);
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   l_ex.getErrorInfo(),
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_strUserCode + "," + l_strCashInOutdNumber,
                   l_ex);
           }
           
           try
           {
               //1.12.6 validate�d������(int)
               l_orderUploadCsv.validateDuplicateOrder(l_intLineNumber);
           }
           //1.12.7 validate�d������()�ŗ�O���X���[���ꂽ�ꍇ�A
           //�ǉ������s���폜�����Y�v�f�̏����𒆒f�icontinue;�j
           catch (WEB3BaseException l_ex)
           {
               //1.12.7.1 �Ǘ��ҁEFX�U�֏d������( )
               WEB3AdminFXDuplicateOrderUnit l_duplicateOrderUnit = new WEB3AdminFXDuplicateOrderUnit();
               
               //1.12.7.2 �v���p�e�B�Z�b�g
               //���p�҃R�[�h
               l_duplicateOrderUnit.userCode = l_orderUploadCsv.getUserCode(l_intLineNumber);
               //���o���ԍ�
               l_duplicateOrderUnit.cashInoutNumber = l_orderUploadCsv.getCashInOutNumber(l_intLineNumber);
               
               //1.12.7.3 add(arg0 : Object)
               l_lisDuplicateOrders.add(l_duplicateOrderUnit);
               
               //1.12.7.4 delete���׍s(�s�ԍ� : int)
               l_orderUploadCsv.deleteRow(l_intLineNumber);
               
               continue;
           }
           
           //1.12.8 get�ڋq(int)
           WEB3GentradeMainAccount l_mainAccount = l_orderUploadCsv.getMainAccount(l_intLineNumber);
           SubAccount l_subAccount = null;
           CompFxConditionParams l_conditionParams = null;
           try
           {
               //1.12.9 getSubAccount(arg0 : SubAccountTypeEnum)
               l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
               
               //1.12.10 get��Е�FX�V�X�e������(�،���ЃR�[�h : String, 
               //���X�R�[�h : String)
               WEB3FXDataControlService l_fxDataControlService = 
                   (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
               l_conditionParams = l_fxDataControlService.getCompFxCondition(
                   l_admin.getInstitutionCode(),
                   l_subAccount.getMainAccount().getBranch().getBranchCode());
           }
           catch (NotFoundException l_ex)
           {
               log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
           }
           
           try
           {
               //1.12.11 validateFX�U�։\(�⏕���� : SubAccount, 
               //FX�V�X�e���R�[�h : String)
               FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
               TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
               WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_tradingModule.getOrderManager();
               l_orderManager.validateFXTransferPossible(l_subAccount, l_conditionParams.getFxSystemCode());
               
               //1.12.12 validate�U�։\��(�⏕���� : SubAccount, 
               //������ : Date, �����J�e�S�� : OrderCategEnum)
               l_orderManager.validateTransferPossibleCount(l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);
           }
           //1.12.13 validateFX�U�։\()�Avalidate�U�։\��()��
           //��O���X���[���ꂽ�ꍇ
           catch (WEB3BaseException l_ex)
           {
               //1.12.13.1 �Ǘ��ҁEFX�U�փG���[����( )
               WEB3AdminFXErrorOrderUnit l_errorOrderUnit = new WEB3AdminFXErrorOrderUnit();
               
               //1.12.13.2 �v���p�e�B�Z�b�g
               //���p�҃R�[�h
               l_errorOrderUnit.userCode = l_orderUploadCsv.getUserCode(l_intLineNumber);
               //���o���ԍ�
               l_errorOrderUnit.cashInoutNumber = l_orderUploadCsv.getCashInOutNumber(l_intLineNumber);
               
               //1.12.13.3 add(arg0 : Object)
               l_lisErrorOrders.add(l_errorOrderUnit);
               
               //1.12.13.4 delete���׍s(�s�ԍ� : int)
               l_orderUploadCsv.deleteRow(l_intLineNumber);
           }
       }
       
       //1.13 toArray( )
       WEB3AdminFXDuplicateOrderUnit[] l_duplicateOrderUnits = 
           new WEB3AdminFXDuplicateOrderUnit[l_lisDuplicateOrders.size()];
       l_lisDuplicateOrders.toArray(l_duplicateOrderUnits);
       
       //1.14 toArray( )
       WEB3AdminFXErrorOrderUnit[] l_errorOrderUnits = new WEB3AdminFXErrorOrderUnit[l_lisErrorOrders.size()];
       l_lisErrorOrders.toArray(l_errorOrderUnits);
       
       //1.15 get���׍s��( )
       int l_intRowCount = l_orderUploadCsv.getRowCount();
       
       //1.16 get�A�b�v���[�h�h�c( )
       long l_lngUploadId = l_orderUploadCsv.getAdministratorUploadId();
       
       //1.17 save�A�b�v���[�hTemp( )
       l_orderUploadCsv.saveUploadTemp();
       
       //1.18 createResponse( )
       WEB3AdminFXTransferUploadConfirmResponse l_response = 
           (WEB3AdminFXTransferUploadConfirmResponse) l_request.createResponse();
       
       //1.19 (*)�v���p�e�B�Z�b�g
       //�i*�j���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�A�l���Z�b�g����B
       //�A�b�v���[�h�����F�@@get���׍s��()�̖߂�l
       l_response.uploadNumber = String.valueOf(l_intRowCount);
       //�A�b�v���[�hID�F�@@get�A�b�v���[�hID()�̖߂�l
       l_response.uploadId = String.valueOf(l_lngUploadId);
       //FX�U�֏d�������F�@@FX�U�֏d������.toArray()
       l_response.duplicateOrderList = l_duplicateOrderUnits;
       //FX�U�փG���[�����F�@@FX�U�փG���[����.toArray()
       l_response.errorOrderList = l_errorOrderUnits;
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (submit�A�b�v���[�h)<BR>
    * �A�b�v���[�h�����������s���B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u�ב֕ۏ؋��iFX�U�֒����t�k�jsubmit�A�b�v���[�h�t�@@�C���v �Q��<BR>
    * @@param l_request - (���N�G�X�g�f�[�^)<BR>
    * @@return WEB3AdminFXTransferUploadCompleteResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C6071301CF
    */
   protected WEB3AdminFXTransferUploadCompleteResponse submitUpload(WEB3AdminFXTransferUploadCompleteRequest l_request)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " submitUpload(WEB3AdminFXTransferUploadCompleteRequest)";
       log.entering(STR_METHOD_NAME);
       
       //1.1 validate( )
       l_request.validate();
       
       //1.2 getInstanceFrom���O�C�����( )
       WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
       //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
       l_admin.validateAuthority(WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE, true);
       
       //1.4 validate����p�X���[�h(�p�X���[�h : String)
       l_admin.validateTradingPassword(l_request.password);              
       
       //1.5 validate������t�\( )
       WEB3GentradeTradingTimeManagement.validateOrderAccept();

       //1.6 FX�U�֒����A�b�v���[�hCSV( )
       WEB3AdminFXTransferOrderUploadCsv l_orderUploadCsv = new WEB3AdminFXTransferOrderUploadCsv();
       
       //1.7 validate�����A�b�v���[�h(�A�b�v���[�h�h�c : long)
       l_orderUploadCsv.validateSameTimeUpload(Long.valueOf(l_request.uploadId));
       
       //1.8 get�Ǘ��҃R�[�h( )
       String l_strAdminCode = l_admin.getAdministratorCode();
       
       //1.9 get�،����( )
       Institution l_institution = l_admin.getInstitution();
       
       //1.10 setDataFrom�A�b�v���[�hTemp(�A�b�v���[�h�h�c : long)
       l_orderUploadCsv.setDataFromUploadTemp(Long.parseLong(l_request.uploadId));
       
       //1.11 get���׍s��( )
       int l_intRowCount = l_orderUploadCsv.getRowCount();
       
       //1.12 ArrayList( )
       ArrayList l_lisErrors = new ArrayList();
       
       //1.13 ���׍s�̐���Loop����
       WEB3AdminFXTransferOrderUnitService l_service = 
           (WEB3AdminFXTransferOrderUnitService) Services.getService(WEB3AdminFXTransferOrderUnitService.class);
       
       for (int i = 0; i < l_intRowCount; i++)
       {
           try
           {
               //1.13.1 execute(FX�U�֒����A�b�v���[�hCSV, int, String, 
               //Institution, String)
               l_service.execute(l_orderUploadCsv, i, l_strAdminCode, l_institution, l_request.password);
           }
           //1.13.2 execute�ŗ�O���X���[���ꂽ�ꍇ
           catch (WEB3BaseException l_ex)
           {
               //1.13.2.1 �Ǘ��ҁEFX�U�փG���[����( )
               WEB3AdminFXErrorOrderUnit l_errorOrderUnit = new WEB3AdminFXErrorOrderUnit();
               
               //1.13.2.2 �v���p�e�B�Z�b�g
               //���p�҃R�[�h�F�A�b�v���[�hCSV.get���p�҃R�[�h(index)
               l_errorOrderUnit.userCode = l_orderUploadCsv.getUserCode(i);
               //���o���ԍ��F�A�b�v���[�hCSV.get���o���ԍ��iindex�j
               l_errorOrderUnit.cashInoutNumber = l_orderUploadCsv.getCashInOutNumber(i);
               
               //1.13.2.3 add(arg0 : Object)
               l_lisErrors.add(l_errorOrderUnit);
               
               //1.13.2.4 delete���׍s(�s�ԍ� : int)
               l_orderUploadCsv.deleteRow(i);
               
               //1.13.2.5 ���[�v��index�ƃ��[�v���i���׍s���j��1�}�C�i�X����B
               l_intRowCount--;
               i--;
           }
       }
       
       //1.14 toArray( )
       WEB3AdminFXErrorOrderUnit[] l_errorOrderUnits = new WEB3AdminFXErrorOrderUnit[l_lisErrors.size()];
       l_lisErrors.toArray(l_errorOrderUnits);
       
       //1.15 get���׍s��( )
       l_intRowCount = l_orderUploadCsv.getRowCount();
       
       //1.16 save�A�b�v���[�h�I��( )
       l_orderUploadCsv.saveUploadEnd();
       
       //1.17 delete�A�b�v���[�hTemp( )
       l_orderUploadCsv.deleteUploadTemp();
       
       //1.18 createResponse( )
       WEB3AdminFXTransferUploadCompleteResponse l_response = 
           (WEB3AdminFXTransferUploadCompleteResponse) l_request.createResponse();
       
       //1.19 (*)�v���p�e�B�Z�b�g
       //(*)�ȉ��̂Ƃ���A�v���p�e�B���Z�b�g����B
       //�A�b�v���[�h�����F get���׍s��()�̖߂�l
       l_response.uploadNumber = String.valueOf(l_intRowCount);
       //FX�G���[�����ꗗ�F �Ǘ��ҁEFX�G���[�����̔z
       l_response.errorOrderList = l_errorOrderUnits;
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (undo�A�b�v���[�h)<BR>
    * �A�b�v���[�h���~�������s���B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u�ב֕ۏ؋��iFX�U�֒����t�k�jundo�A�b�v���[�h�v �Q��<BR>
    * @@param l_request - (���N�G�X�g�f�[�^)<BR>
    * @@return WEB3AdminFXTransferUploadCancelResponse
    * @@throws WEB3BaseException
    * @@roseuid 43C607BA0302
    */
   protected WEB3AdminFXTransferUploadCancelResponse undoUpload(WEB3AdminFXTransferUploadCancelRequest l_request) 
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " undoUpload(WEB3AdminFXTransferUploadCancelRequest)";
       log.entering(STR_METHOD_NAME);
       
       //1.1 FX�U�֒����A�b�v���[�hCSV(long)
       WEB3AdminFXTransferOrderUploadCsv l_uploadCsv = 
           new WEB3AdminFXTransferOrderUploadCsv(Long.parseLong(l_request.uploadId));
       
       //1.2 delete�A�b�v���[�hTemp( )
       l_uploadCsv.deleteUploadTemp();
       
       //1.3 save�A�b�v���[�h���~( )
       l_uploadCsv.saveUploadStop();
       
       //1.4 createResponse( )
       WEB3AdminFXTransferUploadCancelResponse l_response = 
           (WEB3AdminFXTransferUploadCancelResponse) l_request.createResponse();
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
}
@
