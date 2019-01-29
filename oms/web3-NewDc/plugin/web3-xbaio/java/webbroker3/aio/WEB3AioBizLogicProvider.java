head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���v�Z�T�[�r�X�N���X(WEB3AioBizLogicProvider)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���z (���u) �V�K�쐬
                   2004/12/09 �����(���u)�c�Ή�
                   2005/1/11 ���E (���u) �c�Ή�
*/

package webbroker3.aio;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (���o���v�Z�T�[�r�X)<BR>
 * ���o���v�Z�T�[�r�X�N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioBizLogicProvider extends WEB3GentradeBizLogicProvider 
    implements GlobalBizLogicProvider, AioBizLogicProvider
{    

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioBizLogicProvider.class);             
       
    /**
     * (calc�U�։\����)<BR>
     * �������Ƃ̐U�։\���ʂ��Z�o����B<BR>
     * <BR>
     * �P�j�������̎擾 <BR>
     * <BR>
     *   ������ԊǗ�.get������() <BR>
     * <BR>
     * �Q�j�ی�a��ۗL���ʁA��p�،��ۗL���ʂ��Z�o����B<BR>
     * <BR>
     *   ���ڍׂ́A�v�Z�����Q�ƁB<BR>  
     * <BR>
     * �R�j����.�a��敪�ɊY������ۗL���ʂ�ԋp����B<BR>
     * @@param l_accountID - (����ID)<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * @@param l_productID - (����ID)<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * @@param l_equityType - (�a��敪)<BR>
     * @@return double
     */
    public double calcTransPossibleAmount(
        long l_accountID, ProductTypeEnum l_productType, 
        long l_productID, TaxTypeEnum l_taxType, 
        String l_equityType)
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "calcTransPossibleAmount(" +
            "long l_accountID, ProductTypeEnum l_productType, " +
            "long l_productID, TaxTypeEnum l_taxType, String l_equityType)";
        log.entering(l_strMethodName);

        //���ڍׂ́A�v�Z�����Q��
        
        // (1)�@@������擾����B
        //   �g���[�f�B���O�V�X�e������A�Ɩ����t���擾����B
		TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
		Date l_datOrderBizDate = tradingSystem.getBizDate();
        String l_strOrderBizDate = WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
        log.debug("l_strOrderBizDate = " + l_strOrderBizDate);
        
        String l_strOrderBizDate1 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 1), "yyyyMMdd");
        String l_strOrderBizDate2 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 2), "yyyyMMdd");
        String l_strOrderBizDate3 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 3), "yyyyMMdd");
        String l_strOrderBizDate4 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 4), "yyyyMMdd");
        String l_strOrderBizDate5 = 
            WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_datOrderBizDate, 5), "yyyyMMdd");
        
        // (2)�@@��n�����ۗ̕L���ʂ̌v�Z���ʂ�ێ�����G���A�𐶐�����B
        //   �����iT+0�j����5�c�Ɠ���iT+5�j�܂ł̕ی�a��y�ё�p�،��̐��ʂ�ێ�����G���A�𐶐�����B
        // 
        //      ��n�ςݕی�a�萔��[T+0 �c T+5]
        final double[] l_dblSafeQuantities = {0, 0, 0, 0, 0, 0};

        //      ��n�ςݑ�p�،�����[T+0 �c T+5]
        final double[] l_dblCollateralQuantities = {0, 0, 0, 0, 0, 0};
        
        // Utility for updating Quantity 
        class UpdateQuantities
        {
            public void process(SubAccountTypeEnum l_subAccountType, 
                    int dayNumber, double quantity, boolean isAdd)
            {
                // �⏕�������a��������̏ꍇ ��n�ςݕی�a�萔��[T+dayNumber]���Z�[�g
                if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountType))
                {
                    if (isAdd)
                    {
                        l_dblSafeQuantities[dayNumber] = 
                            l_dblSafeQuantities[dayNumber] + quantity;
                    }
                    else
                    {
                        l_dblSafeQuantities[dayNumber] = 
                            l_dblSafeQuantities[dayNumber] - quantity;
                    }
                }
                // �⏕�������ۏ؋������̏ꍇ�@@��n�ςݑ�p�،�����[T+dayNumber]���Z�[�g
                else if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountType))
                {
                    if (isAdd)
                    {
                        l_dblCollateralQuantities[dayNumber] = 
                            l_dblCollateralQuantities[dayNumber] + quantity;
                    }
                    else
                    {
                        l_dblCollateralQuantities[dayNumber] = 
                            l_dblCollateralQuantities[dayNumber] - quantity;
                    }
                }
            }
        }
        UpdateQuantities l_updateQuantities = new UpdateQuantities();
        
        // (3)�@@�O�c�Ɠ����_�ۗ̕L���ʂ��Z�o����B
        // ����.����ID�ɊY������ڋq�ɂ��āA����.����ID�ɊY����������̎�n�ςݕۗL���ʂ�ی�a��y�ё�p�،���
        // �����ɂ��ĎZ�o����B
        // 
        // ��n���ʕۗL���Y�e�[�u������A�ȉ��̒��o�����Ń��R�[�h���擾���A�ȉ��̗v�̂ŏW�v����B
        // 
        //   [���o����]
        // 
        //   ����ID = ����.����ID
        //     ����ID = ����.����ID
        //     �ŋ敪 = ����.�ŋ敪
        //     �~�j���敪 = "0"(DEFAULT)
        //     ��n�� < (1)�Ŏ擾�������
        List l_lisDailyBeforeAssetRows = WEB3AioUtility.doFindAllQuery(
                DailyAssetRow.TYPE,
                "account_id = ? and product_id = ? and tax_type = ? and mini_stock_div = ? " +
                    "and to_char(delivery_date, 'YYYYMMDD') < ?",
                null,
                new Object[]{new Long(l_accountID), new Long(l_productID), 
                        l_taxType, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK, l_strOrderBizDate},
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "�O�c�Ɠ����_�̎�n���ʕۗL���Y�e�[�u�̃��R�[�h���擾���G���[");
        log.debug("l_lisDailyBeforeAssetRows.size() = " + l_lisDailyBeforeAssetRows.size());
        
        //   [�W�v���@@]
        // 
        // �P�j ��n���ʕۗL���Y�e�[�u���̊e�擾���R�[�h�ɂ��āA  
        for (int i = 0; i < l_lisDailyBeforeAssetRows.size(); i++)
        {
            DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyBeforeAssetRows.get(i);
            SubAccountTypeEnum l_subAccountType = 
                this.getSubAccountType(l_accountID, l_dailyAssetRow.getSubAccountId());
            log.debug("l_dailyAssetRow[" + i + "] = " + l_dailyAssetRow);
            log.debug("l_subAccountType[" + i + "] = " + l_subAccountType);
            
            // ��    ��n���ʕۗL���Y�e�[�u��.�⏕����ID�̕⏕�������a��������̏ꍇ
            // 
            //   ��n�ςݕی�a�萔��[T+0] ����n�ςݕی�a�萔��[T+0] + ��n���ʕۗL���Y�e�[�u��.����
            // 
            // ��    ��n���ʕۗL���Y�e�[�u��.�⏕����ID�̕⏕�������ۏ؋������̏ꍇ
            // 
            //   ��n�ςݑ�p�،�����[T+0] ����n�ςݑ�p�،�����[T+0] + ��n���ʕۗL���Y�e�[�u��.����
            l_updateQuantities.process(l_subAccountType, 0, l_dailyAssetRow.getQuantity(), true);
        }
        // �Q�j �ۗL����[T+0]�̒l��[T+5]�܂ł̊e�v�f�ɂ��Z�b�g����B
        l_dblSafeQuantities[1] = l_dblSafeQuantities[0];
        l_dblSafeQuantities[2] = l_dblSafeQuantities[0];
        l_dblSafeQuantities[3] = l_dblSafeQuantities[0];
        l_dblSafeQuantities[4] = l_dblSafeQuantities[0];
        l_dblSafeQuantities[5] = l_dblSafeQuantities[0];
        log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
        
        l_dblCollateralQuantities[1] = l_dblCollateralQuantities[0];
        l_dblCollateralQuantities[2] = l_dblCollateralQuantities[0];
        l_dblCollateralQuantities[3] = l_dblCollateralQuantities[0];
        l_dblCollateralQuantities[4] = l_dblCollateralQuantities[0];
        l_dblCollateralQuantities[5] = l_dblCollateralQuantities[0];
        log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
        log.debug("end ---------�O�c�Ɠ����_�ۗ̕L���ʂ��Z�o���� ----- ");
        // ��    �����Œ��ӂ��ׂ��_�́A��n���ʕۗL���Y�e�[�u���̕⏕�������a��������̃f�[�^�́A�a��敪���ی�a��̂���
        // ���w���Ă���킯�ł͂Ȃ��A�ی�a��{��p�̂��́i�܂�A�ۗL���Y�̑����j��\���Ă���Ƃ������Ƃł���B
        // 
        // (4) �����ȍ~�ۗ̕L���ʂ��擾���A���Z����B
        // ����.����ID�ɊY������ڋq�ɂ��āA����.����ID�ɊY����������̎�n�ςݕۗL���ʂ�ی�a��y�ё�p�،���
        // �����ɂ��ĎZ�o����B
        
        // ��n���ʕۗL���Y�e�[�u������A�ȉ��̒��o�����Ń��R�[�h���擾���A�ȉ��̗v�̂ŏW�v����B
        // 
        //   [���o����]
        // 
        //   ����ID = ����.����ID
        //     ����ID = ����.����ID
        //     �ŋ敪 = ����.�ŋ敪
		//     �~�j���敪 = "0"(DEFAULT)
        //     ��n�� �� (1)�Ŏ擾�������
        List l_lisDailyAfterAssetRows = WEB3AioUtility.doFindAllQuery(
                DailyAssetRow.TYPE,
				"account_id = ? and product_id = ? and tax_type = ? and mini_stock_div = ?" +
                    " and to_char(delivery_date, 'YYYYMMDD') >= ?",
                null,
                new Object[]{new Long(l_accountID), new Long(l_productID), l_taxType,
                	WEB3MiniStockDivDef.DEFAULT_MINI_STOCK, l_strOrderBizDate},
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "�����ȍ~�̎�n���ʕۗL���Y�e�[�u�̃��R�[�h���擾���G���[");
         
        //   [�W�v���@@]
        for (int i = 0; i < l_lisDailyAfterAssetRows.size(); i++)
        {
            DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyAfterAssetRows.get(i);
            log.debug("l_dailyAssetRow[" + i + "] = " + l_dailyAssetRow);
            SubAccountTypeEnum l_subAccountType = 
                this.getSubAccountType(l_accountID, l_dailyAssetRow.getSubAccountId());
            
            // ��n���ʕۗL���Y�e�[�u��.��n��(T+n)�ɂ��A
            String l_strAssetDeliveryDate = 
                WEB3DateUtility.formatDate(l_dailyAssetRow.getDeliveryDate(), "yyyyMMdd");
            
            // ��    ��n���ʕۗL���Y�e�[�u��.�⏕����ID�̕⏕�������a��������̏ꍇ
            // 
            //   ��n�ςݕی�a�萔��[T+x] ����n�ςݕی�a�萔��[T+x] + ��n���ʕۗL���Y�e�[�u��.����
            // 
            // ��    ��n���ʕۗL���Y�e�[�u��.�⏕����ID�̕⏕�������ۏ؋������̏ꍇ
            // 
            //   ��n�ςݑ�p�،�����[T+x] ����n�ςݑ�p�،�����[T+x] + ��n���ʕۗL���Y�e�[�u��.����

            // ��n�ςݕی�a�萔��[T+0]
            log.debug("l_strOrderBizDate = " + l_strOrderBizDate);
            log.debug("l_strOrderBizDate1 = " + l_strOrderBizDate1);
            log.debug("l_strOrderBizDate2 = " + l_strOrderBizDate2);
            log.debug("l_strOrderBizDate3 = " + l_strOrderBizDate3);
            log.debug("l_strOrderBizDate4 = " + l_strOrderBizDate4);
            log.debug("l_strOrderBizDate5 = " + l_strOrderBizDate5);
            if (l_strOrderBizDate.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 0, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 1, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 2, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 3, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }
            
            // ��n�ςݕی�a�萔��[T+1]
            if (l_strOrderBizDate1.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 1, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 2, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 3, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }

            // ��n�ςݕی�a�萔��[T+2]
            if (l_strOrderBizDate2.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 2, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 3, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }

            // ��n�ςݕی�a�萔��[T+3]
            if (l_strOrderBizDate3.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 3, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }

            // ��n�ςݕی�a�萔��[T+4]
            if (l_strOrderBizDate4.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 4, l_dailyAssetRow.getQuantity(), true);
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }

            // ��n�ςݕی�a�萔��[T+5]
            if (l_strOrderBizDate5.equals(l_strAssetDeliveryDate))
            {
                l_updateQuantities.process(l_subAccountType, 5, l_dailyAssetRow.getQuantity(), true);
            }
            
            // �� ���́A�z���index�B n = 0 .. 5�B
            // �� x�́A�z���index�B x = n .. 5�ŁA������5�܂ł̂��ׂĂ̗v�f�ɂ��ď�L�̏������s���B
        }
        
        log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
        log.debug("l_dblSafeQuantities[1] = " + l_dblSafeQuantities[1]);
        log.debug("l_dblSafeQuantities[2] = " + l_dblSafeQuantities[2]);
        log.debug("l_dblSafeQuantities[3] = " + l_dblSafeQuantities[3]);
        log.debug("l_dblSafeQuantities[4] = " + l_dblSafeQuantities[4]);
        log.debug("l_dblSafeQuantities[5] = " + l_dblSafeQuantities[5]);
        log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
        log.debug("l_dblCollateralQuantities[1] = " + l_dblCollateralQuantities[1]);
        log.debug("l_dblCollateralQuantities[2] = " + l_dblCollateralQuantities[2]);
        log.debug("l_dblCollateralQuantities[3] = " + l_dblCollateralQuantities[3]);
        log.debug("l_dblCollateralQuantities[4] = " + l_dblCollateralQuantities[4]);
        log.debug("l_dblCollateralQuantities[5] = " + l_dblCollateralQuantities[5]);
        log.debug("end ---------�����ȍ~�ۗ̕L���ʂ��擾���A���Z����---------");

        // (5)�@@�،��U�ւ̈ړ����ʂ��Z�o����B
        // ����.����ID�ɊY������ڋq�ɂ��āA����.����ID�ɊY����������̏،��U�ւ̈ړ����ʂ��Z�o����B
        // 
        // ���o���̒����P�ʃe�[�u������A�ȉ��̒��o�����Ń��R�[�h���擾���A�ȉ��̗v�̂ŏW�v����B
        // 
        //   [���o����]
        // 
        //   ����ID = ����.����ID
        //   �⏕����ID = ����.����ID����擾�����ۏ؋������̕⏕����ID
        //   �����^�C�v = ����.�����^�C�v
        //     ����ID = ����.����ID
        //     �ŋ敪 = ����.�ŋ敪
        //     �~�j���敪 = "0"(DEFAULT)
        //     ������� == 1009�i�،��U�֒����i�ی�a�肩���p�L���،��j�j or 1010�i�،��U�֒����i��p�L���،�����ی�a��j�j
        //     �����L����� = 1�i�I�[�v���j
        SubAccount l_marginSubAccount = 
            this.getSubAccountType(l_accountID, SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        log.debug("l_subAccount.getAccountId() = " + l_marginSubAccount.getAccountId());
        log.debug("l_subAccount.getSubAccountId() = " + l_marginSubAccount.getSubAccountId());
        
        List l_lisAioOrderUnitRows = WEB3AioUtility.doFindAllQuery(
                AioOrderUnitRow.TYPE,
					"account_id = ? and sub_account_id = ? and product_type = ? and " +
					"product_id = ? and tax_type = ? and mini_stock_div = ? and (order_type = ? or order_type = ?) and " +
                    "order_open_status = ?",
                null,
                new Object[]{
                    new Long(l_accountID), 
                    new Long(l_marginSubAccount.getSubAccountId()),
                    l_productType, 
                    new Long(l_productID), 
                    l_taxType,
					WEB3MiniStockDivDef.DEFAULT_MINI_STOCK,
					OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES,
					OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT,
                    OrderOpenStatusEnum.OPEN},
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "�،��U�֓��o���̒����P�ʃe�[�u���̃��R�[�h���擾���G���[");
        log.debug("l_lisAioOrderUnitRows.size() = " + l_lisAioOrderUnitRows.size());
        
        //   [�W�v���@@]
        for (int i = 0; i < l_lisAioOrderUnitRows.size(); i++)
        {
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(i);
            log.debug("l_aioOrderUnitRow[" + i + "] = " + l_aioOrderUnitRow);

            //   ��n�ςݑ�p�،�����[T+x] ����n�ςݑ�p�،�����[T+x] + �����P�ʃe�[�u��.����
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 0, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 1, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 2, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
			l_updateQuantities.process(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
            // �� x�́A�z���index�B x = 0 .. 5�ŁA0����5�܂ł̂��ׂĂ̗v�f�ɂ��ď�L�̏������s���B
        }
        log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
        log.debug("l_dblSafeQuantities[1] = " + l_dblSafeQuantities[1]);
        log.debug("l_dblSafeQuantities[2] = " + l_dblSafeQuantities[2]);
        log.debug("l_dblSafeQuantities[3] = " + l_dblSafeQuantities[3]);
        log.debug("l_dblSafeQuantities[4] = " + l_dblSafeQuantities[4]);
        log.debug("l_dblSafeQuantities[5] = " + l_dblSafeQuantities[5]);
        log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
        log.debug("l_dblCollateralQuantities[1] = " + l_dblCollateralQuantities[1]);
        log.debug("l_dblCollateralQuantities[2] = " + l_dblCollateralQuantities[2]);
        log.debug("l_dblCollateralQuantities[3] = " + l_dblCollateralQuantities[3]);
        log.debug("l_dblCollateralQuantities[4] = " + l_dblCollateralQuantities[4]);
        log.debug("l_dblCollateralQuantities[5] = " + l_dblCollateralQuantities[5]);
        log.debug("end ---------�،��U�ւ̈ړ�����---------");

		// (6) ��������U�ւ̈ړ����ʂ̎Z�o
		// ����.����ID�ɊY������ڋq�ɂ��āA����.����ID�ɊY����������̓�������U�ւ̈ړ����ʂ��Z�o����B
		// 
		// ���o���̒����P�ʃe�[�u������A�ȉ��̒��o�����Ń��R�[�h���擾���A�ȉ��̗v�̂ŏW�v����B
		// 
		//   [���o����]
		// 
		//   ����ID = ����.����ID
		//   �⏕����ID = ����.����ID����擾�����a����������̕⏕����ID
		//   �����^�C�v = ����.�����^�C�v
		//     ����ID = ����.����ID
		//     �ŋ敪 = ����.�ŋ敪
		//     �~�j���敪 = "0"(DEFAULT)
		//     ������� == 1015�i�،��U�֒����i������������ʌ����j�j or 1016�i�،��U�֒����i��ʌ��������������j�j
		//     �����L����� = 1�i�I�[�v���j
		SubAccount l_subAccount = 
			this.getSubAccountType(l_accountID, SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
		log.debug("l_subAccount.getAccountId() = " + l_subAccount.getAccountId());
		log.debug("l_subAccount.getSubAccountId() = " + l_subAccount.getSubAccountId());
        
		List l_lisSpTransOrderUnitRows = WEB3AioUtility.doFindAllQuery(
				AioOrderUnitRow.TYPE,
					"account_id = ? and sub_account_id = ? and product_type = ? and " +
					"product_id = ? and tax_type = ? and mini_stock_div = ? and (order_type = ? or order_type = ?) and " +
					"order_open_status = ?",
				null,
				new Object[]{
					new Long(l_accountID), 
					new Long(l_subAccount.getSubAccountId()),
					l_productType, 
					new Long(l_productID), 
					l_taxType,
					WEB3MiniStockDivDef.DEFAULT_MINI_STOCK,
					OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT,
					OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT,
					OrderOpenStatusEnum.OPEN},
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				"��������U�ւ̒����P�ʃe�[�u���̃��R�[�h���擾���G���[");
		log.debug("l_lisSpTransOrderUnitRows.size() = " + l_lisSpTransOrderUnitRows.size());
        
		//   [�W�v���@@]
		for (int i = 0; i < l_lisSpTransOrderUnitRows.size(); i++)
		{
			AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisSpTransOrderUnitRows.get(i);
			log.debug("l_aioOrderUnitRow[" + i + "] = " + l_aioOrderUnitRow);
			// �����P�ʃe�[�u��.��n��(T+n)�ɂ��A  
			String l_strAioOrderDeliveryDate = 
				WEB3DateUtility.formatDate(l_aioOrderUnitRow.getDeliveryDate(), "yyyyMMdd");
			log.debug("l_strAioOrderDeliveryDate = " + l_strAioOrderDeliveryDate);
			//   ��n�ςݕی�a�萔��[T+x] ����n�ςݕی�a�萔��[T+x] + �����P�ʃe�[�u��.����
            
			// ��n�ςݕی�a�萔��[T+0]
			if (l_strOrderBizDate.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 0, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 1, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 2, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}

			// ��n�ςݕی�a�萔��[T+1]
			if (l_strOrderBizDate1.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 1, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 2, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}
            
			// ��n�ςݕی�a�萔��[T+2]
			if (l_strOrderBizDate2.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 2, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}
            
			// ��n�ςݕی�a�萔��[T+3]
			if (l_strOrderBizDate3.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 3, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}

			// ��n�ςݕی�a�萔��[T+4]
			if (l_strOrderBizDate4.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 4, l_aioOrderUnitRow.getQuantity(), true);
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}

			// ��n�ςݕی�a�萔��[T+5]
			if (l_strOrderBizDate5.equals(l_strAioOrderDeliveryDate))
			{
				l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 5, l_aioOrderUnitRow.getQuantity(), true); 
			}
			// �� ���́A�z���index�B n = 0 .. 5�B
			// �� x�́A�z���index�B x = n .. 5�ŁA������5�܂ł̂��ׂĂ̗v�f�ɂ��ď�L�̏������s���B
		}
		log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
		log.debug("l_dblSafeQuantities[1] = " + l_dblSafeQuantities[1]);
		log.debug("l_dblSafeQuantities[2] = " + l_dblSafeQuantities[2]);
		log.debug("l_dblSafeQuantities[3] = " + l_dblSafeQuantities[3]);
		log.debug("l_dblSafeQuantities[4] = " + l_dblSafeQuantities[4]);
		log.debug("l_dblSafeQuantities[5] = " + l_dblSafeQuantities[5]);
		log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
		log.debug("l_dblCollateralQuantities[1] = " + l_dblCollateralQuantities[1]);
		log.debug("l_dblCollateralQuantities[2] = " + l_dblCollateralQuantities[2]);
		log.debug("l_dblCollateralQuantities[3] = " + l_dblCollateralQuantities[3]);
		log.debug("l_dblCollateralQuantities[4] = " + l_dblCollateralQuantities[4]);
		log.debug("l_dblCollateralQuantities[5] = " + l_dblCollateralQuantities[5]);
		log.debug("end ---------��������U�ւ̈ړ�����---------");

        // (7)�@@�Ώۖ����������̏ꍇ�A��������萔�ʂƌ��n���ʂ��Z�o����B
        // ���������P�ʃe�[�u������A�ȉ��̒��o�����Ń��R�[�h���擾���A�ȉ��̗v�̂ŏW�v����B
        // 
        //   [���o����]
        // 
        //   ������
        // ����ID = ����.����ID
        //   �����^�C�v = ����.�����^�C�v
        //     ����ID = ����.����ID
        //     ������ = ���ݓ��t
        //     �����敪 = 1�i�I�[�v���j
        //     ��萔�� != null
        // 
        // �������������̏ꍇ
        //   ������� = 2�i�����������j
        //   �ŋ敪 = ����.�ŋ敪
        // 
        //   �����n�����̏ꍇ
        //   ������� = 8�i���n�����j
        //   �ŋ敪�i�������n�j = ����.�ŋ敪
        log.debug("l_accountID = " + l_accountID);
        log.debug("l_productType = " + l_productType);
        log.debug("l_productID = " + l_productID);
        log.debug("l_strOrderBizDate = " + l_strOrderBizDate);
        log.debug("l_taxType = " + l_taxType);
        List l_lisEqtypeOrderUnitRows = WEB3AioUtility.doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                "account_id = ? and product_type = ? and product_id = ? " +
                    "and biz_date = ? and expiration_status = ? " +
                    "and executed_quantity is not null " +
//                    "and (executed_quantity is not null or executed_quantity != 0) " +
                    "and (order_type = ? or order_type = ?) " + "and tax_type = ?",
                null,
                new Object[]{
                    new Long(l_accountID), // ����ID = ����.����ID
                    l_productType,// �����^�C�v = ����.�����^�C�v
                    new Long(l_productID),//����ID = ����.����ID
                    l_strOrderBizDate,// ������ = ���ݓ��t
                    OrderExpirationStatusEnum.OPEN, // �����敪 = 1�i�I�[�v���j
                    OrderTypeEnum.EQUITY_SELL,//�����������̏ꍇ ������� = 2�i�����������j
                    OrderTypeEnum.SWAP_MARGIN_SHORT, //�����������̏ꍇ ������� = 8�i���n�����j
                    l_taxType},
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "���������P�ʃe�[�u���̒����P�ʃe�[�u���̃��R�[�h���擾���G���[");
        log.debug("l_lisEqtypeOrderUnitRows.size() =  " + l_lisEqtypeOrderUnitRows.size());
        //   [�W�v���@@]
        for (int i = 0; i < l_lisEqtypeOrderUnitRows.size(); i++)
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = 
                (EqtypeOrderUnitRow)l_lisEqtypeOrderUnitRows.get(i);
            log.debug("l_eqtypeOrderUnitRow[" + i + "] = " + l_eqtypeOrderUnitRow);
            //   �����P�ʃe�[�u��.��n��(T+n)�ɂ��A  
            String l_strEqtypeOrderDeliveryDate = 
                WEB3DateUtility.formatDate(l_eqtypeOrderUnitRow.getDeliveryDate(), "yyyyMMdd");

            // ��n�ςݕی�a�萔��[T+x] ����n�ςݕی�a�萔��[T+x] - �����P�ʃe�[�u��.��萔��

            // ��n�ςݕی�a�萔��[T+0]
            if (l_strOrderBizDate.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        0, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        1, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        2, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        3, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }

            // ��n�ςݕی�a�萔��[T+1]
            if (l_strOrderBizDate1.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        1, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        2, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        3, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }

            // ��n�ςݕی�a�萔��[T+2]
            if (l_strOrderBizDate2.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        2, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        3, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }
            
            // ��n�ςݕی�a�萔��[T+3]
            if (l_strOrderBizDate3.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        3, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }
            
            // ��n�ςݕی�a�萔��[T+4]
            if (l_strOrderBizDate4.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        4, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }
            
            // ��n�ςݕی�a�萔��[T+5]
            if (l_strOrderBizDate5.equals(l_strEqtypeOrderDeliveryDate))
            {
                l_updateQuantities.process(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT, 
                        5, 
                        l_eqtypeOrderUnitRow.getQuantity(), 
                        false);
            }
            // �� ���́A�z���index�B n = 0 .. 5�B
            // �� x�́A�z���index�B x = n .. 5�ŁA������5�܂ł̂��ׂĂ̗v�f�ɂ��ď�L�̏������s���B
        }
        log.debug("l_dblSafeQuantities[0] = " + l_dblSafeQuantities[0]);
        log.debug("l_dblSafeQuantities[1] = " + l_dblSafeQuantities[1]);
        log.debug("l_dblSafeQuantities[2] = " + l_dblSafeQuantities[2]);
        log.debug("l_dblSafeQuantities[3] = " + l_dblSafeQuantities[3]);
        log.debug("l_dblSafeQuantities[4] = " + l_dblSafeQuantities[4]);
        log.debug("l_dblSafeQuantities[5] = " + l_dblSafeQuantities[5]);
        log.debug("l_dblCollateralQuantities[0] = " + l_dblCollateralQuantities[0]);
        log.debug("l_dblCollateralQuantities[1] = " + l_dblCollateralQuantities[1]);
        log.debug("l_dblCollateralQuantities[2] = " + l_dblCollateralQuantities[2]);
        log.debug("l_dblCollateralQuantities[3] = " + l_dblCollateralQuantities[3]);
        log.debug("l_dblCollateralQuantities[4] = " + l_dblCollateralQuantities[4]);
        log.debug("l_dblCollateralQuantities[5] = " + l_dblCollateralQuantities[5]);
        log.debug("end ---------�Ώۖ����������̏ꍇ�A��������萔�ʂƌ��n���ʂ��Z�o����---------");
        
        // (8)�@@�Z�o������n�����̕ی�a��ۗL���ʂ̒��ŁA�ŏ��̐��ʂ�ی�a��ۗL���ʂƂ���B
        //    ���ی�a��ۗL���ʂ�������n�������݂����ꍇ�́A��p�،��ۗL���ʂ̑����ق���I������B
        
        // �ی�a��ۗL����
		double l_dblReturnSafeQuantity = l_dblSafeQuantities[0];
		log.debug("l_dblReturnSafeQuantity = " + l_dblReturnSafeQuantity);
        
        // ��p�،��ۗL����
        double l_dblReturnCollateralQuantity = l_dblCollateralQuantities[0];
        log.debug("l_dblReturnCollateralQuantity = " + l_dblReturnCollateralQuantity);
        for (int i = 1; i <=5; i++)
        {
            if (l_dblSafeQuantities[i] < l_dblReturnSafeQuantity)
            {
                l_dblReturnSafeQuantity = l_dblSafeQuantities[i];

                // (8) �m�肵���ی�a��ۗL���ʂ�����n���̑�p�،��ۗL���ʂ��擾���A���̐��ʂ��p�،��ۗL���ʂƂ���B
                l_dblReturnCollateralQuantity = l_dblCollateralQuantities[i];
            }
			// ���ی�a��ۗL���ʂ�������n�������݂����ꍇ�́A��p�،��ۗL���ʂ̑����ق���I������B
            else if (l_dblSafeQuantities[i] == l_dblReturnSafeQuantity &&
            			l_dblCollateralQuantities[i] > l_dblReturnCollateralQuantity)
            {
				l_dblReturnSafeQuantity = l_dblSafeQuantities[i];
				l_dblReturnCollateralQuantity = l_dblCollateralQuantities[i];
            }
        }
        
        // (9) �ŏI�I�ȕۗL���ʂ��m�肷��B
        // 
        // ��    �ی�a��ۗL���� �� 0 �̏ꍇ
        if (l_dblReturnSafeQuantity <= 0)
        {
            // �ی�a��ۗL���� �� 0
            l_dblReturnSafeQuantity = 0;

            // ��p�،��ۗL���� �� 0
            l_dblReturnCollateralQuantity = 0;
        }

        // ��    �ی�a��ۗL���� �� 0 �̏ꍇ
        // �E�ی�a��ۗL���� �� ��p�،��ۗL���� �̏ꍇ
        else if (l_dblReturnSafeQuantity <= l_dblReturnCollateralQuantity)
        {
            // ��p�،��ۗL���� �� �ی�a��ۗL����
            l_dblReturnCollateralQuantity = l_dblReturnSafeQuantity;
            
            // �ی�a��ۗL���� �� 0
            l_dblReturnSafeQuantity = 0;
            
        }
        // �E�ی�a��ۗL���� �� ��p�،��ۗL���� �̏ꍇ
        else
        {
            // �ی�a��ۗL���� �� �ی�a��ۗL���� �| ��p�،��ۗL���� 
            l_dblReturnSafeQuantity = l_dblReturnSafeQuantity - l_dblReturnCollateralQuantity;
            
            // ��p�،��ۗL���� �� ��p�،��ۗL����
        }
        log.debug("l_dblReturnSafeQuantity = " + l_dblReturnSafeQuantity);   
        log.debug("l_dblReturnCollateralQuantity = " + l_dblReturnCollateralQuantity);
        //10�j����.�a��敪�ɊY������ۗL���ʂ�ԋp����
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_equityType))
        {
            log.exiting(l_strMethodName);
            return l_dblReturnSafeQuantity;
        }
        else if (WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(l_equityType))
        {
            log.exiting(l_strMethodName);
            return l_dblReturnCollateralQuantity;
        }
        return 0;
    }

    /**
     * (calc�،��]���z)<BR>
     * �،��]���z���Z�o����B <BR>
     * <BR>
     * �P�j�����e�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     * [��������] <BR>
     * ����ID�F ����.����ID <BR>
     * <BR>
     * 2�j�]���z�v�Z <BR>
     * <BR>
     *   ���ڍׂ́A�v�Z�����Q�ƁB<BR> 
     * <BR>
     * 3�j�Z�o�����]���z��ԋp����B <BR>
     * <BR>
     * @@param l_productID - (����ID)
     * @@param l_equityType - (�a��敪)
     * @@param l_dblAmount - (����)
     * @@return double  
     */
    public double calcStockEvalueAmount(
        long l_productID, String l_equityType, double l_dblAmount)
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "calcStockEvalueAmount(long l_productID, String l_equityType, double l_Amount)";
        log.entering(l_strMethodName);
        
        //�P�j�����e�[�u�����烌�R�[�h���擾����
        //[��������] 
        //����ID�F����.����ID
        try
        {
            ProductRow l_productRow = 
                (ProductRow) ProductDao.findRowByProductId(l_productID);
            log.debug("l_productRow = " + l_productRow);
            if (l_productRow == null)
            {
                log.debug("�����e�[�u�����烌�R�[�h���擾���Ȃ�");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName);
            }
            
            double l_dblRatio = 0;   

            // ======remian zhou-yong begin ========

            l_dblRatio = l_productRow.getMarginRatio();              

            // ======remian zhou-yong end ========
            
            //�R�j�]���z�v�Z
            //���ڍׂ́A�v�Z�����Q��
            //�i�ۗL����(*1) �~ �]���P��(*2) �~ �|��(*3)�j�� �v�Z�P��(*4)
            //(*1)�@@�ۗL���ʁF �ی�a��̕]���z�Z�o���͕ی�a��ۗL���ʁA��p�،��̕]���z�Z�o���͑�p�،��ۗL����
            //(*2)�@@�]���P���F �����e�[�u��.�]���P��
            //(*3)�@@�|�ځF �����e�[�u��.��p�|��
			//(*4)�@@�v�Z�P�ʁF �����e�[�u��.�v�Z�P�ʁi���E���M�ȊO�́A1�Œ�j
			//�����̏ꍇ�́A�v�Z���ʂ�100�����������̂��ŏI�I�ȕ]���z�Ƃ���B
            
            // �v�Z�P�ʁF �����e�[�u��.�v�Z�P�ʁi���E���M�ȊO�́A1�Œ�j
            double l_dblCalcSize = 1;
            if (ProductTypeEnum.MUTUAL_FUND.equals(l_productRow.getProductType())
                || ProductTypeEnum.BOND.equals(l_productRow.getProductType()))
            {
                l_dblCalcSize = l_productRow.getCalcSize();
            }
            
            // �]���P���F �����e�[�u��.�]���P��
            double l_dblEstimationPrice = l_productRow.getEstimationPrice();
            log.debug("l_dblAmount = " + l_dblAmount);
            log.debug("l_dblEstimationPrice = " + l_dblEstimationPrice);
            log.debug("l_dblRatio = " + l_dblRatio);
            log.debug("(l_dblRatio / 100.0) = " + (l_dblRatio / 100.0));
			log.debug("l_dblCalcSize = " + l_dblCalcSize);
            // �]���z�v�Z
            // ����p�|�ڂ͒P�ʂ����Ȃ̂ŁA100�Ŋ���K�v������
            double l_dblMarketValue = 
            	(l_dblAmount * l_dblEstimationPrice * (l_dblRatio / 100.0)) / l_dblCalcSize;
            
            //���̏ꍇ�́A�����100��������B
            if (ProductTypeEnum.BOND.equals(l_productRow.getProductType()))
				l_dblMarketValue *= 100.0;

            log.exiting(l_strMethodName);
            
            //�S�j�Z�o�����]���z��ԋp����B
            // �������_�ȉ��͐؂�̂�
            return (long)l_dblMarketValue;
            
        }     
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }
    }
    
    private SubAccountTypeEnum getSubAccountType(long l_accountId, long l_subAccountId)
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "getSubAccountType(long l_accountId, long l_subAccountId)";
        log.entering(l_strMethodName);

        try
        {
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            
            SubAccount l_subAccount = 
                l_accountManager.getSubAccount(l_accountId, l_subAccountId);
            log.debug("l_subAccount.getAccountId() = " + l_subAccount.getAccountId());
            log.debug("l_subAccount.getSubAccountId() = " + l_subAccount.getSubAccountId());
            log.debug("l_subAccount.getSubAccountType() = " + l_subAccount.getSubAccountType());
            log.exiting(l_strMethodName);
            return l_subAccount.getSubAccountType();
        }
        catch (NotFoundException l_ex)
        {
            log.error("�⏕�������擾���G���[....", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
    }

    private SubAccount getSubAccountType(long l_accountId, SubAccountTypeEnum l_subAccountType)
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "getSubAccountType(long l_accountId, SubAccountTypeEnum l_subAccountType)";
        log.entering(l_strMethodName);
    
        try
        {
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            
            SubAccount l_subAccount = 
                l_accountManager.getSubAccount(l_accountId, l_subAccountType);
            log.exiting(l_strMethodName);
            return l_subAccount;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�⏕�������擾���G���[....", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider#checkTradingPower(com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec)
     */
    public OrderValidationResult checkTradingPower(SubAccount arg0, OrderSpec arg1)
    {
        return null;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbaio.AioBizLogicProvider#calcCommission(com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit)
     */
    public double calcCommission(AioOrderUnit arg0)
    {
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcCommission(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution)
     */
    public double calcCommission(OrderExecution arg0)
    {
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcCapitalGainTax(com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum, double)
     */
    public double calcCapitalGainTax(SubAccount arg0, TaxTypeEnum arg1, double arg2)
    {
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcExecutionAmount(com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, long, double, double, com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum)
     */
    public double calcExecutionAmount(ProductTypeEnum arg0, long arg1, double arg2, double arg3, QuantityTypeEnum arg4)
    {
        return 0;
    }    
}
@
