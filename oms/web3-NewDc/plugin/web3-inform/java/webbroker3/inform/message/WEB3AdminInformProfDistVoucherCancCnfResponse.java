head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherCancCnfResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・口座伝票取消確認レスポンス(WEB3AdminInformProfDistVoucherCancCnfResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.054
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・口座伝票取消確認レスポンス)<BR>
 * 管理者・口座伝票取消確認レスポンスクラス
 */
public class WEB3AdminInformProfDistVoucherCancCnfResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_canc_cnf";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * (連絡情報)<BR>
     * 連絡情報
     */
    public WEB3InformDetailInfoUnit informInfoUnit;

    /**
     * (付加情報)<BR>
     * 各種連絡情報について画面表示で使用する付加情報
     */
    public WEB3InformAddInfoUnit informAddUnit;

    /**
     * (金融機@関情報)<BR>
     * 金融機@関情報
     */
    public WEB3AdminInformProfDistSellTransSrcInfo financialInstitutionInfo;

    /**
     * @@roseuid 4663A9D7036B
     */
    public WEB3AdminInformProfDistVoucherCancCnfResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformProfDistVoucherCancCnfResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
