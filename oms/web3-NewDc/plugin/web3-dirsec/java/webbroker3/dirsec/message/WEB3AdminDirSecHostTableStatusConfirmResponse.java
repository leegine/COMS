head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableStatusConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  管理者・キューテーブルステータス更新確認レスポンスクラス(WEB3AdminDirSecHostTableStatusConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  肖志偉(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (管理者・キューテーブルステータス更新確認レスポンス)<BR>
 * 管理者・キューテーブルステータス更新確認レスポンスクラス。
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableStatusConfirmResponse extends WEB3AdminDirSecHostTableUpdateCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_status_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (キューテーブルレコード詳細)
     */
    public WEB3AdminDirSecHostTableDetail[] hostTableDetails;
    
    /**
     * @@roseuid 442A1C880196
     */
    public WEB3AdminDirSecHostTableStatusConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminDirSecHostTableStatusConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
