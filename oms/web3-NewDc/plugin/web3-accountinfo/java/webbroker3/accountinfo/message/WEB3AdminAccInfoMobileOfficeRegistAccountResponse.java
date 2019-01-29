head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更申込顧客ﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoMobileOfficeRegistAccountResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
                   2006/12/18 周捷 (中訊) モデルNo.153
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報携帯番号・勤務先情報変更申込顧客ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報携帯番号・勤務先情報変更申込顧客ﾚｽﾎﾟﾝｽ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistAccount";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082112L;

    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (変更前申込顧客一覧)<BR>
     */
    public WEB3AccInfoMobileOfficeChangeAccount[] beforeChangeAccountList;

    /**
     * (変更後申込顧客一覧)<BR>
     */
    public WEB3AccInfoMobileOfficeChangeAccount[] afterChangeAccountList;

    /**
     * (管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ<BR>
     * @@param l_request - (l_request)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse
     * @@roseuid 414981B802DA
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
