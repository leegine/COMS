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
filename	WEB3AdminBondProductRegisterServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Җ����o�^�T�[�r�XImpl(WEB3AdminBondProductRegisterServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ����(���u) �V�K�쐬
                   2006/10/09 ��іQ(���u) ���f��No.106.107.121 �c�a�X�V�d�lNo.017 
Revesion History : 2008/08/13 �g�C�� (���u) �d�l�ύX�E���f��260
Revesion History : 2009/07/24 ���g (���u) �d�l�ύX�E���f��261
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.data.BondAutoExecLimitActionRow;
import webbroker3.bd.data.BondProductCouponRow;
import webbroker3.bd.define.WEB3BondRecruitAcceptDivDef;
import webbroker3.bd.define.WEB3BondRecruitInvitationFormDef;
import webbroker3.bd.message.WEB3AdminBondAutoExecLimitHistoryUnit;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondProductBasicInfo;
import webbroker3.bd.message.WEB3AdminBondProductCouponUnit;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductUpdateInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3AdminBondProductRegisterService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AutoExecDivDef;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.define.WEB3TradingTimeCheckDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���Ǘ��Җ����o�^�T�[�r�XImpl)<BR>
 * ���Ǘ��Җ����o�^�T�[�r�X�@@�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminBondProductRegisterServiceImpl 
    implements WEB3AdminBondProductRegisterService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegisterServiceImpl.class);
    /**
     * @@roseuid 44E3362E01F4
     */
    public WEB3AdminBondProductRegisterServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��ҍ������o�^�����{����B <BR>
     * <BR>
     * �V�[�P���X�}�u(��)�����o�^execute�v���Q��
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B601CA0104
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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
        //1.1.��_request���������o�^���̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminBondProductRegistInputRequest)
        {
            //1.1.1.input�����o�^(�Ǘ��ҍ������o�^���̓��N�G�X�g)
            l_response = inputProductRegister((WEB3AdminBondProductRegistInputRequest) l_request);         
        }
        
        //1.2. ��_request���������o�^�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminBondProductRegistConfirmRequest)
        {
            //1.2.1.validate�����o�^(�Ǘ��ҍ������o�^�m�F���N�G�X�g)
            l_response = validateProductRegister((WEB3AdminBondProductRegistConfirmRequest) l_request);         
        }
        
        //1.3.��_request���������o�^�������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminBondProductRegistCompleteRequest)
        {
            //1.3.1.submit�����o�^(�Ǘ��ҍ������o�^�������N�G�X�g)
            l_response = submitProductRegister((WEB3AdminBondProductRegistCompleteRequest) l_request);         
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
     * (input�����o�^)<BR>
     * �������o�^���͏������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(��)input�����o�^�v���Q��
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B601CA0123
     */
    protected WEB3AdminBondProductRegistInputResponse inputProductRegister(
        WEB3AdminBondProductRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " inputProductRegister(WEB3AdminBondProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N������  
        //[validate����()�Ɏw�肷�����]  
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�������Ǘ� 
        //is�X�V�F�@@false 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            false);
        
        //1.4.get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5.get������(Institution, String)
        //���������擾����B 
        //[����]  
        //�،���ЁFget�،���� 
        //�����R�[�h(WEB3)�F���N�G�X�g�f�[�^.�����R�[�h(WEB3)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();   
        WEB3BondProduct l_bondProduct = null;
        try
        {
            l_bondProduct = (WEB3BondProduct) l_bondProductManager.getBondProduct(
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
        //1.6.��������{���
        WEB3AdminBondProductBasicInfo l_productBasicInfo = new WEB3AdminBondProductBasicInfo();
        
        //1.7.�v���p�e�B�Z�b�g
        //���e���ڂ�NULL�łȂ��ꍇ�̂݁A�Z�b�g����B
        // �����R�[�h(WEB3) = ������.get�����R�[�h�iWEB3�j()
        BondProductRow l_productRow = (BondProductRow) l_bondProduct.getDataSourceObject();
        if (l_productRow.getProductCodeIsSet())
        {
            l_productBasicInfo.productCode = l_bondProduct.getProductCode();
        }
        // ���s�� = ������.getIssueDate() 
        if (l_productRow.getIssueDateIsSet())
        {
            l_productBasicInfo.issueDate = l_bondProduct.getIssueDate();
        }
        // ���s���i = ������.getIssuePrice()
        if (l_productRow.getIssuePriceIsSet())
        {
            l_productBasicInfo.issuePrice = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getIssuePrice());
        }
        // ���s�z = ������.get���s�z()
        if (!l_productRow.getIssueAmountIsNull())
        {
            l_productBasicInfo.issueAmount = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getIssueAmount());
        }
        // �P�ʊz�� = ������. getParValue()
        if (l_productRow.getParValueIsSet())
        {
            l_productBasicInfo.parValue = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getParValue());
        }
        // ���ғ� = ������.getMaturityDate() 
        if (l_productRow.getMaturityDateIsSet())
        {
            l_productBasicInfo.maturityDate = l_bondProduct.getMaturityDate();
        }
        // ���҉��i = ������.getRedemptionPrice() 
        if (l_productRow.getRedemptionPriceIsSet())
        {
            l_productBasicInfo.redemptionPrice = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getRedemptionPrice());
        }
        // ���t�^�C�v = ������.getCouponType()
        if (l_productRow.getCouponTypeIsSet())
        {
            l_productBasicInfo.couponType = l_bondProduct.getCouponType().intValue() + "";
        }
        // ���� = ������.getCoupon()
        if (l_productRow.getCouponIsSet())
        {
            l_productBasicInfo.coupon = WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());
        }
        // �N�ԗ����� = ������.getYearlyInterestPayments()  
        if (l_productRow.getYearlyInterestPaymentsIsSet())
        {
            l_productBasicInfo.yearlyInterestPayments = l_bondProduct.getYearlyInterestPayments() + "";
        }
        // ������1 = ������.get������1() 
        if (l_bondProduct.getInterestPaymentDay1()  != null)
        {
            l_productBasicInfo.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();
        }
        // ������2 = ������.get������2()
        if (l_bondProduct.getInterestPaymentDay2() != null)
        {
            l_productBasicInfo.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();
        }
        // HOST������1 = ������.getHOST������1() 
        if (l_bondProduct.getHostProductName1() != null)
        {
            l_productBasicInfo.hostProductName1 = l_bondProduct.getHostProductName1();
        }
        // HOST������2 = ������.getHOST������2()
        if (l_bondProduct.getHostProductName2() != null)
        {
            l_productBasicInfo.hostProductName2 = l_bondProduct.getHostProductName2();
        }
        // HOST�ȗ������� = ������.getHOST�ȗ�������()
        if (l_bondProduct.getHostShortProductName() != null)
        {
            l_productBasicInfo.hostShortProductName = l_bondProduct.getHostShortProductName();
        }
        // ��ʃR�[�h = ������.get��ʃR�[�h()
        if (l_bondProduct.getBondCategCode() != null)
        {
            l_productBasicInfo.bondCategCode = l_bondProduct.getBondCategCode();
        }
        // �ʉ݃R�[�h=������.get�ʉ݃R�[�h()
        if (l_bondProduct.getCurrencyCode() != null)
        {
            l_productBasicInfo.currencyCode = l_bondProduct.getCurrencyCode();
        }
        // ���s�s��R�[�h = ������.get���s�s��R�[�h()
        if (l_bondProduct.getIssueMarketCode() != null)
        {
            l_productBasicInfo.issueMarketCode = l_bondProduct.getIssueMarketCode();
        }
        // ���s�̃R�[�h = ������.get���s�̃R�[�h()
        if (l_bondProduct.getIssueAssociationCode() != null)
        {
            l_productBasicInfo.issueAssociationCode = l_bondProduct.getIssueAssociationCode();
        }
        // �o�ߗ��q�v�Z�^�C�v = ������.get�o�ߗ��q�v�Z�^�C�v() 
        if (l_bondProduct.getAccruedInterestCalcType() != null)
        {
            l_productBasicInfo.accruedInterestCalcType = l_bondProduct.getAccruedInterestCalcType();
        }
        // �o�ߗ��q�N�Z�� = ������.get�o�ߗ��q�N�Z��()
        if (l_bondProduct.getAccruedInterestStartDay() != null)
        {
            l_productBasicInfo.accruedInterestStartDay = l_bondProduct.getAccruedInterestStartDay();
        }
        // ���ꗘ���敪 = ������.���ꗘ���敪() 
        if (l_bondProduct.getSpecialPaymentDiv() != null)
        {
            l_productBasicInfo.specialPaymentDiv = l_bondProduct.getSpecialPaymentDiv();
        }
        // �t���[�e�B���O���[�g�E�������ԋ敪 = ������.get�t���[�e�B���O���[�g�E�������ԋ敪()
        if (l_bondProduct.getFloatingInterestPeriodDiv() != null)
        {
            l_productBasicInfo.floatingInterestPeriodDiv = l_bondProduct.getFloatingInterestPeriodDiv();
        }
        // �t���[�e�B���O���[�g�E�������� = ������.get�t���[�e�B���O���[�g�E��������()
        if (l_bondProduct.getFloatingInterestPeriod() != null)
        {
            l_productBasicInfo.floatingInterestPeriod = l_bondProduct.getFloatingInterestPeriod();
        }
        // �t���[�e�B���O���[�g�E������� = ������.get�t���[�e�B���O���[�g�E�������()
        if (l_bondProduct.getFloatingInterestType() != null)
        {
            l_productBasicInfo.floatingInterestType = l_bondProduct.getFloatingInterestType();
        }
        // �t���[�e�B���O���[�g�E�X�v���b�h = ������.get�t���[�e�B���O���[�g�E�X�v���b�h()
        if (!l_productRow.getFloatingInterestSpreadIsNull())
        {
            l_productBasicInfo.floatingInterestSpread = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getFloatingInterestSpread());
        }
        // �t���[�e�B���O���[�g�E�~�j�}���N�[�|�� = ������.get�t���[�e�B���O���[�g�E�~�j�}���N�[�|��()
        if (!l_productRow.getFloatingMinCouponIsNull())
        {
            l_productBasicInfo.floatingMinCoupon = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getFloatingMinCoupon());
        }
        // �Ɛŋ敪 = ������.get�Ɛŋ敪()
        if (l_bondProduct.getTaxFreeDiv() != null)
        {
            l_productBasicInfo.taxFreeDiv = l_bondProduct.getTaxFreeDiv();
        }
        // S&P = ������.getS&P()
        if (l_bondProduct.getSAndP() != null)
        {
            l_productBasicInfo.sAndP = l_bondProduct.getSAndP();
        }
        // MOODY'S = ������.getMoody's()
        if (l_bondProduct.getMoodys() != null)
        {
            l_productBasicInfo.moodys = l_bondProduct.getMoodys();
        }
        // CUSIP = ������.getCUSIP()
        if (l_bondProduct.getCUSIP() != null)
        {
            l_productBasicInfo.cusip = l_bondProduct.getCUSIP();
        }
        
        //1.8.�������X�V���()
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo = 
            new WEB3AdminBondProductUpdateInfo();
        
        //1.9.�v���p�e�B�Z�b�g
        //�������X�V���I�u�W�F�N�g�Ɉȉ��̃v���p�e�B���Z�b�g����B     
        // �戵�敪 = ������.get�戵�敪() 
        if (l_productRow.getTradeHandleDivIsSet())
        {
            l_productUpdateInfo.tradeHandleDiv = l_bondProduct.getTradeHandleDiv();
        }
        // �戵�J�n���� = ������.get�戵�J�n����()
        if (l_bondProduct.getTradeStartDate() != null)
        {
            l_productUpdateInfo.tradeStartDate = l_bondProduct.getTradeStartDate();
        }
        // �戵�I������ = ������.get�戵�I������()
        if (l_bondProduct.getTradeEndDate() != null)
        {
            l_productUpdateInfo.tradeEndDate = l_bondProduct.getTradeEndDate();
        }
        // ��J�n�� = ������.get����J�n��()
        if (l_bondProduct.getRecruitStartDate() != null)
        {
            l_productUpdateInfo.recruitStartDate = l_bondProduct.getRecruitStartDate();
        }
        // ����I���� = ������.get����I����()
        if (l_bondProduct.getRecruitEndDate() != null)
        {
            l_productUpdateInfo.recruitEndDate = l_bondProduct.getRecruitEndDate();
        }
        // �����敪 = ������.get�������()
        if (l_bondProduct.getTradeType() != null)
        {
            l_productUpdateInfo.buySellDiv = l_bondProduct.getTradeType();
        }
        // ������ = ������.get������()
        if (l_bondProduct.getProductName() != null)
        {
            l_productUpdateInfo.productName = l_bondProduct.getProductName();
        }
        // ���t�P�� = ������.get���t�P��()
        if (!l_productRow.getBuyPriceIsNull())
        {
            l_productUpdateInfo.buyPrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
        }
        // ���p�P�� = ������.get���p�P��()
        if (!l_productRow.getSellPriceIsNull())
        {
            l_productUpdateInfo.sellPrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getSellPrice());
        }
        // �\���P�� = ������.get�\���P��()
        if (l_productRow.getTradeUnitIsSet())
        {
            l_productUpdateInfo.tradeUnit = WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());
        }
        // �Œ�z�� = ������.get�Œ�z��()
        if (l_productRow.getMinFaceAmountIsSet())
        {
            l_productUpdateInfo.minFaceAmount = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMinFaceAmount());
        }
        // �ō��z�� = ������.get�ō��z��()
        if (!l_productRow.getMaxFaceAmountIsNull())
        {
            l_productUpdateInfo.maxFaceAmount = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMaxFaceAmount());
        }
        // �J�����_�[�A�g�s��R�[�h = ������.get�J�����_�[�A�g�s��R�[�h() 
        if (l_bondProduct.getCalLinkedMarketCode() != null)
        {
            l_productUpdateInfo.calendarLinkedDiv = l_bondProduct.getCalLinkedMarketCode();
        }
        // ���t��n���ړ����� = ������.get���t��n���ړ�����()
        if (!l_productRow.getBuyDeliveryDateShiftdaysIsNull())
        {
            l_productUpdateInfo.buyDeliveryMove = l_bondProduct.getBuyDeliveryDateShiftDays() + "";
        }
        // ���p��n���ړ����� = ������.get���p��n���ړ�����()
        if (!l_productRow.getSellDeliveryDateShiftdaysIsNull())
        {
            l_productUpdateInfo.sellDeliveryMove = l_bondProduct.getSellDeliveryDateShiftDays() + "";
        }
        // �������敪 = ������.get�������敪()
        if (l_productRow.getAutoExecDivIsSet())
        {
            l_productUpdateInfo.autoExecDiv = l_bondProduct.getAutoExecDiv();
        }
        // �������g = ������.get�������g()
        if (!l_productRow.getAutoExecLimitIsNull())
        {
            l_productUpdateInfo.autoExecLimit = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getAutoExecLimit());
        }
        // �J�X�g�f�B�A���R�[�h=������.get�J�X�g�f�B�A���R�[�h()
        if (l_bondProduct.getCustodianCode() != null)
        {
            l_productUpdateInfo.custodianCode = l_bondProduct.getCustodianCode();
        }
        
        //�d�����̈בփ��[�g= ������.get�d�����̈בփ��[�g()
        if (!l_productRow.getBuyingFxRateIsNull())
        {
            l_productUpdateInfo.fxRateAtStock = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyingFxRate());
        }
        
        //������ԃ`�F�b�N�敪 = ������.get������ԃ`�F�b�N�敪()
        if(l_bondProduct.getTradingTimeCheckDiv() != null)
        {
            l_productUpdateInfo.tradeTimeCheckDiv = l_bondProduct.getTradingTimeCheckDiv();
        }
        
        // ����萔������������.get����萔����
        if (!l_productRow.getMediatorCommissionRateIsNull())
        {
            l_productUpdateInfo.mediatorCommissionRate = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMediatorCommissionRate());
        }
        //���助�U�`����������.get���助�U�`��()
        l_productUpdateInfo.recruitInvitationForm =
            l_productRow.getRecruitInvitationDiv();
        //������󂯋敪��������.get������󂯋敪()
        l_productUpdateInfo.recruitAcceptDiv = l_productRow.getRecruitAcceptDiv();

        //��n����������.get��n��()
        l_productUpdateInfo.deliveryDate = l_bondProduct.getDeliveryDate();

        //1.10.get�J�X�g�f�B�A���ꗗ(�،����)
        //�J�X�g�f�B�A���ꗗ���擾����B 
        //[����]   
        //�،���ЁFget�،���� 
        WEB3BondDataManagerService l_dataManagerService = 
            (WEB3BondDataManagerService) Services.getService(WEB3BondDataManagerService.class);
        List l_lisCustodians = l_dataManagerService.getCustodianList(l_institution);
        
        //1.11.to�J�X�g�f�B�A���ꗗ(List)
        //�J�X�g�f�B�A���ꗗ���擾 
        //[to�J�X�g�f�B�A���ꗗ()�̈���] 
        //�J�X�g�f�B�A�����X�g�Fget�J�X�g�f�B�A���ꗗ
        WEB3AdminBondHelperService l_helperService = 
            (WEB3AdminBondHelperService) Services.getService(WEB3AdminBondHelperService.class);
        List l_lisCustodianLists = l_helperService.toCustodianList(l_lisCustodians);
        
        //1.12.get���������g�����ꗗ(BondProduct)
        //���������g�����ꗗ���擾����B 
        //[����]  
        //�������F get������
        List l_lisAutoExecLimitHistorys = l_bondProductManager.getBondAutoExecLimitHistoryList(l_bondProduct);
        
        //1.13.get�������g�����ꗗ()�̖߂�l�̗v�f����LOOP
        //get�������g�����ꗗ()�̖߂�l�̗v�f����LOOP���āA 
        //�������g����z����쐬����B
        //WEB3AdminBondAutoExecLimitHistoryUnit 
        List l_lisLimitHistoryUnits = new ArrayList(); 
        if (l_lisAutoExecLimitHistorys != null || !l_lisAutoExecLimitHistorys.isEmpty())
        {
            
            for (int i = 0; i < l_lisAutoExecLimitHistorys.size(); i++)
            {
                BondAutoExecLimitActionRow l_actionRow = 
                    (BondAutoExecLimitActionRow)l_lisAutoExecLimitHistorys.get(i);
                //1.13.1.to�������g����(�������g����Row)
                //[����] 
                // �������g����Row�F�@@LOOP�Ώۂ̎������g����Row�@@
                l_lisLimitHistoryUnits.add(toAutoExecLimitAction(l_actionRow));
            }
        }
        WEB3AdminBondAutoExecLimitHistoryUnit[] l_autoExecLimitHistoryUnit = 
            new WEB3AdminBondAutoExecLimitHistoryUnit[l_lisLimitHistoryUnits.size()];
        l_lisLimitHistoryUnits.toArray(l_autoExecLimitHistoryUnit);
        //1.14.get���������ώc��(Institution, String)
        //���������ώc�����擾����B 
        //[����]  
        //�،���ЁF get�،���� 
        //�����R�[�h(WEB3)�F���N�G�X�g�f�[�^.�����R�[�h(WEB3)
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        double l_dblProductExecutedBalance = 
            l_bondOrderManager.getBondProductExecutedBalance(l_institution, l_request.productCode);
        
        //1.15.get�����������ꗗ(BondProduct)
        //�����������ꗗ���擾����B 
        //[����]  
        //�������F get������
        List l_lisCouponList = l_bondProductManager.getBondProductCouponList(l_bondProduct);
        
        //1.16.get�����������ꗗ�i�j�̖߂�l�̐擪����10�����܂�LOOP
        //get�����������ꗗ�i�j�̖߂�l�̐擪����10�����܂�LOOP���āA 
        //�����������z����쐬����B 
        //��11���ڈȍ~�͈ȉ��̏������s�Ȃ�Ȃ��B 
        List l_lisProductCouponUnit = new ArrayList(); 
        
        if (l_lisCouponList != null)
        {
            if (l_lisCouponList.size() > 10)
            {
                for (int j = 0; j < 10; j++)
                {
                    BondProductCouponRow l_couponRow = (BondProductCouponRow) l_lisCouponList.get(j);
                    //to����������(����������Row)
                    //[����] 
                    //�@@�����������F�@@LOOP�Ώۂ̍���������Row
                    l_lisProductCouponUnit.add(toBondProductCoupon(l_couponRow));
                }
            }
            else
            {
                for (int j = 0; j < l_lisCouponList.size(); j++)
                {
                    BondProductCouponRow l_couponRow = (BondProductCouponRow) l_lisCouponList.get(j);
                    //to����������(����������Row)
                    //[����] 
                    //�@@�����������F�@@LOOP�Ώۂ̍���������Row
                    l_lisProductCouponUnit.add(toBondProductCoupon(l_couponRow));
                }
            }
        }
        WEB3AdminBondProductCouponUnit[] l_productCouponUnits = 
            new WEB3AdminBondProductCouponUnit[l_lisProductCouponUnit.size()];
        l_lisProductCouponUnit.toArray(l_productCouponUnits);
        
        //1.17get�戵�\�O���s��(String)
        //�،���ЃR�[�h�F�Ǘ���.get�،���ЃR�[�h
        WEB3GentradeFinObjectManager l_finObjectManager = 
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        
        Market[] l_markets = l_finObjectManager.getOpenFeqMarkets(l_admin.getInstitutionCode());
      
        //1.18.create���X�|���X( )
        WEB3AdminBondProductRegistInputResponse l_response = null;
        l_response = (WEB3AdminBondProductRegistInputResponse) l_request.createResponse();
        
        //1.19.�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B 
        // ��������{��� = ��������{���I�u�W�F�N�g
        l_response.basicInfo = l_productBasicInfo;
        // �������X�V��� = �������X�V���I�u�W�F�N�g
        l_response.updateInfo = l_productUpdateInfo;
        // �J�X�g�f�B�A���ꗗ = to�J�X�g�f�B�A���ꗗ�i�j�̖߂�l���Z�b�g����
        WEB3AdminBondCustodianUnit[] l_custodianUnits = null;
        if (l_lisCustodianLists != null && !l_lisCustodianLists.isEmpty())
        {
            l_custodianUnits = new WEB3AdminBondCustodianUnit[l_lisCustodianLists.size()];
            l_lisCustodianLists.toArray(l_custodianUnits);
        }
        l_response.custodianList = l_custodianUnits;
        // �������g�����ꗗ = �쐬�����������g����z����Z�b�g����B
        l_response.autoExecLimitList = l_autoExecLimitHistoryUnit;
        // ���ώc�� = get���������ώc��()
        l_response.executedBalance = 
            WEB3StringTypeUtility.formatNumber(l_dblProductExecutedBalance);
        // �����������ꗗ = �쐬���������������z����Z�b�g����B
        l_response.productCouponList = l_productCouponUnits;
        // �Ǘ��҃R�[�h = ������.get�ŏI�X�V�҃R�[�h()
        l_response.administratorCode = l_bondProduct.getLastUpdater();
        // �Ǘ��ҍŏI�X�V���� = ������.get�Ǘ��ҍX�V���t()
        l_response.lastUpdateTime = l_bondProduct.getAdminLastUpdatedTimestamp();
        // �戵�敪���X�g = {�s��,  �Ǘ���,�@@�Ǘ���/�ڋq}�@@���R�[�h�l�ɂ��String�z��
        String[] l_strTradeHandleDivs = 
            new String[]{
                WEB3TradeHandleDivDef.DISABLED,
                WEB3TradeHandleDivDef.MANAGER, 
                WEB3TradeHandleDivDef.MANAGER_CUSTOMER};
        l_response.tradeHandleDivList = l_strTradeHandleDivs;
        
        // �����敪���X�g = {���t,  ���p, ����, ���t/���p�p�@@���R�[�h�l�ɂ��String�z��
        String[] l_strBuySellDivs =
            new String[]{
                WEB3BondTradeTypeDef.BUY, 
                WEB3BondTradeTypeDef.SELL, 
                WEB3BondTradeTypeDef.RECRUIT, 
                WEB3BondTradeTypeDef.BUY_SELL};
        l_response.buySellDivList = l_strBuySellDivs;
        
        // �J�����_�[�A�g�s��R�[�h���X�g 
        String[] l_strMarketCodes = new String[l_markets.length];
        int l_intIndex = l_markets.length;
        for (int i = 0; i < l_intIndex; i++)
        {
            l_strMarketCodes[i] = l_markets[i].getMarketCode();
        }
        
        l_response.calLinkedDivList = l_strMarketCodes;
        
        // �������g�敪���X�g = {�񎩓����, �������p�@@���R�[�h�l�ɂ��String�z��
        String[] l_strAutoExecDivs = 
            new String[]{
                WEB3AutoExecDivDef.NOT_AUTO_EXECUTE, 
                WEB3AutoExecDivDef.AUTO_EXECUTE};
        l_response.autoExecDivList = l_strAutoExecDivs;
        
        //������ԃ`�F�b�N�敪���X�g = {������Ԃ��`�F�b�N����, ������Ԃ��`�F�b�N���Ȃ�}
        //���R�[�h�l�ɂ��String�z��
        String[] l_strTradeTimeCheckDiv = 
            new String[]{
                WEB3TradingTimeCheckDivDef.TRADING_TIME_CHECK,
                WEB3TradingTimeCheckDivDef.TRADING_TIME_NOT_CHECK};
        l_response.tradeTimeCheckDivList = l_strTradeTimeCheckDiv;

        //���助�U�`�����X�g = {��W,�@@���o��,�@@����,�@@��W���o��}�@@���R�[�h�l�ɂ��String�z��
        String[] l_strRecruitInvitationDivs =
            new String[]{
                WEB3BondRecruitInvitationFormDef.RECRUIT,
                WEB3BondRecruitInvitationFormDef.SELL, 
                WEB3BondRecruitInvitationFormDef.PRIVATE_RECRUIT,
                WEB3BondRecruitInvitationFormDef.RECRUIT_SELL};
        l_response.recruitInvitationFormList = l_strRecruitInvitationDivs;
        //������󂯋敪���X�g = {����,�@@�����}�@@���R�[�h�l�ɂ��String�z��
        String[] l_strRecruitAcceptDivs =
            new String[]{
                WEB3BondRecruitAcceptDivDef.ACCEPT,
                WEB3BondRecruitAcceptDivDef.UNACCEPT};
        l_response.recruitAcceptDivList = l_strRecruitAcceptDivs;

        log.exiting(STR_METHOD_NAME);         
        return l_response;
    }
    
    /**
     * (validate�����o�^)<BR>
     * �������o�^�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(��)validate�����o�^�v���Q��
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B601CA0125
     */
    protected WEB3AdminBondProductRegistConfirmResponse validateProductRegister(
        WEB3AdminBondProductRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateProductRegister(WEB3AdminBondProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N������  
        //[validate����()�Ɏw�肷�����]  
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�������Ǘ� 
        //is�X�V�F�@@true 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);
        
        //1.4.get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5.validate�������e(Institution, �������X�V���)
        //���������e�̐��������`�F�b�N����B 
        //[����] 
        //�،����:get�،����() 
        //���������e�F���N�G�X�g.�������X�V���
        //�����R�[�h(WEB3):�@@���N�G�X�g�f�[�^.�����R�[�h(WEB3)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();   
        l_bondProductManager.validateProductSpec(l_institution, l_request.updateInfo, l_request.productCode);
        
        //1.6.create���X�|���X( )
        WEB3AdminBondProductRegistConfirmResponse l_response = null;
        l_response = (WEB3AdminBondProductRegistConfirmResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�����o�^)<BR>
     * �������o�^�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(��)submit�����o�^�v���Q��
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B601CA0127
     */
    protected WEB3AdminBondProductRegistCompleteResponse submitProductRegister(
        WEB3AdminBondProductRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitProductRegister(WEB3AdminBondProductRegistCompleteRequest)";
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
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);
        
        //1.4.validate����p�X���[�h(�p�X���[�h : String)
        //����p�X���[�h�����������`�F�b�N���� 
        //[validate����p�X���[�h()�̈���] 
        //�p�X���[�h�F���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5.get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.6.get�Ǘ��҃R�[�h( )
        String l_strtAdministratorCode = l_admin.getAdministratorCode();
        
        //1.7.validate�������e(Institution, �������X�V���)
        //���������e�̐��������`�F�b�N����B 
        //[����] 
        //�،����:get�،����() 
        //���������e�F���N�G�X�g.�������X�V���
        //�����R�[�h(WEB3):�@@���N�G�X�g�f�[�^.�����R�[�h(WEB3)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();   
        l_bondProductManager.validateProductSpec(l_institution, l_request.updateInfo, l_request.productCode);
        
        //1.8.update���������e(Institution, String, �������X�V���, String)
        //���������e��DB�֔��f����B 
        //[����] 
        //�،����:�@@get�،���Ёi�j 
        //�����R�[�h(WEB3):�@@���N�G�X�g�f�[�^.�����R�[�h(WEB3) 
        //�������X�V���:�@@���N�G�X�g�f�[�^.�������X�V��� 
        //�Ǘ��҃R�[�h:�@@get�Ǘ��҃R�[�h�i�j 
        l_bondProductManager.updateBondProductSpec(
                l_institution, l_request.productCode, l_request.updateInfo, l_strtAdministratorCode);
        
        //1.9.create���X�|���X( )
        WEB3AdminBondProductRegistCompleteResponse l_response = null;
        l_response = (WEB3AdminBondProductRegistCompleteResponse) l_request.createResponse();
        
        //1.10.�v���p�e�B�Z�b�g
        //�X�V���� = ���ݓ���
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp(); 
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (to�������g����)<BR>
     * ���X�|���X�f�[�^�p�ɊǗ��҃��b�Z�[�W.�������g�����𐶐�����B<BR>
     * <BR>
     * �P�j������藚���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�v���p�e�B�Z�b�g<BR>
     * �@@�@@�������g����.���g�X�V���t = ���������g����Row�I�u�W�F�N�g.get���g�X�V���t()<BR>
     * �@@�@@�������g����.���ώc���@@�@@�@@ = ���������g����Row�I�u�W�F�N�g.get���ώc��()<BR>
     * �@@�@@�������g����.�������g�@@�@@�@@ = ���������g����Row�I�u�W�F�N�g.get�������g()<BR>
     * <BR>
     * �R�j�쐬����������藚���I�u�W�F�N�g��ԋp����B
     * @@param l_row - (�������g����Row)<BR>
     * �������g����Row
     * @@return webbroker3.bd.message.WEB3AdminBondAutoExecLimitHistoryUnit
     * @@throws WEB3BaseException
     * @@roseuid 44D9C85E00F9
     */
    protected WEB3AdminBondAutoExecLimitHistoryUnit toAutoExecLimitAction(
        BondAutoExecLimitActionRow l_row) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " toAutoExecLimitAction(BondAutoExecLimitActionRow)";
        log.entering(STR_METHOD_NAME);
         
        //�P�j������藚���I�u�W�F�N�g�𐶐�����B 
        WEB3AdminBondAutoExecLimitHistoryUnit l_limitHistoryUnit = 
            new WEB3AdminBondAutoExecLimitHistoryUnit();
        
        //�Q�j�v���p�e�B�Z�b�g 
        //�@@�@@�������g����.���g�X�V���t = ���������g����Row�I�u�W�F�N�g.get���g�X�V���t() 
        l_limitHistoryUnit.executionUpdateDate = l_row.getExecutionUpdateDate();
        //�@@�@@�������g����.���ώc���@@�@@�@@ = ���������g����Row�I�u�W�F�N�g.get���ώc��() 
        l_limitHistoryUnit.executedBalance = WEB3StringTypeUtility.formatNumber(l_row.getAutoExecAmount());
        //�@@�@@�������g����.�������g�@@�@@�@@ = ���������g����Row�I�u�W�F�N�g.get�������g() 
        l_limitHistoryUnit.autoExecLimit = WEB3StringTypeUtility.formatNumber(l_row.getAutoExecLimit());

        //�R�j�쐬����������藚���I�u�W�F�N�g��ԋp����B
        
        log.exiting(STR_METHOD_NAME);
        return l_limitHistoryUnit;
    }
    
    /**
     * (to����������)<BR>
     * ���X�|���X�f�[�^�p�ɊǗ��҃��b�Z�[�W.�����������𐶐�����B<BR>
     * <BR>
     * �P�j�����������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�v���p�e�B�Z�b�g<BR>
     * �@@�@@����������.������ = ����������Row.������<BR>
     * �@@�@@����������.���� �@@= ����������Row.����<BR>
     * <BR>
     * �R�j�쐬���������������I�u�W�F�N�g��ԋp����B
     * @@param l_row - (����������Row)<BR>
     * ����������Row
     * @@return WEB3AdminBondProductCouponUnit
     * @@throws WEB3BaseException
     * @@roseuid 44D9C8670185
     */
    protected WEB3AdminBondProductCouponUnit toBondProductCoupon(
        BondProductCouponRow l_row) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toBondProductCoupon(BondProductCouponRow)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�����������I�u�W�F�N�g�𐶐�����B 
        WEB3AdminBondProductCouponUnit l_productCouponUnit = 
            new WEB3AdminBondProductCouponUnit();
        //�Q�j�v���p�e�B�Z�b�g 
        //�@@�@@����������.������ = ����������Row.������ 
        l_productCouponUnit.interestPaymentDay = l_row.getInterestPaymentDay();
        
        //�@@�@@����������.���� �@@= ����������Row.���� 
        l_productCouponUnit.coupon = WEB3StringTypeUtility.formatNumber(l_row.getCoupon());
        //�R�j�쐬���������������I�u�W�F�N�g��ԋp����B

        log.exiting(STR_METHOD_NAME);
        return l_productCouponUnit;
    }
}
@
