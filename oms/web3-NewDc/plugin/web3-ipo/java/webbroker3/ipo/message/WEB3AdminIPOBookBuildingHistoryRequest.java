head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾘｸｴｽﾄ(WEB3AdminIPOBookBuildingHistoryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
Revesion History : 2005/01/06 坂上(SRA) 残対応>>>056
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾘｸｴｽﾄクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingHistoryRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121108L;
    
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
     * @@roseuid 4112DF8C01DF
     */
    public WEB3AdminIPOBookBuildingHistoryRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8C01F3
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOBookBuildingHistoryResponse(this);
    }
}
@
