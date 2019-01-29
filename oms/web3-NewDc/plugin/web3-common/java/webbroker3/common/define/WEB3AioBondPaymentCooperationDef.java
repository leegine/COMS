head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioBondPaymentCooperationDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券出金連携実行区分定数定義インタフェイス(WEB3AioBondPaymentCooperationDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/18 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 債券出金連携実行区分定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3AioBondPaymentCooperationDef
{
    /**
     * 0:債券出金連携を実行しない。
     */
     public static final String DEFAULT = "0";
     
     /**
      * 1:債券出金連携を実行する。
      */
     public static final String EXECUTE = "1";
}
@
