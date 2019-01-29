head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListSearchConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄一覧検索条件(WEB3AdminBondDomesticProductListSearchConditionUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 周墨洋 (中訊) 新規作成 モデル203
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者国内債券銘柄一覧検索条件)<BR>
 * 管理者国内債券銘柄一覧検索条件<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListSearchConditionUnit extends Message
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductListSearchConditionUnit.class);

    /**
     * (債券タイプ)<BR>
     * 債券タイプ<BR>
     * <BR>
     * 11:個人向け国債<BR>
     * 12:社債<BR>
     */
    public String bondType;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (回号コード)<BR>
     * 回号コード<BR>
     * <BR>
     * ※）'1'が入力された場合、画面で、'00001'のように0を詰めて5桁でセットする。<BR>
     */
    public String productIssueCode;

    /**
     * (銘柄名（HOST）)<BR>
     * 銘柄名（HOST）<BR>
     */
    public String productNameHost;

    /**
     * (銘柄名（WEB3）)<BR>
     * 銘柄名（WEB3）<BR>
     */
    public String productNameWEB3;

    /**
     * (発行日)<BR>
     * 発行日<BR>
     */
    public Date issueDate;

    /**
     * (償還日)<BR>
     * 償還日<BR>
     */
    public Date maturityDate;

    /**
     * (利払日)<BR>
     * 利払日<BR>
     * <BR>
     * ’MMDD’入力<BR>
     */
    public String interestPaymentDay;

    /**
     * (取扱区分)<BR>
     * 取扱区分<BR>
     * <BR>
     * 0：不可<BR>
     * 2：顧客<BR>
     */
    public String tradeHandleDiv;

    /**
     * @@roseuid 4691C5EC016F
     */
    public WEB3AdminBondDomesticProductListSearchConditionUnit()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）債券タイプチェック<BR>
     * 　@　@債券タイプ ≠ null の場合、以下のチェックを行なう。<BR>
     * 　@　@　@・債券タイプが以下の値でない場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@’個人向け国債’<BR>
     * 　@　@　@　@　@　@’社債’<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02869<BR>
     * <BR>
     * ２）取扱区分チェック<BR>
     * 　@　@取扱区分 ≠ null の場合、以下のチェックを行なう。<BR>
     * 　@　@　@・取扱区分が以下の値でない場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@’不可’<BR>
     * 　@　@　@　@　@　@’顧客’<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02844<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4684C09F0366
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）債券タイプチェック
        //　@　@債券タイプ ≠ null の場合、以下のチェックを行なう。
        //　@　@　@・債券タイプが以下の値でない場合、例外をスローする。
        //　@　@　@　@　@　@’個人向け国債’
        //　@　@　@　@　@　@’社債’
        if (this.bondType != null)
        {
            if (!String.valueOf(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND.intValue()).equals(this.bondType)
                && !String.valueOf(BondTypeEnum.CORPORATE_BOND.intValue()).equals(this.bondType))
            {
                log.debug("債券タイプの値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02869,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "債券タイプの値が存在しないコード値です。");
            }
        }

        //２）取扱区分チェック
        //　@　@取扱区分 ≠ null の場合、以下のチェックを行なう。
        //　@　@　@・取扱区分が以下の値でない場合、例外をスローする。
        //　@　@　@　@　@　@’不可’
        //　@　@　@　@　@　@’顧客’
        if (this.tradeHandleDiv != null)
        {
            if (!WEB3TradeHandleDivDef.DISABLED.equals(this.tradeHandleDiv)
                && !WEB3TradeHandleDivDef.MANAGER_CUSTOMER.equals(this.tradeHandleDiv))
            {
                log.debug("取扱区分の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02844,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "取扱区分の値が存在しないコード値です。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
