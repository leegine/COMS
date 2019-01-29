head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoReportDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 交付区分 定数定義インタフェイス(WEB3EleDeliveryFlagDef.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 張騰宇(中訊) 新規作成 仕様変更モデルNo.277
*/
package webbroker3.accountinfo.define;

/**
 * 交付区分 定数定義インタフェイス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AccInfoReportDivDef
{
    /**
     * 0：　@郵便配布 <BR>
     */
    public final static String MAIL = "0";

    /**
     * 1：　@電子配布<BR>
     */
    public final static String ELECTRON = "1";
}
@
