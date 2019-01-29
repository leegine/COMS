head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ�������T�[�r�XImpl(WEB3AdminBondExecuteCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 �����(���u) �V�K�쐬         
Revesion History : 2007/7/25 ���g (���u) �d�l�ύX�E���f��No.237
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;

import webbroker3.bd.WEB3AdminBondDomesticOrderCancelUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondExecuteCancelUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderCancelAcceptUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderCancelUpdateInterceptor;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteCancelService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3SalesofficeTpcheckDivDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҍ�������T�[�r�XImpl)<BR>
 * �Ǘ��ҐV�K������T�[�r�XImpl�N���X
 * 
 * @@author �����
 * @@version 1.0
 */
public class WEB3AdminBondExecuteCancelServiceImpl 
    implements WEB3AdminBondExecuteCancelService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCancelServiceImpl.class);
  
    /**
     * @@roseuid 44E3362E034B
     */
    public WEB3AdminBondExecuteCancelServiceImpl() 
    {
     
    }
    
    /**
     * ����������������{����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B <BR>
     * <BR>
     * �|validate�����() <BR>
     * �|submit�����()
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6FD3F0341
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        WEB3GenResponse l_response = null;
        
        //�|validate�����()
        if (l_request instanceof WEB3AdminBondExecCancelConfirmRequest)
        {
            l_response = 
                this.validateExecuteCancel(
                    (WEB3AdminBondExecCancelConfirmRequest) l_request);
        }
        
        //�|submit�����()
        else if (l_request instanceof WEB3AdminBondExecCancelCompleteRequest)
        {
            l_response = 
                this.submitExecuteCancel(
                    (WEB3AdminBondExecCancelCompleteRequest) l_request);
        }
        
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�������)<BR>
     * ��������m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�}�uvalidate������v �Q�ƁB
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҍ�������m�F���N�G�X�g�f�[�^
     * @@return WEB3AdminBondExecCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6FD4A03AE
     */
    protected WEB3AdminBondExecCancelConfirmResponse validateExecuteCancel(
        WEB3AdminBondExecCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateExecuteCancel(WEB3AdminBondExecCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N������  
        //[validate����()�Ɏw�肷�����]  
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���i���ύX�A������j  
        //is�X�V�F�@@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4 get�������P��By����ID(long)
        //�������P�ʃI�u�W�F�N�g���擾 
        //[����] 
        //����ID�F���N�G�X�g�f�[�^.����ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.5 get�⏕����(����ID : , �⏕����ID : )
        //�⏕�����I�u�W�F�N�g���擾 
        //[get�⏕����()�̈���] 
        //����ID�Fget�������P��By����ID.get����ID() 
        //�⏕����ID�Fget�������P��By����ID.get�⏕����ID()
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                    l_bondOrderUnit.getAccountId(), 
                    l_bondOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 

        //1.6 validate���X����(���X�R�[�h : String)
        //�Ǘ��҂��w�肵�����X�Ɏ�舵���邩�`�F�b�N 
        //[validate���X����()�̈���] 
        //���X�R�[�h�Fget�⏕����.get����X().get���X�R�[�h()
        l_admin.validateBranchPermission(
            l_subAccount.getWeb3GenBranch().getBranchCode());
        
        //1.7 get������(long)
        //�����������I�u�W�F�N�g���擾 
        //[get������()�̈���] 
        //����ID�F�擾�����������P��.get����ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProduct().getProductId());

        //1.8 validate�Ǘ��Ҏ戵�\����(������)
        //�Ǘ��Ҏ戵�\���`�F�b�N 
        //[����] 
        //�������Fget������
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.9 validate����\���(BondOrderUnit)
        //����\�ł��邩�ǂ����`�F�b�N 
        //[validate����\���()�̈���] 
        //�������P�ʁFget�������P��By����ID
        l_bondOrderManager.validateCancelPossibleStatus(l_bondOrderUnit);
        
        //1.10 get�������敪( )
        //�������敪���擾
        String l_strExecStatus = l_bondOrderUnit.getOrderExecStatus();
        
        //1.11 get�������敪() == ���ς̏ꍇ
        double l_dblOtherTradingPower = 0.0D;
        boolean l_blnFlag = false;
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strExecStatus))
        {
            //1.11.1 is�]�̓`�F�b�N�Ώۖ����(�g���������P��) == true
            if (this.isTpCheckNeedToExecuteCancel(l_bondOrderUnit))
            {
                //1.11.1.1 getDeliveryDate( )
                Date l_datDeliveryDate = l_bondOrderUnit.getDeliveryDate();
                
                //1.11.1.2 get���̑����i���t�\�z(�⏕���� : �⏕����, ��n�� : Date)
                WEB3TPTradingPowerService l_tpTradingPowerService = 
                    (WEB3TPTradingPowerService) Services.getService(
                        WEB3TPTradingPowerService.class);
                l_dblOtherTradingPower =
                    l_tpTradingPowerService.getOtherTradingPower(
                        l_subAccount, 
                        l_datDeliveryDate);
                
                //1.11.1.3 get��n���(�~��)
                double l_dblEstimatedPrice = l_bondOrderUnit.getEstimatedPrice();
                
                //1.11.1.4 get���̑����i���t�\�z�@@���@@get��n���(�~��)�@@�̏ꍇ
                if (l_dblOtherTradingPower < l_dblEstimatedPrice)
                {
                    l_blnFlag = true;
                }       
            }
        }
        
        //1.12 to�ڋq���(�g���������P��)
        //�ڋq���I�u�W�F�N�g���擾 
        //[to�ڋq���()�̈���] 
        //�������P�ʁFget�������P��By����ID
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(WEB3AdminBondHelperService.class);
        WEB3AdminBondAccountInfo l_bondAccountInfo =
            l_helperService.toAccountInfo(l_bondOrderUnit);
        
        //1.13 to�������(������)
        //�������I�u�W�F�N�g���擾 
        //[����] 
        //�������Fget������ 
        WEB3AdminBondProductInfo l_bondProductInfo = 
            l_helperService.toProductInfo(l_bondProduct);
        
        //1.14 to�������(�g���������P��)
        //�������I�u�W�F�N�g���擾 
        //[get�������()�̈���] 
        //�������P�ʁF�擾�����������P�ʃI�u�W�F�N�g
        WEB3AdminBondOrderInfo l_bondOrderInfo = 
            l_helperService.toOrderInfo(l_bondOrderUnit);
        
        //1.15 to�����(�g���������P��)
        //�����I�u�W�F�N�g���擾 
        //[get�����()�̈���] 
        //�������P�ʁF�������P�ʃI�u�W�F�N�g
        WEB3AdminBondOrderExecInfo l_orderExecInfo = 
            l_helperService.toOrderExecInfo(l_bondOrderUnit);
        
        //1.16 create���X�|���X()
        WEB3AdminBondExecCancelConfirmResponse l_response = 
            (WEB3AdminBondExecCancelConfirmResponse)l_request.createResponse();
        
        //1.17 �v���p�e�B�Z�b�g
        //create���X�|���X()�ɂĎ擾�������X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B  
        //�ڋq���=to�ڋq���
        //�������=to������� 
        //�������=to�������
        //�����=to�����
        //�������.�x���敪�ꗗ�ɂ́A�ȉ��ɊY������x���敪��ǉ�����B 
        //�Eget���̑����i���t�\�z�@@���@@get��n���(�~��)�@@�̎���"�]�̓`�F�b�NNG"  
        l_response.accountInfo = l_bondAccountInfo;
        l_response.productInfo = l_bondProductInfo;
        l_response.orderInfo = l_bondOrderInfo;
        if (l_blnFlag)
        {
            l_orderExecInfo.warningDiv = 
                new String[] {WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG};
        }
        l_response.execInfo = l_orderExecInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�������)<BR>
     * ������������������s���B <BR>
     * <BR>
     * �V�[�P���X�}�usubmit������v �Q�ƁB
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҍ�������������N�G�X�g�f�[�^
     * @@return WEB3AdminBondExecCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6FD5200B0
     */
    protected WEB3AdminBondExecCancelCompleteResponse submitExecuteCancel(
        WEB3AdminBondExecCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitExecuteCancel(WEB3AdminBondExecCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N������  
        //[validate����()�Ɏw�肷�����]  
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���i���ύX�A������j  
        //is�X�V�F�@@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4 validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������`�F�b�N���� 
        //[validate����p�X���[�h()�̈���] 
        //�p�X���[�h�F���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        //1.6 get�������P��By����ID(long)
        //�������P�ʃI�u�W�F�N�g���擾 
        //[get�������P��By����ID()�̈���]  
        //����ID�F���N�G�X�g�f�[�^.����ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.7 get�⏕����(����ID : , �⏕����ID : )
        //�⏕�����I�u�W�F�N�g���擾 
        //[get�⏕����()�̈���] 
        //����ID�Fget�������P��By����ID.get����ID() 
        //�⏕����ID�Fget�������P��By����ID.get�⏕����ID()
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                l_bondOrderUnit.getAccountId(), 
                l_bondOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 

        //1.8 validate���X����(���X�R�[�h : String)
        //���Y�Ǘ��҂��A�w��̕��X����舵���邩���`�F�b�N����B 
        //[validate���X����()�̈���] 
        //���X�R�[�h�Fget�⏕����.get����X().get���X�R�[�h()
        l_admin.validateBranchPermission(
            l_subAccount.getWeb3GenBranch().getBranchCode());
        
        //1.9 get������(long)
        //�����������I�u�W�F�N�g���擾 
        //[get������()�̈���] 
        //����ID�Fget�������P��By����ID.get����ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProduct().getProductId());
        
        //1.10 validate�Ǘ��Ҏ戵�\����(������)
        //�Ǘ��Ҏ戵�\���`�F�b�N 
        //[����] 
        //�������Fget������
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.11 validate����\���(BondOrderUnit)
        //������\�ł��邩�ǂ����`�F�b�N���� 
        //[����] 
        //�������P�ʁFget�������P��By����ID
        l_bondOrderManager.validateCancelPossibleStatus(l_bondOrderUnit);
        
        //1.12 get�������敪( )
        //�������敪���擾
        String l_strExecStatus = l_bondOrderUnit.getOrderExecStatus();
        
        //1.13 CancelOrderSpec(arg0 : long)
        //������������e�𐶐� 
        //�����F���N�G�X�g�f�[�^.����ID()
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(Long.parseLong(l_request.id));
        
        //1.14 get�������敪() == ���ς̏ꍇ ���� �������P��.���^�C�v == �O���� �̏ꍇ
        double l_dblOtherTradingPower = 0.0D;
        boolean l_blnFlag = false;
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strExecStatus)
            && BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
        {
            //1.14.1 ���Ǘ��Җ�����X�V�C���^�Z�v�^()
            //�C���^�Z�v�^�𐶐�
            WEB3AdminBondExecuteCancelUpdateInterceptor l_interceptor = 
                new WEB3AdminBondExecuteCancelUpdateInterceptor();
            
            //1.14.2 �v���p�e�B�Z�b�g
            //���������C���^�Z�v�^�ɉ��L�̃v���p�e�B���Z�b�g���� 
            //�������Fget������ 
            //�Ǘ��ҁFgetInstanceFrom���O�C����� 
            //�g���������P�ʁFget�������P��By����ID 
            l_interceptor.setBondProduct(l_bondProduct);
            l_interceptor.setAdministrator(l_admin);
            l_interceptor.setBondOrderUnit(l_bondOrderUnit);
            
            //1.14.3 is�]�̓`�F�b�N�Ώۖ����(�g���������P��) == true
            if (this.isTpCheckNeedToExecuteCancel(l_bondOrderUnit))
            {
                //1.14.3.1 getDeliveryDate( )
                Date l_datDeliveryDate = l_bondOrderUnit.getDeliveryDate();
                
                //1.14.3.2 get���̑����i���t�\�z(�⏕���� : �⏕����, ��n�� : Date)
                l_dblOtherTradingPower =
                    l_tpTradingPowerService.getOtherTradingPower(
                        l_subAccount, 
                        l_datDeliveryDate);
                
                //1.14.3.3 get��n���(�~��)
                double l_dblEstimatedPrice = l_bondOrderUnit.getEstimatedPrice();
                
                //1.14.3.4 get���̑����i���t�\�z�@@���@@get��n���(�~��)�@@�̏ꍇ
                if (l_dblOtherTradingPower < l_dblEstimatedPrice)
                {
                    l_blnFlag = true;
                }            
            }
            
            //1.14.4 undo���(BondOrderUnit, ���Ǘ��҃f�t�H���g�C���^�Z�v�^)
            //����������� 
            //[����]  
            //�������P�ʁF get�������P��By����ID 
            //���Ǘ��҃f�t�H���g�C���^�Z�v�^�F ���Ǘ��Җ�����X�V�C���^�Z�v�^
            WEB3AdminBondExecuteNotifyService l_service = 
                (WEB3AdminBondExecuteNotifyService)Services.getService(WEB3AdminBondExecuteNotifyService.class);
            l_service.undoExecute(
                l_bondOrderUnit, 
                l_interceptor);     
        }

        //1.15�������P��.���^�C�v == �O���� �̏ꍇ
        if (BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
        {
            //���Ǘ��Ғ�������X�V�C���^�Z�v�^( )
            WEB3AdminBondOrderCancelUpdateInterceptor l_orderCancelUpdateInterceptor =
                new WEB3AdminBondOrderCancelUpdateInterceptor();

            //�v���p�e�B�Z�b�g
            //���������C���X�^���X�ɉ��L�̃v���p�e�B���Z�b�g����B
            //�Ǘ��ҁFgetInstanceFrom���O�C�����
            l_orderCancelUpdateInterceptor.setAdministrator(l_admin);

            //setThreadLocalPersistenceEventInterceptor(
            //arg0 : BondOrderManagerPersistenceEventInterceptor)
            //[����]
            //BondOrderManagerPersistenceEventInterceptor�F
            //�������P��.���^�C�v == �O���� �̏ꍇ�A���Ǘ��Ғ�������X�V�C���^�Z�v�^
            l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_orderCancelUpdateInterceptor);
        }
        else
        {
            //�������P��.���^�C�v �� �O����
            // �������Ǘ��Ғ�������X�V�C���^�Z�v�^( )
            WEB3AdminBondDomesticOrderCancelUpdateInterceptor l_adminBondDomesticOrderCancelUpdateInterceptor =
                new WEB3AdminBondDomesticOrderCancelUpdateInterceptor();

            //�v���p�e�B�Z�b�g
            //���������C���X�^���X�ɉ��L�̃v���p�e�B���Z�b�g����B
            //�Ǘ��ҁFgetInstanceFrom���O�C�����
            l_adminBondDomesticOrderCancelUpdateInterceptor.setAdministrator(l_admin);

            //setThreadLocalPersistenceEventInterceptor(
            //arg0 : BondOrderManagerPersistenceEventInterceptor)
            //[����]
            //BondOrderManagerPersistenceEventInterceptor�F
            //�������P��.���^�C�v �� �O���� �̏ꍇ�A�������Ǘ��Ғ�������X�V�C���^�Z�v�^
            l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_adminBondDomesticOrderCancelUpdateInterceptor);
        }

        //1.18 submitCancelOrder(arg0 : SubAccount, arg1 : CancelOrderSpec,
        //arg2 : �_���r���[::java::lang::String, arg3 : boolean)
        //�����������submit���� 
        //[submitCancelOrder�̈���]  
        //�⏕�����Fget�⏕����  
        //����������e�F��������������������e  
        //����p�X���[�h�F 
        //����p�X���[�h�ݒ� == �f����p�X���[�h�g�p�f�̏ꍇ�A 
        //�擾�����⏕����.get�ڋq().getTradingPassword�i�j�̖߂�l�� 
        //WEB3Crypt().decrypt()�ŕ�����������  
        //����p�X���[�h�ݒ� == �fDEFAULT�f�̏ꍇ�A���N�G�X�g�f�[�^.�Ïؔԍ� 
        //is�����R���ȗ��Ftrue
        
        //���O�C���^�C�v�������擾����
        OpLoginSecurityService l_securityService = 
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_adminService = (OpLoginAdminService) Services.getService(OpLoginAdminService.class);
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        String l_strAttribute = (String) l_mapAttributes.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
       
        //����p�X���[�h�ݒ� == �hDEFAULT�h
        String l_strTradingPassword = null;
        if (WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            l_strTradingPassword = l_request.password;
        }
        //����p�X���[�h�ݒ� == �h����p�X���[�h�g�p�h �̏ꍇ
        else if (WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            WEB3Crypt l_web3Crypt = new WEB3Crypt();
            l_strTradingPassword = l_web3Crypt.decrypt(l_subAccount.getMainAccount().getTradingPassword());
        }
        l_bondOrderManager.submitCancelOrder(
            l_subAccount, 
            l_cancelOrderSpec,
            l_strTradingPassword,
            true);

        //get�������P��By����ID
        //[get�������P��By����ID()�̈���]
        //����ID�F���N�G�X�g�f�[�^.����ID
        long l_lngOrderId = Long.parseLong(l_request.id);
        WEB3BondOrderUnit l_bondOrderUnitByOrderId =
            l_bondOrderManager.getBondOrderUnitByOrderId(l_lngOrderId);

        //getOrderStatus( )
        OrderStatusEnum l_orderStatusEnum = l_bondOrderUnitByOrderId.getOrderStatus();

        int l_intOrderStatusEnum = l_orderStatusEnum.intValue();
        String l_strOrderStatusEnum = l_intOrderStatusEnum + "";
        //�擾����������� == ��t�ρi��������j
        if (WEB3OrderStatusDef.ACCEPTED_CANCELORDER.equals(l_strOrderStatusEnum))
        {
            //1.19.1 ���Ǘ��Ғ��������t�X�V�C���^�Z�v�^()
            WEB3AdminBondOrderCancelAcceptUpdateInterceptor l_orderCancelAcceptUpdateInterceptor = 
                new WEB3AdminBondOrderCancelAcceptUpdateInterceptor();
            
            //1.19.2 accept�������(long, ���Ǘ��҃f�t�H���g�C���^�Z�v�^)
            //accept������������s 
            //[accept�������()�̈���] 
            //����ID�F���N�G�X�g�f�[�^.����ID()  
            //���Ǘ��҃f�t�H���g�C���^�Z�v�^ : �����������Ǘ��Ғ��������t�X�V�C���^�Z�v�^
            WEB3AdminBondExecuteNotifyService l_service = 
                (WEB3AdminBondExecuteNotifyService)Services.getService(WEB3AdminBondExecuteNotifyService.class);
            l_service.acceptOrderCancel(Long.parseLong(l_request.id), l_orderCancelAcceptUpdateInterceptor);  
        }
        
        //1.20 �]�͍Čv�Z(�⏕���� : �⏕����)
        l_tpTradingPowerService.reCalcTradingPower(
            (WEB3GentradeSubAccount)l_subAccount);  
              
        //1.21 create���X�|���X()
        //�Ǘ��ҍ�������������X�|���X�𐶐�
        WEB3AdminBondExecCancelCompleteResponse l_response = 
            (WEB3AdminBondExecCancelCompleteResponse)l_request.createResponse();
        
        //1.22 �v���p�e�B�Z�b�g
        //�v���p�e�B���Z�b�g����B   
        //�X�V���ԁF���ݓ��� 
        //���ʔԍ��F���N�G�X�g�f�[�^.����ID 
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        l_response.orderActionId = l_request.id;
  
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (is�]�̓`�F�b�N�Ώۖ����)<BR>
     * �]�̓`�F�b�N�Ώۂ��`�F�b�N����B<BR>
     * <BR>
     * �P�j���L�����𖞂����ꍇ�Atrue��Ԃ��B<BR>
     * �@@�@@����.�������P��.get�������敪() ==�@@����<BR>
     *�@@�@@���@@�������P��.get������� = �����蒍��<BR>
     *�@@�@@ ���@@�������P��.get���ϋ敪�@@= �~�� <BR>
     *�@@�@@���@@���X�p�v���t�@@�����X.�c�ƓX����]�̓`�F�b�N���{�敪-��(*)�@@!= EXECUTE <BR>
     * <BR>
     * �Q�j ��L�ȊO�̏ꍇ�Afalse��Ԃ��B<BR>
     * <BR>
     * (*)�u���X�p�v���t�@@�����X.�c�ƓX����]�̓`�F�b�N���{�敪-���v�̎擾���@@�F <BR>
     * ���X�p�v���t�@@�����X�e�[�u������������B <BR>
     * [��������] <BR>
     * �@@�@@�@@���XID�F�������P��.get���XID <BR>
     *       �v���t�@@�����X���F�c�ƓX����]�̓`�F�b�N���{�敪-�� <BR>
     *       �v���t�@@�����X�A�� = 1 <BR>
     * <BR>
     * @@param l_bondOrderUnit - (�g���������P��)<BR>
     * �g���������P��
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 44BCA43001CA
     */
    protected boolean isTpCheckNeedToExecuteCancel(WEB3BondOrderUnit l_bondOrderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isTpCheckNeedToExecuteCancel(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
     
        if (l_bondOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //(*)�u���X�p�v���t�@@�����X.�c�ƓX����]�̓`�F�b�N���{�敪-���v�̎擾���@@�F 
        //���X�p�v���t�@@�����X�e�[�u������������B 
        //[��������] 
        //���XID�F�������P��.get���XID 
        //�v���t�@@�����X���F�c�ƓX����]�̓`�F�b�N���{�敪-�� 
        //�v���t�@@�����X�A�� = 1 
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_bondOrderUnit.getBranchId(),
                    "bond.salesoffice.tpcheck.div",
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        boolean l_blnValue = false;
        if (l_branchPreferencesRow == null || 
            !WEB3SalesofficeTpcheckDivDef.EXECUTE.equals(l_branchPreferencesRow.getValue()))
        {
            l_blnValue = true;
        }
        
        //�P�j���L�����𖞂����ꍇ�Atrue��Ԃ��B
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()) &&
            OrderTypeEnum.BOND_SELL.equals(l_bondOrderUnit.getOrderType()) && 
            WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(l_bondOrderUnit.getSettlementDiv()) &&
            l_blnValue)
        {           
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
              
    }
}
@
