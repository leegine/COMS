head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3TraderAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ紐付顧客情報クラス(WEB3TraderAccountInfo.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成・モデルNo.039
*/

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (CCオペレータ紐付顧客情報)<BR>
 * CCオペレータ紐付顧客情報クラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3TraderAccountInfo extends Message
{

    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String acceptCode;

    /**
     * (名前（漢字）)<BR>
     * 名前（漢字）
     */
    public String nameKanji;

    /**
     * (名前（カナ）)<BR>
     * 名前（カナ）
     */
    public String nameKana;

    /**
     * (口座ID)<BR>
     * 口座ID
     */
    public long accountID;

    /**
     * (CCオペレータ紐付顧客情報)<BR>
     * コンストラクタ<BR>
     */
    public WEB3TraderAccountInfo()
    {

    }

}
@
