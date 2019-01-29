head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������Ɖ�T�[�r�XImpl(WEB3FeqExecuteReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬       
                 : 2005/08/03 �A�C��(���u) ���r���[       
Revesion History : 2007/07/04 �đo�g(���u) ����No.003
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.473�A474�A476
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderAction;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqTypeOrderManagerReusableValidations;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.define.WEB3FeqReferenceTypeDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3FeqChangeCancelHistoryGroup;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqExecuteDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqExecuteDetailsRequest;
import webbroker3.feq.message.WEB3FeqExecuteDetailsResponse;
import webbroker3.feq.message.WEB3FeqExecuteGroup;
import webbroker3.feq.message.WEB3FeqExecuteReferenceRequest;
import webbroker3.feq.message.WEB3FeqExecuteReferenceResponse;
import webbroker3.feq.message.WEB3FeqExecuteUnit;
import webbroker3.feq.message.WEB3FeqOrderDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqOrderHistoryRequest;
import webbroker3.feq.message.WEB3FeqOrderHistoryResponse;
import webbroker3.feq.message.WEB3FeqProductCodeNameUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.feq.service.delegate.WEB3FeqExecuteReferenceService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�O�������������Ɖ�T�[�r�XImpl)<BR>
 * �O�������������Ɖ�T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqExecuteReferenceServiceImpl extends 
    WEB3FeqClientRequestService implements WEB3FeqExecuteReferenceService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F6036B
     */
    public WEB3FeqExecuteReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �O�������������Ɖ�T�[�r�X�������s�� <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^���A<BR>
     * �@@[�O�������������Ɖ�N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.get�������Ɖ�()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[�O�������������ڍ׃��N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.get�������ڍ�()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[�O������������藚�����N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.get������藚��()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429EAA2C01E0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^���A 
        //[�O�������������Ɖ�N�G�X�g�̏ꍇ] 
        if (l_request instanceof WEB3FeqExecuteReferenceRequest)
        {
            //�@@this.get�������Ɖ�()���\�b�h���R�[������B 
            l_response = this.getOrderExecuteReference(
                (WEB3FeqExecuteReferenceRequest)l_request);   
        }        
        //[�O�������������ڍ׃��N�G�X�g�̏ꍇ] 
        else if (l_request instanceof WEB3FeqExecuteDetailsRequest)
        {
            //�@@this.get�������ڍ�()���\�b�h���R�[������B 
            l_response = 
                this.getOrderExecuteDetails(
                    (WEB3FeqExecuteDetailsRequest)l_request);   
        }        
        //[�O������������藚�����N�G�X�g�̏ꍇ] 
        else if (l_request instanceof WEB3FeqOrderHistoryRequest)
        {
            //�@@this.get������藚��()���\�b�h���R�[������B 
            l_response =
                this.getOrderExecuteAction((WEB3FeqOrderHistoryRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get�������Ɖ�)<BR>
     * �O�������������Ɖ�����s�� <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jget�������Ɖ�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FeqExecuteReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 429EAA0E0367
     */
    protected WEB3FeqExecuteReferenceResponse getOrderExecuteReference(
        WEB3FeqExecuteReferenceRequest l_request) 
            throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = "getOrderExecuteReference(" +
            "WEB3FeqExecuteReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 �⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.3 validate�������Ɖ�(�⏕����, �O�������������Ɖ�N�G�X�g)
        //�������Ɖ�����\���ǂ����`�F�b�N����B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //���N�G�X�g�f�[�^�F�@@���N�G�X�g�f�[�^
        this.validateOrderExecuteReference(l_subAccount, l_request);
        
        //1.4 ���X�|���X�f�[�^�𐶐�����B
        WEB3FeqExecuteReferenceResponse l_response = 
            (WEB3FeqExecuteReferenceResponse)l_request.createResponse();

        //1.5 create�������ꗗ()
        //�ڋq�ɊY��������������쐬���A���X�|���X�f�[�^�ɃZ�b�g����B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //���N�G�X�g�f�[�^�F�@@���N�G�X�g�f�[�^ 
        //���X�|���X�f�[�^�F�@@createResponse()�̖߂�l
        this.createProductInformationList(l_subAccount, l_request, l_response);
        
        //1.6 create�������׈ꗗ()
        //�ڋq�ɊY�����钍�����ׂ��쐬���A���X�|���X�f�[�^�ɃZ�b�g����B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //���N�G�X�g�f�[�^�F�@@���N�G�X�g�f�[�^ 
        //���X�|���X�f�[�^�F�@@createResponse()�̖߂�l
        this.createOrderDetailList(l_subAccount, l_request, l_response);
        
        //1.7 get�s��ǌx���O���s��(���X : ���X)
        //�s��Ǌԋ߂̎s��R�[�h�̈ꗗ���擾����B 
        //[����] 
        //���X�F�@@get�⏕����()�̖߂�l.get����X()
        WEB3GentradeBranch l_branch = 
            (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
        String[] l_strCloseFeqMarkets = 
        WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(l_branch);

        //1.8 get�戵�\�s��(���X, ProductTypeEnum)
        //���X���戵�\�Ȏs��R�[�h�̈ꗗ���擾����B 
        //[����] 
        //���X�F�@@get�⏕����()�̖߂�l.get����X() 
        //�����^�C�v�F�@@ProductTypeEnum.�O������
        String[] l_strHandlingPossMarkets = 
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.9 get�������ꗗ(String, String[])
        //�������̈ꗗ���擾����B 
        //[����] 
        //�Ɖ�敪�F�@@���N�G�X�g�f�[�^.�Ɖ�敪 
        //�s��R�[�h�ꗗ�F�@@get�戵�\�s��()�̖߂�l
        Date[] l_datOrderBizDates = 
            this.getOrderBizDateList(
                l_request.referenceType, l_strHandlingPossMarkets);
        
        //1.10 ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B        
        
        //���X�|���X.�s��R�[�h�ꗗ       ���@@get�戵�\�s��()�̖߂�l
        l_response.marketList = l_strHandlingPossMarkets;        
        log.debug("���X�|���X.�s��R�[�h�ꗗ = " + l_response.marketList);
        
        //���X�|���X.�������ꗗ     ���@@get�������ꗗ()�̖߂�l
        l_response.orderBizDateList = l_datOrderBizDates;
        log.debug("���X�|���X.�������ꗗ = " + l_response.orderBizDateList);
        
        //���X�|���X.����I���x���s��R�[�h�ꗗ ���@@get�s��ǌx���O���s��()�̖߂�l
        l_response.messageSuspension = l_strCloseFeqMarkets;
        log.debug("���X�|���X.����I���x���s��R�[�h�ꗗ = " + l_response.messageSuspension);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�������ڍ�)<BR>
     * �O�������������ڍ׏Ɖ�����s�� <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jget�������ڍׁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������������ڍ׃��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FeqExecuteDetailsResponse
     * @@throws WEB3BaseException
     * @@roseuid 429EAA0E0386
     */
    protected WEB3FeqExecuteDetailsResponse getOrderExecuteDetails(
        WEB3FeqExecuteDetailsRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderExecuteDetails(" +
            "WEB3FeqExecuteDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getOrderUnits(arg0 : long)
        //�����P�ʂ��擾����B 
        //[����] 
        //arg0�F�@@���N�G�X�g�f�[�^.����ID        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        OrderUnit[] l_orderUnits = l_feqOrderManager.getOrderUnits(
            Long.parseLong(l_request.orderId));       
        
        if (l_orderUnits == null)
        {
          log.debug("Error in �����P�ʃI�u�W�F�N�g���擾����");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02142,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
            
        //�ȍ~�̏����ł́AgetOrderUnits()�̖߂�l��0�Ԗڂ̗v�f�𒍕��P�ʂƂ��Ĉ����B        
        WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)l_orderUnits[0];
        
        //1.3 create�����ڍ׏��(�O�����������P��)
        //�����ڍ׏����쐬����B 
        //[����] 
        //�����P�ʁF�@@�����P��
        WEB3FeqOrderDetailInfoUnit l_feqOrderDetailInfoUnit = 
            this.createOrderDetailsInformation(l_feqOrderUnit);
        
        //1.4 create���ڍ׏��(�O�����������P��)
        //���ڍ׏����쐬����B 
        //[����] 
        //�����P�ʁF�@@�����P��
        WEB3FeqExecuteDetailInfoUnit l_feqExecuteDetailInfoUnit = 
            this.createExecuteDetailsInformation(l_feqOrderUnit);

        //1.5 ���X�|���X�f�[�^�𐶐�����B
        WEB3FeqExecuteDetailsResponse l_response = 
            (WEB3FeqExecuteDetailsResponse)l_request.createResponse();
        
        //1.6 (*)���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //���X�|���X.�����ڍ�  ���@@create�����ڍ׏��()�̖߂�l
        l_response.orderDetailInfoUnit = l_feqOrderDetailInfoUnit;
        
        //���X�|���X.���ڍ�  ���@@create���ڍ׏��()�̖߂�l
        l_response.executeDetailInfoUnit = l_feqExecuteDetailInfoUnit;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get������藚��)<BR>
     * �O������������藚���Ɖ�����s�� <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jget������藚���v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O������������藚�����N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FeqOrderHistoryResponse
     * @@throws WEB3BaseException
     * @@roseuid 429EAA0E03A5
     */
    protected WEB3FeqOrderHistoryResponse getOrderExecuteAction(
        WEB3FeqOrderHistoryRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderExecuteAction(" +
            "WEB3FeqOrderHistoryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getOrderUnits(arg0 : long)
        //�����P�ʂ��擾����B 
        //[����] 
        //arg0�F�@@���N�G�X�g�f�[�^.����ID        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        OrderUnit[] l_orderUnits = l_feqOrderManager.getOrderUnits(
            Long.parseLong(l_request.orderId));   
        
        if (l_orderUnits == null)
        {
          log.debug("Error in �����P�ʃI�u�W�F�N�g���擾����");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02142,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
            
        //�ȍ~�̏����ł́AgetOrderUnits()�̖߂�l��0�Ԗڂ̗v�f�𒍕��P�ʂƂ��Ĉ����B        
        WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)l_orderUnits[0];
        
        //1.3 create���������ꗗ(�O�����������P��)
        //���������ꗗ���쐬����B 
        //[����] 
        //�����P�ʁF�@@�����P��
        WEB3FeqChangeCancelHistoryGroup[] l_changeCancelHistoryGroups = 
            this.createOrderActionList(l_feqOrderUnit);
        
        //1.4 ���X�|���X�f�[�^�𐶐�����B
        WEB3FeqOrderHistoryResponse l_response = 
            (WEB3FeqOrderHistoryResponse)l_request.createResponse();
        
        //1.5 (*)���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //���X�|���X.���������ꗗ ���@@create���������ꗗ()�̖߂�l
        l_response.changeCancelHistoryGroups = l_changeCancelHistoryGroups;        
               
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�⏕����)<BR>
     * �igetSubAccount�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����B<BR>
     * ���Ǘ��ҊO�������������Ɖ�@@�\����R�[������邱�Ƃ��l���B<BR>
     * <BR>
     * �P�j���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾���A<BR>
     * �Y������ڋq�I�u�W�F�N�g���擾����B<BR>
     *    ���擾��������ID == 0�̏ꍇ�A<BR>
     * ThreadLocalSystemAttributeRegistry.getAttribute()���<BR>
     *       ����ID���擾����B<BR>
     * <BR>
     *          �ݒ�L�[�F�@@ACCOUNT_ID<BR>
     * �@@�@@�@@�@@���ݒ�L�[�FACCOUNT_ID�͕K���萔�N���X���쐬���A<BR>
     * ���̒萔���Q�Ƃ��邱�ƁB<BR>
     * <BR>
     * �Q�j�ڋq�I�u�W�F�N�g.getSubAccount( )�ɂāA<BR>
     * �Y���ڋq�̕⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *    [getSubAccount����]<BR>
     * �@@�@@[�ڋq.is�M�p�����J��("�w��Ȃ�") == true�̏ꍇ]<BR>
     * �@@�@@�@@SubAccountTypeEnum.�����M�p�������<BR>
     * EQUITY_MARGIN_SUB_ACCOUNT�j<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@SubAccountTypeEnum.������������iEQUITY_SUB_ACCOUNT�j<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@throws WEB3BaseException
     * @@roseuid 429EACB000E6
     */
    public SubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        
        //�P�j���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾���A�Y������ڋq�I�u�W�F�N�g���擾����B 
        //���擾��������ID == 0�̏ꍇ�AThreadLocalSystemAttributeRegistry.getAttribute()��� 
        //����ID���擾����B 
        //   �ݒ�L�[�F�@@ACCOUNT_ID 
        //  ���ݒ�L�[�FACCOUNT_ID�͕K���萔�N���X���쐬���A���̒萔���Q�Ƃ��邱�ƁB
        
        long l_lngAccountId = l_opLoginSec.getAccountId();
        if (l_lngAccountId == 0L)
        {
            log.debug("�擾��������ID == 0�̏ꍇ");
            l_lngAccountId = Long.parseLong(
                (String)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID));           
        }
        //�Q�j�ڋq�I�u�W�F�N�g.getSubAccount( )�ɂāA�Y���ڋq�̕⏕�����I�u�W�F�N�g���擾����B 
        //[getSubAccount����] 
        //[�ڋq.is�M�p�����J��("�w��Ȃ�") == true�̏ꍇ] 
        //�@@SubAccountTypeEnum.�����M�p��������iEQUITY_MARGIN_SUB_ACCOUNT�j 
        //[��L�ȊO�̏ꍇ] 
        //SubAccountTypeEnum.������������iEQUITY_SUB_ACCOUNT�j 

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(l_lngAccountId);
        
            SubAccountTypeEnum l_subAccountType = null;
            
            boolean l_blnMarginAccount = 
                l_mainAccount.isMarginAccountEstablished(
                    WEB3GentradeRepaymentDivDef.DEFAULT);
            
            if (l_blnMarginAccount)
            {
                log.debug("�ڋq.is�M�p�����J��()==true�̏ꍇ");
                l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            else
            {
                log.debug("�ڋq.is�M�p�����J��()==false�̏ꍇ");
                l_subAccountType = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }    
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(l_subAccountType);
            
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate�������Ɖ�)<BR>
     * �������Ɖ�������{�\�ł��邩�`�F�b�N����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jvalidate�������Ɖ�v�Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�O������Ɖ�v(�O������Ɖ�jvalidate�������Ɖ�)<BR>
     * �@@�@@: 1.1.2.2.1 throw<BR> 
     * �@@�@@�u�w������Ȃ��G���[�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�O������Ɖ�v(�O������Ɖ�jvalidate�������Ɖ�)<BR>
     * �@@�@@: 1.2.5.1 throw<BR> 
     * �@@�@@�u��������s�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02177<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �@@�O������Ɖ�v(�O������Ɖ�jvalidate�������Ɖ�)<BR>
     * �@@�@@: 1.2.8.1 validate�O������(�،����, String)<BR> 
     * �@@�@@�Y���f�[�^�Ȃ��̏ꍇ�́A<BR>
     *     �u�w������Ȃ��G���[�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@throws WEB3BaseException
     * �O�������������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@roseuid 429ED278024E
     */
    protected void validateOrderExecuteReference(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqExecuteReferenceRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderExecuteReference(" +
            "WEB3GentradeSubAccount, WEB3FeqExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);               
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.1 (*)���N�G�X�g�f�[�^.�Ɖ�敪 == "�������Ɖ�"�̏ꍇ
        if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(
            l_request.referenceType))
        {
            log.debug("���N�G�X�g�f�[�^.�Ɖ�敪 == '�������Ɖ�'�̏ꍇ");
            
            //1.1.1 validate������t�\( )(������ԊǗ�::validate������t�\)
            //"�Ɖ�"�̃V�X�e���ً}��~���E�o�b�`���`�F�b�N���s���B
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.1.2 (*)���N�G�X�g�f�[�^.�����R�[�h != null�̏ꍇ
            if (l_request.productCode != null)
            {
                log.debug("���N�G�X�g�f�[�^.�����R�[�h != null�̏ꍇ");
                
                //1.1.2.1  get�O����������(Institution, String)
                //�O�������������擾����B 
                //[����] 
                //arg0�F�@@�p�����[�^.�⏕����.getInstitution() 
                //arg1�F�@@���N�G�X�g�f�[�^.�����R�[�h
                
                WEB3FeqProductManager l_feqProductManager = 
                    (WEB3FeqProductManager)l_tradingModule.getProductManager();
                                
                try
                {                    
                    l_feqProductManager.getFeqProduct(
                        l_subAccount.getInstitution(), 
                        l_request.productCode);
                }
                //1.1.2.2 (*)get�O����������()����O���X���[�����ꍇ
                catch (NotFoundException l_ex)                
                {                    
                    log.error("__NotFound �O����������__", l_ex);
                    
                    //1.1.2.2.1 throw �u�w������Ȃ��G���[�v�̗�O���X���[����B
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�O�������������擾�ł��܂���B");
                }                
            }            
        }
        //1.2  (*)���N�G�X�g�f�[�^.�Ɖ�敪 == "��������ꗗ"�̏ꍇ
        else if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(
            l_request.referenceType))
        {
            log.debug("���N�G�X�g�f�[�^.�Ɖ�敪 == '��������ꗗ'�̏ꍇ");
            
            //1.2.1 reset������t�g�����U�N�V����(String)
            //������t�g�����U�N�V������"����"�ɕύX����B 
            //[����] 
            //������t�g�����U�N�V�����F�@@"����"
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CHANGE);
            
            //1.2.2 validate������t�\( )(������ԊǗ�::validate������t�\)
            //"����"�̃V�X�e���ً}��~���E�o�b�`���`�F�b�N���s���B
            boolean l_blnOrderAcceptErr1 = false;
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch(WEB3BaseException l_ex)
            {
                log.debug("error in validate������t�\( )");
                l_blnOrderAcceptErr1 = true;
            }

            //1.2.3 reset������t�g�����U�N�V����(String)
            //������t�g�����U�N�V������"���"�ɕύX����B 
            //[����] 
            //������t�g�����U�N�V�����F�@@"���"
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CANCEL);
            
            //1.2.4 validate������t�\( )(������ԊǗ�::validate������t�\)
            //"���"�̃V�X�e���ً}��~���E�o�b�`���`�F�b�N���s���B
            boolean l_blnOrderAcceptErr2 = false;
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();   
            }
            catch(WEB3BaseException l_ex)
            {
                log.debug("error in validate������t�\( )");
                l_blnOrderAcceptErr2 = true;
            }
            //1.2.5 (*)��Lvalidate������t�\()�������Ƃ���O���X���[�����ꍇ            
            if (l_blnOrderAcceptErr1 && l_blnOrderAcceptErr2)
            {
                //1.2.5.1 throw �u��������s�v�̗�O���X���[����B
                log.debug("��������s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02177,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��������s�B");
            }
            
            //1.2.6 getOrderValidator( )
            //�����`�F�b�N�I�u�W�F�N�g���擾����B            
            WEB3FeqOrderManager l_feqOrderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();      
            
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
            
            //1.2.7 validate����\�ڋq(SubAccount)
            //����\�ڋq�`�F�b�N���s���B 
            //[����] 
            //�⏕�����F�@@�p�����[�^.�⏕����
            OrderValidationResult l_validationResult =
                l_orderValidator.validateSubAccountForTrading(
                    l_subAccount);
                
            log.debug("�����`�F�b�N.validate����\�ڋq(): isFailedResult = " + 
                    l_validationResult.getProcessingResult().isFailedResult());                       
            
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("����\�ڋq�`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����\�ڋq�`�F�b�N�G���[");
            }
            
			//getInstance( )
			WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
			   	(WEB3FeqTypeOrderManagerReusableValidations)
				   WEB3FeqTypeOrderManagerReusableValidations.getInstance();
            
            //validate�O�����������J��(�⏕����)
			l_orderMgrResVal.validateFeqAccountEstablish(l_subAccount);
            
            //1.2.8  (*)���N�G�X�g�f�[�^.�����R�[�h != null�̏ꍇ
            if (l_request.productCode != null)
            {
                log.debug("���N�G�X�g�f�[�^.�����R�[�h != null�̏ꍇ");
                
                //1.2.8.1 validate�O������(�،����, String)
                //�O�����������̎擾�`�F�b�N���s���B 
                //[����] 
                //�،���ЁF�@@�p�����[�^.�⏕����.getInstitution() 
                //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
                
                WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)
                    l_subAccount.getInstitution();
                
                WEB3FeqProduct l_feqProduct = null;
                try
                {
                    l_feqProduct = (WEB3FeqProduct)
                        l_feqOrderManager.validateFeqProduct(
                            l_institution, 
                            l_request.productCode);
                }
                catch(WEB3BaseException l_ex)
                {
                    //�Y���f�[�^�Ȃ��̏ꍇ�́A
                    //�u�w������Ȃ��G���[�v�̗�O���X���[����B               
                    log.debug("�O�������������擾�ł��܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�O�������������擾�ł��܂���B");                
                }

                //1.2.8.2 validate�������(�O����������, �s��)
                //�O��������������̎擾�E����E�����ԃ`�F�b�N���s���B 
                //[����] 
                //�O�����������F�@@validate�O������()�̖߂�l 
                //�s��F�@@validate�O������()�̖߂�l.get�s��()
                l_feqOrderManager.validateTradedProduct(
                    l_feqProduct, 
                    l_feqProduct.getMarket());
            }
            
            //1.2.9 (*)���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ
            if (l_request.marketCode != null)
            {
                log.debug("���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ");
                
                //1.2.9.1 get�s��(, )(�g�����Z�I�u�W�F�N�g�}�l�[�W��::get�s��)
                //�s��I�u�W�F�N�g���擾����B 
                //[����] 
                //�،���ЃR�[�h�F�@@�p�����[�^.�⏕����.getInstitution().get�،���ЃR�[�h() 
                //�s��R�[�h�F�@@���N�G�X�g�f�[�^.�s��R�[�h                
                WEB3GentradeFinObjectManager l_finObjManager = 
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();                               
                
                String l_strInstitutionCode = 
                    l_subAccount.getInstitution().getInstitutionCode();
                
                try
                {      
                    l_finObjManager.getMarket(
                        l_strInstitutionCode, 
                        l_request.marketCode);
                }
                catch (NotFoundException l_ex)
                {
                    log.debug("__NotFoundException__", l_ex);
                    //��O���X���[����
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }   
                //1.2.9.2 validate�戵�\�s��(String, String, String)
                //�戵�\�s��`�F�b�N���s���B 
                //[����] 
                //�،���ЃR�[�h�F�@@�p�����[�^.�⏕����.getInstitution().get�،���ЃR�[�h() 
                //���X�R�[�h�F�@@�p�����[�^.�⏕����.get����X().get���X�R�[�h() 
                //�s��R�[�h�F�@@���N�G�X�g�f�[�^.�s��R�[�h
                String l_strBranchCode = 
                    l_subAccount.getMainAccount().getBranch().getBranchCode();
                
                l_feqOrderManager.validateHandlingPossibleMarket(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_request.marketCode);
            }            
        }        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (create�������ꗗ)<BR>
     * �ڋq�ɊY������O�������������ꗗ���쐬���A<BR>
     * ���X�|���X�f�[�^�ɃZ�b�g����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jcreate�������ꗗ�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@param l_response - (���X�|���X�f�[�^)<BR>
     * �O�������������Ɖ�X�|���X�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 42A41AF801E0
     */
    protected void createProductInformationList(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqExecuteReferenceRequest l_request, 
        WEB3FeqExecuteReferenceResponse l_response) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductInformationList(" +
            "WEB3GentradeSubAccount l_subAccount, WEB3FeqExecuteReferenceRequest l_request, " +
                "WEB3FeqExecuteReferenceResponse l_response)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_request == null || l_response == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 create��������������(String, String, String, Date)
        //����������������쐬����B 
        //[����] 
        //�����R�[�h�F�@@null 
        //�s��R�[�h�F�@@null 
        //����ԋ敪�F�@@null 
        //�������F�@@null
        String l_strQueryString = 
            this.createQueryString(null, null, null, null);
        
        //1.2 create���������f�[�^�R���e�i(�،����, String, String, String, Date)
        //���������f�[�^�R���e�i���쐬����B 
        //[����] 
        //�،���ЁF�@@null 
        //�����R�[�h�F�@@null 
        //�s��R�[�h�F�@@null 
        //����ԋ敪�F�@@null 
        //�������F�@@�Ɩ����t(*1)�̑O�c�Ɠ� 
        //(*1)GtlUtils.getTradingSystem().getBizDate()
        
        Date l_datSysDate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsSysDate = new Timestamp(l_datSysDate.getTime());
        
        WEB3GentradeBizDate l_gentradeBizDate = 
            new WEB3GentradeBizDate(new Timestamp(l_datSysDate.getTime()));
            
        Date l_datPreBizDate = l_gentradeBizDate.calcFeqBizDate(
            l_subAccount.getInstitution().getInstitutionCode(), 
            l_request.marketCode, 
            l_tsSysDate, 
            -1);
        
        String[] l_strQueryDataContainers = 
            this.createQueryDataContainer(
                null, null, null, null, l_datPreBizDate);
            
        //1.3 create�\�[�g����(�O�������\�[�g�L�[[])
        //�\�[�g�������쐬����B 
        //[����] 
        //�\�[�g�L�[�ꗗ�F�@@null
        String l_strSortCond = this.createSortCond(null);

        //1.4 get�����P�ʈꗗ(�⏕����, ProductTypeEnum, String, String[], String)
        //�����ɊY�����钍���P�ʂ̈ꗗ���擾����B 
        //[����] 
        //�⏕�����F�@@�p�����[�^.�⏕���� 
        //�����^�C�v�F�@@ProductTypeEnum.�O������ 
        //��������������F�@@create��������������()�̖߂�l 
        //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
        //�\�[�g�����F�@@create�\�[�g����()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        List l_lisOrderUnits = new ArrayList();
        l_lisOrderUnits = 
            l_feqOrderManager.getOrderUnits(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_strQueryString, 
                l_strQueryDataContainers, 
                l_strSortCond);

        //1.5 (*)�Y���f�[�^�Ȃ�(get�����P�ʈꗗ()�̖߂�l == null)�̏ꍇ
        if (l_lisOrderUnits == null)
        {
            //1.5.1  (*)���X�|���X.�������ꗗ��null���Z�b�g�B
            l_response.productCodeNames = null;
            
            return;
        }
        
        //1.6  TreeMap( )
        //TreeMap�𐶐�����B
        TreeMap l_treeMap = new TreeMap();

        log.debug("�����P�ʈꗗ.size() = " + l_lisOrderUnits.size());
        
        //1.7 (*)get�����P�ʈꗗ()�̗v�f����Loop����
        for (int i = 0; i < l_lisOrderUnits.size(); i++)
        {
            WEB3FeqOrderUnit l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_lisOrderUnits.get(i);
            
            //1.7.1 getProduct( ) �O�������������擾����B
            WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)
                l_feqOrderUnit.getProduct();
            
            if (l_feqProduct == null)
            {
                log.debug("Error in �O�������������擾����");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.7.2 get(arg0 : Object)
            //TreeMap����v�f���擾����B 
            //[����] 
            //arg0�F�@@getProduct()�̖߂�l.�����R�[�h
            WEB3FeqProductCodeNameUnit l_feqProductCodeNameUnit = null;
            
            if (l_treeMap.get(l_feqProduct.getProductCode()) != null)
            {
                l_feqProductCodeNameUnit = 
                    (WEB3FeqProductCodeNameUnit)l_treeMap.get(l_feqProduct.getProductCode());
            }
            //1.7.3 (*)���X�g�ɖ��ǉ�(get()�̖߂�l == null)�̏ꍇ�A�ȉ��̏��������{����B
            if (l_feqProductCodeNameUnit == null)
            {
                //1.7.3.1 �O�������������C���X�^���X�𐶐�����B
                l_feqProductCodeNameUnit = new WEB3FeqProductCodeNameUnit();
                
                //1.7.3.2 (*)�v���p�e�B�Z�b�g
                //(*)�O�������������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                //�����R�[�h   ���@@getProduct()�̖߂�l.�����R�[�h
                l_feqProductCodeNameUnit.productCode = 
                    l_feqProduct.getProductCode();
                
                log.debug("�O�������������.�����R�[�h = " + l_feqProductCodeNameUnit.productCode);
                
                //������     ���@@getProduct()�̖߂�l.get�\��������()�̖߂�l
                l_feqProductCodeNameUnit.productName = 
                    l_feqProduct.getDisplayProductName();
                
                log.debug("�O�������������.������ = " + l_feqProductCodeNameUnit.productName);
                
                //1.7.3.3  put(arg0 : Object, arg1 : Object)
                //TreeMap�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B 
                //[����] 
                //arg0�F�@@getProduct()�̖߂�l.�����R�[�h 
                //arg1�F�@@�v���p�e�B�Z�b�g�����O�������������C���X�^���X
                l_treeMap.put(
                    l_feqProduct.getProductCode(), l_feqProductCodeNameUnit);                
            }
        }
        
        //1.8 values( ).toArray( )
        //�O�������������C���X�^���X�̔z��𐶐�����B
        WEB3FeqProductCodeNameUnit[] l_feqProductCodeNameUnits = 
            new WEB3FeqProductCodeNameUnit[l_treeMap.size()];
        
        l_treeMap.values().toArray(l_feqProductCodeNameUnits);
        
        //1.9 (*)���X�|���X.�������ꗗ = values().toArray()�̖߂�l���Z�b�g����B�@@
        //���߂�l�̗v�f�� == 0�̏ꍇ�́Anull���Z�b�g����B
        if (l_feqProductCodeNameUnits != null)
        {
            l_response.productCodeNames = l_feqProductCodeNameUnits;
        }
        else
        {
            l_response.productCodeNames = null;
        }        
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (create�������׈ꗗ)<BR>
     * �ڋq�ɊY������O�������������׈ꗗ���쐬���A<BR>
     * ���X�|���X�f�[�^�ɃZ�b�g����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jcreate�������׈ꗗ�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@param l_response - (���X�|���X�f�[�^)<BR>
     * �O�������������Ɖ�X�|���X�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 429EF7910069
     */
    protected void createOrderDetailList(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqExecuteReferenceRequest l_request, 
        WEB3FeqExecuteReferenceResponse l_response) 
            throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = "createOrderDetailList(" +
            "WEB3GentradeSubAccount l_subAccount, WEB3FeqExecuteReferenceRequest l_request, " +
                "WEB3FeqExecuteReferenceResponse l_response)";        
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_request == null || l_response == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 create��������������(String, String, String, Date)
        //����������������쐬����B 
        //[����] 
        //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h 
        //�s��R�[�h�F�@@���N�G�X�g�f�[�^.�s��R�[�h 
        //����ԋ敪�F�@@���N�G�X�g�f�[�^.����ԋ敪 
        //�������F�@@���N�G�X�g�f�[�^.������
        String l_strQueryString = 
            this.createQueryString(
                l_request.productCode, l_request.marketCode, 
                l_request.execType, l_request.orderBizDate);
        
        //1.2 ���������f�[�^�R���e�i���쐬����B 
        //[����] 
        //�،���ЁF�@@�p�����[�^.�⏕����.getInstitution() 
        //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h 
        //�s��R�[�h�F�@@���N�G�X�g�f�[�^.�s��R�[�h 
        //����ԋ敪�F�@@���N�G�X�g�f�[�^.����ԋ敪 
        //�������F�@@���N�G�X�g�f�[�^.������
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        
        String[] l_strQueryDataContainer = 
            this.createQueryDataContainer(
                l_institution, 
                l_request.productCode, 
                l_request.marketCode, 
                l_request.execType, 
                l_request.orderBizDate);
        
        //1.3 create�\�[�g����(�O�������\�[�g�L�[[])
        //�\�[�g�������쐬����B 
        //[����] 
        //�\�[�g�L�[�ꗗ�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.4 get�����P�ʈꗗ(�⏕����, ProductTypeEnum, String, String[], String)
        //�����ɊY�����钍���P�ʂ̈ꗗ���擾����B 
        //[����] 
        //�⏕�����F�@@�p�����[�^.�⏕���� 
        //�����^�C�v�F�@@ProductTypeEnum.�O������ 
        //��������������F�@@create��������������()�̖߂�l 
        //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
        //�\�[�g�����F�@@create�\�[�g����()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        List l_lisOrderUnits = new ArrayList();
        
        l_lisOrderUnits = 
            l_feqOrderManager.getOrderUnits(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_strQueryString, 
                l_strQueryDataContainer, 
                l_strSortCond);
        
        if (l_lisOrderUnits == null)
        {
            log.debug("Error in �����P�ʃI�u�W�F�N�g���擾����");
			//���X�|���X�f�[�^�ɏ����l���Z�b�g����B
			//���X�|���X.�����ꗗ  ���@@null
			l_response.executeGroups = null;
            
			//���X�|���X.�\���y�[�W�ԍ�   ���@@0
			l_response.pageIndex = 0 + "";
            
            //���X�|���X.���y�[�W�� ���@@0
			l_response.totalPages = 0 + "";
            
			//���X�|���X.�����R�[�h��    ���@@0
			l_response.totalRecords = 0 + "";
            
			return;
        }
        
        //1.5 remove�J�z�������P��(�O�����������P��[])
        //�J�z�������̏������s���B 
        //[����] 
        //�����P�ʈꗗ�F�@@get�����P�ʈꗗ()�̖߂�l.toArray()
        WEB3FeqOrderUnit[] l_feqOrderUnits = 
            new WEB3FeqOrderUnit[l_lisOrderUnits.size()];
        
        l_lisOrderUnits.toArray(l_feqOrderUnits);
        WEB3FeqOrderUnit[] l_carryOrderUnits = 
            l_feqOrderManager.removeCarryOverOriginOrder(l_feqOrderUnits);
        
        WEB3FeqOrderUnit[] l_feqOrderUnitResults = l_carryOrderUnits;
                         
        //1.6 (*)���N�G�X�g�f�[�^.�Ɖ�敪 == "��������ꗗ"�̏ꍇ
        if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(
            l_request.referenceType))
        {
            log.debug("���N�G�X�g�f�[�^.�Ɖ�敪 == '��������ꗗ'�̏ꍇ");
            
            //1.6.1 remove��������s�����P��(�O�����������P��[])
            //��������s�Ȓ����P�ʂ���������B 
            //[����] 
            //�����P�ʈꗗ�F�@@remove�J�z�������P��()�̖߂�l
            WEB3FeqOrderUnit[] l_changeCancelOrderUnits = 
                this.removeChangeCancelNotOrderUnit(l_carryOrderUnits);
            
            l_feqOrderUnitResults = l_changeCancelOrderUnits;
        }
        //remove��������s�����P��()���\�b�h���R�[�����Ă���ꍇ�́A
        //���̖߂�l�ɂ��Ĕ��ʂ���B
        //1.7 (*)�Y���f�[�^�Ȃ�(remove�J�z�������P��()�̖߂�l == null)�̏ꍇ
        if (l_feqOrderUnitResults == null)
        {
            log.debug("remove�J�z�������P��()�̖߂�l == null�̏ꍇ");
            //1.7.1 (*)�v���p�e�B�Z�b�g
            //(*)���X�|���X�f�[�^�ɏ����l���Z�b�g����B
            //���X�|���X.�����ꗗ  ���@@null
            l_response.executeGroups = null;
            
            //���X�|���X.�\���y�[�W�ԍ�   ���@@0
            l_response.pageIndex = 0 + "";
            
            //���X�|���X.���y�[�W�� ���@@0
            l_response.totalPages = 0 + "";
            
            //���X�|���X.�����R�[�h��    ���@@0
            l_response.totalRecords = 0 + "";
            
            //1.7.2 
            return;
        }
        //1.8 WEB3PageIndexInfo()
        //WEB3PageIndexInfo�C���X�^���X�𐶐�����B 
        //[����] 
        //arg0�F�@@remove�J�z�������P��()�̖߂�l 
        //��remove��������s�����P��()���R�[�����Ă���ꍇ�́A���̖߂�l 
        //arg1�F�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ� 
        //arg2�F�@@���N�G�X�g�f�[�^.�y�[�W���\���s��
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_feqOrderUnitResults, 
            Integer.parseInt(l_request.pageIndex), 
            Integer.parseInt(l_request.pageSize));
        
        //1.9 getArrayReturned(arg0 : Class)
        //�\���Ώۂ̒����P�ʈꗗ���擾����B 
        //[����] 
        //arg0�F�@@�O�����������P��.class
        l_feqOrderUnitResults = (WEB3FeqOrderUnit[])
            l_pageIndexInfo.getArrayReturned(
                WEB3FeqOrderUnit.class);
        
        List l_lisFeqExecuteGroup = new ArrayList();
        
        //1.10 (*)�\���Ώۂ̒����P��(getArrayReturned()�̖߂�l)����Loop����
        for (int i = 0; i < l_feqOrderUnitResults.length; i++)
        {
            WEB3FeqOrderUnit l_feqOrderUnitLoop = l_feqOrderUnitResults[i];
            
            //1.10.1 get�s��( )(�O�����������P��::get�s��)
            //�s��I�u�W�F�N�g���擾����B
            Market l_market = l_feqOrderUnitLoop.getMarket();
            
            //1.10.2 reset�s��R�[�h(String)(������ԊǗ�::reset�s��R�[�h)
            //����J�����_�R���e�L�X�g.�s��R�[�h���ăZ�b�g����B 
            //[����] 
            //�s��R�[�h�F�@@get�s��()�̖߂�l.�s��R�[�h
            String l_strMarketCode = null;
            if (l_market != null)
            {
                l_strMarketCode = l_market.getMarketCode();
            }
                
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            //���N�G�X�g�f�[�^.�Ɖ�敪 == "��������ꗗ"�̏ꍇ�́A
            //���Ƀ`�F�b�N�ς݂ł���ׁAis������������P��()�̖߂�l�́A�ꗥtrue�Ƃ���B
            boolean l_blnIsChangeCancelOrder = true;
            
            //1.10.3  (*)���N�G�X�g�f�[�^.�Ɖ�敪 == "�������Ɖ�"�̏ꍇ
            if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(
                l_request.referenceType))
            {
                log.debug("���N�G�X�g�f�[�^.�Ɖ�敪 == '�������Ɖ�'�̏ꍇ");
                
                //1.10.3.1 is��������\�����P��(�O�����������P��)
                //��������\�Ȓ����P�ʂ��ǂ������ʂ���B 
                //[����] 
                //�����P�ʁF�@@�����Ώۂ̒����P��
                l_blnIsChangeCancelOrder = 
                    this.isChangeCancelOrderUnit(l_feqOrderUnitLoop);
            }
            //1.10.4 is�����\(�O�����������P��)
            //�����\�Ȓ����P�ʂł��邩�ǂ������ʂ���B 
            //[����] 
            //�����P�ʁF�@@�����Ώۂ̒����P��
            boolean l_blnIsChangePossible = 
                this.isChangePossible(l_feqOrderUnitLoop);

            //1.10.5 is����\(�O�����������P��)
            //����\�Ȓ����P�ʂł��邩�ǂ������ʂ���B 
            //[����] 
            //�����P�ʁF�@@�����Ώۂ̒����P��
            boolean l_blnIsCancelPossible = 
                this.isCancelPossible(l_feqOrderUnitLoop);

            //1.10.6 getProduct( )
            //�O�����������I�u�W�F�N�g���擾����B
            WEB3FeqProduct l_feqProduct = 
                (WEB3FeqProduct)l_feqOrderUnitLoop.getProduct();
            
            if (l_feqProduct == null)
            {
                log.debug("Error in �O�������������擾����");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.10.7 is���t( )(�O�����������P��::is���t)
            //���t�������ǂ������ʂ���B 
            boolean l_blnIsBuy = l_feqOrderUnitLoop.isBuy();

            //1.10.8 is�w�l( )(�O�����������P��::is�w�l)
            //�w�l�������ǂ������ʂ���B
            boolean l_blnIsLimit = l_feqOrderUnitLoop.isLimitOrder();
            
            //1.10.9 get�����󋵋敪( )(�O�����������P��::get�����󋵋敪)
            //�����󋵋敪���擾����B
            String l_strTransactionStatetype = 
                l_feqOrderUnitLoop.getTransactionStateType();
            
            //1.10.10 get���s�����iSONAR�j(String)
            //���s�����iSONAR�j���擾����B 
            //[����] 
            //���s�����F�@@�����Ώۂ̒����P��.���s����
            String l_strExecCondTypeSonar = 
                l_feqOrderManager.getExecutionConditionTypeSonar(                
                    l_feqOrderUnitLoop.getExecutionConditionType().intValue() + "");
                
            //1.10.11 is�o����܂Œ����P��(FeqOrderUnit)
            //�o����܂Œ������ǂ������ʂ���B 
            //[����] 
            //�����P�ʁF�@@�����Ώۂ̒����P��
            boolean l_blnIsCarriedOrderUnit = 
                l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnitLoop);
            
            //1.10.12 �O�������������׃C���X�^���X�𐶐�����B
            WEB3FeqExecuteGroup l_feqExecuteGroup = new WEB3FeqExecuteGroup();
            
            //1.10.13 (*)�v���p�e�B�Z�b�g
            //(*)�O�������������׃C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            FeqOrderUnitParams l_feqOrderUnitParams = 
                new FeqOrderUnitParams(
                    (FeqOrderUnitRow) l_feqOrderUnitLoop.getDataSourceObject());
            
            log.debug("�����P��Params = " + l_feqOrderUnitParams);
            
            //����ID            ���@@�����P��.����ID
            l_feqExecuteGroup.id = l_feqOrderUnitParams.getOrderId() + "";
            
            //�����R�[�h       ���@@getProduct()�̖߂�l.�����R�[�h
            l_feqExecuteGroup.productCode = l_feqProduct.getProductCode();
            
            //���n�����R�[�h     ���@@getProduct()�̖߂�l.���n�����R�[�h
            l_feqExecuteGroup.localProductCode = 
                l_feqProduct.getOffshoreProductCode();
            
            //������         ���@@getProduct()�̖߂�l.get�\��������()�̖߂�l
            l_feqExecuteGroup.productName = 
                l_feqProduct.getDisplayProductName();
            
            //��������敪      ���@@�����P��.�ŋ敪 == "���"�̏ꍇ�A"���"���Z�b�g�B
            //                                  "����"�̏ꍇ�A"����"���Z�b�g�B
            if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitParams.getTaxType()))
            {
                l_feqExecuteGroup.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitParams.getTaxType()))
            {
                l_feqExecuteGroup.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            
            //�s��R�[�h       ���@@get�s��()�̖߂�l.�s��R�[�h
            l_feqExecuteGroup.marketCode = l_strMarketCode;
            
            //�����敪        ���@@is���t()�̖߂�l == true�̏ꍇ�A"���t"���Z�b�g�B�@@
            //                 �ȊO�A"���t"���Z�b�g�B
            if (l_blnIsBuy)
            {
                l_feqExecuteGroup.dealingType = WEB3BuySellTypeDef.BUY;
            }
            else
            {
                l_feqExecuteGroup.dealingType = WEB3BuySellTypeDef.SELL;
            }            
            //���ϋ敪        ���@@�����P��.���ϋ敪
            l_feqExecuteGroup.settleDiv = l_feqOrderUnitParams.getSettleDiv();
            
            //���s����        ���@@get���s�����iSONAR�j�̖߂�l
            l_feqExecuteGroup.execCondType = l_strExecCondTypeSonar;
            
            //��������        ���@@�����P��.��������
            l_feqExecuteGroup.orderCondType = 
                l_feqOrderUnitParams.getOrderConditionType();
            
            //������         ���@@�����P��.������
            l_feqExecuteGroup.orderBizDate = WEB3DateUtility.getDate(
                l_feqOrderUnitParams.getBizDate(), "yyyyMMdd");
            
            //�����L������      ���@@is�o����܂Œ����P��()�̖߂�l == true�̏ꍇ�A
            //                   �����P��.�����������t���Z�b�g�B     
            if (l_blnIsCarriedOrderUnit)
            {
                l_feqExecuteGroup.expirationDate = 
                    l_feqOrderUnitParams.getExpirationDate();
            }            
            //��������        ���@@�����P��.�쐬���t
            l_feqExecuteGroup.orderDate = 
                l_feqOrderUnitParams.getCreatedTimestamp();
            
            //��������        ���@@�����P��.����
            l_feqExecuteGroup.orderQuantity = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqOrderUnitParams.getQuantity());
            
            //�����P���敪      ���@@is�w�l()�̖߂�l == true�̏ꍇ�A"�w�l"���Z�b�g�B�@@
            //                   �ȊO�A"���s"���Z�b�g�B
            if (l_blnIsLimit)  
            {
                l_feqExecuteGroup.orderPriceDiv = 
                    WEB3OrderPriceDivDef.LIMIT_PRICE;
                
                //�����P��        ���@@is�w�l()�̖߂�l == true�̏ꍇ�A�����P��.�w�l���Z�b�g�B
                l_feqExecuteGroup.limitPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqOrderUnitParams.getLimitPrice());
            }
            else
            {
                l_feqExecuteGroup.orderPriceDiv = 
                    WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            
            //�ʉ݃R�[�h       ���@@�����P��.�ʉ݃R�[�h
            l_feqExecuteGroup.currencyCode = 
                l_feqOrderUnitParams.getCurrencyCode();
            
            //(*1)�����P��.isUnExecuted() == false�̏ꍇ�̂݃Z�b�g�B
            if(!l_feqOrderUnitLoop.isUnexecuted())
            {
                log.debug("�����P��.isUnExecuted() == false�̏ꍇ");
                //��萔��(*1)        ���@@�����P��.��萔��
                l_feqExecuteGroup.execQuantity = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqOrderUnitParams.getExecutedQuantity());
            						
				//���P��(*1)        ��
				//�����P��.���v�����z(�O��) �^ �����P��.��萔��
                BigDecimal l_bdExecutedAmount =
                    new BigDecimal(
                        String.valueOf(l_feqOrderUnitParams.getExecutedAmount()));
                BigDecimal l_bdExecutedQuantity =
                    new BigDecimal(
                        String.valueOf(l_feqOrderUnitParams.getExecutedQuantity()));
                BigDecimal l_bdExecPrice =
                    l_bdExecutedAmount.divide(l_bdExecutedQuantity, 6, BigDecimal.ROUND_HALF_EVEN);
                l_feqExecuteGroup.execPrice =
                    WEB3StringTypeUtility.formatNumber(l_bdExecPrice.doubleValue());
            }
            
            //��n���        ���@@�����P��.�T�Z��n���
            l_feqExecuteGroup.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqOrderUnitParams.getEstimatedPrice());
            
            //��n����i�O�݁j        ���@@�����P��.�T�Z��n����i�O�݁j
            l_feqExecuteGroup.foreignEstimatedPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqOrderUnitParams.getFEstimatedPrice());
            
            //������        ���@@get�����󋵋敪()�̖߂�l
            l_feqExecuteGroup.transactionStateType = l_strTransactionStatetype;
            
            //�����\�t���O     ���@@is��������\�����P��()�̖߂�l == true ���� 
            //                   is�����\()�̖߂�l == true�̏ꍇ�Atrue���Z�b�g�B
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�Afalse���Z�b�g�B
            if (l_blnIsChangeCancelOrder && l_blnIsChangePossible)
            {
                l_feqExecuteGroup.changeFlag = true;                
            }
            else
            {
                l_feqExecuteGroup.changeFlag = false;
            }
            //����\�t���O     ���@@is��������\�����P��()�̖߂�l == true ���� 
            //                   is����\()�̖߂�l == true�̏ꍇ�Atrue���Z�b�g�B
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�Afalse���Z�b�g�B
            if (l_blnIsChangeCancelOrder && l_blnIsCancelPossible)
            {
                l_feqExecuteGroup.cancelFlag = true;
            }
            else
            {
                l_feqExecuteGroup.cancelFlag = false;
            }
            
            //(*2)this.get�㗝���͎�() != null(�R�[���Z���^�[����̎Q��)�̏ꍇ�̂݃Z�b�g�B
            if (this.getTrader() != null)
            {
                //�����`���l��(*2)      ���@@�����P��.���񒍕��̒����`���l��
                l_feqExecuteGroup.orderChannel = 
                    l_feqOrderUnitParams.getOrderChanel();
                
                //�����o�H�敪(*2)      ���@@�����P��.�����o�H�敪
                l_feqExecuteGroup.orderRootDiv = 
                    l_feqOrderUnitParams.getOrderRootDiv();
                
                //���҃R�[�h(*2)       ���@@
                //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader(�����P��.�����ID).���҃R�[�h���Z�b�g�B
                
                WEB3GentradeFinObjectManager l_finObjManager = 
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                try
                {
                	//(�����P��.�����ID != null)�̏ꍇ�̂ݏ������s
                	if(!l_feqOrderUnitParams.getTraderIdIsNull()){
						String l_strTraderCode = l_finObjManager.getTrader(
							l_feqOrderUnitParams.getTraderId()).getTraderCode();
                    
						l_feqExecuteGroup.traderCode = l_strTraderCode;
                	}
                     
                }
                catch (NotFoundException l_ex)
                {
                    log.debug("__NotFoundException__", l_ex);
                    //��O���X���[����
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }   
            }
            //�������ꗗ(*1)      ���@@this.create�������ꗗ(�����P��)
            //(*1)�����P��.isUnExecuted() == false�̏ꍇ�̂݃Z�b�g�B
            if(!l_feqOrderUnitLoop.isUnexecuted())
            {
                l_feqExecuteGroup.executeUnits = 
                    this.createExecuteUnits(l_feqOrderUnitLoop); 
            }
            //1.10.14 add(arg0 : Object)
            //ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B 
            //[����] 
            //arg0�F�@@�v���p�e�B�Z�b�g�����O�������������׃C���X�^���X
            l_lisFeqExecuteGroup.add(l_feqExecuteGroup);
        }

        //1.11 toArray( )
        //�O�������������ׂ̔z��𐶐�����B
        WEB3FeqExecuteGroup[] l_feqExecuteGroups = 
            new WEB3FeqExecuteGroup[l_lisFeqExecuteGroup.size()];
        
        l_lisFeqExecuteGroup.toArray(l_feqExecuteGroups);

        //1.12 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        
        //���X�|���X.�����ꗗ  ���@@toArray()�̖߂�l
        l_response.executeGroups = l_feqExecuteGroups;
        
        //���X�|���X.�\���y�[�W�ԍ�   ���@@WEB3PageIndexInfo.getPageIndex()
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        
        //���X�|���X.���y�[�W�� ���@@WEB3PageIndexInfo.getTotalPages()
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        
        //���X�|���X.�����R�[�h��    ���@@WEB3PageIndexInfo.getTotalRecords()
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get�������ꗗ)<BR>
     * �������̈ꗗ���쐬���A�ԋp����B<BR>
     * <BR>
     * �ԋp�l�A�ԋp���͈ȉ��̂Ƃ���B<BR>
     * �@@�@@�Ɩ����t(*1)�̑O�c�Ɠ��@@���Ɖ�敪 == �������Ɖ�̏ꍇ�̂݁B<BR>
     * �@@�A�Ɩ����t<BR>
     * �@@�B�p�����[�^.�s��R�[�h�ꗗ�̎s��ɂ��Ĕ��������擾���A<BR>
     * �@@�@@�@@�A�A�ƈقȂ�ꍇ�͒ǉ��B(�@@�A�A�Ɠ���̏ꍇ�̓Z�b�g���Ȃ�)<BR>
     * �@@�@@���B�͈�����̔������̍l��<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�Ɖ�敪 == "�������Ɖ�"�̏ꍇ�A<BR>
     * �@@�@@�@@�Ɩ����t�̑O�c�Ɠ���ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@�Ɩ����t��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@�p�����[�^.�s��R�[�h�ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�S�|�P�j�@@������ԊǗ�.reset�s��R�[�h()���R�[������B<BR>
     * <BR>
     * �@@�@@[reset�s��R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����Ώۂ̎s��R�[�h<BR>
     * �@@�S�|�Q�j�@@������ԊǗ�.get������()���R�[������B<BR>
     * �@@�S�|�R�j�@@�S�|�Q�j�̖߂�l��ArrayList�Ɋ܂܂�Ă��Ȃ��ꍇ�́A<BR>
     * �@@�@@ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_strReferenceType - (�Ɖ�敪)<BR>
     * �Ɖ�敪<BR>
     * <BR>
     * 0�F�������Ɖ�<BR>
     * 1�F��������ꗗ<BR>
     * @@param l_strMarketCodeList - (�s��R�[�h�ꗗ)<BR>
     * ���X���戵�\�Ȏs��R�[�h�̈ꗗ
     * @@return java.util.Date[]
     * @@throws WEB3BaseException
     * @@roseuid 429EFBBC000B
     */
    protected Date[] getOrderBizDateList(
        String l_strReferenceType, 
        String[] l_strMarketCodes) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDateList(" +
            "String l_strReferenceType, String[] l_strMarketCodes)";
        
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�𐶐�����B 
        List l_lisBizDates = new ArrayList();
        
        //�Ɩ����t (*1)GtlUtils.getTradingSystem().getBizDate()
        Date l_datSysDate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsSysDate = new Timestamp(l_datSysDate.getTime());
        
        //�Q�j�@@�p�����[�^.�Ɖ�敪 == "�������Ɖ�"�̏ꍇ�A 
        //�@@�@@�Ɩ����t�̑O�c�Ɠ���ArrayList�ɒǉ�����B   //QA88
        if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(
            l_strReferenceType))
        {
            log.debug("�p�����[�^.�Ɖ�敪 == '�������Ɖ�'�̏ꍇ");
            
            //�s��R�[�h�ꗗ�ɑΉ�����S�c�Ɠ���S�Ď擾��
            for (int i = 0; i < l_strMarketCodes.length; i++)
            {
                WEB3GentradeBizDate l_gentradeBizDate = 
                    new WEB3GentradeBizDate(
                        new Timestamp(l_datSysDate.getTime()));
                    
                Date l_datPreBizDate = l_gentradeBizDate.calcFeqBizDate(
                    this.getSubAccount().getInstitution().getInstitutionCode(), 
                    l_strMarketCodes[i], 
                    l_tsSysDate, 
                    -1);
                
                log.debug("�Ɩ����t�̑O�c�Ɠ� = " + l_datPreBizDate);
                
                boolean l_blnIsContain = 
                    WEB3Toolkit.contain(l_lisBizDates, l_datPreBizDate);
                
                //�d�����Ȃ��l�̂�ArrayList�ɒǉ����ĉ������B
                if (!l_blnIsContain)
                {
                    l_lisBizDates.add(l_datPreBizDate);
                }                
            }            
        }
        
        //�R�j�@@�Ɩ����t��ArrayList�ɒǉ�����B 
        l_lisBizDates.add(l_datSysDate);
        
        //�S�j�@@�p�����[�^.�s��R�[�h�ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B 
        //�S�|�P�j�@@������ԊǗ�.reset�s��R�[�h()���R�[������B 
        //[reset�s��R�[�h()�Ɏw�肷�����] 
        //�@@�s��R�[�h�F�@@�����Ώۂ̎s��R�[�h 
        //�S�|�Q�j�@@������ԊǗ�.get������()���R�[������B 
        //�S�|�R�j�@@�S�|�Q�j�̖߂�l��ArrayList�Ɋ܂܂�Ă��Ȃ��ꍇ�́A 
        //ArrayList�ɒǉ�����B 
        for (int i = 0; i < l_strMarketCodes.length; i++)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(
                l_strMarketCodes[i]);
            
            Date l_datBizDte = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            log.debug("������ԊǗ�.get������() = " + l_datBizDte);
            boolean l_blnIsContain = 
                WEB3Toolkit.contain(l_lisBizDates, l_datBizDte);
            
            if (!l_blnIsContain)
            {
                l_lisBizDates.add(l_datBizDte);
            }
        }
        
        //�T�j�@@ArrayList.toArray()�̖߂�l��ԋp����B
        log.debug("�������ꗗ.size = " + l_lisBizDates.size());
        Date[] l_datOrderBizDates = new Date[l_lisBizDates.size()];
        l_lisBizDates.toArray(l_datOrderBizDates);
        
        log.exiting(STR_METHOD_NAME);
        return l_datOrderBizDates;
    }
    
    /**
     * (create��������������)<BR>
     * (createQueryString)<BR>
     * ����������������쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@���������쐬<BR>
     * �@@�p�����[�^.�����R�[�h != null�̏ꍇ�A����������<BR>
     * �@@��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += " and product_id = ?"<BR>
     * <BR>
     * �Q�j�@@�s������쐬<BR>
     * �@@�p�����[�^.�s��R�[�h != null�̏ꍇ�A�s�������<BR>
     * �@@��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += " and market_id = ?"<BR>
     * <BR>
     * �R�j�@@����ԏ����쐬<BR>
     * �@@�p�����[�^.����ԋ敪 != null�̏ꍇ�A����ԏ�����<BR>
     * �@@��������������ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.����ԋ敪 == "�����"�̏ꍇ]<BR>
     * �@@�@@�������������� += " and (executed_quantity is null"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " or executed_quantity = ?)"<BR>
     * <BR>
     * �@@[�p�����[�^.����ԋ敪 == "�ꕔ����" �܂��� "��菈����(�ꕔ����)" �̏ꍇ]<BR>
     * �@@�@@�������������� += " and executed_quantity is not null"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " and executed_quantity != ?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " and executed_quantity < confirmed_quantity"<BR>
     * <BR>
     * �@@[�p�����[�^.����ԋ敪 == "�S������" �܂��� "��菈����(�S������)" �̏ꍇ]<BR>
     * �@@�@@�������������� += " and executed_quantity is not null"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " and executed_quantity != ?"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ " and executed_quantity = confirmed_quantity"<BR>
     * <BR>
     * �S�j�@@������ԏ����쐬<BR> 
     * �@@�p�����[�^.����ԋ敪 != null�̏ꍇ�A������ԏ�����<BR>
     * �@@��������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += " and temporary_execution_flag = ?"<BR>
     * <BR>
     * �T�j�@@�����������쐬<BR>
     * �@@��������������������������ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.������ == null�̏ꍇ]<BR>
     * �@@�@@�������������� += " and biz_date >= ?"<BR>
     * <BR>
     * �@@[�p�����[�^.������ != null�̏ꍇ]<BR>
     * �@@�@@�������������� += " and biz_date = ?"<BR>
     * <BR>
     * �U�j�@@�쐬�������������������ԋp����B<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@param l_strExecType - (����ԋ敪)<BR>
     * ����ԋ敪<BR>
     * <BR>
     * 0�F�����<BR>
     * 1�F�ꕔ����<BR>
     * 2�F�S������<BR>
     * 3�F��菈����(�ꕔ����)<BR>
     * 4�F��菈����(�S������)<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * ������<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@return String
     * @@roseuid 42A3B2A901F5
     */
    protected String createQueryString(
        String l_strProductCode, 
        String l_strMarketCode, 
        String l_strExecType, 
        Date l_datOrderBizDate) 
    {
        final String STR_METHOD_NAME = "createQueryString(" +
            "String l_strProductCode, String l_strMarketCode, " +
            "String l_strExecType, Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���������쐬 
        //�p�����[�^.�����R�[�h != null�̏ꍇ�A���������� 
        //��������������ɒǉ�����B 
        //�������������� += " and product_id = ?" 
        String l_strQueryString = "";
        if (l_strProductCode != null)
        {
            l_strQueryString += " and product_id = ?";
        }        
        //�Q�j�@@�s������쐬 
        //�p�����[�^.�s��R�[�h != null�̏ꍇ�A�s������� 
        //��������������ɒǉ�����B 
        //�������������� += " and market_id = ?" 
        if (l_strMarketCode != null)
        {
            l_strQueryString += " and market_id = ?";
        }
        //�R�j�@@����ԏ����쐬 
        //�p�����[�^.����ԋ敪 != null�̏ꍇ�A����ԏ����� 
        //��������������ɒǉ�����B 

        if (l_strExecType != null)
        {
            //[�p�����[�^.����ԋ敪 == "�����"�̏ꍇ] 
            //�������������� += " and (executed_quantity is null" 
            //�@@�@@�@@�@@�@@�@@�@@ + " or executed_quantity = ?)"            
            if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(
                l_strExecType))
            {
                l_strQueryString += " and (executed_quantity is null"  + 
                    " or executed_quantity = ?)" ;
            }
            
            //[�p�����[�^.����ԋ敪 == "�ꕔ����" �܂��� "��菈����(�ꕔ����)" �̏ꍇ] 
            //�������������� += " and executed_quantity is not null" 
            //�@@�@@�@@�@@�@@�@@�@@ + " and executed_quantity != ?" 
            //�@@�@@�@@�@@�@@�@@�@@ + " and executed_quantity < confirmed_quantity"            
            else if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(l_strExecType)
                        || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(l_strExecType))
            {
                l_strQueryString += " and executed_quantity is not null"  + 
                    " and executed_quantity != ?" + 
                    " and executed_quantity < confirmed_quantity";
            }   
            
            //[�p�����[�^.����ԋ敪 == "�S������" �܂��� "��菈����(�S������)" �̏ꍇ] 
            //�������������� += " and executed_quantity is not null" 
            //�@@�@@�@@�@@�@@�@@�@@ + " and executed_quantity != ?" 
            //�@@�@@�@@�@@�@@�@@�@@ + " and executed_quantity = confirmed_quantity" 
            else if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(l_strExecType)
                        || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(l_strExecType))
            {
                l_strQueryString += " and executed_quantity is not null"  + 
                    " and executed_quantity != ?" + 
                    " and executed_quantity = confirmed_quantity";
            }
        }
        
        // ������ԏ����쐬
        // ������ԏ�������������������ɒǉ�����B
        if (l_strExecType != null)
        {
            l_strQueryString += " and temporary_execution_flag = ?";
        }
        
        //�S�j�@@�����������쐬 
        //��������������������������ɒǉ�����B        
        //[�p�����[�^.������ == null�̏ꍇ] 
        //�@@�������������� += " and biz_date >= ?" 
        if (l_datOrderBizDate == null)
        {
            l_strQueryString += " and biz_date >= ?";
        }
        //[�p�����[�^.������ != null�̏ꍇ] 
        //�@@�������������� += " and biz_date = ?" 
        else
        {
            l_strQueryString += " and biz_date = ?";
        }
        
        //�T�j�쐬�������������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * (createQueryDataContainer)<BR>
     * ���������f�[�^�R���e�i(�FString[]�j���쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���������쐬<BR>
     * �@@�p�����[�^.�����R�[�h != null�̏ꍇ�A����ID��<BR>
     * �@@ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@������ID�́A�O�������v���_�N�g�}�l�[�W��.getFeqProduct()�ɂĎ擾����<BR>
     * �@@�@@�O����������.����ID���g�p����B<BR>
     * <BR>
     * �@@�@@[getFeqProduct()�Ɏw�肷�����]<BR>
     * �@@�@@�@@arg0�F�@@�p�����[�^.�،����<BR>
     * �@@�@@�@@arg1�F�@@�p�����[�^.�����R�[�h<BR>
     * <BR>
     * �R�j�@@�s������쐬<BR>
     * �@@�p�����[�^.�s��R�[�h != null�̏ꍇ�A�s��ID��<BR>
     * �@@ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@���s��ID�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɂĎ擾����<BR>
     * �@@�@@�s��.�s��ID���g�p����B<BR>
     * <BR>
     * �@@�@@[get�s��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h<BR>
     * <BR>
     * <BR>
     * �S�j�@@����ԏ����쐬<BR>
     * �@@�p�����[�^.����ԋ敪 != null�̏ꍇ�A"0"��<BR>
     * �@@ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�@@������ԏ����쐬<BR> 
     * �@@�p�����[�^.����ԋ敪 != null�̏ꍇ�A�ȉ��̉�����ԏ�����ArrayList�ɒǉ�����B<BR> 
     * <BR>
     * �@@[�p�����[�^.����ԋ敪 == "��菈����(�ꕔ����)" �܂��� "��菈����(�S������)" �̏ꍇ]<BR> 
     * �@@�@@�E"�����"<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR> 
     * �@@�@@�EDEFAULT<BR>
     * <BR>
     * �U�j�@@�����������쐬<BR>
     * �@@�ȉ��̔�����������ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.������ == null�̏ꍇ]<BR>
     * �@@�@@�E�Ɩ����t(*1)��YYYYMMDD<BR>
     * <BR>
     * �@@[�p�����[�^.������ != null�̏ꍇ]<BR>
     * �@@�@@�E�p�����[�^.��������YYYYMMDD<BR>
     * <BR>
     * �V�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@param l_strExecType - (����ԋ敪)<BR>
     * ����ԋ敪<BR>
     * <BR>
     * 0�F�����<BR>
     * 1�F�ꕔ����<BR>
     * 2�F�S������<BR>
     * 3�F��菈����(�ꕔ����)<BR>
     * 4�F��菈����(�S������)<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * ������<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 42A3B61D03D9
     */
    protected String[] createQueryDataContainer(
            WEB3GentradeInstitution l_institution, 
            String l_strProductCode, 
            String l_strMarketCode, 
            String l_strExecType, 
            Date l_datOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(" +
            "WEB3GentradeInstitution l_institution, String l_strProductCode, " +
            "String l_strMarketCode, String l_strExecType, " +
            "Date l_datOrderBizDate) ";        
        
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�𐶐�����B
        List l_lisQueryContainer = new ArrayList();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //�Q�j�@@���������쐬 
        //�p�����[�^.�����R�[�h != null�̏ꍇ�A����ID��ArrayList�ɒǉ�����B 
        if (l_strProductCode != null)
        {
            log.debug("�p�����[�^.�����R�[�h != null�̏ꍇ");            

            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            
            WEB3FeqProductManager l_feqProductManager = 
                (WEB3FeqProductManager)l_tradingModule.getProductManager();
                    
            FeqProduct l_feqProduct = null;
            try
            {
                l_feqProduct = 
                    l_feqProductManager.getFeqProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex)                
            {
                log.debug("__NotFound �O����������__", l_ex);
                //��O���X���[����
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            String l_strProductId = l_feqProduct.getProductId() + "";
            l_lisQueryContainer.add(l_strProductId);         
        }
    
        //�R�j�@@�s������쐬 
        //�p�����[�^.�s��R�[�h != null�̏ꍇ�A�s��ID�� 
        //ArrayList�ɒǉ�����B 

        //���s��ID�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɂĎ擾���� 
        //�@@�s��.�s��ID���g�p����B 

        //�@@[get�s��()�Ɏw�肷�����] 
        //�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h 
        //�@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h 
        WEB3GentradeFinObjectManager l_finObjManager = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;

        if (l_strMarketCode != null)
        {
            log.debug("�p�����[�^.�s��R�[�h != null�̏ꍇ");
            try
            {
                l_market = l_finObjManager.getMarket(
                    l_institution.getInstitutionCode(), 
                    l_strMarketCode);
            }
            catch (NotFoundException l_ex)
            {
                log.debug("__NotFoundException__", l_ex);
                //��O���X���[����
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            String l_strMarketId = l_market.getMarketId() + "";
            l_lisQueryContainer.add(l_strMarketId);
        }
        
        //�S�j�@@����ԏ����쐬 
        //�p�����[�^.����ԋ敪 != null�̏ꍇ�A"0"��ArrayList�ɒǉ�����B 
        if (l_strExecType != null)
        {
            log.debug("�p�����[�^.����ԋ敪 != null�̏ꍇ");
            l_lisQueryContainer.add(WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE);
        }
        
        // ������ԏ����쐬
        if (l_strExecType != null)
        {
            // [�p�����[�^.����ԋ敪 == "��菈����(�ꕔ����)" �܂��� "��菈����(�S������)" �̏ꍇ]
            if (WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(l_strExecType)
                || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(l_strExecType))
            {
                l_lisQueryContainer.add(WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC);
            }
            // [��L�ȊO�̏ꍇ]
            else
            {
                l_lisQueryContainer.add(WEB3TemporaryExecutionFlagDef.DEFAULT);
            }            
        }
        
        //�T�j�@@�����������쐬 
        //�ȉ��̔�����������ArrayList�ɒǉ�����B 
        //[�p�����[�^.������ == null�̏ꍇ] 
        //�@@�E�Ɩ����t(*1)��YYYYMMDD 
        //(*1)GtlUtils.getTradingSystem().getBizDate() 

        if (l_datOrderBizDate == null)
        {
            log.debug("�p�����[�^.������ == null�̏ꍇ");
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(
                GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"));
        }
        //[�p�����[�^.������ != null�̏ꍇ] 
        //�@@�E�p�����[�^.��������YYYYMMDD 
        else
        {
            log.debug("�p�����[�^.������ != null�̏ꍇ");
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(
                l_datOrderBizDate, "yyyyMMdd"));
        }
        
       //�U�j�@@ArrayList.toArray()�̖߂�l��ԋp����B 
        String[] l_strQueryContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * (createSortCond)<BR>
     * �\�[�g�������쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�\�[�g�L�[�ꗗ == null�̏ꍇ�A<BR>
     * �@@"����ID ����"�̃\�[�g������ԋp����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�\�[�g�L�[�ꗗ�̗v�f�����A<BR>
     * �Ή�����e�[�u���̗񕨗����A<BR>
     * �@@�@@�����^�~���w���t�����Z�b�g<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�<BR>
     * �@@�@@�@@���e�[�u�����F�O�����������P�ʃe�[�u��(feq_order_unit)<BR>
     * �@@�@@�@@���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q��<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q��<BR>
     * <BR>
     * �@@�@@�@@�@@�ϊ��O�@@�@@�@@�@@�@@�@@�@@�@@�@@�ϊ���<BR>
     *        -------------�@@�@@�@@�@@-----------------------------<BR>
     * �@@�@@�@@�@@�E�����R�[�h  �@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D����ID<BR>
     * �@@�@@�@@�@@�E��������敪�@@�@@�@@ �F�O�����������P�ʃe�[�u���D�ŋ敪<BR>
     *         �E�s��R�[�h  �@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D�s��ID<BR>
     * �@@�@@�@@�@@�E�����敪 �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D�������<BR>
     * �@@�@@�@@�@@�E���ϋ敪�@@�@@�@@�@@�@@�@@ �F�O�����������P�ʃe�[�u���D���ϋ敪<BR>
     * �@@�@@�@@�@@�E���s���� �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D���s����<BR>
     * �@@�@@�@@�@@�E�������� �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D��������<BR>
     * �@@�@@�@@�@@�E�������� �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D�쐬���t<BR>
     * �@@�@@�@@�@@�E������    �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D������<BR>
     * �@@�@@�@@�@@�E�����L������ �@@�@@�@@�F�O�����������P�ʃe�[�u���D�����������t<BR>
     * <BR>
     * �@@�@@�E�����^�~���w��́A�O�������\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ�<BR>
     * <BR>
     * �R�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt������B<BR>
     * <BR>
     * �S�j�@@�쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[�ꗗ)
     * @@return String
     * @@roseuid 42A3B777030E
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = "createSortCond(" +
            "WEB3ForeignSortKey[] l_sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_strSortCond = new StringBuffer();
        
        //�P�j�@@�p�����[�^.�\�[�g�L�[�ꗗ == null�̏ꍇ�A 
        //"����ID ����"�̃\�[�g������ԋp����B
        if (l_sortKeys == null)
        {
            l_strSortCond.append(" product_id");
            
            log.exiting(STR_METHOD_NAME);
            return l_strSortCond.toString();
        }

        //�Q�j�@@�p�����[�^.�\�[�g�L�[�ꗗ�̗v�f�����A�Ή�����e�[�u���̗񕨗����A 
        //�����^�~���w���t�����Z�b�g 

        //�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ� 
        //�@@���e�[�u�����F�O�����������P�ʃe�[�u��(feq_order_unit) 
        //�@@���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q�� 
        //�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q��
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //�E�����R�[�h  �@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D����ID 
            if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" product_id");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" product_id desc");
                }
            }
            //�E��������敪�@@�@@�@@ �F�O�����������P�ʃe�[�u���D�ŋ敪 
            else if (WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" tax_type");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" tax_type desc");
                }
            }
            //�E�s��R�[�h  �@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D�s��ID 
            else if (WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" market_id");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" market_id desc");
                }
            }
            //�E�����敪 �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D�������
            else if (WEB3FeqSortKeyItemNameDef.BUY_SELL_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" order_type");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" order_type desc");
                }
            }
            //�E���ϋ敪�@@�@@�@@�@@�@@�@@ �F�O�����������P�ʃe�[�u���D���ϋ敪
            else if (WEB3FeqSortKeyItemNameDef.SETTLE_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" settle_div");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" settle_div desc");
                }
            }
            //�E���s���� �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D���s����
            else if (WEB3FeqSortKeyItemNameDef.EXEC_COND_TYPE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" execution_condition_type");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" execution_condition_type desc");
                }
            }
            //�E�������� �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D��������
            else if (WEB3FeqSortKeyItemNameDef.ORDER_COND_TYPE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" order_condition_type");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" order_condition_type desc");
                }
            }
            //�E�������� �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D�쐬���t
            else if (WEB3FeqSortKeyItemNameDef.ORDER_TIME.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" created_timestamp");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" created_timestamp desc");
                }
            }
            //�E������    �@@�@@�@@�@@�@@�@@�F�O�����������P�ʃe�[�u���D������
            else if (WEB3FeqSortKeyItemNameDef.BIZ_DATE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" biz_date");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" biz_date desc");
                }
            }
            //�E�����L������ �@@�@@�@@�F�O�����������P�ʃe�[�u���D�����������t
            else if (WEB3FeqSortKeyItemNameDef.EXPIRATION_DATE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" expiration_date");
                }
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" expiration_date desc");
                }
            }
            
            l_strSortCond.append(",");
            
        }
        //�R�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt������B 
        l_strSortCond.append(" last_updated_timestamp");
        
        //�S�j�@@�쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond.toString();
    }
    
    /**
     * (remove��������s�����P��)<BR>
     * �����̒����P�ʈꗗ�����������s�Ȓ����P�ʂ�<BR>
     * ��������B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P�ʈꗗ == null�̏ꍇ�A<BR>
     * �@@null��ԋp���ďI������B<BR>
     * <BR>
     * �Q�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�����P�ʈꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�@@������ԊǗ�.reset�s��R�[�h()���R�[������B<BR>
     * <BR>
     * �@@�@@[reset�s��R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����Ώۂ̒����P��.get�s��().�s��R�[�h<BR>
     * <BR>
     * �@@�R�|�Q�j�@@this.is��������\�����P��()���R�[������B<BR>
     * �@@�@@false���ԋp���ꂽ�ꍇ�A���̗v�f�֏������ڍs����B(continue)<BR>
     * <BR>
     * �@@�@@[is��������\�����P��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�����Ώۂ̒����P��<BR>
     * <BR>
     * �@@�R�|�R�j�@@this.is�����\()�Ais����\()���R�[������B<BR>
     * �@@�@@�����Ƃ�false��ԋp�����ꍇ�A���̗v�f�֏������ڍs����B(continue)<BR>
     * <BR>
     * �@@�@@[is�����\()�Ais����\()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���������P�ʁF�@@�����Ώۂ̒����P��<BR>
     * <BR>
     * �@@�R�|�S�j�@@ArrayList�ɏ����Ώۂ̒����P�ʂ�ǉ�����B<BR>
     * <BR>
     * �S�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * �@@�@@�@@��ArrayList�̃T�C�Y == 0�̏ꍇ�Anull��ԋp����B<BR>
     * @@param l_orderUnits - (�����P�ʈꗗ)<BR>
     * �O�����������P�ʃI�u�W�F�N�g�̔z��
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A3DED80089
     */
    protected WEB3FeqOrderUnit[] removeChangeCancelNotOrderUnit(
        WEB3FeqOrderUnit[] l_orderUnits) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "removeChangeCancelNotOrderUnit(" +
            "WEB3FeqOrderUnit[] l_orderUnits)";
       
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�p�����[�^.�����P�ʈꗗ == null�̏ꍇ�A 
        //null��ԋp���ďI������B 
        if (l_orderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�Q�j�@@ArrayList�𐶐�����B 
        List l_lisFeqOrderUnits = new ArrayList();
        
        //�R�j�@@�p�����[�^.�����P�ʈꗗ�̗v�f�����A 
        //�@@�ȉ��̏������J��Ԃ��B 
        //�@@�R�|�P�j�@@������ԊǗ�.reset�s��R�[�h()���R�[������B 
        //�@@[reset�s��R�[�h()�Ɏw�肷�����] 
        //�@@�@@�s��R�[�h�F�@@�����Ώۂ̒����P��.get�s��().�s��R�[�h 
        for (int i = 0; i < l_orderUnits.length; i++)
        {
            String l_strMarketCode = l_orderUnits[i].getMarket().getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            //�R�|�Q�j�@@this.is��������\�����P��()���R�[������B 
            //false���ԋp���ꂽ�ꍇ�A���̗v�f�֏������ڍs����B(continue) 
            //[is��������\�����P��()�Ɏw�肷�����] 
            //�@@�����P�ʁF�@@�����Ώۂ̒����P�� 
            boolean l_blnIsChangeCancel = 
                this.isChangeCancelOrderUnit(l_orderUnits[i]);
            if (!l_blnIsChangeCancel)
            {
                log.debug("this.is��������\�����P��(),false���ԋp���ꂽ�ꍇ");
                continue;
            }
            
            //�R�|�R�j�@@this.is�����\()�Ais����\()���R�[������B 
            //�����Ƃ�false��ԋp�����ꍇ�A���̗v�f�֏������ڍs����B(continue) 
            //[is�����\()�Ais����\()�Ɏw�肷�����] 
            //�@@���������P�ʁF�@@�����Ώۂ̒����P�� 
            boolean l_blnIsChange = this.isChangePossible(l_orderUnits[i]);
            boolean l_blnIsCancel = this.isCancelPossible(l_orderUnits[i]);
            log.debug("is�����\() = " + l_blnIsChange);
            log.debug("is����\() = " + l_blnIsCancel);
            
            if (!l_blnIsChange && !l_blnIsCancel)
            {
                log.debug("this.is�����\()�Ais����\(),�����Ƃ�false��ԋp�����ꍇ");
                continue;
            }
            
            //�R�|�S�j�@@ArrayList�ɏ����Ώۂ̒����P�ʂ�ǉ�����B
            l_lisFeqOrderUnits.add(l_orderUnits[i]);
        }
        //�S�j�@@ArrayList.toArray()�̖߂�l��ԋp����B 
        //�@@��ArrayList�̃T�C�Y == 0�̏ꍇ�Anull��ԋp����B
        WEB3FeqOrderUnit[] l_feqOrderUnits = 
            new WEB3FeqOrderUnit[l_lisFeqOrderUnits.size()];

        l_lisFeqOrderUnits.toArray(l_feqOrderUnits);
                
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnits;
    }
    
    /**
     * (is��������\�����P��)<BR>
     * �����̒����P�ʂ���������\�Ȓ����P�ʂł��邩���ʂ���B<BR>
     * (����������ʃ`�F�b�N)<BR>
     * <BR>
     * ��������\�ȏꍇ��true���A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jis��������\�����P�ʁv�Q��<BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 42A3E99102BB
     */
    protected boolean isChangeCancelOrderUnit(WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeCancelOrderUnit(" +
            "WEB3FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        //1.1 validate�������(�O����������, �s��)
        //��������̔����K���`�F�b�N���s���B 
        //[����] 
        //�O�����������F�@@�p�����[�^.�����P��.getProduct() 
        //�s��F�@@�p�����[�^.�����P��.get�s��()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();        
        
        try
        {            
            l_feqOrderManager.validateTradedProduct(
                (WEB3FeqProduct)l_feqOrderUnit.getProduct(), 
                (WEB3GentradeMarket)l_feqOrderUnit.getMarket());
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("��������`�F�b�N�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //1.2 validate�戵�\�s��(String, String, String)
        //�戵�\�Ȏs�ꂩ�ǂ������ʂ���B 
        //[����] 
        //�،���ЃR�[�h�F�@@�p�����[�^.�����P��.get�،���ЃR�[�h 
        //���X�R�[�h�F�@@�p�����[�^.�����P��.get���X�R�[�h 
        //�s��R�[�h�F�@@�p�����[�^.�����P��.get�s��().�s��R�[�h
        try
        {
            l_feqOrderManager.validateHandlingPossibleMarket(
                l_feqOrderUnit.getInstitutionCode(),
                l_feqOrderUnit.getBranchCode(), 
                l_feqOrderUnit.getMarket().getMarketCode());
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("�s��`�F�b�N�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //1.3 getOrderValidator( )
        //�����`�F�b�N�I�u�W�F�N�g���擾����B        
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.4 validate����\�ڋq(�ڋq)(�����`�F�b�N::validate����\�ڋq)
        //����\�ڋq���ǂ����`�F�b�N����B 
        //[����] 
        //�⏕�����F�@@�p�����[�^.�����P��.get�⏕����()
        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(
                l_feqOrderUnit.getSubAccount());
            
        log.debug("�����`�F�b�N.validate����\�ڋq(): isFailedResult = " + 
                l_validationResult.getProcessingResult().isFailedResult());
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        boolean l_blnIsTradeOpenTimeZone = 
            WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        
        //1.5 (*)�������(is�s��J�ǎ��ԑ�() == false)�@@���@@
        //      �z�X�g��t�ϓ�������(�s�ꂩ��m�F�ς݂̐��� != NaN)�̏ꍇ
        if (!l_blnIsTradeOpenTimeZone && !Double.isNaN(
            l_feqOrderUnit.getConfirmedQuantity()))
        {
            log.debug("is�s��J�ǎ��ԑ�() == false ���� �s�ꂩ��m�F�ς݂̐��� != NaN �̏ꍇ");
            
            //1.5.1 validate�ǌ���������t�\(ProductTypeEnum)
            //�ǌ�ɒ��������������t�\�ł��邩�`�F�b�N����B 
            //[����] 
            //�����^�C�v�F�@@ProductTypeEnum.�O������
            try
            {
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.FOREIGN_EQUITY);
            }
            catch(WEB3BaseException l_ex)
            {
                log.debug("����\�ڋq�`�F�G���[", l_ex);
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        //��Lvalidate���\�b�h�̂����ꂩ����O���X���[�A�܂��̓`�F�b�NNG
        //�������ꍇ�́Afalse��ԋp����B
        //�S�ă`�F�b�NOK�̏ꍇ�́Atrue��ԋp����B
        
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (is�����\)<BR>
     * �����̒����P�ʂ������\�Ȓ��������ʂ���B<BR>
     * <BR>
     * �P�j�@@������t�g�����U�N�V�����̍ăZ�b�g<BR>
     * �@@������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B<BR>
     * <BR>
     * �@@[reset������t�g�����U�N�V����()�Ɏw�肷�����]<BR>
     * �@@�@@������t�g�����U�N�V�����F�@@"����"<BR>
     * <BR>
     * �Q�j�@@�����\�`�F�b�N<BR>
     * �@@�ȉ��̃��\�b�h���R�[�����A��ł���O���X���[���ꂽ�ꍇ��false��<BR>
     * �@@�ȊO�Atrue��ԋp����B<BR>
     * �@@���߂�l��ԋp����O�ɁAreset������t�g�����U�N�V����()�ɂ�<BR>
     * �@@�@@������t�g�����U�N�V������"�Ɖ�"�ɍăZ�b�g����B<BR>
     * �@@�@@�E������ԊǗ�.validate������t�\()<BR>
     * �@@�@@�E�O�����������}�l�[�W��.validate���������\���()<BR>
     * �@@�@@�E�O�����������}�l�[�W��.validate�������()<BR>
     * �@@�@@�E�O�����������}�l�[�W��.validate�ڋq�����ʎ����~()<BR>
     * <BR>
     * �@@�@@�@@[validate���������\���()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@����ID�F�@@�p�����[�^.�����P��.����ID<BR>
     * <BR>
     * �@@�@@�@@[validate�O������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�O�����������F�@@�p�����[�^.�����P��.getProduct()<BR>
     * �@@�@@�@@�@@�s��F�@@�p�����[�^.�����P��.get�s��()<BR>
     * �@@�@@�@@�@@is�������F�@@�p�����[�^.�����P��.is���t()<BR>
     * <BR>
     * �@@�@@�@@[validate�ڋq�����ʎ����~()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�⏕�����F�@@�p�����[�^.�����P��.�⏕����ID�ɊY������⏕����<BR>
     * �@@�@@�@@�@@����ID�F�@@�p�����[�^.�����P��.����ID<BR>
     * �@@�@@�@@�@@������ʁF�@@�p�����[�^.�����P��.�������<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 42A3E9A5027D
     */
    protected boolean isChangePossible(WEB3FeqOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangePossible(" +
                "WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@������t�g�����U�N�V�����̍ăZ�b�g 
        //������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B 
        //[reset������t�g�����U�N�V����()�Ɏw�肷�����] 
        //�@@������t�g�����U�N�V�����F�@@"����" 
        WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CHANGE);
        
        //�Q�j�@@�����\�`�F�b�N 
        //�ȉ��̃��\�b�h���R�[�����A��ł���O���X���[���ꂽ�ꍇ��false�� 
        //�ȊO�Atrue��ԋp����B 
        //���߂�l��ԋp����O�ɁAreset������t�g�����U�N�V����()�ɂ� 
        //�@@������t�g�����U�N�V������"�Ɖ�"�ɍăZ�b�g����B 
        //�@@�E������ԊǗ�.validate������t�\() 
        //�@@�E�O�����������}�l�[�W��.validate���������\���() 
        //�@@�E�O�����������}�l�[�W��.validate�������() 
        //�@@�E�O�����������}�l�[�W��.validate�ڋq�����ʎ����~() 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        try
        {
            //������ԊǗ�.validate������t�\()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //[validate���������\���()�Ɏw�肷�����] 
            //����ID�F�@@�p�����[�^.�����P��.����ID 
            l_feqOrderManager.validateOrderChangePossibleStatus(
                l_orderUnit.getOrderId());
            
            //[validate�������()�Ɏw�肷�����] 
            //�O�����������F�@@�p�����[�^.�����P��.getProduct() 
            //�s��F�@@�p�����[�^.�����P��.get�s��() 
            //is�������F�@@�p�����[�^.�����P��.is���t() 
            l_feqOrderManager.validateTradedProduct(
                (WEB3FeqProduct)l_orderUnit.getProduct(), 
                l_orderUnit.getMarket(), 
                l_orderUnit.isBuy());
            
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            long l_lngSubAccountId = 
                l_orderUnit.getSubAccount().getSubAccountId();
            
            long l_lngAccountId = l_orderUnit.getAccountId();
            
            SubAccount l_subAccount = l_accountManager.getSubAccount(
                l_lngAccountId, l_lngSubAccountId);
            
            long l_lngProductId = l_orderUnit.getProduct().getProductId();
            log.debug("test1@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            //[validate�ڋq�����ʎ����~()�Ɏw�肷�����] 
            //�@@�⏕�����F�@@�p�����[�^.�����P��.�⏕����ID�ɊY������⏕���� 
            //�@@����ID�F�@@�p�����[�^.�����P��.����ID 
            //�@@������ʁF�@@�p�����[�^.�����P��.������� 
            l_feqOrderManager.validateAccountProductTradedStop(
                l_subAccount, 
                l_lngProductId, 
                l_orderUnit.getOrderType());
            
            log.debug("test2@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);
            
            log.exiting(STR_METHOD_NAME);
            return false;            
        }
    }
    
    /**
     * (is����\)<BR>
     * �����̒����P�ʂ�����\�Ȓ��������ʂ���B<BR>
     * <BR>
     * �P�j�@@������t�g�����U�N�V�����̍ăZ�b�g<BR>
     * �@@������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B<BR>
     * <BR>
     * �@@[reset������t�g�����U�N�V����()�Ɏw�肷�����]<BR>
     * �@@�@@������t�g�����U�N�V�����F�@@"���"<BR>
     * <BR>
     * �Q�j�@@����\�`�F�b�N<BR>
     * �@@�ȉ��̃��\�b�h���R�[�����A��ł���O���X���[���ꂽ�ꍇ��false��<BR>
     * �@@�ȊO�Atrue��ԋp����B<BR>
     * �@@���߂�l��ԋp����O�ɁAreset������t�g�����U�N�V����()�ɂ�<BR>
     * �@@�@@������t�g�����U�N�V������"�Ɖ�"�ɍăZ�b�g����B<BR>
     * �@@�@@�E������ԊǗ�.validate������t�\()<BR>
     * �@@�@@�E�O�����������}�l�[�W��.validate��������\���()<BR>
     * <BR>
     * �@@�@@�@@[validate��������\���()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@����ID�F�@@�p�����[�^.�����P��.����ID<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g
     * @@return Boolean
     * @@roseuid 42A7A4BD00DF
     */
    protected boolean isCancelPossible(WEB3FeqOrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "isCancelPossible(" +
            "WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@������t�g�����U�N�V�����̍ăZ�b�g 
        //������ԊǗ�.reset������t�g�����U�N�V����()���R�[������B 
        //[reset������t�g�����U�N�V����()�Ɏw�肷�����] 
        //�@@������t�g�����U�N�V�����F�@@"���" 
        WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.CANCEL);

        //�Q�j�@@����\�`�F�b�N 
        //�ȉ��̃��\�b�h���R�[�����A��ł���O���X���[���ꂽ�ꍇ��false�� 
        //�ȊO�Atrue��ԋp����B 
        //���߂�l��ԋp����O�ɁAreset������t�g�����U�N�V����()�ɂ� 
        //�@@�@@������t�g�����U�N�V������"�Ɖ�"�ɍăZ�b�g����B 
        //�@@�E������ԊǗ�.validate������t�\() 
        //�@@�E�O�����������}�l�[�W��.validate��������\���()       

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //[validate��������\���()�Ɏw�肷�����] 
            //����ID�F�@@�p�����[�^.�����P��.����ID 
            l_feqOrderManager.validateOrderCancelPossibleStatus(
                l_orderUnit.getOrderId());            

            log.exiting(STR_METHOD_NAME);
            return true;
        }        
        catch(WEB3BaseException l_ex)
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);
            
            log.exiting(STR_METHOD_NAME);
            return false;            
        }        
    }
    
    /**
     * (create�������ꗗ)<BR>
     * �����̒����P�ʂ��O�����������̈ꗗ�� <BR>
     * �쐬���A�ԋp����B <BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�����P��.getExecutions()���R�[������B <BR>
     * <BR>
     * �R�j�@@�Q�j�̖߂�l�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �@@�R�|�P�j�@@�O�����������C���X�^���X�𐶐�����B <BR>
     * �@@�R�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�������@@���@@�����Ώۂ̖��.������ <BR>
     * �@@�@@��萔�ʁ@@���@@�����Ώۂ̖��.��萔�� <BR>
     * �@@�@@���P���@@���@@�����Ώۂ̖��.���P�� <BR>
     * �@@�R�|�R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ArrayList�ɒǉ�����B<BR> 
     * <BR>
     * �S�j�@@ArrayList.toArray()�̖߂�l��ԋp����B <BR>
     * �@@�@@�@@��ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g
     * @@return WEB3FeqExecuteUnit[]
     * @@roseuid 42A53E7202FC
     */
    protected WEB3FeqExecuteUnit[] createExecuteUnits(
        WEB3FeqOrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "createExecuteUnits(" +
            "WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@ArrayList�𐶐�����B 
        List l_lisFeqExecuteUnit = new ArrayList();
        
        //�Q�j�@@�p�����[�^.�����P��.getExecutions()���R�[������B
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
        
        //�R�j�@@�Q�j�̖߂�l�̗v�f�����ȉ��̏������J��Ԃ��B 
        for (int i = 0; i < l_orderExecutions.length; i++)
        {
            //�R�|�P�j�@@�O�����������C���X�^���X�𐶐�����B 
            WEB3FeqExecuteUnit l_feqExecuteUnit = new WEB3FeqExecuteUnit();
            
            //�R�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B 
            //�@@�������@@���@@�����Ώۂ̖��.������ 
            l_feqExecuteUnit.executionTimestamp = 
                l_orderExecutions[i].getExecutionTimestamp();
            
            log.debug("������ = " + l_feqExecuteUnit.executionTimestamp);
            
            //�@@��萔�ʁ@@���@@�����Ώۂ̖��.��萔�� 
            l_feqExecuteUnit.execQuantity = WEB3StringTypeUtility.formatNumber(
                l_orderExecutions[i].getExecutionQuantity());
            
            log.debug("��萔�� = " + l_feqExecuteUnit.execQuantity);
            
            //�@@���P���@@���@@�����Ώۂ̖��.���P�� 
            l_feqExecuteUnit.execPrice = WEB3StringTypeUtility.formatNumber(
                l_orderExecutions[i].getExecutionPrice());            

            log.debug("���P�� = " + l_feqExecuteUnit.execPrice);
            
            //�R�|�R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ArrayList�ɒǉ�����B
            l_lisFeqExecuteUnit.add(l_feqExecuteUnit);
        }
        
        //�S�j�@@ArrayList.toArray()�̖߂�l��ԋp����B 
        //�@@��ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B
        WEB3FeqExecuteUnit[] l_feqExecuteUnits = 
            new WEB3FeqExecuteUnit[l_lisFeqExecuteUnit.size()];
        
        l_lisFeqExecuteUnit.toArray(l_feqExecuteUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqExecuteUnits;
    }
    
    /**
     * (create�����ڍ׏��)<BR>
     * �����̒����P�ʂ��O�����������ڍ׏����쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jcreate�����ڍ׏��v�Q��<BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g
     * @@return WEB3FeqOrderDetailInfoUnit
     * @@throws WEB3BaseException
     * @@roseuid 42A4402D0071
     */
    protected WEB3FeqOrderDetailInfoUnit createOrderDetailsInformation(
        WEB3FeqOrderUnit l_feqOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderDetailsInformation(" +
            "WEB3FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }    
        
        //1.1 �O�����������ڍ׏��C���X�^���X�𐶐�����B
        WEB3FeqOrderDetailInfoUnit l_feqOrderDetailInfoUnit = 
            new WEB3FeqOrderDetailInfoUnit();
        
        //1.2 create�O�������������ʖ���(�O�������������ʖ���, �O�����������P��)
        //�X�[�p�[�N���X�̃v���p�e�B�Z�b�g���s���B 
        //[����] 
        //�O�������������ʖ��ׁF�@@���������C���X�^���X 
        //�����P�ʁF�@@�p�����[�^.�����P��
        WEB3FeqCommonMessageCreatedService l_commonMessageCreatedService = 
            new WEB3FeqCommonMessageCreatedServiceImpl();

        l_commonMessageCreatedService.createFeqOrderCommonUnit(
            l_feqOrderDetailInfoUnit, 
            l_feqOrderUnit);
        
        //(*)���������C���X�^���X�Ƀv���p�e�B���Z�b�g����B

        //�����L������ �� �O�����������}�l�[�W��.is�o����܂Œ���(�p�����[�^.�����P��) == false
        //�@@�@@�@@�@@�@@�@@�@@�@@�̏ꍇ�̂�null���Z�b�g�B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_blnIsCarriedOrderUnit = 
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);
        
        if (!l_blnIsCarriedOrderUnit)
        {
            l_feqOrderDetailInfoUnit.expirationDate = null;
        }
        
        //�����o�H�敪      ���@@�p�����[�^.�����P��.�����o�H�敪
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        l_feqOrderDetailInfoUnit.orderRootDiv = 
            l_feqOrderUnitParams.getOrderRootDiv();
        
        //������ԋ敪      ���@@�p�����[�^.�����P��.get������ԋ敪()
        String l_strOrderStatusDiv = 
            l_feqOrderUnit.getOrderStatusDiv();
        
        l_feqOrderDetailInfoUnit.orderState = 
            l_feqOrderUnit.getOrderStatusDiv();
        
        //��������敪      ���@@�p�����[�^.�����P��.���������E����敪
        l_feqOrderDetailInfoUnit.changeCancelDiv = 
            l_feqOrderUnitParams.getModifyCancelType();
        
        //�J�z�G���[�R�[�h        ���@@get������ԋ敪()�̖߂�l == "�J�z���s"�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@  �p�����[�^.�����P��.�����G���[���R�R�[�h���Z�b�g�B
        if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_strOrderStatusDiv))
        {
            l_feqOrderDetailInfoUnit.transferErrCode = 
                l_feqOrderUnitParams.getErrorReasonCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderDetailInfoUnit;
    }
    
    /**
     * (create���ڍ׏��)<BR>
     * �����̒����P�ʂ��O���������ڍ׏����쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jcreate���ڍ׏��v�Q��<BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g
     * @@return WEB3FeqExecuteDetailInfoUnit
     * @@WEB3BaseException
     * @@roseuid 42A44089036F
     */
    protected WEB3FeqExecuteDetailInfoUnit createExecuteDetailsInformation(
        WEB3FeqOrderUnit l_feqOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createExecuteDetailsInformation(" +
        "WEB3FeqOrderUnit l_feqOrderUnit)";
    log.entering(STR_METHOD_NAME);
    
    if (l_feqOrderUnit == null)
    {
        log.debug("�p�����[�^Null�o���Ȃ��B");
        throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
            this.getClass().getName() + "." + STR_METHOD_NAME);
    }    
    
    //1.1 �O�����������ڍ׏��C���X�^���X�𐶐�����B
    WEB3FeqExecuteDetailInfoUnit l_feqExecuteDetailInfoUnit = 
        new WEB3FeqExecuteDetailInfoUnit();
    
    //1.2 get����ԋ敪( )(�O�����������P��::get����ԋ敪)
    //����ԋ敪���擾����B
    String l_strExecStatusDiv = l_feqOrderUnit.getExecStatusDiv();

    //1.3 (*)�����(get����ԋ敪()�̖߂�l == "�����")�̏ꍇ  
    if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(
        l_strExecStatusDiv))
    {
        log.debug("�����(get����ԋ敪()�̖߂�l == '�����')�̏ꍇ");
        //1.3.1 (*)����ԋ敪��get����ԋ敪()�̖߂�l���Z�b�g�B
        l_feqExecuteDetailInfoUnit.execType = l_strExecStatusDiv;
        
        //1.3.2 
        log.exiting(STR_METHOD_NAME);
        return l_feqExecuteDetailInfoUnit;
    }
    //1.4 get�g�����U�N�V����(�O�����������P��)
    //(�O�������g�����U�N�V�����}�l�[�W��::get�g�����U�N�V����)
    //�����P�ʂɕR�Â��g�����U�N�V�������擾����B 
    //[����] 
    //�����P�ʁF�@@�p�����[�^.�����P��
    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    TradingModule l_tradingModule = l_finApp.getTradingModule(
        ProductTypeEnum.FOREIGN_EQUITY);
    
    WEB3FeqFinTransactionManager l_finTransactionManager = 
        (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
    
    List l_lisTransaction = 
        l_finTransactionManager.getTransactions(l_feqOrderUnit);
    
    double l_dblDeliveryPerice = 0.0D; 
    BigDecimal l_bdNetAmountFc = new BigDecimal("0"); 
    BigDecimal l_bdForeignCommissionFee = new BigDecimal("0"); 
    BigDecimal l_bdForeignTax = new BigDecimal("0"); 
    BigDecimal l_bdForeignFeeExt1 = new BigDecimal("0"); 
    BigDecimal l_bdForeignFeeExt2 = new BigDecimal("0"); 
    double l_dblBalanceAmount = 0.0D; 
    BigDecimal l_bdBalanceAmountFc = new BigDecimal("0"); 
    double l_dblCommissionFee = 0.0D; 
    double l_dblCommissionFeeTax = 0.0D; 
    BigDecimal l_bdCommissionFeeFc = new BigDecimal("0"); 
    BigDecimal l_bdCommissionFeeTaxFc = new BigDecimal("0"); 
    
    log.debug("get�g�����U�N�V����()�̖߂�l�̗v�f�� = " + l_lisTransaction.size());
    
    //1.5.1 (*)get�g�����U�N�V����()�̖߂�l�̗v�f����Loop����
    for (int i = 0; i < l_lisTransaction.size(); i++)
    {
        //(*)�����Ώۃg�����U�N�V�����̈ȉ��̒l���W�v����B
        FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow) 
            l_lisTransaction.get(i);     
        log.debug("�g�����U�N�V����Row = " + l_transactionRow);
        
        //�W�v������n���            += �g�����U�N�V����.getNetAmount()
        l_dblDeliveryPerice += l_transactionRow.getNetAmount();
        
        //�W�v������n����i�O�݁j        += �g�����U�N�V����.getNetAmountFc()
        l_bdNetAmountFc =
            l_bdNetAmountFc.add(new BigDecimal(String.valueOf(l_transactionRow.getNetAmountFc())));
        
        //�W�v�������n�萔��         += �g�����U�N�V����.getForeignCommissionFee()
        l_bdForeignCommissionFee =
            l_bdForeignCommissionFee.add(new BigDecimal(String.valueOf(l_transactionRow.getForeignCommissionFee())));
        
        l_feqExecuteDetailInfoUnit.localCommission = 
            WEB3StringTypeUtility.formatNumber(l_bdForeignCommissionFee.doubleValue());
        
        //�W�v�������n�����           += �g�����U�N�V����.getForeignTax()
        l_bdForeignTax =
            l_bdForeignTax.add(new BigDecimal(String.valueOf(l_transactionRow.getForeignTax())));
        
        l_feqExecuteDetailInfoUnit.localTradingTax = 
            WEB3StringTypeUtility.formatNumber(l_bdForeignTax.doubleValue());
        
        //�W�v�������̑��R�X�g�P     += �g�����U�N�V����.getForeignFeeExt1()
        l_bdForeignFeeExt1 =
            l_bdForeignFeeExt1.add(new BigDecimal(String.valueOf(l_transactionRow.getForeignFeeExt1())));
        
        l_feqExecuteDetailInfoUnit.otherCost1 = 
            WEB3StringTypeUtility.formatNumber(l_bdForeignFeeExt1.doubleValue());
        
        //�W�v�������̑��R�X�g�Q     += �g�����U�N�V����.getForeignFeeExt2()
        l_bdForeignFeeExt2 =
            l_bdForeignFeeExt2.add(new BigDecimal(String.valueOf(l_transactionRow.getForeignFeeExt2())));
        
        l_feqExecuteDetailInfoUnit.otherCost2 = 
            WEB3StringTypeUtility.formatNumber(l_bdForeignFeeExt2.doubleValue());
        
        //�W�v�������Z���            += �g�����U�N�V����.getBalanceAmount()
        l_dblBalanceAmount += l_transactionRow.getBalanceAmount();
        
        l_feqExecuteDetailInfoUnit.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblBalanceAmount);
        
        //�W�v�������Z����i�O�݁j        += �g�����U�N�V����.getBalanceAmountFc()
        l_bdBalanceAmountFc =
            l_bdBalanceAmountFc.add(new BigDecimal(String.valueOf(l_transactionRow.getBalanceAmountFc())));
        
        l_feqExecuteDetailInfoUnit.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdBalanceAmountFc.doubleValue());
        
        //�W�v���������萔��           += �g�����U�N�V����.getCommissionFee()
        l_dblCommissionFee += l_transactionRow.getCommissionFee();
        
        l_feqExecuteDetailInfoUnit.commission = 
            WEB3StringTypeUtility.formatNumber(l_dblCommissionFee);
        
        //�W�v���������萔�������        += �g�����U�N�V����.getCommissionFeeTax()
        l_dblCommissionFeeTax += l_transactionRow.getCommissionFeeTax();
        
        l_feqExecuteDetailInfoUnit.commissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_dblCommissionFeeTax);
        
        //�W�v���������萔���i�O�݁j       += �g�����U�N�V����.getCommissionFeeFc()
        l_bdCommissionFeeFc =
            l_bdCommissionFeeFc.add(new BigDecimal(String.valueOf(l_transactionRow.getCommissionFeeFc())));
        
        l_feqExecuteDetailInfoUnit.foreignCommission = 
            WEB3StringTypeUtility.formatNumber(l_bdCommissionFeeFc.doubleValue());
        
        //�W�v���������萔������Łi�O�݁j    += �g�����U�N�V����.getCommissionFeeTaxFc()
        l_bdCommissionFeeTaxFc =
            l_bdCommissionFeeTaxFc.add(new BigDecimal(String.valueOf(l_transactionRow.getCommissionFeeTaxFc())));
        
        l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_bdCommissionFeeTaxFc.doubleValue());
    }
    
    //1.6 ArrayList�𐶐�����B
    List l_lisExecDetailInfoUnit = new ArrayList();
    
    //1.7 getExecutions( )
    //�����P�ʂɕR�Â����̈ꗗ���擾����B
    OrderExecution[] l_orderExecutions = (OrderExecution[])
        l_feqOrderUnit.getExecutions();
        
    //1.8 (*)getExecutions()�̖߂�l�̗v�f����Loop����
    for (int i = 0; i < l_orderExecutions.length; i++)
    {
        WEB3FeqOrderExecution l_feqOrderExecution = 
            (WEB3FeqOrderExecution) l_orderExecutions[i];
        
        //1.8.1 get�g�����U�N�V����(FeqOrderExecution)
        //���ɑΉ�����g�����U�N�V�������擾����B 
        //[����] 
        //���F�@@���
        FeqFinTransactionParams l_transactionParams = 
            l_finTransactionManager.getTransaction(l_feqOrderExecution);
        
        log.debug("�g�����U�N�V����Params = " + l_transactionParams);

        //1.8.2 �O���������ڍׁi�Ǘ��ҁj�C���X�^���X�𐶐�����B
        WEB3FeqExecDetailInfoUnit l_feqExecDetailInfoUnit = 
            new WEB3FeqExecDetailInfoUnit();
        
        //1.8.3 create�O���������ڍׁi�Ǘ��ҁj
        //���������O���������ڍׁi�Ǘ��ҁj�C���X�^���X�Ƀv���p�e�B���Z�b�g����B 
        //[����] 
        //�O���������ڍׁi�Ǘ��ҁj�F�@@���������C���X�^���X 
        //���F�@@��� 
        //�g�����U�N�V�����i������薾�ׁj�s�F�@@get�g�����U�N�V����()�̖߂�l
        WEB3FeqCommonMessageCreatedService l_commonMessageCreatedService = 
            new WEB3FeqCommonMessageCreatedServiceImpl();
        
        l_commonMessageCreatedService.createAdminFeqExecDetailInfoUnit(
            l_feqExecDetailInfoUnit, 
            l_feqOrderExecution, 
            l_transactionParams);
        
        //1.8.4 (*)������ = �����Ώۂ̖��.���������Z�b�g����B
        l_feqExecDetailInfoUnit.executionTimestamp = 
            l_feqOrderExecution.getExecutionTimestamp();
        
        //1.8.5 add(arg0 : Object)
        //ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B 
        //[����] 
        //arg0�F�@@�v���p�e�B�Z�b�g�����O���������ڍׁi�Ǘ��ҁj�C���X�^���X
        l_lisExecDetailInfoUnit.add(l_feqExecDetailInfoUnit);            
    }
    
    //1.9 toArray( )      
    WEB3FeqExecDetailInfoUnit[] l_feqExecDetailInfoUnit = 
        new WEB3FeqExecDetailInfoUnit[l_lisExecDetailInfoUnit.size()];
    
    l_lisExecDetailInfoUnit.toArray(l_feqExecDetailInfoUnit);

    WEB3FeqOrderExecution l_feqOrderExecution = 
        (WEB3FeqOrderExecution) l_orderExecutions[0];
    
    //1.10 (*)���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
    
    //��萔��        ���@@�p�����[�^.�����P��.��萔��
    FeqOrderUnitParams l_feqOrderUnitparams = new FeqOrderUnitParams(
        (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject());        
   
    l_feqExecuteDetailInfoUnit.execQuantity = 
        WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitparams.getExecutedQuantity());

    log.debug("��萔�� = " + l_feqExecuteDetailInfoUnit.execQuantity);

    //���P��  ���@@�p�����[�^.�����P��.���v�����z�i�O�݁j�@@�^�@@�p�����[�^.�����P��.��萔��
    BigDecimal l_bdExecutedAmount =
        new BigDecimal(
            String.valueOf(l_feqOrderUnitparams.getExecutedAmount()));
    BigDecimal l_bdExecutedQuantity =
        new BigDecimal(
            String.valueOf(l_feqOrderUnitparams.getExecutedQuantity()));
    BigDecimal l_bdExecPrice =
        l_bdExecutedAmount.divide(l_bdExecutedQuantity, 6, BigDecimal.ROUND_HALF_EVEN);
    l_feqExecuteDetailInfoUnit.execPrice =
        WEB3StringTypeUtility.formatNumber(l_bdExecPrice.doubleValue());

    log.debug("���P�� = " + l_feqExecuteDetailInfoUnit.execPrice);
    
    //����ԋ敪      ���@@get����ԋ敪()�̖߂�l
    l_feqExecuteDetailInfoUnit.execType = l_strExecStatusDiv;
        
    log.debug("����ԋ敪 = " + l_feqExecuteDetailInfoUnit.execType);
    
    //��薾�׈ꗗ      ���@@toArray()�̖߂�l
    l_feqExecuteDetailInfoUnit.execDetailList = l_feqExecDetailInfoUnit;
            
    log.debug("��薾�׈ꗗ = " + l_feqExecuteDetailInfoUnit.execDetailList);
    
    // �����P��.get����ԋ敪()�̖߂�l��"��菈����(�ꕔ����)"�܂���"��菈����(�S������)"�̏ꍇ�A
    if (!(WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(l_feqOrderUnit.getExecStatusDiv())
        || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(l_feqOrderUnit.getExecStatusDiv())))
    {       
        //��n��         ���@@getExecutions()�̖߂�l[0].��n��
        l_feqExecuteDetailInfoUnit.deliveryDate = 
            l_feqOrderExecution.getDeliveryDate();
        
        log.debug("��n�� = " + l_feqExecuteDetailInfoUnit.deliveryDate);
        
        //���n��n��       ���@@getExecutions()�̖߂�l[0].���n��n��
        FeqOrderExecutionRow l_feqOrderExeRow =
                    (FeqOrderExecutionRow)l_feqOrderExecution.getDataSourceObject();
        l_feqExecuteDetailInfoUnit.localDeliveryDate = 
            l_feqOrderExeRow.getFDeliveryDate();
        
        log.debug("���n��n�� = " + l_feqExecuteDetailInfoUnit.localDeliveryDate);
        
        //�����        ���@@�p�����[�^.�����P��.���v�����z
        l_feqExecuteDetailInfoUnit.execAmount = 
            WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitparams.getExecutedAmount());
        
        log.debug("����� = " + l_feqExecuteDetailInfoUnit.execAmount);
        
        //���בփ��[�g   ���@@getExecutions()�̖߂�l[0].�בփ��[�g
        l_feqExecuteDetailInfoUnit.execExchangeRate = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderExecution.getFxRate());
        
        log.debug("���בփ��[�g = " + l_feqExecuteDetailInfoUnit.execExchangeRate);
        
        //���҃R�[�h       ���@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader(
        //                  �p�����[�^.�����P��.�����ID).���҃R�[�h���Z�b�g�B
        //   ��this.get�㗝���͎�() != null(�R�[���Z���^�[����̎Q��)�̏ꍇ�̂݃Z�b�g�B
        
        if (this.getTrader() != null)
        {
            log.debug("this.get�㗝���͎�() != null");
            
            WEB3GentradeFinObjectManager l_finObjManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                if (!l_feqOrderUnitparams.getTraderIdIsNull())
                {
                    String l_strTraderCode = 
                        l_finObjManager.getTrader(
                            l_feqOrderUnitparams.getTraderId()).getTraderCode();
                    
                    l_feqExecuteDetailInfoUnit.traderCode = l_strTraderCode;
                    
                    log.debug("���҃R�[�h = " + l_feqExecuteDetailInfoUnit.traderCode);
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("___NotFoundException___" , l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
        }
        
        //��n���        ���@@�W�v������n���
        if (OrderTypeEnum.FEQ_BUY.equals(
                        l_feqOrderUnit.getOrderType()) == true)
        {
            l_feqExecuteDetailInfoUnit.deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblDeliveryPerice * (-1));
        }else{
            l_feqExecuteDetailInfoUnit.deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblDeliveryPerice);
        }
        
        log.debug("��n��� = " + l_feqExecuteDetailInfoUnit.deliveryPrice);
        
        //��n����i�O�݁j  ���@@�W�v������n����i�O�݁j
        if (OrderTypeEnum.FEQ_BUY.equals(
                        l_feqOrderUnit.getOrderType()) == true)
        {
            l_feqExecuteDetailInfoUnit.foreignDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bdNetAmountFc.multiply(new BigDecimal("-1")).doubleValue());
        }else{
            l_feqExecuteDetailInfoUnit.foreignDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bdNetAmountFc.doubleValue());
        }
        
        log.debug("��n����i�O�݁j = " + l_feqExecuteDetailInfoUnit.foreignDeliveryPrice);
        
        //���n�萔��       ���@@�W�v�������n�萔��
        l_feqExecuteDetailInfoUnit.localCommission = 
            l_feqExecuteDetailInfoUnit.localCommission;
        
        log.debug("���n�萔�� = " + l_feqExecuteDetailInfoUnit.localCommission);
        
        //���n�����       ���@@�W�v�������n�����
        l_feqExecuteDetailInfoUnit.localTradingTax = 
            l_feqExecuteDetailInfoUnit.localTradingTax;
        
        log.debug("���n����� = " + l_feqExecuteDetailInfoUnit.localTradingTax);
        
        //���̑��R�X�g�P     ���@@�W�v�������̑��R�X�g�P
        l_feqExecuteDetailInfoUnit.otherCost1 = 
            l_feqExecuteDetailInfoUnit.otherCost1;
        
        log.debug("���̑��R�X�g�P = " + l_feqExecuteDetailInfoUnit.otherCost1);
        
        //���̑��R�X�g�Q     ���@@�W�v�������̑��R�X�g�Q
        l_feqExecuteDetailInfoUnit.otherCost2 = 
            l_feqExecuteDetailInfoUnit.otherCost2;
        
        log.debug("���̑��R�X�g�Q = " + l_feqExecuteDetailInfoUnit.otherCost2);
        
        //���Z���        ���@@�W�v�������Z���
        l_feqExecuteDetailInfoUnit.clearUpPrice = 
            l_feqExecuteDetailInfoUnit.clearUpPrice;
        
        log.debug("���Z��� = " + l_feqExecuteDetailInfoUnit.clearUpPrice);
        
        //���Z����i�O�݁j   ���@@�W�v�������Z����i�O�݁j
        l_feqExecuteDetailInfoUnit.foreignClearUpPrice = 
            l_feqExecuteDetailInfoUnit.foreignClearUpPrice;
        
        log.debug("���Z����i�O�݁j = " + l_feqExecuteDetailInfoUnit.foreignClearUpPrice);
        
        //�����萔��       ���@@�W�v���������萔��
        l_feqExecuteDetailInfoUnit.commission = 
            l_feqExecuteDetailInfoUnit.commission;
        
        log.debug("�����萔�� = " + l_feqExecuteDetailInfoUnit.commission);
        
        //�����萔�������    ���@@�W�v���������萔�������
        l_feqExecuteDetailInfoUnit.commissionConsumptionTax = 
            l_feqExecuteDetailInfoUnit.commissionConsumptionTax;
        
        log.debug("�����萔������� = " + l_feqExecuteDetailInfoUnit.commissionConsumptionTax);
        
        //�����萔���i�O�݁j   ���@@�W�v���������萔���i�O�݁j
        l_feqExecuteDetailInfoUnit.foreignCommission = 
            l_feqExecuteDetailInfoUnit.foreignCommission;
        
        log.debug("�����萔���i�O�݁j = " + l_feqExecuteDetailInfoUnit.foreignCommission);
        
        //�����萔������Łi�O�݁j    ���@@�W�v���������萔������Łi�O�݁j
        l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax = 
            l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax;
        
        log.debug("�����萔������Łi�O�݁j = " + 
            l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax);
    }
    // ���P���Ɩ�萔�ʂƖ�薾�׈ꗗ�̍��ڈȊO�ɂ�null���Z�b�g����B
    else
    {
        // ��n��
        l_feqExecuteDetailInfoUnit.deliveryDate = null;
        
        // ���n��n��
        l_feqExecuteDetailInfoUnit.localDeliveryDate = null;
        
        // �����
        l_feqExecuteDetailInfoUnit.execAmount = null;
        
        // ���בփ��[�g
        l_feqExecuteDetailInfoUnit.execExchangeRate = null;
        
        // ���҃R�[�h
        l_feqExecuteDetailInfoUnit.traderCode = null;
        
        // ��n���
        l_feqExecuteDetailInfoUnit.deliveryPrice = null;
        
        // ��n����i�O�݁j
        l_feqExecuteDetailInfoUnit.foreignDeliveryPrice = null;
        
        // ���n�萔��
        l_feqExecuteDetailInfoUnit.localCommission = null;
        
        // ���n�����
        l_feqExecuteDetailInfoUnit.localTradingTax = null;
        
        // ���̑��R�X�g�P
        l_feqExecuteDetailInfoUnit.otherCost1 = null;
        
        // ���̑��R�X�g�Q
        l_feqExecuteDetailInfoUnit.otherCost2 = null;
        
        // ���Z���
        l_feqExecuteDetailInfoUnit.clearUpPrice = null;
        
        // ���Z����i�O�݁j
        l_feqExecuteDetailInfoUnit.foreignClearUpPrice = null;
        
        // �����萔��
        l_feqExecuteDetailInfoUnit.commission = null;
        
        // �����萔�������
        l_feqExecuteDetailInfoUnit.commissionConsumptionTax = null;
        
        // �����萔���i�O�݁j
        l_feqExecuteDetailInfoUnit.foreignCommission = null;
        
        // �����萔������Łi�O�݁j
        l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax = null;
    }
    
    log.exiting(STR_METHOD_NAME);
    return l_feqExecuteDetailInfoUnit;
}
    
    /**
     * (create���������ꗗ)<BR>
     * �����̒����P�ʂ��璍�������ꗗ���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������Ɖ�jcreate���������ꗗ�v�Q��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g
     * @@return WEB3FeqChangeCancelHistoryGroup[]
     * @@throws WEB3BaseException
     * @@roseuid 42A547C503C7
     */
    protected WEB3FeqChangeCancelHistoryGroup[] createOrderActionList(
        WEB3FeqOrderUnit l_feqOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createOrderActionList(WEB3FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 get�o����܂Œ����P��FromFirstToLast(�O�����������P��)
        //�������`�ŐV�܂ł̏o����܂Œ�����S�Ď擾����B 
        //[����] 
        //�����P�ʁF�@@�p�����[�^.�����P��
        WEB3FeqOrderUnit[] l_feqOrderUnits = 
            this.getCarriedOrderUnitFromFirstToLast(l_feqOrderUnit);
        
        //1.2 is�o����܂Œ����P��(FeqOrderUnit)
        //�o����܂Œ������ǂ������ʂ���B 
        //[����] 
        //�����P�ʁF�@@�����̒����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_blnIsCarriedOrder = 
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);
        
        //1.3 ArrayList�𐶐�����B
        List l_lisChangeCancelHistoryGroup = new ArrayList();
        
        //1.4  (*)get�o����܂Œ����P��FromFirstToLast()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_feqOrderUnits.length; i++)
        {
            //1.4.1 getOrderActions( )
            //�����P�ʂɕR�Â������������擾����B 
            OrderAction[] l_orderActions = 
			l_feqOrderUnits[i].getOrderActions();
            
            //1.4.2 (*)getOrderActions()�̖߂�l�̗v�f����Loop����
            for (int j = 0; j < l_orderActions.length; j++)
            {
                WEB3FeqOrderAction l_feqOrderAction = 
                    (WEB3FeqOrderAction)l_orderActions[j];
                
                FeqOrderActionParams l_feqOrderActionParams = 
                    new FeqOrderActionParams((FeqOrderActionRow)
                        l_orderActions[j].getDataSourceObject());
                
                log.debug("��������Params = " + l_feqOrderActionParams);
                
                //1.4.2.1 get������ԋ敪(�O�����������P��)
                //������ԋ敪���擾����B 
                //[����] 
                //�����P�ʁF�@@�����Ώۂ̒����P��
                String l_strActionStateDiv = 
                    l_feqOrderAction.getActionStateDiv(l_feqOrderUnits[i]);

                //1.4.2.2 get��t���ʋ敪( )(�O��������������::get��t���ʋ敪)
                //��t���ʋ敪���擾����B
                String l_strAcceptStatesDiv = 
                    l_feqOrderAction.getAcceptStatusDiv();
                
                //1.4.2.3 isUnexecuted( )
                //��莞�ɍ쐬���ꂽ�������ǂ������ʂ���B
                boolean l_blnIsUnexecuted = l_feqOrderAction.isUnexecuted();
                
                //1.4.2.4 get���s�����iSONAR�j(String)
                //���s�����iSONAR�j���擾����B 
                //[����] 
                //���s�����F�@@�����Ώۂ̒�������.���s����
                String l_strExectutinConditionType = 
                    l_feqOrderAction.getExecutionConditionType().intValue() + "";
                
                String l_strExecutionConditionTypeSonar = 
                    l_feqOrderManager.getExecutionConditionTypeSonar(
                        l_strExectutinConditionType);

                //1.4.2.5 �O������������藚�𖾍׃C���X�^���X�𐶐�����B
                WEB3FeqChangeCancelHistoryGroup l_feqChangeCancelHistoryGroup = 
                    new WEB3FeqChangeCancelHistoryGroup();
                
                //1.4.2.6  (*)��莞�ɍ쐬���ꂽ�����łȂ�(isUnexecuted() == true)�ꍇ
                if (l_blnIsUnexecuted)
                {
                    //1.4.2.6.1 (*)�v���p�e�B�Z�b�g
                    //  (*)�y��莞�ɍ쐬���ꂽ�����łȂ��ꍇ�z
                    //���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B

                    //��������ID      ���@@��������.��������ID
                    l_feqChangeCancelHistoryGroup.orderActionId = 
                        l_feqOrderActionParams.getOrderActionId() + "";
                    
                    //��t����        ���@@��������.�쐬���t
                    l_feqChangeCancelHistoryGroup.orderDate = 
                        l_feqOrderActionParams.getCreatedTimestamp();
                        
                    //������ԋ敪      ���@@get������ԋ敪()�̖߂�l
                    l_feqChangeCancelHistoryGroup.orderType = l_strActionStateDiv;
                    
                    //���s����        ���@@get���s�����iSONAR�j�̖߂�l
                    l_feqChangeCancelHistoryGroup.execCondType = 
                        l_strExecutionConditionTypeSonar;
                    
                    //��������        ���@@��������.��������
                    l_feqChangeCancelHistoryGroup.orderCondType = 
                        l_feqOrderActionParams.getOrderConditionType();
                    
                    // (*1)��������.�������� == ("�t�w�l" or "W�w�l")�̏ꍇ�̂݃Z�b�g�B
                    if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                            l_feqOrderActionParams.getOrderConditionType()) || 
                        WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                            l_feqOrderActionParams.getOrderConditionType()))
                    {
                        //���������P��(*1)�@@���@@��������.�t�w�l��l
                        l_feqChangeCancelHistoryGroup.orderCondPrice = 
                            WEB3StringTypeUtility.formatNumber(
                                l_feqOrderActionParams.getStopOrderPrice());
                        
                        //�����������Z�q(*1) ���@@��������.�����������Z�q���Z�b�g�B
                        l_feqChangeCancelHistoryGroup.condOperator = 
                            l_feqOrderActionParams.getOrderCondOperator();
                    }
                    
                    //(*2)��������.�������� == "W�w�l"�̏ꍇ�̂݃Z�b�g�B
                    if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                        l_feqOrderActionParams.getOrderConditionType()))
                    {
                        //W�w�l�p�����P���敪(*2)  ���@@
                        //          ��������.�iW�w�l�j�����w�l == 0�̏ꍇ�A"���s"���Z�b�g�B
                        //�@@�@@�@@�@@�@@�@@�ȊO�A"�w�l"���Z�b�g�B
                        if (l_feqOrderActionParams.getWLimitPrice() == 0)
                        {
                            l_feqChangeCancelHistoryGroup.wLimitOrderPriceDiv = 
                                WEB3OrderPriceDivDef.MARKET_PRICE;
                        }
                        else
                        {
                            l_feqChangeCancelHistoryGroup.wLimitOrderPriceDiv = 
                                WEB3OrderPriceDivDef.LIMIT_PRICE;
                        }                    
                        //W�w�l�p�����P��(*2)  ���@@��������.�iW�w�l�j�����w�l != 0�̏ꍇ�A
                        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��������.�iW�w�l�j�����w�l���Z�b�g�B
                        if (l_feqOrderActionParams.getWLimitPrice() != 0)
                        {
                            l_feqChangeCancelHistoryGroup.wLimitPrice = 
                                WEB3StringTypeUtility.formatNumber(
                                    l_feqOrderActionParams.getWLimitPrice());
                        }
                    }
                    //��������        ���@@��������.��������
                    l_feqChangeCancelHistoryGroup.orderQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getQuantity());
                    
                    //�����P���敪�@@���@@��������.�����P�� == 0�̏ꍇ�A"���s"�B
                    //               �ȊO�A"�w�l"���Z�b�g�B
                    if (l_feqOrderActionParams.getPrice() == 0)
                    {
                        l_feqChangeCancelHistoryGroup.orderPriceDiv = 
                            WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        l_feqChangeCancelHistoryGroup.orderPriceDiv = 
                            WEB3OrderPriceDivDef.LIMIT_PRICE;
                    }
                    
                    //�����P��        ���@@��������.�����P��
                    l_feqChangeCancelHistoryGroup.limitPrice = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getPrice());
                    
                    //�����L������      ���@@is�o����܂Œ����P��()�̖߂�l == true�̏ꍇ�A
                    //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ ��������.�����������t���Z�b�g�B
                    if (l_blnIsCarriedOrder)
                    {
                        l_feqChangeCancelHistoryGroup.expirationDate = 
                            WEB3DateUtility.toDay(
                                l_feqOrderActionParams.getExpirationDate());
                    }
                    
                    //��t���ʋ敪      ���@@get��t���ʋ敪()�̖߂�
                    l_feqChangeCancelHistoryGroup.acceptedResultDiv = 
                        l_strAcceptStatesDiv;
                    
                    //�ʉ݃R�[�h       ���@@�����P��.�ʉ݃R�[�h
                    l_feqChangeCancelHistoryGroup.currencyCode = 
                        l_feqOrderUnits[i].getCurrencyCode();
                }
                //1.4.2.7 (*)��莞�ɍ쐬���ꂽ����(isUnexecuted() == false)�̏ꍇ
                else
                {
                    //1.4.2.7.1 (*)�v���p�e�B�Z�b�g
                    //(*)�y��莞�ɍ쐬���ꂽ�����̏ꍇ�z
                    //���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                    //��������ID      ���@@��������.��������ID
                    l_feqChangeCancelHistoryGroup.orderActionId = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getOrderActionId());
                    
                    //��t����        ���@@��������.�쐬���t
                    l_feqChangeCancelHistoryGroup.orderDate = 
                        l_feqOrderActionParams.getCreatedTimestamp();
                    
                    //������ԋ敪      ���@@get������ԋ敪()�̖߂�l
                    l_feqChangeCancelHistoryGroup.orderType = l_strActionStateDiv;
                    
                    //��萔��        ���@@��������.��萔��
                    l_feqChangeCancelHistoryGroup.execQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getExecutedQuantity());
                    
                    //���P��        ���@@��������.���P��
                    l_feqChangeCancelHistoryGroup.execPrice = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getExecutedPrice());
                    
                    //������        ���@@��������.������
                    l_feqChangeCancelHistoryGroup.executionTimestamp = 
                        l_feqOrderActionParams.getExecTimestamp();
                    
                    //��t���ʋ敪      ���@@get��t���ʋ敪()�̖߂�l
                    l_feqChangeCancelHistoryGroup.acceptedResultDiv = 
                        l_strAcceptStatesDiv;
                    
                    //�ʉ݃R�[�h       ���@@�����P��.�ʉ݃R�[�h
                    l_feqChangeCancelHistoryGroup.currencyCode = 
                        l_feqOrderUnits[i].getCurrencyCode();
                }
                //1.4.2.8 add(arg0 : Object)
                //ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B 
                //[����] 
                //arg0�F�@@�v���p�e�B�Z�b�g�����O������������藚�𖾍׃C���X�^���X
                l_lisChangeCancelHistoryGroup.add(l_feqChangeCancelHistoryGroup);                
            }
        }
        //1.5 toArray( )
        //�O������������藚�𖾍ׂ̔z��𐶐�����B
        WEB3FeqChangeCancelHistoryGroup[] l_feqChangeCancelHistorys = 
            new WEB3FeqChangeCancelHistoryGroup[l_lisChangeCancelHistoryGroup.size()];
        
        l_lisChangeCancelHistoryGroup.toArray(l_feqChangeCancelHistorys);

        log.exiting(STR_METHOD_NAME);
        return l_feqChangeCancelHistorys;
    }
    
    /**
     * (get�o����܂Œ����P��FromFirstToLast)<BR>
     * �w�肳�ꂽ�u�o����܂Œ����v�̒����P�ʃI�u�W�F�N�g�ɑ΂���A<BR>
     * �������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g�̈ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@�o����܂Œ����`�F�b�N<BR>
     * �@@�O�����������}�l�[�W��.is�o����܂�<BR>
     * �����P��(�p�����[�^.�����P��) == false�̏ꍇ�A<BR>
     * �@@�p�����[�^.�����P�ʂ݂̂�v�f�Ƃ���O�����������P�ʂ̔z��𐶐����A<BR>
     * �@@�ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g�����L���o�����ɂĎ擾����B<BR>
     * <BR>
     * �@@�@@�����o������<BR>
     * <BR>
     * �@@�@@�@@[�p�����[�^.�����P��.���񒍕��̒����P��ID == 0�i���o����܂Œ�����<BR>
     * �������j�̏ꍇ]<BR>
     * �@@�@@�@@�@@�p�����[�^.�����P�ʁi���������g�j�A<BR>
     * �@@�@@�@@�@@�y�с@@���񒍕��̒����P��ID == �p�����[�^.�����P��.�����P��ID<BR>
     * <BR>
     * �@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@���񒍕��̒����P��ID == �p�����[�^.�����P��.���񒍕���<BR>
     * �����P��ID�@@�܂���<BR>
     * �@@�@@�@@�@@�����P��ID == �p�����[�^.�����P��.���񒍕��̒����P��ID<BR>
     * <BR>
     * �@@�@@�@@�@@��������.���񒍕��̒����P��ID�ɂ́A0���Z�b�g����Ă���ׁB<BR>
     * <BR>
     * �@@�@@�@@�@@�擾���������P�ʃI�u�W�F�N�g���쐬�������ɏ����Ń\�[�g���A<BR>
     * �z��ɂ��ĕԋp����B<BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@roseuid 42A54A49009B
     */
    protected WEB3FeqOrderUnit[] getCarriedOrderUnitFromFirstToLast(
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getCarriedOrderUnitFromFirstToLast(WEB3FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@�o����܂Œ����`�F�b�N 
        //�O�����������}�l�[�W��.is�o����܂Œ����P��(�p�����[�^.�����P��) == false�̏ꍇ�A 
        //�p�����[�^.�����P�ʂ݂̂�v�f�Ƃ���O�����������P�ʂ̔z��𐶐����A 
        //�ԋp����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_blnIsCarriedOrderUnit = 
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);       

        if (!l_blnIsCarriedOrderUnit)
        {
            log.debug("�O�����������}�l�[�W��.is�o����܂Œ����P��(�p�����[�^.�����P��) == false�̏ꍇ");
            
            WEB3FeqOrderUnit[] l_feqOrderUnits = new WEB3FeqOrderUnit[1];
            l_feqOrderUnits[0] = l_feqOrderUnit;
            
            log.exiting(STR_METHOD_NAME);
            return l_feqOrderUnits;
        }
        
        //�Q�j�@@�������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g�����L���o�����ɂĎ擾����B 
        //�����o������ 
        //�@@[�p�����[�^.�����P��.���񒍕��̒����P��ID == 0�i���o����܂Œ����̌������j�̏ꍇ] 
        //�@@�@@    �p�����[�^.�����P�ʁi���������g�j�A 
        //�@@�@@    �y�с@@���񒍕��̒����P��ID == �p�����[�^.�����P��.�����P��ID 
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        FeqOrderManager l_orderManager =
            (FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        if (l_feqOrderUnitParams.getFirstOrderUnitId() == 0)
        {
            log.debug("�p�����[�^.�����P��.���񒍕��̒����P��ID == 0�̏ꍇ");
            
            List l_lisFeqOrderUnits = new ArrayList();
            
            List l_lisSearchRows = null;
            String l_strWhereClause = 
                "order_unit_id = ? or first_order_unit_id = ?"; 
            
            String l_strSortCond = "created_timestamp";
            
            long l_lngOrderUnitId = l_feqOrderUnit.getOrderUnitId();
            log.debug("�����P��ID = " + l_lngOrderUnitId);
            
            Object l_bindVars[] = {
                new Long(l_lngOrderUnitId), 
                new Long(l_lngOrderUnitId)};
            
            try
            {
                l_lisSearchRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        FeqOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_strSortCond, 
                        null,
                        l_bindVars);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (l_lisSearchRows == null || l_lisSearchRows.isEmpty())
            {
                log.debug("�O�������P�ʂ��Ȃ�");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            for (int i = 0; i < l_lisSearchRows.size(); i++)
            {
                FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_lisSearchRows.get(i);
                FeqOrderUnit l_newFeqOrderUnit = 
                    (FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
                
                l_lisFeqOrderUnits.add(l_newFeqOrderUnit);
            }
            WEB3FeqOrderUnit[] l_feqOrderUnits = 
                new WEB3FeqOrderUnit[l_lisFeqOrderUnits.size()];
            
            l_lisFeqOrderUnits.toArray(l_feqOrderUnits);
            
            log.exiting(STR_METHOD_NAME);
            return l_feqOrderUnits;
            
        }
        //�@@�@@[��L�ȊO�̏ꍇ] 
        else
        {
            List l_lisFeqOrderUnits = new ArrayList();
            
            List l_lisSearchRows = null;
            
            //���񒍕��̒����P��ID == �p�����[�^.�����P��.���񒍕��̒����P��ID�@@�܂��� 
            //�����P��ID == �p�����[�^.�����P��.���񒍕��̒����P��ID 
            
            String l_strWhereClause = 
                "first_order_unit_id = ? or order_unit_id = ?";            
            
            String l_strSortCond = "created_timestamp";
            
            long l_lngFirstOrderUnitId = l_feqOrderUnitParams.getFirstOrderUnitId();
            log.debug("���񒍕��̒����P��ID = " + l_lngFirstOrderUnitId);
            
            Object l_bindVars[] = {
                new Long(l_lngFirstOrderUnitId), 
                new Long(l_lngFirstOrderUnitId)};
            
            try
            {
                l_lisSearchRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        FeqOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_strSortCond, 
                        null,
                        l_bindVars);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (l_lisSearchRows == null || l_lisSearchRows.isEmpty())
            {
                log.debug("�O�������P�ʂ��Ȃ�");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            for (int i = 0; i < l_lisSearchRows.size(); i++)
            {
                FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_lisSearchRows.get(i);
                FeqOrderUnit l_newFeqOrderUnit = 
                    (FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
                
                l_lisFeqOrderUnits.add(l_newFeqOrderUnit);
            }
            WEB3FeqOrderUnit[] l_feqOrderUnits = new WEB3FeqOrderUnit[l_lisFeqOrderUnits.size()];
            l_lisFeqOrderUnits.toArray(l_feqOrderUnits);
            
            log.exiting(STR_METHOD_NAME);
            return l_feqOrderUnits;
        }
    }
}
@
