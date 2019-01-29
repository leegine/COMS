head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҊO�������o�����̓T�[�r�XImpl(WEB3AdminFeqExecutionInputServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/18 �Ջ`�g(���u) �V�K�쐬
                    2005/08/01 ��O��(���u) ���r���[
 Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
 Revesion History : 2008/02/02 �đo�g(���u) ���f��No.396
 Revesion History : 2009/08/03 ���@@�g(���u) ���f��No.509
 */

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqAmountCalcResultFactor;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductTypeOrderSubmitterPersistenceManager;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.message.WEB3AdminFeqExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionInputResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionSearchRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionSearchResponse;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqExecuteUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionInputService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO�������o�����̓T�[�r�XImpl) <BR>
 * �Ǘ��ҊO�������o�����̓T�[�r�X�����N���X <BR>
 * 
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionInputServiceImpl implements WEB3AdminFeqExecutionInputService
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionInputServiceImpl.class);

    /**
     * @@roseuid 42CE39F100CB
     */
    public WEB3AdminFeqExecutionInputServiceImpl()
    {

    }

    /**
     * �O�������o�����͏��������{����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B <BR>
     * <BR>
     * �|get�������() <BR>
     * �|get���͉��() <BR>
     * �|validate�o��() <BR>
     * �|submit�o��() <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^) <BR>
     *            ���N�G�X�g�f�[�^ <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 428C3F840136
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B
        //�|get�������()
        if (l_request instanceof WEB3AdminFeqExecutionSearchRequest)
        {
            l_response = getSearchScreen((WEB3AdminFeqExecutionSearchRequest) l_request);
        }

        //�|get���͉��()
        else if (l_request instanceof WEB3AdminFeqExecutionInputRequest)
        {
            l_response = getInputScreen((WEB3AdminFeqExecutionInputRequest) l_request);
        }

        //�|validate�o��()
        else if (l_request instanceof WEB3AdminFeqExecutionConfirmRequest)
        {
            l_response = validateExec((WEB3AdminFeqExecutionConfirmRequest) l_request);
        }

        //�|submit�o��()
        else if (l_request instanceof WEB3AdminFeqExecutionCompleteRequest)
        {
            l_response = submitExec((WEB3AdminFeqExecutionCompleteRequest) l_request);
        } 
        else
        {
            log.debug("error in get necessory request");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������) <BR>
     * ������ʕ\�������B <BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�o�����́jget������ʁv �Q�ƁB <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^) <BR>
     *            ���N�G�X�g�f�[�^ <BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B91317022E
     */
    protected WEB3AdminFeqExecutionSearchResponse getSearchScreen(
        WEB3AdminFeqExecutionSearchRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminFeqExecutionSearchRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 �Ǘ��҃I�u�W�F�N�g���擾 getInstanceFrom���O�C�����( )
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.2 �Ǘ��҂̌����`�F�b�N���s��
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        //1.3 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqExecutionSearchResponse l_response = 
            (WEB3AdminFeqExecutionSearchResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��) <BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B <BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�o�����́jget���͉�ʁv �Q�ƁB <BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * (��)�o�����́v((��)�o�����́jget���͉��) <BR>
     * : 1.6 �������̏ꍇ�igetOrderStatus() ==�h1:��t�ρi�V�K�����j�h�j�A <BR>
     * ��O���X���[���� <BR>
     * �������̏ꍇ�igetOrderStatus() == �h1:��t�ρi�V�K�����j�h�j�A <BR>
     * ��O���X���[���� <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02161 <BR>
     * ========================================================== <BR>
     * ========================================================== <BR>
     * �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * (��)�o�����́v((��)�o�����́jget���͉��) <BR>
     * : 1.8 �o���I����iis�o���I��() == true�j�A��O���X���[����B <BR>
     * �o���I����iis�o���I��() == true�j�A��O���X���[����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02162 <BR>
     * ========================================================== <BR>
     * ========================================================== <BR>
     * �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * (��)�o�����́v((��)�o�����́jget���͉��) <BR>
     * : 1.10 (*) HOST�����̏ꍇ�iisHOST����() == true�j<BR>
     * �A��O���X���[����B <BR>
     * HOST�����̏ꍇ�iisHOST����() == true�j�A��O���X���[����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02163 <BR>
     * ========================================================== <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^) <BR>
     *            ���N�G�X�g�f�[�^ <BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 428C3F840221
     */
    protected WEB3AdminFeqExecutionInputResponse getInputScreen(
        WEB3AdminFeqExecutionInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminFeqExecutionInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N���� validate()
        l_request.validate();

        //1.2 �Ǘ��҃I�u�W�F�N�g���擾 getInstanceFrom���O�C�����( )
         WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
         //1.3 �Ǘ��҂̌����`�F�b�N���s��
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);

        //get�،���ЃR�[�h
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //get�^�p�R�[�h(�،���ЃR�[�h : String, �^�p�R�[�h : String)
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //�^�p�R�[�h�F���N�G�X�g�f�[�^.�^�p�R�[�h
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);

        //1.4 �^�p�R�[�h�ɊY�����钍���P�ʂ��擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingModule.getOrderManager();
        FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
       
        //[get�L�������P��By�^�p�R�[�h()�Ɏw�肷�����]
        // �������F ���N�G�X�g�f�[�^.������
        // �������͂̏ꍇ�A���������iTradingSystem.getSystemTimestamp()�j�̓��t�����B
        // �^�p�R�[�h�F get�^�p�R�[�h�i�j�̖߂�l
        Date l_datBizDate = null;
        if (l_request.orderBizDate == null)
        {
            l_datBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            log.debug("l_request.orderBizDate == null, so l_datBizdat = " + l_datBizDate);
        } 
        else
        {
            l_datBizDate = l_request.orderBizDate;
        }
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit) l_orderManager.getValidOrderUnitByOrderEmpCode(
                l_datBizDate,
                l_strEmpCode);
        
        if (l_feqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�O�����������P�ʂ����݂��Ȃ��B");
        }
        // �Ώۃf�[�^��������t����F�؂��s���Ă��Ȃ��ꍇ�@@�����ΏۊO
        FeqOrderUnitImpl l_feqOrderUnitI = l_feqOrderUnit;
        boolean l_confirmedPrice = l_feqOrderUnitI.isConfirmedPriceMarketOrder();
        if (l_confirmedPrice)
        {
            log.debug("�Y�����钍��ID�f�[�^�͑ΏۊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02143,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.5 ������Ԃ��擾���� getOrderStatus()
        //1.6 �������̏ꍇ�igetOrderStatus() == �h1:��t�ρi�V�K�����j�h�j����
        //    �������̏ꍇ�igetOrderStatus() == �h2:�������i�V�K�����j�h�j�A��O���X���[����
        if (OrderStatusEnum.ACCEPTED.equals(l_feqOrderUnit.getOrderStatus()) ||
            OrderStatusEnum.ORDERING.equals(l_feqOrderUnit.getOrderStatus()))
        {
            String l_strMessage = "������Ԃ��擾��O";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02161, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.7 �o���I���ォ�𔻒肷��
        //1.8 �o���I����iis�o���I��() == true�j�A��O���X���[����
        if (l_feqOrderUnit.isExecEnd())
        {
            String l_strMessage = "�o���I��";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02162, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.9 HOST�������𔻒肷��
        //1.10 HOST�����̏ꍇ�iisHOST����() == true�j�A��O���X���[����
        if (l_feqOrderUnit.isHOSTOrder())
        {
            String l_strMessage = "HOST����";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02163, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.11 �O�����������I�u�W�F�N�g���擾����
        WEB3FeqProduct l_web3FeqProduct = 
            (WEB3FeqProduct) l_feqOrderUnit.getProduct();
            
        if (l_web3FeqProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�O���������������݂��Ȃ��B");
        }        

        //1.12 �ʉ݃I�u�W�F�N�g���擾����
        WEB3GentradeCurrency l_currency = l_web3FeqProduct.getCurrency();

        //1.13 ���בփ��[�g���擾����
        //[get�בփ��[�g()�Ɏw�肷�����]
        //is���t�F
        //�����P��.getSide() == �h���h�̏ꍇtrue
        //�ȊO�Afalse
        //is���v�Z�F true
        //���͈בփ��[�g�F 0
        boolean l_blnIsBuy = false;
        if (SideEnum.BUY.equals(l_feqOrderUnit.getSide()))
        {
            l_blnIsBuy = true;
            log.debug("�����P��.getSide() == �h���h�̏ꍇtrue");
        }
        
        double l_dblFxRate = l_currency.getExchangeRate(
            l_blnIsBuy, 
            true, 
            0);

        //1.4 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqExecutionInputResponse l_response = 
            (WEB3AdminFeqExecutionInputResponse) l_request.createResponse();
        
        FeqOrderUnitRow l_feqOrderUnitRow = 
            (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        if (l_feqOrderUnitRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����P��Row�����݂��Ȃ��B");
        }
            
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = 
                (WEB3GentradeMainAccount) l_finApp.getAccountManager().getMainAccount(
                    l_feqOrderUnitRow.getAccountId());
        } 
        catch (NotFoundException l_ex)
        {
            log.error("getMainAccount not found");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "getMainAccount not found", 
                l_ex);
        }
        
        //(*)�v���p�e�B�Z�b�g
        //���ʃR�[�h�F �����P��.���ʃR�[�h
        l_response.requestNumber = l_feqOrderUnitRow.getOrderRequestNumber();

        //�����h�c�F �����P��.�����h�c
        l_response.orderId = Long.toString(l_feqOrderUnitRow.getOrderId());

        //�ڋq�R�[�h�F �����P��.getAccountId()�ɊY������ڋq.get�\���ڋq�R�[�h()�B
        l_response.accountCode = l_mainAccount.getDisplayAccountCode();

        //��������敪�F �����P��.�ŋ敪 == �h��ʁh�̏ꍇ�h��ʁh�A�ȊO�h����h�B
        l_response.taxType = 
            TaxTypeEnum.NORMAL.equals(l_feqOrderUnitRow.getTaxType()) ? 
                WEB3TaxTypeDivDef.NORMAL : WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE;

        //�`�[�ԍ��F �����P��.�`�[�m���D
        l_response.orderNumber = l_feqOrderUnitRow.getVoucherNo();

        //�������ԁF �����P��.�󒍓���
        l_response.orderDate = l_feqOrderUnitRow.getReceivedDateTime();
        
        //�������F �����P��.������
        l_response.orderBizDate = 
            WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        //�s��R�[�h�F �����P��.�s��h�c�ɊY������s��.getMarketCode()�B        
        if (!l_feqOrderUnitRow.getMarketIdIsNull())
        {
            long l_lngMarketId = l_feqOrderUnitRow.getMarketId();
            
            try
            {
                l_response.marketCode = 
                    l_finObjectManager.getMarket(l_lngMarketId).getMarketCode();
            } 
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "Market not found";
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_strMessage, 
                    l_ex);
            }
        }
        else
        {
            l_response.marketCode = null;                                
        }        

        //�����R�[�h�F �O����������.getProductCode()
        l_response.productCode = l_web3FeqProduct.getProductCode();

        //�������F �O����������.get�\��������()
        l_response.productName = l_web3FeqProduct.getDisplayProductName();

        //�����敪�F �i�����P��.getSide() == SideEnum.���j�̏ꍇ�h���t�h�C�ȊO�h���t�h�B
        l_response.dealingType = 
            SideEnum.BUY.equals(l_feqOrderUnit.getSide()) ? 
                Integer.toString(SideEnum.BUY.intValue()) : Integer.toString(SideEnum.SELL.intValue());

        //�������ʁF �����P��. ��������
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getQuantity());

        //�����P���F �����P��.�w�l
        l_response.orderPrice = WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getLimitPrice());

        //���ϋ敪�F �����P��.���ϋ敪
        l_response.settleDiv = l_feqOrderUnitRow.getSettleDiv();

        //���בփ��[�g�F get�ʉ�().get�בփ��[�g()        
        l_response.execExchangeRate = 
            WEB3StringTypeUtility.formatNumber(l_dblFxRate);
            
        //WEB3-XBFEQ-A-UT-0141 
        //���n�����R�[�h           
        ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
        if (l_productMgr == null)
        {
            log.debug("ProductManager�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "ProductManager�����݂��Ȃ��B");
        }
        
        try
        {
            WEB3FeqProduct l_product = 
                (WEB3FeqProduct)l_productMgr.getProduct(l_feqOrderUnitRow.getProductId());//NotFoundException
                
            if (l_product == null)
            {
                log.debug("���������݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "���������݂��Ȃ��B");
            }
            
            l_response.localProductCode = l_product.getOffshoreProductCode();
        }
        catch (NotFoundException l_ex) 
        {
            log.debug("���������݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "���������݂��Ȃ��B");
        }                

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�o��) <BR>
     * �o�����͊m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�o�����́jvalidate�o���v �Q�ƁB <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^) <BR>
     *            ���N�G�X�g�f�[�^ <BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 428C3F8402EC
     */
    protected WEB3AdminFeqExecutionConfirmResponse validateExec(
        WEB3AdminFeqExecutionConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExec(WEB3AdminFeqExecutionConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accManage = l_finApp.getAccountManager();

        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N���� validate()
        l_request.validate();

        //1.2 �Ǘ��҂̌����`�F�b�N���s��
        //[validate����()�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F �@@�\�J�e�S���R�[�h.�h�O���i�������Ǘ��j�h
        // is�X�V�F true
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        //1.3 �����P�ʃI�u�W�F�N�g���擾����
        //[get�����P��ByOrderId()�Ɏw�肷�����]
        // �����h�c�F ���N�G�X�g�f�[�^.�����h�c
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit) l_orderManager.getOrderUnitByOrderId(
                Long.parseLong(l_request.orderId));

        //1.4 �s��R�[�h���Z�b�g����
        //[reset�s��R�[�h()�Ɏw�肷�����]
        // �s��R�[�h�F �����P��.get�s��().getMarketCode()
        WEB3GentradeTradingTimeManagement.resetMarketCode(
            l_feqOrderUnit.getMarket().getMarketCode());

        //1.5�i�����͂̂���ꍇ�̂݁j
        //�����̃`�F�b�N���s���B
        //[validate����()�Ɏw�肷�����]
        //�����P�ʁF get�����P��ByOrderId()
        //�����F ���N�G�X�g�f�[�^.����
        if (l_request.executionDate != null)
        {
            log.debug("1.5�i�����͂̂���ꍇ�̂݁j�����̃`�F�b�N");
            
            l_orderManager.validateExecutionDate(
                l_feqOrderUnit, 
                l_request.executionDate);
        }

        //1.6 ��萔�ʂ̃`�F�b�N���s���B
        //[validate��萔��()�Ɏw�肷�����]
        //�����P�ʁF get�����P��ByOrderId()
        //��萔�ʁF ���N�G�X�g�f�[�^.�o�����ꗗ.��萔�ʂ̍��v�l�isum�j
        double l_dblExecQuantityTotal = 0;
        
        int l_intCnt = 0;
        
        if (l_request.executeList != null && l_request.executeList.length > 0)
        {
            l_intCnt = l_request.executeList.length;
        }
        
        WEB3FeqExecuteUnit[] l_feqExecuteUnits = l_request.executeList;
        for (int i = 0; i < l_intCnt; i++)
        {
            l_dblExecQuantityTotal = 
                l_dblExecQuantityTotal + 
                Double.parseDouble(l_feqExecuteUnits[i].execQuantity);
        }
        
        log.debug("���N�G�X�g�f�[�^.�o�����ꗗ.��萔�ʂ̍��v�l�isum�j:" + l_dblExecQuantityTotal);
        l_orderManager.validateExecutedQuantity(
            l_feqOrderUnit, 
            l_dblExecQuantityTotal);

        //1.7 �i�����͂̂���ꍇ�̂݁j
        //���n��n���̃`�F�b�N���s���B
        //[validate���n��n��()�Ɏw�肷�����]
        //�����P�ʁF get�����P��ByOrderId()
        //�����F ���N�G�X�g�f�[�^.���n��n��
        if (l_request.localDeliveryDate != null)
        {
            log.debug("1.7 �i�����͂̂���ꍇ�̂݁j���n��n���̃`�F�b�N");
            
            l_orderManager.validateFDeliveryDate(
                l_feqOrderUnit, 
                l_request.localDeliveryDate);
        }
        
        // (*1)�����������擾
        Date l_datExecTimestamp = GtlUtils.getSystemTimestamp();

        //1.8 ���N�G�X�g�f�[�^.�o�����ꗗ[]�̗v�f����LOOP����
        log.debug("���N�G�X�g�f�[�^.�o�����ꗗ[]�̗v�f��:" + l_intCnt);
        
        for (int i = 0; i < l_intCnt; i++)
        {
            //1.8 ���P���̃`�F�b�N���s���B
            //[validate���P��()�Ɏw�肷�����]
            //�����P�ʁF get�����P��ByOrderId()
            //��萔�ʁF ���N�G�X�g�f�[�^.�o�����ꗗ[index].���P��
            l_orderManager.validateExecutedPrice(
                l_feqOrderUnit, 
                Double.parseDouble(l_feqExecuteUnits[i].execPrice));
                
            // ���N�G�X�g.�o�����ꗗ[index].������ != null�̏ꍇ
            // �i��莞�Ԃ����͂���Ă���ꍇ�j
            // �������̃`�F�b�N���s���B
            // [validate����()�Ɏw�肷�����] 
            // �����P�ʁF�@@�����P�� 
            // �����F�@@���N�G�X�g�f�[�^.�o�����ꗗ[index].������ 
            if (l_feqExecuteUnits[i].executionTimestamp != null)
            {
                l_orderManager.validateExecutionDate(
                    l_feqOrderUnit,
                    l_feqExecuteUnits[i].executionTimestamp);
            }
            // ���N�G�X�g.�o�����ꗗ[index].������ == null�̏ꍇ
            else
            {
                // �擾�����������t(*1)���Z�b�g����B
                l_feqExecuteUnits[i].executionTimestamp = l_datExecTimestamp;
            }
        }

        //1.9 create�o���ڍ׏��ꗗ
        //�����P�ʁC�o�����ꗗ�����v�Z���s���B
        //�v�Z���ʂɂĊO���������ڍׁi�Ǘ��ҁj�I�u�W�F�N�g�̔z��𐶐�����B
        //
        //[create�o���ڍ׏��ꗗ()�Ɏw�肷�����]
        //�����P�ʍs�F �����P��.getDataSourceObject()
        //�o�����ꗗ�F ���N�G�X�g�f�[�^.�o�����ꗗ
        //�����F ���N�G�X�g�f�[�^.����
        //���i���� == null�j�̏ꍇ�A����
        //���בփ��[�g�F ���N�G�X�g�f�[�^.���בփ��[�g
        Date l_datExecDate = l_request.executionDate;
        if (l_datExecDate == null)
        {
            l_datExecDate = GtlUtils.getSystemTimestamp();
            
            log.debug("�i���� == null�j," + l_datExecDate);
        }
        
        WEB3FeqExecDetailInfoUnit[] l_web3FeqExecDetailInfoUnits = 
            createExecDetailInfoList(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject(), 
                l_request.executeList,
                l_datExecDate, 
                Double.parseDouble(l_request.execExchangeRate));

        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizLogicProvider = 
            (WEB3FeqBizLogicProvider) l_tradingModule.getBizLogicProvider();
        WEB3GentradeSubAccount l_web3GentradeSubAccount = null;
        try
        {
            l_web3GentradeSubAccount = 
                (WEB3GentradeSubAccount) l_accManage.getSubAccount(
                    l_feqOrderUnit.getAccountId(), 
                    l_feqOrderUnit.getSubAccountId());
        } 
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "not found sub account";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }

        //1.10 ���t�̏ꍇ�i�����P��.getSide() == SideEnum."��"�j�A���n���v�^���n�v�ł��v�Z����
        double l_deliveryPriceTotal = 0;
        double l_execQuantityTotal = 0;
        double l_dblCapitalProfitLoss = 0;
        double l_dblCapitalGainTax = 0;
        if (SideEnum.SELL.equals(l_feqOrderUnit.getSide()))
        {
            log.debug("1.10 ���t�̏ꍇ�i�����P��.getSide() == SideEnum.���j");
            
            //1.10.1 ���n���v���v�Z����B
            //[calc���n���v()�Ɏw�肷�����]
            //��������i�~�݁j�F ��n����i�~�݁j���v��1
            //�������F ��萔�ʐ��ʍ��v��2
            //�����h�c�F �����P��.getProduct().getProductId()
            //�⏕�����F �����P��.getSubAccountId()�ɊY������⏕����
            //�ŋ敪�F �����P��.getTaxType()
            //��1 �o���ڍ׏��i�F�O���������ڍׁi�Ǘ��ҁj�j.��n����̍��v�l�isum�j
            //��2 �o���ڍ׏��i�F�O���������ڍׁi�Ǘ��ҁj�j.��萔�ʂ̍��v�l�isum�j
            int l_intCnt2 = 0;
            if (l_web3FeqExecDetailInfoUnits != null && l_web3FeqExecDetailInfoUnits.length > 0)
            {
                l_intCnt2 = l_web3FeqExecDetailInfoUnits.length;
            }
            
            for (int i = 0; i < l_intCnt2; i++)
            {
                l_deliveryPriceTotal = 
                    l_deliveryPriceTotal + 
                    Double.parseDouble(l_web3FeqExecDetailInfoUnits[i].deliveryPrice);
                l_execQuantityTotal += Double.parseDouble(l_web3FeqExecDetailInfoUnits[i].execQuantity);
            }
            l_dblCapitalProfitLoss = 
                l_bizLogicProvider.calcCapitalProfitLoss(
                    l_deliveryPriceTotal,
                    l_execQuantityTotal, 
                    l_feqOrderUnit.getProduct().getProductId(), 
                    l_web3GentradeSubAccount,
                    l_feqOrderUnit.getTaxType());
            
            log.debug("1.10.1 ���n���v���v�Z����B" + l_dblCapitalProfitLoss);

            //1.10.2 ���n�v�ł��v�Z����B
            //[calc���n�v��()�Ɏw�肷�����]
            //�⏕�����F �����P��.getSubAccountId()�ɊY������⏕����
            //�ŋ敪�F �����P��.getTaxType()
            //��������i�~�݁j�F calc���n���v()
            //����F ���N�G�X�g�f�[�^.�������i���t�����j�̂R�c�Ɠ���
            //���i������ == null�j�̏ꍇ�A�����̂R�c�Ɠ�
            WEB3GentradeBizDate l_web3GentradeBizDate = 
                new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
            if (l_datExecDate != null)
            {
                l_web3GentradeBizDate = 
                    new WEB3GentradeBizDate(new Timestamp(WEB3DateUtility.toDay(l_datExecDate).getTime()));
            }
            Date l_datBaseDate = l_web3GentradeBizDate.roll(3);
            log.debug("���:" + l_datBaseDate);
            
            l_dblCapitalGainTax = 
                l_bizLogicProvider.calcCapitalGainTax(
                    l_web3GentradeSubAccount, 
                    l_feqOrderUnit.getTaxType(), 
                    l_dblCapitalProfitLoss, 
                    WEB3DateUtility.toDay(l_datBaseDate));
            
            log.debug("1.10.2 ���n�v�ł��v�Z����B" + l_dblCapitalGainTax);
        }

        //1.11 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqExecutionConfirmResponse l_response = 
            (WEB3AdminFeqExecutionConfirmResponse) l_request.createResponse();

        //1.12 ���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����
        //�o���ڍ׏��ꗗ�F create�o���ڍ׏��ꗗ()
        l_response.execDetailInfoList = l_web3FeqExecDetailInfoUnits;
 
        //���בփ��[�g�F ���N�G�X�g�f�[�^.���בփ��[�g
        l_response.execExchangeRate = l_request.execExchangeRate;


        //�����F ���N�G�X�g�f�[�^.�����i��null�̏ꍇ�ATradingSystem.getSystemTimestamp()�̓��t�����j
        if (l_request.executionDate != null)
        {
            l_response.executionDate = 
                WEB3DateUtility.toDay(l_request.executionDate);
        } 
        else
        {
            l_response.executionDate = WEB3DateUtility.toDay(
                GtlUtils.getSystemTimestamp());
        }

        //���n��n���F ���N�G�X�g�f�[�^.���n��n���i��null�̏ꍇ�A�����̂R�c�Ɠ���j
        if (l_request.localDeliveryDate == null)
        {
            WEB3GentradeBizDate l_web3GentradeBizDate = 
                new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
            if (l_datExecDate != null)
            {
                l_web3GentradeBizDate = 
                    new WEB3GentradeBizDate(new Timestamp(WEB3DateUtility.toDay(l_datExecDate).getTime()));
            }
            Date l_datBaseDate = l_web3GentradeBizDate.roll(3);
            l_response.localDeliveryDate = l_datBaseDate;
        } 
        else
        {
            l_response.localDeliveryDate = l_request.localDeliveryDate;
        }

        //���n�v�F calc���n���v()
        l_response.passProfit = 
            WEB3StringTypeUtility.formatNumber(l_dblCapitalProfitLoss);

        //���n�v�ŁF calc���n�v��()
        l_response.passProfitTax = 
            WEB3StringTypeUtility.formatNumber(l_dblCapitalGainTax);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�o��) <BR>
     * �o�����͊����������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�o�����́jsubmit�o���v �Q�ƁB <BR>
     * 
     * @@param l_request -
     *            (���N�G�X�g�f�[�^) <BR>
     *            ���N�G�X�g�f�[�^ <BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 428C3F8403A7
     */
    protected WEB3AdminFeqExecutionCompleteResponse submitExec(
        WEB3AdminFeqExecutionCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitExec(WEB3AdminFeqExecutionCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //1.2 �Ǘ��҂̌����`�F�b�N���s���B
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F �@@�\�J�e�S���R�[�h.�h�O���i�������Ǘ��j�h
        //is�X�V�F true
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        //1.3 ����p�X���[�h�̃`�F�b�N���s���B
        //[validate����p�X���[�h()�Ɏw�肷�����]
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_web3Administrator.validateTradingPassword(l_request.password);

        //1.4 �����P�ʃI�u�W�F�N�g���擾����B
        //[get�����P��ByOrderId()�Ɏw�肷�����]
        //�����h�c�F ���N�G�X�g�f�[�^.�����h�c
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager) l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderId(
                    Long.parseLong(l_request.orderId));

        //1.5 �s��R�[�h���Z�b�g����B
        //[reset�s��R�[�h()�Ɏw�肷�����]
        //�s��R�[�h�F �����P��.get�s��().getMarketCode()
        WEB3GentradeTradingTimeManagement.resetMarketCode(
            l_feqOrderUnit.getMarket().getMarketCode());

        //1.6 �����̃`�F�b�N���s���B
        //[validate����()�Ɏw�肷�����]
        //�����P�ʁF get�����P��ByOrderId()
        //�����F ���N�G�X�g�f�[�^.����
        //���i������ == null�j�̏ꍇ�A����
        Date l_datExecDate = l_request.executionDate;
        if (l_datExecDate == null)
        {
            l_datExecDate = GtlUtils.getSystemTimestamp();
        }
        
        log.debug("1.6 �����̃`�F�b�N���s���B����:" + l_datExecDate);
        l_orderManager.validateExecutionDate(
            l_feqOrderUnit, 
            l_datExecDate);

        //1.7 ��萔�ʂ̃`�F�b�N���s���B
        int l_intCnt = 0;
        if (l_request.executeList != null && l_request.executeList.length > 0)
        {
            l_intCnt = l_request.executeList.length;
        }
        double l_dblExecuteQuantitySum = 0;
        
        for (int i = 0; i < l_intCnt; i++)
        {
            l_dblExecuteQuantitySum = 
                l_dblExecuteQuantitySum
                + Double.parseDouble(l_request.executeList[i].execQuantity);
        }
        log.debug("��萔��Sum:" + l_dblExecuteQuantitySum);

        //[validate��萔��()�Ɏw�肷�����]
        //�����P�ʁF get�����P��ByOrderId()
        //��萔�ʁF ���N�G�X�g�f�[�^.�o�����ꗗ.��萔�ʂ̍��v�l�isum�j
        l_orderManager.validateExecutedQuantity(
            l_feqOrderUnit, 
            l_dblExecuteQuantitySum);

        if (l_request.localDeliveryDate != null)
        {
            l_orderManager.validateFDeliveryDate(
                l_feqOrderUnit,
                l_request.localDeliveryDate);
        }

        WEB3FeqPositionManager l_positionManager = 
            (WEB3FeqPositionManager) l_tradingModule.getPositionManager();
        long l_lngOrderUnitId = l_feqOrderUnit.getOrderUnitId();
        
        log.debug("���N�G�X�g�f�[�^.�o�����ꗗ[]�̗v�f����LOOP����:" + l_intCnt);    

        //1.8 ���N�G�X�g�f�[�^.�o�����ꗗ[]�̗v�f����LOOP����
        for (int i = 0; i < l_intCnt; i++)
        {
            //1.8.1 ���P���̃`�F�b�N���s���B
            //[validate���P��()�Ɏw�肷�����]
            //�����P�ʁF get�����P��ByOrderId()
            //��萔�ʁF ���N�G�X�g�f�[�^.�o�����ꗗ[index].���P��
            l_orderManager.validateExecutedPrice(
                l_feqOrderUnit, 
                Double.parseDouble(l_request.executeList[i].execPrice));

            //1.8.2 �������X�V����B
            //�V�[�P���X�}�u�i�����X�V�jupdate�����v
            WEB3FeqOrderAndExecutionUpdateService l_executeUpdateService = 
                (WEB3FeqOrderAndExecutionUpdateService) Services.getService(
                    WEB3FeqOrderAndExecutionUpdateService.class);

            //[update�����()�Ɏw�肷�����]
            //�O���o���ʒm�L���[�F �i���������I�u�W�F�N�g���j
            HostFeqOrderExecNotifyParams l_orderExecNotifyParams = new HostFeqOrderExecNotifyParams();
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
            
            //�@@���O���o���ʒm�L���[Params�𐶐����A�ȉ��̒ʂ�v���p�e�B�ɒl���Z�b�g����B
            //�@@�،���ЃR�[�h�F �����P��.get�،���ЃR�[�h()
            l_orderExecNotifyParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());

            //�@@���X�R�[�h�F �����P��.get���X�R�[�h()
            l_orderExecNotifyParams.setBranchCode(l_feqOrderUnit.getBranchCode());

            //�@@�ڋq�R�[�h�F �����P��.get�����R�[�h()
            l_orderExecNotifyParams.setAccountCode(l_feqOrderUnit.getAccountCode());

            //�@@���ʃR�[�h�F �����P��.���ʃR�[�h
            l_orderExecNotifyParams.setRequestCode(l_feqOrderUnitRow.getOrderRequestNumber());

            //�@@�^�p�R�[�h�F �����P��.�^�p�R�[�h
            l_orderExecNotifyParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());

            //�@@��芔���F ���N�G�X�g�f�[�^.�o�����ꗗ[index].��芔��
            l_orderExecNotifyParams.setExecQuantity(
                Double.parseDouble(l_request.executeList[i].execQuantity));

            //�@@���P���F ���N�G�X�g�f�[�^.�o�����ꗗ[index].���P��
            l_orderExecNotifyParams.setExecPrice(
                Double.parseDouble(l_request.executeList[i].execPrice));

            //�@@�������F ���N�G�X�g�f�[�^.�o�����ꗗ[index].������
            l_orderExecNotifyParams.setExecTimestamp(
                l_request.executeList[i].executionTimestamp);

            //�@@���n��n���F ���N�G�X�g�f�[�^.���n��n��
            l_orderExecNotifyParams.setFDeliveryDate(l_request.localDeliveryDate);
            
            //  �������F �����P�ʁD������
            l_orderExecNotifyParams.setOrderBizDate(WEB3DateUtility.getDate(l_feqOrderUnit.getBizDate(), "yyyyMMdd"));

            //�@@�בփ��[�g�F ���N�G�X�g�f�[�^.���בփ��[�g
            l_orderExecNotifyParams.setFxRate(
                Double.parseDouble(l_request.execExchangeRate));

            l_executeUpdateService.updateExecuteUnit(l_orderExecNotifyParams);
            
            l_positionManager.updateTransaction(
                l_lngOrderUnitId, 
                false);
            
            WEB3FeqOrderUnit l_feqOrderUnitUpdated = null;
            try
            {
                l_feqOrderUnitUpdated = 
                    (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            Date l_datBizDate =  l_request.executeList[i].executionTimestamp;
            l_orderManager.updateEstimatedPrice(
                l_feqOrderUnitUpdated, 
                l_datBizDate);
            
            l_orderManager.executeChangeCancelOrderRejected(
                l_feqOrderUnitUpdated.getOrderUnitId());
        }

        //1.11 �]�͍Čv�Z�����{����B
        //[�]�͍Čv�Z()�Ɏw�肷�����]
        //�⏕�����F get�����P��ByOrderId().get�⏕����()
        WEB3TPTradingPowerReCalcService l_powerReCalcServie = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        l_powerReCalcServie.reCalcTradingPower(l_feqOrderUnit.getSubAccount());

        //1.12 ���X�|���X�f�[�^�𐶐�����
        log.exiting(STR_METHOD_NAME);
        return (WEB3AdminFeqExecutionCompleteResponse) l_request.createResponse();
    }

    /**
     * (create�o���ڍ׏��ꗗ) <BR>
     * �����P�ʁC�o�����ꗗ�����v�Z���s���B <BR>
     * �v�Z���ʂɂĊO���������ڍׁi�Ǘ��ҁj�I�u�W�F�N�g�̔z��𐶐�����B <BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�o�����́jcreate�o���ڍ׏��ꗗ�v �Q�ƁB <BR>
     * 
     * @@param l_orderUnitRow -
     *            (�����P�ʍs) <BR>
     *            �����P�ʍs�I�u�W�F�N�g <BR>
     * @@param l_execInfoList -
     *            (�o�����ꗗ) <BR>
     *            ��ʂœ��͂��ꂽ�O�����������̔z�� <BR>
     * @@param l_datExecDate -
     *            (����) <BR>
     *            ���� <BR>
     * @@param l_dblExecFxRate -
     *            (���בփ��[�g) <BR>
     *            ���בփ��[�g <BR>
     * @@return WEB3FeqExecDetailInfoUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42B2545B000A
     */
    private WEB3FeqExecDetailInfoUnit[] createExecDetailInfoList(
        FeqOrderUnitRow l_orderUnitRow, 
        WEB3FeqExecuteUnit[] l_execInfoList, 
        Date l_datExecDate, 
        double l_dblExecFxRate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createExecDetailInfoList()";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitRow == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            
        WEB3FeqPositionManager l_positionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
            
        WEB3FeqBizLogicProvider l_bizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        WEB3FeqOrderManager l_web3FeqOrderManager = 
            (WEB3FeqOrderManager) l_tradingModule.getOrderManager();            
        
        //1.1 �����̃g�����U�N�V����List���擾����B
        //[get������薾��ForOrderUnit()�Ɏw�肷�����]
        //�����P�ʂh�c�F �����P�ʍs.getOrderUnitId()
        List l_lisFinTransactionParams = null;
        try 
        {
            l_lisFinTransactionParams = l_positionManager.getFinTransactionForOrderUnit(
                l_orderUnitRow.getOrderUnitId());
        } 
        catch (DataException l_ex) 
        {
            String l_strMessage = "�����̃g�����U�N�V����List���擾����";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }
        
        int l_intFinTransactionForOrderUnitCnt = 0;        
        
        if (l_lisFinTransactionParams != null && !l_lisFinTransactionParams.isEmpty())
        {
            l_intFinTransactionForOrderUnitCnt = l_lisFinTransactionParams.size();
        }
        else
        {
            l_lisFinTransactionParams = new ArrayList();
        }
        
        log.debug("�����̃g�����U�N�V����List-Count: " + l_intFinTransactionForOrderUnitCnt);
        
        //1.2 getInstance()
        WEB3FeqProductTypeOrderSubmitterPersistenceManager l_updateManager =            
            (WEB3FeqProductTypeOrderSubmitterPersistenceManager)WEB3FeqProductTypeOrderSubmitterPersistenceManager.getInstance();
        
        //1.3 �o�����ꗗ[]�̗v�f����LOOP����
        int l_intExecInfoListCnt = 0;
        
        if (l_execInfoList != null && l_execInfoList.length > 0)
        {
            l_intExecInfoListCnt = l_execInfoList.length;
        }
        
        log.debug("1.3 �o�����ꗗ[]�̗v�f����LOOP����-Count: " + l_intExecInfoListCnt);
        
        for (int i = 0; i < l_intExecInfoListCnt; i++) 
        {
            //1.3.1 ���I�u�W�F�N�g�𐶐�����B

            //[create���()�Ɏw�肷�����]
            //�����P�ʍs�F �����P�ʍs
            //��萔�ʁF �o�����ꗗ[index].��芔��
            //���P���F �o�����ꗗ[index].���P��
            //�������F�@@�o�����ꗗ[index].������ 
            //���בփ��[�g�F ���בփ��[�g
            WEB3FeqOrderExecution l_web3FeqOrderExecution
                = l_updateManager.createExecution(
                    l_orderUnitRow,
                    Double.parseDouble(l_execInfoList[i].execQuantity),
                    Double.parseDouble(l_execInfoList[i].execPrice),
                    l_execInfoList[i].executionTimestamp,
                    l_dblExecFxRate);
            
            //1.3.2 �g�����U�N�V�����i������薾�ׁj�s�𐶐�����B
            //[create�g�����U�N�V�����s()�Ɏw�肷�����]
            //���F create���()
            //�����P�ʍs�F �����P�ʍs
            FeqFinTransactionParams l_feqFinTransactionParams
                = l_positionManager.createTransactionParams(
                    l_web3FeqOrderExecution,
                    l_orderUnitRow);
            
            //1.3.3 �g�����U�N�V����List�ɐ��������g�����U�N�V������ǉ�����
            l_lisFinTransactionParams.add(l_feqFinTransactionParams);
        }
        
        //1.4 �g�����U�N�V����List�̃T�C�Y���擾����
        int l_intFeqFinTransactionParamsCnt = l_lisFinTransactionParams.size();
        
        log.debug("�g�����U�N�V����List-Count: " + l_intFeqFinTransactionParamsCnt);
        
        //1.5 ����t���[ ��肪�P���̏ꍇ�isize() == 1�j
        WEB3GentradeBizDate l_web3GentradeBizDate = 
            new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
        if (l_datExecDate != null)
        {
            l_web3GentradeBizDate = 
                new WEB3GentradeBizDate(new Timestamp(WEB3DateUtility.toDay(l_datExecDate).getTime()));
        }
        if (l_intFeqFinTransactionParamsCnt == 1) 
        {
            log.debug("1.5 ����t���[ ��肪�P���̏ꍇ�isize() == 1�j");
            
            //1.5.1 �o���ڍ׏��i�F�O���������ڍׁi�Ǘ��ҁj�j�𐶐�����
            WEB3FeqExecDetailInfoUnit l_execDetailInfoUnit = 
                new WEB3FeqExecDetailInfoUnit();
            
            //1.5.2 �o���ڍ׏��i�F�O���������ڍׁi�Ǘ��ҁj�j�Ƀv���p�e�B���Z�b�g����B
            //[create�o���ڍ׏��()�Ɏw�肷�����]
            //�o���ڍ׏��F �i���������I�u�W�F�N�g�j
            //�g�����U�N�V�����i������薾�ׁj�s�F �g�����U�N�V��������List[0]
            //��n���F �i�������i���t�����j�̂R�c�Ɠ���j
            //���ʔԁF �i�����P�ʍs.���ŏI�ʔ� + 1�j
            FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
            String l_strMarketCode = null;
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                long l_lngMarketId = l_orderUnitRow.getMarketId();
            
                try
                {
                    l_strMarketCode = 
                        l_finObjectManager.getMarket(l_lngMarketId).getMarketCode();
                } 
                catch (NotFoundException l_ex)
                {
                    String l_strMessage = "Market not found";
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        l_strMessage, 
                        l_ex);
                }
            }
 
            Date l_datDeliveryDate = l_web3GentradeBizDate.roll(3);
            
            log.debug("������: " + l_datExecDate);
            log.debug("��n��:" + l_datDeliveryDate);                        
            
            createExecDetailInfo(
                l_execDetailInfoUnit,
                (FeqFinTransactionParams) l_lisFinTransactionParams.get(0),
                l_datDeliveryDate,
                l_orderUnitRow.getLastExecutionSerialNo() + 1);
            
            //1.5.3 return
            return new WEB3FeqExecDetailInfoUnit[] {l_execDetailInfoUnit};
        }
        
        //1.6 �O���������z���v�Z���s���B
        FeqFinTransactionParams[] l_feqFinTransactions = 
            new FeqFinTransactionParams[l_intFeqFinTransactionParamsCnt];
            l_lisFinTransactionParams.toArray(l_feqFinTransactions);

        //[calc�O���������z�i���j�Ɏw�肷�����]
        //�g�����U�N�V����List.toArray() 
        WEB3FeqAmountCalcResultFactor l_resultFactor =
            l_bizLogicProvider.calcFeqAmountFactor(l_feqFinTransactions);

        //return result
        List l_lisExecDetailInfoList = new ArrayList();

        int l_intLastExecSerNo = l_orderUnitRow.getLastExecutionSerialNo();
        
        //1.7 ������薾��List�e�v�f����LOOP����
        for (int i = 0; i < l_intFeqFinTransactionParamsCnt; i++) 
        {
            //1.7.1 �O���������z�v�Z���ʂ��擾����B

            //[get�O���������z�v�Z���ʁi���ׁj()�Ɏw�肷�����]
            //index�F index
            WEB3FeqAmountCalcResult l_web3FeqAmountCalcResult
                = l_resultFactor.getFeqAmountCalcResultDetails(i);

            //1.7.2 �o���ڍ׏��i�F�O���������ڍׁi�Ǘ��ҁj�j�𐶐�����
            WEB3FeqExecDetailInfoUnit l_execDetailInfoUnit 
                = new WEB3FeqExecDetailInfoUnit();
            
            //1.7.3 �o���ڍ׏��i�F�O���������ڍׁi�Ǘ��ҁj�j�Ƀv���p�e�B���Z�b�g����B
            //[create�o���ڍ׏��()�Ɏw�肷�����]
            //�o���ڍ׏��F �i���������I�u�W�F�N�g�j
            //�g�����U�N�V�����i������薾�ׁj�s�F�@@�g�����U�N�V��������List[index] 
            //�O���������z�v�Z���ʁF�@@get�O���������z�v�Z���ʁi���ׁj()�̖߂�l 
            
            //��n���F
            //�����̏ꍇ(*1)�F ���(*2).��n��
            //���ʔԁF
            //�����̏ꍇ(*1)�F ���(*2).��菇�ԍ�
            //(*2) �g�����U�N�V��������List[index].���h�c�ɊY��������
            //(*1) �����̏ꍇ�̔���
            //�@@�iindex <= get������薾��ForOrderUnit()�Ŏ擾����List�̗v�f���j�ł���Ί����A�ȊO������B
            Date l_datDeliveryDate = null;
            int l_intExecSerialNo = 0;
            if ( i < l_intFinTransactionForOrderUnitCnt)
            {
                log.debug("�����");
                
                FeqFinTransactionRow l_feqFinTransactionRow = 
                    (FeqFinTransactionRow) l_lisFinTransactionParams.get(i);
                WEB3FeqOrderExecution l_feqOrdreExecution = null;
                try 
                {
                    l_feqOrdreExecution = 
                        (WEB3FeqOrderExecution) l_web3FeqOrderManager.getOrderExecution(
                            l_feqFinTransactionRow.getOrderExecutionId());
                } 
                catch (NotFoundException l_ex) 
                {
                    String l_strMessage = "���h�c�ɊY�������� not found";
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName()
                        + STR_METHOD_NAME, l_strMessage, l_ex);
                }
                
                l_datDeliveryDate = l_feqOrdreExecution.getDeliveryDate();
                l_intExecSerialNo = l_feqOrdreExecution.getExecutionSerialNo();
                
                log.debug("��n��:" + l_datDeliveryDate);
                log.debug("���ʔ�:" + l_intExecSerialNo);
            } 

            //������̏ꍇ�F �i�������i���t�����j�̂R�c�Ɠ���j
            //������̏ꍇ�F �i�����P�ʍs.���ŏI�ʔ� + index + 1
            else 
            {
                log.debug("������");
                
                FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
                String l_strMarketCode = null;
                if (!l_orderUnitRow.getMarketIdIsNull())
                {
                    long l_lngMarketId = l_orderUnitRow.getMarketId();
            
                    try
                    {
                        l_strMarketCode = 
                            l_finObjectManager.getMarket(l_lngMarketId).getMarketCode();
                    } 
                    catch (NotFoundException l_ex)
                    {
                        String l_strMessage = "Market not found";
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                            this.getClass().getName() + "." + STR_METHOD_NAME, 
                            l_strMessage, 
                            l_ex);
                    }
                }
                l_datDeliveryDate = l_web3GentradeBizDate.roll(3);
                l_intExecSerialNo = l_intLastExecSerNo + 1;
                l_intLastExecSerNo ++;
                
                log.debug("��n��:" + l_datDeliveryDate);
                log.debug("���ʔ�:" + l_intExecSerialNo);
            }            

            createExecDetailInfo(
                l_execDetailInfoUnit,
                l_feqFinTransactions[i],
                l_web3FeqAmountCalcResult,
                l_datDeliveryDate,
                l_intExecSerialNo);
            l_lisExecDetailInfoList.add(l_execDetailInfoUnit);
        }

        WEB3FeqExecDetailInfoUnit[] l_execDetailInfoUnits = 
            new WEB3FeqExecDetailInfoUnit[l_lisExecDetailInfoList.size()];
        l_lisExecDetailInfoList.toArray(l_execDetailInfoUnits);

        //temp serialNo[]
        int l_intExecDetailInfoLen = l_execDetailInfoUnits.length;
        int[] serialNo = new int[l_intExecDetailInfoLen];
        for (int i = 0; i < l_intExecDetailInfoLen; i++) 
        {
            serialNo[i] = Integer.parseInt(l_execDetailInfoUnits[i].execNo);
        }

        //�i����v�Z���̏o���ڍ׏��j�Ɓi�����͕��̏o���ڍ׏��j�̊e�v�f��
        //���ԍ��̏�����sort���A�z��ŕԋp����B
        WEB3FeqExecDetailInfoUnit l_web3FeqExecDetailInfoUnit = null;
        int l_intTemp = 0;
        for (int j = 0; j < l_intExecDetailInfoLen - 1; j++) 
        {
            for (int i = 0; i < l_intExecDetailInfoLen - 1 - j; i++) 
            {
                if (serialNo[i] > serialNo[i + 1]) 
                {
                    l_intTemp = serialNo[i + 1];
                    serialNo[i + 1] = serialNo[i];
                    serialNo[i] = l_intTemp;
                    l_web3FeqExecDetailInfoUnit = l_execDetailInfoUnits[i + 1];
                    l_execDetailInfoUnits[i + 1] = l_execDetailInfoUnits[i];
                    l_execDetailInfoUnits[i] = l_web3FeqExecDetailInfoUnit;
                }
            }
        }
        
        //1.8 return
        log.exiting(STR_METHOD_NAME);
        return l_execDetailInfoUnits;
    }
    
    /**
     * (create�o���ڍ׏��) <BR>
     * �o���ڍ׏��Ɉȉ��̒ʂ�l���Z�b�g����B <BR>
     * <BR>
     * ���h�c�F null <BR>
     * ���ԍ��F ���ʔԂ��Apattern="000"�Ńt�H�[�}�b�g����������B <BR>
     * ��������F �g�����U�N�V�����i������薾�ׁj�s.��萔��() <BR>
     * �~�g�����U�N�V�����i������薾�ׁj�s.���P��() <BR>
     * ���n�萔���F �g�����U�N�V�����i������薾�ׁj�s.���n�萔�� <BR>
     * ���n����ŁF �g�����U�N�V�����i������薾�ׁj�s.���n����� <BR>
     * ���̑��R�X�g�P�F �g�����U�N�V�����i������薾�ׁj�s.���̑��R�X�g�P <BR>
     * ���̑��R�X�g�Q�F �g�����U�N�V�����i������薾�ׁj�s.���̑��R�X�g�Q <BR>
     * ���Z����i�~�݁j�F �g�����U�N�V�����i������薾�ׁj�s.���n���Z����i�~�݁j <BR>
     * ���Z����i�O�݁j�F �g�����U�N�V�����i������薾�ׁj�s.���n���Z����i�O�݁j <BR>
     * �����萔���i�~�݁j�F �g�����U�N�V�����i������薾�ׁj�s.�ϑ��萔�� <BR>
     * �����萔���i�O�݁j�F �g�����U�N�V�����i������薾�ׁj�s.�ϑ��萔���i�O�݁j <BR>
     * ����Łi�~�݁j�F �g�����U�N�V�����i������薾�ׁj�s.�ϑ��萔������� <BR>
     * ����Łi�O�݁j�F �g�����U�N�V�����i������薾�ׁj�s.�ϑ��萔������Łi�O�݁j <BR>
     * ��n����F �g�����U�N�V�����i������薾�ׁj�s.��n��� <BR>
     * ��n����i�O�݁j�F �g�����U�N�V�����i������薾�ׁj�s.��n����i�O�݁j <BR>
     * ������n���F ��n�� <BR>
     * ���P���F�@@�g�����U�N�V�����i������薾�ׁj�s.���P��<BR>
     * ��萔�ʁF�@@�g�����U�N�V�����i������薾�ׁj�s.��萔��<BR>
     * �������F�@@�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V������������<BR>
     * <BR>
     * @@param l_execDetailInfo -
     *            (�o���ڍ׏��) <BR>
     *            �o���ڍ׏�񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g <BR>
     * @@param l_transactionParams -
     *            (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     *            �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g <BR>
     * @@param l_datDeliveryDate -
     *            (��n��) <BR>
     *            ��n�� <BR>
     * @@param l_intExecSerialNo -
     *            (���ʔ�) <BR>
     *            ���ʔ� <BR>
     * @@roseuid 42B2786B03BC
     */
    private void createExecDetailInfo(
        WEB3FeqExecDetailInfoUnit l_execDetailInfo,
        FeqFinTransactionParams l_transactionParams, 
        Date l_datDeliveryDate, 
        int l_intExecSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createExecDetailInfo(WEB3FeqExecDetailInfoUnit, " + 
            "FeqFinTransactionParams, Date, int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_execDetailInfo == null || l_transactionParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //���h�c�F null
        l_execDetailInfo.execId = null;
        //���ԍ��F ���ʔԂ��Apattern="000"�Ńt�H�[�}�b�g����������B
        l_execDetailInfo.execNo = WEB3StringTypeUtility.formatNumber(l_intExecSerialNo, 3);

        //��������F �g�����U�N�V�����i������薾�ׁj�s.��萔��()�~�g�����U�N�V�����i������薾�ׁj�s.���P��()
        long l_lngOrderUnitId = l_transactionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager =
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        WEB3FeqProduct l_product = (WEB3FeqProduct)l_orderUnit.getProduct();
        WEB3GentradeCurrency l_currency = l_product.getCurrency();
        int l_intDecimalPlace = l_currency.getScale();
        double l_dblForeignTradePrice =
            l_transactionParams.getQuantity() * l_transactionParams.getPrice();
        BigDecimal l_bdForeignTradePrice = new BigDecimal(l_dblForeignTradePrice);
        l_bdForeignTradePrice =
            l_bdForeignTradePrice.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblForeignTradePrice = l_bdForeignTradePrice.doubleValue();
        l_execDetailInfo.foreignTradePrice =
            WEB3StringTypeUtility.formatNumber(l_dblForeignTradePrice);

        //���n�萔���F �g�����U�N�V�����i������薾�ׁj�s.���n�萔��
        l_execDetailInfo.localCommission = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getForeignCommissionFee());

        //���n����ŁF �g�����U�N�V�����i������薾�ׁj�s.���n�����
        l_execDetailInfo.localTradingTax = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getForeignTax());

        //���̑��R�X�g�P�F �g�����U�N�V�����i������薾�ׁj�s.���̑��R�X�g�P
        l_execDetailInfo.otherCost1 = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getForeignFeeExt1());

        //���̑��R�X�g�Q�F �g�����U�N�V�����i������薾�ׁj�s.���̑��R�X�g�Q
        l_execDetailInfo.otherCost2 = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getForeignFeeExt2());

        //���Z����i�~�݁j�F �g�����U�N�V�����i������薾�ׁj�s.���n���Z����i�~�݁j
        l_execDetailInfo.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getBalanceAmount());

        //���Z����i�O�݁j�F �g�����U�N�V�����i������薾�ׁj�s.���n���Z����i�O�݁j
        l_execDetailInfo.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getBalanceAmountFc());

        //�����萔���i�~�݁j�F �g�����U�N�V�����i������薾�ׁj�s.�ϑ��萔��
        l_execDetailInfo.commission = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getCommissionFee());

        //�����萔���i�O�݁j�F �g�����U�N�V�����i������薾�ׁj�s.�ϑ��萔���i�O�݁j
        l_execDetailInfo.foreignCommission = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getCommissionFeeFc());

        //����Łi�~�݁j�F �g�����U�N�V�����i������薾�ׁj�s.�ϑ��萔�������
        l_execDetailInfo.consumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getCommissionFeeTax());

        //����Łi�O�݁j�F �g�����U�N�V�����i������薾�ׁj�s.�ϑ��萔������Łi�O�݁j
        l_execDetailInfo.foreignConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getCommissionFeeTaxFc());

        //��n��� (�~��)
        if(l_transactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_BUY))
        {
            //��n���z�i�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj�s.��n��� �~�i-1�j
            l_execDetailInfo.deliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_transactionParams.getNetAmount() * (-1));
        }
        else if(l_transactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_SELL))
        {
            //��n���z�i�~�݁j�F�@@�g�����U�N�V�����i������薾�ׁj�s.��n���
            l_execDetailInfo.deliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_transactionParams.getNetAmount());
        }
        
        //��n����i�O�݁j
        if(l_transactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_BUY))
        {
            //��n���z�i�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj�s.��n��� �~�i-1�j
            l_execDetailInfo.foreignDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_transactionParams.getNetAmountFc() * (-1));
        }
        else if(l_transactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_SELL))
        {
            //��n���z�i�O�݁j�F�@@�g�����U�N�V�����i������薾�ׁj�s.��n���
            l_execDetailInfo.foreignDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_transactionParams.getNetAmountFc());
        }
        
        //������n���F ��n��
        l_execDetailInfo.deliveryDate = 
            WEB3DateUtility.toDay(l_datDeliveryDate);
            
        //���P���F�g�����U�N�V�����i������薾�ׁj�s.���P�� 
        l_execDetailInfo.execPrice = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getPrice());
        
        //��萔�ʁF�g�����U�N�V�����i������薾�ׁj�s.��萔��
        l_execDetailInfo.execQuantity = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getQuantity());
         
        //�������F�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V������������ 
        l_execDetailInfo.executionTimestamp = l_transactionParams.getFinTransactionTimestamp();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�o���ڍ׏��) <BR>
     * �o���ڍ׏��Ɉȉ��̒ʂ�l���Z�b�g����B <BR>
     * <BR>
     * ���h�c�F null <BR>
     * ���ԍ��F ���ʔԂ��Apattern="000"�Ńt�H�[�}�b�g����������B <BR>
     * ��������F �O���������z�v�Z����.get��������i�O�݁j() <BR>
     * ���n�萔���F �O���������z�v�Z����.get���n�萔��() <BR>
     * ���n����ŁF �O���������z�v�Z����.get���n�����() <BR>
     * ���̑��R�X�g�P�F �O���������z�v�Z����.get���̑��R�X�g�P() <BR>
     * ���̑��R�X�g�Q�F �O���������z�v�Z����.get���̑��R�X�g�Q() <BR>
     * ���Z����i�~�݁j�F �O���������z�v�Z����.get���n���Z����i�~�݁j() <BR>
     * ���Z����i�O�݁j�F �O���������z�v�Z����.get���n���Z���() <BR>
     * �����萔���i�~�݁j�F �O���������z�v�Z����.get�ϑ��萔��() <BR>
     * �����萔���i�O�݁j�F �O���������z�v�Z����.get�ϑ��萔���i�O�݁j() <BR>
     * ����Łi�~�݁j�F �O���������z�v�Z����.get�ϑ��萔�������() <BR>
     * ����Łi�O�݁j�F �O���������z�v�Z����.get�ϑ��萔������Łi�O�݁j() <BR>
     * ��n����F �O���������z�v�Z����.get��n���() <BR>
     * ��n����i�O�݁j�F �O���������z�v�Z����.get��n����i�O�݁j() <BR>
     * ������n���F ��n�� <BR>
     * ���P���F�@@�g�����U�N�V�����i������薾�ׁj�s.���P��<BR>
     * ��萔�ʁF�@@�g�����U�N�V�����i������薾�ׁj�s.��萔��<BR>
     * �������F�@@�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V������������<BR>
     * 
     * @@param l_execDetailInfo -
     *            (�o���ڍ׏��) <BR>
     *            �o���ڍ׏�񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g <BR>
     * @@param l_transactionParams -
     *            (�g�����U�N�V�����i������薾�ׁj�s)<BR>
     *            �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g<BR>
     * @@param l_feqAmountCalcResult -
     *            (�O���������z�v�Z����) <BR>
     *            �O���������z�v�Z���ʃI�u�W�F�N�g <BR>
     * @@param l_datDeliveryDate -
     *            (��n��) <BR>
     *            ��n�� <BR>
     * @@param l_intExecSerialNo -
     *            (���ʔ�) <BR>
     *            ���ʔ� <BR>
     * @@roseuid 42B27BD50310
     */
    private void createExecDetailInfo(
        WEB3FeqExecDetailInfoUnit l_execDetailInfo,
        FeqFinTransactionParams l_transactionParams,
        WEB3FeqAmountCalcResult l_feqAmountCalcResult, 
        Date l_datDeliveryDate, 
        int l_intExecSerialNo)
    {
        final String STR_METHOD_NAME = "createExecDetailInfo(WEB3FeqExecDetailInfoUnit, " + 
            "FeqFinTransactionParams, WEB3FeqAmountCalcResult, Date, int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_execDetailInfo == null || l_feqAmountCalcResult == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���h�c�F null
        l_execDetailInfo.execId = null;

        //���ԍ��F ���ʔԂ��Apattern="000"�Ńt�H�[�}�b�g����������B
        l_execDetailInfo.execNo = WEB3StringTypeUtility.formatNumber(l_intExecSerialNo, 3);

        //��������F �O���������z�v�Z����.get��������i�O�݁j()
        l_execDetailInfo.foreignTradePrice = WEB3StringTypeUtility.formatNumber(
            l_feqAmountCalcResult.getTradePriceFc());

        //���n�萔���F �O���������z�v�Z����.get���n�萔��()
        l_execDetailInfo.localCommission = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getForignCommissionFee());

        //���n����ŁF �O���������z�v�Z����.get���n�����()
        l_execDetailInfo.localTradingTax = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getForeignTax());

        //���̑��R�X�g�P�F �O���������z�v�Z����.get���̑��R�X�g1()
        l_execDetailInfo.otherCost1 = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getForeignFeeExt1());

        //���̑��R�X�g�Q�F �O���������z�v�Z����.get���̑��R�X�g�Q()
        l_execDetailInfo.otherCost2 = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getForeignFeeExt2());

        //���Z����i�~�݁j�F �O���������z�v�Z����.get���n���Z����i�~�݁j()
        l_execDetailInfo.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getBalanceAmount());

        //���Z����i�O�݁j�F �O���������z�v�Z����.get���n���Z���()
        l_execDetailInfo.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getBalanceAmountFc());

        //�����萔���i�~�݁j�F �O���������z�v�Z����.get�ϑ��萔��()
        l_execDetailInfo.commission = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getCommissionFee());

        //�����萔���i�O�݁j�F �O���������z�v�Z����.get�ϑ��萔���i�O�݁j()
        l_execDetailInfo.foreignCommission = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getCommissionFeeFc());

        //����Łi�~�݁j�F �O���������z�v�Z����.get�ϑ��萔�������()
        l_execDetailInfo.consumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getCommisionFeeTax());

        //����Łi�O�݁j�F �O���������z�v�Z����.get�ϑ��萔������Łi�O�݁j()
        l_execDetailInfo.foreignConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getCommisionFeeTaxFc());

        //��n����F �O���������z�v�Z����.get��n���()
        l_execDetailInfo.deliveryPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getNetAmount());

        //��n����i�O�݁j�F �O���������z�v�Z����.get��n����i�O�݁j()
        l_execDetailInfo.foreignDeliveryPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getNetAmountFc());

        //������n���F ��n��
        l_execDetailInfo.deliveryDate = l_datDeliveryDate;
        
        //���P���F�g�����U�N�V�����i������薾�ׁj�s.���P�� 
        l_execDetailInfo.execPrice = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getPrice());
        
        //��萔�ʁF�g�����U�N�V�����i������薾�ׁj�s.��萔��
        l_execDetailInfo.execQuantity = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getQuantity());
         
        //�������F�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V������������ 
        l_execDetailInfo.executionTimestamp = l_transactionParams.getFinTransactionTimestamp();

        log.exiting(STR_METHOD_NAME);
    }
}@
