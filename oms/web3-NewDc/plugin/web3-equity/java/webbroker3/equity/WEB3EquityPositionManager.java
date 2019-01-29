head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPositionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����|�W�V�����}�l�[�W��(WEB3EquityPositionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 �������F(SRA) �V�K�쐬
Revesion History : 2007/04/25 �Ӑ�(���u) ���f��1138,1141
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypePositionManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.equity.define.WEB3EquitySettlementStateDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.equity.define.WEB3MarginClosingStatusTypeDef;
import webbroker3.equity.message.WEB3MarginBalanceReferenceDetailUnit;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����|�W�V�����}�l�[�W���j�B<BR>
 * <BR>
 * �����p�̃|�W�V�����}�l�[�W���B <BR>
 * �iEqTypePositionManagerImpl�̃T�u�N���X�j
 * @@ author ������ 
 * @@ version 1.0
 */
public class WEB3EquityPositionManager extends EqTypePositionManagerImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityPositionManager.class);

    /**
     * @@roseuid 409CBF120148
     */
    public WEB3EquityPositionManager()
    {
        super.m_tradingType = ProductTypeEnum.EQUITY;
        super.m_helper = new WEB3EquityPositionManagerHelper(super.m_tradingType);
    }

    /**
     * �i�����菈���j<BR>
     * <BR>
     * �����菈�����s���B<BR>
     * �i* �g���|�W�V�����w���p�[.�����菈��( )�ɈϏ�����B)<BR>
     * <BR>
     * @@param l_lngOrderUnitId - �����P��ID<BR>
     * @@param l_lngAssetId - ���YID<BR>
     * @@param l_isExecuteCancel - is�����<BR>
     * ��������ǂ����̃t���O�B
     * @@throws WEB3BaseException
     */
    public void shareContractExecution(
        long l_lngOrderUnitId,
        long l_lngAssetId,
        boolean l_isExecuteCancel)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "shareContractExecution(long, long, boolean)";
        log.entering(STR_METHOD_NAME);
        ((WEB3EquityPositionManagerHelper)m_helper).shareContractExecution(l_lngOrderUnitId, l_lngAssetId, l_isExecuteCancel);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �iget�ۗL���Y�j<BR>
     * <BR>
     * �igetAsset(����ID, �⏕����ID, ����ID)�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �y�ۗL���Y�e�[�u���z����A�ȉ��̏������w�肵�Y������ۗL���Y���擾���ԋp����B<BR>
     * <BR>
     * �����h�c�F�@@�����̌����h�c<BR>
     * �⏕�����h�c�F�@@�����̕⏕�����h�c<BR>
     * �����h�c�F�@@�����̖����h�c<BR>
     * �ŋ敪�F�@@�����̐ŋ敪<BR>
     * �~�j���敪�F�@@0�FDEFAULT�i�~�j���ȊO�j<BR>
     * <BR>
     * @@param l_lngAccountId - ����ID�B <BR>
     * @@param l_lngSubAccountId - �⏕����ID�B <BR>
     * @@param l_lngProductId - ����ID�B <BR>
     * @@param l_taxType - �ŋ敪�B <BR>
     * @@return EqTypeAsset
     * @@throws WEB3BaseException
     * @@roseuid 413BED51035D
     */
    public EqTypeAsset getAsset(long l_lngAccountId, long l_lngSubAccountId, long l_lngProductId, TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAsset(long ,long ,long ,TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityAsset l_asset = null;
        try
        {
            QueryProcessor l_qp = null;
            List l_lisRows = null;
            l_qp = Processors.getDefaultProcessor();
            String l_strWhere = "account_id=? and sub_account_id=? and product_id=? and tax_type=? and mini_stock_div = ?";
            Object[] l_objBinds = new Object[5];
            l_objBinds[0] = new Long(l_lngAccountId);
            l_objBinds[1] = new Long(l_lngSubAccountId);
            l_objBinds[2] = new Long(l_lngProductId);
            l_objBinds[3] = l_taxType;
            l_objBinds[4] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            l_lisRows = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_objBinds);

            int l_intSize = l_lisRows.size();
            if (l_intSize > 0)
            {
                l_asset = new WEB3EquityAsset((AssetRow)l_lisRows.get(0));
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error("�f�[�^�s�����G���[�B", l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dfe.getMessage(), l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("�f�[�^�s�����G���[�B", l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
        log.exiting(STR_METHOD_NAME);
        return l_asset;
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
     * �@@�Q�|�P�j�@@�~�j���̏ꍇ(*1)�A�������s�킸�������I������ireturn�j�B <BR>
     *      (*1) �~�j���̔��� <BR>
     *      �����P��.������� = OrderTypeEnum.MINI_STOCK_SELL�i�����~�j�����������j Or <BR>
     *      �����P��.������� = OrderTypeEnum.MINI_STOCK_BUY�i�����~�j�����������j<BR>
     * <BR>
     * �R�j�@@this.get�ۗL���Y(����ID, �⏕����ID, ����ID, �ŋ敪)���R�[�����A<BR>
�@@   *         �ۗL���Y�I�u�W�F�N�g���擾����B<BR>
�@@   * �R�|�P�j�@@�������n�����̏ꍇ(*2)�A�ŋ敪�Ƃ��Ē����P��.�ŋ敪�i�������n�j���w�肷��B<BR>
     * �@@�������n�����łȂ��ꍇ�A�ŋ敪�Ƃ��Ē����P��.�ŋ敪���w�肷��B<BR>
     * �@@(*2)�������n�����̔���<BR>
     * �@@�����P��.�����J�e�S�� = OrderCategEnum.SWAP_MARGIN�i�������n����)<BR>
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
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

            //�����P�ʃI�u�W�F�N�g���擾����
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            //�@@�Q�|�P�j�@@�~�j���̏ꍇ(*1)�A�������s�킸�������I������ireturn�j�B 
            if (OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderUnit.getOrderType()) || OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderUnit.getOrderType()))
            {
                return;
            }
            //�擾�ۗL���Y
            TaxTypeEnum l_taxTypeEnum = null;
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            //�������n�����̏ꍇ(*2)�A�ŋ敪�Ƃ��Ē����P��.�ŋ敪�i�������n�j���w�肷��B
            if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                l_taxTypeEnum = l_orderUnitRow.getSwapTaxType();
            }
            //�������n�����łȂ��ꍇ�A�ŋ敪�Ƃ��Ē����P��.�ŋ敪���w�肷��B
            else
            {
                l_taxTypeEnum = l_orderUnitRow.getTaxType();
            }
            WEB3EquityAsset l_asset =
                (WEB3EquityAsset) this.getAsset(
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

            LockedAssetDetailsParams l_eqtypeLockedAssetDetailsParams = (LockedAssetDetailsParams) LockedAssetDetailsDao.findRowByAssetId(l_lngAssetID);

            if (l_eqtypeLockedAssetDetailsParams == null)
            {
                l_eqtypeLockedAssetDetailsParams = new LockedAssetDetailsParams();
                l_eqtypeLockedAssetDetailsParams.setAssetId(l_lngAssetID);
                l_eqtypeLockedAssetDetailsParams.setAccountId(l_lngAccountId);
                l_eqtypeLockedAssetDetailsParams.setSubAccountId(l_lngSubAccountId);
                l_eqtypeLockedAssetDetailsParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_eqtypeLockedAssetDetailsParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_eqtypeLockedAssetDetailsParams.setLockedQuantity(l_dblAdjustQuantity);

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_eqtypeLockedAssetDetailsParams);
            }
            else
            {
                double l_dblNewLockedQuantity = l_dblAdjustQuantity + l_eqtypeLockedAssetDetailsParams.getLockedQuantity();
                LockedAssetDetailsParams l_lockedAssetDetailsParams = new LockedAssetDetailsParams();
                GtlUtils.copyRow2Params(l_eqtypeLockedAssetDetailsParams, l_lockedAssetDetailsParams);
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
     * (get���t�\�ۗL���Y�ꗗ)<BR>
     * <BR>
     * �w������Ɉ�v���锄�t�\�ȕۗL���Y�I�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * �ۗL���Y�ꗗ�ɗ��p����B<BR>
     * <BR>
     * �P�j�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����������ǉ�����B<BR>
     * <BR>
     * �Q�|�P�j�@@����.��������������̐擪�ɁA<BR>
     * �@@"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ? and �~�j���敪 = 0�FDEFAULT�i�~�j���łȂ��j  and ���� > 0"<BR>
     * �@@��t������B<BR>
     * <BR>
     * �Q�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@������ID�A�⏕����ID�́A�����̕⏕�����I�u�W�F�N�g���ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�������^�C�v�́A�����̖����^�C�v���ݒ肷��B<BR>
     * <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�ۗL���Y�I�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(,�ۗL���YRow.TYPE<BR>
     *                                      �Q�|�P�j�̌�������������,<BR>
     *                                      �����̃\�[�g����,<BR>
     *                                      null,<BR>
     *                                      �Q�|�Q�j�̌��������f�[�^�R���e�i)<BR>
     * <BR>
     * �S�j�@@�������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j<BR>
     * @@param l_strSearchString - (��������������)<BR>
     * ��������������I�u�W�F�N�g<BR>
     * @@param l_searchCondContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i�I�u�W�F�N�g<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 413BED52010F
     */
    public List getSellableAssets(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchString,
        String[] l_searchCondContainers,
        String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCanSellAssets(SubAccount, ProductTypeEnum,String, String[] ,String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //��������������
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? and mini_stock_div = ? and quantity > 0";
        List l_lisResults = null;
        if (l_strSearchString != null && l_strSearchString.length() > 0)
        {
            l_strWhere += " and " + l_strSearchString;
        }
        try
        {
            int l_intCondParamLen = 0;
            if (l_searchCondContainers != null)
            {
                l_intCondParamLen = l_searchCondContainers.length;
            }

            Object[] l_objBinds = new Object[4 + l_intCondParamLen];
            l_objBinds[0] = new Long(l_subAccount.getAccountId());
            l_objBinds[1] = new Long(l_subAccount.getSubAccountId());
            l_objBinds[2] = l_productType;
            l_objBinds[3] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            for (int i = 0; i < l_intCondParamLen; i++)
            {
                l_objBinds[4 + i] = l_searchCondContainers[i];
            }

            QueryProcessor l_qp = null;

            l_qp = Processors.getDefaultProcessor();
            List l_lisAssets = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_strSortCond, null, l_objBinds);
            int l_assetCount = l_lisAssets.size();
            l_lisResults = new ArrayList(l_assetCount);
            for (int i = 0; i < l_assetCount; i++)
            {
                l_lisResults.add(new WEB3EquityAsset((AssetRow)l_lisAssets.get(i)));
            }
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (IllegalStateException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisResults;
    }

    /**
     * EqtypeAssetRow�I�u�W�F�N�g���玑�Y�N���X�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * @@param l_row EqtypeAssetRow�I�u�W�F�N�g
     * @@return Asset
     * @@roseuid 4042EC7C01AC
     */
    public Asset toAsset(Row l_row)
    {
        final String STR_METHOD_NAME = "toAsset";
        log.entering(STR_METHOD_NAME);
        if (l_row == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return new WEB3EquityAsset((AssetRow) l_row);
    }

    /**
     * (get����)<BR>
     * �y�����e�[�u���z����A�����̌���ID�ɊY�����錚���I�u�W�F�N�g�i*�j<BR>
     * ���擾���ԋp����B<BR>
     * �igetContract(����ID)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �i*�j�ԋp�l�FxTrade��EqTypeContractImpl���g�����Ē�`�����A<BR>
     * �����I�u�W�F�N�g�B<BR>
     * @@param l_lngContractId - ����ID�B
     * @@return Contract
     * @@throws NotFoundException
     * @@roseuid 40BBEBC1030B
     */
    public Contract getContract(long l_lngContractId) throws NotFoundException
    {
        final String STR_METHOD_NAME = "getContract";
        log.entering(STR_METHOD_NAME);
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract = new WEB3EquityContract(l_lngContractId);
        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "�����e�[�u�������� error";
            log.error(l_strMessage, l_dqe);
            throw new RuntimeSystemException("�����e�[�u�������� error", l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "�����e�[�u�������� error";
            log.error(l_strMessage, l_dne);
            throw new RuntimeSystemException("�����e�[�u�������� error", l_dne);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }

    /**
     * (adjust�ԍώw����)<BR>
     * �ԍώw����̐��ʂ𒲐�����B <BR>
     * <BR>
     * ���̗l�ɕԍώw����̕ԍϐ��ʂ𒲐�����B <BR>
     * �|���ʌ��̏ꍇ�A�ԍϘA�ԁF�偨���̏��ɁA�������ʕ��� <BR>
     * �@@�ԍϊ������ʂ̂����̖���萔�ʂ��猸�Z���Ă����B <BR>
     * �|���ʑ��̏ꍇ�A�ԍϘA�ԁF������̏��ɁA�������ʕ��� <BR>
     * �@@�����O�̕ԍϊ������ʁi�s��m�F�ϕԍϐ��ʁj������l�Ƃ��� <BR>
     * �@@�������̕ԍϊ������ʁi�ԍϒ������ʁj�ɉ��Z���Ă����B <BR>
     * <BR>
     * �P�j�@@�������ʂ��v�Z����B <BR>
     * <BR>
     * �������� = �i�p�����[�^.�����O���ʁ@@�|�@@�p�����[�^.�����㐔�ʁj <BR>
     * <BR>
     * �Q�j�@@�ԍώw����Ǎ� <BR>
     * �@@�����ԍώw����e�[�u����ǂ݁A<BR>
     * �����ԍώw����s�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * [��������] <BR>
     * �����ԍώw����.�����h�c = �p�����[�^.�����h�c <BR>
     * �����ԍώw����.�⏕�����h�c = �p�����[�^.�⏕�����h�c <BR>
     * �����ԍώw����.�����h�c = �p�����[�^.�����h�c <BR>
     * �����ԍώw����.�����P�ʂh�c = �p�����[�^.�����P�ʂh�c <BR>
     * <BR>
     * ���擾�� ��� �ԍϘA�Ԃ̏����iasc�j <BR>
     * <BR>
     * �R�j�@@�擾���������ԍώw����̕ԍϒ������ʂ𒲐�����B <BR>
     * <BR>
     * �R�|�P�j�@@�������ʁ��O�̏ꍇ�i�ԍϐ��ʂ̊����Ă����炷�ꍇ�j <BR>
     * �@@�������ʂ������ԍώw����̖���萔�ʂɊ��蓖�Ă�B <BR>
     * �@@�擾���������ԍώw����s�I�u�W�F�N�g�ɑS�đ΂��ԍϘA�Ԃ̍~���Ɉȉ��������s���B <BR>
     * �@@�i���z��̌ォ��j <BR>
     * <BR>
     * �@@�R�|�P�|�P�j�@@����萔�ʎZ�o <BR>
     * �@@�@@����萔�� = �����ԍώw����.�ԍϒ������� �| �����ԍώw����.�ԍϖ�萔�� <BR>
     * <BR>
     * �@@�R�|�P�|�Q�j�@@�������ʊ��蓖�� <BR>
     * �@@�@@[�i����萔�� >= �������ʁj�̏ꍇ] <BR>
     * �@@�@@�@@�E�i�����ԍώw����.�ԍϒ������� �| �������ʁj�̒l�������ԍώw����.�ԍϒ������ʂɃZ�b�g����B <BR>
     * �@@�@@�@@��LOOP�������I������B �i�������ʂ̊����Ċ����j <BR>
     * <BR>
     * �@@�@@[�i����萔�� < �������ʁj�̏ꍇ] <BR>
     * �@@�@@�@@�E�����ԍώw����.�ԍϖ�萔�ʂ������ԍώw����.�ԍϒ������ʂɃZ�b�g����B <BR>
     * �@@�@@�@@�E�������� = �������� - ����萔�� <BR>
     * <BR>
     * �R�|�Q�j�����łȂ��ꍇ�i�ԍϐ��ʂ̊����Ă𑝂₷�ꍇ�j <BR>
     * �@@�s��m�F�ϕԍϐ��ʂ�����Ƃ��ĕԍϒ������ʂɒ������ʂ����蓖�Ă�B <BR>
     * <BR>
     * �@@�R�|�Q�|�P�j�������ʂ̕����𔽓]������B <BR>
     * <BR>
     * �@@�擾���������ԍώw����s�I�u�W�F�N�g�ɑS�đ΂��ԍϘA�Ԃ̏����Ɉȉ��������s���B <BR>
     * �@@�i���z��̑O����j <BR>
     * <BR>
     * �@@�@@�R�|�Q�|�Q�|�P�j�@@�����\���ʂ��Z�o���� <BR>
     * �@@�@@�@@�����\���ʁ������ԍώw����.�s��m�F�ϕԍϐ��� �| �����ԍώw����.�ԍϒ������� <BR>
     * <BR>
     * �@@�@@�R�|�Q�|�Q�|�Q�j�����s�\�ȏꍇ�i�����\���ʁ��O�̏ꍇ�j <BR>
     * �@@�@@�@@���̕ԍώw����̏����� <BR>
     * <BR>
     * �@@�@@�R�|�Q�|�Q�|�R�j�@@�ԍϒ������ʂɒ������ʂ������Ă� <BR>
     * �@@�@@�@@[�i�����\���� �� �������ʁj�̏ꍇ] <BR>
     * �@@�@@�@@�@@�E�i�����ԍώw����.�ԍϒ������� �{ �������ʁj�������ԍώw����.�ԍϒ������ʂɃZ�b�g����B <BR>
     * �@@�@@�@@�@@��LOOP�������I������B �i�������ʂ̊����Ċ����j <BR>
     * <BR>
     * �@@�@@�@@[�i�����\���� �� �������ʁj�̏ꍇ] <BR>
     * �@@�@@�@@�@@�E�i�����ԍώw����.�ԍϒ������� �{ �����\���ʁj�������ԍώw����.�ԍϒ������ʂɃZ�b�g����B <BR>
     * �@@�@@�@@�@@�E�������� = �������� - �����\���� <BR>
     * <BR>
     * �S�j�@@�����ԍώw����X�V <BR>
     * �@@������̌����ԍώw����s�I�u�W�F�N�g�̓��e��DB���X�V�iupdate�j����B <BR>
     * <BR>
     * @@param l_lngAccountId - �����h�c
     * @@param l_lngSubAccountId - �⏕����ID
     * @@param l_lngOrderId - ����ID
     * @@param l_lngOrderUnitId - �����P��ID
     * @@param l_dblBeforeChangingQuantity - �����O����
     * @@param l_dblChangedQuantity - �����㐔��
     * @@throws WEB3BaseException
     * @@roseuid 40F7B7F200A3
     */
    public void adjustClosingContractSpecInfo(long l_lngAccountId, long l_lngSubAccountId, long l_lngOrderId, long l_lngOrderUnitId, double l_dblBeforeChangingQuantity, double l_dblChangedQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "adjustCloseContractSpecInfo";
        log.entering(STR_METHOD_NAME);

        try
        {
            // �������ʂ��v�Z����B
            double l_dblAdjustQuantity = l_dblBeforeChangingQuantity - l_dblChangedQuantity;
            
            // �ԍώw����̈ꗗ���擾����B
            String l_strWhere = " account_id = ? and sub_account_id = ? and order_id = ? and order_unit_id = ? ";
            String l_strOrderBy = "closing_serial_no asc";
            Object[] l_objWhereValues = { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(l_lngOrderId), new Long(l_lngOrderUnitId)};
            List l_returnList = new ArrayList();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_returnList = l_processor.doFindAllQuery(EqtypeClosingContractSpecParams.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValues);

            EqtypeClosingContractSpecParams l_contractSpecParams = null;
            
            // �ԍϐ��ʂ̊����Ă����炷�ꍇ
            if (l_dblAdjustQuantity >= 0)
            {
                for (int i = l_returnList.size() - 1; i >= 0  ; i--)
                {
                    l_contractSpecParams = new EqtypeClosingContractSpecParams(
                        (EqtypeClosingContractSpecRow) l_returnList.get(i));
                        
                    double l_dblExecuteQuantity = l_contractSpecParams.getExecutedQuantity();
                    double l_dblQuantity = l_contractSpecParams.getQuantity();
                    double l_dblUnExecutedQuantity = l_dblQuantity - l_dblExecuteQuantity;
     
                    if (l_dblUnExecutedQuantity >= l_dblAdjustQuantity)
                    {
                        l_contractSpecParams.setQuantity(l_dblQuantity - l_dblAdjustQuantity);
                        l_processor.doUpdateQuery(l_contractSpecParams);
                        break;
                    }
                    else
                    {
                        l_dblAdjustQuantity = l_dblAdjustQuantity - l_dblUnExecutedQuantity;
                        l_contractSpecParams.setQuantity(l_dblExecuteQuantity);
                    }
                    l_processor.doUpdateQuery(l_contractSpecParams);
                }
            }
            // �ԍϐ��ʂ̊����Ă𑝂₷�ꍇ
            else
            {
                l_dblAdjustQuantity = - l_dblAdjustQuantity;
                for (int i = 0; i < l_returnList.size(); i++)
                {
                    l_contractSpecParams = new EqtypeClosingContractSpecParams(
                        (EqtypeClosingContractSpecRow) l_returnList.get(i));
                        
                    double l_dblConfirmedQuantity = l_contractSpecParams.getConfirmedQuantity();
                    double l_dblQuantity = l_contractSpecParams.getQuantity();
                    double l_dblAssignableQuantity = l_dblConfirmedQuantity - l_dblQuantity;
                    
                    if (l_dblAssignableQuantity <= 0) continue;
                    
                    if (l_dblAssignableQuantity >= l_dblAdjustQuantity)
                    {
                        l_contractSpecParams.setQuantity(l_dblQuantity + l_dblAdjustQuantity);
                        l_processor.doUpdateQuery(l_contractSpecParams);
                        break;
                    }
                    else
                    {
                        l_contractSpecParams.setQuantity(l_dblQuantity + l_dblAssignableQuantity);
                        l_dblAdjustQuantity = l_dblAdjustQuantity - l_dblAssignableQuantity;
                        l_processor.doUpdateQuery(l_contractSpecParams);
                    }
                }
            }

        }
        catch (DataQueryException l_dqe)
        {
            String l_strMessage = "�����ԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_dqe);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            String l_strMessage = "�����ԍώw����e�[�u��������  error";
            log.error(l_strMessage, l_dne);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
    }

    /**
     * (create���ϒ��������)<BR>
     * ���ϒ��̌�������1���׍쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�c���jcreate���ϒ��������v�Q�ƁB<BR>
     * @@param l_contract - (����)<BR>
     * ���������쐬����Ώۂ̌���
     * @@return WEB3MarginContractInfo
     * @@throws WEB3BaseException
     * @@roseuid 40EB4E960071
     */
    public WEB3MarginContractInfo createClosingMarginContractInfo(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosingMarginContractInfo";
        log.entering(STR_METHOD_NAME);

        //�M�p����������I�u�W�F�N�g�𐶐�����B
        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        WEB3MarginContractInfo l_marginContractInfo = new WEB3MarginContractInfo();

        //���ϒ��������ʂ��擾����B
        double l_dblLockedQuantity = l_contract.getLockedQuantity();

        //����ID���擾����B
        long l_lngContractId = l_contract.getContractId();

        //���ϒ������̒����P�ʂ̈ꗗ���擾����B
		EqTypeClosingContractSpec[] l_constractSpec = this.getContractOrderingClosingContractSpecInfo(l_lngContractId);
        
        double l_dblEvaluateIncome = 0;
        double l_dblEvaluateIncomeWithCost = 0;
        //�����P�ʗv�f���Ƃ�Loop����
        int l_intOrderUnitLength = 0;
        if (l_constractSpec != null)
        {
            l_intOrderUnitLength = l_constractSpec.length;
        }
        
		//�Y�����������̎������擾����B
		double l_dblCurrentPrice = 0D;
        try
        {
			l_dblCurrentPrice = this.getContractCurrentPrice(l_contract);
        }
        catch (WEB3BaseException l_be)
        {
			log.error("�����擾�G���[: " + STR_METHOD_NAME, l_be);
        }
		
		if (l_dblCurrentPrice != 0D)
		{
	        for (int i = 0; i < l_intOrderUnitLength; i++)
	        {
	            //�������ʂ��擾����B
	            double l_dblQuantity = l_constractSpec[i].getConfirmedQuantity();
	            if (Double.isNaN(l_dblQuantity))
	            {
	                l_dblQuantity = l_constractSpec[i].getQuantity();
	            }
	            
	            //�ԍϖ�萔�ʂ��擾����B
	            double l_dblExecQuantity = l_constractSpec[i].getExecutedQuantity();
				if (Double.isNaN(l_dblExecQuantity))
	            {
					l_dblExecQuantity = 0d;
	            }
	            
				// �Y���ԍώw���񕪂̌��ϒ����ʂ��擾����B
				double l_dblOrderingQuantity = l_dblQuantity - l_dblExecQuantity;
                
				//�]�����v���擾����B
				l_dblEvaluateIncome += l_contract.getAppraisalProfitOrLoss(
                    l_dblCurrentPrice, l_dblOrderingQuantity);
	        }
			//(*)�M�p����������I�u�W�F�N�g�Ɉȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
			//�]�����v�F�@@(����.get�]�����v( )�̖߂�l�̍��v�l)
			l_marginContractInfo.evaluationIncome = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
		}
		else
		{
			//�������擾�ł��Ȃ������ꍇ��null���Z�b�g
			l_marginContractInfo.evaluationIncome = null;
			l_marginContractInfo.takeExpensesOffEvaluationIncome = null;
		}

        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //(*)�M�p����������I�u�W�F�N�g�Ɉȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //ID�F�@@����.����ID
        l_marginContractInfo.id = "" + l_contract.getContractId();

        //�s��R�[�h�F�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(����.����ID).�s��R�[�h
        WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        try
        {
            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct) l_equityProductManager.getProduct(l_eqtypeContractRow.getProductId());

            //�����R�[�h�F�@@��������(**).�����R�[�h
            //�������F�@@��������(**).������
            l_marginContractInfo.productCode = l_equityProduct.getProductCode();
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject(); 
            l_marginContractInfo.standardName = l_eqtypeProductRow.getStandardName();
            //�s��R�[�h�F�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(
            l_marginContractInfo.marketCode = l_finApp.getFinObjectManager().getMarket(l_contract.getMarketId()).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        //�����敪�F�@@
        if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
        {
            //�@@����.�ŋ敪==�h��ʁh�̏ꍇ�A�h��ʁh���Z�b�g�B
            l_marginContractInfo.accountType = WEB3TaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
        {
            //����.�ŋ敪==�i�h����h�A�܂��́A�h������������򒥎��h�j�̏ꍇ�A�h����h���Z�b�g�B
            l_marginContractInfo.accountType = WEB3TaxTypeDef.SPECIAL;
        }
        //���敪�F�@@����.���敪
        if (ContractTypeEnum.LONG.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }
        //�ٍϋ敪�F�@@����.�ٍϋ敪
        l_marginContractInfo.repaymentType = l_eqtypeContractRow.getRepaymentType();
        //�ٍϊ����l�F�@@����.�ٍϊ����l
        l_marginContractInfo.repaymentNum = "" + l_eqtypeContractRow.getRepaymentNum();
        //�����F�@@����.����
        l_marginContractInfo.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
        //���P���F�@@����.���P��
        l_marginContractInfo.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        //�������F�@@����.getLockedQuantity( )
        l_marginContractInfo.quantity = WEB3StringTypeUtility.formatNumber(l_contract.getLockedQuantity());
        //�����F�@@����.����
        l_marginContractInfo.closeDate = WEB3DateUtility.toDay(l_contract.getCloseDate());
        //������F�@@����.get�����( )�̖߂�l
        l_marginContractInfo.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractAmount(l_dblLockedQuantity));
        //���Ϗ�ԋ敪�F�@@�h���ϒ��h�@@�����ϒ����h2�h
        //�ԍω\�t���O�F�@@false(�Œ�)
        //�������n�\�t���O�F false(�Œ�)
        l_marginContractInfo.closingStatusType = WEB3MarginClosingStatusTypeDef.SETTLING;
        l_marginContractInfo.closingPossibleFlag = false;
        l_marginContractInfo.swapPossibleFlag = false;
        //���萔���F�@@(����.���萔�� + ����.���萔�������)
        double l_dblSetupFee = l_contract.getSetupFee(l_dblLockedQuantity);
        double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.setupFee = WEB3StringTypeUtility.formatNumber(
            l_dblSetupFee
            + l_dblSetupFeeTax);
        //�������F�@@����.������
        double l_dblInterestFee = l_contract.getInterestFee(l_dblLockedQuantity);
        l_marginContractInfo.interestFee = WEB3StringTypeUtility.formatNumber(
            l_dblInterestFee);
        //�t�����F�@@����.�t����
        double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblLockedQuantity);
        l_marginContractInfo.payInterestFee = WEB3StringTypeUtility.formatNumber(
            l_dblPayInterestFee);
        //�݊����F�@@����.�݊���
        double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblLockedQuantity);
        l_marginContractInfo.loanEquityFee = WEB3StringTypeUtility.formatNumber(
            l_dblLoanEquityFee);
        //�������F�@@(����.���`������ + ����.���`�����������)
        double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblLockedQuantity);
        double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.transferFee = WEB3StringTypeUtility.formatNumber(
            l_dblNameTransferFee
            + l_dblNameTransferFeeTax);
        //�Ǘ���F�@@(����.�Ǘ��� + ����.�Ǘ�������)
        double l_dblManagementFee = l_contract.getManagementFee(l_dblLockedQuantity);
        double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.managementFee = WEB3StringTypeUtility.formatNumber(
            l_dblManagementFee
            + l_dblManagementFeeTax);
        //���̑��F�@@����.���̑�
        double l_dblOther = l_contract.getOther(l_dblLockedQuantity);
        l_marginContractInfo.other = WEB3StringTypeUtility.formatNumber(
            l_dblOther);

        if (l_dblCurrentPrice != 0D)
        {
	        WEB3EquityBizLogicProvider l_bizLogic =
	            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
	        // ���o��v
	        double l_dblTotalCost = l_bizLogic.calcExpenses(0,
	            0,
	            l_dblSetupFee,
	            l_dblSetupFeeTax,
	            l_dblNameTransferFee,
	            l_dblNameTransferFeeTax,
	            l_dblManagementFee,
	            l_dblManagementFeeTax,
	            l_dblInterestFee,
	            l_dblPayInterestFee,
	            l_dblLoanEquityFee,
	            l_dblOther,
	            l_eqtypeContractRow.getContractType());
	        l_dblEvaluateIncomeWithCost = l_dblEvaluateIncome - l_dblTotalCost;
	        // �]�����v�i���o��l���j
	        l_marginContractInfo.takeExpensesOffEvaluationIncome =
	            WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncomeWithCost);
        }
        
        log.exiting(STR_METHOD_NAME);
        //�v���p�e�B�Z�b�g�����M�p���������I�u�W�F�N�g��ԋp����B
        return l_marginContractInfo;
    }

    /**
     * (create�����ό������)<BR>
     * �����ς̌�������1���׍쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�c���jcreate�����ό������v�Q�ƁB<BR>
     * @@param l_contract - (����)<BR>
     * ���������쐬����Ώۂ̌���
     * @@return WEB3MarginContractInfo
     * @@throws WEB3BaseException
     * @@roseuid 40EB4E880217
     */
    public WEB3MarginContractInfo createUnCloseMarginContractInfo(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createUnCloseMarginContractInfo";
        log.entering(STR_METHOD_NAME);
        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }

        //�M�p����������I�u�W�F�N�g�𐶐�����B
        WEB3MarginContractInfo l_marginContractInfo = new WEB3MarginContractInfo();

        //���������擾����B
        double l_dblContractQuantity = l_contract.getQuantity();

        //���ϒ��������ʂ��擾����B
        double l_dblLockedQuantity = l_contract.getLockedQuantity();
        
        //���Y���̖����ϐ��ʁi���ϒ��̐��ʂ����������ʁj���擾����B
		double l_dblUnCloseContractQuantity = l_dblContractQuantity - l_dblLockedQuantity;

        //�����������擾����B
		double l_dblCurrentPrice = 0D;
        try
        {
			l_dblCurrentPrice = this.getContractCurrentPrice(l_contract);
        }
        catch (WEB3BaseException l_be)
        {
			log.error("�����擾�G���[: " + STR_METHOD_NAME, l_be);
        }

        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_contract.getDataSourceObject();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //��������擾����B
        double l_dblContractPrice = l_contract.getContractAmount(l_dblUnCloseContractQuantity);

        //(*)�M�p����������I�u�W�F�N�g�Ɉȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //ID�F�@@����.����ID
        l_marginContractInfo.id = "" + l_contract.getContractId();

        //�s��R�[�h�F�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(����.����ID).�s��R�[�h
        WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        try
        {
            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct) l_equityProductManager.getProduct(l_eqtypeContractRow.getProductId());

            //�����R�[�h�F�@@��������(**).�����R�[�h
            //�������F�@@��������(**).������
            l_marginContractInfo.productCode = l_equityProduct.getProductCode();
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject(); 
            l_marginContractInfo.standardName = l_eqtypeProductRow.getStandardName();
            //�s��R�[�h�F�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(
            l_marginContractInfo.marketCode = l_finApp.getFinObjectManager().getMarket(l_contract.getMarketId()).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        //�����敪�F�@@
        if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
        {
            //�@@����.�ŋ敪==�h��ʁh�̏ꍇ�A�h��ʁh���Z�b�g�B
            l_marginContractInfo.accountType = WEB3TaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
        {
            //����.�ŋ敪==�i�h����h�A�܂��́A�h������������򒥎��h�j�̏ꍇ�A�h����h���Z�b�g�B
            l_marginContractInfo.accountType = WEB3TaxTypeDef.SPECIAL;
        }
        //���敪�F�@@����.���敪
        if (ContractTypeEnum.LONG.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }

        //�ٍϋ敪�F�@@����.�ٍϋ敪
        l_marginContractInfo.repaymentType = l_eqtypeContractRow.getRepaymentType();
        //�ٍϊ����l�F�@@����.�ٍϊ����l
        l_marginContractInfo.repaymentNum = "" + l_eqtypeContractRow.getRepaymentNum();
        //�����F�@@����.����
        l_marginContractInfo.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
        //���P���F�@@����.���P��
        l_marginContractInfo.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        //�������F�@@���Y���̖����ϐ��ʁi== (����.getQuantity( ) - ����.getLockedQuantity( ))�j
        l_marginContractInfo.quantity = WEB3StringTypeUtility.formatNumber(l_dblUnCloseContractQuantity);
        //�����F�@@����.����
        l_marginContractInfo.closeDate = WEB3DateUtility.toDay(l_contract.getCloseDate());
        //������F�@@����.get�����( )�̖߂�l
        l_marginContractInfo.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
        //���Ϗ�ԋ敪�F�@@�h�����ρh�@@�������ρ��h1�h
        l_marginContractInfo.closingStatusType = WEB3MarginClosingStatusTypeDef.HAVE_NOT_SETTLED;
        //���萔���F�@@(����.���萔�� + ����.���萔�������)
        double l_dblSetupFee = l_contract.getSetupFee(l_dblContractQuantity) - l_contract.getSetupFee(l_dblLockedQuantity);
        double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblContractQuantity) - l_contract.getSetupFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.setupFee = WEB3StringTypeUtility.formatNumber(
            l_dblSetupFee
            + l_dblSetupFeeTax);
        //�������F�@@����.������
        double l_dblInterestFee = l_contract.getInterestFee(l_dblContractQuantity) - l_contract.getInterestFee(l_dblLockedQuantity);
        l_marginContractInfo.interestFee = WEB3StringTypeUtility.formatNumber(
            l_dblInterestFee);
        //�t�����F�@@����.�t����
        double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblContractQuantity) - l_contract.getPayInterestFee(l_dblLockedQuantity);
        l_marginContractInfo.payInterestFee = WEB3StringTypeUtility.formatNumber(
            l_dblPayInterestFee);
        //�݊����F�@@����.�݊���
        double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblContractQuantity) - l_contract.getLoanEquityFee(l_dblLockedQuantity);
        l_marginContractInfo.loanEquityFee = WEB3StringTypeUtility.formatNumber(
            l_dblLoanEquityFee);
        //�������F�@@(����.���`������ + ����.���`�����������)
        double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblContractQuantity) - l_contract.getNameTransferFee(l_dblLockedQuantity);
        double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblContractQuantity) - l_contract.getNameTransferFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.transferFee = WEB3StringTypeUtility.formatNumber(
            l_dblNameTransferFee
            + l_dblNameTransferFeeTax);
        //�Ǘ���F�@@(����.�Ǘ��� + ����.�Ǘ�������)
        double l_dblManagementFee = l_contract.getManagementFee(l_dblContractQuantity) - l_contract.getManagementFee(l_dblLockedQuantity);
        double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblContractQuantity) - l_contract.getManagementFeeTax(l_dblLockedQuantity);
        l_marginContractInfo.managementFee = WEB3StringTypeUtility.formatNumber(
            l_dblManagementFee
            + l_dblManagementFeeTax);
        //���̑��F�@@����.���̑�
        double l_dblOther = l_contract.getOther(l_dblContractQuantity) - l_contract.getOther(l_dblLockedQuantity);
        l_marginContractInfo.other = WEB3StringTypeUtility.formatNumber(
            l_dblOther);
        
        if (l_dblCurrentPrice != 0D)
        {
            //�]�����v���擾����B
            double l_dblEvaluateIncome = l_contract.getAppraisalProfitOrLoss(
                l_dblCurrentPrice, l_dblUnCloseContractQuantity);
            
            WEB3EquityBizLogicProvider l_bizLogic =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            // ���o��v
            double l_dblTotalCost = l_bizLogic.calcExpenses(0,
                0,
                l_dblSetupFee,
                l_dblSetupFeeTax,
                l_dblNameTransferFee,
                l_dblNameTransferFeeTax,
                l_dblManagementFee,
                l_dblManagementFeeTax,
                l_dblInterestFee,
                l_dblPayInterestFee,
                l_dblLoanEquityFee,
                l_dblOther,
                l_eqtypeContractRow.getContractType());
            //�]�����v�i���o��l���j���擾����B
            double l_dblEvaluateIncomeWithCost = l_dblEvaluateIncome - l_dblTotalCost;

            //(*)�M�p����������I�u�W�F�N�g�Ɉȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
            //�]�����v�F�@@����.get�]�����v( )�̖߂�l
            l_marginContractInfo.evaluationIncome =
                WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
            //�]�����v(���o��l��)�F�@@����.get�]�����v( )�̖߂�l - calc���o��()�̖߂�l
            l_marginContractInfo.takeExpensesOffEvaluationIncome =
                WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncomeWithCost);
        }
        else
        {
            //�������擾�ł��Ȃ������ꍇ��null���Z�b�g
            l_marginContractInfo.evaluationIncome = null;
            l_marginContractInfo.takeExpensesOffEvaluationIncome = null;
        }

        log.exiting(STR_METHOD_NAME);
        //�v���p�e�B�Z�b�g�����M�p����������I�u�W�F�N�g��ԋp����B
        return l_marginContractInfo;
    }

    /**
     * (create���ύό������)<BR>
     * �������ύς̌�������1���׍쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�c���jcreate���ύό������v�Q�ƁB<BR>
     * @@param l_contract - (����)<BR>
     * ���������쐬����Ώۂ̌���
     * @@return WEB3MarginContractInfo
     * @@throws WEB3BaseException
     * @@roseuid 40EB4E65013C
     */
    public WEB3MarginContractInfo createClosedMarginContractInfo(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosedMarginContractInfo";
        log.entering(STR_METHOD_NAME);

        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //�M�p����������I�u�W�F�N�g�𐶐�����B
        WEB3MarginContractInfo l_marginContractInfo = new WEB3MarginContractInfo();

        //����ID���擾����B
        long l_lngContractId = l_contract.getContractId();

        //���������擾����B
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //�������ύς̖�萔�ʂ��擾����B
        double l_dblCloseExecQuantity = this.getDayCloseContractExecutedQuantity(l_lngContractId, GtlUtils.getSystemTimestamp());

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityFinTransactionManager l_finTransactionManager = (WEB3EquityFinTransactionManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getFinTransactionManager();
        //�����ڋq���薾�ׂ̈ꗗ���擾����B
        List l_lstTransaction = l_finTransactionManager.getTransactions(l_lngContractId, GtlUtils.getSystemTimestamp());

        EqtypeFinTransactionRow[] l_eqtyFinTransactionRow = new EqtypeFinTransactionRow[l_lstTransaction.size()];
        l_lstTransaction.toArray(l_eqtyFinTransactionRow);

        //��n���
        double l_dblNetAmount = 0D;

        //���萔��
        double l_dblSetupFee = 0D;

        //������
        double l_dblInterestFee = 0D;

        //�t����
        double l_dblPayInterestFee = 0D;

        //�݊���
        double l_dblLoanEquityFee = 0D;

        //������
        double l_dblTransferFee = 0D;

        //�Ǘ���
        double l_dblManagementFee = 0D;

        //���̑�
        double l_dblOther = 0D;

        //�ڋq���薾��(get�g�����U�N�V�����̖߂�l)�v�f���Ƃ�Loop����
        int l_tracsactionRowLength = 0;
        if (l_eqtyFinTransactionRow != null)
        {
            l_tracsactionRowLength = l_eqtyFinTransactionRow.length;
        }
        for (int i = 0; i < l_tracsactionRowLength; i++)
        {
            //�g�����U�N�V�����J�e�S�����擾����B
            FinTransactionCateg l_finTransactionCateg = l_eqtyFinTransactionRow[i].getFinTransactionCateg();

            //�i�ΏۃJ�e�S������)
            //�ԍρA�������n�ȊO�̏ꍇ�́A�ȍ~��Loop�������͎��{���Ȃ��B(continue;)
            //(�����ڋq���薾��.getFinTransactionCateg( ) != (�h�ԍώ���h�A�܂��́h�����E���n����h))
            if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_finTransactionCateg) || FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_finTransactionCateg))
            {
                //�]�����v���]�����v + �����ڋq���薾��.��
                l_dblNetAmount += l_eqtyFinTransactionRow[i].getNetAmount();

                //���萔�������萔�� + �����ڋq���薾��.���萔�� + �����ڋq���薾��.���萔�������
                l_dblSetupFee += (l_eqtyFinTransactionRow[i].getImportedSetupFee() + l_eqtyFinTransactionRow[i].getImportedSetupFeeTax());

                //�������������� + �����ڋq���薾��.������
                l_dblInterestFee += l_eqtyFinTransactionRow[i].getImportedInterestFee();

                //�t�������t���� + �����ڋq���薾��.�t����
                l_dblPayInterestFee += l_eqtyFinTransactionRow[i].getImportedPayInterestFee();

                //�݊������݊��� + �����ڋq���薾��
                l_dblLoanEquityFee += l_eqtyFinTransactionRow[i].getImportedLoanEquityFee();

                //�������������� + �����ڋq���薾��.���`������ + �����ڋq���薾��.���`�����������
                l_dblTransferFee += (l_eqtyFinTransactionRow[i].getImportedNameTransferFee() + l_eqtyFinTransactionRow[i].getImportedNameTransferFeeTax());

                //�Ǘ���Ǘ��� + �����ڋq���薾��.�Ǘ��� + �����ڋq���薾��.�Ǘ�������
                l_dblManagementFee += (l_eqtyFinTransactionRow[i].getImportedManagementFee() + l_eqtyFinTransactionRow[i].getImportedManagementFeeTax());

                //���̑������̑� + �����ڋq���薾��.���̑�
                l_dblOther += l_eqtyFinTransactionRow[i].getImportedOther();
            }
        }

        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_contract.getDataSourceObject();

        //(*)�M�p����������I�u�W�F�N�g�Ɉȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //ID�F�@@����.����ID
        l_marginContractInfo.id = "" + l_contract.getContractId();

        //(**)�g���v���_�N�g�}�l�[�W��.getProduct(����.����ID)�ɂĎ擾
        WEB3EquityProductManager l_equityProductManager = (WEB3EquityProductManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        try
        {

            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct) l_equityProductManager.getProduct(l_eqtypeContractRow.getProductId());

            //�����R�[�h�F�@@��������(**).�����R�[�h
            //�������F�@@��������(**).������
            l_marginContractInfo.productCode = l_equityProduct.getProductCode();
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();
            l_marginContractInfo.standardName = l_eqtypeProductRow.getStandardName();

            l_marginContractInfo.marketCode = l_finApp.getFinObjectManager().getMarket(l_contract.getMarketId()).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        //�����敪�F�@@
        if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
        {
            //����.�ŋ敪==�h��ʁh�̏ꍇ�A�h��
            l_marginContractInfo.accountType = WEB3TaxTypeDef.NORMAL;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType()) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
        {
            //����.�ŋ敪==�i�h����h�A�܂��́A�h������������򒥎��h
            l_marginContractInfo.accountType = WEB3TaxTypeDef.SPECIAL;
        }
        //���敪�F�@@����.����
        if (ContractTypeEnum.LONG.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_eqtypeContractRow.getContractType()))
        {
            l_marginContractInfo.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }

        //�ٍϋ敪�F�@@����.�ٍϋ敪
        l_marginContractInfo.repaymentType = l_eqtypeContractRow.getRepaymentType();
        //�ٍϊ����l�F�@@����.�ٍϊ����l
        l_marginContractInfo.repaymentNum = "" + l_eqtypeContractRow.getRepaymentNum();
        //�����F�@@����.����
        l_marginContractInfo.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
        //���P���F�@@����.���P��
        l_marginContractInfo.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        //�������F�@@this.�������ϖ�芔��( )��
        l_marginContractInfo.quantity = WEB3StringTypeUtility.formatNumber(l_dblCloseExecQuantity);
        //�����F�@@����.��
        l_marginContractInfo.closeDate = WEB3DateUtility.toDay(l_contract.getCloseDate());
        //������F�@@����.get�����(this.�������ϖ�芔��( )�̖�
        l_marginContractInfo.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractAmount(l_dblCloseExecQuantity));
        //�]�����v�F�@@(��n����̍��v�l)
        l_marginContractInfo.evaluationIncome = WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
        //�]�����v(���o��l��)�F�@@(��n�����
        l_marginContractInfo.takeExpensesOffEvaluationIncome = WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
        //���Ϗ�ԋ敪�F�@@�h���ύρh�@@�����ύρ��h0�h
        //�ԍω\�t���O�F�@@false(�Œ�)
        //�������n�\�t���O�F false(��
        l_marginContractInfo.closingStatusType = WEB3MarginClosingStatusTypeDef.SETTLEMENT_END;
        l_marginContractInfo.closingPossibleFlag = false;
        l_marginContractInfo.swapPossibleFlag = false;
        //���萔���F�@@(���萔�� + ���萔������ł̍��v�l)
        l_marginContractInfo.setupFee = WEB3StringTypeUtility.formatNumber(l_dblSetupFee);
        //�������F�@@(�������̍��v�l)
        l_marginContractInfo.interestFee = WEB3StringTypeUtility.formatNumber(l_dblInterestFee);
        //�t�����F�@@(�t�����̍��v�l)
        l_marginContractInfo.payInterestFee = WEB3StringTypeUtility.formatNumber(l_dblPayInterestFee);
        //�݊����F�@@(�݊����̍��v�l)
        l_marginContractInfo.loanEquityFee = WEB3StringTypeUtility.formatNumber(l_dblLoanEquityFee);
        //(���`�������̍��v�l)
        l_marginContractInfo.transferFee = WEB3StringTypeUtility.formatNumber(l_dblTransferFee);
        //�Ǘ���F�@@(�Ǘ���̍��v�l)
        l_marginContractInfo.managementFee = WEB3StringTypeUtility.formatNumber(l_dblManagementFee);
        //���̑��F�@@(���̑��̍��v�l)
        l_marginContractInfo.other = WEB3StringTypeUtility.formatNumber(l_dblOther);

        log.exiting(STR_METHOD_NAME);

        //�v���p�e�B�Z�b�g�����M�p����������I�u�W�F�N�g��ԋp����B
        return l_marginContractInfo;
    }

    /**
     * (get�ԍώw����)<BR>
     * �w�肵������ID�ɊY������ԍώw����̈ꗗ���擾����<BR>
     * <BR>
     * �P�j�@@�����ԍώw����e�[�u������<BR>
     * �@@�ȉ��̏����Ō����ԍώw����e�[�u������������<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = ����.����ID<BR>
     * <BR>
     * �Q�j�@@�P�j�̎擾���ʂ̈ꗗ��ԋp����<BR>
     * ���Y������f�[�^�����݂��Ȃ��ꍇ�ɂ�null��ԋp����<BR>
     * @@param l_lngContractId - ����ID
     * @@return EqTypeClosingContractSpec[]
     * @@throws WEB3BaseException
     * @@roseuid 40E514E90212
     */
    public EqTypeClosingContractSpec[] getClosingContractSpecs(long l_lngContractId) throws WEB3BaseException
    {
        final String l_strMethodName = "getClosingContractSpecs";
        log.entering(l_strMethodName);

        // (1)����I�ԍώw����e�[�u������
        List l_lisReturnValue = new ArrayList();
        // [��������]:
        String l_strWhere = " contract_id = ? ";

        Object[] l_objWhereValue = { new Long(l_lngContractId)};

        // QueryProcessor.doFindAllQuery()
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "�����ԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "�����ԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + l_strMethodName, l_ex.getMessage(), l_ex);
        }

        // (2)(1)�̎擾���ʂ̈ꗗ��ԋp����
        if (l_lisReturnValue.size() == 0)
        {
            // --- Test Log
            log.debug("�����ԍώw����e�[�u�������̌��ʂ��Ȃ��Anull�� �ԋp����I�I�I");

            log.exiting(l_strMethodName);
            return null;
        }
        WEB3EquityClosingContractSpec[] l_closingcontractspec = new WEB3EquityClosingContractSpec[l_lisReturnValue.size()];

        ArrayList l_lisReturnList = new ArrayList();
        for (int i = 0; i < l_lisReturnValue.size(); i++)
        {
            EqtypeClosingContractSpecRow l_row = (EqtypeClosingContractSpecRow) l_lisReturnValue.get(i);
            WEB3EquityClosingContractSpec l_impl = new WEB3EquityClosingContractSpec(l_row);
            l_lisReturnList.add(i, l_impl);
        }

        l_lisReturnList.toArray(l_closingcontractspec);

        log.exiting(l_strMethodName);
        return l_closingcontractspec;
    }

    /**
     * (get�ԍώw����)<BR>
     * �w�肵������ID�ƍX�V���t�ɊY������ԍώw����̈ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@�����ԍώw����e�[�u������<BR>
     * �ȉ��̏����Ō����ԍώw����e�[�u������������<BR>
     * <BR>
     * [��������]<BR>
     * ����ID������.����ID<BR>
     * �X�V���t������.�X�V���t�Ɠ������t<BR>
     * <BR>
     * �Q�j�@@�P�j�̎擾���ʂ̈ꗗ��ԋp����<BR>
     * ���Y������f�[�^�����݂��Ȃ��ꍇ�ɂ�null��ԋp����<BR>
     * @@param l_lngContractId - ����ID
     * @@param l_datLastUpdatedTimestamp - (�X�V���t)<BR>
     * �����̓��t<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return EqTypeClosingContractSpec[]
     * @@throws WEB3BaseException
     * @@roseuid 40E5092302ED
     */
    public EqTypeClosingContractSpec[] getClosingContractSpecs(long l_lngContractId, Date l_datLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getClosingContractSpecs";
        log.entering(STR_METHOD_NAME);
        WEB3EquityClosingContractSpec[] l_closingContractSpec = null;
        try
        {
            // �P�j�����ԍώw����Ǎ�

            //�����h�c = this.getContractId()
            String l_strWhere = " contract_id = ? and to_char(last_updated_timestamp,'yyyyMMdd') = ? ";

            // [����1]:�����ԍώw����.�����h�c = ����.�����h�c

            Object[] l_objWhereValues = { new Long(l_lngContractId), WEB3DateUtility.formatDate(l_datLastUpdatedTimestamp, "yyyyMMdd")};

            List l_returnList = new ArrayList();

            // �f�[�^����   
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_returnList = processor.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, l_strWhere, l_objWhereValues);

            // �Q�j�擾���ʂ̈ꗗ��ԋp
            if (l_returnList == null ||l_returnList.size() == 0)
            {
                return null;
            }

            l_closingContractSpec = new WEB3EquityClosingContractSpec[l_returnList.size()];
            ArrayList l_lisReturnList = new ArrayList();
            for (int i = 0; i < l_returnList.size(); i++)
            {
                EqtypeClosingContractSpecRow l_row = (EqtypeClosingContractSpecRow) l_returnList.get(i);
                WEB3EquityClosingContractSpec l_impl = new WEB3EquityClosingContractSpec(l_row);
                l_lisReturnList.add(i, l_impl);
            }
            l_lisReturnList.toArray(l_closingContractSpec);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "�����ԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "�����ԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_closingContractSpec;
    }

    /**
     * (get�������ϖ�芔��)<BR>
     * �w�茚���̓������ϖ�芔�����擾����B<BR>
     * <BR>
     * �P�j�@@�����̕ԍώw����ꗗ���擾<BR>
     * �@@this.get�ԍώw����(����.����ID�A����.�X�V���t)���R�[��<BR>
     * �@@��get�ԍώw����̖߂�l��null�̏ꍇ�A0��ԋp����<BR>
     * <BR>
     * �Q�j�@@�ԍώw����v�f�����Ƃ�Loop����<BR>
     * �@@�ԍώw����.getExecutedQuantity( )�̒l�����Z���Ă���<BR>
     * <BR>
     * �R�j�@@ �ԍϖ�萔�ʂ̍��v�l��ԋp����<BR>
     * @@param l_lngContractId - ����ID
     * @@param l_datLastUpdatedTimestamp - (�X�V���t)<BR>
     * �����̓��t<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E504EF0212
     */
    public double getDayCloseContractExecutedQuantity(long l_lngContractId, Date l_datLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDayCloseContractExecutedQuantity";
        log.entering(STR_METHOD_NAME);
        WEB3EquityClosingContractSpec[] l_closingContractSpec = (WEB3EquityClosingContractSpec[]) this.getClosingContractSpecs(l_lngContractId, l_datLastUpdatedTimestamp);
        if (l_closingContractSpec == null)
        {
            return 0;
        }
        double l_dblexecutedQuantity = 0D;
        int l_intContractSpecLength = 0;
        if (l_closingContractSpec != null)
        {
            l_intContractSpecLength = l_closingContractSpec.length;
        }
        for (int i = 0; i < l_intContractSpecLength; i++)
        {
            l_dblexecutedQuantity += l_closingContractSpec[i].getExecutedQuantity();
        }

        return l_dblexecutedQuantity;
    }

    /**
     * (get��������)<BR>
     * �w�茚���̖����̎������擾����B<BR>
     * <BR>
     * ���L�菇�Ŏ擾����������ԋp����B<BR>
     * <BR>
     * �P�j�@@�s��ID�A����ID�̎擾<BR>
     * �s��ID = ����.����.get�s��ID()<BR>
     * ����ID = ����.����.get����ID()<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�A�����R�[�h�̎擾<BR>
     * �s�� = �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(�s��ID)<BR>
     * �s��R�[�h = �s��.get�s��R�[�h()<BR>
     * <BR>
     * �������� = �g���v���_�N�g�}�l�[�W��.getProduct(����ID)<BR>
     * �����R�[�h = ��������.get�����R�[�h()<BR>
     * <BR>
     * �R�j�@@�����Z�b�g���肨��ю����̎擾<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(CURRENT_PRICE)��Hashtable���擾<BR>
     * <BR>
     * �@@�R�|�P�j�@@�Y�������̎������Z�b�g����Ă���ꍇ(Hashtable.get(�����R�[�h + �s��R�[�h)��NULL�łȂ��ꍇ)<BR>
     * <BR>
     *         ���� = Hashtable.get(�����R�[�h + �s��R�[�h)<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�Y�������̎������Z�b�g����Ă��Ȃ��ꍇ(Hashtable.get(�����R�[�h + �s��R�[�h)��NULL�̏ꍇ)<BR>
     * <BR>
     * �@@�@@�R�|�Q�|�P�j�@@�����̎擾<BR>
     * �@@�@@�@@�@@�@@�@@�@@������� = �g���v���_�N�g�}�l�[�W��.getTradedProduct(����ID�A�s��ID�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@���� = �g���v���_�N�g�}�l�[�W��.get����(�������)<BR>
     * �@@�@@<BR>
     * �@@�@@�R�|�Q�|�Q�j�@@�����̒ǉ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�擾����Hashtable�ɖ����R�[�h + �s��R�[�h��key�Ƃ��A������ǉ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@Hashtable.put�i�����R�[�h + �s��R�[�h, ����)<BR>
     * <BR>
     * �@@�@@�R�|�Q�|�R�j�@@�����̃Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�@@ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ������Z�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ݒ�L�[�F�@@CURRENT_PRICE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�l�F�@@�R�|�Q�|�Q�j�Ŏ�����ǉ�����Hashtable<BR>
     * <BR>
     * �S�j�@@������ԋp<BR>
     * <BR>
     * �����Y���\�b�h���g�p����ꍇ�́A�e�T�[�r�X�C���^�Z�v�^��onCall�ɂĎ����̃Z�b�g����(*)�A
     * �@@onReturn�����onThrowable���\�b�h���ɂĎ����̃N���A�������s������<BR>
     * <BR>
     * (*)ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�ThreadLocal�Ɏ����̕ϐ����Z�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ݒ�L�[�F�@@CURRENT_PRICE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�l�F�@@Hashtable(�V�K�ɍ쐬����Hashtable)<BR>
     * <BR>
     * @@param l_contract - ����
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40E4F2FC03D7
     */
    public double getContractCurrentPrice(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCurrentPrice";
        log.entering(STR_METHOD_NAME);

        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        double l_dblCurrentPrice = 0.0D;
        
        // �P�j�@@�s��ID�A����ID�̎擾
        long l_lngMarketId = l_contract.getMarketId();
        long l_lngProductId = l_contract.getProduct().getProductId();

        // �Q�j�@@�s��R�[�h�A�����R�[�h�̎擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        Market l_market = null;
        WEB3EquityProduct l_product = null;
        try {
            l_market = l_finObjectManager.getMarket(l_lngMarketId);
            l_product = (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        String l_strMarketCode = l_market.getMarketCode();
        String l_strProductCode = l_product.getProductCode();

        //  �R�j�@@�����Z�b�g���肨��ю����̎擾
        Hashtable l_htCurrentPrices =
            (Hashtable)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3MarginAttributeNameDef.CURRENT_PRICE);
        String l_strKey = l_strProductCode + l_strMarketCode;
        // �@@�R�|�P�j�@@�Y�������̎������Z�b�g����Ă���ꍇ(Hashtable.get(�����R�[�h + �s��R�[�h)��NULL�łȂ��ꍇ)
        if (l_htCurrentPrices != null && l_htCurrentPrices.containsKey(l_strKey))
        {
            Double l_currentPrice =
                (Double)l_htCurrentPrices.get(l_strKey);
            l_dblCurrentPrice = l_currentPrice.doubleValue();
        }
        // �@@�R�|�Q�j�@@�Y�������̎������Z�b�g����Ă��Ȃ��ꍇ(Hashtable.get(�����R�[�h + �s��R�[�h)��NULL�̏ꍇ)
        else
        {
            // �@@�@@�R�|�Q�|�P�j�@@�����̎擾
            WEB3EquityTradedProduct l_tradeProduct = null;
            try
            {
                l_tradeProduct = (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_lngProductId, l_lngMarketId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            l_dblCurrentPrice = l_productManager.getCurrentPrice(l_tradeProduct);

            // �@@�@@�R�|�Q�|�Q�j�@@�����̒ǉ�
            if (l_htCurrentPrices == null)
            {
                l_htCurrentPrices = new Hashtable();
            }
            l_htCurrentPrices.put(l_strKey, new Double(l_dblCurrentPrice));

            // �@@�@@�R�|�Q�|�R�j�@@�����̃Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.CURRENT_PRICE,
                l_htCurrentPrices);
        }
        
        // �S�j�@@������ԋp
        log.exiting(STR_METHOD_NAME);
        return l_dblCurrentPrice;
    }

    /**
     * (get���Ϗ��)<BR>
     * �����̌��Ϗ�Ԃ𔻒肷��B<BR>
     * <BR>
     * �P�j�@@�M�p������Ϗ�Ԃ̐���<BR>
     * �@@�߂�l�ƂȂ�M�p������Ϗ�ԃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�M�p������Ϗ�Ԃ̃v���p�e�B�̃f�t�H���g�ݒ�͉��L�Ƃ���B<BR>
     * �@@�E���ύσt���O��false<BR>
     * �@@�E�����σt���O��false<BR>
     * �@@�E���ϒ��t���O��false<BR>
     * <BR>
     * �Q�j�@@���Ϗ�Ԕ��荀�ڂ̎擾<BR>
     * <BR>
     * �@@�E����ID������.����.getContractId( )<BR>
     * �@@�E�X�V���t������.����.getLastUpdatedTimestamp( )<BR>
     * �@@�E��������������.����.getOriginalQuantity( )<BR>
     * �@@�E������������.����.getQuantity( )<BR>
     * <BR>
     * �R�j�@@���Ϗ�Ԃ̔���P<BR>
     * <BR>
     * �@@�R�|�P�j�@@��������==0�A��������==0�̏ꍇ(�V�K�������)�A<BR>
     * �@@�@@�@@�@@�@@�@@�f�t�H���g�ݒ�̂܂܂ŐM�p������Ϗ�Ԃ�ԋp����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�X�V���t(yyyymmdd)���V�X�e�����t(yyyymmdd)�ŁA��������<BR>
     * ==0�̏ꍇ(�O���ȑO���ύ�)�A<BR>
     * �@@�@@�@@�@@�@@�@@�f�t�H���g�ݒ�̂܂܂ŐM�p������Ϗ�Ԃ�ԋp����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�X�V���t(yyyymmdd)���V�X�e�����t�iyyyymmdd�j�A<BR>
     * ��������==0�̏ꍇ(���ύ�)�A<BR>
     * �@@�@@�@@�@@�@@�@@���ύσt���O��true�ɐݒ肵�M�p������Ϗ�Ԃ�ԋp����B<BR>
     * <BR>
     * �@@�R�|�S�j�@@��L(�R�|�P�j�A�R�|�Q�j�A�R�|�R)�ȊO�̏ꍇ�A�S�j�̏������s���B<BR>
     * <BR>
     * �S�j�@@���b�N�����ʁA����сA�������ϖ�芔���̎擾<BR>
     * <BR>
     * �@@�@@�E���b�N�����ʁ�����.����.getLockedQuantity( )<BR>
     * �@@�@@�E�������ϖ�芔����this.�������ϖ�芔��(����ID�A�V�X�e�����t<BR>
     * (yyyymmdd))<BR>
     * <BR>
     * �T�j�@@���Ϗ�Ԃ̔���Q<BR>
     * <BR>
     * �@@�T�|�P�j�@@���b�N������==0�̏ꍇ<BR>
     * �@@�@@�@@�E�������ϖ�芔��==0�̏ꍇ(������)�A<BR>
     * �@@�@@�@@�@@�����σt���O��true�ɐݒ肵�M�p������Ϗ�Ԃ�ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�E�������ϖ�芔����1�̏ꍇ(���ύςƖ�����)�A<BR>
     * �@@�@@�@@�@@���ύσt���O��true�A�����σt���O��true�ɐݒ肵�M�p������Ϗ�Ԃ�ԋp����
     * �B<BR>
     * <BR>
     * �@@�T�|�Q�j�@@���b�N�����ʁ�1�̏ꍇ<BR>
     * �@@�@@�@@�T�|�Q�|�P�j�@@�������ϖ�芔��==0�̏ꍇ<BR>
     * �@@�@@�@@�@@�E���b�N������==�������̏ꍇ(���ϒ�)�A<BR>
     * �@@�@@�@@�@@�@@���ϒ��t���O��true�ɐݒ肵�M�p������Ϗ�Ԃ�ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E���b�N������!=�������̏ꍇ(�����ςƌ��ϒ�)�A<BR>
     * �@@�@@�@@�@@�@@�����σt���O��true�A���ϒ��t���O��true�ɐݒ肵�M�p������Ϗ�Ԃ�ԋp��
     * ��B<BR>
     * <BR>
     * �@@�@@�@@�T�|�Q�|�Q�j�@@�������ϖ�芔����1�̏ꍇ<BR>
     * �@@�@@�@@�@@�E���b�N������==�������̏ꍇ(���ύςƌ��ϒ�)�A<BR>
     * �@@�@@�@@�@@�@@���ύσt���O��true�A���ϒ��t���O��true�ɐݒ肵�M�p������Ϗ�Ԃ�ԋp��
     * ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�E���b�N������!=�������̏ꍇ(���ύςƖ����ςƌ��ϒ�)�A<BR>
     * �@@�@@�@@�@@�@@���ύσt���O��true�A�����σt���O��ture�A<BR>
     * ���ϒ��t���O��ture�ɐݒ肵�M�p������Ϗ�Ԃ�ԋp����B<BR>
     * @@param l_contract - ����
     * @@return WEB3MarginCloseStatus
     * @@throws WEB3BaseException
     * @@roseuid 40E4F23E01B4
     */
    public WEB3MarginCloseStatus getMarginCloseStatus(WEB3EquityContract l_contract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarginCloseStatus";
        log.entering(STR_METHOD_NAME);
        if (l_contract == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        WEB3MarginCloseStatus l_marginCloseStatus = new WEB3MarginCloseStatus();
        //���ύσt���O
        l_marginCloseStatus.closedMarginFlag = false;

        //�����σt���O
        l_marginCloseStatus.closeMarginFlag = false;

        //���ϒ��t���O
        l_marginCloseStatus.closingMarginFlag = false;

        // �������擾����
        EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_contract.getDataSourceObject();
        // 1.����ID���擾����B
        long l_lngContractId = l_contract.getContractId();
        String l_timestamp = WEB3DateUtility.formatDate(l_eqtypeContractRow.getLastUpdatedTimestamp(), "yyyyMMdd");

        // 3.getOriginalQuantity( ) --- ���������ʂ��擾����B
        double l_dblOriginalQuantity = l_contract.getOriginalQuantity();
        if (Double.isNaN(l_dblOriginalQuantity))
        {
            l_dblOriginalQuantity = 0;
        }

        Timestamp l_dteCurrentDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        String l_CurrentDate = WEB3DateUtility.formatDate(l_dteCurrentDate, "yyyyMMdd");
        // 4.getQuantity( ) --- �������ʂ��擾����B
        double l_dblQuantity = l_contract.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        // �������� == 0 ���� ������ == 0�̏ꍇ(�����)
        if (l_dblOriginalQuantity == 0 && l_dblQuantity == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_marginCloseStatus;
        }
        // �X�V���t(yyyymmdd) < �V�X�e�����t(yyyymmdd)�ŁA�������� == 0�̏ꍇ(�O���ȑO���ύ�)
        else if (l_timestamp.compareTo(l_CurrentDate) < 0 && l_dblQuantity == 0)
        {
            return l_marginCloseStatus;
        }
        // �X�V���t(yyyymmdd) >= �V�X�e�����t(yyyymmdd)�A�������� == 0�̏ꍇ(���ύ�)
        else if (l_timestamp.compareTo(l_CurrentDate) >= 0 && l_dblQuantity == 0)
        {
            l_marginCloseStatus.closedMarginFlag = true;

            log.exiting(STR_METHOD_NAME);
            return l_marginCloseStatus;
        }
        else
        {
            // 5.getLockedQuantity( ) --- ���ϒ��������ʂ��擾����B
            double l_dblLockedQuantity = l_contract.getLockedQuantity();
            //�������ϖ�芔����this.�������ϖ�芔��(����ID�A�V�X�e�����t
            double l_dblContractExecutionCnt = getDayCloseContractExecutedQuantity(l_lngContractId, l_dteCurrentDate);
            
            // ���b�N������ == 0�̏ꍇ
            if (l_dblLockedQuantity == 0)
            {
                // �������ϖ�芔�� == 0�̏ꍇ(������)
                if (l_dblContractExecutionCnt == 0)
                {
                    // �����σt���O == true
                    l_marginCloseStatus.closeMarginFlag = true;
                }
                // �������ϖ�芔�� �� 1�̏ꍇ(���ύςƖ�����)
                else if (l_dblContractExecutionCnt >= 1)
                {
                    // ���ύρE�����σt���O == true
                    l_marginCloseStatus.closedMarginFlag = true;
                    l_marginCloseStatus.closeMarginFlag = true;

                }
                return l_marginCloseStatus;
            }
            // ���b�N������ �� 1�̏ꍇ
            else if (l_dblLockedQuantity >= 1)
            {
                // �������ϖ�芔�� == 0�̏ꍇ
                if (l_dblContractExecutionCnt == 0)
                {
                    // ���b�N������ == �������̏ꍇ(���ϒ�)
                    if (l_dblLockedQuantity == l_dblQuantity)
                    {
                        // ���ϒ��t���O == true
                        l_marginCloseStatus.closingMarginFlag = true;
                    }
                    else    // ���b�N������ != �������̏ꍇ(�����ςƌ��ϒ�)
                    {
                        // �����ρE���ϒ��t���O == true
                        l_marginCloseStatus.closeMarginFlag = true;
                        l_marginCloseStatus.closingMarginFlag = true;
                    }
                }
                // �������ϖ�芔�� �� 1�̏ꍇ
                else if (l_dblContractExecutionCnt >= 1)
                {
                    // ���b�N������ == �������̏ꍇ(���ύςƌ��ϒ�)
                    if (l_dblLockedQuantity == l_dblQuantity)
                    {
                        // ���ύρE���ϒ��t���O == true
                        l_marginCloseStatus.closedMarginFlag = true;
                        l_marginCloseStatus.closingMarginFlag = true;
                    }
                    else    // ���b�N������ != �������̏ꍇ(���ύςƖ����ςƌ��ϒ�)
                    {
                        // ���ύρE�����ρE���ϒ��t���O == true
                        l_marginCloseStatus.closedMarginFlag = true;
                        l_marginCloseStatus.closeMarginFlag = true;
                        l_marginCloseStatus.closingMarginFlag = true;
                    }
                }
                
                log.exiting(STR_METHOD_NAME);
                return l_marginCloseStatus;
            }
        }
        log.error("Error In Method: " + STR_METHOD_NAME);
        throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + STR_METHOD_NAME);
    }

    /**
     * (is������)<BR>
     * �w�肳�ꂽ�ٍϊ����l���u�������v�ł��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * �����ٍ̕ϊ����l��9999999(ALL9)�ł���ꍇ�́A<BR>
     * �u�������v�Ɣ��肵true��Ԃ��B<BR>
     * �ȊO��false��Ԃ��B<BR>
     * @@param l_dblRepaymentNum - �ٍϊ����l�B
     * 
     * @@return boolean
     * @@roseuid 40D2F02700FB
     */
    public boolean isIndefinite(double l_dblRepaymentNum)
    {
        final String STR_METHOD_NAME = "isIndefinite";
        log.entering(STR_METHOD_NAME);

        if (Double.compare(l_dblRepaymentNum, 9999999) == 0)
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

    /**
     * (update�����f�[�^)<BR>
     * ��������ɁA�ȉ��̃G���e�B�e�B�̍X�V���s���B<BR>
     * <BR>
     * �|�����ڋq����iEqTypeFinTransaction�j<BR>
     * �|�ڋq����iGenFinTransaction�j<BR>
     * �|�����iEqTypeContract�j<BR>
     * <BR>
     * �g���|�W�V�����w���p�[.update�����f�[�^(���)��delegate����B<BR>
     * @@param l_orderExecution - ���B
     * @@throws WEB3BaseException
     * @@roseuid 40D0EAC20208
     */
    public void updateExecutedData(EqTypeOrderExecution l_orderExecution) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExecutedData(EqTypeOrderExecution)";
        log.entering(STR_METHOD_NAME);
        ((WEB3EquityPositionManagerHelper)m_helper).updateExecutedData(l_orderExecution);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����ꗗ)<BR>
     * �w������Ɉ�v���錚���I�u�W�F�N�g�iEqTypeContract�j�̈ꗗ���\�[�g���ĕԋp����B<BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ��NULL��ԋp����B<BR>
     * �igetContracts�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����������ǉ�����B<BR>
     * <BR>
     * �Q�|�P�j�@@����.��������������̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ?"<BR>
     * �@@�@@�@@�@@�@@��t������B<BR>
     * <BR>
     * �Q�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�������̕⏕�����I�u�W�F�N�g�A�y�ш����̖����^�C�v���ݒ肷��B<BR>
     * <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�����I�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(,����Row.TYPE<BR>
     *                                      �Q�|�P�j�̌�������������,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�\�[�g����������,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@null,<BR>
     *                                      �Q�|�Q�j�̌��������f�[�^�R���e�i)<BR>
     * <BR>
     * �S�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j<BR>
     * <BR>
     * 0�F���̑�<BR>
     * 1�F����<BR>
     * 2�F��<BR>
     * 3�F�����M��<BR>
     * 4�F�O����<BR>
     * 5�F����<BR>
     * 6�F�敨�I�v�V����<BR>
     * @@param l_strSearchCondCharacterString - �������� ������
     * @@param l_strSortCondCharacterString - �\�[�g����������
     * @@param l_strSearchCondDataContainers - (���������f�[�^�R���e�i)<BR>
     * ��������
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40F2536202BC
     */
    public List getContracts(
        WEB3GentradeSubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchCondCharacterString,
        String l_strSortCondCharacterString,
        String[] l_strSearchCondDataContainers)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContracts";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        // (1)�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����
        List l_lisReturnValue = new ArrayList();

        // (2)���������ǉ�
        // (2-1�j����������������쐬
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ?";

        Object[] l_objWhereValue = null;
        // (2-2)������z��𐶐���
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        Object[] l_objWhereValue1 = { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(l_productType.intValue())};

        if ((l_strSearchCondCharacterString != null && !l_strSearchCondCharacterString.equals("")) && l_strSearchCondDataContainers != null)
        {

            l_strWhere += l_strSearchCondCharacterString;

            l_objWhereValue = new Object[l_strSearchCondDataContainers.length + 3];
            System.arraycopy(l_objWhereValue1, 0, l_objWhereValue, 0, 3);

            System.arraycopy(l_strSearchCondDataContainers, 0, l_objWhereValue, 3, l_strSearchCondDataContainers.length);

        }
        else
        {
            l_objWhereValue = new Object[3];
            l_objWhereValue[0] = new Long(l_lngAccountId);
            l_objWhereValue[1] = new Long(l_lngSubAccountId);
            l_objWhereValue[2] = new Long(l_productType.intValue());
        }

        // (3)QueryProcessor.doFindAllQuery( )�ɂ��A
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, l_strSortCondCharacterString, null, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "�����e�[�u��������  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "�����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }

    /**
     * (get�����ꗗ)<BR>
     * �w������Ɉ�v���錚���I�u�W�F�N�g�iEqTypeContract�j�̈ꗗ��ԋp����B<BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ��NULL��ԋp����B<BR>
     * �igetContracts�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����������ǉ�����B<BR>
     * <BR>
     * �Q�|�P�j�@@����.��������������̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ?"<BR>
     * �@@�@@�@@�@@�@@��t������B<BR>
     * <BR>
     * �Q�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�������̕⏕�����I�u�W�F�N�g�A�y�ш����̖����^�C�v���ݒ肷��B<BR>
     * <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�����I�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(,����Row.TYPE<BR>
     *                                      �Q�|�P�j�̌�������������,<BR>
     *                                      �Q�|�Q�j�̌��������f�[�^�R���e�i)<BR>
     * 
     * �S�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j<BR>
     * <BR>
     * 0�F���̑�<BR>
     * 1�F����<BR>
     * 2�F��<BR>
     * 3�F�����M��<BR>
     * 4�F�O����<BR>
     * 5�F����<BR>
     * 6�F�敨�I�v�V����<BR>
     * @@param l_strSearchCondCharacterString - �������� ������
     * @@param l_strSearchCondDataContainers - (���������f�[�^�R���e�i)<BR>
     * ��������
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40ABFE5E03C4
     */
    public List getContracts(
        WEB3GentradeSubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchCondCharacterString,
        String[] l_strSearchCondDataContainers)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContracts()";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        // (1)�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����
        List l_lisReturnValue = new ArrayList();

        // (2)���������ǉ�
        // (2-1�j����������������쐬
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ?";

        Object[] l_objWhereValue = null;
        // (2-2)������z��𐶐���
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        Object[] l_objWhereValue1 = {
            new Long(l_lngAccountId),
            new Long(l_lngSubAccountId),
            new Long(l_productType.intValue())};

        if ((l_strSearchCondCharacterString != null
            && !l_strSearchCondCharacterString.equals(""))
            && l_strSearchCondDataContainers != null)
        {

            //l_strWhere += "and "  +l_strSearchConditionString;
            l_strWhere += l_strSearchCondCharacterString;

            l_objWhereValue = new Object[l_strSearchCondDataContainers.length + 3];
            System.arraycopy(l_objWhereValue1, 0, l_objWhereValue, 0, 3);

            System.arraycopy(l_strSearchCondDataContainers, 0, l_objWhereValue, 3, l_strSearchCondDataContainers.length);

        }
        else
        {
            l_objWhereValue = new Object[3];
            l_objWhereValue[0] = new Long(l_lngAccountId);
            l_objWhereValue[1] = new Long(l_lngSubAccountId);
            l_objWhereValue[2] = new Long(l_productType.intValue());
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "�����e�[�u��������  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "�����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        if (l_lisReturnValue == null || l_lisReturnValue.size() == 0)
        {
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }
    /**
     * (get�~�j���ۗL����)<BR>
     * �igetMiniStockQuantity�j<BR>
     *  �P�j�@@�ۗL���Y�擾 <BR>
     * �y�ۗL���Y�e�[�u���z����A�ȉ��̏������w�肵�Y������ۗL���Y���擾����B<BR> 
     * [����] <BR>
     * �����h�c = �����h�c And <BR>
     * �⏕�����h�c = �⏕�����h�c And <BR>
     * �����h�c = �����h�c And <BR>
     * �~�j���敪 = 1�F�~�j�� <BR>
     * <BR>
     * �Q�j�@@�������v <BR>
     * �擾�������ׂĂۗ̕L���Y�����i�ۗL���Y.getQuantity()�j�̍��v�l��ԋp����<BR>
     * <BR>
     * @@param l_lngMainAccountId - (����ID)<BR>
     * @@param l_lngSubAccountId - (�⏕����ID)<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * @@return double
     */
    public double getMiniStockQuantity(long l_lngMainAccountId, long l_lngSubAccountId, long l_lngProductId)
    {
        final String STR_METHOD_NAME = " getMiniStockQuantity(long, long, long, boolean)";
        log.entering(STR_METHOD_NAME);
        List l_list = null;
        String l_strWhere = "(account_id = ?) and (sub_account_id = ?) and (product_id = ?) and (mini_stock_div = ?)";
        Object[] l_objWhere = new Object[4];
        l_objWhere[0] = new Long(l_lngMainAccountId);
        l_objWhere[1] = new Long(l_lngSubAccountId);
        l_objWhere[2] = new Long(l_lngProductId);
        l_objWhere[3] = new String("1");
        try
        {

            l_list = Processors.getDefaultProcessor().doFindAllQuery(AssetRow.TYPE, l_strWhere, l_objWhere);

            int l_intLength = l_list.size();
            double l_dblTotal = 0;
            for (int i = 0; i < l_intLength; i++)
            {
                AssetParams l_assetParams = new AssetParams((AssetRow) l_list.get(i));
                l_dblTotal = l_dblTotal + l_assetParams.getQuantity();
            }
            return l_dblTotal;
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException("System exception while searching product with market id :", l_ex);
        }
    }
    
    /**
     * (get�~�j���ۗL���Y�ꗗ)<BR>
     * �igetMiniStockAssets�j<BR>
     *  �w������Ɉ�v����ۗL���Y�I�u�W�F�N�g�̈ꗗ������h�c���ɕԋp����B<BR> 
     * <BR>
     * �P�j�@@���������ҏW <BR>
     * �P�|�P�j�@@��������������ҏW <BR>
     * " �����h�c = ?�@@and�@@�⏕�����h�c = ? and �����^�C�v = ? and �~�j���敪 = 1�F�~�j�� "��ҏW����B<BR> 
     * <BR>
     * �P�|�Q�j�@@�f�[�^�R���e�i�쐬 <BR>
     * ��������������� ? �ɊY������bind�l��z��ɕҏW����B <BR>
     * �� �⏕�����I�u�W�F�N�g�A�y�ш����̖����^�C�v���ݒ肷��B<BR>
     *  <BR>
     * �Q�j�@@�N�G�����s <BR>
     * QueryProcessor.doFindAllQuery()�ɂāA�ۗL���Y�I�u�W�F�N�g��List���擾�A�߂�l��ԋp����B <BR>
     * <BR>
     * [doFindAllQuery()�Ɏw�肷�����] <BR>
     * rowType�F�@@�ۗL���YRow.TYPE <BR>
     * where�F�@@�P�|�P�j�̌������������� <BR>
     * orderBy�F�@@(* ����ID�̍��ږ��j <BR>
     * conditions�F�@@null <BR>
     * bindVars�F�@@�P�|�Q�j�̌��������f�[�^�R���e�i<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j
     * @@return List
     */
    public List getMiniStockAssets(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMiniStockAssets(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType)";
        log.entering(STR_METHOD_NAME);

        //��������������
        String l_strWhere = " (account_id = ?) and (sub_account_id) = ? and (product_type = ?) and (mini_stock_div = ?)";
        Object[] l_objWhere = new Object[4];
        l_objWhere[0] = new Long(l_subAccount.getAccountId());
        l_objWhere[1] = new Long(l_subAccount.getSubAccountId());
        l_objWhere[2] = new Long(l_productType.intValue());
        l_objWhere[3] = new String("1");

        List l_lisReturnValue = new ArrayList();
        QueryProcessor l_queryProcessor = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(AssetRow.TYPE, l_strWhere, "product_id", null, l_objWhere);
        }
        catch (DataFindException e)
        {

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00670, this.getClass().getName() + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (DataNetworkException e)
        {
            String l_strMessage = "�����e�[�u�������� error";
            log.error(l_strMessage, e);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, e.getMessage(), e);
        }

        catch (DataQueryException e)
        {
            String l_strMessage = "�����e�[�u��������  error";
            log.error(l_strMessage, e);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, e.getMessage(), e);
        }
        List l_list2 = new ArrayList();
        for(int i = 0; i < l_lisReturnValue.size(); i++)
        {
            Asset l_asset = new WEB3EquityAsset((AssetRow)l_lisReturnValue.get(i));
            l_list2.add(l_asset);
        }
        
        return l_list2;
    }

    /**
     * �icreate���ό������׈ꗗ�j<BR>
     * <BR>
     * �����e�[�u���̃��R�[�h���猈�ό������ׂ̔z����쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�c���jcreate���ό������׈ꗗ�v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param isLong - (is����)<BR>
     * �������ǂ����̃t���O<BR>
     * true�F����<BR>
     * false�F����<BR>
     * @@param l_lngMarketId - (�s��ID)<BR>
     * �s��ID�B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID�B<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * �ŋ敪<BR>
     * 0�F���̑�<BR>
     * 1�F���<BR>
     * 2�F����<BR>
     * 3�F������������򒥎�<BR>
     * (TaxTypeEnum�ɂĒ�`)<BR>
     * @@param l_strRepaymentType - (�ٍϋ敪)<BR>
     * �ٍϋ敪<BR>
     * 1�F���x�M�p<BR>
     * 2�F��ʐM�p<BR>
     * @@param l_dblRepaymentNum - (�ٍϊ����l)<BR>
     * �ٍϊ����l�B<BR>
     * @@throws WEB3BaseException
     * @@return WEB3MarginCloseMarginContractUnit[]
     */
    public WEB3MarginCloseMarginContractUnit[] createCloseMarginContracts(
        WEB3GentradeSubAccount l_subAccount,
        boolean isLong,
        long l_lngMarketId,
        long l_lngProductId,
        TaxTypeEnum l_taxType,
        String l_strRepaymentType,
        double l_dblRepaymentNum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCloseMarginContracts(WEB3GentradeSubAccount, boolean, long, long, TaxTypeEnum, String, double)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. ��������������̍쐬
        String l_strWhere = " and market_id = ?"
                          + " and contract_type = ?"
					      + " and product_id = ?"
					      + " and quantity != ?"
					      + " and tax_type = ?"
					      + " and repayment_type = ?"
                          + " and repayment_num = ?";
        
        //1.2. ���������f�[�^�R���e�i�̍쐬
        String[] l_strData = new String[7];
        l_strData[0] = Long.toString(l_lngMarketId);
        if (isLong)
        {
            l_strData[1] = Integer.toString(ContractTypeEnum.LONG.intValue());
        }
        else
        {
            l_strData[1] = Integer.toString(ContractTypeEnum.SHORT.intValue());
        }
        l_strData[2] = Long.toString(l_lngProductId);
        l_strData[3] = Integer.toString(0);
        l_strData[4] = Integer.toString(l_taxType.intValue());
        l_strData[5] = l_strRepaymentType;
        l_strData[6] = Double.toString(l_dblRepaymentNum);
        
        //1.3. �\�[�g����(order by��)������̍쐬
        String l_strSort;
        if (isLong)
        {
            l_strSort = "open_date asc, first_open_date asc, contract_price asc";
        }
        else
        {
            l_strSort = "open_date asc, first_open_date asc, contract_price desc";
        }
        
        //1.4. get�����ꗗ()
        List l_lisContracts = this.getContracts(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            l_strWhere,
            l_strSort,
            l_strData);
        
        if(l_lisContracts == null || l_lisContracts.size() == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits = null;
        
        //1.6. �擾���������I�u�W�F�N�g��ں��ސ�������Loop����
        if (l_lisContracts != null)
        {
            int l_intSize = l_lisContracts.size();
            l_closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[l_intSize];
            for (int i = 0;i < l_intSize;i++)
            {
                EqtypeContractRow l_contractRow = (EqtypeContractRow)l_lisContracts.get(i);
                l_closeMarginContractUnits[i] = new WEB3MarginCloseMarginContractUnit();
                l_closeMarginContractUnits[i].id = Long.toString(l_contractRow.getContractId());
                l_closeMarginContractUnits[i].orderQuantity = null;
                l_closeMarginContractUnits[i].settlePriority = null;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_closeMarginContractUnits;
    }

    /**
     * �iget���ϒ������ԍώw����j<BR>
     * <BR>
     * �w�茚���ɊY�����錈�ϒ������̌����ԍώw����̈ꗗ���擾����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�ԍώw����ꗗ���擾�B<BR>
     * �@@this.get�ԍώw����(����.����ID�j�ŁA<BR>
     * �@@���Y�����ɑ΂���ԍώw����I�u�W�F�N�g�̈ꗗ���擾����B<BR>
     * <BR>
     * �R�j�@@�Q�j�Ŏ擾�����ԍώw����v�f������Loop�����B<BR>
     * �@@�@@�@@���ԍϊ��蓖�ĂȂ��v�f�A�N���[�Y�����̗v�f�����O����B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�ԍϒ������ʂ��擾�@@<BR>
     * �@@�@@�ԍώw����.�s��m�F�ϕԍϐ��� == NaN�i��t���ρj�̏ꍇ�́A<BR>
     * �@@�@@�ԍώw����.�ԍϒ������ʂ��g�p����B<BR>
     * �@@�@@�ԍώw����.�s��m�F�ϕԍϐ��� != NaN�i��t�ρj�̏ꍇ�́A<BR>
     * �@@�@@�ԍώw����.�s��m�F�ϕԍϐ��ʂ��g�p����B<BR>
     *     ���ԍϒ�������==0�̏ꍇ�́A�ȍ~��Loop���������s��Ȃ�(continue;)<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�����P��ID���擾�@@<BR>
     * �@@�@@�����P��ID���ԍώw����.get�����P��ID()<BR>
     * <BR>
     * �@@�R�|�R�j�@@�����P�ʃI�u�W�F�N�g���擾<BR>
     * �@@�@@�����P�ʁ��g�����������}�l�[�W��.getOrderUnit(�����P��ID)<BR>
     * <BR>
     * �@@�R�|�S�j�@@�����L����Ԃ̔���<BR>
     * �@@�@@�����P��.�����L�����==�h�I�[�v���h�̏ꍇ�̂݁A<BR>
     * �@@�@@ArrayList�ɕԍώw����I�u�W�F�N�g��ǉ�<BR>
     * �@@<BR>
     * �S�j�@@ArrayList.toArray( )�ŕԍώw����I�u�W�F�N�g�̔z���ԋp����<BR>
     * <BR>
     * @@param l_lngContractId - (����ID)<BR>
     * ����ID�B<BR>
     * @@throws WEB3BaseException
     * @@return EqTypeClosingContractSpec[]
     */
    public EqTypeClosingContractSpec[] getContractOrderingClosingContractSpecInfo(
        long l_lngContractId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractOrderingClosingContractSpecInfo(long)";
        log.entering(STR_METHOD_NAME);
        
        EqTypeClosingContractSpec[] l_closingContractSpecs = null;
        
        // �P�j�@@ArrayList�𐶐�����B
        List l_lisClosingContractSpec = new ArrayList();
        
        // �Q�j�@@�ԍώw����ꗗ���擾�B
        EqTypeClosingContractSpec[] l_specs = this.getClosingContractSpecs(l_lngContractId);
        
        // �R�j�@@�Q�j�Ŏ擾�����ԍώw����v�f������Loop�����B
        if (l_specs != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
			TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
			WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();

            for (int i = 0;i < l_specs.length;i++)
            {
                // �@@�R�|�P�j�@@�ԍϒ������ʂ��擾
                double l_dblQuantity;
				EqtypeClosingContractSpecRow l_specsRow = (EqtypeClosingContractSpecRow)l_specs[i].getDataSourceObject();
				if (l_specsRow.getConfirmedQuantityIsNull() == true)
				{
					l_dblQuantity = l_specsRow.getQuantity();
				}
				else
				{
					l_dblQuantity = l_specsRow.getConfirmedQuantity();
				}
                if (l_dblQuantity == 0.0D)
                {
                	continue;
                }
                // �@@�R�|�Q�j�@@�����P��ID���擾
                long l_lngOrderUnitId = l_specs[i].getOrderUnitId();
                // �@@�R�|�R�j�@@�����P�ʃI�u�W�F�N�g���擾
                EqTypeOrderUnit l_orderUnit = null;
                try
                {
                    l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfe.getMessage(),
                        l_nfe);
                }
                // �@@�R�|�S�j�@@�����L����Ԃ̔���
                if (OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
                {
                    l_lisClosingContractSpec.add(l_specs[i]);
                }
            }
            
            // �S�j�@@ArrayList.toArray( )�ŕԍώw����I�u�W�F�N�g�̔z���ԋp����
            int l_intSize = l_lisClosingContractSpec.size();            
            l_closingContractSpecs = new EqTypeClosingContractSpec[l_intSize];
            l_lisClosingContractSpec.toArray(l_closingContractSpecs);
        }
        log.exiting(STR_METHOD_NAME);
        return l_closingContractSpecs;
    }

    /**
     * �icalc���o����䗦�j<BR>
     * <BR>
     * �����̌����A�y�ъ����ڋq���薾�ׂ��A���o��v�Z���Ɏg�p������䗦���v�Z��<BR>
     * �ԋp����B<BR>
     * �i* �g���|�W�V�����w���p�[.calc���o����䗦( )��delegate����j<BR>
     * <BR>
     * @@param l_dblBalance - (�����c��)<BR>
     * �����c���B
     * @@param l_dblClosingExecutedQuantity - (���ϖ�萔��)<BR>
     * ���ϖ�萔�ʁi������́A�Y�������ւ̊��蓖�ĕ����ʁj�B
     * @@return double
     */
    public double calcExpensesFactorRatio(double l_dblBalance, double l_dblClosingExecutedQuantity)
    {
        final String STR_METHOD_NAME = "calcExpensesFactorRatio(double, double)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return ((WEB3EquityPositionManagerHelper)m_helper).calcExpensesFactorRatio(l_dblBalance, l_dblClosingExecutedQuantity);
    }

    /**
     * �iget�����ڋq���薾��ListBy�����P��Plus�����j<BR>
     * <BR>
     * �w�肳�ꂽ�����f�[�^�{�����f�[�^�ɑ΂���A<BR>
     * ������v�Z�ΏۂƂȂ銔���ڋq���薾��Params��List���擾����B<BR>
     * �i* �g���|�W�V�����w���p�[.�g���f�[�^�}�l�[�W��.get�����ڋq���薾��ListBy�����P��Plus����( )��<BR>
     * �@@�@@delegate����j<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * @@param l_lngContractId - (����ID)<BR>
     * @@throws WEB3BaseException
     * @@return double
     */
    public List getFinTransactionListByOrderUnitPlusContract(
        long l_lngOrderUnitId,
        long l_lngContractId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFinTransactionListByOrderUnitPlusContract(long, long)";
        log.entering(STR_METHOD_NAME);
        WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager l_dataManager =
            (WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager)((WEB3EquityPositionManagerHelper)m_helper).getPersistenceManager();
        log.exiting(STR_METHOD_NAME);
        return l_dataManager.getFinTransactionListByOrderUnitPlusContract(l_lngOrderUnitId, l_lngContractId);
    }

    /**
     * �icreate���ό����G���g���j<BR>
     * <BR>
     * �����P��ID�ɊY�����錈�ό����̃G���g�����쐬����B <BR>
     * <BR>
     * �i�V�[�P���X�}�j <BR>
     * �u�i�M�p�c���jcreate���ό����G���g���v�Q��<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * @@throws WEB3BaseException
     * @@return EqTypeSettleContractOrderEntry[]
     */
    public EqTypeSettleContractOrderEntry[] createCloseMarginContractEntry(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createCloseMarginContractEntry(long)";
        log.entering(STR_METHOD_NAME);

        EqTypeSettleContractOrderEntry[] l_contractOrderEntry = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        try
        {
            //1.1. getOrderUnit(�����P��ID : long)
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            //1.2. getContractsToClose()
            EqTypeClosingContractSpec[] l_specs = null;
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                EqTypeContractSettleOrderUnit l_newOrderUnit = (EqTypeContractSettleOrderUnit)l_orderUnit;
                l_specs = l_newOrderUnit.getContractsToClose();
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                EqTypeContractSwapOrderUnit l_newOrderUnit = (EqTypeContractSwapOrderUnit)l_orderUnit;
                l_specs = l_newOrderUnit.getContractsToClose();
            }
            //1.3. ArrayList()
            ArrayList l_array = new ArrayList();
            int l_size = 0;
            if (l_specs != null)
            {
                l_size = l_specs.length;
            }
            //1.4. �ԍώw����̗v�f����Loop����
            for (int i = 0;i < l_size;i++)
            {
                EqTypeClosingContractSpec l_spec = l_specs[i];
                //1.4.1. getContractId()
                long l_lngContractId = l_spec.getContractId();
                //1.4.2. getQuantity()
                double l_dblQuantity = l_spec.getQuantity();
                //1.4.3. EqTypeSettleContractOrderEntry()
                EqTypeSettleContractOrderEntry l_entry = new EqTypeSettleContractOrderEntry(l_lngContractId, l_dblQuantity);
                //1.4.4. add()
                l_array.add(l_entry);
            }
            //1.5. toArray()
            l_contractOrderEntry = new EqTypeSettleContractOrderEntry[l_array.size()];
            l_array.toArray(l_contractOrderEntry);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("Not Found Exception");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.entering(STR_METHOD_NAME);
        return l_contractOrderEntry;
    }

    /**
     * �iget�ۗL���Y�ꗗ�j<BR>
     * <BR>
     * �igetAssets�̃I�[�o�[���[�h�j<BR>
     * �w������Ɉ�v����ۗL���Y�I�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����������ǉ�����B<BR>
     * <BR>
     * �Q�|�P�j�@@����.��������������̐擪�ɁA<BR>
     * �@@"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ? and �~�j���敪 = 0�FDEFAULT�i�~�j���łȂ��j  and (����+���t�s�\����) > 0"<BR>
     * �@@��t������B<BR>
     * <BR>
     * �Q�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@������ID�A�⏕����ID�́A�����̕⏕�����I�u�W�F�N�g���ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�������^�C�v�́A�����̖����^�C�v���ݒ肷��B<BR>
     * <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�ۗL���Y�I�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(,�ۗL���YRow.TYPE<BR>
     *                                      �Q�|�P�j�̌�������������,<BR>
     *                                      �����̃\�[�g����,<BR>
     *                                      null,<BR>
     *                                      �Q�|�Q�j�̌��������f�[�^�R���e�i)<BR>
     * <BR>
     * �S�j�@@�������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j<BR>
     * @@param l_strSearchString - (��������������)<BR>
     * ��������������I�u�W�F�N�g<BR>
     * @@param l_searchCondContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i�I�u�W�F�N�g<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getAssets(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchString,
        String[] l_searchCondContainers,
        String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssets(SubAccount, ProductTypeEnum,String, String[] ,String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //��������������
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? and mini_stock_div = ? and (quantity + quantity_cannot_sell) > 0";
        List l_lisResults = null;
        if (l_strSearchString != null && l_strSearchString.length() > 0)
        {
            l_strWhere += l_strSearchString;
        }
        try
        {
            int l_intCondParamLen = 0;
            if (l_searchCondContainers != null)
            {
                l_intCondParamLen = l_searchCondContainers.length;
            }

            Object[] l_objBinds = new Object[4 + l_intCondParamLen];
            l_objBinds[0] = new Long(l_subAccount.getAccountId());
            l_objBinds[1] = new Long(l_subAccount.getSubAccountId());
            l_objBinds[2] = l_productType;
            l_objBinds[3] = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
            for (int i = 0; i < l_intCondParamLen; i++)
            {
                l_objBinds[4 + i] = l_searchCondContainers[i];
            }

            QueryProcessor l_qp = null;

            l_qp = Processors.getDefaultProcessor();
            List l_lisAssets = l_qp.doFindAllQuery(AssetRow.TYPE, l_strWhere, l_strSortCond, null, l_objBinds);
            int l_assetCount = l_lisAssets.size();
            l_lisResults = new ArrayList(l_assetCount);
            for (int i = 0; i < l_assetCount; i++)
            {
                l_lisResults.add(new WEB3EquityAsset((AssetRow)l_lisAssets.get(i)));
            }
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }
        catch (IllegalStateException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME, e.getMessage(), e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisResults;
    }
    
    /**
     * (create�����ώc������)<BR>
     * <BR>
     * �����ς̎c���Ɖ�ׂ�1���׍쐬����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�M�p�c���jcreate�����ώc���Ɖ�ׁv�Q�ƁB<BR>
     * <BR>
     * @@params l_subAccount - (�⏕����)<BR><BR>
     * @@params l_contract - (����)<BR>
     * @@return WEB3MarginBalanceReferenceDetailUnit - (�M�p����c���Ɖ��)<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3MarginBalanceReferenceDetailUnit createUnCloseMarginBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, WEB3EquityContract l_contract)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createUnCloseMarginBalanceReferenceUnit(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        // �M�p����c���Ɖ�׃C���X�^���X�𐶐�
        WEB3MarginBalanceReferenceDetailUnit l_balanceReferenceUnit =
            new WEB3MarginBalanceReferenceDetailUnit();
        
        // �����������擾
        WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
        
        // �������ʁA���b�N�����ʂ��擾
        double l_dblQuantity = l_contract.getQuantity();
        double l_dblLockedQuantity = l_contract.getLockedQuantity();
        
        // �����������̎擾
        WEB3EquityProductQuote l_productQuote = this.getContractCurrentPriceInfo(l_subAccount, l_contract);
        
        // ����萔�ʂ̎Z�o
        double l_dblUnExecQuantity = l_dblQuantity - l_dblLockedQuantity;
        
        double l_dblCurrentPrice = 0.0D;
        // ����
        if (l_productQuote != null)
        {
	        l_dblCurrentPrice = l_productQuote.getQuote();
        }
        
        // �����
        double l_dblContractAmount = l_contract.getContractAmount(l_dblUnExecQuantity);
        EqtypeContractRow l_contractRow =
            (EqtypeContractRow)l_contract.getDataSourceObject();
        // ���萔��
        double l_dblSetupFee = l_contract.getSetupFee(l_dblQuantity) - l_contract.getSetupFee(l_dblLockedQuantity);
        // ���萔�������
        double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblQuantity) - l_contract.getSetupFeeTax(l_dblLockedQuantity);
        // ���`������
        double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblQuantity) - l_contract.getNameTransferFee(l_dblLockedQuantity);
        // ���`�����������
        double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblQuantity) - l_contract.getNameTransferFeeTax(l_dblLockedQuantity);
        // �Ǘ���
        double l_dblManagementFee = l_contract.getManagementFee(l_dblQuantity) - l_contract.getManagementFee(l_dblLockedQuantity);
        // �Ǘ�������
        double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblQuantity) - l_contract.getManagementFeeTax(l_dblLockedQuantity);
        // ������
        double l_dblInterestFee = l_contract.getInterestFee(l_dblQuantity) - l_contract.getInterestFee(l_dblLockedQuantity);
        // �t����
        double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblQuantity) - l_contract.getPayInterestFee(l_dblLockedQuantity);
        // �݊���
        double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblQuantity) - l_contract.getLoanEquityFee(l_dblLockedQuantity);
        // ���̑�
        double l_dblOther = l_contract.getOther(l_dblQuantity) - l_contract.getOther(l_dblLockedQuantity);
        
        // �c���Ɖ�ׂɃv���p�e�B�Z�b�g
        // ID
        l_balanceReferenceUnit.id = String.valueOf(l_contract.getContractId());
        // �����R�[�h
        l_balanceReferenceUnit.productCode = l_eqtypeProduct.getProductCode();
        // ������
        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
        l_balanceReferenceUnit.productName = l_eqtypeProductRow.getStandardName();
        
        // �g�����Z�I�u�W�F�N�g�}�l�[�W�����擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;
        try
        {
	        l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�s��I�u�W�F�N�g�̎擾�Ɏ��s���܂���");
        }
        // �s��R�[�h
        l_balanceReferenceUnit.marketCode = l_market.getMarketCode();
        // �����敪
        // ����.�ŋ敪 == "���"�̏ꍇ
        if (l_contractRow.getTaxType().equals(TaxTypeEnum.NORMAL))
        {
            // "���"���Z�b�g
            l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.NORMAL;
        }
        // ����.�ŋ敪 == "����" or "������������򒥎�"�̏ꍇ
        else if (l_contractRow.getTaxType().equals(TaxTypeEnum.SPECIAL)
            || l_contractRow.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
        {
            // "����"���Z�b�g
            l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.SPECIAL;
        }
        // ���敪
        if (ContractTypeEnum.LONG.equals(l_contractRow.getContractType()))
        {
            l_balanceReferenceUnit.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_contractRow.getContractType()))
        {
            l_balanceReferenceUnit.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }
        // �ٍϋ敪�A�ٍϊ����l
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        l_repaymentUnit.repaymentDiv = l_contractRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = String.valueOf(l_contractRow.getRepaymentNum());
        l_balanceReferenceUnit.repayment = l_repaymentUnit;
        
        // ����
        l_balanceReferenceUnit.openDate = l_contract.getOpenDate();
        // ���P��
        l_balanceReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        // ������
        l_balanceReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblUnExecQuantity);
        // ����
        l_balanceReferenceUnit.closeDate = l_contract.getCloseDate();
        // �����
        l_balanceReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmount);
        // ���Ϗ�ԋ敪
        l_balanceReferenceUnit.settlementState = WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED;
        // �ԍω\�t���O
        l_balanceReferenceUnit.closeMarginFlag = true;
        // �������n�\�t���O
        l_balanceReferenceUnit.swapFlag = true;
        // ���萔��
        l_balanceReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblSetupFee + l_dblSetupFeeTax);
        // ������
        l_balanceReferenceUnit.interestFee = WEB3StringTypeUtility.formatNumber(l_dblInterestFee);
        // �t����
        l_balanceReferenceUnit.payInterestFee = WEB3StringTypeUtility.formatNumber(l_dblPayInterestFee);
        // �݊���
        l_balanceReferenceUnit.loanEquityFee = WEB3StringTypeUtility.formatNumber(l_dblLoanEquityFee);
        // ������
        l_balanceReferenceUnit.setupFee = WEB3StringTypeUtility.formatNumber(l_dblNameTransferFee + l_dblNameTransferFeeTax);
        // �Ǘ���
        l_balanceReferenceUnit.managementFee = WEB3StringTypeUtility.formatNumber(l_dblManagementFee + l_dblManagementFeeTax);
        // ���̑�
        l_balanceReferenceUnit.otherwise = WEB3StringTypeUtility.formatNumber(l_dblOther);
        
        // get�����������()�̖߂�l != null�̏ꍇ
        if (l_productQuote != null)
        {
	        // �]�����v
	        double l_dblProfitLoss = l_contract.getAppraisalProfitOrLoss(l_dblCurrentPrice, l_dblUnExecQuantity);
	        // �]�����v
	        l_balanceReferenceUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLoss);
	        
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityBizLogicProvider l_bizLogic =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            // ���o��v
            double l_dblTotalCost = l_bizLogic.calcExpenses(0,
                0,
                l_dblSetupFee,
                l_dblSetupFeeTax,
                l_dblNameTransferFee,
                l_dblNameTransferFeeTax,
                l_dblManagementFee,
                l_dblManagementFeeTax,
                l_dblInterestFee,
                l_dblPayInterestFee,
                l_dblLoanEquityFee,
                l_dblOther,
                l_contractRow.getContractType());
	        double l_dblProfitLossCost = l_dblProfitLoss - l_dblTotalCost;
	        // �]�����v�i���o��l���j
	        l_balanceReferenceUnit.appraisalProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCost);
	        // ����
	        l_balanceReferenceUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
	        // �O����
            l_balanceReferenceUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_productQuote.getComparedPreviousDay());
            // �����擾����(HH:MM)
            log.debug("�����擾�敪:[" + l_productQuote.getQuoteFromDiv() + "]");
            Timestamp l_quoteTime = l_productQuote.getQuoteTime();
            if (l_quoteTime != null)
            {
                l_balanceReferenceUnit.currentPriceTime = WEB3DateUtility.formatDate(l_quoteTime, "HH:mm");
            }
        }
        else
        {
            // �������擾�ł��Ȃ������ꍇ��null���Z�b�g
            l_balanceReferenceUnit.appraisalProfitLoss = null;
            l_balanceReferenceUnit.appraisalProfitLossCost = null;
            l_balanceReferenceUnit.currentPrice = null;
            l_balanceReferenceUnit.comparedPreviousDay = null;
            l_balanceReferenceUnit.currentPriceTime = null;
        }
        
        // �V�K���\�t���O
        l_balanceReferenceUnit.tradingFlag = true;
        
        log.exiting(STR_METHOD_NAME);
        return l_balanceReferenceUnit;
    }
    
    /**
     * (create���ϒ��c������)<BR>
     * <BR>
     * ���ϒ��̎c���Ɖ�ׂ�1���׍쐬����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�M�p�c���jcreate���ϒ��c���Ɖ�ׁv�Q�ƁB<BR>
     * <BR>
     * @@params l_subAccount - (�⏕����)<BR><BR>
     * @@params l_contract - (����)<BR>
     * @@return WEB3MarginBalanceReferenceDetailUnit - (�M�p����c���Ɖ��)<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3MarginBalanceReferenceDetailUnit createClosingMarginBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, WEB3EquityContract l_contract)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosingMarginBalanceReferenceDetailUnit(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        // �M�p����c���Ɖ�׃C���X�^���X�𐶐�
        WEB3MarginBalanceReferenceDetailUnit l_balanceReferenceUnit =
            new WEB3MarginBalanceReferenceDetailUnit();
        
        // �����������擾
        WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
        
        // ���b�N�����ʂ��擾
        double l_dblLockedQuantity = l_contract.getLockedQuantity();
        
        // ���ϒ������ԍώw������擾
        EqTypeClosingContractSpec[] l_eqtypeClosingContractSpecs = 
            this.getContractOrderingClosingContractSpecInfo(l_contract.getContractId());
            
        // �����������̎擾
        WEB3EquityProductQuote l_productQuote = this.getContractCurrentPriceInfo(l_subAccount, l_contract);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        double l_dblProfitLossTotal = 0.0D;
        
        // get���ϒ������ԍώw����()�̖߂�l�̗v�f����Loop����
        if (l_productQuote != null)
        {
            for (int i = 0; i < l_eqtypeClosingContractSpecs.length; i++)
            {
                EqtypeClosingContractSpecRow l_specRow =
                    (EqtypeClosingContractSpecRow)l_eqtypeClosingContractSpecs[i].getDataSourceObject();
                    
                double l_dblConfirmedQuantity = 0;
                // �s��m�F�ϕԍϒ������� != null�̏ꍇ
                if (!l_specRow.getConfirmedQuantityIsNull())
                {
                    // �s��m�F�ϕԍϒ������ʂ̎擾
                    l_dblConfirmedQuantity = l_specRow.getConfirmedQuantity();
                }
                else
                {
                    // �ԍϒ������ʂ̎擾
                    l_dblConfirmedQuantity = l_specRow.getQuantity();
                }
                
                // �ԍϖ�萔�ʂ̎擾
                double l_dblExecutedQuantity = l_eqtypeClosingContractSpecs[i].getExecutedQuantity();
                
                // �v�Z�P��
                double l_dblCalcPrice = l_productQuote.getQuote();
                
                // �ԍϒ��������ʂ̎Z�o
                double l_dblCloseOrderingQuantity = l_dblConfirmedQuantity - l_dblExecutedQuantity;
                
                // �]�����v���Z�o����
                double l_dblProfitLoss = l_contract.getAppraisalProfitOrLoss(l_dblCalcPrice, l_dblCloseOrderingQuantity);
                
                // �]�����v���W�v����
                l_dblProfitLossTotal += l_dblProfitLoss;
            }
        }
        
        // �����
        double l_dblContractAmount = l_contract.getContractAmount(l_dblLockedQuantity);
        // ���萔��
        double l_dblSetupFee = l_contract.getSetupFee(l_dblLockedQuantity);
        // ���萔�������
        double l_dblSetupFeeTax = l_contract.getSetupFeeTax(l_dblLockedQuantity);
        // ���`������
        double l_dblNameTransferFee = l_contract.getNameTransferFee(l_dblLockedQuantity);
        // ���`�����������
        double l_dblNameTransferFeeTax = l_contract.getNameTransferFeeTax(l_dblLockedQuantity);
        // �Ǘ���
        double l_dblManagementFee = l_contract.getManagementFee(l_dblLockedQuantity);
        // �Ǘ�������
        double l_dblManagementFeeTax = l_contract.getManagementFeeTax(l_dblLockedQuantity);
        // ������
        double l_dblInterestFee = l_contract.getInterestFee(l_dblLockedQuantity);
        // �t����
        double l_dblPayInterestFee = l_contract.getPayInterestFee(l_dblLockedQuantity);
        // �݊���
        double l_dblLoanEquityFee = l_contract.getLoanEquityFee(l_dblLockedQuantity);
        // ���̑�
        double l_dblOther = l_contract.getOther(l_dblLockedQuantity);
        
        // �c���Ɖ�ׂɃv���p�e�B�Z�b�g
        // ID
        l_balanceReferenceUnit.id = String.valueOf(l_contract.getContractId());
        // �����R�[�h
        l_balanceReferenceUnit.productCode = l_eqtypeProduct.getProductCode();
        // ������
        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
        l_balanceReferenceUnit.productName = l_eqtypeProductRow.getStandardName();
        
        // �g�����Z�I�u�W�F�N�g�}�l�[�W�����擾
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;
        try
        {
            l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�s��I�u�W�F�N�g�̎擾�Ɏ��s���܂���");
        }
        // �s��R�[�h
        l_balanceReferenceUnit.marketCode = l_market.getMarketCode();
        // �����敪
        EqtypeContractRow l_contractRow =
            (EqtypeContractRow)l_contract.getDataSourceObject();
        // ����.�ŋ敪 == "���"�̏ꍇ
        if (l_contractRow.getTaxType().equals(TaxTypeEnum.NORMAL))
        {
            // "���"���Z�b�g
            l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.NORMAL;
        }
        // ����.�ŋ敪 == "����" or "������������򒥎�"�̏ꍇ
        else if (l_contractRow.getTaxType().equals(TaxTypeEnum.SPECIAL)
            || l_contractRow.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
        {
            // "����"���Z�b�g
            l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.SPECIAL;
        }
        // ���敪
        if (ContractTypeEnum.LONG.equals(l_contractRow.getContractType()))
        {
            l_balanceReferenceUnit.contractType = "" + ContractTypeEnum.LONG.intValue();
        }
        else if (ContractTypeEnum.SHORT.equals(l_contractRow.getContractType()))
        {
            l_balanceReferenceUnit.contractType = "" + ContractTypeEnum.SHORT.intValue();
        }
        // �ٍϋ敪�A�ٍϊ����l
        WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
        l_repaymentUnit.repaymentDiv = l_contractRow.getRepaymentType();
        l_repaymentUnit.repaymentTimeLimit = String.valueOf(l_contractRow.getRepaymentNum());
        l_balanceReferenceUnit.repayment = l_repaymentUnit;
        
        // ����
        l_balanceReferenceUnit.openDate = l_contract.getOpenDate();
        // ���P��
        l_balanceReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
        // ������
        l_balanceReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblLockedQuantity);
        // ����
        l_balanceReferenceUnit.closeDate = l_contract.getCloseDate();
        // �����
        l_balanceReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmount);
        // ���Ϗ�ԋ敪
        l_balanceReferenceUnit.settlementState = WEB3EquitySettlementStateDef.SETTLING;
        // �ԍω\�t���O
        l_balanceReferenceUnit.closeMarginFlag = false;
        // �������n�\�t���O
        l_balanceReferenceUnit.swapFlag = false;
        // ���萔��
        l_balanceReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblSetupFee + l_dblSetupFeeTax);
        // ������
        l_balanceReferenceUnit.interestFee = WEB3StringTypeUtility.formatNumber(l_dblInterestFee);
        // �t����
        l_balanceReferenceUnit.payInterestFee = WEB3StringTypeUtility.formatNumber(l_dblPayInterestFee);
        // �݊���
        l_balanceReferenceUnit.loanEquityFee = WEB3StringTypeUtility.formatNumber(l_dblLoanEquityFee);
        // ������
        l_balanceReferenceUnit.setupFee = WEB3StringTypeUtility.formatNumber(l_dblNameTransferFee + l_dblNameTransferFeeTax);
        // �Ǘ���
        l_balanceReferenceUnit.managementFee = WEB3StringTypeUtility.formatNumber(l_dblManagementFee + l_dblManagementFeeTax);
        // ���̑�
        l_balanceReferenceUnit.otherwise = WEB3StringTypeUtility.formatNumber(l_dblOther);
        
        // get�����������()�̖߂�l != null�̏ꍇ
        if (l_productQuote != null)
        {
	        // �]�����v
	        l_balanceReferenceUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossTotal);
	        
            WEB3EquityBizLogicProvider l_bizLogic =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            // ���o��v
            double l_dblTotalCost = l_bizLogic.calcExpenses(0,
                0,
                l_dblSetupFee,
                l_dblSetupFeeTax,
                l_dblNameTransferFee,
                l_dblNameTransferFeeTax,
                l_dblManagementFee,
                l_dblManagementFeeTax,
                l_dblInterestFee,
                l_dblPayInterestFee,
                l_dblLoanEquityFee,
                l_dblOther,
                l_contractRow.getContractType());
            double l_dblProfitLossCost = l_dblProfitLossTotal - l_dblTotalCost;
	        // �]�����v�i���o��l���j
	        l_balanceReferenceUnit.appraisalProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCost);
            // ����
            l_balanceReferenceUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_productQuote.getQuote());
            // �O����
            l_balanceReferenceUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_productQuote.getComparedPreviousDay());
            // �����擾����(HH:MM)
            log.debug("�����擾�敪:[" + l_productQuote.getQuoteFromDiv() + "]");
            Timestamp l_quoteTime = l_productQuote.getQuoteTime();
            if (l_quoteTime != null)
            {
                l_balanceReferenceUnit.currentPriceTime = WEB3DateUtility.formatDate(l_quoteTime, "HH:mm");
            }
        }
        else
        {
            // �������擾�ł��Ȃ������ꍇ��null���Z�b�g
            l_balanceReferenceUnit.appraisalProfitLoss = null;
            l_balanceReferenceUnit.appraisalProfitLossCost = null;
            l_balanceReferenceUnit.currentPrice = null;
            l_balanceReferenceUnit.comparedPreviousDay = null;
            l_balanceReferenceUnit.currentPriceTime = null;
        }
        
        // �V�K���\�t���O
        l_balanceReferenceUnit.tradingFlag = true;
        
        log.exiting(STR_METHOD_NAME);
        return l_balanceReferenceUnit;
    }
    
    /**
     * (get�����������)<BR>
     * <BR>
     * �w�茚���̖����̎������(�����A�����擾���ԂȂ�)���擾����B <BR>
     * <BR>
     * ���L�菇�Ŏ擾������������ԋp����B <BR>
     * <BR>
     * �P�j�����R�[�h�̎擾 <BR>
     * �@@�������� = �p�����[�^.����.getProduct() <BR>
     * �@@�����R�[�h = ��������.get�����R�[�h() <BR>
     * <BR>
     * �Q�j�s��R�[�h�̎擾 <BR>
     * �@@�s�� = �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(����.�s��ID) <BR>
     * �@@�s��R�[�h = �s��.�s��R�[�h <BR>
     * <BR>
     * �R�j�������Z�b�g���肨��ю������̎擾 <BR>
     * �@@ThreadLocalSystemAttributesRegistry. <BR>
     * �@@getAttribute(CURRENT_PRICE_INFO)��Hashtable���擾 <BR>
     * <BR>
     * �@@�R�|�P�j�Y�������̎�����񂪃Z�b�g����Ă���ꍇ <BR>
     * �@@�@@�@@(Hashtable.get(�����R�[�h + �s��R�[�h) != null�̏ꍇ) <BR>
     * <BR>
     *         ������� = Hashtable.get(�����R�[�h + �s��R�[�h) <BR>
     * <BR>
     * �@@�R�|�Q�j�Y�������̎�����񂪃Z�b�g����Ă��Ȃ��ꍇ <BR>
     * �@@�@@�@@(Hashtable.get(�����R�[�h + �s��R�[�h) == null�̏ꍇ) <BR>
     * <BR>
     * �@@�@@�R�|�Q�|�P�j�������̎擾 <BR>
     * �@@�@@�@@�@@����������� = �p�����[�^.����.getTradedProduct() <BR>
     * �@@�@@�@@�@@������� = �g���v���_�N�g�}�l�[�W��.get�������(�����������, "���A��") <BR>
     * �@@�@@ <BR>
     * �@@�@@�R�|�Q�|�Q�j�������̒ǉ� <BR>
     * �@@�@@�@@�@@�擾����Hashtable�ɖ����R�[�h + �s��R�[�h��key�Ƃ��Ď�������ǉ� <BR>
     * �@@�@@�@@�@@Hashtable.put�i�����R�[�h + �s��R�[�h, �R�|�Q�|�P�j�ɂĎ擾�����������) <BR>
     * <BR>
     * �@@�@@�R�|�Q�|�R�j�����̃Z�b�g <BR>
     * �@@�@@�@@�@@ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ��������Z�b�g���� <BR>
     * �@@�@@�@@�@@�ݒ�L�[�F�@@CURRENT_PRICE_INFO <BR>
     * �@@�@@�@@�@@�l�F�@@�R�|�Q�|�Q�j�ɂĎ�������ǉ�����Hashtable <BR>
     * <BR>
     * �S�j�擾������������ԋp <BR>
     * <BR>
     * �����Y���\�b�h���g�p����ꍇ�́A�e�T�[�r�X�C���^�Z�v�^��onCall�ɂĎ������̃Z�b�g����(*)�A <BR>
     * �@@onReturn()�����onThrowable()���\�b�h���ɂĎ������̃N���A�������s������ <BR>
     * <BR>
     * (*)ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ� <BR>
     * �@@ThreadLocal�Ɏ������̕ϐ����Z�b�g���� <BR>
     * �@@�ݒ�L�[�F�@@CURRENT_PRICE_INFO <BR>
     * �@@�l�F�@@Hashtable(�V�K�ɍ쐬����Hashtable) <BR>
     * <BR>
     * @@params l_subAccount - (�⏕����)<BR>
     * @@params l_contract - (����)<BR>
     * @@return WEB3EquityProductQuote - (���������������)<BR>
     */
    public WEB3EquityProductQuote getContractCurrentPriceInfo(WEB3GentradeSubAccount l_subAccount, WEB3EquityContract l_contract)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCurrentPriceInfo(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        // �����R�[�h�̎擾
        WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
        String l_strProductCode = l_eqtypeProduct.getProductCode();
        
        // �s��R�[�h�̎擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;
        try
        {
	        l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�s��I�u�W�F�N�g�̎擾�Ɏ��s���܂���");
        }
        String l_strMarketCode = l_market.getMarketCode();
        
        // �������i�[���X�g�̎擾
        String l_strKey = l_strProductCode + l_strMarketCode;
        Hashtable l_htProductQuote = 
            (Hashtable)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3MarginAttributeNameDef.CURRENT_PRICE_INFO);
        
        // �������̎擾
        WEB3EquityProductQuote l_productQuote =
            (WEB3EquityProductQuote)l_htProductQuote.get(l_strKey);
        
        // �Y�������̎�����񂪃Z�b�g����Ă��Ȃ��ꍇ
        if (l_productQuote == null)
        {
            // ����������擾
            WEB3EquityTradedProduct l_eqtypeTradedProduct = null;
            try
            {
	            l_eqtypeTradedProduct =
	                (WEB3EquityTradedProduct)l_contract.getTradedProduct();
            }
            catch (Exception l_ex)
            {
                return null;
            }
            
            // ���������擾
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
                
            l_productQuote = l_productManager.getProductQuote(l_eqtypeTradedProduct, RealType.REAL);

			if (l_productQuote != null)
			{
            // �������̒ǉ�
            l_htProductQuote.put(l_strKey, l_productQuote);
            
            // ThreadLocal�Ɏ�������ǉ�����Hashtable���Z�b�g
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.CURRENT_PRICE_INFO,
                l_htProductQuote);
			}
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }
    
    /**
     * (get�~�j���ۗL���Y�ꗗ)<BR>
     * �igetMiniStockAssets�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �w������Ɉ�v����~�j���ۗL���Y�I�u�W�F�N�g�̈ꗗ��ԋp����B <BR>
     * <BR>
     * �P�j�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@����������ǉ�����B <BR>
     * <BR>
     * �Q�|�P�j�@@����.��������������̐擪�ɁA <BR>
     * �@@"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ? and �~�j���敪 = "1�F�~�j��"  and (����+���t�s�\����) > 0" <BR>
     * �@@��t������B <BR>
     * <BR>
     * �Q�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA <BR>
     * �@@�@@�@@�@@�@@��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B <BR>
     * �@@�@@�@@�@@�@@������ID�A�⏕����ID�́A�����̕⏕�����I�u�W�F�N�g���ݒ肷��B <BR>
     * �@@�@@�@@�@@�@@�������^�C�v�́A�����̖����^�C�v���ݒ肷��B <BR>
     * <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�ۗL���Y�I�u�W�F�N�g��List���擾����B <BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(,�ۗL���YRow.TYPE <BR>
     *                                      �Q�|�P�j�̌�������������, <BR>
     *                                      �����̃\�[�g����, <BR>
     *                                      null, <BR>
     *                                      �Q�|�Q�j�̌��������f�[�^�R���e�i) <BR>
     * <BR>
     * �S�j�@@�������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������
     * @@param l_strQueryDataContainerString - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getMiniStockAssets(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType, String l_strQueryString, String[] l_strQueryDataContainer, String l_strSortCond)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMiniStockAssets(WEB3GentradeSubAccount, WEB3GentradeSubAccount, String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        // �ǉ����錟�������̍쐬
        String l_strWhere = "account_id = ? "
                            + "and sub_account_id = ? "
                            + "and product_type = ? "
                            + "and mini_stock_div = ? "
                            + "and (quantity + quantity_cannot_sell) > 0 ";
        // �����̌��������̐擪�ɒǉ�
        if (l_strQueryString != null)
        {
	        l_strWhere += l_strQueryString;
        }
        
        // �ǉ����錟�������f�[�^�R���e�i�̍쐬
        ArrayList l_lstBinds = new ArrayList();
        // ����ID
        l_lstBinds.add(String.valueOf(l_subAccount.getAccountId()));
        // �⏕����ID
        l_lstBinds.add(String.valueOf(l_subAccount.getSubAccountId()));
        // �����^�C�v
        l_lstBinds.add(String.valueOf(l_productType.intValue()));
        // �~�j���敪
        l_lstBinds.add(WEB3MiniStockDivDef.MINI_STOCK);
        
        if (l_strQueryDataContainer != null)
        {
	        // �����̌��������f�[�^�R���e�i��ǉ�
	        for (int i = 0; i < l_strQueryDataContainer.length; i++)
	        {
	            l_lstBinds.add(l_strQueryDataContainer[i]);
	        }
        }
        
        String[] l_strBinds = (String[])l_lstBinds.toArray(new String[0]);
        List l_lstAssets = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstAssets = l_queryProcessor.doFindAllQuery(AssetRow.TYPE,
                                                        l_strWhere,
                                                        l_strSortCond,
                                                        null,
                                                        l_strBinds);
        } catch (DataException e) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                e.getMessage());
        }
        
        if (l_lstAssets == null || l_lstAssets.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        ArrayList l_lstReturnValue = new ArrayList();
        for(int i = 0; i < l_lstAssets.size(); i++)
        {
            Asset l_asset = new WEB3EquityAsset((AssetRow)l_lstAssets.get(i));
            l_lstReturnValue.add(l_asset);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lstReturnValue;
    }
    
    /**
     * (get��������)<BR>
     * ����.�����ɐݒ肷������icloseDate�j���v�Z���Ԃ��B<BR>
     * <BR>
     * �g���|�W�V�����w���p�[.get��������(����, �ٍϊ����l)��<BR>
     * �������Ϗ��idelegate�j����B<BR>
     * @@param l_datOpenDate - (����)<BR>
     * �����B
     * @@param l_dblRepaymentNum - (�ٍϊ����l)<BR>
     * �ٍϊ����l�B
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getContractCloseDate(
        Date l_datOpenDate,
        double l_dblRepaymentNum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCloseDate(Date, double)";
        log.entering(STR_METHOD_NAME);
        
        Date l_datContractCloseDate =
            ((WEB3EquityPositionManagerHelper)m_helper).getContractCloseDate(
                l_datOpenDate, l_dblRepaymentNum);
        
        log.exiting(STR_METHOD_NAME);
        return l_datContractCloseDate;
    }
    
    /**
     * (get����ListBy�����P��)<BR>
     * �w�肳�ꂽ�����f�[�^�ɑ΂���A�����f�[�^��S�Ď擾���A<BR>
     * ����Params��List���쐬���ĕԂ��B<BR>
     * <BR>
     * �g���f�[�^�}�l�[�W��.get����ListBy�����P��(�����P��ID)��delegate����B<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID�B
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getContractListByOrderUnit(
        long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractListByOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager l_dataManager =
            (WEB3EquityPositionManagerHelper.WEB3EquityPersistentDataManager)m_helper.getPersistenceManager();
        List l_lisContractListByOrderUnit =
            l_dataManager.getContractListByOrderUnit(l_lngOrderUnitId);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisContractListByOrderUnit;
    }

    /**
     * (get�����ꗗ)<BR>
     * �����̌ڋq�����W�ŁA�L���Ȍ�����S�Ď擾���A�ꗗ��ԋp����B <BR>
     * <BR>
     * �P�j�@@�����e�[�u���̌��������s����B <BR>
     * �@@QueryProcessor.doFindAllQuery()�ɂ��A <BR>
     * �@@�ȉ����������ɂāA�����e�[�u���ieqtype_contract�j����������B <BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@����.From����ID �� ����.����ID�@@���A <BR>
     * �@@�@@����.����ID �� ����.To����ID�@@���A <BR>
     * �@@�@@����.������ �� 0�@@���A <BR>
     * �@@�@@����.�����^�C�v �� �h�����h <BR>
     * <BR>
     * �@@�@@������ID�ŏ����iASC�j��sort���邱�ƁB <BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B <BR>
     * @@param l_rangeFrom - From����ID
     * @@param l_rangeTo - To����ID
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getContracts(long l_lngRangeFrom, long l_lngRangeTo) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getContracts(long, long)";
        log.entering(STR_METHOD_NAME);

        List l_returnValue = new ArrayList();

        // �����e�[�u���̌��������s����B
        String l_strWhere = "account_id >= ? and account_id <= ? and quantity != ? and product_type = ?";
        String l_strOrderBy = "account_id ASC";

        Object[] l_objWhereValue = new Object[4];
        l_objWhereValue[0] = new Long(l_lngRangeFrom);
        l_objWhereValue[1] = new Long(l_lngRangeTo);
        l_objWhereValue[2] = new BigDecimal(0);
        l_objWhereValue[3] = ProductTypeEnum.EQUITY;

        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();

            l_returnValue = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE,
                l_strWhere, l_strOrderBy, null, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPositionManager.class.getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPositionManager.class.getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_returnValue;
    }

    /**
     * (get�������ϊ������������ꗗ)<BR>
     * �����̌ڋq�����W�ŁA���ϊ�������������������S�Ď擾���A�ꗗ��ԋp����B<BR>
     * �����ϊ����́A�ݒ肵��������������ĎZ�o����B<BR>
     * <BR>
     * �P�j�@@�Ɩ����t���擾����B<BR>
     * <BR>
     * �@@GtlUtils.getTradingSystem().getBizDate()��Call����B<BR>
     * <BR>
     * �Q�j�@@�����e�[�u���̌��������s����B<BR>
     * �@@QueryProcessor.doFindAllQuery()�ɂ��A<BR>
     * �@@�ȉ����������ɂāA�����e�[�u���ieqtype_contract�j����������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@����.From����ID �� ����.����ID�@@���A<BR>
     * �@@�@@����.����ID �� ����.To����ID�@@���A<BR>
     * �@@�@@����.���� �� �������ϊ����i*1�j�@@���A<BR>
     * �@@�@@����.������ �� 0�@@���A<BR>
     * �@@�@@����.�����^�C�v �� �h�����h<BR>
     * <BR>
     * �@@�@@������ID�ŏ����iASC�j��sort���邱�ƁB<BR>
     * <BR>
     * �@@�@@�i*1�j�������ϊ����͈ȉ��v�Z���ɂ��Z�o����B<BR>
     * �@@�@@�@@�������ϊ��� �� �Ɩ����t �{ ����.�،����.�M�p�������ϊ����Z�o����<BR>
     * <BR>
     * �R�j�@@�Q�j�̌������ʂ�ԋp����B<BR>
     * @@param l_institution - �،����
     * @@param l_rangeFrom - From����ID
     * @@param l_rangeTo - To����ID
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getForcedSettleCloseDateContractList(
        Institution l_institution,
        long l_lngRangeFrom,
        long l_lngRangeTo)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getForcedSettleCloseDateContractList(Institution, long, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�Ɩ����t���擾����B
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //�Q�j�@@�����e�[�u���̌��������s����B
        List l_lisReturnValue = new ArrayList();

        String l_strWhere = "account_id >= ? and account_id <= ? and to_char(close_date,'yyyyMMdd') <= ? "
            + "and quantity != ? and product_type = ?";

        String l_strOrderBy = "account_id ASC";

        //�i*1�j�������ϊ����͈ȉ��v�Z���ɂ��Z�o����B
        InstitutionRow l_InstitutionRow = (InstitutionRow)l_institution.getDataSourceObject();
        Date l_datForcedSettleCloseDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(l_InstitutionRow.getForcedsettleorderClosedayCnt());

        Object[] l_objWhereValues = new Object[5];
        l_objWhereValues[0] = new Long(l_lngRangeFrom);
        l_objWhereValues[1] = new Long(l_lngRangeTo);
        l_objWhereValues[2] = WEB3DateUtility.formatDate(l_datForcedSettleCloseDate, "yyyyMMdd");
        l_objWhereValues[3] = new BigDecimal(0);
        l_objWhereValues[4] = ProductTypeEnum.EQUITY;

        QueryProcessor l_queryProcessor;

        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();

            l_lisReturnValue = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE,
                l_strWhere, l_strOrderBy, null, l_objWhereValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPositionManager.class.getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPositionManager.class.getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }
}
@
