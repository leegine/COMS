head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteRMMRcvdCallbackImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteRMMRcvdCallbackImpl�N���X(WEB3QuoteRMMRcvdCallbackImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/04/23 �V��(FLJ) �V�K�쐬
*/
package webbroker3.quoteadaptor.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.feed.comm.RMMRecevierCallBack;
import webbroker3.util.WEB3LogUtility;

/**
 * (������M�R�[���o�b�NRMMImpl)
 * 
 * ������M�R�[���o�b�NRMM�����N���X�B
 * 
 * @@author �V��(FLJ)
 * @@version 1.0
 */
public class WEB3QuoteRMMRcvdCallbackImpl implements RMMRecevierCallBack
{

    /**
     * ���O�E���[�e�B���e�B
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteRMMRcvdCallbackImpl.class);

    /** ��M�v���Z�b�T�[ */
    private WEB3QuoteRMMRcvdProcessor rcvdProcessor;
    
    /**
     * 
     */
    public WEB3QuoteRMMRcvdCallbackImpl(WEB3QuoteRMMRcvdProcessor l_rcvdProcessor)
    {
        rcvdProcessor = l_rcvdProcessor;
    }

    /**
     * @@see webbroker3.feed.comm.RMMRecevierCallBack#notifyMsg(byte[])
     */
    public void notifyMsg(byte[] data)
    {
        try
        {
            rcvdProcessor.receiveData(data);
        }
        catch (Throwable e)
        {
            log.error("process receiveData failed." + e.getMessage(), e);
        }
    }

    /**
     * @@see webbroker3.feed.comm.RMMRecevierCallBack#notifyHeartTimeOut(String)
     */
    public void notifyHeartTimeOut(String l_strSourceAddr)
    {
        try
        {
            log.warn("heartbeat time out. address=" + l_strSourceAddr);

            WEB3QuoteDataSupplierServiceManager l_serviceManager = 
                (WEB3QuoteDataSupplierServiceManager) Services.getService(WEB3QuoteDataSupplierServiceManager.class);

            l_serviceManager.changeRMMDataSource();
        }
        catch (Throwable e)
        {
            log.error("change rmm primary/secondary failed. " + e.getMessage(), e);
        }
    }
}
@
