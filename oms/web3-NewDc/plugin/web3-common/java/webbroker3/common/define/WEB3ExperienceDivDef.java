head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExperienceDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替手数料区分(WEB3ExperienceDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 振替手数料区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3ExperienceDivDef
{

    /**
     * 0：なし
     */
    public final static String WITHOUT  = "0";

    /**
     * 1：１年未満
     */
    public final static String LESS_THAN_ONE_YEAR  = "1";

    /**
     * 2：２，３年
     */
    public final static String TWO_TO_THREE_YEARS  = "2";

    /**
     * 3：５年未満
     */
    public final static String LESS_THAN_FIVE_YEARS  = "3";

    /**
     * 4：５年以上
     */
    public final static String MORE_THAN_FIVE_YEARS  = "4";

}@
