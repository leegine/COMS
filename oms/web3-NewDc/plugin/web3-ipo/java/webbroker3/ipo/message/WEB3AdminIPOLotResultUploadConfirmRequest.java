head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄクラス(WEB3AdminIPOLotResultUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131117L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * アップロードファ@イル<BR>
     * <BR>
     * ※ CSVファ@イル行の配列<BR>
     */
    public String[] uploadFile;
    
    /**
     * 抽選区分<BR>
     * <BR>
     * 1：　@新規抽選<BR>
     * 2：　@繰上抽選<BR>
     */
    public String lotDiv;
    
    /**
     * @@roseuid 4112DAD400CA
     */
    public WEB3AdminIPOLotResultUploadConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD400E8
     */
    public WEB3GenResponse createResponse() 
    {

        return new WEB3AdminIPOLotResultUploadConfirmResponse(this);
        
    }
}
@
