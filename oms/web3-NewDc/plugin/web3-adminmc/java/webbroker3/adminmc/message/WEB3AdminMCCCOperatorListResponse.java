head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.54.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾚｽﾎﾟﾝｽ(WEB3AdminMCCCOperatorListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾚｽﾎﾟﾝｽ<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorList";

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
     * (CCオペレータ登録情報一覧)<BR>
     * CCオペレータ登録情報一覧<BR>
     */
    public WEB3AdminMCCCOperatorRegistUnit[] ccOperatorRegistUnits;
    
    /**
     * (管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse
     * @@roseuid 417E28F000B4
     */
    public WEB3AdminMCCCOperatorListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
