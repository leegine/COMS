head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�v�Z�T�[�r�X(WEB3IfoBizLogicProvider.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 �n�U�c (���u) �V�K�쐬
                 : 2004/07/07 ���Q�i���u�j�@@���@@calcRestraintTurnover() ���X�V���܂���
Revesion History : 2007/06/08 �Ј��� (���u) �d�l�ύX���f��No.681
Revesion History : 2007/06/14 �Ј��� (���u) �d�l�ύX���f��No.731
Revesion History : 2007/10/10 �����F (���u) �d�l�ύX���f��No.784 �v�Z���� 014 015
Revesion History : 2007/10/16 �����F (���u) �d�l�ύX���f��No.791
Revesion History : 2008/08/18 ���z(���u) IFO�����_�Ή�
Revesion History : 2008/09/03 ���z(���u) IFO�����_�Ή�
**/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�v�Z�T�[�r�X)<BR>
 * �I�v�V�����v�Z�T�[�r�X�N���X<BR>
 * @@author  �n�U�c
 * @@version 1.0
 */
public class WEB3IfoBizLogicProvider extends WEB3GentradeBizLogicProvider implements GlobalBizLogicProvider, IfoBizLogicProvider
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoBizLogicProvider.class);

    /**
     * @@roseuid 40C075150157
     */
    public WEB3IfoBizLogicProvider()
    {

    }

    /**
     * (calc��n���)<BR>
     * ��n������v�Z����B<BR>
     * <BR>
     * �ȉ��̌v�Z���Ŏ�n��������߂�B<BR>
     * <BR>
     * �i�v�Z���j<BR>
     * �@@���@@���̏ꍇ�i����==SideEnum.SideEnum.BUY�j<BR>
     * �@@�@@���o��v�Z�p����{�i�ϑ��萔���{�ϑ��萔������Łj<BR>
     * <BR>
     * �@@���@@���̏ꍇ�i����==SideEnum.SideEnum.SELL�j<BR>
     * �@@�@@���o��v�Z�p����|�i�ϑ��萔���{�ϑ��萔������Łj<BR>
     * <BR>
     * (*1) �敨OP�������.1�P�ʓ�����搔<BR>
     * @@param l_dealing - ����<BR>
     * �@@SideEnum.BUY�i���j<BR>
     * �@@SideEnum.SELL�i���j<BR>
     *
     * @@param l_dblExpenseCalculationAmount - ����p�v�Z�p���<BR>
     * <BR>
     * �@@�|���ʁ~�P���~�w���搔(*1)<BR>
     * �@@�A���A�T�Z��n����̌v�Z�̏ꍇ�́i���ʁ~�P���~�w���搔(*1)�~�����S�����j<BR>
     * @@param l_dblConsignmentCommission - �ϑ��萔��
     * @@param l_dblCommConsumptionTax - �ϑ��萔�������
     * @@return double
     * @@roseuid 4073C1BD007C
     */
    public double calcDeliveryAmount(SideEnum l_dealing, double l_dblExpenseCalculationAmount, double l_dblConsignmentCommission, double l_dblCommConsumptionTax)
    {
        double l_dblDeliveryAmount = 0.0;
        BigDecimal l_bdExpenseCalculationAmount = new BigDecimal(l_dblExpenseCalculationAmount + "");
        BigDecimal l_bdConsignmentCommission = new BigDecimal(l_dblConsignmentCommission + "");
        BigDecimal l_bdCommConsumptionTax = new BigDecimal(l_dblCommConsumptionTax + "");
        log.debug("l_dealing = " + l_dealing);
        log.debug("����p�v�Z�p��� = " + l_dblExpenseCalculationAmount);
        log.debug("�ϑ��萔�� = " + l_dblConsignmentCommission);
        log.debug("�ϑ��萔������� = " + l_dblCommConsumptionTax);
        
        if (SideEnum.BUY.equals(l_dealing))
        {
            log.debug("���̏ꍇ");
            l_dblDeliveryAmount =
                l_bdExpenseCalculationAmount.add(l_bdConsignmentCommission).add(l_bdCommConsumptionTax).doubleValue();
        }

        if (SideEnum.SELL.equals(l_dealing))
        {
            log.debug("���̏ꍇ");
            l_dblDeliveryAmount =
                l_bdExpenseCalculationAmount.subtract(l_bdConsignmentCommission).subtract(l_bdCommConsumptionTax).doubleValue();
        }
        log.debug("��n��� = " + l_dblDeliveryAmount);
        return l_dblDeliveryAmount;
    }

    /**
     * (calc�萔���i���j)<BR>
     * ������ɑ΂���ϑ��萔���E����ł��v�Z���A  <BR>
     * ���̓��e��\��ConsolidatedCommissionInfo�I�u�W�F�N�g��Ԃ��B  <BR>
     * <BR>
     * 1) ����.������薾��Params[]�̗v�f�����[�v�������s���A<BR>
     * �ȉ����ڂ̍��v�l���v�Z����B <BR>
     * <BR>
     * �@@�@@�E(���v)�����(*1) <BR>
     * �@@�@@�E(���v)��萔��(*2) <BR>
     * <BR>
     * 2) (���v)�ϑ��萔�����Z�o����B <BR>
     * <BR>
     * �@@�@@2)-1 this.create�萔��(�����P��ID,����)���R�[������(*3) <BR>
     * �@@�@@2)-2 2)-1�Ő��������萔��.���o��v�Z�p����� 1)�ŎZ�o����<BR>
     * �@@�@@�@@�@@�@@�@@(���v)�������ݒ肷�� <BR>
     * �@@�@@2)-2 calc�ϑ��萔��(�萔��,�⏕����)���R�[������(*4) <BR>
     * <BR>
     * 3) (���v)����ł��Z�o����B <BR>
     * <BR>
     * �@@�@@3)-1 calc�����(���z,���,�⏕����)���R�[������(*5) <BR>
     * �@@�@@3)-2 2)-1�Ő��������萔��.����ł� 3)-1�ŎZ�o����<BR>
     * �@@�@@�@@�@@�@@�@@(���v)����ł�ݒ肷�� <BR>
     * <BR>
     * 4) �ϑ��萔���A����łɂ��āA���v�Z���s���B <BR>
     * <BR>
     * �@@�@@4�|1) �萔��.�萔���搔 == null�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�@@this.calc�萔���i������j���R�[������(*6) <BR>
     * <BR>
     * �@@�@@4�|2) �萔��.�萔���搔 != null�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�@@this.calc�萔���i�������j���R�[������(*7) <BR>
     * <BR>
     * 5) 4)�Ŏ擾�����v�Z���ʂ�ԋp����B <BR>
     * <BR>
     * <BR>
     * (*1) ������薾��Params[n]�̖����(��萔�� * ���P�� * �w���搔)<BR>
     * �@@�@@�����v�����l�B <BR>
     * (*2) ������薾��Params[n].��萔�ʂ����v�����l�B <BR>
     * (*3) [�����ݒ�] <BR>
     * �@@�@@�@@�@@�����P��ID �F ������薾��Params[0].�����P��ID <BR>
     * �@@�@@�@@�@@���� �F 1)�ŎZ�o����(���v)��萔�� <BR>
     * (*4) [�����ݒ�] <BR>
     * �@@�@@�@@�@@�萔�� �F 2)-1�Ő��������萔�� <BR>
     * �@@�@@�@@�@@�⏕���� : �⏕���� <BR>
     * (*5) [�����ݒ�] <BR>
     * �@@�@@�@@�@@���z �F 2)-1�Ő��������萔��.get�萔�����z() <BR>
     * �@@�@@�@@�@@��� �F 2)-1�Ő��������萔��.get������() <BR>
     * �@@�@@�@@�@@�⏕���� �F �⏕���� <BR>
     * (*6) [�����ݒ�] <BR>
     * �@@�@@�@@�@@������薾��Params �F �����\�b�h�̈���.������薾��Params <BR>
     * �@@�@@�@@�@@�萔�� �F 2)-1�Ő��������萔�� <BR>
     * �@@�@@�@@�@@(����)�����[] �F ������薾��Params�̖���� <BR>
     * (*7) [�����ݒ�] <BR>
     * �@@�@@�@@�@@������薾��Params �F �����\�b�h�̈���.������薾��Params <BR>
     * �@@�@@�@@�@@�萔�� �F 2)-1�Ő��������萔�� <BR>
     * �@@�@@�@@�@@(����)��萔��[] �F ������薾��Params.��萔��<BR>
     * @@param l_arrIfoFinTransactionRow - ������薾��Params<BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̔z��<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4088B35503E1
     */
    public ConsolidatedCommissionInfo calcCommission(IfoFinTransactionRow[] l_arrIfoFinTransactionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCommission(IfoFinTransactionRow[])";

        log.entering(STR_METHOD_NAME);

        if (l_arrIfoFinTransactionRow == null)
        {
            log.error("������null�ł��B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        IfoProductManager l_productManager = (IfoProductManager)l_tm.getProductManager();
        FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();

        // �m���v�n������ƍő�������߂�
        int l_intLength = l_arrIfoFinTransactionRow.length;
        BigDecimal[] l_bdAmounts = new BigDecimal[l_intLength];
        BigDecimal l_bdTotalAmount = new BigDecimal("0.0");
        double l_dblTotalQuantity = 0;
        
        int l_intMax = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            IfoFinTransactionRow l_row = l_arrIfoFinTransactionRow[i];

            TradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct =  l_productManager.getTradedProduct(
                    l_productManager.getProduct(l_row.getProductId()), 
                    l_finObjectManager.getMarket(l_row.getMarketId()));
            }
            catch (NotFoundException l_exp)
            {
                log.error(STR_METHOD_NAME, l_exp);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    l_exp.getMessage());
            }

            IfoTradedProductRow l_ifoTradedProductRow = (IfoTradedProductRow)l_tradedProduct.getDataSourceObject();
             
            BigDecimal l_bdPrice = new BigDecimal(l_row.getPrice() + "");
            log.debug("������薾��[" + i + "].���P���F" + l_bdPrice.doubleValue());
    
            BigDecimal l_bdQuantity = new BigDecimal(l_row.getQuantity() + "");
            log.debug("������薾��[" + i + "].��萔�ʁF" + l_bdQuantity.doubleValue());
            
            l_dblTotalQuantity += l_row.getQuantity();
    
            // ���������萔�ʁ~���P���~�w���搔
            // �w���搔�͐敨OP��������}�X�^.�P�P�ʓ���w���搔����ݒ肵�Ă��������B
            BigDecimal l_bdUnitSize = new BigDecimal(l_ifoTradedProductRow.getUnitSize() + "");
            l_bdAmounts[i] = l_bdPrice.multiply(l_bdQuantity).multiply(l_bdUnitSize);
            log.debug("������薾��[" + i + "].�����z�F" + l_bdAmounts[i].doubleValue());
    
            l_bdTotalAmount = l_bdTotalAmount.add(l_bdAmounts[i]);
        }
        log.debug("�m���v�n������F" + l_bdTotalAmount.doubleValue());
        log.debug("�m���v�n��萔�ʁF" + l_dblTotalQuantity);

        // �m���v�n�ϑ��萔�������߂�
        BigDecimal l_bdTotalCommission;
        WEB3GentradeCommission l_commission;
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_arrIfoFinTransactionRow[l_intMax].getAccountId(),
                    l_arrIfoFinTransactionRow[l_intMax].getSubAccountId()
                    );
            
            l_commission = this.createCommission(
                l_arrIfoFinTransactionRow[l_intMax].getOrderUnitId(),
                l_dblTotalQuantity);
            l_commission.setExpensesCalcAmount(l_bdTotalAmount.doubleValue());
            
            calcCommission(l_commission, l_subAccount);
            l_bdTotalCommission = new BigDecimal(l_commission.getCommission() + "");
            log.debug("�m���v�n�ϑ��萔���F" + l_bdTotalCommission.doubleValue());
        }
        catch (NotFoundException l_nfE)
        {
            String l_strMessage = "�f�[�^��������܂���";
            log.error(l_strMessage, l_nfE);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                l_nfE.getMessage()
                );
        }

        // �m���v�n����ł̌v�Z���s��
        BigDecimal l_bdTotalSalesTax =
            new BigDecimal(
                calcSalesTax(
                    l_commission.getCommission(),
                    l_commission.getOrderBizDate(),
                    l_subAccount) + ""
                    );
        log.debug("�m���v�n����ŁF" + l_bdTotalSalesTax.doubleValue());
        l_commission.setConsumptionTax(l_bdTotalSalesTax.doubleValue());

        //�ϑ��萔���A����łɂ��āA���v�Z���s���B
        ConsolidatedCommissionInfo l_consolidatedCommissionInfo = null;

        //�萔��.�萔���搔 == null�̏ꍇ
        //this.calc�萔���i������j���R�[������
        if (l_commission.getCommitionPerUnit() == 0)
        {
            double[] l_dblAmounts = new double[l_intLength];
            for (int i = 0; i < l_intLength; i++)
            {
                l_dblAmounts[i] = l_bdAmounts[i].doubleValue();
            }
            l_consolidatedCommissionInfo =
                this.calcCommissionAmount(l_arrIfoFinTransactionRow, l_commission, l_dblAmounts);
        }
        //�萔��.�萔���搔 != null�̏ꍇ
        //this.calc�萔���i�������j���R�[������
        else
        {
            double[] l_dblQuantitys = new double[l_intLength];
            for (int i = 0; i < l_intLength; i++)
            {
                l_dblQuantitys[i] = l_arrIfoFinTransactionRow[i].getQuantity();
            }
            l_consolidatedCommissionInfo =
                this.calcCommissionQuantity(l_arrIfoFinTransactionRow, l_commission, l_dblQuantitys);
        }

        log.exiting(STR_METHOD_NAME);
        return l_consolidatedCommissionInfo;
    }

    /**
     * (calc�������)<BR>
     * ����������v�Z���ĕԋp����B<BR>
     * <BR>
     * ������� �� ���������~�v�Z�P���~�w���搔(*1)<BR>
     * <BR>
     * (*1) �敨OP�������.1�P�ʓ�����搔<BR>
     * @@param l_dblQuantity - ����
     * @@param l_dblCalcUnitPrice - �v�Z�P��<BR>
     * ����������v�Z���邽�߂̒P��<BR>
     * @@param l_ifoTradedProduct - �敨OP�������<BR>
     * �敨OP��������I�u�W�F�N�g<BR>
     * @@return double
     * @@roseuid 40A853D00145
     */
    public double calcTurnOver(double l_dblQuantity, double l_dblCalcUnitPrice, WEB3IfoTradedProductImpl l_ifoTradedProduct)
    {
        final String STR_METHOD_NAME = "calcTurnOver(double , double, WEB3IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);

        double l_dblTurnover = 0.0;
        BigDecimal l_bdTurnover;
        BigDecimal l_bdUnitPrice;
        BigDecimal l_bdQuantity;
        BigDecimal l_bdMultiple;

        //������� �� ���������~�v�Z�P���~�w���搔(*1)
        //(*1) �敨OP�������.1�P�ʓ�����搔
        IfoTradedProductRow l_row =
            (IfoTradedProductRow)l_ifoTradedProduct.getDataSourceObject();
        l_bdMultiple = new BigDecimal(l_row.getUnitSize() + "");

        l_bdQuantity = new BigDecimal(l_dblQuantity + "");
        l_bdUnitPrice = new BigDecimal(l_dblCalcUnitPrice + "");
        l_bdTurnover = l_bdUnitPrice.multiply(l_bdQuantity);
        l_bdTurnover = l_bdTurnover.multiply(l_bdMultiple);
        l_dblTurnover = l_bdTurnover.setScale(0, BigDecimal.ROUND_FLOOR).doubleValue();
        log.debug("��������F[" + l_dblTurnover + "]");
        log.debug("�v�Z�P���F[" + l_dblCalcUnitPrice + "]");
        log.debug("���������F[" + l_dblQuantity + "]");
        log.debug("�w���搔�F[" + l_row.getUnitSize() + "]");

        log.exiting(STR_METHOD_NAME);

        return l_dblTurnover;
    }

    /**
      * (calc�S���������)<BR>
      *<BR>
      * �v�Z�P���A�������S������������v�Z���ĕԋp����B<BR>
      *<BR>
      * ���v�Z�̏ڍׂɂ��Ă�<BR>
      * �u��{�݌v�v�Z�����i�����w���I�v�V�����j_ver.1.1.doc�v<BR>
      * 8.�S����������@@���Q�� <BR>
      *<BR>
      * �P�j��Е��X���i�e�[�u����芄���S�������擾����B�i����.���XID�ƈ���.�萔�����i�R�[�h�Ŏ擾�j<BR>
      *<BR>
      * �Q�j�S������������v�Z����B<BR>
      *<BR>
      * �@@�S���������������.���ʁ~����.�v�Z�P���~�����S����<BR>
      *
      * �i���j�����S�������̗p���Ȃ��P�[�X�ɂ��ẮA���Y���ڂɂ�'1'���ݒ肳��Ă��邽�� �A<BR>
      * �ꗥ��L�̌v�Z���Ōv�Z����B<BR>
      *
      * �R�j�@@�w���搔���|����<BR>
      * �@@�ȉ��̒l��ԋp����B<BR>
      * �i�S����������j�~�敨OP�������.1�P�ʓ�����搔<BR>
      *
      * @@param l_dblQuantity - (����)<BR>
      *<BR>
      * �����i���j����<BR>
      * @@param l_dblUnitPrice - (�v�Z�P��)<BR>
      *<BR>
      * �S������������v�Z���邽�߂̒P��<BR>
      * @@param l_lngBranchId - ���XID<BR>
      * @@param l_strCommissionProductCode - �萔�����i�R�[�h<BR>
      * �T�O�F�敨<BR>
      * �T�P�F�����w���n�o<BR>
      * @@param l_blnIsLimitPrice - (is�w�l)<BR>
      *
      * �w�l�����̏ꍇ��true�A���s�����̏ꍇ��false�B<BR>
      * @@param l_ifoTradedProduct - �敨OP��������I�u�W�F�N�g
      * @@return double
      * @@throws WEB3BaseEception �S����������̌v�Z�Ɏ��s�����ꍇ
      */
     public double calcRestraintTurnOver(
         double l_dblQuantity,
         double l_dblUnitPrice,
         long l_lngBranchId,
         String l_strCommissionProductCode,
         boolean l_blnIsLimitPrice,
		WEB3IfoTradedProductImpl l_ifoTradedProduct)
         throws WEB3BaseException
     {
         String l_errorMessage1 = "��Е��X���i�e�[�u���ɊY������f�[�^������܂���B";
         long l_lngRestraintTurnover;
         double l_dbRestraintTurnover = 0.0;
         InstBranchProductRow l_row = null;
         BigDecimal l_bdUnitPrice;
         BigDecimal l_bdQuantity;
         BigDecimal l_bdPremiumRestraintRate;
		 BigDecimal l_bdRestraintTurnover;
		 BigDecimal l_bdUnitSize;
         int l_bdEstimatePriceCalcForm;
         final String STR_METHOD_NAME = "calcRestraintTurnover()";
         log.entering(STR_METHOD_NAME);

         if(l_dblQuantity < 0 || l_dblUnitPrice < 0)
         {
             log.debug("����<0 || �v�Z�P��<0");
             throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                  STR_METHOD_NAME);
         }
         // ��Е��X���i�e�[�u����ǂݍ���
         try
         {
             l_row =
                 InstBranchProductDao.findRowByPk(
                     l_lngBranchId,
                     l_strCommissionProductCode);
         }
         catch (DataException exp2)
         {
             // DB�A�N�Z�X�G���[�B
             log.debug("DB�A�N�Z�X�G���[.");
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 STR_METHOD_NAME,
                 exp2.getMessage(),
                 exp2);
         }
         if (l_row == null)
         {
             // �Y������f�[�^�������B
             log.debug("�Y������f�[�^�������B");
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 STR_METHOD_NAME,
                 l_errorMessage1);
         }

         if (!l_blnIsLimitPrice)
         {
             // ���s���������s�̏ꍇ
             log.debug("���s���������s�̏ꍇ:");
             l_bdUnitPrice = new BigDecimal(l_dblUnitPrice + "");
             l_bdQuantity = new BigDecimal(l_dblQuantity + "");
             l_bdPremiumRestraintRate =
                 new BigDecimal(l_row.getPremiumRestraintRate() + "");
             l_bdEstimatePriceCalcForm = l_row.getEstimatePriceCalcForm();
             if (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_bdEstimatePriceCalcForm)
             {
                 // �T�Z���z�v�Z������STOP�������̏ꍇ
                 // �S��������� �� �v�Z�P�� �~ ��������
                 log.debug("�T�Z���z�v�Z������STOP�������̏ꍇ: �S��������� �� �v�Z�P�� �~ ��������");
				l_bdRestraintTurnover =
                     l_bdUnitPrice
                         .multiply(l_bdQuantity);
             }
             else
             {
                 //�T�Z���z�v�Z�����������S�������̏ꍇ
                 // �S��������� �� �v�Z�P�� �~ �������� �~ �����S����
                 log.debug("�T�Z���z�v�Z�����������S�������̏ꍇ: �S��������� �� �v�Z�P�� �~ �������� �~ �����S����");
				l_bdRestraintTurnover =
                     l_bdUnitPrice
                         .multiply(l_bdQuantity)
                         .multiply(l_bdPremiumRestraintRate);
             }
             log.debug("�v�Z�P���F[" + l_dblUnitPrice + "]");
             log.debug("���������F[" + l_dblQuantity + "]");
             log.debug("�����S�����F[" + l_row.getPremiumRestraintRate() + "]");
         }
         else
         {
             // ���s�������w�l�̏ꍇ
             // �S��������� �� �v�Z�P�� �~ ��������
             log.debug("���s�������w�l�̏ꍇ: �S��������� �� �v�Z�P�� �~ ��������");
             l_bdUnitPrice = new BigDecimal(l_dblUnitPrice + "");
             l_bdQuantity = new BigDecimal(l_dblQuantity + "");
			 l_bdRestraintTurnover = l_bdUnitPrice.multiply(l_bdQuantity);
             log.debug("�v�Z�P���F[" + l_dblUnitPrice + "]");
             log.debug("���������F[" + l_dblQuantity + "]");
         }

		//(*1) �敨OP�������.1�P�ʓ�����搔
		IfoTradedProductRow l_IfoTradedProductRow =
			(IfoTradedProductRow)l_ifoTradedProduct.getDataSourceObject();
		l_bdUnitSize = new BigDecimal(l_IfoTradedProductRow.getUnitSize() + "");
         // �����_�ȉ��؂�̂�
        l_dbRestraintTurnover =l_bdRestraintTurnover.multiply(l_bdUnitSize).setScale(
            0, BigDecimal.ROUND_FLOOR).doubleValue();
		log.debug("�S����������F[" + l_dbRestraintTurnover + "]");

         log.exiting(STR_METHOD_NAME);
         return l_dbRestraintTurnover;
     }


    /**
     * (calcCommission)<BR>
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
     * (calcSalesTax)<BR>
     * @@param l_dbData
     * @@return double
     * @@roseuid 40A3495903AE
    */
    public double calcSalesTax(double l_dblData)
    {
        //�w��̃T�[�r�X�����������ł��Z�o���܂��B
        return 0.0;
    }

    /**
     * (calcCapitalGainTax)<BR>
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
     * (checkTradingPower)<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_orderSpec - �������e
     *
     * @@return OrderValidationResult
     * @@roseuid 4010AF2C0227
     */
    public OrderValidationResult checkTradingPower(SubAccount l_subAccount, OrderSpec l_orderSpec)
    {
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    /**
     * (calc�����)<BR>
     * @@param pType - �v���_�N�g�^�C�v
     * @@param pId - �v���_�N�gID
     * @@param price - ���P��
     * @@param quantity - ��萔��
     * @@param qType - ���ʃ^�C�v
     *
     * @@return double - �����z
     * @@roseuid 4010AF2C0227
     */
    public double calcExecutionAmount(
        ProductTypeEnum l_productType, 
        long l_lngProductId, 
        double l_dblPrice, 
        double l_dblQuantity, 
        QuantityTypeEnum l_quantityType)
    {
        String STR_METHOD_NAME = "calcExecutionAmount)";
        log.debug(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        
        try
        {
            long l_lngMarketID = l_ifoProductManagerImpl.getProduct(l_lngProductId).getPrimaryMarket().getMarketId();
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoProductManagerImpl.getTradedProduct(l_lngProductId, l_lngMarketID);
            
            log.exiting(STR_METHOD_NAME);

            BigDecimal l_bdPrice = new BigDecimal(l_dblPrice + "");
            BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity + "");
            BigDecimal l_bdUnitSize = new BigDecimal(l_ifoTradedProductImpl.getUnitSize() + "");
            return l_bdPrice.multiply(l_bdQuantity).multiply(l_bdUnitSize).doubleValue();
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
    }
    
    /**
     * (create�萔��)<BR>
     *�@@�T�Z��������v�Z�Ɏg�p����萔���I�u�W�F�N�g�𐶐����A<BR> 
     *�@@�����Ŏw�肳�ꂽ�����̒����P�ʃI�u�W�F�N�g���v���p�e�B���Z�b�g����B<BR> 
     *�@@<BR>
     *�P�j�@@OP�����}�l�[�W��.getOrderUnit()�ŁA<BR> 
     *�@@�@@�@@�����̒����P��ID�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR> 
     *�@@<BR>
     *�Q�j�@@�萔���C���X�^���X�𐶐����A<BR>
     *�@@�@@�@@�v���p�e�B�Ɉȉ��̒ʂ�ɒ����P�ʃI�u�W�F�N�g�̒l���Z�b�g����B<BR> 
     *�@@�@@�@@�����`���l��    = �����P��.���񒍕��̒����`���l��<BR>
     *�@@�@@�@@�،���ЃR�[�h   = �����P��.���XID����擾�����،���Ђ̃R�[�h<BR>
     *�@@�@@�@@���XID      = �����P��.���XID<BR>
     *�@@�@@�@@������       = �����P��.������<BR>
     *�@@�@@�@@����R�[�h(SONAR)  = �����P��.����R�[�h(SONAR)<BR>
     *�@@�@@�@@�萔�����i�R�[�h  = �����P��.�萔�����i�R�[�h<BR>
     *�@@�@@�@@�ٍϋ敪      = 00�i���̑��j<BR>
     *�@@�@@�@@�����������`���l�� = �����P��.���񒍕��̒����`���l��<BR>
     *�@@�@@�@@�������萔��No  = �����P��.���񒍕��̎萔��No<BR>
     *�@@�@@�@@�������萔��No�}��    = �����P��.���񒍕��̎萔��No�}��<BR>
     *�@@�@@�@@is�w�l  �@@�@@�@@�@@�@@�@@�@@= �����P��.isMarketOrder( )==false�̏ꍇ��true<BR>
     *�@@�@@�@@�����P�ʃI�u�W�F�N�g.isMarketOrder( )==true�̏ꍇ��false<BR>
     *�@@�@@�@@�����Y�����R�[�h   = �����P��.get����( ).get�����Y�����R�[�h( )�̖߂�l<BR>
     *�@@�@@�@@���v��敪  = �����P��.���v��敪<BR>
     *�@@�@@�@@����     = �����P��.��������<BR>
     *�@@<BR>
     *�@@�@@�@@���ȉ��̃v���p�e�B�͐ݒ肵�Ȃ��B<BR> 
     *�@@�@@�@@�萔��No <BR>
     *�@@�@@�@@�萔��No�}�� <BR>
     *�@@�@@�@@�萔�����z <BR>
     *�@@�@@�@@���o��v�Z�p��� <BR>
     *�@@�@@�@@�萔���R�[�X�R�[�h <BR>
     *�@@�@@�@@�s��R�[�h�iSONAR�j<BR>
     *�@@�@@�@@�萔���搔<BR>
     *�@@<BR>
     *�R�j���������萔���I�u�W�F�N�g�̃C���X�^���X��ԋp����B<BR>      
     * @@param l_lngOrderUnitId - �����P��Id
     * @@return WEB3GentradeCommission
     * @@roseuid 4010AF2C0227
     */
    public WEB3GentradeCommission createCommission(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createCommission(long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCommission l_commission = null;
        try
        {
            //�P�j�@@OP�����}�l�[�W��.getOrderUnit()�ŁA 
            //�@@�@@�@@�����̒����P��ID�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B 
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager = 
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            IfoOrderUnitRow l_orderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //�Q�j�@@�萔���C���X�^���X�𐶐����A
            //  �v���p�e�B�Ɉȉ��̒ʂ�ɒ����P�ʃI�u�W�F�N�g�̒l���Z�b�g����B
            l_commission = new WEB3GentradeCommission();
 
            // �����`���l��    = �����P��.���񒍕��̒����`���l��
            l_commission.setOrderChannel(l_orderUnitRow.getOrderChanel());

            // �،���ЃR�[�h   = �����P��.���XID����擾�����،���Ђ̃R�[�h
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_orderUnitRow.getBranchId());
            l_commission.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());

            // ���XID      = �����P��.���XID
            l_commission.setBranchId(l_orderUnitRow.getBranchId());

            // ������       = �����P��.������
            String l_strBizDate = l_orderUnitRow.getBizDate();
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd").getTime()));

            // ����R�[�h(SONAR)  = �����P��.����R�[�h(SONAR)
            l_commission.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());

            // �萔�����i�R�[�h  = �����P��.�萔�����i�R�[�h
            l_commission.setCommissionProductCode(l_orderUnitRow.getCommProductCode());

            // �ٍϋ敪      = 00�i���̑��j
            l_commission.setPayType(WEB3PayTypeDef.OTHER);

            // �����������`���l�� = �����P��.���񒍕��̒����`���l��
            l_commission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());

            // �������萔��No  = �����P��.���񒍕��̎萔��No
            l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());

            // �������萔��No�}��    = �����P��.���񒍕��̎萔��No�}��
            l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());

            // is�w�l  �@@�@@�@@�@@�@@�@@�@@= �����P��.isMarketOrder( )==false�̏ꍇ��true
            if (l_orderUnit.isMarketOrder() == false)
            {
                l_commission.setIsLimitPrice(true);
            }
            else
            {
                l_commission.setIsLimitPrice(false);
            }
            
            // �����Y�����R�[�h   = �����P��.get����( ).get�����Y�����R�[�h( )�̖߂�l
            l_commission.setUnderlyingProductCode(
                ((IfoProduct)l_orderUnit.getProduct()).getUnderlyingProductCode());
            
            // ���v��敪  = �����P��.���v��敪
            l_commission.setDayTradeType(l_orderUnitRow.getDayTradeType());
            
            // ����     = �����P��.��������
            l_commission.setQuantity(l_orderUnit.getQuantity());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }
    
    /**
     * (create�萔��)<BR>
     *�@@�T�Z��������v�Z�Ɏg�p����萔���I�u�W�F�N�g�𐶐����A<BR> 
     *�@@�����Ŏw�肳�ꂽ�����̒����P�ʃI�u�W�F�N�g���v���p�e�B���Z�b�g����B<BR> 
     *�@@<BR>
     *�P�j�@@create�萔���ilong �����P��ID�j�i�I�[�o�[���[�h���\�b�h�j���R�[������B<BR> 
     *�@@�@@�@@�����̒����P��ID�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR> 
     *�@@<BR>
     *�Q�j�@@�P�j�Ő��������萔���I�u�W�F�N�g�̃v���p�e�B�ֈȉ��̒ʂ�ɒl���Z�b�g����B<BR>
     *      �萔��.����= ����.����<BR> 
     *�@@<BR>
     *�R�j�@@�萔���I�u�W�F�N�g�̃C���X�^���X��ԋp����B<BR>      
     * @@param l_lngOrderUnitId - �����P��Id
     * @@param l_quantity - ����
     * @@return WEB3GentradeCommission
     * @@roseuid 4010AF2C0227
     */
    public WEB3GentradeCommission createCommission(long l_lngOrderUnitId, double l_quantity)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createCommission(long,double)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCommission l_commission = null;
        //�P�j�@@create�萔���ilong �����P��ID�j�i�I�[�o�[���[�h���\�b�h�j���R�[������B
        l_commission = this.createCommission(l_lngOrderUnitId);
        
        //�Q�j�@@�P�j�Ő��������萔���I�u�W�F�N�g�̃v���p�e�B�ֈȉ��̒ʂ�ɒl���Z�b�g����B
        // �萔��.����= ����.����
        l_commission.setQuantity(l_quantity);
        
        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }

    /**
     * (calc�萔���i������j)<BR>
     * ������̈ϑ��萔���E����ł������̊�������v�Z���A <BR>
     * ���̓��e��\��ConsolidatedCommissionInfo�I�u�W�F�N�g��Ԃ��B <BR>
     * <BR>
     * 1) ����.������薾��Params[]�̒�����A�ȉ��̏����ōő�������肷��B <BR>
     * <BR>
     * �@@�@@�@@��������ō��z�̖����ő���Ƃ���B <BR>
     * �@@�@@�A����������z�̏ꍇ�A���P�������������ő���Ƃ���B <BR>
     * �@@�@@�B���P�������z�̏ꍇ�A<BR>
     * �@@�@@�@@�@@���e�[�u��.��菇�ԍ����傫�������ő���Ƃ���B <BR>
     * <BR>
     * 2) 1)�Ō��肵���ő���ȊO�̗v�f�ɂ��āA(����)�ϑ��萔���A<BR>
     * (����)����ł��v�Z����B <BR>
     * <BR>
     * �@@�@@2)-1 (����)�ϑ��萔���͈ȉ��̂Ƃ���ɎZ�o����B <BR>
     * <BR>
     * �@@�@@�@@�@@(����)�ϑ��萔�� = <BR>
     * �@@�@@�@@�@@�@@�@@(���v)�ϑ��萔��(*1) * (����)�����(*3) / (���v)�����(*4) <BR>
     * �@@�@@�@@�@@�������_�ȉ��؎̂� <BR>
     * <BR>
     * �@@�@@2)-2 (����)����ł͈ȉ��̂Ƃ���ɎZ�o����B <BR>
     * <BR>
     * �@@�@@�@@�@@(����)����� = <BR>
     * �@@�@@�@@�@@�@@�@@(���v)�����(*2) * (����)�����(*3) / (���v)�����(*4) <BR>
     * �@@�@@�@@�@@�������_�ȉ��؎̂� <BR>
     * <BR>
     * �@@�@@2)-3 ConsolidatedCommisionInfo�֌v�Z���ʂ�ݒ肷��(*5) <BR>
     * <BR>
     * 3) �ő���̗v�f�ɂ��āA(����)�ϑ��萔���A(����)����ł��v�Z����B <BR>
     * <BR>
     * �@@�@@3)-1 (����)�ϑ��萔���͈ȉ��̂Ƃ���ɎZ�o����B <BR>
     * <BR>
     * �@@�@@�@@�@@(����)�ϑ��萔�� = (���v)�ϑ��萔�� - 2)��<BR>
     * �@@�@@�@@�@@�@@�@@�Z�o����(����)�ϑ��萔�����v�l <BR>
     * <BR>
     * �@@�@@3)-2 (����)����ł͈ȉ��̂Ƃ���ɎZ�o����B <BR>
     * <BR>
     * �@@�@@�@@�@@(����)����� = (���v)����� - 2)�ŎZ�o����(����)����ō��v�l <BR>
     * <BR>
     * �@@�@@3)-3 ConsolidatedCommisionInfo�֌v�Z���ʂ�ݒ肷��(*5) <BR>
     * <BR>
     * (*1) ����.�萔��.get�萔�����z() <BR>
     * (*2) ����.�萔��.get�����() <BR>
     * (*3) ����.(����)�����[]�̗v�f�ԍ��́A<BR>
     * �@@�@@����.������薾��Params[]�̗v�f�ԍ��ƑΉ�������B <BR>
     * (*4) ����.�萔��.get���o��v�Z�p���() <BR>
     * (*5) ConsolidatedCommisionInfo�̗v�f�ԍ��́A<BR>
     * �@@�@@����.������薾��Params[]�̗v�f�ԍ��ƑΉ�������B<BR>
     * @@param l_arrIfoFinTransactionRows - (������薾��Params)<BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̔z��<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔��<BR>
     * @@param l_dblAmounts - ([����]�����)<BR>
     * [����]�����<BR>
     * @@return ConsolidatedCommissionInfo
     * @@throws WEB3BaseException
     */
    public ConsolidatedCommissionInfo calcCommissionAmount(
        IfoFinTransactionRow[] l_arrIfoFinTransactionRows,
        WEB3GentradeCommission l_commission,
        double[] l_dblAmounts) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCommissionAmount(IfoFinTransactionRow[], WEB3GentradeCommission, double[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        IfoOrderManager l_orderMgr = (IfoOrderManager)l_tm.getOrderManager();

        //����.������薾��Params[]�̒�����A�ȉ��̏����ōő�������肷��
        //��������ō��z�̖����ő���Ƃ���
        //����������z�̏ꍇ�A���P�������������ő���Ƃ���
        //���P�������z�̏ꍇ�A���e�[�u��.��菇�ԍ����傫�������ő���Ƃ���
        int l_intLength = l_arrIfoFinTransactionRows.length;
        int l_intMax = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            IfoFinTransactionRow l_row = l_arrIfoFinTransactionRows[i];
            if (i != 0)
            {
                BigDecimal l_bdAmount = new BigDecimal(l_dblAmounts[i] + "");
                BigDecimal l_bdMaxAmount = new BigDecimal(l_dblAmounts[l_intMax] + "");
                int l_intResult = l_bdAmount.compareTo(l_bdMaxAmount);

                if (l_intResult == 1)
                {
                    // �����z���ő�����z���傫���ꍇ
                    l_intMax = i;
                }
                else if (l_intResult == 0)
                {
                    IfoFinTransactionRow l_maxRow = l_arrIfoFinTransactionRows[l_intMax];
                    if (l_row.getPrice() > l_maxRow.getPrice())
                    {
                        // �����z�����z�ŁA���P���������ꍇ
                        l_intMax = i;
                    }
                    else if (l_row.getPrice() == l_maxRow.getPrice())
                    {
                        IfoOrderExecution l_orderExec = null;
                        IfoOrderExecution l_orderExecMax = null;
                        try
                        {
                            l_orderExec =
                                (IfoOrderExecution)l_orderMgr.getOrderExecution(
                                    l_row.getOrderExecutionId());
                            l_orderExecMax =
                                (IfoOrderExecution) l_orderMgr.getOrderExecution(
                                    l_maxRow.getOrderExecutionId());
                            if (l_orderExec.getExecutionSerialNo()
                                > l_orderExecMax.getExecutionSerialNo())
                            {
                                // ���P�������z�ŁA��菇�ԍ����傫���ꍇ
                                l_intMax = i;
                            }
                        }
                        catch (NotFoundException l_exp)
                        {
                            String l_strMessage = "�I�v�V�������f�[�^��������܂���B";
                            log.error(l_strMessage, l_exp);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                l_exp.getMessage());
                        }
                    }
                }
            }
        }

        // ����(n)�̈ϑ��萔���Ə���ŁA�ϑ��萔���̑��a�A����ł̑��a�̌v�Z
        // �ő���͏������X�L�b�v
        double[] l_dbCommissions = new double[l_intLength];
        double[] l_dbSalesTaxs = new double[l_intLength];
        BigDecimal l_bdSigmaCommission = new BigDecimal(0);
        BigDecimal l_bdSigmaSalesTax = new BigDecimal(0);
        BigDecimal l_bdCommission;
        BigDecimal l_bdSalesTax;
        BigDecimal l_bdTotalCommission = new BigDecimal(l_commission.getCommission() + "");
        BigDecimal l_bdTotalAmount = new BigDecimal(l_commission.getExpensesCalcAmount() + "");
        BigDecimal l_bdTotalSalesTax = new BigDecimal(l_commission.getConsumptionTax() + "");
        int l_scale = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            if (i != l_intMax)
            {
                BigDecimal l_bdAmount = new BigDecimal(l_dblAmounts[i] + "");
                l_bdCommission =
                    (l_bdTotalCommission.multiply(l_bdAmount)).divide(
                        l_bdTotalAmount,
                        l_scale,
                        BigDecimal.ROUND_DOWN);
                l_dbCommissions[i] = l_bdCommission.longValue();
                log.debug("�m����(" + i + ")�n�ϑ��萔���F" + l_dbCommissions[i]);
                l_bdSalesTax =
                    (l_bdTotalSalesTax.multiply(l_bdAmount)).divide(
                        l_bdTotalAmount,
                        l_scale,
                        BigDecimal.ROUND_DOWN);
                l_dbSalesTaxs[i] = l_bdSalesTax.longValue();
                log.debug("�m����(" + i + ")�n����ŁF" + l_dbSalesTaxs[i]);
                l_bdSigmaCommission =
                    l_bdSigmaCommission.add(new BigDecimal(l_dbCommissions[i] + ""));
                l_bdSigmaSalesTax =
                    l_bdSigmaSalesTax.add(new BigDecimal(l_dbSalesTaxs[i] + ""));
            }
        }

        // �ϑ��萔���A����ł̒[�����v�Z���A�ő���̈ϑ��萔���A����łɊ񂹂�
        l_bdCommission = l_bdTotalCommission.subtract(l_bdSigmaCommission);
        l_dbCommissions[l_intMax] = l_bdCommission.doubleValue();
        log.debug(
            "�m����(" + l_intMax + ")�n�ϑ��萔���F" + l_dbCommissions[l_intMax]);
        l_bdSalesTax = l_bdTotalSalesTax.subtract(l_bdSigmaSalesTax);
        l_dbSalesTaxs[l_intMax] = l_bdSalesTax.doubleValue();

        log.exiting(STR_METHOD_NAME);
        return new ConsolidatedCommissionInfo(
            l_dbCommissions,
            l_bdTotalCommission.doubleValue(),
            l_dbSalesTaxs,
            l_bdTotalSalesTax.doubleValue());
    }

    /**
     * (calc�萔���i�������j)<BR>
     * ������̈ϑ��萔���E����ł��萔�ʂ̊�������v�Z���A <BR>
     * ���̓��e��\��ConsolidatedCommissionInfo�I�u�W�F�N�g��Ԃ��B <BR>
     * <BR>
     * 1) ����.������薾��Params[]�̒�����A�ȉ��̏����ōő�������肷��B <BR>
     * <BR>
     * �@@�@@�@@���e�[�u��.��菇�ԍ����傫�������ő���Ƃ���B <BR>
     * <BR>
     * 2) 1)�Ō��肵���ő���ȊO�̗v�f�ɂ��āA<BR>
     * (����)�ϑ��萔���A(����)����ł��v�Z����B <BR>
     * <BR>
     * �@@�@@2)-1 (����)�ϑ��萔���͈ȉ��̂Ƃ���ɎZ�o����B <BR>
     * <BR>
     * �@@�@@�@@�@@(����)�ϑ��萔�� = <BR>
     * �@@�@@�@@�@@�@@�@@(���v)�ϑ��萔��(*1) * (����)��萔��(*3) / (���v)��萔��(*4)<BR>
     * �@@�@@�@@�@@�������_�ȉ��؎̂� <BR>
     * <BR>
     * �@@�@@2)-2 (����)����ł͈ȉ��̂Ƃ���ɎZ�o����B <BR>
     * <BR>
     * �@@�@@�@@�@@(����)����� = <BR>
     * �@@�@@�@@�@@�@@�@@(���v)�����(*2) * (����)��萔��(*3) / (���v)��萔��(*4) <BR>
     * �@@�@@�@@�@@�������_�ȉ��؎̂� <BR>
     * <BR>
     * �@@�@@2)-3 ConsolidatedCommisionInfo�֌v�Z���ʂ�ݒ肷��(*5) <BR>
     * <BR>
     * 3) �ő���̗v�f�ɂ��āA(����)�ϑ��萔���A(����)����ł��v�Z����B <BR>
     * <BR>
     * �@@�@@3)-1 (����)�ϑ��萔���͈ȉ��̂Ƃ���ɎZ�o����B <BR>
     * <BR>
     * �@@�@@�@@�@@(����)�ϑ��萔�� = (���v)�ϑ��萔�� - 2)��<BR>
     * �@@�@@�@@�@@�@@�@@�Z�o����(����)�ϑ��萔�����v�l <BR>
     * <BR>
     * �@@�@@3)-2 (����)����ł͈ȉ��̂Ƃ���ɎZ�o����B <BR>
     * <BR>
     * �@@�@@�@@�@@(����)����� = (���v)����� - 2)�ŎZ�o����(����)����ō��v�l <BR>
     * <BR>
     * �@@�@@3)-3 ConsolidatedCommisionInfo�֌v�Z���ʂ�ݒ肷��(*5) <BR>
     * <BR>
     * (*1) ����.�萔��.get�萔�����z() <BR>
     * (*2) ����.�萔��.get�����() <BR>
     * (*3) ����.(����)��萔��[]�̗v�f�ԍ��́A<BR>
     * ����.������薾��Params[]�̗v�f�ԍ��ƑΉ�������B <BR>
     * (*4) ����.�萔��.get����() <BR>
     * (*5) ConsolidatedCommisionInfo�̗v�f�ԍ��́A<BR>
     * ����.������薾��Params[]�̗v�f�ԍ��ƑΉ�������B<BR>
     * @@param l_arrIfoFinTransactionRows - (������薾��Params)<BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̔z��<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔��<BR>
     * @@param l_dblQuantitys - ([����]��萔��)<BR>
     * [����]��萔��<BR>
     * @@return ConsolidatedCommissionInfo
     * @@throws WEB3BaseException
     */
    public ConsolidatedCommissionInfo calcCommissionQuantity(
        IfoFinTransactionRow[] l_arrIfoFinTransactionRows,
        WEB3GentradeCommission l_commission,
        double[] l_dblQuantitys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCommissionAmount(IfoFinTransactionRow[], WEB3GentradeCommission, double[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        IfoOrderManager l_orderMgr = (IfoOrderManager)l_tm.getOrderManager();

        //����.������薾��Params[]�̒�����A�ȉ��̏����ōő�������肷��
        //���e�[�u��.��菇�ԍ����傫�������ő���Ƃ���
        int l_intLength = l_arrIfoFinTransactionRows.length;
        int l_intMax = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            IfoFinTransactionRow l_row = l_arrIfoFinTransactionRows[i];
            if (i != 0)
            {

                IfoFinTransactionRow l_maxRow = l_arrIfoFinTransactionRows[l_intMax];
                IfoOrderExecution l_orderExec = null;
                IfoOrderExecution l_orderExecMax = null;
                try
                {
                    l_orderExec =
                        (IfoOrderExecution)l_orderMgr.getOrderExecution(
                            l_row.getOrderExecutionId());
                    l_orderExecMax =
                        (IfoOrderExecution) l_orderMgr.getOrderExecution(
                            l_maxRow.getOrderExecutionId());
                    if (l_orderExec.getExecutionSerialNo()
                        > l_orderExecMax.getExecutionSerialNo())
                    {
                        //��萔�ʂ������̏ꍇ�A���e�[�u��.��菇�ԍ����傫�������ő���Ƃ���
                        l_intMax = i;
                    }
                }
                catch (NotFoundException l_exp)
                {
                    String l_strMessage = "�I�v�V�������f�[�^��������܂���B";
                    log.error(l_strMessage, l_exp);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        l_exp.getMessage());
                }
            }
        }

        // ����(n)�̈ϑ��萔���Ə���ŁA�ϑ��萔���̑��a�A����ł̑��a�̌v�Z
        // �ő���͏������X�L�b�v
        double[] l_dbCommissions = new double[l_intLength];
        double[] l_dbSalesTaxs = new double[l_intLength];
        BigDecimal l_bdSigmaCommission = new BigDecimal(0);
        BigDecimal l_bdSigmaSalesTax = new BigDecimal(0);
        BigDecimal l_bdCommission;
        BigDecimal l_bdSalesTax;
        BigDecimal l_bdTotalCommission = new BigDecimal(l_commission.getCommission() + "");
        BigDecimal l_bdTotalQuantity = new BigDecimal(l_commission.getQuantity() + "");
        BigDecimal l_bdTotalSalesTax = new BigDecimal(l_commission.getConsumptionTax() + "");
        int l_scale = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            if (i != l_intMax)
            {
                BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantitys[i] + "");
                l_bdCommission =
                    (l_bdTotalCommission.multiply(l_bdQuantity)).divide(
                        l_bdTotalQuantity,
                        l_scale,
                        BigDecimal.ROUND_DOWN);
                l_dbCommissions[i] = l_bdCommission.longValue();
                log.debug("�m����(" + i + ")�n�ϑ��萔���F" + l_dbCommissions[i]);
                l_bdSalesTax =
                    (l_bdTotalSalesTax.multiply(l_bdQuantity)).divide(
                        l_bdTotalQuantity,
                        l_scale,
                        BigDecimal.ROUND_DOWN);
                l_dbSalesTaxs[i] = l_bdSalesTax.longValue();
                log.debug("�m����(" + i + ")�n����ŁF" + l_dbSalesTaxs[i]);
                l_bdSigmaCommission =
                    l_bdSigmaCommission.add(new BigDecimal(l_dbCommissions[i] + ""));
                l_bdSigmaSalesTax =
                    l_bdSigmaSalesTax.add(new BigDecimal(l_dbSalesTaxs[i] + ""));
            }
        }

        // �ϑ��萔���A����ł̒[�����v�Z���A�ő���̈ϑ��萔���A����łɊ񂹂�
        l_bdCommission = l_bdTotalCommission.subtract(l_bdSigmaCommission);
        l_dbCommissions[l_intMax] = l_bdCommission.doubleValue();
        log.debug(
            "�m����(" + l_intMax + ")�n�ϑ��萔���F" + l_dbCommissions[l_intMax]);
        l_bdSalesTax = l_bdTotalSalesTax.subtract(l_bdSigmaSalesTax);
        l_dbSalesTaxs[l_intMax] = l_bdSalesTax.doubleValue();

        log.exiting(STR_METHOD_NAME);
        return new ConsolidatedCommissionInfo(
            l_dbCommissions,
            l_bdTotalCommission.doubleValue(),
            l_dbSalesTaxs,
            l_bdTotalSalesTax.doubleValue());
    }
}
@
