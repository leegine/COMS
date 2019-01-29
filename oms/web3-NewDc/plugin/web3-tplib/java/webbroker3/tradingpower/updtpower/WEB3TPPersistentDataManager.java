head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͍X�V�T�[�r�X(WEB3TPPersistentDataManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/8/03 �� (fitechlabs) �V�K�쐬
                    2006/09/11 ���G�� (���u) ���f��No.017  
                    2006/09/25 ���G�� (���u) ���f��No.063�`066
 Revesion History : 2007/09/20 �����Q(���u) ���f��No.175
 Revesion History : 2007/11/05 �И��� (���u) ���f��No.224�ANo.227
 Revesion History : 2008/01/22 �����F (���u) �d�l�ύX�@@���f��No.240
 Revesion History : 2008/05/26 �k�v�u (���u) �d�l�ύX�@@���f��No.282�A�c�a�X�V�d�l002�C003
 Revesion History : 2008/09/10 ���� (���u) �d�l�ύX�@@���f��No.296�ANo.297�ANo.298
 Revesion History : 2009/10/02 �юu�� (���u) �d�l�ύX�@@���f��No.397
 Revesion History : 2010/01/11 ���g (���u) �d�l�ύX�@@���f��No.421�ANo.443
 Revesion History : 2010/02/22 ���g (���u) �d�l�ύX���f��No.455
 */

package webbroker3.tradingpower.updtpower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BondOrderSettleDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3HostReflectDivDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.gentrade.data.DailyAssetParams;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.gentrade.data.ExchangeRateDao;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.data.EqtypeFixedContractDao;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.data.TpCalcResultEquityDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCashBalanceDao;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.data.TpOtherTempRestraintDao;
import webbroker3.tradingpower.data.TpOtherTempRestraintParams;
import webbroker3.tradingpower.data.TpOtherTempRestraintRow;
import webbroker3.tradingpower.define.WEB3TPAccountTaxTypeKeyDef;
import webbroker3.tradingpower.define.WEB3TPFeqExecFileSendStatusDivDef;
import webbroker3.tradingpower.define.WEB3TPFeqSettlementDivDef;
import webbroker3.tradingpower.define.WEB3TPIPOLotResultTypeDef;
import webbroker3.tradingpower.define.WEB3TPIPOOfferingDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�f�[�^�x�[�X�A�N�Z�X�Ǘ�) <BR>
 * �]�͌v�Z�f�[�^�x�[�X�A�N�Z�X�Ǘ��p�N���X
 */
public class WEB3TPPersistentDataManager
{
    
    private static WEB3TPPersistentDataManager THIS_INSTANCE = new
    WEB3TPPersistentDataManager();
    
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPPersistentDataManager.class);
    
    /**
     * @@roseuid 4106191F00BB
     */
    public WEB3TPPersistentDataManager()
    {
        
    }
    
    /**
     * (get������n���ʕۗL���Y���R�[�h)<BR>
     * ������n���ʕۗL���Y���R�[�h<�m��>���擾����B<BR>
     * ���������F<BR>
     *  ��n���ʎc���e�[�u��.����ID = ����.�ڋq����.����ID<BR>
     *  ��n���ʎc���e�[�u��.�⏕����ID = ����.�ڋq����.�⏕����ID<BR>
     * <BR>
     * @@param l_accountInfo - (�ڋq����)
     * @@return List
     * @@roseuid 4104D2DE02CE
     */
    public List getDayAssets(WEB3TPAccountInfo l_accountInfo)
    {
        final String STR_METHOD_NAME = "getDayAssets(WEB3TPAccountInfo l_accountInfo)";
        try
        {
            String l_strWhere = "account_id=?";
            Object[] l_bindVars = { new Long(l_accountInfo.getAccountId())};
            
            log.debug("WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            
            List l_rows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        DailyAssetRow.TYPE,
                        l_strWhere,
                        l_bindVars);
            
            ArrayList l_dayAssets = new ArrayList();
            for (int i = 0; i < l_rows.size(); i++)
            {
                DailyAssetRow l_row = (DailyAssetRow)l_rows.get(i);
                DailyAssetParams l_params = new DailyAssetParams();
                l_params.setAccountId(l_row.getAccountId());
                l_params.setSubAccountId(l_row.getSubAccountId());
                l_params.setProductId(l_row.getProductId());
                l_params.setProductType(l_row.getProductType());
                l_params.setDeliveryDate(l_row.getDeliveryDate());
                l_params.setQuantity(l_row.getQuantity());
                l_params.setTaxType(l_row.getTaxType());
                //�~�j���敪�̒ǉ�
                l_params.setMiniStockDiv(l_row.getMiniStockDiv());
                //�����V�����敪�̒ǉ�
                l_params.setSplitNewStockDiv(l_row.getSplitNewStockDiv());
                //�������̒ǉ�
                l_params.setOriginalExecDate(l_row.getOriginalExecDate());                
                l_dayAssets.add(l_params);
            }
            
            return l_dayAssets;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get�����ۗL���Y���R�[�h)<BR>
     * �����ۗL�c���ϓ����R�[�h<�m��>���擾����B<BR>
     * ���������F<BR>
     *  �ۗL���Y�e�[�u��.����ID = ����.�ڋq����.����ID<BR>
     *  �ۗL���Y�e�[�u��.�⏕����ID = ����.�ڋq����.�⏕����ID<BR>
     *  �ۗL���Y�e�[�u��.�����^�C�v = �ݓ�<BR>
     * <BR>
     * @@param l_accountInfo - (�ڋq����)
     * @@return List
     * @@roseuid 4104D2DE02EE
     */
    public List getAssets(WEB3TPAccountInfo l_accountInfo)
    {
        final String STR_METHOD_NAME = "getAssets(WEB3TPAccountInfo l_accountInfo)";
        try
        {
            String l_strWhere = "account_id=? and product_type=?";
            Object[] l_bindVars = { new Long(l_accountInfo.getAccountId()), ProductTypeEnum.RUITO };
            
            return Processors.getDefaultProcessor().doFindAllQuery(
                    AssetRow.TYPE,
                    l_strWhere,
                    l_bindVars);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get���Y�ړ����R�[�h<�o��>)<BR>
     * ���Y�ړ����R�[�h<�����ȍ~>���擾����B<BR>
     * ���������F<BR>
     *   ���Y�ړ��e�[�u��.����ID = ����.�ڋq����.����ID<BR>
     *   ���Y�ړ��e�[�u��.�����J�e�S�� = ���o��<BR>
     *   ���Y�ړ��e�[�u��.�����J�e�S���^�C�v = �o��<BR>
     *   ���Y�ړ��e�[�u��.������� NOT IN (�������s(�V�K����)�A������(�������))<BR>
     *   ���Y�ړ��e�[�u��.������ >= ����.�v�Z����.get�c�Ɠ�(T+0)<BR>
     * <BR>
     * @@param l_assetValuation - ���Y�]��
     * @@return List
     */
    public List getAssetOutAioOrderUnits(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getAssetOutAioOrderUnits(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = null;
        long l_accountId;
        List l_lisRows = null;
        
        try
        {
            l_accountInfo = l_assetValuation.getAccountInfo();
            l_accountId = l_accountInfo.getAccountId();
            
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id = ? AND ");
            l_strWhereBuf.append("order_categ = ? AND ");
            l_strWhereBuf.append("order_type = ? AND ");
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ? ");
            
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    OrderCategEnum.ASSET_IN_OUT,
                    OrderTypeEnum.ASSET_OUT,
                    OrderStatusEnum.NOT_ORDERED,
                    OrderStatusEnum.CANCELLED,
                    l_datT0
            };
            
            String l_strWhere = l_strWhereBuf.toString();
            log.debug("l_strWhere=" + l_strWhere);
            for(int i = 0 ; i < l_bindVars.length; i++)
            {
                log.debug("l_bindVars=[" + i + "]");
                log.debug(l_bindVars[i].toString());                
            }
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisRows = l_qp.doFindAllQuery(AioOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
            
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get���Y�ړ����R�[�h<�U��>)<BR>
     * ���Y�ړ����R�[�h<�����ȍ~>���擾����B<BR>
     *   ���Y�ړ��e�[�u��.����ID = ����.�ڋq����.����ID<BR>
     *   ���Y�ړ��e�[�u��.�����J�e�S�� = �،��U��<BR>
     *   ���Y�ړ��e�[�u��.�����^�C�v IN (�ی삩���p�A��p����ی�)<BR>
     *   ���Y�ړ��e�[�u��.������� NOT IN (�������s(�V�K����)�A������(�������))<BR>
     *   ���Y�ړ��e�[�u��.������ >= ����.�v�Z����.get�c�Ɠ�(T+0)<BR>
     * <BR>
     * @@param l_assetValuation - ���Y�]��
     * @@return List
     */
    public List getAssetTransferAioOrderUnits(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getAssetTransferAioOrderUnits(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = null;
        long l_accountId;
        List l_lisRows = null;
        
        try
        {
            l_accountInfo = l_assetValuation.getAccountInfo();
            l_accountId = l_accountInfo.getAccountId();
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id = ? AND ");
            l_strWhereBuf.append("order_categ = ? AND ");
            l_strWhereBuf.append("order_type in (?,?) AND ");
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ? ");
            
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    OrderCategEnum.ASSET_TRANSFER,
                    OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT,
                    OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES,
                    OrderStatusEnum.NOT_ORDERED,
                    OrderStatusEnum.CANCELLED,
                    l_datT0 };
            
            String l_strWhere = l_strWhereBuf.toString();
            log.debug("l_strWhere=" + l_strWhere);
            for(int i = 0 ; i < l_bindVars.length; i++)
            {
                log.debug("l_bindVars=[" + i + "]");
                log.debug(l_bindVars[i].toString());                
            }
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisRows = l_qp.doFindAllQuery(AioOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs == null)
        {
            return l_lisRows;
        }
        
        //�V�K�������e����荞��
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows.get(i);
            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
        }
        
        long l_intNewAioOrderUnitId = WEB3TPNewOrderSpec.DEFAULT_NEW_ID;
        for (int i = 0; i < l_newOrderSpecs.length; i++)
        {
            //���������e.�����J�e�S�� != �،��U�� �̏ꍇ
            if (!OrderCategEnum.ASSET_TRANSFER.equals(l_newOrderSpecs[i].getOrderCategory()))
            {
                continue;
            }
            
            if (l_newOrderSpecs[i].getOrderUnitId() == WEB3TPNewOrderSpec.DEFAULT_NEW_ID)
            {
                l_newOrderSpecs[i].setOrderUnitId(l_intNewAioOrderUnitId);
                l_intNewAioOrderUnitId = l_intNewAioOrderUnitId - 1;
            }
            
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            l_params.setAccountId(l_accountId);
            l_params.setSubAccountId(l_newOrderSpecs[i].getSubAccountId());
            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
            l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
            l_params.setProductType(l_newOrderSpecs[i].getProductType());
            l_params.setProductId(l_newOrderSpecs[i].getProductId());
            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
            
            AioOrderUnitParams l_oldParams =
                (AioOrderUnitParams)l_ht.get(new Long(l_params.getOrderUnitId()));
            if (l_oldParams != null)
            {
                l_ht.remove(new Long(l_params.getOrderUnitId()));
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            else
            {
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        List l_lisRet = new ArrayList();
        Iterator l_iterator = l_ht.values().iterator();
        while (l_iterator.hasNext())
        {
            AioOrderUnitParams l_aioOrderUnitParams = (AioOrderUnitParams)
            l_iterator.next();
            l_lisRet.add(l_aioOrderUnitParams);
        }
        
        return l_lisRet;
    }
    
    /**
     * (get����������R�[�h)<BR>
     * �����������R�[�h<�����ȍ~>���擾����B<BR>
     * ���������F<BR>
     * �@@���������P�ʃe�[�u��.����ID = ����.�ڋq����.get����ID()<BR>
     * �@@���������P�ʃe�[�u��.�⏕����ID = ����.�ڋq����.get�⏕����ID()<BR>
     * �@@���������P�ʃe�[�u��.������ >= ����.�v�Z����.get�c�Ɠ�(T+0)<BR>
     * �@@���������P�ʃe�[�u��.�����J�e�S�� in (��������, �����E���n����, ����O����)<BR>
     * �@@(�@@���������P�ʃe�[�u��.�����I�[�v�� = �I�[�v��<BR>
     * �@@�@@�@@OR<BR>
     * �@@�@@�i���������P�ʃe�[�u��.�����I�[�v�� = �N���[�Y<BR>
     * �@@�@@�@@�@@AND<BR>
     * �@@�@@�@@���������P�ʃe�[�u��.�������� - ���������P�ʃe�[�u��.��萔�� >= 0 <BR>
     * �@@�@@�@@AND ���������P�ʃe�[�u��.��萔�� > 0)<BR>
     * �@@)<BR>
     * <BR>
     * @@param l_accountInfo - (�ڋq����)
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@return List
     * @@roseuid 4104D2DE031C
     */
    public List getEqTypeOrderUnits(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getEqTypeOrderUnits(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(l_accountInfo.
                isMarginCustomer());
        
        //Y00125�F�����S�����Ή�
        //�����S�������擾
        double l_dblPreRestRate = this.getPremiumRestraintRate(
                WEB3CommisionProductCodeDef.LISTING_STOCK,
                l_assetValuation);
        
        List l_lisRows=l_assetValuation.getTodaysEquityOrders();
        
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_lisRows.get(i);
            
            boolean isTarget=
                l_row.getSubAccountId()==l_subAccountId
                &&
                (OrderCategEnum.ASSET.equals(l_row.getOrderCateg())
                        || OrderCategEnum.SWAP_MARGIN.equals(l_row.getOrderCateg())
                )
                && (  (OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus()))
                        || (OrderOpenStatusEnum.CLOSED.equals(l_row.getOrderOpenStatus())
                                &&
                                ( (l_row.getExecutedQuantity() > 0) &&
                                        (l_row.getQuantity() - l_row.getExecutedQuantity()) >= 0)
                        )
                )
                &&(!OrderTypeEnum.MINI_STOCK_BUY.equals(l_row.getOrderType()))
                &&(!OrderTypeEnum.MINI_STOCK_SELL.equals(l_row.getOrderType()));
            if(!isTarget)continue;                
            
            EqtypeOrderUnitParams l_params = new EqtypeOrderUnitParams(l_row);
            //���o���{�������ꂽ(���͎���ς�)�����̏ꍇ�A��������=��萔�ʁ@@�ɕϊ�����
            if (OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus()) &&
                    l_params.getExecutedQuantity() > 0)
            {
                l_params.setQuantity(l_params.getExecutedQuantity());
            }
            
            //Y00125�F�����S�����Ή�
            l_ht.put(
                    new Long(l_params.getOrderUnitId()),
                    new WEB3TPEqtypeOrderUnitRowWrapper(l_params, l_dblPreRestRate));
        }
        
        //�V�K�������e����荞��
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                //���������e[i].�����^�C�v!=�����̏ꍇ
                if(!ProductTypeEnum.EQUITY.equals(l_newOrderSpecs[i].getProductType()))
                {
                    continue;
                }
                
                //Y00126:�ԍό�]�͂̕s��
                //���������e.�����J�e�S��==�M�p�V�K�� ���� �M�p�ԍς̏ꍇ
                if(OrderCategEnum.OPEN_MARGIN.equals(l_newOrderSpecs[i].getOrderCategory())
                        || OrderCategEnum.CLOSE_MARGIN.equals(l_newOrderSpecs[i].getOrderCategory()))
                {
                    continue;
                }
                
                //���������e.�����^�C�v==�~�j���� ���� �~�j�����̏ꍇ
                if(OrderTypeEnum.MINI_STOCK_BUY.equals(l_newOrderSpecs[i].getOrderType())
                        || OrderTypeEnum.MINI_STOCK_SELL.equals(l_newOrderSpecs[i].getOrderType()))
                {
                    continue;
                }
                
                //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                EqtypeOrderUnitRow l_oldParams = 
                    (EqtypeOrderUnitRow)l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                
                EqtypeOrderUnitParams l_params = null;
                
                //�Â�Params�ɐV���������㏑������                  
                if (l_oldParams != null)
                {   
                    //��Params����R�s�[Params�쐬
                    l_params = new EqtypeOrderUnitParams(l_oldParams);
                    
                    //��Params���폜
                    l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                }
                else
                {
                    l_params = new EqtypeOrderUnitParams();
                }
                
                l_params.setAccountId(l_accountId);
                l_params.setSubAccountId(l_subAccountId);
                l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                l_params.setProductType(l_newOrderSpecs[i].getProductType());
                l_params.setProductId(l_newOrderSpecs[i].getProductId());
                l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                l_params.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                l_params.setBizDate(WEB3DateUtility.formatDate(
                        l_newOrderSpecs[i].
                        getOrderBizDate(), "yyyyMMdd"));
                l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                l_params.setMarketId(l_newOrderSpecs[i].getMarketId());
                l_params.setPrice(l_newOrderSpecs[i].getPrice());
                
                //Y00125�F�����S�����Ή�
                l_params.setLimitPrice(l_newOrderSpecs[i].getLimitPrice());
                
                l_ht.put(new Long(l_params.getOrderUnitId()),
                        new WEB3TPEqtypeOrderUnitRowWrapper(l_params, l_dblPreRestRate));
            }
            
        }
        
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (getGP������R�[�h)<BR>
     * �����������R�[�h<�����ȍ~>���擾����B<BR>
     * ���������F<BR>
     *   �ݓ������P�ʃe�[�u��.����ID = ����.�ڋq����.get����ID()<BR>
     *   �ݓ������P�ʃe�[�u��.�⏕����ID = ����.�ڋq����.get�⏕����ID()<BR>
     *   �ݓ������P�ʃe�[�u��.������ >= ����.�v�Z����.get�c�Ɠ�(T+0)<BR>
     *   �ݓ������P�ʃe�[�u��.�ݓ��^�C�v in (�������t�@@���h, MMF)<BR>
     *   �ݓ������P�ʃe�[�u��.������� not in <BR>
     *   �@@(��t��(�������), ������(�������), ������(�������)))<BR>
     *   �ݓ������P�ʃe�[�u��.�����I�[�v�� = �I�[�v��<BR>
     * <BR>
     * @@param l_accountInfo - (�ڋq����)
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@return List
     * @@roseuid 4104D2DE032C
     */
    public List getRuitoOrderUnits(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getRuitoOrderUnits(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            long l_accountId = l_accountInfo.getAccountId();
            long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id = ? AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ? AND ");
            l_strWhereBuf.append("ruito_type in (?, ?) AND ");
            l_strWhereBuf.append("order_status not in( ? , ? ,? ) AND ");
            l_strWhereBuf.append("order_open_status = ?  ");
            String l_strWhere = l_strWhereBuf.toString();
            
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    l_datT0,
                    RuitoTypeEnum.CHUUKOKU_FUND,
                    RuitoTypeEnum.MMF,
                    OrderStatusEnum.CANCEL_ACCEPTED,
                    OrderStatusEnum.CANCELLING,
                    OrderStatusEnum.CANCELLED,
                    OrderOpenStatusEnum.OPEN
                    
            };
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(RuitoOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
            
            //�V�K�������e����荞��
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs == null)
            {
                return l_lisRows;
            }
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                RuitoOrderUnitParams l_params = (RuitoOrderUnitParams) l_lisRows.get(i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if(!ProductTypeEnum.RUITO.equals(l_newOrderSpecs[i].getProductType()))
                {
                    continue;
                }
                RuitoOrderUnitParams l_params = new RuitoOrderUnitParams();
                l_params.setAccountId(l_accountId);
                l_params.setSubAccountId(l_subAccountId);
                l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                l_params.setProductType(l_newOrderSpecs[i].getProductType());
                l_params.setProductId(l_newOrderSpecs[i].getProductId());
                l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                
                RuitoOrderUnitParams l_oldParams = (RuitoOrderUnitParams) l_ht.get(new
                        Long(l_params.getOrderUnitId()));
                if (l_oldParams != null)
                {
                    l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                    l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                }
                else
                {
                    l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                }
            }
            List l_lisRet = new ArrayList();
            Iterator l_iterator = l_ht.values().iterator();
            while (l_iterator.hasNext())
            {
                RuitoOrderUnitParams l_ruitoOrderUnitParams = (RuitoOrderUnitParams)
                l_iterator.next();
                l_lisRet.add(l_ruitoOrderUnitParams);
                
            }
            return l_lisRet;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get����)<BR>
     * �����e�[�u���������������������<BR>
     * ���������F<BR>
     * �@@�����e�[�u��.����ID = ����.����ID<BR>
     * <BR>
     * @@param l_lngProductId - (�����h�c)
     * @@return ProductParams
     * @@roseuid 4104D2DE034B
     */
    public ProductParams getProduct(long l_lngProductId)
    {
        final String STR_METHOD_NAME =
            "getProduct(long l_lngProductId)";
        try
        {
            return (ProductParams) ProductDao.findRowByPk(l_lngProductId);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�����������)<BR>
     *
     * ��ЃR�[�h=����.���Y�]��.�ڋq����.��ЃR�[�h
     * ����.���Y�]��.�]�͌v�Z����.����������t�J�n�敪<��������>=true <BR>
     * �̏ꍇ�A�L����=����.�]�͌v�Z����.get�c�Ɠ�[T�{1] <BR>
     *
     * ����.���Y�]��.�]�͌v�Z����.����������t�J�n�敪<��������>=false <BR>
     * �̏ꍇ�A�L����=����.�]�͌v�Z����.get�c�Ɠ�[T�{0] <BR>
     *
     * ������������e�[�u���ieqtype_traded_product�j����������B<BR>
     * ���������F<BR>
     * �@@������������e�[�u��.����ID = ����.����ID<BR>
     * �@@������������e�[�u��.�L���� = �L����<BR>
     * �@@������������e�[�u��.��ЃR�[�h = ��ЃR�[�h<BR>
     *
     * ������������ɊY���f�[�^�Ȃ���΁A<BR>
     *
     * ������������e�[�u��_updq(eqtype_traded_product_updq)����������B
     * ���������F<BR>
     *   ������������e�[�u��_updq.����ID = ����.����ID<BR>
     *   ������������e�[�u��_updq.�L���� = �L����<BR>
     * �@@������������e�[�u��_updq.��ЃR�[�h = ��ЃR�[�h<BR>
     * @@param l_lngProductId - (�����h�c)
     * @@param l_assetValuation -(���Y�]��)
     * @@return List
     * @@roseuid 4104D2DE035B
     */
    public List getEqTypeTradedProduct(
            long l_lngProductId,
            WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getEqTypeTradedProduct(long l_lngProductId,WEB3TPAssetValuation l_assetValuation)";
        try
        {
            List l_ret = Collections.EMPTY_LIST;
            String baseDate = null;
            WEB3TPCalcCondition l_calcCondition =
                l_assetValuation.getCalcCondition();
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            
            if (l_calcCondition.isEquityNextDayOrderStartDiv())
            {
                baseDate =
                    GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                            l_calcCondition.getBizDate(1));
                //T+1
            }
            else
            {
                baseDate =
                    GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                            l_calcCondition.getBizDate(0));
                //T+0
            }
            l_ret =
                EqtypeTradedProductDao
                .findRowsByInstitutionCodeProductIdValidUntilBizDate(
                        l_accountInfo.getInstitutionCode(),
                        l_lngProductId,
                        baseDate);
            if (l_ret.size() <= 0)
            {
                l_ret =
                    EqtypeTradedProductUpdqDao
                    .findRowsByInstitutionCodeProductIdValidUntilBizDate(
                            l_accountInfo.getInstitutionCode(),
                            l_lngProductId,
                            baseDate);
            }
            return l_ret;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get������背�R�[�h)<BR>
     * �����������e�[�u�����犔��������������������B<BR>
     * ���������F<BR>
     * �@@�����������e�[�u��.�����P��ID = ����.�����P��ID<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)
     * @@return List
     * @@roseuid 4104D2DE036B
     */
    public List getEqTypeOrderExecutions(EqtypeOrderUnitRow l_orderUnit)
    {
        
        final String STR_METHOD_NAME =
            "getEqTypeOrderExecutions(long l_lngOrderUnitId)";
        
        try
        {
            return EqtypeOrderExecutionDao.findRowsByOrderUnitId(l_orderUnit.
                    getOrderUnitId());
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get���ʏ��<�m��>)<BR>
     * �m��̌��ʏ����擾����B<BR>
     * <BR>
     * �P�j�m��̌��ʏ����擾����B<BR>
     * �@@�@@�m�茚���e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�|��������<BR>
     * �@@�@@�@@�E�m�茚���e�[�u��.����ID�@@�@@�@@������.�ڋq����.����ID<BR>
     * �@@�@@�@@�E�m�茚���e�[�u��.�⏕����ID�@@������.�ڋq����.�⏕����ID<BR>
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3360274
     */
    public List getFixedContracts(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getFixedContracts(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        try
        {
            List l_lisRows = EqtypeFixedContractDao
            .findRowsByAccountIdSubAccountId(l_accountInfo.getAccountId(),
                    l_accountInfo.getSubAccountId(true));
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get���ʏ��<����>)<BR>
     * �����̌��ʏ����擾����B<BR>
     * <BR>
     * �P�j�����̌��ʏ����擾����B<BR>
     * �@@�@@�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�|��������<BR>
     * �@@�@@�@@�E�����e�[�u��.����ID�@@�@@�@@������.�ڋq����.����ID<BR>
     * �@@�@@�@@�E�����e�[�u��.�⏕����ID�@@������.�ڋq����.�⏕����ID<BR>
     * �@@�@@�@@�E�����e�[�u��.�����@@�@@�@@�@@������.�]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3360293
     */
    public List getTodaysContracts(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysContracts(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("TRUNC(open_date) = ? ");
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars =
            {
                    new Long(l_accountInfo.getAccountId()),
                    new Long(l_accountInfo.getSubAccountId(true)),
                    l_calcCondition.getBizDate(0)
            };
            log.debug(STR_METHOD_NAME + " WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            
            List l_lisRows = l_qp.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�ϓ����<�m��>)><BR>
     * �m��̕ϓ������擾����B<BR>
     * <BR>
     * �P�j�m��̕ϓ������擾����B<BR>
     * �@@�@@�m��g�����U�N�V�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�|��������<BR>
     * �@@�@@�@@�E�m��g�����U�N�V�����e�[�u��.����ID�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@������.�ڋq����.����ID<BR>
     * �@@�@@�@@�E�m��g�����U�N�V�����e�[�u��.�⏕����ID�@@�@@�@@�@@�@@�@@�@@�@@������.�ڋq����.�⏕����ID<BR>
     * �@@�@@�@@�E�m��g�����U�N�V�����e�[�u��.�g�����U�N�V�����J�e�S���@@in�@@( �ԍώ�� , �������n��� )
     * �@@�@@�@@�E�m��g�����U�N�V�����e�[�u��.��n���@@�@@�@@�@@�@@�@@�@@�@@�@@�@@������.�]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�E�m��g�����U�N�V�����e�[�u��.�폜�t���O        �@@�@@�@@�@@��FALSE<BR>
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D33602B2
     */
    public List getFixedHistories(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getFixedHistories(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("fin_transaction_categ IN (?, ?) AND ");
            l_strWhereBuf.append("delivery_date > ? AND ");
            l_strWhereBuf.append("delete_flag = ? ");
            String l_strWhere = l_strWhereBuf.toString();
            
            Object[] l_bindVars =
            {
                    new Long(l_accountInfo.getAccountId()),
                    new Long(l_accountInfo.getSubAccountId(true)),
                    FinTransactionCateg.EQTYPE_CLOSE_MARGIN,
                    FinTransactionCateg.EQTYPE_SWAP_MARGIN,
                    l_calcCondition.getBizDate(0),
                    BooleanEnum.FALSE
            };
            log.debug(STR_METHOD_NAME + " WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            log.debug("BindVars[3]=" + l_bindVars[3]);
            log.debug("BindVars[4]=" + l_bindVars[4]);
            log.debug("BindVars[5]=" + l_bindVars[5]);
            
            List l_lisRows = l_qp.doFindAllQuery(FixedFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�ϓ����<����>)<BR>
     * �����̕ϓ������擾����B<BR>
     * <BR>
     * �P�j�����̕ϓ������擾����B<BR>
     * �@@�@@�g�����U�N�V�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�|��������<BR>
     * �@@�@@�@@�E�g�����U�N�V�����e�[�u��.����ID�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@������.�ڋq����.����ID<BR>
     * �@@�@@�@@�E�g�����U�N�V�����e�[�u��.�⏕����ID�@@�@@�@@�@@�@@�@@�@@�@@������.�ڋq����.�⏕����ID<BR>
     * �@@�@@�@@�E�g�����U�N�V�����e�[�u��.�g�����U�N�V�����J�e�S���@@in�@@( �ԍώ�� , �������n��� )<BR>
     * �@@�@@�@@�E�g�����U�N�V�����e�[�u��.���������@@�@@�@@�@@�@@�@@�@@�@@�@@������.�]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�E�g�����U�N�V�����e�[�u��.�폜�t���O       �@@�@@�@@�@@��FALSE<BR>
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D33602C2
     */
    public List getTodaysHistories(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysHistories(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("fin_transaction_categ IN (?, ?) AND ");
            l_strWhereBuf.append("TRUNC(fin_transaction_timestamp) = ? AND ");
            l_strWhereBuf.append("delete_flag = ? ");
            String l_strWhere = l_strWhereBuf.toString();
            
            Object[] l_bindVars =
            {
                    new Long(l_accountInfo.getAccountId()),
                    new Long(l_accountInfo.getSubAccountId(true)),
                    FinTransactionCateg.EQTYPE_CLOSE_MARGIN,
                    FinTransactionCateg.EQTYPE_SWAP_MARGIN,
                    l_calcCondition.getBizDate(0),
                    BooleanEnum.FALSE
            };
            log.debug(STR_METHOD_NAME + " WHERE=" + l_strWhere);
            log.debug("BindVars[0]=" + l_bindVars[0]);
            log.debug("BindVars[1]=" + l_bindVars[1]);
            log.debug("BindVars[2]=" + l_bindVars[2]);
            log.debug("BindVars[3]=" + l_bindVars[3]);
            log.debug("BindVars[4]=" + l_bindVars[4]);
            log.debug("BindVars[5]=" + l_bindVars[5]);
            
            List l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�����ϓ����)<BR>
     * �����ϓ������擾����B<BR>
     * <BR>
     * �P�j�����ϓ������擾����B<BR>
     * �@@�@@���������P�ʃe�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�|��������<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.����ID�@@�@@�@@�@@������.�ڋq����.����ID<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.�⏕����ID�@@�@@������.�ڋq����.�⏕����ID<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.�J�e�S���@@�@@�@@in�@@( �V�K������ , �������n���� )
     * �@@�@@�@@�E���������P�ʃe�[�u��.�������@@�@@�@@�@@��������.�]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.������� �@@�@@�@@not in ( �����ρi��������j)<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.�����L����ԁ@@���I�[�v��
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D33602D2
     */
    public List getUnExecutedOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getUnExecutedOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(l_accountInfo.
                isMarginCustomer());
        
        //Y00125�F�����S�����Ή�
        //�����S�������擾
        double l_dblPreRestRate = this.getPremiumRestraintRate(
                WEB3CommisionProductCodeDef.LISTING_STOCK,
                l_assetValuation);
        
        //�������������ȍ~�̒���List(getTodaysEquityOrders�Ŏ擾����List)
        List l_lisRows=l_assetValuation.getTodaysEquityOrders();
        
        TreeMap l_ht = new TreeMap();
        for(int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_lisRows.get(i);
            
            //�T�u�A�J�E���gID
            if(l_subAccountId == l_row.getSubAccountId())
            {
                //�����L����ԁ��I�[�v��
                if(OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus()))
                {
                    //�J�e�S���@@in�@@( �V�K������ , �������n���� )
                    if(OrderCategEnum.OPEN_MARGIN.equals(l_row.getOrderCateg())
                            || OrderCategEnum.SWAP_MARGIN.equals(l_row.getOrderCateg()))
                    {
                        //������� not in ( �����ρi��������j)
                        if(! OrderStatusEnum.CANCELLED.equals(l_row.getOrderStatus()))
                        {
                            //�s��m�F�ϒl�Ή�
                            //Y00125�F�����S�����Ή�
                            l_ht.put(
                                    new Long(l_row.getOrderUnitId()),
                                    new WEB3TPEqtypeOrderUnitRowWrapper(l_row, l_dblPreRestRate));
                        }
                    }
                }
            }
        }
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        //���������e��null�łȂ��ꍇ�A���f������
        if(l_newOrderSpecs != null)
        {
            int l_intNewSize = l_newOrderSpecs.length;
            for (int i = 0; i < l_intNewSize; i++)
            {
                //�J�e�S�����V�K�� ���� �������n
                OrderCategEnum l_newSpecOrderCateg = l_newOrderSpecs[i].getOrderCategory();
                if (OrderCategEnum.SWAP_MARGIN.equals(l_newSpecOrderCateg)
                        || OrderCategEnum.OPEN_MARGIN.equals(l_newSpecOrderCateg))
                {
                    //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                    EqtypeOrderUnitRow l_oldParams = 
                        (EqtypeOrderUnitRow)l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //�Â�Params�ɐV���������㏑������                  
                    if (l_oldParams != null)
                    {   
                        //��Params����R�s�[Params�쐬
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        
                        //��Params���폜
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                    }
                    
                    l_params.setAccountId(l_accountId);
                    l_params.setSubAccountId(l_subAccountId);
                    l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                    l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                    l_params.setProductType(l_newOrderSpecs[i].getProductType());
                    l_params.setProductId(l_newOrderSpecs[i].getProductId());
                    l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                    l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                    l_params.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                    l_params.setBizDate(WEB3DateUtility.formatDate(
                            l_newOrderSpecs[i].
                            getOrderBizDate(), "yyyyMMdd"));
                    l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                    l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                    l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                    l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                    l_params.setMarketId(l_newOrderSpecs[i].getMarketId());
                    l_params.setPrice(l_newOrderSpecs[i].getPrice());
                    
                    //Y00125�F�����S�����Ή�
                    l_params.setLimitPrice(l_newOrderSpecs[i].getLimitPrice());
                    
                    //Y00125�F�����S�����Ή�
                    l_ht.put(
                            new Long(l_params.getOrderUnitId()),
                            new WEB3TPEqtypeOrderUnitRowWrapper(
                                    l_params, l_dblPreRestRate));
                }
            }
        }
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get���ʕԍώw����)<BR>
     * ���ʕԍώw������擾����B<BR>
     * <BR>
     * �P�j���ʕԍώw������擾����B<BR>
     * �@@�@@���ʕԍώw����e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�|��������<BR>
     * �@@�@@�@@�E���ʕԍώw����e�[�u��.����ID�@@�@@�@@������.�ڋq����.����ID<BR>
     * �@@�@@�@@�E���ʕԍώw����e�[�u��.�⏕����ID�@@������.�ڋq����.�⏕����ID<BR>
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D33602F1
     */
    public List getClosingContractSpecs(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getClosingContractSpecs(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        List l_lisRows = null;
        
        try
        {
            l_lisRows = EqtypeClosingContractSpecDao
            .findRowsByAccountIdSubAccountId(l_accountInfo.getAccountId(),
                    l_accountInfo.getSubAccountId(true));
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs == null)
        {
            return l_lisRows;
        }
        
        //���������e��List�ɔ��f
        TreeMap l_ht = new TreeMap();
        int l_lisRowsSize = l_lisRows.size();
        for (int i = 0; i < l_lisRowsSize; i++)
        {
            EqtypeClosingContractSpecParams l_params = (EqtypeClosingContractSpecParams) l_lisRows.get(i);
            String l_strKey= String.valueOf(l_params.getOrderUnitId()) + String.valueOf(l_params.getContractId());
            l_ht.put(l_strKey, l_params);
        }
        
        int l_intNewSize = l_newOrderSpecs.length;
        for (int i = 0; i < l_intNewSize; i++)
        {
            //�J�e�S�����������n�@@�܂��́A�@@�ԍς̏ꍇ
            OrderCategEnum l_newSpecOrderCateg = l_newOrderSpecs[i].getOrderCategory();
            if (OrderCategEnum.SWAP_MARGIN.equals(l_newSpecOrderCateg)
                    || OrderCategEnum.CLOSE_MARGIN.equals(l_newSpecOrderCateg))
            {
                //���������e�̕ԍώw������擾
                WEB3TPContractSettleSpecify[] l_contractSettleSpecs = l_newOrderSpecs[i].getContractSettleSpecify();
                
                //�V�K ���� ���������̏ꍇ
                if(l_contractSettleSpecs != null)
                {
                    int l_intSpecsSize = l_contractSettleSpecs.length;
                    for (int j = 0; j < l_intSpecsSize; j++)
                    {
                        //���������e�̕ԍώw��������ɃI�u�W�F�N�g����
                        EqtypeClosingContractSpecParams l_eqClosingContractSpec = new EqtypeClosingContractSpecParams();
                        l_eqClosingContractSpec.setClosingContractSpecId(l_newOrderSpecs[i].getOrderUnitId());
                        l_eqClosingContractSpec.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_eqClosingContractSpec.setContractId(l_contractSettleSpecs[j].getContractId());
                        l_eqClosingContractSpec.setQuantity(l_contractSettleSpecs[j].getQuantity());
                        
                        String l_strNewKey = String.valueOf(l_eqClosingContractSpec.getOrderUnitId()) + String.valueOf(l_eqClosingContractSpec.getContractId());
                        EqtypeClosingContractSpecParams l_oldParams = (EqtypeClosingContractSpecParams) l_ht.get(l_strNewKey);
                        if (l_oldParams != null)
                        {
                            l_eqClosingContractSpec.setExecutedQuantity(l_oldParams.getExecutedQuantity());
                            String l_strOldKey = String.valueOf(l_oldParams.getOrderUnitId()) + String.valueOf(l_oldParams.getContractId());
                            l_ht.remove(l_strOldKey);
                            l_ht.put(l_strNewKey, l_eqClosingContractSpec);
                        }
                        else
                        {
                            l_ht.put(l_strNewKey, l_eqClosingContractSpec);
                        }
                    }
                }
                //��������̏ꍇ
                else
                {
                    //DB�����Ŏ擾�����ԍώw����Params�ꗗ�Ń��[�v
                    for (int j = 0; j < l_lisRowsSize; j++)
                    {
                        //�o�^�����ԍώw����Params���擾
                        EqtypeClosingContractSpecParams l_params = (EqtypeClosingContractSpecParams) l_lisRows.get(j);
                        
                        long l_lngCancelOrderUnitId = l_newOrderSpecs[i].getOrderUnitId();
                        
                        long l_lngCancelClosingContractOrderUnitId = l_params.getOrderUnitId();
                        
                        //�����P��ID�������ꍇ
                        if(l_lngCancelOrderUnitId == l_lngCancelClosingContractOrderUnitId)
                        {
                            //DB�����Ŏ擾�����ԍώw����Params���폜
                            String l_strRegisterdKey = String.valueOf(l_params.getOrderUnitId()) + String.valueOf(l_params.getContractId());
                            l_ht.remove(l_strRegisterdKey);
                        }
                    }
                }
            }
        }
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get�����������)<BR>
     * ����������������擾����B<BR>
     * <BR>
     * �P�j��������������擾����B<BR>
     * �@@�@@������������}�X�^�[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�|��������<BR>
     * �@@�@@�@@�E������������}�X�^�[�e�[�u��.�،���ЃR�[�h�@@������.���Y�]��.get�،���ЃR�[�h<BR>
     * �@@�@@�@@�E������������}�X�^�[�e�[�u��.����ID�@@�@@�@@�@@�@@������.����ID<BR>
     * �@@�@@�@@�E������������}�X�^�[�e�[�u��.�s��ID�@@�@@�@@�@@�@@������.�s��ID<BR>
     * �@@�@@�@@�E������������}�X�^�[�e�[�u��.�L�����@@�@@�@@�@@�@@������.������<BR>
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@param l_lngProductId - (����ID)
     * @@param l_lngMarketId - (�s��ID)
     * @@param l_strValidUntilBizDate - (������)
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow
     * @@roseuid 4104D3360300
     */
    public EqtypeTradedProductRow getEqtypeTradedProduct(WEB3TPAssetValuation
            l_assetValuation, long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)
    {
        final String STR_METHOD_NAME =
            "getEqtypeTradedProduct(WEB3TPAssetValuation l_assetValuation, long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        try
        {
            EqtypeTradedProductRow l_row
            = EqtypeTradedProductDao.
            findRowByInstitutionCodeProductIdMarketIdValidUntilBizDate
            (
                    l_accountInfo.getInstitutionCode(),
                    l_lngProductId,
                    l_lngMarketId,
                    l_strValidUntilBizDate
            );
            return l_row;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�����������)<BR>
     * ����������������擾����B<BR>
     * <BR>
     * �P�j��������������擾����B<BR>
     * ������������}�X�^�[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �|��������<BR>
     * �E������������}�X�^�[�e�[�u��.�،���ЃR�[�h ������.�،���ЃR�[�h<BR>
     * �E������������}�X�^�[�e�[�u��.����ID ������.����ID<BR>
     * �E������������}�X�^�[�e�[�u��.�s��ID ������.�s��ID<BR>
     * �E������������}�X�^�[�e�[�u��.�L���� ������.������<BR>
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * 
     * @@param l_strInstCode - (�،���ЃR�[�h)
     * @@param l_lngProductId - (����ID)
     * @@param l_lngMarketId - (�s��ID)
     * @@param l_strValidUntilBizDate - (������)
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow
     */
    public EqtypeTradedProductRow getEqtypeTradedProduct(String l_strInstCode, long l_lngProductId,
            long l_lngMarketId, String l_strValidUntilBizDate)
    {
        final String STR_METHOD_NAME = "getEqtypeTradedProduct(String l_strInstCode, long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)";

        try
        {
            EqtypeTradedProductRow l_row = EqtypeTradedProductDao
                    .findRowByInstitutionCodeProductIdMarketIdValidUntilBizDate(l_strInstCode,
                            l_lngProductId, l_lngMarketId, l_strValidUntilBizDate);
            return l_row;
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass()
                    .getName()
                    + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }

    /**
     * (get�����������Updq)<BR>
     * �����������Updq�����擾����B<BR>
     * <BR>
     * �P�j�����������Updq���擾����B<BR>
     * ������������}�X�^�[Updq�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �|��������<BR>
     * �E������������}�X�^�[Updq�e�[�u��.����ID ������.����ID<BR>
     * �E������������}�X�^�[Updq�e�[�u��.�s��ID ������.�s��ID<BR>
     * �E������������}�X�^�[Updq�e�[�u��.�L���� ������.������<BR>
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_lngProductId - (����ID)
     * @@param l_lngMarketId - (�s��ID)
     * @@param l_strValidUntilBizDate - (������)
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow
     */
    public EqtypeTradedProductUpdqRow getEqtypeTradedProductUpdq(long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)
    {
        final String STR_METHOD_NAME =
            "getEqtypeTradedProductUpdq(long l_lngProductId, long l_lngMarketId, String l_strValidUntilBizDate)";
        
        try
        {
            EqtypeTradedProductUpdqRow l_row
            = EqtypeTradedProductUpdqDao.
            findRowByProductIdMarketIdValidUntilBizDate
            (
                    l_lngProductId,
                    l_lngMarketId,
                    l_strValidUntilBizDate
            );
            return l_row;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�����ȍ~����������)<BR>
     * �����ȍ~�����������i���������P�ʂ̃��X�g�j���擾����B<BR>
     * <BR>
     * �P�j ���������P�ʃe�[�u����蒍������<BR>
     * <BR>
     * �������ϊ����i���E���j<BR>
     * ��������芔���i���j<BR>
     * ��������芔���i���j<BR>
     * �������B<BR>
     * <BR>
     * �����F<BR>
     * ����ID �� �ڋq����.get����ID()<BR>
     * �⏕����ID �� �ڋq����.get�⏕����ID�i�⏕�����^�C�v�j<BR>
     * �⏕�����^�C�v�E�E�E�����q�F�⏕�����^�C�v.����<BR>
     * �E�E�E�M�p�q�F�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j<BR>
     * �����^�C�v �� ��<BR>
     * ������� IN ���������A��������<BR>
     * ������ ���� ����<BR>
     * ������� NOT IN �����<BR>
     * �����L����� �� �I�[�v��<BR>
     * OR<BR>
     * ��萔�� ��0<BR>
     * <BR>
     * �Q�j �������ꂽ���������̃��X�g��Ԃ�<BR>
     * 
     * @@param l_assetValuation -
     *            (���Y�]��)
     * @@return List
     * @@roseuid 4104D3750355
     */
    public List getTodaysCashBasedEquityOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCashBasedEquityOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId;
        //�����q�̏ꍇ
        if (!l_accountInfo.isMarginCustomer())
        {
            l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
        }
        //�M�p�q�̏ꍇ
        else
        {
            l_subAccountId = l_accountInfo.getSubAccountId(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        
        List l_lisRows=l_assetValuation.getTodaysEquityOrders();
        
        //�t�B���^����
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.
            get(i);
            if ((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (OrderTypeEnum.EQUITY_BUY.equals(l_params.getOrderType())
                            || OrderTypeEnum.EQUITY_SELL.equals(l_params.getOrderType()))
                            && 
                            ((OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()))
                                    || (OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus())
                                            &&
                                            ( (l_params.getExecutedQuantity() > 0) &&
                                                    (l_params.getQuantity() - l_params.getExecutedQuantity()) >= 0)))
            )
            {
                //�s��m�F�ϒl�Ή�
                l_ht.put(new Long(l_params.getOrderUnitId()),
                        new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
            }
        }
        
        //�V�K�������e����荞��
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.EQUITY_BUY.equals(l_newOrderSpecs[i].getOrderType()) 
                        ||			            
                        OrderTypeEnum.EQUITY_SELL.equals(l_newOrderSpecs[i].getOrderType()))
                {
                    //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                    EqtypeOrderUnitRow l_oldParams = (EqtypeOrderUnitRow)
                    l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //�Â�Params�ɐV���������㏑������		        	
                    if (l_oldParams != null)
                    {	
                        //��Params����R�s�[Params�쐬
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setPrice(l_newOrderSpecs[i].getPrice());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        
                        //��Params���폜
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setPrice(l_newOrderSpecs[i].getPrice());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());				        			
                    }
                    
                    //������or�V����Params��ǉ�
                    if(l_params.getEstimatedPrice() != 0.0d)
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()),
                                new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
                    }
                    else
                    {
                        log.debug("�T�Z��n���z��0�̐V�K�������e=" + l_newOrderSpecs[i]);
                    }
                }
            }
        }
        
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get�����ȍ~�M�p�ԍϒ���)<BR>
     *�@@�����ȍ~�M�p�ԍϒ����i���������P�ʂ̃��X�g�j���擾����B<BR>
     *<BR>
     * �P�j�@@��������<BR>
     * <BR>
     * ���������P�ʃe�[�u����蒍�������B<BR>
     * <BR>
     * �������ό��ϑ�<BR>
     * �������ό��ωv<BR>
     * �������B<BR>
     * <BR>
     * �����F<BR>
     * ����ID     ��   �ڋq����.get����ID()<BR>
     * �⏕����ID       ��   �ڋq����.get�⏕����ID�i�⏕�����^�C�v�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�⏕�����^�C�v�E�E�E�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j<BR>
     * �����J�e�S��   ��   �M�p�ԍ�<BR>
     * ������      ��   ����<BR>
     * ��萔�� ��   0<BR>
     * <BR>
     * <BR>
     * �Q�j�@@���������̃��X�g��Ԃ�<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3750375
     */
    public List getTodaysCloseMarginOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCloseMarginOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        //�M�p�q�łȂ��ꍇ�v�f0�̃��X�g��Ԃ�
        if (!l_accountInfo.isMarginCustomer())
        {
            return Collections.EMPTY_LIST;
        }
        long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                EQUITY_MARGIN_SUB_ACCOUNT);
        
        List l_lisRows = l_assetValuation.getTodaysEquityOrders();
        
        //�V�K�������e�͖����̂��ߎ�荞�܂Ȃ�
        
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.
            get(i);
            if((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (OrderCategEnum.CLOSE_MARGIN.equals(l_params.getOrderCateg()))
                    && 
                    (l_params.getExecutedQuantity() > 0)
            )
            {
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        List l_lisRet = new ArrayList();
        Iterator l_iterator = l_ht.values().iterator();
        while (l_iterator.hasNext())
        {
            EqtypeOrderUnitParams l_eqOrderUnitParams = (EqtypeOrderUnitParams)
            l_iterator.next();
            l_lisRet.add(l_eqOrderUnitParams);
            
        }
        return l_lisRet;
    }
    
    /**
     * (get�����ȍ~�������n����)<BR>
     *�@@�����ȍ~�������n�����i���������P�ʂ̃��X�g�j���擾����B<BR>
     *<BR>
     * �P�j�@@��������<BR>
     * <BR>
     * ���������P�ʃe�[�u����蒍�������B<BR>
     * <BR>
     * �������ό��ρi�����E���n�j<BR>
     * ��������茈�ρi�����E���n�j<BR>
     * ��������茈�ρi�����E���n�j<BR>
     * �������B<BR>
     * <BR>
     * �����F<BR>
     * ����ID     ��   �ڋq����.get����ID()<BR>
     * �⏕����ID       ��   �ڋq����.get�⏕����ID�i�⏕�����^�C�v�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�⏕�����^�C�v�E�E�E�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j<BR>
     * �����J�e�S��   ��   �������n<BR>
     * ������      ����  ����<BR>
     * �������     NOT IN  �����<BR>
     * (<BR>
     * �����L�����   ��   �I�[�v��<BR>
     * OR<BR>
     * (<BR>
     * �����L�����   ��   �N���[�Y<BR>
     * AND<BR>
     * ��萔��     ��   0<BR>
     * ))<BR>
     * <BR>
     * �Q�j�@@�������ꂽ���������̃��X�g��Ԃ�<BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3750384
     */
    public List getTodaysSwapMarginOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysSwapMarginOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        log.debug("l_accountInfo.isMarginCustomer=" + l_accountInfo.isMarginCustomer());
        
        //�M�p�q�łȂ��ꍇ�v�f0�̃��X�g��Ԃ�
        if (!l_accountInfo.isMarginCustomer())
        {
            return Collections.EMPTY_LIST;
        }
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                EQUITY_MARGIN_SUB_ACCOUNT);
        
        List l_lisRows = l_assetValuation.getTodaysEquityOrders();
        
        log.debug("getTodaysEquityOrders�@@size=" + l_lisRows.size());
        
        //�t�B���^����
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.
            get(i);
            if((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (OrderCategEnum.SWAP_MARGIN.equals(l_params.getOrderCateg())) 
                    &&
                    (!OrderStatusEnum.CANCELLED.equals(l_params.getOrderStatus())) 
                    &&
                    ((OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus())) 
                            ||
                            (l_params.getExecutedQuantity() > 0)
                    )
            )
                
            {            	
                log.debug("�ǉ����������������n����=" + l_params);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        
        //�V�K�������e����荞��
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_newOrderSpecs[i].
                        getOrderType()) ||
                        OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_newOrderSpecs[i].
                                getOrderType()))
                {
                    
                    //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                    EqtypeOrderUnitRow l_oldParams = (EqtypeOrderUnitRow)
                    l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //�Â�Params�ɐV���������㏑������		        	
                    if (l_oldParams != null)
                    {	
                        //��Params����R�s�[Params�쐬
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        
                        //��Params���폜
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());				        
                        
                    }
                    
                    //������or�V����Params��ǉ�
                    //���n��������́A����ςƂ��Ė��ϑ���Ɍv�サ�Ȃ�
                    //���̂Ƃ��̊T�Z��n�����0�~                        
                    if(l_params.getEstimatedPrice() != 0.0d)
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()),
                                new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
                    }
                    else
                    {
                        log.debug("�T�Z��n���z��0�̐V�K�������e=" + l_newOrderSpecs[i]);
                    }
                    
                }
            }
        }
        
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get�����M�������ꗗ)<BR>
     *�@@�����M�������ꗗ�i���M�����P�ʂ̃��X�g�j���擾����B<BR>
     *<BR>
     * �P�j�@@��������<BR>
     * <BR>
     * ���M�����P�ʃe�[�u����蒍�������B<BR>
     * <BR>
     * ����蓊�M�i���t�A��W�A�抷�A���t<�o���\��t��>�j<BR>
     * �������B<BR>
     * <BR>
     * �����F<BR>
     * ����ID      ��   �ڋq����.get����ID()<BR>
     * �⏕����ID   ��   �ڋq����.get�⏕����ID�i�⏕�����^�C�v�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�⏕�����^�C�v�E�E�E�����q�F�⏕�����^�C�v.����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�M�p�q�F�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j<BR>
     * �����^�C�v   ��   ���M<BR>
     * ������     ��   ���M���t�@@OR�@@���M��W�@@OR�@@���M�抷�@@OR�@@(���M���t AND �o���������ʃR�[�h IS NOT NULL)<BR>
     * �����L����ԁ@@���@@�I�[�v��<BR>
     * ���ϋ敪     ���~��<BR>
     * <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�������ꂽ���M�����̃��X�g��Ԃ�<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D37503A3
     */
    public List getMutualFundOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            //���M���͖�ԃo�b�`�Ŕ��f�����̂Łi�q�ϔ��f�ρj
            //�����̂ݒ��o����΂悢
            //��������蒆�̂��̂����݂���̂�
            //�����L����ԁ��I�[�v���̂��̂͂��ׂĒ��o����B
            //�O�݌��ē��M�͉~���̂��̂̂ݒ��o�B
            
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            long l_accountId = l_accountInfo.getAccountId();
            l_lisBindVars.add(new Long(l_accountId));
            
            //���o�����@@�⏕����ID�̎擾
            //�⏕�����^�C�v�E�E�E�����q�F�⏕�����^�C�v.����
            //                   �E�E�E�M�p�q�F�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j
            long l_defaultSubAccountId;
            //�����q�̏ꍇ
            if (!l_accountInfo.isMarginCustomer())
            {
                l_strWhereBuf.append("sub_account_id = ? AND ");
                long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
                l_defaultSubAccountId = l_subAccountId;
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //�M�p�q�̏ꍇ
            else
            {
                l_strWhereBuf.append("sub_account_id in (?, ?) AND ");
                long l_equitySubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_equitySubAccountId));
                long l_marginSubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_defaultSubAccountId = l_marginSubAccountId;
                l_lisBindVars.add(new Long(l_marginSubAccountId));
                
            }
            
            l_strWhereBuf.append("product_type = ? AND ");
            l_lisBindVars.add(ProductTypeEnum.MUTUAL_FUND);
            
            l_strWhereBuf.append("(order_type = ? OR ");
            l_lisBindVars.add(OrderTypeEnum.MF_BUY);
            
            l_strWhereBuf.append("order_type = ? OR ");
            l_lisBindVars.add(OrderTypeEnum.MF_RECRUIT);
            
            l_strWhereBuf.append("order_type = ? OR ");
            l_lisBindVars.add(OrderTypeEnum.MF_SWITCHING);
            
            l_strWhereBuf.append("(order_type = ? AND payment_order_req_number IS NOT NULL )) AND ");
            l_lisBindVars.add(OrderTypeEnum.MF_SELL);
            
            l_strWhereBuf.append("order_open_status = ? AND ");
            l_lisBindVars.add(OrderOpenStatusEnum.OPEN);
            
            l_strWhereBuf.append("settlement_div = ? ");
            l_lisBindVars.add(WEB3SettlementDivDef.JAPANESE_CURRENCY);
            
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars = l_lisBindVars.toArray();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(MutualFundOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                MutualFundOrderUnitParams l_params = (MutualFundOrderUnitParams)
                l_lisRows.get(i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            
            //�V�K�������e����荞��
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {
                    if (OrderTypeEnum.MF_BUY.equals(l_newOrderSpecs[i].getOrderType())
                            || OrderTypeEnum.MF_RECRUIT.equals(l_newOrderSpecs[i].getOrderType())
                            || OrderTypeEnum.MF_SWITCHING.equals(l_newOrderSpecs[i].getOrderType()))
                    {
                        //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                        MutualFundOrderUnitRow l_oldParams = (MutualFundOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        MutualFundOrderUnitParams l_params = null;
                        
                        //�Â�Params�ɐV���������㏑������		        	
                        if (l_oldParams != null)
                        {	
                            //��Params����R�s�[Params�쐬
                            l_params = new MutualFundOrderUnitParams(l_oldParams);
                            
                            //��Params���폜
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        }
                        else
                        {
                            l_params = new MutualFundOrderUnitParams();
                        }
                        
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_defaultSubAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setWithholdingTaxRestriction(l_newOrderSpecs[i].getWithholdingTaxRestriction());
                        l_params.setPaymentDate(l_newOrderSpecs[i].getPaymentDate());
                        
                        l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                    }	                        
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�����M������<������>)<BR>
     *�@@����.���M�����ꗗ����������̌v��ΏۂƂȂ钍���������o����B<BR>
     *<BR>
     * �P�j�ȉ��̏����Ńt�B���^�����O����B<BR>
     * <BR>
     * �@@�E������ʁ����M���t�@@OR�@@���M��W�@@OR�@@���M���t<BR>
     * <BR>
     * �Q�j�����ɓK�����������̃��X�g��Ԃ��B<BR>
     * <BR>
     * @@param l_mutualFundOrders - (�����M�������ꗗ)
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     */
    public List getMutualFundOrdersAmount(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundOrdersAmount(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)";
        
        List l_list = new ArrayList();
        for(int i = 0; i < l_mutualFundOrders.size(); i++)
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_mutualFundOrders.get(i);
            OrderTypeEnum l_orderType = l_row.getOrderType();
            
            //���M���t�A���M��W�A���M���t�����̂ݎ擾����
            if(OrderTypeEnum.MF_BUY.equals(l_orderType)
                    || OrderTypeEnum.MF_RECRUIT.equals(l_orderType)
                    || OrderTypeEnum.MF_SELL.equals(l_orderType))
            {
                l_list.add(l_row);
            }
        }
        return l_list;
    }
    
    /**
     * (get�����M������<���n�v��>)<BR>
     *�@@����.���M�����ꗗ���瓊���M�����n�v�ōS�����̌v��ΏۂƂȂ钍���������o����B<BR>
     *<BR>
     * �P�j�ȉ��̏����Ńt�B���^�����O����B<BR>
     * <BR>
     * �@@�E������ʁ����M�抷<BR>
     * <BR>
     * �Q�j�����ɓK�����������̃��X�g��Ԃ��B<BR>
     * <BR>
     * @@param l_mutualFundOrders - (�����M�������ꗗ)
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     */
    public List getMutualFundOrdersCapitalGainTax(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundOrdersCapitalGainTax(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)";
        
        List l_list = new ArrayList();
        for(int i = 0; i < l_mutualFundOrders.size(); i++)
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_mutualFundOrders.get(i);
            OrderTypeEnum l_orderType = l_row.getOrderType();
            
            //���M�抷�����̂ݎ擾����
            if(OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
            {
                l_list.add(l_row);
            }
        }
        return l_list;
    }
    
    /**
     * (get�����M���O��S��<�����>)<BR>
     *�@@����.���M�����ꗗ���瓊���M���O��S�����̌v��ΏۂƂȂ钍���������o����B<BR>
     *<BR>
     * �P�j�ȉ��̏����Ńt�B���^�����O����B<BR>
     * <BR>
     * �@@�E������ʁ����M���t�@@OR�@@���M��W�@@OR�@@���M�抷<BR>
     * <BR>
     * �Q�j�����ɓK�����������̃��X�g��Ԃ��B<BR>
     * <BR>
     * @@param l_mutualFundOrders - (�����M�������ꗗ)
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     */
    public List getMutualFundAdvanceRestraintUnExecuted(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundAdvanceRestraintUnExecuted(List l_mutualFundOrders, WEB3TPAssetValuation l_assetValuation)";
        
        List l_list = new ArrayList();
        for(int i = 0; i < l_mutualFundOrders.size(); i++)
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_mutualFundOrders.get(i);
            OrderTypeEnum l_orderType = l_row.getOrderType();
            
            //���M���t�A���M��W�A���M�抷�����̂ݎ擾����
            if(OrderTypeEnum.MF_BUY.equals(l_orderType)
                    || OrderTypeEnum.MF_RECRUIT.equals(l_orderType)
                    || OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
            {
                l_list.add(l_row);
            }
        }
        return l_list;
    }
    
    /**
     * (get�����M���O��S��<����>)<BR>
     *�@@�m�������׃e�[�u�����瓊���M���O��S�����̌v��ΏۂƂȂ钍�����擾����B<BR>
     *<BR>
     * �P�j �m�������׃e�[�u�����ȉ��̏����Ō����B<BR>
     * <BR>
     * �����F<BR>
     * �E�ڋqID �� �ڋq����.get�ڋqID()<BR>
     * �E�⏕����ID IN (�ڋq����.get�⏕����ID�i�⏕�����^�C�v(*)�j)<BR>
     * �E�g�����U�N�V�����^�C�v IN (�����M�����t�A�����M����W)<BR>
     * �E�c�Ɠ�[0]����n��<BR>
     * �E�폜�t���O��false
     * <BR>
     * (*)�⏕�����^�C�v�ˌ����q�F�⏕�����^�C�v.����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ːM�p�q�F�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p<BR>
     * <BR>
     * �Q�j�@@�������ꂽ�m�������ׂ̃��X�g��Ԃ�<BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     */
    public List getMutualFundAdvanceRestraintFixed(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getMutualFundAdvanceRestraintFixed(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_accountInfo.getAccountId()));
            
            //���o�����@@�⏕����ID�̎擾
            //�⏕�����^�C�v�E�E�E�����q�F�⏕�����^�C�v.����
            //                   �E�E�E�M�p�q�F�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j
            //�����q�̏ꍇ
            if (!l_accountInfo.isMarginCustomer())
            {
                l_strWhereBuf.append("sub_account_id = ? AND ");
                long l_subAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //�M�p�q�̏ꍇ
            else
            {
                l_strWhereBuf.append("sub_account_id in (?, ?) AND ");
                long l_equitySubAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_equitySubAccountId));
                long l_marginSubAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_marginSubAccountId));
                
            }
            
            l_strWhereBuf.append("fin_transaction_type IN(?,?) AND ");
            l_lisBindVars.add(FinTransactionType.EQTYPE_MF_BUY);
            l_lisBindVars.add(FinTransactionType.EQTYPE_MF_RECRUIT);
            
            l_strWhereBuf.append("delivery_date > ? AND ");
            l_lisBindVars.add(l_datT0);
            
            l_strWhereBuf.append("delete_flag = ?");
            l_lisBindVars.add(BooleanEnum.FALSE);
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows =
                l_qp.doFindAllQuery(
                        FixedFinTransactionRow.TYPE,
                        l_strWhere,
                        null,
                        l_lisBindVars.toArray());
            
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get���M����)<BR>
     *�@@���M�������擾����B<BR>
     *<BR>
     * �P�j��������<BR>
     * <BR>
     * ���M�����}�X�^�[�e�[�u�������������B<BR>
     * <BR>
     * �����F<BR>
     * ����ID �� ����.����ID<BR>
     * <BR>
     * �Q�j�������ꂽ���M������Ԃ�<BR>
     * @@param l_lngProductId - (����ID)
     * @@return MutualFundProductRow
     */
    public MutualFundProductRow getMutualFundProduct(long l_lngProductId)
    {
        final String STR_METHOD_NAME =
            "getMutualFundProduct(long l_lngProductId)";
        
        MutualFundProductRow l_row = null;
        
        try
        {
            log.debug("mutual_fund_product table : product_id=" + l_lngProductId);
            
            l_row = MutualFundProductDao.findRowByPk(l_lngProductId);
        }
        catch (DataFindException dfe)
        {
            l_row = null;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
        return l_row;
    }
    
    /**
     * (get�בփ��[�g)<BR>
     *�@@�בփ��[�g���擾����B<BR>
     *<BR>
     * �P�j�בփ��[�g����<BR>
     * <BR>
     * �בփ��[�g�e�[�u����茟���B<BR>
     * <BR>
     * �����F<BR>
     * �،���ЃR�[�h �� ����.�،���ЃR�[�h<BR>
     * �ʉ݃R�[�h �@@�@@�� ����.�ʉ݃R�[�h<BR>
     * <BR>
     * �Q�j�������ꂽ�בփ��[�g��Ԃ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)
     * @@return ExchangeRateRow
     */
    public ExchangeRateRow getExchangeRate(String l_strInstitutionCode, String l_strCurrencyCode)
    {
        final String STR_METHOD_NAME =
            "getExchangeRate(String l_strInstitutionCode, String l_strCurrencyCode)";
        
        ExchangeRateRow l_row = null;
        
        try
        {
            log.debug("exchange_rate table : institution_code=" + l_strInstitutionCode + " currency_code=" + l_strCurrencyCode);
            
            l_row = ExchangeRateDao.findRowByPk(l_strInstitutionCode, l_strCurrencyCode);
        }
        catch (DataFindException dfe)
        {
            l_row = null;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
        return l_row;
    }
    
    /**
     * (get�����ȍ~�I�v�V��������)<BR>
     *�@@�����ȍ~�I�v�V���������i�I�v�V���������P�ʂ̃��X�g�j���擾����B<BR>
     *<BR>
     * �P�j�@@��������<BR>
     * <BR>
     * �敨�I�v�V���������P�ʃe�[�u�����<BR>
     * <BR>
     * �������ϐV�K����<BR>
     * �������ϔ����ԍ�<BR>
     * ���������V�K����<BR>
     * ���������V�K����<BR>
     * <BR>
     * �������B<BR>
     * <BR>
     * �����F<BR>
     * ����ID     ��   �ڋq����.get����ID()<BR>
     * �⏕����ID   ��   �ڋq����.get�⏕����ID�i�⏕�����^�C�v.�����j<BR>
     * �����^�C�v    ��   �敨�I�v�V����<BR>
     * ������     IN  �I�v�V�����V�K�����A�I�v�V�������ԍ�<BR>
     * ������      ����  ����<BR>
     * �������     NOT IN  �����<BR>
     * (<BR>
     * ������ ��   �I�v�V�����V�K����<BR>
     * AND<BR>
     * (�����L�����  ��   �I�[�v��<BR>
     * OR<BR>
     * ��萔�� ���O<BR>
     * ))<BR>
     * AND<BR>
     * (<BR>
     * ������ ��   �I�v�V�������ԍ�<BR>
     *      AND<BR>
     * ��萔�� ���O<BR>
     * )<BR>
     * <BR>
     * �Q�j�@@�������ꂽ�I�v�V���������̃��X�g��Ԃ�<BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D37503B3
     */
    public List getTodaysOptionOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysOptionOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            
            long l_accountId = l_accountInfo.getAccountId();
            
            long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("product_type = ? AND ");
            l_strWhereBuf.append("((order_type = ?  AND ");
            l_strWhereBuf.append("(order_open_status = ? OR ");
            l_strWhereBuf.append("executed_quantity > ?)) OR ");
            l_strWhereBuf.append("(order_type = ?  AND ");
            l_strWhereBuf.append("executed_quantity > ?)) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ?");
            
            String l_strWhere = l_strWhereBuf.toString();
            
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    new Long(l_subAccountId),
                    ProductTypeEnum.IFO,
                    OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN,
                    OrderOpenStatusEnum.OPEN,
                    new Double(0),
                    //OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE,
                    OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE,
                    new Double(0),
                    l_datT0
            };
            
            log.debug(l_strWhere + l_bindVars);
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(IfoOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                //�s��m�F�ϒl�g�p�Ή�
                IfoOrderUnitRow l_params = (IfoOrderUnitRow) l_lisRows.get(i);
                l_ht.put(new Long(l_params.getOrderUnitId()), new WEB3TPIfoOrderUnitRowWrapper(l_params));
            }
            
            //�V�K�������e����荞��
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {
                    //�I�v�V���������̏ꍇ�̂݁i�I�v�V�������ԍς͖��ς̂ݎ�荞�ނ��ߐV�K�������e�͂Ƃ肱�܂Ȃ��j
                    if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_newOrderSpecs[i].
                            getOrderType()))
                    {
                        //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                        IfoOrderUnitRow l_oldParams = (IfoOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        IfoOrderUnitParams l_params = null;
                        
                        //�Â�Params�ɐV���������㏑������		        	
                        if (l_oldParams != null)
                        {	
                            //��Params����R�s�[Params�쐬
                            l_params = new IfoOrderUnitParams(l_oldParams);
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                    getEstimatedPrice());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            
                            //��Params���폜
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                        }
                        else
                        {
                            l_params = new IfoOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                    getEstimatedPrice());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            
                        }
                        
                        //������or�V����Params��ǉ�
                        if(l_params.getEstimatedPrice() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()),
                                    new WEB3TPIfoOrderUnitRowWrapper(l_params));
                        }
                        else
                        {
                            log.debug("�T�Z��n���z��0�̐V�K�������e=" + l_newOrderSpecs[i]);
                        }
                    }
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get�����ȍ~GP����)<BR>
     *�@@�����ȍ~GP�����i�ݓ������P�ʂ̃��X�g�j���擾����B<BR>
     *<BR>
     * �P�j�@@��������<BR>
     * <BR>
     * �ݓ������P�ʃe�[�u����蒍�������B<BR>
     * <BR>
     * �������ϗݓ��i���A���i���j�j<BR>
     * �������B<BR>
     * <BR>
     * �����F<BR>
     * ����D      ��   �ڋq����.get����ID()<BR>
     * �⏕����ID   ��   �ڋq����.get�⏕����ID�i�⏕�����^�C�v.�����j<BR>
     * �����^�C�v    ��   �ݓ�<BR>
     * ������     ��   �ݓ����A�ݓ����i�ݓ����j<BR>
     * �R�[�X      ��   ���t�@@���AMMF<BR>
     * ������ԁ@@        NOT IN  ������A�����<BR>
     * (��n���@@�@@�@@�@@��   �،���������<BR>
     * OR<BR>
     * ��n���@@�@@�@@�@@��   NULL)<BR>
     * ������      �����@@����<BR>
     * (<BR>
     * ������     ��   �ݓ���<BR>
     * AND<BR>
     * ��n��      ����  ����<BR>
     * )<BR>
     * OR<BR>
     * (<BR>
     * ������     ��   �ݓ���<BR>
     * AND<BR>
     * ��n��      ����  ����<BR>
     * )<BR>
     * <BR>
     * �Q�j�@@�������ꂽGP�����̃��X�g��Ԃ�<BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D37503D2
     */
    public List getTodaysGPOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysGPOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            
            long l_accountId = l_accountInfo.getAccountId();
            long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(0);
            Date l_datT1 = l_assetValuation.getCalcCondition().getBizDate(1);
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("product_type = ? AND ");
            l_strWhereBuf.append("ruito_type in (?, ?) AND ");
            l_strWhereBuf.append("order_open_status = ? AND ");
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_strWhereBuf.append(
            "((payment_method = ?) OR (payment_method IS NULL)) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ?");
            
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    new Long(l_subAccountId),
                    ProductTypeEnum.RUITO,
                    RuitoTypeEnum.CHUUKOKU_FUND,
                    RuitoTypeEnum.MMF,
                    OrderOpenStatusEnum.OPEN,
                    OrderStatusEnum.CANCELLING,
                    OrderStatusEnum.CANCELLED,
                    WEB3PaymentMethodDef.SECURITIES_ACCOUNT_INPUT,
                    l_datT0,
            };
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(RuitoOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                RuitoOrderUnitParams l_params = (RuitoOrderUnitParams) l_lisRows.get(
                        i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            
            //�V�K�������e����荞��
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {
                    //���̏ꍇ�͎�n���������ȍ~�A����̏ꍇ�͎�n���������ȍ~
                    if ( ( (OrderTypeEnum.RUITO_BUY.equals(l_newOrderSpecs[i].
                            getOrderType()) &&
                            (l_datT0.compareTo(l_newOrderSpecs[i].getDeliveryDate()) <= 0))) ||
                            (OrderTypeEnum.RUITO_SELL.equals(l_newOrderSpecs[i].getOrderType()) &&
                                    (l_datT1.compareTo(l_newOrderSpecs[i].getDeliveryDate()) <= 0)))
                    {
                        //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                        RuitoOrderUnitRow l_oldParams = (RuitoOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        RuitoOrderUnitParams l_params = null;
                        
                        //�Â�Params�ɐV���������㏑������		        	
                        if (l_oldParams != null)
                        {	
                            //��Params����R�s�[Params�쐬
                            l_params = new RuitoOrderUnitParams(l_oldParams);
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            
                            //��Params���폜
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                        }
                        else
                        {
                            l_params = new RuitoOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());    			
                        }
                        
                        //������or�V����Params��ǉ�
                        if(l_params.getQuantity() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                        }
                        else
                        {
                            log.debug("�T�Z��n���z��0�̐V�K�������e=" + l_newOrderSpecs[i]);
                        }
                    }
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�����ȍ~�~�j������)<BR>
     *�@@�����ȍ~�~�j�������i���������P�ʂ̃��X�g�j���擾����B<BR>
     *<BR>
     * �P�j��������<BR>
     * <BR>
     * ���������P�ʃe�[�u����蒍������<BR>
     * <BR>
     * �O�������~�j���i���j<BR>
     * ���������~�j���i���j<BR>
     * ���������~�j���i���j<BR>
     * �������B<BR>
     * <BR>
     * �����F<BR>
     * ����ID     ��   �ڋq����.get����ID()<BR>
     * �⏕����ID   ��   �ڋq����.get�⏕����ID�i�⏕�����^�C�v.�����j<BR>
     * �������     NOT IN  �����<BR>
     * ((������     ��   �~�j���� AND<BR>
     *�@@(�����L����ԁ@@���@@�I�[�v�� OR�@@
     * (�����L����ԁ@@���N���[�Y AND �������ʁ���萔��)))
     *  OR
     * (������     ��   �~�j���� AND<BR>
     * (�����L����ԁ@@���N���[�Y AND �������ʁ���萔��)))     *
     * ������      ����  ����<BR>
     * <BR>
     * �Q�j�������ꂽ�~�j�������i���������Ɠ����^�j�̔z���Ԃ�<BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D37503E2
     */
    public List getTodaysMiniStockOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysMiniStockOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                EQUITY_SUB_ACCOUNT);
        
        List l_lisRows = l_assetValuation.getTodaysEquityOrders();
        
        //�t�B���^����
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.get(i);
            if ((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (!(OrderStatusEnum.CANCELLED.equals(l_params.getOrderStatus())))
                    &&
                    (((OrderTypeEnum.MINI_STOCK_BUY.equals(l_params.getOrderType()))
                            &&
                            ((OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()))
                                    || 
                                    (OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus())
                                            &&
                                            ((l_params.getQuantity() == l_params.getExecutedQuantity()) 
                                                    &&
                                                    (l_params.getExecutedQuantity() > 0))
                                    )
                            )
                    )                     
                    || 
                    ((OrderTypeEnum.MINI_STOCK_SELL.equals(l_params.getOrderType())) 
                            && 
                            ((OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus()))
                                    &&
                                    ((l_params.getQuantity() == l_params.getExecutedQuantity()) 
                                            &&
                                            (l_params.getExecutedQuantity() > 0))
                            )
                    )
                    )
            )
            {
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        //�V�K�������e����荞��
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_newOrderSpecs[i].
                        getOrderType()))
                {
                    
                    //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                    EqtypeOrderUnitRow l_oldParams = (EqtypeOrderUnitRow)
                    l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //�Â�Params�ɐV���������㏑������		        	
                    if (l_oldParams != null)
                    {	
                        //��Params����R�s�[Params�쐬
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        
                        //��Params���폜
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));			        	
                        
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());				        			
                    }
                    
                    //������or�V����Params��ǉ�
                    if(l_params.getEstimatedPrice() != 0.0d)
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()),
                                new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
                    }
                    else
                    {
                        log.debug("�T�Z��n���z��0�̐V�K�������e=" + l_newOrderSpecs[i]);
                    }
                }
            }
        }
        
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get�����ȍ~�O������)<BR>
     * �����ȍ~�O�������i�O�������P�ʂ̃��X�g�j���擾����B<BR>
     * <BR>
     * �����F<BR>
     * ����ID �@@�@@�@@�@@�@@= �ڋq����.get����ID()<BR>
     * �⏕����ID �@@�@@�@@�@@�@@= �ڋq����.get�⏕����ID(�⏕�����^�C�v.����)<BR>
     * ���ϋ敪 �@@�@@�@@�@@�@@= �~��<BR>
     * ���f�[�^�t�@@�C�����M�t���O�@@!= �@@�@@�q�����f��("2�F�o�b�`�X�V��")<BR>
     * (������ �@@�@@�@@�@@�@@= �O����<BR>
     * AND<BR>
     * (�����L����� �@@�@@�@@�@@�@@ = �I�[�v��<BR>
     * OR<BR>
     * ��萔�� �@@�@@�@@�@@�@@ > 0))<BR>
     * OR<BR>
     * �����������ȉ������ǉ��|�|�|<BR>
     * �@@�@@�w�O�����v�����̗p(���P)�̏ꍇ�x<BR>
     * �@@�@@ ( ������ = �O���� AND<BR>
     * �@@�@@�@@(�����L����� = �I�[�v�� OR ��萔�� > 0�jAND<BR>
     * �@@�@@�@@�����t���O <> 1�F�����)<BR>
     * <BR>
     * �@@�@@�w�i���P�ȊO�j�O�����v�������̗p�̏ꍇ�x<BR>
     * �@@�@@(�@@������ = �O���� AND<BR>
     * �@@�@@�@@��萔�� > 0 AND<BR>
     * �@@�@@�@@�o���I���������� IS NOT NULL)<BR>
     * <BR>
     * (���P)����.���Y�]��.get�]�͌v�Z����().is�O�����v�����̗p()��,true�̏ꍇ<BR>
     * <BR>
     * �Q�D����.���Y�]��.���������e !=null�̏ꍇ,<BR>
     * ���������e�̒��A�O��������(�������=�O���� <BR>
     * �@@������ �������=�O����)���O�������P�ʃe�[�u���s�I�u�W�F�N�g�֕ϊ�����B<BR>
     * <BR>
     * �R�D�@@�P�D�ƂQ�D�Ŏ擾�������������P�ʃe�[�u���s�I�u�W�F�N�g�������Ƃ���<BR>
     * �]�͊O�������P��Wrapper�𐶐������X�g�ɒǉ�����B<BR>
     * �i�O�������P��Wrapper��get��n���()�Ŏs��m�F�ς̒l�Ɣ�r���Ó��Ȓl��ԋp����)<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3760019
     */
    public List getTodaysFeqOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysFeqOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("settle_div = ? AND ");
            l_strWhereBuf.append("((exec_file_send_flag IS NULL) OR (exec_file_send_flag != ?)) AND ");
            l_strWhereBuf.append("((order_type = ? AND ");
            l_strWhereBuf.append("(order_open_status = ? OR executed_quantity > ?)) OR");
            //����.���Y�]��.get�]�͌v�Z����().is�O�����v�����̗p()��,true�̏ꍇ
            boolean l_blnIsFeqDayTradeAdoption =
                l_assetValuation.getCalcCondition().isFeqDayTradeAdoption();
            if (l_blnIsFeqDayTradeAdoption)
            {
                //( ������ = �O���� AND
                //(�����L����� = �I�[�v�� OR ��萔�� > 0�jAND
                //�����t���O <> 1�F�����)
                l_strWhereBuf.append(" (order_type = ? AND ");
                l_strWhereBuf.append(
                    "(order_open_status = ? OR executed_quantity > ?) and temporary_execution_flag <> ?))");
            }
            else
            {
                //(�@@������ = �O���� AND
                //��萔�� > 0 AND
                //�o���I���������� IS NOT NULL)
                l_strWhereBuf.append(
                    "(order_type = ? AND (executed_quantity > ?) AND exec_end_timestamp IS NOT NULL))");
            }
            String l_strWhere = l_strWhereBuf.toString();
            
            long l_accountId = l_accountInfo.getAccountId();
            long l_subAccountId;
            //�����q�̏ꍇ
            if (!l_accountInfo.isMarginCustomer())
            {
                l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
            }
            //�M�p�q�̏ꍇ
            else
            {
                l_subAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            
            Object[] l_bindVars = null;
            if (l_blnIsFeqDayTradeAdoption)
            {
                l_bindVars =new Object[]{
                    new Long(l_accountId),
                    new Long(l_subAccountId),
                    WEB3TPFeqSettlementDivDef.JAPANESE_CURRENCY,
                    WEB3TPFeqExecFileSendStatusDivDef.PROCESSED,
                    OrderTypeEnum.FEQ_BUY,
                    OrderOpenStatusEnum.OPEN,
                    new Double(0),
                    OrderTypeEnum.FEQ_SELL,
                    OrderOpenStatusEnum.OPEN,
                    new Double(0),
                    WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC};
            }
            else
            {
                l_bindVars =new Object[]{
                    new Long(l_accountId),
                    new Long(l_subAccountId),
                    WEB3TPFeqSettlementDivDef.JAPANESE_CURRENCY,
                    WEB3TPFeqExecFileSendStatusDivDef.PROCESSED,
                    OrderTypeEnum.FEQ_BUY,
                    OrderOpenStatusEnum.OPEN,
                    new Double(0),
                    OrderTypeEnum.FEQ_SELL,
                    new Double(0)};
            }
            
            
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(FeqOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            double l_dblPreRestRate = this.getPremiumRestraintRate(
                    WEB3CommisionProductCodeDef.FOREIGN_EQITY,
                    l_assetValuation);                            
            
            if(log.ison())
            {
                log.debug("l_strWhere" + l_strWhere);
                for(int i = 0; i < l_bindVars.length; i++)
                {
                    log.debug("l_bindVars[" + i + "]=" + l_bindVars[i]);                    
                }
                log.debug("select���ꂽ�O�������P�ʐ�=" + l_lisRows.size());
            }
            
            
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                FeqOrderUnitRow l_params = (FeqOrderUnitRow)l_lisRows.get(i);
                FeqOrderUnitRow l_wrapper = new WEB3TPFeqOrderUnitRowWrapper(l_params);
                l_ht.put(new Long(l_wrapper.getOrderUnitId()), l_wrapper);
            }
            
            //�V�K�������e����荞��
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {                    
                    //�������=�O���� ������ �������=�O�����̂ݐV�K�������e����荞��
                    if (OrderTypeEnum.FEQ_BUY.equals(l_newOrderSpecs[i].getOrderType())
                        || OrderTypeEnum.FEQ_SELL.equals(l_newOrderSpecs[i].getOrderType()))
                    {
                        FeqOrderUnitRow l_oldParams = (FeqOrderUnitRow)l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));                    
                        FeqOrderUnitParams l_params = null;
                        
                        //���������݂����ꍇ
                        if(l_oldParams != null)
                        {
                            l_params = new FeqOrderUnitParams(l_oldParams);
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                    getEstimatedPrice());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            
                            //��Params���폜
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                            log.debug("�����������e����荞��=" + l_params);
                            
                        }                    
                        else
                        {
                            l_params = new FeqOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            
                            l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
                            l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                            
                        }                        
                        
                        //������or�V����Params��ǉ�
                        if(l_params.getEstimatedPrice() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()),
                                    new WEB3TPFeqOrderUnitRowWrapper(l_params));
                            
                            log.debug("�V�K�������e����荞��=" + l_params);
                            
                        }
                        else
                        {
                            log.debug("�T�Z��n���z��0�̐V�K�������e=" + l_newOrderSpecs[i]);
                        }	                    	                    
                    }                    
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get�����ȍ~���o������)<BR>
     *�@@�����ȍ~���o�������i���o���U�֒����P�ʂ̃��X�g�j���擾����B<BR>
     *<BR>
     * �P�j�@@��������<BR>
     * <BR>
     * ���o���U�֒����P�ʃe�[�u����蒍������<BR>
     * <BR>
     * �����F<BR>
     * ����ID     ��   �ڋq����.get����ID()<BR>
     * �⏕����ID���ڋq����.get�⏕����ID�i�⏕�����^�C�v.�����j<BR>
     * ������      IN     (��������,  �o������)<BR>
     * ������� NOT IN   (�������s, �����)<BR>
     * ������      ����  ����<BR>
     * 
     * �Q�j�@@�������ꂽ���o�������̃��X�g��Ԃ�<BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3760029
     */
    public List getTodaysCashInOutOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCashInOutOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {        	
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_type in (?,?) AND ");
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ?");
            
            long l_accountId = l_accountInfo.getAccountId();
            //���o���͗a��������̂�
            long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            l_lisBindVars.add(0, new Long(l_accountId));
            l_lisBindVars.add(1, new Long(l_subAccountId));
            l_lisBindVars.add(2, OrderTypeEnum.CASH_IN);
            l_lisBindVars.add(3, OrderTypeEnum.CASH_OUT);            
            l_lisBindVars.add(4, OrderStatusEnum.NOT_ORDERED);
            l_lisBindVars.add(5, OrderStatusEnum.CANCELLED);
            l_lisBindVars.add(6, l_datT0);            
                        
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars = l_lisBindVars.toArray();            
            
            log.debug("l_strWhere=" + l_strWhere);
            for(int i = 0 ; i < l_bindVars.length; i++)
            {
                log.debug("l_bindVars=[" + i + "]");
                log.debug(l_bindVars[i].toString());                
            }
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(AioOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                AioOrderUnitParams l_params = (AioOrderUnitParams) l_lisRows.get(
                        i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            
            //�V�K�������e����荞��
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {
                    if (OrderTypeEnum.CASH_IN.equals(l_newOrderSpecs[i].getOrderType()) ||
                            (OrderTypeEnum.CASH_OUT.equals(l_newOrderSpecs[i].getOrderType())))
                    {
                        //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                        AioOrderUnitRow l_oldParams = (AioOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        AioOrderUnitParams l_params = null;
                        
                        //�Â�Params�ɐV���������㏑������		        	
                        if (l_oldParams != null)
                        {	
                            //��Params����R�s�[Params�쐬
                            l_params = new AioOrderUnitParams(l_oldParams);
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            //�L�����敪
                            l_params.setPaymentApplicationDiv(l_newOrderSpecs[i].getPaymentApplicationDiv());
                            //�󒍓���
                            l_params.setReceivedDateTime(l_newOrderSpecs[i].getReceivedDateTime());
                            
                            //��Params���폜
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                            log.debug("�폜�����Â������P�ʁF" + l_oldParams);
                            
                        }
                        else
                        {
                            l_params = new AioOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_subAccountId);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            //�L�����敪
                            l_params.setPaymentApplicationDiv(l_newOrderSpecs[i].getPaymentApplicationDiv());
                            //�󒍓���
                            l_params.setReceivedDateTime(l_newOrderSpecs[i].getReceivedDateTime());
                            
                        }
                        
                        //������or�V����Params��ǉ�
                        if(l_params.getQuantity() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                            log.debug("�ǉ����������P�ʁF" + l_params);
                        }
                        else
                        {
                            log.debug("�T�Z��n���z��0�̐V�K�������e=" + l_newOrderSpecs[i]);
                        }
                    }
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�����ȍ~�U�֒���) <BR>
     * <BR>
     * ���[�h�ΏۂƂȂ�U�֒������AAIO�����P�ʃe�[�u����茟�����A <BR>
     * �s�I�u�W�F�N�g�̃��X�g��ԋp����B <BR>
     * <BR>
     * ���V�[�P���X�}�u(�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�)get�����ȍ~�U�֒����v�Q�� <BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3760029
     */
    public List getTodaysCashTransferOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCashTransferOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            //�����h�c
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            long l_accountId = l_accountInfo.getAccountId();
            l_lisBindVars.add(new Long(l_accountId));
            
            //���o�����@@�⏕����ID�̎擾
            //�⏕�����^�C�v�E�E�E�����q�F�⏕�����^�C�v.����
            //                   �E�E�E�M�p�q�F�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p
            long l_defaultSubAccountId;
            //�����q�̏ꍇ
            if (!l_accountInfo.isMarginCustomer())
            {
                l_strWhereBuf.append("sub_account_id = ? AND ");
                long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
                l_defaultSubAccountId = l_subAccountId;
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //�M�p�q�̏ꍇ
            else
            {
                l_strWhereBuf.append("sub_account_id in (?, ?) AND ");
                long l_equitySubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_equitySubAccountId));
                long l_marginSubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_defaultSubAccountId = l_marginSubAccountId;
                l_lisBindVars.add(new Long(l_marginSubAccountId));
            }
            //������� not in (�������s, �����) 
            l_strWhereBuf.append("order_status not in (?, ?) AND ");
            l_lisBindVars.add(OrderStatusEnum.NOT_ORDERED);
            l_lisBindVars.add(OrderStatusEnum.CANCELLED);
            
            //�������@@T+0�ȍ~
            l_strWhereBuf.append("to_date(biz_date, 'YYYYMMDD') >= ?");
            l_lisBindVars.add(l_datT0);
            
            String l_strWhere = l_strWhereBuf.toString();            
            Object[] l_bindVars = l_lisBindVars.toArray();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(AioOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            //���o�ΏۂƂȂ�
            //            ������ʁF
            //			1005�F�U�֒���(�a�������M�p�ۏ؋�)�@@�@@�@@
            //			1006�F�U�֒���(�M�p�ۏ؋�����a���)�@@�@@�@@
            //			1007�F�U�֒���(�a������犔��؋���)
            //			1008�F�U�֒���(����؋�������a���)
            //			1011�F�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�@@���ǉ�
            //			1012�F�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�@@���ǉ�
            //			1013�F�O�������U�֒����i�a�������O�����������j�@@���ǉ�
            //			1014�F�O�������U�֒����i�O��������������a����j�@@���ǉ�
            //			1017�F���̑��U�֒����i�a�������X�j�@@�@@�@@�@@�@@�@@�@@ ���ǉ�
            //			1018�F���̑��U�֒����iX����a����j�@@�@@�@@�@@�@@�@@�@@ ���ǉ�
            //        �@@1019�F�U�֒����i�a��������؋��j�@@             ���ǉ�
            //        �@@1020: �U�֒����i�a�������I���b�N�X�N���W�b�g�j  ���ǉ�
            //          1021�FCFD�U�֒����i�a�������CFD�����j
            //          1022�FCFD�U�֒����iCFD��������a����j
            List orderTypeList = new ArrayList();
            orderTypeList.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            orderTypeList.add(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            orderTypeList.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            orderTypeList.add(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
            orderTypeList.add(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            orderTypeList.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            orderTypeList.add(OrderTypeEnum.TRANSFER_TO_FEQ);
            orderTypeList.add(OrderTypeEnum.TRANSFER_FROM_FEQ);
            orderTypeList.add(OrderTypeEnum.TO_OTHER_TRANSFER);
            orderTypeList.add(OrderTypeEnum.FROM_OTHER_TRANSFER);
            orderTypeList.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK);
            orderTypeList.add(OrderTypeEnum.TO_ORIX_CREDIT);
            orderTypeList.add(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
            orderTypeList.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
            
            TreeMap l_ht = new TreeMap();
            for(int i = 0; i < l_lisRows.size(); i++)
            {
                AioOrderUnitRow l_params = (AioOrderUnitRow)l_lisRows.get(i);
                log.debug("�c�a���猟���������o������[" + i + "]" + l_params );
                if(orderTypeList.contains(l_params.getOrderType()))
                {            	    
                    l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                    log.debug("���o���������U�֒���=" + l_params);
                }
                
                
            }
            log.debug("�U�֓��o��������=" + l_lisRows.size() + "�@@�F�@@�t�B���^�������o����������=" + l_ht.size());            	
            
            //�V�K�������e����荞��
            //�Ώۂ��a����̃T�u�A�J�E���g�̏ꍇ�����V�K�������e���n�����O��
            WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
            if (l_newOrderSpecs != null)
            {
                for (int i = 0; i < l_newOrderSpecs.length; i++)
                {                	
                    if(orderTypeList.contains(l_newOrderSpecs[i].getOrderType()))
                    {                    	
                        //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                        AioOrderUnitRow l_oldParams = (AioOrderUnitRow)
                        l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                        
                        AioOrderUnitParams l_params = null;
                        
                        //�Â�Params�ɐV���������㏑������		        	
                        if (l_oldParams != null)
                        {	
                            //��Params����R�s�[Params�쐬
                            l_params = new AioOrderUnitParams(l_oldParams);
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            l_params.setPaymentApplicationDiv(l_newOrderSpecs[i].getPaymentApplicationDiv());
                            l_params.setReceivedDateTime(l_newOrderSpecs[i].getReceivedDateTime());
                            
                            //��Params���폜
                            l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                            
                            log.debug("���������e[����/���] ��=" + l_oldParams + " �V=" + l_params);
                            
                        }
                        else
                        {
                            l_params = new AioOrderUnitParams();
                            l_params.setAccountId(l_accountId);
                            l_params.setSubAccountId(l_newOrderSpecs[i].getSubAccountId());
                            l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                            l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                            l_params.setProductType(l_newOrderSpecs[i].getProductType());
                            l_params.setProductId(l_newOrderSpecs[i].getProductId());
                            l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                            l_params.setBizDate(WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].
                                    getOrderBizDate(), "yyyyMMdd"));
                            l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                            l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                            l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                            l_params.setPaymentApplicationDiv(l_newOrderSpecs[i].getPaymentApplicationDiv());
                            l_params.setReceivedDateTime(l_newOrderSpecs[i].getReceivedDateTime());
                            
                            
                            AssetTransferTypeEnum l_transferType;
                            if(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_newOrderSpecs[i].getOrderType()) ||    
                                    OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_newOrderSpecs[i].getOrderType()) ||	                               
                                    OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_newOrderSpecs[i].getOrderType()) ||    
                                    OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.TO_ORIX_CREDIT.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_newOrderSpecs[i].getOrderType())
                            )
                            {
                                l_transferType = AssetTransferTypeEnum.CASH_OUT;
                            }
                            else if(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_newOrderSpecs[i].getOrderType()) ||    
                                    OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_newOrderSpecs[i].getOrderType()) ||    
                                    OrderTypeEnum.TRANSFER_FROM_FEQ.equals(l_newOrderSpecs[i].getOrderType()) ||	                               
                                    OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_newOrderSpecs[i].getOrderType()) ||
                                    OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(l_newOrderSpecs[i].getOrderType())
                            )	                               
                            {
                                l_transferType = AssetTransferTypeEnum.CASH_IN;		                            
                            }
                            else
                            {
                                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);        		                            
                            }
                            
                            //���ۂ͐V�K�������e�Ŏ�荞�ނ̂͏o������
                            l_params.setTransferType(l_transferType);		                        
                            
                            log.debug("���������e[�V�K]=" + l_params);
                        }
                        
                        //������or�V����Params��ǉ�
                        if(l_params.getQuantity() != 0.0d)
                        {
                            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                        }
                        else
                        {
                            log.debug("�T�Z��n���z��0�̐V�K�������e=" + l_newOrderSpecs[i]);
                        }
                    }
                }
            }
            
            return new ArrayList(l_ht.values());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get�����ȍ~IPO����)<BR>
     *�@@�����ȍ~IPO�����iIPO�����P�ʂ̃��X�g�j���擾����B<BR>
     * <BR>
     * �P�j�@@IPO�\���e�[�u�����ȉ��̏����Œ��������B<BR>
     * �����F<BR>
     * ����ID             ��   �@@�ڋq����.get�ڋqID()<BR>
     * �⏕�ڋqID       ���@@�ڋq����.get�⏕����ID�i�⏕�����^�C�v.�����j<BR>
     * IPO�\���L�����        ��   �I�[�v��<BR>
     * IPO�w���\���敪        !��   ����<BR>
     * (���I����         IN  ���I)<BR>
     * OR<BR>
     * (���I����         IN  �⌇<BR>
     * ���I���ʁi�J��グ�j�@@!���@@���I)<BR>
     * <BR>
     * �Q�j�@@�������ꂽIPO�����̃��X�g��Ԃ��B<BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3760048
     */
    public List getTodaysIPOOrders(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysIPOOrders(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            long l_lngAccountId = l_accountInfo.getAccountId();
            long l_lngSubAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_open_status = ? AND ");
            l_strWhereBuf.append("offering_div != ? AND ");
            l_strWhereBuf.append("(lot_result = ? OR ");
            l_strWhereBuf.append("(lot_result = ? AND  ");
            l_strWhereBuf.append("lot_result_retry != ?))");
            
            String l_strWhere = l_strWhereBuf.toString();
            
            Object[] l_bindVars =
            {
                    new Long(l_lngAccountId),
                    new Long(l_lngSubAccountId),
                    OrderOpenStatusEnum.OPEN,
                    WEB3TPIPOOfferingDivDef.CANCELLED,
                    WEB3TPIPOLotResultTypeDef.WIN,
                    WEB3TPIPOLotResultTypeDef.SUBSTITUTE,
                    WEB3TPIPOLotResultTypeDef.LOSE
            };
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            return l_qp.doFindAllQuery(IpoOrderRow.TYPE, l_strWhere, null, l_bindVars);
            
            //IPO�͊��ɂ��郌�R�[�h�ɑ΂��ė]�͍S����������̂�
            //�����R���ɑ�������V�K�������e�̎�荞�݂͍l�����Ȃ��Ă悢
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get�����ȍ~��������<����������򂠂�>)<BR>
     *�@@�����ȍ~��������<����������򂠂�>�i���������P�ʂ̃��X�g�j���擾����B<BR>
     * <BR>
     * �P�j�@@��������<BR>
     * <BR>
     * ���������P�ʃe�[�u����蒍������<BR>
     * <BR>
     * �����F<BR>
     * ����ID     ��   �ڋq����.����ID<BR>
     * �⏕�ڋqID    IN     �ڋq����.get�⏕����ID�i�⏕�����^�C�v.�M�p�j<BR>
     * �����^�C�v    ��   ����<BR>
     * �������     IN  ���n<BR>
     * �ŋ敪      ��   ����������򒥎�����<BR>
     * ������      ����  ����<BR>
     * <BR>
     * �Q�j�@@�������ꂽ���������̃��X�g��Ԃ�<BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3760067
     */
    public List getTodaysSpecialWithHoldEquityOrders(WEB3TPAssetValuation
            l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysSpecialWithHoldEquityOrders(WEB3TPAssetValuation l_assetValuation)";
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        //�M�p�q�łȂ��ꍇ�v�f0�̃��X�g��Ԃ�
        if (!l_accountInfo.isMarginCustomer())
        {
            return Collections.EMPTY_LIST;
        }
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                EQUITY_MARGIN_SUB_ACCOUNT);
        
        List l_lisRows = l_assetValuation.getTodaysEquityOrders();
        //�t�B���^����
        TreeMap l_ht = new TreeMap();
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams) l_lisRows.
            get(i);
            
            if((l_subAccountId == l_params.getSubAccountId())
                    &&
                    (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_params.getOrderType())) 
                    &&
                    (TaxTypeEnum.SPECIAL.equals(l_params.getTaxType())))
            {
                
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
        }
        
        //�V�K�������e����荞��
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        if (l_newOrderSpecs != null)
        {
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_newOrderSpecs[i].
                        getOrderType()) &&
                        TaxTypeEnum.SPECIAL.equals(l_newOrderSpecs[i].getTaxType()))
                {
                    //�V�K�������e�Ɠ��꒍���P�ʂh�c��Params�����邩�`�F�b�N
                    EqtypeOrderUnitRow l_oldParams = (EqtypeOrderUnitRow)
                    l_ht.get(new Long(l_newOrderSpecs[i].getOrderUnitId()));
                    
                    EqtypeOrderUnitParams l_params = null;
                    
                    //�Â�Params�ɐV���������㏑������		        	
                    if (l_oldParams != null)
                    {	
                        //��Params����R�s�[Params�쐬
                        l_params = new EqtypeOrderUnitParams(l_oldParams);
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        
                        //��Params���폜
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        
                    }
                    else
                    {
                        l_params = new EqtypeOrderUnitParams();
                        l_params.setAccountId(l_accountId);
                        l_params.setSubAccountId(l_subAccountId);
                        l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                        l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                        l_params.setProductType(l_newOrderSpecs[i].getProductType());
                        l_params.setProductId(l_newOrderSpecs[i].getProductId());
                        l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                        l_params.setEstimatedPrice(l_newOrderSpecs[i].
                                getEstimatedPrice());
                        l_params.setBizDate(WEB3DateUtility.formatDate(
                                l_newOrderSpecs[i].
                                getOrderBizDate(), "yyyyMMdd"));
                        l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                        l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                        l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                        l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                        //������Ԓǉ�
                        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
                        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                        
                    }
                    
                    //������or�V����Params��ǉ�
                    if(l_params.getEstimatedPrice() != 0.0d)
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()),
                                new WEB3TPEqtypeOrderUnitRowWrapper(l_params));
                        log.debug("�ǉ������V�K����" + l_params);
                    }
                    else
                    {
                        log.debug("�T�Z��n���z��0�̐V�K�������e=" + l_newOrderSpecs[i]);
                    }                	
                }
            }
        }
        
        return new ArrayList(l_ht.values());
        
    }
    
    /**
     * (get�����g�����U�N�V����<����������򂠂�>)<BR>
     *�@@�����g�����U�N�V����<����������򂠂�>�i�����g�����U�N�V�����̃��X�g�j���擾����B<BR>
     * <BR>
     * �P�j�@@��������<BR>
     * <BR>
     * �����g�����U�N�V�����e�[�u����蒍������<BR>
     * <BR>
     * �����F<BR>
     * ����ID         ��   �ڋq����.get����ID()<BR>
     * �⏕����ID       ��   �ڋq����.get�⏕����ID�i�⏕�����^�C�v�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�⏕�����^�C�v�E�E�E�����q�F�⏕�����^�C�v.����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�M�p�q�F�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p<BR>
     * �����^�C�v        ��   ����<BR>
     * �ŋ敪          ��   ����������򒥎�����<BR>
     * �폜�t���O        ��   FALSE<BR>
     * �g�����U�N�V�����^�C�v      IN  ����������A�����ԍώ���A�����ԍώ���A���n���<BR>
     * �g�����U�N�V����������  ����  ����<BR>
     * <BR>
     * �Q�j�@@�������ꂽ�����g�����U�N�V�����̃��X�g��Ԃ�<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3760077
     */
    public List getTodaysSpecialWithHoldEquityFinTransactions(WEB3TPAssetValuation
            l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysSpecialWithHoldEquityFinTransactions(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_accountInfo.getAccountId()));
            
            //���o�����@@�⏕����ID�̎擾
            //�⏕�����^�C�v�E�E�E�����q�F�⏕�����^�C�v.����
            //                   �E�E�E�M�p�q�F�⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j
            //�����q�̏ꍇ
            l_strWhereBuf.append("sub_account_id = ? AND ");
            if (!l_accountInfo.isMarginCustomer())
            {
                long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //�M�p�q�̏ꍇ
            else
            {
                long l_marginSubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_marginSubAccountId));
            }
            
            l_strWhereBuf.append("delete_flag = ? AND ");
            l_lisBindVars.add(BooleanEnum.FALSE);
            
            l_strWhereBuf.append("fin_transaction_timestamp >= ? AND ");
            l_lisBindVars.add(l_datT0);
            
            l_strWhereBuf.append("tax_type = ? AND ");
            l_lisBindVars.add(TaxTypeEnum.SPECIAL);
            
            
            l_strWhereBuf.append("fin_transaction_type in (?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_EQUITY_SELL);
            l_strWhereBuf.append(", ?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG);
            l_strWhereBuf.append(", ?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT);
            l_strWhereBuf.append(", ?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT);
            l_strWhereBuf.append(")");
            
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars = l_lisBindVars.toArray();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�O���g�����U�N�V����<����������򂠂�>)<BR>
     *�@@�O���g�����U�N�V����<����������򂠂�>�i�O���g�����U�N�V�����̃��X�g�j���擾����B<BR>
     * <BR>
     * �P�j�@@��������<BR>
     * <BR>
     * �O���g�����U�N�V�����e�[�u����蒍������<BR>
     * <BR>
     * �����F<BR>
     * ����ID         ��   �ڋq����.get����ID()<BR>
     * �⏕�ڋqID        IN     �ڋq����.get�⏕����ID�i�⏕�����^�C�v.�����j<BR>
     * �����^�C�v        ��   �O��<BR>
     * �g�����U�N�V�����^�C�v      IN  �O�������(������`)<BR>
     * �ŋ敪          ��   ����������򒥎�����<BR>
     * �폜�t���O              ��   FALSE<BR>
     * �g�����U�N�V����������  ����  ����<BR>
     * <BR>
     * �Q�j�@@�������ꂽ�O���g�����U�N�V�����̃��X�g��Ԃ�<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D3760096
     */
    public List getTodaysSpecialWithHoldFeqFinTranasctions(WEB3TPAssetValuation
            l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysSpecialWithHoldFeqFinTranasctions(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_accountInfo.getAccountId()));
            
            //���o�����@@�⏕����ID�̎擾
            //�⏕�����^�C�v�E�E�E�����q�F�⏕�����^�C�v.����
            //                   �E�E�E�M�p�q�F�⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j
            //�����q�̏ꍇ
            l_strWhereBuf.append("sub_account_id = ? AND ");
            if (!l_accountInfo.isMarginCustomer())
            {
                long l_subAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //�M�p�q�̏ꍇ
            else
            {
                long l_marginSubAccountId = l_accountInfo.getSubAccountId(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_marginSubAccountId));
            }
            
            l_strWhereBuf.append("delete_flag = ? AND ");
            l_lisBindVars.add(BooleanEnum.FALSE);
                        
            l_strWhereBuf.append("tax_type = ? AND ");
            l_lisBindVars.add(TaxTypeEnum.SPECIAL);
            
            
            l_strWhereBuf.append("fin_transaction_type = ?");
            l_lisBindVars.add(FinTransactionType.EQTYPE_FEQ_SELL);
            
            String l_strWhere = l_strWhereBuf.toString();
            Object[] l_bindVars = l_lisBindVars.toArray();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(FeqFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�m��������)<BR>
     *�@@�m�������ׂ̃��X�g���擾����B<BR>
     * <BR>
     * �P�j �m�������׃e�[�u�����m�������ׂ������B<BR>
     * <BR>
     * �����F<BR>
     * �ڋqID �� �ڋq����.get�ڋqID() AND <BR>
     * �⏕����ID IN (�ڋq����.get�⏕����ID�i�⏕�����^�C�v(*)�j) AND <BR>
     * �c�Ɠ�[0]������n�������c�Ɠ�[5]<BR>
     * <BR>
     * (*)�⏕�����^�C�v�ˌ����q�F�⏕�����^�C�v.����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ːM�p�q�F�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p<BR>
     * <BR>
     * �Q�j�@@�������ꂽ�m�������ׂ̃��X�g��Ԃ�<BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D37600B5
     */
    public List getFixedFinTransactions(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getFixedFinTransactions(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            Date l_datT5 = l_calcCondition.getBizDate(5);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_accountInfo.getAccountId()));
            
            //���o�����@@�⏕����ID�̎擾
            //�⏕�����^�C�v�E�E�E�����q�F�⏕�����^�C�v.����
            //                   �E�E�E�M�p�q�F�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j
            //�����q�̏ꍇ
            if (!l_accountInfo.isMarginCustomer())
            {
                l_strWhereBuf.append("sub_account_id = ? AND ");
                long l_subAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            //�M�p�q�̏ꍇ
            else
            {
                l_strWhereBuf.append("sub_account_id in (?, ?) AND ");
                long l_equitySubAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_equitySubAccountId));
                long l_marginSubAccountId =
                    l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_marginSubAccountId));
                
            }
            
            l_strWhereBuf.append("(delivery_date >= ? AND ");
            l_strWhereBuf.append("delivery_date <= ?) AND ");
            l_strWhereBuf.append("delete_flag = ?");
            l_lisBindVars.add(l_datT0);
            l_lisBindVars.add(l_datT5);
            l_lisBindVars.add(BooleanEnum.FALSE);
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows =
                l_qp.doFindAllQuery(
                        FixedFinTransactionRow.TYPE,
                        l_strWhere,
                        null,
                        l_lisBindVars.toArray());
            
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
        }
    }
    
    /**
     * (get�����g�����U�N�V����)<BR>
     *�@@�����g�����U�N�V�����̃��X�g���擾����B<BR>
     * <BR>
     * �P�j�@@��������<BR>
     * <BR>
     * �����g�����U�N�V�����e�[�u����蒍������<BR>
     * <BR>
     * �����F<BR>
     * �����P��ID           ��   �����P��ID<BR>
     * �폜�t���O              ��   FALSE<BR>
     * <BR>
     * �Q�j�@@�������ꂽ�����g�����U�N�V������Ԃ�<BR>
     * <BR>
     * @@param orderUnitId - (�����P��ID)
     * @@return List
     * @@roseuid 4104D37600C5
     */
    public List getEqtypeFinTransactions(EqtypeOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME =
            "getEqtypeFinTransactions(EqtypeOrderUnitRow l_row)";
        
        try
        {
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_unit_id = ? AND ");
            l_strWhereBuf.append("delete_flag = ?");
            
            Object[] l_bindVars =
            {
                    new Long(l_row.getAccountId()),
                    new Long(l_row.getSubAccountId()),
                    new Long(l_row.getOrderUnitId()),
                    BooleanEnum.FALSE
            };
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get�I�v�V�����g�����U�N�V����)<BR>
     *�@@�I�v�V�����g�����U�N�V�����̃��X�g���擾����B<BR>
     *<BR>
     * �P�j�@@��������<BR>
     * <BR>
     * �i�敨�j�I�v�V�����g�����U�N�V�����e�[�u����蒍������<BR>
     * <BR>
     * �����F<BR>
     * �����P��ID           ��   �����P��ID<BR>
     * �폜�t���O              ��   FALSE<BR>
     * <BR>
     * �Q�j�@@�������ꂽ�I�v�V�����g�����U�N�V������Ԃ�<BR>
     * @@param orderUnitId - (�����P��ID)
     * @@return Lits
     * @@roseuid 4104D37600E4
     */
    public List getIfoFinTransactions(IfoOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME =
            "getIfoFinTransactions(IfoOrderUnitRow l_row)";
        
        try
        {
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_unit_id = ? AND ");
            l_strWhereBuf.append("delete_flag = ?");
            
            Object[] l_bindVars =
            {
                    new Long(l_row.getAccountId()),
                    new Long(l_row.getSubAccountId()),
                    new Long(l_row.getOrderUnitId()),
                    BooleanEnum.FALSE
            };
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(IfoFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get�O���g�����U�N�V����)<BR>
     *�@@�O���g�����U�N�V�����̃��X�g���擾����B<BR>
     *<BR>
     * �P�j�@@��������<BR>
     * <BR>
     * �O���g�����U�N�V�����e�[�u����蒍������<BR>
     * <BR>
     * �����F<BR>
     * �����P��ID           ��   �����P��ID<BR>
     * �폜�t���O              ��   FALSE<BR>
     * <BR>
     * �Q�j�@@�������ꂽ�O���g�����U�N�V������Ԃ�<BR>
     * @@param orderUnitId - (�����P��ID)
     * @@return List
     * @@roseuid 4104D37600F4
     */
    public List getFeqFinTransactions(FeqOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME =
            "getFeqFinTransactions(FeqOrderUnitRow l_row)";
        
        try
        {
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_strWhereBuf.append("sub_account_id = ? AND ");
            l_strWhereBuf.append("order_unit_id = ? AND ");
            l_strWhereBuf.append("delete_flag = ?");
            
            Object[] l_bindVars =
            {
                    new Long(l_row.getAccountId()),
                    new Long(l_row.getSubAccountId()),
                    new Long(l_row.getOrderUnitId()),
                    BooleanEnum.FALSE
            };
            
            String l_strWhere = l_strWhereBuf.toString();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(FeqFinTransactionRow.TYPE, l_strWhere, null,
                    l_bindVars);
            return l_lisRows;
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get������������)<BR>
     * ���������������擾����B<BR>
     *  <BR>
     * �P�j�@@����������������<BR>
     * <BR>
     * [a. ����.�g�����U�N�V�����������������̏ꍇ]<BR>
     *  ������������e�[�u����茟��<BR>
     * [a. ��L�ȊO�̏ꍇ]<BR>
     *  �����������Updq�e�[�u����茟��<BR>
     * <BR>
     * �����F<BR>
     * ����ID   ��   ����.����ID<BR>
     * ������s��   ��   ����.�g�����U�N�V����������<BR>
     * �������������t���O����<BR>
     * <BR>
     * �Q�j�������ʂ̃��X�g��ԋp����B<BR>
     *<BR>
     * @@return List
     * @@roseuid 4104D3760123
     */
    public List getTodayDepFundProducts(long l_lngProductId,
            Date l_transactionDate,
            WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodayDepRegProduct(long l_productId, Date l_transactionDate)";
        
        try
        {
            Date l_datT0 = l_assetValuation.getCalcCondition().getBizDate(
                    WEB3TPSpecifiedPointDef.T_0);
            String l_strYYYYMMDD = "yyyyMMdd";
            String l_strTransactionDate = WEB3DateUtility.formatDate(l_transactionDate,
                    l_strYYYYMMDD);
            
            final String l_strWhere =
                "product_id = ? AND valid_until_biz_date = ? AND today_dep_fund_reg = ?";
            
            final Object[] l_bindVars =
            {
                    new Long(l_lngProductId),
                    l_strTransactionDate,
                    BooleanEnum.TRUE
            };
            
            //������������EqtypeTradedProduct�e�[�u�����猟���A
            //�����ȊO��EqtypeTradedProductUqdq�e�[�u�����猟��
            final RowType l_rowType = WEB3DateUtility.compareToDay(l_datT0,
                    l_transactionDate) == 0 ?
                            EqtypeTradedProductRow.TYPE : EqtypeTradedProductUpdqRow.TYPE;
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            return l_qp.doFindAllQuery(l_rowType, l_strWhere, null, l_bindVars);
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (getIPO����)<BR>
     * IPO�������擾����B<BR>
     * <BR>
     * �P�j�@@IPO��������<BR>
     * <BR>
     * IPO�����e�[�u������������<BR>
     * <BR>
     * �����F<BR>
     * ����ID         ��   ����ID<BR>
     * <BR>
     * �Q�j�@@�������ꂽIPO������Ԃ�<BR>
     *
     * @@param productId - (����ID)
     * @@return IpoProductRow
     * @@roseuid 4104D3760132
     */
    public IpoProductRow getIPOProduct(long l_productId)
    {
        final String STR_METHOD_NAME =
            "getIPOProduct(long l_productId)";
        
        try
        {
            return IpoProductDao.findRowByIpoProductId(l_productId);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�ŗ�)<BR>
     * �ŗ����擾����B<BR>
     * <BR>
     * �ŗ��e�[�u�����<BR>
     * �E�Ŏ�ށ�����.�Ŏ��<BR>
     * �E�K�p�J�n����������.�K�p��<BR>
     * �E�K�p�I������������.�K�p��<BR>
     * �Ō����������ʃ��R�[�h.�ŗ���Ԃ��B<BR>
     * <BR>
     * @@param taxType - (�Ŏ��)
     * @@param applicationDate - (�K�p��)
     * @@return double
     * @@roseuid 4104D3760152
     */
    public double getTaxRate(WEB3TPAssetValuation l_assetValuation, String l_strTaxType,
            Date l_datApplicationDate)
    {
        final String STR_METHOD_NAME =
            "getTaxRate(String taxType, Date applicationDate)";
        
        try
        {
            final String l_strWhere =
                "institution_code=? and tax_type=? and (appli_start_date<=? and appli_end_date>=?)";
            //          �C�� �V�� 2005/02/01
            //                          "institution_code = ? AND tax_type = ? AND appli_start_date <= ? AND appli_end_date >= ?";
            
            Object[] l_bindVars =
            {
                    l_assetValuation.getAccountInfo().getInstitutionCode(),
                    l_strTaxType,
                    l_datApplicationDate,
                    l_datApplicationDate
            };
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(TaxRateTableRow.TYPE, l_strWhere, null,
                    l_bindVars);
            
            int l_intSize = l_lisRows.size();
            
            if (l_intSize == 0)
            {
                return 0.0d;
            }
            
            TaxRateTableRow l_row = (TaxRateTableRow) l_lisRows.get(0);
            //�C����
            //            BigDecimal l_oneHandred = new BigDecimal(WEB3GentradeNumberConstDef.HYAKU);
            //            int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
            //
            //            BigDecimal l_bdTaxRate =
            //                new BigDecimal(l_row.getTaxRate()).divide(l_oneHandred, l_intScale,
            //                BigDecimal.ROUND_HALF_EVEN);
            //            return l_bdTaxRate.doubleValue();
            return l_row.getTaxRate()/100;
            //end
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get���������z)<BR>
     * ���������z���擾����B<BR>
     *<BR>
     * �P�j�@@���X�e�[�u�����ȉ��̏����Ō����B<BR>
     * <BR>
     * �����F<BR>
     * ���XID���ڋq����.���XID<BR>
     * <BR>
     * �Q�j�@@��������.���������z��Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return double
     * @@roseuid 4104D3760171
     */
    public double getDayInterestAdjustment(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getDayInterestAdjustment(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            BranchRow l_row = BranchDao.findRowByBranchId(l_assetValuation.getAccountInfo().
                    getBranchId());
            
            return (l_row == null) ? 0.0d : l_row.getDailyInterestAdjustAmount();
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�萔��������������z)<BR>
     * �萔��������������z���擾����B<BR>
     *<BR>
     * �P�j�@@��Е��X���i�e�[�u�����ȉ��̏����Ō����B<BR>
     * <BR>
     * �����F<BR>
     * ���XID���ڋq����.���XID<BR>
     * �萔�����i�R�[�h���萔�����i�R�[�h<BR>
     * <BR>
     * �Q�j�@@��������.�萔����������z��Ԃ��B<BR>
     * @@param commissionProductCode - (�萔�����i�R�[�h)
     * @@param l_assetValuation - (���Y�]��)
     * @@return double
     * @@roseuid 4104D3760181
     */
    public double getConsolidatedCommissionAdjustment(String l_strCommProductCode,
            WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getConsolidatedCommissionAdjustment(String l_commProductCode, WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            InstBranchProductRow l_row =
                InstBranchProductDao.findRowByBranchIdCommissionProductCode(
                        l_assetValuation.getAccountInfo().getBranchId(), l_strCommProductCode);
            
            return (l_row == null) ? 0.0d : l_row.getCommissionLumpAdjustAmount();
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�����S����) <BR>
     * �����S�������擾����B <BR>
     * <BR>
     * �P�j ��Е��X���i�e�[�u�����ȉ��̏����Ō����B <BR>
     * <BR>
     * �����F <BR>
     * ���XID���ڋq����.���XID <BR>
     * �萔�����i�R�[�h���萔�����i�R�[�h <BR>
     * <BR>
     * �Q�j ��������.�����S������Ԃ��B <BR>
     * 
     * @@param commissionProductCode - (�萔�����i�R�[�h)
     * @@param l_assetValuation - (���Y�]��)
     * @@return double
     */
    public double getPremiumRestraintRate(String l_strCommProductCode,
            WEB3TPAssetValuation l_assetValuation)
    {
        //Y00125�F�����S�����Ή�
        final String STR_METHOD_NAME =
            "getPremiumRestraintRate(String l_commProductCode, WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            InstBranchProductRow l_row =
                InstBranchProductDao.findRowByBranchIdCommissionProductCode(
                        l_assetValuation.getAccountInfo().getBranchId(), l_strCommProductCode);
            
            return (l_row == null) ? 1.0d : l_row.getPremiumRestraintRate();
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get�⏕�����c���}�X�^�[)<BR>
     * �⏕�����c���}�X�^�[�̃��X�g���擾����B<BR>
     *<BR>
     * �P�j�@@�⏕�����c���}�X�^�[���i���j���ȉ��̏����Ō����B<BR>
     * <BR>
     * �����F<BR>
     * ����ID���ڋq����.����ID<BR>
     * �⏕����ID���ڋq����.�⏕����ID<BR>
     * <BR>
     * �Q�j�@@�������ʂ�Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     * @@roseuid 4104D37601A0
     */
    public List getTpCashBalances(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTpCashBalances(WEB3TPAssetValuation l_assetValuation)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
            long l_lngAccId = l_accountInfo.getAccountId();
            long l_lngEqSubAccId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                    EQUITY_SUB_ACCOUNT);
            
            List l_lisBindVars = new ArrayList();
            StringBuffer l_strWhereBuf = new StringBuffer("account_id = ? AND ");
            l_lisBindVars.add(new Long(l_lngAccId));
            l_strWhereBuf.append("sub_account_id in (?");
            l_lisBindVars.add(new Long(l_lngEqSubAccId));
            
            if (l_accountInfo.isMarginCustomer())
            {
                long l_lngMgSubAccId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.
                        EQUITY_MARGIN_SUB_ACCOUNT);
                l_lisBindVars.add(new Long(l_lngMgSubAccId));
                l_strWhereBuf.append(", ?");
            }
            l_strWhereBuf.append(")");
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            return l_qp.doFindAllQuery(TpCashBalanceRow.TYPE, l_strWhereBuf.toString(), null,
                    l_lisBindVars.toArray());
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * @@return webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager
     * @@roseuid 4104D3A700AF
     */
    public static WEB3TPPersistentDataManager getInstance()
    {
        
        if (THIS_INSTANCE == null)
        {
            THIS_INSTANCE = new WEB3TPPersistentDataManager();
        }
        return THIS_INSTANCE;
        
    }
    
    /**
     * (save�]�͍X�V���e<�����ڋq>)
     *
     * �P�jPrimary_key(calc_result_equity_id)���̔Ԃ���<BR>
     * �Q�j�����ڋq�̗]�͍X�V���e��DB�֕ۑ�����<BR>
     *
     * @@param l_list�@@- �����ڋq�̗]�͍X�V���e<BR>
     * @@roseuid 4110854102B3
     */
    public void saveTradingpowerUpdResultEquity(List l_list)
    {
        
        final String STR_METHOD_NAME =
            "saveTradingpowerUpdResultEquity(List)";
        
        TpCalcResultEquityParams l_params = null;
        TpCalcResultEquityDetailParams l_paramsDetail = null;
        
        Iterator l_iterator = l_list.iterator();
        while (l_iterator.hasNext())
        {
            Object l_obj = l_iterator.next();
            if(l_obj instanceof TpCalcResultEquityParams)
            {
                l_params = (TpCalcResultEquityParams)l_obj;
            }
            else if(l_obj instanceof TpCalcResultEquityDetailParams)
            {
                l_paramsDetail = (TpCalcResultEquityDetailParams)l_obj;
            }
        }
        
        try
        {
            if(l_params != null && l_paramsDetail != null)
            {
                long l_lngCalcResultEquityId = TpCalcResultEquityDao.
                newPkValue();
                l_params.setCalcResultEquityId(l_lngCalcResultEquityId);
                l_paramsDetail.setCalcResultEquityId(l_lngCalcResultEquityId);
                BatchedQuery[] l_batchedQueries = new BatchedQuery[]
                                                                   {
                        BatchedQuery.createInsertQuery(l_params),
                        BatchedQuery.createInsertQuery(l_paramsDetail)
                                                                   };
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                l_qp.doQueries(l_batchedQueries);
            }
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            // start 20090622��Q�����̏�Q�Ή��Ƃ��ă��O�o�͂�ǉ�
            log.error(l_list.toString(),de);
            // end
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (save�]�͍X�V���e<�M�p�ڋq>)
     *
     * �P�jPrimary_key(calc_result_margin_id)���̔Ԃ���<BR>
     * �Q�j�M�p�ڋq�̗]�͍X�V���e��DB�֕ۑ�����<BR>
     *
     * @@param l_list�@@- �M�p�ڋq�̗]�͍X�V���e<BR>
     * @@roseuid 41108593020E
     */
    public void saveTradingpowerUpdResultMargin(List l_list)
    {
        final String STR_METHOD_NAME =
            "saveTradingpowerUpdResultMargin(List)";
        
        TpCalcResultMarginParams l_params = null;
        TpCalcResultMarginDetailParams l_paramsDetail = null;
        
        Iterator l_iterator = l_list.iterator();
        while (l_iterator.hasNext())
        {
            Object l_obj = l_iterator.next();
            if(l_obj instanceof TpCalcResultMarginParams)
            {
                l_params = (TpCalcResultMarginParams)l_obj;
            }
            else if(l_obj instanceof TpCalcResultMarginDetailParams)
            {
                l_paramsDetail = (TpCalcResultMarginDetailParams)l_obj;
            }
        }
        
        try
        {
            if(l_params != null && l_paramsDetail != null)
            {
                long l_lngCalcResultMarginId = TpCalcResultMarginDao.
                newPkValue();
                l_params.setCalcResultMarginId(l_lngCalcResultMarginId);
                l_paramsDetail.setCalcResultMarginId(l_lngCalcResultMarginId);
                BatchedQuery[] l_batchedQueries = new BatchedQuery[]
                                                                   {
                        BatchedQuery.createInsertQuery(l_params),
                        BatchedQuery.createInsertQuery(l_paramsDetail)
                                                                   };
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                l_qp.doQueries(l_batchedQueries);
            }
            else
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            // start 20090622��Q�����̏�Q�Ή��Ƃ��ă��O�o�͂�ǉ�
            log.error(l_list.toString(),de);
            // end
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (�ڋq�����擾())
     * �ڋq�������擾����B<BR>
     * @@param l_lngAccountId - ����ID
     * @@param l_blnMarginFlag- �M�p�ڋq�t���O
     *
     * @@param l_blnMarginFlag
     * @@return WEB3TPAccountInfo
     * @@roseuid 4110900102B1
     */
    public WEB3TPAccountInfo getAccountInfo(long l_lngAccountId,
            boolean l_blnMarginFlag)
    {
        
        final String STR_METHOD_NAME =
            "getAccountInfo(long l_lngAccountId, boolean l_blnMarginFlag)";
        
        try
        {
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(l_lngAccountId);
            l_accountInfo.setMarginCustFlag(l_blnMarginFlag);
            
            MainAccountRow l_mainAccount = MainAccountDao.findRowByAccountId(
                    l_lngAccountId);
            if (l_mainAccount == null)
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_accountInfo.setBranchId(l_mainAccount.getBranchId());
            l_accountInfo.setBranchCode(l_mainAccount.getBranchCode());
            l_accountInfo.setInstitutionId(l_mainAccount.getInstitutionId());
            l_accountInfo.setInstitutionCode(l_mainAccount.getInstitutionCode());
            
            //�ŋ敪�����[�h����B
            Hashtable l_taxTypes = new Hashtable();
            l_taxTypes.put(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE, l_mainAccount.getTaxType());
            l_taxTypes.put(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT, l_mainAccount.getTaxTypeNext());
            
            if(l_blnMarginFlag)
            {
                l_taxTypes.put(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE, l_mainAccount.getMarginTaxType());
                l_taxTypes.put(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE_NEXT, l_mainAccount.getMarginTaxTypeNext());            	
            }
            l_accountInfo.setTaxTypes(l_taxTypes);
            
            List l_rows = SubAccountDao.findRowsByAccountId(l_lngAccountId);
            Hashtable l_subAccountIds = new Hashtable();
            for (int i = 0; i < l_rows.size(); i++)
            {
                SubAccountRow l_subAccountRow = (SubAccountRow) l_rows.get(i);
                l_subAccountIds.put(new Long(l_subAccountRow.getSubAccountId()),
                        l_subAccountRow.getSubAccountType());
            }
            l_accountInfo.setSubAccountIds(l_subAccountIds);
            
            
            return l_accountInfo;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
        
    }
    
    /**
     * (get����蕪�ԍό��ϑ��v)<BR>
     * <BR>
     * ���o�������܂ށA�����ȍ~�M�p�ԍϒ����i���������P�ʂ̃��X�g�j���擾����B<BR>
     * <BR>
     * �P�j�@@���������P�ʃe�[�u����蒍����������<BR>
     * <BR>
     * �����F<BR>
     * �@@����ID �� �ڋq����.get����ID()<BR>
     * �@@�⏕����ID �� �ڋq����.get�⏕����ID�i�⏕�����^�C�v�j<BR>
     * �@@�����^�C�v �� ��<BR>
     * �@@�����J�e�S�� �� �M�p�ԍ�<BR>
     * �@@�����L����� �� �I�[�v�� OR ��萔�� �� 0<BR>
     * �@@������ ���� ����<BR>
     * <BR>
     * �i���j�⏕�����^�C�v�E�E�E�⏕�����^�C�v.�������邢�͕⏕�����^�C�v.�M�p�i���ۂ̃f�[�^�͕⏕�����^�C�v.�M�p�̂݁j<BR>
     * <BR>
     * �Q�j�@@���������e�����������P��Params�ɕϊ������X�g�ɒǉ�����<BR>
     * <BR>
     * �R�j�@@���������̃��X�g��Ԃ�<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     */
    public List getTodaysCloseMarginOrdersAfterRepay(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getTodaysCloseMarginOrdersAfterRepay(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        //�M�p�q�łȂ��ꍇ�v�f0�̃��X�g��Ԃ�
        if (!l_accountInfo.isMarginCustomer())
        {
            log.exiting(STR_METHOD_NAME);
            return Collections.EMPTY_LIST;
        }
        
        long l_accountId = l_accountInfo.getAccountId();
        long l_equitySubAccountId =
            l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        long l_marginSubAccountId =
            l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        
        List l_lisRows = new ArrayList();
        
        //�������������ȍ~�̒���List(getTodaysEquityOrders�Ŏ擾����List)
        List l_lisTempRows=l_assetValuation.getTodaysEquityOrders();
        for(int i = 0; i < l_lisTempRows.size(); i++)
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_lisTempRows.get(i);
            
            //�T�u�A�J�E���gID
            if(l_marginSubAccountId == l_row.getSubAccountId())
            {
                //�����^�C�v �� ��
                if(ProductTypeEnum.EQUITY.equals(l_row.getProductType()))
                {
                    //�J�e�S�� �� �M�p�ԍ�
                    if(OrderCategEnum.CLOSE_MARGIN.equals(l_row.getOrderCateg()))
                    {
                        //�����L����� �� �I�[�v�� OR ��萔�� �� 0
                        if(OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus())
                                || (!l_row.getExecutedQuantityIsNull() && l_row.getExecutedQuantity() > 0))
                        {
                            l_lisRows.add(l_row);
                        }
                    }
                }
            }
        }
        
        /*
         * ���������e�����������P��Params�ɕϊ������X�g�ɒǉ�����
         */
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        if (l_newOrderSpecs != null)
        {
            TreeMap l_ht = new TreeMap();
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams)l_lisRows.get(i);
                l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
            }
            
            for (int i = 0; i < l_newOrderSpecs.length; i++)
            {
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_newOrderSpecs[i].getOrderType())
                        || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_newOrderSpecs[i].getOrderType()))
                {
                    EqtypeOrderUnitParams l_params = new EqtypeOrderUnitParams();
                    l_params.setAccountId(l_accountId);
                    l_params.setSubAccountId(l_marginSubAccountId);
                    l_params.setOrderId(l_newOrderSpecs[i].getOrderId());
                    l_params.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                    l_params.setProductType(l_newOrderSpecs[i].getProductType());
                    l_params.setProductId(l_newOrderSpecs[i].getProductId());
                    l_params.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                    l_params.setOrderType(l_newOrderSpecs[i].getOrderType());
                    l_params.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                    l_params.setQuantity(l_newOrderSpecs[i].getQuantity());
                    l_params.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                    l_params.setTaxType(l_newOrderSpecs[i].getTaxType());
                    l_params.setCapitalGain(l_newOrderSpecs[i].getCapitaGain());
                    l_params.setBizDate(
                            WEB3DateUtility.formatDate(
                                    l_newOrderSpecs[i].getOrderBizDate(),
                            "yyyyMMdd"));
                    
                    EqtypeOrderUnitParams l_oldParams =
                        (EqtypeOrderUnitParams)l_ht.get(new Long(l_params.getOrderUnitId()));
                    
                    if (l_oldParams != null)
                    {
                        l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                        l_ht.put(new Long(l_oldParams.getOrderUnitId()), l_params);
                    }
                    else
                    {
                        l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
                    }
                }
            }
            
            log.exiting(STR_METHOD_NAME);
            return new ArrayList(l_ht.values());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisRows;
    }
    
    /**
     * (get�����ԍϕϓ����)<BR>
     * �����ϓ������擾����B<BR>
     * <BR>
     * �P�j�����ϓ������擾����B<BR>
     * �@@�@@���������P�ʃe�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�|��������<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.����ID �� ����.�ڋq����.����ID<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.�⏕����ID �� ����.�ڋq����.�⏕����ID<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.�J�e�S�� �� �ԍϒ���
     * �@@�@@�@@�E���������P�ʃe�[�u��.������ ���� ����.�]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.������� not in ( �����ρi��������j)<BR>
     * �@@�@@�@@�E���������P�ʃe�[�u��.�����L����ԁ@@���I�[�v��
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     */
    public List getUnExecutedOrdersAfterRepay(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getUnExecutedOrdersAfterRepay(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        WEB3TPCalcCondition l_calcCondition = l_assetValuation.getCalcCondition();
        List l_lisRows = new ArrayList();
        
        //�M�p�q�łȂ��ꍇ�v�f0�̃��X�g��Ԃ�
        if (!l_accountInfo.isMarginCustomer())
        {
            log.exiting(STR_METHOD_NAME);
            return Collections.EMPTY_LIST;
        }
        
        long l_marginSubAccountId = l_accountInfo.getSubAccountId(true);
        
        //�������������ȍ~�̒���List(getTodaysEquityOrders�Ŏ擾����List)
        List l_lisTempRows=l_assetValuation.getTodaysEquityOrders();
        for(int i = 0; i < l_lisTempRows.size(); i++)
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_lisTempRows.get(i);
            
            //�T�u�A�J�E���gID
            if(l_marginSubAccountId == l_row.getSubAccountId())
            {
                //�J�e�S�� �� �M�p�ԍ�
                if(OrderCategEnum.CLOSE_MARGIN.equals(l_row.getOrderCateg()))
                {
                    //�����L����� �� �I�[�v��
                    if(OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus()))
                    {
                        //������� not in ( �����ρi��������j)
                        if(! OrderStatusEnum.CANCELLED.equals(l_row.getOrderStatus()))
                        {
                            l_lisRows.add(l_row);
                        }
                    }
                }
            }
        }
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        if (l_newOrderSpecs == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
        
        //���������e��List�ɔ��f
        TreeMap l_ht = new TreeMap();
        int l_lisRowsSize = l_lisRows.size();
        for (int i = 0; i < l_lisRowsSize; i++)
        {
            EqtypeOrderUnitParams l_params = (EqtypeOrderUnitParams)l_lisRows.get(i);
            l_ht.put(new Long(l_params.getOrderUnitId()), l_params);
        }
        
        int l_intNewSize = l_newOrderSpecs.length;
        for (int i = 0; i < l_intNewSize; i++)
        {
            //�J�e�S�����ԍς̏ꍇ
            OrderCategEnum l_newSpecOrderCateg = l_newOrderSpecs[i].getOrderCategory();
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_newSpecOrderCateg))
            {
                //���������e�����ɃI�u�W�F�N�g����
                EqtypeOrderUnitParams l_eqOrderUnit = new EqtypeOrderUnitParams();
                l_eqOrderUnit.setOrderId(l_newOrderSpecs[i].getOrderId());
                l_eqOrderUnit.setOrderUnitId(l_newOrderSpecs[i].getOrderUnitId());
                l_eqOrderUnit.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                l_eqOrderUnit.setOrderType(l_newOrderSpecs[i].getOrderType());
                l_eqOrderUnit.setEstimatedPrice(l_newOrderSpecs[i].getEstimatedPrice());
                l_eqOrderUnit.setPrice(l_newOrderSpecs[i].getPrice());
                l_eqOrderUnit.setQuantity(l_newOrderSpecs[i].getQuantity());
                l_eqOrderUnit.setBizDate(
                        GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
                                l_newOrderSpecs[i].getOrderBizDate()));
                l_eqOrderUnit.setDeliveryDate(l_newOrderSpecs[i].getDeliveryDate());
                l_eqOrderUnit.setMarketId(l_newOrderSpecs[i].getMarketId());
                l_eqOrderUnit.setProductId(l_newOrderSpecs[i].getProductId());
                l_eqOrderUnit.setTaxType(l_newOrderSpecs[i].getTaxType());
                
                EqtypeOrderUnitParams l_oldParams =
                    (EqtypeOrderUnitParams)l_ht.get(new Long(l_eqOrderUnit.getOrderUnitId()));
                if (l_oldParams != null)
                {
                    l_ht.remove(new Long(l_oldParams.getOrderUnitId()));
                    l_ht.put(new Long(l_eqOrderUnit.getOrderUnitId()), l_eqOrderUnit);
                }
                else
                {
                    l_ht.put(new Long(l_eqOrderUnit.getOrderUnitId()), l_eqOrderUnit);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return new ArrayList(l_ht.values());
    }
    
    /**
     * (get�����ȍ~����������)<BR>
     *�@@�����ȍ~�����������i���������P�ʂ̃��X�g�j���擾����B<BR>
     *<BR>
     * �P�j�@@���������P�ʃe�[�u����蓖����������<BR>
     * �����F<BR>
     * ����ID         ��   �ڋq����.get����ID()<BR>
     * ������          ����  ����<BR>
     * �Q�j�@@�������ꂽ���������̃��X�g��Ԃ�<BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     */
    public List getTodaysEquityOrders(WEB3TPAccountInfo l_accountInfo,
            WEB3TPCalcCondition l_calcCondition)
    {
        final String STR_METHOD_NAME =
            "getTodaysEquityOrders(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            long l_accountId = l_accountInfo.getAccountId();
            
            StringBuffer l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("account_id=? and ");
            l_strWhereBuf.append("TO_DATE(biz_Date,'YYYYMMDD') >=?");
            String l_strWhere = l_strWhereBuf.toString();
            Date l_datT0 = l_calcCondition.getBizDate(0);
            Object[] l_bindVars =
            {
                    new Long(l_accountId),
                    l_datT0
            };
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRows = l_qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE, l_strWhere, null,
                    l_bindVars);
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, de.getMessage(), de);
        }
    }
    
    /**
     * (get������<������>)<BR>
     * <BR>
     * �������ɂ����ă��[�h�ΏۂƂȂ���������A<BR>
     * �������P�ʃe�[�u����茟�����A�s�I�u�W�F�N�g�̃��X�g��ԋp����B<BR> 
     * <BR>
     * ���V�[�P���X�}�u(�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�)get������<������>�v�Q�� <BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     */
    public List getBondOrdersAmount(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getBondOrdersAmount(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. get�ڋq����( )
        //�ڋq�����I�u�W�F�N�g���擾����B
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        //1.2.get�]�͌v�Z����( )
        //�]�͌v�Z�����I�u�W�F�N�g���擾����B
        WEB3TPCalcCondition l_tpCalcCondition = l_assetValuation.getCalcCondition();
        
        //1.3.get�c�Ɠ�(int)
        //�����c�Ɠ����擾����B 
        //[����] 
        //int = 0
        Date l_datBizDate = l_tpCalcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        //1.4.(*)�������P�ʃe�[�u������������B
        //    ���������F
        //   �@@����ID = "�ڋq����".get����ID()
        l_sbWhere.append("account_id = ? ");
        long l_lngAccountId = l_accountInfo.getAccountId();
        l_lisBindVars.add(new Long(l_lngAccountId));
        
        //   �@@[a.�����ڋq�̏ꍇ]
        //   �@@("�ڋq����".is�M�p�ڋq() == false)
        //   �@@�@@�⏕����ID = "�ڋq����".get�⏕����ID(:SubAccountTypeEnum = '1:������������i�a����j')
        long l_lngSubAccountId = 0;
        if (!l_accountInfo.isMarginCustomer())
        {
            l_sbWhere.append("and sub_account_id = ? ");
            l_lngSubAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngSubAccountId));
        }
        
        //   �@@[a.�M�p�ڋq�̏ꍇ]
        //   �@@("�ڋq����".is�M�p�ڋq() == true)
        //   �@@�@@�⏕����ID IN 
        //   �@@�@@�@@(�@@"�ڋq����".get�⏕����ID(:SubAccountTypeEnum = 1�F������������i�a����j),  
        //   �@@�@@�@@�@@�@@"�ڋq����".get�⏕����ID(:SubAccountTypeEnum = 2�F�����M�p��������i�ۏ؋��j) )
        else if (l_accountInfo.isMarginCustomer())
        {
            l_sbWhere.append("and sub_account_id in (?, ?) ");
            long l_lngEquitySubAccountId =
                l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngEquitySubAccountId));
            long l_lngMarginSubAccountId =
                l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngMarginSubAccountId));
        }
            
        //   �@@������� IN (401�F����������, ������� = 402�F����������)
        l_sbWhere.append("and order_type in (?, ?) ");
        l_lisBindVars.add(OrderTypeEnum.BOND_BUY);
        l_lisBindVars.add(OrderTypeEnum.BOND_SELL);
        
        //   �@@�������敪 IN (0�F�����, 1�F����))
        l_sbWhere.append("and order_exec_status in (?, ?) ");
        l_lisBindVars.add(WEB3BondOrderExecStatusDef.UNEXECUTED);
        l_lisBindVars.add(WEB3BondOrderExecStatusDef.EXECUTED);
        
        //   �@@��蕪host���f�敪 �� 2�F�q�����f��
        l_sbWhere.append("and exec_host_reflect_div != ? ");
        l_lisBindVars.add(WEB3HostReflectDivDef.CUSTOMER_CALCULATION_REFLECTED);
        
        //   �@@��n�� >= "T+0"
        l_sbWhere.append("and delivery_date >= ? ");
        l_lisBindVars.add(l_datBizDate);
        
        //   �@@���ϋ敪 = 1�F�~��
        l_sbWhere.append("and settlement_div = ? ");
        l_lisBindVars.add(WEB3BondOrderSettleDivDef.YEN_CURRENCY);
        
        String l_strWhere = l_sbWhere.toString();
        List l_lisBondOrderUnitRows = null;
        try
        {
            QueryProcessor l_qProcessor = Processors.getDefaultProcessor();
            l_lisBondOrderUnitRows =
                l_qProcessor.doFindAllQuery(
                    BondOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_lisBindVars.toArray());
        }
        catch (DataException l_dex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        //1.5.(*)LOOP�����F�������P�ʃe�[�u���̌������ʂ̍s����
        TreeMap l_treeMap = new TreeMap();
        BondOrderUnitRow l_bondOrderUnitRow = null;
        for (Iterator l_iter = l_lisBondOrderUnitRows.iterator(); l_iter.hasNext(); )
        {
            //1.5.1. put(Object, Object)
            //�������P�ʃ}�b�v�ɁA�s�I�u�W�F�N�g��ǉ�����B 
            //[����] 
            //Object = new Long(:long = �s�I�u�W�F�N�g.�����P��ID) 
            //Object = �s�I�u�W�F�N�g 
            l_bondOrderUnitRow = (BondOrderUnitRow)l_iter.next();
            l_treeMap.put(new Long(l_bondOrderUnitRow.getOrderUnitId()), l_bondOrderUnitRow);
        }
        
        //1.6.get���������e( )
        //���������e�I�u�W�F�N�g�̔z����擾����B 
        WEB3TPNewOrderSpec[] l_newOrderSpecs = l_assetValuation.getNewOrderSpecs();
        
        //1.7.(*)����t���[
        //���������e�����݂���ꍇ
        //(get���������e() != null)
        if (l_newOrderSpecs != null)
        {
            //1.7.1.1.(*)LOOP�����Fget���������e()�̖߂�l�̔z��v�f����
            for (int i = 0 ; i < l_newOrderSpecs.length; i ++)
            {
                //1.7.1.1.get�����^�C�v( )
                //�����^�C�v�i=������ʁj���擾����B
                OrderTypeEnum l_orderTypeEnum = l_newOrderSpecs[i].getOrderType();
                
                //1.7.1.2.(*)����t���[
                //�������ł���ꍇ
                //(get�����^�C�v()�̖߂�l IN (401�F�����������@@402�F�����蒍��))
                if (OrderTypeEnum.BOND_BUY.equals(l_orderTypeEnum) 
                    || OrderTypeEnum.BOND_SELL.equals(l_orderTypeEnum))
                {
                    //1.7.1.2.1.get�����P��ID( )
                    //�����P��ID���擾����B
                    long l_lngOrderUnitId = l_newOrderSpecs[i].getOrderUnitId();
                    
                    //1.7.1.2.2. get(Object)
                    //�V�K�������e�Ɠ��꒍���P�ʂh�c�ł���s�I�u�W�F�N�g���擾����B
                    //[����]
                    //Object = new Long(:long = get�����P��ID()�̖߂�l)  
                    Long l_orderUnitId = new Long(l_lngOrderUnitId);
                    
                    BondOrderUnitRow l_oldBondOrderUnitRow = 
                        (BondOrderUnitRow)l_treeMap.get(l_orderUnitId);
                    
                    BondOrderUnitParams l_bondOrderUnitParams = null;
                    
                    //1.7.1.2.3.(*)����t���[
                    //�������� �܂��� ��������ł���ꍇ
                    //("�������P�ʃ}�b�v".get()�̖߂�l != null)
                    if (l_oldBondOrderUnitRow != null)
                    {
                        //1.7.1.2.3.1.BondOrderUnitParams(BondOrderUnitRow)
                        //"�������P��Paramas"�𐶐�����B
                        //[����]
                        //BondOrderUnitRow = "�������P�ʃ}�b�v".get()�̖߂�l
                        //��Params����R�s�[Params�쐬
                        l_bondOrderUnitParams = new BondOrderUnitParams(
                            l_oldBondOrderUnitRow);
                        
                        //1.7.1.2.3.2. (*)"�������P��Params"�ɁA�l���Z�b�g����B
                        //[�ݒ�l]
                        //�@@"�������P��Params".����ID = "�ڋq����".get����ID()
                        l_bondOrderUnitParams.setAccountId(l_accountInfo.getAccountId());
                        
                        //�@@"�������P��Params".�⏕����ID = ���������e.get�⏕����ID()
                        l_bondOrderUnitParams.setSubAccountId(
                            l_newOrderSpecs[i].getSubAccountId());
                        
                        //�@@"�������P��Params".����ID = ���������e.get����ID()
                        l_bondOrderUnitParams.setOrderId(
                            l_newOrderSpecs[i].getOrderId());
                        
                        //�@@"�������P��Params".�����P��ID = ���������e.get�����P��ID()
                        l_bondOrderUnitParams.setOrderUnitId(
                            l_newOrderSpecs[i].getOrderUnitId());
                        
                        //�@@"�������P��Params".����ID = ���������e.get����ID()
                        l_bondOrderUnitParams.setProductId(
                            l_newOrderSpecs[i].getProductId());
                        
                        //�@@"�������P��Params".�����^�C�v = ���������e.get�����^�C�v()
                        l_bondOrderUnitParams.setProductType(
                            l_newOrderSpecs[i].getProductType());
                        
                        //�@@"�������P��Params".�s��ID = ���������e.get�s��ID()
                        l_bondOrderUnitParams.setMarketId(
                            l_newOrderSpecs[i].getMarketId());
                        
                        //�@@"�������P��Params".�����J�e�S�� = ���������e.get�����J�e�S��()
                        l_bondOrderUnitParams.setOrderCateg(
                            l_newOrderSpecs[i].getOrderCategory());
                        
                        //�@@"�������P��Params".������� = ���������e.get�����^�C�v()
                        l_bondOrderUnitParams.setOrderType(
                            l_newOrderSpecs[i].getOrderType());
                        
                        //�@@"�������P��Params".�������� = ���������e.get����()
                        l_bondOrderUnitParams.setQuantity(
                            l_newOrderSpecs[i].getQuantity());
                        
                        //�@@"�������P��Params".�����P�� = ���������e.get�P��()
                        l_bondOrderUnitParams.setPrice(l_newOrderSpecs[i].getPrice());
                        
                        //�@@"�������P��Params".�w�l = ���������e.get�w�l()
                        l_bondOrderUnitParams.setLimitPrice(
                            l_newOrderSpecs[i].getLimitPrice());
                        
                        //�@@"�������P��Params".��n���(�~��) = ���������e.get�T�Z���()
                        l_bondOrderUnitParams.setEstimatedPrice(
                            l_newOrderSpecs[i].getEstimatedPrice());
                        
                        //�@@"�������P��Params".������ = ���������e.get������()
                        l_bondOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                            l_newOrderSpecs[i].getOrderBizDate(), "yyyyMMdd"));
                        
                        //�@@"�������P��Params".��n�� = ���������e.get��n��()
                        l_bondOrderUnitParams.setDeliveryDate(
                            l_newOrderSpecs[i].getDeliveryDate());
                        
                        //�@@"�������P��Params".�ŋ敪 = ���������e.get�ŋ敪()
                        l_bondOrderUnitParams.setTaxType(l_newOrderSpecs[i].getTaxType());
                        
                        //�@@"�������P��Params".�󒍓��� = ���������e.get�󒍓���()
                        l_bondOrderUnitParams.setReceivedDateTime(
                            l_newOrderSpecs[i].getReceivedDateTime());
                        
                        //�@@"�������P��Params".������ = ���������e.get������()
                        l_bondOrderUnitParams.setPaymentDate(
                            l_newOrderSpecs[i].getPaymentDate());
                        
                        //�@@"�������P��Params".��萔�� = ���������e.get��萔��()
                        l_bondOrderUnitParams.setExecutedQuantity(
                            l_newOrderSpecs[i].getExecutedQuantity());
                        
                        //�@@"�������P��Params".�������敪 = ���������e.get�������敪()
                        l_bondOrderUnitParams.setOrderExecStatus(
                            l_newOrderSpecs[i].getOrderExecStatus());
                        
                        //�@@"�������P��Params".��� = ���������e.get���()
                        l_bondOrderUnitParams.setDealType(l_newOrderSpecs[i].getDealType());
                        
                        //��"�ڋq����" = get�ڋq����()�̖߂�l
                        
                        //1.7.1.2.3.3.remove(Object)
                        //"�������P�ʃ}�b�v"���A�w��̗v�f���폜����B
                        //[����]
                        //Object = new Long(:long = get�����P��ID()�̖߂�l)
                        l_treeMap.remove(new Long(l_lngOrderUnitId));
                    }
                    
                    //1.7.1.2.4.(*)����t���[
                    //�ȊO�i�V�K�����j�̏ꍇ
                    else
                    {
                        //1.7.1.2.4.1.BondOrderUnitParams( )
                        //"�������P��Paramas"�𐶐�����B
                        l_bondOrderUnitParams = new BondOrderUnitParams();
                        
                        //[�ݒ�l]
                        //�@@"�������P��Params".����ID = "�ڋq����".get����ID()
                        l_bondOrderUnitParams.setAccountId(l_accountInfo.getAccountId());
                        
                        //�@@"�������P��Params".�⏕����ID = ���������e.get�⏕����ID()
                        l_bondOrderUnitParams.setSubAccountId(
                            l_newOrderSpecs[i].getSubAccountId());
                        
                        //�@@"�������P��Params".����ID = ���������e.get����ID()
                        l_bondOrderUnitParams.setOrderId(l_newOrderSpecs[i].getOrderId());
                        
                        //�@@"�������P��Params".�����P��ID = ���������e.get�����P��ID()
                        l_bondOrderUnitParams.setOrderUnitId(
                            l_newOrderSpecs[i].getOrderUnitId());
                        
                        //�@@"�������P��Params".����ID = ���������e.get����ID()
                        l_bondOrderUnitParams.setProductId(l_newOrderSpecs[i].getProductId());
                        
                        //�@@"�������P��Params".�����^�C�v = ���������e.get�����^�C�v()
                        l_bondOrderUnitParams.setProductType(l_newOrderSpecs[i].getProductType());
                        
                        //�@@"�������P��Params".�s��ID = ���������e.get�s��ID()
                        l_bondOrderUnitParams.setMarketId(l_newOrderSpecs[i].getMarketId());
                        
                        //�@@"�������P��Params".�����J�e�S�� = ���������e.get�����J�e�S��()
                        l_bondOrderUnitParams.setOrderCateg(l_newOrderSpecs[i].getOrderCategory());
                        
                        //�@@"�������P��Params".������� = ���������e.get�����^�C�v()
                        l_bondOrderUnitParams.setOrderType(l_newOrderSpecs[i].getOrderType());
                        
                        //�@@"�������P��Params".�������� = ���������e.get����()
                        l_bondOrderUnitParams.setQuantity(l_newOrderSpecs[i].getQuantity());
                        
                        //�@@"�������P��Params".�����P�� = ���������e.get�P��()
                        l_bondOrderUnitParams.setPrice(l_newOrderSpecs[i].getPrice());
                        
                        //�@@"�������P��Params".�w�l = ���������e.get�w�l()
                        l_bondOrderUnitParams.setLimitPrice(l_newOrderSpecs[i].getLimitPrice());
                        
                        //�@@"�������P��Params".��n���(�~��) = ���������e.get�T�Z���()
                        l_bondOrderUnitParams.setEstimatedPrice(
                            l_newOrderSpecs[i].getEstimatedPrice());
                        
                        //�@@"�������P��Params".������ = ���������e.get������()
                        l_bondOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                            l_newOrderSpecs[i].getOrderBizDate(), "yyyyMMdd"));
                        
                        //�@@"�������P��Params".��n�� = ���������e.get��n��()
                        l_bondOrderUnitParams.setDeliveryDate(
                            l_newOrderSpecs[i].getDeliveryDate());
                        
                        //�@@"�������P��Params".�ŋ敪 = ���������e.get�ŋ敪()
                        l_bondOrderUnitParams.setTaxType(l_newOrderSpecs[i].getTaxType());
                        
                        //�@@"�������P��Params".�󒍓��� = ���������e.get�󒍓���()
                        l_bondOrderUnitParams.setReceivedDateTime(
                            l_newOrderSpecs[i].getReceivedDateTime());
                        
                        //�@@"�������P��Params".������ = ���������e.get������()
                        l_bondOrderUnitParams.setPaymentDate(
                            l_newOrderSpecs[i].getPaymentDate());
                        
                        //�@@"�������P��Params".��萔�� = ���������e.get��萔��()
                        l_bondOrderUnitParams.setExecutedQuantity(
                            l_newOrderSpecs[i].getExecutedQuantity());
                        
                        //�@@"�������P��Params".�������敪 = ���������e.get�������敪()
                        l_bondOrderUnitParams.setOrderExecStatus(
                            l_newOrderSpecs[i].getOrderExecStatus());
                        
                        //�@@"�������P��Params".��� = ���������e.get���()
                        l_bondOrderUnitParams.setDealType(l_newOrderSpecs[i].getDealType());
                    }
                    
                    //1.7.1.2.5.(*)����t���[
                    //(*)����t���[
                    //��������łȂ��ꍇ
                    //("�������P��Params".get��������()�̖߂�l != 0)
                    if (l_bondOrderUnitParams.getQuantity() != 0)
                    {
                        //1.7.1.2.5.1.put(Object, Object)
                        //"�������P�ʃ}�b�v"�ɁA"�������P��Params"��ǉ�����B
                        //[����]
                        //Object = new Long(:long = "�������P��Params".�����P��ID)
                        //Object = "�������P��Params"
                        l_treeMap.put(new Long(
                            l_bondOrderUnitParams.getOrderUnitId()), 
                            l_bondOrderUnitParams);
                    }
                }
            }
        }

        //1.8.(*)"�������P�ʃ}�b�v"���A���X�g�ɕϊ����ĕԋp����B
        
        log.exiting(STR_METHOD_NAME);
        return new ArrayList(l_treeMap.values());
    }
    
    /**
     * (get������<�a���>)<BR> 
     * <BR>
     * �a����ɂ����ă��[�h�ΏۂƂȂ���ߓ�������������A<BR> 
     * �������P�ʃe�[�u����茟�����A�s�I�u�W�F�N�g�̃��X�g��ԋp����B<BR> 
     * <BR>
     * ���V�[�P���X�}�u(�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�)get������<�a���>�v�Q�� <BR>
     * <BR>
     * @@param l_assetValuation - (���Y�]��)
     * @@return List
     */
    public List getBondOrdersCash(WEB3TPAssetValuation l_assetValuation)
    {
        final String STR_METHOD_NAME =
            "getBondOrdersCash(WEB3TPAssetValuation l_assetValuation)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get�ڋq����( )
        //�ڋq�����I�u�W�F�N�g���擾����B
        WEB3TPAccountInfo l_accountInfo = l_assetValuation.getAccountInfo();
        
        //1.2.get�]�͌v�Z����( )
        //�]�͌v�Z�����I�u�W�F�N�g���擾����B
        WEB3TPCalcCondition l_caclCondition = l_assetValuation.getCalcCondition();
        
        //1.3.get�c�Ɠ�(int)
        //�����c�Ɠ����擾����B
        //[����]
        //int = 0
        Date l_datBizDate = l_caclCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        
        //1.4.(*)�������P�ʃe�[�u������������B
        //    ���������F
        //   �@@����ID = "�ڋq����".get����ID()
        l_sbWhere.append("account_id = ? ");
        long l_lngAccountId = l_accountInfo.getAccountId();
        l_lisBindVars.add(new Long(l_lngAccountId));
        
        //   �@@AND
        //   �@@[a.�����ڋq�̏ꍇ]
        //   �@@("�ڋq����".is�M�p�ڋq() == false)
        //   �@@�@@�⏕����ID = "�ڋq����".get�⏕����ID(:SubAccountTypeEnum = '1:������������i�a����j')
        long l_lngSubAccountId = 0;
        if (!l_accountInfo.isMarginCustomer())
        {
            l_sbWhere.append("and sub_account_id = ? ");
            l_lngSubAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngSubAccountId));
        }
        
        //   �@@[a.�M�p�ڋq�̏ꍇ]
        //   �@@("�ڋq����".is�M�p�ڋq() == true)
        //   �@@�@@�⏕����ID IN 
        //   �@@�@@�@@(�@@"�ڋq����".get�⏕����ID(:SubAccountTypeEnum = 1�F������������i�a����j),  
        //   �@@�@@�@@�@@�@@"�ڋq����".get�⏕����ID(:SubAccountTypeEnum = 2�F�����M�p��������i�ۏ؋��j) )
        else if (l_accountInfo.isMarginCustomer())
        {
            l_sbWhere.append("and sub_account_id in (?, ?) ");
            long l_lngEquitySubAccountId =
                l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngEquitySubAccountId));
            long l_lngMarginSubAccountId =
                l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_lisBindVars.add(new Long(l_lngMarginSubAccountId));
        }
            
        //   �@@AND
        //   �@@������� IN (401�F����������, ������� = 402�F����������)
        l_sbWhere.append("and order_type in (?, ?) ");
        l_lisBindVars.add(OrderTypeEnum.BOND_BUY);
        l_lisBindVars.add(OrderTypeEnum.BOND_SELL);
        
        //   �@@AND 
        //   �@@�������敪 �� 2�F�����
        l_sbWhere.append("and order_exec_status = ? ");
        l_lisBindVars.add(WEB3BondOrderExecStatusDef.CANCELED);
        
        //   �@@AND 
        //   �@@��蕪host���f�敪 �� 2�F�q�����f��
        l_sbWhere.append("and exec_host_reflect_div = ? ");
        l_lisBindVars.add(WEB3HostReflectDivDef.CUSTOMER_CALCULATION_REFLECTED);
        
        //   �@@AND
        //   �@@�����host���f�敪 IN(0�F�����f, 1�F���M��) 
        l_sbWhere.append("and cancel_host_reflect_div in (?, ?) ");
        l_lisBindVars.add(WEB3HostReflectDivDef.NOT_REFLECT); 
        l_lisBindVars.add(WEB3HostReflectDivDef.SENDED); 
        
        //   �@@AND
        //   �@@��n�� >= "T+0"
        l_sbWhere.append("and delivery_date >= ? ");
        l_lisBindVars.add(l_datBizDate);
        
        //   �@@AND
        //   �@@���ϋ敪 = 1�F�~��
        l_sbWhere.append("and settlement_div = ? ");
        l_lisBindVars.add(WEB3BondOrderSettleDivDef.YEN_CURRENCY);
        
        String l_strWhere = l_sbWhere.toString();
        List l_lisBondOrderUnitRows = null;
        
        try
        {
            QueryProcessor l_qProcessor = Processors.getDefaultProcessor();
            l_lisBondOrderUnitRows =
                l_qProcessor.doFindAllQuery(
                    BondOrderUnitRow.TYPE,
                    l_strWhere,
                    null,
                    l_lisBindVars.toArray());
        }
        catch (DataException l_dex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        //1.5.(*)�������ʍs���X�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisBondOrderUnitRows;
    }

    /**
     * (get���̑��S����(���S��))<BR>
     * <BR>
     * ���̑��S����(���S����)�����擾����B<BR>
     * <BR>
     * �P�j���̑��S����(���S����)�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@����ID = ����.����ID<BR>
     * �@@�@@�@@AND �폜�t���O = 0�Ffalse<BR>
     * <BR>
     * �Q�j�������ʂ�Ԃ��B<BR>
     * @@param l_accountId - (����ID)<BR>
     * @@return List
     */
    public List getTempRestraint(Long l_accountId)
    {
        final String STR_METHOD_NAME = "getTempRestraint(Long)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        //����ID = ����.����ID
        l_sbWhere.append(" account_id = ? ");
        l_lisBindVars.add(l_accountId);
        //�폜�t���O = 0�Ffalse
        l_sbWhere.append(" and delete_flag = ? ");
        l_lisBindVars.add(BooleanEnum.FALSE);

        List l_lisTpOtherTempRestraintRows = null;
        try
        {
            QueryProcessor l_qProcessor = Processors.getDefaultProcessor();
            l_lisTpOtherTempRestraintRows =
                l_qProcessor.doFindAllQuery(
                    TpOtherTempRestraintRow.TYPE,
                    l_sbWhere.toString(),
                    l_lisBindVars.toArray());
        }
        catch (DataException l_dex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        //�������ʂ�Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_lisTpOtherTempRestraintRows;
    }

    /**
     * (get�O������)<BR>
     * �O�������e�[�u����PK��������B <BR>
     * <BR>
     * �������� <BR>
     * �@@����ID = ����.����ID <BR>
     * <BR>
     * �������ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * @@param l_lngProductId - (�����h�c)<BR>
     * (�����h�c)<BR>
     * @@return FeqProductRow
     */
    public FeqProductRow getFeqProduct(long l_lngProductId)
    {
        final String STR_METHOD_NAME = "getFeqProduct(long)";
        log.entering(STR_METHOD_NAME);

        FeqProductRow l_feqProductRow = null;
        //�O�������e�[�u����PK��������
        //��������
        //�@@����ID = ����.����ID
        try
        {
            l_feqProductRow = FeqProductDao.findRowByPk(l_lngProductId);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqProductRow;
    }

    /**
     * (get�i���ʁj�ʉ�)<BR>
     * �i���ʁj�ʉ݃e�[�u����PK��������B <BR>
     * <BR>
     * ���������F <BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�ʉ݃R�[�h = ����.�ʉ݃R�[�h <BR>
     * <BR>
     * �������ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * @@return GenCurrencyRow
     */
    public GenCurrencyRow getGenCurrency(String l_strInstitutionCode, String l_strCurrencyCode)
    {
        final String STR_METHOD_NAME = "getGenCurrency(String, String)";
        log.entering(STR_METHOD_NAME);

        GenCurrencyRow l_genCurrencyRow = null;
        //�i���ʁj�ʉ݃e�[�u����PK��������
        //��������
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h
        //�@@�ʉ݃R�[�h = ����.�ʉ݃R�[�h
        try
        {
            l_genCurrencyRow = GenCurrencyDao.findRowByPk(l_strInstitutionCode, l_strCurrencyCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_genCurrencyRow;
    }

    /**
     * (save���̑��S�����i���S���j)<BR>
     * ���̑��S�����i���S���j�e�[�u���Ƀ��R�[�h��}������B<BR>
     * <BR>
     * [�ݒ�l]<BR>
     * �@@�u�}��_���̑��S����(���S��)�vDB�X�V�d�l�Q��<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_dblTradeRestraint - (�S����)<BR>
     * �S����<BR>
     * @@param l_datTransaction - (�g�����U�N�V����������)<BR>
     * �g�����U�N�V����������<BR>
     * @@param l_datDelivery - (��n��)<BR>
     * ��n��<BR>
     * @@param l_strDeletekey1 - (�폜�L�[�P)<BR>
     * �폜�L�[�P<BR>
     * @@param l_strRestraintDiv - (�S�������)<BR>
     * �S�������<BR>
     * @@throws WEB3BaseException
     */
    public void saveOtherRestraint(
		long l_lngAccountId,
		double l_dblTradeRestraint,
		Date l_datTransaction,
		Date l_datDelivery,
		String l_strDeletekey1,
		String l_strRestraintDiv) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME =
            "saveOtherRestraint(long, double, Date, Date, String, String)";
    	log.entering(STR_METHOD_NAME);

        try
        {
        	TpOtherTempRestraintParams l_tpOtherTempRestraintParams = new TpOtherTempRestraintParams();
        	//����ID=����.�����h�c
        	l_tpOtherTempRestraintParams.setAccountId(l_lngAccountId);
        	//�S�������=����.�S�������
        	l_tpOtherTempRestraintParams.setRestraintDiv(l_strRestraintDiv);
        	//�S����=����.�S����
        	l_tpOtherTempRestraintParams.setAmount(l_dblTradeRestraint);
        	//�g�����U�N�V����������=����.�g�����U�N�V����������
        	l_tpOtherTempRestraintParams.setTransactionDate(l_datTransaction);
        	//��n��=����.��n��
        	l_tpOtherTempRestraintParams.setDeliveryDate(l_datDelivery);
        	//�폜�L�[1=����.�폜�L�[�P
        	l_tpOtherTempRestraintParams.setDeleteKey1(l_strDeletekey1);
        	//�폜�L�[2=NULL
        	l_tpOtherTempRestraintParams.setDeleteKey2(null);
        	//�폜�t���O=0�FFALSE
        	l_tpOtherTempRestraintParams.setDeleteFlag(BooleanEnum.FALSE);
        	//�쐬���t=���ݎ����i�V�X�e���^�C���X�^���v�j
        	l_tpOtherTempRestraintParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        	//�X�V���t=���ݎ����i�V�X�e���^�C���X�^���v�j
        	l_tpOtherTempRestraintParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        	QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        	l_queryProcessor.doInsertQuery(l_tpOtherTempRestraintParams);
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
    }

    /**
     * (delete���̑��S�����i���S���j)<BR>
     * ���̑��S�����i���S���j�e�[�u�����X�V�i�_���폜�j����B<BR>
     * <BR>
     * �P�j���̑��S�����i���S���j�e�[�u�����������A���ڂ��X�V����B<BR>
     * [��������]<BR>
     * �@@�S������� = ����.�S�������<BR>
     * �@@�폜�L�[�P = ����.�폜�L�[�P<BR>
     * <BR>
     * [�ݒ�l] <BR>
     * �@@�u�X�V_���̑��S�����i���S���j�vDB�X�V�d�l�Q��<BR>
     * @@param l_strRestraintDiv - (�S�������)<BR>
     * �S�������<BR>
     * @@param l_strDeletekey1 - (�폜�L�[�P)<BR>
     * �폜�L�[�P<BR>
     * @@throws WEB3BaseException
     */
    public void deleteOtherRestraint(String l_strRestraintDiv, String l_strDeletekey1) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "deleteOtherRestraint(String, String)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" restraint_div = ? ");
        l_sbWhere.append(" and delete_key1 = ? ");

        Object[] l_objWheres =
            new Object[]{l_strRestraintDiv, l_strDeletekey1};

        Map l_map = new HashMap();
        l_map.put("delete_flag", BooleanEnum.TRUE);
        l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                TpOtherTempRestraintRow.TYPE,
                l_sbWhere.toString(),
                l_objWheres,
                l_map);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (isExist���̑��S�����i���S���j)<BR>
     * <BR>
     * ���̑��S�����i���S���j�e�[�u���Ɉ���.�폜�L�[�P�����݂��邩�ǂ������f����B<BR>
     * �@@�����R�[�h�����݂���ꍇ�́Atrue�A���R�[�h�����݂��Ȃ��ꍇ�́Afalse ��ԋp����B<BR>
     * <BR>
     * [��������]<BR>
     * �@@�S������� = ����.�S�������<BR>
     * �@@�폜�L�[�P = ����.�폜�L�[�P<BR>
     * @@param l_strRestraintDiv - (�S�������)<BR>
     * �S�������<BR>
     * @@param l_strDeletekey1 - (�폜�L�[�P)<BR>
     * �폜�L�[�P<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isExistOtherRestraint(String l_strRestraintDiv, String l_strDeletekey1) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "isExistOtherRestraint(String, String)";
    	log.entering(STR_METHOD_NAME);

    	List l_lisTpOtherTempRestraintRows = new ArrayList();
        try
        {
        	//[��������]
        	//�S������� = ����.�S�������
        	//�폜�L�[�P = ����.�폜�L�[�P
        	l_lisTpOtherTempRestraintRows =
        		TpOtherTempRestraintDao.findRowsByRestraintDivDeleteKey1(l_strRestraintDiv, l_strDeletekey1);
        	if (!l_lisTpOtherTempRestraintRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
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
    }

    /**
     * (get�ڋq����c��(�}�X�^���))<BR>
     * <BR>
     * �ڋq����c��(�}�X�^���)Row���擾����B<BR>
     * ���擾�ł��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * [��������]<BR>
     * �@@�E����ID = ����.����ID<BR>
     * �@@�E�⏕����ID = ����.�⏕����ID<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_lngSubAccountId - (�⏕����ID)<BR>
     * �⏕����ID<BR>
     * @@return TpCashBalanceRow
     * @@throws WEB3BaseException
     */
    public TpCashBalanceRow getTpCashBalanceRow(long l_lngAccountId, long l_lngSubAccountId)
        throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getTpCashBalanceRow(long, long)";
        log.entering(STR_METHOD_NAME);

        TpCashBalanceRow l_tpCashBalanceRow = null;
        //�ڋq����c��(�}�X�^���)Row���擾����B
        //[��������]
        //�@@����ID = ����.����ID
        //�@@�⏕����ID = ����.�⏕����ID
        try
        {
        	l_tpCashBalanceRow = TpCashBalanceDao.findRowByAccountIdSubAccountId(l_lngAccountId, l_lngSubAccountId);
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
        return l_tpCashBalanceRow;
    }

    /**
     * (get��������)<BR>
     * ���������e�[�u����PK��������B <BR>
     * <BR>
     * ��������<BR> 
     * �@@����ID = ����.����ID<BR> 
     * <BR>
     * @@param l_lngProductId -(����ID)<BR>
     * ����ID<BR>
     * @@return EqtypeProductRow
     * @@throws WEB3BaseException
     */
    public EqtypeProductRow getEqtypeProduct(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEqtypeProduct(long)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeProductRow l_eqtypeProductRow = null;
        
        try
        {
        	l_eqtypeProductRow = EqtypeProductDao.findRowByPk(l_lngProductId);
        }
        catch (DataFindException l_dfe)
        {
        	l_eqtypeProductRow = null;
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
        return l_eqtypeProductRow;
        
    }
    		
}
@
