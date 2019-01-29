head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�XImpl(WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����F (���u) �V�K�쐬 �d�l�ύX���f��No.129
Revision History : 2007/05/16 �����F (���u) ���f��152
Revision History : 2007/05/28 �����F (���u) ���f��155
Revision History : 2008/12/10 ������ (���u) ���f��215
Revision History : 2009/11/19 �Ԑi    (���u) �y�g���K�[�����E���Ǘ��ҁz�t�w�l����������Q�Ή�
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.DefaultEqTypeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveUnitService;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.marketadaptor.WEB3EquityMarketRequestSenderServiceImpl;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl
    implements WEB3AdminEquityForcedSettleOrderApproveUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl.class);

    /**
     * @@roseuid 462CA4240321
     */
    public WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl()
    {

    }

    /**
     * (exec���F)<BR>
     * �������ω��������F�������s���B<BR>
     * �i�߂�l�@@false�F�G���[�Ȃ��@@true�F�G���[����j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҋ������ω��������F�^�񏳔F�ꌏ�T�[�r�X�jexec���F�v�Q�ƁB<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.15.1.4�@@send�������ϒ���<BR>
     * �@@�@@�@@�@@�Ď擾���������P�ʂ̒����P��Row���g�p���邱�ƁB<BR>
     * �@@�@@�@@�@@�܂��A�s�ꑗ�M�����Ɏ��s�����ꍇ�A<BR>
     * �@@�@@�@@�@@�u�V�K�����s�ꃁ�b�Z�[�W���M���s�B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException       <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_00237         <BR>
     * =============================================== <BR>
     * @@param l_forcedSettleOrderRow - (�������ϒ���Row)<BR>
     * �������ϒ���Row�I�u�W�F�N�g<BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 46076E8302A9
     */
    public boolean execApprove(
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow,
        WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execApprove(AdminEqForcedSettleOrderRow, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_forcedSettleOrderRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        EqtypeOrderUnitRow l_eqtypeOrderUnitRow  = null;
        EqTypeOrderUnit l_eqOrderUnit = null;
        WEB3GentradeSubAccount l_subAccount = null;
        WEB3MarginSettleContractOrderSpec l_settleContractOrderSpec = null;
        WEB3EquityContract l_contract = null;
        EqTypeSettleContractOrderEntry[] l_contractOrderEntryList = null;
        WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice = null;
        WEB3GentradeMainAccount l_account = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        try
        {
            try
            {
                // get�ڋq(����ID : )
                l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                    l_forcedSettleOrderRow.getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }


            //lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
            l_accountManager.lockAccount(
                l_account.getInstitution().getInstitutionCode(),
                l_account.getBranch().getBranchCode(),
                l_account.getAccountCode());

            try
            {
                //getOrderUnit(arg0 : long)
                l_eqOrderUnit =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_forcedSettleOrderRow.getOrderUnitId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }


            //is���F�^�񏳔F�����Ώے���(EqtypeOrderUnitRow)
            l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
            boolean l_blnIsApproveDealObjectOrder =
                WEB3AdminPMEquityDataManager.isApproveProcessTargetOrder(l_eqtypeOrderUnitRow);

            //is���F�^�񏳔F�����Ώے���()�̖߂�l == false�̏ꍇ
            if (!l_blnIsApproveDealObjectOrder)
            {
                //�����ΏۊO�ł���ׁA��������return����B
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            //create���ό����G���g��(�����P��ID : long)
            l_contractOrderEntryList =
                l_positionManager.createCloseMarginContractEntry(l_eqtypeOrderUnitRow.getOrderUnitId());

            try
            {
                //create�ԍϒ������e
                //(*)�����ݒ�d�l
                //���ҁF�@@�����P��Row.�����ID�ɊY�����鈵�ҁ@@��null�̏ꍇ��null���Z�b�g�B
                //���ό����G���g���F�@@create���ό����G���g��()�̖߂�l
                //�w�l�F�@@�����P��Row.�w�l
                //���s�����F�@@�����P��Row.���s����
                //�����������F�@@�����P��Row.�����������t
                //�ŋ敪�F�@@�����P��Row.�ŋ敪
                //�l�i�����F�@@�����P��Row.�l�i����
                //���������F�@@�����P��Row.��������
                //�����������Z�q�F�@@�����P��Row.�����������Z�q
                //�t�w�l��l�F�@@�����P��Row.�t�w�l��l
                //�iW�w�l�j�����w�l�F�@@�����P��Row.�iW�w�l�j�����w�l
                //���Ϗ����敪�F�@@�����P��Row.���Ϗ����敪
                //���񒍕��̒����P��ID�F�@@�����P��Row.���񒍕��̒����P��ID
                //�iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s����
                WEB3GentradeTrader l_trader = null;
                if (!l_eqtypeOrderUnitRow.getTraderIdIsNull())
                {
                    l_trader = (WEB3GentradeTrader)l_finObjectManager.getTrader(l_eqtypeOrderUnitRow.getTraderId());
                }

                Long l_firstOrderUnitId = null;
                if (!l_eqtypeOrderUnitRow.getFirstOrderUnitIdIsNull())
                {
                    l_firstOrderUnitId = new Long(l_eqtypeOrderUnitRow.getFirstOrderUnitId());
                }

                l_settleContractOrderSpec =
                    WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                        l_trader,
                        l_contractOrderEntryList,
                        l_eqtypeOrderUnitRow.getLimitPrice(),
                        l_eqtypeOrderUnitRow.getExecutionConditionType(),
                        l_eqtypeOrderUnitRow.getExpirationDate(),
                        l_eqtypeOrderUnitRow.getTaxType(),
                        l_eqtypeOrderUnitRow.getPriceConditionType(),
                        l_eqtypeOrderUnitRow.getOrderConditionType(),
                        l_eqtypeOrderUnitRow.getOrderCondOperator(),
                        l_eqtypeOrderUnitRow.getStopOrderPrice(),
                        l_eqtypeOrderUnitRow.getWLimitPrice(),
                        l_eqtypeOrderUnitRow.getClosingOrderType(),
                        l_firstOrderUnitId,
                        l_eqtypeOrderUnitRow.getWLimitExecCondType());

                //get����(����ID : long)
                //����ID�F�@@create���ό����G���g��()�̖߂�l��0�Ԗڂ̗v�f.����ID
                l_contract =
                    (WEB3EquityContract)l_positionManager.getContract(l_contractOrderEntryList[0].getContractId());

                //get�⏕����(����ID : long, �⏕����ID : long)
                l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_eqtypeOrderUnitRow.getAccountId(),
                    l_eqtypeOrderUnitRow.getSubAccountId());

            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            try
            {
                //validate�ԍϒ���(�⏕���� : SubAccount, �M�p�ԍϒ������e
                //[����]
                //�⏕�����F�@@get�⏕����()�̖߂�l
                //�M�p�ԍϒ������e�F�@@create�ԍϒ������e()�̖߂�l
                //�����F�@@get����()�̖߂�l
                EqTypeNewOrderValidationResult l_validationResult =
                    l_orderManager.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_contract);
                if (l_validationResult.getProcessingResult().isFailedResult())
                {
                    throw new WEB3BusinessLayerException(
                        l_validationResult.getProcessingResult().getErrorInfo(),
                        STR_METHOD_NAME);
                }

                // getTradedProduct( )
                WEB3EquityTradedProduct l_tradedProduct =
                    (WEB3EquityTradedProduct)l_contract.getTradedProduct();

                //validate���ϑ�������
                //[����]
                //�⏕�����F�@@get�⏕����()�̖߂�l
                //�����P��ID�F�@@�����P��Row.�����P��ID�i��d�S������Ȃ��悤�A�������Ɠ��l�̏������s���B�j
                //��������F�@@getTradedProduct()�̖߂�l
                //�ŋ敪�F�@@�M�p�ԍϒ������e.getTaxType( )
                //�ٍϋ敪�F�@@����.�ٍϋ敪
                //�ٍϊ����l�F�@@����.�ٍϊ����l
                //�����F�@@�M�p�ԍϒ������e.getTotalQuantity( )
                //���敪�F�@@����.���敪
                EqtypeContractRow l_contractRow =
                    (EqtypeContractRow)l_contract.getDataSourceObject();
                WEB3EquityTypeOrderManagerReusableValidations l_reusableValidations =
                    (WEB3EquityTypeOrderManagerReusableValidations)
                        WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                l_reusableValidations.validateSettleContractTotalQuantity(
                    l_subAccount,
                    l_eqtypeOrderUnitRow.getOrderUnitId(),
                    l_tradedProduct,
                    l_settleContractOrderSpec.getTaxType(),
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_contractRow.getContractType());

                //create�萔��(�����P�� : �����P��)
                //�����P�ʁF�@@�����P��Row��萶�����������P�ʃI�u�W�F�N�g
                WEB3GentradeCommission l_commission =
                    l_bizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqOrderUnit);

                //calc�T�Z���ϑ��v���
                //(*)�����ݒ�d�l
                //�萔���F�@@create�萔��()�̖߂�l
                //�w�l�F�@@�����P��Row.�w�l
                //�⏕�����F�@@get�⏕����()�̖߂�l
                //��������F�@@getTradedProduct()�̖߂�l
                //���ό����G���g���F�@@create���ό����G���g��()�̖߂�l
                //���ʁF�@@�ԍϒ������e.getTotalQuantity()
                //�����P�ʁF�@@null�i�Œ�j
                //�����萔�ʁF�@@0
                //������P���F�@@0
                //isSkip���z�`�F�b�N�F�@@false�i�X�L�b�v���Ȃ��j
                l_realizedProfitAndLossPrice = l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_eqtypeOrderUnitRow.getLimitPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_contractOrderEntryList,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    null,
                    0,
                    0,
                    false);

                //����I�������ꍇ
                try
                {
                    //get�����o�H�敪(����������� : �������, �،���ЃR�[�h : String, ����R�[�h�iSONAR�j : String)
                    //[����]
                    //������������F�@@getTradedProduct()�̖߂�l
                    //�،���ЃR�[�h�F�@@�ڋq.�،���ЃR�[�h
                    //����R�[�h�iSONAR�j�F�@@�����P��Row�̓�������
                    WEB3EquityFrontOrderService l_frontOrderService =
                        (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
                    String l_strSubmitOrderRouteDiv = l_frontOrderService.getSubmitOrderRouteDiv(
                        l_tradedProduct,
                        l_account.getInstitution().getInstitutionCode(),
                        l_eqtypeOrderUnitRow.getSonarTradedCode());

                    //update�����f�[�^(�Ǘ���, EqtypeOrderUnitRow, �T�Z���ϑ��v����v�Z����, String, boolean)
                    //[����]
                    //�Ǘ��ҁF�@@�擾�����Ǘ��҃I�u�W�F�N�g
                    //�����P��Row�F�@@�����P��Row
                    //�T�Z����v�Z���ʁF�@@calc�T�Z���ϑ��v���()�̖߂�l
                    //�����o�H�敪�F�@@get�����o�H�敪()�̖߂�l
                    //is���F�F�@@true
                    this.updateOrderData(
                        l_admin,
                        l_eqtypeOrderUnitRow,
                        l_realizedProfitAndLossPrice,
                        l_strSubmitOrderRouteDiv, true);
                    try
                    {
                        //getOrderUnit(arg0 : long)
                        //�X�V��̒����P�ʂ��Ď擾����B
                        //[����]
                        //arg0�F�@@�����P��Row.�����P��ID
                        l_eqOrderUnit =
                            (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_eqtypeOrderUnitRow.getOrderUnitId());
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error(l_ex.getMessage(), l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    //send�������ϒ���(�⏕����, �������, EqtypeOrderUnitRow)
                    //�������ϒ������s��ɑ��M����B
                    //[����]
                    //�⏕�����F�@@get�⏕����()�̖߂�l
                    //��������F�@@getTradedProduct()�̖߂�l
                    //�����P��Row�F�@@�����P��Row
                    EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
                    MarketRequestSendResult l_result =
                        this.sendForcedSettleOrder(l_subAccount, l_tradedProduct, l_orderUnitRow);

                    //�Ď擾���������P�ʂ̒����P��Row���g�p���邱�ƁB
                    //�܂��A�s�ꑗ�M�����Ɏ��s�����ꍇ�A
                    //�u�V�K�����s�ꃁ�b�Z�[�W���M���s�B�v�̗�O���X���[����B
                    if (l_result.getProcessingResult().isFailedResult())
                    {
                        log.error(STR_METHOD_NAME + " �V�K�����s�ꃁ�b�Z�[�W���M���s�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00237,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�V�K�����s�ꃁ�b�Z�[�W���M���s�B");
                    }
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //�������ɗ�O���X���[���ꂽ�ꍇ
                //update��������(�Ǘ���, EqtypeOrderUnitRow, String, boolean)
                //[����]
                //�Ǘ��ҁF�@@�擾�����Ǘ��҃I�u�W�F�N�g
                //�����P��Row�F�@@�����P��Row
                //�G���[�R�[�h�F�@@�X���[���ꂽ��O�N���X���擾�����G���[�R�[�h
                //is���F�F�@@true�i���F�j
                this.updateOrderExpire(
                    l_admin,
                    l_eqtypeOrderUnitRow,
                    l_ex.getErrorInfo().getErrorCode(),
                    true);
            }
            //�]�͍Čv�Z(�⏕���� : �⏕����)
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (exec�񏳔F)<BR>
     * �������ω������񏳔F�������s���B<BR>
     * �i�߂�l�@@false�F�G���[�Ȃ��@@true�F�G���[����j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҋ������ω��������F�^�񏳔F�ꌏ�T�[�r�X�jexec�񏳔F�v�Q�ƁB<BR>
     * @@param l_forcedSettleOrderRow - (�������ϒ���Row)<BR>
     * �������ϒ���Row�I�u�W�F�N�g<BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 46076E8302C8
     */
    public boolean execDisapprove(
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow,
        WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execDisapprove(AdminEqForcedSettleOrderRow, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_forcedSettleOrderRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        WEB3GentradeMainAccount l_account = null;
        WEB3GentradeSubAccount l_subAccount = null;
        EqTypeOrderUnit l_eqOrderUnit = null;
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
        try
        {
            //get�ڋq(����ID : )
            //����ID�F�@@�p�����[�^.�������ϒ���Row.����ID
            l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_forcedSettleOrderRow.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        l_accountManager.lockAccount(
            l_account.getInstitution().getInstitutionCode(),
            l_account.getBranch().getBranchCode(),
            l_account.getAccountCode());
        try
        {
            //getOrderUnit(arg0 : long)
            l_eqOrderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_forcedSettleOrderRow.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //is���F�^�񏳔F�����Ώے���(EqtypeOrderUnitRow)
        l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
        boolean l_blnIsApproveDealObjectOrder =
            WEB3AdminPMEquityDataManager.isApproveProcessTargetOrder(l_eqtypeOrderUnitRow);

        //is���F�^�񏳔F�����Ώے���()�̖߂�l == false�̏ꍇ
        if (!l_blnIsApproveDealObjectOrder)
        {
            //�����ΏۊO�ł���ׁA��������return����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //update�����f�[�^(�Ǘ���, EqtypeOrderUnitRow, �T�Z���ϑ��v����v�Z����, String, boolean)
        //[����]
        //�Ǘ��ҁF�@@�擾�����Ǘ��҃I�u�W�F�N�g
        //�����P��Row�F�@@�����P��Row
        //�T�Z����v�Z���ʁF�@@null
        //�����o�H�敪�F�@@null
        //is���F�F�@@false�i�񏳔F�j
        this.updateOrderData(l_admin, l_eqtypeOrderUnitRow, null, null, false);
        try
        {
            //get�⏕����(����ID : long, �⏕����ID : long)
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                l_eqtypeOrderUnitRow.getAccountId(),
                l_eqtypeOrderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�]�͍Čv�Z(�⏕���� : �⏕����)
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (update�����f�[�^)<BR>
     * �����f�[�^�����F�^�񏳔F��Ԃ�update����B<BR>
     * <BR>
     * �P�j�@@���F�����i�p�����[�^.is���F == true�j�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * �@@�P�|�P�j�@@�X�V��̒����P��Params�̍쐬<BR>
     * �@@�@@�p�����[�^.�����P��Row�̓��e���R�s�[����<BR>
     * �@@�@@�����P��Params�𐶐����A�X�V�l���Z�b�g����B<BR>
     * �@@�@@�X�V���e�́ADB�X�V�d�l<BR>
     * �@@�@@�u���������F_���������P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR>
     * <BR>
     * �@@�P�|�Q�j�@@��������Params�̐���<BR>
     * �@@�@@��������Params�𐶐����A�X�V�l���Z�b�g����B<BR>
     * �@@�@@�X�V���e�́ADB�X�V�d�l<BR>
     * �@@�@@�u���������F_�������������e�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �@@�P�|�R�j�@@�g�����������}�l�[�W��.update�����f�[�^()<BR>
     * �@@�@@���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[update�����f�[�^()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�X�V�l���Z�b�g���������P��Params���<BR>
     * �@@�@@�@@�@@�������������P�ʃI�u�W�F�N�g<BR>
     * �@@�@@�@@���������F�@@�X�V�l���Z�b�g������������Params���<BR>
     * �@@�@@�@@�@@�����������������I�u�W�F�N�g<BR>
     * <BR>
     * �Q�j�@@�񏳔F�����i�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * �@@�Q�|�P�j�@@this.update��������()��<BR>
     * �@@�@@�������Ϗ�����B<BR>
     * �@@�@@[update��������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�Ǘ��ҁF�@@�p�����[�^.�Ǘ���<BR>
     * �@@�@@�@@�����P��Row�F�@@�p�����[�^.�����P��Row<BR>
     * �@@�@@�@@�G���[�R�[�h�F�@@null<BR>
     * �@@�@@�@@is���F�F�@@�p�����[�^.is���F<BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@param l_eqtypeOrderUnitRow - (�����P��Row)<BR>
     * �����P��Row�I�u�W�F�N�g<BR>
     * @@param l_equityRealizedProfitAndLossPrice - (�T�Z����v�Z����)<BR>
     * �T�Z���ϑ��v����v�Z���ʃI�u�W�F�N�g<BR>
     * @@param l_strSubmitOrderRouteDiv - (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * @@param l_blnIsApprove - (is���F)<BR>
     * ���F�������ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�񏳔F<BR>
     * true�F�@@���F<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4608AD940143
     */
    protected void updateOrderData(
        WEB3Administrator l_admin,
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow,
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice,
        String l_strSubmitOrderRouteDiv,
        boolean l_blnIsApprove) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderData(WEB3Administrator, EqtypeOrderUnitRow" +
            "WEB3EquityRealizedProfitAndLossPrice, String, boolean)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //�P�j�@@���F�����i�p�����[�^.is���F == true�j�̏ꍇ�A
        //�@@�ȉ��̏��������{����B
        //�@@�P�|�P�j�@@�X�V��̒����P��Params�̍쐬
        //�@@�@@�p�����[�^.�����P��Row�̓��e���R�s�[����
        //�@@�@@�����P��Params�𐶐����A�X�V�l���Z�b�g����B
        //�@@�@@�X�V���e�́ADB�X�V�d�l
        //�@@�@@�u���������F_���������P�ʃe�[�u���d�l.xls�v�Q�ƁB
        if (l_blnIsApprove)
        {
            EqtypeOrderUnitParams l_orderUnitParams =
                new EqtypeOrderUnitParams(l_eqtypeOrderUnitRow);
            //���������ŏI�ʔ�  last_order_action_serial_no  �i�����l�j + 1
            l_orderUnitParams.setLastOrderActionSerialNo(
                l_eqtypeOrderUnitRow.getLastOrderActionSerialNo() + 1);

            //�����P��    price �p�����[�^.�T�Z���ϑ��v����v�Z����.�v�Z�P��
            l_orderUnitParams.setPrice(l_equityRealizedProfitAndLossPrice.getCalcUnitPrice());

            //�T�Z��n���   estimated_price   �p�����[�^.�T�Z���ϑ��v����v�Z����.�T�Z���ϑ��v���
            l_orderUnitParams.setEstimatedPrice(
                l_equityRealizedProfitAndLossPrice.getEstimatedRealizedProfitAndLossAmount());

            //�����o�H�敪   submit_order_route_div  �p�����[�^.�����o�H�敪
            l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

            //�����G���[���R�R�[�h  error_reason_code  0000�F����
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

            //�X�V���t  ���ݎ���
            l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            // ���F��ԋ敪  approve_status_type  1�F���F��
            l_orderUnitParams.setApproveStatusType(WEB3ApproveStatusType.APPROVED);

            //���F�҃R�[�h  approver_code  �p�����[�^.�Ǘ���.�Ǘ��҃R�[�h
            l_orderUnitParams.setApproverCode(l_admin.getAdministratorCode());

            //���F�^�񏳔F����  approve_timestamp ���ݎ���
            l_orderUnitParams.setApproveTimestamp(GtlUtils.getSystemTimestamp());

            //�@@�P�|�Q�j�@@��������Params�̐���
            //�@@�@@��������Params�𐶐����A�X�V�l���Z�b�g����B
            //�@@�@@�X�V���e�́ADB�X�V�d�l
            //�@@�@@�u���������F_�������������e�[�u��.xls�v�Q�ƁB
            EqtypeOrderActionParams l_orderActionParams = new EqtypeOrderActionParams();
            try
            {
                long l_lngOrderActionId = EqtypeOrderActionDao.newPkValue();
                l_orderActionParams.setOrderActionId(l_lngOrderActionId);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            l_orderActionParams.setAccountId(l_orderUnitParams.getAccountId());
            l_orderActionParams.setSubAccountId(l_orderUnitParams.getSubAccountId());
            if (l_orderUnitParams.getTraderIdIsNull())
            {
                l_orderActionParams.setTraderId(null);
            }
            else
            {
                l_orderActionParams.setTraderId(l_orderUnitParams.getTraderId());
            }
            l_orderActionParams.setOrderId(l_orderUnitParams.getOrderId());
            l_orderActionParams.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            if (l_orderUnitParams.getMarketIdIsNull())
            {
                l_orderActionParams.setMarketId(null);
            }
            else
            {
                l_orderActionParams.setMarketId(l_orderUnitParams.getMarketId());
            }
            l_orderActionParams.setOrderType(l_orderUnitParams.getOrderType());
            l_orderActionParams.setOrderEventType(OrderEventTypeEnum.APPROVED);
            if (l_orderUnitParams.getLimitPriceIsNull())
            {
                l_orderActionParams.setPrice(null);
            }
            else
            {
                l_orderActionParams.setPrice(l_orderUnitParams.getLimitPrice());
            }
            l_orderActionParams.setExecutionConditionType(l_orderUnitParams.getExecutionConditionType());
            l_orderActionParams.setPriceConditionType(l_orderUnitParams.getPriceConditionType());
            l_orderActionParams.setOrderConditionType(l_orderUnitParams.getOrderConditionType());
            l_orderActionParams.setOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
            if (l_orderUnitParams.getStopOrderPriceIsNull())
            {
                l_orderActionParams.setStopOrderPrice(null);
            }
            else
            {
                l_orderActionParams.setStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
            }
            if (l_orderUnitParams.getWLimitPriceIsNull())
            {
                l_orderActionParams.setWLimitPrice(null);
            }
            else
            {
                l_orderActionParams.setWLimitPrice(l_orderUnitParams.getWLimitPrice());
            }
            l_orderActionParams.setExpirationDate(l_orderUnitParams.getExpirationDate());
            l_orderActionParams.setQuantity(l_orderUnitParams.getQuantity());
            if (l_orderUnitParams.getConfirmedPriceIsNull())
            {
                l_orderActionParams.setConfirmedPrice(null);
            }
            else
            {
                l_orderActionParams.setConfirmedPrice(l_orderUnitParams.getConfirmedPrice());
            }
            if (l_orderUnitParams.getConfirmedQuantityIsNull())
            {
                l_orderActionParams.setConfirmedQuantity(null);
            }
            else
            {
                l_orderActionParams.setConfirmedQuantity(l_orderUnitParams.getConfirmedQuantity());
            }
            l_orderActionParams.setExecutedQuantity(null);
            l_orderActionParams.setOrderStatus(l_orderUnitParams.getOrderStatus());
            l_orderActionParams.setExpirationStatus(l_orderUnitParams.getExpirationStatus());
            l_orderActionParams.setOrderActionSerialNo(l_orderUnitParams.getLastOrderActionSerialNo());
            l_orderActionParams.setExecutedPrice(null);
            l_orderActionParams.setProductType(l_orderUnitParams.getProductType());
            l_orderActionParams.setProductId(l_orderUnitParams.getProductId());
            l_orderActionParams.setQuantityType(l_orderUnitParams.getQuantityType());
            if (l_orderUnitParams.getEstimatedPriceIsNull())
            {
                l_orderActionParams.setEstimatedPrice(null);
            }
            else
            {
                l_orderActionParams.setEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            }
            l_orderActionParams.setModifyCancelType(l_orderUnitParams.getModifyCancelType());
            l_orderActionParams.setOrderRootDiv(l_orderUnitParams.getOrderRootDiv());
            l_orderActionParams.setClosingOrderType(l_orderUnitParams.getClosingOrderType());
            l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            l_orderActionParams.setRequestType(l_orderUnitParams.getRequestType());
            l_orderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_orderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_orderActionParams.setIpAddress(null);
            l_orderActionParams.setOrgOrderConditionType(l_orderUnitParams.getOrgOrderConditionType());
            l_orderActionParams.setOrgOrderCondOperator(l_orderUnitParams.getOrgOrderCondOperator());
            if (l_orderUnitParams.getOrgStopOrderPriceIsNull())
            {
                l_orderActionParams.setOrgStopOrderPrice(null);
            }
            else
            {
                l_orderActionParams.setOrgStopOrderPrice(l_orderUnitParams.getOrgStopOrderPrice());
            }
            if (l_orderUnitParams.getOrgWLimitPriceIsNull())
            {
                l_orderActionParams.setOrgWLimitPrice(null);
            }
            else
            {
                l_orderActionParams.setOrgWLimitPrice(l_orderUnitParams.getOrgWLimitPrice());
            }
            l_orderActionParams.setOrgWLimitExecCondType(l_orderUnitParams.getOrgWLimitExecCondType());
            l_orderActionParams.setWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
            if (l_orderUnitParams.getWLimitBeforeLimitPriceIsNull())
            {
                l_orderActionParams.setWLimitBeforeLimitPrice(null);
            }
            else
            {
                l_orderActionParams.setWLimitBeforeLimitPrice(l_orderUnitParams.getWLimitBeforeLimitPrice());
            }
            l_orderActionParams.setWLimitBeforeExecCondType(l_orderUnitParams.getWLimitBeforeExecCondType());
            l_orderActionParams.setConfirmedExecConditionType(l_orderUnitParams.getConfirmedExecConditionType());

            //�@@�P�|�R�j�@@�g�����������}�l�[�W��.update�����f�[�^()���\�b�h���R�[������B
            EqTypeOrderUnit l_eqTypeOrderUnit =
               (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
            EqTypeOrderAction l_eqTypeOrderAction =
                (EqTypeOrderAction)l_orderManager.toOrderAction(l_orderActionParams);

            l_orderManager.updateOrderData(l_eqTypeOrderUnit, l_eqTypeOrderAction);
        }
        else
        {
            //�Q�j�@@�񏳔F�����i�P�j�ȊO�j�̏ꍇ�A
            //�@@�ȉ��̏��������{����B
            //�@@�Q�|�P�j�@@this.update��������()��
            //�@@�@@�������Ϗ�����B
            //�@@�@@[update��������()�Ɏw�肷�����]
            //�@@�@@�@@�Ǘ��ҁF�@@�p�����[�^.�Ǘ���
            //�@@�@@�@@�����P��Row�F�@@�p�����[�^.�����P��Row
            //�@@�@@�@@�G���[�R�[�h�F�@@null
            //�@@�@@�@@is���F�F�@@�p�����[�^.is���F
            this.updateOrderExpire(l_admin, l_eqtypeOrderUnitRow, null, l_blnIsApprove);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (send�������ϒ���)<BR>
     * �������ϒ������s��ɑ��M����B<BR>
     * <BR>
     * �P�j�@@DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec<BR>
     * �@@�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@�����P��ID�F�@@�p�����[�^.�����P��Row.�����P��ID<BR>
     * �@@�@@���ʁF�@@�p�����[�^.�����P��Row.��������<BR>
     * �@@�@@�w�l�F�@@�@@�p�����[�^.�����P��Row.�w�l<BR>
     * �@@�@@��������F�@@�p�����[�^.�������<BR>
     * �@@�@@is�����F<BR>
     * �@@�@@�@@�p�����[�^.�����P��Row.������� == "�������ԍ�"�̏ꍇ�Atrue�B<BR>
     * �@@�@@�@@�ȊO�Afalse�B<BR>
     * �@@�@@���s�����F�@@�p�����[�^.�����P��Row.���s����<BR>
     * �@@�@@�����������t�F�@@�p�����[�^.�����P��Row.�����������t<BR>
     * �@@�@@�����P��Params�F�@@�p�����[�^.�����P��Row<BR>
     * <BR>
     * �Q�j�@@DefaultEqTypeSettleContractOrderMarketRequestMessage�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@����ID�F�@@�p�����[�^.�����P��Row.����ID<BR>
     * �@@�@@�������e�F�@@�P�j�ɂĐ��������C���X�^���X<BR>
     * <BR>
     * �R�j�@@EqTypeMarketRequestSenderService���擾����B<BR>
     * �@@�������s�ꃊ�N�G�X�g���M�T�[�r�X<BR>
     * <BR>
     * �S�j�@@�R�j�ɂĎ擾�����T�[�r�X.send()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[send()�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�Q�j�ɂĐ�������Message�C���X�^���X<BR>
     * <BR>
     * �T�j�@@�S�j�̃��\�b�h�̖߂�l��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g<BR>
     * @@param l_eqtypeOrderUnitRow - (�����P��Row)<BR>
     * �����P��Row�I�u�W�F�N�g<BR>
     * @@return MarketRequestSendResult
     * @@roseuid 4608B6E00058
     */
    protected MarketRequestSendResult sendForcedSettleOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityTradedProduct l_tradedProduct,
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow)
    {
        final String STR_METHOD_NAME = "sendForcedSettleOrder(WEB3GentradeSubAccount," +
            "EqtypeTradedProduct, EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec
        //�@@�C���X�^���X�𐶐�����B
        //�@@[�R���X�g���N�^�Ɏw�肷�����]
        //�@@�@@�����P��ID�F�@@�p�����[�^.�����P��Row.�����P��ID
        //�@@�@@���ʁF�@@�p�����[�^.�����P��Row.��������
        //�@@�@@�w�l�F�@@�@@�p�����[�^.�����P��Row.�w�l
        //�@@�@@��������F�@@�p�����[�^.�������
        //�@@�@@is�����F
        //�@@�@@�@@�p�����[�^.�����P��Row.������� == "�������ԍ�"�̏ꍇ�Atrue�B
        //�@@�@@�@@�ȊO�Afalse�B
        //�@@�@@���s�����F�@@�p�����[�^.�����P��Row.���s����
        //�@@�@@�����������t�F�@@�p�����[�^.�����P��Row.�����������t
        //�@@�@@�����P��Params�F�@@�p�����[�^.�����P��Row
        boolean l_blnIsBuy = false;
        if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_eqtypeOrderUnitRow.getOrderType()))
        {
            l_blnIsBuy = true;
        }
        DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec l_settleOrderUnitSpec =
            new DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec(
                l_eqtypeOrderUnitRow.getOrderUnitId(),
                l_eqtypeOrderUnitRow.getQuantity(),
                l_eqtypeOrderUnitRow.getLimitPrice(),
                l_tradedProduct,
                l_blnIsBuy,
                l_eqtypeOrderUnitRow.getExecutionConditionType(),
                l_eqtypeOrderUnitRow.getExpirationDate(),
                l_eqtypeOrderUnitRow);

        //�Q�j�@@DefaultEqTypeSettleContractOrderMarketRequestMessage�𐶐�����B
        //�@@[�R���X�g���N�^�Ɏw�肷�����]
        //�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@����ID�F�@@�p�����[�^.�����P��Row.����ID
        //�@@�@@�������e�F�@@�P�j�ɂĐ��������C���X�^���X
        DefaultEqTypeSettleContractOrderMarketRequestMessage l_orderMarketRequestMessage =
            new DefaultEqTypeSettleContractOrderMarketRequestMessage(
                l_subAccount,
                l_eqtypeOrderUnitRow.getOrderId(),
                l_settleOrderUnitSpec);

        //�R�j�@@EqTypeMarketRequestSenderService���擾����B
        //�@@�������s�ꃊ�N�G�X�g���M�T�[�r�X
        WEB3EquityMarketRequestSenderServiceImpl l_marketRequestSenderService = 
            (WEB3EquityMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                ProductTypeEnum.EQUITY).getMarketAdapter().getMarketRequestSenderServce();

        //�S�j�@@�R�j�ɂĎ擾�����T�[�r�X.send()���\�b�h���R�[������B
        //�@@[send()�Ɏw�肷�����]
        //�@@�@@arg0�F�@@�Q�j�ɂĐ�������Message�C���X�^���X
        MarketRequestSendResult l_sendResult =
            l_marketRequestSenderService.send(l_orderMarketRequestMessage);

        //�T�j�@@�S�j�̃��\�b�h�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sendResult;
    }

    /**
     * (update��������)<BR>
     * �����f�[�^��������ԂɍX�V����B<BR>
     * <BR>
     * �P�j�@@�����G���[���R�R�[�h�̎擾<BR>
     * �@@�p�����[�^.�G���[�R�[�h != null�̏ꍇ�A<BR>
     * �@@�g�����������}�l�[�W��.get�����G���[���R�R�[�h()��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[get�����G���[���R�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�G���[�R�[�h�F�@@�p�����[�^.�G���[�R�[�h<BR>
     * <BR>
     * �@@���p�����[�^.�G���[�R�[�h == null�ł���΁A<BR>
     * �@@�@@�����G���[���R�R�[�h = "0000�F����"�Ƃ���B<BR>
     * <BR>
     * �Q�j�@@���������̎��{<BR>
     * �@@�Q�|�P�j�@@�����ʒm�L���[Params�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@��������Params�ɁA�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�f�[�^�R�[�h	���@@"AI813"�i���������ʒm�j<BR>
     * �@@�@@�،���ЃR�[�h	���@@�ڋq(*1).�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h	���@@�ڋq(*1).���X�R�[�h<BR>
     * �@@�@@�����R�[�h	���@@�ڋq(*1).�����R�[�h<BR>
     * �@@�@@���҃R�[�h	��<BR>
     * �@@�@@�@@�p�����[�^.�����P��Row.�����ID�ɊY�����鈵��.���҃R�[�h<BR>
     * �@@�@@�@@��null�ł���΁Anull���Z�b�g�B<BR>
     * �@@�@@���ʃR�[�h	���@@�p�����[�^.�����P��Row.���ʃR�[�h<BR>
     * �@@�@@��萔��	���@@�p�����[�^.�����P��Row.��萔��<BR>
     * �@@�@@�������R�R�[�h	���@@"�������j��"<BR>
     * �@@�@@�����ʒm�敪	���@@"����"<BR>
     * �@@�@@�G���[���b�Z�[�W	���@@�擾���������G���[���R�R�[�h<BR>
     * �@@�@@�@@���G���[���b�Z�[�W�́A�����P��.�����G���[���R�R�[�h�ɋL�^�����B<BR>
     * �@@�@@�����敪	���@@"������"<BR>
     * <BR>
     * �@@�@@(*1)�p�����[�^.�����P��Row.����ID�ɊY������ڋq���g�p����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@���������ʒm�ꌏ�T�[�r�XImpl.exec����()���R�[������B<BR>
     * �@@�@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A<BR>
     * �@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB<BR>
     * �@@�@@�i�Q�l�FWEB3GentradeDaemonTriggerManager.startThread()�j<BR>
     * <BR>
     * �@@�@@[exec����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���������ʒm�L���[Params�F�@@�v���p�e�B�Z�b�g����Params<BR>
     * �@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��Row��萶�����������P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * �R�j�@@RAC�R���e�L�X�g�ɒl���ăZ�b�g����B<BR>
     * �@@�������ʒm�ꌏ�T�[�r�X�ƕR�t���������菈��RAC�R���e�L�X�g�C���^�Z�v�^�ɂ�<BR>
     * �@@��RAC�̃N���A�����Ă��邽�߁A�Đݒ肪�K�v�B<BR>
     * <BR>
     * �@@�R�|�P�j�@@RAC�R���e�L�X�g�Ɍ���ID���Z�b�g����B<BR>
     * �@@�@@WEB3DescendRacCtxService.setAccountIdCtx()��call����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@����.�����P��Row.����ID<BR>
     * <BR>
     * �S�j�@@�񏳔F�E�������s���̋L�^<BR>
     * �@@�g�����������}�l�[�W��.update�����f�[�^()���R�[������B<BR>
     * <BR>
     * �@@[update�����f�[�^()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�X�V�l���Z�b�g���������P��Params��萶������<BR>
     * �@@�@@�@@�����P�ʃI�u�W�F�N�g<BR>
     * �@@�@@���������F�@@null�i�Œ�j<BR>
     * <BR>
     * �@@���X�V�l�ݒ���e�ɂ��ẮA�ȉ���DB�X�V�d�l���Q�ƁB<BR>
     * �@@�@@[�񏳔F�����i�p�����[�^.is���F == false ���� �擾����<BR>
     * �@@�@@�@@�����G���[���R�R�[�h == "0000�F����"�j�̏ꍇ]<BR>
     * �@@�@@�@@�u�������񏳔F_���������P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR>
     * <BR>
     * �@@�@@[�������s�i��L�ȊO�j�̏ꍇ]<BR>
     * �@@�@@�@@�u���F�^�񏳔F�������s_���������P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@param l_eqtypeOrderUnitRow - (�����P��Row)<BR>
     * �����P��Row�I�u�W�F�N�g<BR>
     * @@param l_strErrorCode - (�G���[�R�[�h)<BR>
     * WEB3ErrorCatalog�ɒ�`����Ă���G���[�R�[�h�B<BR>
     * @@param l_blnIsApprove - (is���F)<BR>
     * ���F�������ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�񏳔F<BR>
     * true�F�@@���F<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4609EAFF00E2
     */
    protected void updateOrderExpire(
        WEB3Administrator l_admin,
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow,
        String l_strErrorCode,
        boolean l_blnIsApprove) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderExpire(WEB3Administrator," +
            "EqtypeOrderUnitRow, String, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeMainAccount l_account = null;
        String l_strErrorReasonCode = null;
        String l_strTraderCode = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        final WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        final EqtypeOrderUnitRow l_finalOrderUnitRow = l_eqtypeOrderUnitRow;

        try
        {
            l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_finalOrderUnitRow.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�P�j�@@�����G���[���R�R�[�h�̎擾
        //�@@�p�����[�^.�G���[�R�[�h != null�̏ꍇ�A
        //�@@�g�����������}�l�[�W��.get�����G���[���R�R�[�h()��
        //�@@�R�[������B
        //�@@[get�����G���[���R�R�[�h()�Ɏw�肷�����]
        //�@@�@@�G���[�R�[�h�F�@@�p�����[�^.�G���[�R�[�h
        //�@@���p�����[�^.�G���[�R�[�h == null�ł���΁A
        //�@@�@@�����G���[���R�R�[�h = "0000�F����"�Ƃ���B
        if (l_strErrorCode != null)
        {
            l_strErrorReasonCode = l_orderManager.getErrorReasonCode(l_strErrorCode);
        }
        else
        {
            l_strErrorReasonCode = WEB3ErrorReasonCodeDef.NORMAL;
        }

        //�Q�j�@@���������̎��{
        //�@@�Q�|�P�j�@@�����ʒm�L���[Params�𐶐�����B
        final HostEqtypeCloseOrderNotifyParams l_closeOrderNotityParams =
            new HostEqtypeCloseOrderNotifyParams();

        //�@@�Q�|�Q�j�@@��������Params�ɁA�ȉ��̃v���p�e�B���Z�b�g����B
        //�@@�@@�f�[�^�R�[�h ���@@"AI813"�i���������ʒm�j
        l_closeOrderNotityParams.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
        //�@@�@@�،���ЃR�[�h ���@@�ڋq(*1).�،���ЃR�[�h
        l_closeOrderNotityParams.setInstitutionCode(l_account.getInstitution().getInstitutionCode());
        //�@@�@@���X�R�[�h ���@@�ڋq(*1).���X�R�[�h
        l_closeOrderNotityParams.setBranchCode(l_account.getBranch().getBranchCode());
        //�@@�@@�����R�[�h ���@@�ڋq(*1).�����R�[�h
        l_closeOrderNotityParams.setAccountCode(l_account.getAccountCode());
        //�@@�@@���҃R�[�h ��
        //�@@�@@�@@�p�����[�^.�����P��Row.�����ID�ɊY�����鈵��.���҃R�[�h
        //�@@�@@�@@��null�ł���΁Anull���Z�b�g�B
        if (l_finalOrderUnitRow.getTraderIdIsNull())
        {
            l_strTraderCode = null;
        }
        else
        {
            l_strTraderCode = l_finalOrderUnitRow.getTraderId() + "";
        }
        l_closeOrderNotityParams.setTraderCode(l_strTraderCode);
        //�@@�@@���ʃR�[�h ���@@�p�����[�^.�����P��Row.���ʃR�[�h
        l_closeOrderNotityParams.setOrderRequestNumber(l_finalOrderUnitRow.getOrderRequestNumber());
        //�@@�@@��萔�� ���@@�p�����[�^.�����P��Row.��萔��
        l_closeOrderNotityParams.setExecutedQuantity(l_finalOrderUnitRow.getExecutedQuantity());
        //�@@�@@�������R�R�[�h ���@@"�������j��"
        l_closeOrderNotityParams.setReasonCode(WEB3ExpirationReasonCodeDef.ORDER_CANCEL);
        //�@@�@@�����ʒm�敪 ���@@"����"
        l_closeOrderNotityParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
        //�@@�@@�G���[���b�Z�[�W ���@@�擾���������G���[���R�R�[�h
        //�@@�@@�@@���G���[���b�Z�[�W�́A�����P��.�����G���[���R�R�[�h�ɋL�^�����B
        l_closeOrderNotityParams.setErrorMessage(l_strErrorReasonCode);
        //�@@�@@�����敪 ���@@"������"
        l_closeOrderNotityParams.setStatus(WEB3StatusDef.NOT_DEAL);

        //�@@�Q�|�R�j�@@���������ʒm�ꌏ�T�[�r�XImpl.exec����()���R�[������B
        //�@@�@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A
        //�@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB
        //�@@�@@�i�Q�l�FWEB3GentradeDaemonTriggerManager.startThread()�j
        //�@@�@@[exec����()�Ɏw�肷�����]
        //�@@�@@�@@���������ʒm�L���[Params�F�@@�v���p�e�B�Z�b�g����Params
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��Row��萶�����������P�ʃI�u�W�F�N�g
       boolean l_blnFlag = false;
       try
       {
           final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

           l_queryProcesser.doTransaction(
               QueryProcessor.TX_CREATE_NEW,
               new TransactionCallback()
                   {
                       public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                       {
                           WEB3EquityReceiveCloseOrderUnitService l_service =
                               (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                                   WEB3EquityReceiveCloseOrderUnitService.class);

                           EqTypeOrderUnit l_orderUnit =
                               (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_finalOrderUnitRow);
                           try
                           {
                               l_service.execCloseOrder(l_closeOrderNotityParams, l_orderUnit);
                           }
                           catch (WEB3BaseException l_ex)
                           {
                               throw new DataCallbackException(l_ex.getErrorMessage(), l_ex);
                           }
                           return null;
                       }
                   }
           );
       }
       catch (DataCallbackException l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           l_blnFlag = true;
       }
       catch (DataException l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(), l_ex);
       }

       //�R�j�@@RAC�R���e�L�X�g�ɒl���ăZ�b�g����B
       //  �������ʒm�ꌏ�T�[�r�X�ƕR�t���������菈��RAC�R���e�L�X�g�C���^�Z�v�^�ɂ�
       //  ��RAC�̃N���A�����Ă��邽�߁A�Đݒ肪�K�v�B
       //  �R�|�P�j�@@RAC�R���e�L�X�g�Ɍ���ID���Z�b�g����B
       //      WEB3DescendRacCtxService.setAccountIdCtx()��call����B
       //      [����]
       //         ����.�����P��Row.����ID
       WEB3DescendRacCtxService l_descendRacCtxService =
           (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
       if (l_descendRacCtxService != null)
       {
           l_descendRacCtxService.setAccountIdCtx(l_eqtypeOrderUnitRow.getAccountId());
       }

       //�R�j�@@�񏳔F�E�������s���̋L�^
       //�@@�g�����������}�l�[�W��.update�����f�[�^()���R�[������B
       //�@@[update�����f�[�^()�Ɏw�肷�����]
       //�@@�@@�����P�ʁF�@@�X�V�l���Z�b�g���������P��Params��萶������
       //�@@�@@�@@�����P�ʃI�u�W�F�N�g
       //�@@�@@���������F�@@null�i�Œ�j
       //�@@���X�V�l�ݒ���e�ɂ��ẮA�ȉ���DB�X�V�d�l���Q�ƁB
       //�@@�@@[�񏳔F�����i�p�����[�^.is���F == false ���� �擾����
       //�@@�@@�@@�����G���[���R�R�[�h == "0000�F����"�j�̏ꍇ]
       //�@@�@@�@@�u�������񏳔F_���������P�ʃe�[�u���d�l.xls�v�Q�ƁB
       //�@@�@@[�������s�i��L�ȊO�j�̏ꍇ]
       //�@@�@@�@@�u���F�^�񏳔F�������s_���������P�ʃe�[�u���d�l.xls�v�Q�ƁB
       EqTypeOrderUnit l_orderUnit = null;
       try
       {
           l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_eqtypeOrderUnitRow.getOrderUnitId());
       }
       catch (NotFoundException l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
       EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
           new EqtypeOrderUnitParams((EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject());
       if (l_blnFlag)
       {
           //�u���F�^�񏳔F�������s_���������P�ʃe�[�u���d�l.xls�v��WEB3-EQTYPEADMIN-A-FT-0033�Q�ƁB
           //�X�V���t
           l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
           //���F��ԋ敪 9�F�G���[
           l_eqtypeOrderUnitParams.setApproveStatusType(WEB3ApproveStatusType.ERROR);
           //���F�҃R�[�h   �p�����[�^.�Ǘ���.�Ǘ��҃R�[�h
           l_eqtypeOrderUnitParams.setApproverCode(l_admin.getAdministratorCode());
           //���F�^�񏳔F����
           l_eqtypeOrderUnitParams.setApproveTimestamp(GtlUtils.getSystemTimestamp());
       }
       else if (!l_blnIsApprove && WEB3ErrorReasonCodeDef.NORMAL.equals(l_strErrorReasonCode))
       {
           //�u�������񏳔F_���������P�ʃe�[�u���d�l.xls�v�Q�ƁB
           //���F��ԋ敪 2�F�񏳔F
           l_eqtypeOrderUnitParams.setApproveStatusType(WEB3ApproveStatusType.NON_APPROVED);
           //���F�҃R�[�h   �p�����[�^.�Ǘ���.�Ǘ��҃R�[�h
           l_eqtypeOrderUnitParams.setApproverCode(l_admin.getAdministratorCode());
           //���F�^�񏳔F����
           l_eqtypeOrderUnitParams.setApproveTimestamp(GtlUtils.getSystemTimestamp());
       }
       else
       {
           //�u���F�^�񏳔F�������s_���������P�ʃe�[�u���d�l.xls�v�Q�ƁB
           //���F��ԋ敪 9�F�G���[
           l_eqtypeOrderUnitParams.setApproveStatusType(WEB3ApproveStatusType.ERROR);
           //���F�҃R�[�h   �p�����[�^.�Ǘ���.�Ǘ��҃R�[�h
           l_eqtypeOrderUnitParams.setApproverCode(l_admin.getAdministratorCode());
           //���F�^�񏳔F����
           l_eqtypeOrderUnitParams.setApproveTimestamp(GtlUtils.getSystemTimestamp());

       }
       EqTypeOrderUnit l_eqTypeOrderUnit =
           (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_eqtypeOrderUnitParams);
       l_orderManager.updateOrderData(l_eqTypeOrderUnit, null);

       log.exiting(STR_METHOD_NAME);
    }
}
@
