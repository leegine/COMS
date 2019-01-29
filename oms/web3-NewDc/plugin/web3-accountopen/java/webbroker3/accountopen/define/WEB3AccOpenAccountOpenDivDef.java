head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAccountOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設区分 定数定義インタフェイス(WEB3AccOpenAccountOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/02 陳琦 (中訊) 新規作成
*/

package webbroker3.accountopen.define;

/**
 * 口座開設区分 定数定義インタフェイス
 * 
 * @@author 陳琦(中訊)
 * @@version 1.0
 */

public interface WEB3AccOpenAccountOpenDivDef 
{
    /**
     * 0：口座未開設
     */
    public static final String NOT_OPEN = "0";
    
    /**
     * 1: 口座開設済み
     */
    public static final String OPEN_COMPLETE = "1";

}
@
