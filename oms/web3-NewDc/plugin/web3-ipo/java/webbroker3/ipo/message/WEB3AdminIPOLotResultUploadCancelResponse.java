head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadCancelResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ中止ﾚｽﾎﾟﾝｽ(WEB3AdminIPOLotResultUploadCancelResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ中止ﾚｽﾎﾟﾝｽ
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadCancelResponse extends WEB3GenResponse 
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
     * @@roseuid 4112DF8A01BE
     */
    public WEB3AdminIPOLotResultUploadCancelResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40F77AA20345
     */
    public WEB3AdminIPOLotResultUploadCancelResponse(WEB3GenRequest l_request) 
    {

        super(l_request);

    }
}
@
