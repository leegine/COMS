head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�������Ɖ�T�[�r�XImpl(WEB3FuturesExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ���Q (���u) �V�K�쐬
                 : 2006/08/14 �s�p (���u) ���f��No.498
                 : 2006/11/14 �юu�� (���u) ���f��No.570
Revesion History : 2007/06/21 �����F(���u) ���f��673�A674�A675�A676�A735�A736
Revesion History : 2007/10/16 �����F(���u) ���f��789 790
Revesion History : 2008/08/18 ���� (���u) IFO�����_�Ή�
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContract;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;

import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoFinTransactionManagerImpl;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoReferenceTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoTradeTypeDef;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3FuturesExecuteUnit;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractGroup;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListResponse;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3FuturesExecuteGroup;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesChangeCancelHistoryGroup;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryRequest;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesExecuteReferenceService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨�������Ɖ�T�[�r�XImpl)<BR>
 * �����w���敨�������Ɖ�T�[�r�X�����N���X
 */
public class WEB3FuturesExecuteReferenceServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesExecuteReferenceService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesExecuteReferenceServiceImpl.class);

    /**
     * FinApp
     */
    private FinApp finApp = (FinApp)Services.getService(FinApp.class);

    /**
     * TradingModule
     */
    private TradingModule tradingModule =
        finApp.getTradingModule(ProductTypeEnum.IFO);

    /**
     * OrderManager
     */
    private WEB3FuturesOrderManagerImpl futOrderManager =
        (WEB3FuturesOrderManagerImpl)tradingModule.getOrderManager();
    /**
     * @@roseuid 40F7A2C6031C
     */
    public WEB3FuturesExecuteReferenceServiceImpl()
    {

    }

    /**
     * �����w���敨�������Ɖ�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Aget�������Ɖ�()�A<BR>
     * get�������ڍ�()�Aget���������Ɖ�()�Aget�ԍό��ʈꗗ()<BR>
     * �̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A9A5A803B2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest)";

        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

         //�u�����w���敨�������Ɖ�N�G�X�g�v�̏ꍇ�Aget�������Ɖ�()
        if (l_request instanceof WEB3FuturesExecuteReferenceRequest)
        {
            l_response = getOrderExecuteReference(
                (WEB3FuturesExecuteReferenceRequest)l_request);
        }

        //�u�����w���敨�����������ڍ׃��N�G�X�g�v�̏ꍇ�Aget�������ڍ�()
        else if (l_request instanceof WEB3FuturesExecuteDetailsRequest)
        {
            l_response = getOrderExecuteDetail(
                (WEB3FuturesExecuteDetailsRequest)l_request);
        }

        //�u�����w���敨���������Ɖ�N�G�X�g�v�̏ꍇ�Aget���������Ɖ�()
        else if (l_request instanceof WEB3FuturesOrderHistoryRequest)
        {
            l_response = getOrderHistoryInquiry(
                (WEB3FuturesOrderHistoryRequest)l_request);
        }

        //�u�����w���敨�ԍό��ʈꗗ���N�G�X�g�v�̏ꍇ�Aget�ԍό��ʈꗗ()
        else if (l_request instanceof WEB3FuturesCloseMarginContractListRequest)
        {
            l_response = getSettleContractList(
                (WEB3FuturesCloseMarginContractListRequest)l_request);
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�������Ɖ�)<BR>
     * �����w���敨�������Ɖ�A�����w���敨��������ꗗ�̏������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�������Ɖ�T�[�r�X�jget�������Ɖ�v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �V�[�P���X�}(�u�i�敨�������Ɖ�T�[�r�X�jget�������Ɖ�v): 
     * 6 is����\�ڋq(�⏕���� : �⏕����)<BR>
     * ���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"<BR>
     * ���Ais����\�ڋq��false�̏ꍇ�A<BR>
     * ��O��throw����B<BR>
     *�@@class: WEB3BusinessLayerException<BR>
�@@   *  tag:   BUSINESS_ERROR_00309<BR>
     * ==============================<BR>
     * �V�[�P���X�}(�u�i�敨�������Ɖ�T�[�r�X�jget�������Ɖ�v):<BR>
     *  9 get����(Institution, String, String, String, double, String, String)<BR>
     * (�����R�[�h�`�F�b�N)<BR>
     * get�����Ŏw��̖����R�[�h���擾�ł��Ȃ������ꍇ�́A<BR>
     * �u�����R�[�h�`�F�b�N�G���[�v�̗�O��throw����B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00301<BR>
     * ==============================<BR>
     * @@param l_request - �����w���敨�������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FuturesExecuteReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A9A5A803D1
     */
    protected WEB3FuturesExecuteReferenceResponse getOrderExecuteReference(WEB3FuturesExecuteReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderExecuteReference(WEB3FuturesExecuteReferenceRequest)";

        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();

        //get�⏕����()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        Institution l_institution = l_subAccount.getInstitution();
        WEB3IfoProductManagerImpl l_productManager =
            (WEB3IfoProductManagerImpl) tradingModule.getProductManager();
        WEB3IfoProductImpl l_ifoProduct = null;

        //���N�G�X�g�f�[�^�ɖ����R�[�h���Z�b�g����Ă���ꍇ
        if (l_request.futProductCode != null
            && l_request.futProductCode.length() != 0)
        {
			//(�����R�[�h�`�F�b�N)
			//get�����Ŏw��̖����R�[�h���擾�ł��Ȃ������ꍇ�́A
			//�u�����R�[�h�`�F�b�N�G���[�v�̗�O��throw����B
			try
			{
				l_ifoProduct = l_productManager.getIfoProduct(
						l_institution,
						l_request.futProductCode);

				if (l_ifoProduct == null)
				{
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00301,
						getClass().getName() + "." + STR_METHOD_NAME);
				}
			}
			catch (NotFoundException l_ex)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00301,
					getClass().getName() + "." + STR_METHOD_NAME);
			}
			catch (WEB3BaseException l_ex)
			{
				throw new WEB3BusinessLayerException(
					l_ex.getErrorInfo(),
					getClass().getName() + "." + STR_METHOD_NAME);
			}

            //�����Y�����R�[�h���擾
            String l_strUnderlyingProductCode = l_ifoProduct.getUnderlyingProductCode();			
            //reset�����R�[�h
            WEB3GentradeTradingTimeManagement.resetProductCode(l_strUnderlyingProductCode);

        }

        //���N�G�X�g�f�[�^�ɖ������荀��(����s��A�w����ʁA����)���ݒ肳��Ă���ꍇ
        if (l_request.marketCode != null &&
            l_request.targetProductCode != null &&
            l_request.delivaryMonth != null)
        {
            //�敨�I�v�V�������i�敪�F�敨
            IfoDerivativeTypeEnum l_derivativeType = null;
            l_derivativeType = IfoDerivativeTypeEnum.FUTURES;
            try
            {
                //get����
                l_ifoProduct = l_productManager.getIfoProduct(
                                    l_institution,
                                    l_request.targetProductCode,
                                    l_request.delivaryMonth,
                                    l_derivativeType,
                                    0,
                                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                                    l_request.targetProductCode);

                if (l_ifoProduct == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            catch (WEB3BaseException l_ex)
            {
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(),
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            String l_strUnderlyingProductCode = l_ifoProduct.getUnderlyingProductCode();            
            //reset�����R�[�h
            WEB3GentradeTradingTimeManagement.resetProductCode(l_strUnderlyingProductCode);
            
        }
        
		//is����\�ڋq(�⏕����)
		//���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
		//���Ais����\�ڋq��false�̏ꍇ�A��O��throw����
		boolean l_blnIsTradedPossibleAccount =
			futOrderManager.isTradedPossibleCustomer(l_subAccount);

		if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType) &&
			l_blnIsTradedPossibleAccount == false)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00275,
				getClass().getName() + "." + STR_METHOD_NAME);
		}

        //validate������t�\()
        //���N�G�X�g�f�[�^.�Ɖ�敪="�������Ɖ�"�̏ꍇ�̂݁A
        //��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N�B
        if (WEB3IfoReferenceTypeDef.ORDER_PROMISE.equals(l_request.referenceType))
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();            
        }

        //get����X()
        Branch l_branch = l_subAccount.getMainAccount().getBranch();
        //������ԊǗ�.get�s��ǌx���s��()
        String[] l_strTradeCloseSuspensions =
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                (WEB3GentradeBranch)l_branch,
                WEB3FuturesOptionDivDef.FUTURES
                );

        //���N�G�X�g�f�[�^.�Ɖ�敪= "��������ꗗ"�̏ꍇ�̂݁A���������{����B
        if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType))
        {
            //reset��t���ԋ敪(��t���ԋ敪�F"12�F�����w���敨OP�i��������j)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL
                );

            boolean l_blnChangeAcceptFail = false;
            boolean l_blnCancelAcceptFail = false;            
            //�G���[�t���O
            boolean l_blnCancelErrBATCH = false;
            boolean l_blnCancelErrSCRAM = false;
            boolean l_blnCancelErrTRADINGTIME = false;
            
            //reset������t�g�����U�N�V����(������t�g�����U�N�V���� : "05�F����")
            log.debug("reset������t�g�����U�N�V����(05:����)");
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CHANGE
                );

            //validate������t�\()
            //������t�g�����U�N�V����="05�F����"
            //��t���ԋ敪="�����w���敨OP�i��������j"��
            //������t�\�`�F�b�N�i��t���ԃ`�F�b�N�A
            //�V�X�e��������~�`�F�b�N�j���s���B
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("�����F������t�s��", l_ex);
                l_blnChangeAcceptFail = true;
            }

            //reset������t�g�����U�N�V����(������t�g�����U�N�V���� : "06:���")
            log.debug("reset������t�g�����U�N�V����(06:���)");
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CANCEL
                );

            //validate������t�\()
            //������t�g�����U�N�V����="06�F���"
            //��t���ԋ敪="�����w���敨OP�i��������j"��
            //������t�\�`�F�b�N�i��t���ԃ`�F�b�N�A
            //�V�X�e��������~�`�F�b�N�j���s���B
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("����F������t�s��", l_ex);
                l_blnCancelAcceptFail = true;
                if (l_ex.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
                {
                    l_blnCancelErrBATCH = true;
                }
                else if (l_ex.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
                {
                	l_blnCancelErrSCRAM = true;
                }
                else if (l_ex.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00013)
                {
                    l_blnCancelErrTRADINGTIME = true;
                }
                else
                {
					throw new WEB3SystemLayerException(
					    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
					    this.getClass().getName() + STR_METHOD_NAME,
					    l_ex.getMessage(),
					    l_ex);
                }
            }

            //�����A����Ƃ��Ɏ�t�s�̏ꍇ�A��O��throw����B
            if (l_blnChangeAcceptFail && l_blnCancelAcceptFail)
            {
                if (l_blnCancelErrBATCH)
                {
                    //�o�b�`������
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����Ǝ�������Ɏ�t�s��");
                }
            	else if (l_blnCancelErrSCRAM)
            	{
					//�ً}��~��
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00012,
					    this.getClass().getName() + "." + STR_METHOD_NAME,
					    "�����Ǝ�������Ɏ�t�s��");
            	}
            	else
            	{
            		//��t�\���ԊO
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00013,
					    this.getClass().getName() + "." + STR_METHOD_NAME,
					    "�����Ǝ�������Ɏ�t�s��");
            	}
            }
        }
        
        //�������ꗗ�̍쐬
        TreeMap l_orderBizDateMap = new TreeMap();
        Date l_datBizdate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsBizdate = new Timestamp(l_datBizdate.getTime());
        
        //�Ɩ����t���Z�b�g
        Date l_datSysdate = WEB3DateUtility.toDay(l_tsBizdate);
        l_orderBizDateMap.put(l_datSysdate, l_datSysdate);
        
        //�Ɩ����t�̑O�c�Ɠ����Z�b�g
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBizdate);
        Date l_datBizDate = WEB3DateUtility.toDay(l_genBizDate.roll(-1));
        l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
        
        //(���X�w����)�戵�����I�u�W�F�N�g���擾����
        String l_instCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_branchCode = l_branch.getBranchCode();
        
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtCond =
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
            l_instCode, l_branchCode, WEB3FuturesOptionDivDef.FUTURES);
            
        //�擾����(���X�w����)�戵�����̗v�f�����ALOOP����
        int l_intIndexSize = 0;
        if (l_branchIndexDealtCond != null)
        {
            l_intIndexSize = l_branchIndexDealtCond.length;
        }
        for (int i = 0; i < l_intIndexSize; i++)
        {
            //reset�����R�[�h
            WEB3GentradeTradingTimeManagement.resetProductCode(l_branchIndexDealtCond[i].getUnderlyingProductCode());
            //���������擾
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //�擾�������������������ꗗ�Ɋ܂܂�Ă��Ȃ����t�̏ꍇ�A�������ꗗ�ɒǉ�
            if (!l_orderBizDateMap.containsKey(l_datBizDate))
            {                                      
                l_orderBizDateMap.put(l_datBizDate, l_datBizDate);
            }
        }
        
        //�擾�����������ꗗ���Z�b�g
        int l_intListSize = l_orderBizDateMap.size();
        Date[] l_bizDateList = new Date[l_intListSize];
        Collection l_collection = l_orderBizDateMap.values();
        l_collection.toArray(l_bizDateList); 
        
        //createResponse()
        WEB3FuturesExecuteReferenceResponse l_response = null;
        l_response = (WEB3FuturesExecuteReferenceResponse)
            l_request.createResponse();

        //create�����R�[�h����
        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits;
        l_productCodeNameUnits = createProductCodeName(
            l_subAccount,
            l_request);

        //create�����R�[�h���̂̕Ԃ�l��NULL�̏ꍇ�A
        //���X�|���X�쐬�A�v���p�e�B�Z�b�g�������s���B
        //�i�������Ɖ�쐬�A�y�[�W���O�����͍s��Ȃ��j
        if (l_productCodeNameUnits == null)
        {
            log.debug("create�����R�[�h���̂̕Ԃ�l��NULL");
            //���X�|���X.�������Ɖ���P�� = NULL
            l_response.futExecuteGroups = null;
            //���X�|���X.�������ꗗ
            l_response.orderBizDateList = l_bizDateList;
            //���X�|���X.�����R�[�h�� = 0
            l_response.totalRecords = "0";
            //���X�|���X.���y�[�W�� = 0
            l_response.totalPages = "0";
            //���X�|���X.�\���y�[�W�ԍ� = 0
            l_response.pageIndex = "0";
            //���X�|���X.ID�ꗗ = NULL
            l_response.idList = null;
            //���X�|���X.����I���x������ = get�s��ǌx���w���̕Ԃ�l
            l_response.messageSuspension = l_strTradeCloseSuspensions;
            //���X�|���X.�����w���敨�I�v�V���������R�[�h���� = NULL
            l_response.futOpProductCodeNames = null;
            //���X�|���X.�ڋq���b�N�敪 =  is����\�ڋq�̕Ԃ�l��false�Ȃ�true
            //�����b�N�ڋq�̏ꍇ�Ftrue�A���b�N�ڋq�łȂ��ꍇ�Ffalse�ƂȂ�
            if (l_blnIsTradedPossibleAccount == false)
            {
                l_response.accountLock = true;
            }
            else
            {
                l_response.accountLock = false;
            }

            log.exiting(STR_METHOD_NAME);

            return l_response;
        }

        //create�������Ɖ�
        WEB3FuturesExecuteGroup[] l_executeGroups;
        l_executeGroups = createOrderExecutedInquiry(
            l_subAccount,
            l_request,
            l_response,
            l_ifoProduct);
            
        //create�������Ɖ�̕Ԃ�l��NULL�̏ꍇ�A
        //���X�|���X�쐬�A�v���p�e�B�Z�b�g�������s���B
        //(�y�[�W���O�����͍s��Ȃ�)
        if (l_executeGroups == null)
        {
            log.debug("create�������Ɖ�̕Ԃ�l��NULL");
            //���X�|���X.�������Ɖ���P�� = NULL
            l_response.futExecuteGroups = null;
            //���X�|���X.�������ꗗ
            l_response.orderBizDateList = l_bizDateList;
            //���X�|���X.�����R�[�h�� = 0
            l_response.totalRecords = "0";
            //���X�|���X.���y�[�W�� = 0
            l_response.totalPages = "0";
            //���X�|���X.�\���y�[�W�ԍ� = 0
            l_response.pageIndex = "0";
            //���X�|���X.ID�ꗗ = NULL
            l_response.idList = null;
            //���X�|���X.����I���x������ = get�s��ǌx���w���̕Ԃ�l
            l_response.messageSuspension = l_strTradeCloseSuspensions;
            //���X�|���X.�����w���敨�I�v�V���������R�[�h����
            l_response.futOpProductCodeNames = l_productCodeNameUnits;
            //���X�|���X.�ڋq���b�N�敪 =  is����\�ڋq�̕Ԃ�l��false�Ȃ�true
            //�����b�N�ڋq�̏ꍇ�Ftrue�A���b�N�ڋq�łȂ��ꍇ�Ffalse�ƂȂ�
            if (l_blnIsTradedPossibleAccount == false)
            {
                l_response.accountLock = true;
            }
            else
            {
                l_response.accountLock = false;
            }

            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        log.debug("total record count: " + l_executeGroups.length);
        log.debug("****** ���X�|���X�D�������ꗗ�̗v�f���F[" + l_response.futExecuteGroups.length + "]");
        log.debug("****** ���X�|���X�DID�ꗗ�̗v�f���F[" + l_response.idList.length + "]");

        //���X�|���X.����I���x������ = get�s��ǌx���w���̕Ԃ�l
        l_response.messageSuspension = l_strTradeCloseSuspensions;

        //���X�|���X.�����w���敨�I�v�V���������R�[�h���� =
        //create�����R�[�h���̂̕Ԃ�l
        l_response.futOpProductCodeNames = l_productCodeNameUnits;

        //���X�|���X.�ڋq���b�N�敪 = is����\�ڋq�̕Ԃ�l��false�Ȃ�true
        //  �����b�N�ڋq�̏ꍇ�Ftrue�A���b�N�ڋq�łȂ��ꍇ�Ffalse�ƂȂ�
        if (l_blnIsTradedPossibleAccount == false)
        {
            l_response.accountLock = true;
        }
        else
        {
            l_response.accountLock = false;
        }
        
        //���X�|���X.�������ꗗ
        l_response.orderBizDateList = l_bizDateList;
        
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�������ڍ�)<BR>
     * �w�肳�ꂽ����ID���������P�ʃI�u�W�F�N�g�̓��e����ʕ\���p�ɕҏW���A<BR>
     * ���X�|���X�ɐݒ肵�ĕԂ��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�������Ɖ�T�[�r�X�jget�������ڍׁv�Q�ƁB<BR>
     * @@param l_request - �����w���敨�����������ڍ׃��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FuturesExecuteDetailsResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A9A5A803E1
     */
    protected WEB3FuturesExecuteDetailsResponse getOrderExecuteDetail(WEB3FuturesExecuteDetailsRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderExecutedDetail(WEB3FuturesExecuteDetailsRequest)";

        log.entering(STR_METHOD_NAME);

        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //1.2 �⏕�������擾����B
        SubAccount l_subAccount = getSubAccount();

        //1.3 ��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N�B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4 ����ID�ɑΉ����钍���P�ʃI�u�W�F�N�g���擾����B
        OrderUnit[] l_orderUnits =
            futOrderManager.getOrderUnits(Long.parseLong(l_request.id));
        if (l_orderUnits.length == 0)
        {
        	throw new WEB3BusinessLayerException(
        	    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
        	    this.getClass().getName() + "." + STR_METHOD_NAME,
        	    "�w�肵������ID�ɊY������f�[�^�����݂��܂���B");
        }
        IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderUnits[0];
        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        //�������擾
        WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_orderUnit.getProduct();

        //1.5 creset�����R�[�h(�����R�[�h)
        WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());

        //1.6 �����P�ʂ̒�����ԋ敪���擾����B
        String l_strOrderStatus = WEB3IfoDataAdapter.getOrderStatusType(l_orderUnit);

        //1.7 �����P�ʂ̖���ԋ敪���擾����B
        String l_strExecType = WEB3IfoDataAdapter.getExecStatusType(l_orderUnit);

        //1.8 PR�w�p�̎��s�����̃R�[�h���擾����B
        String l_strPRExcCondType = WEB3IfoDataAdapter.getExecutionCondByPr(l_orderUnit.getExecutionConditionType());
        
        //1.9 get�����󋵋敪(�����P��)
        String l_strTransStatusType = WEB3IfoDataAdapter.getTransactionStatusType(l_orderUnit);
        
        //1.10 get�v�w�l�p�L����ԋ敪(IfoOrderUnit)                
        String l_strWLimitEnableStatusDiv =
            WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
        
        //1.11 get�v�w�l�p�֑ؑO�����P��(IfoOrderUnit)
        String l_strWLimitBefSwitchPrice =
            WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);
        
        //1.12 get�v�w�l�p�֑ؑO���s����(IfoOrderUnit)
        String l_strWLimitBefSwitchExecCondType =
            WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);
        
        //1.13 is�蓮�����\(IfoOrderUnit)
        boolean l_blnIsManualOrderPossible = 
            futOrderManager.isManualOrderPossible(l_orderUnit);
        
        //1.14 get�x���敪(IfoOrderUnit)
        String l_strDelayDiv = WEB3IfoDataAdapter.getDelayDiv(l_orderUnit);        

        //1.16 �����P�ʂɂЂ��Â����̈ꗗ���擾����B
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();

        //1.17 �����P�ʂɂЂ��Â��g�����U�N�V�����̈ꗗ���擾����B
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager =
            (WEB3IfoFinTransactionManagerImpl)tradingModule.getFinTransactionManager();
        List l_lisTransactions = l_finTransactionManager.getTransactions(l_orderUnit);
        IfoFinTransactionRow l_finTransactionRow = null;
        
        WEB3IfoTradedProductImpl l_tradedProduct = (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct();

        double l_dblUnitSize = l_tradedProduct.getUnitSize(); //�w���搔
        double l_dblDeliveryPrice = 0.0;    //���ϑ��v
        double l_dblCommission = 0.0;       //�萔��
        double l_dblConsumptionTax = 0.0;   //�����

        //1.18 �����(���ϑ��v)���擾����B
        l_dblDeliveryPrice = futOrderManager.getNetAmount(l_orderUnit);

        BigDecimal l_bdCommission = new BigDecimal(l_dblCommission + "");
        BigDecimal l_bdConsumptionTax = new BigDecimal(l_dblConsumptionTax + "");

        int l_intListLength = l_lisTransactions.size();
        for (int i = 0; i < l_intListLength; i++)
        {
            l_finTransactionRow = (IfoFinTransactionRow)l_lisTransactions.get(i);
            l_bdCommission = l_bdCommission.add(new BigDecimal(l_finTransactionRow.getCommissionFee() + ""));
            l_bdConsumptionTax = l_bdConsumptionTax.add(new BigDecimal(l_finTransactionRow.getCommissionFeeTax() + ""));
        }

        //1.20 ���X�|���X�f�[�^�𐶐�����B
        WEB3FuturesExecuteDetailsResponse l_response = null;
        l_response = (WEB3FuturesExecuteDetailsResponse)
            l_request.createResponse();

        //1.21 ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //���X�|���X.������ = �敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID).������
        l_response.futProductName = ((IfoProductRow)l_ifoProduct.getDataSourceObject()).getStandardName();
        //���X�|���X.�w����� = �敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID).�����Y�����R�[�h
        l_response.targetProductCode = l_ifoProduct.getUnderlyingProductCode();
        //���X�|���X.���� = �敨OP�v���_�N�g�}�l�[�W��.getProduct(�����P��.����ID).����
        l_response.delivaryMonth = l_ifoProduct.getMonthOfDelivery();
        //������
        l_response.orderBizDate = WEB3DateUtility.toDay(WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(),"yyyyMMdd"));

        WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //���X�R�[�h
        l_response.branchCode = l_account.getBranch().getBranchCode();
        //�ڋq�R�[�h(�\���p)
        l_response.accountCode = l_account.getDisplayAccountCode();
        //�ڋq��(�\���p)
        l_response.accountName = l_account.getDisplayAccountName();
        //��������敪
        l_response.changeCancelDiv = l_ifoOrderUnitRow.getModifyCancelType();
        //�����o�H�敪
        l_response.orderRootDiv = l_ifoOrderUnitRow.getOrderRootDiv();
        //������
        l_response.transactionStateType = l_strTransStatusType;

        //���X�|���X.����s�� = �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(�����P��.�s��ID).�s��R�[�h
        //�g�����Z�I�u�W�F�N�g�}�l�[�W��
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)finApp.getFinObjectManager();
        //�s��̎擾
        Market l_market;
        try
        {
            l_market = l_gentradeFinObjectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003, STR_METHOD_NAME,
                l_nfe.getMessage(), l_nfe); 
        }
        //���X�|���X.����s�� = �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(�����P��.�s��ID).�s��R�[�h
        l_response.marketCode = l_market.getMarketCode();
        //���X�|���X.����敪 = �����P��.�������
        l_response.tradingType = getPRLevelTradeTypeDiv(l_orderUnit.getOrderType());
        //���X�|���X.���� = null���Z�b�g
        l_response.openDate = null;
        //���X�|���X.���P�� = null���Z�b�g
        l_response.contractPrice = null;
        //���X�|���X.�������� = �����P��.��������
        l_response.futOrderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        //���X�|���X.�����P���敪
        //���X�|���X.�����P��
        if (l_orderUnit.isMarketOrder())
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_response.limitPrice = null;
        }
        else
        {
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_response.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }
        //���X�|���X.�T�Z������i�T�Z���ϑ��v�j = �����P��.�T�Z��n���
        l_response.estimatedContractPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());
        //���X�|���X.�����L������ = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l��
        //�@@�@@�@@"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�����������t���Z�b�g�B
        //�@@�@@�@@�ȊO�̏ꍇ�ANULL���Z�b�g�B
        String l_strExpirationDateType =
            WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
        {
            //���X�|���X.�����L������ = �����P��.�����������t
            l_response.expirationDate = WEB3DateUtility.toDay(l_ifoOrderUnitRow.getExpirationDate());
        }
        else
        {
            l_response.expirationDate = null;
        }
        //���X�|���X.���s���� = get���s����(PR�w)�̕Ԃ�l
        l_response.execCondType = l_strPRExcCondType;
        //���X�|���X.���������敪 = �����P��.��������
        l_response.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();
        //�����P��.����������"�t�w�l"�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_response.orderCondType))
        {
            l_response.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
            l_response.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
        }
        //�����P��.����������"W�w�l"�̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_response.orderCondType))
        {
            l_response.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
            l_response.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
            if (l_ifoOrderUnitRow.getWLimitPrice() == 0)
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                l_response.wLimitPrice = null;
            }
            else
            {
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_response.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
            }
            
            //W�w�l�p���s���� = (*9)�敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.(W�w�l)���s����)�̖߂�l
            l_response.wlimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType());
        }
        
        //W�w�l�p�L����ԋ敪 = �敨OP�f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪()�̖߂�l
        l_response.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
        
        //W�w�l�p�֑ؑO�����P�� = �敨OP�f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P��()�̖߂�l
        l_response.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
        
        //W�w�l�p�֑ؑO���s���� = �敨OP�f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s����()�̖߂�l
        l_response.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;       
                
        //�����������敪 = �����P��.����������
        l_response.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
        
        //�����������P�� = �����P��.���t�w�l��l
        if (!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice = 
                WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getOrgStopOrderPrice());
        }

        //�������������Z�q = �����P��.�������������Z�q
        l_response.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();
        
        //��W�w�l�p�����P���敪 = �敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P���敪(�����P��)
        l_response.orgWLimitOrderPriceDiv = WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);
        
        //��W�w�l�p�����P�� = ���v�w�l�p�����P���敪��"�w�l"�̏ꍇ�̂݁A
        //�敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_response.orgWLimitOrderPriceDiv))
        {
            l_response.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }
                
        //��W�w�l�p���s���� = �敨OP�f�[�^�A�_�v�^.get���v�w�l�p���s����(�����P��)
        l_response.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_orderUnit);
        
        //���X�|���X.������t�� = �����P��.�󒍓���
        l_response.orderDate = l_ifoOrderUnitRow.getReceivedDateTime();
        //���X�|���X.������ԋ敪 = get������ԋ敪�̕Ԃ�l
        l_response.orderState = l_strOrderStatus;
        //���X�|���X.�J�z�G���[�R�[�h = get������ԋ敪�̕Ԃ�l��"�J�z���s"�̏ꍇ�̂݁A�����P��.�����G���[���R�R�[�h���Z�b�g
        if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_strOrderStatus))
        {
            l_response.transferErrCode = l_ifoOrderUnitRow.getErrorReasonCode();
        }
        //���X�|���X.����ԋ敪 = get����ԋ敪�̕Ԃ�l
        l_response.execType = l_strExecType;

        //�x���敪 = �敨OP�f�[�^�A�_�v�^.get�x���敪()�̖߂�l
        l_response.delayDiv = l_strDelayDiv;

        //�蓮�����\�t���O = �敨�����}�l�[�W��.is�蓮�����\()�̖߂�l
        l_response.manualFlag = l_blnIsManualOrderPossible;

        //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
        l_response.eveningSessionCarryoverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnit);

        //����敪 = �����P��.����敪
        l_response.sessionType = l_ifoOrderUnitRow.getSessionType();

        //<���n���>
        //��肪����ꍇ(�����P��.isUnexecuted��false)�̂݃Z�b�g
        if (!l_orderUnit.isUnexecuted())
        {
            //���X�|���X.��n�� = �����P��.��n��
            l_response.deliveryDate = WEB3DateUtility.toDay(l_orderUnit.getDeliveryDate());
            //���X�|���X.��萔�� = �����P��.��萔��
            l_response.execQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());
            double l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount + "");
            BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity + "");
            BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize + "");
            //���X�|���X.���P�� = �����P��.���v�����z �� �����P��.��萔�� �� �w���搔(�~�����͎l�̌ܓ�)
            double l_dblExecPrice = l_bdExecutedAmount.divide(
                l_bdExecutedQuantity, 10, BigDecimal.ROUND_HALF_UP).divide(
                    l_bdUnitSize, 0, BigDecimal.ROUND_HALF_UP).doubleValue();
            l_response.execPrice = WEB3StringTypeUtility.formatNumber(l_dblExecPrice);
            //���X�|���X.�����z = �����P��.���v�����z
            l_response.execTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblExecutedAmount);
            //���X�|���X.�����(���ϑ��v)
            l_response.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryPrice);
            //���X�|���X.�萔�� = �g�����U�N�V����.getCommissionFee�̍��v�l
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_bdCommission.doubleValue());
            //���X�|���X.����� = �g�����U�N�V����.getCommissionFeeTax�̍��v�l
            l_response.consumptionTax = WEB3StringTypeUtility.formatNumber(l_bdConsumptionTax.doubleValue());
            //���X�|���X.�������ڍז��
            WEB3FuturesExecuteUnit[] l_executeDetailUnits =
                new WEB3FuturesExecuteUnit[l_orderExecutions.length];
            for (int i = 0; i < l_orderExecutions.length; i++)
            {
                l_executeDetailUnits[i] = new WEB3FuturesExecuteUnit();
                //�����w���I�v�V�����������ڍו������.�������̐ݒ�
                l_executeDetailUnits[i].executionTimestamp =
                     l_orderExecutions[i].getExecutionTimestamp();
                //�����w���I�v�V�����������ڍו������.��芔���̐ݒ�
                l_executeDetailUnits[i].execQuantity =
                     WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionQuantity());
                //�����w���I�v�V�����������ڍו������.���P���̐ݒ�
                l_executeDetailUnits[i].execPrice =
                     WEB3StringTypeUtility.formatNumber(l_orderExecutions[i].getExecutionPrice());
            }
            l_response.futExecuteUnits = l_executeDetailUnits;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���������Ɖ�)<BR>
     * �w�肳�ꂽ����ID���������P�ʃI�u�W�F�N�g�̒����������擾����<BR>
     * ��ʕ\���p�ɕҏW���A���X�|���X�ɐݒ肵�ĕԂ��B<BR>
     * �o����܂Œ����̏ꍇ�́A�������`�ŐV�̒����܂�<BR>
     * �̒���������ΏۂƂ���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�敨�������Ɖ�T�[�r�X�jget���������Ɖ�v�Q�ƁB<BR>
     * @@param l_request - �����w���敨���������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FuturesOrderHistoryResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A9A5A90009
     */
    protected WEB3FuturesOrderHistoryResponse getOrderHistoryInquiry(WEB3FuturesOrderHistoryRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderHistoryInquiry(WEB3FuturesOrderHistoryRequest)";
        log.entering(STR_METHOD_NAME);

        //1.���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N�B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //4. ����ID�ɑΉ����钍���P�ʃI�u�W�F�N�g���擾����B
        OrderUnit[] l_orderUnits = 
            futOrderManager.getOrderUnits(Long.parseLong(l_request.id));
		if (l_orderUnits.length == 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�w�肵������ID�ɊY������f�[�^�����݂��܂���B");
		}

        OrderUnit l_orderUnit = l_orderUnits[0];
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        IfoOrderUnit[] l_executedOrderUnits = null;
        if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(
            WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit)))
        {
            //�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l��"��������"�ȊO�̏ꍇ�̂݁A�ȉ��̏������s��
            //get�o����܂Œ����P��FromFirstToLast(IfoOrderUnit)
            l_executedOrderUnits = getCarriedOrderUnitFromFirstToLast(
                (IfoOrderUnit)l_orderUnit);
        }

        List l_lisOrderHistory = new ArrayList();

        //��������v�f
        IfoOrderUnit[] l_targetOrderUnits = null;
        if (l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull() == false)
        {
            l_targetOrderUnits = l_executedOrderUnits;
        }
        else
        {
            l_targetOrderUnits = new IfoOrderUnit[1];
            l_targetOrderUnits[0] = (IfoOrderUnit)l_orderUnit;
        }

        OrderAction[] l_orderAction = null;
        
        //��������v�f���Ƃ�Loop�����B
        for (int i = 0; i < l_targetOrderUnits.length; i++)
        {
            //get���������ꗗ(�����P�� : IfoOrderUnit)
            l_orderAction = l_targetOrderUnits[i].getOrderActions();

            IfoOrderUnit l_curOrderUnit = (IfoOrderUnit)l_targetOrderUnits[i];
            log.debug("l_curOrderUnit.getOrderUnitId()=" + l_curOrderUnit.getOrderUnitId());
            for (int j = 0; j < l_orderAction.length; j++)
            {
                IfoOrderAction l_curAction = (IfoOrderAction)l_orderAction[j];

                IfoOrderActionRow l_curOrderActionRow = (IfoOrderActionRow)
                    l_curAction.getDataSourceObject();

                //�����敨���������ꗗ�s()
                WEB3FuturesChangeCancelHistoryGroup l_action =
                    new WEB3FuturesChangeCancelHistoryGroup();

                //get���s����(PR�w)
                String l_strPRExecCond = WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_curAction.getExecutionConditionType()
                    );

                //get�������e�敪(IfoOrderAction, IfoOrderUnit)
                String l_strOrderType = WEB3IfoDataAdapter.getOrderSpecType(
                    l_curAction,l_curOrderUnit);

                //get��t���ʋ敪(IfoOrderAction)
                String l_strAcceptedResultDiv = WEB3IfoDataAdapter.getAcceptResultType(l_curAction);
                
                //(*2)�v���p�e�B�Z�b�g
                //����NO = ��������.��������ID
                l_action.orderActionId = WEB3StringTypeUtility.formatNumber(l_curAction.getOrderActionId());

                //������t�� = ��������.�쐬���t
                l_action.orderDate = l_curAction.getOrderActionTimestamp();

                //(*3)��肪�Ȃ��ꍇ(��������.isUnexecuted��true)�̂݃Z�b�g
                if (l_curAction.isUnexecuted())
                {
                    //get�v�w�l�p�L����ԋ敪(IfoOrderUnit)                
                    String l_strWLimitEnableStatusDiv =
                        WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_curAction);
                    
                    //get�v�w�l�p�֑ؑO�����P��(IfoOrderUnit)
                    String l_strWLimitBefSwitchPrice =
                        WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_curAction);
                    
                    //get�v�w�l�p�֑ؑO���s����(IfoOrderUnit)
                    String l_strWLimitBefSwitchExecCondType =
                        WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_curAction);
                    
                    //�������� = (*3)��������.��������
                    l_action.futOrderQuantity = WEB3StringTypeUtility.formatNumber(
                        Double.isNaN(l_curAction.getQuantity())?0D:(l_curAction.getQuantity()));

                    //�����P���敪 = (*3)��������.isMarketOrder�̕Ԃ�l��
                    //true�̏ꍇ�́A"���s"���Afalse�̏ꍇ�́A"�w�l"���Z�b�g
                    if (l_curAction.isMarketOrder())
                    {
                        l_action.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                        l_action.limitPrice = null;
                    }
                    else
                    {
                        l_action.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                        //�����P�� = (*3)�����P���敪��"�w�l"�̏ꍇ�̂݁A��������.�����P�� ���Z�b�g
                        l_action.limitPrice = WEB3StringTypeUtility.formatNumber(
                            Double.isNaN(l_curAction.getPrice())?0D:(l_curAction.getPrice()));
                    }

                    //���s���� =�@@(*3)get���s����(PR�w)�̖߂�l
                    l_action.execCondType = l_strPRExecCond;

                    //���������敪 = (*3)��������.��������
                    l_action.orderCondType = String.valueOf(l_curOrderActionRow.getOrderConditionType());

                    // �t�w�l�p���������P�� = (*3)��������.�������� == "�t�w�l"�̏ꍇ�̂݁A��������.�t�w�l��l
                    // �t�w�l�p�����������Z�q = (*3)��������.�������� == "�t�w�l"�̏ꍇ�̂݁A��������.�����������Z�q
                    if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                        l_curOrderActionRow.getOrderConditionType()))
                    {
                        l_action.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_curOrderActionRow.getStopOrderPrice());

                        l_action.stopOrderCondOperator = l_curOrderActionRow.getOrderCondOperator();
                    }
                    // W�w�l�p���������P�� = (*3)��������.�������� == "W�w�l"�̏ꍇ�̂݁A��������.�t�w�l��l
                    // W�w�l�p�����������Z�q = (*3)��������.�������� == "W�w�l"�̏ꍇ�̂݁A��������.�����������Z�q
                    // W�w�l�p�����P���敪 = (*3)��������.�������� == "W�w�l"�̏ꍇ�̂݁A(W�w�l)�����w�l��0�̏ꍇ��"���s"�A0�ȊO�̏ꍇ��"�w�l"
                    // W�w�l�p�����P�� = (*3)��������.�������� == "W�w�l"&& ��������.(W�w�l)�����w�l != 0(�w�l)�̏ꍇ�̂݁A��������.(W�w�l)�����w�l
                    // W�w�l�p���s���� = (*3)��������.�������� == "W�w�l"�̏ꍇ�̂݁A�敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(��������(W�w�l).���s����)
                    else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                        l_curOrderActionRow.getOrderConditionType()))
                    {
                        l_action.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_curOrderActionRow.getStopOrderPrice());

                        l_action.wlimitOrderCondOperator = l_curOrderActionRow.getOrderCondOperator();
                        
                        if (l_curOrderActionRow.getWLimitPrice() == 0)
                        {
                            l_action.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                            l_action.wLimitPrice = null;
                        }
                        else
                        {
                            l_action.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                            l_action.wLimitPrice = WEB3StringTypeUtility.formatNumber(
                                l_curOrderActionRow.getWLimitPrice());
                        }
                        
                        l_action.wlimitExecCondType = 
                            WEB3IfoDataAdapter.getExecutionCondByPr(l_curOrderActionRow.getWLimitExecCondType());
                    }

                    // �����L������ = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l��
                    //�@@�@@�@@"�o����܂Œ���"�̏ꍇ�̂݁A��������.�����������t���Z�b�g�B
                    //�@@�@@�@@�ȊO�ANULL���Z�b�g�B
                    String l_strExpirationDateType =
                        WEB3IfoDataAdapter.getExpirationDateType(l_curOrderUnit);
                    if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
                    {
                        l_action.expirationDate = WEB3DateUtility.toDay(l_curOrderActionRow.getExpirationDate());
                    }
                    else
                    {
                        l_action.expirationDate = null;
                    }
                    
                    //W�w�l�p�L����ԋ敪 = (*3)�敨OP�f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪()�̖߂�l
                    l_action.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
                    
                    //W�w�l�p�֑ؑO�����P�� = (*3)�敨OP�f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P��()�̖߂�l
                    l_action.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
                    
                    //W�w�l�p�֑ؑO���s���� = (*3)�敨OP�f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s����()�̖߂�l
                    l_action.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
                    
                    //�����������敪 = (*3)��������.����������
                    l_action.orgOrderCondType = l_curOrderActionRow.getOrgOrderConditionType();
                    
                    //�����������P�� = (*3)��������.���t�w�l��l
                    if (!l_curOrderActionRow.getOrgStopOrderPriceIsNull())
                    {
                        l_action.orgOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_curOrderActionRow.getOrgStopOrderPrice());
                    }
                    
                    //�������������Z�q = (*3)��������.�������������Z�q
                    l_action.orgCondOperator = l_curOrderActionRow.getOrgOrderCondOperator();
                    
                    //��W�w�l�p�����P���敪 = (*3)�敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P���敪(��������)
                    l_action.orgWLimitOrderPriceDiv = 
                        WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_curAction);
                    
                    //��W�w�l�p�����P�� = (*3)���v�w�l�p�����P���敪��"�w�l"�̏ꍇ�̂݁A
                    //�敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P��(��������)
                    if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_action.orgWLimitOrderPriceDiv))
                    {
                        l_action.orgWLimitPrice = WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_curAction);
                    }
                    
                    //��W�w�l�p���s���� = (*3)�敨OP�f�[�^�A�_�v�^.get���v�w�l�p���s����(��������)
                    l_action.orgWlimitExecCondType = 
                        WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_curAction);

                    //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
                    l_action.eveningSessionCarryoverFlag =
                        WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_curOrderUnit);

                    //����敪 = �����P��.����敪
                    l_action.sessionType =
                        ((IfoOrderUnitRow)l_curOrderUnit.getDataSourceObject()).getSessionType();
                }

                //�������e�敪 = get�������e�敪()�̖߂�l
                l_action.orderType = l_strOrderType;

                if (l_curAction.isUnexecuted() == false)
                {                                        
                    //(*4)��肪����ꍇ(��������.isUnexecuted��false)�̂݃Z�b�g
                    //��萔�� = (*4)��������.��萔��
                    l_action.execQuantity = WEB3StringTypeUtility.formatNumber(
                        Double.isNaN(l_curAction.getExecutionQuantity())?0D:(l_curAction.getExecutionQuantity()));

                    //���P�� = (*4)��������.���P��
                    l_action.execPrice = WEB3StringTypeUtility.formatNumber(
                        Double.isNaN(l_curAction.getExecutionPrice())?0D:(l_curAction.getExecutionPrice()));
                }

                //���l(���Ϗ���) = ��������.���Ϗ����iNULL�̏ꍇ��NULL���Z�b�g)
                l_action.closingOrder = l_curOrderActionRow.getClosingOrder();

                //��t���ʋ敪 = get��t���ʋ敪()�̖߂�l
                l_action.acceptedResultDiv = l_strAcceptedResultDiv;

                //add(�����w���I�v�V�������������ꗗ�s : Object)
                l_lisOrderHistory.add(l_action);
            }
        }

        //19.createResponse()
        WEB3FuturesOrderHistoryResponse l_response = null;
        l_response = (WEB3FuturesOrderHistoryResponse)
            l_request.createResponse();

        WEB3FuturesChangeCancelHistoryGroup[] l_historyGroups =
            new WEB3FuturesChangeCancelHistoryGroup[l_lisOrderHistory.size()];

        l_lisOrderHistory.toArray(l_historyGroups);

        l_response.futChangeCancelHistoryGroups = l_historyGroups;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ԍό��ʈꗗ)<BR>
     * �w�肳�ꂽ����ID���������P�ʃI�u�W�F�N�g�̕ԍό��ʏ����擾����<BR>
     * ��ʕ\���p�ɕҏW���A���X�|���X�ɐݒ肵�ĕԂ��B<BR>
     * �ԍϒ����̏ꍇ�̂݁A�R�[�������B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�敨�������Ɖ�T�[�r�X�jget�ԍό��ʈꗗ�v�Q�ƁB<BR>
     * <BR>
     * =============================================== <BR>
     *    �V�[�P���X�} : (�敨�������Ɖ�T�[�r�X)get�ԍό��ʈꗗ <BR>
     *    ��̈ʒu     : 1.5 (*)�����J�e�S���`�F�b�N�ԍϒ����ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�����P��.�����J�e�S�� != �h�敨�ԍϒ����h)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u�����ΏۊO�v�̗�O��throw����B<BR>
     *          class�@@�@@�@@�@@: WEB3BusinessLayerException <BR>
     *          tag          �@@: SYSTEM_ERROR_80025 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - �����w���敨�ԍό��ʈꗗ���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FuturesCloseMarginContractListResponse
     * @@throws WEB3BaseException
     *      * @@roseuid 40A9A5A90028
     */
    protected WEB3FuturesCloseMarginContractListResponse getSettleContractList(WEB3FuturesCloseMarginContractListRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSettleContractList(WEB3FuturesCloseMarginContractListRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3IfoPositionManagerImpl l_positionManager = 
           (WEB3IfoPositionManagerImpl)tradingModule.getPositionManager();
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager =
            (WEB3IfoFinTransactionManagerImpl)tradingModule.getFinTransactionManager();
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)finApp.getFinObjectManager();
            
        WEB3FuturesCloseMarginContractListResponse l_response = null;
            
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        try
        {
            //��t���ԃ`�F�b�N�A�V�X�e��������~�`�F�b�N�B
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            OrderUnit[] l_orderUnits =
                (OrderUnit[])futOrderManager.getOrderUnits(Long.parseLong(l_request.id));
        
		    if (l_orderUnits.length == 0)
		    {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w�肵������ID�ɊY������f�[�^�����݂��܂���B");
            }    

            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();
        
            //�ԍϒ����ȊO�̏ꍇ
            if (!OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnitRow.getOrderCateg()))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80025,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        
            IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = 
                (IfoContractSettleOrderUnit)l_orderUnits[0];

            IfoClosingContractSpec[] l_ifoClosingContractSpecs =
                l_ifoContractSettleOrderUnit.getContractsToClose();

            //�����P�ʂɊY������ԍώw���񂪎擾�ł��Ȃ������ꍇ    
            if (l_ifoClosingContractSpecs.length == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
       
            //reset�����R�[�h 
            IfoContract l_ifoContract1 = (IfoContract)l_positionManager.getContract(l_ifoClosingContractSpecs[0].getContractId());
            IfoProduct l_ifoProduct1 = (IfoProduct)l_ifoContract1.getProduct();
            WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct1.getUnderlyingProductCode());        

            //�����w���I�v�V�����ԍό��ʈꗗ���׍s�I�u�W�F�N�g���i�[����ԍό��ʖ��׍s���X�g�𐶐�
            ArrayList l_lisClosingContract = new ArrayList();
            
            //�����ʃJ�E���^�[
            int l_intCloseMarginOrderNumber = 0;     

            //���ʕԍώw����v�f���Ƃ�Loop�����B
            for (int i = 0; i < l_ifoClosingContractSpecs.length; i++)
            {
                //�����ʃJ�E���^�[�̃J�E���g�A�b�v
			    ++ l_intCloseMarginOrderNumber;
				
                IfoClosingContractSpec l_curIfoClosingContractSpec = l_ifoClosingContractSpecs[i];
            
                //�ԍϒ������ʂ��擾����B
                double l_dblQuantity = l_curIfoClosingContractSpec.getQuantity();
            
                // �ԍϒ������ʂ�0�̏ꍇ�A���X�g�ւ̒ǉ����s��Ȃ�                 
                if (l_dblQuantity == 0.0D)                  
                {                   
                    //�����ʃJ�E���^�[�̃J�E���g�_�E��
                    -- l_intCloseMarginOrderNumber;
                    continue;               
                }

                //�ԍϖ�萔�ʂ��擾����B
                double l_dblExecutedQuantity = l_curIfoClosingContractSpec.getExecutedQuantity();
                //����ID���擾����B
                long l_lngContractId = l_curIfoClosingContractSpec.getContractId();
                //�ԍώw����ɂЂ��Â����ʃI�u�W�F�N�g���擾����B
                WEB3IfoContractImpl l_ifoContract = 
                    (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);
                //���P�����擾����B
                double l_dblContractPrice = l_ifoContract.getContractPrice();
                //�������擾����B
                Date l_datOpenDate = l_ifoContract.getOpenDate();
                //����������擾����B
                double l_dblExecTotalPrice =
                    l_ifoContract.getContractExecutedAmount(l_dblQuantity);
                //���萔��
                double l_dblCommission = 0;
                //���萔�������
                double l_dblCommissionConsumptionTax = 0;
                //�����z
                double l_dblExecuteAmount = 0;
                //���v      
                double l_dblLostProfit = 0;
           
                //�ԍϖ����A�ꕔ���̏ꍇ 
                if (l_dblQuantity > l_dblExecutedQuantity)
                {
                    //���萔�����擾����B
                    l_dblCommission =
                        l_ifoContract.getContractCommission(l_dblQuantity - l_dblExecutedQuantity);
                
                    //���萔������ł��擾����B
                    l_dblCommissionConsumptionTax =
                        l_ifoContract.getContractCommissionConsumptionTax(l_dblQuantity - l_dblExecutedQuantity);
                }

                BigDecimal l_bdCommission = new BigDecimal(l_dblCommission + "");
                BigDecimal l_bdCommissionConsumptionTax = new BigDecimal(l_dblCommissionConsumptionTax + "");
                BigDecimal l_bdExecuteAmount = new BigDecimal(l_dblExecuteAmount + "");
                BigDecimal l_bdLostProfit = new BigDecimal(l_dblLostProfit + "");
                //�ԍψꕔ���A�S�����̏ꍇ 
                if (l_dblExecutedQuantity > 0)
                {
                    //�����P��ID�ƌ���ID�ɊY������g�����U�N�V����(������薾��)���擾����                
                    List l_transacions = l_finTransactionManager.getTransactions(l_ifoContractSettleOrderUnit.getOrderUnitId(),l_ifoContract.getContractId());
                    for (int j = 0; j < l_transacions.size(); j++)
                    {
                        IfoFinTransactionRow l_transacionRow = (IfoFinTransactionRow)l_transacions.get(j);
                        l_bdExecuteAmount = l_bdExecuteAmount.add(
                            new BigDecimal(l_transacionRow.getQuantity() + "").multiply(
                                new BigDecimal(l_transacionRow.getPrice() + "")));
                        l_bdLostProfit = l_bdLostProfit.add(new BigDecimal(l_transacionRow.getCapitalGain() + ""));
                        l_bdCommission = l_bdCommission.add(new BigDecimal(l_transacionRow.getImportedSetupFee() + ""));
                        l_bdCommissionConsumptionTax = l_bdCommissionConsumptionTax.add(
                            new BigDecimal(l_transacionRow.getImportedSetupFeeTax() + ""));
                    }
                }

                //�����w���敨�ԍό��ʈꗗ���׍s�I�u�W�F�N�g�𐶐�����B
                WEB3FuturesCloseMarginContractGroup l_futCloseMarginContractGroup = new WEB3FuturesCloseMarginContractGroup();
                l_futCloseMarginContractGroup.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
                l_futCloseMarginContractGroup.openDate = l_datOpenDate;
                l_futCloseMarginContractGroup.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
                if (l_orderUnitRow.getLimitPrice() == 0)
                {
                    l_futCloseMarginContractGroup.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_futCloseMarginContractGroup.limitPrice = null;
                }
                else
                {
                    l_futCloseMarginContractGroup.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_futCloseMarginContractGroup.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
                }
            
                if (l_dblExecutedQuantity > 0)
                {
                    //��萔�� = �敨OP�ԍώw����.�ԍϖ�萔��
                    l_futCloseMarginContractGroup.execQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
                    l_futCloseMarginContractGroup.execPrice = WEB3StringTypeUtility.formatNumber(l_bdExecuteAmount.divide(
                        new BigDecimal(l_dblExecutedQuantity + ""), 0, BigDecimal.ROUND_HALF_UP).doubleValue());
                    l_futCloseMarginContractGroup.settleProfitLoss = WEB3StringTypeUtility.formatNumber(l_bdLostProfit.doubleValue());
                }
                l_futCloseMarginContractGroup.contractCommission = WEB3StringTypeUtility.formatNumber(l_bdCommission.doubleValue());
                l_futCloseMarginContractGroup.contractCommissionConsumptionTax =
                    WEB3StringTypeUtility.formatNumber(l_bdCommissionConsumptionTax.doubleValue());
                l_futCloseMarginContractGroup.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblExecTotalPrice);
                l_futCloseMarginContractGroup.closeMarginOrderNumber = String.valueOf(l_intCloseMarginOrderNumber);
                l_futCloseMarginContractGroup.sessionType =
                    ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();

                //�v���p�e�B���Z�b�g���������w���I�v�V�����ԍό��ʈꗗ���׍s�I�u�W�F�N�g��ԍό��ʈꗗ���׍s���X�g�ɒǉ�
                l_lisClosingContract.add(l_futCloseMarginContractGroup);
            }
                
            //19.createResponse()
            l_response = (WEB3FuturesCloseMarginContractListResponse)l_request.createResponse();
            
            l_response.marketCode =
                l_gentradeFinObjectManager.getMarket(l_ifoContract1.getMarketId()).getMarketCode();
            l_response.productName = ((IfoProductRow)l_ifoProduct1.getDataSourceObject()).getStandardName();
            if (l_ifoContract1.isLong())
            {
                l_response.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
            }
            else
            {
                l_response.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
            }
            l_response.closingOrder = l_orderUnitRow.getClosingOrder();
            l_response.closeMarginContractGroups = 
                (WEB3FuturesCloseMarginContractGroup[])l_lisClosingContract.toArray
                (new WEB3FuturesCloseMarginContractGroup[l_lisClosingContract.size()]);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�����R�[�h����)<BR>
     * �w������̕ێ����銔���w���敨�����̖����R�[�h��<BR>
     * �������̈ꗗ���擾����B<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�������Ɖ�T�[�r�X�jcreate�����R�[�h���́v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_request - �����w���敨�������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@return WEB3FuturesOptionsProductCodeNameUnit[]
     * @@roseuid 40A9A5A90037
     */
    protected WEB3FuturesOptionsProductCodeNameUnit[] createProductCodeName(WEB3GentradeSubAccount l_subAccount, WEB3FuturesExecuteReferenceRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createProductCodeName(WEB3GentradeSubAccount, WEB3FuturesExecuteReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        //����������������쐬����
        String l_strSerachCond = createSearchCondCharacterString();

        //���������f�[�^�R���e�i���쐬����
        String[] l_SearchCondDataContainer = createSearchCondDataContainer();

        //create�\�[�g����(�����w���敨�I�v�V�����\�[�g�L�[[])
        String l_strSort = createSortCond(null);

        //�w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B
        List l_lisOrderUnits = futOrderManager.getOrderUnits(
            l_subAccount,
            ProductTypeEnum.IFO,
            l_strSerachCond,
            l_SearchCondDataContainer,
            l_strSort
            );

        //get�����P�ʈꗗ�̕Ԃ�l��NULL�̏ꍇ�ɂ́ANULL��ԋp���I������B
        if (l_lisOrderUnits == null || l_lisOrderUnits.size() == 0)
        {
            return null;
        }

        TreeMap l_productList = new TreeMap(); 
        
        //�����P�ʗv�f���Ƃ�Loop�����B
        int l_intListLength = l_lisOrderUnits.size();
        for (int i = 0; i < l_intListLength; i++)
        {
            //�����P�ʂ̎擾
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_lisOrderUnits.get(i);
            //�����̎擾
            WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_orderUnit.getProduct();
            IfoProductRow l_productRow = (IfoProductRow)l_ifoProduct.getDataSourceObject();
            //reset�����R�[�h
            WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProduct.getUnderlyingProductCode());

            //���N�G�X�g.�Ɖ�敪 = "��������ꗗ"�̏ꍇ�A��������\�`�F�b�N���s��
            if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType))
            {
                //��������s�̏ꍇ�A�ȍ~�̏������s��Ȃ�
                if (isChangeCancelEnable(l_subAccount, l_orderUnit) == false)
                {
                    continue;
                }
            }
            
            String l_strProductCode = l_productRow.getProductCode();
            String l_strProductName = l_productRow.getStandardName();

            if (!l_productList.containsKey(l_strProductCode))
            {
                WEB3FuturesOptionsProductCodeNameUnit l_productCodeName
                     = new WEB3FuturesOptionsProductCodeNameUnit();
                l_productCodeName.productCode = l_strProductCode;
                l_productCodeName.productName = l_strProductName;
                l_productList.put(
                    l_productCodeName.productCode,
                    l_productCodeName
                    );
            }
        }
        int l_size = l_productList.size();
        if(l_size == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        WEB3FuturesOptionsProductCodeNameUnit[] l_retrunValues = new WEB3FuturesOptionsProductCodeNameUnit[l_size];
        l_productList.values().toArray(l_retrunValues);
        
        log.exiting(STR_METHOD_NAME);
        return l_retrunValues;
    }

    /**
     * (create�������Ɖ�)<BR>
     * �������Ɖ��ʁ^��������ꗗ��ʂɕ\������<BR>
     * �������Ɖ���P�ʂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�������Ɖ�T�[�r�X�jcreate�������Ɖ�v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_request - �����w���敨�������Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@param l_response - �����w���敨�������Ɖ�X�|���X�I�u�W�F�N�g
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@return WEB3FuturesExecuteGroup[]
     * @@roseuid 40A9A5A90047
     */
    protected WEB3FuturesExecuteGroup[] createOrderExecutedInquiry(
        WEB3GentradeSubAccount l_subAccount,
        WEB3FuturesExecuteReferenceRequest l_request,
        WEB3FuturesExecuteReferenceResponse l_response,
        WEB3IfoProductImpl l_ifoProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createOrderExecutedInquiry(WEB3GentradeSubAccount, " +
            "WEB3FuturesExecuteReferenceRequest,WEB3IfoProductImpl)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesExecuteGroup[] l_executeGroups = null;

        //1.1 ����������������쐬����
        String l_strSerachCond = createSearchCondCharacterString(
            l_ifoProduct,
            l_request.orderBizDate,
            l_request.orderCondType);

        //1.2 ���������f�[�^�R���e�i���쐬����
        String[] l_SearchCondDataContainer = createSearchCondDataContainer(
            l_ifoProduct,
            l_request.orderBizDate,
            l_request.orderCondType);

        //1.3 �\�[�g����
        String l_strSort = createSortCond(l_request.futOpSortKeys);
        //�w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B
        List l_lisOrderUnit = futOrderManager.getOrderUnits(
            l_subAccount,
            ProductTypeEnum.IFO,
            l_strSerachCond,
            l_SearchCondDataContainer,
            l_strSort
            );

        //1.4 get�����P�ʈꗗ�̕Ԃ�l��NULL�̏ꍇ�ɂ́ANULL��ԋp���I������B
        if (l_lisOrderUnit == null || l_lisOrderUnit.size() == 0)
        {
            log.debug("�����Ɉ�v���钍���P�ʃ��R�[�h�Ȃ�");
            return null;
        }

        //1.5 ���N�G�X�g�f�[�^.����ԋ敪��NULL�̏ꍇ�̂݁A�ȉ��̏��������{����B
        if (l_request.execType != null)
        {
            ListIterator l_iterator = l_lisOrderUnit.listIterator();
            while (l_iterator.hasNext())
            {
                IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_iterator.next();
                boolean l_excuteStatusDivIs = isDesignateExecutedStatus(l_request.execType, l_orderUnit);
                //�w��̖���ԂƈقȂ�ꍇ�A�����P�ʈꗗ���珜��
                if (l_excuteStatusDivIs == false)
                {
                    l_iterator.remove();
                }
            }
        }
        //�����P�ʈꗗ�v�f����0�ɂȂ����ꍇ��NULL��ԋp���I������B
        if (l_lisOrderUnit.size() == 0)
        {
            return null;
        }

        //1.6 �J�z�������P�ʂ̏���
        IfoOrderUnit[] l_orderUnitsBeforeRemove =
            (IfoOrderUnit[])l_lisOrderUnit.toArray(new IfoOrderUnit[l_lisOrderUnit.size()]);
        IfoOrderUnit[] l_orderUnitsAfterRemove =
            futOrderManager.removeCarryOverOriginalOrderUnit(l_orderUnitsBeforeRemove);

        //remove�J�z�������P�ʂ̕Ԃ�l��NULL�̏ꍇ��NULL��ԋp���I������B
        if (l_orderUnitsAfterRemove == null ||
            l_orderUnitsAfterRemove.length == 0)
        {
            return null;
        }

        //1.7 �������Ɖ���P�ʃ��X�g�𐶐�
        ArrayList l_lisExecutionGroup = new ArrayList(l_orderUnitsAfterRemove.length);
        //�����P�ʃ��X�g�𐶐�
        ArrayList l_lisSelectedOrderUnit = new ArrayList(l_orderUnitsAfterRemove.length);

        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)finApp.getFinObjectManager();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)
            tradingModule.getPositionManager();

        try
        {

            for (int i = 0; i < l_orderUnitsAfterRemove.length; i++)
            {
                //�����P�ʂ̎擾
                IfoOrderUnit l_curOrderUnit = l_orderUnitsAfterRemove[i];
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_curOrderUnit.getDataSourceObject();

                //�s��̎擾
                Market l_market = l_finObjectManager.getMarket(l_ifoOrderUnitRow.getMarketId());

                //�����̎擾
                WEB3IfoProductImpl l_curIfoProduct = (WEB3IfoProductImpl)l_curOrderUnit.getProduct();
                IfoProductRow l_productRow = (IfoProductRow)l_curIfoProduct.getDataSourceObject();

                //1.8 reset�����R�[�h
                WEB3GentradeTradingTimeManagement.resetProductCode(l_curIfoProduct.getUnderlyingProductCode());

                //1.9 ��������̎擾 
                WEB3IfoTradedProductImpl l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_curOrderUnit.getTradedProduct();

                //1.10 ������ʂ̎擾
                OrderTypeEnum l_orderType = l_curOrderUnit.getOrderType();

                //is�����iisLongOrder�j
                boolean l_blnIsLongOrder = false;
                if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType) ||
                    OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
                {
                    l_blnIsLongOrder = true;
                }
                //is�V�K���iisOpenContract�j
                boolean l_blnIsOpenContract = false;
                if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType) ||
                    OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
                {
                    l_blnIsOpenContract = true;
                }
                //1.11 is����K��(boolean, boolean)
                boolean l_blnTradingSuspended = false;
                l_blnTradingSuspended = l_ifoTradedProduct.isTradingSuspended(
                    l_blnIsLongOrder,
                    l_blnIsOpenContract
                    );

                //1.12 ���N�G�X�g.�Ɖ�敪 = "��������ꗗ"�̏ꍇ�A��������s�Ȃ�΁A�ȍ~�̏����͍s��Ȃ�
                boolean l_blnCanChangeCancel = isChangeCancelEnable(l_subAccount, l_curOrderUnit);
                if (WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(l_request.referenceType))
                {
                    if (l_blnCanChangeCancel == false)
                    {
                        continue;
                    }
                }

                //1.13 �����w���敨�������Ɖ���P��()
                WEB3FuturesExecuteGroup l_executeGroup = new WEB3FuturesExecuteGroup();

                //1.14 �v���p�e�B�Z�b�g
                //ID = �����P��.����ID
                l_executeGroup.id = WEB3StringTypeUtility.formatNumber(l_curOrderUnit.getOrderId());
                //������
                l_executeGroup.futProductName = l_productRow.getStandardName();
                //����s��
                l_executeGroup.marketCode = l_market.getMarketCode();
                //�����R�[�h
                l_executeGroup.futProductCode = l_curIfoProduct.getProductCode();
                //�w�����
                l_executeGroup.targetProductCode = l_curIfoProduct.getUnderlyingProductCode();
                //����
                l_executeGroup.delivaryMonth = l_curIfoProduct.getMonthOfDelivery();

                //�����\�t���O
                //�ȉ��̂����ꂩ�ɂ��Ă͂܂�ꍇ��false���Z�b�g�B�ȊO��true���Z�b�g�B
                //�E�����K���`�F�b�N���`�F�b�NNG(is����K����false)
                //�E��������\�`�F�b�N���`�F�b�NNG
                //�Evalidate���������\��Ԃ���O��throw
                WEB3IfoOrderManagerReusableValidations l_ifoOrderValidator =
                    (WEB3IfoOrderManagerReusableValidations)
                        WEB3IfoOrderManagerReusableValidations.getInstance();

                Order l_order = l_curOrderUnit.getOrder();

                l_executeGroup.changeFlag = true;
                if (l_blnTradingSuspended == true || l_blnCanChangeCancel == false)
                {
                    l_executeGroup.changeFlag = false;
                }
                else
                {
                    try
                    {
                        l_ifoOrderValidator.validateOrderForChangeability(l_order);
                    }
                    catch (OrderValidationException l_ovE)
                    {
                        l_executeGroup.changeFlag = false;
                    }
                }

                //����\�t���O
                //�ȉ��̂����ꂩ�ɂ��Ă͂܂�ꍇ��false���Z�b�g�B�ȊO��true���Z�b�g�B
                //�E��������\�`�F�b�N���`�F�b�NNG
                //�Evalidate��������\��Ԃ���O��throw
                l_executeGroup.cancelFlag = true;
                if (l_blnCanChangeCancel == false)
                {
                    l_executeGroup.cancelFlag = false;
                }
                else
                {
                    try
                    {
                        l_ifoOrderValidator.validateOrderForCancellation(l_order);
                    }
                    catch (OrderValidationException l_ovE)
                    {
                        l_executeGroup.cancelFlag = false;
                    }
                }

                //1.15 �������Ɖ���P�ʃ��X�g�ɒǉ�
                l_lisExecutionGroup.add(l_executeGroup);

                //1.16 �����P�ʃ��X�g�ɒǉ�
                l_lisSelectedOrderUnit.add(l_curOrderUnit);
            }

            if(l_lisExecutionGroup.size() == 0)
            {
                return null;
            }

            //1.17 toArray
            l_executeGroups = new WEB3FuturesExecuteGroup[l_lisExecutionGroup.size()];
            l_lisExecutionGroup.toArray(l_executeGroups);
            
            //���X�|���X�̃y�[�W�֘A���ڐݒ�
            //1.18 ���X�|���X.ID�ꗗ�F�@@���������ɊY�����钍��ID��S�ăZ�b�g
            // �i�v�f���́A�����R�[�h���ɓ������j
            if (l_lisExecutionGroup != null)
            {
                int l_intOrderSize = l_executeGroups.length;
                l_response.idList = new String[l_intOrderSize];
                for (int i = 0;i < l_intOrderSize; i++)
                {
                    l_response.idList[i] = l_executeGroups[i].id;
                }
            }

            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intPageSize = Integer.parseInt(l_request.pageSize);

            //1.19 �\���ΏۂƂȂ钍�����Ɖ���P�ʃ��X�g�̍쐬
            WEB3PageIndexInfo l_pageIndexInfo =
                new WEB3PageIndexInfo(l_executeGroups, l_intPageIndex, l_intPageSize);
            l_response.futExecuteGroups
                = (WEB3FuturesExecuteGroup[])l_pageIndexInfo.getArrayReturned(WEB3FuturesExecuteGroup.class);

            //���X�|���X.�\���y�[�W�ԍ�
            l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
            //���X�|���X.���y�[�W��
            l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
            //���X�|���X.�����R�[�h��
            l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

            //�\���ΏۂƂȂ钍���P�ʃ��X�g�̍쐬
            WEB3PageIndexInfo l_ordersAtPage =
                new WEB3PageIndexInfo(l_lisSelectedOrderUnit, l_intPageIndex, l_intPageSize);
            Object[] l_objReturned = l_ordersAtPage.getArrayReturned(IfoOrderUnit.class);

            for (int i = 0; i < l_response.futExecuteGroups.length; i++)
            {
                WEB3FuturesExecuteGroup l_executeGroup = l_response.futExecuteGroups[i];
                IfoOrderUnit l_curOrderUnit = (IfoOrderUnit)l_objReturned[i];
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_curOrderUnit.getDataSourceObject();

                //�����̎擾
                WEB3IfoProductImpl l_curIfoProduct = (WEB3IfoProductImpl)l_curOrderUnit.getProduct();

                //1.20 reset�����R�[�h
                WEB3GentradeTradingTimeManagement.resetProductCode(l_curIfoProduct.getUnderlyingProductCode());

                //��������̎擾 
                WEB3IfoTradedProductImpl l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_curOrderUnit.getTradedProduct();

                //1.21 get���s����(PR�w)
                String l_strPRExcCondType = WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_ifoOrderUnitRow.getExecutionConditionType());

                //1.23 get�����󋵋敪()
                String l_strTransactionStateType =
                    WEB3IfoDataAdapter.getTransactionStatusType(l_curOrderUnit);
                
                //1.24 get�v�w�l�p�L����ԋ敪(IfoOrderUnit)                
                String l_strWLimitEnableStatusDiv =
                    WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_curOrderUnit);
                
                //1.25 get�v�w�l�p�֑ؑO�����P��(IfoOrderUnit)
                String l_strWLimitBefSwitchPrice =
                    WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_curOrderUnit);
                
                //1.26 get�v�w�l�p�֑ؑO���s����(IfoOrderUnit)
                String l_strWLimitBefSwitchExecCondType =
                    WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_curOrderUnit);
                
                //1.27 is�蓮�����\(IfoOrderUnit)
                boolean l_blnIsManualOrderPossible = 
                    futOrderManager.isManualOrderPossible(l_curOrderUnit);
                
                //1.28 get�x���敪(IfoOrderUnit)
                String l_strDelayDiv = WEB3IfoDataAdapter.getDelayDiv(l_curOrderUnit);
                
                //�����A���P��
                // [�����P��.�����J�e�S�� == �h�敨�V�K�������h�̏ꍇ]
                if(OrderCategEnum.IDX_FUTURES_OPEN.equals(l_curOrderUnit.getOrderCateg()))
                {
                    l_executeGroup.openDate = null;
                    l_executeGroup.contractPrice = null;    
                }
                // [�����P��.�����J�e�S�� == �h�敨�ԍϒ����h�̏ꍇ]
                else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_curOrderUnit.getOrderCateg())) 
                {
                    //���ʕԍώw���� = �ԍϒ����P��.getContractsToClose()�Ŏ擾
                    IfoContractSettleOrderUnit l_contractSettleOrderUnit = (IfoContractSettleOrderUnit)l_curOrderUnit;
                    IfoClosingContractSpec[] l_spec = l_contractSettleOrderUnit.getContractsToClose();
                    
                    if (l_ifoOrderUnitRow.getClosingOrder() == null)
                    {
                        l_executeGroup.openDate = l_positionManager.getContract(l_spec[0].getContractId()).getOpenDate();
                        l_executeGroup.contractPrice = WEB3StringTypeUtility.formatNumber(l_positionManager.getContract(l_spec[0].getContractId()).getContractPrice());
                    }
                    else
                    {
                        l_executeGroup.openDate = null;
                        double l_dblTotalContractAmount = 0.0D;
                        double l_dblTotalQuantity = 0.0D;
                        
                        BigDecimal l_bdTotalContractAmount = new BigDecimal(l_dblTotalContractAmount + "");
                        //���ʕԍώw����̗v�f���ƂɌ�������Z�o���A�W�v����SUM�l���擾����
                        for (int j = 0;j < l_spec.length;j++)
                        {
                            //����� += (���ʕԍώw����.����ID����擾��������.���P�� * ���ʕԍώw����.�ԍϒ�������)
                            Contract l_contract = l_positionManager.getContract(l_spec[j].getContractId());
                            double l_dblQuantity = l_spec[j].getQuantity();
                            l_bdTotalContractAmount = l_bdTotalContractAmount.add(
                                new BigDecimal(l_contract.getContractPrice() + "").multiply(
                                    new BigDecimal(l_dblQuantity + "")));
                            l_dblTotalQuantity += l_dblQuantity;   
                        }
                        //���P���̕��ϒl = ����� /�@@���ʕԍώw����.�ԍϒ������ʂ�SUM�l(�~�����͎l�̌ܓ�)
                        l_executeGroup.contractPrice =
                            WEB3StringTypeUtility.formatNumber(l_bdTotalContractAmount.divide(
                                new BigDecimal(l_dblTotalQuantity + ""), 0, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                }
                //����敪 = �����P��.�������                
                l_executeGroup.tradingType = getPRLevelTradeTypeDiv(l_curOrderUnit.getOrderType());
                //�������� = �����P��.�󒍓���
                l_executeGroup.orderDate = l_ifoOrderUnitRow.getReceivedDateTime();
                //������ = �����P��.������
                l_executeGroup.orderBizDate = WEB3DateUtility.toDay(WEB3DateUtility.toDay(WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd")));                
                //�������� = �����P��.��������
                l_executeGroup.futOrderQuantity = WEB3StringTypeUtility.formatNumber(l_curOrderUnit.getQuantity());
                //�����P���敪
                //�����P��
                if (l_curOrderUnit.isMarketOrder())
                {
                    l_executeGroup.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    l_executeGroup.limitPrice = null;
                }
                else
                {
                    l_executeGroup.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_executeGroup.limitPrice = WEB3StringTypeUtility.formatNumber(l_curOrderUnit.getLimitPrice());
                }
                //���s���� = get���s����(PR�w)�̕Ԃ�l
                l_executeGroup.execCondType = l_strPRExcCondType;
                //���������敪 = �����P��.��������
                l_executeGroup.orderCondType = l_ifoOrderUnitRow.getOrderConditionType();
                //�����P��.����������"�t�w�l"�̏ꍇ
                if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_executeGroup.orderCondType))
                {
                    l_executeGroup.stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    l_executeGroup.stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                }
                //�����P��.����������"W�w�l"�̏ꍇ
                else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_executeGroup.orderCondType))
                {
                    l_executeGroup.wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    l_executeGroup.wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                    if (l_ifoOrderUnitRow.getWLimitPrice() == 0D)
                    {
                        l_executeGroup.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        l_executeGroup.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                        l_executeGroup.wLimitPrice = WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                    }
                    
                    //W�w�l�p���s���� = (*11)�敨OP�f�[�^�A�_�v�^.get���s�����iPR�w�j(�����P��.(W�w�l)���s����)�̖߂�l
                    l_executeGroup.wlimitExecCondType = 
                        WEB3IfoDataAdapter.getExecutionCondByPr(l_ifoOrderUnitRow.getWLimitExecCondType());
                }
                
                //W�w�l�p�L����ԋ敪 = �敨OP�f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪()�̖߂�l
                l_executeGroup.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
                
                //W�w�l�p�֑ؑO�����P�� = �敨OP�f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P��()�̖߂�l
                l_executeGroup.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
                
                //W�w�l�p�֑ؑO���s���� = �敨OP�f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s����()�̖߂�l
                l_executeGroup.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
                
                //(*7)��萔�ʁA���P���A�������ڍז��
                //1.29 ��肪����ꍇ(�����P��.isUnexecuted��false)�̂݃Z�b�g
                if (l_curOrderUnit.isUnexecuted() == false)
                {
                    //1.29.1 getExecutions()
                    OrderExecution[] l_orderExecutions = l_curOrderUnit.getExecutions();

                    //��������̎擾 
                    l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_curOrderUnit.getTradedProduct();
                    //�����P��.���v�����z�̎擾
                    double l_dblExecutedAmount = l_curOrderUnit.getExecutedAmount();
                    //�����P��.��萔�ʂ̎擾
                    double l_dblExecutedQuantity = l_curOrderUnit.getExecutedQuantity();
                    //�w���搔�̎擾
                    double l_dblUnitSize = l_ifoTradedProduct.getUnitSize();
                    //���P�� = �����P��.���v�����z �� �����P��.��萔�� ���w���搔(�~�����͎l�̌ܓ�)
                    BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount + "");
                    BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity + "");
                    BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize + "");
                    double l_execPrice = l_bdExecutedAmount.divide(
                        l_bdExecutedQuantity, 10, BigDecimal.ROUND_HALF_UP).divide(
                            l_bdUnitSize, 0, BigDecimal.ROUND_HALF_UP).doubleValue();
                    l_executeGroup.execPrice = WEB3StringTypeUtility.formatNumber(l_execPrice);
                    //��萔��
                    l_executeGroup.execQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);

                    //�������ڍז��
                    WEB3FuturesExecuteUnit[] l_executeUnits =
                        new WEB3FuturesExecuteUnit[l_orderExecutions.length];
                    for (int j = 0; j < l_orderExecutions.length; j++)
                    {
                        l_executeUnits[j] = new WEB3FuturesExecuteUnit();
                        //OP�����������Ɖ���.���P���̐ݒ�
                        l_executeUnits[j].execPrice =
                            WEB3StringTypeUtility.formatNumber(l_orderExecutions[j].getExecutionPrice());
                        //OP�����������Ɖ���.��芔���̐ݒ�
                        l_executeUnits[j].execQuantity =
                            WEB3StringTypeUtility.formatNumber(l_orderExecutions[j].getExecutionQuantity());
                        //OP�����������Ɖ���.�������̐ݒ�
                        l_executeUnits[j].executionTimestamp =
                            l_orderExecutions[j].getExecutionTimestamp();
                    }
                    l_executeGroup.futExecuteUnits = l_executeUnits;

                    //1.29.2 get��n���z���v()
                    double l_dblDeliveryAmt = futOrderManager.getNetAmount(l_curOrderUnit);
                    l_executeGroup.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryAmt);
                }                
                //�T�Z��n��� = �����P��.�T�Z��n���
                l_executeGroup.estimatedContractPrice = 
                    WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getEstimatedPrice());
                //������ = get�����󋵋敪()�̕Ԃ�l
                l_executeGroup.transactionStateType = l_strTransactionStateType;
                //�����L������ = �敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)�̖߂�l��
                //�@@�@@�@@"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�����������t���Z�b�g�B
                //�@@�@@�@@�ȊO�ANULL���Z�b�g�B
                String l_strExpirationDateType =
                    WEB3IfoDataAdapter.getExpirationDateType(l_curOrderUnit);
                if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
                {
                    l_executeGroup.expirationDate = l_curOrderUnit.getExpirationTimestamp();
                }
                else
                {
                    l_executeGroup.expirationDate = null;
                }

                if (this.getTrader() != null)
                {
                    //�����`���l�� = �����P��.���񒍕��̒����`���l�����Z�b�g
                    l_executeGroup.orderChannel = l_ifoOrderUnitRow.getOrderChanel();
                    //�����o�H�敪 = �����P��.�����o�H�敪���Z�b�g
                    l_executeGroup.orderRootDiv = l_ifoOrderUnitRow.getOrderRootDiv();
                    //�����P��.�����ID��null�̏ꍇ�̂݁A���҃R�[�h���Z�b�g
                    if (!l_ifoOrderUnitRow.getTraderIdIsNull())
                    {
                        try
                        {
                            //�g�����Z�I�u�W�F�N�g�}�l�[�W��.getTrader()
                            Trader l_trader = null;
                            l_trader = l_finObjectManager.getTrader(l_curOrderUnit.getTraderId());
                            l_executeGroup.operatorCode = l_trader.getTraderCode();
                        }
                        catch (NotFoundException l_nfe)
                        {
                            //�����P��.�����ID�ɊY������f�[�^�����҃e�[�u���ɑ��݂��Ȃ��ꍇ
                            l_executeGroup.operatorCode = null;
                        }
                    }
                }

                //�x���敪 = �敨OP�f�[�^�A�_�v�^.get�x���敪()�̖߂�l
                l_executeGroup.delayDiv = l_strDelayDiv;

                //�蓮�����\�t���O = �敨�����}�l�[�W��.is�蓮�����\()�̖߂�l
                l_executeGroup.manualFlag = l_blnIsManualOrderPossible;

                //�����������敪 = �����P��.����������
                l_executeGroup.orgOrderCondType = l_ifoOrderUnitRow.getOrgOrderConditionType();
                
                //�����������P�� = �����P��.���t�w�l��l
                if (!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
                {
                    l_executeGroup.orgOrderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getOrgStopOrderPrice());
                }
                
                //�������������Z�q = �����P��.�������������Z�q
                l_executeGroup.orgCondOperator = l_ifoOrderUnitRow.getOrgOrderCondOperator();
                
                //��W�w�l�p�����P���敪 = �敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P���敪(�����P��)
                l_executeGroup.orgWLimitOrderPriceDiv = 
                    WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_curOrderUnit);
                
                //��W�w�l�p�����P�� = ���v�w�l�p�����P���敪��"�w�l"�̏ꍇ�̂݁A
                //�敨OP�f�[�^�A�_�v�^.get���v�w�l�p�����P��(�����P��)
                if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_executeGroup.orgWLimitOrderPriceDiv))
                {
                    l_executeGroup.orgWLimitPrice = 
                        WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_curOrderUnit);
                }
                                
                //��W�w�l�p���s���� = �敨OP�f�[�^�A�_�v�^.get���v�w�l�p���s����(�����P��)
                l_executeGroup.orgWlimitExecCondType = WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_curOrderUnit);

                //�[��O�J�z�Ώۃt���O = �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
                l_executeGroup.eveningSessionCarryoverFlag =
                    WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_curOrderUnit);

                //����敪 = �����P��.����敪
                l_executeGroup.sessionType = l_ifoOrderUnitRow.getSessionType();
            }
        } catch (NotFoundException l_ex) {
                String msg = "�f�[�^�擾�G���[";
                log.error(msg, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_executeGroups;
    }

    /**
     * (create��������������)<BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
     * <BR>
     * (2)�敨�^�I�v�V�����敪�𕶎���C���X�^���X�ɐݒ�<BR>
     * <BR>
     *     " and future_option_div = ?"<BR>
     * <BR>
     * (3)�������w��𕶎���C���X�^���X�ɐݒ� <BR>
     * <BR>
     * (3-1)�p�����[�^.��������NULL�i�������w��j�̏ꍇ<BR>
     * <BR>
     *     " and biz_date = ?" <BR>
     * <BR>
     * (3-2)�p�����[�^.��������NULL�i�������w��Ȃ��j�̏ꍇ <BR>
     * <BR>
     *     " and biz_date >= ?" <BR>
     * <BR>
     * (4)�p�����[�^.�����R�[�h��NULL�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * ����ID�w���ǉ��i�����R�[�h�ɑΉ��������ID�Ō������s��)<BR>
     * <BR>
     * " and product_id=?" <BR>
     * <BR> 
     * (5)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ�A<BR>
     * �@@�@@���������敪�w���ǉ� <BR>
     * �@@�@@�i�敨OP�����P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���������������ƂɌ�������B  <BR>
     * �@@�@@�@@�����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�敨OP�����P�ʃe�[�u��.�������������ƂɌ�������B) <BR>
     * <BR>
     * �@@�@@�@@" and nvl(org_order_condition_type,order_condition_type) = ?"  <BR>
     * <BR>
     * (6)������C���X�^���X��ԋp<BR>
     * <BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@param l_datBizDate - ������
     * @@param l_strOrderCondType - (���������敪)<BR>
     * ���������敪<BR>
     * @@return String
     * @@roseuid 40A9A5A90066
     */
    protected String createSearchCondCharacterString(
        WEB3IfoProductImpl l_ifoProduct,
        Date l_datBizDate,
        String l_strOrderCondType)
    {
        final String STR_METHOD_NAME =
            "createSearchCondCharacterString(WEB3IfoProductImpl, Date, String)";

        log.entering(STR_METHOD_NAME);

        //(1)�敨�^�I�v�V�����敪�𕶎���C���X�^���X�ɐݒ�
        String l_strSearchCond = " and future_option_div = ? ";
        
        //�p�����[�^.��������NULL�i�������w��j�̏ꍇ
        if (l_datBizDate != null && !l_datBizDate.equals(""))
        {
            l_strSearchCond += " and biz_date = ? ";
        }
        else
        {
            l_strSearchCond += " and biz_date >= ? ";
        }

        //�p�����[�^.�敨OP������NULL�̏ꍇ
        if (l_ifoProduct != null)
        {
            //����ID�w���ǉ�����
            l_strSearchCond += " and product_id = ?";
        }

        //�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
        if (l_strOrderCondType != null)
        {
            l_strSearchCond += " and nvl(org_order_condition_type,order_condition_type) = ?";
        }
        log.debug("�������������� = " + l_strSearchCond);

        log.exiting(STR_METHOD_NAME);

        return l_strSearchCond;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������iwhere�ȉ��w��̕�����j�̃p�����[�^�̕�����z����쐬����B<BR>
     * <BR>
     * (1)������z��𐶐�<BR>
     * <BR>
     * (2)�敨�^�I�v�V�����敪�𕶎���z��ɃZ�b�g<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪 �� "�敨"<BR>
     * �@@�@@�@@���敨�^�I�v�V�����敪 1�F�敨�@@2�F�I�v�V����<BR>
     * <BR>
     * (3)�������w��l�𕶎���z��ɃZ�b�g <BR>
     * <BR>
     * (3-1)�p�����[�^.��������NULL�i�������w��j�̏ꍇ <BR>
     * �������w��l �� �p�����[�^.������ <BR>
     * <BR>
     * (3-2)�p�����[�^.��������NULL�i�������w��Ȃ��j�̏ꍇ <BR>
     * �������w��l �� �Ɩ����t(*1) <BR>
     * <BR>
     * (4)�p�����[�^.�敨OP������NULL �̏ꍇ�A<BR>
     * ����ID�𕶎���z��ɃZ�b�g�i����ID�Ō������s��)<BR>
     * <BR>
     * (5)�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.���������敪�𕶎���z��ɃZ�b�g <BR>
     * <BR>
     * �@@�@@�@@���������敪 �� �p�����[�^.���������敪 <BR>
     * <BR>
     * (6)(2)�A(3)�A(4)�A(5)�ɂăp�����[�^���Z�b�g����������z���ԋp <BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@param l_datBizDate - ������
     * @@param l_strOrderCondType - (���������敪)<BR>
     * ���������敪<BR>
     * @@throws WEB3BaseException
     * @@return String[]
     * @@roseuid 40A9A5A90076
     */
    protected String[] createSearchCondDataContainer(
        WEB3IfoProductImpl l_ifoProduct,
        Date l_datBizDate,
        String l_strOrderCondType)
    {
        final String STR_METHOD_NAME =
            "createSearchCondDataContainer(WEB3IfoProductImpl, Date, String)";

        log.entering(STR_METHOD_NAME);

        //���������̐��ʂ�ݒ肷��B
        int l_intCondCount = 2;

        if (l_ifoProduct != null)
        {
            l_intCondCount++;
        }
        if (l_strOrderCondType != null)
        {
            l_intCondCount++;
        }

        //(1)������z��𐶐�
        String[] l_strParam = new String[l_intCondCount];

        //(2)�敨�^�I�v�V�����敪: 1 "�敨"
        l_strParam[0] = WEB3FuturesOptionDivDef.FUTURES;

        //(3)�������w��l�𕶎���z��ɃZ�b�g 
        if (l_datBizDate != null && !"".equals(l_datBizDate))
        {
            l_strParam[1] = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        }
        else
        {
            //�Ɩ����t���擾                 
            Date l_datDate = GtlUtils.getTradingSystem().getBizDate();
            l_strParam[1] = WEB3DateUtility.formatDate(l_datDate,"yyyyMMdd");
        }
        if (l_ifoProduct != null)
        {
            //����ID���Z�b�g
            l_strParam[2] = String.valueOf(l_ifoProduct.getProductId());
            //�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
            if (l_strOrderCondType != null)
            {
                //���������敪
                l_strParam[3] = l_strOrderCondType;
            }
        }
        else
        {
            //�p�����[�^.���������敪��NULL�i���������敪�w��j�̏ꍇ
            if (l_strOrderCondType != null)
            {
                //���������敪
                l_strParam[2] = l_strOrderCondType;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strParam;
    }


    /**
     * (create��������������)<BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * (1)�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�<BR>
     * <BR>
     * (2)�敨�^�I�v�V�����敪�𕶎���C���X�^���X�ɐݒ�<BR>
     * <BR>
     *     " and future_option_div = ?"<BR>
     * <BR>
     * (3)�������w��𕶎���C���X�^���X�ɐݒ� <BR>
     * <BR>
     *     " and biz_date >= ?" <BR>
     * <BR>
     * (4)������C���X�^���X��ԋp<BR>
     * <BR>
     * @@return String
     */
    protected String createSearchCondCharacterString()
    {
        final String STR_METHOD_NAME = "createSearchCondCharacterString()";
        log.entering(STR_METHOD_NAME);

        //�敨�^�I�v�V�����敪�𕶎���C���X�^���X�ɐݒ�
        String l_strSearchCond = " and future_option_div = ? ";

        //�������w��𕶎���C���X�^���X�ɐݒ�
        l_strSearchCond += " and biz_date >= ? ";

        log.exiting(STR_METHOD_NAME);
        return l_strSearchCond;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������iwhere�ȉ��w��̕�����j�̃p�����[�^�̕�����z����쐬����B<BR>
     * <BR>
     * (1)������z��𐶐�<BR>
     * <BR>
     * (2)�敨�^�I�v�V�����敪�𕶎���z��ɃZ�b�g<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪 �� "�敨"<BR>
     * �@@�@@�@@���敨�^�I�v�V�����敪 1�F�敨�@@2�F�I�v�V����<BR>
     * <BR>
     * (3)�������w��l�𕶎���z��ɃZ�b�g <BR>
     * <BR>
     * �������w��l �� �Ɩ����t(*1)�̑O�c�Ɠ�<BR>
     * <BR>
     * (4)(2)�A(3)�ɂăp�����[�^���Z�b�g����������z���ԋp<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@return String[]
     */
    protected String[] createSearchCondDataContainer() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSearchCondDataContainer()";
        log.entering(STR_METHOD_NAME);

        //������z��𐶐�
        String[] l_strParam = new String[2];

        //�敨�^�I�v�V�����敪: 1 "�敨"
        l_strParam[0] = WEB3FuturesOptionDivDef.FUTURES;

        //�Ɩ����t�̑O�c�Ɠ����擾                 
        Date l_datDate = GtlUtils.getTradingSystem().getBizDate();
        Date l_datFormerDate = (Date)WEB3GentradeUtils.getBizDate(l_datDate, -1);

		//�������w��l�𕶎���z��ɃZ�b�g
        l_strParam[1] = WEB3DateUtility.formatDate(l_datFormerDate,"yyyyMMdd");

        log.exiting(STR_METHOD_NAME);
        return l_strParam;
    }

    /**
     * (create�\�[�g����)<BR>
     *�\�[�g������������쐬���Ԃ��B<BR>
     *<BR>
     *(1)�p�����[�^.�\�[�g�L�[ == null�̏ꍇ�́A <BR>
     *�@@"����ID(*) ����"�̃\�[�g�����������ԋp����B <BR>
     *<BR>
     *�@@(*)�����P�ʃe�[�u��.����ID<BR>
     *<BR>
     *(2)�p�����[�^.�\�[�g�L�[.�L�[���ڂ̐����A<BR>
     *    �Ή�����e�[�u���̗񕨗����������^�~���w���t�����\�[�g������������Z�b�g���Ă����B<BR>
     *<BR>
     *�@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�<BR>
     *�@@�@@�@@�@@�@@�@@�E�����R�[�h�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D����ID<BR>
     *�@@�@@�@@�@@�@@�@@�E����s��@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�s��ID<BR>
     *�@@�@@�@@�@@�@@�@@�E����敪�@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�������<BR>
     *�@@�@@�@@�@@�@@�@@�E�������ԁ@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�󒍓���<BR>
     *�@@�@@�@@�@@�@@�@@�E�������@@�@@�@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D������<BR>
     *�@@�@@�@@�@@�@@�@@�E�����L�������@@�@@�@@�F�����P�ʃe�[�u���D�����������t<BR>
     *<BR>
     *�@@�@@�E�����^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ�<BR>
     *<BR>
     *(3)�\�[�g���������񖖔��ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��<BR>
     *<BR>
     *(4)�쐬�����\�[�g�����������ԋp<BR>
     *<BR>
     *���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q��<BR>
     *���e�[�u�����F�����P�ʃe�[�u��(ifo_order_unit)<BR>
     *���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q��<BR>
     * @@param l_futuresOptionsSortKey - �����w���敨�I�v�V�����\�[�g�L�[<BR>
     * @@return String
     * @@roseuid 40A9A5A90095
     */
    protected String createSortCond(WEB3FuturesOptionsSortKey[] l_futuresOptionsSortKey)
    {
        final String STR_METHOD_NAME =
            "createSortCond(WEB3FuturesOptionsSortKey)";

        log.entering(STR_METHOD_NAME);

        StringBuffer l_strReturn = new StringBuffer();
        if (l_futuresOptionsSortKey == null)
        {
            l_strReturn.append("product_id");
            l_strReturn.append(" ");
            l_strReturn.append("ASC");
            return l_strReturn.toString();
        }

        //�Ԋ҂̒l�̐ݒ�
        for (int i = 0; i < l_futuresOptionsSortKey.length; i++)
        {
            log.debug(" �L�[����" + i + " = "+ l_futuresOptionsSortKey[i].keyItem);
            log.debug(" �����^�~��" + i + " = "+ l_futuresOptionsSortKey[i].ascDesc);
            //�E�����R�[�h�@@�@@ �@@�@@�@@�F�����P�ʃe�[�u���D����ID
            if (l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.FUTPRODUCTCODE))
            {
                l_strReturn.append("PRODUCT_ID");
            }
            //�E����s��@@�@@�@@�@@�@@  �F�����P�ʃe�[�u���D�s��ID
            else if (l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.TRADE_MARKET))
            {
                l_strReturn.append("MARKET_ID");
            }
            //�E����敪            �F�����P�ʃe�[�u���D�������
            else if (l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.TRADE_DIVISION))
            {
                l_strReturn.append("ORDER_TYPE");
            }
            //�E�������ԁ@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�󒍓���
            else if (l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.ORDER_TIME))
            {
                l_strReturn.append("RECEIVED_DATE_TIME");
            }
            //�E������ �@@�@@�@@�@@�@@ �F�����P�ʃe�[�u���D������
            else if (l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.BIZ_DATE)) 
            {
                l_strReturn.append("BIZ_DATE");
            }
            //�E���������@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�����������t
            else if (l_futuresOptionsSortKey[i].keyItem.equals(WEB3IfoKeyItemDef.ORDER_EXPIRATION_DATE))
            {
                l_strReturn.append("EXPIRATION_DATE");
            }
            else
            {
                continue;
            }
            l_strReturn.append(" ");
            if (WEB3AscDescDef.ASC.equals(l_futuresOptionsSortKey[i].ascDesc))
            {
                l_strReturn.append("ASC");
            }
            else
            {
                l_strReturn.append("DESC");
            }
            if (i < l_futuresOptionsSortKey.length)
            {
                l_strReturn.append(" , ");
            }
        }
        l_strReturn.append("last_updated_timestamp");
        l_strReturn.append(" ");
        l_strReturn.append("ASC");
        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();
    }

    /**
     * (is�w������)<BR>
     * �w�肳�ꂽ����Ԃɍ��v���Ă��邩�ǂ����𔻒肵�A<BR>
     * ���v���Ă���ꍇ��true���A���v���Ă��Ȃ��ꍇ��false���A���ꂼ��Ԃ��B<BR>
     * <BR>
     * �敨OP�f�[�^�A�_�v�^.get����ԋ敪()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     *   �����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �@@�߂�l�̖���ԋ敪���p�����[�^.����ԋ敪�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �@@�ȊO�Afalse��Ԃ��B<BR>
     * @@param l_strExecutedStatus - ����ԋ敪�B<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g�B
     * @@return boolean
     * @@roseuid 40A9A5A90180
     */
    protected boolean isDesignateExecutedStatus(String l_strExecutedStatus, IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "isDesignateExecutedStatus(String, IfoOrderUnit)";

        log.entering(STR_METHOD_NAME);

        boolean l_blnReturn = false; ////�Ԋ҂̒l

        if (l_strExecutedStatus == null)
        {
            //����.����ԋ敪��null�̏ꍇ
            log.debug("����.����ԋ敪��null�̏ꍇ");
            //true��Ԃ��B
            l_blnReturn = true;
        }
        else
        {
            //����.����ԋ敪��null�̏ꍇ
            String l_strExecType = WEB3IfoDataAdapter.getExecStatusType(l_orderUnit);
            l_blnReturn = l_strExecType.equals(l_strExecutedStatus);
        }

        log.debug("is�w������ = " + l_blnReturn);

        log.exiting(STR_METHOD_NAME);

        return l_blnReturn;

    }

    /**
     * (get�o����܂Œ����P��FromFirstToLast)<BR>
     * �w�肳�ꂽ�u�[��܂Œ����v��u�o����܂Œ����v�̒����P�ʃI�u�W�F�N�g�ɑ΂���A<BR>
     * �������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g�̈ꗗ���擾����B<BR>
     * <BR>
     * (1)�p�����[�^.�����P��.���񒍕��̒����P��ID == 0�@@<BR>
     * �@@�܂��́A�p�����[�^.�����P��.���񒍕��̒����P��ID == null�̏ꍇ<BR>
     * �@@�i�������Ŗ��J�z��Ԃ̏ꍇ�j<BR>
     * <BR>
     * �@@�p�����[�^.�����P�ʂ�z��ɃZ�b�g���ĕԋp����B<BR>
     * <BR>
     * (2)�������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g�����L���o�����ɂĎ擾����B <BR>
     * <BR>
     * �@@�@@�@@�����o������<BR>
     * �@@�@@�@@�@@�@@���񒍕��̒����P��ID == �p�����[�^.�����P��.���񒍕��̒����P��ID�@@<BR>
     * �@@�@@�@@�@@�@@�܂��́A�����P��ID == �p�����[�^.�����P��.���񒍕��̒����P��ID(*) <BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*)������.���񒍕��̒����P��ID�ɂ́A0���Z�b�g����Ă���ׁB <BR>
     * <BR>
     * (3)�擾���������P�ʃI�u�W�F�N�g���쐬�������ɏ����Ń\�[�g���A�z��ɂ��ĕԋp����B<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@return IfoOrderUnit[]
     * @@roseuid 40A9A5A901AE
     */
    protected IfoOrderUnit[] getCarriedOrderUnitFromFirstToLast(IfoOrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCarriedOrderUnitFromFirstToLast(IfoOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�������`�ŐV�̒����܂ł̒����P�ʃI�u�W�F�N�g���擾����B
        List l_lisRecords = null;
        IfoOrderUnit[] l_orderUnits = null;
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        if (l_orderUnitRow.getFirstOrderUnitId() == 0
            || l_orderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_orderUnits = new IfoOrderUnit[1];
            l_orderUnits[0] = l_orderUnit;
            return l_orderUnits;
        }
        try
        {

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = "order_unit_id=? or first_order_unit_id=?";
            Object l_objWhere[] =
                new Object[] {
                    new Long(l_orderUnitRow.getFirstOrderUnitId()),
                    new Long(l_orderUnitRow.getFirstOrderUnitId())};
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    "created_timestamp asc",
                    null,
                    l_objWhere);
            l_orderUnits = new IfoOrderUnit[l_lisRecords.size()];

           for (int i = 0;i < l_lisRecords.size();i++)
           {
               IfoOrderUnitRow l_orderUnitRowNew = (IfoOrderUnitRow)l_lisRecords.get(i);
               l_orderUnits[i] = (IfoOrderUnit)futOrderManager.getOrderUnit(l_orderUnitRowNew.getOrderUnitId());
           }
        }
        catch(NotFoundException l_nfe)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME, l_nfe);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);            
        }
        catch (DataException l_de)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME, l_de);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnits;
    }

    /** 
     * (is��������\)<BR>
     * ����������\�ł��邩�𔻒肷��B<BR>
     * ��������\�ł���ꍇtrue���A�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�敨�������Ɖ�T�[�r�X�jis��������\�v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isChangeCancelEnable(WEB3GentradeSubAccount l_subAccount, IfoOrderUnit l_orderUnit)                              
        throws WEB3BaseException                             
    {                                
        final String STR_METHOD_NAME =                               
            "isChangeCancelEnable(WEB3GentradeSubAccount, IfoOrderUnit)";                                
                                
        log.entering(STR_METHOD_NAME);                               

        IfoOrderUnitRow l_orderUnitRow =                             
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();                              

        //�����}�l�[�W��.getOrder(�����P��.����ID)                                
        Order l_order = null;                                
        try                              
        {                                
            l_order = futOrderManager.getOrder(l_orderUnit.getOrderId());                             
        }                                
        catch (NotFoundException l_nfE)                              
        {                                
            log.error(getClass().getName() + "." + STR_METHOD_NAME,l_nfE);                               
            throw new WEB3SystemLayerException(                              
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,                             
                STR_METHOD_NAME,                             
                l_nfE.getMessage(),                              
                l_nfE);                              
        }                                
                                
        //�����}�l�[�W��.validate���������\���(���� : ����)                              
        boolean l_blnOrderChange = true;                             
        try                              
        {                                
            futOrderManager.validateOrderChangePossibleStatus(l_order);                               
        }                                
        catch (WEB3BaseException l_wbe)                              
        {                                
            l_blnOrderChange = false;                                
        }                                
                                
        //�����}�l�[�W��.validate��������\���(���� : ����)                              
        boolean l_blnOrderCancel = true;                             
        try                              
        {                                
            futOrderManager.validateOrderCancelPossibleStatus(l_order);                               
        }                                
        catch (WEB3BaseException l_wbe)                              
        {                                
            l_blnOrderCancel = false;                                
        }                                
                                
        //�B��������\��ԃ`�F�b�N                              
        if (l_blnOrderChange == false && l_blnOrderCancel == false)                              
        {                                
            log.debug("��������\�`�F�b�N�F������������s��");                                
            log.exiting(STR_METHOD_NAME);                                
            return false;                                
        }                                

        //�����̒����^����̎�t���\�Ȏ��ԑт����肷��
        //validate�ǌ���������t�\(�����^�C�v :
        //  ProductTypeEnum, �敨�^�I�v�V�����敪 : String, ���X : ���X, ����敪 : String, ������ : Date)
        //[�w�肷�����]
        //�����^�C�v�F�@@�����P��.�����^�C�v
        //�敨�^�I�v�V�����敪�F�@@�����P��.�敨�^�I�v�V�����敪
        //���X�F�@@�⏕����.get�戵�X()
        //����敪�F�@@�����P��.����敪
        //�������F�@@�����P��.������
        try
        {
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_orderUnitRow.getProductType(),
                l_orderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_orderUnitRow.getSessionType(),
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
        }
        catch (WEB3BaseException l_wbe)
        {
            log.debug("��������\�`�F�b�N�F�s��ǌ� ��������s����");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�E����\�ڋq�`�F�b�N                                
        WEB3GentradeOrderValidator l_gentradeOrderValidator = null;                              
        l_gentradeOrderValidator = (WEB3GentradeOrderValidator)this.finApp.getCommonOrderValidator();                                
                                        
        OrderValidationResult l_validationResult =                               
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);                             
                                
        if(l_validationResult.getProcessingResult().isFailedResult())                                
        {                                
            log.debug("��������\�`�F�b�N�F����s�ڋq");                              
            log.exiting(STR_METHOD_NAME);                                
            return false;                                
        }                                
                                
        //�S�`�F�b�NOK�̏ꍇ                             
        log.exiting(STR_METHOD_NAME);                                
        return true;                             
    }                                  
    /**
     * (getPR�w����敪)<BR>
     * ������ʂ��烌�X�|���X�ɕҏW����PR�w�p�̎���敪�̃R�[�h�𔻒肵�A�ԋp����B <BR>
     *<BR>
     * �߂�l��PR�w�p�̎���敪�R�[�h�F <BR>
     * 3�F�V�K���������@@4�F�V�K���������@@5�F�����ԍϒ���(���ԍ�)�@@6�F�����ԍϒ���(���ԍ�) <BR>
     *<BR>
     *�E�p�����[�^.������ʁ�"601"�i�敨�V�K���������j�̏ꍇ�A"3"(�V�K��������)��Ԃ��B<BR> 
     *<BR>
     *�E�p�����[�^.������ʁ�"602"�i�敨�V�K���������j�̏ꍇ�A"4"(�V�K��������)��Ԃ��B<BR> 
     *<BR>
     *�E�p�����[�^.������ʁ�"604"�i�敨�����ԍϒ����j�̏ꍇ�A"5"(�����ԍϒ���(���ԍ�))��Ԃ��B <BR>
     *<BR>
     *�E�p�����[�^.������ʁ�"603"�i�敨�����ԍϒ����j�̏ꍇ�A"6"(�����ԍϒ���(���ԍ�))��Ԃ��B<BR> 
     *<BR>
     *�E�p�����[�^.������ʂ���L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     * @@return String
     * @@roseuid 40A36C160227
     */

    protected String getPRLevelTradeTypeDiv(OrderTypeEnum l_orderTypeEnum) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPRLevelTradeTypeDiv(OrderTypeEnum l_orderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        String l_prTradeTypeDiv = "";
        if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.OPEN_LONG_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.OPEN_SHORT_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.CLOSE_SELL_CONTRACT;
        }
        else if (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderTypeEnum))
        {
            l_prTradeTypeDiv = WEB3IfoTradeTypeDef.CLOSE_BUY_CONTRACT;
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01439,
                STR_METHOD_NAME);            
        }
        log.exiting(STR_METHOD_NAME);
        return l_prTradeTypeDiv;
    }
}
@
