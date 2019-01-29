head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֒���UnitServiceImpl(WEB3AdminFXTransferOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/23 �A����(���u) �V�K�쐬
Revesion History : 2007/07/12 ��іQ(���u) ���f��No.733
Revesion History : 2007/07/28 �Ј���(���u) �d�l�ύX���f��744
Revesion History : 2009/03/11 ���u��(���u) �d�l�ύX���f��1115
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXTransferOrderUpdateInterceptor;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferOrderUnitService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioTransferDivDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�U�֒���UnitServiceImpl)<BR>
 * FX�U�֒���UnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B <BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXTransferOrderUnitServiceImpl implements WEB3AdminFXTransferOrderUnitService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderUnitServiceImpl.class);

   /**
    * @@roseuid 43FC2F9D029F
    */
   public WEB3AdminFXTransferOrderUnitServiceImpl() 
   {
    
   }
   
   /**
    * �U�֒����������s���B <BR>
    * <BR>
    * �V�[�P���X�} <BR>
    * �u�ב֕ۏ؋��iFX�U�֒����t�k�j�U�֒����v �Q��<BR>
    * @@param l_fxTransferOrderUploadCsv - (FX�U�֒����A�b�v���[�hCSV)<BR>
    * @@param l_intLineNumber - (�s�ԍ�)<BR>
    * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
    * @@param l_institution - (�،����)<BR>
    * @@param l_strPassword - (�Ïؔԍ�)<BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 43D0AA9D030C
    */
   public WEB3GenResponse execute(
       WEB3AdminFXTransferOrderUploadCsv l_fxTransferOrderUploadCsv,
       int l_intLineNumber,
       String l_strAdministratorCode,
       Institution l_institution,
       String l_strPassword) throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " execute(WEB3AdminFXTransferUploadCsv, int, String, Institution, String)";
       log.entering(STR_METHOD_NAME);
       
       if (l_fxTransferOrderUploadCsv == null || l_institution == null)
       {
           log.debug("�p�����[�^�l�s���B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "," + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
       }
       
       //1.1 get������( )
       Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
       
       //1.2 get�ڋq(int)
       WEB3GentradeMainAccount l_mainAccount = l_fxTransferOrderUploadCsv.getMainAccount(l_intLineNumber);

       //lock����(�،���ЃR�[�h : String, ���X�R�[�h :
       //String, �����R�[�h : String)
       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       WEB3GentradeAccountManager l_accManager =
           (WEB3GentradeAccountManager)l_finApp.getAccountManager();
       String l_strInstitutionCode = l_institution.getInstitutionCode();
       String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
       String l_strAccountCode = l_mainAccount.getAccountCode();
       l_accManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

       //getSubAccount(arg0 : SubAccountTypeEnum)
       SubAccount l_subAccount = null;
       try
       {
          l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
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

       //1.5 get�V�K���ʃR�[�h(�،���ЃR�[�h : String, ���X�R�[�h : 
       //String, �����^�C�v : ProductTypeEnum)
       WEB3HostReqOrderNumberManageService l_hostReqOrderNumMgrService =
           (WEB3HostReqOrderNumberManageService) Services.getService(WEB3HostReqOrderNumberManageService.class); 
       String l_strNewNumber = l_hostReqOrderNumMgrService.getNewNumber(
           l_strInstitutionCode, 
           l_strBranchCode, 
           ProductTypeEnum.CASH);
       
       //1.6 get��Е�FX�V�X�e������(�،���ЃR�[�h : String, ���X�R�[�h : String) 
       WEB3FXDataControlService l_dataControlService = 
           (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
       CompFxConditionParams l_compFxConditionParams = null;
       FxAccountParams l_fxAccountParams = null;
       FxAccountCodeParams l_fxAccountCodeParams = null;
       FxTransferMasterParams l_fxTransferMasterParams = null;
       
       try
       {
           l_compFxConditionParams = 
               l_dataControlService.getCompFxCondition(l_strInstitutionCode, l_strBranchCode);

           //getFX�U�֏����}�X�^(long, String)
           //FX�U�֏����}�X�^Params�擾����B
           //�y�����z
           //FX�V�X�e������ID�@@= ��Е�FX�V�X�e������.FX�V�X�e������ID
           //�U�֋敪 = 0�F����
           l_fxTransferMasterParams =
               l_dataControlService.getFxTransferMasterParams(
                   l_compFxConditionParams.getFxSystemId(),
                   WEB3AioTransferDivDef.CASHIN);

           //1.7 getFX�ڋq(�،���ЃR�[�h : String, ���X�R�[�h :
           //String, FX�V�X�e���R�[�h : String, �ڋq�R�[�h : String)
           l_fxAccountParams = l_dataControlService.getFXAccount(
               l_strInstitutionCode,
               l_strBranchCode,
               l_compFxConditionParams.getFxSystemCode(),
               l_strAccountCode);
           
           //1.8 getFX�����ԍ�(�،���ЃR�[�h : String, 
           //���X�R�[�h : String, �ڋq�R�[�h : String, �R�[�X�敪 : String)
           l_fxAccountCodeParams = l_dataControlService.getFXAccountCode(
               l_strInstitutionCode, 
               l_strBranchCode, 
               l_strAccountCode, 
               WEB3GftTransStatusCourseDivDef.ONE_COSE);
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
       
       //1.9 GFT�˗��d������( )
       WEB3FXGftAskingTelegramUnit l_telUnit = new WEB3FXGftAskingTelegramUnit();
       
       //1.10 �i*�j�v���p�e�B�Z�b�g
       //(*)GFT�˗��d�����ׂɕK�v�ȃv���p�e�B���Z�b�g����
       //�i���L�ȊO�̃v���p�e�B�͐ݒ肵�Ȃ��j
       //DIR��GFT���M���� �F���ݎ����i�V�X�e���^�C���X�^���v�j
       l_telUnit.dirSendTime = 
           WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHHmmss");
       
       //�����敪 �F02(�����j
       l_telUnit.gftOperationDiv = WEB3GftMessageOperationDef.CASH_IN;
       
       //�ב֕ۏ؋������ԍ� �FgetFX�����ԍ�()�̖߂�l
       l_telUnit.fxAccountCode = l_fxAccountCodeParams.getFxAccountCode();
       
       //�������O�C��ID�FFX�ڋqParams.FX���O�C��ID
       l_telUnit.fxFirstLoginId = String.valueOf(l_fxAccountParams.getFxLoginId());
       
       //�S���敪�F��Е�FX�V�X�e������Params.�S���敪
       l_telUnit.groupName = l_compFxConditionParams.getGroupName();
           
       //���o���z �F����.�A�b�v���[�hCSV.get�o���z(����.�s�ԍ�)
       double l_dblCashOutAmt = l_fxTransferOrderUploadCsv.getCashOutAmt(l_intLineNumber);
       l_telUnit.cashinoutAmt = WEB3StringTypeUtility.formatNumber(l_dblCashOutAmt);
       
       //��ЃR�[�h�F����.�،����.�،���ЃR�[�h
       l_telUnit.institutionCode = l_strInstitutionCode;
       
       //���X�R�[�h�F�ڋq����擾�������X�R�[�h
       l_telUnit.branchCode = l_strBranchCode;
                  
       //�ڋq�R�[�h�F�ڋq����擾�����ڋq�R�[�h
       l_telUnit.accountCode = l_strAccountCode;
       
       //���ʃR�[�h�Fget�V�K���ʃR�[�h()�̖߂�l
       l_telUnit.requestNumber = l_strNewNumber;
       
       //1.11 createNewOrderId( )
       WEB3AioOrderManager l_orderManager =
           (WEB3AioOrderManager) l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
       long l_lngOrderId = l_orderManager.createNewOrderId();
       
       //1.12 get���iID(�،���� : Institution)
       long l_lngProductId = l_orderManager.getProductId(l_institution);

       //is�M�p�����J��(�ٍϋ敪 : String)
       //�ٍϋ敪�F�@@"0"�i�w�薳���j
       boolean l_blnIsMarginAccountEstablished =
           l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

       Date l_datPaymentDate = l_fxTransferOrderUploadCsv.getPaymentDate(l_intLineNumber);

       //�Ïؔԍ��F�@@OpLoginSecurityService���A���O�C���^�C�v�������擾���A
       OpLoginSecurityService l_securityService =
           (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
       OpLoginAdminService l_adminService = (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
       LoginInfo l_loginInfo = l_securityService.getLoginInfo();
       Map l_mapAttributes = l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());

       String l_strAttribute = (String)l_mapAttributes.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

       //�E����p�X���[�h�ݒ� == �hDEFAULT�h �̏ꍇ�A����.�Ïؔԍ�
       String l_strTradingPassword = null;
       if (WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
       {
           l_strTradingPassword = l_strPassword;
       }
       //����p�X���[�h�ݒ� == �h����p�X���[�h�g�p�h �̏ꍇ
       else if (WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
       {
           //�ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ�����������
           WEB3Crypt l_web3Crypt = new WEB3Crypt();
           l_strTradingPassword = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());
       }

       //�ڋq���M�p�������J�݂��Ă���iis�M�p�����J��()==TRUE�j�ꍇ�A�������s��
       if (l_blnIsMarginAccountEstablished)
       {
           //������==�o�����̏ꍇ�A�������s��
           //������ == �o�����iget������() == ����.�A�b�v���[�hCSV.get�o����(����.�s�ԍ�)�j�̏ꍇ�A�������s��
           int l_intCompareToDay = WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datPaymentDate);
           if (l_intCompareToDay == 0)
           {
               //submit�ۏ؋��U��(�ڋq, Date, double, String)
               //�ڋq�F�@@get�ڋq()�̖߂�l
               //��n���F�@@����.�A�b�v���[�hCSV.get�o����(����.�s�ԍ�)
               //�����z�F�@@����.�A�b�v���[�hCSV.get���o���z(����.�s�ԍ�)
               //�Ïؔԍ�
               //�㗝���͎ҁF�@@null
               WEB3MarginTransferService l_marginTransferService =
                   (WEB3MarginTransferService)Services.getService(WEB3MarginTransferService.class);

               l_marginTransferService.submitMarginTransfer(
                   l_mainAccount,
                   l_datPaymentDate,
                   l_dblCashOutAmt,
                   l_strTradingPassword,
                   null);
           }
       }

       //1.13 ���o���������e(�㗝���͎� : Trader, ������� : OrderTypeEnum, 
       //�U�փ^�C�v : AssetTransferTypeEnum, ���iID : long, ���z : double, 
       //�L�q : String, �U�֗\��� : Date, ���ϋ@@��ID : String, ����ID : Long,
       //�E�v�R�[�h�F String, �E�v���F String)
       WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
           null,
           l_fxTransferMasterParams.getOrderType(),
           AssetTransferTypeEnum.CASH_IN,
           l_lngProductId,
           l_dblCashOutAmt,
           null,
           l_datPaymentDate,
           null,
           null,
           l_fxTransferMasterParams.getRemarkCode(),
           l_fxTransferMasterParams.getRemarkName());
       
       //1.14 FX�U�֒����X�V�C���^�Z�v�^(���o���������e : ���o���������e)
       WEB3FXTransferOrderUpdateInterceptor l_orderUpdateInterceptor = 
           new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
       
       //1.15 �i*�j�v���p�e�B�Z�b�g
       //(*)�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
       //�������F�@@get������()�̖߂�l
       l_orderUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
       
       //��n���F�@@����.�A�b�v���[�hCSV.get�o����(����.�s�ԍ�)
       l_orderUpdateInterceptor.setDeliveryDate(l_datPaymentDate);
       
       //���ʃR�[�h�F�@@get�V�K���ʃR�[�h()�̖߂�l
       l_orderUpdateInterceptor.setOrderRequestNumber(l_strNewNumber);
       
       //MQ�X�e�[�^�X�F�@@1(���M�ς�)
       l_orderUpdateInterceptor.setMQStatus(WEB3MqStatusDef.MAIL_SENDED);
       
       //1.6 submit�U�֒���(�⏕���� : SubAccount, �����^�C�v : ProductTypeEnum, 
       //������� : OrderTypeEnum, �������e : NewOrderSpec, 
       //�C���^�Z�v�^ : AioOrderManagerPersistenceInterceptor, 
       //����ID : long, �p�X���[�h : String)

       l_orderManager.submitTransferOrder(
           l_subAccount,
           ProductTypeEnum.CASH,
           l_fxTransferMasterParams.getOrderType(),
           l_aioNewOrderSpec,
           l_orderUpdateInterceptor,
           l_lngOrderId,
           l_strTradingPassword);

       //1.17 �]�͍Čv�Z(�⏕���� : �⏕����)
       WEB3TPTradingPowerService l_tradingPowerService = 
           (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
       l_tradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount) l_subAccount);
       
       //1.18 insertGFT�U�֏�(GFT�˗��d������ : GFT�˗��d������, 
       //�R�[�X�敪 : String, ��n�\��� : String, 
       //�M�p�U�֗p���ʃR�[�h : String, ���o���ԍ� : String, ���o���ꗗ����敪: String)
       l_dataControlService.insertGFTTransferStatus(
           l_telUnit, 
           WEB3GftTransStatusCourseDivDef.ONE_COSE,
           WEB3DateUtility.formatDate(l_datPaymentDate, "yyyyMMdd"),
           null,
           l_fxTransferOrderUploadCsv.getCashInOutNumber(l_intLineNumber),
           l_fxTransferMasterParams.getIoListTradeDiv());
       
       log.exiting(STR_METHOD_NAME);
       return null;
   }
}
@
