head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LoginStopDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン停止区分定数定義クラス(WEB3LoginStopDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * ログイン停止区分定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3LoginStopDef
{
    /**
     * 不許可（ログイン停止）
     */
    public static final String DISABLE = "0";
    
    /**
     * 許可（ログイン可能）
     */
    public static final String ENABLE = "1";
}@
