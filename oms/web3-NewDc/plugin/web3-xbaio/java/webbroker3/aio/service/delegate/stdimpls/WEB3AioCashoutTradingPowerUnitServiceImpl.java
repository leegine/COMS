head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���]�̓`�F�b�NUnitServiceImpl(WEB3AioCashoutTradingPowerUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
                   2004/12/09 ���E (���u) �c�Ή�
                   2006/02/21 ��O�� (���u) �d�l�ύX�E���f��No.498
                   2006/08/28 �Ԑi (���u) �d�l�ύX�E���f��No.630�A645
                   2006/11/15 ���G�� (���u)�d�l�ύX�E���f��No.684
                   2006/12/22 �R�c (SCS)�d�l�ύX�E���f��No.687
Revesion History : 2009/12/11 �Ԑi (���u) �d�l�ύX�E���f��No.1252�ANo.1253�ANo.1254 No.1257 No.1258 �c�a�X�V�d�lNo.232�ANo.233
Revesion History : 2010/01/15 ���g (���u) �d�l�ύX�E���f��No.1259
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioCashTransAcceptUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostPaymentOrderDao;
import webbroker3.aio.data.HostPaymentOrderRow;
import webbroker3.aio.data.HostPaymentOrderPK;
import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.aio.define.WEB3AioProcessFlagDef;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioHostStatusDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CashoutTodayDepositTransferDivDef;
import webbroker3.common.define.WEB3DbCurrentPriceCheckDivDef;
import webbroker3.common.define.WEB3DepositAutoTransferStopRegistDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���]�̓`�F�b�NUnitServiceImpl)<BR>
 * �o���]�̓`�F�b�NUnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * ���w�肷��B<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutTradingPowerUnitServiceImpl 
    implements WEB3AioCashoutTradingPowerUnitService 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutTradingPowerUnitServiceImpl.class);  
    
    /**
     * �����̗]�̓`�F�b�N�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���]�̓`�F�b�N�j�]�̓`�F�b�N�����v �Q��<BR>
     * <BR>
     * @@param l_hostPaymentOrderParams - (�o�����������L���[�̍s�I�u�W�F�N�g)
     * @@param l_strProcessFlag - (�����t���O)
     * @@param l_blnTriggerIssueFlag - (�g���K���s�t���O)
     * @@param l_strDbCurrentPriceCheckDiv - (DB�����]�̓`�F�b�N�敪)
     * @@throws WEB3BaseException
     * @@roseuid 412B0319039D
     */
    public void execute(HostPaymentOrderParams l_hostPaymentOrderParams,
        String l_strProcessFlag, boolean l_blnTriggerIssueFlag, String l_strDbCurrentPriceCheckDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "execute(HostPaymentOrderParams, String, boolean, String)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 get�����P��(String, String, String, String, SubAccountTypeEnum)
        //�����P�ʃI�u�W�F�N�g���擾����B 
        //[����] 
        //�،���ЃR�[�h�F ����.�o�����������L���[Params.�،���ЃR�[�h 
        //���X�R�[�h�F ����.�o�����������L���[Params.���X�R�[�h 
        //�ڋq�R�[�h�F ����.�o�����������L���[Params.�ڋq�R�[�h 
        //���ʃR�[�h�F ����.�o�����������L���[Params.���ʃR�[�h 
        //�⏕�����^�C�v�F 1�i�a��������j 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();

        AccountManager l_accMgr = l_finApp.getAccountManager();

        //�،���ЃR�[�h�F ����.�o�����������L���[Params.�،���ЃR�[�h
        String l_strInstitutionCode = l_hostPaymentOrderParams.getInstitutionCode();

        //���X�R�[�h�F ����.�o�����������L���[Params.���X�R�[�h 
        String l_strBranchCode = l_hostPaymentOrderParams.getBranchCode();
 
        //�ڋq�R�[�h�F ����.�o�����������L���[Params.�ڋq�R�[�h 
        String l_strAccountCode = l_hostPaymentOrderParams.getAccountCode();
 
        //���ʃR�[�h�F ����.�o�����������L���[Params.���ʃR�[�h 
        String l_strOrderRequestNumber = l_hostPaymentOrderParams.getOrderRequestNumber();
        
        //�����P�ʃI�u�W�F�N�g���擾����
        AioOrderUnit l_aioOrderUnit = null;
        
        try
        {
          l_aioOrderUnit = 
                 l_aioOrderManager.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode, 
                    l_strOrderRequestNumber,
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����P�ʂ��擾�ł��܂���B���ʃR�[�h  = " + l_strOrderRequestNumber);         
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        long l_accountId = l_aioOrderUnit.getAccountId();

        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.setAccountIdCtx(l_accountId);
        }

        //����
        WEB3GentradeMainAccount l_mainAccount = null;

        //�⏕����
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_accountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //is�M�p�����J��         ����  �ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //is�o���ɔ��������ۏ؋��U�֎��{   �����P�ʁF �擾���������P��.���XID
        boolean l_blnIsPaymentTodayDepositTransferEnforce =
            this.isPaymentTodayDepositTransferEnforce(l_aioOrderUnit.getBranchId());

        //1.2 ����.�����t���O = '0'�i�S���f�[�^�����j �̏ꍇ
        if (WEB3AioProcessFlagDef.ALL_DATE_PROCESS.equals(l_strProcessFlag))
        {
            //1.2.1 DB�����]�̓`�F�b�N�敪 = 0�F�����{ �Ƃ���B
            l_strDbCurrentPriceCheckDiv = WEB3DbCurrentPriceCheckDivDef.NOT_ENFORCEMENT;

            //1.2.2 validate�o���]��(AioOrderUnit, String)

            //�o���]�͂̃`�F�b�N���s��
            //[����]
            //�����P�ʁF �擾���������P��
            //DB�����]�̓`�F�b�N�敪�F �p�����[�^.DB�����]�̓`�F�b�N�敪
            boolean l_blnIsCashoutTp =
                this.validateCashoutTradingPower(l_aioOrderUnit, l_strDbCurrentPriceCheckDiv);
            
            //validate�o���]��()�̖߂�l = false �̏ꍇ
            if (!l_blnIsCashoutTp)
            {
                //1.2.3 �����������(AioOrderUnit)

                //�����̎���������s���B
                //[����] 
                //�����P�ʁF �擾���������P�� 
                this.orderCancelProcess(l_aioOrderUnit);
            }
        }
        //����.�����t���O != '0'�i�S���f�[�^�����j �̏ꍇ
        else
        {
            //��������t���O = true
            boolean l_blnCheckFlag = true;
            
            //�g���K���s�t���O = true �̏ꍇ
            if (l_blnTriggerIssueFlag)
            {
                //is�o���ɔ��������ۏ؋��U�֎��{()�̖߂�l = true ���� is�M�p�����J��()�̖߂�l = true
                //���� �����t���O = '2'�i�����U�����f�[�^�����j�̏ꍇ
                if (WEB3AioProcessFlagDef.NEXT_DATE_TRANSFER_PROCESS.equals(l_strProcessFlag)
                    && l_blnIsMarginAccountEstablished && l_blnIsPaymentTodayDepositTransferEnforce)
                {
                    //get�����s����(�⏕����  : �⏕����)
                    //[����] 
                    //�⏕�����F�⏕����<�����������(�ۏ؋�)>�I�u�W�F�N�g���擾����B
                    WEB3TPTradingPowerService l_web3TPTradingPowerService =
                        (WEB3TPTradingPowerService) Services.getService(
                            WEB3TPTradingPowerService.class);

                    //�⏕����
                    WEB3GentradeSubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount = 
                            (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error(STR_METHOD_NAME,l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    //�ۏ؋������U�֒�~�`�F�b�N���s��
                    //�ۏ؋������U�֒�~�e�[�u�����ȉ��̏����Ō�������B
                    //[��������]
                    // �ۏ؋������U�֒�~�e�[�u��.����ID  =�@@�ڋq�}�X�^.����ID
                    //�ۏ؋������U�֒�~�e�[�u��.�o�^�敪  = 1�u�o�^�v
                    // �V�X�e�����t�i�����j  between�@@�ۏ؋������U�֒�~�e�[�u��.��~���ԁi���j
                    //and�ۏ؋������U�֒�~�e�[�u��.��~���ԁi���j
                    final String l_strWhere =
                        " account_id = ?"
                        + " and regist_div = ?"
                        + " and  ? between autotransfer_stop_start and autotransfer_stop_end ";

                    final Object[] l_bindVars =
                    {
                        new Long(l_mainAccount.getAccountId()),
                        WEB3DepositAutoTransferStopRegistDivDef.DEFAULT,
                        WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp())
                    };
                    double l_dblNextBizDateShortfall = 0D;
                    try
                    {
                        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                        List l_depositAutotransferStopRows = l_queryProcessor.doFindAllQuery(
                            DepositAutotransferStopRow.TYPE,
                            l_strWhere,
                            l_bindVars);
                        //���R�[�h���� > 0�̏ꍇ
                        if(l_depositAutotransferStopRows.size() > 0)
                        {
                            l_dblNextBizDateShortfall = 0.0D;
                        }
                        else
                        {
                            l_dblNextBizDateShortfall =
                                l_web3TPTradingPowerService.getNextBizDateShortfall(l_subAccount);
                        }
                    }
                    catch (DataQueryException l_ex)
                    {
                        log.error(STR_METHOD_NAME,l_ex);
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataNetworkException l_ex)
                    {
                        log.error(STR_METHOD_NAME,l_ex);
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    
                    //�o���z = �擾���������P��.�������� - �����s����
                    double l_dblPaymentAmount = l_aioOrderUnit.getQuantity() - l_dblNextBizDateShortfall;

                    //�o���z <= 0 �̏ꍇ
                    if (l_dblPaymentAmount <= 0)
                    {
                        //�����̎���������s���B
                        //[����] 
                        //�����P�ʁF �擾���������P�� 
                        this.orderCancelProcess(l_aioOrderUnit);

                        if (l_ctx_serv != null)
                        {
                            l_ctx_serv.clearAccountIdCtx();
                        }
                        log.exiting(STR_METHOD_NAME);
                        return;
                    }

                    //0 < �o���z < �����P��.�������� �̏ꍇ
                    else if (l_dblPaymentAmount < l_aioOrderUnit.getQuantity())
                    {
                        //�����P�ʃe�[�u��.�������ʁA�o�����������L���[�e�[�u��.�o���z�Ɉ���.�o���z���Z�b�g����B
                        this.updatePayMentData(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode, 
                            l_strOrderRequestNumber,
                            l_aioOrderUnit.getOrderUnitId(),
                            l_dblPaymentAmount);
                    }
                }
                //��L�ȊO�̏ꍇ
                else
                {
                    //�߂�l = false �̏ꍇ�A��������t���O = false �Ƃ���B
                    //validate�o���]��(AioOrderUnit, String)
                    //�o���]�͂̃`�F�b�N���s���B
                    //[����] 
                    //�����P�ʁF �擾���������P��
                    //DB�����]�̓`�F�b�N�敪�F �p�����[�^.DB�����]�̓`�F�b�N�敪
                    l_blnCheckFlag =
                        this.validateCashoutTradingPower(l_aioOrderUnit, l_strDbCurrentPriceCheckDiv);

                    //��������t���O = false �̏ꍇ
                    if (!l_blnCheckFlag)
                    {
                        //�����������(AioOrderUnit)(�o���]�̓`�F�b�NUnitServiceImpl::�����������)
                        //�����̎���������s���B
                        //[����] 
                        //�����P�ʁF �擾���������P�� 
                        this.orderCancelProcess(l_aioOrderUnit);
                    }
                }
            }
            //��������t���O = true �̏ꍇ�A���{
            if (l_blnCheckFlag)
            {
                //update�����敪(String, String, String, String, long)
                //�o�����������L���[�e�[�u�����X�V����B 

                //[����] 
                //�،���ЃR�[�h�F ����.�o�����������L���[Params.�،���ЃR�[�h 
                //���X�R�[�h�F ����.�o�����������L���[Params.���X�R�[�h 
                //�ڋq�R�[�h�F ����.�o�����������L���[Params.�ڋq�R�[�h 
                //���ʃR�[�h�F ����.�o�����������L���[Params.���ʃR�[�h 
                //�����P��ID�F �擾���������P��.�����P��ID 
                WEB3AioMarketRequestSenderServiceImpl l_aioMarketSenderService = 
                    (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                        ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();

                l_aioMarketSenderService.updateStatus(
                    l_hostPaymentOrderParams.getInstitutionCode(),
                    l_hostPaymentOrderParams.getBranchCode(), 
                    l_hostPaymentOrderParams.getAccountCode(), 
                    l_hostPaymentOrderParams.getOrderRequestNumber(), 
                    l_aioOrderUnit.getOrderUnitId()
                    );
            }
        }
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }        
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * (�����������)<BR>
     * �����̎���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���]�̓`�F�b�N�j������������v �Q��<BR>
     * 
     * @@param l_hostPaymentOrderParams - (�o�����������L���[�̍s�I�u�W�F�N�g)
     * @@param l_strFlag - (�����t���O)
     * @@roseuid 412B031300CE
     */
    protected void orderCancelProcess(AioOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "orderCancelProcess(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        }
        
        //�P.�P�j���o����t�X�V�C���^�Z�v�^�𐶐�����B 
        //[����] 
        //�G���[�R�[�h�F "0001" 
        WEB3AioCashTransAcceptUpdateInterceptor l_aioCashAcceptUpdInterceptor =
            new WEB3AioCashTransAcceptUpdateInterceptor(WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR);
        
        //1.2) setThreadLocalPersistenceEventInterceptor(���o����t�X�V�C���^�Z�v�^)
        //[����] 
        //���o����t�X�V�C���^�Z�v�^�F�@@�����������o����t�X�V�C���^�Z�v�^ 
        
        //AIO�����}�l�[�W�����擾���� 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();     
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioCashAcceptUpdInterceptor);
        
        //1.3) DefaultNewOrderRejectedMarketResponseMessage(����ID : long)
        //��t���ʁi��t�G���[�j�I�u�W�F�N�g�𐶐�����B 
        //[����] 
        //����ID�F ����.�����P��.����ID 
        DefaultNewOrderRejectedMarketResponseMessage l_defaultResponseMessage = 
            new DefaultNewOrderRejectedMarketResponseMessage(
                l_orderUnit.getOrderId());
        
        //1.4) process(��t���� : NewOrderRejectedMarketResponseMessage)
        //��t�G���[�𒍕��ɍX�V����B
        //[����] 
        //��t���ʁF �i����������t���ʁi�G���[�j�I�u�W�F�N�g�j 
        MarketAdapter l_marketAdapter =
            GtlUtils.getTradingModule(ProductTypeEnum.AIO).getMarketAdapter();
        AioMarketResponseReceiverCallbackService l_marketCallbackService = 
            (AioMarketResponseReceiverCallbackService)
                l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        ProcessingResult l_processResult = 
            l_marketCallbackService.process(l_defaultResponseMessage);
        
        if (l_processResult.isFailedResult())
        {
            log.debug("��t�G���[��Ԃɒ������X�V���s�ł���");
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00395,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "��t�G���[��Ԃɒ������X�V���s");   
        }

        //1.5) get�⏕����()
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F ����.�����P��.����ID 
        //�⏕����ID�F ����.�����P��.�⏕����ID 
        
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //�⏕����
        SubAccount l_subAccount = null;
        try
        {
            //�⏕�����I�u�W�F�N�g���擾����B 
            l_subAccount = 
                l_accMgr.getSubAccount(
                    l_orderUnit.getAccountId(),  
                    l_orderUnit.getSubAccountId());
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("�⏕�����I�u�W�F�N�g���擾, �ڋq�I�u�W�F�N�g���擾", l_ex);
            log.exiting(STR_METHOD_NAME);  
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }         

        //1.6) �]�͍Čv�Z(�⏕���� : �⏕����)
        //�]�͂̍X�V������B 
        //[����] 
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g
        WEB3TPTradingPowerReCalcService l_service =
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        //1.7)             
        //�P�j�o�����������L���[Params�C���X�^���X�𐶐�����B 
        HostPaymentOrderParams l_params = new HostPaymentOrderParams();
        //�Q�j�o�����������L���[Params�Ƀv���p�e�B���Z�b�g����B 
        //�f�[�^�R�[�h request_code        �o�������FGI801
        l_params.setRequestCode(WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER);
        l_params.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        l_params.setBranchCode(l_subAccount.getMainAccount().getBranch().getBranchCode());
        l_params.setAccountCode(l_subAccount.getMainAccount().getAccountCode());
        AioOrderUnitRow l_orderUnitRow = 
            (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());

        try
        {
            log.debug("�o�����������L���[Params = " + 
                    HostPaymentOrderDao.findRowByPk(
                            (HostPaymentOrderPK)l_params.getPrimaryKey()));

            WEB3DataAccessUtility.deleteRow(l_params);
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In �Y������o�����������L���[�e�[�u���̃��R�[�h���폜����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In �Y������o�����������L���[�e�[�u���̃��R�[�h���폜����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
    }
    
    /**
     * (validate�o���]��)<BR>
     * �o���]�͂̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j����.DB�����]�̓`�F�b�N�敪 = 0�F�����{ �̏ꍇ<BR>
     * <BR>
     * �@@�P-�P�j�o���\�z���擾����B<BR>
     * �@@  �@@ ����]�̓T�[�r�X.get�o���\�z�`0�␳�����`<BR>
     * <BR>
     * �@@�@@   [����]<BR>
     *   �@@�@@ �⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g<BR>
     *    �@@�@@��n���F �����P��.��n��<BR>
     * <BR>
     * �Q�j����.DB�����]�̓`�F�b�N�敪 = 1�F���{ �̏ꍇ<BR>
     * <BR>
     * �@@�Q-�P�j�o���\�z���擾����B<BR>
     * �@@  �@@ ����]�̓T�[�r�X.get�o���\�z<DB����>�`0�␳�����`<BR>
     * <BR>
     * �@@�@@   [����]<BR>
     *   �@@�@@ �⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g<BR>
     *    �@@�@@��n���F �����P��.��n��<BR>
     * <BR>
     * �R�jget�o���\�z()�̖߂�l < 0 �̏ꍇ�Afalse ��Ԃ��B<BR>
     * <BR>
     * �S�j�����P��.������ = �����c�Ɠ� ����<BR>
     * �@@�@@�����P��.��n�� = �����c�Ɠ� ����<BR>
     * �@@�@@is�o���ɔ��������ۏ؋��U�֎��{�@@=�@@true �̏ꍇ<BR>
     * <BR>
     * �@@�S-�P�j�����ۏ؋����o�]�͂��擾����B<BR>
     * �@@�@@�@@����]�̓T�[�r�X.get�����ۏ؋����o�]��<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@DB�����]�̓`�F�b�N�敪�F ����.DB�����]�̓`�F�b�N�敪<BR>
     * <BR>
     * �@@�S-�Q�jget�����ۏ؋����o�]��()�̖߂�l < 0 �̏ꍇ�Afalse ��Ԃ��B<BR>
     * <BR>
     * �T�j��L�ȊO�̏ꍇ�Atrue ��Ԃ��B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@param l_strDbCurrentPriceCheckDiv - (DB�����]�̓`�F�b�N�敪)<BR>
     * DB�����]�̓`�F�b�N�敪
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean validateCashoutTradingPower(
        AioOrderUnit l_orderUnit,
        String l_strDbCurrentPriceCheckDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateCashoutTradingPower(AioOrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //�⏕����
        SubAccount l_subAccount = null;
        try
        {
            //�⏕�����I�u�W�F�N�g���擾����B 
            l_subAccount = 
                l_accMgr.getSubAccount(
                    l_orderUnit.getAccountId(),  
                    l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�⏕�����I�u�W�F�N�g���擾", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //����]�̓T�[�r�X���擾����
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        double l_dblCashoutPossiblePrice = 0;
        // �P�j����.DB�����]�̓`�F�b�N�敪 = 0�F�����{ �̏ꍇ    
        if (WEB3DbCurrentPriceCheckDivDef.NOT_ENFORCEMENT.equals(l_strDbCurrentPriceCheckDiv))
        {
            // �@@�P-�P�j�o���\�z���擾����
            // �@@  �@@ ����]�̓T�[�r�X.get�o���\�z�`0�␳�����`                          
            // �@@�@@   [����]
            //   �@@�@@ �⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g
            //    �@@�@@��n���F �����P��.��n��
            l_dblCashoutPossiblePrice =
                l_tPTradingPowerService.getPaymentTradingPowerForCheck(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_orderUnit.getDeliveryDate());
        }
        else if (WEB3DbCurrentPriceCheckDivDef.ENFORCEMENT.equals(l_strDbCurrentPriceCheckDiv))
        {
            // �Q�j����.DB�����]�̓`�F�b�N�敪 = 1�F���{ �̏ꍇ                                            
            // �@@�Q-�P�j�o���\�z���擾����
            // �@@  �@@ ����]�̓T�[�r�X.get�o���\�z<DB����>�`0�␳�����`                                      
            // �@@�@@   [����]
            //   �@@�@@ �⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g
            //    �@@�@@��n���F �����P��.��n��
            l_dblCashoutPossiblePrice =
                l_tPTradingPowerService.getPaymentTradingPowerDBQuote(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_orderUnit.getDeliveryDate());
        }
        else
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�R�jget�o���\�z()�̖߂�l < 0 �̏ꍇ�Afalse ��Ԃ��B
        if(l_dblCashoutPossiblePrice < 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�S�j�����P��.������ = �����c�Ɠ� ����
        //�����P��.��n�� = �����c�Ɠ� ����
        //is�o���ɔ��������ۏ؋��U�֎��{�@@=�@@true �̏ꍇ
        //is�o���ɔ��������ۏ؋��U�֎��{   �����P�ʁF �擾���������P��.���XID
        Timestamp l_tsBaseDate = GtlUtils.getSystemTimestamp();
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBaseDate);
        Timestamp l_tsDate = l_genBizDate.roll(0);
        Timestamp l_tsDateNext = l_genBizDate.roll(1);
        AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsPaymentTodayDepositTransferEnforce =
            this.isPaymentTodayDepositTransferEnforce(l_orderUnit.getBranchId());
        if (WEB3DateUtility.compareToDay(WEB3DateUtility.getDate(
                l_aioOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                l_tsDate) == 0
            && WEB3DateUtility.compare(l_aioOrderUnitRow.getDeliveryDate(), l_tsDateNext) == 0
            && l_blnIsPaymentTodayDepositTransferEnforce)
        {
            //�S-�P�j�����ۏ؋����o�]�͂��擾����B
            //����]�̓T�[�r�X.get�����ۏ؋����o�]��
            //�⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g
            //DB�����]�̓`�F�b�N�敪�F ����.DB�����]�̓`�F�b�N�敪
            double l_dblTodayDepositWithdrawTradingPower =
                l_tPTradingPowerService.getTodayDepositWithdrawTradingPower(
                    (WEB3GentradeSubAccount)l_subAccount, l_strDbCurrentPriceCheckDiv);

            //�S-�Q�jget�����ۏ؋����o�]��()�̖߂�l < 0 �̏ꍇ�Afalse ��Ԃ��B
            if (l_dblTodayDepositWithdrawTradingPower < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        //�T�j��L�ȊO�̏ꍇ�Atrue ��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return true;
    } 

    /**
     * (update�o���f�[�^)<BR>
     * �����P�ʃe�[�u��.�������ʁA<BR>
     * �o�����������L���[�e�[�u��.�o���z�Ɉ���.�o���z���Z�b�g����B<BR>
     * �X�V���e�́uupdate�o���f�[�^_DB�X�V�d�l�v���Q�ƁB<BR>
     * <BR>
     * �P�j�����P�ʃe�[�u���̍X�V<BR>
     * <BR>
     * �@@�P�|�P�j�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B<BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�����P��ID�F ����.�����P��ID<BR>
     * <BR>
     * �@@�P�|�Q�j�������ʂɈ���.�o���z���A�X�V���t�ɃV�X�e���^�C���X�^���v���Z�b�g����B<BR>
     * <BR>
     * �@@�P�|�R�j�Y�����R�[�h���X�V����B<BR>
     * <BR>
     * �Q�j�o�����������L���[�e�[�u���̍X�V<BR>
     * <BR>
     * �@@�Q�|�P�j�ȉ��̏����ŁA�o�����������L���[�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�f�[�^�R�[�h�F "GI801"<BR>
     * �@@�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F ����.���X�R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�F ����.�ڋq�R�[�h<BR>
     * �@@�@@���ʃR�[�h�F ����.���ʃR�[�h<BR>
     * �@@�@@�����敪�F '3'�i�������j<BR>
     * <BR>
     * �@@�Q�|�Q�j�o���z�Ɉ���.�o���z���Z�b�g����B<BR>
     * <BR>
     * �@@�Q�|�R�j�Y�����R�[�h���X�V����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranceCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID
     * @@param l_dblCashOutAmt - (�o���z)<BR>
     * �o���z
     * @@throws WEB3BaseException
     */
    protected void updatePayMentData(String l_strInstitutionCode, String l_strBranceCode,
        String l_strAccountCode, String l_strOrderRequestNumber, long l_lngOrderUnitId,
        double l_dblCashOutAmt) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "updatePayMentData(String, String, String, String, long, double)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //�����P�ʃe�[�u���̍X�V
            //�ȉ��̏����ŁA�����P�ʃe�[�u�����烌�R�[�h���擾����B 
            //[��������] 
            //�����P��ID�F ����.�����P��ID 
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(l_lngOrderUnitId);
            log.debug("�����P�ʃe�[�u��Row := " + l_orderUnitRow);

            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_orderUnitRow);
            //�������ʂɈ���.�o���z���A�X�V���t�ɃV�X�e���^�C���X�^���v���Z�b�g����B 
            l_orderUnitParams.setQuantity(l_dblCashOutAmt);
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //�Y�����R�[�h���X�V����B
            l_queryProcessor.doUpdateQuery(l_orderUnitParams);
            log.debug("updated �����P�ʃe�[�u��Row := " + l_orderUnitParams);

            //�ȉ��̏����ŁA�o�����������L���[�e�[�u�����烌�R�[�h���擾����B
            //[��������]
            //�f�[�^�R�[�h�F "GI801"
            //�،���ЃR�[�h�F ����.�،���ЃR�[�h
            //���X�R�[�h�F ����.���X�R�[�h
            //�ڋq�R�[�h�F ����.�ڋq�R�[�h
            //���ʃR�[�h�F ����.���ʃR�[�h
            //�����敪�F '3'�i�������j
            String l_strQueryString =
                " request_code = ? and institution_code = ? and branch_code = ? " +
                " and account_code = ? and order_request_number = ? and status = ? ";
            Object[] l_objQueryDataContainer =  new Object[] { 
                WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER,
                l_strInstitutionCode,
                l_strBranceCode, 
                l_strAccountCode,
                l_strOrderRequestNumber, 
                WEB3AioHostStatusDef.NOT_DEAL};

            List l_lisHostPaymentOrderRows = l_queryProcessor.doFindAllQuery(
                HostPaymentOrderRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer);

            if (l_lisHostPaymentOrderRows.size() == 1)
            {
                HostPaymentOrderRow l_hostPaymentOrderRow = 
                    (HostPaymentOrderRow) l_lisHostPaymentOrderRows.get(0);
                HostPaymentOrderParams l_hostPaymentOrderParams = 
                    new HostPaymentOrderParams(l_hostPaymentOrderRow);

                //�o���z�Ɉ���.�o���z���Z�b�g����B
                l_hostPaymentOrderParams.setPaymentAmount((int)l_dblCashOutAmt);

                //�Y�����R�[�h���X�V����B
                l_queryProcessor.doUpdateQuery(l_hostPaymentOrderParams);
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //�g���A�J�E���g�}�l�[�W���擾����
            WEB3GentradeAccountManager l_accMgr = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //�⏕����
            SubAccount l_subAccount = null;
            try
            {
                //�⏕�����I�u�W�F�N�g���擾����B 
                l_subAccount =
                    l_accMgr.getSubAccount(
                        l_orderUnitParams.getAccountId(),
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�⏕�����I�u�W�F�N�g���擾", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //�]�͍Čv�Z���R�[��
            WEB3TPTradingPowerReCalcService l_service =
                (WEB3TPTradingPowerReCalcService) Services.getService(
                    WEB3TPTradingPowerReCalcService.class);
            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

            l_service.reCalcTradingPower(l_gentradeSubAccount);
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�o���ɔ��������ۏ؋��U�֎��{)<BR>
     * �o���ɔ��������ۏ؋��U�֎��{���ǂ����̔��f���s���B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���XID = ����.�����P��.���XID And<BR>
     * �@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.�o���ɔ��������ۏ؋��U�֎��{�敪 And<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = "1"<BR>
     * <BR>
     * <BR>
     * �Q�j��������.�v���t�@@�����X�̒l�� 0�F DEFAULT �̏ꍇ�Afalse��ԋp����B<BR>
     * �@@�@@1�F EXECUTE �̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isPaymentTodayDepositTransferEnforce(long l_lngBranchId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isPaymentTodayDepositTransferEnforce(long)";
        log.entering(STR_METHOD_NAME);

        //���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B
        //[����]
        //���XID = ����.���XID And
        //�v���t�@@�����X�� = �v���t�@@�����X��.�o���ɔ��������ۏ؋��U�֎��{�敪 And
        //�v���t�@@�����X���̘A�� = "1"
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
            l_branchPreferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
        if (l_branchPreferencesRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            String l_strPreferencesValue = l_branchPreferencesRow.getValue();
            //��������.�v���t�@@�����X�̒l�� 0�F DEFAULT �̏ꍇ�Afalse��ԋp����B
            if (WEB3CashoutTodayDepositTransferDivDef.DEFAULT.equals(l_strPreferencesValue))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            //1�F EXECUTE �̏ꍇ�Atrue��ԋp����B
            else if (WEB3CashoutTodayDepositTransferDivDef.EXECUTE.equals(l_strPreferencesValue))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
    }
}
@
