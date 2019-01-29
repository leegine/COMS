head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcEstimatedAmountCalServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X��n����v�Z�T�[�r�X�C���^�Z�v�^(WEB3TrialCalcEstimatedAmountCalServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc;

import java.lang.reflect.Method;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcRequest;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

/**
 * �i�v�Z�T�[�r�X��n����v�Z�T�[�r�X�C���^�Z�v�^�j<BR>
 * <BR>
 * ��n����v�Z�T�[�r�X�C���^�Z�v�^�N���X�B<BR>
 * <BR>
 * WEB3TrialCalcEstimatedAmountCalServiceInterceptor<BR>
 * @@author sudhindra kinnal
 * @@version 1.0
 */
public class WEB3TrialCalcEstimatedAmountCalServiceInterceptor implements Interceptor
{
    /**
     * log is instance of WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcEstimatedAmountCalServiceInterceptor.class);

    /**
     * strMethodName is a private variable.
     */
    private String strMethodName;

    /**
     * @@roseuid 41C813A50223
     */
    public WEB3TrialCalcEstimatedAmountCalServiceInterceptor()
    {
    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = (*1)<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*2)<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = "0�FDEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h�ڋq�T�[�r�X�h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "07�F�Ɖ�"<BR>
     * <BR>
     * �@@(*1)���N�G�X�g�f�[�^�̌^���u�v�Z�T�[�r�X��n����v�Z���̓��N�G�X�g�v�̏ꍇ�́A
     * null�B<BR>
     * �@@�@@�@@�@@���N�G�X�g�f�[�^�̌^���u�v�Z�T�[�r�X��n����v�Z���N�G�X�g�v�̏ꍇ�́A��
     * ���̒ʂ�B<BR>
     * �@@�@@�@@�@@�@@�|���N�G�X�g�f�[�^.�����^�~�j���敪��"��������"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�s��R�[�h��null�ł���΁A���N�G�X�g�f�[�^.�s��
     * �R�[�h���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�s��R�[�h��null�ł���΁Anull���Z�b�g�B�i�T�[?
     * �X���Őݒ肷��j<BR>
     * �@@�@@�@@�@@�@@�|���N�G�X�g�f�[�^.�����^�~�j���敪��"�~�j��"�̏ꍇ�́A"0�FDEFAULT"�B<
     * BR>
     * <BR>
     * (*2)
     * ���N�G�X�g�f�[�^�̌^���u�v�Z�T�[�r�X��n����v�Z���̓��N�G�X�g�v�̏ꍇ�́A"01�F?
     * ���E�M�p"�B<BR>
     * �@@�@@�@@�@@���N�G�X�g�f�[�^�̌^���u�v�Z�T�[�r�X��n����v�Z���N�G�X�g�v�̏ꍇ�́A<B
     * R>
     * �@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�����^�~�j���敪��"��������"�̏ꍇ�́A"01�F�����E�M
     * �p"�B<BR>
     * �@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�����^�~�j���敪��"�~�j��"�̏ꍇ�́A"05�F�����~�j��
     * ��"�B<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Call when the service method starts.<BR>
     * �iIt is called before service is executed from the xTrade kernel. �j<BR>
     * <BR>
     * 1) Set values in WEB3GentradeTradingClendarContext<BR>
     * <BR>
     *   - Cast the argument[0] of service�@@request to the data object<BR>
     *   - Set the property of "dealings time context" from the request data and
     * OpLoginSecurityService<BR>
     * <BR>
     *   Edit from WEB3GentradeTradingClendarContext.institutionCode =
     * OpLoginSecurityService<BR>
     *   Edit from WEB3GentradeTradingClendarContext.branchCode =
     * OpLoginSecurityService<BR>
     *   WEB3GentradeTradingClendarContext.marketCode  = (*1)<BR>
     *   WEB3GentradeTradingClendarContext.tradingTimeType = (*2)<BR>
     *   WEB3GentradeTradingClendarContext.productCode = "0�FDEFAULT"<BR>
     * WEB3GentradeTradingClendarContext.orderAcceptProduct = '22'(Customer
     * Service)<BR>
     *   WEB3GentradeTradingClendarContext.orderAcceptTransaction  = '07'(Refer)<BR>
     * <BR>
     * (*1) If "Type of request data = [WEB3TrialCalcEstimatedAmountCalcInputRequest]" ->
     * null<BR>
     *          If "Type of request data = [WEB3TrialCalcEstimatedAmountCalcRequest]<BR>
     *            If "l_request.equityMiniDiv ='equity' -> '1'(TOKYO)<BR>
     *             �iBecause the commission is requested at the order date based on
     * the Tokyo market,<BR>
     *               "Tokyo" is set by fixation. �j<BR>
     *            If "l_request.equityMiniDiv = 'Mini stock division' ->
     * '0'(DEFAULT)<BR>
     * <BR>
     * (*2) If "Type of request data = [WEB3TrialCalcEstimatedAmountCalcInputRequest] ->
     * '01'(equity/trust)<BR>
     *        If "Type of request data = [WEB3TrialCalcEstimatedAmountCalcRequest]<BR>
     *          If "l_request.equityMiniDiv ='equity'"  -> '01'(equity/trust)<BR>
     *          If "l_request.equityMiniDiv = 'Mini stock division' '05'(The stocks
     * mini-investment)<BR>
     * <BR>
     *  - Set dealings time context using
     * "ThreadLocalSystemAttributesRegistry.setAttribute( )"<BR>
     *    Set key : WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * 2)�@@Set orderDate and dateRoll<BR>
     *  - Call WEB3GentradeTradingTimeManagement.setTimestamp()<BR>
     * @@param l_method �T�[�r�X���\�b�h - �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParam �T�[�r�X���\�b�h���� - �T�[�r�X���\�b�h����
     * @@return java.lang.Object
     * @@roseuid 4190A61C00DB
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        strMethodName = "onCall(Method,Object[])";
        log.entering(strMethodName);

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        Object l_request = l_serviceParam[0];
        String l_strMarketCode = null;
        MainAccount l_mainAccount = null;
        WEB3GentradeAccountManager l_accountManager = null;
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        long l_lngAccountId = 0;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);
            l_lngAccountId = l_opLoginSec.getAccountId();
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            l_context.setBranchCode(l_strBranchCode);
        } catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage());
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + strMethodName,
                    l_ex.toString(),
                    l_ex);
        }

        if (l_request instanceof WEB3TrialCalcDeliveryPriceCalcInputRequest)
        {
            l_context.setMarketCode(null);
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

        } else if (l_request instanceof WEB3TrialCalcDeliveryPriceCalcRequest)
        {
            WEB3TrialCalcDeliveryPriceCalcRequest l_deliveryPriceRequest =
                ((WEB3TrialCalcDeliveryPriceCalcRequest) l_request);

            if (l_deliveryPriceRequest.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.EQUITY))
            {
                l_context.setMarketCode(WEB3MarketCodeDef.TOKYO);
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            } else
            {
                l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MINI_STOCK);
            }
        } else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + strMethodName,
                "INPUT ���N�G�X�g NOT ���t�ꗗ���N�G�X�g");
        }

        //Declared as private
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        } catch (WEB3BaseException l_ex)
        {
            log.error(strMethodName, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(strMethodName);
        return l_context;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * When the service method ends, it is called. <BR>
     * Dealings calendar context clearn process. <BR>
     * <BR>
     * Clear the next areas of ThreadLocalSystemAttributesRegistry<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_objOnCallReturnValue onCall���^�[���l - onCall���^�[���l
     * @@param l_objServiceMethodReturnValue �T�[�r�X���\�b�h���^�[���l - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 4190A61C00DE
     */
    public void onReturn(Object l_objOnCallReturnValue, Object l_objServiceMethodReturnValue)
    {
        strMethodName = "onReturn(Object,Object)";
        log.entering(strMethodName);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        log.exiting(strMethodName);
    }
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * When the service method throws out an exception, it is called.  <BR>
     * <BR>
     * Clear the next areas of ThreadLocalSystemAttributesRegistry<BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_objOnCallReturnValue onCall���^�[���l - onCall���^�[���l
     * @@param l_throwable ��O�I�u�W�F�N�g - ��O�I�u�W�F�N�g
     * @@roseuid 4190A61C00E1
     */
    public void onThrowable(Object l_objOnCallReturnValue, Throwable l_throwable)
    {
        strMethodName = "onThrowable(Object ,Throwable)";
        log.entering(strMethodName);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.exiting(strMethodName);
    }
}@
