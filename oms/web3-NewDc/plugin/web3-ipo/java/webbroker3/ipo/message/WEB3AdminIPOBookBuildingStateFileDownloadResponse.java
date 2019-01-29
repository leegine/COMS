head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingStateFileDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ(WEB3AdminIPOBookBuildingStateFileDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingStateFileDownloadResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingStateFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121023L;    
    
    /**
     * ダウンロードファ@イル<BR>
     * <BR>
     * ※ CSVファ@イル行の配列<BR>
     */
    public String[] downloadFile;
    
    /**
     * 現在日時
     */
    public Date currentDate;
    
    /**
     * @@roseuid 4112DAD601A9
     */
    public WEB3AdminIPOBookBuildingStateFileDownloadResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E11AF30291
     */
    public WEB3AdminIPOBookBuildingStateFileDownloadResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
