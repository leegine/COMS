head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FamilyRelationshipDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 続柄区分(WEB3FamilyRelationshipDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 続柄区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3FamilyRelationshipDef
{

    /**
     * 1：本人
     */
    public final static String IDENTICAL_PERSON  = "1";

    /**
     * 2：配偶者
     */
    public final static String MATE  = "2";

    /**
     * 3：子供
     */
    public final static String CHILD  = "3";

    /**
     * 4：その他
     */
    public final static String OTHER  = "4";

}@
