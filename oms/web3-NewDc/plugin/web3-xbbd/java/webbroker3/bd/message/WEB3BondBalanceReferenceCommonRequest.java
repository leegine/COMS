head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会共通リクエスト(WEB3BondBalanceReferenceCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 武波 (中訊) 新規作成 モデル206
*/
package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondBalanceReferenceTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券残高照会共通リクエスト)<BR>
 * 債券残高照会共通リクエストクラス
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceCommonRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707171900L;

    /**
     * (照会区分)<BR>
     * 照会区分<BR>
     * <BR>
     * １：すべての銘柄<BR>
     * ２：外国債券銘柄のみ<BR>
     * ３：国内債券（個人向け国債を含む）<BR>
     * ４：国内債券（個人向け国債を含まない）<BR>
     * ５：個人向け国債のみ<BR>
     * <BR>
     * ※nullの場合、「1：すべての銘柄」とする。<BR>
     */
    public String referenceType;

    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondBalanceReferenceCommonRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }

    /**
     * 当クラスの整合性チェックを行う<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）照会区分のチェック <BR>
     * 　@this.照会区分≠nullの場合、定義値のチェックを行う。<BR>
     * 　@　@this.照会区分が以下の値以外の場合、<BR>
     * 　@　@「照会区分が未定義値」の例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@１：すべての銘柄<BR>
     * 　@　@　@　@２：外国債券銘柄のみ<BR>
     * 　@　@　@　@３：国内債券（個人向け国債を含む）<BR>
     * 　@　@　@　@４：国内債券（個人向け国債を含まない）<BR>
     * 　@　@　@　@５：個人向け国債のみ<BR>
     * <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00082<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //this.照会区分≠nullでない場合、定義値のチェックを行う。
        if (this.referenceType != null)
        {
            if ((!WEB3BondBalanceReferenceTypeDef.ALL_PRODUCT.equals(this.referenceType))
                && (!WEB3BondBalanceReferenceTypeDef.FOREIGN_BOND_ONLY.equals(this.referenceType))
                && (!WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND.equals(this.referenceType))
                && (!WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(this.referenceType))
                && (!WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND_INDIVIDUAL.equals(this.referenceType)))
            {
                log.debug(STR_METHOD_NAME + "照会区分の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "照会区分の値が存在しないコード値です。");
            }
        }
        else
        {
            //※nullの場合、「1：すべての銘柄」とする。
            this.referenceType = WEB3BondBalanceReferenceTypeDef.ALL_PRODUCT;
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
