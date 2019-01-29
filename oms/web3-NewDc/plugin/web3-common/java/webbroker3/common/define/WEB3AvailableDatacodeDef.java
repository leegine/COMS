head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AvailableDatacodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託使用可能データコード設定(WEB3AvailableDatacodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 孟東 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 投資信託使用可能データコード設定 定数定義インタフェイス<BR>
 *<BR>
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3AvailableDatacodeDef
{
    /**
     * 0：オプション未使用（国内投信[CI811]のみ）
     */
    public final static String DEFAULT  = "0";

    /**
     * 1：外国投信[CI813]取扱可能
     */
    public final static String FOREIGN_MF  = "1";

    /**
     * 2：募集[CI817]取扱可能
     */
    public final static String RECRUIT_ORDER  = "2";

    /**
     * 3：外国投信＋募集取扱可能
     */
    public final static String ALL  = "3";

    /**
     * 4：取扱不可
     */
    public final static String NONE  = "4";
}
@
