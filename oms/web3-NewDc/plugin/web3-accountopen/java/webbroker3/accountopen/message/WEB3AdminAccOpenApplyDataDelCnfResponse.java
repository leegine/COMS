head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelCnfResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者口座開設資料請求データ削除確認レスポンス(WEB3AdminAccOpenApplyDataDelCnfResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 劉仁和(中訊) 新規作成 モデルNo.160
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設資料請求データ削除確認レスポンス)<BR>
 * 管理者口座開設資料請求データ削除確認レスポンス<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelCnfResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_data_del_cnf";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812121037L;

    /**
     * (顧客姓（漢字）)<BR>
     * 顧客姓（漢字）<BR>
     */
    public String accountFamilyName;

    /**
     * (顧客名（漢字）)<BR>
     * 顧客名（漢字）<BR>
     */
    public String accountName;

    /**
     * (顧客姓（カナ）)<BR>
     * 顧客姓（カナ）<BR>
     */
    public String accountFamilyNameKana;

    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR>
     */
    public String accountNameKana;

    /**
     * (住所１)<BR>
     * 住所１<BR>
     */
    public String address1;

    /**
     * (住所２)<BR>
     * 住所２<BR>
     */
    public String address2;

    /**
     * (住所３)<BR>
     * 住所３<BR>
     */
    public String address3;

    /**
     * (住所１（カナ）)<BR>
     * 住所１（カナ）<BR>
     */
    public String addressKana1;

    /**
     * (住所２（カナ）)<BR>
     * 住所２（カナ）<BR>
     */
    public String addressKana2;

    /**
     * (住所３（カナ）)<BR>
     * 住所３（カナ）<BR>
     */
    public String addressKana3;

    /**
     * (電話番号)<BR>
     * 電話番号<BR>
     */
    public String telephone;

    /**
     * @@roseuid 4940F22B01F4
     */
    public WEB3AdminAccOpenApplyDataDelCnfResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     */
    public WEB3AdminAccOpenApplyDataDelCnfResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
