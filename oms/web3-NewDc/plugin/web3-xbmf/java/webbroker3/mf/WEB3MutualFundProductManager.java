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
filename	WEB3MutualFundProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信銘柄マネージャクラス(WEB3MutualFundProductManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 于美麗 (中訊) 新規作成
Revesion History : 2004/08/23 王蘭芬 (中訊) レビュー
Revesion History : 2004/12/06 于美麗 (中訊) 残対応
Revesion History : 2005/10/20 韋念瓊 (中訊) フィデリティ対応
Revesion History : 2006/03/14 情野 (SRA) 仕様変更モデルNo.404対応 
Revesion History : 2006/06/28 肖志偉 (中訊) 仕様変更モデルNo.461対応  
Revesion History : 2007/01/04 何文敏 (中訊) 仕様変更モデルNo.517対応
Revesion History : 2007/02/05 唐性峰 (中訊) 仕様変更モデルNo.529対応
Revesion History : 2007/04/09 張騰宇 (中訊) 仕様変更モデルNo.547対応
Revesion History : 2007/08/30 張騰宇 (中訊) 仕様変更モデルNo.571対応
Revesion History : 2008/04/29 王志葵 (中訊) 仕様変更モデルNo.598対応
Revesion History : 2008/07/10 王志葵 (中訊) 仕様変更モデルNo.609,618対応
Revesion History : 2009/05/13 武　@波 (中訊) 仕様変更モデルNo.641対応
*/
package webbroker3.mf;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BuyPossibleDivDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3FixedBuyPossibleDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3SellPossibleDivDef;
import webbroker3.common.define.WEB3SpecDivDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3SwtPossibleDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.data.MutualFund2ndProductSonarDao;
import webbroker3.mf.data.MutualFund2ndProductSonarRow;
import webbroker3.mf.data.MutualFundInstCondSonarDao;
import webbroker3.mf.data.MutualFundInstCondSonarRow;
import webbroker3.mf.data.MutualFundProductCategoryDao;
import webbroker3.mf.data.MutualFundProductCategoryPK;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3MFRecruitPossibleDivDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 拡張投信銘柄マネージャクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundProductManager extends MutualFundProductManagerImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProductManager.class);

    /**
     * (拡張投信銘柄マネージャ)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 40AD90090261
     */
    public WEB3MutualFundProductManager()
    {

    }

	/**
	 * (get投信銘柄)<BR>
	 * （getMutualFundProductのオーバーライド）<BR>
	 * <BR>
	 * 拡張投信銘柄を取得する。<BR>
	 * <BR>
	 * １）　@投信銘柄オブジェクトを取得する。<BR>
	 * 　@super.getMutualFundProduct()をコールして、投信銘柄オブジェクトを<BR>
	 * 　@取得する。<BR>
	 * 　@[getMutualFundProductに渡すパラメタ]<BR>
	 * 　@　@証券会社： 引数.証券会社<BR>
	 * 　@　@銘柄コード： 引数.銘柄コード<BR>
	 * 　@　@回号コード： 引数.回号コード<BR>
	 * <BR>
	 * ２）　@拡張投信銘柄オブジェクトを取得する。<BR>
	 * 　@this.to銘柄()をコールして、拡張投信銘柄オブジェクトを取得する。<BR>
	 * 　@[to銘柄に渡すパラメタ］<BR>
	 * 　@　@Rowオブジェクト： <BR>
	 *        取得した投信銘柄オブジェクト.getDataSourceObject()の戻り値<BR>
	 * <BR>
	 * ３）　@取得した拡張投信銘柄オブジェクトを返す。<BR>
	 * <BR>
	 * @@param l_institution - 証券会社
	 * @@param l_strProductCode - 銘柄コード
	 * @@param l_strProductIssueCode - 回号コード
	 * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
	 * @@roseuid 40AD90090270
	 */
	public MutualFundProduct getMutualFundProduct(
		Institution l_institution,
		String l_strProductCode,
		String l_strProductIssueCode)
		throws NotFoundException
	{
		final String STR_METHOD_NAME =
			"getMutualFundProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode,"
				+ "String l_strProductIssueCode)";
                
		log.entering(STR_METHOD_NAME);
        
		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		//１）　@投信銘柄オブジェクトを取得する
		MutualFundProduct l_mutualFundProduct =
			super.getMutualFundProduct(
				l_institution,
				l_strProductCode,
				l_strProductIssueCode);

		if (l_mutualFundProduct == null)
		{
			String l_strmsg =
				"拡張投信銘柄取得できない場合エラー for "
					+ "institution code = "
					+ l_institution.getInstitutionCode()
					+ ", ProductCode = "
					+ l_strProductCode
					+ ", ProductIssueCode = "
					+ l_strProductIssueCode;
                    
			log.debug(l_strmsg);
			log.exiting(STR_METHOD_NAME);
			throw new NotFoundException(l_strmsg);
		}

		//２）　@拡張投信銘柄オブジェクトを取得する
		WEB3MutualFundProduct l_web3MutualFundProduct =
			(WEB3MutualFundProduct) this.toProduct(
				(MutualFundProductRow) l_mutualFundProduct.getDataSourceObject());

		//３）　@取得した拡張投信銘柄オブジェクトを返す
		log.exiting(STR_METHOD_NAME);
		return l_web3MutualFundProduct;
	}

	/**
	 * (get投信取引銘柄)<BR>
	 * （getMutualFundTradedProductのオーバーライド）<BR>
	 * <BR>
	 * 拡張投信取引銘柄を取得する。<BR>
	 * <BR>
	 * １）　@投信取引銘柄オブジェクトを取得する。<BR>
	 * 　@super.getMutualFundTradedProduct()をコールして、<BR>
	 * 投信取引銘柄オブジェクトを取得する。<BR>
	 * 　@[getMutualFundTradedProductに渡すパラメタ]<BR>
	 * 　@　@証券会社： 引数.証券会社<BR>
	 * 　@　@銘柄コード： 引数.銘柄コード<BR>
	 * 　@　@回号コード： 引数.回号コード<BR>
	 * 　@　@市場コード： 引数.市場コード<BR>
	 * <BR>
	 * ２）　@拡張投信取引銘柄オブジェクトを取得する。<BR>
	 * 　@this.to取引銘柄()をコールして、拡張投信取引銘柄オブジェクトを取得する。<BR>
	 * 　@[to取引銘柄に渡すパラメタ］<BR>
	 * 　@　@Rowオブジェクト： <BR>
	 *       取得した投信取引銘柄オブジェク.getDataSourceObject()の戻り値<BR>
	 * <BR>
	 * ３）　@取得した拡張投信取引銘柄オブジェクトを返す。<BR>
	 * <BR>
	 * @@param l_institution - 証券会社
	 * @@param l_strProductCode - 銘柄コード
	 * @@param l_strProductIssueCode - 回号コード
	 * @@param l_strMarketCode - 市場コード
	 * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradedProduct
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
	 * @@roseuid 40AD90090280
	 */
	public MutualFundTradedProduct getMutualFundTradedProduct(
		Institution l_institution,
		String l_strProductCode,
		String l_strProductIssueCode,
		String l_strMarketCode)
		throws NotFoundException
	{
		final String STR_METHOD_NAME =
			"getMutualFundTradedProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode,"
				+ "String l_strProductIssueCode,"
				+ "String l_strMarketCode)";
		log.entering(STR_METHOD_NAME);

		//１）　@投信取引銘柄オブジェクトを取得する
		MutualFundTradedProduct l_mutualFundTradedProduct =
			super.getMutualFundTradedProduct(
				l_institution,
				l_strProductCode,
				l_strProductIssueCode,
				l_strMarketCode);

		if (l_mutualFundTradedProduct == null)
		{
			String l_strmsg =
				"拡張投信取引銘柄取得できない場合エラー for "
					+ "l_institution = "
					+ l_institution
					+ ", l_strProductCode = "
					+ l_strProductCode
					+ ", l_strProductIssueCode = "
					+ l_strProductIssueCode
					+ ", l_strMarketCode = "
					+ l_strMarketCode;
			log.debug(l_strmsg);
			log.exiting(STR_METHOD_NAME);
			throw new NotFoundException(l_strmsg);
		}

		//２）　@拡張投信取引銘柄オブジェクトを取得する
		WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
			(WEB3MutualFundTradedProduct) this.toTradedProduct(
				(MutualFundTradedProductRow) l_mutualFundTradedProduct.getDataSourceObject());

		log.exiting(STR_METHOD_NAME);

		//３）　@取得した拡張投信取引銘柄オブジェクトを返す
		return l_web3MutualFundTradedProduct;
	}

	/**
	 * (to銘柄)<BR>
	 * （toProductのオーバーライド）<BR>
	 * <BR>
	 * 指定のRowオブジェクトから拡張投信銘柄オブジェクトを作成して返す。<BR>
	 * <BR>
	 * １）　@引数.RowオブジェクトがProductRowクラスのインスタンスでなく、<BR>
	 * 　@かつMutualFundProductRowクラスのインスタンスでない場合は、<BR>
	 * 　@IllegalArgumentExceptionをスローする<BR>
	 * <BR>
	 * ２）　@拡張投信銘柄オブジェクトを生成する<BR>
	 * 　@－引数.RowオブジェクトがProductRowクラスのインスタンスの場合<BR>
	 * 　@　@［コンストラクタに渡すパラメタ］<BR>
	 * 　@　@　@銘柄Row： 引数.RowオブジェクトをProductRowクラスにキャストしたもの<BR>
	 * 　@－引数.RowオブジェクトがMutualFundProductRowクラスのインスタンスの場合<BR>
	 * 　@　@［コンストラクタに渡すパラメタ］<BR>
	 * 　@　@　@投信銘柄Row： 引数.RowオブジェクトをMutualFundProductRowクラスにキャストしたもの<BR>
	 * <BR>
	 * ３）　@生成した拡張投信銘柄オブジェクトを返す。<BR>
	 * <BR>
	 * @@param l_rowObject - (Rowオブジェクト)<BR>
	 * 銘柄Rowオブジェクト<BR>
	 * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Product
	 * @@roseuid 40AD90090290
	 */
	public Product toProduct(Row l_rowObject)
	{
		final String STR_METHOD_NAME = "toProduct(Row l_rowObject)";
		log.entering(STR_METHOD_NAME);

		if (l_rowObject == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		WEB3MutualFundProduct l_web3MutualFundProduct = null;
		try
		{
			//１）　@引数.RowオブジェクトがProductRowクラスのインスタンスでなく、
			//　@かつMutualFundProductRowクラスのインスタンスでない場合
			if (!(l_rowObject instanceof ProductRow)
				&& !(l_rowObject instanceof MutualFundProductRow))
			{
				log.debug("__an IllegalArgumentException__");
				log.exiting(STR_METHOD_NAME);
				String l_strmsg = this.getClass().getName() + "." + STR_METHOD_NAME 
						+ ": The expect type is ProductRow or MutualFundProductRow,"
						+ " but in fact it is " + l_rowObject.getClass();
				throw new IllegalArgumentException(l_strmsg);
			}
            
			//２）　@引数.RowがProductRowクラスのインスタンスである場合
			if (l_rowObject instanceof ProductRow)
			{
				l_web3MutualFundProduct =
					new WEB3MutualFundProduct((ProductRow) l_rowObject);
			}
            
			//２）引数.RowがMutualFundProductRowクラスのインスタンスである場合
			if (l_rowObject instanceof MutualFundProductRow)
			{
				l_web3MutualFundProduct =
					new WEB3MutualFundProduct(
						(MutualFundProductRow) l_rowObject);
			}
		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when new WEB3MutualFundProduct()");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when new WEB3MutualFundProduct()");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//３）　@生成した拡張投信銘柄オブジェクトを返す
		return l_web3MutualFundProduct;
	}

	/**
	 * (to取引銘柄)<BR>
	 * （toTradedProductのオーバーライド）<BR>
	 * <BR>
	 * 指定のRowオブジェクトから拡張投信取引銘柄オブジェクトを作成して返す。<BR>
	 * <BR>
	 * １）　@引数.RowオブジェクトがTradedProductRowクラスのインスタンスでなく、<BR>
	 * 　@かつMutualFundTradedProductRowクラスのインスタンスでない場合は、<BR>
	 * 　@IllegalArgumentExceptionをスローする<BR>
	 * <BR>
	 * ２）　@拡張投信取引銘柄オブジェクトを生成する<BR>
	 * 　@－引数.RowオブジェクトがTradedProductRowクラスのインスタンスの場合<BR>
	 * 　@　@［コンストラクタに渡すパラメタ］<BR>
	 * 　@　@　@取引銘柄Row： 引数.RowオブジェクトをTradedProductRowクラスにキャストしたもの<BR>
	 * 　@－引数.RowオブジェクトがMutualFundTradedProductRowクラスのインスタンスの場合<BR>
	 * 　@　@［コンストラクタに渡すパラメタ］<BR>
	 * 　@　@　@投信取引銘柄Row： <BR>
	 * 引数.RowオブジェクトをMutualFundTradedProductRowクラスにキャストしたもの<BR>
	 * <BR>
	 * ３）　@生成した拡張投信取引銘柄オブジェクトを返す。<BR>
	 * <BR>
	 * @@param l_rowObject - 取引銘柄Rowオブジェクト
	 * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct
	 * @@roseuid 40AD9009029F
	 */
	public TradedProduct toTradedProduct(Row l_rowObject)
	{
		final String STR_METHOD_NAME = "toTradedProduct(Row l_rowObject)";
		log.entering(STR_METHOD_NAME);

		if (l_rowObject == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct = null;
		try
		{
			//１）　@引数.RowオブジェクトがTradedProductRowクラスのインスタンスでなく、
			// 　@かつMutualFundTradedProductRowクラスのインスタンスでない場合は、
			if (!(l_rowObject instanceof TradedProductRow)
				&& !(l_rowObject instanceof MutualFundTradedProductRow))
			{
				log.debug("__an IllegalArgumentException__");
				log.exiting(STR_METHOD_NAME);
				String l_strmsg = this.getClass().getName() + "." + STR_METHOD_NAME 
						+ ": The expect type is TradedProductRow or"
						+ " MutualFundTradedProductRow,"
						+ " but in fact it is " + l_rowObject.getClass();
				throw new IllegalArgumentException(l_strmsg);
			}

			//２）　@引数.RowオブジェクトがTradedProductRowクラスのインスタンスである場合
			if (l_rowObject instanceof TradedProductRow)
			{
				l_web3MutualFundTradedProduct =
					new WEB3MutualFundTradedProduct(
						(TradedProductRow) l_rowObject);
			}

			//２）　@引数.RowオブジェクトがTradedProductRowクラスのインスタンスである場合
			if (l_rowObject instanceof MutualFundTradedProductRow)
			{
				l_web3MutualFundTradedProduct =
					new WEB3MutualFundTradedProduct(
						(MutualFundTradedProductRow) l_rowObject);
			}

		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when new WEB3MutualFundTradedProduct()");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when new WEB3MutualFundTradedProduct()");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//３）　@生成した拡張投信取引銘柄オブジェクトを返す
		return l_web3MutualFundTradedProduct;
	}

	/**
	 * (get投信銘柄)<BR>
	 * （getMutualFundProductのオーバーロード）<BR>
	 * <BR>
	 * 拡張投信銘柄を取得する。<BR>
	 * <BR>
	 * １）　@this.get拡張投信銘柄()をコールし、その戻り値を返す。<BR>
	 * 　@［get拡張投信銘柄に渡すパラメタ］<BR>
	 * 　@　@証券会社： 引数.証券会社<BR>
	 * 　@　@銘柄コード： 引数銘柄コード<BR>
	 * 　@　@回号コード： ”0：DEFAULT”<BR>
	 * <BR>
	 * @@param l_institution - 証券会社
	 * @@param l_strProductCode - 銘柄コード
	 * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
	 * @@roseuid 40AD9019003E
	 */
	public MutualFundProduct getMutualFundProduct(
		Institution l_institution,
		String l_strProductCode)
		throws NotFoundException
	{
		final String STR_METHOD_NAME =
			"getMutualFundProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode)";
		log.entering(STR_METHOD_NAME);
		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        
		//１）　@this.get拡張投信銘柄()をコールし、その戻り値を返す
		MutualFundProduct l_mutualFundProduct =
			this.getMutualFundProduct(l_institution, l_strProductCode, "0");
		log.exiting(STR_METHOD_NAME);
		return l_mutualFundProduct;
	}

	/**
	 * (get投信取引銘柄)<BR>
	 * （getMutualFundTradedProductのオーバーロード）<BR>
	 * <BR>
	 * 拡張投信取引銘柄を取得する。<BR>
	 * <BR>
	 * １）　@this.get拡張投信取引銘柄()をコールし、その戻り値を返す。<BR>
	 * 　@［get拡張投信取引銘柄に渡すパラメタ］<BR>
	 * 　@　@証券会社： 引数.証券会社<BR>
	 * 　@　@銘柄コード： 引数銘柄コード<BR>
	 * 　@　@回号コード： ”0：DEFAULT”<BR>
	 * 　@　@市場コード： ”0：DEFAULT”<BR>
	 * <BR>
	 * @@param l_institution - 証券会社
	 * @@param l_strProductCode - 銘柄コード
	 * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradedProduct
	 * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
	 * @@roseuid 40AD9019005D
	 */
	public MutualFundTradedProduct getMutualFundTradedProduct(
		Institution l_institution,
		String l_strProductCode)
		throws NotFoundException
	{
		final String STR_METHOD_NAME =
			"getMutualFundTradedProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode)";
		log.entering(STR_METHOD_NAME);
		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
       
		//１）　@this.get拡張投信取引銘柄()をコールし、その戻り値を返す
		MutualFundTradedProduct l_mutualFundTradedProduct =
			this.getMutualFundTradedProduct(
				l_institution,
				l_strProductCode,
				"0",
				WEB3MarketCodeDef.DEFAULT);
		log.exiting(STR_METHOD_NAME);                
		return l_mutualFundTradedProduct;
	}

	/**
	 * (get投信銘柄カテゴリーリスト)<BR>
	 * 投信銘柄カテゴリーParamsのリストを返す。<BR>
	 * <BR>
	 * １）　@投信銘柄カテゴリーテーブルを検索し、投信銘柄カテゴリーParams<BR>
	 * 　@　@オブジェクトのListを取得して返す。<BR>
	 * 　@　@［検索条件］<BR>
	 * 　@　@　@証券会社コード = 引数.証券会社コード<BR>
	 * 　@　@［ソート条件］<BR>
	 * 　@　@　@親カテゴリーコード nulls first<BR>
	 * 　@　@　@カテゴリーコード<BR>
	 * <BR>
	 * 　@－取得した投信銘柄カテゴリーParamsオブジェクトのListを返す。<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - 証券会社コード
	 * @@return List
	 * @@roseuid 40ADB77E0203
	 */
	public List getMutualFundProductCategoryList(String l_strInstitutionCode) 
			throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getMutualFundProductCategoryList(String l_strInstitutionCode)";
		log.entering(STR_METHOD_NAME);

		if (l_strInstitutionCode == null || l_strInstitutionCode.length() == 0)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		List l_rtnList = new Vector();

		//検索の準備
		String l_whereClause = " institution_code = ? ";
		String l_strSortCond =
			" parent_category_code nulls first , category_code ";
		Object l_bindVars[] = { l_strInstitutionCode };

		try
		{
			//１）　@投信銘柄カテゴリーテーブルを検索し、投信銘柄カテゴリーParamsのListを取得し
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_rtnList =
				l_queryProcessor.doFindAllQuery(
					MutualFundProductCategoryRow.TYPE,
					l_whereClause,
					l_strSortCond,
					null,
					l_bindVars);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました  when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//取得した投信銘柄カテゴリーParamsオブジェクトのListを返す
		return l_rtnList;
	}

	/**
	 * (get下位投信銘柄カテゴリーリスト)<BR>
	 * 指定されたカテゴリーコードに紐付く下位カテゴリーの<BR>
	 * 投信銘柄カテゴリーParamsのリストを返す。<BR>
	 * <BR>
	 * １）　@投信銘柄カテゴリーテーブルを検索し、投信銘柄カテゴリーParams<BR>
	 * 　@　@オブジェクトのListを取得して返す。<BR>
	 * <BR>
	 * 　@　@［検索条件文字列］<BR>
	 * 　@　@　@ "カテゴリーコード != ?<BR>
	 * 　@　@　@　@connect by prior カテゴリーコード = 親カテゴリーコード<BR>
	 * 　@　@　@　@and 証券会社コード = ?<BR>
	 * 　@　@　@　@start with カテゴリーコード = ? and 証券会社コード = ?"<BR>
	 * <BR>
	 * 　@　@［検索条件データコンテナ］<BR>
	 * 　@　@　@引数.カテゴリーコード<BR>
	 * 　@　@　@引数.証券会社コード<BR>
	 * 　@　@　@引数.カテゴリーコード<BR>
	 * 　@　@　@引数.証券会社コード<BR>
	 * <BR>
	 * 　@　@［ソート条件］<BR>
	 * 　@　@　@親カテゴリーコード nulls first<BR>
	 * 　@　@　@カテゴリーコード<BR>
	 * <BR>
	 * 　@－取得した投信銘柄カテゴリーParamsオブジェクトのListを返す。<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - 証券会社コード
	 * @@param l_strCategoryCode - 銘柄カテゴリーコード
	 * @@return List
	 * @@roseuid 40DBCE500193
	 */
	public List getLowMutualFundProductCategoryList(
		String l_strInstitutionCode,
		String l_strCategoryCode) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getLowMutualFundProductCategoryList("
				+ "String l_strInstitutionCode,"
				+ "String l_strCategoryCode)";
		log.entering(STR_METHOD_NAME);

		List l_rtnList = new Vector();

		// 検索の準備
		//［検索条件文字列］
		String l_whereClause = " category_code != ? ";
		String l_strConditions = " connect by prior "
				+ " category_code = parent_category_code "
				+ " and institution_code = ? "
				+ " start with category_code = ? and institution_code = ? ";

		//［ソート条件］
		String l_strSortCond =
			" order by parent_category_code nulls first , category_code ";

		//［検索条件データコンテナ］
		Object l_bindVars[] =
			{
				l_strCategoryCode,
				l_strInstitutionCode,
				l_strCategoryCode,
				l_strInstitutionCode };

		try
		{
			//１）　@投信銘柄カテゴリーテーブルを検索し、投信銘柄カテゴリーParamsListを取得して返す            
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

			l_rtnList =
				l_queryProcessor.doFindAllQuery(
					MutualFundProductCategoryRow.TYPE,
					l_whereClause,
					null,
					l_strConditions + l_strSortCond,
					l_bindVars);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}
		//取得した投信銘柄カテゴリーParamsオブジェクトのListを返す        
		return l_rtnList;
	}
    
	/**
     * (get投信銘柄カテゴリー)<BR>
	 * 指定された投信銘柄カテゴリーを取得し、返却する。<BR> 
	 * １）以下の条件で、投信銘柄カテゴリーテーブルを検索する。<BR> 
	 *[検索条件] <BR>
　@	 * 証券会社コード=引数.証券会社コード and <BR>
　@	 * カテゴリーコード=引数.投信銘柄カテゴリーコード <BR>
	 *<BR>
	 *２）検索結果==0件の場合、nullを返却する。<BR> 
	 *<BR>
	 *３）検索結果==1件の場合、投信銘柄カテゴリーのコンストラクタをコールし、<BR> 
　@	 *  生成した投信銘柄カテゴリーオブジェクトを返却する。<BR> 
	 * [引数] <BR>
	 *  投信銘柄カテゴリー行=検索結果である投信銘柄カテゴリーParamsオブジェクト<BR>
	 * 
	 * @@param l_strInstitutionCode - 証券会社コード
	 * @@param l_strCategoryCode - 投信銘柄カテゴリーコード
	 * @@throws WEB3BaseException
	 * @@roseuid 40DBCE500193
	 */   
	public WEB3MutualProductCategory getMutualFundProductCategory(
		String l_strInstitutionCode, 
		String l_strCategoryCode) throws WEB3BaseException
	{   
		final String STR_METHOD_NAME =
			"getMutualFundProductCategory(String l_strInstitutionCode, " +
			"String l_strCategoryCode)";
		log.entering(STR_METHOD_NAME);

		//［ソート条件］
		String l_strWhere = "institution_code = ? and category_code = ?";
		//［検索条件データコンテナ］
		Object[] l_bindVars = {l_strInstitutionCode, l_strCategoryCode};
        
		//１）以下の条件で、投信銘柄カテゴリーテーブルを検索する。 
		//  [検索条件] 
		//　@ 証券会社コード=引数.証券会社コード and 
		//　@ カテゴリーコード=引数.投信銘柄カテゴリーコード 
		List l_lisMFProductCategoryRows = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisMFProductCategoryRows =
				l_queryProcessor.doFindAllQuery(
					MutualFundProductCategoryRow.TYPE, 
					l_strWhere, 
					l_bindVars);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
        
		int l_intSize = l_lisMFProductCategoryRows.size();
        
		//２）検索結果==0件の場合、nullを返却する。 
		if (l_intSize == 0)
		{
			log.exiting(STR_METHOD_NAME);
			return null;
		}
        
		//３）検索結果==1件の場合、投信銘柄カテゴリーのコンストラクタをコールし、 
		//  生成した投信銘柄カテゴリーオブジェクトを返却する。 
		//  [引数] 
		//　@投信銘柄カテゴリー行=検索結果である投信銘柄カテゴリーParamsオブジェクト
		WEB3MutualProductCategory l_mutualProductCategory  = null;
		if (l_intSize == 1)
		{
			MutualFundProductCategoryRow 
				l_mutualFundProductCategoryRow  = 
					(MutualFundProductCategoryRow) l_lisMFProductCategoryRows.get(0);
			l_mutualProductCategory = 
				new WEB3MutualProductCategory(l_mutualFundProductCategoryRow);
		}
		log.exiting(STR_METHOD_NAME);
		return l_mutualProductCategory;
	} 
    
	/**
	 * (validate投信銘柄カテゴリー階層)<BR>
	 * 指定された親カテゴリーコードの階層が、<BR>
	 * ３階層より深くなっていないかチェックする。<BR>
	 *（指定された親カテゴリー≦３階層（孫）であった場合、例外をスローする。）<BR> 
	 * シーケンス図「（投信）validate投信銘柄カテゴリー階層」参照<BR>
	 * 
	 * @@param l_strInstitutionCode - 証券会社コード
	 * @@param l_strCategoryCode - 投信銘柄カテゴリーコード
	 * @@param l_strParentCategoryCode - 親カテゴリーコード
	 * @@throws WEB3BaseException
	 * @@roseuid 40DBCE500193
	 */ 
	public void validateProductCategorystorey(
		String l_strInstitutionCode, 
		String l_strCategoryCode,
		String l_strParentCategoryCode) throws WEB3BaseException 
	{
		final String STR_METHOD_NAME =
			"validateProductCategorystorey(String l_strInstitutionCode, " +
			"String l_strCategoryCode) ";
		log.entering(STR_METHOD_NAME);

		//1.1)get投信銘柄カテゴリー(String, String)
		//引数.投信銘柄カテゴリーの投信銘柄カテゴリーオブジェクトを取得する。 
		//[get投信銘柄カテゴリーに渡す引数] 
		//  証券会社コード=引数.証券会社コード 
		// 投信銘柄カテゴリーコード=引数.親カテゴリーコード
		WEB3MutualProductCategory 
			l_mutualProductCategory1 =
				this.getMutualFundProductCategory(
					l_strInstitutionCode, 
					l_strParentCategoryCode);
        
		boolean l_blnFlag = false;
		if (l_mutualProductCategory1 != null)
		{
			//1.1.1)get親カテゴリーコード( )
			String l_strParentCategoryCode1 = 
				l_mutualProductCategory1.getParentCategoryCode();
				
			if (l_strParentCategoryCode1 != null)
			{
                
		  		//1.2)get投信銘柄カテゴリー(String, String)
		  		//引数.投信銘柄カテゴリーの親カテゴリーとなる投信銘柄カテゴリーオブジェクトを取得する。 
		  		//[get投信銘柄カテゴリーに渡す引数] 
		  		//  証券会社コード=引数.証券会社コード 
		  		//  投信銘柄カテゴリーコード=取得した親カテゴリーコード
		  		WEB3MutualProductCategory 
			  		l_mutualProductCategory2 =
				  		this.getMutualFundProductCategory(
					  		l_strInstitutionCode, 
					  		l_strParentCategoryCode1);

		  		if (l_mutualProductCategory2 != null)
		  		{
			  		//1.2.1)get親カテゴリーコード( )
			  		String l_strParentCategoryCode2 = 
				  		l_mutualProductCategory2.getParentCategoryCode();
			  		//最大階層オーバーエラー
            
					l_blnFlag = true;     
			  		//1.3)get親カテゴリーコード( )の戻り値!=nullの場合
			  		if (l_strParentCategoryCode2 != null)
			  		{
						log.debug("最大階層オーバーエラー。");
						log.exiting(STR_METHOD_NAME);
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_01293,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							"最大階層オーバーエラー。");
					}
			    }            
		    }            
	    }
        
	   if (l_strCategoryCode != null)
		{		
			//1.4)get下位投信銘柄カテゴリーリスト(String, String)
			//  指定されたカテゴリーコードを「親カテゴリーコード」に持つ投信銘柄カテゴリーの一覧を検出する。 
			//  [get下位投信銘柄カテゴリーリストに渡す引数] 
			//      証券会社コード=引数.証券会社コード 
			//      カテゴリーコード=引数.カテゴリーコード
			List l_lisLowMutualFundProductList =
				this.getLowMutualFundProductCategoryList(
					l_strInstitutionCode, 
					l_strCategoryCode);
        
			//1.5)のget下位投信銘柄カテゴリーリスト( )の戻り値が!=nullであり、かつ件数＞0件の場合
			if (l_lisLowMutualFundProductList != null && 
				!l_lisLowMutualFundProductList.isEmpty())
			{
				//1.5.1)上位階層のチェックで、チェック２回目を実施している場合
				if (l_blnFlag)
				{
					log.debug("最大階層オーバーエラー。");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_01293,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"最大階層オーバーエラー。");
				}
        
				//1.5.2)繰り返し処理
				MutualFundProductCategoryParams l_mutualFundProductCategoryParams = null;
				for (int i = 0; i < l_lisLowMutualFundProductList.size(); i++)
				{
					//1.5.2.1)get下位投信銘柄カテゴリーリスト(String, String)     
					//「親カテゴリーコード」に持つ投信銘柄カテゴリーの一覧を検出する。 
					//[get下位投信銘柄カテゴリーリストに渡す引数] 
					//証券会社コード=引数.証券会社コード 
					//カテゴリーコード=<一次検索>で取得した検索結果の 
					//投信銘柄カテゴリーParamsオブジェクト.getカテゴリーコード( )
					l_mutualFundProductCategoryParams =
						(MutualFundProductCategoryParams) l_lisLowMutualFundProductList.get(i);
                
					List l_lisLowMutualFundProductCategory2 =
						this.getLowMutualFundProductCategoryList(
							l_strInstitutionCode,
							l_mutualFundProductCategoryParams.getCategoryCode());
        
					//1.5.2.2のget下位投信銘柄カテゴリーリスト( )の戻り値が!=nullであり、かつ件数＞0件の場合
					if (l_lisLowMutualFundProductCategory2 != null && 
						!l_lisLowMutualFundProductCategory2.isEmpty())
					{
						log.debug("最大階層オーバーエラー。");
						log.exiting(STR_METHOD_NAME);
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_01293,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							"最大階層オーバーエラー。");
					}
				}
			}
		}
		log.exiting(STR_METHOD_NAME);
	}
    
	/**
	 * (create投信銘柄カテゴリー一覧)<BR>
	 * 投信銘柄カテゴリーテーブルの検索結果からレスポンスに引き渡す為、<BR>
	 * ツリー構造の配列を作成する。<BR>
	 * <BR>
	 * １)　@引数:銘柄カテゴリー一覧の全ての要素を、<BR>
	 * 当メソッドの戻り値（以下、"戻り値オブジェクト"と表記)<BR>
	 * 　@となるオブジェクトに移し変えるまで以下を繰り返す。<BR>
	 * 　@１－１)　@投信銘柄カテゴリーコード名称オブジェクトを生成する。<BR>
	 * 　@　@　@　@[引数]<BR>
	 * 　@　@　@　@　@銘柄カテゴリーParams.getカテゴリーコード( )<BR>
	 * 　@　@　@　@　@銘柄カテゴリーParams.getカテゴリー名称( )<BR>
	 * <BR>
	 * 　@１－２)　@銘柄カテゴリーParams.get親カテゴリーコード( )をコールして、<BR>
	 * 親カテゴリーコードを取得する。<BR>
	 * 　@　@１－２－１)　@親カテゴリーコードがnullの場合<BR>
	 * 　@　@　@　@　@　@　@戻り値オブジェクトトにそのまま追加する。<BR>
	 * <BR>
	 * 　@　@１－２－２)　@１－２)の戻り値がnull以外の場合<BR>
	 * 　@　@　@１－２－２－１)　@戻り値オブジェクトの中から、<BR>
	 * 親カテゴリーコードと同じカテゴリーコードを持つオブジェクトが存在する場合<BR>
	 * 　@　@　@　@　@　@　@　@　@　@　@そのオブジェクト.内包カテゴリーコード名称に追加する。<BR>
	 * (既に存在する場合は、追加しない)<BR>
	 * <BR>
	 * 　@　@　@１－２－２－２)　@戻り値オブジェクトの中から、<BR>
	 * 親カテゴリーコードと同じカテゴリーコードを持つオブジェクトが存在しない場合<BR>
	 * 　@　@　@　@１－２－２－２－１)　@引数:銘柄カテゴリー一覧から<BR>
	 * 親カテゴリーコードと同じカテゴリーコードを持つオブジェクトを探し出し、<BR>
	 * そのオブジェクトを戻り値オブジェクトに追加する。<BR>
	 * <BR>
	 * ２)　@作成した戻り値オブジェクトをリターンする。<BR>
	 * <BR>
	 * @@param l_lisProductCategoryList - 銘柄カテゴリー一覧
	 * @@return WEB3MutualProductCategoryUnit[ ]
	 * @@roseuid 40E4E09602B1
	 */
	public WEB3MutualProductCategoryUnit[] createMutualFundProductCategoryList(List l_lisProductCategoryList)
	{
		final String STR_METHOD_NAME =
			"createMutualFundProductCategoryList(List l_lisProductCategoryList)";
		log.entering(STR_METHOD_NAME);

		WEB3MutualProductCategoryUnit[] l_arrayWEB3MFProductCategoryUnit = null;
        
		int l_listLength = l_lisProductCategoryList.size();
		List l_lisMutualProductCategoryUnit = new Vector();
		Map l_mapIndex = new Hashtable();
		Map l_mapRelation = new Hashtable();

		for (int i = 0; i < l_listLength; i++)
		{
			log.debug(" i = " + i);
			// 銘柄カテゴリーParamsを取得する
			MutualFundProductCategoryParams l_productCategoryParams =
				(MutualFundProductCategoryParams) l_lisProductCategoryList.get(i);

			// １－１)　@投信銘柄カテゴリーコード名称オブジェクトを生成する        
			WEB3MutualProductCategoryUnit l_web3MutualProductCategoryUnit =
				new WEB3MutualProductCategoryUnit();

			// 銘柄カテゴリーParams.getカテゴリーコード()
			String l_strCategoryCode = l_productCategoryParams.getCategoryCode();
			l_web3MutualProductCategoryUnit.categoryCode = l_strCategoryCode;

			// 銘柄カテゴリーParams.getカテゴリー名称()
			l_web3MutualProductCategoryUnit.categoryName =
				l_productCategoryParams.getCategoryName();

			l_mapIndex.put(l_strCategoryCode, l_web3MutualProductCategoryUnit);
			log.debug(" Node_" + i + "= " + l_strCategoryCode);            
			l_mapRelation.put(l_strCategoryCode, new Vector());
		}

		for (int i = 0; i < l_listLength; i++)
		{

			MutualFundProductCategoryParams l_productCategoryParams =
				(MutualFundProductCategoryParams) l_lisProductCategoryList.get(i);

			String l_strCategoryCode = l_productCategoryParams.getCategoryCode();
			String l_strParentCode = l_productCategoryParams.getParentCategoryCode();
            
			log.debug(" Node_" + i + ".l_strCategoryCode= " + l_strCategoryCode);
			log.debug(" Node_" + i + ".l_strParentCode= " + l_strParentCode);

			//　@投信銘柄カテゴリーコード名称オブジェクトを生成する        
			WEB3MutualProductCategoryUnit l_web3MutualProductCategoryUnit =
				(WEB3MutualProductCategoryUnit)l_mapIndex.get(l_strCategoryCode);
                
			if (l_strParentCode != null && l_mapRelation.containsKey(l_strParentCode))
			{
				((List)l_mapRelation.get(l_strParentCode)).add(l_web3MutualProductCategoryUnit);
                
				log.debug(" Node_" + l_strParentCode + ".child = " + l_productCategoryParams.category_code);
			}
			else
			{
				l_lisMutualProductCategoryUnit.add(l_web3MutualProductCategoryUnit);
				log.debug(" ParentNode " + l_productCategoryParams.category_code);
			}
		}

		int l_intLisCategoryUnitLength = l_lisMutualProductCategoryUnit.size();
		l_arrayWEB3MFProductCategoryUnit = new WEB3MutualProductCategoryUnit[l_intLisCategoryUnitLength];
		l_lisMutualProductCategoryUnit.toArray(l_arrayWEB3MFProductCategoryUnit);

		for (int i = 0; i < l_listLength; i++)
		{
			// 銘柄カテゴリーParamsを取得する
			MutualFundProductCategoryParams l_productCategoryParams =
				(MutualFundProductCategoryParams) l_lisProductCategoryList.get(i);

			String l_strCategoryCode = l_productCategoryParams.getCategoryCode();

			List childList = (List) l_mapRelation.get(l_strCategoryCode);

			if (childList != null && childList.size() != 0)
			{
				WEB3MutualProductCategoryUnit l_web3MutualProductCategoryUnit =
					(WEB3MutualProductCategoryUnit) l_mapIndex.get(l_strCategoryCode);

				l_web3MutualProductCategoryUnit.childCategory =
					new WEB3MutualProductCategoryUnit[childList.size()];

				childList.toArray(
					l_web3MutualProductCategoryUnit.childCategory);
				log.debug(" ParentNode_" + l_web3MutualProductCategoryUnit.categoryCode 
							 + ".ChildenLength = " 
							 + l_web3MutualProductCategoryUnit.childCategory.length);
			}
		}

		log.exiting(STR_METHOD_NAME);
		return l_arrayWEB3MFProductCategoryUnit;
	}
    
	/**
	 * (get投信銘柄リスト)<BR>
	 * 投信銘柄Paramsのリストを返す。<BR>
	 * <BR>
	 * １)　@検索条件補正<BR>
	 * 　@１－１)　@引数:検索条件文字列がnull以外の場合、<BR>
	 * 先頭に" and "を追加する。<BR>
	 * <BR>
	 * 　@１－２)　@引数:検索条件文字列の先頭に以下を追加する。<BR>
	 * 　@　@　@　@"証券会社コード=?"<BR>
	 * 　@　@　@　@（引数:検索条件文字列がnullで無い場合、<BR>
	 * 追加する文字列の後に" and "を付ける）<BR>
	 * <BR>
	 * 　@１－３)　@引数:検索条件データコンテナの先頭に以下を追加する。<BR>
	 * 　@　@　@　@引数:証券会社コード<BR>
	 * <BR>
	 * ２）　@投信銘柄マスタテーブルを検索し、<BR>
	 * 投信銘柄ParamsオブジェクトのListを取得して返す。<BR>
	 * 　@　@［検索条件］<BR>
	 * 　@　@　@１－２)で作成した検索条件文字列<BR>
	 * 　@　@　@１－３)で作成した検索条件データコンテナ<BR>
	 * 　@　@ [並び替え］<BR>
	 * ・引数.ソート条件区分＝”買付一覧照会”の場合  <BR>
	 * －表示順位で昇順、銘柄コードで昇順  <BR>
	 * ・引数.ソート条件区分＝”管理者投信銘柄条件登録照会”の場合  <BR>
	 * －銘柄コードで昇順  <BR>
	 * <BR>
	 * 　@－取得した投信銘柄ParamsオブジェクトのListを返す。<BR>
	 * <BR>
	 * @@param l_strInstitutionCode - 証券会社コード
	 * @@param l_strSearchCondCharacterString - 検索条件文字列
	 * @@param l_strSearchCondDataContainer - 検索条件データコンテナ
	 * @@param l_strSearchCondDef - ソート条件区分
	 * @@return List
	 * @@roseuid 40ADB8A601E4
	 */
	public List getMutualFundProductList(
		String l_strInstitutionCode,
		String l_strSearchCondCharacterString,
		String[] l_strSearchCondDataContainer,
		String l_strSearchCondDef) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getMutualFundProductList("
				+ "String l_strInstitutionCode,"
				+ "String l_strSearchCondCharacterString,"
				+ "String[] l_strSearchCondDataContainer)";
		log.entering(STR_METHOD_NAME);

		if (l_strInstitutionCode == null || l_strInstitutionCode.length() == 0)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		List l_rtnList = null;

		//１)　@検索条件補正
		//１－１)　@引数:検索条件文字列がnull以外の場合
		if (l_strSearchCondCharacterString != null
			&& !"".equals(l_strSearchCondCharacterString))
		{
			l_strSearchCondCharacterString =
				" and " + l_strSearchCondCharacterString;
		}

		//１－２)　@引数:検索条件文字列の先頭に以下を追加する
		if(l_strSearchCondCharacterString != null)
		{
			l_strSearchCondCharacterString =
				" institution_code = ? " + l_strSearchCondCharacterString;
		}
		else
		{
			l_strSearchCondCharacterString =
				" institution_code = ? ";
		}

		//１－３)　@引数:検索条件データコンテナの先頭に以下を追加する
		String[] l_bindVars = null;

		if (l_strSearchCondDataContainer != null
			&& l_strSearchCondDataContainer.length != 0)
		{
			l_bindVars =
				new String[l_strSearchCondDataContainer.length + 1];
			l_bindVars[0] = l_strInstitutionCode;
            
			for (int i = 1; i < l_strSearchCondDataContainer.length + 1; i++)
			{
				l_bindVars[i] = l_strSearchCondDataContainer[i - 1];
			}
		}
		else
		{
			l_bindVars =
				new String[1];
			l_bindVars[0] = l_strInstitutionCode;
		}

		//[並び替え］
		String l_strSort = " indication_ranking asc";
		if(WEBMFSortConditionDivDef.MUTUAL_BUY_LIST.equals(l_strSearchCondDef))
		{
			// ・引数.ソート条件区分＝”買付一覧照会”の場合 
			// －表示順位で昇順、銘柄コードで昇順  
			l_strSort = " indication_ranking asc, product_code asc";
		}
		else
		{
			if(WEBMFSortConditionDivDef.ADMIN_MUTUAL_COND_REF.equals(
					l_strSearchCondDef))
			{
				// ・引数.ソート条件区分＝”管理者投信銘柄条件登録照会”の場合 
				// －銘柄コードで昇順  
				l_strSort = " product_code asc ";
			}
		}

		// Test log
		log.debug("投信銘柄マスタテーブルを検索し l_strSearchCondCharacterString = " + l_strSearchCondCharacterString);
		for (int i = 0; i < l_bindVars.length; i ++)
		{
			log.debug("投信銘柄マスタテーブルを検索し l_bindVars " + i + "  = " + l_bindVars[i]);
		}

		try
		{
			//２）　@投信銘柄マスタテーブルを検索し、投信銘柄ParamsのListを取得して返す
			QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
			l_rtnList =
				l_QueryProcessor.doFindAllQuery(
					MutualFundProductRow.TYPE,
					l_strSearchCondCharacterString,
					l_strSort,
					null,
					l_bindVars);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when search MutualFundProduct");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when search MutualFundProduct");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//取得した投信銘柄ParamsオブジェクトのListを返す
		return l_rtnList;
	}

	/**
	 * (get受渡日)<BR>
	 * 受渡日を返す。<BR>
	 * <BR>
	 * １）　@拡張投信取引銘柄オブジェクトを取得する。<BR>
	 * 　@this.get投信取引銘柄()をコールして、<BR>
	 * 拡張投信取引銘柄オブジェクトを取得する。<BR>
	 * 　@［get投信取引銘柄に渡すパラメタ］<BR>
	 * 　@　@証券会社： 引数.証券会社<BR>
	 * 　@　@銘柄コード： 引数.銘柄コード<BR>
	 * <BR>
	 * ２）　@受渡日を取得する。<BR>
	 * 　@拡張投信取引銘柄.get受渡日()をコールして、受渡日を取得する。<BR>
	 * 　@［get受渡日に渡すパラメタ］<BR>
	 * 　@　@is買付： 引数.is買付<BR>
	 * <BR>
	 * ３）  拡張投信銘柄を取得する。<BR>
     * 　@　@　@this．get投信銘柄()<BR>
     * 　@　@　@[get投信銘柄()の引数]<BR>
     * 　@　@　@証券会社：引数.証券会社<BR>
     * 　@　@　@銘柄コード：引数.銘柄コード<BR>
     * <BR>
     * ４）  拡張投信銘柄.is外貨MMF = true の場合<BR>
     * 　@　@　@（取引時間管理.get投信発注日()と同じく、海外発注日を取得する）<BR>
     * <BR>
     * 　@　@４-１）　@取得した受渡日を引数に、投信海外市場カレンダ.is休日()をコールする。<BR>
     * <BR>
     * 　@　@　@４-１-１）　@is休日() = trueの場合 <BR>
     * 　@　@　@　@　@　@　@　@　@　@①@　@発注日計算を用いて翌営業日を取得する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@②　@取得した翌営業日を引数に、投信海外市場カレンダ.is休日()をコールする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@③　@is休日() = trueの場合、①@へ戻る<BR>
     * 　@　@　@　@　@　@　@　@　@　@④　@is休日() = falseの場合、取得した翌営業日を返す。<BR>
     * <BR>
     * 　@　@　@４-１-２）　@is休日() = falseの場合、取得した受渡日を返す。<BR>
     * <BR>
     * ５）  拡張投信銘柄.is外貨MMF = false の場合、取得した受渡日を返す。<BR>
	 * @@param l_institution - 証券会社
	 * @@param l_strProductCode - 銘柄コード
	 * @@param l_blnIsAcquired - (is買付)<BR>
	 * 買付の場合はtrueを、解約の場合はfalseを指定する<BR>
	 * @@return Timestamp
	 * @@roseuid 40B44D83037A
	 */
	public Timestamp getDeliveryDate(
		Institution l_institution,
		String l_strProductCode,
		boolean l_blnIsAcquired) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getDeliveryDate("
				+ "Institution l_institution,"
				+ "String l_strProductCode,"
				+ "boolean l_blnIsAcquired)";
		log.entering(STR_METHOD_NAME);

		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		Timestamp l_deliveryDate = null;
		Date l_datDeliveryDate = null;
		try
		{
			//１）　@拡張投信取引銘柄オブジェクトを取得する
			WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
				(WEB3MutualFundTradedProduct) this.getMutualFundTradedProduct(
					l_institution,
					l_strProductCode);

			//２）　@受渡日を取得する
			l_datDeliveryDate =
				l_web3MutualFundTradedProduct.getDeliveryDate(l_blnIsAcquired);
			l_deliveryDate = new Timestamp(l_datDeliveryDate.getTime());

            //３）  拡張投信銘柄を取得する。
            //      this．get投信銘柄()
            //      [get投信銘柄()の引数]
            //      証券会社：引数.証券会社
            //      銘柄コード：引数.銘柄コード
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct)this.getMutualFundProduct(
                    l_institution,
                    l_strProductCode);

            //４）  拡張投信銘柄.is外貨MMF = true の場合
            //　@　@　@（取引時間管理.get投信発注日()と同じく、海外発注日を取得する）
            if (l_mutualFundProduct.isFrgnMmf())
            {
                //　@　@４-１）　@取得した受渡日を引数に、投信海外市場カレンダ.is休日()をコールする。
                WEB3AdminMutualFrgncal l_adminMutualFrgncal =
                    new WEB3AdminMutualFrgncal();
                boolean l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                    l_institution.getInstitutionCode(),
                    l_strProductCode,
                    l_deliveryDate);

                //４-１-１）　@is休日() = trueの場合
                //　@　@　@①@　@発注日計算を用いて翌営業日を取得する。
                //　@　@　@②　@取得した翌営業日を引数に、投信海外市場カレンダ.is休日()をコールする。
                //　@　@　@③　@is休日() = trueの場合、①@へ戻る
                //　@　@　@④　@is休日() = falseの場合、取得した翌営業日を返す。
                if (l_blnIsHoliday)
                {
                    while (l_blnIsHoliday)
                    {
                        l_deliveryDate =
                            new WEB3GentradeBizDate(l_deliveryDate).roll(1);
                        l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                            l_institution.getInstitutionCode(),
                            l_strProductCode,
                            l_deliveryDate);
                    }
                }

                //４-１-２）　@is休日() = falseの場合、取得した受渡日を返す。
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_deliveryDate;
                }
            }

            //５）  拡張投信銘柄.is外貨MMF = false の場合、取得した受渡日を返す。
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_deliveryDate;
            }
		}
		catch (NotFoundException l_ex)
		{
			log.error("拡張投信取引銘柄オブジェクトを取得するできない for："
					  + "this.getMutualFundTradedProduct() "
					  + "l_institution = " + l_institution.getInstitutionId()
					  + "l_strProductCode = " + l_strProductCode);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		return l_deliveryDate;
	}

	/**
	 * (get約定日)<BR>
	 * 約定日を返す。<BR>
	 * <BR>
	 * １）　@拡張投信取引銘柄オブジェクトを取得する。<BR>
	 * 　@this.get投信取引銘柄()をコールして、<BR>
	 * 拡張投信取引銘柄オブジェクトを取得する。<BR>
	 * 　@［get投信取引銘柄に渡すパラメタ］<BR>
	 * 　@　@証券会社： 引数.証券会社<BR>
	 * 　@　@銘柄コード： 引数.銘柄コード<BR>
	 * <BR>
	 * ２）　@約定日を取得する。<BR>
	 * 　@拡張投信取引銘柄.get約定日()をコールして、約定日を取得する。<BR>
	 * <BR>
	 * ３）　@取得した約定日を返す。<BR>
	 * <BR>
	 * @@param l_institution - 証券会社
	 * @@param l_strProductCode - 銘柄コード
	 * @@return Timestamp
	 * @@roseuid 40B44DE700DA
	 */
	public Timestamp getExecutedDate(
		Institution l_institution,
		String l_strProductCode) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getExecutedDate("
				+ "Institution l_institution,"
				+ "String l_strProductCode)";
		log.entering(STR_METHOD_NAME);

		if (l_institution == null)
		{
			log.debug(" __parameter_error__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		Timestamp l_executedDate = null;
		Date l_datExecutedDate = null;
		try
		{
			//１）　@拡張投信取引銘柄オブジェクトを取得する
			WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
				(WEB3MutualFundTradedProduct) this.getMutualFundTradedProduct(
					l_institution,
					l_strProductCode);

			//２）　@約定日を取得する
			l_datExecutedDate = l_web3MutualFundTradedProduct.getExecutedDate();
			l_executedDate = new Timestamp(l_datExecutedDate.getTime());
		}
		catch (NotFoundException l_ex)
		{
			log.error("拡張投信取引銘柄オブジェクトを取得するできない for: "
					  + "this.getMutualFundTradedProduct() "
					  + "l_institution = " + l_institution.getInstitutionCode()
					  + "l_strProductCode = " + l_strProductCode);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		finally
		{
			log.exiting(STR_METHOD_NAME);
		}

		//３）　@取得した約定日を返す
		return l_executedDate;
	}

    /**
     * （validate銘柄条件()）<BR>
     * シーケンス図「（投信）銘柄条件登録審査」参照<BR>
     * --------------------------------------------------<BR>
     * 銘柄条件の登録審査を行う。<BR>
     * <BR>
     * 1) 投信銘柄オブジェクトの取得<BR>
     * <BR>
     * 2) "買付開始日"のチェック<BR>
     * 　@引数.銘柄条件内容.get買付開始日()がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（買付開始日エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00335 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get買付開始日()＜投信銘柄オブジェクト.get設定日() - 1営業日<BR>
     * <BR>
     * 3) "買付終了日"のチェック <BR>
     * 　@(※1)買付終了日/解約乗換終了日チェックを参照<BR>
     * <BR>
     * 4) "解約乗換開始日"のチェック<BR>
     * 　@引数.銘柄条件内容.get解約乗換開始日()がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（解約／乗換開始日エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00337 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get解約乗換開始日()＜投信銘柄オブジェクト.get解約解禁日()<BR>
     * <BR>
     * 5) "解約乗換終了日"のチェック <BR>
     * 　@(※1)買付終了日/解約乗換終了日チェックを参照<BR>
     * <BR>
     * 6) "買取請求開始日"のチェック<BR>
     * 　@引数.銘柄条件内容.get買取請求開始日()がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（買取請求開始日エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00339 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get買取請求開始日()≦<BR>
     * 投信銘柄オブジェクト.get設定日()<BR>
     * <BR>
     * 7) "買取請求終了日"のチェック<BR>
     * 　@引数.銘柄条件内容.get買取請求終了日()がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（買取請求終了日エラー）の例外をスロー。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00340 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get買取請求終了日()＞<BR>
     * 投信銘柄オブジェクト.get償還日()<BR>
     * <BR>
     * <BR>
     * 8) 投信第2銘柄マスタDao.findRowByProductCode()をコールし、<BR>
     * 投信第2銘柄マスタRowオブジェクトを取得。<BR>
     * 　@[引数]<BR>
     * 　@　@　@引数.銘柄条件内容.get銘柄コード<BR>
     *  <BR>
     * -- 買付 <BR>
     *  <BR>
     * 9) 指定方法@（買付）のチェック <BR>
     *  9-1) 引数.銘柄条件内容.get指定方法@（買付）＝"口数"であり、 <BR>
     * 　@　@取得した投信第2銘柄マスタRowオブジェクト.get買付口数指定区分()＝<BR>
     *    "不可"の場合、 <BR>
     * 　@　@例外をスローする。 <BR>
     *  <BR>
     *  9-2) 引数.銘柄条件内容.get指定方法@（買付）＝"口数"であり、 <BR>
     * 　@引数.銘柄条件内容.get指定方法@（買付）()＝"口数"であり、<BR>
     *     かつ以下が全てnullの場合、例外をスローする。 <BR>
     * 　@　@・引数.銘柄条件内容.get最低口数（新規買付）の戻り値 <BR>
     * 　@　@・引数.銘柄条件内容.get単位口数（新規買付）の戻り値 <BR>
     * 　@　@・引数.銘柄条件内容.get最低口数（追加買付）の戻り値 <BR>
     * 　@　@・引数.銘柄条件内容.get単位口数（追加買付）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低口数（新規買付）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位口数（新規買付）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低口数（追加買付）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位口数（追加買付）の戻り値 <BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get最低口数（買付）の戻り値 <BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get単位口数（買付）の戻り値 <BR>
     *  <BR>
     *  9-3) 引数.銘柄条件内容.get指定方法@（買付）＝"金額"であり、 <BR>
     * 　@　@取得した投信第2銘柄マスタRowオブジェクト.get買付金額指定区分()＝<BR>
     *    "不可"の場合、<BR>
     * 　@　@例外をスローする。 <BR>
     *  <BR>
     *  9-4) 引数.銘柄条件内容.get指定方法@（買付）＝"金額"であり、 <BR>
     * 　@引数.銘柄条件内容.get指定方法@（買付）()＝"金額"であり、<BR>
     *     かつ以下が全てnullの場合、例外をスローする。 <BR>
     * 　@　@・引数.銘柄条件内容.get最低金額（新規買付）の戻り値 <BR>
     * 　@　@・引数.銘柄条件内容.get単位金額（新規買付）の戻り値 <BR>
     * 　@　@・引数.銘柄条件内容.get最低金額（追加買付）の戻り値 <BR>
     * 　@　@・引数.銘柄条件内容.get単位金額（追加買付）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低金額（新規買付）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位金額（新規買付）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低金額（追加買付）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位金額（追加買付）の戻り値 <BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get最低金額（買付）の戻り値 <BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get単位金額（買付）の戻り値 <BR>
     * <BR>
     * -- 新規買付<BR>
     * <BR>
     * 10) 最低口数（新規買付）のチェック<BR>
     * 　@引数.銘柄条件内容.get最低口数（新規買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（最低口数（新規買付）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00341 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get最低口数（新規買付）＜<BR>
     * 投信第2銘柄マスタRowオブジェクト.get最低口数（買付）()<BR>
     * <BR>
     * 11) 単位口数（新規買付）のチェック<BR>
     * 　@引数.銘柄条件内容.get単位口数（新規買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（単位口数（新規買付）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00342 <BR>
     * 　@[条件]<BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（買付）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（新規買付）÷<BR>
     * 投信第2銘柄マスタRowオブジェクト.get単位口数（買付）()<BR>
     * 　@　@の余りが0以外の場合<BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（買付）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（新規買付）が0以外の場合<BR>
     * <BR>
     * 12) 最低金額（新規買付）のチェック<BR>
     * 　@引数.銘柄条件内容.get最低金額（新規買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（最低金額（新規買付）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00343 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get最低金額（新規買付）＜<BR>
     * 投信第2銘柄マスタRowオブジェクト.get最低金額（買付）()<BR>
     * <BR>
     * 13) 単位金額（新規買付）のチェック<BR>
     * 　@引数.銘柄条件内容.get単位金額（新規買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（単位金額（新規買付）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00344 <BR>
     * 　@[条件]<BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（買付）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（新規買付）÷<BR>
     * 投信第2銘柄マスタRowオブジェクト.get単位金額（買付）()<BR>
     * 　@　@の余りが0以外の場合<BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（買付）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（新規買付）が0以外の場合<BR>
     * <BR>
     * -- 追加買付<BR>
     * <BR>
     * 14) 最低口数（追加買付）のチェック<BR>
     * 　@引数.銘柄条件内容.get最低口数（追加買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（最低口数（追加買付）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00345 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get最低口数（追加買付）＜<BR>
     * 投信第2銘柄マスタRowオブジェクト.get最低口数（買付）()<BR>
     * <BR>
     * 15) 単位口数（追加買付）のチェック<BR>
     * 　@引数.銘柄条件内容.get単位口数（追加買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（単位口数（追加買付）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00346 <BR>
     * 　@[条件]<BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（買付）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（追加買付）÷<BR>
     * 投信第2銘柄マスタRowオブジェクト.get単位口数（買付）()<BR>
     * 　@　@の余りが0以外の場合<BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（買付）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（追加買付）が0以外の場合<BR>
     * <BR>
     * 16) 最低金額（追加買付）のチェック<BR>
     * 　@引数.銘柄条件内容.get最低金額（追加買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（最低金額（追加買付）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00347 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get最低金額（追加買付）＜<BR>
     * 投信第2銘柄マスタRowオブジェクト.get最低金額（買付）()<BR>
     * <BR>
     * 17) 単位金額（追加買付）のチェック<BR>
     * 　@引数.銘柄条件内容.get単位金額（追加買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（単位金額（追加買付）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00348 <BR>
     * 　@[条件]<BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（買付）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（追加買付）÷<BR>
     * 投信第2銘柄マスタRowオブジェクト.get単位金額（買付）()<BR>
     * 　@　@の余りが0以外の場合<BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（買付）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（追加買付）が0以外の場合<BR>
     * <BR>
     * -- 解約<BR>
     * <BR>
     * 18) 指定方法@（解約）のチェック<BR>
     *  18-1) 引数.銘柄条件内容.get指定方法@（解約）＝"口数"であり、<BR>
     * 　@　@取得した投信第2銘柄マスタRowオブジェクト.get解約口数指定区分()＝<BR>
     *    "不可"の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * <BR>
     *  18-2) 引数.銘柄条件内容.get指定方法@（解約）＝"口数"であり、<BR>
     * 　@引数.銘柄条件内容.get指定方法@（解約）()＝"口数"であり、<BR>
     *     かつ以下が全てnullの場合、例外をスローする。<BR>
     * 　@　@・引数.銘柄条件内容.get最低口数（解約）の戻り値<BR>
     * 　@　@・引数.銘柄条件内容.get単位口数（解約）の戻り値<BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低口数（解約）の戻り値<BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位口数（解約）の戻り値<BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get最低口数（解約）の戻り値<BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get単位口数（解約）の戻り値<BR>
     * <BR>
     *  18-3) 引数.銘柄条件内容.get指定方法@（解約）＝"金額"であり、<BR>
     * 　@　@取得した投信第2銘柄マスタRowオブジェクト.get解約金額指定区分()＝<BR>
     *    "不可"の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * <BR>
     *  18-4) 引数.銘柄条件内容.get指定方法@（解約）＝"金額"であり、<BR>
     * 　@引数.銘柄条件内容.get指定方法@（解約）()＝"金額"であり、<BR>
     *   かつ以下が全てnullの場合、例外をスローする。<BR>
     * 　@　@・引数.銘柄条件内容.get最低金額（解約）の戻り値<BR>
     * 　@　@・引数.銘柄条件内容.get単位金額（解約）の戻り値<BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低金額（解約）の戻り値<BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位金額（解約）の戻り値<BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get最低金額（解約）の戻り値<BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get単位金額（解約）の戻り値<BR>
     * <BR>
     * 19) 最低口数（解約）のチェック<BR>
     * 　@引数.銘柄条件内容.get最低口数（解約）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（最低口数（解約）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00349 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get最低口数（解約）＜<BR>
     * 投信第2銘柄マスタRowオブジェクト.get最低口数（解約）()<BR>
     * <BR>
     * 20) 単位口数（解約）のチェック<BR>
     * 　@引数.銘柄条件内容.get単位口数（解約）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（単位口数（解約）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00350 <BR>
     * 　@[条件]<BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（解約）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（解約）÷<BR>
     * 投信第2銘柄マスタRowオブジェクト.get単位口数（解約）()<BR>
     * 　@　@の余りが0以外の場合<BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（解約）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（解約）が0以外の場合<BR>
     * <BR>
     * 21) 最低金額（解約）のチェック<BR>
     * 　@引数.銘柄条件内容.get最低金額（解約）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（最低金額（解約）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00351 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get最低金額（解約）＜<BR>
     * 投信第2銘柄マスタRowオブジェクト.get最低金額（解約）()<BR>
     * <BR>
     * 22) 単位金額（解約）のチェック<BR>
     * 　@引数.銘柄条件内容.get単位金額（解約）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（単位金額（解約）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00352 <BR>
     * 　@[条件]<BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（解約）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（解約）÷<BR>
     * 投信第2銘柄マスタRowオブジェクト.get単位金額（解約）()<BR>
     * 　@　@の余りが0以外の場合<BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（解約）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（解約）が0以外の場合<BR>
     * <BR>
     * -- 乗換<BR>
     * <BR>
     * 23) 指定方法@（乗換）のチェック<BR>
     *  23-1) 引数.銘柄条件内容.get指定方法@（乗換）＝"口数"であり、<BR>
     * 　@　@取得した投信第2銘柄マスタRowオブジェクト.get乗換口数指定区分()＝<BR>
     *    "不可"の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 
     *  23-2) 引数.銘柄条件内容.get指定方法@（乗換）＝"口数"であり、<BR>
     * 　@引数.銘柄条件内容.get指定方法@（乗換）()＝"口数"であり、<BR>
     *    かつ以下が全てnullの場合、例外をスローする。<BR>
     * 　@　@・引数.銘柄条件内容.get最低口数（乗換）の戻り値<BR>
     * 　@　@・引数.銘柄条件内容.get単位口数（乗換）の戻り値<BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低口数（乗換）の戻り値<BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位口数（乗換）の戻り値<BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get最低口数（乗換）の戻り値<BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get単位口数（乗換）の戻り値<BR>
     * <BR>
     *  23-4) 引数.銘柄条件内容.get指定方法@（乗換）＝"金額"であり、<BR>
     * 　@　@取得した投信第2銘柄マスタRowオブジェクト.get乗換金額指定区分()＝<BR>
     *    "不可"の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * <BR>
     *  23-4) 引数.銘柄条件内容.get指定方法@（乗換）＝"金額"であり、<BR>
     * 　@引数.銘柄条件内容.get指定方法@（乗換）()＝"金額"であり、<BR>
     *    かつ以下が全てnullの場合、例外をスローする。<BR>
     * 　@　@・引数.銘柄条件内容.get最低金額（乗換）の戻り値<BR>
     * 　@　@・引数.銘柄条件内容.get単位金額（乗換）の戻り値<BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低金額（乗換）の戻り値<BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位金額（乗換）の戻り値<BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get最低金額（乗換）の戻り値<BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get単位金額（乗換）の戻り値<BR>
     * <BR>
     * 24) 最低口数（乗換）のチェック<BR>
     * 　@引数.銘柄条件内容.get最低口数（乗換）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（最低口数（乗換）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00353 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get最低口数（乗換）＜<BR>
     * 投信第2銘柄マスタRowオブジェクト.get最低口数（乗換）()<BR>
     * <BR>
     * 25) 単位口数（乗換）のチェック<BR>
     * 　@引数.銘柄条件内容.get単位口数（乗換）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（単位口数（乗換）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00354 <BR>
     * 　@[条件]<BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（乗換）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（乗換）÷<BR>
     * 投信第2銘柄マスタRowオブジェクト.get単位口数（乗換）()<BR>
     * 　@　@の余りが0以外の場合<BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（乗換）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（乗換）が0以外の場合<BR>
     * <BR>
     * 26) 最低金額（乗換）のチェック<BR>
     * 　@引数.銘柄条件内容.get最低金額（乗換）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（最低金額（乗換）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00355 <BR>
     * 　@[条件]<BR>
     * 　@　@　@引数.銘柄条件内容.get最低金額（乗換）＜<BR>
     * 投信第2銘柄マスタRowオブジェクト.get最低金額（乗換）()<BR>
     * <BR>
     * 27) 単位金額（乗換）のチェック<BR>
     * 　@引数.銘柄条件内容.get単位金額（乗換）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、<BR>
     * 　@（単位金額（乗換）エラー）の例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00356 <BR>
     * 　@[条件]<BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（乗換）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（乗換）÷<BR>
     * 投信第2銘柄マスタRowオブジェクト.get単位金額（乗換）()<BR>
     * 　@　@の余りが0以外の場合<BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（乗換）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（乗換）が0以外の場合<BR>
     * <BR>
     * -- 募集 <BR>
     * <BR>
     * 28) 募集開始日のチェック <BR>
     * 　@引数.銘柄条件内容.get募集開始日()がnull以外であり、<BR>
     *  かつ以下の条件が満たされる場合、 <BR>
     * 　@（募集開始日エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@引数.銘柄条件内容.get募集開始日()＜ <BR>
     *       取得した投信銘柄オブジェクト.get募集開始日(SONAR)() <BR>
     * <BR>
     *      *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02272 <BR>
     * <BR>
     * 29) 募集終了日のチェック <BR>
     * 　@引数.銘柄条件内容.get募集終了日()がnull以外であり、<BR>
     *      かつ以下の条件が満たされる場合、 <BR>
     * 　@（募集終了日エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@引数.銘柄条件内容.get募集終了日()＞ <BR>
     *      取得した投信銘柄オブジェクト.get募集終了日(SONAR)() <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02273 <BR>
     * <BR>
     * 30) 指定方法@（募集）のチェック <BR>
     *  30-1) 引数.銘柄条件内容.get指定方法@（募集）＝"口数"であり、 <BR>
     * 　@　@かつ取得した投信第2銘柄マスタRowオブジェクト. <BR>
     *      get募集口数指定区分()＝"不可"の場合、 <BR>
     * 　@　@例外をスローする。 <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02281 <BR>
     * <BR>
     *  30-2) 引数.銘柄条件内容.get指定方法@（募集）＝"口数"であり、<BR>
     *      かつ以下が全てnullの場合、例外をスローする。 <BR>
     * 　@　@・引数.銘柄条件内容.get最低口数（募集）の戻り値 <BR>
     * 　@　@・引数.銘柄条件内容.get単位口数（募集）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低口数（募集）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位口数（募集）の戻り値 <BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get最低口数（募集）の戻り値 <BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get単位口数（募集）の戻り値 <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02282 <BR>
     * <BR>
     *  30-3) 引数.銘柄条件内容.get指定方法@（募集）＝"金額"であり、 <BR>
     * 　@　@かつ取得した投信第2銘柄マスタRowオブジェクト. <BR>
     *      get募集金額指定区分()＝"不可"の場合、 <BR>
     * 　@　@例外をスローする。 <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02283 <BR>
     * <BR>
     *  30-4) 引数.銘柄条件内容.get指定方法@（募集）＝"金額"であり、<BR>
     *      かつ以下が全てnullの場合、例外をスローする。 <BR>
     * 　@　@・引数.銘柄条件内容.get最低金額（募集）の戻り値 <BR>
     * 　@　@・引数.銘柄条件内容.get単位金額（募集）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get最低金額（募集）の戻り値 <BR>
     * 　@　@・取得した投信銘柄オブジェクト.get単位金額（募集）の戻り値 <BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get最低金額（募集）の戻り値 <BR>
     * 　@　@・取得した投信第2銘柄マスタRowオブジェクト.get単位金額（募集）の戻り値 <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02284 <BR>
     * <BR>
     * 31) 最低口数（募集）のチェック <BR>
     * 　@引数.銘柄条件内容.get最低口数（募集）がnull以外であり、<BR>
     *      かつ以下の条件が満たされる場合、 <BR>
     * 　@（最低口数（募集）エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@引数.銘柄条件内容.get最低口数（募集）＜ <BR>
     *      投信第2銘柄マスタRowオブジェクト.get最低口数（募集）() <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02274 <BR>
     * <BR>
     * 32) 単位口数（募集）のチェック <BR>
     * 　@引数.銘柄条件内容.get単位口数（募集）がnull以外であり、<BR>
     *      かつ以下の条件が満たされる場合、 <BR>
     * 　@（単位口数（募集）エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（募集）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（募集）÷ <BR>
     *      投信第2銘柄マスタRowオブジェクト.get単位口数（募集）() <BR>
     * 　@　@の余りが0以外の場合 <BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（募集）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位口数（募集）が0以外の場合<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02275 <BR>
     * <BR>
     * 33) 最低金額（募集）のチェック <BR>
     * 　@引数.銘柄条件内容.get最低金額（募集）がnull以外であり、<BR>
     *      かつ以下の条件が満たされる場合、 <BR>
     * 　@（最低金額（募集）エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@引数.銘柄条件内容.get最低金額（募集）＜ <BR>
     *          投信第2銘柄マスタRowオブジェクト.get最低金額（募集）() <BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02276 <BR>
     * <BR>
     * 34) 単位金額（募集）のチェック <BR>
     * 　@引数.銘柄条件内容.get単位金額（募集）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、 <BR>
     * 　@（単位金額（募集）エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（募集）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（募集）÷ <BR>
     *          投信第2銘柄マスタRowオブジェクト.get単位金額（募集）() <BR>
     * 　@　@の余りが0以外の場合 <BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（募集）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get単位金額（募集）が0以外の場合<BR>
     * <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02277 <BR>
     * <BR> 
     * 35) 外貨最低金額（新規買付）のチェック <BR>
     * 　@引数.銘柄条件内容.get外貨最低金額（新規買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、 <BR>
     * 　@（外貨最低金額（新規買付）エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@引数.銘柄条件内容.get外貨最低金額（新規買付）＜<BR>
     *          投信第2銘柄マスタRowオブジェクト.get外貨最低金額（買付）() <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02745 <BR>
     * <BR>
     * 36) 外貨単位金額（新規買付）のチェック <BR>
     * 　@引数.銘柄条件内容.get外貨単位金額（新規買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、 <BR>
     * 　@（外貨単位金額（新規買付）エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get外貨単位金額（新規買付）÷<BR>
     *          投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() <BR>
     * 　@　@の余りが0以外の場合 <BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get外貨単位金額（新規買付）が0以外の場合<BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02746 <BR>
     * <BR>
     * 37) 外貨最低金額（追加買付）のチェック <BR>
     * 　@引数.銘柄条件内容.get外貨最低金額（追加買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、 <BR>
     * 　@（外貨最低金額（追加買付）エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@引数.銘柄条件内容.get外貨最低金額（追加買付）＜<BR>
     *          投信第2銘柄マスタRowオブジェクト.get外貨最低金額（買付）() <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02747 <BR>
     * <BR>
     * 38) 外貨単位金額（追加買付）のチェック <BR>
     * 　@引数.銘柄条件内容.get外貨単位金額（追加買付）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、 <BR>
     * 　@（外貨単位金額（追加買付）エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() が0以外かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get外貨単位金額（追加買付）÷<BR>
     *          投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() <BR>
     * 　@　@の余りが0以外の場合 <BR>
     * <BR>
     * 　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() が0かつ、<BR>
     * 　@　@　@引数.銘柄条件内容.get外貨単位金額（追加買付）が0以外の場合<BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02748 <BR>
     * <BR>
     * 39) 外貨最低金額（解約）のチェック <BR>
     * 　@引数.銘柄条件内容.get外貨最低金額（解約）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、 <BR>
     * 　@（外貨最低金額（解約）エラー）の例外をスローする。 <BR>
     * 　@[条件] <BR>
     * 　@　@　@引数.銘柄条件内容.get外貨最低金額（解約）＜<BR>
     *          投信第2銘柄マスタRowオブジェクト.get外貨最低金額（解約）() <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02749 <BR>
     * <BR>
     * 40) 外貨単位金額（解約）のチェック <BR>
     * 　@引数.銘柄条件内容.get外貨単位金額（解約）がnull以外であり、<BR>
     * かつ以下の条件が満たされる場合、 <BR>
     * 　@（外貨単位金額（解約）エラー）の例外をスローする。 <BR>
     *　@[条件] <BR>
     *　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（解約）() が0以外かつ、<BR>
     *　@　@　@引数.銘柄条件内容.get外貨単位金額（解約）÷<BR>
     *          投信第2銘柄マスタRowオブジェクト.get外貨単位金額（解約）() <BR>
     *　@　@の余りが0以外の場合<BR>
     * <BR>
     *　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（解約）() が0かつ、<BR>
     *　@　@　@引数.銘柄条件内容.get外貨単位金額（解約）が0以外の場合<BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02750 <BR>
     * <BR>
     * - 注文受付締切時間のチェック <BR>
     * <BR>
     * シーケンス図「（投信）銘柄条件登録審査」の注文締切時間のチェックを参照 <BR>
     * ※アイテム定義の記述は削除しました<BR>
     * <BR>
     * <BR>
     * (※1)買付終了日/解約乗換終了日チェック <BR>
     * 　@○買付終了日： <BR>
     * 　@　@　@　@買付終了日!=nullかつ、以下の条件を満たす場合、<BR>
     * 　@　@　@　@　@　@（買付終了日エラー）の例外をスローする。 <BR>
     * <BR>
     * 　@　@　@　@　@　@買付終了日 > 償還日 - ( 約定日移動日数 + 1 )営業日 <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00336 <BR>
     * <BR>
     * 　@○解約乗換終了日： <BR>
     * 　@　@　@　@解約乗換終了日!=nullかつ、以下の条件を満たす場合、<BR>
     * 　@　@　@　@　@　@（解約／乗換終了日エラー）の例外をスローする。 <BR>
     * <BR>
     * 　@　@　@　@　@解約乗換終了日 > 償還日 - ( 約定日移動日数 + 1 )営業日 <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00338 <BR>
     * <BR>
     * <BR>
     * 　@　@※ 買付終了日       ： 引数.銘柄条件内容.get買付終了日() <BR>
     * 　@　@ ※ 解約乗換終了日 ： 引数.銘柄条件内容.get解約乗換終了日() <BR>
     * 　@　@※ 償還日             ： 投信銘柄オブジェクト.get償還日() <BR>
     * 　@　@※ 約定日移動日数 ： this.get投信取引銘柄 (証券会社,銘柄コード).get約定日移動日数() <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@[get投信取引銘柄の引数] <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@証券会社：引数.証券会社 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@銘柄コード：引数.銘柄条件内容.get銘柄コード <BR>
     * @@param l_institution - 証券会社
     * @@param l_productCondSpec - 銘柄条件内容
     * @@throws WEB3BaseException
     * @@roseuid 40E9357A0369
     */
    public void validateProductCond(
        Institution l_institution,
        WEB3MutualFundProductCondSpec l_productCondSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
              "validateProductCond(" 
              + "Institution l_institution," 
              + "WEB3MutualFundProductCondSpec l_productCondSpec)";
            
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_productCondSpec == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //1) 投信銘柄オブジェクトの取得
            MutualFundProduct l_mutualFundProduct =
                (MutualFundProduct) this.getMutualFundProduct(
                    l_institution,
                    l_productCondSpec.getMutualProductCode());
            MutualFundProductParams l_mutualFundProductParams =
                new MutualFundProductParams((MutualFundProductRow) l_mutualFundProduct.getDataSourceObject());

            //償還日 ： 投信銘柄オブジェクト.get償還日()
            Timestamp l_tmsRedemptionDate = l_mutualFundProductParams.getRedemptionDate();

            //約定日移動日数：this.get投信取引銘柄 (証券会社,銘柄コード).get約定日移動日数()
            MutualFundTradedProduct l_mutualFundTradedProduct =
                this.getMutualFundTradedProduct(l_institution,
                    l_productCondSpec.getMutualProductCode());
            MutualFundTradedProductParams l_mutualFundTradedProductParams =
                new MutualFundTradedProductParams(
                    (MutualFundTradedProductRow)l_mutualFundTradedProduct.getDataSourceObject());
            int l_intExecDateShiftdays = l_mutualFundTradedProductParams.getExecDateShiftdays();

            //償還日 - ( 約定日移動日数 + 1 )営業日
            Date l_datBizDate = new WEB3GentradeBizDate(l_tmsRedemptionDate).roll(-(l_intExecDateShiftdays + 1));

            //2) "買付開始日"のチェック
            Timestamp l_tmsSettingDate = l_mutualFundProductParams.getSettingDate();
            if (l_productCondSpec.getBuyStartDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getBuyStartDate(),
                    new WEB3GentradeBizDate(l_tmsSettingDate).roll(-1))
                    < 0)
            {
                log.debug("買付開始日エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00335,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "買付開始日エラー");
            }

            //3) "買付終了日"のチェック
            if (l_productCondSpec.getBuyEndDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getBuyEndDate(),
                    l_datBizDate)
                    > 0)
            {
                log.debug("買付終了日エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00336,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "買付終了日エラー");
            }

            //4) "解約乗換開始日"のチェック
            if (l_productCondSpec.getSellSwitchingStartDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getSellSwitchingStartDate(),
                    l_mutualFundProductParams.getSellBanDate())
                    < 0)
            {
                log.debug("解約／乗換開始日エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00337,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "解約／乗換開始日エラー");
            }

            //5) "解約乗換終了日"のチェック
            if (l_productCondSpec.getSellSwitchingEndDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getSellSwitchingEndDate(),
                    l_datBizDate)
                    > 0)
            {
                log.debug("解約／乗換終了日エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00338,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "解約／乗換終了日エラー");
            }

            //6) "買取請求開始日"のチェック
            if (l_productCondSpec.getBuyClaimStartDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getBuyClaimStartDate(),
                    l_mutualFundProductParams.getSettingDate())
                    <= 0)
            {
                log.debug("買取請求開始日エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00339,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "買取請求開始日エラー");
            }

            //7) "買取請求終了日"のチェック
            if (l_productCondSpec.getBuyClaimEndDate() != null
                && WEB3DateUtility.compareToDay(
                    l_productCondSpec.getBuyClaimEndDate(),
                    l_mutualFundProductParams.getRedemptionDate())
                    > 0)
            {
                log.debug("買取請求終了日エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00340,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "買取請求終了日エラー");
            }

            //8) MutualFund2ndProductSonarRowを取得    
            MutualFund2ndProductSonarRow l_mutualFundProductSonar2Row =
                MutualFund2ndProductSonarDao.findRowByProductCodeInstitutionCode(
                    l_productCondSpec.getMutualProductCode(),
                    l_institution.getInstitutionCode());

            //9) 指定方法@（買付）のチェック
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getBuySelectable()) 
                && WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(l_mutualFundProductSonar2Row.getBuyQtySpecDiv()))
            {
                log.debug("指定方法@（買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（買付）エラー");             
            }
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getBuySelectable()) 
                      && l_productCondSpec.getNewBuyMinQty() == null
                      && l_productCondSpec.getNewBuyUnitQty() == null 
                      && l_productCondSpec.getAddBuyMinQty() == null
                      && l_productCondSpec.getAddBuyUnitQty() == null
                      && l_mutualFundProductParams.getNewBuyMinQtyIsNull()
                      && l_mutualFundProductParams.getNewBuyUnitQtyIsNull()
                      && l_mutualFundProductParams.getAddBuyMinQtyIsNull()
                      && l_mutualFundProductParams.getAddBuyUnitQtyIsNull()
                      && l_mutualFundProductSonar2Row.getBuyMinQtyIsNull()
                      && l_mutualFundProductSonar2Row.getBuyUnitQtyIsNull())
            {
                log.debug("指定方法@（買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（買付）エラー"); 
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getBuySelectable()) 
                && WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(l_mutualFundProductSonar2Row.getBuyAmtSpecDiv()))
            {
                log.debug("指定方法@（買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（買付）エラー");             
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getBuySelectable()) 
                      && l_productCondSpec.getNewBuyMinAmt() == null
                      && l_productCondSpec.getNewBuyUnitAmt() == null 
                      && l_productCondSpec.getAddBuyMinAmt() == null
                      && l_productCondSpec.getAddBuyUnitAmt() == null
                      && l_mutualFundProductParams.getNewBuyMinAmtIsNull()
                      && l_mutualFundProductParams.getNewBuyUnitAmtIsNull()
                      && l_mutualFundProductParams.getAddBuyMinAmtIsNull()
                      && l_mutualFundProductParams.getAddBuyUnitAmtIsNull()
                      && l_mutualFundProductSonar2Row.getBuyMinAmtIsNull()
                      && l_mutualFundProductSonar2Row.getBuyUnitAmtIsNull())
            {
                log.debug("指定方法@（買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（買付）エラー"); 
            }           

            // 10) 最低口数（新規買付）のチェック
            if (l_productCondSpec.getNewBuyMinQty() != null
                &&(Integer.parseInt(l_productCondSpec.getNewBuyMinQty()))
                    < l_mutualFundProductSonar2Row.getBuyMinQty())
            {
                log.debug("最低口数（新規買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低口数（新規買付）エラー");
            }

            // 11) 単位口数（新規買付）のチェック
            //　@引数.銘柄条件内容.get単位口数（新規買付）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位口数（新規買付）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（買付）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（新規買付）÷投信第2銘柄マスタRowオブジェクト.get単位口数（買付）()
            //　@　@の余りが0以外の場合
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（買付）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（新規買付）が0以外の場合
            if (l_productCondSpec.getNewBuyUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getBuyUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getNewBuyUnitQty())
                    % l_mutualFundProductSonar2Row.getBuyUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getBuyUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getNewBuyUnitQty()) != 0)))
            {
                log.debug("単位口数（新規買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00342,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位口数（新規買付）エラー");
            }

            // 12) 最低金額（新規買付）のチェック    
            if (l_productCondSpec.getNewBuyMinAmt() != null
                && Integer.parseInt(l_productCondSpec.getNewBuyMinAmt())
                    < l_mutualFundProductSonar2Row.getBuyMinAmt())
            {
                log.debug("最低金額（新規買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00343,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低金額（新規買付）エラー");
            }

            // 13) 単位金額（新規買付）のチェック
            //　@引数.銘柄条件内容.get単位金額（新規買付）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位金額（新規買付）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（買付）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（新規買付）÷投信第2銘柄マスタRowオブジェクト.get単位金額（買付）()
            //　@　@の余りが0以外の場合
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（買付）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（新規買付）が0以外の場合
            if (l_productCondSpec.getNewBuyUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getBuyUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getNewBuyUnitAmt())
                    % l_mutualFundProductSonar2Row.getBuyUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getBuyUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getNewBuyUnitAmt()) != 0)))
            {
                log.debug("単位金額（新規買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00344,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位金額（新規買付）エラー");
            }

            // 14) 最低口数（追加買付）のチェック   
            if (l_productCondSpec.getAddBuyMinQty() != null
                && Integer.parseInt(l_productCondSpec.getAddBuyMinQty())
                    < l_mutualFundProductSonar2Row.getBuyMinQty())
            {
                log.debug("最低口数（追加買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00345,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低口数（追加買付）エラー");
            }

            // 15) 単位口数（追加買付）のチェック
            //　@引数.銘柄条件内容.get単位口数（追加買付）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位口数（追加買付）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（買付）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（追加買付）÷投信第2銘柄マスタRowオブジェクト.get単位口数（買付）()
            //　@　@の余りが0以外の場合
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（買付）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（追加買付）が0以外の場合
            if (l_productCondSpec.getAddBuyUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getBuyUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getAddBuyUnitQty())
                    % l_mutualFundProductSonar2Row.getBuyUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getBuyUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getAddBuyUnitQty()) != 0)))
            {
                log.debug("単位口数（追加買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00346,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位口数（追加買付）エラー");
            }

            // 16 最低金額（追加買付）のチェック  
            if (l_productCondSpec.getAddBuyMinAmt() != null
                && Integer.parseInt(l_productCondSpec.getAddBuyMinAmt())
                    < l_mutualFundProductSonar2Row.getBuyMinAmt())
            {
                log.debug("最低金額（追加買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00347,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低金額（追加買付）エラー");
            }

            // 17) 単位金額（追加買付）のチェック
            //　@引数.銘柄条件内容.get単位金額（追加買付）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位金額（追加買付）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（買付）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（追加買付）÷投信第2銘柄マスタRowオブジェクト.get単位金額（買付）()
            //　@　@の余りが0以外の場合
            //
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（買付）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（追加買付）が0以外の場合
            if (l_productCondSpec.getAddBuyUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getBuyUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getAddBuyUnitAmt())
                    % l_mutualFundProductSonar2Row.getBuyUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getBuyUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getAddBuyUnitAmt()) != 0)))
            {
                log.debug("単位金額（追加買付）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00348,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位金額（追加買付）エラー");
            }

            //18) 指定方法@（解約）のチェック
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getSellSelectable()) 
                && WEB3SellPossibleDivDef.NOT_SELL.equals(l_mutualFundProductSonar2Row.getSellQtySpecDiv()))
            {
                log.debug("指定方法@（解約）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01232,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（解約）エラー");             
            }
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getSellSelectable()) 
                      && l_productCondSpec.getSellMinQty() == null
                      && l_productCondSpec.getSellUnitQty() == null 
                      && l_mutualFundProductParams.getSellMinQtyIsNull()
                      && l_mutualFundProductParams.getSellUnitQtyIsNull()
                      && l_mutualFundProductSonar2Row.getSellMinQtyIsNull()
                      && l_mutualFundProductSonar2Row.getSellUnitQtyIsNull())
            {
                log.debug("指定方法@（解約）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01232,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（解約）エラー");     
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getSellSelectable()) 
                && WEB3SellPossibleDivDef.NOT_SELL.equals(l_mutualFundProductSonar2Row.getSellAmtSpecDiv()))
            {
                log.debug("指定方法@（解約）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01232,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（解約）エラー");             
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getSellSelectable()) 
                      && l_productCondSpec.getSellMinAmt() == null
                      && l_productCondSpec.getSellUnitAmt() == null 
                      && l_mutualFundProductParams.getSellMinAmtIsNull()
                      && l_mutualFundProductParams.getSellUnitAmtIsNull()
                      && l_mutualFundProductSonar2Row.getSellMinAmtIsNull()
                      && l_mutualFundProductSonar2Row.getSellUnitAmtIsNull())
            {
                log.debug("指定方法@（解約）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01232,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（解約）エラー"); 
            }           

            // 19) 最低口数（解約）のチェック
            if (l_productCondSpec.getSellMinQty() != null
                && Integer.parseInt(l_productCondSpec.getSellMinQty())
                    < l_mutualFundProductSonar2Row.getSellMinQty())
            {
                log.debug("最低口数（解約）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00349,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低口数（解約）エラー");
            }

            // 20) 単位口数（解約）のチェック
            //　@引数.銘柄条件内容.get単位口数（解約）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位口数（解約）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（解約）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（解約）÷投信第2銘柄マスタRowオブジェクト.get単位口数（解約）()
            //　@　@の余りが0以外の場合
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（解約）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（解約）が0以外の場合
            if (l_productCondSpec.getSellUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getSellUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getSellUnitQty())
                    % l_mutualFundProductSonar2Row.getSellUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getSellUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getSellUnitQty()) != 0)))
            {
                log.debug("単位口数（解約）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00350,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位口数（解約）エラー");
            }

            // 21) 最低金額（解約）のチェック 
            if (l_productCondSpec.getSellMinAmt() != null
                && Integer.parseInt(l_productCondSpec.getSellMinAmt())
                    < l_mutualFundProductSonar2Row.getSellMinAmt())
            {
                log.debug("最低金額（解約）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00351,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低金額（解約）エラー");
            }

            // 22) 単位金額（解約）のチェック
            //　@引数.銘柄条件内容.get単位金額（解約）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位金額（解約）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（解約）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（解約）÷投信第2銘柄マスタRowオブジェクト.get単位金額（解約）()
            //　@　@の余りが0以外の場合
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（解約）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（解約）が0以外の場合
            if (l_productCondSpec.getSellUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getSellUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getSellUnitAmt())
                    % l_mutualFundProductSonar2Row.getSellUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getSellUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getSellUnitAmt()) != 0)))
            {
                log.debug("単位金額（解約）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00352,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位金額（解約）エラー");
            }

            //23) 指定方法@（乗換）のチェック
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getSwitchingSelectable()) 
                && WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(l_mutualFundProductSonar2Row.getSwtQtySpecDiv()))
            {
                log.debug("指定方法@（乗換）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01233,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（乗換）エラー");             
            }
            else if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getSwitchingSelectable()) 
                      && l_productCondSpec.getSwitchingMinQty() == null
                      && l_productCondSpec.getSwitchingUnitQty() == null 
                      && l_mutualFundProductParams.getSwtMinQtyIsNull()
                      && l_mutualFundProductParams.getSwtUnitQtyIsNull()
                      && l_mutualFundProductSonar2Row.getSwtMinQtyIsNull()
                      && l_mutualFundProductSonar2Row.getSwtUnitQtyIsNull())
            {
                log.debug("指定方法@（乗換）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01233,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（乗換）エラー");     
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getSwitchingSelectable()) 
                && WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(l_mutualFundProductSonar2Row.getSwtAmtSpecDiv()))
            {
                log.debug("指定方法@（乗換）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01233,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（乗換）エラー");         
            }
            else if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getSwitchingSelectable()) 
                      && l_productCondSpec.getSwitchingMinAmt() == null
                      && l_productCondSpec.getSwitchingUnitAmt() == null 
                      && l_mutualFundProductParams.getSwtMinAmtIsNull()
                      && l_mutualFundProductParams.getSwtUnitAmtIsNull()
                      && l_mutualFundProductSonar2Row.getSwtMinAmtIsNull()
                      && l_mutualFundProductSonar2Row.getSwtUnitAmtIsNull())
            {
                log.debug("指定方法@（乗換）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01233,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（乗換）エラー");
            }           

            // 24) 最低口数（乗換）のチェック
            if (l_productCondSpec.getSwitchingMinQty() != null
                && Integer.parseInt(l_productCondSpec.getSwitchingMinQty())
                    < l_mutualFundProductSonar2Row.getSwtMinQty())
            {
                log.debug("最低口数（乗換）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00353,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低口数（乗換）エラー");
            }

            // 25) 単位口数（乗換）のチェック
            //　@引数.銘柄条件内容.get単位口数（乗換）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位口数（乗換）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（乗換）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（乗換）÷投信第2銘柄マスタRowオブジェクト.get単位口数（乗換）()
            //　@　@の余りが0以外の場合
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（乗換）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（乗換）が0以外の場合
            if (l_productCondSpec.getSwitchingUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getSwtUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getSwitchingUnitQty())
                    % l_mutualFundProductSonar2Row.getSwtUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getSwtUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getSwitchingUnitQty()) != 0)))
            {
                log.debug("単位口数（乗換）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00354,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位口数（乗換）エラー");
            }

            // 26) 最低金額（乗換）のチェック 
            if (l_productCondSpec.getSwitchingMinAmt() != null
                && Integer.parseInt(l_productCondSpec.getSwitchingMinAmt())
                    < l_mutualFundProductSonar2Row.getSwtMinAmt())
            {
                log.debug("最低金額（乗換）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00355,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低金額（乗換）エラー");
            }

            // 27) 単位金額（乗換）のチェック
            //　@引数.銘柄条件内容.get単位金額（乗換）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位金額（乗換）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（乗換）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（乗換）÷投信第2銘柄マスタRowオブジェクト.get単位金額（乗換）()
            //　@　@の余りが0以外の場合
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（乗換）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（乗換）が0以外の場合
            if (l_productCondSpec.getSwitchingUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getSwtUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getSwitchingUnitAmt())
                    % l_mutualFundProductSonar2Row.getSwtUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getSwtUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getSwitchingUnitAmt()) != 0)))
            {
                log.debug("単位金額（乗換）エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00356,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位金額（乗換）エラー");
            }
            
            //1.3 募集関連項目のチェック
            //（詳細は、validate銘柄条件()のアイテム定義を参照）
            
            //-- 募集 
            //28) 募集開始日のチェック 
            //  引数.銘柄条件内容.get募集開始日()がnull以外であり、かつ以下の条件が満たされる場合、 
            //（募集開始日エラー）の例外をスローする。           
            //  [条件] 
            //　@　@引数.銘柄条件内容.get募集開始日()＜
            //          取得した投信銘柄オブジェクト.get募集開始日（SONAR）() 
            if (l_productCondSpec.getApplyAbleStartDate() != null &&
                WEB3DateUtility.compareToDay(l_productCondSpec.getApplyAbleStartDate(), 
                   l_mutualFundProductParams.getRecruitStartDateSonar()) < 0)                
            {
                log.debug("募集開始日エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02272,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "募集開始日エラー。");
            }
            
            //29) 募集終了日のチェック 
            //　@引数.銘柄条件内容.get募集終了日()がnull以外であり、かつ以下の条件が満たされる場合、 
            //（募集終了日エラー）の例外をスローする。 
            // [条件] 
            //  引数.銘柄条件内容.get募集終了日()＞
            //        取得した投信銘柄オブジェクト.get募集終了日（SONAR）()の前営業日 
            
            WEB3GentradeBizDate l_gentradeBizDate = null;
            Timestamp l_tsRecruitEndDate = null;
            if(l_mutualFundProductParams.getRecruitEndDateSonar() != null)
            { 
                 l_gentradeBizDate = 
                     new WEB3GentradeBizDate(l_mutualFundProductParams.getRecruitEndDateSonar());
                 l_tsRecruitEndDate= l_gentradeBizDate.roll(-1);
            }
            
            if (l_productCondSpec.getApplyAbleEndDate() != null &&
                WEB3DateUtility.compareToDay(l_productCondSpec.getApplyAbleEndDate(), 
                    l_tsRecruitEndDate) > 0)                
            {
                log.debug("募集終了日エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02273,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "募集終了日エラー。");
            }
            
            //30) 指定方法@（募集）のチェック 
            //30-1) 引数.銘柄条件内容.get指定方法@（募集）＝"口数"であり、 
            //　@かつ取得した投信第2銘柄マスタRowオブジェクト.get募集口数指定区分()＝"不可"の場合、 
            //　@例外をスローする。 
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getApplySelectable()) && 
                WEB3MFRecruitPossibleDivDef.NOT_RECRUIT.equals(
                    l_mutualFundProductSonar2Row.getRecruitQtySpecDiv()))
            {
                log.debug("指定方法@（募集）エラー(”口数”を指定不可)。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02281,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "指定方法@（募集）エラー(”口数”を指定不可)。");
            }
            //30-2) 引数.銘柄条件内容.get指定方法@（募集）＝"口数"であり、
            //      かつ以下が全てnullの場合、例外をスローする。 
            //　@・引数.銘柄条件内容.get最低口数（募集）の戻り値 
            //　@・引数.銘柄条件内容.get単位口数（募集）の戻り値 
            //　@・取得した投信銘柄オブジェクト.get最低口数（募集）の戻り値 
            //　@・取得した投信銘柄オブジェクト.get単位口数（募集）の戻り値 
            //　@・取得した投信第2銘柄マスタRowオブジェクト.get最低口数（募集）の戻り値 
            //　@・取得した投信第2銘柄マスタRowオブジェクト.get単位口数（募集）の戻り値 
            if (WEB3DesignateMethodDef.NUMBER.equals(l_productCondSpec.getApplySelectable()) && 
                l_productCondSpec.getApplyMinQty() == null && 
                l_productCondSpec.getApplyUnitQty() == null && 
                l_mutualFundProductParams.getRecruitMinQtyIsNull() && 
                l_mutualFundProductParams.getRecruitUnitQtyIsNull() && 
                l_mutualFundProductSonar2Row.getRecruitMinQtyIsNull()&&
                l_mutualFundProductSonar2Row.getRecruitUnitQtyIsNull())
            {
                log.debug("指定方法@（募集）エラー（”口数”を指定の場合、" +
                        "最低口数と単位口数が取得できません。）");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02282,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（募集）エラー（”口数”を指定の場合、" +
                    "最低口数と単位口数が取得できません。）");
            }
            
            //30-3) 引数.銘柄条件内容.get指定方法@（募集）＝"金額"であり、 
            //　@かつ取得した投信第2銘柄マスタRowオブジェクト.get募集金額指定区分()＝"不可"の場合、 
            //　@例外をスローする。 
            if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getApplySelectable()) &&
                WEB3SpecDivDef.NOT_POSSIBLE.equals(
                    l_mutualFundProductSonar2Row.getRecruitAmtSpecDiv()))
            {
                log.debug("指定方法@（募集）エラー(”金額”を指定不可)。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02283,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（募集）エラー(”金額”を指定不可)。");
            }
            
            // 30-4) 引数.銘柄条件内容.get指定方法@（募集）＝"金額"であり、
            //        かつ以下が全てnullの場合、例外をスローする。 
            //　@・引数.銘柄条件内容.get最低金額（募集）の戻り値 
            //　@・引数.銘柄条件内容.get単位金額（募集）の戻り値 
            //　@・取得した投信銘柄オブジェクト.get最低金額（募集）の戻り値 
            //　@・取得した投信銘柄オブジェクト.get単位金額（募集）の戻り値 
            //　@・取得した投信第2銘柄マスタRowオブジェクト.get最低金額（募集）の戻り値 
            //　@・取得した投信第2銘柄マスタRowオブジェクト.get単位金額（募集）の戻り値 
            if (WEB3DesignateMethodDef.AMOUNT.equals(l_productCondSpec.getApplySelectable()) && 
                l_productCondSpec.getApplyMinAmt() == null && 
                l_productCondSpec.getApplyUnitAmt() == null && 
                l_mutualFundProductParams.getRecruitMinAmtIsNull() && 
                l_mutualFundProductParams.getRecruitUnitAmtIsNull() && 
                l_mutualFundProductSonar2Row.getRecruitMinAmtIsNull()&&
                l_mutualFundProductSonar2Row.getRecruitUnitAmtIsNull())
            {
                log.debug("指定方法@（募集）エラー（”金額”を指定の場合、" +
                        "最低金額と単位金額が取得できません。）");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02284,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定方法@（募集）エラー（”金額”を指定の場合、" +
                    "最低金額と単位金額が取得できません。）");
            }

            //31) 最低口数（募集）のチェック 
            //引数.銘柄条件内容.get最低口数（募集）がnull以外であり、かつ以下の条件が満たされる場合、 
            //（最低口数（募集）エラー）の例外をスローする。 
            //　@[条件] 
            //　@引数.銘柄条件内容.get最低口数（募集）＜投信第2銘柄マスタRowオブジェクト.get最低口数（募集）() 
            if (l_productCondSpec.getApplyMinQty() != null &&
                (Integer.parseInt(l_productCondSpec.getApplyMinQty()) < 
                    l_mutualFundProductSonar2Row.getRecruitMinQty()))
            {
                log.debug("最低口数（募集）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02274,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低口数（募集）エラー。");
            }
            
            //32) 単位口数（募集）のチェック 
            //　@引数.銘柄条件内容.get単位口数（募集）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位口数（募集）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（募集）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（募集）÷投信第2銘柄マスタRowオブジェクト.get単位口数（募集）()
            //　@　@の余りが0以外の場合
            //
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位口数（募集）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位口数（募集）が0以外の場合 
            if (l_productCondSpec.getApplyUnitQty() != null
                && ((l_mutualFundProductSonar2Row.getRecruitUnitQty() != 0
                    && Integer.parseInt(l_productCondSpec.getApplyUnitQty())
                    % l_mutualFundProductSonar2Row.getRecruitUnitQty() != 0)
                    || (l_mutualFundProductSonar2Row.getRecruitUnitQty() == 0
                        && Integer.parseInt(l_productCondSpec.getApplyUnitQty()) != 0)))
            {
                log.debug("単位口数（募集）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02275,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位口数（募集）エラー。");
            }
            
            //33) 最低金額（募集）のチェック 
            //　@引数.銘柄条件内容.get最低金額（募集）がnull以外であり、かつ以下の条件が満たされる場合、 
            //　@（最低金額（募集）エラー）の例外をスローする。 
            //　@[条件] 
            //　@　@　@引数.銘柄条件内容.get最低金額（募集）＜投信第2銘柄マスタRowオブジェクト.get最低金額（募集）() 
            if (l_productCondSpec.getApplyMinAmt() != null &&
                (Integer.parseInt(l_productCondSpec.getApplyMinAmt()) < 
                    l_mutualFundProductSonar2Row.getRecruitMinAmt()))
            {
                log.debug("最低金額（募集）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02276,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低金額（募集）エラー。");
            }

            //34) 単位金額（募集）のチェック
            //　@引数.銘柄条件内容.get単位金額（募集）がnull以外であり、かつ以下の条件が満たされる場合、
            //　@（単位金額（募集）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（募集）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（募集）÷投信第2銘柄マスタRowオブジェクト.get単位金額（募集）()
            //　@　@の余りが0以外の場合
            //
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get単位金額（募集）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get単位金額（募集）が0以外の場合
            if (l_productCondSpec.getApplyUnitAmt() != null
                && ((l_mutualFundProductSonar2Row.getRecruitUnitAmt() != 0
                        && Integer.parseInt(l_productCondSpec.getApplyUnitAmt())
                        % l_mutualFundProductSonar2Row.getRecruitUnitAmt() != 0)
                        || (l_mutualFundProductSonar2Row.getRecruitUnitAmt() == 0
                            && Integer.parseInt(l_productCondSpec.getApplyUnitAmt()) != 0)))
            {
                log.debug("単位金額（募集）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02277,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単位金額（募集）エラー。");
            }

            //35) 外貨最低金額（新規買付）のチェック
            //　@引数.銘柄条件内容.get外貨最低金額（新規買付）がnull以外であり、
            // かつ以下の条件が満たされる場合、
            //　@（外貨最低金額（新規買付）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@引数.銘柄条件内容.get外貨最低金額（新規買付）
            //         ＜投信第2銘柄マスタRowオブジェクト.get外貨最低金額（買付）()
            if (l_productCondSpec.getBuyFrgnMinAmtBuy() != null
                && (Integer.parseInt(l_productCondSpec.getBuyFrgnMinAmtBuy()) <
                    l_mutualFundProductSonar2Row.getFrgnBuyMinAmt()))
            {
                log.debug("外貨最低金額（新規買付）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02745,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外貨最低金額（新規買付）エラー。");
            }

            //36) 外貨単位金額（新規買付）のチェック
            //　@引数.銘柄条件内容.get外貨単位金額（新規買付）がnull以外であり、
            // かつ以下の条件が満たされる場合、
            //　@（外貨単位金額（新規買付）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get外貨単位金額（新規買付）÷投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）()
            //　@　@の余りが0以外の場合
            //
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get外貨単位金額（新規買付）が0以外の場合
            if (l_productCondSpec.getBuyFrgnUnitAmtBuy() != null
                && ((l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getBuyFrgnUnitAmtBuy())
                    % l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getBuyFrgnUnitAmtBuy()) != 0)))
            {
                log.debug("外貨単位金額（新規買付）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02746,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外貨単位金額（新規買付）エラー。");
            }

            //37) 外貨最低金額（追加買付）のチェック
            //　@引数.銘柄条件内容.get外貨最低金額（追加買付）がnull以外であり、
            // かつ以下の条件が満たされる場合、
            //　@（外貨最低金額（追加買付）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@引数.銘柄条件内容.get外貨最低金額（追加買付）
            //  ＜投信第2銘柄マスタRowオブジェクト.get外貨最低金額（買付）()
            if (l_productCondSpec.getBuyFrgnMinAmtAdd() != null
                && (Integer.parseInt(l_productCondSpec.getBuyFrgnMinAmtAdd()) <
                    l_mutualFundProductSonar2Row.getFrgnBuyMinAmt()))
            {
                log.debug("外貨最低金額（追加買付）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02747,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外貨最低金額（追加買付）エラー。");
            }

            //38) 外貨単位金額（追加買付）のチェック
            //　@引数.銘柄条件内容.get外貨単位金額（追加買付）がnull以外であり、
            //    かつ以下の条件が満たされる場合、
            //　@（外貨単位金額（追加買付）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get外貨単位金額（追加買付）÷投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）()
            //　@　@の余りが0以外の場合
            //
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（買付）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get外貨単位金額（追加買付）が0以外の場合
            if (l_productCondSpec.getBuyFrgnUnitAmtAdd() != null
                && ((l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getBuyFrgnUnitAmtAdd())
                    % l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getFrgnBuyUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getBuyFrgnUnitAmtAdd()) != 0)))
            {
                log.debug("外貨単位金額（追加買付）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02748,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外貨単位金額（追加買付）エラー。");
            }

            //39) 外貨最低金額（解約）のチェック
            //　@引数.銘柄条件内容.get外貨最低金額（解約）がnull以外であり、
            // かつ以下の条件が満たされる場合、
            //　@（外貨最低金額（解約）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@引数.銘柄条件内容.get外貨最低金額（解約）
            //          ＜投信第2銘柄マスタRowオブジェクト.get外貨最低金額（解約）()
            if (l_productCondSpec.getSellFrgnMinAmtSell() != null
                && (Integer.parseInt(l_productCondSpec.getSellFrgnMinAmtSell()) <
                    l_mutualFundProductSonar2Row.getFrgnSellMinAmt()))
            {
                log.debug("外貨最低金額（解約）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02749,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外貨最低金額（解約）エラー。");
            }

            //40) 外貨単位金額（解約）のチェック
            //　@引数.銘柄条件内容.get外貨単位金額（解約）がnull以外であり、
            //  かつ以下の条件が満たされる場合、
            //　@（外貨単位金額（解約）エラー）の例外をスローする。
            //　@[条件]
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（解約）() が0以外かつ、
            //　@　@　@引数.銘柄条件内容.get外貨単位金額（解約）÷投信第2銘柄マスタRowオブジェクト.get外貨単位金額（解約）()
            //　@　@の余りが0以外の場合
            //
            //　@　@　@投信第2銘柄マスタRowオブジェクト.get外貨単位金額（解約）() が0かつ、
            //　@　@　@引数.銘柄条件内容.get外貨単位金額（解約）が0以外の場合
            if (l_productCondSpec.getSellFrgnUnitAmtSell() != null
                && ((l_mutualFundProductSonar2Row.getFrgnSellUnitAmt() != 0
                    && Integer.parseInt(l_productCondSpec.getSellFrgnUnitAmtSell())
                    % l_mutualFundProductSonar2Row.getFrgnSellUnitAmt() != 0)
                    || (l_mutualFundProductSonar2Row.getFrgnSellUnitAmt() == 0
                        && Integer.parseInt(l_productCondSpec.getSellFrgnUnitAmtSell()) != 0)))
            {
                log.debug("外貨単位金額（解約）エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02750,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外貨単位金額（解約）エラー。");
            }
            
            // 1.14 注文受付締切時間のチェック 
            // 引数.銘柄条件内容の以下の項目のいずれかがnullではない場合、 
            // 　@以下を実施する。 
            // 　@・注文締切開始時間（平日）　@　@・注文締切終了時間（平日） 
            // 　@・注文締切開始時間（半日）　@　@・注文締切終了時間（半日） 
            if(l_productCondSpec.getOrderCloseStartTimeFull() != null
                    || l_productCondSpec.getOrderCloseEndTimeFull() != null
                    || l_productCondSpec.getOrderCloseStartTimeHalf() != null
                    || l_productCondSpec.getOrderCloseEndTimeHalf() != null)
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //  1.14.1) 「投信会社別条件テーブル（SONAR）」を検索する。  
                // 　@[検索条件]  
                // 　@　@証券会社コード＝引数.証券会社.getInstitutionCode() and 
                // 　@　@銘柄コード＝引数.銘柄条件内容.get銘柄コード()
                MutualFundInstCondSonarRow l_mfInstCondSonarRow = 
                    MutualFundInstCondSonarDao.findRowByPk(
                        l_institution.getInstitutionCode(),
                        l_productCondSpec.getMutualProductCode());
                
                // 1.14.2 get注文締切開始時間（平日）
                String l_strCondSpecOrderCloseStartTimeFull = 
                    l_productCondSpec.getOrderCloseStartTimeFull();
                
                // 1.14.3 get注文締切終了時間（平日）
                String l_strCondSpecOrderCloseEndTimeFull = 
                    l_productCondSpec.getOrderCloseEndTimeFull();
                
                //  ----- 注文締切開始時間（平日）のチェック

                //1.14.4 引数.銘柄条件内容.get注文締切開始時間（平日）!=null 
                //      または 引数.銘柄条件内容.get注文締切終了時間（平日）!=nullの場合
                if(l_strCondSpecOrderCloseStartTimeFull != null || l_strCondSpecOrderCloseEndTimeFull != null)
                {
                    // 1.14.4.1 取引時間テーブル検索処理
                    // 　@　@検索結果の最初の一件目を取得して「取引時間テーブルParams」でキャストする。 
                    //  　@[検索条件]  
                    //  　@　@証券会社コード＝引数.証券会社.getInstitutionCode() and  
                    //  　@　@市場コード＝”DEFAULT” and 
                    //  　@　@受付時間区分＝”投資信託” and 
                    //  　@　@商品コード＝引数.銘柄条件内容.銘柄コード and  
                    //  　@　@営業日区分＝”終日営業日” and  
                    //  　@　@市場トリガ発行＝”SONARへMQトリガを実施する” and  
                    //  　@　@受付可能＝”受付可能” and  
                    //  　@　@発注日計算＝”当日” 
                    String l_strWhere = " institution_code = ? and " +
                        " market_code = ? and " +
                        " trading_time_type = ? and " +
                        " product_code = ? and " +
                        " biz_date_type = ? and " +
                        " submit_market_trigger = ? and " +
                        " enable_order = ? and " +
                        " bizdate_calc_parameter = ? ";
            
                    Object[] l_objWhere = {
                        l_institution.getInstitutionCode(),
                        WEB3MarketCodeDef.DEFAULT,
                        WEB3TradingTimeTypeDef.MUTUAL_FUND,
                        l_productCondSpec.getMutualProductCode(),
                        WEB3BizDateTypeDef.BIZ_DATE,
                        WEB3SubmitMarketTriggerDef.TRIGGER,
                        WEB3EnableOrderDef.ENABLE,
                        WEB3BizDateCalcParameterDef.DAY_BIZ_DATE
                        };
                    List l_lisSearchResult =
                        l_queryProcessor.doFindAllQuery(
                            TradingTimeRow.TYPE,
                            l_strWhere,
                            null,
                            l_objWhere);
                    
                    if(l_lisSearchResult != null && l_lisSearchResult.size() > 0)
                    {
                        TradingTimeRow l_tradingTimeRow = 
                            (TradingTimeRow)l_lisSearchResult.get(0);   
                        
                        if(l_tradingTimeRow.getStartTime() == null 
                                || l_tradingTimeRow.getStartTime().length() < 6 
                                || l_tradingTimeRow.getEndTime() == null 
                                || l_tradingTimeRow.getEndTime().length() < 6)
                        {
                            log.debug("取引時間テーブルParamsオブジェクト.開始時間　@または　@" +
                                    "取引時間テーブルParamsオブジェクト.終了時間不正");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }

                        long l_lngTradingTimeRowStartTime = 
                            Long.parseLong(l_tradingTimeRow.getStartTime().substring(0, 6));

                        // 1.14.4.2 get注文締切開始時間（平日）!=null の場合、取引開始時間との整合性チェックを実施する。
                        if (l_strCondSpecOrderCloseStartTimeFull != null)
                        {
                            if(l_strCondSpecOrderCloseStartTimeFull.length() < 4)
                            {
                                log.debug("銘柄条件内容.get注文締切開始時間（平日）不正。");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME);
                            }
                            
                            //format 銘柄条件内容.get注文締切開始時間（平日） with hhmmss
                            long l_lngCondSpecCloseStartTime = 
                                Long.parseLong(l_strCondSpecOrderCloseStartTimeFull + "00");

                            //取得した「取引時間テーブルParamsオブジェクト」.get開始時間() 
                            //≧ 引数.銘柄条件内容.get注文締切開始時間（平日）() 
                            //の場合、例外をスローする。
                            if (l_lngTradingTimeRowStartTime >= l_lngCondSpecCloseStartTime)
                            {
                                log.debug("注文受付停止開始時間（平日）の指定可能時間の範囲内ではありません。");
                                throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_01701,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    "注文受付停止開始時間（平日）の指定可能時間の範囲内ではありません。");
                            }
                            
                            
                            if(l_mfInstCondSonarRow.getOrderAcceptLimitTimeUsual() == null
                                    || l_mfInstCondSonarRow.getOrderAcceptLimitTimeUsual().length() < 4)
                            {
                                log.debug("投信会社別テーブル（SONAR）.注文締切時間（平日）不正。");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME);
                            }
                            // 取得した投信会社別テーブル（SONAR）.注文締切時間（平日)
                            // format 投信会社別テーブル（SONAR）.注文締切時間（平日) with hhmmss
                            long l_lngOrderAcceptLimitTimeUsual = 
                                Long.parseLong(l_mfInstCondSonarRow.getOrderAcceptLimitTimeUsual() + "00");

                            // 1.14.4.2.1 取得した投信会社別テーブル（SONAR）.注文締切時間（平日）
                            //       ＜ 引数.注文締切開始時間（平日）の場合、例外をスロー。
                            if(l_lngOrderAcceptLimitTimeUsual < l_lngCondSpecCloseStartTime)
                            {
                                log.debug(" Remain_Test ----------------------------> case3");
                                log.debug("注文受付停止開始時間（平日）の指定がHOST登録時間より後の時間が指定されています。");
                                throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_01702,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    "注文受付停止開始時間（平日）の指定がHOST登録時間より後の時間が指定されています。");
                            }
                        }
                        
                        // 1.14.4.3 get注文締切開始時間（平日）=nullまたは、get注文締切終了時間（平日()=nullの場合
                        if(l_strCondSpecOrderCloseStartTimeFull == null || l_strCondSpecOrderCloseEndTimeFull == null)
                        {
                            // 1.14.4.3.1 取引時間テーブルより「取引停止時間帯」を取得する検索処理
                            // 以下の条件で、「取引時間テーブル」から現在の「取引停止時間帯」を検索する。 
                            //[検索条件] 
                            // 証券会社コード＝引数.証券会社.getInstitutionCode() and 
                            //市場コード＝”DEFAULT” and 
                            //受付時間区分＝”投資信託” and 
                            //商品コード＝引数.銘柄条件内容.銘柄コード and 
                            //営業日区分＝”終日営業日” and 
                            //市場トリガ発行＝”SONARへMQトリガを実施しない” and 
                            //受付可能＝”受付不可” and 
                            //発注日計算＝”翌営業日”
                            String l_strTradingStopWhere = " institution_code = ? and "
                                + " market_code = ? and "
                                + " trading_time_type = ? and "
                                + " product_code = ? and "
                                + " biz_date_type = ? and "
                                + " submit_market_trigger = ? and "
                                + " enable_order = ? and "
                                + " bizdate_calc_parameter = ? ";

                            Object[] l_objTradingStopWhereValue =
                            { l_institution.getInstitutionCode(),
                                WEB3MarketCodeDef.DEFAULT,
                                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                                l_productCondSpec.getMutualProductCode(),
                                WEB3BizDateTypeDef.BIZ_DATE,
                                WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                                WEB3EnableOrderDef.DISABLED,
                                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE };
                            
                            List l_lisTradingStopTimeResult = 
                                l_queryProcessor.doFindAllQuery(
                                    TradingTimeRow.TYPE,l_strTradingStopWhere, 
                                    null, l_objTradingStopWhereValue);
                        
                            TradingTimeRow l_tradingStopTimeRow = null;
                            if(l_lisTradingStopTimeResult != null && l_lisTradingStopTimeResult.size() > 0)
                            {
                                l_tradingStopTimeRow = 
                                    (TradingTimeRow)l_lisTradingStopTimeResult.get(0); 
                                
                                // 1.14.4.3.2 引数.銘柄条件内容.get注文締切開始時間（平日）() = null の場合
                                if (l_strCondSpecOrderCloseStartTimeFull == null)
                                {
                                    // 取得した「取引停止時間帯」の開始時間
                                    long l_lngTradingStopTimeStartTime = 
                                        Long.parseLong(l_tradingStopTimeRow.getStartTime().substring(0, 6));

                                    // 引数.銘柄条件内容.get注文締切終了時間（平日）
                                    long l_lngOrderCloseEndTimeFull = 
                                        Long.parseLong(l_strCondSpecOrderCloseEndTimeFull + "00");
                                    
                                    // 1.14.4.3.2.1  取得した「取引停止時間帯」の開始時間
                                    // ＞引数.銘柄条件内容.get注文締切終了時間（平日）()の場合、例外をスロー
                                    if(l_lngTradingStopTimeStartTime > l_lngOrderCloseEndTimeFull)
                                    {
                                        log.debug("注文受付停止開始時間（平日）が注文受付停止終了時間（平日）以上です。");
                                        throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01266,
                                            this.getClass().getName() + "." + STR_METHOD_NAME,
                                            "注文受付停止開始時間（平日）が注文受付停止終了時間（平日）以上です。");
                                    }
                                }
                                // 1.14.4.3.3 引数.銘柄条件内容.get注文締切終了時間（平日）()= nullの場合
                                if (l_strCondSpecOrderCloseEndTimeFull == null)
                                {
                                    // 引数.銘柄条件内容.get注文締切開始時間（平日）
                                    long l_lngOrderCloseStartTimeFull = 
                                        Long.parseLong(l_strCondSpecOrderCloseStartTimeFull + "00");

                                    // 取得した「取引停止時間帯」の終了時間
									Date l_datTradingStopTimeEndTime = 
										WEB3DateUtility.getDate(l_tradingStopTimeRow.getEndTime(), "HHmmss");
									String l_strTradingStopTimeEndTime =
										WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
											l_datTradingStopTimeEndTime,1L), "HHmmss");
									long l_lngTradingStopTimeEndTime = 
										Long.parseLong(l_strTradingStopTimeEndTime.substring(0, 6));

                                    // 1.14.4.3.3.1 引数.銘柄条件内容.get注文締切開始時間（平日）()
                                    // ＞取得した「取引停止時間帯」の終了時間の場合、例外をスロー
                                    if(l_lngOrderCloseStartTimeFull > l_lngTradingStopTimeEndTime)
                                    {
                                        log.debug("注文受付停止開始時間（平日）が注文受付停止終了時間（平日）以上です。");
                                        throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01266,
                                            this.getClass().getName() + "." + STR_METHOD_NAME,
                                            "注文受付停止開始時間（平日）が注文受付停止終了時間（平日）以上です。");
                                    }
                                }
                            }
                        }
                    }
                }
                
                // 1.14.5  get注文締切開始時間（半日）
                String l_strCondSpecOrderCloseStartTimeHalf = 
                    l_productCondSpec.getOrderCloseStartTimeHalf();
                
                // 1.14.6 get注文締切終了時間（半日）
                String l_strCondSpecOrderCloseEndTimeHalf = 
                    l_productCondSpec.getOrderCloseEndTimeHalf();
                
                //  ----- 注文締切開始時間（半日）のチェック

                //1.14.7 引数.銘柄条件内容.get注文締切開始時間（半日）!=null 
                //      または 引数.銘柄条件内容.get注文締切終了時間（半日）!=nullの場合
                if(l_strCondSpecOrderCloseStartTimeHalf != null || l_strCondSpecOrderCloseEndTimeHalf != null)
                {
                    // 1.14.7.1 取引時間テーブル検索処理
                    // 　@　@検索結果の最初の一件目を取得して「取引時間テーブルParams」でキャストする。 
                    //  　@[検索条件]  
                    //  　@　@証券会社コード＝引数.証券会社.getInstitutionCode() and  
                    //  　@　@市場コード＝”DEFAULT” and 
                    //  　@　@受付時間区分＝”投資信託” and 
                    //  　@　@商品コード＝引数.銘柄条件内容.銘柄コード and  
                    //  　@　@営業日区分＝半日（午前のみ）” and  
                    //  　@　@市場トリガ発行＝”SONARへMQトリガを実施する” and  
                    //  　@　@受付可能＝”受付可能” and  
                    //  　@　@発注日計算＝”当日” 
                    String l_strWhere = " institution_code = ? and " +
                        " market_code = ? and " +
                        " trading_time_type = ? and " +
                        " product_code = ? and " +
                        " biz_date_type = ? and " +
                        " submit_market_trigger = ? and " +
                        " enable_order = ? and " +
                        " bizdate_calc_parameter = ? ";
            
                    Object[] l_objWhere = {
                        l_institution.getInstitutionCode(),
                        WEB3MarketCodeDef.DEFAULT,
                        WEB3TradingTimeTypeDef.MUTUAL_FUND,
                        l_productCondSpec.getMutualProductCode(),
                        WEB3BizDateTypeDef.BIZ_DATE_AM,
                        WEB3SubmitMarketTriggerDef.TRIGGER,
                        WEB3EnableOrderDef.ENABLE,
                        WEB3BizDateCalcParameterDef.DAY_BIZ_DATE
                        };
                    List l_lisSearchResult =
                        l_queryProcessor.doFindAllQuery(
                            TradingTimeRow.TYPE,
                            l_strWhere,
                            null,
                            l_objWhere);
                    
                    if(l_lisSearchResult != null && l_lisSearchResult.size() > 0)
                    {
                        TradingTimeRow l_tradingTimeRow = 
                            (TradingTimeRow)l_lisSearchResult.get(0);   
                        
                        if(l_tradingTimeRow.getStartTime() == null 
                                || l_tradingTimeRow.getStartTime().length() < 6 
                                || l_tradingTimeRow.getEndTime() == null 
                                || l_tradingTimeRow.getEndTime().length() < 6)
                        {
                            log.debug("取引時間テーブルParamsオブジェクト.開始時間　@または　@" +
                                    "取引時間テーブルParamsオブジェクト.終了時間不正");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }

                        long l_lngTradingTimeRowStartTime = 
                            Long.parseLong(l_tradingTimeRow.getStartTime().substring(0, 6));

                        // 1.14.7.2 get注文締切開始時間（半日）!=null の場合、取引開始時間との整合性チェックを実施する。
                        if (l_strCondSpecOrderCloseStartTimeHalf != null)
                        {
                            if(l_strCondSpecOrderCloseStartTimeHalf.length() < 4)
                            {
                                log.debug("銘柄条件内容.get注文締切開始時間（半日）不正。");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME);
                            }
                            //format 銘柄条件内容.get注文締切開始時間（半日） with hhmmss
                            long l_lngCondSpecCloseStartTimeHalf = 
                                Long.parseLong(l_strCondSpecOrderCloseStartTimeHalf + "00");
                            
                            //取得した「取引時間テーブルParamsオブジェクト」.get開始時間()
                            //≧ 引数.銘柄条件内容.get注文締切開始時間（半日）() 
                            //の場合、例外をスローする。
                            if (l_lngTradingTimeRowStartTime >= l_lngCondSpecCloseStartTimeHalf)
                            {
                                log.debug("注文受付停止開始時間の指定可能時間の範囲内ではありません。");
                                throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_01701,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    "注文受付停止開始時間の指定可能時間の範囲内ではありません。");
                            }
                            
                            if(l_mfInstCondSonarRow.getOrderAcceptLimitTimeHalf() == null
                                    || l_mfInstCondSonarRow.getOrderAcceptLimitTimeHalf().length() < 4)
                            {
                                log.debug("投信会社別テーブル（SONAR）.注文締切時間（半日）不正。");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME);
                            }
                            // 取得した投信会社別テーブル（SONAR）.注文締切時間（半日)
                            //format 投信会社別テーブル（SONAR）.注文締切時間（半日) with hhmmss
                            long l_lngOrderAcceptLimitTimeHalf = 
                                Long.parseLong(l_mfInstCondSonarRow.getOrderAcceptLimitTimeHalf() + "00");

                            // 1.14.7.2.1 取得した投信会社別テーブル（SONAR）.注文締切時間（半日）
                            //       ＜ 引数.注文締切開始時間（半日）の場合、例外をスロー。
                            if(l_lngOrderAcceptLimitTimeHalf < l_lngCondSpecCloseStartTimeHalf)
                            {
                                log.debug("注文受付停止開始時間の指定がHOST登録時間より後の時間が指定されています。");
                                throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_01702,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    "注文受付停止開始時間の指定がHOST登録時間より後の時間が指定されています。");
                            }
                        }
                        
                        // 1.14.7.3 get注文締切開始時間（半日）=nullまたは、get注文締切終了時間（半日()=nullの場合
                        if(l_strCondSpecOrderCloseStartTimeHalf == null || l_strCondSpecOrderCloseEndTimeHalf == null)
                        {
                            // 1.14.7.3.1 取引時間テーブルより「取引停止時間帯」を取得する検索処理
                            // 以下の条件で、「取引時間テーブル」から現在の「取引停止時間帯」を検索する。 
                            //[検索条件]  
                            // 証券会社コード＝引数.証券会社.getInstitutionCode() and 
                            //市場コード＝”DEFAULT” and 
                            //受付時間区分＝”投資信託” and 
                            //商品コード＝引数.銘柄条件内容.銘柄コード and 
                            //営業日区分＝”半日（午前のみ）” and 
                            //市場トリガ発行＝”SONARへMQトリガを実施しない” and 
                            //受付可能＝”受付不可” and 
                            //発注日計算＝”翌営業日”
                            String l_strTradingStopWhere = " institution_code = ? and "
                                + " market_code = ? and "
                                + " trading_time_type = ? and "
                                + " product_code = ? and "
                                + " biz_date_type = ? and "
                                + " submit_market_trigger = ? and "
                                + " enable_order = ? and "
                                + " bizdate_calc_parameter = ? ";

                            Object[] l_objTradingStopWhereValue =
                            { l_institution.getInstitutionCode(),
                                WEB3MarketCodeDef.DEFAULT,
                                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                                l_productCondSpec.getMutualProductCode(),
                                WEB3BizDateTypeDef.BIZ_DATE_AM,
                                WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                                WEB3EnableOrderDef.DISABLED,
                                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE };
                            
                            List l_lisTradingStopTimeResult = 
                                l_queryProcessor.doFindAllQuery(
                                    TradingTimeRow.TYPE,l_strTradingStopWhere, 
                                    null, l_objTradingStopWhereValue);
                        
                            TradingTimeRow l_tradingStopTimeRow = null;
                            if(l_lisTradingStopTimeResult != null && l_lisTradingStopTimeResult.size() > 0)
                            {
                                l_tradingStopTimeRow = 
                                    (TradingTimeRow)l_lisTradingStopTimeResult.get(0); 
                                
                                // 1.14.7.3.2 引数.銘柄条件内容.get注文締切開始時間（半日）() = null の場合
                                if (l_strCondSpecOrderCloseStartTimeHalf == null)
                                {
                                    // 取得した「取引停止時間帯」の開始時間
                                    long l_lngTradingStopTimeStartTime = 
                                        Long.parseLong(l_tradingStopTimeRow.getStartTime().substring(0, 6));

                                    // 引数.銘柄条件内容.get注文締切終了時間（半日）
                                    long l_lngOrderCloseEndTimeHalf = 
                                        Long.parseLong(l_strCondSpecOrderCloseEndTimeHalf + "00");
                                    
                                    // 1.14.7.3.2.1  取得した「取引停止時間帯」の開始時間
                                    // ＞引数.銘柄条件内容.get注文締切終了時間（半日）()の場合、例外をスロー
                                    if(l_lngTradingStopTimeStartTime > l_lngOrderCloseEndTimeHalf)
                                    {
                                        log.debug("注文受付停止開始時間（半日）が注文受付停止終了時間（半日）以上です。");
                                        throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01270,
                                            this.getClass().getName() + "." + STR_METHOD_NAME,
                                            "注文受付停止開始時間（半日）が注文受付停止終了時間（半日）以上です。");
                                    }
                                }
                                // 1.14.7.3.3 引数.銘柄条件内容.get注文締切終了時間（半日）()= nullの場合
                                if (l_strCondSpecOrderCloseEndTimeHalf == null)
                                {
                                    // 引数.銘柄条件内容.get注文締切開始時間（半日）
                                    long l_lngOrderCloseStartTimeHalf = 
                                        Long.parseLong(l_strCondSpecOrderCloseStartTimeHalf + "00");

                                    // 取得した「取引停止時間帯」の終了時間
									Date l_datTradingStopTimeEndTime = 
										WEB3DateUtility.getDate(l_tradingStopTimeRow.getEndTime(), "HHmmss");
									String l_strTradingStopTimeEndTime =
										WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
											l_datTradingStopTimeEndTime,1L), "HHmmss");
                                    long l_lngTradingStopTimeEndTime = 
                                        Long.parseLong(l_strTradingStopTimeEndTime.substring(0, 6));

                                    // 1.14.7.3.3.1 引数.銘柄条件内容.get注文締切開始時間（半日）()
                                    // ＞取得した「取引停止時間帯」の終了時間の場合、例外をスロー
                                    if(l_lngOrderCloseStartTimeHalf > l_lngTradingStopTimeEndTime)
                                    {
                                        log.debug("注文受付停止開始時間（半日）が注文受付停止終了時間（半日）以上です。");
                                        throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_01270,
                                            this.getClass().getName() + "." + STR_METHOD_NAME,
                                            "注文受付停止開始時間（半日）が注文受付停止終了時間（半日）以上です。");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("該当データなし", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                    "投信銘柄オブジェクトの取得できない with "
                    + "this.getMutualFundProduct()  証券会社= "
                    + l_institution.getInstitutionCode()
                    + "銘柄コード = "
                    + l_productCondSpec.getMutualProductCode());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
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
        
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (get更新用投信銘柄)<BR>
	 * （getMutualFundProductのオーバーロード） <BR>
	 * <BR>
	 *Select For Updateで拡張投信銘柄を取得する。  <BR>
	 *
	 *１）　@以下の条件で「投信銘柄マスターテーブル」を検索する。  <BR>
	 *　@証券会社コード=引数.証券会社コード and  <BR>
	 *　@銘柄コード=引数.銘柄コード  <BR>
	 *　@回号コード=”0：DEFAULT”  <BR>
	 * <BR>
	 *２）　@１）の検索結果のクローンを作成し、そのクローンを引数に、  <BR>
	 *　@this.to銘柄( )をコールして取得した拡張投信銘柄オブジェクトを返却する。 <BR>
	 * <BR>
	 * @@param l_institutionCode - 証券会社コード
	 * @@param l_strProductCode - 銘柄コード
	 * @@return WEB3MutualFundProduct
	 * @@throws WEB3BaseException
	 * @@roseuid 40AD9019005D
	 */
	public WEB3MutualFundProduct getUpdateMutualFundTradedProduct(
		String l_institutionCode,
		String l_strProductCode)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getUpdateMutualFundTradedProduct("
				+ "Institution l_institution,"
				+ "String l_strProductCode)";
		log.entering(STR_METHOD_NAME);
       
		// １）　@以下の条件で「投信銘柄マスターテーブル」を検索する。 
		//   証券会社コード=引数.証券会社コード and  
		//   銘柄コード=引数.銘柄コード 
		//   回号コード=”0：DEFAULT” 
		MutualFundProductRow l_mfProductRow = null;
		try
		{
			String l_strWhere = "institution_code = ? and " +
					" product_code = ? and " +
					" product_issue_code = ? ";
            
			String l_strCondition = " for update ";

			Object[] l_objWhere = {
					l_institutionCode,
					l_strProductCode,
					"0"};
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			List l_lisSearchResult =
				l_queryProcessor.doFindAllQuery(
					MutualFundProductRow.TYPE,
					l_strWhere,
					null,
					l_strCondition,
					l_objWhere);
            
			l_mfProductRow = 
				(MutualFundProductRow)l_lisSearchResult.get(0);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました  when search MutualFundProductCategory");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);

		}
        
		// ２）　@１）の検索結果のクローンを作成し、そのクローンを引数に、 
		//   this.to銘柄()をコールして取得した拡張投信銘柄オブジェクトを返却する。
		WEB3MutualFundProduct l_mfProduct =
			(WEB3MutualFundProduct) this.toProduct(l_mfProductRow);
        
		log.exiting(STR_METHOD_NAME);                
		return l_mfProduct;
	}
    
    /**
     * (get乗換約定日)<BR>
     * 乗換注文の約定日を返す。 <BR>
     * <BR>
     * １）発注日を取得する。<BR>
     * 　@　@投信取引時間管理.get乗換発注日（）をコールする。<BR>
     *     [引数]
     *     乗換元銘柄コード： 引数.乗換元銘柄コード
     *     乗換先銘柄コード： 引数.乗換先銘柄コード
     * <BR>
     * ２）取引カレンダコンテキストを乗換先銘柄の情報でリセットする。 <BR>
     * <BR>
     * ２－１）投信取引時間管理.reset銘柄コード()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    銘柄コード： 引数.乗換先銘柄コード <BR>
     * <BR>
     * ２－２）投信取引時間管理.reset注文受付トランザクション()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    注文受付トランザクション： ”買付” <BR>
     * <BR>
     * ２－３）投信取引時間管理.setTimestamp()をコールする。<BR> 
     * <BR>
     * ３）乗換先銘柄の約定日を取得する。 <BR>
     * <BR>
     *    this.get約定日()をコールする。 <BR>
     * <BR>
     *    ［引数］ <BR>
     *    証券会社： 引数.証券会社 <BR>
     *    銘柄コード： 引数.乗換先銘柄コード <BR>
     *    発注日： 取得した乗換発注日 <BR>
     * <BR>
     * ４）取引カレンダコンテキストを乗換元銘柄の情報でリセットする。 <BR>
     * <BR>
     * ４－１）投信取引時間管理.reset銘柄コード()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    銘柄コード： 引数.乗換元銘柄コード <BR>
     * <BR>
     * ４－２）投信取引時間管理.reset注文受付トランザクション()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    注文受付トランザクション： ”売付” <BR>
     * <BR>
     * ４－３）投信取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ５）乗換元銘柄の約定日を取得する。 <BR>
     * <BR>
     *    this.get約定日()をコールする。 <BR>
     * <BR>
     *    ［引数］ <BR>
     *    証券会社： 引数.証券会社 <BR>
     *    銘柄コード： 引数.乗換元銘柄コード <BR>
     *    発注日： 取得した乗換発注日 <BR>
     * <BR>
     * ６）約定日を決定し返却する。 <BR>
     * <BR>
     *    乗換元銘柄の約定日 < 乗換先銘柄の約定日の場合、<BR>
     * 乗換先銘柄の約定日を返却する。 <BR>
     *    乗換元銘柄の約定日 >= 乗換先銘柄の約定日の場合、<BR>
     * 乗換元銘柄の約定日を返却する。 <BR>
     * <BR>
     * @@param l_institution - 証券会社
     * @@param l_strSwtOriginProductCode - 乗換元銘柄コード
     * @@param l_strSwtPointProductCode - 乗換先銘柄コード
     * @@return Timestamp
     * @@throws WEB3BaseException
     * @@roseuid 40AD9019005D
     */
    public Timestamp getSwtExecutedDate(
        Institution l_institution, 
        String l_strProductCode, 
        String l_strSwtProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSwtExecutedDate(Institution, String, String)";                
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）発注日を取得する。
        //   投信取引時間管理.get乗換発注日（）をコールする。
        //   [引数]
        //   乗換元銘柄コード： 引数.乗換元銘柄コード
        //   乗換先銘柄コード： 引数.乗換先銘柄コード
        Date l_datCurrentBizDate = WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
            l_strProductCode, l_strSwtProductCode);
        
        //２）取引カレンダコンテキストを乗換先銘柄の情報でリセットする。 
        //２－１）投信取引時間管理.reset銘柄コード()をコールする。 
        //   [引数] 
        //   銘柄コード： 引数.乗換先銘柄コード        
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_strSwtProductCode);

        //２－２）投信取引時間管理.reset注文受付トランザクション()をコールする。 
        //   [引数] 
        //   注文受付トランザクション： ”買付” 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);

        //２－３）投信取引時間管理.set日付ロール()をコールする。         
		WEB3MutualFundTradingTimeManagement.setDateRole();
        
        //３）乗換先銘柄の約定日を取得する。
        //   this.get約定日()をコールする。
        //［引数］ 
        //証券会社： 引数.証券会社 
        //銘柄コード： 引数.乗換先銘柄コード 
        //発注日： 取得した乗換発注日
        Date l_datSwtExecutedDate = this.getExecutedDate(
            l_institution, l_strSwtProductCode, l_datCurrentBizDate);
        
        //４）取引カレンダコンテキストを乗換元銘柄の情報でリセットする。 
        //４－１）投信取引時間管理.reset銘柄コード()をコールする。 
        //   [引数] 
        //   銘柄コード： 引数.乗換元銘柄コード 
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_strProductCode);

        //４－２）投信取引時間管理.reset注文受付トランザクション()をコールする。 
        //   [引数] 
        //   注文受付トランザクション： ”売付” 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        
        //４－３）投信取引時間管理.set日付ロール()をコールする。 
        WEB3MutualFundTradingTimeManagement.setDateRole();
        
        //５）乗換元銘柄の約定日を取得する。
        //    this.get約定日()をコールする。
        //［引数］ 
        //証券会社： 引数.証券会社 
        //銘柄コード： 引数.乗換元銘柄コード 
        //発注日： 取得した乗換発注日
        Date l_datExecutedDate = this.getExecutedDate(
            l_institution, l_strProductCode, l_datCurrentBizDate);

        log.debug("乗換元銘柄の約定日 = " + l_datExecutedDate);
        log.debug("乗換先銘柄の約定日 = " + l_datSwtExecutedDate);
        
        //６）約定日を決定し返却する。 
        //乗換元銘柄の約定日 < 乗換先銘柄の約定日の場合、乗換先銘柄の約定日を返却する。 
        //乗換元銘柄の約定日 >= 乗換先銘柄の約定日の場合、乗換元銘柄の約定日を返却する。 
        if (WEB3DateUtility.compareToDay(l_datExecutedDate, l_datSwtExecutedDate) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("乗換約定日 = " + l_datSwtExecutedDate);
            return new Timestamp(l_datSwtExecutedDate.getTime());
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("乗換約定日 = " + l_datExecutedDate);
            return new Timestamp(l_datExecutedDate.getTime());
        }
    }
    
    /**
     * (get乗換受渡日)<BR>
     * 乗換注文の受渡日を返す。 <BR>
     * <BR>
     * １）約定日を取得する。<BR>
     * 　@　@this.get乗換約定日（）をコールする。<BR>
     *     [引数]<BR>
     *     証券会社： 引数.証券会社<BR>
     *     乗換元銘柄コード： 引数.乗換元銘柄コード<BR>
     *     乗換先銘柄コード： 引数.乗換先銘柄コード<BR>
     * <BR>
     * ２）取引カレンダコンテキストを乗換先銘柄の情報でリセットする。 <BR>
     * <BR>
     * ２－１）投信取引時間管理.reset銘柄コード()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    銘柄コード： 引数.乗換先銘柄コード <BR>
     * <BR>
     * ２－２）投信取引時間管理.reset注文受付トランザクション()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    注文受付トランザクション： ”買付” <BR>
     * <BR>
     * ２－３）投信取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）乗換先銘柄の受渡日を取得する。 <BR>
     * <BR>
     *    this.get受渡日()をコールする。 <BR>
     * <BR>
     *    ［引数］ <BR>
     *    証券会社： 引数.証券会社 <BR>
     *    銘柄コード： 引数.乗換先銘柄コード <BR>
     *    is買付： true <BR>
     *    約定日： 取得した約定日 <BR>
     * <BR>
     * ４）取引カレンダコンテキストを乗換元銘柄の情報でリセットする。 <BR>
     * <BR>
     * ４－１）投信取引時間管理.reset銘柄コード()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    銘柄コード： 引数.乗換元銘柄コード <BR>
     * <BR>
     * ４－２）投信取引時間管理.reset注文受付トランザクション()をコールする。 <BR>
     * <BR>
     *    [引数] <BR>
     *    注文受付トランザクション： ”売付” <BR>
     * <BR>
     * ４－３）投信取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ５）乗換元銘柄の受渡日を取得する。 <BR>
     * <BR>
     *    this.get受渡日()をコールする。 <BR>
     * <BR>
     *    ［引数］ <BR>
     *    証券会社： 引数.証券会社 <BR>
     *    銘柄コード： 引数.乗換元銘柄コード <BR>
     *    is買付： false <BR>
     *    約定日：　@取得した約定日
     * <BR>
     * ６）受渡日を決定し返却する。 <BR>
     * <BR>
     *    乗換元銘柄の受渡日 < 乗換先銘柄の受渡日の場合、乗換元銘柄の受渡日を返却する。 <BR>
     *    乗換元銘柄の受渡日 >= 乗換先銘柄の受渡日の場合、乗換先銘柄の受渡日を返却する。 <BR>
     * <BR>
     * @@param l_institution - 証券会社
     * @@param l_strSwtOriginProductCode - 乗換元銘柄コード
     * @@param l_strSwtPointProductCode - 乗換先銘柄コード
     * @@return Timestamp
     * @@throws WEB3BaseException
     * @@roseuid 40AD9019005D
     */
    public Timestamp getSwtDeliveryDate(
        Institution l_institution, 
        String l_strProductCode, 
        String l_strSwtProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSwtDeliveryDate(Institution, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）約定日を取得する。
        //   this.get乗換約定日（）をコールする。
        //   [引数]
        //   証券会社： 引数.証券会社
        //   乗換元銘柄コード： 引数.乗換元銘柄コード
        //   乗換先銘柄コード： 引数.乗換先銘柄コード
        Date l_datSwtExecutedDate = this.getSwtExecutedDate(
            l_institution, l_strProductCode, l_strSwtProductCode);
        
        //２）取引カレンダコンテキストを乗換先銘柄の情報でリセットする。 
        //２－１）投信取引時間管理.reset銘柄コード()をコールする。 
        //   [引数] 
        //   銘柄コード： 引数.乗換先銘柄コード         
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_strSwtProductCode);

        //２－２）投信取引時間管理.reset注文受付トランザクション()をコールする。 
        //   [引数] 
        //   注文受付トランザクション： ”買付” 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);

        //２－３）投信取引時間管理.set日付ロール()をコールする。         
        WEB3MutualFundTradingTimeManagement.setDateRole();
        
        //３）乗換先銘柄の受渡日を取得する。 
        //　@　@this.get受渡日()をコールする。 
        //　@ ［引数］ 
        //　@　@証券会社： 引数.証券会社 
        //　@　@銘柄コード： 引数.乗換先銘柄コード 
        //　@　@is買付： true 
        //　@　@約定日：取得した約定日
        Date l_datSwtDeliveryDate = 
            this.getDeliveryDate(l_institution, l_strSwtProductCode, true, l_datSwtExecutedDate);
        
        //４）取引カレンダコンテキストを乗換元銘柄の情報でリセットする。 
        //４－１）投信取引時間管理.reset銘柄コード()をコールする。 
        //   [引数] 
        //   銘柄コード： 引数.乗換元銘柄コード 
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_strProductCode);

        //４－２）投信取引時間管理.reset注文受付トランザクション()をコールする。 
        //   [引数] 
        //   注文受付トランザクション： ”売付” 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        
        //４－３）投信取引時間管理.set日付ロール()をコールする。 
        WEB3MutualFundTradingTimeManagement.setDateRole();
        
        //５）乗換元銘柄の受渡日を取得する。 
        //   this.get受渡日()をコールする。
        //  ［引数］ 
        //   証券会社： 引数.証券会社 
        //   銘柄コード： 引数.乗換元銘柄コード 
        //   is買付： false 
        //   約定日：取得した約定日
        Date l_datDeliveryDate = 
            this.getDeliveryDate(l_institution, l_strProductCode, false, l_datSwtExecutedDate);
        
        log.debug("乗換元銘柄の受渡日 = " + l_datDeliveryDate);
        log.debug("乗換先銘柄の受渡日 = " + l_datSwtDeliveryDate);

        //６）受渡日を決定し返却する。 
        //乗換元銘柄の受渡日 < 乗換先銘柄の受渡日の場合、乗換元銘柄の受渡日を返却する。 
        //乗換元銘柄の受渡日 >= 乗換先銘柄の受渡日の場合、乗換先銘柄の受渡日を返却する。  
        
        if (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datSwtDeliveryDate) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("乗換受渡日 = " + l_datDeliveryDate);
            return new Timestamp(l_datDeliveryDate.getTime());
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("乗換受渡日 = " + l_datSwtDeliveryDate);
            return new Timestamp(l_datSwtDeliveryDate.getTime());
        }
    }
    
    /**
     * (get約定日)<BR>
     * 引数.発注日に対する約定日を返す。<BR>
     * <BR>
     * １）　@拡張投信取引銘柄オブジェクトを取得する。<BR>
     * 　@this.get投信取引銘柄()をコールして、<BR>
     * 拡張投信取引銘柄オブジェクトを取得する。<BR>
     * 　@［get投信取引銘柄に渡すパラメタ］<BR>
     * 　@　@証券会社： 引数.証券会社<BR>
     * 　@　@銘柄コード： 引数.銘柄コード<BR>
     * <BR>
     * ２）　@約定日を取得する。<BR>
     * 　@拡張投信取引銘柄.get約定日()をコールして、約定日を取得する。<BR>
     *   [引数]
     *   発注日：引数.発注日
     * <BR>
     * ３）　@取得した約定日を返す。<BR>
     * <BR>
     * @@param l_institution - 証券会社
     * @@param l_strProductCode - 銘柄コード
     * @@param l_datCurrentBizDate - 発注日
     * @@return Timestamp
     */
    public Timestamp getExecutedDate(
        Institution l_institution,
        String l_strProductCode,
        Date l_datCurrentBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getExecutedDate("
                + "Institution l_institution,"
                + "String l_strProductCode)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Timestamp l_executedDate = null;
        Date l_datExecutedDate = null;
        try
        {
            //１）拡張投信取引銘柄オブジェクトを取得する。
            WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
                (WEB3MutualFundTradedProduct) this.getMutualFundTradedProduct(
                    l_institution,
                    l_strProductCode);

            //２）約定日を取得する。
            //  拡張投信取引銘柄.get約定日()をコールして、約定日を取得する。
            //  [引数]
            //  発注日：引数.発注日
            l_datExecutedDate = l_web3MutualFundTradedProduct.getExecutedDate(
                l_datCurrentBizDate);
            l_executedDate = new Timestamp(l_datExecutedDate.getTime());
        }
        catch (NotFoundException l_ex)
        {
            log.error("拡張投信取引銘柄オブジェクトを取得するできない for: "
                      + "this.getMutualFundTradedProduct() "
                      + "l_institution = " + l_institution.getInstitutionCode()
                      + "l_strProductCode = " + l_strProductCode);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }

        //３）　@取得した約定日を返す
        return l_executedDate;
    }
    
    /**
     * (get受渡日)<BR>
     * 引数.約定日に対する受渡日を返す。<BR>
     * <BR>
     * １）　@拡張投信取引銘柄オブジェクトを取得する。<BR>
     * 　@this.get投信取引銘柄()をコールして、<BR>
     * 拡張投信取引銘柄オブジェクトを取得する。<BR>
     * 　@［get投信取引銘柄に渡すパラメタ］<BR>
     * 　@　@証券会社： 引数.証券会社<BR>
     * 　@　@銘柄コード： 引数.銘柄コード<BR>
     * <BR>
     * ２）　@受渡日を取得する。<BR>
     * 　@拡張投信取引銘柄.get受渡日()をコールして、受渡日を取得する。<BR>
     * 　@［get受渡日に渡すパラメタ］<BR>
     * 　@　@is買付： 引数.is買付<BR>
     *     約定日： 引数.約定日<BR>
     * <BR>
     * ３）  拡張投信銘柄を取得する。<BR>
     * 　@　@　@this．get投信銘柄()<BR>
     * 　@　@　@[get投信銘柄()の引数]<BR>
     * 　@　@　@証券会社：引数.証券会社<BR>
     * 　@　@　@銘柄コード：引数.銘柄コード<BR>
     * <BR>
     * ４）  拡張投信銘柄.is外貨MMF = true の場合<BR>
     * 　@　@　@（取引時間管理.get投信発注日()と同じく、海外発注日を取得する）<BR>
     * <BR>
     * 　@　@４-１）　@取得した受渡日を引数に、投信海外市場カレンダ.is休日()をコールする。<BR>
     * <BR>
     * 　@　@　@　@４-１-１）　@is休日() = trueの場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@①@　@発注日計算を用いて翌営業日を取得する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@②　@取得した翌営業日を引数に、投信海外市場カレンダ.is休日()をコールする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@③　@is休日() = trueの場合、①@へ戻る<BR>
     * 　@　@　@　@　@　@　@　@　@　@④　@is休日() = falseの場合、取得した翌営業日を返す。<BR>
     * <BR>
     * 　@　@　@　@４-１-２）　@is休日() = falseの場合、取得した受渡日を返す。<BR>
     * <BR>
     * ５）  拡張投信銘柄.is外貨MMF = false の場合、取得した受渡日を返す。<BR>
     * @@param l_institution - 証券会社<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_blnIsAcquired - (is買付)<BR>
     * @@param l_datSwtExecutedDate - 約定日<BR>
     * 買付の場合はtrueを、解約の場合はfalseを指定する<BR>
     * @@return Timestamp
     * @@roseuid 40B44D83037A
     */
    public Timestamp getDeliveryDate(
        Institution l_institution,
        String l_strProductCode,
        boolean l_blnIsAcquired,
        Date l_datSwtExecutedDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryDate("
                + "Institution l_institution,"
                + "String l_strProductCode,"
                + "boolean l_blnIsAcquired)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Timestamp l_deliveryDate = null;
        Date l_datDeliveryDate = null;
        try
        {
            //１）　@拡張投信取引銘柄オブジェクトを取得する
            WEB3MutualFundTradedProduct l_web3MutualFundTradedProduct =
                (WEB3MutualFundTradedProduct) this.getMutualFundTradedProduct(
                    l_institution,
                    l_strProductCode);

            //２）　@受渡日を取得する
            l_datDeliveryDate =
                l_web3MutualFundTradedProduct.getDeliveryDate(
                    l_blnIsAcquired, l_datSwtExecutedDate);
            l_deliveryDate = new Timestamp(l_datDeliveryDate.getTime());

            //３）  拡張投信銘柄を取得する。   
            //      this．get投信銘柄() 
            //      [get投信銘柄()の引数] 
            //      証券会社：引数.証券会社 
            //      銘柄コード：引数.銘柄コード 
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct)this.getMutualFundProduct(l_institution,l_strProductCode);

            //４）  拡張投信銘柄.is外貨MMF = true の場合
            //　@　@　@（取引時間管理.get投信発注日()と同じく、海外発注日を取得する）
            if (l_mutualFundProduct.isFrgnMmf())
            {
                //　@　@４-１）　@取得した受渡日を引数に、投信海外市場カレンダ.is休日()をコールする。
                WEB3AdminMutualFrgncal l_adminMutualFrgncal =
                    new WEB3AdminMutualFrgncal();
                boolean l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                    l_institution.getInstitutionCode(),
                    l_strProductCode,
                    l_deliveryDate);

                //４-１-１）　@is休日() = trueの場合
                //　@　@　@　@①@　@発注日計算を用いて翌営業日を取得する。
                //　@　@　@　@②　@取得した翌営業日を引数に、投信海外市場カレンダ.is休日()をコールする。
                //　@　@　@　@③　@is休日() = trueの場合、①@へ戻る
                //　@　@　@　@④　@is休日() = falseの場合、取得した翌営業日を返す。
                if (l_blnIsHoliday)
                {
                    while (l_blnIsHoliday)
                    {
                        l_deliveryDate =
                            new WEB3GentradeBizDate(l_deliveryDate).roll(1);
                        l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                            l_institution.getInstitutionCode(),
                            l_strProductCode,
                            l_deliveryDate);
                    }
                }

                //４-１-２）　@is休日() = falseの場合、取得した受渡日を返す。
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_deliveryDate;
                }
            }

            //５）  拡張投信銘柄.is外貨MMF = false の場合、取得した受渡日を返す。
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_deliveryDate;
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("拡張投信取引銘柄オブジェクトを取得するできない for："
                      + "this.getMutualFundTradedProduct() "
                      + "l_institution = " + l_institution.getInstitutionId()
                      + "l_strProductCode = " + l_strProductCode);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }

        return l_deliveryDate;
    }
    
    /**
     * (get定時定額買付可能銘柄リスト)<BR>
     * 定時定額買付可能な銘柄のリストを返す。<BR> 
     * <BR>
     * １）　@投信銘柄マスターテーブルを検索し、定時定額買付可能な銘柄の投信銘柄Params <BR>
     * 　@　@オブジェクトのListを取得する。 <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@定時定額買付可能区分 = 　@1：定時定額買付可　@and<BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード and<BR>
     * 　@　@　@買付開始日(YYYYMMDDHH24MISS)　@<= 現在日時(YYYYMMDDHH24MISS)　@and<BR>
     * 　@　@　@現在日時(YYYYMMDD)　@<= 買付終了日(YYYYMMDD)<BR>
     * <BR>
     * ※現在日時＝ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。<BR>
     * <BR>
     * <BR>
     * 　@　@［ソート条件］<BR> 
     * 　@　@　@表示順序の昇順＞カテゴリーコードの昇順＞銘柄コードの昇順<BR> 
     * 　@ <BR>
     * ２）　@投信銘柄ParamsオブジェクトのListの件数分以下の処理を行う。<BR> 
     * 　@　@－拡張投信銘柄オブジェクトを生成する。 <BR>
     * 　@　@　@［コンストラクタに渡すパラメタ］ <BR>
     * 　@　@　@　@銘柄Row： 投信銘柄Params <BR>
     * 　@　@－生成した拡張投信銘柄を、拡張投信銘柄のListに追加する。<BR> 
     * <BR>
     * ３）　@拡張投信銘柄のListをリターンする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@return List
     * @@throws WEB3BaseException 
     */
    public List getFixedBuyPossibleProductList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMutualFixedBuyPossibleDivProductList(String)";
        log.entering(STR_METHOD_NAME); 
        
        //１）　@投信銘柄マスターテーブルを検索し、定時定額買付可能な銘柄の投信銘柄Params
        //オブジェクトのListを取得する。
        List l_lisRows = null;

        //現在日付の取得
        //ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
        String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
        String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

        //定時定額買付可能区分 = 　@1：定時定額買付可　@and
        //証券会社コード = 引数.証券会社コード
        //買付開始日(YYYYMMDDHH24MISS)　@<= 現在日時(YYYYMMDDHH24MISS)　@and
        //現在日時(YYYYMMDD)　@<= 買付終了日(YYYYMMDD)
        String l_strQueryString = "fixed_buy_possible_div = ? and institution_code = ? and " +
            "(to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? and to_char(buy_end_date, 'YYYYMMDD') >= ?)";

        Object[] l_objQueryDataContainer =
            {WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE,
            l_strInstitutionCode,
            l_strFormatTime,
            l_strFormatDate};

        //表示順序の昇順＞カテゴリーコードの昇順＞銘柄コードの昇順
        String l_strSortCond = "indication_ranking, category_code, product_code";
        try 
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRows =
                l_QueryProcessor.doFindAllQuery(
                    MutualFundProductRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_objQueryDataContainer);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //２）　@投信銘柄ParamsオブジェクトのListの件数分以下の処理を行う。
        List l_lisMutualFundProducts = new ArrayList();
        
        if (l_lisRows != null && l_lisRows.size() > 0)
        {
            int l_intLength = l_lisRows.size();
            try 
            {
                for (int i = 0; i < l_intLength; i++) 
                {
                    WEB3MutualFundProduct l_mutualFundProduct = 
                        new WEB3MutualFundProduct((MutualFundProductRow)l_lisRows.get(i));
                    
                    //生成した拡張投信銘柄を、拡張投信銘柄のListに追加する
                    l_lisMutualFundProducts.add(l_mutualFundProduct);
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //３）　@拡張投信銘柄のListをリターンする。
        log.exiting(STR_METHOD_NAME);
        return l_lisMutualFundProducts;
    }

    /**
     * (get投信銘柄)<BR>
     * 投信銘柄を取得する。<BR>
     * <BR>
     * 1）投信銘柄マスターテーブルを検索し、投信銘柄Paramsオブジェクトを返す。<BR>
     * [検索条件]<BR>
     * 証券会社コード＝引数．証券会社コード　@   and<BR>
     * カテゴリーコード＝引数．投信銘柄カテゴリーコード<BR>
     * <BR>
     * ※検索結果が0件の場合、nullを返す。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strCategoryCode - (カテゴリーコード)<BR>
     * カテゴリーコード<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getMutualFundProduct(
        String l_strInstitutionCode,
        String l_strCategoryCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualFundProduct(String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRows = null;
        //投信銘柄マスターテーブルを検索し、投信銘柄Paramsオブジェクトを返す。
        //[検索条件]
        //証券会社コード＝引数．証券会社コード　@   and
        //カテゴリーコード＝引数．投信銘柄カテゴリーコード
        String l_strQueryString = " institution_code = ? and category_code = ? ";

        Object[] l_objQueryDataContainers = {l_strInstitutionCode, l_strCategoryCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                MutualFundProductRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainers);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //※検索結果が0件の場合、nullを返す。
        if (l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisRows;
    }

    /**
     * (get上位投信銘柄カテゴリーコード)<BR>
     * 指定されたカテゴリーコードから、上位カテゴリーコードを取得する。<BR>
     * <BR>
     * 1）投信銘柄カテゴリーテーブルを検索し、投信銘柄カテゴリーParamsを取得する。<BR>
     * 　@［条件］<BR>
     * 　@証券会社コード = 引数．証券会社コード<BR>
     * 　@カテゴリーコード = 引数．カテゴリーコード<BR>
     * <BR>
     * 2)取得した投信銘柄カテゴリーParams．get親カテゴリーコードがnullの場合<BR>
     * 　@2-1）取得した投信銘柄カテゴリーParams．getカテゴリーコードをリターンする。<BR>
     * <BR>
     * 3）投信銘柄カテゴリーテーブルを検索し、投信銘柄カテゴリーParamsを取得する。<BR>
     * 　@［条件］<BR>
     * 　@証券会社コード = 引数．証券会社コード<BR>
     * 　@カテゴリーコード = 取得した投信銘柄カテゴリーParams．get親カテゴリーコード<BR>
     * <BR>
     * 4）) 3）で取得した投信銘柄カテゴリーParams．get親カテゴリーコードがnullの場合<BR>
     * 　@4）-1) getカテゴリコードをリターンする。<BR>
     * <BR>
     * 5）) 3）で取得した投信銘柄カテゴリーParams．get親カテゴリーコードがnull以外の場合<BR>
     * 　@5）-1) get親カテゴリコードをリターンする。<BR>
     * @@param l_strCategoryCode - (カテゴリーコード)<BR>
     * カテゴリーコード<BR>
     * @@param l_strInstitutionCode - (会社コード)<BR>
     * 会社コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getUpMutualFundProductCategoryCode(
        String l_strCategoryCode,
        String l_strInstitutionCode)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUpMutualFundProductCategoryCode(String, String)";
        log.entering(STR_METHOD_NAME);

        //1）投信銘柄カテゴリーテーブルを検索し、投信銘柄カテゴリーParamsを取得する。
        //［条件］
        //証券会社コード = 引数．証券会社コード
        //カテゴリーコード = 引数．カテゴリーコード
        MutualFundProductCategoryParams l_mutualFundProductCategoryParams = null;
        MutualFundProductCategoryRow l_mutualFundProductCategoryRow = null;
        try
        {
            MutualFundProductCategoryPK l_mutualFundProductCategoryPK =
                new MutualFundProductCategoryPK(l_strInstitutionCode, l_strCategoryCode);
            l_mutualFundProductCategoryRow =
                MutualFundProductCategoryDao.findRowByPk(l_mutualFundProductCategoryPK);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_mutualFundProductCategoryParams =
            new MutualFundProductCategoryParams(l_mutualFundProductCategoryRow);

        //2)取得した投信銘柄カテゴリーParams．get親カテゴリーコードがnullの場合
        String l_strParentCategoryCode =
            l_mutualFundProductCategoryParams.getParentCategoryCode();
        if (WEB3StringTypeUtility.isEmpty(l_strParentCategoryCode))
        {
            //2-1）取得した投信銘柄カテゴリーParams．getカテゴリーコードをリターンする。
            log.exiting(STR_METHOD_NAME);
            return l_mutualFundProductCategoryParams.getCategoryCode();
        }

        //3）投信銘柄カテゴリーテーブルを検索し、投信銘柄カテゴリーParamsを取得する。
        //［条件］
        //証券会社コード = 引数．証券会社コード
        //カテゴリーコード = 取得した投信銘柄カテゴリーParams．get親カテゴリーコード
        try
        {
            MutualFundProductCategoryPK l_mutualFundProductCategoryPK =
                new MutualFundProductCategoryPK(l_strInstitutionCode, l_strParentCategoryCode);
            l_mutualFundProductCategoryRow =
                MutualFundProductCategoryDao.findRowByPk(l_mutualFundProductCategoryPK);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_mutualFundProductCategoryParams =
            new MutualFundProductCategoryParams(l_mutualFundProductCategoryRow);

        //4）) 3）で取得した投信銘柄カテゴリーParams．get親カテゴリーコードがnullの場合
        l_strParentCategoryCode =
            l_mutualFundProductCategoryParams.getParentCategoryCode();
        if (WEB3StringTypeUtility.isEmpty(l_strParentCategoryCode))
        {
            //4）-1) getカテゴリコードをリターンする。
            log.exiting(STR_METHOD_NAME);
            return l_mutualFundProductCategoryParams.getCategoryCode();
        }
        //5）) 3）で取得した投信銘柄カテゴリーParams．get親カテゴリーコードがnull以外の場合
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strParentCategoryCode;
        }
    }

    /**
     * (get定時定額買付可能銘柄リスト)<BR>
     * 定時定額買付可能な銘柄のリストを返す。<BR>
     * <BR>
     * １）　@投信銘柄マスターテーブルを検索し、定時定額買付可能な銘柄の投信銘柄Params<BR>
     * 　@　@オブジェクトのListを取得する。<BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@定時定額買付可能区分 = 　@1：定時定額買付可　@and<BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード and<BR>
     * 　@　@　@買付開始日(YYYYMMDDHH24MISS)　@<= 現在日時(YYYYMMDDHH24MISS)　@and<BR>
     * 　@　@　@現在日時(YYYYMMDD)　@<= 買付終了日(YYYYMMDD) and<BR>
     * 　@　@　@作成した銘柄コード文字列 and(※１)<BR>
     * 　@　@　@作成したカテゴリコード文字列(※２)<BR>
     * <BR>
     * ※現在日時＝ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、<BR>
     * 　@　@現在日時を取得する。<BR>
     * <BR>
     * 　@　@［ソート条件］<BR>
     * 　@　@　@表示順序の昇順＞カテゴリーコードの昇順＞銘柄コードの昇順<BR>
     * <BR>
     * ※１．引数.銘柄コード一覧==null以外の場合、<BR>
     * 　@　@銘柄コード一覧でループし「銘柄コード in (銘柄コードの値,銘柄コードの値) and」の文字列を作成する。<BR>
     * ※２．引数.カテゴリーリスト==null以外の場合、<BR>
     * 　@　@カテゴリーリストでループし「カテゴリコード in (カテゴリコードの値,カテゴリコードの値)」<BR>
     * 　@　@の文字列を作成する。<BR>
     * <BR>
     * ２）　@投信銘柄ParamsオブジェクトのListの件数分以下の処理を行う。<BR>
     * 　@　@－拡張投信銘柄オブジェクトを生成する。<BR>
     * 　@　@　@［コンストラクタに渡すパラメタ］<BR>
     * 　@　@　@　@銘柄Row： 投信銘柄Params<BR>
     * 　@　@－生成した拡張投信銘柄を、拡張投信銘柄のListに追加する。<BR>
     * <BR>
     * ３）　@拡張投信銘柄のListをリターンする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strProductCodes - (銘柄コード一覧)<BR>
     * 銘柄コード一覧<BR>
     * @@param l_lisCategoryList - (カテゴリーリスト)<BR>
     * カテゴリーリスト<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getFixedBuyPossibleProductList(
        String l_strInstitutionCode,
        String[] l_strProductCodes,
        List l_lisCategoryList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFixedBuyPossibleProductList"
            +"(String, String[], List)";
        log.entering(STR_METHOD_NAME);

        //１）　@投信銘柄マスターテーブルを検索し、定時定額買付可能な銘柄の投信銘柄Params
        //オブジェクトのListを取得する。
        List l_lisRows = null;

        //［検索条件］
        //定時定額買付可能区分 = 　@1：定時定額買付可　@and
        //証券会社コード = 引数.証券会社コード and
        //買付開始日(YYYYMMDDHH24MISS)　@<= 現在日時(YYYYMMDDHH24MISS)　@and
        //現在日時(YYYYMMDD)　@<= 買付終了日(YYYYMMDD) and
        String l_strQueryString = " fixed_buy_possible_div = ? "
            + "and institution_code = ? "
            + "and (to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? "
            + "and to_char(buy_end_date, 'YYYYMMDD') >= ?) ";

        //作成した銘柄コード文字列 and(※１)
        //※１．引数.銘柄コード一覧==null以外の場合、銘柄コード一覧でループし「銘柄コード in
        //(銘柄コードの値,銘柄コードの値) and」の文字列を作成する。
        if (l_strProductCodes != null && l_strProductCodes.length > 0)
        {
            l_strQueryString = l_strQueryString + " and product_code in ( ";
            int l_intLength = l_strProductCodes.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_strQueryString = l_strQueryString + l_strProductCodes[i];
                if (i != (l_intLength - 1))
                {
                    l_strQueryString = l_strQueryString + ",";
                }
            }
            l_strQueryString = l_strQueryString + ") ";
        }
        //作成したカテゴリコード文字列 (※２)
        //※２．引数.カテゴリーリスト==null以外の場合、カテゴリーリストでループし「カテゴリコード in
        //(カテゴリコードの値,カテゴリコードの値)」の文字列を作成する。
        if (l_lisCategoryList != null && l_lisCategoryList.size() > 0)
        {
            l_strQueryString = l_strQueryString + " and category_code in ( ";
            int l_intSize = l_lisCategoryList.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //障害3098
                MutualFundProductCategoryRow l_mutualFundProductCategoryRow =
                    (MutualFundProductCategoryRow)l_lisCategoryList.get(i);
                l_strQueryString = l_strQueryString + l_mutualFundProductCategoryRow.getCategoryCode();
                if (i != (l_intSize - 1))
                {
                    l_strQueryString = l_strQueryString + ",";
                }
            }
            l_strQueryString = l_strQueryString + ") ";
        }

        //※現在日時＝ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
        String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
        String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

        //［ソート条件］
        //表示順序の昇順＞カテゴリーコードの昇順＞銘柄コードの昇順
        String l_strSortCond = " indication_ranking, category_code, product_code ";

        Object[] l_objQueryDataContainer =
        {WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE,
        l_strInstitutionCode,
        l_strFormatTime,
        l_strFormatDate};

        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRows =
                l_QueryProcessor.doFindAllQuery(
                    MutualFundProductRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_objQueryDataContainer);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@投信銘柄ParamsオブジェクトのListの件数分以下の処理を行う。
        //－拡張投信銘柄オブジェクトを生成する。
        //［コンストラクタに渡すパラメタ］
        //銘柄Row： 投信銘柄Params
        //－生成した拡張投信銘柄を、拡張投信銘柄のListに追加する。
        List l_lisMutualFundProducts = new ArrayList();

        if (l_lisRows != null && l_lisRows.size() > 0)
        {
            int l_intLength = l_lisRows.size();
            try
            {
                for (int i = 0; i < l_intLength; i++)
                {
                    WEB3MutualFundProduct l_mutualFundProduct =
                        new WEB3MutualFundProduct((MutualFundProductRow)l_lisRows.get(i));

                    l_lisMutualFundProducts.add(l_mutualFundProduct);
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //３）　@拡張投信銘柄のListをリターンする。
        log.exiting(STR_METHOD_NAME);
        return l_lisMutualFundProducts;
    }
}
@
