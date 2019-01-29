head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者投資信託　@銘柄条件登録照会サービス実装(WEB3AdminMutualConditionsReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/18 王蘭芬(中訊) 新規作成
Revesion History : 2004/08/20 黄建 (中訊) レビュー
Revesion History : 2004/12/09 于美麗 (中訊) 残対応
Revesion History : 2007/04/06 趙林鵬 (中訊) モデル 544
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceResponse;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.message.WEB3MutualProductConditionsGroup;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsReferenceService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 管理者投資信託　@銘柄条件登録照会サービス実装クラス<BR>
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualConditionsReferenceServiceImpl extends WEB3MutualClientRequestService 
    implements WEB3AdminMutualConditionsReferenceService 
{
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsReferenceServiceImpl.class);

    /**
     * @@roseuid 410653B6035B
     */
    public WEB3AdminMutualConditionsReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 管理者投資信託　@銘柄条件登録照会を実施する。<BR>
     * <BR>
     * リクエストデータの型により、以下のいずれかのメソッドをコールする。<BR>
     * <BR>
     * ｼｰｹﾝｽ図「（投信）銘柄条件登録照会execute」参照<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E4C0A200C5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // リクエストデータの型により、以下のいずれかのメソッドをコールする。
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualConditionsRefInputRequest)
        {
            // －input銘柄条件登録照会()
            l_response = this.inputProductConditionsRegistRef(
                (WEB3AdminMutualConditionsRefInputRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualConditionsReferenceRequest)
        {
            // －search銘柄条件登録()
            l_response = this.searchProductConditionsRegist(
                (WEB3AdminMutualConditionsReferenceRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else
        {
            // パラメータ値が不正
            log.debug(l_strMethodName + " パラメータ値が不正する！");
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        } 
    }
    
    /**
     * (input銘柄条件登録照会)<BR>
     * 管理者投資信託　@銘柄条件登録照会入力処理を行う。 <BR>
     * <BR>
     * シーケンス図「（投信）銘柄条件登録照会入力」参照<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E4C1560375
     */
    protected WEB3AdminMutualConditionsRefInputResponse inputProductConditionsRegistRef(
        WEB3AdminMutualConditionsRefInputRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "inputProductConditionsRegistRef(" 
            + "WEB3AdminMutualConditionsRefInputRequest l_request)";
        log.entering(l_strMethodName);

        //２）管理者権限チェック 
        //　@２－１）管理者オブジェクト.getInstanceFromログイン情報( )をコールする。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //　@２－２）管理者オブジェクト.validate権限( )をコールする。 
        // [validate権限()に指定する引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.投信（銘柄管理） 
        // is更新：　@false
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                false);
        
        // ３）証券会社コードの取得
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        // --------- Start -------------- 拡張投信銘柄マネージャの取得を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        // --------- End -------------- 拡張投信銘柄マネージャの取得を行う
        // ４）投信銘柄カテゴリー一覧の取得する。
        List l_lisMFProductCategorys = 
            l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
        WEB3MutualProductCategoryUnit[] l_mfProductCategoryUnits = null;
        if (l_lisMFProductCategorys != null && l_lisMFProductCategorys.size() > 0)
        {
            // ５）投信銘柄カテゴリー一覧をレスポンスに設定する形式（ツリー構造の配列）に変換する。        
           l_mfProductCategoryUnits = 
                l_mfProductManager.createMutualFundProductCategoryList(l_lisMFProductCategorys);        
        }
        // ６）レスポンスの設定
        WEB3AdminMutualConditionsRefInputResponse l_response = 
            (WEB3AdminMutualConditionsRefInputResponse)l_request.createResponse();
        l_response.categoryList = l_mfProductCategoryUnits;
        return l_response;
    }
    
    /**
     * (search銘柄条件登録)<BR>
     * 管理者投資信託　@銘柄条件登録照会処理を行う。 <BR>
     * <BR>
     * シーケンス図「（投信）銘柄条件登録照会検索」参照<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E4C1620171
     */
    protected WEB3AdminMutualConditionsReferenceResponse searchProductConditionsRegist(
        WEB3AdminMutualConditionsReferenceRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "searchProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsReferenceRequest l_request)";
        log.entering(l_strMethodName);
        
        //２）入力チェック 
        //　@引数.リクエストデータ.validate()をコールする。 
        l_request.validate();

        //投信・外貨MMF表示区分
        //※nullの場合、「2:両方」とする
        if (l_request.mutualFrgnMmfDisplayDiv == null)
        {
            l_request.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.BOTH;
        }

        //３）管理者権限チェック 
        //　@３－１）管理者オブジェクト.getInstanceFromログイン情報( )をコールする。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //　@３－２）管理者オブジェクト.validate権限( )をコールする。 
        // [validate権限()に指定する引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.投信（銘柄管理） 
        // is更新：　@false
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                false);
        
        //４）証券会社コードの取得  
        //　@管理者オブジェクト.get証券会社コード( )をコールする。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        //５）銘柄一覧の取得 
        //　@５－１）this.create検索条件文字列()をコールし、検索条件文字列を取得。
        
        String l_strWhere =
            this.createSearchCondCharacterString(
                l_request.mutualProductCode,
                l_request.mutualAssocProductCode,
                l_request.categoryCode,
                l_request.mutualFrgnMmfDisplayDiv);
        //　@５－２）this.create検索条件データコンテナ()をコールし、検索条件データコンテナを取得。
        String[] l_strWhereValues =
            this.createSearchCondDataContainer(
                l_request.mutualProductCode,
                l_request.mutualAssocProductCode,
                l_request.categoryCode,
                l_request.mutualFrgnMmfDisplayDiv);
        //　@５－３）投信拡張銘柄マネージャ.get投信銘柄リスト()をコールし、銘柄一覧を取得。
        // --------- Start -------------- 拡張投信銘柄マネージャの取得を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        // --------- End -------------- 拡張投信銘柄マネージャの取得を行う
        String l_strSortConditionDivDef = 
            WEBMFSortConditionDivDef.ADMIN_MUTUAL_COND_REF;
        List l_lisMFProducts = 
            l_mfProductManager.getMutualFundProductList(
                    l_strInstitutionCode,
                    l_strWhere,
                    l_strWhereValues,
                    l_strSortConditionDivDef);
        
        //　@５－４）get投信銘柄リスト()の戻り値が、０件の場合、例外をスローする。（銘柄検索エラー） 
        if (l_lisMFProducts == null || l_lisMFProducts.size() == 0)
        {
            log.debug("銘柄検索エラー! for l_strInstitutionCode, l_strWhere, l_strWhereValues = " + 
                l_strInstitutionCode + ", " + l_strWhere + ", " + l_strWhereValues);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00383,
                this.getClass().getName() + "." + l_strMethodName,
                "銘柄検索エラー");
        }
        //６）銘柄一覧の件数分、投信銘柄条件登録照会銘柄一覧行オブジェクトを生成し、 
        //　@データを設定する。 
        List l_lisProductConditions = new Vector();
        for (int i = 0; i < l_lisMFProducts.size(); i++)
        {
            MutualFundProductRow l_mfProductRow = (MutualFundProductRow)l_lisMFProducts.get(i);
            WEB3MutualProductConditionsGroup l_mfProductConditionsGroup = 
                new WEB3MutualProductConditionsGroup();
            l_mfProductConditionsGroup.id = Long.toString(l_mfProductRow.getProductId());
            l_mfProductConditionsGroup.mutualProductCode = l_mfProductRow.getProductCode();
            l_mfProductConditionsGroup.mutualAssocProductCode = l_mfProductRow.getMutualassocProductCode();
            l_mfProductConditionsGroup.mutualProductName = l_mfProductRow.getStandardName();
            l_mfProductConditionsGroup.categoryCode = l_mfProductRow.getCategoryCode();
            l_lisProductConditions.add(l_mfProductConditionsGroup);
        }
        WEB3MutualProductConditionsGroup[] productConditions = 
            new WEB3MutualProductConditionsGroup[l_lisProductConditions.size()];
        l_lisProductConditions.toArray(productConditions);
        //７）ページング制御 
        // ----------------- レスポンスオブジェクトを生成し
        WEB3AdminMutualConditionsReferenceResponse l_response = 
            (WEB3AdminMutualConditionsReferenceResponse)l_request.createResponse();
        //　@７－１）レスポンスの以下の項目を設定する。 
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        //　@○レスポンス.総ページ数＝銘柄一覧の要素数÷リクエスト.ページ内表示行数 
        //　@　@　@※余りが出る場合は、＋１した値を設定。 
        //　@　@　@※銘柄一覧の要素数＝0(表示対象データなし)の場合、0をセット。 
        int l_intTotalPages = productConditions.length / l_intRequestPageSize;
        if (productConditions.length % l_intRequestPageSize != 0)
        {
            l_intTotalPages = l_intTotalPages + 1;
        }
        l_response.totalPages = l_intTotalPages + "";
        //　@○レスポンス.総レコード数:　@銘柄一覧の要素数 
        l_response.totalRecords = productConditions.length + "";
        //　@○レスポンス.表示ページ番号(表示が何ページ目にあたるか): 
        //　@　@　@　@以下の条件に合致するのであれば、リクエスト.要求ページ番号。 
        //[銘柄一覧の要素数 > (リクエスト.ページ内表示行数×(リクエスト.要求ページ番号-1) )] 
        //　@　@　@　@上記以外の場合は、レスポンス.総ページ数をそのまま設定。 
        //　@　@　@※検索結果がPR層からの要求ページ番号に満たない場合は、最終ページに 
        //　@　@　@　@該当するデータをレスポンスに設定する。 
        if (productConditions.length > (l_intRequestPageSize * l_intRequestPageIndex))
        {
            l_response.pageIndex = l_request.pageIndex;
        }
        else
        {
            l_response.pageIndex = l_intTotalPages + "";
        }
        //　@７－２）設定後、レスポンス.総ページ数＝0 の場合は、レスポンス.銘柄一覧行 
        //　@(投信銘柄条件登録照会銘柄一覧行[ ])にnullをセットし例外をスローする。 
        //
        //８）確定した銘柄一覧のうち、レスポンスに設定する明細を決める。 
        //
        //　@　@８－１)　@(リクエスト.ページ内表示行数×(レスポンス.表示ページ番号?1)数分、 
        //　@　@　@　@確定した銘柄一覧の要素をスキップする。 
        int l_intBeginRecordNumber =  l_intRequestPageSize * (Integer.parseInt(l_response.pageIndex) - 1);
        int l_intEndRecordNumber =  l_intRequestPageSize * Integer.parseInt(l_response.pageIndex);
        if (l_intEndRecordNumber > productConditions.length)
        {
            l_intEndRecordNumber = productConditions.length;
        }
        //　@　@８－２)　@上記で決定した銘柄一覧の要素番号～ 
        //　@　@　@　@(銘柄一覧の要素番号＋リクエスト.ページ内表示行数) 
        //　@　@　@　@までに該当する銘柄一覧を、レスポンスデータ.銘柄一覧行セットする。
        List l_lisProducts = new Vector(); 
        for (int i = l_intBeginRecordNumber; i < l_intEndRecordNumber; i++)
        {
            l_lisProducts.add(productConditions[i]);
        }
        WEB3MutualProductConditionsGroup[] l_products = 
            new WEB3MutualProductConditionsGroup[l_lisProducts.size()];
        l_lisProducts.toArray(l_products);
        //９）レスポンスの設定 
        //　@○レスポンス.銘柄一覧行＝ページング制御を行って確定させた銘柄一覧の配列 
        l_response.productConditions = l_products;
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 銘柄検索を実施する為の検索条件文字列を作成して返却する。<BR>
     * <BR>
     * １）引数.銘柄コードの判定<BR>
     * 　@１－１）引数.銘柄コード!=nullの場合、以下を検索条件文字列に追加する。<BR>
     * 　@　@　@　@　@" 銘柄コード like '?%' "<BR>
     * <BR>
     * ２）引数.投信協会銘柄コードの判定<BR>
     * 　@引数.投信協会銘柄コード!=nullの場合、以下を実施<BR>
     *  ２－１）検索条件文字列!=nullの場合、以下を検索条件文字列に追加する。<BR>
     *　@　@      " and  投信協会銘柄コード like '?%'"<BR>
     * <BR>
     * 　@２－２）以下を検索条件文字列に追加する。<BR>
     *     　@　@" 投信協会銘柄コード like '?%' "<BR>
     * <BR>
     * ３）引数.投信銘柄カテゴリーコードの判定<BR>
     *    引数.投信銘柄カテゴリーコード!=がnullの場合、以下を実施<BR>
     * 　@３－１）検索条件文字列!=nullの場合<BR>
     *         " and 投信銘柄カテゴリーコード "<BR>
     * <BR>
     * 　@３－２）以下を実施。<BR>
     *         拡張投信銘柄マネージャ.get下位投信銘柄カテゴリーリスト()<BR>
     *         [get下位投信銘柄カテゴリーリスト]に渡す引数<BR>
     *         証券会社コード＝管理者.getInstanceFromログイン情報( ).get証券会社コード()<BR>
     *         カテゴリーコード＝引数.投信銘柄カテゴリーコード<BR>
     * <BR>
     *  ３－３）"in (?"の後ろに、<BR>
     * 　@　@　@　@（引数.投信銘柄カテゴリーコードと３－２）の戻り値のカテゴリーコードの全件分）個(*)の<BR>
     *　@       ",?"を追加し、最後に")"を追加する。<BR>
     * 　@　@　@（※：最後、","を１つ削除した上で")"を閉じる事<BR>
     * <BR>
     * ４）引数.投信・外貨MMF表示区分の判定<BR>
     * <BR>
     * ４－１）引数.投信・外貨MMF表示区分＝"投信のみ"の場合<BR>
     * <BR>
     * 　@４－１－１）検索条件文字列!=nullの場合、以下を検索条件文字列に追加する。<BR>
     * 　@　@　@　@　@　@" and 投信タイプ<>?"<BR>
     * <BR>
     * 　@４－１－２）検索条件文字列=nullの場合 、以下を検索条件文字列に追加する。<BR>
     * 　@　@　@　@　@　@" 投信タイプ<>?"<BR>
     * <BR>
     * ４－２）引数.投信・外貨MMF表示区分＝"外貨MMFのみ"の場合<BR>
     * <BR>
     * 　@４－２－１）検索条件文字列!=nullの場合、以下を検索条件文字列に追加する。<BR>
     * 　@　@　@　@　@　@" and 投信タイプ=?"<BR>
     * <BR>
     * 　@４－２－２）検索条件文字列=nullの場合 、以下を検索条件文字列に追加する。<BR>
     * 　@　@　@　@　@　@" 投信タイプ=?"<BR>
     * <BR>
     * <BR>
     * ５）検索条件文字列をリターンする。<BR>
     * <BR>
     * EX）仮に、01というカテゴリーが02、03という子カテゴリーを持っていた場合、<BR>
     *   ３－３）は”in( ?, ?, ? )”となる。<BR>
     *   03が引数で渡され、03が子カテゴリーを持っていなかった場合、<BR>
     *   ３－３）は”in ( ? )”となる。<BR>
     * <BR>
     * (*) 引数.投信銘柄カテゴリーコード==nullの場合、個数に含めない。<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_strMutualAssocProductCode - 投信協会銘柄コード<BR>
     * @@param l_strMutualProductCategoryCode - 投信銘柄カテゴリーコード<BR>
     * @@param l_strmutualFrgnMmfDisplayDiv - (投信・外貨MMF表示区分)<BR>
     * 投信・外貨MMF表示区分<BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分<BR>
     * <BR>
     * 0:投信のみ<BR>
     * 1:外貨MMFのみ<BR>
     * 2:両方<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 40E508E902C1
     */
    protected String createSearchCondCharacterString(
        String l_strProductCode,
        String l_strMutualAssocProductCode,
        String l_strMutualProductCategoryCode,
        String l_strmutualFrgnMmfDisplayDiv)
        throws WEB3BaseException
    {
        final String l_strMethodName = "createSearchCondCharacterString("
            + "String l_strProductCode,"
            + "String l_strMutualAssocProductCode,"
            + "String l_strMutualProductCategoryCode,"
            + "String l_strmutualFrgnMmfDisplayDiv)";
        log.entering(l_strMethodName);
        
        String l_strWhere = "";
        
        //１）引数.銘柄コードの判定
        // １－１）引数.銘柄コード!=nullの場合、以下を検索条件文字列に追加する。
        //　@   " 銘柄コード like '?%' 
        if (l_strProductCode != null)
        {
            l_strWhere = l_strWhere + " product_code like ? ||'%' ";
        }
        
        //２）引数.投信協会銘柄コードの判定
        // 引数.投信協会銘柄コード!=nullの場合、以下を実施
        if (l_strMutualAssocProductCode != null)
        {    
            //２－１）検索条件文字列!=nullの場合、以下を検索条件文字列に追加する。
            //    " and  投信協会銘柄コード like '?%'"   
            if (!"".equals(l_strWhere))
            {
                l_strWhere = l_strWhere + " and mutualassoc_product_code like ? ||'%' ";
            }
            //２－２）以下を検索条件文字列に追加する。
            //    " 投信協会銘柄コード like '?%' "
            else
            {
                l_strWhere = l_strWhere + " mutualassoc_product_code like ? ||'%' ";
            }            
        }
        
        // 拡張投信銘柄マネージャの取得を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3Administrator l_admin;
        String l_institutionCode;
        l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_institutionCode = l_admin.getInstitutionCode();
        List l_lisLowMutualFundProductList;
        //３）引数.投信銘柄カテゴリーコードの判定 
        //   拡張投信銘柄マネージャ.get投信銘柄カテゴリーリスト()の戻り値＞０件の場合、以下を実施 
        List l_lisCategoryList = 
            l_mfProductManager.getMutualFundProductCategoryList(l_institutionCode);
        if (l_lisCategoryList != null && l_lisCategoryList.size() > 0)
        {
            if (WEB3StringTypeUtility.isNotEmpty(l_strMutualProductCategoryCode))
            {
                //３－１）検索条件文字列!=nullの場合 " and 投信銘柄カテゴリーコード " 
                if (!"".equals(l_strWhere))
                {
                    l_strWhere = l_strWhere + " and category_code ";         
                }
                else
                {
                    l_strWhere = l_strWhere + " category_code ";
                }

                try
                {
                    //３－２）以下を実施。 
                    //拡張投信銘柄マネージャ.get下位投信銘柄カテゴリーリスト() 
                    //[get下位投信銘柄カテゴリーリスト]に渡す引数 
                    //証券会社コード＝管理者.getInstanceFromログイン情報( ).get証券会社コード() 
                    //カテゴリーコード＝引数.投信銘柄カテゴリーコード 
                    l_lisLowMutualFundProductList =
                        l_mfProductManager.getLowMutualFundProductCategoryList(
                        WEB3Administrator.getInstanceFromLoginInfo().getInstitutionCode(),
                        l_strMutualProductCategoryCode);
                } 
                catch(WEB3BaseException l_be)
                {
                    log.error("銘柄検索エラー! for l_strInstitutionCode, l_strMutualProductCategoryCode = " +
                    l_institutionCode + ", " + l_strMutualProductCategoryCode);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00383,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_be.getMessage(),
                        l_be);
                }
            
                //３－３）"in (?"の後ろに、 
                //（引数.投信銘柄カテゴリーコードと３－２）の戻り値のカテゴリーコードの全件分）個(*)の 
                //",?"を追加し、最後に")"を追加する。 
                if (l_lisLowMutualFundProductList != null && l_lisLowMutualFundProductList.size() >= 0)
                {   
                    l_strWhere = l_strWhere + "in(";
                    if (l_strMutualProductCategoryCode != null)
                    {
                        l_strWhere = l_strWhere + "?";
                    }
                    for (int i = 0; i < l_lisLowMutualFundProductList.size(); i++)
                    {
                        l_strWhere = l_strWhere + ",?";
                    }
                    l_strWhere = l_strWhere + ")";
                }
            }
        }

        //４）引数.投信・外貨MMF表示区分の判定
        //４－１）引数.投信・外貨MMF表示区分＝"投信のみ"の場合
        if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(
            l_strmutualFrgnMmfDisplayDiv))
        {
            //４－１－１）検索条件文字列!=nullの場合、以下を検索条件文字列に追加する。
            //" and 投信タイプ<>?"
            if (!"".equals(l_strWhere))
            {
                l_strWhere = l_strWhere + " and fund_type <> ? ";
            }
            //４－１－２）検索条件文字列=nullの場合 、以下を検索条件文字列に追加する
            // " 投信タイプ<>?"
            else
            {
                l_strWhere = l_strWhere + " fund_type <> ? ";
            }
        }
        //４－２）引数.投信・外貨MMF表示区分＝"外貨MMFのみ"の場合
        if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(
            l_strmutualFrgnMmfDisplayDiv))
        {
            //４－２－１）検索条件文字列!=nullの場合、以下を検索条件文字列に追加する
            // " and 投信タイプ=?"
            if (!"".equals(l_strWhere))
            {
                l_strWhere = l_strWhere + " and fund_type = ? ";
            }
            //４－２－２）検索条件文字列=nullの場合 、以下を検索条件文字列に追加する
            // " 投信タイプ=?"
            else
            {
                l_strWhere = l_strWhere + " fund_type = ? ";
            }
        }

        //４）検索条件文字列をリターンする。
        log.exiting(l_strMethodName);
        return l_strWhere;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 銘柄検索を実施する為の検索条件データコンテナを作成して返却する。<BR>
     * <BR>
     * １）引数.銘柄コードの判定<BR>
     * 　@１－１）引数.銘柄コードがnull以外の場合、以下を検索条件データコンテナに追加する。<BR>
     * 　@　@　@　@　@引数.銘柄コード<BR>
     * <BR>
     * ２）引数.投信協会銘柄コードの判定<BR>
     * 　@２－１）引数.投信協会銘柄コードがnull以外の場合、<BR>
     * 以下を検索条件データコンテナに追加する。<BR>
     * 　@　@　@　@　@投信協会銘柄コード<BR>
     * <BR>
     * ３）引数.投信銘柄カテゴリーコードの判定<BR>
     * 　@拡張投信銘柄マネージャ.get投信銘柄カテゴリーリスト()の戻り値＞０件の場合、以下を実施<BR>
     * ３－１）引数.投信銘柄カテゴリーコードを検索条件データコンテナに追加する。<BR>
     * ３－２）以下を実施する。<BR>
     *    拡張投信銘柄マネージャ.get下位投信銘柄カテゴリーリスト()<BR>
     *    [get下位投信銘柄カテゴリーリスト]に渡す引数<BR>
     *    証券会社コード＝管理者.getInstanceFromログイン情報( ).get証券会社コード()<BR>
     *    カテゴリーコード＝引数.投信銘柄カテゴリーコード<BR>
     * <BR>
     * ３－３）引数.投信銘柄カテゴリーコード!=nullの場合<BR>
     * 　@　@　@３-２）の戻り値の先頭に、引数.投信銘柄カテゴリーコードを追加する。<BR>
     * <BR>
     * ３－４） ３－３）の戻り値の件数分、以下を検索条件データコンテナに追加する。<BR>
     * <BR>
     * ４）引数.投信・外貨MMF表示区分の判定<BR>
     * 　@　@４－１）引数.投信・外貨MMF表示区分＝"投信のみ"または "外貨MMFのみ"の場合<BR>
     * 　@　@　@　@　@　@以下を検索条件データコンテナに追加する。<BR>
     * 　@　@　@　@　@　@MutualFundTypeEnum.外貨MMF<BR>
     * <BR>
     * ５）検索条件データコンテナをリターンする。<BR>
     * <BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_strMutualAssocProductCode - 投信協会銘柄コード<BR>
     * @@param l_strMutualProductCategoryCode - 投信銘柄カテゴリーコード<BR>
     * @@param l_strmutualFrgnMmfDisplayDiv - (投信・外貨MMF表示区分)<BR>
     * 投信・外貨MMF表示区分<BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分<BR>
     * <BR>
     * 0:投信のみ<BR>
     * 1:外貨MMFのみ<BR>
     * 2:両方<BR>
     * <BR>
     * ※nullの場合、「2:両方」とする
     * <BR>
     * @@return String[ ]
     * @@throws WEB3BaseException
     * @@roseuid 40E508F2009E
     */
    protected String[ ] createSearchCondDataContainer(
        String l_strProductCode,
        String l_strMutualAssocProductCode,
        String l_strMutualProductCategoryCode,
        String l_strmutualFrgnMmfDisplayDiv)
        throws WEB3BaseException
    {
        final String l_strMethodName = "createSearchCondDataContainer("
            + "String l_strProductCode,"
            + "String l_strMutualAssocProductCode,"
            + "String l_strMutualProductCategoryCode,"
            + "String l_strmutualFrgnMmfDisplayDiv)";
        log.entering(l_strMethodName);
        List l_lisWhereValue = new Vector();
        //１）引数.銘柄コードの判定 
        //　@１－１）引数.銘柄コードがnull以外の場合、以下を検索条件データコンテナに追加する。 
        //　@　@　@　@　@引数.銘柄コード 
        if (l_strProductCode != null)
        {
            l_lisWhereValue.add(l_strProductCode);
        }
        //２）引数.投信協会銘柄コードの判定 
        //　@２－１）引数.投信協会銘柄コードがnull以外の場合、以下を検索条件データコンテナに追加する。 
        //　@　@　@　@　@投信協会銘柄コード 
        if (l_strMutualAssocProductCode != null)
        {
            l_lisWhereValue.add(l_strMutualAssocProductCode);
        }
        
        // 拡張投信銘柄マネージャの取得を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        WEB3Administrator l_admin;
        String l_institutionCode;
        l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_institutionCode = l_admin.getInstitutionCode();
        //３）引数.投信銘柄カテゴリーコードの判定 
        //拡張投信銘柄マネージャ.get投信銘柄カテゴリーリスト()の戻り値＞０件の場合、以下を実施 
        List l_lisCategoryList = 
            l_mfProductManager.getMutualFundProductCategoryList(l_institutionCode);
        if (l_lisCategoryList != null && l_lisCategoryList.size() > 0)
        {
            if (l_strMutualProductCategoryCode != null)
            {
                //３－１）引数.投信銘柄カテゴリーコードを検索条件データコンテナに追加する。 
                //３－２）以下を実施する。 
                // 拡張投信銘柄マネージャ.get下位投信銘柄カテゴリーリスト() 
                // [get下位投信銘柄カテゴリーリスト]に渡す引数 
                //証券会社コード＝管理者.getInstanceFromログイン情報( ).get証券会社コード() 
                //カテゴリーコード＝引数.投信銘柄カテゴリーコード 
                List l_lisLowMutualFundProductList = null;    
                try
                {
                    // 拡張投信銘柄マネージャ.get下位投信銘柄カテゴリーリスト()
                    l_lisLowMutualFundProductList =
                        l_mfProductManager.getLowMutualFundProductCategoryList(
                        WEB3Administrator.getInstanceFromLoginInfo().getInstitutionCode(),
                        l_strMutualProductCategoryCode);
                } 
                catch(WEB3BaseException l_be)
                {
                    log.error("銘柄検索エラー! for l_strInstitutionCode, l_strMutualProductCategoryCode = " +
                    l_institutionCode + ", " + l_strMutualProductCategoryCode);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00383,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_be.getMessage(),
                        l_be);
                }    
                //３－３）引数.投信銘柄カテゴリーコード!=nullの場合 
                    //３-２）の戻り値の先頭に、引数.投信銘柄カテゴリーコードを追加する。 
                l_lisWhereValue.add(l_strMutualProductCategoryCode);
                //３－４） ３－３）の戻り値の件数分、以下を検索条件データコンテナに追加する。 
                if (l_lisLowMutualFundProductList != null && l_lisLowMutualFundProductList.size() != 0)
                {
                    for (int i = 0; i < l_lisLowMutualFundProductList.size(); i++)
                    {
                        l_lisWhereValue.add(
                            ((MutualFundProductCategoryParams)
                                l_lisLowMutualFundProductList.get(i)).getCategoryCode());
                    }
                }
            }
        }

        //４）引数.投信・外貨MMF表示区分の判定
        //４－１）引数.投信・外貨MMF表示区分＝"投信のみ"または "外貨MMFのみ"の場合
        //以下を検索条件データコンテナに追加する。
        if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strmutualFrgnMmfDisplayDiv)
            || WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strmutualFrgnMmfDisplayDiv))
        {
            //MutualFundTypeEnum.外貨MMF
            l_lisWhereValue.add(MutualFundTypeEnum.FOREIGN_MMF.intValue() + "");
        }

        //４）検索条件データコンテナをリターンする。
        String[] l_strWhereValues = new String[l_lisWhereValue.size()];
        l_lisWhereValue.toArray(l_strWhereValues);
        log.exiting(l_strMethodName);
        return l_strWhereValues;
    }
}
@
