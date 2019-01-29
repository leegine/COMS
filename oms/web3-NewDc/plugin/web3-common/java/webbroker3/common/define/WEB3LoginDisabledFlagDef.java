head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LoginDisabledFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン有効性 定数定義インタフェイス(WEB3LoginDisabledFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 李海波(中訊) 新規作成
Revesion History : 2006/09/18 栄イ(中訊)  ＤＢレイアウト(ログイン)による
*/
package webbroker3.common.define;

/**
 * ログイン有効性 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3LoginDisabledFlagDef
{

    /**
     * 0: enabled　@
     */
    public static final String ACCINFO_ENABLED = "0"; 

    /**
     * 1：disabled
     */
    public static final String ACCINFO_DISABLED = "1"; 

}
@
