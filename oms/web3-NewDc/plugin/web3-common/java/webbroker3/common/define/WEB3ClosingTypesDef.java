head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ClosingTypesDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 指定区分(WEB3ClosingTypesDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 張宝楠 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 決済順序　@定数定義インタフェイス
 *                                                                     
 * @@author 張宝楠 (中訊)
 * @@version 1.0
 */
public interface WEB3ClosingTypesDef
{
    /**
     * 4 : 建日・単価
     */
    public static final String OPEN_DATE_UNIT_PRICE = "4";    
}
@
