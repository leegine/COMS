head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄キー(WEB3AdminOffFloorProductKey.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;

/**
 * (管理者立会外分売銘柄キー)<BR>
 * <BR>
 * 管理者立会外分売銘柄キー。<BR>
 * <BR>
 * WEB3AdminOffFloorProductKey<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorProductKey extends Message
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductRegistInfoUnit.class);

    /**
     * (銘柄コード)<BR>
     * <BR>
     * 銘柄コード。<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * (市場コード)<BR>
     * <BR>
     * 市場コード。<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     */
    public String marketCode;

    /**
     * (受付終了日時)<BR>
     * <BR>
     * 受付終了日時。<BR>
     * <BR>
     * orderEndDatetime<BR>
     * <BR>
     */
    public Date orderEndDatetime;

    /**
     * @@roseuid 421AE32F035E
     */
    public WEB3AdminOffFloorProductKey()
    {

    }

    /**
     * 当クラスのプロパティの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@銘柄コードチェック <BR>
     * <BR>
     * 　@１－１）　@this.銘柄コード＝nullの場合、 <BR>
     * 　@　@　@　@　@「銘柄コードがnull」の例外をthrowする。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * ２）　@市場コードチェック <BR>
     * <BR>
     * 　@２－１）　@this.市場コード＝nullの場合、 <BR>
     * 　@　@　@　@　@「市場コードがnull」の例外をthrowする。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * 　@２－２）　@this.市場コード≠null、かつ 以下の値以外の場合、 <BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をthrowする。 <BR>
     *          ・"1：東京" <BR>
     *          ・"2：大阪" <BR>
     *          ・"3：名古屋" <BR>
     *          ・"6：福岡" <BR>
     *          ・"8：札幌" <BR>
     *          ・"9：NNM" <BR>
     *          ・"10：JASDAQ" <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * ３）　@受付終了日時チェック<BR>
     * <BR>
     * 　@３－１）　@this.受付終了日時＝nullの場合、<BR>
     * 　@　@　@　@　@「受付終了日時がnull」の例外をthrowする。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01452<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence of the properties in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)productCode check<BR>
     * <BR>
     * 　@1-1)If this.productCode＝null,<BR>
     * 　@　@　@　@　@Throw the exception "productCode is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 2)marketCode check<BR>
     * <BR>
     * 　@2-1)If this.marketCode＝null<BR>
     * 　@　@　@　@　@Throw the exception "marketCode is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * 　@2-2)If this.marketCode ≠null and it has a value other than the
     * followings,<BR>
     * 　@　@　@　@　@Throw the exception "marketCode has an undefined value"<BR>
     *          ・1: Def.TOKYO<BR>
     *          ・2: Def.OSAKA<BR>
     *          ・3: Def.NAGOYA<BR>
     *          ・6: Def.FUKUOKA<BR>
     *          ・8: Def.SAPPORO<BR>
     *          ・9: Def.NNM<BR>
     *          ・10: Def.JASDAQ<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00608<BR>
     * <BR>
     * 3)orderEndDatetime check<BR>
     * <BR>
     * 　@3-1)If this.orderEndDatetime＝null,<BR>
     * 　@　@　@　@　@Throw the exception "orderEndDatetime is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01452<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 41B7C3740059
     */
    public void validate() throws WEB3BusinessLayerException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //1-1)If this.productCode＝null, throw Exception
        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //2-1)If this.marketCode＝null,
        if (this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            /*
             *  2-2)If marketCode has a value other than the
             *  followings Throw the exception
             * .1: Def.TOKYO
             * ・2: Def.OSAKA
             * ・3: Def.NAGOYA
             * ・6: Def.FUKUOKA
             * ・8: Def.SAPPORO
             * ・9: Def.NNM
             * ・10: Def.JASDAQ
             */
        } else
        {
            if ((!WEB3MarketCodeDef.TOKYO.equals(marketCode))
                && (!WEB3MarketCodeDef.OSAKA.equals(marketCode))
                && (!WEB3MarketCodeDef.NAGOYA.equals(marketCode))
                && (!WEB3MarketCodeDef.FUKUOKA.equals(marketCode))
                && (!WEB3MarketCodeDef.SAPPORO.equals(marketCode))
                && (!WEB3MarketCodeDef.NNM.equals(marketCode))
                && (!WEB3MarketCodeDef.JASDAQ.equals(marketCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //3-1) If this.orderEndDatetime＝null
        if (orderEndDatetime == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01452,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
