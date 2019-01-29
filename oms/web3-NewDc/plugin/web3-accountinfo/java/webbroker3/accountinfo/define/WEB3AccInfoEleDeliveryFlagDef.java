head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoEleDeliveryFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子交付申込フラグ 定数定義インタフェイス(WEB3EleDeliveryFlagDef.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 張騰宇(中訊) 新規作成 仕様変更モデルNo.277
*/
package webbroker3.accountinfo.define;

/**
 * 電子交付申込フラグ 定数定義インタフェイス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AccInfoEleDeliveryFlagDef
{
    /**
     * 0：　@申込しない <BR>
     */
    public final static String NOT_APPLY = "0";

    /**
     * 1：　@申込<BR>
     */
    public final static String APPLY = "1";

}
@
