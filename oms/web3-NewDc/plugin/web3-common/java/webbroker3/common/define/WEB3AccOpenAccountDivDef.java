head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccOpenAccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座区分(WEB3AccOpenAccountDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 口座区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3AccOpenAccountDivDef
{

    /**
     * 0：個人アカウント
     */
    public final static String INDIVIDUAL_ACCOUNT  = "0";

    /**
     * 1：法@人アカウント
     */
    public final static String CORPORATE_ACCOUNT = "1";

}@
