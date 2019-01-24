/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteEventHandlerImpl�N���X(DOTQuoteEventHandlerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/19 �R�c�@��i(FLJ) �V�K�쐬
                 : 2006/07/19 �R�c�@��i(FLJ) ���ݒl�̔�r���@��ύX�A���O�̏o�͓��e�Əo�̓J�e�S����ύX
                 : 2006/08/15 �R�c�@��i(FLJ) JASDAQ�s��A��l�z�M���Ή��B�L�����ԃ`�F�b�N��ǉ��B
 */
package jp.co.dir.dot.intellioms.quote;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.fin.intellioms.ticker.Ticker;
import com.fitechlabs.fin.intellioms.ticker.TickersManagerException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEvent;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEventHandler;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteFileAdaptor;
import jp.co.dir.dot.intellioms.recovery.DOTRecoveryService;
import jp.co.dir.dot.intellioms.rulesys.DOTBizDateProvider;
import jp.co.dir.dot.intellioms.ticker.DOTTicker;
import jp.co.dir.dot.intellioms.ticker.DOTTickerManager;



/**
 * (�����C�x���g�n���h��Impl)<BR>
 * <BR>
 * �����C�x���g�n���h���̎����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteEventHandlerImpl implements DOTQuoteEventHandler
{


    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /**
     * ��M�����������ƃL���b�V������Ă��錻�ݒl�̍���
     * �����Œ�`�����l�����̏ꍇ�͐؂�̂Ă�B
     */
    private static final double DELTA = 0.01;

    /**
     * �X���b�h�Z�[�t�ȓ��t�t�H�[�}�b�g��ێ�����B
     */
    private static ThreadLocal dateFormat = new ThreadLocal(){
        protected synchronized Object initialValue()
        {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    /**
     * �X���b�h�Z�[�t�ȓ��t�t�H�[�}�b�g�iHHmm�j��ێ�����B
     */
    private static ThreadLocal dateFormatHHMM = new ThreadLocal()
    {
        protected synchronized Object initialValue()
        {
            return new SimpleDateFormat("HH:mm");
        }
    };

    /** ���ݒl���X�V���ꂽ������񂾂���ۑ�����t�@�C���A�_�v�^*/
    private DOTQuoteFileAdaptor fileAdaptor;

    /** �S�Ă̎�������ۑ�����t�@�C���A�_�v�^ */
    private DOTQuoteFileAdaptor fileAdaptorAll;

    /** Ticker�}�l�[�W�� */
    private DOTTickerManager tickersManager;

    /** �������Ǘ��e�[�u�� */
    private DOTRlsQuoteCacheStore cacheStore;

    /** �������v�[�� */
    private DOTQuotePool quotePool;

    /** ������񃊃J�o���T�[�r�X */
    private DOTRecoveryService recoveryService;

    /** �r�W�l�X���t�v���o�C�_ */
    private DOTBizDateProvider bizDateProvider;

    /** �w���p�[�N���X */
    private DOTQuoteHelper helper;

    /**
     * �c�Ɠ��`�F�b�N�L���t���O
     *
     * �utrue�v�̏ꍇ�A�c�Ɠ��Ǝ������̎������t���قȂ�
     * �������͐؂�̂Ă�B
     */
    private boolean isBizDateCheckEnabled;

    /**
     * �L������FROM
     * �t�H�[�}�b�g�FHH:mm�i24���ԕ\�L�j
     */
    private String activeTimeFrom;

    /**
     * �L������TO
     * �t�H�[�}�b�g�FHH:mm�i24���ԕ\�L�j
     */
    private String activeTimeTo;

    /**
     * �R���X�g���N�^
     */
    public DOTQuoteEventHandlerImpl()
    {
        this.helper = DOTQuoteHelper.getInstance();
    }

    /**
     * (register�g���e�B�b�J�[�}�l�[�W��)<BR>
     * <BR>
     * �g���e�B�b�J�[�}�l�[�W����o�^����B<BR>
     *
     * @param l_tickersManager �g���e�B�b�J�[�}�l�[�W��
     */
    public void registerTickersManager(DOTTickerManager l_tickersManager)
    {
        this.tickersManager = l_tickersManager;
        log.info("TickersManager registered. [" + l_tickersManager.getClass().getName() + "]");
    }

    /**
     * (register�������t�@�C���A�_�v�^)<BR>
     * <BR>
     * ���ݒl���X�V���ꂽ������񂾂���ۑ�����t�@�C���A�_�v�^��o�^����B<BR>
     *
     * @param l_fileAdaptor ���ݒl���X�V���ꂽ������񂾂���ۑ�����t�@�C���A�_�v�^
     */
    public void registerFileAdaptor(DOTQuoteFileAdaptor l_fileAdaptor)
    {
        this.fileAdaptor = l_fileAdaptor;
        log.info("QuoteFileAdaptor registered. [" + l_fileAdaptor + "]");
    }

    /**
     * (register�������t�@�C���A�_�v�^All)<BR>
     * <BR>
     * �S�Ă̎�������ۑ�����t�@�C���A�_�v�^��ۑ�����B<BR>
     *
     * @param l_fileAdaptorAll �S�Ă̎�������ۑ�����t�@�C���A�_�v�^
     */
    public void registerFileAdaptorAll(DOTQuoteFileAdaptor l_fileAdaptorAll)
    {
        this.fileAdaptorAll = l_fileAdaptorAll;
        log.info("QuoteFileAdaptor registered. [" + l_fileAdaptorAll + "]");
    }

    /**
     * (register�g���������Ǘ��e�[�u��)<BR>
     * <BR>
     * �������Ǘ��e�[�u����o�^����B<BR>
     *
     * @param l_cacheStore �������Ǘ��e�[�u��
     */
    public void registerCacheStore(DOTRlsQuoteCacheStore l_cacheStore)
    {
        this.cacheStore = l_cacheStore;
        log.info("QuoteCacheStore registered. [" + l_cacheStore.getClass().getName() + "]");
    }

    /**
     * (register�������v�[��)<BR>
     * <BR>
     * �������v�[����o�^����B<BR>
     *
     * @param l_quotePool �������v�[��
     */
    public void registerQuotePool(DOTQuotePool l_quotePool)
    {
        this.quotePool = l_quotePool;
        log.info("QuotePool registered. [" + l_quotePool.getClass().getName() + "]");
    }

    /**
     * (register��Q�����T�[�r�X)<BR>
     * <BR>
     * ��Q�����T�[�r�X��o�^����B<BR>
     *
     * @param l_recoveryService ��Q�����T�[�r�X
     */
    public void registerRecoveryService(DOTRecoveryService l_recoveryService)
    {
        this.recoveryService = l_recoveryService;
        log.info("RecoveryService registered. [" + l_recoveryService + "]");
    }

    /**
     * (register�r�W�l�X���t�v���o�C�_)<BR>
     * <BR>
     * �r�W�l�X���t�v���o�C�_��o�^����B<BR>
     *
     * @param l_bizDateProvider �r�W�l�X���t�v���o�C�_
     *
     * @param l_bizDateProvider
     */
    public void registerBizDateProvider(DOTBizDateProvider l_bizDateProvider)
    {
        this.bizDateProvider = l_bizDateProvider;
        log.info("BizDateProvider registered. ["
            + l_bizDateProvider.getClass().getName() + "]");
    }

    /**
     * (set�c�Ɠ��`�F�b�N�L���t���O)
     *
     * �c�Ɠ��`�F�b�N�L���t���O��ݒ肷��B
     * true�F�L���^false�F����
     */
    public void setBizDateCheckEnabled(boolean l_isBizDateCheckEnabled)
    {
        this.isBizDateCheckEnabled = l_isBizDateCheckEnabled;
    }

    /**
     * (is�c�Ɠ��`�F�b�N�L��)
     *
     * �c�Ɠ��`�F�b�N�L���t���O�̐ݒ�l���擾����B
     *
     * @return true�F�L���^false�F����
     */
    public boolean isBizDateCheckEnabled()
    {
        return isBizDateCheckEnabled;
    }

    /**
     * �L������FROM��ݒ肷��B
     *
     * @param l_strActiveTimeFrom �L������FROM�i�t�H�[�}�b�g�F�uHH:mm�v�j
     */
    public void setActiveTimeFrom(String l_strActiveTimeFrom)
    {
        this.activeTimeFrom = l_strActiveTimeFrom;
    }

    /**
     * �L������FROM���擾����B
     *
     * @return �L������FROM�i�t�H�[�}�b�g�F�uHH:mm�v�j
     */
    public String getActiveTimeFrom()
    {
        return activeTimeFrom;
    }

    /**
     * �L������TO��ݒ肷��B
     *
     * @param l_strActiveTimeTo �L������TO�i�t�H�[�}�b�g�F�uHH:mm�v�j
     */
    public void setActiveTimeTo(String l_strActiveTimeTo)
    {
        this.activeTimeTo = l_strActiveTimeTo;
    }

    /**
     * �L������TO���擾����B
     *
     * @return �L������TO�i�t�H�[�}�b�g�F�uHH:mm�v�j
     */
    public String getActiveTimeTo()
    {
        return activeTimeTo;
    }

    /**
     * �������������C�x���g����������B<BR>
     * <BR>
     * �y�V�[�P���X�}�z<BR>
     * �H�H�H�H�H�H�H�H�H�H�H�H���Q�ƁB<BR>
     * <BR>
     *
     * @see DOTQuoteEventHandler#push(DOTQuoteEvent)
     */
    public void push(DOTQuoteEvent l_event)
    {

        if (fileAdaptor == null || fileAdaptorAll == null
            || tickersManager == null || cacheStore == null
            || quotePool == null || recoveryService == null
            || bizDateProvider == null)
        {
            throw new IllegalStateException(
                "fileAdaptor, fileAdaptorAll, tickersManager, cacheStore, quotePool, recoveryService or bizDateProvider are not registered.");
        }

        DOTQuoteData l_quoteData = l_event.getQuoteData();
        fileAdaptorAll.save(l_quoteData);

        if (log.isDebug())
        {
            log.debug("QuoteData saved. [fileAdaptor=" + fileAdaptorAll
                + ", quoteData=" + l_quoteData + "]");
        }

        Ticker l_ticker = getTicker(l_quoteData);

        if (isDiscardable(l_ticker, l_quoteData))
        {
            return;
        }

        fileAdaptor.save(l_quoteData);

        if (log.isDebug())
        {
            log.debug("QuoteData saved. [fileAdaptor=" + fileAdaptor
                + ", quoteData=" + l_quoteData + "]");
        }

        cacheStore.addQuoteData(l_ticker, l_quoteData);

        if (log.isDebug())
        {
            log.debug("QuoteData added to QuoteManager. [quoteData="
                + l_quoteData + "]");
        }

        quotePool.addQuoteData(l_ticker, l_quoteData);

        if (log.isDebug())
        {
            log.debug("QuoteData added to QuotePool. [quoteData="
                + l_quoteData + "]");
        }

    }

    /**
     * (get�e�B�b�J�[)<BR>
     * <BR>
     * �w�肵���������ɑΉ�����Ticker���擾����B<BR>
     *
     * @param l_quoteData �������
     * @return �w�肵���������ɑΉ�����Ticker�A�Ή�����Ticker�����݂��Ȃ��ꍇ��<code>null</code>
     */
    private Ticker getTicker(DOTQuoteData l_quoteData)
    {

        Ticker l_ticker = null;

        try
        {
            DOTTicker l_web3Ticker = helper.createTicker(l_quoteData);
            l_ticker = tickersManager.getTicker(l_web3Ticker);
        } catch (TickersManagerException l_tme)
        {
            log.warn("", l_tme);
        }

        return l_ticker;

    }

    /**
     * (is�؎̉\)<BR>
     * <BR>
     * �����f�[�^�\�[�X����ʒm���ꂽ�������
     * ���[���G���W����ŏ�������K�v�����邩���肷��B<BR>
     *
     * @param l_ticker Ticker
     * @param l_receivedQuoteData �������
     * @return �w�肵����������؂�̂ĉ\�ȏꍇ��<code>true</code>�A
     *         ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B
     */
    private boolean isDiscardable(Ticker l_ticker, DOTQuoteData l_receivedQuoteData)
    {

        if (l_ticker == null)
        {
            log.warn("QuoteData discarded because ticker is null."
                + " quoteData=[" + l_receivedQuoteData + "]");
            return true;
        }

        if (Double.isNaN(l_receivedQuoteData.getCurrentPrice()))
        {
            log.debug("QuoteData discarded because current price is NaN."
                + " quoteData=[" + l_receivedQuoteData + "]");
            return true;
        }

        DateFormat df = (DateFormat) dateFormat.get();
        String l_strBizDate = bizDateProvider.getBizDate();
        Date l_datQuoteDate = l_receivedQuoteData.getQuoteDate();
        String l_strQuoteDate = (l_datQuoteDate != null) ?
            df.format(l_datQuoteDate) : null;

        if (isBizDateCheckEnabled
            && l_strBizDate != null
            && !l_strBizDate.equals(l_strQuoteDate))
        {
            log.debug("QuoteData discarded because quote date is not current bizdate."
                + " bizDate=[" + l_strBizDate +" , quoteData=" + l_receivedQuoteData +"]");
            return true;
        }

        if (!isActive())
        {
            if (log.isDebug())
            {
                log.debug("QuoteData discarded because EventHandler is not active."
                    + " activeTime=[" + activeTimeFrom + ", " + activeTimeTo + "]");
            }
            return true;
        }

        DOTQuoteData l_cachedQuoteData = cacheStore.getQuoteData(l_ticker);
        if (l_cachedQuoteData == null)
        {
            log.debug("Cached quoteData not found, process will be continued.");
            return false;
        }

        double cachedV = l_cachedQuoteData.getVolume();
        double receivedV = l_receivedQuoteData.getVolume();

        if (!Double.isNaN(cachedV)
            && Math.abs(cachedV - receivedV) < DELTA)
        {
            if (log.isDebug())
            {
                log.debug("QuoteData discarded because current volume is not updated."
                    + " quoteData=[" + l_receivedQuoteData + "]"
                    + " Math.abs(cachedV - receivedV)=" + Math.abs(cachedV - receivedV));
            }
            return true;
        }

        if (log.isDebug())
        {
            log.debug("QuoteData updated, process will be continued. "
                + "quoteData=[" + l_receivedQuoteData + "]");
        }

        return false;

    }

    /**
     * �C�x���g�n���h���̗L�����ԃ`�F�b�N���s���B
     * �L������FROM�ATO���ݒ�ς݂ŁA�V�X�e�����Ԃ��L������FROM�ATO��
     * �͈͊O�̏ꍇ�́ufalse�v��Ԃ��B
     *
     * @return true�F�L������ / false�F�L�����ԊO
     */
    private boolean isActive()
    {

        boolean isActive = true;
        long l_lngCurrentTime = bizDateProvider.getCurrentTimeMillis();
        Date l_datCurrentTime = new Date(l_lngCurrentTime);
        String l_strCurrentTime =
            ((DateFormat) dateFormatHHMM.get()).format(l_datCurrentTime);

        if (activeTimeFrom != null
            && activeTimeFrom.compareTo(l_strCurrentTime) > 0)
        {
            isActive = false;
        }

        if (activeTimeTo != null
            && activeTimeTo.compareTo(l_strCurrentTime) < 0)
        {
            isActive = false;
        }

        if (log.isDebug())
        {
            log.debug("EventHandler#isActive()=" + isActive
                + " [currentTime=" + l_strCurrentTime
                + ",activeTimeFrom=" + activeTimeFrom
                + ",activeTimeTo=" + activeTimeTo + "]");
        }

        return isActive;

    }

}
