head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出庫通知UnitServiceImpl(WEB3AioOutputNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.define.WEB3HostTransferPaymentStatusDef;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CustdyDiv;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3AioHostSpsecCommodityDef;
import webbroker3.common.define.WEB3CancelDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

/**
 * (出庫通知UnitServiceImpl) <BR>
 * 出庫通知UnitService実装クラス <BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor( <BR>
 * TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * <BR>
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyUnitServiceImpl implements WEB3AioOutputNotifyUnitService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOutputNotifyUnitServiceImpl.class); 
    
    /**
     * (notify出庫通知)<BR>
     * 出庫通知処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（出庫通知）notify出庫通知」 参照<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strCustodyDiv - 保管区分
     * @@param l_strSpecificDiv - 特定口座区分
     * @@param l_lngQuantity - 数量
     * @@param l_strCommodityDiv - 商品区分
     * @@param l_strCancelDiv - 取消区分
     * @@return String
     * @@roseuid 4157961901F5
     */
    public String notifyOutputNotify(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strProductCode, 
        String l_strCustodyDiv, 
        String l_strSpecificDiv, 
        Long l_lngQuantity,
	    String l_strCommodityDiv,
	    String l_strCancelDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyOutputNotify(" +
            "String, String, String, String, String, String, Long)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 InstitutionImpl(String)

        //証券会社オブジェクトを取得する。 
        //[引数] 
        //証券会社コード： 引数.証券会社コード
        
        WEB3GentradeInstitution l_institution = null;
        try
        {
            l_institution = new WEB3GentradeInstitution(l_strInstitutionCode);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DBサーバとの通信に失敗した]__" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2 get銘柄(ProductTypeEnum, String, Institution)
        //銘柄を取得する。 

        //[引数] 
        //銘柄タイプ： 1（株式） 
        //銘柄コード： 引数.銘柄コード 
        //証券会社： 証券会社オブジェクト
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
        
        Product l_product = l_productManager.getProduct(
            ProductTypeEnum.EQUITY, 
            l_strProductCode, 
            l_institution);
        
        //1.3 MainAccountImpl(long, String, String)
        //顧客オブジェクトを取得する。  

        //[引数]  
        //証券会社ID： 証券会社.getInstitutionId()の戻り値  
        //部店コード： 引数.部店コード  
        //顧客コード： 引数.顧客コード 
        try
        {
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(
                l_institution.getInstitutionId(), 
                l_strBranchCode, 
                l_strAccountCode);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DBサーバとの通信に失敗した]__" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.4 is信用口座開設(弁済区分 : String)
        //補助口座を取得する。  

		//信用口座を開設しているかのチェックを行う。 
		
		//[引数] 
		//弁済区分： 0（指定なし） 
        
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        
        SubAccount l_subAccount = null;
        try
        {
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(), l_strBranchCode, l_strAccountCode);
			WEB3GentradeMainAccount l_gentradeMainAcc = 
				(WEB3GentradeMainAccount)l_mainAccount;
            //1.5 is信用口座開設( ) == trueの場合
            if (l_gentradeMainAcc.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
				//1.5.1 getSubAccount(SubAccountTypeEnum)
				//補助口座を取得する。  

				//[引数]  
				//補助口座タイプ：　@SubAccountTypeEnum.株式信用取引口座（保証金）
				l_subAccount = l_gentradeMainAcc.getSubAccount(
					SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            //1.6 is信用口座開設( ) == falseの場合
            else
            {
				//1.6.1 getSubAccount(SubAccountTypeEnum)
				//補助口座を取得する。  

				//[引数]  
				//補助口座タイプ：　@SubAccountTypeEnum.SubAccountTypeEnum.株式取引口座（預り金）
				l_subAccount = l_gentradeMainAcc.getSubAccount(
					SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("証券会社インスタンスを生成する", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.7 is出庫対象データ (String 保管区分)
        //出庫対象データの保管区分のチェックを行う。
        //[引数] 保管区分： 引数.保管区分
         
         boolean l_blnOutputObjData = this.isOutputObjData(l_strCustodyDiv);
       
        //1.8 is出庫対象データの結果が「false」の場合、
        //「２（出庫対象データ対象外」を返却する。
        if (!l_blnOutputObjData)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT;
        }
        
        //1.9 is商品区分(String 商品区分)
        //出庫対象データの商品区分のチェックを行う。
        //[引数] 商品区分： 引数.商品区分
        
        boolean l_blnCommodityDiv = this.isCommodityDiv(l_strCommodityDiv);
        
		//1.10 is商品区分()の結果が「false」の場合、
		//「２（出庫対象データ対象外」を返却する。
		if (!l_blnCommodityDiv)
		{
			log.exiting(STR_METHOD_NAME);
			return WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT;
		}
		
		//1.11 is取消区分(String 取消区分)
		//出庫対象データの商品区分のチェックを行う。
		//[引数] 取消区分： 引数.取消区分
        
		boolean l_blnCancelDiv = this.isCancelDiv(l_strCancelDiv);
        
		//1.12 is取消区分()の結果が「false」の場合、
		//「２（出庫対象データ対象外」を返却する。
		if (!l_blnCancelDiv)
		{
			log.exiting(STR_METHOD_NAME);
			return WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT;
		}
                
        //1.13 update保有資産(Long, Long, Long, String, String, Long)

        //保有資産テーブルを更新する。  

        //[引数]  
        //口座ID：補助口座.getMainAccount().口座ID 
        //補助口座ID：補助口座.補助口座ID 
        //銘柄ID：（get銘柄()の戻り値）.銘柄ID 
        //税区分：引数.特定口座区分 
        //ミニ株区分：0（default） 
        //数量：引数.数量
        
        boolean l_blnUpdateAsset = this.updateAsset(
            new Long(l_subAccount.getMainAccount().getAccountId()), 
            new Long(l_subAccount.getSubAccountId()), 
            new Long(l_product.getProductId()), 
            l_strSpecificDiv, 
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK, 
            l_lngQuantity);

        //1.14 update保有資産（）の戻り値が、「true」の場合は、
        //「１：（処理済）」を返却する。
        //update保有資産（）の戻り値が、「false」の場合は、
        //「３：（該当する保有資産なし）」を返却する。
        
        if (l_blnUpdateAsset)
        {
            log.debug("update保有資産（）の戻り値が、「true」の場合");
            log.debug("notify出庫通知 = " + WEB3HostTransferPaymentStatusDef.DEALT);
            
            log.exiting(STR_METHOD_NAME);
            return WEB3HostTransferPaymentStatusDef.DEALT;
        }
        else
        {
            log.debug("update保有資産（）の戻り値が、「false」の場合");
            log.debug("notify出庫通知 = " + WEB3HostTransferPaymentStatusDef.NO_ASSET);
            
            log.exiting(STR_METHOD_NAME);
            return WEB3HostTransferPaymentStatusDef.NO_ASSET;
        }
    }

    /**
     * (is出庫対象データ)<BR>
     * 出庫対象データの保管区分のチェックを行う。<BR>
     * <BR>
     *１）保管区分チェック 
     *　@（１）引数.保管区分=”8（機@構）” or ”1（集中保管”の場合は、「true」を返却する。<BR>
     *　@（２）上記に当てはまらない場合、「false」を返却する。<BR>
     * <BR>
     * @@param l_strCustodyDiv - 保管区分
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    protected boolean isOutputObjData(String l_strCustodyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOutputObjData(String l_strCustodyDiv)";
        log.entering(STR_METHOD_NAME);
    
        //１）出庫対象データチェック 
        //（１）引数.保管区分=”8（機@構）” or ”1（集中保管）”の場合は、 
        //「true」を返却する。 
        if (WEB3CustdyDiv.INSTITUTE_SAVE.equals(l_strCustodyDiv) 
           || WEB3CustdyDiv.INTERNAL_SAVE.equals(l_strCustodyDiv))
        {
            log.debug("is出庫対象データ = " + true);
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //（２）上記に当てはまらない場合、「false」を返却する。
        else
        {
            log.debug("is出庫対象データ = " + false);
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (update保有資産)<BR>
     * 保有資産の数量と売付不能株数を更新する。<BR>  
     * <BR>
     * １）税区分の設定 <BR>
     * 　@  引数.特定口座区分 = "０（一般）"の場合、"１：（一般）"を設定 <BR>
     * <BR>
     * 　@　@引数,特定口座区分 = "１（特定）"の場合、"２：（特定）"を設定 <BR>
     * 　@　@引数.特定口座区分 ≠ "０（一般）" or  "１（特定）"でない場合は、 <BR>
     * 　@　@「false」を返却する。 <BR>
     * <BR>
     * ２）対象となる保有資産レコードを取得する。  <BR>
     * <BR>
     * [取得条件]  <BR>
     *  口座ID = 引数.口座ID  <BR>
     *  補助口座ID = 引数.補助口座ID <BR>
     *  銘柄ID = 引数.銘柄ID  <BR>
     *  税区分 = １）で設定した税区分 <BR>
     *  ミニ株区分 = 引数.ミニ株区分  <BR>
     * <BR>
     * ３）保有資産レコードを更新（登録）する。  <BR>
     * <BR>
     * ３－１）保有資産レコードが取得できなかった場合  <BR>
     *        「false」を返却する。 <BR>
     * <BR>
     * ３－２）保有資産レコードが取得できた場合  <BR>
     * <BR>
     *        保有資産レコードを更新し、「true」を返却する。 <BR>
     *        以下の項目と値でDB更新を行う。 <BR>
     * <BR>
     * ３－２－１）保有資産.数量 ≧ 引数.数量の場合 <BR>
     * <BR>
     *          保有資産.数量 　@　@　@　@　@　@= 保有資産.数量 - 引数.数量 <BR>
     *          保有資産.売付不能数量 = 保有資産.売付不能株数 + 引数.数量 <BR>
     *          保有資産.更新日付 = 現在時刻 <BR>
     *
     * ３－２－１）保有資産.数量 ＜ 引数.数量の場合 <BR>
     *
     *          保有資産.数量 　@　@　@　@　@　@= 0 <BR>
     *          保有資産.売付不能数量 = 保有資産.売付不能株数 + 保有資産.数量（※１） <BR>
     *          保有資産.更新日付 = 現在時刻 <BR>
     * <BR>
     * （※１）保有資産.数量は、取得した数量（元数量）とする。 <BR>
     * <BR>
     * @@param l_lngAccountId - 口座ID
     * @@param l_lngSubAccountId - 補助口座ID
     * @@param l_lngProductId - 銘柄ID
     * @@param l_strTaxType - 税区分
     * @@param l_strMiniStockDiv - ミニ株区分
     * @@param l_lngQuantity - 数量
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    protected boolean updateAsset(
        Long l_lngAccountId, 
        Long l_lngSubAccountId, 
        Long l_lngProductId, 
        String l_strTaxType, 
        String l_strMiniStockDiv, 
        Long l_lngQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAsset(" +
            "Long, Long, Long, String, String, Long)";
        log.entering(STR_METHOD_NAME);
        
        //１）税区分の設定 
        // 引数.特定口座区分 = "０（一般）"の場合、"１：（一般）"を設定 
        // 引数,特定口座区分 = "１（特定）"の場合、"２：（特定）"を設定 
        // 引数.特定口座区分 ≠ "０（一般）" or  "１（特定）"でない場合は、 
        //「false」を返却する。 
        
        TaxTypeEnum l_taxType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType))
        {
            l_taxType = TaxTypeEnum.NORMAL;
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxType)) 
        {
            l_taxType = TaxTypeEnum.SPECIAL;
        }
        
        if (!(WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType) || 
            WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxType)))
        {
            log.debug("引数.特定口座区分 ≠ '０（一般）' or  '１（特定）'でない場合");
            log.debug("update保有資産 = " + false);
            
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）対象となる保有資産レコードを取得する。  

        //[取得条件]  
        // 口座ID = 引数.口座ID  
        // 補助口座ID = 引数.補助口座ID  
        // 銘柄ID = 引数.銘柄ID  
        // 税区分 = １）で設定した税区分 
        // ミニ株区分 = 引数.ミニ株区分
        List l_lisAssetRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAssetRows = l_queryProcessor.doFindAllQuery(
                AssetRow.TYPE,
                "account_id = ? and sub_account_id = ? and " +
                "product_id = ? and tax_type = ? and mini_stock_div = ?",
                null,
                new Object[] {
                    l_lngAccountId, 
                    l_lngSubAccountId, 
                    l_lngProductId, 
                    l_taxType, 
                    l_strMiniStockDiv
                    });
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //３）保有資産レコードを更新（登録）する。  

        //３－１）保有資産レコードが取得できなかった場合  
        //       「false」を返却する。 
        if (l_lisAssetRows == null || l_lisAssetRows.size() == 0)
        {
            log.debug("保有資産レコードが取得できなかった場合");
            log.debug("update保有資産 = " + false);
            
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //３－２）保有資産レコードが取得できた場合  
        //       保有資産レコードを更新し、「true」を返却する。 
        //       以下の項目と値でDB更新を行う。 

        //　@　@　@ 保有資産.数量 　@　@　@　@　@　@= 保有資産.数量 - 引数.数量 
        //　@　@　@ 保有資産.売付不能数量 = 保有資産.売付不能株数 + 引数.数量  
        //　@　@　@ 保有資産.更新日付       = 現在時刻 
        
        AssetRow l_assetRow = null;
        AssetParams l_assetParams = null;
        
        if (l_lisAssetRows.size() == 1)
        {            
            log.debug("保有資産レコードが取得Size() = " + l_lisAssetRows.size());
            l_assetRow = (AssetRow)l_lisAssetRows.get(0);
            l_assetParams = new AssetParams(l_assetRow);
            
            log.debug("保有資産レコード = " + l_assetParams);

            double l_dblQuangtity = Double.parseDouble(l_lngQuantity.toString());
            
			//３－２－１）保有資産.数量 ≧ 引数.数量の場合 
            if(Double.compare(l_assetParams.getQuantity(),l_dblQuangtity) >= 0)
            {
                //保有資産.数量 = 保有資産.数量 - 引数.数量 
                l_assetParams.setQuantity( l_assetParams.getQuantity() - l_dblQuangtity);
                //保有資産.売付不能数量 = 保有資産.売付不能株数 + 引数.数量
                l_assetParams.setQuantityCannotSell(l_assetParams.getQuantityCannotSell() + l_dblQuangtity);
            }
            else
            {
				//３－２－２）保有資産.数量 ＜ 引数.数量の場合
                //保有資産.売付不能数量 = 保有資産.売付不能株数 + 保有資産.数量
                l_assetParams.setQuantityCannotSell(l_assetParams.getQuantityCannotSell() + l_assetParams.getQuantity());
                //保有資産.数量= 0
                l_assetParams.setQuantity(0);
            }

            //保有資産.更新日付 = 現在時刻
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                
                // do update
                l_queryProcessor.doUpdateQuery(l_assetParams);
            }
            catch (DataException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }           
        }
        
        log.debug("保有資産レコードが取得できた場合保有資産レコードを更新");
        log.debug("update保有資産 = " + true);
        
        log.exiting(STR_METHOD_NAME);
        return true;        
    }
    
	/**
	 * (is商品区分)<BR>
	 * 出庫対象データの商品区分のチェックを行う。<BR>
	 * <BR>
	 *１）商品区分チェック 
	 *　@（１）引数.商品区分=”1（株式）”の場合は、「true」を返却する。<BR>
	 *　@（２）上記に当てはまらない場合、「false」を返却する。<BR>
	 * <BR>
	 * @@param l_strCommodityDiv - 商品区分
	 * @@return boolean
	 * @@throws WEB3BaseException
	 * @@roseuid 4157977900AD
	 */
	protected boolean isCommodityDiv(String l_strCommodityDiv) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isCommodityDiv(String l_strCommodityDiv)";
		log.entering(STR_METHOD_NAME);
    
		//１）商品区分チェック 
		//（１）引数.商品区分=”1（株式）”の場合は、「true」を返却する。 
		if (WEB3AioHostSpsecCommodityDef.EQUITY.equals(l_strCommodityDiv))
		{
			log.debug("is商品区分 = " + true);
			log.exiting(STR_METHOD_NAME);
			return true;
		}
		//（２）上記に当てはまらない場合、「false」を返却する。
		else
		{
			log.debug("is商品区分 = " + false);
			log.exiting(STR_METHOD_NAME);
			return false;
		}
	}
	
	/**
	 * (is取消区分)<BR>
	 * 出庫対象データの取消区分のチェックを行う。<BR>
	 * <BR>
	 *１）商品区分チェック 
	 *　@（１）引数.商品区分=”-（取消）”の場合は、「false」を返却する。<BR>
	 *　@（２）上記に当てはまらない場合、「true」を返却する。<BR>
	 * <BR>
	 * @@param l_strCancelDiv - 取消区分
	 * @@return boolean
	 * @@throws WEB3BaseException
	 * @@roseuid 4157977900AD
	 */
	protected boolean isCancelDiv(String l_strCancelDiv) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isCancelDiv(String l_strCancelDiv)";
		log.entering(STR_METHOD_NAME);
    
		//１）取消区分チェック 
		//（１）引数.取消区分=”-（取消）”の場合は、「false」を返却する。 
		if (l_strCancelDiv == null || l_strCancelDiv.length() == 0 )
		{
			log.debug("is取消区分 = " + true);
			log.exiting(STR_METHOD_NAME);
			return true;
		}
		else if (WEB3CancelDef.CANCLE.equals(l_strCancelDiv))
		{
			log.debug("is取消区分 = " + false);
			log.exiting(STR_METHOD_NAME);
			return false;
		}
		else
		{
			//（２）上記に当てはまらない場合、「true」を返却する。
			log.debug("is取消区分 = " + true);
			log.exiting(STR_METHOD_NAME);
			return true;
		}
	}
}
@
