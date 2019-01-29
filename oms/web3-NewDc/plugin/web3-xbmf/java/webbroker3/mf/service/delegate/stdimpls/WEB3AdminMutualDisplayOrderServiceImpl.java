head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者銘柄表示順序登録サービスImpl(WEB3AdminMutualDisplayOrderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 黄建 (中訊) 新規作成 
Revesion History : 2007/02/25 唐性峰 (中訊) モデル No.546
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
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
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.WEB3MutualProductCategory;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputResponse;
import webbroker3.mf.message.WEB3MutualCurrentDisplayOrderComparator;
import webbroker3.mf.message.WEB3MutualAssocProductCodeComparator;
import webbroker3.mf.message.WEB3MutualCategoryCode1Comparator;
import webbroker3.mf.message.WEB3MutualCategoryCode2Comparator;
import webbroker3.mf.message.WEB3MutualCategoryCode3Comparator;
import webbroker3.mf.message.WEB3MutualDisplayOrderGroup;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mf.message.WEB3MutualProductCodeComparator;
import webbroker3.mf.service.delegate.WEB3AdminMutualDisplayOrderService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信管理者銘柄表示順序登録サービスImpl)<BR>
 * 投資信託管理者銘柄表示順序登録サービス実装クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualDisplayOrderServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3AdminMutualDisplayOrderService
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualDisplayOrderServiceImpl.class);
    /**
     * 投信管理者銘柄表示順序登録サービスを実施する。<BR>
     * <BR>
     * リクエストデータによって、以下のいずれかのメソッドをコールする。<BR>
     * 　@○create銘柄表示順序登録入力画面<BR>
     * 　@○submit銘柄表示順序登録
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualDisplayOrderInputRequest)
        {
            //○create銘柄表示順序登録入力画面
            l_response = 
                this.createMutualDisplayOrderInput(
                    (WEB3AdminMutualDisplayOrderInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualDisplayOrderCompleteRequest)
        {
            //○submit銘柄表示順序登録
            l_response = 
                this.submitMutualDisplayOrderRegistr(
                    (WEB3AdminMutualDisplayOrderCompleteRequest) l_request);
        }
        return l_response;
    }
    
    /**
     * (create銘柄表示順序登録入力画面)<BR>
     * 投信管理者銘柄表示順序登録入力画面取得処理を実施する。<BR>
     * <BR>
     * シーケンス図「（投信）管理者銘柄表示順序登録入力画面」参照<BR>
     * --------------------------------------------------<BR>
     * １）入力チェック<BR>
     * 　@リクエストデータ.validate( )をコールする。<BR>
     * <BR>
     * ２）処理日時の設定<BR>
     * 　@投信取引時間管理.setBusinessTimestamp( )をコールする。<BR>
     * <BR>
     * ３）管理者オブジェクトの取得<BR>
     * 　@３－１）管理者クラス.getInstanceFromログイン情報( )をコールする。<BR>
     * 　@３－２）取得した管理者オブジェクト.validate権限( )をコールする。<BR>
     * <BR>
     * ４）銘柄一覧の取得<BR>
     * 　@WEBBROKERⅢで取り扱える全ての投信銘柄を取得する。<BR>
     * 　@４－１）取得した管理者オブジェクトより証券会社コードを取得する。<BR>
     * 　@４－２）拡張投信銘柄マネージャ.get投信銘柄リスト( )をコールする。<BR>
     * 　@４－３）get投信銘柄リスト( )の戻り値＝null、または0件であった場合、<BR>
     * 　@　@　@　@[検索結果なし]として例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:BUSINESS_ERROR_01279<BR>
     * <BR>
     * ５）投信銘柄明細の作成<BR>
     * 　@  投信管理者銘柄表示順序登録一覧行オブジェクトの配列を作成する。<BR>
     * 　@５－１）投信管理者銘柄表示順序登録一覧行オブジェクトを生成する。<BR>
     * 　@５－２）以下のプロパティを設定する。<BR>
     * 　@　@　@　@  ○表示順<BR>
     * 　@　@　@　@  ○銘柄コード<BR>
     * 　@　@　@　@  ○投信協会銘柄コード<BR>
     * 　@　@　@　@  ○銘柄名<BR>
     * 　@　@　@　@  ○投信銘柄カテゴリー１～３<BR>
     * 　@５－３）拡張投信銘柄マネージャ.get投信銘柄カテゴリー( )をコールし、<BR>
     * 　@　@　@    以下のプロパティを設定する。<BR>
     * 　@　@　@　@       ○投信銘柄カテゴリーコード１<BR>
     * 　@　@　@　@       ○投信銘柄カテゴリー名称１<BR>
     * 　@　@　@　@       ○投信銘柄カテゴリーコード２<BR>
     * 　@　@　@　@       ○投信銘柄カテゴリー名称２<BR>
     * 　@　@　@　@       ○投信銘柄カテゴリーコード３<BR>
     * 　@　@　@　@       ○投信銘柄カテゴリー名称３<BR>
     * <BR>
     * ６）ソート処理<BR>
     * 　@ this.sort投信銘柄一覧明細( )をコールし、並び替えを実施する。<BR>
     * <BR>
     * ７）sort投信銘柄一覧明細( )の戻り値を、<BR>
     *    レスポンスデータ.銘柄表示順序登録一覧<BR>
     * 　@ にセットし、返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 投信管理者銘柄表示順序登録入力画面リクエストオブジェクト
     * @@return WEB3AdminMutualDisplayOrderInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F9450121
     */
    protected WEB3AdminMutualDisplayOrderInputResponse createMutualDisplayOrderInput(
        WEB3AdminMutualDisplayOrderInputRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createMutualDisplayOrderInput(" +
            "WEB3AdminMutualDisplayOrderInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1）入力チェック 
        //　@  リクエストデータ.validate( )をコールする。 
        l_request.validate();

        //投信・外貨MMF表示区分
        //※nullの場合、「2:両方」とする
        if (l_request.mutualFrgnMmfDisplayDiv == null)
        {
            l_request.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.BOTH;
        }

        //1.3）管理者オブジェクトの取得 
        //  管理者クラス.getInstanceFromログイン情報( )をコールする。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4）取得した管理者オブジェクト.validate権限( )をコールする。
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, false);
        
        //1.5）取得した管理者オブジェクトより証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6）拡張投信銘柄マネージャ.get投信銘柄リスト( )をコールする。 
        //証券会社に紐付く全ての投信銘柄の一覧を取得する。
        //[get投信銘柄リストに渡す引数]
        //　@証券会社コード＝管理者オブジェクトから取得した証券会社コード
        //  検索条件文字列（※1）
        //  検索条件データコンテナ（※1）
        //　@ソート条件区分＝”買付一覧照会”
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_tradingModule.getProductManager();

        //(※1)  検索条件文字列/検索条件データコンテナ
        //    ①@リクエスト.投信・外貨MMF表示区分 = "投信のみ"の場合
        //       検索条件文字列：
        //           "system_handling_div != ? and fund_type != ?"
        //       検索条件データコンテナ：
        //           "0：WEBBROKERⅢで取り扱わない", "MutualFundTypeEnum.外貨MMF" の配列
        String l_strSearchCondCharacter = null;
        List l_lisSearchCondDataContainer = new ArrayList();
        if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_request.mutualFrgnMmfDisplayDiv))
        {
            //検索条件文字列
            l_strSearchCondCharacter = "system_handling_div != ? and fund_type != ?";
            //検索条件データコンテナ
            l_lisSearchCondDataContainer.add(WEB3SystemHandlingDivDef.WEBBROKER3_DONOT_TREAT_IT_IN);
            l_lisSearchCondDataContainer.add(MutualFundTypeEnum.FOREIGN_MMF.intValue() + "");
        }

        //    ②リクエスト.投信・外貨MMF表示区分 = "外貨MMFのみ"の場合
        //       検索条件文字列：
        //           "system_handling_div != ? and fund_type = ?"
        //       検索条件データコンテナ：
        //           "0：WEBBROKERⅢで取り扱わない", "MutualFundTypeEnum.外貨MMF" の配列
        else if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_request.mutualFrgnMmfDisplayDiv))
        {
            //検索条件文字列
            l_strSearchCondCharacter = "system_handling_div != ? and fund_type = ?";
            //検索条件データコンテナ
            l_lisSearchCondDataContainer.add(WEB3SystemHandlingDivDef.WEBBROKER3_DONOT_TREAT_IT_IN);
            l_lisSearchCondDataContainer.add(MutualFundTypeEnum.FOREIGN_MMF.intValue() + "");
        }

        //    ③リクエスト.投信・外貨MMF表示区分 = "両方"の場合
        //       検索条件文字列：
        //           "system_handling_div != ?"
        //       検索条件データコンテナ：
        //           "0：WEBBROKERⅢで取り扱わない"のみの配列
        else if (WEB3MutualFrgnMmfDisplayDivDef.BOTH.equals(l_request.mutualFrgnMmfDisplayDiv))
        {
            //検索条件文字列
            l_strSearchCondCharacter = "system_handling_div != ?";
            //検索条件データコンテナ
            l_lisSearchCondDataContainer.add(WEB3SystemHandlingDivDef.WEBBROKER3_DONOT_TREAT_IT_IN);
        }

        int l_intLength = l_lisSearchCondDataContainer.size();
        String[] l_strSearchCondDataContainer = new String[l_intLength];
        l_lisSearchCondDataContainer.toArray(l_strSearchCondDataContainer);

        List l_lisMutualFundProductList =
            l_mfProductManager.getMutualFundProductList(
                l_strInstitutionCode,
                l_strSearchCondCharacter,
                l_strSearchCondDataContainer,
                WEBMFSortConditionDivDef.MUTUAL_BUY_LIST);
        
        //get投信銘柄リスト( )の戻り値＝null、または0件であった場合、 
        //　@[検索結果なし]として例外をスローする。 
        if (l_lisMutualFundProductList == null || 
            l_lisMutualFundProductList.size() == 0)
        {
            log.debug("検索結果なし。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01279,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.7) 繰り返し処理
        // 投信銘柄明細の作成 
        // get投信銘柄リスト( )の戻り値の件数分繰り返して
        // 投信管理者銘柄表示順序登録一覧行オブジェクトの
        List l_lismfProductRentn = new Vector();
        for (int i = 0; i < l_lisMutualFundProductList.size(); i++)
        {
            //1.7.1) 投信管理者銘柄表示順序登録一覧行( )
            WEB3MutualDisplayOrderGroup l_mutualDisplayOrderGroup = 
                new WEB3MutualDisplayOrderGroup();
        
            //1.7.2)プロパティ・セット *1
            //  get投信銘柄リスト( )の戻り値の中より投信銘柄Paramsを取得し、 
            //  生成した投信管理者銘柄表示順序登録一覧行オブジェクトに、 
            //  以下のプロパティをセットする。                     
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_lisMutualFundProductList.get(i);
            MutualFundProductParams l_mfProductParams = 
                new MutualFundProductParams(l_mfProductRow);
            // ○表示順＝投信銘柄Params.get表示順位( )
			if(l_mfProductParams.getIndicationRankingIsNull())
			{
				l_mutualDisplayOrderGroup.displayOrder = null;
			}
			else
			{
            	l_mutualDisplayOrderGroup.displayOrder = 
                	l_mfProductParams.getIndicationRanking() + "";
			}
            // ○銘柄コード＝投信銘柄Params.get銘柄コード( ) 
            l_mutualDisplayOrderGroup.mutualProductCode = 
                l_mfProductParams.getProductCode();
            // ○投信協会銘柄コード＝投信銘柄Params.get投信協会銘柄コード( ) 
            l_mutualDisplayOrderGroup.mutualAssocProductCode = 
                l_mfProductParams.getMutualassocProductCode();
            // ○銘柄名＝投信銘柄Params.get銘柄名( ) 
            l_mutualDisplayOrderGroup.mutualProductName = 
                l_mfProductParams.getStandardName();

            // ○注文受付締切時間＝(*) 
            // (*-1) 投信取引時間管理.reset銘柄コード( )をコールする。
            // [reset銘柄コードに渡す引数] 
            // 銘柄コード＝投信銘柄Params.get銘柄コード( ) 
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_mfProductParams.getProductCode());

            // (*-2) 投信取引時間管理.setTimestamp( )をコールする。 
            WEB3MutualFundTradingTimeManagement.setTimestamp();
            // (*-3) 投信取引時間管理.get注文受付締切時間( )をコールし、 
            // 戻された値"HHMMSS"を"HH:MM"に編集してセットする。
            
            String l_strOrderCloseTime = 
                WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
            Date l_datOrderCloseTime = 
                WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
            l_strOrderCloseTime = 
                WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss");                    
            log.debug("l_strOrderCloseTime" + l_strOrderCloseTime);
            l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
            	+ WEB3GentradeTimeDef.STR_COLON
            	+ l_strOrderCloseTime.substring(2, 4);
            l_mutualDisplayOrderGroup.orderCloseTime = 
                l_strOrderCloseTime;
        
            //1.7.3）投信銘柄Params.getカテゴリーコード( )＝nullの場合
            if (l_mfProductParams.getCategoryCode() == null)
            {
                //1.7.3.1）プロパティ・セット *2
                //投信銘柄管理者銘柄表示順序登録一覧行オブジェクトに 
                //  以下をセットする。 
                //  ○投信銘柄カテゴリーコード１＝null
                l_mutualDisplayOrderGroup.categoryCode1 = null;
                //  ○投信銘柄カテゴリー名称１＝null 
                l_mutualDisplayOrderGroup.categoryName1 = null;
                //  ○投信銘柄カテゴリーコード２＝null 
                l_mutualDisplayOrderGroup.categoryCode2 = null;
                //  ○投信銘柄カテゴリー名称２＝null 
                l_mutualDisplayOrderGroup.categoryName2 = null;
                //  ○投信銘柄カテゴリーコード３＝null
                l_mutualDisplayOrderGroup.categoryCode3 = null;
                //  ○投信銘柄カテゴリー名称３＝null
                l_mutualDisplayOrderGroup.categoryName3 = null;
            }
        
            //1.7.4）投信銘柄Params.getカテゴリーコード( )!=nullの場合
            else
            {
                //1.7.4.1）get投信銘柄カテゴリー(String, String)
                //  取得した投信銘柄カテゴリーオブジェクトを仮に"Aカテゴリー"とする
                //  [get投信銘柄カテゴリーに渡す引数] 
                //　@証券会社コード＝管理者オブジェクトから取得した証券会社コード 
                //　@投信銘柄カテゴリーコード＝検索結果の投信銘柄Params.getカテゴリーコード( )
                WEB3MutualProductCategory l_mutualProductCategoryA =
                    l_mfProductManager.getMutualFundProductCategory(
                        l_strInstitutionCode,
                        l_mfProductParams.getCategoryCode());
                if (l_mutualProductCategoryA != null)
                {
                
                    //1.7.4.2）get親カテゴリーコード( )
                    String l_strParaentCategoryCodeA = 
                        l_mutualProductCategoryA.getParentCategoryCode();
                    log.debug("get親カテゴリーコードA( ) =" + l_strParaentCategoryCodeA);
                    // ●Aカテゴリーのみが取得できた場合
                    //　@○投信銘柄カテゴリーコード１　@＝Aカテゴリー.getカテゴリーコード( )
                    l_mutualDisplayOrderGroup.categoryCode1 = 
                        l_mutualProductCategoryA.getCategoryCode();
                    //　@○投信銘柄カテゴリー名称１　@ ＝Aカテゴリー.getカテゴリー名称( ) 
                    l_mutualDisplayOrderGroup.categoryName1 = 
                        l_mutualProductCategoryA.getCategoryName();
                    //　@○投信銘柄カテゴリーコード２　@＝null 
                    l_mutualDisplayOrderGroup.categoryCode2 = null;
                    //　@○投信銘柄カテゴリー名称２　@ ＝null 
                    l_mutualDisplayOrderGroup.categoryName2 = null;
                    //　@○投信銘柄カテゴリーコード３　@＝null 
                    l_mutualDisplayOrderGroup.categoryCode3 = null;
                    //　@○投信銘柄カテゴリー名称３　@ ＝null 
                    l_mutualDisplayOrderGroup.categoryName3 = null;
                    //1.7.4.3）Aカテゴリー.get親カテゴリーコード( )の戻り値!=nullの場合
                    if (l_strParaentCategoryCodeA != null)
                    {
                        //1.7.4.3.1）get投信銘柄カテゴリー(String, String)
                        //  Bカテゴリーの親カテゴリーとなる投信銘柄カテゴリーオブジェクトを取得する。
                        //  投信銘柄カテゴリーオブジェクトを取得する。 
                        //  [get投信銘柄カテゴリーに渡す引数] 
                        //     証券会社コード＝管理者オブジェクトから取得した証券会社コード 
                        //     投信銘柄カテゴリーコード＝Aカテゴリー.get親カテゴリーコード( )
                        WEB3MutualProductCategory l_l_mutualProductCategoryB =
                            l_mfProductManager.getMutualFundProductCategory(
                                l_strInstitutionCode,
                                l_strParaentCategoryCodeA);
                        if (l_l_mutualProductCategoryB != null)
                        {
                            //1.7.4.3.2）get親カテゴリーコード( )
                            String l_strParentCategoryCodeB = 
                                l_l_mutualProductCategoryB.getParentCategoryCode();
                            log.debug("get親カテゴリーコードB( ) =" + l_strParentCategoryCodeB);
                            //●Aカテゴリー、Bカテゴリーが取得できた場合 
                            //　@○投信銘柄カテゴリーコード１　@＝Bカテゴリー.getカテゴリーコード( )
                            l_mutualDisplayOrderGroup.categoryCode1 = 
                                l_l_mutualProductCategoryB.getCategoryCode();
                            //　@○投信銘柄カテゴリー名称１　@ ＝Bカテゴリー.getカテゴリー名称( ) 
                            l_mutualDisplayOrderGroup.categoryName1 = 
                                l_l_mutualProductCategoryB.getCategoryName();
                            //　@○投信銘柄カテゴリーコード２　@＝Aカテゴリー.getカテゴリーコード( ) 
                            l_mutualDisplayOrderGroup.categoryCode2 = 
                                l_mutualProductCategoryA.getCategoryCode();
                            //　@○投信銘柄カテゴリー名称２　@ ＝Aカテゴリー.getカテゴリー名称( ) 
                            l_mutualDisplayOrderGroup.categoryName2 = 
                                l_mutualProductCategoryA.getCategoryName();
                            //　@○投信銘柄カテゴリーコード３　@＝null 
                            l_mutualDisplayOrderGroup.categoryCode3 = null;
                            //　@○投信銘柄カテゴリー名称３　@ ＝null
                            l_mutualDisplayOrderGroup.categoryName3 = null;
                            //1.7.4.3.3）Bカテゴリー.get親カテゴリーコード( )の戻り値!=nullの場合
                            if (l_strParentCategoryCodeB != null)
                            {
                                //1.7.4.3.3.1）get投信銘柄カテゴリー(String, String)
                                //  取得した投信銘柄カテゴリーオブジェクトを仮に"Cカテゴリー"とする
                                //  投信銘柄カテゴリーオブジェクトを取得する。 
                                // [get投信銘柄カテゴリーに渡す引数] 
                                // 証券会社コード＝管理者オブジェクトから取得した証券会社コード 
                                // 投信銘柄カテゴリーコード＝Bカテゴリー.get親カテゴリーコード( )
                                WEB3MutualProductCategory 
                                    l_l_mutualProductCategoryC =
                                        l_mfProductManager.getMutualFundProductCategory(
                                            l_strInstitutionCode,
                                            l_strParentCategoryCodeB);
                                if (l_l_mutualProductCategoryC != null)
                                {
                                    //1.7.4.3.3.2）get親カテゴリーコード( )
                                    String l_strParentCategoryCodeC = 
                                        l_l_mutualProductCategoryC.getParentCategoryCode();
                                    log.debug("get親カテゴリーコードC( ) =" + l_strParentCategoryCodeC);
                                    //●A、B、C全てのカテゴリーが取得できた場合 
                                    //　@○投信銘柄カテゴリーコード１　@＝Cカテゴリー.getカテゴリーコード( )
                                    l_mutualDisplayOrderGroup.categoryCode1 = 
                                        l_l_mutualProductCategoryC.getCategoryCode();
                                    //　@○投信銘柄カテゴリー名称１　@ ＝Cカテゴリー.getカテゴリー名称( )
                                    l_mutualDisplayOrderGroup.categoryName1 = 
                                        l_l_mutualProductCategoryC.getCategoryName();
                                    //　@○投信銘柄カテゴリーコード２　@＝Bカテゴリー.getカテゴリーコード( )
                                    l_mutualDisplayOrderGroup.categoryCode2 = 
                                        l_l_mutualProductCategoryB.getCategoryCode();
                                    //　@○投信銘柄カテゴリー名称２　@ ＝Bカテゴリー.getカテゴリー名称( ) 
                                    l_mutualDisplayOrderGroup.categoryName2 = 
                                        l_l_mutualProductCategoryB.getCategoryName();
                                    //　@○投信銘柄カテゴリーコード３　@＝Aカテゴリー.getカテゴリーコード( ) 
                                    l_mutualDisplayOrderGroup.categoryCode3 = 
                                        l_mutualProductCategoryA.getCategoryCode();
                                    //　@○投信銘柄カテゴリー名称３　@ ＝Aカテゴリー.getカテゴリー名称( )
                                    l_mutualDisplayOrderGroup.categoryName3 = 
                                        l_mutualProductCategoryA.getCategoryName();
                                }            
                            }        
                        }
                    }
                }
            }
            l_lismfProductRentn.add(l_mutualDisplayOrderGroup);
        }
        
        //1.7.6）sort投信銘柄一覧明細(投信管理者銘柄表示順序登録一覧行[ ], 投信ソートキー[ ])
        //  リクエストで渡されたソートキーに沿う順序に並べ替えを行う。 
        //  [sort投信銘柄一覧明細に渡す引数] 
        //　@  投信銘柄明細＝「投信銘柄明細の作成」で作成した 
        //　@  投信管理者銘柄表示順序登録一覧行オブジェクトの配列 s
        //　@  ソートキー＝リクエストデータ.投信ソートキー
        WEB3MutualDisplayOrderGroup[] l_mfDisplayOrderGroup =
            new WEB3MutualDisplayOrderGroup[l_lismfProductRentn.size()];
        l_lismfProductRentn.toArray(l_mfDisplayOrderGroup);
        
        //1.9) createレスポンス( )(
        WEB3AdminMutualDisplayOrderInputResponse l_response =
            (WEB3AdminMutualDisplayOrderInputResponse) l_request.createResponse();
        l_response.displayOrderGroups = 
            this.sortMutualProductList(l_mfDisplayOrderGroup, l_request.sortKeys);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (submit銘柄表示順序登録)<BR>
     * 投信管理者銘柄表示順序登録処理を実施する。<BR>
     * <BR>
     * シーケンス図「（投信）管理者銘柄表示順序登録完了」参照<BR>
     * --------------------------------------------------<BR>
     * １）入力チェック<BR>
     * 　@  リクエストデータ.validate( )をコールする。<BR>
     * <BR>
     * ２）処理日時の設定<BR>
     * 　@ 投信取引時間管理.setBusinessTimestamp( )をコールする。<BR>
     * <BR>
     * ３）管理者オブジェクトの取得<BR>
     * 　@３－１）管理者クラス.getInstanceFromログイン情報( )をコールする。<BR>
     * 　@３－２）取得した管理者オブジェクト.validate権限( )をコールする。<BR>
     * 　@３－３）取得した管理者オブジェクト.validate取引パスワード( )をコールする。<BR>
     * <BR>
     * ４）銘柄情報の更新処理<BR>
     * 　@  リクエストデータ.銘柄表示順序更新値一覧の件数分、以下を繰り返す。<BR>
     * 　@４－１）拡張投信銘柄マネージャ.get更新用投信銘柄( )をコールする。<BR>
     * 　@４－２）４－１）で取得した拡張投信銘柄オブジェクト.getDataSourceObject( )<BR>
     *          をコールする。<BR>
     * 　@４－３）取得したDataSourceObjectを投信銘柄マスタParamsにキャストし、<BR>
     * 　@　@　@　@  そこに以下にプロパティをセットする。<BR>
     * 　@　@　@　@  ○表示順位<BR>
     * 　@　@　@　@  ○最終更新者コード<BR>
     * 　@　@　@　@  ○更新日付<BR>
     * 　@４－４）設定した投信銘柄マスタParamsオブジェクト<BR>
     *           の値で「投信銘柄マスターテーブル」の<BR>
     * 　@　@　@　@  Update処理を行う。<BR>
     * <BR>
     * ５）レスポンスデータを返却する。
     * @@param l_request - (リクエストデータ)<BR>
     * 投信管理者銘柄表示順序登録完了リクエストオブジェクト
     * @@return WEB3AdminMutualDisplayOrderCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F9B7021B
     */
    protected WEB3AdminMutualDisplayOrderCompleteResponse submitMutualDisplayOrderRegistr(
        WEB3AdminMutualDisplayOrderCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitMutualDisplayOrderRegistr(" +
            "WEB3AdminMutualDisplayOrderCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータ.validate( )をコールする。 
        l_request.validate();
        
        //1.3）管理者オブジェクトの取得 
        //  管理者クラス.getInstanceFromログイン情報( )をコールする。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4）取得した管理者オブジェクト.validate権限( )をコールする。
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, true);
        
        //1.5）validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6）取得した管理者オブジェクトより証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7）get管理者コード( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.8）繰り返し処理
        // リクエストデータ.銘柄表示順序更新値一覧の件数分
        //  繰り返し処理を行う。
        for (int i = 0; i < l_request.displayOrderChangeList.length; i++)
        {
            //1.8.1）投信銘柄Paramsオブジェクトの取得
            //1.8.1.1）get更新用投信銘柄(String, String)
            // [get更新用投信銘柄に渡す引数] 
            //　@   証券会社コード＝管理者オブジェクトから取得した証券会社コード 
            //　@   銘柄コード＝リクエストデータ.銘柄表示順序更新値一覧.銘柄コード
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mfProductManager =
                (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
            WEB3MutualFundProduct l_mutualFundProduct =
                l_mfProductManager.getUpdateMutualFundTradedProduct(
                    l_strInstitutionCode,
                    l_request.displayOrderChangeList[i].mutualProductCode);
        
            //1.8.1.2）○取得した投信銘柄オブジェクトよりDataSourceObjectを取得し
            // 投信銘柄マスタParamsクラスでキャストする。
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_mutualFundProduct.getDataSourceObject();
            MutualFundProductParams l_mfProductParams = 
                new MutualFundProductParams(l_mfProductRow);
            //○投信銘柄マスタParamsオブジェクトに以下のプロパティをセットする。
            //　@表示順位＝リクエストデータ.銘柄表示順序更新値一覧.表示順
            if (l_request.displayOrderChangeList[i].displayOrder == null)
            {
                l_mfProductParams.setIndicationRanking(null);
            }
            else
            {
                l_mfProductParams.setIndicationRanking(
                    Integer.parseInt(l_request.displayOrderChangeList[i].displayOrder));
            }
            //　@最終更新者コード＝取得した管理者コード
            l_mfProductParams.setLastUpdater(l_strAdministratorCode);
            //　@更新日付（オンライン）＝GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
            l_mfProductParams.setOnlineUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            try
            {
                //1.8.1.3）getDefaultProcessor( )
                QueryProcessor l_queryProcessor = 
                    Processors.getDefaultProcessor();
                
                //1.8.1.4）doUpdateQuery(Row)
                l_queryProcessor.doUpdateQuery(l_mfProductParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        //1.9）createレスポンス( )
        WEB3AdminMutualDisplayOrderCompleteResponse l_response =
            (WEB3AdminMutualDisplayOrderCompleteResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (sort投信銘柄一覧明細)<BR>
     * 指定されたソートキー、昇降順に基づいて投信銘柄明細のソートを行う。 <BR>
     * <BR>
     * １)　@ArrayListを作成 <BR>
     * <BR>
     * ２)　@引数:ソートキーの配列数分Loop処理 <BR>
     * <BR>
     * 　@２－１)引数:ソートキー.キー項目を取得 <BR>
     * <BR>
     * 　@２－２)引数:ソートキー.昇順/降順を取得 <BR>
     * <BR>
     * 　@２－３)キー項目による分岐処理 <BR>
     * 　@　@  キー項目が現在表示順であった場合、現在表示順Comparatorを生成 <BR>
     * 　@　@  [コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@  キー項目が銘柄コードであった場合、銘柄コードComparatorを生成 <BR>
     * 　@　@  [コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@  キー項目が投信協会銘柄コードであった場合、<BR>
     *       投信協会銘柄コードComparatorを生成 <BR>
     * 　@　@  [コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@  キー項目が投信銘柄カテゴリーコード１であった場合、<BR>
     * 　@    投信銘柄カテゴリーコード１Comparatorを生成 <BR>
     * 　@　@  [コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@  キー項目が投信銘柄カテゴリーコード２であった場合、<BR>
     * 　@    投信銘柄カテゴリーコード２Comparatorを生成 <BR>
     * 　@　@  [コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@　@  キー項目が投信銘柄カテゴリーコード３であった場合、<BR>
     * 　@    投信銘柄カテゴリーコード３Comparatorを生成 <BR>
     * 　@    [コンストラクタのパラメータ=２－２)で取得した昇順/降順] <BR>
     * <BR>
     * 　@２－４)　@２－３)にて作成したComparatorをArrayListに追加 <BR>
     * <BR>
     * ３)　@ArrayListからComparatorの配列を作成 <BR>
     * <BR>
     * ４)　@Comparatorの配列順のソート処理 <BR>
     *      (web3-common)WEB3ArraysUtility.sort(<BR>
     *      引数:投信銘柄明細、Comparator[]) <BR>
     * <BR>
     * ５)　@ソートされた投信銘柄明細の配列を返却
     * 
     * @@param displayOrderGroups
     *  - (投信銘柄明細)
     * @@param sortKeys - (ソートキー)
     * @@return webbroker3.mf.message.WEB3MutualDisplayOrderGroup[]
     * @@roseuid 415799EE0153
     */
    protected WEB3MutualDisplayOrderGroup[] sortMutualProductList(
        WEB3MutualDisplayOrderGroup[] l_mfdisplayOrderGroups,
        WEB3MutualSortKey[] sortKeys)
    {
        final String STR_METHOD_NAME = 
            "sortMutualProductList(" +
            "WEB3MutualDisplayOrderGroup[] displayOrderGroups," +
            "WEB3MutualSortKey[] sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        //１)　@ArrayListを作成 
        List l_lisComparator = new Vector();
        
        //２)　@引数:ソートキーの配列数分Loop処理 
        for (int i = 0; i < sortKeys.length; i++)
        {
            //２－１)　@引数:ソートキー.キー項目を取得
            String l_strKeyItem = sortKeys[i].keyItem;
            
            //２－２)　@引数:ソートキー.昇順/降順を取得 
            String l_strAscDesc = sortKeys[i].ascDesc;
        
            // ２－３)キー項目による分岐処理 
            //　@キー項目が現在表示順であった場合、現在表示順Comparatorを生成 
            //　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順]
            if (WEB3MFSortkeyItemDef.DISPLAY_ORDER.equals(l_strKeyItem))
            {
                WEB3MutualCurrentDisplayOrderComparator 
                    l_currentDisplayOrderComparator = 
                        new WEB3MutualCurrentDisplayOrderComparator(l_strAscDesc);
                l_lisComparator.add(l_currentDisplayOrderComparator);
            }
            //　@キー項目が銘柄コードであった場合、銘柄コードComparatorを生成 
            //　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順]
            if (WEB3MFSortkeyItemDef.PRODUCT_CODE.equals(l_strKeyItem))
            {
                WEB3MutualProductCodeComparator 
                    l_productCodeComparator = 
                        new WEB3MutualProductCodeComparator(l_strAscDesc);
                l_lisComparator.add(l_productCodeComparator);
            }
            //　@ キー項目が投信協会銘柄コードであった場合、投信協会銘柄コードComparatorを生成 
            if (WEB3MFSortkeyItemDef.MUTUAL_ASSOC_PRODUCT_CODE.equals(l_strKeyItem))
            {
                WEB3MutualAssocProductCodeComparator 
                    l_mutualAssocProductCodeComparator = 
                        new WEB3MutualAssocProductCodeComparator(l_strAscDesc);
                l_lisComparator.add(l_mutualAssocProductCodeComparator);
            }
            //　@キー項目が投信銘柄カテゴリーコード１であった場合、 
            //　@投信銘柄カテゴリーコード１Comparatorを生成 
            //　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順] 
            if (WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_1.equals(l_strKeyItem))
            {
                WEB3MutualCategoryCode1Comparator 
                    l_mutualCategoryCode1Comparator = 
                        new WEB3MutualCategoryCode1Comparator(l_strAscDesc);
                l_lisComparator.add(l_mutualCategoryCode1Comparator);
            }
            //　@キー項目が投信銘柄カテゴリーコード２であった場合、 
            //　@投信銘柄カテゴリーコード２Comparatorを生成 
            //　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順]
            if (WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_2.equals(l_strKeyItem))
            {
                WEB3MutualCategoryCode2Comparator 
                    l_mutualCategoryCode2Comparator = 
                        new WEB3MutualCategoryCode2Comparator(l_strAscDesc);
                l_lisComparator.add(l_mutualCategoryCode2Comparator);
            }
            //　@キー項目が投信銘柄カテゴリーコード３であった場合、 
            //　@投信銘柄カテゴリーコード３Comparatorを生成 
            //　@[コンストラクタのパラメータ=２－２)で取得した昇順/降順]
            if (WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_3.equals(l_strKeyItem))
            {
                WEB3MutualCategoryCode3Comparator 
                    l_mutualCategoryCode3Comparator = 
                        new WEB3MutualCategoryCode3Comparator(l_strAscDesc);
                l_lisComparator.add(l_mutualCategoryCode3Comparator);
            }
            
            //２－４)　@２－３)にて作成したComparatorをArrayListに追加
        }
            
        // ３)　@ArrayListからComparatorの配列を作成
        Comparator[] l_comparator = new  Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparator);
        
        // ４)　@Comparatorの配列順のソート処理
        // (web3-common)WEB3ArraysUtility.sort(引数:投信銘柄明細、Comparator[])
        WEB3ArraysUtility.sort(l_mfdisplayOrderGroups, l_comparator);
        
        // ５)　@ソートされた投信銘柄明細の配列を返却 
        return l_mfdisplayOrderGroups;

    }
}@
