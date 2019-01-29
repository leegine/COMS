/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RecoveryQuoteDuringLostTimeCommand�N���X(WEB3RecoveryQuoteDuringLostTimeCommand.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/23 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery;

import java.sql.Timestamp;

import com.fitechlabs.fin.intellioms.util.Log;

/**
 * �����ؒf��������������
 *
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRecoveryQuoteDuringLostTime extends DOTRecoveryQuote implements Runnable
{
    /**
     * ���K�[�B
     */
    private static final Log log = Log.getLogger(DOTRecoveryQuoteDuringLostTime.class);

    private static final boolean DBG = log.isDebug();

    /**
     * @see Runnable#run()
     */
    public void run()
    {
        execute();
    }

    /**
     * @see DOTRecoveryCommand#execute()
     */
    public void execute()
    {
        log.info("execute() **** START *****.");

        //�I������
        while(running)
        {
            //�ŏI�ؒf����
            Timestamp l_lastDisconnectedTime = quoteDataSource.getLastDisconnectedTime();

            boolean isConnected = false;

            //�t�@�C���̍ŏI�X�V��������Đڑ�����܂ł̊Ԃ̎�������
            //�������Ǘ��e�[�u���ɕ���
            //�ڑ���҂�
            while(!isConnected)
            {
                if(!running)
                    break;

                //�ڑ��J�n
                if(quoteDataSource.isConnected())
                {
                    //�ŏI�ڑ�����
                    Timestamp l_lastConnectedTime = quoteDataSource.getLastConnectedTime();

                    if(DBG)
                    {
                        log.debug("Quote Feeder connected.");
                        log.debug("FROM(quote feeder last disconnect timestamp):" + l_lastDisconnectedTime);
                        log.debug("TO(quote feeder last connect timestamp):" + l_lastConnectedTime);
                    }

                    try
                    {
                        //���X�g�A����
                        restoreQuoteDataViaQuoteCacheStoreAdaptor(l_lastDisconnectedTime, l_lastConnectedTime);
                    }
                    catch(IllegalArgumentException l_iae)
                    {
                        if(DBG)
                        {
                            log.debug(l_iae.getMessage());
                        }
                    }

                    isConnected = true;

                }
                try
                {
                    //1000�~���b�ҋ@
                    Thread.sleep(1000);
                }
                catch(InterruptedException l_ie)
                {
                    if(DBG)
                    {
                        log.debug("Exeption Occurred during thread sleeping." + l_ie.getMessage());
                    }
                }

            }

            running = false;

        }

        log.info("execute() **** DONE *****");

    }


}
