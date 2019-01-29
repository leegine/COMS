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
filename	WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ω������쐬�ꌏ�T�[�r�XImpl(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����q(���u) �V�K�쐬 �d�l�ύX���f��No.131�A���f��No.146�A���f��No.142
Revision History : 2007/06/04 �đo�g(���u) �d�l�ύX ���f��No.151�ANo.153�ANo.156�ANo.158�A�c�a�X�V�d�lNo.012
Revision History : 2007/06/13 �đo�g(���u) �c�a�X�V�d�lNo.013
Revision History : 2007/10/09 �����q(���u) �d�l�ύX ���f��No.165
Revision History : 2008/01/17 �И���(���u) �c�a�X�V�d�lNo.018
Revision History : 2008/12/10 ������(���u) �d�l�ύX ���f��No.214
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
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
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExpirationReasonCodeDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.eqtypeadmin.WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqDao;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqParams;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqRow;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleTempOrderCreateUnitService;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������ω������쐬�ꌏ�T�[�r�XImpl)<BR>
 * ���ۃN���X<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public abstract class WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl implements
    WEB3AdminEquityForcedSettleTempOrderCreateUnitService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl.class);

    /**
     * @@roseuid 462CA4230014
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl()
    {
     
    }
    
    /**
     * (exec�������쐬)<BR>
     * ���������ƁA�������̓o�^�����{����B<BR>
     * <BR>
     * �P�j�@@�����Ώے����̎擾<BR>
     * �@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get���ϒ������ꗗ()��call����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����Row�F�@@����.����Row�I�u�W�F�N�g<BR>
     * �@@�@@is�\�񒍕��l���v�F�@@����.is�A�������戵�\<BR>
     * <BR>
     * �Q�j�@@���������̎��{<BR>
     * �@@�P�j�Ŏ擾����ArrayList��Loop���A�v�f���Ɏ����������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�v�f�̌^���h���������P��Row�h�̏ꍇ<BR>
     * �@@�@@�Q�|�P�|�P�j�@@�������ϒ����̔���<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.is�������ϒ���() �� true�̏ꍇ�A<BR>
     * �@@�@@�@@���̗v�f�ɏ������ڍs����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�g�����������}�l�[�W��.toOrderUnit(���������P��Row)<BR>
     * <BR>
     * �@@�@@�@@�@@���������ϒ����͎��������Ȃ��B<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�@@��������L���[�����b�N����B<BR>
     * �@@�@@�@@���������T�[�r�X.lock������������L���[()��������������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�g�����������}�l�[�W��.toOrderUnit(���������P��Row)<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�R�j�@@�s�ꖢ���M�̃L���[����������B<BR>
     * �@@�@@�@@���������T�[�r�X.get������������L���[()��������������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�g�����������}�l�[�W��.toOrderUnit(���������P��Row)<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�S�j�@@�G���[�����̓o�^<BR>
     * �@@�@@�@@�����E���n�����A�s�ꑗ�M�ς݂̕ԍϒ����͎��������Ȃ��ŁA<BR>
     * �@@�@@�@@�G���[�����̓o�^���s���B<BR>
     * <BR>
     * �@@�@@�@@���������P��Row.�����J�e�S�� �� "�����E���n����"�̏ꍇ�A<BR>
     * �@@�@@�@@�܂��́A<BR>
     * �@@�@@�@@���������P��Row.�����J�e�S�� �� "�ԍϒ���"���A<BR>
     * �@@�@@�@@�I�i���������P��Row.�������� �� "�t�w�l"���A<BR>
     * �@@�@@�@@�@@�g�����������}�l�[�W��.is����������()��true�j���A<BR>
     * �@@�@@�@@�Q�|�P�|�R�j�̖߂�l �� null�̏ꍇ�A<BR>
     * �@@�@@�@@this.insert�������σG���[����()��call���A���̗v�f�ɏ������ڍs����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@����Row�F�@@����.����Row<BR>
     * �@@�@@�@@�@@�������ϗ��R�敪�F�@@����.�������ϗ��R�敪<BR>
     * �@@�@@�@@�@@�������ϗ]�͌v�Z���ʁF�@@����.�������ϗ]�͌v�Z����<BR>
     * �@@�@@�@@�@@����ID�F�@@���������P��Row.����ID<BR>
     * �@@�@@�@@�@@�������ʁF�@@���������P��Row.��������<BR>
     * �@@�@@�@@�@@�w�l�F�@@null<BR>
     * �@@�@@�@@�@@�����G���[���R�R�[�h�F<BR>
     * �@@�@@�@@�@@�@@[�����E���n�����̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@"�����E���n�����o�^�σG���["<BR>
     * �@@�@@�@@�@@�@@[�s�ꑗ�M�ς̕ԍϒ����̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@"�����c���s���G���["<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�T�j�@@������������<BR>
     * �@@�@@�@@this.expire���ϒ���()��call���A�Y�������̎������������{����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���������P��Row�F�@@�����Ώۂ̗v�f<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�v�f�̌^���h�����\�񒍕��P��Row�h�̏ꍇ<BR>
     * �@@�@@�@@this.expire�\�񌈍ϒ���()��call���A�Y�������̎������������{����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�����\�񒍕��P��Row�F�@@�����Ώۂ̗v�f<BR>
     * <BR>
     * �R�j�@@RAC�R���e�L�X�g�ɒl���ăZ�b�g����B<BR>
     * �@@��this.expire���ϒ���()���ŃR�[�����Ă��鎸���ʒm�ꌏ�T�[�r�X�ƕR�t��<BR>
     * �@@���������菈��RAC�R���e�L�X�g�C���^�Z�v�^�ɂ�RAC�̃N���A�����Ă��邽�߁A�Đݒ肪�K�v�B<BR>
     * <BR>
     * �@@�R�|�P�j�@@RAC�R���e�L�X�g�Ɍ���ID���Z�b�g����B<BR>
     * �@@�@@WEB3DescendRacCtxService.setAccountIdCtx()��call����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@����.����Row.����ID<BR>
     * <BR>
     * �S�j�@@�������̍쐬<BR>
     * �@@this.submit�ԍω�����()��call���A��������o�^����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����Row�F�@@����.����Row�I�u�W�F�N�g<BR>
     * �@@�@@�������ϗ��R�敪�F�@@����.�������ϗ��R�敪<BR>
     * �@@�@@�������ϗ]�͌v�Z���ʁF�@@����.�������ϗ]�͌v�Z���� <BR>
     * <BR>
     * @@param l_eqtypeContractRow - (����Row)<BR>
     * �������ϑΏۂ̌���Row�I�u�W�F�N�g<BR>
     * @@param l_strForcedSettleReasonType - (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪<BR>
     * @@param l_contractForceSettleResult - (�������ϗ]�͌v�Z����)<BR>
     * �]�͂Ōv�Z�������ʋ������ό��ʃI�u�W�F�N�g<BR>
     * @@param l_blnIsSuccOrderHandling - (is�A�������戵�\)<BR>
     * �A���������戵�\���ǂ����̃t���O<BR>
     * <BR>
     * true�F�@@�A�������戵�\<BR>
     * false�F�@@�A�������戵�s��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46105996030D
     */
    public void execTempOrderCreation(
        EqtypeContractRow l_eqtypeContractRow,
        String l_strForcedSettleReasonType,
        WEB3TPContractForcedSettleResult l_contractForceSettleResult,
        boolean l_blnIsSuccOrderHandling) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execTempOrderCreation(" +
            "EqtypeContractRow, String, WEB3TPContractForcedSettleResult, boolean)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����Ώے����̎擾
        //�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get���ϒ������ꗗ()��call����B    
        ArrayList l_lisCloseOrders = WEB3AdminPMEquityDataManager.getCloseOrderList(
            l_eqtypeContractRow, l_blnIsSuccOrderHandling);

        // �Q�j�@@���������̎��{
        // �P�j�Ŏ擾����ArrayList��Loop���A�v�f���Ɏ����������s���B
        int l_intSize = l_lisCloseOrders.size();
        for (int i = 0; i < l_intSize; i++)
        {
            // �Q�|�P�j�@@�v�f�̌^��instanceof�Ŕ��肵�A�������\�b�h���Ăѕ�����B
            // �Q�|�P�|�P�j�@@�v�f�̌^���h���������P��Row�h�̏ꍇ
            if (l_lisCloseOrders.get(i) instanceof EqtypeOrderUnitRow)
            {
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_lisCloseOrders.get(i);

                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderMgr =
                    (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
                // �����P�ʁF�@@�g�����������}�l�[�W��.toOrderUnit(���������P��Row)
                OrderUnit l_orderUnit =
                    l_orderMgr.toOrderUnit(l_eqtypeOrderUnitRow);
                EqTypeOrderUnit l_eqTypeorderUnit = (EqTypeOrderUnit)l_orderUnit;
                // �g�����������}�l�[�W��.is�������ϒ���()
                boolean l_blnIsForcedSettleOrder = l_orderMgr.isForcedSettleOrder(l_eqTypeorderUnit);
                // �g�����������}�l�[�W��.is�������ϒ���() �� true�̏ꍇ�A
                // ���̗v�f�ɏ������ڍs����B
                if (l_blnIsForcedSettleOrder)
                {
                    continue;
                }

                // �Q�|�P�|�Q�j�@@��������L���[�����b�N����B
                // ���������T�[�r�X.lock������������L���[()��������������B
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(
                        WEB3EquityFrontOrderService.class);
                l_frontOrderService.lockHostEqtypeOrderAll(l_eqTypeorderUnit);

                // �Q�|�P�|�R�j�@@�s�ꖢ���M�̃L���[����������B
                // �@@�@@�@@���������T�[�r�X.get������������L���[()��������������B
                HostEqtypeOrderAllParams l_hosrEqtypeOrderAll =
                    l_frontOrderService.getHostEqtypeOrderAll(l_eqTypeorderUnit);

                // �Q�|�P�|�S�j�@@�G���[�����̓o�^
                // �@@�@@�@@�����E���n�����A�s�ꑗ�M�ς݂̕ԍϒ����͎��������Ȃ��ŁA
                // �@@�@@�@@�G���[�����̓o�^���s���B
                // ���������P��Row.�����J�e�S�� �� "�����E���n����"�̏ꍇ�A
                // �܂��́A 
                // ���������P��Row.�����J�e�S�� �� "�ԍϒ���"���A
                // �I�i���������P��Row.�������� �� "�t�w�l"���A
                // �g�����������}�l�[�W��.is����������()��true�j���A
                // �Q�|�P�|�R�j�̖߂�l �� null�̏ꍇ�A
                // this.insert�������σG���[����()��call���A���̗v�f�ɏ������ڍs����B
                boolean l_blnIsNotOrderedOrder = l_orderMgr.isNotOrderedOrder(l_eqTypeorderUnit);
                if (OrderCategEnum.SWAP_MARGIN.equals(l_eqtypeOrderUnitRow.getOrderCateg()))
                {
                    this.insertForceSettleErrorOrder(
                        l_eqtypeContractRow,
                        l_strForcedSettleReasonType,
                        l_contractForceSettleResult,
                        new Long(l_eqtypeOrderUnitRow.getOrderId()),
                        l_eqtypeOrderUnitRow.getQuantity(),
                        null,
                        WEB3ErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR);

                    continue;
                }
                if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqtypeOrderUnitRow.getOrderCateg())
                    && !(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                        l_eqtypeOrderUnitRow.getOrderConditionType())
                    && l_blnIsNotOrderedOrder)
                    && l_hosrEqtypeOrderAll == null)
                {
                    this.insertForceSettleErrorOrder(
                        l_eqtypeContractRow,
                        l_strForcedSettleReasonType,
                        l_contractForceSettleResult,
                        new Long(l_eqtypeOrderUnitRow.getOrderId()),
                        l_eqtypeOrderUnitRow.getQuantity(),
                        null,
                        WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR);

                    continue;
                }

                // �Q�|�P�|�T�j�@@������������
                // this.expire���ϒ���()��call���A�Y�������̎������������{����B
                this.expireSettleOrder(l_eqtypeOrderUnitRow);
            }

            // �Q�|�Q�j�@@�v�f�̌^���h�����\�񒍕��P��Row�h�̏ꍇ
            // this.expire�\�񌈍ϒ���()��call���A�Y�������̎������������{����B
            else if (l_lisCloseOrders.get(i) instanceof RsvEqOrderUnitRow)
            {
                //�����\�񒍕��P��Row�F�@@�����Ώۂ̗v�f
                RsvEqOrderUnitRow l_rscEqOrderUnitRow =
                    (RsvEqOrderUnitRow)l_lisCloseOrders.get(i);
                // this.expire�\�񌈍ϒ���()��call���A�Y�������̎������������{����
                this.expireRsvSettleOrder(l_rscEqOrderUnitRow);
            }             
        }

        //�R�j�@@RAC�R���e�L�X�g�ɒl���ăZ�b�g����B
        // ��this.expire���ϒ���()���ŃR�[�����Ă��鎸���ʒm�ꌏ�T�[�r�X�ƕR�t��
        // ���������菈��RAC�R���e�L�X�g�C���^�Z�v�^�ɂ�RAC�̃N���A�����Ă��邽�߁A�Đݒ肪�K�v�B
        // �R�|�P�j�@@RAC�R���e�L�X�g�Ɍ���ID���Z�b�g����B
        //    WEB3DescendRacCtxService.setAccountIdCtx()��call����B
        //    [����]
        //       ����.����Row.����ID
        WEB3DescendRacCtxService l_descendRacCtxService =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        if (l_descendRacCtxService != null)
        {
            l_descendRacCtxService.setAccountIdCtx(l_eqtypeContractRow.getAccountId());
        }

        // �R�j�@@�������̍쐬
        // this.submit�ԍω�����()��call���A��������o�^����B
        this.submitRepayTempOrder(l_eqtypeContractRow,
            l_strForcedSettleReasonType,
            l_contractForceSettleResult);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�ԍω�����)<BR>
     * �ԍω������쐬�������s���B<BR>
     * <BR>
     * �����̃V�[�P���X�́A <BR>
     * �V�[�P���X�}�u�i�������ω������쐬�jsubmit�ԍω������v���Q�ƁB<BR>
     * =============================================== <BR>
     * ��̈ʒu    :  1.17 �i�������ϊ����`�F�b�N�j<BR>
     * �i�������ϊ����`�F�b�N�j<BR>
     * �Y�����������ϊ����𒴂��Ă���ꍇ�A<BR>
     * �u�������ϊ����`�F�b�N�G���[�v�̗�O��throw����B<BR>
     * �i������(get������()) �� ���ϊ����i����Row.getCloseDate()<BR>
     *  class         :  WEB3BusinessLayerException       <BR>
     *  tag            :  BUSINESS_ERROR_00748      <BR>
     * =============================================== <BR>
     * @@param l_eqtypeContractRow - (����Row)<BR>
     * ����Row<BR>
     * @@param l_strForcedSettleResonDiv - (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪<BR>
     * @@param l_contractForceSettleResult - (�������ϗ]�͌v�Z����)<BR>
     * ���ʋ������ό���<BR>
     * @@throws WEB3BaseException
     * @@roseuid 460899CA004D
     */
    protected void submitRepayTempOrder(
        EqtypeContractRow l_eqtypeContractRow,
        String l_strForcedSettleResonDiv,
        WEB3TPContractForcedSettleResult l_contractForceSettleResult) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitRepayTempOrder(EqtypeContractRow, String, " +
            "WEB3TPContractForcedSettleResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        WEB3EquityTradedProduct l_tradedProduct;
        WEB3GentradeSubAccount l_subAccount;
        WEB3GentradeMarket l_market ;
        MainAccount l_mainAccount ;
        WEB3EquityContract l_contract;
        
        // ����(�����h�c : long)
        try
        {
            l_contract =
                (WEB3EquityContract) l_positionManager.getContract(l_eqtypeContractRow.getContractId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__�e�[�u���ɊY������f�[�^������܂���B__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        // getQuantity()
        // �������ʂ��擾����B
        double l_dbQuantity = l_contract.getQuantity();

        // getLockedQuantity()
        // �����ɑ΂���A�S�Ẵ��b�N�����ʂ��擾����B 
        double l_dbLockedQuantity = l_contract.getLockedQuantity();

        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dbQuantity));
        BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_dbLockedQuantity));
        BigDecimal l_bdSubQuantity = l_bdQuantity.subtract(l_bdLockedQuantity);
        if (l_bdQuantity.compareTo(l_bdLockedQuantity) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // EqTypeSettleContractOrderEntry(arg0 : long, arg1 : double)
        // ���ό����G���g���𐶐�����B
        EqTypeSettleContractOrderEntry l_entry =
            new EqTypeSettleContractOrderEntry(
                l_eqtypeContractRow.getContractId(), l_bdSubQuantity.doubleValue());

        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
            new EqTypeSettleContractOrderEntry[]{l_entry};
        try
        {
            // getTradedProduct
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productMgr.getTradedProduct(
                    l_eqtypeContractRow.getProductId(), l_eqtypeContractRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__�e�[�u���ɊY������f�[�^������܂���B__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        double l_dblLimitPrice = 0;
        // get�����P��(�������, boolean)
        // �����P�����擾����B
        if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_dblLimitPrice = this.getLimitPrice(l_tradedProduct, true, l_contract);
        }
        else
        {
            l_dblLimitPrice = this.getLimitPrice(l_tradedProduct, false, l_contract);
        }

        // get������( )
        // ���������擾����B
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        TaxTypeEnum l_taxType = l_eqtypeContractRow.getTaxType();
        // create�ԍϒ������e(���� : ����, ���ό����G���g�� : EqTypeSettleContractOrderEntry[],
        // �w�l : double, ���s���� : EqTypeExecutionConditionType, ���������� : Date, �ŋ敪 : TaxTypeEnum,
        // �l�i���� : String, �������� : String, �����������Z�q : String, �t�w�l��l : double,
        // (W�w�l)�����w�l : double, ���Ϗ����敪 : String, ���񒍕��̒����P��ID : Long)
        WEB3MarginSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                null,
                l_settleContractOrderEntrys,
                l_dblLimitPrice,
                EqTypeExecutionConditionType.NONE,
                l_datBizDate,
                l_taxType,
                WEB3PriceConditionDef.DEFAULT,
                WEB3OrderingConditionDef.DEFAULT,
                null,
                0D,
                0D,
                null,
                null);

        // get�⏕����(����ID : long, �⏕����ID : long)
        // �⏕�������擾����B

        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accMgr.getSubAccount(
                l_eqtypeContractRow.getAccountId(), l_eqtypeContractRow.getSubAccountId());
            // getMarket(�s��ID : long)
            // �s��I�u�W�F�N�g���擾����B
            
           l_market =
                (WEB3GentradeMarket)l_finObjectManager.getMarket(l_eqtypeContractRow.getMarketId());
        } 
        catch (NotFoundException l_ex)
        {
            log.error("__�e�[�u���ɊY������f�[�^������܂���B__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // create�萔��(�⏕���� : �⏕����, �s��R�[�h : String, ������ : Date,
        // �����`���l�� : String, �M�p����敪 : String, �ٍϊ����l : double, �����J�e�S�� : OrderCategEnum)
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
            l_subAccount, 
            l_market.getMarketCode(), 
            l_datBizDate, 
            WEB3ChannelDef.CALL_CENTER, 
            l_eqtypeContractRow.getRepaymentType(), 
            l_eqtypeContractRow.getRepaymentNum(), 
            OrderCategEnum.CLOSE_MARGIN);

        // setIs�w�l(is�w�l : boolean)
        // �萔���Ɏw�l/���s�̕ʂ�set����B 
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());

        //  calc�T�Z���ϑ��v���
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice =
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_commission,
                l_dblLimitPrice,
                l_subAccount,
                l_tradedProduct,
                l_settleContractOrderEntrys,
                l_settleContractOrderSpec.getTotalQuantity(),
                null,
                0D,
                0D,
                true);

        // get�ڋq(����ID : long)
        try
        {
            l_mainAccount =
                l_accMgr.getMainAccount(l_eqtypeContractRow.getAccountId());
        } 
        catch (NotFoundException l_ex)
        {
            log.error("__�e�[�u���ɊY������f�[�^������܂���B__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // getBranch( )
        //���X�I�u�W�F�N�g���擾����B
        l_mainAccount.getBranch();

        try
        {
            //validate���ϊ�������(���� : ����)
            WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
                (WEB3EquityTypeOrderManagerReusableValidations)
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            l_orderManagerReusableValidations.validateCloseDateExcess(l_contract);

            // validate�ԍϒ���
            // �����R�����s���B
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                l_orderManager.validateSettleContractOrder(
                    l_subAccount,
                    l_settleContractOrderSpec);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
            }
        }
        catch (Exception l_ex)
        {
            ErrorInfo l_errorInfo = null;
            if (l_ex instanceof WEB3BaseException)
            {
                l_errorInfo = ((WEB3BaseException)l_ex).getErrorInfo();
            }
            else if (l_ex instanceof WEB3BaseRuntimeException)
            {
                l_errorInfo = ((WEB3BaseRuntimeException)l_ex).getErrorInfo();
            }

            //�X���[���ꂽ��O�ɑΉ����钍���G���[���R�R�[�h���擾����B
            String l_strReasonCode = l_orderManager.getErrorReasonCode(l_errorInfo.getErrorCode());           

            // insert�������σG���[����
            // �������σG���[������o�^����B
             this.insertForceSettleErrorOrder(
                 (EqtypeContractRow)l_contract.getDataSourceObject(),
                 l_strForcedSettleResonDiv,
                 l_contractForceSettleResult,
                 null,
                 l_settleContractOrderSpec.getTotalQuantity(),
                 new Double(l_dblLimitPrice),
                 l_strReasonCode);

             log.exiting(STR_METHOD_NAME);
             return;
        }

        //�ۏ؋��a����
        Double l_marginMaintenanceRate = null;
        //�Ǐؔ�����
        Date l_datadditionalMarginDate = null;
        //�Ǐؔ���������̌o�ߓ���
        Integer l_additionalMarginAccruedDays = null;
        if (l_contractForceSettleResult != null)
        {
            l_marginMaintenanceRate = l_contractForceSettleResult.marginMaintenanceRate;
            l_datadditionalMarginDate = l_contractForceSettleResult.additionalMarginDate;
            l_additionalMarginAccruedDays = l_contractForceSettleResult.additionalMarginAccruedDays;
        }

        // setThreadLocalPersistenceEventInterceptor
        WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor l_forcedSettleTempOrderUpdateInterceptor =
            new WEB3AdminEquityForcedSettleTempOrderUpdateInterceptor(
                l_settleContractOrderSpec,
                l_commission,
                l_profitAndLossPrice,
                l_eqtypeContractRow.getRepaymentType(),
                Double.parseDouble(l_eqtypeContractRow.getRepaymentNum() + ""),
                WEB3ChannelDef.CALL_CENTER,
                WEB3OrderRootDivDef.FORCED_SETTLE,
                l_strForcedSettleResonDiv,
                WEB3ApproveStatusType.UNAPPROVED,
                l_marginMaintenanceRate,
                l_datadditionalMarginDate,
                l_additionalMarginAccruedDays);
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_forcedSettleTempOrderUpdateInterceptor);
       
        // createNewOrderId( )
        // �V�K����ID���擾����B
        long l_lngNewOrderId = l_orderManager.createNewOrderId();

        // getTradingPassword( )
        // ����p�X���[�h���擾����B
        String l_strTradingPassword = l_mainAccount.getTradingPassword();

        // submitSettleContractOrder
        // �����ԍϒ�����o�^����
        OrderSubmissionResult l_result = l_orderManager.submitSettleContractOrder(
            l_subAccount,
            l_settleContractOrderSpec,
            l_lngNewOrderId,
            l_strTradingPassword,
            true);

        if (l_result.getProcessingResult().isFailedResult())
        {
            log.error(l_result.getProcessingResult().toString());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // �]�͍Čv�Z(�⏕���� : �⏕����)
        // �]�͎c�����X�V����B
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);


        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (expire���ϒ���)<BR>
     * �����ʒm�ɂ��A�����̒����P�ʂ�����������B<BR>
     * <BR>
     * �P�j�@@�����̒����P�ʂɕR�t���L���ȗ\�񒍕����擾����B<BR>
     * �@@�����\�񒍕��X�V�T�[�r�X.get�L���\�񒍕��P�ʈꗗ()��call����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�e�����̒���ID�F�@@����.�����P��Row.����ID<BR>
     * <BR>
     * �Q�j�@@�����ʒm��call����B<BR>
     * �@@this.exec�����ʒm()��call���A�����̎����������s���B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�����P��Row�F�@@����.�����P��Row<BR>
     * <BR>
     * �R�j�@@�����P�ʂ̍X�V<BR>
     * �@@�R�|�P�j�@@������̒����̍Ď擾<BR>
     * �@@�@@�g�����������}�l�[�W��.getOrderUnit()��call���A<BR>
     * �@@�@@������̒����P�ʂ��Ď擾����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@orderUnitId�F�@@����.�����P��Row.�����P��ID<BR>
     * <BR>
     * �@@�R�|�Q�j�@@���������敪�ɒl���Z�b�g����B<BR>
     * �@@�@@�@@getOrderUnit()�̖߂�l�̒����P��.���������敪�Ɉȉ��l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�����������敪�ɃZ�b�g����l��<BR>
     * �@@�@@�@@�@@���������敪�F�@@"����������"<BR>
     * <BR>
     * �@@�R�|�R�j�@@�����P�ʂ��X�V����<BR>
     * �@@�@@�@@�g�����������}�l�[�W��.update�����f�[�^()��call����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�R�|�Q�j�Œl���Z�b�g���������P��<BR>
     * �@@�@@�@@�@@���������F�@@null<BR>
     * <BR>
     * �S�j�@@�\�񒍕��P�ʂ̍X�V<BR>
     * �@@�P�j�̖߂�l��Loop���A�v�f���ɒl�̍X�V���s���B<BR>
     * <BR>
     * �@@�S�|�P�j�@@������̗\�񒍕��̍Ď擾<BR>
     * �@@�@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��()��call���A<BR>
     * �@@�@@�����\�񒍕��P�ʂ��Ď擾����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����ID�F�@@�����v�f�̊����\�񒍕��P��Row.����ID<BR>
     * <BR>
     * �@@�S�|�Q�j�@@���������敪�ɒl���Z�b�g����B<BR>
     * �@@�@@get�����\�񒍕��P��()�̖߂�l�̗\�񒍕��P��.���������敪�Ɉȉ��l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�����������敪�ɃZ�b�g����l��<BR>
     * �@@�@@�@@�@@���������敪�F�@@"����������"<BR>
     * <BR>
     * �@@�S�|�R�j�@@�\�񒍕��P�ʂ��X�V����B<BR>
     * �@@�@@�����\�񒍕��X�V�T�[�r�X.update�\�񒍕��f�[�^()��call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�S�|�Q�j�Œl���Z�b�g���������P��<BR>
     * �@@�@@�@@���������F�@@null<BR>
     * <BR>
     * �T�j�@@������������L���[�̍폜<BR>
     * �@@�T�|�P�j�@@�폜�Ώۂ̔���<BR>
     * �@@�@@�����P��.����������"�t�w�l"���A<BR>
     * �@@�@@�g�����������}�l�[�W��.is����������()��true�̏ꍇ�A�ȍ~�̃L���[�̍폜���s��Ȃ��B<BR>
     * <BR>
     * �@@�T�|�Q�j�@@�L���[�̘_���폜<BR>
     * �@@�@@������������L���[�̈ȉ������ɊY�����郌�R�[�h���X�V����B<BR>
     * �@@�@@�X�V���e�ɂ��ẮADB�X�V�d�l�u��������_������������L���[�e�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �@@�@@�@@���_���폜���������<BR>
     * �@@�@@������������L���[�e�[�u��.�f�[�^�R�[�h�F�@@"AI801"<BR>
     * �@@�@@������������L���[�e�[�u��.�،���ЃR�[�h�F�@@�����P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@������������L���[�e�[�u��.���X�R�[�h�F�@@�����P��.getBranch().���X�R�[�h<BR>
     * �@@�@@������������L���[�e�[�u��.���ʃR�[�h�F�@@�����P��.���ʃR�[�h<BR>
     * �@@�@@������������L���[�e�[�u��.�Г��������ڂɊ܂܂�钍��Rev.(*1)�F�@@�����P��.����Rev.<BR>
     * �@@�@@������������L���[�e�[�u��.�����敪�F�@@"������"<BR>
     * <BR>
     * �@@�@@(*1)�J�n�ʒu�A������<BR>
     * �@@�@@�@@�@@���������T�[�r�X.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()�Ŏ擾���Ďw�肷��B<BR>
     * <BR>
     * @@param l_eqtypeOrderUnitRow - (�����P��Row)<BR>
     * �����Ώۂ̒����P��Row�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4607A55A02CE
     */
    protected void expireSettleOrder(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireSettleOrder(EqtypeOrderUnitRow l_eqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_eqtypeOrderUnitRow == null)
        {
            log.error("�p�����[�^�l�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�����̒����P�ʂɕR�t���L���ȗ\�񒍕����擾����B
        //�@@�����\�񒍕��X�V�T�[�r�X.get�L���\�񒍕��P�ʈꗗ()��call����B
        //�@@[����]
        //�@@�@@�e�����̒���ID�F�@@����.�����P��Row.����ID
        WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
        List l_lisOpenReserveEqtypeOrderUnit =
            l_updateService.getOpenReserveEqtypeOrderUnits(l_eqtypeOrderUnitRow.getOrderId());

        //�Q�j�@@�����ʒm��call����B
        // this.exec�����ʒm()��call���A�����̎����������s���B
        this.execCloseNotice(l_eqtypeOrderUnitRow);

        //�R�j�����P�ʂ̍X�V
        // �R�|�P�j������̒����̍Ď擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        OrderUnit l_orderUnit = null;
        try
        {
            // �g�����������}�l�[�W��.getOrderUnit()��call���A
            l_orderUnit = l_orderMgr.getOrderUnit(l_eqtypeOrderUnitRow.getOrderUnitId());
            l_branch = l_accountManager.getBranch(l_eqtypeOrderUnitRow.getBranchId());

            // �R�|�Q�j�@@���������敪�ɒl���Z�b�g����B
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
            EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                new EqtypeOrderUnitParams(l_eqTypeOrderUnitRow);
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            // getOrderUnit()�̖߂�l�̒����P��.���������敪�Ɉȉ��l���Z�b�g����B
            l_eqTypeOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.FORCED_EXPIRED);

            // �R�|�R�j�@@�����P�ʂ��X�V����
            // �g�����������}�l�[�W��.update�����f�[�^()��call����B
            EqTypeOrderUnit l_eqTypeOrderUnit1 =
                (EqTypeOrderUnit)l_orderMgr.toOrderUnit(l_eqTypeOrderUnitParams);
            l_orderMgr.updateOrderData(l_eqTypeOrderUnit1, null);

            //�S�j�\�񒍕��P�ʂ̍X�V
            //�P�j�̖߂�l��Loop���A�v�f���ɒl�̍X�V���s���B
            int l_intLoopCnt = 0;
            if (l_lisOpenReserveEqtypeOrderUnit != null)
            {
                l_intLoopCnt = l_lisOpenReserveEqtypeOrderUnit.size();
            }

            for (int i = 0; i < l_intLoopCnt; i++)
            {
                RsvEqOrderUnitRow l_rsvEqOrderUnitRowFromList =
                    (RsvEqOrderUnitRow)l_lisOpenReserveEqtypeOrderUnit.get(i);

                //������̗\�񒍕��̍Ď擾
                //�A�������}�l�[�W��Impl.get�����\�񒍕��P��()��call���A
                //�����\�񒍕��P�ʂ��Ď擾����B
                //[����]
                //����ID�F�@@�����v�f�̊����\�񒍕��P��Row.����ID
                WEB3ToSuccOrderManagerImpl l_orderManagerImpl =
                    (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnitImpl =
                    l_orderManagerImpl.getReserveEqtypeOrderUnit(l_rsvEqOrderUnitRowFromList.getOrderId());

                RsvEqOrderUnitRow l_rsvEqOrderUnitRow =
                    (RsvEqOrderUnitRow)l_eqTypeOrderUnitImpl.getDataSourceObject();
                RsvEqOrderUnitParams l_rsvEqOrderUnitParams =
                    new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow);

                //���������敪�ɒl���Z�b�g����B
                //get�����\�񒍕��P��()�̖߂�l�̗\�񒍕��P��.���������敪�Ɉȉ��l���Z�b�g����B
                //�����������敪�ɃZ�b�g����l��
                //���������敪�F�@@"����������"
                l_rsvEqOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.FORCED_EXPIRED);

                //�\�񒍕��P�ʂ��X�V����B
                //�����\�񒍕��X�V�T�[�r�X.update�\�񒍕��f�[�^()��call����B
                //[����]
                //�����P�ʁF�@@�S�|�Q�j�Œl���Z�b�g���������P��
                //���������F�@@null
                l_updateService.updateReserveOrderData(l_rsvEqOrderUnitParams, null);
            }

            // �T�j������������L���[�̍폜
            // �����P��.����������"�t�w�l"���A
            // �g�����������}�l�[�W��.is����������()��true�̏ꍇ�A�ȍ~�̃L���[�̍폜���s��Ȃ��B
            // �X�V���e�ɂ��ẮADB�X�V�d�l�u��������_������������L���[�e�[�u��.xls�v�Q�ƁB
            boolean l_blnIsNotOrderedOrder = l_orderMgr.isNotOrderedOrder(l_eqTypeOrderUnit);
            if (!(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                l_eqTypeOrderUnitParams.getOrderConditionType()) && l_blnIsNotOrderedOrder))
            {
                // ������������L���[�e�[�u��.�f�[�^�R�[�h�F�@@"AI801"
                String l_strRequestCode = WEB3HostRequestCodeDef.EQUITY_ORDER;
                // ������������L���[�e�[�u��.�،���ЃR�[�h�F�@@�����P��.getBranch().�،���ЃR�[�h
                String l_strInstitutionCode = l_branchRow.getInstitutionCode();
                // ������������L���[�e�[�u��.���X�R�[�h�F�@@�����P��.getBranch().���X�R�[�h
                String l_strBranchCode = l_branchRow.getBranchCode();
                // ������������L���[�e�[�u��.���ʃR�[�h�F�@@�����P��.���ʃR�[�h
                String l_strOrderRequestNumber = l_eqTypeOrderUnitParams.getOrderRequestNumber();
                // ������������L���[�e�[�u��.�Г��������ڂɊ܂܂�钍��Rev.(*1)�F�@@�����P��.����Rev.
                String l_strCorpCode = l_eqTypeOrderUnitParams.getOrderRev();
                // ������������L���[�e�[�u��.�����敪�F�@@"������"
                String l_strStatus = WEB3StatusDef.NOT_DEAL;

                // ���������T�[�r�X
                WEB3EquityFrontOrderService l_frontOrderService =
                    (WEB3EquityFrontOrderService)Services.getService(
                        WEB3EquityFrontOrderService.class);
                // ����Rev�J�n�ʒuIN�Г���������
                int l_intIndex = l_frontOrderService.getIndexOfOrderRevInCorpCode();
                // ����Rev����
                int l_intFigure = l_frontOrderService.getFigureOfOrderRev();

                QueryProcessor l_processor = Processors.getDefaultProcessor();
                StringBuffer l_strWhere = new StringBuffer();
                l_strWhere.append("request_code = ? ");
                l_strWhere.append(" and institution_code = ? ");
                l_strWhere.append(" and branch_code = ? ");
                l_strWhere.append(" and order_request_number = ? ");
                l_strWhere.append(" and substr(corp_code, " + l_intIndex + ", " + l_intFigure + ") = ?");
                l_strWhere.append(" and status = ? ");
                Object[] l_objWhereValues = {
                    l_strRequestCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strOrderRequestNumber,
                    l_strCorpCode,
                    l_strStatus};

                List l_lisHostEqtypeOrderAllRow = l_processor.doFindAllQuery(
                    HostEqtypeOrderAllRow.TYPE,
                    l_strWhere.toString(),
                    null,
                    l_objWhereValues);

                if (l_lisHostEqtypeOrderAllRow == null && l_lisHostEqtypeOrderAllRow.size() == 0)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�e�[�u���ɊY������f�[�^������܂���B");
                }

                //������������L���[�e�[�u��
                HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow =
                    (HostEqtypeOrderAllRow)l_lisHostEqtypeOrderAllRow.get(0);
                HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                     new HostEqtypeOrderAllParams(l_hostEqtypeOrderAllRow);

                // ��������ԍ�
                l_hostEqtypeOrderAllParams.setOrderActionSerialNo(
                    l_hostEqtypeOrderAllRow.getOrderActionSerialNo() + 1);
                // �����敪�i�X�e�[�^�X�j
                l_hostEqtypeOrderAllParams.setStatus(WEB3StatusDef.ADMIN_MANUAL_EXPIRED);
                // �X�V���t
                // ���ݎ���
                l_hostEqtypeOrderAllParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                Processors.getDefaultProcessor().doUpdateQuery(l_hostEqtypeOrderAllParams);
            }
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
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (expire�\�񌈍ϒ���)<BR>
     * �����\�񒍕��X�V�T�[�r�X�ɂ��A�����̒����P�ʂ�����������B<BR>
     * <BR>
     * �P�j�@@�����Ώۂ̔���<BR>
     * �@@�P�|�P�j�@@�����̍Ď擾<BR>
     * �@@�@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��()��call���A<BR>
     * �@@�@@�����\�񒍕��P�ʂ��Ď擾����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����ID�F�@@����.�����\�񒍕��P��Row.getOrderId()<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�Ώ۔���<BR>
     * �@@�@@�Ď擾���������\�񒍕��P��.�����L����� �� "�N���[�Y"�̏ꍇ�A<BR>
     * �@@�@@�������I������B�ireturn;�j<BR>
     * <BR>
     * �Q�j�@@�����������s��<BR>
     * �@@�����\�񒍕��X�V�T�[�r�XImpl.invalidate�\�񒍕��P��()��call����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�����\�񒍕��P�ʍs�F�@@����.�����\�񒍕��P��Row<BR>
     * �@@�@@�����G���[�R�[�h�F�@@null<BR>
     * <BR>
     * �R�j�@@������̒����̍Ď擾<BR>
     * �@@�A�������}�l�[�W��Impl.get�����\�񒍕��P��()��call���A<BR>
     * �@@������̊����\�񒍕��P�ʂ��Ď擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����ID�F�@@����.�����\�񒍕��P��Row.getOrderId()<BR>
     * <BR>
     * �S�j�@@�����P�ʂ̍X�V<BR>
     * �@@�S�|�P�j�@@���������敪�ɒl���Z�b�g����B<BR>
     * �@@�@@������̊����\�񒍕��P�ʂ̋��������敪�Ɉȉ��l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�����������敪�ɃZ�b�g����l��<BR>
     * �@@�@@���������敪�F�@@"����������"<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�����P�ʂ��X�V����<BR>
     * �@@�@@�����\�񒍕��X�V�T�[�r�XImpl.update�\�񒍕��f�[�^()��call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�����P�ʁF�@@�S�|�P�j�Œl���Z�b�g���������P��<BR>
     * �@@�@@�@@���������F�@@null<BR>
     * <BR>
     * @@param l_rsvEqOrderUnitRow - (�����\�񒍕��P��Row)<BR>
     * �����Ώۂ̗\�񒍕��P��Row�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46133695028F
     */
    protected void expireRsvSettleOrder(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireRsvSettleOrder(RsvEqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvEqOrderUnitRow == null)
        {
            log.error("�p�����[�^�l�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �A�������}�l�[�W��Impl
        WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        // ����ID�F�@@����.�����\�񒍕��P��Row.getOrderId()
        long l_lngOrderId = l_rsvEqOrderUnitRow.getOrderId();
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqtypeOrderUnit = null;
        //�P�|�P�j�@@�����̍Ď擾
        // �A�������}�l�[�W��Impl.get�����\�񒍕��P��()��call���A
        try
        {
            l_rsvEqtypeOrderUnit =
                l_orderManagerImpl.getReserveEqtypeOrderUnit(l_lngOrderId);
        } 
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        // �P�|�Q�j�@@�Ώ۔���
        // �Ď擾���������\�񒍕��P��.�����L����� �� "�N���[�Y"�̏ꍇ
        if (OrderOpenStatusEnum.CLOSED.equals(
            l_rsvEqtypeOrderUnit.getOrderOpenStatus()))
        {
            log.debug(" �Ď擾���������\�񒍕��P��.�����L����� �� '�N���[�Y' ");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // �Q�j�@@�����������s��
        // �����\�񒍕��X�V�T�[�r�XImpl.invalidate�\�񒍕��P��()��call����B
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqTypeOrderUpdateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
        l_rsvEqTypeOrderUpdateService.invalidateOrderUnit(l_rsvEqOrderUnitRow, null);

        // �R�j�@@������̒����̍Ď擾 
        // �A�������}�l�[�W��Impl.get�����\�񒍕��P��()��call���A
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqtypeOrderUnit1 = null;
        try
        {
            l_rsvEqtypeOrderUnit1 =
                l_orderManagerImpl.getReserveEqtypeOrderUnit(l_lngOrderId);
        } 
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        // �S�|�P�j�@@���������敪�ɒl���Z�b�g����B
        // ������̊����\�񒍕��P�ʂ̋��������敪�Ɉȉ��l���Z�b�g����B
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow1 =
            (RsvEqOrderUnitRow)l_rsvEqtypeOrderUnit1.getDataSourceObject();
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams =
            new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow1);
        l_rsvEqOrderUnitParams.setForcedExpireType(WEB3ForcedExpireType.FORCED_EXPIRED);

        // �S�|�Q�j�@@�����P�ʂ��X�V����
        // �����\�񒍕��X�V�T�[�r�XImpl.update�\�񒍕��f�[�^()��call����B
        l_rsvEqTypeOrderUpdateService.updateReserveOrderData(l_rsvEqOrderUnitParams, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (exec�����ʒm)<BR>
     * �P�j�@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�g���A�J�E���g�}�l�[�W��.getMainAccount()��call����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@����ID�F�@@����.�����P��Row.getAccountId()<BR>
     * <BR>
     * �Q�j�@@�����������s���B<BR>
     * �@@�Q�|�P�j�@@���������ʒm�L���[�̐���<BR>
     * �@@�@@���������ʒm�L���[Params�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���������C���X�^���X�̃v���p�e�B�Z�b�g���s��<BR>
     * <BR>
     * �@@�@@�@@���e�v���p�e�B�ɃZ�b�g����l��<BR>
     * �@@�@@�f�[�^�R�[�h�F�@@"AI813"�i���������ʒm�j<BR>
     * �@@�@@�،���ЃR�[�h�F�@@getMainAccount()�̖߂�l.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@getMainAccount()�̖߂�l.���X�R�[�h<BR>
     * �@@�@@�����R�[�h�F�@@getMainAccount()�̖߂�l.�����R�[�h<BR>
     * �@@�@@���҃R�[�h�F�@@null�i�Œ�j<BR>
     * �@@�@@���ʃR�[�h�F�@@����.�����P��Row.getOrderRequestNumber()<BR>
     * �@@�@@��萔�ʁF�@@����.�����P��Row.getExecutedQuantity()<BR>
     * �@@�@@�������R�R�[�h�F�@@"�������j��"<BR>
     * �@@�@@�����ʒm�敪�F�@@"����"<BR>
     * �@@�@@�G���[���b�Z�[�W�F�@@"����"<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�����������s��<BR>
     * �@@�@@���������ʒm�ꌏ�T�[�r�X���擾���Aexec����()��call����B<BR>
     * <BR>
     * �@@�@@[exec�����̈���]<BR>
     * �@@�@@�@@���������ʒm�L���[Params�F�@@�Q�|�Q�j�Ńv���p�e�B�Z�b�g���������I�u�W�F�N�g<BR>
     * �@@�@@�@@�����P�ʁF�@@�g�����������}�l�[�W��.toOrderUnit(����.�����P��Row)�̖߂�l<BR>
     * <BR>
     * @@param l_eqtypeOrderUnitRow - (�����P��Row)<BR>
     * �����P��Row<BR>
     * @@roseuid 46149337004E
     * @@throws WEB3BaseException
     */
    protected void execCloseNotice(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execCloseNotice(EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_eqtypeOrderUnitRow == null)
        {
            log.error("�p�����[�^�l�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j�@@�ڋq�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        MainAccount l_mainAccount = null;
        // �g���A�J�E���g�}�l�[�W��.getMainAccount()��call����B
        try
        {
            l_mainAccount =
                l_accMgr.getMainAccount(l_eqtypeOrderUnitRow.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        // �Q�j�@@�����������s���B
        //�@@�Q�|�P�j�@@���������ʒm�L���[�̐���
        //�@@���������ʒm�L���[Params�𐶐�����B
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
            new HostEqtypeCloseOrderNotifyParams();

        // �e�v���p�e�B�ɃZ�b�g����l
        // �f�[�^�R�[�h�F�@@"AI813"�i���������ʒm�j
        l_hostEqtypeCloseOrderNotifyParams.setRequestCode(
            WEB3HostRequestCodeDef.EQUITY_ORDER_CLOSE_NOTICE);
        // �،���ЃR�[�h�F�@@getMainAccount()�̖߂�l.�،���ЃR�[�h
        l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode(
            l_mainAccount.getInstitution().getInstitutionCode());
        // ���X�R�[�h�F�@@getMainAccount()�̖߂�l.���X�R�[�h
        l_hostEqtypeCloseOrderNotifyParams.setBranchCode(
            l_mainAccount.getBranch().getBranchCode());
        // �����R�[�h�F�@@getMainAccount()�̖߂�l.�����R�[�h
        l_hostEqtypeCloseOrderNotifyParams.setAccountCode(l_mainAccount.getAccountCode());
        // ���҃R�[�h�F�@@null�i�Œ�j
        l_hostEqtypeCloseOrderNotifyParams.setTraderCode(null);
        // ���ʃR�[�h�F�@@����.�����P��Row.getOrderRequestNumber()
        l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber(
            l_eqtypeOrderUnitRow.getOrderRequestNumber());
        // ��萔�ʁF�@@����.�����P��Row.getExecutedQuantity()
        l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(
            l_eqtypeOrderUnitRow.getExecutedQuantity());
        // �������R�R�[�h�F�@@"�������j��"
        l_hostEqtypeCloseOrderNotifyParams.setReasonCode(WEB3ExpirationReasonCodeDef.ORDER_CANCEL);
        // �����ʒm�敪�F�@@"����"
        l_hostEqtypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
        // �G���[���b�Z�[�W�F�@@"����"
        l_hostEqtypeCloseOrderNotifyParams.setErrorMessage(WEB3ErrorReasonCodeDef.NORMAL);

        // �Q�|�R�j�@@�����������s��
        // ���������ʒm�ꌏ�T�[�r�X���擾���Aexec����()��call����B
        WEB3EquityReceiveCloseOrderUnitService l_receiverCloseOrderUnitService =
            (WEB3EquityReceiveCloseOrderUnitService)Services.getService(
                WEB3EquityReceiveCloseOrderUnitService.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        //�����P�ʁF�@@�g�����������}�l�[�W��.toOrderUnit(���������P��Row)
        OrderUnit l_orderUnit =
            l_orderMgr.toOrderUnit(l_eqtypeOrderUnitRow);
        EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
        l_receiverCloseOrderUnitService.execCloseOrder(l_hostEqtypeCloseOrderNotifyParams, l_eqTypeOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����P��)<BR>
     * �������ϗp�̒����P�����擾����B<BR>
     * <BR>
     * ���}�[�P�b�g���C�N�����̏ꍇ�A�l������܂��͒l�������𒍕��P���Ƃ���B<BR>
     * �@@�ȊO�A0�i�F���s�j�𒍕��P���Ƃ���B<BR>
     * <BR>
     * �P�j�@@�}�[�P�b�g���C�N�����ȊO�̔���<BR>
     * �@@�@@����.�������.�X�����J�敪 �� "�}�[�P�b�g���C�N����"�̏ꍇ<BR>
     * �@@�@@0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��l�i�I�l�j�̔���<BR>
     * �@@�@@����.�������.��l�i�I�l�j �� 0�̏ꍇ<BR>
     * �@@�@@����.����.getContractPrice()�̖߂�l��ԋp����B<BR>
     * <BR>
     * �R�j�@@�l������l/�����l���擾����B<BR>
     * �@@�@@[����.is���ԍ� == true�̏ꍇ]<BR>
     * �@@�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get�������ϗp�l�����()��call����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@��������F�@@����.�������<BR>
     * <BR>
     * �@@�@@[�ȊO�̏ꍇ]<BR>
     * �@@�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get�������ϗp�l������()��call����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@��������F�@@����.�������<BR>
     * <BR>
     * �S�j�@@�����P���ԋp<BR>
     * �@@�R�j�Ŏ擾�����l��ԋp����B<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * �����������<BR>
     * @@param isCloseMarginShort - (is���ԍ�)<BR>
     * ���ԍϒ����̏ꍇ�Atrue�B<BR>
     * �ȊO�Afalse<BR>
     * @@param l_eqtypeContract - (����)<BR>
     * ����<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4609FA75037F
     */
    protected double getLimitPrice(
        TradedProduct l_tradedProduct,
        boolean isCloseMarginShort,
        WEB3EquityContract l_eqtypeContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLimitPrice(TradedProduct, boolean, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.error("�p�����[�^�l�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j�@@�}�[�P�b�g���C�N�����ȊO�̔���
        // ����.�������.�X�����J�敪 �� "�}�[�P�b�g���C�N����"�̏ꍇ 0��ԋp����B
        EqtypeTradedProductRow l_eqTypeTradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        if (!WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(
            l_eqTypeTradedProductRow.getOpenOtcDiv()))
        {
            return 0;
        }

        // �Q�j�@@��l�i�I�l�j�̔���
        // ����.�������.��l�i�I�l�j �� 0�̏ꍇ
        double l_dbLastClosingPrice =
            ((WEB3EquityTradedProduct)l_tradedProduct).getLastClosingPrice();
        if (l_dbLastClosingPrice == 0)
        {
            // ����.����.getContractPrice()�̖߂�l��ԋp����B
            double l_dbContractPrice = l_eqtypeContract.getContractPrice();
            log.exiting(STR_METHOD_NAME);
            return l_dbContractPrice;
        }

        // �R�j�@@�l������l/�����l���擾����B
        // [����.is���ԍ� == true�̏ꍇ]
        // ���i�Ǘ��i�����j�f�[�^�}�l�[�W��
        WEB3EquityTradedProduct l_equityTradedProduct =
            (WEB3EquityTradedProduct)l_tradedProduct;
        double l_dblPrice = 0;
        if (isCloseMarginShort)
        {
            // ���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get�������ϗp�l�����()��call����
            l_dblPrice = WEB3AdminPMEquityDataManager.getForcedSettleHighPriceRange(l_equityTradedProduct);
        }
        else
        {
            // ���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get�������ϗp�l������()��call����B
            l_dblPrice = WEB3AdminPMEquityDataManager.getForcedSettleLowPriceRange(l_equityTradedProduct);
        }

        // �S�j�����P���ԋp  
        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }
   
    /**
     * (insert�������σG���[����)<BR>
     * �������ϒ����Ɖ�Params�𐶐����A<BR>
     * �����̏�񂩂�l���Z�b�g���ċ������ϒ����Ɖ�e�[�u���ɂP��insert����B<BR>
     * �������A�����̏����Ŋ��Ƀ��R�[�h�����݂���ꍇ�́A�������Ȃ��B<BR>
     * <BR>
     * ���X�V���e�́A�ȉ���DB�ݒ�_�����Q�ƁB<BR>
     * �@@�@@�E�G���[�����o�^_�������ϒ����Ɖ�e�[�u���d�l.xls <BR>
     * <BR>
     * �P�j�@@�������ϒ����Ɖ�e�[�u������������B<BR>
     * �@@�@@QueryProcessor.doGetCountQuery()��call���A<BR>
     * �@@�@@�߂�l��1�̏ꍇ�́A�������I������B<BR>
     * <BR>
     * �@@�@@������������<BR>
     * �@@�@@�@@����ID�F�@@����.����ID(*)<BR>
     * �@@�@@�@@�������F�@@������ԊǗ�.get������()<BR>
     * �@@�@@�@@����ID�F�@@����.����Row.����ID<BR>
     * <BR>
     * (*)����.����ID��null�̏ꍇ�́A" is Null "�����������Ƃ���B<BR>
     * <BR>
     * �Q�j�@@�������ϒ����Ɖ�Params�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�P�j��Params�Ɉ�������ҏW�����l���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@QueryProcessor.doInsertQuery()��call���A���R�[�h��o�^����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@Row�F�@@�Q�j�Ńv���p�e�B�Z�b�g����Params<BR>
     * <BR>
     * @@param l_eqtypeContractRow - (����Row)<BR>
     * ����Row<BR>
     * @@param l_strForcedSettleResonDiv - (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪<BR>
     * @@param l_contractForceSettleResult - (�������ϗ]�͌v�Z����)<BR>
     * �������ϗ]�͌v�Z����<BR>
     * @@param l_orderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_dbOrdeNumber - (��������)<BR>
     * �G���[��������<BR>
     * @@param l_limitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@param l_strOrderErrorReasonCode - (�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 461D9F3601E3
     */
    protected void insertForceSettleErrorOrder(
        EqtypeContractRow l_eqtypeContractRow,
        String l_strForcedSettleResonDiv,
        WEB3TPContractForcedSettleResult l_contractForceSettleResult,
        Long l_orderId,
        double l_dblOrderNumber,
        Double l_limitPrice,
        String l_strOrderErrorReasonCode) throws WEB3BaseException
        
    {
        final String STR_METHOD_NAME =
            "insertForceSettleErrorOrder(EqtypeContractRow, String," +
            " WEB3TPContractForcedSettleResult, Long, double, Double, String)";
        log.entering(STR_METHOD_NAME);

        if (l_eqtypeContractRow == null)
        {
            log.error("�p�����[�^�l�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�������ϒ����Ɖ�e�[�u������������B
        // QueryProcessor.doGetCountQuery()��call���A
        // �߂�l��1�̏ꍇ�́A�������I������B
        // ������������
        //  ����ID�F�@@����.����ID(*)
        //  �������F�@@������ԊǗ�.get������()
        //  ����ID�F�@@����.����Row.����ID
        // (*)����.����ID��null�̏ꍇ�́A" is Null "�����������Ƃ���B
        StringBuffer l_sbQuerySql = new StringBuffer();
        List l_lisSqlValues = new ArrayList();
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        if (l_orderId == null)
        {
            l_sbQuerySql.append(" order_id is null ");
        }
        else
        {
            l_sbQuerySql.append(" order_id = ? ");
            l_lisSqlValues.add(l_orderId);
        }

        l_sbQuerySql.append(" and biz_date = ? ");
        l_lisSqlValues.add(l_strBizDate);

        l_sbQuerySql.append(" and contract_id = ? ");
        l_lisSqlValues.add(new Long(l_eqtypeContractRow.getContractId()));
        Object[] l_sqlValues = new Object[l_lisSqlValues.size()];
        l_lisSqlValues.toArray(l_sqlValues);

        QueryProcessor l_queryProcessor = null;
        int l_intQueryCnt;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_intQueryCnt = l_queryProcessor.doGetCountQuery(ForcedSettleOrderInqRow.TYPE,
                l_sbQuerySql.toString(),
                l_sqlValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_intQueryCnt >= 1)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�������ϒ����Ɖ�Params�𐶐�����
        ForcedSettleOrderInqParams l_foredSettleOrderInqParams =
            new ForcedSettleOrderInqParams();

        try
        {
            //�������ϒ����Ɖ�ID    forced_settle_order_inq_id  �����̔Ԃ����l
            l_foredSettleOrderInqParams.setForcedSettleOrderInqId(
                    ForcedSettleOrderInqDao.newPkValue());
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�����h�c   order_id   insert�������σG���[�����̈���.����ID
        l_foredSettleOrderInqParams.setOrderId(l_orderId);

        //�����h�c   account_id   ����.����ID
        l_foredSettleOrderInqParams.setAccountId(l_eqtypeContractRow.getAccountId());

        //�⏕�����h�c    sub_account_id     ����.�⏕����ID
        l_foredSettleOrderInqParams.setSubAccountId(l_eqtypeContractRow.getSubAccountId());

        //���X�h�c  branch_id    "�ڋq.���XID*�ڋq�̎���X)"
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                l_accManager.getMainAccount(l_eqtypeContractRow.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        l_foredSettleOrderInqParams.setBranchId(l_mainAccount.getBranch().getBranchId());

        //�������   order_type
        // ����.���敪 == "����"�̏ꍇ�F�@@5�F�����ԍϒ����i���ԍρj
        // ����.���敪 == "����"�̏ꍇ�F�@@6�F�����ԍϒ����i���ԍρj
        if (ContractTypeEnum.LONG.equals(l_eqtypeContractRow.getContractType()))
        {
            l_foredSettleOrderInqParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
        }
        if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_foredSettleOrderInqParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_SHORT);
        }

        //�����J�e�S��    order_categ     5�F�ԍϒ���
        l_foredSettleOrderInqParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);

        //�s��h�c   market_id  ����.�s��ID
        l_foredSettleOrderInqParams.setMarketId(l_eqtypeContractRow.getMarketId());

        //��������   quantity      insert�������σG���[�����̈���.��������
        l_foredSettleOrderInqParams.setQuantity(l_dblOrderNumber);

        //�w�l    limit_price   insert�������σG���[�����̈���.�w�l
        l_foredSettleOrderInqParams.setLimitPrice(l_limitPrice);

        //��n��   delivery_date
        //"�������.getDailyDeliveryDate( )�̖߂�l
        //�i* ��������3�c�Ɠ���B���Y���������������̏ꍇ��4�c�Ɠ��オ�߂����j"
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityTradedProduct l_tradedProduct = null;
        WEB3EquityProduct l_product = null;
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingMod.getProductManager();
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productMgr.getTradedProduct(
                    l_eqtypeContractRow.getProductId(),
                    l_eqtypeContractRow.getMarketId());
            l_product =
                (WEB3EquityProduct)l_productMgr.getProduct(l_eqtypeContractRow.getProductId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        l_foredSettleOrderInqParams.setDeliveryDate(l_tradedProduct.getDailyDeliveryDate());

        //������   biz_date   ������ԊǗ�.get������()(TradingCalendar.getCurrentBizDate( ))
        l_foredSettleOrderInqParams.setBizDate(l_strBizDate);

        //�����h�c  product_id       ����.����ID
        l_foredSettleOrderInqParams.setProductId(l_eqtypeContractRow.getProductId());

        //�󒍓���  received_date_time
        //  �T�[�o���ŃT�[�r�X���N�����ꂽ���ԁi�v�Z�����i���ʁj(*2) �������t�@@���Q�Ɓj
        Timestamp l_tsReceivedDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_foredSettleOrderInqParams.setReceivedDateTime(l_tsReceivedDate);

        //�����G���[���R�R�[�h    error_reason_code
        // insert�������σG���[�����̈���.�����G���[���R�R�[�h
        l_foredSettleOrderInqParams.setErrorReasonCode(l_strOrderErrorReasonCode);

        //�������ϗ��R�敪  forced_settle_reason_type
        //insert�������σG���[�����̈���.�������ϗ��R�敪
        l_foredSettleOrderInqParams.setForcedSettleReasonType(l_strForcedSettleResonDiv);

        //���F��ԋ敪    approve_status_type      9�F�G���[
        l_foredSettleOrderInqParams.setApproveStatusType(WEB3ApproveStatusType.ERROR);

        if (l_contractForceSettleResult != null)
        {
            //�ۏ؋��ێ���  margin_maintenance_rate
            //insert�������σG���[�����̈���.�������ϗ]�͌v�Z����.�ۏ؋��a����
            l_foredSettleOrderInqParams.setMarginMaintenanceRate(
                l_contractForceSettleResult.marginMaintenanceRate);

            //�Ǐؔ�����   additional_margin_date
            //insert�������σG���[�����̈���.�������ϗ]�͌v�Z����.�Ǐؔ�����
            l_foredSettleOrderInqParams.setAdditionalMarginDate(
                l_contractForceSettleResult.additionalMarginDate);

            //�Ǐ،o�ߓ���  additional_margin_accrued_days
            //insert�������σG���[�����̈���.�������ϗ]�͌v�Z����.�Ǐؔ���������̌o�ߓ���
            if (l_contractForceSettleResult.additionalMarginAccruedDays != null)
            {
                l_foredSettleOrderInqParams.setAdditionalMarginAccruedDays(
                    Long.parseLong((l_contractForceSettleResult.additionalMarginAccruedDays).intValue() + ""));
            }
        }
        else
        {
            l_foredSettleOrderInqParams.setMarginMaintenanceRate(null);
        }

        //�����h�c    contract_id     ����.����ID
        l_foredSettleOrderInqParams.setContractId(l_eqtypeContractRow.getContractId());

        //��������    org_contract_quantity   ����.��������
        l_foredSettleOrderInqParams.setOrgContractQuantity(l_eqtypeContractRow.getOriginalQuantity());

        //������ contract_quantity      ����.������
        l_foredSettleOrderInqParams.setContractQuantity(l_eqtypeContractRow.getQuantity());

        //�����P��    original_contract_price         ����.�����P��
        l_foredSettleOrderInqParams.setOriginalContractPrice(l_eqtypeContractRow.getOriginalContractPrice());

        //���P�� contract_price    ����.���P��
        l_foredSettleOrderInqParams.setContractPrice(l_eqtypeContractRow.getContractPrice());

        //���敪 contract_type     ����.���敪
        l_foredSettleOrderInqParams.setContractType(l_eqtypeContractRow.getContractType().intValue());

        //����  open_date     ����.����
        l_foredSettleOrderInqParams.setOpenDate(l_eqtypeContractRow.getOpenDate());

        //����  close_date    ����.����
        l_foredSettleOrderInqParams.setCloseDate(l_eqtypeContractRow.getCloseDate());

        //�ŋ敪 tax_type     ����.�ŋ敪
        if (l_eqtypeContractRow.getTaxType() != null)
        {
            l_foredSettleOrderInqParams.setTaxType(l_eqtypeContractRow.getTaxType().intValue());
        }

        //�ٍϋ敪    repayment_type     ����.�ٍϋ敪
        l_foredSettleOrderInqParams.setRepaymentType(l_eqtypeContractRow.getRepaymentType());

        //�ٍϊ����l   repayment_num     ����.�ٍϊ����l
        l_foredSettleOrderInqParams.setRepaymentNum(l_eqtypeContractRow.getRepaymentNum());

        //�쐬���t    created_timestamp     ���ݓ���
        Timestamp l_tsSystemtime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_foredSettleOrderInqParams.setCreatedTimestamp(l_tsSystemtime);

        //�X�V���t    last_updated_timestamp    ���ݓ���
        l_foredSettleOrderInqParams.setLastUpdatedTimestamp(l_tsSystemtime);

        //�����R�[�h  account_code  �ڋq.����ID�ɊY������ڋq.get�\���ڋq�R�[�h()
        l_foredSettleOrderInqParams.setAccountCode(((WEB3GentradeMainAccount)l_mainAccount).getDisplayAccountCode());

        //�����R�[�h   product_code ����.����ID�ɊY���������.�����R�[�h
        l_foredSettleOrderInqParams.setProductCode(l_product.getProductCode());

        //QueryProcessor.doInsertQuery()��call���A���R�[�h��o�^����B
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_QueryProcessor.doInsertQuery(l_foredSettleOrderInqParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
