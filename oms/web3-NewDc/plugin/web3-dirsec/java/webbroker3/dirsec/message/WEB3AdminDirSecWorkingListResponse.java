head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecWorkingListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・稼動状況一覧レスポンス(WEB3AdminDirSecWorkingListResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/28 柴双紅(中訊) 新規作成 モデルNo.117
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・稼動状況一覧レスポンス)<BR>
 * 管理者・稼動状況一覧レスポンス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminDirSecWorkingListResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dir_sec_working_list";

    /**
     * serialVersionUID
     */
    public static final long serialVersionUID = 200804281140L;

    /**
     * (電子鳩システム会社部店別プリファ@レンステーブルレコード詳細)<BR>
     * 電子鳩システム会社部店別プリファ@レンステーブルレコード詳細<BR>
     */
    public WEB3AdminDirSecBatoPreferenceRecordDetail[] batoPreferenceRecord;

    /**
     * @@roseuid 481169CD01DC
     */
    public WEB3AdminDirSecWorkingListResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     */
    public WEB3AdminDirSecWorkingListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
