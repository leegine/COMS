head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3ProcessAddChangeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ProcessAddChangeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 王蘭芬(中訊)　@新規作成
Revesion History : 2008/04/29 武波(中訊)　@仕様変更モデル596
*/
package webbroker3.mf.define;

/**
 * 処理区分（追加／変更）　@定数定義インタフェイス
 *  (投信管理者カテゴリー登録共通リクエスト)<BR>
 * 
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3ProcessAddChangeDivDef {

    /**
     * 0:追加
     */
    public static final String ADD = "0";

    /**
     * 1:変更
     */
    public static final String CHANGE = "1";

    /**
     * 2:削除
     */
    public static final String DELETE = "2";
}
@
