head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託海外市場カレンダー登録サービス　@実装(WEB3AdminMutualFrgncalServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 王蘭芬(中訊) 新規作成
                   2004/08/20 黄建 (中訊) レビュー
                   2004/12/07 于美麗 (中訊) 残対応
Revesion History : 2009/01/23 王志葵 (中訊) 仕様変更モデルNo.638,640
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3AdminMutualFrgncal;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.data.MutualFundFrgncalDao;
import webbroker3.mf.data.MutualFundFrgncalPK;
import webbroker3.mf.data.MutualFundFrgncalParams;
import webbroker3.mf.data.MutualFundFrgncalRow;
import webbroker3.mf.define.WEB3MFBizDateTypeDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse;
import webbroker3.mf.message.WEB3MutualBizDateUnit;
import webbroker3.mf.message.WEB3MutualProductCodeNameUnit;
import webbroker3.mf.service.delegate.WEB3AdminMutualFrgncalService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託海外市場カレンダー登録サービス　@実装クラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalServiceImpl extends WEB3MutualClientRequestService implements WEB3AdminMutualFrgncalService 
{
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualFrgncalServiceImpl.class);
    
    /**
     * @@roseuid 410653B702EE
     */
    public WEB3AdminMutualFrgncalServiceImpl() 
    {
     
    }
    
    /**
     * 投資信託海外市場カレンダー登録を実施する。<BR>
     * <BR>
     * リクエストデータの型により、以下のいずれかのメソッドをコールする。<BR>
     * －input海外市場カレンダー( )<BR>
     * －search海外市場カレンダー( )<BR>
     * －submit海外市場カレンダー( )<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D80D5A02CC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // リクエストデータの型により、以下のいずれかのメソッドをコールする。
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualFrgncalInputRequest)
        {
            // －input海外市場カレンダー( )
            l_response = this.inputFrgncal(
                (WEB3AdminMutualFrgncalInputRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualFrgncalReferenceRequest)
        {
            // －search海外市場カレンダー( )
            l_response = this.searchFrgncal(
                (WEB3AdminMutualFrgncalReferenceRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualFrgncalCompleteRequest)
        {
            // －submit海外市場カレンダー( )
            l_response = this.submitFrgncal(
                (WEB3AdminMutualFrgncalCompleteRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else
        {
            // パラメータ値が不正
            log.debug(l_strMethodName + " パラメータ値が不正！");
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }       
    }
    
    /**
     * (input海外市場カレンダー)<BR>
     * 投資信託海外市場カレンダー登録入力処理を行う。 <BR>
     * <BR>
     * シーケンス図「（投信）海外市場カレンダー入力」参照<BR>
     * --------------------------------------------------<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D8104E0230
     */
    protected WEB3AdminMutualFrgncalInputResponse inputFrgncal(
        WEB3AdminMutualFrgncalInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String l_strMethodName = "inputFrgncal(WEB3AdminMutualFrgncalInputRequest l_request)";
        log.entering(l_strMethodName);
        
        // 1.2）管理者オブジェクトの取得
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3）管理者の権限チェック 
        // [validate権限()に指定する引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.投信（カレンダー管理） 
        // is更新：　@false
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_FRGNCAL,
                false);
        
        // 1.4）証券会社オブジェクトの取得
		Institution l_institution = l_admin.getInstitution();
		// -証券会社コードの取得
		String l_strInstitutionCode = l_institution.getInstitutionCode();

        //検索条件の作成<*1>
        //<*1>
        //1-1) 検索条件データコンテナ（Stringの配列）に以下を設定する。
        //1：WEBBROKERⅢで取り扱う
        //1-2) 検索条件文字列に、"システム取扱区分 = ?"
        String[] l_strSearchCondDataContainers =
            new String[]{WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN};
        String l_strSearchCondCharacterString = " system_handling_div = ? ";

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        //get投信銘柄リスト
        //アイテムの定義
        //投信銘柄リストを取得する
        //引数:
        //証券会社コード：get証券会社（）．getInstitutionCode()の戻り値
        //検索条件文字列：<*1>で作成した検索条件文字列
        //検索条件データコンテナ：<*1>で作成した検索条件データコンテナ
        //ソート条件区分："管理者投信銘柄条件登録照会"
        List l_lisMutualFundProductList =
            l_mfProductManager.getMutualFundProductList(
                l_strInstitutionCode,
                l_strSearchCondCharacterString,
                l_strSearchCondDataContainers,
                WEBMFSortConditionDivDef.ADMIN_MUTUAL_COND_REF);

		//<繰り返し処理> get投信銘柄リスト( )の戻り値の件数分、以下を繰り返す
        int l_intMutualFundProductListSize = l_lisMutualFundProductList.size();
        List l_lisProductCodeNameUnits = new Vector();
        for (int i = 0; i < l_intMutualFundProductListSize; i++)
        {
          	// 1.6.1）投信銘柄コード名称()
			WEB3MutualProductCodeNameUnit l_mfProductCodeNameUnit = 
				new WEB3MutualProductCodeNameUnit();

           	//＜プロパティ・セット＞
            //生成した投信銘柄コード名称オブジェクトに以下をセットする。
            //銘柄コード=get投信銘柄リスト( )の戻り値[n].getProductCode( )の戻り値
            //銘柄名=get投信銘柄リスト( )の戻り値[n].get銘柄名( )の戻り値
            //買付口座区分一覧=null
           	l_mfProductCodeNameUnit.mutualProductCode =
                ((MutualFundProductRow)l_lisMutualFundProductList.get(i)).getProductCode();
           	l_mfProductCodeNameUnit.mutualProductName =
                ((MutualFundProductRow)l_lisMutualFundProductList.get(i)).getStandardName();
           	l_mfProductCodeNameUnit.taxTypeList = null;

            // 1.6.4）銘柄一覧用配列に追加
            l_lisProductCodeNameUnits.add(l_mfProductCodeNameUnit);
        }
        WEB3MutualProductCodeNameUnit[] l_mfProductCodeNameUnits = 
            new WEB3MutualProductCodeNameUnit[l_lisProductCodeNameUnits.size()];
        l_lisProductCodeNameUnits.toArray(l_mfProductCodeNameUnits);

        //検索年月の作成
        //現在日付 の当月と 翌月を"YYYYMM"形式で取得して配列化する。
        //現在日付＝GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        List l_lisTimestamps = new Vector();
        Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        Date l_datNextMonth = WEB3DateUtility.addMonth(l_datSystemTimestamp, 1);
        l_lisTimestamps.add(
            WEB3DateUtility.formatDate(
                l_datSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YM));
        l_lisTimestamps.add(
            WEB3DateUtility.formatDate(
                l_datNextMonth, WEB3GentradeTimeDef.DATE_FORMAT_YM));
        String[] l_strTimestamps = new String[l_lisTimestamps.size()];
        l_lisTimestamps.toArray(l_strTimestamps);

        //createレスポンス( )
        //レスポンスの設定
        //以下をセットする。
        //・レスポンスデータクラス.検索銘柄一覧＝作成した銘柄一覧の配列
        //・レスポンスデータクラス.検索年月一覧＝作成した検索年月の配列
        WEB3AdminMutualFrgncalInputResponse l_response = 
            (WEB3AdminMutualFrgncalInputResponse)l_request.createResponse();
        l_response.mutualProductCodeNames = l_mfProductCodeNameUnits;
        l_response.searchYMList = l_strTimestamps;

        log.exiting(l_strMethodName);
        return l_response;
    }
    
    /**
     * (search海外市場カレンダー)<BR>
     * 投資信託海外市場カレンダー登録照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（投信）海外市場カレンダー照会」参照<BR>
     * --------------------------------------------------<BR>
     * １）管理者権限チェック<BR>
     * 　@詳細未定<BR>
     * <BR>
     * ２）投信銘柄オブジェクトの取得<BR>
     * 　@投信銘柄オブジェクトが取得できなかった場合、例外をスローする。<BR>
     * 　@（指定銘柄エラー）<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00334 <BR>
     * <BR>
     * ３）海外市場カレンダーテーブル検索<BR>
     * 　@３－１）検索条件作成<BR>
     * 　@　@　@　@○検索条件データコンテナとして、以下の値を配列化する。<BR>
     * 　@　@　@　@○検索条件文字列として以下の文字列を設定する。<BR>
     * <BR>
     * 　@３－２）海外市場カレンダー.get休日一覧をコールして、<BR>
     * 指定条件に合致する休日の一覧を取得する。<BR>
     * <BR>
     * ４）get休日一覧( )の戻り値の件数分、以下を繰り返す。<BR>
     * 　@４－１）海外市場カレンダー.休日を配列化する。<BR>
     * <BR>
     * ５）投信海外市場カレンダー登録照会リクエスト.createレスポンス()をコールして<BR>
     * レスポンスデータを生成し、以下を設定する。<BR>
     * 　@○レスポンスデータ.カレンダー休日一覧＝作成した休日の配列<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（投信）海外市場カレンダー照会」): 7 get投信銘柄(Institution, String)<BR>
     * 投信銘柄が取得できなかった場合、例外『銘柄指定エラー』をスロー。<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00334 <BR>
     * [get投信銘柄の引数]<BR>
     * 　@　@管理者オブジェクト.get証券会社コード( )の戻り値<BR>
     * 　@　@リクエストデータ.銘柄コード<BR>
     * =========================================================<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D8104E024F
     */
    protected WEB3AdminMutualFrgncalReferenceResponse searchFrgncal(
        WEB3AdminMutualFrgncalReferenceRequest l_request) 
        throws WEB3BaseException 
    {
        final String l_strMethodName = "searchFrgncal(WEB3AdminMutualFrgncalReferenceRequest l_request)";
        log.entering(l_strMethodName);

		// 1.1）validate()
		l_request.validate();
		
        // 1.3）管理者オブジェクトの取得
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.4）管理者の権限チェック
        // [validate権限()に指定する引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.投信（カレンダー管理） 
        // is更新：　@false
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_FRGNCAL,
                false);
        
        // 1.5) 証券会社オブジェクトの取得
		Institution l_institution = l_admin.getInstitution();
		// -証券会社コードの取得
        String l_strInstitutionCode = l_institution.getInstitutionCode();

        // -拡張投信銘柄マネージャの取得を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        try
        {
            // 1.6）投信銘柄オブジェクトの取得
            l_mfProductManager.getMutualFundProduct(
            	l_institution, l_request.mutualProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("該当する投信銘柄オブジェクトがありません!"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00334,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        // 1.7）海外市場カレンダーテーブル検索条件の作成
        String[] l_strWhereValues = {l_request.mutualProductCode,l_request.searchYM};
        String l_strWhere = " product_code = ? and to_char(holiday, 'YYYYMM') = ? ";
        
		// 1.8）get休日一覧( )
        List l_lisHolidays = 
            WEB3AdminMutualFrgncal.getHolidayList(l_strInstitutionCode, l_strWhere, l_strWhereValues);
            
        // -get休日一覧( )の戻り値の件数分、以下を繰り返す。
        //  海外市場カレンダー.休日を配列化する。
        Date[] l_dteHolidays = null;
        List l_lisTempHolidays = new Vector();
        if (l_lisHolidays != null){
            //l_dteHolidays = new Date[l_lisHolidays.size()];
            for (int i = 0; i < l_lisHolidays.size(); i ++)
            {
                MutualFundFrgncalRow l_mfFrgncalRow = 
                    (MutualFundFrgncalRow)l_lisHolidays.get(i);
                if (i != 0)
                {
                    if (WEB3DateUtility.compareToDay(l_mfFrgncalRow.getHoliday(), 
                        ((MutualFundFrgncalRow)l_lisHolidays.get(i-1)).getHoliday()) == 0)
                    {
                        continue;
                    }
                }
                l_lisTempHolidays.add(l_mfFrgncalRow.getHoliday());
            }
            l_dteHolidays = new Date[l_lisTempHolidays.size()];
            l_lisTempHolidays.toArray(l_dteHolidays);
        }
        // 1.9) createレスポンス()
        WEB3AdminMutualFrgncalReferenceResponse l_response = 
            (WEB3AdminMutualFrgncalReferenceResponse)l_request.createResponse();
            
        // 1.10) レスポンスデータ.カレンダー休日一覧＝作成した休日の配列
        l_response.notBizDateList = l_dteHolidays;
        log.exiting(l_strMethodName);
        return l_response;
    }
    
    /**
     * (submit海外市場カレンダー)<BR>
     * 投資信託海外市場カレンダー登録完了処理を行う。<BR>
     * <BR>
     * シーケンス図「（投信）海外市場カレンダー完了」参照<BR>
     * --------------------------------------------------<BR>
     * １）管理者権限チェック<BR>
     * 　@詳細未定<BR>
     * <BR>
     * ２）暗証番号チェック<BR>
     * 　@詳細未定<BR>
     * <BR>
     * ３）投信銘柄オブジェクトの取得<BR>
     * 　@　@　@　@投信銘柄オブジェクトが取得できなかった場合、例外をスローする。<BR>
     * 　@　@　@　@（指定銘柄エラー）<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00334 <BR>
     * <BR>
     * ４）リクエストデータ.登録更新情報の件数分、以下のチェックを行う。<BR>
     * 　@４－１）海外市場カレンダー.is休日()をコールする。<BR>
     * <BR>
     * 　@４－２）上記is休日()の戻り値=true、かつ<BR>
     *         リクエストデータ.登録更新情報[n].営業日区分="営業日"の場合<BR>
     * <BR>
     *   ４－２－１）海外市場カレンダーDao.findRowByPK( )をコールして、<BR>
     *         海外市場カレンダーRowオブジェクトを取得する。<BR>
     * 　@４－２－２）海外市場カレンダーRowオブジェクト.getPrimaryKey()をコールして、<BR>
     * 　@　@　@　@　@　@　@　@PrimaryKeyオブジェクトを取得。<BR>
     * 　@４－２－３）QueryProcessor.doDeleteQuery()をコールし、<BR>
     *              削除処理を実施する。<BR>
     * <BR>
     * 　@４－３）リクエストデータ.登録更新情報[n].営業日区分=<BR>
     *          "非営業日"の場合<BR>
     * 　@　@４－３－１）QueryProcessor.doInsertQuery()をコールし、<BR>
     *          登録処理を実施する。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（投信）海外市場カレンダー完了」): 8 get投信銘柄(Institution, String)<BR>
     * 投信銘柄が取得できなかった場合、例外『銘柄指定エラー』をスロー。<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00334 <BR>
     * [get投信銘柄の引数]<BR>
     * 　@　@管理者オブジェクト.get証券会社コード( )の戻り値<BR>
     * 　@　@リクエストデータ.銘柄コード<BR>
     * =========================================================<BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse
     * @@roseuid 40D8104E027E
     */
    protected WEB3AdminMutualFrgncalCompleteResponse submitFrgncal(
        WEB3AdminMutualFrgncalCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "submitFrgncal(WEB3AdminMutualFrgncalCompleteRequest l_request)";
        log.entering(l_strMethodName);

		// 1.1) validate()
		l_request.validate();
		
		// 1.3）管理者オブジェクトの取得
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.4) 管理者の権限チェック 
        // [validate権限()に指定する引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.投信（カレンダー管理） 
        // is更新： true
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_FRGNCAL,
                true);
        
        // 1.5）暗証番号チェック 
        l_admin.validateTradingPassword(l_request.password);
        
        // 1.6) 証券会社オブジェクトの取得
		Institution l_institution = l_admin.getInstitution(); 
        String l_strInstitutionCode = l_institution.getInstitutionCode();

        // -拡張投信銘柄マネージャの取得を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        // 1.7）投信銘柄オブジェクトの取得
        try
        {
            l_mfProductManager.getMutualFundProduct(l_institution, l_request.mutualProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("該当する投信銘柄オブジェクトがありません!"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00334,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.8）リクエストデータ.登録更新情報の件数分、以下のチェックを行う
        try
        {
            WEB3MutualBizDateUnit[] l_mfBizDateUnit = l_request.bizDateList;
            String l_strProductCode = l_request.mutualProductCode;
            for (int i = 0; i < l_mfBizDateUnit.length; i ++)
            {
                // 1.8.1）海外市場カレンダー.is休日() 
                WEB3AdminMutualFrgncal l_adminMutualFrgncal = new WEB3AdminMutualFrgncal();
                boolean l_blnIsHolidy = 
                    l_adminMutualFrgncal.isHoliday(
                        l_strInstitutionCode, 
                        l_strProductCode, 
                        new Timestamp(l_mfBizDateUnit[i].bizDate.getTime()));
                        
                // 1.8.2）getDefaultProcessor()
				QueryProcessor l_defaultProcessors = Processors.getDefaultProcessor();
                
                // -リクエストデータ.登録更新情報[n].営業日区分の取得
				String l_strBizDateType = l_mfBizDateUnit[i].bizDateType;
				
				// 1.8.3）is休日の戻り値=true、
				//        かつリクエストデータ.登録更新情報.営業日区分="営業日"の場合
				MutualFundFrgncalRow l_mfFrgncalRow = null;
				if (l_blnIsHolidy && WEB3MFBizDateTypeDef.BIZDATE.equals(l_strBizDateType))
				{
					log.debug("is休日=trueかつ営業日区分=営業日の場合");
					// 1.8.3.1) 海外市場カレンダーRowオブジェクトを取得 
                	try
                	{
                    	l_mfFrgncalRow = MutualFundFrgncalDao.findRowByPk(
                        	l_strInstitutionCode,
                        	l_strProductCode, 
                        	new Timestamp(l_mfBizDateUnit[i].bizDate.getTime()));
                	}
                	catch (DataFindException l_findEx)
                	{
						log.error("該当する海外市場カレンダーRowオブジェクトがありません!"); 
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00334,
							this.getClass().getName() + "." + l_strMethodName,
								l_findEx.getMessage(),
								l_findEx);
                	}

					// 1.8.3.2) getPrimaryKey()
                	MutualFundFrgncalPK l_mfFrgncalPK =
                		(MutualFundFrgncalPK)l_mfFrgncalRow.getPrimaryKey();
                
               		// 1.8.3.3）QueryProcessor.doDeleteQuery()をコールし、削除処理を実施
                    l_defaultProcessors.doDeleteQuery(l_mfFrgncalPK);
                    log.debug("削除処理");
                }
                
                // 1.8.4）is休日の戻り値=false、
                //        かつリクエストデータ.登録更新情報.営業日区分="非営業日"の場合
                else if (!l_blnIsHolidy && WEB3MFBizDateTypeDef.NOT_BIZDATE.equals(l_strBizDateType))
                {
					log.debug("is休日=falseかつ営業日区分=非営業日の場合");
					// 1.8.4.1) 海外市場カレンダーRowオブジェクトを生成し、プロパティをセットする。 
					MutualFundFrgncalParams l_mfFrgncalParams = new MutualFundFrgncalParams();
					l_mfFrgncalParams.setInstitutionCode(l_strInstitutionCode);
					l_mfFrgncalParams.setProductCode(l_strProductCode);
					l_mfFrgncalParams.setHoliday(l_mfBizDateUnit[i].bizDate);
					l_mfFrgncalParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
					l_mfFrgncalParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
					l_mfFrgncalParams.markAllValuesAsSet();
					l_mfFrgncalRow = l_mfFrgncalParams;
					
					// 1.8.4.2）QueryProcessor.doInsertQuery()をコールし、登録処理を実施        
                    l_defaultProcessors.doInsertQuery(l_mfFrgncalRow);
					log.debug("登録処理");					
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました! ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました!", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        
        // 1.9) createレスポンス()
        WEB3AdminMutualFrgncalCompleteResponse l_response = 
            (WEB3AdminMutualFrgncalCompleteResponse)l_request.createResponse();
        log.exiting(l_strMethodName);
        return l_response;
    }

}
@
