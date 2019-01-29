head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���|�W�V�����}�l�[�W���w���p�[(WEB3BondPositionManagerHelper.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17 ����(���u) �V�K�쐬
                    2006/10/09 ���� (���u) �d�l�ύX�E���f��111
                    2006/10/12 �����(���u)WEB�V�J���W���̌������̑Ή��inewBigDecimal�����j
                    2006/10/16 ��іQ(���u) ���f��No.127
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondPositionManagerHelper;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondAssetCheckDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (���|�W�V�����}�l�[�W���w���p�[)<BR>
 * ���|�W�V�����}�l�[�W���w���p�[�N���X
 *
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3BondPositionManagerHelper extends BondPositionManagerHelper
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondPositionManagerHelper.class);

    public WEB3BondPositionManagerHelper(ProductTypeEnum l_typeEnum)
    {
        super(l_typeEnum);
    }


    public PersistentDataManager getPersistentDataManager()
    {
        return new WEB3BondPersistentDataManager();
    }
    /**
     * (applyTo�ۗL���Y�|�W�V����)applyToAssetPosition<BR>
     * �̃I�[�o�[���C�h<BR>
     * <BR>
     * �V�[�P���X�}�uapplyTo�ۗL���Y�|�W�V�����v���Q��<BR>
     * <BR>
     * =============================================== <BR>
     *�@@�@@�@@�@@�@@�V�[�P���X�} : �uapplyTo�ۗL���Y�|�W�V�����v<BR>
     *�@@�@@�@@�@@�@@��̈ʒu     : 1.6.1. get�ۗL���YParams == null�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O�B <BR>
     *�@@�@@�@@�@@�@@class        : WEB3BusinessLayerException <BR>
     *�@@�@@�@@�@@�@@tag          : BUSINESS_ERROR_00204 <BR>
     *=============================================== <BR>
     * <BR>
     * @@param l_bondFinTransactionParams - (BondFinTransactionParams)<BR>
     * BondFinTransactionParams
     * @@return List
     * @@throws DataException
     * @@roseuid 44D0354A0000
     */
    public List applyToAssetPosition(
        BondFinTransactionParams l_bondFinTransactionParams)
        throws DataException
    {
        final String STR_METHOD_NAME =
            " applyToAssetPosition(BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_bondFinTransactionParams == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�p�����[�^�l��NULL");
        }

        //1.1.�����X�ʏ���(long)
        //�����X�ʏ����𐶐�����B
        //[����]
        //���XID�F �g���A�J�E���g�}�l�[�W��.get�ڋq(
        //����.BondFinTransactionParams.get����ID()).get���X().get���XID()

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        WEB3BondBranchCondition l_branchCondition = null;
        try
        {
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_web3GentradeAccountManager.getMainAccount(
                    l_bondFinTransactionParams.getAccountId());

            l_branchCondition =
                new WEB3BondBranchCondition(l_mainAccount.getBranch().getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("__error in DB�ւ̃A�N�Z�X�Ɏ��s���܂����B__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2.get�ۗL���Y�`�F�b�N�敪( )
        //1.3.get�ۗL���Y�`�F�b�N�敪�����@@'�`�F�b�N���Ȃ�' �̏ꍇ
        if (WEB3BondAssetCheckDef.EXCEPT.equals(l_branchCondition.getAssetCheckDiv()))
        {
            //1.3.1.null��Ԃ�
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //1.4.get�ۗL���YParams(BondFinTransactionParams)
        WEB3BondPersistentDataManager l_persistentDataManager =
            (WEB3BondPersistentDataManager) getPersistentDataManager();

        //�ۗL���YParams���擾����B
        //�u���\�b�h�ɓn�������v
        //BondFinTransactionParams������.BondFinTransactionParams
        AssetParams l_assetParams = null;
        l_assetParams =
            l_persistentDataManager.getAsset(l_bondFinTransactionParams);

        //1.5.getFinTransactionType( )
        FinTransactionType l_transactionType =
            l_bondFinTransactionParams.getFinTransactionType();

        //1.6.getSide(arg0 : FinTransactionType)
        SideEnum l_sideEnum = getSide(l_transactionType);

        //1.7.getSide()==SideEnum.Buy�̏ꍇ
        if (l_sideEnum == SideEnum.BUY)
        {
            //1.7.1.get�ۗL���YParams == null�̏ꍇ
            if (l_assetParams == null)
            {
                //1.7.1.1.AssetParams( )
                l_assetParams = new AssetParams();

                //1.7.1.2.setAssetDefaultValues(arg0 : AssetParams)
                setAssetDefaultValues(l_assetParams);

                //1.7.1.3.set�ۗL���YParams(AssetParams, BondFinTransactionParams)
                this.setNewAssetParamsFromMarketTradedTrans(
                    l_assetParams, l_bondFinTransactionParams);

                //1.7.1.4.saveNewAsset(arg0 : AssetParams)
                l_persistentDataManager.saveNewAsset(l_assetParams);
            }
            else
            {
                //1.7.2.get�ۗL���YParams != null�̏ꍇ
                //1.7.2.1.update�ۗL���YParams(AssetParams, BondFinTransactionParams)
                this.updateAssetParamsFromMarketTradedTrans(
                    l_assetParams, l_bondFinTransactionParams);

                //1.7.2.2.updateAssetByTrans(arg0 : AssetParams)
                l_persistentDataManager.updateAssetByTrans(l_assetParams);
            }
        }
        else if (l_sideEnum == SideEnum.SELL)
        {
            //1.8.getSide()==SideEnum.SELL�̏ꍇ
            //1.8.1.get�ۗL���YParams == null�̏ꍇ
            if (l_assetParams == null)
            {
                log.debug("�ۗL���Y�Y���f�[�^�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ۗL���Y�Y���f�[�^�Ȃ��B");
            }
            else
            {
                //1.8.2.get�ۗL���YParams != null�̏ꍇ
                //1.8.2.1.update�ۗL���YParams(AssetParams, BondFinTransactionParams)
                updateAssetParamsFromMarketTradedTrans(
                    l_assetParams, l_bondFinTransactionParams);

                //1.8.2.2.updateAssetByTrans(arg0 : AssetParams)
                l_persistentDataManager.updateAssetByTrans(l_assetParams);
            }
        }

        //1.9. setAssetId(arg0 : long)
        l_bondFinTransactionParams.setAssetId(l_assetParams.getAssetId());

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (set�ۗL���YParams)<BR>
     * setNewAssetParamsFromMarketTradedTrans(AssetParams, <BR>
     * BondFinTransactionParams)�̃I�[�o�[���C�h<BR>
     * <BR>
     * <BR>
     * �P�j�ȉ��̂悤�ɒl���Z�b�g����B<BR>
     * �ۗL���YParams.����ID��BondFinTransactionParams.����ID<BR>
     * �ۗL���YParams.�⏕����ID��BondFinTransactionParams.�⏕����ID<BR>
     * �ۗL���YParams.����ID��BondFinTransactionParams.����ID<BR>
     * �ۗL���YParams.�����^�C�v��BondFinTransactionParams.�����^�C�v<BR>
     * �ۗL���YParams.���ʁ�BondFinTransactionParams.��萔��<BR>
     * �ۗL���YParams.�ŋ敪��BondFinTransactionParams.�ŋ敪<BR>
     * �ۗL���YParams.���t�s�\���ʁ�0<BR>
     * �ۗL���YParams.���ʁi�뉿�P���v�Z�p�j��BondFinTransactionParams.��萔��<BR>
     * �ۗL���YParams.���͕뉿�P����null<BR>
     * �ۗL���YParams.�뉿�P�����͓�����null<BR>
     * �ۗL���YParams.�~�j���敪��WEB3MiniStockDivDef.DEFAULT_MINI_STOCK<BR>
     * �ۗL���YParams.���z����0<BR>
     * �ۗL���YParams.30�����o�ߎc��������0<BR>
     * �ۗL���YParams.�쐬���t��GtlUtils.getSystemTimestamp()<BR>
     * �ۗL���YParams.�X�V���t��GtlUtils.getSystemTimestamp()<BR>
     * �ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j�����v�Z�T�[�r�X.calc��������i����, �P��, �������j<BR>
     * �@@[����]<BR>
     * �@@�@@�@@���ʁ�BondFinTransactionParams.��萔��<BR>
     * �@@�@@�@@�P����BondFinTransactionParams.���P��<BR>
     * �@@�@@�@@�����������v���_�N�g�}�l�[�W��.get������(BondFinTransactionParams.����ID)<BR>
     * @@param l_assetParams - (�ۗL���YParams)<BR>
     * �ۗL���YParams<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@roseuid 44D04B210128
     */
    protected void setNewAssetParamsFromMarketTradedTrans(
        AssetParams l_assetParams,
        BondFinTransactionParams l_bondFinTransactionParams)
    {
        final String STR_METHOD_NAME =
            " setNewAssetParamsFromMarketTradedTrans(" +
            "AssetParams, BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_assetParams == null || l_bondFinTransactionParams == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�p�����[�^�l��NULL");
        }

        //�P�j�ȉ��̂悤�ɒl���Z�b�g����B
        //�ۗL���YParams.����ID��BondFinTransactionParams.����ID
        l_assetParams.setAccountId(l_bondFinTransactionParams.getAccountId());
        //�ۗL���YParams.�⏕����ID��BondFinTransactionParams.�⏕����ID
        l_assetParams.setSubAccountId(l_bondFinTransactionParams.getSubAccountId());
        //�ۗL���YParams.����ID��BondFinTransactionParams.����ID
        l_assetParams.setProductId(l_bondFinTransactionParams.getProductId());
        //�ۗL���YParams.�����^�C�v��BondFinTransactionParams.�����^�C�v
        l_assetParams.setProductType(l_bondFinTransactionParams.getProductType());
        //�ۗL���YParams.���ʁ�BondFinTransactionParams.��萔��
        l_assetParams.setQuantity(l_bondFinTransactionParams.getQuantity());
        //�ۗL���YParams.�ŋ敪��BondFinTransactionParams.�ŋ敪
        l_assetParams.setTaxType(l_bondFinTransactionParams.getTaxType());
        //�ۗL���YParams.���t�s�\���ʁ�0
        l_assetParams.setQuantityCannotSell(0);
        //�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j��BondFinTransactionParams.��萔��
        l_assetParams.setQuantityForBookValue(l_bondFinTransactionParams.getQuantity());
        //�ۗL���YParams.���͕뉿�P����null
        l_assetParams.setInputBookValue(null);
        //�ۗL���YParams.�뉿�P�����͓�����null
        l_assetParams.setInputTimestamp(null);
        //�ۗL���YParams.�~�j���敪��WEB3MiniStockDivDef.DEFAULT_MINI_STOCK
        l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        //�ۗL���YParams.���z����0
        l_assetParams.setProfitDistribution(0);
        //�ۗL���YParams.30�����o�ߎc��������0
        l_assetParams.setCountBeforePenalty(0);
        //�ۗL���YParams.�쐬���t��GtlUtils.getSystemTimestamp()
        l_assetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�ۗL���YParams.�X�V���t��GtlUtils.getSystemTimestamp()
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j�����v�Z�T�[�r�X.calc��������i����, �P��, �������j
        // [����]
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getBizLogicProvider();
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();

        //  ���ʁ�BondFinTransactionParams.��萔��
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getQuantity()));
        //  �P����BondFinTransactionParams.���P��
        BigDecimal l_bdPrice = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getPrice()));
        //  �����������v���_�N�g�}�l�[�W��.get������(BondFinTransactionParams.����ID)
        BigDecimal l_bdTradePrice = null ;

        WEB3BondProduct l_bondProduct = null;

        try
        {
            l_bondProduct =
                (WEB3BondProduct) l_productManager.getBondProduct(l_bondFinTransactionParams.getProductId());
            l_bdTradePrice = l_bizLogicProvider.calcTradingPrice(l_bdQuantity, l_bdPrice, l_bondProduct);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        l_assetParams.setBookValue(l_bdTradePrice.doubleValue());
    }

    /**
     * (update�ۗL���YParams)<BR>
     * updateAssetParamsFromMarketTradedTrans(AssetParams, <BR>
     * BondFinTransactionParams)�̃I�[�o�[���C�h<BR>
     * <BR>
     * (this.getSide(BondFinTransactionParams.�g�����U�N�V�����^�C�v)==SideEnum.��)�̏ꍇ<BR>
     * {<BR>
     * �@@�ۗL���YParams.���ʁ��ۗL���YParams.���ʁ@@�{�@@BondFinTransactionParams.��萔��<BR>
     * �@@�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j���ۗL���YParams.���ʁi�뉿�P���v�Z�p�j�@@<BR>
     * �{�@@BondFinTransactionParams.��萔��<BR>
     * <BR>
     * �@@if(�ۗL���YParams.�ŋ敪 == "���"�@@���@@<BR>
     * �@@�@@�X�V�O�ۗ̕L���YParams.���ʁi�뉿�P���v�Z�p�j> 0�@@����<BR>
     * �@@�@@�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j== 0�j<BR>
     * �@@{<BR>
     * �@@//no operation<BR>
     * �@@}<BR>
     * �@@else<BR>
     * �@@{<BR>
     * �@@�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j���ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���v�Z�T�[�r�X.get��������i����, �P��, �������j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ [����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ ���ʁ�BondFinTransactionParams.��萔��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �P����BondFinTransactionParams.���P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@
     * �����������v���_�N�g�}�l�[�W��.get������(BondFinTransactionParams.����ID)<BR>
     * �@@}<BR>
     * }<BR>
     * (this.getSide(BondFinTransactionParams.�g�����U�N�V�����^�C�v)==SideEnum.��)�̏ꍇ<BR>
     * {<BR>
     * �@@�c���ʁ��ۗL���YParams.���t�s�\���� + �ۗL���YParams.���ʁi�뉿�P���v�Z�p) -<BR>
     *  BondFinTransactionParams.��萔��<BR>
     * �@@if(�c���� < 0)<BR>
     * �@@{<BR>
     * �@@�@@�u�ۗL���Y�c���ʃ`�F�b�N�G���[�v���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     * �@@   tag:   BUSINESS_ERROR_01931<BR>
     * �@@}<BR>
     * �@@if(�ۗL���YParams.���ʁ@@�����@@BondFinTransactionParams.��萔��)<BR>
     * �@@{<BR>
     * �@@�@@�ۗL���YParams.���ʁ��ۗL���YParams.���ʁ@@�|�@@<BR>
     * BondFinTransactionParams.��萔��<BR>
     * �@@}<BR>
     * �@@else<BR>
     * �@@{<BR>
     * �@@�@@�ۗL���YParams.���ʁ�0<BR>
     * �@@�@@�ۗL���YParams.���t�s�\���ʁ��ۗL���YParams.���t�s�\����<BR>
     * �@@�|�@@(BondFinTransactionParams.��萔�ʁ@@�|�@@�ۗL���YParams
     * .����)<BR>
     * �@@}<BR>
     * }<BR>
     * �ۗL���YParams.�X�V���t��GtlUtils.getSystemTimestamp()<BR>
     * @@param l_assetParams - AssetParams<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@roseuid 44D07288037A
     */
    protected void updateAssetParamsFromMarketTradedTrans(
        AssetParams l_assetParams,
        BondFinTransactionParams l_bondFinTransactionParams)
    {
        final String STR_METHOD_NAME =
            " updateAssetParamsFromMarketTradedTrans(AssetParams, " +
            "BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_assetParams == null || l_bondFinTransactionParams == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�p�����[�^�l��NULL");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //(this.getSide(BondFinTransactionParams.�g�����U�N�V�����^�C�v)==SideEnum.��)�̏ꍇ
        SideEnum l_sideEnum = getSide(l_bondFinTransactionParams.getFinTransactionType());
        BigDecimal l_bdQuantity= new BigDecimal(String.valueOf(l_assetParams.getQuantity()));
        BigDecimal l_bdFinQuantity = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getQuantity()));
        BigDecimal l_bdPrice = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getPrice()));
        BigDecimal l_bdForBookValue = new BigDecimal(String.valueOf(l_assetParams.getQuantityForBookValue()));
        BigDecimal l_bdTradePrice = null ;
        BigDecimal l_bdBookValue = new BigDecimal(String.valueOf(l_assetParams.getBookValue()));
        BigDecimal l_bdQuantityCannotSell = new BigDecimal(String.valueOf(l_assetParams.getQuantityCannotSell()));

        if (l_sideEnum == SideEnum.BUY)
        {
            //�ۗL���YParams.���ʁ��ۗL���YParams.���ʁ@@
            //�{�@@BondFinTransactionParams.��萔��
            l_assetParams.setQuantity(
                l_bdQuantity.add(l_bdFinQuantity).doubleValue());

            //�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j���ۗL���YParams.���ʁi�뉿�P���v�Z�p�j�@@
            //�{�@@BondFinTransactionParams.��萔��
            l_assetParams.setQuantityForBookValue(
                l_bdForBookValue.add(l_bdFinQuantity).doubleValue());

            //�@@if(�ۗL���YParams.�ŋ敪 == "���"�@@���@@
            //�@@�@@�X�V�O�ۗ̕L���YParams.���ʁi�뉿�P���v�Z�p�j> 0�@@����
            //�@@�@@�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j== 0�j
            if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
                && l_bdForBookValue.doubleValue() > 0.0D
                && l_assetParams.getBookValue() == 0.0D)
            {
                //no operation
            }
            else
            {
                //�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j���ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j
                //  �{�@@���v�Z�T�[�r�X.get��������i����, �P��, �������j
                //�@@�@@[����]
                //�@@�@@�@@���ʁ�BondFinTransactionParams.��萔��
                //�@@�@@�@@�P����BondFinTransactionParams.���P��
                //�@@�@@�@@�����������v���_�N�g�}�l�[�W��.get������(BondFinTransactionParams.����ID)
                WEB3BondBizLogicProvider l_bizLogicProvider =
                    (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getBizLogicProvider();
                WEB3BondProductManager l_productManager =
                    (WEB3BondProductManager) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getProductManager();

                WEB3BondProduct l_bondProduct = null;
                try
                {
                    l_bondProduct =
                        (WEB3BondProduct) l_productManager.getBondProduct(
                            l_bondFinTransactionParams.getProductId());
                    l_bdTradePrice = l_bizLogicProvider.calcTradingPrice(
                        l_bdFinQuantity, l_bdPrice, l_bondProduct);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                if (l_bdTradePrice != null)
                {
                    l_assetParams.setBookValue(
                        l_bdBookValue.add(l_bdTradePrice).doubleValue());
                }
            }
        }
        //(this.getSide(BondFinTransactionParams.�g�����U�N�V�����^�C�v)==SideEnum.��)�̏ꍇ
        if (l_sideEnum == SideEnum.SELL)
        {
            //�c���ʁ��ۗL���YParams.���t�s�\���� + �ۗL���YParams.����
            //- BondFinTransactionParams.��萔��
            double l_dblQuantity = 0.0D;
            l_dblQuantity = l_bdQuantityCannotSell.add(l_bdQuantity) .subtract(l_bdFinQuantity).doubleValue();
            //�@@if(�c���� < 0)
            if (l_dblQuantity < 0)
            {
                //�u�ۗL���Y�c���ʃ`�F�b�N�G���[�v���X���[����B
                log.debug("�ۗL���Y�c���ʃ`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ۗL���Y�c���ʃ`�F�b�N�G���[�B");
            }
            //if(�ۗL���YParams.���ʁ@@�����@@BondFinTransactionParams.��萔��)
            if (l_assetParams.getQuantity() >= l_bondFinTransactionParams.getQuantity())
            {
                //�ۗL���YParams.���ʁ��ۗL���YParams.���ʁ@@
                //�|�@@BondFinTransactionParams.��萔��
                l_assetParams.setQuantity(l_bdQuantity.subtract(l_bdFinQuantity).doubleValue());
            }
            else
            {
                //�ۗL���YParams.���ʁ�0
                l_assetParams.setQuantity(0);

                //�ۗL���YParams.���t�s�\���ʁ��ۗL���YParams.���t�s�\���ʁ@@
                //�|�@@(BondFinTransactionParams.��萔�ʁ@@�|�@@�ۗL���YParams.����)
                l_assetParams.setQuantityCannotSell(
                    l_bdQuantityCannotSell.subtract(l_bdFinQuantity.subtract(l_bdQuantity)).doubleValue());
            }
        }

        //�ۗL���YParams.�X�V���t��GtlUtils.getSystemTimestamp()
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ۗL���YNetAmount)<BR>
     * setAssetNetAmount(BondFinTransactionParams)�̃I�[�o�[���C�h<BR>
     * <BR>
     * �������Ȃ�<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@roseuid 44D075450128
     */
    protected void setAssetNetAmount(
        BondFinTransactionParams l_bondFinTransactionParams)
    {
        final String STR_METHOD_NAME =
            " updateAssetParamsFromMarketTradedTrans(" +
            "AssetParams, BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        //setAssetNetAmount(BondFinTransactionParams)�̃I�[�o�[���C�h
        //�������Ȃ�

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�����To���ڋq���薾��)<BR>
     * setExecutionInfoToMarketOrderedTrans(BondFinTransactionParams, <BR>
     * BondOrderExecution, BondOrderUnitRow)�̃I�[�o�[���C�h<BR>
     * <BR>
     * BondFinTransactionParams.����ID��BondOrderUnitRow.����ID<BR>
     * BondFinTransactionParams.�⏕����ID��BondOrderUnitRow.�⏕����ID<BR>
     * BondFinTransactionParams.����ID��BondOrderUnitRow.����ID<BR>
     * BondFinTransactionParams.�g�����U�N�V�����^�C�v��<BR>
     * BondOrderUnitRow.�������.toFinTransactionType()<BR>
     * BondFinTransactionParams.�g�����U�N�V�����J�e�S����<BR>
     * BondOrderUnitRow.�������.toFinTransactionType().toFinTransactionCateg()<BR>
     * BondFinTransactionParams.�ŋ敪��BondOrderUnitRow.�ŋ敪<BR>
     * BondFinTransactionParams.��n����BondOrderUnitRow.��n��<BR>
     * BondFinTransactionParams.�����^�C�v��BondOrderUnitRow.�����^�C�v<BR>
     * BondFinTransactionParams.�s��ID��BondOrderUnitRow.�s��ID<BR>
     * BondFinTransactionParams.���P����BondOrderUnitRow.���P��<BR>
     * BondFinTransactionParams.��萔�ʁ�BondOrderUnitRow.��萔��<BR>
     * BondFinTransactionParams.����ID��BondOrderUnitRow.����ID<BR>
     * BondFinTransactionParams.�����P��ID��BondOrderUnitRow.�����P��ID<BR>
     * BondFinTransactionParams.�o�ߗ��q��0<BR>
     * int l_sign = this.getSide(BondFinTransactionParams.�g�����U�N�V�����^�C�v).cashFlowDir();<BR>
     * BondFinTransactionParams.��n�����BondOrderUnitRow.��n����i�~�݁j* l_sign<BR>
     * BondFinTransactionParams.���ID��BondOrderExecutionParams.���ID<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@param l_bondOrderExecution - BondOrderExecution<BR>
     * @@param l_bondOrderUnitRow - BondOrderUnitRow<BR>
     * @@roseuid 44D075FA0128
     */
    protected void setExecutionInfoToMarketOrderedTrans(
        BondFinTransactionParams l_bondFinTransactionParams,
        BondOrderExecution l_bondOrderExecution,
        BondOrderUnitRow l_bondOrderUnitRow)
    {
        final String STR_METHOD_NAME =
            " setExecutionInfoToMarketOrderedTrans(BondFinTransactionParams,"
            + "BondOrderExecution, BondOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_bondFinTransactionParams == null
            || l_bondOrderExecution == null || l_bondOrderUnitRow == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�p�����[�^�l��NULL");
        }

        //BondFinTransactionParams.����ID��BondOrderUnitRow.����ID
        l_bondFinTransactionParams.setAccountId(l_bondOrderUnitRow.getAccountId());

        //BondFinTransactionParams.�⏕����ID��BondOrderUnitRow.�⏕����ID
        l_bondFinTransactionParams.setSubAccountId(l_bondOrderUnitRow.getSubAccountId());

        //BondFinTransactionParams.����ID��BondOrderUnitRow.����ID
        l_bondFinTransactionParams.setProductId(l_bondOrderUnitRow.getProductId());
        //BondFinTransactionParams.�g�����U�N�V�����^�C�v��
        //BondOrderUnitRow.�������.toFinTransactionType()
        l_bondFinTransactionParams.setFinTransactionType(
            l_bondOrderUnitRow.getOrderType().toFinTransactionType());

        //BondFinTransactionParams.�g�����U�N�V�����J�e�S����
        //BondOrderUnitRow.�������.toFinTransactionType().toFinTransactionCateg()
        l_bondFinTransactionParams.setFinTransactionCateg(
            l_bondOrderUnitRow.getOrderType().toFinTransactionType().toFinTransactionCateg());

        //BondFinTransactionParams.�ŋ敪��BondOrderUnitRow.�ŋ敪
        l_bondFinTransactionParams.setTaxType(l_bondOrderUnitRow.getTaxType());

        //BondFinTransactionParams.��n����BondOrderUnitRow.��n��
        l_bondFinTransactionParams.setDeliveryDate(l_bondOrderUnitRow.getDeliveryDate());

        //BondFinTransactionParams.�����^�C�v��BondOrderUnitRow.�����^�C�v
        l_bondFinTransactionParams.setProductType(l_bondOrderUnitRow.getProductType());

        //BondFinTransactionParams.�s��ID��BondOrderUnitRow.�s��ID
        l_bondFinTransactionParams.setMarketId(l_bondOrderUnitRow.getMarketId());

        //BondFinTransactionParams.���P����BondOrderUnitRow.���P��
        l_bondFinTransactionParams.setPrice(l_bondOrderUnitRow.getExecutedPrice());

        //BondFinTransactionParams.��萔�ʁ�BondOrderUnitRow.��萔��
        l_bondFinTransactionParams.setQuantity(l_bondOrderUnitRow.getExecutedQuantity());

        //BondFinTransactionParams.����ID��BondOrderUnitRow.����ID
        l_bondFinTransactionParams.setOrderId(l_bondOrderUnitRow.getOrderId());

        //BondFinTransactionParams.�����P��ID��BondOrderUnitRow.�����P��ID
        l_bondFinTransactionParams.setOrderUnitId(l_bondOrderUnitRow.getOrderUnitId());

        //BondFinTransactionParams.�o�ߗ��q��0
        l_bondFinTransactionParams.setAccruedInterest(0);

        //int l_sign = this.getSide(BondFinTransactionParams.�g�����U�N�V�����^�C�v).cashFlowDir();
        int l_intSign = getSide(l_bondFinTransactionParams.getFinTransactionType()).cashFlowDir();

        //BondFinTransactionParams.��n�����BondOrderUnitRow.��n����i�~�݁j* l_sign
        l_bondFinTransactionParams.setNetAmount(
            new BigDecimal(
                String.valueOf(l_bondOrderUnitRow.getEstimatedPrice())).multiply(
                    new BigDecimal(String.valueOf(l_intSign))).doubleValue());

        //BondFinTransactionParams.���ID��BondOrderExecutionParams.���ID
        l_bondFinTransactionParams.setOrderExecutionId(
            l_bondOrderExecution.getOrderExecutionId());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (reverse�ۗL���YBy���ڋq���薾��)<BR>
     * reverseAssetPositionByTrans(BondFinTransactionParams, SideEnum)�̃I�[�o�[���C�h<BR>
     * <BR>
     * �P�j�@@�ۗL���Y�`�F�b�N�敪���`�F�b�N <BR>
     * �@@�P�|�P�j�����X�ʏ����𐶐�����B <BR>
     *�@@�@@�@@�@@�@@[����] <BR>
     *�@@�@@�@@�@@�@@�@@���XID=�g���A�J�E���g�}�l�[�W��.get�ڋq(<BR>
     *�@@�@@�@@�@@�@@�@@����.BondFinTransactionParams.get����ID()).get���X().get���XID()<BR>
     * �@@�P�|�Q�j�����X�ʏ���.get�ۗL���Y�`�F�b�N�敪 == '�`�F�b�N���Ȃ�'�̏ꍇ�A������������return����B<BR>
     * <BR>
     * �Q�j�ۗL���YParams���擾����<BR>
     * �@@���f�[�^�}�l�[�W��.getAsset(BondFinTransactionParams.���YID)<BR>
     * �@@���j�ۗL���YParams����null�̏ꍇ<BR>
     * �@@�@@�@@��O���X���[����<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00204<BR>
     * <BR>
     * �R�jSideEnum�����̏ꍇ<BR>
     * �@@�R�|�P�j�ۗL���YParams.���ʁ��ۗL���YParams.���ʁ@@-<BR>
     * �@@BondFinTransactionParams.��萔��<BR>
     * �@@�@@�@@if(�ۗL���YParams.���� < 0) <BR>
     * �@@�@@�@@{<BR>
     * �@@�@@�@@�@@�u�ۗL���Y�c���ʃ`�F�b�N�G���[�v���X���[����B<BR>
     * �@@�@@�@@} <BR>
     *      class: WEB3BusinessLayerException<BR>
     * �@@   tag:   BUSINESS_ERROR_01931<BR>
     * <BR>
     * �@@�R�|�Q�j�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j��<BR>
     *     �@@�ۗL���YParams.���ʁi�뉿�P���v�Z�p�j�@@-�@@BondFinTransactionParams.��萔��<BR>
     * �@@�R�|�R�j���̏ꍇ(�ۗL���YParams.�ŋ敪 == "���"�@@���@@<BR>
     * �@@�@@�@@�@@�@@�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j== 0�j<BR>
     * �@@�@@�@@�@@�@@{<BR>
     * �@@�@@�@@�@@�@@�@@�@@//no operation<BR>
     * �@@�@@�@@�@@�@@}<BR>
     * �@@�@@�@@�@@�@@else<BR>
     * �@@�@@�@@�@@�@@{<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j���ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@-���v�Z�T�[�r�X.get��������i����, �P��, �������j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���ʁ�BondFinTransactionParams.��萔��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�P����BondFinTransactionParams.���P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����������v���_�N�g�}�l�[�W��.get������(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@BondFinTransactionParams.����ID)<BR>
     * �@@�@@�@@�@@�@@�@@}<BR>
     * <BR>
     * �S�jSideEnum�����̏ꍇ<BR>
     * �@@�@@�E�ۗL���YParams.���ʁ��ۗL���YParams.���ʁ@@+�@@<BR>
     * �@@�@@BondFinTransactionParams.��萔��<BR>
     * <BR>
     * �T�j�ۗL���YParams.�X�V���t��GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �U�j�ۗL���YParams���X�V����B<BR>
     * �@@���f�[�^�}�l�[�W��.updateAssetByTrans(�ۗL���YParams)<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@param l_sideEnum - SideEnum<BR>
     * @@throws DataException
     * @@roseuid 44D096020128
     */
    protected void reverseAssetPositionByTrans(
        BondFinTransactionParams l_bondFinTransactionParams,
        SideEnum l_sideEnum) throws DataException
    {
        final String STR_METHOD_NAME =
            " reverseAssetPositionByTrans(BondFinTransactionParams,"
            + "SideEnum, BondOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_bondFinTransactionParams == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        //�P�j�@@�ۗL���Y�`�F�b�N�敪���`�F�b�N
        //   �P�|�P�j�����X�ʏ����𐶐�����B
        //     [����]
        //       ���XID=�g���A�J�E���g�}�l�[�W��.get�ڋq(
        //       ����.BondFinTransactionParams.get����ID()).get���X().get���XID()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        long l_lngAccountId = l_bondFinTransactionParams.getAccountId();
        try
        {
            long l_lngBranch =
                l_web3GentradeAccountManager.getMainAccount(l_lngAccountId).getBranch().getBranchId();
            WEB3BondBranchCondition l_branchCondition = new WEB3BondBranchCondition(l_lngBranch);

            // �P�|�Q�j�����X�ʏ���.get�ۗL���Y�`�F�b�N�敪 == '�`�F�b�N���Ȃ�'�̏ꍇ�A������������return����B
            if (WEB3BondAssetCheckDef.EXCEPT.equals(l_branchCondition.getAssetCheckDiv()))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in �g���A�J�E���g�}�l�[�W������ڋq���擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in DB�ւ̃A�N�Z�X�Ɏ��s���܂����B__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�ۗL���YParams���擾����
        //  ���f�[�^�}�l�[�W��.getAsset(BondFinTransactionParams.���YID)
        //  ���j�ۗL���YParams����null�̏ꍇ
        //  ��O���X���[����
        WEB3BondPersistentDataManager l_bondPersistentManager =
            (WEB3BondPersistentDataManager) getPersistentDataManager();
        AssetParams l_assetParams = null;

        l_assetParams =
            l_bondPersistentManager.getAsset(l_bondFinTransactionParams.getAssetId());

        if (l_assetParams == null)
        {
            log.debug("�ۗL���Y�Y���f�[�^�Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���Y�Y���f�[�^�Ȃ��B");
        }

        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_assetParams.getQuantity()));
        BigDecimal l_bdFinQuantity = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getQuantity()));
        BigDecimal l_bdQuantityForBookValue = new BigDecimal(String.valueOf(l_assetParams.getQuantityForBookValue()));
        //�R�jSideEnum�����̏ꍇ
        if (l_sideEnum == SideEnum.BUY)
        {
            //�R�|�P�j�ۗL���YParams.���ʁ��ۗL���YParams.���ʁ@@
            //-�@@BondFinTransactionParams.��萔��
            l_assetParams.setQuantity(
                l_bdQuantity.subtract(l_bdFinQuantity).doubleValue());
            
            //if(�ۗL���YParams.���� < 0)  
            if (l_assetParams.getQuantity() < 0)
            {
                //�u�ۗL���Y�c���ʃ`�F�b�N�G���[�v���X���[����B
                log.debug("�ۗL���Y�c���ʃ`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ۗL���Y�c���ʃ`�F�b�N�G���[�B");
            }

            //�R�|�Q�j�ۗL���YParams.���ʁi�뉿�P���p�j��
            //�ۗL���YParams.���ʁi�뉿�P���p�j�@@-�@@BondFinTransactionParams.��萔��
            l_assetParams.setQuantityForBookValue(
                l_bdQuantityForBookValue.subtract(l_bdFinQuantity).doubleValue());

            //�R�|�R�j���̏ꍇ(�ۗL���YParams.�ŋ敪 == "���"�@@���@@
            //    �ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j== 0�j
            if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
                && l_assetParams.getBookValue() == 0.0D)
            {
                //no operation
            }
            else
            {
                //�ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j���ۗL���YParams.�뉿�i�뉿�P���v�Z�p�j
                //  -�@@���v�Z�T�[�r�X.get��������i����, �P��, �������j
                //�@@�@@[����]
                //�@@�@@�@@���ʁ�BondFinTransactionParams.��萔��
                //�@@�@@�@@�P����BondFinTransactionParams.���P��
                //�@@�@@�@@�����������v���_�N�g�}�l�[�W��.get������(BondFinTransactionParams.����ID)
                WEB3BondBizLogicProvider l_bizLogicProvider =
                    (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getBizLogicProvider();
                WEB3BondProductManager l_productManager =
                    (WEB3BondProductManager) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getProductManager();

                BigDecimal l_bdPrice = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getPrice()));
                BigDecimal l_bdTradePrice = null ;

                WEB3BondProduct l_bondProduct = null;
                try
                {
                    l_bondProduct =
                        (WEB3BondProduct) l_productManager.getBondProduct(
                            l_bondFinTransactionParams.getProductId());
                    l_bdTradePrice = l_bizLogicProvider.calcTradingPrice(l_bdFinQuantity, l_bdPrice,
                        l_bondProduct);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                if (l_bdTradePrice != null)
                {
                    BigDecimal l_bdBookValue = new BigDecimal(String.valueOf(l_assetParams.getBookValue()));
                    l_assetParams.setBookValue(
                        l_bdBookValue.subtract(l_bdTradePrice).doubleValue());
                    log.debug(">>>>>>>>>" + l_bdBookValue.subtract(l_bdTradePrice).doubleValue());
                }

            }
        }

        //�S�jSideEnum�����̏ꍇ
        //�@@�E�ۗL���YParams.���ʁ��ۗL���YParams.���ʁ@@+
        //�@@BondFinTransactionParams.��萔��
        if (l_sideEnum == SideEnum.SELL)
        {
            l_assetParams.setQuantity(
                l_bdQuantity.add(l_bdFinQuantity).doubleValue());
        }

        //�T�j�ۗL���YParams.�X�V���t��GtlUtils.getSystemTimestamp()
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //�U�j�ۗL���YParams���X�V����B
        // ���f�[�^�}�l�[�W��.updateAssetByTrans(�ۗL���YParams)
        l_bondPersistentManager.updateAssetByTrans(l_assetParams);
        log.exiting(STR_METHOD_NAME);
    }
}@
