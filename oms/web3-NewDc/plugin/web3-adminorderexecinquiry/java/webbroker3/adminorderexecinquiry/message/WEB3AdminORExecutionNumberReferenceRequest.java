head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORExecutionNumberReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注文約定件数照会リクエスト (WEB3AdminORExecutionNumberReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/11 齊珂 (中訊) 仕様変更No.55修正
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.adminorderexecinquiry.define.WEB3AdminMarketDspDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminMonthlySumDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminOrderRootDspDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminSumProductTypeDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminSummryDivDef;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・注文約定件数照会リクエスト)<BR>
 * <BR>
 * 管理者・注文約定件数照会リクエストクラス<BR>
 * <BR>
 * WEB3AdminORExecutionNumberReferenceRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORExecutionNumberReferenceRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_execution_number_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOREquityOrderExecutionRefInputRequest.class);

    /**
     * (部店コード)<BR>
     * <BR>
     * 部店コードの配列<BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している<BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     * An array of branchCode<BR>
     * <BR>
     * ※The handling possible branchCodeList held by PR layer is set when branchCode
     * is not input.<BR>
     * <BR>
     */
    public String[] branchCode;

    /**
     * (集計対象商品区分一覧)<BR>
     * <BR>
     * 集計対象商品区分一覧<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@外国株式<BR>
     * 4：　@オプション<BR>
     * 5：　@先物<BR>
     * 6：　@投信<BR>
     * 7：　@中国F<BR>
     * 8：　@MMF<BR>
     * 9：　@債券 <BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * sumProductTypeList<BR>
     * <BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.FORIGN_STOCK<BR>
     * 4: Def.OPTION<BR>
     * 5: Def.FUTURE<BR>
     * 6: Def.MF<BR>
     * 7: Def.MIDIUM_TERM_GOV_FUND<BR>
     * 8: Def.MMF_SET<BR>
     * 9: Def.BOND<BR>
     * <BR>
     */
    public String[] sumProductTypeList;

    /**
     * (集計区分)<BR>
     * <BR>
     * 集計区分<BR>
     * <BR>
     * 0：　@日別<BR>
     * 1：　@月別<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * summryDiv<BR>
     * 0: Def.DAILY<BR>
     * 1: Def.MONTHLY<BR>
     * <BR>
     */
    public String summryDiv;

    /**
     * (日別集計対象年月)<BR>
     * <BR>
     * 日別集計対象年月<BR>
     * (YYYYMM)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * dailySumYm<BR>
     * (YYYYMM)<BR>
     * <BR>
     */
    public String dailySumYm;

    /**
     * (月別集計対象区分)<BR>
     * <BR>
     * 月別集計対象区分<BR>
     * <BR>
     * 0：　@過去3ヶ月<BR>
     * 1：　@過去6ヶ月<BR>
     * 2：　@過去12ヶ月<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * monthlySumDiv<BR>
     * 0: Def.PAST_THREE_MONTH<BR>
     * 1: Def.PAST_SIX_MONTH<BR>
     * 2: Def.PAST_TWELVE_MONTH<BR>
     * <BR>
     */
    public String monthlySumDiv;

    /**
     * (注文経路表示区分)<BR>
     * <BR>
     * 注文経路表示区分<BR>
     * <BR>
     * 0：　@詳細<BR>
     * 1：　@合計<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderRootDspDiv<BR>
     * 0: Def.DETAIL<BR>
     * 1: Def.TOTAL<BR>
     * <BR>
     */
    public String orderRootDspDiv;

    /**
     * (市場表示区分)<BR>
     * <BR>
     * 市場表示区分<BR>
     * <BR>
     * 0：　@詳細<BR>
     * 1：　@合計<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * marketDspDiv<BR>
     * 0: Def.DETAIL<BR>
     * 1: Def.TOTAL<BR>
     * <BR>
     */
    public String marketDspDiv;
    
    

    /**
     * @@roseuid 4212FD03024A
     */
    public WEB3AdminORExecutionNumberReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コードチェック<BR>
     * 　@１－１）this.部店コード == nullの場合、<BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@１－２）this.部店コード.length == 0の場合、<BR>
     * 　@　@　@　@　@「部店コードの要素数が0」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * 　@１－３）this.部店コードの要素数分以下のチェックを行う。<BR>
     * 　@　@１－３－１）this.部店コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.部店コード != 数値<BR>
     * 　@　@　@　@　@　@・this.部店コード.length != 3<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * ２）商品区分一覧チェック<BR>
     * 　@２－１）this.商品区分一覧 == nullの場合、<BR>
     * 　@　@　@　@　@「商品区分一覧がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01462<BR>
     * <BR>
     * 　@２－２）this.商品区分一覧.length == 0の場合、<BR>
     * 　@　@　@　@　@「商品区分一覧の要素数が0」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01463<BR>
     * <BR>
     * 　@２－３）this.商品区分一覧の各要素が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「商品区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"現物株式"<BR>
     * 　@　@　@　@　@　@・"信用取引"<BR>
     * 　@　@　@　@　@　@・"株式ミニ投資"<BR>
     * 　@　@　@　@　@　@・"オプション"<BR>
     * 　@　@　@　@　@　@・"先物"<BR>
     * 　@　@　@　@　@　@・"投信"<BR>
     * 　@　@　@　@　@　@・"中国F"<BR>
     * 　@　@　@　@　@　@・"MMF"<BR>
     * 　@　@　@　@　@　@・"外国株式"<BR>
     * 　@　@　@　@　@　@・"債券"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * ３）集計区分チェック<BR>
     * 　@３－１）this.集計区分 == nullの場合、<BR>
     * 　@　@　@　@　@「集計区分がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01465<BR>
     * <BR>
     * 　@３－２）this.集計区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「集計区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"日別"<BR>
     * 　@　@　@　@　@　@・"月別"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01466<BR>
     * <BR>
     * ４）日別集計対象年月チェック<BR>
     * 　@this.集計区分 == "日別"の場合、以下のチェックを行う。<BR>
     * 　@４－１）this.日別集計対象年月 == nullの場合、<BR>
     * 　@　@　@　@　@「日別集計対象年月がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01467<BR>
     * <BR>
     * 　@４－２）this.日別集計対象年月が以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「日別集計対象年月エラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.日別集計対象年月 != 数値<BR>
     * 　@　@　@　@　@　@・this.日別集計対象年月.length != 6<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01468<BR>
     * <BR>
     * ５）月別集計対象区分チェック<BR>
     * 　@this.集計区分 == "月別"の場合、以下のチェックを行う。<BR>
     * 　@５－１）this.月別集計対象区分 == nullの場合、<BR>
     * 　@　@　@　@　@「月別集計対象区分がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01469<BR>
     * <BR>
     * 　@５－２）this.月別集計対象区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「月別集計対象区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"過去3ヶ月"<BR>
     * 　@　@　@　@　@　@・"過去6ヶ月"<BR>
     * 　@　@　@　@　@　@・"過去12ヶ月"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01470<BR>
     * <BR>
     * ６）注文経路表示区分チェック<BR>
     * 　@６－１）this.注文経路表示区分 == nullの場合、<BR>
     * 　@　@　@　@　@「注文経路表示区分がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01471<BR>
     * <BR>
     * 　@６－２）this.注文経路表示区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「注文経路表示区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"詳細"<BR>
     * 　@　@　@　@　@　@・"合計"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01472<BR>
     * <BR>
     * ７）市場表示区分チェック<BR>
     * 　@７－１）this.市場表示区分 == nullの場合、<BR>
     * 　@　@　@　@　@「市場表示区分がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01473<BR>
     * <BR>
     * 　@７－２）this.市場表示区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「市場表示区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"詳細"<BR>
     * 　@　@　@　@　@　@・"合計"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01474<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     * 　@1-1)If this.branchCode== null<BR>
     * 　@　@　@　@　@Throw the exception "branchCode is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@1-2)If this.branchCode.length == 0<BR>
     * 　@　@　@　@Throw the exception "The number of the elements of branchCode is 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * 　@1-3)Check the following for as many times as elements of this.branchCode<BR>
     * 　@　@1-3-1)If this.branchCode meets with the following conditions,<BR>
     * 　@　@　@　@　@　@Throw the exception "branchCode error"<BR>
     * 　@　@　@　@　@　@・this.branchCode != numerical value<BR>
     * 　@　@　@　@　@　@・this.branchCode.length != 3<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 2)sumProductTypeList check<BR>
     * 　@2-1)If this.sumProductTypeList == null,<BR>
     * 　@　@　@　@Throw the exception "sumProductTypeList is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01462<BR>
     * <BR>
     * 　@2-2)If this.sumProductTypeList.length == 0<BR>
     * 　@　@　@　@Throw the exception "The number of the elements of sumProductTypeList is
     * 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01463<BR>
     * <BR>
     * 　@2-3)If each element of this.sumProductTypeList is not either of the following
     * values,<BR>
     * 　@　@　@　@Throw the exception "sumProductTypeList has an undefined value"<BR>
     * 　@　@　@　@　@　@・Def.EQUITY<BR>
     * 　@　@　@　@　@　@・Def.MARGIN<BR>
     * 　@　@　@　@　@　@・Def.MINI_STOCK<BR>
     * 　@　@　@　@　@　@・Def.OPTION<BR>
     * 　@　@　@　@　@　@・Def.FUTURE<BR>
     * 　@　@　@　@　@　@・Def.MF<BR>
     * 　@　@　@　@　@　@・Def.MIDIUM_TERM_GOV_FUND<BR>
     * 　@　@　@　@　@　@・Def.MMF_SET<BR>
     * 　@　@　@　@　@　@・Def.FORIGN_STOCK<BR>
     * 　@　@　@　@　@　@・Def.BOND<BR> 
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01068<BR>
     * <BR>
     * 3)summryDiv check<BR>
     * 　@3-1)If this.summryDiv == null,<BR>
     * 　@　@　@　@Throw the exception "summryDiv is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01465<BR>
     * <BR>
     * 　@3-2)If this.summryDiv contains values other than the followings,<BR>
     * 　@　@　@　@Throw the exception "summryDiv has an undefined value"<BR>
     * 　@　@　@　@　@　@・Def.DAILY<BR>
     * 　@　@　@　@　@　@・Def.MONTHLY<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01466<BR>
     * <BR>
     * 4)dailySumYm check<BR>
     * 　@Check the following if this.summryDiv == Def.DAILY<BR>
     * 　@4-1)If this.dailySumYm == null<BR>
     * 　@　@　@　@Throw the exception "dailySumYm is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01467<BR>
     * <BR>
     * 　@4-2)If this.dailySumYm meets with the following conditions<BR>
     * 　@　@　@　@　@Throw the exception "dailySumYm error"<BR>
     * 　@　@　@　@　@　@・this.dailySumYm != numerical value<BR>
     * 　@　@　@　@　@　@・this.dailySumYm.length != 6<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01468<BR>
     * <BR>
     * 5)monthlySumDiv check<BR>
     * 　@Cehck the following if this.summryDiv == Def.MONTHLY<BR>
     * 　@5-1)If this.monthlySumDiv== null,<BR>
     * 　@　@　@　@Throw the exception "monthlySumDiv is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01469<BR>
     * <BR>
     * 　@5-2)If this.monthlySumDiv contains values other than the followings,<BR>
     * 　@　@　@　@Throw the exception "monthlySumDiv has an undefined value"<BR>
     * 　@　@　@　@　@　@・Def.PAST_THREE_MONTH<BR>
     * 　@　@　@　@　@　@・Def.PAST_SIX_MONTH<BR>
     * 　@　@　@　@　@　@・Def.PAST_TWELVE_MONTH<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01470<BR>
     * <BR>
     * 6)orderRootDspDiv check<BR>
     * 　@6-1)If this.orderRootDspDiv== null,<BR>
     * 　@　@　@　@Throw the exception "orderRootDspDiv is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01471<BR>
     * <BR>
     * 　@6-2)If this.orderRootDspDiv contains values other than the followings,<BR>
     * 　@　@　@　@Throw the exception "orderRootDspDiv has an undefined value"<BR>
     * 　@　@　@　@　@　@・Def.DETAIL<BR>
     * 　@　@　@　@　@　@・Def.TOTAL<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01472<BR>
     * <BR>
     * 7)marketDspDiv check<BR>
     * 　@7-1)if this.marketDspDiv== null,<BR>
     * 　@　@　@　@ Throw the exception "marketDspDiv is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01473<BR>
     * <BR>
     * 　@7-2)If this.marketDspDiv contains values other than the followings,<BR>
     * 　@　@　@　@Throw the exception "marketDspDiv has an undefined value"<BR>
     * 　@　@　@　@　@　@・Def.DETAIL<BR>
     * 　@　@　@　@　@　@・Def.TOTAL<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01474<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 419C625C0275
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intThree = 3;
        final int l_intSix = 6;
        int l_intMin = 0;
        int l_intBranchCodeLength = 0;
        int l_intSumProductTypeListLength = 0;

        // 1-1 if branchCode is null, throw Exception.
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1-2 if branchCode.length is 0, throw Exception.
        if (this.branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_intBranchCodeLength = this.branchCode.length;
            for (int i = 0;  i < l_intBranchCodeLength; i++)
            {
                // 1-3-1 if branchCode is not numeric & its length not eqial to 3, throw Exception.
                if ((!WEB3StringTypeUtility.isNumber(branchCode[i]))
                    || (WEB3StringTypeUtility.getByteLength(this.branchCode[i]) != l_intThree))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

        // 2-1 if sumProductTypeList is null, throw Exception.
        if (this.sumProductTypeList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01462,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2 if length of sumProductTypeList is null, throw Exception.
            l_intSumProductTypeListLength = this.sumProductTypeList.length;
            if (l_intSumProductTypeListLength == l_intMin)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01463,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-3 Within loop, sumProductTypeList is not any of Def then throw Exception.
        l_intSumProductTypeListLength = this.sumProductTypeList.length;
        for (int k = 0; k < l_intSumProductTypeListLength; k++)
        {
            if ((!WEB3AdminSumProductTypeDef.EQUITY.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MARGIN.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MINI_STOCK.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.OPTION.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.FUTURE.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MF.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MIDIUM_TERM_GOV_FUND.equals
                    (this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.MMF_SET.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.FORIGN_STOCK.equals(this.sumProductTypeList[k]))
                && (!WEB3AdminSumProductTypeDef.BOND.equals(this.sumProductTypeList[k])))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if summreyDiv is null, throw Exception
        if (this.summryDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01465,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 3-2 if summryDiv is not of Def, throw Exception.
            if ((!WEB3AdminSummryDivDef.DAILY.equals(this.summryDiv))
                && (!WEB3AdminSummryDivDef.MONTHLY.equals(this.summryDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01466,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if dailySumYm is null, throw Exception.
        if (WEB3AdminSummryDivDef.DAILY.equals(this.summryDiv))
        {
            if (this.dailySumYm == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01467,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 4-2 if dailySumYm is not Numeric or length of dailySumYm is not 6, throw Exception.
                if ((!WEB3StringTypeUtility.isNumber(this.dailySumYm))
                    || (WEB3StringTypeUtility.getByteLength(this.dailySumYm) != l_intSix))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01468,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

        // 5-1 if  summryDiv equals MONTHLY then if monthlySumDiv is null, throw Exception.
        if ((WEB3AdminSummryDivDef.MONTHLY.equals(this.summryDiv)))
        {
            if (this.monthlySumDiv == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01469,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 5-2 if monthlySumDiv not equal to Def , throw Exception.
                if ((!WEB3AdminMonthlySumDivDef.PAST_THREE_MONTH.equals(this.monthlySumDiv))
                    && (!WEB3AdminMonthlySumDivDef.PAST_SIX_MONTH.equals(this.monthlySumDiv))
                    && (!WEB3AdminMonthlySumDivDef.PAST_TWELVE_MONTH.equals(this.monthlySumDiv)))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01470,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

        // 6-1 if orderRootDspDiv is null, throw Exception.
        if (this.orderRootDspDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01471,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 6-2 if monthlySumDiv is not any of Def, throw Exception.
            if ((!WEB3AdminOrderRootDspDivDef.DETAIL.equals(this.orderRootDspDiv))
                && (!WEB3AdminOrderRootDspDivDef.TOTAL.equals(this.orderRootDspDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01472,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 7-1 if marketDspDiv is null, throw Exception.
        if (this.marketDspDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01473,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 7-2 if marketDspDiv is not any of Def, throw Exception.
            if ((!WEB3AdminMarketDspDivDef.DETAIL.equals(this.marketDspDiv))
                && (!WEB3AdminMarketDspDivDef.TOTAL.equals(this.marketDspDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01474,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORExecutionNumberReferenceResponse();
    }
}
@
