head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況一覧ﾘｸｴｽﾄ(WEB3AdminIPOLotResultOfferListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果購入申込状況一覧ﾘｸｴｽﾄクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121118L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * 要求ページ番号
     */
    public String pageIndex;
    
    /**
     * ページ内表示行数
     */
    public String pageSize;
    
    /**
     * (ソートキー)
     */
    public WEB3IPOSortKey[] sortKeys;
    
    /**
     * @@roseuid 4112DAD500CB
     */
    public WEB3AdminIPOLotResultOfferListRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD500DF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotResultOfferListResponse(this);
    }
}
@
