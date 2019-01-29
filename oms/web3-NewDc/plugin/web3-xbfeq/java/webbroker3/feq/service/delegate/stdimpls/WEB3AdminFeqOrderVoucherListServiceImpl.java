head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO���������������`�[�ꗗ�T�[�r�XImpl(WEB3AdminFeqOrderVoucherListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �s�p (���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
Revesion History : 2007/01/16 ���� (���u) ���f��No.330�A332�A337
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.470
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransactionTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.define.WEB3FeqOrderAcceptTypeDef;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListCondUnit;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderVoucherListService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO���������������`�[�ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��ҊO���������������`�[�ꗗ�T�[�r�X�����N���X<BR>
 *    
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListServiceImpl implements WEB3AdminFeqOrderVoucherListService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderVoucherListServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F201E4
     */
    public WEB3AdminFeqOrderVoucherListServiceImpl() 
    {
     
    }
    
    /**
     * �O���������������`�[�ꗗ�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *    get���͉��()<BR>
     *    get�ꗗ���()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FC6DB02B3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqOrderVoucherListInputRequest)
        {
            //get���͉��()
            l_response = 
                this.getInputScreen((WEB3AdminFeqOrderVoucherListInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderVoucherListRequest)
        {
            //get�ꗗ���()
            l_response = 
                this.getListScreen((WEB3AdminFeqOrderVoucherListRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj���������`�[�ꗗ�jget���͉�ʁv �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FC6D7035F
     */
    protected WEB3AdminFeqOrderVoucherListInputResponse getInputScreen(WEB3AdminFeqOrderVoucherListInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderVoucherListInputRequest) ";
        log.entering(STR_METHOD_NAME);        
        
        //1.1:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
        }
        
        //1.2:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);
        
        //1.3:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.4:get�戵�\�O���s��(String)
        String[] l_strMarketCodes =
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY);

        if (l_strMarketCodes == null || l_strMarketCodes.length == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        //1.8:createResponse( )
        WEB3AdminFeqOrderVoucherListInputResponse l_response = 
           (WEB3AdminFeqOrderVoucherListInputResponse)l_request.createResponse();
        
        //1.9:(*2)�v���p�e�B�Z�b�g   
        l_response.marketCodeList = l_strMarketCodes;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �ꗗ��ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj���������`�[�ꗗ�jget�ꗗ��ʁv �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}�i�ǁj���������`�[�ꗗ / �i�i�ǁj���������`�[�ꗗ�jget�ꗗ���<BR>
     * �@@�@@:  1.8.doFindAllQuery:�������ʂ��擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�@@�u�Y������f�[�^�����݂��܂���B�v��<BR>
     * �@@�@@�@@�Ɩ��G���[��ԋp����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_00398<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderVoucherListResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FC6D7037E
     */
    protected WEB3AdminFeqOrderVoucherListResponse getListScreen(WEB3AdminFeqOrderVoucherListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqOrderVoucherListRequest) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1.1: validate( )
            l_request.validate();
            
            //1.2:getInstanceFrom���O�C�����( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
            if (l_admin == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
            }
            
            //1.3:validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
            l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);
            
            //1.4:get�،���ЃR�[�h( )
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            
            //1.5:getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException

            //(*)���������i�[��̍쐬
            String l_strWhere = null;
            Object[] l_objs = null;
            List l_lisOrderUnitRows = null;
            List l_lisObjs = new ArrayList();
            //1.6:(*1)���N�G�X�g.�ꗗ�����̊e�v�f�ɂ���Loop����
            if (l_request.condList != null && l_request.condList.length > 0)
            {
                int l_intCnt = l_request.condList.length;
                
                log.debug("1.6:(*1)���N�G�X�g.�ꗗ�����̊e�v�f�ɂ���Loop����:" + l_intCnt);
                
                WEB3AdminFeqOrderVoucherListCondUnit[] l_orderVoucherListConds = l_request.condList;
                
                for (int i = 0; i < l_intCnt; i++)
                {
                    log.debug("����:" + i);
                    
                    //1.6.1:create�擾����������(�O���������������`�[�ꗗ����)
                    String l_strCreateQuery = this.createQueryString(l_orderVoucherListConds[i]);

                    //1.6.2:create�擾�����f�[�^�R���e�i(String, �O���������������`�[�ꗗ����)
                    l_objs = this.createQueryContainer(
                        l_strInstitutionCode,
                        l_orderVoucherListConds[i]);

                    //(*)���������i�[��ւ̒ǉ�
                    if (l_strWhere == null)
                    {
                        l_strWhere = "(" + l_strCreateQuery + ")";
                    }
                    else
                    {
                        l_strWhere += " or (" + l_strCreateQuery + ")";
                    }
                    for (int j = 0; j < l_objs.length; j++)
                    {
                        l_lisObjs.add(l_objs[j]);
                    }
                }

                //doFindAllQuery(arg0 : String, arg1 : String,
                //     arg2 : String, arg3 : String, arg4 : Object[])
                //[����] 
                // Row�^�C�v�F�@@�����P��Row.TYPE
                // Where�F�@@�쐬������������������
                // �\�[�g�F�@@"biz_date, market_id"�i������ ����, �s��ID �����j
                // condition�F�@@null
                // ���X�g�F�@@�쐬�������������f�[�^�R���e�i
                l_lisOrderUnitRows = l_queryProcessor.doFindAllQuery(
                    FeqOrderUnitRow.TYPE,
                    l_strWhere,
                    "biz_date asc, market_id asc",
                    null,
                    l_lisObjs.toArray());

                if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
                {
                    log.debug("�Y������f�[�^�����݂��܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                         WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                         this.getClass().getName() + STR_METHOD_NAME,
                         "�Y������f�[�^�����݂��܂���B");
                }
            }

            //(*)�擾�σI�u�W�F�N�g���X�g�쐬
            HashMap l_hmBranch = new HashMap();
            HashMap l_hmAccount = new HashMap();
            HashMap l_hmTrader = new HashMap();
            HashMap l_hmProduct = new HashMap();
            HashMap l_hmMarket = new HashMap();
            HashMap l_hmCurrency = new HashMap();

            //StringBuffer( )
            StringBuffer l_sbCSVData = new StringBuffer();

            //ArrayList( )
            ArrayList l_arrayListCSV = new ArrayList();

            //(*2)�擾���������P�ʖ���Loop����
            int l_intRowsCnt = l_lisOrderUnitRows.size();
            for (int i = 0; i < l_intRowsCnt; i++)
            {
                FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisOrderUnitRows.get(i);

                //delete(arg0 : int, arg1 : int)
                //[����]
                // arg0�F�@@0�i�Œ�j
                // arg1�F�@@StringBuffer�C���X�^���X.length
                l_sbCSVData.delete(0, l_sbCSVData.length());

                //getObject
                //[����]
                // �L�[���F�@@�����Ώۂ̗v�f.���XID
                // �擾�σI�u�W�F�N�g�ꗗ�F�@@�쐬�����擾�σI�u�W�F�N�g���X�g�i���X�j
                // �N���X���F�@@���X.class
                // �����P��Row�F�@@�����Ώۂ̗v�f
                WEB3GentradeBranch l_branch =
                    (WEB3GentradeBranch)this.getObject(
                        l_orderUnitRow.getBranchId() + "",
                        l_hmBranch,
                        WEB3GentradeBranch.class,
                        l_orderUnitRow);

                //getObject
                //[����]
                // �L�[���F�@@�����Ώۂ̗v�f.����ID
                // �擾�σI�u�W�F�N�g�ꗗ�F�@@�쐬�����擾�σI�u�W�F�N�g���X�g�i�ڋq�j
                // �N���X���F�@@�ڋq.class
                // �����P��Row�F�@@�����Ώۂ̗v�f
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)this.getObject(
                        l_orderUnitRow.getAccountId() + "",
                        l_hmAccount,
                        WEB3GentradeMainAccount.class,
                        l_orderUnitRow);

                //(*)�����Ώۂ̗v�f.�����ID != null�̏ꍇ
                WEB3GentradeTrader l_trader = null;
                if (!l_orderUnitRow.getTraderIdIsNull())
                {
                    //getObject
                    //[����]
                    // �L�[���F�@@�����Ώۂ̗v�f.�����ID
                    // �擾�σI�u�W�F�N�g�ꗗ�F�@@�쐬�����擾�σI�u�W�F�N�g���X�g�i���ҁj
                    // �N���X���F�@@����.class
                    // �����P��Row�F�@@�����Ώۂ̗v�f
                    l_trader =
                        (WEB3GentradeTrader)this.getObject(
                            l_orderUnitRow.getTraderId() + "",
                            l_hmTrader,
                            WEB3GentradeTrader.class,
                            l_orderUnitRow);
                }

                //getObject
                //[����]
                // �L�[���F�@@�����Ώۂ̗v�f.����ID
                // �擾�σI�u�W�F�N�g�ꗗ�F�@@�쐬�����擾�σI�u�W�F�N�g���X�g�i�����j
                // �N���X���F�@@�O������.class
                // �����P��Row�F�@@�����Ώۂ̗v�f
                WEB3FeqProduct l_product =
                    (WEB3FeqProduct)this.getObject(
                        l_orderUnitRow.getProductId() + "",
                        l_hmProduct,
                        WEB3FeqProduct.class,
                        l_orderUnitRow);

                //getObject
                //[����]
                // �L�[���F�@@�����Ώۂ̗v�f.�s��ID
                // �擾�σI�u�W�F�N�g�ꗗ�F�@@�쐬�����擾�σI�u�W�F�N�g���X�g�i�s��j
                // �N���X���F�@@�s��.class
                // �����P��Row�F�@@�����Ώۂ̗v�f
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)this.getObject(
                        l_orderUnitRow.getMarketId() + "",
                        l_hmMarket,
                        WEB3GentradeMarket.class,
                        l_orderUnitRow);

                //getObject
                //[����]
                // �L�[���F�@@�����Ώۂ̗v�f.�،���ЃR�[�h + �ʉ݃R�[�h
                // �擾�σI�u�W�F�N�g�ꗗ�F�@@�쐬�����擾�σI�u�W�F�N�g���X�g�i�ʉ݁j
                // �N���X���F�@@�ʉ�.class
                // �����P��Row�F�@@�����Ώۂ̗v�f
                WEB3GentradeCurrency l_currency =
                    (WEB3GentradeCurrency)this.getObject(
                        l_orderUnitRow.getInstitutionCode() + l_orderUnitRow.getCurrencyCode(),
                        l_hmCurrency,
                        WEB3GentradeCurrency.class,
                        l_orderUnitRow);

                //(*)CSV�f�[�^�쐬
                //�،���ЃR�[�h
                l_sbCSVData.append(l_orderUnitRow.getInstitutionCode());

                //���X�R�[�h
                l_sbCSVData.append("," + l_branch.getBranchCode());
                //�����ԍ�
                l_sbCSVData.append("," + l_mainAccount.getDisplayAccountCode());
                //���҃R�[�h
                if (l_trader != null)
                {
                    l_sbCSVData.append("," + l_trader.getTraderCode());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //���ʃR�[�h
                l_sbCSVData.append("," + l_orderUnitRow.getOrderRequestNumber());
                //�`�[No
                l_sbCSVData.append("," + l_orderUnitRow.getVoucherNo());
                //�����V�[�P���XNo
                l_sbCSVData.append("," + l_orderUnitRow.getOrderId());
                //�����R�[�h
                l_sbCSVData.append("," + l_product.getProductCode());
                //���n�����R�[�h
                l_sbCSVData.append("," + l_product.getOffshoreProductCode());
                //������
                l_sbCSVData.append("," + l_product.getDisplayProductName());
                //�s��R�[�h
                l_sbCSVData.append("," + l_market.getMarketCode());
                //�s�ꖼ
                l_sbCSVData.append("," + l_market.getMarketName());
                //����
                if (OrderTypeEnum.FEQ_BUY.equals(l_orderUnitRow.getOrderType()))
                {
                    l_sbCSVData.append("," + WEB3TransactionTypeDef.BUY);
                }
                else
                {
                    l_sbCSVData.append("," + WEB3TransactionTypeDef.SELL);
                }
                //��������
                l_sbCSVData.append("," + WEB3StringTypeUtility.formatNumber(new BigDecimal(String.valueOf(l_orderUnitRow.getQuantity())).doubleValue()));
                //�w�l�E���s
                if (l_orderUnitRow.getLimitPrice() == 0)
                {
                    l_sbCSVData.append("," + WEB3OrderPriceDivDef.MARKET_PRICE);
                }
                else
                {
                    l_sbCSVData.append("," + WEB3OrderPriceDivDef.LIMIT_PRICE);
                }
                //�P��
                l_sbCSVData.append("," + WEB3StringTypeUtility.formatNumber(new BigDecimal(String.valueOf(l_orderUnitRow.getLimitPrice())).doubleValue()));
                //���ϋ敪
                if (l_orderUnitRow.getSettleDiv() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getSettleDiv());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //�ʉ݃R�[�h
                l_sbCSVData.append("," + l_orderUnitRow.getCurrencyCode());
                //�ʉݖ���
                l_sbCSVData.append("," + l_currency.getCurrencyName());
                //��������
                if (l_orderUnitRow.getReceivedDateTime() != null)
                {
                    l_sbCSVData.append("," +
                        WEB3DateUtility.formatDate(
                            l_orderUnitRow.getReceivedDateTime(), "yyyy/MM/dd HH:mm:ss"));
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //������
                l_sbCSVData.append("," + l_orderUnitRow.getBizDate());
                //���s����
                if (l_orderUnitRow.getExecutionConditionType() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getExecutionConditionType().intValue());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //��������
                if (l_orderUnitRow.getOrderConditionType() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getOrderConditionType());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //�����������Z�q
                if (l_orderUnitRow.getOrderCondOperator() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getOrderCondOperator());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //���������P��
                if (!l_orderUnitRow.getStopOrderPriceIsNull())
                {
                    l_sbCSVData.append("," + WEB3StringTypeUtility.formatNumber(new BigDecimal(String.valueOf(l_orderUnitRow.getStopOrderPrice())).doubleValue()));
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //�iW�w�l�j�����w�l
                if (!l_orderUnitRow.getWLimitPriceIsNull())
                {
                    l_sbCSVData.append("," + WEB3StringTypeUtility.formatNumber(new BigDecimal(String.valueOf(l_orderUnitRow.getWLimitPrice())).doubleValue()));
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //�������
                if (OrderStatusEnum.ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.ORDERING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.MODIFYING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NAN);
                }
                else if (OrderStatusEnum.ORDERED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NOT_NAN);
                }
                else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.NOT_CANCELLED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR);
                }
                else if (OrderStatusEnum.MODIFIED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCELLED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3FeqOrderAcceptTypeDef.CNANGE_CANCELED_DATA);
                }
                //���敪
                if (l_orderUnitRow.getExecutedQuantity() == 0)
                {
                    l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE);
                }
                else if(l_orderUnitRow.getExecutedQuantity() > 0)
                {
                    if (l_orderUnitRow.getExecutedQuantity() < l_orderUnitRow.getQuantity())
                    {
                        if (WEB3TemporaryExecutionFlagDef.DEFAULT.equals(l_orderUnitRow.getTemporaryExecutionFlag()))
                        {
                            l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE);
                        }
                        else
                        {
                            l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE);
                        }
                    }
                    else if (l_orderUnitRow.getExecutedQuantity() == l_orderUnitRow.getQuantity())
                    {
                        if (WEB3TemporaryExecutionFlagDef.DEFAULT.equals(l_orderUnitRow.getTemporaryExecutionFlag()))
                        {
                            l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE);
                        }
                        else
                        {
                            l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE);
                        }
                    }
                }
                //��������敪
                l_sbCSVData.append("," + l_orderUnitRow.getModifyCancelType());
                //�����`���l��
                if (l_orderUnitRow.getOrderChanel() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getOrderChanel());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //�t�@@�N�^�[
                if (l_orderUnitRow.getFactor() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getFactor());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //�萔��No
                if (l_orderUnitRow.getCommTblNo() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getCommTblNo());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //�萔��No�}��
                if (l_orderUnitRow.getCommTblSubNo() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getCommTblSubNo());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //���i�R�[�h
                if (l_orderUnitRow.getCommProductCode() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getCommProductCode());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //�����o�H�敪
                if (l_orderUnitRow.getOrderRootDiv() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getOrderRootDiv());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //���[���敪
                if (OrderStatusEnum.ORDERING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.MODIFYING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3MqStatusDef.NOT_SEND_MAIL);
                }
                else
                {
                    l_sbCSVData.append("," + WEB3MqStatusDef.MAIL_SENDED);
                }
                //��������敪
                if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
                {
                    l_sbCSVData.append("," + WEB3TaxTypeSpecialDef.NORMAL);
                }
                else if (TaxTypeEnum.SPECIAL.equals(l_orderUnitRow.getTaxType()))
                {
                    l_sbCSVData.append("," + WEB3TaxTypeSpecialDef.SPECIAL);
                }
                //�^�p�R�[�h
                l_sbCSVData.append("," + l_orderUnitRow.getOrderEmpCode());

                //add(arg0 : Object)
                //[����]
                //�@@arg0�F�@@��������StringBuffer�C���X�^���X.toString()
                l_arrayListCSV.add(l_sbCSVData.toString());
            }

            //createResponse( )
            WEB3AdminFeqOrderVoucherListResponse l_response = 
               (WEB3AdminFeqOrderVoucherListResponse)l_request.createResponse();

            //(*3)�v���p�e�B�Z�b�g
            //(*3) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
            //���������`�[�F sort()�̖�
            String[] l_strOrderVoucherList = new String[l_arrayListCSV.size()];
            l_arrayListCSV.toArray(l_strOrderVoucherList);
            l_response.orderVoucherList = l_strOrderVoucherList;

            log.exiting(STR_METHOD_NAME);
            return l_response;

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
    }
    
    /**
     * (create�擾����������)<BR>
     * �擾����������𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�s��ID<BR>
     * <BR>
     *    "market_id = ? " ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j������<BR>
     * <BR>
     * �R�|�P�j����.�ꗗ����.�������i���j != null �̏ꍇ<BR>
     * <BR>
     *    " and biz_date >= ? and biz_date <= ?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�|�Q�j����.�ꗗ����.�������i���j == null �̏ꍇ<BR>
     * <BR>
     *    " and biz_date = ?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �S�j���������������ԋp����B<BR>
     * @@param l_listCond - (�ꗗ����)<BR>
     * �O���������������`�[�ꗗ�����I�u�W�F�N�g<BR>
     * 
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 42A01AC0001E
     */
    protected String createQueryString(WEB3AdminFeqOrderVoucherListCondUnit l_listCond) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminFeqOrderVoucherListCondUnit) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_listCond == null)
        {
            log.debug("�ꗗ���������w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�ꗗ���������w��(null)�ł��B");
        }
        
        //�P�j��̕�����𐶐�����B
        String l_strQueryStr = null;
        
        //�Q�j�s��ID
        // "market_id = ? " ���P�j�̕�����ɒǉ�����B
        l_strQueryStr = " market_id = ?";
        
        //�R�j������
        //�R�|�P�j����.�ꗗ����.�������i���j != null �̏ꍇ
        //" and biz_date >= ? and biz_date <= ?" ���P�j�̕�����ɒǉ�����B
        if (l_listCond.orderBizDateFrom != null)
        {
            l_strQueryStr += " and biz_date >= ? and biz_date <= ? ";
        }        
        //�R�|�Q�j����.�ꗗ����.�������i���j == null �̏ꍇ
        // " and biz_date = ?" ���P�j�̕�����ɒǉ�����B
        else
        {
            l_strQueryStr += " and biz_date = ? ";
        }
        
        //�S�j���������������ԋp����B        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryStr;
    }
    
    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * �擾�����ɃZ�b�g����f�[�^�R���e�i�𐶐�����B<BR>
     * <BR>
     * �P�j��̃��X�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�s��ID<BR>
     * <BR>
     * �Q�|�P�j�s��I�u�W�F�N�g�̎擾<BR>
     * <BR>
     *    �g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     *    �s��R�[�h�F ����.�ꗗ����.�s��R�[�h<BR>
     * <BR>
     * �Q�|�Q�j�s��ID�̎擾<BR>
     * <BR>
     *    �s��.�s��ID���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �R�j������<BR>
     * <BR>
     * �R�|�P�j����.�ꗗ����.�������i���j != null �̏ꍇ<BR>
     * <BR>
     *    ����.�ꗗ����.�������i���j�̓��t�����iYYYYMMDD�j�̕�����<BR>
     *    ����.�ꗗ����.�������i���j�̓��t�����iYYYYMMDD�j�̕�����<BR>
     * <BR>
     *    ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�|�Q�j����.�ꗗ����.�������i���j == null �̏ꍇ<BR>
     * <BR>
     *     �V�X�e���^�C���X�^���v�̓��t�����iYYYYMMDD�j�̕�����<BR>
     * <BR>
     *    ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �S�j���X�g����z����擾���A�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_listCond - (�ꗗ����)<BR>
     * �O���������������`�[�ꗗ�����I�u�W�F�N�g<BR>
     * 
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 42A01ADF01A5
     */
    protected Object[] createQueryContainer(String l_strInstitutionCode, WEB3AdminFeqOrderVoucherListCondUnit l_listCond) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryContainer(String, WEB3AdminFeqOrderVoucherListCondUnit) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            if (l_listCond == null)
        
            {
                log.debug("�ꗗ���������w��(null)�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "�ꗗ���������w��(null)�ł��B");
            }
            
            //�P�j��̃��X�g�𐶐�����B
            List l_lisObjs = new ArrayList();
            
            //�Q�|�P�j�s��I�u�W�F�N�g�̎擾
            //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
    
            WEB3GentradeFinObjectManager l_finObjManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            if (l_finObjManager == null)
            {
                log.debug("�g�����Z�I�u�W�F�N�g�}�l�[�W�������݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "�g�����Z�I�u�W�F�N�g�}�l�[�W�������݂��Ȃ��B");
            }
            
            Market l_market = l_finObjManager.getMarket(l_strInstitutionCode, l_listCond.marketCode);//NotFoundException
            
            if (l_market == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "�s�ꂪ���݂��Ȃ��B");
            }
            //�Q�|�Q�j�s��ID�̎擾
            l_lisObjs.add(new Long(l_market.getMarketId()));
            
            //�R�j������
            //�R�|�P�j����.�ꗗ����.�������i���j != null �̏ꍇ
            //����.�ꗗ����.�������i���j�̓��t�����iYYYYMMDD�j�̕�����
            //����.�ꗗ����.�������i���j�̓��t�����iYYYYMMDD�j�̕�����
            if (l_listCond.orderBizDateFrom != null)
            {
                l_lisObjs.add(WEB3DateUtility.formatDate(
                    l_listCond.orderBizDateFrom, 
                    "yyyyMMdd"));
                l_lisObjs.add(WEB3DateUtility.formatDate(
                    l_listCond.orderBizDateTo, 
                    "yyyyMMdd"));
            }
            //�R�|�Q�j����.�ꗗ����.�������i���j == null �̏ꍇ
            //�V�X�e���^�C���X�^���v�̓��t�����iYYYYMMDD�j�̕�����
            else
            {
                l_lisObjs.add(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"));
            }
            
            //�S�j���X�g����z����擾���A�ԋp����B            
            Object[]  l_objs = new Object[l_lisObjs.size()];
            l_lisObjs.toArray(l_objs);           
            
            log.exiting(STR_METHOD_NAME);
            
            return l_objs;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }    

    /**
     * �����̃L�[���ɊY������I�u�W�F�N�g��������HashMap���ԋp����B<BR>
     * �I�u�W�F�N�g��������HashMap�ɑ��݂��Ȃ��ꍇ��<BR>
     * DB����V�K�Ƀ��R�[�h���擾���A������HashMap�ɒǉ�������A<BR>
     * �ԋp����B<BR>
     *<BR>
     * �P�j�@@�I�u�W�F�N�g�̌���<BR>
     * �@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ.get()���\�b�h���R�[������B<BR>
     *<BR>
     * �@@[get()�Ɏw�肷�����]<BR>
     * �@@�@@key�F�@@�p�����[�^.�L�[���<BR>
     *<BR>
     * �@@���ʂ��擾�ł����ꍇ�́Aget()�̖߂�l��ԋp����B<BR>
     *<BR>
     * �Q�j�@@�P�j�ɂ�null���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�ȉ��̎菇�ɂ�DB���烌�R�[�h���擾���A�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�Q�|�P�j�@@�p�����[�^.�N���X���ɂ�菈���𕪊򂷂�B<BR>
     * �@@�@@���e������HashMap.put()���\�b�h�̈����Fkey�́A<BR>
     * �@@�@@�@@String�Őݒ肷�邱�ƁB<BR>
     * �@@�@@���ďo���ŃJ�X�^�}�C�Y�������\�b�h���g�p����ׁA<BR>
     * �@@�@@�@@�I�u�W�F�N�g��WEB3�`�̌^�ō쐬�^�ǉ����邱�ƁB<BR>
     * �@@�@@�@@�ixTrade�W���I�u�W�F�N�g�ł͂Ȃ��j<BR>
     * �@@�@@�@@�܂��A��������̔��ʂ�WEB3�`�̌^�ōs�����ƁB<BR>
     * �@@�@@�@@��j�@@�~�@@branch.class<BR>
     * �@@�@@�@@�@@�@@�@@���@@WEB3GentradeBranch.class<BR>
     *<BR>
     * �@@�@@// ���X<BR>
     * �@@�@@[�p�����[�^.�N���X��� == ���X.class�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�����P��Row.���XID�ɊY�����镔�X�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B<BR>
     *<BR>
     * �@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.���XID<BR>
     * �@@�@@�@@�@@value�F�@@�擾�������X�I�u�W�F�N�g<BR>
     *<BR>
     * �@@�@@// �ڋq<BR>
     * �@@�@@[�p�����[�^.�N���X��� == �ڋq.class�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�����P��Row.����ID�ɊY������ڋq�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B<BR>
     *<BR>
     * �@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.����ID<BR>
     * �@@�@@�@@�@@value�F�@@�擾�����ڋq�I�u�W�F�N�g<BR>
     *<BR>
     * �@@�@@// ����<BR>
     * �@@�@@[�p�����[�^.�N���X��� == ����.class�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�����P��Row.�����ID�ɊY������ڋq�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B<BR>
     *<BR>
     * �@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.�����ID<BR>
     * �@@�@@�@@�@@value�F�@@�擾�������҃I�u�W�F�N�g<BR>
     *<BR>
     * �@@�@@// �O������<BR>
     * �@@�@@[�p�����[�^.�N���X��� == �O������.class�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�����P��Row.����ID�ɊY������O�������I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B<BR>
     *<BR>
     * �@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.����ID<BR>
     * �@@�@@�@@�@@value�F�@@�擾�����O�������I�u�W�F�N�g<BR>
     *<BR>
     * �@@�@@// �s��<BR>
     * �@@�@@[�p�����[�^.�N���X��� == �s��.class�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�����P��Row.�s��ID�ɊY������s��I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B<BR>
     *<BR>
     * �@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.�s��ID<BR>
     * �@@�@@�@@�@@value�F�@@�擾�����s��I�u�W�F�N�g<BR>
     *<BR>
     * �@@�@@// �ʉ�<BR>
     * �@@�@@[�p�����[�^.�N���X��� == �i���ʁj�ʉ�.class�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.�����P��Row.�،���ЃR�[�h�A�ʉ݃R�[�h�������Ƃ��āA<BR>
     * �@@�@@�@@�i���ʁj�ʉ݃I�u�W�F�N�g�𐶐����A<BR>
     * �@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B<BR>
     *<BR>
     * �@@�@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.�،���ЃR�[�h + �ʉ݃R�[�h<BR>
     * �@@�@@�@@�@@value�F�@@�擾�����i���ʁj�ʉ݃I�u�W�F�N�g<BR>
     *<BR>
     * �@@�@@��L�����ɂČ������ʂ��擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�u�Y���f�[�^�Ȃ��v�̃V�X�e���G���[���X���[����B<BR>
     *<BR>
     * �R�j�@@�Q�j�ɂĎ擾�����I�u�W�F�N�g��ԋp����B<BR>
     * 
     * @@param l_strKeyInfo - (�L�[���)<BR>
     * HashMap����I�u�W�F�N�g���擾����ׂ̃L�[��� <BR>
     * <BR>
     * Obj�@@�@@�@@�@@Key <BR>
     * ----�@@�@@�@@---- <BR>
     * ���X�@@�ˁ@@���XID <BR>
     * �ڋq�@@�ˁ@@����ID <BR>
     * ���ҁ@@�ˁ@@�����ID <BR>
     * �����@@�ˁ@@����ID <BR>
     * �s��@@�ˁ@@�s��ID <BR>
     * �ʉ݁@@�ˁ@@�،���ЃR�[�h + �ʉ݃R�[�h<BR>
     * 
     * @@param l_hmObjectList - (�擾�σI�u�W�F�N�g�ꗗ)<BR>
     * DB����擾�ς̃I�u�W�F�N�g�ꗗ<BR>
     * 
     * @@param l_classInfo - (�N���X���)<BR>
     * �擾�σI�u�W�F�N�g�ꗗ�Ɋi�[����Ă���i�i�[����\��j�� <BR>
     * �I�u�W�F�N�g�̃N���X���iObject.class�j<BR>
     * 
     * @@param l_feqOrderUnitRow - (�����P��Row)<BR>
     * �O�������P��Row�I�u�W�F�N�g
     * @@return Object
     * @@throws WEB3BaseException 
     */
    protected Object getObject(
        String l_strKeyInfo, HashMap l_hmObjectList,
        Class l_classInfo, FeqOrderUnitRow l_feqOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getObject(String, HashMap, Class, FeqOrderUnitRow) ";
        log.entering(STR_METHOD_NAME);

        if (l_hmObjectList == null || l_feqOrderUnitRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�p�����[�^��null�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^��null�ł��B");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3FeqProductManager l_web3FeqProductManager =
            (WEB3FeqProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();

        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //�P�j�@@�I�u�W�F�N�g�̌���
        // �p�����[�^.�擾�σI�u�W�F�N�g�ꗗ.get()���\�b�h���R�[������B
        // [get()�Ɏw�肷�����]
        // �@@key�F�@@�p�����[�^.�L�[���
        // ���ʂ��擾�ł����ꍇ�́Aget()�̖߂�l��ԋp����B
        Object l_objRutern = null;
        l_objRutern = l_hmObjectList.get(l_strKeyInfo);
        if (l_objRutern != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_objRutern;
        }
        else
        {
            //�Q�j�@@�P�j�ɂ�null���ԋp���ꂽ�ꍇ�A
            //�@@�ȉ��̎菇�ɂ�DB���烌�R�[�h���擾���A�I�u�W�F�N�g�𐶐�����B
            //�@@�Q�|�P�j�@@�p�����[�^.�N���X���ɂ�菈���𕪊򂷂�B
            //�@@�@@���e������HashMap.put()���\�b�h�̈����Fkey�́A
            //�@@�@@�@@String�Őݒ肷�邱�ƁB
            //�@@�@@���ďo���ŃJ�X�^�}�C�Y�������\�b�h���g�p����ׁA
            //�@@�@@�@@�I�u�W�F�N�g��WEB3�`�̌^�ō쐬�^�ǉ����邱�ƁB
            //�@@�@@�@@�ixTrade�W���I�u�W�F�N�g�ł͂Ȃ��j
            //�@@�@@�@@�܂��A��������̔��ʂ�WEB3�`�̌^�ōs�����ƁB
            //�@@�@@�@@��j�@@�~�@@branch.class
            //�@@�@@�@@�@@�@@�@@���@@WEB3GentradeBranch.class
            //�@@�@@���X
            //�@@�@@[�p�����[�^.�N���X��� == ���X.class�̏ꍇ]
            //�@@�@@�@@�p�����[�^.�����P��Row.���XID�ɊY�����镔�X�I�u�W�F�N�g���擾���A
            //�@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B
            //�@@�@@�@@[put()�Ɏw�肷�����]
            //�@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.���XID
            //�@@�@@�@@�@@value�F�@@�擾�������X�I�u�W�F�N�g
            try
            {
                if (l_classInfo == WEB3GentradeBranch.class)
                {
                    WEB3GentradeBranch l_branch =
                        (WEB3GentradeBranch)l_gentradeAccountManager.getBranch(
                            l_feqOrderUnitRow.getBranchId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getBranchId() + "", l_branch);
                }

                //�@@�@@�ڋq
                //�@@�@@[�p�����[�^.�N���X��� == �ڋq.class�̏ꍇ]
                //�@@�@@�@@�p�����[�^.�����P��Row.����ID�ɊY������ڋq�I�u�W�F�N�g���擾���A
                //�@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B
                //�@@�@@�@@[put()�Ɏw�肷�����]
                //�@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.����ID
                //�@@�@@�@@�@@value�F�@@�擾�����ڋq�I�u�W�F�N�g
                else if (l_classInfo == WEB3GentradeMainAccount.class)
                {
                    WEB3GentradeMainAccount l_mainAccount =
                        (WEB3GentradeMainAccount)l_gentradeAccountManager.getMainAccount(
                            l_feqOrderUnitRow.getAccountId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getAccountId() + "", l_mainAccount);
                }

                //�@@�@@����
                //�@@�@@[�p�����[�^.�N���X��� == ����.class�̏ꍇ]
                //�@@�@@�@@�p�����[�^.�����P��Row.�����ID�ɊY������ڋq�I�u�W�F�N�g���擾���A
                //�@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B
                //�@@�@@�@@[put()�Ɏw�肷�����]
                //�@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.�����ID
                //�@@�@@�@@�@@value�F�@@�擾�������҃I�u�W�F�N�g
                else if (l_classInfo == WEB3GentradeTrader.class)
                {
                    WEB3GentradeTrader l_trader =
                        (WEB3GentradeTrader)l_finObjectManager.getTrader(
                            l_feqOrderUnitRow.getTraderId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getTraderId() + "", l_trader);
                }

                //�@@�@@�O������
                //�@@�@@[�p�����[�^.�N���X��� == �O������.class�̏ꍇ]
                //�@@�@@�@@�p�����[�^.�����P��Row.����ID�ɊY������O�������I�u�W�F�N�g���擾���A
                //�@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B
                //�@@�@@�@@[put()�Ɏw�肷�����]
                //�@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.����ID
                //�@@�@@�@@�@@value�F�@@�擾�����O�������I�u�W�F�N�g
                else if (l_classInfo == WEB3FeqProduct.class)
                {
                    WEB3FeqProduct l_product =
                        (WEB3FeqProduct)l_web3FeqProductManager.getFeqProduct(
                            l_feqOrderUnitRow.getProductId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getProductId() + "", l_product);
                }

                //   �s��
                //�@@�@@[�p�����[�^.�N���X��� == �s��.class�̏ꍇ]
                //�@@�@@�@@�p�����[�^.�����P��Row.�s��ID�ɊY������s��I�u�W�F�N�g���擾���A
                //�@@�@@�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B
                //�@@�@@�@@[put()�Ɏw�肷�����]
                //�@@�@@�@@�@@key�F�@@�p�����[�^.�����P��Row.�s��ID
                //�@@�@@�@@�@@value�F�@@�擾�����s��I�u�W�F�N�g
                else if (l_classInfo == WEB3GentradeMarket.class)
                {
                    WEB3GentradeMarket l_market =
                        (WEB3GentradeMarket)l_finObjectManager.getMarket(
                            l_feqOrderUnitRow.getMarketId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getMarketId() + "", l_market);
                }

                //�ʉ�
                //[�p�����[�^.�N���X��� == �i���ʁj�ʉ�.class�̏ꍇ]
                //�@@�p�����[�^.�����P��Row.�،���ЃR�[�h�A�ʉ݃R�[�h�������Ƃ��āA
                //�@@�i���ʁj�ʉ݃I�u�W�F�N�g�𐶐����A
                //�@@�p�����[�^.�擾�σI�u�W�F�N�g�ꗗ�ɒǉ��iput�j����B
                //�@@[put()�Ɏw�肷�����]
                //�@@�@@key�F�@@�p�����[�^.�����P��Row.�،���ЃR�[�h + �ʉ݃R�[�h
                //�@@�@@value�F�@@�擾�����i���ʁj�ʉ݃I�u�W�F�N�g
                else if (l_classInfo == WEB3GentradeCurrency.class)
                {
                    WEB3GentradeCurrency l_currency =
                        WEB3GentradeCurrency.genCurrency(
                            l_feqOrderUnitRow.getInstitutionCode(),
                            l_feqOrderUnitRow.getCurrencyCode());
                    l_hmObjectList.put(
                        l_feqOrderUnitRow.getInstitutionCode() + l_feqOrderUnitRow.getCurrencyCode(),
                        l_currency);
                }

                //��L�����ɂČ������ʂ��擾�ł��Ȃ������ꍇ�A
                //�u�Y���f�[�^�Ȃ��v�̃V�X�e���G���[���X���[����B
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("�Y���f�[�^�Ȃ��B");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�Y���f�[�^�Ȃ��B");
                }
            }
            catch(NotFoundException l_ex)
            {
                log.error("�Y���f�[�^�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�R�j�@@�Q�j�ɂĎ擾�����I�u�W�F�N�g��ԋp����B
            l_objRutern = l_hmObjectList.get(l_strKeyInfo);
            log.exiting(STR_METHOD_NAME);
            return l_objRutern;
        }
    }
}
@
