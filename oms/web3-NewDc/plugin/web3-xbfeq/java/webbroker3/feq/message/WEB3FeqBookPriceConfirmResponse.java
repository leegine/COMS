head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookPriceConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式簿価単価登録確認レスポンス(WEB3FeqBookPriceConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/16 柴双紅(中訊) 新規作成 モデルNo.373
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外国株式簿価単価登録確認レスポンス)<BR>
 * 外国株式簿価単価登録確認レスポンス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3FeqBookPriceConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "feq_book_price_confirm";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200801151256L;

    public WEB3FeqBookPriceConfirmResponse()
    {

    }

    /**
     * (変更後概算簿価単価)<BR>
     * 変更後概算簿価単価<BR>
     */
    public String aftBookPrice;

    public WEB3FeqBookPriceConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
