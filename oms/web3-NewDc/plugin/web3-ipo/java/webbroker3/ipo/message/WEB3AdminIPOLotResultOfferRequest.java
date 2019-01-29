head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況ﾘｸｴｽﾄ(WEB3AdminIPOLotResultOfferRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果購入申込状況ﾘｸｴｽﾄクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOffer";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121118L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * 部店コード
     */
    public String branchCode;
    
    /**
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * @@roseuid 4112DAD4034B
     */
    public WEB3AdminIPOLotResultOfferRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD4035F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotResultOfferResponse(this);
    }
}
@
