head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityApproveTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 承認区分定数定義インタフェイス(WEB3AdminEquityApproveTypeDef.java)
Author Name      : Daiwa Institute of Research　@　@　@
Revision History : 2007/4/25 孫洪江　@ (中訊) 新規作成  仕様変更モデルNo.128
*/

package webbroker3.eqtypeadmin.define;

/**
 * 承認区分定数定義インタフェイス
 *
 * @@author 孫洪江
 * @@version 1.0
 */

public interface WEB3AdminEquityApproveTypeDef 
{
    /**
     * 0：　@承認<BR>
     */
    public static final String APPROVE = "0";

    /**
     * 1：　@非承認<BR>
     */
    public static final String DISAPPROVE = "1";
}
@
