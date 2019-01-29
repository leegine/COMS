head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�XImpl(WEB3AdminToEquityOrderReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03�@@������(���u) �V�K�쐬
Revesion History : 2006/7/5 ���G�� (���u) �d�l�ύX���f��076
Revesion History : 2006/7/5 �юu�� (���u) �d�l�ύX���f��077
Revesion History : 2006/10/18  ������(���u) �d�l�ύX���f��No.096
Revesion History : 2006/10/23  ������(���u) �d�l�ύX���f��No.085
Revesion History : 2006/10/30  �đo�g(���u) �d�l�ύX���f��No.100
Revesion History : 2006/11/20  ����(���u) �d�l�ύX���f��No.101�A102�A104�A105�A107�A110�A111�A118
Revision History : 2006/11/20  ꎉ�(���u) �d�l�ύX���f��No.119
Revision History : 2006/12/12  ꎉ�(���u) �d�l�ύX���f��No.123
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.data.AdmintoEqtypeOrderUnitRow;
import webbroker3.admintriggerorder.define.WEB3AdminToEquityKeyItemDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTickMatchDivDef;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderRefSortKey;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToEquityOrderReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.data.RlsOrderMissParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�XImpl)<BR>
 * �g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�XImpl<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminToEquityOrderReferenceServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3AdminToEquityOrderReferenceService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToEquityOrderReferenceServiceImpl.class);
    
    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AdminToEquityOrderReferenceServiceImpl() 
    {
     
    }
    
    /**
     * ���������Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE���������Ɖ���̓��N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE���������Ɖ�N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.get�Ɖ���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
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
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminToEquityOrderRefInpRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToEquityOrderRefInpRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToEquityOrderRefRefRequest)
        {   
            l_response = this.getReferenceScreen((WEB3AdminToEquityOrderRefRefRequest) l_request);
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
     * ���������Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�jget���͉�ʁv<BR>
     * �Q�ƁB<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�jget���͉�� �v<BR>
     * 1.7 (*)����t���[�F(*1)�ō쐬�����s��z��0���̏ꍇ�A�Ɩ��G���[���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02692<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE���������Ɖ���̓��N�G�X�g <BR>
     * @@return WEB3AdminToEquityOrderRefInpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminToEquityOrderRefInpResponse getInputScreen(
        WEB3AdminToEquityOrderRefInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToEquityOrderRefInpRequest)";
        log.entering(STR_METHOD_NAME);

        l_request.validate();
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.��������(�g���K�[�����Ɖ�) 
        //is�X�V�F�@@false(�X�V�Ȃ�) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_TRIGGER_ORDER_INQUIRY, false);

        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.5 ArrayList( )�s��R�[�h
        List l_lisMarkets = new ArrayList();
        //������
        List l_lisDates = new ArrayList();


        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //1.8 ���N�G�X�g.���X�R�[�h�̗v�f����Loop����

        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            //1.8.1get���X(�،���ЃR�[�h : String, ���X�R�[�h : String)
            WEB3GentradeBranch l_branch  = null;
            try
            {
                l_branch = 
                    l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_request.branchCode[i]);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(),l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }

            //1.6 get�戵�\�s��(���X : ���X, �ٍϋ敪 : String, �ٍϊ����l : double)
            String[] l_strPossibleMarkets = null;

            String[] l_strMarkets =
                WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                    l_branch, ProductTypeEnum.EQUITY);

            //1.7(*1)�s�ꂪArrayList�Ɋ܂܂�Ă��Ȃ��ꍇ�A�ǉ�����B
            if (l_strMarkets != null && l_strMarkets.length != 0)
            {
                for (int y = 0; y < l_strMarkets.length; y++)
                {
                    if (!l_lisMarkets.contains(l_strMarkets[y]))
                    {
                        l_lisMarkets.add(l_strMarkets[y]);
                    }
                }
            }

            //1.8.2get�戵�\�s��(���X : ���X, �ٍϋ敪 : String, �ٍϊ����l : double)
            l_strPossibleMarkets =
                WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
                    l_branch, WEB3GentradeRepaymentDivDef.DEFAULT, 0);
            
            //1.8.3(*1)�s�ꂪArrayList�Ɋ܂܂�Ă��Ȃ��ꍇ�A�ǉ�����B
            if (l_strPossibleMarkets != null && l_strPossibleMarkets.length != 0)
            {
                for (int k = 0; k < l_strPossibleMarkets.length; k++)
                {
                    if (!l_lisMarkets.contains(l_strPossibleMarkets[k]))
                    {
                        l_lisMarkets.add(l_strPossibleMarkets[k]);
                    }
                }
            }
        }

        //(*)����t���[�F(*1)�ō쐬�����s��z��0���̏ꍇ�A�Ɩ��G���[���X���[����B
        if (l_lisMarkets == null || l_lisMarkets.isEmpty())         
        {           
            log.debug("�戵�\�s�ꂪ���݂��Ȃ��B");         
            log.exiting(STR_METHOD_NAME);           
            throw new WEB3BusinessLayerException(           
                WEB3ErrorCatalog.BUSINESS_ERROR_00643,          
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�戵�\�s�ꂪ���݂��Ȃ��B");           
        }
        
        //1.9�s��̔z��𐶐�����B
        String[] l_strHandlingPossibleMarket = new String[l_lisMarkets.size()];
        l_lisMarkets.toArray(l_strHandlingPossibleMarket);

        //1.10(*)�Ɩ����t�̑O�c�Ɠ��A�Ɩ����t��ǉ�����B
        //      �Ɩ����t�F GtlUtils.getTradingSystem().getBizDate()
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDateCalcUtil = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        Timestamp l_tsDevidendRightDate = l_bizDateCalcUtil.roll(-1);
        l_lisDates.add(WEB3DateUtility.toDay(l_tsDevidendRightDate));
        l_lisDates.add(WEB3DateUtility.toDay(l_datBizDate));

        //1.11(*)�s��z��̗v�f����Loop����
        if (l_strHandlingPossibleMarket != null)
        {
            for (int i = 0; i < l_strHandlingPossibleMarket.length; i++)
            {
                //1.11.1reset�s��R�[�h(�s��R�[�h : String)
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strHandlingPossibleMarket[i]);
                //1.11.2get������( )
                l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                //1.11.3(*2)�擾������������ArrayList�Ɋ܂܂�Ă��Ȃ��ꍇ�A�ǉ�����B
                if (l_datBizDate != null)
                {

                    if (!l_lisDates.contains(l_datBizDate))
                    {
                        l_lisDates.add(WEB3DateUtility.toDay(l_datBizDate));
                    }
                }
                
            }                        
        }
        
        Date[] l_datBizDates = new Date[l_lisDates.size()]; 
        l_lisDates.toArray(l_datBizDates);
        
        //1.12get�戵�\����������ʈꗗ�iPR�w�j(String, String[], String)
        String[] l_strOrderTypeList = 
            this.getValidTriggerOrderTypeListByPr(l_strInstitutionCode, l_strHandlingPossibleMarket, ProductTypeEnum.EQUITY);
        
        WEB3AdminToEquityOrderRefInpResponse l_response = (WEB3AdminToEquityOrderRefInpResponse)l_request.createResponse();
        
        //1.13�v���p�e�B�Z�b�g
        //�s��ꗗ      ���@@(*1)�Ő��������z��
        l_response.marketList = l_strHandlingPossibleMarket;
        //�������ꗗ ���@@(*2)�Ő��������z��
        l_response.orderBizDateList = l_datBizDates;
        //����������ʈꗗ  ���@@get�戵�\����������ʈꗗ�iPR�w�̖߂�l
        l_response.triggerOrderTypeList = l_strOrderTypeList;
        return l_response;
    }
    
    /**
     * (get�Ɖ���)<BR>
     * ���������Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X�jget�Ɖ��ʁv<BR>
     * �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE���������Ɖ�N�G�X�g<BR>
     * @@return WEB3AdminToEquityOrderRefRefResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminToEquityOrderRefRefResponse getReferenceScreen(
        WEB3AdminToEquityOrderRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminToEquityOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.��������(�g���K�[�����Ɖ�)
        //is�X�V�F�@@false(�X�V�Ȃ�)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_TRIGGER_ORDER_INQUIRY, false);

        //1.4 validate���X����(���X�R�[�h : String[])
        l_admin.validateBranchPermission(l_request.branchCode);

        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.6 create���ʌ�������������(�،���ЃR�[�h : String,
        //    ���N�G�X�g�f�[�^ : �g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g)
        String l_strCommonQueryString = WEB3AdminToDataManager.createCommonQueryString(
            l_strInstitutionCode, l_request);

        //1.7 get�����h�c(String, String)
        //[����]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
        String l_strProductId = this.getProductId(l_strInstitutionCode, l_request.productCode);

        //1.8 create����������(String)
        String l_strQueryString = this.createQueryString(l_strProductId);

        //1.9 create���ʌ��������f�[�^�R���e�i(�،���ЃR�[�h : String,
        //���N�G�X�g�f�[�^ : �g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g, �����^�C�v : ProductTypeEnum)
        String[] l_strCommonDataContainers =
            WEB3AdminToDataManager.createCommonQueryDataContainer(
                l_strInstitutionCode,
                l_request,
                ProductTypeEnum.EQUITY);

        //1.10 create���������f�[�^�R���e�i(String)
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_strProductId);

        //1.11 create�\�[�g����(�����Ɖ�\�[�g�L�[[])
        //[����]
        //�\�[�g�L�[�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strSortCondition = this.createSortCond(l_request.sortKeys);

        //1.12 get�����P�ʈꗗ(String, String[], String)
        //[get�����P�ʈꗗ()�̈����ݒ�]
        //��������������F
        //�@@create���ʌ�������������()�̖߂�l�{create��������������()�̖߂�l
        //���������f�[�^�R���e�i�F
        //  create���ʌ��������f�[�^�R���e�i()�̖߂�l�{create���������f�[�^�R���e�i()�̖߂�l
        //�\�[�g�����F
        //�@@create�\�[�g����()�̖߂�l
        int l_intDcLength = l_strCommonDataContainers.length;
        int l_intQdcLength = l_strQueryDataContainers.length;
        String[] l_strContainers = new String[l_intDcLength + l_intQdcLength];
        List l_lisContainers = new ArrayList();
        for (int i = 0; i < l_intDcLength; i++)
        {
            l_lisContainers.add(l_strCommonDataContainers[i]);
        }

        for (int i = 0; i < l_intQdcLength; i++)
        {
            l_lisContainers.add(l_strQueryDataContainers[i]);
        }

        l_lisContainers.toArray(l_strContainers);

        EqtypeOrderUnitParams[] l_orderUnitParamses = this.getOrderUnitList(
            l_strCommonQueryString + l_strQueryString,
            l_strContainers,
            l_strSortCondition);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManagerImpl =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        EqTypeOrderUnit[] l_carryOverOrderUnit = null;
        //1.13���b�Z�[�W ����i�t���[�Fget�����P�ʈꗗ�̖߂�l��null�̏ꍇ�j
        if (l_orderUnitParamses != null)
        {
            int l_intLength = l_orderUnitParamses.length;
            EqTypeOrderUnit[] l_orderUnits = new EqTypeOrderUnit[l_intLength];
            for (int i = 0; i < l_intLength; i++)
            {
                l_orderUnits[i] = (EqTypeOrderUnit)l_orderManagerImpl.toOrderUnit(l_orderUnitParamses[i]);
            }
            //1.13.1 remove�J�z�������P��(�����P��Params�ꗗ : EqTypeOrderUnitParams[])
            //[����]
            //�����P��Params�ꗗ�F�@@get�����P�ʈꗗ()�̖߂�l
            l_carryOverOrderUnit = l_orderManagerImpl.removeCarryOverOriginalOrderUnit(l_orderUnits);
        }

        WEB3AdminToEquityOrderRefRefResponse l_response =
            (WEB3AdminToEquityOrderRefRefResponse)l_request.createResponse();
        //1.14 �i����t���[�F remove�J�z�������P��()�̖߂�l��null�@@�܂��́@@get�����P�ʈꗗ�̖߂�l == null�̏ꍇ�j
        if (l_carryOverOrderUnit == null || l_orderUnitParamses == null)
        {
            //1.14.2(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
            //���y�[�W�� ��0
            l_response.totalPages = "0";
            //�����R�[�h�� ��0
            l_response.totalRecords = "0";
            //�\���y�[�W�ԍ�  ��0
            l_response.pageIndex = "0";
            //���������Ɖ�Unit�ꗗ   ��null
            l_response.equityOrderList = null;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.15 WEB3PageIndexInfo(l_objs : �_���r���[::java::lang::Object[],
        //l_intRequestPageIndex : l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_carryOverOrderUnit,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

        //1.16 getArrayReturned(l_classType : Class)
        EqTypeOrderUnit[] l_eqTypeOrderUnits =
            (EqTypeOrderUnit[])l_pageIndexInfo.getArrayReturned(EqTypeOrderUnit.class);

        //1.17create���������Ɖ�Unit�ꗗ(�����P��[],  ���������Ɖ�N�G�X�g)
        WEB3AdminToEquityOrderRefUnit[] l_refUnits =
            this.createEquityOrderRefUnitList(l_eqTypeOrderUnits, l_request);

        //1.21 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //���y�[�W��       �� WEB3PageIndexInfo.getTotalPages()
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());

        //�����R�[�h��      �� WEB3PageIndexInfo.getTotalRecords()
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

        //�\���y�[�W�ԍ�     �� WEB3PageIndexInfo.getPageIndex()
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());

        //���������Ɖ�Unit�ꗗ   ��create���������Ɖ�Unit�ꗗ()�̖߂�l
        l_response.equityOrderList = l_refUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get����ID)<BR>
     * �����̏����ɊY�����銔������.����ID��ԋp����B<BR> 
     * <BR>
     * �P�j�p�����[�^.�����R�[�h��null�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j DB���� <BR>
     * �@@�ȉ��̏����Ŋ��������e�[�u������������B <BR>
     * <BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h ���� <BR>
     * �@@�����R�[�h = �p�����[�^.�����R�[�h <BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v�� <BR>
     * �@@��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * <BR>
     * �R�j �������ʂ�ԋp����B <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getProductId(String l_strInstitutionCode, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductId(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�p�����[�^.�����R�[�h��null�̏ꍇ�́Anull��ԋp����B
        if (l_strProductCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�Q�j DB�����ȉ��̏����Ŋ��������e�[�u������������B
        //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h ���� 
       //�����R�[�h = �p�����[�^.�����R�[�h 
        String l_strWhere = " institution_code = ? ";
        l_strWhere += " and product_code = ? ";
        
        ArrayList l_lisConditions = new ArrayList();
        l_lisConditions.add(l_strInstitutionCode);
        l_lisConditions.add(l_strProductCode);
        
        
        String[] l_strConditions = new String[l_lisConditions.size()];
        l_lisConditions.toArray(l_strConditions);
        List l_lisReturns = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns = l_queryProcessor.doFindAllQuery(
                EqtypeProductRow.TYPE,
                l_strWhere,
                l_strConditions);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
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

        //�������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B
        if ( l_lisReturns == null || l_lisReturns.size() == 0)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //�R�j �������ʂ�ԋp����B
        EqtypeProductRow[] l_rows = new EqtypeProductRow[l_lisReturns.size()];
        l_lisReturns.toArray(l_rows);
        
        log.exiting(STR_METHOD_NAME);
        return String.valueOf(l_rows[0].getProductId());
    }
    
    /**
     * (create��������������)<BR>
     * ����������������쐬����B  <BR>
     * <BR>
     * �P�j�@@��������������C���X�^���X(�FString)�𐶐�����B<BR>  
     * <BR>
     * �Q�j�@@�p�����[�^.����ID��null�̏ꍇ�A  <BR>
     * �@@����ID����������������ɒǉ�����B<BR> 
     * <BR>
     * �@@�������������� += "and product_id = ? "<BR> 
     * <BR>
     * �R�j�@@�쐬�������������������ԋp����B<BR>  
     * <BR>
     * @@param l_strProductId - (����ID)<BR>
     * @@return String
     */
    protected String createQueryString(String l_strProductId) 
    {
        final String STR_METHOD_NAME = " createQueryString(String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strQueryString = new String();
        if (null != l_strProductId)
        {
            l_strQueryString += "and product_id = ? ";
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>  
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B  <BR>
     * <BR>
     * �Q�j�@@�p�����[�^.����ID��null�̏ꍇ�A <BR> 
     * �@@����ID�𐶐�����ArrayList�ɒǉ�����B <BR> 
     * <BR>
     * �R�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>  
     * @@param l_strProductId - (����ID)<BR>
     * @@return String[]
     */
    protected String[] createQueryDataContainer(String l_strProductId) 
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�𐶐�����B
        ArrayList l_lisQueryDataContainers = new ArrayList();
        //�Q�j�@@�p�����[�^.����ID��null�̏ꍇ�A����ID�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_strProductId)
        {
            l_lisQueryDataContainers.add(l_strProductId);
        }
        
        //�R�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����
        String[] l_strContainers = new String[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_strContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strContainers;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�������쐬����B  <BR>
     * <BR>
     * �P�j�@@�p�����[�^.�\�[�g�L�[��null�̏ꍇ�A <BR> 
     * �@@�@@"����ID ����"�̃\�[�g������ԋp����B<BR>  
     * <BR>
     * �Q�j�@@�\�[�g����������(�FString)���쐬����B  <BR>
     * <BR>
     * �R�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>  
     * �@@�R�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>  
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u���X�R�[�h�v�@@���@@�����P��.���XID<BR>
     * �@@�@@�E�u�ڋq�R�[�h�v�@@���@@substr(�����P��.����ID, 9, 6)<BR> 
     * �@@�@@�E�u�����R�[�h�v�@@���@@�����P��.����ID <BR>
     * �@@�@@�E�u�s��R�[�h�v�@@���@@�����P��.�s��ID <BR> 
     * �@@�@@�E�u�����敪�v�@@���@@�����P��.�ŋ敪<BR>
     * �@@�@@�E�u����敪�v�@@���@@�����P��.������� <BR> 
     * �@@�@@�E�u�ٍρv�@@���@@�����P��.�ٍϋ敪<BR>
     * �@@�@@�E�u�l�i�����v�@@���@@�����P��.�l�i����<BR>
     * �@@�@@�E�u���s�����v�@@���@@�����P��.���s����<BR>  
     * �@@�@@�E�u�����L�������v�@@���@@�����P��.�����������t<BR>  
     * �@@�@@�E�u�������ԁv�@@���@@�����P��.�󒍓���<BR> 
     * �@@�@@�E�u�������v�@@���@@�����P��.������ <BR>
     * �@@�@@�E�u��n���v�@@���@@�����P��.��n��<BR>  
     * <BR>
     * �@@�R�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾���� <BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>  
     * <BR>
     * �S�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt�� <BR> 
     * <BR>
     * �T�j�@@�쐬�����\�[�g�����������ԋp����B<BR> 
     * @@param l_sortKeys - (�����Ɖ�\�[�g�L�[[])<BR>
     * @@return String
     */
    protected String createSortCond(WEB3AdminToOrderRefSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = " createSortCondition(WEB3AdminToOrderRefSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�p�����[�^.�\�[�g�L�[��null�̏ꍇ�A"����ID ����"�̃\�[�g������ԋp����B
        if (null == l_sortKeys)
        {
            log.exiting(STR_METHOD_NAME);
            return " product_id asc ";
        }

        //�Q�j�@@�\�[�g����������(�FString)���쐬����B
        StringBuffer l_strBuf = new StringBuffer();
        
        //�R�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //�R�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����B
            //�E�u���X�R�[�h�v�@@���@@�����P��.���XID
            if (WEB3AdminToEquityKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("branch_id");
            }
            //�E�u�ڋq�R�[�h�v�@@���@@substr(�����P��.����ID, 9, 6)
            else if (WEB3AdminToEquityKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("substr(account_id, 9, 6)");
            }
            //�E�u�����R�[�h�v�@@���@@�����P��.����ID
            else if (WEB3AdminToEquityKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("product_id");
            }
            //�E�u�s��R�[�h�v�@@���@@�����P��.�s��ID
            else if (WEB3AdminToEquityKeyItemDef.MARKET_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("market_id");
            }
            //�E�u�����敪�v�@@���@@�����P��.�ŋ敪
            else if (WEB3AdminToEquityKeyItemDef.TAX_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("tax_type");
            }
            //�E�u����敪�v�@@���@@�����P��.�������
            else if (WEB3AdminToEquityKeyItemDef.TRADING_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("order_type");
            }
            //�E�u�ٍρv�@@���@@�����P��.�ٍϋ敪
            else if (WEB3AdminToEquityKeyItemDef.REPAYMENT_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("repayment_type");
            }
            //�E�u�l�i�����v�@@���@@�����P��.�l�i����
            else if (WEB3AdminToEquityKeyItemDef.PRICE_CONDITION_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("price_condition_type");
            }            
            //�E�u���s�����v�@@���@@�����P��.���s����
            else if (WEB3AdminToEquityKeyItemDef.EXEC_COND_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("execution_condition_type");
            }
            //�E�u�����L�������v�@@���@@�����P��.�����������t
            else if (WEB3AdminToEquityKeyItemDef.EXPIRATION_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("expiration_date");
            }
            //�E�u�������ԁv�@@���@@�����P��.�󒍓���
            else if (WEB3AdminToEquityKeyItemDef.ORDER_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("received_date_time");
            }
            //�E�u�������v�@@���@@�����P��.������
            else if (WEB3AdminToEquityKeyItemDef.ORDER_BIZ_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("biz_date");
            }
            //�E�u��n���v�@@���@@�����P��.��n��
            else if (WEB3AdminToEquityKeyItemDef.DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("delivery_date");
            }
            else
            {
                continue;
            }
         
            //�R�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_strBuf.append(" asc,");
            }
            else
            {
                l_strBuf.append(" desc,");
            }
        }     

        //�S�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��
        l_strBuf.append(" last_updated_timestamp");
        l_strBuf.append(" asc");
        
        //�T�j�@@�쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strBuf.toString();
    }
    
    /**
     * (get�����P�ʈꗗ)<BR>
     * �����̏����ɊY�����钍���P�ʂ̈ꗗ��ԋp����B<BR>  
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>  
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>  
     * �@@�@@arg0�F�@@�����P��ViewRow(AdmintoEqtypeOrderUnitRow).TYPE<BR> 
     * �@@�@@arg1�F�@@�p�����[�^.��������������<BR>  
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>  
     * �@@�@@arg3�F�@@null<BR>  
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>  
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>  
     * <BR>
     * �Q�j�������ʂ�List����EqtypeOrderUnitParams[]�𐶐����ĕԋp����B<BR>  
     * @@param l_strQueryString - (��������������)<BR>
     * @@param l_strQueryDataContainer - (���������f�[�^�R���e�i)<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * @@return EqtypeOrderUnitParams[]
     * @@throws WEB3BaseException
     */
    protected EqtypeOrderUnitParams[] getOrderUnitList(
        String l_strQueryString, 
        String[] l_strQueryDataContainer,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnitList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //  �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        //  �@@arg0�F�@@�����P��ViewRow(AdmintoEqtypeOrderUnitRow).TYPE
        //�@@�@@arg1�F�@@�p�����[�^.��������������
        //�@@�@@arg2�F�@@�p�����[�^.�\�[�g����
        //�@@�@@arg3�F�@@null
        //�@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i
        List l_lisReturns = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns = l_queryProcessor.doFindAllQuery(
                AdmintoEqtypeOrderUnitRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
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
        
        //�Q�j�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (null == l_lisReturns || l_lisReturns.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�R�j �������ʂ�List����EqtypeOrderUnitParams[]�𐶐����ĕԋp����B
        EqtypeOrderUnitParams[] l_orderUnitParams = new EqtypeOrderUnitParams[l_lisReturns.size()];
        for (int i = 0; i < l_lisReturns.size(); i++)
        {
            l_orderUnitParams[i] = toEqtypeOrderUnitParams((AdmintoEqtypeOrderUnitRow)l_lisReturns.get(i));
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (create���������Ɖ�Unit�ꗗ)<BR>
     * �����̒����P�ʈꗗ���A�����������Ɖ�Unit�̈ꗗ�� <BR> 
     * �쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>  
     * �u(�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�X)create���������Ɖ�Unit�ꗗ�v<BR>  
     * �Q�ƁB<BR> 
     * @@param l_orderUnitList - (�����P�ʈꗗ)<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE���������Ɖ�N�G�X�g
     * @@return WEB3AdminToEquityOrderRefUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AdminToEquityOrderRefUnit[] createEquityOrderRefUnitList(
        EqTypeOrderUnit[] l_orderUnitList, 
        WEB3AdminToEquityOrderRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createEquityOrderRefUnitList(EqTypeOrderUnit[], WEB3AdminToEquityOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitList == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }

        //1.1 ArrayList( )
        List l_lisOrderUnits = new ArrayList();
        
        //1.2 (*)�p�����[�^.�����P�ʈꗗ�v�f����Loop����
        int l_intLength = l_orderUnitList.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //1.2.1 getOrderId( )
            long l_lngOrderId = l_orderUnitList[i].getOrderId();
            
            //1.2.2 getBranchId( )
            long l_lngBranchId = l_orderUnitList[i].getBranchId();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

            WEB3EquityTradingModule l_tradingModule
                = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager
                = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnitList[i].getDataSourceObject();

            //1.2.4 getAccountId( )
            long l_lngAccountId = l_orderUnitList[i].getAccountId();

            WEB3GentradeBranch l_branch = null;
            WEB3GentradeMainAccount l_mainAccount = null;
            WEB3GentradeMarket l_market = null;
            try
            {
                //1.2.3 getBranch(arg0 : long)
                l_branch = (WEB3GentradeBranch) l_accountManager.getBranch(l_lngBranchId);
                //1.2.5 getMainAccount(arg0 : long)
                l_mainAccount = (WEB3GentradeMainAccount) l_accountManager.getMainAccount(l_lngAccountId);
                l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());

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
            
            //1.2.6 get�\���ڋq�R�[�h( )
            String l_strDisplayAccountCode = l_mainAccount.getDisplayAccountCode();
            
            //1.2.7 getProduct( )
            EqTypeProduct l_product = (EqTypeProduct) l_orderUnitList[i].getProduct();
            
            //reset�s��R�[�h(�s��R�[�h : String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            
            //is�蓮�����\(�����P�� : EqTypeOrderUnit)
            boolean l_blnIsManualOrderPossible = 
                l_orderManager.isManualOrderPossible(l_orderUnitList[i]);
   
            //1.2.8 get���i�敪(EquityOrderUnit)
            String l_strProductDiv = WEB3EquityDataAdapter.getProductType(l_orderUnitList[i]);
            
            //1.2.9 ����敪���擾����B (������� : OrderTypeEnum)
            String l_strTradingType = WEB3EquityDataAdapter.getTradingType(
                l_orderUnitList[i].getOrderType());
            
            //1.2.10 ������ԋ敪���擾����B  (�����P�� : OrderUnit)
            String l_strOrderState = WEB3EquityDataAdapter.getOrderState(l_orderUnitList[i]);
            
            //1.2.11 get�����敪(�ŋ敪 : TaxTypeEnum)
            //�����敪���擾����B
            //[����]
            //�ŋ敪�F�@@�����P��.�ŋ敪
            String l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitList[i].getTaxType());

            String l_strExecType = WEB3EquityDataAdapter.getExecType(l_orderUnitList[i]);
            
            //1.2.12 get���s�����iSONAR�j(���s���� : EqTypeExecutionConditionType)
            String l_strExecCond = 
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
            
            //1.2.13is�o����܂Œ����P��
            boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnitList[i]);
            //1.2.14 isMarketOrder( )
            boolean l_blnIsMarketOrder = l_orderUnitList[i].isMarketOrder();
            
            //get�����󋵋敪(�����P�� : EqTypeOrderUnit, ����������� : String)
            String l_strOrderStatusType = 
                WEB3EquityDataAdapter.getOrderStatusType(l_orderUnitList[i], l_request.triggerOrderType);
            
            //1.2.16 get���[���G���W������̒ʒm(�����P�� : OrderUnit, ����������� : String,
            //       �����^�C�v : ProductTypeEnum)
            //  �����P�ʁF�@@�����P��
            //  ����������ʁF�@@�p�����[�^.�����������
            //  �����^�C�v�F�@@ProductTypeEnum.���� 
            RlsConOrderHitNotifyParams l_notifyParams = 
                WEB3AdminToDataManager.getRlsConOrderHitNotify(
                    l_orderUnitList[i], l_request.triggerOrderType, ProductTypeEnum.EQUITY); 
            
            //get�������s����(OrderUnit, String, ProductTypeEnum, String)
            //[����]  
            // �����P�ʁF�@@�����P��  
            // ����������ʁF�@@�p�����[�^.���N�G�X�g�f�[�^.�����������  
            // �����^�C�v�F�@@ProductTypeEnum.����  
            // ���s�敪�F�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ��敪(*)  
            // (*)���ݒl�ƍ��敪��null�A���A  
            // �@@���ݒl�ƍ��敪��"�S�ăG���["�̏ꍇ�̂ݐݒ�
            String l_strMissType = null;
            String l_strTickMatchDiv = l_request.tickMatchDiv;
            if (l_strTickMatchDiv != null
                && !WEB3AdminToTickMatchDivDef.ALL_ERROR.equals(l_strTickMatchDiv))
            {
                l_strMissType = l_strTickMatchDiv;
            }
            RlsOrderMissParams l_rlsOrderMissParams = 
                WEB3AdminToDataManager.getRlsOrderMiss(
                    l_orderUnitList[i], 
                    l_request.triggerOrderType, 
                    ProductTypeEnum.EQUITY, 
                    l_strMissType);
            
            //1.2.17�g���K�[�����Ǘ��ҁE���������Ɖ�Unit( )
            WEB3AdminToEquityOrderRefUnit l_orderRefUnit = new WEB3AdminToEquityOrderRefUnit();
            
            //1.2.18�v���p�e�B�Z�b�g
            //ID        �� �����P��.����ID
            l_orderRefUnit.id = "" + l_orderUnitList[i].getOrderId();
            
            //�����������    �� �p�����[�^.�����������
            l_orderRefUnit.triggerOrderType = l_request.triggerOrderType;
            
            //�����������Z�q   �� �����f�[�^�A�_�v�^.get�����������Z�q(�����P��)�̖߂�l
            l_orderRefUnit.condOperator = WEB3EquityDataAdapter.getOrderCondOperator(
        		l_orderUnitList[i]);
            
            //���������P��    �� �����f�[�^�A�_�v�^.get�t�w�l��l(�����P��)�̖߂�l
            l_orderRefUnit.orderCondPrice = WEB3EquityDataAdapter.getStopOrderPrice(
        		l_orderUnitList[i]);
            
            //�v�w�l�p�����P���敪 = �����f�[�^�A�_�v�^.get�v�w�l�p�����P���敪(�����P��)�̖߂�l
            String l_strWLimitOrderPriceDiv = WEB3EquityDataAdapter.getWLimitOrderPriceDiv(l_orderUnitList[i]);
            l_orderRefUnit.wLimitOrderPriceDiv = l_strWLimitOrderPriceDiv;
            
            //�v�w�l�p�����P�� = (*1)�����f�[�^�A�_�v�^.get�v�w�l�p�����P��(�����P��)�̖߂�l
            //(*1)W�w�l�p�����P���敪��"�w�l"�̏ꍇ�̂݃Z�b�g
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strWLimitOrderPriceDiv))
            {
                l_orderRefUnit.wLimitPrice = 
                    WEB3EquityDataAdapter.getWLimitOrderPrice(l_orderUnitList[i]);
            }
            
            //�v�w�l�p���s���� = �����f�[�^�A�_�v�^.get�v�w�l�p���s����(�����P��)�̖߂�l
            l_orderRefUnit.wlimitExecCondType = 
                WEB3EquityDataAdapter.getWLimitExecCondType(l_orderUnitList[i]);
            
            //�v�w�l�p�L����ԋ敪 = �����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪(�����P��)�̖߂�l
            l_orderRefUnit.wlimitEnableStatusDiv = 
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnitList[i]);
            
            //W�w�l�p�֑ؑO�����P�� = = �����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P��(�����P��)�̖߂�l
            l_orderRefUnit.wlimitBefChgLimitPrice = 
                WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnitList[i]);
            
            //W�w�l�p�֑ؑO���s���� = �����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s����(�����P��)�̖߂�l
            l_orderRefUnit.wlimitBefChgExecCondType = 
                WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnitList[i]);

            //���X�R�[�h       �� getBranch()�̖߂�l.���X�R�[�h
            l_orderRefUnit.branchCode = l_branch.getBranchCode();
            
            //�ڋq�R�[�h       �� getMainAccount()�̖߂�l.get�\���ڋq�R�[�h()
            l_orderRefUnit.accountCode = l_strDisplayAccountCode;
            
            //�s��R�[�h       �� �����P��.�s��ID�ɊY������s��.�s��R�[�h
            l_orderRefUnit.marketCode = l_market.getMarketCode();
            
            //������     �� ������     �� null
            l_orderRefUnit.productName = null;
            
            //���i�敪        �� get���i�敪()�̖߂�l
            l_orderRefUnit.productDiv = l_strProductDiv;
            
            //����敪��get����敪�i�j�̖߂�l
            l_orderRefUnit.tradingType = l_strTradingType;
            
            //���s����      �� get���s�����iSONAR�j
            l_orderRefUnit.execCondType = l_strExecCond;
            
            //�����L������    �� is�o����܂Œ����P()�̖߂�l��true�̏ꍇ�A�����P��.�����������t���Z�b�g
            if (l_blnIsCarriedOrderUnit)
            {
                l_orderRefUnit.expirationDate = l_orderUnitRow.getExpirationDate();
            }
            
            //��������      �� �����P��.��������
            l_orderRefUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
            
            //�����P���敪    �� isMarketOrder()�̖߂�l��true�̏ꍇ�A"���s"���Z�b�g�Bfalse�̏ꍇ�A"�w�l"���Z�b�g�B
            if (l_blnIsMarketOrder)
            {
                l_orderRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_orderRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                //�����P��        �� �����P���敪��"�w�l"�̏ꍇ�A�����P��.�w�l���Z�b�g�B
                l_orderRefUnit.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
            }
            
            //������ԋ敪    �� get������ԋ敪()�̖߂�l
            l_orderRefUnit.orderState = l_strOrderState;
            
            //����ԋ敪    �� get����ԋ敪()�̖߂�l
            l_orderRefUnit.execType = l_strExecType;
            
            //��������敪  �� �����P��.���������E����敪
            l_orderRefUnit.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
            
            //��������        �� �����P��.�󒍓���
            l_orderRefUnit.orderDate = l_orderUnitRow.getReceivedDateTime();
                        
            //������     �� �����P��.������
            l_orderRefUnit.orderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
            
            //��n��     �� �����P��.��n��
            l_orderRefUnit.deliveryDate = WEB3DateUtility.toDay(l_orderUnitRow.getDeliveryDate());
            
            //�����󋵋敪    �� get�����󋵋敪�i�����P�ʁC����������ʁj�̖߂�l
            l_orderRefUnit.triggerOrderState = l_strOrderStatusType;
            
            //(*2)get���[���G���W������̒ʒm()�̖߂�l��null�̏ꍇ�Z�b�g
            if (null != l_notifyParams)
            {
                //��������M����    �� (*2)���[���G���W������̒ʒmParams.tick�q�b�g�^�C���X�^���v
                l_orderRefUnit.currentPriceInfoAcceptTime = l_notifyParams.getHitTickTimestamp();
                //�g���K�[�N������    �� (*2)���[���G���W������̒ʒmParams.���[���G���W���t�@@�C�A�^�C���X�^���v
                l_orderRefUnit.triggerStartTime = l_notifyParams.getRlsHitTimestamp();
                //������������  �� (*2)���[���G���W������̒ʒmParams.���������^�C���X�^���v
                l_orderRefUnit.orderCompleteTime = l_notifyParams.getOrderSubmitTimestamp();
            }
            
            if (null != l_rlsOrderMissParams)
            {
                //���ݒl�\�z���� = (*2)�������s����Params.tick�q�b�g�^�C���X�^���v
                l_orderRefUnit.tickExpectTime = l_rlsOrderMissParams.getHitTickTimestamp();
                //���ݒl�ƍ��敪 = get�������s�����̖߂�l��null�̏ꍇ"����"�B�ȊO�A�������s����Params.���s�敪
                l_orderRefUnit.tickMatchDiv = l_rlsOrderMissParams.getMissType();
            }
            else
            {
                l_orderRefUnit.tickMatchDiv = WEB3AdminToTickMatchDivDef.NORMAL;    
            }
            
            //�蓮�����\�t���O   �� �g�����������}�l�[�W��.is�蓮�����\(�����P��)�̖߂�l�B
            l_orderRefUnit.manualFlag = l_blnIsManualOrderPossible;
            
            //---------- ���������v���p�e�B ----------------------------
            //�����敪        �� get�����敪()�̖߂�l
            l_orderRefUnit.taxType = l_strTaxType;
            
            //�����R�[�h       �� getProduct()�̖߂�l.�����R�[�h
            l_orderRefUnit.productCode = l_product.getProductCode();
            
            //�ٍϋ敪        �� �����P��.�ٍϋ敪
            l_orderRefUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();
            
            //�l�i����        �� �����P��.�l�i����
            l_orderRefUnit.priceCondType = l_orderUnitRow.getPriceConditionType();
            
            //1.2.19 add(arg0 : Object)
            l_lisOrderUnits.add(l_orderRefUnit);
        }
        
        //1.3 toArray( )
        WEB3AdminToEquityOrderRefUnit[] l_refUnits = new WEB3AdminToEquityOrderRefUnit[l_lisOrderUnits.size()];
        l_lisOrderUnits.toArray(l_refUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_refUnits;
    }
    
    /**
     * (get�戵�\����������ʈꗗ�iPR�w))<BR>
     * �Y����ЁE�s��R�[�h�E�����^�C�v�Ŏ戵�\�ȏ����������擾���A<BR> 
     * PR�w�Ŏg�p�������������ʂ̈ꗗ��ԋp����B<BR>
     * �戵�\�ȏ������������݂��Ȃ��ꍇ��null��ԋp����B<BR>   
     * <BR>
     * �P�j�@@ArrayList���쐬����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�s��R�[�h�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�戵�\���������擾 <BR>   
     * <BR>
     * �@@�@@�Y�����i�̎戵�\���������I�u�W�F�N�g���擾����B<BR>     
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�����^�C�v�F�@@ProductTypeEnum.����<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪�F�@@"DEFAULT"<BR>
     * �@@�@@�@@�M�p����敪�F�@@"DEFUALT"<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�Y���̃p�����[�^.�s��R�[�h<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�戵�\��������Row�̎擾<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�戵��������Row.�i���������j�t�w�l��"�戵�\"�@@���� <BR>
     * �@@�@@�@@�@@�@@�@@ArrayList��"�t�w�l"���Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@"�t�w�l"��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�戵��������Row.�i���������jW�w�l��"�戵�\"�@@���� <BR>
     * �@@�@@�@@�@@�@@�@@ArrayList��"W�w�l"���Ȃ��ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�@@"W�w�l"��ArrayList�ɒǉ�����B<BR> 
     * <BR>
     * �@@�Q�|�T�j�@@�戵��������Row.�A��������"�戵�\"�@@���� <BR>
     * �@@�@@�@@�@@�@@�@@ArrayList��"�A������"���Ȃ��ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�@@"�A������"��ArrayList�ɒǉ�����B<BR> 
     * <BR>
     * �R�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR> 
     * �@@�@@�@@��toArray()�̖߂�l.length��0�̏ꍇ�Anull��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strMarketCodes - (�s��R�[�h�z��)<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    protected String[] getValidTriggerOrderTypeListByPr(
        String l_strInstitutionCode, 
        String[] l_strMarketCodes, 
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getValidTriggerOrderTypeListByPr(String, String[], ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strMarketCodes == null)
        {
            log.debug("�s��R�[�h�z�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�s��R�[�h�z�� = null�B");
        }
        
        //�P�j�@@ArrayList���쐬����B
        List l_lisValidTriggerOrderTypes = new ArrayList();
        
        //�Q�j�p�����[�^.�s��R�[�h�̗v�f�����ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_strMarketCodes.length; i++)
        {
            //�Q�|�P�j�@@�戵�\���������擾 <BR>   
            //�Y�����i�̎戵�\���������I�u�W�F�N�g���擾����B
            // �@@�@@[����] 
            // �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h
            // �@@�@@�@@�����^�C�v�F�@@ProductTypeEnum.����
            // �@@�@@�@@�敨�^�I�v�V�����敪�F�@@"DEFAULT"
            // �@@�@@�@@�M�p����敪�F�@@"DEFUALT"
            // �@@�@@�@@�s��R�[�h�F�@@�Y���̃p�����[�^.�s��R�[�h
            WEB3GentradeHandlingOrderCond l_orderCond = new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode, 
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCodes[i]);
            
            //�Q�|�Q�j�@@�戵�\��������Row�̎擾
            //�Q�|�P�j�Ŏ擾�����戵��������.getDataSourceObject()���R�[������B
            EnableOrderConditionRow l_conditionRow = (EnableOrderConditionRow)l_orderCond.getDataSourceObject();
            //�Q�|�R�j�@@�戵��������Row.�i���������j�t�w�l��"�戵�\"�@@���� 
            //ArrayList��"�t�w�l"���Ȃ��ꍇ�A
            //"�t�w�l"��ArrayList�ɒǉ�����B
            if (WEB3DealtDef.CAN_DEALT.equals(l_conditionRow.getStopOrder())
                && !l_lisValidTriggerOrderTypes.contains(WEB3TriggerOrderTypeDef.STOP))
            {
                l_lisValidTriggerOrderTypes.add(WEB3TriggerOrderTypeDef.STOP);
            }
            //�Q�|�S�j�@@�戵��������Row.�i���������jW�w�l��"�戵�\"�@@���� 
            //ArrayList��"W�w�l"���Ȃ��ꍇ�A
            //"W�w�l"��ArrayList�ɒǉ�����B
            if (WEB3DealtDef.CAN_DEALT.equals(l_conditionRow.getWLimitOrder())
                && !l_lisValidTriggerOrderTypes.contains(WEB3TriggerOrderTypeDef.W_LlIMIT))
            {
                l_lisValidTriggerOrderTypes.add(WEB3TriggerOrderTypeDef.W_LlIMIT);
            }
            
            //�Q�|�T�j�@@�戵��������Row.�A��������"�戵�\"�@@���� 
            //ArrayList��"�A������"���Ȃ��ꍇ�A
            //"�A������"��ArrayList�ɒǉ�����B
            if (WEB3DealtDef.CAN_DEALT.equals(l_conditionRow.getChainOrder())
                && !l_lisValidTriggerOrderTypes.contains(WEB3TriggerOrderTypeDef.SUCC))
            {
                l_lisValidTriggerOrderTypes.add(WEB3TriggerOrderTypeDef.SUCC);
            }                        
            
        }
        
        //�R�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B
        //��toArray()�̖߂�l.length��0�̏ꍇ�Anull��ԋp����B
        String[] l_strValidTriggerOrderTypes = new String[l_lisValidTriggerOrderTypes.size()];
        l_lisValidTriggerOrderTypes.toArray(l_strValidTriggerOrderTypes);
        
        if (l_strValidTriggerOrderTypes.length ==0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strValidTriggerOrderTypes;
        }

    }
    

    /**
     * (AdmintoEqtypeOrderUnitRow ���@@eqtypeOrderUnitParams �ɓ]������)<BR>
     * AdmintoEqtypeOrderUnitRow ��  eqtypeOrderUnitParams �ɓ]������<BR>   
     * <BR>
     * @@param p_row - (AdmintoEqtypeOrderUnitRow)<BR>
     * @@return EqtypeOrderUnitParams
     */
    
    private EqtypeOrderUnitParams toEqtypeOrderUnitParams(AdmintoEqtypeOrderUnitRow p_row)
    {
        EqtypeOrderUnitParams l_params = new EqtypeOrderUnitParams();
        l_params.setOrderUnitId(p_row.getOrderUnitId());
        l_params.setAccountId(p_row.getAccountId());
        l_params.setSubAccountId(p_row.getSubAccountId());
        l_params.setBranchId(p_row.getBranchId());
        if (!p_row.getTraderIdIsNull())
        {
            l_params.setTraderId(p_row.getTraderId());
        }
        l_params.setOrderId(p_row.getOrderId());
        l_params.setOrderType(p_row.getOrderType());
        l_params.setOrderCateg(p_row.getOrderCateg());
        l_params.setLastOrderActionSerialNo(p_row.getLastOrderActionSerialNo());
        l_params.setLastExecutionSerialNo(p_row.getLastExecutionSerialNo());
        l_params.setProductType(p_row.getProductType());
        if (!p_row.getMarketIdIsNull())
        {
            l_params.setMarketId(p_row.getMarketId());
        }
        l_params.setQuantity(p_row.getQuantity());
        
        if (!p_row.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(new Double(p_row.getLimitPrice()));
        }
        l_params.setExecutionConditionType(p_row.getExecutionConditionType());
        l_params.setPriceConditionType(p_row.getPriceConditionType());
        l_params.setOrderConditionType(p_row.getOrderConditionType());
        l_params.setOrderCondOperator(p_row.getOrderCondOperator());
        if (!p_row.getStopOrderPriceIsNull())
        {
            l_params.setStopOrderPrice(new Double(p_row.getStopOrderPrice()));
        }
        if (!p_row.getWLimitPriceIsNull())
        {
            l_params.setWLimitPrice(new Double(p_row.getWLimitPrice()));
        }
        l_params.setDeliveryDate(p_row.getDeliveryDate());
        l_params.setExpirationDate(p_row.getExpirationDate());
        if (!p_row.getConfirmedQuantityIsNull())
        {
            l_params.setConfirmedQuantity(new Double(p_row.getConfirmedQuantity()));
        }
        if (!p_row.getConfirmedPriceIsNull())
        {
            l_params.setConfirmedPrice(new Double(p_row.getConfirmedPrice()));
        }
        if (!p_row.getExecutedQuantityIsNull())
        {
            l_params.setExecutedQuantity(new Double(p_row.getExecutedQuantity()));
        }
        if (!p_row.getExecutedAmountIsNull())
        {
            l_params.setExecutedAmount(new Double(p_row.getExecutedAmount()));
        }
        l_params.setOrderStatus(p_row.getOrderStatus());
        l_params.setOrderOpenStatus(p_row.getOrderOpenStatus());
        l_params.setExpirationStatus(p_row.getExpirationStatus());
        l_params.setTaxType(p_row.getTaxType());
        l_params.setSwapTaxType(p_row.getSwapTaxType());
        l_params.setRepaymentType(p_row.getRepaymentType());
        if (!p_row.getRepaymentNumIsNull())
        {
            l_params.setRepaymentNum(new Integer(p_row.getRepaymentNum()));
        }
        l_params.setSonarRepaymentType(p_row.getSonarRepaymentType());
        l_params.setBizDate(p_row.getBizDate());
        l_params.setProductId(p_row.getProductId());
        l_params.setQuantityType(p_row.getQuantityType());
        l_params.setOrderChanel(p_row.getOrderChanel());
        l_params.setReceivedDateTime(p_row.getReceivedDateTime());
        l_params.setVoucherNo(p_row.getVoucherNo());
        l_params.setCommTblNo(p_row.getCommTblNo());
        l_params.setCommTblSubNo(p_row.getCommTblSubNo());
        l_params.setSonarTraderCode(p_row.getSonarTraderCode());
        if (!p_row.getPriceIsNull())
        {
            l_params.setPrice(new Double(p_row.getPrice()));
        }
        l_params.setOrderRequestNumber(p_row.getOrderRequestNumber());
        if (!p_row.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(new Double(p_row.getEstimatedPrice()));
        }
        if (!p_row.getCapitalGainIsNull())
        {
            l_params.setCapitalGain(new Double(p_row.getCapitalGain()));
        }
        if (!p_row.getCapitalGainTaxIsNull())
        {
            l_params.setCapitalGainTax(new Double(p_row.getCapitalGainTax()));
        }
        l_params.setSonarTradedCode(p_row.getSonarTradedCode());
        l_params.setSonarMarketCode(p_row.getSonarMarketCode());
        l_params.setCommProductCode(p_row.getCommProductCode());
        l_params.setShortSellOrderFlag(p_row.getShortSellOrderFlag());
        l_params.setModifyCancelType(p_row.getModifyCancelType());
        l_params.setOrderRootDiv(p_row.getOrderRootDiv());
        l_params.setSubmitOrderRouteDiv(p_row.getSubmitOrderRouteDiv());
        if (!p_row.getConfirmedOrderPriceIsNull())
        {
            l_params.setConfirmedOrderPrice(new Double(p_row.getConfirmedOrderPrice()));
        }
        if (!p_row.getConfirmedEstimatedPriceIsNull())
        {
            l_params.setConfirmedEstimatedPrice(new Double(p_row.getConfirmedEstimatedPrice()));
        }
        l_params.setConfirmedExecConditionType(p_row.getConfirmedExecConditionType());
        l_params.setConfirmedPriceConditionType(p_row.getConfirmedPriceConditionType());
        l_params.setClosingOrderType(p_row.getClosingOrderType());
        l_params.setErrorReasonCode(p_row.getErrorReasonCode());
        l_params.setRequestType(p_row.getRequestType());
        if (!p_row.getFirstOrderUnitIdIsNull())
        {
            l_params.setFirstOrderUnitId(new Long(p_row.getFirstOrderUnitId()));
        }
        l_params.setCreatedTimestamp(p_row.getCreatedTimestamp());
        l_params.setLastUpdatedTimestamp(p_row.getLastUpdatedTimestamp());
        l_params.setConfirmedOrderRev(p_row.getConfirmedOrderRev());
        l_params.setOrderRev(p_row.getOrderRev());
        l_params.setReserveOrderExistFlag(p_row.getReserveOrderExistFlag());
        if (!p_row.getOriginalQuantityIsNull())
        {
            l_params.setOriginalQuantity(new Double(p_row.getOriginalQuantity()));
        }
        l_params.setStopOrderOrderedTimestamp(p_row.getStopOrderOrderedTimestamp());
        l_params.setOrgOrderConditionType(p_row.getOrgOrderConditionType());
        l_params.setOrgOrderCondOperator(p_row.getOrgOrderCondOperator());
        if (!p_row.getOrgStopOrderPriceIsNull())
        {
            l_params.setOrgStopOrderPrice(new Double(p_row.getOrgStopOrderPrice()));
        }
        if (!p_row.getOrgWLimitPriceIsNull())
        {
            l_params.setOrgWLimitPrice(new Double(p_row.getOrgWLimitPrice()));
        }  
        l_params.setOrgWLimitExecCondType(p_row.getOrgWLimitExecCondType());
        l_params.setWLimitExecCondType(p_row.getWLimitExecCondType());
        if (!p_row.getWLimitBeforeLimitPriceIsNull())
        {
            l_params.setWLimitBeforeLimitPrice(new Double(p_row.getWLimitBeforeLimitPrice()));
        }
        l_params.setWLimitBeforeExecCondType(p_row.getWLimitBeforeExecCondType());
        l_params.setSubmitOrderDelayFlag(p_row.getSubmitOrderDelayFlag());
        return l_params;
    }
    


}
@
