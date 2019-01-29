head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��ʒm�T�[�r�XImpl(WEB3AdminEquityAttentionInfoNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 ���� (���u) �V�K�쐬 ���f��No.219 No.226 No.227 �c�a�X�V�d�lNo.022,No.023
Revision History : 2008/01/14 ������ (���u) �d�l�ύX �c�a�X�V�d�lNo.024
Revision History : 2008/01/14 ���� (���u) �d�l�ύX ���f��No.229
Revision History : 2009/01/21 �И���(���u) ���f��No.230 No.232
Revision History : 2009/01/23 ����(���u) ���f�� No.233
Revision History : 2009/02/11 ����(���u) ���f�� No.236 �c�a�X�V�d�lNo.025,026,027
Revision History : 2009/02/17 ����(���u) ���f�� No.237 �c�a�X�V�d�lNo.028
Revision History : 2009/02/18 ����(���u) ���f�� No.238 No.239
Revision History : 2009/02/20 �И���(���u) ���f�� No.240
Revision History : 2009/02/27 ����(���u) ���f�� No.241 �c�a�X�V�d�lNo.029
Revision History : 2009/03/02 ����(���u) ���f�� No.242
Revision History : 2009/05/05 ���ʗ�(���u) ���f�� No.243 �c�a�X�V�d�lNo.032
Revision History : 2009/05/14 SRA���J�� �c�a�X�V�d�lNo.033
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountCodeDef;
import webbroker3.common.define.WEB3AdminEquityStatusDef;
import webbroker3.common.define.WEB3AttentionInfoDivCodeDef;
import webbroker3.common.define.WEB3AttentionInfoTypeDef;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3PriceRangeIdDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.common.define.WEB3ProcessResultDivDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryDao;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryParams;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityDiscernmentIdDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityHashMapKeyDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityProductUpdateFlagDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyUnitService;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.data.ExtMailProcParams;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ӏ��ʒm�T�[�r�XImpl)<BR>
 * ���ӏ��ʒm�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoNotifyServiceImpl implements WEB3AdminEquityAttentionInfoNotifyService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyServiceImpl.class);

    /**
     * @@roseuid 49588AEE0270
     */
    public WEB3AdminEquityAttentionInfoNotifyServiceImpl()
    {

    }

    /**
     * ���ӏ��ʒm�T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3BackResponse
     * @@roseuid 49377CBE0128
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
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

        if (!(l_request instanceof WEB3AdminEquityAttentionInfoNotifyRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3AdminEquityAttentionInfoNotifyRequest l_attentionInfoNotifyRequest =
            (WEB3AdminEquityAttentionInfoNotifyRequest)l_request;
        WEB3BackResponse l_response = null;

        try
        {
            //getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //���ӏ��ʒmTransactionCallback()
            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_transactionCallback =
                new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();

            //doTransaction(�g�����U�N�V�������� : int, TransactionCallback : TransactionCallback)
            //�����̐ݒ�d�l�͈ȉ��̒ʂ�
            //�g�����U�N�V�������� : TX_CREATE_NEW
            //TransactionCallback : �����������ӏ��ʒmTransactionCallback�I�u�W�F�N�g
            l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallback);

            //createResponse()
            l_response = l_attentionInfoNotifyRequest.createResponse();
        }
        catch (DataCallbackException l_ex)
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

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (���ӏ��ʒmTransactionCallback)<BR>
     * ���ӏ��ʒmTransactionCallback <BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X<BR>
     */
    public class WEB3AdminEquityAttentionInfoNotifyTransactionCallback implements TransactionCallback
    {

        /**
         * (���ӏ��ʒmTransactionCallback)<BR>
         * �R���X�g���N�^<BR>
         * @@roseuid 49377F7B0107
         */
        public WEB3AdminEquityAttentionInfoNotifyTransactionCallback()
        {

        }

        /**
         * ���ӏ��ʒm���������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i���ӏ��T�[�r�X�jprocess�v�Q��<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 493784990359
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //�i���ӏ��ʒm�L���[�e�[�u�����擾����j
            String l_strWhere = " request_code in ( ? , ? , ? ) and status = ? ";
            Object[] l_values = {
                WEB3HostRequestCodeDef.SELL_STOP_INFO,
                WEB3HostRequestCodeDef.LIMIT_RANGE_INFO,
                WEB3HostRequestCodeDef.FREE_FORMAT,
                WEB3AdminEquityStatusDef.NOT_DEAL};
            String l_strOrderBy = " info_generation_timestamp ";

            List l_lisHostAttentionInfoNotifyRows = null;
            l_lisHostAttentionInfoNotifyRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    HostAttentionInfoNotifyRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_values);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            WEB3EquityTradingModule l_tradingModule =
                (WEB3EquityTradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            //�i�擾�����L���[�e�[�u���̃��R�[�h����LOOP�j
            int l_intListSize = l_lisHostAttentionInfoNotifyRows.size();
            for (int i = 0; i < l_intListSize; i++)
            {
                HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                    (HostAttentionInfoNotifyParams)l_lisHostAttentionInfoNotifyRows.get(i);

                try
                {
                    //�����e�[�u��
                    ProductRow l_productRow = null;
                    //������������}�X�^�e�[�u��
                    EqtypeTradedProductRow l_eqtypeTradedProductRow = null;
                    //������������}�X�^updq�e�[�u��
                    EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow = null;
                    //���������e�[�u��
                    EqtypeProductRow l_eqtypeProductRow = null;
                    //�s��
                    Market l_market = null;
                    //���������I�u�W�F�N�g
                    WEB3EquityProduct l_equityProduct = null;
                    //���ӏ��ʒm�L���[�e�[�u��.�����R�[�h
                    String l_strProductCode = l_hostAttentionInfoNotifyParams.getProductCode();
                    //���ӏ��ʒm�L���[�e�[�u��.�f�[�^�R�[�h
                    String l_strRequestCode = l_hostAttentionInfoNotifyParams.getRequestCode();
                    //notify�����l�����()�̖߂�l
                    String l_strReturn = null;
                    //notify�����l�����()���R�[���t���O
                    boolean l_blnFlag = false;

                    try
                    {
                        //�،����(�،���ЃR�[�h : String)
                        //�����̐ݒ�d�l�͈ȉ��̒ʂ�
                        //�،���ЃR�[�h : �������ʒm�L���[�e�[�u��.�،���ЃR�[�h
                        String l_strInstitutionCode = l_hostAttentionInfoNotifyParams.getInstitutionCode();
                        Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);

                        //get�s��BySONAR(�،���ЃR�[�h : String, �s��R�[�h(SONAR) : String)
                        //�����̐ݒ�d�l�͈ȉ��̒ʂ�
                        //�،���ЃR�[�h : �������ʒm�L���[�e�[�u��.�،���ЃR�[�h
                        //�s��R�[�h�iSONAR�j : ���ӏ��ʒm�L���[�e�[�u��.�s��R�[�h�iSONAR�j
                        String l_strSonarMarketCode = l_hostAttentionInfoNotifyParams.getSonarMarketCode();
                        l_market = l_finObjectManager.getMarketBySONAR(l_strInstitutionCode, l_strSonarMarketCode);

                        //�i����t���[�F���ӏ��ʒm�L���[�e�[�u��.�����R�[�h��null�̏ꍇ�j
                        if (l_strProductCode != null)
                        {
                            //���������I�u�W�F�N�g���擾����B
                            //�����̐ݒ�d�l�͈ȉ��̒ʂ�
                            //�@@�،���� : �،���ЃI�u�W�F�N�g
                            //�@@�����R�[�h :
                            //�@@�@@���ӏ��ʒm�L���[�e�[�u��.�����R�[�h�̒l��4���̏ꍇ�A���ӏ��ʒm�L���[�e�[�u��.�����R�[�h�{"0"
                            //�@@�@@��L�ȊO�̏ꍇ�A���ӏ��ʒm�L���[�e�[�u��.�����R�[�h
                            if (l_strProductCode.length() == 4)
                            {
                                l_strProductCode = l_strProductCode + "0";
                            }

                            l_equityProduct =
                                (WEB3EquityProduct)l_equityProductManager.getProduct(l_institution, l_strProductCode);

                            l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();

                            //����t���[�F(*1)
                            Timestamp l_tsInfoGenerationTimestamp =
                                l_hostAttentionInfoNotifyParams.getInfoGenerationTimestamp();
                            Date l_datBizDate = l_finApp.getTradingSystem().getBizDate();
                            if (WEB3DateUtility.compareToDay(l_tsInfoGenerationTimestamp, l_datBizDate) == 0)
                            {
                                //�i����t���[�F���ӏ��ʒm�L���[�e�[�u��.�f�[�^�R�[�h��"���ӏ��i�����l�����j�ʒm"�̏ꍇ�j
                                List l_lisProductRows = null;
                                List l_lisEqtypeTradedProductRows = null;
                                List l_lisEqtypeTradedProductUpdqRows = null;
                                if (WEB3HostRequestCodeDef.LIMIT_RANGE_INFO.equals(l_strRequestCode))
                                {
                                    //�����e�[�u�����擾����
                                    String l_strQuery0 = " product_id = ?";
                                    Object[] l_dataContainers0 = {new Long(l_equityProduct.getProductId())};

                                    l_lisProductRows = Processors.getDefaultProcessor().doFindAllQuery(
                                        ProductRow.TYPE,
                                        l_strQuery0,
                                        l_dataContainers0);

                                    if (l_lisProductRows.isEmpty())
                                    {
                                        log.debug(
                                            "�����h�c = " + l_equityProduct.getProductId() + "�ɊY����������e�[�u�����擾�ł��܂���");
                                        log.exiting(STR_METHOD_NAME);
                                        throw new NotFoundException(
                                            "�����h�c = " + l_equityProduct.getProductId() + "�ɊY����������e�[�u�����擾�ł��܂���");
                                    }

                                    l_productRow = (ProductRow)l_lisProductRows.get(0);

                                    //������������}�X�^�e�[�u�����擾����
                                    String l_strBizDate = WEB3DateUtility.formatDate(
                                        l_datBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                                    String l_strQuery1 =
                                        " product_id = ? and market_id = ? and valid_until_biz_date = ?";
                                    Object[] l_dataContainers1 = {
                                        new Long(l_equityProduct.getProductId()),
                                        new Long(l_market.getMarketId()),
                                        l_strBizDate};

                                    l_lisEqtypeTradedProductRows =
                                        Processors.getDefaultProcessor().doFindAllQuery(
                                            EqtypeTradedProductRow.TYPE,
                                            l_strQuery1,
                                            l_dataContainers1);

                                    if (l_lisEqtypeTradedProductRows.isEmpty())
                                    {
                                        log.debug(
                                            "�����h�c = " + l_equityProduct.getProductId()
                                            + "�s��h�c = " + l_market.getMarketId()
                                            + "�L���� = " + l_strBizDate + "�ɊY�����銔����������}�X�^�e�[�u�����擾�ł��܂���");
                                        log.exiting(STR_METHOD_NAME);
                                        throw new NotFoundException(
                                            "�����h�c = " + l_equityProduct.getProductId()
                                            + "�s��h�c = " + l_market.getMarketId()
                                            + "�L���� = " + l_strBizDate + "�ɊY�����銔����������}�X�^�e�[�u�����擾�ł��܂���");
                                    }

                                    l_eqtypeTradedProductRow =
                                        (EqtypeTradedProductRow)l_lisEqtypeTradedProductRows.get(0);

                                    //������������}�X�^updq�e�[�u�����擾����
                                    WEB3GentradeBizDate l_bizDate =
                                        new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
                                    String l_strBizDateNext = WEB3DateUtility.formatDate(
                                        l_bizDate.roll(1), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                                    String l_strQuery2 =
                                        " product_id = ? and market_id = ? and valid_until_biz_date = ?";
                                    Object[] l_dataContainers2 = {
                                        new Long(l_equityProduct.getProductId()),
                                        new Long(l_market.getMarketId()),
                                        l_strBizDateNext};

                                    l_lisEqtypeTradedProductUpdqRows =
                                        Processors.getDefaultProcessor().doFindAllQuery(
                                            EqtypeTradedProductUpdqRow.TYPE,
                                            l_strQuery2,
                                            l_dataContainers2);

                                    if (!l_lisEqtypeTradedProductUpdqRows.isEmpty())
                                    {
                                        l_eqtypeTradedProductUpdqRow =
                                            (EqtypeTradedProductUpdqRow)l_lisEqtypeTradedProductUpdqRows.get(0);
                                    }

                                    //notify�����l�����(���ӏ��ʒm�L���[Params, �����������Row, �����������updqRow, ����Row)
                                    //�����̐ݒ�d�l�͈ȉ��̒ʂ�
                                    //���ӏ��ʒm�L���[�e�[�u�� : ���ӏ��ʒm�L���[�e�[�u���I�u�W�F�N�g
                                    //����������� : ������������I�u�W�F�N�g
                                    //�����������updq : �����������updq�I�u�W�F�N�g
                                    //���� : �����I�u�W�F�N�g
                                    WEB3AdminEquityAttentionInfoNotifyUnitService l_service =
                                        (WEB3AdminEquityAttentionInfoNotifyUnitService)Services.getService(
                                            WEB3AdminEquityAttentionInfoNotifyUnitService.class);

                                    l_strReturn = l_service.notifyLimitRangeInfo(
                                        l_hostAttentionInfoNotifyParams,
                                        l_eqtypeTradedProductRow,
                                        l_eqtypeTradedProductUpdqRow,
                                        l_productRow);

                                    l_blnFlag = true;
                                }
                            }
                        }

                        if (l_strProductCode == null
                            && WEB3HostRequestCodeDef.LIMIT_RANGE_INFO.equals(l_strRequestCode))
                        {
                            //�����敪
                            l_hostAttentionInfoNotifyParams.setStatus(WEB3AdminEquityStatusDef.ERROR);
                            //�X�V���t
                            l_hostAttentionInfoNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        }
                        else
                        {
                            //�����敪
                            l_hostAttentionInfoNotifyParams.setStatus(WEB3AdminEquityStatusDef.DEALT);
                            //�X�V���t
                            l_hostAttentionInfoNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        }
                    }
                    catch (Exception l_ex)
                    {
                        //�����敪
                        l_hostAttentionInfoNotifyParams.setStatus(WEB3AdminEquityStatusDef.ERROR);
                        //�X�V���t
                        l_hostAttentionInfoNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    }

                    //�L���[�e�[�u���̏����敪�A�X�V���t���X�V����
                    Processors.getDefaultProcessor().doUpdateQuery(l_hostAttentionInfoNotifyParams);

                    //insert���ӏ�񗚗�
                    //(���ӏ��ʒm�L���[Params, �s��, ����Row, �����������Row, �����������updqRow, String)
                    //�����̐ݒ�d�l�͈ȉ��̒ʂ�
                    //���ӏ��ʒm�L���[�e�[�u�� : ���ӏ��ʒm�L���[Params
                    //�s�� : �s��I�u�W�F�N�g
                    //�X�V�O�̖��� :
                    //  ���ӏ��ʒm�L���[�e�[�u��.�����R�[�h��null�̏ꍇ�A�����I�u�W�F�N�g�i���j
                    //  �ȊO�Anull
                    //�X�V�O�̊���������� :
                    //  ���ӏ��ʒm�L���[�e�[�u��.�����R�[�h��null�̏ꍇ�A������������I�u�W�F�N�g�i���j
                    //  �ȊO�Anull
                    //�X�V�O�̊����������updq :
                    //  ���ӏ��ʒm�L���[�e�[�u��.�����R�[�h��null�̏ꍇ�A�����������updq�I�u�W�F�N�g�i���j
                    //  �ȊO�Anull
                    //�������� :
                    //  ���ӏ��ʒm�L���[�e�[�u��.�����R�[�h��null�̏ꍇ�A���������I�u�W�F�N�g
                    //  �ȊO�Anull
                    if (l_strProductCode == null)
                    {
                        l_productRow = null;
                        l_eqtypeTradedProductRow = null;
                        l_eqtypeTradedProductUpdqRow = null;
                        l_eqtypeProductRow = null;
                    }

                    //�����X�V�t���O :
                    //  notify�����l�����()���R�[�����Ă���ꍇ�́Anotify�����l�����()�̖߂�l
                    //  �ȊO�A"2�F�������X�V"
                    String l_strProductUpdateFlag = null;
                    if (l_blnFlag)
                    {
                        l_strProductUpdateFlag = l_strReturn;
                    }
                    else
                    {
                        l_strProductUpdateFlag = WEB3AdminEquityProductUpdateFlagDef.PRODUCT_NOT_UPDATE;
                    }

                    AttentionInfoHistoryParams l_attentionInfoHistoryParams =
                        this.insertAttentionInfoHistory(
                            l_hostAttentionInfoNotifyParams,
                            l_market,
                            l_productRow,
                            l_eqtypeTradedProductRow,
                            l_eqtypeTradedProductUpdqRow,
                            l_eqtypeProductRow,
                            l_strProductUpdateFlag);

                    //insert���[�����M�e�[�u��(��������, �s��, ���ӏ�񗚗�)
                    //�����̐ݒ�d�l�͈ȉ��̒ʂ�
                    //�������� : ���ӏ��ʒm�L���[�e�[�u��.�����R�[�h��null�̏ꍇ�A���������I�u�W�F�N�g
                    //�ȊO�Anull
                    //�s�� : �s��I�u�W�F�N�g
                    //���ӏ�񗚗� : ���ӏ�񗚗��I�u�W�F�N�g
                    this.insertSendMail(
                        l_equityProduct,
                        l_market,
                        l_attentionInfoHistoryParams);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new DataCallbackException(
                        l_ex.getMessage(),
                        l_ex);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (insert���ӏ�񗚗�)<BR>
         * ���ӏ�񗚗��e�[�u����1��Insert���s���A<BR>
         * Insert���ꂽ���ӏ�񗚗��I�u�W�F�N�g��ԋp����B<BR>
         * <BR>
         * �X�V���e�́w���ӏ��ʒm_���ӏ�񗚗��e�[�u��.xls�x���Q�ƁB<BR>
         * @@param l_hostAttentionInfoNotifyParams - (���ӏ��ʒm�L���[�e�[�u��)<BR>
         * ���ӏ��ʒm�L���[�e�[�u��<BR>
         * @@param l_market - (�s��)<BR>
         * �s��I�u�W�F�N�g<BR>
         * @@param l_productRow - (�X�V�O�̖���)<BR>
         * �X�V�O�̖����I�u�W�F�N�g<BR>
         * @@param l_eqtypeTradedProductRow - (�X�V�O�̊����������)<BR>
         * �X�V�O�̊�����������I�u�W�F�N�g<BR>
         * @@param l_eqtypeTradedProductUpdqRow - (�X�V�O�̊����������updq)<BR>
         * �X�V�O�̊����������updq�I�u�W�F�N�g<BR>
         * @@param l_eqtypeProductRow - (��������)<BR>
         * ���������I�u�W�F�N�g<BR>
         * @@param l_strProductUpdateFlag - (�����X�V�t���O)<BR>
         * �����X�V�t���O<BR>
         * <BR>
         * �E1�F�����X�V�ς�<BR>
         * �E2�F�������X�V<BR>
         * @@return AttentionInfoHistoryParams
         * @@throws WEB3BaseException
         * @@roseuid 493CADF80307
         */
        private AttentionInfoHistoryParams insertAttentionInfoHistory(
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams,
            Market l_market,
            ProductRow l_productRow,
            EqtypeTradedProductRow l_eqtypeTradedProductRow,
            EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow,
            EqtypeProductRow l_eqtypeProductRow,
            String l_strProductUpdateFlag) throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                "insertAttentionInfoHistory(HostAttentionInfoNotifyParams, Market, ProductRow,"
                + " EqtypeTradedProductRow, EqtypeTradedProductUpdqRow, EqtypeProductRow, String)";
            log.entering(STR_METHOD_NAME);

            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();

            try
            {
                //���ӏ�񗚗�ID
                l_attentionInfoHistoryParams.setAttentionInfoHistoryId(AttentionInfoHistoryDao.newPkValue());
                //���ӏ����
                if (WEB3HostRequestCodeDef.SELL_STOP_INFO.equals(l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //���ӏ��ʒm�L���[.�f�[�^�R�[�h��"���ӏ��i������j�ʒm"�̏ꍇ
                    //"1�F������"
                    l_attentionInfoHistoryParams.setAttentionInfoType(WEB3AttentionInfoTypeDef.SELL_STOP_INFO);
                }
                else if (WEB3HostRequestCodeDef.LIMIT_RANGE_INFO.equals(
                    l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //���ӏ��ʒm�L���[.�f�[�^�R�[�h��"���ӏ��i�����l�����j�ʒm"�̏ꍇ
                    //"2�F�����l�����"
                    l_attentionInfoHistoryParams.setAttentionInfoType(WEB3AttentionInfoTypeDef.LIMIT_RANGE_INFO);
                }
                else if (WEB3HostRequestCodeDef.FREE_FORMAT.equals(
                    l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //���ӏ��ʒm�L���[.�f�[�^�R�[�h��"���ӏ��i�t���[�t�H�[�}�b�g�j�ʒm"�̏ꍇ
                    //"3�F�t���[�t�H�[�}�b�g"
                    l_attentionInfoHistoryParams.setAttentionInfoType(WEB3AttentionInfoTypeDef.FREE_FORMAT);
                }
                //�،���ЃR�[�h
                l_attentionInfoHistoryParams.setInstitutionCode(l_hostAttentionInfoNotifyParams.getInstitutionCode());

                //�����h�c
                //��������.�����h�c�i���R�j
                //������
                //��������.�������i���R�j
                if (l_eqtypeProductRow != null)
                {
                    l_attentionInfoHistoryParams.setProductId(l_eqtypeProductRow.getProductId());
                    l_attentionInfoHistoryParams.setStandardName(l_eqtypeProductRow.getStandardName());
                }

                //�s��h�c
                //�s��.�s��h�c�i���R�j
                if (l_market != null)
                {
                    l_attentionInfoHistoryParams.setMarketId(l_market.getMarketId());
                }

                //�L����
                l_attentionInfoHistoryParams.setValidUntilBizDate(
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),
                        WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //���ӏ��敪�R�[�h
                l_attentionInfoHistoryParams.setAttentionInfoDivCode(
                    l_hostAttentionInfoNotifyParams.getAttentionInfoDivCode());

                if (WEB3HostRequestCodeDef.LIMIT_RANGE_INFO.equals(
                    l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //�]���P���i�ύX�O�j
                    //�X�V�O�̖���.�]���P���i���P�j�i���R�j
                    if (l_productRow != null)
                    {
                        l_attentionInfoHistoryParams.setOldEstimationPrice(l_productRow.getEstimationPrice());
                    }

                    if (l_eqtypeTradedProductRow != null)
                    {
                        //��l�i�I�l�j�i�ύX�O�j
                        //�X�V�O�̊����������.��l�i�I�l�j�i���P�j�i���R�j
                        l_attentionInfoHistoryParams.setOldLastClosingPrice(
                            l_eqtypeTradedProductRow.getLastClosingPrice());

                        //��l�i�ύX�O�j
                        //�X�V�O�̊����������.��l�i���P�j�i���R�j
                        l_attentionInfoHistoryParams.setOldBasePrice(
                            l_eqtypeTradedProductRow.getBasePrice());

                        //�l���`�F�b�N�敪�i�ύX�O�j
                        //�X�V�O�̊����������.�l���`�F�b�N�敪�i���P�j�i���R�j
                        l_attentionInfoHistoryParams.setOldPriceRangeType(
                            l_eqtypeTradedProductRow.getPriceRangeType());

                        //�l���敪�i�ύX�O�j
                        //�X�V�O�̊����������.�l���敪�i���P�j�i���R�j
                        l_attentionInfoHistoryParams.setOldPriceRangeUnitType(
                            l_eqtypeTradedProductRow.getPriceRangeUnitType());

                        if (!l_eqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull())
                        {
                            //�����l���i����l�j�i�ύX�O�j
                            //�X�V�O�̊����������.�����l���i����l�j�i���P�j�i���R�j
                            l_attentionInfoHistoryParams.setOldHighCompPriceRange(
                                l_eqtypeTradedProductRow.getHighCompulsivePriceRange());
                        }

                        if (!l_eqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull())
                        {
                            //�����l���i�����l�j�i�ύX�O�j
                            //�X�V�O�̊����������.�����l���i�����l�j�i���P�j�i���R�j
                            l_attentionInfoHistoryParams.setOldLowCompPriceRange(
                                l_eqtypeTradedProductRow.getLowCompulsivePriceRange());
                        }
                    }

                    if (l_eqtypeTradedProductUpdqRow != null)
                    {
                        //��l�i�I�l�j�i�����j�i�ύX�O�j
                        //�X�V�O�̊����������updq.��l�i�I�l�j�i���P�j�i���R�j
                        l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(
                            l_eqtypeTradedProductUpdqRow.getLastClosingPrice());

                        //��l�i�����j�i�ύX�O�j
                        //�X�V�O�̊����������updq.��l�i���P�j�i���R�j
                        l_attentionInfoHistoryParams.setOldBasePriceUpdq(
                            l_eqtypeTradedProductUpdqRow.getBasePrice());
                    }

                    if (!l_hostAttentionInfoNotifyParams.getBasePriceIsNull())
                    {
                        //�]���P���i�ύX��j
                        //���ӏ��ʒm�L���[.��l�i���P�j
                        l_attentionInfoHistoryParams.setNewEstimationPrice(
                            l_hostAttentionInfoNotifyParams.getBasePrice());

                        //��l�i�I�l�j�i�ύX��j
                        //���ӏ��ʒm�L���[.��l�i���P�j
                        l_attentionInfoHistoryParams.setNewLastClosingPrice(
                            l_hostAttentionInfoNotifyParams.getBasePrice());

                        //��l�i�ύX��j
                        //���ӏ��ʒm�L���[.��l�i���P�j
                        l_attentionInfoHistoryParams.setNewBasePrice(
                            l_hostAttentionInfoNotifyParams.getBasePrice());

                        //��l�i�I�l�j�i�����j�i�ύX��j
                        //���ӏ��ʒm�L���[.��l�i���P�j
                        l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(
                            l_hostAttentionInfoNotifyParams.getBasePrice());

                        //��l�i�����j�i�ύX��j
                        //���ӏ��ʒm�L���[.��l�i���P�j
                        l_attentionInfoHistoryParams.setNewBasePriceUpdq(
                            l_hostAttentionInfoNotifyParams.getBasePrice());
                    }

                    //�����l������i�ύX��j
                    //���ӏ��ʒm�L���[.�����l������i���P�j
                    if (!l_hostAttentionInfoNotifyParams.getHighPriceRangeIsNull())
                    {
                        l_attentionInfoHistoryParams.setNewHighPriceRange(
                            l_hostAttentionInfoNotifyParams.getHighPriceRange());
                    }

                    //�����l�������i�ύX��j
                    //���ӏ��ʒm�L���[.�����l�������i���P�j
                    if (!l_hostAttentionInfoNotifyParams.getLowPriceRangeIsNull())
                    {
                        l_attentionInfoHistoryParams.setNewLowPriceRange(
                            l_hostAttentionInfoNotifyParams.getLowPriceRange());
                    }

                    //�l���`�F�b�N�敪(�ύX��)
                    //"1:�l���`�F�b�N����"�i���P�j
                    l_attentionInfoHistoryParams.setNewPriceRangeType(WEB3PriceRangeTypeDef.CHECK);

                    //�l���敪�i�ύX��j
                    //"1�F�~"�i���P�j
                    l_attentionInfoHistoryParams.setNewPriceRangeUnitType(WEB3PriceRangeIdDef.YEN);
                }

                if (WEB3HostRequestCodeDef.FREE_FORMAT.equals(
                    l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //�\��
                    l_attentionInfoHistoryParams.setFreeFormatTitle(
                        l_hostAttentionInfoNotifyParams.getFreeFormatTitle());
                    //�{��
                    l_attentionInfoHistoryParams.setFreeFormatText(
                        l_hostAttentionInfoNotifyParams.getFreeFormatText());
                }
                //��񔭐�����
                l_attentionInfoHistoryParams.setInfoGenerationTimestamp(
                    l_hostAttentionInfoNotifyParams.getInfoGenerationTimestamp());

                //������t�ĊJ����
                l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(
                    l_hostAttentionInfoNotifyParams.getOrdReceiptRestartTimestamp());

                //������~�����^�����ĊJ����
                l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(
                    l_hostAttentionInfoNotifyParams.getTradeStopRestartTimestamp());

                //�������ʋ敪
                if (WEB3AdminEquityStatusDef.DEALT.equals(l_hostAttentionInfoNotifyParams.getStatus()))
                {
                    //���ӏ��ʒm�L���[.�����敪��"1�F������"�ł������X�V�t���O��"1�F�����X�V�ς�"�̏ꍇ
                    //1�F����
                    if (WEB3AdminEquityProductUpdateFlagDef.PRODUCT_UPDATE.equals(l_strProductUpdateFlag))
                    {
                        l_attentionInfoHistoryParams.setProcessResultDiv(WEB3ProcessResultDivDef.NORMAL);
                    }
                    //�����X�V�t���O��"2�F�������X�V"�̏ꍇ
                    //2�F����i�X�V���j
                    else if (WEB3AdminEquityProductUpdateFlagDef.PRODUCT_NOT_UPDATE.equals(l_strProductUpdateFlag))
                    {
                        l_attentionInfoHistoryParams.setProcessResultDiv(WEB3ProcessResultDivDef.NORMAL_NOT_UPDATE);
                    }
                }
                else if (WEB3AdminEquityStatusDef.ERROR.equals(
                    l_hostAttentionInfoNotifyParams.getStatus()))
                {
                    //���ӏ��ʒm�L���[.�����敪��"9�F�G���["�̏ꍇ
                    //9�F�G���[
                    l_attentionInfoHistoryParams.setProcessResultDiv(WEB3ProcessResultDivDef.ERROR);
                }

                //�쐬���t
                l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //�X�V���t
                l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doInsertQuery(l_attentionInfoHistoryParams);
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

            log.exiting(STR_METHOD_NAME);
            return l_attentionInfoHistoryParams;
        }

        /**
         * (insert���[�����M�e�[�u��)<BR>
         * ���[�����M�e�[�u���A���[�����M�g���e�[�u����Insert���s���B<BR>
         * <BR>
         * �P�j�@@���[���I�u�W�F�N�g�𐶐�����B<BR>
         * �@@�@@[����]<BR>
         * �@@�@@�@@�،���ЃR�[�h : ���ӏ�񗚗��e�[�u��.�،���ЃR�[�h<BR>
         * �@@�@@�@@���M���[���敪 : <BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.�������ʋ敪��"�G���["�̏ꍇ<BR>
         * �@@�@@�@@�@@�@@"31�F���ӏ��ʒm���[��"�@@�{�@@"04�F���ӏ��i�G���[�j"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ���ʁ�"������"�̏ꍇ<BR>
         * �@@�@@�@@�@@�@@"31�F���ӏ��ʒm���[��"�@@�{�@@"01�F���ӏ��i������j"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ���ʁ�"�����l�����"�̏ꍇ<BR>
         * �@@�@@�@@�@@�@@"31�F���ӏ��ʒm���[��"�@@�{�@@"02�F���ӏ��i�����l�����j"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ���ʁ�"�t���[�t�H�[�}�b�g"�̏ꍇ<BR>
         * �@@�@@�@@�@@�@@"31�F���ӏ��ʒm���[��"�@@�{�@@"03�F���ӏ��i�t���[�t�H�[�}�b�g�j"<BR>
         * <BR>
         * �@@�@@�@@����ID : <BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.�������ʋ敪��"�G���["�̏ꍇ�A"1"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�j"�̏ꍇ�A"1"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�s�j"�̏ꍇ�A"2"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�j�̎��"�̏ꍇ�A"3"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�s�j�̎��"�̏ꍇ�A"4"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�j�̉���"�̏ꍇ�A"5"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�s�j�̉���"�̏ꍇ�A"6"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�j�̉����̎��"�̏ꍇ�A"7"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�s�j�̉����̎��"�̏ꍇ�A"8"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�������f"�̏ꍇ�A"9"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�������f�̎��"�̏ꍇ�A"10"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�������f�̉���"�̏ꍇ�A"11"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�������f�̉����̎��"�̏ꍇ�A"12"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�V�K�������̏��l���t�����ꍇ"�̏ꍇ<BR>
         * �@@�@@�@@�@@�@@�E���ӏ�񗚗��e�[�u��.�������ʋ敪��"����"�̏ꍇ�A"1"<BR>
         * �@@�@@�@@�@@�@@�E���ӏ�񗚗��e�[�u��.�������ʋ敪��"����i�X�V���j"�̏ꍇ�A"2"<BR>
         * �@@�@@�@@�@@���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�t���[�t�H�[�}�b�g"�̏ꍇ�A"1"<BR>
         * <BR>
         * �Q�j�@@���[�����M�e�[�u����1��Insert���s���B<BR>
         * �@@�X�V���e�́w���ӏ��ʒm_���[�����M�e�[�u��.xls�x���Q��<BR>
         * <BR>
         * �R�j�@@���[�����M�g���e�[�u����Insert���s�Ȃ��B<BR>
         * �@@�X�V���e�́w���ӏ��ʒm_���[�����M�g���e�[�u��.xls�x���Q��<BR>
         * <BR>
         * @@param l_equityProduct - (��������)<BR>
         * ���������I�u�W�F�N�g<BR>
         * @@param l_market - (�s��)<BR>
         * �s��I�u�W�F�N�g<BR>
         * @@param l_attentionInfoHistoryParams - (���ӏ�񗚗�)<BR>
         * ���ӏ�񗚗��I�u�W�F�N�g<BR>
         * @@throws WEB3BaseException
         * @@roseuid 493CB36902BC
         */
        private void insertSendMail(
            WEB3EquityProduct l_equityProduct,
            Market l_market,
            AttentionInfoHistoryParams l_attentionInfoHistoryParams) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "insertSendMail(WEB3EquityProduct, Market, AttentionInfoHistoryParams)";
            log.entering(STR_METHOD_NAME);

            //���[���I�u�W�F�N�g�𐶐�����B
            //�،���ЃR�[�h : ���ӏ�񗚗��e�[�u��.�،���ЃR�[�h
            String l_strInstitutionCode = l_attentionInfoHistoryParams.getInstitutionCode();
            //���M���[���敪 :
            String l_strSendmailDiv = null;
            String l_strProcessResultDiv = l_attentionInfoHistoryParams.getProcessResultDiv();
            String l_strAttentionInfoType = l_attentionInfoHistoryParams.getAttentionInfoType();
            //���ӏ�񗚗��e�[�u��.�������ʋ敪��"�G���["�̏ꍇ
            //"31�F���ӏ��ʒm���[��"�@@�{�@@"04�F���ӏ��i�G���[�j"
            if (WEB3ProcessResultDivDef.ERROR.equals(l_strProcessResultDiv))
            {
                l_strSendmailDiv = WEB3SendmailDivDef.TTENTION_INFO_ERROR;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ���ʁ�"������"�̏ꍇ
            //�h31�F���ӏ��ʒm���[��"�@@�{�@@"01�F���ӏ��i������j"
            else if (WEB3AttentionInfoTypeDef.SELL_STOP_INFO.equals(l_strAttentionInfoType))
            {
                l_strSendmailDiv = WEB3SendmailDivDef.ATTENTION_INFO_SELL_STOP_INFO;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ���ʁ�"�����l�����"�̏ꍇ
            //�h31�F���ӏ��ʒm���[��"�@@�{�@@"02�F���ӏ��i�����l�����j"
            else if (WEB3AttentionInfoTypeDef.LIMIT_RANGE_INFO.equals(l_strAttentionInfoType))
            {
                l_strSendmailDiv = WEB3SendmailDivDef.TTENTION_INFO_LIMIT_RANGE_INFO;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ���ʁ�"�t���[�t�H�[�}�b�g"�̏ꍇ
            //�h31�F���ӏ��ʒm���[��"�@@�{�@@"03�F���ӏ��i�t���[�t�H�[�}�b�g�j"
            else if (WEB3AttentionInfoTypeDef.FREE_FORMAT.equals(l_strAttentionInfoType))
            {
                l_strSendmailDiv = WEB3SendmailDivDef.TTENTION_INFO_FREE_FORMAT;
            }

            //����ID :
            String l_strDiscernmentId = null;
            String l_strAttentionInfoDivCode = l_attentionInfoHistoryParams.getAttentionInfoDivCode();

            //���ӏ�񗚗��e�[�u��.�������ʋ敪��"�G���["�̏ꍇ�A"1"
            if (WEB3ProcessResultDivDef.ERROR.equals(l_strProcessResultDiv))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ONE;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�j"�̏ꍇ�A"1"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_ORDER_ACCEPT_ENABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ONE;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�s�j"�̏ꍇ�A"2"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_ORDER_ACCEPT_DISABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.TWO;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�j�̎��"�̏ꍇ�A"3"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_CANCEL_ORDER_ACCEPT_ENABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.THREE;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�s�j�̎��"�̏ꍇ�A"4"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_CANCEL_ORDER_ACCEPT_DISABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.FOUR;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�j�̉���"�̏ꍇ�A"5"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_ORDER_ACCEPT_ENABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.FIVE;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�s�j�̉���"�̏ꍇ�A"6"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_ORDER_ACCEPT_DISABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.SIX;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�j�̉����̎��"�̏ꍇ�A"7"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_ENABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.SEVEN;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"������~�i������t�s�j�̉����̎��"�̏ꍇ�A"8"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_DISABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.EIGHT;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�������f"�̏ꍇ�A"9"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.NINE;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�������f�̎��"�̏ꍇ�A"10"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_CANCEL.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.TEN;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�������f�̉���"�̏ꍇ�A"11"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_RELEASE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ELEVEN;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�������f�̉����̎��"�̏ꍇ�A"12"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_RELEASE_CANCEL.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.TWELVE;
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�V�K�������̏��l���t�����ꍇ"�̏ꍇ
            else if (WEB3AttentionInfoDivCodeDef.OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE.equals(
                l_strAttentionInfoDivCode))
            {
                //�E���ӏ�񗚗��e�[�u��.�������ʋ敪��"����"�̏ꍇ�A"1"
                if (WEB3ProcessResultDivDef.NORMAL.equals(l_strProcessResultDiv))
                {
                    l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ONE;
                }
                //�E���ӏ�񗚗��e�[�u��.�������ʋ敪��"����i�X�V���j"�̏ꍇ�A"2"
                else if (WEB3ProcessResultDivDef.NORMAL_NOT_UPDATE.equals(l_strProcessResultDiv))
                {
                    l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.TWO;
                }
            }
            //���ӏ�񗚗��e�[�u��.���ӏ��敪�R�[�h��"�t���[�t�H�[�}�b�g"�̏ꍇ�A"1"
            else if (WEB3AttentionInfoDivCodeDef.FREE_FORMAT.equals(l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ONE;
            }

            WEB3GentradeMailInfo l_mailInfo =
                new WEB3GentradeMailInfo(
                    l_strInstitutionCode,
                    l_strSendmailDiv,
                    l_strDiscernmentId);

            //���[�����M�e�[�u����1��Insert���s���B
            //�X�V���e�́w���ӏ��ʒm_���[�����M�e�[�u��.xls�x���Q��
            MailProcParams l_mailProcParams = new MailProcParams();

            l_mailProcParams.setInstitutionCode(l_strInstitutionCode);
            l_mailProcParams.setBranchCode(WEB3BranchCodeDef.DEFAULT);
            l_mailProcParams.setSendmailDiv(l_mailInfo.getSendmailDiv());
            l_mailProcParams.setDiscernmentId(l_mailInfo.getDiscernmentId());
            l_mailProcParams.setAccountCode(WEB3AccountCodeDef.ACCOUNT_CODE_0000000);
            l_mailProcParams.setMailId(l_attentionInfoHistoryParams.getAttentionInfoHistoryId());
            if (l_attentionInfoHistoryParams.getStandardName() != null)
            {
                l_mailProcParams.setName1(l_attentionInfoHistoryParams.getStandardName());
            }
            else
            {
                l_mailProcParams.setName1(" ");
            }
            if (l_market != null)
            {
                l_mailProcParams.setName2(l_market.getMarketName());
            }
            else
            {
                l_mailProcParams.setName2(" ");
            }
            l_mailProcParams.setStatus(WEB3AdminEquityStatusDef.NOT_DEAL);
            l_mailProcParams.setEmailAddress(l_mailInfo.getSendAddress());
            l_mailProcParams.setSendEmailAddress(l_mailInfo.getMailSender());
            l_mailProcParams.setSubject(l_mailInfo.getSubject());
            if (l_attentionInfoHistoryParams.getFreeFormatText() != null)
            {
                l_mailProcParams.setMailText(l_attentionInfoHistoryParams.getFreeFormatText());
            }
            else
            {
                l_mailProcParams.setMailText(" ");
            }
            l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            try
            {
                Processors.getDefaultProcessor().doInsertQuery(l_mailProcParams);
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

            //���[�����M�g���e�[�u����Insert���s�Ȃ��B
            //�X�V���e�́w���ӏ��ʒm_���[�����M�g���e�[�u��.xls�x���Q��
            String l_strSendMailDiv = l_mailProcParams.getSendmailDiv();

            ExtMailProcParams l_extMailProcParams = new ExtMailProcParams();

            l_extMailProcParams.setInstitutionCode(l_mailProcParams.getInstitutionCode());
            l_extMailProcParams.setBranchCode(l_mailProcParams.getBranchCode());
            l_extMailProcParams.setSendmailDiv(l_strSendMailDiv);
            l_extMailProcParams.setDiscernmentId(l_mailProcParams.getDiscernmentId());
            l_extMailProcParams.setAccountCode(l_mailProcParams.getAccountCode());
            l_extMailProcParams.setMailId(l_mailProcParams.getMailId());
            l_extMailProcParams.setDeleteFlag(BooleanEnum.FALSE);

            //�o�^���e:���ږ� ���ړ��e
            Map l_mapItems = new HashMap();
            //�����R�[�h
            //����������null�̏ꍇ�A��������.�����R�[�h�ȊO�Anull
            if (l_equityProduct != null)
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE, l_equityProduct.getProductCode());
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE, null);
            }

            //�s��R�[�h
            //�s�ꁂnull�̏ꍇ�A�s��.�s��R�[�h�ȊO�Anull
            if (l_market != null)
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.MARKET_CODE, l_market.getMarketCode());
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.MARKET_CODE, null);
            }

            //��񔭐�����
            //���ӏ�񗚗��e�[�u��.��񔭐������iyyyy/mm/dd hh:mm:ss�`���j
            Timestamp l_tsInfoGenerationTimestamp =
                l_attentionInfoHistoryParams.getInfoGenerationTimestamp();
            String l_strInfoGenerationTimestamp = WEB3DateUtility.formatDate(
                l_tsInfoGenerationTimestamp, WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                l_strInfoGenerationTimestamp);

            //������~�����^�����ĊJ����
            //���ӏ�񗚗��e�[�u��.������~�����^�����ĊJ�����iyyyy/mm/dd hh:mm:ss�`���j
            Timestamp l_tsTradeStopRestartTimestamp =
                l_attentionInfoHistoryParams.getTradeStopRestartTimestamp();
            if (l_tsTradeStopRestartTimestamp != null)
            {
                String l_strTradeStopRestartTimestamp = WEB3DateUtility.formatDate(
                    l_tsTradeStopRestartTimestamp, WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.TRADE_STOP_RESTART_TIMESTAMP,
                    l_strTradeStopRestartTimestamp);
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.TRADE_STOP_RESTART_TIMESTAMP, null);
            }
            //������t�ĊJ����
            //���ӏ�񗚗��e�[�u��.������t�ĊJ�����iyyyy/mm/dd hh:mm:ss�`���j
            Timestamp l_tsOrdReceiptRestartTimestamp =
                l_attentionInfoHistoryParams.getOrdReceiptRestartTimestamp();
            if (l_tsOrdReceiptRestartTimestamp != null)
            {
                String l_strOrdReceiptRestartTimestamp = WEB3DateUtility.formatDate(
                    l_tsOrdReceiptRestartTimestamp, WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.ORD_RECEIPT_RESTART_TIMESTAMP,
                    l_strOrdReceiptRestartTimestamp);
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.ORD_RECEIPT_RESTART_TIMESTAMP, null);
            }

            //�]���P���i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.�]���P���i�ύX�O�j
            if (!l_attentionInfoHistoryParams.getOldEstimationPriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_ESTIMATION_PRICE,
                    l_attentionInfoHistoryParams.getOldEstimationPrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_ESTIMATION_PRICE, null);
            }

            //�]���P���i�ύX��j
            //���ӏ�񗚗��e�[�u��.�]���P���i�ύX��j
            if (!l_attentionInfoHistoryParams.getNewEstimationPriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_ESTIMATION_PRICE,
                    l_attentionInfoHistoryParams.getNewEstimationPrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_ESTIMATION_PRICE, null);
            }

            //��l�i�I�l�j�i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.��l�i�I�l�j�i�ύX�O�j
            if (!l_attentionInfoHistoryParams.getOldLastClosingPriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE,
                    l_attentionInfoHistoryParams.getOldLastClosingPrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE, null);
            }

            //��l�i�I�l�j�i�ύX��j
            //���ӏ�񗚗��e�[�u��.��l�i�I�l�j�i�ύX��j
            if (!l_attentionInfoHistoryParams.getNewLastClosingPriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE,
                    l_attentionInfoHistoryParams.getNewLastClosingPrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE, null);
            }

            //��l�i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.��l�i�ύX�O�j
            if (!l_attentionInfoHistoryParams.getOldBasePriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE,
                    l_attentionInfoHistoryParams.getOldBasePrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE, null);
            }

            //��l�i�ύX��j
            //���ӏ�񗚗��e�[�u��.��l�i�ύX��j
            if (!l_attentionInfoHistoryParams.getNewBasePriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE,
                    l_attentionInfoHistoryParams.getNewBasePrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE, null);
            }

            //�l���`�F�b�N�敪�i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.�l���`�F�b�N�敪�i�ύX�O�j
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_TYPE,
                l_attentionInfoHistoryParams.getOldPriceRangeType());

            //�l���`�F�b�N�敪�i�ύX��j
            //���ӏ�񗚗��e�[�u��.�l���`�F�b�N�敪�i�ύX��j
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_TYPE,
                l_attentionInfoHistoryParams.getNewPriceRangeType());

            //�l���敪�i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.�l���敪�i�ύX�O�j
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_UNIT_TYPE,
                l_attentionInfoHistoryParams.getOldPriceRangeUnitType());

            //�l���敪�i�ύX��j
            //���ӏ�񗚗��e�[�u��.�l���敪�i�ύX��j
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_UNIT_TYPE,
                l_attentionInfoHistoryParams.getNewPriceRangeUnitType());

            //�����l���i����l�j�i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.�����l���i����l�j�i�ύX�O�j��null����
            //���ӏ�񗚗��e�[�u��.��l�i�ύX�O�j��nul�̏ꍇ
            //���ӏ�񗚗��e�[�u��.��l�i�ύX�O�j+���ӏ�񗚗��e�[�u��.�����l���i����l�j�i�ύX�O�j
            //�i�������A�v�Z���ʂ�0�ȉ��̏ꍇ��null�j
            //��L�ȊO�̏ꍇ�Anull
            if (!l_attentionInfoHistoryParams.getOldHighCompPriceRangeIsNull()
                && !l_attentionInfoHistoryParams.getOldBasePriceIsNull())
            {
                BigDecimal l_bdOldHighCompPriceRange =
                    new BigDecimal(l_attentionInfoHistoryParams.getOldHighCompPriceRange() + "");
                BigDecimal l_bdOldBasePrice =
                    new BigDecimal(l_attentionInfoHistoryParams.getOldBasePrice() + "");
                BigDecimal l_bdOldHighPriceRange = l_bdOldBasePrice.add(l_bdOldHighCompPriceRange);

                if (l_bdOldHighPriceRange.doubleValue() > 0)
                {
                    l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE,
                        l_bdOldHighPriceRange.doubleValue() + "");
                }
                else
                {
                    l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE, null);
                }
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE, null);
            }

            //�����l������i�ύX��j
            //���ӏ�񗚗��e�[�u��.�����l������i�ύX��j
            if (!l_attentionInfoHistoryParams.getNewHighPriceRangeIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_HIGH_PRICE_RANGE,
                    l_attentionInfoHistoryParams.getNewHighPriceRange() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_HIGH_PRICE_RANGE, null);
            }

            //�����l���i�����l�j�i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.�����l���i�����l�j�i�ύX�O�j��null����
            //���ӏ�񗚗��e�[�u��.��l�i�ύX�O�j��null�̏ꍇ
            //���ӏ�񗚗��e�[�u��.��l�i�ύX�O�j-���ӏ�񗚗��e�[�u��.�����l���i�����l�j�i�ύX�O�j
            //�i�������A�v�Z���ʂ�0�ȉ��̏ꍇ��null�j
            //��L�ȊO�̏ꍇ�Anull
            if (!l_attentionInfoHistoryParams.getOldLowCompPriceRangeIsNull()
                && !l_attentionInfoHistoryParams.getOldBasePriceIsNull())
            {
                BigDecimal l_bdOldLowCompPriceRange =
                    new BigDecimal(l_attentionInfoHistoryParams.getOldLowCompPriceRange() + "");
                BigDecimal l_bdOldBasePrice =
                    new BigDecimal(l_attentionInfoHistoryParams.getOldBasePrice() + "");
                BigDecimal l_bdOldLowPriceRange = l_bdOldBasePrice.subtract(l_bdOldLowCompPriceRange);

                if (l_bdOldLowPriceRange.doubleValue() > 0)
                {
                    l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE,
                        l_bdOldLowPriceRange.doubleValue() + "");
                }
                else
                {
                    l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE, null);
                }
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE, null);
            }

            //�����l���i�����l�j�i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.�����l���i�����l�j�i�ύX�O�j
            if (!l_attentionInfoHistoryParams.getNewLowPriceRangeIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LOW_PRICE_RANGE,
                    l_attentionInfoHistoryParams.getNewLowPriceRange() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LOW_PRICE_RANGE, null);
            }

            //��l�i�I�l�j�i�����j�i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.��l�i�I�l�j�i�����j�i�ύX�O�j
            if (!l_attentionInfoHistoryParams.getOldLastClosingPriceUpdqIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE_UPDQ,
                    l_attentionInfoHistoryParams.getOldLastClosingPriceUpdq() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE_UPDQ, null);
            }

            //��l�i�I�l�j�i�����j�i�ύX��j
            //���ӏ�񗚗��e�[�u��.��l�i�I�l�j�i�����j�i�ύX��j
            if (!l_attentionInfoHistoryParams.getNewLastClosingPriceUpdqIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE_UPDQ,
                    l_attentionInfoHistoryParams.getNewLastClosingPriceUpdq() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE_UPDQ, null);
            }

            //��l�i�����j�i�ύX�O�j
            //���ӏ�񗚗��e�[�u��.��l�i�����j�i�ύX�O�j
            if (!l_attentionInfoHistoryParams.getOldBasePriceUpdqIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE_UPDQ,
                    l_attentionInfoHistoryParams.getOldBasePriceUpdq() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE_UPDQ, null);
            }

            //��l�i�����j�i�ύX��j
            //���ӏ�񗚗��e�[�u��.��l�i�����j�i�ύX��j
            if (!l_attentionInfoHistoryParams.getNewBasePriceUpdqIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE_UPDQ,
                    l_attentionInfoHistoryParams.getNewBasePriceUpdq() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE_UPDQ, null);
            }

            //�\��
            //���ӏ�񗚗��e�[�u��.�\��
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.TITLE,
                l_attentionInfoHistoryParams.getFreeFormatTitle());

            Map l_map = new HashMap();

            //���[�����M�e�[�u��.���M���[���敪 = 3101�F���ӏ��i������j
            //�o�^���ږ�:�����R�[�h�A�s��R�[�h�A��񔭐������A������~�����^�����ĊJ�����A������t�ĊJ����
            l_map.put(WEB3SendmailDivDef.ATTENTION_INFO_SELL_STOP_INFO,
                new String[]{
                    WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE,
                    WEB3AdminEquityHashMapKeyDef.MARKET_CODE,
                    WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.TRADE_STOP_RESTART_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.ORD_RECEIPT_RESTART_TIMESTAMP});

            //���[�����M�e�[�u��.���M���[���敪 = 3102�F���ӏ��i�����l�����j
            //�o�^���ږ�:�����R�[�h�A�s��R�[�h�A��񔭐������A�]���P���i�ύX�O�j...
            l_map.put(WEB3SendmailDivDef.TTENTION_INFO_LIMIT_RANGE_INFO,
                new String[]{
                    WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE,
                    WEB3AdminEquityHashMapKeyDef.MARKET_CODE,
                    WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.OLD_ESTIMATION_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_ESTIMATION_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_TYPE,
                    WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_TYPE,
                    WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_UNIT_TYPE,
                    WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_UNIT_TYPE,
                    WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.NEW_HIGH_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.NEW_LOW_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE_UPDQ});

            //���[�����M�e�[�u��.���M���[���敪 = 3103�F���ӏ��i�t���[�t�H�[�}�b�g�j
            //�o�^���ږ�:�s��R�[�h�A��񔭐������A�\��
            l_map.put(WEB3SendmailDivDef.TTENTION_INFO_FREE_FORMAT,
                new String[]{
                    WEB3AdminEquityHashMapKeyDef.MARKET_CODE,
                    WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.TITLE});

            //���[�����M�e�[�u��.���M���[���敪 = 3104�F���ӏ��i�G���[�j
            //�o�^���ږ�:�@@�S��
            l_map.put(WEB3SendmailDivDef.TTENTION_INFO_ERROR,
                new String[]{
                    WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE,
                    WEB3AdminEquityHashMapKeyDef.MARKET_CODE,
                    WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.TRADE_STOP_RESTART_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.ORD_RECEIPT_RESTART_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.OLD_ESTIMATION_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_ESTIMATION_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_TYPE,
                    WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_TYPE,
                    WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_UNIT_TYPE,
                    WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_UNIT_TYPE,
                    WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.NEW_HIGH_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.NEW_LOW_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.TITLE});

            if (l_map.containsKey(l_strSendMailDiv))
            {
                int l_intLength = 0;
                String[] l_strIndexs = (String[])l_map.get(l_strSendMailDiv);
                l_intLength = l_strIndexs.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    if (l_mapItems.containsKey(l_strIndexs[i]) && l_mapItems.get(l_strIndexs[i]) != null)
                    {
                        l_extMailProcParams.setItemName(l_strIndexs[i]);
                        l_extMailProcParams.setItemContents((String)l_mapItems.get(l_strIndexs[i]));
                        l_extMailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_extMailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                        try
                        {
                            Processors.getDefaultProcessor().doInsertQuery(l_extMailProcParams);
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
                    }
                }
            }
            log.exiting(STR_METHOD_NAME);
        }
    }
}@
