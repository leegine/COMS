head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報内部情報一覧レスポンス(WEB3AdminAccInfoInsiderInfoListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 李海波 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報内部情報一覧レスポンス)<BR>
 * 管理者お客様情報内部情報一覧レスポンス<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_InsiderInfoList";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412211207L;
    
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
     * (内部者情報一覧)<BR>
     * 検索条件（部店コード、顧客コード、銘柄コード）<BR>
     * に合致し、かつ画面表示ソート順を反映し、かつページ内表示行数を無視した、<BR>
     * 当該画面表示対象となるデータの全銘柄のリスト<BR>
     */
    public WEB3AccInfoInsiderInfoUnit[] insideInfoList;
    
    /**
     * (管理者お客様情報内部者情報一覧レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     */
    public WEB3AdminAccInfoInsiderInfoListResponse (WEB3GenRequest l_request)
    {
        super(l_request);
    }
    
}
@
