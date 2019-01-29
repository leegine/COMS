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
filename	WEB3AdminBondDomesticProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@ /**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ����������ꗗ�T�[�r�XImpl(WEB3AdminBondDomesticProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 �����q(���u) �V�K�쐬 �d�l�ύX�E���f��No.193,No.199,No.209
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondSortKeyDef;
import webbroker3.bd.define.WEB3BondTypeListDef;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchConditionUnit;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayResponse;
import webbroker3.bd.message.WEB3BondDomesticProductRefInfo;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҍ����������ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��ҍ����������ꗗ�T�[�r�XImpl<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListServiceImpl
    implements WEB3AdminBondDomesticProductListService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductListServiceImpl.class);

    /**
     * @@roseuid 4691D3AE017E
     */
    public WEB3AdminBondDomesticProductListServiceImpl()
    {

    }

    /**
     * �Ǘ��ҍ����������ꗗ�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * get������ʕ\��()�Aget�����ꗗ��ʕ\��()<BR>
     * �̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4663A03601F3
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

        // get������ʕ\��
        if (l_request instanceof WEB3AdminBondDomesticProductListSearchDisplayRequest)
        {
            l_response =
                this.getSearchScreenDisplay(
                    (WEB3AdminBondDomesticProductListSearchDisplayRequest)l_request);
        }

        // get�����ꗗ��ʕ\��
        else if (l_request instanceof WEB3AdminBondDomesticProductListDisplayRequest)
        {
            l_response =
                this.getProductListScreenDisplay(
                    (WEB3AdminBondDomesticProductListDisplayRequest)l_request);
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
     * (get������ʕ\��)<BR>
     * �Ǘ��ҍ����������ꗗ������ʕ\���������s�Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�F�u�i�Ǘ��ҍ����������ꗗ�jget������ʕ\���v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondDomesticProductListSearchDisplayResponse
     * @@throws WEB3BaseException
     * @@roseuid 4663AD29037A
     */
    protected WEB3AdminBondDomesticProductListSearchDisplayResponse getSearchScreenDisplay(
        WEB3AdminBondDomesticProductListSearchDisplayRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSearchScreenDisplay(WEB3AdminBondDomesticProductListSearchDisplayRequest)";
        log.entering(STR_METHOD_NAME);

        // getInstanceFrom���O�C�����( )
        WEB3Administrator l_adminstrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        // �@@�\�J�e�S���[�R�[�h�@@�F�@@�\�J�e�S���R�[�h.�������Ǘ�
        // is�X�V�@@�Ffalse
        l_adminstrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE, false);

        // createResponse( )
        WEB3AdminBondDomesticProductListSearchDisplayResponse l_domesticProductListResponse =
            (WEB3AdminBondDomesticProductListSearchDisplayResponse)l_request.createResponse();

        // �v���p�e�B�E�Z�b�g
        String[] l_strBondTypeList = new String[]{
            WEB3BondTypeListDef.INDIVIDUAL_GOVERNMENT_BOND,
            WEB3BondTypeListDef.CORPORATE_BOND};
        l_domesticProductListResponse.bondTypeList = l_strBondTypeList;

        String[] l_strTradeHandleDivList =
            new String[]{WEB3TradeHandleDivDef.DISABLED, WEB3TradeHandleDivDef.MANAGER_CUSTOMER};
        l_domesticProductListResponse.tradeHandleDivList = l_strTradeHandleDivList;

        log.exiting(STR_METHOD_NAME);
        return l_domesticProductListResponse;
    }

    /**
     * (get�����ꗗ��ʕ\��)<BR>
     * �Ǘ��ҍ����������ꗗ�����������s�Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�F�u�i�Ǘ��ҍ����������ꗗ�jget�����ꗗ��ʕ\���v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminBondDomesticProductListDisplayResponse
     * @@throws WEB3BaseException
     * @@roseuid 4663B960008C
     */
    protected WEB3AdminBondDomesticProductListDisplayResponse getProductListScreenDisplay(
        WEB3AdminBondDomesticProductListDisplayRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "getProductListScreenDisplay(WEB3AdminBondDomesticProductListDisplayRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE, false);

        // get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // create��������������(�Ǘ��ҍ����������ꗗ��������)
        // ���������F�@@���N�G�X�g�f�[�^.�Ǘ��ҍ����������ꗗ��������
        String l_strCreateQueryString = this.createQueryString(l_request.searchCondition);

        // create���������f�[�^�R���e�i(�Ǘ��ҍ����������ꗗ��������)
        // ���������F�@@���N�G�X�g�f�[�^.�Ǘ��ҍ����������ꗗ��������
        Object[] l_createQueryDataContainers =
            this.createQueryDataContainer(l_request.searchCondition);

        // create�\�[�g����������(���\�[�g�L�[[])
        // �\�[�g�L�[�ꗗ�@@�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strCreateSortCond = this.createSortCond(l_request.sortKeys);

        // get���������X�g(String, String, Object[], String)
        // �،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        // ��������������F�@@create��������������()�̖߂�l
        // ���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l
        // �\�[�g����������F�@@create�\�[�g����������()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        List l_lisBondProducts = l_bondProductManager.getBondProductList(
            l_strInstitutionCode,
            l_strCreateQueryString,
            l_createQueryDataContainers,
            l_strCreateSortCond);

        // WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        // l_list�F�@@get���������X�g()�̖߂�l
        // l_intRequestPageIndex�F�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
        // l_intRequestPageSize�F�@@���N�G�X�g�f�[�^.�y�[�W���\���s��
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);

        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_lisBondProducts,
                l_intRequestPageIndex,
                l_intRequestPageSize);

        // getArrayReturned(l_classType : Class)
        // �\���Ώۃy�[�W�ɊY������I�u�W�F�N�g�z����擾����B
        // [����]
        // _classType�F�@@������Row.class
        BondProductRow[] l_bondProductRows =
            (BondProductRow[])l_pageIndexInfo.getArrayReturned(
                BondProductRow.class);

        // getArrayReturned()�̖߂�l�̗v�f����Loop����
        int l_intLength = l_bondProductRows.length;
        List l_lisContainers = new ArrayList();

        for (int i = 0; i < l_intLength; i++)
        {
            // �����������Ɖ���( )
            WEB3BondDomesticProductRefInfo l_domesticProductRefInfo =
                new WEB3BondDomesticProductRefInfo();

            BondProductRow l_bondProductRow = l_bondProductRows[i];

            // �v���p�e�B�E�Z�b�g
            // ����ID         �F������.����ID
            l_domesticProductRefInfo.productId = l_bondProductRow.getProductId() + "";

            // �����R�[�h           �F������.�����R�[�h�iSONAR)
            l_domesticProductRefInfo.productCode = l_bondProductRow.getHostProductCode();

            // �񍆃R�[�h           �F������.�񍆃R�[�h�iSONAR�j
            l_domesticProductRefInfo.productIssueCode = l_bondProductRow.getHostProductIssueCode();

            // �������iHOST)       �F������.HOST�������P
            l_domesticProductRefInfo.productNameHost = l_bondProductRow.getHostProductName1();

            // �������iWEB3)       �F������.������
            l_domesticProductRefInfo.productNameWEB3 = l_bondProductRow.getProductName();

            // ����P��            �F������.���t�P��
            if (!l_bondProductRow.getBuyPriceIsNull())
            {
                l_domesticProductRefInfo.applyPrice =
                    WEB3StringTypeUtility.formatNumber(l_bondProductRow.getBuyPrice());
            }
            else
            {
                l_domesticProductRefInfo.applyPrice = null;
            }

            // �戵�敪            �F������.�戵�敪
            l_domesticProductRefInfo.tradeHandleDiv = l_bondProductRow.getTradeHandleDiv();

            // ����          �F������.����
            l_domesticProductRefInfo.coupon =
                WEB3StringTypeUtility.formatNumber(l_bondProductRow.getCoupon());

            // ���s��         �F������.���s��
            l_domesticProductRefInfo.issueDate = WEB3DateUtility.toDay(l_bondProductRow.getIssueDate());

            // ���ғ�         �F������.���ғ�
            l_domesticProductRefInfo.maturityDate = WEB3DateUtility.toDay(l_bondProductRow.getMaturityDate());

            // �N�ԗ�����      �F������.�N�ԗ�����
            l_domesticProductRefInfo.yearlyInterestPayments =
                l_bondProductRow.getYearlyInterestPayments() + "";

            // �������P            �F������.�������P
            l_domesticProductRefInfo.couponPaymentDate1 =
                l_bondProductRow.getInterestPaymentDay1st();

            // �������Q            �F������.�������Q
            l_domesticProductRefInfo.couponPaymentDate2 =
                l_bondProductRow.getInterestPaymentDay2nd();

            l_lisContainers.add(l_domesticProductRefInfo);
        }

        // getPageIndex( )
        // �\���y�[�W�ԍ����擾����B
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();

        // getTotalPages( )
        // ���y�[�W�����擾����B
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();

        // getTotalRecords( )
        // �����R�[�h�����擾����B
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        // createResponse( )
        // ���X�|���X�𐶐�����B
        WEB3AdminBondDomesticProductListDisplayResponse l_domesticProductListDisplayResponse =
            (WEB3AdminBondDomesticProductListDisplayResponse)l_request.createResponse();

        // �v���p�e�B�E�Z�b�g
        // �\���y�[�W�ԍ�          �FgetPageIndex()�̖߂�l
        l_domesticProductListDisplayResponse.pageIndex = l_intPageIndex + "";
        // ���y�[�W��           �FgetTotalPages()�̖߂�l
        l_domesticProductListDisplayResponse.totalPages = l_intTotalPages + "";
        // �����R�[�h��          �FgetTotalRecords()�̖߂�l
        l_domesticProductListDisplayResponse.totalRecords = l_intTotalRecords + "";
        // �����Ɖ���ꗗ            �F�����������Ɖ���̔z��
        WEB3BondDomesticProductRefInfo[] l_domesticProductRefInfos =
            new WEB3BondDomesticProductRefInfo[l_lisContainers.size()];
        l_lisContainers.toArray(l_domesticProductRefInfos);
        l_domesticProductListDisplayResponse.productRefInfoList = l_domesticProductRefInfos;

        log.exiting(STR_METHOD_NAME);
        return l_domesticProductListDisplayResponse;
    }

    /**
     * (create��������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j�ȉ��̕�������쐬����B<BR>
     * <BR>
     * �@@�@@"���^�C�v != ?"<BR>
     * <BR>
     * �Q�j����.��������.���^�C�v�� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@"and ���^�C�v = ?"<BR>
     * <BR>
     * �R�j����.��������.�����R�[�h �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and �����R�[�h�iSONAR�j = ? "<BR>
     * <BR>
     * �S�j����.��������.�񍆃R�[�h �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and �񍆃R�[�h�iSONAR�j = ? "<BR>
     * <BR>
     * �T�j����.��������.�������iHOST�j �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and HOST�������P like ? "<BR>
     * <BR>
     * �U�j����.��������.�������iWEB3�j �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and ������ like ? "<BR>
     * <BR>
     * �V�j����.��������.���s�� �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and ���s�� = ? "<BR>
     * <BR>
     * �W�j����.��������.���ғ� �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@"and ���ғ� = ? "<BR>
     * <BR>
     * �X�j����.�������������� �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and ( ������1 = ?�@@or ������2 = ? ) " <BR>
     * <BR>
     * �P�O�j����.��������.�戵�敪 �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B<BR>
     * <BR>
     * �@@" and �戵�敪 = ? "<BR>
     * <BR>
     * �P�P�j�쐬�����������ԋp����B<BR>
     * <BR>
     * @@param l_searchCondition - (��������)<BR>
     * ��������<BR>
     * @@return String
     * @@roseuid 4663D208005D
     */
    protected String createQueryString(
        WEB3AdminBondDomesticProductListSearchConditionUnit l_searchCondition)
    {
        final String STR_METHOD_NAME =
            "createQueryString(WEB3AdminBondDomesticProductListSearchConditionUnit)";
        log.entering(STR_METHOD_NAME);

        // �P�j�ȉ��̕�������쐬����B
        // "���^�C�v != ?"
        String l_strQueryString = " bond_type != ? ";

        // �Q�j����.��������.���^�C�v�� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        // "and ���^�C�v = ?"
        if (l_searchCondition.bondType != null)
        {
            l_strQueryString += " and bond_type = ? ";
        }

        // �R�j����.��������.�����R�[�h �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        //  " and �����R�[�h�iSONAR�j = ? "
        if (l_searchCondition.productCode != null)
        {
            l_strQueryString += " and host_product_code = ? ";
        }

        // �S�j����.��������.�񍆃R�[�h �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        // " and �񍆃R�[�h�iSONAR�j = ? "
        if (l_searchCondition.productIssueCode != null)
        {
            l_strQueryString += " and host_product_issue_code = ? ";
        }

        // �T�j����.��������.�������iHOST�j �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        // " and HOST�������P like ? "
        if (l_searchCondition.productNameHost != null)
        {
            l_strQueryString += " and host_product_name_1 like ? ";
        }

        // �U�j����.��������.�������iWEB3�j �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        //  and ������ like ? "
        if (l_searchCondition.productNameWEB3 != null)
        {
            l_strQueryString += " and product_name like ? ";
        }

        // �V�j����.��������.���s�� �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        // " and ���s�� = ? "
        if (l_searchCondition.issueDate != null)
        {
            l_strQueryString += " and issue_date = ? ";
        }

        // �W�j����.��������.���ғ� �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        // " and ���ғ� = ? "
        if (l_searchCondition.maturityDate != null)
        {
            l_strQueryString += " and maturity_date = ? ";
        }

        // �X�j����.�������������� �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        // " and ( ������1 = ?�@@or ������2 = ? ) "
        if (l_searchCondition.interestPaymentDay != null)
        {
            l_strQueryString += " and ( interest_payment_day_1st = ?�@@or interest_payment_day_2nd = ? ) ";
        }

        // �P�O�j����.��������.�戵�敪 �� null �̏ꍇ�A�ȉ��̕�������Ō���ɒǉ�����B
        // " and �戵�敪 = ? "
        if (l_searchCondition.tradeHandleDiv != null)
        {
            l_strQueryString += " and trade_handle_div = ? ";
        }

        // �P�P�j�쐬�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^��Object�̔z����쐬����B<BR>
     * <BR>
     * �P�jObject�̔z����쐬����B<BR>
     * <BR>
     * �Q�j�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@"�O����"�i�����^�C�v�j<BR>
     * <BR>
     * �R�j����.��������.���^�C�v �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@����.��������.���^�C�v<BR>
     * <BR>
     * �S�j����.��������.�����R�[�h �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@����.��������.�����R�[�h<BR>
     * <BR>
     * �T�j����.��������.�񍆃R�[�h �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@����.��������.�񍆃R�[�h<BR>
     * <BR>
     * �U�j����.��������.�������iHOST�j �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@% + ����.��������.�������iHOST) + %<BR>
     * <BR>
     * �V�j����.��������.�������iWEB3�j �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@% + ����.��������.�������iWEB3) + %<BR>
     * <BR>
     * �W�j����.��������.���s�� �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@����.��������.���s��<BR>
     * <BR>
     * �X�j����.��������.���ғ� �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@����.��������.���ғ�<BR>
     * <BR>
     * �P�O�j����.��������.������ �� null �̏ꍇ�A�z��Ɉȉ����Q��ǉ�����B<BR>
     * <BR>
     * �@@�@@����.��������.������<BR>
     * <BR>
     * �P�P�j����.��������.�戵�敪 �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B<BR>
     * <BR>
     * �@@�@@����.��������.�戵�敪<BR>
     * <BR>
     * �P�Q�j�쐬�����z���ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_searchCondition - (��������)<BR>
     * ��������<BR>
     * @@return Object[]
     * @@roseuid 4663D2D80251
     */
    protected Object[] createQueryDataContainer(
        WEB3AdminBondDomesticProductListSearchConditionUnit l_searchCondition)
    {
        final String STR_METHOD_NAME =
            "createQueryData(WEB3AdminBondDomesticProductListSearchConditionUnit)";
        log.entering(STR_METHOD_NAME);

        // �P�jObject�̔z����쐬����B
        List l_lisCreateQueryDataContainers = new ArrayList();
        // �Q�j�z��Ɉȉ���ǉ�����B
        // "�O����"�i�����^�C�v�j
        l_lisCreateQueryDataContainers.add(new Integer(BondTypeEnum.FOREIGN_BOND.intValue()));

        // �R�j����.��������.���^�C�v �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B
        // ����.��������.���^�C�v
        if (l_searchCondition.bondType != null)
        {
            l_lisCreateQueryDataContainers.add(new Integer(l_searchCondition.bondType));
        }

        // �S�j����.��������.�����R�[�h �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B
        //  ����.��������.�����R�[�h
        if (l_searchCondition.productCode != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.productCode);
        }

        // �T�j����.��������.�񍆃R�[�h �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B
        // ����.��������.�񍆃R�[�h
        if (l_searchCondition.productIssueCode != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.productIssueCode);
        }

        // �U�j����.��������.�������iHOST�j �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B
        // % + ����.��������.�������iHOST) + %
        if (l_searchCondition.productNameHost != null)
        {
            l_lisCreateQueryDataContainers.add("%" + l_searchCondition.productNameHost + "%");
        }

        // �V�j����.��������.�������iWEB3�j �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B
        //  % + ����.��������.�������iWEB3) + %
        if (l_searchCondition.productNameWEB3 != null)
        {
            l_lisCreateQueryDataContainers.add("%" + l_searchCondition.productNameWEB3 + "%");
        }

        // �W�j����.��������.���s�� �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B
        // ����.��������.���s��
        if (l_searchCondition.issueDate != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.issueDate);
        }

        // �X�j����.��������.���ғ� �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B
        // ����.��������.���ғ�
        if (l_searchCondition.maturityDate != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.maturityDate);
        }

        // �P�O�j����.��������.������ �� null �̏ꍇ�A�z��Ɉȉ����Q��ǉ�����B
        // ����.��������.������
        if (l_searchCondition.interestPaymentDay != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.interestPaymentDay);
            l_lisCreateQueryDataContainers.add(l_searchCondition.interestPaymentDay);
        }

        // �P�P�j����.��������.�戵�敪 �� null �̏ꍇ�A�z��Ɉȉ���ǉ�����B
        // ����.��������.�戵�敪
        if (l_searchCondition.tradeHandleDiv != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.tradeHandleDiv);
        }

        // �P�Q�j�쐬�����z���ԋp����B
        Object[] l_objReturns = new Object[l_lisCreateQueryDataContainers.size()];
        l_lisCreateQueryDataContainers.toArray(l_objReturns);

        log.exiting(STR_METHOD_NAME);
        return l_objReturns;
    }

    /**
     * (create�\�[�g����������)<BR>
     * �\�[�g������������쐬����B<BR>
     * <BR>
     * �P�j����.�\�[�g�L�[�ꗗ�̗v�f�����A�L�[���ڂɑΉ�����e�[�u���̗񕨗�����<BR>
     * �@@�@@�����^�~���w���t�������\�[�g������������쐬����B<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B<BR>
     * �@@�@@�@@���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q�ƁB<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�����R�[�h�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�@@������.�����R�[�h�iSONAR)<BR>
     * �@@�@@�@@�@@�@@�񍆃R�[�h�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�@@������.�񍆃R�[�h�iSONAR�j<BR>
     * �@@�@@�@@�@@�@@���s���@@�@@�@@�@@�@@�@@�@@�@@ �@@�@@ �F�@@������.���s��<BR>
     * �@@�@@�@@�@@�@@���ғ��@@�@@�@@�@@�@@�@@�@@�@@ �@@�@@ �F�@@������.���ғ�<BR>
     * �@@�@@�@@�@@�@@�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�@@������.����<BR>
     * <BR>
     * �@@�@@�E�����^�~���w��́A���\�[�g�L�[.�����^�~�� �ɏ]���ݒ肷��B<BR>
     * <BR>
     * �Q�j�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * @@param l_sortKeys - (�\�[�g�L�[�ꗗ)<BR>
     * �\�[�g�L�[�ꗗ<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4663C18F03A9
     */
    protected String createSortCond(WEB3BondSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3BondSortKey[])";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�����̃\�[�g�L�[�������L�[���ڂ��A�����J�ݐR���҂��񕨗����ɕϊ�����
        //�\�[�g�L�[�̎w��̒ʂ�A�\�[�g����������iorder by��j��ҏW���ԋp����B
        //���@@�\�[�g�L�[�Ɏw�肳��鍀�ڂ͈ȉ��̒ʂ�B
        String l_strSortCond = "";
        int l_intSortKeyLength = l_sortKeys.length;

        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            WEB3BondSortKey l_sortKey = l_sortKeys[i];

            //�����R�[�h�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�@@������.�����R�[�h�iSONAR)
            if (WEB3BondSortKeyDef.PRODUCT_CODE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " host_product_code ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            //�񍆃R�[�h�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�@@������.�񍆃R�[�h�iSONAR�j
            else if (WEB3BondSortKeyDef.PRODUCT_ISSUE_CODE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " host_product_issue_code ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            // ���s���@@�@@�@@�@@�@@�@@�@@�@@ �@@�@@ �F�@@������.���s��
            else if (WEB3BondSortKeyDef.ISSUE_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " issue_date ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            // ���ғ��@@�@@�@@�@@�@@�@@�@@�@@ �@@�@@ �F�@@������.���ғ�
            else if (WEB3BondSortKeyDef.MATURITY_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " maturity_date ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            // �����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�@@������.����
            else if (WEB3BondSortKeyDef.COUPON.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " coupon ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
        }

        l_strSortCond =
            l_strSortCond.substring(0, l_strSortCond.length() - 2) + " ";

        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }
}
@
