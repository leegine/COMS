head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminIPOLotResultOfferDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferDownloadRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121114L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * @@roseuid 4112DAD50338
     */
    public WEB3AdminIPOLotResultOfferDownloadRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD5034C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotResultOfferDownloadResponse(this);
    }
}
@
