head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminIPOLotResultOfferFileDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
                 : 2006/11/09 齊珂 (中訊) 仕様変更・モデル160
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果購入申込状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferFileDownloadRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121116L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * (CSV区分) <BR>
     * CSV区分 <BR>
     * <BR>
     * 0：追加項目無<BR>
     * 1：追加項目有（扱者コード、公開価格、信用区分、抽選番号）<BR>
     */
    public String csvDiv = "0";
    
    /**
     * @@roseuid 4112DAD501D0
     */
    public WEB3AdminIPOLotResultOfferFileDownloadRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD501E4
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotResultOfferFileDownloadResponse(this);
    }
}
@
