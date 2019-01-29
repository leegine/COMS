head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleRequestPreparer.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleRequestPreparerクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 呉 新規作成
 Revision History : 2006/06/09 李(FLJ) ソース精査
*/
package webbroker3.slegateway.service.delegate.stdimpls;

import com.fitechlabs.xbconnector.glbase.gldata.GlSleRequest;
import webbroker3.slebase.data.SleSendQRow;

/**
 * SLEコネクタへリクエストを送信する前の準備処理インタフェース.
 */
public interface WEB3SleRequestPreparer {

	/**
	 * SLEコネクタへリクエストを送信する前の電文変換処理.
	 *
	 * @@param sendqRow  ma_sle_send_q SEND_Qメッセージ
	 * @@return  GlSleRequest GlSleRequest電文オブジェクト.
	 */
	public GlSleRequest prepare(SleSendQRow sendqRow);
}
@
