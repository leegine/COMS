head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLRegistProductReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۓo�^�����Ɖ�T�[�r�XImpl�N���X(WEB3AdminAioSLRegistProductReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����F (���u) �V�K�쐬 ���f��760 766 769
Revision History : 2007/10/18 �И��� (���u) �d�l�ύX ���f��808
Revision History : 2007/10/31 �����F (���u) �d�l�ύX ���f��819
Revision History : 2007/11/08 ��іQ (���u) �d�l�ύX ���f��No.821
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.define.WEB3AioTargetPeriodDivDef;
import webbroker3.aio.define.WEB3SLSortKeyDef;
import webbroker3.aio.message.WEB3AdminSLProductRegiListRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegiListResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.aio.service.delegate.WEB3AdminAioSLRegistProductReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�S�ۓo�^�����Ɖ�T�[�r�XImpl)<BR>
 * �S�ۓo�^�����Ɖ�T�[�r�X�����N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminAioSLRegistProductReferenceServiceImpl implements
    WEB3AdminAioSLRegistProductReferenceService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLRegistProductReferenceServiceImpl.class);

    /**
     * �S�ۓo�^�����ꗗ�Ɖ�������{����B <BR>
     * <BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �P�|�P�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�ꗗ���N�G�X�g�̏ꍇ<BR>
     * �@@�|get�S�ۓo�^�����ꗗ���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�|�P�j �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�ꗗ���N�G�X�g�̏ꍇ
        //�|get�S�ۓo�^�����ꗗ���()���R�[������B
        if (l_request instanceof WEB3AdminSLProductRegiListRequest)
        {
            l_response =
                this.getSLProductRegiList((WEB3AdminSLProductRegiListRequest)l_request);
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
     * (get�S�ۓo�^�����ꗗ)<BR>
     * �S�ۓo�^�����ꗗ��ʕ\���������s���B  <BR>
     * <BR>
     * �V�[�P���X�}  <BR>
     * �uget�S�ۓo�^�����ꗗ�v�Q�ƁB <BR>
     * ========================================================== <BR>
     * �V�[�P���X�} �F(�S�ۓo�^�����Ɖ� / get�S�ۓo�^�����ꗗ) <BR>
     * ��̈ʒu�F(�����Ώۃ��R�[�h�����݂��Ȃ��ꍇ�A��O���X���[����B)<BR>
     * class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00398<BR>
     * ========================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �S�ۖ����o�^�ꗗ���N�G�X�g�N���X<BR>
     * @@return WEB3AdminSLProductRegiListResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLProductRegiListResponse getSLProductRegiList(
        WEB3AdminSLProductRegiListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSLProductRegiList(WEB3AdminSLProductRegiListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductRegiListResponse l_response = null;

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�FB0602
        //is�X�V�Ffalse
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, false);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //create��������������(�S�ۖ����o�^�ꗗ���N�G�X�g)
        String l_strQueryString = this.createQueryString(l_request);

        //create���������f�[�^�R���e�i
        Object[] l_objQueryDataContainer =
            this.createQueryDataContainer(l_strInstitutionCode, l_request);

        //create�\�[�g�L�[
        String l_sortKey = this.createSortKey(l_request.sortKeys);

        List l_lisRows = null;
        try
        {
            //getDefaultProcessor
            //Row�^�C�v�F �S�ۖ���Row.TYPE
            //Where�F create�擾����������()�̖߂�l
            //orderBy�F create�\�[�g�L�[()�̖߂�l
            //condition�F null
            //���X�g�F create���������f�[�^�R���e�i()�̖߂�l
            //�y�[�W�T�C�Y�F ���N�G�X�g.�y�[�W���\���s��
            //�y�[�W�ԍ��F ���N�G�X�g.�v���y�[�W�ԍ� - 1
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                SecurityProductRow.TYPE,
                l_strQueryString,
                l_sortKey,
                null,
                l_objQueryDataContainer,
                Integer.parseInt(l_request.pageSize),
                (Integer.parseInt(l_request.pageIndex) - 1));
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRows.isEmpty())
        {
            //�����Ώۃ��R�[�h�����݂��Ȃ��ꍇ�A��O���X���[����
            log.debug("�Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        //WEB3PageIndexInfo(l_list : List,
        //           l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[WEB3PageIndexInfo�i�j�Ɏw�肷�����]
        //l_list : doFindAllQuery�i�j�̖߂�l
        //l_intRequestPageIndex :�@@���N�G�X�g.�v���y�[�W�ԍ���int�^�ɕϊ������l
        //l_intRequestPageSize :�@@���N�G�X�g.�y�[�W���\���s����int�^�ɕϊ������l
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_lisRows, Integer.parseInt(l_request.pageIndex), Integer.parseInt(l_request.pageSize));

        //getListReturned( )
        List l_lisReturned = l_pageIndexInfo.getListReturned();

        //ArrayList( )
        List l_lisArrayList = new ArrayList();

        int l_intSize = l_lisReturned.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //create�S�ۓo�^�������(�S�ۖ���Params)
            SecurityProductRow l_securityProduceRow = (SecurityProductRow)l_lisReturned.get(i);
            WEB3SLProductInfoUnit l_productInfoUnit =
                this.createSLProductInfoUnit(new SecurityProductParams(l_securityProduceRow));
            //add(arg0 : Object)
            l_lisArrayList.add(l_productInfoUnit);
        }

        //getTotalPages( )
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();

        //getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        //createResponse( )
        l_response = (WEB3AdminSLProductRegiListResponse)l_request.createResponse();

        //�����o�^���ꗗ�F�@@ArrayList�i�j.toArray�ŕϊ������l
        WEB3SLProductInfoUnit[] l_productInfoUnits =
            new WEB3SLProductInfoUnit[l_lisArrayList.size()];
        l_response.stockLoanProductInfoList =
            (WEB3SLProductInfoUnit[])l_lisArrayList.toArray(l_productInfoUnits);
        //���y�[�W���F WEB3StringTypeUtility.formatNumber(getTotalPages()�̖߂�l)
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_intTotalPages);
        //�����R�[�h���F WEB3StringTypeUtility.formatNumber(getTotalRecords()�̖߂�l)
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_intTotalRecords);
        //�\���y�[�W�ԍ��F���N�G�X�g.�v���y�[�W�ԍ�
        l_response.pageIndex = l_request.pageIndex;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * �S�ۖ����e�[�u���̌���������������쐬����B <BR>
     * <BR>
     * <BR>
     * �P�j�@@��������������C���X�^���X�𐶐�����B   <BR>
     * <BR>
     * �Q�j�@@�،���ЃR�[�h����������������ɒǉ�����B  <BR>
     * <BR>
     * �@@"institution_code = ? "  <BR>
     * <BR>
     * �R�j�@@����:�������.�����^�C�v != null�̏ꍇ�A   <BR>
     * �����^�C�v����������������ɒǉ�����B  <BR>
     * <BR>
     * �������������� += "and product_type = ? "   <BR>
     * <BR>
     * �S�j�@@����:�������.�����R�[�h != null�̏ꍇ�A   <BR>
     * �@@�@@ �����R�[�h����������������ɒǉ�����B   <BR>
     * <BR>
     * �������������� += "and product_code like ? %"   <BR>
     * <BR>
     * �T�j�@@����:�������.�K�i�敪 != null�̏ꍇ�A   <BR>
     * �@@�@@ �K�i�敪����������������ɒǉ�����B   <BR>
     * <BR>
     * �������������� += "and fit_flg = ? "   <BR>
     * <BR>
     * �U�j�@@����:�������.�K�p���ԋ敪 != null�̏ꍇ�A   <BR>
     * <BR>
     * �U�|�P�j�@@�K�p���ԋ敪��0�F�K�p���Ԓ��̏ꍇ�A <BR>
     * �@@�������������� += "and�@@apply_term_from <= ? "   <BR>
     * �@@�������������� += "and (apply_term_to >= ?  or apply_term_to = null)" <BR>
     * <BR>
     * <BR>
     * �U�|�Q�j�@@�K�p���ԋ敪��1�F�K�p���ԊO�̏ꍇ�A <BR>
     * �@@�������������� += "and (apply_term_from > ? "   <BR>
     * �@@�������������� += "or apply_term_to < ? )"   <BR>
     * <BR>
     * <BR>
     * �V�j�@@�쐬������������������C���X�^���X��ԋp����B<BR>
     * @@param l_request - (�������)<BR>
     * �S�ۖ����o�^�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return String
     */
    private String createQueryString(WEB3AdminSLProductRegiListRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3AdminSLProductRegiListRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@��������������C���X�^���X�𐶐�����B
        StringBuffer l_sbWhere = new StringBuffer();

        //�Q�j�@@�،���ЃR�[�h����������������ɒǉ�����B
        //"institution_code = ? "
        l_sbWhere.append("institution_code = ? ");

        //�R�j�@@����:�������.�����^�C�v != null�̏ꍇ�A
        //�����^�C�v����������������ɒǉ�����B
        //�������������� += "and product_type = ? "
        if (l_request.productType != null)
        {
            l_sbWhere.append("and product_type = ? ");
        }

        //�S�j�@@����:�������.�����R�[�h != null�̏ꍇ�A
        //�@@�@@ �����R�[�h����������������ɒǉ�����B
        //�������������� += "and product_code like ? %"
        if (l_request.productCode != null)
        {
            l_sbWhere.append("and product_code like ? || '%' ");
        }

        //�T�j�@@����:�������.�K�i�敪 != null�̏ꍇ�A
        //�@@�@@ �K�i�敪����������������ɒǉ�����B
        //�������������� += "and fit_flg = ? "
        if (l_request.qualifiedDiv != null)
        {
            l_sbWhere.append("and fit_flg = ? ");
        }

        //�U�j�@@����:�������.�K�p���ԋ敪 != null�̏ꍇ�A
        if (l_request.targetPeriodDiv != null)
        {
            //�U�|�P�j�@@�K�p���ԋ敪��0�F�K�p���Ԓ��̏ꍇ�A
            //�@@�������������� += "and�@@apply_term_from <= ? "
            //�@@�������������� += "and (apply_term_to >= ?  or apply_term_to = null) "
            if (WEB3AioTargetPeriodDivDef.TARGET_PERIODING.equals(l_request.targetPeriodDiv))
            {
                l_sbWhere.append("and apply_term_from <= ? ");
                l_sbWhere.append("and (apply_term_to >= ? or apply_term_to is null)");
            }
            //�U�|�Q�j�@@�K�p���ԋ敪��1�F�K�p���ԊO�̏ꍇ�A
            //�@@�������������� += "and (apply_term_from > ? "
            //�@@�������������� += "or apply_term_to < ? )"
            else if (WEB3AioTargetPeriodDivDef.TARGET_PERIOD_OUT.equals(l_request.targetPeriodDiv))
            {
                l_sbWhere.append("and (apply_term_from > ? ");
                l_sbWhere.append("or apply_term_to < ? ) ");
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbWhere.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^���X�g�𐶐�����B  <BR>
     * <BR>
     * <BR>
     * �P�j�@@���ArrayList�𐶐�����B  <BR>
     * <BR>
     * �Q�j�@@�،���ЃR�[�h <BR>
     * <BR>
     * �����،���ЃR�[�h���P�j�̃��X�g�ɒǉ�����B  <BR>
     * <BR>
     * �R�j�@@�����^�C�v <BR>
     * <BR>
     * ����.�������.�����^�C�v != null �̏ꍇ  <BR>
     * <BR>
     * ����.�������.�����^�C�v���P�j�̃��X�g�ɒǉ�����B  <BR>
     * <BR>
     * �S�j�@@�����R�[�h <BR>
     * <BR>
     * ����.�������.�����R�[�h != null �̏ꍇ  <BR>
     * <BR>
     * ����.�������.�����R�[�h���P�j�̃��X�g�ɒǉ�����B  <BR>
     * <BR>
     * <BR>
     * �T�j�@@�K�i�敪 <BR>
     * <BR>
     * ����.�������.�K�i�敪 != null �̏ꍇ  <BR>
     * <BR>
     * ����.�������.�K�i�敪���P�j�̃��X�g�ɒǉ�����B  <BR>
     * <BR>
     * <BR>
     * �U�j�@@�K�p���ԋ敪 <BR>
     * <BR>
     * ����.�������.�K�p���ԋ敪 != null �̏ꍇ  <BR>
     * <BR>
     * �ETradingSystem.getSystemTimestamp()�̖߂�l�� <BR>
     * �@@Date�^�ɕϊ������l���P�j�̃��X�g��2��ǉ�����B  <BR>
     * <BR>
     * <BR>
     * �V�j�@@���X�g����z����擾���A�ԋp����B  <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_request - (�������)<BR>
     * �S�ۖ����o�^�ꗗ���N�G�X�g�N���X<BR>
     * @@return Object[]
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode, WEB3AdminSLProductRegiListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, WEB3AdminSLProductRegiListRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���ArrayList�𐶐�����B
        List l_lisQueryContainers = new ArrayList();

        //�Q�j�@@�،���ЃR�[�h �����،���ЃR�[�h���P�j�̃��X�g�ɒǉ�����B
        l_lisQueryContainers.add(l_strInstitutionCode);

        //�R�j�@@�����^�C�v
        //����.�������.�����^�C�v != null �̏ꍇ
        //����.�������.�����^�C�v���P�j�̃��X�g�ɒǉ�����B
        if (l_request.productType != null)
        {
            l_lisQueryContainers.add(l_request.productType);
        }

        //�S�j�@@�����R�[�h
        //����.�������.�����R�[�h != null �̏ꍇ
        //����.�������.�����R�[�h���P�j�̃��X�g�ɒǉ�����B
        if (l_request.productCode != null)
        {
            l_lisQueryContainers.add(l_request.productCode);
        }

        //�T�j�@@�K�i�敪
        //����.�������.�K�i�敪 != null �̏ꍇ
        //����.�������.�K�i�敪���P�j�̃��X�g�ɒǉ�����B
        if (l_request.qualifiedDiv != null)
        {
            l_lisQueryContainers.add(l_request.qualifiedDiv);
        }

        //�U�j�@@�K�p���ԋ敪
        //����.�������.�K�p���ԋ敪 != null �̏ꍇ
        //�ETradingSystem.getSystemTimestamp()�̖߂�l��
        //�@@Date�^�ɕϊ������l���P�j�̃��X�g��2��ǉ�����B
        if (l_request.targetPeriodDiv != null)
        {
            l_lisQueryContainers.add(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
            l_lisQueryContainers.add(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisQueryContainers.toArray();
    }

    /**
     * (create�\�[�g�L�[)<BR>
     * �\�[�g�����������ҏW����B   <BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A<BR>
     * �\�[�g����������iorder by��j��ҏW����B  <BR>
     * <BR>
     * �P�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A   <BR>
     * �@@�@@�@@�\�[�g������������쐬����B  <BR>
     * �@@�P�|�P�j�\�[�g�L�[��ҏW  <BR>
     * �@@�@@�P�|�P�|�P�j�\�[�g�L�[ = �����^�C�v�̏ꍇ  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�S�ۖ����e�[�u��.product_type <BR>
     * <BR>
     * �@@�@@�P�|�P�|�Q�j�\�[�g���� = �����R�[�h�̏ꍇ  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�S�ۖ����e�[�u��.product_code <BR>
     * <BR>
     * �@@�@@�P�|�P�|�R�j�\�[�g���� = �K�p����from�̏ꍇ  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�S�ۖ����e�[�u��.apply_term_from <BR>
     * <BR>
     * <BR>
     * �@@�P�|�Q�j�\�[�g�����ɊY������\�[�g������ҏW����B  <BR>
     * �@@�@@�@@�@@�@@�����Fasc  <BR>
     * �@@�@@�@@�@@�@@�~���Fdesc  <BR>
     * <BR>
     * �Q�j �쐬�����\�[�g�����������ԋp����B <BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �S�ۃ��[���\�[�g�L�[�I�u�W�F�N�g<BR>
     * @@return String
     */
    private String createSortKey(WEB3SLSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortKey(WEB3SLSortKey[])";
        log.entering(STR_METHOD_NAME);

        String l_strSortKey = "";

        //�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A
        //�\�[�g������������쐬����B
        int l_intLength = l_sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //�P�|�P�|�P�j�\�[�g�L�[ = �����^�C�v�̏ꍇ
            //�@@�@@�@@�@@�S�ۖ����e�[�u��.product_type
            if (WEB3SLSortKeyDef.PRODUCT_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strSortKey += " product_type ";
            }
            else if (WEB3SLSortKeyDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSortKey += " product_code ";
            }
            else if (WEB3SLSortKeyDef.TARGET_PERIOD_FORM.equals(l_sortKeys[i].keyItem))
            {
                l_strSortKey += " apply_term_from ";
            }

            //�@@�P�|�Q�j�\�[�g�����ɊY������\�[�g������ҏW����B
            //�@@�@@�@@�@@�@@�����Fasc
            //�@@�@@�@@�@@�@@�~���Fdesc
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortKey += "asc,";
            }
            else
            {
                l_strSortKey += "desc,";
            }
        }
        l_strSortKey = l_strSortKey.substring(0, l_strSortKey.length() - 1);
        log.exiting(STR_METHOD_NAME);
        return l_strSortKey;
    }

    /**
     * (create�S�ۓo�^�������)<BR>
     * �S�ۖ����o�^���C���X�^���X�𐶐�����B  <BR>
     * <BR>
     * <BR>
     * �P�j�@@�S�ۖ����o�^���C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@�g���v���_�N�g�}�l�[�W��.getProduct(arg0 )�ŁA<BR>
     * �@@�@@�@@�����I�u�W�F�N�g���擾����B <BR>
     * �@@[����] <BR>
     * �@@arg0�F�@@����:�S�ۖ���Params.����ID <BR>
     * <BR>
     * �R�j�@@�ȉ��̂悤�ɁA�S�ۖ����o�^���C���X�^���X�ɒl���Z�b�g����B <BR>
     * <BR>
     * �@@�S�ۖ����o�^���.����ID = ����:�S�ۖ���Params.����ID <BR>
     * �@@�S�ۖ����o�^���.�����R�[�h = ����:�S�ۖ���Params.�����R�[�h <BR>
     * �@@�S�ۖ����o�^���.�����^�C�v = <BR>
     * �@@�@@�@@����:�S�ۖ���Params.�����^�C�v��String�^�ɕϊ������l <BR>
     * �@@�S�ۖ����o�^���.�����R�[�h = ����:�S�ۖ���Params.�����R�[�h <BR>
     * �@@�S�ۖ����o�^���.������ = getProduct�i�j�̖߂�l.������ <BR>
     * �@@�S�ۖ����o�^���.�K�i�敪 = ����:�S�ۖ���Params.�K�i�敪 <BR>
     * �@@�S�ۖ����o�^���.�|�� = <BR>
     * �@@�@@�@@����:�S�ۖ���Params.�]���|�ڂ�String�^�ɕϊ������l <BR>
     * �@@�S�ۖ����o�^���.�K�p����from = ����:�S�ۖ���Params.�K�p����from <BR>
     * �@@�S�ۖ����o�^���.�K�p����to = ����:�S�ۖ���Params.�K�p����to <BR>
     * �@@�S�ۖ����o�^���.���R = ����:�S�ۖ���Params.���R <BR>
     * <BR>
     * �S�j�@@�S�ۖ����o�^���C���X�^���X��ԋp����B<BR>
     * @@param l_securityProductParams - (�S�ۖ���Params)<BR>
     * �S�ۖ���Params�I�u�W�F�N�g<BR>
     * @@return WEB3SLProductInfoUnit
     * @@throws WEB3BaseException
     */
    private WEB3SLProductInfoUnit createSLProductInfoUnit(SecurityProductParams l_securityProductParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSLProductInfoUnit(SecurityProductParams)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�S�ۖ����o�^���C���X�^���X�𐶐�����B
        WEB3SLProductInfoUnit l_slProductInfoUnit = new WEB3SLProductInfoUnit();

        //�Q�j�@@�g���v���_�N�g�}�l�[�W��.getProduct(arg0 )�ŁA�����I�u�W�F�N�g���擾����B
        //�@@[����]
        //�@@arg0�F�@@����:�S�ۖ���Params.����ID
        Product l_product = null;
        try
        {
            TradingModule l_tradingModule =
                GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
            ProductManager l_productMgr = l_tradingModule.getProductManager();
            l_product = l_productMgr.getProduct(l_securityProductParams.getProductId());
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

        //�R�j�@@�ȉ��̂悤�ɁA�S�ۖ����o�^���C���X�^���X�ɒl���Z�b�g����B
        //�@@�S�ۖ����o�^���.����ID = ����:�S�ۖ���Params.����ID
        l_slProductInfoUnit.productId = l_securityProductParams.getProductId();
        //�@@�S�ۖ����o�^���.�����R�[�h = ����:�S�ۖ���Params.�����R�[�h
        l_slProductInfoUnit.productCode = l_securityProductParams.getProductCode();
        //�@@�S�ۖ����o�^���.�����^�C�v = ����:�S�ۖ���Params.�����^�C�v��String�^�ɕϊ������l
        l_slProductInfoUnit.productType = l_securityProductParams.getProductType().intValue() + "";
        //�@@�S�ۖ����o�^���.������ = getProduct�i�j�̖߂�l.������
        l_slProductInfoUnit.productName = l_product.getStandardName();
        //�@@�S�ۖ����o�^���.�K�i�敪 = ����:�S�ۖ���Params.�K�i�敪
        l_slProductInfoUnit.qualifiedDiv = l_securityProductParams.getFitFlg();
        //�@@�S�ۖ����o�^���.�|�� = ����:�S�ۖ���Params.�]���|�ڂ�String�^�ɕϊ������l
        l_slProductInfoUnit.weight = l_securityProductParams.getEstimationRatio() + "";
        //�@@�S�ۖ����o�^���.�K�p����from = ����:�S�ۖ���Params.�K�p����from
        l_slProductInfoUnit.targetPeriodFrom = l_securityProductParams.getApplyTermFrom();
        //�@@�S�ۖ����o�^���.�K�p����to = ����:�S�ۖ���Params.�K�p����to
        l_slProductInfoUnit.targetPeriodTo = l_securityProductParams.getApplyTermTo();
        //�@@�S�ۖ����o�^���.���R = ����:�S�ۖ���Params.���R
        l_slProductInfoUnit.reason = l_securityProductParams.getReason();

        //�S�j�@@�S�ۖ����o�^���C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_slProductInfoUnit;
    }
}
@
