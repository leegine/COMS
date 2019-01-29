head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInsiderDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 内部者登録区分 定数定義インタフェイス（WEB3AccOpenInsiderDivDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/10 武波 (中訊) 新規作成 モデル No.216
*/
package webbroker3.accountopen.define;

/**
 * 内部者登録区分 定数定義インタフェイス
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AccOpenInsiderDivDef
{
    /**
     * 1：TRUE/登録あり<BR>
     */
    public final static String TRUE = "1";

    /**
     * 0：FALSE/登録なし（DEFAULT）<BR>
     */
    public final static String DEFAULT = "0";
}
@
