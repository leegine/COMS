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
 Copyright        : ()åa¤ Ø\[VVXeæñ
 File Name        : Â|WV}l[Wwp[(WEB3BondPositionManagerHelper.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17 ü·(u) VKì¬
                    2006/10/09 ü· (u) dlÏXEf111
                    2006/10/12 åû(u)WEBVJ­WÌ©¼µÌÎinewBigDecimalªj
                    2006/10/16 æâÑQ(u) fNo.127
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
 * (Â|WV}l[Wwp[)<BR>
 * Â|WV}l[Wwp[NX
 *
 * @@author ü·(u)
 * @@version 1.0
 */
public class WEB3BondPositionManagerHelper extends BondPositionManagerHelper
{

    /**
     * OoÍ[eBeBB<BR>
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
     * (applyToÛLY|WV)applyToAssetPosition<BR>
     * ÌI[o[Ch<BR>
     * <BR>
     * V[PX}uapplyToÛLY|WVvðQÆ<BR>
     * <BR>
     * =============================================== <BR>
     *@@@@@@@@@@V[PX} : uapplyToÛLY|WVv<BR>
     *@@@@@@@@@@ïÌÊu     : 1.6.1. getÛLYParams == nullÌê<BR>
     *@@@@@@@@@@@@@@@@@@@@@@@@@@@@áOB <BR>
     *@@@@@@@@@@class        : WEB3BusinessLayerException <BR>
     *@@@@@@@@@@tag          : BUSINESS_ERROR_00204 <BR>
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
            log.debug("p[^lªNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "p[^lªNULL");
        }

        //1.1.ÂXÊð(long)
        //ÂXÊðð¶¬·éB
        //[ø]
        //XIDF g£AJEg}l[W.getÚq(
        //ø.BondFinTransactionParams.getûÀID()).getX().getXID()

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
            log.error("e[uÉY·éf[^ª èÜ¹ñB", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("__error in DBÖÌANZXÉ¸sµÜµ½B__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2.getÛLY`FbNæª( )
        //1.3.getÛLY`FbNæª@@'`FbNµÈ¢' Ìê
        if (WEB3BondAssetCheckDef.EXCEPT.equals(l_branchCondition.getAssetCheckDiv()))
        {
            //1.3.1.nullðÔ·
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //1.4.getÛLYParams(BondFinTransactionParams)
        WEB3BondPersistentDataManager l_persistentDataManager =
            (WEB3BondPersistentDataManager) getPersistentDataManager();

        //ÛLYParamsðæ¾·éB
        //u\bhÉn·øv
        //BondFinTransactionParamsø.BondFinTransactionParams
        AssetParams l_assetParams = null;
        l_assetParams =
            l_persistentDataManager.getAsset(l_bondFinTransactionParams);

        //1.5.getFinTransactionType( )
        FinTransactionType l_transactionType =
            l_bondFinTransactionParams.getFinTransactionType();

        //1.6.getSide(arg0 : FinTransactionType)
        SideEnum l_sideEnum = getSide(l_transactionType);

        //1.7.getSide()==SideEnum.BuyÌê
        if (l_sideEnum == SideEnum.BUY)
        {
            //1.7.1.getÛLYParams == nullÌê
            if (l_assetParams == null)
            {
                //1.7.1.1.AssetParams( )
                l_assetParams = new AssetParams();

                //1.7.1.2.setAssetDefaultValues(arg0 : AssetParams)
                setAssetDefaultValues(l_assetParams);

                //1.7.1.3.setÛLYParams(AssetParams, BondFinTransactionParams)
                this.setNewAssetParamsFromMarketTradedTrans(
                    l_assetParams, l_bondFinTransactionParams);

                //1.7.1.4.saveNewAsset(arg0 : AssetParams)
                l_persistentDataManager.saveNewAsset(l_assetParams);
            }
            else
            {
                //1.7.2.getÛLYParams != nullÌê
                //1.7.2.1.updateÛLYParams(AssetParams, BondFinTransactionParams)
                this.updateAssetParamsFromMarketTradedTrans(
                    l_assetParams, l_bondFinTransactionParams);

                //1.7.2.2.updateAssetByTrans(arg0 : AssetParams)
                l_persistentDataManager.updateAssetByTrans(l_assetParams);
            }
        }
        else if (l_sideEnum == SideEnum.SELL)
        {
            //1.8.getSide()==SideEnum.SELLÌê
            //1.8.1.getÛLYParams == nullÌê
            if (l_assetParams == null)
            {
                log.debug("ÛLYYf[^ÈµB");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ÛLYYf[^ÈµB");
            }
            else
            {
                //1.8.2.getÛLYParams != nullÌê
                //1.8.2.1.updateÛLYParams(AssetParams, BondFinTransactionParams)
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
     * (setÛLYParams)<BR>
     * setNewAssetParamsFromMarketTradedTrans(AssetParams, <BR>
     * BondFinTransactionParams)ÌI[o[Ch<BR>
     * <BR>
     * <BR>
     * PjÈºÌæ¤ÉlðZbg·éB<BR>
     * ÛLYParams.ûÀIDBondFinTransactionParams.ûÀID<BR>
     * ÛLYParams.âûÀIDBondFinTransactionParams.âûÀID<BR>
     * ÛLYParams.Á¿IDBondFinTransactionParams.Á¿ID<BR>
     * ÛLYParams.Á¿^CvBondFinTransactionParams.Á¿^Cv<BR>
     * ÛLYParams.ÊBondFinTransactionParams.ñèÊ<BR>
     * ÛLYParams.ÅæªBondFinTransactionParams.Åæª<BR>
     * ÛLYParams.ts\Ê0<BR>
     * ÛLYParams.Êië¿P¿vZpjBondFinTransactionParams.ñèÊ<BR>
     * ÛLYParams.üÍë¿P¿null<BR>
     * ÛLYParams.ë¿P¿üÍúnull<BR>
     * ÛLYParams.~jæªWEB3MiniStockDivDef.DEFAULT_MINI_STOCK<BR>
     * ÛLYParams.ªzà0<BR>
     * ÛLYParams.30ú¢oßcû0<BR>
     * ÛLYParams.ì¬útGtlUtils.getSystemTimestamp()<BR>
     * ÛLYParams.XVútGtlUtils.getSystemTimestamp()<BR>
     * ÛLYParams.ë¿ië¿P¿vZpjÂvZT[rX.calcãàiÊ, P¿, ÂÁ¿j<BR>
     * @@[ø]<BR>
     * @@@@@@ÊBondFinTransactionParams.ñèÊ<BR>
     * @@@@@@P¿BondFinTransactionParams.ñèP¿<BR>
     * @@@@@@ÂÁ¿Âv_Ng}l[W.getÂÁ¿(BondFinTransactionParams.Á¿ID)<BR>
     * @@param l_assetParams - (ÛLYParams)<BR>
     * ÛLYParams<BR>
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
            log.debug("p[^lªNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "p[^lªNULL");
        }

        //PjÈºÌæ¤ÉlðZbg·éB
        //ÛLYParams.ûÀIDBondFinTransactionParams.ûÀID
        l_assetParams.setAccountId(l_bondFinTransactionParams.getAccountId());
        //ÛLYParams.âûÀIDBondFinTransactionParams.âûÀID
        l_assetParams.setSubAccountId(l_bondFinTransactionParams.getSubAccountId());
        //ÛLYParams.Á¿IDBondFinTransactionParams.Á¿ID
        l_assetParams.setProductId(l_bondFinTransactionParams.getProductId());
        //ÛLYParams.Á¿^CvBondFinTransactionParams.Á¿^Cv
        l_assetParams.setProductType(l_bondFinTransactionParams.getProductType());
        //ÛLYParams.ÊBondFinTransactionParams.ñèÊ
        l_assetParams.setQuantity(l_bondFinTransactionParams.getQuantity());
        //ÛLYParams.ÅæªBondFinTransactionParams.Åæª
        l_assetParams.setTaxType(l_bondFinTransactionParams.getTaxType());
        //ÛLYParams.ts\Ê0
        l_assetParams.setQuantityCannotSell(0);
        //ÛLYParams.Êië¿P¿vZpjBondFinTransactionParams.ñèÊ
        l_assetParams.setQuantityForBookValue(l_bondFinTransactionParams.getQuantity());
        //ÛLYParams.üÍë¿P¿null
        l_assetParams.setInputBookValue(null);
        //ÛLYParams.ë¿P¿üÍúnull
        l_assetParams.setInputTimestamp(null);
        //ÛLYParams.~jæªWEB3MiniStockDivDef.DEFAULT_MINI_STOCK
        l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        //ÛLYParams.ªzà0
        l_assetParams.setProfitDistribution(0);
        //ÛLYParams.30ú¢oßcû0
        l_assetParams.setCountBeforePenalty(0);
        //ÛLYParams.ì¬útGtlUtils.getSystemTimestamp()
        l_assetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //ÛLYParams.XVútGtlUtils.getSystemTimestamp()
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //ÛLYParams.ë¿ië¿P¿vZpjÂvZT[rX.calcãàiÊ, P¿, ÂÁ¿j
        // [ø]
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getBizLogicProvider();
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();

        //  ÊBondFinTransactionParams.ñèÊ
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getQuantity()));
        //  P¿BondFinTransactionParams.ñèP¿
        BigDecimal l_bdPrice = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getPrice()));
        //  ÂÁ¿Âv_Ng}l[W.getÂÁ¿(BondFinTransactionParams.Á¿ID)
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
     * (updateÛLYParams)<BR>
     * updateAssetParamsFromMarketTradedTrans(AssetParams, <BR>
     * BondFinTransactionParams)ÌI[o[Ch<BR>
     * <BR>
     * (this.getSide(BondFinTransactionParams.gUNV^Cv)==SideEnum.)Ìê<BR>
     * {<BR>
     * @@ÛLYParams.ÊÛLYParams.Ê@@{@@BondFinTransactionParams.ñèÊ<BR>
     * @@ÛLYParams.Êië¿P¿vZpjÛLYParams.Êië¿P¿vZpj@@<BR>
     * {@@BondFinTransactionParams.ñèÊ<BR>
     * <BR>
     * @@if(ÛLYParams.Åæª == "êÊ"@@©Â@@<BR>
     * @@@@XVOÌÛLYParams.Êië¿P¿vZpj> 0@@©Â<BR>
     * @@@@ÛLYParams.ë¿ië¿P¿vZpj== 0j<BR>
     * @@{<BR>
     * @@//no operation<BR>
     * @@}<BR>
     * @@else<BR>
     * @@{<BR>
     * @@ÛLYParams.ë¿ië¿P¿vZpjÛLYParams.ë¿ië¿P¿vZpj<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{@@ÂvZT[rX.getãàiÊ, P¿, ÂÁ¿j<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ [ø]<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ÊBondFinTransactionParams.ñèÊ<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ P¿BondFinTransactionParams.ñèP¿<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     * ÂÁ¿Âv_Ng}l[W.getÂÁ¿(BondFinTransactionParams.Á¿ID)<BR>
     * @@}<BR>
     * }<BR>
     * (this.getSide(BondFinTransactionParams.gUNV^Cv)==SideEnum.)Ìê<BR>
     * {<BR>
     * @@cÊÛLYParams.ts\Ê + ÛLYParams.Êië¿P¿vZp) -<BR>
     *  BondFinTransactionParams.ñèÊ<BR>
     * @@if(cÊ < 0)<BR>
     * @@{<BR>
     * @@@@uÛLYcÊ`FbNG[vðX[·éB<BR>
     *      class: WEB3BusinessLayerException<BR>
     * @@   tag:   BUSINESS_ERROR_01931<BR>
     * @@}<BR>
     * @@if(ÛLYParams.Ê@@@@BondFinTransactionParams.ñèÊ)<BR>
     * @@{<BR>
     * @@@@ÛLYParams.ÊÛLYParams.Ê@@|@@<BR>
     * BondFinTransactionParams.ñèÊ<BR>
     * @@}<BR>
     * @@else<BR>
     * @@{<BR>
     * @@@@ÛLYParams.Ê0<BR>
     * @@@@ÛLYParams.ts\ÊÛLYParams.ts\Ê<BR>
     * @@|@@(BondFinTransactionParams.ñèÊ@@|@@ÛLYParams
     * .Ê)<BR>
     * @@}<BR>
     * }<BR>
     * ÛLYParams.XVútGtlUtils.getSystemTimestamp()<BR>
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
            log.debug("p[^lªNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "p[^lªNULL");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //(this.getSide(BondFinTransactionParams.gUNV^Cv)==SideEnum.)Ìê
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
            //ÛLYParams.ÊÛLYParams.Ê@@
            //{@@BondFinTransactionParams.ñèÊ
            l_assetParams.setQuantity(
                l_bdQuantity.add(l_bdFinQuantity).doubleValue());

            //ÛLYParams.Êië¿P¿vZpjÛLYParams.Êië¿P¿vZpj@@
            //{@@BondFinTransactionParams.ñèÊ
            l_assetParams.setQuantityForBookValue(
                l_bdForBookValue.add(l_bdFinQuantity).doubleValue());

            //@@if(ÛLYParams.Åæª == "êÊ"@@©Â@@
            //@@@@XVOÌÛLYParams.Êië¿P¿vZpj> 0@@©Â
            //@@@@ÛLYParams.ë¿ië¿P¿vZpj== 0j
            if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
                && l_bdForBookValue.doubleValue() > 0.0D
                && l_assetParams.getBookValue() == 0.0D)
            {
                //no operation
            }
            else
            {
                //ÛLYParams.ë¿ië¿P¿vZpjÛLYParams.ë¿ië¿P¿vZpj
                //  {@@ÂvZT[rX.getãàiÊ, P¿, ÂÁ¿j
                //@@@@[ø]
                //@@@@@@ÊBondFinTransactionParams.ñèÊ
                //@@@@@@P¿BondFinTransactionParams.ñèP¿
                //@@@@@@ÂÁ¿Âv_Ng}l[W.getÂÁ¿(BondFinTransactionParams.Á¿ID)
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
        //(this.getSide(BondFinTransactionParams.gUNV^Cv)==SideEnum.)Ìê
        if (l_sideEnum == SideEnum.SELL)
        {
            //cÊÛLYParams.ts\Ê + ÛLYParams.Ê
            //- BondFinTransactionParams.ñèÊ
            double l_dblQuantity = 0.0D;
            l_dblQuantity = l_bdQuantityCannotSell.add(l_bdQuantity) .subtract(l_bdFinQuantity).doubleValue();
            //@@if(cÊ < 0)
            if (l_dblQuantity < 0)
            {
                //uÛLYcÊ`FbNG[vðX[·éB
                log.debug("ÛLYcÊ`FbNG[B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ÛLYcÊ`FbNG[B");
            }
            //if(ÛLYParams.Ê@@@@BondFinTransactionParams.ñèÊ)
            if (l_assetParams.getQuantity() >= l_bondFinTransactionParams.getQuantity())
            {
                //ÛLYParams.ÊÛLYParams.Ê@@
                //|@@BondFinTransactionParams.ñèÊ
                l_assetParams.setQuantity(l_bdQuantity.subtract(l_bdFinQuantity).doubleValue());
            }
            else
            {
                //ÛLYParams.Ê0
                l_assetParams.setQuantity(0);

                //ÛLYParams.ts\ÊÛLYParams.ts\Ê@@
                //|@@(BondFinTransactionParams.ñèÊ@@|@@ÛLYParams.Ê)
                l_assetParams.setQuantityCannotSell(
                    l_bdQuantityCannotSell.subtract(l_bdFinQuantity.subtract(l_bdQuantity)).doubleValue());
            }
        }

        //ÛLYParams.XVútGtlUtils.getSystemTimestamp()
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setÛLYNetAmount)<BR>
     * setAssetNetAmount(BondFinTransactionParams)ÌI[o[Ch<BR>
     * <BR>
     * ½àµÈ¢<BR>
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
        //setAssetNetAmount(BondFinTransactionParams)ÌI[o[Ch
        //½àµÈ¢

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setñèîñToÂÚq¨è¾×)<BR>
     * setExecutionInfoToMarketOrderedTrans(BondFinTransactionParams, <BR>
     * BondOrderExecution, BondOrderUnitRow)ÌI[o[Ch<BR>
     * <BR>
     * BondFinTransactionParams.ûÀIDBondOrderUnitRow.ûÀID<BR>
     * BondFinTransactionParams.âûÀIDBondOrderUnitRow.âûÀID<BR>
     * BondFinTransactionParams.Á¿IDBondOrderUnitRow.Á¿ID<BR>
     * BondFinTransactionParams.gUNV^Cv<BR>
     * BondOrderUnitRow.¶íÊ.toFinTransactionType()<BR>
     * BondFinTransactionParams.gUNVJeS<BR>
     * BondOrderUnitRow.¶íÊ.toFinTransactionType().toFinTransactionCateg()<BR>
     * BondFinTransactionParams.ÅæªBondOrderUnitRow.Åæª<BR>
     * BondFinTransactionParams.ónúBondOrderUnitRow.ónú<BR>
     * BondFinTransactionParams.Á¿^CvBondOrderUnitRow.Á¿^Cv<BR>
     * BondFinTransactionParams.sêIDBondOrderUnitRow.sêID<BR>
     * BondFinTransactionParams.ñèP¿BondOrderUnitRow.ñèP¿<BR>
     * BondFinTransactionParams.ñèÊBondOrderUnitRow.ñèÊ<BR>
     * BondFinTransactionParams.¶IDBondOrderUnitRow.¶ID<BR>
     * BondFinTransactionParams.¶PÊIDBondOrderUnitRow.¶PÊID<BR>
     * BondFinTransactionParams.oßq0<BR>
     * int l_sign = this.getSide(BondFinTransactionParams.gUNV^Cv).cashFlowDir();<BR>
     * BondFinTransactionParams.ónãàBondOrderUnitRow.ónãài~Ýj* l_sign<BR>
     * BondFinTransactionParams.ñèIDBondOrderExecutionParams.ñèID<BR>
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
            log.debug("p[^lªNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "p[^lªNULL");
        }

        //BondFinTransactionParams.ûÀIDBondOrderUnitRow.ûÀID
        l_bondFinTransactionParams.setAccountId(l_bondOrderUnitRow.getAccountId());

        //BondFinTransactionParams.âûÀIDBondOrderUnitRow.âûÀID
        l_bondFinTransactionParams.setSubAccountId(l_bondOrderUnitRow.getSubAccountId());

        //BondFinTransactionParams.Á¿IDBondOrderUnitRow.Á¿ID
        l_bondFinTransactionParams.setProductId(l_bondOrderUnitRow.getProductId());
        //BondFinTransactionParams.gUNV^Cv
        //BondOrderUnitRow.¶íÊ.toFinTransactionType()
        l_bondFinTransactionParams.setFinTransactionType(
            l_bondOrderUnitRow.getOrderType().toFinTransactionType());

        //BondFinTransactionParams.gUNVJeS
        //BondOrderUnitRow.¶íÊ.toFinTransactionType().toFinTransactionCateg()
        l_bondFinTransactionParams.setFinTransactionCateg(
            l_bondOrderUnitRow.getOrderType().toFinTransactionType().toFinTransactionCateg());

        //BondFinTransactionParams.ÅæªBondOrderUnitRow.Åæª
        l_bondFinTransactionParams.setTaxType(l_bondOrderUnitRow.getTaxType());

        //BondFinTransactionParams.ónúBondOrderUnitRow.ónú
        l_bondFinTransactionParams.setDeliveryDate(l_bondOrderUnitRow.getDeliveryDate());

        //BondFinTransactionParams.Á¿^CvBondOrderUnitRow.Á¿^Cv
        l_bondFinTransactionParams.setProductType(l_bondOrderUnitRow.getProductType());

        //BondFinTransactionParams.sêIDBondOrderUnitRow.sêID
        l_bondFinTransactionParams.setMarketId(l_bondOrderUnitRow.getMarketId());

        //BondFinTransactionParams.ñèP¿BondOrderUnitRow.ñèP¿
        l_bondFinTransactionParams.setPrice(l_bondOrderUnitRow.getExecutedPrice());

        //BondFinTransactionParams.ñèÊBondOrderUnitRow.ñèÊ
        l_bondFinTransactionParams.setQuantity(l_bondOrderUnitRow.getExecutedQuantity());

        //BondFinTransactionParams.¶IDBondOrderUnitRow.¶ID
        l_bondFinTransactionParams.setOrderId(l_bondOrderUnitRow.getOrderId());

        //BondFinTransactionParams.¶PÊIDBondOrderUnitRow.¶PÊID
        l_bondFinTransactionParams.setOrderUnitId(l_bondOrderUnitRow.getOrderUnitId());

        //BondFinTransactionParams.oßq0
        l_bondFinTransactionParams.setAccruedInterest(0);

        //int l_sign = this.getSide(BondFinTransactionParams.gUNV^Cv).cashFlowDir();
        int l_intSign = getSide(l_bondFinTransactionParams.getFinTransactionType()).cashFlowDir();

        //BondFinTransactionParams.ónãàBondOrderUnitRow.ónãài~Ýj* l_sign
        l_bondFinTransactionParams.setNetAmount(
            new BigDecimal(
                String.valueOf(l_bondOrderUnitRow.getEstimatedPrice())).multiply(
                    new BigDecimal(String.valueOf(l_intSign))).doubleValue());

        //BondFinTransactionParams.ñèIDBondOrderExecutionParams.ñèID
        l_bondFinTransactionParams.setOrderExecutionId(
            l_bondOrderExecution.getOrderExecutionId());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (reverseÛLYByÂÚq¨è¾×)<BR>
     * reverseAssetPositionByTrans(BondFinTransactionParams, SideEnum)ÌI[o[Ch<BR>
     * <BR>
     * Pj@@ÛLY`FbNæªð`FbN <BR>
     * @@P|PjÂXÊðð¶¬·éB <BR>
     *@@@@@@@@@@[ø] <BR>
     *@@@@@@@@@@@@XID=g£AJEg}l[W.getÚq(<BR>
     *@@@@@@@@@@@@ø.BondFinTransactionParams.getûÀID()).getX().getXID()<BR>
     * @@P|QjÂXÊð.getÛLY`FbNæª == '`FbNµÈ¢'ÌêAð¹¸Éreturn·éB<BR>
     * <BR>
     * QjÛLYParamsðæ¾·é<BR>
     * @@Âf[^}l[W.getAsset(BondFinTransactionParams.YID)<BR>
     * @@jÛLYParamsnullÌê<BR>
     * @@@@@@áOðX[·é<BR>
     * @@@@@@class: WEB3BusinessLayerException<BR>
     * @@@@@@tag:   BUSINESS_ERROR_00204<BR>
     * <BR>
     * RjSideEnumªÌê<BR>
     * @@R|PjÛLYParams.ÊÛLYParams.Ê@@-<BR>
     * @@BondFinTransactionParams.ñèÊ<BR>
     * @@@@@@if(ÛLYParams.Ê < 0) <BR>
     * @@@@@@{<BR>
     * @@@@@@@@uÛLYcÊ`FbNG[vðX[·éB<BR>
     * @@@@@@} <BR>
     *      class: WEB3BusinessLayerException<BR>
     * @@   tag:   BUSINESS_ERROR_01931<BR>
     * <BR>
     * @@R|QjÛLYParams.Êië¿P¿vZpj<BR>
     *     @@ÛLYParams.Êië¿P¿vZpj@@-@@BondFinTransactionParams.ñèÊ<BR>
     * @@R|RjÌê(ÛLYParams.Åæª == "êÊ"@@©Â@@<BR>
     * @@@@@@@@@@ÛLYParams.ë¿ië¿P¿vZpj== 0j<BR>
     * @@@@@@@@@@{<BR>
     * @@@@@@@@@@@@@@//no operation<BR>
     * @@@@@@@@@@}<BR>
     * @@@@@@@@@@else<BR>
     * @@@@@@@@@@{<BR>
     * @@@@@@@@@@@@@@ÛLYParams.ë¿ië¿P¿vZpjÛLYParams.ë¿ië¿P¿vZpj@@<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-ÂvZT[rX.getãàiÊ, P¿, ÂÁ¿j<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@[ø]<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ÊBondFinTransactionParams.ñèÊ<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@P¿BondFinTransactionParams.ñèP¿<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ÂÁ¿Âv_Ng}l[W.getÂÁ¿(<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@BondFinTransactionParams.Á¿ID)<BR>
     * @@@@@@@@@@@@}<BR>
     * <BR>
     * SjSideEnumªÌê<BR>
     * @@@@EÛLYParams.ÊÛLYParams.Ê@@+@@<BR>
     * @@@@BondFinTransactionParams.ñèÊ<BR>
     * <BR>
     * TjÛLYParams.XVútGtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * UjÛLYParamsðXV·éB<BR>
     * @@Âf[^}l[W.updateAssetByTrans(ÛLYParams)<BR>
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
            log.debug("p[^lªNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "p[^lªNULL");
        }

        //Pj@@ÛLY`FbNæªð`FbN
        //   P|PjÂXÊðð¶¬·éB
        //     [ø]
        //       XID=g£AJEg}l[W.getÚq(
        //       ø.BondFinTransactionParams.getûÀID()).getX().getXID()
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        long l_lngAccountId = l_bondFinTransactionParams.getAccountId();
        try
        {
            long l_lngBranch =
                l_web3GentradeAccountManager.getMainAccount(l_lngAccountId).getBranch().getBranchId();
            WEB3BondBranchCondition l_branchCondition = new WEB3BondBranchCondition(l_lngBranch);

            // P|QjÂXÊð.getÛLY`FbNæª == '`FbNµÈ¢'ÌêAð¹¸Éreturn·éB
            if (WEB3BondAssetCheckDef.EXCEPT.equals(l_branchCondition.getAssetCheckDiv()))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in g£AJEg}l[W©çÚqðæ¾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in DBÖÌANZXÉ¸sµÜµ½B__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //QjÛLYParamsðæ¾·é
        //  Âf[^}l[W.getAsset(BondFinTransactionParams.YID)
        //  jÛLYParamsnullÌê
        //  áOðX[·é
        WEB3BondPersistentDataManager l_bondPersistentManager =
            (WEB3BondPersistentDataManager) getPersistentDataManager();
        AssetParams l_assetParams = null;

        l_assetParams =
            l_bondPersistentManager.getAsset(l_bondFinTransactionParams.getAssetId());

        if (l_assetParams == null)
        {
            log.debug("ÛLYYf[^ÈµB");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ÛLYYf[^ÈµB");
        }

        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_assetParams.getQuantity()));
        BigDecimal l_bdFinQuantity = new BigDecimal(String.valueOf(l_bondFinTransactionParams.getQuantity()));
        BigDecimal l_bdQuantityForBookValue = new BigDecimal(String.valueOf(l_assetParams.getQuantityForBookValue()));
        //RjSideEnumªÌê
        if (l_sideEnum == SideEnum.BUY)
        {
            //R|PjÛLYParams.ÊÛLYParams.Ê@@
            //-@@BondFinTransactionParams.ñèÊ
            l_assetParams.setQuantity(
                l_bdQuantity.subtract(l_bdFinQuantity).doubleValue());
            
            //if(ÛLYParams.Ê < 0)  
            if (l_assetParams.getQuantity() < 0)
            {
                //uÛLYcÊ`FbNG[vðX[·éB
                log.debug("ÛLYcÊ`FbNG[B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01931,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ÛLYcÊ`FbNG[B");
            }

            //R|QjÛLYParams.Êië¿P¿pj
            //ÛLYParams.Êië¿P¿pj@@-@@BondFinTransactionParams.ñèÊ
            l_assetParams.setQuantityForBookValue(
                l_bdQuantityForBookValue.subtract(l_bdFinQuantity).doubleValue());

            //R|RjÌê(ÛLYParams.Åæª == "êÊ"@@©Â@@
            //    ÛLYParams.ë¿ië¿P¿vZpj== 0j
            if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
                && l_assetParams.getBookValue() == 0.0D)
            {
                //no operation
            }
            else
            {
                //ÛLYParams.ë¿ië¿P¿vZpjÛLYParams.ë¿ië¿P¿vZpj
                //  -@@ÂvZT[rX.getãàiÊ, P¿, ÂÁ¿j
                //@@@@[ø]
                //@@@@@@ÊBondFinTransactionParams.ñèÊ
                //@@@@@@P¿BondFinTransactionParams.ñèP¿
                //@@@@@@ÂÁ¿Âv_Ng}l[W.getÂÁ¿(BondFinTransactionParams.Á¿ID)
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

        //SjSideEnumªÌê
        //@@EÛLYParams.ÊÛLYParams.Ê@@+
        //@@BondFinTransactionParams.ñèÊ
        if (l_sideEnum == SideEnum.SELL)
        {
            l_assetParams.setQuantity(
                l_bdQuantity.add(l_bdFinQuantity).doubleValue());
        }

        //TjÛLYParams.XVútGtlUtils.getSystemTimestamp()
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //UjÛLYParamsðXV·éB
        // Âf[^}l[W.updateAssetByTrans(ÛLYParams)
        l_bondPersistentManager.updateAssetByTrans(l_assetParams);
        log.exiting(STR_METHOD_NAME);
    }
}@
