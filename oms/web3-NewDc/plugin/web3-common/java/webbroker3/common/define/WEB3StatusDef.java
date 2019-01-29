head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3StatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2006/5/25 凌建平 (中訊) インターフェイス申請・No079
Revesion History : 2009/8/21 趙林鵬 【口座開設】仕様変更管理台帳・ＤＢレイアウト058
*/
package webbroker3.common.define;

/**
 * 処理区分　@定数定義インタフェイス
 *                                                                   
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3StatusDef
{
    /**
     * 0 : 未処理
     */
    public static final String NOT_DEAL = "0";

    /**
     * 1 : 処理済
     */
    public static final String DEALT = "1";
    
    /**
     * 2 : 翌日未処理
     */
    public static final String NEXT_NOT_DEAL = "2";    

    /**
     * 3：仮
     */
    public static final String TEMPORARY = "3";

    /**
     * 5 : 処理中
     */
    public static final String DEALING = "5";

    /**
     * 8 : プログラムエラー
     */
    public static final String PROGRAM_ERROR = "8";

    /**
     * 9 : データエラー
     */
    public static final String DATA_ERROR = "9";

    /**
     * 4：管理者手動失効済
     */
    public static final String ADMIN_MANUAL_EXPIRED = "4";
}
@
