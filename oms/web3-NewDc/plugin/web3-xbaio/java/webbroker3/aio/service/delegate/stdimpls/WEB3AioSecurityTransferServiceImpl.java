head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�փT�[�r�XImpl(WEB3AioSecurityTransferServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 ���z (���u) �V�K�쐬
Revesion History : 2007/04/06 �����q (���u) ���f��No.719�Ή�
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.aio.WEB3AioBizLogicProvider;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.WEB3AioSecurityTransferOrderUpdateInterceptor;
import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.aio.message.WEB3AioSecurityTransferCompleteRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferCompleteResponse;
import webbroker3.aio.message.WEB3AioSecurityTransferConfirmRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��U�փT�[�r�XImpl)<BR>
 * �،��U�փT�[�r�X�����N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferServiceImpl extends WEB3ClientRequestService implements WEB3AioSecurityTransferService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferServiceImpl.class); 
    
    /**
     * �،��U�փT�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��validate����()�A<BR>
     * �܂���submit����()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4157799D00CA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        //���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́Astart����()
        if (l_request instanceof WEB3AioSecurityTransferConfirmRequest)
        {
            l_response = 
                validateOrder((WEB3AioSecurityTransferConfirmRequest)l_request);   
        }
        else if (l_request instanceof WEB3AioSecurityTransferCompleteRequest)
        {
            l_response =
                submitOrder((WEB3AioSecurityTransferCompleteRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (validate����)<BR>
     * �U�֒����̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��U�ցjvalidate�����v �Q��
     * <BR>
     * ========================================================<BR>
     *     �V�[�P���X�}(�u�i�،��U�ցjvalidate�����v �v<BR>
     *     (validate����) <BR>
     *     6) is�M�p�����J��(String)<BR>
     *     �A�C�e���̒�`<BR>
     *     �M�p�������J�݂��Ă��邩�̃`�F�b�N���s���B<BR> 
     *     [����] <BR>
     *     �ٍϋ敪�F 0�i�w��Ȃ�)<BR>
     * <BR>
     *     �߂�l=false �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00747<BR> 
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *     �V�[�P���X�}(�u�i�،��U�ցjvalidate�����v �v<BR>
     *     (validate����) <BR> 
     *     10) calc�U�։\����(long, ProductTypeEnum, long, TaxTypeEnum, String)<BR>
     *     �A�C�e���̒�`<BR>
     *     �U�։\���ʂ��Z�o����B<BR> 
     *     [����]<BR> 
     *     ����ID�F �iget�⏕����()�̖߂�l�j.getAccountId()�̖߂�l<BR> 
     *     �����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v <BR>
     *     ����ID�F �iget����()�̖߂�l�j.����ID <BR>
     *     �ŋ敪�F <BR>
     *     ���N�G�X�g�f�[�^.�����敪=null�̏ꍇ�F 0�i���̑��j<BR> 
     *     ���N�G�X�g�f�[�^.�����敪=�h��ʁh�̏ꍇ�F 1�i��ʁj <BR>
     *     ���N�G�X�g�f�[�^.�����敪=�h����h�̏ꍇ�F �ڋq.�ŋ敪 <BR>
     *     �a��敪�F ���N�G�X�g�f�[�^.�U�֌��a��敪 <BR>
     * <BR>
     *     �U�։\���ʎ擾<BR>
     *     ���N�G�X�g�f�[�^.�U�֐��� > �擾���ʁicalc�U�։\����()�̖߂�l�j �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01305<BR> 
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *     �V�[�P���X�}(�u�i�،��U�ցjvalidate�����v �v<BR>
     *     (validate����) <BR> 
     *     18) validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],<BR>
     *     �������e : Object[], ������� :OrderTypeEnum, �]�͍X�V�t���O : boolean)<BR>
     *     �A�C�e���̒�`<BR>
     *     ��K���`�F�b�N���s���B<BR>
     *     [����] <BR>
     *     �⏕�����F �ڋq.getSubAccount(�⏕�����^�C�v=2�i�ۏ؋������j)�̖߂�l<BR> 
     *     �������e�C���^�Z�v�^�F �U�֌������̃C���^�Z�v�^�ƐU�֐撍���̃C���^�Z�v�^�̔z��<BR> 
     *     �������e�F �U�֌������̒������e�ƐU�֐撍���̒������e�̔z�� <BR>
     *     ������ʁF<BR> 
     *     ���N�G�X�g�f�[�^.�a��敪 = 1�i�ی�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j<BR> 
     *     ���N�G�X�g�f�[�^.�a��敪 = 2�i��p�j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j <BR>
     *     �]�͍X�V�t���O�F false<BR>
     * <BR>
     *     ��K���`�F�b�N<BR>
     *     �߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[��<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01306<BR> 
     * <BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioSecurityTransferConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41577A360109
     */
    protected WEB3AioSecurityTransferConfirmResponse validateOrder(WEB3AioSecurityTransferConfirmRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "validateOrder(WEB3AioSecurityTransferConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j 
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //FinApp, TradingModule, OrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4 �ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //1.5 �M�p�������J�݂��Ă��邩�̃`�F�b�N���s���B 
        //[����] 
        //�ٍϋ敪�F 0�i�w��Ȃ��j 
        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //is�M�p�����J��(String)�̖߂�l=false �̏ꍇ�A��O���X���[����B
        //   class: WEB3BusinessLayerException
        //     tag: BUSINESS_ERROR_00747
        if (l_booisMarginAccountEstablished == false)
        {
            log.debug("is�M�p�����J��(String)�̖߂�l=false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                this.getClass().getName() + "." + l_strMethodName,
                "is�M�p�����J��(String)�̖߂�l=false");            
        }
        
        //1.6 ���������擾����B 
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7 �،��U�֒�����1���̏���񐔂𒴂��ĂȂ������`�F�b�N����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        l_orderManager.validateInstitutionTransferPossibleCount(
            l_subAccount,
            l_datOrderBizDate);
            
        //1.8 �����I�u�W�F�N�g���擾����B 
        //[����] 
        //�����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v 
        String l_strInstrumentType = l_request.instrumentsType;
        
        ProductTypeEnum l_enumInstrumentType;    
        if (ProductTypeEnum.EQUITY.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.EQUITY;
        }
        else if (ProductTypeEnum.BOND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.BOND;
        }
        else if (ProductTypeEnum.MUTUAL_FUND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.MUTUAL_FUND;
        }
        else 
        {
            l_enumInstrumentType = ProductTypeEnum.FOREIGN_EQUITY;
        }
        
        //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
        String l_strProductCode = l_request.productCode;
        //�،���ЁF �⏕����.getInsutitution()�̖߂�l 
        Institution l_institution = l_subAccount.getInstitution(); 
        
        //AIO�v���_�N�g�}�l�[�W�����擾 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
       
        Product l_product = l_productManager.getProduct(
            l_enumInstrumentType, 
            l_strProductCode, 
            l_institution);

        
        // validate�،��U�։\�P��
        // ���N�G�X�g�f�[�^�D���i�^�C�v�ɑΉ���������^�C�v="���M"�� 
        //�iget����()�̖߂�l�j.���M�����}�X�^.���͒P��="2"�i1���j�̏ꍇ�A 
        // �U�֐��ʂ̒P�ʃ`�F�b�N���s���B  
        // �P�j���N�G�X�g�f�[�^�D���i�^�C�v�ɑΉ�����
        // �����^�C�v == "���M" ���� ���M�����}�X�^.���͒P�� == 2�i1���j�̏ꍇ
        // (1)����.�U�֐��� / 10000 �̏�]��0�łȂ��ꍇ
        if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
        {
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_product.getDataSourceObject();
            if ( WEB3InputUnitDef.TEN_THOUSAND.equals(l_mutualFundProductRow.getInputUnit()))
            {
                this.validateSecurityTransferPossibleUnit(l_request.transferQuantity);
            }
        }

        //1.9 �U�։\���ʂ��Z�o����B 
        //[����] 
        //a> ����ID�F �iget�⏕����()�̖߂�l�j.getAccountId()�̖߂�l 
        long l_lngAccountId = l_subAccount.getAccountId();
        
        //b> �����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v ---l_enumInstrumentType in 1.8
        
        //c> ����ID�F �iget����()�̖߂�l�j.����ID 
        long l_lngProductId = l_product.getProductId();
        
        //d> �ŋ敪�F 
        TaxTypeEnum l_taxTypeEnum;
        
        //���N�G�X�g�f�[�^.�����敪=null�̏ꍇ�F 0�i���̑��j
        if (l_request.taxType == null)
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;            
        }
        //���N�G�X�g�f�[�^.�����敪=�h��ʁh�̏ꍇ�F 1�i��ʁj 
        else if(WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;    
        }
        //���N�G�X�g�f�[�^.�����敪=�h����h�̏ꍇ�F2�i����j
        else
        {            
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        
        //e> �a��敪�F ���N�G�X�g�f�[�^.�U�֌��a��敪 
        String l_strDepositDiv = l_request.depositDiv;
        
        //f> get WEB3AioBizLogicProvider
        WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
            (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        double l_dblTransPossibleAmount =
            l_web3AioBizLogicProvider.calcTransPossibleAmount(
                l_lngAccountId,
                l_enumInstrumentType,
                l_lngProductId,
                l_taxTypeEnum,
                l_strDepositDiv);
                
        //���N�G�X�g�f�[�^.�U�֐��� > �擾���ʁicalc�U�։\����()�̖߂�l�j �̏ꍇ�A��O���X���[����B
        //     class: WEB3BusinessLayerException
        //     tag:   BUSINESS_ERROR_01305
        if (Double.parseDouble(l_request.transferQuantity) > l_dblTransPossibleAmount)
        {
            log.debug("�U�֐��ʂ́A�擾���ʂ��傫���ł��B"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01305,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֐���[" + l_request.transferQuantity + "] > �擾���ʁicalc�U�։\����()�̖߂�l�j["
                + l_dblTransPossibleAmount + "]");       
        }
        
        //1.10 �㗝���͎҃I�u�W�F�N�g���擾����B 
        Trader l_trador = this.getTrader();
        
        //***************start of �U�֌�����********************
        
        //1.11 ���o���������e�C���X�^���X�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l --l_trador
        //������ʁF 
        //���N�G�X�g�f�[�^.�a��敪 = 1�i�ی�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j 
        //���N�G�X�g�f�[�^.�a��敪 = 2�i��p�j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j 
        //�U�փ^�C�v�F 2�i�o���j--l_transferTypeEnumOut
        //����ID�F ����.����ID --l_lngProductId
        //���z�F ���N�G�X�g�f�[�^.�U�֐��� �~ -1 --l_dblTransferQuantityOut
        //�L�q�F null 
        //�U�֗\����F get������()�̖߂�l --l_datOrderBizDate
        //���ϋ@@��ID�F null 
        //����ID�F null 

        //a> �������
        OrderTypeEnum l_orderTypeEnum;
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_request.depositDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES;     
        }
        else 
        {
            l_orderTypeEnum = OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT;    
        }
        
        //b> �U�փ^�C�v
        AssetTransferTypeEnum l_transferTypeEnumOut = AssetTransferTypeEnum.CASH_OUT;
        
        //c> ���z
        double l_dblTransferQuantityOut = 
            (Double.parseDouble(l_request.transferQuantity)) * (-1);
        
        //new 
        WEB3AioNewOrderSpec l_web3AioNewOrderSpecOut =
            new WEB3AioNewOrderSpec(
                l_trador,
                l_orderTypeEnum,
                l_transferTypeEnumOut,
                l_lngProductId,
                l_dblTransferQuantityOut,
                null,
                l_datOrderBizDate,
                null,
                null);
                
        //1.12 �،��U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g 
        WEB3AioSecurityTransferOrderUpdateInterceptor l_orderUpdateInterceptorOut =
            new WEB3AioSecurityTransferOrderUpdateInterceptor(l_web3AioNewOrderSpecOut);
            
        //1.13 �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //a> �C���^�Z�v�^.������ = get������()�̖߂�l
        l_orderUpdateInterceptorOut.setOrderBizDat(l_datOrderBizDate);
        
        //b> �C���^�Z�v�^.��n�� = get������()�̖߂�l
        l_orderUpdateInterceptorOut.setDeliveryDate(l_datOrderBizDate);
        
        //c> �C���^�Z�v�^.�ŋ敪 = �i�ȉ��̂Ƃ���j
        //   ���N�G�X�g�f�[�^.�����敪=�h��ʁh�̏ꍇ�F 1�i��ʁj
        //   ���N�G�X�g�f�[�^.�����敪=�h����h�̏ꍇ�F 2�i����j
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorOut.setTaxType(TaxTypeEnum.NORMAL);    
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorOut.setTaxType(TaxTypeEnum.SPECIAL);    
        }
        
        //d> �C���^�Z�v�^.���ʃR�[�h = null    
        l_orderUpdateInterceptorOut.setOrderRequestNumber(null);
        
        //***************start of �U�֐撍��********************
        
        //1.14 ���o���������e�C���X�^���X�𐶐�����B
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l --l_trador
        //������ʁF --l_orderTypeEnum
        //���N�G�X�g�f�[�^.�a��敪 = 1�i�ی�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j 
        //���N�G�X�g�f�[�^.�a��敪 = 2�i��p�j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j 
        //�U�փ^�C�v�F 1�i�����j--l_transferTypeEnumIn 
        //����ID�F ����.����ID --l_lngProductId
        //���z�F ���N�G�X�g�f�[�^.�U�֐��� --l_dblTransferQuantityIn
        //�L�q�F null 
        //�U�֗\����F get������()�̖߂�l --l_datOrderBizDate
        //���ϋ@@��ID�F null 
        //����ID�F null 
        
        //a> �U�փ^�C�v
        AssetTransferTypeEnum l_transferTypeEnumIn = AssetTransferTypeEnum.CASH_IN;
        
        //b> ���z
        double l_dblTransferQuantityIn = 
            (Double.parseDouble(l_request.transferQuantity));
            
        //new 
        WEB3AioNewOrderSpec l_web3AioNewOrderSpecIn =
            new WEB3AioNewOrderSpec(
                l_trador,
                l_orderTypeEnum,
                l_transferTypeEnumIn,
                l_lngProductId,
                l_dblTransferQuantityIn,
                null,
                l_datOrderBizDate,
                null,
                null);    
                
        //1.15 �،��U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g 
        WEB3AioSecurityTransferOrderUpdateInterceptor l_orderUpdateInterceptorIn =
            new WEB3AioSecurityTransferOrderUpdateInterceptor(l_web3AioNewOrderSpecIn);    
            
        //1.16 �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //a> �C���^�Z�v�^.������ = get������()�̖߂�l
        l_orderUpdateInterceptorIn.setOrderBizDat(l_datOrderBizDate);
        
        //b> �C���^�Z�v�^.��n�� = get������()�̖߂�l
        l_orderUpdateInterceptorIn.setDeliveryDate(l_datOrderBizDate);
        
        //c> �C���^�Z�v�^.�ŋ敪 = �i�ȉ��̂Ƃ���j
        //   ���N�G�X�g�f�[�^.�����敪=�h��ʁh�̏ꍇ�F 1�i��ʁj
        //   ���N�G�X�g�f�[�^.�����敪=�h����h�̏ꍇ�F 2�i����j
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorIn.setTaxType(TaxTypeEnum.NORMAL);    
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorIn.setTaxType(TaxTypeEnum.SPECIAL);
        }
        
        //d> �C���^�Z�v�^.���ʃR�[�h = null    
        l_orderUpdateInterceptorIn.setOrderRequestNumber(null);
        
        //1.17 ��K���`�F�b�N���s���B 
        //[����] 
        //�⏕�����F �ڋq.getSubAccount(�⏕�����^�C�v=2�i�ۏ؋������j)�̖߂�l --l_subAccount
        //�������e�C���^�Z�v�^�F �U�֌������̃C���^�Z�v�^�ƐU�֐撍���̃C���^�Z�v�^�̔z�� --l_orderUpdateInterceptor
        //�������e�F �U�֌������̒������e�ƐU�֐撍���̒������e�̔z�� --l_aioNewOrderSpec
        //������ʁF --l_orderTypeEnum
        //���N�G�X�g�f�[�^.�a��敪 = 1�i�ی�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j 
        //���N�G�X�g�f�[�^.�a��敪 = 2�i��p�j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j 
        //�]�͍X�V�t���O�F false 
        
        //a> �⏕����
        WEB3GentradeSubAccount l_subAccountCheck = 
            (WEB3GentradeSubAccount)this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        
        //b> ����]�̓T�[�r�X
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        //c> �������e�C���^�Z�v�^
        WEB3AioSecurityTransferOrderUpdateInterceptor[] l_orderUpdateInterceptor = 
            {l_orderUpdateInterceptorOut, l_orderUpdateInterceptorIn};
            
        //d> �������e
        WEB3AioNewOrderSpec[] l_aioNewOrderSpec = 
            {l_web3AioNewOrderSpecOut, l_web3AioNewOrderSpecIn}; 
            
        //validate
        WEB3TPTradingPowerResult l_result = l_service.validateTradingPower(
            l_subAccountCheck,
            l_orderUpdateInterceptor,
            l_aioNewOrderSpec,
            l_orderTypeEnum,
            false);
            
        //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[��
        //     class: WEB3BusinessLayerException
        //     tag:   BUSINESS_ERROR_01306
        if (l_result.isResultFlg() == false)
        {
            log.debug("����]�̓`�F�b�N�G���[�B"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + l_strMethodName,
                "����]�͌���.����t���O == false");     
        }
            
        //1.18 �V�K�̒���ID���擾����B 
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        
        //1.19 ���X�|���X�f�[�^�𐶐�����B 
        WEB3AioSecurityTransferConfirmResponse l_response = 
            (WEB3AioSecurityTransferConfirmResponse)l_request.createResponse();
        
        //1.20 �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
        l_response.checkDate = l_datOrderBizDate;
        
        //���X�|���X.����ID = AIO�����}�l�[�W��.createNewOrderId()�̖߂�l
        l_response.orderId = String.valueOf(l_lngNewOrderId);
     
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * �U�֒����̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��U�ցjsubmit�����v �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioSecurityTransferCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41577A95032B
     */
    protected WEB3AioSecurityTransferCompleteResponse submitOrder(WEB3AioSecurityTransferCompleteRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "submitOrder(WEB3AioSecurityTransferCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j 
        SubAccount l_subAccountEQUITY = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //FinApp, TradingModule, OrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccountEQUITY);
        
        //1.4 �ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccountEQUITY.getMainAccount();
        
        //1.5 �M�p�������J�݂��Ă��邩�̃`�F�b�N���s���B 
        //[����] 
        //�ٍϋ敪�F 0�i�w��Ȃ��j 
        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //is�M�p�����J��(String)�̖߂�l=false �̏ꍇ�A��O���X���[����B
        //   class: WEB3BusinessLayerException
        //     tag: BUSINESS_ERROR_00747
        if (l_booisMarginAccountEstablished == false)
        {
            log.debug("is�M�p�����J��(String)�̖߂�l=false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                this.getClass().getName() + "." + l_strMethodName,
                "is�M�p�����J��(String)�̖߂�l=false");            
        }
        
        //1.6 ���������擾����B 
        Date l_confirmBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //1.7 �،��U�֒�����1���̏���񐔂𒴂��ĂȂ������`�F�b�N����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        l_orderManager.validateInstitutionTransferPossibleCount(
        l_subAccountEQUITY,
            l_confirmBizDate);
            
        //1.8 �����I�u�W�F�N�g���擾����B 
        //[����] 
        //�����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v 
        String l_strInstrumentType = l_request.instrumentsType;
        
        ProductTypeEnum l_enumInstrumentType;    
        if (ProductTypeEnum.EQUITY.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.EQUITY;
        }
        else if (ProductTypeEnum.BOND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.BOND;
        }
        else if (ProductTypeEnum.MUTUAL_FUND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.MUTUAL_FUND;
        }
        else 
        {
            l_enumInstrumentType = ProductTypeEnum.FOREIGN_EQUITY;
        }
        
        //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
        String l_strProductCode = l_request.productCode;
        //�،���ЁF �⏕����.getInsutitution()�̖߂�l 
        Institution l_institution = l_subAccountEQUITY.getInstitution(); 
        
        //AIO�v���_�N�g�}�l�[�W�����擾 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
       
        Product l_product = l_productManager.getProduct(
            l_enumInstrumentType, 
            l_strProductCode, 
            l_institution);
        
        //1.9 �U�։\���ʂ��Z�o����B 
        //[����] 
        //a> ����ID�F �iget�⏕����()�̖߂�l�j.getAccountId()�̖߂�l 
        long l_lngAccountId = l_subAccountEQUITY.getAccountId();
        
        //b> �����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v ---l_enumInstrumentType in 1.8
        
        //c> ����ID�F �iget����()�̖߂�l�j.����ID 
        long l_lngProductId = l_product.getProductId();
        
        //d> �ŋ敪�F 
        TaxTypeEnum l_taxTypeEnum;
        
        //���N�G�X�g�f�[�^.�����敪=null�̏ꍇ�F 0�i���̑��j
        if (l_request.taxType == null)
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;            
        }
        //���N�G�X�g�f�[�^.�����敪=�h��ʁh�̏ꍇ�F 1�i��ʁj 
        else if(WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;    
        }
        //���N�G�X�g�f�[�^.�����敪=�h����h�̏ꍇ�F 2�i����j 
        else
        {            
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        
        //e> �a��敪�F ���N�G�X�g�f�[�^.�U�֌��a��敪 
        String l_strDepositDiv = l_request.depositDiv;
        
        //get WEB3AioBizLogicProvider
        WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
            (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        // test log
        log.debug("l_lngAccountId = " + l_lngAccountId);
        log.debug("l_enumInstrumentType = " + l_enumInstrumentType);
        log.debug("l_lngProductId = " + l_lngProductId);
        log.debug("l_taxTypeEnum = " + l_taxTypeEnum);
        log.debug("l_strDepositDiv = " + l_strDepositDiv);
       
        double l_dblTransPossibleAmount = 
            l_web3AioBizLogicProvider.calcTransPossibleAmount(
                l_lngAccountId,
                l_enumInstrumentType,
                l_lngProductId,
                l_taxTypeEnum,
                l_strDepositDiv);
        
        
        
                
        //���N�G�X�g�f�[�^.�U�֐��� > �擾���ʁicalc�U�։\����()�̖߂�l�j �̏ꍇ�A��O���X���[����B
        //     class: WEB3BusinessLayerException
        //     tag:   BUSINESS_ERROR_01305
        if (Double.parseDouble(l_request.changeQuantity) > l_dblTransPossibleAmount)
        {
            log.debug("�U�֐��ʂ́A�擾���ʂ��傫���ł��B"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01305,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֐���[" + l_request.changeQuantity + "] > �擾���ʁicalc�U�։\����()�̖߂�l�j["
                + l_dblTransPossibleAmount + "]");       
        }
        
        //1.10 �⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 2�i�ۏ؋������j 
        WEB3GentradeSubAccount l_subAccountMARGIN = 
            (WEB3GentradeSubAccount)this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        
        //1.11 �㗝���͎҃I�u�W�F�N�g���擾����B 
        Trader l_trador = this.getTrader();
        
        //1.12 �V�K�̎��ʃR�[�h���擾����B 
        //[����] 
        //a> �،���ЃR�[�h�F �⏕����.getInsutitution().getInstitutionCode()�̖߂�l 
        String l_strInstitutionCode = 
            l_subAccountMARGIN.getInstitution().getInstitutionCode();
        
        //b> ���X�R�[�h�F �⏕����.get����X().getBranchCode()�̖߂�l 
        String l_strBranchCode = 
            l_subAccountMARGIN.getWeb3GenBranch().getBranchCode();
        
        //�����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v --l_enumInstrumentType
        
        //���ʔԍ��̔ԃC���^�[�t�F�[�X
        WEB3HostReqOrderNumberManageService l_orderNumberManageService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
                
        String l_strNewNumber = l_orderNumberManageService.getNewNumber(
            l_strInstitutionCode,
            l_strBranchCode,
            l_enumInstrumentType);
        
        //***************start of �U�֌�����********************
        
        //1.13 ���o���������e�C���X�^���X�𐶐�����B 
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l --l_trador
        //������ʁF 
        //���N�G�X�g�f�[�^.�a��敪 = 1�i�ی�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j 
        //���N�G�X�g�f�[�^.�a��敪 = 2�i��p�j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j 
        //�U�փ^�C�v�F 2�i�o���j--l_transferTypeEnumOut
        //����ID�F ����.����ID --l_lngProductId
        //���z�F ���N�G�X�g�f�[�^.�U�֐��� �~ -1 --l_dblTransferQuantityOut
        //�L�q�F null 
        //�U�֗\����F get������()�̖߂�l --l_confirmBizDate
        //���ϋ@@��ID�F null 
        //����ID�F null 

        //a> �������
        OrderTypeEnum l_orderTypeEnum;
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_request.depositDiv))
        {
            l_orderTypeEnum = OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES;     
        }
        else 
        {
            l_orderTypeEnum = OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT;    
        }
        
        //b> �U�փ^�C�v
        AssetTransferTypeEnum l_transferTypeEnumOut = AssetTransferTypeEnum.CASH_OUT;
        
        //c> ���z
        double l_dblTransferQuantityOut = 
            (Double.parseDouble(l_request.changeQuantity)) * (-1);
        
        //new 
        WEB3AioNewOrderSpec l_web3AioNewOrderSpecOut =
            new WEB3AioNewOrderSpec(
                l_trador,
                l_orderTypeEnum,
                l_transferTypeEnumOut,
                l_lngProductId,
                l_dblTransferQuantityOut,
                null,
                l_confirmBizDate,
                null,
                null);
                
        //1.14 �،��U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g 
        WEB3AioSecurityTransferOrderUpdateInterceptor l_orderUpdateInterceptorOut =
            new WEB3AioSecurityTransferOrderUpdateInterceptor(l_web3AioNewOrderSpecOut);
            
        //1.15 �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //a> �C���^�Z�v�^.������ = get������()�̖߂�l
        l_orderUpdateInterceptorOut.setOrderBizDat(l_confirmBizDate);
        
        //b> �C���^�Z�v�^.��n�� = get������()�̖߂�l
        l_orderUpdateInterceptorOut.setDeliveryDate(l_confirmBizDate);
        
        //c> �C���^�Z�v�^.�ŋ敪 = �i�ȉ��̂Ƃ���j
        //   ���N�G�X�g�f�[�^.�����敪=�h��ʁh�̏ꍇ�F 1�i��ʁj
        //   ���N�G�X�g�f�[�^.�����敪=�h����h�̏ꍇ�F 2�i����j
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorOut.setTaxType(TaxTypeEnum.NORMAL);    
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorOut.setTaxType(TaxTypeEnum.SPECIAL);    
        }
        
        //d> �C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l    
        l_orderUpdateInterceptorOut.setOrderRequestNumber(l_strNewNumber);
        
        //***************start of �U�֐撍��********************
        
        //1.16 ���o���������e�C���X�^���X�𐶐�����B
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l --l_trador
        //������ʁF --l_orderTypeEnum
        //���N�G�X�g�f�[�^.�a��敪 = 1�i�ی�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j 
        //���N�G�X�g�f�[�^.�a��敪 = 2�i��p�j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j 
        //�U�փ^�C�v�F 1�i�����j--l_transferTypeEnumIn 
        //����ID�F ����.����ID --l_lngProductId
        //���z�F ���N�G�X�g�f�[�^.�U�֐��� --l_dblTransferQuantityIn
        //�L�q�F null 
        //�U�֗\����F get������()�̖߂�l --l_confirmBizDate
        //���ϋ@@��ID�F null 
        //����ID�F null 
        
        //a> �U�փ^�C�v
        AssetTransferTypeEnum l_transferTypeEnumIn = AssetTransferTypeEnum.CASH_IN;
        
        //b> ���z
        double l_dblTransferQuantityIn = 
            (Double.parseDouble(l_request.changeQuantity));
            
        //new 
        WEB3AioNewOrderSpec l_web3AioNewOrderSpecIn =
            new WEB3AioNewOrderSpec(
                l_trador,
                l_orderTypeEnum,
                l_transferTypeEnumIn,
                l_lngProductId,
                l_dblTransferQuantityIn,
                null,
                l_confirmBizDate,
                null,
                null);    
                
        //1.17 �،��U�֒����X�V�C���^�Z�v�^�̃C���X�^���X�𐶐�����B 
        //[����] 
        //���o���������e�F ���o���������e�I�u�W�F�N�g 
        WEB3AioSecurityTransferOrderUpdateInterceptor l_orderUpdateInterceptorIn =
            new WEB3AioSecurityTransferOrderUpdateInterceptor(l_web3AioNewOrderSpecIn);    
            
        //1.18 (*1)(*2)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //a> �C���^�Z�v�^.������ = get������()�̖߂�l
        l_orderUpdateInterceptorIn.setOrderBizDat(l_confirmBizDate);
        
        //b> �C���^�Z�v�^.��n�� = get������()�̖߂�l
        l_orderUpdateInterceptorIn.setDeliveryDate(l_confirmBizDate);
        
        //c> �C���^�Z�v�^.�ŋ敪 = �i�ȉ��̂Ƃ���j
        //   ���N�G�X�g�f�[�^.�����敪=�h��ʁh�̏ꍇ�F 1�i��ʁj
        //   ���N�G�X�g�f�[�^.�����敪=�h����h�̏ꍇ�F 2�i����j
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorIn.setTaxType(TaxTypeEnum.NORMAL);    
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_orderUpdateInterceptorIn.setTaxType(TaxTypeEnum.SPECIAL);    
        }
        
        //d> �C���^�Z�v�^.���ʃR�[�h = get�V�K���ʃR�[�h()�̖߂�l    
        l_orderUpdateInterceptorIn.setOrderRequestNumber(l_strNewNumber);
        
        //1.19 ��K���`�F�b�N���s���B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l�i�ۏ؋������j--l_subAccountCheck
        //�������e�C���^�Z�v�^�F �U�֌������̃C���^�Z�v�^�ƐU�֐撍���̃C���^�Z�v�^�̔z�� 
        //�������e�F �U�֌������̒������e�ƐU�֐撍���̒������e�̔z�� 
        //������ʁF 
        //���N�G�X�g�f�[�^.�a��敪 = 1�i�ی�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j 
        //���N�G�X�g�f�[�^.�a��敪 = 2�i��p�j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j 
        //�]�͍X�V�t���O�F true
        
        //a> ����]�̓T�[�r�X
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        //b> �������e�C���^�Z�v�^
        WEB3AioSecurityTransferOrderUpdateInterceptor[] l_orderUpdateInterceptor = 
            {l_orderUpdateInterceptorOut, l_orderUpdateInterceptorIn};
            
        //c> �������e
        WEB3AioNewOrderSpec[] l_aioNewOrderSpec = 
            {l_web3AioNewOrderSpecOut, l_web3AioNewOrderSpecIn}; 
            
        //validate
        WEB3TPTradingPowerResult l_result = l_service.validateTradingPower(
            l_subAccountMARGIN,
            l_orderUpdateInterceptor,
            l_aioNewOrderSpec,
            l_orderTypeEnum,
            true);
            
        //�߂�l�̎���]�͌���.����t���O == false �̏ꍇ�A��O���X���[��
        //     class: WEB3BusinessLayerException
        //     tag:   BUSINESS_ERROR_01306
        if (l_result.isResultFlg() == false)
        {
            log.debug("����]�̓`�F�b�N�G���[�B"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + l_strMethodName,
                "����]�͌���.����t���O == false");     
        }   
        
        //1.20 �U�֌��̏،��U�֒�����o�^����B 
        //[����] 
        //�⏕�����F--l_subAccountOut
        //���N�G�X�g�f�[�^.�U�֌��a��敪 = 1�i�ی�j�̏ꍇ�Aget�⏕����()�̖߂�l�i�a��������j 
        //���N�G�X�g�f�[�^.�U�֌��a��敪 = 2�i��p�j�̏ꍇ�Aget�⏕����()�̖߂�l�i�ۏ؋������j 
        //�����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v --l_enumInstrumentType
        //������ʁF --l_orderTypeEnum
        //���N�G�X�g�f�[�^.�U�֌��a��敪 = 1�i�ی�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j 
        //���N�G�X�g�f�[�^.�U�֌��a��敪 = 2�i��p�j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j 
        //�������e�F���o���������e�i�U�֌������j--l_web3AioNewOrderSpecOut 
        //�C���^�Z�v�^�F�،��U�֒����X�V�C���^�Z�v�^�i�U�֌������j-- l_orderUpdateInterceptorOut
        //����ID�F ���N�G�X�g�f�[�^.����ID --l_lngOrderId
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� --l_strPassword
        
        //a> �⏕���� 
        SubAccount l_subAccountOut = null;
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_request.depositDiv))
        {
            l_subAccountOut = l_subAccountEQUITY;        
        }
        else if (WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(l_request.depositDiv))
        {
            l_subAccountOut = l_subAccountMARGIN;        
        }
        
        //b> ����ID
        long l_lngOrderId = Long.parseLong(l_request.orderId);
        
        //c> �p�X���[�h
        String l_strPassword = l_request.password;
        
        //test log
        log.debug("subAccount = " + l_subAccountOut);
        log.debug("enumInstrumentType = " + l_enumInstrumentType);
        log.debug("orderTypeEnum = " + l_orderTypeEnum);
        log.debug("web3AioNewOrderSpecOut = " + l_web3AioNewOrderSpecOut);
        log.debug("orderUpdateInterceptorOut = " + l_orderUpdateInterceptorOut);
        log.debug("OrderId = " + l_lngOrderId);
        log.debug("Password = " + l_strPassword);
        
        l_orderManager.submitTransferOrder(
            l_subAccountOut,
            l_enumInstrumentType,
            l_orderTypeEnum,
            l_web3AioNewOrderSpecOut,
            l_orderUpdateInterceptorOut,
            l_lngOrderId,
            l_strPassword);    
        
        //1.21 ���Β����p�̒���ID���擾����B  
        long lngNewOrderId = l_orderManager.createNewOrderId();
        //test log
        log.debug("2 OrderId = " + lngNewOrderId);
        
        //1.22 �U�֐�̏،��U�֒�����o�^����B 
        //[����] 
        //�⏕�����F --l_subAccountOut
        //���N�G�X�g�f�[�^.�U�֌��a��敪 = 1�i�ی�j�̏ꍇ�Aget�⏕����()�̖߂�l�i�ۏ؋������j 
        //���N�G�X�g�f�[�^.�U�֌��a��敪 = 2�i��p�j�̏ꍇ�Aget�⏕����()�̖߂�l�i�a��������j 
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_request.depositDiv))
        {
            l_subAccountOut = l_subAccountMARGIN;        
        }
        else if (WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(l_request.depositDiv))
        {
            l_subAccountOut = l_subAccountEQUITY;        
        }

        //�����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v --l_enumInstrumentType
        //������ʁF --l_orderTypeEnum
        //���N�G�X�g�f�[�^.�U�֌��a��敪 = 1�i�ی�j�̏ꍇ�A1009�i�،��U�֒����i�ی�a�肩���p�L���،��j 
        //���N�G�X�g�f�[�^.�U�֌��a��敪 = 2�i��p�j�̏ꍇ�A1010�i�،��U�֒����i��p�L���،�����ی�a��j 
        //�������e�F���o���������e�i�U�֐撍���j --l_web3AioNewOrderSpecIn
        //�C���^�Z�v�^�F�،��U�֒����X�V�C���^�Z�v�^�i�U�֐撍���j--l_orderUpdateInterceptorIn 
        //����ID�F createNewOrderId()�̖߂�l --lngNewOrderId
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� --l_strPassword

        l_orderManager.submitTransferOrder(
            l_subAccountOut,
            l_enumInstrumentType,
            l_orderTypeEnum,
            l_web3AioNewOrderSpecIn,
            l_orderUpdateInterceptorIn,
            lngNewOrderId,
            l_strPassword);
            
        //�������擾����B 
        //[����] 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        try
        {
            Order l_order = l_orderManager.getOrder(l_lngOrderId);           

            //1.24 ���X�|���X�f�[�^�𐶐�����B 
            WEB3AioSecurityTransferCompleteResponse l_response = 
                (WEB3AioSecurityTransferCompleteResponse)l_request.createResponse();
            
            //1.25 �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
            //���X�|���X.�X�V���� = ����.�X�V���t
            l_response.lastUpdatedTimestamp = ((AioOrderRow)l_order.getDataSourceObject()).getLastUpdatedTimestamp();
        
            //���X�|���X.����ID = ���N�G�X�g�f�[�^.����ID
            l_response.orderId = String.valueOf(l_lngOrderId);
            
            log.exiting(l_strMethodName);
            
            return l_response;
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
    }    

    /**
     * (validate�،��U�։\�P��)
     * �U�֐��ʂ̒P�ʃ`�F�b�N���s���B
     * 
     * ����.�U�֐��� / 10000 �̏�]��0�łȂ��ꍇ�A
     * ��O���X���[����B
     * 
     * @@param - (�U�֐���)
     * ���N�G�X�g�f�[�^�D�U�֐���
     * @@throws WEB3BaseException 
     */
    private void validateSecurityTransferPossibleUnit(String l_strQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSecurityTransferPossibleUnit()";
        log.entering(STR_METHOD_NAME);
        
        BigDecimal l_bdTransferQuantity = new BigDecimal(l_strQuantity);
        BigDecimal l_bdResult =
            l_bdTransferQuantity.divide(new BigDecimal(10000), 0);
        BigDecimal l_bdResult1 = l_bdResult.multiply(new BigDecimal(10000));

        if (l_bdResult1.compareTo(l_bdTransferQuantity) != 0)
        {
            log.debug("����.�U�֐��� / 10000 �̏�]��0�łȂ�");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01300,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�U�֐��ʂ̃T�C�Y���s���ł��B");  
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
