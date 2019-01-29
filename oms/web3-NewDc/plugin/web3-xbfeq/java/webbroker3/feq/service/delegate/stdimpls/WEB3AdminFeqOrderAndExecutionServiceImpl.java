head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAndExecutionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �Ǘ��ҊO�����������̓T�[�r�XImpl(WEB3AdminFeqOrderAndExecutionServiceImpl)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/24 ���Ō� (���u) �V�K�쐬
                 : 2005/08/02 ��O��(���u) ���r���[
                   2006/10/17 �����(���u) ���f���@@No.291�Ή�
Revesion History : 2007/11/07 �����q(���u) ���f���@@No.357�A360�A361�Ή�
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2008/02/02 �đo�g(���u) ���f��No.396
Revesion History : 2008/11/12 ���m�a(���u) ���f��No.496
Revesion History : 2009/08/03 �Ԑi(���u)    ���f��No.512
Revesion History : 2010/10/15 ��V��(���u) ���f��No.559
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.DefaultFeqOrderFillMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqExecuteUpdateInterceptor;
import webbroker3.feq.WEB3FeqForeignCost;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqPositionManagerHelper;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductTypeOrderSubmitterPersistenceManager;
import webbroker3.feq.data.ForeignCostParams;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqCostDivDef;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchResponse;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAndExecutionService;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.feq.util.WEB3FeqDateUtility;
import webbroker3.feq.util.WEB3FeqOrderUtility;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO�����������̓T�[�r�XImpl)<BR>
 * �Ǘ��ҊO�����������̓T�[�r�X�����N���X<BR>
 * 
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionServiceImpl implements WEB3AdminFeqOrderAndExecutionService 
{
    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminFeqOrderAndExecutionServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F4005D
     */
    public WEB3AdminFeqOrderAndExecutionServiceImpl() 
    {
     
    }
    
    /**
     * �O�����������͏��������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B<BR>
     * <BR>
     * �|get�������()<BR>
     * �|get���͉��()<BR>
     * �|validate���()<BR>
     * �|submit���()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FFA9403AA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqOrderAndExecutionInputRequest)
        {
            //get���͉��
            l_response = 
                this.getInputScreen((WEB3AdminFeqOrderAndExecutionInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAndExecutionSearchRequest)
        {
            //get�������
            l_response = 
                this.getQueryScreen((WEB3AdminFeqOrderAndExecutionSearchRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAndExecutionConfirmRequest)
        {
            //validate���
            l_response = 
                this.validateExec((WEB3AdminFeqOrderAndExecutionConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAndExecutionCompleteRequest)
        {
            //submit���
            l_response = 
                this.submitExec((WEB3AdminFeqOrderAndExecutionCompleteRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
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
     * (get�������)<BR>
     * ������ʕ\�������B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�����́jget������ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B9149E0098
     */
    protected WEB3AdminFeqOrderAndExecutionSearchResponse getQueryScreen(
        WEB3AdminFeqOrderAndExecutionSearchRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getQueryScreen(WEB3AdminFeqOrderAndExecutionSearchRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3Administrator l_web3Administrator;
        
        //1.1�Ǘ��҃I�u�W�F�N�g���擾���� getInstanceFrom���O�C�����()
        l_web3Administrator  = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2�Ǘ��҂̌����`�F�b�N���s�� �@@�\�J�e�S���R�[�h,
        //[validate����()�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�h�O���i�������Ǘ��j�h 
        //is�X�V�F�@@true 
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
    
        //1.3���X�|���X�f�[�^�𐶐�����
        log.exiting(STR_METHOD_NAME);
        return (WEB3AdminFeqOrderAndExecutionSearchResponse)l_request.createResponse();
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�����́jget���͉�ʁv �Q�ƁB<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@(��)�����́v(��)�����́jget���͉��)<BR>
     * �@@�@@:  1.6 (*) �������̏ꍇ�igetOrderStatus() == �h1:��t�ρi�V�K�����j�h�j�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR> 
     * <BR> 
     * �@@�@@�������̏ꍇ�igetOrderStatus() == �h1:��t�ρi�V�K�����j�h�j�A<BR>
     * �@@�@@��O���X���[����<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02143<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �@@(��)�����́v(��)�����́jget���͉��)<BR>
     * �@@�@@:  1.8�@@(*) �o���I�������ς݂̏ꍇ�iis�o���I��() == true�j�A<BR> 
     * �@@�@@�@@�@@��O���X���[����B<BR> 
     * <BR> 
     * �@@�@@�o���I�������ς݂̏ꍇ�iis�o���I��() == true�j�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02144<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@(��)�����́v(��)�����́jget���͉��)<BR>
     * �@@�@@:  1.10�@@(*) �ꕔ�o���̏ꍇ�iisPartiallyExecuted() == true�j�A<BR> 
     * �@@�@@�@@�@@��O���X���[����B<BR> 
     * <BR> 
     * �@@�@@�ꕔ�o���̏ꍇ�iisPartiallyExecuted() == true�j�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02145<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FFA9403AC
     */
    protected WEB3AdminFeqOrderAndExecutionInputResponse getInputScreen(
        WEB3AdminFeqOrderAndExecutionInputRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderAndExecutionInputRequest)";
        log.entering(STR_METHOD_NAME);        
        
        //1.1 validate() ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_web3Administrator
            = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����()
        //[validate����()�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�h�O���i�������Ǘ��j�h 
        //is�X�V�F�@@true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        WEB3AdminFeqOrderAndExecutionInputResponse l_response = null;
        
        //1.4 �^�p�R�[�h�����͂̏ꍇ
        if (WEB3StringTypeUtility.isEmpty(l_request.managementCode))
        {
            //1.4.1 createResponse( )
            l_response = 
                (WEB3AdminFeqOrderAndExecutionInputResponse)l_request.createResponse();
            //1.4.2  return()
            return l_response;
        }

        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //�V���́u�^�p�R�[�h�v��������擾����B
        //[get�^�p�R�[�h()�Ɏw�肷�����]
        //�،���ЃR�[�h�Fget�،���ЃR�[�h( )�̖߂�l
        //�^�p�R�[�h�F���N�G�X�g�f�[�^.�^�p�R�[�h
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService) Services.getService(
            WEB3FeqOrderEmpCodeGettingService.class);

        String l_strManagementCode = l_feqOrderEmpCodeGettingService.getEmpCode(
            l_strInstitutionCode, l_request.managementCode);

        //1.5 get�L�������P��By�^�p�R�[�h()
        //[get�L�������P��By�^�p�R�[�h()�Ɏw�肷�����] 
        //�������F�@@���N�G�X�g�f�[�^.������ 
        //�������͂̏ꍇ�A���������iTradingSystem.getSystemTimestamp()�j�̓��t�����B 
        //�^�p�R�[�h�Fget�^�p�R�[�h�i�j�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        //���������擾               
        Date l_datOrderBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        if (l_request.orderBizDate != null) 
        {
            l_datOrderBizDate = l_request.orderBizDate;
        }
        
        //get�L�������P��By�^�p�R�[�h
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_feqOrderManager.getValidOrderUnitByOrderEmpCode(
                l_datOrderBizDate,
                l_strManagementCode);

        //1.6 �������̏ꍇ�igetOrderStatus() == �h1:��t�ρi�V�K�����j�h�j����
        //    �������̏ꍇ�igetOrderStatus() == �h2:�������i�V�K�����j�h�j�A
        //    �Ώۃf�[�^��������t����F�؂��s���Ă��Ȃ��ꍇ
        //��O���X���[����B
        FeqOrderUnitImpl l_feqOrderUnitI = l_feqOrderUnit;
        boolean l_confirmedPrice = l_feqOrderUnitI.isConfirmedPriceMarketOrder();
        if (OrderStatusEnum.ACCEPTED.equals(l_feqOrderUnit.getOrderStatus()) ||
            OrderStatusEnum.ORDERING.equals(l_feqOrderUnit.getOrderStatus()) ||
            l_confirmedPrice)
        {
            //1.6.1 ��O���X���[����
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02143,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "������Ԃ�" + l_feqOrderUnit.getOrderStatus() + "�ł�");
        }
        
        //1.7 is�o���I��()
        boolean l_blnIsExecEnd = l_feqOrderUnit.isExecEnd();
        //1.8 �o���I�������ς݂̏ꍇ�iis�o���I��() == true�j�A��O���X���[����B
        if (l_blnIsExecEnd)
        {
            //1.8.1 ��O���X���[����
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02144,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "is�o���I��() = " + l_blnIsExecEnd);
        }
        
        //1.9 isPartiallyExecuted() �����擾����
        boolean l_blnIsPartExecuted  = l_feqOrderUnit.isPartiallyExecuted();
        //1.10 �ꕔ�o���̏ꍇ�iisPartiallyExecuted() == true�j
        if (l_blnIsPartExecuted)
        {
            //1.10.1 ��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02145,
                this.getClass().getName() + STR_METHOD_NAME,
                "sPartiallyExecuted() = " + l_blnIsPartExecuted);
        }
        
        //1.11 �O�����������͏��𐶐�����
        WEB3FeqOrderAndExecutionUnit l_feqExecuteInputInfo = 
            new WEB3FeqOrderAndExecutionUnit();
        
        //1.12 �����̏����O�����������͏��ɃZ�b�g����
        WEB3FeqCommonMessageCreatedService l_messageCreateService =
            new WEB3FeqCommonMessageCreatedServiceImpl();
        //create�O�����������͏��
        //�O�������������ʖ��ׁF�i���������I�u�W�F�N�g�j
        //�����P�ʁF�@@get�L�������P��By�^�p�R�[�h()
        //���F�@@null
        //�g�����U�N�V�����i������薾�ׁj�s�F�@@null
        l_messageCreateService.createFeqOrderAndExecutionUnit(
            l_feqExecuteInputInfo,
            l_feqOrderUnit,
            null,
            null);
        
        //1.13 ���X�|���X�f�[�^�𐶐�����
        l_response = (WEB3AdminFeqOrderAndExecutionInputResponse)l_request.createResponse();
        
        //1.14 ���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����
        l_response.orderAndExecutionUnit = l_feqExecuteInputInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate���)<BR>
     * �����͊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�����́jvalidate���v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3AdminFeqOrderAndExecutionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FFA9403B9
     */
    protected WEB3AdminFeqOrderAndExecutionConfirmResponse validateExec(
        WEB3AdminFeqOrderAndExecutionConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateExec(WEB3AdminFeqOrderAndExecutionConfirmRequest)";
        log.entering(STR_METHOD_NAME);                

        //1.1 getInstanceFrom���O�C�����()
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate����()
        //[validate����()�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�h�O���i�������Ǘ��j�h 
        //is�X�V�F�@@true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        //1.3 clone()
        //���b�Z�[�W�f�[�^�̃R�s�[���擾����B 
        WEB3FeqOrderAndExecutionUnit l_feqExeInputInfo = 
            (WEB3FeqOrderAndExecutionUnit) l_request.orderAndExecutionUnit.clone();
            
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //1.4 HOST�����i���N�G�X�g�f�[�^.�����͏��.�����h�c�������́j�̏ꍇ
        if (WEB3StringTypeUtility.isEmpty(l_feqExeInputInfo.orderId))
        {
            //1.4.1 get�،����()
            WEB3GentradeInstitution l_institution = 
                (WEB3GentradeInstitution) l_web3Administrator.getInstitution();

            //1.4.2 validateHOST�������()
            //�،���ЁFget�،����()
            //�����͏��F���N�G�X�g�f�[�^.�����͏��.clone()
            validateHostOrderExec(
                l_institution,
                l_feqExeInputInfo);
            
            // �v���p�e�B�Z�b�g
            // ����ID
            l_feqExeInputInfo.orderId = l_feqOrderManager.createNewOrderId() + "";
            // �������� 
            l_feqExeInputInfo.orderDate 
                = GtlUtils.getSystemTimestamp();
            
            // ������
            WEB3FeqProductManager l_feqProductManager =
                (WEB3FeqProductManager) GtlUtils.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
            WEB3FeqProduct l_feqProduct = null;
            try
            {
                l_feqProduct = 
                    (WEB3FeqProduct) l_feqProductManager.getFeqProduct(
                        l_institution, 
                        l_feqExeInputInfo.productCode);
            }
            catch (NotFoundException l_ex)
            {
                log.debug("�O�������������擾�ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_feqExeInputInfo.productName = l_feqProduct.getDisplayProductName();
                        
        } 
        else
        {
            //1.5 ����ȯĒ����i���N�G�X�g�f�[�^.�����͏��.�����h�c�ɓ��͂���j�̏ꍇ
                        
            //1.5.1 get�����P��ByOrderId(long)
            //�����h�c�F�@@���N�G�X�g�f�[�^.�����͏��.�����h�c
            WEB3FeqOrderUnit l_feqOrderUnit = null;

            l_feqOrderUnit = 
                (WEB3FeqOrderUnit) l_feqOrderManager.getOrderUnitByOrderId(
                    new Long(l_feqExeInputInfo.orderId).longValue());

            //1.5.2 validate����ȯĒ������
            //�����P�ʁF�@@get�����P��ByOrderId()
            //�����͏��F�@@���N�G�X�g�f�[�^.�����͏��.clone()
            validateInternetOrderExec(
                l_feqOrderUnit,
                l_feqExeInputInfo);
        }
        //1.6 createResponse()
        WEB3AdminFeqOrderAndExecutionConfirmResponse l_response = 
            (WEB3AdminFeqOrderAndExecutionConfirmResponse)l_request.createResponse();
        
        //1.7 �v���p�e�B�Z�b�g
        l_response.orderAndExecutionUnit = l_feqExeInputInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���)<BR>
     * �����͊����������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)�����́jsubmit���v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3AdminFeqOrderAndExecutionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FFA9403BB
     */
    protected WEB3AdminFeqOrderAndExecutionCompleteResponse submitExec(
        WEB3AdminFeqOrderAndExecutionCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitExec(WEB3AdminFeqOrderAndExecutionCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3Administrator l_web3Administrator = null;

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�[�����w��(null)�ł��B");
        }

        //1.1 validate()
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����()
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate����()
        //[validate����()�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�h�O���i�������Ǘ��j�h
        //is�X�V�F�@@true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
            true);

        //1.4 validate����p�X���[�h()
        //[validate����p�X���[�h()�Ɏw�肷�����] 
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_web3Administrator.validateTradingPassword(l_request.password);

        //1.5 ����J�����_�R���e�L�X�g�̎s��R�[�h���ăZ�b�g����
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.orderAndExecutionUnit.marketCode);
        
        //1.6 �����e�[�u�����擾����B
        boolean l_isHostOrder = false;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        Order l_feqOrder = null;
        try
        {
            l_feqOrder = 
                (Order) l_feqOrderManager.getOrder(
                    new Long(l_request.orderAndExecutionUnit.orderId).longValue());
        }
        //NotFoundException���X���[���ꂽ�ꍇ(�Y�����������݂��Ȃ�)�AHOST�����Ƃ���B
        catch(NotFoundException ex)
        {
            l_isHostOrder = true;
        }
        
        //1.7 HOST�����i�����e�[�u���Ȃ��j�̏ꍇ
        if (l_isHostOrder)
        {
            //1.7.1 get�،����()
            WEB3GentradeInstitution l_institution = 
                (WEB3GentradeInstitution)l_web3Administrator.getInstitution();

            //1.7.2 get�Ǘ��҃R�[�h()
            String l_strAdminCode = l_web3Administrator.getAdministratorCode();

            //1.7.3 persistHOST����()
            //�،���ЁF�@@get�،����()
            //�X�V�҃R�[�h�F�@@get�Ǘ��҃R�[�h()
            //�����͏��F�@@���N�G�X�g�f�[�^.�����͏��
            persistHostOrder(l_institution,
                l_strAdminCode,
                l_request.orderAndExecutionUnit);
        }
        //1.8 ����ȯĒ����i�����e�[�u������j�̏ꍇ
        else
        {
            //1.8.1 persist����ȯĒ���
            //�����͏��F�@@���N�G�X�g�f�[�^.�����͏��
            persistInternetOrder(l_request.orderAndExecutionUnit);
        }

        log.exiting(STR_METHOD_NAME);

        //1.9 createResponse()
        return (WEB3AdminFeqOrderAndExecutionCompleteResponse) l_request.createResponse();
    }
    
    /**
     * (validate����ȯĒ������)<BR>
     * �����͊m�F�������s���B�i�C���^�[�l�b�g�����̏ꍇ�j<BR>
     * <BR>
     * �P�j�@@�����f�[�^�̃`�F�b�N<BR>
     * �@@�����f�[�^�̓��e���ύX����Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�������������̏ꍇ�i�����P��.getOrderStatus() == <BR>
     * �h1:��t�ρi�V�K�����j�h�j�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02143<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�o���I���ς݂̏ꍇ�i�����P��.is�o���I��() == true�j�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02144<BR>
     * <BR>
     * �@@�P�|�R�j�@@�ꕔ�o���̏ꍇ�i�����P��.isPartiallyExecuted() == true�j�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02145<BR>
     * <BR>
     * �@@�P�|�S�j�@@�O�����������͏�񐶐�<BR>
     * �@@�@@�O�����������͏��𐶐����A<BR>
     *�@@�@@ �O���������ʃ��b�Z�[�W�쐬�T�[�r�XImpl.create�O�����������͏��()<BR>
     * �@@�@@�ɂāA���b�Z�[�W�f�[�^�𐶐�����B<BR>
     * <BR>
     * �@@�@@[create�O�����������͏��()�Ɏw�肷�����]<BR>
     * �@@�@@�O�����������͏��F�@@�i���������I�u�W�F�N�g�j<BR>
     * �@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@���F�@@null<BR>
     * �@@�@@�g�����U�N�V�����i������薾�ׁj�s�F�@@null<BR>
     * <BR>
     * �@@�P�|�T�j�@@�I�u�W�F�N�g��r<BR>
     * �@@�@@�P�|�S�j�Ő��������I�u�W�F�N�g�ƈ����̖����͏��̃v���p�e�B�l��<BR>
     * ���������r����B<BR>
     * �@@�@@����łȂ��ꍇ�����f�[�^���ύX���ꂽ�Ɣ��肵�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02146<BR>
     * <BR>
     * �@@�@@���A���A���^�g�����U�N�V�����i������薾�ׁj�s����ҏW����<BR>
     * ���ڂ͔�r�̑ΏۊO�Ƃ���B<BR>
     * <BR>
     * �Q�j�@@�����͏��.���בփ��[�g�̃`�F�b�N<BR>
     * �@@���ڒl���ύX���ꂽ�ꍇ�i�P�|�S�j�Ő��������I�u�W�F�N�g.���בփ��[�g != <BR>
     * �����̖����͏��.���בփ��[�g�j�̂݁A�������{�B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02036<BR>
     * <BR>
     *   �Q�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02220<BR>
     * <BR>
     * �@@�Q�|�R�j�@@���l�ɕϊ��������̗L���������A�������R���C<BR>
     * �������S���͈̔͊O�ł���΁A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02037<BR>
     * <BR>
     * �@@�Q�|�S�j�@@���l�ɕϊ������l <= 0�ł���΁A��O���X���[����B <BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02196<BR>
     * <BR>
     * �R�j�@@�����͏��.���ڍ�.���P���̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02021<BR>
     * <BR>
     * �@@�R�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02022<BR>
     * <BR>
     * �@@�R�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02023<BR>
     * <BR>
     * �@@�R�|�S�j�@@���l�ɕϊ��������̗L���������A�������U���C<BR>
     * �������T���͈̔͊O�ł���΁A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02024<BR>
     * <BR>
     * �@@�R�|�T�j�@@�O�����������}�l�[�W��.validate���P��()�ɂāA<BR>
     * ���P�����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@[validate���P��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@��萔�ʁF�@@�����͏��.���ڍ�.���P��<BR>
     * <BR>
     * �S�j�@@�����͏��.���ڍ�.��萔�ʂ̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02025<BR>
     * <BR>
     * �@@�S�|�Q�j�@@9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02026<BR>
     * <BR>
     *   �S�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02186<BR>
     * <BR>
     * �@@�S�|�S�j�@@�O�����������}�l�[�W��.validate��萔��()�ɂāA<BR>
     *             ��萔�ʂ��`�F�b�N����B<BR>
     * <BR>
     * �@@�@@[validate��萔��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@��萔�ʁF�@@�����͏��.���ڍ�.��萔��<BR>
     * <BR>
     * �T�j�@@�����͏��.�������̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * <BR>
     * �@@�|�i���͂������ꍇ�j<BR>
     * �@@�@@�O�����������}�l�[�W��.validate����()�ɂāA���������`�F�b�N����B<BR>
     * <BR>
     * �@@�@@[validate����()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@�����F�@@�����͏��.���ڍ�.������<BR>
     * <BR>
     * �@@�|�i�����͂̏ꍇ�j<BR>
     * �@@�@@�@@�����iTradingSystem.getSystemTimestamp()�j���Z�b�g����B<BR>
     * <BR>
     * �U�j�@@�����͏��.���n��n���̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * <BR>
     * �@@�|�i���͂�����ꍇ�j<BR>
     * �@@�@@�O�����������}�l�[�W��.validate���n��n��()�ɂāA���n��n�����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@[validate���n��n��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@���n��n���F�@@�����͏��.���n��n��<BR>
     * <BR>
     * �@@�|�i�����͂̏ꍇ�j<BR>
     * �@@�@@�����͏��.�����̂R�c�Ɠ�����Z�b�g����B<BR>
     * <BR>
     * �V�j�@@������n���̃`�F�b�N<BR>
     * �@@�i�����͂̏ꍇ�j<BR>
     * �@@�@@�����͏��.�����̂R�c�Ɠ�����Z�b�g����B<BR>
     * <BR>
     * �W�j�@@���No.�̍̔�<BR>
     * �@@�i���꒍���ŉߋ��ɖ�肪�������Ă���ꍇ�j<BR> 
     * �@@�@@�����P��.getExecute()�ɂāA�������擾����B<BR> 
     * �@@�@@�w�Ō�̒���No.�{1�x������̖��No.�Ƃ���B<BR>
     * <BR>
     * �X�j�@@���z���ڂ̃`�F�b�N<BR>
     * �@@this.validate�O���������z()���R�[������B<BR>
     * <BR>
     * �@@[validate�O���������z()�Ɏw�肷�����]<BR>
     * �@@�،���ЃR�[�h�F�@@�����P��.�،���ЃR�[�h<BR>
     * �@@�⏕�����F�@@�����P��.get�⏕����()<BR>
     * �@@�s��F�@@�����P��.getProduct().get�s��()<BR>
     * �@@�ʉ݁F�@@�����P��.getProduct().get�ʉ�()<BR>
     * �@@�����F�@@�O����������<BR>
     * �@@�ŋ敪�F�@@�����P��.getTaxType()<BR>
     * �@@�����͏��F�@@�����͏��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_execInputInfo - (�����͏��)<BR>
     * �O�����������͏��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B65B4C035D
     */
    private void validateInternetOrderExec(
        WEB3FeqOrderUnit l_orderUnit, 
        WEB3FeqOrderAndExecutionUnit l_execInputInfo)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateInternetOrderExec(WEB3FeqOrderUnit," + 
            " WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null || l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�[�����w��(null)�ł��B");
        }
        
        //�P�j�@@�����f�[�^�̃`�F�b�N �����f�[�^�̓��e���ύX����Ă��Ȃ����`�F�b�N���s���B
        
        //�P�|�P�j�@@�������������̏ꍇ
        //�i�����P��.getOrderStatus() == �h1:��t�ρi�V�K�����j�h�j�A
        //��O���X���[����B
        if (OrderStatusEnum.ACCEPTED.equals(l_orderUnit.getOrderStatus())) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02143,
                this.getClass().getName() + STR_METHOD_NAME,
                "������Ԃ�" + l_orderUnit.getOrderStatus() + "�ł�");
        }
        
        //�P�|�Q�j�@@�o���I���ς݂̏ꍇ�i�����P��.is�o���I��() == true�j�A
        //��O���X���[����B
        if (l_orderUnit.isExecEnd())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02144,
                this.getClass().getName() + STR_METHOD_NAME,
                "�o���I�������ς݂Ȃ̂ŁA���s�ł��B");
        }
        
        //�P�|�R�j�@@�ꕔ�o���̏ꍇ�i�����P��.isPartiallyExecuted() == true�j�A
        //��O���X���[����B
        if (l_orderUnit.isPartiallyExecuted())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02145,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ꕔ�o���̏ꍇ�Ȃ̂ŁA���s�ł��B");
        }
        
        //�P�|�S�j�@@�O�����������͏�񐶐�
        //�O�����������͏��𐶐����A
        //�O���������ʃ��b�Z�[�W�쐬�T�[�r�XImpl.create�O�����������͏��()�ɂāA
        //���b�Z�[�W�f�[�^�𐶐�����B        
        WEB3FeqCommonMessageCreatedService l_messageCreateService =
            new WEB3FeqCommonMessageCreatedServiceImpl();

        WEB3FeqOrderAndExecutionUnit l_web3FeqOrderAndExecutionUnit = 
            (WEB3FeqOrderAndExecutionUnit) l_execInputInfo.clone();
        //[create�O�����������͏��()�Ɏw�肷�����]
        //�O�����������͏��F�@@�i���������I�u�W�F�N�g�j
        //�����P�ʁF�@@�����P��
        //���F�@@null
        //�g�����U�N�V�����i������薾�ׁj�s�F�@@null
        l_messageCreateService.createFeqOrderAndExecutionUnit(
            l_web3FeqOrderAndExecutionUnit,
            l_orderUnit,
            null,
            null);
        
        //�P�|�T�j�@@�I�u�W�F�N�g��r
        //  �P�|�S�j�Ő��������I�u�W�F�N�g�ƈ����̖����͏��̃v���p�e�B�l�����������r����B
        //�@@����łȂ��ꍇ�����f�[�^���ύX���ꂽ�Ɣ��肵�A��O���X���[����B
        //�@@���A���A���^�g�����U�N�V�����i������薾�ׁj�s����ҏW���鍀�ڂ͔�r�̑ΏۊO�Ƃ���B      
        if (!this.isOrderAndExecutionUnitEquals(l_web3FeqOrderAndExecutionUnit, l_execInputInfo))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02146,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����͏�񂪓���ł͂Ȃ������f�[�^�Ȃ̂ŁA�ύX�s�ł��B");            
        }

        if (l_execInputInfo.execDetailInfoUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����͏��.���ڍׂ����݂��Ȃ��B");   
        }

        //�Q�j�@@�����͏��.���בփ��[�g�̃`�F�b�N
        //���ڒl���ύX���ꂽ�ꍇ�i�P�|�S�j��
        //���������I�u�W�F�N�g.���בփ��[�g != �����̖����͏��.���בփ��[�g�j�̂݁A
        //�������{�B
        String l_strExecRate1 = l_web3FeqOrderAndExecutionUnit.execExchangeRate;
        String l_strExecRate2 = l_execInputInfo.execExchangeRate;
        if (!this.isEqual(l_strExecRate1, l_strExecRate2))
        {
            //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(l_strExecRate2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02036,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���בփ��[�g�����w��ł��B");                
            }
            //�Q�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(l_strExecRate2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ���בփ��[�g�����l�łȂ��̏ꍇ�`�F�b�N"); 
            }            
            //�Q�|�R�j�@@���l�ɕϊ��������̗L���������A�������R���C
            //�������S���͈̔͊O�ł���΁A��O���X���[����B
            if (WEB3StringTypeUtility.getIntegerDigits(l_strExecRate2) > 3 
                || WEB3StringTypeUtility.getFractionDigits(l_strExecRate2) > 4)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���בփ��[�g�̗L���������A�������R���C�������S���͈̔͊O�ł��B");
            }

            //�Q�|�S�j�@@���l�ɕϊ������l <= 0�ł���΁A��O���X���[����B
            if (Double.parseDouble(l_strExecRate2) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���בփ��[�g�����l�ɕϊ������l <= 0�ł��B");
            }
        }

        //�R�j�@@�����͏��.���ڍ�.���P���̃`�F�b�N
        //�@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        String l_strExecPrice = l_execInputInfo.execDetailInfoUnit.execPrice;
        if (WEB3StringTypeUtility.isEmpty(l_strExecPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + STR_METHOD_NAME,
                "���P���������͂ł��B");
        }
        
        //�@@�R�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����
        if (!WEB3StringTypeUtility.isNumber(l_strExecPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + STR_METHOD_NAME,
                "���P�������l�ȊO�̒l�ł��B");
        }
        
        //�@@�R�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B
        if (Double.parseDouble(l_strExecPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + STR_METHOD_NAME,
                "���P�������l�ɕϊ������l <= 0�ł��B");
        }
        //�@@�R�|�S�j�@@���l�ɕϊ��������̗L���������A�������U���C
        //�������T���͈̔͊O�ł���΁A��O���X���[����B
        if (WEB3StringTypeUtility.getIntegerDigits(l_strExecPrice) > 6 
            || WEB3StringTypeUtility.getFractionDigits(l_strExecPrice) > 5)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02024,
                this.getClass().getName() + STR_METHOD_NAME,
                "���P���͐������U���C�������T���͈̔͊O�ł��B");
        }
        
        //�@@�R�|�T�j�@@�O�����������}�l�[�W��.validate���P��()�ɂāA
        //���P�����`�F�b�N����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        double l_dblExecPrice = Double.parseDouble(l_strExecPrice);
        //[validate���P��()�Ɏw�肷�����] 
        //�����P�ʁF�@@�����P��
        //���P���F�@@�����͏��.���ڍ�.���P��
        l_feqOrderManager.validateExecutedPrice(
            l_orderUnit,
            l_dblExecPrice);

        //�S�j�@@�����͏��.���ڍ�.��萔�ʂ̃`�F�b�N
        String l_strExecQuantity = l_execInputInfo.execDetailInfoUnit.execQuantity;
        //�@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_strExecQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02025,
                this.getClass().getName() + STR_METHOD_NAME,
                "��萔�ʂ������͂ł��B");
        }
        
        //�@@�S�|�Q�j�@@9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(l_strExecQuantity) || 
            WEB3StringTypeUtility.getIntegerDigits(l_strExecQuantity) > 9)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                "��萔�ʂ�9���ȓ��̐����l�ł͂���܂���B");
        }
        
        //  �S�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B
        if (Double.parseDouble(l_strExecQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02186,
                this.getClass().getName() + STR_METHOD_NAME,
                "��萔�ʂ����l�ɕϊ������l <= 0�ł��B");
        }
        
        //�@@�S�|�S�j�@@�O�����������}�l�[�W��.validate��萔��()�ɂāA
        //��萔�ʂ��`�F�b�N����B
        double l_dblExecQuantity = Double.parseDouble(l_strExecQuantity);
        //[validate��萔��()�Ɏw�肷�����] 
        //�����P�ʁF�@@�����P��
        //��萔�ʁF�@@�����͏��.���ڍ�.��萔��
        l_feqOrderManager.validateExecutedQuantity(
            l_orderUnit,
            l_dblExecQuantity);
        
		//�����敪�����t�̏ꍇ�A��萔�ʂɂ��Ĉȉ��̃`�F�b�N���s���B
		if (WEB3BuySellTypeDef.SELL.equals(l_execInputInfo.dealingType)){
			//����ID���擾����B
			long l_lngProductId = l_orderUnit.getProduct().getProductId();
			//�ŋ敪���擾����B
			TaxTypeEnum l_taxType = 
						  WEB3TaxTypeSpecialDef.SPECIAL.equals(l_execInputInfo.taxType)?TaxTypeEnum.SPECIAL:TaxTypeEnum.NORMAL;

			WEB3FeqPositionManager l_web3FeqPositionManager =
						(WEB3FeqPositionManager)l_finApp.getTradingModule(
						ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();

			//�ۗL���Y���擾����B
			Asset l_asset = 
					  l_web3FeqPositionManager.getAsset(
					  l_orderUnit.getAccountId(), 
					  l_orderUnit.getSubAccountId(),
					  l_lngProductId, 
					  l_taxType);
							
			if(l_asset == null){
				//�ۗL���Y�ɊY������f�[�^���Ȃ��ꍇ�A��O���X���[����B
				log.error("�ۗL���Y�Y���f�[�^�Ȃ��B");
				throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00204,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}else{
				if(!(l_asset.getQuantity() >= Long.parseLong(l_execInputInfo.execDetailInfoUnit.execQuantity))){
					//�i�ۗL���Y�̔��t�\���� >= ��萔�ʁj�łȂ��ꍇ�A��O���X���[����B
					log.error("�ۗL���Y�c���ʃ`�F�b�N�G���[�B");
					throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01931,
					this.getClass().getName() + "." + STR_METHOD_NAME);
				}
			}
		}
        
        //�T�j�@@�����͏��.�������̃`�F�b�N
        //�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B
        //�@@�|�i�����͂̏ꍇ�j
        //�@@�@@�@@�����iTradingSystem.getSystemTimestamp()�j���Z�b�g����B
        if (l_execInputInfo.execDetailInfoUnit.executionTimestamp == null)
        {
            l_execInputInfo.execDetailInfoUnit.executionTimestamp = 
                GtlUtils.getSystemTimestamp();
        }
        else
        {
            //�@@�|�i���͂������ꍇ�j
            //�@@�@@�O�����������}�l�[�W��.validate����()�ɂāA���������`�F�b�N����B
            //�����P�ʁF�@@�����P��
            //�����F�@@�����͏��.���ڍ�.������
            l_feqOrderManager.validateExecutionDate(
                l_orderUnit,
                l_execInputInfo.execDetailInfoUnit.executionTimestamp);
        }
        
        //�U�j�@@�����͏��.���n��n���̃`�F�b�N
        //�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B
        //�@@�|�i�����͂̏ꍇ�j
        //�@@�@@�����͏��.�����̂R�c�Ɠ�����Z�b�g����B        
        if (l_execInputInfo.localDeliveryDate == null)
        {
            
            Timestamp l_tsExecutionTimestamp = 
                new Timestamp(l_execInputInfo.execDetailInfoUnit.executionTimestamp.getTime());
            
            WEB3GentradeBizDate l_bizDate = 
                new WEB3GentradeBizDate(l_tsExecutionTimestamp);            
            
            l_execInputInfo.localDeliveryDate = l_bizDate.roll(3);
        } 
        else
        {
            //�@@�|�i���͂�����ꍇ�j
            //�@@�@@�O�����������}�l�[�W��.validate���n��n��()�ɂāA���n��n�����`�F�b�N����B
            //�����P�ʁF�@@�����P��
            //���n��n���F�@@�����͏��.���n��n��
            l_feqOrderManager.validateFDeliveryDate(
                l_orderUnit,
                l_execInputInfo.localDeliveryDate);
        }
        
        //�V�j�@@������n���̃`�F�b�N
        //�@@�����͂̏ꍇ�͒l�Z�b�g���s���B
        //�@@�@@�����͏��.�����̂R�c�Ɠ�����Z�b�g����B        
        if (l_execInputInfo.execDetailInfoUnit.deliveryDate == null)
        {            
            Timestamp l_tsExecutionTimestamp = 
                new Timestamp(l_execInputInfo.execDetailInfoUnit.executionTimestamp.getTime());
            
            WEB3GentradeBizDate l_bizDate = 
                new WEB3GentradeBizDate(l_tsExecutionTimestamp);            
            
            l_execInputInfo.execDetailInfoUnit.deliveryDate = l_bizDate.roll(3);
        }        
        
        //�W�j�@@���No.�̍̔�
        // ���꒍���ŉߋ��ɖ�肪�������Ă���ꍇ�́A
        // �Ō�́w���No.�{�P�x������̖��No.�Ƃ���B         
        // �����P��.getExecutions()�ɂāA�������擾����B
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
        
        int l_intExecSerialNo = 1; 
        for (int i = 0; i < l_orderExecutions.length; i++)
        {
            if (l_intExecSerialNo < l_orderExecutions[i].getExecutionSerialNo())
            {
                l_intExecSerialNo = l_orderExecutions[i].getExecutionSerialNo() + 1;
            }
        }
        // �t�H�[�}�b�g�ϊ�
        l_execInputInfo.execDetailInfoUnit.execNo = 
            WEB3StringTypeUtility.formatNumber(l_intExecSerialNo, 3);
        
        //�X�j�@@���z���ڂ̃`�F�b�N
        //�@@this.validate�O���������z()���R�[������B
        //�،���ЃR�[�h�F�@@�����P��.�،���ЃR�[�h
        //�⏕�����F�@@�����P��.get�⏕����()
        //�s��F�@@�����P��.getProduct().get�s��()
        //�ʉ݁F�@@�����P��.getProduct().get�ʉ�()
        //�����F�@@�O����������
        //�ŋ敪�F�@@�����P��.getTaxType()
        //�����͏��F�@@�����͏��
        WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_orderUnit.getProduct();
        validateFeqAmount(
            l_orderUnit.getInstitutionCode(),
            l_orderUnit.getSubAccount(),
            l_feqProduct.getMarket(),
            l_feqProduct.getCurrency(),
            l_feqProduct,
            l_orderUnit.getTaxType(),
            l_execInputInfo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateHOST�������)<BR>
     * �����͊m�F�������s���B�iHOST�����̏ꍇ�j<BR>
     * <BR>
     * �P�j�@@�����͏��.���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���݃`�F�b�N�����{����B<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.getBranch()�ɂĕ��X�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�擾�ł��Ȃ������ꍇ�A���X�R�[�h���͂��s���Ɣ��f���A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01386<BR>
     * <BR>
     * �@@�@@[getBranch()�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЁF�@@�،����<BR>
     * �@@�@@���X�R�[�h�F�@@�����͏��.���X�R�[�h<BR>
     * <BR>
     * �Q�j�@@�����͏��.�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_00835<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���݃`�F�b�N�����{����B<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�擾�ł��Ȃ������ꍇ�A�ڋq�R�[�h���͂��s���Ɣ��f���A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_01035<BR>
     * <BR>
     * �@@�@@[get�ڋq()�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�،����.getInstitutionCode()<BR>
     * �@@�@@���X�R�[�h�F�@@�����͏��.���X�R�[�h<BR>
     * �@@�@@�����R�[�h�F�@@�����͏��.�ڋq�R�[�h<BR>
     * <BR>
     * �R�j�@@�����͏��.���҃R�[�h�̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * <BR>
     * �@@�|�i���͂�����ꍇ�j<BR>
     * �@@�@@�ڋq.���҃R�[�h�iSONAR�j�Ɩ����͏��.���҃R�[�h���s��v�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02147<BR>
     * <BR>
     * �@@�|�i�����͂̏ꍇ�j<BR>
     * �@@�@@�ڋq.���҃R�[�h�iSONAR�j���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@�����͏��.�������̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * <BR>
     * �@@�|�i���͂�����ꍇ�j<BR>
     * �@@�@@�������̓��t���A�c�Ɠ��ł��邩�`�F�b�N����B<BR>
     * �@@�@@�c�Ɠ��łȂ���΁A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02149<BR>
     * <BR>
     * �@@�|�i�����͂̏ꍇ�j<BR>
     * �@@�@@�@@�����iTradingSystem.getSystemTimestamp()�j���Z�b�g����B<BR>
     * <BR>
     * �T�j�@@�����͏��.��������敪�̃`�F�b�N<BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02113<BR>
     * <BR>
     * �@@�T�|�Q�j�@@��������w��i�����͏��.��������敪 == �h1�F����h�j�̏ꍇ�A<BR>
     * ��������Ŏ���\�����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�����}�l�[�W��.validate��������J��()�ɂē�������敪���`�F�b�N����B<BR>
     * <BR>
     * �@@�@@[validate��������J��()�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����F�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕�����i�ڋq�j<BR>
     * �@@�@@�������F�@@�����͏��.���ڍ�.������<BR>
     * <BR>
     *   �U�j�@@�����͏��.�����R�[�h�^���n�����R�[�h�̃`�F�b�N<BR>
     * �@@�U�|�P�j�@@�����͏��.�����R�[�h�^���n�����R�[�h�̗����������͂̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02148<BR>
     * <BR>
     * �@@�U�|�Q�j�@@���݃`�F�b�N�����{����B<BR>
     * �@@�@@�O�������v���_�N�g�}�l�[�W��.get�O����������()�ɂĊO������<BR>
     * �����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�擾�ł��Ȃ������ꍇ�A�����R�[�h���͂��s���Ɣ��f���A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02142<BR>
     * <BR>
     * �@@�@@[get�O����������()�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЁF�@@�،����<BR>
     * �@@�@@�����R�[�h�F�@@�����͏��.�����R�[�h�B�����͂̏ꍇ�͌��n�����R�[�h�B<BR>
     * <BR>
     * �@@�U�|�R�j�@@�����R�[�h�^���n�����R�[�h�^�s��R�[�h�^�ʉ݃R�[�h���Z�b�g����B<BR>
     * �@@�@@�����͏��.�����R�[�h�F�@@�O����������.getProductCode()<BR>
     * �@@�@@�����͏��.���n�����R�[�h�F�@@�O����������.get���n�����R�[�h()<BR>
     * �@@�@@�����͏��.�s��R�[�h�F�@@�O����������.get�s��R�[�h()<BR>
     * �@@�@@�����͏��.�ʉ݃R�[�h�F�@@�O����������.get�ʉ݃R�[�h()<BR>
     * <BR>
     * �V�j�@@�����͏��.���ϋ敪�̃`�F�b�N<BR>
     * �@@�V�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02111<BR>
     * <BR>
     * �@@�V�|�Q�j�@@�O�݌��ώw��i�����͏��.���ϋ敪 == �h1�F�O�݌��ρh�j�̏ꍇ�A<BR>
     * �O�݌��ω\�����`�F�b�N����B<BR>
     * �@@�@@�����}�l�[�W��.validate�O�݌���()�ɂČ��ϋ敪���`�F�b�N����B<BR>
     * <BR>
     * �@@�@@[validate�O�݌���()�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����F�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕�����i�ڋq�j<BR>
     * �@@�@@�ʉ݃R�[�h�F�@@�����͏��.�ʉ݃R�[�h<BR>
     * <BR>
     * �W�j�@@�����͏��.�����敪�̃`�F�b�N<BR>
     * �@@�W�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_01402<BR>
     * <BR>
     * �@@�W�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_01403<BR>
     * <BR>

     * �X�j�@@�����P���敪�̃`�F�b�N<BR>
     * �@@�X�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_00184<BR>
     * <BR>
     * �@@�X�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_00185<BR>
     * <BR>
     * �P�O�j�@@�����͏��.���בփ��[�g�̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * �@@�P�O�|�P�j�@@�����͂̏ꍇ<BR>
     * �@@�@@�@@�P�O�|�P�|�P�j�@@�ʉ݃R�[�h���擾����B<BR>
     * �@@�@@�@@�P�O�|�P�|�Q�j�@@�i���ʁj�ʉ݃e�[�u�����Y��������בփ��[�g���擾���A<BR>
     * �@@�@@�@@�Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@-a)�ʉ݃R�[�h�Ə،���ЃR�[�h�ɕR�Â��A�i���ʁj�ʉ݃I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�@@-b)�i���ʁj�ʉ݃I�u�W�F�N�g���A�@@���N�G�X�g�f�[�^�D�����敪�ɊY������<BR>
     * �@@�@@�@@�@@�@@�@@�בփ��[�g���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i�����敪�@@���@@���@@�̏ꍇ�j�@@���t���בփ��[�g���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i�����敪�@@���@@���@@�̏ꍇ�j�@@���t���בփ��[�g���擾����B<BR>
     * �@@�@@�@@�@@�@@-c)�@@-b�Ŏ擾�������בփ��[�g���Z�b�g����B<BR>
     * <BR>
     *   �P�O�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02220<BR>
     * <BR>
     * �@@�P�O�|�R�j�@@���l�ɕϊ��������̗L���������A�������R���C�������S����<BR>
     * �͈͊O�ł���΁A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02037<BR>
     * <BR>
     * �@@�P�O�|�S�j�@@���l�ɕϊ������l <= 0�ł���΁A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02196<BR>
     * <BR>
     * �P�P�j�@@�����͏��.���ڍ�.���P���̃`�F�b�N<BR>
     * �@@�P�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02021<BR>
     * <BR>
     * �@@�P�P�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02022<BR>
     * <BR>
     * �@@�P�P�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02023<BR>
     * <BR>
     * �@@�P�P�|�S�j�@@���l�ɕϊ��������̗L���������A�������U���C�������T����<BR>
     * �͈͊O�ł���΁A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02024<BR>
     * <BR>
     * �P�Q�j�@@�����͏��.���ڍ�.��萔�ʂ̃`�F�b�N<BR>
     * �@@�P�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02025<BR>
     * <BR>
     * �@@�P�Q�|�Q�j�@@9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02026<BR>
     * �P�Q�|�R�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02186<BR>
     * <BR>
     * �P�R�j�@@�����͏��.���ڍ�.������n���Z�b�g<BR>
     * �@@��n���������͂̏ꍇ�i�����͏��.���ڍ�.������n�� == null�j�A<BR>
     * �@@�����̂R�c�Ɠ�����Z�b�g����B<BR>
     * <BR>
     * �P�S�j�@@�����͏��.���n��n���̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * <BR>
     * �@@�|�i�����͂̏ꍇ�j<BR>
     * �@@�@@�����͏��.���ڍ�.������n�����Z�b�g����B<BR>
     * <BR>
     * �P�T�j�@@�������̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * <BR>
     * �@@�|�i���͂�����ꍇ�j<BR>
     * �@@�@@�����iTradingSystem.getSystemTimestamp()�̓��t�����j��<BR>
     * �R�c�Ɠ��O���擾����B<BR>
     * �@@�@@�R�c�Ɠ��O���O�̓��t�ł���΁i�����̂R�c�Ɠ��O �� �������j�A<BR>
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02150<BR>
     * <BR>
     * �@@�|�i�����͂̏ꍇ�j<BR>
     * �@@�@@�����iTradingSystem.getSystemTimestamp()�̓��t�����j���Z�b�g����B<BR>
     * <BR>
     * �@@�|���������c�Ɠ����`�F�b�N����B<BR> 
     * �@@�@@�c�Ɠ��łȂ���΁A��O���X���[����B<BR>
     * �P�U�j�@@�V�X�e�������Z�b�g���ڂ̃`�F�b�N<BR>
     * �@@�P�U�|�P�j�@@���͒l�`�F�b�N<BR>
     * �@@�@@�ȉ��̍��ڂɓ��͂�����ꍇ�inull�łȂ��ꍇ�j�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:�@@�@@BUSINESS_ERROR_02151<BR>
     * <BR>
     * �@@�@@�����͏��.�^�p�R�[�h<BR>
     * �@@�@@�����͏��.�`�[�ԍ�<BR>
     * �@@�@@�����͏��.��������<BR>
     * �@@�@@�����͏��.�����P��<BR>
     * <BR>
     * �@@�P�U�|�Q�j�@@�l�Z�b�g<BR>
     * �@@�@@�ȉ��̒ʂ�A�����͏��̃v���p�e�B�ɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�����͏��.�^�p�R�[�h�F�@@<BR>
     * �@@�@@�@@�O�������^�p�R�[�h�̔ԃT�[�r�X.get�V�K�^�p�R�[�h()<BR>
     * <BR>
     * �@@�@@�@@[get�V�K�^�p�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s��F�@@�O����������.get�s��()<BR>
     * �@@�@@�@@�������F�@@�����͏��.������<BR>
     * <BR>
     * �@@�@@�����͏��.���ʃR�[�h�F<BR>
     * �@@�@@�@@�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()<BR>
     * <BR>
     * �@@�@@�@@[get�V�K���ʃR�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�،����.getInstitutionCode()<BR>
     * �@@�@@�@@���X�R�[�h�F�@@�����͏��.���X�R�[�h<BR>
     * �@@�@@�@@�����^�C�v�F�@@�����^�C�v.�O������<BR>
     * <BR>
     * �@@�@@�����͏��.�`�[�ԍ��F�@@"9"(WebBroker)�{���ʃR�[�h�̉�3��<BR>
     * <BR>�@@�@@
     * �@@�@@�����͏��.�������ʁF�@@�����͏��.���ڍ�.��萔��<BR>
     * �@@�@@�����͏��.�����P���F�@@�����͏��.���ڍ�.���P��<BR>
     * <BR>
     * �P�V�j�@@���No.�̍̔�<BR>
     *   this.validate�O���������z()���R�[������B<BR>
     * <BR>
     * �P�W�j�@@���z���ڂ̃`�F�b�N<BR>
     * �@@this.validate�O���������z()���R�[������B<BR>
     * <BR>
     * �@@[validate�O���������z()�Ɏw�肷�����]<BR>
     * �@@�،���ЃR�[�h�F�@@�����P��.�،���ЃR�[�h<BR>
     * �@@�⏕�����F�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕�����i�ڋq�j<BR>
     * �@@�s��F�@@�O����������.get�s��()<BR>
     * �@@�ʉ݁F�@@�O����������.get�ʉ�()<BR>
     * �@@�����F�@@�O����������<BR>
     * �@@�ŋ敪�F�@@�����͏��.��������敪�ɑΉ�����ŋ敪�iTaxTypeEnum�j<BR>
     * �@@�����͏��F�@@�����͏��<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_execInputInfo - (�����͏��)<BR>
     * �O�����������͏��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B65B4E0001
     */
    private void validateHostOrderExec(
        WEB3GentradeInstitution l_institution, 
        WEB3FeqOrderAndExecutionUnit l_execInputInfo)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateHostOrderExec(WEB3GentradeInstitution," + 
            " WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null || l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�[�����w��(null)�ł��B");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        WEB3FeqProductManager l_web3FeqProductManager = 
            (WEB3FeqProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        
		WEB3FeqPositionManager l_web3FeqPositionManager =
					(WEB3FeqPositionManager)l_finApp.getTradingModule(
						ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        
        WEB3FeqProduct l_web3FeqProduct = null;
        
        //�P�j�@@�����͏��.���X�R�[�h�̃`�F�b�N
        //�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        } 
        else
        {
            //�P�|�Q�j�@@���݃`�F�b�N�����{����B
            //�@@�g���A�J�E���g�}�l�[�W��.getBranch()�ɂĕ��X�I�u�W�F�N�g���擾����B
            //  �擾�ł��Ȃ������ꍇ�A���X�R�[�h���͂��s���Ɣ��f���A��O���X���[����B
            //[getBranch()�Ɏw�肷�����] 
            //�،���ЁF�@@�،����
            //���X�R�[�h�F�@@�����͏��.���X�R�[�h
            try 
            {
                WEB3GentradeBranch l_branch = 
                    (WEB3GentradeBranch)l_gentradeAccountManager.getBranch(
                        l_institution,
                        l_execInputInfo.branchCode);
                
                if (l_branch == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���X�R�[�h���͂��s���ł��A���͂��ꂽ���X�R�[�h = " + l_execInputInfo.branchCode);                     
                }
            }
            catch (NotFoundException l_nfex) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���X�R�[�h���͂��s���ł��A���͂��ꂽ���X�R�[�h = " + l_execInputInfo.branchCode);
            }
        }
         
        //�Q�j�@@�����͏��.�ڋq�R�[�h�̃`�F�b�N
        //�@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        MainAccount l_mainAccount = null;
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.accountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }
        else
        {
            //�Q�|�Q�j�@@���݃`�F�b�N�����{����B
            //�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾����B
            //�@@�擾�ł��Ȃ������ꍇ�A�ڋq�R�[�h���͂��s���Ɣ��f���A��O���X���[����B
            //
            //[get�ڋq()�Ɏw�肷�����] 
            //   �،���ЃR�[�h�F�@@�،����.getInstitutionCode()
            //   ���X�R�[�h�F�@@�����͏��.���X�R�[�h
            //   �����R�[�h�F�@@�����͏��.�ڋq�R�[�h
            try
            {
                l_mainAccount = l_gentradeAccountManager.getMainAccount(
                    l_institution.getInstitutionCode(),
                    l_execInputInfo.branchCode,
                    l_execInputInfo.accountCode);
            }
            catch (WEB3BaseException e)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̓��͂��s���ł��B");
            }
        }
         
        //�R�j�@@�����͏��.���҃R�[�h�̃`�F�b�N
        //�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B
        //�@@�|�i�����͂̏ꍇ�j
        //�@@�@@�ڋq.���҃R�[�h�iSONAR�j���Z�b�g����B
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        if (l_mainAccountRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋqRow�I�u�W�F�N�g�����݂��Ȃ��B"); 
        }
        String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.traderCode))
        {
            l_execInputInfo.traderCode = l_strSonarTraderCode;
        }
        else
        {
            //�@@�|�i���͂�����ꍇ�j
            //�@@�@@�ڋq.���҃R�[�h�iSONAR�j�Ɩ����͏��.���҃R�[�h���s��v�̏ꍇ�A��O���X���[����B
            if (!l_strSonarTraderCode.equals(l_execInputInfo.traderCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02147,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����͏��̈��҃R�[�h�ƌڋq.���҃R�[�h�iSONAR�j���s��v�ł��B");
            }
        }
        
        WEB3GentradeSubAccount l_subAccount = 
            WEB3FeqClientRequestService.getSubAccount(
                (WEB3GentradeMainAccount)l_mainAccount);
             
        //�S�j�@@�����͏��.�������̃`�F�b�N
        //�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B
        //�@@�|�i�����͂̏ꍇ�j
        //�@@�@@�@@�����iTradingSystem.getSystemTimestamp()�j���Z�b�g����B
        if (l_execInputInfo.execDetailInfoUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����͏��.���ڍׂ����݂��Ȃ��B");   
        }
        Timestamp l_tsExecutionTimestamp = GtlUtils.getSystemTimestamp(); 

        if (l_execInputInfo.execDetailInfoUnit.executionTimestamp == null)
        {
            l_execInputInfo.execDetailInfoUnit.executionTimestamp = 
                l_tsExecutionTimestamp;
        }
        else
        {
            l_tsExecutionTimestamp = 
                new Timestamp(l_execInputInfo.execDetailInfoUnit.executionTimestamp.getTime());
            //�|�i���͂�����ꍇ�j
            //�@@�������̓��t���A�c�Ɠ��ł��邩�`�F�b�N����B
            //�@@�c�Ɠ��łȂ���΁A��O���X���[����B
            if (!WEB3FeqDateUtility.isFeqBizDate(l_tsExecutionTimestamp))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02149,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�������̓��t���c�Ɠ��ł͂���܂���B"); 
            }
        }

        //�T�j�@@�����͏��.��������敪�̃`�F�b�N
        //�@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.taxType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02113,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������敪��null�ł��B");             
        }
        else
        {
            //�T�|�Q�j��������w��i�����͏��.��������敪 == �h1�F����h�j�̏ꍇ�A
            //    ��������Ŏ���\�����`�F�b�N����B
            //�@@�����}�l�[�W��.validate��������J��()�ɂē�������敪���`�F�b�N����B
            //�@@[validate��������J��()�Ɏw�肷�����]
            //�@@�⏕�����F�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕�����i�ڋq�j
            //�@@�������F�@@�����͏��.���ڍ�.������
            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_execInputInfo.taxType))
            {
                //�⏕�����F�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕�����i�ڋq�j
                //�������F�@@�����͏��.���ڍ�.������
                l_orderManager.validateSpecialAccountEstablish(
                    l_subAccount,
                    l_execInputInfo.execDetailInfoUnit.executionTimestamp);
            }
        }
        
        //�U�j�@@�����͏��.�����R�[�h�^���n�����R�[�h�̃`�F�b�N
        //�@@�U�|�P�j�@@�����͏��.�����R�[�h�^���n�����R�[�h�̗����������͂̏ꍇ�A
        //��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.productCode) 
            && WEB3StringTypeUtility.isEmpty(l_execInputInfo.localProductCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02148,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����͏��.�����R�[�h�ƌ��n�����R�[�h�̗����������͂ł��B"); 
        }
        else
        {
            //�U�|�Q�j�@@���݃`�F�b�N�����{����B
            //�@@�O�������v���_�N�g�}�l�[�W��.get�O����������()�ɂĊO�����������I�u�W�F�N�g���擾����B
            //�@@�擾�ł��Ȃ������ꍇ�A�����R�[�h���͂��s���Ɣ��f���A��O���X���[����B
            //
            //�@@[get�O����������()�Ɏw�肷�����]
            //�@@�،���ЁF�@@�،����
            //�@@�����R�[�h�F�@@�����͏��.�����R�[�h�B�����͂̏ꍇ�͌��n�����R�[�h�B
            String l_strProductCode = l_execInputInfo.productCode;
            if (WEB3StringTypeUtility.isEmpty(l_strProductCode))
            {
                l_strProductCode = l_execInputInfo.localProductCode;
            }
            try
            {
                l_web3FeqProduct = 
                    (WEB3FeqProduct)l_web3FeqProductManager.getFeqProduct(
                        l_institution,
                        l_strProductCode); 
                //�擾�ł��Ȃ������ꍇ�A�����R�[�h���͂��s���Ɣ��f���A��O���X���[����B
                if (l_web3FeqProduct == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�����R�[�h���͂��s���B"); 
                }
                
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����R�[�h���͂��s���B");                 
            }
            
            //�@@�U�|�R�j�@@�����R�[�h�^���n�����R�[�h�^�s��R�[�h�^�ʉ݃R�[�h���Z�b�g����B
            //�����͏��.�����R�[�h�F�@@�O����������.getProductCode()
            l_execInputInfo.productCode = l_web3FeqProduct.getProductCode();
            //�����͏��.���n�����R�[�h�F�@@�O����������.get���n�����R�[�h()
            l_execInputInfo.localProductCode = l_web3FeqProduct.getOffshoreProductCode();
            //�����͏��.�s��R�[�h�F�@@�O����������.get�s��R�[�h()
            l_execInputInfo.marketCode = l_web3FeqProduct.getMarketCode();
            //�����͏��.�ʉ݃R�[�h�F�@@�O����������.get�ʉ݃R�[�h()
            l_execInputInfo.currencyCode = l_web3FeqProduct.getCurrencyCode();
        }
         
        //�V�j�@@�����͏��.���ϋ敪�̃`�F�b�N
        //�@@�V�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.settleDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02111,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ϋ敪��null�ł��B");  
        }
        else
        {
            //�V�|�Q�j�O�݌��ώw��i�����͏��.���ϋ敪 == �h1�F�O�݌��ρh�j�̏ꍇ�A
            //      �O�݌��ω\�����`�F�b�N����B
            //�@@�����}�l�[�W��.validate�O�݌���()�ɂČ��ϋ敪���`�F�b�N����B
            //�@@[validate�O�݌���()�Ɏw�肷�����]
            //�@@�⏕�����F�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕�����i�ڋq�j
            //�@@�ʉ݃R�[�h�F�@@�����͏��.�ʉ݃R�[�h
            if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(
                l_execInputInfo.settleDiv))
            {
                //�⏕�����F�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕����(�ڋq)
                //�ʉ݃R�[�h�F�@@�����͏��.�ʉ݃R�[�h
                l_orderManager.validateFcSettle(
                    l_subAccount,
                    l_execInputInfo.currencyCode);
            }
        }
         
        //�W�j�@@�����͏��.�����敪�̃`�F�b�N
        //�@@�W�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.dealingType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01402,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����敪�����w��ł��B");  
        }
        else
        {
            //�@@�W�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
            if (!WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType)
                && !WEB3BuySellTypeDef.SELL.equals(l_execInputInfo.dealingType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B"); 
            }
        }                 
         
        //�X�j�@@�����P���敪�̃`�F�b�N
        //�X�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00184,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����P���敪�����w��ł��B"); 
        }
        else if (!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_execInputInfo.orderPriceDiv)
            && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_execInputInfo.orderPriceDiv))
        {
            //�X�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00185,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����P���敪�̒l�����݂��Ȃ��R�[�h�l�ł��B"); 
        }

        //�P�O�j�@@�����͏��.���בփ��[�g�̃`�F�b�N
        //�@@�P�O�|�P�j�@@�����͂̏ꍇ
        String l_execExchangeRate = l_execInputInfo.execExchangeRate;

        if (WEB3StringTypeUtility.isEmpty(l_execExchangeRate))
        {
            // �P�O�|�P�|�P�j�ʉ݃R�[�h���擾����B
            String l_strCurrencyCode = l_execInputInfo.currencyCode;
            // �،���ЃR�[�h
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            // �����敪
            String l_strDealingType = l_execInputInfo.dealingType;

            // �P�O�|�P�|�Q�j�i���ʁj�ʉ݃e�[�u�����Y��������בփ��[�g���擾���A�Z�b�g����B
            // -a)�ʉ݃R�[�h�Ə،���ЃR�[�h�ɕR�Â��A�i���ʁj�ʉ݃I�u�W�F�N�g���擾����B
            WEB3GentradeCurrency l_currency =
                WEB3GentradeCurrency.genCurrency(l_strInstitutionCode, l_strCurrencyCode);

            // �i�����敪�@@���@@���@@�̏ꍇ�j�@@���t���בփ��[�g���擾����B
            if (WEB3BuySellTypeDef.BUY.equals(l_strDealingType))
            {
                // -b�Ŏ擾�������בփ��[�g���Z�b�g����B
                l_execInputInfo.execExchangeRate =
                    WEB3StringTypeUtility.formatNumber(l_currency.getBuyExecRate());
            }
            // �i�����敪�@@���@@���@@�̏ꍇ�j�@@���t���בփ��[�g���擾����B
            else if (WEB3BuySellTypeDef.SELL.equals(l_strDealingType))
            {
                // -b�Ŏ擾�������בփ��[�g���Z�b�g����B
                l_execInputInfo.execExchangeRate =
                    WEB3StringTypeUtility.formatNumber(l_currency.getSellExecRate());
            }
        }
        else
        {
            //�P�O�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(l_execExchangeRate))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ���בփ��[�g�����l�łȂ��̏ꍇ�`�F�b�N"); 
            }
            
            //�@@�P�O�|�R�j�@@���l�ɕϊ��������̗L���������A�������R���C�������S���͈̔͊O�ł���΁A
            //��O���X���[����B
            if (WEB3StringTypeUtility.getIntegerDigits(l_execExchangeRate) > 3 || 
                WEB3StringTypeUtility.getFractionDigits(l_execExchangeRate) > 4)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���בփ��[�g�̗L���������A�������R���C�������S���͈̔͊O�ł��B"); 
            }

            // �P�O�|�S�j�@@���l�ɕϊ������l <= 0�ł���΁A��O���X���[����B
            if (Double.parseDouble(l_execExchangeRate) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���בփ��[�g�����l�ɕϊ������l <= 0�ł��B"); 
            }
        }

        //�P�P�j�@@�����͏��.���ڍ�.���P���̃`�F�b�N
        String l_strExecPrice = l_execInputInfo.execDetailInfoUnit.execPrice;
        //�@@�P�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_strExecPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + STR_METHOD_NAME,
                "���P���������͂ł��B"); 
        }

        //�P�P�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����
        //�L�����l�`�F�b�N
        if (!WEB3StringTypeUtility.isNumber(l_strExecPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + STR_METHOD_NAME,
                "���P�������l�ȊO�̒l�ł��B"); 
        }
         
        //�P�P�|�R�j�@@���l�ɕϊ������l <= 0�ł���΁A��O���X���[����B
        if (Double.parseDouble(l_strExecPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + STR_METHOD_NAME,
                "���P�������l�ɕϊ������l <= 0�ł��B"); 
        }
         
        //�P�P�|�S�j�@@���l�ɕϊ��������̗L���������A�������U���C�������T���͈̔͊O�ł���΁A��O���X���[����B
        if (WEB3StringTypeUtility.getIntegerDigits(l_strExecPrice) > 6 
            || WEB3StringTypeUtility.getFractionDigits(l_strExecPrice) > 5)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02024,
                this.getClass().getName() + STR_METHOD_NAME,
                "���P���͐������U���C�������T���͈̔͊O�ł��B"); 
        }
         
        //�P�Q�j�@@�����͏��.���ڍ�.��萔�ʂ̃`�F�b�N
        String l_strExecQuantity = l_execInputInfo.execDetailInfoUnit.execQuantity;
        //�@@�P�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_strExecQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02025,
                this.getClass().getName() + STR_METHOD_NAME,
                "��萔�ʂ������͂ł��B"); 
        }
        //�@@�P�Q�|�Q�j�@@9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(l_strExecQuantity) 
            || WEB3StringTypeUtility.getIntegerDigits(l_strExecQuantity) > 9)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                "��萔�ʂ�9���ȓ��̐����l�ł͂���܂���B"); 
        }
        
        //  �P�Q�|�R�j�@@���l�ɕϊ������l <= 0�ł���΁A��O���X���[����B
        if (Double.parseDouble(l_strExecQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02186,
                this.getClass().getName() + STR_METHOD_NAME,
                "��萔�ʂ����l�ɕϊ������l <= 0�ł��B"); 
        }
         
		//�����敪�����t�̏ꍇ�A��萔�ʂɂ��Ĉȉ��̃`�F�b�N���s���B
		if (WEB3BuySellTypeDef.SELL.equals(l_execInputInfo.dealingType)){
			//����ID���擾����B
			FeqProductParams l_feqProductParams = 
							new FeqProductParams((FeqProductRow)l_web3FeqProduct.getDataSourceObject());
			long l_lngProductId = l_feqProductParams.getProductId();

			//�ŋ敪���擾����B
			TaxTypeEnum l_taxType = 
							WEB3TaxTypeSpecialDef.SPECIAL.equals(l_execInputInfo.taxType)?TaxTypeEnum.SPECIAL:TaxTypeEnum.NORMAL;

			//�ۗL���Y���擾����B
			Asset l_asset = 
					l_web3FeqPositionManager.getAsset(
							l_mainAccountRow.getAccountId(), 
							l_subAccount.getSubAccountId(),
							l_lngProductId, 
							l_taxType);
							
			if(l_asset == null){
				//�ۗL���Y�ɊY������f�[�^���Ȃ��ꍇ�A��O���X���[����B
				log.error("�ۗL���Y�Y���f�[�^�Ȃ��B");
				throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00204,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}else{
				if(!(l_asset.getQuantity() >= Long.parseLong(l_execInputInfo.execDetailInfoUnit.execQuantity))){
					//�i�ۗL���Y�̔��t�\���� >= ��萔�ʁj�łȂ��ꍇ�A��O���X���[����B
					log.error("�ۗL���Y�c���ʃ`�F�b�N�G���[�B");
					throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01931,
					this.getClass().getName() + "." + STR_METHOD_NAME);
				}
			}
		}
         
        //�P�R�j�@@�����͏��.���ڍ�.������n���Z�b�g
        //�@@��n���������͂̏ꍇ�i�����͏��.���ڍ�.������n�� == null�j�A
        //�@@�����̂R�c�Ɠ�����Z�b�g����B
        if (l_execInputInfo.execDetailInfoUnit.deliveryDate == null)
        {
            WEB3GentradeBizDate l_web3GenBizDate = 
                new WEB3GentradeBizDate(l_tsExecutionTimestamp);
            
            l_execInputInfo.execDetailInfoUnit.deliveryDate = l_web3GenBizDate.roll(3);
        }
        
        //�P�S�j�@@�����͏��.���n��n���̃`�F�b�N
        //�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B
        //�@@�|�i�����͂̏ꍇ�j
        if (l_execInputInfo.localDeliveryDate == null)
        {
            l_execInputInfo.localDeliveryDate = 
                l_execInputInfo.execDetailInfoUnit.deliveryDate;
        }
         
        //�P�T�j�@@�������̃`�F�b�N
        //�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B
        //�@@�|�i�����͂̏ꍇ�j
        //�@@�@@�����iTradingSystem.getSystemTimestamp()�̓��t�����j���Z�b�g����B
        if (l_execInputInfo.orderBizDate == null)
        {
            l_execInputInfo.orderBizDate = 
                WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        }
        else
        {
            //�@@�|�i���͂�����ꍇ�j
            //�@@�@@�����iTradingSystem.getSystemTimestamp()�̓��t�����j�̂R�c�Ɠ��O���擾����B
            //�@@�@@�R�c�Ɠ��O���O�̓��t�ł���΁i�����̂R�c�Ɠ��O �� �������j�A��O���X���[����B
            WEB3GentradeBizDate l_web3GenBizDate
                = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
            
            Date l_datBizDate3DayAfter = l_web3GenBizDate.calcFeqBizDate(
                l_institution.getInstitutionCode(),
                l_execInputInfo.marketCode,
                GtlUtils.getSystemTimestamp(),
                -3);
             
            if (WEB3DateUtility.compareToDay(
                l_datBizDate3DayAfter, 
                l_execInputInfo.orderBizDate) > 0) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02150,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���������R�c�Ɠ��O���O�̓��t�ł��B"); 
            }
        }
        
        //�@@�|���������c�Ɠ����`�F�b�N����B 
        //�@@�@@�c�Ɠ��łȂ���΁A��O���X���[����B
        String l_strFlag = WEB3GentradeTradingTimeManagement.getBizDateType(
            new Timestamp(l_execInputInfo.orderBizDate.getTime()));
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                this.getClass().getName() + STR_METHOD_NAME,
                " �������͉c�Ɠ��ł͂���܂���B" + l_execInputInfo.orderBizDate); 
        }
        
        //�P�U�j�@@�V�X�e�������Z�b�g���ڂ̃`�F�b�N
        //�@@�P�U�|�P�j�@@���͒l�`�F�b�N
        //�@@�@@�ȉ��̍��ڂɓ��͂�����ꍇ�inull�łȂ��ꍇ�j�A��O���X���[����B
        //�@@�@@�����͏��.�^�p�R�[�h
        //�@@�@@�����͏��.�`�[�ԍ�
        //�@@�@@�����͏��.��������
        //�@@�@@�����͏��.�����P��
        if (!WEB3StringTypeUtility.isEmpty(l_execInputInfo.managementCode)
            || !WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderNumber)
            || !WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderQuantity)
            || !WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02151,
                this.getClass().getName() + STR_METHOD_NAME,
                "�^�p�R�[�h,�`�[�ԍ�,��������,�����P�����V�X�e�������Z�b�g���ڂȂ̂ŁA���͕s�ł��B"); 
        }
        else
        {
            //�P�U�|�Q�j�@@�l�Z�b�g
            //�ȉ��̒ʂ�A�����͏��̃v���p�e�B�ɒl���Z�b�g����B
            //�����͏��.�^�p�R�[�h�F�@@
            //�O�������^�p�R�[�h�̔ԃT�[�r�X.get�V�K�^�p�R�[�h()
            WEB3FeqOrderEmpCodeManageService l_codeManageService = 
                (WEB3FeqOrderEmpCodeManageService) Services.getService(
                    WEB3FeqOrderEmpCodeManageService.class);
             
            //[get�V�K�^�p�R�[�h()�Ɏw�肷�����] 
            //�s��F�@@�O����������.get�s��()
            //�������F�@@�����͏��.������
            l_execInputInfo.managementCode = l_codeManageService.getNewEmpCode(
                l_web3FeqProduct.getMarket(),
                l_execInputInfo.orderBizDate);
             
            //�����͏��.���ʃR�[�h�F
            //�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()
            //[get�V�K���ʃR�[�h()�Ɏw�肷�����]
            //�،���ЃR�[�h�F�@@�،����.getInstitutionCode()
            //���X�R�[�h�F�@@�����͏��.���X�R�[�h
            //�����^�C�v�F�@@�����^�C�v.�O������
            //�������ʃR�[�h�̔ԃT�[�r�X
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
             
            l_execInputInfo.requestNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_institution.getInstitutionCode(),
                l_execInputInfo.branchCode,
                ProductTypeEnum.FOREIGN_EQUITY);
             
            //�����͏��.�`�[�ԍ��F�@@"9"(WebBroker)�{���ʃR�[�h�̉�3��
            l_execInputInfo.orderNumber = "9"
                + l_execInputInfo.requestNumber.substring(l_execInputInfo.requestNumber.length() - 3);
             
            //�����͏��.�������ʁF�@@�����͏��.���ڍ�.��萔��
            l_execInputInfo.orderQuantity = 
                l_execInputInfo.execDetailInfoUnit.execQuantity;
            //�����͏��.�����P���F�@@�����͏��.���ڍ�.���P��
            l_execInputInfo.orderPrice = 
                l_execInputInfo.execDetailInfoUnit.execPrice;
        }
        
        //�P�V�j ���No.�̍̔�
        int l_intExecSerialNo = 1;
        l_execInputInfo.execDetailInfoUnit.execNo = 
            WEB3StringTypeUtility.formatNumber(l_intExecSerialNo, 3);
         
        //�P�W�j�@@���z���ڂ̃`�F�b�N
        //�@@this.validate�O���������z()���R�[������B
        //�@@[validate�O���������z()�Ɏw�肷�����]
        //�@@�،���ЃR�[�h�F�@@�����P��.�،���ЃR�[�h
        //�@@�⏕�����F�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕�����i�ڋq�j
        //�@@�s��F�@@�O����������.get�s��()
        //�@@�ʉ݁F�@@�O����������.get�ʉ�()
        //�@@�����F�@@�O����������
        //�@@�ŋ敪�F�@@�����͏��.��������敪�ɑΉ�����ŋ敪�iTaxTypeEnum�j
        //�@@�����͏��F�@@�����͏��
        validateFeqAmount(
            l_institution.getInstitutionCode(),
            l_subAccount,
            l_web3FeqProduct.getMarket(),
            l_web3FeqProduct.getCurrency(),
            l_web3FeqProduct,
            WEB3FeqOrderUtility.getTaxType(l_execInputInfo.taxType),
            l_execInputInfo);
                 
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�O���������z)<BR>
     * �O���������z���ړ��̓`�F�b�N���s���B<BR>
     * �ȉ��ɋL�q����L���͈͊O�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�j�@@��������̌v�Z<BR>
     * �@@�ȉ��̒ʂ蔄��������v�Z���A�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�����͏��.���ڍ�.��������F�@@�����͏��.���ڍ�.���P�� �~ <BR>
     * �����͏��.���ڍ�.��芔��<BR>
     * <BR>
     * �Q�j�@@���n���o��̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * <BR>
     * �@@�|�i���͂�����ꍇ�j�ȉ��Ɏ����L�������͈͓��̐��l�ł��邱�Ƃ��`�F�b�N����B<BR>
     * �@@�@@�����͏��.���ڍ�.���n�萔��<BR>
     * �@@�@@�@@�������F�@@9���ȓ�<BR>
     * �@@�@@�@@�������F�@@�L������(*1)�ȓ�<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02152<BR>
     * <BR>
     * �@@�@@�����͏��.���ڍ�.���n�����<BR>
     * �@@�@@�@@�������F�@@9���ȓ�<BR>
     * �@@�@@�@@�������F�@@�L������(*1)�ȓ�<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02153<BR>
     * <BR>
     * �@@�@@�����͏��.���ڍ�.���̑��R�X�g�P<BR>
     * �@@�@@�@@�������F�@@7���ȓ�<BR>
     * �@@�@@�@@�������F�@@�L������(*1)�ȓ�<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02154<BR>
     * <BR>
     * �@@�@@�����͏��.���ڍ�.���̑��R�X�g�Q<BR>
     * �@@�@@�@@�������F�@@7���ȓ�<BR>
     * �@@�@@�@@�������F�@@�L������(*1)�ȓ�<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02155<BR>
     * <BR>�@@�@@
     * �@@�|�i�����͂̏ꍇ�j�O�������v�Z�T�[�r�X.calc���n���o��()�ɂĊe�R�X�g���v�Z���A<BR>
     * �����͏��.���ڍׂ̊e���ڂɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@[calc���n���o��()�Ɏw�肷�����]<BR>
     * �@@�@@����ID�F�@@����.getProductId()<BR>
     * �@@�@@���n�����R�[�h�F�@@����.get���n�����R�[�h()<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�،���ЃR�[�h<BR>
     * �@@�@@�s��R�[�h�F�@@�����͏��.�s��R�[�h<BR>
     * �@@�@@��������i�O�݁j�F�@@�����͏��.���ڍ�.�������<BR>
     * �@@�@@����F�@@�����͏��.����<BR>
     * �@@�@@is���t�F�@@�i�����͏��.�����敪 == �h���t�h�j�̏ꍇtrue�A�ȊOfalse�B<BR>
     * <BR>�@@�@@
     * �@@(*1) �e�L�������ɂ���<BR>
     * �@@�C�O���o��}�X�^�s�̂����A���o��敪�ɊY������s���擾����B<BR>
     * �@@�Ή����鏔�o��敪�̍s���Ȃ��ꍇ�́A�`�F�b�N�Ȃ��B<BR>
     * <BR>
     * �@@[�C�O���o��}�X�^�̎擾]<BR>
     * �@@�O�������v�Z�T�[�r�X.get�C�O���o��}�X�^()�ɂāA�C�O���o��}�X�^�s�iParams�j��<BR>
     * �擾����B<BR>
     * <BR>
     * �@@[get�C�O���o��}�X�^()�Ɏw�肷�����]<BR>
     * �@@����ID�F�@@����.getProductId()<BR>
     * �@@���n�����R�[�h�F�@@����.get���n�����R�[�h() <BR>
     * �@@�،���ЃR�[�h�F�@@�،���ЃR�[�h<BR>
     * �@@�s��R�[�h�F�@@�����͏��.�s��R�[�h<BR>
     * �@@��������F�@@�����͏��.���ڍ�.�������<BR>
     * �@@����F�@@�����͏��.���ڍ�.������<BR>
     * �@@�@@�������͂̏ꍇ�A�����iTradingSystem.getSystemTimestamp()�̓��t�����j<BR>
     * �@@���o��敪�F�@@null<BR>
     * �@@�����敪�F�@@�����͏��.�����敪<BR>
     * <BR>
     * �R�j�@@�����͏��.���ڍ�.���Z����̌v�Z<BR>
     * �@@�ȉ��̒ʂ�A���Z����i�O�݁j�^�i�~�݁j���v�Z���A�l���Z�b�g����B<BR>
     * <BR>
     * �@@�����͏��.���ڍ�.���Z����i�O�݁j�F<BR>
     * �@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j<BR>
     * �@@�@@�@@���n���Z����i�O�݁j = <BR>
     * ��������i�O�݁j�{���n�萔���{���n����Ł{���̑��R�X�g�P�{���̑��R�X�g�Q<BR>
     * <BR>
     * �@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j<BR>
     * �@@�@@�@@���n���Z����i�O�݁j = <BR>
     * ��������i�O�݁j�|���n�萔���|���n����Ł|���̑��R�X�g�P�|���̑��R�X�g�Q<BR>
     * <BR>
     * �@@�����͏��.���ڍ�.���Z����i�~�݁j�F<BR>
     * �@@�@@�O�������v�Z�T�[�r�X.calc�~�݊��Z()�ɂĐ��Z����i�O�݁j��M�݊��Z�����l�B<BR>
     * <BR>
     * �@@�@@[calc�~�݊��Z()�Ɏw�肷�����]<BR>
     * �@@�@@���z�i�O�݁j�F�@@�����͏��.���ڍ�.���Z����i�O�݁j<BR>
     * �@@�@@���[�g�F�@@�����͏��.���בփ��[�g<BR>
     * �@@�@@�~�݊��Z�ۂߕ����F�@@�ʉ�.get�~�݊��Z�ۂߕ���()<BR>
     * <BR>
     * �S�j�@@�����͏��.���ڍ�.�����萔���i�~�݁j�̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * <BR>
     * �@@�|�i���͂�����ꍇ�j�ȉ��Ɏ����L�������͈͓��̐��l�ł��邱�Ƃ��`�F�b�N����B<BR>
     * �@@�@@�������F�@@9���ȓ�<BR>
     * �@@�@@�������F�@@�Ȃ�<BR>
     * <BR>
     * �@@�|�i�����͂̏ꍇ�j�O�������v�Z�T�[�r�X.calc�ϑ��萔��()�ɂČv�Z���A<BR>
     * �����͏��.���ڍׂ̍��ڂɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@[calc�ϑ��萔��()�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����F�@@�⏕����<BR>
     * �@@�@@�s��F�@@�s��<BR>
     * �@@�@@����F�@@�����͏��.���ڍ�.������<BR>
     * �@@�@@is�w�l�����F�@@�i�����͏��.�����P���敪 == �h�w�l�h�j�̏ꍇtrue�A
     * <BR>�ȊOfalse�B<BR>
     * �@@�@@���n���Z����i�~�݁j�F�@@�����͏��.���ڍ�.���Z����i�~�݁j<BR>
     * �@@�@@�����`���l���F <BR>
     *   �|HOST�����̏ꍇ�i�����͏��.����ID�������́j <BR>
     *      "�c�ƓX"���Z�b�g <BR>
     *   �|��L�ȊO�̏ꍇ <BR>
     *      �����͏��.����ID�ɊY�����钍���P��.���񒍕��̒����`���l�����Z�b�g�B<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�����͏��.���ڍ�.�����萔���i�O�݁j�l�Z�b�g<BR>
     * �@@�@@�ȉ��̒ʂ�A�����萔���i�O�݁j���v�Z���A�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�����͏��.���ڍ�.�����萔���i�O�݁j�F<BR>
     * �@@�@@�@@�O�������v�Z�T�[�r�X.calc�O�݊��Z()�ɂč����萔���i�~�݁j���O�݊��Z�����l�B<BR>
     * <BR>
     * �@@�@@�@@[calc�O�݊��Z()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���z�i�~�݁j�F�@@�����͏��.���ڍ�.�����萔���i�~�݁j<BR>
     * �@@�@@�@@���[�g�F�@@�����͏��.���בփ��[�g<BR>
     * �@@�@@�@@�����������F�@@�ʉ�.get����������()<BR>
     * �@@�@@�@@�O�݊��Z�ۂߕ����F�@@�ʉ�.get�O�݊��Z�ۂߕ���()<BR>
     * <BR>
     * �T�j�@@�����͏��.���ڍ�.����Łi�~�݁j�̃`�F�b�N<BR>
     * �@@�T�|�P�j�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B<BR>
     * <BR>
     * �@@�|�i���͂�����ꍇ�j�ȉ��Ɏ����L�������͈͓��̐��l�ł��邱�Ƃ��`�F�b�N����B<BR>
     * �@@�@@�������F�@@9���ȓ�<BR>
     * �@@�@@�������F�@@�Ȃ�<BR>
     * <BR>
     * �@@�|�i�����͂̏ꍇ�j�O�������v�Z�T�[�r�X.calc�����()�ɂČv�Z���A<BR>
     * �����͏��.���ڍׂ̍��ڂɃZ�b�g����B<BR>
     * �@@�@@[calc�����()�Ɏw�肷�����]<BR>
     * �@@�@@���z�F�@@�����͏��.���ڍ�.�����萔���i�~�݁j<BR>
     * �@@�@@����F�@@�����͏��.���ڍ�.������<BR>
     * �@@�@@�⏕�����F�@@�⏕����<BR>
     * <BR>
     * �@@�T�|�Q�j�@@�����͏��.���ڍ�.����Łi�O�݁j�l�Z�b�g<BR>
     * �@@�@@�ȉ��̒ʂ�A�����萔���i�O�݁j���v�Z���A�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�����͏��.���ڍ�.�����萔���i�O�݁j�F<BR>
     * �@@�@@�@@�O�������v�Z�T�[�r�X.calc�O�݊��Z()�ɂď���Łi�~�݁j���O�݊��Z�����l�B<BR>
     * <BR>
     * �@@�@@�@@[calc�O�݊��Z()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���z�i�~�݁j�F�@@�����͏��.���ڍ�.����Łi�~�݁j<BR>
     * �@@�@@�@@���[�g�F�@@�����͏��.���בփ��[�g<BR>
     * �@@�@@�@@�����������F�@@�ʉ�.get����������()<BR>
     * �@@�@@�@@�O�݊��Z�ۂߕ����F�@@�ʉ�.get�O�݊��Z�ۂߕ���()<BR>
     * <BR>
     * �U�j�@@��n����̌v�Z<BR>
     * �@@�ȉ��̒ʂ�A��n����i�O�݁j�^�i�~�݁j���v�Z���A�l���Z�b�g����B<BR>
     * <BR>
     * �@@�����͏��.���ڍ�.��n����i�~�݁j�F<BR>
     * �@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j<BR>
     * �@@�@@�@@��n����i�~�݁j = ���Z����i�~�݁j�{�����萔���i�~�݁j�{����Łi�~�݁j<BR>
     * <BR>
     * �@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j<BR>
     * �@@�@@�@@��n����i�~�݁j = ���Z����i�~�݁j�|�����萔���i�~�݁j�|����Łi�~�݁j<BR>
     * <BR>
     * �@@�����͏��.���ڍ�.��n����i�O�݁j�F<BR>
     * �@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j<BR>
     * �@@�@@�@@��n����i�O�݁j = ���Z����i�O�݁j�{�����萔���i�O�݁j�{����Łi�O�݁j<BR>
     * <BR>
     * �@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j<BR>
     * �@@�@@�@@��n����i�O�݁j = ���Z����i�O�݁j�|�����萔���i�O�݁j�|����Łi�O�݁j<BR>
     * <BR>
     * �V�j�@@���n�v�^���n�v�ł̌v�Z<BR>
     * �@@�ȉ��̒ʂ�A���n�v�^���n�v�ł��v�Z���A�l���Z�b�g����B<BR>
     * <BR>
     * �@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j<BR>
     * �@@�@@�����͏��.���n�v�F�@@0<BR>
     * �@@�@@�����͏��.���n�v�ŁF�@@0<BR>
     * <BR>
     * �@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j�A<BR>
     * �@@�@@�ȉ��̌v�Z���ʁB<BR>
     * <BR>
     * �@@�@@�����͏��.���n�v�F<BR>
     * �@@�@@�@@�O�������v�Z�T�[�r�X.calc���n���v()�ɂČv�Z�����l�B<BR>
     * <BR>
     * �@@�@@�@@[calc���n���v()�Ɏw�肷�����]<BR>
     * �@@�@@�@@��������i�~�݁j�F�@@�����͏��.���ڍ�.��n����i�~�݁j<BR>
     * �@@�@@�@@�������F�@@�����͏��.���ڍ�.��萔��<BR>
     * �@@�@@�@@�����h�c�F�@@����.getProductId()<BR>
     * �@@�@@�@@�⏕�����F�@@�⏕����<BR>
     * �@@�@@�@@�ŋ敪�F�@@�ŋ敪<BR>
     * <BR>
     * �@@�@@�����͏��.���n�v�ŁF<BR>
     * �@@�@@�@@�O�������v�Z�T�[�r�X.calc���n�v��()�ɂČv�Z�����l�B<BR>
     * <BR>
     * �@@�@@�@@[calc���n�v��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�⏕�����F�@@�⏕����<BR>
     * �@@�@@�@@�ŋ敪�F�@@�ŋ敪<BR>
     * �@@�@@�@@��������i�~�݁j�F�@@�����͏��.���n�v<BR>
     * �@@�@@�@@��n���F�@@�����͏��.���ڍ�.������n��<BR>
     * �@@
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g<BR>
     * @@param l_currency - (�ʉ�)<BR>
     * �ʉ݃I�u�W�F�N�g<BR>
     * @@param l_product - (����)<BR>
     * �����I�u�W�F�N�g<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * @@param l_execInputInfo - (�����͏��)<BR>
     * �O�����������͏��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B66A3F009C
     */
    private void validateFeqAmount(
        String l_strInstitutionCode, 
        WEB3GentradeSubAccount l_subAccount, 
        WEB3GentradeMarket l_market, 
        WEB3GentradeCurrency l_currency, 
        WEB3FeqProduct l_product, 
        TaxTypeEnum l_taxType, 
        WEB3FeqOrderAndExecutionUnit l_execInputInfo)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateFeqAmount(String, " +
            "WEB3GentradeSubAccount, WEB3GentradeCurrency, WEB3FeqProduct, " + 
            "TaxTypeEnum, WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || 
            l_market == null ||
            l_product == null || 
            l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�[�����w��(null)�ł��B");  
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();

        //�P�j�@@��������̌v�Z
        //�ȉ��̒ʂ蔄��������v�Z���A�l���Z�b�g����B
        //�����͏��.���ڍ�.��������F�@@�����͏��.���ڍ�.���P�� �~ �����͏��.���ڍ�.��芔��
        double l_dblTradePrice = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice) * Double.parseDouble(
                l_execInputInfo.execDetailInfoUnit.execQuantity);
        
        int l_intDecimalPlace = l_currency.getScale();
        BigDecimal l_bdTradePrice = new BigDecimal(l_dblTradePrice);
        l_bdTradePrice =
            l_bdTradePrice.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblTradePrice = l_bdTradePrice.doubleValue();

        l_execInputInfo.execDetailInfoUnit.foreignTradePrice = 
            WEB3StringTypeUtility.formatNumber(l_dblTradePrice);
        
        //���n�萔��
        String l_strLocalCommission = 
            l_execInputInfo.execDetailInfoUnit.localCommission;
        //���n�����
        String l_strLocalTradingTax = 
            l_execInputInfo.execDetailInfoUnit.localTradingTax;        
        //���̑��R�X�g�P
        String l_strOtherCost1 = 
            l_execInputInfo.execDetailInfoUnit.otherCost1;
        //���̑��R�X�g�Q
        String l_strOtherCost2 = 
            l_execInputInfo.execDetailInfoUnit.otherCost2;
        //���בփ��[�g
        double l_dblExecExchangeRate = 
            Double.parseDouble(l_execInputInfo.execExchangeRate);
        
        double l_dblFDoubleoreignTradePrice = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignTradePrice);
        
        //�Q�j�@@���n���o��̃`�F�b�N
        //�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B
        //�@@���͂��Ȃ��ꍇ�͊O�������v�Z�T�[�r�X.calc���n���o��()�ɂĊe�R�X�g���v�Z���A
        //�@@�����͏��.���ڍׂ̊e���ڂɃZ�b�g����B
        //
        //�����͏��.���ڍׂ̊e���ڂɃZ�b�g����B
        //�@@�@@[calc���n���o��()�Ɏw�肷�����]
        //    ����ID�F�@@����.getProductId()
        //    �،���ЃR�[�h�F�@@�،���ЃR�[�h
        //    �s��R�[�h�F�@@�����͏��.�s��R�[�h
        //    ��������i�O�݁j�F�@@�����͏��.���ڍ�.�������
        //    ����F�@@�����͏��.����
        //    is���t�F�@@�i�����͏��.�����敪 == �h���t�h�j�̏ꍇtrue�A�ȊOfalse�B
        //���n���o��
        WEB3FeqForeignCost l_web3FeqBalanceCost =  
            l_bizLogicProvider.calcForeignCost(
                new Long(l_product.getProductId()),
                l_product.getOffshoreProductCode(),
                l_strInstitutionCode,
                l_market.getMarketCode(),
                l_dblFDoubleoreignTradePrice,
                l_execInputInfo.execDetailInfoUnit.executionTimestamp,
                (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType) ? true : false));            


        //�L���������擾
        //�@@(*1) �e�L�������ɂ��� 
        //�C�O���o��}�X�^�s�̂����A���o��敪�ɊY������s���擾����B 
        //�Ή����鏔�o��敪�̍s���Ȃ��ꍇ�́A�`�F�b�N�Ȃ��B
        //�@@[�C�O���o��}�X�^�̎擾] 
        //�O�������v�Z�T�[�r�X.get�C�O���o��}�X�^()�ɂāA
        // �C�O���o��}�X�^�s�iParams�j���擾����B                        
        Date l_datBaseDate = 
            l_execInputInfo.execDetailInfoUnit.executionTimestamp;
        if (l_datBaseDate == null)
        {
            l_datBaseDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        }
            
        //[get�C�O���o��}�X�^()�Ɏw�肷�����] 
        //����ID�F�@@����.getProductId()<BR>
        //�،���ЃR�[�h�F�@@�،���ЃR�[�h 
        //�s��R�[�h�F�@@�����͏��.�s��R�[�h 
        //��������F�@@�����͏��.���ڍ�.������� 
        //����F�@@�����͏��.���ڍ�.������ 
        //�@@�������͂̏ꍇ�A�����iTradingSystem.getSystemTimestamp()�̓��t�����j 
        //���o��敪�F�@@null 
        //�����敪�F�@@�����͏��.�����敪
        ForeignCostParams[] l_foreignCostParams  = 
            l_bizLogicProvider.getForeignCost(
                new Long(l_product.getProductId()),
                l_product.getOffshoreProductCode(),
                l_strInstitutionCode,
                l_execInputInfo.marketCode,
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignTradePrice),
                l_datBaseDate,
                null,
                l_execInputInfo.dealingType);
            
        //���n�萔���̗L������
        int l_intScaleLocalCommission = 0;
        boolean l_blnIsCheckLocalCommission = false;
            
        //���n����ł̗L������
        int l_intScaleLocalTradingTax = 0;
        boolean l_blnIsCheckLocalTradingTax = false;
            
        //���̑��R�X�g�P�̗L������
        int l_intScaleOtherCost1 = 0;     
        boolean l_blnIsCheckOtherCost1 = false;
            
        //���̑��R�X�g�Q�̗L������
        int l_intScaleOtherCost2 = 0;
        boolean l_blnIsCheckOtherCost2 = false;            
            
        //�O�������v�Z�T�[�r�X.get�C�O���o��}�X�^()�̖߂�l��loop
        if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
        {
            for (int i = 0; i < l_foreignCostParams.length; i++)
            {
                if (WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    //���o��敪��"���n�萔��"�̃f�[�^������̏ꍇ�B
                    l_intScaleLocalCommission = l_foreignCostParams[i].getScale();
                    l_blnIsCheckLocalCommission = true;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_TAX.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    //���o��敪��"���n�����"�̃f�[�^������̏ꍇ�B
                    l_intScaleLocalTradingTax = l_foreignCostParams[i].getScale();
                    l_blnIsCheckLocalTradingTax = true;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    //���o��敪��"���̑��R�X�g�P"�̃f�[�^������̏ꍇ�B
                    l_intScaleOtherCost1 = l_foreignCostParams[i].getScale();
                    l_blnIsCheckOtherCost1 = true;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    //���o��敪��"���̑��R�X�g�P"�̃f�[�^������̏ꍇ�B
                    l_intScaleOtherCost2 = l_foreignCostParams[i].getScale();
                    l_blnIsCheckOtherCost2 = true;
                }
            }
        }            

        //�@@�@@�����͏��.���ڍ�.���n�萔��
        //�@@�@@�@@�������F�@@9���ȓ�
        //�@@�@@�@@�������F�@@�L������(*1)�ȓ�        
        if (l_execInputInfo.execDetailInfoUnit.localCommission != null)
        {
            if (WEB3StringTypeUtility.getIntegerDigits(l_strLocalCommission) > 9
                || (l_blnIsCheckLocalCommission && 
                    WEB3StringTypeUtility.getFractionDigits(l_strLocalCommission) >  
                        l_intScaleLocalCommission))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02152,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���n�萔�����L�������͈͊O�ł��B"); 
            }
        }
        else
        {
            //�����͏��.���ڍ�.���n�萔���ɃZ�b�g����B
            l_execInputInfo.execDetailInfoUnit.localCommission = 
                WEB3StringTypeUtility.formatNumber(l_web3FeqBalanceCost.getForeignCommissionFee());

            //���n�萔��
            l_strLocalCommission = 
                l_execInputInfo.execDetailInfoUnit.localCommission;
        }

        if (l_execInputInfo.execDetailInfoUnit.localTradingTax != null)
        {
            //�@@�@@�����͏��.���ڍ�.���n�����
            //�@@�@@�@@�������F�@@9���ȓ�
            //�@@�@@�@@�������F�@@�L������(*1)�ȓ�
            if (l_execInputInfo.execDetailInfoUnit.localTradingTax != null)
            {
                if (WEB3StringTypeUtility.getIntegerDigits(l_strLocalTradingTax) > 9
                    || (l_blnIsCheckLocalTradingTax && 
                        WEB3StringTypeUtility.getFractionDigits(l_strLocalTradingTax) > 
                            l_intScaleLocalTradingTax))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02153,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���n����ł��L�������͈͊O�ł��B"); 
                }
            }            
        }
        else
        {
            //�����͏��.���ڍ�.���n����łɃZ�b�g����B
            l_execInputInfo.execDetailInfoUnit.localTradingTax = 
                WEB3StringTypeUtility.formatNumber(l_web3FeqBalanceCost.getForeignTax());

            //���n�����
            l_strLocalTradingTax = 
                l_execInputInfo.execDetailInfoUnit.localTradingTax;
        }
        
        //�@@�@@�����͏��.���ڍ�.���̑��R�X�g�P
        //�@@�@@�@@�������F�@@7���ȓ�
        //�@@�@@�@@�������F�@@�L������(*1)�ȓ�
        if (l_execInputInfo.execDetailInfoUnit.otherCost1 != null)
        {                
            if (WEB3StringTypeUtility.getIntegerDigits(l_strOtherCost1) > 7
                || (l_blnIsCheckOtherCost1 && 
                    WEB3StringTypeUtility.getFractionDigits(l_strOtherCost1) > 
                        l_intScaleOtherCost1))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02154,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���̑��R�X�g�P���L�������͈͊O�ł��B"); 
            }
        }
        else
        {
            //�����͏��.���ڍ�.���n����łɃZ�b�g����B
            l_execInputInfo.execDetailInfoUnit.otherCost1 = 
                WEB3StringTypeUtility.formatNumber(l_web3FeqBalanceCost.getForeignFeeExt1());

            //���̑��R�X�g�P
            l_strOtherCost1 = 
                l_execInputInfo.execDetailInfoUnit.otherCost1;
        }
            
        //�@@�@@�����͏��.���ڍ�.���̑��R�X�g�Q
        //�@@�@@�@@�������F�@@7���ȓ�
        //�@@�@@�@@�������F�@@�L������(*1)�ȓ�
        if (l_execInputInfo.execDetailInfoUnit.otherCost2 != null)
        {                
            if (WEB3StringTypeUtility.getIntegerDigits(l_strOtherCost2) > 7
                || (l_blnIsCheckOtherCost2 && 
                    WEB3StringTypeUtility.getFractionDigits(l_strOtherCost2) > 
                        l_intScaleOtherCost2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02155,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���̑��R�X�g�Q���L�������͈͊O�ł��B"); 
            }
        }
        else
        {
            //�����͏��.���ڍ�.���n����łɃZ�b�g����B
            l_execInputInfo.execDetailInfoUnit.otherCost2 = 
                WEB3StringTypeUtility.formatNumber(l_web3FeqBalanceCost.getForeignFeeExt2());

            //���̑��R�X�g�Q
            l_strOtherCost2 = 
                l_execInputInfo.execDetailInfoUnit.otherCost2;
        }


        //���n�萔��
        double l_dblLocalCommission = 0.0D;
        if (l_strLocalCommission != null)
        {
            l_dblLocalCommission = Double.parseDouble(l_strLocalCommission);
        }
        
        //���n�����
        double l_dblLocalTradingTax = 0.0D;
        if (l_strLocalTradingTax != null)
        {
            l_dblLocalTradingTax = Double.parseDouble(l_strLocalTradingTax);
        }
        
        //���̑��R�X�g�P
        double l_dblOtherCost1 = 0.0D;
        if (l_strOtherCost1 != null)
        {
            l_dblOtherCost1 = Double.parseDouble(l_strOtherCost1);
        }
        
        //���̑��R�X�g�Q
        double l_dblOtherCost2 = 0.0D;
        if (l_strOtherCost2 != null)
        {
            l_dblOtherCost2 = Double.parseDouble(l_strOtherCost2);
        }
        
        //�R�j�@@�����͏��.���ڍ�.���Z����̌v�Z
        //�@@�ȉ��̒ʂ�A���Z����i�O�݁j/�i�~�݁j���v�Z���A�l���Z�b�g����B
        //�@@�����͏��.���ڍ�.���Z����i�O�݁j�F
        double l_foreignClearUpPrice = 0.0D;
        //���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j
        //�@@�@@���n���Z����i�O�݁j = ��������i�O�݁j�{���n�萔���{���n����Ł{���̑��R�X�g�P�{���̑��R�X�g�Q
        if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
        {
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblFDoubleoreignTradePrice,l_dblLocalCommission);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalPlus(l_foreignClearUpPrice,l_dblLocalTradingTax);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalPlus(l_foreignClearUpPrice,l_dblOtherCost1);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalPlus(l_foreignClearUpPrice,l_dblOtherCost2);
        }
        else
        {
            //���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j
            //�@@�@@���n���Z����i�O�݁j = ��������i�O�݁j�|���n�萔���|���n����� �|���̑��R�X�g�P�|���̑��R�X�g�Q
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblFDoubleoreignTradePrice,l_dblLocalCommission);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalMinus(l_foreignClearUpPrice,l_dblLocalTradingTax);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalMinus(l_foreignClearUpPrice,l_dblOtherCost1);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalMinus(l_foreignClearUpPrice,l_dblOtherCost2);
        }
        //�@@�����͏��.���ڍ�.���Z����i�O�݁j 
        l_execInputInfo.execDetailInfoUnit.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_foreignClearUpPrice);
        
        //�@@�����͏��.���ڍ�.���Z����i�~�݁j�F
        //�@@�@@�O�������v�Z�T�[�r�X.calc�~�݊��Z()�ɂĐ��Z����i�O�݁j��M�݊��Z�����l�B
        //�@@�@@[calc�~�݊��Z()�Ɏw�肷�����]
        //    ���z(�O��):�����͏��.���ڍ�.���Z����i�O�݁j
        //    ���[�g�F�@@�����͏��.���בփ��[�g
        //    �~�݊��Z�ۂߕ����F�@@�ʉ�.get�~�݊��Z�ۂߕ���()
        double l_dblClearUpPrice = 
            l_bizLogicProvider.calcJPYAmount(
                l_foreignClearUpPrice,
                l_dblExecExchangeRate,
                l_currency.getChangeJpyRoundDiv());        
        l_execInputInfo.execDetailInfoUnit.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblClearUpPrice);
        
        //�S�j�@@�����͏��.���ڍ�.�����萔���i�~�݁j�̃`�F�b�N
        //�@@�S�|�P�j�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B
        //�@@�|�i���͂�����ꍇ�j�ȉ��Ɏ����L�������͈͓��̐��l�ł��邱�Ƃ��`�F�b�N����B
        //�@@�@@�������F�@@9���ȓ�
        //�@@�@@�������F�@@�Ȃ�
        if  (l_execInputInfo.execDetailInfoUnit.commission != null)
        {
            String l_strCommission = l_execInputInfo.execDetailInfoUnit.commission;
            //�L�����l�`�F�b�N
            if (!WEB3StringTypeUtility.isInteger(l_strCommission) 
                || WEB3StringTypeUtility.getIntegerDigits(l_strCommission) > 9)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02189,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����萔���i�~�݁j���L�������͈͊O�ł��B"); 
            }
        }
        else        
        {
            //�|�i�����͂̏ꍇ�j�O�������v�Z�T�[�r�X.calc�ϑ��萔��()�ɂČv�Z���A
            //�����͏��.���ڍׂ̍��ڂɃZ�b�g����B
            
            //[calc�ϑ��萔��()�Ɏw�肷�����] 
            //�⏕�����F�@@�⏕����
            //�s��F�@@�s��
            //����F�@@�����͏��.���ڍ�.������
            //is���t�F�@@�i�����͏��.�����敪 == �h���t�h�j�̏ꍇ�Atrue�A�ȊOfalse�B 
            //is�w�l�����F�i�����͏��.�����P���敪 == �h�w�l�h�j�̏ꍇtrue�A�ȊOfalse�B
            //���n���Z����i�~�݁j�F�@@�����͏��.���ڍ�.���Z����i�~�݁j 
            //�����`���l���F 
            // �|HOST�����̏ꍇ�i�����͏��.����ID�������́j 
            // "�c�ƓX"���Z�b�g 
            // �|��L�ȊO�̏ꍇ 
            // �����͏��.����ID�ɊY�����钍���P��.���񒍕��̒����`���l�����Z�b�g�B  
            String l_strOrderChannel = null;
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderId))
            {
                l_strOrderChannel = WEB3ChannelDef.BRANCH;
            }
            else
            {
                WEB3FeqOrderManager l_orderMgr = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();                
                
                WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_orderMgr.getOrderUnitByOrderId(
                    Long.parseLong(l_execInputInfo.orderId));
                    
                if (l_orderUnit == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                         this.getClass().getName() + STR_METHOD_NAME,
                        "�����P�ʂ����݂��Ȃ��B");
                }
                
                FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                
                if (l_orderUnitRow == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                         this.getClass().getName() + STR_METHOD_NAME,
                        "�����P�ʍs�����݂��Ȃ��B");
                }
                
                l_strOrderChannel = l_orderUnitRow.getOrderChanel();
            }
            WEB3GentradeCommission l_web3GentradeCommission = 
                l_bizLogicProvider.calcCommission(
                    l_subAccount,
                    l_market,
                    l_execInputInfo.execDetailInfoUnit.executionTimestamp,
                    (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType)
                        ? true : false),
                    (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(
                        l_execInputInfo.orderPriceDiv) ? true : false),
                    l_dblClearUpPrice,
                    l_strOrderChannel);
            
            l_execInputInfo.execDetailInfoUnit.commission = 
                WEB3StringTypeUtility.formatNumber(l_web3GentradeCommission.getCommission());
        }
        
        //�S�|�Q�j�@@�����͏��.���ڍ�.�����萔���i�O�݁j�l�Z�b�g
        //�ȉ��̒ʂ�A�����萔���i�O�݁j���v�Z���A�l���Z�b�g����B
        //�����͏��.���ڍ�.�����萔���i�O�݁j�F
        //�O�������v�Z�T�[�r�X.calc�O�݊��Z()�ɂč����萔���i�~�݁j���O�݊��Z�����l�B
        //
        //�@@[calc�O�݊��Z()�Ɏw�肷�����] 
        //���z�i�~�݁j�F�����͏��.���ڍ�.�����萔���i�~�݁j
        //���[�g�F�@@�����͏��.���בփ��[�g
        //�����������F�@@�ʉ�.get����������()
        //�O�݊��Z�ۂߕ����F�@@�ʉ�.get�O�݊��Z�ۂߕ���()
        
        double l_dblForeignCommission = 
            l_bizLogicProvider.calcForeignCCYAmount(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.commission),
                l_dblExecExchangeRate,
                l_currency.getScale(),
                l_currency.getChangeFCcyRoundDiv());
        
        l_execInputInfo.execDetailInfoUnit.foreignCommission = 
            WEB3StringTypeUtility.formatNumber(l_dblForeignCommission);
        
        //�T�j�@@�����͏��.���ڍ�.����Łi�~�݁j�̃`�F�b�N
        //�@@�T�|�P�j�@@���͂�����ꍇ�͓��͒l�̃`�F�b�N�A�����͂̏ꍇ�͒l�Z�b�g���s���B
        if (l_execInputInfo.execDetailInfoUnit.consumptionTax != null)
        {
            //�@@�|�i���͂�����ꍇ�j�ȉ��Ɏ����L�������͈͓��̐��l�ł��邱�Ƃ��`�F�b�N����B
            //�@@�@@�������F�@@9���ȓ�
            //�@@�@@�������F�@@�Ȃ�
            String l_strConsumptionTax = l_execInputInfo.execDetailInfoUnit.consumptionTax;
            
            //�L�����l�`�F�b�N
            if (!WEB3StringTypeUtility.isInteger(l_strConsumptionTax) 
                || WEB3StringTypeUtility.getIntegerDigits(l_strConsumptionTax) > 9)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02190,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "����Łi�~�݁j���L�������͈͊O�ł��B"); 
            }
        }
        else
        {
            //�@@�|�i�����͂̏ꍇ�j�O�������v�Z�T�[�r�X.calc�����()�ɂČv�Z���A
            //�����͏��.���ڍׂ̍��ڂɃZ�b�g����B
            
            //[calc�����()�Ɏw�肷�����] 
            //���z�F�����͏��.���ڍ�.�����萔���i�~�݁j
            //����F�����͏��.���ڍ�.������
            //�⏕�����F�⏕����
            double l_dblConsumptionTax = 
                l_bizLogicProvider.calcSalesTax(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.commission),
                    l_execInputInfo.execDetailInfoUnit.executionTimestamp,
                    l_subAccount);
            
            l_execInputInfo.execDetailInfoUnit.consumptionTax = 
                WEB3StringTypeUtility.formatNumber(l_dblConsumptionTax);
        }
        
        //�@@�T�|�Q�j�@@�����͏��.���ڍ�.����Łi�O�݁j�l�Z�b�g
        //�@@�@@�ȉ��̒ʂ�A�����萔���i�O�݁j���v�Z���A�l���Z�b�g����B
        //�@@�@@�@@�O�������v�Z�T�[�r�X.calc�O�݊��Z()�ɂď���Łi�~�݁j���O�݊��Z�����l�B
        //      ���z�i�~�݁j�F�����͏��.���ڍ�.����Łi�~�݁j
        //      ���[�g�F�����͏��.���בփ��[�g
        //      �����������F�ʉ�.get����������()
        //      �O�݊��Z�ۂߕ����F�ʉ�.get�O�݊��Z�ۂߕ���()
        double l_dblForeignConsumptionTax
        = l_bizLogicProvider.calcForeignCCYAmount(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.consumptionTax),
                l_dblExecExchangeRate,
                l_currency.getScale(),
                l_currency.getChangeFCcyRoundDiv());
        
        //�����͏��.���ڍ�.�����萔���i�O�݁j�F
        l_execInputInfo.execDetailInfoUnit.foreignConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_dblForeignConsumptionTax);
        
        //�U�j�@@��n����̌v�Z
        //�@@�ȉ��̒ʂ�A��n����i�O�݁j�^�i�~�݁j���v�Z���A�l���Z�b�g����B
        //�@@�����͏��.���ڍ�.��n����i�~�݁j�F
        //�@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j
        //�@@�@@�@@��n����i�~�݁j = ���Z����i�~�݁j�{�����萔���i�~�݁j�{����Łi�~�݁j
        //�@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j
        //�@@�@@�@@��n����i�~�݁j = ���Z����i�~�݁j�|�����萔���i�~�݁j�|����Łi�~�݁j
        double l_dblDeliveryPrice = 0.0D;
        
        //���Z����i�~�݁j
        l_dblClearUpPrice = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.clearUpPrice);
        //�����萔���i�~�݁j
        double l_dblCommission = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.commission);
        //����Łi�~�݁j
        double l_dblConsumptionTax = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.consumptionTax);
        
        if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
        {
            //���̏ꍇ
            //��n����i�~�݁j = ���Z����i�~�݁j�{�����萔���i�~�݁j�{����Łi�~�݁j
            l_dblDeliveryPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblClearUpPrice,l_dblCommission);
                
            l_dblDeliveryPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblDeliveryPrice,l_dblConsumptionTax);
        }
        else
        {
            //���̏ꍇ
            //��n����i�~�݁j = ���Z����i�~�݁j�|�����萔���i�~�݁j�|����Łi�~�݁j
            l_dblDeliveryPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblClearUpPrice,l_dblCommission);
            
            l_dblDeliveryPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblDeliveryPrice,l_dblConsumptionTax);
        }
        l_execInputInfo.execDetailInfoUnit.deliveryPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblDeliveryPrice);
        
        //�@@�����͏��.���ڍ�.��n����i�O�݁j�F
        //�@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j
        //�@@�@@�@@��n����i�O�݁j = ���Z����i�O�݁j�{�����萔���i�O�݁j�{����Łi�O�݁j
        //
        //�@@�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j
        //�@@�@@�@@��n����i�O�݁j = ���Z����i�O�݁j�|�����萔���i�O�݁j�|����Łi�O�݁j        
        double l_dblForeignDeliveryPrice = 0.0D;
        
        //���Z����i�O�݁j
        double l_dblForeignClearUpPrice = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignClearUpPrice);
        //�����萔���i�O�݁j
        l_dblForeignCommission = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignCommission);
        //����Łi�O�݁j
        l_dblForeignConsumptionTax = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignConsumptionTax);
        
        if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
        {
            //���̏ꍇ
            //��n����i�O�݁j = ���Z����i�O�݁j�{�����萔���i�O�݁j�{����Łi�O�݁j
            l_dblForeignDeliveryPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblForeignClearUpPrice,l_dblForeignCommission);
                
            l_dblForeignDeliveryPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblForeignDeliveryPrice,l_dblForeignConsumptionTax);
        }
        else
        {
            //���̏ꍇ
            //��n����i�O�݁j = ���Z����i�O�݁j�|�����萔���i�O�݁j�|����Łi�O�݁j
            l_dblForeignDeliveryPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblForeignClearUpPrice,l_dblForeignCommission);
                
            l_dblForeignDeliveryPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblForeignDeliveryPrice,l_dblForeignConsumptionTax);
        }
        //�����͏��.���ڍ�.��n����i�O�݁j
        l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblForeignDeliveryPrice);
        
        //�V�j�@@���n�v�^���n�v�ł̌v�Z
        //�@@�ȉ��̒ʂ�A���n�v�^���n�v�ł��v�Z���A�l���Z�b�g����B
        //�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j
        if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
        {
            //�����͏��.���n�v�F�@@0
            l_execInputInfo.passProfit = "0";
            //�����͏��.���n�v�ŁF�@@0
            l_execInputInfo.passProfitTax = "0";
        }
        else
        {
            //�@@���@@���̏ꍇ�i�����͏��.�����敪 == �h���t�h�j�A
            //�@@�@@�ȉ��̌v�Z���ʁB
            
            //�@@�@@�����͏��.���ڍ�.���n�v�F
            //�@@�@@�@@�O�������v�Z�T�[�r�X.calc���n���v()�ɂČv�Z�����l�B
            //      ��������i�~�݁j�F�����͏��.���ڍ�.��n����i�~�݁j
            //      �������F�����͏��.���ڍ�.��萔��
            //      �����h�c�F����.getProductId()
            //      �⏕�����F�⏕����
            //      �ŋ敪�F�ŋ敪
            double l_dblCapitalProfitLoss = 
                l_bizLogicProvider.calcCapitalProfitLoss(
                    l_dblDeliveryPrice,
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity),
                    l_product.getProductId(),
                    l_subAccount,
                    l_taxType);
            
            l_execInputInfo.passProfit = 
                WEB3StringTypeUtility.formatNumber(l_dblCapitalProfitLoss);
            
            //�@@�@@�����͏��.���ڍ�.���n�v�ŁF
            //�@@�@@�@@�O�������v�Z�T�[�r�X.calc���n�v��()�ɂČv�Z�����l�B
            //      �⏕�����F�⏕����
            //      �ŋ敪�F�ŋ敪
            //      ��������i�~�݁j�F�����͏��.���ڍ�.���n�v
            //      ��n���F�����͏��.���ڍ�.������n��
            double l_dblCapitalGainTax = 
                l_bizLogicProvider.calcCapitalGainTax(
                    l_subAccount,
                    l_taxType,
                    Double.parseDouble(l_execInputInfo.passProfit),
                    l_execInputInfo.execDetailInfoUnit.deliveryDate);
            
            l_execInputInfo.passProfitTax = 
                WEB3StringTypeUtility.formatNumber(l_dblCapitalGainTax);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (persistHOST����)<BR>
     * ��������DB�ɍX�V����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i(��)�����́jpersistHOST�����v�Q�ƁB<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@param l_execInputInfo - (�����͏��)<BR>
     * �O�����������͏��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7AB0C0075
     */
    private void persistHostOrder(
        WEB3GentradeInstitution l_institution,
        String l_strUpdaterCode,
        WEB3FeqOrderAndExecutionUnit l_execInputInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " persistHostOrder(WEB3GentradeInstitution," + 
            " String, WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null || l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�[�����w��(null)�ł��B");  
        }        
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        WEB3GentradeAccountManager l_accManage = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //1.1 �ڋq�I�u�W�F�N�g���擾  �g���A�J�E���g�}�l�[�W��.get�ڋq
        //[get�ڋq()�Ɏw�肷�����] 
        //�،���ЃR�[�h�F�@@�،����.getInstitutionCode()
        //���X�R�[�h�F�@@�����͏��.���X�R�[�h
        //�����R�[�h�F�@@�����͏��.�ڋq�R�[�h
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accManage.getMainAccount(
            l_institution.getInstitutionCode(),
            l_execInputInfo.branchCode,
            l_execInputInfo.accountCode);

        //1.2 get�⏕����
        //[get�⏕����()�Ɏw�肷�����] 
        //�ڋq�F�@@get�ڋq() 
        SubAccount l_subAccount = 
            WEB3FeqClientRequestService.getSubAccount(l_mainAccount);

        WEB3FeqProductManager l_web3FeqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        //1.3 get�O����������        
        //[get�O����������()�Ɏw�肷�����] 
        //�،���ЁF�@@�،����
        //�����R�[�h�F�@@�����͏��.�����R�[�h�B�����͂̏ꍇ�͌��n�����R�[�h�B
        WEB3FeqProduct l_web3FeqProduct = null;
        try
        {
            l_web3FeqProduct = 
                (WEB3FeqProduct) l_web3FeqProductManager.getFeqProduct(
                   l_institution,
                   WEB3StringTypeUtility.isEmpty(l_execInputInfo.productCode) ? 
                       l_execInputInfo.localProductCode : l_execInputInfo.productCode);
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h���͂��s���B");
        }
               
        WEB3FeqBizLogicProvider l_bizLogicProvider =
           (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();

        //���ݓ���
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        
        //1.4 �����iͯ�ށj�s�I�u�W�F�N�g
        FeqOrderParams l_feqOrderParams = new FeqOrderParams();

        QueryProcessor l_queryProcessor;
        //1.6 doInsertQuery
        try 
        {
            //1.5 �����iͯ�ށj�s�I�u�W�F�N�g�ɒl���Z�b�g
            
            //�����h�c:�i�����̔ԁj
            l_feqOrderParams.setOrderId(l_orderManager.createNewOrderId());
            //�����h�c:�⏕����.�����h�c
            l_feqOrderParams.setAccountId(l_subAccount.getAccountId());
            //�⏕�����h�c:�⏕����.�⏕�����h�c
            l_feqOrderParams.setSubAccountId(l_subAccount.getSubAccountId());
            //�����^�C�v:4�F�O�����iProductTypeEnum�ɂĒ�`�j
            l_feqOrderParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            //�X�V�҃R�[�h:�Ǘ��҃R�[�h
            l_feqOrderParams.setLastUpdater(l_strUpdaterCode);
            //�쐬���t:���ݓ���
            l_feqOrderParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //�X�V���t:���ݓ���
            l_feqOrderParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_feqOrderParams);        
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.7 �����P�ʍs�I�u�W�F�N�g�𐶐�
        FeqOrderUnitParams l_feqOrderUnitParams = new FeqOrderUnitParams();
                
        //1.9 doInsertQuery
        try 
        {            
            //1.8 �����P�ʍs�I�u�W�F�N�g�ɒl���Z�b�g
            //�����P�ʂh�c: �i�����̔ԁj
            l_feqOrderUnitParams.setOrderUnitId(l_orderManager.createNewOrderUnitId());
            
            //�،���ЃR�[�h   :�،���ЃR�[�h
            l_feqOrderUnitParams.setInstitutionCode(l_institution.getInstitutionCode());
            
            //�����h�c :�⏕����.getAccountId()
            l_feqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
            
            //�⏕�����h�c :�⏕����.getSubAccountId()
            l_feqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
            
            //���X�h�c :�⏕����.getBranch().getBranchId()
            l_feqOrderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            
            //����҂h�c :null
            l_feqOrderUnitParams.setTraderId(null);
            
            //�����h�c :����.����ID
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            
            //������� :�����͏��.�����敪 == �h���t�h�̏ꍇ�A�h701�F�O�������h 
            //         �h���t�h�̏ꍇ�A�h702�F�O������h
            if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
            {
                l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
            } 
            else if (WEB3BuySellTypeDef.SELL.equals(l_execInputInfo.dealingType))
            {
                l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_SELL);
            }
            
            //�����J�e�S�� :1�F���������iOrderCategEnum�ɂĒ�`�j
            l_feqOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            
            //���������ŏI�ʔ� :1
            l_feqOrderUnitParams.setLastOrderActionSerialNo(1);
            
            //���ŏI�ʔ� :1
            l_feqOrderUnitParams.setLastExecutionSerialNo(1);
            
            //�����^�C�v :4�F�O�����iProductTypeEnum�ɂĒ�`�j
            l_feqOrderUnitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            
            //�s��h�c :�O����������.get�s��().�s��ID
            l_feqOrderUnitParams.setMarketId(l_web3FeqProduct.getMarket().getMarketId());
            
            //�������� :�����͏��.���ڍ�.��萔��
            l_feqOrderUnitParams.setQuantity(Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity));
            
            //�w�l :�����͏��.�����P���敪 == �h���s�h�̏ꍇ0�B
            //�ȊO�A�����͏��.���ڍ�.���P���B
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_execInputInfo.orderPriceDiv))
            {
                l_feqOrderUnitParams.setLimitPrice(0);
            } 
            else
            {
                l_feqOrderUnitParams.setLimitPrice(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice));
            }
            
            //���s���� :FeqExecutionConditionType.NONE(1:�����Ȃ�)
            l_feqOrderUnitParams.setExecutionConditionType(
                FeqExecutionConditionType.NONE);
            
            //�������� :�����͏��.��������
            l_feqOrderUnitParams.setOrderConditionType(l_execInputInfo.orderCondType);
            
            //�����������Z�q :�����͏��.�����������Z�q
            l_feqOrderUnitParams.setOrderCondOperator(l_execInputInfo.condOperator);
            
            //�t�w�l��l :�����͏��.���������P��
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderCondPrice))
            {
                l_feqOrderUnitParams.setStopOrderPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setStopOrderPrice(
                    Double.parseDouble(l_execInputInfo.orderCondPrice));
            }
            
            //�iW�w�l�j�����w�l :�i�����͏��.W�w�l�p�����P���敪 == �h���s�h�j�̏ꍇ�A0�B
            //�ȊO�A�����͏��.W�w�l�p�����P���B
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_execInputInfo.wLimitOrderPriceDiv))
            {
                l_feqOrderUnitParams.setWLimitPrice(0);
            } 
            else
            {
                if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.wLimitPrice))
                {
                    l_feqOrderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_feqOrderUnitParams.setWLimitPrice(
                        Double.parseDouble(l_execInputInfo.wLimitPrice));
                }
            }
            
            //���ϋ敪 :"�����͏��.���ϋ敪�i�m�F�����ɂē��́^�Z�b�g�j"
            l_feqOrderUnitParams.setSettleDiv(l_execInputInfo.settleDiv);
            
            //��n�� :�����͏��.���ڍ�.������n��
            l_feqOrderUnitParams.setDeliveryDate(
                l_execInputInfo.execDetailInfoUnit.deliveryDate);
            
            //�����������t :�����͏��.�������̓��t����
            l_feqOrderUnitParams.setExpirationDate(
                WEB3DateUtility.toDay(l_execInputInfo.execDetailInfoUnit.executionTimestamp));
            
            //�s�ꂩ��m�F�ς݂̐��� :��������
            l_feqOrderUnitParams.setConfirmedQuantity(
                l_feqOrderUnitParams.getQuantity());
            
            //�s�ꂩ��m�F�ς݂̎w�l :�w�l
            l_feqOrderUnitParams.setConfirmedPrice(l_feqOrderUnitParams.getLimitPrice());
            
            //��萔�� :�����͏��.���ڍ�.��萔��
            l_feqOrderUnitParams.setExecutedQuantity(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity));
            
            //���v�����z :�����͏��.��������i�O�݁j
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.execDetailInfoUnit.foreignTradePrice))
            {
                l_feqOrderUnitParams.setExecutedAmount(null);
            }
            else
            {
                l_feqOrderUnitParams.setExecutedAmount(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignTradePrice));
            }
            
            //���v�����z�i�~�j :�����͏��.��������i�~�݁j
            //�i��������i�O�݁j���v�Z�T�[�r�X.calc�~�݊��Z()�ɂĉ~�݊��Z�����l�j
            //
            //�،���ЃR�[�h
            //�ʉ݃R�[�h
             WEB3GentradeCurrency l_genCurrency = WEB3GentradeCurrency.genCurrency(
                l_institution.getInstitutionCode(),
                l_execInputInfo.currencyCode);
             
            //��������i�O�݁j
            //���[�g�F �����͏��.���בփ��[�g
            //�~�݊��Z�ۂߕ����F �ʉ�.get�~�݊��Z�ۂߕ���()
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.execDetailInfoUnit.foreignTradePrice))
            {
                l_feqOrderUnitParams.setExecutedAmountYen(null);
            }
            else
            {
                double l_dblTradePrice = 
                    l_bizLogicProvider.calcJPYAmount(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignTradePrice),
                    Double.parseDouble(l_execInputInfo.execExchangeRate),
                    l_genCurrency.getChangeJpyRoundDiv());
                l_feqOrderUnitParams.setExecutedAmountYen(l_dblTradePrice);
            }
                    
            //������� :1�F��t�ς݁i�V�K�����j�B�iOrderStatusEnum�ɂĒ�`�j
            l_feqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            
            //�����L����� :2�F�N���[�Y�iOrderOpenStatusEnum�ɂĒ�`�j
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            
            //�����敪 :1:�I�[�v���iOrderExpirationStatusEnum�ɂĒ�`�j
            l_feqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            
            //�ŋ敪 :�����͏��.��������敪�ɑΉ�����ŋ敪�iTaxTypeEnum�ɂĒ�`�j
            l_feqOrderUnitParams.setTaxType(
                WEB3FeqOrderUtility.getTaxType(l_execInputInfo.taxType));
            
            //������ :�����͏��.������
            l_feqOrderUnitParams.setBizDate(
                WEB3FeqDateUtility.formatDate(l_execInputInfo.orderBizDate, "yyyyMMdd"));
            
            //�����h�c :�O����������.getProductId()
            l_feqOrderUnitParams.setProductId(l_web3FeqProduct.getProductId());
            
            //�ʉ݃R�[�h :�����͏��.�ʉ݃R�[�h
            l_feqOrderUnitParams.setCurrencyCode(l_execInputInfo.currencyCode);
            
            //���񒍕��̒����`���l�� :�����`���l��.�h0�F�c�ƓX�h
            l_feqOrderUnitParams.setOrderChanel(WEB3ChannelDef.BRANCH);
            
            //�󒍓��� :���ݓ���
            l_feqOrderUnitParams.setReceivedDateTime(l_tsSystemTimestamp);
            
            //�`�[No :"9"(WebBroker)�{���ʃR�[�h�̉�3��
            l_feqOrderUnitParams.setVoucherNo("9" + 
                l_execInputInfo.requestNumber.substring(l_execInputInfo.requestNumber.length() - 3));
            
            //���񒍕��̎萔��No        :null
            l_feqOrderUnitParams.setCommTblNo(null);
            
            //���񒍕��̎萔��No�}��    :null
            l_feqOrderUnitParams.setCommTblSubNo(null);
            
            //���҃R�[�h�iSONAR�j :�ڋq.���҃R�[�h�iSONAR�j
            String l_strTraderCode = 
                ((MainAccountRow) (l_mainAccount.getDataSourceObject())).getSonarTraderCode();
            l_feqOrderUnitParams.setSonarTraderCode(l_strTraderCode);
                    
            //�����P�� :�����͏��.���ڍ�.���P��
            l_feqOrderUnitParams.setPrice(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice));
            
            //���ʃR�[�h :�����͏��.���ʃR�[�h
            l_feqOrderUnitParams.setOrderRequestNumber(l_execInputInfo.requestNumber);
            
            //�T�Z��n��� :�����͏��.���ڍ�.��n����i�~�݁j
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.execDetailInfoUnit.deliveryPrice))
            {
                l_feqOrderUnitParams.setEstimatedPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setEstimatedPrice(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.deliveryPrice));
            }
            
            //�T�Z��n����i�O�݁j :�����͏��.���ڍ�.��n����i�O�݁j
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice))
            {
                l_feqOrderUnitParams.setFEstimatedPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setFEstimatedPrice(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice));
            }
            
            //����R�[�h�iSONAR�j :11�F���ʊ���(WEB3TransactionTypeSONARDef�ɂĒ�`)
            l_feqOrderUnitParams.setSonarTradedCode(
                WEB3TransactionTypeSONARDef.MARKET_TRADING);
                    
            //�s��R�[�h�iSONAR�j :�O����������.get�s��().�s��R�[�h(SONAR)
            Market l_market = l_web3FeqProduct.getMarket();
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();        
            l_feqOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            
            //�萔�����i�R�[�h :40�F�O������(WEB3CommisionProductCodeDef�ɂĒ�`)
            l_feqOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.FOREIGN_EQITY);

            //���n�v���z :�����͏��.���ڍ�.���n�v
            l_feqOrderUnitParams.setCapitalGain(
                Double.parseDouble(l_execInputInfo.passProfit));

            //���n�v�Ŋz :�����͏��.���ڍ�.���n�v��
            l_feqOrderUnitParams.setCapitalGainTax(
                Double.parseDouble(l_execInputInfo.passProfitTax));

            //���������E����敪 :"0�F�����l�iWEB3ModifyCancelTypeDef�ɂĒ�`�j"        
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            //�����o�H�敪 :�����o�H�敪.�h9�FHOST�h
            l_feqOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            
            //�s�ꂩ��m�F�ς݂̒����P�� :�w�l
            l_feqOrderUnitParams.setConfirmedOrderPrice(l_feqOrderUnitParams.getLimitPrice());
            
            //�s�ꂩ��m�F�ς݂̊T�Z��n��� :�����͏��.���ڍ�.��n����i�~�݁j
            l_feqOrderUnitParams.setConfirmedEstimatedPrice(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.deliveryPrice));

            //�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j:�����͏��.���ڍ�.��n����i�O�݁j
            l_feqOrderUnitParams.setConfirmedFEstimatedPrice(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice));
                    
            //�s�ꂩ��m�F�ς݂̎��s���� :FeqExecutionConditionType.NONE(1:�����Ȃ�)
            l_feqOrderUnitParams.setConfirmedExecConditionType(
			    FeqExecutionConditionType.NONE);
            
            //�����G���[���R�R�[�h :0000�F����
            l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //�t�@@�N�^�[ :�u�����N
            l_feqOrderUnitParams.setFactor(" ");
            
            //�^�p�R�[�h :�����͏��.�^�p�R�[�h
            l_feqOrderUnitParams.setOrderEmpCode(l_execInputInfo.managementCode);
            
            //�ڋq�敪 :�����͏��.�ڋq�敪
            l_feqOrderUnitParams.setAccountDiv(l_execInputInfo.accountDiv);
            
            //�o���I���������� :null
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            
            //���񒍕��̒����P�ʂh�c :null
            l_feqOrderUnitParams.setFirstOrderUnitId(null);
            
            //�X�V�҃R�[�h :����.�X�V�҃R�[�h
            l_feqOrderUnitParams.setLastUpdater(l_strUpdaterCode);
            
            //�쐬���t :���ݓ���
            l_feqOrderUnitParams.setCreatedTimestamp(
                l_tsSystemTimestamp);

            //�X�V���t :���ݓ���
            l_feqOrderUnitParams.setLastUpdatedTimestamp(
                l_tsSystemTimestamp);
            l_queryProcessor.doInsertQuery(l_feqOrderUnitParams);
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.10 ���������s�I�u�W�F�N�g�𐶐�
        FeqOrderActionParams l_feqOrderActionParams = new FeqOrderActionParams();
        
        //1.12 doInsertQuery
        try
        {
            //1.11 ���������s�I�u�W�F�N�g�ɒl���Z�b�g
            //���������h�c : �i�����̔ԁj
            l_feqOrderActionParams.setOrderActionId(FeqOrderActionDao.newPkValue());
            
            //�����h�c :�����P��.�����h�c
            l_feqOrderActionParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            
            //�⏕�����h�c :�����P��.�⏕�����h�c
            l_feqOrderActionParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            
            //�����h�c :�����P��.�����h�c
            l_feqOrderActionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            
            //�����P�ʂh�c :�����P��.�����P�ʂh�c
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            
            //�s��h�c :�����P��.�s��h�c
            l_feqOrderActionParams.setMarketId(l_feqOrderUnitParams.getMarketId());
            
            //������� :�����P��.�������
            l_feqOrderActionParams.setOrderType(l_feqOrderUnitParams.getOrderType());
            
            //�����C�x���g�^�C�v :91�F���ρiOrderEventTypeEnum�ɂĒ�`�j
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXECUTE);
            
            //�����P�� :�����P��.�w�l
            if (l_feqOrderUnitParams.getLimitPriceIsNull())
            {
                l_feqOrderActionParams.setPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setPrice(l_feqOrderUnitParams.getLimitPrice());
            }
            
            //���s���� :�����P��.���s����
            l_feqOrderActionParams.setExecutionConditionType(
                l_feqOrderUnitParams.getExecutionConditionType());
            
            //�������� :�����P��.��������
            l_feqOrderActionParams.setOrderConditionType(
                l_feqOrderUnitParams.getOrderConditionType());
            
            //�����������Z�q :�����P��.�����������Z�q
            l_feqOrderActionParams.setOrderCondOperator(
                l_feqOrderUnitParams.getOrderCondOperator());
            
            //�t�w�l��l :�����P��.�t�w�l��l
            if (l_feqOrderUnitParams.getStopOrderPriceIsNull())
            {
                l_feqOrderActionParams.setStopOrderPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setStopOrderPrice(l_feqOrderUnitParams.getStopOrderPrice());
            }
            
            //�iW�w�l�j�����w�l :�����P��.�iW�w�l�j�����w�l
            if (l_feqOrderUnitParams.getWLimitPriceIsNull())
            {
                l_feqOrderActionParams.setWLimitPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setWLimitPrice(l_feqOrderUnitParams.getWLimitPrice());
            }
            
            //�����������t :�����P��.�����������t
            l_feqOrderActionParams.setExpirationDate(l_feqOrderUnitParams.getExpirationDate());
            
            //�������� :�����P��.����
            l_feqOrderActionParams.setQuantity(l_feqOrderUnitParams.getQuantity());
            
            //�s��Ɗm�F�ς݂̎w�l :�����P��.�s�ꂩ��m�F�ς̎w�l
            if (l_feqOrderUnitParams.getConfirmedPriceIsNull())
            {
                l_feqOrderActionParams.setConfirmedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedPrice(l_feqOrderUnitParams.getConfirmedPrice());
            }
            
            //�s��Ɗm�F�ς݂̐��� :�����P��.�s�ꂩ��m�F�ς̐���
            if (l_feqOrderUnitParams.getConfirmedQuantityIsNull())
            {
                l_feqOrderActionParams.setConfirmedQuantity(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedQuantity(l_feqOrderUnitParams.getConfirmedQuantity());
            }
            
            //��萔�� :�����P��.��������
            if (l_feqOrderUnitParams.getQuantityIsSet())
            {
                l_feqOrderActionParams.setExecutedQuantity(l_feqOrderUnitParams.getQuantity());
            }
            else
            {
                l_feqOrderActionParams.setExecutedQuantity(null);
            }
            
            //������� :�����P��.�������
            l_feqOrderActionParams.setOrderStatus(l_feqOrderUnitParams.getOrderStatus());
            
            //���������X�e�[�^�X :�����P��.�����敪
            l_feqOrderActionParams.setExpirationStatus(l_feqOrderUnitParams.getExpirationStatus());
            
            //��������ԍ� :�����P��.���������ŏI�ʔ�
            l_feqOrderActionParams.setOrderActionSerialNo(
                l_feqOrderUnitParams.getLastOrderActionSerialNo());
            
            //���P�� :�����P��.�w�l
            if (l_feqOrderUnitParams.getLimitPriceIsNull())
            {
                l_feqOrderActionParams.setExecutedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setExecutedPrice(l_feqOrderUnitParams.getLimitPrice());
            }
            
            //������ :�����͏��.������
            l_feqOrderActionParams.setExecTimestamp(
                l_execInputInfo.execDetailInfoUnit.executionTimestamp);
            
            //�����h�c :�����P��.�����h�c
            l_feqOrderActionParams.setProductId(
                l_feqOrderUnitParams.getProductId());
            
            //�T�Z��n��� :�����P��.�T�Z��n���
            if (l_feqOrderUnitParams.getEstimatedPriceIsNull())
            {
                l_feqOrderActionParams.setEstimatedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setEstimatedPrice(
                    l_feqOrderUnitParams.getEstimatedPrice());
            }
            
            //�T�Z��n����i�O�݁j :�����P��.�T�Z��n����i�O�݁j
            if (l_feqOrderUnitParams.getFEstimatedPriceIsNull())
            {
                l_feqOrderActionParams.setFEstimatedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setFEstimatedPrice(
                    l_feqOrderUnitParams.getFEstimatedPrice());
            }
            
            //���������E����敪 :�����P��.���������E����敪
            l_feqOrderActionParams.setModifyCancelType(
                l_feqOrderUnitParams.getModifyCancelType());
            
            //�����o�H�敪 :�����P��.�����o�H�敪
            l_feqOrderActionParams.setOrderRootDiv(l_feqOrderUnitParams.getOrderRootDiv());
            
            //�s�ꂩ��m�F�ς݂̒����P�� :�����P��.�s�ꂩ��m�F�ς݂̒����P��
            if (l_feqOrderUnitParams.getConfirmedOrderPriceIsNull())
            {
                l_feqOrderActionParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedOrderPrice(
                    l_feqOrderUnitParams.getConfirmedOrderPrice());
            }
            
            //�s�ꂩ��m�F�ς݂̊T�Z��n��� :�����P��.�s�ꂩ��m�F�ς݂̊T�Z��n���
            if (l_feqOrderUnitParams.getConfirmedEstimatedPriceIsNull())
            {
                l_feqOrderActionParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedEstimatedPrice(
                    l_feqOrderUnitParams.getConfirmedEstimatedPrice());
            }
            
            //�s�ꂩ��m�F�ς݂̊T�Z��n��� :�����P��.�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
            if (l_feqOrderUnitParams.getConfirmedFEstimatedPriceIsNull())
            {
                l_feqOrderActionParams.setConfirmedFEstimatedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedFEstimatedPrice(
                    l_feqOrderUnitParams.getConfirmedFEstimatedPrice());
            }

            //�s�ꂩ��m�F�ς݂̎��s���� :�����P��.�s�ꂩ��m�F�ς݂̎��s����
            l_feqOrderActionParams.setConfirmedExecConditionType(
                l_feqOrderUnitParams.getConfirmedExecConditionType());
            
            //�����G���[���R�R�[�h :�����P��.�����G���[���R�R�[�h
            l_feqOrderActionParams.setErrorReasonCode(
                l_feqOrderUnitParams.getErrorReasonCode());
            
            //�X�V�҃R�[�h :�����P��.�X�V�҃R�[�h
            l_feqOrderActionParams.setLastUpdater(l_feqOrderUnitParams.getLastUpdater());
            
            //�쐬���t :���ݓ���
            l_feqOrderActionParams.setCreatedTimestamp(l_tsSystemTimestamp);
            
            //�X�V���t :���ݓ���
            l_feqOrderActionParams.setLastUpdatedTimestamp(
                l_tsSystemTimestamp);
            
            l_queryProcessor.doInsertQuery(l_feqOrderActionParams);        
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.13 FeqOrderExecutionParams()
        FeqOrderExecutionParams l_feqOrderExecutionParams = new FeqOrderExecutionParams();
                
        //1.15 doInsertQuery
        try 
        {            
            //1.14 ���s�I�u�W�F�N�g�ɒl���Z�b�g
            //���h�c : �i�����̔ԁj
            l_feqOrderExecutionParams.setOrderExecutionId(FeqOrderExecutionDao.newPkValue());
    
            //�����h�c :�����P��.�����h�c
            l_feqOrderExecutionParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            
            //�⏕�����h�c :�����P��.�⏕�����h�c
            l_feqOrderExecutionParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            
            //���X�h�c :�����P��.���X�h�c
            l_feqOrderExecutionParams.setBranchId(l_feqOrderUnitParams.getBranchId());
            
            //����҂h�c :�����P��.����҂h�c
            if (l_feqOrderUnitParams.getTraderIdIsNull())
            {
                l_feqOrderExecutionParams.setTraderId(null);
            }
            else
            {
                l_feqOrderExecutionParams.setTraderId(l_feqOrderUnitParams.getTraderId());
            }

            //�����h�c :�����P��.�����h�c
            l_feqOrderExecutionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            
            //�����P�ʂh�c :�����P��.�����P�ʂh�c
            l_feqOrderExecutionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            
            //������� :�����P��.�������
            l_feqOrderExecutionParams.setOrderType(l_feqOrderUnitParams.getOrderType());
            
            //�����^�C�v :�����P��.�����^�C�v
            l_feqOrderExecutionParams.setProductType(l_feqOrderUnitParams.getProductType());
            
            //�s��h�c :�����P��.�s��h�c
            l_feqOrderExecutionParams.setMarketId(l_feqOrderUnitParams.getMarketId());
            
            //��n�� :�����P��.��n��
            l_feqOrderExecutionParams.setDeliveryDate(l_feqOrderUnitParams.getDeliveryDate());
            
            //���n��n�� :�����͏��.���n��n��
            l_feqOrderExecutionParams.setFDeliveryDate(l_execInputInfo.localDeliveryDate);
            
            //���ʔ� :�����P��.���ŏI�ʔ�
            l_feqOrderExecutionParams.setExecSerialNo(l_feqOrderUnitParams.getLastExecutionSerialNo());
            
            //���P�� :�����͏��.���ڍ�.���P��
            l_feqOrderExecutionParams.setExecPrice(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice));
            
            //�בփ��[�g :�����͏��.���בփ��[�g
            l_feqOrderExecutionParams.setFxRate(
                Double.parseDouble(l_execInputInfo.execExchangeRate));
            
            //��萔�� :�����͏��.���ڍ�.��萔��
            l_feqOrderExecutionParams.setExecQuantity(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity));
            
            //������ :�����͏��.������
            l_feqOrderExecutionParams.setExecTimestamp(
                l_execInputInfo.execDetailInfoUnit.executionTimestamp);
            
            //�폜�t���O :0�FFALSE
            l_feqOrderExecutionParams.setDeleteFlag(BooleanEnum.FALSE);
            
            //������ :�����P��.������
            l_feqOrderExecutionParams.setBizDate(l_feqOrderUnitParams.getBizDate());
            
            //�����h�c :�����P��.�����h�c
            l_feqOrderExecutionParams.setProductId(
                l_feqOrderUnitParams.getProductId());
            
            //���ϋ敪 :�����P��.���ϋ敪
            l_feqOrderExecutionParams.setSettleDiv(l_feqOrderUnitParams.getSettleDiv());
            
            //���ʃR�[�h :�����P��.���ʃR�[�h
            l_feqOrderExecutionParams.setOrderRequestNumber(l_feqOrderUnitParams.getOrderRequestNumber());
            
            //�^�p�R�[�h :�����P��.�^�p�R�[�h
            l_feqOrderExecutionParams.setOrderEmpCode(l_feqOrderUnitParams.getOrderEmpCode());
            
            //      ���o�H�敪
            // :ThreadLocalSystemAttributesRegistry.getAttribute(�h���o�H�敪�h)
            //�i���e�T�[�r�X�C���^�Z�v�^�ɂăZ�b�g�����l�j
            l_feqOrderExecutionParams.setOrderExecRouteDiv(
                (String) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV));
            
            //�X�V�҃R�[�h :�����P��.�X�V�҃R�[�h
            l_feqOrderExecutionParams.setLastUpdater(l_feqOrderUnitParams.getLastUpdater());
            
            //�쐬���t :���ݓ���
            l_feqOrderExecutionParams.setCreatedTimestamp(l_tsSystemTimestamp);
            
            //�X�V���t :���ݓ���
            l_feqOrderExecutionParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
            //��������@@�����_��O�ʂ��l�̌ܓ��isonar�̎d�l�j
            double l_dblForeignTradePricewk =
                new BigDecimal(String.valueOf(l_feqOrderExecutionParams.getExecPrice()))
                .multiply(new BigDecimal(String.valueOf(l_feqOrderExecutionParams.getExecQuantity())))
                .doubleValue();
            double l_dblForeignTradePrice = 
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblForeignTradePricewk,
                    2,
                    WEB3FeqOrderUtility.ROUND); 
            l_feqOrderExecutionParams.setForeignTradePrice(l_dblForeignTradePrice);
            
            l_queryProcessor.doInsertQuery(l_feqOrderExecutionParams);        
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.16 persist���c��(FeqOrderUnitParams, long, �O�����������͏��)
        //      �����P�ʍs�F�@@�i�������������P�ʍs�I�u�W�F�N�g�j
        //      ���h�c�F�@@�i�����������s�I�u�W�F�N�g�j.getOrderExecutionId()
        //      �����͏��F�@@�����͏��
        persistExecBalance(
            l_feqOrderUnitParams, 
            l_feqOrderExecutionParams.getOrderExecutionId(), 
            l_execInputInfo);
        
        //1.17 �]�͍Čv�Z
        WEB3TPTradingPowerReCalcService l_powerReCalcServie = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        //[�]�͍Čv�Z()�Ɏw�肷�����] 
        //�⏕�����F�@@get�⏕����()
        l_powerReCalcServie.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (persist����ȯĒ���)<BR>
     * ��������DB�ɍX�V����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i(��)�����́jpersist����ȯĒ����v�Q�ƁB<BR>
     * @@param l_execInputOrder - (�����͏��)<BR>
     * �O�����������͏��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7CF4C010A
     */
    private void persistInternetOrder(WEB3FeqOrderAndExecutionUnit l_execInputOrder)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =" persistInternetOrder(WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_execInputOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�[�����w��(null)�ł��B");  
        }         
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //1.1 �����P�ʂ��擾
        //[get�����P��ByOrderId()�Ɏw�肷�����]
        // �����h�c�F�@@�����͏��.�����h�c
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderId(
                Long.parseLong(l_execInputOrder.orderId));

        FeqOrderUnitRow l_feqOrderUnitRow = 
            (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        
        //1.2 �X�V�C�x���g�C���^�Z�v�^�𐶐�
        //   [�R���X�g���N�^�Ɏw�肷�����]
        //    �O���o���ʒm�L���[�F�@@�i���������I�u�W�F�N�g���j
        //    �@@���O���o���ʒm�L���[Params�𐶐����A�ȉ��̒ʂ�v���p�e�B�ɒl���Z�b�g����B
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams
            = new HostFeqOrderExecNotifyParams();
        
        //�،���ЃR�[�h�F�@@�����P��.get�،���ЃR�[�h()
        l_hostFeqOrderExecNotifyParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
        
        //���X�R�[�h�F�@@�����P��.get���X�R�[�h()
        l_hostFeqOrderExecNotifyParams.setBranchCode(l_feqOrderUnit.getBranchCode());
        
        //�ڋq�R�[�h�F�@@�����P��.get�����R�[�h()
        l_hostFeqOrderExecNotifyParams.setAccountCode(l_feqOrderUnit.getAccountCode());
        
        //���ʃR�[�h�F�@@�����P��.���ʃR�[�h
        l_hostFeqOrderExecNotifyParams.setRequestCode(l_feqOrderUnitRow.getOrderRequestNumber());
        
        //�^�p�R�[�h�F�@@�����P��.�^�p�R�[�h
        l_hostFeqOrderExecNotifyParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());
        
        //��芔���F�@@�����͏��.���ڍ�.��芔��
        if (WEB3StringTypeUtility.isEmpty(l_execInputOrder.execDetailInfoUnit.execQuantity))
        {
            l_hostFeqOrderExecNotifyParams.setExecQuantity(null);
        }
        else
        {
            l_hostFeqOrderExecNotifyParams.setExecQuantity(
                Double.parseDouble(l_execInputOrder.execDetailInfoUnit.execQuantity));
        }
        
        //���P���F�@@�����͏��.���ڍ�.���P��
        if (WEB3StringTypeUtility.isEmpty(l_execInputOrder.execDetailInfoUnit.execPrice))
        {
            l_hostFeqOrderExecNotifyParams.setExecPrice(null);
        }
        else
        {
            l_hostFeqOrderExecNotifyParams.setExecPrice(
                Double.parseDouble(l_execInputOrder.execDetailInfoUnit.execPrice));
        }
        
        //�������F�@@�����͏��.���ڍ�.������
        l_hostFeqOrderExecNotifyParams.setExecTimestamp(
            l_execInputOrder.execDetailInfoUnit.executionTimestamp);
        
        //���n��n���F�@@�����͏��.���n��n��
        l_hostFeqOrderExecNotifyParams.setFDeliveryDate(l_execInputOrder.localDeliveryDate);
        
        //�בփ��[�g�F�@@�����͏��.���בփ��[�g
        if (WEB3StringTypeUtility.isEmpty(l_execInputOrder.execExchangeRate))
        {
            l_hostFeqOrderExecNotifyParams.setFxRate(null);
        }
        else
        {
            l_hostFeqOrderExecNotifyParams.setFxRate(
                Double.parseDouble(l_execInputOrder.execExchangeRate));
        }

        WEB3FeqExecuteUpdateInterceptor l_web3FeqExecuteUpdateInterceptor 
            = new WEB3FeqExecuteUpdateInterceptor(l_hostFeqOrderExecNotifyParams);
        
        // �����P�ʂ��Z�b�g
        l_web3FeqExecuteUpdateInterceptor.setFeqOrderUnit(l_feqOrderUnit);        
        // ��萔�ʂ��Z�b�g
        l_web3FeqExecuteUpdateInterceptor.setExecQuantity(
            Double.parseDouble(l_execInputOrder.execDetailInfoUnit.execQuantity));
        // ���P�����Z�b�g
        l_web3FeqExecuteUpdateInterceptor.setExecPrice(
            Double.parseDouble(l_execInputOrder.execDetailInfoUnit.execPrice));
        // ���בփ��[�g���Z�b�g
        l_web3FeqExecuteUpdateInterceptor.setFxRate(
            Double.parseDouble(l_execInputOrder.execExchangeRate));
        
        //1.3 �X�V�C�x���g�C���^�Z�v�^�𒍕��}�l�[�W���ɃZ�b�g
        //[setThreadLocalPersistenceEventInterceptor()�Ɏw�肷�����]
        // arg0�F�@@�O���������X�V�C�x���g�C���^�Z�v�^
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_web3FeqExecuteUpdateInterceptor);
        
        //1.4 ���R�[���o�b�N���b�Z�[�W�𐶐�
        //[�R���X�g���N�^�̈���]
        // �����h�c�F�@@�����P��.getOrderId()
        // �����P�ʂh�c�F�@@�����P��.getOrderUnitId()
        // ��萔�ʁF�@@�O���o���ʒm�L���[.��芔��
        // ���P���F�@@�O���o���ʒm�L���[.���P��
        // ���בփ��[�g�F�@@�O���o���ʒm�L���[.�בփ��[�g
        DefaultFeqOrderFillMarketResponseMessage l_defaultFeqOrderFillMarketResponseMessage = 
            new DefaultFeqOrderFillMarketResponseMessage(
                l_feqOrderUnit.getOrderId(),
                l_feqOrderUnit.getOrderUnitId(),
                l_hostFeqOrderExecNotifyParams.getExecQuantity(),
                l_hostFeqOrderExecNotifyParams.getExecPrice(),
                l_hostFeqOrderExecNotifyParams.getFxRate());

        //1.5 handle���X�V
        //[handle���X�V()�Ɏw�肷�����]
        // �����h�c�F�@@�����͏��.�����h�c
        // ���R�[���o�b�N���b�Z�[�W�F�@@�����������R�[���o�b�N���b�Z�[�W
        WEB3FeqProductTypeOrderSubmitterPersistenceManager l_orderSubmitterPersistenceManager = 
            (WEB3FeqProductTypeOrderSubmitterPersistenceManager)WEB3FeqProductTypeOrderSubmitterPersistenceManager.getInstance();

        OrderExecution l_orderExecution
            = l_orderSubmitterPersistenceManager.handleExecutionUpdate(
                Long.parseLong(l_execInputOrder.orderId),
                l_defaultFeqOrderFillMarketResponseMessage);

        //�����P�ʍĎ擾
        l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderId(
                Long.parseLong(l_execInputOrder.orderId));

        //1.6 �c������DB�ɍX�V����B
        //[persist���c��()�Ɏw�肷�����]
        //�����P�ʍs�F�@@�����P��.getDataSourceObject()
        //���h�c�F�@@handle���X�V()�̖߂�l
        //�����͏��F�@@�����͏��
        persistExecBalance(
                (FeqOrderUnitParams) l_feqOrderUnit.getDataSourceObject(),
                l_orderExecution.getOrderExecutionId(),
                l_execInputOrder);

        //1.7 �T�Z��n����Čv�Z�������s���B
        //[update�T�Z��n���()�Ɏw�肷�����]
        //�����P�ʁF�@@�����P��
        //����F�@@�����͏��.���ڍ�.������
        Date l_datBaseDate  =  l_execInputOrder.execDetailInfoUnit.executionTimestamp;
        
        l_orderManager.updateEstimatedPrice(
            l_feqOrderUnit,
            l_datBaseDate);

        l_orderManager.executeChangeCancelOrderRejected(l_feqOrderUnit.getOrderUnitId());

        //1.8 �]�͍Čv�Z���������{����B
        //[�]�͍Čv�Z()�Ɏw�肷�����]
        //�⏕�����F�@@�����P��.get�⏕����()

        WEB3TPTradingPowerReCalcService l_powerReCalcServie = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        
        l_powerReCalcServie.reCalcTradingPower(l_feqOrderUnit.getSubAccount());
        
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (persist���c��)<BR>
     * �c������DB�ɍX�V����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i(��)�����́jpersist���c���v�Q�ƁB<BR>
     * @@param l_orderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�I�u�W�F�N�g<BR>
     * @@param l_lngExecId - (���ID)<BR>
     * ���ID<BR>
     * @@param l_execInputInfo - (�����͏��)<BR>
     * �O�����������͏��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7B641006D
     */
    private void persistExecBalance(
        FeqOrderUnitParams l_orderUnitParams, 
        long l_lngExecId, 
        WEB3FeqOrderAndExecutionUnit l_execInputInfo)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " persistExecBalance(FeqOrderUnitParams," +
            " long, WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null || l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�[�����w��(null)�ł��B");  
        }
                
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        //1.1 �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�𐶐�
        FeqFinTransactionParams l_feqFinTransactionParams = 
            new FeqFinTransactionParams();
        
        //1.2 ������薾�׍s�I�u�W�F�N�g�ɒl���Z�b�g
        //�����h�c : �����P��.�����h�c
        l_feqFinTransactionParams.setAccountId(l_orderUnitParams.getAccountId());
        
        //�⏕�����h�c : �����P��.�⏕���������h�c
        l_feqFinTransactionParams.setSubAccountId(l_orderUnitParams.getSubAccountId());
        
        //�����P��.�����h�c
        l_feqFinTransactionParams.setProductId(l_orderUnitParams.getProductId());
        
        //�g�����U�N�V�����^�C�v : �i�����P��.������� == 701�F�O�������j�̏ꍇ�A701�F�O������
        //�i�����P��.������� == 702�F�O������j�̏ꍇ�A702�F�O������
        if (l_orderUnitParams.getOrderType().equals(OrderTypeEnum.FEQ_BUY))
        {
            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            //�����͏��.���ڍ�.��n����i�~�݁j�~�i-1�j
            l_feqFinTransactionParams.setNetAmount(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.deliveryPrice) * (-1));
            //�����͏��.���ڍ�.��n����i�O�݁j�~�i-1�j
            l_feqFinTransactionParams.setNetAmountFc(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice) * (-1));
        } 
        else if (l_orderUnitParams.getOrderType().equals(OrderTypeEnum.FEQ_SELL))
        {
            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            //�����͏��.���ڍ�.��n����i�~�݁j
            l_feqFinTransactionParams.setNetAmount(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.deliveryPrice));
            //�����͏��.���ڍ�.��n����i�O�݁j
            l_feqFinTransactionParams.setNetAmountFc(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice));
        }
        
        //�g�����U�N�V�����J�e�S��:  20�F��������i�����A�~�j���A���A���M�A�O���j
        l_feqFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
        
        //�g�����U�N�V������������ : ���ݓ���
        l_feqFinTransactionParams.setFinTransactionTimestamp(
            GtlUtils.getSystemTimestamp());
        
        //�ŋ敪 : �����P��.�ŋ敪
        l_feqFinTransactionParams.setTaxType(l_orderUnitParams.getTaxType());
        
        //���ϋ敪 : �����P��.���ϋ敪
        l_feqFinTransactionParams.setSettleDiv(l_orderUnitParams.getSettleDiv());
        
        //������: �����P��.������
        l_feqFinTransactionParams.setBizDate(l_orderUnitParams.getBizDate());
        
        //��n��: �����͏��.���ڍ�.��n��
        l_feqFinTransactionParams.setDeliveryDate(l_execInputInfo.execDetailInfoUnit.deliveryDate);
        
        //�ʉ݃R�[�h:�����P��.�ʉ݃R�[�h
        l_feqFinTransactionParams.setCurrencyCode(l_orderUnitParams.getCurrencyCode());
        
        //�K�p�בփ��[�g: �����͏��.���בփ��[�g
        l_feqFinTransactionParams.setFxRate(
            Double.parseDouble(l_execInputInfo.execExchangeRate));
        
        //�����^�C�v: �����P��.�����^�C�v
        l_feqFinTransactionParams.setProductType(l_orderUnitParams.getProductType());
        
        //�s��h�c: �����P��.�s��h�c
        l_feqFinTransactionParams.setMarketId(l_orderUnitParams.getMarketId());
        
        //���P��:�����͏��.���ڍ�.���P��
        l_feqFinTransactionParams.setPrice(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice));
        
        //��萔��:�����͏��.���ڍ�.��萔��
        l_feqFinTransactionParams.setQuantity(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity));
        
        //�����h�c: �����P��.�����h�c
        l_feqFinTransactionParams.setOrderId(l_orderUnitParams.getOrderId());
        
        //�����P�ʂh�c:�����P��.�����P�ʂh�c
        l_feqFinTransactionParams.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
        
        //���h�c
        l_feqFinTransactionParams.setOrderExecutionId(l_lngExecId);
        
        //�ϑ��萔��:�����͏��.���ڍ�.�����萔���i�~�݁j
        l_feqFinTransactionParams.setCommissionFee(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.commission));
        
        //�ϑ��萔�������:�����͏��.���ڍ�.����Łi�~�݁j
        l_feqFinTransactionParams.setCommissionFeeTax(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.consumptionTax));
        
        //���n���Z����i�~�݁j:�����͏��.���ڍ�.���Z����i�~�݁j
        l_feqFinTransactionParams.setBalanceAmount(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.clearUpPrice));
        
        //�ϑ��萔���i�O�݁j:�����͏��.���ڍ�.�����萔���i�O�݁j
        l_feqFinTransactionParams.setCommissionFeeFc(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignCommission));
        
        //�ϑ��萔������Łi�O�݁j:�����͏��.���ڍ�.����Łi�O�݁j
        l_feqFinTransactionParams.setCommissionFeeTaxFc(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignConsumptionTax));
        
        //���n���Z����i�O�݁j: �����͏��.���ڍ�.���Z����i�O�݁j
        l_feqFinTransactionParams.setBalanceAmountFc(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignClearUpPrice));        
        
        //���n�萔��:�����͏��.���ڍ�.���n�萔��
        l_feqFinTransactionParams.setForeignCommissionFee(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.localCommission));
        
        //���n�����:�����͏��.���ڍ�.���n�����
        l_feqFinTransactionParams.setForeignTax(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.localTradingTax));
        
        //���̑��R�X�g�P: �����͏��.���ڍ�.���̑��R�X�g�P
        l_feqFinTransactionParams.setForeignFeeExt1(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.otherCost1));
        
        //���̑��R�X�g�Q: �����͏��.���ڍ�.���̑��R�X�g�Q
        l_feqFinTransactionParams.setForeignFeeExt2(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.otherCost2));
                
        //���n�v���z:�����͏��.���n�v
        l_feqFinTransactionParams.setCapitalGain(
            Double.parseDouble(l_execInputInfo.passProfit));
        
        //���n�v�Ŋz:�����͏��.���n�v��
        l_feqFinTransactionParams.setCapitalGainTax(
            Double.parseDouble(l_execInputInfo.passProfitTax));
        
        //���n�v���z�i�O�݁j
        boolean l_blnIsBuy = l_orderUnitParams.getOrderType().equals(OrderTypeEnum.FEQ_BUY);
        WEB3FeqBizLogicProvider l_bizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblCapitalGainFc =
            l_bizLogicProvider.calcForeignCCYAmount(
                l_feqFinTransactionParams.getCapitalGain(),
                l_feqFinTransactionParams.getFxRate(),
                l_orderUnitParams.getProductId(),
                l_blnIsBuy,
                true);
        l_feqFinTransactionParams.setCapitalGainFc(l_dblCapitalGainFc);
        //���n�v�Ŋz�i�O�݁j
        double l_dblCapitalGainTaxFc =
            l_bizLogicProvider.calcForeignCCYAmount(
                l_feqFinTransactionParams.getCapitalGainTax(),
                l_feqFinTransactionParams.getFxRate(),
                l_orderUnitParams.getProductId(),
                l_blnIsBuy,
                true);
        l_feqFinTransactionParams.setCapitalGainTaxFc(l_dblCapitalGainTaxFc);
        
        //���p�ۗL���Y�̊Ǘ���:0
        l_feqFinTransactionParams.setTransferedAssetMngFee(0);
        //���p�ۗL���Y�̊Ǘ�������:0
        l_feqFinTransactionParams.setTransferedAssetMngFeeTax(0);
        //���p�ۗL���Y�̎萔��:0
        l_feqFinTransactionParams.setTransferedAssetSetupFee(0);
        //���p�ۗL���Y�̎萔�������:0
        l_feqFinTransactionParams.setTransferedAssetSetupFeeTax(0);
        //���Y�̕뉿:0
        l_feqFinTransactionParams.setTransferedAssetBookValue(0);
        //�폜�t���O:0�FFALSE
        l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        //���o�H�敪: "ThreadLocalSystemAttributesRegistry.getAttribute(�h���o�H�敪�h)
        //�i���e�T�[�r�X�C���^�Z�v�^�ɂăZ�b�g�����l�j"
        l_feqFinTransactionParams.setOrderExecRouteDiv(            
                (String) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV));

        //�X�V�҃R�[�h:�����P��.�X�V�҃R�[�h
        l_feqFinTransactionParams.setLastUpdater(l_orderUnitParams.getLastUpdater());

        //�쐬���t:"���ݓ����i���W������setExecutionInfoToMarketOrderedTrans( )�ɂăZ�b�g�j"
        l_feqFinTransactionParams.setCreatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //�X�V���t:"���ݓ����i���W������setExecutionInfoToMarketOrderedTrans( )�ɂăZ�b�g�j"
        l_feqFinTransactionParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        try
        {
            //1.3 �ۗL���Y���X�V����B
            //[applyTo�ۗL���Y�c��()�Ɏw�肷�����]
            //�g�����U�N�V�����i������薾�ׁj�s�F�@@�i���������I�u�W�F�N�g�j
            WEB3FeqPositionManager l_web3FeqPositionManager
                = (WEB3FeqPositionManager) l_tradingModule.getPositionManager();

            l_web3FeqPositionManager.applyToAssetBalance(l_feqFinTransactionParams);

            //1.4 �O�������X�V�f�[�^�}�l�[�W�����擾
            WEB3FeqPositionManagerHelper l_web3FeqPositionManagerHelper
                = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);

            //1.5 �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g��DB�ɍX�V����B
            //[saveNewFinTransaction()�Ɏw�肷�����]
            //�g�����U�N�V�����i������薾�ׁj�s�F�@@�i���������I�u�W�F�N�g�j
            l_web3FeqPositionManagerHelper.getPersistenceManager().saveNewFinTransaction(
                l_feqFinTransactionParams);

            //1.6 �ڋq���薾�ׁC�⏕�������X�V����B
            //[notify�ڋq����()�Ɏw�肷�����]
            //�g�����U�N�V�����i������薾�ׁj�s�F�@@�i���������I�u�W�F�N�g�j
            l_web3FeqPositionManager.notifyAccountCash(l_feqFinTransactionParams);            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
			ErrorInfo l_errorInfo = l_ex.getErrorInfo();
			
			//error_tag��"BUSINESS_ERROR_00204"�̏ꍇ�́AWEB3BusinessLayerException���X���[����B
			if (l_errorInfo.getErrorTag().equals("BUSINESS_ERROR_00204"))
			{
				throw new WEB3BusinessLayerException(
		  		WEB3ErrorCatalog.BUSINESS_ERROR_00204,
		  		this.getClass().getName() + "." + STR_METHOD_NAME);
		  		
			}
			//error_tag��"BUSINESS_ERROR_01931"�̏ꍇ�́AWEB3BusinessLayerException���X���[����B
			else if(l_errorInfo.getErrorTag().equals("BUSINESS_ERROR_01931"))
			{
				throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01931,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			else
			{
				throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}
        }
        catch (DataException l_ex)
        {
        	
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * to compare WEB3FeqOrderAndExecutionUnit's instance
     * @@param l_unit1
     * @@param l_unit2
     * @@return boolean
     */
    private boolean isOrderAndExecutionUnitEquals(
        WEB3FeqOrderAndExecutionUnit l_unit1, 
        WEB3FeqOrderAndExecutionUnit l_unit2)
    {
        final String STR_METHOD_NAME = " isOrderAndExecutionUnitEquals(" +
            "WEB3FeqOrderAndExecutionUnit, " + 
            "WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_unit1 == null && l_unit2 == null)
        {
            return true;
        }
        
        if (l_unit1 != null && l_unit2 == null)
        {
            return false;
        }
        
        if (l_unit1 == null && l_unit2 != null)
        {
            return false;
        }        
        
        //���҃R�[�h�F�@@        
        if (!this.isEqual(l_unit1.traderCode, l_unit2.traderCode))
        {
            return false;
        }        
        
        //�ڋq�敪�F�@@
        if (!this.isEqual(l_unit1.accountDiv, l_unit2.accountDiv))
        {
            return false;
        }   
        log.exiting(STR_METHOD_NAME);
        return true;        
    }
    
    /**
     * to compare Object
     * @@param l_obj1
     * @@param l_obj2
     * @@return boolean
     */
    private boolean isEqual(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = " isEqual(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        if (l_obj1 != null)
        {
            if (l_obj1.equals(l_obj2))
            {
                return true;
            }
        }
        else
        {
            if (l_obj2 != null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        
        return false;        
    }
}
@
