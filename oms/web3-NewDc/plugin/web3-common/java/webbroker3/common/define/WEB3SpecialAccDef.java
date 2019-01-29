head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SpecialAccDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座区分定数定義インタフェイス(WEB3SpecialAccDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 特定口座区分定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3SpecialAccDef
{
    /**
     * 0：一般口座
     */
    public final static String NORMAL = "0";

    /**
     * 1：特定口座（源泉徴収なし）
     */
    public final static String SPECIAL = "1";

    /**
     * 2：特定口座（源泉徴収あり）
     */
    public final static String SPECIAL_WITHHOLD = "2";
}
@
