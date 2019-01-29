head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽクラス(WEB3AdminIPOLotResultUploadCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408181639L;
    
    /**
     * @@roseuid 4112DF8A0100
     */
    public WEB3AdminIPOLotResultUploadCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E11ABE031E
     */
    public WEB3AdminIPOLotResultUploadCompleteResponse(WEB3GenRequest l_request) 
    {
        
        super(l_request);
     
    }
}
@
