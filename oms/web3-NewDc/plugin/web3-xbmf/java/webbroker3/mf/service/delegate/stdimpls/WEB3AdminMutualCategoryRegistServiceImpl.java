head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者カテゴリー登録サービスImpl(WEB3AdminMutualCategoryRegistServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 黄建 (中訊) 新規作成 
Revesion History : 2008/04/29 武波 (中訊) 仕様変更モデル597,600
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

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
import webbroker3.mf.WEB3MutualProductCategory;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3ProcessAddChangeDivDef;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputResponse;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3AdminMutualCategoryRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信管理者カテゴリー登録サービスImpl)<BR>
 * 投資信託管理者カテゴリー登録サービス実装クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3AdminMutualCategoryRegistService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualCategoryRegistServiceImpl.class);
    /**
     * (execute)<BR>
     * 投資信託銘柄カテゴリー登録を実施する。<BR>
     * <BR>
     * メッセージによって、以下のいずれかのメソッドをコールする。<BR>
     * 　@○create登録入力画面( )<BR>
     * 　@○create変更入力画面( )<BR>
     * 　@○validate投信銘柄カテゴリー登録( )<BR>
     * 　@○submit投信銘柄カテゴリー登録( )
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153ED9E0269
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("if l_request == null");
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualCategoryRegistInputRequest)
        {
            //create登録入力画面( )
            l_response = 
                this.createRegistrInput(
                    (WEB3AdminMutualCategoryRegistInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualCategoryRegistChangeRequest)
        {
            //create変更入力画面( )
            l_response = 
                this.createRegistChangeInput(
                    (WEB3AdminMutualCategoryRegistChangeRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualCategoryRegistConfirmRequest)
        {
            //validate投信銘柄カテゴリー登録( )
            l_response =
                this.validateMutualProductCategoryRegistr(
                    (WEB3AdminMutualCategoryRegistConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualCategoryRegistCompleteRequest)
        {
            //submit投信銘柄カテゴリー登録( )
            l_response =
                this.submitMutualProductCategoryRegistr(
                    (WEB3AdminMutualCategoryRegistCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                STR_METHOD_NAME
                    + " l_Request と"
                    + " WEB3AioCashoutCancelConfirmRequest "
                    + " と WEB3AioCashoutCancelCompleteRequest"
                    + "と  WEB3AdminMutualCategoryRegistConfirmRequest"
                    + "と  WEB3AdminMutualCategoryRegistCompleteRequest"
                    + "以外である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (create登録入力画面)<BR>
     * 投資信託銘柄カテゴリー登録用入力画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図「（投信）管理者カテゴリー登録入力画面」参照<BR>
     * --------------------------------------------------<BR>
     * １）処理日時の設定<BR>
     * 　@ 投信取引時間管理.setBusinessTimestamp( )をコールする。<BR>
     * <BR>
     * ２）管理者オブジェクトの取得<BR>
     * 　@２－１）管理者クラス.getInstanceFromログイン情報( )をコールし、<BR>
     * 　@　@　@　@   管理者オブジェクトを取得する。<BR>
     * 　@２－２）取得した管理者オブジェクト.validate権限( )をコールする。<BR>
     * <BR>
     * ３）投信銘柄カテゴリーの明細の取得<BR>
     * 　@３－１）取得した管理者オブジェクト.get証券会社コード( )をコールする。<BR>
     * 　@３－２）拡張投信銘柄マネージャ.get投信銘柄カテゴリーリスト( 
     * )をコールする。<BR>
     * 　@３－３）拡張投信銘柄マネージャ.create投信銘柄カテゴリー一覧( 
     * )をコールする。<BR>
     * <BR>
     * ４）戻り値の作成<BR>
     * 　@４－１）リクエストデータ.createレスポンス( )をコールし、<BR>
     *           レスポンスデータを取得する。<BR>
     * 　@４－２）取得したレスポンスデータに、以下のプロパティをセットする。<BR>
     * 　@　@　@　@  ○投信銘柄カテゴリー一覧＝create投信銘柄カテゴリー一覧( )の戻り値<BR>
     * <BR>
     * ５）レスポンスデータを返却する。
     * @@param l_request - (リクエストデータ)<BR>
     * 投信管理者カテゴリー登録入力画面リクエストオブジェクト
     * @@return WEB3AdminMutualCategoryRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153EE6202F6
     */
    protected WEB3AdminMutualCategoryRegistInputResponse createRegistrInput(
        WEB3AdminMutualCategoryRegistInputRequest l_request)
            throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = 
            "createRegistrInput(WEB3AdminMutualCategoryRegistInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //２）管理者オブジェクトの取得 
        //　@ ２－１）管理者クラス.getInstanceFromログイン情報( )をコールし、 
        //　@　@　@　@ 管理者オブジェクトを取得する。 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //　@ ２－２）取得した管理者オブジェクト.validate権限( )をコールする。 
        //  [validate権限()に指定する引数] 
        //  機@能カテゴリコード＝機@能カテゴリコード.投信（銘柄管理） 
        //  is更新＝true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, false);
        
        //   ３）投信銘柄カテゴリーの明細の取得 
        //　@   ３－１）取得した管理者オブジェクト.get証券会社コード( )をコールする。 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //　@   ３－２）拡張投信銘柄マネージャ.get投信銘柄カテゴリーリスト( )をコールする。 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
        List l_lisMFProductCategorysList = 
            l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
        
        //　@   ３－３）拡張投信銘柄マネージャ.create投信銘柄カテゴリー一覧( )をコールする。         
        WEB3MutualProductCategoryUnit[] l_mfProductCategoryUnits =
            l_mfProductManager.createMutualFundProductCategoryList(l_lisMFProductCategorysList);
        
        //４）戻り値の作成 
        //　@  ４－１）リクエストデータ.createレスポンス( )をコールし、レスポンスデータを取得する。
        WEB3AdminMutualCategoryRegistInputResponse l_response =
            (WEB3AdminMutualCategoryRegistInputResponse) l_request.createResponse();
        
        //　@  ４－２）取得したレスポンスデータに、以下のプロパティをセットする。 
        //　@　@　@○投信銘柄カテゴリー一覧＝create投信銘柄カテゴリー一覧( )の戻り値 
        l_response.categoryList = l_mfProductCategoryUnits;
        
        //５）レスポンスデータを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (create変更入力画面)<BR>
     * 投資信託銘柄カテゴリー変更用入力画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図「（投信）管理者カテゴリー変更入力画面」参照<BR>
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
     * <BR>
     * ４）投信銘柄カテゴリーのチェック<BR>
     * 　@４－１）取得した管理者オブジェクト.get証券会社コード( )をコールする。<BR>
     * 　@４－２）拡張投信銘柄マネージャ.get投信銘柄カテゴリー( )をコールする。<BR>
     * 　@４－３）４－２）のget投信銘柄カテゴリー( )の戻り値＝nullの場合、<BR>
     * 　@　@　@　@[データ不整合]として例外をスローする。<BR>
     *          class: WEB3SystemLayerException<BR>
     *          tag:SYSTEM_ERROR_80006<BR>
     * <BR>
     * ５）戻り値の作成<BR>
     * 　@５－１）リクエストデータ.createレスポンス( 
     * )をコールし、レスポンスデータを取得する。<BR>
     * 　@５－１）取得したレスポンスデータに以下のプロパティをセットする。<BR>
     * 　@　@　@    
     * ○投信銘柄カテゴリーコード＝リクエストデータ.投信銘柄カテゴリーコード<BR>
     * 　@　@　@    ○投信銘柄カテゴリー名称＝<BR>
     *              取得した投信銘柄カテゴリーオブジェクト.getカテゴリー名称( )<BR>
     * 　@　@　@    ○親カテゴリーコード＝<BR>
     *              取得した投信銘柄カテゴリーオブジェクト.get親カテゴリーコード( )<BR>
     * <BR>
     * ６）レスポンスデータを返却する。
     * @@param l_request - (リクエストデータ)<BR>
     * 投信管理者カテゴリー変更入力画面リクエストオブジェクト
     * @@throws WEB3BaseException
     * @@return WEB3AdminMutualCategoryRegistChangeResponse
     * @@roseuid 4153EE6A0056
     */
    protected WEB3AdminMutualCategoryRegistChangeResponse createRegistChangeInput(
        WEB3AdminMutualCategoryRegistChangeRequest l_request)
            throws WEB3BaseException
    {       
        final String STR_METHOD_NAME = 
            "createRegistChangeInput(WEB3AdminMutualCategoryRegistChangeRequest l_request)";
          log.entering(STR_METHOD_NAME);
        
        //１）入力チェック 
        //　@ リクエストデータ.validate( )をコールする。 
        l_request.validate();
      
        //３）管理者オブジェクトの取得 
        //　@ ３－１）管理者クラス.getInstanceFromログイン情報( )をコールする。 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //　@ ３－２）取得した管理者オブジェクト.validate権限( )をコールする。 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, true);
   
        //４）投信銘柄カテゴリーのチェック 
        //　@ ４－１）取得した管理者オブジェクト.get証券会社コード( )をコールする。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
     
        //　@ ４－２）拡張投信銘柄マネージャ.get投信銘柄カテゴリー( )をコールする。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
         TradingModule l_tradingModule = 
             l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
         WEB3MutualFundProductManager l_mfProductManager =
             (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
        WEB3MutualProductCategory l_mfProductCategory =
             l_mfProductManager.getMutualFundProductCategory(
                l_strInstitutionCode, 
                l_request.categoryCode);
         
        //　@ ４－３）４－２）のget投信銘柄カテゴリー( )の戻り値＝nullの場合、 
        //　@　@　@　@ [データ不整合]として例外をスローする。 
        if (l_mfProductCategory == null)
        {
            log.debug("データ不整合エラー。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //５）戻り値の作成 
        //　@ ５－１）リクエストデータ.createレスポンス( )をコールし、レスポンスデータを取得する。 
        WEB3AdminMutualCategoryRegistChangeResponse l_response =
            (WEB3AdminMutualCategoryRegistChangeResponse)l_request.createResponse();
        
        //５－１）取得したレスポンスデータに以下のプロパティをセットする。 
        //　@　@○投信銘柄カテゴリーコード＝リクエストデータ.投信銘柄カテゴリーコード 
        l_response.categoryCode = l_request.categoryCode;
        //    ○投信銘柄カテゴリー名称＝取得した投信銘柄カテゴリーオブジェクト.getカテゴリー名称( )
        l_response.categoryName = 
            l_mfProductCategory.getCategoryName();
        //    ○親カテゴリーコード＝取得した投信銘柄カテゴリーオブジェクト.get親カテゴリーコード( ) 
        l_response.parentCategoryCode = 
            l_mfProductCategory.getParentCategoryCode();
        
        //６）レスポンスデータを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (validate投信銘柄カテゴリー登録)<BR>
     * 投資信託銘柄カテゴリー登録確認処理を行う。<BR>
     * <BR>
     * シーケンス図「（投信）管理者カテゴリー登録確認」参照<BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：get投信銘柄カテゴリー( )の戻り値 == nullの場合<BR>
     * 　@　@指定された投信銘柄カテゴリーが存在しなかった場合、<BR>
     * 　@　@[データ不整合]として例外をスローする。<BR>
     * 　@　@class: WEB3SystemLayerException <BR>
     * 　@　@tag　@: SYSTEM_ERROR_80006 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：get下位投信銘柄カテゴリーリスト（）の戻り値が0件でない場合、<BR>
     * 　@　@[削除不可]として例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_03081 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：get投信銘柄（）の戻り値が0件でない場合<BR>
     *   [削除不可]として例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_03081 <BR>
     * ======================================================== <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 投信管理者カテゴリー登録確認リクエストオブジェクト
     * @@throws WEB3BaseException
     * @@return WEB3AdminMutualCategoryRegistConfirmResponse
     * @@roseuid 4153EE700373
     */
    protected WEB3AdminMutualCategoryRegistConfirmResponse validateMutualProductCategoryRegistr(
        WEB3AdminMutualCategoryRegistConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMutualProductCategoryRegistr(WEB3AdminMutualCategoryRegistConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1）validate( )
        //　@リクエストデータ.validate( )をコールする。 
        l_request.validate();
        
        //1.3）getInstanceFromログイン情報( )
        //  管理者クラス.getInstanceFromログイン情報( )をコールする。 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4）validate権限(String, boolean)
        //  [validate権限()に指定する引数] 
        //    機@能カテゴリコード＝機@能カテゴリコード.投信（銘柄管理） 
        //    is更新＝true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, true);
        
        //1.5）get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6）get投信銘柄カテゴリー(String, String) 
        //  指定された投信銘柄カテゴリーコードから投信銘柄カテゴリーオブジェクトの取得する。 
        //  [get投信銘柄カテゴリーに渡す引数] 
        //    証券会社コード＝管理者オブジェクトより取得した証券会社コード 
        //    投信銘柄カテゴリーコード＝リクエストデータ.投信銘柄カテゴリーコード
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
        WEB3MutualProductCategory l_mutualFundProductCategory =
            l_mfProductManager.getMutualFundProductCategory(
                l_strInstitutionCode, 
                l_request.categoryCode);

        //1.7）リクエストデータ.親カテゴリーコード!=nullの場合
        if (l_request.parentCategoryCode != null)
        {
            //1.7.1）validate投信銘柄カテゴリー階層(String, String, String)
            //   [validate投信銘柄カテゴリーに渡す引数] 
            //　@ 証券会社コード＝管理者オブジェクトより取得した証券会社コード 
            //　@ カテゴリーコード＝リクエストデータ.投信銘柄カテゴリーコード
            //   親カテゴリーコード＝リクエストデータ.親カテゴリーコード
			String l_strCategoryCode = null;
			if (WEB3ProcessAddChangeDivDef.CHANGE.equals(l_request.procedureDiv))
			{
				l_strCategoryCode = l_request.categoryCode;
			}
			
			l_mfProductManager.validateProductCategorystorey(
				l_strInstitutionCode, 
				l_strCategoryCode,
				l_request.parentCategoryCode);  
        }
        
        List l_lisReturn = new ArrayList();    
        //1.8）get投信銘柄カテゴリーリスト(String)
        //     証券会社に紐付く全ての投信銘柄カテゴリーを取得する。 
        //    [get投信銘柄カテゴリーリストに渡す引数] 
        //　@  証券会社コード＝管理者オブジェクトから取得した証券会社コード
        List l_lisMutualFundProductCategoryList = null;
        
        //1.9）リクエストデータ.処理区分＝”追加”の場合
        if (WEB3ProcessAddChangeDivDef.ADD.equals(l_request.procedureDiv))
        {
            //指定された投信銘柄カテゴリーが、既に登録されているレコードだった場合、
            //[カテゴリコード重複エラー]として例外をスローする。
            //get投信銘柄カテゴリー( )の戻り値!=nullの場合、例外。）
            if (l_mutualFundProductCategory != null)
            {
                log.debug("カテゴリーコード重複エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01278,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            l_lisMutualFundProductCategoryList =
                l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
            MutualFundProductCategoryParams l_mfProductCategoryChangeParams = null;   
            for (int i = 0; i < l_lisMutualFundProductCategoryList.size(); i++)
            {
                l_mfProductCategoryChangeParams =
                    (MutualFundProductCategoryParams) l_lisMutualFundProductCategoryList.get(i);
                l_lisReturn.add(l_mfProductCategoryChangeParams);                  
            }
            //1.9.1）信銘柄カテゴリー( )の戻り値＝nullの場合
            l_mutualFundProductCategory = new WEB3MutualProductCategory();
            
            //1.9.1.1）createNew投信銘柄カテゴリー(String, String, String)
            //   新規行となる投信銘柄カテゴリーオブジェクトを生成する。 
            //   [createNew投信銘柄カテゴリーに渡す引数] 
            //     証券会社コード＝管理者オブジェクトより取得した証券会社コード 
            //     カテゴリーコード＝リクエストデータ.投信銘柄カテゴリーコード 
            //     カテゴリー名称＝リクエストデータ.投信銘柄カテゴリー名称
            l_mutualFundProductCategory.createNewMutualProductCategory(
                l_strInstitutionCode,
                l_request.categoryCode,
                l_request.categoryName);
                
            //1.9.1.2）set親カテゴリーコード(String) 
            l_mutualFundProductCategory.setParentCategoryCode(l_request.parentCategoryCode);  
            
            //1.9.1.3）登録後一覧の取得
            l_lisReturn.add(l_mutualFundProductCategory.getDataSourceObject());
        }
        
        //1.10）リクエストデータ.処理区分＝”変更”の場合
    
        if (WEB3ProcessAddChangeDivDef.CHANGE.equals(l_request.procedureDiv))
        {
            //指定された投信銘柄カテゴリーが存在しなかった場合、
            //[データ不整合]として例外をスローする。
            //（get投信銘柄カテゴリー( )の戻り値＝nullの場合、例外。）
            if (l_mutualFundProductCategory == null)
            {
                log.debug("データ不整合エラー。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.10.1）get投信銘柄カテゴリー( )の戻り値 != nullの場合
            //1.10.1.1）繰り返し処理
            l_lisMutualFundProductCategoryList =
                l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);

            MutualFundProductCategoryParams l_mfProductCategoryChangeParams = null;

            for (int i = 0; i < l_lisMutualFundProductCategoryList.size(); i++)
            {
                l_mfProductCategoryChangeParams =
                    (MutualFundProductCategoryParams) l_lisMutualFundProductCategoryList.get(i);
                
                //1.10.1.1.1）変更対象を特定する
                //  <変更後一覧の取得>
                //  get投信銘柄カテゴリーリスト( )の戻り値分ループさせ、
                //  変更対象となった投信銘柄カテゴリーParamsオブジェクトを特定し、
                //  その投信銘柄カテゴリーParamsオブジェクトのプロパティに値のセットを行う。
                if (l_request.categoryCode.equals(l_mfProductCategoryChangeParams.getCategoryCode()))
                {
                    //1.10.1.1.1.1） setカテゴリー名称(String)
                    l_mutualFundProductCategory.setCategoryName(l_request.categoryName);
                
                    //1.10.1.1.1.2）set親カテゴリーコード(String)
                    l_mutualFundProductCategory.setParentCategoryCode(l_request.parentCategoryCode);
                    
                    l_lisReturn.add(l_mutualFundProductCategory.getDataSourceObject());
                }
                else 
                {
                    l_lisReturn.add(l_mfProductCategoryChangeParams);
                }
            }

        }

        //リクエストデータ.処理区分=”削除”の場合
        if (WEB3ProcessAddChangeDivDef.DELETE.equals(l_request.procedureDiv))
        {
            //get投信銘柄カテゴリー( )の戻り値 == nullの場合
            //指定された投信銘柄カテゴリーが存在しなかった場合、
            //[データ不整合]として例外をスローする。
            if (l_mutualFundProductCategory == null)
            {
                log.debug("データ不整合エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "データ不整合エラー。");
            }

            //get下位投信銘柄カテゴリーリスト(String, String)
            //[get下位投信銘柄カテゴリーリストに渡す引数]
            //証券会社コード＝管理者オブジェクトより取得した証券会社コード
            //カテゴリーコード＝リクエストデータ.投信銘柄カテゴリーコード
            List l_lisLowMutualFundProductCategorys =
                l_mfProductManager.getLowMutualFundProductCategoryList(
                    l_strInstitutionCode,
                    l_request.categoryCode);

            //get下位投信銘柄カテゴリーリスト（）の戻り値が0件でない場合、[削除不可]として例外をスローする。
            if (l_lisLowMutualFundProductCategorys.size() != 0)
            {
                log.debug("削除不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03081,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "削除不可。");
            }

            //get投信銘柄(String, String)
            //[get投信銘柄に渡す引数]
            //証券会社コード＝管理者オブジェクトから取得した証券会社コード
            //カテゴリーコード＝リクエスト．投信銘柄カテゴリーコード
            List l_lisMutualFundProducts = l_mfProductManager.getMutualFundProduct(
                l_strInstitutionCode,
                l_request.categoryCode);

            //get投信銘柄（）の戻り値が0件でない場合
            //get投信銘柄（）の戻り値!=null の場合、
            //[削除不可]として例外をスローする。
            if (l_lisMutualFundProducts != null
                && l_lisMutualFundProducts.size() != 0)
            {
                log.debug("削除不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03081,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "削除不可。");
            }

            //繰り返し処理
            //<削除後一覧の取得>
            //get投信銘柄カテゴリーリスト( )の戻り値分ループさせ、
            //削除対象となった投信銘柄カテゴリーParamsオブジェクトを特定し、
            //その投信銘柄カテゴリーParamsオブジェクトをリストから削除する。
            //削除対象を特定する
            l_lisMutualFundProductCategoryList =
                l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
            MutualFundProductCategoryParams l_mfProductCategoryChangeParams = null;
            for (int i = 0; i < l_lisMutualFundProductCategoryList.size(); i++)
            {
                MutualFundProductCategoryRow l_mutualFundProductCategoryRow =
                    (MutualFundProductCategoryRow)l_lisMutualFundProductCategoryList.get(i);
                l_mfProductCategoryChangeParams =
                    new MutualFundProductCategoryParams(l_mutualFundProductCategoryRow);

                if (!l_request.categoryCode.equals(l_mfProductCategoryChangeParams.getCategoryCode()))
                {
                    l_lisReturn.add(l_mfProductCategoryChangeParams);
                }
            }
        }

        //1.11）create投信銘柄カテゴリー一覧(List)
        //[create投信銘柄カテゴリー一覧に渡す引数]
        //銘柄カテゴリー一覧＝<登録後一覧の取得>または<変更後一覧の取得>
        //または<削除後一覧の取得>で作成した投信銘柄カテゴリーParamsオブジェクトの一覧
        WEB3MutualProductCategoryUnit[] l_mutualProductCategoryUnit =
            l_mfProductManager.createMutualFundProductCategoryList(l_lisReturn);
            
        //1.12）createレスポンス( )
        WEB3AdminMutualCategoryRegistConfirmResponse l_response =
            (WEB3AdminMutualCategoryRegistConfirmResponse) l_request.createResponse();
        l_response.categoryList = l_mutualProductCategoryUnit;
            
        //レスポンスデータを返却する。    
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    /**
     * (submit投信銘柄カテゴリー登録)<BR>
     * 投資信託銘柄カテゴリー登録処理を行う。<BR>
     * BR>
     * シーケンス図「（投信）管理者カテゴリー登録完了」参照<BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：get投信銘柄カテゴリー( )の戻り値 == nullの場合<BR>
     * 　@　@指定された投信銘柄カテゴリーが存在しなかった場合、<BR>
     * 　@　@[データ不整合]として例外をスローする。<BR>
     * 　@　@class: WEB3SystemLayerException <BR>
     * 　@　@tag　@: SYSTEM_ERROR_80006 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：get下位投信銘柄カテゴリーリスト（）の戻り値が0件でない場合、<BR>
     * 　@　@[削除不可]として例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_03081 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：get投信銘柄（）の戻り値が0件でない場合<BR>
     *   [削除不可]として例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_03081 <BR>
     * ======================================================== <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 投信管理者カテゴリー登録完了リクエストオブジェクト
     * @@throws WEB3BaseException
     * @@return WEB3AdminMutualCategoryRegistCompleteResponse
     * @@roseuid 4153F01501AE
     */
    protected WEB3AdminMutualCategoryRegistCompleteResponse submitMutualProductCategoryRegistr(
        WEB3AdminMutualCategoryRegistCompleteRequest l_request) 
            throws WEB3BaseException   
    {
        final String STR_METHOD_NAME =
            "submitMutualProductCategoryRegistr(WEB3AdminMutualCategoryRegistCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1）validate( ) 
        //　@リクエストデータ.validate( )をコールする。 
        l_request.validate(); 
       
        //1.3）getInstanceFromログイン情報( )
        //　@管理者クラス.getInstanceFromログイン情報( )をコールする。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
         
        //1.4）取得した管理者オブジェクト.validate権限( )をコールする。 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, true);
        
        //1.5）validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6）get証券会社コード( )
        //取得した管理者オブジェクト.get証券会社コード( )をコールする。
        String l_strInstitutionCode = l_admin.getInstitutionCode();
         
        //1.7）get投信銘柄カテゴリー(String, String) 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
        WEB3MutualProductCategory l_mutualFundProductCategory =
            l_mfProductManager.getMutualFundProductCategory(
                l_strInstitutionCode, 
                l_request.categoryCode);
      
        //1.8）リクエストデータ.親カテゴリーコード != nullの場合
        //   1.8.1）validate投信銘柄カテゴリー階層(String, String)
        if (l_request.parentCategoryCode != null)
        {
        	String l_strCategoryCode = null;
			if (WEB3ProcessAddChangeDivDef.CHANGE.equals(l_request.procedureDiv))
			{
				l_strCategoryCode = l_request.categoryCode;
			}
			
            l_mfProductManager.validateProductCategorystorey(
                l_strInstitutionCode, 
				l_strCategoryCode,
                l_request.parentCategoryCode);  
        }
       
        //1.9）リクエストデータ.処理区分＝”追加”の場合 
        if (WEB3ProcessAddChangeDivDef.ADD.equals(l_request.procedureDiv))
        {
            //指定された投信銘柄カテゴリーが、既に登録されているレコードだった場合、
            //[カテゴリコード重複エラー]として例外をスローする。
            //（get投信銘柄カテゴリー( )の戻り値!=nullの場合、例外。）
            if (l_mutualFundProductCategory != null)
            {
                log.debug("カテゴリーコード重複エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01278,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.9.1）get投信銘柄カテゴリー( )の戻り値＝nullの場合 
            l_mutualFundProductCategory = new WEB3MutualProductCategory();
              
            //1.9.1.1）　@createNew投信銘柄カテゴリー(String, String, String)
            //  投信銘柄カテゴリーオブジェクトを生成する。
            //[createNew投信銘柄カテゴリーに渡す引数] 
            //  証券会社コード＝管理者オブジェクトより取得した証券会社コード 
            //  カテゴリーコード＝リクエストデータ.投信銘柄カテゴリーコード 
            //  カテゴリー名称＝リクエストデータ.投信銘柄カテゴリー名称  
            l_mutualFundProductCategory.createNewMutualProductCategory(
                l_strInstitutionCode,
                l_request.categoryCode,
                l_request.categoryName);

            //1.9.1.2）set親カテゴリーコード(String)
            //[set親カテゴリーコードに渡す引数] 
            //親カテゴリーコード＝リクエストデータ.親カテゴリーコード 
            l_mutualFundProductCategory.setParentCategoryCode(l_request.parentCategoryCode);
            
            //1.9.1.3）　@ saveNew投信銘柄カテゴリー( ) 
            //INSERT処理を行う。 
            l_mutualFundProductCategory.saveNewMutualProductCategory();
        }
        
        //1.10）リクエストデータ.処理区分＝”変更”の場合
        else
        {
            if (WEB3ProcessAddChangeDivDef.CHANGE.equals(l_request.procedureDiv))
            {
                //指定された投信銘柄カテゴリーが存在しなかった場合、
                //[データ不整合]として例外をスローする。
                //（get投信銘柄カテゴリー( )の戻り値＝nullの場合、例外。）
                if (l_mutualFundProductCategory == null)
                {
                    log.debug("データ不整合エラー。");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                
                //1.10.1）get投信銘柄カテゴリー( )の戻り値 != nullの場合
                //1.10.1.1）createForUpdateParams( )
                l_mutualFundProductCategory.createForUpdateParams();
                
                //1.10.1.2）setカテゴリー名称(String)
                l_mutualFundProductCategory.setCategoryName(l_request.categoryName);
                
                //1.10.1.3）set親カテゴリーコード(String)
                l_mutualFundProductCategory.setParentCategoryCode(l_request.parentCategoryCode);
                
                //1.10.1.4）save投信銘柄カテゴリー( ) 
                l_mutualFundProductCategory.saveMutualProductCategory();
            }
        }

        //リクエストデータ.処理区分=”削除”の場合
        if (WEB3ProcessAddChangeDivDef.DELETE.equals(l_request.procedureDiv))
        {
            //get投信銘柄カテゴリー( )の戻り値 == nullの場合
            //指定された投信銘柄カテゴリーが存在しなかった場合、
            //[データ不整合]として例外をスローする。
            if (l_mutualFundProductCategory == null)
            {
                log.debug("データ不整合エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "データ不整合エラー。");
            }

            //get下位投信銘柄カテゴリーリスト(String, String)
            //[get下位投信銘柄カテゴリーリストに渡す引数]
            //証券会社コード＝管理者オブジェクトより取得した証券会社コード
            //カテゴリーコード＝リクエストデータ.投信銘柄カテゴリーコード
            List l_lisLowMutualFundProductCategorys =
                l_mfProductManager.getLowMutualFundProductCategoryList(
                    l_strInstitutionCode,
                    l_request.categoryCode);

            //get下位投信銘柄カテゴリーリスト（）の戻り値が0件でない場合、[削除不可]として例外をスローする。
            if (l_lisLowMutualFundProductCategorys.size() != 0)
            {
                log.debug("削除不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03081,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "削除不可。");
            }

            //get投信銘柄(String, String)
            //[get投信銘柄に渡す引数]
            //証券会社コード＝管理者オブジェクトから取得した証券会社コード
            //カテゴリーコード＝リクエスト．投信銘柄カテゴリーコード
            List l_lisMutualFundProducts = l_mfProductManager.getMutualFundProduct(
                l_strInstitutionCode,
                l_request.categoryCode);

            //get投信銘柄（）の戻り値が0件でない場合
            //get投信銘柄（）の戻り値!=null の場合、
            //[削除不可]として例外をスローする。
            if (l_lisMutualFundProducts != null
                && l_lisMutualFundProducts.size() != 0)
            {
                log.debug("削除不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03081,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "削除不可。");
            }

            //delete投信銘柄カテゴリー(String, String)
            //[delete投信銘柄カテゴリーに渡す引数]
            //証券会社コード＝管理者オブジェクトより取得した証券会社コード
            //カテゴリーコード＝リクエストデータ.投信銘柄カテゴリーコード
            l_mutualFundProductCategory.deleteMutualProductCategory(
                l_strInstitutionCode,
                l_request.categoryCode);
        }


        //1.10.11）createレスポンス( )
        WEB3AdminMutualCategoryRegistCompleteResponse l_response =
            (WEB3AdminMutualCategoryRegistCompleteResponse) l_request.createResponse();
        //レスポンスデータを返却する
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
