head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ��ύX�T�[�r�XImpl(WEB3AdminBondExecuteChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����(���u) �V�K�쐬
                   2006/10/12 �����(���u)WEB�V�J���W���̌������̑Ή��inewBigDecimal�����j
                   2006/10/16 �����F (���u) ���f��No.106.108.129
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondChangeOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.WEB3AdminBondExecuteCancelUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondExecuteChangeCommonInterceptor;
import webbroker3.bd.WEB3AdminBondExecuteUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderAcceptUpdateInterceptor;
import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputResponse;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteChangeService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;

/**
 * (�Ǘ��Җ��ύX�T�[�r�XImpl)<BR>
 * �Ǘ��Җ��ύX�T�[�r�XImpl�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteChangeServiceImpl 
    implements WEB3AdminBondExecuteChangeService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteChangeServiceImpl.class);
    
    /**
     * @@roseuid 44E3362F00DA
     */
    public WEB3AdminBondExecuteChangeServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��Җ��ύX�T�[�r�X���������{����B<BR>
     * <BR>
     * ���ύXexecute�������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u���ύXexecute�v�Q�� <BR>
     * --------------------------------------------------<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44CB39E400C0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        WEB3GenResponse l_response = null;
        //1.1.���N�G�X�g�f�[�^�͊Ǘ��Җ��ύX���̓��N�G�X�g�ł���ꍇ
        if (l_request instanceof WEB3AdminBondExecChangeInputRequest)
        {
            //1.1.1.get�Ǘ��Җ��ύX���͉��(�Ǘ��Җ��ύX���̓��N�G�X�g)
            l_response = 
                getExecuteChangeInputScreen((WEB3AdminBondExecChangeInputRequest) l_request);         
        }
        
        //1.2.���N�G�X�g�f�[�^�͊Ǘ��Җ��ύX�m�F���N�G�X�g�ł���ꍇ
        if (l_request instanceof WEB3AdminBondExecChangeConfirmRequest)
        {
            //1.2.1.validate�Ǘ��Җ��ύX(�Ǘ��Җ��ύX�m�F���N�G�X�g)
            l_response = validateExecuteChange(
                (WEB3AdminBondExecChangeConfirmRequest) l_request);         
        }
        
        //1.3.���N�G�X�g�f�[�^�͊Ǘ��Җ��ύX�������N�G�X�g�ł���ꍇ
        if (l_request instanceof WEB3AdminBondExecChangeCompleteRequest)
        {
            //1.3.1.submit�Ǘ��Җ��ύX(�Ǘ��Җ��ύX�������N�G�X�g)
            l_response = submitExecuteChange(
                (WEB3AdminBondExecChangeCompleteRequest) l_request);         
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�Ǘ��Җ��ύX���͉��)<BR>
     * get�Ǘ��Җ��ύX���͉�ʏ������s���B <BR>
     * <BR>
     * �V�[�P���X�}�uget�Ǘ��Җ��ύX���͉�ʁv�Q�� <BR>
     * --------------------------------------------------<BR>
     * @@param l_request - (�Ǘ��Җ��ύX���̓��N�G�X�g)<BR>
     * �Ǘ��Җ��ύX���̓��N�G�X�g<BR>
     * @@return WEB3AdminBondExecChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44CB39E400E0
     */
    protected WEB3AdminBondExecChangeInputResponse getExecuteChangeInputScreen(
        WEB3AdminBondExecChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getExecuteChangeInputScreen(WEB3AdminBondExecChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N������  
        //[validate����()�Ɏw�肷�����]  
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���i���ύX�A������j  
        //is�X�V�F�@@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4.get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5.get�������P��By����ID(long)
        //�������P�ʃI�u�W�F�N�g���擾 
        //[����] 
        //����ID�F���N�G�X�g�f�[�^.����ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.6.to�ڋq���(�g���������P��)
        //�ڋq�����擾 
        //[to�ڋq���()�̈���] 
        //�������P�ʁFget�������P��By����ID
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(
                WEB3AdminBondHelperService.class);
        WEB3AdminBondAccountInfo l_bondAccountInfo =
            l_helperService.toAccountInfo(l_bondOrderUnit);
        
        //1.7.validate���X����(���X�R�[�h : String)
        //���Y�Ǘ��҂��A�w��̕��X����舵���邩���`�F�b�N 
        //[validate���X����()�̈���] 
        //���X�R�[�h�Fto�ڋq���.���X�R�[�h
        l_admin.validateBranchPermission(l_bondAccountInfo.branchCode);
        
        //1.8.get������(long)
        //�������I�u�W�F�N�g���擾 
        //[get������()�̈���] 
        //����ID�Fget�������P��By����ID.get����ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProductId());
        
        //1.9.validate�Ǘ��Ҏ戵�\����(������)
        //�Ǘ��Ҏ戵�\���`�F�b�N 
        //[����] 
        //�������Fget������
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.10.validate���\���(BondOrderUnit)
        //�������ɑ΂����\�ł��邩�ǂ����`�F�b�N 
        //[����] 
        //�������P�ʁFget�������P��By����ID 
        l_bondOrderManager.validateExecutePossibleStatus(l_bondOrderUnit);
        
        //1.11.get������( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.12.to�������(������)
        //�������I�u�W�F�N�g���擾 
        //[����] 
        //�������Fget������ 
        WEB3AdminBondProductInfo l_bondProductInfo = 
            l_helperService.toProductInfo(l_bondProduct);
        
        //1.13.to�������(�g���������P��)
        //�������I�u�W�F�N�g���擾 
        //[get�������()�̈���] 
        //�������P�ʁF�擾�����������P�ʃI�u�W�F�N�g
        WEB3AdminBondOrderInfo l_bondOrderInfo = l_helperService.toOrderInfo(l_bondOrderUnit);
        
        //1.14.to�����(�g���������P��)
        //�����I�u�W�F�N�g���擾 
        //[get�����()�̈���] 
        //�������P�ʁF�������P�ʃI�u�W�F�N�g
        WEB3AdminBondOrderExecInfo l_orderExecInfo = 
            l_helperService.toOrderExecInfo(l_bondOrderUnit);
        
        //1.15.get�J�X�g�f�B�A���ꗗ(�،����)
        //�J�X�g�f�B�A���ꗗ���X�g���擾 
        //[get�J�X�g�f�B�A���ꗗ()�̈���] 
        //�،���ЁF�،���ЃI�u�W�F�N�g
        WEB3BondDataManagerService l_dataManagerService = 
            (WEB3BondDataManagerService) Services.getService(
                WEB3BondDataManagerService.class);
        List l_lisCustodians = l_dataManagerService.getCustodianList(l_institution);
        
        //1.16.to�J�X�g�f�B�A���ꗗ(List)
        //�J�X�g�f�B�A���ꗗ���擾 
        //[to�J�X�g�f�B�A���ꗗ()�̈���] 
        //�J�X�g�f�B�A�����X�g�Fget�J�X�g�f�B�A���ꗗ
        List l_lisCustodianList = l_helperService.toCustodianList(l_lisCustodians);
        
        //1.17.create���X�|���X( )
        //�Ǘ��Җ��ύX���̓��X�|���X�I�u�W�F�N�g�𐶐�
        WEB3AdminBondExecChangeInputResponse l_response =
            (WEB3AdminBondExecChangeInputResponse) l_request.createResponse();
        
        WEB3AdminBondCustodianUnit[] l_custodianUnits = null;
        if (l_lisCustodianList != null && !l_lisCustodianList.isEmpty())
        {
            l_custodianUnits = 
                new WEB3AdminBondCustodianUnit[l_lisCustodianList.size()];
            l_lisCustodianList.toArray(l_custodianUnits);
        }
        //1.18.�v���p�e�B�Z�b�g
        //�ڋq���=to�ڋq���
        l_response.accountInfo = l_bondAccountInfo;
        
        //������=to������
        l_response.productInfo = l_bondProductInfo;

        //�������=to�������
        l_response.orderInfo = l_bondOrderInfo;

        //�����=to�����
        l_response.execInfo = l_orderExecInfo;

        //�ڋq���=to�ڋq���
        l_response.inpOrderDate = l_datOrderBizDate;

        //�J�X�g�f�B�A���ꗗ=to�J�X�g�f�B�A���ꗗ
        l_response.custodianList = l_custodianUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�Ǘ��Җ��ύX)<BR>
     * validate�Ǘ��Җ��ύX�������s���B<BR> 
     * <BR>
     * �V�[�P���X�}�uvalidate�Ǘ��Җ��ύX�����v�Q�� <BR> 
     * --------------------------------------------------<BR>
     * @@param l_request - (�Ǘ��Җ��ύX�m�F���N�G�X�g)<BR>
     * �Ǘ��Җ��ύX�m�F���N�G�X�g<BR>
     * @@return WEB3AdminBondExecChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44CB39E400FF
     */
    protected WEB3AdminBondExecChangeConfirmResponse validateExecuteChange(
        WEB3AdminBondExecChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateExecuteChange(WEB3AdminBondExecChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N������  
        //[validate����()�Ɏw�肷�����]  
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���i���ύX�A������j  
        //is�X�V�F�@@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4.get�������P��By����ID(long)
        //�������P�ʃI�u�W�F�N�g���擾 
        //[����] 
        //����ID�F���N�G�X�g�f�[�^.����ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.5.get�⏕����(����ID : , �⏕����ID : )
        //�⏕�����I�u�W�F�N�g���擾 
        //[get�⏕����()�̈���] 
        //����ID�F�������P�ʃI�u�W�F�N�g.get����ID() 
        //�⏕����ID�F�������P�ʃI�u�W�F�N�g.get�⏕����ID()
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                    l_bondOrderUnit.getAccountId(), l_bondOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in �g���A�J�E���g�}�l�[�W������ڋq���擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.6.validate���X����(���X�R�[�h : String)
        //�Ǘ��҂��w�肵�����X�Ɏ�舵���邩�`�F�b�N 
        //[validate���X����()�̈���] 
        //���X�R�[�h�F�擾�����⏕����.get����X().get���X�R�[�h() 
        l_admin.validateBranchPermission(
            l_subAccount.getWeb3GenBranch().getBranchCode());
        
        //1.7.get������(long)
        //�������I�u�W�F�N�g���擾 
        //[get������()�̈���] 
        //����ID�Fget�������P��By����ID.get����ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProductId());
        
        //1.8.validate�Ǘ��Ҏ戵�\����(������)
        //�Ǘ��Ҏ戵�\���`�F�b�N 
        //[����] 
        //�������Fget������
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.9.get������(�m�F�������� : Date)
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.inpOrderDate);
        
        //1.10.validate���\���(BondOrderUnit)
        //�������ɑ΂����\�ł��邩�ǂ����`�F�b�N 
        //[����] 
        //�������P�ʁFget�������P��By����ID 
        l_bondOrderManager.validateExecutePossibleStatus(l_bondOrderUnit);
        
        //1.11.validate����(double, ������)
        //���ʂ��`�F�b�N 
        //[validate����()�̈���] 
        //�������ʁF���N�G�X�g�f�[�^.�����.��萔�� 
        //�������Fget������
        double l_dblExecFaceAmount = 0D;
        double l_dblExecPrice = 0D;
        BigDecimal l_bdExecFxRate = null;
        if (l_request.execInfo.execFaceAmount != null)
        {
            l_dblExecFaceAmount = Double.parseDouble(l_request.execInfo.execFaceAmount);
        }
        if (l_request.execInfo.execPrice != null)
        {
            l_dblExecPrice = Double.parseDouble(l_request.execInfo.execPrice);
        }
        if (l_request.execInfo.execFxRate != null)
        {
        	l_bdExecFxRate = new BigDecimal(l_request.execInfo.execFxRate);
        }    
        l_bondOrderManager.validateQuantity(
            l_dblExecFaceAmount, 
            l_bondProduct);
        
        //1.12.validate���������(double, �g���������P��)
        //������ʂ��`�F�b�N 
        //[validate�������()�̈���] 
        //�������ʁF���N�G�X�g�f�[�^.�����.��萔�� 
        //�������P�ʁFget�������P��By����ID
        l_bondOrderManager.validateExecuteMaxQuantity(
            l_dblExecFaceAmount, l_bondOrderUnit);
        
        //1.13validate�P��(������, String)
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_request.execInfo.execPrice);
        
        //1.14.validate�בփ��[�g(������, String)
        //�בփ��[�g�`�F�b�N 
        //[����] 
        //�������Fget������ 
        //�בփ��[�g�F���N�G�X�g�f�[�^.�����.���בփ��[�g
        l_bondOrderManager.validateFxRate(
            l_bondProduct, l_request.execInfo.execFxRate);
        
        //1.15.get��������ʔ���( )
        WEB3BondOrderTypeJudge l_orderTypeJudge = l_bondOrderUnit.getBondOrderTypeJudge();
        
        //1.16.create���������(java.util.Date, ��������ʔ���, ������)
        //���������𐶐����� 
        //[����] 
        //�������Fget������ 
        //��������ʔ���F����������������ʔ��� 
        //�������Fget������ 
        //���ϋ敪�Fget�������P��By����ID.get���ϋ敪 
        //���X�F�擾�����⏕����.get����X()
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = 
            l_bondOrderManager.createBondExecutionDateInfo(
                l_datOrderBizDate, l_orderTypeJudge, 
                l_bondProduct, l_bondOrderUnit.getSettlementDiv(),
                l_subAccount.getWeb3GenBranch());
        
        //1.17.reset�������(�����, ���������)
        //���������Đݒ� 
        //[reset���������()����] 
        //�����F���N�G�X�g�f�[�^.����� 
        //���������Fcreate���������
        //��������ʔ���F����������������ʔ��� 
        //�������Fget������ 
        //���ϋ敪�Fget�������P��By����ID.get���ϋ敪 
        //���X�F�擾�����⏕����.get����X()
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(
                WEB3AdminBondHelperService.class); 
        l_bondExecuteDateInfo = 
            l_helperService.resetExecuteDateInfo(l_request.execInfo, l_bondExecuteDateInfo, l_orderTypeJudge, 
                    l_bondProduct, l_bondOrderUnit.getSettlementDiv(),
                    l_subAccount.getWeb3GenBranch());
        
        //1.18.validate����(������)
        //�����������`�F�b�N���� 
        //[����] 
        //�������Fget������
        l_bondExecuteDateInfo.validateExecuteDate(l_bondProduct);
        
        //1.19is�O�݌�( )
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();
        
        BigDecimal l_bdGetFxRate = null;
        
        if (l_blnIsForeignCurrency)
        {
            //1.20.1get�בփ��[�g(������, ��������ʔ���, String, BigDecimal, boolean)
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_orderTypeJudge,
                l_bondOrderUnit.getSettlementDiv(),
                l_bdExecFxRate,
                true);
        }
        
        //1.21.calc��n���(��������ʔ���, BigDecimal,
        //BigDecimal, BigDecimal, ������, ���������)
        //����n����v�Z���ʃI�u�W�F�N�g�𐶐����� 
        //[����] 
        //��������ʔ���F����������������ʔ��� 
        //���ʁF���N�G�X�g.�����.��萔�� 
        //�����P���F���N�G�X�g.�����.���P�� l_bdValidateExecPrice
        //�בփ��[�g�Fget�בփ��[�g�i��is�O�݌�()�̖߂�l == false�̏ꍇ�Anull���Z�b�g����B�j 
        //�������Fget������ 
        //���������Freset������� 
        WEB3BondBizLogicProvider l_bizLogicProvider = 
            (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getBizLogicProvider();
        
        WEB3BondEstimatedPriceCalcResult l_priceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_orderTypeJudge, 
                new BigDecimal(String.valueOf(l_dblExecFaceAmount)),
                new BigDecimal(String.valueOf(l_dblExecPrice)),
                l_bdGetFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);
        
        //1.22.reset��n���(����n����v�Z����, �����, ������)
        //����n����v�Z���ʂ��Đݒ� 
        //[reset��n���()] 
        //����n����v�Z���ʁFcalc��n��� 
        //�����F���N�G�X�g.����� 
        //�������Fget������
        l_priceCalcResult = 
            l_helperService.resetEstimatedPrice(
                l_priceCalcResult, 
                l_request.execInfo, 
                l_bondProduct);
        
        //1.23.get�������敪()
        String l_strOrderExecStatus = l_bondOrderUnit.getOrderExecStatus();
        
        //1.24.�������敪�@@== ���ρ@@�̏ꍇ
        WEB3AdminBondExecuteCancelUpdateInterceptor l_cancelUpdateInterceptor = null;
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strOrderExecStatus))
        {
            //1.24.1���Ǘ��Җ�����X�V�C���^�Z�v�^( )
            l_cancelUpdateInterceptor = 
                new WEB3AdminBondExecuteCancelUpdateInterceptor();
            
            //1.24.2�v���p�e�B�Z�b�g
            //�ȉ��̃v���p�e�B���Z�b�g���� 
            //�Ǘ��ҁ�getInstanceFrom���O�C����� 
            //��������get������ 
            //�g���������P�ʁFget�������P��By����ID 
            l_cancelUpdateInterceptor.setAdministrator(l_admin);
            l_cancelUpdateInterceptor.setBondProduct(l_bondProduct);
            l_cancelUpdateInterceptor.setBondOrderUnit(l_bondOrderUnit);
        }
        
        //1.25.���Ǘ��Җ��X�V�C���^�Z�v�^( )
        WEB3AdminBondExecuteUpdateInterceptor l_executeUpdateInterceptor = 
            new WEB3AdminBondExecuteUpdateInterceptor();
        
        //1.26.�v���p�e�B�Z�b�g
        //���������C���^�Z�v�^�Ɉȉ��̃v���p�e�B���Z�b�g����B  
        //���������Freset������� 
        //����n����v�Z���ʁFreset��n��� 
        //�J�X�g�f�B�A���R�[�h�F���N�G�X�g�f�[�^.�����.�J�X�g�f�B�A��.�J�X�g�f�B�A���R�[�h() 
        //�������Fget������ 
        //�Ǘ��ҁFgetInstanceFrom���O�C�����
        l_executeUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_executeUpdateInterceptor.setBondEstimatedPriceCalcResult(l_priceCalcResult);
        if (l_request.execInfo.custodianInfo != null)
        {
            l_executeUpdateInterceptor.setCustodianCode(
                l_request.execInfo.custodianInfo.custodianCode);
        }
        l_executeUpdateInterceptor.setBondProduct(l_bondProduct);
        l_executeUpdateInterceptor.setAdministrator(l_admin);
        
        boolean l_isSellFirstExecute = l_orderTypeJudge.isSellOrder() && WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_strOrderExecStatus);
        
        //1.27.���ϋ敪�����~�݁@@���@@�i���p�����Ŗ����ł������j����false�̏ꍇ
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_bondOrderUnit.getSettlementDiv())
                && !l_isSellFirstExecute)
        {
            
            //1.27.1.���Ǘ��Җ��ύX�C���^�Z�v�^( )
            WEB3AdminBondExecuteChangeCommonInterceptor l_changeCommonInterceptor =
                new WEB3AdminBondExecuteChangeCommonInterceptor();
            
            //1.27.2.set���Ǘ��Җ�����X�V�C���^�Z�v�^(���Ǘ��Җ�����X�V�C���^�Z�v�^)
            //�C���^�Z�v�^���Z�b�g���� 
            //�����F���Ǘ��Җ�����X�V�C���^�Z�v�^ 
            //�@@�@@�@@�@@(�쐬����ĂȂ�����null�ɂȂ�)
            l_changeCommonInterceptor.setBondExecuteCancelUpdateInterceptor(l_cancelUpdateInterceptor);
            
            //1.27.3.set���Ǘ��Җ��X�V�C���^�Z�v�^(���Ǘ��ҐV�K���X�V�C���^�Z�v�^)
            //�C���^�Z�v�^���Z�b�g���� 
            //�����F���Ǘ��Җ��X�V�C���^�Z�v�^
            l_changeCommonInterceptor.setBondExecuteUpdateInterceptor(l_executeUpdateInterceptor);
            
            //1.27.4.BondChangeOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
            //���ύX�������e�I�u�W�F�N�g�𐶐� 
            //[����] 
            //����ID�F���N�G�X�g�f�[�^.get����ID() 
            //�����P��ID�Fget�������P��By����ID,get�����P��ID() 
            //���ʁF���N�G�X�g�f�[�^.�����.��萔��() 
            //�P���F���N�G�X�g�f�[�^.�����.���P��() 
            BondChangeOrderSpec l_changeOrderSpec = 
                new BondChangeOrderSpec(
                    Long.parseLong(l_request.id), 
                    l_bondOrderUnit.getOrderUnitId(),
                    l_dblExecFaceAmount,
                    l_dblExecPrice);
            
            //1.27.5.validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], 
            //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //�]�̓`�F�b�N 
            //[validate����]��()�̈���] 
            //�⏕�����Fget�⏕���� 
            //�������e�C���^�Z�v�^�F���Ǘ��Җ��ύX�C���^�Z�v�^ 
            //�������e�FBondChangeOrderSpec 
            //������ʁFget�������P��By����ID.get������� 
            //�]�͍X�V�t���O�Ffalse
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            Object[] l_objCommonInterceptors = new Object[]{l_changeCommonInterceptor};
            Object[] l_objChangeOrderSpecs= new Object[]{l_changeOrderSpec};
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_service.validateTradingPower(
                    l_subAccount, 
                    l_objCommonInterceptors, 
                    l_objChangeOrderSpecs, 
                    l_bondOrderUnit.getOrderType(), 
                    false);
            
            //1.27.6.is����t���O( )
            //1.27.7. is����t���O����false�̏ꍇ
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                //1.24.7.1.add�x���敪(String)
                //�x���敪��ǉ� 
                //[����] 
                //�x���敪�F�]�̓`�F�b�NNG
                l_priceCalcResult.addWarningDiv(WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG);
            }
        }
        
        //1.28.to�����(���������, ����n����v�Z����, �J�X�g�f�B�A��, �g���������P��)
        //���X�|���X�̖������Z�b�g 
        //�����F 
        //���������=reset������� 
        //����n����v�Z����=reset��n��� 
        //�J�X�g�f�B�A��=���N�G�X�g.�����.�J�X�g�f�B�A�� 
        //�g���������P�ʁ�get�������P��By����ID()�̖߂�l  
        WEB3AdminBondOrderExecInfo l_orderExecInfo = l_helperService.toOrderExecInfo(
            l_bondExecuteDateInfo, l_priceCalcResult, l_request.execInfo.custodianInfo, l_bondOrderUnit);
        
        //1.29.create���X�|���X( )
        //�Ǘ��Җ��ύX�m�F���X�|���X�I�u�W�F�N�g�𐶐�
        WEB3AdminBondExecChangeConfirmResponse l_response = null;
        l_response = (WEB3AdminBondExecChangeConfirmResponse) l_request.createResponse();
        
        //1.30.�v���p�e�B�Z�b�g
        //�v���p�e�B���Z�b�g����B
        //���͎���������get������ 
        //�����=to����� 
        l_response.inpOrderDate = l_datOrderBizDate;
        l_response.execInfo = l_orderExecInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�Ǘ��Җ��ύX)<BR>
     * submit�Ǘ��Җ��ύX�������s���B <BR>
     * <BR>
     * �V�[�P���X�}�usubmit�Ǘ��Җ��ύX�����v�Q�� <BR>
     * --------------------------------------------------<BR>
     * @@param l_request - (�Ǘ��Җ��ύX�������N�G�X�g)<BR>
     * �Ǘ��Җ��ύX�������N�G�X�g<BR>
     * @@return WEB3AdminBondExecChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44CB39E4011E
     */
    protected WEB3AdminBondExecChangeCompleteResponse submitExecuteChange(
        WEB3AdminBondExecChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitExecuteChange(WEB3AdminBondExecChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N������  
        //[validate����()�Ɏw�肷�����]  
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���i���ύX�A������j  
        //is�X�V�F�@@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.4.validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������`�F�b�N���� 
        //[validate����p�X���[�h()�̈���] 
        //�p�X���[�h�F���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5.get�������P��By����ID(long)
        //�������P�ʃI�u�W�F�N�g���擾 
        //[����] 
        //����ID�F���N�G�X�g�f�[�^.����ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        
        //1.6.get�⏕����(����ID : , �⏕����ID : )
        //�⏕�����I�u�W�F�N�g���擾 
        //[get�⏕����()�̈���] 
        //����ID�F�������P�ʃI�u�W�F�N�g.get����ID() 
        //�⏕����ID�F�������P�ʃI�u�W�F�N�g.get�⏕����ID()
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                l_bondOrderUnit.getAccountId(), l_bondOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in �g���A�J�E���g�}�l�[�W������ڋq���擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.7.validate���X����(���X�R�[�h : String)
        //�Ǘ��҂��w�肵�����X�Ɏ�舵���邩�`�F�b�N 
        //[validate���X����()�̈���] 
        //���X�R�[�h�F�擾�����⏕����.get����X().get���X�R�[�h() 
        l_admin.validateBranchPermission(l_subAccount.getWeb3GenBranch().getBranchCode());
        
        //1.8.get������(long)
        //�������I�u�W�F�N�g���擾 
        //[get������()�̈���] 
        //����ID�Fget�������P��By����ID.get����ID()
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProductId());
        
        //1.9.validate�Ǘ��Ҏ戵�\����(������)
        //�Ǘ��Ҏ戵�\���`�F�b�N 
        //[����] 
        //�������Fget������
        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);
        
        //1.10.get������(�m�F�������� : Date)
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.inpOrderDate);
        
        //1.11.validate���\���(BondOrderUnit)
        //�������ɑ΂����\�ł��邩�ǂ����`�F�b�N 
        //[����] 
        //�������P�ʁFget�������P��By����ID 
        l_bondOrderManager.validateExecutePossibleStatus(l_bondOrderUnit);
        
        //1.12.validate����(double, ������)
        //���ʂ��`�F�b�N 
        //[validate����()�̈���] 
        //�������ʁF���N�G�X�g�f�[�^.�����.��萔�� 
        //�������Fget������
        double l_dblExecFaceAmount = 0D;
        double l_dblExecPrice = 0D;
        BigDecimal l_bdExecFxRate = null;
        if (l_request.execInfo.execFaceAmount != null)
        {
            l_dblExecFaceAmount = Double.parseDouble(l_request.execInfo.execFaceAmount);
        }
        if (l_request.execInfo.execPrice != null)
        {
            l_dblExecPrice = Double.parseDouble(l_request.execInfo.execPrice);
        }
        if (l_request.execInfo.execFxRate != null)
        {
        	l_bdExecFxRate = new BigDecimal(l_request.execInfo.execFxRate);
        }
        
        l_bondOrderManager.validateQuantity(l_dblExecFaceAmount, l_bondProduct);
        
        //1.13.validate���������(double, �g���������P��)
        //������ʂ��`�F�b�N 
        //[validate�������()�̈���] 
        //�������ʁF���N�G�X�g�f�[�^.�����.��萔�� 
        //�������P�ʁFget�������P��By����ID
        l_bondOrderManager.validateExecuteMaxQuantity(l_dblExecFaceAmount, l_bondOrderUnit);
        
        //1.15 validate�P��(������, String)
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_request.execInfo.execPrice);
        
        //1.14.validate�בփ��[�g(������, String)
        //�בփ��[�g�`�F�b�N 
        //[����] 
        //�������Fget������ 
        //�בփ��[�g�F���N�G�X�g�f�[�^.�����.���בփ��[�g
        l_bondOrderManager.validateFxRate(
            l_bondProduct, l_request.execInfo.execFxRate);
        
        //1.15.get��������ʔ���( )
        WEB3BondOrderTypeJudge l_orderTypeJudge = l_bondOrderUnit.getBondOrderTypeJudge();
        
        //1.16.create���������(java.util.Date, ��������ʔ���, ������, String, Branch)
        //���������𐶐����� 
        //[����] 
        //�������Fget������ 
        //��������ʔ���F����������������ʔ��� 
        //�������Fget������
        //���ϋ敪�Fget�������P��By����ID.get���ϋ敪 
        //���X�F�擾�����⏕����.get����X()
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = 
            l_bondOrderManager.createBondExecutionDateInfo(
                l_datOrderBizDate, l_orderTypeJudge, 
                l_bondProduct, l_bondOrderUnit.getSettlementDiv(),
                l_subAccount.getWeb3GenBranch());
        
        //1.17.reset�������(�����, ���������)
        //���������Đݒ� 
        //[reset���������()����] 
        //�����F���N�G�X�g�f�[�^.����� 
        //���������Fcreate���������
        //��������ʔ���F����������������ʔ��� 
        //�������Fget������
        //���ϋ敪�Fget�������P��By����ID.get���ϋ敪 
        //���X�F�擾�����⏕����.get����X()
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(WEB3AdminBondHelperService.class); 
        l_bondExecuteDateInfo = 
            l_helperService.resetExecuteDateInfo(l_request.execInfo, l_bondExecuteDateInfo, l_orderTypeJudge, 
                    l_bondProduct, l_bondOrderUnit.getSettlementDiv(),
                    l_subAccount.getWeb3GenBranch());
        
        //1.18.validate����(������)
        //�����������`�F�b�N���� 
        //[����] 
        //�������Fget������
        l_bondExecuteDateInfo.validateExecuteDate(l_bondProduct);
        
        //1.20 is�O�݌�( )
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();
        
        BigDecimal l_bdGetFxRate = null;
        
        if (l_blnIsForeignCurrency)
        {
            //1.19.1get�בփ��[�g(������, ��������ʔ���, String, BigDecimal, boolean)
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_orderTypeJudge,
                l_bondOrderUnit.getSettlementDiv(),
                l_bdExecFxRate,
                true);
        }
        
        //1.22.calc��n���(��������ʔ���, BigDecimal,
        //BigDecimal, BigDecimal, ������, ���������)
        //����n����v�Z���ʃI�u�W�F�N�g�𐶐����� 
        //[����] 
        //��������ʔ���F����������������ʔ��� 
        //���ʁF���N�G�X�g.�����.��萔�� 
        //�����P���F���N�G�X�g.�����.���P�� 
        //�בփ��[�g�Fget�בփ��[�g�i��is�O�݌�()�̖߂�l == false�̏ꍇ�Anull���Z�b�g����B�j 
        //�������Fget������ 
        //���������Freset������� 
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.BOND).getBizLogicProvider();
        WEB3BondEstimatedPriceCalcResult l_priceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_orderTypeJudge, 
                new BigDecimal(String.valueOf(l_dblExecFaceAmount)),
                new BigDecimal(String.valueOf(l_dblExecPrice)),
                l_bdGetFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);
        
        //1.23.reset��n���(����n����v�Z����, �����, ������)
        //����n����v�Z���ʂ��Đݒ� 
        //[reset��n���()] 
        //����n����v�Z���ʁFcalc��n��� 
        //�����F���N�G�X�g.����� 
        //�������Fget������
        l_priceCalcResult = 
            l_helperService.resetEstimatedPrice(l_priceCalcResult, l_request.execInfo, l_bondProduct);
        
        //1.24.get�������敪()
        String l_strOrderExecStatus = l_bondOrderUnit.getOrderExecStatus();
        
        //1.25.�������敪�@@== ���ρ@@�̏ꍇ
        WEB3AdminBondExecuteCancelUpdateInterceptor l_cancelUpdateInterceptor = null;
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strOrderExecStatus))
        {
            //1.25.1���Ǘ��Җ�����X�V�C���^�Z�v�^( )
            l_cancelUpdateInterceptor = 
                new WEB3AdminBondExecuteCancelUpdateInterceptor();
            
            //1.25.2�v���p�e�B�Z�b�g
            //�ȉ��̃v���p�e�B���Z�b�g���� 
            //�Ǘ��ҁ�getInstanceFrom���O�C����� 
            //��������get������ 
            //�g���������P�ʁFget�������P��By����ID 
            l_cancelUpdateInterceptor.setAdministrator(l_admin);
            l_cancelUpdateInterceptor.setBondProduct(l_bondProduct);
            l_cancelUpdateInterceptor.setBondOrderUnit(l_bondOrderUnit);
        }
        
        //1.26.���Ǘ��Җ��X�V�C���^�Z�v�^( )
        WEB3AdminBondExecuteUpdateInterceptor l_executeUpdateInterceptor = 
            new WEB3AdminBondExecuteUpdateInterceptor();
        
        //1.27.�v���p�e�B�Z�b�g
        //���������C���^�Z�v�^�Ɉȉ��̃v���p�e�B���Z�b�g����B  
        //���������Freset������� 
        //����n����v�Z���ʁFreset��n��� 
        //�J�X�g�f�B�A���R�[�h�F���N�G�X�g�f�[�^.�����.�J�X�g�f�B�A��.�J�X�g�f�B�A���R�[�h() 
        //�������Fget������ 
        //�Ǘ��ҁFgetInstanceFrom���O�C�����
        l_executeUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_executeUpdateInterceptor.setBondEstimatedPriceCalcResult(l_priceCalcResult);
        if (l_request.execInfo.custodianInfo != null)
        {
            l_executeUpdateInterceptor.setCustodianCode(l_request.execInfo.custodianInfo.custodianCode);
        }
        l_executeUpdateInterceptor.setBondProduct(l_bondProduct);
        l_executeUpdateInterceptor.setAdministrator(l_admin);
        
        boolean l_isSellFirstExecute = l_orderTypeJudge.isSellOrder() && WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_strOrderExecStatus);
        
        //1.28.���ϋ敪�����~�݁@@���@@�i���p�����Ŗ����ł������j����false�̏ꍇ
        WEB3TPTradingPowerResult l_tPTradingPowerResult = null;
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_bondOrderUnit.getSettlementDiv())
                && !l_isSellFirstExecute)
        {
            
            //1.28.1.���Ǘ��Җ��ύX�C���^�Z�v�^( )
            WEB3AdminBondExecuteChangeCommonInterceptor l_changeCommonInterceptor =
                new WEB3AdminBondExecuteChangeCommonInterceptor();
            
            //1.28.2.set���Ǘ��Җ�����X�V�C���^�Z�v�^(���Ǘ��Җ�����X�V�C���^�Z�v�^)
            //�C���^�Z�v�^���Z�b�g���� 
            //�����F���Ǘ��Җ�����X�V�C���^�Z�v�^ 
            //�@@�@@�@@�@@(�쐬����ĂȂ�����null�ɂȂ�)
            l_changeCommonInterceptor.setBondExecuteCancelUpdateInterceptor(l_cancelUpdateInterceptor);
            
            //1.28.3.set���Ǘ��Җ��X�V�C���^�Z�v�^(���Ǘ��ҐV�K���X�V�C���^�Z�v�^)
            //�C���^�Z�v�^���Z�b�g���� 
            //�����F���Ǘ��Җ��X�V�C���^�Z�v�^
            l_changeCommonInterceptor.setBondExecuteUpdateInterceptor(l_executeUpdateInterceptor);
            
            //1.28.4.BondChangeOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
            //���ύX�������e�I�u�W�F�N�g�𐶐� 
            //[����] 
            //����ID�F���N�G�X�g�f�[�^.get����ID() 
            //�����P��ID�Fget�������P��By����ID,get�����P��ID() 
            //���ʁF���N�G�X�g�f�[�^.�����.��萔��() 
            //�P���F���N�G�X�g�f�[�^.�����.���P��() 
            BondChangeOrderSpec l_changeOrderSpec = 
                new BondChangeOrderSpec(
                    Long.parseLong(l_request.id), 
                    l_bondOrderUnit.getOrderUnitId(),
                    l_dblExecFaceAmount,
                    l_dblExecPrice);
            
            //1.28.5.validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], 
            //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //�]�̓`�F�b�N 
            //[validate����]��()�̈���] 
            //�⏕�����Fget�⏕���� 
            //�������e�C���^�Z�v�^�F���Ǘ��Җ��ύX�C���^�Z�v�^ 
            //�������e�FBondChangeOrderSpec 
            //������ʁFget�������P��By����ID.get������� 
            //�]�͍X�V�t���O�Ftrue         
            Object[] l_objCommonInterceptors = new Object[]{l_changeCommonInterceptor};
            Object[] l_objChangeOrderSpecs= new Object[]{l_changeOrderSpec};
            
            l_tPTradingPowerResult = 
                l_service.validateTradingPower(
                    l_subAccount, 
                    l_objCommonInterceptors, 
                    l_objChangeOrderSpecs, 
                    l_bondOrderUnit.getOrderType(), 
                    true);
            
            //1.28.6.is����t���O( )
            //1.28.7. is����t���O����false�̏ꍇ
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                //1.28.7.1.add�x���敪(String)
                //�x���敪��ǉ� 
                //[����] 
                //�x���敪�F�]�̓`�F�b�NNG
                l_priceCalcResult.addWarningDiv(WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG);
            }
        }
        WEB3AdminBondExecuteNotifyService l_notifyService = 
            (WEB3AdminBondExecuteNotifyService) Services.getService(
                WEB3AdminBondExecuteNotifyService.class);
        //1.29.�������敪�@@== ���ρ@@�̏ꍇ
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_strOrderExecStatus))
        {
            //1.29.1.undo���(BondOrderUnit, ���Ǘ��҃f�t�H���g�C���^�Z�v�^)
            //����������� 
            //[����]  
            //�@@�������P�ʁF get�������P��By����ID 
            //�@@���Ǘ��҃f�t�H���g�C���^�Z�v�^�F ���Ǘ��Җ�����X�V�C���^�Z�v                    
            l_notifyService.undoExecute(l_bondOrderUnit, l_cancelUpdateInterceptor);
        }
        else
        {
            //1.30.�������敪�@@!= ���ρ@@�̏ꍇ
            //1.30.1.���Ǘ��Ғ�����t�X�V�C���^�Z�v�^( )
            WEB3AdminBondOrderAcceptUpdateInterceptor l_acceptUpdateInterceptor = 
                new WEB3AdminBondOrderAcceptUpdateInterceptor();
            
            //1.30.2.accept�V�K����(long, ���Ǘ��҃f�t�H���g�C���^�Z�v�^)
            //�V�K������t������ 
            //[����]  
            //�@@����ID�F ���N�G�X�g�f�[�^.����ID 
            //�@@���Ǘ��҃f�t�H���g�C���^�Z�v�^�F ���Ǘ��Ғ�����t�X�V�C���^�Z�v�^
            l_notifyService.acceptNewOrder(Long.parseLong(l_request.id), l_acceptUpdateInterceptor);
            
        }
        
        //1.31.notify���(BondOrderUnit, ���Ǘ��҃f�t�H���g�C���^�Z�v�^)
        //��菈�������� 
        //[����]  
        //�@@�������P�ʁF get�������P��By����ID 
        //�@@���Ǘ��҃f�t�H���g�C���^�Z�v�^�F ���Ǘ��Җ��X�V�C���^�Z�v�^
        l_bondOrderUnit = l_bondOrderManager.getBondOrderUnitByOrderId(Long.parseLong(l_request.id));
        l_notifyService.notifyExecute(l_bondOrderUnit, l_executeUpdateInterceptor);

        //1.32.�iis����t���O�I��null ���@@is����t���O����false�j���́i�i���p�����Ŗ����ł������j����true�j�̏ꍇ
        if((l_tPTradingPowerResult !=null && !l_tPTradingPowerResult.isResultFlg())
            || l_isSellFirstExecute)
        {
            //1.32.1 �]�͍Čv�Z(�⏕���� : �⏕����)
            l_service.reCalcTradingPower(l_subAccount);  
        }

        //1.33.create���X�|���X( )
        WEB3AdminBondExecChangeCompleteResponse l_response =
            (WEB3AdminBondExecChangeCompleteResponse) l_request.createResponse();
        
        //1.34.�v���p�e�B�Z�b�g
        //�v���p�e�B���Z�b�g����B  
        //�@@�@@�X�V���ԁF���ݓ��� 
        //�@@�@@���ʔԍ��F���N�G�X�g�f�[�^.����ID 
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        l_response.orderActionId = l_request.id;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
