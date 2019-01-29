head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecuteUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Unit�T�[�r�XImpl(WEB3BondAutoExecuteUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 ����� (���u) �V�K�쐬
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondChangeOrderSpec;

import webbroker3.bd.WEB3AdminBondOrderAcceptUpdateInterceptor;
import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondExecuteUpdateInterceptor;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������Unit�T�[�r�XImpl)<BR>
 * ���������Unit�T�[�r�X�����N���X
 * 
 * @@author �����
 * @@version 1.0
 */

public class WEB3BondAutoExecuteUnitServiceImpl implements WEB3BondAutoExecuteUnitService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondAutoExecuteUnitServiceImpl.class);
    
    /**
     * @@roseuid 44E3362F0242
     */
    public WEB3BondAutoExecuteUnitServiceImpl() 
    {
     
    }

    /**
     * (notify�������)<BR>
     * ������菈��������<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���������Unit�T�[�r�X�jnotify�������v�Q�ƁB<BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    public void notifyAutoExecute(WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyAutoExecute(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
    
        //1.1 update�������(�g���������P��)
        try 
        {
            this.updateAutoExecute(l_bondOrderUnit);
        }
        //1.2 update�������ŗ�O���X���[���ꂽ�ꍇ
        catch(WEB3BaseException l_ex)
        {
            //1.2.1 error_tag��"SYSTEM_ERROR"�Ŏn�܂�ꍇ�́AWEB3SystemLayerException���X���[����B
            if (l_ex.getErrorInfo().getErrorTag().startsWith("SYSTEM_ERROR"))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    l_ex.getErrorMethod(),
                    l_ex.getMessage(),
                    l_ex.getException());
            }
            
            //1.2.2 to�����G���[���R�R�[�h(ErrorInfo)
            //�����G���[���R�R�[�h���擾 
            //[����] 
            //�G���[���Fcatch������O����擾����ErrorInfo
            String l_strErrorReasonCode = 
                this.toOrderErrorReasonCode(l_ex.getErrorInfo());
            
            //1.2.3 update�G���[��������(�������P��, String)
            //�����G���[���R�R�[�h���X�V���� 
            //[����] 
            //�������P�ʁF����.�������P�� 
            //�����G���[���R�R�[�h�Fto�����G���[���R�R�[�h
            this.updateErrorOrder(l_bondOrderUnit, l_strErrorReasonCode);
        } 
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�������)<BR>
     * ������菈��������<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���������Unit�T�[�r�X�jupdate�������v�Q�ƁB <BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    protected void updateAutoExecute(WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "updateAutoExecute(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
    
        //1.1 get������()
        //���������擾����
        Date l_datBizDate = 
            WEB3DateUtility.getDate(
                l_bondOrderUnit.getBizDate() , "yyyyMMdd");
        
        //1.2 get��������ʔ���()
        //��������ʔ�����擾
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge = 
            l_bondOrderUnit.getBondOrderTypeJudge();
        
        //1.3 get���ϋ敪()
        //���ϋ敪���擾
        String l_strSettlementDiv = l_bondOrderUnit.getSettlementDiv();
        
        //1.4 getBranchId()
        //���XID���擾
        long l_lngBranchId = l_bondOrderUnit.getBranchId();
        
        //1.5 getBranch(arg0 : long)
        //���X���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_web3GentradeAccountManager.getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.6 get������(long)
        //���������擾
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager = 
            (WEB3BondProductManager) l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct)l_productManager.getBondProduct(l_bondOrderUnit.getProductId());
        
        //1.7 create���������(java.util.Date, ��������ʔ���, ������, String, Branch)
        //���������𐶐����� 
        //[����] 
        //�������Fget������ 
        //��������ʔ���F����������������ʔ��� 
        //�������Fget������ 
        //���ϋ敪�Fget�������P��By����ID.get���ϋ敪 
        //���X�Fget���X
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = 
            l_bondOrderManager.createBondExecutionDateInfo(
                l_datBizDate, 
                l_bondOrderTypeJudge, 
                l_bondProduct,
                l_strSettlementDiv,
                l_branch);
        
        //1.8 is�O�݌�()
        //�O�݌������肷��
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();
        
        //1.9 is�O�݌�����true�̏ꍇ
        BigDecimal l_bdFxRate = null;
        if (l_blnIsForeignCurrency)
        {
            //1.9.1 get�בփ��[�g(������, ��������ʔ���, String, BigDecimal, boolean)
            //�בփ��[�g���擾����B 
            //[����] 
            //�������Fget������ 
            //��������ʔ���F����������������ʔ��� 
            //���ϋ敪�Fget���ϋ敪 
            //���͈בփ��[�g�F�@@0 
            //is���v�Z�Ftrue
            l_bdFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct, 
                l_bondOrderTypeJudge, 
                l_strSettlementDiv, 
                new BigDecimal("0"),
                true);
        }
        
        //1.10 getQuantity()
        //���ʂ��擾
        BondOrderUnitRow l_bondOrderUnitRow = 
            (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
       
        BigDecimal l_bdQuantity = null;
        double l_dblQuantity = 0D;
        if (l_bondOrderUnitRow.getQuantityIsSet())
        {
            l_bdQuantity = new BigDecimal(String.valueOf(l_bondOrderUnit.getQuantity()));
            l_dblQuantity = l_bondOrderUnit.getQuantity();
        }   
        
        //1.11 getLimitPrice()
        //�w�l���擾
        BigDecimal l_bdLimitPrice = null;
        double l_dblLimitPrice = 0D;
        if (!l_bondOrderUnitRow.getLimitPriceIsNull())
        {
            l_bdLimitPrice = new BigDecimal(String.valueOf(l_bondOrderUnit.getLimitPrice()));
            l_dblLimitPrice = l_bondOrderUnit.getLimitPrice();
        }
        
        //1.12 calc��n���(��������ʔ���, BigDecimal, BigDecimal, BigDecimal, ������, ���������)
        //����n����v�Z���ʃI�u�W�F�N�g�𐶐����� 
        //[����] 
        //��������ʔ���F����������������ʔ��� 
        //���ʁFgetQuantity 
        //�����P���FgetLimitPrice 
        //�בփ��[�g�Fget�בփ��[�g�i��is�O�݌�()�̖߂�l == false�̏ꍇ�Anull���Z�b�g����B�j 
        //�������Fget������ 
        //���������F����������������� 
        WEB3BondBizLogicProvider l_bizLogicProvider = 
            (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getBizLogicProvider();
        
        WEB3BondEstimatedPriceCalcResult l_priceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge, 
                l_bdQuantity,
                l_bdLimitPrice,
                l_bdFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);
        
        //1.13 �����X�V�C���^�Z�v�^()
        //�C���^�Z�v�^�𐶐�����
        WEB3BondExecuteUpdateInterceptor l_bondExecuteUpdateInterceptor = 
            new WEB3BondExecuteUpdateInterceptor();
        
        //1.14 �v���p�e�B�Z�b�g
        //���������C���^�Z�v�^�Ɉȉ��̃v���p�e�B���Z�b�g����B  
        //���������Fcreate������� 
        //����n����v�Z���ʁFcalc��n��� 
        //�������Fget������ 
        l_bondExecuteUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_bondExecuteUpdateInterceptor.setBondEstimatedPriceCalcResult(l_priceCalcResult);
        l_bondExecuteUpdateInterceptor.setBondProduct(l_bondProduct);
        
        //1.15 ��������ʔ���.is���p����( )����false�@@���@@get���ϋ敪�����~�݂̏ꍇ
        if (!l_bondOrderTypeJudge.isSellOrder() && 
            WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettlementDiv))
        {
            //1.15.1  get�⏕����()
            //�⏕�������擾���� 
            //[����] 
            //����ID�F�������P��.get����ID 
            //�⏕����ID�F�������P��.get�⏕����ID
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                        l_bondOrderUnit.getAccountId(), 
                        l_bondOrderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            
            //1.15.2 BondChangeOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
            //���ύX�������e�I�u�W�F�N�g�𐶐� 
            //[����] 
            //����ID�F�������P��.get����ID 
            //�����P��ID�F�������P��,get�����P��ID 
            //���ʁFgetQuantity 
            //�P���FgetLimitPrice 
            BondChangeOrderSpec l_changeOrderSpec = 
                new BondChangeOrderSpec(
                    l_bondOrderUnit.getOrderId(),
                    l_bondOrderUnit.getOrderUnitId(),
                    l_dblQuantity,
                    l_dblLimitPrice);
            
            //1.15.3 validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[], 
            //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            //�]�̓`�F�b�N 
            //[validate����]��()�̈���] 
            //�⏕�����Fget�⏕���� 
            //�������e�C���^�Z�v�^�F�����X�V�C���^�Z�v�^ 
            //�������e�FBondChangeOrderSpec 
            //������ʁFOrderTypeEnum.�������� 
            //�]�͍X�V�t���O�Ftrue
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            Object[] l_objExecuteUpdateInterceptor = new Object[]{l_bondExecuteUpdateInterceptor};
            Object[] l_objChangeOrderSpecs = new Object[]{l_changeOrderSpec};
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_service.validateTradingPower(
                    l_subAccount, 
                    l_objExecuteUpdateInterceptor, 
                    l_objChangeOrderSpecs, 
                    OrderTypeEnum.BOND_BUY, 
                    true);
            
            //1.15.4 is����t���O()
            //�]�͌��ʂ𔻒肷��
            if (l_tPTradingPowerResult != null)
            {
                boolean l_blnIsResultFlg = l_tPTradingPowerResult.isResultFlg();
                
                //1.15.5 is����t���O����false�̏ꍇ�A��O���X���[����B
                if (!l_blnIsResultFlg)
                {
                    log.debug("����]�̓`�F�b�N�G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "����]�̓`�F�b�N�G���[�B");
                }
            }
        }     
        //1.16 ���Ǘ��Ғ�����t�X�V�C���^�Z�v�^()
        //�C���^�Z�v�^�𐶐�����
        WEB3AdminBondOrderAcceptUpdateInterceptor l_orderAcceptUpdateInterceptor = 
            new WEB3AdminBondOrderAcceptUpdateInterceptor();
        
        //1.17 accept�V�K����(long, ���Ǘ��҃f�t�H���g�C���^�Z�v�^)
        //�V�K������t������ 
        //[����]  
        //����ID�F �������P��.get����ID 
        //���Ǘ��҃f�t�H���g�C���^�Z�v�^�F ���Ǘ��Ғ�����t�X�V�C���^�Z�v�^
        WEB3AdminBondExecuteNotifyService l_notifyService = 
            (WEB3AdminBondExecuteNotifyService) Services.getService(
                WEB3AdminBondExecuteNotifyService.class);
        l_notifyService.acceptNewOrder(
            l_bondOrderUnit.getOrderId(), 
            l_orderAcceptUpdateInterceptor);
        
        //1.18.notify���(BondOrderUnit, ���Ǘ��҃f�t�H���g�C���^�Z�v�^)
        //��菈�������� 
        //[����]  
        //�@@�������P�ʁF get�������P��By����ID 
        //�@@���Ǘ��҃f�t�H���g�C���^�Z�v�^�F �����X�V�C���^�Z�v�^
        l_bondOrderUnit = l_bondOrderManager.getBondOrderUnitByOrderId(
            l_bondOrderUnit.getOrderId());
        
        l_notifyService.notifyExecute(
            l_bondOrderUnit, l_bondExecuteUpdateInterceptor);         
          
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * (to�����G���[���R�R�[�h)<BR>
     * �����̃G���[���ɑΉ����钍���G���[���R�R�[�h��ԋp����B<BR>
     * <BR>  
     * ���ԋp����钍���G���[���R�R�[�h�ɂ��ẮA <BR>
     * �@@DB���C�A�E�g�u�������P�ʃe�[�u���d�l.xls#�i�����P�ʃe�[�u���⑫�j�����G���[���R�R�[�h�v�Q�ƁB<BR> 
     * <BR>
     * �P�j�p�����[�^.�G���[���ɂ��A�����G���[���R�R�[�h�����肷��B<BR>
     * <BR>
     * �@@[�p�����[�^.�G���[��� == "�a����s��"�̏ꍇ]<BR>  
     * �@@(validate����]��()�̌��ʁA�X���[���ꂽ��O�̏ꍇ)<BR>  
     * �@@�@@�����G���[���R�R�[�h = "�a����s���G���[" <BR> 
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>  
     * �@@�@@�����G���[���R�R�[�h = "���̑��G���["<BR>  
     * <BR>
     * �Q�j�@@���肵�������G���[���R�R�[�h��ԋp����B<BR> 
     * @@param l_errorInfo - (�G���[���)<BR>
     * �G���[���<BR>
     * @@roseuid 44CB3777025E
     */
    protected String toOrderErrorReasonCode(ErrorInfo  l_errorInfo) 
    {
        final String STR_METHOD_NAME = "toOrderErrorReasonCode(ErrorInfo)";
        log.entering(STR_METHOD_NAME);
        
        String l_strErrorCode = null;
        
        //[�p�����[�^.�G���[��� == "�a����s��"�̏ꍇ]
        if (WEB3ErrorCatalog.BUSINESS_ERROR_01306.equals(l_errorInfo))
        {
            //�����G���[���R�R�[�h = "�a����s���G���["
            l_strErrorCode = WEB3ErrorReasonCodeDef.DEPOSIT_MONEY_SHORT_ERROR;
        }
        //[��L�ȊO�̏ꍇ]
        else
        {
            //�����G���[���R�R�[�h = "���̑��G���["
            l_strErrorCode = WEB3ErrorReasonCodeDef.OTHRE_ERROR;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strErrorCode;
    }
    
    
    /**
     * (update�G���[��������)<BR>
     * �G���[�������������P�ʂ̒����G���[���R�R�[�h�Ȃǂ�update����B<BR>
     * <BR>  
     * �P�j�@@�G���[�������������̒����G���[���R�R�[�h��update����B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�G���[�������������̒����P�ʃ��R�[�h��update����B <BR>
     * �@@�@@�P�|�P�|�P�j�������P��.getDataSourceObject()����������P��Params���擾����B<BR> 
     * �@@�@@�P�|�P�|�Q�j�����P�ʂ̃N���[�����쐬����Bnew �������P��Params(��L�Ŏ擾�����������P��Params)<BR>
     * �@@�@@�P�|�P�|�R�j�����P�ʂ̃N���[�������ɁA�����P�ʃ��R�[�h��update����B<BR> 
     * �@@�@@�@@�@@<�X�V���e><BR>  
     * �@@�@@�@@�@@�@@�����G���[���R�R�[�h = ����.�����G���[���R�R�[�h <BR> 
     * �@@�@@�@@�@@�@@�X�V���t = ���ݓ���  <BR>
     * <BR>
     * �@@�P�|�Q�j�@@�ȉ��̏����ɊY������A�G���[�������������̒����i�w�b�_�j�̍X�V������update����B<BR>  
     * <BR>
     * �@@�@@<����> <BR>
     * �@@�@@�����i�w�b�_�j�e�[�u��.����ID = �����P�ʂ̃N���[��.get����ID <BR> 
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�����i�w�b�_�j�e�[�u��.�X�V���t = ���ݓ���  <BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@param l_strOrderErrorReasonCode - (�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    protected void updateErrorOrder(BondOrderUnit l_bondOrderUnit,
        String l_strOrderErrorReasonCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "updateErrorOrder(BondOrderUnit String)";
        log.entering(STR_METHOD_NAME);
    
        //�P�j�@@�G���[�������������̒����G���[���R�R�[�h��update����B
        //�P�|�P�j�@@�G���[�������������̒����P�ʃ��R�[�h��update����B
        //�P�|�P�|�P�j�������P��.getDataSourceObject()����������P��Params���擾����B
        BondOrderUnitRow l_orderUnitRow = 
            (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
    
        //�P�|�P�|�Q�j�����P�ʂ̃N���[�����쐬����Bnew �������P��Params(��L�Ŏ擾�����������P��Params)
        BondOrderUnitParams l_bondOrderUnitParams = 
            new BondOrderUnitParams(l_orderUnitRow);
        
        //�P�|�P�|�R�j�����P�ʂ̃N���[�������ɁA�����P�ʃ��R�[�h��update����B
        //<�X�V���e>
        //�����G���[���R�R�[�h = ����.�����G���[���R�R�[�h
        //�X�V���t = ���ݓ���
        l_bondOrderUnitParams.setErrorReasonCode(l_strOrderErrorReasonCode);
        l_bondOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_bondOrderUnitParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), 
                l_ex);
        }
               
        //�P�|�Q�j�@@�ȉ��̏����ɊY������A�G���[�������������̒����i�w�b�_�j�̍X�V������update����B
        //<����>
        //�����i�w�b�_�j�e�[�u��.����ID = �����P�ʂ̃N���[��.get����ID
        //<�X�V���e>
        //�����i�w�b�_�j�e�[�u��.�X�V���t = ���ݓ���
        try
        {
            String l_strWhere = " order_id = ? ";
            Object[] l_objCont = {new Long(l_bondOrderUnit.getOrderId())};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();        
            List l_lisOrderRows = l_queryProcessor.doFindAllQuery(
                BondOrderRow.TYPE, 
                l_strWhere,
                l_objCont);
            
            if (l_lisOrderRows != null && !l_lisOrderRows.isEmpty())
            {
                int l_intCnt = l_lisOrderRows.size();
                
                if (l_intCnt == 1)
                {
                    BondOrderRow l_ordertRows = 
                        (BondOrderRow)l_lisOrderRows.get(0);
            
                    BondOrderParams l_orderParams = 
                        new BondOrderParams(l_ordertRows);
                
                     l_orderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                     l_queryProcessor.doUpdateQuery(l_orderParams);
                }
                else
                {
                    log.debug("�����i�w�b�_�j�e�[�u�����f�[�^�s�����G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�f�[�^�s�����G���[�B");
                }
            }  
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
