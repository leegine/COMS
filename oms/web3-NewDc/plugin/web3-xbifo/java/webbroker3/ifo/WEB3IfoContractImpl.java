head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoContractImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP����(WEB3IfoContractImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21 �����(���u)�V�K�쐬
Revesion History : 2008/04/08 ��іQ(���u) OP�ԍψꗗ�̉��P�Ή�
Revesion History : 2008/08/18 ���z(���u) IFO�����_�Ή�
Revesion History : 2008/08/18 ���z(���u) �d�l�ύX ���f��No.903
*/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3LogUtility;


/**
 * (�敨OP����)<BR>
 * �敨OP���ʃN���X<BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3IfoContractImpl extends IfoContractImpl
{

    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoContractImpl.class);

    /**
     * (get�]�����v)<BR>
     * <BR>
     * �w��P���ŁA�w�萔�ʕԍς����ꍇ�̕]�����v���擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * (1)���敪�������̏ꍇ<BR>
     *
     * �i�p�����[�^.�ԍϒP�� �| getContractPrice()�j �~ �p�����[�^.���ʁ@@�~�@@(*1) �w���搔<BR>
     * <BR>
     * (2)���敪�������̏ꍇ<BR>
     * <BR>
     * �igetContractPrice() �| �p�����[�^.�ԍϒP���j �~ �p�����[�^.���ʁ@@�~�@@(*1) �w���搔<BR>
     * <BR>
     * (*1) �w���搔<BR>
     * �@@�|�v���_�N�g�}�l�[�W��.getTradedProduct()�ɂĎ���������擾����B<BR>
     * �@@[getTradedProduct()�Ɏw�肷�����]<BR>
     * �@@Product�F�@@this.getProduct()<BR>
     * �@@Market�F�@@this.getMarketId()�ɑΉ�����s��<BR>
     * <BR>
     * �@@�|�擾�����敨OP�������.1�P�ʓ�����搔<BR>
     * <BR>
     * @@param l_dblSettlementUnitPrice - (�ԍϒP��)<BR>
     * <BR>
     * �ԍώ��̒P��<BR>
     * <BR>
     * �Y�����ʂ̌��Ϗ�Ԃ������ρA�܂��͌��ϒ��E���s�����̏ꍇ�F����<BR>
     * <BR>
     * �Y�����ʂ̌��Ϗ�Ԃ����ϒ��E�w�l�����̏ꍇ�F�w�l<BR>
     * @@param l_dblSettlementCnt - �ԍϐ���<BR>
     *
     * @@return double
     * @@roseuid 4099B7D101DA
     */
    public double getEvaluateIncome(
        double l_dblSettlementUnitPrice,
        double l_dblSettlementCnt) throws WEB3BaseException
    {
        final String l_strMethodName = "getEvaluateIncome";
        log.entering(l_strMethodName);

        // --- Test Log
        log.debug("Input Parm: l_dblSettlementUnitPrice = " + l_dblSettlementUnitPrice);
        log.debug("Input Parm: l_dblSettlementUnitPrice = " + l_dblSettlementCnt);

        double l_dblReturnValue = 0;
        try
        {
            // (*1) �w���搔
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                Market l_market =
                    l_gentradeFinObjectManager.getMarket(this.getMarketId());

            WEB3IfoTradedProductImpl l_tradedProduct  =
                (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                    this.getProduct(),
                    l_market);

            IfoTradedProductRow l_tradedProduceRow = (IfoTradedProductRow)l_tradedProduct.getDataSourceObject();
            BigDecimal l_bdUnitSize = new BigDecimal(l_tradedProduceRow.getUnitSize() + "");

            // --- Test Log
            log.debug("�w���搔: l_bdUnitSize = " + l_bdUnitSize);

            ContractTypeEnum l_contracttype = this.m_Row.getContractType();

            BigDecimal l_bdSettlementUnitPrice = new BigDecimal(l_dblSettlementUnitPrice + "");
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice() + "");
            BigDecimal l_bdSettlementCnt = new BigDecimal(l_dblSettlementCnt + "");
            // (1)���敪�������̏ꍇ
            // (�p�����[�^.�ԍϒP���|getContractPrice())�~�p�����[�^.���ʁ~(*1)�w���搔
            if(ContractTypeEnum.LONG.equals(l_contracttype))
            {
                // --- Test Log
                log.debug("ContractType: ContractTypeEnum.LONG" );
                l_dblReturnValue =
                    (l_bdSettlementUnitPrice.subtract(l_bdContractPrice)).multiply(
                        l_bdSettlementCnt).multiply(l_bdUnitSize).doubleValue();
            }

            // (2)���敪�������̏ꍇ
            //�igetContractPrice()�|�p�����[�^.�ԍϒP��)�~�p�����[�^.���ʁ~(*1) �w���搔
            if(ContractTypeEnum.SHORT.equals(l_contracttype))
            {
                // --- Test Log
                log.debug("ContractType: ContractTypeEnum.SHORT" );
                l_dblReturnValue =
                    (l_bdContractPrice.subtract(l_bdSettlementUnitPrice)).multiply(
                        l_bdSettlementCnt).multiply(l_bdUnitSize).doubleValue();
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        // --- Test Log
        log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);

        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }

    /**
     * (get�������)<BR>
     * <BR>
     * �w�萔�ʂɂ����錚��������擾����B<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * getContractPrice() �~ �p�����[�^.���� �~ �w���搔(*1)<BR>
     * <BR>
     * (*1) �w���搔<BR>
     * �@@�|�v���_�N�g�}�l�[�W��.getTradedProduct()�ɂĎ���������擾����B<BR>
     * �@@[getTradedProduct()�Ɏw�肷�����]<BR>
     * �@@Product�F�@@this.getProduct()<BR>
     * �@@Market�F�@@this.getMarketId()�ɑΉ�����s�� <BR>
     * <BR>
     * �@@�|�擾�����敨OP�������.1�P�ʓ�����搔<BR>
     * @@param l_dblCount - ����<BR>
     * @@return double
     * @@roseuid 4099B7D101E0
     * @@throws webbroker3.common.WEB3BaseException
     */
    public double getContractExecutedAmount(double l_dblCount)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getContractExecutedAmount";
        log.entering(l_strMethodName);

        // --- Test Log
        log.debug("Input Parm: l_dblCount = " + l_dblCount);
        double l_dblReturnValue = 0;
        try
        {
            // (*1) �w���搔
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                Market l_market =
                    l_gentradeFinObjectManager.getMarket(this.getMarketId());

            WEB3IfoTradedProductImpl l_tradedProduct  =
                (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                    this.getProduct(),
                    l_market);

            IfoTradedProductRow l_tradedProduceRow =
                (IfoTradedProductRow)l_tradedProduct.getDataSourceObject();
            double l_dblUnitSize = l_tradedProduceRow.getUnitSize();

            // --- Test Log
            log.debug("�w���搔: l_dblUnitSize = " + l_dblUnitSize);

            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice() + "");
            BigDecimal l_bdCount = new BigDecimal(l_dblCount + "");
            BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize + "");
            // getContractPrice()�~�p�����[�^.���ʁ~�w���搔(*1)�̌v�Z���ʂ�ԋp����
            l_dblReturnValue = l_bdContractPrice.multiply(l_bdCount).multiply(l_bdUnitSize).doubleValue();
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        // --- Test Log
        log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);
        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }

    /**
     * (get���萔��)<BR>
     * <BR>
     * �w�萔�ʂ̌��萔�����v�Z����B<BR>
     * �i�����ϕ��̌��萔���擾�Ɏg�p�j<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * (get���ϑ��萔��() * �p�����[�^.����) / get���ʐ���()<BR>
     * �������_�ȉ��؂�̂�<BR>
     * <BR>
     * @@param l_dblContractCnt - ����<BR>
     * @@return double
     * @@roseuid 4099B7D101EA
     */
    public double getContractCommission(double l_dblContractCnt)
    {
        final String l_strMethodName = "getContractCommission";
        log.entering(l_strMethodName);

        // --- Test Log
        log.debug("Input Parm: l_dblContractCnt = " + l_dblContractCnt);
        double l_dblReturnValue = 0;

        // (get���ϑ��萔��() * �p�����[�^.����) / get���ʐ���()�̌v�Z���ʂ�ԋp����
        BigDecimal l_bdSetupFee = new BigDecimal(this.m_Row.getSetupFee() + "");
        BigDecimal l_bdQuantity = new BigDecimal(this.m_Row.getQuantity() + "");
        BigDecimal l_bdContractCnt = new BigDecimal(l_dblContractCnt + "");

        if (l_bdQuantity.doubleValue() != 0)
        {
            l_dblReturnValue =
                l_bdSetupFee.multiply(l_bdContractCnt).divide(l_bdQuantity, 0, BigDecimal.ROUND_FLOOR).doubleValue();
        }

        // --- Test Log
        log.debug("l_bdSetupFee = " + l_bdSetupFee);
        log.debug("l_bdQuantity = " + l_bdQuantity);
        log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);

        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }

    /**
     * (�敨OP����)<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * ���ʂh�c�Ɉ�v����s�����ʃe�[�u�����擾����B<BR>
     * �擾�������ʍs�I�u�W�F�N�g�iIfoContractParams�j���w�肵<BR>
     * �X�[�p�[�N���X�̃R���X�g���N�^���R�[������B<BR>
     * @@param l_lngContractId - ���ʂh�c<BR>
     * @@return webbroker3.ifo.WEB3IfoContractImpl
     * @@roseuid 4099B7D101EC
     */
    public WEB3IfoContractImpl(long l_lngContractId)
        throws DataQueryException, DataNetworkException
    {
      super(l_lngContractId);
    }
    /**
     * (�敨OP����)<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * ���ʂh�c�Ɉ�v����s�����ʃe�[�u�����擾����B<BR>
     * �擾�������ʍs�I�u�W�F�N�g�iIfoContractParams�j���w�肵<BR>
     * �X�[�p�[�N���X�̃R���X�g���N�^���R�[������B<BR>
     * @@param l_row - IfoContractRow���ʂh�c<BR>
     * @@return webbroker3.ifo.WEB3IfoContractImpl
     * @@roseuid 4099B7D101EC
     */
    public WEB3IfoContractImpl(IfoContractRow l_row)
    {
      super(l_row);
    }

    /**
     * (get���b�N������)<BR>
     * <BR>
     * �igetLockedQuantity�̃I�[�o�[���[�h�j<BR>
     * �w��̒����P�ʂ����b�N���̐��ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@�ԍώw����Ǎ�<BR>
     * �ȉ��̏����Ō��ʕԍώw����e�[�u����ǂށB<BR>
     * <BR>
     * [����]<BR>
     * ���ʕԍώw����.���ʂh�c = this.getContractId()<BR>
     * ���ʕԍώw����.�����P�ʂh�c = ����.�����P�ʂh�c<BR>
     * <BR>
     * �Y���s�����݂��Ȃ��ꍇ��0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@���b�N�����ʕԋp<BR>
     *
     * �擾���������ԍώw����s���ƂɁA<BR>
     * �����ԍώw����s.�ԍϒ������� �| �����ԍώw����s.�ԍϖ�萔�� ���v�Z����B <BR>
     * ��L�v�Z���ʂ́A�S�Ă̕ԍώw����s��SUM�l��ԋp����B<BR>
     * <BR>
     * @@param l_lngOrderUnit�hd - �����P�ʂh�c<BR>
     * @@return double
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4099B7D101EE
     */
    public double getLockedQuantity(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getLockedQuantity";
        log.entering(l_strMethodName);

        // --- Test Log
        log.debug("Input Parm: l_lngOrderUnitId = " + l_lngOrderUnitId);

        double l_dblReturnValue = 0;
        try
        {
            // �P�j�ԍώw����Ǎ�

            // [����1]:���ʕԍώw����.���ʂh�c = this.getContractId()
            String l_strWhere = " contract_id = ? and order_unit_id = ? ";
            long l_lngContractIdValue = this.getContractId();

            // [����1]:���ʕԍώw����.�����P�ʂh�c = ����.�����P�ʂh�c

            Object[] l_objWhereValues =
            {
                new Long(l_lngContractIdValue), new Long(l_lngOrderUnitId)
            };

            // --- Test Log
            log.debug("SQL: l_strWhere = " + l_strWhere);
            log.debug("SQL: l_objWhereValues 0 = " + l_objWhereValues[0]);
            log.debug("SQL: l_objWhereValues 1 = " + l_objWhereValues[1]);

            List l_returnList = new ArrayList();

            QueryProcessor processor = Processors.getDefaultProcessor();
            l_returnList =
                processor.doFindAllQuery(
                    IfoClosingContractSpecParams.TYPE,
                    l_strWhere,
                    l_objWhereValues);

            // �Q�j���b�N�����ʕԋp
            for (int i = 0; i < l_returnList.size(); i++)
            {
                //�����ԍώw����s.�ԍϒ������� �| �����ԍώw����s.�ԍϖ�萔�� ���v�Z����B
                //��L�v�Z���ʂ́A�S�Ă̕ԍώw����s��SUM�l��ԋp����B
                IfoClosingContractSpecParams l_contractSpecParams =
                    new IfoClosingContractSpecParams((IfoClosingContractSpecRow)l_returnList.get(i));
                double l_dblQuantity = l_contractSpecParams.getQuantity();
                double l_dblExecutedQuantity = l_contractSpecParams.getExecutedQuantity();
                // --- Test Log
                log.debug("DB return value l_dblQuantity No."+ i + "= " + l_dblQuantity);

                l_dblReturnValue += (l_dblQuantity - l_dblExecutedQuantity);
            }

            // --- Test Log
            log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);

        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            //throw new DataException(l_strMessage);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(l_strMethodName);

        return l_dblReturnValue;
    }

    /**
     * (get�ԍϖ��ϐ���)<BR>
     * <BR>
     * �w��̒����P�ʂɊ֘A����ԍϖ��ϐ��ʂ��擾����B<BR>
     * <BR>
     * �P�j�@@�ԍώw����Ǎ�<BR>
     * �ȉ��̏����Ō��ʕԍώw����e�[�u����ǂށB<BR>
     * <BR>
     * [����]<BR>
     * ���ʕԍώw����.���ʂh�c = this.getContractId()<BR>
     * ���ʕԍώw����.�����P�ʂh�c = ����.�����P�ʂh�c<BR>
     * <BR>
     * �Y���s�����݂��Ȃ��ꍇ��0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ԍϖ��ϐ��ʕԋp<BR>
     * �擾�����ԍώw����s.�ԍϖ�萔�ʂ̍��v�l��ԋp����B<BR>
     * <BR>
     * @@param l_lngOrderUnitId - �����P�ʂh�c<BR>
     * @@return double
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4099B7D101F0
     */
    public double getClosingExecuteContractCnt(long l_lngOrderUnitId)
        throws  WEB3BaseException
    {
        final String l_strMethodName = "getClosingExecuteContractCnt";
        log.entering(l_strMethodName);


        // --- Test Log
        log.debug("Input Parm: l_lngOrderUnitId = " + l_lngOrderUnitId);

        double l_dblReturnValue = 0;
        try
        {
            // �P�j�ԍώw����Ǎ�

            // [����1]:���ʕԍώw����.���ʂh�c = this.getContractId()
            String l_strWhere = " contract_id = ? and order_unit_id = ? ";
            long l_lngContractIdValue = this.getContractId();

            // [����1]:���ʕԍώw����.�����P�ʂh�c = ����.�����P�ʂh�c

            Object[] l_objWhereValues =
            {
                new Long(l_lngContractIdValue), new Long(l_lngOrderUnitId)
            };

            // --- Test Log
            log.debug("SQL: l_strWhere = " + l_strWhere);
            log.debug("SQL: l_objWhereValues contract_id = " + l_objWhereValues[0]);
            log.debug("SQL: l_objWhereValues order_unit_id = " + l_objWhereValues[1]);

            List l_returnList = new ArrayList();

            // �f�[�^���m

            // --- Test Log
            log.debug("Excute SQL query!");

            QueryProcessor processor = Processors.getDefaultProcessor();
            l_returnList =
                processor.doFindAllQuery(
                    IfoClosingContractSpecParams.TYPE,
                    l_strWhere,
                    l_objWhereValues);

            // �Q�j�ԍϖ��ϐ��ʕԋp


            // --- Test Log
            log.debug("DB return value size = " + l_returnList.size());

            for (int i = 0; i < l_returnList.size(); i++)
            {
                // �擾�����ԍώw����s.�ԍϖ�萔�ʂ̍��v�l��ԋp����B
                IfoClosingContractSpecParams l_contractSpecParams =
                    new IfoClosingContractSpecParams((IfoClosingContractSpecRow)l_returnList.get(i));
                double l_dblExecuteQuantity =
                    l_contractSpecParams.getExecutedQuantity();

                // --- Test Log
                log.debug("DB return value l_dblExecuteQuantity No."+ i + "= " + l_dblExecuteQuantity);

                l_dblReturnValue += l_dblExecuteQuantity;
            }

            // --- Test Log
            log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);

        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException();
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            //throw new WEB3SystemLayerException();
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(l_strMethodName);

        return l_dblReturnValue;
    }

    /**
     * (get���萔�������)<BR>
     * <BR>
     * �w�萔�ʂ̌��萔������ł��v�Z����B<BR>
     * �i�����ϕ��̌��萔������Ŏ擾�Ɏg�p�j<BR>
     * <BR>
     * �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * (get���ϑ��萔�������() * �p�����[�^.����) / get���ʐ���()<BR>
     * �������_�ȉ��؂�̂�<BR>
     * @@param l_dblContractCnt - ����<BR>
     * @@return double
     * @@roseuid 4099B7D101F2
     */
    public double getContractCommissionConsumptionTax(double l_dblContractCnt)
    {
        final String l_strMethodName = "getContractCommissionConsumptionTax";
        log.entering(l_strMethodName);


        // --- Test Log
        log.debug("Input Parm: l_dblContractCnt = " + l_dblContractCnt);

        double l_dblReturnValue = 0;

        // (get���ϑ��萔�������() * �p�����[�^.����) / get���ʐ���()�̌v�Z���ʂ�ԋp����
        BigDecimal l_bdSetupFeeTax = new BigDecimal(this.m_Row.getSetupFeeTax() + "");
        BigDecimal l_bdQuantity = new BigDecimal(this.m_Row.getQuantity() + "");
        BigDecimal l_bdContractCnt = new BigDecimal(l_dblContractCnt + "");

        if (l_bdQuantity.doubleValue() != 0)
        {
            l_dblReturnValue =
                l_bdSetupFeeTax.multiply(l_bdContractCnt).divide(l_bdQuantity, 0, BigDecimal.ROUND_FLOOR).doubleValue();
        }

        // --- Test Log
        log.debug("l_bdSetupFeeTax = " + l_bdSetupFeeTax);
        log.debug("l_bdQuantity = " + l_bdQuantity);
        log.debug("Output Parm: l_dblReturnValue = " + l_dblReturnValue);


        log.exiting(l_strMethodName);
     return l_dblReturnValue;
    }
    
    /**
     * (get���萔��)<BR>
     * <BR>
     *�w�萔�ʕ��̌��萔�����v�Z����B<BR> 
     *�i���ϕ����l���������萔���擾�Ɏg�p�j<BR> 
     * <BR>
     *�i�����ϕ�(*1)�̌��萔���{���ύϕ�(*2)�̌��萔���j��Ԃ��B<BR> 
     *-------------------------------<BR> 
     *(*1)���ʂ̓����v���p�e�B������v�Z�ɂ��擾�B<BR> 
     *(*2)���ϖ��ɂ�茚�ʂ��猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR> 
     *-------------------------------<BR> 
     * <BR>
     *�P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR> 
     *�@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR> 
     * <BR>
     *�@@�@@�@@this.get�ԍϖ��ϐ���(�����P��ID)�ɂ��擾����B<BR> 
     * <BR>
     *�@@�@@�@@----------------------------------------------------------<BR> 
     *�@@�@@�@@[�����̐ݒ�]<BR> 
     * <BR>
     *�@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR> 
     *�@@�@@�@@----------------------------------------------------------<BR> 
     * <BR>
     *�Q�j�@@this.���ʐ� > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̌��萔�����擾����B<BR>  
     * <BR>
     *�@@�@@�@@this.get���萔���i���ʁj�ɂ��擾����B<BR>  
     * <BR>
     *�@@�@@�@@----------------------------------------------------------<BR> 
     *�@@�@@�@@[�����̐ݒ�]<BR>  
     * <BR>
     *�@@�@@�@@���ʁF�@@�����̐��� - �P�j�̌��ϖ�萔��<BR>  
     *�@@�@@�@@----------------------------------------------------------<BR>  
     * <BR>
     *�@@�@@�@@���萔���i�����ϕ��j �� this.get���萔���i���ʁj�߂�l <BR> 
     * <BR>
     *�R�j�@@�P�j�̌��ϖ�萔�� > 0�i�����ϖ�肠��j�̏ꍇ�A���ύϕ��̌��萔�����擾����B<BR>  
     * <BR>
     *�@@�R?�P�j�@@���Y���ʁ{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ�� <BR> 
     *�@@�@@�@@�@@�@@�擾����B 
     * <BR>
     *�@@�@@�@@�敨OP�g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����(�����P��ID,����ID)�ɂ��擾����B<BR>  
     * <BR>
     *�@@�@@�@@----------------------------------------------------------<BR>  
     *�@@�@@�@@[�����̐ݒ�]<BR>  
     * <BR>
     *�@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>  
     *�@@�@@�@@����ID�F�@@this.����ID<BR>  
     *�@@�@@�@@----------------------------------------------------------<BR>  
     * <BR>
     *�@@�R?�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̌��萔�����W�v����B<BR>  
     * <BR>
     *�@@�@@�@@�@@�@@���萔���i���ύϕ��j += �g�����U�N�V����.���萔��<BR>  
     * <BR>
     *�@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͌��萔���i���ύϕ��j��0�ƂȂ� �B<BR>  
     * <BR>
     *�S�j�@@�i���萔���i�����ϕ��j + ���萔���i���ύϕ��j�j��ԋp����B<BR> 
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException<BR>
     * @@return double 
     */
    public double getContractCommission(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getContractCommission(double, long)";
        log.entering(l_strMethodName);

        double l_dblReturnValue = 0D;
        double l_dblClosingExecutedQuantity = 0D;
        double l_dblCommissionOpen = 0D;

        BigDecimal l_bdCommissionOpen = new BigDecimal("0");
        BigDecimal l_bdCommissionClose = new BigDecimal("0");
        
        //�P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ 
        //this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾���� 
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = 
                this.getClosingExecuteContractCnt(l_lngOrderUnitId);
        }
        
        //�Q�j�@@this.���ʐ� > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̌��萔�����擾����
        if (this.getQuantity() > 0D)
        {
            l_dblCommissionOpen =
                this.getContractCommission(l_dblQuantity - l_dblClosingExecutedQuantity);
            l_bdCommissionOpen = new BigDecimal(l_dblCommissionOpen + "");
        }

        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i�����ϖ�肠��j�̏ꍇ�A���ύϕ��̌��萔�����擾����  
        if(l_dblClosingExecutedQuantity > 0D)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoFinTransactionManagerImpl l_finTransactionManager = 
                (WEB3IfoFinTransactionManagerImpl) l_tradingModule.getFinTransactionManager();        
            List l_transactions = l_finTransactionManager.getTransactions(l_lngOrderUnitId, this.getContractId());  
            for (int j = 0; j < l_transactions.size(); j++)
            {
                IfoFinTransactionRow l_finTransactionRow = (IfoFinTransactionRow)l_transactions.get(j);  
                BigDecimal l_bdImportedSetupFee = new BigDecimal(l_finTransactionRow.getImportedSetupFee() + "");
                l_bdCommissionClose = l_bdCommissionClose.add(l_bdImportedSetupFee);
            }
        }
        
        //�S�j�@@�i���萔���i�����ϕ��j + ���萔���i���ύϕ��j�j��ԋp���� 
        l_dblReturnValue = l_bdCommissionOpen.add(l_bdCommissionClose).doubleValue();
        log.debug("���萔���i�����ϕ��{���ύϕ��j = " + l_dblReturnValue);
        
        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }
    
    /**
     * (get���萔�������)<BR>
     * <BR>
     *�w�萔�ʕ��̌��萔������ł��v�Z����B<BR> 
     *�i���ϕ����l���������萔������Ŏ擾�Ɏg�p�j<BR> 
     * <BR>
     *�i�����ϕ�(*1)�̌��萔������Ł{���ύϕ�(*2)�̌��萔������Łj��Ԃ��B<BR> 
     *-------------------------------<BR> 
     *(*1)���ʂ̓����v���p�e�B������v�Z�ɂ��擾�B<BR> 
     *(*2)���ϖ��ɂ�茚�ʂ��猸�Z���ꂽ���z�B���ς̃g�����U�N�V�������擾�B<BR> 
     *-------------------------------<BR> 
     * <BR>
     *�P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ�A<BR> 
     *�@@�@@�@@this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾����B<BR> 
     * <BR>
     *�@@�@@�@@this.get�ԍϖ��ϐ���(�����P��ID)�ɂ��擾����B<BR> 
     * <BR>
     *�@@�@@�@@----------------------------------------------------------<BR> 
     *�@@�@@�@@[�����̐ݒ�]<BR> 
     * <BR>
     *�@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR> 
     *�@@�@@�@@----------------------------------------------------------<BR> 
     * <BR>
     *�Q�j�@@this.���ʐ� > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̌��萔������ł��擾����B<BR>  
     * <BR>
     *�@@�@@�@@this.get���萔������Łi���ʁj�ɂ��擾����B<BR>  
     * <BR>
     *�@@�@@�@@----------------------------------------------------------<BR> 
     *�@@�@@�@@[�����̐ݒ�]<BR>  
     * <BR>
     *�@@�@@�@@���ʁF�@@�����̐��� - �P�j�̌��ϖ�萔��<BR>  
     *�@@�@@�@@----------------------------------------------------------<BR>  
     * <BR>
     *�@@�@@�@@���萔������Łi�����ϕ��j �� this.get���萔������Łi���ʁj�߂�l <BR> 
     * <BR>
     *�R�j�@@�P�j�̌��ϖ�萔�� > 0�i�����ϖ�肠��j�̏ꍇ�A���ύϕ��̌��萔������ł��擾����B<BR>  
     * <BR>
     *�@@�R�|�P�j�@@���Y���ʁ{�w�茈�ϒ����ɕR�t�����ς̃g�����U�N�V�����i�����ڋq���薾�ׁj�̈ꗗ�� <BR> 
     *�@@�@@�@@�@@�@@�擾����B 
     * <BR>
     *�@@�@@�@@�敨OP�g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����(�����P��ID,����ID)�ɂ��擾����B<BR>  
     * <BR>
     *�@@�@@�@@----------------------------------------------------------<BR>  
     *�@@�@@�@@[�����̐ݒ�]<BR>  
     * <BR>
     *�@@�@@�@@�����P��ID�F�@@�����̒����P��ID<BR>  
     *�@@�@@�@@����ID�F�@@this.����ID<BR>  
     *�@@�@@�@@----------------------------------------------------------<BR>  
     * <BR>
     *�@@�R�|�Q�j�@@�擾�����g�����U�N�V�����̑S�v�f�̌��萔������ł��W�v����B<BR>  
     * <BR>
     *�@@�@@�@@�@@�@@���萔������Łi���ύϕ��j += �g�����U�N�V����.���萔�������<BR>  
     * <BR>
     *�@@�@@�@@�����ϖ�肪�Ȃ��ꍇ�͌��萔������Łi���ύϕ��j��0�ƂȂ� �B<BR>  
     * <BR>
     *�S�j�@@�i���萔������Łi�����ϕ��j + ���萔������Łi���ύϕ��j�j��ԋp����B<BR> 
     * <BR>
     * @@param l_dblQuantity - ����<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@throws WEB3BaseException<BR>
     * @@return double 
     */
    public double getContractCommissionConsumptionTax(
        double l_dblQuantity,
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getContractCommissionConsumptionTax(double, long)";
        log.entering(l_strMethodName);

        double l_dblReturnValue = 0D;
        double l_dblClosingExecutedQuantity = 0D;
        double l_dblCommissionConsumptionTaxOpen = 0D;

        BigDecimal l_bdCommissionConsumptionTaxOpen = new BigDecimal("0");
        BigDecimal l_bdCommissionConsumptionTaxClose = new BigDecimal("0");
        
        //�P�j�@@�����̒����P��ID != 0�i�����������݂̂��ߌ��ϖ��ϕ��̍l���v�j�̏ꍇ 
        //this.����ID�y�ш����̒����P��ID�ɊY�����镪�́A���ϖ�萔�ʂ��擾���� 
        if (l_lngOrderUnitId != 0L)
        {
            l_dblClosingExecutedQuantity = 
                this.getClosingExecuteContractCnt(l_lngOrderUnitId);
        }
        
        //�Q�j�@@this.���ʐ� > 0�i�������ϕ�����j�̏ꍇ�A�����ϕ��̌��萔������ł��擾����
        if (this.getQuantity() > 0D)
        {
            l_dblCommissionConsumptionTaxOpen =
                this.getContractCommissionConsumptionTax(l_dblQuantity - l_dblClosingExecutedQuantity);
            l_bdCommissionConsumptionTaxOpen =
                new BigDecimal(l_dblCommissionConsumptionTaxOpen + "");
        }

        // �R�j�@@�P�j�̌��ϖ�萔�� > 0�i�����ϖ�肠��j�̏ꍇ�A���ύϕ��̌��萔������ł��擾����  
        if(l_dblClosingExecutedQuantity > 0D)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoFinTransactionManagerImpl l_finTransactionManager = 
                (WEB3IfoFinTransactionManagerImpl) l_tradingModule.getFinTransactionManager();        
            List l_transactions = l_finTransactionManager.getTransactions(l_lngOrderUnitId, this.getContractId());  
            for (int j = 0; j < l_transactions.size(); j++)
            {
                IfoFinTransactionRow l_finTransactionRow = (IfoFinTransactionRow)l_transactions.get(j);  
                BigDecimal l_bdImportedSetupFeeTax = new BigDecimal(l_finTransactionRow.getImportedSetupFeeTax() + "");
                l_bdCommissionConsumptionTaxClose = l_bdCommissionConsumptionTaxClose.add(l_bdImportedSetupFeeTax);
            }
        }
        
        //�S�j�@@�i���萔������Łi�����ϕ��j + ���萔������Łi���ύϕ��j�j��ԋp���� 
        l_dblReturnValue = l_bdCommissionConsumptionTaxOpen.add(l_bdCommissionConsumptionTaxClose).doubleValue();
        log.debug("���萔������Łi�����ϕ��{���ύϕ��j = " + l_dblReturnValue);
        
        log.exiting(l_strMethodName);
        return l_dblReturnValue;
    }
}
@
