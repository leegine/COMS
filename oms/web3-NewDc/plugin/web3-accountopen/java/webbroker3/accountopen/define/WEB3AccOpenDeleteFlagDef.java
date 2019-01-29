head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenDeleteFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 削除フラグ 定数定義インタフェイス（WEB3AccOpenDeleteFlagDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 武波 (中訊) 新規作成 モデルNo.163
*/
package webbroker3.accountopen.define;

/**
 * 削除フラグ 定数定義インタフェイス
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AccOpenDeleteFlagDef
{
    /**
     * 1：TRUE/無効（削除）<BR>
     */
    public final static String DELETE = "1";

    /**
     * 0：FALSE/有効（DEFAULT）<BR>
     */
    public final static String DEFAULT = "0";
}
@
