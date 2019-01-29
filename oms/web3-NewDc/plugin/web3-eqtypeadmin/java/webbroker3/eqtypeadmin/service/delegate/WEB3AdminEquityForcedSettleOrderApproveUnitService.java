head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認一件サービス(WEB3AdminEquityForcedSettleOrderApproveUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデルNo.129
Revision History : 2007/05/16 張騰宇 (中訊) モデル152
*/
package webbroker3.eqtypeadmin.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.gentrade.WEB3Administrator;

/**
 * (管理者・強制決済仮注文承認／非承認一件サービス)<BR>
 * 管理者・強制決済仮注文承認／非承認一件サービスインターフェイス<BR>
 * （トランザクション属性：TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */

public interface WEB3AdminEquityForcedSettleOrderApproveUnitService extends Service
{

    /**
     * (exec承認)<BR>
     * 強制決済仮注文承認処理を行う。<BR>
     * （戻り値　@false：エラーなし　@true：エラーあり）<BR>
     * @@param l_forcedSettleOrderRow - (強制決済注文Row)<BR>
     * 強制決済注文Rowオブジェクト<BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 46076B040067
     */
    public boolean execApprove(
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow,
        WEB3Administrator l_admin) throws WEB3BaseException;

    /**
     * (exec非承認)<BR>
     * 強制決済仮注文非承認処理を行う。<BR>
     * （戻り値　@false：エラーなし　@true：エラーあり）<BR>
     * @@param l_forcedSettleOrderRow - (強制決済注文Row)<BR>
     * 強制決済注文Rowオブジェクト<BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 46076B3D00C4
     */
    public boolean execDisapprove(
        AdminEqForcedSettleOrderRow l_forcedSettleOrderRow,
        WEB3Administrator l_admin) throws WEB3BaseException;
}
@
