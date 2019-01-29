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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteFutureData�N���X(WEB3QuoteFutureData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/26 �V��(FLJ) �V�K�쐬�i�����V�X�e��QUICK�ւ̈ڍs�j
*/
package webbroker3.quoteadaptor.stdimpls;

/**
 * (�擾�\��f�[�^)<BR>
 * <BR>
 * �擾�\��f�[�^<BR>
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
		//�ăZ�b�g���Ȃ�
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
				//�Z�b�g�����܂ő҂�
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
