head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 口座伝票作成共通リクエスト(WEB3AdminInformProfDistVoucherCommonRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.054
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (口座伝票作成共通リクエスト)<BR>
 * 利金・分配金伝票作成共通リクエストクラス
 */
public class WEB3AdminInformProfDistVoucherCommonRequest extends WEB3GenRequest
{

    /**
     * (連絡情報)<BR>
     * 連絡情報
     */
    public WEB3InformDetailInfoUnit informInfoUnit;

    /**
     * @@roseuid 4663A9D70129
     */
    public WEB3AdminInformProfDistVoucherCommonRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
