head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenTaxTypeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座区分 定数定義インタフェイス（WEB3AccOpenTaxTypeDivDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 武波 (中訊) 新規作成 モデルNo.163
*/
package webbroker3.accountopen.define;

/**
 * 特定口座区分 定数定義インタフェイス
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AccOpenTaxTypeDivDef
{
    /**
     * 0：一般口座
     */
    public static final String NORMAL = "0";

    /**
     * 1：特定口座
     */
    public static final String SPECIAL = "1";
}
@
