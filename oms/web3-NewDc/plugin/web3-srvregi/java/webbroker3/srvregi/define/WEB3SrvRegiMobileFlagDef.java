head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiMobileFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : モバイルフラグ(WEB3SrvRegiMobileFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/29 金シュ 新規作成 モデル329
*/

package webbroker3.srvregi.define;

/**
 * モバイルフラグ
 *
 * @@author 金シュ
 * @@version 1.0
 */
public interface WEB3SrvRegiMobileFlagDef
{
    /**
     * 1：モバイル
     */
    public final static String MOBILE = "1";

    /**
     * null：PC
     */
    public final static String PC = null;
}
@
