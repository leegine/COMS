head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteFutureData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteFutureDataクラス(WEB3QuoteFutureData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/26 齋藤(FLJ) 新規作成（時価システムQUICKへの移行）
*/
package webbroker3.quoteadaptor.stdimpls;

/**
 * (取得予定データ)<BR>
 * <BR>
 * 取得予定データ<BR>
 *
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3QuoteFutureData
{
	private String data;
	
	private boolean isReady = false;
	
	public synchronized void setRealData(String l_strData)
	{
		//再セットしない
		if(isReady)
		{
			return;
		}
		
		data = l_strData;
		isReady = true;
		
		notifyAll();
	}
	
	public synchronized String getData()
	{
		while(!isReady)
		{
			try
			{
				//セットされるまで待つ
				wait();
			}
			catch (InterruptedException e)
			{
			}
		}
		
		return data;
	}
}
@
