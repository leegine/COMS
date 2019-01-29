head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCalendarRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式カレンダー登録サービスImpl(WEB3AdminFeqCalendarRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 鄭海良(中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー  
Revesion History : 2007/01/16 齊珂 (中訊) 仕様変更No.336を対応
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqDateDivDef;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistInputRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistInputResponse;
import webbroker3.feq.message.WEB3AdminFeqCalendarSearchCondInputRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarSearchCondInputResponse;
import webbroker3.feq.message.WEB3AdminFeqLocalCalendarUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqCalendarRegistService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.data.FeqCalendarDao;
import webbroker3.gentrade.data.FeqCalendarParams;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式カレンダー登録サービスImpl)<BR>
 * 外国株式カレンダー登録サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqCalendarRegistServiceImpl implements WEB3AdminFeqCalendarRegistService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCalendarRegistServiceImpl.class);
    
    /**
     * @@roseuid 42CE39EF0213
     */
    public WEB3AdminFeqCalendarRegistServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式カレンダー登録サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *   get検索条件入力画面()<BR>
     *   get入力画面()<BR>
     *   validate登録()<BR>
     *   submit登録()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107C88013C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqCalendarSearchCondInputRequest)
        {
            //get検索条件入力画面()
            l_response = 
                this.getQueryCondInputScreen((WEB3AdminFeqCalendarSearchCondInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCalendarRegistInputRequest)
        {
            //get入力画面()
            l_response = 
                this.getInputScreen((WEB3AdminFeqCalendarRegistInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCalendarRegistConfirmRequest)
        {
            //validate登録()
            l_response = 
                this.validateRegist((WEB3AdminFeqCalendarRegistConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCalendarRegistCompleteRequest)
        {
            //submit登録()
            l_response = 
                this.submitRegist((WEB3AdminFeqCalendarRegistCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get検索条件入力画面)<BR>
     * カレンダー検索条件入力画面に必要なデータを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）カレンダー登録）get検索条件入力画面」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarSearchCondInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107F790350
     */
    protected WEB3AdminFeqCalendarSearchCondInputResponse getQueryCondInputScreen(
        WEB3AdminFeqCalendarSearchCondInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getQueryCondInputScreen(WEB3AdminFeqCalendarSearchCondInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_CALENDAR_MANAGE, 
            false);//WEB3BaseException

        //get取扱可能市場
        //[引数]
        // 証券会社： 管理者.get証券会社コード()の戻り値
        // 銘柄タイプ：　@ProductTypeEnum.外国株式
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        String[] l_strPossibleMarkets = WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
            l_strInstitutionCode, ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.4 ArrayList()
        ArrayList l_arrayList = new ArrayList();
        
        //1.5 取得した市場オブジェクト毎にLoop処理
        int l_intCount = 0; 

    	if(l_strPossibleMarkets == null || l_strPossibleMarkets.length == 0)
    	{
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "テーブルに該当するデータがありません。");
    	}

        l_intCount = l_strPossibleMarkets.length;

        for (int i = 0; i < l_intCount; i++)
        {
            //1.5.1 add(arg0 : Object)
            l_arrayList.add(l_strPossibleMarkets[i]);
        }
        
        //1.6 toArray( )
        String[] l_strMarketCodes = new String[l_arrayList.size()];
        l_arrayList.toArray(l_strMarketCodes);
        
        //1.7 createResponse( )
        WEB3AdminFeqCalendarSearchCondInputResponse l_response = 
            (WEB3AdminFeqCalendarSearchCondInputResponse)l_request.createResponse();
        
        //1.8 プロパティセット
        l_response.marketList = l_strMarketCodes;
                
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * カレンダー登録入力画面に必要なデータを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）カレンダー登録）get入力画面」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107F79036F
     */
    protected WEB3AdminFeqCalendarRegistInputResponse getInputScreen(
        WEB3AdminFeqCalendarRegistInputRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqCalendarRegistInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_CALENDAR_MANAGE, false);//WEB3BaseException
        
        //1.4 create取得条件データコンテナ(String, String, String)
        Object[] l_objContainers = this.createGetContainer(
            l_admin.getInstitutionCode(),
            l_request.marketCode,
            l_request.period);
        List l_lisRecord = null;            
        try
        {
            //1.5 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.6 doFindAllQuery(Rowタイプ : RowType, Where : String, orderBy : String, condition : String, リスト : Object[])
            l_lisRecord = l_queryProcessor.doFindAllQuery(
                FeqCalendarRow.TYPE,
                "institution_code = ? and market_code= ? and biz_date >= ? and biz_date <= ? ",
                "biz_date",
                null,
                l_objContainers);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.7 ArrayList( )
        ArrayList l_arrayList = new ArrayList();
        
        //1.8 取得したレコード毎にLoop処理
        int l_intCount = 0; 
        if (l_lisRecord != null)
        {
            l_intCount = l_lisRecord.size();
        }
        for (int i = 0; i < l_intCount; i++)
        {
            //1.8.1 現地カレンダー情報( )
            WEB3AdminFeqLocalCalendarUnit l_feqLocalCalendarUnit = new WEB3AdminFeqLocalCalendarUnit();
            
            //1.8.2 プロパティセット
            //日付 = 外株海外市場カレンダーテーブル.日付
            //日付区分 = 外株海外市場カレンダーテーブル.営業日区分
            //更新日 = 外株海外市場カレンダーテーブル.更新日付
            FeqCalendarRow l_feqCalendarRow = (FeqCalendarRow)l_lisRecord.get(i);
            l_feqLocalCalendarUnit.bizDate = WEB3DateUtility.toDay(l_feqCalendarRow.getBizDate());
            l_feqLocalCalendarUnit.bizDateDiv = l_feqCalendarRow.getBizDateType();
            l_feqLocalCalendarUnit.updateDate = l_feqCalendarRow.getLastUpdatedTimestamp();
                
            //1.8.3 add(arg0 : Object)
            l_arrayList.add(l_feqLocalCalendarUnit);
        }
        
        //1.9  toArray( )
        WEB3AdminFeqLocalCalendarUnit[] l_feqLocalCalendarUnits = 
            new WEB3AdminFeqLocalCalendarUnit[l_arrayList.size()];
        l_arrayList.toArray(l_feqLocalCalendarUnits);

        //1.10 createResponse()
        WEB3AdminFeqCalendarRegistInputResponse l_response = 
            (WEB3AdminFeqCalendarRegistInputResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.debug("レスポンスがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "レスポンスがnullです。");
        }    

        //1.11 プロパティセット
        l_response.feqLocalCalendarUnit = l_feqLocalCalendarUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate登録)<BR>
     * カレンダー登録の確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）カレンダー登録）validate登録」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR>
     * （管）カレンダー登録」(（管）カレンダー登録）validate登録)<BR>
     * 　@　@:  1.4 <BR> 
     * 　@　@リクエスト.カレンダー情報一覧 == null or<BR> 
     * 　@　@リクエスト.カレンダー情報一覧.length() == 0<BR> 
     * 　@　@の場合、例外をスローする。<BR> 
     * <BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02156<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR>
     * （管）カレンダー登録」(（管）カレンダー登録）validate登録)<BR>
     * 　@　@:  1.5.1 <BR> 
     * 　@　@１）　@nullチェック<BR> 
     *    日付 == null or 日付区分 == nullの場合、<BR> 
     *    例外をスローする。<BR> 
     * <BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02157<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR>
     * （管）カレンダー登録」(（管）カレンダー登録）validate登録)<BR>
     * 　@　@:  1.5.1 <BR> 
     * 　@　@２）日付区分チェック<BR> 
     * 　@　@日付区分が以下の値以外の場合、例外をスローする。<BR> 
     * 　@　@0：非営業日<BR> 
     * 　@　@1：営業日<BR> 
     * <BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02158<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107F79037F
     */
    protected WEB3AdminFeqCalendarRegistConfirmResponse validateRegist(
        WEB3AdminFeqCalendarRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateRegist(WEB3AdminFeqCalendarRegistConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_CALENDAR_MANAGE, true);//WEB3BaseException
        
        //1.4 リクエスト.カレンダー情報一覧 == null or
        //  リクエスト.カレンダー情報一覧.length() == 0
        //  の場合、例外をスローする。
        if (l_request.feqLocalCalendarUnit == null || l_request.feqLocalCalendarUnit.length == 0)
        {
            String l_strMessage = "カレンダー情報一覧が存在しません。"; 
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02156,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.5 カレンダー情報一覧の各要素についてLoop処理
        int l_intCount = l_request.feqLocalCalendarUnit.length;
        for (int i = 0; i < l_intCount; i++)
        {
            //1.5.1 以下のチェックを行う。
            //１）nullチェック
            //   日付 == null or 日付区分 == nullの場合、
            //   例外をスローする。
            if (l_request.feqLocalCalendarUnit[i].bizDate == null
                || WEB3StringTypeUtility.isEmpty(l_request.feqLocalCalendarUnit[i].bizDateDiv))
            {
                String l_strMessage = "日付或いは日付区分がnullです。"; 
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02157,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //２）日付区分チェック
            //   日付区分が以下の値以外の場合、例外をスローする。
            //   0：非営業日
            //   1：営業日
            if (!WEB3FeqDateDivDef.NO_BIZ_DATE.equals(l_request.feqLocalCalendarUnit[i].bizDateDiv)
                && !WEB3FeqDateDivDef.BIZ_DATE.equals(l_request.feqLocalCalendarUnit[i].bizDateDiv))
            {
                String l_strMessage = "日付区分が未定義の値です。「" 
                    + l_request.feqLocalCalendarUnit[i].bizDateDiv + "」"; 
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02158,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        //1.6 createResponse()
        WEB3AdminFeqCalendarRegistConfirmResponse l_response = 
            (WEB3AdminFeqCalendarRegistConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit登録)<BR>
     * カレンダーの登録処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）カレンダー登録）submit登録」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR>
     * （管）カレンダー登録」(（管）カレンダー登録）submit登録)<BR>
     * 　@　@:  1.4 <BR> 
     * 　@　@リクエスト.カレンダー情報一覧 == null or<BR> 
     * 　@　@リクエスト.カレンダー情報一覧.length() == 0<BR> 
     * 　@　@の場合、例外をスローする。<BR> 
     * <BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02156<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /（管）カレンダー登録」(（管）カレンダー登録）submit登録)<BR>
     * 　@　@:  1.6.1 <BR> 
     * 　@　@１）　@nullチェック<BR> 
     *    日付 == null or 日付区分 == nullの場合、<BR> 
     *    例外をスローする。<BR> 
     * <BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02157<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /（管）カレンダー登録」(（管）カレンダー登録）submit登録)<BR>
     * 　@　@:  1.6.1<BR> 
     * 　@　@２）日付区分チェック<BR> 
     * 　@　@日付区分が以下の値以外の場合、例外をスローする。<BR> 
     * 　@　@0：非営業日<BR> 
     * 　@　@1：営業日<BR> 
     * <BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02158<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107F79039E
     */
    protected WEB3AdminFeqCalendarRegistCompleteResponse submitRegist(
        WEB3AdminFeqCalendarRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitRegist(WEB3AdminFeqCalendarRegistCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_CALENDAR_MANAGE, true);//WEB3BaseException
        
        //1.4 リクエスト.カレンダー情報一覧 == null or
        //  リクエスト.カレンダー情報一覧.length() == 0
        //  の場合、例外をスローする。
        if (l_request.feqLocalCalendarUnit == null || l_request.feqLocalCalendarUnit.length == 0)
        {
            String l_strMessage = "カレンダー情報一覧が存在しません。"; 
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02156,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.5 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        //1.6 カレンダー情報一覧の各要素についてLoop処理
        int l_intCount = l_request.feqLocalCalendarUnit.length;
        for (int i = 0; i < l_intCount; i++)
        {
            //1.6.1 以下のチェックを行う。
            //１）nullチェック
            //   日付 == null or 日付区分 == nullの場合、
            //   例外をスローする。
            if (l_request.feqLocalCalendarUnit[i].bizDate == null
                || WEB3StringTypeUtility.isEmpty(l_request.feqLocalCalendarUnit[i].bizDateDiv))
            {
                String l_strMessage = "日付或いは日付区分がnullです。"; 
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02157,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //２）日付区分チェック
            //   日付区分が以下の値以外の場合、例外をスローする。
            //   0：非営業日
            //   1：営業日
            if (!WEB3FeqDateDivDef.NO_BIZ_DATE.equals(l_request.feqLocalCalendarUnit[i].bizDateDiv)
                && !WEB3FeqDateDivDef.BIZ_DATE.equals(l_request.feqLocalCalendarUnit[i].bizDateDiv))
            {
                String l_strMessage = "日付区分が未定義の値です。「" 
                    + l_request.feqLocalCalendarUnit[i].bizDateDiv + "」"; 
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02158,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //1.6.2 update外株海外市場カレンダー(管理者, String, 現地カレンダー情報)
            this.updateFeqForeignMarketCalendar(
                l_admin,
                l_request.marketCode,
                l_request.feqLocalCalendarUnit[i]);//WEB3BaseException
        }

        //1.7 createResponse()
        WEB3AdminFeqCalendarRegistCompleteResponse l_response = 
            (WEB3AdminFeqCalendarRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * リクエストデータから、取得条件のデータコンテナを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     * 引数.証券会社コードをArrayListに追加する。<BR>
     * <BR>
     * ３）市場コード<BR>
     * <BR>
     * 引数.リクエストデータ.市場コードをArrayListに追加する。<BR>
     * <BR>
     * ４）年月条件生成<BR>
     * <BR>
     * 引数.リクエストデータ.年月の最初の日(1日)と最後の日を算出し、<BR>
     * ArrayListに追加する。<BR>
     * ※時間部分は、初期値（00:00:00）<BR>
     * <BR>
     * ５）ArrayLisｔ..toArray()の戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * 
     * @@param l_strYearMonth - (年月)<BR>
     * 年月（YYYYMM）<BR>
     * 
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 421C68BB0175
     */
    protected Object[] createGetContainer(
        String l_strInstitutionCode, 
        String l_strMarketCode, 
        String l_strYearMonth) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " createGetContainer(String , String , String )";
        log.entering(STR_METHOD_NAME);
        
        //１）空のArrayListを生成する。
        ArrayList l_arrayList = new ArrayList();
        
        //２）証券会社コード        
        //引数.証券会社コードをArrayListに追加する。
        l_arrayList.add(l_strInstitutionCode);
        
        //３）市場コード        
        //引数.リクエストデータ.市場コードをArrayListに追加する。
        l_arrayList.add(l_strMarketCode);
        
        //４）年月条件生成        
        //引数.リクエストデータ.年月の最初の日(1日)と最後の日を算出し、
        //ArrayListに追加する。
        //※時間部分は、初期値（00:00:00）
        Date l_datYearMonth = WEB3DateUtility.getDate(l_strYearMonth, "yyyyMM");
        if (l_datYearMonth == null)
        {
            String l_strMessage = "年月が日付として有りえない値です.「" + l_strYearMonth + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        Calendar l_canlendar = Calendar.getInstance();
        l_canlendar.setTime(l_datYearMonth);
        l_canlendar.set(Calendar.DAY_OF_MONTH, 1);
        l_arrayList.add(l_canlendar.getTime());
        l_canlendar.add(Calendar.MONTH, 1);
        l_canlendar.add(Calendar.DAY_OF_MONTH, -1);
        l_arrayList.add(l_canlendar.getTime());
                
        //５）ArrayLisｔ..toArray()の戻り値を返却する。
        Object[] l_objects = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_objects);
        
        log.exiting(STR_METHOD_NAME);
        return l_objects;
    }
    
    /**
     * (update外株海外市場カレンダー)<BR>
     * 外株海外市場カレンダーテーブルを更新する。<BR>
     * <BR>
     * １）以下の条件で外株海外市場カレンダーテーブルからレコードを取得する。<BR>
     * <BR>
     * [条件]<BR>
     * 証券会社コード： 引数.管理者.get証券会社コード()の戻り値<BR>
     * 市場コード： 引数.市場コード<BR>
     * 日付： 引数.カレンダー情報.日付<BR>
     * <BR>
     * ２）レコードが取得できた場合、以下の処理を行う。<BR>
     * <BR>
     * ２－１）１）で取得した行オブジェクトのクローンを生成する。<BR>
     * <BR>
     * ２－２）行オブジェクトのクローンにプロパティをセットする。<BR>
     * <BR>
     *    営業日区分： 引数.カレンダー情報.日付区分<BR>
     *    更新者コード： 引数.管理者.get管理者コード()の戻り値<BR>
     *    更新日付： 現在時刻<BR>
     * <BR>
     * ２－３）外株海外市場カレンダーテーブルを更新する。<BR>
     * <BR>
     *    WEB3DataAccessUtility.updateRow()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    外株海外市場カレンダー行オブジェクトのクローン<BR>
     * <BR>
     * ３）レコードが取得できなかった場合、以下の処理を行う。<BR>
     * <BR>
     * ３－１）空の外株海外市場カレンダー行オブジェクトを生成する。<BR>
     * <BR>
     * ３－２）行オブジェクトにプロパティをセットする。<BR>
     * <BR>
     *    証券会社コード： 引数.管理者.get証券会社コード()の戻り値<BR>
     *    市場コード： 引数.市場コード<BR>
     *    日付： 引数.カレンダー情報.日付<BR>
     *    営業日区分： 引数.カレンダー情報.日付区分<BR>
     *    更新者コード： 引数.管理者.get管理者コード()の戻り値<BR>
     *    作成日付： 現在時刻<BR>
     *    更新日付： 現在時刻<BR>
     * <BR>
     * ３－３）外株海外市場カレンダー行を登録する。<BR>
     * <BR>
     *    WEB3DataAccessUtility.insertRow()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    外株海外市場カレンダー行オブジェクト<BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * 
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * 
     * @@param l_calendarInfo - (カレンダー情報)<BR>
     * 現地カレンダー情報オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 421C8106017C
     */
    protected void updateFeqForeignMarketCalendar(
        WEB3Administrator l_admin, 
        String l_strMarketCode, 
        WEB3AdminFeqLocalCalendarUnit l_calendarInfo) throws WEB3BaseException   
    {
        final String STR_METHOD_NAME = 
            " updateFeqForeignMarketCalendar(WEB3Administrator , String , WEB3AdminFeqLocalCalendarUnit )";
        log.entering(STR_METHOD_NAME);
        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("パラメータ「管理者」が未指定(null)です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ「管理者」が未指定(null)です。");
        }
        if (l_calendarInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("パラメータが「現地カレンダー情報」未指定(null)です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータが「現地カレンダー情報」未指定(null)です。");
        }
        
        //１）以下の条件で外株海外市場カレンダーテーブルからレコードを取得する。
        //[条件]
        //証券会社コード： 引数.管理者.get証券会社コード()の戻り値
        //市場コード： 引数.市場コード
        //日付： 引数.カレンダー情報.日付
        try
        {
            FeqCalendarRow l_reqCalendarRow = 
                FeqCalendarDao.findRowByInstitutionCodeMarketCodeBizDate(
                    l_admin.getInstitutionCode(),
                    l_strMarketCode,
                    new Timestamp(l_calendarInfo.bizDate.getTime()));
        
            Timestamp l_tsCurrentTime = GtlUtils.getSystemTimestamp();
            
            //２）レコードが取得できた場合、以下の処理を行う。
            if (l_reqCalendarRow != null)
            {
                //２－１）１）で取得した行オブジェクトのクローンを生成する。
                FeqCalendarParams l_params = 
                    new FeqCalendarParams(l_reqCalendarRow);           
            
                //２－２）行オブジェクトのクローンにプロパティをセットする。
                //   営業日区分： 引数.カレンダー情報.日付区分
                //   更新者コード： 引数.管理者.get管理者コード()の戻り値
                //   更新日付： 現在時刻
                l_params.setBizDateType(l_calendarInfo.bizDateDiv);
                l_params.setLastUpdater(l_admin.getAdministratorCode());
                l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
            
                //２－３）外株海外市場カレンダーテーブルを更新する。
                //   WEB3DataAccessUtility.updateRow()をコールする。
                //   [引数]
                //   外株海外市場カレンダー行オブジェクトのクローン
                WEB3DataAccessUtility.updateRow(l_params);
            }
            //３）レコードが取得できなかった場合、以下の処理を行う。
            else
            {
                //３－１）空の外株海外市場カレンダー行オブジェクトを生成する。
                FeqCalendarParams l_params = new FeqCalendarParams();           
            
                //３－２）行オブジェクトにプロパティをセットする。
            
                //   証券会社コード： 引数.管理者.get証券会社コード()の戻り値
                //   市場コード： 引数.市場コード
                //   日付： 引数.カレンダー情報.日付
                //   営業日区分： 引数.カレンダー情報.日付区分
                //   更新者コード： 引数.管理者.get管理者コード()の戻り値
                //   作成日付： 現在時刻
                //   更新日付： 現在時刻
                l_params.setInstitutionCode(l_admin.getInstitutionCode());
                l_params.setMarketCode(l_strMarketCode);
                l_params.setBizDate(l_calendarInfo.bizDate);
                l_params.setBizDateType(l_calendarInfo.bizDateDiv);
                l_params.setLastUpdater(l_admin.getAdministratorCode());
                l_params.setCreatedTimestamp(l_tsCurrentTime);
                l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
            
                //３－３）外株海外市場カレンダー行を登録する。
                //   WEB3DataAccessUtility.insertRow()をコールする。
                //   [引数]
                //   外株海外市場カレンダー行オブジェクト
                WEB3DataAccessUtility.insertRow(l_params);
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
