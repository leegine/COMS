head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontNoticeHistoryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者通知履歴参照サービスImpl(WEB3AdminFrontNoticeHistoryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27  王明明 (中訊) 仕様変更モデルNo.122
Revision History : 2007/02/27  王明明 (中訊) 仕様変更モデルNo.123
Revision History : 2007/02/27  王明明 (中訊) 仕様変更モデルNo.125
Revision History : 2007/06/21  謝旋 (中訊) 仕様変更実装の問題No.005
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.define.WEB3AdminFrontMarketKeyItemDef;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontMarketNoticeHistoryUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontSortKey;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontNoticeHistoryService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.equity.data.MarketNoticeManagementRow;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 管理者通知履歴参照サービス実装クラス<BR>
 */
public class WEB3AdminFrontNoticeHistoryServiceImpl implements WEB3AdminFrontNoticeHistoryService 
{
	/**
	 * Log Variable.<BR>
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3AdminFrontNoticeHistoryServiceImpl.class);

   /**
    * @@roseuid 43001B0903AC
    */
   public WEB3AdminFrontNoticeHistoryServiceImpl() 
   {
    
   }
   
   /**
    * 管理者通知履歴参照サービスを行う。<BR>
    * <BR>
    * リクエストデータの型により以下のメソッドを<BR>
    * 呼び分ける。<BR>
    * <BR>
    * リクエストデータが、<BR>
    * 　@[管理者・通知履歴参照入力リクエストの場合]<BR>
    * 　@　@this.get入力画面()をコールする。<BR>
    * <BR>
    * 　@[管理者・通知履歴参照リクエストの場合]<BR>
    * 　@　@this.get参照画面()をコールする。<BR>
    * @@param l_request - リクエスト<BR>
    * @@return WEB3GenResponse<BR>
    * @@throws WEB3BaseException<BR>
    * @@roseuid 42D2158701C9
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
   {
		final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
		log.entering(STR_METHOD_NAME);
	
		WEB3GenResponse l_response = null;
	
//		try 
//		{
			//get入力画面
			if (l_request instanceof WEB3AdminFrontNoticeHistoryInputRequest) {
				l_response =
					this.getInputScreen(
						(WEB3AdminFrontNoticeHistoryInputRequest) l_request);
	
			//get参照画面
			} else if (
				l_request instanceof WEB3AdminFrontNoticeHistoryReferenceRequest) {
				l_response =
					this.getReferenceScreen(
						(WEB3AdminFrontNoticeHistoryReferenceRequest) l_request);
	
			//リクエストデータが上記２つ以外のときはエラー
			} else 
			{
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80017,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"INPUT リクエスト NOT 管理者・通知履歴参照入力リクエスト、管理者・通知履歴参照リクエスト");
			}

//		}
//		catch (WEB3BusinessLayerException l_bussinesException)
//		{
//			log.error(l_bussinesException.getMessage(), l_bussinesException);
//			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//				this.getClass().getName() + STR_METHOD_NAME,l_bussinesException.getMessage(),l_bussinesException);
//		} 
		
		/*
		catch (DataQueryException l_dqex)
		{
			String l_strMsg = "Error while aquiring the Data ";
			log.error(l_strMsg, l_dqex);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,l_dqex.toString(),l_dqex);

		} 
		catch (DataNetworkException l_dnex)
		{
			String l_strMsg = "Error while aquiring the Data ";
			log.error(l_strMsg, l_dnex);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,l_dnex.toString(),l_dnex);
		}
		*/
		
		log.exiting(STR_METHOD_NAME);
		return l_response;
	
   }
   
   /**
    * 管理者通知履歴参照入力画面表示処理を行う。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「（通知履歴参照）get入力画面」参照<BR>
    * @@param リクエストデータ - 管理者・通知履歴参照入力リクエストオブジェクト<BR>
    * @@return 管理者・通知履歴参照入力レスポンス<BR>
    * @@roseuid 42D215D7011D
    */
   protected WEB3AdminFrontNoticeHistoryInputResponse getInputScreen(WEB3AdminFrontNoticeHistoryInputRequest requestdata) 
   throws WEB3BaseException,WEB3BusinessLayerException
   {
		final String STR_METHOD_NAME = "getInputScreen()";
		log.entering(STR_METHOD_NAME);

		WEB3Administrator l_web3Administrator = null;
		
		WEB3AdminFrontOrderCommonService l_adminFrontOrderCommonService = 
		(WEB3AdminFrontOrderCommonService) Services.getService(WEB3AdminFrontOrderCommonService.class);
		
		//ログイン情報取得
		l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

		//管理者の権限チェック
		l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_FRONT_NOTICE_HIST_REF,false);

		//証券会社コード取得
		String institutionCode = l_web3Administrator.getInstitutionCode();

        //get銘柄タイプ一覧(String)
        String[] l_strProductTypeList = this.getProductTypeList(institutionCode);
        
		//レスポンスデータ作成    	
		WEB3AdminFrontNoticeHistoryInputResponse l_response =
			(WEB3AdminFrontNoticeHistoryInputResponse) requestdata.createResponse();

		//市場コード一覧取得
		l_response.convertMarketCodeList = l_adminFrontOrderCommonService.getFindPossibleMarketCode(institutionCode);

        //銘柄タイプ一覧
        l_response.productTypeList = l_strProductTypeList;
        
		//get通知受信日付一覧 
		l_response.createdTimestampList = l_adminFrontOrderCommonService.getNoticeReceivedDateRef();
		log.exiting(STR_METHOD_NAME);
	
		return l_response;
   }
   
	/**
    * 管理者通知履歴参照処理を行う。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「（通知履歴参照）get参照画面」参照<BR>
    * @@param リクエストデータ - 管理者・通知履歴参照リクエストオブジェクト<BR>
    * @@return 管理者・通知履歴参照レスポンス<BR>
    * @@roseuid 42D215D7013C
    */
   protected WEB3AdminFrontNoticeHistoryReferenceResponse getReferenceScreen(WEB3AdminFrontNoticeHistoryReferenceRequest requestdata) 
   throws WEB3BaseException,WEB3BusinessLayerException
   {
		final String STR_METHOD_NAME = "getReferenceScreen()";
		log.entering(STR_METHOD_NAME);
	
		WEB3Administrator l_web3Administrator = null;
		WEB3PageIndexInfo  l_web3PageIndexInfo  = null;
		WEB3AdminFrontSortKey[] l_web3AdminFrontSortKey = null;
		WEB3AdminFrontMarketNoticeHistoryUnit[] l_web3AdminFrontMarketNoticeHistoryUnit = null;
	  	
		WEB3AdminFrontOrderCommonService l_adminFrontOrderCommonService = 
		(WEB3AdminFrontOrderCommonService) Services.getService(WEB3AdminFrontOrderCommonService.class);
		int l_intPageIndex = 0;
		int l_intPageSize = 0;


		//リクエストデータvalidateチェック
		requestdata.validate();
	 
		//ログイン情報取得
		l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

		// 管理者の権限チェック
		l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_FRONT_NOTICE_HIST_REF,false);

		// 証券会社コード取得
		String institutionCode = l_web3Administrator.getInstitutionCode();

		//フロント発注取引所区分コード取得
		String frontOrderTradeCode = l_adminFrontOrderCommonService.getFrontOrderExchangeCode(requestdata.convertMarketCode);

		//フロント発注システム区分取得
		String frontOrderSystemCode = l_adminFrontOrderCommonService.getFrontSystemDiv(requestdata.convertMarketCode);

		//create検索条件文字列( 戻り String )
		String search_Condition_Character_String = createSearchConditionCharacterString(institutionCode, frontOrderTradeCode, frontOrderSystemCode, requestdata); 

		//create検索条件データコンテナ( 戻り String[] )
//		String[] search_Condition_Data_Container = (String[]) createSearchConditionDataContainer(institutionCode, frontOrderTradeCode, frontOrderSystemCode, requestdata);
        Object[] search_Condition_Data_Container = createSearchConditionDataContainer(institutionCode, frontOrderTradeCode, frontOrderSystemCode, requestdata);

  		l_web3AdminFrontSortKey = requestdata.sortKeys;

		//createソート条件(WEB3AdminFrontSortKey SortKey) ( 戻り String )
		String create_Sort_Condition = createSortCondition(l_web3AdminFrontSortKey);
        
        //get通知履歴一覧( 戻り List ) 
		List Notice_History_List = getNoticeHistoryList(search_Condition_Character_String, search_Condition_Data_Container, create_Sort_Condition, null ,requestdata.pageSize, requestdata.pageIndex);
        
		//create通知履歴参照一覧( 戻り 市場通知履歴明細[])
		l_web3AdminFrontMarketNoticeHistoryUnit = l_adminFrontOrderCommonService.createNoticeHistryRefList(Notice_History_List);
		
		//要求により、リストのページをめくる処理を行う
        // ListPage型にキャスト
        ListPage page = (ListPage)Notice_History_List;
        
//		l_intPageIndex = Integer.parseInt(requestdata.pageIndex);
//		l_intPageSize = Integer.parseInt(requestdata.pageSize);
//		l_web3PageIndexInfo = new WEB3PageIndexInfo(l_web3AdminFrontMarketNoticeHistoryUnit, l_intPageIndex, l_intPageSize);

		//レスポンスデータ作成    	
		WEB3AdminFrontNoticeHistoryReferenceResponse l_response =
			(WEB3AdminFrontNoticeHistoryReferenceResponse) requestdata.createResponse();

		//シーケンス図 get参照画面の1.16-プロパティセットの処理
//		l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getTotalRecords());
//		l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getTotalPages());
//		l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getPageIndex());
        // 総レコード数
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(page.totalSize());
        // 総ページ数
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(page.totalPages());
        // 表示ページ番号
        l_response.pageIndex = requestdata.pageIndex;
        // 明細一覧をセット
		l_response.referenceList = l_web3AdminFrontMarketNoticeHistoryUnit;

		log.exiting(STR_METHOD_NAME);
	
		return l_response;
   }

   /**
    * 検索条件文字列を作成する。<BR>
    * <BR>
    * １）　@検索条件文字列インスタンスを生成する。 <BR>
    * <BR>
    * ２）　@証券会社コードを検索条件文字列に追加する。<BR>
    * <BR>
    * 　@"institution_code = ? "<BR>
    * <BR>
    * ３）　@パラメータ:フロント発注取引所区分コード!=nullの場合、<BR>
    * 　@　@ フロント発注取引所区分コードを検索条件文字列に追加する。<BR>
    * <BR>
    *   検索条件文字列 += "and front_order_exchange_code = ? " <BR>
    * <BR>
    * ４）　@パラメータ:フロント発注システム区分!=nullの場合、<BR>
    * 　@　@ フロント発注システム区分を検索条件文字列に追加する。<BR>
    * <BR>
    *   検索条件文字列 += "and front_order_system_code = ? " <BR>
    * <BR>
    * ５）　@パラメータ:銘柄タイプ != nullの場合、<BR>
    * 　@　@ 銘柄タイプを検索条件文字列に追加する。<BR>
    * <BR>
    *   検索条件文字列 += "and product_type = ? "　@ <BR>
    * <BR>
    * ６）　@パラメータ:データ種別コード!= nullの場合、 <BR>
    *   　@データ種別コードを検索条件文字列に追加する。<BR>
    * <BR>
    *   検索条件文字列 += "and data_class_code = ? " <BR>
    * <BR>
    * ７）　@パラメータ:部店コード!= nullの場合、 <BR>
    *   　@部店コードを検索条件文字列に追加する。<BR>
    * <BR>
    *   検索条件文字列 += "and branch_code = ? " <BR>
    * <BR>
    * ８）　@仮想サーバNo!= nullの場合、 <BR>
    *   　@仮想サーバNoを検索条件文字列に追加する。<BR>
    * <BR>
    *   検索条件文字列 += "and virtual_server_number_market = ? " <BR>
    * <BR>
    * ９）　@口座コード!= nullの場合、 <BR>
    *   　@口座コードを検索条件文字列に追加する。<BR>
    * <BR>
    *   検索条件文字列 += "and SUBSTR（account_code,1,6） = ? " <BR>
    * <BR>
    * １０）　@パラメータ.銘柄コード != nullの場合、 <BR>
    * 　@　@銘柄コードを検索条件文字列に追加する。 <BR>
    * <BR>
    * 　@検索条件文字列 += "and product_code = ? " <BR>
    * <BR>
	* １１）　@パラメータ.通知受信日付 != nullの場合、<BR> 
	*　@　@　@ 通知受信日付を検索条件文字列に追加する。<BR> 
	*
	*　@ 検索条件文字列 += "and TO_CHAR(created_timestamp,'YYYYMMDD') = ? " <BR>
    * <BR>
    * １２）　@パラメータ.通知受信時刻From != nullの場合、 <BR>
    * 　@　@　@通知受信時刻Fromを検索条件文字列に追加する。 <BR>
    * <BR>
    * 　@検索条件文字列 += "and TO_CHAR(created_timestamp,'HH24MISS') >= ? " <BR>
    * <BR>
    * １３）　@パラメータ.通知受信時刻Ｔｏ != nullの場合、 <BR>
    * 　@　@　@通知受信時刻Toを検索条件文字列に追加する。 <BR>
    * <BR>
    * 　@検索条件文字列 += "and TO_CHAR(created_timestamp,'HH24MISS') <= ? " <BR>
    * <BR>
    * １４）　@作成した検索条件文字列インスタンスを返却する。<BR>
    * @@param 証券会社コード - 証券会社コード<BR>
    * @@param フロント発注取引所区分コード - <BR>
    * getフロント発注取引所区分コードで、取得した値。<BR>
    * @@param フロント発注システム区分 - getフロント発注システム区分で、取得した値。<BR>
    * @@param リクエストデータ - 管理者・通知履歴参照リクエストオブジェクト<BR>
    * @@return String<BR>
    * @@roseuid 42D6087E0387
    */
   protected String createSearchConditionCharacterString(String institutionCode, String frontOrderTradeCode, String frontOrderSystemCode, WEB3AdminFrontNoticeHistoryReferenceRequest requestdata) 
   {
		final String STR_METHOD_NAME = "createSearchConditionCharacterString(String, String, String, WEB3AdminFrontNoticeHistoryReferenceRequest)";
		log.entering(STR_METHOD_NAME);
	
		//検索条件文字列パラメータインスタンス作成
		StringBuffer l_sbQueryCond = new StringBuffer();
	
		// 証券会社コードを検索条件文字列に追加
		l_sbQueryCond.append("institution_code = ? ");
	
		// フロント発注取引所区分コードチェック frontOrderTradeCode is not null
		if (frontOrderTradeCode != null)
		{
			l_sbQueryCond.append("and front_order_exchange_code = ? ");
		}
	
		// フロント発注システム区分チェック frontOrderSystemCode is not null
		if (frontOrderSystemCode != null)
		{
			l_sbQueryCond.append("and front_order_system_code = ? ");
		}

        // 銘柄タイプチェック productType is not null
        if (requestdata.productType != null)
        {
            l_sbQueryCond.append("and product_type = ? ");
        }
        
		// データ種別コードチェック dataClassCode is not null
		if (requestdata.dataClassCode != null)
		{
			l_sbQueryCond.append("and data_class_code = ? ");
		}

		// 部店コードチェック branchCode is not null
		if (requestdata.branchCode != null)
		{
			l_sbQueryCond.append("and branch_code = ? ");
		}

		// 仮想サーバＮｏチェック virtualServerNumber is not null
		if (requestdata.virtualServerNumber != null)
		{
			l_sbQueryCond.append("and virtual_server_number_market = ? ");
		}

		// 口座コードチェック accountCode is not null
		if (requestdata.accountCode != null)
		{
			l_sbQueryCond.append("and SUBSTR(account_code,1,6) = ? ");
		}

		// 銘柄コードチェック productCode is not null
		if (requestdata.productCode != null)
		{
			l_sbQueryCond.append("and product_code = ? ");
		}

		// 通信受信日付チェック createdTimestamp is not null
		if (requestdata.createdTimestamp != null)
		{
			//l_sbQueryCond.append("and createdTimestamp = ? ");
			l_sbQueryCond.append("and TO_CHAR(created_Timestamp,'YYYYMMDD')= ? ");
		}

		// 通信受信時刻Fromチェック createdTimestampFrom is not null
		if (requestdata.createdTimestampFrom != null)
		{
			l_sbQueryCond.append("and TO_CHAR(created_timestamp,'HH24MISS') >= ? ");
		}

		// 通信受信時刻Toチェック createdTimestampTo is not null
		if (requestdata.createdTimestampTo != null)
		{
			l_sbQueryCond.append("and TO_CHAR(created_timestamp,'HH24MISS') <= ? ");
		}

		log.exiting(STR_METHOD_NAME);
 		//作成した検索条件文字列インスタンスを返却する
    	return l_sbQueryCond.toString();
   }
   
   /**
    * 検索条件データコンテナを編集する。<BR>
    * <BR>
    * １）　@検索値を格納するListオブジェクトを生成する。<BR>
    * <BR>
    * ２）　@証券会社コードをListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(証券会社コード);<BR>
    * <BR>
    * ３）　@パラメータ:フロント発注取引所区分コード!=nullの場合、<BR>
    * 　@　@ フロント発注取引所区分コードをListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(フロント発注取引所区分コード);<BR>
    * <BR>
    * ４）　@パラメータ:フロント発注システム区分!=nullの場合、<BR>
    * 　@　@ フロント発注システム区分をListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(フロント発注システム区分);<BR>
    * <BR>
    * ５）　@パラメータ:銘柄タイプ != nullの場合、 <BR>
    * 　@　@ 銘柄タイプをListオブジェクトに追加する。 <BR>
    * <BR>   
    * 　@Listオブジェクト.add(銘柄タイプ); <BR>
    * <BR>
    * ６）　@パラメータ:データ種別コード!= nullの場合、 <BR>
    *   　@ データ種別コードをListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(データ種別コード);<BR>
    * <BR>
    * ７）  パラメータ:部店コード!= nullの場合、 <BR>
    *      部店コードをListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(部店コード);<BR>
    * <BR>
    * ８）  仮想サーバNo!= nullの場合、 <BR>
    *      仮想サーバNoをListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(仮想サーバNo);<BR>
    * <BR>
    * ９）  口座コード!= nullの場合、 <BR>
    *      口座コードをListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(口座コード);<BR>
    * <BR>
    * １０）  パラメータ.銘柄コード != nullの場合、 <BR> 
    * 　@   銘柄コードをListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(銘柄コード);<BR>
    * <BR>
	* １１） パラメータ.通知受信日付 != nullの場合、<BR> 
	*	　@ 　@通知受信日付をListオブジェクトに追加する。<BR> 
    * <BR>	
	*	　@Listオブジェクト.add(通知受信日付);<BR>  
	* <BR>
    * １２）  パラメータ.通知受信時刻From != nullの場合、 <BR>
    * 　@   　@通知受信時刻FromをListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(通知受信時刻From);<BR>
    * <BR>
    * １３）  パラメータ.通知受信時刻Ｔｏ != nullの場合、 <BR>
    *     　@ 通知受信時刻ToをListオブジェクトに追加する。<BR>
    * <BR>
    * 　@Listオブジェクト.add(通知受信時刻To);<BR>
    * <BR>
    * １４）  格納したリストを配列オブジェクトに変換する。<BR>
    * <BR>
    * １５）　@作成した配列オブジェクトを返却する。<BR>
    * @@param 証券会社コード - 証券会社コード<BR>
    * @@param フロント発注取引所区分コード - <BR>
    * getフロント発注取引所区分コードから取得した値。<BR>
    * @@param フロント発注システム区分 - getフロント発注システム区分から取得した値。<BR>
    * @@param リクエストデータ - 管理者・通知履歴参照リクエストオブジェクト<BR>
    * @@return Object[]<BR>
    * @@roseuid 42D62AC70135
    */
   protected Object[] createSearchConditionDataContainer(String institutionCode, String frontOrderTradeCode, String frontOrderSystemCode, WEB3AdminFrontNoticeHistoryReferenceRequest requestdata)
   {
		final String STR_METHOD_NAME =	"createSearchConditionDataContainer(String, String, String, String, WEB3AdminFrontNoticeHistoryReferenceRequest)";
		log.entering(STR_METHOD_NAME);
	
		ArrayList l_arrQueryDataList = new ArrayList();
		Object[] l_queryData = null;

		// 証券会社コードを検索条件データコンテナに追加
		l_arrQueryDataList.add(institutionCode);

		// フロント発注取引所区分コードチェック frontOrderTradeCode is not null
		if (frontOrderTradeCode != null)
		{
			l_arrQueryDataList.add(frontOrderTradeCode);
		}

		// フロント発注システム区分チェック frontOrderSystemCode is not null
		if (frontOrderSystemCode != null)
		{
			l_arrQueryDataList.add(frontOrderSystemCode);
		}
        
        // 銘柄タイプチェック productType is not null
        if (requestdata.productType != null)
        {
            l_arrQueryDataList.add(requestdata.productType);
        }

		// データ種別コードチェック dataClassCode is not null
		if (requestdata.dataClassCode != null)
		{
			l_arrQueryDataList.add(requestdata.dataClassCode);
		}

		// 部店コードチェック branchCode is not null
		if (requestdata.branchCode != null)
		{
			l_arrQueryDataList.add(requestdata.branchCode);
		}

		// 仮想サーバNoチェック virtualServerNumber is not null
		if (requestdata.virtualServerNumber != null)
		{
			l_arrQueryDataList.add(requestdata.virtualServerNumber);
		}

		// 口座コードチェック accountCode is not null
		if (requestdata.accountCode != null)
		{
			l_arrQueryDataList.add(requestdata.accountCode);
		}

		// 銘柄コードチェック productCode is not null
		if (requestdata.productCode != null)
		{
			l_arrQueryDataList.add(requestdata.productCode);
		}

		// 通知受信日付チェック createdTimestamp is not null
		if (requestdata.createdTimestamp != null)
		{
			l_arrQueryDataList.add(requestdata.createdTimestamp);
		}

		// 通知受信時刻Fromチェック createdTimestampFrom is not null
		if (requestdata.createdTimestampFrom != null)
		{
			l_arrQueryDataList.add(requestdata.createdTimestampFrom);
		}
	
		// 通知受信時刻Toチェック createdTimestampTo is not null
		if (requestdata.createdTimestampTo != null)
		{
			l_arrQueryDataList.add(requestdata.createdTimestampTo);
		}

		// 格納したリストを配列オブジェクトに変換する
		l_queryData = l_arrQueryDataList.toArray();
	
		log.exiting(STR_METHOD_NAME);
		// 作成した配列オブジェクトを返却する
		return l_queryData;

   }
   
   /**
    * ソート条件を作成する。 <BR>
    * <BR>
    * 　@１）　@ソート条件文字列(：String)を作成する。<BR>
    * <BR>
    * 　@２）　@パラメータ.ソートキー == nullの場合、<BR>
    * <BR>
    * 　@　@"作成日付、データ種別コード 昇順"のソート条件を返却する。<BR>
    * <BR>
    * 　@３）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
    * <BR>
    * 　@３－１）　@ソートキー.キー項目を対応する列物理名に変換し、作成したソート条件文字列に追加する。<BR>
    * <BR>
    * 　@・「通知受信日時」　@→　@市場通知管理.作成日時<BR>
    * 　@・「データ種別コード」　@→　@市場通知管理.データ種別コード<BR>
    * 　@・「部店コード」　@→　@市場通知管理.部店コード<BR>
    * 　@・「銘柄コード」　@→　@市場通知管理.銘柄コード<BR>
    * 　@・「口座コード」　@→　@市場通知管理.口座コード<BR>
    * 　@・「仮想サーバNo」　@→　@市場通知管理.仮想サーバNo<BR>
    * <BR>
    * 　@３－２）　@ソートキー.昇順／降順に対応する取得順序(asc or <BR>
    * desc)をソート条件文字列に追加する。<BR>
    * <BR>
    * 　@４）　@作成したソート条件文字列を返却する。<BR>
    * @@param ソートキー - 通知履歴参照ソートキー<BR>
    * @@return String<BR>
    * @@roseuid 42D749C900A5
    */
   protected String createSortCondition(WEB3AdminFrontSortKey[] SortKey) 
   {
		final String STR_METHOD_NAME = "createSortCondition()";
		log.entering(STR_METHOD_NAME);
		int i = 0;
	
		StringBuffer l_sortCondList = new StringBuffer();
		String sortMoji = null;
		
		//ソート条件文字列(：String)を作成する
		
		//パラメータ.ソートキー == nullの場合
		if(SortKey == null){
			//"作成日付、データ種別コード 昇順"のソート条件を返却する
			sortMoji = "created_timestamp,data_class_code asc";
			log.exiting(STR_METHOD_NAME);
			return sortMoji;
				
		}else{

			//要素数
			int l_intLen = SortKey.length;
			
			//パラメータ.ソートキーの要素数分以下の処理を繰り返す
			for (i = 0; i < l_intLen; i++)
			{
				 //If sortCondList is not empty(""), add ","(comma) to sortCondList before processing
				 if (!l_sortCondList.toString().equals(""))
				 {
					l_sortCondList.append(", ");
				 }

				 //ソートキー.キー項目を対応する列物理名に変換し、作成したソート条件文字列に追加する
                if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.CREATED_TIMESTAMP))
                {
                   //「通知受信日時」　@→　@市場通知管理.通知受信日時
                   l_sortCondList.append("created_timestamp");
                }

				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.DATA_CLASS_CODE))
				 {
				 	//「データ種別コード」　@→　@市場通知管理.データ種別コード
				    l_sortCondList.append("data_class_code");
				 }

				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.BRANCH_CODE))
				 {
				 	//「部店コード」　@→　@市場通知管理.部店コード
					l_sortCondList.append("branch_code");
				 }
	
				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.PRODUCT_CODE))
				 {
				    //「銘柄コード」　@→　@市場通知管理.銘柄コード
				    l_sortCondList.append("product_code");
				 }

				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.ACCOUNT_CODE))
				 {
				 	//「口座コード」　@→　@市場通知管理.口座コード
					l_sortCondList.append("account_code");
				 }
	
				 if (SortKey[i].keyItem.equals(WEB3AdminFrontMarketKeyItemDef.VIRTUAL_SERVER_NUMBER))
				 {
				 	//「仮想サーバNo」　@→　@市場通知管理.仮想サーバNo
					l_sortCondList.append("virtual_server_number_market");
				 }
	
				 //ソートキー.昇順／降順に対応する取得順序(asc or desc)をソート条件文字列に追加する
				 if (SortKey[i].ascDesc.equals(WEB3AscDescDef.ASC))
				 {
					l_sortCondList.append(" asc");
				 }

				 if (SortKey[i].ascDesc.equals(WEB3AscDescDef.DESC))
				 {
					l_sortCondList.append(" desc");
				 }

			}	// for END
			
			//作成したソート条件文字列を返却する	
			log.exiting(STR_METHOD_NAME);
			return l_sortCondList.toString();
		}
   }
   
   /**
    * 引数の条件に該当する通知履歴の一覧を返却する。 <BR>
    * <BR>
    * １）QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
    * <BR>
    * 　@[doFindAllQuery()にセットするパラメータ] <BR>
    * 　@　@arg0：　@市場通知管理Row.TYPE <BR>
    * 　@　@arg1：　@パラメータ.検索条件文字列 <BR>
    * 　@　@arg2：　@パラメータ.ソート条件 <BR>
    * 　@　@arg3：　@null <BR>
    * 　@　@arg4：　@パラメータ.検索条件データコンテナ <BR>
    * 　@　@arg5：　@パラメータ.ページ内表示行数 <BR>
    * 　@　@arg6：　@パラメータ.表示ページ番号 <BR>
    * <BR>
    * 　@検索結果が取得できなかった場合、エラー処理を行う。 <BR>
    * <BR>
    * <BR>
    * ２）検索結果を返却する。<BR>
    * @@param 検索条件文字列 - 検索条件文字列<BR>
    * @@param 検索条件データコンテナ - 検索条件データコンテナ<BR>
    * @@param ソート条件 - ソート条件<BR>
    * @@param クエリ条件文字列<BR>
    * @@param ページ内表示行数 - ページ内に表示する行数。<BR>
    * @@param 表示ページ番号 - 現ページ番号。<BR>
    * @@return List<BR>
    * @@roseuid 42D75B690367
    */
// protected List getNoticeHistoryList(String searchConditionCharacterString, String[] searchConditionDataContainer, String sortCondition, String query,String pageSize, String pageIndex)
   protected List getNoticeHistoryList(String searchConditionCharacterString, Object[] searchConditionDataContainer, String sortCondition, String query,String pageSize, String pageIndex) 
   throws WEB3SystemLayerException 
   {
		final String STR_METHOD_NAME = "getNoticeHistoryList()";
		log.entering(STR_METHOD_NAME);
		
		int i_pageSize = Integer.parseInt(pageSize);
		int i_pageIndex = Integer.parseInt(pageIndex)-1;
		
		// ArrayListオブジェクトの生成
		List noticeHistoryList = new ArrayList(); 

		try
		{
			//QueryProcessor.doFindAllQuery()メソッドをコールする
			QueryProcessor queryProcessor = Processors.getDefaultProcessor();

			noticeHistoryList = queryProcessor.doFindAllQuery(
				MarketNoticeManagementRow.TYPE,
				searchConditionCharacterString,
				sortCondition,
				null,
				searchConditionDataContainer,
				i_pageSize,
				i_pageIndex
			);
   	
		}
		catch (DataException de)
		{
			//検索結果が取得できなかった場合、エラー処理を行う
			log.error(STR_METHOD_NAME, de);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
			this.getClass().getName() + "." + STR_METHOD_NAME,de.getMessage(),de);
		}
   	
		//検索結果を返却する
   		return noticeHistoryList;
   }
   
   /**
    * (get銘柄タイプ一覧)<BR>
    * 市場通知管理テーブルの検索に適した銘柄タイプを取得する。<BR>
    * <BR>
    * １）　@ArrayListオブジェクトを生成する。 <BR>
    * <BR>
    *　@２）　@DB検索 <BR>
    *　@以下の条件で発注先切替テーブルを検索する。 <BR>
    *　@※銘柄タイプで、ソートする。 <BR>
    *　@<BR>
    *　@証券会社コード = パラメータ.証券会社コード and <BR>
    *　@銘柄タイプ in (1：株式, 6：先物オプション) and <BR>
    *　@発注経路区分 = 2：フロント発注正系 <BR>
    * <BR>
    *　@検索結果を取得できなかった場合、例外をスローする。 <BR>
    * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
    * 　@　@　@　@　@tag:   BUSINESS_ERROR_01279<BR>
    * <BR>
    * ３）　@取得したListのサイズ分、Loop処理を行う。 <BR>
    * <BR>
    * ３－１）　@Listオブジェクトから、銘柄タイプを取得する。 <BR>
    * <BR>
    * ３－２）　@取得した銘柄タイプが、既にArrayListオブジェクトにadd()されている（contains() == true）場合、continueする。 <BR>
    * <BR>
    * ３－３）　@取得した銘柄タイプをArrayListオブジェクトにadd()する。 <BR>
    * <BR>
    * ４）　@ArrayListオブジェクトをString配列に変換する。 <BR>
    * <BR>
    * ５）　@変換したString配列を返却する。<BR>
    * @@param 証券会社コード - 証券会社コード。<BR>
    * @@return 銘柄タイプString[]。<BR>
    * @@throws WEB3BaseException <BR>
    */
   
   public String[] getProductTypeList(String l_strInstitutionCode) throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = ".getProductTypeList(String)";
       log.entering(STR_METHOD_NAME);
       
       // ArrayListオブジェクトの生成
       List l_lstConvertCodes = new ArrayList();
       // ArrayListオブジェクトの生成
       List l_lstAddProductType = new ArrayList();
       // 発注先切替テーブル検索結果List
       List l_switchingResult = new ArrayList();
       // return用String型配列
       String[] l_dispMarketLists = null;
       
       // 検索条件文字列の生成
       StringBuffer l_sbWhere = new StringBuffer();
       l_sbWhere.append(" institution_code = ? ");
       l_sbWhere.append(" and product_type in (　@?,?)");
       l_sbWhere.append(" and submit_order_route_div = ? ");
       
       // 検索条件コンテナの生成
       Object[] l_objWhere =
           {
               l_strInstitutionCode,
               Integer.toString(ProductTypeEnum.IFO.intValue()),
               Integer.toString(ProductTypeEnum.EQUITY.intValue()),
               WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION
           };
       
       try {
           // DB検索
           QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

           l_switchingResult = l_queryProcessor.doFindAllQuery(
               OrderSwitchingRow.TYPE,
               l_sbWhere.toString(),
               null,
               l_objWhere);
           
           // 検索結果が0件の場合、エラーメッセージをスローする。
           if (l_switchingResult.size() == 0)
           {
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01279, 
                   this.getClass().getName() + STR_METHOD_NAME);                 
           }
       }
       catch (DataException l_de)
       {
           log.error(STR_METHOD_NAME, l_de);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + STR_METHOD_NAME,
               l_de.getMessage(),
               l_de);
       }  

       Iterator l_ObjSwitchResult = l_switchingResult.iterator();
       
       while (l_ObjSwitchResult.hasNext())
       {   
           // 発注先切替テーブルRowオブジェクトの取得      
           OrderSwitchingRow switchRow = (OrderSwitchingParams)l_ObjSwitchResult.next();
           
           // 銘柄タイプを取得
           ProductTypeEnum l_productType = switchRow.getProductType();
           
           // 既にArrayListオブジェクトにadd()されている（contains() == true）場合、continueする。 
           if (l_lstConvertCodes.contains("" + l_productType.intValue()))
           {
               continue;
           }
           
           // Listオブジェクトに追加
           l_lstConvertCodes.add("" + l_productType.intValue());
       }
       
       l_dispMarketLists = new String[l_lstConvertCodes.size()];
       
       // Listから配列に変換
       for (int i=0;i<l_lstConvertCodes.size();i++)
       {
           l_lstAddProductType.add(l_lstConvertCodes.get(i));
       }
       
       l_lstAddProductType.toArray(l_dispMarketLists);

       log.exiting(STR_METHOD_NAME);
       return l_dispMarketLists;
   }
}

@
