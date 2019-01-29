head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SuspensionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引停止フラグ定数定義クラス(WEB3SuspensionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/23 今井　@高史(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 取引停止フラグ定数定義クラス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3SuspensionDef
{

    /**
     * 0:通常
     */
    public final static String NORAML = "0";

    /**
     * 1:取引停止中
     */
    public final static String SUSPENSION = "1";
}
@
