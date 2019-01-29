head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�N���X(WEB3EquityBizLogicProvider.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/12 �R�w��(���u) �V�K�쐬
Revesion History : 2006/07/15 �h�C �y�����z�d�l�ύX�Ǘ��䒠�E���f��952�A954��Ή�
Revesion History : 2006/08/14 �h�C �y�����z�d�l�ύX�Ǘ��䒠�E���f��973��Ή�
                 : 2006/08/29 �����F(���u) ���f�� 970
                 : 2006/11/01 ��іQ (���u) ���f�� No.1031
                 : 2006/12/21 ������ (���u) ���f�� No.1093
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CapitalGainStatusDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.EquityCommCondMstRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeNumberConstDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�v�Z�T�[�r�X �i�����v�Z�j�j�B<BR>
 * <BR>
 * �e��v�Z��񋟂��郆�[�e�B���e�B�N���X�B<BR>
 * �]�͌v�Z�́AxTrade���񋟂���C���^�t�F�C�X�ȊO�ɂ��p�����[�^���K�v�B
 * @@version 1.0
 */
public class WEB3EquityBizLogicProvider
    extends WEB3GentradeBizLogicProvider
    implements GlobalBizLogicProvider, EqTypeBizLogicProvider
{

    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBizLogicProvider.class);

    /**
     * @@roseuid 40A3495902F0
     */
    public WEB3EquityBizLogicProvider()
    {

    }

    public double calcExecutionAmount(
        ProductTypeEnum l_productTypeEnum,
        long l_lng,
        double l_dbl1,
        double l_dbl2,
        QuantityTypeEnum l_quantityTypeEnum)
    {
        return 0.0;
    }

    /**
     * (calc����]��)<BR>
     *<BR>
     * �w������̎���]�͂��v�Z���A�v�Z���ʂ�ԋp���܂��B<BR>
     * �iGlobalBizLogicProvider���̃��\�b�h�̃J�������j<BR>
     *<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@return double
     * @@roseuid 3FFBD9950222
     */
    public double calcTradingPower(SubAccount l_subAccount)
    {
        return Double.NaN;
    }

    /**
     * (check����]��)<BR>
     *<BR>
     * ���t�]�̓`�F�b�N���s���B<BR>
     * �icheckTradingPower�̃I�[�o�[���C�h�B�J�������j<BR>
     *<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_orderSpec - �������e
     *
     * @@return OrderValidationResult
     * @@roseuid 4010AF2C0227
     */
    public OrderValidationResult checkTradingPower(
        SubAccount l_subAccount,
        OrderSpec l_orderSpec)
    {
        return null;
    }

    /**
     * (calc�S���������)<BR>
     *<BR>
     * �v�Z�P���A�������S������������v�Z���ĕԋp����B<BR>
     *<BR>
     * ���v�Z�̏ڍׂɂ��ẮA��{�݌v�v�Z�����i���ʁj.doc���Q��<BR>
     *<BR>
     * �P�j��Е��X���i�e�[�u����芄���S�������擾����B�i���XID�Ə��i�R�[�h�Ŏ擾�j<BR>
     *<BR>
     * �Q�j�S������������v�Z����B<BR>
     *<BR>
     * �@@�S��������������������~�v�Z�P���~�����S����<BR>
     *
     * �i���j�����S�������̗p���Ȃ��P�[�X�ɂ��ẮA���Y���ڂɂ�'1'���ݒ肳��Ă��邽�� �A<BR>
     * �ꗥ��L�̌v�Z���Ōv�Z����B<BR>
     * @@param l_dblQuantity - (����)<BR>
     *<BR>
     * �����i���j����<BR>
     * @@param l_dblUnitPrice - (�v�Z�P��)<BR>
     *<BR>
     * �S������������v�Z���邽�߂̒P��<BR>
     * @@param l_lngBranchId - ���XID<BR>
     * @@param l_strCommissionProductCode - �萔�����i�R�[�h<BR>
     * �P�O�F��ꊔ��<BR><BR>
     * �P�P�F�X������(JASDAQ)<BR>
     * �P�Q�F�~�j����<BR>
     * �S�O�F�O������<BR>
     * �T�O�F�敨<BR>
     * �T�P�F�����w���n�o<BR>
     * @@param l_blnIsLimitPrice - (is�w�l)<BR>
     *
     * �w�l�����̏ꍇ��true�A���s�����̏ꍇ��false�B<BR>
     * @@return double
     * @@throws WEB3BaseEception �S����������̌v�Z�Ɏ��s�����ꍇ
     * @@roseuid 4014761A0387
     */
    public double calcRestraintTurnover(
        double l_dblQuantity,
        double l_dblUnitPrice,
        long l_lngBranchId,
        String l_strCommissionProductCode,
        boolean l_blnIsLimitPrice)
        throws WEB3BaseException
    {
        String l_errorMessage1 = "��Е��X���i�e�[�u���ɊY������f�[�^������܂���B";
        long l_lngRestraintTurnover;
        double l_dbRestraintTurnover = 0.0;
        InstBranchProductRow l_instBranchProductRow;
        BigDecimal l_bdUnitPrice;
        BigDecimal l_bdQuantity;
        BigDecimal l_bdPremiumRestraintRate;

        final String STR_METHOD_NAME =
            "calcRestraintTurnover(double , double , long , String , boolean)";
        log.entering(STR_METHOD_NAME);

        // ��Е��X���i�e�[�u����ǂݍ���
        try
        {
            l_instBranchProductRow =
                InstBranchProductDao.findRowByPk(
                    l_lngBranchId,
                    l_strCommissionProductCode);
        }
        catch (DataException de)
        {
            // DB�A�N�Z�X�G���[�B
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        if (l_instBranchProductRow == null)
        {
            // �Y������f�[�^�������B
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_errorMessage1);
        }

        //get �T�Z���z�v�Z����
        int l_intEstimatePriceCalcForm =
            l_instBranchProductRow.getEstimatePriceCalcForm();

        //get �����S����
        l_bdPremiumRestraintRate =
            new BigDecimal(l_instBranchProductRow.getPremiumRestraintRate());

        //�����P���敪�����s
        if (!l_blnIsLimitPrice)
        {
            //�����P���敪�����s�i�萔��.is�w�l() == false�j�@@���@@
            //�T�Z���z�v�Z�����������S���������@@�@@
            //�S��������� �� �v�Z�P�� �~ ���������~ �����S����
            if (WEB3PremiumRestraintRateDef.PREMIUM_RESTRANT
                == l_intEstimatePriceCalcForm)
            {
                l_bdUnitPrice = new BigDecimal(l_dblUnitPrice);
                l_bdQuantity = new BigDecimal(l_dblQuantity);
                l_dbRestraintTurnover =
                    l_bdUnitPrice.multiply(l_bdQuantity).multiply(l_bdPremiumRestraintRate).doubleValue();
                log.debug("****** �v�Z�P���F[" + l_dblUnitPrice + "]");
                log.debug("****** ���������F[" + l_dblQuantity + "]");
                log.debug("****** �����S�����F[" + l_bdPremiumRestraintRate.doubleValue() + "]");
                log.debug("****** �T�Z���z�v�Z�����������S��������");
                log.debug("****** �S��������� �� �v�Z�P�� �~ ���������~ �����S���� �F ["
                    + l_dbRestraintTurnover + "]");
            }
            //�����P���敪�����s�i�萔��.is�w�l() == false�j�@@���@@
            //�T�Z���z�v�Z������STOP�������@@
            //�S��������� �� �v�Z�P�� �~ ��������
            else
            {
                l_bdUnitPrice = new BigDecimal(l_dblUnitPrice);
                l_bdQuantity = new BigDecimal(l_dblQuantity);
                l_dbRestraintTurnover =
                    l_bdUnitPrice.multiply(l_bdQuantity).doubleValue();
                log.debug("****** �v�Z�P���F[" + l_dblUnitPrice + "]");
                log.debug("****** ���������F[" + l_dblQuantity + "]");
                log.debug("****** �T�Z���z�v�Z������STOP������");
                log.debug("****** �S��������� �� �v�Z�P�� �~ �������� �F [" 
                    + l_dbRestraintTurnover + "]");
            }
        }
        //�����P���敪���w�l
        else
        {
            //�S��������� �� �v�Z�P���i�w�l�j �~ ��������
            l_bdUnitPrice = new BigDecimal(l_dblUnitPrice);
            l_bdQuantity = new BigDecimal(l_dblQuantity);
            l_dbRestraintTurnover =
                l_bdUnitPrice.multiply(l_bdQuantity).doubleValue();
            log.debug("****** �v�Z�P���i�w�l�j�F[" + l_dblUnitPrice + "]");
            log.debug("****** ���������F[" + l_dblQuantity + "]");
            log.debug("****** �S��������� �� �v�Z�P���i�w�l�j �~ �������� �F [" 
                + l_dbRestraintTurnover + "]");
        }

        // �����_�ȉ��؂�̂�
        l_lngRestraintTurnover = (long) l_dbRestraintTurnover;
        l_dbRestraintTurnover = l_lngRestraintTurnover;

        log.exiting(STR_METHOD_NAME);
        return l_dbRestraintTurnover;
    }

    /**
     * �icreate�萔���j<BR>
     * <BR>
     * �T�Z��������v�Z�Ɏg�p����萔���I�u�W�F�N�g�𐶐����A<BR>
     * �����Ŏw�肳�ꂽ�����̒����P�ʃI�u�W�F�N�g���v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�g�����������}�l�[�W��.getOrderUnits( )�ŁA<BR>
     * �@@�@@�@@�����̒���ID�ɊY�����钍���P�ʃI�u�W�F�N�g�̃��X�g���擾����B<BR>
     * �ȍ~�A�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f���g�p����B<BR>
     * <BR>
     * �Q�j�@@�萔���C���X�^���X�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�ɒ����P�ʃI�u�W�F�N�g�̒l���Z�b�g����B<BR>
     * <BR>
     * �،���ЃR�[�h�F �����P�ʃI�u�W�F�N�g.���XID���畔�X�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���X�I�u�W�F�N�g�̓����ڂ��Z�b�g<BR>
     * ���XID�F �����P�ʃI�u�W�F�N�g�̓�����<BR>
     * �萔�����i�R�[�h�F �����P�ʃI�u�W�F�N�g�̓�����<BR>
     * ����R�[�h�iSONAR�j�F �����P�ʃI�u�W�F�N�g�̓�����<BR>
     * �������F�@@�����P�ʃI�u�W�F�N�g�̓�����<BR>
     * �ٍϋ敪�F<BR>
     * �@@�������P�ʃI�u�W�F�N�g.�ٍϋ敪�iSONAR�j==null�̏ꍇ<BR>
     * �@@�@@�@@�ٍϋ敪.���̑��i"00"�j���Z�b�g�i�Œ�j<BR>
     * �@@�������P�ʃI�u�W�F�N�g.�ٍϋ敪�iSONAR�j!=null�̏ꍇ<BR>
     * �@@�@@�@@�����P�ʃI�u�W�F�N�g.�ٍϋ敪�iSONAR�j�����̂܂܃Z�b�g<BR>
     * �����`���l���F �����P�ʃI�u�W�F�N�g.���񒍕��̒����`���l��<BR>
     * �����������`���l���F �����P�ʃI�u�W�F�N�g.���񒍕��̒����`���l��<BR>
     * �������萔��No�F �����P�ʃI�u�W�F�N�g.���񒍕��̎萔��No<BR>
     * �������萔��No�}�ԁF �����P�ʃI�u�W�F�N�g.���񒍕��̎萔��No�}��<BR>
     * is�w�l�F�@@�����P�ʃI�u�W�F�N�g.isMarketOrder( )==false�̏ꍇ��true���A<BR>
     * �@@�@@�@@�@@�@@�@@�����P�ʃI�u�W�F�N�g.isMarketOrder( )==true�̏ꍇ��false���A���ꂼ��Z�b�g<BR>
     * �s��R�[�h�iSONAR�j�F�@@�����P�ʃI�u�W�F�N�g.�s��ID�ɊY������<BR>
     * �@@�@@�@@�@@�@@�@@�s��I�u�W�F�N�g.�s��R�[�h�iSONAR�j���Z�b�g<BR>
     * <BR>
     * ���ȉ��̃v���p�e�B�͐ݒ肵�Ȃ��B<BR>
     * �萔��No<BR>
     * �萔��No�}��<BR>
     * �萔�����z<BR>
     * ���o��v�Z�p���<BR>
     * �萔���R�[�X�R�[�h<BR>
     * <BR>
     * �R�j�@@���������萔���I�u�W�F�N�g�̃C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID
     * @@return WEB3GentradeCommission<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA004D
     */
    public WEB3GentradeCommission createCommission(long l_lngOrderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCommission(long)";

        log.entering(STR_METHOD_NAME);

        //�P�j�g�����������}�l�[�W��.getOrderUnits()�ŁA
        // �����̒���ID�ɊY�����钍���P�ʃI�u�W�F�N�g�̃��X�g���擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderMgr =
            (EqTypeOrderManager) l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_lngOrderId);
        OrderUnit l_orderUnit = l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        //�Q�j�萔���C���X�^���X�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ��
        //  �����P�ʃI�u�W�F�N�g�̒l���Z�b�g����B
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //���XID���畔�X�I�u�W�F�N�g���擾����
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = new WEB3GentradeBranch(l_orderUnit.getBranchId());
        }
        catch (DataException l_de)
        {
            // DB�A�N�Z�X�G���[�B
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        //�،���ЃR�[�h�F�����P�ʃI�u�W�F�N�g.���XID���畔�X�I�u�W�F�N�g���擾���A
        // ���X�I�u�W�F�N�g�̓����ڂ��Z�b�g 
        l_commission.setInstitutionCode(
            l_branch.getInstitution().getInstitutionCode());
        //���XID�F �����P�ʃI�u�W�F�N�g�̓�����
        l_commission.setBranchId(l_orderUnit.getBranchId());
        //�萔�����i�R�[�h�F �����P�ʃI�u�W�F�N�g�̓�����
        l_commission.setCommissionProductCode(
            l_orderUnitRow.getCommProductCode());
        //����R�[�h�iSONAR�j�F �����P�ʃI�u�W�F�N�g�̓�����
        l_commission.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
        //�������F�@@�����P�ʃI�u�W�F�N�g�̓�����
        String l_strBizDate = l_orderUnitRow.getBizDate();
        Date l_datBizDate = null;
        try
        {
            l_datBizDate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(l_strBizDate);
        }
        catch (ParseException l_pe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_pe.getMessage(),
                l_pe);
        }
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        //�ٍϋ敪�F�@@�����P�ʃI�u�W�F�N�g.�ٍϋ敪�iSONAR�j
        if (l_orderUnitRow.getSonarRepaymentType() != null)
        {
            l_commission.setPayType(l_orderUnitRow.getSonarRepaymentType());
        }
        else
        {
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
        }
        //�����������`���l���F �����P�ʃI�u�W�F�N�g.���񒍕��̒����`���l��
        l_commission.setOrderChannel(l_orderUnitRow.getOrderChanel());
        //�������萔��No�F �����P�ʃI�u�W�F�N�g.���񒍕��̎萔��No
        l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
        //�������萔��No�}�ԁF �����P�ʃI�u�W�F�N�g.���񒍕��̎萔��No�}��
        l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
        // is�w�l�F�@@�����P�ʃI�u�W�F�N�g.isMarketOrder()==false�̏ꍇ��true���A
        //�@@�����P�ʃI�u�W�F�N�g.isMarketOrder()==true�̏ꍇ��false���A���ꂼ��Z�b�g
        if (l_orderUnit.isMarketOrder() == false)
        {
            l_commission.setIsLimitPrice(true);
        }
        else
        {
            l_commission.setIsLimitPrice(false);
        }
        //�s��R�[�h�iSONAR�j
        l_commission.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }

    /**
     * (calc�������)<BR>
     *<BR>
     * �v�Z�P���A������蔄��������v�Z���ĕԋp����B<BR>
     * 
     *<BR>
     * ���v�Z�̏ڍׂɂ��ẮA��{�݌v�v�Z�����i���ʁj.doc���Q��<BR>
     *<BR>
     * �P�j����������v�Z����B<BR>
     *<BR>
     * �@@������������������~�v�Z�P��<BR>
     * @@param l_dblPartContQuantity - (����)<BR>
     *     �����i���j����<BR>
     * @@param l_dblCalcPrice - (�v�Z�P��)<BR>
     *     ����������v�Z���邽�߂̒P��<BR>
     * <BR>
     * @@return double
     * @@roseuid 4056BC3B0107
     */
    public double calcTurnover(
        double l_dblPartContQuantity,
        double l_dblCalcPrice)
    {
        long l_lngRestraintTurnover;
        double l_dbRestraintTurnover = 0.0;
        BigDecimal l_bdUnitPrice;
        BigDecimal l_bdQuantity;

        final String STR_METHOD_NAME = "calcTurnover(double , double)";
        log.entering(STR_METHOD_NAME);

        if (l_dblPartContQuantity < 0 || l_dblCalcPrice < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "���� = " + l_dblPartContQuantity + "�A�v�Z�P�� = " + l_dblCalcPrice);
        }

        // ������� �� �v�Z�P�� �~ ��������
        l_bdUnitPrice = new BigDecimal(l_dblCalcPrice);
        l_bdQuantity = new BigDecimal(l_dblPartContQuantity);
        l_dbRestraintTurnover =
            l_bdUnitPrice.multiply(l_bdQuantity).doubleValue();
        log.debug("****** �v�Z�P���F[" + l_dblCalcPrice + "]");
        log.debug("****** ���������F[" + l_dblPartContQuantity + "]");
        log.debug(
            "****** ������� �� �v�Z�P�� �~ �������� �F ["
                + l_dbRestraintTurnover
                + "]");

        // �����_�ȉ��؂�̂�
        l_lngRestraintTurnover = (long) l_dbRestraintTurnover;
        l_dbRestraintTurnover = l_lngRestraintTurnover;

        log.exiting(STR_METHOD_NAME);
        return l_dbRestraintTurnover;
    }

    /**
     * (calc���n���v)<BR>
     * <BR>
     * (calc���n���v)<BR>
     * <BR>
     * ���n���v�̋��z���v�Z����B<BR>
     * <BR>
     * �P�j�@@�����̐ŋ敪���A���n���v�v�Z�ΏۊO�̏ꍇ�͂O��ԋp����B<BR>
     * �@@�@@�@@�i�����̐ŋ敪���i�h��������h�A�܂��́h������������򒥎��h�j�̏ꍇ<BR>
     * �@@�@@�@@�@@���u���n���v�v�Z�Ώہv�B<BR>
     * �@@�@@�@@�@@��L�ȊO�̏ꍇ���u���n���v�v�Z�ΏۊO�v�B�j<BR>
     * <BR>
     * �Q�j�@@�������u���n���v�v�Z�Ώہv�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�|�P�j�@@this.calc�T�Z���n���v( )�ŏ��n���v���v�Z����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@-----------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@��calc�T�Z���n���v( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���z�F�@@�����̋��z<BR>
     * �@@�@@�@@�@@�@@�����ʁF�@@�����̔�����<BR>
     * �@@�@@�@@�@@�@@����ID�F�@@�����̖���ID <BR>
     * �@@�@@�@@�@@�@@�⏕�����F�@@�����̕⏕����<BR>
     * 	         �ŋ敪�F�@@�����̐ŋ敪<BR>
     * �@@�@@�@@�@@�@@-----------------------------------------------------------<BR>
     * �R�jthis.calc�T�Z���n���v( )�̖߂�l��ԋp����B
     * <BR>
     * @@param l_dblExpensesCalcAmount - (���z)<BR>
     * <BR>
     * ���n���v���Z�o����Ώۂ̋��z�B<BR>
     *  - ���z
     * @@param l_dblOrderQuantity - (������)<BR>
     * <BR>
     * �������̒������ʁB<BR>
     *  - ������
     * @@param l_lngProductId - ����ID�B<BR>
     * �y�ۗL���Y�e�[�u���z���뉿���擾����ۂɎg�p����B<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_taxType - �ŋ敪<BR>
     * @@return double<BR>
     * @@throws WEB3BaseEception
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA01AC
     */
    public double calcCapitaGain(
        double l_dblExpensesCalcAmount,
        double l_dblOrderQuantity,
        long l_lngProductId,
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME =
            "calcCapitaGain(double, double, long, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����̐ŋ敪���A���n���v�v�Z�ΏۊO�̏ꍇ�͂O��ԋp����B
        // �@@�@@�@@�i�����̐ŋ敪���i�h��������h�A�܂��́h������������򒥎��h�j�̏ꍇ<BR>
        // �@@�@@�@@�@@���u���n���v�v�Z�Ώہv�B<BR>
        // �@@�@@�@@�@@��L�ȊO�̏ꍇ���u���n���v�v�Z�ΏۊO�v�B�j
        if (!TaxTypeEnum.SPECIAL.equals(l_taxType) &&
            !TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            log.debug("****** ���n���v�v�Z�ΏۊO �F [���n���v = 0]");
            log.exiting(STR_METHOD_NAME);
            return 0.0D;
        }
  
        // �Q�|�P�j�T�Z���n���v���v�Z����B
        double l_dblEstimatedCapitalGain = this.calcEstimatedCapitalGain(
        	l_dblExpensesCalcAmount,
        	l_dblOrderQuantity,
        	l_lngProductId,
        	l_subAccount,
        	l_taxType);

        log.debug("****** �����̋��z�F[" + l_dblExpensesCalcAmount + "]");
        log.debug("****** �����̔����ʁF[" + l_dblOrderQuantity + "]");
        log.exiting(STR_METHOD_NAME);
        return l_dblEstimatedCapitalGain; 
    }

    /**
     * (calc�萔���i���j)<BR>
     * ��������ɑ΂���ϑ��萔���A�ϑ��萔������ł��v�Z���A<BR>
     * ���̓��e��\��ConsolidatedCommissionInfo�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i������v�Z�j.doc�v�Q�ƁB<BR>
     * <BR>
     * super.calc�ϑ��萔��(�萔��, �⏕����)���R�[������ۂ̈����ɐݒ肷��<BR>
     * �萔���I�u�W�F�N�g�̍쐬�d�l�́A�ȉ��̒ʂ�Ƃ���B<BR>
     * ---------------------------------------------------------<BR>
     * ���萔���I�u�W�F�N�g�̍쐬��<BR>
     * <BR>
     * �P�j�@@this.create�萔��(�����̊����ڋq���薾��Params[0].����ID)�ɂ��A<BR>
     * �@@�@@�@@�萔���I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ő��������萔���I�u�W�F�N�g�̃v���p�e�B�u���o��v�Z�p����v�ɁA<BR>
     * �@@�@@�@@���v�����z(*1)���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�萔��.set���o��v�Z�p���(���v�����z(*1))�ɂ��Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@(*1)���v�����z<BR>
     * �@@�@@�@@�@@�@@�@@�� �����̊����ڋq���薾��Params�̑S�v�f��<BR>
     * �i���P�� �~ ��萔�ʁj��SUM�l<BR>
     * ---------------------------------------------------------<BR>
     * @@param l_eqFinTransactionRows
     * @@return ConsolidatedCommissionInfo
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA02C4
     */
    public ConsolidatedCommissionInfo calcCommission(EqtypeFinTransactionRow[] l_eqFinTransactionRows)
        throws WEB3BaseException
    {
        EqtypeFinTransactionRow l_row;
        EqtypeFinTransactionRow l_maxRow = null;
        BigDecimal l_bdMaxAmount = null;
        BigDecimal l_bdTotalAmount = new BigDecimal("0.0");
        BigDecimal l_bdTotalCommission;
        BigDecimal l_bdTotalSalesTax = null;
        BigDecimal l_bdPrice;
        BigDecimal l_bdQuantity;
        BigDecimal[] l_bdAmounts;
        BigDecimal l_bdCommission;
        BigDecimal l_bdSalesTax;
        BigDecimal l_bdSigmaCommission = new BigDecimal("0.0");
        BigDecimal l_bdSigmaSalesTax = new BigDecimal("0.0");
        double[] l_dbCommissions;
        double[] l_dbSalesTaxs;
        String l_strMessage;
        int l_intLength;
        int l_intIndex = -1;
        int i;

        final String STR_METHOD_NAME =
            "calcCommission(EqtypeFinTransactionRow[])";

        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderMgr =
            (EqTypeOrderManager) l_tm.getOrderManager();

        // �m���v�n������ƍő�������߂�
        l_intLength = l_eqFinTransactionRows.length;
        l_bdAmounts = new BigDecimal[l_intLength];
        for (i = 0; i < l_intLength; i++)
        {
            l_row = l_eqFinTransactionRows[i];
            l_bdPrice = new BigDecimal(l_row.getPrice());
            log.debug("�����ڋq����[" + i + "].���P���F" + l_bdPrice.doubleValue());
            l_bdQuantity = new BigDecimal(l_row.getQuantity());
            log.debug("�����ڋq����[" + i + "].��萔�ʁF" + l_bdQuantity.doubleValue());
            l_bdAmounts[i] = l_bdPrice.multiply(l_bdQuantity);
            log.debug("�����ڋq����[" + i + "].�����z�F" + l_bdAmounts[i].doubleValue());
            l_bdTotalAmount = l_bdTotalAmount.add(l_bdAmounts[i]);

            if (l_maxRow == null)
            {
                l_maxRow = l_row;
                l_bdMaxAmount = l_bdAmounts[i];
                l_intIndex = i;
            }
            else
            {
                int l_intResult = l_bdAmounts[i].compareTo(l_bdMaxAmount);
                if (l_intResult == 1)
                {
                    // �����z���ő�����z���傫���ꍇ
                    l_maxRow = l_row;
                    l_bdMaxAmount = l_bdAmounts[i];
                    l_intIndex = i;
                }
                else if (l_intResult == 0)
                {
                    if (l_row.getPrice() > l_maxRow.getPrice())
                    {
                        // �����z�����z�ŁA���P���������ꍇ
                        l_maxRow = l_row;
                        l_bdMaxAmount = l_bdAmounts[i];
                        l_intIndex = i;
                    }
                    else if (l_row.getPrice() == l_maxRow.getPrice())
                    {
                        EqTypeOrderExecution l_orderExec = null;
                        EqTypeOrderExecution l_orderExecMax = null;
                        try
                        {
                            l_orderExec =
                                (EqTypeOrderExecution)l_orderMgr.getOrderExecution(
                                    l_row.getOrderExecutionId());
                            l_orderExecMax =
                                (EqTypeOrderExecution) l_orderMgr.getOrderExecution(
                                    l_maxRow.getOrderExecutionId());
                        }
                        catch (NotFoundException nfe)
                        {
                            l_strMessage = "�������f�[�^��������܂���B";
                            log.error(l_strMessage, nfe);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName()
                                    + "."
                                    + STR_METHOD_NAME,
                                l_strMessage,
                                nfe);
                        }
                        if (l_orderExec.getExecutionSerialNo()
                            > l_orderExecMax.getExecutionSerialNo())
                        {
                            // ���P�������z�ŁA��菇�ԍ����傫���ꍇ
                            l_maxRow = l_row;
                            l_bdMaxAmount = l_bdAmounts[i];
                            l_intIndex = i;
                        }
                    }
                }
            }
        }
        log.debug("�m���v�n������F" + l_bdTotalAmount.doubleValue());
        log.debug("�ő������F" + l_bdMaxAmount.doubleValue());
        log.debug("�ő���̏��ԁF" + l_intIndex);

        // �m���v�n�ϑ��萔�������߂�
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_maxRow.getAccountId(),
                    l_maxRow.getSubAccountId());
        }
        catch (NotFoundException nfe)
        {
            l_strMessage =
                "�⏕������������܂���F account_id("
                    + l_maxRow.getAccountId()
                    + ") sub_account_id ("
                    + l_maxRow.getSubAccountId()
                    + ")";
            log.error(l_strMessage, nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMessage,
                nfe);
        }
        
        // �ϑ��萔���̌v�Z
        WEB3GentradeCommission l_commission =
            this.createCommission(l_eqFinTransactionRows[0].getOrderId());
        l_commission.setExpensesCalcAmount(l_bdTotalAmount.doubleValue());
        calcCommission(l_commission, l_subAccount);

        l_bdTotalCommission = new BigDecimal(l_commission.getCommission());
        log.debug("�m���v�n�ϑ��萔���F" + l_bdTotalCommission.doubleValue());

        // �m���v�n����ł̌v�Z���s��
        l_bdTotalSalesTax =
            new BigDecimal(
                calcSalesTax(
                    l_commission.getCommission(),
                    l_commission.getOrderBizDate(),
                    l_subAccount));

        log.debug("�m���v�n����ŁF" + l_bdTotalSalesTax.doubleValue());

        // ����(n)�̈ϑ��萔���Ə���ŁA�ϑ��萔���̑��a�A����ł̑��a�̌v�Z
        // �ő���͏������X�L�b�v
        l_dbCommissions = new double[l_intLength];
        l_dbSalesTaxs = new double[l_intLength];
        BigDecimal l_bdProRata;
        //int l_scale = 10;
        int l_scale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);

        for (i = 0; i < l_intLength; i++)
        {
            if (i != l_intIndex)
            {
                l_bdProRata =
                    l_bdAmounts[i].divide(
                        l_bdTotalAmount,
                        l_scale,
                        BigDecimal.ROUND_HALF_EVEN);
                log.debug("�m����(" + i + ")�n���䗦�F" + l_bdProRata.doubleValue());
                l_bdCommission = l_bdTotalCommission.multiply(l_bdProRata);
                l_dbCommissions[i] = l_bdCommission.longValue();
                log.debug("�m����(" + i + ")�n�ϑ��萔���F" + l_dbCommissions[i]);
                l_bdSalesTax = l_bdTotalSalesTax.multiply(l_bdProRata);
                l_dbSalesTaxs[i] = l_bdSalesTax.longValue();
                log.debug("�m����(" + i + ")�n����ŁF" + l_dbSalesTaxs[i]);
                l_bdSigmaCommission =
                    l_bdSigmaCommission.add(new BigDecimal(l_dbCommissions[i]));
                l_bdSigmaSalesTax =
                    l_bdSigmaSalesTax.add(new BigDecimal(l_dbSalesTaxs[i]));
            }
        }
        log.debug("�ϑ��萔���̑��a�F" + l_bdSigmaCommission.doubleValue());
        log.debug("����ł̑��a�F" + l_bdSigmaSalesTax.doubleValue());

        // �ϑ��萔���A����ł̒[�����v�Z���A�ő���̈ϑ��萔���A����łɊ񂹂�
        l_bdCommission = l_bdTotalCommission.subtract(l_bdSigmaCommission);
        l_dbCommissions[l_intIndex] = l_bdCommission.doubleValue();
        log.debug(
            "�m����(" + l_intIndex + ")�n�ϑ��萔���F" + l_dbCommissions[l_intIndex]);
        l_bdSalesTax = l_bdTotalSalesTax.subtract(l_bdSigmaSalesTax);
        l_dbSalesTaxs[l_intIndex] = l_bdSalesTax.doubleValue();
        log.debug("�m����(" + l_intIndex + ")�n����ŁF" + l_dbSalesTaxs[l_intIndex]);

        log.exiting(STR_METHOD_NAME);
        return new ConsolidatedCommissionInfo(
            l_dbCommissions,
            l_bdTotalCommission.doubleValue(),
            l_dbSalesTaxs,
            l_bdTotalSalesTax.doubleValue());
    }

    /**
     * (calc�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P�����v�Z����B<BR>
     * <BR>
     * this.calc�뉿�P��(����ID, �⏕����, �ŋ敪, <BR>
     * �~�����L������)��delegate����B<BR>
     * �����́u�~�����L�������v�ɂ�0���A<BR>
     * ���̑��̈����ɂ͓����\�b�h�̈����̒l�����̂܂܃Z�b�g����B<BR>
     * @@param l_lngProductId - ����ID�B
     * �y�ۗL���Y�e�[�u���z���뉿���擾����ۂɎg�p����B
     * @@param l_subAccount - �⏕����
     * @@param l_taxType - �ŋ敪�B
     * @@throws WEB3BaseException
     * @@return double@@throws WEB3BaseException
     * @@roseuid 413D2FEA03D3
     */
    public double calcEstimatedBookPrice(
        long l_lngProductId,
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcEstimatedBookPrice(long, SubAccount, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        double l_dbEstimatedBookPrice = 
            this.calcBookValuePrice(l_lngProductId,l_subAccount,l_taxType,0);
        log.exiting(STR_METHOD_NAME);
        
        return l_dbEstimatedBookPrice;
    }

    /**
     * �iEqTypeBizLogicProvider.calcCommission(OrderExecution orderexecution)�̎����j<BR>
     * <BR>
     * @@param l_orderExecution
     * @@return double
     * @@roseuid 40A3495A00B7
     */
    public double calcCommission(OrderExecution l_orderExecution)
    {
        //���钍���̖��萔�����Z�o����B
        return 0.0;
    }

    /**
     * �iEqTypeBizLogicProvider.calcCapitalGainTax(SubAccount subaccount, TaxTypeEnum taxtypeenum, double d)�̎����j<BR>
     * <BR>
     * @@param l_subAccount
     * @@param l_taxTypeEnum
     * @@param l_dblData
     * @@return double
     */
    public double calcCapitalGainTax(
        SubAccount l_subAccount,
        TaxTypeEnum l_taxTypeEnum,
        double l_dblData)
    {
        //���n�v�ł̌v�Z���s���B
        return 0.0;
    }

    /**
     * (calc���o��)<BR>
     * ���o����v�Z���ԋp����B<BR>
     * <BR>
     * ------------------------------------------------------<BR>
     * �������̌��敪���h�����h�̏ꍇ<BR>
     * <BR>
     * �i�����̈ϑ��萔���{�ϑ��萔������Ł{���萔���{���萔�������<BR>
     * �@@�{���`�������{���`����������Ł{�Ǘ���{�Ǘ�������<BR>
     * �@@�{�������|�t�����{���̑��j�̒l���v�Z���ԋp����B<BR>
     * <BR>
     * ------------------------------------------------------<BR>
     * �������̌��敪���h�����h�̏ꍇ<BR>
     * <BR>
     * �i�����̈ϑ��萔���{�ϑ��萔������Ł{���萔���{���萔�������<BR>
     * �@@�{���`�������{���`����������Ł{�Ǘ���{�Ǘ�������<BR>
     * �@@�|�������{�t�����{�݊����{���̑��j�̒l���v�Z���ԋp����B<BR>
     * <BR>
     * @@param l_dblCommissionFee - �ϑ��萔���B
     * @@param l_dblCommissionFeeTax - �ϑ��萔������ŁB
     * @@param l_dblSetupFee - ���萔���B
     * @@param l_dblSetupFeeTax - ���萔������ŁB
     * @@param l_dblNameTransferFee - ���`�������B
     * @@param l_dblNameTransferFeeTax - ���`����������ŁB
     * @@param l_dblManagementFee - �Ǘ���B
     * @@param l_dblManagementFeeTax - �Ǘ������ŁB
     * @@param l_dblInterestFee - �������B
     * @@param l_dblPayInterestFee - �t�����B
     * @@param l_dblLoanEquityFee - �݊����B
     * @@param l_dblOther - ���̑��B
     * @@param l_contractType - ���敪�B
     * @@return double
     * @@roseuid 40E218F400F8
     */
    public double calcExpenses(
        double l_dblCommissionFee,
        double l_dblCommissionFeeTax,
        double l_dblSetupFee,
        double l_dblSetupFeeTax,
        double l_dblNameTransferFee,
        double l_dblNameTransferFeeTax,
        double l_dblManagementFee,
        double l_dblManagementFeeTax,
        double l_dblInterestFee,
        double l_dblPayInterestFee,
        double l_dblLoanEquityFee,
        double l_dblOther,
        ContractTypeEnum l_contractType)
    {
        BigDecimal l_bdCommissionFee = new BigDecimal(l_dblCommissionFee);
        BigDecimal l_bdCommissionFeeTax = new BigDecimal(l_dblCommissionFeeTax);
        BigDecimal l_bdSetupFee = new BigDecimal(l_dblSetupFee);
        BigDecimal l_bdSetupFeeTax = new BigDecimal(l_dblSetupFeeTax);
        BigDecimal l_bdNameTransferFee = new BigDecimal(l_dblNameTransferFee);
        BigDecimal l_bdNameTransferFeeTax = new BigDecimal(l_dblNameTransferFeeTax);
        BigDecimal l_bdManagementFee = new BigDecimal(l_dblManagementFee);
        BigDecimal l_bdManagementFeeTax = new BigDecimal(l_dblManagementFeeTax);
        BigDecimal l_bdInterestFee = new BigDecimal(l_dblInterestFee);
        BigDecimal l_bdPayInterestFee = new BigDecimal(l_dblPayInterestFee);
        BigDecimal l_bdLoanEquityFee = new BigDecimal(l_dblLoanEquityFee);
        BigDecimal l_bdOther = new BigDecimal(l_dblOther);
        
        final String STR_METHOD_NAME =
            "calcExpenses(double, double, double, double, double, double, double, double, double, double, double, double,ContractTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        double l_dbExpenses;
        //�������̌��敪���h�����h�̏ꍇ
        if(ContractTypeEnum.LONG.equals(l_contractType))
        {
            //�i�����̈ϑ��萔���{�ϑ��萔������Ł{���萔���{���萔�������
            // �{���`�������{���`����������Ł{�Ǘ���{�Ǘ�������
            // �{�������|�t�����{���̑��j�̒l���v�Z���ԋp����B
            l_dbExpenses = l_bdCommissionFee
                .add(l_bdCommissionFeeTax)
                .add(l_bdSetupFee)
                .add(l_bdSetupFeeTax)
                .add(l_bdNameTransferFee)
                .add(l_bdNameTransferFeeTax)
                .add(l_bdManagementFee)
                .add(l_bdManagementFeeTax)
                .add(l_bdInterestFee)
                .subtract(l_bdPayInterestFee)
                .add(l_bdOther)
                .doubleValue();
        }
        // �������̌��敪���h�����h�̏ꍇ
        else if(ContractTypeEnum.SHORT.equals(l_contractType))
        {
            //�i�����̈ϑ��萔���{�ϑ��萔������Ł{���萔���{���萔�������
            // �{���`�������{���`����������Ł{�Ǘ���{�Ǘ�������
            // �|�������{�t�����{�݊����{���̑��j�̒l���v�Z���ԋp����B
            l_dbExpenses = l_bdCommissionFee
                .add(l_bdCommissionFeeTax)
                .add(l_bdSetupFee)
                .add(l_bdSetupFeeTax)
                .add(l_bdNameTransferFee)
                .add(l_bdNameTransferFeeTax)
                .add(l_bdManagementFee)
                .add(l_bdManagementFeeTax)
                .subtract(l_bdInterestFee)
                .add(l_bdPayInterestFee)
                .add(l_bdLoanEquityFee)
                .add(l_bdOther)
                .doubleValue();
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "���敪 = " + l_contractType);
        }
        
        log.debug("****** ���敪�F[" + l_contractType + "]");
        log.debug("****** ���o��F[" + l_dbExpenses + "]");
        log.exiting(STR_METHOD_NAME);
        return l_dbExpenses;
                       
    }

    /**
     * (calc�뉿�P��)<BR>
     * <BR>
     * �뉿�P�����v�Z����B<BR>
     * <BR>
     * �P�j�@@�����|�W�V�����}�l�[�W��.get�ۗL���Y(����ID, �⏕����ID, ����ID, ��<BR>���̐ŋ敪)�ɂ��A<BR>
     * �@@�@@�@@�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ����͗�O��throw����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00204<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------
     * <BR>
     * �@@�@@�@@�������|�W�V�����}�l�[�W��.get�ۗL���Y( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@����ID�F�@@�����̕⏕����.����ID<BR>
     * �@@�@@�@@�⏕����ID�F�@@�����̕⏕����.�⏕����ID<BR>
     * �@@�@@�@@����ID�F�@@�����̖���ID<BR>
     * �@@�@@�@@�ŋ敪�F�@@�����̐ŋ敪<BR>
     * �@@�@@�@@----------------------------------------------------------
     * <BR>
     * �Q�j�@@�뉿�P�������߁A�v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�뉿�P�����ۗL���Y.�뉿�i�뉿�P���v�Z�p�j �� �ۗL���Y.���ʁi�뉿�P���v�Z�p�j<BR>
     * �@@�@@���v�Z���ʂ��A�����́u�~�����L�������v�܂ł̌����ɁA�؎̂Ăɂ��ۂ߂�B<BR>
     * <BR>
     * @@param l_lngProductId - ����ID�B
     * �y�ۗL���Y�e�[�u���z���뉿���擾����ۂɎg�p����B
     * @@param l_subAccount - �⏕����
     * @@param l_taxType - �ŋ敪�B
     * @@param l_lngUnderYenEffectiveBit - (�~�����L������)<BR>
     * �~�����i�����_�ȉ��j�扽�ʂ܂ł�L���Ƃ��邩���w�肷��B<BR>
     * ex.) �����_�ȉ���T�ʂ�؎̂Ă��A�����_�ȉ���S�ʂ܂ł̒l��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�u�S�v���w�肷��B<BR>
     * <BR>
     * @@return double
     * @@roseuid 40EDE63B035B
     */
    public double calcBookValuePrice(
        long l_lngProductId,
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType,
        long l_lngUnderYenEffectiveBit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "calcBookValuePrice(long, SubAccount, TaxTypeEnum, long)";
        log.entering(STR_METHOD_NAME);
        
        if(l_lngUnderYenEffectiveBit < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "�����́u�~�����L�������v = " + l_lngUnderYenEffectiveBit);
        }
        
        //�P�j�����|�W�V�����}�l�[�W��.get�ۗL���Y(����ID, �⏕����ID, ����ID, �����̐ŋ敪)
        //   �ɂ��A�ۗL���Y�I�u�W�F�N�g���擾����B�Y���f�[�^�Ȃ����͗�O��throw����B
        
        // get �����|�W�V�����}�l�[�W��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        // get �ۗL���Y
        EqTypeAsset l_asset;

        l_asset =
            l_positionManager.getAsset(
                l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_lngProductId,
                l_taxType);
                
        if (l_asset == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass() + "." + STR_METHOD_NAME,
                "�ۗL���Y�e�[�u���ɊY������f�[�^������܂���");
        }
        
        // �Q�j�@@�뉿�P�������߁A�v�Z���ʂ�ԋp����B
        
        AssetRow l_assetRow =
            (AssetRow)l_asset.getDataSourceObject();
        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
        //get �ۗL���Y.���ʁi�뉿�P���v�Z�p�j
        double l_dblTotalQuantity = l_assetRow.getQuantityForBookValue();
        BigDecimal l_bdTotalQutity = new BigDecimal(l_dblTotalQuantity);
        if (l_dblTotalQuantity == 0.0D)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass() + "." + STR_METHOD_NAME,
                "�ۗL���Y�e�[�u��.���ʁi�뉿�P���v�Z�p�j��0���ݒ肳��Ă��܂�");
        }
        //get �ۗL���Y.�뉿�i�뉿�P���v�Z�p�j
        double l_dbBookValue = l_assetRow.getBookValue();
        BigDecimal l_bdBookValue = new BigDecimal(l_dbBookValue);
        
        //�뉿�P�����ۗL���Y.�뉿���ۗL���Y.����
        BigDecimal l_bdBookPrice =
            l_bdBookValue.divide(
                l_bdTotalQutity,
                l_intScale,
                BigDecimal.ROUND_HALF_EVEN);
                
        log.debug("****** �ۗL���Y.�뉿�i�뉿�P���v�Z�p�j�F[" + l_dbBookValue + "]");
        log.debug("****** �ۗL���Y.���ʁi�뉿�P���v�Z�p�j�F[" + l_dblTotalQuantity + "]");
        log.debug("****** �뉿�P��(�����؎̑O)���ۗL���Y.�뉿���ۗL���Y.���ʁF[" + l_bdBookPrice.doubleValue() + "]");
                
        //�v�Z���ʂ��A�����́u�~�����L�������v�܂ł̌����ɁA�؎̂Ăɂ��ۂ߂�  
        long l_lngBaseValue = 1;
        for(int i = 0; i < l_lngUnderYenEffectiveBit; i++)
        {
            l_lngBaseValue = l_lngBaseValue * 10;
        }
        BigDecimal l_bdBaseValue = new BigDecimal(l_lngBaseValue);
        l_bdBookPrice = l_bdBookPrice.multiply(l_bdBaseValue);
        long l_lngBookPrice = (long)l_bdBookPrice.doubleValue();
        l_bdBookPrice = new BigDecimal(l_lngBookPrice);
        l_bdBookPrice = 
            l_bdBookPrice.divide(
                l_bdBaseValue,
                l_intScale,
                BigDecimal.ROUND_HALF_EVEN);
        
        log.debug("****** �����́u�~�����L�������v�F[" + l_lngUnderYenEffectiveBit + "]");
        log.debug("****** �뉿�P��(�����؎̌�)�F[" + l_bdBookPrice.doubleValue() + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_bdBookPrice.doubleValue();
    }

    /**
     * �icreate�萔���j<BR>
     * <BR>
     * �萔���I�u�W�F�N�g�𐶐����A�����̓��e���v���p�e�B���Z�b�g���ĕԋp����B<BR>
     * <BR>
     * �P�j�@@�萔���C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B���ȉ��̒ʂ�ɐݒ肷��B<BR>
     * <BR>
     * ====================================================================<BR>
     * �،���ЃR�[�h�F �����̕⏕�����I�u�W�F�N�g.�،���ЃR�[�h<BR>
     * ���XID�F �����̕⏕�����I�u�W�F�N�g.���XID<BR>
     * �萔�����i�R�[�h�F �萔�����i�R�[�h.��ꊔ��<BR>
     * ����R�[�h�iSONAR�j�F <BR>
     * �@@---------------------------------------------------------------<BR>
     * �@@�������̐M�p����敪��DEFAULT�̏ꍇ��<BR>
     * �@@�@@�@@����R�[�h�iSONAR�j.���ʊ���<BR>
     * �@@---------------------------------------------------------------<BR>
     * �@@�������̐M�p����敪��DEFAULT�̏ꍇ��<BR>
     * �@@�@@�@@�����J�e�S�����V�K�������̏ꍇ�A����R�[�h�iSONAR�j.�M�p��<BR>
     * �@@�@@�@@�����J�e�S�����ԍϒ����̏ꍇ�A����R�[�h�iSONAR�j.�M�p�ԍ�<BR>
     * �@@�@@�@@�����J�e�S���������E���n�����̏ꍇ�A����R�[�h�iSONAR�j.�������n<BR>
     * �@@�@@�@@�������J�e�S������L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     * �@@---------------------------------------------------------------<BR>
     * �������F�@@�����̔�����<BR>
     * �ٍϋ敪�F<BR>
     * �@@�����̐M�p����敪��DEFAULT�̏ꍇ�A<BR>
     * �@@�@@�@@00�F���̑�<BR>
     * �@@�����̐M�p����敪��DEFAULT�̏ꍇ�A<BR>
     * �@@�@@�@@�i���X�s��ٍϕʁj�戵�����̃C���X�^���X���擾���A<BR>
     * �@@�@@�@@�擾�����C���X�^���X.�ٍϋ敪�iSONAR�j�̒l���Z�b�g�B<BR>
     * �@@<BR>
     * �@@�@@�@@[�i���X�s��ٍϕʁj�戵�����E�R���X�g���N�^����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̕⏕����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@�����̕⏕����.���XID�̕��X�I�u�W�F�N�g.���X�R�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̎s��R�[�h<BR>
     * �@@�@@�@@�ٍϋ敪�F�@@�����̐M�p����敪<BR>
     * �@@�@@�@@�ٍϊ����l�F�@@�����ٍ̕ϊ����l<BR>
     * <BR>
     * �����`���l���F �����̒����`���l��<BR>
     * �����������`���l���F �����̒����`���l��<BR>
     * �s��R�[�h�iSONAR�j�F<BR>
     * �@@�����̎s��R�[�h��null�̏ꍇ�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��(<BR>
     * �@@�@@�@@�⏕����.�،���ЃR�[�h, �����̎s��R�[�h)�ɊY������s��I�u�W�F�N�g.�s��R�[�h�iSONAR�j<BR>
     * �@@�@@�@@���Z�b�g�B<BR>
     * �@@�����̎s��R�[�h��null�̏ꍇ�́Anull���Z�b�g�B<BR>
     * ====================================================================<BR>
     * <BR>
     * ���ȉ��̃v���p�e�B�͐ݒ肵�Ȃ��B<BR>
     * is�w�l<BR>
     * �萔��No<BR>
     * �萔��No�}��<BR>
     * �萔�����z<BR>
     * ���o��v�Z�p���<BR>
     * �������萔��No<BR>
     * �������萔��No�}��<BR>
     * �萔���R�[�X�R�[�h<BR>
     * <BR>
     * �R�j�@@���������萔���I�u�W�F�N�g�̃C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_genSubAccount - (�⏕����) <BR>    
     * @@param l_strMarketCode - (�s��R�[�h) <BR>    
     * @@param l_datBizDate - (������) <BR>          
     * @@param l_strOrderChanel - (�����`���l��) <BR> 
     *     0:�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�[�@@3�F���o�C��<BR> 
     * @@param l_strMarginType - (�M�p����敪) <BR> 
     *     0�FDEFAULT�i�M�p����ȊO�j�@@1�F���x�M�p�@@2�F��ʐM�p<BR> 
     * @@param l_dblRepaymentNum - (�ٍϊ����l) <BR>   
     * @@param l_orderCateg - (�����J�e�S��) <BR> 
     *    ���M�p����敪��0�FDEFAULT�ȊO�̏ꍇ�̂݁A�ݒ�v�B<BR>                      
     *    3�F�V�K�������@@5�F�ԍϒ����@@7�F�����E���n���� <BR>                                                                                                              
     */
    public WEB3GentradeCommission createCommission(
        WEB3GentradeSubAccount l_genSubAccount,
        String l_strMarketCode,
        Date l_datBizDate,
        String l_strOrderChanel,
        String l_strMarginType,
        double l_dblRepaymentNum,
        OrderCategEnum l_orderCateg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createCommission(SubAccount, String, Date, String, String, double, OrderCategEnum)";
        log.entering(STR_METHOD_NAME);
        
        SubAccountRow l_subAccountRow = (SubAccountRow)l_genSubAccount.getDataSourceObject();
        
        //�P�j�@@�萔���C���X�^���X�𐶐�����B
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
        
        //�Q�j�@@�v���p�e�B���ȉ��̒ʂ�ɐݒ肷��B
        
        //get �،���ЃR�[�h
        String l_strInstitutionCode = l_genSubAccount.getInstitution().getInstitutionCode();
        
        //get ���X�R�[�h
        WEB3GentradeBranch l_gentradeBranch;
        try
        {
            l_gentradeBranch = new WEB3GentradeBranch(l_subAccountRow.getBranchId());
        }
        catch (DataException de)
        {
            // DB�A�N�Z�X�G���[�B
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
                               
        //2-1) �،���ЃR�[�h�F �����̕⏕�����I�u�W�F�N�g.�،���ЃR�[�h
        l_gentradeCommission.setInstitutionCode(l_strInstitutionCode);
            
        //2-2) ���XID�F �����̕⏕�����I�u�W�F�N�g.���XID
        l_gentradeCommission.setBranchId(l_subAccountRow.getBranchId());
        //2-3�j�萔�����i�R�[�h�F �萔�����i�R�[�h.��ꊔ��
        l_gentradeCommission.setCommissionProductCode(
            WEB3CommisionProductCodeDef.LISTING_STOCK);
        
        //2-4) ����R�[�h�iSONAR�j�F   
        // �������̐M�p����敪��DEFAULT�̏ꍇ��
        //�@@   ����R�[�h�iSONAR�j.���ʊ���
        // �������̐M�p����敪��DEFAULT�̏ꍇ��
        // �@@�@@�����J�e�S�����V�K�������̏ꍇ�A����R�[�h�iSONAR�j.�M�p�� 
        // �@@�@@�����J�e�S�����ԍϒ����̏ꍇ�A����R�[�h�iSONAR�j.�M�p�ԍ� 
        // �@@�@@�����J�e�S���������E���n�����̏ꍇ�A����R�[�h�iSONAR�j.�������n 
        //�@@�@@�������J�e�S������L�ȊO�̏ꍇ�́A��O��throw����B
        String l_strSonarTradedCode;
        if(WEB3MarginTradingDivDef.DEFAULT.equals(l_strMarginType))
        {
                l_strSonarTradedCode = 
                    WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        else
        {
            if(OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
            {
                l_strSonarTradedCode = 
                    WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
            }
            else if(OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                l_strSonarTradedCode = 
                    WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
            }
            else if(OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                l_strSonarTradedCode = 
                    WEB3TransactionTypeSONARDef.SWAP_CONTRACT;
            }
            else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����J�e�S�� = " + l_orderCateg);
            }
        }
        l_gentradeCommission.setSonarTradedCode(l_strSonarTradedCode);
        
        //2-5) �������F�@@�����̔����� 
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        
        //2-6) �ٍϋ敪�F                                            
        //  �����̐M�p����敪��DEFAULT�̏ꍇ�A00�F���̑� 
        //  �����̐M�p����敪��DEFAULT�̏ꍇ�A
        //   �i���X�s��ٍϕʁj�戵�����̃C���X�^���X���擾���A
        //    �擾�����C���X�^���X.�ٍϋ敪�iSONAR�j�̒l���Z�b�g�B
        String l_strPayType;
        if(WEB3MarginTradingDivDef.DEFAULT.equals(l_strMarginType))
        {
            l_strPayType = WEB3PayTypeDef.OTHER;
        }
        else
        {
            //�i���X�s��ٍϕʁj�戵�����̃C���X�^���X���擾���� 
            WEB3GentradeBranchMarketRepayDealtCond l_genBranchMarketRepayDealtCond = 
                new WEB3GentradeBranchMarketRepayDealtCond(
                    l_strInstitutionCode,//�����̕⏕����.�،���ЃR�[�h
                    l_gentradeBranch.getBranchCode(),//�����̕⏕����.���XID�̕��X�I�u�W�F�N�g.���X�R�[�h
                    l_strMarketCode,//�����̎s��R�[�h
                    l_strMarginType,//�����̐M�p����敪
                    l_dblRepaymentNum//�����ٍ̕ϊ����l
                    );   
            BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)
                l_genBranchMarketRepayDealtCond.getDataSourceObject();
            l_strPayType = 
                l_branchMarketRepayDealtCondRow.getSonarRepaymentType();
        }
        l_gentradeCommission.setPayType(l_strPayType);

        //2-7) �����`���l���F �����̒����`���l��
        l_gentradeCommission.setOrderChannel(l_strOrderChanel);    
                              
        //2-8) �����������`���l���F �����̒����`���l��
        l_gentradeCommission.setOrgOrderChannel(l_strOrderChanel);    
        
        //�s��R�[�h�iSONAR�j
        if (l_strMarketCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                Market l_market =
                    l_finObjectManager.getMarket(l_strInstitutionCode, l_strMarketCode);
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                l_gentradeCommission.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_nfe.getMessage(),
                   l_nfe);
            }
        }
        else
        {
            l_gentradeCommission.setSonarMarketCode(null);
        }
        
        // �R�j�@@���������萔���I�u�W�F�N�g�̃C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return  l_gentradeCommission;

    }
    
    /**
     * (calc���n�v��)<BR>
     * ���n�v�ł��v�Z����B<BR>
     * �idouble calcCapitalGainTax(SubAccount sub_account, TaxTypeEnum tax, double amount)<BR>
     * �@@�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�����̐ŋ敪���h��ʌ����h�@@or �h�X�g�b�N�I�v�V���������h�̏ꍇ�́A�O��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����̐ŋ敪����L�ȊO�̏ꍇ�i����������̏ꍇ�j�A<BR>
     * �@@�@@�@@�����̌ڋq�ŋ敪���A���n�v�Œ����Ώۂ��ǂ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@�@@�����̌ڋq�ŋ敪���h������������򒥎��h�̏ꍇ�F�@@�u���n�v�Œ����Ώہv�B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�F�@@�u���n�v�Œ����ΏۊO�v�B<BR>
     * <BR>
     * �@@�@@�@@���n�v�Œ����ΏۊO�̏ꍇ�͂O��ԋp����B<BR>
     * <BR>
     * �R�j�@@�u���n�v�Œ����Ώہv�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �R�|�P�j�@@���n�v�ł��v�Z���A�v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �@@�@@�E�ŗ��e�[�u�����������A����ɑΉ�������n�v�ŗ����擾����B<BR>
     * �@@�@@�@@���،���ЃR�[�h�F�@@�����̕⏕����.�،���ЃR�[�h<BR>
     * �@@�@@�E���n�v�� �� �����̋��z �~ ���n�v�ŗ�<BR>
     * �@@�@@���v�Z���ʂ��~�����؎̂āB<BR>
     * �@@�@@���}�C�i�X�l�̏ꍇ���A���̂܂ܕԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (SubAccount)<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * @@param l_dblAmount - (���z)<BR>
     * @@param l_baseDate - (���)<BR>
     *      ���n�v�ł��Z�o������<BR>
     * @@param l_accountTaxType - (�ڋq�ŋ敪)<BR>
     * �q�̐ŋ敪�B<BR>
     * �i�ڋq.�ŋ敪�^�ŋ敪�i���N�j�^�M�p����ŋ敪�^�M�p����ŋ敪�i���N�j�j<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public double calcCapitalGainTax(
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType,
        double l_dblAmount,
        Timestamp l_baseDate,
        TaxTypeEnum l_accountTaxType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCapitalGainTax(TaxTypeEnum, double, Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����̐ŋ敪���h��ʌ����hor �h�X�g�b�N�I�v�V���������h�̏ꍇ�́A�O��ԋp����B
        if (TaxTypeEnum.NORMAL.equals(l_taxType) || TaxTypeEnum.STOCK_OPTION.equals(l_taxType))
        {
            log.debug("���n�v�Œ����ΏۊO");
            log.exiting(STR_METHOD_NAME);
            return 0D;
        }
        // �Q�j�@@�����̐ŋ敪����L�ȊO�̏ꍇ�i����������̏ꍇ�j
        // �@@�@@�@@�����̌ڋq�ŋ敪���A���n�v�Œ����Ώۂ��ǂ����𔻒肷��
        else
        {
            if (!TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_accountTaxType))
            {
                log.debug("���n�v�Œ����ΏۊO");
                log.exiting(STR_METHOD_NAME);
                return 0D;
            }
        }
        
        // �R�j�@@�u���n�v�Œ����Ώہv�̏ꍇ�́A�ȉ��̏������s���B
        // �R�|�P�j�@@���n�v�ł��v�Z���A�v�Z���ʂ�ԋp����B
        //�@@�@@�@@�E�ŗ��e�[�u�����������A����ɑΉ�������n�v�ŗ����擾����B
        WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(
            l_subAccount.getInstitution().getInstitutionCode(),
            WEB3GentradeTaxRate.CAPITAL_GAIN_TAX,
            l_baseDate);
        
        //�@@�@@�@@�E���n�v�� �� �����̋��z �~ ���n�v�ŗ�
        BigDecimal l_oneHandred =
            new BigDecimal(WEB3GentradeNumberConstDef.HYAKU);
        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
        BigDecimal l_bdAmount = new BigDecimal(l_dblAmount);
        BigDecimal l_bdTaxRate =
            new BigDecimal(l_taxRate.getTaxRate()).divide(
                l_oneHandred,
                l_intScale,
                BigDecimal.ROUND_HALF_EVEN);
        int l_intCapitalGainTax = l_bdAmount.multiply(l_bdTaxRate).intValue();
        
        log.debug("****** �����̋��z�F[" + l_dblAmount + "]");
        log.debug("****** ���n�v�ŗ��F[" + l_bdTaxRate.doubleValue() + "]");
        log.debug("****** �v�Z���ʂ��~�����؎̂�");
        log.debug("****** ���n�v�� �� �����̋��z �~ ���n�v�ŗ� �F[" + l_intCapitalGainTax + "]");
        log.exiting(STR_METHOD_NAME);
        return (double)l_intCapitalGainTax;
    }

    /**
     * �iget�萔���R�[�X�R�[�h�j<BR>
     * <BR>
     * �ϑ��萔�������o�^�}�X�^�[����萔���R�[�X�R�[�h���擾����B<BR>
     * <BR>
     * -------------------<BR>
     * �P�j�@@�ϑ��萔�������o�^�}�X�^�[�����L�����Ō�������B<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�@@���@@����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�萔�����i�R�[�h�@@���@@�����萔�����i�R�[�h<BR>
     * �@@�@@�@@�o�^NO�@@���@@����.�萔��NO�{����.�}��<BR>
     * �@@�@@�@@�K�p�J�n�N�����@@���@@����.�������@@���@@�K�p�I���N����<BR>
     * <BR>
     * �@@�@@�Y���f�[�^�Ȃ��̏ꍇ�A�܂��͕����s���R�[�h���擾���ꂽ�ꍇ��<BR>
     * �@@�@@��O���X���[����B<BR>
     * <BR>
     * �Q�j�@@�擾�������R�[�h�̎萔���R�[�X�R�[�h��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strCommProductCode - (�萔�����i�R�[�h)<BR>
     * @@param l_strRegNo - (�萔��NO)<BR>
     * @@param l_strRevision - (�}��)<BR>
     * @@param l_datBizDate - (������)<BR>
     * @@throws WEB3BaseException
     * @@return String
     */
     public String getCommissionCourseDiv(
         String l_strInstitutionCode,
         String l_strCommProductCode,
         String l_strRegNo,
         String l_strRevision,
         Date l_datBizDate)
         throws WEB3BaseException
     {
         final String STR_METHOD_NAME =
             "getCommissionCourseDiv(String, String, String, String, Date)";
         log.entering(STR_METHOD_NAME);

         String l_strCommissionCourseDiv = null;
         try
         {
             // �P�j�@@�ϑ��萔�������o�^�}�X�^�[�����L�����Ō�������B
             // �@@�@@�@@�،���ЃR�[�h�@@���@@����.�،���ЃR�[�h
             // �@@�@@�@@�萔�����i�R�[�h�@@���@@�����萔�����i�R�[�h
             // �@@�@@�@@�o�^NO�@@���@@����.�萔��NO�{����.�}��
             // �@@�@@�@@�K�p�J�n�N�����@@���@@����.�������@@���@@�K�p�I���N����
             String l_strWhere = "institution_code = ? and comm_product_code = ? and reg_no = ? and appli_start_date <= ? and appli_end_date >= ?";
             QueryProcessor l_qp = l_qp = Processors.getDefaultProcessor();
             List l_lisRows = null;
             Object[] l_objWhereValues = { l_strInstitutionCode, l_strCommProductCode, l_strRegNo + l_strRevision, l_datBizDate, l_datBizDate };
             l_lisRows = l_qp.doFindAllQuery(EquityCommCondMstRow.TYPE, l_strWhere, l_objWhereValues);
             int l_intRowSize = l_lisRows.size();
             // �Y���f�[�^�Ȃ��̏ꍇ�A�܂��͕����s���R�[�h���擾���ꂽ�ꍇ�͗�O���X���[����B
             if (l_intRowSize == 0)
             {
                 log.error("�ϑ��萔�������o�^�}�X�^�[�ɊY������f�[�^�������݂��܂���B");
                 throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "�ϑ��萔�������o�^�}�X�^�[�ɊY������f�[�^�������݂��܂���B");
             }
             else if (l_intRowSize > 1)
             {
                 log.error("�ϑ��萔�������o�^�}�X�^�[�ɊY���f�[�^�� " + l_intRowSize + " �����݂��܂��B");
                 throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "�ϑ��萔�������o�^�}�X�^�[�ɊY���f�[�^�� " + l_intRowSize + " �����݂��܂��B");
             }
             
             l_strCommissionCourseDiv = ((EquityCommCondMstRow)l_lisRows.get(0)).getCommissionCourseDiv();
         }
         catch (DataNetworkException l_dnwe)
         {
             log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_dnwe.getMessage(),
                 l_dnwe);
         }
         catch (DataQueryException l_dqe)
         {
             log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
             throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_dqe.getMessage(),
                 l_dqe);
         }

         // �Q�j�@@�擾�������R�[�h�̎萔���R�[�X�R�[�h��ԋp����B
         log.exiting(STR_METHOD_NAME);
         return l_strCommissionCourseDiv;
    }
    
    /**
     * (create�萔��)<BR>
     * <BR>
     * ��莞�̈ϑ��萔���v�Z�Ɏg�p����萔���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �P�j�@@this.create�萔��(�����̒����P��.����ID)�ɂ��A�萔���I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�萔��.is�w�l �v���p�e�B�̃Z�b�g���������K�v�ȏꍇ(*1)�A<BR>
     * �@@�@@�@@�萔��.setIs�w�l(false)�ɂ��A�萔��.is�w�l �v���p�e�B��"���s"�ɕύX����B<BR>
     * <BR>
     * �@@�@@�@@(*1)�萔��.is�w�l �v���p�e�B�̃Z�b�g���������K�v�ȏꍇ�F<BR>
     * �@@�@@�@@�@@�@@���s�c���w�l�����A���� ������ ���� �ꕔ�o�� �̏ꍇ�̂݁B<BR>
     * �@@�@@�@@�@@�@@��������ɂ��w�l�����ƂȂ邪�A����͐��s�Ōv�Z���邽�߁B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�����̒����P��.�l�i����=="���s�c���w�l"<BR>
     * �@@�@@�@@�@@�@@���� �����̒����P��.��萔��==���.��萔��<BR>
     * �@@�@@�@@�@@�@@���� �����̒����P��.isPartiallyExecuted( )==true�i���ꕔ���j<BR>
     * �@@�@@�@@�@@�@@���� �����̖��.�폜�t���O==FALSE�i��������j<BR>
     * <BR>
     * �R�j�@@�쐬�����萔���I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@param l_orderExec - (���)<BR>
     * ���I�u�W�F�N�g�B
     * @@return WEB3GentradeCommission
     * @@throws WEB3BaseException
     */
    public WEB3GentradeCommission createCommission(
        EqTypeOrderUnit l_orderUnit,
        EqTypeOrderExecution l_orderExec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCommission(EqTypeOrderUnit, EqTypeOrderExecution)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCommission l_commission = this.createCommission(l_orderUnit.getOrderId());
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderExecutionRow l_orderExecRow =
            (EqtypeOrderExecutionRow)l_orderExec.getDataSourceObject();
        if (WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(l_orderUnitRow.getPriceConditionType()) &&
            l_orderUnitRow.getExecutedQuantity() == l_orderExecRow.getExecQuantity() &&
            l_orderUnit.isPartiallyExecuted() &&
            BooleanEnum.FALSE.equals(l_orderExecRow.getDeleteFlag()))
        {
            l_commission.setIsLimitPrice(false);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }
    
    /**
     * (calc�T�Z�뉿�P��)<BR>
     * <BR>
     * �T�Z�뉿�P�����v�Z����B<BR>
     * <BR>
     * �@@�p�����[�^.�뉿 �� �p�����[�^.���ʂ̌v�Z���ʂ�ԋp����B<BR>
     * �@@���v�Z���ʂ��A�����́u�~�����L�������v�܂ł̌����ɁA�؎̂Ăɂ��ۂ߂�B<BR>
     * <BR>
     * @@param l_dblBookValue - �뉿<BR>
     * �뉿<BR>
     * @@param l_dblQuantity - ����<BR>
     * ����<BR>
     * @@param l_intUnderYenEffectiveBit - �~�����L������<BR>
     * �~�����i�����_�ȉ��j�扽�ʂ܂ł�L���Ƃ��邩���w�肷��B<BR>
     * ex.) �����_�ȉ���T�ʂ�؎̂Ă��A�����_�ȉ���S�ʂ܂ł̒l��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�u�S�v���w�肷��B<BR>
     * <BR>
     * @@return double
     */
    public double calcEstimatedBookPrice(
        double l_dblBookValue,
        double l_dblQuantity,
        int l_intUnderYenEffectiveBit)
    {
        final String STR_METHOD_NAME = 
            "calcEstimatedBookPrice(double, double, long)";
        log.entering(STR_METHOD_NAME);
        
        if(l_intUnderYenEffectiveBit < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "�����́u�~�����L�������v = " + l_intUnderYenEffectiveBit);
        }
        
        //�뉿�P�����뉿������
        BigDecimal l_bdBookValue = new BigDecimal(l_dblBookValue);
        BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);
        BigDecimal l_bdBookPrice =
            l_bdBookValue.divide(
                l_bdQuantity,
                l_intUnderYenEffectiveBit,
                BigDecimal.ROUND_DOWN);
                
        log.debug("****** �p�����[�^.�뉿�F[" + l_dblBookValue + "]");
        log.debug("****** �p�����[�^.���ʁF[" + l_dblQuantity + "]");
        log.debug("****** �p�����[�^.�~�����L�������F[" + l_intUnderYenEffectiveBit + "]");
        log.debug("****** �T�Z�뉿�P�����ۗL���Y.�뉿���ۗL���Y.���ʁF[" + l_bdBookPrice.doubleValue() + "]");
                
        log.exiting(STR_METHOD_NAME);
        return l_bdBookPrice.doubleValue();
    }
    
    /**
     * �icreate�萔���j<BR>
     * <BR>
     * �T�Z��������v�Z�Ɏg�p����萔���I�u�W�F�N�g�𐶐����A<BR>
     * �����Ŏw�肳�ꂽ�����P�ʃI�u�W�F�N�g���v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�萔���C���X�^���X�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�ɒ����P�ʃI�u�W�F�N�g�̒l���Z�b�g����B<BR> 
     * <BR>
     * �@@�،���ЃR�[�h�F �p�����[�^.�����P��.���XID���畔�X�I�u�W�F�N�g���擾���A<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���X�I�u�W�F�N�g�̓����ڂ��Z�b�g <BR>
     * �@@���XID�F �p�����[�^.�����P�ʂ̓����� <BR>
     * �@@�萔�����i�R�[�h�F �p�����[�^.�����P�ʂ̓�����<BR> 
     * �@@����R�[�h�iSONAR�j�F �p�����[�^.�����P�ʂ̓�����<BR>
     * �@@�������F�@@�p�����[�^.�����P�ʂ̓�����<BR> 
     * �@@�ٍϋ敪�F (*1)<BR>
     * �@@�����`���l���F �p�����[�^.�����P��.���񒍕��̒����`���l��<BR> 
     * �@@�����������`���l���F �p�����[�^.�����P��.���񒍕��̒����`���l��<BR> 
     * �@@�������萔��No�F �p�����[�^.�����P��.���񒍕��̎萔��No <BR>
     * �@@�������萔��No�}�ԁF �p�����[�^.�����P��.���񒍕��̎萔��No�}��<BR> 
     * �@@is�w�l�F�@@�p�����[�^.�����P��.isMarketOrder( )==false�̏ꍇ��true���A<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.isMarketOrder( )==true�̏ꍇ��false���A���ꂼ��Z�b�g<BR> 
     * �@@�s��R�[�h�iSONAR�j�F�@@�p�����[�^.�����P��.�s��ID�ɊY������ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�s��I�u�W�F�N�g.�s��R�[�h�iSONAR�j���Z�b�g <BR>
     * <BR>
     * �@@(*1)�ٍϋ敪<BR>
     * �@@�@@�E�p�����[�^.�����P��.�ٍϋ敪�iSONAR�j==null�̏ꍇ<BR> 
     * �@@�@@�@@�@@�ٍϋ敪.���̑��i"00"�j���Z�b�g�i�Œ�j<BR>
     * �@@�@@�E�p�����[�^.�����P��.�ٍϋ敪�iSONAR�j!=null�̏ꍇ<BR> 
     * �@@�@@�@@�@@�p�����[�^.�����P��.�ٍϋ敪�iSONAR�j�����̂܂܃Z�b�g<BR> 
     * <BR>
     * ���ȉ��̃v���p�e�B�͐ݒ肵�Ȃ��B<BR>
     * �萔��No<BR>
     * �萔��No�}��<BR>
     * �萔�����z<BR>
     * ���o��v�Z�p���<BR>
     * �萔���R�[�X�R�[�h<BR>
     * <BR>
     * �Q�j�@@���������萔���I�u�W�F�N�g�̃C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��
     * @@return WEB3GentradeCommission<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GentradeCommission createCommission(EqTypeOrderUnitImpl l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCommission(EqTypeOrderUnitImpl)";

        log.entering(STR_METHOD_NAME);

        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        //�萔���C���X�^���X�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ��
        //�����P�ʃI�u�W�F�N�g�̒l���Z�b�g����B
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //���XID���畔�X�I�u�W�F�N�g���擾����
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = new WEB3GentradeBranch(l_orderUnit.getBranchId());
        }
        catch (DataException l_de)
        {
            // DB�A�N�Z�X�G���[�B
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        //�،���ЃR�[�h�F�����P�ʃI�u�W�F�N�g.���XID���畔�X�I�u�W�F�N�g���擾���A
        // ���X�I�u�W�F�N�g�̓����ڂ��Z�b�g 
        l_commission.setInstitutionCode(
            l_branch.getInstitution().getInstitutionCode());
        //���XID�F �����P�ʃI�u�W�F�N�g�̓�����
        l_commission.setBranchId(l_orderUnit.getBranchId());
        //�萔�����i�R�[�h�F �����P�ʃI�u�W�F�N�g�̓�����
        l_commission.setCommissionProductCode(
            l_orderUnitRow.getCommProductCode());
        //����R�[�h�iSONAR�j�F�����P�ʃI�u�W�F�N�g�̓�����
        l_commission.setSonarTradedCode(
            l_orderUnitRow.getSonarTradedCode());
        
        //�������F�@@�����P�ʃI�u�W�F�N�g�̓�����
        String l_strBizDate = l_orderUnitRow.getBizDate();
        Date l_datBizDate = null;
        try
        {
            l_datBizDate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(l_strBizDate);
        }
        catch (ParseException l_pe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_pe.getMessage(),
                l_pe);
        }
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        //�ٍϋ敪�F�@@�����P�ʃI�u�W�F�N�g.�ٍϋ敪�iSONAR�j
        if (l_orderUnitRow.getSonarRepaymentType() != null)
        {
            l_commission.setPayType(l_orderUnitRow.getSonarRepaymentType());
        }
        else
        {
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
        }
        //�����������`���l���F �����P�ʃI�u�W�F�N�g.���񒍕��̒����`���l��
        l_commission.setOrderChannel(l_orderUnitRow.getOrderChanel());
        //�������萔��No�F �����P�ʃI�u�W�F�N�g.���񒍕��̎萔��No
        l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
        //�������萔��No�}�ԁF �����P�ʃI�u�W�F�N�g.���񒍕��̎萔��No�}��
        l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
        // is�w�l�F�@@�����P�ʃI�u�W�F�N�g.isMarketOrder()==false�̏ꍇ��true���A
        //�@@�����P�ʃI�u�W�F�N�g.isMarketOrder()==true�̏ꍇ��false���A���ꂼ��Z�b�g
        if (l_orderUnit.isMarketOrder() == false)
        {
            l_commission.setIsLimitPrice(true);
        }
        else
        {
            l_commission.setIsLimitPrice(false);
        }
        //�s��R�[�h�iSONAR�j
        l_commission.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }
    
    /**
     * (calc�T�Z���n���v)<BR>
     * <BR>
     * ���n���v�̋��z���v�Z����B <BR>
     * ���ŋ敪�@@=�@@"���"�@@�̏ꍇ�͊T�Z���n���v���v�Z����B<BR>
     * <BR>
     * �P�j�@@���n���v���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@���n���v �� �����̋��z�|�i�����̔����� �~ �뉿�P��(*1)�j <BR>
     * �@@�@@�@@���i�����̔����� �~ �뉿�P���j�̌��ʂ��~�����؎̂Ă�����A�����̋��z���猸�Z����B<BR>
     * <BR>
     *       �v�Z�������n���v��ԋp����B<BR>
     * <BR>
     *       (*1)�뉿�P���F�����v�Z�T�[�r�X.calc�뉿�P��( )�Ŏ擾�B<BR>
     * �@@�@@�@@�@@�@@-----------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@��calc�뉿�P��( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@����ID�F�@@�����̖���ID <BR>
     * �@@�@@�@@�@@�@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�@@�@@�@@�@@�ŋ敪�F�@@�����̐ŋ敪<BR>
     * �@@�@@�@@�@@�@@�~�����L�������F�@@4�i�����_��T�ʂ�؎̂āj<BR>
     * �@@�@@�@@�@@�@@-----------------------------------------------------------<BR>
     * <BR>
     * @@param l_dblExpensesCalcAmount - (���z)<BR>
     * @@param l_dblOrderQuantity - (������)<BR>
     * @@param l_lngProductId - ����ID�B<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_taxType - �ŋ敪<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA01AC
     */
    public double calcEstimatedCapitalGain(
        double l_dblExpensesCalcAmount,
        double l_dblOrderQuantity,
        long l_lngProductId,
        SubAccount l_subAccount,
        TaxTypeEnum l_taxType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcEstimatedCapitalGain(double, double, long, SubAccount, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���n���v���v�Z����B 
        //  (*1)�뉿�P���F�����v�Z�T�[�r�X.calc�뉿�P��( )�Ŏ擾�B 
        double l_dblBookValuePrice = this.calcBookValuePrice(
          	l_lngProductId,
           	l_subAccount,
           	l_taxType,
           	4);

        //�@@���n���v �� �����̋��z�|�i�����̔����� �~ �뉿�P��(*1)�j 
        //�@@���i�����̔����� �~ �뉿�P���j�̌��ʂ��~�����؎̂Ă�����A�����̋��z���猸�Z����B 
        double l_dblCapitalGain = l_dblExpensesCalcAmount - Math.floor(l_dblOrderQuantity * l_dblBookValuePrice);

        //  �v�Z�������n���v��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_dblCapitalGain;
    }
    
    /**
     * (get���n�v�L�����)<BR>
     * <BR>
     * ���n�v�L����Ԃ𔻒肷��B <BR>
     * <BR>
     * �P�D����.�g�����U�N�V�����^�C�v���i����������@@�܂��́@@���n����j�̏ꍇ�A<BR>
     * �@@�@@0�F�����@@��ԋp����B<BR>
     * <BR>
     * �Q�D�ۗL���Y���擾����B <BR>
     * <BR>
     * �@@�@@�����|�W�V�����}�l�[�W��.get�ۗL���Y  <BR>
     * --------------------------------------------- <BR>
     * �@@�@@�����h�c�F�@@�����̌����h�c  <BR>
     * �@@�@@�⏕�����h�c�F�@@�����̕⏕�����h�c  <BR>
     * �@@�@@�����h�c�F�@@�����̖����h�c  <BR>
     * �@@�@@�ŋ敪�F�@@�����̐ŋ敪  <BR>
     * --------------------------------------------- <BR>
     * <BR>
     * �R�D���n�v�L����Ԃ̔�����s���B <BR>
     * �@@�R�|�P�j�@@�ۗL���Y�e�[�u��.�뉿�i�뉿�P���v�Z�p�j��0�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@1�F�L�� ��ԋp����B <BR>
     * <BR>
     * �@@�R�|�R�j�@@��L�ȊO�̏ꍇ�A0�F���� ��ԋp����B<BR>
     * <BR>
     * @@param l_lngAccountId - �����h�c<BR>
     * @@param l_lngSubAccountId - �⏕�����h�c<BR>
     * @@param l_lngProductId - �����h�c<BR>
     * @@param l_taxType - �ŋ敪<BR>
     * @@param l_finTransactionType  - �g�����U�N�V�����^�C�v<BR>
     * @@return String <BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D2FEA01AC
     */
    public String getCapitalGainStatus(
    	long l_lngAccountId,
    	long l_lngSubAccountId,
        long l_lngProductId,
        TaxTypeEnum l_taxType,
        FinTransactionType l_finTransactionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCapitalGainStatus(long, long, long, TaxTypeEnum, FinTransactionType)";
        log.entering(STR_METHOD_NAME);
        
        //�P�D����.�g�����U�N�V�����^�C�v���i����������@@�܂��́@@���n����j�̏ꍇ�A
        //�@@�@@0�F�����@@��ԋp����B
        if(!FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_finTransactionType) 
            && !FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_finTransactionType))
        {
        	log.exiting(STR_METHOD_NAME);
        	return WEB3CapitalGainStatusDef.INVALIDITY;
        }
        
        //�Q�D�ۗL���Y���擾����B 
        //    �����|�W�V�����}�l�[�W��.get�ۗL���Y  
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        EqTypeAsset l_eqTypeAsset = l_positionManager.getAsset(
        	l_lngAccountId,
        	l_lngSubAccountId,
        	l_lngProductId,
        	l_taxType);
        
        //�R�D���n�v�L����Ԃ̔�����s���B
        //  �R�|1�j�@@�ۗL���Y�e�[�u��.�뉿�i�뉿�P���v�Z�p�j��0�̏ꍇ�A 
        //             1�F�L�� ��ԋp����B 
        if(l_eqTypeAsset.getBookValue() != 0)
        {
        	log.exiting(STR_METHOD_NAME);
        	return WEB3CapitalGainStatusDef.VALIDITY;
        }

        //  �R�|�R�j�@@��L�ȊO�̏ꍇ�A0�F���� ��ԋp����B 
    	log.exiting(STR_METHOD_NAME);
    	return WEB3CapitalGainStatusDef.INVALIDITY;
    }

    /**
     * (set�萔���v�Z����)<BR>
     * <BR>
     * �����̃R�s�[���萔���̈ȉ��̌v�Z���ʃv���p�e�B���A�����̃R�s�[��萔���ɃZ�b�g����B<BR>
     * <BR>
     * ���Ώۃv���p�e�B��<BR>
     * is�w�l<BR>
     * �萔�����z�A�萔��No�A�萔��No�}��<BR>
     * �萔���R�[�X�R�[�h�A�Œ�萔��<BR>
     * ���o��v�Z�p����A<BR>
     * �������萔��No�A<BR>
     * �������萔��No�}��<BR>
     * @@param l_copyCommission - (�R�s�[��萔��)<BR>
     * �R�s�[��̎萔���I�u�W�F�N�g�B<BR>
     * @@param l_copyOrgCommission - (�R�s�[���萔��)<BR>
     * �R�s�[���̎萔���I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void setCommissionCalcResult(
        WEB3GentradeCommission l_copyCommission,
        WEB3GentradeCommission l_copyOrgCommission)throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "setCommissionCalcResult(WEB3GentradeCommission, WEB3GentradeCommission)";
        log.entering(STR_METHOD_NAME);

        if (l_copyCommission == null || l_copyOrgCommission == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�����̃R�s�[���萔���̈ȉ��̌v�Z���ʃv���p�e�B���A�����̃R�s�[��萔���ɃZ�b�g����B
        //���Ώۃv���p�e�B��
        //is�w�l
        //�萔�����z�A�萔��No�A�萔��No�}��
        //�萔���R�[�X�R�[�h�A�Œ�萔��
        //���o��v�Z�p����A
        //�������萔��No�A
        //�������萔��No�}��

        //is�w�l
        l_copyCommission.setIsLimitPrice(l_copyOrgCommission.isLimitPrice());

        //�萔�����z
        l_copyCommission.setCommission(l_copyOrgCommission.getCommission());

        //�萔��No
        l_copyCommission.setCommissionNo(l_copyOrgCommission.getCommissionNo());

        //�萔��No�}��
        l_copyCommission.setCommissionRevNo(l_copyOrgCommission.getCommissionRevNo());

        //�萔���R�[�X�R�[�h
        l_copyCommission.setCommissionCourseDiv(l_copyOrgCommission.getCommissionCourseDiv());

        //�Œ�萔��
        l_copyCommission.setMinCommission(l_copyOrgCommission.getMinCommission());

        //���o��v�Z�p���
        l_copyCommission.setExpensesCalcAmount(l_copyOrgCommission.getExpensesCalcAmount());

        //�������萔��No
        l_copyCommission.setOrgCommissionNo(l_copyOrgCommission.getOrgCommissionNo());

        //�������萔��No�}��
        l_copyCommission.setOrgCommissionRevNo(l_copyOrgCommission.getOrgCommissionRevNo());

        log.exiting(STR_METHOD_NAME);
    }

}
@
