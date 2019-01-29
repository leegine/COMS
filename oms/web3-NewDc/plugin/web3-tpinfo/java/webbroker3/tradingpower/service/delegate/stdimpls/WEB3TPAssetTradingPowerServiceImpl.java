head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetTradingPowerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���Y�]�͏���ʕ\���T�[�r�XImpl�N���X(WEB3TPAssetTradingPowerServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 Revision History : 2007/08/07 �g�E�N�|(���u) ���f��No.120
 Revision History : 2007/08/07 �g�E�N�|(���u) ���f��No.153
 Revision History : 2008/01/28 �����Q(���u) ���f��No.250
 Revision History : 2008/10/09 ����(���u) ���f��No.312�ANo.313
 Revision History : 2008/10/20 ����(���u) ���f��No.336
 Revision History : 2008/10/20 ������(���u) ���f��No.330�ANo.331�ANo.332
 Revesion History : 2008/11/26 ���� (���u) ���f��No.376
 Revesion History : 2010/01/29 ���g (���u) ���f��No.453
 */
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.define.WEB3CurrencyCodeDef;
import webbroker3.common.define.WEB3TradingStopDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3ForeignPositionContract;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPAdddepositInfo;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPCalcResult;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.WEB3TPTradingPowerSettlementService;
import webbroker3.tradingpower.data.TpAssetHistoryRow;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPClearUpDivDef;
import webbroker3.tradingpower.define.WEB3TPDepositDivDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketStateDivDef;
import webbroker3.tradingpower.define.WEB3TPOrderdMarketCodeListDef;
import webbroker3.tradingpower.define.WEB3TPSecuredLoanSecAccOpenDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationResponse;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryRequest;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryResponse;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryUnit;
import webbroker3.tradingpower.message.WEB3TPAssetRequest;
import webbroker3.tradingpower.message.WEB3TPAssetResponse;
import webbroker3.tradingpower.message.WEB3TPAssetUnit;
import webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerRequest;
import webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerResponse;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailResponse;
import webbroker3.tradingpower.message.WEB3TPFirstAdditionalInfo;
import webbroker3.tradingpower.message.WEB3TPMarginMaintenanceTradingPowerUnit;
import webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailResponse;
import webbroker3.tradingpower.message.WEB3TPPaymentAcceptRequest;
import webbroker3.tradingpower.message.WEB3TPPaymentAcceptResponse;
import webbroker3.tradingpower.message.WEB3TPPaymentAcceptUnit;
import webbroker3.tradingpower.message.WEB3TPSecondAdditionalInfo;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationInfo;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationResponse;
import webbroker3.tradingpower.message.WEB3TPTradingPowerRequest;
import webbroker3.tradingpower.message.WEB3TPTradingPowerResponse;
import webbroker3.tradingpower.message.WEB3TPTradingPowerUnit;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceRequest;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceResponse;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUnit;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUseQuoteRequest;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUseQuoteResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.tradingpower.service.delegate.WEB3TPTradingPowerClientRequestService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���Y�]�͏���ʕ\���T�[�r�XImpl)<BR>
 * ���Y�]�͏���ʕ\���T�[�r�X�����N���X�B<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPAssetTradingPowerServiceImpl extends WEB3TPTradingPowerClientRequestService
        implements
            WEB3TPAssetTradingPowerService
{

    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPAssetTradingPowerServiceImpl.class);

    /**
     * (�f�o�b�Oison)
     */
    private static boolean DBG = log.ison();

    /**
     * @@roseuid 41B6817601E3
     */
    public WEB3TPAssetTradingPowerServiceImpl()
    {
    }

    /**
     * (execute) <BR>
     * <BR>
     * ���V�[�P���X�}�u�i���Y�]�͏���ʕ\���T�[�r�X�jexecute�v�Q��<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(METHOD_NAME);

        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //�a�莑�Y���
        if(l_request instanceof WEB3TPAssetRequest)
        {
            return createAssetResponse((WEB3TPAssetRequest)l_request);
        }
        //����]�͉��
        else if(l_request instanceof WEB3TPTradingPowerRequest)
        {
            return createTradingPowerResponse((WEB3TPTradingPowerRequest)l_request);
        }
        //���v���������]�͎��Z���
        else if(l_request instanceof WEB3TPDayTradeTradingPowerRequest)
        {
            return createDayTradeTradingPowerResponse((WEB3TPDayTradeTradingPowerRequest)l_request);
        }
        //�]�͐���(�����v�Z)���
        else if(l_request instanceof WEB3TPTransitionReferenceUseQuoteRequest)
        {
            return createTransitionReferenceUseQuoteResponse((WEB3TPTransitionReferenceUseQuoteRequest)l_request);
        }
        //�]�͐��ډ��
        else if(l_request instanceof WEB3TPTransitionReferenceRequest)
        {
            return createTransitionReferenceResponse((WEB3TPTransitionReferenceRequest)l_request);
        }
        //�o����t�󋵉��
        else if(l_request instanceof WEB3TPPaymentAcceptRequest)
        {
            return createPaymentStatusResponse((WEB3TPPaymentAcceptRequest)l_request);
        }
        //���������t�]�͏ڍ׉��
        else if(l_request instanceof WEB3TPEquityTradingPowerDetailRequest)
        {
            return createEquityTradingPowerDetailResponse((WEB3TPEquityTradingPowerDetailRequest)l_request);
        }
        //�M�p�V�K���]�͏ڍ׉��
        else if(l_request instanceof WEB3TPMarginTradingPowerDetailRequest)
        {
            return createMarginTradingPowerDetailResponse((WEB3TPMarginTradingPowerDetailRequest)l_request);
        }
        //create���Y�]���z�������
        else if(l_request instanceof WEB3TPAssetHistoryRequest)
        {
            return createAssertHistory((WEB3TPAssetHistoryRequest)l_request);
        }
        //create�s�����������
        else if (l_request instanceof WEB3TPShortfallGenerationRequest)
        {
            return createShortfallGenerationScreen((WEB3TPShortfallGenerationRequest)l_request);
        }
        //create�Ǐؔ������
        else if (l_request instanceof WEB3TPAdditionalGenerationRequest)
        {
            return createAdditionalGenerationScreen((WEB3TPAdditionalGenerationRequest)l_request);
        }
        else
        {
            log.error("�s���ȃ��N�G�X�g�ł��B");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, "execute()");
        }
    }

    /**
     * (create�a�莑�Y���)<BR>
     * �a�莑�Y��ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate�a�莑�Y��ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPAssetResponse
     * @@roseuid 41B66DB2009B
     */
    protected WEB3TPAssetResponse createAssetResponse(WEB3TPAssetRequest l_request)
            throws WEB3BaseException
    {

        //���X�|���X�擾
        WEB3TPAssetResponse l_response = (WEB3TPAssetResponse)l_request.createResponse();

        //�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //�ڋq�擾
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createAssetResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createAssetResponse",
                    dne);
        }

        //����]�̓T�[�r�X
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //�O�ݎc�����擾
        WEB3ForeignPositionContract l_foreignPositionContract = null;

        //�O�ݎc�����(�h��)�擾
        l_foreignPositionContract =
            l_tradingPowerService.getForeignPositionContract(l_subAccount, WEB3CurrencyCodeDef.A0);

        //get�O�ݎc��(T+0)
        Double l_accountBalanceDollarT0 =
            l_foreignPositionContract.getForeignPositionBalance(WEB3TPSpecifiedPointDef.T_0);
        //get�O�ݎc��(T+5)
        Double l_accountBalanceDollarT5 =
            l_foreignPositionContract.getForeignPositionBalance(WEB3TPSpecifiedPointDef.T_5);

        //�O�ݎc�����(���[��)�擾
        l_foreignPositionContract =
            l_tradingPowerService.getForeignPositionContract(l_subAccount, WEB3CurrencyCodeDef.Z4);

        //get�O�ݎc��(T+0)
        Double l_accountBalanceEuroT0 =
            l_foreignPositionContract.getForeignPositionBalance(WEB3TPSpecifiedPointDef.T_0);
        //get�O�ݎc��(T+5)
        Double l_accountBalanceEuroT5 =
            l_foreignPositionContract.getForeignPositionBalance(WEB3TPSpecifiedPointDef.T_5);

        //�M�p�����J�ݍς݂�
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͌v�Z����
        WEB3TPCalcCondition l_condition = null;

        //�a�莑�Y����(��c�p)����
        WEB3TPAssetUnit l_assetUnit1 = new WEB3TPAssetUnit();
        //�a�莑�Y����(��c�p)����
        WEB3TPAssetUnit l_assetUnit2 = new WEB3TPAssetUnit();

        //�]�͌v�Z����ID
        long l_calcResultId;

        //�����ڋq
        if(!l_isMarginAccountEstablishd)
        {
            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�����ڋq>�擾
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount);

            //�]�͌v�Z�����擾
            l_condition = l_calcEquity.getCalcCondition();

            //�]�͌v�Z���ʏڍ�Params<�����ڋq>�擾
            TpCalcResultEquityDetailParams l_equityDetailParams = l_calcEquity.getCalcResultDetailEquity();

            //�]�͌v�Z����ID�擾
            l_calcResultId = l_equityDetailParams.getCalcResultEquityId();

            //�a����c��(T+0)�擾
            double l_dblAccountBalanceT_0 = l_calcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_0);
            //�a����c��(T+5)�擾
            double l_dblAccountBalanceT_5 = l_calcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_5);
            //�������ϑ��(T+0)�擾
            double l_dblTodayExecutedAmountT_0 = l_calcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_0);
            //�������ϑ��(T+5)�擾
            double l_dblTodayExecutedAmountT_5 = l_calcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_5);
            //�����]���z<��c>�擾
            double l_dblEquityAssetDelivered = l_equityDetailParams.getEquityAssetDelivered();
            //�����]���z<��c>�擾
            double l_dblEquityAssetExecuted = l_equityDetailParams.getEquityAssetExecuted();
            //�����~�j����<��c>�擾
            double l_dblMiniStockAssetDelivered = l_equityDetailParams.getMiniStockAssetDelivered();
            //�����~�j����<��c>�擾
            double l_dblMiniStockAssetExecuted = l_equityDetailParams.getMiniStockAssetExecuted();
            //�ݐϓ����]���z�擾<��c>
            double l_dblRuitoAssetDelivered = l_equityDetailParams.getRuitoAssetDelivered();
            //�ݐϓ����]���z�擾<��c>
            double l_dblRuitoAssetExecuted = l_equityDetailParams.getRuitoAssetExecuted();
            //�����M���]���z<��c>�擾
            double l_dblMutualFundAssetDelivered = l_equityDetailParams.getMutualFundAssetDelivered();
            //�����M���]���z<��c>�擾
            double l_dblMutualFundAssetExecuted = l_equityDetailParams.getMutualFundAssetExecuted();
            //���]���z<��c>�擾
            double l_dblBondAssetDelivered = l_equityDetailParams.getBondAssetDelivered();
            //���]���z<��c>�擾
            double l_dblBondAssetExecuted = l_equityDetailParams.getBondAssetExecuted();
            //�O�������]���z<��c>�擾
            double l_dblForeignEquityAssetDelivered = l_equityDetailParams.getForeignEquityAssetDelivered();
            //�O�������]���z<��c>�擾
            double l_dblForeignEquityAssetExecuted = l_equityDetailParams.getForeignEquityAssetExecuted();

            //�a�莑�Y����(��c�p)�̃v���p�e�B�Z�b�g 
            l_assetUnit1.clearUpDiv = WEB3TPClearUpDivDef.DELIVERD;//���Z�ϋ敪 �� 0�F��n�� 
            l_assetUnit1.accountBalance = format(l_dblAccountBalanceT_0
                    - l_dblTodayExecutedAmountT_0);//�a���
            l_assetUnit1.equityAsset = format(l_dblEquityAssetDelivered);//�����]���z
            l_assetUnit1.mstkAsset = format(l_dblMiniStockAssetDelivered);//�����~�j�����]���z
            l_assetUnit1.mutualAsset = format(l_dblMutualFundAssetDelivered);//�����M���]���z
            l_assetUnit1.ruitoAsset = format(l_dblRuitoAssetDelivered);//�ݐϓ����]��
            l_assetUnit1.bondAsset = format(l_dblBondAssetDelivered);//���]���z
            l_assetUnit1.feqAsset = format(l_dblForeignEquityAssetDelivered);//�O�������]���z(��c)
            l_assetUnit1.totalAsset = format(l_dblEquityAssetDelivered
                    + l_dblMiniStockAssetDelivered
                    + l_dblRuitoAssetDelivered
                    + l_dblMutualFundAssetDelivered
                    + l_dblBondAssetDelivered
                    + l_dblForeignEquityAssetDelivered);//���z�z

            //�a���<�h��>
            //get�O�ݎc��<�h��>(T+0)��null�̏ꍇ
            if (l_accountBalanceDollarT0 == null)
            {
                l_assetUnit1.accountBalanceDollar = null;
            }
            //get�O�ݎc��<�h��>(T+0)��null�ȊO�̏ꍇ
            else
            {
                l_assetUnit1.accountBalanceDollar = format(l_accountBalanceDollarT0.doubleValue());
            }

            //�a���<���[��>
            //get�O�ݎc��<���[��>(T+0)��null�̏ꍇ
            if (l_accountBalanceEuroT0 == null)
            {
                l_assetUnit1.accountBalanceEuro = null;
            }
            //get�O�ݎc��<���[��>(T+0)��null�ȊO�̏ꍇ
            else
            {
                l_assetUnit1.accountBalanceEuro = format(l_accountBalanceEuroT0.doubleValue());
            }

            //�a�莑�Y����(��c�p)�̃v���p�e�B�Z�b�g
            l_assetUnit2.clearUpDiv = WEB3TPClearUpDivDef.EXECUTED;//���Z�ϋ敪 �� 1�F����n���܂�
            l_assetUnit2.accountBalance = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_0);//�a���
            l_assetUnit2.accountBalanceDay = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_5);//�a���<�����U���ݕ��܂�>
            l_assetUnit2.equityAsset = format(l_dblEquityAssetExecuted);//�����]���z�ݒ�
            l_assetUnit2.mstkAsset = format(l_dblMiniStockAssetExecuted);//�����~�j�����]���z
            l_assetUnit2.mutualAsset = format(l_dblMutualFundAssetExecuted);//�����M���]���z
            l_assetUnit2.ruitoAsset = format(l_dblRuitoAssetExecuted);//�ݐϓ����]��
            l_assetUnit2.bondAsset = format(l_dblBondAssetExecuted);//���]���z
            l_assetUnit2.feqAsset = format(l_dblForeignEquityAssetExecuted);//�O�������]���z(��c)
            l_assetUnit2.totalAsset = format(l_dblEquityAssetExecuted
                    + l_dblMiniStockAssetExecuted
                    + l_dblRuitoAssetExecuted
                    + l_dblMutualFundAssetExecuted
                    + l_dblBondAssetExecuted
                    + l_dblForeignEquityAssetExecuted);//���z�z

            //�a���<�h��>
            //get�O�ݎc��<�h��>(T+5)��null�̏ꍇ
            if (l_accountBalanceDollarT5 == null)
            {
                l_assetUnit2.accountBalanceDollar = null;
            }
            //get�O�ݎc��<�h��>(T+5)��null�ȊO�̏ꍇ
            else
            {
                l_assetUnit2.accountBalanceDollar = format(l_accountBalanceDollarT5.doubleValue());
            }

            //�a���<���[��>
            //get�O�ݎc��<���[��>(T+5)��null�̏ꍇ
            if (l_accountBalanceEuroT5 == null)
            {
                l_assetUnit2.accountBalanceEuro = null;
            }
            //get�O�ݎc��<���[��>(T+5)��null�ȊO�̏ꍇ
            else
            {
                l_assetUnit2.accountBalanceEuro = format(l_accountBalanceEuroT5.doubleValue());
            }
        }
        //�M�p�ڋq
        else
        {

            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�M�p�ڋq>�擾
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount);

            //�]�͌v�Z�����擾
            l_condition = l_calcMargin.getCalcCondition();

            //�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>�擾
            TpCalcResultMarginDetailParams l_marginDetailParams = l_calcMargin.getCalcResultDetailMargin();

            //�]�͌v�Z����ID
            l_calcResultId = l_marginDetailParams.getCalcResultMarginId();

            //�a����c��(T+0)�擾
            double l_dblAccountBalanceT_0 = l_calcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_0);
            //�a����c��(T+5)�擾
            double l_dblAccountBalanceT_5 = l_calcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_5);
            //�������ϑ��(T+0)�擾
            double l_dblTodayExecutedAmountT_0 = l_calcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_0);
            //�������ϑ��(T+5)�擾
            double l_dblTodayExecutedAmountT_5 = l_calcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_5);
            //�����]���z<��c>�擾
            double l_dblEquityAssetDelivered = l_marginDetailParams.getEquityAssetDelivered();
            //�����]���z<��c>�擾
            double l_dblEquityAssetExecuted = l_marginDetailParams.getEquityAssetExecuted();
            //�����~�j����<��c>�擾
            double l_dblMiniStockAssetDelivered = l_marginDetailParams.getMiniStockAssetDelivered();
            //�����~�j����<��c>�擾
            double l_dblMiniStockAssetExecuted = l_marginDetailParams.getMiniStockAssetExecuted();
            //�ݐϓ����]���z�擾<��c>
            double l_dblRuitoAssetDelivered = l_marginDetailParams.getRuitoAssetDelivered();
            //�ݐϓ����]���z�擾<��c>
            double l_dblRuitoAssetExecuted = l_marginDetailParams.getRuitoAssetExecuted();
            //�����M���]���z<��c>�擾
            double l_dblMutualFundAssetDelivered = l_marginDetailParams.getMutualFundAssetDelivered();
            //�����M���]���z<��c>�擾
            double l_dblMutualFundAssetExecuted = l_marginDetailParams.getMutualFundAssetExecuted();
            //���]���z<��c>�擾
            double l_dblBondAssetDelivered = l_marginDetailParams.getBondAssetDelivered();
            //���]���z<��c>�擾
            double l_dblBondAssetExecuted = l_marginDetailParams.getBondAssetExecuted();
            //�O�������]���z<��c>�擾
            double l_dblForeignEquityAssetDelivered = l_marginDetailParams.getForeignEquityAssetDelivered();
            //�O�������]���z<��c>�擾
            double l_dblForeignEquityAssetExecuted = l_marginDetailParams.getForeignEquityAssetExecuted();

            //�a�莑�Y����(��c�p)�̃v���p�e�B�Z�b�g
            l_assetUnit1.clearUpDiv = WEB3TPClearUpDivDef.DELIVERD;//���Z�ϋ敪 �� 0�F��n�� 
            l_assetUnit1.accountBalance = format(l_dblAccountBalanceT_0
                    - l_dblTodayExecutedAmountT_0);//�a���
            l_assetUnit1.equityAsset = format(l_dblEquityAssetDelivered);//�����]���z
            l_assetUnit1.mstkAsset = format(l_dblMiniStockAssetDelivered);//�����~�j�����]���z
            l_assetUnit1.mutualAsset = format(l_dblMutualFundAssetDelivered);//�����M���]���z
            l_assetUnit1.ruitoAsset = format(l_dblRuitoAssetDelivered);//�ݐϓ����]��
            l_assetUnit1.bondAsset = format(l_dblBondAssetDelivered);//���]���z
            l_assetUnit1.feqAsset = format(l_dblForeignEquityAssetDelivered);//�O�������]���z(��c)
            l_assetUnit1.totalAsset = format(l_dblEquityAssetDelivered
                    + l_dblMiniStockAssetDelivered
                    + l_dblRuitoAssetDelivered
                    + l_dblMutualFundAssetDelivered
                    + l_dblBondAssetDelivered
                    + l_dblForeignEquityAssetDelivered);//���v�z

            //�a���<�h��>
            //get�O�ݎc��<�h��>(T+0)��null�̏ꍇ
            if (l_accountBalanceDollarT0 == null)
            {
                l_assetUnit1.accountBalanceDollar = null;
            }
            //get�O�ݎc��<�h��>(T+0)��null�ȊO�̏ꍇ
            else
            {
                l_assetUnit1.accountBalanceDollar = format(l_accountBalanceDollarT0.doubleValue());
            }

            //�a���<���[��>
            //get�O�ݎc��<���[��>(T+0)��null�̏ꍇ
            if (l_accountBalanceEuroT0 == null)
            {
                l_assetUnit1.accountBalanceEuro = null;
            }
            //get�O�ݎc��<���[��>(T+0)��null�ȊO�̏ꍇ
            else
            {
                l_assetUnit1.accountBalanceEuro = format(l_accountBalanceEuroT0.doubleValue());
            }

            //�a�莑�Y����(��c�p)�̃v���p�e�B�Z�b�g
            l_assetUnit2.clearUpDiv = WEB3TPClearUpDivDef.EXECUTED;//���Z�ϋ敪 �� 1�F����n���܂�
            l_assetUnit2.accountBalance = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_0);//�a���
            l_assetUnit2.accountBalanceDay = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_5);//�a���<�����U���ݕ��܂�>
            l_assetUnit2.accountBalance = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_0);//�a���
            l_assetUnit2.accountBalanceDay = format(l_dblAccountBalanceT_5
                    - l_dblTodayExecutedAmountT_5);//�a���<�����U���ݕ��܂�>
            l_assetUnit2.equityAsset = format(l_dblEquityAssetExecuted);//�����]���z�ݒ�
            l_assetUnit2.mstkAsset = format(l_dblMiniStockAssetExecuted);//�����~�j�����]���z
            l_assetUnit2.mutualAsset = format(l_dblMutualFundAssetExecuted);//�����M���]���z
            l_assetUnit2.ruitoAsset = format(l_dblRuitoAssetExecuted);//�ݐϓ����]��
            l_assetUnit2.bondAsset = format(l_dblBondAssetExecuted);//���]���z
            l_assetUnit2.feqAsset = format(l_dblForeignEquityAssetExecuted);//�O�������]���z(��c)
            l_assetUnit2.totalAsset = format(l_dblEquityAssetExecuted
                    + l_dblMiniStockAssetExecuted
                    + l_dblRuitoAssetExecuted
                    + l_dblMutualFundAssetExecuted
                    + l_dblBondAssetExecuted
                    + l_dblForeignEquityAssetExecuted);//���v�z

            //�a���<�h��>
            //get�O�ݎc��<�h��>(T+5)��null�̏ꍇ
            if (l_accountBalanceDollarT5 == null)
            {
                l_assetUnit2.accountBalanceDollar = null;
            }
            //get�O�ݎc��<�h��>(T+5)��null�ȊO�̏ꍇ
            else
            {
                l_assetUnit2.accountBalanceDollar = format(l_accountBalanceDollarT5.doubleValue());
            }

            //�a���<���[��>
            //get�O�ݎc��<���[��>(T+5)��null�̏ꍇ
            if (l_accountBalanceEuroT5 == null)
            {
                l_assetUnit2.accountBalanceEuro = null;
            }
            //get�O�ݎc��<���[��>(T+5)��null�ȊO�̏ꍇ
            else
            {
                l_assetUnit2.accountBalanceEuro = format(l_accountBalanceEuroT5.doubleValue());
            }
        }

        //�c�Ɠ�(T+0)�擾
        Date l_bizDate = l_condition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //���X�|���X�Ƀv���p�e�B�Z�b�g
        l_response.calcResultId = String.valueOf(l_calcResultId);//�]�͌v�Z����ID
        l_response.bizDate = l_bizDate;//���t
        l_response.assetUnits = new WEB3TPAssetUnit[2];//�a������׈ꗗ
        l_response.assetUnits[0] = l_assetUnit1;//�a�������<��c>
        l_response.assetUnits[1] = l_assetUnit2;//�a�������<��c>

        //���X�|���X�ԋp
        return l_response;
    }

    /**
     * (create����]�͉��)<BR>
     * ����]�͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate����]�͉�ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPTradingPowerResponse
     * @@roseuid 41B67E1B0241
     */
    protected WEB3TPTradingPowerResponse createTradingPowerResponse(
            WEB3TPTradingPowerRequest l_request) throws WEB3SystemLayerException
    {

        //���X�|���X�擾
        WEB3TPTradingPowerResponse l_response = (WEB3TPTradingPowerResponse)l_request.createResponse();

        //�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //�ڋq�擾
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTradingPowerResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTradingPowerResponse",
                    dne);
        }

        //�M�p�����J�ݍς݂�
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�]�͌v�Z����ID�擾
        long l_lngCalcResultId;

        //����]�͖��׈ꗗ
        WEB3TPTradingPowerUnit[] l_units = new WEB3TPTradingPowerUnit[6];

        //�]�͌v�Z����
        WEB3TPCalcCondition l_condition = null;

        //�����ڋq
        if(!l_isMarginAccountEstablishd)
        {

            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�����ڋq>�擾
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount);

            //�]�͌v�Z����<�����ڋq>Params�擾
            TpCalcResultEquityParams l_equityParams = l_calcEquity.getCalcResultEquity();

            //�]�͌v�Z�����擾
            l_condition = l_calcEquity.getCalcCondition();

            //�]�͌v�Z����ID�擾
            l_lngCalcResultId = l_equityParams.getCalcResultEquityId();

            for(int i = 0; i < 6; i++)
            {

                //T+n�擾
                Date l_datBizDate = l_condition.getBizDate(i);

                //�������t�\�z�擾
                double l_dblEquityTradingPower = Math.max(
                        0,
                        l_calcEquity.calcAppliedEquityTradingPower(i).tradingPower);
                //���M���t�\�z�擾
                double l_dblFundTradingPower = Math.max(
                        0,
                        l_calcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.MF_BUY, i).tradingPower);
                //���̑����i���t�\�z�擾
                double l_dblOtherTradingPower = Math.max(
                        0,
                        l_calcEquity.calcAppliedOtherTradingPower(null, i).tradingPower);
                //�o���\�z
                double l_dblPaymentTradingPower = Math.max(
                        0,
                        l_calcEquity.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT, i).tradingPower);

                //�c�Ɠ�(�������t�\�z�D�K�p��)���擾
                Date l_datEquityBuyApplyDate = l_condition.getBizDate(l_calcEquity.calcAppliedEquityTradingPower(i).appliedPoint);

                //����]�͖��׃��j�b�g
                WEB3TPTradingPowerUnit l_unit = new WEB3TPTradingPowerUnit();

                //���j�b�g�Ƀv���p�e�B�Z�b�g
                l_unit.bizDate = l_datBizDate;//���t
                l_unit.equityTradingPower = format(l_dblEquityTradingPower);//���������t�]��
                l_unit.mutualTradingPower = format(l_dblFundTradingPower);//���M���t�]��
                l_unit.otherTradingPower = format(l_dblOtherTradingPower);//���̑����i���t�]��
                l_unit.paymentTradingPower = format(l_dblPaymentTradingPower);//�o���]��         
                l_unit.equityBuyApplyDate = l_datEquityBuyApplyDate;//�]�͓K�p��<���������t�]��>

                //�ꗗ�ɐݒ�
                l_units[i] = l_unit;
            }

        }
        //�M�p�ڋq
        else
        {
            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�M�p�ڋq>�擾
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount);

            //�]�͌v�Z����<�M�p�ڋq>Params�擾
            TpCalcResultMarginParams l_marginParams = l_calcMargin.getCalcResultMargin();

            //�]�͌v�Z�����擾
            l_condition = l_calcMargin.getCalcCondition();

            //�]�͌v�Z����ID�擾
            l_lngCalcResultId = l_marginParams.getCalcResultMarginId();

            for(int i = 0; i < 6; i++)
            {

                //T+n�擾
                Date l_datBizDate = l_condition.getBizDate(i);

                //�������t�\�z�擾
                double l_dblEquityTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedEquityTradingPower(i).tradingPower);
                //�M�p�V�K���\�z
                double l_dblMarginTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedMarginTradingPower(i).tradingPower);
                //�M�p�����\�z
                double l_dblActualReceiptTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedActualReceiptTradingPower(i).tradingPower);
                //���M���t�\�z�擾
                double l_dblFundTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedOtherTradingPower(OrderTypeEnum.MF_BUY, i).tradingPower);
                //���̑����i���t�\�z�擾
                double l_dblOtherTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedOtherTradingPower(null, i).tradingPower);
                //�o���\�z�擾
                double l_dblPaymentTradingPower = 0;

                if (i == 1)
                {
                    try
                    {
                        l_dblPaymentTradingPower =
                            l_service.getPaymentTradingPowerAioCashoutInput(l_subAccount, l_datBizDate);
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        log.error(l_ex.getMessage(), l_ex);
                        throw new WEB3BaseRuntimeException(
                            l_ex.getErrorInfo(),
                            getClass().getName() + ".createTradingPowerResponse",
                            l_ex);
                    }
                }
                else
                {
                    l_dblPaymentTradingPower = Math.max(
                        0,
                        l_calcMargin.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT, i).tradingPower);
                }
                //�ۏ؋��a�����擾
                double l_dblMarginDepositRate = l_calcMargin.calcAppliedMarginDepositRate(i).tradingPower;

                //�c�Ɠ�(�������t�\�z�D�K�p��)���擾
                Date l_datEquityBuyApplyDate = l_condition.getBizDate(l_calcMargin.calcAppliedEquityTradingPower(i).appliedPoint);
                //�c�Ɠ�(�M�p�V�K���\�z�D�K�p��)���擾
                Date l_datMarginApplyDate = l_condition.getBizDate(l_calcMargin.calcAppliedMarginTradingPower(i).appliedPoint);

                //����]�͖��׃��j�b�g
                WEB3TPTradingPowerUnit l_unit = new WEB3TPTradingPowerUnit();

                //���X�|���X�Ƀv���p�e�B�Z�b�g
                l_unit.bizDate = l_datBizDate;//���t
                l_unit.equityTradingPower = format(l_dblEquityTradingPower);//���������t�]��
                l_unit.marginTradingPower = format(l_dblMarginTradingPower);//�M�p�V�K���]��
                l_unit.swapLongTradingPower = format(l_dblActualReceiptTradingPower);//�M�p�����]��
                l_unit.mutualTradingPower = format(l_dblFundTradingPower);//���M���t�]��
                l_unit.otherTradingPower = format(l_dblOtherTradingPower);//���̑����i���t�]��
                l_unit.paymentTradingPower = format(l_dblPaymentTradingPower);//�o���]��

                //�ۏ؋��a����=������l
                if(l_dblMarginDepositRate == Double.POSITIVE_INFINITY)
                {
                    l_unit.marginCollateralRate = null;//�ۏ؋��a����                         
                }
                else
                {
                    l_unit.marginCollateralRate = format(l_dblMarginDepositRate);//�ۏ؋��a����                         
                }

                l_unit.equityBuyApplyDate = l_datEquityBuyApplyDate;//�]�͓K�p��<���������t�]��>
                l_unit.marginApplyDate = l_datMarginApplyDate;//�]�͓K�p��<�M�p�V�K���]��>

                //�ꗗ�ɐݒ�
                l_units[i] = l_unit;

            }

        }

        /*
         * ���X�|���X�ݒ�
         */
        //�]�͌v�Z����ID
        l_response.calcResultId = String.valueOf(l_lngCalcResultId);
        //����]�͖��׈ꗗ
        l_response.tradingPowerUnits = l_units;

        /*
         * ����������n��
         */
        //�]�͌v�Z���<�������t�^�M�p����>
        int l_intBasePoint = l_condition.getEquityBasePoint();
        //�]�͌v�Z���<�������t�^�M�p����>��Date�^�ɕϊ�
        Date l_datBasePoint = l_condition.getBizDate(l_intBasePoint);
        //���X�|���X�ɗ]�͌v�Z���<�������t�^�M�p����>�iDate�^�j���Z�b�g
        l_response.equityDeliveryDate = l_datBasePoint;

        /*
         * �M�p�V�K�����
         */
        //�]�͌v�Z���<�M�p�V�K��>
        int l_intMarginBasePoint = l_condition.getMarginBasePoint();
        //�]�͌v�Z���<�M�p�V�K��>��Date�^�ɕϊ�
        Date l_datMarginBasePoint = l_condition.getBizDate(l_intMarginBasePoint);
        //���X�|���X�ɗ]�͌v�Z���<�M�p�V�K��>�iDate�^�j���Z�b�g
        l_response.marginBaseDate = l_datMarginBasePoint;

        /*
         * �~�j����n��
         */
        //�]�͌v�Z���<�~�j��>
        int l_intMstkBasePoint = l_condition.getMstkBasePoint();
        //�]�͌v�Z���<�~�j��>��Date�^�ɕϊ�
        Date l_datMstkBasePoint = l_condition.getBizDate(l_intMstkBasePoint);
        //���X�|���X�ɗ]�͌v�Z���<�~�j��>�iDate�^�j���Z�b�g
        l_response.mstkDeliveryDate = l_datMstkBasePoint;

        /*
         * �I�v�V������n��
         */
        //�]�͌v�Z���<�I�v�V�����V�K��>
        int l_intOptionBasePoint = l_condition.getOptionBasePoint();
        //�]�͌v�Z���<�I�v�V�����V�K��>��Date�^�ɕϊ�
        Date l_datOptionBasePoint = l_condition.getBizDate(l_intOptionBasePoint);
        //���X�|���X�ɗ]�͌v�Z���<�I�v�V�����V�K��>�iDate�^�j���Z�b�g
        l_response.optionsDeliveryDate = l_datOptionBasePoint;

        /*
         * ���M��n��
         */
        //�]�͌v�Z���<���M>
        int l_intFundBasePoint = l_condition.getFundBasePoint();
        //�]�͌v�Z���<���M>��Date�^�ɕϊ�
        Date l_datFundBasePoint = l_condition.getBizDate(l_intFundBasePoint);
        //���X�|���X�ɗ]�͌v�Z���<���M>�iDate�^�j���Z�b�g
        l_response.mutualDeliveryDate = l_datFundBasePoint;

        /*
         * �ݓ���n��
         */
        //�]�͌v�Z���<�ݓ�>
        int l_intRuitoBasePoint = l_condition.getRuitoBasePoint();
        //�]�͌v�Z���<�ݓ�>��Date�^�ɕϊ�
        Date l_datruitoBasePoint = l_condition.getBizDate(l_intRuitoBasePoint);
        //���X�|���X�ɗ]�͌v�Z���<�ݓ�>�iDate�^�j���Z�b�g
        l_response.ruitoDeliveryDate = l_datruitoBasePoint;

        /*
         * IPO���
         */
        //�]�͌v�Z���<IPO>
        int l_intIpoBasePoint = l_condition.getIpoBasePoint();
        //�]�͌v�Z���<IPO>��Date�^�ɕϊ�
        Date l_datIpoBasePoint = l_condition.getBizDate(l_intIpoBasePoint);
        //���X�|���X�ɗ]�͌v�Z���<IPO>�iDate�^�j���Z�b�g
        l_response.ipoBaseDate = l_datIpoBasePoint;

        /*
         * ����������n��
         */
        //�]�͌v�Z���<��������>
        int l_intFeqBasePoint = l_condition.getFeqBasePoint();
        //�]�͌v�Z���<��������>��Date�^�ɕϊ�
        Date l_datFeqBasePoint = l_condition.getBizDate(l_intFeqBasePoint);
        //���X�|���X�ɗ]�͌v�Z���<��������>�iDate�^�j���Z�b�g
        l_response.feqDeliveryDate = l_datFeqBasePoint;

        /*
         * �o����n��
         */
        //�]�͌v�Z���<�o��>
        int l_intPaymentBasePoint = l_condition.getPaymentBasePoint();
        //�]�͌v�Z���<�o��>��Date�^�ɕϊ�
        Date l_datPaymentBasePoint = l_condition.getBizDate(l_intPaymentBasePoint);
        //���X�|���X�ɗ]�͌v�Z���<�o��>�iDate�^�j���Z�b�g
        l_response.aioDeliveryDate = l_datPaymentBasePoint;

        //���X�|���X�ԋp
        return l_response;
    }

    /**
     * (create���v���������]�͎��Z���)<BR>
     * ���v���������]�͎��Z��ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate���v���������]�͎��Z��ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerResponse
     * @@roseuid 41B67EA40241
     */
    protected WEB3TPDayTradeTradingPowerResponse createDayTradeTradingPowerResponse(
            WEB3TPDayTradeTradingPowerRequest l_request) throws WEB3BaseException
    {

        //���X�|���X���擾
        WEB3TPDayTradeTradingPowerResponse l_response = (WEB3TPDayTradeTradingPowerResponse)l_request.createResponse();

        //���N�G�X�g�������R�[�h���擾
        String l_strProductCode = l_request.productCode;

        //�����R�[�h��NULL�̏ꍇ�A�����I��
        if(l_strProductCode == null)
        {
            return l_response;
        }

        //�⏕�������擾
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //�،���ЃR�[�h�擾
        String l_institutionCode = l_subAccount.getInstitution().getInstitutionCode();

        //��������
        EqtypeProductRow l_eqtypeProduct = null;
        try
        {
            //���������擾
            l_eqtypeProduct = EqtypeProductDao.findRowByInstitutionCodeProductCode(
                    l_institutionCode,
                    l_strProductCode);
        }
        catch(DataFindException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }

        if(l_eqtypeProduct == null)
        {
            if(DBG)
            {
                log.debug("���������}�X�^�ɊY�����郌�R�[�h������܂���B");
            }
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }

        //����
        ProductRow l_product = null;
        try
        {
            //�����擾
            l_product = ProductDao.findRowByPk(l_eqtypeProduct.getProductId());
        }
        catch(DataFindException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }

        if(l_product == null)
        {
            if(DBG)
            {
                log.debug("�����}�X�^�ɊY�����郌�R�[�h������܂���B");
            }
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createDayTradeTradingPowerResponse");
        }

        //�������擾
        String l_strProductName = l_product.getStandardName();

        //�������擾
        Date l_orderDate = l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        if(DBG)
        {
            log.debug("������ = " + l_orderDate);
        }

        //�D�揇�s�ꃊ�X�g�擾
        List l_lisMarket = getMarketList(l_subAccount.getInstitution());

        //�������ID
        long l_lngTradedProductID;

        //��n��
        Date l_datDevidendRightDate = null;
        //�s��ID
        long l_lngMarketId = 0;
        //�����P�ʎ擾
        double l_dblLotSize;

        //���������Ώۖ����t���O
        boolean l_isTodayDepFundReg;

        //get�������
        Object l_tradedProduct = getTradedProduct(l_product, l_orderDate, l_lisMarket);
        if(l_tradedProduct instanceof TradedProductRow)
        {
            //�������Row
            TradedProductRow l_tradedProductRow = (TradedProductRow)l_tradedProduct;
            //�������ID�擾
            l_lngTradedProductID = l_tradedProductRow.getTradedProductId();
            //��n���擾
            l_datDevidendRightDate = l_tradedProductRow.getDailyDeliveryDate();
            //�s��ID�擾
            l_lngMarketId = l_tradedProductRow.getMarketId();
        }
        else
        {
            //�������UpdqRow
            TradedProductUpdqRow l_tradedProductUpdqRow = (TradedProductUpdqRow)l_tradedProduct;
            //�������ID�擾
            l_lngTradedProductID = l_tradedProductUpdqRow.getTradedProductId();
            //��n���擾
            l_datDevidendRightDate = l_tradedProductUpdqRow.getDailyDeliveryDate();
            //�s��ID�擾
            l_lngMarketId = l_tradedProductUpdqRow.getMarketId();
        }

        //get��������
        Object l_eqtypeTradedProduct = getEqtypeTradedProduct(l_lngTradedProductID, l_orderDate);
        if(l_eqtypeTradedProduct instanceof EqtypeTradedProductRow)
        {
            //�����������Row
            EqtypeTradedProductRow l_eqtypeTradedProductRow = (EqtypeTradedProductRow)l_eqtypeTradedProduct;
            //�����P�ʎ擾
            l_dblLotSize = l_eqtypeTradedProductRow.getLotSize();
            // �������������t���O�擾
            if(BooleanEnum.TRUE.equals(l_eqtypeTradedProductRow.getTodayDepFundReg()))
            {
                l_isTodayDepFundReg = true;
            }
            else
            {
                l_isTodayDepFundReg = false;
            }
        }
        else
        {
            //�����������UpdqRow
            EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow = (EqtypeTradedProductUpdqRow)l_eqtypeTradedProduct;
            //�����P�ʎ擾
            l_dblLotSize = l_eqtypeTradedProductUpdqRow.getLotSize();
            //�������������t���O�擾
            if(BooleanEnum.TRUE.equals(l_eqtypeTradedProductUpdqRow.getTodayDepFundReg()))
            {
                l_isTodayDepFundReg = true;
            }
            else
            {
                l_isTodayDepFundReg = false;
            }
        }

        //�������ώ���]�̓T�[�r�X�擾
        WEB3TPTradingPowerSettlementService l_service = (WEB3TPTradingPowerSettlementService)Services.getService(WEB3TPTradingPowerSettlementService.class);

        //�������ϔ��t�\�z�擾
        if(DBG)
        {
            log.debug("CALL get�������ϔ��t�\�z");
            log.debug("�@@��n�� = " + l_datDevidendRightDate);
            log.debug("�@@����ID = " + l_product.getProductId());
            log.debug("�@@�������������t���O = " + l_isTodayDepFundReg);
        }
        double l_dblDayTradeEquityTradingPower = l_service.getBuyOrderPossibleAmount(
                l_subAccount,
                l_datDevidendRightDate,
                l_product.getProductId(),
                l_isTodayDepFundReg);

        //�������ϔ��t�\���ʎ擾
        if(DBG)
        {
            log.debug("CALL get�������ϔ��t�\����");
            log.debug("�@@��n�� = " + l_datDevidendRightDate);
            log.debug("�@@����ID = " + l_product.getProductId());
            log.debug("�@@�s��ID = " + l_lngMarketId);
            log.debug("�@@�����P�� = " + l_dblLotSize);
        }
        double l_sellPossQtyResult = l_service.getSellOrderPossibleQuantity(
                l_subAccount,
                l_datDevidendRightDate,
                l_product.getProductId(),
                l_lngMarketId,
                0,
                l_dblLotSize);

        //���X�|���X�̃v���p�e�B�Z�b�g
        l_response.productCode = l_strProductCode;//�����R�[�h
        l_response.productName = l_strProductName;//������
        l_response.dayTradeEquityTradingPower = format(l_dblDayTradeEquityTradingPower);//�w��������t�]��
        l_response.dayTradeEquitySellPossQuantity = format(l_sellPossQtyResult);//�w��������t�\����

        //���X�|���X��ԋp
        return l_response;
    }

    /**
     * (create�]�͐��ډ��)<BR>
     * �]�͐��ډ�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate�]�͐��ډ�ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPTransitionReferenceResponse
     * @@roseuid 41B67FC802BE
     */
    protected WEB3TPTransitionReferenceResponse createTransitionReferenceResponse(
            WEB3TPTransitionReferenceRequest l_request) throws WEB3BaseException
    {

        //���X�|���X�擾
        WEB3TPTransitionReferenceResponse l_response = (WEB3TPTransitionReferenceResponse)l_request.createResponse();

        //�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //�ڋq�擾
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTransitionReferenceResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTransitionReferenceResponse",
                    dne);
        }

        //�M�p�����J�ݍς݂�
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq
        if(!l_isMarginAccountEstablishd)
        {

            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�����ڋq>�擾
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount);

            //create�]�͐��ږ���<�����ڋq>
            createTransitionReferenceEquity(l_calcEquity, l_response);

        }
        //�M�p�ڋq
        else
        {

            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�M�p�ڋq>�擾
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount);

            //create�]�͐��ږ���<�M�p�ڋq>
            createTransitionReferenceMargin(l_calcMargin, l_response);

        }

        /*
         * �l�􂢏�ԋ敪���Z�b�g
         */
        //��ЃR�[�h
        String l_strInstCode = l_subAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h
        String l_strBranCode = l_subAccount.getWeb3GenBranch().getBranchCode();

        //�l�􂢏�ԋ敪
        String l_strMarkToMarketStateDiv = this.getMarkToMarketStateDiv(
                l_strInstCode,
                l_strBranCode);
        //�l�􂢏�ԋ敪�����X�|���X�ɃZ�b�g
        l_response.markToMarketStateDiv = l_strMarkToMarketStateDiv;

        //���X�|���X�ԋp
        return l_response;
    }

    /**
     * (create�]�͐���(�����v�Z)���)<BR>
     * <BR>
     * ���V�[�P���X�}�u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate�]�͐���(�����v�Z)��ʁv�Q��<BR>
     * <BR>
     * @@param l_request
     * @@return WEB3TPTransitionReferenceUseQuoteResponse
     */
    protected WEB3TPTransitionReferenceUseQuoteResponse createTransitionReferenceUseQuoteResponse(
            WEB3TPTransitionReferenceUseQuoteRequest l_request) throws WEB3BaseException
    {

        //���X�|���X�擾
        WEB3TPTransitionReferenceUseQuoteResponse l_response = (WEB3TPTransitionReferenceUseQuoteResponse)l_request.createResponse();

        //�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //�ڋq�擾
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTransitionReferenceUseQuoteResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createTransitionReferenceUseQuoteResponse",
                    dne);
        }

        //�M�p�����J�ݍς݂�
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq
        if(!l_isMarginAccountEstablishd)
        {

            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�����ڋq>�擾
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquityQuote(l_subAccount);

            //create�]�͐��ږ���<�����ڋq>
            createTransitionReferenceEquity(l_calcEquity, l_response);

        }
        //�M�p�ڋq
        else
        {

            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�M�p�ڋq>�擾
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMarginQuote(l_subAccount);

            //create�]�͐��ږ���<�M�p�ڋq>
            createTransitionReferenceMargin(l_calcMargin, l_response);

        }

        //���X�|���X�ԋp
        return l_response;
    }

    /**
     * (create�o����t�󋵉��)<BR>
     * �o����t�󋵉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate�o����t�󋵉�ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPPaymentStatusResponse
     * @@roseuid 41B933E20110
     */
    protected WEB3TPPaymentAcceptResponse createPaymentStatusResponse(
            WEB3TPPaymentAcceptRequest l_request) throws WEB3BaseException
    {
        //���X�|���X�擾
        WEB3TPPaymentAcceptResponse l_response = (WEB3TPPaymentAcceptResponse)l_request.createResponse();

        //�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //�ڋq�擾
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createPaymentStatusResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createPaymentStatusResponse",
                    dne);
        }

        //�M�p�����J�ݍς݂�
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�o����t�󋵖��׈ꗗ
        WEB3TPPaymentAcceptUnit[] l_paymentAcceptUnits = new WEB3TPPaymentAcceptUnit[6];

        //�]�͌v�Z����ID
        long l_lngCalcResultId;

        //�����ڋq
        if(!l_isMarginAccountEstablishd)
        {

            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�����ڋq>�擾
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount);

            //�]�͌v�Z����Detail<�����ڋq>Params�擾
            TpCalcResultEquityDetailParams l_detailEquity = l_calcEquity.getCalcResultDetailEquity();

            //�]�͌v�Z�����擾
            WEB3TPCalcCondition l_condition = l_calcEquity.getCalcCondition();

            //�]�͌v�Z����ID�擾
            l_lngCalcResultId = l_detailEquity.getCalcResultEquityId();

            //T0�`T+5
            for(int i = 0; i < 6; i++)
            {
                //�c�Ɠ�(T+n)�擾
                Date l_bizDate = l_condition.getBizDate(i);

                //�o���z<�w���>(T+n)�擾
                double l_dblPaymentAmountDesignate = getPaymentAmountDesignate(l_detailEquity, i);

                //�o����t�󋵖��׃C���X�^���X����
                WEB3TPPaymentAcceptUnit l_paymentAcceptUnit = new WEB3TPPaymentAcceptUnit();

                //���j�b�g�̃v���p�e�B�Z�b�g
                l_paymentAcceptUnit.bizDate = l_bizDate;//���t
                l_paymentAcceptUnit.paymentAmount = format(l_dblPaymentAmountDesignate);//�o���z

                //�ꗗ�ɒǉ�
                l_paymentAcceptUnits[i] = l_paymentAcceptUnit;

            }

        }
        //�M�p�ڋq
        else
        {

            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //���Y�]�͏��<�M�p�ڋq>�擾
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount);

            //�]�͌v�Z����Detail<�M�p�ڋq>Params�擾
            TpCalcResultMarginDetailParams l_detailMargin = l_calcMargin.getCalcResultDetailMargin();

            //�]�͌v�Z�����擾
            WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

            //�]�͌v�Z����ID�擾
            l_lngCalcResultId = l_detailMargin.getCalcResultMarginId();

            //T0�`T+5
            for(int i = 0; i < 6; i++)
            {
                //�c�Ɠ�(T+n)�擾
                Date l_bizDate = l_condition.getBizDate(i);

                //�o���z<�w���>(T+n)�擾
                double l_dblPaymentAmountDesignate = getPaymentAmountDesignate(l_detailMargin, i);

                //�o����t�󋵖��׃C���X�^���X����
                WEB3TPPaymentAcceptUnit l_paymentAcceptUnit = new WEB3TPPaymentAcceptUnit();

                //���j�b�g�̃v���p�e�B�Z�b�g
                l_paymentAcceptUnit.bizDate = l_bizDate;//���t
                l_paymentAcceptUnit.paymentAmount = format(l_dblPaymentAmountDesignate);//�o���z

                //�ꗗ�ɒǉ�
                l_paymentAcceptUnits[i] = l_paymentAcceptUnit;

            }

        }

        //���X�|���X�̃v���p�e�B�Z�b�g
        l_response.calcResultId = String.valueOf(l_lngCalcResultId);//�]�͌v�Z����ID
        l_response.paymentAcceptUnits = l_paymentAcceptUnits;//�o����t�󋵈ꗗ

        //���X�|���X�ԋp
        return l_response;
    }

    /**
     * (create���������t�]�͏ڍ׉��)<BR>
     * ���������t�]�͏ڍ׉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate���������t�]�͏ڍ׉�ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailResponse
     * @@roseuid 41B680320128
     */
    protected WEB3TPEquityTradingPowerDetailResponse createEquityTradingPowerDetailResponse(
            WEB3TPEquityTradingPowerDetailRequest l_request) throws WEB3BaseException
    {

        //���X�|���X�擾
        WEB3TPEquityTradingPowerDetailResponse l_response = (WEB3TPEquityTradingPowerDetailResponse)l_request.createResponse();

        //�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //�ڋq�擾
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createEquityTradingPowerDetailResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createEquityTradingPowerDetailResponse",
                    dne);
        }

        //�M�p�����J�ݍς݂�
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�M�p�ڋq�łȂ��ꍇ�A�G���[
        if(!l_isMarginAccountEstablishd)
        {
            log.error("�w�肳�ꂽ����ID�ɑΉ�����ڋq�́A�M�p�ڋq�ł͂���܂���B");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "createEquityTradingPowerDetailResponse");
        }

        //�]�͍X�V����ID�擾
        long l_lngResultId = Long.parseLong(l_request.calcResultId);

        //����]�̓T�[�r�X
        WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //���Y�]�͏��<�M�p�ڋq>�擾
        WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_lngResultId);

        //���t�擾
        Date l_bizDate = l_request.bizDate;

        //�]�͌v�Z�����擾
        WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

        //�w����擾
        int l_intSpecifiedPoint = l_condition.calcSpecifiedPoint(l_bizDate);

        //�a����c��(�w���)�擾
        double l_dblAccountBalance1 = l_calcMargin.getAccountBalance(l_intSpecifiedPoint);
        //�a����c��(�w���-1)�擾
        double l_dblAccountBalance2 = 0d;
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblAccountBalance2 = l_calcMargin.getAccountBalance(l_intSpecifiedPoint - 1);
        }
        //�������ϑ��(�w���)�擾
        double l_dblTodayExecutedAmount1 = l_calcMargin.getTodayExecutedAmount(l_intSpecifiedPoint);
        //�������ϑ��(�w���-1)�擾
        double l_dblTodayExecutedAmount2 = 0d;
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblTodayExecutedAmount2 = l_calcMargin.getTodayExecutedAmount(l_intSpecifiedPoint - 1);
        }
        //�����������(�w���)�擾
        double l_dblTodayUnexecutedAmount = l_calcMargin.getTodayUnexecutedAmount(l_intSpecifiedPoint);
        //���v��S����(�w���)�擾
        double l_dblDayTradeRestraint = l_calcMargin.getDayTradeRestraint(l_intSpecifiedPoint);
        //���̑��S����(�w���)�擾
        double l_dblOtherRestraint = l_calcMargin.getOtherRestraint(l_intSpecifiedPoint);
        //�����ۏ؋�(�w���)�擾
        double l_dblMarginAccountBalance = l_calcMargin.calcMarginAccountBalance(l_intSpecifiedPoint);
        //��p�،��]���z(�w���)�擾
        double l_dblSubstituteSecurityAsset = l_calcMargin.getSubstituteSecurityAsset(l_intSpecifiedPoint);
        //�����ό��ʕ]�����v(�w���)�擾
        double l_dblContractAssetProfitLoss = l_calcMargin.getContractAssetProfitLoss(l_intSpecifiedPoint);
        //���ʏ��o��擾
        double l_dblContractTotalCost = l_calcMargin.getContractTotalCost(l_intSpecifiedPoint);
        //����n���ʌ��ϑ�(�w���)�擾
        double l_dblUndeliContractLoss = l_calcMargin.getUndeliContractLoss(l_intSpecifiedPoint);
        //����n���ʌ��ωv(�w���)�擾
        double l_dblUndeliContractProfit = l_calcMargin.getUndeliContractProfit(l_intSpecifiedPoint);
        //����ۏ؋�(�w���)�擾
        double l_dblReceiptMarginDeposit = l_calcMargin.calcReceiptMarginDeposit(l_intSpecifiedPoint);
        //�K�v�ۏ؋�(�w���)�擾
        double l_dblMarginDeposit = l_calcMargin.getMarginDeposit(l_intSpecifiedPoint);
        //�����K�v�ۏ؋�(�w���)�擾
        double l_dblCashMarginDeposit = l_calcMargin.getCashMarginDeposit(l_intSpecifiedPoint);
        //����n���ʌ����K�v�ۏ؋�(�w���)�擾
        double l_dblUndeliCashMarginDeposit = l_calcMargin.getUndeliCashMarginDeposit(l_intSpecifiedPoint);
        //�ۏ؋��]��(�w���)�擾
        double l_dblMarginPower = l_calcMargin.calcMarginPower(l_intSpecifiedPoint);
        //�g�p�\����(�w���)�擾
        double l_dblActualAccountBalance = l_calcMargin.calcActualAccountBalance(l_intSpecifiedPoint);
        //�������t�\�z(�w���)�擾
        double l_dblEquityTradingPower = l_calcMargin.calcEquityTradingPower(l_intSpecifiedPoint);

        //���X�|���X�̃v���p�e�B�Z�b�g
        l_response.calcResultId = l_request.calcResultId;//�]�͌v�Z����ID
        l_response.bizDate = l_bizDate;//���t
        l_response.accountBalance = format(l_dblAccountBalance1 - l_dblTodayExecutedAmount1);//�a���

        //�O����
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_response.comparedPreviousDay = format((l_dblAccountBalance1 - l_dblTodayExecutedAmount1)
                    - (l_dblAccountBalance2 - l_dblTodayExecutedAmount2));
        }

        l_response.unexecutedAmount = format(l_dblTodayUnexecutedAmount);//�����[����
        l_response.dayTradeRestraint = format(l_dblDayTradeRestraint);//���v��S����
        l_response.otherRestraint = format(l_dblOtherRestraint);//���̑��S����
        l_response.cashDeposit = format(l_dblMarginAccountBalance);//�����ۏ؋�
        l_response.substituteSecurityAsset = format(l_dblSubstituteSecurityAsset);//��p�،��]���z
        l_response.contractAssetProfitLoss = format(l_dblContractAssetProfitLoss);//���ʕ]�����v
        l_response.contractTotalCost = format(l_dblContractTotalCost);//���ʏ��o��
        l_response.undeliContractLoss = format(l_dblUndeliContractLoss);//����n���ʌ��ϑ�
        l_response.undeliContractProfit = format(l_dblUndeliContractProfit);//����n���ʌ��ωv
        l_response.acceptDeposit = format(l_dblReceiptMarginDeposit);//����ۏ؋�
        l_response.marginDeposit = format(l_dblMarginDeposit);//�K�v�ۏ؋�
        l_response.cashMarginDeposit = format(l_dblCashMarginDeposit + l_dblUndeliCashMarginDeposit);//�K�v�ۏ؋�
        l_response.depositTradingPower = format(l_dblMarginPower);//�ۏ؋��]��
        l_response.actualAccountBalance = format(l_dblActualAccountBalance);//�g�p�\����
        l_response.equityApplyTradingPower = format(l_dblEquityTradingPower);//���������t�[���]��
        l_response.equityTradingPower = format(l_dblEquityTradingPower);//���������t�]��

        //���X�|���X�ԋp
        return l_response;
    }

    /**
     * (create�M�p�V�K���]�͏ڍ׉��)<BR>
     * �M�p�V�K���]�͏ڍ׉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate�M�p�V�K���]�͏ڍ׉�ʁv�Q�ƁB<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPMarginTradingPowerDetailResponse
     * @@roseuid 41B680A3004D
     */
    protected WEB3TPMarginTradingPowerDetailResponse createMarginTradingPowerDetailResponse(
            WEB3TPMarginTradingPowerDetailRequest l_request) throws WEB3BaseException
    {

        //���X�|���X�擾
        WEB3TPMarginTradingPowerDetailResponse l_response = (WEB3TPMarginTradingPowerDetailResponse)l_request.createResponse();

        //�⏕�����擾
        WEB3GentradeSubAccount l_subAccount = super.getSubAccount();

        //�ڋq�擾
        WEB3GentradeMainAccount l_account = null;
        try
        {
            l_account = new WEB3GentradeMainAccount(l_subAccount.getAccountId());
        }
        catch(DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createMarginTradingPowerDetailResponse",
                    dqe);
        }
        catch(DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "createMarginTradingPowerDetailResponse",
                    dne);
        }

        //�M�p�����J�ݍς݂�
        boolean l_isMarginAccountEstablishd = l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�M�p�ڋq�łȂ��ꍇ�A�G���[
        if(!l_isMarginAccountEstablishd)
        {
            log.error("�w�肳�ꂽ����ID�ɑΉ�����ڋq�́A�M�p�ڋq�ł͂���܂���B");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "createMarginTradingPowerDetailResponse");
        }

        //�]�͍X�V����ID�擾
        long l_lngResultId = Long.parseLong(l_request.calcResultId);

        //����]�̓T�[�r�X
        WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //���Y�]�͏��<�M�p�ڋq>�擾
        WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_lngResultId);

        //�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>�擾
        TpCalcResultMarginDetailParams l_detailMargin = l_calcMargin.getCalcResultDetailMargin();

        //���t�擾
        Date l_bizDate = l_request.bizDate;

        //�]�͌v�Z�����擾
        WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

        //�w����擾
        int l_intSpecifiedPoint = l_condition.calcSpecifiedPoint(l_bizDate);

        //�a����c��(�w���)�擾
        double l_dblAccountBalance1 = l_calcMargin.getAccountBalance(l_intSpecifiedPoint);
        //�a����Z�c��(�w���-1)�擾
        double l_dblAccountBalance2 = 0d;
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblAccountBalance2 = l_calcMargin.getAccountBalance(l_intSpecifiedPoint - 1);
        }
        //�������ϑ��(�w���)�擾
        double l_dblTodayExecutedAmount1 = l_calcMargin.getTodayExecutedAmount(l_intSpecifiedPoint);
        //�������ς���(�w���-1)�擾
        double l_dblTodayExecutedAmount2 = 0d;
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblTodayExecutedAmount2 = l_calcMargin.getTodayExecutedAmount(l_intSpecifiedPoint - 1);
        }
        //�����������(�w���)�擾
        double l_dblTodayUnexecutedAmount = l_calcMargin.getTodayUnexecutedAmount(l_intSpecifiedPoint);
        //���̑��S����(�w���)�擾
        double l_dblOtherRestraint = l_calcMargin.getOtherRestraint(l_intSpecifiedPoint);
        //�����ۏ؋�(�w���)�擾
        double l_dblMarginAccountBalance = l_calcMargin.calcMarginAccountBalance(l_intSpecifiedPoint);
        //��p�،��]���z(�w���)�擾
        double l_dblSubstituteSecurityAsset = l_calcMargin.getSubstituteSecurityAsset(l_intSpecifiedPoint);
        //��������p�،��]���z(�w���)�擾
        double l_dblUnexecSubstiSecurityAsset = getUnexecSubstiSecurityAsset(
                l_detailMargin,
                l_intSpecifiedPoint);
        //�����ۏ؋�(�w���)�擾
        double l_dblPaidMarginDeposit = l_calcMargin.calcPaidMarginDeposit(l_intSpecifiedPoint);

        //�����ό��ʕ]����(�w���)�擾
        double l_dblContractAssetLoss = getContractAssetLoss(l_detailMargin, l_intSpecifiedPoint);
        //�����ό��ʕ]���v(�w���)�擾
        double l_dblContractAssetProfit = getContractAssetProfit(l_detailMargin, l_intSpecifiedPoint);
        
        //�����ό��ʕ]�����v�擾
        double l_dblContractAssetProfitLoss = l_calcMargin.getContractAssetProfitLoss(l_intSpecifiedPoint);

        //get���萔��(int)
        //[����]
        //int = calc�w���()�̖߂�l
        double l_dblSetupFee = l_calcMargin.getSetupFee(l_intSpecifiedPoint);
        //get�����E�t������(int)
        //[����]
        //int = calc�w���()�̖߂�l
        double l_dblContractInterestLoss = l_calcMargin.getContractInterestLoss(l_intSpecifiedPoint);
        //get�����E�t�����v(int)
        //[����]
        //int = calc�w���()�̖߂�l
        double l_dblContractInterestProfit = l_calcMargin.getContractInterestProfit(l_intSpecifiedPoint);
        //get���̑����ʏ��o��(int)
        //[����]
        //int = calc�w���()�̖߂�l
        double l_dblContractOtherCost = l_calcMargin.getContractOtherCost(l_intSpecifiedPoint);

        //����n���ʌ��ϑ�(�w���)�擾
        double l_dblUndeliContractLoss = l_calcMargin.getUndeliContractLoss(l_intSpecifiedPoint);
        //����n���ʌ��ωv(�w���)�擾
        double l_dblUndeliContractProfit = l_calcMargin.getUndeliContractProfit(l_intSpecifiedPoint);
        //���ʏ��o��擾
        double l_dblContractTotalCost = l_calcMargin.getContractTotalCost(l_intSpecifiedPoint);
        //����ۏ؋�(�w���)�擾
        double l_dblReceiptMarginDeposit = l_calcMargin.calcReceiptMarginDeposit(l_intSpecifiedPoint);

        //�����ό��ʑ��(�w���)�擾
        double l_dblContractAmount = l_calcMargin.getContractAmount(l_intSpecifiedPoint);
        //���������ʑ��(�w���)�擾
        double l_dblUnexecContractAmount = getUnexecContractAmount(
                l_detailMargin,
                l_intSpecifiedPoint);
        //���v��ԍρE�������n���ʑ��(�w���)�擾
        double l_dblDayRepayContractAmount = l_calcMargin.getDayRepayContractAmount(l_intSpecifiedPoint);

        //�K�v�ۏ؋�(�w���)�擾
        double l_dblMarginDeposit = l_calcMargin.getMarginDeposit(l_intSpecifiedPoint);
        //�������K�v�ۏ؋�(�w���)�擾
        double l_dblUnexecMarginDeposit = getUnexecMarginDeposit(
                l_detailMargin,
                l_intSpecifiedPoint);
        //���v��ԍρE�������n�K�v�ۏ؋�(�w���)�擾
        double l_dblDayRepayMarginDeposit = getDayRepayMarginDeposit(
                l_detailMargin,
                l_intSpecifiedPoint);

        //�����K�v�ۏ؋�(�w���)�擾
        double l_dblCashMarginDeposit = l_calcMargin.getCashMarginDeposit(l_intSpecifiedPoint);
        //�����������K�v�ۏ؋�(�w���)�擾
        double l_dblUnexecCashMarginDeposit = getUnexecCashMarginDeposit(
                l_detailMargin,
                l_intSpecifiedPoint);
        //���v��ԍρE�������n�����K�v�ۏ؋�(�w���)�擾
        double l_dblDayRepayCashMarginDeposit = getDayRepayCashMarginDeposit(
                l_detailMargin,
                l_intSpecifiedPoint);

        //�ۏ؋��]��(�w���)�擾
        double l_dblMarginPower = l_calcMargin.calcMarginPower(l_intSpecifiedPoint);
        //�M�p�V�K���\�z(�w���)�擾
        double l_dblMarginTradingPower = l_calcMargin.calcMarginTradingPower(l_intSpecifiedPoint);
        //�ۏ؋��a����(�w���)�擾
        double l_dblMarginDepositRate1 = l_calcMargin.calcMarginDepositRate(l_intSpecifiedPoint);
        //���ʌ��ϑ�<����>
        double l_dblTodayRepayContractLoss = l_detailMargin.getTodayRepayContractLoss();
        //���ʌ��ωv<����>
        double l_dblTodayRepayContractProfit = l_detailMargin.getTodayRepayContractProfit();
        //���ʌ��ϑ�<�w���>
        double l_dblContractLossDesignate = getContractLossDesignate(
                l_detailMargin,
                l_intSpecifiedPoint);
        //���ʌ��ωv<�w���>
        double l_dblContractProfitDesignate = getContractProfitDesignate(
                l_detailMargin,
                l_intSpecifiedPoint);
        //���ό��ʑO�����i�]��<����>�擾
        double l_dblTodayRepayContractPreAsset = l_detailMargin.getTodayRepayContractPreAsset();

        //�ۏ؋����擾
        int l_intMarginDepositRate = l_condition.getMarginDepositRate();
        //�ۏ؋��ێ����擾
        int l_intMarginMentenanceRate = l_condition.getMarginMentenanceRate();
        //�@@��ۏ؋����擾
        int l_intLegalMarginDepositRate = l_condition.getLegalMarginDepositRate();
        //�Œ�K�v�ۏ؋��擾
        double l_dblMinMarginDeposit = l_condition.getMinMarginDeposit();

        //�ۏ؋��ێ��]��(�ۏ؋���)�擾
        double l_dblMarginMaintenancePower1 = l_calcMargin.calcMarginMaintenancePower(
                l_intSpecifiedPoint,
                l_intMarginDepositRate);
        //�ۏ؋��ێ��]��(�ۏ؋��ێ���)�擾
        double l_dblMarginMaintenancePower2 = l_calcMargin.calcMarginMaintenancePower(
                l_intSpecifiedPoint,
                l_intMarginMentenanceRate);
        //�ۏ؋��ێ��]��(�@@��ۏ؋���)�擾
        double l_dblMarginMaintenancePower3 = l_calcMargin.calcMarginMaintenancePower(
                l_intSpecifiedPoint,
                l_intLegalMarginDepositRate);

        //�ۏ؋��ێ��]�̓��j�b�g(�ۏ؋���)
        WEB3TPMarginMaintenanceTradingPowerUnit l_unit1 = new WEB3TPMarginMaintenanceTradingPowerUnit();
        l_unit1.marginMaintenanceRate = String.valueOf(l_intMarginDepositRate);//�ۏ؋��ێ���
        l_unit1.marginMaintenanceTradingPower = format(l_dblMarginMaintenancePower1);//�ۏ؋��ێ��]��

        //�ۏ؋��ێ��]�̓��j�b�g(�ۏ؋��ێ���)
        WEB3TPMarginMaintenanceTradingPowerUnit l_unit2 = new WEB3TPMarginMaintenanceTradingPowerUnit();
        l_unit2.marginMaintenanceRate = String.valueOf(l_intMarginMentenanceRate);//�ۏ؋��ێ���
        l_unit2.marginMaintenanceTradingPower = format(l_dblMarginMaintenancePower2);//�ۏ؋��ێ��]��

        //�ۏ؋��ێ��]�̓��j�b�g(�@@��ۏ؋���)
        WEB3TPMarginMaintenanceTradingPowerUnit l_unit3 = new WEB3TPMarginMaintenanceTradingPowerUnit();
        l_unit3.marginMaintenanceRate = String.valueOf(l_intLegalMarginDepositRate);//�ۏ؋��ێ���
        l_unit3.marginMaintenanceTradingPower = format(l_dblMarginMaintenancePower3);//�ۏ؋��ێ��]��

        WEB3TPMarginMaintenanceTradingPowerUnit[] l_units =
        {l_unit1, l_unit2, l_unit3};

        //���X�|���X�̃v���p�e�B�Z�b�g
        l_response.calcResultId = l_request.calcResultId;//�]�͌v�Z����ID
        l_response.bizDate = l_bizDate;//���t
        l_response.accountBalance = format(l_dblAccountBalance1 - l_dblTodayExecutedAmount1);//�a���

        //�O����
        if(l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0)
        {
            l_response.comparedPreviousDay = format((l_dblAccountBalance1 - l_dblTodayExecutedAmount1)
                    - (l_dblAccountBalance2 - l_dblTodayExecutedAmount2));
        }

        l_response.unexecutedAmount = format(l_dblTodayUnexecutedAmount);//�����[����
        l_response.otherRestraint = format(l_dblOtherRestraint);//���̑��S����
        l_response.cashDeposit = format(l_dblMarginAccountBalance);//�����ۏ؋�
        l_response.substituteSecurityAsset = format(l_dblSubstituteSecurityAsset
                - l_dblUnexecSubstiSecurityAsset);
        l_response.orderSubstituteSecurityAsset = format(l_dblUnexecSubstiSecurityAsset);
        l_response.guarantyDeposit = format(l_dblPaidMarginDeposit);//�����ۏ؋�
        l_response.contractAssetLoss = format(l_dblContractAssetLoss);//���ʕ]����
        l_response.contractAssetProfit = format(l_dblContractAssetProfit);//���ʕ]���v
        l_response.contractAssetProfitLoss = format(l_dblContractAssetProfitLoss);//���ʕ]�����v
        l_response.contractCommission = format(l_dblSetupFee);//���萔��
        l_response.interestFeeLoss = format(l_dblContractInterestLoss);//�����E�t�����E�݊���
        l_response.interestFeeProfit = format(l_dblContractInterestProfit);//�����E�t�����v
        l_response.otherAccruedCost = format(l_dblContractOtherCost);//���̑�������p
        l_response.contractTotalCost = format(l_dblContractTotalCost);//���ʏ��o��
        l_response.undeliContractLoss = format(l_dblUndeliContractLoss);//����n���ʌ��ϑ�
        l_response.undeliContractProfit = format(l_dblUndeliContractProfit);//����n���ʌ��ωv
        l_response.acceptDeposit = format(l_dblReceiptMarginDeposit);//����ۏ؋�
        l_response.contractAmount = format(l_dblContractAmount - l_dblUnexecContractAmount);//���������ό��ʑ��
        l_response.marginDeposit = format(l_dblMarginDeposit
                - l_dblUnexecMarginDeposit
                - l_dblDayRepayMarginDeposit);//���������ό��ʂ̕K�v�ۏ؋�
        l_response.cashMarginDeposit = format(l_dblCashMarginDeposit
                - l_dblUnexecCashMarginDeposit
                - l_dblDayRepayCashMarginDeposit);//���������ό��ʂ̌����K�v�ۏ؋�

        l_response.orderContractAmount = format(l_dblUnexecContractAmount);//���������ʑ��
        l_response.orderMarginDeposit = format(l_dblUnexecMarginDeposit);//�������K�v�ۏ؋�
        l_response.orderCashMarginDeposit = format(l_dblUnexecCashMarginDeposit);//�����������K�v�ۏ؋�

        l_response.dayRepayContractAmount = format(l_dblDayRepayContractAmount);//���v��ԍρE�������n���ʑ��
        l_response.dayRepayMarginDeposit = format(l_dblDayRepayMarginDeposit);//���v��ԍρE�������n�K�v�ۏ؋�
        l_response.dayRepayCashMarginDeposit = format(l_dblDayRepayCashMarginDeposit);//���v��ԍρE�������n�����K�v�ۏ؋�

        l_response.marginDepositTotal = format(l_dblMarginDeposit);//�K�v�ۏ؋����v       
        l_response.cashMarginDepositTotal = format(l_dblCashMarginDeposit);//�����K�v�ۏ؋����v       
        l_response.depositTradingPower = format(l_dblMarginPower);//�ۏ؋��]��
        l_response.marginTradingPower = format(l_dblMarginTradingPower);//�M�p�V�K���]��
        l_response.marginMaintenanceTradingPowerUnits = l_units;//�ۏ؋��ێ��]��
        //�ۏ؋��a����=������l
        if(l_dblMarginDepositRate1 == Double.POSITIVE_INFINITY)
        {
            l_response.marginCollateralRate = null;//�ۏ؋��a����
        }
        //�ȊO�̏ꍇ
        else
        {
            l_response.marginCollateralRate = format(l_dblMarginDepositRate1);//�ۏ؋��a����
        }
        l_response.marginDepositRate = format(l_intMarginDepositRate);//�ۏ؋���
        l_response.minMarginDeposit = format(l_dblMinMarginDeposit);//�Œ�K�v�ۏ؋�
        l_response.settleContractLossToday = format(l_dblTodayRepayContractLoss);//���ʌ��ϑ�<����>
        l_response.settleContractLossDesignatedDate = format(l_dblContractLossDesignate);//���ʌ��ϑ�<�w���>
        l_response.settleContractProfitToday = format(l_dblTodayRepayContractProfit);//���ʌ��ωv<����>
        l_response.settleContractProfitDesignatedDate = format(l_dblContractProfitDesignate);//���ʌ��ωv<�w���>
        l_response.settleContractProfitLossToday = format(l_dblTodayRepayContractProfit
                - l_dblTodayRepayContractLoss);//���ϑ��v���v<����>
        l_response.settleContractPrevDay = format(l_dblTodayRepayContractPreAsset);//���ό���<�O�����i�]��>
        l_response.settleContractProfitLossTotal = format(l_dblContractProfitDesignate
                - l_dblContractLossDesignate);//���ϑ��v�݌v<�T�Z>

        //���X�|���X�ԋp
        return l_response;
    }

    /**
     * (create�]�͐���<�����ڋq>)<BR>
     * <BR>
     * ����.���Y�]�͏��<�����ڋq>���A<BR>
     * ����.�]�͐��ډ�ʕ\�����X�|���X�ɒl���Z�b�g����B
     * <BR>
     * �V�[�P���X�}�u(���Y�]�͏���ʕ\���T�[�r�X)create�]�͐���<�����ڋq>�v�Q��<BR>
     * <BR>
     * @@param l_calcEquity ���Y�]�͏��<�����ڋq>
     * @@param l_response �]�͐��ډ�ʕ\�����X�|���X
     */
    protected void createTransitionReferenceEquity(
            WEB3TPTradingPowerCalcEquity l_calcEquity,
            WEB3TPTransitionReferenceResponse l_response)
    {
        //create�]�͐��ږ���<�����ڋq>
        WEB3TPTransitionReferenceUnit[] l_units = createTransitionReferenceUnitsEquity(l_calcEquity);

        //�]�͌v�Z����Params<�����ڋq>
        TpCalcResultEquityParams l_equityParams = l_calcEquity.getCalcResultEquity();

        //�]�͌v�Z����
        WEB3TPCalcCondition l_condition = l_calcEquity.getCalcCondition();

        //�]�͌v�Z����ID�擾
        long l_lngCalcResultId = l_equityParams.getCalcResultEquityId();

        //is�a��،��]�����擾
        boolean l_isEquityEvalDiv = l_condition.isEquityEvalDiv();

        //is�،��S�ۃ��[���敪
        boolean l_isSecuredLoanSecAccOpenDiv = l_condition.isSecuredLoanSecAccOpenDiv();

        //�K�p�������t�\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultEquity = l_calcEquity.calcAppliedEquityTradingPower();
        //���M���t�\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultFund = l_calcEquity.calcFundTradingPower();
        //�o���\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultPayment = l_calcEquity.calcPaymentTradingPower();
        //�K�p���̑����i���t�\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultOther = l_calcEquity.calcAppliedOtherTradingPower(null);

        //�c�Ɠ�(�K�p�������t�\�z�D�K�p��)�擾
        Date l_datEquityApplyDate = l_condition.getBizDate(l_calcResultEquity.appliedPoint);
        //�c�Ɠ�(���M���t�\�z�D�K�p��)�擾
        Date l_datFundApplyDate = l_condition.getBizDate(l_calcResultFund.appliedPoint);
        //�c�Ɠ�(�o���\�z�D�K�p��)�擾
        Date l_datPaymentApplyDate = l_condition.getBizDate(l_calcResultPayment.appliedPoint);
        //�c�Ɠ�(�K�p���̑����i���t�\�z�D�K�p��)�擾
        Date l_datOtherApplyDate = l_condition.getBizDate(l_calcResultOther.appliedPoint);

        /*
         * ���X�|���X�̃v���p�e�B�ɐݒ�
         */
        //�]�͌v�Z����ID
        l_response.calcResultId = String.valueOf(l_lngCalcResultId);

        //�a��،��ڋq�敪
        if(l_isEquityEvalDiv)
        {
            //�i�a��،��ڋq�j
            l_response.depositDiv = WEB3TPDepositDivDef.DEPOSIT_CUSTOMER;
        }
        else
        {
            //�i�a��،��ڋq�łȂ��j
            l_response.depositDiv = WEB3TPDepositDivDef.NOT_DEPOSIT_CUSTOMER;
        }

        //�]�͐��ږ��׈ꗗ
        l_response.transitionReferenceUnits = l_units;
        //�]�͓K�p��<���������t�]��>
        l_response.equityBuyApplyDate = l_datEquityApplyDate;
        //�]�͓K�p��<���M���t�]��>
        l_response.mutualBuyApplyDate = l_datFundApplyDate;
        //�]�͓K�p��<���̑����i���t�]��>
        l_response.otherApplyDate = l_datOtherApplyDate;
        //�]�͓K�p��<�o���]��>
        l_response.paymentApplyDate = l_datPaymentApplyDate;

        //�،��S�ۃ��[���敪
        if(l_isSecuredLoanSecAccOpenDiv)
        {
            //1�F���{
            l_response.securedLoanSecAccOpenDiv = WEB3TPSecuredLoanSecAccOpenDivDef.ENFORCEMENT;
        }
        else
        {
            //0�F�����{
            l_response.securedLoanSecAccOpenDiv = WEB3TPSecuredLoanSecAccOpenDivDef.NON_ENFORCEMENT;
        }
    }

    /**
     * (create�]�͐���<�M�p�ڋq>)<BR>
     * <BR>
     * ����.���Y�]�͏��<�M�p�ڋq>���A<BR>
     * ����.�]�͐��ډ�ʕ\�����X�|���X�ɒl���Z�b�g����B
     * <BR>
     * �V�[�P���X�}�u(���Y�]�͏���ʕ\���T�[�r�X)create�]�͐���<�M�p�ڋq>�v�Q��<BR>
     * <BR>
     * @@param l_calcMargin ���Y�]�͏��<�M�p�ڋq>
     * @@param l_response �]�͐��ډ�ʕ\�����X�|���X
     */
    protected void createTransitionReferenceMargin(
            WEB3TPTradingPowerCalcMargin l_calcMargin,
            WEB3TPTransitionReferenceResponse l_response)
    {
        //create�]�͐��ږ���<�M�p�ڋq>
        WEB3TPTransitionReferenceUnit[] l_units = createTransitionReferenceUnitsMargin(l_calcMargin);

        //�]�͌v�Z����Params<�M�p�ڋq>
        TpCalcResultMarginParams l_marginParams = l_calcMargin.getCalcResultMargin();

        //�]�͌v�Z����
        WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

        //�]�͌v�Z����ID�擾
        long l_lngCalcResultId = l_marginParams.getCalcResultMarginId();

        //�K�p�������t�\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultEquity = l_calcMargin.calcAppliedEquityTradingPower();
        //�K�p�M�p�V�K���\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultMargin = l_calcMargin.calcAppliedMarginTradingPower();
        //�K�p�M�p�����\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultActualReceipt = l_calcMargin.calcAppliedActualReceiptTradingPower();
        //���M���t�\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultFund = l_calcMargin.calcFundTradingPower();
        //�o���\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultPayment = l_calcMargin.calcPaymentTradingPower();
        //�K�p���̑����i���t�\�z�D�K�p���擾
        WEB3TPCalcResult l_calcResultOther = l_calcMargin.calcAppliedOtherTradingPower(null);
        //�K�p�ۏ؋��a�����D�K�p���擾
        WEB3TPCalcResult l_calcResultMarginDepositRate = l_calcMargin.calcAppliedMarginDepositRate();

        //�c�Ɠ�(�K�p�������t�\�z�D�K�p��)�擾
        Date l_datEquityApplyDate = l_condition.getBizDate(l_calcResultEquity.appliedPoint);
        //�c�Ɠ�(�K�p�M�p�V�K���\�z�D�K�p��)�擾
        Date l_datMarginApplyDate = l_condition.getBizDate(l_calcResultMargin.appliedPoint);
        //�c�Ɠ�(�K�p�M�p�����\�z�D�K�p��)�擾
        Date l_datActualReceiptApplyDate = l_condition.getBizDate(l_calcResultActualReceipt.appliedPoint);
        //�c�Ɠ�(���M���t�\�z�D�K�p��)�擾
        Date l_datFundApplyDate = l_condition.getBizDate(l_calcResultFund.appliedPoint);
        //�c�Ɠ�(�o���\�z�D�K�p��)�擾
        Date l_datPaymentApplyDate = l_condition.getBizDate(l_calcResultPayment.appliedPoint);
        //�c�Ɠ�(�K�p���̑����i���t�\�z�D�K�p��)�擾
        Date l_datOtherApplyDate = l_condition.getBizDate(l_calcResultOther.appliedPoint);
        //�c�Ɠ�(�K�p�ۏ؋��a�����D�K�p��)�擾
        Date l_datMarginDepositRateApplyDate = l_condition.getBizDate(l_calcResultMarginDepositRate.appliedPoint);

        /*
         * ���X�|���X�̃v���p�e�B�ɐݒ�
         */
        //�]�͌v�Z����ID
        l_response.calcResultId = String.valueOf(l_lngCalcResultId);
        //�a��،��ڋq�敪�i�a��،��ڋq�łȂ��j
        l_response.depositDiv = WEB3TPDepositDivDef.NOT_DEPOSIT_CUSTOMER;
        //�]�͐��ږ��׈ꗗ
        l_response.transitionReferenceUnits = l_units;
        //�]�͓K�p��<���������t�]��>
        l_response.equityBuyApplyDate = l_datEquityApplyDate;
        //�]�͓K�p��<�M�p�V�K���]��>
        l_response.marginApplyDate = l_datMarginApplyDate;
        //�]�͓K�p��<�M�p�����]��>
        l_response.swapLongApplyDate = l_datActualReceiptApplyDate;
        //�]�͓K�p��<���M���t�]��>
        l_response.mutualBuyApplyDate = l_datFundApplyDate;
        //�]�͓K�p��<���̑����i���t�]��>
        l_response.otherApplyDate = l_datOtherApplyDate;
        //�]�͓K�p��<�o���]��>
        l_response.paymentApplyDate = l_datPaymentApplyDate;
        //�]�͓K�p��<�ۏ؋��a����>
        l_response.marginCollateralRateApplyDate = l_datMarginDepositRateApplyDate;
        //�،��S�ۃ��[���敪�i0�F�����{�j
        l_response.securedLoanSecAccOpenDiv = WEB3TPSecuredLoanSecAccOpenDivDef.NON_ENFORCEMENT;

    }

    /**
     * (create�]�͐��ږ���<�����ڋq>)<BR>
     * create�]�͐��ږ��׏��������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate�]�͐��ږ���<�����ڋq>�v�Q�ƁB<BR>
     * <BR>
     * @@param WEB3TPTradingPowerCalcEquity
     * @@return WEB3TPTransitionReferenceUnit
     */
    protected WEB3TPTransitionReferenceUnit[] createTransitionReferenceUnitsEquity(
            WEB3TPTradingPowerCalcEquity l_calcEquity)
    {

        //�]�͌v�Z�����擾
        WEB3TPCalcCondition l_condition = l_calcEquity.getCalcCondition();

        //���ʗ��֋����ю擾
        double l_dblSpecialDebitAmount = l_condition.getSpecialDebitAmount();

        //�]�͐��ږ��׈ꗗ����
        WEB3TPTransitionReferenceUnit[] l_units = new WEB3TPTransitionReferenceUnit[6];

        for(int i = 0; i < 6; i++)
        {

            //�c�Ɠ��擾
            Date l_bizDate = l_condition.getBizDate(i);

            //�a����c��(�w���)�擾
            double l_dblAccountBalance1 = l_calcEquity.getAccountBalance(i);
            //�a����c��(�w���-1)�擾
            double l_dblAccountBalance2 = 0.0d;
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_dblAccountBalance2 = l_calcEquity.getAccountBalance(i - 1);
            }
            //�������ϑ���擾(�w���)
            double l_dblTodayExecutedAmount1 = l_calcEquity.getTodayExecutedAmount(i);
            //�������ϑ���擾(�w���-1)
            double l_dblTodayExecutedAmount2 = 0.0d;
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_dblTodayExecutedAmount2 = l_calcEquity.getTodayExecutedAmount(i - 1);
            }
            //������������擾
            double l_dblTodayUnexecutedAmount = l_calcEquity.getTodayUnexecutedAmount(i);
            //���v��S�����擾
            double l_dblDayTradeRestraint = l_calcEquity.getDayTradeRestraint(i);
            //���̑��S�����擾
            double l_dblOtherRestraint = l_calcEquity.getOtherRestraint(i);
            //�a����S�ۏo���S�����擾
            double l_dblCashDepositRestraint = l_calcEquity.getCashDepositRestraint(i);
            //�a��،��]���z�擾
            double l_dblTrustSecurityAsset = l_calcEquity.getTrustSecurityAsset(i);
            //���o�\�����擾
            double l_dblActualPaymentBalance = l_calcEquity.calcActualPaymentBalance(i);
            //���������z
            double l_dblDemandamount = l_calcEquity.calcDemandamount(i);
            //�������t�\�z�擾
            double l_dblEquityTradingPower = l_calcEquity.calcEquityTradingPower(i);
            //�������t�\�z<���v��S�����l��>�擾
            double l_dblEquityTradingPowerIncDayTrade = l_calcEquity.calcEquityTradingPowerIncDayTrade(i);
            //���̑����i���t�\�z<���v��S�����l��>�擾
            double l_dblOtherTradingPower = l_calcEquity.calcOtherTradingPowerIncDayTrade(i);
            //�o���\�z<���v��S�����l��>�擾
            double l_dblPaymentTradingPowerIncDayTrade = l_calcEquity.calcPaymentTradingPowerIncDayTrade(i);
            
            
            //�]�͐��ږ��א���
            WEB3TPTransitionReferenceUnit l_unit = new WEB3TPTransitionReferenceUnit();

            /*
             * �v���p�e�B�Z�b�g
             */
            //���t
            l_unit.bizDate = l_bizDate;
            //�a���
            l_unit.accountBalance = format(l_dblAccountBalance1 - l_dblTodayExecutedAmount1);

            //�O����
            //T+0�łȂ��ꍇ
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_unit.comparedPreviousDay = format((l_dblAccountBalance1 - l_dblTodayExecutedAmount1)
                        - (l_dblAccountBalance2 - l_dblTodayExecutedAmount2));
            }

            //�����[����
            l_unit.unexecutedAmount = format(l_dblTodayUnexecutedAmount);

            //���v��S����
            //T+0�̏ꍇ
            if(i == WEB3TPSpecifiedPointDef.T_0)
            {
                //���v��S�����Ɠ��ʗ��֋��̑傫����
                l_unit.dayTradeRestraint = format(Math.max(
                        l_dblDayTradeRestraint,
                        l_dblSpecialDebitAmount));
            }
            else
            {
                l_unit.dayTradeRestraint = format(l_dblDayTradeRestraint);
            }

            //���̑��S����
            l_unit.otherRestraint = format(l_dblOtherRestraint);
            //�a����S�ۏo���S����
            l_unit.cashDepositRestraint = format(l_dblCashDepositRestraint);
            //�،��]���z
            l_unit.securityAsset = format(l_dblTrustSecurityAsset);
            //�o���]��
            l_unit.paymentTradingPower = format(l_dblPaymentTradingPowerIncDayTrade);
            //���������z
            l_unit.payClaimAmount = format(l_dblDemandamount);
            //���������t�]��
            l_unit.equityTradingPower = format(l_dblEquityTradingPower);
            //���������t�]��<���v��S�����l��>
            l_unit.equityTradingPowerDayTrade = format(l_dblEquityTradingPowerIncDayTrade);
            //���M���t�]��
            l_unit.mutualTradingPower = format(l_dblOtherTradingPower);
            //���̑����i���t�]��
            l_unit.otherTradingPower = format(l_dblOtherTradingPower);
            //�a����s���z=ABS(MIN(0,���o�\����))
            l_unit.accountBalanceShortfall = format(Math.abs(Math.min(0, l_dblActualPaymentBalance)));

            l_units[i] = l_unit;

        }

        //�]�͐��ڈꗗ�ԋp
        return l_units;
    }

    /**
     * (create�]�͐��ږ���<�M�p�ڋq>)<BR>
     * create�]�͐��ږ��׏��������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate�]�͐��ږ���<�M�p�ڋq>�v�Q�ƁB<BR>
     * <BR>
     * @@param WEB3TPTradingPowerCalcMargin
     * @@return WEB3TPTransitionReferenceUnit
     */
    protected WEB3TPTransitionReferenceUnit[] createTransitionReferenceUnitsMargin(
            WEB3TPTradingPowerCalcMargin l_calcMargin)
    {

        //�]�͌v�Z�����擾
        WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

        //���ʏڍ�Params�擾
        TpCalcResultMarginDetailParams l_detailMargin = l_calcMargin.getCalcResultDetailMargin();

        //���ʗ��֋����ю擾
        double l_dblSpecialDebitAmount = l_condition.getSpecialDebitAmount();

        //�]�͐��ږ��׈ꗗ����
        WEB3TPTransitionReferenceUnit[] l_units = new WEB3TPTransitionReferenceUnit[6];

        for(int i = 0; i < 6; i++)
        {
            //��]�͐��ږ��א���
            WEB3TPTransitionReferenceUnit l_unit = new WEB3TPTransitionReferenceUnit();

            //�c�Ɠ��擾
            Date l_bizDate = l_condition.getBizDate(i);

            //�a����c��(�w���)�擾
            double l_dblAccountBalance1 = l_calcMargin.getAccountBalance(i);
            //�a����c��(�w���-1)�擾
            double l_dblAccountBalance2 = 0d;
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_dblAccountBalance2 = l_calcMargin.getAccountBalance(i - 1);
            }
            //�������ϑ��(�w���)�擾
            double l_dblTodayExecutedAmount1 = l_calcMargin.getTodayExecutedAmount(i);
            //�������ϑ��(�w���-1)�擾
            double l_dblTodayExecutedAmount2 = 0d;
            if(i != WEB3TPSpecifiedPointDef.T_0)
            {
                l_dblTodayExecutedAmount2 = l_calcMargin.getTodayExecutedAmount(i - 1);
            }
            //������������擾
            double l_dblTodayUnexecutedAmount = l_calcMargin.getTodayUnexecutedAmount(i);
            //���v��S�����擾
            double l_dblDayTradeRestraint = l_calcMargin.getDayTradeRestraint(i);
            //���̑��S�����擾
            double l_dblOtherRestraint = l_calcMargin.getOtherRestraint(i);
            //�����ۏ؋��擾
            double l_dblMarginAccountBalance = l_calcMargin.calcMarginAccountBalance(i);
            //��p�،��]���z�擾
            double l_dblSubstituteSecurityAsset = l_calcMargin.getSubstituteSecurityAsset(i);
            //�����ۏ؋��擾
            double l_dblPaidMarginDeposit = l_calcMargin.calcPaidMarginDeposit(i);

            //���ʕ]�����擾
            double ldblContractAssetLoss = getContractAssetLoss(l_detailMargin, i);
            //���ʕ]���v�擾
            double ldblContractAssetProfit =  getContractAssetProfit(l_detailMargin, i);

            //�����ό��ʕ]�����v�擾
            double l_dblContractAssetProfitLoss = l_calcMargin.getContractAssetProfitLoss(i);
            //���ʏ��o��擾
            double l_dblContractTotalCost = l_calcMargin.getContractTotalCost(i);
            //����n���ʌ��ϑ��擾
            double l_dblUndeliContractLoss = l_calcMargin.getUndeliContractLoss(i);
            //����n���ʌ��ωv�擾
            double l_dblUndeliContractProfit = l_calcMargin.getUndeliContractProfit(i);
            //����ۏ؋��擾
            double l_dblReceiptMarginDeposit = l_calcMargin.calcReceiptMarginDeposit(i);
            //�����ό��ʑ��
            double l_dblContractAmount = l_calcMargin.getContractAmount(i);
            //���v��ԍρE�������n���ʑ��
            double l_dblDayRepayContractAmount = l_calcMargin.getDayRepayContractAmount(i);
            //����n���ʑ��
            double l_dblUndeliContractAmount = l_calcMargin.getUndeliContractAmount(i);
            //�K�v�ۏ؋�
            double l_dblMarginDeposit = l_calcMargin.getMarginDeposit(i);
            //���v��ԍρE�������n�K�v�ۏ؋�
            double l_dblDayRepayMarginDeposit = getDayRepayMarginDeposit(l_detailMargin, i);
            //����n���ʕK�v�ۏ؋�
            double l_dblUndeliMarginDeposit = l_calcMargin.getUndeliMarginDeposit(i);
            //�����K�v�ۏ؋�
            double l_dblCashMarginDeposit = l_calcMargin.getCashMarginDeposit(i);
            //���v��ԍρE�������n�K�v�ۏ؋�
            double l_dblDayRepayCashMarginDeposit = getDayRepayCashMarginDeposit(l_detailMargin, i);
            //����n���ʕK�v�ۏ؋�
            double l_dblUndeliCashMarginDeposit = l_calcMargin.getUndeliCashMarginDeposit(i);
            //�ۏ؋��]��
            double l_dblMarginPower = l_calcMargin.calcMarginPower(i);
            //�ۏ؋����o�S����
            double l_dblMarginDrawDeposit = l_calcMargin.calcMarginDrawDeposit(i);
            //�ۏ؋����o�]��
            double l_dblMarginDrawPower = l_calcMargin.calcMarginDrawPower(i);
            //���o�\����
            double l_dblActualPaymentBalance = l_calcMargin.calcActualPaymentBalance(i);
            //�a��������]��
            double l_dblAccountBalanceDemandPower = l_calcMargin.calcAccountBalanceDemandPower(i);
            //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>
            double l_dblContractAmountDayRepay = l_calcMargin.getContractAmountDayRepay(i);
            //�ۏ؋��a����
            double l_dblMarginDepositRate = l_calcMargin.calcMarginDepositRate(i);
            //�ǏؕK�v�ۏ؋�
            double l_dblMarginCallDeposit = l_calcMargin.calcMarginCallDeposit(i);
            //�Ǐؗ]��
            double l_dblMarginCallPower = l_calcMargin.calcMarginCallPower(i);
            //�ۏ؋��ێ��]�́��ۏ؋�����
            double l_dblMarginDepositPower = l_calcMargin.calcMarginMaintenancePower(
                    i,
                    l_condition.getMarginDepositRate());
            //�ۏ؋��ێ��]�́��ۏ؋��ێ�����
            double l_dblMarginMaintenancePower = l_calcMargin.calcMarginMaintenancePower(
                    i,
                    l_condition.getMarginMentenanceRate());
            //�ۏ؋��ێ��]�́��@@��ۏ؋��ێ�����
            double l_dblLegalMarginDepositPower = l_calcMargin.calcMarginMaintenancePower(
                    i,
                    l_condition.getLegalMarginDepositRate());
            //���������z
            double l_dblDemandAmount = l_calcMargin.calcDemandamount(i);
            //�M�p�V�K���\�z
            double l_dblMarginTradingPower = l_calcMargin.calcMarginTradingPower(i);
            //�M�p�����\�z
            double l_dblActualReceiptTradingPower = l_calcMargin.calcActualReceiptTradingPower(i);
            //�M�p�����\�z<���v��S�����l��>�擾
            double l_dblActualReceiptTradingPowerIncDayTrade = l_calcMargin.calcActualReceiptTradingPowerIncDayTrade(i);
            //�������t�\�z
            double l_dblEquityTradingPower = l_calcMargin.calcEquityTradingPower(i);
            //�������t�\�z<���v��S�����l��>�擾
            double l_dblEquityTradingPowerIncDayTrade = l_calcMargin.calcEquityTradingPowerIncDayTrade(i);
            //���̑����i���t�\�z<���v��S�����l��>�擾
            double l_dblOtherTradingPower = l_calcMargin.calcOtherTradingPowerIncDayTrade(i);

            //�v���p�e�B�Z�b�g
            l_unit.bizDate = l_bizDate;//���t
            l_unit.accountBalance = format(l_dblAccountBalance1 - l_dblTodayExecutedAmount1);//�a���

            //�O����
            if(i != WEB3TPSpecifiedPointDef.T_0)//T+0�łȂ��ꍇ
            {
                l_unit.comparedPreviousDay = format((l_dblAccountBalance1 - l_dblTodayExecutedAmount1)
                        - (l_dblAccountBalance2 - l_dblTodayExecutedAmount2));//�O����                                  
            }

            l_unit.unexecutedAmount = format(l_dblTodayUnexecutedAmount);//�����[����

            //���v��S����
            if(i == WEB3TPSpecifiedPointDef.T_0)//T+0�̏ꍇ
            {
                //���v��S�����Ɠ��ʗ��֋��̑傫����
                l_unit.dayTradeRestraint = format(Math.max(
                        l_dblDayTradeRestraint,
                        l_dblSpecialDebitAmount));
            }
            else
            {
                l_unit.dayTradeRestraint = format(l_dblDayTradeRestraint);
            }

            //�ۏ؋��a����=������l
            if(l_dblMarginDepositRate == Double.POSITIVE_INFINITY)
            {
                l_unit.marginCollateralRate = null;
            }
            //�ȊO�̏ꍇ
            else
            {
                l_unit.marginCollateralRate = format(l_dblMarginDepositRate);
            }

            l_unit.otherRestraint = format(l_dblOtherRestraint);//���̑��S����
            l_unit.cashDeposit = format(l_dblMarginAccountBalance);//�����ۏ؋�
            l_unit.securityAsset = format(l_dblSubstituteSecurityAsset);//��p�،��]���z
            l_unit.guarantyDeposit = format(l_dblPaidMarginDeposit);//�����ۏ؋�
            l_unit.contractAssetProfit = format(ldblContractAssetProfit);//���ʕ]���v
            l_unit.contractAssetLoss = format(ldblContractAssetLoss);//���ʕ]����
            l_unit.contractAssetProfitLoss = format(l_dblContractAssetProfitLoss);//���ʕ]�����v
            l_unit.contractTotalCost = format(l_dblContractTotalCost);//���ʏ��o��
            l_unit.undeliContractLoss = format(l_dblUndeliContractLoss);//����n���ʌ��ϑ�
            l_unit.undeliContractProfit = format(l_dblUndeliContractProfit);//����n���ʌ��ωv
            l_unit.acceptDeposit = format(l_dblReceiptMarginDeposit);//����ۏ؋�
            l_unit.contractAmount = format(l_dblContractAmount);//���ʑ��
            l_unit.dayRepayContractAmount = format(l_dblDayRepayContractAmount);//���v��ԍρE�������n���ʑ��
            l_unit.undeliContractAmount = format(l_dblUndeliContractAmount);//����n���ʑ��
            l_unit.marginDeposit = format(l_dblMarginDeposit);//�K�v�ۏ؋�
            l_unit.dayRepayMarginDeposit = format(l_dblDayRepayMarginDeposit);//���v��ԍρE�������n�K�v�ۏ؋�
            l_unit.undeliMarginDeposit = format(l_dblUndeliMarginDeposit);//����n���ʕK�v�ۏ؋�
            l_unit.cashMarginDeposit = format(l_dblCashMarginDeposit);//�����K�v�ۏ؋�
            l_unit.dayRepayCashMarginDeposit = format(l_dblDayRepayCashMarginDeposit);//���v��ԍρE�������n�K�v�ۏ؋�
            l_unit.undeliCashMarginDeposit = format(l_dblUndeliCashMarginDeposit);//����n���ʕK�v�ۏ؋�

            l_unit.depositTradingPower = format(l_dblMarginPower);//�ۏ؋��]��
            l_unit.depositWithdrawRestraint = format(l_dblMarginDrawDeposit);//�ۏ؋����o�S����
            l_unit.depositWithdrawTradingPower = format(l_dblMarginDrawPower);//�ۏ؋����o�]��
            l_unit.paymentTradingPower = format(Math.min(
                    l_dblMarginDrawPower,
                    l_dblActualPaymentBalance));//�o���]��           
            l_unit.additionalMarginDeposit = format(l_dblMarginCallDeposit);//�ǏؕK�v�ۏ؋�
            l_unit.additionalDepositTradingPower = format(l_dblMarginCallPower);//�Ǐؗ]��            
            l_unit.marginDepositPower = format(l_dblMarginDepositPower);//�ۏ؋��ێ��]�́��ۏ؋�����
            l_unit.marginMaintenancePower = format(l_dblMarginMaintenancePower);//�ۏ؋��ێ��]�́��ۏ؋��ێ�����
            l_unit.legalMarginDepositPower = format(l_dblLegalMarginDepositPower);//�ۏ؋��ێ��]�́��@@��ۏ؋��ێ�����   
            //�a����s���z=ABS(MIN(0,�a��������]��))
            l_unit.accountBalanceShortfall = format(Math.abs(Math.min(
                    0,
                    l_dblAccountBalanceDemandPower)));
            l_unit.payClaimAmount = format(l_dblDemandAmount);//���������z
            l_unit.marginTradingPower = format(l_dblMarginTradingPower);//�M�p�V�K���]��
            l_unit.swapLongTradingPower = format(l_dblActualReceiptTradingPower);//�M�p�����]��
            l_unit.swapLongTradingPowerDayTrade = format(l_dblActualReceiptTradingPowerIncDayTrade);//�M�p�����]��<���v��S�����l��>            
            l_unit.equityTradingPower = format(l_dblEquityTradingPower);//���������t�]��
            l_unit.equityTradingPowerDayTrade = format(l_dblEquityTradingPowerIncDayTrade);//���������t�]��<���v��S�����l��>
            l_unit.mutualTradingPower = format(l_dblOtherTradingPower);//���M���t�]��
            l_unit.otherTradingPower = format(l_dblOtherTradingPower);//���̑����i���t�]��

            //�ꗗ�ɒǉ�
            l_units[i] = l_unit;

        }

        //�]�͐��ڈꗗ�ԋp
        return l_units;
    }

    /**
     * (get�������)<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jget��������v�Q�ƁB<BR>
     * <BR>
     * @@param ����Row
     * @@params ������
     * @@params �D�揇�s�ꃊ�X�g�i�v�f�̌^�FWEB3GentradeMarket�j
     * @@return Object
     */
    protected Object getTradedProduct(ProductRow l_product, Date l_orderDate, List l_lisMarket)
            throws WEB3BaseException
    {

        //��������}�X�^����
        List l_lisTradedProduct = null;
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            String l_strWhere = " product_id = ? AND valid_until_biz_date = ? ";
            Object[] l_bindVars =
            {
                    new Long(l_product.getProductId()),
                    WEB3DateUtility.formatDate(l_orderDate, "yyyyMMdd")};
            l_lisTradedProduct = l_qp.doFindAllQuery(
                    TradedProductRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getTradedProduct");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getTradedProduct");
        }

        //��������}�X�^�ɊY���f�[�^������ꍇ
        if(l_lisTradedProduct != null && l_lisTradedProduct.size() != 0)
        {
            //�D��s��ID��NULL�łȂ��ꍇ
            if(!l_product.getPrimaryMarketIdIsNull())
            {
                for(int i = 0; i < l_lisTradedProduct.size(); i++)
                {
                    //�������Row
                    TradedProductRow l_tradedProdut = (TradedProductRow)l_lisTradedProduct.get(i);
                    //�D��s��ID=��������D�s��ID�̏ꍇ
                    if(l_product.getPrimaryMarketId() == l_tradedProdut.getMarketId())
                    {
                        //�������Row��ԋp
                        return l_tradedProdut;
                    }
                }
            }

            for(int i = 0; i < l_lisMarket.size(); i++)
            {
                //�s��
                WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_lisMarket.get(i);
                for(int j = 0; j < l_lisTradedProduct.size(); j++)
                {
                    //�������Row
                    TradedProductRow l_tradedProdut = (TradedProductRow)l_lisTradedProduct.get(j);
                    //�s��D�s��ID = ��������D�s��ID�̏ꍇ  
                    if(l_market.getMarketId() == l_tradedProdut.getMarketId())
                    {
                        //�������Row��ԋp
                        return l_tradedProdut;
                    }
                }
            }

        }

        //�������UPDQ����
        List l_lisTradedProductUpdq = null;
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            String l_strWhere = " product_id = ? AND valid_until_biz_date = ? ";
            Object[] l_bindVars =
            {
                    new Long(l_product.getProductId()),
                    WEB3DateUtility.formatDate(l_orderDate, "yyyyMMdd")};
            l_lisTradedProductUpdq = l_qp.doFindAllQuery(
                    TradedProductUpdqRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getTradedProduct");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getTradedProduct");
        }

        //�Y���Ȃ�
        if(l_lisTradedProductUpdq == null || l_lisTradedProductUpdq.size() == 0)
        {
            if(DBG)
            {
                log.debug("�������Updq�ɊY�����郌�R�[�h������܂���B");
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        getClass().getName() + "getTradedProduct");
            }
        }

        //�D��s��ID��NULL�łȂ��ꍇ
        if(!l_product.getPrimaryMarketIdIsNull())
        {
            for(int i = 0; i < l_lisTradedProductUpdq.size(); i++)
            {
                //�������Updq
                TradedProductUpdqRow l_tradedProdutUpdqRow = (TradedProductUpdqRow)l_lisTradedProductUpdq.get(i);
                //�D��s��ID=�������UPDQ�D�s��ID�̏ꍇ
                if(l_tradedProdutUpdqRow.getMarketId() == l_product.getPrimaryMarketId())
                {
                    //�������UPDQRow��ԋp
                    return l_tradedProdutUpdqRow;
                }
            }
        }

        for(int i = 0; i < l_lisMarket.size(); i++)
        {
            //�s��
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_lisMarket.get(i);
            for(int j = 0; j < l_lisTradedProductUpdq.size(); j++)
            {
                //�������Updq
                TradedProductUpdqRow l_tradedProdutUpdq = (TradedProductUpdqRow)l_lisTradedProductUpdq.get(j);
                //�s��D�s��ID = ��������D�s��ID �̏ꍇ
                if(l_market.getMarketId() == l_tradedProdutUpdq.getMarketId())
                {
                    //�������UpdqRow��ԋp
                    return l_tradedProdutUpdq;
                }
            }
        }

        if(DBG)
        {
            log.debug("��������A�������UPDQ�ɊY�����郌�R�[�h������܂���B");
        }
        throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                getClass().getName() + "getTradedProduct");

    }

    /**
     * (get�����������)<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jget������������v�Q�ƁB<BR>
     * <BR>
     * @@param l_lngTradedProductID�@@�������ID
     * @@params l_orderDate�@@������
     * @@return Object �����������Row �������� �����������UpdqRow
     */
    protected Object getEqtypeTradedProduct(long l_lngTradedProductID, Date l_orderDate)
            throws WEB3BaseException
    {

        //������������}�X�^����
        List l_lisEqtypeTradedProduct = null;
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            String l_strWhere = " traded_product_id = ? AND valid_until_biz_date = ? ";
            Object[] l_bindVars =
            {new Long(l_lngTradedProductID), WEB3DateUtility.formatDate(l_orderDate, "yyyyMMdd")};
            l_lisEqtypeTradedProduct = l_qp.doFindAllQuery(
                    EqtypeTradedProductRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }

        //������������}�X�^�ɊY���f�[�^�����݂���ꍇ
        if(l_lisEqtypeTradedProduct != null && l_lisEqtypeTradedProduct.size() != 0)
        {
            //�����������Row��ԋp
            return (EqtypeTradedProductRow)l_lisEqtypeTradedProduct.get(0);
        }

        //�������UPDQ����
        EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow = null;
        try
        {
            l_eqtypeTradedProductUpdqRow = EqtypeTradedProductUpdqDao.findRowByPk(
                    l_lngTradedProductID,
                    WEB3DateUtility.formatDate(l_orderDate, "yyyyMMdd"));
        }
        catch(DataFindException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }
        catch(DataNetworkException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }
        catch(DataQueryException e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + ".getEqtypeTradedProduct");
        }

        if(l_eqtypeTradedProductUpdqRow != null)
        {
            //�����������UpqqRow��ԋp
            return l_eqtypeTradedProductUpdqRow;
        }

        log.error("������������A�����������Updq�ɊY�����郌�R�[�h������܂���B");
        throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + ".getEqtypeTradedProduct");

    }

    /**
     * (get�D�揇�s�ꃊ�X�g)<BR>
     * <BR>
     * �w�肳�ꂽ�،���Ђ̗D�揇�s�ꃊ�X�g���擾����B<BR>
     * <BR>
     *[�D�揇]<BR>
     *�@@���s��R�[�h�����L�̏�
     *�@@�����@@���@@���É��@@JASDAC�@@NNM�@@�����@@�D�y<BR>
     * <BR>
     * @@param l_instituion �،����
     * @@return List �D�揇�s�ꃊ�X�g(�v�f�̌^�FWEB3GentradeMarket)
     */
    protected List getMarketList(Institution l_institution) throws WEB3BaseException
    {
        //�D�揇�}�[�P�b�g�R�[�h���X�g
        String[] l_orderdMarketCodeList = WEB3TPOrderdMarketCodeListDef.ORDERD_MARKET_CODE_LIST;

        //�D�揇�}�[�P�b�g���X�g
        List l_lisMarket = new ArrayList();

        for(int i = 0; i < l_orderdMarketCodeList.length; i++)
        {
            //�s��
            WEB3GentradeMarket l_market;
            try
            {
                l_market = new WEB3GentradeMarket(l_institution, l_orderdMarketCodeList[i]);
            }
            catch(DataFindException e)
            {
                if(DBG)
                {
                    log.debug("�Y������s�ꂪ���݂��܂���B");
                }
                continue;
            }
            catch(DataQueryException e)
            {
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        e.getMessage(),
                        getClass().getName() + "getMarketList",
                        e);
            }
            catch(DataNetworkException e)
            {
                throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        e.getMessage(),
                        getClass().getName() + "getMarketList",
                        e);
            }

            if(l_market == null)
            {
                if(DBG)
                {
                    log.debug("�Y������s�ꂪ���݂��܂���B");
                }
                continue;
            }

            //�D�揇�s�ꃊ�X�g�ɒǉ�
            l_lisMarket.add(l_market);

        }

        if(l_lisMarket.size() == 0)
        {
            log.error("�Y������s�ꂪ�ꌏ������܂���B");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "getMarketList");
        }

        //�D�揇�s�ꃊ�X�g��ԋp
        return l_lisMarket;
    }

    /**
     * (get�l�􂢏�ԋ敪) <BR>
     * ���݂̒l�􂢏�ԋ敪��ԋp����B<BR>
     * <BR>
     * �P�j�v���Z�X�Ǘ��e�[�u�����A�u������l�􂢏I���v���R�[�h���擾����B<BR>
     * <BR>
     * �@@[�����Ώۃe�[�u��]<BR>
     * �@@�@@�v���Z�X�Ǘ��e�[�u��<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�v���Z�XID�F�h0002�h<BR>
     * �@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F����.���X�R�[�h�@@<BR>
     * <BR>
     * �Q�j�v���Z�X�Ǘ��e�[�u�����A�u���������l�􂢏I���v���R�[�h���擾����B<BR>
     * <BR>
     * �@@[�����Ώۃe�[�u��]<BR>
     * �@@�@@�v���Z�X�Ǘ��e�[�u��<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�v���Z�XID�F�h0004�h<BR>
     * �@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F����.���X�R�[�h�@@<BR>
     * <BR>
     * �R�j�擾�������R�[�h���A�l�􂢏�ԋ敪��ԋp����B<BR>
     * <BR>
     * �@@[������l�􂢏I���̏ꍇ]<BR>
     * �@@�i�u������l�􂢏I���v���R�[�h != null ���� �u������l�􂢏I���v���R�[�h.�����敪 == 1�F�����ρj<BR>
     * <BR>
     * �@@�@@�ԋp�l�F2�i�F�l�􂢏I���j<BR>
     * <BR>
     * �@@[���������l�􂢏I���̏ꍇ]<BR>
     * �@@�i�u���������l�􂢏I���v���R�[�h != null ���� �u���������l�􂢏I���v���R�[�h.�����敪 == 1�F�����ρj<BR>
     * <BR>
     * �@@�@@�ԋp�l�F1�i�F�l�􂢒��j<BR>
     * <BR>
     * �@@[�ȊO�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�ԋp�l�Fnull<BR>
     * <BR>
     * @@param l_strInstCode �،���ЃR�[�h
     * @@param l_strBranCode ���X�R�[�h
     * @@return String �l�􂢏�ԋ敪
     */
    protected String getMarkToMarketStateDiv(String l_strInstCode, String l_strBranCode)
    {
        try
        {
            //�v���Z�XID
            String l_strProcessId = null;

            //������l��
            l_strProcessId = "0002";
            ProcessManagementParams l_params1 = (ProcessManagementParams)ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    l_strProcessId,
                    l_strInstCode,
                    l_strBranCode);

            //���������l��
            l_strProcessId = "0004";
            ProcessManagementParams l_params2 = (ProcessManagementParams)ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    l_strProcessId,
                    l_strInstCode,
                    l_strBranCode);

            //������l�􂢂̃��R�[�h�����݂��邩�A�����敪=1:�����ςݏꍇ
            if(l_params1 != null && l_params1.getStatus().equals("1"))
            {
                return WEB3TPMarkToMarketStateDivDef.FIXED;
            }
            //���������l�􂢂̃��R�[�h�����݂��邩�A�����敪=1:�����ςݏꍇ
            else if(l_params2 != null && l_params2.getStatus().equals("1"))
            {
                return WEB3TPMarkToMarketStateDivDef.PRELIMINARY;
            }
            //�ȊO�̏ꍇ
            else
            {
                return null;
            }
        }
        catch(DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    getClass().getName() + "getProcessManagementParams(String, String)",
                    dfe);
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "getProcessManagementParams(String, String)",
                    de);
        }
    }

    /**
     * �iget��������p�،��]���z�j <BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u��������p�،��]���z�v��ԋp����B <BR>
     * <BR>
     * �m�ԋp�l�n <BR>
     * �]�͌v�Z���ʏڍ�Params <�M�p�ڋq>.get��������p�،��]���z�iT+n�j <BR>
     * 
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getUnexecSubstiSecurityAsset(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //��������p�،��]���z
        double l_dblUnexecSubstiSecurityAsset;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //��������p�،��]���z( T + 0 )���擾����B
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //��������p�،��]���z( T + 1 )���擾����B
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //��������p�،��]���z( T + 2 )���擾����B
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //��������p�،��]���z( T + 3 )���擾����B
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //��������p�،��]���z( T + 4 )���擾����B
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //��������p�،��]���z( T + 5 )���擾����B
                l_dblUnexecSubstiSecurityAsset = l_detailMargin.getUnexecSubstiSecurityAsset5();
                break;
            default:
                l_dblUnexecSubstiSecurityAsset = 0d;
        }

        //�擾������������p�،��]���z��ԋp����B
        return l_dblUnexecSubstiSecurityAsset;
    }

    /**
     * �iget���������ʑ���j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���������ʑ���v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get���������ʑ���iT+n�j<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getUnexecContractAmount(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //���������ʑ��
        double l_dblUnexecContractAmount;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //���������ʑ��( T + 0 )���擾����B
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //���������ʑ��( T + 1 )���擾����B
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //���������ʑ��( T + 2 )���擾����B
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //���������ʑ��( T + 3 )���擾����B
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //���������ʑ��( T + 4 )���擾����B
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //���������ʑ��( T + 5 )���擾����B
                l_dblUnexecContractAmount = l_detailMargin.getUnexecContractAmount5();
                break;
            default:
                l_dblUnexecContractAmount = 0d;
        }

        //�擾�������������ʑ����ԋp����B
        return l_dblUnexecContractAmount;
    }

    /**
     * �iget�������K�v�ۏ؋��j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get�������K�v�ۏ؋��iT+n�j<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getUnexecMarginDeposit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //�������K�v�ۏ؋�
        double l_dblUnexecMarginDeposit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //�������K�v�ۏ؋�( T + 0 )���擾����B
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //�������K�v�ۏ؋�( T + 1 )���擾����B
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //�������K�v�ۏ؋�( T + 2 )���擾����B
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //�������K�v�ۏ؋�( T + 3 )���擾����B
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //�������K�v�ۏ؋�( T + 4 )���擾����B
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //�������K�v�ۏ؋�( T + 5 )���擾����B
                l_dblUnexecMarginDeposit = l_detailMargin.getUnexecMarginDeposit5();
                break;
            default:
                l_dblUnexecMarginDeposit = 0d;
        }

        //�擾�����������K�v�ۏ؋���ԋp����B
        return l_dblUnexecMarginDeposit;
    }

    /**
     * �iget�����������K�v�ۏ؋��j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����������K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get�����������K�v�ۏ؋��iT+n�j<BR>
     * <BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getUnexecCashMarginDeposit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //�����������K�v�ۏ؋�
        double l_dblUnexecCashMarginDeposit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //�������K�v�ۏ؋�( T + 0 )���擾����B
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //�������K�v�ۏ؋�( T + 1 )���擾����B
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //�������K�v�ۏ؋�( T + 2 )���擾����B
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //�������K�v�ۏ؋�( T + 3 )���擾����B
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //�������K�v�ۏ؋�( T + 4 )���擾����B
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //�������K�v�ۏ؋�( T + 5 )���擾����B
                l_dblUnexecCashMarginDeposit = l_detailMargin.getUnexecCashMarginDeposit5();
                break;
            default:
                l_dblUnexecCashMarginDeposit = 0d;
        }

        //�擾���������������K�v�ۏ؋���ԋp����B
        return l_dblUnexecCashMarginDeposit;
    }

    /**
     * �iget���v��ԍρE�������n�K�v�ۏ؋��j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���v��ԍρE�������n�K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get���v��ԍρE�������n�K�v�ۏ؋��iT+n�j<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getDayRepayMarginDeposit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //���v��ԍρE�������n�K�v�ۏ؋�
        double l_dblDayRepayMarginDeposit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //���v��ԍρE�������n�K�v�ۏ؋�( T + 0 )���擾����B
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //���v��ԍρE�������n�K�v�ۏ؋�( T + 1 )���擾����B
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //���v��ԍρE�������n�K�v�ۏ؋�( T + 2 )���擾����B
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //���v��ԍρE�������n�K�v�ۏ؋�( T + 3 )���擾����B
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //���v��ԍρE�������n�K�v�ۏ؋�( T + 4 )���擾����B
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //���v��ԍρE�������n�K�v�ۏ؋�( T + 5 )���擾����B
                l_dblDayRepayMarginDeposit = l_detailMargin.getDayRepayMarginDeposit5();
                break;
            default:
                l_dblDayRepayMarginDeposit = 0d;
        }

        //�擾�������v��ԍρE�������n�K�v�ۏ؋���ԋp����B
        return l_dblDayRepayMarginDeposit;
    }

    /**
     * �iget���v��ԍρE�������n�����K�v�ۏ؋��j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���v��ԍρE�������n�����K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get���v��ԍρE�������n�����K�v�ۏ؋��iT+n�j<BR>
     * <BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getDayRepayCashMarginDeposit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //���v��ԍρE�������n�����K�v�ۏ؋�
        double l_dblDayRepayCashMarginDeposit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //���v��ԍρE�������n�����K�v�ۏ؋�( T + 0 )���擾����B
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //���v��ԍρE�������n�����K�v�ۏ؋�( T + 1 )���擾����B
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //���v��ԍρE�������n�����K�v�ۏ؋�( T + 2 )���擾����B
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //���v��ԍρE�������n�����K�v�ۏ؋�( T + 3 )���擾����B
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //���v��ԍρE�������n�����K�v�ۏ؋�( T + 4 )���擾����B
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //���v��ԍρE�������n�����K�v�ۏ؋�( T + 5 )���擾����B
                l_dblDayRepayCashMarginDeposit = l_detailMargin.getDayRepayCashMarginDeposit5();
                break;
            default:
                l_dblDayRepayCashMarginDeposit = 0d;
        }

        //�擾�������v��ԍρE�������n�K�v�ۏ؋���ԋp����B
        return l_dblDayRepayCashMarginDeposit;
    }

    /**
     * �iget�����ό��ʕ]�����j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ό��ʕ]�����v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get�����ό��ʕ]�����iT+n�j<BR>
     * <BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getContractAssetLoss(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //�����ό��ʕ]����
        double l_dblContractAssetLoss;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //�����ό��ʕ]����( T + 0 )���擾����B
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //�����ό��ʕ]����( T + 1 )���擾����B
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //�����ό��ʕ]����( T + 2 )���擾����B
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //�����ό��ʕ]����( T + 3 )���擾����B
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //�����ό��ʕ]����( T + 4 )���擾����B
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //�����ό��ʕ]����( T + 5 )���擾����B
                l_dblContractAssetLoss = l_detailMargin.getContractAssetLoss5();
                break;
            default:
                l_dblContractAssetLoss = 0d;
        }

        //�擾���������ό��ʕ]������ԋp����B
        return l_dblContractAssetLoss;
    }

    /**
     * �iget�����ό��ʕ]���v�j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ό��ʕ]���v�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get�����ό��ʕ]���v�iT+n�j<BR>
     * <BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getContractAssetProfit(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //�����ό��ʕ]���v
        double l_dblContractAssetProfit;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //�����ό��ʕ]���v( T + 0 )���擾����B
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //�����ό��ʕ]���v( T + 1 )���擾����B
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //�����ό��ʕ]���v( T + 2 )���擾����B
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //�����ό��ʕ]���v( T + 3 )���擾����B
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //�����ό��ʕ]���v( T + 4 )���擾����B
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //�����ό��ʕ]���v( T + 5 )���擾����B
                l_dblContractAssetProfit = l_detailMargin.getContractAssetProfit5();
                break;
            default:
                l_dblContractAssetProfit = 0d;
        }

        //�擾���������ό��ʕ]���v��ԋp����B
        return l_dblContractAssetProfit;
    }
    
    /**
     * �iget���ʌ��ϑ�<�w���>�j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���ʌ��ϑ�<�w���>�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get���ʌ��ϑ�<�w���>�iT+n�j<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getContractLossDesignate(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //���ʌ��ϑ�<�w���>
        double l_dblContractLossDesignate;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_1:
                //���ʌ��ϑ�<�w���>( T + 1 )���擾����B
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //���ʌ��ϑ�<�w���>( T + 2 )���擾����B
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //���ʌ��ϑ�<�w���>( T + 3 )���擾����B
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //���ʌ��ϑ�<�w���>( T + 4 )���擾����B
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //���ʌ��ϑ�<�w���>( T + 5 )���擾����B
                l_dblContractLossDesignate = l_detailMargin.getContractLossDesignate5();
                break;
            default:
                l_dblContractLossDesignate = 0d;
        }

        //�擾�������ʌ��ϑ�<�w���>��ԋp����B
        return l_dblContractLossDesignate;
    }

    /**
     * �iget���ʌ��ωv<�w���>�j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���ʌ��ωv<�w���>�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get���ʌ��ωv<�w���>�iT+n�j<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getContractProfitDesignate(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //���ʌ��ωv<�w���>
        double l_dblContractProfitDesignate;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_1:
                //���ʌ��ωv<�w���>( T + 1 )���擾����B
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //���ʌ��ωv<�w���>( T + 2 )���擾����B
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //���ʌ��ωv<�w���>( T + 3 )���擾����B
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //���ʌ��ωv<�w���>( T + 4 )���擾����B
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //���ʌ��ωv<�w���>( T + 5 )���擾����B
                l_dblContractProfitDesignate = l_detailMargin.getContractProfitDesignate5();
                break;
            default:
                l_dblContractProfitDesignate = 0d;
        }

        //�擾�������ʌ��ωv<�w���>��ԋp����B
        return l_dblContractProfitDesignate;
    }

    /**
     * �iget�o���z<�w���>�j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�o���z<�w���>�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get�o���z<�w���>�iT+n�j<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getPaymentAmountDesignate(
            TpCalcResultEquityDetailParams l_detailEquity,
            int l_intSpecifiedPoint)
    {
        //�o���z<�w���>
        double l_dblPaymentAmountDesignate;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //�o���z<�w���>( T + 0 )���擾����B
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //�o���z<�w���>( T + 1 )���擾����B
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //�o���z<�w���>( T + 2 )���擾����B
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //�o���z<�w���>( T + 3 )���擾����B
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //�o���z<�w���>( T + 4 )���擾����B
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //�o���z<�w���>( T + 5 )���擾����B
                l_dblPaymentAmountDesignate = l_detailEquity.getPaymentAmountDesignate5();
                break;
            default:
                l_dblPaymentAmountDesignate = 0d;
        }

        //�擾�����o���z<�w���>��ԋp����B
        return l_dblPaymentAmountDesignate;
    }

    /**
     * �iget�o���z<�w���>�j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�o���z<�w���>�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * �]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.get�o���z<�w���>�iT+n�j<BR>
     * @@param l_detailMargin
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    private double getPaymentAmountDesignate(
            TpCalcResultMarginDetailParams l_detailMargin,
            int l_intSpecifiedPoint)
    {
        //�o���z<�w���>
        double l_dblPaymentAmountDesignate;

        switch(l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0:
                //�o���z<�w���>( T + 0 )���擾����B
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate0();
                break;

            case WEB3TPSpecifiedPointDef.T_1:
                //�o���z<�w���>( T + 1 )���擾����B
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate1();
                break;

            case WEB3TPSpecifiedPointDef.T_2:
                //�o���z<�w���>( T + 2 )���擾����B
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate2();
                break;

            case WEB3TPSpecifiedPointDef.T_3:
                //�o���z<�w���>( T + 3 )���擾����B
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate3();
                break;

            case WEB3TPSpecifiedPointDef.T_4:
                //�o���z<�w���>( T + 4 )���擾����B
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate4();
                break;

            case WEB3TPSpecifiedPointDef.T_5:
                //�o���z<�w���>( T + 5 )���擾����B
                l_dblPaymentAmountDesignate = l_detailMargin.getPaymentAmountDesignate5();
                break;
            default:
                l_dblPaymentAmountDesignate = 0d;
        }

        //�擾�����o���z<�w���>��ԋp����B
        return l_dblPaymentAmountDesignate;
    }

    /**
     * �icreate���Y�]���z������ʁj<BR>
     * <BR>
     * ���Y�]���z������ʂ̕\���������s���B<BR> 
     * <BR>
     * ���V�[�P���X�}<BR> 
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate���Y�]���z������ʁv�Q��<BR> 
     * @@param l_request ���N�G�X�g�f�[�^
     * @@return WEB3TPAssetHistoryResponse
     */
    public WEB3TPAssetHistoryResponse createAssertHistory(WEB3TPAssetHistoryRequest l_reqest)
            throws WEB3BaseException
    {
        final String METHOD_NAME = "createAssertHistory(WEB3TPAssetHistoryRequest)";
        log.entering(METHOD_NAME);
        //1.1get�⏕����( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        //1.2getMainAccount( )
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(
                    l_subAccount.getAccountId());
        }
        catch(NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        //1.3is�M�p�����J��(�ٍϋ敪 : String)
        //�M�p�q���ǂ������`�F�b�N����B 

        //�m�����n 
        //�ٍϋ敪�F �h�w��Ȃ��h 
        boolean l_blnIsMarginAccountEstablished = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        //1.4ArrayList( )
        //��̃��X�g�𐶐�����B 
        ArrayList l_arrayList = new ArrayList();
        //1.5create���Y�]���z���𖾍ׁi�������j(�⏕����, boolean)
        //�������̎��Y�]���z���𖾍ׂ𐶐�����B 

        //�m�����n 
        //�⏕�����F get�⏕����()�̖߂�l 
        //is�M�p�q�F is�M�p�����J��()�̖߂�l
        WEB3TPAssetHistoryUnit l_tpAssetHistoryUnit = this.createAssertHistoryPerDay(
                l_subAccount,
                l_blnIsMarginAccountEstablished);
        //1.6add(arg0 : Object)
        //���X�g�ɓ������̎��Y�]���z���𖾍ׂ�ǉ�����B 

        //�m�����n 
        //arg0�F create���Y�]���z���𖾍ׁi�������j()�̖߂�l
        l_arrayList.add(l_tpAssetHistoryUnit);
        //1.7getDefaultProcessor( )�N�G���v���Z�b�T���擾����B 

        //1.8doFindAllQuery(Row�^�C�v : RowType, Where : String, orderBy : String, Condition :
        //String, ���X�g : Object[])
        //�ȉ��̏����ŁA�]�͎��Y�]���z�����e�[�u�����烌�R�[�h���擾����B 
        //�i�O���ȑO�̃f�[�^���擾����B�j 

        //�m�����n 
        //����ID = �⏕����.getAccountId()�̖߂�l 

        //�m�����n 
        //Row�^�C�v�F �]�͎��Y�]���z����Row.TYPE 
        //Where�F "account_id = ?" 
        //orderBy�F "calc_date desc" 
        //Condition�F null 
        //���X�g�F �ȉ��̗v�f�̔z�� 
        //   �⏕����.getAccountId()�̖߂�l 
        String l_strWhere = "account_id = ?";
        String l_strSortBy = "calc_date desc";
        Object[] l_objValue =
        {new Long(l_subAccount.getAccountId())};
        List l_lstTpAssertHistory = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lstTpAssertHistory = l_processor.doFindAllQuery(
                    TpAssetHistoryRow.TYPE,
                    l_strWhere,
                    l_strSortBy,
                    null,
                    l_objValue);
        }
        catch(DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch(IllegalStateException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        //1.9�擾�������R�[�h����Loop����
        int l_intCount = 0;
        if(l_lstTpAssertHistory != null)
        {
            l_intCount = l_lstTpAssertHistory.size();
        }
        for(int i = 0; i < l_intCount; i++)
        {
            //1.9.1���Y�]���z���𖾍�( 
            WEB3TPAssetHistoryUnit l_historyUnit = new WEB3TPAssetHistoryUnit();
            //1.9.2�v���p�e�B�Z�b�g
            //(*2) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
            TpAssetHistoryRow l_assertHistoryRow = (TpAssetHistoryRow)l_lstTpAssertHistory.get(i);
            //���t�F �]�͎��Y�]���z����Params.���t
            l_historyUnit.bizDate = l_assertHistoryRow.getCalcDate();

            //�a����]���z�F �]�͎��Y�]���z����Params.�a����c��
            double l_dblAccountBalance = l_assertHistoryRow.getAccountBalance();
            l_historyUnit.accountBalance = WEB3StringTypeUtility.formatNumber(l_dblAccountBalance);
            //�����]���z�F �]�͎��Y�]���z����Params.�����]���z
            double l_dblEquityAssetDelivered = l_assertHistoryRow.getEquityAssetDelivered();
            l_historyUnit.equityAsset = WEB3StringTypeUtility.formatNumber(l_dblEquityAssetDelivered);
            //�����~�j�����]���z�F �]�͎��Y�]���z����Params.�����~�j�����]���z
            double l_dblMiniStockAssetDelivered = l_assertHistoryRow.getMiniStockAssetDelivered();
            l_historyUnit.mstkAsset = WEB3StringTypeUtility.formatNumber(l_dblMiniStockAssetDelivered);
            //�ݐϓ����]���z�F �]�͎��Y�]���z����Params.�ݐϓ����]���z
            double l_dblRuitoAssetDelivered = l_assertHistoryRow.getRuitoAssetDelivered();
            l_historyUnit.ruitoAsset = WEB3StringTypeUtility.formatNumber(l_dblRuitoAssetDelivered);
            //�����M���]���z�F �]�͎��Y�]���z����Params.�����M���]���z
            double l_dblMutualFundAssetDelivered = l_assertHistoryRow.getMutualFundAssetDelivered();
            l_historyUnit.mutualAsset = WEB3StringTypeUtility.formatNumber(l_dblMutualFundAssetDelivered);
            //���]���z�F �]�͎��Y�]���z����Params.���]���z
            double l_dblBondAssetDelivered = l_assertHistoryRow.getBondAssetDelivered();
            l_historyUnit.bondAsset = WEB3StringTypeUtility.formatNumber(l_dblBondAssetDelivered);
            //���v�]���z�F �a����]���z + �����]���z + �����~�j�����]���z           
            //                    + �ݐϓ����]���z + �����M���]���z + ���]���z
            l_historyUnit.totalAsset = WEB3StringTypeUtility.formatNumber(l_dblAccountBalance
                    + l_dblEquityAssetDelivered
                    + l_dblMiniStockAssetDelivered
                    + l_dblRuitoAssetDelivered
                    + l_dblMutualFundAssetDelivered
                    + l_dblBondAssetDelivered);

            //�O����F null
            l_historyUnit.comparedPreviousDay = null;
            //1.9.3add(arg0 : Object)
            //���X�g�Ɏ��Y�]���z���𖾍ׂ�ǉ�����B 

            //�m�����n 
            //arg0�F ���Y�]���z���𖾍׃I�u�W�F�N�g 
            l_arrayList.add(l_historyUnit);
        }

        //1.10 toArray( )   
        WEB3TPAssetHistoryUnit[] l_tpAssetHistoryUnits = new WEB3TPAssetHistoryUnit[l_arrayList.size()];
        l_arrayList.toArray(l_tpAssetHistoryUnits);
        //1.11�z��̊e�v�f�ɂ���Loop����
        //1.11.1(*4) �O���䍀�ڂ̒l���Z�o����B

        //���Y�]���z���𖾍ׁmindex].�O���� =���Y�]���z���𖾍ׁmindex].���v�]���z - ���Y�]���z���𖾍�[index+1].���v�]���z
        //�� index == ���Y�]���z���𖾍�[].length - 1 �̏ꍇ�́A0 ���Z�b�g����B
        for(int i = 0; i < l_tpAssetHistoryUnits.length; i++)
        {
            WEB3TPAssetHistoryUnit l_unit = l_tpAssetHistoryUnits[i];
            if(i == (l_tpAssetHistoryUnits.length - 1))
            {
                l_unit.comparedPreviousDay = "0";
                continue;
            }
            double l_dblTotalAsset = Double.parseDouble(l_unit.totalAsset);
            double l_dblPreTotalAsset = Double.parseDouble(l_tpAssetHistoryUnits[i + 1].totalAsset);
            l_unit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblTotalAsset
                    - l_dblPreTotalAsset);
        }

        //1.12createResponse( )
        WEB3TPAssetHistoryResponse l_response = (WEB3TPAssetHistoryResponse)l_reqest.createResponse();
        //1.12.1�v���p�e�B�Z�b�g
        //(*5) �ȉ��̂Ƃ���ɁA�v���p�e�B�ɃZ�b�g����B

        //���Y�]���z�����ꗗ�F ���Y�]���z���𖾍ׂ̔z��
        l_response.assetHistoryList = l_tpAssetHistoryUnits;
        log.exiting(METHOD_NAME);
        return l_response;
    }

    /**
     * �ireate���Y�]���z���𖾍ׁi�������j�j<BR>
     * <BR>
     * �������̎��Y�]���z���𖾍ׂ𐶐�����B <BR> 
     * <BR>
     * ���V�[�P���X�}<BR> 
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate���Y�]���z���𖾍ׁi�������j�v�Q�� <BR> 
     * @@param l_subAccount  - �⏕����
     * @@param l_blnIsMargin  - is�M�p�q
     * @@return WEB3TPAssetHistoryUnit
     */
    public WEB3TPAssetHistoryUnit createAssertHistoryPerDay(
            WEB3GentradeSubAccount l_subAccount,
            boolean l_blnIsMargin) throws WEB3BaseException
    {
        final String METHOD_NAME = "createAssertHistoryPerDay(WEB3GentradeSubAccount,boolean)";
        log.entering(METHOD_NAME);
        WEB3TPTradingPowerService l_tradingPowerService = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblAccountBalance = 0;
        double l_dblTodayExecutedAmount = 0;
        double l_dblEquityAssetExecuted = 0;
        double l_dblRuitoAssetExecuted = 0;
        double l_dblMiniStockAssetExecuted = 0;
        double l_dblMutualFundAssetExecuted = 0;
        double l_dblBondAssetExecuted = 0;
        WEB3TPCalcCondition l_calcCondition = null;
        //1.1(*1)����.is�M�p�q == false �i�����q�j�̏ꍇ
        if(!l_blnIsMargin)
        {

            //1.1.1get���Y�]�͏��<�����ڋq>(�⏕����)
            //���Y�]�͏��<�����ڋq>�I�u�W�F�N�g���擾����B 

            //�m�����n 
            //�⏕�����F ����.�⏕���� 
            WEB3TPTradingPowerCalcEquity l_tradingPowerCalcEquity = l_tradingPowerService.getTradingPowerCalcEquity(l_subAccount);
            //1.1.2get�a����c��(int)
            //T+5�̗a����c�����擾����B 

            //�m�����n 
            //�w����F 5 
            l_dblAccountBalance = l_tradingPowerCalcEquity.getAccountBalance(5);
            //1.1.3get�������ϑ��(int)
            //T+5�̓������ϑ�����擾����B 

            //�m�����n 
            //�w����F 0
            l_dblTodayExecutedAmount = l_tradingPowerCalcEquity.getTodayExecutedAmount(0);
            //1.1.4get�]�͌v�Z���ʏڍ�Params<�����ڋq>( )
            TpCalcResultEquityDetailParams l_calcResultEquityDetailParams = l_tradingPowerCalcEquity.getCalcResultDetailEquity();
            //1.1.5get�����]���z(��c)
            l_dblEquityAssetExecuted = l_calcResultEquityDetailParams.getEquityAssetExecuted();
            //1.1.6get�����~�j�����]���z(��c)
            l_dblMiniStockAssetExecuted = l_calcResultEquityDetailParams.getMiniStockAssetExecuted();
            //1.1.7 get�ݐϓ����]���z(��c)
            l_dblRuitoAssetExecuted = l_calcResultEquityDetailParams.getRuitoAssetExecuted();
            //1.1.8get�����M���]���z(��c)
            l_dblMutualFundAssetExecuted = l_calcResultEquityDetailParams.getMutualFundAssetExecuted();
            //1.1.9get���]���z(��c)
            l_dblBondAssetExecuted = l_calcResultEquityDetailParams.getBondAssetExecuted();
            //1.1.10get�]�͌v�Z����
            l_calcCondition = l_tradingPowerCalcEquity.getCalcCondition();
        }
        else
        {
            //1.2����.is�M�p�q == true �i�M�p�q�j�̏ꍇ
            //1.2.1get���Y�]�͏��<�M�p�ڋq>
            //���Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g���擾����B 

            //�m�����n 
            //�⏕�����F �⏕�����I�u�W�F�N�g 
            WEB3TPTradingPowerCalcMargin l_tradingPowerCalcMargin = l_tradingPowerService.getTradingPowerCalcMargin(l_subAccount);
            //1.2.2get�a����c��(int)
            //T+5�̗a����c�����擾����B 

            //�m�����n 
            //�w����F 5 
            l_dblAccountBalance = l_tradingPowerCalcMargin.getAccountBalance(5);
            //1.2.3get�������ϑ��(int)
            //T+5�̓������ϑ�����擾����B 

            //�m�����n 
            //�w����F 0 
            l_dblTodayExecutedAmount = l_tradingPowerCalcMargin.getTodayExecutedAmount(0);
            //1.2.4get�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>
            TpCalcResultMarginDetailParams l_marginDetailParams = l_tradingPowerCalcMargin.getCalcResultDetailMargin();
            //1.2.5get�����]���z(��c)
            l_dblEquityAssetExecuted = l_marginDetailParams.getEquityAssetExecuted();
            //1.2.6get�����~�j�����]���z(��c)
            l_dblMiniStockAssetExecuted = l_marginDetailParams.getMiniStockAssetExecuted();
            //1.2.7get�ݐϓ����]���z(��c)
            l_dblRuitoAssetExecuted = l_marginDetailParams.getRuitoAssetExecuted();
            //1.2.8get�����M���]���z(��c)
            l_dblMutualFundAssetExecuted = l_marginDetailParams.getMutualFundAssetExecuted();
            //1.2.9get���]���z(��c)
            l_dblBondAssetExecuted = l_marginDetailParams.getBondAssetExecuted();
            //1.2.10get�]�͌v�Z����( )
            l_calcCondition = l_tradingPowerCalcMargin.getCalcCondition();

        }

        //1.3 get�c�Ɠ�(int)
        Date l_datBizDate = l_calcCondition.getBizDate(0);
        //1.4���Y�]���z���𖾍�( )
        WEB3TPAssetHistoryUnit l_historyUnit = new WEB3TPAssetHistoryUnit();
        //1.5 (*3)�v���p�e�B�Z�b�g
        //(*3) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

        //���t�F get�c�Ɠ�()�̖߂�l
        l_historyUnit.bizDate = l_datBizDate;
        //�a����]���z�F get�a����c��()�̖߂�l - get�������ϑ��()�̖߂�l
        double l_dblAccountBalanceResult = l_dblAccountBalance - l_dblTodayExecutedAmount;
        l_historyUnit.accountBalance = WEB3StringTypeUtility.formatNumber(l_dblAccountBalanceResult);
        //�����]���z�F get�����]���z�i��c�j()�̖߂�l
        l_historyUnit.equityAsset = WEB3StringTypeUtility.formatNumber(l_dblEquityAssetExecuted);
        //�����~�j�����]���z�F get�����~�j�����]���z�i��c�j()�̖߂�l
        l_historyUnit.mstkAsset = WEB3StringTypeUtility.formatNumber(l_dblMiniStockAssetExecuted);
        //�ݐϓ����]���z�F get�ݐϓ����]���z�i��c�j()�̖߂�l
        l_historyUnit.ruitoAsset = WEB3StringTypeUtility.formatNumber(l_dblRuitoAssetExecuted);
        //�����M���]���z�F get�����M���]���z�i��c�j()�̖߂�l
        l_historyUnit.mutualAsset = WEB3StringTypeUtility.formatNumber(l_dblMutualFundAssetExecuted);
        //���]���z�F get���]���z�i��c�j()�̖߂�l
        l_historyUnit.bondAsset = WEB3StringTypeUtility.formatNumber(l_dblBondAssetExecuted);
        //���v�]���z�F �a����]���z + �����]���z + �����~�j�����]���z
        //                    + �ݐϓ����]���z + �����M���]���z + ���]���z
        l_historyUnit.totalAsset = WEB3StringTypeUtility.formatNumber(l_dblAccountBalanceResult
                + l_dblEquityAssetExecuted
                + l_dblMiniStockAssetExecuted
                + l_dblRuitoAssetExecuted
                + l_dblMutualFundAssetExecuted
                + l_dblBondAssetExecuted);
        //�O����F null
        l_historyUnit.comparedPreviousDay = null;

        //���Y���ڋq�������q�̏ꍇ��(*1)�̕���Ŏ擾�������̂��A
        //   �M�p�q�̏ꍇ��(*2)�̕���Ŏ擾�������̂��Z�b�g����B
        log.exiting(METHOD_NAME);
        return l_historyUnit;
    }

    /**
     * (get�ڋq�]�͏���Params)<BR>
     * �ڋq�]�͏����e�[�u�����A�ȉ��̏����Ń��R�[�h�����������ʂ�Ԃ��B<BR>
     * <BR>
     * �����F<BR>
     * ����ID = �����̃A�J�E���gID<BR>
     * <BR>
     * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_01037<BR>
     * <BR>
     * @@param l_accountId - (����ID)<BR>
     * ����ID<BR>
     * @@return TradingpowerCalcConditionParams
     * @@throws WEB3BaseException
     */
    private TradingpowerCalcConditionParams getTradingpowerCalcConditionParams(Long l_accountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradingpowerCalcConditionParams(Long)";
        log.entering(STR_METHOD_NAME);

        List l_lisTradingpowerCalcConditionRows = null;

        try
        {
            //�����F
            //����ID = �����̃A�J�E���gID
            l_lisTradingpowerCalcConditionRows =
                TradingpowerCalcConditionDao.findRowsByAccountId(l_accountId.longValue());
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

        //���������ʂ�0���̏ꍇ�A�G���[��ԋp����B
        if (l_lisTradingpowerCalcConditionRows.isEmpty())
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            (TradingpowerCalcConditionParams)l_lisTradingpowerCalcConditionRows.get(0);

        log.exiting(STR_METHOD_NAME);
        return l_tradingpowerCalcConditionParams;
    }

    /**
     * �icreate�s����������ʁj<BR>
     * <BR>
     * ���V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate�s����������ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �s���������󋵉�ʕ\�����N�G�X�g<BR>
     * @@return WEB3TPShortfallGenerationResponse
     * @@throws WEB3BaseException
     */
    protected WEB3TPShortfallGenerationResponse createShortfallGenerationScreen(
        WEB3TPShortfallGenerationRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createShortfallGenerationScreen(WEB3TPShortfallGenerationRequest)";
        log.entering(STR_METHOD_NAME);

        //createResponse()
        //�s����������ʕ\�����N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g�𐶐�����B
        WEB3TPShortfallGenerationResponse l_response = (WEB3TPShortfallGenerationResponse)l_request.createResponse();

        //get�⏕����()
        //�⏕�����I�u�W�F�N�g���擾����B
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //get�ڋq()
        //�ڋq�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_subAccount.getAccountId());
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

        //getAccountId()
        //�擾�����ڋq�̃A�J�E���gID���擾����B
        long l_lngAccountId = l_mainAccount.getAccountId();

        //get�ڋq�]�͏���Params(Long)
        //[����]
        //�EgetAccountId()�Ŏ擾�����A�J�E���gID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            this.getTradingpowerCalcConditionParams(new Long(l_lngAccountId));

        //get�s����������(�ڋq)
        //[����]
        //get�ڋq()�̖߂�l
        WEB3TPPaymentRequisitionManageService l_paymentRequisitionManageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(WEB3TPPaymentRequisitionManageService.class);
        String l_strLackCashOccurSituation =
            l_paymentRequisitionManageService.getLackCashOccurSituation(l_mainAccount);

        //get�s�����������(�ڋq)
        //[����]
        //get�ڋq()�̖߂�l
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
            l_paymentRequisitionManageService.getShortfallGenerationInfo(l_mainAccount);

        //�s�����������()
        //�s�����������𐶐�����B
        WEB3TPShortfallGenerationInfo l_newShortfallGenerationInfoInfo = new WEB3TPShortfallGenerationInfo();

        //�v���p�e�B�Z�b�g
        //�s�����������̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
        //�E����(T+0)�@@=�@@get�s�����������()�̖߂�l.����(T+0)
        l_newShortfallGenerationInfoInfo.closeDate0 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate0);
        //�E����(T+1)�@@=�@@get�s�����������()�̖߂�l.����(T+1)
        l_newShortfallGenerationInfoInfo.closeDate1 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate1);
        //�E����(T+2)�@@=�@@get�s�����������()�̖߂�l.����(T+2)
        l_newShortfallGenerationInfoInfo.closeDate2 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate2);
        //�E����(T+3)�@@=�@@get�s�����������()�̖߂�l.����(T+3)
        l_newShortfallGenerationInfoInfo.closeDate3 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate3);
        //�E����(T+4)�@@=�@@get�s�����������()�̖߂�l.����(T+4)
        l_newShortfallGenerationInfoInfo.closeDate4 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate4);
        //�E����(T+5)�@@=�@@get�s�����������()�̖߂�l.����(T+5)
        l_newShortfallGenerationInfoInfo.closeDate5 =
            WEB3DateUtility.toDay(l_shortfallGenerationInfo.closeDate5);

        //�E�����K�v�z(T+0)�@@=�@@get�s�����������()�̖߂�l.�����K�v�z(T+0)
        l_newShortfallGenerationInfoInfo.requiredPayAmt0 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt0);
        //�E�����K�v�z(T+1)�@@=�@@get�s�����������()�̖߂�l.�����K�v�z(T+1)
        l_newShortfallGenerationInfoInfo.requiredPayAmt1 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt1);
        //�E�����K�v�z(T+2)�@@=�@@get�s�����������()�̖߂�l.�����K�v�z(T+2)
        l_newShortfallGenerationInfoInfo.requiredPayAmt2 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt2);
        //�E�����K�v�z(T+3)�@@=�@@get�s�����������()�̖߂�l.�����K�v�z(T+3)
        l_newShortfallGenerationInfoInfo.requiredPayAmt3 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt3);
        //�E�����K�v�z(T+4)�@@=�@@get�s�����������()�̖߂�l.�����K�v�z(T+4)
        l_newShortfallGenerationInfoInfo.requiredPayAmt4 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt4);
        //�E�����K�v�z(T+5)�@@=�@@get�s�����������()�̖߂�l.�����K�v�z(T+5)
        l_newShortfallGenerationInfoInfo.requiredPayAmt5 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.requiredPayAmt5);

        //�E���Z�z(T+0)�@@=�@@get�s�����������()�̖߂�l.�s�����������.���Z�z(T+0)
        l_newShortfallGenerationInfoInfo.adjustedAmt0 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.adjustedAmt0);
        //�E���Z�z(T+1)�@@=�@@get�s�����������()�̖߂�l.�s�����������.���Z�z(T+1)
        l_newShortfallGenerationInfoInfo.adjustedAmt1 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.adjustedAmt1);

        //�E���v��S����(T+0)�@@=�@@get�s�����������()�̖߂�l.�s�����������.���v��S����(T+0)
        l_newShortfallGenerationInfoInfo.dayTradeRestraint0 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.dayTradeRestraint0);
        //�E���v��S����(T+1)�@@=�@@get�s�����������()�̖߂�l.�s�����������.���v��S����(T+1)
        l_newShortfallGenerationInfoInfo.dayTradeRestraint1 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.dayTradeRestraint1);

        //�E�ۏ؋�����̐U�֊z(T+0)�@@=�@@get�s�����������()�̖߂�l.�s�����������.�ۏ؋�����̐U�֊z(T+0)
        l_newShortfallGenerationInfoInfo.transferFromMarginDeposit0 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.transferFromMarginDeposit0);
        //�E�ۏ؋�����̐U�֊z(T+1)�@@=�@@get�s�����������()�̖߂�l.�s�����������.�ۏ؋�����̐U�֊z(T+1)
        l_newShortfallGenerationInfoInfo.transferFromMarginDeposit1 =
            WEB3StringTypeUtility.formatNumber(l_shortfallGenerationInfo.transferFromMarginDeposit1);

        //�v���p�e�B�Z�b�g
        //�s����������ʕ\�����X�|���X�̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
        //�E�s���������󋵁@@=�@@get�s����������()�̖߂�l
        l_response.shortfallGenerationStateDiv = l_strLackCashOccurSituation;
        //�E�ۏ؋������U�֌㔻��t���O�@@=�@@get�s�����������()�̖߂�l.�ۏ؋������U�֌㔻��t���O
        l_response.autoTransferAfterJudgeFlag = l_shortfallGenerationInfo.depositAutoTransferDivFlag;
        //�E�����x�������t���O�@@=�@@get�ڋq�]�͏���Params()�̖߂�l.�����~�敪
        String l_strTradingStop = l_tradingpowerCalcConditionParams.getTradingStop();
        if (l_strTradingStop.equals(WEB3TradingStopDef.TRADING_ENABLE))
        {
            l_response.payDelayGenerationFlag = false;
        }
        else if (l_strTradingStop.equals(WEB3TradingStopDef.TRADING_STOP))
        {
            l_response.payDelayGenerationFlag = true;
        }
        //�E�s�����������@@=�@@�v���p�e�B�Z�b�g�����s�����������
        l_response.shortfallGenerationInfo = l_newShortfallGenerationInfoInfo;

        log.exiting(STR_METHOD_NAME);

        //�s����������ʕ\�����X�|���X��ԋp
        return l_response;
    }

    /**
     * �icreate�Ǐؔ�����ʁj<BR>
     * <BR>
     * ���V�[�P���X�}<BR>
     * �u�i���Y�]�͏���ʕ\���T�[�r�X�jcreate�Ǐؔ�����ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǐؔ�����ʕ\�����N�G�X�g<BR>
     * @@return WEB3TPAdditionalGenerationResponse
     * @@throws WEB3BaseException
     */
    protected WEB3TPAdditionalGenerationResponse createAdditionalGenerationScreen(
        WEB3TPAdditionalGenerationRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAdditionalGenerationScreen(WEB3TPAdditionalGenerationRequest)";
        log.entering(STR_METHOD_NAME);

        //createResponse()
        //�Ǐؔ�����ʕ\�����N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g�𐶐�����B
        WEB3TPAdditionalGenerationResponse l_response = (WEB3TPAdditionalGenerationResponse)l_request.createResponse();

        //get�⏕����()
        //�⏕�����I�u�W�F�N�g���擾����B
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //get�ڋq()
        //�ڋq�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_subAccount.getAccountId());
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

        //getAccountId()
        //�擾�����ڋq�̃A�J�E���gID���擾����B
        long l_lngAccountId = l_mainAccount.getAccountId();

        //get�ڋq�]�͏���Params(Long)
        //[����]
        //�EgetAccountId()�Ŏ擾�����A�J�E���gID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            this.getTradingpowerCalcConditionParams(new Long(l_lngAccountId));

        //get�Ǐؔ�����(�ڋq, boolean)
        //[����]
        //get�ڋq()�̖߂�l
        //�����Ǐؔ����l���t���O�F�@@true
        WEB3TPPaymentRequisitionManageService l_paymentRequisitionManageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(WEB3TPPaymentRequisitionManageService.class);
        String l_strAdditionalMarginSituation =
            l_paymentRequisitionManageService.getAdditionalMarginSituation(l_mainAccount, true);

        //get�Ǐؔ������(�ڋq, boolean)
        //[����]
        //get�ڋq()�̖߂�l
        //�����Ǐؔ����l���t���O�F�@@true
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
            l_paymentRequisitionManageService.getAdddepositGenerationInfo(l_mainAccount, true);

        //�v���p�e�B�Z�b�g
        //�Ǐؔ�����ʕ\�����X�|���X�̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
        //�E�Ǐؔ����󋵁@@=�@@get�Ǐؔ�����()�̖߂�l
        l_response.additionalGenerationStateDiv = l_strAdditionalMarginSituation;
        //�E�ۏ؋������U�֌㔻��t���O�@@=�@@get�Ǐؔ������().�ۏ؋������U�֌㔻��t���O
        l_response.autoTransferAfterJudgeFlag = l_adddepositGenerationInfo.getDepositAutoTransferDivFlag();
        //�E�Ǐؖ����������t���O�@@=�@@get�ڋq�]�͏���Params().�Ǐؖ������敪
        String l_srtAdditionalDepositStop = l_tradingpowerCalcConditionParams.getAdditionalDepositStop();
        if (l_srtAdditionalDepositStop.equals(WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_NOT_STOP))
        {
            l_response.additionalNonPayAmtFlag = false;
        }
        else if (l_srtAdditionalDepositStop.equals(WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_STOP))
        {
            l_response.additionalNonPayAmtFlag = true;
        }

        //get�Ǐؔ������().�Ǐ؏����擾����B
        WEB3TPAdddepositInfo l_adddepositInfo = l_adddepositGenerationInfo.getAdddepositInfo();

        //(*)����t���|
        //get�Ǐؔ������().�Ǐ؏��@@==�@@NULL �̏ꍇ
        if (l_adddepositInfo == null)
        {
            //�v���p�e�B�Z�b�g
            //�Ǐؔ�����ʕ\�����X�|���X�̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
            //�E��ꐅ���Ǐ؏��@@=�@@null
            l_response.firstAdditionalInfo = null;
            //�E��񐅏��Ǐ؏��@@=�@@null
            l_response.secondAdditionalInfo = null;

            //�Ǐؔ�����ʕ\�����X�|���X��ԋp
            return l_response;
        }

        //(*)����t���|
        //get�Ǐؔ������().�Ǐ؏�� ����ꐅ���Ǐ؏��̃C���X�^���X�ł���ꍇ
        if (l_adddepositInfo instanceof WEB3TPFirstAdddepositInfo)
        {
            //��ꐅ���Ǐ؏��()
            //��ꐅ���Ǐ؏��𐶐�����B
            WEB3TPFirstAdditionalInfo l_firstAdditionalInfo = new WEB3TPFirstAdditionalInfo();

            WEB3TPFirstAdddepositInfo l_newFirstAdddepositInfo = (WEB3TPFirstAdddepositInfo)l_adddepositInfo;

            //�v���p�e�B�Z�b�g
            //��ꐅ���Ǐ؏��̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
            //�E��ꐅ���Ǐ؏��.�o�ߓ��� = get�Ǐؔ������().��ꐅ���Ǐ؏��.�o�ߓ���
            l_firstAdditionalInfo.firstDepositPassDay = String.valueOf(l_newFirstAdddepositInfo.firstDepositPassDay);
            //�E��ꐅ���Ǐ؏��.�L���o�ߓ��� = get�Ǐؔ������().��ꐅ���Ǐ؏��.�L���o�ߓ���
            l_firstAdditionalInfo.firstDepositPassDayValid =
                String.valueOf(l_newFirstAdddepositInfo.firstDepositPassDayValid);
            //�E��ꐅ���Ǐ؏��.������ = get�Ǐؔ������().��ꐅ���Ǐ؏��.������
            l_firstAdditionalInfo.firstDepositOccurredDate =
                WEB3DateUtility.toDay(l_newFirstAdddepositInfo.firstDepositOccurredDate);
            //�E��ꐅ���Ǐ؏��.�ۏ؋��� = get�Ǐؔ������().��ꐅ���Ǐ؏��.�ۏ؋���
            l_firstAdditionalInfo.firstMarginDepositRate =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstMarginDepositRate);
            //�E��ꐅ���Ǐ؏��.�ۏ؋��ێ��� = get�Ǐؔ������().��ꐅ���Ǐ؏��.�ۏ؋��ێ���
            l_firstAdditionalInfo.firstDepositRate =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstDepositRate);
            //�E��ꐅ���Ǐ؏��.�Ǐ؋��z = get�Ǐؔ������().��ꐅ���Ǐ؏��.�Ǐ؋��z
            l_firstAdditionalInfo.firstDepositAmount =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstDepositAmount);
            //�E��ꐅ���Ǐ؏��.�Ǐ،��ϕK�v�z = get�Ǐؔ������().��ꐅ���Ǐ؏��.�Ǐ،��ϕK�v�z
            l_firstAdditionalInfo.firstSettlement =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstSettlement);
            //�E��ꐅ���Ǐ؏��.�ۏ؋����� = get�Ǐؔ������().��ꐅ���Ǐ؏��.�ۏ؋�����
            l_firstAdditionalInfo.firstMarginDepositInDe =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstMarginDepositInDe);
            //�E��ꐅ���Ǐ؏��.�ۏ؋�����(�������z) = get�Ǐؔ������().��ꐅ���Ǐ؏��.�ۏ؋�����(�������z)
            l_firstAdditionalInfo.firstMarginDepositInDeExpect =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstMarginDepositInDeExpect);
            //�E��ꐅ���Ǐ؏��.���ύό��� = get�Ǐؔ������().��ꐅ���Ǐ؏��.���ύό���
            l_firstAdditionalInfo.firstSettledContract =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstSettledContract);
            //�E��ꐅ���Ǐ؏��.���������z = get�Ǐؔ������().��ꐅ���Ǐ؏��.���������z
            l_firstAdditionalInfo.firstUncancelAmt =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstUncancelAmt);
            //�E��ꐅ���Ǐ؏��.���������ϕK�v�z = get�Ǐؔ������().��ꐅ���Ǐ؏��.���������ϕK�v�z
            l_firstAdditionalInfo.firstUncancelSettleRequiredAmt =
                WEB3StringTypeUtility.formatNumber(l_newFirstAdddepositInfo.firstUncancelSettleRequiredAmt);

            //�v���p�e�B�Z�b�g
            //�Ǐؔ�����ʕ\�����X�|���X�̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
            //�E��ꐅ���Ǐ؏��@@=�@@�v���p�e�B�Z�b�g������ꐅ���Ǐ؏��
            l_response.firstAdditionalInfo = l_firstAdditionalInfo;
            //�E��񐅏��Ǐ؏��@@=�@@null
            l_response.secondAdditionalInfo = null;
        }
        //(*)����t���|
        //get�Ǐؔ������().�Ǐ؏�� ����񐅏��Ǐ؏��̃C���X�^���X�ł���ꍇ
        else if (l_adddepositInfo instanceof WEB3TPSecondAdddepositInfo)
        {
            //��񐅏��Ǐ؏��()
            //��񐅏��Ǐ؏��𐶐�����B
            WEB3TPSecondAdditionalInfo l_secondAdditionalInfo = new WEB3TPSecondAdditionalInfo();

            WEB3TPSecondAdddepositInfo l_newSecondAdddepositInfo = (WEB3TPSecondAdddepositInfo)l_adddepositInfo;

            //�v���p�e�B�Z�b�g
            //��񐅏��Ǐ؏��̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
            //�E��񐅏��Ǐ؏��.����(����2) = get�Ǐؔ������().��񐅏��Ǐ؏��.����(����2)
            String l_strSecondCloseDate2 =
                WEB3DateUtility.formatDate(
                    l_newSecondAdddepositInfo.secondCloseDate2,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            l_secondAdditionalInfo.secondCloseDate2 =
                WEB3DateUtility.getDate(l_strSecondCloseDate2,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            //�E��񐅏��Ǐ؏��.����(����1) = get�Ǐؔ������().��񐅏��Ǐ؏��.����(����1)
            String l_strSecondCloseDate1 =
                WEB3DateUtility.formatDate(
                    l_newSecondAdddepositInfo.secondCloseDate1,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            l_secondAdditionalInfo.secondCloseDate1 =
                WEB3DateUtility.getDate(l_strSecondCloseDate1,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            //�E��񐅏��Ǐ؏��.����(��������) = get�Ǐؔ������().��񐅏��Ǐ؏��.����(��������)
            String l_strSecondCloseDateExpect =
                WEB3DateUtility.formatDate(
                    l_newSecondAdddepositInfo.secondCloseDateExpect,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            l_secondAdditionalInfo.secondCloseDateExpect =
                WEB3DateUtility.getDate(l_strSecondCloseDateExpect,
                    WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
            //�E��񐅏��Ǐ؏��.������(����2) = get�Ǐؔ������().��񐅏��Ǐ؏��.������(����2)
            l_secondAdditionalInfo.secondDepositOccurredDate2 =
                WEB3DateUtility.toDay(l_newSecondAdddepositInfo.secondDepositOccurredDate2);
            //�E��񐅏��Ǐ؏��.������(����1) = get�Ǐؔ������().��񐅏��Ǐ؏��.������(����1)
            l_secondAdditionalInfo.secondDepositOccurredDate1 =
                WEB3DateUtility.toDay(l_newSecondAdddepositInfo.secondDepositOccurredDate1);
            //�E��񐅏��Ǐ؏��.������(��������) = get�Ǐؔ������().��񐅏��Ǐ؏��.������(��������)
            l_secondAdditionalInfo.secondDepositOccurredDateExpect =
                WEB3DateUtility.toDay(l_newSecondAdddepositInfo.secondDepositOccurredDateExpect);
            //�E��񐅏��Ǐ؏��.�ۏ؋��ێ��� = get�Ǐؔ������().��񐅏��Ǐ؏��.�ۏ؋��ێ���
            l_secondAdditionalInfo.secondDepositRate =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDepositRate);
            //�E��񐅏��Ǐ؏��.�ۏ؋��߂��ێ��� = get�Ǐؔ������().��񐅏��Ǐ؏��.�ۏ؋��߂��ێ���
            l_secondAdditionalInfo.secondDepositBackRate =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDepositBackRate);
            //�E��񐅏��Ǐ؏��.�ۏ؋���(����2) = get�Ǐؔ������().��񐅏��Ǐ؏��.�ۏ؋���(����2)
            l_secondAdditionalInfo.secondMarginDepositRate2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositRate2);
            //�E��񐅏��Ǐ؏��.�ۏ؋���(����1) = get�Ǐؔ������().��񐅏��Ǐ؏��.�ۏ؋���(����1)
            l_secondAdditionalInfo.secondMarginDepositRate1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositRate1);
            //�E��񐅏��Ǐ؏��.�ۏ؋���(��������) = get�Ǐؔ������().��񐅏��Ǐ؏��.�ۏ؋���(��������)
            l_secondAdditionalInfo.secondMarginDepositRateExpect =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositRateExpect);
            //�E��񐅏��Ǐ؏��.�Ǐ؋��z(������) = get�Ǐؔ������().��񐅏��Ǐ؏��.�Ǐ؋��z(������)
            l_secondAdditionalInfo.secondDepositNonPay =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDepositNonPay);
            //�E��񐅏��Ǐ؏��.�Ǐ؋��z(����2) = get�Ǐؔ������().��񐅏��Ǐ؏��.�Ǐ؋��z(����2)
            l_secondAdditionalInfo.secondDeposit2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDeposit2);
            //�E��񐅏��Ǐ؏��.�Ǐ؋��z(����1) = get�Ǐؔ������().��񐅏��Ǐ؏��.�Ǐ؋��z(����1)
            l_secondAdditionalInfo.secondDeposit1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondDeposit1);
            //�E��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(������) = get�Ǐؔ������().��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(������)
            l_secondAdditionalInfo.secondSettlementNonPay =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondSettlementNonPay);
            //�E��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����2) = get�Ǐؔ������().��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����2)
            l_secondAdditionalInfo.secondSettlement2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondSettlement2);
            //�E��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����1) = get�Ǐؔ������().��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����1)
            l_secondAdditionalInfo.secondSettlement1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondSettlement1);
            //�E��񐅏��Ǐ؏��.�ۏ؋����� = get�Ǐؔ������().��񐅏��Ǐ؏��.�ۏ؋�����
            l_secondAdditionalInfo.secondMarginDepositInDe =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositInDe);
            //�E��񐅏��Ǐ؏��.�ۏ؋�����(�������z) = get�Ǐؔ������().��񐅏��Ǐ؏��.�ۏ؋�����(�������z)
            l_secondAdditionalInfo.secondMarginDepositInDeExpect =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondMarginDepositInDeExpect);
            //�E��񐅏��Ǐ؏��.���ύό��� = get�Ǐؔ������().��񐅏��Ǐ؏��.���ύό���
            l_secondAdditionalInfo.secondSettledContract =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondSettledContract);
            //�E��񐅏��Ǐ؏��.���������z(������) = get�Ǐؔ������().��񐅏��Ǐ؏��.���������z(������)
            l_secondAdditionalInfo.secondUncancelAmtNonPay =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelAmtNonPay);
            //�E��񐅏��Ǐ؏��.���������z(����2) = get�Ǐؔ������().��񐅏��Ǐ؏��.���������z(����2)
            l_secondAdditionalInfo.secondUncancelAmt2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelAmt2);
            //�E��񐅏��Ǐ؏��.���������z(����1) = get�Ǐؔ������().��񐅏��Ǐ؏��.���������z(����1)
            l_secondAdditionalInfo.secondUncancelAmt1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelAmt1);
            //�E��񐅏��Ǐ؏��.���������z(��������) = get�Ǐؔ������().��񐅏��Ǐ؏��.���������z(��������)
            l_secondAdditionalInfo.secondUncancelAmtExpect =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelAmtExpect);
            //�E��񐅏��Ǐ؏��.���������ϕK�v�z(������) = get�Ǐؔ������().��񐅏��Ǐ؏��.���������ϕK�v�z(������)
            l_secondAdditionalInfo.secondUncancelSettleRequiredAmtNonPay =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay);
            //�E��񐅏��Ǐ؏��.���������ϕK�v�z(����2) = get�Ǐؔ������().��񐅏��Ǐ؏��.���������ϕK�v�z(����2)
            l_secondAdditionalInfo.secondUncancelSettleRequiredAmt2 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelSettleRequiredAmt2);
            //�E��񐅏��Ǐ؏��.���������ϕK�v�z(����1) = get�Ǐؔ������().��񐅏��Ǐ؏��.���������ϕK�v�z(����1)
            l_secondAdditionalInfo.secondUncancelSettleRequiredAmt1 =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelSettleRequiredAmt1);
            //�E��񐅏��Ǐ؏��.���������ϕK�v�z(��������) = get�Ǐؔ������().��񐅏��Ǐ؏��.���������ϕK�v(��������)
            l_secondAdditionalInfo.secondUncancelSettleRequiredAmtExpect =
                WEB3StringTypeUtility.formatNumber(l_newSecondAdddepositInfo.secondUncancelSettleRequiredAmtExpect);

            //�v���p�e�B�Z�b�g
            //�Ǐؔ�����ʕ\�����X�|���X�̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
            //�E��ꐅ���Ǐ؏��@@=�@@null
            l_response.firstAdditionalInfo = null;
            //�E��񐅏��Ǐ؏��@@=�@@�v���p�e�B�Z�b�g������񐅏��Ǐ؏��
            l_response.secondAdditionalInfo = l_secondAdditionalInfo;
        }

        log.exiting(STR_METHOD_NAME);

        //�Ǐؔ�����ʕ\�����X�|���X��ԋp
        return l_response;
    }

}@
