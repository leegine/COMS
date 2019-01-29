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
filename	WEB3MFAccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座区分 定数定義インタフェイス(WEB3MFAccountDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 張騰宇(中訊) 新規作成（モデル535）
*/
package webbroker3.mf.define;

/**
 * 口座区分 定数定義インタフェイス
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3MFAccountDivDef 
{
    /**
     * 一般
     */
    public static final String NORMAL = "0";

    /**
     * 特定
     */
    public static final String SPECIAL = "1";

    /**
     * その他
     */
    public static final String OTHER = "2";
}
@
