head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherSearchInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・顧客情報登録伝票検索入力レスポンス(WEB3AdminDirSecAccRegVoucherSearchInpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12  何文敏 (中訊) 新規作成 仕様変更 モデルNo.098
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・顧客情報登録伝票検索入力レスポンス)<BR>
 * 管理者・顧客情報登録伝票検索入力レスポンスクラス。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherSearchInpResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_search_inp";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 200706121120L;

    /**
     * @@roseuid 466E0B6B01B2
     */
    public WEB3AdminDirSecAccRegVoucherSearchInpResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminDirSecAccRegVoucherSearchInpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
