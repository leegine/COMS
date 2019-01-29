head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundPositionManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3MutualFundPositionManagerForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundPositionManagerForMock extends WEB3MutualFundPositionManager
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualFundPositionManagerForMock.class);

    /**
     * (Mock)
     * getAssetsのオーバーロード<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - (投信･外貨MMF表示区分)<BR>
     * 投信･外貨MMF表示区分 <BR>
     * <BR>
     * 0:投信のみ<BR>
     * 1:外貨MMFのみ <BR>
     * 2:両方 <BR>
     * <BR>
     * ※nullの場合、「0:投信のみ」とする<BR>
     * @@return List
     * @@throws WEB3BaseException 
     */
    public List getAssets(
        SubAccount l_subAccount,
        String l_strMutualFrgnMmfDisplayDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssets(SubAccount, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class},
            new Object[]{l_subAccount, l_strMutualFrgnMmfDisplayDiv});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAssets",
            new Class[] {SubAccount.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getAssets",
                new Class[] {SubAccount.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asVoid
            return (List)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getAssets",
                new Class[] {SubAccount.class, String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getAssets(l_subAccount, l_strMutualFrgnMmfDisplayDiv);
    }

    /**
     * (calc解約可能残高口数(Mock))<BR>
     * 当該顧客の保有する、現在解約可能な投資信託の解約可能残高口数を返す。<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_strAssetId - 資産ID
     * @@return double
     */
    public double calcSellPossiblePositionQty(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcSellPossiblePositionQty(SubAccount, WEB3MutualFundProduct, String))-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            new Object[]{l_subAccount, l_mutualFundProduct, l_strAssetId});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellPossiblePositionQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcSellPossiblePositionQty",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcSellPossiblePositionQty",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcSellPossiblePositionQty(l_subAccount, l_mutualFundProduct, l_strAssetId);
    }

    /**
     * (calc解約中概算口数(Mock))<BR>
     * 当該顧客の、現在解約中の概算口数を返す。<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_taxType - 税区分
     * @@param l_asset - 保有資産
     * @@return double
     */
    public double calcSellingEstimatedQty(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        TaxTypeEnum l_taxType, 
        Asset l_asset)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcSellingEstimatedQty(SubAccount, WEB3MutualFundProduct, String, Asset)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellingEstimatedQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, TaxTypeEnum.class, Asset.class},
            new Object[]{l_subAccount, l_mutualFundProduct, l_taxType, l_asset});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcSellingEstimatedQty",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, TaxTypeEnum.class, Asset.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcSellingEstimatedQty",
                new Class[] {
                    SubAccount.class,
                    WEB3MutualFundProduct.class,
                    TaxTypeEnum.class, Asset.class}).asWEB3BaseException();

            //3)MockFor --〉 asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcSellingEstimatedQty",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class,
                    TaxTypeEnum.class, Asset.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcSellingEstimatedQty(l_subAccount, l_mutualFundProduct, l_taxType, l_asset);
    }

    /**
     * (calc評価額(Mock))<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_strAssetId - 資産ID
     * @@return double
     */
    public double calcMarketValue(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "calcMarketValue(SubAccount ,WEB3MutualFundProduct, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            new Object[]{l_subAccount, l_mutualFundProduct, l_strAssetId});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcMarketValue",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcMarketValue",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcMarketValue(l_subAccount, l_mutualFundProduct, l_strAssetId);
    }

    /**
     * (calc評価損益(Mock))<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_mutualFundProduct - 拡張投信銘柄
     * @@param l_strAssetId - 資産ID
     * @@return double
     */
    public double calcAppraisalProfitLoss(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strAssetId)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "calcAppraisalProfitLoss(SubAccount , WEB3MutualFundProduct , String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class},
            new Object[]{l_subAccount, l_mutualFundProduct, l_strAssetId});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "calcAppraisalProfitLoss",
            new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcAppraisalProfitLoss",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "calcAppraisalProfitLoss",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, String.class}).asDouble();
        }

        log.exiting(STR_METHOD_NAME);
        return super.calcAppraisalProfitLoss(l_subAccount, l_mutualFundProduct, l_strAssetId);
    }

    /**
     * (get未収分配金(Mock))<BR>
     * 未収分配金を返却する。<BR>
     * <BR>
     * 1). 以下の条件で投信保有資産補助テーブルより行オブジェクトを取得する。<BR>
     * 　@資産ID = 引数.資産ID <BR>
     * <BR>
     * 2). 取得した行オブジェクトのget未収分配金残高をリターンする。<BR>
     * 　@(小数点以下切り捨て)<BR>
     * @@param l_strAssetId - (資産ID)<BR>
     * 資産ID<BR>
     * @@return long
     * @@throws WEB3BaseException 
     */
    public long getUnreceiveDist(String l_strAssetId, WEB3MutualFundProduct l_mutualFundProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnreceiveDist(String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class,WEB3MutualFundProduct.class},
            new Object[]{l_strAssetId, l_mutualFundProduct});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getUnreceiveDist",
            new Class[] {String.class,WEB3MutualFundProduct.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getUnreceiveDist",
                new Class[] {String.class, WEB3MutualFundProduct.class}).asWEB3BaseException();

            //3)MockFor --〉 asDouble
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getUnreceiveDist",
                new Class[] {String.class, WEB3MutualFundProduct.class}).asLong();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getUnreceiveDist(l_strAssetId, l_mutualFundProduct);
    }

    public Asset getAsset(long assetId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getAsset(long)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAsset",
            new Class[] {long.class},
            new Object[]{new Long(assetId)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundPositionManager",
            "getAsset",
            new Class[] {long.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);

            //3)MockFor --〉 asDouble
            return (Asset)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundPositionManager",
                "getAsset",
                new Class[] {long.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getAsset(assetId);
    }

}
@
