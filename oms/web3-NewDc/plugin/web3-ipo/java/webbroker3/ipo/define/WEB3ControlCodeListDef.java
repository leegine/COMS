head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3ControlCodeListDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ControlCodeListDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/18  李頴淵(sinocom)　@新規作成
Revesion History : 2005/12/20  譚漢江 (中訊) 仕様変更No.101修正
*/
package webbroker3.ipo.define;

/**
 * 操作コード一覧
 *                                                                     
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3ControlCodeListDef
{

    /**
     * 01：　@訂正
     */
    public static final String CHANGE = "01";

    /**
     * 02：　@削除
     */
    public static final String CANCEL = "02";
    
    /**
     * 11：　@ブックビルディング状況ダウンロード
     */
    public static final String BOOK_BUILDING_SITUATION_DOWNLOAD = "11";
    
    /**
     * 12：　@抽選結果／購入申込状況ダウンロード
     */
    public static final String LOT_RESULT_OFFER_STATE_DOWNLOAD = "12";
    
    /**
     * 21：　@抽選結果アップロード
     */
    public static final String LOT_RESULT_UPLOAD = "21";
    
    /**
     * 31：　@IPO募集停止／再開
     */
    public static final String IPO_RECRUIT_STOP_RESUMPTION = "31";
    
    /**
     * 32：　@IPO中止
     */
    public static final String IPO_DISCONTINUATION = "32";

    /**
     * 41：　@IPO抽選割当入力
     */
    public static final String IPO_LOT_INPUT = "41";

    /**
     * 42：　@IPO抽選割当結果確認
     */
    public static final String IPO_LOT_RESULT_CONFIRM = "42";

    /**
     * 43：　@IPO抽選割当結果完了
     */
    public static final String IPO_LOT_RESULT_COMPLETE = "43";
    
}
@
