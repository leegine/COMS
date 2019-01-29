head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件照会レスポンス (WEB3AdminPMProductCondRefReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式銘柄条件照会レスポンス)<BR>
 * <BR>
 * 管理者・株式銘柄条件照会レスポンスクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondRefReferenceResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondRefReferenceResponse extends WEB3GenResponse
{
    /**
      * PTYPE<BR>
      */
    public final static String PTYPE = "admin_pm_product_cond_ref_reference";

    /**
      * serialVersionUID<BR>
      */
    public final static long serialVersionUID = 200502011606L;

    /**
     * （現在日時）<BR>
     * <BR>
     * 現在日時<BR>
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * （銘柄コード）<BR>
     * <BR>
     * 銘柄コード<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * （銘柄名）<BR>
     * <BR>
     * 銘柄名<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * （営業日一覧）<BR>
     * <BR>
     * 営業日の一覧<BR>
     * <BR>
     * 当日営業日、翌営業日、翌々営業日の順で格納<BR>
     * <BR>
     * bixDateList<BR>
     * <BR>
     * Stock in the following order<BR>
     * bizDate, nextBizDate, Next2BizDate<BR>
     * <BR>
     */
    public Date[] bizDateList;

    /**
     * （決算日）<BR>
     * <BR>
     * 決算日<BR>
     * <BR>
     * settlementDate<BR>
     * <BR>
     */
    public Date settlementDate;

	/**
	* （優先市場）<BR>
	* 優先市場 <BR>
	*<BR>
	*  1：　@東京<BR> 
	*  2：　@大阪<BR>
	*  3：　@名古屋<BR>
	*  6：　@福岡<BR>
	*  8：　@札幌<BR>
	*  9：　@NNM<BR>
	* 10：　@JASDAQ<BR>
	*/
   public String primaryMarket;

    /**
     * （現物株式売買停止）<BR>
     * <BR>
     * 0：　@停止でない<BR>
     * 1：　@停止中(取引所規制)<BR>
     * 2：　@停止中(当社規制)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * equityDealingStatus<BR>
     * <BR>
     * 0: Def.ACTIVE<BR>
     * 1: Def.STOP_MARKET_DEREG<BR>
     * 2: Def.STOP_COMPANY_DEREG<BR>
     * <BR>
     */
    public String equityDealingStatus;

    /**
     * （制度信用売買停止）<BR>
     * <BR>
     * 制度信用売買停止<BR>
     * <BR>
     * 0：　@停止でない<BR>
     * 1：　@停止中(取引所規制)<BR>
     * 2：　@停止中(当社規制)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * marketMarginDealingStatus<BR>
     * <BR>
     * 0: Def.ACTIVE<BR>
     * 1: Def.STOP_MARKET_DEREG<BR>
     * 2: Def.STOP_COMPANY_DEREG<BR>
     * <BR>
     */
    public String marketMarginDealingStatus = null;

    /**
     * （一般信用売買停止）<BR>
     * <BR>
     * 一般信用売買停止<BR>
     * <BR>
     * 0：　@停止でない<BR>
     * 1：　@停止中(取引所規制)<BR>
     * 2：　@停止中(当社規制)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * institutionMarginDealingStatus<BR>
     * <BR>
     * 0: Def.ACTIVE<BR>
     * 1: Def.STOP_MARKET_DEREG<BR>
     * 2: Def.STOP_COMPANY_DEREG<BR>
     * <BR>
     */
    public String institutionMarginDealingStatus = null;

    /**
     * （特定口座取扱規制）<BR>
     * <BR>
     * 特定口座取扱規制<BR>
     * <BR>
     * 0：　@取扱不可<BR>
     * 1：　@取扱可能<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * capitalGainRegulation<BR>
     * <BR>
     * 0: Def.DISABLE<BR>
     * 1: Def.NORMAL<BR>
     * <BR>
     */
    public String capitalGainRegulation;

    /**
     * （代用掛目）<BR>
     * <BR>
     * 代用掛目<BR>
     * <BR>
     * substituteAssessmentRate<BR>
     * <BR>
     */
    public String substituteAssessmentRate = null;

    /**
     * （預り証券評価掛目）<BR>
     * <BR>
     * 預り証券評価掛目<BR>
     * <BR>
     * depositSecuritiesAssessmentRate<BR>
     * <BR>
     */
    public String depositSecuritiesAssessmentRate = null;

    /**
     * （代用評価単価）<BR>
     * <BR>
     * 代用評価単価<BR>
     * <BR>
     * substituteSecurityAssetPrice<BR>
     * <BR>
     */
    public String substituteSecurityAssetPrice = null;

	/**
	 * （ミニミニ株実施フラグ）<BR>
	 * <BR>
	 * ミニ株実施フラグ<BR>
	 * <BR>
	 * false：未実施<BR>
	 * true ：実施<BR>
	 * <BR>
	 * ----<English>--------------------<BR>
	 * <BR>
	 * miniFlag<BR>
	 * <BR>
	 * false：FALSE<BR>
	 * true ：TRUE<BR>
	 * <BR>
	 */

	public boolean miniFlag;

    /**
     * （ミニ株銘柄情報一覧）<BR>
     * <BR>
     * ミニ株銘柄情報の一覧<BR>
     * ※ミニ株未実施会社の場合はnullをセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * miniProductInfo list<BR>
     * ※Set null for the mini share unexecution company. <BR>
     * <BR>
     */
    public WEB3AdminPMProductRegistInfoUnit[] miniProductInfoList = null;

    /**
     * （市場別銘柄条件照会情報一覧）
     * 市場別銘柄条件照会情報一覧
     * ----<English>--------------------
     * marketProductCondInfoList
     */
    public WEB3AdminPMProductCondInfoUnit[] marketProductCondInfoList;

    /**
     * （比較結果情報一覧）
     * 比較結果情報一覧
     * ----<English>--------------------
     * compResultInfoList
     */
    public WEB3AdminPMCompResultInfoUnit[] compResultInfoList;

    /**
     * @@roseuid 41FA2E1502BF
     */
    public WEB3AdminPMProductCondRefReferenceResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
