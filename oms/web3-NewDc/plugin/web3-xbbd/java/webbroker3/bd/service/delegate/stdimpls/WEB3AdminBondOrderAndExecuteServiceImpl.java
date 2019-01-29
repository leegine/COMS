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
filename	WEB3AdminBondOrderAndExecuteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҐV�K�����̓T�[�r�XImpl(WEB3AdminBondOrderAndExecuteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
                 : 2006/10/12 �����F (���u) WEB�V�J���W���̌������̑Ή��inewBigDecimal�����j
                 : 2006/10/16 ��іQ (���u) ���f�� No.106.108.121.129.131 �c�a�X�V�d�lNo.019
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.bd.WEB3AdminBondNewExecuteUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondNewOrderUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderAcceptUpdateInterceptor;
import webbroker3.bd.WEB3AdminBondOrderAndExecuteCommonInterceptor;
import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondTradingTypeDef;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondExecCalculateRequest;
import webbroker3.bd.message.WEB3AdminBondExecCalculateResponse;
import webbroker3.bd.message.WEB3AdminBondExecCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecInputResponse;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderAndExecuteService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderSettleDivDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
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
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҐV�K�����̓T�[�r�XImpl)<BR>
 * �Ǘ��ҐV�K�����̓T�[�r�XImpl�N���X
 *
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondOrderAndExecuteServiceImpl implements WEB3AdminBondOrderAndExecuteService
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderAndExecuteServiceImpl.class);

    /**
     * @@roseuid 44E3362C0148
     */
    public WEB3AdminBondOrderAndExecuteServiceImpl()
    {

    }

    /**
     * �Ǘ��ҐV�K�����̓T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�V�K������execute�v���Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@roseuid 44B61BFD006D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        //1.1.���N�G�X�g�f�[�^�͊Ǘ��ҐV�K�����̓��N�G�X�g�ł���ꍇ
        if (l_request instanceof WEB3AdminBondExecInputRequest)
        {
            //1.1.1.get�Ǘ��ҐV�K�����͉��(�Ǘ��ҐV�K�����̓��N�G�X�g)
            l_response =
                getExecuteInputScreen((WEB3AdminBondExecInputRequest)l_request);
        }
        //1.2.���N�G�X�g�f�[�^�͊Ǘ��ҐV�K���m�F���N�G�X�g�ł���ꍇ
        else if (l_request instanceof WEB3AdminBondExecConfirmRequest)
        {
            //1.2.1.validate�Ǘ��ҐV�K������(�Ǘ��ҐV�K���m�F���N�G�X�g)
            l_response =
                validateExecuteInput((WEB3AdminBondExecConfirmRequest)l_request);
        }
        //1.3.���N�G�X�g�f�[�^�͊Ǘ��ҐV�K��芮�����N�G�X�g�ł���ꍇ
        else if (l_request instanceof WEB3AdminBondExecCompleteRequest)
        {
            //1.3.1.submit�Ǘ��ҐV�K������(�Ǘ��ҐV�K��芮�����N�G�X�g)
            l_response =
                submitExecuteInput((WEB3AdminBondExecCompleteRequest)l_request);
        }
        //1.4.���N�G�X�g�f�[�^�͊Ǘ��ҐV�K���v�Z���N�G�X�g�ł���ꍇ
        else if (l_request instanceof WEB3AdminBondExecCalculateRequest)
        {
            //1.4.1.calc��n���(��n����v�Z���N�G�X�g)
            l_response =
                calcEstimatedPrice((WEB3AdminBondExecCalculateRequest)l_request);
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
     * (get�Ǘ��ҐV�K�����͉��)<BR>
     * �V�K�����͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�uget�Ǘ��ҐV�K�����͉�ʁv���Q��
     * @@param l_request - (�Ǘ��ҐV�K�����̓��N�G�X�g)
     * @@return WEB3AdminBondExecInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6224F02AF
     */
    protected WEB3AdminBondExecInputResponse getExecuteInputScreen(
        WEB3AdminBondExecInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecuteInputScreen(WEB3AdminBondExecInputRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1.validate( )
        l_request.validate();
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_INPUT, true);

        //1.4get�،����( )
        Institution l_institution = l_admin.getInstitution();

        //1.5.getBranch(arg0 : Institution, arg1 : �_���r���[::java::lang::String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getBranch(
                l_institution,
                l_admin.getBranchCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.6. ���X�����݂��Ȃ��ꍇ�A��O���X���[����
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����X�f�[�^�Ȃ�");
        }
        //1.7. get������(Institution, String)
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_institution,
                l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.8.validate�Ǘ��Ҏ戵�\����(������)
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();

        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);

        //1.9.to�������(������)
        WEB3AdminBondHelperService l_bondHelperService =
            (WEB3AdminBondHelperService)Services.getService(WEB3AdminBondHelperService.class);

        WEB3AdminBondProductInfo l_bondProductInfo =
            l_bondHelperService.toProductInfo(l_bondProduct);

        //1.10.��������ʔ���(�������, String)
        //��������ʃI�u�W�F�N�g�𐶐�
        //[��������ʔ���()�̈���]
        //�������:OrderTypeEnum.�����t
        //����F�����d�؎��
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.BOND_BUY,
                WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);

        //1.11. �������( )
        WEB3AdminBondOrderInfo l_bondOrderInfo = new WEB3AdminBondOrderInfo();

        //1.12.get���t�P��( )
        String l_strPrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());

        //1.13.is�O�݌�( )
        boolean l_foreignCurrency = l_bondProduct.isForeignCurrency();

        //1.14is�O�݌�( )����true�̏ꍇ
        BigDecimal l_bdGetFxRate = null;

        String l_strSettlementDiv = null;
        if (l_foreignCurrency)
        {
            l_strSettlementDiv = WEB3BondOrderSettleDivDef.FOREIGN_CURRENCY;
        }
        else
        {
            l_strSettlementDiv = WEB3BondOrderSettleDivDef.YEN_CURRENCY;
        }

        if (l_foreignCurrency)
        {

            //1.14.1get�בփ��[�g(������, ��������ʔ���, String, BigDecimal, boolean)
            //    �������Fget������
            //   �@@��������ʔ���F��������ʔ���I�u�W�F�N�g
            //   �@@������.is�O�݌�()==true�̏ꍇ
            //   �@@�@@���ϋ敪�F�O��
            //   �@@���͈בփ��[�g�F�@@0
            //   �@@is���v�Z�F�@@true
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_bondOrderTypeJudge,
                l_strSettlementDiv,
                new BigDecimal("0"),
                true);
        }

        //1.15.�v���p�e�B�Z�b�g
        //���������������I�u�W�F�N�g�Ɉȉ��̃f�t�H���g�v���p�e�B�l���Z�b�g����.
        //�E������ʁF�����t
        //�E����F�����d�؎��
        //�E������.is�O�݌�()==true�̏ꍇ
        //�@@�@@���ϋ敪�F�O��
        //�@@������.is�O�݌�()==false�̏ꍇ
        //�@@�@@���ϋ敪�F�~��
        //�E�������ʁFnull
        //�E�P���F������.get���t�P��()
        //�E������.is�O�݌�()==true�̏ꍇ
        //�E�בփ��[�g�Fget�בփ��[�g
        //�E�ŋ敪�F���
        l_bondOrderInfo.tradingType = WEB3BondTradingTypeDef.BOND_BUY;
        l_bondOrderInfo.dealType = WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING;
        if (l_foreignCurrency)
        {
            l_bondOrderInfo.settleDiv = WEB3BondOrderSettleDivDef.FOREIGN_CURRENCY;

            l_bondOrderInfo.fxRate = l_bdGetFxRate.toString();
        }
        else
        {
            l_bondOrderInfo.settleDiv = WEB3BondOrderSettleDivDef.YEN_CURRENCY;
        }
        l_bondOrderInfo.orderQuantity = null;
        l_bondOrderInfo.price = l_strPrice;

        l_bondOrderInfo.taxType = TaxTypeEnum.NORMAL.intValue() + "";

        //1.16.get������( )
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.17.create���������(java.util.Date, ��������ʔ���, ������)
        //�����������擾
        //[����]
        //�������Fget������
        //��������ʔ���F��������ʔ���I�u�W�F�N�g
        //�������Fget������
        //������.is�O�݌�()==true�̏ꍇ
        //�@@���ϋ敪�F�O��
        //������.is�O�݌�()==false�̏ꍇ
        //�@@���ϋ敪�F�~��
        //���X�FgetBranch
        WEB3BondExecuteDateInfo l_bondExecutionDateInfo =
            (WEB3BondExecuteDateInfo)l_bondOrderManager.createBondExecutionDateInfo(
            l_datBizDate,
            l_bondOrderTypeJudge,
            l_bondProduct,
            l_strSettlementDiv,
            l_branch);

        //1.18�����( )
        WEB3AdminBondOrderExecInfo l_bondOrderExecInfo = new WEB3AdminBondOrderExecInfo();

        //1.19�v���p�e�B�Z�b�g
        //�����I�u�W�F�N�g�Ɉȉ��̃v���p�e�B���Z�b�g����.
        //����=create���������.get����()
        //���n����=create���������.get���n����()
        //��n��=create���������.get��n��()
        //���n��n��= create���������.get���n��n��()
        //������= create���������.get������()
        l_bondOrderExecInfo.domesticExecutionDate = l_bondExecutionDateInfo.getExecuteDate();
        l_bondOrderExecInfo.foreignExecutionDate = l_bondExecutionDateInfo.getForeignExecuteDate();
        l_bondOrderExecInfo.domesticDeliveryDate = l_bondExecutionDateInfo.getDeliveryDate();
        l_bondOrderExecInfo.foreignDeliveryDate = l_bondExecutionDateInfo.getForeignDeliveryDate();
        l_bondOrderExecInfo.paymentDate = l_bondExecutionDateInfo.getPaymentDate();

        //1.20get�J�X�g�f�B�A���ꗗ(�،����)
        WEB3BondDataManagerService l_bondDataManagerService =
            (WEB3BondDataManagerService)Services.getService(WEB3BondDataManagerService.class);
        List l_list = l_bondDataManagerService.getCustodianList(l_institution);

        //1.21.to�J�X�g�f�B�A���ꗗ(List)

        List l_lisCustodianList = l_bondHelperService.toCustodianList(l_list);

        //1.22.create���X�|���X( )
        WEB3AdminBondExecInputResponse l_response =
            (WEB3AdminBondExecInputResponse)l_request.createResponse();

        //1.23.create���X�|���X()�ɂĎ擾�������X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //)�������=to�������
        l_response.productInfo = l_bondProductInfo;
        //�Q�j�������=���������������I�u�W�F�N�g
        l_response.orderInfo = l_bondOrderInfo;
        //�R�j�����=�������������I�u�W�F�N�g
        l_response.execInfo = l_bondOrderExecInfo;
        //�S�j�J�X�g�f�B�A���ꗗ=to�J�X�g�f�B�A���ꗗ�̖߂�l��z��ɂ���
        WEB3AdminBondCustodianUnit[] l_bondCustodianUnits = null;
        if (l_lisCustodianList != null && !l_lisCustodianList.isEmpty())
        {
            l_bondCustodianUnits = new WEB3AdminBondCustodianUnit[l_lisCustodianList.size()];
            l_lisCustodianList.toArray(l_bondCustodianUnits);
        }
        l_response.custodianList = l_bondCustodianUnits;
        //�T�j���͎��������Fget������
        l_response.inpOrderDate = l_datBizDate;
        //�U�j������ʈꗗ={����������, �����蒍��}
        String[] l_strBondTradings = {WEB3BondTradingTypeDef.BOND_BUY,
            WEB3BondTradingTypeDef.BOND_SELL};
        l_response.tradingTypeList = l_strBondTradings;
        //�V�j����ꗗ={��W���, �����d�؎��}
        String[] l_strDealTypes = {WEB3DealTypeDef.RECRUIT_TRADING,
            WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING};
        l_response.dealTypeList = l_strDealTypes;
        //�W�j���ϋ敪�ꗗ={�~��, �O��}
        String[] l_strSettlementDivs = {WEB3BondOrderSettleDivDef.YEN_CURRENCY,
            WEB3BondOrderSettleDivDef.FOREIGN_CURRENCY};
        l_response.settleDivList = l_strSettlementDivs;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�Ǘ��ҐV�K������)<BR>
     * �Ǘ��ҐV�K�����͊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�uvalidate�Ǘ��ҐV�K�����́v���Q�ƁB
     * ==========================================================
     * 1.6. ���X�����݂��Ȃ��ꍇ�A��O���X���[����
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01386<BR>
     * <BR>
     * ==========================================================
     * <BR>
     * @@param l_request - (�Ǘ��ҐV�K���m�F���N�G�X�g)
     * @@return WEB3AdminBondExecConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B622570195
     */
    protected WEB3AdminBondExecConfirmResponse validateExecuteInput(
            WEB3AdminBondExecConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecuteInput(WEB3AdminBondExecConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate( )
        l_request.validate();

        //1.2. getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3.�،����( )
        Institution l_institution = l_admin.getInstitution();

        //1.4.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_INPUT,
            true);

        //1.5.getBranch(arg0 : Institution, arg1 : �_���r���[::java::lang::String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getBranch(
                l_institution,
                l_request.accountInfo.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.6. ���X�����݂��Ȃ��ꍇ�A��O���X���[����
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����X�f�[�^�Ȃ�");
        }

        //1.7.validate���X����(���X�R�[�h : String)
        l_admin.validateBranchPermission(l_request.accountInfo.branchCode);
        //1.8. get�⏕����(String, String, String)
        //�⏕�����I�u�W�F�N�g���擾
        //[get�⏕����()�̈���]
        //�،���ЃR�[�h�F�擾�����،���ЃI�u�W�F�N�g.�،���ЃR�[�h()
        //���X�R�[�h�F���N�G�X�g�f�[�^.�ڋq���.���X�R�[�h
        //�ڋq�R�[�h�F���N�G�X�g�f�[�^.�ڋq���.�ڋq�R�[�h
        WEB3AdminBondHelperService l_bondHelperService =
            (WEB3AdminBondHelperService)Services.getService(WEB3AdminBondHelperService.class);
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)l_bondHelperService.getSubAccount(
            l_institution.getInstitutionCode(),
            l_request.accountInfo.branchCode,
            l_request.accountInfo.accountCode);

        //1.9.get������(Institution, String)

        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.10.validate�Ǘ��Ҏ戵�\����(������)
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();

        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);

        //1.11.get������(�m�F�������� : Date)
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.inpOrderDate);

        //1.12.��������ʔ���(�������, String)
        //������ʁF���N�G�X�g�f�[�^.�������.�������
        //����F���N�G�X�g�f�[�^.�������.���
        OrderTypeEnum l_orderTypeEnum = null;
        String l_strBondBuy = OrderTypeEnum.BOND_BUY.intValue() + "";
        String l_strBondSell = OrderTypeEnum.BOND_SELL.intValue() + "";
        
        if (l_strBondBuy.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_BUY;
        }
        else if (l_strBondSell.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_SELL;
        }

        WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(
            l_orderTypeEnum,
            l_request.orderInfo.dealType);

        //1.13create���������(java.util.Date, ��������ʔ���, ������)
        //���������𐶐�����
        //[����]
        //�������Fget������
        //��������ʔ���F����������������ʔ���
        //�������Fget������
        //���ϋ敪�F���N�G�X�g.�������.���ϋ敪
        //���X�FgetBranch
        WEB3BondExecuteDateInfo l_bondExecutionDateInfo = 
            (WEB3BondExecuteDateInfo)l_bondOrderManager.createBondExecutionDateInfo(
            l_datBizDate,
            l_bondOrderTypeJudge,
            l_bondProduct,
            l_request.orderInfo.settleDiv,
            l_branch);

        // 1.14.reset�������(�����, ���������, ��������ʔ���, ������, ���ϋ敪, ���X)
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo =
            l_bondHelperService.resetExecuteDateInfo(
                l_request.execInfo,
                l_bondExecutionDateInfo,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.orderInfo.settleDiv,
                l_branch);

        //1.15.validate����(������)
        l_bondExecutionDateInfo.validateExecuteDate(l_bondProduct);

        //1.16.validate����(double, ������)

        l_bondOrderManager.validateQuantity(
            Double.parseDouble(l_request.orderInfo.orderQuantity),
            l_bondProduct);

        //1.17validate�P��(������, String)
        //�������Fget������
        //�P���F���N�G�X�g.�������.�P��
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_request.orderInfo.price);

        //1.18validate�בփ��[�g(������, String)
        //[����]
        // �������Fget������
        // �בփ��[�g�F���N�G�X�g.�������.�בփ��[�g
        l_bondOrderManager.validateFxRate(l_bondProduct, l_request.orderInfo.fxRate);

        //1.19is�O�݌�( )
        boolean l_foreignCurrency = l_bondProduct.isForeignCurrency();

        //1.20is�O�݌�( )����true�̏ꍇ
        BigDecimal l_bdGetFxRate = null;
        if (l_foreignCurrency)
        {
            //1.20.1get�בփ��[�g(������, ��������ʔ���, String, BigDecimal, boolean)
            //   �@@�������Fget������
            //   �@@��������ʔ���F��������ʔ���I�u�W�F�N�g
            //   �@@���ϋ敪�F���N�G�X�g.�������.���ϋ敪
            //   �@@���͈בփ��[�g�F�@@���N�G�X�g.�������.�בփ��[�g
            //   �@@is���v�Z�F�@@true
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_bondOrderTypeJudge,
                l_request.orderInfo.settleDiv,
                new BigDecimal(l_request.orderInfo.fxRate),
                true);
        }

        // 1.21.calc��n���(��������ʔ���, BigDecimal, BigDecimal, BigDecimal, ������, ���������)
        //����n����v�Z���ʃI�u�W�F�N�g�𐶐�����
        //[����]
        //��������ʔ���F����������������ʔ���
        //���ʁF���N�G�X�g.�������.��������
        //�����P���F���N�G�X�g.�������.�����P��
        //�בփ��[�g�Fget�בփ��[�g�i��is�O�݌�()�̖߂�l == false�̏ꍇ�Anull���Z�b�g����B�j
        //�������Fget������
        //���������F���������������
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_bondBizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bondBizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge,
                new BigDecimal(l_request.orderInfo.orderQuantity),
                new BigDecimal(l_request.orderInfo.price),
                l_bdGetFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);

        //1.22.reset��n���(����n����v�Z����, �����, ������)
        //[reset��n���()]
        // ����n����v�Z���ʁFcalc��n����̖߂�l
        // �����F���N�G�X�g.�����
        // �������Fget������
        WEB3BondEstimatedPriceCalcResult l_calcResult =
            l_bondHelperService.resetEstimatedPrice(
                l_bondEstimatedPriceCalcResult,
                l_request.execInfo,
                l_bondProduct);

        //1.23is�C�O�s��c�Ɠ�(������, Date)
        //   �@@�������Fget������
        //   �@@�����������Fget������
        WEB3BondTradingTimeManagement l_bondTradingTimeManagement =
            new WEB3BondTradingTimeManagement();

        boolean l_soreignMarketBizDate =
            l_bondTradingTimeManagement.isForeignMarketBizDate(l_bondProduct, l_datBizDate);

        //1.24 is�C�O�s��c�Ɠ�����false�̏ꍇ
        if (!l_soreignMarketBizDate)
        {
            //1.24.1add�x���敪(String)
            l_calcResult.addWarningDiv(WEB3BondWarningDivDef.FRGN_ORDERBIZDATE_NOBIZDATE);
        }

        //1.25.create�g�����V�K�������e(Trader, ��������ʔ���, String, double, double, TaxTypeEnum, java.util.Date, String)
        //�g�����V�K�������e�I�u�W�F�N�g�𐶐�
        //[����]
        //�I�y���[�^�Fnull
        //��������ʔ���F����������������ʔ���I�u�W�F�N�g
        //�����R�[�h�iWEB3�j�F���N�G�X�g.�����R�[�h(WEB3)
        //���ʁF���N�G�X�g.�������.��������
        //�P���F���N�G�X�g.�������.�P��
        //�ŋ敪�F���N�G�X�g.�������.�ŋ敪
        //�@@���N�G�X�g.�������.�ŋ敪����null�̏ꍇ�A�@@TaxTypeEnum.NORMAL(��ʌ���)�Ƃ���
        //��n���Fcreate���������.��n��
        //���ϋ敪�F���N�G�X�g.�������.���ϋ敪
        String l_strNormal = TaxTypeEnum.NORMAL.intValue() + "";
        String l_strUndefined = TaxTypeEnum.UNDEFINED.intValue() + "";
        String l_strSpecial = TaxTypeEnum.SPECIAL.intValue() + "";
        String l_strSpecialWithhold = TaxTypeEnum.SPECIAL_WITHHOLD.intValue() + "";

        TaxTypeEnum l_taxTypeEnum = null;
        if ((l_request.orderInfo.taxType) == null ||
            l_strNormal.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;

        }
        else if (l_strUndefined.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;
        }
        else if (l_strSpecial.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        else if (l_strSpecialWithhold.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD;
        }

        WEB3BondNewOrderSpec l_bondNewOrderSpec = WEB3BondNewOrderSpec.createBondNewOrderSpec(
            null,
            l_bondOrderTypeJudge,
            l_request.productCode,
            Double.parseDouble(l_request.orderInfo.orderQuantity),
            Double.parseDouble(l_request.orderInfo.price),
            l_taxTypeEnum,
            l_bondExecutionDateInfo.getDeliveryDate(),
            l_request.orderInfo.settleDiv);

        //1.26. validate���V�K����(SubAccount, BondNewOrderSpec, ���������, ����n����v�Z����)
        //���V�K�������`�F�b�N����
        //[����]
        //�⏕�����Fget�⏕����
        //�g�����V�K�������e�Fcreate�g�����V�K�������e
        //���������F���������I�u�W�F�N�g
        //����n����v�Z���ʁF������������n����v�Z���ʃI�u�W�F�N�g
        l_bondOrderManager.validateBondNewOrder(
            l_subAccount,
            l_bondNewOrderSpec,
            l_bondExecuteDateInfo);

        //1.27���Ǘ��ҐV�K�����X�V�C���^�Z�v�^( )
        WEB3AdminBondNewOrderUpdateInterceptor l_bondNewOrderUpdateInterceptor =
            new WEB3AdminBondNewOrderUpdateInterceptor();

        //1.28�v���p�e�B�Z�b�g
        //���������C���^�Z�v�^�Ɉȉ��̃v���p�e�B���Z�b�g����.
        //���������Freset�������
        //����n����v�Z���ʁFreset��n���
        //�J�X�g�f�B�A���R�[�h�F���N�G�X�g�f�[�^.�����.�J�X�g�f�B�A��.�J�X�g�f�B�A���R�[�h()
        //�������Fget������
        //�Ǘ��ҁFgetInstanceFrom���O�C�����
        //���V�K�������e�Fcreate���V�K����
        l_bondNewOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_bondNewOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_calcResult);
        if (l_request.execInfo.custodianInfo != null)
        {
            l_bondNewOrderUpdateInterceptor.setCustodianCode(
                l_request.execInfo.custodianInfo.custodianCode);
        }
        l_bondNewOrderUpdateInterceptor.setBondProduct(l_bondProduct);
        l_bondNewOrderUpdateInterceptor.setAdministrator(l_admin);
        l_bondNewOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //1.29.���Ǘ��ҐV�K���X�V�C���^�Z�v�^( )
        WEB3AdminBondNewExecuteUpdateInterceptor l_executeUpdateInterceptor =
            new WEB3AdminBondNewExecuteUpdateInterceptor();

        //1.30.�v���p�e�B�Z�b�g
        //���������C���^�Z�v�^�Ɉȉ��̃v���p�e�B���Z�b�g����.
        //����n����v�Z���ʁFreset��n���
        //�������Fget������
        l_executeUpdateInterceptor.setBondEstimatedPriceCalcResult(l_calcResult);
        l_executeUpdateInterceptor.setBondProduct(l_bondProduct);

        //1.31.��������ʔ���.is���p��������false�@@���@@���ϋ敪�����~�݂̏ꍇ
        if ((!l_bondOrderTypeJudge.isSellOrder()) &&
            (WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_request.orderInfo.settleDiv)))
        {
            //1.31.1.���Ǘ��ҐV�K�����̓C���^�Z�v�^( )
            WEB3AdminBondOrderAndExecuteCommonInterceptor l_commonInterceptor = new WEB3AdminBondOrderAndExecuteCommonInterceptor();
            
            //1.31.2.���������C���^�Z�v�^�Ɉȉ��̃v���p�e�B���Z�b�g����B 
            //���Ǘ��ҐV�K�����X�V�C���^�Z�v�^�F���Ǘ��ҐV�K�����X�V�C���^�Z�v�^
            //���Ǘ��ҐV�K���X�V�C���^�Z�v�^�F���Ǘ��ҐV�K���X�V�C���^�Z�v�^
            l_commonInterceptor.setBondNewOrderUpdateInterceptor(l_bondNewOrderUpdateInterceptor);
            l_commonInterceptor.setBondNewExecuteUpdateInterceptor(l_executeUpdateInterceptor);

            //1.31.3.validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], �������e : Object[],
            //������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //�]�̓`�F�b�N
            //[validate����]��()�̈���]
            //�⏕�����Fget�⏕����
            //�������e�C���^�Z�v�^�F���Ǘ��ҐV�K�����C���^�Z�v�^
            //�������e�F�g�����V�K�������e
            //������ʁFOrderTypeEnum.��������
            //�]�͍X�V�t���O�Ffalse
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            Object[] l_objNewOrderUpdateInterceptor = {l_commonInterceptor};
            Object[] l_objNewOrderSpec = {l_bondNewOrderSpec};
    
            WEB3TPTradingPowerResult l_tPTradingPowerResult =
                l_service.validateTradingPower(
                l_subAccount,
                l_objNewOrderUpdateInterceptor,
                l_objNewOrderSpec,
                OrderTypeEnum.BOND_BUY,
                false);

            //1.31.4is����t���O( )
            //1.31.5.���b�Z�[�W is����t���O����false�̏ꍇ
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                //1.31.5.1.add�x���敪(String)
                //�x���敪��ǉ�
                //[����]
                //�x���敪�F�]�̓`�F�b�NNG
                l_calcResult.addWarningDiv(WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG);
            }

        }

        //1.32.to�����(���������, ����n����v�Z����, �J�X�g�f�B�A��, �g���������P��)
        //�����F
        //���������=create���������()�̖߂�l
        //�ireset�������()�ōĐݒ肵����̍��������j
        //����n����v�Z����=calc��n���()�̖߂�l
        //�ireset��n���()�ōĐݒ肵����̍���n����v�Z���ʁj
        //�J�X�g�f�B�A��=���N�G�X�g.�����.�J�X�g�f�B�A��
        //�g���������P�ʁ�null
        WEB3AdminBondOrderExecInfo l_bondOrderExecInfo = l_bondHelperService.toOrderExecInfo(
            l_bondExecuteDateInfo,
            l_calcResult,
            l_request.execInfo.custodianInfo,
            null);

        //1.33. to�ڋq���(�ڋq)
        //�ڋq�Fget�⏕����.get�ڋq()
        WEB3AdminBondAccountInfo l_bondAccountInfo =
            l_bondHelperService.toAccountInfo(l_subAccount.getMainAccount());

        //1.34.createNewOrderId( )
        long l_lngCreateNewOrderId = l_bondOrderManager.createNewOrderId();

        //1.35.create���X�|���X( )
        WEB3AdminBondExecConfirmResponse l_response =
            (WEB3AdminBondExecConfirmResponse)l_request.createResponse();

        //1.36.�v���p�e�B�Z�b�g
        //�v���p�e�B���Z�b�g����B
        //����ID��createNewOrderId
        //���͎���������get������
        //�����=to�����
        //�ڋq���=to�ڋq���
        l_response.id = l_lngCreateNewOrderId + "";
        l_response.inpOrderDate = l_datBizDate;
        l_response.execInfo = l_bondOrderExecInfo;
        l_response.accountInfo = l_bondAccountInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�Ǘ��ҐV�K������)<BR>
     * �V�K�����͊����������s���B<BR>
     * <BR>
     * �V�[�P���X�}�usubmit�Ǘ��ҐV�K�����́v���Q�ƁB
     * ==========================================================
     * 1.7. ���X�����݂��Ȃ��ꍇ�A��O���X���[����
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01386<BR>
     * <BR>
     * ==========================================================
     * <BR>
     * @@param l_request - (�Ǘ��ҐV�K��芮�����N�G�X�g)
     * @@return WEB3AdminBondExecCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B6229D0222
     */
    protected WEB3AdminBondExecCompleteResponse submitExecuteInput(
        WEB3AdminBondExecCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitExecuteInput(WEB3AdminBondExecCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1. validate( )
        l_request.validate();

        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3.get�،����( )
        Institution l_institution = l_admin.getInstitution();

        //1.4.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_INPUT, true);

        //1.5. validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������`�F�b�N����
        //[validate����p�X���[�h()�̈���]
        //�p�X���[�h�F���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        //1.6.getBranch(arg0 : Institution, arg1 : �_���r���[::java::lang::String)
        //���X�I�u�W�F�N�g���擾
        //[get���X()�̈���]
        //�،���ЃR�[�h�Fget�،����
        //���X�R�[�h�F���N�G�X�g.�ڋq���.get���X�R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getBranch(
                l_institution,
                l_request.accountInfo.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.7.���X�����݂��Ȃ��ꍇ�A��O���X���[����
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����X�f�[�^�Ȃ�");
        }

        //1.8.validate���X����(���X�R�[�h : String)
        //���Y�Ǘ��҂��A�w��̕��X����舵���邩���`�F�b�N����B
        //[validate���X����()�̈���]
        //���X�R�[�h�F���N�G�X�g�f�[�^.�ڋq���.���X�R�[�h
        l_admin.validateBranchPermission(
            l_request.accountInfo.branchCode);

        //1.9.get�⏕����(String, String, String)
        //�ڋq�̕⏕�������擾
        //[get�⏕����()�̈���]
        //�،���ЃR�[�h:�،���ЃR�[�h�F�擾�����،���ЃI�u�W�F�N�g.�،���ЃR�[�h()
        //���X�R�[�h:���N�G�X�g.�ڋq���.���X�R�[�h
        //�ڋq�R�[�h:���N�G�X�g.�ڋq���.�ڋq�R�[�h
        WEB3AdminBondHelperService l_bondHelperService =
            (WEB3AdminBondHelperService)Services.getService(
                WEB3AdminBondHelperService.class);

        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)l_bondHelperService.getSubAccount(
                l_institution.getInstitutionCode(),
                l_request.accountInfo.branchCode,
                l_request.accountInfo.accountCode);

        //1.10.get������(Institution, String)
        //�������I�u�W�F�N�g���擾
        //[get������()�̈���]
        //�،���ЁFget�،����
        //�����R�[�h(WEB3)�F���N�G�X�g�f�[�^.�����R�[�h(WEB3)
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct =
                (WEB3BondProduct)l_bondProductManager.getBondProduct(
                    l_institution,
                    l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.11.validate�Ǘ��Ҏ戵�\����(������)
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();

        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);

        //1.12.get������(�m�F�������� : Date)
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.inpOrderDate);

        //1.13 ��������ʔ���(�������, String)
        //��������ʔ���I�u�W�F�N�g�𐶐�
        //[��������ʔ���()�̈���]
        //������ʁF���N�G�X�g�f�[�^.�������.�������
        //����F���N�G�X�g�f�[�^.�������.���
        OrderTypeEnum l_orderTypeEnum = null;
        String l_strBondBuy = OrderTypeEnum.BOND_BUY.intValue() + "";
        String l_strBondSell = OrderTypeEnum.BOND_SELL.intValue() + "";
        
        if (l_strBondBuy.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_BUY;
        }
        else if (l_strBondSell.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_SELL;
        }

        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                l_orderTypeEnum,
                l_request.orderInfo.dealType);

        //1.14.create���������(java.util.Date, ��������ʔ���, ������)
        //���������𐶐�����
        //[����]
        //�������Fget������
        //��������ʔ���F����������������ʔ���
        //�������Fget������
        //���ϋ敪�F���N�G�X�g.�������.���ϋ敪
        //���X�FgetBranch
        WEB3BondExecuteDateInfo l_bondExecutionDateInfo =
            (WEB3BondExecuteDateInfo)l_bondOrderManager.createBondExecutionDateInfo(
                l_datBizDate,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.orderInfo.settleDiv,
                l_branch);

        // 1.15.reset�������(�����, ���������, ��������ʔ���, ������, ���ϋ敪, ���X)
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo =
            l_bondHelperService.resetExecuteDateInfo(
                l_request.execInfo,
                l_bondExecutionDateInfo,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.orderInfo.settleDiv,
                l_branch);

        //1.16.validate����(������)
        l_bondExecutionDateInfo.validateExecuteDate(l_bondProduct);

        //1.17.validate����(double, ������)
        l_bondOrderManager.validateQuantity(Double.parseDouble(
            l_request.orderInfo.orderQuantity),
            l_bondProduct);

        //1.18validate�P��(������, String)
        //�������Fget������
        //�P���F���N�G�X�g.�������.�P��
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_request.orderInfo.price);

        //1.19validate�בփ��[�g(������, BigDecimal)
        //[����]
        // �������Fget������
        // �בփ��[�g�F���N�G�X�g.�������.�בփ��[�g
        l_bondOrderManager.validateFxRate(l_bondProduct, l_request.orderInfo.fxRate);

        //1.20is�O�݌�( )
        boolean l_foreignCurrency = l_bondProduct.isForeignCurrency();

        //1.21is�O�݌�( )����true�̏ꍇ
        BigDecimal l_bdGetFxRate = null;
        if (l_foreignCurrency)
        {
            //1.21.1get�בփ��[�g(������, ��������ʔ���, String, BigDecimal, boolean)
            //   �@@�������Fget������
            //   �@@��������ʔ���F��������ʔ���I�u�W�F�N�g
            //   �@@���ϋ敪�F���N�G�X�g.�������.���ϋ敪
            //   �@@���͈בփ��[�g�F�@@���N�G�X�g.�������.���בփ��[�g
            //   �@@is���v�Z�F�@@true
            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_bondOrderTypeJudge,
                l_request.orderInfo.settleDiv,
                new BigDecimal(l_request.orderInfo.fxRate),
                true);
        }

        //1.22.calc��n���(��������ʔ���, BigDecimal,
        //BigDecimal, BigDecimal, ������, ���������)
        //����n����v�Z���ʃI�u�W�F�N�g�𐶐�����
        //[����]
        //��������ʔ���F����������������ʔ���
        //���ʁF���N�G�X�g.�������.��������
        //�����P���F���N�G�X�g.�������.�����P��
        //�בփ��[�g�Fget�בփ��[�g�i��is�O�݌�()�̖߂�l == false�̏ꍇ�Anull���Z�b�g����B�j
        //�������Fget������
        //���������F���������������
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_bondBizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bondBizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge,
                new BigDecimal(l_request.orderInfo.orderQuantity),
                new BigDecimal(l_request.orderInfo.price),
                l_bdGetFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);


        //1.23.reset��n���(����n����v�Z����, �����, ������)
        //[reset��n���()]
        // ����n����v�Z���ʁFcalc��n���
        // �����F���N�G�X�g.�����
        // �������Fget������
        WEB3BondEstimatedPriceCalcResult l_calcResult =
            l_bondHelperService.resetEstimatedPrice(
                l_bondEstimatedPriceCalcResult,
                l_request.execInfo,
                l_bondProduct);

        //1.24is�C�O�s��c�Ɠ�(������, Date)
        //   �@@�������Fget������
        //   �@@�����������Fget������
        WEB3BondTradingTimeManagement l_bondTradingTimeManagement =
            new WEB3BondTradingTimeManagement();

        boolean l_soreignMarketBizDate =
            l_bondTradingTimeManagement.isForeignMarketBizDate(l_bondProduct, l_datBizDate);

        //1.25 is�C�O�s��c�Ɠ�����false�̏ꍇ
        if (!l_soreignMarketBizDate)
        {
            //1.25.1add�x���敪(String)
            l_calcResult.addWarningDiv(WEB3BondWarningDivDef.FRGN_ORDERBIZDATE_NOBIZDATE);
        }

        //1.26.create�g�����V�K�������e(Trader, ��������ʔ���,
        //String, double, double, TaxTypeEnum, java.util.Date, String)
        //�g�����V�K�������e�I�u�W�F�N�g�𐶐�
        //[����]
        //�I�y���[�^�Fnull
        //��������ʔ���F����������������ʔ���I�u�W�F�N�g
        //�����R�[�h�iWEB3�j�F���N�G�X�g.�����R�[�h(WEB3)
        //���ʁF���N�G�X�g.�������.��������
        //�P���F���N�G�X�g.�������.�P��
        //�ŋ敪�F���N�G�X�g.�������.�ŋ敪
        //�@@���N�G�X�g.�������.�ŋ敪����null�̏ꍇ�A�@@TaxTypeEnum.NORMAL(��ʌ���)�Ƃ���
        //��n���Fcreate���������.��n��
        //���ϋ敪�F���N�G�X�g.�������.���ϋ敪
        String l_strNormal = TaxTypeEnum.NORMAL.intValue() + "";
        String l_strUndefined = TaxTypeEnum.UNDEFINED.intValue() + "";
        String l_strSpecial = TaxTypeEnum.SPECIAL.intValue() + "";
        String l_strSpecialWithhold = TaxTypeEnum.SPECIAL_WITHHOLD.intValue() + "";

        TaxTypeEnum l_taxTypeEnum = null;
        if ((l_request.orderInfo.taxType) == null ||
            l_strNormal.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;

        }
        else if (l_strUndefined.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;
        }
        else if (l_strSpecial.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        else if (l_strSpecialWithhold.equals(l_request.orderInfo.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD;
        }

        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpec(
            null,
            l_bondOrderTypeJudge,
            l_request.productCode,
            Double.parseDouble(l_request.orderInfo.orderQuantity),
            Double.parseDouble(l_request.orderInfo.price),
            l_taxTypeEnum,
            l_bondExecutionDateInfo.getDeliveryDate(),
            l_request.orderInfo.settleDiv);

        //1.27.validate���V�K����(SubAccount, BondNewOrderSpec,
        //���������, ����n����v�Z����)
        //���V�K�������`�F�b�N����
        //[����]
        //�⏕�����Fget�⏕����
        //�g�����V�K�������e�Fcreate�g�����V�K�������e
        //���������Freset���������
        //����n����v�Z���ʁFreset��n���
        l_bondOrderManager.validateBondNewOrder(
            l_subAccount,
            l_bondNewOrderSpec,
            l_bondExecuteDateInfo);

        //1.28.���Ǘ��ҐV�K�����X�V�C���^�Z�v�^( )
        WEB3AdminBondNewOrderUpdateInterceptor l_bondNewOrderUpdateInterceptor =
            new WEB3AdminBondNewOrderUpdateInterceptor();

        //1.29.�v���p�e�B�Z�b�g
        //���������C���^�Z�v�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //���������Freset�������
        //����n����v�Z���ʁFreset��n���
        //�J�X�g�f�B�A���R�[�h�F���N�G�X�g�f�[�^.�����.�J�X�g�f�B�A��.�J�X�g�f�B�A���R�[�h()
        //�������Fget������
        //�Ǘ��ҁFgetInstanceFrom���O�C�����
        //���V�K�������e�Fcreate���V�K����
        l_bondNewOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_bondNewOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_calcResult);
        if (l_request.execInfo.custodianInfo != null)
        {
            l_bondNewOrderUpdateInterceptor.setCustodianCode(
                l_request.execInfo.custodianInfo.custodianCode);
        }
        l_bondNewOrderUpdateInterceptor.setBondProduct(l_bondProduct);
        l_bondNewOrderUpdateInterceptor.setAdministrator(l_admin);
        l_bondNewOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //1.30.���Ǘ��ҐV�K���X�V�C���^�Z�v�^( )
        WEB3AdminBondNewExecuteUpdateInterceptor l_executeUpdateInterceptor =
            new WEB3AdminBondNewExecuteUpdateInterceptor();

        //1.31.�v���p�e�B�Z�b�g
        //���������C���^�Z�v�^�Ɉȉ��̃v���p�e�B���Z�b�g����.
        //����n����v�Z���ʁFreset��n���
        //�������Fget������
        l_executeUpdateInterceptor.setBondEstimatedPriceCalcResult(l_calcResult);
        l_executeUpdateInterceptor.setBondProduct(l_bondProduct);

        //1.32.��������ʔ���.is���p��������false�@@���@@���ϋ敪�����~�݂̏ꍇ
        WEB3TPTradingPowerResult l_tPTradingPowerResult = null;
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        if ((!l_bondOrderTypeJudge.isSellOrder())  &&
            (WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_request.orderInfo.settleDiv)))
        {
            //���Ǘ��ҐV�K�����̓C���^�Z�v�^( )
            WEB3AdminBondOrderAndExecuteCommonInterceptor l_commonInterceptor = new WEB3AdminBondOrderAndExecuteCommonInterceptor();
            
            //���������C���^�Z�v�^�Ɉȉ��̃v���p�e�B���Z�b�g����B 
            //���Ǘ��ҐV�K�����X�V�C���^�Z�v�^�F���Ǘ��ҐV�K�����X�V�C���^�Z�v�^
            //���Ǘ��ҐV�K���X�V�C���^�Z�v�^�F���Ǘ��ҐV�K���X�V�C���^�Z�v�^
            l_commonInterceptor.setBondNewOrderUpdateInterceptor(l_bondNewOrderUpdateInterceptor);
            l_commonInterceptor.setBondNewExecuteUpdateInterceptor(l_executeUpdateInterceptor);
            
            //validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],
            //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //�]�̓`�F�b�N
            //[validate����]��()�̈���]
            //�⏕�����Fget�⏕����
            //�������e�C���^�Z�v�^�F���Ǘ��ҐV�K�����̓C���^�Z�v�^
            //�������e�F�g�����V�K�������e
            //������ʁFOrderTypeEnum.��������
            //�]�͍X�V�t���O�Ftrue
            Object[] l_objNewOrderUpdateInterceptor = {l_commonInterceptor};
            Object[] l_objNewOrderSpec = {l_bondNewOrderSpec};

            l_tPTradingPowerResult =
                l_service.validateTradingPower(
                l_subAccount,
                l_objNewOrderUpdateInterceptor,
                l_objNewOrderSpec,
                OrderTypeEnum.BOND_BUY,
                true);
            //is����t���O( )
            //is����t���O����false�̏ꍇ
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                //1.30.3.1.add�x���敪(String)
                //�x���敪��ǉ�
                //[����]
                //�x���敪�F�]�̓`�F�b�NNG
                l_calcResult.addWarningDiv(
                    WEB3BondWarningDivDef.TRADE_POWER_CHECK_NG);
            }
        }
        //1.33.setThreadLocalPersistenceEventInterceptor(arg0 :
        //BondOrderManagerPersistenceEventInterceptor)
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_bondNewOrderUpdateInterceptor);

        //1.34.submitNewOrder(arg0 : SubAccount, arg1 : ProductTypeEnum,
        //arg2 : NewOrderSpec, arg3 : long, a
        //rg4 : �_���r���[::java::lang::String, arg5 : boolean)
        //[submitNewOrder�̈���]
        //�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g
        //�����^�C�v�F ProductTypeEnum.��
        //�V�K�������e�F ���������g�����V�K�������e
        //����ID�F ���N�G�X�g�f�[�^.����ID
        //����p�X���[�h�F
        //����p�X���[�h�ݒ� == �f����p�X���[�h�g�p�f�̏ꍇ
        //�擾�����⏕����.get�ڋq().getTradingPassword�i�j�̖߂�l��
        //WEB3Crypt().decrypt()�ŕ�����������
        //����p�X���[�h�ݒ� == �fDEFAULT�f�̏ꍇ�A���N�G�X�g�f�[�^.�Ïؔԍ�
        //is�����R���ȗ��F true
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_adminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_adminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        String l_strAttribute = (String)l_mapAttributes.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

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
            l_strTradingPassword = l_web3Crypt.decrypt(
                l_subAccount.getMainAccount().getTradingPassword());
        }
        l_bondOrderManager.submitNewOrder(
            l_subAccount,
            ProductTypeEnum.BOND,
            l_bondNewOrderSpec,
            Long.parseLong(l_request.id),
            l_strTradingPassword,
            true);

        //1.35.���Ǘ��Ғ�����t�X�V�C���^�Z�v�^( )
        WEB3AdminBondOrderAcceptUpdateInterceptor l_acceptUpdateInterceptor =
            new WEB3AdminBondOrderAcceptUpdateInterceptor();

        //1.36.accept�V�K����(long, ���Ǘ��҃f�t�H���g�C���^�Z�v�^)(
        //�V�K������t������
        //[����]
        //����ID�F ���N�G�X�g�f�[�^.����ID
        //���Ǘ��҃f�t�H���g�C���^�Z�v�^�F ���Ǘ��Ғ�����t�X�V�C���^�Z�v�^
        WEB3AdminBondExecuteNotifyService l_bondExecuteNotifyService =
            (WEB3AdminBondExecuteNotifyService)Services.getService(
                WEB3AdminBondExecuteNotifyService.class);
        l_bondExecuteNotifyService.acceptNewOrder(
            Long.parseLong(l_request.id),
            l_acceptUpdateInterceptor);

        //1.37.get�������P��By����ID(long)
        //[����]
        //����ID�F ���N�G�X�g�f�[�^.����ID
        WEB3BondOrderUnit l_bondOrderUnit =
            l_bondOrderManager.getBondOrderUnitByOrderId(
                Long.parseLong(l_request.id));

        //1.38.notify���(BondOrderUnit, ���Ǘ��҃f�t�H���g�C���^�Z�v�^)
        //��菈��������
        //[����]
        //�������P�ʁF get�������P��By����ID
        //���Ǘ��҃f�t�H���g�C���^�Z�v�^�F ���Ǘ��ҐV�K���X�V�C���^�Z�v�^
        l_bondExecuteNotifyService.notifyExecute(
            l_bondOrderUnit,
            l_executeUpdateInterceptor);

        //1.39.�iis����t���O�I��null ���@@is����t���O����false�j ���́@@�i��������ʔ���.is���p��������true�@@���@@���ϋ敪�����~�݁j�̏ꍇ
        if((l_tPTradingPowerResult !=null && !l_tPTradingPowerResult.isResultFlg())
            ||
            (l_bondOrderTypeJudge.isSellOrder()  &&
            WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_request.orderInfo.settleDiv)))
        {
            //1.39.1 �]�͍Čv�Z(�⏕���� : �⏕����)
            l_service.reCalcTradingPower(l_subAccount);  
        }

        //1.40.create���X�|���X( )
        WEB3AdminBondExecCompleteResponse l_response =
            (WEB3AdminBondExecCompleteResponse)l_request.createResponse();

        //1.41.�v���p�e�B�Z�b�g
        //�v���p�e�B���Z�b�g����B
        //�X�V���ԁF���ݓ���
        //���ʔԍ��F���N�G�X�g�f�[�^.����ID
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        l_response.orderActionId = l_request.id;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (calc��n���)<BR>
     * ��n����v�Z���������s����B<BR>
     * <BR>
     * �V�[�P���X�ucalc��n����v���Q�ƁB
     * @@param l_request - (�Ǘ��ҐV�K���v�Z���N�G�X�g)
     * @@return WEB3AdminBondExecCalculateResponse
     * @@throws WEB3BaseException
     * @@roseuid 44BB49210197
     */
    protected WEB3AdminBondExecCalculateResponse calcEstimatedPrice(
            WEB3AdminBondExecCalculateRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcEstimatedPrice(WEB3AdminBondExecCalculateRequest)";
        log.entering(STR_METHOD_NAME);
        //1.1. validate( )
        l_request.validate();

        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_INPUT, true);

        //1.4.validate���X����(���X�R�[�h : String)
        String l_strBranchCode = null;
        if (l_request.accountInfo.branchCode != null)
        {
            l_strBranchCode = l_request.accountInfo.branchCode;
        }
        else
        {
            l_strBranchCode = l_admin.getBranchCode();
        }
        l_admin.validateBranchPermission(l_strBranchCode);

        //1.5.get�،����( )
        Institution l_institution = l_admin.getInstitution();

        //1.6.getBranch(arg0 : Institution, arg1 : �_���r���[::java::lang::String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getBranch(
                l_institution,
                l_strBranchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.7. ���X�����݂��Ȃ��ꍇ�A��O���X���[����
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����X�f�[�^�Ȃ�");
        }

        WEB3AdminBondHelperService l_bondHelperService =
            (WEB3AdminBondHelperService)Services.getService(WEB3AdminBondHelperService.class);
        //1.8.get������(Institution, String)
        //�����������I�u�W�F�N�g���擾����
        //[get������()�̈���]
        //�،���ЁF�擾�����،���ЃI�u�W�F�N�g
        //�����R�[�h�F���N�G�X�g�f�[�^.�����R�[�h(WEB3)
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.9.validate�Ǘ��Ҏ戵�\����(������)
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();

        l_bondOrderManager.validateAdminTradingPossibleProduct(l_bondProduct);

        //1.10.get������(�m�F�������� : Date)
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.inpOrderDate);

        //1.11.��������ʔ���(�������, String)
        //��������ʔ���I�u�W�F�N�g�𐶐�
        //[����]
        //������ʁF���N�G�X�g.�������.������ʂ�OrderTypeEnum�ɕϊ�
        //����F���N�G�X�g.�������.���
        WEB3AdminBondOrderInfo l_bondOrderInfo = l_request.orderInfo;

        OrderTypeEnum l_orderTypeEnum = null;
        String l_strBondBuy = OrderTypeEnum.BOND_BUY.intValue() + "";
        String l_strBondSell = OrderTypeEnum.BOND_SELL.intValue() + "";
        
        if (l_strBondBuy.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_BUY;
        }
        else if (l_strBondSell.equals(l_request.orderInfo.tradingType))
        {
            l_orderTypeEnum = OrderTypeEnum.BOND_SELL;
        }
        
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(
            l_orderTypeEnum,
            l_bondOrderInfo.dealType);

        //1.12.create���������(java.util.Date, ��������ʔ���, ������)
        //���������𐶐�����
        //[����]
        //�������Fget������
        //��������ʔ���F����������������ʔ���
        //�������Fget������
        //���ϋ敪�F���N�G�X�g.�������.���ϋ敪
        //���X�FgetBranch
        WEB3BondExecuteDateInfo l_bondExecutionDateInfo = (
            WEB3BondExecuteDateInfo)l_bondOrderManager.createBondExecutionDateInfo(
                l_datBizDate,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.orderInfo.settleDiv,
                l_branch);

        //1.13.reset�������(�����, ���������, ��������ʔ���, ������, ���ϋ敪, ���X)
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = l_bondHelperService.resetExecuteDateInfo(
            l_request.execInfo,
            l_bondExecutionDateInfo,
            l_bondOrderTypeJudge,
            l_bondProduct,
            l_request.orderInfo.settleDiv,
            l_branch);

        //1.14 validate����(������)
        l_bondExecuteDateInfo.validateExecuteDate(l_bondProduct);

        // 1.15.validate����(double, ������)
        //���ʁF���N�G�X�g.�����.��萔�ʁ@@!= null�̏ꍇ
        //�@@�@@���ʁ����N�G�X�g.�����.��萔��
        //�@@��L�ȊO�̏ꍇ
        //�@@�@@���ʁ����N�G�X�g.�������.��������
        String l_strQuantity = null;
        if (l_request.execInfo.execFaceAmount != null)
        {
            l_strQuantity = l_request.execInfo.execFaceAmount;
        }
        else
        {
            l_strQuantity = l_request.orderInfo.orderQuantity;
        }
        l_bondOrderManager.validateQuantity(
            Double.parseDouble(l_strQuantity),
            l_bondProduct);

        //1.16validate�P��(������, String)
        //�������Fget������
        //�P���F���N�G�X�g.�����.���P���@@!= null�̏ꍇ
        //�@@�@@�P�������N�G�X�g.�����.���P��
        //�@@��L�ȊO�̏ꍇ
        //�@@�@@�P�������N�G�X�g.�������.�P��
        String l_strPrice = null;
        if (l_request.execInfo.execPrice != null)
        {
            l_strPrice = l_request.execInfo.execPrice;
        }
        else
        {
            l_strPrice = l_request.orderInfo.price;
        }
        l_bondOrderManager.validateExecPrice(l_bondProduct, l_strPrice);

        //1.17.validate�בփ��[�g(������, BigDecimal)
        //[����]
        // �������Fget������
        // �בփ��[�g�F
        // ���N�G�X�g.�����.���בփ��[�g�@@!= null�̏ꍇ
        // �בփ��[�g�����N�G�X�g.�����.���בփ��[�g
        // ��L�ȊO�̏ꍇ
        // �בփ��[�g�����N�G�X�g.�������.�בփ��[�g
        String l_strFxRate = null;

        if (l_request.execInfo.execFxRate != null)
        {
            l_strFxRate = l_request.execInfo.execFxRate;
        }
        else
        {
            l_strFxRate = l_request.orderInfo.fxRate;
        }

        l_bondOrderManager.validateFxRate(
            l_bondProduct, l_strFxRate);

        //1.18.is�O�݌�( )
        boolean l_foreignCurrency = l_bondProduct.isForeignCurrency();

        //1.19.is�O�݌�( )����true
        BigDecimal l_bdGetFxRate = null;
        if (l_foreignCurrency)
        {
            //1.19.1.get�בփ��[�g(������, ��������ʔ���, String, BigDecimal, boolean)
            //�בփ��[�g���擾����B
            //[����]
            //�@@�������Fget������
            //�@@��������ʔ���F��������ʔ���I�u�W�F�N�g
            //�@@���ϋ敪�F���N�G�X�g.�������.���ϋ敪
            //�@@���͈בփ��[�g�F���N�G�X�g.�����.���בփ��[�g�@@!= null�̏ꍇ
            //�@@�@@�בփ��[�g�����N�G�X�g.�����.���בփ��[�g
            //�@@��L�ȊO�̏ꍇ
            //�@@�@@�בփ��[�g�����N�G�X�g.�������.�בփ��[�g
            //�@@is���v�Z�F�@@true
            BigDecimal l_bdFxRate = null;

            if (l_request.execInfo.execFxRate != null)
            {
                l_bdFxRate = new BigDecimal(l_request.execInfo.execFxRate);
            }
            else
            {
                l_bdFxRate = new BigDecimal(l_request.orderInfo.fxRate);
            }

            l_bdGetFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct,
                l_bondOrderTypeJudge,
                l_request.orderInfo.settleDiv,
                l_bdFxRate,
                true);
        }

        //1.20.calc��n���(��������ʔ���, BigDecimal, BigDecimal, BigDecimal, ������, ���������)
        //����n����v�Z���ʃI�u�W�F�N�g�𐶐�����
        //[����]
        //��������ʔ���F����������������ʔ���
        //���ʁF
        //�@@���N�G�X�g.�����.��萔�ʁ@@!= null�̏ꍇ
        //�@@�@@���ʁ����N�G�X�g.�����.��萔��
        //�@@��L�ȊO�̏ꍇ
        //�@@�@@���ʁ����N�G�X�g.�������.��������
        //�P���F
        //�@@���N�G�X�g.�����.���P���@@!= null�̏ꍇ
        //�@@�@@�P�������N�G�X�g.�����.���P��
        //�@@��L�ȊO�̏ꍇ
        //�@@�@@�P�������N�G�X�g.�������.�P��
        //�בփ��[�g�Fget�בփ��[�g�i��is�O�݌�()�̖߂�l == false�̏ꍇ�Anull���Z�b�g����B�j
        //�������Fget������
        //���������F���������������
        String l_dblQuantity = "0";
        String l_dblPrice = "0";

        if (l_request.execInfo.execFaceAmount != null)
        {
            l_dblQuantity = l_request.execInfo.execFaceAmount;
        }
        else if (l_bondOrderInfo.orderQuantity != null)
        {
            l_dblQuantity = l_bondOrderInfo.orderQuantity;
        }

        if (l_request.execInfo.execPrice != null)
        {
            l_dblPrice = l_request.execInfo.execPrice;
        }
        else if (l_bondOrderInfo.price != null)
        {
            l_dblPrice = l_bondOrderInfo.price;
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondBizLogicProvider l_bondBizLogicProvider =
            (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bondBizLogicProvider.calcEstimatedPrice(
            l_bondOrderTypeJudge,
            new BigDecimal(l_dblQuantity),
            new BigDecimal(l_dblPrice),
            l_bdGetFxRate,
            l_bondProduct,
            l_bondExecuteDateInfo);

        //1.21.to�����(���������, ����n����v�Z����, �J�X�g�f�B�A��, �g���������P��)
        //�����F
        //���������=create���������()�̖߂�l
        //�ireset�������()�ōĐݒ肵����̍��������j
        //����n����v�Z����=calc��n���()�̖߂�l
        //�J�X�g�f�B�A��=���N�G�X�g.�����.�J�X�g�f�B�A��
        //�g���������P�ʁ�null
        WEB3AdminBondOrderExecInfo l_bondOrderExecInfo = l_bondHelperService.toOrderExecInfo(
            l_bondExecuteDateInfo,
            l_bondEstimatedPriceCalcResult,
            l_request.execInfo.custodianInfo,
            null);

        //1.22.create���X�|���X( )
        WEB3AdminBondExecCalculateResponse l_response =
            (WEB3AdminBondExecCalculateResponse)l_request.createResponse();
        //1.23.�v���p�e�B�Z�b�g
        //�����=to�����
        l_response.execInfo = l_bondOrderExecInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
