head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleOpenStatusEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SleBasedMarketAdapterBasePluginクラス
Author Name      : Daiwa Institute of Research
Revision History : 2006/05/19 李 新規作成
*/
package webbroker3.slebase.enums;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;
/**
 * 0(CLOSED),1(OPEN) ステータスしか含まないステータスエナムクラス
 * @@author  : 李（FLJ）
 * @@version : 1.0
 */
public class SleOpenStatusEnum extends Enumerated {
	  /**0：クローズ */
	  public static final SleOpenStatusEnum CLOSE = new SleOpenStatusEnum(IntValues.CLOSE, "CLOSE");

	  /**1：オープン */
	  public static final SleOpenStatusEnum OPEN =  new SleOpenStatusEnum(IntValues.OPEN,"OPEN");
    
	  public SleOpenStatusEnum(int i, String s)
	  {
		  super(i, s);    
	  }
    
	  /**
	   * 任意のエナムの整数値定義をする内部クラスです。整数値を定義することにより、 
	   * このクラスをswitch文で容易に利用することができます。
	   */
	  public static class IntValues {

		  //~ Static fields/initializers ---------------------------------------------

		/**0： クローズ*/
		public static final int CLOSE = 0;
		
		/**1:  オープン */
		public static final int OPEN = 1;
	  }    
}
@
