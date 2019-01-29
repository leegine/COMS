head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�������|�W�V�����}�l�[�W��(WEB3FeqPositionManager.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/14 ������(���u) �V�K�쐬
                   2005/07/27 鰊](���u) ���r���[
                   2010/09/08 ��V��(���u) ���f��545
*/
package webbroker3.feq;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������|�W�V�����}�l�[�W��) <BR>
 * �O�������|�W�V�����}�l�[�W��<BR>
 * <BR>
 * @@ author ������ <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqPositionManager extends FeqPositionManagerImpl 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqPositionManager.class);
    
    protected WEB3FeqPositionManagerHelper m_helper;
    protected ProductTypeEnum m_tradingType;
    /**
     * getPersistenceManager
     * �O�������X�V�f�[�^�}�l�[�W�����擾����B 
     * �|�W�V�����w���p�[.getPersistenceManager()�̖߂�l��ԋp����B
     */
    public WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager getPersistenceManager()
    {
        WEB3FeqPositionManagerHelper l_positionManagerHelper = 
            new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)l_positionManagerHelper.getPersistenceManager();
        
        return l_dataManager;
    }

    /**
     * @@roseuid 42CE39E8009C
     */
    public WEB3FeqPositionManager() 
    {
        m_helper = null;
        m_tradingType = null;
        m_tradingType = ProductTypeEnum.FOREIGN_EQUITY;
        m_helper = new WEB3FeqPositionManagerHelper(m_tradingType);
        super.m_helper = m_helper; 
    }
    
    /**
     * (get�ۗL���Y) <BR>
     * �ۗL���Y�e�[�u������A <BR>
     * �ȉ��̏����ɊY������ۗL���Y���擾���ԋp����B <BR>
     *  <BR>
     * �����h�c�F ����.�����h�c  <BR>
     * �⏕�����h�c�F ����.�⏕�����h�c  <BR>
     * �����h�c�F ����.�����h�c  <BR>
     * �ŋ敪�F ����.�ŋ敪  <BR>
     * �~�j���敪�F 0�FDEFAULT�i�~�j���ȊO�j <BR>
     * @@param l_lngAccountId - (����ID) <BR>
     * ����ID
     * 
     * @@param l_lngSubAccountId - (�⏕����ID)
     * 
     * @@param l_lngProductId - (����ID)
     * 
     * @@param l_taxType - (�ŋ敪)
     * 
     * @@return Asset
     * @@throws WEB3BaseException
     * @@roseuid 429466E70186
     */
    public Asset getAsset(
        long l_lngAccountId, 
        long l_lngSubAccountId, 
        long l_lngProductId, 
        TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAsset(long,long,long)";
        log.entering(STR_METHOD_NAME);
        
        FeqAssetImpl l_asset = null;
        try
        {
            QueryProcessor l_qp = null;
            List l_lisRows = null;
            //�ȉ��̏����ɊY������ۗL���Y���擾���ԋp����B
            
            l_qp = Processors.getDefaultProcessor();
            String l_strWhere = "account_id = ? and sub_account_id = ? and product_id = ? and tax_type = ? and mini_stock_div = ?";
            Object[] l_objBinds = new Object[5];
            
            //�����h�c�F ����.�����h�c
            l_objBinds[0] = new Long(l_lngAccountId);
            
            //�⏕�����h�c�F ����.�⏕�����h�c
            l_objBinds[1] = new Long(l_lngSubAccountId);
            
            //�����h�c�F ����.�����h�c
            l_objBinds[2] = new Long(l_lngProductId);
            
            //�ŋ敪�F ����.�ŋ敪
            l_objBinds[3] = l_taxType;
            
            //�~�j���敪�F 0�FDEFAULT�i�~�j���ȊO�j
            l_objBinds[4] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            l_lisRows = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_objBinds);

            int l_intSize = 0;
            if (l_lisRows != null && !l_lisRows.isEmpty())
            {
                l_intSize = l_lisRows.size();
            }
            if (l_intSize > 0)
            {
                l_asset = new FeqAssetImpl((AssetRow)l_lisRows.get(0));
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_asset;
    }
    
    /**
     * (get���b�N���̎��Y�ڍ�) <BR>
     * ���b�N���̎��Y�ڍ׍s�i�FLockedAssetDetailsParams�j���擾����B <BR>
     *  <BR>
     * �P�j�@@�ۗL���Y�擾 <BR>
     * �@@this.get�ۗL���Y()�ɂĕۗL���Y���擾����B <BR>
     *  <BR>
     * �@@[get�ۗL���Y()�Ɏw�肷�����] <BR>
     * �@@�����h�c�F�@@�����h�c <BR>
     * �@@�⏕�����h�c�F�@@�⏕�����h�c  <BR>
     * �@@�����h�c�F�@@�����h�c  <BR>
     * �@@�ŋ敪�F�@@�ŋ敪  <BR>
     *  <BR>
     * �Q�j�@@���b�N���̎��Y�ڍ׍s�i�FLockedAssetDetailsParams�j�擾 <BR>
     * �@@�P�j�Ŏ擾�����ۗL���Y.getAssetId()�ɊY������s�� <BR>
     * �@@�@@�@@���b�N���̎��Y�ڍׂ��擾���ԋp����B <BR>
     * @@param l_lngAccountId - (����ID)
     * 
     * @@param l_lngSubAccountId - (�⏕����ID)
     * 
     * @@param l_lngProductId - (����ID)
     * 
     * @@param l_taxType - (�ŋ敪)
     * 
     * @@return LockedAssetDetailsParams
     * @@throws WEB3BaseException
     * @@roseuid 42A9477F00DC
     */
    public LockedAssetDetailsParams getLockedAssetDetails(
        long l_lngAccountId, 
        long l_lngSubAccountId, 
        long l_lngProductId, 
        TaxTypeEnum l_taxType)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLockedAssetDetails(long,long,long,TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�ۗL���Y�擾
        FeqAssetImpl l_asset = null;
        l_asset = (FeqAssetImpl)this.getAsset(l_lngAccountId, l_lngSubAccountId,l_lngProductId, l_taxType);
        
        if (l_asset == null)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�Q�j�@@���b�N���̎��Y�ڍ׍s�i�FLockedAssetDetailsParams�j�擾
        //�j�Ŏ擾�����ۗL���Y.getAssetId()�ɊY������s�����b�N���̎��Y�ڍׂ��擾���ԋp����B
        long l_assetId = l_asset.getAssetId();
        
        LockedAssetDetailsRow l_assetDetailsRow = null;
        try
        {
            l_assetDetailsRow = LockedAssetDetailsDao.findRowByAssetId(l_assetId);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        if (l_assetDetailsRow != null)
        {
            log.exiting(STR_METHOD_NAME);
            return new LockedAssetDetailsParams (l_assetDetailsRow);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    
    }
    
    /**
     * (update�g�����U�N�V����) <BR>
     * �萔�����v�Z�i������j�����{���A <BR>
     * �g�����U�N�V�����f�[�^���X�V����B <BR>
     * <BR>
     * �O�������|�W�V�����w���p�[.update�g�����U�N�V����()�� <BR>
     * �Ϗ��ideligate�j����B<BR>
     * @@param l_lngOrderUnitId - (�����P�ʂh�c)
     * @@param l_blnIsCancel - (is���) <BR>
     * ��������̔��� <BR>
     *  <BR>
     * ture�F����� <BR>
     * false�F��� <BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A808210269
     */
    public void updateTransaction(long l_lngOrderUnitId, boolean l_blnIsCancel) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction(long,boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������|�W�V�����w���p�[.update�g�����U�N�V����()�ɈϏ��ideligate�j����B
        m_helper.updateTransaction(l_lngOrderUnitId, l_blnIsCancel);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update�g�����U�N�V����) <BR>
     * �ꊇ�ΏۂƂȂ�g�����U�N�V�����ɂ��āA <BR>
     * �萔�����v�Z�i������j�����{���A�g�����U�N�V�����f�[�^���X�V����B  <BR>
     *  <BR>
     * �萔�����v�Z�́A�ŏ��O�݂̎萔�����v�Z���s���A���̌�A�~�݂̎萔�����v�Z���s���B<BR>
     *  <BR>
     * �O�������|�W�V�����w���p�[.update�g�����U�N�V����()�ɈϏ��ideligate�j����B <BR>
     * @@param l_lngOrderUnitIds - (�����P�ʂh�c�̈ꗗ)
     * @@param l_datBaseDate - (���)
     * @@param l_blnIsNetting - (is�l�b�e�B���O����)
     * @@throws WEB3BaseException
     * @@roseuid 42B672630370
     */
    public void updateTransaction(
        long[] l_lngOrderUnitIds,
        Date l_datBaseDate,
        boolean l_blnIsNetting) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction(long[],Date,boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������|�W�V�����w���p�[.update�g�����U�N�V����()�ɈϏ��ideligate�j����B
        m_helper.updateTransaction(l_lngOrderUnitIds, l_datBaseDate, l_blnIsNetting);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get���t�\�ۗL���Y�ꗗ) <BR>
     * �w������Ɉ�v����ۗL���Y�I�u�W�F�N�g�̈ꗗ��ԋp����B <BR>
     *  <BR>
     * �P�j�@@����������ǉ�����B <BR>
     *  <BR>
     * �P�|�P�j�@@�p�����[�^.��������������̐擪�ɁA <BR>
     * �@@"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ?  <BR>
     * �@@and �~�j���敪 = ? and ���� > ? " <BR>
     * �@@��t������B <BR>
     *  <BR>
     * �P�|�Q�j�@@�p�����[�^.���������f�[�^�R���e�i�̐擪�ɁA <BR>
     * �@@�@@�@@�@@�@@�ȉ��̃p�����[�^���X�g��ǉ�����B <BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�⏕����.����ID <BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�⏕����.�⏕����ID <BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�����^�C�v <BR>
     * �@@�@@�@@�@@�@@�@@�E"0�FDEFAULT(�~�j���łȂ�)" <BR>
     * �@@�@@�@@�@@�@@�@@�E0 <BR>
     *  <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A <BR>
     *      �ۗL���Y�I�u�W�F�N�g��List���擾����B <BR>
     *  <BR>
     * �@@�@@�@@doFindAllQuery(�ۗL���YRow.TYPE <BR>
     *                      �P�|�P�j�̌�������������, <BR>
     *                      �p�����[�^.�\�[�g����, <BR>
     *                      null, <BR>
     *                      �P�|�Q�j�̌��������f�[�^�R���e�i) <BR>
     *  <BR>
     * �@@�@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     *  <BR>
     * �S�j�@@�������ʂ�ԋp����B <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (�����^�C�v)
     * @@param l_strQueryString - (��������������)
     * @@param l_queryContainer - (���������f�[�^�R���e�i)
     * @@param l_strSortCond - (�\�[�g����)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 42A81D2C03E5
     */
    public List getSellPossibleAssetList(
        WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strQueryString, 
        String[] l_queryContainer, 
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSellPossibleAssetList(WEB3GentradeSubAccount,ProductTypeEnum,String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            QueryProcessor l_qp = null;
            List l_lisRows = null;
            
            //�ȉ��̏����ɊY������ۗL���Y���擾���ԋp����B
            l_qp = Processors.getDefaultProcessor();
            
            //�P�j�@@����������ǉ�����B
            String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? and mini_stock_div = ? and quantity > ?";
            if (l_strQueryString != null && l_strQueryString.length() > 0)
            {
                l_strWhere += l_strQueryString;
            }
            int l_intCondParamLen = 0;
            Object[] l_objBinds = null;
            if (l_queryContainer != null && l_queryContainer.length != 0)
            {
                l_intCondParamLen = l_queryContainer.length;
                l_objBinds = new Object[5 + l_intCondParamLen];
            }
            else
            {
                l_objBinds = new Object[5];
            }
            //�P�|�Q�j�@@�p�����[�^.���������f�[�^�R���e�i�̐擪�ɁA�ȉ��̃p�����[�^���X�g��ǉ�����B
            
            
            //�����h�c�F ����.�����h�c
            l_objBinds[0] = new Long(l_subAccount.getAccountId());
            
            //�⏕�����h�c�F ����.�⏕�����h�c
            l_objBinds[1] = new Long(l_subAccount.getSubAccountId());
            
            //�����h�c�F ����.�����h�c
            l_objBinds[2] = l_productType;
            
            //"0�FDEFAULT(�~�j���łȂ�)"
            l_objBinds[3] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            l_objBinds[4] = new Double(0.0D);
            for (int i = 0; i < l_intCondParamLen; i++)
            {
                l_objBinds[5 + i] = l_queryContainer[i];
            }
            
            //�R�j�ۗL���Y�I�u�W�F�N�g��List���擾����B
            l_lisRows = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_strSortCond, null, l_objBinds);
            
            //�S�j�@@�������ʂ�ԋp����B
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;

        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

    }
    
    /**
     * (get�ۗL���Y�ꗗ) <BR>
     * �w������Ɉ�v����ۗL���Y�I�u�W�F�N�g�̈ꗗ��ԋp����B <BR>
     *  <BR>
     * �P�j�@@����������ǉ�����B <BR>
     *  <BR>
     * �P�|�P�j�@@�p�����[�^.��������������̐擪�ɁA <BR>
     * �@@"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ?  <BR>
     * �@@and �~�j���敪 = ? and (���� + ���t�s�\����) > ? " <BR>
     * �@@��t������B <BR>
     *  <BR>
     * �P�|�Q�j�@@�p�����[�^.���������f�[�^�R���e�i�̐擪�ɁA <BR>
     * �@@�@@�@@�@@�@@�ȉ��̃p�����[�^���X�g��ǉ�����B <BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�⏕����.����ID <BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�⏕����.�⏕����ID <BR>
     * �@@�@@�@@�@@�@@�@@�E�p�����[�^.�����^�C�v <BR>
     * �@@�@@�@@�@@�@@�@@�E"0�FDEFAULT(�~�j���łȂ�)" <BR>
     * �@@�@@�@@�@@�@@�@@�E0 <BR>
     *  <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A <BR>
     * �@@�@@�@@�ۗL���Y�I�u�W�F�N�g��List���擾����B <BR>
     *  <BR>
     * �@@�@@�@@doFindAllQuery(�ۗL���YRow.TYPE <BR>
     *                      �P�|�P�j�̌�������������, <BR>
     *                      �p�����[�^.�\�[�g����, <BR>
     *                      null, <BR>
     *                      �P�|�Q�j�̌��������f�[�^�R���e�i) <BR>
     *  <BR>
     * �@@�@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     *  <BR>
     * �S�j�@@�������ʂ�ԋp����B <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (�����^�C�v)
     * @@param l_strQueryString - (��������������)
     * @@param l_queryContainer - (���������f�[�^�R���e�i)
     * @@param l_strSortCond - (�\�[�g����)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 42A81D950348
     */
    public List getAssetList(WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strQueryString, 
        String[] l_queryContainer, 
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssetList(WEB3GentradeSubAccount,ProductTypeEnum,String,String,String)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            QueryProcessor l_qp = null;
            List l_lisRows = null;
            //�ȉ��̏����ɊY������ۗL���Y���擾���ԋp����B
            l_qp = Processors.getDefaultProcessor();
            String l_strWhere = 
                "account_id = ? and sub_account_id = ? and product_type = ? and mini_stock_div = ? and (quantity + quantity_cannot_sell) > ?";
            if (l_strQueryString != null && l_strQueryString.length() > 0)
            {
                l_strWhere += l_strQueryString;
            }
            
            Object[] l_objBinds = null;
            
            int l_intCondParamLen = 0;
            if (l_queryContainer != null)
            {
                l_intCondParamLen = l_queryContainer.length;
                l_objBinds = new Object[5 + l_intCondParamLen];
            }
            else
            {
                l_objBinds = new Object[5];
            }

            //�����h�c�F ����.�����h�c
            l_objBinds[0] = new Long(l_subAccount.getAccountId());
            //�⏕�����h�c�F ����.�⏕�����h�c
            l_objBinds[1] = new Long(l_subAccount.getSubAccountId());
            //�����h�c�F ����.�����h�c
            l_objBinds[2] = l_productType;
            //"0�FDEFAULT(�~�j���łȂ�)"
            l_objBinds[3] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            l_objBinds[4] = new Double(0.0D);
            for (int i = 0; i < l_intCondParamLen; i++)
            {
                l_objBinds[5 + i] = l_queryContainer[i];
            }
            l_lisRows = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_strSortCond, null, l_objBinds);
            
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;

        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
    }
    
    /**
     * (create�g�����U�N�V�����s) <BR>
     * �����P�ʍs�^���s���A�g�����U�N�V�����s�I�u�W�F�N�g�𐶐�����B <BR>
     *  <BR>
     * �O�������|�W�V�����w���p�[.create�g�����U�N�V����()�ɈϏ��ideligate�j����B <BR>
     * @@param l_feqOrderExecution - (���) <BR>
     * ���I�u�W�F�N�g
     * @@param l_feqOrderUnitRow - (�����P�ʍs)
     * �����P�ʍs�I�u�W�F�N�g
     * 
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams
     * @@throws WEB3BaseException
     * @@roseuid 42B25F6C0394
     */
    public FeqFinTransactionParams createTransactionParams(
        FeqOrderExecution l_feqOrderExecution, 
        FeqOrderUnitRow l_feqOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createTransactionParams(FeqOrderExecution,FeqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        //�O�������|�W�V�����w���p�[.create�g�����U�N�V����()�ɈϏ��ideligate�j����B
        FeqFinTransactionParams l_params = m_helper.createTransactionParams(l_feqOrderExecution, l_feqOrderUnitRow);
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (get������薾��ForOrderUnit) <BR>
     * �igetFinTransactionForOrderUnit�j <BR>
     * ���꒍���Ɋ֘A��������̎�����薾�ׂ����X�g�Ŏ擾����B  <BR>
     *  <BR>
     * �O�������|�W�V�����w���p�[.getPersistenceManager().get������薾�� <BR>
     * ForOrderUnit()�ɈϏ��ideligate�j����B <BR>
     *  <BR>
     * [get������薾��ForOrderUnit()�Ɏw�肷�����] <BR>
     * �����P�ʂh�c�F�@@�����P�ʂh�c <BR>
     * @@param l_lngOrderUnitId - (�����P�ʂh�c)
     * @@return List
     * @@throws DataException
     * @@throws RuntimeSystemException
     * @@roseuid 42B2696C03D2
     */
    public List getFinTransactionForOrderUnit(long l_lngOrderUnitId) throws DataException, RuntimeSystemException 
    {
        final String STR_METHOD_NAME = "getFinTransactionForOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������|�W�V�����w���p�[.getPersistenceManager().get������薾��ForOrderUnit
        //�i�����́j()�ɈϏ��ideligate�j����B
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        
        try
        {
            List l_lisOrderUnit = l_dataManager.getFinTransactionForOrderUnit(l_lngOrderUnitId);
            log.exiting(STR_METHOD_NAME);
            return l_lisOrderUnit;  
        }
        catch (WEB3BaseException l_ex)        
        {
            log.error(l_ex.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }
    }
    
    /**
     * (get������薾��ForOrderUnit�i�����́j) <BR>
     * �����Ɋ֘A��������̎�����薾�ׂŁA <BR>
     * �����͂̍s�����X�g�Ŏ擾����B  <BR>
     *  <BR>
     * �O�������|�W�V�����w���p�[.getPersistenceManager().get������薾�� <BR>
     * ForOrderUnit�i�����́j()�ɈϏ��ideligate�j����B <BR>
     *  <BR>
     * [get������薾��ForOrderUnit()�Ɏw�肷�����] <BR>
     * �����P�ʂh�c�F�@@�����P�ʂh�c <BR>
     * @@param l_lngOrderUnitId - �����P�ʂh�c
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 42B28D310255
     */
    public List getTradeDetailsForOrderUnit(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeDetailsForOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������|�W�V�����w���p�[.getPersistenceManager().get������薾��ForOrderUnit
        //�i�����́j()�ɈϏ��ideligate�j����B
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        List l_lisOrderUnit = l_dataManager.getFinTransactionForOrderUnitExecInput(l_lngOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisOrderUnit;
    }
    
    /**
     * (applyTo�ۗL���Y�c��) <BR>
     * �ۗL���Y���X�V����B <BR>
     *  <BR>
     * �|�W�V�����w���p�[.applyTo�ۗL���Y�c��Deligate() <BR>
     * �ɈϏ��ideligate�j����B <BR>
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@return List
     * @@throws DataException
     * @@throws RuntimeSystemException
     * @@roseuid 42B7B1D600FA
     */
    public List applyToAssetBalance(FeqFinTransactionParams l_feqFinTransactionParams) 
        throws DataException, RuntimeSystemException 
    {
        final String STR_METHOD_NAME = "applyToAssetBalance(FeqFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        //�|�W�V�����w���p�[.applyTo�ۗL���Y�c��Deligate()�ɈϏ��ideligate�j����B
        List l_lstAssetBalance = m_helper.applyToAssetBalanceDeligate(l_feqFinTransactionParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_lstAssetBalance;
    }
    
    /**
     * (notify�ڋq����) <BR>
     * �ڋq���薾�ׁC�⏕�������X�V����B <BR>
     *  <BR>
     * �|�W�V�����w���p�[.notify�ڋq����Deligate()�ɈϏ��ideligate�j����B <BR>
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@roseuid 42B7B33A0196
     */
    public void notifyAccountCash(FeqFinTransactionParams l_feqFinTransactionParams)
    {
        final String STR_METHOD_NAME = "notifyAccountCash(FeqFinTransactionParams)";
        log.entering(STR_METHOD_NAME);        
        
        //�|�W�V�����w���p�[.notify�ڋq����Deligate()�ɈϏ��ideligate�j����B
        m_helper.notifyCashDeligate(l_feqFinTransactionParams);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update���b�N������)<BR>
     * <BR>
     * �y���b�N���̎��Y�ڍ׃e�[�u���z�̃��b�N�����ʂ̒������s���B <BR>
     * �iupdateLockedQuantity(long accountId, <BR>
     *                        long subAccountId, <BR>
     *                        long orderUnitId, <BR>
     *                        long productId, <BR>
     *                        double lockedQtyToBeAdjusted)<BR>
     * �̃I�[�o�[���C�h�j <BR>
     * �P�j�@@����.lockedQtyToBeAdjusted��0�Ɠ������ꍇ�́A�����������ɂ��̂܂�return����B<BR>
     * if(Utils.Double.isZero(lockedQtyToBeAdjusted))<BR>
     * �@@�@@�@@�@@�@@return;<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.getOrderUnit(����.�����P��ID)�ɂ��A<BR>
     * �����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�@@this.get�ۗL���Y(����ID, �⏕����ID, ����ID, �ŋ敪)���R�[�����A<BR>
     *         �ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �S)�@@�ۗL���Y�I�u�W�F�N�g.assetid���L�[�ɂ��āALocked_Asset_Details�e�[�u������������B<BR>
     * LockedAssetDetailsRow lockedAssetRow = LockedAssetDetailsDao.findRowByPk(assetId);<BR>
     * <BR>
     * �S-�P)�@@ �Y��assetid�L�[��LockedAssetDetailsRow�����݂��Ȃ��ꍇ�AInsert���s���B<BR>
     * �|�V�K���b�N�����ʃI�u�W�F�N�g�iLockedAssetDetailsParams�j�̃v���p�e�B��ݒ肷��B<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setAssetId(assetId);<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setAccountId(����.accountId);<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setSubAccountId(����.subAccountId);<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setCreatedTimestamp(Utils.getSystemTimestamp());<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setLastUpdatedTimestamp(Utils.getSystemTimestamp());<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setLockedQuantity�i����.lockedQtyToBeAdjusted�j;<BR>
     * �|DB�X�VQueryProcessor�Ńf�[�^�x�[�X�ɐV�K�s��ǉ�����B<BR>
     * �@@�@@QueryProcessor qp = Processors.getDefaultProcessor();<BR>
     * �@@�@@qp.doInsertQuery(�V�K���b�N�����ʃI�u�W�F�N�g);<BR>
     * <BR>
     * �S-�Q)�@@ �Y��assetid�L�[��LockedAssetDetailsRow�����łɑ��݂����ꍇ�AUpdate���s���B<BR>
     * �|�V���b�N�����ʂ��Z�o����B<BR>
     * �@@�@@  �V���b�N������=lockedAssetRow.getLockedQuantity()+����.lockedQtyToBeAdjusted;<BR>
     * �|�Z�o�������ʂ̐V���b�N�����ʂ��[����������B�i0�Ɠ������ꍇ�A0.0D���Z�b�g����j<BR>
     * �@@�@@�@@if(Utils.Double.isZero(newLockedQty))�@@�V���b�N������=0.0D;<BR>
     * <BR>
     * �|�X�V���b�N�����ʃI�u�W�F�N�g�iLockedAssetDetailsParams�j�̃v���p�e�B��ݒ肷��B<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setAssetId(assetId);<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setAccountId(����.accountId);<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setSubAccountId(����.subAccountId);<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setLastUpdatedTimestamp(Utils.getSystemTimestamp());<BR>
     * �@@�@@���b�N�����ʃI�u�W�F�N�g.setLockedQuantity�i�V���b�N�����ʁj;<BR>
     * �|DB�X�VQueryProcessor�Ńf�[�^�x�[�X�ɊY���s���X�V����B<BR>
     * �@@�@@QueryProcessor qp = Processors.getDefaultProcessor();�@@<BR>
     * �@@�@@qp.doUpdateQuery(�X�V���b�N�����ʃI�u�W�F�N�g);<BR>
     * @@param l_lngAccountId
     * @@param l_lngSubAccountId
     * @@param l_lngOrderUnitId
     * @@param l_lngProductId
     * @@param l_dblAdjustQuantity
     * @@roseuid 413BED5200DD
     */
    
    public void updateLockedQuantity(long l_lngAccountId, long l_lngSubAccountId, long l_lngOrderUnitId, long l_lngProductId, double l_dblAdjustQuantity)
    {
        final String STR_METHOD_NAME = "updateLockedQuantity(long, long, long, long, double)";
        log.entering(STR_METHOD_NAME);
    
        //�@@����.lockedQtyToBeAdjusted��0�Ɠ������ꍇ�́A�����������ɂ��̂܂�return����B
        if (GtlUtils.Double.isZero(l_dblAdjustQuantity))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        try
        {
            //�擾�g�����������}�l�[�W��
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingModule.getOrderManager();
            
            //�����P�ʃI�u�W�F�N�g���擾����
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            
            //�擾�ۗL���Y
            TaxTypeEnum l_taxTypeEnum = null;
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
            //�ŋ敪�Ƃ��Ē����P��.�ŋ敪���w�肷��B
            l_taxTypeEnum = l_orderUnitRow.getTaxType();
        
            Asset l_asset = this.getAsset(
                    l_lngAccountId, l_lngSubAccountId, l_lngProductId, l_taxTypeEnum);
        
            if (l_asset == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y������ۗL���Y������܂���:["
                    + l_lngAccountId + ","
                    + l_lngSubAccountId + ","
                    + l_taxTypeEnum + "]");
            }
            long l_lngAssetID = l_asset.getAssetId();
            
            LockedAssetDetailsParams l_feqLockedAssetDetailsParams = (LockedAssetDetailsParams) LockedAssetDetailsDao.findRowByAssetId(l_lngAssetID);
        
            if (l_feqLockedAssetDetailsParams == null)
            {
                l_feqLockedAssetDetailsParams = new LockedAssetDetailsParams();
                l_feqLockedAssetDetailsParams.setAssetId(l_lngAssetID);
                l_feqLockedAssetDetailsParams.setAccountId(l_lngAccountId);
                l_feqLockedAssetDetailsParams.setSubAccountId(l_lngSubAccountId);
                l_feqLockedAssetDetailsParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_feqLockedAssetDetailsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_feqLockedAssetDetailsParams.setLockedQuantity(l_dblAdjustQuantity);
        
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_feqLockedAssetDetailsParams);
            }
            else
            {
                double l_dblNewLockedQuantity = l_dblAdjustQuantity + l_feqLockedAssetDetailsParams.getLockedQuantity();
                LockedAssetDetailsParams l_lockedAssetDetailsParams = new LockedAssetDetailsParams();
                GtlUtils.copyRow2Params(l_feqLockedAssetDetailsParams, l_lockedAssetDetailsParams);
                l_lockedAssetDetailsParams.setAssetId(l_lngAssetID);
                l_lockedAssetDetailsParams.setAccountId(l_lngAccountId);
                l_lockedAssetDetailsParams.setSubAccountId(l_lngSubAccountId);
                l_lockedAssetDetailsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_lockedAssetDetailsParams.setLockedQuantity(l_dblNewLockedQuantity);
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_lockedAssetDetailsParams);
            }
        }
        catch (NotFoundException l_nfe)
        {
            String l_strMessage = "���b�N�����ʂ�update�ł��܂���";
            log.error(l_strMessage, l_nfe);
            throw new RuntimeSystemException(l_strMessage, l_nfe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "���b�N�����ʂ�update�ł��܂���";
            log.error(l_strMessage, l_dne);
            throw new RuntimeSystemException(l_strMessage, l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "���b�N�����ʂ�update�ł��܂���";
            log.error(l_strMessage, l_dqe);
            throw new RuntimeSystemException(l_strMessage, l_dqe);
        }
        catch (WEB3BaseException l_be)
        {
            String l_strMessage = "���b�N�����ʂ�update�ł��܂���";
            log.error(l_strMessage, l_be);
            throw new RuntimeSystemException(l_strMessage, l_be);
        }
        log.exiting(STR_METHOD_NAME);
     }

    /**
     * (update�ۗL���Y) <BR>
     * �ۗL���Y���X�V����B <BR>
     *  <BR>
     * �|�W�V�����w���p�[.update�ۗL���Y() <BR>
     * �ɈϏ��ideligate�j����B <BR>
     * @@param l_feqOrderUnit - (�O�����������P��) <BR> 
     * @@throws WEB3BaseException
     * @@roseuid 42B7B1D600FA
     */
    public void updateAsset(WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "updateAsset(WEB3FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //�|�W�V�����w���p�[.update�ۗL���Y()�ɈϏ��ideligate�j����B
        m_helper.updateAsset(l_feqOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
        return;
    }

}
@
