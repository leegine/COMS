/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RecoveryQuoteAllCommand�N���X(WEB3RecoveryQuoteAllCommand.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/23 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery;

import java.sql.Timestamp;

import com.fitechlabs.fin.intellioms.util.Log;

/**
 * �i�T�[�o�[�_�E����j�N����������������
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRecoveryQuoteSinceFileLastUpdated extends DOTRecoveryQuote implements Runnable
{
    /**
     * ���K�[�B
     */
    private static final Log log = Log.getLogger(DOTRecoveryQuoteSinceFileLastUpdated.class);

    private static final boolean DBG = log.isDebug();

    /**
     * @see Runnable#run()
     */
    public void run()
    {
        execute();
    }

    public void execute()
    {
        log.info("execute() **** START *****.");

        //�I������
        while(running)
        {
            Timestamp l_lastUpdatedTimestamp = quoteFileAdaptor.getLastUpdatedTimestamp();
            if(l_lastUpdatedTimestamp == null)
            {
                if(DBG)
                {
                    log.debug("failed to get file last updated timestamp. exit.");
                }
                running = false;
            }

            boolean isConnected = false;

            //�t�@�C���̍ŏI�X�V��������Đڑ�����܂ł̊Ԃ̎�������
            //�������Ǘ��e�[�u���ɕ���
            //�ڑ���҂�
            while(!isConnected)
            {
                //�I������
                if(!running)
                    break;

                if(quoteDataSource.isConnected())
                {
                    Timestamp l_lastConnectedTime = quoteDataSource.getLastConnectedTime();

                    if(DBG)
                    {
                        log.debug("Quote Feeder connected.");
                        log.debug("FROM(file last quote data updated timestamp):" + l_lastUpdatedTimestamp);
                        log.debug("TO(quote feeder last connect timestamp):" + l_lastConnectedTime);
                    }

                    try
                    {
                        restoreQuoteDataViaQuoteCacheStoreAdaptor(l_lastUpdatedTimestamp, l_lastConnectedTime);
                    }
                    catch(IllegalArgumentException l_iae)
                    {
                        log.warn(l_iae.getMessage());

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
