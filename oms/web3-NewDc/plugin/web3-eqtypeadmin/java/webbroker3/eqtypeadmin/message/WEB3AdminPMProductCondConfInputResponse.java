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
filename	WEB3AdminPMProductCondConfInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件設定入力レスポンス(WEB3AdminPMProductCondConfInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式銘柄条件設定入力レスポンス) <BR>
 * <BR>
 * 管理者・株式銘柄条件設定入力レスポンスクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondConfInputResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_conf_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * （現在日時）<BR>
     * <BR>
     * 現在日時
     * <BR>
     * currentDate<BR>
     * <BR>
     */
    public Date currentDate;

    /**
     * （営業日）<BR>
     * <BR>
     * 本日の営業日<BR>
     * <BR>
     * bizDate<BR>
     * <BR>
     */
    public Date bizDate;

    /**
     * （翌営業日）<BR>
     * <BR>
     * 翌日の営業日<BR>
     * <BR>
     * nextBizDate<BR>
     * <BR>
     */
    public Date nextBizDate;

    /**
     * （翌々営業日）<BR>
     * <BR>
     * 翌々日の営業日<BR>
     * <BR>
     * next2BizDate<BR>
     * <BR>
     */
    public Date next2BizDate;

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
     * （上場市場コード一覧）<BR>
     * <BR>
     * 銘柄が上場している市場の市場コードの配列<BR>
     * <BR>
     * The array of marketCode of markets in which products list<BR>
     * <BR>
     */
    public String[] listingCodeList;

    /**
     * （制度信用実施フラグ）<BR>
     * <BR>
     * 制度信用実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * marketMarginFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean marketMarginFlag;

    /**
     * （一般信用実施フラグ）<BR>
     * <BR>
     * 一般信用実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * institutionMarginFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean institutionMarginFlag;

    /**
     * （ミニ株実施フラグ）<BR>
     * <BR>
     * ミニ株実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * miniFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean miniFlag;

    /**
     * （取引規制一覧）<BR>
     * <BR>
     * 株式銘柄取引規制の一覧<BR>
     * <BR>
     * tradingRegulation list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] tradingRegulationList;

    /**
     * （基本情報一覧）<BR>
     * <BR>
     * 株式銘柄基本情報の一覧<BR>
     * <BR>
     * basicInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] basicInfoList;

    /**
     * （信用銘柄情報一覧）<BR>
     * <BR>
     * 株式銘柄信用銘柄情報の一覧<BR>
     * <BR>
     * stockMarginInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] stockMarginInfoList;

    /**
     * （委託保証金率一覧）<BR>
     * <BR>
     * 株式銘柄委託保証金率の一覧<BR>
     * <BR>
     * depositRate list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] depositRateList;

    /**
     * （代用有価証券情報一覧）<BR>
     * <BR>
     * 株式銘柄代用有価証券情報の一覧<BR>
     * <BR>
     * substituteSecurityInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] substituteSecurityInfoList;

    /**
     * （値段情報一覧）<BR>
     * <BR>
     * 株式銘柄値段情報の一覧<BR>
     * <BR>
     * priceInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] priceInfoList;

    /**
     * @@roseuid 41FA2A3501A6
     */
    public WEB3AdminPMProductCondConfInputResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminPMProductCondConfInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
