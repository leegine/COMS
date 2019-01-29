head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募確認リクエスト(WEB3BondDomesticApplyConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 モデルNo.227
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (国内債券応募確認リクエスト)<BR>
 * 国内債券応募確認リクエスト<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyConfirmRequest extends WEB3BondDomesticApplyCommonRequest
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
     * @@roseuid 46A473FD032C
     */
    public WEB3BondDomesticApplyConfirmRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 国内債券応募確認レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondDomesticApplyConfirmResponse(this);
    }
}
@
