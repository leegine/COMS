head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableUpdateCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・キューテーブル更新共通レスポンス(WEB3AdminDirSecHostTableUpdateCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30 奚翔(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・キューテーブル更新共通レスポンス)<BR>
 * 管理者・キューテーブル更新共通レスポンスクラス。<BR>
 * 
 * @@author 奚翔
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableUpdateCommonResponse extends WEB3GenResponse 
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    /**
     * (テーブル名)<BR>
     * テーブル名（和名）。
     */
    public String tableJpnName;
    
    /**
     * (テーブル物理名)<BR>
     * テーブル物理名。
     */
    public String tableName;
    
    /**
     * (識別コード有無フラグ)<BR>
     * 識別コード有無フラグ。<BR>
     * <BR>
     * 0：なし<BR>
     * 1：有り<BR>
     */
    public String identityCodeFlag;
    
    /**
     * (作成日付有無フラグ)<BR>
     * 作成日付有無フラグ。<BR>
     * <BR>
     * 0：なし<BR>
     * 1：有り<BR>
     */
    public String createDateFlag;
    
    /**
     * @@roseuid 442A1C8901A5
     */
    public WEB3AdminDirSecHostTableUpdateCommonResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminDirSecHostTableUpdateCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
