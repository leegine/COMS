head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募確認レスポンス(WEB3BondDomesticApplyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 モデルNo.227
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (国内債券応募確認レスポンス)<BR>
 * 国内債券応募確認レスポンス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyConfirmResponse
    extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String id;

    /**
     * (初回利子調整額)<BR>
     * 初回利子調整額<BR>
     */
    public String initialInterestAdjustAmount;

    /**
     * (受渡金額)<BR>
     * 受渡代金<BR>
     */
    public String deliveryPrice;

    /**
     * (約定日)<BR>
     * 約定日<BR>
     */
    public Date executionUpdateDate;

    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;

    /**
     * (国内債券応募確認レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 466661A403D6
     */
    public WEB3BondDomesticApplyConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondDomesticApplyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
