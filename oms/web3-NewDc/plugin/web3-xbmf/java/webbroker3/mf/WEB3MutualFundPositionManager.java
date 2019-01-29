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
filename	WEB3MutualFundPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����M�|�W�V�����}�l�[�W��(WEB3MutualFundPositionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 �����(���u) �V�K�쐬
Revesion History : 2004/08/20 ��O�� (���u) ���r���[   
Revesion History : 2004/12/06 ������ (���u) �c�Ή� 
Revesion History : 2006/03/28 �R�� (SRA) �d�l�ύX���f��No.405�Ή�
Revesion History : 2006/03/28 �R�� (SRA) �d�l�ύX���f��No.405�Ή� ���������̕s���C��
Revesion History : 2006/05/15 ���� (���u) �d�l�ύX�i���f���j�F413
Revesion History : 2007/02/05 ������ (���u) ���f��532,539
Revesion History : 2007/02/16 �đo�g (���u) �d�l�ύX�E���f��541
*/

package webbroker3.mf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecStatusDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.mf.data.MfSubAssetRow;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g�����M�|�W�V�����}�l�[�W��)<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3MutualFundPositionManager extends MutualFundPositionManagerImpl 
{
    
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualFundPositionManager.class);

    /**
     * �iupdateLockedQuantity�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����������s��Ȃ��B<BR>
     * @@param l_lngAccountId - ����ID<BR>
     * @@param l_lngSubAccountId - �⏕����ID<BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@param l_lngProductId - ����ID<BR>
     * @@param l_dblLockedQuantity - (���b�N����)<BR>
     * ���b�N���悤�Ƃ���v���X���}�C�i�X�̐���<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
     * @@roseuid 40A97684000F
     */
    public void updateLockedQuantity(
        long l_lngAccountId, 
        long l_lngSubAccountId, 
        long l_lngOrderUnitId, 
        long l_lngProductId, 
        double l_dblLockedQuantity) 
        throws RuntimeSystemException 
    {
        // �����������s��Ȃ��B
    }
    
    /**
     * (calc���\�c������)<BR>
     * ���Y�ڋq�ۗ̕L����A���݉��\�ȓ����M���̉��\�c��������Ԃ��B<BR>
     * <BR>
     * �P�j�@@�ۗL���Y�e�[�u�����c�����擾����B<BR>
     * �@@�|this.getAsset()���R�[�����A�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mgetAsset�ɓn���p�����^�n<BR>
     * �@@�@@�@@�A�Z�b�gID�F ����.���YID <BR>
     * �@@�@@�|getAsset()��NotFoundException���X���[�����ꍇ�́A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00204<BR>
     * �@@�@@�|�擾�����ۗL���Y�I�u�W�F�N�g.getQuantity()���R�[�����Ďc�����擾����B<BR>
     * <BR>
     * �Q�j�@@this.calc��񒆊T�Z����()���R�[�����A��񒆊T�Z�������擾����B<BR>
     * �@@�@@�mcalc��񒆊T�Z�����ɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�����F�@@����.�g�����M�����I�u�W�F�N�g�B<BR>
     *      �ŋ敪:�F�@@getAsset()�Ŏ擾�������Y�̐ŋ敪<BR> 
     *      �ۗL���Y�F�@@getAsset()�̖߂�l <BR> 
     * <BR>
     * �R�j�@@�c�� - ��񒆊T�Z������Ԃ��B<BR>
     * �@@�@@�@@
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_strAssetId - ���YID
     * @@return double
     * @@roseuid 40B2D8FF008C
     */
    public double calcSellPossiblePositionQty(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "calcSellPossiblePositionQty("
            + "SubAccount l_subAccount, "
            + "WEB3MutualFundProduct l_mutualFundProduct, String l_strAssetId))";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundProduct == null || l_strAssetId == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // �P�j�@@�ۗL���Y�e�[�u�����c�����擾����B
            // �mgetAsset�ɓn���p�����^�n
            // �A�Z�b�gID�F ����.���YID 
            Asset l_asset = this.getAsset(Long.parseLong(l_strAssetId));
            BigDecimal l_bdQuantity =
            	new BigDecimal(Double.toString(l_asset.getQuantity()));
            log.debug("�ۗL���Y�̎c�� = " + l_bdQuantity);

            // �Q�j�@@this.calc��񒆊T�Z����()���R�[�����A��񒆊T�Z�������擾����B
            // �mcalc��񒆊T�Z�����ɓn���p�����^�n 
            // �⏕�����F ����.�⏕�����I�u�W�F�N�g 
            // �����F�@@����.�g�����M�����I�u�W�F�N�g 
            // �ŋ敪:�F�@@getAsset()�Ŏ擾�������Y�̐ŋ敪
            // �ۗL���Y�F�@@getAsset()�̖߂�l <BR> 
            double l_dblSellingEstimatedQty = 
                this.calcSellingEstimatedQty(
                    l_subAccount, 
                    l_mutualFundProduct,
                    l_asset.getTaxType(), 
                    l_asset);
            
            BigDecimal l_bdSellingEstimatedQty =
            	new BigDecimal(Double.toString(l_dblSellingEstimatedQty));
            
			log.debug("��񒆊T�Z���� = " + l_bdSellingEstimatedQty);

            // �R�j�@@�c�� - ��񒆊T�Z������Ԃ��B
            log.exiting(l_strMethodName);
			return l_bdQuantity.subtract(l_bdSellingEstimatedQty).doubleValue();
        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y������ۗL���Y������܂��� for SubAccountId = " 
                + l_subAccount.getSubAccountId() + " ProductId = " 
                + l_mutualFundProduct.getProductId());
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
    }
    
    /**
     * (calc��񒆊T�Z����)<BR>
     * ���Y�ڋq�́A���݉�񒆂̊T�Z������Ԃ��B<BR>
     * <BR>
     * �P�j�@@���M�����P�ʃe�[�u�����������A��񒍕������̑��v���v�Z����B<BR>
     * �@@�|���M�����P�ʃe�[�u�����������A���M�����P��Params��List���擾����B<BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId()<BR>
     * �@@�@@�@@AND �⏕����ID = ����.�⏕����.getSubAccountId()<BR>
     * �@@�@@�@@AND ����ID = ����.�g�����M����.getProductId()<BR> �@@�@@�@@
     * �@@�@@�@@AND (������� = OrderStatusEnum.��t�ρi�V�K�����j OR �������<BR>
     * = OrderStatusEnum.�����ρi�V�K�����j)<BR>
     *       AND �ŋ敪 = ����.�ŋ敪 <BR>
     * �@@�@@�@@AND (  (������� = OrderTypeEnum.�����M�������� <BR>
     * �@@�@@�@@                 AND ����� != �h2�F���ρh) <BR>
     *           OR (������� = OrderTypeEnum.�����M���抷�����@@AND
     *                   (����� is null OR (����� = �h1�F��蒆�hAND ���� = �抷������) ) )  )
     *      
     * �@@�|�擾�������M�����P��Params�̊T�Z���������iget�T�Z��������()�ɂĎ擾�j<BR>
     * �̑��v���v�Z����B<BR>
     * �i*)(�g�����M����.�ʉ݃R�[�h != �~�� ����get���M���敪 = �S��) �̏ꍇ�A <BR>
     *     ���v = get��������() ���Z�b�g����B<BR>
     * <BR>
     * �Q�j�@@�擾������񒍕������̑��v��Ԃ��B
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_taxType - �ŋ敪
     * @@param l_asset - �ۗL���Y
     * @@return double
     * @@roseuid 40B2E37F033C
     */
    public double calcSellingEstimatedQty(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        TaxTypeEnum l_taxType, 
        Asset l_asset)
        throws WEB3BaseException
    {
        final String l_strMethodName = "calcSellingEstimatedQty(" +
            "SubAccount, WEB3MutualFundProduct, String, Asset)";
        log.entering(l_strMethodName);
        
        if (l_subAccount == null || l_mutualFundProduct == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // �P�j�@@���M�����P�ʃe�[�u�����������A��񒍕������̑��v���v�Z����B
            List l_lisMFOrderUnits = new ArrayList();

            String l_strWhere = 
			    "account_id = ? and sub_account_id = ? "
			    + "and product_id = ? "
			    + "and (order_status = ? or order_status = ?) "
			    + "and tax_type = ? "
			    + "and (   (order_type = ? and (exec_status != ? or exec_status is null) ) "
			    + "     or (order_type = ? and (exec_status is null or (exec_status = ? and exec_date = swt_exec_date) ) ) ) ";

            Object[] l_objWhereValues = {
                new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mutualFundProduct.getProductId()),
                // OrderStatusEnum.ACCEPTED ---- 1�F��t�ρi�V�K�����j
				OrderStatusEnum.ACCEPTED,
				// OrderStatusEnum.ORDERED --- 3�F�����ρi�V�K�����j
				OrderStatusEnum.ORDERED,
                // �ŋ敪 = ����.�ŋ敪 
				l_taxType,                                
                // OrderTypeEnum.MF_SELL --- 202�F�����M��������
                OrderTypeEnum.MF_SELL,
                // ����� != �h2�F���ρh
				WEB3ExecStatusDef.EXECUTED_COMPLETE,
                // OrderTypeEnum.MF_SWITCHING --- 204�F�����M���抷����
                OrderTypeEnum.MF_SWITCHING,
				// ����� = �h1�F��蒆�h
				WEB3ExecStatusDef.EXECUTED_IN_PROCESS,            
            };
            // -���M�����P�ʃe�[�u�����������A���M�����P��Params��List���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisMFOrderUnits = l_queryProcessor.doFindAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhere,
                l_objWhereValues);
            
            if (l_lisMFOrderUnits == null || l_lisMFOrderUnits.size() == 0)
            {
                return 0;
            }            
            
            // �T�Z��������(�W�v�p)   
            BigDecimal l_bdEstimateDealingQty = new BigDecimal("0");
			// �T�Z��������(��Ɨp)
			BigDecimal l_bdMfEstimateDealingQty = null;
			
            // �擾�������M�����P��Params�̊T�Z���������̑��v���v�Z����B
            for (int i = 0; i < l_lisMFOrderUnits.size(); i ++)
            {
                //�擾�������M�����P��Params
                MutualFundOrderUnitRow l_orderUnitRow = 
                    (MutualFundOrderUnitRow)l_lisMFOrderUnits.get(i);

                
                //�i*)(�g�����M����.�ʉ݃R�[�h != �~�� ����get���M���敪 = �S��) �̏ꍇ�A
				//    ���v = get��������() ���Z�b�g����B                 	
                if ( (!WEB3MFOrderQuantityType.EN.equals( l_mutualFundProduct.getCurrencyCode())) 
                  && ( WEB3SellDivDef.ALL_DESIGNATE.equals(l_orderUnitRow.getFundSellDiv()))       )
                {	
                	                	
					l_bdMfEstimateDealingQty =
                        new BigDecimal(Double.toString(l_orderUnitRow.getQuantity()));                                        
                }                
                else
                {
                	                    
	                //�T�Z���������̑��v���v�Z����B
	                l_bdMfEstimateDealingQty =
	                    new BigDecimal(Double.toString(l_orderUnitRow.getEstimateDealingQty()));
	                
                }
				l_bdEstimateDealingQty =
					l_bdEstimateDealingQty.add(l_bdMfEstimateDealingQty);
            }
            
            // �Q�j�@@�擾������񒍕������̑��v��Ԃ��B
            log.exiting(l_strMethodName);
            return l_bdEstimateDealingQty.doubleValue();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In ���M�����P�ʃe�[�u���������� ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In ���M�����P�ʃe�[�u���������� ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
    }
    
    /**
     * (calc�]���z)<BR>
     * ���Y�ڋq�ۗ̕L��������̕]���z���Z�o����B<BR>
     * <BR>
     * �P)�@@���\�c�������擾<BR>
     * �@@this.calc���\�c������()���R�[�����A���\�c���������擾����B<BR>
     * �@@�@@�mcalc���\�c�������ɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����<BR>
     * �@@�@@�@@�g�����M�����F ����.�g�����M����<BR>
     *      ���YID�F�@@����.���YID <BR>
     * <BR>
     * �Q)�@@�]���z�Z�o<BR>
     * �@@(2-1) �g�����M�����I�u�W�F�N�g�̒ʉ݃R�[�h��T0�̏ꍇ<BR> 
     *     �@@ �]���z���Z�o���A���ʂ����^�[������B�i�����_�ȉ��l�̌ܓ��j<BR> 
     * �@@        �]���z �� (����.�g�����M����.get��񉿊z() �~ ���\�c������)�@@�^�@@<BR> 
     *�@@�@@           ����.�g�����M����.get����z�v�Z�P��() <BR>
     * <BR>
     *   (2-2) �g�����M�����I�u�W�F�N�g�̒ʉ݃R�[�h��T0�ȊO�ꍇ <BR>
     * <BR>
     * �@@�@@�@@(2-2-1) ����.�g�����M����.is�O��MMF=true�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�T�Z��n����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�A�v�Z�T�[�r�X��calc�O��MMF�T�Z��n���()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[calc�O��MMF�T�Z��n���()�̈���]<BR>
     * �@@�@@�@@�@@�@@�@@�����敪           �F���<BR>
     * �@@�@@�@@�@@�@@�@@��������           �F���\�c������ <BR>
     * �@@�@@�@@�@@�@@�@@���ϕ��@@           �F�~�� <BR>
     * �@@�@@�@@�@@�@@�@@�g�����M����     �F�����g�����M�����I�u�W�F�N�g <BR>
     * �@@�@@�@@�@@�@@�@@�T�Z��n���     �F�@@�Ő��������T�Z��n����I�u�W�F�N�g <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�]���z�@@=�@@�T�Z��n����I�u�W�F�N�g.�T�Z������� <BR>
     * <BR>
     * �@@�@@(2-2-2) ����.�g�����M����.is�O��MMF=false�̏ꍇ <BR>
     *     �@@ �בփ��[�g�e�[�u��(*)���A�בփ��[�g�iTTB�j�ƈבփ��[�g�v�Z�P�ʂ��擾����B<BR> 
     *        (*)����.�g�����M����.get�בփ��[�g()�ɂĈבփ��[�gParams�擾 <BR>
     * <BR>
     *     �A �O�݂ł̕]���z���Z�o����B<BR>
     *        �@@�]���z�i�O�݁j �� (����.�g�����M����.get��񉿊z() �~ ���\�c������)�@@�^<BR>�@@ 
     *�@@�@@�@@�@@�@@�@@�@@�@@����.�g�����M����.get����z�v�Z�P��() <BR>
     *     �B �~�݂ł̕]���z���Z�o���A���ʂ����^�[������B�i�����_�ȉ��l�̌ܓ��j <BR>
     *          �]���z �� �]���z�i�O�݁j �~ TTB�@@�^�@@�בփ��[�g�v�Z�P��<BR>
     * <BR>
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_strAssetId - ���YID
     * @@return double
     * @@roseuid 40BB0DF60203
     */
    public double calcMarketValue(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId)
        throws WEB3BaseException 
    {
        final String l_strMethodName = "calcMarketValue("
            + "SubAccount l_subAccount, "
            + "WEB3MutualFundProduct l_mutualFundProduct, String l_strAssetId)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundProduct == null || l_strAssetId == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // �P)�@@���\�c�������擾 
        //  - this.calc���\�c������()���R�[�����A���\�c���������擾����B
        // �mcalc���\�c�������ɓn���p�����^�n 
        // �@@�⏕�����F ����.�⏕����
        // �@@�g�����M�����F ����.�g�����M���� 
        // �@@���YID�F�@@����.���YID
        BigDecimal l_bdSellPossiblePositionQty =
            new BigDecimal (Double.toString(
                this.calcSellPossiblePositionQty(l_subAccount, l_mutualFundProduct, l_strAssetId)));
        log.debug("���\�c������ = " + l_bdSellPossiblePositionQty);
        
        // �Q)�@@�]���z�Z�o
        BigDecimal l_bdMarketValue = null;
        BigDecimal l_bdSellValue =
            new BigDecimal(Double.toString(l_mutualFundProduct.getSellValue()));
        
        BigDecimal l_bdConstantValueCalcUnit =
            new BigDecimal(Double.toString(l_mutualFundProduct.getConstantValueCalcUnit()));
        
        log.debug("����.�g�����M����.get��񉿊z() = " + l_bdSellValue);
        log.debug("����.�g�����M����.get����z�v�Z�P��() = " + l_bdConstantValueCalcUnit);
        
        // �@@(2-1) �g�����M�����I�u�W�F�N�g�̒ʉ݃R�[�h��T0�̏ꍇ
        //     �@@ �]���z���Z�o���A���ʂ����^�[������B�i�����_�ȉ��l�̌ܓ��j
        // �@@        �]���z �� (����.�g�����M����.get��񉿊z() �~ ���\�c������) �^
        //�@@�@@           ����.�g�����M����.get����z�v�Z�P��()
        if(WEB3MFOrderQuantityType.EN.equals(
                l_mutualFundProduct.getCurrencyCode()))
        {
            l_bdMarketValue = 
                l_bdSellValue.multiply(l_bdSellPossiblePositionQty).divide(
                    l_bdConstantValueCalcUnit, 0, BigDecimal.ROUND_HALF_UP);
        }
        //  �@@(2-2) �g�����M�����I�u�W�F�N�g�̒ʉ݃R�[�h��T0�ȊO�̏ꍇ 
        else
        {
            // (2-2-1) ����.�g�����M����.is�O��MMF=true�̏ꍇ
            if (l_mutualFundProduct.isFrgnMmf())
            {
                // �@@�T�Z��n����I�u�W�F�N�g�𐶐�����B
                WEB3MutualFundEstimatedPrice l_mfEstimatedPrice =
                    new WEB3MutualFundEstimatedPrice();

                // �A�v�Z�T�[�r�X��calc�O��MMF�T�Z��n���()���R�[������B
                //         [calc�O��MMF�T�Z��n���()�̈���]
                //         �����敪           �F���
                //         ��������           �F���\�c������
                //         ���ϕ��@@           �F�~��
                //         �g�����M����     �F�����g�����M�����I�u�W�F�N�g
                //         �T�Z��n���     �F�@@�Ő��������T�Z��n����I�u�W�F�N�g
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
                    (WEB3MutualFundBizLogicProvider)l_finApp.getTradingModule(
                        ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();
                l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                    WEB3MFProcessDivDef.SELL,
                    l_bdSellPossiblePositionQty.doubleValue(),
                    WEB3SettlementDivDef.JAPANESE_CURRENCY,
                    l_mutualFundProduct,
                    l_mfEstimatedPrice);

                //     �]���z�@@=�@@�T�Z��n����I�u�W�F�N�g.�T�Z�������
                l_bdMarketValue =
                    new BigDecimal(WEB3StringTypeUtility.formatNumber(
                        l_mfEstimatedPrice.getEstimatedTradeAmount()));
            }

            //(2-2-2) ����.�g�����M����.is�O��MMF=false�̏ꍇ
            else
            {
                //�@@ �בփ��[�g�e�[�u�����A�בփ��[�g�iTTB�j�ƈבփ��[�g�v�Z�P�ʂ��擾����B
                //(*)����.�g�����M����.get�בփ��[�g()�ɂĈבփ��[�gParams�擾        
                ExchangeRateParams l_exchangeRateParams = 
                    l_mutualFundProduct.getExchangeRate();
                   
                // �A �O�݂ł̕]���z���Z�o����B
                //     �]���z�i�O�݁j �� (����.�g�����M����.get��񉿊z() �~ ���\�c������) �^�@@ 
                //�@@�@@�@@�@@�@@����.�g�����M����.get����z�v�Z�P��()
                BigDecimal l_bdMarketValueForeign = 
                    l_bdSellValue.multiply(l_bdSellPossiblePositionQty).divide(
                        l_bdConstantValueCalcUnit, 6, BigDecimal.ROUND_HALF_UP);
                
                //�B �~�݂ł̕]���z���Z�o���A���ʂ����^�[������B�i�����_�ȉ��l�̌ܓ��j
                //     �]���z �� �]���z�i�O�݁j �~ TTB�@@�^�@@�בփ��[�g�v�Z�P��
                BigDecimal l_bdTtBuyRate =
                    new BigDecimal(Double.toString(l_exchangeRateParams.tt_buying_rate));
                BigDecimal l_bdExchangeCalcUnit =
                    new BigDecimal(Double.toString(l_exchangeRateParams.exchange_calc_unit));
                
                l_bdMarketValue = 
                    l_bdMarketValueForeign.multiply(l_bdTtBuyRate).divide(
                        l_bdExchangeCalcUnit, 0, BigDecimal.ROUND_HALF_UP);
            }
        }
        
        log.exiting(l_strMethodName);
        return l_bdMarketValue.doubleValue();
    }
    
    /**
     * (calc�]�����v)<BR>
     * ���Y�ڋq�ۗ̕L��������̕]�����v���Z�o����B<BR>
     * <BR>
     * �P�j�@@�ۗL�c���̎擾<BR>
     * �@@this.getAsset()���R�[�����āA�ۗL�c���I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mgetAsset�ɓn���p�����^�n<BR>
     * �@@�@@�@@���YID�F ����.���YID <BR>
     * <BR>
     * �Q�j�@@�擾�����ۗL�c���I�u�W�F�N�g.getTaxType() == TaxTypeEnum.��ʌ���
     * �@@�ŁA<BR>
     * ����.�g�����M����.getMutualFundType() == MutualFundTypeEnum.���O
     * �@@�̏ꍇ�A<BR>
     * Double.NaN �����^�[������B<BR>
     * <BR>
     * �R�j�@@�g�����M�����I�u�W�F�N�g.is�O��MMF��true�̏ꍇ�ADouble.NaN �����^�[������B<BR>
     * <BR>
     * �S�j�@@���\�c�������擾<BR>
     * �@@this.calc���\�c������()���R�[�����A���\�c���������擾����B<BR>
     * �@@�@@�mcalc���\�c�������ɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����<BR>
     * �@@�@@�@@�g�����M�����F ����.�g�����M����<BR>
     *      ���YID�F ����.���YID <BR>
     * <BR>
     * �T�j�@@�]�����v�Z�o<BR>
     * �@@(5-1) �g�����M�����I�u�W�F�N�g�̒ʉ݃R�[�h��T0�̏ꍇ <BR>
     * 	 �@@ �]�����v�v�Z���s���A���ʂ����^�[������B�i�����_�ȉ��l�̌ܓ��j <BR>
     *		((����.�g�����M����.get��񉿊z() �| �ۗL���Y.getBookValue()) �~ ���\�c������) <BR>
     *�@@�@@�@@�@@�@@�@@�^ ����.�g�����M����.get����z�v�Z�P��() <BR>
     * <BR>
     *  (5-2) �g�����M�����I�u�W�F�N�g�̒ʉ݃R�[�h��T0�ȊO�̏ꍇ <BR>
     *    �@@ �בփ��[�g�e�[�u�����A�בփ��[�g�iTTB�j�ƈבփ��[�g�v�Z�P�ʂ��擾����B <BR>
     *�@@     �i�����_�ȉ��l�̌ܓ��j <BR>
     *       (*)����.�g�����M����.get�בփ��[�g()�ɂĈבփ��[�gParams�擾 <BR>
     * <BR>
     *    �A �]�����v�v�Z���s���A���ʂ����^�[������B�i�����_�ȉ��l�̌ܓ��j <BR>
     *      (((����.�g�����M����.get��񉿊z() �~ TTB �^ �בփ��[�g�v�Z�P��) �| �ۗL���Y.getBookValue()) <BR>
     *�@@�@@�@@�@@�@@ �~ ���\�c������) �^ ����.�g�����M����.get����z�v�Z�P��() <BR>
     * <BR>
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_strAssetId - ���YID
     * @@return double
     * @@roseuid 40BB0E12005D
     */
    public double calcAppraisalProfitLoss(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId)
        throws WEB3BaseException 
    {
        final String l_strMethodName = "calcAppraisalProfitLoss("
            + "SubAccount l_subAccount, "
            + "WEB3MutualFundProduct l_mutualFundProduct, String l_strAssetId)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundProduct == null || l_strAssetId == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // �P�j�@@�ۗL���Y�e�[�u�����c�����擾����B
            MutualFundAsset l_asset =
                (MutualFundAsset)this.getAsset(Long.parseLong(l_strAssetId));
            
            // �Q�j�擾�����ۗL�c���I�u�W�F�N�g.getTaxType() == TaxTypeEnum.��ʌ���
            // �ŁA����.�g�����M����.getMutualFundType() == MutualFundTypeEnum.���O
            // �̏ꍇ�ADouble.NaN �����^�[������B
            TaxTypeEnum l_taxTypeEnum = l_asset.getTaxType();
            MutualFundTypeEnum l_mfTypeEnum = l_mutualFundProduct.getMutualFundType();
            // test log
            log.debug("�擾�����ۗL�c���I�u�W�F�N�g.getTaxType() = " + l_taxTypeEnum);
            log.debug("����.�g�����M����.getMutualFundType() = " + l_mfTypeEnum);
            if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum) 
                && MutualFundTypeEnum.FOREIGN.equals(l_mfTypeEnum))
            {
                log.exiting(l_strMethodName);
                return  Double.NaN;    
            }

            //�R�j�@@�g�����M�����I�u�W�F�N�g.is�O��MMF��true�̏ꍇ�ADouble.NaN �����^�[������B 
            if (l_mutualFundProduct.isFrgnMmf())
            {
                log.exiting(l_strMethodName);
                return  Double.NaN; 
            }

            // �R�j�@@���\�c�������擾
            //  - this.calc���\�c������()���R�[�����A���\�c���������擾����B
            // �mcalc���\�c�������ɓn���p�����^�n
            // �@@�⏕�����F ����.�⏕���� 
            // �@@�g�����M�����F ����.�g�����M���� 
            // �@@���YID�F ����.���YID 
            BigDecimal l_bdSellPossiblePositionQty = 
                new BigDecimal(Double.toString(
                    this.calcSellPossiblePositionQty(
                        l_subAccount, l_mutualFundProduct, l_strAssetId)));

            // �S�j�@@�]�����v�Z�o
            BigDecimal l_bdAppraisalProfitLoss = null;            
            BigDecimal l_bdSellValue =
                new BigDecimal(Double.toString(l_mutualFundProduct.getSellValue()));
            
            BigDecimal l_bdBookValue =
                new BigDecimal(Double.toString(l_asset.getBookValue()));
            BigDecimal l_bdConstantValue =
                new BigDecimal(Double.toString(l_mutualFundProduct.getConstantValueCalcUnit()));
            
            // �@@(4-1) �g�����M�����I�u�W�F�N�g�̒ʉ݃R�[�h��T0�̏ꍇ
            // 	 �@@ �]�����v�v�Z���s���A���ʂ����^�[������B�i�����_�ȉ��l�̌ܓ��j 
            //		((����.�g�����M����.get��񉿊z() �| �ۗL���Y.getBookValue()) �~ ���\�c������)
            //�@@�@@�@@�@@�@@�@@�^ ����.�g�����M����.get����z�v�Z�P��() 
            if(WEB3MFOrderQuantityType.EN.equals(l_mutualFundProduct.getCurrencyCode()))
            {   
                l_bdAppraisalProfitLoss = 
                    (l_bdSellValue.subtract(l_bdBookValue)).multiply(
                        l_bdSellPossiblePositionQty).divide(
                            l_bdConstantValue, 0, BigDecimal.ROUND_HALF_UP);       
            }
            
            //  (4-2) �g�����M�����I�u�W�F�N�g�̒ʉ݃R�[�h��T0�ȊO�̏ꍇ
            else
            {
                //    �@@ �בփ��[�g�e�[�u�����A�בփ��[�g�iTTB�j�ƈבփ��[�g�v�Z�P�ʂ��擾����B
                //    (*)����.�g�����M����.get�בփ��[�g()�ɂĈבփ��[�gParams�擾 
				ExchangeRateParams l_exchangeRateParams = 
					l_mutualFundProduct.getExchangeRate();
                
                //    �A �]�����v�v�Z���s���A���ʂ����^�[������B�i�����_�ȉ��l�̌ܓ��j 
                //      (((����.�g�����M����.get��񉿊z() �~ TTB �^ �בփ��[�g�v�Z�P��) �| �ۗL���Y.getBookValue())
                //�@@�@@�@@�@@�@@ �~ ���\�c������) �^ ����.�g�����M����.get����z�v�Z�P��()
                BigDecimal l_bdTtBuyRate =
                    new BigDecimal(Double.toString(l_exchangeRateParams.tt_buying_rate));
                BigDecimal l_bdExchangeCalcUnit =
                    new BigDecimal(Double.toString(l_exchangeRateParams.exchange_calc_unit));
                
                l_bdAppraisalProfitLoss = 
                    (l_bdSellValue.multiply(l_bdTtBuyRate).divide(
                        l_bdExchangeCalcUnit, 10, BigDecimal.ROUND_HALF_UP).subtract(
                            l_bdBookValue)).multiply(l_bdSellPossiblePositionQty).divide(
                                l_bdConstantValue, 0, BigDecimal.ROUND_HALF_UP);
            }

            log.exiting(l_strMethodName);
            return l_bdAppraisalProfitLoss.doubleValue();
        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y������ۗL���Y���s���� with SubAccountId = " 
                + l_subAccount.getSubAccountId() + " ProductId = " 
                + l_mutualFundProduct.getProductId());
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
    }
    
    /**
     * (calc���v�T�Z��n���)<BR>
     * ����.���M�����ɑ΂������Ŕ������������̊T�Z��n��������Z���ĕԋp����B<BR>
     * <BR>
     * �P)�@@����ID�̎擾<BR>
     * �@@����.���M����.getProductId( )���R�[�����A����ID���擾�B<BR>
     * <BR>
     * �Q)�@@���M�����P�ʃe�[�u�����������A�T�Z��n����̑��v���v�Z����B<BR>
     * �@@���M�����P�ʃe�[�u�����������A���M�����P��Params��List���擾����B<BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId()<BR>
     * �@@�@@�@@AND �⏕����ID = ����.�⏕����.getSubAccountId()<BR>
     * �@@�@@�@@AND ����ID = ����.�g�����M����.getProductId()<BR>
     * �@@�@@�@@AND �i������� = OrderTypeEnum.�����M�������� <BR>
     *       OR ������� = OrderTypeEnum.�����M���抷�����j<BR>
     * �@@�@@�@@AND (������� = OrderStatusEnum.��t�ρi�V�K�����j OR �������<BR>
     *  = OrderStatusEnum.�����ρi�V�K�����j)<BR>
     * �@@�@@�@@AND ����� != �h2�F���ρh<BR>
     * <BR>
     * �R)�@@�擾�����T�Z��n����̑��v��Ԃ��B<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_mutualFundProduct - ���M����<BR>
     * @@return double
     * @@roseuid 40D9564E0150
     */
    public double calcTotalEstimateDeliveryAmount(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct)
        throws WEB3BaseException 
    {
        final String l_strMethodName = "calcTotalEstimateDeliveryAmount("
            + "SubAccount l_subAccount, "
            + "WEB3MutualFundProduct l_mutualFundProduct)";
        log.entering(l_strMethodName);
        if (l_subAccount == null || l_mutualFundProduct == null)
        {
            log.debug("�p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        try
        {
            // �P�j�@@���M�����P�ʃe�[�u�����������A��񒍕������̑��v���v�Z����B
            List l_lisMFOrderUnits = new ArrayList();
            
            String l_strWhere = 
                "account_id = ? and sub_account_id = ? "
                + "and product_id = ? and (order_type = ? or order_type = ?) "
                + "and (order_status = ? or order_status = ?) "
                + "and exec_status != ? ";
            Object[] l_objWhereValues = {
                new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mutualFundProduct.getProductId()),
                // OrderTypeEnum.MF_SELL --- 202�F�����M��������
                OrderTypeEnum.MF_SELL,
                // OrderTypeEnum.MF_SWITCHING --- 204�F�����M���抷�����j
                OrderTypeEnum.MF_SWITCHING,
                // OrderStatusEnum.ACCEPTED ---- 1�F��t�ρi�V�K�����j
                OrderStatusEnum.ACCEPTED,
                // OrderStatusEnum.ORDERED --- 3�F�����ρi�V�K�����j
                OrderStatusEnum.ORDERED,
                // WEB3ExecStatusDef.EXECUTED_COMPLETE -- ����� != �h2�F���ρh
                WEB3ExecStatusDef.EXECUTED_COMPLETE
            };
            // -���M�����P�ʃe�[�u�����������A���M�����P��Params��List���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisMFOrderUnits = l_queryProcessor.doFindAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhere,
                l_objWhereValues);
            // �T�Z��n���   
            double l_dblEstimatedPrice = 0D;
            // �擾�������M�����P��Params�̊T�Z��n����̑��v���v�Z����B
            for (int i = 0; i < l_lisMFOrderUnits.size(); i ++)
            {
                //�擾�������M�����P��Params
                MutualFundOrderUnitRow l_orderUnitRow = 
                    (MutualFundOrderUnitRow)l_lisMFOrderUnits.get(i);
                l_dblEstimatedPrice += l_orderUnitRow.getEstimatedPrice();
                
            }
            // �Q�j�@@�擾�����T�Z��n����̑��v��Ԃ��B
            log.exiting(l_strMethodName);
            return l_dblEstimatedPrice;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("Error In ���M�����P�ʃe�[�u���������� ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("Error In ���M�����P�ʃe�[�u���������� ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
    }

    /**
     * getAssets�̃I�[�o�[���[�h<BR>
     * <BR>
     * 1)�D �ۗL���Y�����ꗗ���擾���� <BR>
     * �@@super.getAssets()���R�[�����A���X�g���擾����B<BR>
     * �@@[����]<BR>
     * �@@�⏕�����F ����.�⏕����<BR>
     * �@@�\�[�g�L�[�Fnull<BR>
     * �@@�����^�C�v�FProductTypeEnum.�����M�� <BR>
     * <BR>
     * 2)�D ����.���M��O��MMF�\���敪��"2�F����"�̏ꍇ <BR>
     * �@@�P�j�Ŏ擾����List�����^�[��<BR>
     * <BR>
     * 3). ���List���쐬����<BR>
     * <BR>
     * 4)�D1)�Ŏ擾����getAssets()�̖߂�l�̗v�f(=�ۗL���YParams)����Loop����<BR>
     * <BR>
     * �@@4)-1)�D����ID���擾����<BR>
     * �@@�@@�@@�@@AssetParams.getProductId()���R�[��<BR>
     * <BR>
     * �@@4)-2)�D�����I�u�W�F�N�g���擾����<BR>
     * �@@�@@�@@�@@�g�����M�����}�l�[�W��.getProduct(����ID)���R�[�� <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@����ID: 4)-1)�Ŏ擾�����ۗL���YParams.getProductId( )<BR>
     * <BR>
     * �@@4)-3)�D�g�����M�������擾����B<BR>
     * �@@�@@�@@�@@�g�����M�����}�l�[�W��.to����(Row)���R�[��<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@����Row: 4)-2)�Ŏ擾����getProduct()�̖߂�l<BR>
     * <BR>
     * �@@4)-4). 4)-3)�Ŏ擾�����g�����M����.is�O��MMF()==false���� <BR>
     * �@@�@@�@@�@@����.���M��O��MMF�\���敪��"0:���M�̂�"�̏ꍇ <BR>
     * <BR>
     * �@@4)-4)-1). 3)�ŗp�ӂ���List�֌��ݍs�̗v�f��ǉ�����B<BR>
     * <BR>
     * �@@4)-5). 4)-3)�Ŏ擾�����g�����M����.is�O��MMF()==true���� <BR>
     * �@@�@@�@@�@@����.���M��O��MMF�\���敪��"1:�O��MMF�̂�"�̏ꍇ <BR>
     * <BR>
     * �@@4)-5)-1). 3)�ŗp�ӂ���List�֌��ݍs�̗v�f��ǉ�����B<BR>
     * <BR>
     * 5). 3)�ŗp�ӂ���List�����^�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - (���M��O��MMF�\���敪)<BR>
     * ���M��O��MMF�\���敪 <BR>
     * <BR>
     * 0:���M�̂�<BR>
     * 1:�O��MMF�̂� <BR>
     * 2:���� <BR>
     * <BR>
     * ��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���<BR>
     * @@return List
     * @@throws WEB3BaseException 
     */
    public List getAssets(
        SubAccount l_subAccount,
        String l_strMutualFrgnMmfDisplayDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssets(SubAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1)�D �ۗL���Y�����ꗗ���擾����
        //  super.getAssets()���R�[�����A���X�g���擾����B
        //  [����]
        //  �⏕�����F ����.�⏕����
        //  �\�[�g�L�[�Fnull
        //  �����^�C�v�FProductTypeEnum.�����M��
        List l_lisAssets = super.getAssets(
            l_subAccount,
            null,
            ProductTypeEnum.MUTUAL_FUND);

        //2)�D ����.���M��O��MMF�\���敪��"2�F����"�̏ꍇ
        //    �P�j�Ŏ擾����List�����^�[��
        if (WEB3MutualFrgnMmfDisplayDivDef.BOTH.equals(l_strMutualFrgnMmfDisplayDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return l_lisAssets;
        }

        //3). ���List���쐬����
        List l_lisReturnList = new ArrayList();

        //4)�D1)�Ŏ擾����getAssets()�̖߂�l�̗v�f(=�ۗL���YParams)����Loop����
        int l_intListSize = 0;
        if (l_lisAssets != null || l_lisAssets.size() != 0)
        {
            l_intListSize = l_lisAssets.size();
        }
        for (int i = 0; i < l_intListSize; i++)
        {
            AssetRow l_assetRow =
                (AssetRow)(((Asset)l_lisAssets.get(i)).getDataSourceObject());
            //  4)-1)�D����ID���擾����
            //         AssetParams.getProductId()���R�[��
            long l_lngProductId = l_assetRow.getProductId();

            //  4)-2)�D�����I�u�W�F�N�g���擾����
            //         �g�����M�����}�l�[�W��.getProduct(����ID)���R�[��
            //         [����]
            //         ����ID: 4)-1)�Ŏ擾�����ۗL���YParams.getProductId( )
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mfProductManager =
                (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
            Product l_product = null;
            try
            {
                l_product = l_mfProductManager.getProduct(l_lngProductId);
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
            }

            //  4)-3)�D�g�����M�������擾����B
            //         �g�����M�����}�l�[�W��.to����(Row)���R�[��
            //         [����]
            //         ����Row: 4)-2)�Ŏ擾����getProduct()�̖߂�l
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct)l_mfProductManager.toProduct(
                    (MutualFundProductRow)l_product.getDataSourceObject());

            //  4)-4). 4)-3)�Ŏ擾�����g�����M����.is�O��MMF()==false����
            //         ����.���M��O��MMF�\���敪��"0:���M�̂�"�̏ꍇ
            if (!l_mutualFundProduct.isFrgnMmf()
                && WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                //    4)-4)-1). 3)�ŗp�ӂ���List�֌��ݍs�̗v�f��ǉ�����B
                l_lisReturnList.add(this.toAsset(l_assetRow));
            }

            //  4)-5). 4)-3)�Ŏ擾�����g�����M����.is�O��MMF()==true����
            //         ����.���M��O��MMF�\���敪��"1:�O��MMF�̂�"�̏ꍇ
            if (l_mutualFundProduct.isFrgnMmf()
                && WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                //    4)-5)-1). 3)�ŗp�ӂ���List�֌��ݍs�̗v�f��ǉ�����B
                l_lisReturnList.add(this.toAsset(l_assetRow));
            }
        }

        //5). 3)�ŗp�ӂ���List�����^�[������B
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnList;
    }

    /**
     * (get�������z��)<BR>
     * �������z����ԋp����B<BR>
     * <BR>
     * 1). �ȉ��̏����œ��M�ۗL���Y�⏕�e�[�u�����s�I�u�W�F�N�g���擾����B<BR>
     * �@@���YID = ����.���YID <BR>
     * <BR>
     * 2). ���R�[�h���擾�ł��Ȃ��ꍇ�A0�����^�[���B<BR>
     * <BR>
     * 3). �T�Z��n����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * 4). �~�]�����������z�����擾����B<BR>
     * �@@�v�Z�T�[�r�X.calc�O��MMF�T�Z��n���()<BR>
     * �@@[����]<BR>
     * �@@�����敪:���<BR>
     * �@@��������:�擾�����s�I�u�W�F�N�g��get�������z���c��<BR>
     * �@@�@@�@@�@@�@@�@@(�����_�ȉ��؂�̂�)<BR>
     * �@@���ϕ��@@:�~��<BR>
     * �@@�g�����M����:����.�g�����M�����I�u�W�F�N�g<BR>
     * �@@�T�Z��n���:���������T�Z��n����I�u�W�F�N�g<BR>
     * <BR>
     * 5). �T�Z��n����I�u�W�F�N�g.�T�Z������������^�[������B<BR>
     * <BR>
     * @@param l_strAssetId - (���YID)<BR>
     * ���YID<BR>
     * @@param l_mutualFundProduct- (�g�����M����)<BR>
     * �g�����M����<BR>
     * @@return long
     * @@throws WEB3BaseException 
     */
    public long getUnreceiveDist(String l_strAssetId, WEB3MutualFundProduct l_mutualFundProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnreceiveDist(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strAssetId == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1). �ȉ��̏����œ��M�ۗL���Y�⏕�e�[�u�����s�I�u�W�F�N�g���擾����
        List l_lisRow = new ArrayList();
        String l_strWhere = " asset_id = ? ";
        Object[] l_objWheres = new Object[]{l_strAssetId};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRow =
                l_queryProcessor.doFindAllQuery(
                    MfSubAssetRow.TYPE,
                    l_strWhere,
                    l_objWheres);
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

        //���R�[�h���擾�ł��Ȃ��ꍇ�A0�����^�[���B
        if (l_lisRow == null || l_lisRow.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //�T�Z��n����I�u�W�F�N�g�𐶐�����B
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();

        //�擾�����s�I�u�W�F�N�g��get�������z���c��
        MfSubAssetRow l_mfSubAssetRow = (MfSubAssetRow)l_lisRow.get(0);
        double l_dblUnreceiveDist = l_mfSubAssetRow.getUnreceiveDist();
        BigDecimal l_bdUnreceiveDist =
            new BigDecimal(WEB3StringTypeUtility.formatNumber(l_dblUnreceiveDist));
        BigDecimal l_bdUnreceiveDistRDown =
            l_bdUnreceiveDist.setScale(0,BigDecimal.ROUND_DOWN);

        //�v�Z�T�[�r�X
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();
        //�~�]�����������z�����擾����B
        //�v�Z�T�[�r�X.calc�O��MMF�T�Z��n���()
        //[����]
        //�����敪:���
        //��������:�擾�����s�I�u�W�F�N�g��get�������z���c��
        //              (�����_�ȉ��؂�̂�)
        //���ϕ��@@:�~��
        //�g�����M����:����.�g�����M�����I�u�W�F�N�g
        //�T�Z��n���:���������T�Z��n����I�u�W�F�N�g
        l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
            WEB3ProcessDivDef.SELL,
            l_bdUnreceiveDistRDown.doubleValue(),
            WEB3SettlementDivDef.JAPANESE_CURRENCY,
            l_mutualFundProduct,
            l_mfEstimatedPrice);

        //�T�Z��n����I�u�W�F�N�g.�T�Z������������^�[������B
        double l_dblEstimatedTradeAmount =
            l_mfEstimatedPrice.getEstimatedTradeAmount();
        BigDecimal l_bdEstimatedTradeAmount =
            new BigDecimal(WEB3StringTypeUtility.formatNumber(l_dblEstimatedTradeAmount));

        log.exiting(STR_METHOD_NAME);
        return l_bdEstimatedTradeAmount.longValue();
    }
}
@
