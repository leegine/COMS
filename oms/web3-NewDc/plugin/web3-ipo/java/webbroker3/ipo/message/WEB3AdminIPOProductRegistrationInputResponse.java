head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductRegistrationInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄新規登録入力レスポンス(WEB3AdminIPOProductRegistrationInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李頴淵 新規作成
Revesion History : 2010/09/23 車進 (中訊) モデル No.181
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者IPO銘柄新規登録入力レスポンス)<BR>
 * IPO銘柄新規登録入力レスポンスクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIPOProductRegistrationInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productRegistrationInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161059L;
    
    /**
     * 公開市場コード一覧<BR>
     * <BR>
     * 公開市場コード<BR>
     * <BR>
     * 10：　@東証　@<BR>
     * 11：　@東証一部　@<BR>
     * 12：　@東証二部　@<BR>
     * 13：　@マザーズ　@<BR>
     * 20：　@大証　@<BR>
     * 21：　@大証一部　@<BR>
     * 22：　@大証二部　@<BR>
     * 30：　@名証　@<BR>
     * 31：　@名証一部　@<BR>
     * 32：　@名証二部　@<BR>
     * 33：　@セントレックス<BR>
     * 40：　@福証　@<BR>
     * 41：　@Q-Board<BR>
     * 50：　@札証　@<BR>
     * 51：　@アンビシャス<BR>
     * 90：　@JASDAQ（スタンダード）
     * 91：　@JASDAQ（グロース）
     * <BR>
     */
    public String[] publicOfferingMarketList;
    
    /**
     * 現在日時
     */
    public Date currentDate;
    
    /**
     * @@roseuid 4112DF8C03D4
     */
    public WEB3AdminIPOProductRegistrationInputResponse() 
    {
     
    }
    
    /**
     * (管理者IPO銘柄新規登録入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D1421C03A0
     */
    public WEB3AdminIPOProductRegistrationInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
