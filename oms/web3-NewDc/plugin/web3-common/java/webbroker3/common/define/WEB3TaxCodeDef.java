head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TaxCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 税コード定数定義インタフェイス(WEB3TaxCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 税コード定数定義インタフェイス<BR>
 * （上場外株・株主登録伝票（G8610）キューの税コードの参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3TaxCodeDef
{
    /**
     * 1：課税
     */
    public final static String TAXATION  = "1";  
}@
