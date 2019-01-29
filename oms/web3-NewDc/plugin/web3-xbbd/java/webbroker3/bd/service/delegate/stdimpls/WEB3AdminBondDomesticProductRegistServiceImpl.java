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
filename	WEB3AdminBondDomesticProductRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ����������o�^�T�[�r�XImpl(WEB3AdminBondDomesticProductRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 �����q(���u) �V�K�쐬 �d�l�ύX�E���f��No.193
Revision History : 2007/07/11 �đo�g(���u) �d�l�ύX�E���f��No.201�A���f��No.212
Revision History : 2007/10/08 ���g (���u) �d�l�ύX�E���f��259
*/

package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputResponse;
import webbroker3.bd.message.WEB3BondDomesticProductBasicInfo;
import webbroker3.bd.message.WEB3BondDomesticProductUpdateInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҍ����������o�^�T�[�r�XImpl)<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductRegistServiceImpl implements WEB3AdminBondDomesticProductRegistService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductRegistServiceImpl.class);

    /**
     * @@roseuid 4691D3AE021B
     */
    public WEB3AdminBondDomesticProductRegistServiceImpl()
    {

    }

    /**
     * �Ǘ��ҍ����������o�^�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * input�����o�^()�Avalidate�����o�^()�Asubmit�����o�^()<BR>
     * �̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 466505DD0109
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
        if (l_request instanceof WEB3AdminBondDomesticProductRegistInputRequest)
        {
            l_response =
                this.inputProductRegist(
                    (WEB3AdminBondDomesticProductRegistInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminBondDomesticProductRegistConfirmRequest)
        {
            l_response =
                this.validateProductRegist(
                    (WEB3AdminBondDomesticProductRegistConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminBondDomesticProductRegistCompleteRequest)
        {
            l_response =
                this.submitProductRegist(
                    (WEB3AdminBondDomesticProductRegistCompleteRequest)l_request);
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
     * (input�����o�^)<BR>
     * �Ǘ��ҍ����������o�^���͏������s�Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�F�u�i�Ǘ��ҍ����������o�^�jinput�����o�^�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondDomesticProductRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4664F51F0128
     */
    protected WEB3AdminBondDomesticProductRegistInputResponse inputProductRegist(
        WEB3AdminBondDomesticProductRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " inputProductRegist(WEB3AdminBondDomesticProductRegistInputRequest)";
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

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //  �@@�\�J�e�S���R�[�h�@@�F�@@�\�J�e�S���R�[�h.�������Ǘ�
        //�@@is�X�V�@@�Ffalse
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            false);

        //get������(long)
        //[����]
        //�@@�@@����ID�@@�F���N�G�X�g�f�[�^.����ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(Long.parseLong(l_request.productId));

        //get���s����(String)
        //[����]
        //�@@�@@����ID�@@�F���N�G�X�g�f�[�^.����ID
        String[] l_strIssueCouponTypes =
            l_productManager.getIssueCouponType(l_request.productId);

        //������������{���( )
        WEB3BondDomesticProductBasicInfo l_productBasicInfo =
            new WEB3BondDomesticProductBasicInfo();

        //�v���p�e�B�E�Z�b�g
        //�����R�[�h           �F������.�����R�[�h�iSONAR)
        l_productBasicInfo.productCode = l_bondProduct.getHostProductCode();

        //�񍆃R�[�h           �F������.�񍆃R�[�h�iSONAR�j
        l_productBasicInfo.productIssueCode = l_bondProduct.getHostProductIssueCode();

        //�������iHOST)       �F������.HOST�������P
        l_productBasicInfo.productNameHost = l_bondProduct.getHostProductName1();

        //��ʃR�[�h           �F������.��ʃR�[�h
        l_productBasicInfo.bondCategCode = l_bondProduct.getBondCategCode();

        //���s����            �Fget���s����()�̖߂�l
        l_productBasicInfo.issueCouponType = l_strIssueCouponTypes;

        //���s��         �F������.���s��
        l_productBasicInfo.issueDate = l_bondProduct.getIssueDate();

        //����P��            �F������.���t�P��
        BondProductRow l_bondProductRow =  (BondProductRow)l_bondProduct.getDataSourceObject();
        if (!l_bondProductRow.getBuyPriceIsNull())
        {
            l_productBasicInfo.applyPrice =
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
        }

        //����          �F������.����
        l_productBasicInfo.coupon =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //�N�ԗ�����      �F������.�N�ԗ�����
        l_productBasicInfo.yearlyInterestPayments = l_bondProduct.getYearlyInterestPayments() + "";

        //�������P            �F������.�������P
        l_productBasicInfo.couponPaymentDate1 = l_bondProduct.getInterestPaymentDay1();

        //�������Q            �F������.�������Q
        l_productBasicInfo.couponPaymentDate2 = l_bondProduct.getInterestPaymentDay2();

        //���ғ�         �F������.���ғ�
        l_productBasicInfo.maturityDate = l_bondProduct.getMaturityDate();

        //����J�n���iSONAR�j    �F������.����J�n���iSONAR�j
        l_productBasicInfo.recruitStartDateSONAR = l_bondProduct.getHostRecruitStartDate();

        //����I�����iSONAR�j    �F������.����I�����iSONAR�j
        l_productBasicInfo.recruitEndDateSONAR = l_bondProduct.getHostRecruitEndDate();

        // �����������X�V���( )
        WEB3BondDomesticProductUpdateInfo l_productUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();

        //�v���p�e�B�E�Z�b�g
        //�戵�敪            �F������.�戵�敪
        l_productUpdateInfo.tradeHandleDiv = l_bondProduct.getTradeHandleDiv();

        //�����敪            �F������.�����敪
        l_productUpdateInfo.dealingType = l_bondProduct.getTradeType();

        //����J�n���iWEB3�j     �F������.�戵�J�n����
        l_productUpdateInfo.recruitStartDateWEB3 = l_bondProduct.getTradeStartDate();

        //����I�����iWEB3)     �F������.�戵�I������
        l_productUpdateInfo.recruitEndDateWEB3 = l_bondProduct.getTradeEndDate();

        //����J�n���i�C���^�[�l�b�g�j  �F������.����J�n��
        l_productUpdateInfo.recruitStartDateInterNet = l_bondProduct.getRecruitStartDate();

        //����I�����i�C���^�[�l�b�g)  �F������.����I����
        l_productUpdateInfo.recruitEndDateInterNet = l_bondProduct.getRecruitEndDate();

        //��n��         �F������.��n��
        l_productUpdateInfo.deliveryDate = l_bondProduct.getDeliveryDate();

        //�������iWEB3)       �F������.������
        l_productUpdateInfo.productNameWEB3 = l_bondProduct.getProductName();

        //�\���P��            �F������.�\���P��
        l_productUpdateInfo.applyUnit =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());

        //�Œ�z��            �F������.�Œ�z��
        l_productUpdateInfo.minFaceAmount =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getMinFaceAmount());

        //�ō��z��            �F������.�ō��z��
        if (!l_bondProductRow.getMaxFaceAmountIsNull())
        {
            l_productUpdateInfo.maxFaceAmount =
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMaxFaceAmount());
        }

        //�ژ_�����{���`�F�b�N�敪    �F������.�ژ_�����{���`�F�b�N�敪
        l_productUpdateInfo.prospectusCheckDiv = l_bondProduct.getProspectusCheckDiv();

        //createResponse( )
        WEB3AdminBondDomesticProductRegistInputResponse l_response =
            (WEB3AdminBondDomesticProductRegistInputResponse)l_request.createResponse();

        //������{���        �F�쐬����������������{���
        l_response.productBasicInfo = l_productBasicInfo;

        //�����X�V���        �F�쐬���������������X�V���
        l_response.productUpdateInfo = l_productUpdateInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�����o�^)<BR>
     * �Ǘ��ҍ����������o�^�m�F�������s�Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�F�u�i�Ǘ��ҍ����������o�^�jvalidate�����o�^�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondDomesticProductRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4664F53500EA
     */
    protected WEB3AdminBondDomesticProductRegistConfirmResponse validateProductRegist(
        WEB3AdminBondDomesticProductRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateProductRegist(WEB3AdminBondDomesticProductRegistConfirmRequest)";
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

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //  �@@�\�J�e�S���R�[�h�@@�F�@@�\�J�e�S���R�[�h.�������Ǘ�
        //�@@is�X�V�@@�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);

        //����I�����iWEB3)�Ɖ���I�����i�C���^�[�l�b�g�j�Ɏ�����ݒ肷��B
        //[����]
        //�����������X�V���F�@@���N�G�X�g�f�[�^.�����X�V���
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            this.setRecruitEndDate(l_request.bondDomesticProductUpdateInfo);

        //validate�������e(String, �����������X�V���)
        //[����]
        //�@@�@@����ID�@@�F���N�G�X�g�f�[�^.����ID
        //�@@�@@�����������X�V���@@�F�����������X�V���
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        l_productManager.validateProductSpec(
            l_request.productId,
            l_bondDomesticProductUpdateInfo);

        //createResponse( )
        WEB3AdminBondDomesticProductRegistConfirmResponse l_response =
            (WEB3AdminBondDomesticProductRegistConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�����o�^)<BR>
     * �Ǘ��ҍ����������o�^�����������s�Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�F�u�i�Ǘ��ҍ����������o�^�jsubmit�����o�^�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondDomesticProductRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4664F57601F3
     */
    protected WEB3AdminBondDomesticProductRegistCompleteResponse submitProductRegist(
        WEB3AdminBondDomesticProductRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitProductRegist(WEB3AdminBondDomesticProductRegistCompleteRequest)";
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

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        // �@@�\�J�e�S���R�[�h�@@�F�@@�\�J�e�S���R�[�h.�������Ǘ�
        //�@@is�X�V�@@�Ftrue
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);

        //validate����p�X���[�h(�p�X���[�h : String)
        //[����]
        //�@@�@@�p�X���[�h�@@�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //����I�����iWEB3)�Ɖ���I�����i�C���^�[�l�b�g�j�Ɏ�����ݒ肷��B
        //[����]
        //�����������X�V���F�@@���N�G�X�g�f�[�^.�����X�V���
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            this.setRecruitEndDate(l_request.bondDomesticProductUpdateInfo);

        //validate�������e(String, �����������X�V���)
        //[����]
        //�@@�@@����ID�@@�F���N�G�X�g�f�[�^.����ID
        //�@@�@@�����������X�V���@@�F�����������X�V���
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        l_productManager.validateProductSpec(
            l_request.productId,
            l_bondDomesticProductUpdateInfo);

        //get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //update���������e(String, �����������X�V���, String)
        //[����]
        //�@@�@@����ID�@@�F���N�G�X�g�f�[�^.����ID
        //�@@�@@�����������X�V���@@�F�����������X�V���
        //�@@�@@�Ǘ��҃R�[�h�@@�Fget�Ǘ��҃R�[�h()�̖߂�l
        l_productManager.updateBondProductContent(
            l_request.productId,
            l_bondDomesticProductUpdateInfo,
            l_strAdministratorCode);

        //createResponse( )
        WEB3AdminBondDomesticProductRegistCompleteResponse l_response =
            (WEB3AdminBondDomesticProductRegistCompleteResponse)l_request.createResponse();

        //�X�V�҃R�[�h    �Fget�Ǘ��҃R�[�h()�̖߂�l
        l_response.updaterCode = l_strAdministratorCode;

        //�X�V����        �F���ݓ���
        l_response.updateTimeStamp = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (set����I������)<BR>
     * �����������X�V���̉���I�����iWEB3)��<BR>
     * �@@�@@�@@����I�����i�C���^�[�l�b�g�j�Ɏ�����ݒ肷��B<BR>
     * <BR>
     * �P�j����I����(�C���^�[�l�b�g�j�̎�����ݒ肷��B<BR>
     * <BR>
     * �@@�@@�P�|�P�j��������ԊǗ�.get���������؎���()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���t�@@�F�@@�����������X�V���.����I����(�C���^�[�l�b�g�j<BR>
     * <BR>
     * �@@�@@�P�|�Q�j�����������X�V���.����I����(�C���^�[�l�b�g�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�̎��ԕ����Ɏ擾�������؎�����ݒ肷��B<BR>
     * <BR>
     * �Q�j����I����(WEB3�j�̎�����ݒ肷��B<BR>
     * <BR>
     * �@@�@@�Q�|�P�j��������ԊǗ�.get���������؎���()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���t�@@�F�@@�����������X�V���.����I����(WEB3�j<BR>
     * <BR>
     * �@@�@@�Q�|�Q�j�����������X�V���.����I����(WEB3�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�̎��ԕ����Ɏ擾�������؎�����ݒ肷��B<BR>
     * <BR>
     * �R�j�����������X�V����ԋp����B<BR>
     * @@param l_bondDomesticProductUpdateInfo - (�����������X�V���)<BR>
     * �����������X�V���<BR>
     * @@return WEB3BondDomesticProductUpdateInfo
     * @@throws WEB3BaseException
     */
    protected WEB3BondDomesticProductUpdateInfo setRecruitEndDate(
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setRecruitEndDate(WEB3BondDomesticProductUpdateInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_bondDomesticProductUpdateInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j����I����(�C���^�[�l�b�g�j�̎�����ݒ肷��B
        //�P�|�P�j��������ԊǗ�.get���������؎���()���R�[������B
        //[����]
        //���t�@@�F�@@�����������X�V���.����I����(�C���^�[�l�b�g�j
        WEB3BondTradingTimeManagement l_bondTradingTimeManagement =
            new WEB3BondTradingTimeManagement();
        String l_strRecruitEndDateInterNet =
            l_bondTradingTimeManagement.getBondDomesticLimitTime(
                l_bondDomesticProductUpdateInfo.recruitEndDateInterNet);

        // �P�|�Q�j�����������X�V���.����I����(�C���^�[�l�b�g�j�̎��ԕ����Ɏ擾�������؎�����ݒ肷��B
        String l_strFormatInterNetDate =
            WEB3DateUtility.formatDate(
                l_bondDomesticProductUpdateInfo.recruitEndDateInterNet,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strRecruitEndDateInterNet;

        // �Q�j����I����(WEB3�j�̎�����ݒ肷��B
        // �@@�@@�Q�|�P�j��������ԊǗ�.get���������؎���()���R�[������B
        //   [����]
        //   ���t�@@�F�@@�����������X�V���.����I����(WEB3�j
        String l_strRecruitEndDateWEB3 = l_bondTradingTimeManagement.getBondDomesticLimitTime(
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3);

        //    �Q�|�Q�j�����������X�V���.����I����(WEB3�j�̎��ԕ����Ɏ擾�������؎�����ݒ肷��B
        String l_strFormatWEB3Date =
            WEB3DateUtility.formatDate(
                l_bondDomesticProductUpdateInfo.recruitEndDateWEB3,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strRecruitEndDateWEB3;

        //�R�j�����������X�V����ԋp����B
        String l_strDateFormat =
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS;
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate(l_strFormatWEB3Date, l_strDateFormat);
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate(l_strFormatInterNetDate, l_strDateFormat);

        log.exiting(STR_METHOD_NAME);
        return l_bondDomesticProductUpdateInfo;
    }
}
@
