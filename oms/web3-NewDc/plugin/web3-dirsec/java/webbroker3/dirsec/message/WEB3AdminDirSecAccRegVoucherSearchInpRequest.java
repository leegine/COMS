head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherSearchInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・顧客情報登録伝票検索入力リクエスト(WEB3AdminDirSecAccRegVoucherSearchInpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12  何文敏 (中訊) 新規作成 仕様変更 モデルNo.098
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・顧客情報登録伝票検索入力リクエスト)<BR>
 * 管理者・顧客情報登録伝票検索入力リクエストクラス。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherSearchInpRequest extends WEB3GenRequest
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
     * @@roseuid 466E0B6B01E0
     */
    public WEB3AdminDirSecAccRegVoucherSearchInpRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAccRegVoucherSearchInpResponse(this);
    }
}
@
