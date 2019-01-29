head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ��ӏ��Ɖ�T�[�r�XImpl(WEB3AdminEquityAttentionInfoReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30�@@���L���E(���u) �V�K�쐬 �d�l�ύX���f��No.216,No.220,No.225,No.227,No.228
Revision History : 2009/01/20�@@�И���(���u) �d�l�ύX���f��No.231
Revision History : 2009/02/11�@@���ʗ�(���u) �d�l�ύX���f��No.234
Revision History : 2009/02/17�@@���ʗ�(���u) �c�a�X�V�d�lNo.028
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySortKeyItemNameDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefDetail;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefSortKey;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoReferenceService;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��Ғ��ӏ��Ɖ�T�[�r�XImpl)<BR>
 * �Ǘ��Ғ��ӏ��Ɖ�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���L���E
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityAttentionInfoReferenceService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoReferenceServiceImpl.class);

    /**
     * @@roseuid 49588AED03C8
     */
    public WEB3AdminEquityAttentionInfoReferenceServiceImpl()
    {

    }

    /**
     * ���ӏ��Ɖ�T�[�r�X���s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��ȉ��̃��\�b�h��<BR>
     * �Ăѕ�����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���A<BR>
     * �@@[�Ǘ��ҁE���ӏ��Ɖ���̓��N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * �@@[�Ǘ��ҁE���ӏ��Ɖ�N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.get�Ɖ���()���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 494226FD0333
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

        if (l_request instanceof WEB3AdminEqAttentionInfoRefInpRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminEqAttentionInfoRefInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminEqAttentionInfoRefRefRequest)
        {
           l_response =
                this.getReferenceScreen(
                    (WEB3AdminEqAttentionInfoRefRefRequest)l_request);
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
     * (get���͉��)<BR>
     * ���ӏ��Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���ӏ��Ɖ�jget���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���ӏ��Ɖ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminEqAttentionInfoRefInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 4945BC980227
     */
    protected WEB3AdminEqAttentionInfoRefInpResponse getInputScreen(
        WEB3AdminEqAttentionInfoRefInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminEqAttentionInfoRefInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;

        //getInstanceFrom���O�C�����
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_ATTENTION_INFO_REFERENCE,
            false);

        //createResponse
        WEB3AdminEqAttentionInfoRefInpResponse l_response =
            (WEB3AdminEqAttentionInfoRefInpResponse)l_request.createResponse();

        //get��񔭐�����From
        l_response.infoOccuredDateFrom = this.getInfoOccuredDateFrom();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * ���ӏ��Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���ӏ��Ɖ�jget�Ɖ��ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���ӏ��Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminEqAttentionInfoRefRefResponse
     * @@throws WEB3BaseException
     * @@roseuid 4945BDCC0372
     */
    protected WEB3AdminEqAttentionInfoRefRefResponse getReferenceScreen(
        WEB3AdminEqAttentionInfoRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminEqAttentionInfoRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        String l_strInstituionCode = null;
        String l_strQueryString = null;
        String[] l_queryDataContainers = null;
        String l_strSortCond = null;
        AttentionInfoHistoryRow[] l_attentionInfoHistoryRows = null;
        WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetails = null;

        //validate
        l_request.validate();

        //getInstanceFrom���O�C�����
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_ATTENTION_INFO_REFERENCE,
            false);

        //get�،���ЃR�[�h
        l_strInstituionCode = l_administrator.getInstitutionCode();

        //create��������������
        l_strQueryString = this.createQueryString(l_request);

        //create���������f�[�^�R���e�i
        l_queryDataContainers = this.createQueryDataContainer(l_request, l_strInstituionCode);

        //create�\�[�g����
        l_strSortCond = this.createSortCond(l_request.sortKeys);

        //get���ӏ�񗚗��ꗗ
        l_attentionInfoHistoryRows = this.getAttentionInfoHistoryList(
            l_strQueryString, l_queryDataContainers, l_strSortCond);

        //createResponse
        WEB3AdminEqAttentionInfoRefRefResponse l_response =
            (WEB3AdminEqAttentionInfoRefRefResponse)l_request.createResponse();

        //get���ӏ�񗚗��ꗗ()�̖߂�l == null�̏ꍇ
        if (l_attentionInfoHistoryRows == null || l_attentionInfoHistoryRows.length == 0)
        {
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.attentionInfoRefList = null;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_attentionInfoHistoryRows,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        // create���ӏ��ꗗ
        l_eqAttentionInfoRefDetails =
            this.createAttentionInfoList((AttentionInfoHistoryRow[])l_pageIndexInfo.getArrayReturned(
                AttentionInfoHistoryRow.class));

        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        l_response.attentionInfoRefList = l_eqAttentionInfoRefDetails;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get��񔭐�����From)<BR>
     * ��񔭐�����From���쐬����B<BR>
     * <BR>
     * �P�j���ݓ����̎擾<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)<BR>
     * �ɂČ��ݓ������擾<BR>
     * <BR>
     * �Q�j�擾�������uYYYYMMDD�v�Ńt�H�[�}�b�g���A�u000000�v��A��<BR>
     * <BR>
     * �R�j�쐬����������ԋp<BR>
     * @@return String
     * @@roseuid 4945BFB9003B
     */
    protected String getInfoOccuredDateFrom()
    {
        final String STR_METHOD_NAME = "getInfoOccuredDateFrom()";
        log.entering(STR_METHOD_NAME);
        //���ݓ����̎擾
        Date l_datCurrentDate = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        //�擾�������uYYYYMMDD�v�Ńt�H�[�}�b�g���A�u000000�v��A��
        String l_strCurrentDate = null;
        l_strCurrentDate = WEB3DateUtility.formatDate(WEB3DateUtility.toDay(l_datCurrentDate),
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

        log.exiting(STR_METHOD_NAME);
        //�쐬����������ԋp
        return l_strCurrentDate;
    }

    /**
     * (create��������������)<BR>
     * <BR>
     * ����������������쐬����B <BR>
     * <BR>
     * �P�j�@@��������������C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@�،���ЃR�[�h����������������ɒǉ�����B <BR>
     * <BR>
     * �������������� =�@@"institution_code = ? " <BR>
     * <BR>
     * �R�j�@@�p�����[�^.���N�G�X�g�f�[�^.���ӏ����!=null�̏ꍇ�A <BR>
     * �@@�@@ ���ӏ���ʂ���������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and attention_info_type = ? " <BR>
     * <BR>
     * �S�j�@@�p�����[�^.���N�G�X�g�f�[�^.���ӏ��敪�R�[�h!=null�̏ꍇ�A <BR>
     * �@@�@@ ���ӏ��敪�R�[�h����������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and attention_info_div_code = ? " <BR>
     * <BR>
     * �T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ�A <BR>
     * �@@�@@ �s��h�c����������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and market_id = ? " <BR>
     * <BR>
     * �U�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����R�[�h!= null�̏ꍇ�A <BR>
     * �@@����ID����������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and product_id = ? " <BR>
     * <BR>
     * �V�j�@@�p�����[�^.���N�G�X�g�f�[�^.�L����!= null�̏ꍇ�A <BR>
     * �@@�L��������������������ɒǉ�����B <BR>
     * <BR>
     * �������������� += "and valid_until_biz_date = ? " <BR>
     * <BR>
     * �W�j�@@�p�����[�^.���N�G�X�g�f�[�^.��񔭐�����From!= null�̏ꍇ�A <BR>
     * �@@��񔭐�����From����������������ɒǉ�����B <BR>
     * <BR>
     * �@@�������������� += "and info_generation_timestamp >= to_date(?,'YYYYMMDDHH24MISS') " <BR>
     * <BR>
     * �X�j�@@�p�����[�^.���N�G�X�g�f�[�^.��񔭐�����To!= null�̏ꍇ�A <BR>
     * �@@��񔭐�����To����������������ɒǉ�����B <BR>
     * <BR>
     * �@@�������������� += "and info_generation_timestamp <= to_date(?,'YYYYMMDDHH24MISS') " <BR>
     * <BR>
     * �P�O�j�@@�쐬������������������C���X�^���X��ԋp����B <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return String
     * @@roseuid 4945E58B0128
     */
    protected String createQueryString(WEB3AdminEqAttentionInfoRefRefRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3AdminEqAttentionInfoRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        //��������������C���X�^���X�𐶐�����B
        StringBuffer l_sbQueryString = new StringBuffer();

        //�،���ЃR�[�h����������������ɒǉ�����B
        l_sbQueryString.append("institution_code = ? ");

        //���ӏ����!=null�̏ꍇ�A���ӏ���ʂ���������������ɒǉ�����B
        if (l_request.attentionInfoType != null)
        {
            l_sbQueryString.append("and attention_info_type = ? ");
        }

        //���ӏ��敪�R�[�h!=null�̏ꍇ�A���ӏ��敪�R�[�h����������������ɒǉ�����B
        if (l_request.attentionInfoDivCode != null)
        {
            l_sbQueryString.append("and attention_info_div_code = ? ");
        }

        //�s��R�[�h != null�̏ꍇ�A�s��h�c����������������ɒǉ�����B
        if (l_request.marketCode != null)
        {
            l_sbQueryString.append("and market_id = ? ");
        }

        //�����R�[�h!= null�̏ꍇ�A����ID����������������ɒǉ�����B
        if (l_request.productCode != null)
        {
            l_sbQueryString.append("and product_id = ? ");
        }

        //�L����!= null�̏ꍇ�A�L��������������������ɒǉ�����B
        if (l_request.validDate != null)
        {
            l_sbQueryString.append("and valid_until_biz_date = ? ");
        }

        //��񔭐�����From!= null�̏ꍇ�A��񔭐�����From����������������ɒǉ�����B
        if (l_request.infoOccuredDateFrom != null)
        {
            l_sbQueryString.append("and info_generation_timestamp >= to_date(?,'YYYYMMDDHH24MISS') ");
        }

        //��񔭐�����To!= null�̏ꍇ�A��񔭐�����To����������������ɒǉ�����B
        if (l_request.infoOccuredDateTo != null)
        {
            l_sbQueryString.append("and info_generation_timestamp <= to_date(?,'YYYYMMDDHH24MISS') ");
        }

        //�쐬������������������C���X�^���X��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i���쐬����B <BR>
     * <BR>
     * �P�j�@@�����l���i�[����List�I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�،���ЃR�[�h��List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(�،���ЃR�[�h); <BR>
     * <BR>
     * �R�j�@@�p�����[�^.���N�G�X�g�f�[�^.���ӏ����!=null�̏ꍇ�A <BR>
     * �@@�@@ ���ӏ���ʂ�List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(���ӏ����); <BR>
     * <BR>
     * �S�j�@@�p�����[�^.���N�G�X�g�f�[�^.���ӏ��敪�R�[�h!=null�̏ꍇ�A <BR>
     * �@@�@@ ���ӏ��敪�R�[�h��List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(���ӏ��敪�R�[�h); <BR>
     * <BR>
     * �T�j�@@�p�����[�^.���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�T�|�P�jget�s��.�s��ID��List�I�u�W�F�N�g�ɒǉ�����B<BR>
     * �@@���g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɂĎ擾�B <BR>
     * <BR>
     * [����] <BR>
     * �@@�@@�،���ЃR�[�h�@@�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�s��R�[�h�F�@@�p�����[�^.���N�G�X�g�f�[�^.�s��R�[�h<BR>
     * <BR>
     * �@@�T�|�Q�j<BR>
     * �@@List�I�u�W�F�N�g.add(�s��ID); <BR>
     * <BR>
     * �U�j�@@�p�����[�^.���N�G�X�g�f�[�^.�����R�[�h!= null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�U�|�P�j�g���A�J�E���g�}�l�[�W��.getInstitution()�ɂď،���Ђ��擾 <BR>
     * <BR>
     * [����] <BR>
     * �@@�@@�،���ЃR�[�h�@@�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * <BR>
     * �@@�U�|�Q�jget����.����ID��List�I�u�W�F�N�g�ɒǉ�����B<BR>
     * �@@���g�������v���_�N�g�}�l�[�W��.getProduct()�ɂĎ擾�B <BR>
     * <BR>
     * �@@��getProduct()�ŗ�O�������́APR�w�ɃG���[��Ԃ��B<BR>
     * �@@�@@�E�w������Ȃ��G���[<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag:�@@�@@BUSINESS_ERROR_00301<BR>
     * <BR>
     * [����] <BR>
     * �@@�@@�،���Ё@@�F getInstitution()�̖߂�l<BR>
     * �@@�@@�����R�[�h�F �p�����[�^.���N�G�X�g�f�[�^.�����R�[�h<BR>
     * <BR>
     * �@@�U�|�R�j<BR>
     * �@@List�I�u�W�F�N�g.add(����ID); <BR>
     * <BR>
     * �V�j �p�����[�^.���N�G�X�g�f�[�^.�L����!= null�̏ꍇ�A <BR>
     * �L������List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(�L����); <BR>
     * <BR>
     * <BR>
     * �W�j �p�����[�^.���N�G�X�g�f�[�^.��񔭐�����From != null�̏ꍇ�A <BR>
     * �@@ �@@��񔭐�����From��List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(��񔭐�����From); <BR>
     * <BR>
     * �X�j �p�����[�^.���N�G�X�g�f�[�^.��񔭐�����To != null�̏ꍇ�A <BR>
     * �@@ ��񔭐�����To��List�I�u�W�F�N�g�ɒǉ�����B <BR>
     * <BR>
     * �@@List�I�u�W�F�N�g.add(��񔭐�����To); <BR>
     * <BR>
     * �P�O�j �i�[�������X�g��z��I�u�W�F�N�g�ɕϊ�����B <BR>
     * <BR>
     * �P�P�j�@@�쐬�����z��I�u�W�F�N�g��ԋp����B <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 4945E76D0286
     */
    protected String[] createQueryDataContainer(
        WEB3AdminEqAttentionInfoRefRefRequest l_request, String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(WEB3AdminEqAttentionInfoRefRefRequest, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //�����l���i�[����List�I�u�W�F�N�g�𐶐�����
        List l_lisQueryDataContainers = new ArrayList();

        //�،���ЃR�[�h��List�I�u�W�F�N�g�ɒǉ�����
        l_lisQueryDataContainers.add(l_strInstitutionCode);

        //���ӏ����!=null�̏ꍇ�A���ӏ���ʂ�List�I�u�W�F�N�g�ɒǉ�����
        if (l_request.attentionInfoType != null)
        {
            l_lisQueryDataContainers.add(l_request.attentionInfoType);
        }

        //���ӏ��敪�R�[�h!=null�̏ꍇ�A���ӏ��敪�R�[�h��List�I�u�W�F�N�g�ɒǉ�����
        if (l_request.attentionInfoDivCode != null)
        {
            l_lisQueryDataContainers.add(l_request.attentionInfoDivCode);
        }

        //�s��R�[�h != null�̏ꍇ�A�ȉ��̏������s��
        if (l_request.marketCode != null)
        {
            Market l_market = null;
            try
            {
            //get�s��.�s��ID��List�I�u�W�F�N�g�ɒǉ�����
            //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɂĎ擾
            //List�I�u�W�F�N�g.add(�s��ID)
            WEB3GentradeFinObjectManager l_gentradeFinObjManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_gentradeFinObjManager.getMarket(
                l_strInstitutionCode,
                l_request.marketCode);
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
            l_lisQueryDataContainers.add(l_market.getMarketId() + "");
        }

        //�����R�[�h!= null�̏ꍇ�A�ȉ��̏������s��
        if (l_request.productCode != null)
        {
            Institution l_institution = null;
            try
            {
                //�g���A�J�E���g�}�l�[�W��.getInstitution()�ɂď،���Ђ��擾
                //get����.����ID��List�I�u�W�F�N�g�ɒǉ�����
                //�g�������v���_�N�g�}�l�[�W��.getProduct()�ɂĎ擾
                WEB3GentradeAccountManager l_gentradeAccountManaer =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                l_institution = l_gentradeAccountManaer.getInstitution(
                    l_strInstitutionCode);
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

            //�g�������v���_�N�g�}�l�[�W��.getProduct()�ɂĎ擾
            //List�I�u�W�F�N�g.add(����ID)
            try
            {
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_equityProductManeger =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                EqTypeProduct l_product = l_equityProductManeger.getProduct(l_institution, l_request.productCode);
                l_lisQueryDataContainers.add(l_product.getProductId() + "");
            }
            catch (NotFoundException l_ex)
            {
                log.error("�w�肵�������R�[�h�ɍ��v���Ă�����������݂��܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w�肵�������R�[�h�ɍ��v���Ă�����������݂��܂���B");
            }
        }

        //�L����!= null�̏ꍇ�A�L������List�I�u�W�F�N�g�ɒǉ�����
        if (l_request.validDate != null)
        {
            l_lisQueryDataContainers.add(l_request.validDate);
        }

        //��񔭐�����From != null�̏ꍇ�A��񔭐�����From��List�I�u�W�F�N�g�ɒǉ�����
        if (l_request.infoOccuredDateFrom != null)
        {
            l_lisQueryDataContainers.add(l_request.infoOccuredDateFrom);
        }

        //��񔭐�����To != null�̏ꍇ�A��񔭐�����To��List�I�u�W�F�N�g�ɒǉ�����
        if (l_request.infoOccuredDateTo != null)
        {
            l_lisQueryDataContainers.add(l_request.infoOccuredDateTo);
        }

        //�i�[�������X�g��z��I�u�W�F�N�g�ɕϊ�����
        String[] l_strQueryDatas = new String[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_strQueryDatas);

        //�쐬�����z��I�u�W�F�N�g��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_strQueryDatas;
    }

    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�������쐬����B <BR>
     * <BR>
     * �P�j�\�[�g����������(�FString)���쐬����B <BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �@@�Q-�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A <BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�E�u���ӏ���ʁv�@@���@@���ӏ�񗚗��e�[�u��.���ӏ���� <BR>
     * �@@�@@�E�u���ӏ��敪�R�[�h�v�@@���@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h<BR>
     * �@@�@@�E�u��񔭐������v�@@���@@���ӏ�񗚗��e�[�u��.��񔭐�����<BR>
     * �@@�@@�E�u�����R�[�h�v�@@���@@���ӏ�񗚗��e�[�u��.�����h�c<BR>
     * �@@�@@�E�u�s��R�[�h�v�@@���@@���ӏ�񗚗��e�[�u��.�s��h�c<BR>
     * <BR>
     * �@@�Q-�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾���� <BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �R�j�\�[�g���������ɁA���ӏ�񗚗��e�[�u��.��񔭐��������~���w��ŕt�� <BR>
     * �@@���\�[�g�����Ɂu��񔭐������v���܂܂�Ă��Ȃ��ꍇ<BR>
     * <BR>
     * �S�j�쐬�����\�[�g�����������ԋp����B <BR>
     * <BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * ���ӏ��Ɖ�\�[�g�L�[<BR>
     * @@return String
     * @@roseuid 4945E7DA0065
     */
    protected String createSortCond(WEB3AdminEqAttentionInfoRefSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3AdminEqAttentionInfoRefSortKey[])";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortCondReturn = new StringBuffer();
        int l_intSortKeyLength = l_sortKeys.length;
        boolean l_blnInfoGenerationTimestamp = false;

        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����
            if (WEB3AdminEquitySortKeyItemNameDef.ATTENTION_INFO_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("attention_info_type");
            }
            else if (WEB3AdminEquitySortKeyItemNameDef.ATTENTION_INFO_DIV_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("attention_info_div_code");
            }
            else if (WEB3AdminEquitySortKeyItemNameDef.INFO_OCCURED_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("info_generation_timestamp");
                l_blnInfoGenerationTimestamp = true;
            }
            else if (WEB3AdminEquitySortKeyItemNameDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("product_id");
            }
            else if (WEB3AdminEquitySortKeyItemNameDef.MARKET_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("market_id");
            }
            else
            {
                continue;
            }

            //�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)���\�[�g����������ɒǉ�����
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCondReturn.append(" ASC");
            }
            else
            {
                l_sbSortCondReturn.append(" DESC");
            }
            if (i < l_intSortKeyLength - 1)
            {
                l_sbSortCondReturn.append(", ");
            }
        }

        //�\�[�g���������ɁA���ӏ�񗚗��e�[�u��.��񔭐��������~���w��ŕt��
        if (!l_blnInfoGenerationTimestamp)
        {
            if (l_sbSortCondReturn.length() != 0)
            {
                l_sbSortCondReturn.append(", info_generation_timestamp DESC");
            }
            else
            {
                l_sbSortCondReturn.append("info_generation_timestamp DESC");
            }
        }

        log.exiting(STR_METHOD_NAME);

        //�쐬�����\�[�g�����������ԋp����
        return l_sbSortCondReturn.toString();
    }

    /**
     * (get���ӏ�񗚗��ꗗ)<BR>
     * �����̏����ɊY�����钍�ӏ�񗚗��̈ꗗ��ԋp����B <BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@arg0�F�@@���ӏ�񗚗��e�[�u��Row.TYPE <BR>
     * �@@�@@arg1�F�@@�p�����[�^.�������������� <BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g���� <BR>
     * �@@�@@arg3�F�@@null <BR>
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i <BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B <BR>
     * <BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_queryDataContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return AttentionInfoHistoryRow[]
     * @@throws WEB3BaseException
     * @@roseuid 4945E8A70123
     */
    protected AttentionInfoHistoryRow[] getAttentionInfoHistoryList(
        String l_strQueryString, String[] l_queryDataContainers, String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAttentionInfoHistoryList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        //QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //arg0�F�@@���ӏ�񗚗��e�[�u��Row.TYPE
        //arg1�F�@@�p�����[�^.��������������
        //arg2�F�@@�p�����[�^.�\�[�g����
        //arg3�F�@@null
        //arg4�F�@@�p�����[�^.���������f�[�^�R���e�i
        List l_lisResults = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResults = l_processor.doFindAllQuery(
                AttentionInfoHistoryRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryDataContainers);
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

        //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����
        if (l_lisResults.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�������ʂ�ԋp����
        int l_intAttentionInfoHistoryCnt = l_lisResults.size();
        AttentionInfoHistoryRow[] l_attentionInfoHistoryRows =
            new AttentionInfoHistoryRow[l_intAttentionInfoHistoryCnt];
        l_lisResults.toArray(l_attentionInfoHistoryRows);

        log.exiting(STR_METHOD_NAME);
        return l_attentionInfoHistoryRows;
    }

    /**
     * (create���ӏ��ꗗ)<BR>
     * �����̒��ӏ�񗚗��ꗗ��蒍�ӏ��Ɖ�ׂ��쐬����B <BR>
     * <BR>
     * �P�j�@@�߂�l���i�[����ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@�p�����[�^.���ӏ�񗚗��ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B <BR>
     * <BR>
     * �@@�R-�P�j�@@���ӏ��Ɖ�ׂ𐶐�����B <BR>
     * <BR>
     * �@@�R-�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@�@@���ӏ���ʁF�@@�����Ώۂ̗v�f.���ӏ���� <BR>
     * �@@�@@���ӏ��敪�R�[�h�F�@@�����Ώۂ̗v�f.���ӏ��敪�R�[�h <BR>
     * �@@�@@��񔭐������F�@@�����Ώۂ̗v�f.��񔭐����� <BR>
     * �@@�@@�s��R�[�h�F�@@(*1) <BR>
     * �@@�@@�����R�[�h�F�@@(*2) <BR>
     * �@@�@@�������F�@@�����Ώۂ̗v�f.������ <BR>
     * �@@�@@�������ʋ敪�F�@@�����Ώۂ̗v�f.�������ʋ敪 <BR>
     * �@@�@@�L�����F�@@�����Ώۂ̗v�f.�L���� <BR>
     * �@@�@@������t�ĊJ�����i�\��j�F�@@�����Ώۂ̗v�f.������t�ĊJ���� <BR>
     * �@@�@@������~�E�ĊJ�����F�@@�����Ώۂ̗v�f.������~�����^�����ĊJ���� <BR>
     * �@@�@@��l�i�ύX�O�j�F�@@�����Ώۂ̗v�f.��l�i�ύX�O�j<BR>
     * �@@�@@��l�i�ύX��j�F�@@�����Ώۂ̗v�f.��l�i�ύX��j<BR>
     * <BR>
     * �@@�@@�����l������i�ύX�O�j�F�@@(*3) <BR>
     * �@@�@@�����l������i�ύX��j�F�@@�����Ώۂ̗v�f.�����l������i�ύX��j<BR>
     * <BR>
     * �@@�@@�����l�������i�ύX�O�j�F�@@(*4) <BR>
     * �@@�@@�����l�������i�ύX��j�F�@@�����Ώۂ̗v�f.�����l�������i�ύX��j<BR>
     * <BR>
     * �@@�@@�]���P���i�ύX�O�j�F�����Ώۂ̗v�f.�]���P���i�ύX�O�j<BR>
     * �@@�@@�]���P���i�ύX��j�F�����Ώۂ̗v�f.�]���P���i�ύX��j<BR>
     * �@@�@@��l�i�I�l�j�i�ύX�O�j�F�����Ώۂ̗v�f.��l�i�I�l�j�i�ύX�O�j<BR>
     * �@@�@@��l�i�I�l�j�i�ύX��j�F�����Ώۂ̗v�f.��l�i�I�l�j�i�ύX��j<BR>
     * �@@�@@�l���`�F�b�N�敪�i�ύX�O�j�F�����Ώۂ̗v�f.�l���`�F�b�N�敪�i�ύX�O�j<BR>
     * �@@�@@�l���`�F�b�N�敪�i�ύX��j�F�����Ώۂ̗v�f.�l���`�F�b�N�敪�i�ύX��j<BR>
     * �@@�@@�l���敪�i�ύX�O�j�F�����Ώۂ̗v�f.�l���敪�i�ύX�O�j<BR>
     * �@@�@@�l���敪�i�ύX��j�F�����Ώۂ̗v�f.�l���敪�i�ύX��j<BR>
     * �@@�@@��l�iupdq�j�i�I�l�j�i�ύX�O�j�F�����Ώۂ̗v�f.��l�i�I�l�j�i�����j�i�ύX�O�j<BR>
     * �@@�@@��l�iupdq�j�i�I�l�j�i�ύX��j�F�����Ώۂ̗v�f.��l�i�I�l�j�i�����j�i�ύX��j<BR>
     * �@@�@@��l�iupdq�j�i�ύX�O�j�F�����Ώۂ̗v�f.��l�i�����j�i�ύX�O�j<BR>
     * �@@�@@��l�iupdq�j�i�ύX��j�F�����Ώۂ̗v�f.��l�i�����j�i�ύX��j<BR>
     * <BR>
     * �@@�@@�\��F�@@�����Ώۂ̗v�f.�\�� <BR>
     * �@@�@@�{���F�@@�����Ώۂ̗v�f.�{�� <BR>
     * <BR>
     * �@@�@@(*1) �����Ώۂ̗v�f.�s��ID��null�̏ꍇ�̂݁A<BR>
�@@�@@�@@�@@ �s��ID�ɊY������s��.�s��R�[�h���Z�b�g�B<BR>
     * <BR>
     * �@@�@@(*2)�����Ώۂ̗v�f.����ID��null�̏ꍇ�̂݁A<BR>
         ����ID�ɊY�����銔������.�����R�[�h���Z�b�g�B<BR>
     * <BR>
     * �@@�@@(*3)�u�����Ώۂ̗v�f.��l�i�ύX�O�j�v = null ���邢�́A <BR>
     * �u�����Ώۂ̗v�f.�����l���i����l�j�i�ύX�O�j�v = null�̏ꍇ�Anull���Z�b�g <BR>
     * �ȊO�̏ꍇ�A�����Ώۂ̗v�f.��l�i�ύX�O�j+�����Ώۂ̗v�f.�����l���i����l�j�i�ύX�O�j<BR>
     * <BR>
     * �@@�@@(*4)�u�����Ώۂ̗v�f.��l�i�ύX�O�j�v = null ���邢�́A <BR>
     * �u�����Ώۂ̗v�f.�����l���i�����l�j�i�ύX�O�j�v = null�̏ꍇ�Anull���Z�b�g <BR>
     * �ȊO�̏ꍇ�A�����Ώۂ̗v�f.��l�i�ύX�O�j-�����Ώۂ̗v�f.�����l���i�����l�j�i�ύX�O�j <BR>
     * ���������A�v�Z���ʂ�0�ȉ��̏ꍇ��null���Z�b�g <BR>
     * <BR>
     * �@@�R-�R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �S�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_attentionInfoHistoryRows - (���ӏ�񗚗��ꗗ)<BR>
     * ���ӏ�񗚗��ꗗ<BR>
     * @@return WEB3AdminEqAttentionInfoRefDetail[]
     * @@throws WEB3BaseException
     * @@roseuid 4945E8D50181
     */
    protected  WEB3AdminEqAttentionInfoRefDetail[] createAttentionInfoList(
        AttentionInfoHistoryRow[] l_attentionInfoHistoryRows) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAttentionInfoList(AttentionInfoHistoryRow[])";
        log.entering(STR_METHOD_NAME);

        //�߂�l���i�[����ArrayList�𐶐�����
        List l_lisAttentionInfoList = new ArrayList();

        //���ӏ�񗚗��ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ�
        int l_intAttentionInfoHistoryCnt = l_attentionInfoHistoryRows.length;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_gentradeFinObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_equityProductManeger =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        for (int i = 0; i < l_intAttentionInfoHistoryCnt; i++)
        {
            //���ӏ��Ɖ�ׂ𐶐�����
            WEB3AdminEqAttentionInfoRefDetail l_attentionInfoReferenceUnitList =
                new WEB3AdminEqAttentionInfoRefDetail();

            //���ӏ���ʁF�@@�����Ώۂ̗v�f.���ӏ����
            l_attentionInfoReferenceUnitList.attentionInfoType = l_attentionInfoHistoryRows[i].getAttentionInfoType();

            //���ӏ��敪�R�[�h�F�@@�����Ώۂ̗v�f.���ӏ��敪�R�[�h
            l_attentionInfoReferenceUnitList.attentionInfoDivCode =
                l_attentionInfoHistoryRows[i].getAttentionInfoDivCode();

            //��񔭐������F�@@�����Ώۂ̗v�f.��񔭐�����
            l_attentionInfoReferenceUnitList.infoOccuredDate =
                l_attentionInfoHistoryRows[i].getInfoGenerationTimestamp();

            try
            {
                //�����Ώۂ̗v�f.�s��ID��null�̏ꍇ�̂݁A�s��ID�ɊY������s��.�s��R�[�h���Z�b�g�B
                if (!l_attentionInfoHistoryRows[i].getMarketIdIsNull())
                {
                    Market l_market = l_gentradeFinObjManager.getMarket(
                        l_attentionInfoHistoryRows[i].getMarketId());
                    l_attentionInfoReferenceUnitList.marketCode = l_market.getMarketCode();
                }

                //�����Ώۂ̗v�f.����ID��null�̏ꍇ�̂݁A����ID�ɊY�����銔������.�����R�[�h���Z�b�g
                if (!l_attentionInfoHistoryRows[i].getProductIdIsNull())
                {
                    WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_equityProductManeger.getProduct(
                        l_attentionInfoHistoryRows[i].getProductId());
                    l_attentionInfoReferenceUnitList.productCode = l_equityProduct.getProductCode();
                }
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

            //�������F�@@�����Ώۂ̗v�f.������
            l_attentionInfoReferenceUnitList.productName = l_attentionInfoHistoryRows[i].getStandardName();

            //�������ʋ敪�F�@@�����Ώۂ̗v�f.�������ʋ敪
            l_attentionInfoReferenceUnitList.attentionInfoProcResDiv =
                l_attentionInfoHistoryRows[i].getProcessResultDiv();

            //�L�����F�@@�����Ώۂ̗v�f.�L����
            l_attentionInfoReferenceUnitList.validDate =
                WEB3DateUtility.getDate(l_attentionInfoHistoryRows[i].getValidUntilBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            //������t�ĊJ�����i�\��j�F�@@�����Ώۂ̗v�f.������t�ĊJ����
            l_attentionInfoReferenceUnitList.orderAcceptResumeScheduledDate =
                l_attentionInfoHistoryRows[i].getOrdReceiptRestartTimestamp();

            //������~�E�ĊJ�����F�@@�����Ώۂ̗v�f.������~�����^�����ĊJ����
            l_attentionInfoReferenceUnitList.buySellSuspendResumeDate =
                l_attentionInfoHistoryRows[i].getTradeStopRestartTimestamp();

            //��l�i�ύX�O�j�F�@@�����Ώۂ̗v�f.��l�i�ύX�O�j
            if (!l_attentionInfoHistoryRows[i].getOldBasePriceIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgBasePrice =
                    WEB3StringTypeUtility.formatNumber(l_attentionInfoHistoryRows[i].getOldBasePrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgBasePrice = null;
            }

            //��l�i�ύX��j�F�@@�����Ώۂ̗v�f.��l�i�ύX��j
            if (!l_attentionInfoHistoryRows[i].getNewBasePriceIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgBasePrice =
                    WEB3StringTypeUtility.formatNumber(l_attentionInfoHistoryRows[i].getNewBasePrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgBasePrice = null;
            }

            //�����Ώۂ̗v�f.��l�i�ύX�O�j�v = null ���邢�́A
            //�����Ώۂ̗v�f.�����l���i����l�j�i�ύX�O�j�v = null�̏ꍇ�Anull���Z�b�g
            //�ȊO�̏ꍇ�A�����Ώۂ̗v�f.��l�i�ύX�O�j+�����Ώۂ̗v�f.�����l���i����l�j�i�ύX�O�j
            BigDecimal l_bdOldBasePrice =
                new BigDecimal(String.valueOf(l_attentionInfoHistoryRows[i].getOldBasePrice()));
            BigDecimal l_bdOldHighCompPriceRange = new BigDecimal(
                String.valueOf(l_attentionInfoHistoryRows[i].getOldHighCompPriceRange()));
            if (l_attentionInfoHistoryRows[i].getOldBasePriceIsNull()
                || l_attentionInfoHistoryRows[i].getOldHighCompPriceRangeIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgUpperPriceRange = null;
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgUpperPriceRange =
                    WEB3StringTypeUtility.formatNumber(l_bdOldBasePrice.add(l_bdOldHighCompPriceRange).doubleValue());
            }

            //�����l������i�ύX��j�F�@@�����Ώۂ̗v�f.�����l������i�ύX��j
            if (!l_attentionInfoHistoryRows[i].getNewHighPriceRangeIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgUpperPriceRange =
                    WEB3StringTypeUtility.formatNumber(l_attentionInfoHistoryRows[i].getNewHighPriceRange());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgUpperPriceRange = null;
            }

            //�u�����Ώۂ̗v�f.��l�i�ύX�O�j�v = null ���邢�́A
            //�����Ώۂ̗v�f.�����l���i�����l�j�i�ύX�O�j�v = null�̏ꍇ�Anull���Z�b�g
            //�ȊO�̏ꍇ�A�����Ώۂ̗v�f.��l�i�ύX�O�j-�����Ώۂ̗v�f.�����l���i�����l�j�i�ύX�O�j
            //���������A�v�Z���ʂ�0�ȉ��̏ꍇ��null���Z�b�g
            BigDecimal l_bdOldLowCompPriceRange = new BigDecimal(
                String.valueOf(l_attentionInfoHistoryRows[i].getOldLowCompPriceRange()));
            if (l_attentionInfoHistoryRows[i].getOldBasePriceIsNull()
                || l_attentionInfoHistoryRows[i].getOldLowCompPriceRangeIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgLowerPriceRange = null;
            }
            else
            {
                BigDecimal l_bdbefChgLowerPriceRange = l_bdOldBasePrice.subtract(l_bdOldLowCompPriceRange);
                if ((l_bdbefChgLowerPriceRange.doubleValue() < 0)
                    || (GtlUtils.Double.isZero(l_bdbefChgLowerPriceRange.doubleValue())))
                {
                    l_attentionInfoReferenceUnitList.befChgLowerPriceRange = null;
                }
                else
                {
                    l_attentionInfoReferenceUnitList.befChgLowerPriceRange =
                        WEB3StringTypeUtility.formatNumber(
                        l_bdbefChgLowerPriceRange.doubleValue());
                }
            }

            //�����l�������i�ύX��j�F�@@�����Ώۂ̗v�f.�����l�������i�ύX��j�j
            if (!l_attentionInfoHistoryRows[i].getNewLowPriceRangeIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgLowerPriceRange =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewLowPriceRange());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgLowerPriceRange = null;
            }

            //�]���P���i�ύX�O�j�F�����Ώۂ̗v�f.�]���P���i�ύX�O�j
            if (!l_attentionInfoHistoryRows[i].getOldEstimationPriceIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgEvaluationPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getOldEstimationPrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgEvaluationPrice = null;
            }

            //�]���P���i�ύX��j�F�����Ώۂ̗v�f.�]���P���i�ύX��j
            if (!l_attentionInfoHistoryRows[i].getNewEstimationPriceIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgEvaluationPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewEstimationPrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgEvaluationPrice = null;
            }

            //��l�i�I�l�j�i�ύX�O�j�F�����Ώۂ̗v�f.��l�i�I�l�j�i�ύX�O�j
            if (!l_attentionInfoHistoryRows[i].getOldLastClosingPriceIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgLastClosingPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getOldLastClosingPrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgLastClosingPrice = null;
            }

            //��l�i�I�l�j�i�ύX��j�F�����Ώۂ̗v�f.��l�i�I�l�j�i�ύX��j
            if (!l_attentionInfoHistoryRows[i].getNewLastClosingPriceIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgLastClosingPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewLastClosingPrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgLastClosingPrice = null;
            }

            //�l���`�F�b�N�敪�i�ύX�O�j�F�����Ώۂ̗v�f.�l���`�F�b�N�敪�i�ύX�O�j
            l_attentionInfoReferenceUnitList.befChgPriceRangeCheckDiv =
                l_attentionInfoHistoryRows[i].getOldPriceRangeType();

            //�l���`�F�b�N�敪�i�ύX��j�F�����Ώۂ̗v�f.�l���`�F�b�N�敪�i�ύX��j
            l_attentionInfoReferenceUnitList.aftChgPriceRangeCheckDiv =
                l_attentionInfoHistoryRows[i].getNewPriceRangeType();

            //�l���敪�i�ύX�O�j�F�����Ώۂ̗v�f.�l���敪�i�ύX�O�j
            l_attentionInfoReferenceUnitList.befChgPriceRangeDiv =
                l_attentionInfoHistoryRows[i].getOldPriceRangeUnitType();

            //�l���敪�i�ύX��j�F�����Ώۂ̗v�f.�l���敪�i�ύX��j
            l_attentionInfoReferenceUnitList.aftChgPriceRangeDiv =
                l_attentionInfoHistoryRows[i].getNewPriceRangeUnitType();

            //��l�iupdq�j�i�I�l�j�i�ύX�O�j�F�����Ώۂ̗v�f.��l�i�I�l�j�i�����j�i�ύX�O�j
            if (!l_attentionInfoHistoryRows[i].getOldLastClosingPriceUpdqIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgLastClosingPriceUpdq =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getOldLastClosingPriceUpdq());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgLastClosingPriceUpdq = null;
            }

            //��l�iupdq�j�i�I�l�j�i�ύX��j�F�����Ώۂ̗v�f.��l�i�I�l�j�i�����j�i�ύX��j
            if (!l_attentionInfoHistoryRows[i].getNewLastClosingPriceUpdqIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgLastClosingPriceUpdq =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewLastClosingPriceUpdq());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgLastClosingPriceUpdq = null;
            }

            //��l�iupdq�j�i�ύX�O�j�F�����Ώۂ̗v�f.��l�i�����j�i�ύX�O�j
            if (!l_attentionInfoHistoryRows[i].getOldBasePriceUpdqIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgBasePriceUpdq =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getOldBasePriceUpdq());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgBasePriceUpdq = null;
            }

            //��l�iupdq�j�i�ύX��j�F�����Ώۂ̗v�f.��l�i�����j�i�ύX��j
            if (!l_attentionInfoHistoryRows[i].getNewBasePriceUpdqIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgBasePriceUpdq =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewBasePriceUpdq());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgBasePriceUpdq = null;
            }

            //�\��F�@@�����Ώۂ̗v�f.�\��
            l_attentionInfoReferenceUnitList.title =
                l_attentionInfoHistoryRows[i].getFreeFormatTitle();

            //�{���F�@@�����Ώۂ̗v�f.�{��
            l_attentionInfoReferenceUnitList.text =
                l_attentionInfoHistoryRows[i].getFreeFormatText();

            //�v���p�e�B�Z�b�g�����C���X�^���X��ArrayList�ɒǉ�����
            l_lisAttentionInfoList.add(l_attentionInfoReferenceUnitList);
        }

        //��������ArrayList.toArray()�̖߂�l��ԋp����
        int l_intAttentionInfoListCnt = l_lisAttentionInfoList.size();
        WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
            new WEB3AdminEqAttentionInfoRefDetail[l_intAttentionInfoListCnt];
        l_lisAttentionInfoList.toArray(l_eqAttentionInfoRefDetail);

        log.exiting(STR_METHOD_NAME);
        return l_eqAttentionInfoRefDetail;
    }
}
@
