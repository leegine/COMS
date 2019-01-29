head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�|�W�V�����w���p�[(WEB3IfoPositionManagerHelper.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/20 �����(���u)�V�K�쐬
Revesion History : 2007/06/08 �юu��(���u)�d�l�ύX ���f��643,734 DB�X�V�d�l167
Revesion History : 2008/03/13 ����(���u) ���f�� 824
Revesion History : 2008/08/18 ���z(���u) IFO�����_�Ή�
Revesion History : 2008/08/19 ���z(���u) �d�l�ύX ���f��No.902
*/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderExecutionImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoPositionManagerHelper;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�|�W�V�����w���p�[)<BR>
 * �敨OP�|�W�V�����w���p�[�N���X<BR>
 * �|�W�V�����}�l�[�W���w���p�[�N���X�B<BR>
 * �c���X�V�̎葱�����s���B<BR>
 * @@author �����(���u)
 * @@version 1.0
 */

public class WEB3IfoPositionManagerHelper extends IfoPositionManagerHelper
{

    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoPositionManagerHelper.class);

    /**
     * �R���X�g���N�^�B
     *
     * @@param l_tradingModuleType - ���i�^�C�v<BR>
     * @@roseuid 40C0750C0186
     */
    public WEB3IfoPositionManagerHelper(ProductTypeEnum l_tradingModuleType)
    {
        super(l_tradingModuleType);
    }

	/**
	 * (set�����To�g�����U�N�V�����i�V�K���̏ꍇ�ɌĂ΂��j)<BR>
	 * <BR>
	 * �isetExecutionInfoToMarketOrderedTrans�̃I�[�o�[���C�h�j<BR>
	 * <BR>
	 * ���f�[�^���g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�ɃZ�b�g����B<BR>
	 * ���V�K���Ŏg�p<BR>
	 * <BR>
	 * setExecutionInfoToMarketOrderedTrans(������薾��Params,���,�����P��Row,����ID)���R�[������B
	 * <BR>
	 * [setExecutionInfoToMarketOrderedTrans()�Ɏw�肷�����]<BR>
	 *   ������薾��Params:�@@����.������薾��Params<BR>
	 *   ���F�@@����.���<BR>
	 * �@@�����P��Row�F�@@����.�����P��Row<BR>
	 * �@@����ID�F�@@0<BR>
	 * <BR>
	 * @@param l_ifoFinTransactionParams - �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g<BR>
	 * @@param l_ifoOrderExecution - ���I�u�W�F�N�g<BR>
	 * @@param l_ifoOrderUnitRow - �����P�� �s�I�u�W�F�N�g<BR>
	 * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
	 * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	 * @@roseuid 40735F710281
	 */
	protected void setExecutionInfoToMarketOrderedTrans(
		IfoFinTransactionParams l_ifoFinTransactionParams,
		IfoOrderExecution l_ifoOrderExecution,
		IfoOrderUnitRow l_ifoOrderUnitRow)
		throws DataQueryException, DataNetworkException,
			RuntimeSystemException
	{
		this.setExecutionInfoToMarketOrderedTrans(
				l_ifoFinTransactionParams,
				l_ifoOrderExecution,
				l_ifoOrderUnitRow,
				0
				);
	}
	
    /**
	 * (set�����To�g�����U�N�V�����i�ԍς̏ꍇ�ɌĂ΂��j)<BR>
     * <BR>
     * �isetExecutionInfoToMarketOrderedTrans�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * ���f�[�^���g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�ɃZ�b�g����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�c���jset�����To�g�����U�N�V�����v�Q�ƁB<BR>
     * <BR>
	 * @@param l_ifoFinTransactionParams - �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g<BR>
     * @@param l_ifoOrderExecution - ���I�u�W�F�N�g<BR>
     * @@param l_ifoOrderUnitRow - �����P�� �s�I�u�W�F�N�g<BR>
     * @@param l_contractId - ����ID
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
     */
    protected void setExecutionInfoToMarketOrderedTrans(
        IfoFinTransactionParams l_ifoFinTransactionParams,
        IfoOrderExecution l_ifoOrderExecution,
        IfoOrderUnitRow l_ifoOrderUnitRow,
        long l_contractId)
        throws DataQueryException, DataNetworkException,
            RuntimeSystemException
    {
        final String STR_METHOD_NAME = "setExecutionInfoToMarketOrderedTrans";
        log.entering(STR_METHOD_NAME);

        try
        {
            // 1.�X�[�p�[�N���X�̏������R�[������B
            super.setExecutionInfoToMarketOrderedTrans(
                l_ifoFinTransactionParams,
                l_ifoOrderExecution,
                l_ifoOrderUnitRow);
            
            WEB3IfoPersistentDataManager l_persistentDataManager =
                new WEB3IfoPersistentDataManager();

            //�����P�ʂh�c�F�@@�����P��Params.getOrderUnitId()
            long l_lngOrderUnitId = l_ifoOrderUnitRow.getOrderUnitId();

            // 2.���꒍���Ɋ֘A����A���ɖ��ς̎�����薾�ׂ����X�g�Ŏ擾����B
            List l_lisRet =
                l_persistentDataManager.getFinTransactionDetailForOrderUnit(
                    l_lngOrderUnitId);

            // 3.�擾����������薾�׃��X�g�ɗv�f�����邩�𔻒肷��B
            //   �|�Ȃ��ꍇ�́A�ȍ~�̏������s�킸�ɏ������I������B
            if (l_lisRet.isEmpty())
            {
                //������肪�Ȃ��ꍇ�iisEmpty() == true�j
                //�|�����̎�����薾��Params���P�Ԗڂ̗v�f�iindex=0�j�Ɋi�[����size1�̔z
                l_lisRet = new ArrayList();
                l_lisRet.add(l_ifoFinTransactionParams);
            }
            else
            {
                // 4.�����̎�����薾��Params�I�u�W�F�N�g���A
                //   get������薾��ForOrderUnit()�Ŏ擾�������X�g�̖����ɒǉ�����B
                l_lisRet.add(l_ifoFinTransactionParams);
            }

            // 5.�z��ɕϊ�����B
            IfoFinTransactionParams[] l_ifoFinTransactionParamses =
                new IfoFinTransactionParams[l_lisRet.size()];
            l_lisRet.toArray(l_ifoFinTransactionParamses);

            // 6.�敨�̓��v��ԍ�(�萔�������Ȃ�)�ȊO�̏ꍇ�A�萔���̈����s��
            ConsolidatedCommissionInfo l_consolidatedCommissionInfo = null;
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoBizLogicProvider l_ifoBizLogicProvider =
                (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            l_consolidatedCommissionInfo = l_ifoBizLogicProvider.calcCommission(l_ifoFinTransactionParamses);

            // 7.������薾��Params���X�g�̗v�f��LOOP
            for (int i = 0; i < l_ifoFinTransactionParamses.length; i++)
            {
                // ����̌v�Z���ʂ�������薾��Params�ɃZ�b�g����B
                
                // 8.calc�萔���i���j()���\�b�h�̖߂�l���A
                //   �w��v�f�ԍ��̈ϑ��萔�����擾����B
                double l_dblCommission = l_consolidatedCommissionInfo.getCommission(i);
                
                // 9.�ϑ��萔�����g�����U�N�V�����i������薾�ׁj�ɃZ�b�g����B
                l_ifoFinTransactionParamses[i].setCommissionFee(l_dblCommission);
                
                // 10.calc�萔���i���j()���\�b�h�̖߂�l���A
                //    �w��v�f�ԍ��̈ϑ��萔������ł��擾����B
                double l_dblSalesTax = l_consolidatedCommissionInfo.getSalesTax(i);
                
                // 11.�ϑ��萔������ł��g�����U�N�V�����i������薾�ׁj�ɃZ�b�g����
                l_ifoFinTransactionParamses[i].setCommissionFeeTax(l_dblSalesTax);

                //set���z(IfoFinTransactionParams)
                setMarginNetAmount(l_ifoFinTransactionParamses[i]);

                Map l_mapFinTransaction = new Hashtable();
                l_mapFinTransaction.put("commission_fee",Double.toString(l_ifoFinTransactionParamses[i].getCommissionFee()));
                l_mapFinTransaction.put("commission_fee_tax",Double.toString(l_ifoFinTransactionParamses[i].getCommissionFeeTax()));
                l_mapFinTransaction.put("capital_gain",new Double(l_ifoFinTransactionParamses[i].getCapitalGain()));
                l_mapFinTransaction.put("net_amount",new Double(l_ifoFinTransactionParamses[i].getNetAmount()));
                l_mapFinTransaction.put("last_updated_timestamp",GtlUtils.getSystemTimestamp());

                // 12.���b�Z�[�W (*)�ŏI�v�f�łȂ��ꍇ�A�X�V�����{����B
                if ( i == l_ifoFinTransactionParamses.length - 1)
                {
                    continue;
                }

                // 13.�g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g��DB�ɍX�V����
                l_persistentDataManager.updateFinTransaction(l_ifoFinTransactionParamses[i],l_mapFinTransaction);

                //notifyUpdateGtl(IfoFinTransactionParams)
                // --- �g�����U�N�V�����i������薾�ׁj�s�����ɁA
                // --- �g�����U�N�V�����i�ڋq���薾�ׁj���X�V����B
                notifyUpdateGtl(l_ifoFinTransactionParamses[i]);
            }
        }
        catch (DataException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                STR_METHOD_NAME);
        }
        

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (set���z)<BR>
     * <BR>
     * �isetMarginNetAmount�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�Ɉȉ��̋��z���ڂ��Z�b�g����B<BR>
     * <BR>
     * �|��n���z<BR>
     * �|���n�v���z�i�]�����v�j<BR>
     * <BR>
     * �� �V�K���̏ꍇ�́A����0�B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�c���jset���z�v�Q�ƁB<BR>
     * @@param l_ifoFinTransactionParams - ����g�����U�N�V���� �s�I�u�W�F�N�g<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataException
     * @@roseuid 40736407004F
     */
    protected void setMarginNetAmount(
        IfoFinTransactionParams
        l_ifoFinTransactionParams)
        throws DataException
    {
        final String l_strMethodName = "setMarginNetAmount";
        log.entering(l_strMethodName);

        // 1.�����h�c���擾����
        long l_lngProductId = l_ifoFinTransactionParams.getProductId();

        // 2.�敨OP�����I�u�W�F�N�g�𐶐�����B
        WEB3IfoProductImpl l_web3IfoProductImpl =
            new WEB3IfoProductImpl(l_lngProductId);

        // 3.�g�����U�N�V�����^�C�v���擾����B
        FinTransactionType l_finTransactionType =
            l_ifoFinTransactionParams.getFinTransactionType();
        log.debug("�g�����U�N�V�����^�C�v = " + l_finTransactionType);

        // 4.�ϑ��萔�����擾����B
        double l_dblCommissionFee =
            l_ifoFinTransactionParams.getCommissionFee();
        log.debug("�ϑ��萔�� = " + l_dblCommissionFee);

        // 5.�ϑ��萔������ł��擾����B
        double l_dblCommissionFeeTax =
            l_ifoFinTransactionParams.getCommissionFeeTax();
        log.debug("�ϑ��萔������� = " + l_dblCommissionFeeTax);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoBizLogicProvider l_web3IfoBizLogicProvider =
            (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();

        WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        // �����Fcalc��n���()�Ɏw�肷�����1
        SideEnum l_dealing = null;

        // ����p�v�Z�p����Fcalc��n���()�Ɏw�肷�����2
        double l_dblExpenseCalculationAmount;

        // �ϑ��萔���Fcalc��n���()�Ɏw�肷�����3
        double l_dblConsignmentCommission = l_dblCommissionFee;

        // �ϑ��萔������ŁFcalc��n���()�Ɏw�肷�����4
        double l_dblCommConsumptionTax = l_dblCommissionFeeTax;

        // ����g�����U�N�V����.getFinTransactionType()==�h�����ԍώ���i���ԍρj�h
        // �܂��́A�h�V�K��������h�̏ꍇ�A�|�h���h�iSideEnum.SELL�j
        if(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_OPEN .equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_OPEN.equals(
            l_finTransactionType))
        {
            log.debug("����g�����U�N�V����.getFinTransactionType()==�h�����ԍώ���i���ԍρj�h �܂��́A�h�V�K��������h�̏ꍇ");
            l_dealing = SideEnum.SELL;
        }

        // ����g�����U�N�V����.getFinTransactionType()==�h�����ԍώ���i���ԍρj�h
        // �܂��́A�h�V�K��������h�̏ꍇ�A�|�h���h�iSideEnum.BUY�j
        if (FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE.equals(
            l_finTransactionType) ||
            FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_OPEN.equals(
            l_finTransactionType))
        {
            log.debug("����g�����U�N�V����.getFinTransactionType()==�h�����ԍώ���i���ԍρj�h �܂��́A�h�V�K��������h�̏ꍇ");
            l_dealing = SideEnum.BUY;
        }
        log.debug("l_dealing = " + l_dealing);

        //����g�����U�N�V����.��萔�ʁ~����g�����U�N�V����.���P��
        WEB3IfoTradedProductImpl l_tradedProductImpl = null;
        try
        {
            l_tradedProductImpl = (WEB3IfoTradedProductImpl)l_productManagerImpl.getTradedProduct(l_lngProductId,l_ifoFinTransactionParams.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + l_strMethodName, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + l_strMethodName,
                l_nfe.getMessage(),
                l_nfe);
        }
        BigDecimal l_bdQuantity = new BigDecimal("" + l_ifoFinTransactionParams.getQuantity());
        BigDecimal l_bdPrice = new BigDecimal("" + l_ifoFinTransactionParams.getPrice());
        BigDecimal l_bdUnitSize = new BigDecimal("" + l_tradedProductImpl.getUnitSize());
        l_dblExpenseCalculationAmount = l_bdQuantity.multiply(l_bdPrice).multiply(l_bdUnitSize).doubleValue();
        log.debug("l_dblExpenseCalculationAmount = "  + l_dblExpenseCalculationAmount);

        // 6.������̎�n������v�Z����B
        double l_dblNetAmountKY = l_web3IfoBizLogicProvider.calcDeliveryAmount(
            l_dealing,
            l_dblExpenseCalculationAmount,
            l_dblConsignmentCommission,
            l_dblCommConsumptionTax);


        // 7.�I�v�V�����������𔻒肷��B
        boolean l_blnFlag = l_web3IfoProductImpl.isOptionProduct();
        log.debug("l_blnFlag = "  + l_blnFlag);
        // 8.���b�Z�[�W (*) is�I�v�V��������() == true�̏ꍇ�������{
        if (l_blnFlag)
        {

            if (FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE.equals(
                l_finTransactionType) ||
                FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN.equals(
                l_finTransactionType) ||
                FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE.equals(
                l_finTransactionType) ||
                FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_OPEN.equals(
                l_finTransactionType))
            {
                l_ifoFinTransactionParams.setNetAmount(
                    new BigDecimal(l_dblNetAmountKY + "").negate().doubleValue());
            }
            else
            {
                // 9.��n������Z�b�g����
                l_ifoFinTransactionParams.setNetAmount(l_dblNetAmountKY);
            }

        }
        else
        {
            // �敨�̏ꍇ�͏����l�Ƃ���0���Z�b�g����
            l_ifoFinTransactionParams.setNetAmount(0);
        }


        // 10.�g�����U�N�V�����J�e�S�����擾����B
        FinTransactionCateg l_finTransactionCateg =
            l_ifoFinTransactionParams.getFinTransactionCateg();

        // �ԍώ���̏ꍇ,�]�����v���Z�b�g����
        if (FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE .equals(l_finTransactionCateg) ||
            FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE .equals(l_finTransactionCateg))
        {
            // 11.���ʋ��z���擾����B
            double l_dblImportedPaidValue =
                l_ifoFinTransactionParams.getImportedPaidValue();
            log.debug("���ʋ��z = " + l_dblImportedPaidValue);

            // 12.���ϑ��萔�����擾����B
            double l_dblImportedSetupFee =
                l_ifoFinTransactionParams.getImportedSetupFee();
            log.debug("���ϑ��萔�� = " + l_dblImportedSetupFee);

            // 13.���ϑ��萔������ł��擾����B
            double l_dblImportedSetupFeeTax =
                l_ifoFinTransactionParams.getImportedSetupFeeTax();
            log.debug("���ϑ��萔������� = " + l_dblImportedSetupFeeTax);

            SideEnum l_sideEnumForContract = null;
            log.debug("l_finTransactionType = " + l_finTransactionType);
            //����g�����U�N�V����.getFinTransactionType()==�h�����ԍώ���i���ԍρj�h�̏ꍇ�A�h���h�iSideEnum.BUY�j
            if(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE.equals(l_finTransactionType)
                    || FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE.equals(l_finTransactionType))
            {

                l_sideEnumForContract = SideEnum.BUY;
            }
            //����g�����U�N�V����.getFinTransactionType()==�h�����ԍώ���i���ԍρj�h�̏ꍇ�A�h���h�iSideEnum.SELL�j
            else if (FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE.equals(l_finTransactionType)
                || FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE.equals(l_finTransactionType))
            {

                l_sideEnumForContract = SideEnum.SELL;
            }
            log.debug("l_sideEnumForContract = " + l_sideEnumForContract);
            // 14.��n����i���j���v�Z����B
            double l_dblNetAmountKenn = l_web3IfoBizLogicProvider.calcDeliveryAmount(
                l_sideEnumForContract,
                l_dblImportedPaidValue,
                l_dblImportedSetupFee,
                l_dblImportedSetupFeeTax);

            log.debug("l_dblNetAmountKenn = " + l_dblNetAmountKenn);

            // 15.�]�����v���Z�b�g����B

            // ���ԍς̏ꍇ�igetFinTransactionType()==�h�������ԍρh�j�F
            //   set ������̎�n����|��n����i���j
            BigDecimal l_bdNetAmountKY = new BigDecimal("" + l_dblNetAmountKY);
            BigDecimal l_bdNetAmountKenn = new BigDecimal("" + l_dblNetAmountKenn);
            if(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE.equals(l_finTransactionType)
                || FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE.equals(l_finTransactionType))
            {
                log.debug("���ԍς̏ꍇ ����̎�n����|��n����i���j");
                l_ifoFinTransactionParams.setCapitalGain(
                        l_bdNetAmountKY.subtract(l_bdNetAmountKenn).doubleValue());

            }

            // ���ԍς̏ꍇ�igetFinTransactionType()==�h�������ԍρh�j�F
            //   ��n����i���j�|������̎�n���
            if (FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE.equals(l_finTransactionType)
                || FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE.equals(l_finTransactionType))
            {
                log.debug("���ԍς̏ꍇ ��n����i���j�|������̎�n��� ");
                l_ifoFinTransactionParams.setCapitalGain(
                        l_bdNetAmountKenn.subtract(l_bdNetAmountKY).doubleValue());
            }
            log.debug("l_ifoFinTransactionParams.getCapitalGain() = " + l_ifoFinTransactionParams.getCapitalGain());

            if(!l_blnFlag)
            {
                // �敨�����̏ꍇ�̂ݎ��{
                // 16.��n������Z�b�g����B
                log.debug("�敨�iis�I�v�V��������() == false�j�̏ꍇ");
                l_ifoFinTransactionParams.setNetAmount(l_ifoFinTransactionParams.getCapitalGain());
            }
        }

        log.exiting(l_strMethodName);

    }

    /**
     * (update�V�K������From�g�����U�N�V����)<BR>
     * <BR>
     * �iupdateContractOpenFromMarketOrderedTrans�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �g�����U�N�V�����f�[�^�����ɁA���ʍs�̌������X�V����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�c���jupdate�V�K������From�g�����U�N�V�����v�Q�ƁB<BR>
     * <BR>
     * @@param l_ifoContractParams - ���ʍs�I�u�W�F�N�g<BR>
     *
     * @@param l_ifoFinTransactionParams - (�g�����U�N�V�����i������薾�ׁjParams)<BR>
     * <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataException;
     * @@roseuid 4075216D03A9
     */
    protected void updateContractOpenFromMarketOrderedTrans(
        IfoContractParams l_ifoContractParams,
        IfoFinTransactionParams l_ifoFinTransactionParams)
        throws RuntimeSystemException, DataException
    {
        final String l_strMethodName =
            "updateContractOpenFromMarketOrderedTrans";
        log.entering(l_strMethodName);

        //getOpenDateIsSet( )
        // --- ����Params�Ɍ������Z�b�g����Ă��邩�`�F�b�N����B
        boolean l_blnOpenDateIsSet = l_ifoContractParams.getOpenDateIsSet();

        //�������Z�b�g����ĂȂ��ꍇ(���ʂ̐V�KINSERT��)
        // ���ʃI�u�W�F�N�g�l���Z�b�g
        if (!l_blnOpenDateIsSet)
        {

            //setAccountId(arg0 : long)
            l_ifoContractParams.setAccountId(
                l_ifoFinTransactionParams.getAccountId());

            //setSubAccountId(arg0 : long)
            l_ifoContractParams.setSubAccountId(
                l_ifoFinTransactionParams.getSubAccountId());

            //setContractType(arg0 : ContractTypeEnum)
            l_ifoContractParams.setContractType(
                ContractTypeEnum.getContractType(
                    l_ifoFinTransactionParams.getFinTransactionType()));

            //setMarketId(arg0 : long)
            l_ifoContractParams.setMarketId(
                l_ifoFinTransactionParams.getMarketId());

            //setProductId(arg0 : long)
            l_ifoContractParams.setProductId(
                l_ifoFinTransactionParams.getProductId());

            //setProductType(arg0 : ProductTypeEnum)
            l_ifoContractParams.setProductType(
                l_ifoFinTransactionParams.getProductType());

            //setContractPrice(arg0 : double)
            l_ifoContractParams.setContractPrice(
                l_ifoFinTransactionParams.getPrice());

            //setOriginalContractPrice(arg0 : double)
            l_ifoContractParams.setOriginalContractPrice(
                l_ifoFinTransactionParams.getPrice());

            //setOpenDate(arg0 : �_���r���[::java::sql::Timestamp)
            // �������Z�b�g����B
            IfoOrderExecutionImpl l_ifoOrderExecution =
                new IfoOrderExecutionImpl(
                    l_ifoFinTransactionParams.getOrderExecutionId());
            l_ifoContractParams.setOpenDate(
                WEB3DateUtility.toDay(l_ifoOrderExecution.getExecutionTimestamp()));

            //setCloseDate(arg0 : Date)
            Date l_closeDate = IfoTradedProductDao.findRowByProductIdMarketId(
                l_ifoContractParams.product_id,
                l_ifoContractParams.market_id).getLastTradingDate();
            l_ifoContractParams.setCloseDate(l_closeDate);

            //setUnitSize(arg0 : double)
            // 1�P�ʓ���搔���Z�b�g����B
            double l_dblUnitSize =
                IfoTradedProductDao.findRowByProductIdMarketId(
                    l_ifoContractParams.product_id,
                    l_ifoContractParams.market_id).getUnitSize();
            l_ifoContractParams.setUnitSize(l_dblUnitSize);

            //setDeliveryDate(arg0 : Date)  ��n��
            l_ifoContractParams.setDeliveryDate(l_ifoFinTransactionParams.getDeliveryDate());

            // getOrderUnit()
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_orderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = null;
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManagerImpl.getOrderUnit(
                    l_ifoFinTransactionParams.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("Error In Method: " + l_strMethodName, l_nfe);
                log.exiting(l_strMethodName);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + l_strMethodName,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

            //setSessionType() ����敪
            l_ifoContractParams.setSessionType(l_ifoOrderUnitRow.getSessionType());
        }
        //setOriginalQuantity(arg0 : double)
        l_ifoContractParams.setOriginalQuantity(
            l_ifoContractParams.getOriginalQuantity() + l_ifoFinTransactionParams.getQuantity());

        //setQuantity(arg0 : double)
        l_ifoContractParams.setQuantity(
            l_ifoContractParams.getQuantity() + l_ifoFinTransactionParams.getQuantity());

		//setSetupFee(arg0 : double)
		l_ifoContractParams.setSetupFee(
			l_ifoContractParams.getSetupFee() +
			l_ifoFinTransactionParams.getCommissionFee()
			);

		//setSetupFeeTax(arg0 : double)
		l_ifoContractParams.setSetupFeeTax(
			l_ifoContractParams.getSetupFeeTax() +
			l_ifoFinTransactionParams.getCommissionFeeTax()
			);

        //setLastUpdatedTimestamp()
        l_ifoContractParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

		try
		{
			//getOrderId() --- �����h�c���擾����
			long l_lngOrderId = l_ifoFinTransactionParams.getOrderId();

			//get���ʂh�cByOrder(long) --- �����ɊY�����錚�ʂh�c��z��Ŏ擾����B

			WEB3IfoPersistentDataManager l_persistentDataManager =	
				new WEB3IfoPersistentDataManager();
			long[] l_lngReturns =
				l_persistentDataManager.getContractIDByOrder(l_lngOrderId);
		
			int l_intSize =l_lngReturns.length;
			long[] l_lngContractIDs;
			l_lngContractIDs =
				new long[l_intSize];
			for (int i = 0; i < l_intSize; i++)
			{
				l_lngContractIDs[i] =l_lngReturns[i];
			}
			
			//����Params.����ID��0�̏ꍇ�A
			//�擾����ID�̔z��Ɉ����̌���Params.����ID��ǉ�����B
			if(l_ifoContractParams.getContractId() != 0)
			{
				l_intSize++;
				l_lngContractIDs = new long[l_intSize];
				for (int i = 0; i < l_intSize; i++)
				{
					if (i == l_intSize-1)
					{
						l_lngContractIDs[i] =
							l_ifoContractParams.getContractId();
					}
					else
					{
						l_lngContractIDs[i] = l_lngReturns[i];
					}
				}
			}



            //���ʂh�c�z��̗v�f��LOOP
            for (int i = 0; i < l_intSize; i++)
            {
                //�i����Params.���ʂh�c != ���ʂh�c[index]�j�̏ꍇ�̂ݎ��{
                if (l_ifoContractParams.getContractId() != l_lngContractIDs[i])
                {
                    WEB3IfoContractImpl l_ifocontractImpl = null;
                    l_ifocontractImpl = new WEB3IfoContractImpl(l_lngContractIDs[i]);
                    //getDataSourceObject() --- ���ʍs�I�u�W�F�N�g���擾����B
					IfoContractParams l_updateContact = new IfoContractParams(
                            (IfoContractRow)l_ifocontractImpl.getDataSourceObject());
					//set���ʋ��z(����Params,�g�����U�N�V�����i������薾�ׁjParams)
                    setContractAmount(l_updateContact, null);

                    l_persistentDataManager.updateContractByTrans(l_updateContact);
                }
				//�i����Params.���ʂh�c == ���ʂh�c[index]�j�̏ꍇ�̂ݎ��{
                if (l_ifoContractParams.getContractId() == l_lngContractIDs[i])
                {
					//set���ʋ��z(����Params,�g�����U�N�V�����i������薾�ׁjParams)
					setContractAmount(l_ifoContractParams, l_ifoFinTransactionParams);
                }
                
             }
        }
        catch (WEB3BaseException l_webex)
        {
            log.error(l_strMethodName,l_webex);
            throw new WEB3BaseRuntimeException(l_webex.getErrorInfo(),l_strMethodName);
        }

        log.exiting(l_strMethodName);
    }

	/**
	 * (update�g�����U�N�V����)<BR>
	 * <BR>
	 * �萔�����v�Z�i������j�����{���A�g�����U�N�V�����f�[�^���X�V����B<BR>
	 * <BR>
	 * �V�[�P���X�}<BR>
	 * �u�i�敨OP�c���jupdate�g�����U�N�V�����v�Q�ƁB<BR>
	 * @@param l_lngOrderUnitID - �����P�ʂh�c<BR>
	 * @@throws webbroker3.common.WEB3BaseException
	 * @@roseuid 40999828002D
	 */
	public void updateTransaction(long l_lngOrderUnitID)
		throws WEB3BaseException
	{
		final String l_strMethodName = "updateTransaction";
		log.entering(l_strMethodName);

		WEB3IfoPersistentDataManager l_persistentDataManager =
			new WEB3IfoPersistentDataManager();

		//		�^�C���X�^���v�ɐݒ肳��Ă����t�������擾���� 
		//�@@		 ?ThreadLocalSystemAttributesRegistry.getAttribute( ) 
		//		  �ݒ�L�[�F ������ԊǗ�.TIMESTAMP_TAG 

		Timestamp l_timestamp =
			(Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

		//		�����ԗp�^�C���X�^���v��������Ԃ��擾���� 
		//�@@		 ?ThreadLocalSystemAttributesRegistry.getAttribute( ) 
		//		  �ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�C�X.REAL_TIMESTAMP 

		Timestamp l_realTimestamp =
			(Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
		//		�����Ԃ��ݒ肳��Ă���ꍇ(REAL_TIMESTAMP != null) 
		if (l_realTimestamp != null)
		{
			//			��t�����̃��Z�b�g�@@ 
			//�@@�@@			  ?ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�t������ 
			//�@@�@@�@@			   �擾���������Ԃ��Z�b�g����B 
			//�@@			   �ݒ�L�[�F ������ԊǗ�.TIMESTAMP_TAG 

			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_realTimestamp);
		}

		try
		{
			// get������薾��ForOrderUnit(long)
			//  --- ���꒍���Ɋ֘A����A���ɖ��ς̎�����薾�ׂ����X�g�Ŏ擾����B
			List l_lisRet =
				l_persistentDataManager.getFinTransactionDetailForOrderUnit(
					l_lngOrderUnitID);

			FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
			TradingModule l_tradingModule =
				l_finApp.getTradingModule(ProductTypeEnum.IFO);
			WEB3IfoBizLogicProvider l_ifoBizLogicProvider =
				(WEB3IfoBizLogicProvider) l_tradingModule.getBizLogicProvider();

			// getOrderUnit(long)
			// --- �����P�ʃI�u�W�F�N�g���擾����B
			WEB3OptionOrderManagerImpl l_orderManagerImpl =
				(WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
			OrderUnit l_orderUnit =
				l_orderManagerImpl.getOrderUnit(l_lngOrderUnitID);

			// getOrderCateg()
			// --- �����J�e�S�����擾����B
			OrderCategEnum l_orderCategEnum = l_orderUnit.getOrderCateg();

			if (l_lisRet != null && !l_lisRet.isEmpty())
			{
				// toArray() --- �z��ɕϊ�����B
				IfoFinTransactionParams[] l_ifoFinTransactionParams =
					new IfoFinTransactionParams[l_lisRet.size()];
				l_lisRet.toArray(l_ifoFinTransactionParams);

				//�����P��.�����J�e�S�� == �h�敨�ԍϒ����h�̏ꍇ�A���{
				if (OrderCategEnum
					.IDX_FUTURES_CLOSE
					.equals(l_orderUnit.getOrderCateg()))
				{
					IfoContractParams l_contractParams =
						this.getContract(l_ifoFinTransactionParams[0]);
					IfoOrderExecution l_ifoOrderExecution =
						(
							IfoOrderExecution) l_orderManagerImpl
								.getOrderExecution(
							l_ifoFinTransactionParams[0].getOrderExecutionId());
					//���ʂ̌��� == �����̖��� �̏ꍇ�i���v��ԍς̏ꍇ�j
					if (l_contractParams != null
						&& WEB3DateUtility.compareToDay(
							l_contractParams.getOpenDate(),
							l_ifoOrderExecution.getExecutionTimestamp())
							== 0)
					{
						WEB3GentradeAccountManager l_accountManager =
							(WEB3GentradeAccountManager) l_finApp
								.getAccountManager();
						SubAccount l_subAccount =
							l_accountManager.getSubAccount(
								l_orderUnit.getAccountId(),
								l_orderUnit.getSubAccountId());
						Institution l_institution =
							l_subAccount.getInstitution();
						InstitutionRow l_institutionRow =
							(InstitutionRow) l_institution
								.getDataSourceObject();

					}
				}

				// �萔���̈����s��
				ConsolidatedCommissionInfo l_consolidatedCommissionInfo = null;
				l_consolidatedCommissionInfo =
					l_ifoBizLogicProvider.calcCommission(
						l_ifoFinTransactionParams);

				// ������薾��Params���X�g�̗v�f��LOOP
				for (int i = 0; i < l_ifoFinTransactionParams.length; i++)
				{
					// ����̌v�Z���ʂ�������薾��Params�ɃZ�b�g����B
                    // calc�萔���i���j()���\�b�h�̖߂�l���A
                    //   �w��v�f�ԍ��̈ϑ��萔�����擾����B
                    double l_dblCommission =
                        l_consolidatedCommissionInfo.getCommission(i);

                    // �ϑ��萔�����g�����U�N�V�����i������薾�ׁj�ɃZ�b�g����B
                    l_ifoFinTransactionParams[i].setCommissionFee(
                        l_dblCommission);

                    // calc�萔���i���j()���\�b�h�̖߂�l���A
                    //    �w��v�f�ԍ��̈ϑ��萔������ł��擾����B
                    double l_dblSalesTax =
                        l_consolidatedCommissionInfo.getSalesTax(i);

                    // �ϑ��萔������ł��g�����U�N�V�����i������薾�ׁj�ɃZ�b�g����
                    l_ifoFinTransactionParams[i].setCommissionFeeTax(
                        l_dblSalesTax);

					// set���z(IfoFinTransactionParams)
					setMarginNetAmount(l_ifoFinTransactionParams[i]);

					Map l_mapFinTransaction = new Hashtable();
					l_mapFinTransaction.put(
						"capital_gain",
						new Double(
							l_ifoFinTransactionParams[i].getCapitalGain()));
					l_mapFinTransaction.put(
						"net_amount",
						new Double(
							l_ifoFinTransactionParams[i].getNetAmount()));
					l_mapFinTransaction.put(
						"commission_fee",
						new Double(
							l_ifoFinTransactionParams[i].getCommissionFee()));
					l_mapFinTransaction.put(
						"commission_fee_tax",
						new Double(
							l_ifoFinTransactionParams[i]
								.getCommissionFeeTax()));
					l_mapFinTransaction.put(
						"last_updated_timestamp",
						GtlUtils.getSystemTimestamp());

					// updateFinTransaction(IfoFinTransactionParams)
					// --- �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g��DB�ɍX�V����B
					l_persistentDataManager.updateFinTransaction(
						l_ifoFinTransactionParams[i],
						l_mapFinTransaction);

					// notifyUpdateGtl(IfoFinTransactionParams)
					// --- �g�����U�N�V�����i������薾�ׁj�s�����ɁA
					// --- �g�����U�N�V�����i�ڋq���薾�ׁj���X�V����B
					notifyUpdateGtl(l_ifoFinTransactionParams[i]);
				}
			}

			if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderCategEnum)
				|| (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderCategEnum)))
			{

				// �����h�c���擾����B
				long l_lngOrderId = l_orderUnit.getOrderId();

				// get���ʂh�cByOrder(long)
				// --- �����ɊY�����錚�ʂh�c��z��Ŏ擾����
				long[] l_lngContractIDs =
					l_persistentDataManager.getContractIDByOrder(l_lngOrderId);

				// ���ʂh�c�z��̗v�f��LOOP
				for (int j = 0; j < l_lngContractIDs.length; j++)
				{
					WEB3IfoContractImpl l_ifoContractImpl = null;
					l_ifoContractImpl =
						new WEB3IfoContractImpl(l_lngContractIDs[j]);

					//���ʍs�I�u�W�F�N�g���擾����B
					IfoContractParams l_ifoContractParams =
						new IfoContractParams(
							(IfoContractRow) l_ifoContractImpl
								.getDataSourceObject());

					// set���ʋ��z(IfoContractParams)
					setContractAmount(l_ifoContractParams, null);

					// updateContractByTrans(IfoContractParams)
					// --- ���ʂ��X�V����B
					l_persistentDataManager.updateContractByTrans(
						l_ifoContractParams);
				}
			}
		}
		catch (DataException l_ex)
		{
			log.error("Error In Method: " + l_strMethodName, l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + l_strMethodName,
				l_ex.getMessage(),
				l_ex);

		}
		catch (NotFoundException l_ex)
		{
			log.error("Error In Method: " + l_strMethodName, l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + l_strMethodName,
				l_ex.getMessage(),
				l_ex);

		}

		//		�����Ԃ��ݒ肳��Ă���ꍇ(REAL_TIMESTAMP != null) 
		if (l_realTimestamp != null)
		{
			//			��t�����̍ă��Z�b�g 
			//�@@�@@			  ?ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�t������ 
			//�@@�@@�@@�@@			�Ŏ擾�����^�C���X�^���v�̐ݒ�l���Z�b�g����B 
			//�@@			   �ݒ�L�[�F ������ԊǗ�.TIMESTAMP_TAG 
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_timestamp);
		}
		log.exiting(l_strMethodName);
	}

    /**
     * (set���ʋ��z)<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�c���jset���ʋ��z�v�Q�ƁB<BR>
     * @@param l_ifoContractParams - ����Params<BR>
	 * @@param l_ifoFinTransactionParams - �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException<BR>
     * @@roseuid 40999ECF03E6
     */
	protected void setContractAmount(
		IfoContractParams l_ifoContractParams,
		IfoFinTransactionParams l_ifoTransParams)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "setContractAmount()";
		log.entering(STR_METHOD_NAME);

		try
		{
			//�V�K���̎�����薾�׃I�u�W�F�N�g�̃��X�g���擾����B 
			WEB3IfoPersistentDataManager l_ifoPersistentDatamanager =
				new WEB3IfoPersistentDataManager();
			List l_lisOpenFinTransaction =
				l_ifoPersistentDatamanager.getFinTransactionDetailForOpenCont(
					l_ifoContractParams.getContractId());

			IfoFinTransactionParams[] l_ifoOpenFinTransactionParams =
				new IfoFinTransactionParams[l_lisOpenFinTransaction.size()];
			l_lisOpenFinTransaction.toArray(l_ifoOpenFinTransactionParams);

			// �ϑ��萔��
			double l_dblSetupFee = 0.0D;
            BigDecimal l_bdSetupFee = new BigDecimal("0");
			// �ϑ��萔�������
			double l_dblSetupFeeTax = 0.0D;
            BigDecimal l_bdSetupFeeTax = new BigDecimal("0");

			//�����̃g�����U�N�V����Params��null�łȂ��ꍇ�ɉ��Z�B		
			if (l_ifoTransParams != null)
			{
				// �ϑ��萔��
				l_dblSetupFee = l_ifoTransParams.getCommissionFee();
                l_bdSetupFee = new BigDecimal(l_dblSetupFee + "");
				// �ϑ��萔�������
				l_dblSetupFeeTax = l_ifoTransParams.getCommissionFeeTax();
                l_bdSetupFeeTax = new BigDecimal(l_dblSetupFeeTax + "");
			}

			for (int i = 0; i < l_ifoOpenFinTransactionParams.length; i++)
			{
                BigDecimal l_bdCommissionFee =
                    new BigDecimal(l_ifoOpenFinTransactionParams[i].getCommissionFee() + "");
                l_bdSetupFee = l_bdSetupFee.add(l_bdCommissionFee);
                l_dblSetupFee = l_bdSetupFee.doubleValue();

                BigDecimal l_bdCommissionFeeTax =
                    new BigDecimal(l_ifoOpenFinTransactionParams[i].getCommissionFeeTax() + "");
                l_bdSetupFeeTax = l_bdSetupFeeTax.add(l_bdCommissionFeeTax);
                l_dblSetupFeeTax = l_bdSetupFeeTax.doubleValue();
			}
			
			//�����̌���Params�ɊY�����ʂ̐V�K�����̈ϑ��萔�����Z�b�g����B
			l_ifoContractParams.setSetupFee(l_dblSetupFee);

			//�����̌���Params�ɊY�����ʂ̐V�K�����̈ϑ��萔������ł��Z�b�g����B
			l_ifoContractParams.setSetupFeeTax(l_dblSetupFeeTax);

			//�ԍς̎�����薾�׃I�u�W�F�N�g�̃��X�g���擾����B 
			List l_lisCloseFinTransaction =
				l_ifoPersistentDatamanager.getFinTransactionDetailForCloseCont(
					l_ifoContractParams.getContractId());

			IfoFinTransactionParams[] l_ifoCloseFinTransactionParams =
				new IfoFinTransactionParams[l_lisCloseFinTransaction.size()];
			l_lisCloseFinTransaction.toArray(l_ifoCloseFinTransactionParams);

			// �ԍώ��̌��ϑ��萔��
			double l_dblCloseSetupFee = 0.0D;
            BigDecimal l_bdCloseSetupFee = new BigDecimal("0");
			// �ԍώ��̌��ϑ��萔�������
			double l_dblCloseSetupFeeTax = 0.0D;
            BigDecimal l_bdCloseSetupFeeTax = new BigDecimal("0");
			// �ԍύςݐ��ʂ����������Ă����B
			double l_dblSettleQuantity = 0.0D;
            BigDecimal l_bdSettleQuantity = new BigDecimal("0");

            //����Params.���ʌ�����
            BigDecimal l_bdOriginalQuantity =
                new BigDecimal(l_ifoContractParams.getOriginalQuantity() + "");
            //����Params.���ϑ��萔��
            BigDecimal l_bdContractSetupFee = new BigDecimal("0");
            //����Params.���ϑ��萔�������
            BigDecimal l_bdContractSetupFeeTax = new BigDecimal("0");

			//(*)�擾�����ԍς̎�����薾�ז���LOOP����
			for (int i = 0; i < l_ifoCloseFinTransactionParams.length; i++)
			{
                //�ԍς̎�����薾��Params.��萔��
                BigDecimal l_bdQuantity =
                    new BigDecimal(l_ifoCloseFinTransactionParams[i].getQuantity() + "");

                //������薾��Params.���ϑ��萔�� =
                // (����Params.���ϑ��萔�� * �ԍς̎�����薾��Params.��萔��) / (����Params.���ʌ����� - ���ύςݐ���)(#2)
                //(#2)�����_�ȉ��͐؂�̂āB
                l_bdContractSetupFee =
                    new BigDecimal(l_ifoContractParams.getSetupFee() + "");
                l_bdCloseSetupFee =
                    l_bdContractSetupFee.multiply(l_bdQuantity).divide(
                        l_bdOriginalQuantity.subtract(l_bdSettleQuantity),
                        0,
                        BigDecimal.ROUND_FLOOR);

                l_dblCloseSetupFee = l_bdCloseSetupFee.doubleValue();

				l_ifoCloseFinTransactionParams[i].setImportedSetupFee(l_dblCloseSetupFee);

				//������薾��Params.���ϑ��萔������� =
				//�@@(����Params.���ϑ��萔������� * �ԍς̎�����薾��Params.��萔��) / (����Params.���ʌ����� - ���ύςݐ���)(#2)
                //(#2)�����_�ȉ��͐؂�̂āB
                l_bdContractSetupFeeTax =
                    new BigDecimal(l_ifoContractParams.getSetupFeeTax() + "");
                l_bdCloseSetupFeeTax =
                    l_bdContractSetupFeeTax.multiply(l_bdQuantity).divide(
                        l_bdOriginalQuantity.subtract(l_bdSettleQuantity),
                        0,
                        BigDecimal.ROUND_FLOOR);

                l_dblCloseSetupFeeTax = l_bdCloseSetupFeeTax.doubleValue();

				l_ifoCloseFinTransactionParams[i].setImportedSetupFeeTax(l_dblCloseSetupFeeTax);

				//������薾��Params.�X�V���t=���ݎ���
				l_ifoCloseFinTransactionParams[i].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
				
				//��n����A�]�����v�i���ϑ��v�j�̍Čv�Z���s���B
				setMarginNetAmount(l_ifoCloseFinTransactionParams[i]);
				
				//������薾�ׂ��X�V����B
				Map l_mapFinTransaction = new Hashtable();
				l_mapFinTransaction.put("imported_setup_fee",Double.toString(l_dblCloseSetupFee));
				l_mapFinTransaction.put("imported_setup_fee_tax",Double.toString(l_dblCloseSetupFeeTax));
				l_mapFinTransaction.put("capital_gain",Double.toString(l_ifoCloseFinTransactionParams[i].getCapitalGain()));
				l_mapFinTransaction.put("net_amount",Double.toString(l_ifoCloseFinTransactionParams[i].getNetAmount()));
				l_mapFinTransaction.put("last_updated_timestamp",l_ifoCloseFinTransactionParams[i].getLastUpdatedTimestamp());
				l_ifoPersistentDatamanager.updateFinTransaction(
					l_ifoCloseFinTransactionParams[i],l_mapFinTransaction);

				//�g�����U�N�V�����i������薾�ׁj�s�����ɁA�g�����U�N�V�����i�ڋq���薾�ׁj���X�V����B
				notifyUpdateGtl(l_ifoCloseFinTransactionParams[i]);

				//�����̌���Params��
				//�Ĉ��������ϑ��萔�����Z�b�g����B
                BigDecimal l_bdImportedSetupFee =
                    new BigDecimal(l_ifoCloseFinTransactionParams[i].getImportedSetupFee() + "");
				l_ifoContractParams.setSetupFee(
                    l_bdContractSetupFee.subtract(l_bdImportedSetupFee).doubleValue());

				//�����̌���Params��
				//�Ĉ��������ϑ��萔������ł��Z�b�g����B
                BigDecimal l_bdImportedSetupFeeTax =
                    new BigDecimal(l_ifoCloseFinTransactionParams[i].getImportedSetupFeeTax() + "");
				l_ifoContractParams.setSetupFeeTax(
                    l_bdContractSetupFeeTax.subtract(l_bdImportedSetupFeeTax).doubleValue());

				//�����̌���Params.�X�V���t�Ɍ��ݎ������Z�b�g����B
				l_ifoContractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

				//���ύςݐ���=+������薾��Params.��萔��			
				l_dblSettleQuantity+=l_ifoCloseFinTransactionParams[i].getQuantity();
                l_bdSettleQuantity = new BigDecimal(l_dblSettleQuantity + "");
			}
		}
		catch (DataException l_ex)
		{
			log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}

		log.exiting(STR_METHOD_NAME);
	}

    /**
     * �������̍X�V���ڋq����ɒʒm����B<BR>
     * <BR>
     * �P�j�@@�g�����U�N�V�����h�c�擾<BR>
     * ������薾��Params.getFinTransactionId()�ɂăg�����U�N�V�����h�c���擾����B<BR>
     * <BR>
     * �Q�j�@@�ڋq���薾�׎擾<BR>
     * �擾�����g�����U�N�V�����h�c�ɊY������g�����U�N�V�����i�ڋq���薾�ׁjParams���擾����B<BR>
     * <BR>
     * �R�j�@@��n����X�V<BR>
     * �ڋq���薾�׍s�Ɉȉ��̒ʂ�l���Z�b�g���ADB�ɍX�V����B<BR>
     * <BR>
     * �g�����U�N�V�����i�ڋq���薾�ׁjParams.��n��� = ������薾��Params.��n���<BR>
     * �g�����U�N�V�����i�ڋq���薾�ׁjParams.�X�V���t = ������薾��Params.�X�V����<BR>
     * <BR>
     * @@param l_ifoFinTransactionParams - (������薾��Params)<BR>
     * <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
     * @@roseuid 4099AC6D003C
     */
	protected void notifyUpdateGtl(IfoFinTransactionParams l_ifoFinTransactionParams)
		throws RuntimeSystemException, DataFindException,
			DataNetworkException, DataQueryException, WEB3BaseException
	{
		final String STR_METHOD_NAME = "notifyUpdateGtl";
		log.entering(STR_METHOD_NAME);

		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3IfoAttributeNameDef.REAL_TIMESTAMP
				);

		// �P�j�g�����U�N�V�����h�c�擾
		long l_lngTransactionId =
			l_ifoFinTransactionParams.getFinTransactionId();
		log.debug("�g�����U�N�V�����h�c�擾 = " + l_lngTransactionId);

		// �Q�j�ڋq���薾�׎擾
		GenFinTransactionParams l_genFinTransactionParams =
			new GenFinTransactionParams(GenFinTransactionDao.findRowByTransactionId(l_lngTransactionId));

		if (l_genFinTransactionParams == null)
		{
			log.error("Error in Method: " + STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + STR_METHOD_NAME);
		}

		// �R�j��n����X�V

		//�g�����U�N�V�����i�ڋq���薾�ׁjParams.��n��� = ������薾��Params.��n���
		log.debug("�g�����U�N�V�����i�ڋq���薾�ׁjParams.��n��� = ������薾��Params.��n���");
		double l_dblNetAmount = l_ifoFinTransactionParams.getNetAmount();
		l_genFinTransactionParams.setNetAmount(l_dblNetAmount);
		log.debug("l_dblNetAmount = " + l_dblNetAmount);

		//�g�����U�N�V�����i�ڋq���薾�ׁjParams.�X�V���t = ������薾��Params.�X�V����
		log.debug("�g�����U�N�V�����i�ڋq���薾�ׁjParams.�X�V���t = ������薾��Params.�X�V����");
		if (l_realTimestamp == null)
		{
			l_realTimestamp =
			l_ifoFinTransactionParams.getLastUpdatedTimestamp();
		}    
		l_genFinTransactionParams.setLastUpdatedTimestamp(l_realTimestamp);
		log.debug("l_realTimestamp = " + l_realTimestamp);

		QueryProcessor processor = Processors.getDefaultProcessor();
		processor.doUpdateQuery(l_genFinTransactionParams);

		log.exiting(STR_METHOD_NAME);
	}



    /**
     * (getContract�̃I�[�o�[���C�h)<BR>
     * <BR>
     * �P�j�@@�g�����U�N�V�����i������薾�ׁjParams�������ID����A���������擾���A<BR>
     * �����Ƃ���B<BR>
     * <BR>
     * �������F IfoOrderExecutionImpl(<BR>
     *      �g�����U�N�V�����i������薾�ׁjParams<BR>
     *          .getOrderExecutionId()).getExecutionTimestamp() <BR>
     * <BR>
     * �Q�j�@@�敨OP�X�V�f�[�^�}�l�[�W��.get����(����ID, �⏕����ID, �s��ID, ����ID, <BR>
     * ���敪, ���P��, ����, ����, ��n��)�ɈϏ�����B<BR>
     * <BR>
     * [����] <BR>
     * ����ID�F �g�����U�N�V�����i������薾�ׁjParams.getAccountId() <BR>
     * �⏕����ID�F �g�����U�N�V�����i������薾�ׁjParams.getSubAccountId() <BR>
     * ����ID�F �g�����U�N�V�����i������薾�ׁjParams.getProductId() <BR>
     * �s��ID�F �g�����U�N�V�����i������薾�ׁjParams.getMarketId() <BR>
     * ���敪�F ContractTypeEnum.getContractType( <BR>
     *      �g�����U�N�V�����i������薾�ׁjParams.getFinTransactionType()) <BR>
     * ���P���F �g�����U�N�V�����i������薾�ׁjParams.getPrice() <BR>
     * �����F (�P�j�Ŏ擾��������) <BR>
     * �����F �敨OP�������.�����ŏI�� <BR>
     * ��n���F �g�����U�N�V�����i������薾�ׁjParams.getDeliveryDate()<BR>
     * <BR>
     * �R�j�@@�Ϗ����\�b�h�̖߂�l�I�u�W�F�N�g�����̂܂ܕԋp����B<BR>
     * <BR>
     * @@param l_ifoFinTransactionParams
     *      - �g�����U�N�V�����i������薾�ׁjParams<BR>
     * @@return IfoContractParams
     * @@throws DataException
     * @@roseuid
     */
    protected IfoContractParams getContract(
        IfoFinTransactionParams l_ifoFinTransactionParams)
        throws DataException
    {
        final String STR_METHOD_NAME = "getContract";
        log.entering(STR_METHOD_NAME);

        // ����Params
        IfoContractParams l_ifoContractParams = new IfoContractParams();

        // �P�j���ʂ̖������擾
        IfoOrderExecutionImpl l_ifoOrderExecution =
            new IfoOrderExecutionImpl(
                l_ifoFinTransactionParams.getOrderExecutionId());
        Date l_executionTimestamp = WEB3DateUtility.toDay(l_ifoOrderExecution.getExecutionTimestamp());

		//��������̔����ŏI�����擾
		Date l_closeDate = IfoTradedProductDao.findRowByProductIdMarketId(
		l_ifoFinTransactionParams.product_id,
		l_ifoFinTransactionParams.getMarketId()).getLastTradingDate();

        WEB3IfoPersistentDataManager l_ifoPersistentDatamanager =
            new WEB3IfoPersistentDataManager();

        // �Q�jgetPersistenceManager().getContract()���R�[������B
        l_ifoContractParams = l_ifoPersistentDatamanager.getContract(
        	//  --- ����ID
        	l_ifoFinTransactionParams.getAccountId(),
        	// --- �⏕����ID
        	l_ifoFinTransactionParams.getSubAccountId(),
            // --- �s��ID
            l_ifoFinTransactionParams.getMarketId(),
        	// --- ����ID
        	l_ifoFinTransactionParams.getProductId(),
        	// --- ���敪
        	ContractTypeEnum.getContractType(
       		l_ifoFinTransactionParams.getFinTransactionType()),
        	// --- ���P��
        	l_ifoFinTransactionParams.getPrice(),
        	// --- ����
        	l_executionTimestamp,
        	// --- ����
			l_closeDate,
            // --- ��n��
            l_ifoFinTransactionParams.getDeliveryDate());

        return l_ifoContractParams;

    }



    /**
      *�iprocessCloseMarginOrderExecution�̃I�[�o�[���C�h�j<BR>
      * <BR>
      *�P�j�@@�g�����U�N�V�������쐬<BR>
      *�@@set�����To�g�����U�N�V����()���R�[�����ăg�����U�N�V�������i�F������薾��Params�j���쐬����B<BR>
      *�@@�쐬�����g�����U�N�V�������i�F������薾��Params�j�ɁA�����̌��ʂh�c���Z�b�g����B<BR>
      * <BR>
      *�@@[set�����To�g�����U�N�V����()�Ɏw�肷�����]<BR>
      *�@@������薾��Params�F�@@new IfoFinTransactionParams()<BR>
      *�@@���F�@@���<BR>
      *�@@�����P��Params�F�@@���.getOrderUnitId()�ɊY�����钍���P�ʍs<BR>
      *�@@���ʂh�c�F�@@���ʂh�c<BR>
      * <BR>
      *�Q�j�@@���ʏ��Z�b�g<BR>
      *�@@updateContractCloseFromMarketOrderedTrans()���R�[�����A���ʏ����Z�b�g����B<BR>
      * <BR>
      *�@@[updateContractCloseFromMarketOrderedTrans()�Ɏw�肷�����]<BR>
      *�@@����Params�F�@@���ʂh�c�ɊY�����錚��Params<BR>
      *�@@������薾��Params�F�@@������薾��Params<BR>
      * <BR>
      *�R�j�@@���ʍX�V<BR>
      *�@@�X�V�f�[�^�}�l�[�W��.updateContractByTrans()�ɂāA���ʂ��X�V�iDB-Update�j����B<BR>
      * <BR>
      *�@@[updateContractByTrans()�Ɏw�肷�����]<BR>
      *�@@����Params�F�@@�Q�j�ŃZ�b�g��������Params<BR>
      * <BR>
      *�S�j�@@��n����A�]�����v�Z�b�g<BR>
      *�@@set���z()���R�[�����A������薾��Params�Ɏ�n����A�]�����v���Z�b�g����B<BR>
      * <BR>
      *�@@[set���z()�Ɏw�肷�����]<BR>
      *�@@������薾��Params�F�@@������薾��Params<BR>
      * <BR>
      *�T�j�@@�g�����U�N�V�����X�V<BR>
      *�@@�X�V�f�[�^�}�l�[�W��.saveNewFinTransaction()�ɂāA�g�����U�N�V�����i������薾�ׁj���X�V�iDB-Insert�j����B<BR>
      * <BR>
      *�@@[saveNewFinTransaction()�Ɏw�肷�����]<BR>
      *�@@������薾��Params�F�@@������薾��Params<BR>
      * <BR>
      *�U�j�@@�f�s�k�ʒm<BR>
      *�@@notifyGtl()�ɂāA�g�����U�N�V�����i�ڋq���薾�ׁj���X�V����B<BR>
      * <BR>
      *�@@[notifyGtl()�Ɏw�肷�����]<BR>
      *�@@������薾��Params�F�@@������薾��Params<BR>
      * <BR>
      * @@param l_ifoOrderExecution - ���I�u�W�F�N�g<BR>
      * @@param l_contractId - ����ID <BR>
      * @@throws com.fitechlabs.xtrade.kernel.data.DataException
      * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
      */
    protected void processCloseMarginOrderExecution(IfoOrderExecution l_ifoOrderExecution, long l_contractId)
        throws DataException, RuntimeSystemException
    {
        IfoFinTransactionParams l_ifoFinTransactionParams = new IfoFinTransactionParams();
        setMarketOrderedTransDefaultValues(l_ifoFinTransactionParams);
        
        //orderUnitRow���擾
        IfoOrderUnitRow l_ifoOrderUnitRow = IfoOrderUnitDao.findRowByPk(l_ifoOrderExecution.getOrderUnitId());
        if(l_ifoOrderUnitRow == null)
        {
            String msg = "processCloseMarginOrderExecution: No corresponding order unit for execId(" + l_ifoOrderExecution.getOrderExecutionId() + "),acct(" + l_ifoOrderExecution.getAccountId() + "),subAcct(" + l_ifoOrderExecution.getSubAccountId() + "),product(" + l_ifoOrderExecution.getProduct().getProductId() + "),market(" + l_ifoOrderExecution.getMarketId() + ")";
            log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        
        //set�����To�g�����U�N�V�������R�[��
        setExecutionInfoToMarketOrderedTrans(l_ifoFinTransactionParams, l_ifoOrderExecution, l_ifoOrderUnitRow, l_contractId);
        IfoContractParams l_ifoContractParams = getPersistenceManager().getContract(l_contractId);
        if(l_ifoContractParams == null)
        {
            String msg = "processCloseMarginOrderExecution dosen't exist for acct(" + l_ifoFinTransactionParams.getAccountId() + "fType(" + l_ifoFinTransactionParams.getFinTransactionType() + "), subAcct(" + l_ifoFinTransactionParams.getSubAccountId() + "), product(" + l_ifoFinTransactionParams.getProductId() + "), price(" + l_ifoFinTransactionParams.getPrice() + "), openDate(" + l_ifoFinTransactionParams.getDeliveryDate() + ")";
            log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        else
        {
            l_ifoFinTransactionParams.setContractId(l_ifoContractParams.getContractId());
            updateContractCloseFromMarketOrderedTrans(l_ifoContractParams, l_ifoFinTransactionParams);
            getPersistenceManager().updateContractByTrans(l_ifoContractParams);
            setMarginNetAmount(l_ifoFinTransactionParams);
            getPersistenceManager().saveNewFinTransaction(l_ifoFinTransactionParams);
            notifyGtl(l_ifoFinTransactionParams);
            return;
        }
    }
    
	/**
	  * �inotifyGtl�̃I�[�o�[���C�h�j<BR>
	  * <BR>
      *�g�����U�N�V�����̍X�V��GTL�w�ɒʒm����B<BR>
	  * <BR>
      *�P�j�@@�^�C���X�^���v�ɐݒ肳��Ă����t�������擾����<BR>
      *�@@�|ThreadLocalSystemAttributesRegistry.getAttribute( )<BR>
      *  �ݒ�L�[�F ������ԊǗ�.TIMESTAMP_TAG<BR>
	  * <BR>
      *�Q�j�@@�����ԗp�^�C���X�^���v��������Ԃ��擾����<BR>
      *�@@�|ThreadLocalSystemAttributesRegistry.getAttribute( )<BR>
      *  �ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�C�X.REAL_TIMESTAMP<BR>
	  * <BR>
      *�R�j�@@�����Ԃ��ݒ肳��Ă���ꍇ(REAL_TIMESTAMP != null)<BR>
      *�@@��t�����Ɏ����Ԃ��Z�b�g���Ă���g�����U�N�V�����X�V�̒ʒm�������s���B<BR>
	  * <BR>
      *�@@�R�|�P�j�@@��t�����̃��Z�b�g<BR>
      *�@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�t������<BR>
      *�@@�@@�@@�Q�j�Ŏ擾���������Ԃ��Z�b�g����B<BR>
      *  �@@�ݒ�L�[�F ������ԊǗ�.TIMESTAMP_TAG<BR>
	  * <BR>
      *�@@�R�|�Q�j�@@GTL�w�ւ̒ʒm����<BR>
      *�@@�@@super.notifyGtl()���R�[������<BR>
	  * <BR>
      *�@@�R�|�R�j�@@��t�����̍ă��Z�b�g<BR>
      *�@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�t������<BR>
      *�@@�@@�@@�@@�P�j�Ŏ擾�����^�C���X�^���v�̐ݒ�l���Z�b�g����B<BR>
      *  �@@�ݒ�L�[�F ������ԊǗ�.TIMESTAMP_TAG<BR>
	  * <BR>
      *�S�j�@@�����Ԃ��ݒ肳��Ă��Ȃ��ꍇ(REAL_TIMESTAMP == null)<BR>
	  * <BR>
      *�@@�S�|�P�j�@@GTL�w�ւ̒ʒm����<BR>
      *�@@�@@super.notifyGtl()���R�[������<BR>
	  * <BR>
	  * @@param tparams - �g�����U�N�V����Params<BR>
	  * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	  */
	protected void notifyGtl(IfoFinTransactionParams tparams)
		throws RuntimeSystemException
	{
		final String STR_METHOD_NAME = "notifyGtl";
		log.entering(STR_METHOD_NAME);
		
		Timestamp l_timestamp =
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG
				);
		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3IfoAttributeNameDef.REAL_TIMESTAMP
				);
		if (l_realTimestamp != null)
		{
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_realTimestamp
				);
			super.notifyGtl(tparams);
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_timestamp
				);
		}
		else
		{
			super.notifyGtl(tparams);
		}
		log.exiting(STR_METHOD_NAME);
	}

	/**
	  * �iundoExecution�̃I�[�o�[���C�h�j<BR>
	  * <BR>
	  * ������������s���B<BR>
	  * <BR>
      *�P�j�@@�^�C���X�^���v�ɐݒ肳��Ă����t�������擾����<BR>
      *�@@�|ThreadLocalSystemAttributesRegistry.getAttribute( )<BR>
      *  �ݒ�L�[�F ������ԊǗ�.TIMESTAMP_TAG<BR>
	  * <BR>
      *�Q�j�@@�����ԗp�^�C���X�^���v��������Ԃ��擾����<BR>
      *�@@�|ThreadLocalSystemAttributesRegistry.getAttribute( )<BR>
      *  �ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�C�X.REAL_TIMESTAMP<BR>
	  * <BR>
      *�R�j�@@�����Ԃ��ݒ肳��Ă���ꍇ(REAL_TIMESTAMP != null)<BR>
      *�@@��t�����Ɏ����Ԃ��Z�b�g���Ă��������������s���B<BR>
	  * <BR>
      *�@@�R�|�P�j�@@��t�����̃��Z�b�g<BR>
      *�@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�t������<BR>
      *�@@�@@�@@�Q�j�Ŏ擾���������Ԃ��Z�b�g����B<BR>
      *  �@@�ݒ�L�[�F ������ԊǗ�.TIMESTAMP_TAG<BR>
	  * <BR>
      *�@@�R�|�Q�j�@@���������<BR>
      *�@@�@@super.undoExecution()���R�[������<BR>
	  * <BR>
      *�@@�R�|�R�j�@@��t�����̍ă��Z�b�g<BR>
      *�@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�t������<BR>
      *�@@�@@�@@�@@�P�j�Ŏ擾�����^�C���X�^���v�̐ݒ�l���Z�b�g����B<BR>
      *  �@@�ݒ�L�[�F ������ԊǗ�.TIMESTAMP_TAG<BR>
	  * <BR>
      *�S�j�@@�����Ԃ��ݒ肳��Ă��Ȃ��ꍇ(REAL_TIMESTAMP == null)<BR>
	  * <BR>
      *�@@�S�|�P�j�@@���������<BR>
      *�@@�@@super.undoExecution()���R�[������<BR>
	  * <BR>
	  * @@param execId - ���ID<BR>
      * @@throws com.fitechlabs.xtrade.kernel.data.DataException
	  * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	  */
	public void undoExecution(long execId)
		throws DataException, RuntimeSystemException
	{
		final String STR_METHOD_NAME = "undoExecution";
		log.entering(STR_METHOD_NAME);
		
		Timestamp l_timestamp =
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG
				);
		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3IfoAttributeNameDef.REAL_TIMESTAMP
				);
		if (l_realTimestamp != null)
		{
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_realTimestamp
				);
			super.undoExecution(execId);
			ThreadLocalSystemAttributesRegistry.setAttribute(
				WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
				l_timestamp
				);
		}
		else
		{
			super.undoExecution(execId);
		}
		log.exiting(STR_METHOD_NAME);
	}


    /**
     * (�敨OP�X�V�f�[�^�}�l�[�W��)<BR>
     *
     * @@author �����(���u)
     * @@version 1.0
     */
    public class WEB3IfoPersistentDataManager
        extends IfoPositionManagerHelper.PersistentDataManager
    {
        /**
         * ���O���[�e�B���e�B
         */
        private WEB3LogUtility sublog =
            WEB3LogUtility.getInstance(WEB3IfoPersistentDataManager.class);

        /**
         * @@roseuid 40C0750C02AF
         */
        public WEB3IfoPersistentDataManager()
        {
            super();
        }
        
		/**
		 * (get������薾��ForContract)<BR>
		 * <BR>
		 * ���ʂɊ֘A����g�����U�N�V�����s�I�u�W�F�N�g�̃��X�g���擾����B<BR>
		 * ���R�[�h��������Ȃ������ꍇ�́A�T�C�Y0��List��ԋp����B<BR>
		 * <BR>
		 * �ȉ��̏����ɂăg�����U�N�V�����i���������j�e�[�u�����������A<BR>
		 * ��v����s�����X�g�ŕԋp����B<BR>
		 * <BR>
		 * [����]<BR>
		 * �g�����U�N�V����.���ʂh�c = ����.���ʂh�c<BR>
		 * �g�����U�N�V����.�폜�t���O = BooleanEnum.FALSE<BR>
		 * @@param l_lngContractID - ���ʂhD<BR>
		 * @@return List
		 * @@throws webbroker3.common.WEB3BaseException
		 */
		public List getFinTransactionDetailForContract(
			long l_lngContractID)
			throws WEB3BaseException
		{
			final String STR_METHOD_NAME = "getFinTransactionDetailForContract()";
			sublog.entering(STR_METHOD_NAME);

			List l_returnList = null;
			String l_strWhere = " contract_id = ? and delete_flag = ? ";

			Object[] l_objBindValue = new Object[2];
			l_objBindValue[0] = new Long(l_lngContractID);
			l_objBindValue[1] = new Integer(BooleanEnum.FALSE.intValue());

			try
			{
				//�f�[�^����
				QueryProcessor processor = Processors.getDefaultProcessor();
				l_returnList = processor.doFindAllQuery(
					IfoFinTransactionRow.TYPE,
					l_strWhere,
					l_objBindValue);
			}
			catch (DataQueryException l_ex)
			{
				String l_strMessage = "�g�����U�N�V�����i������薾�ׁj�e�[�u�������� error !!!";
				sublog.error(l_strMessage, l_ex);

				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
			catch (DataNetworkException l_ex)
			{
				String l_strMessage = "�g�����U�N�V�����i������薾�ׁj�e�[�u�������� error !!!";
				sublog.error(l_strMessage, l_ex);

				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}

			sublog.exiting(STR_METHOD_NAME);
			return l_returnList;
		}

        /**
         * (get������薾��ForOrderUnit)<BR>
         * <BR>
         * �g�����U�N�V�����i������薾�ׁj�e�[�u�����A�ȉ��̏����ōs���������A<BR>
         * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̃��X�g���擾����B<BR>
         * ���R�[�h��������Ȃ������ꍇ�́A�T�C�Y0��List��ԋp����B<BR>
         * <BR>
         * [��������]<BR>
         * �g�����U�N�V�����i������薾�ׁj.�����P�ʂh�c = �����̒����P�ʂh�c<BR>
         * �g�����U�N�V����.�폜�t���O = BooleanEnum.FALSE<BR>
         * @@param l_lngOrderUnitID - �����P�ʂh�c<BR>
         * @@return List
         * @@throws webbroker3.common.WEB3BaseException
         * @@roseuid 40736CF9003F
         */
        public List getFinTransactionDetailForOrderUnit(long l_lngOrderUnitID)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionDetailForOrderUnit";
            sublog.entering(STR_METHOD_NAME);

            List l_returnList = null;

            String l_strWhere = " order_unit_id = ?  and delete_flag = ? ";
            Object[] l_objBindValue = new Object[2];

            l_objBindValue[0] = new Long(l_lngOrderUnitID);
            l_objBindValue[1] = BooleanEnum.FALSE;

            try
            {
                //�f�[�^���m
                QueryProcessor processor = Processors.getDefaultProcessor();
                l_returnList = processor.doFindAllQuery(
                        IfoFinTransactionRow.TYPE,
                        l_strWhere,
                        l_objBindValue);

            }
            catch (DataQueryException l_ex)
            {
                String l_strMessage = "�g�����U�N�V�����i������薾�ׁj�e�[�u�������� error !!!";
                sublog.error(l_strMessage, l_ex);

                //throw new WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                String l_strMessage = "�g�����U�N�V�����i������薾�ׁj�e�[�u�������� error !!!";
                sublog.error(l_strMessage, l_ex);

                //throw new WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            sublog.exiting(STR_METHOD_NAME);

            return l_returnList;
        }

        /**
         * (get������薾��ForOpenCont)<BR>
         * <BR>
         * ���ʂɊ֘A����V�K���g�����U�N�V�����s�I�u�W�F�N�g�̃��X�g���擾����B<BR>
         * ���R�[�h��������Ȃ������ꍇ�́A�T�C�Y0��List��ԋp����B<BR>
         * <BR>
         * �ȉ��̏����ɂăg�����U�N�V�����i���������j�e�[�u�����������A<BR>
         * ��v����s�����X�g�ŕԋp����B<BR>
         * <BR>
         * [����]<BR>
         * �g�����U�N�V����.�g�����U�N�V�����J�e�S�� = �h91�F�敨�V�K������h or �h93�FOP�V�K������h<BR>
         * �g�����U�N�V����.���ʂh�c = ����.���ʂh�c<BR>
         * �g�����U�N�V����.�폜�t���O = BooleanEnum.FALSE<BR>
         * @@param l_lngContractID - ���ʂhD<BR>
         * @@return List
         * @@throws webbroker3.common.WEB3BaseException
         * @@roseuid 407523AD03B9
         */
        public List getFinTransactionDetailForOpenCont(
            long l_lngContractID)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionDetailForOpenCont()";
            sublog.entering(STR_METHOD_NAME);

            List l_returnList = null;
            String l_strWhere = " (fin_transaction_categ = ? or fin_transaction_categ = ?) and contract_id = ? and delete_flag = ? ";

            Object[] l_objBindValue = new Object[4];
            l_objBindValue[0] = new Integer(FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN.intValue());
            l_objBindValue[1] = new Integer(FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN.intValue());
            l_objBindValue[2] = new Long(l_lngContractID);
            l_objBindValue[3] = new Integer(BooleanEnum.FALSE.intValue());

            try
            {
                //�f�[�^���m
                QueryProcessor processor = Processors.getDefaultProcessor();
                l_returnList = processor.doFindAllQuery(
                    IfoFinTransactionRow.TYPE,
                    l_strWhere,
                    l_objBindValue);
            }
            catch (DataQueryException l_ex)
            {
                String l_strMessage = "�g�����U�N�V�����i������薾�ׁj�e�[�u�������� error !!!";
                sublog.error(l_strMessage, l_ex);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                String l_strMessage = "�g�����U�N�V�����i������薾�ׁj�e�[�u�������� error !!!";
                sublog.error(l_strMessage, l_ex);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            sublog.exiting(STR_METHOD_NAME);
            return l_returnList;
        }

        /**
         * (get���ʂh�cByOrder)<BR>
         * <BR>
         * �����Ɋ֘A���錚�ʂh�c��z��Ŏ擾����B<BR>
         * <BR>
         * �����h�c�Ńg�����U�N�V�����i������薾�ׁj�e�[�u�����������A<BR>
         * ��v����s�̌��ʂh�c��z��ŕԋp����B<BR>
         * �i�d���v�f�͍폜����j<BR>
         * <BR>
         * @@param l_lngOrder�h�c - �����h�c<BR>
         * @@return long[]
         * @@throws webbroker3.common.WEB3BaseException
         * @@roseuid 40A2E5720226
         */
        public long[] getContractIDByOrder(long l_lngOrderID)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getContractIDByOrder";
            sublog.entering(STR_METHOD_NAME);

            long[] l_lngContractIDs;
            try
            {
                // �����h�c�Ńg�����U�N�V�����i������薾�ׁj�e�[�u����������
                List l_lisTransactionParams =
                    IfoFinTransactionDao.findRowsByOrderId(l_lngOrderID);

                l_lngContractIDs =
                    new long[l_lisTransactionParams.size()];
                IfoFinTransactionParams[] l_lngTransactionParams =
                    new IfoFinTransactionParams[l_lisTransactionParams.size()];
                l_lisTransactionParams.toArray(l_lngTransactionParams);

                // ��v����s�̌��ʂh�c��z��ŕԋp����B
                for (int i = 0; i < l_lngTransactionParams.length; i++)
                {
                    l_lngContractIDs[i] =
                        l_lngTransactionParams[i].getContractId();

                }

            }
            catch (DataException l_ex){
                log.error("Error In Method: " + STR_METHOD_NAME, l_ex);

                // throws WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            sublog.exiting(STR_METHOD_NAME);
            return l_lngContractIDs;
        }


        /**
         * (get������薾��ForCloseCont)<BR>
         * ���ʂɊ֘A����ԍσg�����U�N�V�����s�I�u�W�F�N�g�̃��X�g���擾����B<BR>
         *���R�[�h��������Ȃ������ꍇ�́A�T�C�Y0��List��ԋp����B<BR>
         *�ȉ��̏����ɂăg�����U�N�V�����i���������j�e�[�u�����������A��v����s�����X�g�ŕԋp����B<BR>
         *<BR>
         *    [����]<BR>
         *    �g�����U�N�V����.�g�����U�N�V�����J�e�S�� = �h92�F�敨�ԍώ���h or �h94�FOP�ԍώ���h<BR>
         *    �g�����U�N�V����.���ʂh�c = ����.���ʂh�c<BR>
         *    �g�����U�N�V����.�폜�t���O = BooleanEnum.FALSE<BR>
         *���g�����U�N�V�������������ifin_transaction_timestamp�j�����Ŏ擾����B<BR>
         * @@param l_lngContractId<BR>
         * @@throws webbroker3.common.WEB3BaseException
         * @@return
         */
        public List getFinTransactionDetailForCloseCont(long l_lngContractId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionDetailForCloseCont";
            sublog.entering(STR_METHOD_NAME);

            List l_returnList = new ArrayList();
            //�f�[�^���m
            QueryProcessor processor = null;
            try
            {
                // //��������������̍쐬�ȉ��Ɏ��������Ō���������������쐬����B
                String l_strWhere = " (fin_transaction_categ = ? or fin_transaction_categ = ?) and contract_id = ? and delete_flag = ?";

                Object[] l_strBindValue = new Object[4];
                l_strBindValue[0] = new Integer(FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE.intValue());
                l_strBindValue[1] = new Integer(FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE.intValue());
                l_strBindValue[2] = new Long(l_lngContractId);
                l_strBindValue[3] = new Integer(BooleanEnum.FALSE.intValue());

                String l_strOrderBy = "fin_transaction_timestamp";

                processor = Processors.getDefaultProcessor();
                l_returnList = processor.doFindAllQuery(
                    IfoFinTransactionRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_strBindValue);
            }
            catch (DataQueryException l_ex)
            {
                String l_strMessage = "�g�����U�N�V�����i������薾�ׁj�e�[�u�������� error !!!";
                sublog.error(l_strMessage, l_ex);

                //throw new WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                String l_strMessage = "�g�����U�N�V�����i������薾�ׁj�e�[�u�������� error !!!";
                sublog.error(l_strMessage, l_ex);

                //throw new WEB3SystemLayerException
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            return l_returnList;
        }
        
        /**
         * (get����)<BR>
         * �y���ʃe�[�u���z����A<BR>
         * �����̒l���w�肵�Y�����錚��Params�I�u�W�F�N�g���擾���ԋp����B<BR>
         * �iIfoContractParams getContract(long accountId, <BR>
         * long subAccountId, long productId, long marketId, ContractTypeEnum type,<BR>
         *  double price, Date openDate, Date closeDate)�̃I�[�o�[���[�h�j <BR>
         * <BR>
         * �P�j�@@�����I�u�W�F�N�g�̈ȉ��̍��ڂ����������Ɏw�肵�A<BR>
         * �@@�@@�@@QueryProcessor���g�p���y���ʃe�[�u���z���猚��Params�I�u�W�F�N�g���擾����B<BR>
         * <BR>
         * �@@�@@������������  <BR>
         * �@@�@@����ID = ����.����ID  <BR>
         * �@@�@@���@@�⏕����ID = ����.�⏕����ID  <BR>
         * �@@�@@���@@�s��ID = ����.�s��ID  <BR>
         * �@@�@@���@@����ID = ����.����ID  <BR>
         * �@@�@@���@@���敪 = ����.���敪  <BR>
         * �@@�@@���@@���P�� = ����.���P��  <BR>
         * �@@�@@���@@���� = ����.����  <BR>
         * �@@�@@���@@���� = ����.����  <BR>
         * �@@�@@���@@��n�� = ����.��n�� <BR>
         * <BR>
         * �Q�j�@@�擾��������Params�I�u�W�F�N�g��ԋp����B<BR>
         * �@@�@@�@@�Y�����錚�ʂ����݂��Ȃ��ꍇ�́Anull��ԋp����B <BR>
         * @@param l_lngAccountId - (����ID)<BR>
         * ����ID�B<BR>
         * @@param l_lngSubAccountId - (�⏕����ID)<BR>
         * �⏕����ID�B<BR>
         * @@param l_lngMarketId - (�s��ID)<BR>
         * �s��ID�B<BR>
         * @@param l_lngProductId - (����ID)<BR>
         * ����ID�B<BR>
         * @@param l_contractType - (���敪)<BR>
         * ���敪�B<BR>
         * @@param l_dblPrice - (���P��)<BR>
         * ���P���B<BR>
         * @@param l_datOpenDate - (����)<BR>
         * �����B<BR>
         * @@param l_datCloseDate - (����)<BR>
         * �����B<BR>
         * @@param l_datDeliveryDate - (��n��)<BR>
         * ��n���B<BR>
         * @@return IfoContractParams
         * @@throws WEB3BaseException
         */
        public IfoContractParams getContract(
            long l_lngAccount,
            long l_lngSubAccountId,
            long l_lngMarketId,
            long l_lngProductId,
            ContractTypeEnum l_contractType,
            double l_dblPrice,
            Date l_datOpenDate,
            Date l_datCloseDate,
            Date l_datDeliveryDate) throws DataException
        {
            final String STR_METHOD_NAME = "getContract(long, long, long, long, ContractTypeEnum, "
                + "double, Date, Date, Date)";
            sublog.entering(STR_METHOD_NAME);

            //��������
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            l_sbWhere.append(" and sub_account_id = ? ");
            l_sbWhere.append(" and market_id = ? ");
            l_sbWhere.append(" and product_id = ? ");
            l_sbWhere.append(" and contract_type = ? ");
            l_sbWhere.append(" and contract_price = ? ");
            l_sbWhere.append(" and open_date = ? ");
            l_sbWhere.append(" and close_date = ? ");
            l_sbWhere.append(" and delivery_date = ? ");

            List l_lisBindValue = new ArrayList();
            l_lisBindValue.add(new Long(l_lngAccount));
            l_lisBindValue.add(new Long(l_lngSubAccountId));
            l_lisBindValue.add(new Long(l_lngMarketId));
            l_lisBindValue.add(new Long(l_lngProductId));
            l_lisBindValue.add(l_contractType);
            l_lisBindValue.add(new Double(l_dblPrice));
            l_lisBindValue.add(l_datOpenDate);
            l_lisBindValue.add(l_datCloseDate);
            l_lisBindValue.add(l_datDeliveryDate);
            Object[] l_objBindValues = new Object[l_lisBindValue.size()];
            l_lisBindValue.toArray(l_objBindValues);

            List l_lisRecords = null;

            //QueryProcessor���g�p���y���ʃe�[�u���z���猚��Params�I�u�W�F�N�g���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                IfoContractRow.TYPE,
                l_sbWhere.toString(),
                l_objBindValues);

            //�Y�����錚�ʂ����݂��Ȃ��ꍇ�́Anull��ԋp����B
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                sublog.exiting(STR_METHOD_NAME);
                return null;
            }
            //�擾��������Params�I�u�W�F�N�g��ԋp����B
            sublog.exiting(STR_METHOD_NAME);
            return (IfoContractParams)l_lisRecords.get(0);
        }
        
        /**
         * (get����ListBy�����P��)<BR>
         * �w�肳�ꂽ�����f�[�^�ɑ΂���A���ʃf�[�^��S�Ď擾���A<BR>
         * ����Params��List���쐬���ĕԂ��B<BR>
         * <BR>
         * �P�j�@@this.get���ʂh�cByOrder(�����̒���ID)���\�b�h�̃R�[���ɂ��A<BR>
         * ����ID��List���擾����B<BR>
         * �Q�j�@@�P�j�ō쐬��������ID��List�����A�ȉ��̏������s���A<BR>
         * �@@�@@�@@�@@�w�肳�ꂽ�����f�[�^�ɑ΂��錚��Params�I�u�W�F�N�g��List���쐬����B<BR>
         * �@@�Q�|�P�j�@@������薾��Params.����ID�ɊY�����錚��Params�I�u�W�F�N�g���擾����B<BR>
         * �@@�@@�@@�@@�敨OP�X�V�f�[�^�}�l�[�W��.getContract(������薾��Params.����ID)�R�[���ɂ��<BR>
         * �@@�@@�@@�@@�擾����B<BR>
         * <BR>
         * �@@�Q�|�Q�j�@@�擾��������Params�I�u�W�F�N�g���A�߂�lList�ɒǉ�����B<BR>
         * <BR>
         * �R�j�@@�쐬��������Params��List��Ԃ��B<BR>
         * <BR>
         * @@param l_lngOrderId - (����ID)<BR>
         * ����ID<BR>
         * @@return List
         * @@throws WEB3BaseException
         *
         */
        public List getContractListByOrderUnit(long l_lngOrderId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getContractListByOrderUnit(long)";
            log.entering(STR_METHOD_NAME);

            List l_lisIfoContractParams = new ArrayList();
            // �P�j�@@this.get���ʂh�cByOrder(�����̒���ID)���\�b�h�̃R�[���ɂ��A����ID��List���擾����B
            long[] l_lngContractIds = this.getContractIDByOrder(l_lngOrderId);

            // �Q�j�w�肳�ꂽ�����f�[�^�ɑ΂��錚��Params�I�u�W�F�N�g��List���쐬����B
            int l_intContractIdsCnt = l_lngContractIds.length;
            for (int i = 0; i < l_intContractIdsCnt; i++)
            {
                try
                {
                    l_lisIfoContractParams.add(super.getContract(l_lngContractIds[i]));
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
                catch (DataException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }

            // �R�j�@@�쐬��������Params��List��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_lisIfoContractParams;
        }

        /**
         * (get������薾��ListBy�����P��Plus����)<BR>
         * �w�肳�ꂽ�����f�[�^�{���ʃf�[�^�ɑ΂���A<BR>
         * ������v�Z�ΏۂƂȂ������薾��Params��List���擾����B<BR>
         * <BR>
         * �P�j�@@�����P��ID�A����ID�������ɁA<BR>
         * �@@�y�g�����U�N�V�����e�[�u���i������薾�ׁj�z����������B<BR>
         * �@@-------------------------------<BR>
         * �@@������������<BR>
         * �@@�@@�@@�@@�����P��ID������.�����P��ID<BR>
         * �@@���@@����ID������.����ID<BR>
         * �@@���@@�폜�t���O��FALSE <BR>
         * �@@-------------------------------<BR>
         * <BR>
         * �Q�j�@@�ϒ��z��I�u�W�F�N�g�ɁA�������ꂽ������薾��Params���A<BR>
         * �@@�ꌏ���z��ɒǉ�����B<BR>
         * <BR>
         * �R�j�@@�ϒ��z��I�u�W�F�N�g��ԋp����B<BR>
         * <BR>
         * @@param l_lngOrderUnitId - (�����P��ID)<BR>
         * �����P��ID<BR>
         * @@param l_lngContractId - (����ID)<BR>
         * ����ID<BR>
         * @@return List
         * @@throws WEB3BaseException
         *
         */
        public List getTransactionsListByOrderUnitPlusContract(long l_lngOrderUnitId, long l_lngContractId)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getTransactionsListByOrderUnitPlusContract(long, long)";
            log.entering(STR_METHOD_NAME);

            //�@@�y�g�����U�N�V�����e�[�u���i������薾�ׁj�z����������B
            //�@@-------------------------------
            //�@@������������
            //�@@�@@�@@�@@�@@�����P��ID������.�����P��ID
            //�@@�@@���@@����ID������.����ID
            //�@@�@@���@@�폜�t���O��FALSE
            //�@@�@@-------------------------------
            List l_lisTransactions = null;

            //��������
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");
            l_sbWhere.append(" and contract_id = ? ");
            l_sbWhere.append(" and delete_flag = ? ");

            Object[] l_objBindValues = {
                new Long(l_lngOrderUnitId),
                new Long(l_lngContractId),
                new Integer(0)};
            try
            {
                QueryProcessor l_queryProcessor = null;
                l_queryProcessor = Processors.getDefaultProcessor();
                l_lisTransactions = l_queryProcessor.doFindAllQuery(
                    IfoFinTransactionRow.TYPE,
                    l_sbWhere.toString(),
                    l_objBindValues);
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
            log.exiting(STR_METHOD_NAME);
            return l_lisTransactions;
        }
    }

    public PersistentDataManager getPersistenceManager()
    {
        return new WEB3IfoPersistentDataManager();
    }
}
@
