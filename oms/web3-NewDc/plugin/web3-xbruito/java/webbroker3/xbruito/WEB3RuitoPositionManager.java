head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���ݓ��|�W�V�����}�l�[�W��(WEB3RuitoPositionManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09  ���u�� (���u) �V�K�쐬
                   2004/12/01  ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoAsset;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CashBalanceflagDef;
import webbroker3.util.WEB3LogUtility;
/**
 * �g���ݓ��|�W�V�����}�l�[�W��<BR>
 */
public class WEB3RuitoPositionManager extends RuitoPositionManagerImpl
{
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoPositionManager.class);
	/**
     * (get�ݓ��ۗL���Y����)<BR>
	 * �ڋq�̗ݓ��ۗL���Y�̏����擾����B<BR>
	 * <BR>
	 * �V�[�P���X�}�uget�ݓ��ۗL���Y���ׁv�Q��<BR>
	 *
	 * �P�j�ݓ���񒍕��擾<BR>
	 * �@@�|�ȉ��̏����ŗݓ������P�ʃe�[�u�����������A<BR>
	 * �@@�@@�@@�ݓ������P��Params��List���擾����B<BR>
	 * <BR>
	 * �@@�@@[��������]<BR>
	 * �@@�@@   ����ID = ����.�⏕����.getAccountId()�̖߂�l <BR>
	 * �@@�@@   ����ID = ����.�ݓ��ۗL���Y����.get����ID()�̖߂�l <BR>
	 * �@@�@@�@@ ������ʁ@@=�@@OrderTypeEnum.RUITO_SELL<BR>
	 * �@@�@@�@@ �����^�C�v�@@=�@@7�F�ݐϓ��� <BR>
	 * �@@�@@�@@ �c���v��t���O = 0�F�c���v�㖢�� <BR>
	 * �@@�@@�@@ ������ԁ@@=�@@1�F��t�ρi�V�K�����j�@@or�@@3�F�����ρi�V�K�����j <BR>
	 * <BR>
	 * �@@�|�ݓ������P��Params.get��������()�̍��v���Z�o���āA<BR>
	 * �@@�@@�@@����.�ݓ��ۗL���Y����.��񒍕����v�ɐݒ肷��B<BR>
	 *     �Y������ݓ������P�ʂ��P���������ꍇ�́A0 ��ݒ肷��B<BR>
	 * <BR>
	 * �Q�j ���\�c���v�Z<BR>
	 * <BR>
	 * �@@this.calc���\�c��()���R�[�����A<BR>
	 *     ����.�ݓ��ۗL���Y���ׂɐM�����Y���ۊz��<BR>�@@
	 *      ���\�c����ݒ肷��B<BR>
	 * �@@�mcalc���\�c���ɓn���p�����^�n<BR>
	 * �@@�@@ �ݓ��ۗL���Y���ׁF ����.�ݓ��ۗL���Y����<BR>
	 * <BR>
	 * �R�j ��񒍕����אݒ� <BR>�@@�@@
	 *   �P�j�Ŏ擾�����ݓ������P�ʂ̌��� �� 0 �̏ꍇ�A�ȉ��̏������s���B<BR>
	 * <BR>
	 *     �|�ݓ���񒍕����׃I�u�W�F�N�g�̔z�� <BR>
	 *        �i�v�f���͎擾�����ݓ������P�ʂ̌���) <BR>
	 *          �𐶐����A����.�ݓ��ۗL���Y����.��񒍕����ׂɐݒ肷��B<BR>
	 * �@@  �|�e�ݓ���񒍕����׃I�u�W�F�N�g�Ɉȉ��̐ݒ���s�� �B<BR>
	 * <BR>
	 *  �ݓ���񒍕�����.�������ԁi������t��) <BR>
	 *      �F�ݓ������P��Params.get��������() <BR> �@@�@@
	 *  �ݓ���񒍕�����.������ԋ敪 <BR>
	 *      �F�ݓ������P��Params.get������ԋ敪() <BR>
	 *  �ݓ���񒍕�����.�������ʃ^�C�v <BR>
	 *      �F�ݓ������P��Params.get�������ʃ^�C�v() <BR>�@@�@@
	 *  �ݓ���񒍕�����.�������� <BR>
	 *      �F�ݓ������P��Params.get��������() <BR>�@@�@@
	 * @@param l_subAccount - �⏕����<BR>
	 * @@param l_ruitoAssetDetail - �ݓ��ۗL���Y����<BR>�@@�@@�@@�@@�@@
     * @@throws WEB3BaseException
	 */
	public void getRuitoAssetGroup(
		SubAccount l_subAccount,
		WEB3RuitoAssetDetail l_ruitoAssetDetail)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
		    "getRuitoAssetGroup(SubAccount l_subAccount, "
		     + "WEB3RuitoAssetDetail l_ruitoAssetDetail)";
		
		log.entering(STR_METHOD_NAME);
		
		if (l_subAccount == null || l_ruitoAssetDetail == null)
		{
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
		}

		//�P�j�ݓ���񒍕��擾
		List l_lisResults = null;
		long l_lngAccountId = 0; //����ID
		long l_lngProductId = 0; //����ID
		l_lngAccountId = l_subAccount.getAccountId();
		l_lngProductId = l_ruitoAssetDetail.getProductID();
        
		QueryProcessor processor = null;
		try
		{
			log.debug("����ID = " + l_lngAccountId);
			log.debug("����ID = " + l_lngProductId);

			processor = Processors.getDefaultProcessor();
			String l_strWhere =   " account_id = ? and product_id = ?         "
					            + " and order_type = ? and product_type=?     "
					            + " and balance_add_flag = ?                  "
					            + " and  (order_status =? or order_status =?) ";
			String l_strCondition = " nowait";
			Object[] l_strBindValues = new Object[7];
			l_strBindValues[0] = "" + l_lngAccountId;
			l_strBindValues[1] = "" + l_lngProductId;
			l_strBindValues[2] = OrderTypeEnum.RUITO_SELL;
			l_strBindValues[3] = ProductTypeEnum.RUITO;
			l_strBindValues[4] = WEB3CashBalanceflagDef.CASH_BALANCE_NO_CLEAR;
			l_strBindValues[5] = OrderStatusEnum.ACCEPTED;
			l_strBindValues[6] = OrderStatusEnum.ORDERED;
			//�ݓ������P��Params��List���擾����B
			//DataQueryException �܂��́@@DataNetworkException��throw

            l_lisResults = 
            	processor.doFindAllQuery(
            		RuitoOrderUnitParams.TYPE,
            		l_strWhere,
            		null,
            		l_strBindValues);
		}
		
        catch (DataQueryException l_ex)
        {
            log.debug("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
		//�Y������ݓ������P�ʂ��P���������ꍇ�́A0 ��ݒ肷��B
		if (l_lisResults == null || l_lisResults.size() == 0)
		{
			log.debug("[getRuitoAssetGroup] l_results.size() = 0");
			l_ruitoAssetDetail.setSellOrderTotal(0);
		}
		else
		{ 
			log.debug("[getRuitoAssetGroup] l_results.size() = " + l_lisResults.size());
			
			//�ݓ������P��Params.get��������()�̍��v���Z�o
			double l_dblSum = 0;
			for (int i = 0; i < l_lisResults.size(); i++)
			{
				RuitoOrderUnitParams ruitoOrderUnitParams =
					(RuitoOrderUnitParams) l_lisResults.get(i);
				l_dblSum += ruitoOrderUnitParams.getQuantity();
			}
			
			log.debug("[getRuitoAssetGroup] l_dblSum = " + l_dblSum);

			//���v��ݓ��ۗL���Y����.��񒍕����v�ɐݒ肷��B
			l_ruitoAssetDetail.setSellOrderTotal(l_dblSum);
		}
		//�Q�j���\�c���v�Z
		this.calcSellPossibleBalance(l_ruitoAssetDetail);
				
		//�R�j��񒍕����אݒ�
		//�ݓ������P�ʂ̌��� �� 0 �̏ꍇ
		if (l_lisResults != null && l_lisResults.size() > 0)
		{
			WEB3RuitoSellOrderDetail[] l_sellOrderDetail =
				new WEB3RuitoSellOrderDetail[l_lisResults.size()];
			
			//��񒍕����ׂɐݒ肷��
			for (int i = 0; i < l_lisResults.size(); i++)
			{
				RuitoOrderUnitParams ruitoOrderUnitParams =
					(RuitoOrderUnitParams) l_lisResults.get(i);
					
				log.debug("[getRuitoAssetGroup] i = " + i);
				log.debug("[getRuitoAssetGroup] OrderTime = " 
					+ ruitoOrderUnitParams.getReceivedDateTime());
				log.debug("[getRuitoAssetGroup] OrderStatusDiv = " 
					+ ruitoOrderUnitParams.getOrderStatus().intValue());
				log.debug("[getRuitoAssetGroup] OrderQuantityType = " 
					+ ruitoOrderUnitParams.getQuantityType().intValue());
				log.debug("[getRuitoAssetGroup] Quantity = " 
					+ ruitoOrderUnitParams.getQuantity());

				l_sellOrderDetail[i] = new WEB3RuitoSellOrderDetail();
				
				//��������
				l_sellOrderDetail[i].setOrderTime(
					ruitoOrderUnitParams.getReceivedDateTime());
				//������ԋ敪
				l_sellOrderDetail[i].setOrderStatusDiv(
					ruitoOrderUnitParams.getOrderStatus().intValue() + "");
				//�������ʃ^�C�v
				l_sellOrderDetail[i].setOrderQuantityType(
					ruitoOrderUnitParams.getQuantityType().intValue() + "");
				//��������
				l_sellOrderDetail[i].setOrderQuantity(
					ruitoOrderUnitParams.getQuantity());
			}
			//�ݓ��ۗL���Y���ׂ̉�񒍕����ׂɐݒ肷��
			l_ruitoAssetDetail.setSellOrderDetail(l_sellOrderDetail);
		}
		
		log.exiting(STR_METHOD_NAME);
	}
	/**
	 * ���\�c�����v�Z���ĕԋp����<BR>
	 * <BR>
	 * 1) �|�M�����Y���ۊz���Z�o����B<BR>
	 * <BR>
	 * �@@�@@�@@�@@30�����o�ߎc���� 0 �̏ꍇ�́A<BR>
	 * �@@�@@�@@�@@�@@�@@�M�����Y���ۊz = 0<BR>
	 * �@@�@@�@@�@@30�����o�ߎc���� 0 �ȊO�̏ꍇ�́A<BR>
	 * �@@�@@�@@�@@�@@�@@�M�����Y���ۊz�@@=�@@<BR>
	 * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ݓ��ۗL���Y����.30�����o�ߎc���@@�~�@@0.001 <BR>
	 * <BR>
	 * �@@�@@�@@�@@�Z�o�����M�����Y���ۊz���A�ݓ��ۗL���Y����.�M�����Y���ۊz<BR>
	 * �@@�@@�@@�@@�ɐݒ肷��B<BR>
	 * <BR>
	 * 2)  �|����.�ݓ��ۗL���Y���ׂ̎c���A��񒆒������v���A<BR>
	 *       ���\�c�����v�Z����. <BR>
	 * �@@  �|���\�c�����v�Z����B�@@<BR>
	 * �@@ �@@�@@ ���\�c���@@=�@@�ݓ��ۗL���Y����.�c�� <BR>
	 * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�| �ݓ��ۗL���Y����.�M�����Y���ۊz <BR>
	 * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�| �ݓ��ۗL���Y����.��񒆒������v<BR>
	 * <BR>
	 * �@@�@@�@@�@@�Z�o�������\�c�����A<BR>
	 *         �ݓ��ۗL���Y����.���\�c���ɐݒ肷��B<BR>
	 * <BR>
	 * @@param l_ruitoAssetDetail - �ݓ��ۗL���Y����<BR>
     * @@throws WEB3BaseException
	 * @@roseuid 40765742020D
	 */
	public void calcSellPossibleBalance(WEB3RuitoAssetDetail l_ruitoAssetDetail) 
        throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"calcSellPossibleBalance(WEB3RuitoAssetDetail l_ruitoAssetDetail)";				
		log.entering(STR_METHOD_NAME);
		
		if (l_ruitoAssetDetail == null)
		{			
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
		}
		
		log.debug("balance = " + l_ruitoAssetDetail.getBalance());
		log.debug("sellOrderTotal = " + l_ruitoAssetDetail.getSellOrderTotal());
		log.debug("countBeforePenalty = " + l_ruitoAssetDetail.getCountBeforePenalty());

		double l_dblPenaltyPrice = 0.0; //�M�����Y���ۊz
		//30�����o�ߎc���� 0 �̏ꍇ
		if (l_ruitoAssetDetail.getCountBeforePenalty() == 0.0)
		{
			l_ruitoAssetDetail.setTrustFortunePenaltyPrice(l_dblPenaltyPrice);
		}
		//30�����o�ߎc���� 0 �ȊO�̏ꍇ
		else
		{
			l_dblPenaltyPrice =
				l_ruitoAssetDetail.getCountBeforePenalty() * 0.001;
            l_dblPenaltyPrice = (int)l_dblPenaltyPrice;
            l_ruitoAssetDetail.setTrustFortunePenaltyPrice(l_dblPenaltyPrice);
		}
		
		log.debug("trustFortunePenaltyPrice = " + l_ruitoAssetDetail.getTrustFortunePenaltyPrice());
		
		//���\�c�����v�Z
        log.debug("�c�� = " + l_ruitoAssetDetail.getBalance());
        log.debug("�M�����Y���ۊz = " + l_ruitoAssetDetail.getTrustFortunePenaltyPrice());
        log.debug("��񒍕����v = " + l_ruitoAssetDetail.getSellOrderTotal());
        
		double l_dblBalance = l_ruitoAssetDetail.getBalance()
				              - l_ruitoAssetDetail.getTrustFortunePenaltyPrice()
				              - l_ruitoAssetDetail.getSellOrderTotal();
		//���\�c���ɐݒ肷��
		l_ruitoAssetDetail.setSellPossibleBalance(l_dblBalance);
		
		log.exiting(STR_METHOD_NAME);
	}
	/**
	 * �iupdateLockedQuantity�̃I�[�o�[���C�h�j<BR>
	 * <BR>
	 * �����������s��Ȃ��B<BR>
	 * @@param l_lngAccountID - ����ID<BR>
	 * @@param l_lngSubAccountID - �⏕����ID<BR>
	 * @@param l_lngOrderUnitID - �����P��ID<BR>
	 * @@param l_lngProductID - ����ID<BR>
	 * @@param l_dblLockedQuantity - ���b�N���悤�Ƃ���v���X���}�C�i�X�̐���<BR>
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	 * @@roseuid 4092096E033B
	 */
	public void updateLockedQuantity(
		long l_lngAccountID,
		long l_lngSubAccountID,
		long l_lngOrderUnitID,
		long l_lngProductID,
		double l_dblLockedQuantity)
		throws RuntimeSystemException
	{
	}	
    
    /**
     * (get���\�c��)
     * ���\�c�����擾����B<BR>
     * <BR>
     * �P�D�g���ݓ��|�W�V�����}�l�[�W���[.getAseet���R�[�����āA�w������ۗ̕L���Y<BR>
     * �@@�@@�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mgetAsset�ɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����<BR>
     * �@@�@@�@@�����F ����.�g���ݓ�����<BR>
     * �@@�@@getAsset()����O���X���[�����ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �Q�D�ݓ��ۗL���Y���׃I�u�W�F�N�g�𐶐����A�ۗL���Y�̏���ݒ肷��B<BR>
     * �@@�@@�m�ݓ��ۗL���Y���׃I�u�W�F�N�g�ɐݒ肷��l�n<BR>
     * �@@�@@�@@�@@����ID�F �ۗL���Y�I�u�W�F�N�g.getProduct().getProductId()�̖߂�l<BR>
     * �@@�@@�@@�@@�c���F �ۗL���Y�I�u�W�F�N�g.getQuantity()�̖߂�l<BR>
     * �@@�@@�@@�@@30�����o�ߎc�������F �ۗL���Y�I�u�W�F�N�g.getDataSourceObject().<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@get30�����o�ߎc�������̖߂�l<BR>
     *         �ݓ��^�C�v�F����.�g���ݓ�����.get�ݓ��^�C�v()�̖߂�l<BR>
     * <BR>
     * �R�D�ݓ��g���|�W�V�����}�l�[�W��.get�ݓ��ۗL���Y����()���R�[�����A<BR>
     * �@@�@@�@@�����P�ʂۗ̕L���Y���ׂ��擾����B<BR>
     * �@@�@@�mget�ݓ��ۗL���Y���ׂɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����<BR>
     * �@@�@@�@@�ݓ��ۗL���Y���ׁF ���������ݓ��ۗL���Y����<BR>
     * <BR>
     * �S�D�ݓ��ۗL���Y����.���\�c����ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_ruitoProducts - (�g���ݓ�����)
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 41084E1D02C5
     */     
    public double getSellPossibleBalance(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getSellPossibleBalance(" +
            "SubAccount l_subAccount,WEB3RuitoProduct l_ruitoProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ruitoProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        //�P�D�g���ݓ��|�W�V�����}�l�[�W���[.getAseet���R�[�����āA
        //�w������ۗ̕L���Y�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3RuitoPositionManager l_web3RuitoPositionManager =
            (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getPositionManager();

        RuitoAsset l_ruitoAsset = null;
        try
        {
            l_ruitoAsset = (RuitoAsset)l_web3RuitoPositionManager.getAsset(
                l_subAccount, l_ruitoProduct);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�D�ݓ��ۗL���Y���׃I�u�W�F�N�g�𐶐����A�ۗL���Y�̏���ݒ肷��B
        AssetRow l_ruitoAssetRow =  
            (AssetRow) l_ruitoAsset.getDataSourceObject();
        //30�����o�ߎc������
        double l_dblCountBeforePenalty = l_ruitoAssetRow.getCountBeforePenalty();
        RuitoTypeEnum l_ruitoTypeEnum = l_ruitoProduct.getRuitoType();

        WEB3RuitoAssetDetail l_web3RuitoAssetDetail =
            new WEB3RuitoAssetDetail(
                l_ruitoAsset.getProduct().getProductId(),
                l_ruitoAsset.getQuantity(),
                l_dblCountBeforePenalty,
                l_ruitoTypeEnum);
    
        //�R�D�ݓ��g���|�W�V�����}�l�[�W��.get�ݓ��ۗL���Y����()���R�[�����A     
        //�����P�ʂۗ̕L���Y���ׂ��擾����B
        l_web3RuitoPositionManager.getRuitoAssetGroup(
                l_subAccount, l_web3RuitoAssetDetail);       
                
        l_web3RuitoPositionManager.calcSellPossibleBalance(l_web3RuitoAssetDetail);        
        
        log.exiting(STR_METHOD_NAME);    
        //�S�D�ݓ��ۗL���Y����.���\�c����ԋp����B
        return l_web3RuitoAssetDetail.getSellPossibleBalance();     
    }
}
@
