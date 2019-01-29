head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioEditRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオ編集リクエスト(WEB3TrialCalcPortfolioEditRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （計算サービスポートフォリオ編集リクエスト）<BR>
 * <BR>
 * 計算サービスポートフォリオサービス（ポートフォリオ編集）のリクエストデータ。<BR>
 * <BR>
 * WEB3TrialCalcPortfolioEditRequest<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioEditRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_portfolioedit";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503301100L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioEditRequest.class);

    /**
     * （ポートフォリオコード）<BR>
     * <BR>
     * portfolioCode<BR>
     */
    public String portfolioCode;

    /**
     * （銘柄明細一覧）<BR>
     * <BR>
     * portfolioEditProductUnit<BR>
     */
    public WEB3TrialCalcPortfolioEditProductUnit[] portfolioEditProductUnit;

    /**
     * @@roseuid 41C811CB0187
     */
    public WEB3TrialCalcPortfolioEditRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ポートフォリオコードチェック<BR>
     * <BR>
     * 　@１－１）　@this.ポートフォリオコード＝nullの場合、<BR>
     * 　@　@　@　@　@「ポートフォリオコードがnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01418<BR>
     * <BR>
     * <BR>
     * ２）　@銘柄明細一覧チェック<BR>
     * 　@２－１）　@this.銘柄明細一覧≠nullの場合、 <BR>
     * 　@　@　@　@this.銘柄明細一覧の全要素に対して下記のチェックを行う。 <BR>
     * <BR>
     * 　@　@２－１－１）　@銘柄明細一覧.validate()をコールする。 <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) portfolioCode check<BR>
     *  1-1) If "this.portfolioCode = null"<BR>
     *       throw the following exception.<BR>
     *       [portfolioCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01418<BR>
     * <BR>
     * <BR>
     * 2) portfolioEditProductUnit check<BR>
     *  2-1)If this.portfolioEditProductUnit is not null<BR>
     *        Check for all the values of this.portfolioEditProductUnit<BR>
     *   2-1-1) Call portfolioEditProductUnit.validate()<BR>
     * @@roseuid 41989124026B
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_length;

        if (this.portfolioCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01418,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (this.portfolioEditProductUnit != null)
        {
            l_length = portfolioEditProductUnit.length;
            for (int i = 0; i < l_length; i++)
            {
                portfolioEditProductUnit[i].validate();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3TrialCalcPortfolioEditResponse(this);
    }
}
@
