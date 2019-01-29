head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.51.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ一覧レスポンス(WEB3AdminMCAdminPermGrpListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18  賈元春 (中訊) 新規作成
*/
package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者メニュー制御管理者権限グループ一覧レスポンス)<BR>
 * 管理者メニュー制御管理者権限グループ一覧レスポンス<BR>
 * 
 * @@author 賈元春
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
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
     * (管理者タイプ情報一覧)<BR>
     * 管理者タイプ情報一覧<BR>
     */
    public WEB3AdminMCAdminTypeUnit[] adminTypeUnits;
    
    /**
     * (管理者メニュー制御管理者権限グループ一覧レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse
     * @@roseuid 4175DFAB0198
     */
    public WEB3AdminMCAdminPermGrpListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
