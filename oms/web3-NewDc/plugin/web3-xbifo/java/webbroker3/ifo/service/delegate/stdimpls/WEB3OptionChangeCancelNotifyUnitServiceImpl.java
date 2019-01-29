head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeCancelNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               /**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP��������ʒmUnitServiceImpl(WEB3OptionChangeCancelNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/16 Ḗ@@�� (���u) �V�K�쐬
              001: 2004/07/22 ���Ō� (���u) �����̊֘A���e���R�����g
              002: 2004/08/05 Ḗ@@�� WEB3_IFO_UT-000117 
              003: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              004: 2006/7/14 ���G�� (���u) �d�l�ύX�@@���f��458
              005: 2006/09/28 �s�p (���u) �d�l�ύX ���f��564
              006: 2006/11/29 ����� (���u) �d�l�ύX ���f��576
Revesion History : 2008/04/10 �����F (���u) ���f�� 845
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoCanmodReceiptTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP��������ʒmUnitServiceImpl)<BR>
 * �����w���I�v�V������������ʒm�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �����P�����Ƃ̒�������ʒm���������{����B<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)���w�肷��B<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3OptionChangeCancelNotifyUnitServiceImpl implements WEB3OptionChangeCancelNotifyUnitService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeCancelNotifyUnitServiceImpl.class);
    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    TradingModule l_tradingMod =
        l_finApp.getTradingModule(ProductTypeEnum.IFO);
    /**
     * @@roseuid 40C0752E0138
     */
    public WEB3OptionChangeCancelNotifyUnitServiceImpl() 
    {
     
    }
    
    /**
     * (notify����)<BR>
     * �����ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP��������ʒm�jnotify�����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_ifoChangeCancelNotifyInterceptor - �敨OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g
     * @@roseuid 4084C37D0150
     */
    public void notifyChange(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException 
    { 
        final String STR_METHOD_NAME =
            "notifyChange(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) ";
        log.entering(STR_METHOD_NAME);
        if ((l_orderUnit == null) || (l_ifoChangeCancelNotifyInterceptor == null))
        {
            log.error("Enter the path that the paramter l_orderUnit is null.");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingMod.getOrderManager();
        
        //1.2.setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
        //�X���b�h�ɃC���^�Z�v�^���Z�b�g����B 
        // [����] 
        //arg0�F ����.�敨OP��������ʒm�C���^�Z�v�^


        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelNotifyInterceptor);

		//1.1.getOrderUnit(�����P��.�����P��ID : long)
        //�����P�ʂ��Ď擾����B�i�������b�N���������ꍇ���l���j
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch (NotFoundException l_nfe)
		{
			log.error("�f�[�^�s�����G���[�B", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe); 
		}
        
        //Start 2004/08/13 ������  �Ή��o�b�O BUG79
        //1.3.�擾������w�l 
        double l_dblChangedLimitPrice = 
           l_ifoChangeCancelNotifyInterceptor.getChangedLimitPrice();
        log.debug("������w�l"+ l_dblChangedLimitPrice);
        
        if (Double.isNaN(l_dblChangedLimitPrice))
        {
            l_dblChangedLimitPrice = 0D;
        }
       
        //1.4.�擾�����㐔��
        double l_dblChangedQuantity = 
              l_ifoChangeCancelNotifyInterceptor.getChangedQuantity();
        if (Double.isNaN(l_dblChangedQuantity))
        {
           l_dblChangedQuantity = 0D;
        }
         
        log.debug("�����㐔��"+l_dblChangedQuantity );
        //�擾�����O����
        double l_dblQuantity = l_reOrderUnit.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
           l_dblQuantity = 0D;
        }
        
        log.debug("�擾�����O����"+l_dblQuantity );
        //1.6.�擾side
        SideEnum l_side = l_reOrderUnit.getSide();
        log.debug("�擾side"+l_side.toString() );
        //1.7.�擾�����J�e�S��            
        OrderCategEnum l_intOrderCateg = l_orderUnit.getOrderCateg();
        log.debug("�擾�����J�e�S��"+l_side.toString() );
        //1.8.��萔��          
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
           l_dblExecutedQuantity = 0D;
        }
        log.debug("��萔��"+l_dblExecutedQuantity );
        //1.9.���v�����z               
        double l_dblExecutedAmount = l_reOrderUnit.getExecutedAmount();
        if (Double.isNaN(l_dblExecutedAmount))
        {
           l_dblExecutedAmount = 0D;
        }
        log.debug(" ���v�����z"+ l_dblExecutedAmount );

        //is�X�g�b�v�����ؑ֒�(IfoOrderUnit)
        //�X�g�b�v�����ؑ֒����ǂ������ʂ���B
        //[����]
        //�����P�ʁF�@@�����P��
        boolean l_blnIsStopOrderSwitching =
            ((WEB3OptionOrderManagerImpl)l_orderManager).isStopOrderSwitching((IfoOrderUnit)l_reOrderUnit);

        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        WEB3IfoEstimateDeliveryAmountCalcResult l_wEstimateResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
        //����UnitId
        long l_lngOrderUnitId = l_reOrderUnit.getOrderUnitId();

        //����Id
        long l_lngOrderId = l_reOrderUnit.getOrderId();

        //�ڋqId
        long l_lngMainAccountId = l_reOrderUnit.getAccountId();

        //SubAccountID
        long l_lngSubAccountId = l_reOrderUnit.getSubAccountId();

        //�擾OrderManager
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                l_lngMainAccountId, l_lngSubAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�X�g�b�v�����������Ƀ��~�b�g�����̒P���ōČv�Z���K�v�ƂȂ钍���̏ꍇ
        //�����P��.������� == "OP�V�K��������" ����
        //get��������ʒm�敪 == �h�������s�h ����
        //is�X�g�b�v�����ؑ֒�() == true
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_reOrderUnit.getOrderType())
            && WEB3IfoCanmodReceiptTypeDef.CHANGED_FAILED.equals(
                l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision())
                && l_blnIsStopOrderSwitching)
        {
            //get�X�g�b�v�����������T�Z����v�Z����(IfoOrderUnit, �⏕����)
            //�X�g�b�v�����������̊T�Z��n������v�Z����B
            //[����]
            //�����P�ʁF�@@�����P��
            //�⏕�����F�@@�����P��.�⏕����ID�ɊY������⏕����
            l_estimateResult =
                l_orderMgr.getStopOrderExpireEstimatedPrice(
                    (IfoOrderUnit)l_reOrderUnit,
                    l_subAccount);
        }
        else
        {
            // is�ԍϒ��� 92�F�敨�ԍϒ��� 94�FOP�ԍϒ���
            boolean l_blnIsSettleContract ;
            if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_intOrderCateg))
            {
                l_blnIsSettleContract = true;
            }
            else
            {
                l_blnIsSettleContract = false;
            }
            
            //1.10. �萔���I�u�W�F�N�g�𐶐�����
            WEB3IfoBizLogicProvider l_bizLogicProvider =
                (WEB3IfoBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
            WEB3GentradeCommission l_commision =
                l_bizLogicProvider.createCommission(l_reOrderUnit.getOrderUnitId(),
                l_dblChangedQuantity);
    
            //IfoOrderUnitRow�𐶐�����
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_reOrderUnit.getDataSourceObject();

            //1.11.setIs�w�l(is�w�l : boolean)
			//setIs�w�l()�Ɏw�肷�����]
			//is�w�l�F�@@�敨OP��������ʒm�X�V�C���^�Z�v�^.get������w�l != 0�̏ꍇ�Atrue�B�ȊO�Afalse�B
            if (l_ifoChangeCancelNotifyInterceptor.getChangedLimitPrice() != 0)
            {
                l_commision.setIsLimitPrice(true);
            }
            else
            {
                l_commision.setIsLimitPrice(false);
            }


            WEB3IfoTradedProductImpl l_tradedProduct1 =  (WEB3IfoTradedProductImpl)l_reOrderUnit.getTradedProduct();
            // 1.12.calc�������T�Z��n���
    		//[calc�������T�Z��n���()�Ɏw�肷�����] 
    		// �萔���F�@@�萔���I�u�W�F�N�g 
    		// �v�Z�P���F�@@�iget������w�l()�߂�l�j 
    		// �⏕�����F�@@�����P��.�⏕�����h�c�ɊY������⏕�����I�u�W�F�N�g 
    		// �敨OP��������F�@@�����P��.getTradedProduct() 
    		// ���ʁF �iget�����㐔��()�߂�l�j 
    		// �����F �����P��.getSide() 
    		// is�ԍϒ����F�@@ 
    		// �@@�����J�e�S�����h�ԍϒ����h�̏ꍇtrue�A�ȊOfalse�B 
    		// ��萔�ʁF�@@�����P��.getExecutedQuantity() 
    		// ���v�����z�F�@@�����P��.getExecutedAmount() 
    		// isSkip���z�`�F�b�N�F�@@true 

            l_estimateResult =
                l_orderMgr.calcChangeEstimateDeliveryAmount(
                    l_commision,
                    l_dblChangedLimitPrice,
                    l_subAccount,
                    l_tradedProduct1,
                    l_dblChangedQuantity,
                    l_side,
                    l_blnIsSettleContract,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    true);
            log.debug("�������T�Z��n��� is:" + l_estimateResult.getCalcUnitPrice());

            //1.13.get�v�w�l�p�L����ԋ敪(�����P�� : IfoOrderUnit)
            // W�w�l�p�L����ԋ敪���擾����B 
    		//[�����̐ݒ�] 
    		// �����P�ʁF�@@�����P��
            String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_orderUnit);
    
    		//1.14.�������� && W�w�l���~�b�g�����L���̏ꍇ�i�����P��.������ʁ�"OP�V�K��������" 
            //&& get�v�w�l�p�L����ԋ敪()�̖߂�l == "���~�b�g�����L��")
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnit.getOrderType()) 
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
    			//1.14.1.setIs�w�l(is�w�l : boolean)
            	//[setIs�w�l()�Ɏw�肷�����] 
    			// is�w�l�F�@@�����P��.�iW�w�l�j�����w�l != 0 �̏ꍇ�́Atrue�B�ȊO�Afalse�B
                if (l_orderUnitRow.getWLimitPrice() != 0)
                {
                    l_commision.setIsLimitPrice(true);
                }
                else
                {
                    l_commision.setIsLimitPrice(false);
                }
                
    			//1.14.2.calc�������T�Z��n���(�萔��, double, SubAccount, �敨OP�������, 
                //		double, SideEnum, boolean, double, double, boolean)
    			//[calc�������T�Z��n���()�Ɏw�肷�����] 
    			// �萔���F�@@W�w�l�p�萔���I�u�W�F�N�g 
    			// �v�Z�P���F�@@�����P��.�iW�w�l�j�����w�l 
    			// �⏕�����F�@@�����P��.�⏕�����h�c�ɊY������⏕�����I�u�W�F�N�g 
    			// �敨OP��������F�@@�����P��.getTradedProduct() 
    			// ���ʁF �iget�����㐔��()�߂�l�j 
    			// �����F �����P��.getSide() 
    			// is�ԍϒ����F�@@ 
    			// �@@�����J�e�S�����h�ԍϒ����h�̏ꍇtrue�A�ȊOfalse�B 
    			// ��萔�ʁF�@@�����P��.getExecutedQuantity() 
    			// ���v�����z�F�@@�����P��.getExecutedAmount() 
    			// isSkip���z�`�F�b�N�F�@@true 
                l_wEstimateResult =
                    l_orderMgr.calcChangeEstimateDeliveryAmount(
                    l_commision,
                    l_orderUnitRow.getWLimitPrice(),
                    l_subAccount,
                    l_tradedProduct1,
                    l_dblChangedQuantity,
                    l_side,
                    l_blnIsSettleContract,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    true);
            }
        }

        //1.15.set�v�Z�P��(double)
        //[set�v�Z�P��()�Ɏw�肷�����] 
        // �v�Z�P���F�@@calc�������T�Z��n���()�̖߂�l���v�Z�P�����擾����
        //1.16.set�T�Z��n���
        //[set�T�Z��n���()�Ɏw�肷�����] 
        // �T�Z��n����F�@@calc�������T�Z��n���()�̖߂�l���T�Z��n������擾����

        //get�v�Z�P��
        double l_dblCalcUnitPrice = 0 ;

        //get�T�Z��n���
        double l_dblEstimateAmount = 0;

        if (l_estimateResult.getEstimateDeliveryAmount() > l_wEstimateResult.getEstimateDeliveryAmount())
        {
            l_dblCalcUnitPrice = l_estimateResult.getCalcUnitPrice();
            l_dblEstimateAmount = l_estimateResult.getEstimateDeliveryAmount();
        }
        else
        {
            l_dblCalcUnitPrice = l_wEstimateResult.getCalcUnitPrice();
            l_dblEstimateAmount = l_wEstimateResult.getEstimateDeliveryAmount();
        }

        log.debug("�v�Z�P�� is:" +l_dblCalcUnitPrice);
        log.debug("�T�Z��n��� is:" +l_dblEstimateAmount);

        //set�v�Z�P�� 
        l_ifoChangeCancelNotifyInterceptor.setCalcUnitPrice(l_dblCalcUnitPrice);

        //set�T�Z��n���
        l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(l_dblEstimateAmount);

        //1.17.�ԍ� �̏ꍇ�i�����P��.getOrderCateg()��"94�FOP�ԍϒ���"�j
		if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_intOrderCateg))
		{
			//1.17.1.:adjust�ԍώw����(long, long, long, long, double, double)
			//�ԍώw����e�[�u���̃f�[�^�������̃f�[�^�ɍX�V����B
			//[adjust�ԍώw����()�Ɏw�肷�����] 
			// �����h�c�F�@@�i�����P��.getAccountId()�̖߂�l�j 
			// �⏕�����h�c�F�@@�i�����P��.getSubAccountId()�̖߂�l�j 
			// �����h�c�F�@@�i�����P��.getOrderId()�̖߂�l�j 
			// �����P�ʂh�c�F�@@�i�����P��.getOrderUnitId()�̖߂�l�j 
			// �����O���ʁF�@@�i�����P��.getQuantity()�̖߂�l�j 
			// �����㐔�ʁF�@@�iOP��������ʒm�C���^�Z�v�^.get�����㐔��()�̖߂�l�j 
            WEB3IfoPositionManagerImpl  l_positionMgr =
                (WEB3IfoPositionManagerImpl) l_tradingMod.getPositionManager();
            l_positionMgr.adjustClosingContractSpecs(
                l_lngMainAccountId,
                l_lngSubAccountId,
                l_lngOrderId,
                l_lngOrderUnitId,
                l_dblQuantity,
                l_dblChangedQuantity
                );
        }
        
		//1.18.setBusinessTimestamp( )	
		//����J�����_�R���e�L�X�g.��t�����̍ăZ�b�g���s���B
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
        
        //1.19.get��������ʒm�敪
        String l_strChangeCancelNotifyDivision =
            l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision();
        log.debug("��������ʒm�敪" + l_strChangeCancelNotifyDivision);
        //�擾MarketAdapter
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        IfoMarketResponseReceiverCallbackService  l_receiverCallbackService =
            (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();                                                             
        //1.20.���������̏ꍇ�iget��������ʒm�敪 == �h���������h�j
        if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
        {
            //1.20.1.DefaultChangeOrderAcceptedMarketResponseMessage(����ID : long)
        	//�������ʁi���������j�I�u�W�F�N�g�𐶐�
			//[�R���X�g���N�^����] 
			// �����h�c�F�@@�i�擾���������h�c�j 
            DefaultChangeOrderAcceptedMarketResponseMessage l_marketAcceptedResponseMessage =
                new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);
            
            //1.20.2.process(�������� : ChangeOrderAcceptedMarketResponseMessage)
            //���������𒍕��ɍX�V����
			//[process()�Ɏw�肷�����] 
			// �������ʁF�@@�i���������������ʃI�u�W�F�N�g�j 
            ProcessingResult l_result = l_receiverCallbackService.process(l_marketAcceptedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            OrderUnit l_newOrderUnit = l_reOrderUnit;
            try
            {
                l_newOrderUnit =  l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�f�[�^�s�����G���[�B", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //1.20.3.isFullyExecuted( )
            boolean l_isFullyExecuted = l_newOrderUnit.isFullyExecuted();

            //1.20.4.������ɑS�����i�������� == ��萔�ʁj�����ꍇ�iisFullyExecuted()��true�j
			//[sendMainProcess()�Ɏw�肷�����] 
			// �����P�ʁF�@@�����P�ʃI�u�W�F�N�g 
			// �������R�R�[�h�F�@@null 
            if (l_isFullyExecuted)
            {   
                WEB3IfoExecutedMailSendService l_mailSendService = 
                    (WEB3IfoExecutedMailSendService) Services.
                    getService(WEB3IfoExecutedMailSendService.class);
            
                //sendMailProcess(OrderUnit, String)(�敨OP��胁�[�����M�T�[�r�XImpl::sendMailProcess)            
                l_mailSendService.sendMailProcess(l_newOrderUnit, null);                 
                
                //1.20.4.2.notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
				//[�����̐ݒ�] 
				// �����P�ʁF�@@�����P�ʃI�u�W�F�N�g 
				// �����F�@@ FILL_ORDER   
                //1.20.4.3.(*)notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
				//*)catch���ď����𑱍s����B
				//   �@@�����[���o�b�N���Ȃ��B
                try
                {
	                l_orderMgr.notifyRLS((IfoOrderUnit) l_newOrderUnit,
	                    OrderManagerPersistenceContext.FILL_ORDER); 
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                	log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[");
                	log.debug(this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        //1.21.�������s�̏ꍇ�iget��������ʒm�敪 == �h�������s�h�j
        if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strChangeCancelNotifyDivision))
        {
        	//1.21.1DefaultChangeOrderRejectedMarketResponseMessage(����ID : long)
			//[�R���X�g���N�^����] 
			// �����h�c�F�@@�i�擾���������h�c�j 
            //�������ʁi�������s�j�I�u�W�F�N�g�𐶐�
            DefaultChangeOrderRejectedMarketResponseMessage l_marketRejectedResponseMessage =
                new DefaultChangeOrderRejectedMarketResponseMessage(l_lngOrderId);
            //1.21.2process(�������s : ChangeOrderRejectedMarketResponseMessage)
			//[process()�Ɏw�肷�����] 
			// �������s�F�@@�i���������������s���ʃI�u�W�F�N�g�j 
            //�������s�𒍕��ɍX�V����
            ProcessingResult l_result = l_receiverCallbackService.process(l_marketRejectedResponseMessage);

            //1.21.2process(�������s : ChangeOrderRejectedMarketResponseMessage)��
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
         }
         
         if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
         {
            WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
         }
         
         log.exiting(STR_METHOD_NAME);          
    }
   
   /**
     * (notify���)<BR>
     * ����ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP��������ʒm�jnotify����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_ifoChangeCancelNotifyInterceptor - �敨OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g
     * @@roseuid 4084C37D0152
     */
    public String notifyCancel(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancel(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor)";
        log.entering(STR_METHOD_NAME);
         //�擾MarketAdapter
        MarketAdapter l_marketAdapter =
            l_tradingMod.getMarketAdapter();
        // get the service
        IfoMarketResponseReceiverCallbackService l_callbackServiceImpl =
             (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();                

        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }         
        IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoChangeCancelNotifyInterceptor);

		//�����P�ʂ��Ď擾����B�i�������b�N���������ꍇ���l���j
		OrderUnit l_reOrderUnit = l_orderUnit;
		try
		{
			l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
		}
		catch (NotFoundException l_nfe)
		{
			log.error("�f�[�^�s�����G���[�B", l_nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				this.getClass().getName() + STR_METHOD_NAME,
				l_nfe.getMessage(),
				l_nfe); 
		}
        //1.3 �擾��������ʒm�敪
        String l_strDivision = l_ifoChangeCancelNotifyInterceptor.getChangeCancelNotifyDivision();
        //�擾OrderId
        long l_lngOrderId = l_reOrderUnit.getOrderId();
        
        //1.4 get����������ʃR�[�h
        String l_strResultCode = l_ifoChangeCancelNotifyInterceptor.getChangeCancelResultCode();
        
        //1.5 get�����㐔��
        double l_dblChangedQuantity = l_ifoChangeCancelNotifyInterceptor.getChangedQuantity();
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }

        if  ((WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_strDivision))&&
        (l_dblChangedQuantity > l_dblExecutedQuantity))
        {
            return WEB3StatusDef.DEALING;                
        }
                
        //OP�����}�l�[�W�����擾
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

        //1.8 �iget��������ʒm�敪 == �h��������h�j�̏ꍇ
        if (WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_strDivision))
        {
	        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

            log.debug("�����㐔�� = " + l_dblChangedQuantity);
            //1.8.1 (get�����㐔�� > 0)�̏ꍇ���{�B
            if (l_dblChangedQuantity > 0)
            {
                double l_dblNetAmount = 0D;

                //1.8.1.1 get��n���z���v(IfoOrderUnit)
                l_dblNetAmount = l_orderMgr.getNetAmount((IfoOrderUnit)l_reOrderUnit);

                //1.8.1.2 set�T�Z��n���(double)
                l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(l_dblNetAmount);
            }
            
            //1.8.2 isSONAR���(IfoOrderUnit)
            boolean l_blnIsSonarCancel = l_orderMgr.isSONARCancel((IfoOrderUnit) l_orderUnit);

            //1.8.3 ������ʁi��������j�I�u�W�F�N�g�𐶐�����
            DefaultCancelOrderAcceptedMarketResponseMessage l_acceptedResponseMessage =
                new DefaultCancelOrderAcceptedMarketResponseMessage(
                l_lngOrderId);
            //1.8.4 ��������𒍕��ɍX�V����    
            
            ProcessingResult l_result = l_callbackServiceImpl.process(l_acceptedResponseMessage);
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //is�\�񒍕��m�F�v(IfoOrderUnit)
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist((IfoOrderUnit)l_orderUnit);

            //�\�񒍕��m�F�v�iis�\�񒍕��m�F�v() == true�j�̏ꍇ
            if (l_blnIsReserveOrderExist)
            {
                //cancelAll�\�񒍕��P��(�e�����̒���ID : long)
                WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);
                l_ifoOrderUpdateService.cancelAllOrderUnit(((IfoOrderUnit)l_orderUnit).getOrderId());
            }

            OrderUnit l_newOrderUnit = l_reOrderUnit;
            try
            {
                l_newOrderUnit =  l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�f�[�^�s�����G���[�B", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
        	//1.8.5.SONAR���͂̎���iisSONAR���()�̖߂�l == true�j�̏ꍇ	
            if (l_blnIsSonarCancel)
            {
            	//1.8.5.1.notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
            	//[�����̐ݒ�] 
            	//�����P�ʁF�@@�����P�ʃI�u�W�F�N�g 
            	//�����F�@@ CANCEL_ORDER_CONFIRMED_BY_MKT 
    			//*)catch���ď����𑱍s����B
    			//   �@@�����[���o�b�N���Ȃ��B
                try
                {
                    l_orderMgr.notifyRLS( (IfoOrderUnit)l_newOrderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT); 
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                	log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[");
                	log.debug(this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            
            //l_isUnexecuted = false( )
            boolean l_isUnexecuted = l_newOrderUnit.isUnexecuted();
            //sendMailProcess  
            //1.8.4 �S�����ς݂��𔻒肷��
            if (!l_isUnexecuted)
            {   
                WEB3IfoExecutedMailSendService l_mailSendService = 
                    (WEB3IfoExecutedMailSendService) Services.
                    getService(WEB3IfoExecutedMailSendService.class);
            
                //1.8.5 ��胁�[�����M�e�[�u���ɖ�胁-���s��}������    
                l_mailSendService.sendMailProcess(l_newOrderUnit, null);
            }
        }
        //1.9 ������s
        else if (WEB3IfoCanmodReceiptTypeDef.CANCELED_FAILED.equals(l_strDivision))
        {
            //get�v�w�l�p�L����ԋ敪(IfoOrderUnit)
            //W�w�l�̗L����ԋ敪���擾����B 
            //[����] 
            //�����P�ʁF�@@�����P��
            String l_strEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_reOrderUnit);

            //�X�g�b�v���������ƂȂ钍���igetW�w�l�p�L����ԋ敪() == "���~�b�g�����L��"�j�̏ꍇ
            if (WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strEnableStatusDiv))
            {
                WEB3GentradeSubAccount l_subAccount = null;
                try
                {
                    l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        l_orderUnit.getAccountId(),
                        l_orderUnit.getSubAccountId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //get�X�g�b�v�����������T�Z����v�Z����(IfoOrderUnit, �⏕����)
                //�X�g�b�v�����������̊T�Z��n����v�Z���ʂ��擾����B
                //[����]
                //�����P�ʁF�@@�����P��
                //�⏕�����F�@@�����P��.�⏕����ID�ɊY������⏕�����I�u�W�F�N�g
                WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult =
                    l_orderMgr.getStopOrderExpireEstimatedPrice((IfoOrderUnit)l_reOrderUnit, l_subAccount);

                double l_dblCalcUnitPrice = ((IfoOrderUnitRow)l_reOrderUnit.getDataSourceObject()).getPrice();
                double l_dblEstimatedPrice = ((IfoOrderUnitRow)l_reOrderUnit.getDataSourceObject()).getEstimatedPrice();
                if (l_estimateDeliveryAmountCalcResult != null)
                {
                    l_dblCalcUnitPrice = l_estimateDeliveryAmountCalcResult.getCalcUnitPrice();
                    l_dblEstimatedPrice = l_estimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
                }

                //set�v�Z�P��(double)
                //�v�Z�P�����Z�b�g����B
                //[����]
                //�v�Z�P���F�@@get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.�v�Z�P��
                //���߂�l��null�������ꍇ�́A�����P��.�����P�����Z�b�g�B
                l_ifoChangeCancelNotifyInterceptor.setCalcUnitPrice(l_dblCalcUnitPrice);

                //set�T�Z��n���(double)
                //�T�Z��n������Z�b�g����B
                //[����]
                //�T�Z��n����F�@@get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.�T�Z��n���
                //���߂�l��null�������ꍇ�́A�����P��.�T�Z��n������Z�b�g�B
                l_ifoChangeCancelNotifyInterceptor.setEstimateDeliveryAmount(l_dblEstimatedPrice);
            }
            
            IfoOrderUnit l_ifoOrderUnit = null;
            try
            {
                l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            } 
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
	        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

            //SONAR���͂̎���iisSONAR���()�̖߂�l == true�j�̏ꍇ
            if (l_orderMgr.isSONARCancel((IfoOrderUnit)l_orderUnit))
            {
                //1.9.3.notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
                //(OP�����}�l�[�W��::notify���[���G���W���T�[�o)
        		//[�����̐ݒ�] 
        		// �����P�ʁF�@@�����P�ʃI�u�W�F�N�g 
        		// �����F�@@ CANCEL_ORDER_REJECTED_BY_MKT
            	//1.9.4.notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
                try
                {
                    l_orderMgr.notifyRLS(l_ifoOrderUnit,
                        OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT); 
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                	log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[");
                	log.debug(this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            //1.9.1 ������ʁi������s�j�I�u�W�F�N�g�𐶐�����
            DefaultCancelOrderRejectedMarketResponseMessage l_rejectedResponseMessage =
                new DefaultCancelOrderRejectedMarketResponseMessage(l_lngOrderId);
            //1.9.2 ������s�𒍕��ɍX�V����             
            ProcessingResult l_result = l_callbackServiceImpl.process(l_rejectedResponseMessage); 
            if (l_result.isFailedResult())
            {
                throw new WEB3SystemLayerException(
                    l_result.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        WEB3GentradeSubAccount l_subAccount = null;
        try
        { 
            //�ڋqId
            long l_lngMainAccountId = l_reOrderUnit.getAccountId();
            //�擾�⏕����         
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().
               getSubAccount(l_lngMainAccountId, l_reOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�f�[�^�s�����G���[�B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe); 
        }

        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
				(WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
			l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
        }
        log.exiting(STR_METHOD_NAME);
        return WEB3StatusDef.DEALT;
    }
   
    /**
     * (is�����X�V��)<BR>
     * �w�蒍���P�ʂ������X�V�ς݂��𔻒肷��B<BR>
     * <BR>
     * �|�����X�V�ς݂̏ꍇ��true��ԋp����B<BR>
     * �|�����X�V���Ă��Ȃ��iHOST����̒������́j�ꍇ��false��ԋp����B<BR>
     * <BR>
     * �����̒����P�ʃI�u�W�F�N�g.������Ԃ��ȉ��̏ꍇtrue��ԋp����B<BR>
     * �@@�h��t�ρi�ύX�����j�h�FOrderStatusEnum.MODIFY_ACCEPTED<BR>
     * �@@�h�������i�ύX�����j�h�FOrderStatusEnum.MODIFYING<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@param l_orderUnit - �����P��
     * @@return boolean
     * @@roseuid 4084F4EB01FC
     */
    protected  boolean isChangeUpdateEnd(OrderUnit l_orderUnit)     
    {
  
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if ((OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)) ||
           (OrderStatusEnum.MODIFYING.equals(l_orderStatus)))
        {
            log.debug("Enter the path that the value is ture.");
            return true;      
        }
        else
        {
            log.debug("Enther the path that the value is false."); 
            return false;
        }
    }
    
    /**
     * (is����X�V��)<BR>
     * �w�蒍���P�ʂ�����X�V�ς݂��𔻒肷��B<BR>
     * <BR>
     * �|����X�V�ς݂̏ꍇ��true��ԋp����B<BR>
     * �|����X�V���Ă��Ȃ��iHOST����̎�����́j�ꍇ��false��ԋp����B<BR>
     * <BR>
     * �����̒����P�ʃI�u�W�F�N�g.������Ԃ��ȉ��̏ꍇtrue��ԋp����B<BR>
     * �@@�h��t�ρi��������j�h�FOrderStatusEnum.CANCEL_ACCEPTED<BR>
     * �@@�h�������i��������j�h�FOrderStatusEnum.CANCELLING<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@param l_orderUnit - �����P��
     * @@return boolean
     * @@roseuid 4084F6F90299
     */
    public boolean isCancelUpdateEnd(OrderUnit l_orderUnit) 
    {
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if ((OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)) ||
           (OrderStatusEnum.CANCELLING.equals(l_orderStatus)))
        {
            log.debug("Enter the path that the value is ture."); 
            return true;      
        }
        else
        {
            log.debug("Enter the path that the value is false."); 
            return false;
        }
    }
}
@
