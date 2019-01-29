head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������U�֋���UnitServiceImpl(WEB3AioSpsecTransferForceUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 ��O�� (���u) �V�K�쐬
                   2006/10/26 �����q (���u) �d�l�ύX�E���f��665
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.aio.WEB3AioCashoutCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.WEB3AioSpsecTransferForceUpdateInterceptor;
import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceUnitService;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioHostSpsecCommodityDef;
import webbroker3.common.define.WEB3BondCategCodeDef;
import webbroker3.common.define.WEB3CustdyDiv;
import webbroker3.common.define.WEB3IoDivDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3NameMethodDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (��������U�֋���UnitServiceImpl)<BR>
 * ��������U�֋���UnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)<BR>
 * ���w�肷��B
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceUnitServiceImpl implements WEB3AioSpsecTransferForceUnitService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceUnitServiceImpl.class); 
    
    /**
     * (submit����)<BR>
     * ��������U�ւ̒�����o�^���A���̒����P�ʂ��擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i��������U�֋����jsubmit�����v �Q�� 
     * @@param l_params - ������������U�փL���[�e�[�u���̍s�I�u�W�F�N�g
     * @@return AioOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    public AioOrderUnit[] submitOrder(HostSpsecTransNotifyParams l_params) 
        throws WEB3BaseException
    {   
        String STR_METHOD_NAME = "submitOrder(HostSpsecTransNotifyParams l_params)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 �،���ЃI�u�W�F�N�g���擾����B 
        //InstitutionImpl(long)
        //[����] 
        //�،���ЃR�[�h�F ����.������������U�փL���[Params.�،���ЃR�[�h 
        
        FinApp l_finApp = 
            (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = GtlUtils.getAccountManager();  
           
        Institution l_institution = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_params.getInstitutionCode());
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
        
        String l_strBranchCode = l_params.getBranchCode();
        
        //1.2 �������擾����B 
        //get����(ProductTypeEnum, String, Institution)
        //[����] 
        //�����^�C�v�F ������������U�փL���[Params.���i�敪�ɑΉ���������^�C�v 
        //�����R�[�h�F ������������U�փL���[Params.�����R�[�h 
        //�،���ЁF �،���ЃI�u�W�F�N�g 

        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
            
        //�����^�C�v�F ������������U�փL���[Params.���i�敪�ɑΉ���������^�C�v 
        String l_strCommodityDiv = l_params.getCommodityDiv();
        
        ProductTypeEnum l_productType = null;
        if (WEB3AioHostSpsecCommodityDef.EQUITY.equals(l_strCommodityDiv))
        {
            l_productType = ProductTypeEnum.EQUITY;        
        }
        else if (WEB3AioHostSpsecCommodityDef.BOND.equals(l_strCommodityDiv))
        {
            l_productType = ProductTypeEnum.BOND;
        }
        else if (WEB3AioHostSpsecCommodityDef.MUTUAL_FUND.equals(l_strCommodityDiv))
        {
            l_productType = ProductTypeEnum.MUTUAL_FUND;        
        }
        else if (WEB3AioHostSpsecCommodityDef.FOREIGN_EQUITY.equals(l_strCommodityDiv))
        {
            l_productType = ProductTypeEnum.FOREIGN_EQUITY;        
        }
        
        log.debug("�����^�C�v�F = " + l_productType);
        String l_strProductCode = l_params.getProductCode();
        
        Product l_product = l_productManager.getProduct(
                l_productType,
                l_strProductCode,
                l_institution);
            
        //1.3 �ڋq�I�u�W�F�N�g���擾����B
        //MainAccountImpl(long) 
        //[����] 
        //�،����ID�F �،����.getInstitutionId()�̖߂�l 
        //���X�R�[�h�F ����.������������U�փL���[Params.���X�R�[�h 
        //�ڋq�R�[�h�F ����.������������U�փL���[Params.�ڋq�R�[�h 
        
        long l_lngInstitutionId = l_institution.getInstitutionId();        
        String l_strAccountCode = l_params.getAccountCode();
        
        MainAccount l_mainAccount;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(
                    l_lngInstitutionId, 
                    l_strBranchCode, 
                    l_strAccountCode);
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
        
        //1.4 ���o���������e�C���X�^���X�𐶐�����B     
        
        //������ʁF �i�ȉ��̂Ƃ���j 
        //�P�j����.������������U�փL���[.��������敪 = �h��ʌ����h�̏ꍇ 
        //�E����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ�AOrderTypeEnum.�h�،��U�֒����i��ʌ��������������j�h 
        //�E����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ�AOrderTypeEnum.�h�،��U�֒����i������������ʌ����j�h 
        //�Q�j����.������������U�փL���[.��������敪 = �h��������h�̏ꍇ 
        //�E����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ�AOrderTypeEnum.�h�،��U�֒����i������������ʌ����j�h 
        //�E����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ�AOrderTypeEnum.�h�،��U�֒����i��ʌ��������������j�h 
        OrderTypeEnum l_orderType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
        {
            log.debug("����.������������U�փL���[.��������敪 = �h��ʌ����h�̏ꍇ");
            if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
            {
                log.debug("����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ");
                l_orderType = OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT;
            }
            else 
            {
                log.debug("����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ");
                l_orderType = OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT;
            }
        }
        else
        {
            log.debug("����.������������U�փL���[.��������敪 = �h��������h�̏ꍇ");
            if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
            {
                log.debug("����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ");
                l_orderType = OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT;
            }
            else 
            {
                log.debug("����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ");
                l_orderType = OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT;
            }
        }     
        log.debug("������ʁF = " + l_orderType);
        
        //�U�փ^�C�v�F �i�ȉ��̂Ƃ���j 
        //����.������������U�փL���[Params.�o���敪=�h�o�h �̏ꍇ�A�h�o���h 
        //����.������������U�փL���[Params.�o���敪=�h���h �̏ꍇ�A�h�����h 
        AssetTransferTypeEnum l_transferType = null;
        if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
        {
            l_transferType = AssetTransferTypeEnum.CASH_OUT;
        }
        else
        {
            l_transferType = AssetTransferTypeEnum.CASH_IN;
        }
        log.debug("�U�փ^�C�v�F = " + l_transferType);
        
        //����ID�F �iget����()�̖߂�l�j.����ID 
        long l_lngProductId = l_product.getProductId();
        log.debug("����ID�F = " + l_lngProductId);
        
        //���ʁF 
        //�U�փ^�C�v=�h�����h�̏ꍇ�A����.������������U�փL���[Params.���� 
        //�U�փ^�C�v=�h�o���h�̏ꍇ�A����.������������U�փL���[Params.���� �~ -1 
        long l_lngQuantity = 0;
        if (AssetTransferTypeEnum.CASH_IN.equals(l_transferType))
        {
            l_lngQuantity = l_params.getQuantity();                
        }
        else
        {
            l_lngQuantity = l_params.getQuantity() * (-1);
        }      
        log.debug("���ʁF = " + l_lngQuantity);
        
        //�㗝���͎ҁF null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec = 
            new WEB3AioNewOrderSpec(
                null,
                l_orderType,
                l_transferType,
                l_lngProductId,
                l_lngQuantity,
                null,
                WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()),
                null,
                null);
                    
        
        //1.5 ��������U�֋����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g 
        WEB3AioSpsecTransferForceUpdateInterceptor l_aioSpsecTransferForceUpdateInterceptor = 
            new WEB3AioSpsecTransferForceUpdateInterceptor(l_aioNewOrderSpec);
            
        //1.6 �ȉ��̂Ƃ���ɃC���^�Z�v�^�̃v���p�e�B���Z�b�g����B
        
        //�C���^�Z�v�^.������ = ���o���������e.�U�֗\���
        l_aioSpsecTransferForceUpdateInterceptor.setOrderBizDate(
            l_aioNewOrderSpec.getEstimatedTransferDate());
        
        //�C���^�Z�v�^.��n�� = ���o���������e.�U�֗\���
        l_aioSpsecTransferForceUpdateInterceptor.setDeliveryDate(
            l_aioNewOrderSpec.getEstimatedTransferDate());
        
        //�C���^�Z�v�^.�ŋ敪 = �i�ȉ��̂Ƃ���j
        //����.������������U�փL���[.��������敪 = �h��ʌ����h�̏ꍇ�A�h��ʁh 
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
        {
            log.debug("������������U�փL���[Params.��������敪 = '���' �̏ꍇ");
            l_aioSpsecTransferForceUpdateInterceptor.setTaxType(
                TaxTypeEnum.NORMAL);
        }
        //����.������������U�փL���[.��������敪 = �h��������h�̏ꍇ�A�h����h 
        else
        {
            log.debug("������������U�փL���[Params.��������敪 = '����' �̏ꍇ");
            l_aioSpsecTransferForceUpdateInterceptor.setTaxType(
                TaxTypeEnum.SPECIAL);
        }
        
        //�C���^�Z�v�^.�~�j���敪 = ������������U�փL���[Params.�~�j���敪
        l_aioSpsecTransferForceUpdateInterceptor.setMiniStockDiv(
            l_params.getMiniStockDiv());
        
        //�C���^�Z�v�^.���ʃR�[�h = ������������U�փL���[Params.���ʃR�[�h
        l_aioSpsecTransferForceUpdateInterceptor.setOrderRequestNumber(
            l_params.getOrderRequestNumber());
         
        //1.7 �C���^�Z�v�^���Z�b�g����B
        //[����] 
        //��������U�֋����X�V�C���^�Z�v�^�F ��������U�֋����X�V�C���^�Z�v�^�I�u�W�F�N�g  
        
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
            
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioSpsecTransferForceUpdateInterceptor);
        
        //1.8 �⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        //�⏕�����^�C�v�F �h�a��������h
        
        WEB3GentradeSubAccount l_subAccount;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
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
        
        //1.9 �V�K����ID���擾����B 
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.10 �����o�^�������s���B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //�����^�C�v�F ������������U�փL���[Params.���i�敪�ɑΉ���������^�C�v 
        //�������e�F ���o���������e�I�u�W�F�N�g 
        //����ID�F createNewOrderId()�̖߂�l 
        //�p�X���[�h�F �ڋq.getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ����������� 
        //isSkip�����R���F true 

        WEB3Crypt l_crypt = new WEB3Crypt();
        String l_strPasswordDecypt = 
            l_crypt.decrypt(l_mainAccount.getTradingPassword());
        
        l_orderManager.submitNewOrder(
            l_subAccount, 
            l_productType, 
            l_aioNewOrderSpec, 
            l_lngNewOrderId, 
            l_strPasswordDecypt, 
            true);
            
        //1.11 �����P�ʂ��擾����B 
        //(*�z���1�Ԗڂ̗v�f���擾����j 
        //[����] 
        //����ID�F createNewOrderId()�̖߂�l 
        log.debug("createNewOrderId()�̖߂�l = " + l_lngNewOrderId);
        OrderUnit[] l_orderUnits = 
            l_orderManager.getOrderUnits(l_lngNewOrderId);
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
            
        //1.12 is���t�\(������������U�փL���[Params)
        //���t�\�̔�����s���B 
        //[����] 
        //������������U�փL���[�F ������������U�փL���[Params�I�u�W�F�N�g
        boolean l_blnIsSellPossible = this.isSellPossible(l_params);
        
        //1.13 �ۗL���Y�e�[�u�����X�V����B 
        //�i�ۗL���Y�e�[�u���Ɋ������R�[�h�����݂��Ȃ��ꍇ�́A�V�K�o�^����B�j 
        //[����] 
        //�����P�ʁF �����P�ʃI�u�W�F�N�g
        //is���t�\�̖߂�l
        this.updateAsset(l_aioOrderUnit, l_blnIsSellPossible);

        //1.14 �]�͂̍Čv�Z���s���B 
        //[����] 
        //�⏕�����F getSubAccount()�̖߂�l 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);      
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        List l_lisAioOrderUnit = new ArrayList();
        
        for (int i = 0; i < l_orderUnits.length; i++)
        {
            l_aioOrderUnit = (AioOrderUnit)l_orderUnits[i];                        
            l_lisAioOrderUnit.add(l_aioOrderUnit);
        }
        AioOrderUnit[] l_aioOrderUnits = new AioOrderUnit[l_orderUnits.length];
        l_lisAioOrderUnit.toArray(l_aioOrderUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_aioOrderUnits;
    }
    
        
    /**
     * (submit���)<BR>
     * ��������U�ւ̒����̎�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i��������U�֋����jsubmit����v �Q�� 
     * @@param l_params - ������������U�փL���[�e�[�u���̍s�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    public void submitCancel(HostSpsecTransNotifyParams l_params) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "submitCancel(HostSpsecTransNotifyParams l_params)";
        log.entering(STR_METHOD_NAME);
        
    	//1.1 �Y�����钍���P�ʂ��擾����B 
        //[����] 
        //������������U�փL���[�F ������������U�փL���[Params�I�u�W�F�N�g 
        AioOrderUnit l_aioOrderUnit = this.getOrderUnit(l_params);
        
        //1.2 ����������e�C���X�^���X�𐶐�����B 
        //[����] 
        //����ID�F �����P��.����ID 
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(l_aioOrderUnit.getOrderId());
        
        //1.3 �o������X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        WEB3AioCashoutCancelUpdateInterceptor l_aioCashoutCancelUpdateInterceptor = 
            new WEB3AioCashoutCancelUpdateInterceptor();
        
        //1.4 �C���^�Z�v�^���Z�b�g����B 
        //[����] 
        //arg0�F �o������X�V�C���^�Z�v�^�I�u�W�F�N�g 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_aioCashoutCancelUpdateInterceptor);
        
        //1.5 �⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F �����P��.����ID 
        //�⏕����ID�F �����P��.�⏕����ID 
        WEB3GentradeAccountManager l_accManage = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accManage.getSubAccount(
                l_aioOrderUnit.getAccountId(),
                l_aioOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�⏕�����I�u�W�F�N�g���擾����:",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }      
        
        //1.6 ��������������s���B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //����������e�F ����������e�I�u�W�F�N�g 
        //�p�X���[�h�F �⏕����.getMainAccount().getTradingPassword()�̖߂�l��WEB3Crypt.decrypt()�ŕ����������� 
        //isSkip�����R���F true 
        WEB3Crypt l_web3Crypt = new WEB3Crypt();
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        String l_strPassword = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());
        
        l_aioOrderManager.submitCancelOrder(
                l_subAccount, 
                l_cancelOrderSpec, 
                l_strPassword, 
                true);
        try
        {
            l_aioOrderUnit = (AioOrderUnit)
                l_aioOrderManager.getOrderUnit(l_aioOrderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                
        
        //1.7 is���t�\(������������U�փL���[Params)
        //���t�\�̔�����s���B 
        //[����] 
        //������������U�փL���[�F ������������U�փL���[Params�I�u�W�F�N�g
        boolean l_blnIsSellPossible = this.isSellPossible(l_params);

        //1.8 �ۗL���Y�e�[�u�����X�V����B 
        //[����] 
        //�����P�ʁF �����P�ʃI�u�W�F�N�g 
        //is���t�\()�̖߂�l 
        this.updateAsset(l_aioOrderUnit, l_blnIsSellPossible);
        
        //1.9 �]�͂̍Čv�Z���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);      
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        l_service.reCalcTradingPower(l_gentradeSubAccount);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�����P��)<BR>
     * �Y�����钍���P�ʂ��擾����B <BR>
     * <BR>
     *�P�j�ȉ��̃I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *�P�|�P�j�،���ЃI�u�W�F�N�g<BR>
     * �g���A�J�E���g�}�l�[�W��.getInstitutiion()���R�[������B<BR>
     *   <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.������������U�փL���[.�،���ЃR�[�h <BR>
     * <BR>
     *�P�|�Q�j���X�I�u�W�F�N�g <BR>
     * �g���A�J�E���g�}�l�[�W��.get���X()���R�[������B<BR>
     *  <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.������������U�փL���[.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F ����.������������U�փL���[.���X�R�[�h <BR>
     * <BR>
     *�P�|�R�j�ڋq�I�u�W�F�N�g <BR>
     * <BR>
     * �g���A�J�E���g�}�l�[�W��.getMainAccount()���R�[������B <BR>
     * <BR>
     *[����] <BR>
     * �،���ЁF �P�|�P�j�Ŏ擾�����،���ЃI�u�W�F�N�g <BR>
     * ���X�F �P�|�Q�j�Ŏ擾�������X�I�u�W�F�N�g <BR>
     * �ڋq�R�[�h�F ����.������������U�փL���[.�ڋq�R�[�h <BR>
     * <BR>
     *�P�|�S�j�⏕�����I�u�W�F�N�g <BR>
     * �g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * ����ID�F �ڋq.����ID <BR>
     * �⏕�����^�C�v�F �a������� <BR>
     * <BR>
     *�P�|�T�j�����I�u�W�F�N�g <BR>
     * AIO�v���_�N�g�}�l�[�W��.get����()���R�[������B <BR>
     * <BR>�@@�@@
     * [����] <BR>
     * �����^�C�v�F ������������U�փL���[Params.���i�敪�ɑΉ���������^�C�v  <BR>
     * �����R�[�h�F ������������U�փL���[Params.�����R�[�h  <BR>
     * �،���ЁF �،���ЃI�u�W�F�N�g  <BR>
     * <BR>
     * 
     *�Q�j�����P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     *[�擾����] <BR>
     *   ����ID = ����.����ID <BR>
     *   �⏕����ID = �⏕����.�⏕����ID <BR>
     *   ���XID = ���X.���XID <BR>
     *   �����^�C�v = ����.������������U�փL���[.���i�敪�ɑΉ���������^�C�v <BR>
     *   �ŋ敪 = �i�ȉ��̂Ƃ���j <BR>
     *      ����.������������U�փL���[.��������敪 = �h��ʌ����h�̏ꍇ�A�h��ʁh <BR>
     *      ����.������������U�փL���[.��������敪 = �h��������h�̏ꍇ�A�h����h <BR>
     * <BR>
     *   �~�j���敪 = ����.������������U�փL���[.�~�j���敪 <BR>
     *   ������� = �i�ȉ��̂Ƃ���j <BR>
     *      �P�j����.������������U�փL���[.��������敪 = �h��ʌ����h�̏ꍇ <BR>
     *         �E����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ�A <BR>
     *			OrderTypeEnum.�h�،��U�֒����i��ʌ��������������j�h<BR>
     *         �E����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ�A <BR>
     *			OrderTypeEnum.�h�،��U�֒����i������������ʌ����j�h<BR>
     * <BR>
     *      �Q�j����.������������U�փL���[.��������敪 = �h��������h�̏ꍇ <BR>
     * 	       �E����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ�A <BR>
     *			OrderTypeEnum.�h�،��U�֒����i������������ʌ����j�h<BR>
     * 	       �E����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ�A <BR>
     *			OrderTypeEnum.�h�،��U�֒����i��ʌ��������������j�h<BR>
     * <BR>
     *   �����L����� = OrderOpenStatusEnum.�h�I�[�v���h <BR>
     *   ���� = �i�ȉ��̂Ƃ���j <BR>
     *		����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ�A <BR>
     *		     ����.������������U�փL���[.���� <BR>
     *		����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ�A <BR>
     *		     ����.������������U�փL���[.���� �~ -1 <BR>
     * <BR>
     * ����ID�F �iget����()�̖߂�l�j.����ID <BR>
     * <BR>
     *�R�j�擾���������P�ʂ�ԋp����B <BR>
     * <BR>
     *   �������s��v�����ꍇ�́A�󒍓�������ԌÂ����̂�ԋp����B <BR>
     * <BR>
     * @@param l_params - ������������U�փL���[�e�[�u���̍s�I�u�W�F�N�g
     * @@return AioOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 416C9A4B0190
     */
    protected AioOrderUnit getOrderUnit(HostSpsecTransNotifyParams l_params) 
    	throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getOrderUnit(HostSpsecTransNotifyParams l_params)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);    
        //�g���A�J�E���g�}�l�[�W���擾���� 
        WEB3GentradeAccountManager l_gentradeAccountManager =  
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        try
        {
            //�P�j�ȉ��̃I�u�W�F�N�g���擾����B 
            //�P�|�P�j�،���ЃI�u�W�F�N�g 
            //�g���A�J�E���g�}�l�[�W��.getInstitutiion()���R�[������B 
            //[����] 
            //�،���ЃR�[�h�F ����.������������U�փL���[.�،���ЃR�[�h 
            Institution l_institution = l_gentradeAccountManager.getInstitution(
                    l_params.getInstitutionCode());
            
            //�P�|�Q�j���X�I�u�W�F�N�g 
            //�g���A�J�E���g�}�l�[�W��.get���X()���R�[������B 
            //[����] 
            //�،���ЃR�[�h�F ����.������������U�փL���[.�،���ЃR�[�h 
            //���X�R�[�h�F ����.������������U�փL���[.���X�R�[�h
            Branch l_branch = l_gentradeAccountManager.getWeb3GenBranch(
                    l_params.getInstitutionCode(), 
                    l_params.getBranchCode());
            
            //�P�|�R�j�ڋq�I�u�W�F�N�g 
            //�g���A�J�E���g�}�l�[�W��.getMainAccount()���R�[������B 
            //[����] 
            //�،���ЁF �P�|�P�j�Ŏ擾�����،���ЃI�u�W�F�N�g 
            //���X�F �P�|�Q�j�Ŏ擾�������X�I�u�W�F�N�g 
            //�ڋq�R�[�h�F ����.������������U�փL���[.�ڋq�R�[�h 
            MainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                    l_institution, 
                    l_branch, 
                    l_params.getAccountCode());
            
            //�P�|�S�j�⏕�����I�u�W�F�N�g 
            //�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B 
            //[����] 
            //����ID�F �ڋq.����ID 
            //�⏕�����^�C�v�F �a�������
            SubAccountTypeEnum l_subAccountType = null;
            WEB3GentradeMainAccount l_gentradeMainAcc = 
                (WEB3GentradeMainAccount)l_mainAccount;

            SubAccount l_subAccount = l_gentradeAccountManager.getSubAccount(
                    l_mainAccount.getAccountId(), 
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    
            //�P�|�T�j�����I�u�W�F�N�g
            //AIO�v���_�N�g�}�l�[�W��.get����()���R�[������B
            //[����]
            //�����^�C�v�F ������������U�փL���[Params.���i�敪�ɑΉ���������^�C�v 
            //�����R�[�h�F ������������U�փL���[Params.�����R�[�h 
            //�،���ЁF �،���ЃI�u�W�F�N�g 
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
            WEB3AioProductManager l_productManager = 
                (WEB3AioProductManager)l_tradingModule.getProductManager();
            
            //�����^�C�v�F ������������U�փL���[Params.���i�敪�ɑΉ���������^�C�v 
            String l_strCommodityDiv = l_params.getCommodityDiv();
        
            ProductTypeEnum l_productType = null;
            if (WEB3AioHostSpsecCommodityDef.EQUITY.equals(l_strCommodityDiv))
            {
                l_productType = ProductTypeEnum.EQUITY;        
            }
            else if (WEB3AioHostSpsecCommodityDef.BOND.equals(l_strCommodityDiv))
            {
                l_productType = ProductTypeEnum.BOND;
            }
            else if (WEB3AioHostSpsecCommodityDef.MUTUAL_FUND.equals(l_strCommodityDiv))
            {
                l_productType = ProductTypeEnum.MUTUAL_FUND;        
            }
            else if (WEB3AioHostSpsecCommodityDef.FOREIGN_EQUITY.equals(l_strCommodityDiv))
            {
                l_productType = ProductTypeEnum.FOREIGN_EQUITY;        
            }
        
            log.debug("�����^�C�v�F = " + l_productType);
            String l_strProductCode = l_params.getProductCode();
        
            Product l_product = l_productManager.getProduct(
                    l_productType,
                    l_strProductCode,
                    l_institution);

                    
            //�Q�j�����P�ʃI�u�W�F�N�g���擾����B
            List l_lisAioOrderUnitRows = null;
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                "and branch_id = ? and product_type = ? and tax_type = ? " +
                "and mini_stock_div = ? and order_type = ? " +
                "and order_open_status = ? and quantity = ? and product_id = ? ";
            
            //����ID = ����.����ID
            long l_lngAccountId = l_mainAccount.getAccountId();
            
            //�⏕����ID = �⏕����.�⏕����ID
            long l_lngSubAccountId = l_subAccount.getSubAccountId();
            
            //���XID = ���X.���XID
            long l_lngBranchId = l_branch.getBranchId();           
            
            //�����^�C�v = ����.������������U�փL���[.�����^�C�v            
            
            //�ŋ敪 = �i�ȉ��̂Ƃ���j 
            //����.������������U�փL���[.��������敪 = �h��ʌ����h�̏ꍇ�A�h��ʁh 
            //����.������������U�փL���[.��������敪 = �h��������h�̏ꍇ�A�h����h 
            
            TaxTypeEnum l_taxType = null;
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
            {
                l_taxType = TaxTypeEnum.NORMAL;
            }
            else
            {
                l_taxType = TaxTypeEnum.SPECIAL;
            }
            log.debug("�ŋ敪 = " + l_taxType);
            
            //�~�j���敪 = ����.������������U�փL���[.�~�j���敪 
            String l_strMiniStockDiv = l_params.getMiniStockDiv();
            log.debug("�~�j���敪 = " + l_strMiniStockDiv);
            
            //������� = �i�ȉ��̂Ƃ���j 
            //�P�j����.������������U�փL���[.��������敪 = �h��ʌ����h�̏ꍇ 
            //�E����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ�A
            //      OrderTypeEnum.�h�،��U�֒����i��ʌ��������������j�h 
            //�E����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ�A
            //      OrderTypeEnum.�h�،��U�֒����i������������ʌ����j�h 
            //�Q�j����.������������U�փL���[.��������敪 = �h��������h�̏ꍇ 
            //�E����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ�A
            //      OrderTypeEnum.�h�،��U�֒����i������������ʌ����j�h 
            //�E����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ�A
            //      OrderTypeEnum.�h�،��U�֒����i��ʌ��������������j�h 

            OrderTypeEnum l_orderType = null;
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_params.getSpecificAccountDiv()))
            {
                log.debug("������������U�փL���[.��������敪 = �h��ʌ����h�̏ꍇ");
                if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
                {
                    l_orderType = OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT;
                }
                else 
                {
                    l_orderType = OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT;
                }
            }
            else
            {
                log.debug("������������U�փL���[.��������敪 = �h��������h�̏ꍇ");
                if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
                {
                    l_orderType = OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT;
                }
                else 
                {
                    l_orderType = OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT;
                }
            }
            log.debug("������� = " + l_orderType);
            
            //�����L����� = OrderOpenStatusEnum.�h�I�[�v���h 
            OrderOpenStatusEnum l_orderOpenStatus = OrderOpenStatusEnum.OPEN;
            log.debug("�����L����� = " + l_orderOpenStatus);
            
            //���� = �i�ȉ��̂Ƃ���j 
            //����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ�A����.������������U�փL���[.���� 
            //����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ�A����.������������U�փL���[.���� �~ -1 
            long l_lngQuantity = 0;
            if (WEB3IoDivDef.OUTPUT.equals(l_params.getTransferFlag()))
            {
                log.debug("����.������������U�փL���[.�o���敪 = �h�o�h�̏ꍇ");
                l_lngQuantity = l_params.getQuantity();
            }
            else
            {
                log.debug("����.������������U�փL���[.�o���敪 = �h���h�̏ꍇ");
                l_lngQuantity = l_params.getQuantity() * (-1);
            }
            log.debug("���� = " + l_lngQuantity);
            
            //����ID�F �iget����()�̖߂�l�j.����ID 
            long l_lngProductId = l_product.getProductId();
            log.debug("����ID�F = " + l_lngProductId);
            
            Object l_bindVars[] = {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngBranchId),
                l_productType,  
                l_taxType, 
                l_strMiniStockDiv,
                l_orderType, 
                l_orderOpenStatus, 
                new Long(l_lngQuantity),
                new Long(l_lngProductId)};
                
            try
            {
                l_lisAioOrderUnitRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        AioOrderUnitRow.TYPE,
                        l_strWhereClause,
                        null,
                        l_bindVars);

            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //�R�j�擾���������P�ʂ�ԋp����B 
            //�������s��v�����ꍇ�́A�󒍓�������ԌÂ����̂�ԋp����B
            AioOrderUnit l_aioOrderUnit = null;
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();                
            
            if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.size() == 0)
            {
                log.debug("�����P�ʃe�[�u������A���R�[�h���擾���Ȃ� !");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            log.debug("l_lisAioOrderUnitRows.size() = " + l_lisAioOrderUnitRows.size());
            
            if (l_lisAioOrderUnitRows.size() > 1)
            {
                int l_intOldDateFlag = 0;
                AioOrderUnitRow l_oldAioOrderUnitRow = 
                    (AioOrderUnitRow)l_lisAioOrderUnitRows.get(0);
                log.debug("�����P��Row = " + l_oldAioOrderUnitRow);
                
                Date l_datOldDate = l_oldAioOrderUnitRow.getReceivedDateTime();
                AioOrderUnitRow l_nextAioOrderUnitRow = null;
                                
                for (int i = 1; i < l_lisAioOrderUnitRows.size(); i++)
                {
                    l_nextAioOrderUnitRow = 
                        (AioOrderUnitRow)l_lisAioOrderUnitRows.get(i);
                    log.debug("�����P��Row = " + l_nextAioOrderUnitRow);
                    
                    Date l_datNextDat = l_nextAioOrderUnitRow.getReceivedDateTime();
                    
                    if (WEB3DateUtility.compareToDay(l_datNextDat, l_datOldDate) < 0)
                    {
                        l_datOldDate = l_datNextDat;
                        l_intOldDateFlag = i;
                    }
                }
                AioOrderUnitRow l_aioOrderUnitRow = 
                    (AioOrderUnitRow)l_lisAioOrderUnitRows.get(l_intOldDateFlag);
                log.debug("�擾���������P��Row = " + l_aioOrderUnitRow);
                
                l_aioOrderUnit = (AioOrderUnit) l_orderManager.toOrderUnit(
                        l_aioOrderUnitRow);
            }
            else if (l_lisAioOrderUnitRows.size() == 1)
            {
                //�����P�ʃI�u�W�F�N�g���擾����B
                AioOrderUnitRow l_aioOrderUnitRow = 
                    (AioOrderUnitRow)l_lisAioOrderUnitRows.get(0);         
                log.debug("�����P��Row = " + l_aioOrderUnitRow);
                
                l_aioOrderUnit = (AioOrderUnit) l_orderManager.toOrderUnit(
                        l_aioOrderUnitRow);
    
                log.exiting(STR_METHOD_NAME);
            }          
            log.exiting(STR_METHOD_NAME);
            return l_aioOrderUnit;
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                
    }
    
    /**    
     *(update�ۗL���Y)<BR>
     * �ۗL���Y�̐��ʂƕ뉿���X�V����B <BR>
     * ���������R�[�h�����݂��Ȃ��ꍇ�́A�V�K�o�^����B <BR>
     * <BR>
     * �P�j�ڋq�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * ����ID�F �����P��.����ID <BR>
     * �Q�j�⏕�����I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �ڋq.getSubAccount()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �⏕�����^�C�v�F �i�ȉ��̂Ƃ���j <BR>
     * �ڋq.is�M�p�����J��()==true�̏ꍇ�A�h�ۏ؋������h <BR>
     * �ڋq.is�M�p�����J��()==false�̏ꍇ�A�h�a��������h <BR>
     * <BR>
     * �R�j�ΏۂƂȂ�ۗL���Y���R�[�h���擾����B <BR>
     * <BR>
     * [�擾����] <BR>
     * ����ID = ����.�����P��.����ID <BR>
     * �⏕����ID = �⏕����.�⏕����ID <BR>
     * ����ID = ����.�����P��.����ID <BR>
     * �ŋ敪 = ����.�����P��.�ŋ敪 <BR>
     * �~�j���敪 = ����.�����P��.�~�j���敪 <BR>
     * <BR>
     * �S�j�ۗL���Y���R�[�h���X�V�i�o�^�j����B <BR>
     * <BR>
     * �S�|�P�j�ۗL���Y���R�[�h���擾�ł��Ȃ������ꍇ <BR>
     * <BR>
     * �ۗL���Y���R�[�h��o�^����B <BR>
     * ���ڂ̐ݒ���@@�́ADB�X�V�d�l�Q�ƁB <BR>
     * <BR>
     * �S�|�Q�j�ۗL���Y���R�[�h���擾�ł����ꍇ <BR>
     * <BR>
     * �ۗL���Y���R�[�h���X�V����B <BR>
     * ���ڂ̐ݒ���@@�́ADB�X�V�d�l�Q�ƁB <BR>
     * �������o�^���ƒ���������ňقȂ�B <BR>
     * �o�^��������̔��f�́A�����P��.��������敪�ɂčs���B <BR>
     * <BR>
     * @@param l_aioOrderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_blnSellPossible - is���t�\
     * @@throws WEB3BaseException
     * @@roseuid 416C9A4B0190
     */
    protected void updateAsset(AioOrderUnit l_aioOrderUnit, boolean l_blnSellPossible) 
        throws WEB3BaseException
    {        
        String STR_METHOD_NAME = "updateAsset(AioOrderUnit l_aioOrderUnit, boolean l_blnSellPossible)";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�ڋq�I�u�W�F�N�g���擾����B
		//�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B
		//[����] 
		//����ID�F �����P��.����ID 
		FinApp l_finApp = 
			(FinApp)Services.getService(FinApp.class);
		AccountManager l_accountManager = GtlUtils.getAccountManager();  
           
		MainAccount l_mainAccount;
		try
		{
			l_mainAccount = l_accountManager.getMainAccount(l_aioOrderUnit.getAccountId());
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

		//�Q�j�⏕�����I�u�W�F�N�g���擾����B
		//�ڋq.getSubAccount()���R�[������B
		//[����] 
		//�⏕�����^�C�v�F 
		//�ڋq.is�M�p�����J��()==true�̏ꍇ�A�h�ۏ؋������h 
		//�ڋq.is�M�p�����J��()==false�̏ꍇ�A�h�a��������h 
        
		SubAccountTypeEnum l_subAccountType = null;        
		WEB3GentradeMainAccount l_gentradeMainAcc = 
			(WEB3GentradeMainAccount)l_mainAccount;
        
		boolean l_blnMarginAccount = 
			l_gentradeMainAcc.isMarginAccountEstablished(
				WEB3GentradeRepaymentDivDef.DEFAULT);
        
		if (l_blnMarginAccount)
		{
			log.debug("�ڋq.is�M�p�����J��()==true�̏ꍇ");
			l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
		}
		else
		{
			log.debug("�ڋq.is�M�p�����J��()==false�̏ꍇ");
			l_subAccountType = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
		}                             

		WEB3GentradeSubAccount l_subAccount;
		try
		{
			l_subAccount = 
				(WEB3GentradeSubAccount)l_mainAccount.getSubAccount(l_subAccountType);
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

        //�R�j�ΏۂƂȂ�ۗL���Y���R�[�h���擾����B
        //[�擾����] 
        //����ID = ����.�����P��.����ID 
        //�⏕����ID = �⏕����.�⏕����ID 
        //����ID = ����.�����P��.����ID 
        //�ŋ敪 = ����.�����P��.�ŋ敪 
        //�~�j���敪 = ����.�����P��.�~�j���敪 
        AioOrderUnitParams l_orderUnitParams = 
            (AioOrderUnitParams)l_aioOrderUnit.getDataSourceObject();
        List l_lisAssetRows = null;
        log.debug("�����P��.����ID = " + l_orderUnitParams.getAccountId());
		log.debug("�⏕����.�⏕����ID = " + l_subAccount.getSubAccountId());
        log.debug("�����P��.����ID = " + l_orderUnitParams.getProductId());
        log.debug("�����P��.�ŋ敪 = " + l_orderUnitParams.getTaxType());
        log.debug("�����P��.�~�j���敪 = " + l_orderUnitParams.getMiniStockDiv());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAssetRows = l_queryProcessor.doFindAllQuery(
                AssetRow.TYPE,
                "account_id = ? and sub_account_id = ? and " +
                "product_id = ? and tax_type = ? and " +
                "mini_stock_div = ?",
                null,
                new Object[] {
                    new Long(l_aioOrderUnit.getAccountId()), 
                    new Long(l_subAccount.getSubAccountId()), 
                    new Long(l_orderUnitParams.getProductId()), 
                    l_orderUnitParams.getTaxType(), 
                    l_orderUnitParams.getMiniStockDiv()
                    });
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.debug("�ۗL���Y���R�[�h���擾Size = " + l_lisAssetRows.size());
        
        //�S�j�ۗL���Y���R�[�h���X�V�i�o�^�j����B 
        //�S�|�P�j�ۗL���Y���R�[�h���擾�ł��Ȃ������ꍇ 
        //�ۗL���Y���R�[�h��o�^����B 
        //���ڂ̐ݒ���@@�́ADB�X�V�d�l�Q�ƁB
        if (l_lisAssetRows.size() == 0)
        {
            log.debug("�ۗL���Y���R�[�h���擾�ł��Ȃ������ꍇ");
            AssetParams l_assetParams = new AssetParams();            
            //�ۗL���YParams.�����h�c = �����P��.����ID
            l_assetParams.setAccountId(l_orderUnitParams.getAccountId());
			//�ۗL���YParams.�⏕����ID = �⏕����.�⏕����ID
			l_assetParams.setSubAccountId(l_subAccount.getSubAccountId());
            //�ۗL���YParams.�����^�C�v = �����P��.�����^�C�v
            l_assetParams.setProductType(l_orderUnitParams.getProductType());
            //is���t�\ == true �̏ꍇ�A
            if (l_blnSellPossible)
            {
                //�ۗL���YParams.���� = �����P��.����
                l_assetParams.setQuantity(l_orderUnitParams.getQuantity());
                //�ۗL���YParams.���t�s�\���� = 0
                l_assetParams.setQuantityCannotSell(0);
            }
            //is���t�\ == false �̏ꍇ
            else
            {
                //�ۗL���YParams.���� = 0
                l_assetParams.setQuantity(0);
                //�ۗL���YParams.���t�s�\���� = �����P��.����
                l_assetParams.setQuantityCannotSell(l_orderUnitParams.getQuantity());
            }
            //�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j = �����P��.����
            l_assetParams.setQuantityForBookValue(l_orderUnitParams.getQuantity());
            //�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j = 0
            l_assetParams.setBookValue(0);
            //�ۗL���YParams.���͕뉿�P�� = null
            l_assetParams.setInputBookValue(null);
            //�ۗL���YParams.�뉿�P�����͓��� = null
            l_assetParams.setInputTimestamp(null);
            //�ۗL���YParams.���`������ = 0
            l_assetParams.setSetupFee(0);
            //�ۗL���YParams.���`����������� = 0
            l_assetParams.setSetupFeeTax(0);
            //�ۗL���YParams.�����Ǘ��� = 0
            l_assetParams.setManagementFee(0);
            //�ۗL���YParams.�����Ǘ������� = 0
            l_assetParams.setManagementFeeTax(0);
            //�ۗL���YParams.�����h�c = �����P��.�����h�c
            l_assetParams.setProductId(l_orderUnitParams.getProductId());
            //�ۗL���YParams.�ŋ敪 = �����P��.�ŋ敪
            l_assetParams.setTaxType(l_orderUnitParams.getTaxType());
            //�ۗL���YParams.�~�j���敪 = �����P��.�~�j���敪
            l_assetParams.setMiniStockDiv(l_orderUnitParams.getMiniStockDiv());
            //�ۗL���YParams.���z�� = null
            l_assetParams.setProfitDistribution(null);
            //�ۗL���YParams.30�����o�ߎc������ = null
            l_assetParams.setCountBeforePenalty(null);   
            //�ۗL���YParams.�쐬���t = ���ݓ���
            l_assetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //�ۗL���YParams.�X�V���t = ���ݓ���
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            try
            {
                long l_lngAssetId = AssetDao.newPkValue();
                l_assetParams.setAssetId(l_lngAssetId);
                WEB3DataAccessUtility.insertRow(l_assetParams);
            }
            catch (DataException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //�S�|�Q�j�ۗL���Y���R�[�h���擾�ł����ꍇ 
        //�ۗL���Y���R�[�h���X�V����B 
        //���ڂ̐ݒ���@@�́ADB�X�V�d�l�Q�ƁB 
        //�������o�^���ƒ���������ňقȂ�B 
        //�o�^��������̔��f�́A�����P��.��������敪�ɂčs���B

        AioOrderUnitParams l_aioOrderUnitParams = 
            (AioOrderUnitParams) l_aioOrderUnit.getDataSourceObject();
        AssetRow l_assetRow = null;
        AssetParams l_assetParams = null;
        
        if (l_lisAssetRows.size() > 0)
        {
            log.debug("�ۗL���Y���R�[�h���擾�ł����ꍇ");
            log.debug("�����P��.��������敪 = " + l_aioOrderUnitParams.getCancelType());
            
            for (int i = 0; i < l_lisAssetRows.size(); i++)
            {
                l_assetRow = (AssetRow)l_lisAssetRows.get(i);
                l_assetParams = new AssetParams(l_assetRow);

                //�����o�^��
                if (WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(
                        l_aioOrderUnitParams.getCancelType()))
                {
                    log.debug("�����o�^��");
                    //==========================================================
                    //���R�[�h�X�V���̐��ʌv�Z���@@
                    //�P�j�O��
                    //�X�V�O�ۗ̕L���Y.����(1)
                    double l_dblBefUpdQuantity = l_assetParams.getQuantity();
                    
                    //�X�V�O�ۗ̕L���Y.���t�s�\����(2)
                    double l_dblBefUpdCannotSell = l_assetParams.getQuantityCannotSell();
                    
                    //�X�V��ۗ̕L���Y.����(3)
                    double l_dblAftUpdQuantity = 0;
                    
                    //�X�V��ۗ̕L���Y.���t�s�\����(4)
                    double l_dblAftUpdCannotSell = 0;
                    
                    //�Q�j�v�Z���@@
                    //�Q�|�P�j���ɂ̏ꍇ
                    if (AssetTransferTypeEnum.CASH_IN.equals(
                            l_aioOrderUnitParams.getTransferType()))
                    {
                        log.debug("���ɂ̏ꍇ");

                        //----���ʂ̃Z�[�g
                        //is���t�\ == true �̏ꍇ
                        //(3)�� (1) �{ �����P��.����
                        //is���t�\ == false �̏ꍇ
                        //(3)�� (1)
                        l_dblAftUpdQuantity = l_blnSellPossible ?
                            (l_dblBefUpdQuantity + l_aioOrderUnitParams.getQuantity())
                            :l_dblBefUpdQuantity;

                        //----���t�s�\���ʂ̃Z�[�g
                        //is���t�\ == true �̏ꍇ
                        //(4)�� (2)
                        //is���t�\ == false �̏ꍇ
                        //(4)�� (2) + �����P��.����
                        l_dblAftUpdCannotSell = l_blnSellPossible ? 
                            l_dblBefUpdCannotSell
                            :(l_dblBefUpdCannotSell + l_aioOrderUnitParams.getQuantity());
                    }
                    //�Q�|�Q�j�o�ɂ̏ꍇ
                    else
                    {
                        log.debug("�o�ɂ̏ꍇ");
                        
                        //is���t�\ == true �̏ꍇ
                        if (l_blnSellPossible)
                        {
                            //(3) �� (1)�{ �����P��.���� �Ƃ���B
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity + l_aioOrderUnitParams.getQuantity();
                            //(4) �� (2)
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell;
                            //(3) �� 0 �̏ꍇ
                            if (l_dblAftUpdQuantity < 0)
                            {
                                //(A) �� (3) �Ƃ���
                                double l_dblQuantityA = l_dblAftUpdQuantity;
                                //(3) �� 0
                                //(4) �� (2) �{ (A)
                                l_dblAftUpdQuantity = 0;
                                l_dblAftUpdCannotSell = l_dblBefUpdCannotSell + l_dblQuantityA;
                            }
                        }
                        //is���t�\ == false �̏ꍇ
                        else
                        {
                            //(3) �� (1)
                            l_dblAftUpdQuantity = l_dblBefUpdQuantity;
                            //(4) �� (2) �{ �����P��.���� �Ƃ���B
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell + l_aioOrderUnitParams.getQuantity();
                        }
                    }                
                    //==========================================================
                    
                    //�X�V�O�̕뉿(�뉿�P���v�Z)
                    double l_dblBefUpdBookValue = 
                        l_assetParams.getBookValue();
    
                    //�X�V�O�̐���(�뉿�P���v�Z�p)
                    double l_dblBefUpdQuantiyBookValue = 
                        l_assetParams.getQuantityForBookValue();
                    
                    //�ۗL���YParams.���� = �V�[�g�u���ʌv�Z�v�Q��
                    l_assetParams.setQuantity(l_dblAftUpdQuantity);
                    log.debug("�ۗL���YParams.���� = " + l_assetParams.getQuantity());
                    
                    //�ۗL���YParams.���t�s�\���� = �V�[�g�u���ʌv�Z�v�Q��
                    l_assetParams.setQuantityCannotSell(l_dblAftUpdCannotSell);
                    log.debug("�ۗL���YParams.���t�s�\���� = " + 
                            l_assetParams.getQuantityCannotSell());
                    
                    //�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j = ���ʁ{���t�s�\����
                    l_assetParams.setQuantityForBookValue(
                        l_assetParams.getQuantity() + 
                        l_assetParams.getQuantityCannotSell());
                    log.debug("�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j = " + 
                            l_assetParams.getQuantityForBookValue());
                    
                    //�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j = 
                    //		(1)�X�V�O�뉿�P���̌v�Z
                    //         �X�V�O�̕뉿(�뉿�P���v�Z�p) �� �X�V�O�̐���(�뉿�P���v�Z�p)
                    //		(2)�뉿�v�Z
                    //         �X�V��̐���(�뉿�P���v�Z�p) �~ (1)�̕뉿�P��
                    double l_dblBookValue = 0.0;
                    if (l_dblBefUpdQuantiyBookValue != 0.0)
                    {
                        l_dblBookValue =  
                            l_dblBefUpdBookValue / l_dblBefUpdQuantiyBookValue;                        
                    }
                    double l_dblAftUpdBookValue = 
                        l_assetParams.getQuantityForBookValue() * l_dblBookValue;
                    l_assetParams.setBookValue(l_dblAftUpdBookValue);
                    
                    log.debug("�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j = " + 
                            l_assetParams.getBookValue());
                    
                    //�ۗL���YParams.�X�V���t = ���ݓ���
                    l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                }
                //���������
                else if (WEB3ModifyCancelTypeDef.CANCELED.equals(
                        l_aioOrderUnitParams.getCancelType()))
                {
                    log.debug("���������");
                    //==========================================================
                    //���R�[�h�X�V���̐��ʌv�Z���@@
                    //�P�j�O��
                    //�X�V�O�ۗ̕L���Y.����(1)
                    double l_dblBefUpdQuantity = l_assetParams.getQuantity();
                    
                    //�X�V�O�ۗ̕L���Y.���t�s�\����(2)
                    double l_dblBefUpdCannotSell = l_assetParams.getQuantityCannotSell();
                    
                    //�X�V��ۗ̕L���Y.����(3)
                    double l_dblAftUpdQuantity = 0;
                    
                    //�X�V��ۗ̕L���Y.���t�s�\����(4)
                    double l_dblAftUpdCannotSell = 0;
                    
                    //�Q�j�v�Z���@@
                    //�Q�|�P�j���Ɏ���̏ꍇ
                    if (AssetTransferTypeEnum.CASH_IN.equals(
                            l_aioOrderUnitParams.getTransferType()))
                    {
                        log.debug("���Ɏ���̏ꍇ");
                        if (l_blnSellPossible)
                        {
                            //(3)�� (1) �| �����P��.����
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity - l_aioOrderUnitParams.getQuantity();
                            //(4) �� (2)
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell;
                        }
                        else
                        {
                            //(3)�� (1)
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity;
                            //(4) �� (2) �| �����P��.����
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell - l_aioOrderUnitParams.getQuantity();
                        }
                    }
                    //�Q�|�Q�j�o�Ɏ���̏ꍇ
                    else
                    {                
                        log.debug("�o�Ɏ���̏ꍇ");
                        if (l_blnSellPossible)
                        {
                            //(3)�� (1) �| �����P��.����
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity - l_aioOrderUnitParams.getQuantity();
                            //(4) �� (2)
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell;
                        }
                        else
                        {
                            //(3)�� (1)
                            l_dblAftUpdQuantity = 
                                l_dblBefUpdQuantity;
                            //(4) �� (2) �| �����P��.����
                            l_dblAftUpdCannotSell = l_dblBefUpdCannotSell - l_aioOrderUnitParams.getQuantity();
                        }
                    }                
                    //==========================================================
                    
                    //�X�V�O�̕뉿(�뉿�P���v�Z�p)
                    double l_dblBefUpdBookValue = 
                        l_assetParams.getBookValue();
    
                    //�X�V�O�̐���(�뉿�P���v�Z�p)
                    double l_dblBefUpdQuantiyBookValue = 
                        l_assetParams.getQuantityForBookValue();
    
                    //�ۗL���YParams.���� = �V�[�g�u���ʌv�Z�v�Q��
                    l_assetParams.setQuantity(l_dblAftUpdQuantity);
                    log.debug("�ۗL���YParams.���� = " + l_assetParams.getQuantity());
                    
                    //�ۗL���YParams.���t�s�\���� = �V�[�g�u���ʌv�Z�v�Q��
                    l_assetParams.setQuantityCannotSell(l_dblAftUpdCannotSell);
                    log.debug("�ۗL���YParams.���t�s�\���� = " + 
                            l_assetParams.getQuantityCannotSell());
                    
                    //�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j = ���ʁ{���t�s�\����
                    l_assetParams.setQuantityForBookValue(
                        l_assetParams.getQuantity() + 
                        l_assetParams.getQuantityCannotSell());
                    log.debug("�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j = " + 
                            l_assetParams.getQuantityForBookValue());
                    
                    //�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j = 
                    //		(1)�X�V�O�뉿�P���̌v�Z
                    //         �X�V�O�̕뉿(�뉿�P���v�Z�p) �� �X�V�O�̐���(�뉿�P���v�Z�p)
                    //		(2)�뉿�v�Z
                    //         �X�V��̐���(�뉿�P���v�Z�p) �~ (1)�̕뉿�P��
                    double l_dblBookValue = 0.0;
                    if (l_dblBefUpdQuantiyBookValue != 0.0)
                    {
                        l_dblBookValue =  
                            l_dblBefUpdBookValue / l_dblBefUpdQuantiyBookValue;                        
                    }
                    double l_dblAftUpdBookValue = 
                        l_assetParams.getQuantityForBookValue() * l_dblBookValue;
                    l_assetParams.setBookValue(l_dblAftUpdBookValue);
                    
                    log.debug("�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j = " + 
                            l_assetParams.getBookValue());
                    
                    //�ۗL���YParams.�X�V���t = ���ݓ���
                    l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                }
    
                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_assetParams);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DataQueryException Error ", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DataNetworkException Error In", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * (is���t�\)<BR>
     * �P�j������������U�փL���[Params.���i�敪 == �h���h �̏ꍇ <BR>
     * <BR>
     * �P�|�P�j <BR>
     *    ������������U�փL���[Params.�~�j���敪 == �h�~�j���h <BR>
     *    �̏ꍇ�Atrue ��ԋp����B <BR>
     * <BR>
     * �P�|�Q�j <BR>
     *    ������������U�փL���[Params.�ۊǋ敪 == �h�@@�\�h and   <BR>
     *    �i������������U�փL���[Params.���`�敪 == �h�P�ʖ{�l�h or �h�P�ʑ��l�h�j <BR>
     *    �̏ꍇ�Atrue ��ԋp����B <BR>
     * <BR>
     * �P�|�R�j <BR>
     *    ����ȊO�̏ꍇ�Afalse ��ԋp����B <BR>
     * <BR>
     * �Q�j������������U�փL���[Params.���i�敪 == �h���h �̏ꍇ <BR>
     * <BR>
     * �Q�|�P�j�����̎擾 <BR>
     * <BR>
     * �Q�|�P�|�P�j�،���ЃI�u�W�F�N�g�̎擾 <BR>
     * <BR>
     *    �g���A�J�E���g�}�l�[�W��.getInstitution()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h�F ������������U�փL���[Params.�،���ЃR�[�h <BR>
     * <BR>
     * �Q�|�P�|�Q�j�����I�u�W�F�N�g�̎擾 <BR>
     * <BR>
     *    AIO�v���_�N�g�}�l�[�W��.get����()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    �����^�C�v�F �����^�C�v.�h���h <BR>
     *    �����R�[�h�F ������������U�փL���[Params.�����R�[�h <BR>
     *    �،���ЁF �g���A�J�E���g�}�l�[�W��.getInstitution()�̖߂�l <BR>
     * <BR>
     * �Q�|�Q�jCB�i����.��ʃR�[�h == �h�]���ЍE���čh�j�̏ꍇ <BR>
     * <BR>
     *    ������������U�փL���[Params.�ۊǋ敪 == �h�i�W�j�h or �h�@@�\�h <BR>
     *    �̏ꍇ�Atrue ��ԋp����B <BR>
     * <BR>
     * �Q�|�R�j����ȊO�̍��i����.��ʃR�[�h != �h�]���ЍE���čh�j�̏ꍇ <BR>
     * <BR>
     *    ������������U�փL���[Params.�ۊǋ敪 == �h�i�W�j�h <BR>
     *    �̏ꍇ�Atrue ��ԋp����B <BR>
     * <BR>
     * �R�j������������U�փL���[Params.���i�敪 == �h���M�h�̏ꍇ <BR>
     * <BR>
     *    ������������U�փL���[Params.�ۊǋ敪 == �h�i�W�j�h <BR>
     *    �̏ꍇ�Atrue ��ԋp����B <BR>
     * <BR>
     * @@param l_params - ������������U�փL���[
     * @@return AioOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    public boolean isSellPossible(HostSpsecTransNotifyParams l_params) 
        throws WEB3BaseException
    {   
        String STR_METHOD_NAME = 
            "isSellPossible(HostSpsecTransNotifyParams l_params)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j������������U�փL���[Params.���i�敪 == �h���h �̏ꍇ
        if (WEB3AioHostSpsecCommodityDef.EQUITY.equals(l_params.getCommodityDiv()))
        {            
            //�P�|�P�j 
            //������������U�փL���[Params.�~�j���敪 == �h�~�j���h 
            //�̏ꍇ�Atrue ��ԋp����B
            if (WEB3MiniStockDivDef.MINI_STOCK.equals(l_params.getMiniStockDiv()))
            {
                log.exiting(STR_METHOD_NAME);       
                return true;
            }            
            //�P�|�Q�j 
            //������������U�փL���[Params.�ۊǋ敪 == �h�@@�\�h and   
            //�i������������U�փL���[Params.���`�敪 == �h�P�ʖ{�l�h or �h�P�ʑ��l�h�j 
            //�̏ꍇ�Atrue ��ԋp����B
            else if (WEB3CustdyDiv.INSTITUTE_SAVE.equals(l_params.getCustodyDiv()) &&
                    (WEB3NameMethodDivDef.COMPANY_SELF.equals(l_params.getTitleDiv()) ||
                    WEB3NameMethodDivDef.COMPANY_OTHER.equals(l_params.getTitleDiv())))
            {
                log.exiting(STR_METHOD_NAME);       
                return true;
            }            
            //�P�|�R�j 
            //����ȊO�̏ꍇ�Afalse ��ԋp����B
            else
            {
                log.exiting(STR_METHOD_NAME);       
                return false;
            }        
        }
        //�Q�j������������U�փL���[Params.���i�敪 == �h���h �̏ꍇ 
        else if (WEB3AioHostSpsecCommodityDef.BOND.equals(l_params.getCommodityDiv()))
        {

            //�Q�|�P�j�����̎擾     
            //�Q�|�P�|�P�j�،���ЃI�u�W�F�N�g�̎擾 
            //   �g���A�J�E���g�}�l�[�W��.getInstitution()���R�[������B 
            //   [����] 
            //   �،���ЃR�[�h�F ������������U�փL���[Params.�،���ЃR�[�h 
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);    
            //�g���A�J�E���g�}�l�[�W���擾���� 
            WEB3GentradeAccountManager l_accountManager =  
                (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
            
            Institution l_institution = null;
            try
            {
                l_institution = l_accountManager.getInstitution(
                        l_params.getInstitutionCode());                
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }     
            //�Q�|�P�|�Q�j�����I�u�W�F�N�g�̎擾 
            //AIO�v���_�N�g�}�l�[�W��.get����()���R�[������B 
            //[����] 
            //�����^�C�v�F �����^�C�v.�h���h 
            //�����R�[�h�F ������������U�փL���[Params.�����R�[�h 
            //�،���ЁF �g���A�J�E���g�}�l�[�W��.getInstitution()�̖߂�l 
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
            WEB3AioProductManager l_aioProductManager = 
                (WEB3AioProductManager)l_tradingModule.getProductManager();
            
            WEB3BondProduct l_product = (WEB3BondProduct)
                l_aioProductManager.getProduct(
                    ProductTypeEnum.BOND,
                    l_params.getProductCode(), 
                    l_institution);
            
            BondProductRow l_bondProductRow = 
                (BondProductRow)l_product.getDataSourceObject();
            
            //�Q�|�Q�jCB�i����.��ʃR�[�h == �h�]���ЍE���čh�j�̏ꍇ 
            if (WEB3BondCategCodeDef.CONVERSION_COMPANY_BOND_WARRANT_BOND.equals(
                    l_bondProductRow.getBondCategCode()))
            {
                //������������U�փL���[Params.�ۊǋ敪 == �h�i�W�j�h or �h�@@�\�h 
                //�̏ꍇ�Atrue ��ԋp����B            
                if (WEB3CustdyDiv.INTERNAL_SAVE.equals(l_params.getCustodyDiv()) || 
                    WEB3CustdyDiv.INSTITUTE_SAVE.equals(l_params.getCustodyDiv()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
            //�Q�|�R�j����ȊO�̍��i����.��ʃR�[�h != �h�]���ЍE���čh�j�̏ꍇ 
            else
            {
                //������������U�փL���[Params.�ۊǋ敪 == �h�i�W�j�h 
                //�̏ꍇ�Atrue ��ԋp����B
                if (WEB3CustdyDiv.INTERNAL_SAVE.equals(l_params.getCustodyDiv()))
                {
                    log.exiting(STR_METHOD_NAME);       
                    return true;
                }
            }
        }        
        //�R�j������������U�փL���[Params.���i�敪 == �h���M�h�̏ꍇ 
        else if (WEB3AioHostSpsecCommodityDef.MUTUAL_FUND.equals(l_params.getCommodityDiv()))
        {
            //������������U�փL���[Params.�ۊǋ敪 == �h�i�W�j�h 
            //�̏ꍇ�Atrue ��ԋp����B 
            if (WEB3CustdyDiv.INTERNAL_SAVE.equals(l_params.getCustodyDiv()))
            {
                log.exiting(STR_METHOD_NAME);       
                return true;
            }
        }
        return false;
    }
}
@
