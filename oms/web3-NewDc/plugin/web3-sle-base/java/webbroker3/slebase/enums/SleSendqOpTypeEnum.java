head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendqOpTypeEnum.java;


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
 Revision History : 2006/05/2 李 新規作成
 */

package webbroker3.slebase.enums;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;


/**
 *  SEND_Qのオペレータタイプに関するエナム定数を定義します。
 */
public class SleSendqOpTypeEnum extends Enumerated {

	/** 新規注文をあらわします。 */
	public static final SleSendqOpTypeEnum NEW_ORDER = new SleSendqOpTypeEnum(IntValues.NEW_ORDER,
		  "NEW_ORDER");

	/** 変更注文をあらわします。 */
	public static final SleSendqOpTypeEnum CHANGE_ORDER= new SleSendqOpTypeEnum(IntValues.CHANGE_ORDER,
		  "CHANGE_ORDER");

	/** 取消注文をあらわします。 */
	public static final SleSendqOpTypeEnum CANCEL_ORDER= new SleSendqOpTypeEnum(IntValues.CANCEL_ORDER,
		  "CANCEL_ORDER");

	/**
	 * 上位クラスのコンストラクタをオーバライドする必須のコンストラクタです。 
	 *
	 * @@param i int型の値
	 * @@param s 文字列表現
	 */
	public SleSendqOpTypeEnum(int i, String s) {
		super(i, s);
	}

	/**
	 * 任意のエナムの整数値定義をする内部クラスです。整数値を定義することにより、 
	 * このクラスをswitch文で容易に利用することができます。
	 */
	public static class IntValues {

		//~ Static fields/initializers ---------------------------------------------

		/** 新規注文をあらわします。 */
		public static final int NEW_ORDER = 0;

		/** 変更注文をあらわします。 */
		public static final int CHANGE_ORDER = 1;

		/** 取消注文をあらわします。 */
		public static final int CANCEL_ORDER = 2;
	}
}
@
