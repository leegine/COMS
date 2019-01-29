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
filename	WEB3GentradeDateAnalysisFlagDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 日付解析フラグ　@定数定義インタフェイス(WEB3GentradeDateAnalysisFlagDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/12/21 謝旋 新規作成 仕様変更 モデル No.303
*/
package webbroker3.gentrade.define;


/**
 * 日付解析フラグ　@定数定義インタフェイス
 */
public interface WEB3GentradeDateAnalysisFlagDivDef 
{
   /**
    * 0：厳密ではない解析
    */
   public static final String NOT_STRICTLY_ANALYSIS = "0";

   /**
    * 1：厳密な解析
    */
   public static final String STRICTLY_ANALYSIS = "1";   
}
@
