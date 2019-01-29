head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3ExecDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定未約定区分定数定義インタフェイス(WEB3ExecDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 劉江涛(sinocom) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 約定未約定区分 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3ExecDivDef
{

    /**
     * 0：　@未約定メール設定　@　@
     */
    public final static String UNEXEC_MAIL = "0";

    /**
     * 1：　@約定メール設定　@ 　@　@　@ 　@　@
     */
    public final static String EXEC_MAIL = "1";
    
}@
