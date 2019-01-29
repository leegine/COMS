head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付一覧照会サービス実装クラス(WEB3MutualBuyListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 周勇 (中訊) 新規作成
Revesion History : 2004/08/23 黄建 (中訊) レビュー 
Revesion History : 2004/12/06 于美麗 (中訊) 残対応
Revesion History : 2005/10/23 黄建 (中訊) フィデリティ対応
Revesion History : 2006/03/08 鈴木 (SRA) 仕様変更（モデル）：402
Revesion History : 2006/05/15 肖志偉(中訊) 仕様変更（モデル) :411,415
Revesion History : 2006/09/19 周捷 (中訊) 仕様変更・モデル483、491
Revesion History : 2006/10/25  張騰宇 (中訊) モデル 512,513
Revesion History : 2007/02/05  唐性峰 (中訊) モデル 523
Revesion History : 2007/04/09  唐性峰 (中訊) モデル 558  実装005
Revesion History : 2007/08/30  孟亞南 (中訊) モデル 570
Revesion History : 2007/09/14  孟亞南 (中訊) モデル 577
Revesion History : 2007/09/25  松本 (SRA) モデル 578
Revesion History : 2008/04/21  王志葵 (中訊) モデル 594
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuyLimitDivDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3MFDealDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3MFReferenceDivDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.define.WEB3RemarkDivDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3MutualBuyListRequest;
import webbroker3.mf.message.WEB3MutualBuyListResponse;
import webbroker3.mf.message.WEB3MutualBuyProductGroup;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3MutualBuyListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託買付一覧照会サービス実装クラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyListServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualBuyListService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyListServiceImpl.class);
    /**
     * 投資信託買付一覧照会サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)買付一覧照会」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40AC775C0056
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualBuyListRequest l_mutualBuyListRequest = null;
        if (l_request instanceof WEB3MutualBuyListRequest)
        {
            //リクエストデータの具象データ型が「投信買付注文入力リクエスト」の場合
            l_mutualBuyListRequest = (WEB3MutualBuyListRequest) l_request;
        }
        else
        {
            log.debug(
                "the parameter of method isn't WEB3MutualBuyListRequest type");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1)　@リクエストデータのvalidate処理
        l_mutualBuyListRequest.validate();
        
        //投信・外貨MMF表示区分
        //※nullの場合、「0:投信のみ」とする
        if (l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv == null)
        {
            l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND;
        }

        //1.2)　@this.get補助口座( )をコールし、補助口座オブジェクトを取得する
        SubAccount l_subAccount = this.getSubAccount();
        
        //顧客別取引停止属性チェック
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);  
        
        //1.3)　@FinApp.getCommonOrderValidator( )をコールし、注文チェックオブジェクトを取得する
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.4)get投信発注日( )
        Timestamp l_datBizDate = 
            new Timestamp(WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate().getTime());
        
        //1.5）　@注文チェック.validate取引可能顧客( )をコールし例外が返された場合、例外をスローする
        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        OrderValidationResult l_validationResult =  
            l_gentradeOrderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.6)　@受付時間チェック・システム取引停止チェック
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.7)投信銘柄カテゴリーコードを検索する。
        //で取得した補助口座オブジェクトより証券会社オブジェクトを取得する
        Institution l_institution = l_subAccount.getInstitution();
        //で取得した証券会社オブジェクトより証券会社コードを取得する
        String l_institutionCode = l_institution.getInstitutionCode();
        //投信銘柄カテゴリーコード検索        
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        //拡張投信銘柄マネージャ.get投信銘柄カテゴリーリスト( )をコールする
        List l_mutualFundProductCategoryList =
            l_mutualFundProductManager.getMutualFundProductCategoryList(
                l_institutionCode);
        
        //1.8.2)リクエストデータ.投信銘柄カテゴリーコード != nullの場
        //の結果が1件以上存在した場合、拡張投信銘柄マネージャ.create投信銘柄カテゴリー一覧( )を
        //コールして、投信銘柄カテゴリー一覧を作成する
        WEB3MutualProductCategoryUnit[] l_mutualProductCategoryUnits = null;
        if (l_mutualFundProductCategoryList != null && l_mutualFundProductCategoryList.size() != 0)
        {
            //1.8.2.1)投信銘柄カテゴリー一覧
            l_mutualProductCategoryUnits =
                l_mutualFundProductManager.createMutualFundProductCategoryList(
                    l_mutualFundProductCategoryList);
        }
        //1.8)get投信銘柄カテゴリーリスト()の戻り>0件の場合、投信銘柄カテゴリー一覧をnullとする        
        else
        {
            l_mutualProductCategoryUnits = null;
        }
        
        //1.9)検索条件追加
        //銘柄検索処理 
        //検索条件データコンテナの作成        
        //1.8で取得した投信銘柄カテゴリー一覧がnullまたは0件
        // 以外の場合、かつ投信買付一覧照会リクエスト.投信銘柄カテゴリーコードが 
        //   null以外の場合、
        
        String[] l_strSearchCondDataContainer = null;
        if (!(l_mutualProductCategoryUnits == null || l_mutualProductCategoryUnits.length == 0)
            && l_mutualBuyListRequest.categoryCode != null)
        {
            l_strSearchCondDataContainer =
                this.createSearchCondDataContainer(
                    l_institutionCode,
                    l_mutualBuyListRequest.categoryCode);
        }
        
        //1.9 ＜検索条件追加＞
        // create検索条件データコンテナ()の戻り値に、以下の値を追加する。 
        
		//○リクエスト.照会区分 == ”買付一覧” の場合 
		//“1：WEBEBROKERⅢで取り扱う” 
		//”0：買付可能”
        //”現在日時（yyyyMMddHHmmss）” 
        //”現在日時（yyyyMMdd）” 
        //”現在日時（yyyyMMddHHmmss）” 
        //”現在日時（yyyyMMdd）”
        String[] l_strDivDef = null;
        //現在日付の取得 
        //ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。
        Timestamp l_tmsSystemTimestamp = 
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);           
        SimpleDateFormat l_simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        if (WEB3MFReferenceDivDef.BUY_REFERENCE.equals(l_mutualBuyListRequest.referenceType))
        {            
            l_strDivDef = new String[6];
            l_strDivDef[0] = WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN;
            l_strDivDef[1] = WEB3BuyLimitDivDef.BUY_POSSIBLE;
            l_strDivDef[2] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[3] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
            l_strDivDef[4] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[5] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
        }

        //○リクエスト.照会区分 == ”目論見書郵送請求一覧” の場合
		//“1：WEBEBROKERⅢで取り扱う”
        //”0：買付可能”
        //”現在日時（yyyyMMddHHmmss）”
        //”現在日時（yyyyMMdd）”
        //”現在日時（yyyyMMddHHmmss）”
        //”現在日時（yyyyMMdd）”
		//”2：郵送請求のみ”
        else if (WEB3MFReferenceDivDef.BOOK_REFERENCE.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strDivDef = new String[6];
            l_strDivDef[0] = WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN;
            l_strDivDef[1] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[2] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_strDivDef[3] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[4] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_strDivDef[5] = WEB3SystemHandlingDivDef.MAIL_REQUEST_ONLY;
        }
        
		//○リクエスト.照会区分 == ”買付のみ” の場合 
		//・“1：WEBEBROKER?で取り扱う”  
		//・”0：買付可能”  
		//・”現在日時（yyyyMMddHHmmss）”  
		//・”現在日時（yyyyMMdd）”  
        else if(WEB3MFReferenceDivDef.BUY.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strDivDef = new String[4];
            l_strDivDef[0] = WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN;
            l_strDivDef[1] = WEB3BuyLimitDivDef.BUY_POSSIBLE;
            l_strDivDef[2] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[3] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
        }
        
		//○リクエスト.照会区分 == ”募集のみ” の場合 
		//・“1：WEBEBROKER?で取り扱う”  
		//・”現在日時（yyyyMMddHHmmss）”  
		//・”現在日時（yyyyMMdd）”  
        else if(WEB3MFReferenceDivDef.RECRUIT.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strDivDef = new String[3];
            l_strDivDef[0] = WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN;
            l_strDivDef[1] = l_simpleDateFormat.format(l_tmsSystemTimestamp);
            l_strDivDef[2] = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
        }

        String[] l_strWhereValues = null;
        if (l_strSearchCondDataContainer != null)
        {
            l_strWhereValues =
                new String[l_strDivDef.length + l_strSearchCondDataContainer.length];
            System.arraycopy(l_strDivDef, 0, l_strWhereValues, 0, l_strDivDef.length);
            System.arraycopy(l_strSearchCondDataContainer, 0, l_strWhereValues,
                l_strDivDef.length, l_strSearchCondDataContainer.length);
        }
        else
        {
            l_strWhereValues = l_strDivDef;
        }
        
        // 1.10)　@＜検索条件文字列作成＞
        String l_strWhere = null;
        
		//①@検索条件文字列として、以下の文字列を設定する。 
		//○リクエスト.照会区分 == ”買付一覧” の場合 
		//" システム取扱区分=? and  
		//(  ( 買付制限区分=? and 買付開始日（YYYYMMDDHH24MISS）=<?　@
        //and ? <= 買付終了日（YYYYMMDD）) or 
		//( 募集開始日（SONAR）（YYYYMMDDHH24MISS）=<?　@
        //and ? <= 募集終了日（SONAR）（YYYYMMDD）)  )  " 
        if (WEB3MFReferenceDivDef.BUY_REFERENCE.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strWhere = " system_handling_div = ? and ((buy_limit_div = ? and " + 
            	"to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? and " + 
            	"to_char(buy_end_date, 'YYYYMMDD') >= ? ) or " + 
            	"(to_char(recruit_start_date_sonar, 'YYYYMMDDHH24MISS') <= ? and " +
            	"to_char(recruit_end_date_sonar, 'YYYYMMDD') >= ?))";
        }
        
		//○リクエスト.照会区分 == ”目論見書郵送請求一覧” の場合
		//"( ( システム取扱区分 = ? and
        //( ( 買付開始日（YYYYMMDDHH24MISS） =< ?　@and ? <= 買付終了日（YYYYMMDD） ) or
        // ( 募集開始日（SONAR）（YYYYMMDDHH24MISS） =< ?　@and ? <= 募集終了日（SONAR）（YYYYMMDD） ) ) ) or
        //システム取扱区分 = ? )"
        else if (WEB3MFReferenceDivDef.BOOK_REFERENCE.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strWhere = " ((system_handling_div = ? and (( " +
                "to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? and " +
                "to_char(buy_end_date, 'YYYYMMDD') >= ? ) or " +
                "(to_char(recruit_start_date_sonar, 'YYYYMMDDHH24MISS') <= ? and " +
                "to_char(recruit_end_date_sonar, 'YYYYMMDD') >= ?))) " +
                "or system_handling_div = ?) ";
        }
        
		//○リクエスト.照会区分 == ”買付のみ” の場合 
		//" システム取扱区分=? and 買付制限区分=? and  
		//買付開始日（YYYYMMDDHH24MISS）=<?　@and ? <= 買付終了日（YYYYMMDD） " 
        else if (WEB3MFReferenceDivDef.BUY.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strWhere = " system_handling_div = ? and buy_limit_div = ? and " + 
            	"(to_char(buy_start_date, 'YYYYMMDDHH24MISS') <= ? and " + 
            	"to_char(buy_end_date, 'YYYYMMDD') >= ?)";
        }
        
		//○リクエスト.照会区分 == ”募集のみ” の場合 
		//" システム取扱区分=? and 募集開始日（SONAR）（YYYYMMDDHH24MISS）=<?　@" +
		//"and ? <= 募集終了日（SONAR）（YYYYMMDD） " 
        else if (WEB3MFReferenceDivDef.RECRUIT.equals(l_mutualBuyListRequest.referenceType))
        {
            l_strWhere = " system_handling_div = ? and " + 
            	"(to_char(recruit_start_date_sonar, 'YYYYMMDDHH24MISS') <= ? and " + 
            	"to_char(recruit_end_date_sonar, 'YYYYMMDD') >= ?)";
        }
       
        //②create投信銘柄カテゴリー一覧()の戻り値が null または 0件以外の場合であり、かつ 
        //リクエストデータ.投信銘柄カテゴリーコードが null 以外の場合、  
        //   文字列「" and カテゴリーコード in ("」と「")"」の間に、
        //create投信銘柄カテゴリー一覧()の結果の要素数分、","区切りの"?"を追加し、 
        //　@検索条件文字列に追加する。
        if (!(l_mutualProductCategoryUnits == null || l_mutualProductCategoryUnits.length == 0)
            && l_mutualBuyListRequest.categoryCode != null)
        {
            l_strWhere += "and category_code in ( ";
            for (int i = 0; i < l_strSearchCondDataContainer.length; i ++)
            {
                if (i < l_strSearchCondDataContainer.length - 1)
                {
                    l_strWhere += " ?, ";
                }
                else
                {
                    l_strWhere += " ? ) ";
                }
            }
        }   

        //＜リクエスト.投信・外貨MMF表示区分  !=  "両方"の場合＞
        if (!WEB3MutualFrgnMmfDisplayDivDef.BOTH.equals(
            l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv))
        {
            //検索条件・検索条件文字列を追加する
            //1）create検索条件データコンテナ()の戻り値に、以下の値を追加する。
            //・投信タイプ.外貨MMF
            l_strDivDef = new String[1];
            l_strDivDef[0] = MutualFundTypeEnum.FOREIGN_MMF.intValue() + "";
            String[] l_strValues = l_strWhereValues;
            l_strWhereValues =
                new String[l_strValues.length + l_strDivDef.length];
            System.arraycopy(l_strValues, 0, l_strWhereValues, 0, l_strValues.length);
            System.arraycopy(l_strDivDef, 0, l_strWhereValues,
                l_strValues.length, l_strDivDef.length);

            //2)検索条件文字列を追加する。
            //    2-1）リクエスト.投信・外貨MMF表示区分＝"投信のみ"の場合、"and 投信タイプ<>?"
            //    2-2）リクエスト.投信・外貨MMF表示区分＝"外貨MMFのみ"の場合、"and 投信タイプ=?"
            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(
                l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv))
            {
                l_strWhere += "and fund_type <> ?";
            }
            else if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(
                l_mutualBuyListRequest.mutualFrgnMmfDisplayDiv))
            {
                l_strWhere += "and fund_type = ?";
            }
        }

        //1.11)で作成した検索条件を元に、拡張投信銘柄マネージャ.
        //get投信銘柄リスト( )をコールし、検索結果を取得する。
        List l_mutualFundProductList =
            l_mutualFundProductManager.getMutualFundProductList(
                l_institutionCode,
                l_strWhere,
                l_strWhereValues,
                WEBMFSortConditionDivDef.MUTUAL_BUY_LIST);
        //1.12)　@検索結果が0件だった場合、"該当データ無し"として例外をスローする。
        //get投信銘柄リスト()の戻り値の件数分繰り返し、投信買付銘柄一覧行オブジェクトの配列を作成する
        if (l_mutualFundProductList == null || l_mutualFundProductList.size() == 0)
        {
            log.debug("検索結果が0件だった場合、該当データ無しとして例外をスローする");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当データ無し");
        }
        int l_intRecords = l_mutualFundProductList.size();
        //明細の生成
        //取引時間コンテキスト更新
        List l_lisMutualBuyProductGroup = new Vector();
        for (int j = 0; j < l_intRecords; j++)
        {
            MutualFundProductParams l_mutualFundProductParams =
                (MutualFundProductParams) l_mutualFundProductList.get(j);
            //1.12.1)　@拡張投信銘柄マネージャ.to投信銘柄( )をコールする    
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
                    l_mutualFundProductParams);
            //1.12.2)　@投信取引時間管理.reset銘柄コード( )をコールする
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_mutualFundProduct.getProductCode());
            //1.12.3)　@取引時間管理.setTimestamp()をコールする
            WEB3GentradeTradingTimeManagement.setTimestamp();
            //1.12.4)　@拡張投信取引銘柄の取得
            try
            {
                l_mutualFundProductManager.getMutualFundTradedProduct(
                        l_institution,
                        l_mutualFundProduct.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("no found WEB3MutualFundTradedProduct ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00372,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.12.5)　@投信買付銘柄一覧行クラスを生成する
            WEB3MutualBuyProductGroup l_mutualBuyProductGroup = 
                new WEB3MutualBuyProductGroup();
            
            //1.12.6)備考の設定
            String l_strNoteType = null;
            //1.12.6.1 is募集可能(Date)
            boolean l_blnIsRecruitPossible = 
                l_mutualFundProduct.isRecruitPossible(l_datBizDate);
            
            //1.12.6.2 if is募集可能() == true
            if(l_blnIsRecruitPossible)
            {
                log.debug("備考 = 募集期間中をセット");
                l_strNoteType = WEB3RemarkDivDef.RECRUIT_BETWEEN;
            }
            //1.12.6.3 if is募集可能() == false
            else
            {
                //1.12.6.3.1 is買付可能()
                // 買付停止中チェック 
                //投信銘柄.is買付可能( ) = falseの場合、
                //投信買付銘柄一覧行オブジェクト.備考"買付停止中"をセット。
               
                if (!l_mutualFundProduct.isAcquiredPossible(l_datBizDate))
                {
                    log.debug("備考 = 取引不可(買付停止中)をセット");
                    l_strNoteType = WEB3RemarkDivDef.HANDLING_DISABLE;
                }
            }

            // 1.12.6.4) 取引時間外チェック
            //投信取引時間管理.validate注文受付可能( )をコールし、
            //例外がスローされた場合"取引時間外注文停止中"をセット
            try
            {
                WEB3MutualFundTradingTimeManagement.validateOrderAccept();
            }
            catch (WEB3BaseException l_ex)
            {
                //1.12.6.5 validate注文受付可能()から例外が返された場合
                //＜“受付時間外”のセット＞
                log.debug("備考 = 取引時間外注文停止中（受付時間外）をセット");
                l_strNoteType = WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
            }
            
            //1.12.6.6) 緊急停止中チェック
            WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
                (WEB3MutualFundOrderManagerReusableValidationsCheck)
                    MutualFundProductTypeOrderManagerReusableValidations.getInstance();
            try
            {
				//処理区分： 
				//(*)is募集可能()==trueの場合は、”募集”を指定 
				//それ以外は”買付”を指定 
                if (l_blnIsRecruitPossible)
                {
                    l_validationsCheck.validateEmergencyStop(l_mutualFundProduct,
                        WEB3ProcessDivDef.RECRUIT); 
                }
                else
                {
                    l_validationsCheck.validateEmergencyStop(l_mutualFundProduct,
                        WEB3ProcessDivDef.BUY);
                }
            }
            catch(WEB3BaseException l_ex)
            {
                //1.12.6.7 validate緊急停止()から例外が返された場合
                //＜”緊急停止中”のセット＞
                log.debug("備考 = 緊急停止中をセット");
                l_strNoteType = WEB3RemarkDivDef.EMERGENCY_STOP;
            }
            
            //1.12.6.8is募集可能(SONAR)
            boolean l_blnIsRecruitPossibleSonar = 
                l_mutualFundProduct.isRecruitPossibleSonar();

            l_mutualBuyProductGroup.noteType = l_strNoteType;
            
            //1.12.6.9 if validate取引可能顧客()で募集取引OK、
            if (l_validationResult.getProcessingResult().isSuccessfulResult())  
            {
                //且つ、is募集可能(SONAR) == true
        		if ( l_blnIsRecruitPossibleSonar)
                {
                    //1.12.6.9.1)　@取得した拡張投信銘柄オブジェクトを元に、 
                    //生成した投信買付銘柄一覧行オブジェクトに以下のプロパティをセットする。 
                    //取引銘柄より、以下の値を投信買付銘柄一覧行にセットする。
                   
                    //ID                         = 投信銘柄.getProductId( )
                    l_mutualBuyProductGroup.id =
                        Long.toString(l_mutualFundProductParams.getProductId());
                    //銘柄コード                   = 投信銘柄.getProductCode( ) 
                    l_mutualBuyProductGroup.mutualProductCode = 
                        l_mutualFundProductParams.getProductCode();
                    //銘柄名　@　@　@　@　@　@　@　@　@　@　@ = 投信銘柄.get銘柄名( )
                    l_mutualBuyProductGroup.mutualProductName =
                        l_mutualFundProductParams.getStandardName();
                    //投信銘柄カテゴリーコード  = 投信銘柄.getカテゴリーコード( )
                    l_mutualBuyProductGroup.categoryCode =
                        l_mutualFundProductParams.getCategoryCode();
                    //買付基準価額通貨コード = 投信銘柄.get通貨コード( )
                    l_mutualBuyProductGroup.constantValueCurrencyCode =
                        l_mutualFundProductParams.getCurrencyCode();
                    //買付基準価額　@　@　@　@　@　@  = 投信銘柄.get募集価額( ) 
                    if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mutualFundProductParams.getFundType())
                        && l_mutualFundProductParams.getRecruitConstantValueIsNull())
                    {//打鍵票指摘事項( 2007/3/5  打鍵票_買付一覧（正常系）)
                        l_mutualBuyProductGroup.constantValue = null;
                    }
                    else
                    {
                        l_mutualBuyProductGroup.constantValue =
                            WEB3StringTypeUtility.formatNumber(
                                l_mutualFundProduct.getRecruitConstantValue());
                    }

                    //買付基準価額適用日　@　@ = null;
                    l_mutualBuyProductGroup.constantValueAppDate = null;
   				  	//新規買付時単位口数　@　@ = 投信銘柄.get単位口数(募集)( ) 
                    if (!l_mutualFundProductParams.getRecruitUnitQtyIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyUnitQty = 
                            l_mutualFundProductParams.getRecruitUnitQty() + "";
                    }
   					//新規買付時最低口数　@　@ = 投信銘柄.get最低口数(募集)( ) 
                    if(!l_mutualFundProductParams.getRecruitMinQtyIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyMinQty = 
                            l_mutualFundProductParams.getRecruitMinQty() + "";
                    }
   					//新規買付時単位金額　@　@ = 投信銘柄.get単位金額(募集)( ) 
                    if(!l_mutualFundProductParams.getRecruitUnitAmtIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyUnitAmt = 
                            l_mutualFundProductParams.getRecruitUnitAmt() + "";
                    }
   					//新規買付時最低金額　@　@ = 投信銘柄.get最低金額(募集)( ) 
                    if(!l_mutualFundProductParams.getRecruitMinAmtIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyMinAmt = 
                            l_mutualFundProductParams.getRecruitMinAmt() + "";
                    }
                    //追加買付時単位口数　@　@ = null
                    l_mutualBuyProductGroup.addBuyUnitQty = null;
                    //追加買付時最低口数　@　@ = null
                    l_mutualBuyProductGroup.addBuyMinQty = null;
                    //追加買付時単位金額　@　@ = null
                    l_mutualBuyProductGroup.addBuyUnitAmt = null;
                    //追加買付時最低金額　@　@ = null
                    l_mutualBuyProductGroup.addBuyMinAmt = null;
                   
                    //注文受付締切時間　@ 　@　@ = 
                    //投信取引時間管理.get注文受付締切( )をコールし、 
                    //戻された値の１秒後の時間"HHMMSS"を"HH:MM"に編集してセットする。

                    String l_strOrderCloseTime = 
                        WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
                    Date l_datOrderCloseTime = 
                        WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
                    l_strOrderCloseTime = 
                        WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss");  
                    l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
                        + WEB3GentradeTimeDef.STR_COLON
                        + l_strOrderCloseTime.substring(2, 4);
                    l_mutualBuyProductGroup.orderCloseTime = 
                        l_strOrderCloseTime;
                   
                    //円転基準価額 
                    //　@　@1）投信銘柄.get通貨コード()がT0の場合 または
                    //　@　@    投信銘柄.is外貨MMF＝trueの場合
                    //　@　@　@　@nullをセットする。                  
                    //　@　@2）投信銘柄.get通貨コード()がT0でない場合 かつ
                    //　@　@　@　@投信銘柄.is外貨MMF＝falseの場合
                    //　@　@　@　@拡張投信銘柄.get円転基準価額(WEB3MFDealDivDef.買付)をセットする。 
                    //・参考レート 
                    //　@　@1）投信銘柄.get通貨コード( )がT0の場合 
                    //　@　@　@ nullをセットする。 
                    //　@　@2）投信銘柄.get通貨コード( )がT0でない場合 
                    //        2-1) 投信銘柄.is外貨MMF ＝ trueの場合
                    //             拡張投信銘柄.get外貨MMF為替レート()の戻り値外貨MMF為替レートParamsのTTS
                    //              をセットする。（小数第３位で四捨五入）
                    //       2-2) 投信銘柄.is外貨MMF ＝ falseの場合
                    //　@　@　@ 拡張投信銘柄.get為替レート()の戻り値為替レートParamsのTTS / 同為替レート計算単位 
                    //　@　@　@をセットする。（小数第３位で四捨五入）
                    String l_strCurrencyCode = l_mutualFundProductParams.getCurrencyCode();
                    if (l_mutualFundProduct.isFrgnMmf())
                    {
                        l_mutualBuyProductGroup.yenConstantValue = null;
                    }
                    if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_strCurrencyCode)) 
                    {
                        l_mutualBuyProductGroup.yenConstantValue = null;
                        l_mutualBuyProductGroup.referenceRate = null;
                    }
                    else 
                    {
                        if (l_mutualFundProduct.isFrgnMmf())
                        {
                            BigDecimal l_bdTtSellingRate =
                                new BigDecimal(l_mutualFundProduct.getFrgnMmfExchangeRate().getTtSellingRate());
                            l_mutualBuyProductGroup.referenceRate =
                                WEB3StringTypeUtility.formatNumber(
                                    l_bdTtSellingRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                        else
                        {
                            l_mutualBuyProductGroup.yenConstantValue =
                                l_mutualFundProduct.getYenConstantValue(WEB3MFDealDivDef.BUY); 
                            BigDecimal l_bdTtSellingRate = 
                                new BigDecimal(l_mutualFundProduct.getExchangeRate().getTtSellingRate());
                            BigDecimal l_bdExchangeCalcUnit = 
                                new BigDecimal(l_mutualFundProduct.getExchangeRate().getExchangeCalcUnit());
                            l_mutualBuyProductGroup.referenceRate = 
                                WEB3StringTypeUtility.formatNumber(
                                    l_bdTtSellingRate.divide(l_bdExchangeCalcUnit, 2, BigDecimal.ROUND_HALF_UP).doubleValue()); 
                        }
                    }
                   
					//   ・募集開始日　@= 拡張投信銘柄.get募集開始日 
					//   ・募集終了日　@= 拡張投信銘柄.get募集終了日 
					//   ・募集開始日（SONAR）　@= 拡張投信銘柄.get募集開始日（SONAR） 
					//   ・募集終了日（SONAR）　@= 拡張投信銘柄.get募集終了日（SONAR）
                    l_mutualBuyProductGroup.applyAbleStartDate = l_mutualFundProduct.getRecruitStartDate();
                    l_mutualBuyProductGroup.applyAbleEndDate = l_mutualFundProduct.getRecruitEndDate();
                    l_mutualBuyProductGroup.applyAbleStartDateSonar = l_mutualFundProduct.getApplyAbleStartDateSonar();
                    l_mutualBuyProductGroup.applyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();               
                   
                    //・参考レート確定日
                    //    1）投信銘柄.get通貨コード( )がT0の場合
                    //       nullをセットする。
                    if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_strCurrencyCode))
                    {
                        l_mutualBuyProductGroup.referenceRateFixedDay = null;
                    }

                    //    2）投信銘柄.get通貨コード( )がT0でない場合
                    else
                    {
                        //       2-1) 投信銘柄.is外貨MMF ＝ trueの場合
                        //            拡張投信銘柄.get外貨MMF為替レート()の戻り値
                        //            外貨MMF為替レートParamsの為替レート確定日をセットする。
                        if (l_mutualFundProduct.isFrgnMmf())
                        {
                            l_mutualBuyProductGroup.referenceRateFixedDay =
                                l_mutualFundProduct.getFrgnMmfExchangeRate().getExecTimestamp();
                        }
                        //       2-2) 投信銘柄.is外貨MMF ＝ falseの場合
                        //            拡張投信銘柄.get為替レート()の戻り値
                        //            為替レートParamsの為替レート確定日をセットする。
                        else
                        {
                            l_mutualBuyProductGroup.referenceRateFixedDay =
                                l_mutualFundProduct.getExchangeRate().getExecTimestamp();
                        }
                    }

                    //・外貨MMFフラグ = 投信銘柄.is外貨MMF
                    l_mutualBuyProductGroup.frgnMmfFlag = l_mutualFundProduct.isFrgnMmf();

                    //・指定方法@ = 投信銘柄.get指定方法@（募集）
                    l_mutualBuyProductGroup.buySelectable =
                        l_mutualFundProduct.getRecruitSpecityDiv();

                    //1.12.6.9.2 投信買付銘柄一覧行オブジェクトへ追加
                    l_lisMutualBuyProductGroup.add(l_mutualBuyProductGroup);
               }
               
               //1.12.6.10 且つ、is募集可能(SONAR) == false
               else
               {
                   //1.12.6.10.1)　@取得した拡張投信銘柄オブジェクトを元に、 
                   //生成した投信買付銘柄一覧行オブジェクトに以下のプロパティをセットする。 
                   //取引銘柄より、以下の値を投信買付銘柄一覧行にセットする。
                   
                   //ID                         = 投信銘柄.getProductId( )
                   l_mutualBuyProductGroup.id =
                       Long.toString(l_mutualFundProductParams.getProductId());
                   //銘柄コード                   = 投信銘柄.getProductCode( ) 
                   l_mutualBuyProductGroup.mutualProductCode = 
                       l_mutualFundProductParams.getProductCode();
                   //銘柄名　@　@　@　@　@　@　@　@　@　@　@ = 投信銘柄.get銘柄名( )
                   l_mutualBuyProductGroup.mutualProductName =
                       l_mutualFundProductParams.getStandardName();
                   //投信銘柄カテゴリーコード  = 投信銘柄.getカテゴリーコード( )
                   l_mutualBuyProductGroup.categoryCode =
                       l_mutualFundProductParams.getCategoryCode();
                   //買付基準価額通貨コード = 投信銘柄.get通貨コード( )
                   l_mutualBuyProductGroup.constantValueCurrencyCode =
                       l_mutualFundProductParams.getCurrencyCode();
                   //買付基準価額　@　@　@　@　@　@  = 投信銘柄.get買付基準価額( )
                   if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_mutualFundProductParams.getFundType())
                       &&l_mutualFundProductParams.getBuyConstantValueIsNull())
                   {//打鍵票指摘事項( 2007/3/5  打鍵票_買付一覧（正常系）)
                       l_mutualBuyProductGroup.constantValue = null;
                   }
                   else
                   {
                       l_mutualBuyProductGroup.constantValue =
                           WEB3StringTypeUtility.formatNumber(
                               l_mutualFundProduct.getConstantValue());
                   }

                   //買付基準価額適用日　@　@ = 投信銘柄.get基準価額適用日( )
                   l_mutualBuyProductGroup.constantValueAppDate =
                       l_mutualFundProductParams.getConstantValueAppDate();
                   //新規買付時単位口数　@　@ = 投信銘柄.get単位口数(新規買付)( )
                   if (!l_mutualFundProductParams.getNewBuyUnitQtyIsNull())
                   {
                       l_mutualBuyProductGroup.newBuyUnitQty =
                           Long.toString(l_mutualFundProductParams.getNewBuyUnitQty());
                   }
                   //新規買付時最低口数　@　@ = 投信銘柄.get最低口数(新規買付)( )
                   if(!l_mutualFundProductParams.getNewBuyMinQtyIsNull())
                   {
                       l_mutualBuyProductGroup.newBuyMinQty =
                           Long.toString(l_mutualFundProductParams.getNewBuyMinQty());
                   }
                   //新規買付時単位金額　@　@ = 投信銘柄.get単位金額(新規買付)( )
                   if (!l_mutualFundProductParams.getNewBuyUnitAmtIsNull())
                   {
                       l_mutualBuyProductGroup.newBuyUnitAmt =
                           Long.toString(l_mutualFundProductParams.getNewBuyUnitAmt());
                   }
                   //新規買付時最低金額　@　@ = 投信銘柄.get最低金額(新規買付)( )
                   if (!l_mutualFundProductParams.getNewBuyMinAmtIsNull())
                   {
                       l_mutualBuyProductGroup.newBuyMinAmt =
                           Long.toString(l_mutualFundProductParams.getNewBuyMinAmt());
                   }
                   //追加買付時単位口数　@　@ = 投信銘柄.get単位口数(追加買付)( )
                   if(!l_mutualFundProductParams.getAddBuyUnitQtyIsNull())
                   {
                       l_mutualBuyProductGroup.addBuyUnitQty =
                           Long.toString(l_mutualFundProductParams.getAddBuyUnitQty());
                   }
                   //追加買付時最低口数　@　@ = 投信銘柄.get最低口数(追加買付)( )
                   if (!l_mutualFundProductParams.getAddBuyMinQtyIsNull())
                   {
                       l_mutualBuyProductGroup.addBuyMinQty =
                           Long.toString(l_mutualFundProductParams.getAddBuyMinQty());
                   }
                   //追加買付時単位金額　@　@ = 投信銘柄.get単位金額(追加買付)( )
                   if (!l_mutualFundProductParams.getAddBuyUnitAmtIsNull())
                   {
                       l_mutualBuyProductGroup.addBuyUnitAmt =
                           Long.toString(l_mutualFundProductParams.getAddBuyUnitAmt());
                   }
                   //追加買付時最低金額　@　@ = 投信銘柄.get最低金額(追加買付)( )
                   if (!l_mutualFundProductParams.getAddBuyMinAmtIsNull())
                   {
                       l_mutualBuyProductGroup.addBuyMinAmt =
                           Long.toString(l_mutualFundProductParams.getAddBuyMinAmt());
                   }
                   //注文受付締切時間　@ 　@　@ = 投信取引時間管理.get注文受付締切時間( )をコールし戻された値"HHMMSS"を"HH:MM"に編集してセット。
                   String l_strOrderCloseTime = 
                       WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
                   Date l_datOrderCloseTime = 
                       WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
                   l_strOrderCloseTime = 
                       WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss");  
                   l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
                   	+ WEB3GentradeTimeDef.STR_COLON
                   	+ l_strOrderCloseTime.substring(2, 4);
                   l_mutualBuyProductGroup.orderCloseTime = 
                       l_strOrderCloseTime;
                   
                   //円転基準価額 
                   //　@　@1）投信銘柄.get通貨コード()がT0の場合 または
                   //  　@　@投信銘柄.is外貨MMF＝trueの場合
                   //　@　@　@　@nullをセットする。                  
                   //　@　@2）投信銘柄.get通貨コード()がT0でない場合 かつ
                   //　@　@　@　@投信銘柄.is外貨MMF＝falseの場合
                   //　@　@　@　@拡張投信銘柄.get円転基準価額(WEB3MFProcessDivDef.買付)をセットする。 
                   //・参考レート 
                   //　@　@1）投信銘柄.get通貨コード( )がT0の場合 
                   //　@　@　@ nullをセットする。 
                   //　@　@2）投信銘柄.get通貨コード( )がT0でない場合 
                   //       2-1) 投信銘柄.is外貨MMF ＝ trueの場合
                   //              拡張投信銘柄.get外貨MMF為替レート()の戻り値外貨MMF為替レートParamsのTTS
                   //              をセットする。（小数第３位で四捨五入）
                   //       2-2) 投信銘柄.is外貨MMF ＝ falseの場合
                   //　@　@　@ 拡張投信銘柄.get為替レート()の戻り値為替レートParamsのTTS / 同為替レート計算単位 
                   //　@　@　@をセットする。（小数第３位で四捨五入）
                   if (l_mutualFundProduct.isFrgnMmf())
                   {
                       l_mutualBuyProductGroup.yenConstantValue = null;
                   }
                   if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mutualFundProductParams.getCurrencyCode())) 
                   {
                       l_mutualBuyProductGroup.yenConstantValue = null;
                       l_mutualBuyProductGroup.referenceRate = null;
                   }
                   else 
                   {
                       if (l_mutualFundProduct.isFrgnMmf())
                       {
                           BigDecimal l_bdTtSellingRate =
                               new BigDecimal(l_mutualFundProduct.getFrgnMmfExchangeRate().getTtSellingRate());
                           l_mutualBuyProductGroup.referenceRate =
                               WEB3StringTypeUtility.formatNumber(
                                   l_bdTtSellingRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                       }
                       else
                       {
                           l_mutualBuyProductGroup.yenConstantValue = 
                               l_mutualFundProduct.getYenConstantValue(WEB3MFProcessDivDef.BUY); 
                           BigDecimal l_bdTtSellingRate = 
                               new BigDecimal(l_mutualFundProduct.getExchangeRate().getTtSellingRate());
                           BigDecimal l_bdExchangeCalcUnit = 
                               new BigDecimal(l_mutualFundProduct.getExchangeRate().getExchangeCalcUnit());
                           l_mutualBuyProductGroup.referenceRate = 
                               WEB3StringTypeUtility.formatNumber(
                                   l_bdTtSellingRate.divide(l_bdExchangeCalcUnit, 2, BigDecimal.ROUND_HALF_UP).doubleValue()); 
                       }
                   }
                    //   ・募集開始日　@= 拡張投信銘柄.get募集開始日 
					//   ・募集終了日　@= 拡張投信銘柄.get募集終了日 
					//   ・募集開始日（SONAR）　@= 拡張投信銘柄.get募集開始日（SONAR） 
					//   ・募集終了日（SONAR）　@= 拡張投信銘柄.get募集終了日（SONAR）
				   	l_mutualBuyProductGroup.applyAbleStartDate = l_mutualFundProduct.getRecruitStartDate();
				   	l_mutualBuyProductGroup.applyAbleEndDate = l_mutualFundProduct.getRecruitEndDate();
				   	l_mutualBuyProductGroup.applyAbleStartDateSonar = l_mutualFundProduct.getApplyAbleStartDateSonar();
				   	l_mutualBuyProductGroup.applyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();  
                   
                     //・参考レート確定日
                     //    1）投信銘柄.get通貨コード( )がT0の場合
                     //       nullをセットする。
                    if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(
                        l_mutualFundProductParams.getCurrencyCode()))
                    {
                        l_mutualBuyProductGroup.referenceRateFixedDay = null;
                    }

                     //    2）投信銘柄.get通貨コード( )がT0でない場合
                    else
                    {
                        //       2-1) 投信銘柄.is外貨MMF ＝ trueの場合
                        //            拡張投信銘柄.get外貨MMF為替レート()の戻り値
                        //            外貨MMF為替レートParamsの為替レート確定日をセットする。
                        if (l_mutualFundProduct.isFrgnMmf())
                        {
                            l_mutualBuyProductGroup.referenceRateFixedDay =
                                l_mutualFundProduct.getFrgnMmfExchangeRate().getExecTimestamp();
                        }

                        //       2-2) 投信銘柄.is外貨MMF ＝ falseの場合
                        //            拡張投信銘柄.get為替レート()の戻り値
                        //            為替レートParamsの為替レート確定日をセットする。
                        else
                        {
                            l_mutualBuyProductGroup.referenceRateFixedDay =
                                l_mutualFundProduct.getExchangeRate().getExecTimestamp();
                        }
                    }

                    //・外貨MMFフラグ = 投信銘柄.is外貨MMF
                    l_mutualBuyProductGroup.frgnMmfFlag = l_mutualFundProduct.isFrgnMmf();
                    //新規買付時外貨単位金額　@　@ = 投信銘柄.get外貨単位金額(新規買付)( )
                    if (!l_mutualFundProductParams.getFrgnNewBuyUnitAmtIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyFrgnUnitAmt =
                            l_mutualFundProduct.getFrgnNewBuyUnitAmt() + "";
                    }
                    //・新規買付時外貨最低金額　@　@ = 投信銘柄.get外貨最低金額(新規買付)( )
                    if (!l_mutualFundProductParams.getFrgnNewBuyMinAmtIsNull())
                    {
                        l_mutualBuyProductGroup.newBuyFrgnMinAmt =
                            l_mutualFundProduct.getFrgnNewBuyMinAmt() + "";
                    }
                    //・追加買付時外貨単位金額　@　@ = 投信銘柄.get外貨単位金額(追加買付)( )
                    if (!l_mutualFundProductParams.getFrgnAddBuyUnitAmtIsNull())
                    {
                        l_mutualBuyProductGroup.addBuyFrgnUnitAmt =
                            l_mutualFundProduct.getFrgnAddBuyUnitAmt() + "";
                    }
                    //・追加買付時外貨最低金額　@　@ = 投信銘柄.get外貨最低金額(追加買付)( )
                    if (!l_mutualFundProductParams.getFrgnAddBuyMinAmtIsNull())
                    {
                        l_mutualBuyProductGroup.addBuyFrgnMinAmt =
                            l_mutualFundProduct.getFrgnAddBuyMinAmt() + "";
                    }

                    //・指定方法@ = 投信銘柄.get指定方法@(買付)
                    l_mutualBuyProductGroup.buySelectable =
                        l_mutualFundProduct.getBuySelectable();

                   //1.12.6.10.2 投信買付銘柄一覧行オブジェクトへ追加
                   l_lisMutualBuyProductGroup.add(l_mutualBuyProductGroup);
               }
            }
        }
        WEB3MutualBuyProductGroup[] l_mutualBuyProductGroups = 
            new WEB3MutualBuyProductGroup[l_lisMutualBuyProductGroup.size()];
        l_lisMutualBuyProductGroup.toArray(l_mutualBuyProductGroups);
        //　@ページング処理 
        //1.13)　@投信買付一覧照会リクエスト.createレスポンス( )メソッドをコールし、投信買付一覧照会レスポンスクラスを生成する。 
        WEB3MutualBuyListResponse l_mutualBuyListResponse =
            (WEB3MutualBuyListResponse) l_mutualBuyListRequest.createResponse();
        //1.14)　@投信買付一覧照会レスポンスの以下の項目を設定する
        //○投信買付一覧照会レスポンス.総ページ数: 
        int l_int = l_intRecords / Integer.parseInt(l_mutualBuyListRequest.pageSize);
        if (l_intRecords == 0)
        {
            l_mutualBuyListResponse.totalPages = "0";
        }
        else if (l_intRecords % Integer.parseInt(l_mutualBuyListRequest.pageSize) == 0)
        {
            l_mutualBuyListResponse.totalPages = l_int + "";
        }
        else if (l_intRecords % Integer.parseInt(l_mutualBuyListRequest.pageSize) > 0)
        {
            l_mutualBuyListResponse.totalPages = Integer.toString(l_int + 1);
        }

        //○投信買付一覧照会レスポンス.総レコード数:　@７)で確定した明細の要素数
        l_mutualBuyListResponse.totalRecords = Integer.toString(l_intRecords);
        //○投信買付一覧照会レスポンス.表示ページ番号(表示が何ページ目にあたるか):以下をセット
        if (l_intRecords > Integer.parseInt(l_mutualBuyListRequest.pageSize)
            * (Integer.parseInt(l_mutualBuyListRequest.pageIndex) - 1))
        {
            l_mutualBuyListResponse.pageIndex = l_mutualBuyListRequest.pageIndex;
        }
        else
        {
            l_mutualBuyListResponse.pageIndex = l_mutualBuyListResponse.totalPages;
        }
        //設定後、投信買付一覧照会レスポンス.総ページ数 = 0 の場合は、
        //投信買付一覧照会レスポンス.買付銘柄一覧(投信買付銘柄一覧行[ ])にnullをセットし
        //例外をスローする。
        if ("0".equals(l_mutualBuyListResponse.totalPages))
        {
            l_mutualBuyListResponse.buyProductGroups = null;
            log.debug("投信買付一覧照会レスポンス.総ページ数 = 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00397,
                STR_METHOD_NAME);
        }
        //　@明細のセット
        //(投信買付一覧照会リクエスト.ページ内表示行数×(投信買付一覧照会レスポンス.
        //表示ページ番号 - 1)数分、７)で確定した投信買付一覧照会レスポンス明細データ一覧の
        //要素をスキップする
        // -------------- Record Begin
        int l_intRecordBegin =
            Integer.parseInt(l_mutualBuyListRequest.pageSize)
                * (Integer.parseInt(l_mutualBuyListResponse.pageIndex) - 1);
        // -------------- Record End
        int l_intRecordEnd = 0;
        if (l_mutualBuyListResponse.pageIndex.equals(l_mutualBuyListResponse.totalPages))
        {
            l_intRecordEnd = l_intRecords;
        }
        else
        {
            l_intRecordEnd = l_intRecordBegin + Integer.parseInt(l_mutualBuyListRequest.pageSize);
        }
        //投信買付一覧照会レスポンス明細データを投信買付一覧照会レスポンスデータ.買付銘柄一覧としてセットする。
        List l_lisBuyProductGroups = new Vector();
        for (int i = l_intRecordBegin ; i < l_intRecordEnd; i++) 
        {
            // ------------ 投信買付一覧照会レスポンス明細データ一覧の要素をセットする
            l_lisBuyProductGroups.add(l_mutualBuyProductGroups[i]);
        }
        WEB3MutualBuyProductGroup[] l_BuyProductGroups = new WEB3MutualBuyProductGroup[l_lisBuyProductGroups.size()];
        l_lisBuyProductGroups.toArray(l_BuyProductGroups); 
        l_mutualBuyListResponse.buyProductGroups = l_BuyProductGroups;
        // で取得した投信銘柄カテゴリー一覧を、投信買付一覧照会レスポンスデータ 
        //  .投信銘柄カテゴリー一覧にセットする。
        l_mutualBuyListResponse.categoryList = l_mutualProductCategoryUnits;
        log.exiting(STR_METHOD_NAME);
        return l_mutualBuyListResponse;
    }
    /**
     * (create検索条件データコンテナ)<BR>
     * 指定されたカテゴリーコードから、検索条件となりうる全てのカテゴリーコードを<BR>
     * 取得し、それを配列にして返却する。<BR>
     * <BR>
     * １)　@拡張投信銘柄マネージャ.get下位投信銘柄カテゴリーリスト( )をコールする。<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@引数:証券会社コード<BR>
     * 　@　@　@　@　@引数:カテゴリーコード<BR>
     * <BR>
     * ２)　@１)で検索結果として取得した全てのカテゴリーコードをStringの配列に<BR>
     * 　@格納してリターンする。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strCategoryCode - 銘柄カテゴリーコード
     * @@return String[ ]
     * @@roseuid 40D17FC00150
     */
    public String[] createSearchCondDataContainer(
        String l_strInstitutionCode,
        String l_strCategoryCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createSearchCondDataContainer(String l_strInstitutionCode,String l_strCategoryCode)";
        if("".equals(l_strInstitutionCode) || "".equals(l_strCategoryCode))
        {
            log.debug("the parameter of mothed l_strInstitutionCode " +
                "or l_strCategoryCode is null or blank");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１)　@拡張投信銘柄マネージャ.get下位投信銘柄カテゴリーリスト( )をコールする
        List l_LowMutualFundProductCategoryList = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();
        try
        {
            l_LowMutualFundProductCategoryList =
                l_mutualFundProductManager.getLowMutualFundProductCategoryList(
                    l_strInstitutionCode,
                    l_strCategoryCode);
        }
        catch (WEB3BaseException e)
        {
            log.error(" no found LowMutualFundProductCategoryList");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getErrorMessage(),
                e);
        }
        // ２)　@１)で検索結果として取得した全てのカテゴリーコードをStringの配列に格納してリターンする。
        if (l_LowMutualFundProductCategoryList == null)
        {
            log.debug("下位投信銘柄カテゴリーリスト null");
            l_LowMutualFundProductCategoryList = new Vector();
        }
        int l_intmfProductCategory = l_LowMutualFundProductCategoryList.size();
        String[] l_strMFProductCategoryCodes = new String[l_intmfProductCategory + 1];
        for (int i = 0; i < l_intmfProductCategory; i++)
        {
            MutualFundProductCategoryRow l_mfProductCategoryRow = 
                (MutualFundProductCategoryRow)l_LowMutualFundProductCategoryList.get(i);
            l_strMFProductCategoryCodes[i] = l_mfProductCategoryRow.getCategoryCode();
        }
        l_strMFProductCategoryCodes[l_intmfProductCategory] = l_strCategoryCode;
        return l_strMFProductCategoryCodes;
    }
}
@
