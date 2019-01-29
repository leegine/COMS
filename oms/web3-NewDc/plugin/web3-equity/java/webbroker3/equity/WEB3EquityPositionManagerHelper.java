head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���|�W�V�����w���p�[(WEB3EquityPositionManagerHelper.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/25 ������(���u) �V�K�쐬
Revesion History : 2006/07/15 �h�C �y�����z�d�l�ύX�Ǘ��䒠�E���f��952�A953�A954��Ή�
Revesion History : 2006/08/14 �h�C �y�����z�d�l�ύX�Ǘ��䒠�E���f��973�ADB�X�V�d�l165��Ή�
Revesion History : 2006/08/18 �h�C �y�����z�d�l�ύX�Ǘ��䒠�E���f��975��Ή�
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypePositionManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypePositionManagerHelper;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GeneralizedFinTransaction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CapitalGainStatusDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�g���|�W�V�����w���p�[�j�B<BR>
 * xTrade�W����������̊g���|�W�V�����w���p�[�N���X<BR>
 * <BR>
 * �c���X�V�̎葱�����L�q
 * @@author ������
 * @@version 1.0
 */
public class WEB3EquityPositionManagerHelper extends EqTypePositionManagerHelper
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityPositionManagerHelper.class);

    /**
     * �g���f�[�^�}�l�[�W���I�u�W�F�N�g�B<BR>
     */
    private static WEB3EquityPersistentDataManager dataManager = new WEB3EquityPersistentDataManager();

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �m�����T�v�n<BR>
     * �P�j�X�[�p�[�N���X�̏������Ăяo���B<BR>
     * 
     * @@param l_tradingModuleType ���i�^�C�v
     */
    public WEB3EquityPositionManagerHelper(ProductTypeEnum l_tradingModuleType)
    {
        super(l_tradingModuleType);
    }

    /**
     * �g���f�[�^�}�l�[�W���I�u�W�F�N�g��Ԃ��B
     */
    public PersistentDataManager getPersistenceManager()
    {
        return dataManager;
    }

    /**
     * (�����菈��)<BR>
     * ������Ƃ��Ď萔���̍Čv�Z�������Ȃ����ʂ�<BR>
     * �ۗL���Y�A�����ڋq���薾�ׁA�ڋq����A�⏕�����ɔ��f������B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�c���j�����菈���v�Q�ƁB<BR>
     * <BR>
     * �P�j����v�Z�ΏۂƂȂ銔���ڋq���薾��Param�I�u�W�F�N�g�̃��X�g���擾����B<BR>
     * �@@�|�g���f�[�^�}�l�[�W���[.get�����ڋq���薾��Param���X�g()���R�[������B<BR>
     * <BR>
     * �R�j�����ڋq���薾��Params�I�u�W�F�N�g�̃��X�g��芔���ڋq���薾�ׂ̔z����쐬����B<BR>
     * �@@�|���X�g�̌������擾����B<BR>
     * �@@�@@�������P�����������́A�ȉ��̒ʂ�Ƃ���B<BR>
     * �@@�@@�|��菈������̃R�[���ł���΁i��������is�����==false�j�������I������B<BR>
     * �@@�@@�|�������������̃R�[���ł���΁i��������is�����==true�j<BR>
     * �@@�@@�@@�ȉ��̏����𑱍s����B<BR>
     * �@@�@@�@@���i�������̒����ɑ΂���ϑ��萔���̍Čv�Z���K�v�Ȃ��߁j<BR>
     * �@@�|���X�g�̌����̃T�C�Y�Ŋ����ڋq���薾�ׂ̔z����쐬����B<BR>
     * <BR>
     * �S�j�����v�Z�T�[�r�X.calc�萔���i���j()���R�[������B<BR>
     * <BR>
     * �T�j������v�Z��̌덷��ۗL���Y�ɔ��f������B�������̎��݂̂��̏��������{<BR>
     * �@@�|������v�Z�O�̈ϑ��萔���̍��v���擾����B<BR>
     * �@@�|������v�Z�O�̏���ł̍��v���擾����B<BR>
     * �@@�|������v�Z��̈ϑ��萔���̍��v���擾����B<BR>
     * �@@�|������v�Z��̈ϑ��萔���̍��v���擾����B<BR>
     * �@@�|������v�Z��̏��o��i�ϑ��萔���{����Łj�̌덷��ۗL���Y�e�[�u���ɔ��f������B<BR>
     * <BR>
     * �U�j�����ڋq���薾��Params�̈ϑ��萔���E����ŁE��n����̒l��������v�Z��̒l�ɍX�V����B<BR>
     * �@@�|������v�Z��̈ϑ��萔�����擾�������ڋq���薾��Params�ɃZ�b�g����B<BR>
     * �@@�|������v�Z��̏���ł��擾�������ڋq���薾��Params�ɃZ�b�g����B<BR>
     * �@@�|�����ڋq���薾��Params�����P�����擾����B<BR>
     * �@@�|�����ڋq���薾��Params����萔�ʂ��擾����B<BR>
     * �@@�|������v�Z��̎�n���(*1)���v�Z�������ڋq���薾��Params�ɃZ�b�g����B<BR>
     * �@@�@@������莞�F��n�����������i��萔�ʁ~���P���j�{���o��i�ϑ��萔���{����Łj<BR>
     * �@@�@@������莞�F��n�����������i��萔�ʁ~���P���j�|���o��i�ϑ��萔���{����Łj<BR>
     * <BR>
     * �@@�|������v�Z��̏��n�v���z���v�Z���A�����ڋq���薾��Params�ɃZ�b�g����B<BR>
     * �@@�@@�@@�i�����莞�̂݁j<BR>
     * �@@�@@�@@�|�����v�Z�T�[�r�X.calc���n���v( )���R�[������B�߂�l���u���n�v���z�v�ɃZ�b�g�B<BR>
     * �@@�@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@�@@��calc���n���v(double, double, long, SubAccount, TaxTypeEnum)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�@@���z�F�@@������v�Z��̎�n���(*1)<BR>
     * �@@�@@�@@�@@�����ʁF�@@�����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�@@����ID�F�@@�����ڋq���薾��Params.����ID<BR>
     * �@@�@@�@@�@@�⏕�����F�@@�����ڋq���薾��Params.����ID�A�⏕����ID�ɊY������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�ŋ敪�F�@@�����ڋq���薾��Params.�ŋ敪<BR>
     * �@@�@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�|������v�Z��̏��n�v�Ŋz���v�Z���A�����ڋq���薾��Params�ɃZ�b�g����B<BR>
     * �@@�@@�@@�i�����莞�̂݁j<BR>
     * �@@�@@�@@�|�����v�Z�T�[�r�X.calc���n�v��( )���R�[������B�߂�l���u���n�v�Ŋz�v�ɃZ�b�g�B<BR>
     * �@@�@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@�@@��calc���n�v��(�⏕����, TaxTypeEnum, double, Timestamp, TaxTypeEnum)�F�����ݒ�d�l��<BR>
     * �@@�@@�@@�@@�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�ŋ敪�F�@@�����ڋq���薾��Params.�ŋ敪<BR>
     * �@@�@@�@@�@@���z�F�@@�v�Z�������n�v���z�icalc���n���v( )�̖߂�l�j<BR>
     * �@@�@@�@@�@@����F�@@�����ڋq���薾��Params.��n��<BR>
     * �@@�@@�@@�@@�ڋq�ŋ敪�F�@@�擾�����ڋq�ŋ敪(*2)<BR>
     * �@@�@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@(*2)�擾�����ڋq�ŋ敪<BR>
     * �@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq(�����ڋq���薾��Params.����ID)��<BR>
     * �@@�@@�@@�@@�@@�ڋq�I�u�W�F�N�g���擾���Ă���A<BR>
     * �@@�@@�@@�@@�@@�ڋq.get��n���ŋ敪(�����ڋq���薾��Params.��n��)�ɂ��A�ڋq�ŋ敪���擾����B<BR>
     * <BR>
     * �@@�|�����ڋq���薾��Params���X�V����B<BR>
     * �@@�|�����ڋq���薾��Params�̕ύX��GTL�w�ɒʒm����B<BR>
     * @@param l_lngOrderUnitId �����P��ID
     * @@param l_lngAssetId ���YID
     * @@param l_isExecuteCancel is�����<BR>
     * ��������ǂ����̃t���O�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4143F6420231
     */
    public void shareContractExecution(
        long l_lngOrderUnitId,
        long l_lngAssetId,
        boolean l_isExecuteCancel)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "shareContractExecution(long, long, boolean)";
        log.entering(STR_METHOD_NAME);

        // �����ڋq���薾�׃f�[�^�̎擾
        List l_lisTrans = dataManager.getEqtypeFinTransactionParams(l_lngOrderUnitId, l_lngAssetId);
        int l_intSize = 0;
        if (l_lisTrans != null)
        {
            l_intSize = l_lisTrans.size();
        }

        if (l_intSize < 1)
        {
            // �����ڋq���薾�ׂ̃f�[�^���O���̏ꍇ�́A����ȉ��̏������s��Ȃ��B
            log.debug("size = 0 and return.");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else if (l_intSize == 1)
        {
            if (!l_isExecuteCancel)
            {
                // �����ڋq���薾�ׂ̃f�[�^���P���ȉ��̏ꍇ�́A����ȉ��̏������s��Ȃ��B
                log.debug("size = 1 and return.");
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        log.debug("fin transcation size:" + Integer.toString(l_lisTrans.size()));
        // �P�����v�Z���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogic = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();

        EqtypeFinTransactionRow[] l_arrayEqtypeFinTransaction = new EqtypeFinTransactionRow[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_arrayEqtypeFinTransaction[i] = (EqtypeFinTransactionRow) l_lisTrans.get(i);
        }
        ConsolidatedCommissionInfo l_commInfo = null;
        try
        {
            l_commInfo = l_bizLogic.calcCommission(l_arrayEqtypeFinTransaction);
        }
        catch (WEB3BaseException e)
        {
            log.error("WEB3BaseException", e);
        }

        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_orderUnit = null;

        try
        {
            l_orderUnit = (EqTypeOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);
			EqtypeFinTransactionRow l_eqtypeFinTranRow = l_arrayEqtypeFinTransaction[0];
			SubAccount l_subAccount =
				l_finApp.getAccountManager().getSubAccount(
				l_eqtypeFinTranRow.getAccountId(), l_eqtypeFinTranRow.getSubAccountId());
			WEB3GentradeMainAccount l_account =
				(WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(
				l_eqtypeFinTranRow.getAccountId());
			TaxTypeEnum l_accountTaxType =
				l_account.getDeliveryDateTaxType(l_eqtypeFinTranRow.getDeliveryDate());

            // �����̂Ƃ��݈̂ȉ������s
            if (l_orderUnit.getSide().equals(SideEnum.BUY))
            {
                // �ϑ��萔�����v�̎擾
                double l_dbCommFeeAmount = getCommissionFeeAmount(l_lisTrans);
                log.debug("�ϑ��萔�����v�F[" + l_dbCommFeeAmount + "]");

                // ����ō��v�̎擾
                double l_dbSalesTaxAmount = getSalesTaxAmount(l_lisTrans);
                log.debug("����ō��v�F[" + l_dbSalesTaxAmount + "]");
                
                log.debug("�i���j�ϑ��萔�����v:[" + l_commInfo.getTotalCommission() + "]");
                log.debug("�i���j����ō��v:[" + l_commInfo.getTotalSalesTax() + "]");
                // ������ۗL���Y�X�V
                updateShareContractAsset(l_lngAssetId, l_dbCommFeeAmount + l_dbSalesTaxAmount, l_commInfo.getTotalCommission() + l_commInfo.getTotalSalesTax());
            }

            EqtypeFinTransactionParams l_row;
            double l_dblCommission;
            double l_dblSalesTax;
            double l_dblPrice;
            double l_dblQuantity;
            BigDecimal l_bdNetAmount;
            int l_intLisTransSize = 0;
            if (l_lisTrans != null)
            {
                l_intLisTransSize = l_lisTrans.size();
            }

            for (int i = 0; i < l_intLisTransSize; i++)
            {
                l_row = (EqtypeFinTransactionParams) l_lisTrans.get(i);

                l_dblCommission = l_commInfo.getCommission(i);
                l_dblSalesTax = l_commInfo.getSalesTax(i);
                l_dblPrice = l_row.getPrice();
                l_dblQuantity = l_row.getQuantity();
                log.debug("�ϑ��萔��[" + i + "]�F[" + l_dblCommission + "]");
                log.debug("�����[" + i + "]�F[" + l_dblSalesTax + "]");
                log.debug("���P��[" + i + "]�F[" + l_dblPrice + "]");
                log.debug("��萔��[" + i + "]�F[" + l_dblQuantity + "]");

                // ��n����̌v�Z
                if (l_orderUnit.getSide().equals(SideEnum.BUY))
                {
                    // ���t�̏ꍇ
                    l_bdNetAmount = new BigDecimal(l_dblPrice);
                    l_bdNetAmount = l_bdNetAmount.multiply(new BigDecimal(l_dblQuantity));
                    l_bdNetAmount = l_bdNetAmount.add(new BigDecimal(l_dblCommission));
                    l_bdNetAmount = l_bdNetAmount.add(new BigDecimal(l_dblSalesTax));
                    l_bdNetAmount = new BigDecimal("0.0").subtract(l_bdNetAmount);
                }
                else if (l_orderUnit.getSide().equals(SideEnum.SELL))
                {
                    // ���t�̏ꍇ
                    l_bdNetAmount = new BigDecimal(l_dblPrice);
                    l_bdNetAmount = l_bdNetAmount.multiply(new BigDecimal(l_dblQuantity));
                    l_bdNetAmount = l_bdNetAmount.subtract(new BigDecimal(l_dblCommission));
                    l_bdNetAmount = l_bdNetAmount.subtract(new BigDecimal(l_dblSalesTax));
                }
                else
                {
                    throw new RuntimeSystemException("FinTransactionType error!");
                }
                log.debug("��n���[" + i + "]�F[" + l_bdNetAmount.doubleValue() + "]");
                
				double l_dblCaptalGain = 0.0D;
				double l_dblCaptalGainTax = 0.0D;
                String l_strCapitalGainStatus = WEB3CapitalGainStatusDef.INVALIDITY;
                // ���n�v���z�A���n�v�Ŋz�̌v�Z�i���t�̏ꍇ�̂݁j
				if (l_orderUnit.getSide().equals(SideEnum.SELL))
				{
					//1.12.1 calc�T�Z���n���v(double, double, long, SubAccount, TaxTypeEnum)
					l_dblCaptalGain = l_bizLogic.calcEstimatedCapitalGain(
						l_bdNetAmount.doubleValue(),
						l_row.getQuantity(),
					    l_row.getProductId(),
					    l_subAccount,
					    l_row.getTaxType());

					//1.12.2 calc���n�v��(�⏕����, TaxTypeEnum, double, Timestamp, TaxTypeEnum)
					l_dblCaptalGainTax =
					    l_bizLogic.calcCapitalGainTax(l_subAccount, l_row.getTaxType(),
					    l_dblCaptalGain, l_row.getDeliveryDate(),l_accountTaxType);

					//1.12.3 get���n�v�L�����(long, long, long, TaxTypeEnum, FinTransactionType)
					l_strCapitalGainStatus = l_bizLogic.getCapitalGainStatus(
						l_row.getAccountId(),
						l_row.getSubAccountId(), 
						l_row.getProductId(), 
						l_row.getTaxType(),
						l_row.getFinTransactionType());

					//1.12.4 setCapitalGainStaus()
					l_row.setCapitalGainStatus(l_strCapitalGainStatus);

					log.debug("���n�v���z[" + i + "]�F[" + l_dblCaptalGain + "]");
					log.debug("���n�v�Ŋz[" + i + "]�F[" + l_dblCaptalGainTax + "]");
				}

                // �����ڋq���薾�׃e�[�u���X�V
                l_row.setCommissionFee(l_dblCommission);
                l_row.setCommissionFeeTax(l_dblSalesTax);
                l_row.setNetAmount(l_bdNetAmount.doubleValue());
                HashMap l_map = new HashMap();
                l_map.put("commission_fee", new BigDecimal(l_row.getCommissionFee()));
                l_map.put("commission_fee_tax", new BigDecimal(l_row.getCommissionFeeTax()));
                l_map.put("net_amount", l_bdNetAmount);
                if (l_orderUnit.getSide().equals(SideEnum.SELL))
                {
					l_map.put("capital_gain", new BigDecimal(l_dblCaptalGain));
					l_map.put("capital_gain_tax", new BigDecimal(l_dblCaptalGainTax));
                    l_map.put("capital_gain_status", l_strCapitalGainStatus);
                }
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                dataManager.updateFinTransaction(l_row, l_map);

                // �ڋq����X�V
                notifyGtl(l_row);
                log.debug("EqtypeFinTransaction.�g�����U�N�V����ID�F[" + l_row.getFinTransactionId() + "]");
                log.debug("EqtypeFinTransaction.�ϑ��萔���F[" + l_row.getCommissionFee() + "]");
                log.debug("EqtypeFinTransaction.�ϑ��萔������ŁF[" + l_row.getCommissionFeeTax() + "]");
            }
        }
        catch (NotFoundException l_nfe)
        {
            String l_strMessage =
                "���������P�ʃf�[�^�^�⏕�����f�[�^�^�����f�[�^��������܂���F order_unit_id("
                + l_lngOrderUnitId + ")";
            log.error(l_strMessage, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (applyTo�ۗL���Y�|�W�V����)<BR>
     * �����ڋq���薾��Params�̓��e�ɂ���ĕۗL���Y���X�V����B<BR>  
     * �iapplyToAssetPosition�̃I�[�o�[���C�h�j<BR>               
     * <BR>                                                 
     * ���V�[�P���X�}�u�i�c���japplyTo�ۗL���Y�|�W�V�����v�Q�ƁB<BR>
     * <BR>
     * �P�j��肵�����Y�������ɕۗL���Y�I�u�W�F�N�g���擾����B <BR> 
     * �@@�@@�i* �����|�W�V�����}�l�[�W��.get�ۗL���Y( )���R�[���j<BR> 
     * <BR>                                   
     * �Q�j�ۗL���Y�������ł������́A�ۗL���Y�̍X�V���s���B <BR>     
     * �@@�@@�ۗL���Y�������ł��Ȃ��������͕ۗL���Y�̑}�����s���B
     * @@param l_trans
     * @@return List
     * @@throws DataException
     * @@roseuid 4143F64203C1
     */
    public List applyToAssetPosition(
        EqtypeFinTransactionParams l_trans)
        throws DataException
    {
        final String STR_METHOD_NAME = "applyToAssetPosition(EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_trans == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3EquityPersistentDataManager l_dataManager =
            (WEB3EquityPersistentDataManager) this.getPersistenceManager();
        
        //2. get�ۗL���YParams()
        AssetParams l_assetParams = l_dataManager.getAsset(l_trans);
        
        //4. getFinTransactionType()
        FinTransactionType l_finTransactionType = l_trans.getFinTransactionType();
        
        //5. getSide()
        SideEnum l_sideEnum = this.getSide(l_finTransactionType);
        
        // �����(SideEnum.BUY)�ŕۗL���YParams��null�̎�
        if (l_assetParams == null && SideEnum.BUY.equals(l_sideEnum))
        {
            l_assetParams = new AssetParams();
            //6. set�ۗL���Y�f�t�H���g�l()
            this.setAssetDefaultValues(l_assetParams);
            //7. set�ۗL���YParams()
            this.setNewAssetParamsFromMarketTradedTrans(l_assetParams, l_trans);
            //9. saveNewAsset()
            l_dataManager.saveNewAsset(l_assetParams);
        }
        // �����(SideEnum.BUY)�ŕۗL���YParams��null�łȂ���
        else if (l_assetParams != null && SideEnum.BUY.equals(l_sideEnum))
        {
            //10. update�ۗL���YParams()
            this.updateAssetParamsFromMarketTradedTrans(l_assetParams, l_trans);
            //12. updateAssetByTrans()
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_dataManager.updateAssetByTrans(l_assetParams);
        }
        // �����(SideEnum.SELL)�̎�
        else if (SideEnum.SELL.equals(l_sideEnum))
        {
            //13. update�ۗL���YParams()
            this.updateAssetParamsFromMarketTradedTrans(l_assetParams, l_trans);
            //14. updateAssetByTrans()
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_dataManager.updateAssetByTrans(l_assetParams);
        }
        //15. setAssetId()
        l_trans.setAssetId(l_assetParams.getAssetId());
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (�������c���X�V����)<BR>
     * ����������莞�̌ڋq�c�����X�V����B<BR>
     * �iprocessCashBasedOrderExecution���I�[�o�[���C�h�j<BR>
     * <BR>
     * �m�����T�v�n<BR>
     * �P�j�g�����U�N�V�����e�[�u���̂P�s��\���B�����ڋq���薾��Params��<BR>
     *    �C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�����ڋq���薾��Params�Ƀf�t�H���g�l���Z�b�g����B<BR>
     * <BR>
     * �R�jset�����ڋq���薾��Params���\�b�h���R�[������B<BR>�@@�@@
     * �@@�@@�|�����ڋq���薾��Params�̃v���p�e�B�ɖ������Z�b�g����B<BR>
     * �@@�@@�|�v�Z�T�[�r�X���A�ϑ��萔���v�Z�E����ł����߂�B<BR>
     * �@@�@@�@@ ������̏ꍇ�̂݁A�v�Z�T�[�r�X.calc���n���v( )�A<BR> 
     *         �v�Z�T�[�r�X.calc���n�v��( )���s���B<BR>
     * �@@�@@�|��n��������߂�B<BR>
     * �@@�@@�@@�i����莞�j<BR>
     * �@@�@@�@@�@@��n��� ���O�|�i�����z�{�ϑ��萔���{����Łj<BR>
     * �@@�@@�@@�i����莞�j<BR>
     * �@@�@@�@@�@@��n����������z�|�i�ϑ��萔���{����Łj<BR>
     * <BR>
     * �S�japplyTo�ۗL���Y�|�W�V����( )���\�b�h���R�[������B<BR>
     * <BR>
     * �T�j�����ڋq���薾��Params�ɃZ�b�g���ꂽ���Ńg�����U�N�V����<BR>
     *     �f�[�^�e�[�u���ɂP�s�}������B<BR>
     * <BR>
     * �U�j�����菈�����R�[������B
     * @@param l_exec ���
     * @@throws DataException
     * @@roseuid 401864E900CE
     */
    public void processCashBasedOrderExecution(EqTypeOrderExecution l_exec) throws DataException
    {
        final String STR_METHOD_NAME = "processCashBasedOrderExecution(" + "EqTypeOrderExecution)";

        log.entering(STR_METHOD_NAME);

        try
        {
            EqtypeFinTransactionParams l_trans = new EqtypeFinTransactionParams();
            setMarketOrderedTransDefaultValues(l_trans);
            setEqtypeFinTransactionParams(l_trans, l_exec);
            applyToAssetPosition(l_trans);
            dataManager.saveNewFinTransaction(l_trans);
            notifyGtl(l_trans);
            shareContractExecution(l_trans.getOrderUnitId(), l_trans.getAssetId(), false);
        }
        catch (WEB3BaseException l_wbex)
        {
            throw new WEB3BaseRuntimeException(l_wbex.getErrorInfo(), STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iset�����ڋq���薾��Params�j<BR>
     * <BR>
     * �����ڋq���薾��Params�̃v���p�e�B��<BR>
     * �����E�ϑ��萔���E����ŁE���n�v���z�E���n�v�ŁE��n������Z�b�g����B<BR>
     * ���n�v���z�E���n�v�łɂ��ẮA�}�C�i�X�l�̏ꍇ�����̂܂܃Z�b�g����B<BR>
     * �i* ���������̏o���ʒm�T�[�r�X�ɂĎg�p�j<BR>
     * <BR>
     * �P�j�g���|�W�V�����w���p�[.set�����To�����ڋq���薾��(�����ڋq���薾��,���)<BR>
     * �@@�@@���R�[�����A<BR>
     * �@@�@@�����ڋq���薾��Params�ɖ��I�u�W�F�N�g�̒l���Z�b�g����B<BR>
     * <BR>
     * �Q�j���I�u�W�F�N�g��蒍��ID�A�����P��ID�A����ID�A�⏕����ID���擾����B<BR>
     * <BR>
     * �R�j�����P��ID�������ɒ����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�|�g�����������}�l�[�W���[�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�|�g�����������}�l�[�W���[�I�u�W�F�N�g.getOrderUnit()���R�[������B<BR>
     * <BR>
     * �S�j����ID�A�⏕����ID���⏕�����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�|�g���A�J�E���g�}�l�[�W���[�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�|�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B<BR>
     * <BR>
     * �T�j�萔���I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �@@�@@�����v�Z�T�[�r�X.create�萔��( �Q�j�Ŏ擾��������ID)���R�[������B<BR>
     * <BR>
     * �U�j�萔���I�u�W�F�N�g.���o��v�Z�p����ɁA��������Z�b�g����B<BR>
     * <BR>
     * �@@�@@�E���o��v�Z�p���<BR>
     * �@@�@@�@@�˖�����i��萔�ʁ~���P���j����萔�ʁA���P���͖��I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �V�j������ɑ΂���A�ϑ��萔�����擾����B<BR>
     * �@@�@@�|�����v�Z�T�[�r�X�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�|�����v�Z�T�[�r�X.calc�ϑ��萔�����R�[������B<BR>
     * �@@�@@�|�萔���I�u�W�F�N�g.get�萔�����z���R�[������B<BR>
     * <BR>
     * �W�j�ϑ��萔���ɑ΂���A����ł��擾����B<BR>
     * �@@�@@�|�����v�Z�T�[�r�X.calc����ł��R�[������B<BR>
     * �@@�@@�@@�������P�ʃI�u�W�F�N�g.����������̓����Ƃ���B<BR>
     * <BR>
     * �X�j�ϑ��萔���^����ł������ڋq���薾��Params�ɃZ�b�g����B<BR>
     * <BR>
     * �P�O�j��n����������ڋq���薾��Params�ɃZ�b�g����B<BR>
     * �@@�@@[�v�Z��]<BR>
     * �@@�@@�i����莞�j<BR>
     * �@@�@@�@@��n��� ���O�|�i�����z�{�ϑ��萔���{����Łj<BR>
     * �@@�@@�i����莞�j<BR>
     * �@@�@@�@@��n����������z�|�i�ϑ��萔���{����Łj<BR>
     * <BR>
     * �P�P�j����莞�̂݁A������ɑ΂�����n�v���z�A���n�v�Ŋz���擾���A<BR>
     * �@@�@@�@@�����ڋq���薾��Params�ɃZ�b�g����B�i�}�C�i�X�l�̏ꍇ�����̂܂܃Z�b�g�j<BR>
     * <BR>
     * �@@�@@�|�����v�Z�T�[�r�X.calc���n���v( )���R�[������B�߂�l���u���n�v���z�v�ɃZ�b�g�B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���n���v(double, double, long, SubAccount, TaxTypeEnum)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@���z�F�@@�P�O�j�Ōv�Z������n���<BR>
     * �@@�@@�@@�����ʁF�@@�����̖��.��萔��<BR>
     * �@@�@@�@@����ID�F�@@�����̖��.����ID<BR>
     * �@@�@@�@@�⏕�����F�@@�����̖��.����ID�A�⏕����ID�ɊY������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�ŋ敪�F�@@�����̖��.�����P��ID�ɊY�����钍���P��.�ŋ敪<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�|�����v�Z�T�[�r�X.calc���n�v��( )���R�[������B�߂�l���u���n�v�Ŋz�v�ɃZ�b�g�B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���n�v��(�⏕����, TaxTypeEnum, double, Timestamp, TaxTypeEnum)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�ŋ敪�F�@@�����̖��.�����P��ID�ɊY�����钍���P��.�ŋ敪<BR>
     * �@@�@@�@@���z�F�@@�v�Z�������n�v���z�icalc���n���v( )�̖߂�l�j<BR>
     * �@@�@@�@@����F�@@�����̖��.��n��<BR>
     * �@@�@@�@@�ڋq�ŋ敪�F�@@�擾�����ڋq�ŋ敪(*1)<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@(*1)�擾�����ڋq�ŋ敪<BR>
     * �@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq(�����̊����ڋq���薾��Params.����ID)��<BR>
     * �@@�@@�@@�@@�@@�ڋq�I�u�W�F�N�g���擾���Ă���A<BR>
     * �@@�@@�@@�@@�@@�ڋq.get��n���ŋ敪(�����̖��.��n��)�ɂ��A�ڋq�ŋ敪���擾����B<BR>
     * <BR>
     * @@param l_trans �����ڋq���薾��Params
     * @@param l_exec ���
     * @@throws DataQueryException, DataNetworkException
     * @@roseuid 413D15BA0329
     */
    public void setEqtypeFinTransactionParams(
        EqtypeFinTransactionParams l_trans,
        EqTypeOrderExecution l_exec)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "setEqtypeFinTransactionParams(EqtypeFinTransactionParams, EqTypeOrderExecution)";
        String l_strMessage;

        log.entering(STR_METHOD_NAME);
        if (l_exec == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            // �P�j�g���|�W�V�����w���p�[.setExecutionInfoToMarketOrderedTrans(�����ڋq���薾��,���)
            // �@@�@@���R�[�����A�����ڋq���薾��Params�ɖ��I�u�W�F�N�g�̒l���Z�b�g����B
            setExecutionInfoToMarketOrderedTrans(l_trans, l_exec);

            // �Q�j���I�u�W�F�N�g��蒍��ID�A�����P��ID�A����ID�A�⏕����ID���擾����B
            EqtypeOrderExecutionRow l_execRow =
                (EqtypeOrderExecutionRow)l_exec.getDataSourceObject();
            long l_lngOrderID = l_execRow.getOrderId();
            long l_lngOrderUnitID = l_exec.getOrderUnitId();
            long l_lngAccountID = l_exec.getAccountId();
            long l_lngSubAccountID = l_exec.getSubAccountId();

            // �R�j�����P��ID�������ɒ����P�ʃI�u�W�F�N�g���擾����B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit = null;
            l_orderUnit = (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_lngOrderUnitID);
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            // �S�j����ID�A�⏕����ID���⏕�����I�u�W�F�N�g���擾����B
            SubAccount l_subAccount = null;
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_lngAccountID,
                    l_lngSubAccountID);

            // �T�j�萔���I�u�W�F�N�g���쐬����B
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_orderUnit, l_exec);

            // �U�j�萔���I�u�W�F�N�g.���o��v�Z�p����ɁA��������Z�b�g����B
            double l_dblAmount = l_exec.getExecutionQuantity() * l_exec.getExecutionPrice();
            l_commission.setExpensesCalcAmount(l_dblAmount);

            // �V�j������ɑ΂���A�ϑ��萔�����擾����B
            WEB3EquityBizLogicProvider l_bizLogic = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            l_bizLogic.calcCommission(l_commission, l_subAccount);
            double l_dblCommissionFee = l_commission.getCommission();

            // �W�j�ϑ��萔���ɑ΂���A����ł��擾����B
            Date l_datBizDate = null;
            try
            {
                l_datBizDate =
                    GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().parse(l_orderUnitRow.getBizDate());
            }
            catch (ParseException l_pe)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_pe.getMessage(),
                    l_pe);
            }
            double l_dblCommissionFeeTax = 0.0;
            l_dblCommissionFeeTax =
                l_bizLogic.calcSalesTax(
                    l_dblCommissionFee,
                    new Timestamp(l_datBizDate.getTime()),
                    l_subAccount);
            
            // �X�j�ϑ��萔���^����ł������ڋq���薾��Params�ɃZ�b�g����B
            l_trans.setCommissionFee(l_dblCommissionFee);
            l_trans.setCommissionFeeTax(l_dblCommissionFeeTax);
            
            // �P�O�j��n����������ڋq���薾��Params�ɃZ�b�g����B
			 double l_dblNetAmount = 0.0D;
			 if (l_orderUnit.getSide().equals(SideEnum.BUY))
			 {
				 // �����̏ꍇ
				l_dblNetAmount = 0.0D - (l_dblAmount + l_dblCommissionFee + l_dblCommissionFeeTax);

			 }
			 else if (l_orderUnit.getSide().equals(SideEnum.SELL))
			 {
				 // �����̏ꍇ
				l_dblNetAmount = l_dblAmount - (l_dblCommissionFee + l_dblCommissionFeeTax);
			 }
			 else
			 {
				 throw new RuntimeSystemException("FinTransactionType error!");
			 }
			 l_trans.setNetAmount(l_dblNetAmount);

            // �P�P�j����莞�̂݁A������ɑ΂�����n�v���z�A���n�v�Ŋz���擾���A
            // �@@�@@�@@�����ڋq���薾��Params�ɃZ�b�g����B�i�}�C�i�X�l�̏ꍇ�����̂܂܃Z�b�g�j
            if (l_orderUnit.getSide().equals(SideEnum.SELL))
            {
                double l_dblCaptalGain =
                    l_bizLogic.calcEstimatedCapitalGain(
				        l_dblNetAmount,
                        l_exec.getExecutionQuantity(),
                        l_exec.getProduct().getProductId(),
                        l_subAccount,
                        l_orderUnitRow.getTaxType());
                
                WEB3GentradeMainAccount l_account =
                    (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_lngAccountID);
                TaxTypeEnum l_accountTaxType =
                    l_account.getDeliveryDateTaxType(l_exec.getDeliveryDate());
                double l_dblCaptalGainTax =
                    l_bizLogic.calcCapitalGainTax(
                        l_subAccount,
                        l_orderUnitRow.getTaxType(),
                        l_dblCaptalGain,
                        new Timestamp(l_exec.getDeliveryDate().getTime()),
                        l_accountTaxType);
                l_trans.setCapitalGain(l_dblCaptalGain);
                l_trans.setCapitalGainTax(l_dblCaptalGainTax);
                
                //1.9.6 get���n�v�L�����(long, long, long, TaxTypeEnum, FinTransactionType)
                String l_strCapitalGainStatus = 
					l_bizLogic.getCapitalGainStatus(
						l_trans.getAccountId(),
						l_trans.getSubAccountId(), 
						l_trans.getProductId(),
						l_trans.getTaxType(),
						l_trans.getFinTransactionType());

                //1.9.7 setCapitalGainStatus()
                l_trans.setCapitalGainStatus(l_strCapitalGainStatus);
            }
        }
        catch (WEB3BaseException e)
        {
            l_strMessage = "set�����ڋq���薾��Params���ُ�I�����܂���";
            log.error(l_strMessage, e);
            throw new RuntimeSystemException(l_strMessage, e);
        }
        catch (NotFoundException e)
        {
            log.error(e.getMessage());
            throw new RuntimeSystemException("set�����ڋq���薾��Params���ُ�I�����܂���", e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iset�ۗL���YParams�j<BR>
     * <BR>
     * �m�����T�v�n<BR>
     * �����ڋq���薾��Params���ۗL���YParams�̊e�v���p�e�B�ɒl���Z�b�g����B<BR>
     * �isetNewAssetParamsFromMarketTradedTrans���I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�ۗL���YParam�̊e�����ɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�E�ۗL���YParams.�ŋ敪<BR>
     * �@@�@@��   �E�����̊����ڋq���薾��Params.�g�����U�N�V�����^�C�v<BR>
     * �@@�@@�@@�@@�@@�@@����������iEQTYPE_SWAP_MARGIN_LONG�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�����P��ID�ɊY������<BR>
     * �@@�@@�@@�@@�@@�@@�����P�ʃI�u�W�F�N�g.�ŋ敪�i�������n�j ���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�E�����̊����ڋq���薾��Params.�g�����U�N�V�����^�C�v<BR>
     * �@@�@@�@@�@@�@@�@@����������iEQTYPE_SWAP_MARGIN_LONG�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�ŋ敪 ���Z�b�g����B<BR>
     * �@@<BR>
     * �@@�E�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j<BR>
     * �@@�@@�ˁ@@�E�ȉ��̒ʂ�ɒl���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������̊����ڋq���薾��Params.�g�����U�N�V�����^�C�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����������iEQTYPE_SWAP_MARGIN_LONG�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�P�jthis.calc��n����i�������n�j( )�R�[���ɂ��A��n������v�Z����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@-------------------------------------------<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��calc��n����i�������n�j( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����ڋq���薾��Params�F�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�@@�����̊����ڋq���薾��Params�����̂܂܃Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@-------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�Q�jcalc��n����i�������n�j( )�̖߂�l�̕����𔽓]�����l���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��calc��n����i�������n�j( )�̖߂�l�~�i�|�P�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������̊����ڋq���薾��Params.�g�����U�N�V�����^�C�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����������iEQTYPE_SWAP_MARGIN_LONG�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@ �@@�@@�@@�@@�����ڋq���薾��Params.��n��� �̕����𔽓]�����l���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ڋq���薾��Params.��n����~�i�|�P�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �@@�E�ۗL���YParams.����ID<BR>
     * �@@�@@�ˊ����ڋq���薾��Params.����ID���Z�b�g����B<BR>
     * <BR>
     * �@@�E�ۗL���YParams.�⏕����ID<BR>
     * �@@�@@�ˊ����ڋq���薾��Params.�⏕����ID���Z�b�g����B<BR>
     * <BR>
     * �@@�E�ۗL���YParams.�����^�C�v<BR>
     * �@@�@@�ˊ����ڋq���薾��Params.�����^�C�v���Z�b�g����B<BR>
     * <BR>
     * �@@�E�ۗL���YParams.����<BR>
     * �@@�@@�ˊ����ڋq���薾��Params.��萔�ʂ��Z�b�g����B<BR>
     * <BR>
     * �@@�E�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j<BR>
     * �@@�@@�ˊ����ڋq���薾��Params.��萔�ʂ��Z�b�g����B<BR>
     * <BR>
     * �@@�E�ۗL���YParams.����ID<BR>
     * �@@�@@�ˊ����ڋq���薾��Params.����ID���Z�b�g����B
     * @@param l_assetParams �ۗL���YParams
     * @@param l_trans �����ڋq���薾��Params>
     * @@throws RuntimeSystemException
     * @@roseuid 413D15BB0059l_orderUnitId
     */
    public void setNewAssetParamsFromMarketTradedTrans(
        AssetParams l_assetParams,
        EqtypeFinTransactionParams l_trans)
    {
        final String STR_METHOD_NAME = "setNewAssetParamsFromMarketTradedTrans(EqtypeAssetParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_trans == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            long l_orderUnitId = l_trans.getOrderUnitId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            if (FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_trans.getFinTransactionType()))
            {
                OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_orderUnitId);
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_assetParams.setTaxType(l_orderUnitRow.getSwapTaxType());
                try
                {
                    l_assetParams.setBookValue(this.calcNetAmountSwap(l_trans) * -1.0D);
                }
                catch (WEB3BaseException l_ex)
                {
                    l_assetParams.setBookValue(0.0D);
                }
            }
            else
            {
                l_assetParams.setTaxType(l_trans.getTaxType());
                l_assetParams.setBookValue(l_trans.getNetAmount() * -1.0D);
            }
            l_assetParams.setAccountId(l_trans.getAccountId());
            l_assetParams.setSubAccountId(l_trans.getSubAccountId());
            l_assetParams.setProductType(l_trans.getProductType());
            l_assetParams.setQuantity(l_trans.getQuantity());
            l_assetParams.setQuantityForBookValue(l_trans.getQuantity());
            l_assetParams.setProductId(l_trans.getProductId());
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_nfe)
        {
            String l_strMessage = "�����P�ʃe�[�u������ error";
            log.error(l_strMessage, l_nfe);
            log.error(l_nfe.getMessage());
            throw new RuntimeSystemException(l_strMessage, l_nfe);
        }
    }

    /**
     * �iupdate�ۗL���YParams�j<BR>
     * <BR>
     * �擾�����ۗL���YParams�̒l���X�V����B<BR>
     * �ivoid updateAssetParamsFromMarketTradedTrans(AssetParams aparams,<BR>
     * �@@EqtypeFinTransactionParams trans)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ------------------------------------------------------------------------<BR>
     * �i���������A�������̎��j<BR>
     * �P�j�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j �̒l��ύX����B<BR>
     * �@@�@@�i* �ۗL���YParams.�ŋ敪���i"�������"�܂���"������������򒥎�"�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�������ōX�V�ΏۂƂ���B<BR>
     * �@@�@@�@@�@@�ۗL���YParams.�ŋ敪���i"�������"�܂���"������������򒥎�"�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�|�i�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j��0<BR>
     * �@@�@@�@@�@@�@@�@@�@@���� �ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j��0�j�̏ꍇ�͍X�V�ΏۊO<BR>
     * �@@�@@�@@�@@�@@�|��L�ȊO�̏ꍇ�͍X�V�Ώ�<BR>
     * �@@�@@�@@�@@�Ƃ���B�j<BR>
     * <BR>
     * �@@�@@�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j<BR>
     * �@@�@@�ˁ@@�E�ȉ��̒ʂ�ɒl���X�V����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������̊����ڋq���薾��Params.�g�����U�N�V�����^�C�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����������iEQTYPE_SWAP_MARGIN_LONG�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�P�jthis.calc��n����i�������n�j( )�R�[���ɂ��A��n������v�Z����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@-------------------------------------------<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��calc��n����i�������n�j( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����ڋq���薾��Params�F�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�@@�����̊����ڋq���薾��Params�����̂܂܃Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@-------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�Q�jcalc��n����i�������n�j( )�̖߂�l�̕����𔽓]�����l�����Z����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��calc��n����i�������n�j( )�̖߂�l�~�i�|�P�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@------------------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������̊����ڋq���薾��Params.�g�����U�N�V�����^�C�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����������iEQTYPE_SWAP_MARGIN_LONG�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@ �@@�@@�@@�@@�����ڋq���薾��Params.��n��� �̕����𔽓]�����l�����Z����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ڋq���薾��Params.��n����~�i�|�P�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@------------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�ۗL���YParams.���ʂ̒l��ύX����B<BR>
     * <BR>
     * �@@�@@�ۗL���YParams.���ʁ@@<BR>
     * �@@�@@���@@<BR>
     * �@@�@@�ۗL���YParams.���� �{ �����ڋq���薾��Params.��萔��<BR>
     * <BR>
     * �R�j�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j�̒l��ύX����B<BR>
     * <BR>
     * �@@�@@�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j�@@<BR>
     * �@@�@@���@@<BR>
     * �@@�@@�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j �{ �����ڋq���薾��Params.��萔��<BR>
     * <BR>
     * ------------------------------------------------------------------------<BR>
     * �i���������A���n���̎��j<BR>
     * �P�j�ۗL���Y�c���ʃ`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�ۗL���Y�c����(*1) �� 0.0D �̏ꍇ�A<BR>
     * �@@�@@�u�ۗL���Y�c���ʃ`�F�b�N�G���[�v�̗�O�i�Ɩ��G���[�j��throw����B<BR>
     * �@@�@@class: WEB3BaseRuntimeException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01931<BR>
     * <BR>
     * �@@�@@(*1)�ۗL���Y�c����<BR>
     * �@@�@@�@@�@@�i�ۗL���YParams.���� �{ �ۗL���YParams.���t�s�\���ʁj<BR>
     *            �| �����ڋq���薾��Params.��萔��<BR>
     * <BR>
     * �Q�j�ۗL���YParams.���ʂ̒l��ύX����B<BR>
     * <BR>
     * �@@�@@�ۗL���YParams.���ʁ@@<BR>
     * �@@�@@���@@<BR>
     * �@@�@@�ۗL���YParams.���ʁ@@�|�@@�����ڋq���薾��Params.��萔��<BR>
     * <BR>
     * �@@�@@�������A�i�ۗL���YParams.���� �� �����ڋq���薾��Params.��萔�ʁj�̏ꍇ�́A<BR>
     * �@@�@@����Ȃ����̐��ʂ� �ۗL���YParams.���t�s�\���� ���猸�Z����B<BR>
     * ------------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_assetParams �ۗL���YParams
     * @@param l_trans �����ڋq���薾��Params
     * @@roseuid 413D15BB00E5
     */
    public void updateAssetParamsFromMarketTradedTrans(
        AssetParams l_assetParams,
        EqtypeFinTransactionParams l_trans)
        throws RuntimeSystemException
    {
        final String STR_METHOD_NAME = "updateAssetParamsFromMarketTradedTrans(AssetParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_trans == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �i���������A�������̎��j
        if (FinTransactionType.EQTYPE_EQUITY_BUY.equals(l_trans.getFinTransactionType()) ||
            FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_trans.getFinTransactionType()))
        {
            // �P�j�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j �̒l��ύX����B
            if (!TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetParams.getTaxType()) &&
                !TaxTypeEnum.SPECIAL.equals(l_assetParams.getTaxType()) &&
                (l_assetParams.getQuantityForBookValue() > 0.0D && l_assetParams.getBookValue() == 0.0D))
            {
                // �X�V�ΏۊO
            }
            else
            {
                if (FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_trans.getFinTransactionType()))
                {
                    try
                    {
                        l_assetParams.setBookValue(
	                        l_assetParams.getBookValue() + (this.calcNetAmountSwap(l_trans) * -1.0D));
                    }
                    catch (WEB3BaseException l_ex)
                    {
                        l_assetParams.setBookValue(0.0D);
                    }
                }
                else
                {
                    l_assetParams.setBookValue(
                        l_assetParams.getBookValue() + (l_trans.getNetAmount() * -1.0D));
                }
            }
            // �Q�j�ۗL���YParams.���ʂ̒l��ύX����B
            l_assetParams.setQuantity(l_assetParams.getQuantity() + l_trans.getQuantity());
            // �R�j�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j�̒l��ύX����B
            l_assetParams.setQuantityForBookValue(l_assetParams.getQuantityForBookValue() + l_trans.getQuantity());
        }
        // �i���������A���n�̎��j
        else if (FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_trans.getFinTransactionType()) ||
                  FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_trans.getFinTransactionType()))
        {
            // �P�j�ۗL���Y�c���ʃ`�F�b�N���s���B
            double l_dblQuantity = (l_assetParams.getQuantity() + l_assetParams.getQuantityCannotSell())
                 - l_trans.getQuantity();
            if (l_dblQuantity < 0.0D)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            // �Q�j�ۗL���YParams.���ʂ̒l��ύX����B
            // ���ʂ����ł͑���Ȃ��ꍇ�́A���t�s�\���ʂ��猸�Z����B
            if (l_assetParams.getQuantity() >= l_trans.getQuantity())
            {
				l_assetParams.setQuantity(l_assetParams.getQuantity() - l_trans.getQuantity());
            }
            else
            {
				l_assetParams.setQuantity(0);
				l_assetParams.setQuantityCannotSell(
				    l_assetParams.getQuantityCannotSell()
				        - (l_trans.getQuantity() - l_assetParams.getQuantity()));
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�ϑ��萔�����v)<BR>
     * �m�����T�v�n<BR>                                                          
     * �����̊����ڋq���薾��Params���X�g�́A�ϑ��萔���̍��v���擾����B<BR>    
     * <BR>                                                               
     * �P�j�@@�����ڋq���薾��Params���X�g�Ɋi�[����Ă��� <BR>                
     * �@@�@@�@@�����ڋq���薾��Params�I�u�W�F�N�g���A�ꌏ���擾����B<BR>
     * �@@-�����ڋq���薾��Params�I�u�W�F�N�g���A�ϑ��萔���̒l���擾����B<BR>
     * �@@-�擾�����ϑ��萔�������v����B<BR>                                   
     *   <BR>                                                                    
     * �Q�j�@@�ϑ��萔���̍��v��ԋp����B <BR>                                   
     * <BR>
     * @@param l_lisTrans �����ڋq���薾��Params���X�g
     * @@return double
     * @@roseuid 4143F6430386
     */
    public double getCommissionFeeAmount(List l_lisTrans)
    {
        final String STR_METHOD_NAME = "getCommissionFeeAmount(List)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisTrans == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        double l_dbCommissionFee = 0.0D;
        EqtypeFinTransactionParams l_row;
        ListIterator l_iterator = l_lisTrans.listIterator();
        while (l_iterator.hasNext())
        {
            l_row = (EqtypeFinTransactionParams)l_iterator.next();
            l_dbCommissionFee += l_row.getCommissionFee();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dbCommissionFee;
    }

    /**
     * (������ۗL���Y�X�V)<BR>
     * <BR>
     * �m�����T�v�n<BR>
     * �P�j���YID���ۗL���YParam���擾����B<BR>
     * <BR>
     * �Q�j�ۗL���YParams.�ŋ敪��"��ʌ���"����<BR>
     * �@@�@@�ۗL���YParam.���ʁi�뉿�P���v�Z�p�j��0 ����<BR>
     * �@@�@@�ۗL���YParam.�뉿�i�뉿�P���v�Z�p�j��0�̏ꍇ�A<BR>
     * �@@�@@��������������return����B <BR>
     * <BR>
     * �R�j��L�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�R�|�P�j�ۗL���YParam�뉿�i�뉿�P���v�Z�p�j �̒l���X�V����B<BR>
     * �@@�@@�@@���X�V��뉿 = �X�V�O�뉿�|�i�v�Z�O�j���o��v�{�i�v�Z��j���o��v<BR>
     * �@@�@@�@@�����o��i�ϑ��萔���{����Łj<BR>
     * <BR>
     * �@@�R�|�Q�j�X�V���ꂽ�ۗL���YParams��ۗL���Y�e�[�u���ɕۑ�����B<BR>
     * �@@�@@�@@���g���f�[�^�}�l�[�W���[.updateAssetByTrans()���R�[��<BR>
     * @@param l_lngAssetId ���YID
     * @@param l_bdBefor ������v�Z�O�̏��o��v
     * @@param l_bdAfter ������v�Z��̏��o��v
     * @@throws DataNetworkException, DataQueryException
     * @@roseuid 401A33AB0371
     */
    public void updateShareContractAsset(long l_lngAssetId, double l_bdBefor, double l_bdAfter) throws DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "updateShareContractAsset(long, double, double)";
        log.entering(STR_METHOD_NAME);

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            EqTypePositionManager l_positionManager = (EqTypePositionManager) l_tradingModule.getPositionManager();
            EqTypeAsset l_asset = null;
            l_asset = (EqTypeAsset) l_positionManager.getAsset(l_lngAssetId);
            AssetParams l_row = (AssetParams) l_asset.getDataSourceObject();
            if (TaxTypeEnum.NORMAL.equals(l_row.getTaxType()) && 
                l_row.getQuantityForBookValue() > 0 &&
                l_row.getBookValue() == 0)
            {
                return;
            }
            BigDecimal l_bdBookValue = new BigDecimal(l_asset.getBookValue());
            log.debug("�X�V�O�뉿�i�뉿�P���v�Z�p�j�F[" + l_bdBookValue.doubleValue() + "]");
            double l_dblBookValue = l_bdBookValue.doubleValue() - l_bdBefor + l_bdAfter;

            l_row.setBookValue(l_dblBookValue);
            l_row.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            dataManager.updateAssetByTrans(l_row);
        }
        catch (NotFoundException e)
        {
            String l_strMessage = "������ۗL���Y���X�V�ł��܂���";
            log.error(l_strMessage, e);
            throw new RuntimeSystemException(l_strMessage, e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�ڋq����X�V)<BR>
     * �m�����T�v�n<BR>
     * <BR>
     * �����ڋq���薾��Params�ɑ΂��ċN�������X�V���A�ڋq���薾�ׂɔ��f������B<BR>
     * �inotifyGtl���I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�����ڋq���薾��Params���A�ڋq���薾��Params�I�u�W�F�N�g���쐬����B<BR>
     * �@@�@@�iGeneralizedFinTransaction�̃R���X�g���N�^���R�[���j<BR>
     * <BR>
     * �Q�j�ڋq���薾�׃I�u�W�F�N�g��GTL�w�ɒʒm����B<BR>
     * �@@�@@�i�ėp�g�����U�N�V�����}�l�[�W���[.notifyTransaction���\�b�h�j<BR>
     * <BR>
     * �R�j�X�V���ꂽ��n���z�Ōڋq���薾�׃��R�[�h���X�V����B
     * @@param l_trans �����ڋq���薾��Params
     * @@roseuid 413D15BB026C
     */
    public void notifyGtl(EqtypeFinTransactionParams l_trans)
    {
        final String STR_METHOD_NAME = "notifyGtl(EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_trans == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            FinTransactionType l_finTransactionType = l_trans.getFinTransactionType();
            GeneralizedFinTransaction l_genFinTransaction =
                new GeneralizedFinTransaction(
                    l_trans.getFinTransactionId(),
                    l_trans.getAccountId(),
                    l_trans.getSubAccountId(),
                    l_trans.getDeliveryDate(),
                    l_finTransactionType,
                    l_trans.getNetAmount(),
                    "EqtypeFinTransaction productId("
                        + l_trans.getProductId()
                        + "), marketId("
                        + l_trans.getMarketId()
                        + "), quantity("
                        + l_trans.getQuantity()
                        + "), price("
                        + l_trans.getPrice()
                        + ")",
                    getTradingModule().getTradingModuleName(),
                    l_trans.getCreatedTimestamp());
            ProcessingResult l_result = m_finApp.getGeneralizedFinTransactionManager().notifyTransaction(l_genFinTransaction);

            if (l_result.isFailedResult())
            {
                throw new RuntimeSystemException("Method addTransaction failed:" + l_result.getErrorInfo());
            }

            String l_strWhere = " transaction_id=? ";
            Map l_mapChanges = new HashMap();

            l_mapChanges.put("net_amount", new Double(l_trans.getNetAmount()));
            Object[] l_objBindVars = { new Double(l_trans.getFinTransactionId())};
            Processors.getDefaultProcessor().doUpdateAllQuery(GenFinTransactionRow.TYPE, l_strWhere, l_objBindVars, l_mapChanges);
        }
        catch (DataNetworkException e)
        {
            String l_strMessage = "��n���z���X�V�ł��܂���";
            log.error(l_strMessage, e);
            throw new RuntimeSystemException(l_strMessage, e);
        }
        catch (DataQueryException e)
        {
            String l_strMessage = "��n���z���X�V�ł��܂���";
            log.error(l_strMessage, e);
            throw new RuntimeSystemException(l_strMessage, e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�ϑ��萔������ō��v)<BR>
     * �m�����T�v�n<BR>                                                                       
     * �����̊����ڋq���薾��Params���X�g�́A�ϑ��萔������ł̍��v���擾����B<BR>               
     *  <BR>                                                                             
     * �P�j�@@�����ڋq���薾��Params���X�g�Ɋi�[����Ă���<BR>
     *       �����ڋq���薾��Params�I�u�W�F�N�g���ꌏ���擾����B<BR>                                                          
     * �@@-�����ڋq���薾��Params�I�u�W�F�N�g���A�ϑ��萔������ł̒l���擾����B<BR>            
     * �@@-�擾�����ϑ��萔������ł����v����B<BR>                                            
     *  <BR>                                                        
     * �Q�j�@@�ϑ��萔������ł̍��v��ԋp����B<BR>                                           
     * <BR>
     * @@param l_lisTrans �����ڋq����Params���X�g
     * @@return double
     * @@roseuid 4143F64400F3
     */
    public double getSalesTaxAmount(List l_lisTrans)
    {
        final String STR_METHOD_NAME = "getSalesTaxAmount(List)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisTrans == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        double l_dbCommissionFeeTax = 0.0D;
        EqtypeFinTransactionParams l_row;
        ListIterator l_iterator = l_lisTrans.listIterator();
        while (l_iterator.hasNext())
        {
            l_row = (EqtypeFinTransactionParams)l_iterator.next();
            l_dbCommissionFeeTax += l_row.getCommissionFeeTax();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dbCommissionFeeTax;
    }

    /**
     * (get����)<BR>
     * �y�����e�[�u���z����A�����̒l���w�肵�Y�����錚��Params�I�u�W�F�N�g��<BR>
     * �擾���ԋp����B<BR>
     * �iEqtypeContractParams getContract(EqtypeFinTransactionParams <BR>
     * tparams)<BR>
     * �̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�g���f�[�^�}�l�[�W��.get����(����ID, �⏕����ID, �s��ID, ����ID, ���敪, ���P��,<BR>
     * �@@�@@�@@ ����, ����, �ŋ敪, �ٍϋ敪, �ٍϊ����l, ��������)�ɈϏ�����B<BR>
     * <BR>
     * �@@�@@�@@��L���\�b�h�̃p�����[�^�̂����A<BR>
     * �@@�@@�@@�ȉ��̍��ڂɂ́A�����\�b�h�̈����i�����ڋq���薾��Params�j<BR>
     * �̓����ڂ�ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@����ID<BR>
     * �@@�@@�@@�@@�@@�@@�⏕����ID<BR>
     * �@@�@@�@@�@@�@@�@@�s��ID<BR>
     * �@@�@@�@@�@@�@@�@@����ID<BR>
     * �@@�@@�@@�@@�@@�@@�ŋ敪<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̍��ڂɂ́A�����\�b�h�̈����i�����ڋq���薾��Params�j����A<BR>
     * �@@�@@�@@�ȉ��̒ʂ�ɐݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@���敪�F�@@�g�����U�N�V�����^�C�v���ݒ�(*1)�B<BR>
     * �@@�@@�@@�@@�@@�@@���P���F�@@���P��<BR>
     * �@@�@@�@@�@@�@@�@@�����F�@@���ID�ɊY��������I�u�W�F�N�g(*2).��������YYYYMMDD<BR>
     * �@@�@@�@@�@@�@@�@@���������F�@@�����ɓ���<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̍��ڂɂ́A�Y�����钍���P�ʃI�u�W�F�N�g(*3)�̓����ڂ�ݒ�F<BR>
     * �@@�@@�@@�@@�@@�@@�ٍϋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�ٍϊ����l<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̍��ڂɂ́Athis.get��������( )�̖߂�l��ݒ�F<BR>
     * �@@�@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@------------------------------------------------------<BR>
     * �@@�@@�@@��get��������(Date, double)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����F�@@���ID�ɊY��������I�u�W�F�N�g(*2).��������YYYYMMDD<BR>
     * �@@�@@�@@�ٍϊ����l�F�@@�Y�����钍���P�ʃI�u�W�F�N�g(*3).�ٍϊ����l<BR>
     * �@@�@@�@@------------------------------------------------------<BR>
     * 
     * �@@�@@�@@(*1)ContractTypeEnum.getContractType(<BR>
     * �@@�@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�g�����U�N�V�����^�C�v)<BR>
     * <BR>
     * �@@�@@�@@(*2)�g�����������}�l�[�W��.getOrderExecution(<BR>
     * �@@�@@�@@�����\�b�h�̈����i�����ڋq���薾��Params.���ID) �ɂ��<BR>
     * �@@�@@�@@�擾�������I�u�W�F�N�g���g�p����B<BR>
     * <BR>
     * �@@�@@�@@(*3)�g�����������}�l�[�W��.getOrderUnit(<BR>
     * �@@�@@�@@�����\�b�h�̈����i�����ڋq���薾��Params.�����P��ID) �ɂ��<BR>
     * �@@�@@�@@�擾���������P�ʃI�u�W�F�N�g���g�p����B<BR>
     * <BR>
     * �Q�j�@@�Ϗ����\�b�h�̖߂�l�I�u�W�F�N�g�����̂܂ܕԋp����B
     * @@param l_eqtypeFinTransactionParams (�����ڋq���薾��Params)<BR>
     * �@@�@@�@@�����ڋq���薾��Params�I�u�W�F�N�g�B
     * @@return EqtypeContractParams
     * @@throws DataException
     * @@roseuid 40A96955013B
     */
    protected EqtypeContractParams getContract(EqtypeFinTransactionParams l_eqtypeFinTransactionParams) throws DataException
    {
        final String STR_METHOD_NAME = "getContract";
        log.entering(STR_METHOD_NAME);
        
        if (l_eqtypeFinTransactionParams == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        
        try
        {
            long l_accountId = l_eqtypeFinTransactionParams.getAccountId();
            long l_subAccountId = l_eqtypeFinTransactionParams.getSubAccountId();
            long l_marketId = l_eqtypeFinTransactionParams.getMarketId();
            long l_productId = l_eqtypeFinTransactionParams.getProductId();
            TaxTypeEnum l_taxType = l_eqtypeFinTransactionParams.getTaxType();
            ContractTypeEnum l_contractType = ContractTypeEnum.getContractType(l_eqtypeFinTransactionParams.getFinTransactionType());
            double l_price = l_eqtypeFinTransactionParams.getPrice();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            
            //(*2)�g�����������}�l�[�W��.getOrderExecution(
            //�����\�b�h�̈����i�����ڋq���薾��Params.���ID) �ɂ��
            //�擾�������I�u�W�F�N�g���g�p����B
            OrderExecution l_orderExecution = l_orderManager.getOrderExecution(l_eqtypeFinTransactionParams.getOrderExecutionId());
            Date l_datexecutiontimestamp = l_orderExecution.getExecutionTimestamp();
            
            //(*3)�g�����������}�l�[�W��.getOrderUnit(
            //�����\�b�h�̈����i�����ڋq���薾��Params.�����P��ID) �ɂ��
            //�擾���������P�ʃI�u�W�F�N�g���g�p����B
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_eqtypeFinTransactionParams.getOrderUnitId());
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            String l_strRepaymentType = l_orderUnitRow.getRepaymentType();
            int l_intReparmentNum = l_orderUnitRow.getRepaymentNum();
            Date l_datCloseDate = this.getContractCloseDate(l_datexecutiontimestamp, l_intReparmentNum);
            WEB3EquityPersistentDataManager l_dataManager = (WEB3EquityPersistentDataManager) this.getPersistenceManager();
            
            //�g���f�[�^�}�l�[�W��.get����(����ID, �⏕����ID, �s��ID, ����ID, ���敪, ���P��,
            //����, ����, �ŋ敪, �ٍϋ敪, �ٍϊ����l, ��������)�ɈϏ�����B
			log.exiting(STR_METHOD_NAME);
            return l_dataManager.getContract(
                l_accountId,
                l_subAccountId,
                l_marketId,
                l_productId,
                l_contractType,
                l_price,
                l_datexecutiontimestamp,
                l_datCloseDate,
                l_taxType,
                l_strRepaymentType,
                l_intReparmentNum,
                l_datexecutiontimestamp);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (WEB3BaseException l_wbe)
        {
            String l_strMessage = "�����ڋq���薾��Params��������܂���";
            log.error(l_strMessage, l_wbe);
            throw new RuntimeSystemException(l_strMessage, l_wbe);
        }
    }

    /**
     * �iset�����To�����ڋq���薾�ׁj<BR>
     * <BR>
     * ���f�[�^�y�ђ����f�[�^���A�����ڋq���薾��Params�ɒl���Z�b�g����B<BR>
     * �ivoid setExecutionInfoToMarketOrderedTrans(<BR>
     * �@@�@@EqtypeFinTransactionParams,<BR>
     * �@@�@@EqTypeOrderExecution,EqtypeOrderUnitRow)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����̊����ڋq���薾��Params�̊e�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * ����ID�F�@@�����̖��.����ID<BR>
     * �⏕����ID�F�@@�����̖��.�⏕����ID<BR>
     * ����ID�F�@@�����̖��.����ID<BR>
     * �����^�C�v�F�@@�����̖��.�����^�C�v<BR>
     * �s��ID�F�@@�����̖��.�s��ID<BR>
     * ����ID�F�@@�����̒����P��Row.����ID<BR>
     * �����P��ID�F�@@�����̖��.�����P��ID<BR>
     * ���ID�F�@@�����̖��.���ID<BR>
     * �g�����U�N�V�����^�C�v�F�@@�����̒����P��Row.getOrderType().getFinTransactionType()<BR>
     * �g�����U�N�V�����J�e�S���F�@@<BR>
     * �@@�@@�����̒����P��Row.getOrderType().getFinTransactionType().getCateg()<BR>
     * �ŋ敪�F�@@�����̒����P��Row.�ŋ敪<BR>
     * ��n���F�@@GtlUtils.truncateDate(�����̖��.��n��)<BR>
     * <BR>
     * �Q�j�@@�����̊����ڋq���薾��Params�̖��P���A��萔�ʁA�����̎�n���z ���Z�b�g����B<BR>
     * <BR>
     * ���P���F�@@�����̖��.���P��<BR>
     * ��萔�ʁF�@@�����̖��.��萔��<BR>
     * �����̎�n���z�F�@@�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S��<BR>
     * �@@�@@�@@����������iEQTYPE_ASSET�j �܂��͐V�K���iEQTYPE_OPEN_MARGIN�j�̏ꍇ�́A0�B<BR>
     * �@@�@@�@@�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S��<BR>
     * �@@�@@�@@���V�K���iEQTYPE_OPEN_MARGIN�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�����̖��.���P�� �~ �����̖��.��萔�ʁB<BR>
     * <BR>
     * �R�j�@@�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S������������iEQTYPE_ASSET�j<BR>
     * �@@�@@�@@�̏ꍇ�́A<BR>
     * �@@�@@�@@�������I������B<BR>
     * �@@�@@�@@�������̏ꍇ�A�ϑ��萔���̌v�Z�� set�����ڋq���薾��Params( )�̒��ŁA<BR>
     * �@@�@@�@@��������v�Z�� �������c���X�V����( )�̒��ŁA<BR>
     * �@@�@@�@@�����ꂼ����s����邽�߁A�����\�b�h�ł͏����s�v�B<BR>
     * <BR>
     * �@@�@@�@@�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S�����������n<BR>
     * �@@�@@�@@�iEQTYPE_SWAP_MARGIN�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�����̊����ڋq���薾��Params.�ϑ��萔���A�ϑ��萔������ł�0���Z�b�g���A<BR>
     * �@@�@@�@@�������I������B<BR>
     * <BR>
     * �@@�@@�@@�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S����<BR>
     * �@@�@@�@@�i��������iEQTYPE_ASSET�j�A�������n�iEQTYPE_SWAP_MARGIN�j�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�ȍ~�̏����𑱍s����B<BR>
     * <BR>
     * �S�j�@@�����ڋq���薾�ׂւ̈ϑ��萔���^�ϑ��萔������� �̃Z�b�g���s���B<BR>
     * �@@�@@�@@�܂��A�V�K�������ɑ΂�����̏ꍇ�́A����蕪�̊����ڋq���薾�ׂ́A<BR>
     * �@@�@@�@@��n����A���n�v���z�A���n�v�Ŋz�̍Čv�Z���s���B<BR>
     * �@@�@@�@@���Čv�Z�ɂ��A�ϑ��萔���^�ϑ��萔������� �̋��z�̕ϓ��𔽉f����B<BR>
     * <BR>
     * �S�|�P�j�@@����蕪�̊����ڋq���薾��Params��List���擾����B<BR>
     * <BR>
     * �@@�@@�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListBy�����P��(�����̖��.�����P��ID)<BR>
     * �@@�@@�R�[���ɂ��擾����B<BR>
     * <BR>
     * �ȉ��A�i�ԍϒ����ɑ΂�����̏ꍇ or ������̏ꍇ�i�S�|�Q�j�j��<BR>
     * �i����肪����ꍇ�i�S�|�R�j�ȍ~�j�ƂŁA�������򂷂�B<BR>
     * <BR>
     * �S�|�Q�j�@@�ȉ��̏����̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�����肵�������z�ɑ΂���ϑ��萔���A�ϑ��萔������ł��v�Z���A<BR>
     * �@@�@@�@@�@@�@@�����̊����ڋq���薾��Params�ɃZ�b�g��Areturn����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@--------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@��������<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�E�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S�����ԍώ��<BR>
     * �@@�@@�@@�@@�@@�@@�iEQTYPE_CLOSE_MARGIN�j�ł���B<BR>
     * �@@�@@�@@�@@�@@�E�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S�����V�K�����<BR>
     * �@@�@@�@@�@@�@@�@@�iEQTYPE_OPEN_MARGIN�j�A<BR>
     * �@@�@@�@@�@@�@@���@@�S�|�P�j�Ŏ擾����List.isEmpty( )==true�i��������j�ł���B<BR>
     * �@@�@@�@@�@@�@@--------------------------------------------------------<BR>
     * <BR>
     * �S�|�Q�|�P�j�@@�����v�Z�T�[�r�X.create�萔��( )�ɂ��A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���̕t���������P�ʃI�u�W�F�N�g�����ƂɎ萔���I�u�W�F�N�g���쐬����B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��create�萔��( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@����ID�F�@@�����̒����P��Row.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �S�|�Q�|�Q�j�@@�萔���I�u�W�F�N�g�́A�v���p�e�B�u���o��v�Z�p����v���Z�b�g����B<BR>
     * <BR>
     * �@@�@@----------------------------------------------------------<BR>
     * �@@�@@�S�|�P�j�Ŏ擾����List.isEmpty( )==true�i��������j�̏ꍇ�F<BR>
     * �@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.���P��<BR>
     * �@@�@@�@@�@@�@@�~ �����̊����ڋq���薾��Params.��萔��<BR>
     * <BR>
     * �@@�@@��L�ȊO�̏ꍇ�F<BR>
     * �@@�@@�@@�@@�@@ �i�����̊����ڋq���薾��Params.���P��<BR>
     * �@@�@@�@@�@@�@@�@@�~ �����̊����ڋq���薾��Params.��萔�ʁj<BR>
     * �@@�@@�@@�@@�@@ �{ �i�S�|�P�j�Ŏ擾��������蕪�̊����ڋq���薾��Params��List�̑S�v�f��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�i���P���~��萔�ʁj��SUM�l�j<BR>
     * �@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �S�|�Q�|�R�j�@@�����v�Z�T�[�r�X.calc�ϑ��萔��( )�ɂ��ϑ��萔�����擾���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�ϑ��萔���ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc�ϑ��萔��( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g<BR>
     * �@@�@@�@@�⏕�����F�@@�����̖��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾�����ϑ��萔�����A�ȉ��̒ʂ��<BR>
     * �@@�@@�@@�����̊����ڋq���薾��Params.�ϑ��萔���ɃZ�b�g����B<BR>
     * �@@�@@----------------------------------------------------------<BR>
     * �@@�@@�S�|�P�j�Ŏ擾����List.isEmpty( )==true�i��������j�̏ꍇ�F<BR>
     * �@@�@@�@@�@@�@@calc�ϑ��萔��( )�R�[����̎萔��.�萔�����z �����̂܂܃Z�b�g�B<BR>
     * <BR>
     * �@@�@@��L�ȊO�̏ꍇ�F<BR>
     * �@@�@@�@@�@@�@@ calc�ϑ��萔��( )�R�[����̎萔��.�萔�����z<BR>
     * �@@�@@�@@�@@�@@ �| �i�S�|�P�j�Ŏ擾��������蕪�̊����ڋq���薾��Params��List�̑S�v�f��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ϑ��萔����SUM�l�j<BR>
     * �@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �S�|�Q�|�S�j�@@�����v�Z�T�[�r�X.calc�����( )�ɂ��ϑ��萔������ł��擾���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�ϑ��萔������łɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc�����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@���z�F�@@calc�ϑ��萔��( )�R�[����̎萔��.�萔�����z<BR>
     * �@@�@@�@@����F�@@�����̒����P��Row.������<BR>
     * �@@�@@�@@�⏕�����F�@@�����̖��.����ID�A�⏕����ID�ɊY������⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�擾�����ϑ��萔������ł��A�ȉ��̒ʂ��<BR>
     * �@@�@@�@@�����̊����ڋq���薾��Params.�ϑ��萔������łɃZ�b�g����B<BR>
     * �@@�@@----------------------------------------------------------<BR>
     * �@@�@@�S�|�P�j�Ŏ擾����List.isEmpty( )==true�i��������j�̏ꍇ�F<BR>
     * �@@�@@�@@�@@�@@calc�����( )�̖߂�l�����̂܂܃Z�b�g�B<BR>
     * <BR>
     * �@@�@@��L�ȊO�̏ꍇ�F<BR>
     * �@@�@@�@@�@@�@@ calc�����( )�̖߂�l<BR>
     * �@@�@@�@@�@@�@@ �| �i�S�|�P�j�Ŏ擾��������蕪�̊����ڋq���薾��Params��List�̑S�v�f��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ϑ��萔������ł�SUM�l�j<BR>
     * �@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �S�|�Q�|�T�j�@@return����B<BR>
     * <BR>
     * �S�|�R�j�@@�S�|�Q�j�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�i�V�K�������ɑ΂�����A<BR>
     * �@@�@@�@@�@@�@@�@@���@@�S�|�P�j�Ŏ擾����List.isEmpty( )==false�i��������j�̏ꍇ�j�A<BR>
     * �@@�@@�@@�@@�@@�S�|�P�j�Ŏ擾����List�ɁA�����蕪�̊����ڋq���薾��Params<BR>
     * �@@�@@�@@�@@�@@�i�������̊����ڋq���薾��Params�j��add����B<BR>
     * <BR>
     * �S�|�S�j�@@�o���ʒm�̗��������̊����ڋq���薾��Params�̊e���R�[�h�ɑ΂���A<BR>
     * �@@�@@�@@�@@�@@�ϑ��萔���A�ϑ��萔������ł̈��l���擾����B<BR>
     * <BR>
     * �@@�@@�����v�Z�T�[�r�X.calc�萔���i���j( )���R�[������B<BR>
     *  �@@�@@�����ɂ́A�S�|�R�j�ō쐬����List��toArray( )�Ŕz��ɕϊ����Đݒ肷��B<BR>
     * <BR>
     * �S�|�T�j�@@�S�|�S�j�̖߂�lConsolidatedCommissionInfo���A<BR>
     * �@@�@@�@@�@@�@@�S�|�R�j�ō쐬����List�̊e�v�f�̈ϑ��萔���A�ϑ��萔������łɃZ�b�g����B<BR>
     * �@@�@@�@@�@@�@@����蕪�̊����ڋq���薾��Params�ɂ��ẮA<BR>
     * �@@�@@�@@�@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update���s���B<BR>
     * <BR>
     * �@@�@@�@@�ȉ��A�S�|�R�j�ō쐬����List�̗v�f�iindex�j��Loop����B<BR>
     * <BR>
     * ������START LOOP������<BR>
     * <BR>
     * �S�|�T�|�P�j�@@�ϑ��萔���A�ϑ��萔������ŁA�X�V���t���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�ϑ��萔���F�@@ConsolidatedCommissionInfo.getCommission(index)<BR>
     * �@@�@@�@@�@@�ϑ��萔������ŁF�@@ConsolidatedCommissionInfo.getSalesTax(index)<BR>
     * �@@�@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �S�|�T�|�Q�j�@@�@@�����ڋq���薾��Params������蕪�̃f�[�^�ł���ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�g���f�[�^�}�l�[�W��.updateFinTransaction(�����ڋq���薾��Params(index), <BR>
     * �@@�@@�@@�@@�X�V�Ώۃv���p�e�BMap)�ɂ��Aupdate���s���B<BR>
     * �@@�@@�@@�@@���X�V�Ώۃv���p�e�BMap�ɂ́A�S�|�T�|�P�j�ŃZ�b�g�����v���p�e�B�y�ђl���Z�b�g����B<BR>
     * <BR>
     * �S�|�T�|�R�j�@@�����ڋq���薾��Params������蕪�̃f�[�^�ł���ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update��GTL�w�ɒʒm����B<BR>
     * <BR>
     * �@@�@@�@@�@@this.�ڋq����X�V(�����ڋq���薾��Params(index))�ɂ��<BR>
     * �@@�@@�@@�@@GTL�w�ւ̒ʒm���s���B<BR>
     * <BR>
     * ������END  LOOP������<BR>
     * <BR>
     * �S�|�U�j�@@return����B<BR>
     * <BR>
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@param l_orderExecution ���I�u�W�F�N�g�B
     * @@param l_orderUnitRow �����P��Row�I�u�W�F�N�g�B
     * @@throws DataQueryException
     * @@throws DataNetworkException 
     * @@roseuid 40CEE03C028A
     */
    protected void setExecutionInfoToMarketOrderedTrans(
        EqtypeFinTransactionParams l_finTransactionParams,
        EqTypeOrderExecution l_orderExecution,
        EqtypeOrderUnitRow l_orderUnitRow)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "setExecutionInfoToMarketOrderedTrans(EqtypeFinTransactionParams, EqTypeOrderExecution, EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        if (l_orderExecution == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            //�P�j�@@�����̊����ڋq���薾��Params�̊e�v���p�e�B���Z�b�g����B
            //����ID�F�@@�����̖��.����ID
            l_finTransactionParams.setAccountId(l_orderExecution.getAccountId());

            //�⏕����ID�F�@@�����̖��.�⏕����ID
            l_finTransactionParams.setSubAccountId(l_orderExecution.getSubAccountId());

            //����ID�F�@@�����̖��.����ID
            l_finTransactionParams.setProductId(l_orderExecution.getProduct().getProductId());

            //�����^�C�v�F�@@�����̖��.�����^�C�v
            l_finTransactionParams.setProductType(l_orderExecution.getProduct().getProductType());

            //�s��ID�F�@@�����̖��.�s��ID
            l_finTransactionParams.setMarketId(l_orderExecution.getMarketId());

            //����ID�F�@@�����̒����P��Row.����ID
            l_finTransactionParams.setOrderId(l_orderUnitRow.getOrderId());

            //�����P��ID�F�@@�����̖��.�����P��ID
            l_finTransactionParams.setOrderUnitId(l_orderExecution.getOrderUnitId());

            //���ID�F�@@�����̖��.���ID
            l_finTransactionParams.setOrderExecutionId(l_orderExecution.getOrderExecutionId());

            //�g�����U�N�V�����^�C�v�F�@@�����̒����P��Row.getOrderType().getFinTransactionType()
            l_finTransactionParams.setFinTransactionType(l_orderUnitRow.getOrderType().toFinTransactionType());

            //�g�����U�N�V�����J�e�S���F�����̒����P��Row.getOrderType().getFinTransactionType().getCateg()
            l_finTransactionParams.setFinTransactionCateg(l_orderUnitRow.getOrderType().toFinTransactionType().toFinTransactionCateg());
            
            //�ŋ敪�F�@@�����̒����P��Row.�ŋ敪
            l_finTransactionParams.setTaxType(l_orderUnitRow.getTaxType());

            //��n���F�@@GtlUtils.truncateDate(�����̖��.��n��)
            l_finTransactionParams.setDeliveryDate(GtlUtils.truncateDate(l_orderExecution.getDeliveryDate()));

            //�Q�j�@@�����̊����ڋq���薾��Params�̖��P���A��萔�ʁA�����̎�n���z ���Z�b�g����B
            //���P���F�@@�����̖��.���P��
            double l_dblexecutionPrice = l_orderExecution.getExecutionPrice();
            l_finTransactionParams.setPrice(l_dblexecutionPrice);

            //��萔�ʁF�@@�����̖��.��萔��
            double l_dblexecutionQuantity = l_orderExecution.getExecutionQuantity();
            l_finTransactionParams.setQuantity(l_dblexecutionQuantity);

            //�����̎�n���z�F�@@�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S��
            //���V�K���iEQTYPE_OPEN_MARGIN�j�̏ꍇ�́A0�B
            //�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S��
            //���V�K���iEQTYPE_OPEN_MARGIN�j�̏ꍇ�́A
            //�����̖��.���P�� �~ �����̖��.��萔�ʁB
            double l_dblDeliveryDate = 0D;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            WEB3EquityPersistentDataManager l_dataManager = (WEB3EquityPersistentDataManager) this.getPersistenceManager();
            if (FinTransactionCateg.EQTYPE_ASSET.equals(l_finTransactionParams.getFinTransactionCateg()) ||
                FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                l_dblDeliveryDate = 0D;
            }
            else
            {
                l_dblDeliveryDate = l_dblexecutionPrice * l_dblexecutionQuantity;
            }
            l_finTransactionParams.setImportedPaidValue(l_dblDeliveryDate);

            //�R�j�@@�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S������������iEQTYPE_ASSET�j
            //�̏ꍇ�͏������I������B
            //�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S�����iEQTYPE_SWAP_MARGIN�j�̏ꍇ�́A
            //�����̊����ڋq���薾��Params.�ϑ��萔���A�ϑ��萔������ł�0���Z�b�g���A
            //�������I������B
            if (FinTransactionCateg.EQTYPE_ASSET.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                return;
            }
            if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                l_finTransactionParams.setCommissionFee(0);
                l_finTransactionParams.setCommissionFeeTax(0);
                return;
                
            }
            //�@@�@@�@@�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S�����������n
            //�iEQTYPE_SWAP_MARGIN�j�̏ꍇ�́A
            //�ȍ~�̏����𑱍s����B
            //�����ڋq���薾�ׂւ̈ϑ��萔���^�ϑ��萔������� �̃Z�b�g���s���B
            //�܂��A�V�K�������ɑ΂�����̏ꍇ�́A����蕪�̊����ڋq���薾�ׂ́A
            //��n����A���n�v���z�A���n�v�Ŋz�̍Čv�Z���s���B
            // ���Čv�Z�ɂ��A�ϑ��萔���^�ϑ��萔������� �̋��z�̕ϓ��𔽉f����B

            //�S�|�P�j�@@����蕪�̊����ڋq���薾��Params��List���擾����B
            List l_lstFinTransaction = l_dataManager.getFinTransactionListByOrderUnit(l_orderExecution.getOrderUnitId());

            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnitRow.getOrderUnitId());
            WEB3GentradeCommission l_commission =
                l_bizLogicProvider.createCommission(l_orderUnit, l_orderExecution);
            double l_dblCalcAmount = 0D;

            //�E�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S�����ԍώ��
            //�iEQTYPE_CLOSE_MARGIN�j�ł���B
            //�E�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S�����V�K�����
            //�iEQTYPE_OPEN_MARGIN�j�A
            //���@@�S�|�P�j�Ŏ擾����List.isEmpty( )==true�i��������j�ł���B
            if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg())
                || (FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()) && l_lstFinTransaction.isEmpty()))
            {
                WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(l_orderExecution.getAccountId(), l_orderExecution.getSubAccountId());
                
                //�擾����List.isEmpty( )==true�i��������j�̏ꍇ�F<BR>
                //�����̊����ڋq���薾��Params.���P��<BR>
                // �@@�@@�@@�@@�@@�~ �����̊����ڋq���薾��Params.��萔��<BR>
                //  ��L�ȊO�̏ꍇ�F<BR>
                //�@@�@@�@@ �i�����̊����ڋq���薾��Params.���P��<BR>
                //     �@@�@@�@@�~ �����̊����ڋq���薾��Params.��萔�ʁj<BR>
                //    �@@�@@�@@ �{ �i�S�|�P�j�Ŏ擾��������蕪�̊����ڋq���薾��Params��List�̑S�v�f��
                //           (���P���~��萔�ʁj��SUM�l�j
                l_dblCalcAmount = l_finTransactionParams.getPrice() * l_finTransactionParams.getQuantity();
                
                if (l_lstFinTransaction != null && !l_lstFinTransaction.isEmpty())
                {
                    int l_intFinTransactionLength = l_lstFinTransaction.size();
                    for (int i = 0; i < l_intFinTransactionLength; i++)
                    {
                        EqtypeFinTransactionParams l_eqtypeFinTransactionParams = (EqtypeFinTransactionParams)l_lstFinTransaction.get(i);
                        l_dblCalcAmount += l_eqtypeFinTransactionParams.getPrice() * l_eqtypeFinTransactionParams.getQuantity();
                    }
                }
                //�萔���I�u�W�F�N�g�́A�v���p�e�B�u���o��v�Z�p����v���Z�b�g����B
                l_commission.setExpensesCalcAmount(l_dblCalcAmount);
                
                //�����v�Z�T�[�r�X.calc�ϑ��萔��( )�ɂ��ϑ��萔�����擾                
                l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
                
                //�����̊����ڋq���薾��Params.�ϑ��萔���ɃZ�b�g����B
                //��������iisEmpty( )==true�j�̏ꍇ
                //    calc�ϑ��萔��( )�R�[����̎萔��.�萔�����z �����̂܂܃Z�b�g�B
                //����L�ȊO�̏ꍇ�i�ԍϒ����ɑ΂�����ŁA����肠��j
                //�icalc�ϑ��萔��( )�R�[����̎萔��.�萔�����z
                //  �| get�����ڋq���薾��ListBy�����P��( )�Ŏ擾����
                //  �S�Ă̊����ڋq���薾��.�ϑ��萔����SUM�l�j���Z�b�g�B
                double l_dblCommission = 0D;
                l_dblCommission = l_commission.getCommission();
                if (l_lstFinTransaction != null && !l_lstFinTransaction.isEmpty())
                {
                    int l_intFinTransactionLength = l_lstFinTransaction.size();
                    for (int i = 0; i < l_intFinTransactionLength; i++)
                    {                        
                        EqtypeFinTransactionParams l_eqtypeFinTransactionParams = (EqtypeFinTransactionParams)l_lstFinTransaction.get(i);
                        l_dblCommission -= l_eqtypeFinTransactionParams.getCommissionFee();
                    }
                }
                
                double l_dblSalesTax = l_bizLogicProvider.calcSalesTax(
                    l_commission.getCommission(), 
                    new Timestamp(WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd").getTime()), 
                    l_subAccount);
                
                if (l_lstFinTransaction != null)
                {
                    int l_intFinTransactionLength = l_lstFinTransaction.size();
                    for (int i = 0; i < l_intFinTransactionLength; i++)
                    {
                        EqtypeFinTransactionParams l_eqtypeFinTransactionParams = (EqtypeFinTransactionParams)l_lstFinTransaction.get(i);
                        l_dblSalesTax -= l_eqtypeFinTransactionParams.getCommissionFeeTax();
                    }
                }
                
                l_finTransactionParams.setNetAmount(l_dblCalcAmount);
                l_finTransactionParams.setCommissionFee(l_dblCommission);
                l_finTransactionParams.setCommissionFeeTax(l_dblSalesTax);
                
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //�i�V�K�������ɑ΂�����A
            //���@@�S�|�P�j�Ŏ擾����List.isEmpty( )==false�i��������j�̏ꍇ�j�A
            //�S�|�P�j�Ŏ擾����List�ɁA�����蕪�̊����ڋq���薾��Params
            //�i�������̊����ڋq���薾��Params�j��add����B
            else
            {
                if (l_lstFinTransaction.isEmpty() == false)
                {
                    l_lstFinTransaction.add(l_finTransactionParams);
                }
                int l_lstFintransactionLength = l_lstFinTransaction.size();
                if (l_lstFintransactionLength > 0)
                {
                    EqtypeFinTransactionRow[] l_finTransaction =
                        new EqtypeFinTransactionRow[l_lstFintransactionLength];
                    l_lstFinTransaction.toArray(l_finTransaction);
                    
                    //�����v�Z�T�[�r�X.calc�萔���i���j( )���R�[������
                    ConsolidatedCommissionInfo l_commissionInfo =
                        l_bizLogicProvider.calcCommission(l_finTransaction);
    
                    for (int i = 0; i < l_lstFintransactionLength; i++)
                    {
                        //�ϑ��萔���A�ϑ��萔������ŁA�X�V���t���Z�b�g����B                            
                        EqtypeFinTransactionParams l_params =
                            (EqtypeFinTransactionParams)l_lstFinTransaction.get(i);
                        l_params.setCommissionFee(l_commissionInfo.getCommission(i));
                        l_params.setCommissionFeeTax(l_commissionInfo.getSalesTax(i));
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    
                        //�g���f�[�^�}�l�[�W��.updateFinTransaction(�����ڋq���薾��Params(index), 
                        //�X�V�Ώۃv���p�e�BMap)�ɂ��update���s���B
                        HashMap l_map = new HashMap();
                        l_map.put("commission_fee", new Double(l_params.getCommissionFee()));
                        l_map.put("commission_fee_tax", new Double(l_params.getCommissionFeeTax()));
                        l_map.put("last_updated_timestamp", l_params.getLastUpdatedTimestamp());
                        l_dataManager.updateFinTransaction(l_params, l_map);
    
                        //this.�ڋq����X�V(�����ڋq���薾��Params(index))�ɂ��GTL�w�ւ̒ʒm���s���B
                        if (l_params != l_finTransactionParams)
                        {
                            this.notifyGtl(l_params);
                        }
                    }
                }
            }
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_wbe.getMessage(), l_wbe);
        }
        catch (NotFoundException l_nfex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfex);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfex.getMessage(), l_nfex);
        }
        catch (DataException l_de)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_de);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_de.getMessage(), l_de);
        }
    }

    /**
     * (takeIn���o��From����To�����ڋq���薾��)<BR>
     * ����Params�̏��o����A�����ڋq���薾��Params�Ɉ����Ĉړ�������B<BR>
     * �i�������A�Ǘ���A�Ǘ������ł݂̂́A���̓x��<BR>
     * �@@����Params�ɍڂ��Ă���S�z���A�����ڋq���薾��Params�Ɉړ�������B�j<BR>
     * �����ڋq���薾��Params�Ɉړ����������z�́A����Params���猸�Z����B<BR>
     * �ivoid takeInCostFromContractToTrans(double factor, <BR>
     * EqtypeContractParams cparams, <BR>
     * �@@EqtypeFinTransactionParams tparams)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * =========================================================<BR>
     * �P�j�@@����Params�̏��o��̈��l���v�Z���A<BR>
     * �@@�@@�@@�v�Z�������l������Params���犔���ڋq���薾��Params�ֈړ�������B<BR>
     * <BR>
     * ---------------------------------------------<BR>
     * ������Params�A�����ڋq���薾��Params��<BR>
     * �@@���v�Z�ɂ��X�V�Ώۃv���p�e�B�i���o��ځj�͈ȉ��̒ʂ�B<BR>
     * �@@������Params�Ɗ����ڋq���薾��Params�ƂŁA�v���p�e�B�̕��������قȂ�̂Œ��ӁB
     * <BR>
     * <BR>
     * ���萔��<BR>
     * ���萔�������<BR>
     * ���`������<BR>
     * ���`�����������<BR>
     * ������<BR>
     * �t����<BR>
     * �݊���(*1)<BR>
     * ���̑�<BR>
     * <BR>
     * (*1)�����̌���Params.���敪��"����"�iSHORT�j�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@���̌v�Z�����g�p���Čv�Z�������ʂ��Z�b�g����B<BR>
     * �@@�@@�@@�����̌���Params.���敪��"����"�iLONG�j�̏ꍇ�́A<BR>
     * �@@�@@�@@����Params.�݊����ɂ͌��݂̒l���A<BR>
     * �@@�@@�@@�����ڋq���薾��Params.�݊����ɂ�0���Œ�ŃZ�b�g����B<BR>
     * <BR>
     * ---------------------------------------------<BR>
     * �����̌v�Z���͈ȉ��̒ʂ�B<BR>
     * <BR>
     * �P�|�P�j�@@Math.floor(�����̈��䗦 �~ ����Params�̍X�V�Ώۃv���p�e�B) 
     * �ɂ��A<BR>
     * �@@�@@�@@����Params���犔���ڋq���薾��Params�Ɉړ���������z�����߂�B<BR>
     * �@@�@@�@@��Math.floor( )�F�����̒l�ȉ��ŁA�v�Z��̐����Ɠ������A<BR>
     * �@@�@@�@@���ő�� (������ɂ����Ƃ��߂�) double �l��Ԃ����\�b�h�B<BR>
     * <BR>
     * �P�|�Q�j�@@�����ڋq���薾��Params�̍X�V�Ώۃv���p�e�B�ɁA<BR>
     * �P�|�P�j�ŋ��߂����z�����Z����B<BR>
     * <BR>
     * �P�|�R�j�@@����Params�̍X�V�Ώۃv���p�e�B����A<BR>
     * �P�|�P�j�ŋ��߂����z�����Z����B<BR>
     * <BR>
     * ============================================================================<BR>
     * �Q�j�@@����Params�̏��o��̑S�z���A<BR>
     * �@@�@@�@@����Params���犔���ڋq���薾��Params�ֈړ�������B<BR>
     * <BR>
     * ---------------------------------------------<BR>
     * ������Params�A�����ڋq���薾��Params��<BR>
     * �@@�S�z�ړ��ɂ��X�V�Ώۃv���p�e�B�i���o��ځj�͈ȉ��̒ʂ�B<BR>
     * �@@������Params�Ɗ����ڋq���薾��Params�ƂŁA�v���p�e�B�̕��������قȂ�̂Œ��ӁB
     * <BR>
     * <BR>
     * �Ǘ���<BR>
     * �Ǘ�������<BR>
     * <BR>
     * ---------------------------------------------<BR>
     * ���S�z�ړ��̌v�Z���͈ȉ��̒ʂ�B<BR>
     * <BR>
     * �Q�|�P�j�@@�����ڋq���薾��Params�̍X�V�Ώۃv���p�e�B�ɁA����Params�̒l�����Z����
     * �B<BR>
     * <BR>
     * �Q�|�Q�j�@@����Params�̍X�V�Ώۃv���p�e�B�ɁA0���Z�b�g����B
     * @@param l_dblFactorRatio (���䗦)<BR>
     * �@@�@@�@@���䗦�i�t�@@�N�^�[�j�B
     * @@param l_contractParams ����Params�B
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@roseuid 40CEEE3400CE
     */
    protected void takeInCostFromContractToTrans(double l_dblFactorRatio, EqtypeContractParams l_contractParams, EqtypeFinTransactionParams l_finTransactionParams)
    {
        final String STR_METHOD_NAME = "takeInCostFromContractToTrans";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����Params�̏��o��̈��l���v�Z���A�v�Z�������l������Params���犔���ڋq���薾��Params�ֈړ�������B
        //���萔��
        if (l_contractParams == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        double l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getSetupFee());
        l_finTransactionParams.setImportedSetupFee(l_finTransactionParams.getImportedSetupFee() + l_dblval);
        l_contractParams.setSetupFee(l_contractParams.getSetupFee() - l_dblval);

        //���萔�������
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getSetupFeeTax());
        l_finTransactionParams.setImportedSetupFeeTax(l_finTransactionParams.getImportedSetupFeeTax() + l_dblval);
        l_contractParams.setSetupFeeTax(l_contractParams.getSetupFeeTax() - l_dblval);

        //���`������
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getNameTransferFee());
        l_finTransactionParams.setImportedNameTransferFee(l_finTransactionParams.getImportedNameTransferFee() + l_dblval);
        l_contractParams.setNameTransferFee(l_contractParams.getNameTransferFee() - l_dblval);

        //���`�����������
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getNameTransferFeeTax());
        l_finTransactionParams.setImportedNameTransferFeeTax(l_finTransactionParams.getImportedNameTransferFeeTax() + l_dblval);
        l_contractParams.setNameTransferFeeTax(l_contractParams.getNameTransferFeeTax() - l_dblval);

        //������
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getInterestFee());
        l_finTransactionParams.setImportedInterestFee(l_finTransactionParams.getImportedInterestFee() + l_dblval);
        l_contractParams.setInterestFee(l_contractParams.getInterestFee() - l_dblval);

        //�t����
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getPayInterestFee());
        l_finTransactionParams.setImportedPayInterestFee(l_finTransactionParams.getImportedPayInterestFee() + l_dblval);
        l_contractParams.setPayInterestFee(l_contractParams.getPayInterestFee() - l_dblval);

        //     * (*1)�����̌���Params.���敪��"����"�iSHORT�j�̏ꍇ�̂݁A<BR>
        //���̌v�Z�����g�p���Čv�Z�������ʂ��Z�b�g����B<BR>
        //�����̌���Params.���敪��"����"�iLONG�j�̏ꍇ�́A<BR>
        //����Params.�݊����ɂ͌��݂̒l���A<BR>
        //�����ڋq���薾��Params.�݊����ɂ�0���Œ�ŃZ�b�g����B
        if (ContractTypeEnum.LONG.equals(l_contractParams.getContractType()))
        {
            l_finTransactionParams.setImportedLoanEquityFee(0);
        }
        else
        {
            l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getLoanEquityFee());
            l_finTransactionParams.setImportedLoanEquityFee(l_finTransactionParams.getImportedLoanEquityFee() + l_dblval);
            l_contractParams.setLoanEquityFee(l_contractParams.getLoanEquityFee() - l_dblval);
        }
        //���̑�
        l_dblval = Math.floor(l_dblFactorRatio * l_contractParams.getOther());
        l_finTransactionParams.setImportedOther(l_finTransactionParams.getImportedOther() + l_dblval);
        l_contractParams.setOther(l_contractParams.getOther() - l_dblval);

        //�Q�j�@@����Params�̏��o��̑S�z���A����Params���犔���ڋq���薾��Params�ֈړ�������B
        // �Ǘ���
        l_finTransactionParams.setImportedManagementFee(l_finTransactionParams.getImportedManagementFee() + l_contractParams.getManagementFee());
        l_contractParams.setManagementFee(0);

        // �Ǘ�������
        l_finTransactionParams.setImportedManagementFeeTax(l_finTransactionParams.getImportedManagementFeeTax() + l_contractParams.getManagementFeeTax());
        l_contractParams.setManagementFeeTax(0);
    }

    /**
     * �iset�����f�t�H���g�l�j<BR>
     * <BR>
     * �����̌���Params�̃v���p�e�B�ɁA�f�t�H���g�l���Z�b�g����B<BR>
     * �ivoid setContractDefaultValues(EqtypeContractParams cparams)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����̌���Params�̃v���p�e�B�ɁA�ȉ��̒ʂ�ɒl���Z�b�g�F<BR>
     * ----------------------------------<BR>
     * ���������F�@@0<BR>
     * �������F�@@0<BR>
     * �����萔���F�@@0<BR>
     * ���萔���F�@@0<BR>
     * �����萔������ŁF�@@0<BR>
     * ���萔������ŁF�@@0<BR>
     * ���`�������F�@@0<BR>
     * ���`����������ŁF�@@0<BR>
     * �Ǘ���F�@@0<BR>
     * �Ǘ������ŁF�@@0<BR>
     * �������F�@@0<BR>
     * ����������ŁF�@@0<BR>
     * �t�����F�@@0<BR>
     * �t��������ŁF�@@0<BR>
     * �݊����F�@@0<BR>
     * ���̑��F�@@0<BR>
     * �����]�����v�F�@@0<BR>
     * �쐬���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * �X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * ----------------------------------
     * @@param l_contractParams �����I�u�W�F�N�g�B
     * @@roseuid 40CFCC09029F
     */
    protected void setContractDefaultValues(EqtypeContractParams l_contractParams)
    {
        final String STR_METHOD_NAME = "setContractDefaultValues(EqtypeContractParams)";
        log.entering(STR_METHOD_NAME);
        if (l_contractParams == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //��������
        l_contractParams.setOriginalQuantity(0);
        //������
        l_contractParams.setQuantity(0);
        //�����萔��
        l_contractParams.setOriginalSetupFee(0);
        //���萔��
        l_contractParams.setSetupFee(0);
        //�����萔�������
        l_contractParams.setOriginalSetupFeeTax(0);
        //���萔�������
        l_contractParams.setSetupFeeTax(0);
        //���`������
        l_contractParams.setNameTransferFee(0);
        //���`�����������
        l_contractParams.setNameTransferFeeTax(0);
        //�Ǘ���
        l_contractParams.setManagementFee(0);
        //�Ǘ�������
        l_contractParams.setManagementFeeTax(0);
        //������
        l_contractParams.setInterestFee(0);
        //�����������
        l_contractParams.setInterestFeeTax(0);
        //�t����
        l_contractParams.setPayInterestFee(0);
        //�t���������
        l_contractParams.setPayInterestFeeTax(0);
        //�݊���
        l_contractParams.setLoanEquityFee(0);
        //���̑�
        l_contractParams.setOther(0);
        //�����]�����v
        l_contractParams.setContractAssetProfitLoss(0);
        //�쐬���t
        l_contractParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t
        l_contractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iupdate�����V�K��From�����ڋq���薾�ׁj<BR>
     * <BR>
     * �����ڋq���薾�ׂ��A�V�K����������莞�̌���Params�̃v���p�e�B�X�V���s���B<BR>
     * �܂��A���萔�����̈��z�̕ϓ����A���ϒ�����莞�̊����ڋq���薾�ׂ֔��f����B<BR>
     * �@@�|�i�P�j������Ώۂ̌����i�������̌���Params�I�u�W�F�N�g�j�ւ̃v���p�e�B�ݒ�<BR>
     * �@@�|�i�Q�j����莞�ɍ쐬���ꂽ�y�����e�[�u���z�����f�[�^��Update<BR>
     * �@@�|�i�R�j����莞�ɍ쐬���ꂽ���ϒ�����莞�́y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z<BR>
     * �@@�@@�@@�@@�@@���σf�[�^�̌��萔���A���萔������ł�Update<BR>
     * ���i�Q�j�i�R�j�́A���萔���A���萔������łւ́A������ɂ��萔�����z�ϓ��̔��f�B<BR>
     * �ivoid updateContractOpenFromMarketOrderedTrans(EqtypeContractParams cparams, <BR>
     * �@@EqtypeFinTransactionParams tparams)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �V�[�P���X�}�u�i�c���jupdate�����V�K��From�����ڋq���薾�ׁv<BR>
     * <BR>
     * �P�j�@@������Ώۂ́A����Params�̃v���p�e�B�ݒ���s���B<BR>
     * <BR>
     * �P�|�P�j�@@�����̌���Params�̃v���p�e�B�ɁA�����̊����ڋq���薾��Params���A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̒ʂ�ɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@���������F�@@����Params.�������� + �����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�������F�@@����Params.������ + �����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�����萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*1)<BR>
     * �@@�@@�@@���萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*1)<BR>
     * �@@�@@�@@�����萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*2)<BR>
     * �@@�@@�@@���萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*2)<BR>
     * �@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �@@�@@�@@(*1)�����̌���Params.���� == null�̏ꍇ�i�������̐V�KInsert���j�́A<BR>
     * �@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�ϑ��萔�� ���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�����̌���Params.���� != null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListOf�V�K������By����(<BR>
     * �@@�@@�@@�@@�@@�����̌���Params.����ID)�Ŏ擾�����A<BR>
     * �@@�@@�@@�@@�@@�����ڋq���薾��Params��List�̑S�v�f�̈ϑ��萔�� ��SUM�l<BR>
     * �@@�@@�@@�@@�@@�{�����̊����ڋq���薾��Params.�ϑ��萔�� ���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@(*2)�����̌���Params.���� == null�̏ꍇ�i�������̐V�KInsert���j�́A<BR>
     * �@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�ϑ��萔������� ���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�����̌���Params.���� != null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListOf�V�K������By����(<BR>
     * �@@�@@�@@�@@�@@�����̌���Params.����ID)�Ŏ擾�����A<BR>
     * �@@�@@�@@�@@�@@�����ڋq���薾��Params��List�̑S�v�f�̈ϑ��萔������� ��SUM�l<BR>
     * �@@�@@�@@�@@�@@�{�����̊����ڋq���薾��Params.�ϑ��萔������� ���Z�b�g�B<BR>
     * <BR>
     * �P�|�Q�j�@@�����̌���Params.���� == null�̏ꍇ�i�������̐V�KInsert���j�̂݁A<BR>
     * �@@�@@�@@�@@�@@�����̌���Params�̃v���p�e�B�ɁA�ȉ��̒ʂ�ɒl���Z�b�g����B<BR>
     * <BR>
     * �P�|�Q�|�P�j�@@�����̊����ڋq���薾��Params�̓����ڂ��Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�����h�c<BR>
     * �@@�@@�@@�⏕�����h�c<BR>
     * �@@�@@�@@�s��h�c<BR>
     * �@@�@@�@@�����h�c<BR>
     * �@@�@@�@@�����^�C�v<BR>
     * �@@�@@�@@�ŋ敪<BR>
     * <BR>
     * �P�|�Q�|�Q�j�@@�����̊����ڋq���薾��Params.�����P��ID�̒����P�ʃI�u�W�F�N�g(*3)��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ڂ��Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�ٍϋ敪<BR>
     * �@@�@@�@@�ٍϊ����l<BR>
     * <BR>
     * �@@�@@�@@(*3)�g�����������}�l�[�W��.getOrderUnit(<BR>
     * �@@�@@�@@�@@�@@�����ڋq���薾��Params.�����P��ID) �ɂ��<BR>
     * �@@�@@�@@�@@�@@�擾���������P�ʃI�u�W�F�N�g���g�p����B<BR>
     * <BR>
     * �P�|�Q�|�R�j�@@�����̊����ڋq���薾��Params���A�ȉ��̒ʂ�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�����P���F�@@�����ڋq���薾��Params.���P��<BR>
     * �@@�@@�@@���P���F�@@�����ڋq���薾��Params.���P��<BR>
     * �@@�@@�@@���敪�F�@@ContractTypeEnum.getContractType(<BR>
     * �@@�@@�@@�@@�@@�@@�����ڋq���薾��Params.�g�����U�N�V�����^�C�v)<BR>
     * �@@�@@�@@�����F�@@�����ڋq���薾��Params.���ID�ɊY��������I�u�W�F�N�g.��������YYYYMMDD<BR>
     * �@@�@@�@@�����F�@@this.get��������(����, �ٍϊ����l)<BR>
     * �@@�@@�@@�@@�@@�@@�������̌����A�ٍϊ����l�́A�����\�b�h���Ŏ擾�����l���g�p����B<BR>
     * �@@�@@�@@�ۏ؋����F�@@���敪��"����"�iLONG�j�̏ꍇ�́A�������.���ۏ؋���(*4)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���敪��"����"�iSHORT�j�̏ꍇ�́A�������.���ۏ؋���(*4)<BR>
     * �@@�@@�@@�����ۏ؋����F�@@���敪��"����"�iLONG�j�̏ꍇ�́A�������.�������ۏ؋���(*4)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���敪��"����"�iSHORT�j�̏ꍇ�́A�������.�������ۏ؋���(*4)<BR>
     * �@@�@@�@@���������F�@@�����ɓ���<BR>
     * <BR>
     * �@@�@@�@@(*4)�g���v���_�N�g�}�l�[�W��.getTradedProduct(�����̊����ڋq���薾��Params.����ID,<BR>
     * �@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�s��ID)�Ŏ擾������������I�u�W�F�N�g��<BR>
     * �@@�@@�@@�@@�@@�g�p����B<BR>
     * <BR>
     * �Q�j�@@����莞�ɍ쐬���ꂽ�����f�[�^���y�����e�[�u���z���擾���A<BR>
     * �@@�@@�@@�����萔���A���萔���A�����萔������ŁA���萔������� ��Update���s���B<BR>
     * <BR>
     * �Q�|�P�j�@@����莞�ɍ쐬���ꂽ����Params��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�g���f�[�^�}�l�[�W��.get����ListBy�����P��(<BR>
     * �@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�����P��ID)�ɂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@����������Update���A<BR>
     * �@@�@@�@@�@@�@@���@@�擾��������List�Ɉ����̌���Params�ɊY�����錚�������݂��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�߂�l��List�Ɉ����̌���Params��ǉ�����B<BR>
     * <BR>
     * �Q�|�Q�j�@@�ȉ��A�Q�|�P�j�Ŏ擾��������Params��List�̗v�f�iindex�j��Loop����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�Ȃ��A����Params(index).����ID�������̌���Params.����ID �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���̌���Params(index)�̑ւ��ɁA�����̌���Params���g�p����B<BR>
     * �@@�@@�@@�@@�@@�i�����̌���Params�͍�����Ώۂ̌����ł��邽�߁A<BR>
     * �@@�@@�@@�@@�@@�@@������ō쐬����銔���ڋq���薾�ׂ̒l���ڂ��Ă���A<BR>
     * �@@�@@�@@�@@�@@�@@�P�j�ō쐬��������Params���g�p����B�j<BR>
     * <BR>
     * ������������START LOOP�i�P�j������������<BR>
     * <BR>
     * �Q�|�Q�|�P�j�@@����Params(index).����ID�������̌���Params.����ID �̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����Params(index)�ɑ΂��A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����萔���A���萔���A�����萔������ŁA���萔������� �̐ݒ���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���V�K��������莞�̈ϑ��萔�����ڂ���B<BR>
     * <BR>
     * �@@�@@�@@�����萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*5)<BR>
     * �@@�@@�@@���萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*5)<BR>
     * �@@�@@�@@�����萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*6)<BR>
     * �@@�@@�@@���萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*6)<BR>
     * �@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �@@�@@�@@(*5)�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListOf�V�K������By����(<BR>
     * �@@�@@�@@�@@�@@����Params(index).����ID)�Ŏ擾�����A<BR>
     * �@@�@@�@@�@@�@@�����ڋq���薾��Params��List�̑S�v�f�̈ϑ��萔�� ��SUM�l�B<BR>
     * �@@�@@�@@�@@�@@���Y������f�[�^�����݂��Ȃ��ꍇ�́A0�Ƃ���B<BR>
     * <BR>
     * �@@�@@�@@(*6)�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListOf�V�K������By����(<BR>
     * �@@�@@�@@�@@�@@����Params(index).����ID)�Ŏ擾�����A<BR>
     * �@@�@@�@@�@@�@@�����ڋq���薾��Params��List�̑S�v�f�̈ϑ��萔������� ��SUM�l<BR>
     * �@@�@@�@@�@@�@@���Y������f�[�^�����݂��Ȃ��ꍇ�́A0�Ƃ���B<BR>
     * <BR>
     * <BR>
     * �Q�|�Q�|�Q�j�@@����Params(index)�ɑ΂���A���ϒ�����莞�̊����ڋq���薾��Params��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�S�Ď擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListOf���ϒ���By����(<BR>
     * �@@�@@�@@�@@�@@����Params(index).����ID)�ɂ��擾����B<BR>
     * <BR>
     * �Q�|�Q�|�R�j�@@���ύϐ��ʁi�x�j���O�ŏ���������B<BR>
     * <BR>
     * �Q�|�Q�|�S�j�@@�ȉ��A�Q�|�Q�|�Q�j�Ŏ擾���������ڋq���薾��Params��List�̗v�f�iindex2�j��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@Loop����B<BR>
     * <BR>
     * ������START LOOP�i�Q�j������<BR>
     * <BR>
     * �Q�|�Q�|�S�|�P�j�@@�����ڋq���薾��Params(index2)�ɑ΂��A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����.���萔���A���萔������ł��Ĉ�����B<BR>
     * <BR>
     * �@@�@@�@@���萔���F�@@����Params(index).���萔�� �~<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����ڋq���薾��Params(index2).��萔�� ��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i����Params(index).�������� �| �x�j�j(*7)<BR>
     * �@@�@@�@@���萔������ŁF�@@����Params(index).���萔������� �~<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����ڋq���薾��Params(index2).��萔�� ��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i����Params(index).�������� �| �x�j�j(*7)<BR>
     * �@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �@@�@@�@@(*7)�ŏI�v�Z���ʂ��A�~�����؎̂Ă���i�v�Z�ߒ��ł́A�ۂ߂͍s��Ȃ��j<BR>
     * <BR>
     * �Q�|�Q�|�S�|�Q�j�@@��n����A���n�v���z�A���n�v�Ŋz�ɁA���萔�����̕ϓ��𔽉f����B<BR>
     * <BR>
     * �@@�@@�@@�@@this.set�M�p������莞���z(�����ڋq���薾��Params(index2))�R�[���ɂ�蔽�f����B<BR>
     * <BR>
     * �Q�|�Q�|�S�|�R�j�@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��Update���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�g���f�[�^�}�l�[�W��.updateFinTransaction(�����ڋq���薾��Params(index2,<BR>
     * �@@�@@�@@�@@�X�V�Ώۃv���p�e�BMap)�ɂ��Aupdate���s���B<BR>
     * �@@�@@�@@�@@���X�V�Ώۃv���p�e�BMap�ɂ́A�Q�|�Q�|�S�|�P�j�ŃZ�b�g�����v���p�e�B�y�ђl���Z�b�g����B<BR>
     * <BR>
     * �Q�|�Q�|�S�|�S�j�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update��GTL�w�ɒʒm����B<BR>
     * <BR>
     * �@@�@@�@@�@@this.�ڋq����X�V(�����ڋq���薾��Params(index2))�ɂ��<BR>
     * �@@�@@�@@�@@GTL�w�ւ̒ʒm���s���B<BR>
     * <BR>
     * �Q�|�Q�|�S�|�T�j�@@���ύϐ��ʁi�x�j���X�V����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�x �� �x �{ �����ڋq���薾��Params(index2).��萔��<BR>
     * <BR>
     * �Q�|�Q�|�S�|�U�j�@@����Params(index)�ɑ΂��A���萔���A���萔������� �̍Đݒ���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ϒ�����莞�̌��萔���A���萔������ł̒������A�����ɔ��f����B<BR>
     * <BR>
     * �@@�@@�@@���萔���F�@@�i�����l�j �| �����ڋq���薾��Params(index2).���萔��<BR>
     * �@@�@@�@@���萔������ŁF�@@�i�����l�j �| �����ڋq���薾��Params(index2).���萔�������<BR>
     * <BR>
     * ������END  LOOP�i�Q�j������<BR>
     * <BR>
     * �Q�|�Q�|�Q�j�@@�y�����e�[�u���z��Update���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@����Params(index).����ID�������̌���Params.����ID �̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�g���f�[�^�}�l�[�W��.updateContractByTrans(����Params(index))�ɂ��<BR>
     * �@@�@@�@@�@@update���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�������̌���Params�͍�����Ώۂ̌����ł��邽�߁A�v���p�e�B�ݒ�̂ݍs���B<BR>
     * �@@�@@�@@�@@��������Ώۂ̌�����DB�X�V�́AprocessNewMarginOrderExecution( )�̍Ō��<BR>
     * �@@�@@�@@�@@��xTrade�W�������ɂ����s����邽�߁A���̃��\�b�h���ł͍s��Ȃ��B<BR>
     * <BR>
     * ������������END  LOOP�i�P�j������������<BR>
     * <BR>
     * �R�j�@@return����B
     * @@param l_contractParams (����Params)<BR>
     * �@@�@@�@@������Ώۂ́A����Params�B
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@throws RuntimeSystemException
     * @@roseuid 40CFCDFC003E
     */
    protected void updateContractOpenFromMarketOrderedTrans(
        EqtypeContractParams l_contractParams,
        EqtypeFinTransactionParams l_finTransactionParams)
        throws RuntimeSystemException
    {
        final String STR_METHOD_NAME = "updateContractOpenFromMarketOrderedTrans(EqtypeContractParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnInsert = false;
        try
        {
            WEB3EquityPersistentDataManager l_dataManager = (WEB3EquityPersistentDataManager) this.getPersistenceManager();
            List l_lstfinParams = l_dataManager.getFinTransactionListOfOpenOrderByContract(l_contractParams.getContractId());
            //// �P�j�@@������Ώۂ́A����Params�̃v���p�e�B�ݒ���s���B
            //���������F�@@����Params.�������� + �����ڋq���薾��Params.��萔��
            l_contractParams.setOriginalQuantity(l_contractParams.getOriginalQuantity() + l_finTransactionParams.getQuantity());
            //�������F�@@����Params.������ + �����ڋq���薾��Params.��萔��
            l_contractParams.setQuantity(l_contractParams.getQuantity() + l_finTransactionParams.getQuantity());
            //�X�V���t�F�@@GtlUtils.getSystemTimestamp()
            l_contractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            if (l_contractParams.getOpenDate() != null)
            {
                int l_intParamsLength = 0;
                if (l_lstfinParams != null)
                {
                    l_intParamsLength = l_lstfinParams.size();
                }
                
                double l_dblCommissionFee = 0D;
                double l_dblCommissionFeeTax = 0D;
                for (int i = 0; i < l_intParamsLength; i++)
                {
                    EqtypeFinTransactionParams l_params = (EqtypeFinTransactionParams) l_lstfinParams.get(i);
                    l_dblCommissionFee += l_params.getCommissionFee();
                    l_dblCommissionFeeTax += l_params.getCommissionFeeTax();
                }
                //���萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*1)
                l_contractParams.setSetupFee(l_dblCommissionFee + l_finTransactionParams.getCommissionFee());
                //�����萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*1)
                l_contractParams.setOriginalSetupFee(l_dblCommissionFee + l_finTransactionParams.getCommissionFee());
                //���萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*2)
                l_contractParams.setSetupFeeTax(l_dblCommissionFeeTax + l_finTransactionParams.getCommissionFeeTax());
                //�����萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*2)
                l_contractParams.setOriginalSetupFeeTax(l_dblCommissionFeeTax + l_finTransactionParams.getCommissionFeeTax());
            }
            //(*1)�����̌���Params.���� == null�̏ꍇ�i�������̐V�KInsert���j�́A
            else
            {
                l_blnInsert = true;
                
                //���萔���F�@@�����̊����ڋq���薾��Params.�ϑ��萔�� ���Z�b�g�B
                l_contractParams.setSetupFee(l_finTransactionParams.getCommissionFee());
                //�����萔���F�@@�����̊����ڋq���薾��Params.�ϑ��萔�� ���Z�b�g�B
                l_contractParams.setOriginalSetupFee(l_finTransactionParams.getCommissionFee());
                //���萔������ŁF�@@�����̊����ڋq���薾��Params.�ϑ��萔������� ���Z�b�g�B
                l_contractParams.setSetupFeeTax(l_finTransactionParams.getCommissionFeeTax());
                //�����萔������ŁF�@@�����̊����ڋq���薾��Params.�ϑ��萔������� ���Z�b�g�B
                l_contractParams.setOriginalSetupFeeTax(l_finTransactionParams.getCommissionFeeTax());
                //�P�|�Q�|�P�j�@@�����̊����ڋq���薾��Params�̓����ڂ��Z�b�g����B
                //�����h�c
                l_contractParams.setAccountId(l_finTransactionParams.getAccountId());
                //�⏕�����h�c
                l_contractParams.setSubAccountId(l_finTransactionParams.getSubAccountId());
                //�s��h�c
                l_contractParams.setMarketId(l_finTransactionParams.getMarketId());
                //�����h�c
                l_contractParams.setProductId(l_finTransactionParams.product_id);
                //�����^�C�v
                l_contractParams.setProductType(l_finTransactionParams.getProductType());
                //�ŋ敪
                l_contractParams.setTaxType(l_finTransactionParams.getTaxType());

                //�擾�g�����������}�l�[�W��
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                //�P�|�Q�|�Q�j�@@�����̊����ڋq���薾��Params.�����P��ID�̒����P�ʃI�u�W�F�N�g(*3)�̓����ڂ��Z�b�g����B
                OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_finTransactionParams.getOrderUnitId());
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
                //�ٍϋ敪
                l_contractParams.setRepaymentType(l_orderUnitRow.getRepaymentType());
                //�ٍϊ����l
                l_contractParams.setRepaymentNum(l_orderUnitRow.getRepaymentNum());
                //�����P���F�@@�����ڋq���薾��Params.���P��
                l_contractParams.setOriginalContractPrice(l_finTransactionParams.getPrice());
                //���P���F�@@�����ڋq���薾��Params.���P��
                l_contractParams.setContractPrice(l_finTransactionParams.getPrice());
                //���敪�F�@@ContractTypeEnum.getContractType(�����ڋq���薾��Params.�g�����U�N�V�����^�C�v)
                ContractTypeEnum l_contractType = ContractTypeEnum.getContractType(l_finTransactionParams.getFinTransactionType());
                l_contractParams.setContractType(l_contractType);
                //�����F�@@�����ڋq���薾��Params.���ID�ɊY��������I�u�W�F�N�g.��������YYYYMMDD
                OrderExecution l_orderExecution =
                    l_orderManager.getOrderExecution(l_finTransactionParams.getOrderExecutionId());
                Date l_datOpenDate = WEB3DateUtility.toDay(l_orderExecution.getExecutionTimestamp());
                l_contractParams.setOpenDate(l_datOpenDate);
                //�����F�@@this.get��������(����, �ٍϊ����l)
                l_contractParams.setCloseDate(this.getContractCloseDate(l_datOpenDate, l_orderUnitRow.getRepaymentNum()));

                WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();
                WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct) l_productManager.getTradedProduct(l_finTransactionParams.getProductId(), l_finTransactionParams.getMarketId());

                EqtypeTradedProductRow l_productRow = (EqtypeTradedProductRow) l_tradedProduct.getDataSourceObject();
                if (ContractTypeEnum.LONG.equals(l_contractType))
                {
                    //�ۏ؋����F�@@���敪��"����"�iLONG�j�̏ꍇ�́A�������.���ۏ؋���(*4)
                    l_contractParams.setMarginDepositRate(l_productRow.getLongMarginDepositRate());
                    //�����ۏ؋����F�@@���敪��"����"�iLONG�j�̏ꍇ�́A�������.�������ۏ؋���(*4)
                    l_contractParams.setCashMarginDepositRate(l_productRow.getLongCashMarginDepositRate());
                }
                else if (ContractTypeEnum.SHORT.equals(l_contractType))
                {
                    //���敪��"����"�iSHORT�j�̏ꍇ�́A�������.���ۏ؋���(*4)
                    l_contractParams.setMarginDepositRate(l_productRow.getShortMarginDepositRate());
                    //���敪��"����"�iSHORT�j�̏ꍇ�́A�������.�������ۏ؋���(*4)
                    l_contractParams.setCashMarginDepositRate(l_productRow.getShortCashMarginDepositRate());
                }
                //���������F�@@�����ɓ���
                l_contractParams.setFirstOpenDate(l_contractParams.getOpenDate());

            }
            //�Q�j�@@����莞�ɍ쐬���ꂽ�����f�[�^���y�����e�[�u���z���擾���A���萔���A���萔������� ��Update���s���B
            //�Q�|�P�j�@@����莞�ɍ쐬���ꂽ����Params��List���擾����B
            List l_lstContract = l_dataManager.getContractListByOrderUnit(l_finTransactionParams.getOrderUnitId());
            int l_intContractLength = 0;
            if (l_lstContract != null)
            {
                l_intContractLength = l_lstContract.size();
            }
            if (l_blnInsert == false)
            {
                boolean l_blnExistContract = false;
                for (int i = 0; i < l_intContractLength; i++)
                {
                    EqtypeContractParams l_params = (EqtypeContractParams) l_lstContract.get(i);
                    if (l_params.getContractId() == l_contractParams.getContractId())
                    {
                        l_blnExistContract = true;
                        break;
                    }
                }
                if (l_blnExistContract == false)
                {
                    l_lstContract.add(l_contractParams);
                    l_intContractLength = l_lstContract.size();
                }
            }

            //�ȉ��A�Q�|�P�j�Ŏ擾��������Params��List�̗v�f�iindex�j��Loop����B
            for (int i = 0; i < l_intContractLength; i++)
            {

                EqtypeContractParams l_params = (EqtypeContractParams) l_lstContract.get(i);
                //�Q�|�Q�j�@@����Params(index)�ɑ΂��A���萔���A���萔������� �̐ݒ���s���B���V�K��������莞�̈ϑ��萔�����ڂ���B
                //�Ȃ��A����Params(index).����ID�������̌���Params.����ID �̏ꍇ�́A���̌���Params(index)�̑ւ��ɁA�����̌���Params���g�p����B
                if (l_params.getContractId() == l_contractParams.getContractId())
                {
                    l_params = l_contractParams;
                }
                
                // �Q�|�Q�|�P�j����Params(index).����ID�������̌���Params.����ID �̏ꍇ�̂݁A 
                //     ����Params(index)�ɑ΂��A 
                //     �����萔���A���萔���A�����萔������ŁA���萔������� �̐ݒ���s���B 
                //     ���V�K��������莞�̈ϑ��萔�����ڂ���B 
	            double l_commissionFee = 0D;
	            double l_commissionFeeTax = 0D;
                if (l_params.getContractId() != l_contractParams.getContractId())
                {
	                //�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListOf�V�K������By����
	                List l_lstFin = l_dataManager.getFinTransactionListOfOpenOrderByContract(l_params.getContractId());
	                int l_intFinLength = 0;
	                if (l_lstFin != null)
	                {
	                    l_intFinLength = l_lstFin.size();
	                }
	                for (int j = 0; j < l_intFinLength; j++)
	                {
	                    EqtypeFinTransactionParams l_finParams = (EqtypeFinTransactionParams) l_lstFin.get(j);
	                    l_commissionFee += l_finParams.getCommissionFee();
	                    l_commissionFeeTax += l_finParams.getCommissionFeeTax();
	                }
	                //�����萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*5)
	                l_params.setOriginalSetupFee(l_commissionFee);
	                //���萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*5)
	                l_params.setSetupFee(l_commissionFee);
	                //�����萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*6)
	                l_params.setOriginalSetupFeeTax(l_commissionFeeTax);
	                //���萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*6)
	                l_params.setSetupFeeTax(l_commissionFeeTax);
	                //�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()
	                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                }
                
                //�Q�|�Q�|�Q�j�@@����Params(index)�ɑ΂���A���ϒ�����莞�̊����ڋq���薾��Params��S�Ď擾����B
                List l_lstFinTransaction = l_dataManager.getFinTransactionListOfCloseOrderByContract(l_params.getContractId());
                int l_intTransactionlength = 0;
                if (l_lstFinTransaction != null )
                {
                    l_intTransactionlength = l_lstFinTransaction.size();
                }

                //�Q�|�Q�|�R�j�@@���ύϐ��ʁi�x�j���O�ŏ���������B
                double l_Y = 0D;
                //�Q�|�Q�|�S�j�@@�ȉ��A�Q�|�Q�|�Q�j�Ŏ擾���������ڋq���薾��Params��List�̗v�f�iindex2�j��Loop����B
                //�Q�|�Q�|�S�|�P�j�@@�����ڋq���薾��Params(index2)�ɑ΂��A����.���萔���A���萔������ł��Ĉ�����B
                for (int k = 0; k < l_intTransactionlength; k++)
                {
                    EqtypeFinTransactionParams l_finParams1 = (EqtypeFinTransactionParams) l_lstFinTransaction.get(k);
                    //�@@���萔���F�@@����Params(index).���萔�� �~�i�����ڋq���薾��Params(index2).��萔�� ���i����Params(index).�������� �| �x�j�j(*7)
                    l_commissionFee = Math.floor(l_params.getSetupFee() * (l_finParams1.getQuantity() / (l_params.getOriginalQuantity() - l_Y)));
                    l_finParams1.setImportedSetupFee(l_commissionFee);
                    //���萔������ŁF�@@����Params(index).���萔������� �~�i�����ڋq���薾��Params(index2).��萔�� ���i����Params(index).�������� �| �x�j�j(*7)
                    l_commissionFeeTax = Math.floor(l_params.getSetupFeeTax() * (l_finParams1.getQuantity() / (l_params.getOriginalQuantity() - l_Y)));
                    l_finParams1.setImportedSetupFeeTax(l_commissionFeeTax);
                    //�X�V���t�F�@@GtlUtils.getSystemTimestamp()
                    l_finParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    //this.set�M�p������莞���z(�����ڋq���薾��Params(index2))�R�[���ɂ�蔽�f����B
                    this.setMarginNetAmount(l_finParams1);
                    //�g���f�[�^�}�l�[�W��.updateFinTransaction(�����ڋq���薾��Params(index2), �X�V�Ώۃv���p�e�BMap)�ɂ��update���s���B
                    HashMap l_map = new HashMap();
                    l_map.put("net_amount", new Double(l_finParams1.getNetAmount()));
                    l_map.put("capital_gain", new Double(l_finParams1.getCapitalGain()));
                    l_map.put("capital_gain_tax", new Double(l_finParams1.getCapitalGainTax())); 
                    l_map.put("imported_setup_fee", new Double(l_finParams1.getImportedSetupFee()));
                    l_map.put("imported_setup_fee_tax", new Double(l_finParams1.getImportedSetupFeeTax()));
                    l_map.put("last_updated_timestamp", l_finParams1.getLastUpdatedTimestamp());
                    l_dataManager.updateFinTransaction(l_finParams1, l_map);
                    //this.�ڋq����X�V(�����ڋq���薾��Params(index2))�ɂ��GTL�w�ւ̒ʒm���s���B
                    this.notifyGtl(l_finParams1);
                    //�Q�|�Q�|�S�|�T�j�@@���ύϐ��ʁi�x�j���X�V����B
                    //�x �� �x �{ �����ڋq���薾��Params(index2).��萔��
                    l_Y += l_finParams1.getQuantity();
                    //�@@�@@�@@���萔���F�@@�i�����l�j �| �����ڋq���薾��Params(index2).���萔��
                    l_params.setSetupFee(l_params.getSetupFee() - l_finParams1.getImportedSetupFee());
                    //���萔������ŁF�@@�i�����l�j �| �����ڋq���薾��Params(index2).���萔�������
                    l_params.setSetupFeeTax(l_params.getSetupFeeTax() - l_finParams1.getImportedSetupFeeTax());
                }
                //����Params(index).����ID�������̌���Params.����ID �̏ꍇ�̂݁A�g���f�[�^�}�l�[�W��.updateContractByTrans(����Params(index))�ɂ��update���s���B
                if (l_params.getContractId() != l_contractParams.getContractId())
                {
                    l_dataManager.updateContractByTrans(l_params);
                }
            }
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_wbe.getMessage(), l_wbe);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (DataException l_dfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_dfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dfe.getMessage(), l_dfe);
        }
    }

    /**
     * (get��������)<BR>
     * ����.�����ɐݒ肷������icloseDate�j���v�Z���Ԃ��B<BR>
     * �iDate getContractCloseDate(Date openDate)�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �����̌����A�ٍϋ敪�A�ٍϊ����l����A�������v�Z����B<BR>
     * <BR>
     * ---------------------------------------------------------<BR>
     * ���ٍϊ����l��������(*) �̏ꍇ<BR>
     * <BR>
     * �@@�@@"99991231"��Ԃ��B<BR>
     * <BR>
     * ���ٍϊ����l��������(*) �̏ꍇ<BR>
     * <BR>
     * �@@�@@�����́A�w������̓��t�����߂ĕԂ��B<BR>
     * �@@�@@���w���ٍϊ����l<BR>
     * <BR>
     * �@@�@@�c�Ɠ��v�Z.get�w��c�Ɠ�( )���\�b�h���R�[�������߂�B<BR>
     * �@@�@@�@@-----------------------------------------------------<BR>
     * �@@�@@�@@��get�w��c�Ɠ�( )�F�����ݒ�d�l��<BR>
     * �@@�@@�@@����F�@@�����̌���<BR>
     * �@@�@@�@@�N���F�@@0<BR>
     * �@@�@@�@@�����F�@@�����ٍ̕ϊ����l<BR>
     * �@@�@@�@@�����F�@@0<BR>
     * �@@�@@�@@���Z�^���Z�F�@@���Z<BR>
     * �@@�@@�@@-----------------------------------------------------<BR>
     * <BR>
     * �@@�@@�E�w������̓��t���J�����_�[���t�łȂ��ꍇ�i�U���R�P�����j�́A<BR>
     * �@@�@@�@@���Y���̍ŏI�c�Ɠ��������ƂȂ�B<BR>
     * �@@�@@�E�w������̓��t����c�Ɠ��̏ꍇ�́A<BR>
     * �@@�@@�@@���̓����O�̓��t�����c�Ɠ��ŁA�w������̓��t�ɍł��߂����t�������ƂȂ�B<BR>
     * ---------------------------------------------------------<BR>
     * <BR>
     * (*)������<BR>
     * �@@�@@�����|�W�V�����}�l�[�W��.is������(�����ٍ̕ϊ����l)��true�̏ꍇ�A<BR>
     * �@@�@@�������ł���Ɣ��肷��B<BR>
     * �@@�@@�ȊO�A�������łȂ��Ɣ��肷��B
     * @@param l_datOpenDate �����B
     * @@param l_dblRepaymentNum �ٍϊ����l�B
     * @@return Date
     * @@roseuid 40CFD51B033B
     */
    public Date getContractCloseDate(Date l_datOpenDate, double l_dblRepaymentNum) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCloseDate";
        log.entering(STR_METHOD_NAME);
        //�����̌����A�ٍϋ敪�A�ٍϊ����l����A�������v�Z����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        // �ٍϊ����l��������(*) �̏ꍇ<BR>"99991231"��Ԃ�
        if (l_positionManager.isIndefinite(l_dblRepaymentNum))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3DateUtility.getDate("99991231", "yyyyMMdd");
        }
        else
        {
            //�c�Ɠ��v�Z.get�w��c�Ɠ�( )���\�b�h���R�[�������߂�B
            Timestamp l_timestamp = new Timestamp(l_datOpenDate.getTime());
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeBizDate.getAppointmentBizDate(l_timestamp, 0L, Math.round(l_dblRepaymentNum), 0L, 1);
        }

    }

    /**
     * (set�M�p������莞���z)<BR>
     * �����蕪�ɂ��āA��n����A���n�v���z�A���n�v�Ŋz���v�Z���A<BR>
     * �����ڋq���薾��Params�̓����v���p�e�B�ɃZ�b�g����B<BR>
     * �ivoid setMarginNetAmount(EqtypeFinTransactionParams tparams)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����.�g�����U�N�V�����J�e�S����<BR>
     * �@@�@@�V�K���iEQTYPE_OPEN_MARGIN�j<BR>
     * �@@�@@�ԍρiEQTYPE_CLOSE_MARGIN�j<BR>
     * �@@�@@�������n�iEQTYPE_SWAP_MARGIN�j<BR>
     * �ȊO�̏ꍇ�́A��O��throw���������I������B<BR>
     * <BR>
     * =======================================================================<BR>
     * ���V�K���̏ꍇ<BR>
     * �@@�i�� ����.�g�����U�N�V�����J�e�S����FinTransactionCateg.EQTYPE_OPEN_MARGIN�j<BR>
     * <BR>
     * �P�j�@@��n����ɁA0���Z�b�g����B<BR>
     * <BR>
     * �Q�j�@@���n�v���z�ɁA0���Z�b�g����B<BR>
     * <BR>
     * �R�j�@@���n�v�Ŋz�ɁA0���Z�b�g����B<BR>
     * <BR>
     * =======================================================================<BR>
     * ���ԍς̏ꍇ<BR>
     * �@@�i�� ����.�g�����U�N�V�����J�e�S����FinTransactionCateg.EQTYPE_CLOSE_MARGIN�j<BR>
     * <BR>
     * �P�j�@@��n����i�����ϑ��v����j���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j�@@���ϑ��v������v�Z����B<BR>
     * <BR>
     * �@@�@@�@@this.calc���ϑ��v���( )�R�[���ɂ��A���ϑ��v������v�Z����B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���ϑ��v���( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����ڋq���薾��Params�F�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�F�@@�����ڋq���薾��Params(index)���Z�b�g����B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �P�|�Q�j�@@��n����ɁA�P�|�P�j�Ōv�Z�������ϑ��v������Z�b�g����B<BR>
     * <BR>
     * �Q�j�@@���n�v���z���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�����̊����ڋq���薾��Params.�ŋ敪���i"����"�A"������������򒥎�"�j�̏ꍇ�A<BR>
     * �@@�@@�@@���n�v���z�ɁA�P�|�Q�j�ŃZ�b�g������n��������̂܂܃Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�����̊����ڋq���薾��Params.�ŋ敪���i"����"�A"������������򒥎�"�j�̏ꍇ�A<BR>
     * �@@�@@�@@���n�v���z��0���Z�b�g����B<BR>
     * <BR>
     * �R�j�@@���n�v�Ŋz���v�Z����B<BR>
     * <BR>
     * �R�|�P�j�@@�����̊����ڋq���薾��Params.����ID�ɊY�����錚���I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�����|�W�V�����}�l�[�W��.get����(�����̊����ڋq���薾��Params.����ID)��<BR>
     * �@@�@@�@@�@@�@@�R�[������B<BR>
     * <BR>
     * �R�|�Q�j�@@���n�v�Ŋz���v�Z����B<BR>
     * <BR>
     * �R�|�Q�|�P�j�@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@���g���A�J�E���g�}�l�[�W��.get�ڋq(long)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@����ID�F�@@�����̊����ڋq���薾��Params.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�|�Q�j�@@�ڋq.get��n���M�p����ŋ敪( )�ɂ��A�M�p����̌ڋq�ŋ敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get��n���M�p����ŋ敪(Date)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@��n���F�@@(*C1)<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�|�R�j�@@�����v�Z�T�[�r�X.calc���n�v��( )�R�[���ɂ��A���n�v�ł��v�Z����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���n�v��(�⏕����, TaxTypeEnum, double, Timestamp, TaxTypeEnum)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�⏕�����F�@@�����̊����ڋq���薾��Params�̌���ID�A�⏕����ID�ɊY������⏕����<BR>
     * �@@�@@�@@�ŋ敪�F�@@�R�|�P�j�Ŏ擾��������.�ŋ敪<BR>
     * �@@�@@�@@���z�F�@@�Q�j�Ōv�Z�������n�v���z<BR>
     * �@@�@@�@@����F�@@�ԍϒ����̎�n��(*C1)<BR>
     * �@@�@@�@@�ڋq�ŋ敪�F�@@�ڋq.get��n���M�p����ŋ敪( )�̖߂�l<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@(*C1)�����̊����ڋq���薾��Params.�����P��ID�ɊY�����钍���P�ʃI�u�W�F�N�g.��n��<BR>
     * <BR>
     * =======================================================================<BR>
     * ���������n�̏ꍇ<BR>
     * �@@�i�� ����.�g�����U�N�V�����J�e�S����FinTransactionCateg.EQTYPE_SWAP_MARGIN�j<BR>
     * <BR>
     * ����.�g�����U�N�V�����^�C�v��<BR>
     * �@@�@@�����iEQTYPE_SWAP_MARGIN_LONG�j<BR>
     * �@@�@@���n�iEQTYPE_SWAP_MARGIN_SHORT�j<BR>
     * �ȊO�̏ꍇ�́A��O��throw���������I������B<BR>
     * <BR>
     * �P�j�@@��n������v�Z����B<BR>
     * <BR>
     * �P�|�P�j�@@�����蕪�ɂ��āA��n������v�Z����B<BR>
     * <BR>
     * �@@�@@�@@this.calc��n����i�������n�j( )�R�[���ɂ��A<BR>
     * �@@�@@�@@��n������v�Z����B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc��n����i�������n�j( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����ڋq���薾��Params�F�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�F�@@�����ڋq���薾��Params(index)���Z�b�g����B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �P�|�Q�j�@@��n����ɁA�P�|�P�j�Ōv�Z������n������Z�b�g����B<BR>
     * <BR>
     * �Q�j�@@���n�v���z���v�Z����B<BR>
     * <BR>
     * �Q�|�P�j�@@�����̏ꍇ�i�� ����.�g�����U�N�V�����^�C�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@FinTransactionType.EQTYPE_SWAP_MARGIN_LONG�j�j�́A<BR>
     * �@@�@@�@@���n�v���z�ɁA0���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@���n�̏ꍇ�i�� ����.�g�����U�N�V�����^�C�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT�j�j�́A<BR>
     * �@@�@@�@@�����v�Z�T�[�r�X.calc���n���v( )�R�[���ɂ��A<BR>
     * �@@�@@�@@���n�v���z���v�Z����B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���n���v(double, double, long, SubAccount, TaxTypeEnum)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@���z�F�@@�P�|�P�j�Ōv�Z������n���<BR>
     * �@@�@@�@@�����ʁF�@@�����̊����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@����ID�F�@@�����̊����ڋq���薾��Params.����ID<BR>
     * �@@�@@�@@�⏕�����F�@@�����̊����ڋq���薾��Params.����ID�A�⏕����ID�ɊY������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�ŋ敪�F�@@�����̊����ڋq���薾��Params.�����P��ID�ɊY������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��.�ŋ敪�i�������n�j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@�v�Z���ʂ��A���n�v���z�ɃZ�b�g����B<BR>
     * <BR>
     * �R�j�@@���n�v�Ŋz���v�Z����B<BR>
     * <BR>
     * �R�|�P�j�@@�����̏ꍇ�i�� ����.�g�����U�N�V�����^�C�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@FinTransactionType.EQTYPE_SWAP_MARGIN_LONG�j�j�́A<BR>
     * �@@�@@�@@�@@�@@���n�v�Ŋz�ɁA0���Z�b�g����B<BR>
     * <BR>
     * �R�|�Q�j�@@���n�̏ꍇ�i�� ����.�g�����U�N�V�����^�C�v��<BR>
     * �@@�@@�@@�@@�@@FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT�j�j�́A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̏������s���B<BR>
     * <BR>
     * �R�|�Q�|�P�j�@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@���g���A�J�E���g�}�l�[�W��.get�ڋq(long)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@����ID�F�@@�����̊����ڋq���薾��Params.����ID<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�|�Q�j�@@�ڋq.get��n���ŋ敪( )�ɂ��A�����̌ڋq�ŋ敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get��n���ŋ敪(Date)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@��n���F�@@(*S1)<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�|�Q�|�R�j�@@�����v�Z�T�[�r�X.calc���n�v��( )�R�[���ɂ��A���n�v�ł��v�Z����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc���n�v��(�⏕����, TaxTypeEnum, double, Timestamp, TaxTypeEnum)�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�⏕�����F�@@�����̊����ڋq���薾��Params�̌���ID�A�⏕����ID�ɊY������⏕����<BR>
     * �@@�@@�@@�ŋ敪�F�@@�����̊����ڋq���薾��Params.�����P��ID�ɊY������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��.�ŋ敪�i�������n�j<BR>
     * �@@�@@�@@���z�F�@@�Q�j�Ōv�Z�������n�v���z<BR>
     * �@@�@@�@@����F�@@���n�����̎�n��(*S1)<BR>
     * �@@�@@�@@�ڋq�ŋ敪�F�@@�ڋq.get��n���ŋ敪( )�̖߂�l<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@�v�Z���ʂ��A���n�v�Ŋz�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@(*S1)�����̊����ڋq���薾��Params.�����P��ID�ɊY�����钍���P�ʃI�u�W�F�N�g.��n��<BR>
     * <BR>
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@throws DataException
     * @@roseuid 40CFDC6E03E7
     */
    protected void setMarginNetAmount(EqtypeFinTransactionParams l_finTransactionParams) throws DataException
    {
        final String STR_METHOD_NAME = "setMarginNetAmount";
        log.entering(STR_METHOD_NAME);
        try
        {
            //�����̊����ڋq���薾��Params.����ID�ɊY�����錚���I�u�W�F�N�g���擾����B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //�����|�W�V�����}�l�[�W��.get����(�����̊����ڋq���薾��Params.����ID)��<BR>�R�[������B
            WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(l_finTransactionParams.getContractId());

            //�����v�Z�T�[�r�X.calc���o��( )�R�[���ɂ��A���o��v�l(*2)���v�Z����B
            WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();

            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_finTransactionParams.getOrderUnitId());
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
            SubAccount l_subAccount = l_accountManager.getSubAccount(l_finTransactionParams.getAccountId(), l_finTransactionParams.getSubAccountId());
            WEB3GentradeMainAccount l_account =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_finTransactionParams.getAccountId());
            
            //����.�g�����U�N�V�����J�e�S�����V�K���iEQTYPE_OPEN_MARGIN�j
            //�ԍρiEQTYPE_CLOSE_MARGIN�j
            //�������n�iEQTYPE_SWAP_MARGIN�j
            //�ȊO�̏ꍇ�́A��O��throw���������I������B
            if (!FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg())
                && !FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg())
                && !FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                String msg = "setMarginNetAmount: too much to settle(" + l_finTransactionParams.getFinTransactionCateg() + ")";
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00824, msg);
            }
            //���V�K���̏ꍇ�i�� ����.�g�����U�N�V�����J�e�S����FinTransactionCateg.EQTYPE_OPEN_MARGIN�j
            else if (FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                //�P�j�@@��n����ɁA0���Z�b�g����B
                l_finTransactionParams.setNetAmount(0);
                //�Q�j�@@���n�v���z�ɁA0���Z�b�g����B
                l_finTransactionParams.setCapitalGain(0);
                //�R�j�@@���n�v�Ŋz�ɁA0���Z�b�g����B
                l_finTransactionParams.setCapitalGainTax(0);

            }
            //���ԍς̏ꍇ�i�� ����.�g�����U�N�V�����J�e�S����FinTransactionCateg.EQTYPE_CLOSE_MARGIN�j
            else if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                //�P�j�@@��n����i�����ϑ��v����j���Z�b�g����B
                l_finTransactionParams.setNetAmount(this.calcRealizedProfitAndLossAmount(l_finTransactionParams));

                if (TaxTypeEnum.SPECIAL.equals(l_finTransactionParams.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_finTransactionParams.getTaxType()))
                {
                    //�@@�@@�@@�����̊����ڋq���薾��Params.�ŋ敪���i"����"�A"������������򒥎�"�j�̏ꍇ�A
                    //���n�v���z�ɁA�P�|�Q�j�ŃZ�b�g������n��������̂܂܃Z�b�g����B
                    l_finTransactionParams.setCapitalGain(this.calcRealizedProfitAndLossAmount(l_finTransactionParams));
                }
                else
                {
                    //�����̊����ڋq���薾��Params.�ŋ敪���i"����"�A"������������򒥎�"�j�̏ꍇ�A
                    //���n�v���z��0���Z�b�g����B
                    l_finTransactionParams.setCapitalGain(0);
                }
                //�R�j�@@���n�v�Ŋz���v�Z����B
                //�R�|�Q�j�@@�����v�Z�T�[�r�X.calc���n�v��( )�R�[���ɂ��A���n�v�Ŋz���v�Z����B
                TaxTypeEnum l_accountTaxType =
                    l_account.getDeliveryDateMarginTaxType(l_orderUnit.getDeliveryDate());
                double l_dblCapitalGainTax =
                    l_bizLogicProvider.calcCapitalGainTax(
                        l_subAccount,
                        l_contractRow.getTaxType(),
                        this.calcRealizedProfitAndLossAmount(l_finTransactionParams),
                        new Timestamp(l_orderUnit.getDeliveryDate().getTime()),
                        l_accountTaxType);
                l_finTransactionParams.setCapitalGainTax(l_dblCapitalGainTax);
            }

            //���������n�̏ꍇ�i�� ����.�g�����U�N�V�����J�e�S����FinTransactionCateg.EQTYPE_SWAP_MARGIN�j
            else if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_finTransactionParams.getFinTransactionCateg()))
            {
                //����.�g�����U�N�V�����^�C�v�������iEQTYPE_SWAP_MARGIN_LONG�j���n�iEQTYPE_SWAP_MARGIN_SHORT�j�ȊO�̏ꍇ�́A��O��throw���������I������B
                if (!FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_finTransactionParams.getFinTransactionType())
                    && !FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_finTransactionParams.getFinTransactionType()))
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�g�����U�N�V�����^�C�v���s���ł��B");
                }
                else
                {
                    //�P�j�@@��n������v�Z����B
                    l_finTransactionParams.setNetAmount(this.calcNetAmountSwap(l_finTransactionParams));
                    //�Q�j�@@���n�v���z���v�Z����B
                    if (FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_finTransactionParams.getFinTransactionType()))
                    {
                        //�Q�|�P�j�@@�����̏ꍇ�i�� ����.�g�����U�N�V�����^�C�v��FinTransactionType.EQTYPE_SWAP_MARGIN_LONG�j�j�́A���n�v���z�ɁA0���Z�b�g����B
                        l_finTransactionParams.setCapitalGain(0);
                        //�R�j�@@���n�v�Ŋz���v�Z����B
                        l_finTransactionParams.setCapitalGainTax(0);
                    }
                    else if (FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_finTransactionParams.getFinTransactionType()))
                    {
                        //���n�̏ꍇ�i�� ����.�g�����U�N�V�����^�C�v��FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT�j�j�́A
                        //�����v�Z�T�[�r�X.calc���n���v( )�R�[���ɂ��A
                        //���n�v���z���v�Z����B

                        double l_capitaGanin =
                            l_bizLogicProvider.calcEstimatedCapitalGain(
                                l_finTransactionParams.getNetAmount(),
                                l_finTransactionParams.getQuantity(),
                                l_finTransactionParams.getProductId(),
                                l_subAccount,
                                l_orderUnitRow.getSwapTaxType());
                        l_finTransactionParams.setCapitalGain(l_capitaGanin);
                        //�@@�@@�@@�v�Z���ʂ��A���n�v�Ŋz�ɃZ�b�g����B
                        TaxTypeEnum l_accountTaxType =
                            l_account.getDeliveryDateTaxType(l_orderUnit.getDeliveryDate());
                        double l_dblCapitalGainTax =
                            l_bizLogicProvider.calcCapitalGainTax(
                                l_subAccount,
                                l_orderUnitRow.getSwapTaxType(),
                                l_capitaGanin,
                                new Timestamp(l_orderUnit.getDeliveryDate().getTime()),
                                l_accountTaxType);
                        l_finTransactionParams.setCapitalGainTax(l_dblCapitalGainTax);

                        //1.3.4.6 get���n�v�L�����(long, long, long, TaxTypeEnum, FinTransactionType)
                        String l_strCapitalGainStatus = 
                        	l_bizLogicProvider.getCapitalGainStatus(
                        		l_finTransactionParams.getAccountId(), 
    							l_finTransactionParams.getSubAccountId(), 
    							l_finTransactionParams.getProductId(), 
                                l_orderUnitRow.getSwapTaxType(), 
    							l_finTransactionParams.getFinTransactionType());
                        
                        //1.3.4.7  setCapitalGainStatus()
                        l_finTransactionParams.setCapitalGainStatus(l_strCapitalGainStatus);
                    }
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_wbe.getMessage(), l_wbe);
        }

    }

    /**
     * �iupdate�����f�[�^�j<BR>
     * <BR>
     * �V�K�������ɑ΂����������ɁA�����f�[�^�ɑ΂�<BR>
     * �|�ϑ��萔���̈����<BR>
     * �|�ϑ��萔���̈�����̌v�Z���ʂ��A�֘A����G���e�B�e�B�֔��f<BR>
     * ���s���B<BR>
     * <BR>
     * ���X�V�ΏۃG���e�B�e�B��<BR>
     * �����ڋq����iEqTypeFinTransaction�j<BR>
     * �ڋq����iGenFinTransaction�j<BR>
     * �����iEqTypeContract�j�F�V�K�������̏ꍇ�̂�<BR>
     * <BR>
     * �V�[�P���X�}�u�i�c���jupdate�����f�[�^�v�Q��<BR>
     * <BR>
     * �P�j�@@�����̖��.getOrderType( )���A<BR>
     *      �V�K���������iMARGIN_LONG�j�A�V�K���������iMARGIN_SHORT�j<BR>
     *      �����ԍϒ����iCLOSE_MARGIN_LONG�j�A�����ԍϒ����iCLOSE_MARGIN_SHORT�j��<BR>
     * �@@�@@�@@�����ꂩ�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * �@@�@@�@@�����̖��.getOrderType( )����L�ȊO�̏ꍇ�́A�������I����return����B<BR>
     * <BR>
     * �Q�j�@@�����̎���Ώۂ̖�肪�R�t�������P�ʂɂ��āA<BR>
     * �@@�@@�@@�R�t�������ڋq���薾�׃f�[�^��S�Ď擾���A<BR>
     * �@@�@@�@@�萔���̈�����v�Z���s���B<BR>
     * <BR>
     * �Q�|�P�j�@@����蕪�̊����ڋq���薾��Params��List���擾����B<BR>
     * <BR>
     * �@@�@@�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListBy�����P��(�����̖��.�����P��ID)<BR>
     * �@@�@@�R�[���ɂ��擾����B<BR>
     * �@@�@@����Ώۂ̒������ԍϒ����̏ꍇ�̂݁A <BR>
     *�@@�@@ �Y���f�[�^�Ȃ��̏ꍇ�́A�������I����return����B�i������v�Z�s�v�̂��߁j <BR>
     * <BR>
     * �Q?�Q�j�@@�Q?�P�j�ŊY������f�[�^���������ꍇ�́A <BR>
     * �@@�@@�@@�@@�@@�ϑ��萔���̈�����v�Z�A�y�сy�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z�� <BR>
     * �@@�@@�@@�@@�@@�X�V�y��GTL�w�ւ̒ʒm���s���B <BR>
     * <BR>
     * �@@�@@�����v�Z�T�[�r�X.calc�萔���i���j( )���R�[������B<BR>
     *  �@@�@@�����ɂ́A�Q�|�P�j�Ŏ擾����List��toArray( )�Ŕz��ɕϊ����Đݒ肷��B<BR>
     * <BR>
     * �@@�@@�@@�ȉ��A�Q�|�P�j�Ŏ擾����List�̗v�f�iindex�j��Loop����B<BR>
     * <BR>
     * ������START LOOP������<BR>
     * <BR>
     * �Q�|�Q�|�P�j�@@�ϑ��萔���A�ϑ��萔������ŁA�X�V���t���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�ϑ��萔���F�@@ConsolidatedCommissionInfo.getCommission(index)<BR>
     * �@@�@@�@@�@@�ϑ��萔������ŁF�@@ConsolidatedCommissionInfo.getSalesTax(index)<BR>
     * �@@�@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �Q�|�Q�|�Q�j�@@�ԍϒ����̏ꍇ<BR>
     *         (�����̖��.getOrderType( )���A<BR>
     *          �����ԍϒ���(CLOSE_MARGIN_LONG)�A�����ԍϒ���(CLOSE_MARGIN_SHORT)��<BR>
     *          �����ꂩ�̏ꍇ)�̂݁A<BR>
     *          ��n����A���n���v���z�A���n���v�Ŋz�ɁA���ώ萔���̕ϓ��𔽉f����B<BR>
     * <BR>
     *          this.set�M�p������莞���z(�����ڋq����Params(index))�R�[���ɂ�蔽�f����B<BR>
     * <BR>
     * �Q�|�Q�|�R�j�@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�g���f�[�^�}�l�[�W��.updateFinTransaction(�����ڋq���薾��Params(index), <BR>
     * �@@�@@�@@�@@�X�V�Ώۃv���p�e�BMap)�ɂ��Aupdate���s���B<BR>
     * �@@�@@�@@�@@���X�V�Ώۃv���p�e�BMap�ɂ́A�Q�|�Q�|�P�j�ŃZ�b�g�����v���p�e�B�y�ђl���Z�b�g����B<BR>
     * <BR>
     * �Q�|�Q�|�S�j�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update��GTL�w�ɒʒm����B<BR>
     * <BR>
     * �@@�@@�@@�@@this.�ڋq����X�V(�����ڋq���薾��Params(index))�ɂ��<BR>
     * �@@�@@�@@�@@GTL�w�ւ̒ʒm���s���B<BR>
     * <BR>
     * ������END  LOOP������<BR>
     * <BR>
     * �ԍϒ����̏ꍇ<BR>
     *  (�����̖��.getOrderType( )���A<BR>
     *   �����ԍϒ���(CLOSE_MARGIN_LONG)�A�����ԍϒ���(CLOSE_MARGIN_SHORT)��<BR>
     *   �����ꂩ�̏ꍇ)�́A�ȍ~�̏����͍s�킸return����B<BR>
     *  �V�K�������̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �R�j�@@�����̎���Ώۂ̖�肪�R�t�������P�ʂō쐬���ꂽ�����ɂ��āA<BR>
     * �@@�@@�@@�Q�j�̎萔���̈�����v�Z�̌��ʂ𔽉f���X�V����B<BR>
     * <BR>
     * �R�|�P�j�@@����莞�ɍ쐬���ꂽ����Params��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�g���f�[�^�}�l�[�W��.get����ListBy�����P��(<BR>
     * �@@�@@�@@�@@�@@�����̖��.�����P��ID)�ɂ��擾����B<BR>
     * <BR>
     * �R�|�Q�j�@@�ȉ��A�R�|�P�j�Ŏ擾����List�̗v�f�iindex�j��Loop����B<BR>
     * <BR>
     * ������������START LOOP�i�P�j������������<BR>
     * <BR>
     * �R�|�Q�|�P�j�@@����Params(index)�ɑ΂��A<BR>
     *�@@�@@�@@�@@�@@�@@�@@ �����萔���A���萔���A�����萔������ŁA���萔������� �̐ݒ���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@���V�K��������莞�̈ϑ��萔�����ڂ���B<BR>
     * <BR>
     * �@@�@@�@@�����萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*1)<BR>
     * �@@�@@�@@���萔���F�@@�����ڋq���薾��Params.�ϑ��萔�� ��SUM�l(*1)<BR>
     * �@@�@@�@@�����萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*2)<BR>
     * �@@�@@�@@���萔������ŁF�@@�����ڋq���薾��Params.�ϑ��萔������� ��SUM�l(*2)<BR>
     * �@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �@@�@@�@@(*1)�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListOf�V�K������By����(<BR>
     * �@@�@@�@@�@@�@@����Params(index).����ID)�Ŏ擾�����A<BR>
     * �@@�@@�@@�@@�@@�����ڋq���薾��Params��List�̑S�v�f�̈ϑ��萔�� ��SUM�l<BR>
     * �@@�@@�@@�@@�@@���Y������f�[�^�����݂��Ȃ��ꍇ�́A0�Ƃ���B<BR>
     * <BR>
     * �@@�@@�@@(*2)�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListOf�V�K������By����(<BR>
     * �@@�@@�@@�@@�@@����Params(index).����ID)�Ŏ擾�����A<BR>
     * �@@�@@�@@�@@�@@�����ڋq���薾��Params��List�̑S�v�f�̈ϑ��萔������� ��SUM�l<BR>
     * �@@�@@�@@�@@�@@���Y������f�[�^�����݂��Ȃ��ꍇ�́A0�Ƃ���B<BR>
     * <BR>
     * <BR>
     * �R�|�Q�|�Q�j�@@����Params(index)�ɑ΂���A���ϒ�����莞�̊����ڋq���薾��Params��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�S�Ď擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListOf���ϒ���By����(<BR>
     * �@@�@@�@@�@@�@@����Params(index).����ID)�ɂ��擾����B<BR>
     * <BR>
     * �R�|�Q�|�R�j�@@���ύϐ��ʁi�x�j���O�ŏ���������B<BR>
     * <BR>
     * �R�|�Q�|�S�j�@@�ȉ��A�R�|�Q�|�Q�j�Ŏ擾���������ڋq���薾��Params��List�̗v�f�iindex2�j��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@Loop����B<BR>
     * <BR>
     * ������START LOOP�i�Q�j������<BR>
     * <BR>
     * �R�|�Q�|�S�|�P�j�@@�����ڋq���薾��Params(index2)�ɑ΂��A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����.���萔���A���萔������ł��Ĉ�����B<BR>
     * <BR>
     * �@@�@@�@@���萔���F�@@����Params(index).���萔�� �~<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����ڋq���薾��Params(index2).��萔�� ��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i����Params(index).�������� �| �x�j�j(*3)<BR>
     * �@@�@@�@@���萔������ŁF�@@����Params(index).���萔������� �~<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����ڋq���薾��Params(index2).��萔�� ��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i����Params(index).�������� �| �x�j�j(*3)<BR>
     * �@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �@@�@@�@@(*3)�ŏI�v�Z���ʂ��A�~�����؎̂Ă���i�v�Z�ߒ��ł́A�ۂ߂͍s��Ȃ��j<BR>
     * <BR>
     * �R�|�Q�|�S�|�Q�j�@@��n����A���n�v���z�A���n�v�Ŋz�ɁA���萔�����̕ϓ��𔽉f����B<BR>
     * <BR>
     * �@@�@@�@@�@@this.set�M�p������莞���z(�����ڋq���薾��Params(index2))�R�[���ɂ�蔽�f����B<BR>
     * <BR>
     * �R�|�Q�|�S�|�R�j�@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��Update���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�g���f�[�^�}�l�[�W��.updateFinTransaction(�����ڋq���薾��Params(index2), <BR>
     * �@@�@@�@@�@@�X�V�Ώۃv���p�e�BMap)�ɂ��Aupdate���s���B<BR>
     * �@@�@@�@@�@@���X�V�Ώۃv���p�e�BMap�ɂ́A�R�|�Q�|�S�|�P�j�y�тR�|�Q�|�S�|�Q�j�ŃZ�b�g����<BR>
     * �@@�@@�@@�@@���v���p�e�B�y�ђl���Z�b�g����B<BR>
     * <BR>
     * �R�|�Q�|�S�|�S�j�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update��GTL�w�ɒʒm����B<BR>
     * <BR>
     * �@@�@@�@@�@@this.�ڋq����X�V(�����ڋq���薾��Params(index2))�ɂ��<BR>
     * �@@�@@�@@�@@GTL�w�ւ̒ʒm���s���B<BR>
     * <BR>
     * �R�|�Q�|�S�|�T�j�@@���ύϐ��ʁi�x�j���X�V����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�x �� �x �{ �����ڋq���薾��Params(index2).��萔��<BR>
     * <BR>
     * �R�|�Q�|�S�|�U�j�@@����Params(index)�ɑ΂��A���萔���A���萔������� �̍Đݒ���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����ϒ�����莞�̌��萔���A���萔������ł̒������A�����ɔ��f����B<BR>
     * <BR>
     * �@@�@@�@@���萔���F�@@�i�����l�j �| �����ڋq���薾��Params(index2).���萔��<BR>
     * �@@�@@�@@���萔������ŁF�@@�i�����l�j �| �����ڋq���薾��Params(index2).���萔�������<BR>
     * <BR>
     * ������END  LOOP�i�Q�j������<BR>
     * <BR>
     * �R�|�Q�|�T�j�@@�y�����e�[�u���z��Update���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�g���f�[�^�}�l�[�W��.updateContractByTrans(����Params(index))�ɂ��<BR>
     * �@@�@@�@@�@@update���s���B<BR>
     * <BR>
     * ������������END  LOOP�i�P�j������������<BR>
     * <BR>
     * �S�j�@@return����B
     * @@param l_orderExecution (���)<BR>
     * �@@�@@�@@����Ώۂ̖��f�[�^�B
     * @@throws WEB3BaseException
     * @@roseuid 40D0EB7403BD
     */
    protected void updateExecutedData(EqTypeOrderExecution l_orderExecution) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExecutedData(EqTypeOrderExecution)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̖��.getOrderType( )���A
        //      �V�K���������iMARGIN_LONG�j�A�V�K���������iMARGIN_SHORT�j
        //      �����ԍϒ����iCLOSE_MARGIN_LONG�j�A�����ԍϒ����iCLOSE_MARGIN_SHORT�j��
        // �@@�@@�@@�����ꂩ�̏ꍇ�́A�ȉ��̏������s���B
        // �@@�@@�@@�����̖��.getOrderType( )����L�ȊO�̏ꍇ�́A�������I����return����B
        WEB3EquityPositionManagerHelper l_positionManagerHelper = new WEB3EquityPositionManagerHelper(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager l_persistentDataManager =
            (WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager) l_positionManagerHelper.getPersistenceManager();
        EqtypeOrderExecutionRow l_orderExecutionRow = (EqtypeOrderExecutionRow) l_orderExecution.getDataSourceObject();
        //EqtypeFinTransactionRow[]  l_finTransactionRow = null;
        if (!OrderTypeEnum.MARGIN_LONG.equals(l_orderExecutionRow.getOrderType()) && !OrderTypeEnum.MARGIN_SHORT.equals(l_orderExecutionRow.getOrderType())
            && !OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderExecution.getOrderType()) && !OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderExecution.getOrderType()))
        {
            return;
        }

        // �Q�j�@@�����̎���Ώۂ̖�肪�R�t�������P�ʂɂ��āA
        // �@@�@@�@@�R�t�������ڋq���薾�׃f�[�^��S�Ď擾���A
        // �@@�@@�@@�萔���̈�����v�Z���s���B
        // �Q�|�P�j�@@����蕪�̊����ڋq���薾��Params��List���擾����B
        List l_lstEqtypeFinTransaction = l_persistentDataManager.getFinTransactionListByOrderUnit(l_orderExecution.getOrderUnitId());
        
        // ������Ώۂ̒��� == �ԍϒ����A���� �Y���f�[�^�Ȃ��̏ꍇ�́A�������I����return����B�i������v�Z�s�v�̂��߁j
        if ((OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderExecution.getOrderType()) 
            || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderExecution.getOrderType()))
            && l_lstEqtypeFinTransaction.size() == 0)
        {
            return;
        }
        
        // �Q�|�Q�j�@@�Q�|�P�j�ŊY������f�[�^���������ꍇ�́A
        // �@@�@@�@@�@@�@@�ϑ��萔���̈�����v�Z�A�y�сy�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��
        // �@@�@@�@@�@@�@@�X�V�y��GTL�w�ւ̒ʒm���s���B
        // �@@�@@�����v�Z�T�[�r�X.calc�萔���i���j( )���R�[������
        if (l_lstEqtypeFinTransaction.size() != 0)
        {
	        EqtypeFinTransactionParams[] l_finTransactionParams = null;
	        l_finTransactionParams = new EqtypeFinTransactionParams[l_lstEqtypeFinTransaction.size()];
	        l_lstEqtypeFinTransaction.toArray(l_finTransactionParams);
	        
	        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
	        WEB3EquityBizLogicProvider l_ifoBizLogicProvider = null;
	        l_ifoBizLogicProvider = (WEB3EquityBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
	        ConsolidatedCommissionInfo l_CommissionInfo = l_ifoBizLogicProvider.calcCommission(l_finTransactionParams);
	        int l_intParams = 0;
	        if (l_finTransactionParams != null)
	        {
	            l_intParams = l_finTransactionParams.length;
	        }
            // �ȉ��A�Q�|�P�j�Ŏ擾����List�̗v�f�iindex�j��Loop����B
            for (int i = 0; i < l_intParams; i++)
            {
                // �Q�|�Q�|�P�j�@@�ϑ��萔���A�ϑ��萔������ŁA�X�V���t���Z�b�g����B
                double l_dblCommission = l_CommissionInfo.getCommission(i);
                l_finTransactionParams[i].setCommissionFee(l_dblCommission);
                double ldblSalesTax = l_CommissionInfo.getSalesTax(i);
                l_finTransactionParams[i].setCommissionFeeTax(ldblSalesTax);
                l_finTransactionParams[i].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                HashMap l_map = new HashMap();
                // �Q�|�Q�|�Q�j�@@�ԍϒ����̏ꍇ<BR>
                //         (�����̖��.getOrderType( )���A<BR>
                //          �����ԍϒ���(CLOSE_MARGIN_LONG)�A�����ԍϒ���(CLOSE_MARGIN_SHORT)��<BR>
                //          �����ꂩ�̏ꍇ)�̂݁A<BR>
                //          ��n����A���n���v���z�A���n���v�Ŋz�ɁA���ώ萔���̕ϓ��𔽉f����B<BR>
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderExecution.getOrderType())
                    || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderExecution.getOrderType()))
                {
                    try
                    {
	                    this.setMarginNetAmount(l_finTransactionParams[i]);
                    }
                    catch (DataException l_ex)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "set�M�p������莞���z()�Ɏ��s���܂��� �g�����U�N�V����ID:[" + l_finTransactionParams[i].getFinTransactionId() + "]",
                            l_ex);
                    }
	                l_map.put("net_amount", new Double(l_finTransactionParams[i].getNetAmount()));
	                l_map.put("capital_gain", new Double(l_finTransactionParams[i].getCapitalGain()));
	                l_map.put("capital_gain_tax", new Double(l_finTransactionParams[i].getCapitalGainTax()));
                    l_map.put("capital_gain_status", WEB3CapitalGainStatusDef.INVALIDITY);
                }
                
                // �Q�|�Q�|�R�j�@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update���s���B
                l_map.put("commission_fee", new Double(l_finTransactionParams[i].getCommissionFee()));
                l_map.put("commission_fee_tax", new Double(l_finTransactionParams[i].getCommissionFeeTax()));
                l_map.put("last_updated_timestamp", l_finTransactionParams[i].getLastUpdatedTimestamp());
                try
                {
	                l_persistentDataManager.updateFinTransaction(l_finTransactionParams[i], l_map);
                }
                catch (DataException l_ex)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�g�����U�N�V�����̍X�V�����Ɏ��s���܂����B" + l_finTransactionParams[i].toString(),
                        l_ex);
                }
                //�Q�|�Q�|�S�j�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update��GTL�w�ɒʒm����B
                this.notifyGtl(l_finTransactionParams[i]);
            }
        }
            
        // �ԍϒ����̏ꍇ
        //  (�����̖��.getOrderType( )���A
        //   �����ԍϒ���(CLOSE_MARGIN_LONG)�A�����ԍϒ���(CLOSE_MARGIN_SHORT)��
        //   �����ꂩ�̏ꍇ)�́A�ȍ~�̏����͍s�킸return����B
        //  �V�K�������̏ꍇ�́A�ȉ��̏������s���B
        if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderExecution.getOrderType())
            || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderExecution.getOrderType()))
        {
            return;
        }
            
        //  �R�j�@@�����̎���Ώۂ̖�肪�R�t�������P�ʂō쐬���ꂽ�����ɂ��āA
        // �@@�@@�@@�Q�j�̎萔���̈�����v�Z�̌��ʂ𔽉f���X�V����B
        // �R�|�P�j�@@����莞�ɍ쐬���ꂽ����Params��List���擾����B
        EqtypeFinTransactionParams[] l_finTransactionParams = null;
        List l_lstByOrderUnit = l_persistentDataManager.getContractListByOrderUnit(l_orderExecution.getOrderUnitId());
        EqtypeContractParams[] l_eqtypeContractParams = null;
        l_eqtypeContractParams = new EqtypeContractParams[l_lstByOrderUnit.size()];
        l_lstByOrderUnit.toArray(l_eqtypeContractParams);
        int l_intContractParams = 0;
        if (l_eqtypeContractParams != null)
        {
            l_intContractParams = l_eqtypeContractParams.length;
        }
        // �R�|�Q�j�@@�ȉ��A�R�|�P�j�Ŏ擾����List�̗v�f�iindex�j��Loop����B
        for (int i = 0; i < l_intContractParams; i++)
        {
            // �R�|�Q�|�P�j�@@����Params(index)�ɑ΂��A
            //�@@�@@�@@�@@�@@�@@�@@ �����萔���A���萔���A�����萔������ŁA���萔������� �̐ݒ���s���B
            //�@@�@@�@@�@@�@@�@@�@@ ���V�K��������莞�̈ϑ��萔�����ڂ���B
            List l_openOrderByContract = l_persistentDataManager.getFinTransactionListOfOpenOrderByContract(l_eqtypeContractParams[i].getContractId());
            l_finTransactionParams = new EqtypeFinTransactionParams[l_openOrderByContract.size()];
            l_openOrderByContract.toArray(l_finTransactionParams);
            double l_dblCommissionFee = 0D;
            double l_dblCommissionFeeTax = 0D;
            int l_intTransactionParamsLength = 0;
            if (l_finTransactionParams != null)
            {
                l_intTransactionParamsLength = l_finTransactionParams.length;
            }
            for (int j = 0; j < l_intTransactionParamsLength; j++)
            {
                l_dblCommissionFee += l_finTransactionParams[j].getCommissionFee();
                l_dblCommissionFeeTax += l_finTransactionParams[j].getCommissionFeeTax();
            }

            // �����萔��
            l_eqtypeContractParams[i].setOriginalSetupFee(l_dblCommissionFee);
            // ���萔��
            l_eqtypeContractParams[i].setSetupFee(l_dblCommissionFee);
            // �����萔�������
            l_eqtypeContractParams[i].setOriginalSetupFeeTax(l_dblCommissionFeeTax);
            // ���萔�������
            l_eqtypeContractParams[i].setSetupFeeTax(l_dblCommissionFeeTax);
            // �X�V���t
            l_eqtypeContractParams[i].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            // �R�|�Q�|�Q�j�@@����Params(index)�ɑ΂���A���ϒ�����莞�̊����ڋq���薾��Params��S�Ď擾����B
            List l_lsteqtypeFinTransaction = l_persistentDataManager.getFinTransactionListOfCloseOrderByContract(l_eqtypeContractParams[i].getContractId());
            int l_intTransactionlength = 0;
            if (l_lsteqtypeFinTransaction != null)
            {
                l_intTransactionlength = l_lsteqtypeFinTransaction.size();
            }

            // �R�|�Q�|�R�j�@@���ύϐ��ʁi�x�j���O�ŏ���������B
            double l_Y = 0D;

            // �R�|�Q�|�S�j�@@�ȉ��A�R�|�Q�|�Q�j�Ŏ擾���������ڋq���薾��Params��List�̗v�f�iindex2�j��Loop����B
            for (int k = 0; k < l_intTransactionlength; k++)
            {
                EqtypeFinTransactionParams l_finParams1 = (EqtypeFinTransactionParams) l_lsteqtypeFinTransaction.get(k);
                // �R�|�Q�|�S�|�P�j�@@�����ڋq���薾��Params(index2)�ɑ΂��A����.���萔���A���萔������ł��Ĉ�����B
                l_dblCommissionFee = Math.floor(l_eqtypeContractParams[i].getSetupFee() * (l_finParams1.getQuantity() / (l_eqtypeContractParams[i].getOriginalQuantity() - l_Y)));
                l_dblCommissionFeeTax = Math.floor(l_eqtypeContractParams[i].getSetupFeeTax() * (l_finParams1.getQuantity() / (l_eqtypeContractParams[i].getOriginalQuantity() - l_Y)));
                l_finParams1.setImportedSetupFee(l_dblCommissionFee);
                l_finParams1.setImportedSetupFeeTax(l_dblCommissionFeeTax);
                l_finParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                // �R�|�Q�|�S�|�Q�j�@@��n����A���n�v���z�A���n�v�Ŋz�ɁA���萔�����̕ϓ��𔽉f����B
                try
                {
	                this.setMarginNetAmount(l_finParams1);
                }
                catch (DataException l_ex)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "set�M�p������莞���z()�Ɏ��s���܂��� �g�����U�N�V����ID:[" + l_finParams1.getFinTransactionId() + "]",
                        l_ex);
                }
                // �R�|�Q�|�S�|�R�j�@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��Update���s���B
                HashMap l_map = new HashMap();
                l_map.put("net_amount", new Double(l_finParams1.getNetAmount()));
                l_map.put("capital_gain", new Double(l_finParams1.getCapitalGain()));
                l_map.put("capital_gain_tax", new Double(l_finParams1.getCapitalGainTax()));
                l_map.put("imported_setup_fee", new Double(l_finParams1.getImportedSetupFee()));
                l_map.put("imported_setup_fee_tax", new Double(l_finParams1.getImportedSetupFeeTax()));
                l_map.put("last_updated_timestamp", l_finParams1.getLastUpdatedTimestamp());
                l_map.put("capital_gain_status", l_finParams1.getCapitalGainStatus());
                
                try
                {
	                l_persistentDataManager.updateFinTransaction(l_finParams1, l_map);
                }
                catch (DataException l_ex)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�g�����U�N�V�����̍X�V�����Ɏ��s���܂����B" + l_finParams1.toString(),
                        l_ex);
                }
                // �R�|�Q�|�S�|�S�j�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z��update��GTL�w�ɒʒm����B
                this.notifyGtl(l_finParams1);

                // �R�|�Q�|�S�|�T�j�@@���ύϐ��ʁi�x�j���X�V����B
                // �@@�@@�@@�@@�@@�@@�@@�x �� �x �{ �����ڋq���薾��Params(index2).��萔��
                l_Y += l_finParams1.getQuantity();
                // �R�|�Q�|�S�|�U�j�@@����Params(index)�ɑ΂��A���萔���A���萔������� �̍Đݒ���s���B
                // �@@�@@�@@���萔���F�@@�i�����l�j �| �����ڋq���薾��Params(index2).���萔��
                l_eqtypeContractParams[i].setSetupFee(l_eqtypeContractParams[i].getSetupFee() - l_finParams1.getImportedSetupFee());
                // �@@�@@�@@���萔������ŁF�@@�i�����l�j �| �����ڋq���薾��Params(index2).���萔�������
                l_eqtypeContractParams[i].setSetupFeeTax(l_eqtypeContractParams[i].getSetupFeeTax() - l_finParams1.getImportedSetupFeeTax());
            }
            // �R�|�Q�|�T�j�@@�y�����e�[�u���z��Update���s���B
            try
            {
	            l_persistentDataManager.updateContractByTrans(l_eqtypeContractParams[i]);
            }
            catch (DataException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����e�[�u���̍X�V�����Ɏ��s���܂����B" + l_eqtypeContractParams[i].toString(),
                    l_ex);
            }
        }
    }

    /**
     * (calc���ϑ��v���)<BR>
     * ���ϑ��v������v�Z���ԋp����B<BR>
     * �i* �ԍϒ�����莞�́A���ϑ��v����̌v�Z���s�����\�b�h�j<BR>
     * <BR>
     * �P�j�@@�����̊����ڋq���薾��Params.����ID�ɊY�����錚���I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����|�W�V�����}�l�[�W��.get����(�����̊����ڋq���薾��Params.����ID)��<BR>
     * �@@�@@�@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�����蕪�ɂ��āA�����(*1)���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�����(*1) �� �擾��������.���P�� �~ �����̊����ڋq���薾��<BR>
     * Params.��萔��<BR>
     * <BR>
     * �@@�@@�@@�������������ɂ��A���P���ɂ͏����_�ȉ���Q�ʂ܂Œl�������Ă��邱�Ƃ�����̂ŁA<BR>
     * �@@�@@�@@���v�Z���ʂ̉~�����؎̂��s���B<BR>
     * <BR>
     * �R�j�@@�����蕪�ɂ��āA���o��v�l(*2)���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�����v�Z�T�[�r�X.calc���o��( )�R�[���ɂ��A���o��v�l(*2)���v�Z����B<BR>
     * �@@�@@�@@-------------------------------------------------------<BR>
     * �@@�@@�@@��calc���o��( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�ϑ��萔���A�ϑ��萔������ŁA<BR>
     * �@@�@@�@@���萔���A���萔������ŁA���`�������A<BR>
     * ���`����������ŁA�Ǘ���A�Ǘ������ŁA<BR>
     * �@@�@@�@@�������A�t�����A�݊����A���̑�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�F�@@�����̊����ڋq���薾��Params�̓����v���p�e�B�����ꂼ��Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@���敪�F�@@�擾��������.���敪<BR>
     * �@@�@@�@@-------------------------------------------------------<BR>
     * <BR>
     * �S�j�@@�����蕪�ɂ��āA��������i�ԍρj(*3)���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@��������i�ԍρj(*3) ��<BR>
     *  �����̊����ڋq���薾��Params.���P�� �~<BR>
     * �@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.��萔��<BR>
     * <BR>
     * �T�j�@@���ϑ��v������v�Z����B<BR>
     * <BR>
     * �@@�@@�@@���擾��������.���敪���h�����h�̏ꍇ��<BR>
     * �@@�@@�@@���ϑ��v��� �� <BR>
     * ��������i�ԍρj(*3) �| �����(*1) �| ���o��v�l(*2)<BR>
     * <BR>
     * �@@�@@�@@���擾��������.���敪���h�����h�̏ꍇ��<BR>
     * �@@�@@�@@���ϑ��v��� ��<BR>
     *  �����(*1) �| ��������i�ԍρj(*3) �| ���o��v�l(*2)<BR>
     * <BR>
     * �U�j�@@�T�j�̌v�Z���ʂ�ԋp����B
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@return double
     * @@roseuid 40DBC85D036F
     */
    public double calcRealizedProfitAndLossAmount(EqtypeFinTransactionParams l_finTransactionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcRealizedProfitAndLossAmount";
        log.entering(STR_METHOD_NAME);
        //�����̊����ڋq���薾��Params.����ID�ɊY�����錚���I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        //�����|�W�V�����}�l�[�W��.get����(�����̊����ڋq���薾��Params.����ID)��<BR>�R�[������B
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract = (WEB3EquityContract) l_positionManager.getContract(l_finTransactionParams.getContractId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }

        // �Q�j�@@�����蕪�ɂ��āA�����(*1)���v�Z����B
        //�����(*1) �� �擾��������.���P�� �~ �����̊����ڋq���薾��Params.��萔��
        double l_dblContractPrice = new BigDecimal(l_contract.getContractPrice() * l_finTransactionParams.getQuantity()).longValue();
        EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
        //���敪�F�@@�擾��������.���敪
        ContractTypeEnum l_contractTypeEnum = l_contractRow.getContractType();
        // �R�j�@@�����蕪�ɂ��āA���o��v�l(*2)���v�Z����B
        //�����v�Z�T�[�r�X.calc���o��( )�R�[���ɂ��A���o��v�l(*2)���v�Z����B
        WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
        //�ϑ��萔���A�ϑ��萔������ŁA���萔���A���萔������ŁA���`�������A
        //���`����������ŁA�Ǘ���A�Ǘ������ŁA�������A�t�����A�݊����A���̑�
        //�F�@@�����̊����ڋq���薾��Params�̓����v���p�e�B�����ꂼ��Z�b�g����B
        double l_dblExpenses =
            l_bizLogicProvider.calcExpenses(
                l_finTransactionParams.getCommissionFee(),
                l_finTransactionParams.getCommissionFeeTax(),
                l_finTransactionParams.getImportedSetupFee(),
                l_finTransactionParams.getImportedSetupFeeTax(),
                l_finTransactionParams.getImportedNameTransferFee(),
                l_finTransactionParams.getImportedNameTransferFeeTax(),
                l_finTransactionParams.getImportedManagementFee(),
                l_finTransactionParams.getImportedManagementFeeTax(),
                l_finTransactionParams.getImportedInterestFee(),
                l_finTransactionParams.getImportedPayInterestFee(),
                l_finTransactionParams.getImportedLoanEquityFee(),
                l_finTransactionParams.getImportedOther(),
                l_contractTypeEnum);

        //�����蕪�ɂ��āA��������i�ԍρj(*3)���v�Z����B 
        //��������i�ԍρj(*3) �������̊����ڋq���薾��Params.���P�� �~�����̊����ڋq���薾��Params.��萔��
        double l_price = l_finTransactionParams.getPrice() * l_finTransactionParams.getQuantity();

        // �T�j�@@���ϑ��v������v�Z����B

        //���擾��������.���敪���h�����h�̏ꍇ�����ϑ��v��� �� ��������i�ԍρj��������i�ԍρj(*3) �| �����(*1) �| ���o��v�l(*2)
        double l_dblIncome = 0D;
        if (ContractTypeEnum.LONG.equals(l_contractTypeEnum))
        {
            l_dblIncome = l_price - l_dblContractPrice - l_dblExpenses;
        }
        //���擾��������.���敪���h�����h�̏ꍇ�����ϑ��v��� �������(*1) �| ��������i�ԍρj(*3) �| ���o��v�l(*2)
        else if (ContractTypeEnum.SHORT.equals(l_contractTypeEnum))
        {
            l_dblIncome = l_dblContractPrice - l_price - l_dblExpenses;
        }

        //�U�j�@@�T�j�̌v�Z���ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblIncome;
    }

    /**
     * (set�����ڋq���薾�׃f�t�H���g�l)<BR>
     * �����̊����ڋq���薾��Params�̃v���p�e�B�ɁA�f�t�H���g�l���Z�b�g����B<BR>
     * �ivoid setMarketOrderedTransDefaultValues(EqtypeFinTransactionParams  tparams)<BR>
     *   �̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����̊����ڋq���薾��Params�̃v���p�e�B�ɁA�ȉ��̒ʂ�ɒl���Z�b�g�F<BR>
     * ----------------------------------<BR>
     * �g�����U�N�V�������������FGtlUtils.getSystemTimestamp()<BR>
     * �ϑ��萔���F0<BR>
     * �ϑ��萔������ŁF0<BR>
     * �����̎�n���z�F0<BR>
     * ���萔���F0<BR>
     * ���萔������ŁF0<BR>
     * ���`�������F0<BR>
     * ���`����������ŁF0<BR>
     * ���n�v���z�F0<BR>
     * ���n�v�Ŋz�F0<BR>
     * �Ǘ���F0<BR>
     * �Ǘ������ŁF0<BR>
     * �������F0<BR>
     * ����������ŁF0<BR>
     * �t�����F0<BR>
     * �t��������ŁF0<BR>
     * �݊����F0<BR>
     * ���̑��F0<BR>
     * ���p�ۗL���Y�̊Ǘ���F0<BR>
     * ���p�ۗL���Y�̊Ǘ������ŁF0<BR>
     * ���p�ۗL���Y�̎萔���F0<BR>
     * ���p�ۗL���Y�̎萔������ŁF0<BR>
     * ���Y�̕뉿�F0<BR>
     * �폜�t���O�F0�iFALSE�j<BR>
     * �������o�ߗ��q�F0<BR>
     * �쐬���t�FGtlUtils.getSystemTimestamp()<BR>
     * �X�V���t�FGtlUtils.getSystemTimestamp()<BR>
     * ----------------------------------
     * @@param l_finTransactionParams �����ڋq���薾��Params�I�u�W�F�N�g�B
     * @@roseuid 40DF561103DE
     */
    protected void setMarketOrderedTransDefaultValues(EqtypeFinTransactionParams l_finTransactionParams)
    {
        final String STR_METHOD_NAME = "setMarketOrderedTransDefaultValues";
        log.entering(STR_METHOD_NAME);
        //�����̊����ڋq���薾��Params�̃v���p�e�B�ɁA�ȉ��̒ʂ�ɒl���Z�b�g�F
        //�g�����U�N�V�������������FGtlUtils.getSystemTimestamp()
        l_finTransactionParams.setFinTransactionTimestamp(GtlUtils.getSystemTimestamp());
        //�ϑ��萔��
        l_finTransactionParams.setCommissionFee(0);
        //�ϑ��萔�������
        l_finTransactionParams.setCommissionFeeTax(0);
        //�����̎�n���z
        l_finTransactionParams.setImportedPaidValue(0);
        //���萔��
        l_finTransactionParams.setImportedSetupFee(0);
        //���萔�������
        l_finTransactionParams.setImportedSetupFeeTax(0);
        //���`������
        l_finTransactionParams.setImportedNameTransferFee(0);
        //���`�����������
        l_finTransactionParams.setImportedNameTransferFeeTax(0);
        //���n�v���z
        l_finTransactionParams.setCapitalGain(0);
        //���n�v�Ŋz
        l_finTransactionParams.setCapitalGainTax(0);
        //�Ǘ���
        l_finTransactionParams.setImportedManagementFee(0);
        //�Ǘ�������
        l_finTransactionParams.setImportedManagementFeeTax(0);
        //������
        l_finTransactionParams.setImportedInterestFee(0);
        //�����������
        l_finTransactionParams.setImportedInterestFeeTax(0);
        //�t����
        l_finTransactionParams.setImportedPayInterestFee(0);
        //�t���������
        l_finTransactionParams.setImportedPayInterestFeeTax(0);
        //�݊���
        l_finTransactionParams.setImportedLoanEquityFee(0);
        //���̑�
        l_finTransactionParams.setImportedOther(0);
        //���p�ۗL���Y�̊Ǘ���
        l_finTransactionParams.setTransferedAssetMngFee(0);
        //���p�ۗL���Y�̊Ǘ�������
        l_finTransactionParams.setTransferedAssetMngFeeTax(0);
        //���p�ۗL���Y�̎萔��
        l_finTransactionParams.setTransferedAssetSetupFee(0);
        //���p�ۗL���Y�̎萔�������
        l_finTransactionParams.setTransferedAssetMngFeeTax(0);
        //���Y�̕뉿
        l_finTransactionParams.setTransferedAssetBookValue(0);
        //�폜�t���O
        l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        //�������o�ߗ��q
        l_finTransactionParams.setAccruedInterest(0);
        //�쐬���t
        l_finTransactionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t
        l_finTransactionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc��n����i�������n�j)<BR>
     * �������n������莞�̎�n������v�Z���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̊����ڋq���薾��Params.����ID�ɊY�����錚���I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����|�W�V�����}�l�[�W��.get����(�����̊����ڋq���薾��Params.����ID)��<BR>
     * �@@�@@�@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@��n������v�Z���A�v�Z���ʂ�ԋp����B<BR>
     * <BR>
     * �@@�@@���擾��������.���敪���h�����h�̏ꍇ��<BR>
     * �@@�@@��n��� �� �| �i��萔�� �~ ���P���j �| �i���o��v�l(*)�j<BR>
     * <BR>
     * �@@�@@���擾��������.���敪���h�����h�̏ꍇ��<BR>
     * �@@�@@��n��� �� �i��萔�� �~ ���P���j �| �i���o��v�l(*)�j<BR>
     * <BR>
     * �@@�@@(*)���o��v�l�F<BR>
     * �@@�@@�����v�Z�T�[�r�X.calc���o��( )�R�[���ɂ��A���o��v�l���v�Z����B<BR>
     * �@@�@@---------------------------------------------------------<BR>
     * �@@�@@��calc���o��( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�ϑ��萔���A�ϑ��萔������ŁA<BR>
     * �@@�@@���萔���A���萔������ŁA���`�������A<BR>
     * ���`����������ŁA�Ǘ���A�Ǘ������ŁA<BR>
     * �@@�@@�������A�t�����A�݊����A���̑�<BR>
     * �@@�@@�@@�@@�@@�@@�F�@@�����̊����ڋq���薾��Params�̓����v���p�e�B�����ꂼ��Z�b�g����B<BR>
     * <BR>
     * �@@�@@���敪�F�@@�擾��������.���敪<BR>
     * �@@�@@------------------------------------------------------
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@return double
     * @@roseuid 40E21C6B027F
     */
    public double calcNetAmountSwap(EqtypeFinTransactionParams l_finTransactionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcNetAmountSwap";
        log.entering(STR_METHOD_NAME);
        try
        {
            //�����̊����ڋq���薾��Params.����ID�ɊY�����錚���I�u�W�F�N�g���擾����B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

            //�����|�W�V�����}�l�[�W��.get����(�����̊����ڋq���薾��Params.����ID)��<BR>�R�[������B
            WEB3EquityContract l_contract = (WEB3EquityContract) l_positionManager.getContract(l_finTransactionParams.getContractId());

            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
            //���敪�F�@@�擾��������.���敪
            ContractTypeEnum l_contractTypeEnum = l_contractRow.getContractType();

            //�����v�Z�T�[�r�X.calc���o��( )�R�[���ɂ��A���o��v�l(*2)���v�Z����B
            WEB3EquityBizLogicProvider l_bizLogicProvider = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            //�ϑ��萔���A�ϑ��萔������ŁA���萔���A���萔������ŁA���`�������A
            //���`����������ŁA�Ǘ���A�Ǘ������ŁA�������A�t�����A�݊����A���̑�
            //�F�@@�����̊����ڋq���薾��Params�̓����v���p�e�B�����ꂼ��Z�b�g����B
            double l_dblExpenses =
                l_bizLogicProvider.calcExpenses(
                    l_finTransactionParams.getCommissionFee(),
                    l_finTransactionParams.getCommissionFeeTax(),
                    l_finTransactionParams.getImportedSetupFee(),
                    l_finTransactionParams.getImportedSetupFeeTax(),
                    l_finTransactionParams.getImportedNameTransferFee(),
                    l_finTransactionParams.getImportedNameTransferFeeTax(),
                    l_finTransactionParams.getImportedManagementFee(),
                    l_finTransactionParams.getImportedManagementFeeTax(),
                    l_finTransactionParams.getImportedInterestFee(),
                    l_finTransactionParams.getImportedPayInterestFee(),
                    l_finTransactionParams.getImportedLoanEquityFee(),
                    l_finTransactionParams.getImportedOther(),
                    l_contractTypeEnum);

            double l_dblDeliveryAmount = 0D;
            //�擾��������.���敪���h�����h�̏ꍇ
            if (ContractTypeEnum.LONG.equals(l_contractTypeEnum))
            {
                //��n��� �� �| �i��萔�� �~ ���P���j �| �i���o��v�l(*)�j
                l_dblDeliveryAmount = - (l_finTransactionParams.getPrice() * l_finTransactionParams.getQuantity()) - l_dblExpenses;
            }
            //�擾��������.���敪���h�����h�̏ꍇ
            else if (ContractTypeEnum.SHORT.equals(l_contractTypeEnum))
            {
                //��n��� �� �i��萔�� �~ ���P���j �| �i���o��v�l(*)�j
                l_dblDeliveryAmount = (l_finTransactionParams.getPrice() * l_finTransactionParams.getQuantity()) - l_dblExpenses;
            }

            log.exiting(STR_METHOD_NAME);
            return l_dblDeliveryAmount;
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
    }

    /**
     * (giveBack���o��To����By�����ڋq���薾��)<BR>
     * �����̊����ڋq���薾��Params�̏��o����A����Params�̏��o��ɖ߂��B<BR>
     * �ivoid giveBackCostToContractByTrans(EqtypeContractParams cparams,<BR> 
     * �@@EqtypeFinTransactionParams tparams)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ---------------------------------------------------------<BR>
     * �������̌���Params�̍X�V�Ώۃv���p�e�B�i���o��ځj�͈ȉ��̒ʂ�B<BR>
     * ������Params�Ɗ����ڋq���薾��Params�ƂŁA�v���p�e�B�̕��������قȂ�̂Œ��ӁB<BR>
     * <BR>
     * ----------------------------------<BR>
     * ���萔��<BR>
     * ���萔�������<BR>
     * ���`������<BR>
     * ���`�����������<BR>
     * �Ǘ���<BR>
     * �Ǘ�������<BR>
     * ������<BR>
     * �t����<BR>
     * �݊���(*1)<BR>
     * ���̑�<BR>
     * <BR>
     * (*1)�����̌���Params.���敪��"����"�iSHORT�j�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@����Params.�݊����̐ݒ�l���X�V����B<BR>
     * �@@�@@�@@�����̌���Params.���敪��"����"�iLONG�j�̏ꍇ�́A<BR>
     * �@@�@@�@@����Params.�݊����̐ݒ�l�͍X�V���Ȃ��B�i�����l�̂܂܂Ƃ���j<BR>
     * <BR>
     * ---------------------------------------------------------<BR>
     * �������̌���Params�̍X�V�̌v�Z���͈ȉ��̒ʂ�B<BR>
     * <BR>
     * �����̌���Params�̃v���p�e�B �� ���݂̒l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{ �����̊����ڋq���薾��Params�̓����v���p�e�B�̒l<BR>
     * <BR>
     * ex.) �����̌���Params.���萔�� �� �����̌���Params.���萔��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{ �����̊����ڋq���薾��Params.��<BR>
     * �萔��
     * @@param l_contractParams - (����Params)<BR>
     * �@@�@@�@@������Ώۂ́A����Params�B
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@roseuid 40EBA03501F2
     */
    protected void giveBackCostToContractByTrans(EqtypeContractParams l_contractParams, EqtypeFinTransactionParams l_finTransactionParams)
    {
        final String STR_METHOD_NAME = "giveBackCostToContractByTrans";
        log.entering(STR_METHOD_NAME);

        //�������̌���Params�̍X�V�Ώۃv���p�e�B�i���o��ځj�͈ȉ��̒ʂ�B
        // ���萔��
        l_contractParams.setSetupFee(l_contractParams.getSetupFee() + l_finTransactionParams.getImportedSetupFee());
        //���萔�������
        l_contractParams.setSetupFeeTax(l_contractParams.getSetupFeeTax() + l_finTransactionParams.getImportedSetupFeeTax());
        //���`������
        l_contractParams.setNameTransferFee(l_contractParams.getNameTransferFee() + l_finTransactionParams.getImportedNameTransferFee());
        //���`�����������
        l_contractParams.setNameTransferFeeTax(l_contractParams.getNameTransferFeeTax() + l_finTransactionParams.getImportedNameTransferFeeTax());
        //�Ǘ���
        l_contractParams.setManagementFee(l_contractParams.getManagementFee() + l_finTransactionParams.getImportedManagementFee());
        //�Ǘ�������
        l_contractParams.setManagementFeeTax(l_contractParams.getManagementFeeTax() + l_finTransactionParams.getImportedManagementFeeTax());
        //������
        l_contractParams.setInterestFee(l_contractParams.getInterestFee() + l_finTransactionParams.getImportedInterestFee());
        //�t����
        l_contractParams.setPayInterestFee(l_contractParams.getPayInterestFee() + l_finTransactionParams.getImportedPayInterestFee());

        //�����̌���Params.���敪��"����"�iSHORT�j�̏ꍇ�̂݁A����Params.�݊����̐ݒ�l���X�V����B
        if (ContractTypeEnum.SHORT.equals(l_contractParams.getContractType()))
        {
            l_contractParams.setLoanEquityFee(l_contractParams.getLoanEquityFee() + l_finTransactionParams.getImportedLoanEquityFee());
        }
        //���̑�
        l_contractParams.setOther(l_contractParams.getOther() + l_finTransactionParams.getImportedOther());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ireverse�ۗL���YParamsBy�����ڋq���薾�ׁj<BR>
     * <BR>
     * �����̊����ڋq���薾��Params���A�������ۗL���YParams�ɔ��f����B<BR>
     * �ivoid reverseAssetParamsByMarketTradedTrans(AssetParams aparams, <BR>
     * �@@EqtypeFinTransactionParams trans)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ���ۗL���YParams�̍X�V�Ώۃv���p�e�B�A�y�эX�V�d�l�͈ȉ��̒ʂ�B<BR>
     * <BR>
     * ���ʁF�@@<BR>
     * �@@�@@�@@�@@�@@�����iSideEnum.BUY�j(*1)�̖�����̏ꍇ�́A�ۗL���Y�c���ʃ`�F�b�N���s���B<BR>
     * �@@�@@�@@�@@�@@-------------------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@�ۗL���Y�c����(*) �� 0.0D �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ۗL���Y�c���ʃ`�F�b�N�G���[�v�̗�O�i�Ɩ��G���[�j��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BaseRuntimeException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01931<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*)�ۗL���Y�c����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ۗL���YParams.���� �| �����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�@@�@@-------------------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@�ۗL���Y�c���ʃ`�F�b�N��OK�̏ꍇ�A�����̊����ڋq���薾��Params.��萔�ʂ����Z�B<BR>
     * �@@�@@�@@�@@�@@�����iSideEnum.SELL�j(*1)�̖�����̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.��萔�� �����Z�B<BR>
     * <BR>
     * ���ʁi�뉿�P���v�Z�p�j�F<BR>
     * �@@�@@�@@�@@�@@�����iSideEnum.BUY�j(*1)�̖�����̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.��萔�� �����Z�B<BR>
     * �@@�@@�@@�@@�@@�����iSideEnum.SELL�j(*1)�̖�����̏ꍇ�́A�X�V�Ȃ��B<BR>
     * <BR>
     * �뉿�i�뉿�P���v�Z�p�j�F<BR>
     * �@@�@@�@@�@@�@@�����iSideEnum.BUY�j(*1)�̖�����̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�X�V�ΏۂƂ���ꍇ(*2)�̂݁A<BR>
     * �@@�@@�@@�@@�@@�u�����̊����ڋq���薾��Params.��n��� �~ �i�|�P�j�v�����Z�B<BR>
     * �@@�@@�@@�@@�@@�����iSideEnum.SELL�j(*1)�̖�����̏ꍇ�́A�X�V�Ȃ��B<BR>
     * <BR>
     * �X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * (*1)�����^�����̔���<BR>
     * �g���|�W�V�����w���p�[.getSide(�����̊����ڋq���薾��Params.�g�����U�N�V�����^�C�v)�ɂ��<BR>
     * �擾����SideEnum���g�p���A������s���B<BR>
     * <BR>
     * (*2)�X�V�ΏۂƂ���ꍇ<BR>
     * �i�i�ۗL���YParams.�ŋ敪��"�������"�܂���"������������򒥎�"�j <BR>
     * �@@���� �X�V�O�ۗ̕L���YParams.�뉿�i�뉿�P���v�Z�p�j==0�j�̏ꍇ�݂͍̂X�V�Ȃ��B<BR>
     * �ȊO�A�X�V����B<BR>
     * ����ʌ����̏ꍇ�A�뉿==0��������X�V���Ȃ��B
     * @@param l_assetParams - (�ۗL���YParams)<BR>
     * �@@�@@�@@������Ώۂ́A�ۗL���YParams�B
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@ throws DataException
     * @@roseuid 40ECD6A900E0
     */
    protected void reverseAssetParamsByMarketTradedTrans(
        AssetParams l_assetParams,
        EqtypeFinTransactionParams l_finTransactionParams)
        throws DataException
    {
        final String STR_METHOD_NAME = "reverseAssetParamsByMarketTradedTrans(AssetParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        // �����iSideEnum.BUY�j�̖�����̏ꍇ
        if (SideEnum.BUY.equals(getSide(l_finTransactionParams.getFinTransactionType())))
        {
            // ����
            double l_dblQuantity = l_assetParams.getQuantity() - l_finTransactionParams.getQuantity();
            if (l_dblQuantity < 0.0D)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_assetParams.setQuantity(l_assetParams.getQuantity() - l_finTransactionParams.getQuantity());
            // ���ʁi�뉿�P���v�Z�p�j
            l_assetParams.setQuantityForBookValue(l_assetParams.getQuantityForBookValue() - l_finTransactionParams.getQuantity());
            // �뉿�i�뉿�P���v�Z�p�j
            if (!TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetParams.getTaxType()) &&
                !TaxTypeEnum.SPECIAL.equals(l_assetParams.getTaxType()) &&
                l_assetParams.getBookValue() == 0.0D)
            {
                // ��ʌ����̏ꍇ�A�뉿==0��������X�V���Ȃ��B
            }
            else
            {
                l_assetParams.setBookValue(l_assetParams.getBookValue() - (l_finTransactionParams.getNetAmount() * -1.0D));
            }
        }
        // �����iSideEnum.SELL�j�̖�����̏ꍇ
        else if (SideEnum.SELL.equals(getSide(l_finTransactionParams.getFinTransactionType())))
        {
            // ����
            l_assetParams.setQuantity(l_assetParams.getQuantity() + l_finTransactionParams.getQuantity());
        }
        log.exiting(STR_METHOD_NAME);
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    /**
     * (reverse�擾�P�ʂłۗ̕L���YPositionBy�����ڋq���薾��)<BR>
     * �����̊����ڋq���薾��Params���A<BR>
     * ��������y�擾�P�ʂłۗ̕L���Y�e�[�u���z�ɔ��f����B<BR>
     * �ivoid reverseAssetUnitPositionByTrans(EqtypeFinTransactionParams trans, <BR>
     * �@@SideEnum side, AssetParams asset)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@����������return����B<BR>
     * �@@�@@�@@��xTrade�W�������𖳌��ɂ��邽�߂ɁA�I�[�o�[���C�h<BR>
     * �@@�@@�@@���iWeb�V�ł́y�擾�P�ʂłۗ̕L���Y�e�[�u���z�iEqTypeAssetUnit�j��<BR>
     * �@@�@@�@@���@@�g�p���Ă��Ȃ����߁j<BR>
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@param l_side (����)<BR>
     * �@@�@@�@@�����̕ʁB
     * @@param l_assetParams (�ۗL���YParams)<BR>
     * �@@�@@�@@������Ώۂ́A�ۗL���YParams�B
     * @@throws DataException
     * @@roseuid 40F2471F005C
     */
    protected void reverseAssetUnitPositionByTrans(EqtypeFinTransactionParams l_finTransactionParams, SideEnum l_side, AssetParams l_assetParams) throws DataException
    {

    }
    
    /**
     * �iupdate�����ԍ�From�����ڋq���薾�ׁj<BR>
     * <BR>
     * �����ڋq���薾�ׂ��A�ԍϒ�������莞�̌���Params�̃v���p�e�B�X�V���s���B<BR>
     * �����̊����ڋq���薾��Params�̏��o��v���p�e�B�X�V���s���B<BR>
     * �ivoid updateContractCloseFromMarketOrderedTrans(EqtypeContractParams cparams, <BR>
     * �@@EqtypeFinTransactionParams tparams)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����̎c���ʃ`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�@@�i�����̌���Params.������ �| �����̊����ڋq���薾��Params.��萔�ʁj �� 0.0D<BR>
     * �@@�@@�@@�̏ꍇ�A�u�����c���ʃ`�F�b�N�G���[�v�̗�O�i�Ɩ��G���[�j��throw����B<BR>
     * �@@�@@�@@class: WEB3BaseRuntimeException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_01934<BR>
     * <BR>
     * �Q�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * <BR>
     * �@@�@@this.calc���o����䗦(�����̌���Params.������, <BR>
     * �@@�@@�@@�@@�����̊����ڋq���薾��Params,��萔��)�R�[���ɂ��擾����B<BR>
     * <BR>
     * �R�j�@@�����̌���Params�̏��o����A�����̊����ڋq���薾��Params�Ɉړ�������B<BR>
     * <BR>
     * �@@�@@ this.takeIn���o��From����To�����ڋq���薾��( )���\�b�h���R�[������B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��takeIn���o��From����To�����ڋq���薾��( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@���䗦�F�@@�Q�j�ŋ��߂����䗦�ifactor�j<BR>
     * �@@�@@�@@����Params�F�@@�����̌���Params<BR>
     * �@@�@@�@@�����ڋq���薾��Params�F�@@�����̊����ڋq���薾��Params<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �S�j�@@�����̌���Params�̃v���p�e�B���X�V����B<BR>
     * <BR>
     * �@@�@@�@@�������F�@@�i�����l�j �| �����̊����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �T�j�@@return����B
     * @@param l_contractParams (����Params)<BR>
     * �@@�@@�@@������Ώۂ́A����Params�B
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@throws RuntimeSystemException
     * @@roseuid 40F75EBC0121
     */
    protected void updateContractCloseFromMarketOrderedTrans(
        EqtypeContractParams l_contractParams,
        EqtypeFinTransactionParams l_finTransactionParams)
        throws RuntimeSystemException
    {
        final String STR_METHOD_NAME =
            "updateContractCloseFromMarketOrderedTrans(EqtypeContractParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̎c���ʃ`�F�b�N���s���B
        // �@@�@@�@@�i�����̌���Params.������ �| �����̊����ڋq���薾��Params.��萔�ʁj �� 0.0D
        // �@@�@@�@@�̏ꍇ�A�u�����c���ʃ`�F�b�N�G���[�v�̗�O�i�Ɩ��G���[�j��throw����B
        if (l_contractParams.getQuantity() - l_finTransactionParams.getQuantity() < 0.0D)
        {
            String msg = "updateContractCloseFromMarketOrderedTrans: too much to settle(" + l_finTransactionParams.getQuantity() + ") for (" + l_contractParams.getQuantity() + ")";
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_01934, msg);
        }
        // �Q�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
        double factor = this.calcExpensesFactorRatio(l_contractParams.getQuantity(), l_finTransactionParams.getQuantity());

        // �R�j�@@�����̌���Params�̏��o����A�����̊����ڋq���薾��Params�Ɉړ�������B
        this.takeInCostFromContractToTrans(factor, l_contractParams, l_finTransactionParams);

        // �S�j�@@�����̌���Params�̃v���p�e�B���X�V����B
        l_contractParams.setQuantity(l_contractParams.getQuantity() - l_finTransactionParams.getQuantity());
        l_contractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iupdate�����������nFrom�����ڋq���薾�ׁj<BR>
     * <BR>
     * �����ڋq���薾�ׂ��A�������n��������莞�̌���Params�̃v���p�e�B�X�V���s���B<BR>
     * �����̊����ڋq���薾��Params�̏��o��v���p�e�B�X�V���s���B<BR>
     * �ivoid updateContractSwapFromMarketOrderedTrans(EqtypeContractParams cparams, <BR>
     * �@@EqtypeFinTransactionParams tparams)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����̎c���ʃ`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�@@�i�����̌���Params.������ �| �����̊����ڋq���薾��Params.��萔�ʁj �� 0.0D<BR>
     * �@@�@@�@@�̏ꍇ�A�u�����c���ʃ`�F�b�N�G���[�v�̗�O�i�Ɩ��G���[�j��throw����B<BR>
     * �@@�@@�@@class: WEB3BaseRuntimeException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_01934<BR>
     * <BR>
     * �Q�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * <BR>
     * �@@�@@this.calc���o����䗦(�����̌���Params.������, <BR>
     * �@@�@@�@@�@@�����̊����ڋq���薾��Params,��萔��)�R�[���ɂ��擾����B<BR>
     * <BR>
     * �R�j�@@�����̌���Params�̏��o����A�����̊����ڋq���薾��Params�Ɉړ�������B<BR>
     * <BR>
     * �@@�@@ this.takeIn���o��From����To�����ڋq���薾��( )���\�b�h���R�[������B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��takeIn���o��From����To�����ڋq���薾��( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@���䗦�F�@@�Q�j�ŋ��߂����䗦�ifactor�j<BR>
     * �@@�@@�@@����Params�F�@@�����̌���Params<BR>
     * �@@�@@�@@�����ڋq���薾��Params�F�@@�����̊����ڋq���薾��Params<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �S�j�@@�������n�������̓��e���A�y�ۗL���Y�e�[�u���z�ɔ��f����B<BR>
     * <BR>
     * �@@�@@�@@this.applyTo�ۗL���Y�|�W�V����( )���\�b�h���R�[������B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��applyTo�ۗL���Y�|�W�V����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�����ڋq���薾��Params�F�@@�����̊����ڋq���薾��Params<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �T�j�@@�����̌���Params�̃v���p�e�B���X�V����B<BR>
     * <BR>
     * �@@�@@�@@�������F�@@�i�����l�j �| �����̊����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �U�j�@@return����B<BR>

     * <BR>
     * �@@�@@�@@�g�����U�N�V�����^�C�v�F�@@�S�|�P�j�Őݒ肵�����O�̒l<BR>
     * �@@�@@�@@�������F�@@�i�����l�j �| �����̊����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �@@�@@�@@���g�����U�N�V�����^�C�v�F�����ڋq���薾��Params<BR>
     * �@@�@@�@@���������A�X�V���t�F����Params<BR>
     * <BR>
     * �U�j�@@return����B
     * @@param l_contractParams (����Params)<BR>
     * �@@�@@�@@������Ώۂ́A����Params�B
     * @@param l_finTransactionParams �����ڋq���薾��Params�B
     * @@throws DataException
     * @@roseuid 40F77CBE016F
     */
    protected List updateContractSwapFromMarketOrderedTrans(
        EqtypeContractParams l_contractParams,
        EqtypeFinTransactionParams l_finTransactionParams)
        throws DataException
    {
        final String STR_METHOD_NAME =
            "updateContractSwapFromMarketOrderedTrans(EqtypeContractParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����̎c���ʃ`�F�b�N���s���B
        // �@@�@@�@@�i�����̌���Params.������ �| �����̊����ڋq���薾��Params.��萔�ʁj �� 0.0D
        // �@@�@@�@@�̏ꍇ�A�u�����c���ʃ`�F�b�N�G���[�v�̗�O�i�Ɩ��G���[�j��throw����B
        if (l_contractParams.getQuantity() - l_finTransactionParams.getQuantity() < 0.0D)
        {
            String msg = "updateContractCloseFromMarketOrderedTrans: too much to settle(" + l_finTransactionParams.getQuantity() + ") for (" + l_contractParams.getQuantity() + ")";
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_01934, msg);
        }
        // �Q�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
        double factor = this.calcExpensesFactorRatio(l_contractParams.getQuantity(), l_finTransactionParams.getQuantity());

        // �R�j�@@�����̌���Params�̏��o����A�����̊����ڋq���薾��Params�Ɉړ�������B
        takeInCostFromContractToTrans(factor, l_contractParams, l_finTransactionParams);

        // �S�j�@@�������n�������̓��e���A�y�ۗL���Y�e�[�u���z�ɔ��f����B
        // �@@�@@�@@this.applyTo�ۗL���Y�|�W�V����( )���\�b�h���R�[������B
        this.applyToAssetPosition(l_finTransactionParams);

        // �T�j�@@�����̌���Params�̃v���p�e�B���X�V����B
        l_contractParams.setQuantity(l_contractParams.getQuantity() - l_finTransactionParams.getQuantity());
        l_contractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �icalc���o����䗦�j<BR>
     * <BR>
     * �����̌����c���A�y�ь��ϐ��ʂ��A���o��v�Z���Ɏg�p������䗦���v�Z��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B<BR>
     * <BR>
     * �@@�@@�@@-------------------------------------------------------------------<BR>
     * �@@�@@�@@�������̌����c�� == �����̌��ϐ��ʂ̏ꍇ��<BR>
     * �@@�@@�@@���䗦�ifactor�j��1.0D<BR>
     * <BR>
     * �@@�@@�@@����L�ȊO�̏ꍇ��<BR>
     * �@@�@@�@@���䗦�ifactor�j�������̌��ϐ��� �� �����̌����c��<BR>
     * �@@�@@�@@�����Z���ʂ̊ۂ߂͍s��Ȃ��B<BR>
     * �@@�@@�@@-------------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�P�j�ŋ��߂����䗦�ifactor�j��return����B
     * @@param l_dblBalance (�����c��)<BR>
     * �@@�@@�@@�����c���B
     * @@param l_dblClosingExecutedQuantity (���ϖ�萔��)<BR>
     * �@@�@@�@@���ϖ�萔�ʁi������́A�Y�������ւ̊��蓖�ĕ����ʁj�B
     * @@return double
     */
    protected double calcExpensesFactorRatio(double l_dblBalance, double l_dblClosingExecutedQuantity)
    {
        final String STR_METHOD_NAME = "calcExpensesFactorRatio(double, double)";
        log.entering(STR_METHOD_NAME);

        double factor = 0.0D;
        // �P�j�@@�����̏��o��������ڋq���薾�ׂɍڂ���ۂɎg�p����A���䗦�ifactor�j�����߂�B
        // �������̌����c�� == �����̌��ϐ��ʂ̏ꍇ��
        if (l_dblBalance == l_dblClosingExecutedQuantity)
        {
            // ���䗦�ifactor�j��1.0D
            factor = 1.0D;
        }
        else
        {
            // ���䗦�ifactor�j�������̌��ϐ��� �� �����̌����c��
            // �����Z���ʂ̊ۂ߂͍s��Ȃ��B
            if (l_dblBalance != 0.0D)
            {
                factor = l_dblClosingExecutedQuantity / l_dblBalance;
            }
        }

        // �Q�j�@@�P�j�ŋ��߂����䗦�ifactor�j��return����B
        log.exiting(STR_METHOD_NAME);
        return factor;

    }

    /**
      * �����\�Ȏ��Y���ǂ����̔���B<BR>
      * true ��Ԃ��B<BR>
      *
      * @@param l_exec ���
      * @@return true
      */
    public boolean isAssetOrderExecution(EqTypeOrderExecution l_exec)
    {
        return true;
    }

    /**
     * (process�V�K���������)<BR>
     * (processNewMarginOrderExecution�̃I�[�o�[���C�h)<BR>
     * �m�����T�v�n<BR>                                                          
     * �V�K��������菈�����s���B<BR>    
     * �V�[�P���X�}<BR>
     * �u�i�c���j�V�K���������v�Q��                                     
     * @@param l_eqtypeOrderExecution (���)
     * @@return void
     */
    protected void processNewMarginOrderExecution(EqTypeOrderExecution l_eqtypeOrderExecution)
        throws DataException, RuntimeSystemException
    {
		final String STR_METHOD_NAME = "processNewMarginOrderExecution(EqTypeOrderExecution)";
		log.entering(STR_METHOD_NAME);
		
        EqtypeFinTransactionParams tparams = new EqtypeFinTransactionParams();
        setMarketOrderedTransDefaultValues(tparams);
        setExecutionInfoToMarketOrderedTrans(tparams, l_eqtypeOrderExecution);
        EqtypeContractParams cparams = getContract(tparams);
        if (cparams == null)
        {
            cparams = new EqtypeContractParams();
            setContractDefaultValues(cparams);
            updateContractOpenFromMarketOrderedTrans(cparams, tparams);
            getPersistenceManager().saveNewContract(cparams);
        }
        else
        {
            updateContractOpenFromMarketOrderedTrans(cparams, tparams);
            getPersistenceManager().updateContractByTrans(cparams);
        }
        tparams.setContractId(cparams.getContractId());
        setMarginNetAmount(tparams);
        getPersistenceManager().saveNewFinTransaction(tparams);
        notifyGtl(tparams);
		log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �i�g���f�[�^�}�l�[�W���j�B
     */
    public static class WEB3EquityPersistentDataManager extends EqTypePositionManagerHelper.PersistentDataManager
    {

        /**
         * (get�ۗL���YParams)<BR>
         * ��肵�����Y�����łɌڋq���ۗL���Ă��邩�ǂ������m�F����ׁA<BR>
         * �ۗL���Y����������B<BR>
         * �iAssetParams getAsset(EqtypeFinTransactionParams tparams)�̃I�[�o�[���C�h�j<BR>
         * <BR>
         * �P�j�@@�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
         * �@@�@@�i* �����|�W�V�����}�l�[�W��.get�ۗL���Y(long,long,long.TaxTypeEnum)���R�[���j<BR>
         * <BR>
         * �@@�@@�@@-----------------------------------------------------<BR>
         * �@@�@@�@@��get�ۗL���Y( )�F�����ݒ�d�l��<BR>
         * <BR>
         * �@@�@@�@@����ID�F�@@�����̊����ڋq���薾��Params.����ID<BR>
         * �@@�@@�@@�⏕����ID�F�@@�����̊����ڋq���薾��Params.�⏕����ID<BR>
         * �@@�@@�@@����ID�F�@@�����̊����ڋq���薾��Params.����ID<BR>
         * �@@�@@�@@�ŋ敪�F<BR>
         * �@@�@@�@@�@@�@@�E�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S��<BR>
         * �@@�@@�@@�@@�@@�@@���������n�����iEQTYPE_SWAP_MARGIN�j�̏ꍇ�́A<BR>
         * �@@�@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�����P��ID�ɊY������<BR>
         * �@@�@@�@@�@@�@@�@@�����P�ʃI�u�W�F�N�g.�ŋ敪�i�������n�j<BR>
         * �@@�@@�@@�@@�@@�E�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S��<BR>
         * �@@�@@�@@�@@�@@�@@���������n�����iEQTYPE_SWAP_MARGIN�j�̏ꍇ�́A<BR>
         * �@@�@@�@@�@@�@@�@@�����̊����ڋq���薾��Params.�ŋ敪<BR>
         * �@@�@@�@@-----------------------------------------------------<BR>
         * <BR>
         * �Q�j�@@�擾�����ۗL���Y�I�u�W�F�N�g�ۗ̕L���YParams��ԋp����B<BR>
         * �@@�@@���Y���f�[�^�Ȃ�����null��ԋp����B<BR>
         * <BR>
         * @@param l_trans�@@�����ڋq���薾��Params
         * @@return �ۗL���YParams
         * @@throws DataQueryException, DataNetworkException
         * @@roseuid 4143F64401BB
         */
        public AssetParams getAsset(EqtypeFinTransactionParams l_trans) throws DataQueryException, DataNetworkException
        {
            final String STR_METHOD_NAME = "getAssetParams";
            log.entering(STR_METHOD_NAME);

            try
            {
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

                WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                TaxTypeEnum l_taxType;
                //�ۗL���Y�I�u�W�F�N�g���擾����B
                //�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S�����������n�����iEQTYPE_SWAP_MARGIN�j�̏ꍇ�́A
                if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_trans.getFinTransactionCateg()))
                {
                    Order order = l_orderManager.getOrder(l_trans.getOrderId());
                    OrderUnit orderUnits[] = order.getOrderUnits();
                    EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)orderUnits[0].getDataSourceObject();
                    l_taxType = l_orderUnitRow.getSwapTaxType();
                }
                //�E�����̊����ڋq���薾��Params.�g�����U�N�V�����J�e�S�����������n�����iEQTYPE_SWAP_MARGIN�j�̏ꍇ��
                else
                {
                    l_taxType = l_trans.getTaxType();
                }

                WEB3EquityAsset l_asset = (WEB3EquityAsset) l_positionManager.getAsset(l_trans.getAccountId(), l_trans.getSubAccountId(), l_trans.getProductId(), l_taxType);
                //�ۗL���YParams
                if (l_asset != null)
                {
                    log.exiting(STR_METHOD_NAME);
                    AssetParams l_assetParams = new AssetParams((AssetRow) l_asset.getDataSourceObject());
                    return l_assetParams;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
            }

            catch (NotFoundException l_dqe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_wbe);
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_wbe.getMessage(), l_wbe);
            }
        }

        /**
         * (get�����ڋq���薾��Params���X�g)<BR>
         * �m�����T�v�n<BR>
         * ������v�Z�ΏۂƂȂ銔���ڋq���薾��Params�̃��X�g���擾����B<BR>
         * <BR>
         * �P�j�����P��ID�A���YID�������Ɋ����g�����U�N�V�����f�[�^�e�[�u������������B<BR>
         * �@@�@@������������<BR>
         * �@@�@@�@@�@@�����P��ID<BR>
         * �@@�@@�@@�@@���YID<BR>
         * �@@�@@�@@�@@�폜�t���O<BR>
         * <BR>
         * �Q�j�ϒ��z��I�u�W�F�N�g�Ɍ������ꂽ�����ڋq���薾��Params���ꌏ�Âz��ɒǉ�����B<BR>
         * <BR>
         * �R�j�ϒ��z��I�u�W�F�N�g��ԋp����B
         * @@param l_lngOrderUnitId �����P��ID
         * @@param l_lngAssetId ���YID
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 4143F6440306
         */
        public List getEqtypeFinTransactionParams(long l_lngOrderUnitId, long l_lngAssetId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getEqtypeFinTransactionParams";
            log.entering(STR_METHOD_NAME);
            try
            {

                String l_strWhere = "order_unit_id = ? and asset_id = ? and delete_flag= ? ";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                Object[] l_objWhereValues = { new Long(l_lngOrderUnitId), new Long(l_lngAssetId), new Long(0)};
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValues);
                int l_intRowSize = l_lisRows.size();
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }
                log.exiting(STR_METHOD_NAME);
                return l_lstParams;
            }
            catch (DataNetworkException l_dnwe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }

        /**
         * (set�X�V��������)<BR>
         * <BR>
         * ����Params�̍X�V�Ώۂ̃v���p�e�B�ɁA�X�V�l���Z�b�g����B<BR>
         * �ivoid setUpdateContractAttributes(EqtypeContractParams cparams, Map vals)��<BR>
         * �@@�I�[�o�[���C�h�j<BR>
         * <BR>
         * �y�����e�[�u���z�̍X�V�Ώۂ̍��ږ��ɁA����Params�̓����v���p�e�B���Z�b�g����B<BR>
         * ex.) �u���������v�ɁA�����̌���Params�̓����v���p�e�B���Z�b�g����B<BR>
         * �@@�@@�@@���@@vals.put("original_quantity", new Double(cparams.getOriginalQuantity()));<BR>
         * <BR>
         * --------------------------------------------------<BR>
         * ���X�V�Ώۍ��ځ�<BR>
         * <BR>
         * ��������<BR>
         * ������<BR>
         * �����P��<BR>
         * �����萔��<BR>
         * ���萔��<BR>
         * �����萔�������<BR>
         * ���萔�������<BR>
         * ���`������<BR>
         * ���`�����������<BR>
         * �Ǘ���<BR>
         * �Ǘ�������<BR>
         * ������<BR>
         * �����������<BR>
         * �t����<BR>
         * �t���������<BR>
         * �݊���<BR>
         * ���̑�<BR>
         * �X�V���t<BR>
         * --------------------------------------------------<BR>
         * <BR>
         * @@param l_contractParams (����Params)<BR>
         * �@@�@@�@@�����I�u�W�F�N�g�B
         * @@param l_updateMap (�X�V�lMap)<BR>
         * �@@�@@�@@�����I�u�W�F�N�g�̃v���p�e�B�ɐݒ肷��l�B
         * @@roseuid 40CFDB3D003E
         */
        public void setUpdateContractAttributes(
            EqtypeContractParams l_contractParams,
            Map l_updateMap)
        {
			final String STR_METHOD_NAME = "setUpdateContractAttributes";
			log.entering(STR_METHOD_NAME);
			
            // ��������
            l_updateMap.put("original_quantity", new Double(l_contractParams.getOriginalQuantity()));
            // ������
            l_updateMap.put("quantity", new Double(l_contractParams.getQuantity()));
            // �����P��
            l_updateMap.put("original_contract_price", new Double(l_contractParams.getOriginalContractPrice()));
            // �����萔��
            l_updateMap.put("original_setup_fee", new Double(l_contractParams.getOriginalSetupFee()));
            // ���萔��
            l_updateMap.put("setup_fee", new Double(l_contractParams.getSetupFee()));
            // �����萔�������
            l_updateMap.put("original_setup_fee_tax", new Double(l_contractParams.getOriginalSetupFeeTax()));
            // ���萔�������
            l_updateMap.put("setup_fee_tax", new Double(l_contractParams.getSetupFeeTax()));
            // ���`������
            l_updateMap.put("name_transfer_fee", new Double(l_contractParams.getNameTransferFee()));
            // ���`�����������
            l_updateMap.put("name_transfer_fee_tax", new Double(l_contractParams.getNameTransferFeeTax()));
            // �Ǘ���
            l_updateMap.put("management_fee", new Double(l_contractParams.getManagementFee()));
            // �Ǘ�������
            l_updateMap.put("management_fee_tax", new Double(l_contractParams.getManagementFeeTax()));
            // ������
            l_updateMap.put("interest_fee", new Double(l_contractParams.getInterestFee()));
            // �����������
            l_updateMap.put("interest_fee_tax", new Double(l_contractParams.getInterestFeeTax()));
            // �t����
            l_updateMap.put("pay_interest_fee", new Double(l_contractParams.getPayInterestFee()));
            // �t���������
            l_updateMap.put("pay_interest_fee_tax", new Double(l_contractParams.getPayInterestFeeTax()));
            // �݊���
            l_updateMap.put("loan_equity_fee", new Double(l_contractParams.getLoanEquityFee()));
            // ���̑�
            l_updateMap.put("other", new Double(l_contractParams.getOther()));
            // �X�V���t
            l_updateMap.put("last_updated_timestamp", l_contractParams.getLastUpdatedTimestamp());
            
			log.exiting(STR_METHOD_NAME);
        }

        /**
         * (get����)<BR>
         * �y�����e�[�u���z����A<BR>
         * �����̒l���w�肵�Y�����錚��Params�I�u�W�F�N�g���擾���ԋp����B<BR>
         * �iEqtypeContractParams getContract<BR>
         * (long accountId, long subAccountId, long productId, <BR>
         * �@@long marketId, ContractTypeEnum type, double price, <BR>
         * �@@Date openDate, Date closeDate)�̃I�[�o�[���[�h�j<BR>
         * <BR>
         * �P�j�@@�����I�u�W�F�N�g�̈ȉ��̍��ڂ����������Ɏw�肵�A<BR>
         * �@@�@@�@@QueryProcessor���g�p���y�����e�[�u���z<BR>
         * ���猚��Params�I�u�W�F�N�g���擾����B<BR>
         * <BR>
         * �@@�@@������������<BR>
         * �@@�@@����ID = ����.����ID<BR>
         * �@@�@@���@@�⏕����ID = ����.�⏕����ID<BR>
         * �@@�@@���@@�s��ID = ����.�s��ID<BR>
         * �@@�@@���@@����ID = ����.����ID<BR>
         * �@@�@@���@@���敪 = ����.���敪<BR>
         * �@@�@@���@@���P�� = ����.���P��<BR>
         * �@@�@@���@@���� = ����.����<BR>
         * �@@�@@���@@���� = ����.����<BR>
         * �@@�@@���@@�ŋ敪 = ����.�ŋ敪<BR>
         * �@@�@@���@@�ٍϋ敪 = ����.�ٍϋ敪<BR>
         * �@@�@@���@@�ٍϊ����l = ����.�ٍϊ����l<BR>
         * �@@�@@���@@�������� = ����.��������<BR>
         * <BR>
         * �Q�j�@@�擾��������Params�I�u�W�F�N�g��ԋp����B<BR>
         * @@param l_lngAccountId ����ID�B
         * @@param l_lngSubAccountId �⏕����ID�B
         * @@param l_lngMarketId �s��ID�B
         * @@param l_lngProductId ����ID�B
         * @@param l_contractType ���敪�B
         * @@param l_dblContractPrice ���P���B
         * @@param l_datOpenDate �����B
         * @@param l_datCloseDate �����B
         * @@param l_taxType �ŋ敪�B
         * @@param l_strRepaymentType �ٍϋ敪�B<BR>
         * �@@�@@�@@�i1�F���x�M�p�@@2�F��ʐM�p�j
         * @@param l_dblRepaymentNum �ٍϊ����l�B
         * @@param l_datFirstOpenDate ���������B
         * @@return EqtypeContractParams
         * @@throws DataNetworkException, DataNetworkException, DataFindException, DataQueryException
         * @@roseuid 40D039DD00B1
         */
        public EqtypeContractParams getContract(
            long l_lngAccountId,
            long l_lngSubAccountId,
            long l_lngMarketId,
            long l_lngProductId,
            ContractTypeEnum l_contractType,
            double l_dblContractPrice,
            Date l_datOpenDate,
            Date l_datCloseDate,
            TaxTypeEnum l_taxType,
            String l_strRepaymentType,
            double l_dblRepaymentNum,
            Date l_datFirstOpenDate)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getContract";
            log.entering(STR_METHOD_NAME);
            try
            {
                //������������
                String l_strWhere =
                    "account_id = ? and sub_account_id = ? and market_id= ? and product_id = ? and contract_type = ? and contract_price = ? and to_char(open_date,'yyyyMMdd') = ? and to_char(close_date,'yyyyMMdd') = ? and tax_type = ? and repayment_type = ? and repayment_num = ? and to_char(first_open_date,'yyyyMMdd') = ?";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                Object[] l_objWhereValues =
                    {
                        new Long(l_lngAccountId),
                        new Long(l_lngSubAccountId),
                        new Long(l_lngMarketId),
                        new Long(l_lngProductId),
                        new Long(l_contractType.intValue()),
                        new Double(l_dblContractPrice),
                        WEB3DateUtility.formatDate(l_datOpenDate, "yyyyMMdd"),
                        WEB3DateUtility.formatDate(l_datCloseDate, "yyyyMMdd"),
                        new Long(l_taxType.intValue()),
                        new String(l_strRepaymentType),
                        new Double(l_dblRepaymentNum),
                        WEB3DateUtility.formatDate(l_datFirstOpenDate, "yyyyMMdd")};
                //�擾��������Params�I�u�W�F�N�g��ԋp����B
                log.exiting(STR_METHOD_NAME);
                l_lisRows = l_qp.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, l_objWhereValues);

                if (l_lisRows.size() == 0)
                {
                    return null;
                }

                EqtypeContractParams l_eqtypeContractParams = new EqtypeContractParams((EqtypeContractRow) l_lisRows.get(0));
                return l_eqtypeContractParams;
            }
            catch (DataNetworkException l_dnwe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }

        }

        /**
         * (get�����ڋq���薾��ListBy�����P��)<BR>
         * �w�肳�ꂽ�����f�[�^�ɑ΂���A<BR>
         * ������v�Z�ΏۂƂȂ銔���ڋq���薾��Params��List���擾����B<BR>
         * <BR>
         * �P�j�@@�����P��ID�������ɁA�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z����������B<BR>
         * �@@�@@-------------------------------<BR>
         * �@@�@@������������<BR>
         * �@@�@@�@@�@@�@@�����P��ID������.�����P��ID<BR>
         * �@@�@@���@@�폜�t���O��FALSE<BR>
         * �@@�@@-------------------------------<BR>
         * <BR>
         * �Q�j�@@�ϒ��z��I�u�W�F�N�g�ɁA�������ꂽ�����ڋq���薾��Params��<BR>
         * �@@�@@�@@�ꌏ���z��ɒǉ�����B<BR>
         * <BR>
         * �R�j�@@�ϒ��z��I�u�W�F�N�g��ԋp����B
         * @@param l_lngOrderUnitId �����P��ID�B
         * @@return List
         * @@throws DataNetworkException, DataQueryException
         * @@roseuid 40D23DA6038C
         */
        public List getFinTransactionListByOrderUnit(long l_lngOrderUnitId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionListByOrderUnit";
            log.entering(STR_METHOD_NAME);
            try
            {
                //    �����P��ID�������ɁA�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z����������B     
                String l_strWhere = "order_unit_id = ? and delete_flag= ? ";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;

                //�����������������P��ID������.�����P��ID���@@�폜�t���O��FALSE
                Object[] l_objWhereValues = { new Long(l_lngOrderUnitId), BooleanEnum.FALSE};

                //�ϒ��z��I�u�W�F�N�g�ɁA�������ꂽ�����ڋq���薾��Params���ꌏ���z��ɒǉ�����B
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValues);

                int l_intRowSize = 0;
                if (!l_lisRows.isEmpty())
                {
                    l_intRowSize = l_lisRows.size();
                }
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }
                log.exiting(STR_METHOD_NAME);

                //�R�j�@@�ϒ��z��I�u�W�F�N�g��ԋp����B
                return l_lstParams;

            }
            catch (DataNetworkException l_dnwe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }

        /**
         * �iget�����ڋq���薾��ListBy�����P��Plus�����j�B<BR>
         * <BR>
         * �w�肳�ꂽ�����f�[�^�{�����f�[�^�ɑ΂���A<BR>
         * ������v�Z�ΏۂƂȂ銔���ڋq���薾��Params��List���擾����B<BR>
         * <BR>
         * �P�j�@@�����P��ID�A����ID�������ɁA<BR>
         * �@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z����������B<BR>
         * �@@�@@-------------------------------<BR>
         * �@@�@@������������<BR>
         * <BR>
         * �@@�@@�@@�@@�@@�����P��ID������.�����P��ID<BR>
         * �@@�@@���@@����ID������.����ID<BR>
         * �@@�@@���@@�폜�t���O��FALSE<BR>
         * �@@�@@-------------------------------<BR>
         * <BR>
         * �Q�j�@@�ϒ��z��I�u�W�F�N�g�ɁA�������ꂽ�����ڋq���薾��Params���A<BR>
         * �@@�@@�ꌏ���z��ɒǉ�����B<BR>
         * <BR>
         * �R�j�@@�ϒ��z��I�u�W�F�N�g��ԋp����B
         * @@param l_lngOrderUnitId �����P��ID�B
         * @@param l_lngContractId ����ID�B
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 40D66B2501F0
         */
        public List getFinTransactionListByOrderUnitPlusContract(long l_lngOrderUnitId, long l_lngContractId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionListByOrderUnitPlusContract";
            log.entering(STR_METHOD_NAME);
            try
            {
                //�P�j�@@�����P��ID�A����ID�������ɁA�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z����������B
                String l_strWhere = "order_unit_id = ? and contract_id = ? and delete_flag = ?";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                Object[] l_objWhereValues = { new Long(l_lngOrderUnitId), new Long(l_lngContractId), new Long(0)};

                //�ϒ��z��I�u�W�F�N�g�ɁA�������ꂽ�����ڋq���薾��Params���A�ꌏ���z��ɒǉ�����B
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValues);

                int l_intRowSize = 0;
                if (!l_lisRows.isEmpty())
                {
                    l_intRowSize = l_lisRows.size();
                }
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }

                log.exiting(STR_METHOD_NAME);

                //�R�j�@@�ϒ��z��I�u�W�F�N�g��ԋp����B
                return l_lstParams;
            }
            catch (DataNetworkException l_dnwe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }

        /**
         * �iget����ListBy�����P�ʁj�B<BR>
         * <BR>
         * �w�肳�ꂽ�����f�[�^�ɑ΂���A�����f�[�^��S�Ď擾���A<BR>
         * ����Params��List���쐬���ĕԂ��B<BR>
         * <BR>
         * �P�j�@@�����̒����P��ID�ɕR�t���A�����ڋq���薾��Params��List���擾����B<BR>
         * <BR>
         *�@@�@@�@@ �N�G���v���Z�b�T�ɂ��A�����̒����P��ID���������ڋq���薾��Params��<BR>
         *�@@�@@�@@ �S�Ď擾����B�i�폜�t���O==TRUE�̃f�[�^���擾�ΏۂƂ���j<BR>
         * <BR>
         * <BR>
         * �Q�j�@@�擾���������ڋq���薾��Params��List����A����ID��List���쐬����B<BR>
         * �@@�@@�@@����ID��List�ɓ�������ID���d�����Ċ܂܂��ꍇ�́A�d������List���珜���B<BR>
         * <BR>
         * �R�j�@@�ȉ��A�Q�j�ō쐬��������ID��List�����A�ȉ��̏������s���A<BR>
         * �@@�@@�@@�w�肳�ꂽ�����f�[�^�ɑ΂��錚��Params�I�u�W�F�N�g��List���쐬����B<BR>
         * <BR>
         * �R�|�P�j�@@�����ڋq���薾��Params.����ID�ɊY�����錚��Params�I�u�W�F�N�g���擾����B<BR>
         * <BR>
         * �@@�@@�@@�g���f�[�^�}�l�[�W��.getContract(�����ڋq���薾��Params.����ID)�R�[���ɂ��<BR>
         * �@@�@@�@@�擾����B<BR>
         * <BR>
         * �R�|�Q�j�@@�擾��������Params�I�u�W�F�N�g���A�߂�lList�ɒǉ�����B<BR>
         * <BR>
         * �S�j�@@�쐬��������Params��List��Ԃ��B
         * @@param l_lngOrderUnitId �����P��Id�B
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 40E3802D0164
         */
        public List getContractListByOrderUnit(long l_lngOrderUnitId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getContractListByOrderUnit";
            log.entering(STR_METHOD_NAME);
            try
            {
                //�P�j�@@�����̒����P��ID�ɕR�t���A�����ڋq���薾��Params��List���擾����B
                //      �N�G���v���Z�b�T�ɂ��A�����̒����P��ID���������ڋq���薾��Params��
                //      �S�Ď擾����B�i�폜�t���O==TRUE�̃f�[�^���擾�ΏۂƂ���j
                String l_strWhere = " order_unit_id = ? ";
                Object[] l_objWhereValues = { new Long(l_lngOrderUnitId)};
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lstParams =
                    l_queryProcessor.doFindAllQuery(EqtypeFinTransactionRow.TYPE,
                        l_strWhere,
                        l_objWhereValues);
                 
                int l_intLength = 0;
                if (l_lstParams != null)
                {
                    l_intLength = l_lstParams.size();
                }
                List l_lstContract = new ArrayList();
                Map l_hashContractId = new Hashtable();

                //�Q�j�@@�擾���������ڋq���薾��Params��List����A����ID��List���쐬����B����ID��List�ɓ�������ID���d�����Ċ܂܂��ꍇ�́A�d������List���珜
                for (int i = 0; i < l_intLength; i++)
                {
                    EqtypeFinTransactionParams l_finTransactionParams = (EqtypeFinTransactionParams) l_lstParams.get(i);
                    long l_lngContractId = l_finTransactionParams.getContractId();
                    if (!l_hashContractId.containsKey(Long.toString(l_lngContractId)))
                    {
                        l_hashContractId.put(Long.toString(l_lngContractId), "1");

                        //�R�j�@@�ȉ��A�Q�j�ō쐬��������ID��List�����A�ȉ��̏������s���A�w�肳�ꂽ�����f�[�^�ɑ΂��錚��Params�I�u�W�F�N�g��List���쐬����B
                        EqtypeContractParams l_eqtypeContractParams = this.getContract(l_lngContractId);

                        //�R�|�Q�j�@@�擾��������Params�I�u�W�F�N�g���A�߂�lList�ɒǉ�����B
                        l_lstContract.add(l_eqtypeContractParams);
                    }
                }
                log.exiting(STR_METHOD_NAME);
                //�S�j�@@�쐬��������Params��List��Ԃ��B
                return l_lstContract;
            }
            catch (DataException l_dex)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dex);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dex.getMessage(), l_dex);
            }

        }

        /**
         * (get�����ڋq���薾��ListOf���ϒ���By����)<BR>
         * �w�肳�ꂽ�����f�[�^�ɑ΂���A<BR>
         * ���ϒ����̖�莞�ɍ쐬���ꂽ�����ڋq���薾��Params��List���擾����B<BR>
         * <BR>
         * �P�j�@@����ID���������ɁA���ϒ�����莞�ɍ쐬���ꂽ<BR>
         * �@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z����������B<BR>
         * �@@�@@-------------------------------<BR>
         * �@@�@@������������<BR>
         * <BR>
         * �@@�@@�@@�@@�@@����ID������.����ID<BR>
         * �@@�@@���@@�g�����U�N�V�����J�e�S�����i"�ԍώ��"�iEQTYPE_CLOSE_MARGIN�j<BR>
         * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@or "�������n���"�i<BR>
         * EQTYPE_SWAP_MARGIN�j�j<BR>
         * �@@�@@���@@�폜�t���O��FALSE<BR>
         * <BR>
         * �@@�@@���g�����U�N�V�������������ifin_transaction_timestamp�j�����Ŏ擾����B<BR>
         * �@@�@@-------------------------------<BR>
         * <BR>
         * �Q�j�@@�ϒ��z��I�u�W�F�N�g�ɁA�������ꂽ�����ڋq���薾��Params���A<BR>
         * �@@�@@�ꌏ���z��ɒǉ�����B<BR>
         * <BR>
         * �R�j�@@�ϒ��z��I�u�W�F�N�g��ԋp����B<BR>
         * @@param l_lngContractId - ����ID�B
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 40FC6955037E
         */
        public List getFinTransactionListOfCloseOrderByContract(long l_lngContractId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionListOfCloseOrderByContract";
            log.entering(STR_METHOD_NAME);
            try
            {
                // �P�j�@@����ID���������ɁA���ϒ�����莞�ɍ쐬���ꂽ�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z����������B       
                String l_strWhere = "contract_id = ? and (fin_transaction_categ = ? or fin_transaction_categ = ?) and delete_flag = ? ";
                String l_strOrderBy = "fin_transaction_timestamp";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                Object[] l_objWhereValues =
                    { new Long(l_lngContractId), new Long(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.intValue()), new Long(FinTransactionCateg.EQTYPE_SWAP_MARGIN.intValue()), new Long(0)};

                // �Q�j�@@�ϒ��z��I�u�W�F�N�g�ɁA�������ꂽ�����ڋq���薾��Params���A�ꌏ���z��ɒǉ�����B
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);
                //l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_objWhereValues);
                int l_intRowSize = l_lisRows.size();
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }

                log.exiting(STR_METHOD_NAME);

                //�R�j�@@�ϒ��z��I�u�W�F�N�g��ԋp����B
                return l_lstParams;

            }
            catch (DataNetworkException l_dnwe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }

        /**
         * (get�����ڋq���薾��ListOf�V�K������By����)<BR>
         * �w�肳�ꂽ�����f�[�^�ɑ΂���A<BR>
         * �V�K�������̖�莞�ɍ쐬���ꂽ�����ڋq���薾��Params��List���擾����B<BR>
         * <BR>
         * �P�j�@@����ID���������ɁA�V�K��������莞�ɍ쐬���ꂽ<BR>
         * �@@�@@�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z����������B<BR>
         * �@@�@@-------------------------------<BR>
         * �@@�@@������������<BR>
         * <BR>
         * �@@�@@�@@�@@�@@����ID������.����ID<BR>
         * �@@�@@���@@�g�����U�N�V�����J�e�S�����i"�V�K�����"�iEQTYPE_OPEN_MARGIN�j�j<BR>
         * �@@�@@���@@�폜�t���O��FALSE<BR>
         * <BR>
         * �@@�@@���g�����U�N�V�������������ifin_transaction_timestamp�j�����Ŏ擾����B<BR>
         * �@@�@@-------------------------------<BR>
         * <BR>
         * �Q�j�@@�ϒ��z��I�u�W�F�N�g�ɁA�������ꂽ�����ڋq���薾��Params���A<BR>
         * �@@�@@�ꌏ���z��ɒǉ�����B<BR>
         * <BR>
         * �R�j�@@�ϒ��z��I�u�W�F�N�g��ԋp����B<BR>
         * @@param l_lngContractId - ����ID�B
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 410449CB034D
         */
        public List getFinTransactionListOfOpenOrderByContract(long l_lngContractId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionListOfOpenOrderByContract";
            log.entering(STR_METHOD_NAME);
            try
            {
                //�P�j�@@����ID���������ɁA�V�K��������莞�ɍ쐬���ꂽ�y�g�����U�N�V�����e�[�u���i�����ڋq���薾�ׁj�z����������B  
                String l_strWhere = "contract_id = ? and fin_transaction_categ = ? and delete_flag= ? ";
                String l_strOrderBy = "fin_transaction_timestamp";
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                List l_lisRows = null;
                // �Q�j�@@�ϒ��z��I�u�W�F�N�g�ɁA�������ꂽ�����ڋq���薾��Params���A�ꌏ���z��ɒǉ�����B
                Object[] l_objWhereValues = { new Long(l_lngContractId), new Long(FinTransactionCateg.EQTYPE_OPEN_MARGIN.intValue()), BooleanEnum.FALSE};
                l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);
                int l_intRowSize = 0;
                if (l_lisRows != null)
                {
                    l_intRowSize = l_lisRows.size();
                }
                List l_lstParams = new ArrayList();
                for (int i = 0; i < l_intRowSize; i++)
                {
                    EqtypeFinTransactionRow l_finTransactionRow = (EqtypeFinTransactionRow) l_lisRows.get(i);
                    EqtypeFinTransactionParams l_params = new EqtypeFinTransactionParams(l_finTransactionRow);
                    l_lstParams.add(l_params);
                }
                log.exiting(STR_METHOD_NAME);
                //�R�j�@@�ϒ��z��I�u�W�F�N�g��ԋp����B
                return l_lstParams;

            }
            catch (DataNetworkException l_dnwe)
            {
                log.exiting(STR_METHOD_NAME);
                log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
            }
        }
        
        /**
         * (set�X�V�ۗL���Y����)<BR>
         * <BR>
         * �ۗL���YParams�̍X�V�Ώۂ̃v���p�e�B�ɁA�X�V�l���Z�b�g����B<BR>
         * �ivoid setUpdateAssetAttributes(AssetParams asset, Map vals)�̃I�[�o�[���C�h�j<BR>
         * <BR>
         * �y�ۗL���Y�e�[�u���z�̍X�V�Ώۂ̍��ږ��ɁA�ۗL���YParams�̓����v���p�e�B���Z�b�g����B<BR>
         * <BR>
         * �P�j�@@super.setUpdateAssetAttributes(�����ۗ̕L���YParams, �����̍X�V�lMap)���R�[�����A<BR>
         * �@@�@@�@@xTrade�W���v���p�e�B���Z�b�g����B<BR>
         * <BR>
         * �Q�j�@@�g���v���p�e�B���Z�b�g����B<BR>
         * <BR>
         * �@@�@@�@@--------------------------------------------------<BR>
         * �@@�@@�@@���Z�b�g�Ώۍ��ځ�<BR>
         * <BR>
         * �@@�@@�@@���t�s�\����<BR>
         * �@@�@@�@@���ʁi�뉿�P���v�Z�p�j<BR>
         * �@@�@@�@@--------------------------------------------------<BR>
         * <BR>
         * @@param l_aparams - �ۗL���YParams<BR>
         * �ۗL���YParams�B
         * @@param l_value - �X�V�lMap<BR>
         * �X�V�lMap�B
         */
        public void setUpdateAssetAttributes(AssetParams l_aparams, Map l_values)
        {
			final String STR_METHOD_NAME = "setUpdateAssetAttributes";
			log.entering(STR_METHOD_NAME);
			
            super.setUpdateAssetAttributes(l_aparams, l_values);
            l_values.put("quantity_cannot_sell",
                new Double(l_aparams.getQuantityCannotSell()));
            l_values.put("quantity_for_book_value",
                new Double(l_aparams.getQuantityForBookValue()));
                
			log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * �iset�ۗL���Y�f�t�H���g�l�j<BR>
     * <BR>
     * �����ۗ̕L���YParams�̃v���p�e�B�ɁA�f�t�H���g�l���Z�b�g����B<BR>
     * �ivoid setAssetDefaultValues(AssetParams aparams)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����ۗ̕L���YParams�̃v���p�e�B�ɁA�ȉ��̒ʂ�ɒl���Z�b�g�F<BR>
     * ----------------------------------<BR>
     * ���ʁF�@@0<BR>
     * ���ʁi�뉿�P���v�Z�p�j�F�@@0<BR>
     * �뉿�i�뉿�P���v�Z�p�j�F�@@0<BR>
     * ���t�s�\���ʁF�@@0<BR>
     * ���`�������F�@@0<BR>
     * ���`����������ŁF�@@0<BR>
     * �����Ǘ���F�@@0<BR>
     * �����Ǘ������ŁF�@@0<BR>
     * �~�j���敪�F�@@0�iDEFAULT�i�~�j���łȂ��j�j<BR>
     * ���z���F�@@0<BR>
     * 30�����o�ߎc�������F�@@0<BR>
     * �쐬���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * �X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * ----------------------------------<BR>
     * <BR>
     * @@param l_aparams - �ۗL���YParams
     */
    protected void setAssetDefaultValues(AssetParams l_aparams)
    {
        final String STR_METHOD_NAME = "setAssetDefaultValues(AssetParams)";
        log.entering(STR_METHOD_NAME);

        l_aparams.setQuantity(0.0D);
        l_aparams.setQuantityForBookValue(0.0D);
        l_aparams.setBookValue(0.0D);
        l_aparams.setQuantityCannotSell(0.0D);
        l_aparams.setSetupFee(0.0D);
        l_aparams.setSetupFeeTax(0.0D);
        l_aparams.setManagementFee(0.0D);
        l_aparams.setManagementFeeTax(0.0D);
        l_aparams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        l_aparams.setProfitDistribution(0.0D);
        l_aparams.setCountBeforePenalty(0.0D);
        l_aparams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_aparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (revert�����V�K��From�����ڋq���薾��)
     * <BR>
     * �����̊����ڋq���薾��Params���A�����������Params�ɔ��f����B<BR>
     * �ivoid revertContractOpenFromMarketOrderedTrans(EqtypeContractParams cparams,<BR>
     * �@@EqtypeFinTransactionParams tparams)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�����̎c���ʃ`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�@@�����c����(*1) �� 0.0D �̏ꍇ�A<BR>
     * �@@�@@�@@�u�����c���ʃ`�F�b�N�G���[�v�̗�O�i�Ɩ��G���[�j��throw����B<BR>
     * �@@�@@�@@class: WEB3BaseRuntimeException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_01934<BR>
     * <BR>
     * �@@�@@�@@(*1)�����c����<BR>
     * �@@�@@�@@�@@�@@�����̌���Params.������ �| �����̊����ڋq���薾��Params.��萔��<BR>
     * <BR>
     * �Q�j�@@�����̌���Params�̃v���p�e�B���X�V����B<BR>
     * <BR>
     * �@@�@@�@@���������F�@@�����̌���Params.�������� �| �����̊����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�������F�@@�����̌���Params.������ �| �����̊����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * @@param l_cparams - (����Params)<BR>
     * ������Ώۂ́A����Params�B<BR>
     * @@param l_tparams - (�����ڋq���薾��Params)<BR>
     * �����ڋq���薾��Params�B<BR>
     * @@throws RuntimeSystemException
     */
    protected void revertContractOpenFromMarketOrderedTrans(
        EqtypeContractParams l_cparams,
        EqtypeFinTransactionParams l_tparams)
        throws RuntimeSystemException
    {
        final String STR_METHOD_NAME =
            "revertContractOpenFromMarketOrderedTrans(EqtypeContractParams, EqtypeFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�����̎c���ʃ`�F�b�N���s���B
        double l_dblQuantity = l_cparams.getQuantity() - l_tparams.getQuantity();
        if (l_dblQuantity < 0.0D)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01934,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �Q�j�@@�����̌���Params�̃v���p�e�B���X�V����B
        l_cparams.setOriginalQuantity(l_cparams.getOriginalQuantity() - l_tparams.getQuantity());
        l_cparams.setQuantity(l_dblQuantity);
        l_cparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (reverse�ۗL���Y�|�W�V����ByTrans(�����ڋq���薾��Params)<BR>
     * <BR>
     * �����̊����ڋq���薾��Params���A�������ۗL���Y�ɔ��f����B<BR>
     * �iprotected void reverseAssetPositionByTrans(EqtypeFinTransactionParams trans,<BR>
     * �@@SideEnum side)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�Ώۂۗ̕L���Y���擾����B<BR>
     * <BR>
     * �@@�@@�@@�g���f�[�^�}�l�[�W��.getAsset(�����ڋq���薾��Params.���YID)�ɂ�<BR>
     * �@@�@@�@@�擾����B<BR>
     * <BR>
     * �Q�j�@@�����iSideEnum.BUY�j(*1)�̖�����̏ꍇ�́A�ۗL���Y�c���ʃ`�F�b�N���s���B<BR>
     * �@@�@@�@@�@@�@@-------------------------------------------------------------------<BR>
     * �@@�@@�@@�@@�@@�ۗL���Y�c����(*) �� 0.0D �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ۗL���Y�c���ʃ`�F�b�N�G���[�v�̗�O�i�Ɩ��G���[�j��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*)�ۗL���Y�c����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ۗL���YParams.���� �| �����ڋq���薾��Params.��萔��<BR>
     * �@@�@@�@@�@@�@@-------------------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@(*1)�����^�����̔���<BR>
     * �@@�@@�@@�����̔�����"��"�̏ꍇ�́A�����B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A�����B<BR>
     * <BR>
     * �R�j�@@super.reverseAssetPositionByTrans( )���R�[������B<BR>
     * <BR>
     * @@param l_trans - (�����ڋq���薾��Params)<BR>
     * �����ڋq���薾��Params�B
     * @@param l_side - (����)<BR>
     * �����̕ʁB
     * @@throws DataException
     * @@throws RuntimeSystemException
     */
    protected void reverseAssetPositionByTrans(EqtypeFinTransactionParams l_trans, SideEnum l_side)
        throws DataException, RuntimeSystemException
    {
        final String STR_METHOD_NAME = "reverseAssetPositionByTrans(EqtypeContractParams, SideEnum)";
        log.entering(STR_METHOD_NAME);
        
        AssetParams l_asset = getPersistenceManager().getAsset(l_trans.getAssetId());
        if (l_side == SideEnum.BUY)
        {
            double l_dblQuantity = l_asset.getQuantity() - l_trans.getQuantity();
            if (l_dblQuantity < 0.0D)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        super.reverseAssetPositionByTrans(l_trans, l_side);
        log.exiting(STR_METHOD_NAME);
    }
}
@
