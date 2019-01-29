head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoUpdateFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 更新処理フラグ 定数定義インタフェイス(WEB3AccInfoUpdateFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1 齊珂 (中訊) 新規作成
*/

package webbroker3.accountinfo.define;

/**
 * 更新処理フラグ 定数定義インタフェイス
 * 
 * @@author 齊珂 (中訊)
 * @@version 1.0
 */
public interface WEB3AccInfoUpdateFlagDef
{
    /**
     * 0：登録処理　@
     */
    public final static String LOGIN = "0";
    
    /**
     * 1：更新処理
     */
    public final static String UPDATE = "1"; 
    
    /**
     * 2：削除処理
     */
    public final static String DELETE = "2"; 
}
@
