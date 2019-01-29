head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleSendqProcessorService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SleSendqProcessorServiceクラス
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/24 呉 新規作成
*/
package webbroker3.slegateway.service.delegate;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import webbroker3.slegateway.WEB3SleConnectorClientFactory;
import webbroker3.slegateway.WEB3SleProcessors;

/**
 * SEND_Q送信処理クラスのインタフェース
 */
public interface WEB3SleSendqProcessorService extends Service{
  /**
   * 指定したSEND_Q メッセージをGlSleRequestオブジェクトへ変換し、 SLEコネクタへ転送する
   * 
   * @@param row
   * @@throws DataException
   */  
  public boolean processRow(Row l_row,final WEB3SleProcessors l_wsp, final WEB3SleConnectorClientFactory l_connectorfactory) throws DataException;	 
}
@
