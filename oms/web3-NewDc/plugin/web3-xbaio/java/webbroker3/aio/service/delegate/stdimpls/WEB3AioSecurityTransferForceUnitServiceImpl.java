head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferForceUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֋���UnitServiceImpl(WEB3AioSecurityTransferForceUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 ���z (���u) �V�K�쐬
Revesion History : 2007/03/16 �����q (���u) ���f��No.715
Revesion History : 2007/03/28 �����q(���u)�@@�����̖��  No.010
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.WEB3AioSecurityTransferForceUpdateInterceptor;
import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioHostCommodityDef;
import webbroker3.common.define.WEB3AioTransNotifyTransferFlagDef;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�،��U�֋���UnitServiceImpl)<BR>
 * �،��U�֋���UnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * ���w�肷��B
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferForceUnitServiceImpl implements WEB3AioSecurityTransferForceUnitService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceUnitServiceImpl.class); 
    
    /**
     * (submit����)<BR>
     * �،��U�ւ̒�����o�^���A�z����擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��U�֋����jsubmit�����v �Q��
     * @@param l_params - ��p�U�֋����L���[�e�[�u���̍s�I�u�W�F�N�g
     * @@return AioOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    public AioOrderUnit[] submitOrder(HostMrgsecTransNotifyParams l_params) throws WEB3BaseException
    {
        String l_strMethodName = "submitOrder(HostMrgsecTransNotifyParams l_params)";
        log.entering(l_strMethodName);
        
        //1.1 �،���ЃI�u�W�F�N�g���擾����B 
        //InstitutionImpl(long)
        //[����] 
        //�،���ЃR�[�h�F ����.��p�U�֋����L���[Params.�،���ЃR�[�h 
        
        //InstitutionCode
        String l_strInstitutionCode = l_params.getInstitutionCode();
        
        //FinApp, AccountManager, Institution
        FinApp l_finApp = 
            (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();    
           
        Institution l_institution;
        try
        {
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2 �㗝���͎҃I�u�W�F�N�g���擾����B 
        //TraderImpl(long, boolean)
        //[����] 
        //�،���ЁF�،���ЃI�u�W�F�N�g --l_institution 
        //���҃R�[�h�F ����.��p�U�֋����L���[Params.���҃R�[�h --l_strTraderCode
        //���X�R�[�h�F ����.��p�U�֋����L���[Params.���X�R�[�h --l_strBranchCode 
        
        //a> traderCode
        String l_strTraderCode = l_params.getTraderCode();
        
        //b> branchCode 
        String l_strBranchCode = l_params.getBranchCode();
        
        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
        Trader l_trader = null;
        if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strTraderCode))
        {
            try
            {
                l_trader = l_finObjMgr.getTrader(l_institution, l_strTraderCode, l_strBranchCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error(" �e�[�u���ɊY������f�[�^������܂���: ", l_ex);
                log.exiting(l_strMethodName);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //1.3 �������擾����B 
        //get����(ProductTypeEnum, String, Institution)
        //[����] 
        //�����^�C�v�F ��p�U�֋����L���[Params.���i�敪�ɑΉ���������^�C�v 
        //�����R�[�h�F ��p�U�֋����L���[Params.�����R�[�h 
        //�،���ЁF �،���ЃI�u�W�F�N�g --l_institution
        
        //a> TradingModule
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //b> AIO�v���_�N�g�}�l�[�W�����擾 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
            
        //c> �����^�C�v
        String l_strCommodityDiv = l_params.getCommodityDiv();
        
        ProductTypeEnum l_productTypeEnum = null;
        if (WEB3AioHostCommodityDef.EQUITY.equals(l_strCommodityDiv))
        {
            l_productTypeEnum = ProductTypeEnum.EQUITY;        
        }
        else if (WEB3AioHostCommodityDef.BOND.equals(l_strCommodityDiv))
        {
            l_productTypeEnum = ProductTypeEnum.BOND;        
        }
        else if (WEB3AioHostCommodityDef.MUTUAL_FUND.equals(l_strCommodityDiv))
        {
            l_productTypeEnum = ProductTypeEnum.MUTUAL_FUND;        
        }
        
        //d> �����R�[�h
        String l_strProductCode = l_params.getProductCode();
        
        //e> get����    
        Product l_product = l_productManager.getProduct(
            l_productTypeEnum,
            l_strProductCode,
            l_institution);
            
        //1.4 �ڋq�I�u�W�F�N�g���擾����B
        //MainAccountImpl(long) 
        //[����] 
        //�،����ID�F �،����.getInstitutionId()�̖߂�l --l_lngInstitutionId
        //���X�R�[�h�F ����.��p�U�֋����L���[Params.���X�R�[�h --l_strBranchCode
        //�ڋq�R�[�h�F ����.��p�U�֋����L���[Params.�ڋq�R�[�h --l_strAccountCode
        
        //a> �،����ID
        long l_lngInstitutionId = l_institution.getInstitutionId();
        
        //b> �ڋq�R�[�h
        String l_strAccountCode = l_params.getAccountCode();
        
        MainAccount l_mainAccount;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(l_lngInstitutionId, l_strBranchCode, l_strAccountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
          
        //1.5 ���List�𐶐�����B 
        List l_lisOrderUnits = new ArrayList();  
        
        //1.6 �U�փ^�C�v[]�i=[1�i�����j�A2�i�o���j]�j �̗v�f����Loop����
        AssetTransferTypeEnum[] l_transferTypeEnum = new AssetTransferTypeEnum[2];
        l_transferTypeEnum[0] = AssetTransferTypeEnum.CASH_IN;
        l_transferTypeEnum[1] = AssetTransferTypeEnum.CASH_OUT;
        
        for (int i = 0; i < 2; i++)
        {
            //1.6.1 ���o���������e�C���X�^���X�𐶐�����B 
            //[����] 
            //�㗝���͎ҁF ���҃I�u�W�F�N�g --l_trader
            //������ʁF --l_orderTypeEnum
            //����.��p�U�֋����L���[Params.�a��敪=01�i�ی�a��j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j�j 
            //����.��p�U�֋����L���[Params.�a��敪=04�i��p�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j�j 
            //�U�փ^�C�v�F �U�փ^�C�v�̗v�f l_transferTypeEnum[i]
            //����ID�F �iget����()�̖߂�l�j.����ID --l_lngProductId
            //���ʁF --l_dblQuantity
            //�P�j�����^�C�v�̔���
            //�E����.��p�U�֋����L���[Params.
            //���i�敪�ɑΉ���������^�C�v = "2"�i���M�j
            //�̏ꍇ
            //(1) ���͒P�ʂ̔���
            //�iget����()�̖߂�l�j.���͒P�� = 1�i1�j�̏ꍇ�A
            //����.��p�U�֋����L���[Params.����
            //�iget����()�̖߂�l�j.���͒P�� = 2�i1���j�̏ꍇ�A
            //����.��p�U�֋����L���[Params.���� * 10000
            //(2) �U�փ^�C�v�̔���
            //�U�փ^�C�v = 1�i�����j�̏ꍇ�A
            //(1)�Ŏ擾��������
            //�U�փ^�C�v = 2�i�o���j�̏ꍇ�A
            //(1)�Ŏ擾�������� �~ -1
            //�E����.��p�U�֋����L���[Params.
            //���i�敪�ɑΉ���������^�C�v != "2"�i���M�ȊO�j
            // �̏ꍇ
            //(1) �U�փ^�C�v�̔���
            //�U�փ^�C�v=1�i�����j�̏ꍇ�A
            //����.��p�U�֋����L���[Params.����
            //�U�փ^�C�v=2�i�o���j�̏ꍇ�A
            //����.��p�U�֋����L���[Params.���� �~ -1
            //�L�q�F null 
            //�U�֗\����F ���ݓ��t --WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp())
            //���ϋ@@��ID�F null 
            //����ID�F null 
    
            //a> �������
            OrderTypeEnum l_orderTypeEnum;
            if (WEB3AioTransNotifyTransferFlagDef.SAFE_DEPOSIT.equals(l_params.getTransferFlag()))
            {
                l_orderTypeEnum = OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT;    
            }
            else 
            {
                l_orderTypeEnum = OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES;     
            }
            //b> ����ID
            long l_lngProductId = l_product.getProductId();
            
            //c> ����
            double l_lngQuantity = 0;
            // �@@�E����.��p�U�֋����L���[Params.���i�敪�ɑΉ���������^�C�v = "2"�i���M�j�̏ꍇ
            if (WEB3AioHostCommodityDef.MUTUAL_FUND.equals(l_strCommodityDiv))
            {
                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_product.getDataSourceObject();

                //�iget����()�̖߂�l�j.���͒P�� = 1�i1�j�̏ꍇ�A����.��p�U�֋����L���[Params.����
                if (WEB3InputUnitDef.ONE.equals(l_mutualFundProductRow.getInputUnit()))
                {
                    l_lngQuantity = l_params.getQuantity();
                }
                //�iget����()�̖߂�l�j.���͒P�� = 2�i1���j�̏ꍇ�A����.��p�U�֋����L���[Params.���� * 10000
                else if (WEB3InputUnitDef.TEN_THOUSAND.equals(l_mutualFundProductRow.getInputUnit()))
                {
                    BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_params.getQuantity()));
                    BigDecimal l_bdResult = l_bdQuantity.multiply(new BigDecimal(10000));
                    l_lngQuantity = l_bdResult.doubleValue();
                }
                // (2) �U�փ^�C�v�̔���
                // �U�փ^�C�v = 1�i�����j�̏ꍇ�A�P�Ŏ擾��������
                //�U�փ^�C�v = 2�i�o���j�̏ꍇ�A�P�Ŏ擾�������� �~ -1
                if (AssetTransferTypeEnum.CASH_OUT.equals(l_transferTypeEnum[i]))
                {
                    BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_lngQuantity));
                    BigDecimal l_bdResult = l_bdQuantity.multiply(new BigDecimal(-1));
                    l_lngQuantity = l_bdResult.doubleValue(); 
                }
            }
            else
            {
                //�U�փ^�C�v�̔���U�փ^�C�v=1�i�����j�̏ꍇ�A
                if (AssetTransferTypeEnum.CASH_IN.equals(l_transferTypeEnum[i]))
                {
                    l_lngQuantity = l_params.getQuantity();
                }
                //�U�փ^�C�v = 2�i�o���j�̏ꍇ�A�P�Ŏ擾�������� �~ -1
                else if (AssetTransferTypeEnum.CASH_OUT.equals(l_transferTypeEnum[i]))
                {
                    BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_params.getQuantity()));
                    BigDecimal l_bdResult = l_bdQuantity.multiply(new BigDecimal(-1));
                    l_lngQuantity = l_bdResult.doubleValue(); 
                }
            }

            //new
            WEB3AioNewOrderSpec l_aioNewOrderSpec = 
                new WEB3AioNewOrderSpec(
                    l_trader,
                    l_orderTypeEnum,
                    l_transferTypeEnum[i],
                    l_lngProductId,
                    l_lngQuantity,
                    null,
                    WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()),
                    null,
                    null);
                    
        
            //1.6.2 �،��U�֋����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
            //[����] 
            //���o���������e�F ���o���������e�I�u�W�F�N�g 
            WEB3AioSecurityTransferForceUpdateInterceptor l_aioSecurityTransferForceUpdateInterceptor = 
                new WEB3AioSecurityTransferForceUpdateInterceptor(l_aioNewOrderSpec);
                
            //1.6.3 �ȉ��̂Ƃ���ɃC���^�Z�v�^�̃v���p�e�B���Z�b�g����B
            
            //a> �C���^�Z�v�^.������ = ���o���������e.�U�֗\���
            l_aioSecurityTransferForceUpdateInterceptor.setOrderBizDate(
                l_aioNewOrderSpec.getEstimatedTransferDate());
            
            //b> �C���^�Z�v�^.��n�� = ���o���������e.�U�֗\���
            l_aioSecurityTransferForceUpdateInterceptor.setDeliveryDate(
                l_aioNewOrderSpec.getEstimatedTransferDate());
            
            //c> �C���^�Z�v�^.�ŋ敪 = �i�ȉ��̂Ƃ���j
            //��p�U�֋����L���[Params.��������敪 = 0�i��ʁj�̏ꍇ�A1�i��ʁj
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
            {
                l_aioSecurityTransferForceUpdateInterceptor.setTaxType(
                    TaxTypeEnum.NORMAL);
            }
            //��p�U�֋����L���[Params.��������敪 = 1�i����j�̏ꍇ�A2�i����j
            else
            {
                l_aioSecurityTransferForceUpdateInterceptor.setTaxType(
                    TaxTypeEnum.SPECIAL);
            }
            
            //d> �C���^�Z�v�^.���ʃR�[�h = ��p�U�֋����L���[Params.���ʃR�[�h
            l_aioSecurityTransferForceUpdateInterceptor.setOrderRequestNumber(
                l_params.getOrderRequestNumber());
             
            //1.6.4 �C���^�Z�v�^���Z�b�g����B
            //[����] 
            //�،��U�֋����X�V�C���^�Z�v�^�F �،��U�֋����X�V�C���^�Z�v�^�I�u�W�F�N�g 
            
            //AioOrderManager
            WEB3AioOrderManager l_orderManager = 
                (WEB3AioOrderManager)l_tradingModule.getOrderManager();
                
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioSecurityTransferForceUpdateInterceptor);
            
            //1.6.5 �⏕�����^�C�v���擾����B 
            //[����] 
            //������ʁF --l_orderTypeEnum
            //����.��p�U�֋����L���[Params.�a��敪=01�i�ی�a��j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j�j 
            //����.��p�U�֋����L���[Params.�a��敪=04�i��p�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j�j 
            //�U�փ^�C�v�F �U�փ^�C�v�̗v�f --l_transferTypeEnum[i]
            SubAccountTypeEnum l_subAccountTypeEnum = 
                this.getSubAccountType(
                    l_orderTypeEnum,
                    l_transferTypeEnum[i]);
                    
            //1.6.6 �⏕�����I�u�W�F�N�g���擾����B 
            //[����] 
            //�⏕�����^�C�v�F get�⏕�����^�C�v()�̖߂�l 
            WEB3GentradeSubAccount l_subAccount;
            try
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(l_subAccountTypeEnum);
            }
            catch (NotFoundException l_ex)
            {
                log.error("___NotFoundException___" , l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }

            //1.6.7 �V�K����ID���擾����B 
            long l_lngNewOrderId = l_orderManager.createNewOrderId();

            //1.6.8 �����o�^�������s���B 
            //[����] 
            //�⏕�����F �⏕�����I�u�W�F�N�g --l_subAccount
            //�����^�C�v�F ��p�U�֋����L���[Params.���i�敪�ɑΉ���������^�C�v --l_productTypeEnum
            //�������e�F ���o���������e�I�u�W�F�N�g --l_aioNewOrderSpec
            //����ID�F createNewOrderId()�̖߂�l --l_lngNewOrderId
            //�p�X���[�h�F �ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ����������� 
            //isSkip�����R���F true 

            //�p�X���[�h
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strPasswordDecypt = 
                l_crypt.decrypt(l_mainAccount.getTradingPassword());
            
            l_orderManager.submitNewOrder(
                l_subAccount,
                l_productTypeEnum,
                l_aioNewOrderSpec,
                l_lngNewOrderId,
                l_strPasswordDecypt,
                true);
                
            //1.6.9 �����P�ʂ��擾����B 
            //(*�z���1�Ԗڂ̗v�f���擾����j 
            //[����] 
            //����ID�F createNewOrderId()�̖߂�l 
            OrderUnit[] l_orderUnits = 
                l_orderManager.getOrderUnits(l_lngNewOrderId);
            //AioOrderUnit l_orderUnit = (AioOrderUnit)l_orderUnits[0];
                
            //1.6.10 ���X�g�ɒǉ�����B 
            //[����] 
            //arg0�F getOrderUnits()�Ŏ擾���������P�� 
            l_lisOrderUnits.add((AioOrderUnit)l_orderUnits[0]);
        }
        
        //1.7 �z����擾����B 
        AioOrderUnit[] l_arrayOrderUnits = new AioOrderUnit[2];
        
        l_lisOrderUnits.toArray(l_arrayOrderUnits);    

		//1.8 �]�͂̍Čv�Z���s���B 
		//[����] 
		//�⏕�����F getSubAccount()�̖߂�l�i�ۏ؋������j 
		WEB3GentradeSubAccount l_subAccount;
		try
		{
			l_subAccount = 
				(WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
		}
		catch (NotFoundException l_ex)
		{
			log.error("___NotFoundException___" , l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + l_strMethodName,
				l_ex.getMessage(),
				l_ex);
		}
		WEB3TPTradingPowerReCalcService l_service =
			(WEB3TPTradingPowerReCalcService) Services.getService(
		WEB3TPTradingPowerReCalcService.class);      
        
		l_service.reCalcTradingPower(l_subAccount);
            
        log.exiting(l_strMethodName);
        
        return l_arrayOrderUnits;
    }
    
    /**
     * (get�⏕�����^�C�v)<BR>
     * �������猈�肳���⏕�����^�C�v��ԋp����B<BR>
     * <BR>
     * �P�j����.������� = 1009�i�،��U�֒����i�ی�a�肩���p�L���،��j�j�̏ꍇ<BR>
     * <BR>
     * �P�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ<BR>
     * <BR>
     *    2�i�ۏ؋������j��ԋp����B<BR>
     * <BR>
     * �P�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ<BR>
     * <BR>
     *    1�i�a��������j��ԋp����B<BR>
     * <BR>
     * �Q�j����.������� = 1010�i�،��U�֒����i��p�L���،�����ی�a��j�j�̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ<BR>
     * <BR>
     *    1�i�a��������j��ԋp����B<BR>
     * 
     * �Q�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ<BR>
     * <BR>
     *    2�i�ۏ؋������j��ԋp����B
     * @@param l_orderType - �������
     * @@param l_assetTransferType - �U�փ^�C�v
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum
     * @@roseuid 416C9A4B0190
     */
    protected SubAccountTypeEnum getSubAccountType(OrderTypeEnum l_orderType, AssetTransferTypeEnum l_assetTransferType) 
    {
        String l_strMethodName = 
            "getSubAccountType(OrderTypeEnum l_orderType, AssetTransferTypeEnum l_assetTransferType)";
        log.entering(l_strMethodName); 
        
        //return 
        SubAccountTypeEnum l_subAccountTypeEnum;
        
        //�P�j����.������� = 1009�i�،��U�֒����i�ی�a�肩���p�L���،��j�j�̏ꍇ
        if (OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES.equals(l_orderType))
        {
            //�P�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ
            if (AssetTransferTypeEnum.CASH_IN.equals(l_assetTransferType))
            {
                //2�i�ۏ؋������j��ԋp����B
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            //�P�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ
            else 
            {
                //1�i�a��������j��ԋp����B
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
        }
        //�Q�j����.������� = 1010�i�،��U�֒����i��p�L���،�����ی�a��j�j�̏ꍇ           
        else
        {
            //�Q�|�P�j����.�U�փ^�C�v = 1�i�����j�̏ꍇ
            if (AssetTransferTypeEnum.CASH_IN.equals(l_assetTransferType))
            {
                //1�i�a��������j��ԋp����B
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            //�Q�|�Q�j����.�U�փ^�C�v = 2�i�o���j�̏ꍇ
            else 
            {
                //2�i�ۏ؋������j��ԋp����B
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
        }   
                        
        log.exiting(l_strMethodName);
        
        return l_subAccountTypeEnum;        
    }
}
@
