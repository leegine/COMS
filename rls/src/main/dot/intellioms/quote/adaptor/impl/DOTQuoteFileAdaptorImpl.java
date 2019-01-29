/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteFileAdaptorImpl�N���X(DOTQuoteFileAdaptorImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/20 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.fin.intellioms.util.ServiceState;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteFileAdaptor;


/**
 * (�������t�@�C���A�_�v�^Impl)<BR>
 * <BR>
 * �������t�@�C���A�_�v�^�̎����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteFileAdaptorImpl implements DOTQuoteFileAdaptor
{

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** �T�[�r�X��� */
    private ServiceState state;

    /** �������p�[�T */
    private DOTQuoteEventImpl parser;

    /** �������t�@�C�� */
    private File file;

    /** �������t�@�C���ɑ΂���r������ */
    private FileLock lock;

    /** ���C�^�[ */
    private BufferedWriter writer;

    /**
     * �R���X�g���N�^
     *
     * @param l_strFileName ��������ۑ�����t�@�C����
     */
    public DOTQuoteFileAdaptorImpl(String l_strFileName)
    {
        this.state = new ServiceState(getClass().getName());
        this.parser = new DOTQuoteEventImpl();
        this.file = new File(l_strFileName);
        this.lock = new FileLock(new File(file.getPath() + ".lock"));
    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteFileAdaptor#save(jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData)
     */
    public synchronized void save(DOTQuoteData l_quoteData)
    {

        boolean l_isLocked = false;

        state.checkStarted();

        String l_strQuoteData = DOTQuoteFormatter.format(l_quoteData);

        try
        {

            l_isLocked = lock.writeLock();

            write(l_strQuoteData);

        } catch (IOException l_ioe)
        {
            throw new RuntimeException(l_ioe);
        } finally
        {

            if (l_isLocked)
            {
                lock.writeUnLock();
            }

        }


    }

    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteFileAdaptor#load()
     */
    public synchronized List load()
    {

        List l_quotes = null;
        boolean l_isLocked = false;

        state.checkStarted();

        if (log.isDebug())
        {
            log.debug("Loading quote data from '" + file + "'.");
        }

        try
        {

            l_isLocked = lock.readLock();

            l_quotes = readAndParse();

        } catch (IOException l_ioe)
        {
            throw new RuntimeException(l_ioe);
        } finally
        {

            if (l_isLocked)
            {
                lock.readUnLock();
            }

        }

        if (log.isDebug())
        {
            int l_intCount = (l_quotes != null) ? l_quotes.size() : 0;
            log.debug("Loading quote data completed. [count=" + l_intCount + "]");
        }

        return l_quotes;

    }

    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#start()
     */
    public synchronized void start() throws InitializationException
    {

        state.checkNotStarted();

        try
        {

            if (!file.exists())
            {
                file.createNewFile();
            } else if (file.isDirectory())
            {
                throw new InitializationException("'" + file.getName() + "' is directory.");
            }

            openWriter();

            state.start();

            log.info("QuoteFileAdaptor started. [file=" + file.getName() + "]");

        } catch (IOException l_ioe)
        {
            log.error(l_ioe.getMessage(), l_ioe);
            throw new InitializationException(l_ioe);
        }
    }

    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public synchronized void stop()
    {

        try
        {
            state.checkStarted();
        } catch (IllegalStateException l_ise)
        {
            return;
        }

        closeWriter();

        state.stop();

        log.info("QuoteFileAdaptor stopped. [file=" + file.getName() + "]");

    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("DOTQuoteFileAdaptorImpl[");
        l_sb.append("file=").append(file);
        l_sb.append("]");
        return l_sb.toString();
    }

    /**
     * (openWriter)<BR>
     * <BR>
     * �t�@�C�����C�^�[���I�[�v������B<BR>
     *
     * @throws IOException
     */
    private void openWriter() throws IOException
    {
        writer = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * (closeWriter)<BR>
     * <BR>
     * �t�@�C�����C�^�[���N���[�Y����B<BR>
     */
    private void closeWriter()
    {
        if (writer != null)
        {
            try
            {
                writer.close();
            } catch (IOException l_ioe)
            {
                log.error("Unexpected exception occured while closing writer.", l_ioe);
            } finally
            {
                writer = null;
            }
        }
    }

    /**
     * (write)<BR>
     * <BR>
     * ����������������<BR>
     *
     * @param l_strQuoteData �Œ蒷������ɐ��`���ꂽ�������
     */
    private void write(String l_strQuoteData) throws IOException
    {
        writer.write(l_strQuoteData);
        writer.newLine();
        writer.flush();
    }

    /**
     * (readAndParse)<BR>
     * <BR>
     * �t�@�C�����玞������ǂݍ��ށB<BR>
     *
     * @return �ǂݍ��񂾎������̃��X�g
     * @throws IOException
     */
    private List readAndParse() throws IOException
    {

        BufferedReader l_reader = null;
        List l_quotes = null;

        try
        {

            l_quotes = new ArrayList();
            l_reader = new BufferedReader(new FileReader(file));
            String l_strQuoteData = null;

            while ((l_strQuoteData = l_reader.readLine()) != null)
            {
                DOTQuoteData l_quoteData = parse(l_strQuoteData);
                if (l_quoteData != null)
                {
                    l_quotes.add(l_quoteData);
                }
            }

        } finally
        {

            if (l_reader != null)
            {
                l_reader.close();
            }

        }

        return l_quotes;

    }

    /**
     * (parse)<BR>
     * <BR>
     * �t�@�C������ǂݍ��񂾎��������p�[�X���������f�[�^���쐬����B<BR>
     *
     * @param l_strQuoteData �t�@�C������ǂݍ��񂾎������
     * @return �������f�[�^
     */
    private DOTQuoteData parse(String l_strQuoteData)
    {

        int l_intOffset = 0;
        byte[] l_bytData = l_strQuoteData.getBytes();

        // �V�[�P���XNO
        long l_lngSequenceNo = DOTQuoteUtils.toLong(
            l_bytData,
            l_intOffset,
            DOTQuoteFormats.AP_SEQUENCE_NO_SIZE,
            0);
        l_intOffset += DOTQuoteFormats.AP_SEQUENCE_NO_SIZE;

        // �X�V����
        Timestamp l_updatedTime = DOTQuoteUtils.toTimestamp(
            l_bytData,
            l_intOffset,
            DOTQuoteFormats.UPDATED_TIME_SIZE,
            DOTQuoteFormats.TIMESTAMP_FORMAT);
        long l_lngUpdatedTime = (l_updatedTime != null) ? l_updatedTime.getTime() : 0;
        l_intOffset += DOTQuoteFormats.UPDATED_TIME_SIZE;

        // �f�[�^��
        parser.setData(
            l_bytData,
            l_intOffset,
            DOTQuoteConstants.RECORD_SIZE,
            l_lngSequenceNo,
            l_lngUpdatedTime);

        return parser.getQuoteData();
    }

    /**
     * �t�@�C���̍ŏI�������ݎ�����Ԃ��B
     * @return Timestamp �ŏI�������ݎ���
     */
    public Timestamp getLastUpdatedTimestamp()
    {
        //�t�@�C����ǂݍ���
        BufferedReader l_reader = null;
        List l_strQuotes = new ArrayList();

        try
        {
            try
            {
                l_reader = new BufferedReader(new FileReader(file));
                String l_strQuoteData = null;

                while ((l_strQuoteData = l_reader.readLine()) != null)
                {
                    if (l_strQuoteData != null)
                    {
                        l_strQuotes.add(l_strQuoteData);
                    }
                }

            }
            finally
            {
                if (l_reader != null)
                {
                    l_reader.close();
                }
            }
        }
        catch(FileNotFoundException l_fne)
        {
            log.debug("file not found. this is startup initial status.");
        }
        catch(IOException l_ioe)
        {
            log.error("Unexpected exception occured during file accessing.", l_ioe);
        }

        //�ŏI���������擾
        int l_lastIndex = l_strQuotes.size() - 1;
        if(l_lastIndex >= 0)
        {
            DOTQuoteData l_quoteData = parse((String)l_strQuotes.get(l_lastIndex));
            return l_quoteData.getUpdatedTime();
        }

        //���R�[�h���Ȃ��ꍇ�Anull��Ԃ��B
        return null;

    }


    /**
     * ������񂪑��݂��邩�ǂ����ԋp����B
     * @return boolean ������񂪂����true, �Ȃ����false
     */
    public boolean dataExists()
    {
        boolean dataExists = false;

        //�t�@�C����ǂݍ���
        BufferedReader l_reader = null;

        try
        {
            try
            {
                l_reader = new BufferedReader(new FileReader(file));
                if(l_reader.readLine() != null)
                {
                    dataExists = true;
                }
            }
            finally
            {
                if (l_reader != null)
                {
                    l_reader.close();
                }
            }
        }
        catch(FileNotFoundException l_fne)
        {
            log.debug("file not found. this is startup initial status.");
        }
        catch(IOException l_ioe)
        {
            log.error("Unexpected exception occured during file accessing.", l_ioe);
        }

        return dataExists;

    }


}
