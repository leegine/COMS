head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄクラス(WEB3AdminIPOLotResultUploadCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131124L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * アップロードＩＤ
     */
    public String uploadID;
    
    /**
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 4112DAD40160
     */
    public WEB3AdminIPOLotResultUploadCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD4017E
     */
    public WEB3GenResponse createResponse() 
    {

        return new WEB3AdminIPOLotResultUploadCompleteResponse(this);

    }
}
@
