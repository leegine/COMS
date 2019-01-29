head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingStateFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminIPOBookBuildingStateFileDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
Revision History : 2005/08/19 沢田(SRA) 未取込案件IPO-No.76（パフォーマンス改善）
                 : 2006/11/09 齊珂 (中訊) 仕様変更・モデル160                
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingStateFileDownloadRequest extends WEB3GenRequest 
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
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * ファ@イル種別<BR>
     * <BR>
     * 0：　@無効OPデータをダウンロードする。<BR>
     * 1：　@BB状況データ（余力あり）をダウンロードする。<BR>
     * 2：　@BB状況データ（余力なし）をダウンロードする。<BR>
     */
    public String fileTypeCode;
    
    /**
     * (部店コード) <BR>
     * 画面にて選択された部店コード <BR>
     * ※未入力の場合は、PR層で保持している <BR>
     * 管理者取扱可能部店の一覧をセット。
     */
    public String[] branchCode;

    /**
     * (顧客コードfrom) <BR>
     * 画面にて入力された顧客コードfrom <BR>
     * <BR>
     * ※null：指定なし
     */
    public String accountCodeFrom;

    /**
     * (顧客コードto) <BR>
     * 画面にて入力された顧客コードto <BR>
     * <BR>
     * ※null：指定なし
     */
    public String accountCodeTo;

    /**
     * (新規申告日時from) <BR>
     * 画面にて入力された新規申告日時from <BR>
     * <BR>
     * ※null：指定なし
     */
    public Date bbCreatedTimestampFrom;

    /**
     * (新規申告日時to) <BR>
     * 画面にて入力された新規申告日時to <BR>
     * <BR>
     * ※null：指定なし
     */
    public Date bbCreatedTimestampTo;
    
    /**
     * (CSV区分 = 0) <BR>
     * CSV区分 <BR>
     * <BR>
     * 0：追加項目無<BR>
     * 1：追加項目有（扱者コード）<BR>
     */
    public String csvDiv = "0";

    /**
     * @@roseuid 4112DAD60221
     */
    public WEB3AdminIPOBookBuildingStateFileDownloadRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD60235
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOBookBuildingStateFileDownloadResponse(this);
    }
}
@
