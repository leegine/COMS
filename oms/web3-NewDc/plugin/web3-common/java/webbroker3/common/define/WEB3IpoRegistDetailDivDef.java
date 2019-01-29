head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IpoRegistDetailDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO登録区分詳細定数定義インタフェイス(WEB3IpoRegistDetailDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 劉江涛(sinocom) 新規作成
Revesion History : 2008/08/22 趙林鵬(中訊) ＤＢレイアウトNo.029
*/
package webbroker3.common.define;

/**
 * IPO登録区分詳細 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3IpoRegistDetailDivDef
{
    /**
     * 1：募集
     */
    public final static String RECRUIT = "1";

    /**
     * 2：売出し
     */
    public final static String SALES = "2";

    /**
     * 3：私募
     */
    public final static String PRIVATE_RECRUIT = "3";

    /**
     * 4：募集・売出し
     */
    public final static String RECRUIT_AND_SALES = "4";

    /**
     * 5：募集の取扱い
     */
    public final static String RECRUIT_HANDING = "5";

    /**
     * 6：売出しの取扱い
     */
    public final static String SALES_HANDING = "6";

    /**
     * 7：私募の取扱い
     */
    public final static String PRIVATE_RECRUIT_HANDING = "7";

    /**
     * 8：募集・売出しの取扱い
     */
    public final static String RECRUIT_AND_SALES_HANDING = "8";
}@
