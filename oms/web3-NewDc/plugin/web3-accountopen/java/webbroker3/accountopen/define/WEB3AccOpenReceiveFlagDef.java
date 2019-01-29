head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenReceiveFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受領フラグ 定数定義インタフェイス（WEB3AccOpenReceiveFlagDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 武波 (中訊) 新規作成 モデルNo.163
*/
package webbroker3.accountopen.define;

/**
 * 受領フラグ 定数定義インタフェイス
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AccOpenReceiveFlagDef
{
    /**
     * 1：TRUE/受領済<BR>
     */
    public final static String RECEIVED = "1";

    /**
     * 0：FALSE/未受領（DEFAULT）<BR>
     */
    public final static String DEFAULT = "0";
}
@
