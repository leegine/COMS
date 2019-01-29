head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AutoExecDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 自動約定区分定数定義インタフェイス(WEB3AutoExecDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 自動約定区分　@定数定義インタフェイス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3AutoExecDivDef
{
    /**
     * 0：非自動約定
     */
    public static final String NOT_AUTO_EXECUTE = "0";

    /**
     * 1：自動約定
     */
    public static final String AUTO_EXECUTE = "1";
}
@
