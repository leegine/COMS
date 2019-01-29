head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽクラス(WEB3AdminIPOLotResultUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 李海波 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽクラス
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultUploadInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotResultUploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131148L;
    
    /**
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * アップロード履歴一覧
     */
    public WEB3IPOUploadHistoryUnit[] uploadHistoryList;
    
    /**
     * @@roseuid 4112DF89034E
     */
    public WEB3AdminIPOLotResultUploadInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E11AA80168
     */
    public WEB3AdminIPOLotResultUploadInputResponse(WEB3GenRequest l_request) 
    {

        super(l_request);
     
    }
}
@
