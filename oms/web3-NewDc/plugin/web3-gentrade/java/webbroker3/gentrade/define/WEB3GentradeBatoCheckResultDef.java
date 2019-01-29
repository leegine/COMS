head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBatoCheckResultDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （電子鳩）チェック結果　@定数定義インタフェイス(WEB3GentradeBatoCheckResultDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.define;


/**
 * （電子鳩）チェック結果　@定数定義インタフェイス
 */
public interface WEB3GentradeBatoCheckResultDef 
{
   
   /**
    * 0： 閲覧済
    */
   public static final String INSPECTION = "0";
   
   /**
    * 1： 閲覧未済
    */
   public static final String UNINSPECTION = "1";
   
   /**
    * 2： 閲覧未済（障害中）
    */
   public static final String UNINSPECTION_TROUBLE = "2";
   
}
@
