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
filename	WEB3BondDomesticApplyProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������������ꗗ�T�[�r�XImpl(WEB3BondDomesticApplyProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.224
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondOrderManagerReusableValidationsCheck;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDivDef;
import webbroker3.bd.define.WEB3TradingPossDivDef;
import webbroker3.bd.message.WEB3BondDomesticApplyProductInfo;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyProductListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (��������������ꗗ�T�[�r�XImpl)<BR>
 * ��������������ꗗ�T�[�r�XImpl<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductListServiceImpl
    extends WEB3BondClientRequestService
    implements WEB3BondDomesticApplyProductListService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyProductListServiceImpl.class);

    /**
     * @@roseuid 46A473FC031C
     */
    public WEB3BondDomesticApplyProductListServiceImpl()
    {

    }

    /**
     * ��������������ꗗ�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u��������������ꗗ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CD702033F
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
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        if (!(l_request instanceof WEB3BondDomesticApplyProductListRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3BondDomesticApplyProductListRequest l_listRequest = (WEB3BondDomesticApplyProductListRequest)l_request;
        WEB3BondDomesticApplyProductListResponse l_listResponse = null;

        List l_lisBondDomesticApplyProductInfos = new ArrayList();

        //validate( )
        l_listRequest.validate();

        //validate������t�\
        WEB3BondTradingTimeManagement.validateOrderAccept();

        //get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        //create��������������(String)
        //[create��������������()�ɓn������]
        //���敪�F�@@���N�G�X�g�f�[�^.���敪
        String l_strQueryString = this.createQueryString(l_listRequest.bondDiv);

        //create���������f�[�^�R���e�i(String)
        //[create���������f�[�^�R���e�i()�ɓn������]
        //���敪�F�@@���N�G�X�g�f�[�^.���敪
        Object[] l_queryDataContainer = this.createQueryDataContainer(l_listRequest.bondDiv);

        //create�\�[�g����������(String)
        //[create�\�[�g����������()�ɓn������]
        //���敪�F�@@���N�G�X�g�f�[�^.���敪
        String l_strSortCond = this.createSortCondString(l_listRequest.bondDiv);

        //get���������X�g(String, String, Object[], String)
        //[get���������X�g()�ɓn������]
        //�،���ЃR�[�h�F�@@�擾�����⏕����.getInstitution().getInstitutionCode()�̖߂�l
        //��������������F�@@create��������������()�̖߂�l
        //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l
        //�\�[�g����������F�@@create�\�[�g����������()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        List l_lisBondProducts = l_bondProductManager.getBondProductList(
            l_strInstitutionCode,
            l_strQueryString,
            l_queryDataContainer,
            l_strSortCond);

        //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[����]
        //l_list�F�@@get���������X�g()�̖߂�l
        //l_intRequestPageIndex�F�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
        //l_intRequestPageSize�F�@@���N�G�X�g�f�[�^.�y�[�W���\���s��
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisBondProducts,
            Integer.parseInt(l_listRequest.pageIndex),
            Integer.parseInt(l_listRequest.pageSize));

        //getArrayReturned(l_classType : Class)
        Object[] l_arrayReturned = l_pageIndexInfo.getArrayReturned(BondProductRow.class);

        try
        {
            //getArrayReturned()�̖߂�l�̗v�f����Loop����
            for (int i = 0; i < l_arrayReturned.length; i++)
            {
                //�ŗ�(�،���ЃR�[�h : String, �Ŏ�� : String, ������ : Date)
                //�@@�@@�@@�،���ЃR�[�h�F������.�،���ЃR�[�h
                //  �@@�@@�Ŏ�ށ@@�@@�@@�@@�@@�F���������򒥎���
                //  �@@�@@�������@@�@@�@@�@@�@@�F������.get������������
                BondProductRow l_bondProductRow = (BondProductRow)l_arrayReturned[i];
                WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductRow);
                WEB3GentradeTaxRate l_gentradeTaxRate = new WEB3GentradeTaxRate(
                    l_bondProduct.getInstitution().getInstitutionCode(),
                    WEB3DutyTypeDef.DOMESTIC_BOND_WITHHOLDING_TAX,
                    new Timestamp(l_bondProduct.getBondDomesticBizDate().getTime()));

                boolean l_blnValidationsCheckFlag = true;
                try
                {
                    //validate����������g(long, ������, double)
                    //[����]
                    //�@@���XID�F�擾�����⏕����.getMainAccountRow().getBranchId()
                    //  �������F�擾����������
                    //  �������ʁF0
                    WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                        new WEB3BondOrderManagerReusableValidationsCheck();
                    l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(
                        ((SubAccountRow)l_subAccount.getDataSourceObject()).getBranchId(),
                        l_bondProduct,
                        0);
                }
                catch (WEB3BaseException l_ex)
                {
                    l_blnValidationsCheckFlag = false;
                }

                //����������������( )
                WEB3BondDomesticApplyProductInfo l_bondDomesticApplyProductInfo =
                    new WEB3BondDomesticApplyProductInfo();

                //����ID          = ������.����ID
                l_bondDomesticApplyProductInfo.productId = String.valueOf(l_bondProduct.getProductId());
                //������          = ������.������
                l_bondDomesticApplyProductInfo.productName = l_bondProduct.getProductName();
                //����J�n��   = ������.�戵�J�n����
                l_bondDomesticApplyProductInfo.recruitStartDate =
                    l_bondProduct.getTradeStartDate();
                //����I����   = ������.�戵�I������
                l_bondDomesticApplyProductInfo.recruitEndDate =
                    l_bondProduct.getTradeEndDate();
                //����             = ������.����
                l_bondDomesticApplyProductInfo.coupon = String.valueOf(l_bondProduct.getCoupon());
                //����(�ېŌ�) = ������.���� �~ ( 1 - (�ŗ�.get�ŗ��~0.01))
                BigDecimal l_bdCoupon = new BigDecimal(String.valueOf(l_bondProduct.getCoupon()));
                BigDecimal l_dbTaxRate = new BigDecimal(String.valueOf(l_gentradeTaxRate.getTaxRate()));
                l_bondDomesticApplyProductInfo.rateAfterTax =
                    WEB3StringTypeUtility.formatNumber(
                        l_bdCoupon.multiply(
                            (new BigDecimal(String.valueOf(1)).subtract(
                                l_dbTaxRate.multiply(
                                    new BigDecimal(String.valueOf(0.01)))))).doubleValue());
                //����P��       = ������.���t�P��
                l_bondDomesticApplyProductInfo.applyPrice =
                    WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
                //�\���P��       = ������.�\���P��
                l_bondDomesticApplyProductInfo.applyUnit =
                    WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());
                //���s��          = ������.���s��
                l_bondDomesticApplyProductInfo.issueDate = l_bondProduct.getIssueDate();
                //���ғ�          = ������.���ғ�
                l_bondDomesticApplyProductInfo.maturityDate = l_bondProduct.getMaturityDate();
                //������1         = ������.������1
                l_bondDomesticApplyProductInfo.couponPaymentDate1 = l_bondProduct.getInterestPaymentDay1();
                //������2         = ������.������2
                l_bondDomesticApplyProductInfo.couponPaymentDate2 = l_bondProduct.getInterestPaymentDay2();
                //�N�ԗ����� = ������.�N�ԗ�����
                l_bondDomesticApplyProductInfo.yearlyInterestPayments =
                    String.valueOf(l_bondProduct.getYearlyInterestPayments());

                //�Z�b�V�������擾���������`���l��
                OpLoginSecurityService l_opLoginSecurityService =
                    (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                String l_strOrderChannel =
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

                //�戵�J�n���� =
                //�Z�b�V�������擾���������`���l�����R�[���Z���^�[or�c�ƓX�̏ꍇ�A������.�戵�J�n����
                //�Z�b�V�������擾���������`���l��������ȊO�̏ꍇ�A������.����J�n��
                if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChannel)
                    || WEB3ChannelDef.BRANCH.equals(l_strOrderChannel))
                {
                    l_bondDomesticApplyProductInfo.tradeStartDate = l_bondProduct.getTradeStartDate();
                }
                else
                {
                    l_bondDomesticApplyProductInfo.tradeStartDate = l_bondProduct.getRecruitStartDate();
                }

                //�戵�I������ =
                //�Z�b�V�������擾���������`���l�����R�[���Z���^�[or�c�ƓX�̏ꍇ�A������.�戵�I������
                //�Z�b�V�������擾���������`���l��������ȊO�̏ꍇ�A������.����I����
                if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChannel)
                    || WEB3ChannelDef.BRANCH.equals(l_strOrderChannel))
                {
                    l_bondDomesticApplyProductInfo.tradeEndDate = l_bondProduct.getTradeEndDate();
                }
                else
                {
                    l_bondDomesticApplyProductInfo.tradeEndDate = l_bondProduct.getRecruitEndDate();
                }

                //����\�敪 =
                //������.is����������\()==true ���� validate����������g������I���̏ꍇ�A1:�\
                //������.is����������\()==true ���� validate����������g���G���[�̏ꍇ�A2:����g����
                //������.is����������\()==false�̏ꍇ�A0:�s��
                if (l_bondProduct.isBondDomesticApplyPossible())
                {
                    if (l_blnValidationsCheckFlag)
                    {
                        l_bondDomesticApplyProductInfo.tradingPossDiv = WEB3TradingPossDivDef.SELL_POSS;
                    }
                    else
                    {
                        l_bondDomesticApplyProductInfo.tradingPossDiv = WEB3TradingPossDivDef.OVER_RECRUIT_LIMIT;
                    }
                }
                else
                {
                    l_bondDomesticApplyProductInfo.tradingPossDiv = WEB3TradingPossDivDef.SELL_POSS_NO;
                }

                l_lisBondDomesticApplyProductInfos.add(l_bondDomesticApplyProductInfo);
            }

            //getPageIndex( )
            int l_intPageIndex = l_pageIndexInfo.getPageIndex();

            //getTotalPages( )
            int l_intTotalPages = l_pageIndexInfo.getTotalPages();

            //getTotalRecords( )
            int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

            //createResponse( )
            l_listResponse =
                (WEB3BondDomesticApplyProductListResponse)l_listRequest.createResponse();

            //�v���p�e�B�E�Z�b�g
            //��������������ꗗ    = ����������������̔z��
            WEB3BondDomesticApplyProductInfo[] l_bondDomesticApplyProductInfos =
                new WEB3BondDomesticApplyProductInfo[l_lisBondDomesticApplyProductInfos.size()];
            l_lisBondDomesticApplyProductInfos.toArray(l_bondDomesticApplyProductInfos);
            l_listResponse.bondDomesticApplyProductList = l_bondDomesticApplyProductInfos;

            //�\���y�[�W�ԍ�       = getPageIndex()�̖߂�l
            l_listResponse.pageIndex = String.valueOf(l_intPageIndex);

            //���y�[�W��     = getTotalPages()�̖߂�l
            l_listResponse.totalPages = String.valueOf(l_intTotalPages);

            //�����R�[�h��        = getTotalRecords()�̖߂�l
            l_listResponse.totalRecords = String.valueOf(l_intTotalRecords);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_listResponse;
    }

    /**
     * (create��������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * (1)�ȉ��̕�������쐬����B<BR>
     * <BR>
     * �@@" ����J�n��(SONAR) <= ? and ����I����(SONAR) >= ?<BR>
     * �@@�@@and ���^�C�v <> ?�@@and �戵�敪�@@= ? "<BR>
     * <BR>
     * (2)����.���敪 == �h�l�������ȊO�h�̏ꍇ�A<BR>
     * �@@�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and ���^�C�v <> ? "<BR>
     * <BR>
     * (3)����.���敪 == �h�l�������̂݁h�̏ꍇ�A<BR>
     * �@@�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and ���^�C�v = ? "<BR>
     * <BR>
     * (4)�쐬�����������ԋp����B<BR>
     * @@param l_strBondDiv - (���敪)<BR>
     * ���敪<BR>
     * @@return String
     * @@roseuid 466CD702033F
     */
    protected String createQueryString(String l_strBondDiv)
    {
        final String STR_METHOD_NAME = " createQueryString(String)";
        log.entering(STR_METHOD_NAME);

        //(1)�ȉ��̕�������쐬����B
        //" ����J�n��(SONAR) <= ? and ����I����(SONAR) >= ?
        //  and ���^�C�v <> ?�@@and �戵�敪�@@= ? "
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" host_recruit_start_date <= ? ");
        l_sbQueryString.append(" AND host_recruit_end_date >= ? ");
        l_sbQueryString.append(" AND bond_type <> ? ");
        l_sbQueryString.append(" AND trade_handle_div = ? ");

        //(2)����.���敪 == �h�l�������ȊO�h�̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        //" and ���^�C�v <> ? "
        if (WEB3BondDivDef.EXCEPT_INDIVIDUAL_GOVERNMENT_BOND.equals(l_strBondDiv))
        {
            l_sbQueryString.append(" AND bond_type <> ? ");
        }

        //(3)����.���敪 == �h�l�������̂݁h�̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        //" and ���^�C�v = ? "
        if (WEB3BondDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strBondDiv))
        {
            l_sbQueryString.append(" AND bond_type = ? ");
        }

        //(4)�쐬�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^��Object�̔z��ɐݒ肵���X�|���X����B<BR>
     * <BR>
     * (1)Object�̔z����쐬����B<BR>
     * <BR>
     * (2)���ݓ������擾����B<BR>
     * <BR>
     * (3)(1)�ō쐬�����z��Ɉȉ����Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@(2)�Ŏ擾�������ݓ���<BR>
     * �@@�@@�A(2)�Ŏ擾�������ݓ���(YYYYMMDD�̂�)<BR>
     * �@@�@@�B"�O����"�i���^�C�v�j<BR>
     * �@@�@@�C"�Ǘ���/�ڋq"(�戵�敪)<BR>
     * <BR>
     * (4)����.���敪 <> �h�S���h�̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@�D"�l��������"�i���^�C�v�j<BR>
     * <BR>
     * (5)�쐬�����z���ԋp����B<BR>
     * @@param l_strBondDiv - (���敪)<BR>
     * ���敪<BR>
     * @@return Object[]
     * @@roseuid 466CD702033F
     */
    protected Object[] createQueryDataContainer(String l_strBondDiv)
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String)";
        log.entering(STR_METHOD_NAME);

        //(1)Object�̔z����쐬����B
        Object[] l_queryDataContainer = null;
        List l_lisQueryDataContainer = new ArrayList();

        //(2)���ݓ������擾����B
        Timestamp l_tsNewTime = GtlUtils.getSystemTimestamp();

        //(3)(1)�ō쐬�����z��Ɉȉ����Z�b�g����B
        //�@@�@@�@@(2)�Ŏ擾�������ݓ���
        //�@@  �A(2)�Ŏ擾�������ݓ���(YYYYMMDD�̂�)
        //�@@  �B"�O����"�i���^�C�v�j
        // �@@ �C"�Ǘ���/�ڋq"(�戵�敪)
        l_lisQueryDataContainer.add(l_tsNewTime);
        l_lisQueryDataContainer.add(WEB3DateUtility.toDay(l_tsNewTime));
        l_lisQueryDataContainer.add(BondTypeEnum.FOREIGN_BOND);
        l_lisQueryDataContainer.add(WEB3TradeHandleDivDef.MANAGER_CUSTOMER);

        //(4)����.���敪 <> �h�S���h�̏ꍇ�A�z��Ɉȉ���ǉ�����B
        //�D"�l��������"�i���^�C�v�j
        if (!WEB3BondDivDef.ALL_BOND.equals(l_strBondDiv))
        {
            l_lisQueryDataContainer.add(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
        }

        //(5)�쐬�����z���ԋp����B
        l_queryDataContainer = new Object[l_lisQueryDataContainer.size()];
        l_lisQueryDataContainer.toArray(l_queryDataContainer);

        log.exiting(STR_METHOD_NAME);
        return l_queryDataContainer;
    }

    /**
     * (create�\�[�g����������)<BR>
     * �\�[�g������������쐬����B<BR>
     * <BR>
     * �ȉ��̏����Ń\�[�g������������쐬���A�쐬�����������ԋp����B<BR>
     * <BR>
     * (1)����.���敪 == �h�l�������̂݁h�̏ꍇ�B<BR>
     * <BR>
     * �@@�@@[�\�[�g����]<BR>
     * �@@�@@"���Ҋ���"�̏���<BR>
     * �@@�@@"�񍆃R�[�h(SONAR)"�̏���<BR>
     * <BR>
     * (2)����.���敪 <> �h�l�������̂݁h�̏ꍇ�B<BR>
     * <BR>
     * �@@�@@[�\�[�g����]<BR>
     * �@@�@@"�����R�[�h�iSONAR�j"�̏����A<BR>
     * �@@�@@"�񍆃R�[�h(SONAR)"�̏���<BR>
     * @@param l_strBondDiv - (���敪)<BR>
     * ���敪<BR>
     * @@return String
     * @@roseuid 466CD702033F
     */
    protected String createSortCondString(String l_strBondDiv)
    {
        final String STR_METHOD_NAME = " createSortCondString(String)";
        log.entering(STR_METHOD_NAME);

        //(1)����.���敪 == �h�l�������̂݁h�̏ꍇ�B
        //    [�\�[�g����]
        //"���Ҋ���"�̏���
        //�@@"�񍆃R�[�h(SONAR)"�̏���
        StringBuffer l_strSortCond = new StringBuffer();
        if (WEB3BondDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strBondDiv))
        {
            l_strSortCond.append(" redemption_term ASC, ");
            l_strSortCond.append(" host_product_issue_code ASC ");
        }
        else
        {
            //(2)����.���敪 <> �h�l�������̂݁h�̏ꍇ�B
            //    [�\�[�g����]
            //"�����R�[�h�iSONAR�j"�̏����A
            //"�񍆃R�[�h(SONAR)"�̏���
            l_strSortCond.append(" host_product_code ASC, ");
            l_strSortCond.append(" host_product_issue_code ASC ");
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSortCond.toString();
    }
}
@
