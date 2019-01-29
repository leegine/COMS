head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : PTS申込客情報(WEB3AdminInformPTSAccountInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 柴双紅(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * PTS申込客情報<BR>
 * PTS申込客情報クラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountInfoUnit extends Message
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
    public String accountCode;

    /**
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;

    /**
     * (申込区分)<BR>
     * 申込区分<BR>
     * <BR>
     * 0：未開設<BR>
     * 1：開設<BR>
     */
    public String ptsAccOpenDiv;

    /**
     * (状態)<BR>
     * 状態<BR>
     * <BR>
     * 0：無効<BR>
     * 1：有効<BR>
     */
    public String status;

    /**
     * (申込日時)<BR>
     * 申込日時
     */
    public Date applyDate;

    /**
     * (更新者)<BR>
     * 更新者
     */
    public String lastUpdater;

    /**
     * コンストラクタ。
     * @@roseuid 47BA5CB00375
     */
    public WEB3AdminInformPTSAccountInfoUnit()
    {

    }
}
@
