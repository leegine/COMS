/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RecoveryQuoteFromFile�N���X(DOTRecoveryQuoteFromFile.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/31 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;


/**
 *
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRecoveryQuoteFromFile extends DOTRecoveryQuote
{
    /**
     * ���K�[�B
     */
    private static final Log log = Log.getLogger(DOTRecoveryQuoteFromFile.class);

    private static final boolean DBG = log.isDebug();

    /**
     * ���������t�@�C�����畜������B
     * @see DOTRecoveryCommand#execute()
     */
    public void execute()
    {
        log.info("execute() **** START *****.");

        while(running)
        {
            long l_lngStartTime = 0, l_lngEndTime = 0;
            Timestamp l_startTime, l_endTime;

            if(!quoteFileAdaptor.dataExists())
            {
                log.info("There is no data. this is normal initial status. don't do recovery. exit....");
                running = false;
            }

            if(DBG)
            {
                l_lngStartTime = System.currentTimeMillis();
            }

            //���[�J���t�@�C������
            //�������Ǘ��e�[�u���ɕ���.
            List l_lisQuoteData = quoteFileAdaptor.load();
            if(l_lisQuoteData == null)
            {
                log.info("there was no quote data in file. exit....");
                running = false;
            }

            quoteCashStore.clear();
            for(Iterator l_iter = l_lisQuoteData.iterator(); l_iter.hasNext();)
            {
                DOTQuoteData l_quoteData = (DOTQuoteData)l_iter.next();
                quoteCashStore.addQuoteData(l_quoteData);

                if(DBG)
                {
                    log.debug("added quote data(to quoteCashStore)=" + l_quoteData);
                }
            }

            if(DBG)
            {
                log.debug("recovered quote data (from file) size=" + l_lisQuoteData.size());
                l_startTime = new Timestamp(l_lngStartTime);
                l_lngEndTime = System.currentTimeMillis();
                l_endTime = new Timestamp(l_lngEndTime);
                long l_lngTotalTime = (l_lngEndTime - l_lngStartTime);

                BigDecimal l_totalTime = new BigDecimal(l_lngTotalTime);
                double l_dblTotalTimeSec = l_totalTime.divide(new BigDecimal(1000), 3, BigDecimal.ROUND_DOWN).doubleValue();

                log.debug("START Time="+ l_startTime);
                log.debug("END Time="+ l_endTime);
                log.debug("Total Time(MillSec)=" + l_lngTotalTime);
                log.debug("Total Time(Sec)="+ l_dblTotalTimeSec);

            }
            running = false;
        }
        log.info("execute() **** DONE *****.");

    }



}
