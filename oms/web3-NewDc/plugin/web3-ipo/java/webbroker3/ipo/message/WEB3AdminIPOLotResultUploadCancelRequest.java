head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄクラス(WEB3AdminIPOLotResultUploadCancelRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadCancelRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadCancel";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131145L;
    
    /**
     * アップロードＩＤ
     */
    public String uploadID;
    
    /**
     * @@roseuid 4112DAD401EC
     */
    public WEB3AdminIPOLotResultUploadCancelRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD40200
     */
    public WEB3GenResponse createResponse() 
    {

        return new WEB3AdminIPOLotResultUploadCancelResponse(this);

    }
}
@
