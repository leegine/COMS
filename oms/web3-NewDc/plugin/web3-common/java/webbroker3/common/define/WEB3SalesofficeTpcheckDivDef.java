head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SalesofficeTpcheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 営業店取引余力チェック実施区分定数定義インタフェイス(WEB3SalesofficeTpcheckDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/14 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 営業店取引余力チェック実施区分定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3SalesofficeTpcheckDivDef
{
    /**
     * 0:通常の取引余力チェックを実施する。
     */
     public static final String DEFAULT = "0";
     
     /**
      * 1:営業店用の取引余力チェックを実施する。
      */
     public static final String EXECUTE = "1";
}
@
