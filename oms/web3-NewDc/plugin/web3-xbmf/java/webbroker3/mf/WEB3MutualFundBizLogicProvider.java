head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�̌v�Z�T�[�r�X(WEB3MutualFundBizLogicProvider)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 ��O�� (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2006/06/26 ���� (���u) �v�Z����  No.022
Revesion History : 2006/07/18 ���{ (SRA) �v�Z����  No.023
Revesion History : 2007/01/04 ���{ (SRA) �v�Z����  No.024
Revesion History : 2007/02/06 �юu��(���u) ���f��No.531
Revesion History : 2007/10/15 ���^�](���u) ���f��No.582
**/

package webbroker3.mf;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundBizLogicProvider;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3RecruitCommissionDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mf.define.WEB3MFCommissionDivDef;
import webbroker3.mf.define.WEB3MFDealDivDef;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * ���M�̌v�Z�T�[�r�X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundBizLogicProvider extends WEB3GentradeBizLogicProvider
    implements GlobalBizLogicProvider, MutualFundBizLogicProvider 
{
    /**
     * ���򒥎����i���Łj
     */
    public static final String INCOME_TAX = "INCOME_TAX";

    /**
     * ���򒥎����i�n���Łj
     */
    public static final String LOCAL_TAX = "LOCAL_TAX";

    /**
     * ����ŗ�
     */
    public static final String TAX_RATE = "TAX_RATE";
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundBizLogicProvider.class);

    /**
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
     * @@param pType - �v���_�N�g�^�C�v
     * @@param pId - �v���_�N�gID
     * @@param price - ���P��
     * @@param quantity - ��萔��
     * @@param qType - ���ʃ^�C�v
     *
     * @@return double - �����z
     * @@roseuid 4010AF2C0227
     */

    public double calcExecutionAmount(ProductTypeEnum pType, long pId, double price, double quantity, QuantityTypeEnum qType)
    {
        return 0.0;
    }
    
    /**
     * @@param l_orderExecution
     * @@return double
     * @@roseuid 40A3495A00B7
     */
    public ConsolidatedCommissionInfo calcCommission(Object[] l_objData)
    {
        //���钍���̖��萔�����Z�o����B
        return null;
    }
    
    /**
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
     * (calc�T�Z�������)<BR>
     * �T�Z����������Z�o���ĊT�Z��n����I�u�W�F�N�g�̊T�Z����������邢��<BR>
     * �T�Z�������(�O�݁j�ɃZ�b�g����B<BR>
     * <BR>
     * <BR>
     * <BR>
     * (1) �����̊g�����M�����I�u�W�F�N�g�D�ʉ݃R�[�h<BR>
     * ��T0�iWEB3MFOrderQuantityType.EN�j�j<BR>
     * <BR>
     * �@@�y���t�z�@@�����敪�����t�̏ꍇ<BR>
     * <BR>
     * �@@�T�Z������� �� �������� �~ ���t����z �^ ����z�v�Z�P��<BR>
     * <BR>
     * �@@�@@���������@@�@@�@@�@@�@@�@@�F�����̒�������<BR>
     * �@@�@@���t����z�@@�@@�@@�F�����̊g�����M�����̔��t����z<BR>
     * �@@�@@����z�v�Z�P�ʁF�����̊g�����M�����̊���z�v�Z�P��<BR>
     * <BR>
     * <BR>
     * �@@�y���^�抷�z �����敪����� or �抷�̏ꍇ<BR>
     * <BR>
     * �@@�T�Z������� �� �������� �~ ��񉿊z  �^ ����z�v�Z�P��<BR>
     * <BR>
     * �@@�@@���������@@�@@�@@�@@�@@�@@�F�����̒�������<BR>
     * �@@�@@��񉿊z�@@�@@�@@�@@�@@�@@�F�g�����M�����̉�񉿊z<BR>
     * �@@�@@����z�v�Z�P�ʁF�g�����M�����̊���z�v�Z�P��<BR>
     * <BR>
     *  �y��W�z�@@�����敪����W�̏ꍇ <BR>
     * <BR>
     * �@@�T�Z������� �� �������� �~ ��W���z �^ ����z�v�Z�P�� <BR>
     * <BR>
     * �@@�@@���������@@�@@�@@�@@�@@�@@�F�����̒������� <BR>
     * �@@�@@��W���z�@@�@@�@@�@@�@@�@@�F�����̊g�����M�����̕�W���z <BR>
     * �@@�@@����z�v�Z�P�ʁF�����̊g�����M�����̊���z�v�Z�P�� <BR>
     * <BR>
     * (2) �����̊g�����M�����I�u�W�F�N�g�D�ʉ݃R�[�h<BR>
     * �I��T0�iWEB3MFOrderQuantityType.EN�j�j<BR>
     * <BR>
     * �@@�@@ �O�݌��Ă̊T�Z����������Z�o����B�i�����_3�ʈȉ��؂�̂āj<BR>
     * <BR>
     * �@@�@@�y���t�z�@@�����敪�����t�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�T�Z��������i�O�݁j �� �������� �~ ���t����z �^ ����z�v�Z�P��<BR>
     * �@@�@@�@@�T�Z������� �� �T�Z��������i�O�݁j<BR>
     * <BR>
     * �@@�@@�@@�@@���������@@�@@�@@�@@�@@�@@�F�����̒�������<BR>
     * �@@�@@�@@�@@���t����z�@@�@@�@@�F�g�����M�����̔��t����z<BR>
     * �@@�@@�@@�@@����z�v�Z�P�ʁF�g�����M�����̊���z�v�Z�P��<BR>
     * <BR>
     * <BR>
     * �@@�@@�y���^�抷�z �����敪����� or �抷�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�T�Z��������i�O�݁j �� �������� �~ ��񉿊z �^ ����z�v�Z�P��<BR>
     * �@@�@@�@@�T�Z������� �� �T�Z��������i�O�݁j<BR>
     * <BR>
     * �@@�@@�@@�@@���������@@�@@�@@�@@�@@�@@�F�����̒�������<BR>
     * �@@�@@�@@�@@��񉿊z�@@�@@�@@�@@�@@�@@�F�g�����M�����̉�񉿊z<BR>
     * �@@�@@�@@�@@����z�v�Z�P�ʁF�g�����M�����̊���z�v�Z�P��<BR>
     * �@@�@@�y��W�z�@@�����敪����W�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�T�Z��������i�O�݁j �� �������� �~ ��W���z �^ ����z�v�Z�P�� <BR>
     * �@@�@@�@@�T�Z������� �� �T�Z��������i�O�݁j <BR>
     * <BR>
     * �@@�@@�@@�@@���������@@�@@�@@�@@�@@�@@�F�����̒������� <BR>
     * �@@�@@�@@�@@��W���z�@@�@@�@@�@@�@@�@@�F�g�����M�����̕�W���z <BR>
     * �@@�@@�@@�@@����z�v�Z�P�ʁF�g�����M�����̊���z�v�Z�P�� <BR>
     * <BR>
     * �@@�A �����̌��ϕ��@@���~�݂̏ꍇ�ɁA�~�݌��Ă̊T�Z����������Z�o����B<BR>
     * �i�����_�ȉ��؂�̂āj<BR>
     * <BR>
     *      �T�Z������� �� �@@�ŎZ�o�����T�Z��������i�O�݁j �~ �בփ��[�g �^ �בփ��[�g�v�Z�P��<BR>
     * <BR>
     * �@@�@@�בփ��[�g�A�v�Z�P�ʁF ����.�g�����M����.get�בփ��[�g()�ɂĈבփ��[�gParams�擾<BR>
     * �@@�@@�@@�E���t�^��W�̏ꍇ�̈בփ��[�g �F TTS<BR>
     * �@@�@@�@@�E���^�抷�̏ꍇ�̈בփ��[�g �F TTB<BR>
     * <BR>
     * @@param l_strProcessDiv - �����敪<BR>
     * <BR>
     * �P�F���t<BR>
     * �Q�F���<BR>
     * �R�F�抷<BR>
     * �S�F��W <BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * @@param l_strSettleDiv - ���ϕ��@@<BR>
     * <BR>
     * �P�F�~��<BR>
     * �Q�F�O��<BR>
     * @@param l_mutualFundProduct - (�g�����M����)<BR>
     * @@param l_estimatedPrice - �T�Z��n����I�u�W�F�N�g<BR>
     * @@roseuid 40ADD3790399
     */
    public void calcEstimatedTradeAmount(
        String l_strProcessDiv, 
        double l_dblOrderQuantity, 
        String l_strSettleDiv, 
        WEB3MutualFundProduct l_web3MutualFundProduct, 
        WEB3MutualFundEstimatedPrice l_estimatedPrice) throws WEB3BaseException        
    {
        final String STR_METHOD_NAME = "calcEstimatedTradeAmount(" +
            "String, double, String, WEB3MutualFundProduct, " +
            "WEB3MutualFundEstimatedPrice)";
        log.entering(STR_METHOD_NAME);
        
        if (l_web3MutualFundProduct == null || l_estimatedPrice == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�T�Z�������
        BigDecimal l_bdEstimatedTradeAmount = null;

        //��������
        BigDecimal l_bdOrderQuantity = new BigDecimal(Double.toString(l_dblOrderQuantity));

        //����i�A����i�v�Z�P��
        BigDecimal l_bdConstantValue = null;
        BigDecimal l_bdConstantValueCalcUnit =
        	new BigDecimal(Double.toString(l_web3MutualFundProduct.getConstantValueCalcUnit()));
        
		//�����敪�����t�̏ꍇ
		if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv))
		{
			log.debug("�����敪�����t");

			//����i �� ���t����i
			l_bdConstantValue = new BigDecimal(Double.toString(l_web3MutualFundProduct.getConstantValue()));
		}
		//�����敪����� or �抷�̏ꍇ
		if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) || 
			WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
		{
			log.debug("�����敪�����or�抷");

			//����i �� ��񉿊i
			l_bdConstantValue = new BigDecimal(Double.toString(l_web3MutualFundProduct.getSellValue()));
		}
            
		//�����敪����W�̏ꍇ
		if (WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
		{
			log.debug("�����敪����W");

			//����i �� ��W���i
			l_bdConstantValue = new BigDecimal(Double.toString(l_web3MutualFundProduct.getRecruitConstantValue()));
		}

		log.debug("����i = " + l_bdConstantValue);
		log.debug("����i�v�Z�P�� = " + l_bdConstantValueCalcUnit);

        //(1)�����̊g�����M�����I�u�W�F�N�g�D�ʉ݃R�[�h = T0 �̏ꍇ
        if (WEB3MFOrderQuantityType.EN.equals(
            l_web3MutualFundProduct.getCurrencyCode()))
        {
			log.debug("�����̊g�����M�����I�u�W�F�N�g�D�ʉ݃R�[�h = T0 �̏ꍇ");

			//�T�Z������� �� �������� �~ ����z �^ ����z�v�Z�P��
			l_bdEstimatedTradeAmount =
				l_bdOrderQuantity.multiply(l_bdConstantValue).divide(
					l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_FLOOR);

			log.debug("�T�Z������� = " + l_bdEstimatedTradeAmount);

			l_estimatedPrice.setEstimatedTradeAmount(l_bdEstimatedTradeAmount.doubleValue());
        }
        
        //�T�Z�������(�O�݁j
		BigDecimal l_bdForeignEstimatedTradeAmount = null;
        
        //(2)�����̊g�����M�����I�u�W�F�N�g�D�ʉ݃R�[�h != T0 �̏ꍇ
        if (!WEB3MFOrderQuantityType.EN.equals(
            l_web3MutualFundProduct.getCurrencyCode()))
        {
			log.debug("�����̊g�����M�����I�u�W�F�N�g�D�ʉ݃R�[�h != T0 �̏ꍇ");

            //�@@ �O�݌��Ă̊T�Z����������Z�o����
			l_bdForeignEstimatedTradeAmount =
				l_bdOrderQuantity.multiply(l_bdConstantValue).divide(
						l_bdConstantValueCalcUnit,2,BigDecimal.ROUND_FLOOR);

			log.debug("�T�Z�������(�O��) = " + l_bdForeignEstimatedTradeAmount);

			l_estimatedPrice.setForeignCurrencyEstimatedTradeAmount(
				l_bdForeignEstimatedTradeAmount.doubleValue());
			l_estimatedPrice.setEstimatedTradeAmount(
				l_bdForeignEstimatedTradeAmount.doubleValue());
                
            //�A �����̌��ϕ��@@���~�݂̏ꍇ�ɁA�~�݌��Ă̊T�Z����������Z�o����
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
            {
            	//�בփ��[�g�A�v�Z�P��
            	BigDecimal l_bdExchangeRate = null;
				BigDecimal l_bdExchangeRateCalcUnit = null;

				// ����.�g�����M����.get�בփ��[�g()���R�[�����A�בփ��[�g�ƌv�Z�P�ʂ��擾����B
				ExchangeRateParams l_exchangeRateParams = 
					l_web3MutualFundProduct.getExchangeRate();

				//   �����敪 �� ���t�A��W �̏ꍇ �F �בփ��[�g �� TTS
				if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv) ||
					WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
				{
					l_bdExchangeRate =
						new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
				}
				//   �����敪 �� ���A�抷 �̏ꍇ �F �בփ��[�g �� TTB
				else if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) || 
					WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
				{
					l_bdExchangeRate =
						new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
				}
			
				l_bdExchangeRateCalcUnit =
					new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
            	
				log.debug("�בփ��[�g = " + l_bdExchangeRate);
				log.debug("�בփ��[�g�v�Z�P�� = " + l_bdExchangeRateCalcUnit);

                //�T�Z������� �� �@@�ŎZ�o�����T�Z��������i�O�݁j �~ �בփ��[�g�^ 
                //                �בփ��[�g�v�Z�P��
				l_bdEstimatedTradeAmount =
					l_bdForeignEstimatedTradeAmount.multiply(l_bdExchangeRate).divide(
						l_bdExchangeRateCalcUnit,0,BigDecimal.ROUND_FLOOR);

				log.debug("�T�Z�������(�~��) = " + l_bdEstimatedTradeAmount);

                l_estimatedPrice.setEstimatedTradeAmount(l_bdEstimatedTradeAmount.doubleValue());
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc�T�Z��������)<BR>
     * �T�Z���������̌v�Z�����ĊT�Z��n����I�u�W�F�N�g�̊T�Z���������ɃZ�b�g����B<BR>
     * �v�Z���ʂ͏����_�ȉ��؂�̂�<BR>
     * <BR>
     * <BR>
     * <BR>
     * �T�Z�����������Z�o����B<BR>
     * <BR>
     * �@@�@@�y���t�z�@@�����敪�����t�̏ꍇ<BR>
     * <BR>
     * �@@�@@�T�Z�������� �� �������z �~ ����z�v�Z�P�� �^ ���t����z<BR>
     * <BR>
     * �@@�@@�������z�@@�@@�@@�@@�@@�@@�F�����̒�������<BR>
     * �@@�@@����z�v�Z�P�ʁF�g�����M�����̊���z�v�Z�P��<BR>
     * �@@�@@���t����z�@@�@@�@@�F�g�����M�����̔��t����z<BR>
     * <BR>
     * <BR>
     * �@@�@@�y���^�抷�z �����敪����� �܂��� �抷�̏ꍇ<BR>
     * <BR>
     * �@@�@@�T�Z�������� �� �i�������z �~ ����z�v�Z�P�� �j�^ ��񉿊z<BR>
     * <BR>
     * �@@�@@�������z�@@�@@�@@�@@�@@�@@�F�����̒�������<BR>
     * �@@�@@����z�v�Z�P�ʁF�g�����M�����̊���z�v�Z�P��<BR>
     * �@@�@@��񉿊z�@@�@@�@@�@@�@@�@@�F�g�����M�����̉�񉿊z<BR>
     * <BR>
     * �@@�@@�y��W�z�@@�����敪����W�̏ꍇ <BR>
     * <BR>
     * �@@�@@�T�Z�������� �� �������z �~ ����z�v�Z�P�� �^ ��W���z <BR>
     * <BR>
     * �@@�@@�������z�@@�@@�@@�@@�@@�@@�F�����̒������� <BR>
     * �@@�@@����z�v�Z�P�ʁF�g�����M�����̊���z�v�Z�P�� <BR>
     * �@@�@@��W���z�@@�@@�@@�@@�@@�@@�F�g�����M�����̕�W���z <BR>v
     * @@param l_strProcessDiv - �����敪<BR>
     * <BR>
     * �P�F���t<BR>
     * �Q�F���<BR>
     * �R�F�抷<BR>
     * �S�F��W<BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * @@param l_mutualFundProduct - �g�����M����<BR>
     * @@param l_estimatedPrice - �T�Z��n����I�u�W�F�N�g<BR>
     * @@roseuid 40B174A101D3
     */
    public void calcEstimatedQty(
        String l_strProcessDiv, 
        double l_dblOrderQuantity, 
        WEB3MutualFundProduct l_web3MutualFundProduct, 
        WEB3MutualFundEstimatedPrice l_estimatedPrice) throws WEB3BaseException         
    {
        String STR_METHOD_NAME = "calcEstimatedQty()";
        log.entering(STR_METHOD_NAME);
        
        if (l_web3MutualFundProduct == null || l_estimatedPrice == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
       
        //�T�Z��������
		BigDecimal l_bdEstimatedQty = null;

		// �P�j�בփ��[�g���擾����B
		BigDecimal l_bdExchangeRate = new BigDecimal("1");
		BigDecimal l_bdExchangeRateCalcUnit = new BigDecimal("1");
		
		// �P�|�P�j����.�g�����M����.is�O�݌����M()�̖߂�l == true �̏ꍇ
		// �P�|�Q�j����ȊO�i�~�����M�j�̏ꍇ
		// �בփ��[�g�ƌv�Z�P�ʂ����ꂼ��1�Ƃ���B
		if (l_web3MutualFundProduct.isForeignCurencyFund())
		{
			log.debug("����.�g�����M����.is�O�݌����M()�̖߂�l == true �̏ꍇ");
			
			// ����.�g�����M����.get�בփ��[�g()���R�[�����A�בփ��[�g�ƌv�Z�P�ʂ��擾����B
			ExchangeRateParams l_exchangeRateParams = 
				l_web3MutualFundProduct.getExchangeRate();

			//   �����敪 �� ���t�A��W �̏ꍇ �F �בփ��[�g �� TTS
			if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv) ||
				WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
			{
				l_bdExchangeRate =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
			}
			//   �����敪 �� ���A�抷 �̏ꍇ �F �בփ��[�g �� TTB
			else if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) || 
				WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
			{
				l_bdExchangeRate =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
			}
			
			l_bdExchangeRateCalcUnit =
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));

			log.debug("�בփ��[�g = " + l_bdExchangeRate);
			log.debug("�בփ��[�g�v�Z�P�� = " + l_bdExchangeRateCalcUnit);
		}
		
		// �Q�j�T�Z�����������Z�o����B
		
		// ����i�̌���
		BigDecimal l_bdConstantValue = null;
		
		//�y���t�z�����敪�����t�̏ꍇ
		if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv))
		{
			log.debug("�����敪�����t");

			// ���t����z
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_web3MutualFundProduct.getConstantValue()));
		}
		//�y���^�抷�z �����敪����� �܂��� �抷�̏ꍇ
		else if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) || 
			WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
		{
			log.debug("�����敪�����or�抷");

			// ��񉿊z  
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_web3MutualFundProduct.getSellValue()));
		}
		//�y��W�z�@@�����敪����W�̏ꍇ
		else if (WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
		{
			log.debug("�����敪����W");

			// ��W���z   
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_web3MutualFundProduct.getRecruitConstantValue()));
		}        

		log.debug("����i = " + l_bdConstantValue);

		//�T�Z�������� �� �������z �~ ����z�v�Z�P�� �^�i����z �~ �בփ��[�g �� �v�Z�P�ʁj
		//�v�Z���ʂ͏����_�ȉ��؂�̂�
		BigDecimal l_bdOrderQuantity =
			new BigDecimal(Double.toString(l_dblOrderQuantity));
		BigDecimal l_bdConstantValueCalcUnit =
			new BigDecimal(Double.toString(l_web3MutualFundProduct.getConstantValueCalcUnit()));

		log.debug("����i�v�Z�P�� = " + l_bdConstantValueCalcUnit);

		l_bdEstimatedQty =
			l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
				l_bdConstantValue.multiply(l_bdExchangeRate).divide(
					l_bdExchangeRateCalcUnit,10,BigDecimal.ROUND_HALF_UP),
				0,BigDecimal.ROUND_FLOOR);

		log.debug("�T�Z�������� = " + l_bdEstimatedQty);

		//�T�Z��n����I�u�W�F�N�g�̊T�Z���������ɃZ�b�g����
		l_estimatedPrice.setEstimatedQty(l_bdEstimatedQty.doubleValue());

		log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc�����S����)<BR>
     * �T�Z��������A���邢�͊T�Z���������ɑ΂��Ċ����S�������������������S������ԋp����B<BR>
     * <BR>
     * (1) ��Е��X���i�e�[�u����芄���S�����A�����S�������擾����B<BR>
     * �i���XID�Ə��i�R�[�h�Ŏ擾�j<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@���XID�@@�@@�F�⏕�����I�u�W�F�N�g�̕��XID<BR>
     * �@@�@@���i�R�[�h�F20�i���M�j<BR>
     * <BR>
     * (2) �����敪����W�̏ꍇ���g�����M����.��W�萔���敪���u2�F�O�g�v�ȊO�̏ꍇ�A<BR>
     * �����S�����A�����S������1�ɂ���B<BR>
     * <BR>
     * (3) �����w��̏ꍇ�A�����S�������Z�o���āA<BR>
     * �T�Z��n����I�u�W�F�N�g�̊T�Z��n����ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�y���t�^��W�z<BR>
     * <BR>
     * �@@�@@�@@�T�Z��n��� �� �T�Z������� �~ �����S����<BR>
     * <BR>
     * �@@�@@�@@�T�Z��������F�����̐���<BR>
     * �@@�@@�@@�����S�����@@ �F(1)�Ŏ擾���������S����<BR>
     * <BR>
     * <BR>
     * �@@�@@�y���^�抷�z<BR>
     * <BR>
     * �@@�@@�@@�T�Z��n��� �� �T�Z������� �~ �����S����<BR>
     * <BR>
     * �@@�@@�@@�T�Z��������F�����̐���<BR>
     * �@@�@@�@@�����S�����@@ �F(1)�Ŏ擾���������S����<BR>
     * <BR>
     * <BR>
     * <BR>
     * (4) ���z�w��̏ꍇ�A�����S���������Z�o���āA<BR>
     * �T�Z��n����I�u�W�F�N�g�̊T�Z���������ɃZ�b�g����B�����_�ȉ��؂�̂�<BR>
     * <BR>
     * �@@�@@�y���t�^��W�z<BR>
     * <BR>
     * �@@�@@�T�Z�������� �� �T�Z�������� �~ �����S����<BR>
     * <BR>
     * �@@�@@�@@�T�Z���������F�����̐���<BR>
     * �@@�@@�@@�����S�����@@ �F(1)�Ŏ擾���������S����<BR>
     * <BR>
     * �@@�@@�y���^�抷�z<BR>
     * <BR>
     * �@@�@@�T�Z�������� �� �T�Z�������� �~ �����S����<BR>
     * <BR>
     * �@@�@@�@@�T�Z���������F�����̐���<BR>
     * �@@�@@�@@�����S�����@@ �F(1)�Ŏ擾���������S����<BR>
     * @@param l_strProcessDiv - �����敪<BR>
     * <BR>
     * �P�F���t<BR>
     * �Q�F���<BR>
     * �R�F�抷<BR>
     * �S�F��W<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * <BR>
     * �R�F���z�w��<BR>
     * �S�F�����w��<BR>
     * @@param l_dblQuantity - (����)<BR>
     * �����w�莞�͊T�Z�������z�A���z�w�莞�͊T�Z����������ݒ�<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_estimatedPrice - �T�Z��n����I�u�W�F�N�g<BR>
     * @@param l_web3MutualFundProduct - �g�����M�����I�u�W�F�N�g<BR>
     * @@roseuid 40B1ACB60127
     */
    public void calcIncreaseRestraintPriceInRatio(
        String l_strProcessDiv, 
        String l_strDesignateMethod, 
        double l_dblQuantity, 
        SubAccount l_subAccount, 
        WEB3MutualFundEstimatedPrice l_estimatedPrice,
        WEB3MutualFundProduct l_web3MutualFundProduct)
        throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcIncreaseRestraintPriceInRatio(" +
            "String, String, double, SubAccount, WEB3MutualFundEstimatedPrice)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        List l_lisRows = new Vector();
        String l_strWhereClause;
        
        //[��������]<BR>
        //���XID�@@�@@�F�⏕�����I�u�W�F�N�g�̕��XID<BR>
        //���i�R�[�h�F20�i���M�j<BR>
        l_strWhereClause = "branch_id = ? and commission_product_code = ?";
        
        long l_lngBranchId = 0L;						//���XID        
        BigDecimal l_bdPremiumRestraintRate = null;	//�����S����        
        BigDecimal l_bdDiscountRestraintRate = null;	//�����S����

        //get���XID
        l_lngBranchId = l_subAccount.getMainAccount().getBranch().getBranchId();
        
        Object l_bindVars[] = { new Long(l_lngBranchId), 
                                WEB3CommisionProductCodeDef.MUTUAL_FUND};
        try
        {
            //DataQueryException,DataNetworkException
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                InstBranchProductRow.TYPE,
                    l_strWhereClause,
                    l_bindVars);
                        
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.debug("__�e�[�u���ɊY������f�[�^������܂���__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                InstBranchProductParams l_instBranchProductParams = 
                    (InstBranchProductParams)l_lisRows.get(0);                               
                //(1) ��Е��X���i�e�[�u����芄���S�������擾����
                l_bdPremiumRestraintRate = 
                    new BigDecimal(Double.toString(l_instBranchProductParams.getPremiumRestraintRate()));
                //(1) ��Е��X���i�e�[�u����芄���S�������擾����
                l_bdDiscountRestraintRate = 
                    new BigDecimal(Double.toString(l_instBranchProductParams.getDiscountRestraintRate()));
                //(2) �����敪����W�̏ꍇ���g�����M����.��W�萔���敪���u2�F�O�g�v�ȊO�̏ꍇ�A
                //�����S�����A�����S������1�ɂ���B
                if (WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv)
                    && !(WEB3RecruitCommissionDivDef.WITHOUT_THE_LIMIT.equals(
                        l_web3MutualFundProduct.getRecruitCommissionDiv())))
                {
                	l_bdPremiumRestraintRate  = new BigDecimal(1);
                	l_bdDiscountRestraintRate = new BigDecimal(1);
                }
            }           
            
			BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_dblQuantity));
			
            //(3) �����w��̏ꍇ
            if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strDesignateMethod))
            {
				//�T�Z��n���
				BigDecimal l_bdEstimatedPrice = null;

                //�y���t�^��W�z
                if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv) || 
                    WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
                {
					log.debug("�S���� = " + l_bdPremiumRestraintRate);

                    //�T�Z��n��� �� �T�Z������� �~ �����S����
                    l_bdEstimatedPrice = l_bdQuantity.multiply(l_bdPremiumRestraintRate);
                }
                //�y���^�抷�z
                else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv) || 
                    WEB3MFProcessDivDef.SWITCHING.equals(l_strProcessDiv))
                {
					log.debug("�S���� = " + l_bdDiscountRestraintRate);

                    //�T�Z��n��� �� �T�Z������� �~ �����S���� 
					l_bdEstimatedPrice = l_bdQuantity.multiply(l_bdDiscountRestraintRate);
                }

				l_bdEstimatedPrice = l_bdEstimatedPrice.setScale(0,BigDecimal.ROUND_FLOOR);

				log.debug("�T�Z��n��� = " + l_bdEstimatedPrice);

				//�T�Z��n����I�u�W�F�N�g�̊T�Z��n����ɃZ�b�g����
				l_estimatedPrice.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());                       
            }
            //(4) ���z�w��̏ꍇ
            else if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod))
            {
				//�T�Z��������
				BigDecimal l_bdEstimatedQty = null;

                //�y���t�^��W�z
                if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv) ||
                    WEB3MFProcessDivDef.RECRUIT.equals(l_strProcessDiv))
                {
					log.debug("�S���� = " + l_bdDiscountRestraintRate);

                    //�T�Z�������� �� �T�Z�������� �~ �����S����(�����_�ȉ��؂�̂�)
					l_bdEstimatedQty = l_bdQuantity.multiply(l_bdDiscountRestraintRate);
                }
                //�y���^�抷�z
                else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv) || 
                    WEB3MFProcessDivDef.SWITCHING.equals(l_strProcessDiv))
                {
					log.debug("�S���� = " + l_bdPremiumRestraintRate);

                    //�T�Z�������� �� �T�Z�������� �~ �����S����(�����_�ȉ��؂�̂�)
					l_bdEstimatedQty = l_bdQuantity.multiply(l_bdPremiumRestraintRate);
                }

				l_bdEstimatedQty = l_bdEstimatedQty.setScale(0,BigDecimal.ROUND_FLOOR);

				log.debug("�T�Z�������� = " + l_bdEstimatedQty);

				//�T�Z��n����I�u�W�F�N�g�̊T�Z���������ɃZ�b�g����
				l_estimatedPrice.setEstimatedQty(l_bdEstimatedQty.doubleValue());
            }

        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                 
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc�T�Z��n���)
     * �T�Z��n����̌v�Z���s���A�v�Z���ʂ��T�Z��n����I�u�W�F�N�g�ɃZ�b�g���ԋp����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_mainAccount - �ڋq
     * @@param l_mfProduct - ����
     * @@param l_mfSwtProduct - �����i�抷��j
     * @@param l_strDealDiv - ����敪
     * @@param l_strOrderChannel - �����`���l��
     * @@param l_strDesignDiv - �w��敪
     * @@param l_dblOrderQuantity - ��������
     * @@param l_strRequestDiv - �����敪
     * @@param l_strAccountDiv - �����敪
     * @@param l_strSettleDiv - ���ϋ敪
     * @@param l_datBizDate - ������
     * @@return WEB3MutualFundEstimatedPrice
     * @@throws WEB3BaseException
     * @@roseuid 40A3495A00B7
     */
    public WEB3MutualFundEstimatedPrice calcEstimatedPrice(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_mfSwtProduct, 
        String l_strDealDiv, 
        String l_strOrderChannel, 
        String l_strDesignDiv, 
        double l_dblOrderQuantity, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        String l_strSettleDiv,
        Date l_datBizDate)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcEstimatedPrice(WEB3GentradeMainAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundProduct, String, String, " +
            "String, double, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //�@@   �������ʂ��擾����B
        BigDecimal l_bdCalcOrderQuantity = null;
		BigDecimal l_bdOrderQuantity = new BigDecimal(Double.toString(l_dblOrderQuantity));
        
        //1)  ����.�w��敪 �� �g���z�h and ����.���ϋ敪 �� �g�O�݁h �̏ꍇ
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv) && 
            WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
        {
            //(*) �בփ��[�g�F ����.����.get�בփ��[�g()�ɂĎ擾�B
            ExchangeRateParams l_exchangeRateParams = 
                l_mfProduct.getExchangeRate();
            
            BigDecimal l_bdExchangeRate = null;
			BigDecimal l_bdExchangeCalcUnit = 
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
			
            //(a) ����.����敪 �� �g���t�h or �g��W�h �̏ꍇ     
            if (WEB3MFDealDivDef.BUY.equals(l_strDealDiv) || 
                WEB3MFDealDivDef.RECRUIT.equals(l_strDealDiv))
            {                
				l_bdExchangeRate = new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
            }
            //(b) ����.����敪 �� �g���h or �g�抷�h �̏ꍇ
            else if (WEB3MFDealDivDef.SELL.equals(l_strDealDiv) || 
                WEB3MFDealDivDef.SWITCHING.equals(l_strDealDiv))
            {
				l_bdExchangeRate = new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
            }
			//�������� �� ����.�������� �~ �בփ��[�g �� �בփ��[�g.�בփ��[�g�v�Z�P��
			l_bdCalcOrderQuantity = l_bdOrderQuantity.multiply(l_bdExchangeRate).divide(
					l_bdExchangeCalcUnit,BigDecimal.ROUND_FLOOR);
        }
        //2)  ����ȊO�̏ꍇ
        else
        {
            //�������� �� ����.��������
            l_bdCalcOrderQuantity = l_bdOrderQuantity;
        }
        
        log.debug(">>>>> �P�j�������� = " + l_bdCalcOrderQuantity);
        
        //�A  ���M��Еʎ萔�����擾����B
        //���M��Еʎ萔���̃C���X�^���X�𐶐�����B

        //[�R���X�g���N�^�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�ڋq.getInstitution().getInstitutionCode() �̖߂�l
        //�����R�[�h�F �i�ȉ��̂Ƃ���j
        //����.����敪 �� �g�抷�h �̏ꍇ�A����.�����i�抷��j.getProductCode() �̖߂�l
        //����.����敪 �� �g�抷�h �̏ꍇ�A����.����.getProductCode() �̖߂�l
        //����敪�F ����.����敪
        //�����`���l���F ����.�����`���l��
        //�������F ����.������
        
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strProductCode = null;
        
        if (WEB3MFDealDivDef.SWITCHING.equals(l_strDealDiv))
        {
            l_strProductCode = l_mfSwtProduct.getProductCode();
        }
        else
        {
            l_strProductCode = l_mfProduct.getProductCode();
        }
      
        WEB3MutualFundInstCommission l_mfInstCommission = 
            new WEB3MutualFundInstCommission(
                l_strInstitutionCode, 
                l_strProductCode, 
                l_strDealDiv, 
                l_strOrderChannel, 
                l_datBizDate);
        
		log.debug(">>>>> �Q�j��Еʎ萔�� = " + l_mfInstCommission);
        
        //�B �����l���Z�o����B
        //�萔���P���A�������肷�邽�߂̌����l���Z�o����B
        //this.calc�����l() ���R�[������B

        //[this.calc�����l()�ɃZ�b�g�������]
        //�ڋq�F ����.�ڋq
        //�����F ����.����
        //��Еʎ萔���F �A�Ŏ擾������Еʎ萔��
        //�������ʁF �@@�Ŏ擾������������
        //�w��敪�F ����.�w��敪
        //�����敪�F ����.�����敪
        //�����敪�F ����.�����敪
        //  �������F ����.������
        BigDecimal l_bdSearchValue = 
            this.calcSearchValue(
                l_mainAccount, 
                l_mfProduct, 
                l_mfInstCommission, 
                l_bdCalcOrderQuantity, 
                l_strDesignDiv, 
                l_strRequestDiv, 
                l_strAccountDiv, 
                l_datBizDate);
        
		log.debug(">>>>> �R�j�����l = " + l_bdSearchValue);
        
        //�C �萔���P���A�����擾����B

        //��Еʎ萔���Ŏ����ʔ͈́i01�j����i10�j�̒����猟���l�����������ɓ��Ă͂܂���̂�I�����A
        //���̐��ʔ͈͂ƃ����N����萔���P���A�����擾����B
        //��Еʎ萔��.get�萔���P���A��() ���R�[������B

        //[��Еʎ萔��.get�萔���P���A��()�ɃZ�b�g�������]
        //�ڋq�F ����.�ڋq
        //�����F ����.����
		//�抷��̖����F ����.����(�抷��)
        //�w��敪�F ����.�w��敪
        //�����l�F �B�ŎZ�o���������l
        //�������F ����.������
        BigDecimal l_bdCommisionPriceRate = 
            l_mfInstCommission.getCommisionPriceRate(
                l_mainAccount, 
                l_mfProduct, 
                l_mfSwtProduct, 
                l_strDesignDiv, 
                l_bdSearchValue, 
                l_datBizDate);
        
		log.debug(">>>>> �S�j�萔���P���A�� = " + l_bdCommisionPriceRate);
        
		//�D �T�Z�����������Z�o����B

		//�T�Z�����������Z�o����B
		//this.calc�T�Z��������() ���R�[������B

		//[this.calc�T�Z��������()�ɃZ�b�g�������]
		//�ڋq�F ����.�ڋq
		//�����F ����.����
		//��Еʎ萔���F �A�Ŏ擾������Еʎ萔��
		//�w��敪�F ����.�w��敪
		//�����敪�F ����.�����敪
		//�����敪�F ����.�����敪
		//�������ʁF �@@�Ŏ擾������������
		//�萔���P���A���F �C�Ŏ擾�����萔���P���A��
		//�������F ����.������
		BigDecimal l_bdEstimatedQty = 
			this.calcEstimatedQty(
				l_mainAccount, 
				l_mfProduct, 
				l_mfInstCommission, 
				l_strDesignDiv,
				l_strRequestDiv, 
				l_strAccountDiv, 
				l_bdCalcOrderQuantity, 
				l_bdCommisionPriceRate, 
				l_datBizDate);

		log.debug(">>>>> �T�j�T�Z�������� = " + l_bdEstimatedQty);
        
        //�E �T�Z����������Z�o����B

        //this.calc�T�Z�������() ���R�[������B
        //[this.calc�T�Z�������()�ɃZ�b�g�������]
        //�����F ����.����
        //��Еʎ萔���F �A�Ŏ擾������Еʎ萔��
		//�T�Z���������F �D�ŎZ�o�����T�Z��������
        
        BigDecimal l_bdEstimatedTradeAmount = 
            this.calcEstimatedTradeAmount(
                l_mfProduct, 
                l_mfInstCommission, 
				l_bdEstimatedQty);
        
		log.debug(">>>>> �U�j�T�Z������� = " + l_bdEstimatedTradeAmount);
        
        //�F �萔�����Z�o����B

        //this.calc�萔��() ���R�[������B
        //[this.calc�萔��()�ɃZ�b�g�������]
        //�ڋq�F ����.�ڋq
        //��Еʎ萔���F �A�Ŏ擾������Еʎ萔��
		//�����F ����.����
		//�抷��̖����F ����.����(�抷��)
		//�T�Z���������F �D�ŎZ�o�����T�Z��������
        //�T�Z��������F �E�ŎZ�o�����T�Z�������
        //�萔���P���A���F �C�Ŏ擾�����萔���P���A��
        
        BigDecimal l_bdCommission = 
            this.calcCommission(
                l_mainAccount, 
                l_mfInstCommission, 
                l_mfProduct, 
                l_mfSwtProduct, 
				l_bdEstimatedQty, 
                l_bdEstimatedTradeAmount, 
                l_bdCommisionPriceRate);
        
		log.debug(">>>>> �V�j�萔�� = " + l_bdCommission);
        
        //�G �萔������ł��Z�o����B

        //this.calc�萔�������() ���R�[������B
        //[this.calc�萔�������()�ɃZ�b�g�������]
        //����.�ڋq
        //�萔���F �F�ŎZ�o�����萔��
        //�������F ����.������
        
        BigDecimal l_bdCommissionTax = 
            this.calcCommissionTax(
                l_mainAccount, 
                l_bdCommission, 
                l_datBizDate);
        
		log.debug(">>>>> �W�j�萔������� = " + l_bdCommissionTax);
        
        //�H �����ł��Z�o����B   
        
        //this.calc������() ���R�[������B
        //[this.calc������()�ɃZ�b�g�������]
        //�ڋq�F ����.�ڋq
        //�����F ����.����
        //��Еʎ萔���F �A�Ŏ擾������Еʎ萔��
        //�����敪�F ����.�����敪
        //�����敪�F ����.�����敪
		//�T�Z���������F �D�ŎZ�o�����T�Z��������
        //�������F ����.������

        //������.����敪���g���h or �g�抷�h and �������M
        //�i����.����.is�O�����M()��false and ����.����.isFWF()��false�j�̏ꍇ�̂݁A���s�B
        //��L�̏����ɊY�����Ȃ��ꍇ�́A������ �� 0 �Ƃ���B        
        
        BigDecimal l_bdIncomeTax = this.calcIncomeTax(
            l_mainAccount, 
            l_mfProduct, 
            l_mfInstCommission,
            l_strRequestDiv, 
            l_strAccountDiv, 
			l_bdEstimatedQty, 
            l_datBizDate);
        
		log.debug(">>>>> �X�j������ = " + l_bdIncomeTax);
        
        //�I �n���ł��Z�o����B

        //�n���ł��Z�o����B
        //this.calc�n����() ���R�[������B

        //[this.calc�n����()�ɃZ�b�g�������]
        //�ڋq�F ����.�ڋq
        //�����F ����.����
        //��Еʎ萔���F �A�Ŏ擾������Еʎ萔��
        //�����敪�F ����.�����敪
        //�����敪�F ����.�����敪
		//�T�Z���������F �D�ŎZ�o�����T�Z��������
        //�������F ����.������

        //������.����敪 �� �g���h or �g�抷�h and 
        //  �������M�i����.����.is�O�����M() �� false and ����.����.isFWF()��false�j�̏ꍇ�̂݁A���s�B
        //  ��L�̏����ɊY�����Ȃ��ꍇ�́A�n���� �� 0 �Ƃ���B
        
        BigDecimal l_bdLocalTax = this.calcLocalTax(
            l_mainAccount, 
            l_mfProduct, 
            l_mfInstCommission,
            l_strRequestDiv, 
            l_strAccountDiv, 
			l_bdEstimatedQty, 
            l_datBizDate);        
        
		log.debug(">>>>> �P�O�j�n���� = " + l_bdLocalTax);
        
        //�J �T�Z��n������Z�o����B

        //�T�Z��n������Z�o����B
        //this.calc�T�Z��n���() ���R�[������B

        //[this.calc�T�Z��n���()�ɃZ�b�g�������]
        //��Еʎ萔���F �A�Ŏ擾������Еʎ萔��
        //�w��敪�F ����.�w��敪
        //�������ʁF �@@�Ŏ擾������������
        //�T�Z��������F �E�ŎZ�o�����T�Z�������
        //�萔���F �F�ŎZ�o�����萔��
        //����ŁF �G�ŎZ�o�����萔�������
        //�����ŁF �H�ŎZ�o����������
        //�n���ŁF �I�ŎZ�o�����n����
        BigDecimal l_bdEstimatedPrice = 
            this.calcEstimatedPrice(
                l_mfInstCommission, 
                l_strDesignDiv,
                l_bdCalcOrderQuantity, 
                l_bdEstimatedTradeAmount, 
                l_bdCommission, 
                l_bdCommissionTax, 
                l_bdIncomeTax, 
                l_bdLocalTax);
        
		log.debug(">>>>> �P�P�j�T�Z��n��� = " + l_bdEstimatedPrice);
        
        //�K �T�Z��n����I�u�W�F�N�g�𐶐����A�v���p�e�B�Ɍv�Z���ʂ��Z�b�g����B
        
        //1)  �T�Z��n����̃C���X�^���X�𐶐�����B
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = 
            new WEB3MutualFundEstimatedPrice();
        
        //2)  ��L�ɂĎZ�o�������ʂ��A�T�Z��n����̃v���p�e�B�ɃZ�b�g����B
        l_mfEstimatedPrice.setCommission(l_bdCommission.doubleValue());
        l_mfEstimatedPrice.setCommissionTax(l_bdCommissionTax.doubleValue());
        l_mfEstimatedPrice.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());
        l_mfEstimatedPrice.setEstimatedQty(l_bdEstimatedQty.doubleValue());
        l_mfEstimatedPrice.setEstimatedTradeAmount(l_bdEstimatedTradeAmount.doubleValue());
        l_mfEstimatedPrice.setIncomeTax(l_bdIncomeTax.doubleValue());
        l_mfEstimatedPrice.setLocalTax(l_bdLocalTax.doubleValue());
        
        //3)  ����.����.is�O�݌����M() �� true �̏ꍇ�͈ȉ��̌v�Z���s���A�v���p�e�B�ɒl���Z�b�g����B
        if (l_mfProduct.isForeignCurencyFund())
        {
            log.debug("����.����.is�O�݌����M() �� true �̏ꍇ");
            
            BigDecimal l_bdForergnEstTradeAmount = null;
			
			//���בփ��[�g�F ����.����.get�בփ��[�g�ɂĎ擾�B
			ExchangeRateParams l_exchangeRateParams = 
				l_mfProduct.getExchangeRate();
            
            BigDecimal l_bdExchangeRate = null;
			BigDecimal l_bdExchangeCalcUnit = 
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
            
            //(a) ����.����敪 �� �g���t�h or �g��W�h �̏ꍇ
            if (WEB3MFDealDivDef.BUY.equals(l_strDealDiv) || 
                WEB3MFDealDivDef.RECRUIT.equals(l_strDealDiv))
            {
                log.debug("(a) ����.����敪 �� �g���t�h or �g��W�h �̏ꍇ");
				l_bdExchangeRate =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
            }
            //(b) ����.����敪 �� �g���h or �g�抷�h �̏ꍇ
            else if (WEB3MFDealDivDef.SELL.equals(l_strDealDiv) || 
                        WEB3MFDealDivDef.SWITCHING.equals(l_strDealDiv))
            {
                log.debug("(b) ����.����敪 �� �g���h or �g�抷�h �̏ꍇ");
				l_bdExchangeRate =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
            }

			//�T�Z��������i�O�݁j �� �T�Z������� �~ �בփ��[�g�i���j.�בփ��[�g�v�Z�P�� �� �בփ��[�g
			//�������_��3�ʈȉ��́A�؂�̂āB
			l_bdForergnEstTradeAmount = 
				l_bdEstimatedTradeAmount.multiply(l_bdExchangeCalcUnit).divide(
						l_bdExchangeRate,2,BigDecimal.ROUND_FLOOR);

			l_mfEstimatedPrice.setForeignCurrencyEstimatedTradeAmount(
				l_bdForergnEstTradeAmount.doubleValue());
        }
        
        //����.���ϋ敪 �� �g�O�݁h �̏ꍇ�B
		if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
		{        
			//4)    ����.�w��敪 �� �g���z�h �̏ꍇ�͈ȉ��̂Ƃ���ɁA�v���p�e�B�ɒl���Z�b�g����B
			if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
			{        
				//�T�Z��n��� �� ����.��������
				l_mfEstimatedPrice.setEstimatedPrice(l_dblOrderQuantity);
			}
			//5)    ����.�w��敪 �� �g�����h �̏ꍇ�͈ȉ��̌v�Z���s���A�v���p�e�B�ɒl���Z�b�g����B
			else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
			{        
				//�T�Z��n���
				BigDecimal l_bdEstPrice = null;
				
				//���בփ��[�g�F ����.����.get�בփ��[�g�ɂĎ擾�B
				ExchangeRateParams l_exchangeRateParams = 
					l_mfProduct.getExchangeRate();
	
				BigDecimal l_bdExchangeRate = null;
				BigDecimal l_bdExchangeCalcUnit = 
					new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
	            
				//(a) ����.����敪 �� �g���t�h or �g��W�h �̏ꍇ
				if (WEB3MFDealDivDef.BUY.equals(l_strDealDiv) || 
					WEB3MFDealDivDef.RECRUIT.equals(l_strDealDiv))
				{
					l_bdExchangeRate =
						new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
				}
				//(b) ����.����敪 �� �g���h or �g�抷�h �̏ꍇ
				else if (WEB3MFDealDivDef.SELL.equals(l_strDealDiv) || 
							WEB3MFDealDivDef.SWITCHING.equals(l_strDealDiv))
				{
					l_bdExchangeRate =
						new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
				}
	
				//�T�Z��n��� �� �T�Z��n����i�J�̖߂�l�j �~ 
				//      �בփ��[�g�i���j.�בփ��[�g�v�Z�P�� �� �בփ��[�g
				//�������_��3�ʈȉ��́A�؂�̂āB
				l_bdEstPrice = 
					l_bdEstimatedPrice.multiply(l_bdExchangeCalcUnit).divide(
							l_bdExchangeRate,2,BigDecimal.ROUND_FLOOR);
	
				l_mfEstimatedPrice.setEstimatedPrice(l_bdEstPrice.doubleValue());
			}
		}
        
        log.exiting(STR_METHOD_NAME);
        return l_mfEstimatedPrice;
    }
    
    /**
     * (calc�����l)
     * �����l���Z�o����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB <BR>
     * @@param l_maiAccount - �ڋq
     * @@param l_mfProduct - ����
     * @@param l_mfInstCommission - ��Еʎ萔��
     * @@param l_bdOrderQuantity - ��������
     * @@param l_strDesignDiv - �w��敪
     * @@param l_strRequestDiv - �����敪
     * @@param l_strAccountDiv - �����敪
     * @@param l_datBizDate - ������
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcSearchValue(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        BigDecimal l_bdOrderQuantity, 
        String l_strDesignDiv, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        Date l_datBizDate)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcSearchValue(WEB3GentradeMainAccount," +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, " +
            "BigDecimal, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        BigDecimal l_bdSearchValue = null;
		
        //is�O�����M
        boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
        //isFWF
        boolean l_blnIsFWF = l_mfProduct.isFWF();
        //is�O�݌����M
        boolean l_blnIsForeignCurencyFund = l_mfProduct.isForeignCurencyFund();
        
		//(*1)����i
		BigDecimal l_bdConstantValue = null;
		BigDecimal l_bdConstantValueCalcUnit =
			new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit()));

		//���t�̏ꍇ�͔��t����i�B
		if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getConstantValue()));
		}
		//��W�̏ꍇ�͕�W���i�B
		else if (WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getRecruitConstantValue()));
		}
		//���E�抷�̏ꍇ�͉�񉿊i�B
		else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
				WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getSellValue()));
		}
		log.debug("(*1)����i = " + l_bdConstantValue);
                        
		//(*2)�בփ��[�g�F ����.����.get�בփ��[�g()�ɂĎ擾�B
		//�擾�ł��Ȃ��ꍇ�i����.����.is�O�݌����M()��false�̏ꍇ�j�́A
		//TTB�ATTS�A�בփ��[�g�v�Z�P�ʂƂ�1�Ƃ��Čv�Z�B
		BigDecimal l_bdTTB = new BigDecimal("1");
		BigDecimal l_bdTTS = new BigDecimal("1");
		BigDecimal l_bdCalcUnit = new BigDecimal("1");

		if (l_blnIsForeignCurencyFund)
		{
			ExchangeRateParams l_exchangeRateParams = 
				l_mfProduct.getExchangeRate();       

			l_bdTTB =
				new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
			l_bdTTS =
				new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
			l_bdCalcUnit =
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
		}

        //�@@ ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h or �g���x���ʖ��̎萔�����h �̏ꍇ

        if (WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(
                l_mfInstCommission.getCommisionDiv()) || 
            WEB3MFCommissionDivDef.UNIT_ONE_COMMISSION_RATE.equals(
                l_mfInstCommission.getCommisionDiv()))
        {        
            log.debug("�@@ ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h " +
                    "or �g���x���ʖ��̎萔�����h �̏ꍇ");
            
            //1)  ����.�w��敪 �� �g�����w��h �̏ꍇ
            if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
            {
                log.debug("1)����.�w��敪 �� �g�����w��h �̏ꍇ");
                //�����l �� ����.��������
                l_bdSearchValue = l_bdOrderQuantity;
            }    
            //2)  ����.�w��敪 �� �g���z�w��h �̏ꍇ
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
            {                
                log.debug("2)����.�w��敪 �� �g���z�w��h �̏ꍇ");
                
                //(a) ����.��Еʎ萔��.����敪 �� �g���t�h or �g��W�h �̏ꍇ
                if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
                    WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
                {
                    log.debug("(a) ����.��Еʎ萔��.����敪 �� �g���t�h or �g��W�h �̏ꍇ");
                    
                    //(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
                    if (l_blnIsForeignFund || l_blnIsFWF)
                    {
                        log.debug("(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                        
                        //�����l �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
                        //�i ����.����.����i(*1) �~ �בփ��[�g(*2).TTS �� �בփ��[�g.�בփ��[�g�v�Z�P�� �j
						//�������_�ȉ��؂�̂āB
						l_bdSearchValue =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTS).divide(l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP),
								0,BigDecimal.ROUND_FLOOR);
                    }                    
                    //(ii) ��L�ȊO�̏ꍇ
                    else
                    {                   
                        log.debug("��L�ȊO�̏ꍇ");
                        
                        //�����l �� ����.�������� �~ ����.����.����i�v�Z�P�� �� ����.����.����i(*)
						//�������_�ȉ��؂�̂āB
						l_bdSearchValue =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue,0,BigDecimal.ROUND_FLOOR);
                    }
                }
                //(b) ����.��Еʎ萔��.����敪 �� �g���h or �g�抷�h �̏ꍇ
                else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
                        WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
                {
                    log.debug("(b) ����.��Еʎ萔��.����敪 �� �g���h or �g�抷�h �̏ꍇ");
                    
                    //(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
                    if (l_blnIsForeignFund || l_blnIsFWF)
                    {    
                        log.debug("����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                        
                        //�����l �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
                        //�i ����.����.��񉿊i �~ �בփ��[�g(*).TTB �� �בփ��[�g.�בփ��[�g�v�Z�P�� �j                        
						//�������_�ȉ��؂�̂āB
						l_bdSearchValue =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTB).divide(l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP),
								0,BigDecimal.ROUND_FLOOR);
                    }
                    //(ii)  ��L�ȊO�̏ꍇ
                    else
                    {                        
                        log.debug("(ii) ��L�ȊO�̏ꍇ");
                        
                        //(*1) calc�v�Z�P�ʓ����菊���Ń��\�b�h�̎葱���Q�ƁB
                        BigDecimal l_bdUnitIncomeTax = 
                            this.calcUnitIncomeTax(
                                l_mainAccount, 
                                l_mfProduct, 
                                l_mfInstCommission, 
                                l_strRequestDiv, 
                                l_strAccountDiv, 
                                l_datBizDate);
                        log.debug("�v�Z�P�ʓ����菊���Ŋz(*1) = " + l_bdUnitIncomeTax);
                        
						//(*2) calc�v�Z�P�ʓ�����n���Ń��\�b�h�̎葱���Q�ƁB
						BigDecimal l_bdUnitLocalTax = 
                            this.calcUnitLocalTax(
                                l_mainAccount, 
                                l_mfProduct, 
                                l_mfInstCommission, 
                                l_strRequestDiv, 
                                l_strAccountDiv, 
                                l_datBizDate);
                        log.debug("�v�Z�P�ʓ�����n���Ŋz(*2) = " + l_bdUnitLocalTax);
                        
                        //�����l �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
                        //�i ����.����.��񉿊i �| �v�Z�P�ʓ����菊���Ŋz(*1) �| �v�Z�P�ʓ�����n���Ŋz(*2) �j
						//�������_�ȉ��؂�̂āB
                        l_bdSearchValue =
                        	l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.subtract(l_bdUnitIncomeTax).subtract(l_bdUnitLocalTax),
                        		0,BigDecimal.ROUND_FLOOR);
                    }
                }
            }
        }
        //�A   ����.��Еʎ萔��.�萔���敪 �� �g��n����i�萔�����j�h or �g��������i�萔�����j�h �̏ꍇ
        if (WEB3MFCommissionDivDef.PAYMENT_PRICE_COMMISSION_RATE.equals(
            l_mfInstCommission.getCommisionDiv()) || 
            WEB3MFCommissionDivDef.TRADE_PRICE_COMMISSION_RATE.equals(
                l_mfInstCommission.getCommisionDiv()))
        {
            log.debug("�A ����.��Еʎ萔��.�萔���敪 �� �g��n����i�萔�����j�h" +
                    "or �g��������i�萔�����j�h �̏ꍇ");
            
            //1)  ����.�w��敪 �� �g���z�w��h �̏ꍇ
            if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
            {
                log.debug("1)  ����.�w��敪 �� �g���z�w��h �̏ꍇ");
                //�����l �� ����.��������
                l_bdSearchValue = l_bdOrderQuantity;
            }
            //2)  ����.�w��敪 �� �g�����w��h �̏ꍇ
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
            {
                log.debug("2)  ����.�w��敪 �� �g�����w��h �̏ꍇ");
                
                //(a) ����.��Еʎ萔��.����敪 �� �g���t�h or �g��W�h �̏ꍇ
                if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
                    WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
                {
                    log.debug("(a) ����.��Еʎ萔��.����敪 �� �g���t�h or �g��W�h �̏ꍇ");
                        
                    //(i)  ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
                    if (l_blnIsForeignFund || l_blnIsFWF)
                    {        
                        log.debug("(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                        
                        //�����l �� ����.�������� �~
                        //�i ����.���M����.����i(*1) �~ �בփ��[�g(*2).TTS �� �בփ��[�g.�בփ��[�g�v�Z�P�� �j ��
                        //����.����.����i�v�Z�P��
						//�������_�ȉ��؂�̂āB
						l_bdSearchValue =
							l_bdOrderQuantity.multiply(l_bdConstantValue).multiply(l_bdTTS).divide(
								l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).divide(
									l_bdConstantValueCalcUnit,
									0,BigDecimal.ROUND_FLOOR);
                    }
                    //(ii)  ��L�ȊO�̏ꍇ
                    else
                    {
                        log.debug("(ii) ��L�ȊO�̏ꍇ");
                        
                        //�����l �� ����.�������� �~ ����.���M����.����i(*) �� ����.����.����i�v�Z�P��
						//�������_�ȉ��؂�̂āB
                        l_bdSearchValue = l_bdOrderQuantity.multiply(l_bdConstantValue).divide(
                        	l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_FLOOR);
                    }
                }
                //(b)   ����.��Еʎ萔��.����敪 �� �g���h or �g�抷�h �̏ꍇ
                else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
                    WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
                {
                    log.debug("(b) ����.��Еʎ萔��.����敪 �� �g���h or �g�抷�h �̏ꍇ");
                    
                    //(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
                    if (l_blnIsForeignFund || l_blnIsFWF)
                    {                    
                        log.debug("(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                        
                        //�����l �� ����.�������� �~
                        //�i ����.���M����.��񉿊i �~ �בփ��[�g(*).TTB �� �בփ��[�g.�בփ��[�g�v�Z�P�� �j ��
                        //����.����.����i�v�Z�P��
						//�������_�ȉ��؂�̂āB
                        l_bdSearchValue =
                        	l_bdOrderQuantity.multiply(l_bdConstantValue).multiply(l_bdTTB).divide(
                        		l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).divide(
                        			l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_FLOOR);
                    }
                    //(ii)  ��L�ȊO�̏ꍇ
                    else
                    {
                        log.debug("(ii) ��L�ȊO�̏ꍇ");
                        
                        //(*1) calc�v�Z�P�ʓ����菊���Ń��\�b�h�̎葱���Q�ƁB
                        BigDecimal l_bdUnitIncomeTax = 
                            this.calcUnitIncomeTax(
                                l_mainAccount, 
                                l_mfProduct, 
                                l_mfInstCommission, 
                                l_strRequestDiv, 
                                l_strAccountDiv, 
                                l_datBizDate);
                        log.debug("�v�Z�P�ʓ����菊���Ŋz(*1) = " + l_bdUnitIncomeTax);
                        
						//(*2) calc�v�Z�P�ʓ�����n���Ń��\�b�h�̎葱���Q�ƁB
                        BigDecimal l_bdUnitLocalTax = 
                            this.calcUnitLocalTax(
                                l_mainAccount, 
                                l_mfProduct, 
                                l_mfInstCommission, 
                                l_strRequestDiv, 
                                l_strAccountDiv, 
                                l_datBizDate);
                        log.debug("�v�Z�P�ʓ�����n���Ŋz(*2) = " + l_bdUnitLocalTax);
                        
                        //�����l �� ����.�������� �~
                        //�i ����.���M����.��񉿊i �| �v�Z�P�ʓ����菊���Ŋz(*1) �| �v�Z�P�ʓ�����n���Ŋz(*2) �j ��
                        //����.����.����i�v�Z�P��
						//�������_�ȉ��؂�̂āB
                        l_bdSearchValue = l_bdOrderQuantity.multiply(
							l_bdConstantValue.subtract(l_bdUnitIncomeTax).subtract(l_bdUnitLocalTax)).divide(
                        		l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_FLOOR);
                    }
                }
            }
        }

        log.debug("�����l �� " + l_bdSearchValue);
        log.exiting(STR_METHOD_NAME);

        return l_bdSearchValue;
    }
    
    /**
     * (calc�v�Z�P�ʓ����菊����)
     * �v�Z�P�ʓ�����̏����ł��Z�o����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB <BR>
     * @@param l_maiAccount - �ڋq
     * @@param l_mfProduct - ����
     * @@param l_mfInstCommission - ��Еʎ萔��
     * @@param l_strRequestDiv - �����敪
     * @@param l_strAccountDiv - �����敪
     * @@param l_datBizDate - ������
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcUnitIncomeTax(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        Date l_datBizDate) throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcUnitIncomeTax(WEB3GentradeMainAccount," +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }

		//  �g�����M�����}�l�[�W�����擾����
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //�@@   �ȉ��̏����ƈ�v����ꍇ
        
        //�i ����.�����敪 �� �g���搿���h and ����.����.is�����^ �� true �j or 
        //  �g�����M�����}�l�[�W��.is��ېŌڋq(����.�ڋq) �� true
        
        if ((WEB3ClaimDivDef.BUY.equals(l_strRequestDiv) && l_mfProduct.isStockType()) ||
			l_orderManager.isTaxFreeAccount(l_mainAccount))
        {
            log.debug("����.�����敪 �� �g���搿���h and ����.����.is�����^ �� true " +
                "and �g�����M�����}�l�[�W��.is��ېŌڋq(����.�ڋq) �� true");
            //�v�Z�P�ʓ����菊���� �� 0
            log.exiting(STR_METHOD_NAME);
            return new BigDecimal("0");
        }
        //�A   ��L�ȊO�̏ꍇ
        else
        {
            //(*1)calc�擾�P�����\�b�h�̎葱���Q�ƁB
            BigDecimal l_bdGetPrice = 
                this.calcGetPrice(l_mfProduct, l_mainAccount, l_strAccountDiv);
            
            log.debug("�擾�P��(*1) = " + l_bdGetPrice);
            
            //(*2)�ŗ��̎擾�Q�ƁB
            BigDecimal l_bdTaxRate = this.getTaxRate(
                INCOME_TAX, 
                l_mainAccount, 
                l_mfProduct, 
                l_datBizDate);
            
            log.debug("���򒥎����i���Łj(*2) = " + l_bdTaxRate);
            
			//�v�Z�P�ʓ����菊���� �� �i ����.����.��񉿊i �| �擾�P��(*1) �j �~ ���򒥎����i���Łj(*2)
			BigDecimal l_bdSellValue = new BigDecimal(Double.toString(l_mfProduct.getSellValue()));
			
			BigDecimal l_bdCalcUnitIncomeTax = 
				l_bdSellValue.subtract(l_bdGetPrice).multiply(l_bdTaxRate);
            
            //�������_�ȉ��؂�̂āB�v�Z���� �� 0 �̏ꍇ�́A�v�Z�P�ʓ����菊���� �� 0�Ƃ���B
			l_bdCalcUnitIncomeTax = l_bdCalcUnitIncomeTax.setScale(0,BigDecimal.ROUND_FLOOR);

            if (l_bdCalcUnitIncomeTax.compareTo(new BigDecimal("0")) < 0)
            {
                log.debug("�v�Z���� �� 0 �̏ꍇ�́A�v�Z�P�ʓ����菊���� �� 0");
                l_bdCalcUnitIncomeTax = new BigDecimal("0");
            }
            
            log.debug("�v�Z�P�ʓ����菊���� = " + l_bdCalcUnitIncomeTax);
            log.exiting(STR_METHOD_NAME);

            return l_bdCalcUnitIncomeTax;
        }        
    }
    
    /**
     * (calc�v�Z�P�ʓ�����n����)
     * �v�Z�P�ʓ�����̒n���ł��Z�o����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB <BR>
     * @@param l_maiAccount - �ڋq
     * @@param l_mfProduct - ����
     * @@param l_mfInstCommission - ��Еʎ萔��
     * @@param l_strRequestDiv - �����敪
     * @@param l_strAccountDiv - �����敪
     * @@param l_datBizDate - ������
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcUnitLocalTax(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        Date l_datBizDate) throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcUnitLocalTax(WEB3GentradeMainAccount," +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
		//  �g�����M�����}�l�[�W�����擾����
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        //�@@   �ȉ��̏����ƈ�v����ꍇ

        //�i ����.�����敪 �� �g���搿���h and ����.����.is�����^ �� true �j or
        //�i ����.�����敪 �� �g��񐿋��h and ����.����.is�����^ �� true and ����.�ڋq.is�@@�l �� true �j or
        //�g�����M�����}�l�[�W��.is��ېŌڋq(����.�ڋq) �� true �� true
        
        if ((WEB3ClaimDivDef.BUY.equals(l_strRequestDiv) && l_mfProduct.isStockType()) || 
            (WEB3ClaimDivDef.SELL.equals(l_strRequestDiv) && l_mfProduct.isStockType() && 
                l_mainAccount.isCorporation()) || l_orderManager.isTaxFreeAccount(l_mainAccount))
        {
            //�v�Z�P�ʓ�����n���� �� 0
            log.exiting(STR_METHOD_NAME);
            return new BigDecimal("0");
        }
        //�A   ��L�ȊO�̏ꍇ
        else
        {
            //(*1)calc�擾�P�����\�b�h�̎葱���Q�ƁB
            BigDecimal l_bdGetPrice = 
                this.calcGetPrice(l_mfProduct, l_mainAccount, l_strAccountDiv);
            
            log.debug("�擾�P��(*1) = " + l_bdGetPrice);
            
            //(*2) �ŗ��̎擾�Q�ƁB
            BigDecimal l_bdTaxRate = this.getTaxRate(
                LOCAL_TAX, 
                l_mainAccount, 
                l_mfProduct, 
                l_datBizDate);
            
            log.debug("���򒥎����i�n���Łj(*2) = " + l_bdTaxRate);
            
            //�v�Z�P�ʓ�����n���� �� �i ����.����.��񉿊i �| �擾�P��(*1) �j �~ ���򒥎����i�n���Łj(*2)            
			BigDecimal l_bdSellValue = new BigDecimal(Double.toString(l_mfProduct.getSellValue()));

			BigDecimal l_bdCalcUnitLocalTax = 
            	l_bdSellValue.subtract(l_bdGetPrice).multiply(l_bdTaxRate);
			
            //�������_�ȉ��؂�̂āB�v�Z���� �� 0 �̏ꍇ�́A�v�Z�P�ʓ�����n���� �� 0�Ƃ���B
			l_bdCalcUnitLocalTax = l_bdCalcUnitLocalTax.setScale(0,BigDecimal.ROUND_FLOOR);

			if (l_bdCalcUnitLocalTax.compareTo(new BigDecimal("0")) < 0)
			{
				log.debug("�v�Z���� �� 0 �̏ꍇ�́A�v�Z�P�ʓ�����n���� �� 0");
				l_bdCalcUnitLocalTax = new BigDecimal("0");
            }

            log.debug("�v�Z�P�ʓ�����n���� �� " + l_bdCalcUnitLocalTax);
            log.exiting(STR_METHOD_NAME);
            
            return l_bdCalcUnitLocalTax;
        }        
    }
    
    /**
     * (calc�擾�P��)
     * �擾�P���i�뉿�P�� or ���{�P���j���Z�o����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB <BR>
     * @@param l_mfProduct - ����
     * @@param l_mainAccount - �ڋq
     * @@param l_strAccountDiv - �����敪
     * @@return BigDecimal
     * @@throws WEB3BaseException
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcGetPrice(
        WEB3MutualFundProduct l_mfProduct, 
        WEB3GentradeMainAccount l_mainAccount, 
        String l_strAccountDiv)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcGetPrice(" +
        "WEB3MutualFundProduct, WEB3GentradeMainAccount, String)";
    log.entering(STR_METHOD_NAME);
    
    if (l_mainAccount == null || l_mfProduct == null)
    {
        log.debug(" �p�����[�^�l��NULL ");
         throw new WEB3BaseRuntimeException(
             WEB3ErrorCatalog.SYSTEM_ERROR_80017,
             this.getClass().getName() + "." + STR_METHOD_NAME);
    }        
    
    TaxTypeEnum l_taxType = null;
    if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strAccountDiv))
    {        
        log.debug("����.�����敪 �� �g��ʁh�̏ꍇ");
        l_taxType = TaxTypeEnum.NORMAL;
    }
    else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strAccountDiv))
    {        
        log.debug("����.�����敪 �� �g����h�̏ꍇ");
        l_taxType = TaxTypeEnum.SPECIAL;
    }
    
    //�@@ �ۗL���Y�e�[�u������A�ȉ��̏����Ń��R�[�h���擾����B

    //[�擾����]
    //����ID �� ����.�ڋq����擾��������ID
    //�⏕����ID �� ����.�ڋq����擾�����⏕����ID(*)
    //����ID �� ����.��������擾��������ID
    //�ŋ敪 �� ����.�����敪

    //(*) �ڋq���M�p�q�̏ꍇ�́A�ۏ؋������̕⏕����ID�B�����q�̏ꍇ�́A�a��������̕⏕����ID�B

    List l_lisRows = new ArrayList();
    
    String l_strWhere = 
        "account_id = ? and sub_account_id = ? and product_id = ? and tax_type = ?";
    
    long l_lngSubAccountId = 0; 
    try
    {
        if (l_mainAccount.isMarginAccountEstablished(
            WEB3GentradeRepaymentDivDef.DEFAULT))
        {
            l_lngSubAccountId = l_mainAccount.getSubAccount(
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT).getSubAccountId();
        }
        else
        {
            l_lngSubAccountId = l_mainAccount.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getSubAccountId();   
        }
        log.debug("�⏕����ID �� " + l_lngSubAccountId);
    }
    catch (NotFoundException l_ex)
    {
        log.error("___NotFoundException___" , l_ex);
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
    }
    Object[] l_bindVars = {
        new Long(l_mainAccount.getAccountId()),
        new Long(l_lngSubAccountId),
        new Long(l_mfProduct.getProductId()), 
        l_taxType};

    try
    {
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_lisRows = l_queryProcessor.doFindAllQuery(
            AssetRow.TYPE,
            l_strWhere,
            l_bindVars);
    
    }
    catch (DataNetworkException l_ex)
    {
        log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search Asset");
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);
    }
    catch (DataQueryException l_ex)
    {
        log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search Asset");
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_ex.getMessage(),
            l_ex);    
    }
    
    if(l_lisRows == null || l_lisRows.size() == 0)
    {
        //�|�������ʂ̌���=0���̏ꍇ�A�i�f�[�^�s�����j�̗�O���X���[����B
        log.debug("�e�[�u���ɊY������f�[�^������܂���B");
        throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + STR_METHOD_NAME
        );
    }
    
    //�A   �擾�����ۗL���Y�e�[�u��.�뉿�i�뉿�P���v�Z�p�j��ԋp����B
    AssetRow l_assetRow = (AssetRow)l_lisRows.get(0);
    BigDecimal l_bdUnitPrice =
    	new BigDecimal(Double.toString(l_assetRow.getBookValue()));
	
    log.debug("�擾�P�� ��" + l_bdUnitPrice);
    log.exiting(STR_METHOD_NAME);

    return l_bdUnitPrice;        
    }

	/**
	 * (calc�T�Z��������)
	 * �T�Z���������̌v�Z���s���B <BR>
	 * <BR>
	 * �ڍׂ́A�v�Z�����Q�ƁB<BR>
	 * @@param l_maiAccount - �ڋq
	 * @@param l_mfProduct - ����
	 * @@param l_mfInstCommission - ��Еʎ萔��
	 * @@param l_strDesignDiv - �w��敪
	 * @@param l_strRequestDiv - �����敪
	 * @@param l_strAccountDiv - �����敪
	 * @@param l_bdOrderQuantity - ��������
	 * @@param l_bdCommissionPriceRate - �萔���P���A��
	 * @@param l_datBizDate - ������
	 * @@return BigDecimal
	 * @@roseuid 40A3495A00B7
	 */
	protected BigDecimal calcEstimatedQty(
		WEB3GentradeMainAccount l_mainAccount, 
		WEB3MutualFundProduct l_mfProduct, 
		WEB3MutualFundInstCommission l_mfInstCommission, 
		String l_strDesignDiv, 
		String l_strRequestDiv, 
		String l_strAccountDiv, 
		BigDecimal l_bdOrderQuantity, 
		BigDecimal l_bdCommissionPriceRate, 
		Date l_datBizDate)
			throws WEB3BaseException
	{        
		String STR_METHOD_NAME = "calcEstimatedQty(WEB3GentradeMainAccount, " +
			"WEB3MutualFundProduct, WEB3MutualFundInstCommission, String, " +
			"String, String, BigDecimal, BigDecimal, Date)";
		log.entering(STR_METHOD_NAME);
        
		if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
		{
			log.debug(" �p�����[�^�l��NULL ");
			 throw new WEB3BaseRuntimeException(
				 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				 this.getClass().getName() + "." + STR_METHOD_NAME);
		}                
        
		BigDecimal l_bdEstimatedQty = null;

		//is�O�����M
		boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
		//isFWF
		boolean l_blnIsFWF = l_mfProduct.isFWF();
		//is�O�݌����M
		boolean l_blnIsForeignCurencyFund = l_mfProduct.isForeignCurencyFund();
        
		//�@@   ����.�w��敪 �� �g�����w��h�̏ꍇ
		if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
		{
			log.debug("�@@����.�w��敪 �� �g�����w��h�̏ꍇ");
			//�T�Z�������� �� ����.��������
			l_bdEstimatedQty = l_bdOrderQuantity;
		}        

		//�A   ����.�w��敪 �� �g���z�w��h�̏ꍇ
		else if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
		{            
			log.debug("�A����.�w��敪 �� �g���z�w��h�̏ꍇ");
            
			//(*1)����i
			BigDecimal l_bdConstantValue = null;
			BigDecimal l_bdConstantValueCalcUnit =
				new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit()));

			//���t�̏ꍇ�͔��t����i�B
			if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()))
			{
				l_bdConstantValue =
					new BigDecimal(Double.toString(l_mfProduct.getConstantValue()));
			}
			//��W�̏ꍇ�͕�W���i�B
			else if (WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
			{
				l_bdConstantValue =
					new BigDecimal(Double.toString(l_mfProduct.getRecruitConstantValue()));
			}
			//���E�抷�̏ꍇ�͉�񉿊i�B
			else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
					WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
			{
				l_bdConstantValue =
					new BigDecimal(Double.toString(l_mfProduct.getSellValue()));
			}
			log.debug("(*1)����i = " + l_bdConstantValue);

			//(*2)�בփ��[�g�F ����.����.get�בփ��[�g()�ɂĎ擾�B
			//�擾�ł��Ȃ��ꍇ�i����.����.is�O�݌����M()��false�̏ꍇ�j�́ATTB�ATTS�A�בփ��[�g�v�Z�P�ʂƂ�1�Ƃ��Čv�Z�B
			BigDecimal l_bdTTB = new BigDecimal("1");
			BigDecimal l_bdTTS = new BigDecimal("1");
			BigDecimal l_bdCalcUnit = new BigDecimal("1");

			if (l_blnIsForeignCurencyFund)
			{
				ExchangeRateParams l_exchangeRateParams = 
					l_mfProduct.getExchangeRate();       

				l_bdTTB =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
				l_bdTTS =
					new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
				l_bdCalcUnit =
					new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
			}

			//(*3) �ŗ��̎擾�Q�ƁB
			BigDecimal l_bdTaxRate = this.getTaxRate(
				TAX_RATE, 
				l_mainAccount, 
				l_mfProduct, 
				l_datBizDate);
                        
			log.debug("(*3)�ŗ� = " + l_bdTaxRate);
            
			//1)  ����.��Еʎ萔��.����敪 �� �g���t�h or �g��W�h �̏ꍇ
			if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
				WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
			{            
				log.debug("1)����.��Еʎ萔��.����敪 �� �g���t�h or �g��W�h �̏ꍇ");
                
				//(a) ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h�̏ꍇ
				if (WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(
					l_mfInstCommission.getCommisionDiv()))
				{                    
					log.debug("(a)����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h�̏ꍇ");
                    
					//(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
					if (l_blnIsForeignFund || l_blnIsFWF)
					{
						log.debug("(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                        
						//�T�Z�������� �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
						//�i ����.����.����i(*1) �~ �בփ��[�g(*2).TTS �� �בփ��[�g.�בփ��[�g�v�Z�P�� 
						//�{ ����.�萔���P���A�� �~ �i �P �{ ����ŗ�(*3) �j �j
						//�������_�ȉ��؂�̂āB
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTS).divide(
									l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).add(
										l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate))),
								0,BigDecimal.ROUND_FLOOR);
					}
					//(ii)  ��L�ȊO�̏ꍇ
					else
					{            
						log.debug("(ii) ��L�ȊO�̏ꍇ");
                        
						//�T�Z�������� �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
						//�i ����.����.����i(*1) �{ ����.�萔���P���A�� �~ 
						//�i �P �{ ����ŗ�(*2) �j �j
						//�������_�ȉ��؂�̂āB
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.add(l_bdCommissionPriceRate.multiply(
									new BigDecimal("1").add(l_bdTaxRate))),
								0,BigDecimal.ROUND_FLOOR);
					}
				}
				//(b)   ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h�̏ꍇ
				else
				{
					log.debug("(b)����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h�̏ꍇ");
                    
					//(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
					if (l_blnIsForeignFund || l_blnIsFWF)
					{    
						log.debug("(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                        
						//�T�Z�������� �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
						//�i �i ����.����.����i(*1) �~ �בփ��[�g(*2).TTS �� 
						//  �בփ��[�g.�בփ��[�g�v�Z�P�� �j �~
						//�i �P �{ ����.�萔���P���A�� �~�i �P �{ ����ŗ�(*3) �j �j �j
						//�������_�ȉ��؂�̂āB
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTS).divide(
									l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).multiply(
										new BigDecimal("1").add(l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate)))),
								0,BigDecimal.ROUND_FLOOR);
					}
					//(ii)  ��L�ȊO�̏ꍇ
					else
					{
						log.debug("(ii) ��L�ȊO�̏ꍇ");
                        
						//�T�Z�������� �� ����.�������� �~ ����.����.����i�v�Z�P�� �� 
						//�i ����.����.����i(*1) �~ �i �P �{ ����.�萔���P���A�� �~ 
						//�i �P �{ ����ŗ�(*2) �j �j �j
						//�������_�ȉ��؂�̂āB
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(new BigDecimal("1").add(
									l_bdCommissionPriceRate.multiply(
										new BigDecimal("1").add(l_bdTaxRate)))),
								0,BigDecimal.ROUND_FLOOR);
					}
				}

			}
			//2)    ����.��Еʎ萔��.����敪 �� �g���h or �g�抷�h �̏ꍇ
			else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
				WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
			{    
				log.debug("2)����.��Еʎ萔��.����敪 �� �g���h or �g�抷�h �̏ꍇ");

				//(a) ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h�̏ꍇ
				if (WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(
					l_mfInstCommission.getCommisionDiv()))
				{
					log.debug("(a) ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h�̏ꍇ");
                    
					//(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
					if (l_blnIsForeignFund || l_blnIsFWF)
					{
						log.debug("(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                        
						//�T�Z�������� �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
						//�i ����.����.��񉿊i �~ �בփ��[�g(*1).TTB �� �בփ��[�g.�בփ��[�g�v�Z�P�� �|
						//����.�萔���P���A�� �~ �i �P �{ ����ŗ�(*2) �j �j
						//�������_�ȉ��؂�̂āB
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTB).divide(
									l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).subtract(
										l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate))),
								0,BigDecimal.ROUND_FLOOR);
					}
					//(ii)  ��L�ȊO�̏ꍇ
					else
					{
						log.debug("(ii) ��L�ȊO�̏ꍇ");
						//(*1) calc�v�Z�P�ʓ����菊���Ń��\�b�h�̎葱���Q�ƁB
						BigDecimal l_bdUnitIncomeTax = 
							this.calcUnitIncomeTax(
								l_mainAccount, 
								l_mfProduct, 
								l_mfInstCommission, 
								l_strRequestDiv, 
								l_strAccountDiv, 
								l_datBizDate);
                        
						log.debug("�v�Z�P�ʓ����菊����(*1) = " + l_bdUnitIncomeTax);
                         
						//(*2) calc�v�Z�P�ʓ�����n���Ń��\�b�h�̎葱���Q�ƁB
						BigDecimal l_bdUnitLocalTax = 
							this.calcUnitLocalTax(
								l_mainAccount, 
								l_mfProduct, 
								l_mfInstCommission, 
								l_strRequestDiv, 
								l_strAccountDiv, 
								l_datBizDate);
                        
						log.debug("�v�Z�P�ʓ�����n����(*2) = " + l_bdUnitLocalTax);
                        
						//�T�Z�������� �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
						//�i ����.����.��񉿊i �| �v�Z�P�ʓ����菊����(*1) �| �v�Z�P�ʓ�����n����(*2) �|
						//����.�萔���P���A�� �~ �i �P �{ ����ŗ�(*3)  �j �j            
						//�������_�ȉ��؂�̂āB
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.subtract(l_bdUnitIncomeTax).subtract(
									l_bdUnitLocalTax).subtract(l_bdCommissionPriceRate.multiply(
										new BigDecimal("1").add(l_bdTaxRate))),
								0,BigDecimal.ROUND_FLOOR);
					}
				}
				//(b) ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h�̏ꍇ
				else
				{
					log.debug("(b) ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h�̏ꍇ");
                    
					//(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
					if (l_blnIsForeignFund || l_blnIsFWF)
					{
						log.debug("(i) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                        
						//�T�Z�������� �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
						//�i �i ����.����.��񉿊i �~ �בփ��[�g(*1).TTB �� �בփ��[�g.�בփ��[�g�v�Z�P�� �j �~
						//�i �P �| ����.�萔���P���A�� �~ �i �P �{ ����ŗ�(*2) �j �j �j
						//�������_�ȉ��؂�̂āB
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.multiply(l_bdTTB).divide(
									l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).multiply(
										new BigDecimal("1").subtract(l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate)))),
								0,BigDecimal.ROUND_FLOOR);
					}
					//(ii)  ��L�ȊO�̏ꍇ
					else
					{        
						log.debug("(ii) ��L�ȊO�̏ꍇ");
						//(*1) calc�v�Z�P�ʓ����菊���Ń��\�b�h�̎葱���Q�ƁB
						BigDecimal l_bdUnitIncomeTax = 
							this.calcUnitIncomeTax(
								l_mainAccount, 
								l_mfProduct, 
								l_mfInstCommission, 
								l_strRequestDiv, 
								l_strAccountDiv, 
								l_datBizDate);
                        
						log.debug("�v�Z�P�ʓ����菊����(*1) = " + l_bdUnitIncomeTax);
                         
						//(*2) calc�v�Z�P�ʓ�����n���Ń��\�b�h�̎葱���Q�ƁB
						BigDecimal l_bdUnitLocalTax = 
							this.calcUnitLocalTax(
								l_mainAccount, 
								l_mfProduct, 
								l_mfInstCommission, 
								l_strRequestDiv, 
								l_strAccountDiv, 
								l_datBizDate);
                        
						log.debug("�v�Z�P�ʓ�����n����(*2) = " + l_bdUnitLocalTax);
                        
						//�T�Z�������� �� ����.�������� �~ ����.����.����i�v�Z�P�� ��
						//�i �i ����.����.��񉿊i �| �v�Z�P�ʓ����菊����(*1) �| �v�Z�P�ʓ�����n����(*2) �j �~
						//�i �P �| ����.�萔���P���A�� �~ �i �P �{ ����ŗ�(*3)  �j �j �j
						//�������_�ȉ��؂�̂āB
						l_bdEstimatedQty =
							l_bdOrderQuantity.multiply(l_bdConstantValueCalcUnit).divide(
								l_bdConstantValue.subtract(l_bdUnitIncomeTax).subtract(
									l_bdUnitLocalTax).multiply(new BigDecimal("1").subtract(
										l_bdCommissionPriceRate.multiply(
											new BigDecimal("1").add(l_bdTaxRate)))),
								0,BigDecimal.ROUND_FLOOR);
					}
				}
			}
		}

		log.debug("�T�Z�������� ��" + l_bdEstimatedQty);
		log.exiting(STR_METHOD_NAME);        
		return l_bdEstimatedQty;
	}

    /**
     * (calc�T�Z�������)
     * �T�Z����������Z�o����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB <BR>
     * @@param l_mfProduct - ����
     * @@param l_mfInstCommission - ��Еʎ萔��
     * @@param l_bdEstimatedQty - �T�Z��������
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcEstimatedTradeAmount(
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        BigDecimal l_bdEstimatedQty)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcEstimatedTradeAmount(" +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, BigDecimal)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        BigDecimal l_bdEstimatedTradeAmount = null;
		
        //is�O�����M
        boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
        //isFWF
        boolean l_blnIsFWF = l_mfProduct.isFWF();
        //is�O�݌����M
        boolean l_blnIsForeignCurencyFund = l_mfProduct.isForeignCurencyFund();

		//(*1)����i
		BigDecimal l_bdConstantValue = null;
		BigDecimal l_bdConstantValueCalcUnit =
			new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit()));

		//���t�̏ꍇ�͔��t����i�B
		if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getConstantValue()));
		}
		//��W�̏ꍇ�͕�W���i�B
		else if (WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getRecruitConstantValue()));
		}
		//���E�抷�̏ꍇ�͉�񉿊i�B
		else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
				WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
		{
			l_bdConstantValue =
				new BigDecimal(Double.toString(l_mfProduct.getSellValue()));
		}

		//(*2)�בփ��[�g�F ����.����.get�בփ��[�g()�ɂĎ擾�B
		//�擾�ł��Ȃ��ꍇ�i����.����.is�O�݌����M()��false�̏ꍇ�j�́ATTB�ATTS�A�בփ��[�g�v�Z�P�ʂƂ�1�Ƃ��Čv�Z�B
		BigDecimal l_bdTTB = new BigDecimal("1");
		BigDecimal l_bdTTS = new BigDecimal("1");
		BigDecimal l_bdCalcUnit = new BigDecimal("1");

		if (l_blnIsForeignCurencyFund)
		{
			ExchangeRateParams l_exchangeRateParams = 
				l_mfProduct.getExchangeRate();       

			l_bdTTB =
				new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
			l_bdTTS =
				new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
			l_bdCalcUnit =
				new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
		}

        //�@@ ����.��Еʎ萔��.����敪 �� �g���t�hor�g��W�h �̏ꍇ
        if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
            WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
        {
            log.debug("�@@ ����.��Еʎ萔��.����敪 �� �g���t�hor�g��W�h �̏ꍇ");
            
            //1) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
            if (l_blnIsForeignFund || l_blnIsFWF)
            {   
                log.debug("1) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                
                //�T�Z������� �� ����.�T�Z�������� �~ ����.����.����i(*1) �~ 
                //�בփ��[�g(*2).TTS �� �בփ��[�g.�בփ��[�g�v�Z�P�� ��
                //����.����.����i�v�Z�P��        
				//�������_�ȉ��͑�1�ʂŎl�̌ܓ��B
				l_bdEstimatedTradeAmount =
					l_bdEstimatedQty.multiply(l_bdConstantValue).multiply(l_bdTTS).divide(
						l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).divide(
							l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_HALF_UP);
            }
            //2)  ��L�ȊO�̏ꍇ
            else
            {
                log.debug("2)  ��L�ȊO�̏ꍇ");
                
                //�T�Z������� �� ����.�T�Z�������� �~ ����.����.����i(*) �� ����.����.����i�v�Z�P��
				//�������_�ȉ��͑�1�ʂŎl�̌ܓ��B
				l_bdEstimatedTradeAmount =
					l_bdEstimatedQty.multiply(l_bdConstantValue).divide(
							l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_HALF_UP);
            }
        }
        //�A ����.��Еʎ萔��.����敪 �� �g���hor�g�抷�h �̏ꍇ
        if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
            WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
        {
            log.debug("�A ����.��Еʎ萔��.����敪 �� �g���hor�g�抷�h �̏ꍇ");

            //1)  ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ
            if (l_blnIsForeignFund || l_blnIsFWF)
            {
                log.debug("1) ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
                
                //�T�Z������� �� ����.�T�Z�������� �~
                //����.����.��񉿊i �~ �בփ��[�g(*).TTB �� �בփ��[�g.�בփ��[�g�v�Z�P�� ��
                //����.����.����i�v�Z�P��        
				//�������_�ȉ��͑�1�ʂŎl�̌ܓ��B
				l_bdEstimatedTradeAmount =
					l_bdEstimatedQty.multiply(l_bdConstantValue).multiply(l_bdTTB).divide(
						l_bdCalcUnit,10,BigDecimal.ROUND_HALF_UP).divide(
							l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_HALF_UP);
            }
            //2)  ��L�ȊO�̏ꍇ
            else
            {    
                log.debug("2)  ��L�ȊO�̏ꍇ");
                
                //�T�Z������� �� ����.�T�Z�������� �~ ����.����.��񉿊i �� ����.����.����i�v�Z�P��
				//�������_�ȉ��͑�1�ʂŎl�̌ܓ��B
				l_bdEstimatedTradeAmount =
					l_bdEstimatedQty.multiply(l_bdConstantValue).divide(
							l_bdConstantValueCalcUnit,0,BigDecimal.ROUND_HALF_UP);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_bdEstimatedTradeAmount;
    }
    
    /**
     * (calc�萔��)
     * �萔�����Z�o����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB <BR>
     * @@param l_maiAccount - �ڋq
     * @@param l_mfInstCommission - ��Еʎ萔��
     * @@param l_mfProduct - ����
     * @@param l_swtProduct - �抷��̖���
     * @@param l_bdEstimatedQty - �T�Z��������
     * @@param l_bdEstimatedTradeAmount - �T�Z�������
     * @@param l_bdCommissionPriceRate - �萔���P���A��
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcCommission(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_swtProduct, 
        BigDecimal l_bdEstimatedQty, 
		BigDecimal l_bdEstimatedTradeAmount, 
		BigDecimal l_bdCommissionPriceRate)
            throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "calcCommission(WEB3GentradeMainAccount, " +
            "WEB3MutualFundInstCommission, BigDecimal, BigDecimal, BigDecimal)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfInstCommission == null || l_mfProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		//  �g�����M�����}�l�[�W�����擾����
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        BigDecimal l_bdCommission = null;
        
        WEB3MutualFundProduct l_wkMfProduct = null;
        //�@@ ����.�抷��̖�����null�̏ꍇ
        if (l_swtProduct == null)
        {
            log.debug("�@@ ����.�抷��̖�����null�̏ꍇ");
        	l_wkMfProduct = l_mfProduct;
        }
        //�A ����.�抷��̖�����null�ȊO�̏ꍇ
        else
        {
        	l_wkMfProduct = l_swtProduct;
            log.debug("�A ����.�抷��̖�����null�ȊO�̏ꍇ");
        }
        
        //�B �g�����M�����}�l�[�W��.is�萔�������ڋq(����.�ڋq,����.����) �� true �̏ꍇ
        if (l_orderManager.isCommissionFreeAccount(l_mainAccount, l_wkMfProduct))
        {
            log.debug("�B �g�����M�����}�l�[�W��.is�萔�������ڋq(����.�ڋq,����.����) �� true �̏ꍇ");
            //�萔�� �� 0
            l_bdCommission = new BigDecimal("0");
            
        }
        //�C �g�����M�����}�l�[�W��.is�萔�������ڋq(����.�ڋq,����.����) �� false �̏ꍇ
        else
        {
            log.debug("�C �g�����M�����}�l�[�W��.is�萔�������ڋq(����.�ڋq,����.����) �� false �̏ꍇ");
            //1)  ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h �̏ꍇ
            
            if (WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(
                l_mfInstCommission.getCommisionDiv()))
            {                
                log.debug("1)����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h �̏ꍇ");
                //�萔�� �� ����.�T�Z�������� �~ ����.�萔���P���A�� �� ����.����.����i�v�Z�P��        
				//�������_�ȉ��͑�1�ʂŎl�̌ܓ��B
                l_bdCommission =
                	l_bdEstimatedQty.multiply(l_bdCommissionPriceRate).divide(
						new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit())),
						0,BigDecimal.ROUND_HALF_UP);
            }
            //2) ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h �̏ꍇ
            else
            {        
                log.debug("2) ����.��Еʎ萔��.�萔���敪 �� �g�P�ʐ��ʓ�����萔���P���h �̏ꍇ");
                //�萔�� �� ����.�T�Z������� �~ ����.�萔���P���A��        
				//�������_�ȉ��͑�1�ʂŎl�̌ܓ��B
				l_bdCommission =
					l_bdEstimatedTradeAmount.multiply(l_bdCommissionPriceRate).setScale(
						0,BigDecimal.ROUND_HALF_UP);
            }
        }        

        log.debug("�萔�� �� " + l_bdCommission);
        log.exiting(STR_METHOD_NAME);        
        return l_bdCommission;
    }
    
    /**
     * (calc�萔�������)
     * �萔������ł��Z�o����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB <BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * @@param l_bdCommission - �萔��
     * @@param l_datBizDate - ������
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcCommissionTax(
        WEB3GentradeMainAccount l_mainAccount, 
        BigDecimal l_bdCommission, 
        Date l_datBizDate) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcCommissionTax(" +
            "WEB3GentradeMainAccount, BigDecimal, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(*3) �ŗ��̎擾�Q�ƁB
        BigDecimal l_bdTaxRate = this.getTaxRate(
            TAX_RATE, 
            l_mainAccount, 
            null, 
            l_datBizDate);
        
        //�萔������� �� ����.�萔�� �~ ����ŗ�(*)
		BigDecimal l_bdCommissionTax =
        	l_bdCommission.multiply(l_bdTaxRate);
		//�������_�ȉ��͑�1�ʂŎl�̌ܓ��B
		l_bdCommissionTax =
			l_bdCommissionTax.setScale(0,BigDecimal.ROUND_HALF_UP);

        log.exiting(STR_METHOD_NAME);        
        return l_bdCommissionTax;
    }
    
    /**
     * (calc������)
     * �����ł��Z�o����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB <BR>
     * @@param l_maiAccount - �ڋq
     * @@param l_mfProduct - ����
     * @@param l_mfInstCommission - ��Еʎ萔��
     * @@param l_strRequestDiv - �����敪
     * @@param l_strAccountDiv - �����敪
     * @@param l_bdEstimatedQty - �T�Z��������
     * @@param l_datBizDate - ������
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcIncomeTax(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
		BigDecimal l_bdEstimatedQty, 
        Date l_datBizDate) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcIncomeTax(WEB3GentradeMainAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, " +
            "String, String, BigDecimal, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		//  �g�����M�����}�l�[�W�����擾����
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

		BigDecimal l_bdIncomeTax = new BigDecimal("0");
                
        //����Еʎ萔��.����敪 �� �g���h or �g�抷�h and 
        //�������M�i����.is�O�����M()��false and ����.isFWF()��false�j �̏ꍇ�̂݁A�Z�o�B
        
        if ((WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
            WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv())) && 
            (!l_mfProduct.isForeignFund() && !l_mfProduct.isFWF()))
        {
            
            //�@@ �ȉ��̏����ƈ�v����ꍇ
    
            //�i ����.�����敪 �� �g���搿���hand ����.����.is�����^ �� true �j or
            //�g�����M�����}�l�[�W��.is��ېŌڋq(����.�ڋq) �� true
            if ((WEB3ClaimDivDef.BUY.equals(l_strRequestDiv) && l_mfProduct.isStockType()) || 
                l_orderManager.isTaxFreeAccount(l_mainAccount))
            {
                //������ �� 0
                l_bdIncomeTax = new BigDecimal("0");
                
            }
            //�A   ��L�ȊO�̏ꍇ
            else
            {
				//(*) calc�v�Z�P�ʓ����菊���Ń��\�b�h�̎葱���Q�ƁB
				BigDecimal l_bdUnitIncomeTax = 
					this.calcUnitIncomeTax(
						l_mainAccount, 
						l_mfProduct, 
						l_mfInstCommission, 
						l_strRequestDiv, 
						l_strAccountDiv, 
						l_datBizDate);
                        
				log.debug("�v�Z�P�ʓ����菊���Ŋz(*) = " + l_bdUnitIncomeTax);

                //������ �� ����.�T�Z�������� �~ �v�Z�P�ʓ����菊����(*) �� ����.����.����i�v�Z�P��            
				//�������_�ȉ��؂�̂āB
                l_bdIncomeTax =
					l_bdEstimatedQty.multiply(l_bdUnitIncomeTax).divide(
						new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit())),
						0,BigDecimal.ROUND_FLOOR);

				//���v�Z���� �� 0 �̏ꍇ�́A������ �� 0�Ƃ���B
                if (l_bdIncomeTax.compareTo(new BigDecimal("0")) < 0)
                {
                    l_bdIncomeTax = new BigDecimal("0");
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_bdIncomeTax;
    }
    
    /**
     * (calc�n����)
     * �n���ł��Z�o����B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB <BR>
     * @@param l_maiAccount - �ڋq
     * @@param l_mfProduct - ����
     * @@param l_mfInstCommission - ��Еʎ萔��
     * @@param l_strRequestDiv - �����敪
     * @@param l_strAccountDiv - �����敪
     * @@param l_bdEstimatedQty - �T�Z��������
     * @@param l_datBizDate - ������
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcLocalTax(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strRequestDiv, 
        String l_strAccountDiv, 
        BigDecimal l_bdEstimatedQty, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcLocalTax(WEB3GentradeMainAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundInstCommission, " +
            "String, String, BigDecimal, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_mfProduct == null || l_mfInstCommission == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
		//  �g�����M�����}�l�[�W�����擾����
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        BigDecimal l_bdLocalTax = new BigDecimal("0");

        //����Еʎ萔��.����敪 �� �g���h or �g�抷�h and 
        //�������M�i����.is�O�����M()��false and ����.isFWF()��false�j �̏ꍇ�̂݁A�Z�o�B
        
        if ((WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
            WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv())) && 
            (!l_mfProduct.isForeignFund() && !l_mfProduct.isFWF()))
        {
        
            //�@@ �ȉ��̏����ƈ�v����ꍇ
    
            //�i ����.�����敪 �� �g���搿���hand ����.����.is�����^ �� true �j or
            //�i ����.�����敪 �� �g��񐿋��hand ����.����.is�����^ �� true and ����.�ڋq.is�@@�l �� true �j or
            //�g�����M�����}�l�[�W��.is��ېŌڋq(����.�ڋq) �� true
    
            if ((WEB3ClaimDivDef.BUY.equals(l_strRequestDiv) && l_mfProduct.isStockType()) || 
                (WEB3ClaimDivDef.SELL.equals(l_strRequestDiv) && l_mfProduct.isStockType() && l_mainAccount.isCorporation()) ||
                l_orderManager.isTaxFreeAccount(l_mainAccount))
            {
                //�n���� �� 0
                l_bdLocalTax = new BigDecimal("0");
            }
            //�A   ��L�ȊO�̏ꍇ
            else
            {
				//(*) calc�v�Z�P�ʓ�����n���Ń��\�b�h�̎葱���Q�ƁB
				BigDecimal l_bdUnitLocalTax = 
					this.calcUnitLocalTax(
						l_mainAccount, 
						l_mfProduct, 
						l_mfInstCommission, 
						l_strRequestDiv, 
						l_strAccountDiv, 
						l_datBizDate);
                        
				log.debug("�v�Z�P�ʓ�����n���Ŋz(*) = " + l_bdUnitLocalTax);

                //�n���� �� ����.�T�Z�������� �~ �v�Z�P�ʓ�����n����(*) �� ����.����.����i�v�Z�P��
				//�������_�ȉ��؂�̂āB
                l_bdLocalTax =
                	l_bdEstimatedQty.multiply(l_bdUnitLocalTax).divide(
						new BigDecimal(Double.toString(l_mfProduct.getConstantValueCalcUnit())),
						0,BigDecimal.ROUND_FLOOR);

				//���v�Z���� �� 0 �̏ꍇ�́A�n���� �� 0�Ƃ���B
				if (l_bdLocalTax.compareTo(new BigDecimal("0")) < 0)
				{
					l_bdLocalTax = new BigDecimal("0");
				}
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_bdLocalTax;
    }
    
    /**
     * (calc�T�Z��n���)
     * �T�Z��n����̌v�Z���s���B <BR>
     * <BR>
     * �ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_mfInstCommission - ��Еʎ萔��
     * @@param l_strDesignDiv - �w��敪
     * @@param l_bdOrderQuantity - ��������
     * @@param l_bdEstimatedTradeAmount - �T�Z�������
     * @@param l_bdCommission - �萔��
     * @@param l_bdConsumptionTax - �����
     * @@param l_bdIncomeTax - ������
     * @@param l_bdLocalTax - �n����
     * @@return BigDecimal
     * @@roseuid 40A3495A00B7
     */
    protected BigDecimal calcEstimatedPrice(
        WEB3MutualFundInstCommission l_mfInstCommission, 
        String l_strDesignDiv, 
        BigDecimal l_bdOrderQuantity, 
		BigDecimal l_bdEstimatedTradeAmount, 
		BigDecimal l_bdCommission, 
		BigDecimal l_bdConsumptionTax, 
		BigDecimal l_bdIncomeTax, 
		BigDecimal l_bdLocalTax)
    {        
        String STR_METHOD_NAME = "calcEstimatedPrice(WEB3MutualFundInstCommission," +
                "String, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal, BigDecimal)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mfInstCommission == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        BigDecimal l_bdEstimatedPrice = null;
        
        //�@@   ����.�w��敪 �� �g���z�w��h�̏ꍇ
        if (WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
        {
            log.debug("����.�w��敪 �� �g���z�w��h�̏ꍇ");
            
            //�T�Z��n��� �� ����.��������
            l_bdEstimatedPrice = l_bdOrderQuantity;
        }
        //�A   ����.�w��敪 �� �g�����w��h�̏ꍇ
        else if (WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
        {
            log.debug("����.�w��敪 �� �g�����w��h�̏ꍇ");
            
            //1)  ����.��Еʎ萔��.����敪 �� �g���t�h or �g��W�h �̏ꍇ
            if (WEB3MFDealDivDef.BUY.equals(l_mfInstCommission.getDealDiv()) || 
                WEB3MFDealDivDef.RECRUIT.equals(l_mfInstCommission.getDealDiv()))
            {
                log.debug("����.��Еʎ萔��.����敪 �� �g���t�h or �g��W�h �̏ꍇ");
                
                //�T�Z��n��� �� ����.�T�Z������� �{ ����.�萔�� �{ ����.�����
                l_bdEstimatedPrice =
                	l_bdEstimatedTradeAmount.add(l_bdCommission).add(l_bdConsumptionTax);
            }
            
            //2)  ����.��Еʎ萔��.����敪 �� �g���h or �g�抷�h �̏ꍇ
            else if (WEB3MFDealDivDef.SELL.equals(l_mfInstCommission.getDealDiv()) || 
                WEB3MFDealDivDef.SWITCHING.equals(l_mfInstCommission.getDealDiv()))
            {    
                log.debug("����.��Еʎ萔��.����敪 �� �g���h or �g�抷�h �̏ꍇ");
                
                //�T�Z��n��� �� ����.�T�Z������� �| ����.�萔�� �| ����.����� �| ����.������ �| ����.�n����
				l_bdEstimatedPrice =
					l_bdEstimatedTradeAmount.subtract(l_bdCommission).subtract(
						l_bdConsumptionTax).subtract(l_bdIncomeTax).subtract(l_bdLocalTax);
                    
                //���v�Z���� �� 0 �̏ꍇ�́A�T�Z��n��� �� 0 �Ƃ���B
                if (l_bdEstimatedPrice.compareTo(new BigDecimal("0")) < 0)
                {
                    log.debug("���v�Z���� �� 0 �̏ꍇ�́A�T�Z��n��� �� 0");
                    l_bdEstimatedPrice = new BigDecimal("0");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_bdEstimatedPrice;
        
        //���������M�ȊO�i����.����.is�O�����M() �� true or ����.����.isFWF() �� true�j�̏ꍇ�́A
        //����.�����łƈ���.�n���ł�0�ɂȂ�B
    }
    
    private BigDecimal getTaxRate(
        String l_strParamType, 
        WEB3GentradeMainAccount l_mainAccount,
        WEB3MutualFundProduct l_mfProduct, 
        Date l_datBizDate) throws WEB3BaseException
    {    
        //1.17.   �ŗ��̎擾
    
        //�ŗ��C���X�^���X�𐶐����A�ŗ����擾����B
    
        //�@@   �ŗ��C���X�^���X�𐶐�����B
        //[�R���X�g���N�^�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�ڋq.getInstitution().getInstitutionCode()�̖߂�l
        //�Ŏ�ށF �i�ȉ��̂Ƃ���j
        //�E   ���򒥎����i���Łj���擾 and ����.����.is�����^ �� true �̏ꍇ
        //      ���M���򒥎�(�����^�E����)
        //�E   ���򒥎����i���Łj���擾 and ����.����.is�����^ �� false �̏ꍇ
        //      ���M���򒥎�(���^�E����)
        //�E   ���򒥎����i�n���Łj���擾 and ����.����.is�����^ �� true �̏ꍇ
        //      ���M���򒥎�(�����^�E�n����)
        //�E   ���򒥎����i�n���Łj���擾 and ����.����.is�����^ �� false �̏ꍇ
        //      ���M���򒥎�(���^�E�n����)
        //�E   ����ŗ����擾�̏ꍇ
        //      �����
        //�������F ����.������
        
        String l_strTaxType = null;
        if (INCOME_TAX.equals(l_strParamType))
        {
            if (l_mfProduct.isStockType())
            {
                l_strTaxType = WEB3DutyTypeDef.MF_WITHHOLDING_STOCK_NATIONAL_TAX;
            }
            else
            {
                l_strTaxType = WEB3DutyTypeDef.MF_WITHHOLDING_BOND__NATIONAL_TAX;
            }
        }
        else if (LOCAL_TAX.equals(l_strParamType))
        {
            if (l_mfProduct.isStockType())
            {
                l_strTaxType = WEB3DutyTypeDef.MF_WITHHOLDING_STOCK_LOCAL_TAX;
            }
            else
            {
                l_strTaxType = WEB3DutyTypeDef.MF_WITHHOLDING_BOND_LOCAL_TAX;
            }
        }
        else if (TAX_RATE.equals(l_strParamType)) 
        {
            l_strTaxType = WEB3DutyTypeDef.CONSUMPTION_TAX;
        }
        
        log.debug("�Ŏ�� = " + l_strTaxType);
        
        WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(
            l_mainAccount.getInstitution().getInstitutionCode(), 
            l_strTaxType, 
            new Timestamp(l_datBizDate.getTime()));        
    
        //�A   �ŗ����擾����B
        //�擾�����ŗ��C���X�^���X.get�ŗ�()���\�b�h���R�[������B
        
        BigDecimal l_bdTaxRate = new BigDecimal(Double.toString(l_taxRate.getTaxRate()));
        
        //�B �ŗ���ԋp����B
        //�擾�����ŗ��~0.01��ԋp����B
		l_bdTaxRate = l_bdTaxRate.multiply(new BigDecimal("0.01"));

        return l_bdTaxRate;
    }
    
	/**
	 * (calc�T�Z���t����)
	 * �抷������̊T�Z���t�����̌v�Z���s���B<BR>
	 * <BR>
	 * @@param l_mfProduct - ����
	 * @@param l_dblOrderQuantity - ��������
	 * @@return double
	 * @@roseuid 40A3495A00B7
	 */
	public double calcEstimatedBuyQty(
		WEB3MutualFundProduct l_mfProduct, 
		double l_dblOrderQuantity)
			throws WEB3BaseException
	{
		String STR_METHOD_NAME = "calcEstimatedBuyQty(WEB3MutualFundProduct, double)";
		log.entering(STR_METHOD_NAME);
        
		if (l_mfProduct == null)
		{
			log.debug(" �p�����[�^�l��NULL ");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
         
		//�P�j�T�Z��n����I�u�W�F�N�g�𐶐�����B 
			WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = 
				new WEB3MutualFundEstimatedPrice();
        
		//�Q�jthis.calc�T�Z��������()�ɏ������Ϗ�����B 
		//[����] 
		//�����敪�F �h���t�h
		//�������ʁF ����.�������� 
		//�����F ����.���� 
		//�T�Z��n����F �R-�P�j�Ő��������I�u�W�F�N�g 
        
		this.calcEstimatedQty(
			WEB3MFDealDivDef.BUY, 
			l_dblOrderQuantity, 
			l_mfProduct, 
			l_mfEstimatedPrice); 

		log.debug(">>>>> �T�Z���t���� = " + l_mfEstimatedPrice.getEstimatedQty());
        
		//�R�j�T�Z��n���.get�T�Z��������()�̖߂�l��ԋp����B 
		log.exiting(STR_METHOD_NAME); 
		return l_mfEstimatedPrice.getEstimatedQty();
            
	}
    
    /**
     * (calc�O��MMF�T�Z��n���)<BR>
     * �ȉ��̒l���Z�o���āA���ꂼ��T�Z��n����I�u�W�F�N�g�ɃZ�b�g����B<BR> 
     * �E�T�Z������� <BR>
     * �E�T�Z�������(�O�݁j<BR> 
     * �E�T�Z��n��� <BR>
     * <BR>
     * �P�j����.�g�����M����.get�O��MMF�בփ��[�g()���R�[������B<BR> 
     * <BR>
     * �Q�j�T�Z����������Z�o���āA�v�Z���ʂ��T�Z��n����I�u�W�F�N�g.�T�Z��������ɃZ�b�g����B<BR> 
     * <BR>
     * �@@�@@�@@�T�Z������� �� ����.�������� �~ �בփ��[�g �^  �⏕�ʉݒP�ʔ�<BR> 
     * �@@�@@�@@���v�Z���ʂ͏����_�ȉ��l�̌ܓ� <BR>
     * <BR>
     * �R�j�T�Z��������i�O�݁j���Z�o���āA<BR>
     * �v�Z���ʂ��T�Z��n����I�u�W�F�N�g.�T�Z��������i�O�݁j�ɃZ�b�g����B<BR> 
     * <BR>
     * �@@�@@�@@�T�Z��������i�O�݁j�� ����.��������  �^  �⏕�ʉݒP�ʔ�<BR> 
     * �@@�@@�@@���v�Z���ʂ́A������R�ʂ�؂�̂ď�����Q�ʂ܂łƂ���B<BR> 
     * <BR>
     * �S�j�T�Z��n������Z�o���āA�v�Z���ʂ��T�Z��n����I�u�W�F�N�g.�T�Z��n����ɃZ�b�g����B<BR> 
     * <BR>
     * �@@�@@�S-�P�j�@@����.���ϕ��@@ ��  "�~��"�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�S-�P-�P�j �����敪 ��  ���t�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�T�Z��n��� �� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����.�������� �~ �בփ��[�g �^  �⏕�ʉݒP�ʔ�  �~ �i 1 �{ �����S���� �j<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@���v�Z���ʂ͏����_�ȉ��؂�̂� <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�S-�P-�Q�j �����敪 ��  ���̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�T�Z��n��� �� ����.�������� �~ �בփ��[�g �^  �⏕�ʉݒP�ʔ�<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@���v�Z���ʂ͏����_�ȉ��؂�̂� <BR>
     * <BR>
     * �@@�@@�S-�Q�j�@@����.���ϕ��@@  != "�~��"�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�T�Z��n��� �� ����.��������  �^  �⏕�ʉݒP�ʔ�<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@���v�Z���ʂ́A������R�ʂ�؂�̂ď�����Q�ʂ܂łƂ���B<BR> 
     * <BR>
     * �בփ��[�g: <BR>
     * �@@�@@�����敪 ��  ���t�̏ꍇ �F �擾�����O��MMF�בփ��[�g.TTS <BR>
     * �@@�@@�����敪 ��  ���̏ꍇ �F �擾�����O��MMF�בփ��[�g.TTB <BR>
     * <BR>
     * �⏕�ʉݒP�ʔ�F�擾�����O��MMF�בփ��[�g.�⏕�ʉݒP�ʔ� <BR>
     * �����S����      �F�擾�����O��MMF�בփ��[�g.�����S���� <BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * �����敪 <BR>
     * <BR>
     * �P�F���t <BR>
     * �Q�F��� <BR>
     * �R�F�抷 <BR>
     * �S�F��W<BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@param l_strSettleDiv - (���ϕ��@@)<BR>
     * ���ϕ��@@<BR>
     * <BR>
     * �P�F�~��<BR>
     * �Q�F�O��<BR>
     * @@param l_mutualFundProduct - (�g�����M����)<BR>
     * �g�����M��<BR>
     * @@param l_estimatedPrice - (�T�Z��n����I�u�W�F�N�g)<BR>
     * �T�Z��n����I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    public void calcFrgnMmfEstimatedTradeAmount(
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strSettleDiv,
        WEB3MutualFundProduct l_mutualFundProduct,
        WEB3MutualFundEstimatedPrice l_estimatedPrice) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimatedBuyQty("
            + "String, double, String, WEB3MutualFundProduct, WEB3MutualFundEstimatedPrice)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFundProduct == null || l_estimatedPrice == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j����.�g�����M����.get�O��MMF�בփ��[�g()���R�[������B
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams =
            l_mutualFundProduct.getFrgnMmfExchangeRate();

        //�בփ��[�g
        BigDecimal l_bdExchangeRate = new BigDecimal("1");

        //  �����敪 ��  ���t�̏ꍇ
        if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv))
        {
            //�בփ��[�g:�擾�����O��MMF�בփ��[�g.TTS
            l_bdExchangeRate =
                new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getTtSellingRate()));
        }
        //�����敪 ��  ���̏ꍇ
        else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv))
        {
            //�בփ��[�g:�擾�����O��MMF�בփ��[�g.TTB
            l_bdExchangeRate =
                new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getTtBuyingRate()));
        }

        //�⏕�ʉݒP�ʔ�F�擾�����O��MMF�בփ��[�g.�⏕�ʉݒP�ʔ�
        BigDecimal l_bdSubCurrencyRatio =
            new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getSubCurrencyRatio()));

        //��������
        BigDecimal l_bdOrderQuantity = new BigDecimal(String.valueOf(l_dblOrderQuantity));

        //�Q�j�T�Z����������Z�o���āA�v�Z���ʂ��T�Z��n����I�u�W�F�N�g.�T�Z��������ɃZ�b�g����B
        //      �T�Z������� �� ����.�������ʁ~�בփ��[�g�^�⏕�ʉݒP�ʔ�
        //      ���v�Z���ʂ͏����_�ȉ��l�̌ܓ�
        BigDecimal l_bdEstimatedTradeAmount =
            l_bdOrderQuantity.multiply(l_bdExchangeRate).divide(
                l_bdSubCurrencyRatio, 0, BigDecimal.ROUND_HALF_UP);
        l_estimatedPrice.setEstimatedTradeAmount(l_bdEstimatedTradeAmount.doubleValue());

        //�R�j�T�Z��������i�O�݁j���Z�o���āA�v�Z���ʂ��T�Z��n����I�u�W�F�N�g.�T�Z��������i�O�݁j�ɃZ�b�g����B
        //      �T�Z��������i�O�݁j�� ����.�������ʁ^�⏕�ʉݒP�ʔ�
        //      ���v�Z���ʂ́A������R�ʂ�؂�̂ď�����Q�ʂ܂łƂ���B
        BigDecimal l_bdFrgnCurrencyEstimatedTradeAmount =
            l_bdOrderQuantity.divide(
                l_bdSubCurrencyRatio, 2, BigDecimal.ROUND_FLOOR);
        l_estimatedPrice.setForeignCurrencyEstimatedTradeAmount(
            l_bdFrgnCurrencyEstimatedTradeAmount.doubleValue());

        //�����S����
        BigDecimal l_bdRestrictRate =
            new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getRestrictRate()));

        //�T�Z��n���
        BigDecimal l_bdEstimatedPrice = new BigDecimal("0");

        //�S�j�T�Z��n������Z�o���āA�v�Z���ʂ��T�Z��n����I�u�W�F�N�g.�T�Z��n����ɃZ�b�g����B
        //  �@@�@@�S-�P�j�@@����.���ϕ��@@ ��  "�~��"�̏ꍇ
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
        {
            //�S-�P-�P�j �����敪 ��  ���t�̏ꍇ
            if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv))
            {
                //�T�Z��n��� �� ����.�������� �~ �בփ��[�g�^�⏕�ʉݒP�ʔ�~(1�{�����S����)
                //���v�Z���ʂ͏����_�ȉ��؂�̂�
                BigDecimal l_bdOne = new BigDecimal("1");
                l_bdEstimatedPrice =
                    l_bdOrderQuantity.multiply(l_bdExchangeRate).divide(
                        l_bdSubCurrencyRatio, 10, BigDecimal.ROUND_HALF_DOWN).multiply(
                            l_bdOne.add(l_bdRestrictRate));
                l_bdEstimatedPrice = l_bdEstimatedPrice.setScale(0, BigDecimal.ROUND_FLOOR);
            }
            //�S-�P-�Q�j �����敪 ��  ���̏ꍇ
            else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv))
            {
                // �T�Z��n��� �� ����.�������� �~ �בփ��[�g�^�⏕�ʉݒP�ʔ�
                // ���v�Z���ʂ͏����_�ȉ��؂�̂�
                l_bdEstimatedPrice =
                    l_bdOrderQuantity.multiply(l_bdExchangeRate).divide(
                        l_bdSubCurrencyRatio, 0, BigDecimal.ROUND_FLOOR);
            }
        }
        //�S-�Q�j�@@����.���ϕ��@@  != "�~��"�̏ꍇ
        else
        {
            //�T�Z��n��� �� ����.�������ʁ^�⏕�ʉݒP�ʔ�
            //���v�Z���ʂ́A������R�ʂ�؂�̂ď�����Q�ʂ܂łƂ���B
            l_bdEstimatedPrice =
                l_bdOrderQuantity.divide(l_bdSubCurrencyRatio, 2, BigDecimal.ROUND_FLOOR);
        }
        l_estimatedPrice.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc�O��MMF�T�Z��������)<BR>
     * �T�Z���������̌v�Z�����ĊT�Z��n����I�u�W�F�N�g�̊T�Z���������ɃZ�b�g����B<BR>
     * <BR>
     * �P�j����.�g�����M����.get�O��MMF�בփ��[�g()���R�[������B <BR>
     * <BR>
     * �Q�j�T�Z�����������Z�o����B <BR>
     * <BR>
     * �@@�Q-�P�j  ����.���ϕ��@@ = "�~��" �̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�@@�T�Z�������� �� ����.��������  �~  �⏕�ʉݒP�ʔ�  �^  �בփ��[�g<BR>
     * �@@�@@�@@�@@���v�Z���ʂ͏����_�ȉ��؂�̂� <BR>
     * <BR>
     * �@@�Q-�Q�j  ����.���ϕ��@@  != "�~��" �̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�@@�T�Z�������� �� ����.��������  �~  �⏕�ʉݒP�ʔ� <BR>
     * �@@�@@�@@�@@���v�Z���ʂ͏����_�ȉ��؂�̂� <BR>
     * <BR>
     * <BR>
     * �E�⏕�ʉݒP�ʔ� �F �擾�����O��MMF�בփ��[�g.�⏕�ʉݒP�ʔ� <BR>
     * �E�בփ��[�g <BR>
     * �@@�@@�����敪 �� ���t�̏ꍇ �F �擾�����O��MMF�בփ��[�g.TTS<BR>
     * �@@�@@�����敪 �� ���̏ꍇ �F �擾�����O��MMF�בփ��[�g.TTB<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * �����敪 <BR>
     * <BR>
     * �P�F���t <BR>
     * �Q�F��� <BR>
     * �R�F�抷 <BR>
     * �S�F��W<BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@param l_strSettleDiv - (���ϕ��@@)<BR>
     * ���ϕ��@@<BR>
     * <BR>
     * �P�F�~��<BR>
     * �Q�F�O��<BR>
     * @@param l_mutualFundProduct - (�g�����M����)<BR>
     * �g�����M��<BR>
     * @@param l_estimatedPrice - (�T�Z��n����I�u�W�F�N�g)<BR>
     * �T�Z��n����I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    public void calcFrgnMmfEstimatedQty(
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strSettleDiv,
        WEB3MutualFundProduct l_mutualFundProduct,
        WEB3MutualFundEstimatedPrice l_estimatedPrice) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimatedBuyQty("
            + "String, double, String, WEB3MutualFundProduct, WEB3MutualFundEstimatedPrice)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFundProduct == null || l_estimatedPrice == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j����.�g�����M����.get�O��MMF�בփ��[�g()���R�[������B
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams =
            l_mutualFundProduct.getFrgnMmfExchangeRate();

        //��������
        BigDecimal l_bdOrderQuantity = new BigDecimal(String.valueOf(l_dblOrderQuantity));

        //�⏕�ʉݒP�ʔ�F�擾�����O��MMF�בփ��[�g.�⏕�ʉݒP�ʔ�
        BigDecimal l_bdSubCurrencyRatio =
            new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getSubCurrencyRatio()));

        //�בփ��[�g
        BigDecimal l_bdExchangeRate = new BigDecimal("1");

        //  �����敪 ��  ���t�̏ꍇ
        if (WEB3MFProcessDivDef.BUY.equals(l_strProcessDiv))
        {
            //�בփ��[�g:�擾�����O��MMF�בփ��[�g.TTS
            l_bdExchangeRate =
                new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getTtSellingRate()));
        }
        //�����敪 ��  ���̏ꍇ
        else if (WEB3MFProcessDivDef.SELL.equals(l_strProcessDiv))
        {
            //�בփ��[�g:�擾�����O��MMF�בփ��[�g.TTB
            l_bdExchangeRate =
                new BigDecimal(String.valueOf(l_frgnMmfExchangeRateParams.getTtBuyingRate()));
        }

        //�T�Z��������
        BigDecimal l_bdEstimatedQty = new BigDecimal("0");

        //�Q�j�T�Z�����������Z�o����B
        //  �Q-�P�j  ����.���ϕ��@@ = "�~��" �̏ꍇ
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
        {
            //�T�Z�������� �� ����.�������ʁ~�⏕�ʉݒP�ʔ�^�בփ��[�g
            //���v�Z���ʂ͏����_�ȉ��؂�̂�
            l_bdEstimatedQty = l_bdOrderQuantity.multiply(l_bdSubCurrencyRatio).divide(
                l_bdExchangeRate, 0, BigDecimal.ROUND_FLOOR);
        }

        //  �Q-�Q�j  ����.���ϕ��@@  != "�~��" �̏ꍇ
        else
        {
            //�T�Z�������� �� ����.��������  �~  �⏕�ʉݒP�ʔ�
            //���v�Z���ʂ͏����_�ȉ��؂�̂�
            l_bdEstimatedQty = l_bdOrderQuantity.multiply(l_bdSubCurrencyRatio).setScale(
                0, BigDecimal.ROUND_FLOOR);
        }
        l_estimatedPrice.setEstimatedQty(l_bdEstimatedQty.doubleValue());

        log.exiting(STR_METHOD_NAME);
    }
}
@
