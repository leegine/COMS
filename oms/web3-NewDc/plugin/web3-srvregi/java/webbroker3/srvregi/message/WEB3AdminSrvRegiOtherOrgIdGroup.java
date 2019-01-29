head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.35.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
Author Name         : Daiwa Institute of Research
File Name           : サービス利用管理者外部連携ID一覧照会明細行(WEB3AdminSrvRegiOtherOrgIdGroup.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (サービス利用管理者外部連携ID一覧照会明細行)<BR>
 * サービス利用管理者外部連携ID一覧照会明細行データクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdGroup extends Message
{

    /**
     * (通番)<BR>
     * 通番<BR>
     */
    public String seqNumber;

    /**
     * (ID)<BR>
     * ID<BR>
     */
    public String id;

    /**
     * (パスワード)<BR>
     * パスワード<BR>
     */
    public String password;

    /**
     * (ステータス)<BR>
     * ステータス<BR>
     */
    public String status;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (口座コード)<BR>
     * 口座コード<BR>
     */
    public String accountCode;

    /**
     * (適用期間From)<BR>
     * 適用期間From<BR>
     */
    public Date appliStartDate;

    /**
     * (適用期間To)<BR>
     * 適用期間To<BR>
     */
    public Date appliEndDate;

    /**
     * (最終更新日)<BR>
     * 最終更新日<BR>
     */
    public Date lastUpdateTime;

    /**
     * (最終更新者)<BR>
     * 最終更新者<BR>
     */
    public String lastUpdater;

    /**
     * (サービス利用管理者外部連携ID一覧照会明細行)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47B8D08D00B0
     */
    public WEB3AdminSrvRegiOtherOrgIdGroup()
    {

    }
}
@
