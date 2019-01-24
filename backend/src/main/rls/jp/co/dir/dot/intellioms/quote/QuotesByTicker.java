/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : QuotesByTicker�N���X(QuotesByTicker.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fitechlabs.fin.intellioms.ticker.Ticker;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;


/**
 * (�����|�������e�[�u��)
 * 
 * �������Ɏ��������Ǘ�����N���X�B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class QuotesByTicker
{
    
    /**
     * �����|�������}�b�v
     * <BR>
     * �L�[ : �e�B�b�J�[<BR>
     * �l : ���ԁ|�������e�[�u��<BR>
     */
    private Map quotesByTicker;
    
    /**
     * �R���X�g���N�^
     */
    public QuotesByTicker()
    {
        quotesByTicker = new HashMap();
    }
    
    /**
     * (add�������)<BR>
     * <BR>
     * ��������ǉ�����B<BR>
     * 
     * @param l_ticker �e�B�b�J�[
     * @param l_index �������C���f�b�N�X
     * @param l_quoteData �������
     */
    public void addQuoteData(Ticker l_ticker, QuoteIndex l_index, DOTQuoteData l_quoteData)
    {
        QuotesByTime l_quotesByTime = (QuotesByTime) quotesByTicker.get(l_ticker);
        if (l_quotesByTime == null)
        {
            l_quotesByTime = new QuotesByTime();
            quotesByTicker.put(l_ticker, l_quotesByTime);
        }
        l_quotesByTime.addQuoteData(l_index, l_quoteData);
    }
    
    /**
     * (get�ŐV�������)<BR>
     * <BR>
     * �w�肳�ꂽ�����̍ŐV�̎��������擾����B<BR>
     * �Ώۃf�[�^�����݂��Ȃ��ꍇ<code>null</code>��Ԃ��B<BR>
     * 
     * @param l_ticker �e�B�b�J�[
     */
    public DOTQuoteData getLastQuoteData(Ticker l_ticker)
    {
        DOTQuoteData l_quoteData = null;
        QuotesByTime l_quotesByTime = (QuotesByTime) quotesByTicker.get(l_ticker);
        if (l_quotesByTime != null)
        {
            l_quoteData = l_quotesByTime.getLastQuoteData();
        }
        return l_quoteData;
    }
    
    /**
     * (get������񃊃X�g)<BR>
     * <BR>
     * �w�肳�ꂽ�����̍X�V���Ԃ��������C���f�b�N�X�iFROM�j�ȏ�A
     * �������C���f�b�N�X�iTO�j�ȉ��̎������̃��X�g���擾����B<BR>
     * �Ώۃf�[�^�����݂��Ȃ��ꍇ<code>null</code>��Ԃ��B<BR>
     * 
     * @param l_ticker �e�B�b�J�[
     * @param l_indexFrom �������C���f�b�N�X�iFROM�j
     * @param l_indexTo �������C���f�b�N�X�iTO�j
     * @return <code>DOTQuoteData</code>��<code>List</code>
     */
    public List getQuoteData(Ticker l_ticker, QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        List l_quotes = null;
        QuotesByTime l_quotesByTime = (QuotesByTime) quotesByTicker.get(l_ticker);
        if (l_quotesByTime != null)
        {
            l_quotes = l_quotesByTime.getQuoteData(l_indexFrom, l_indexTo);
        }
        return l_quotes;
    }
    
    /**
     * (remove�������)<BR>
     * <BR>
     * �w�肳�ꂽ�����̍X�V���Ԃ��������C���f�b�N�X�iFROM�j�ȏ�A
     * �������C���f�b�N�X�iTO�j�ȉ��̎�����񂪑��݂���ꍇ�A
     * ���̎�����������|�������e�[�u������폜����B<BR>
     * 
     * @param l_ticker �e�B�b�J�[
     * @param l_indexFrom �������C���f�b�N�X�iFROM�j
     * @param l_indexTo �������C���f�b�N�X�iTO�j
     */
    public void removeQuoteData(Ticker l_ticker, QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        QuotesByTime l_quotesByTime = (QuotesByTime) quotesByTicker.get(l_ticker);
        if (l_quotesByTime != null)
        {
            l_quotesByTime.removeQuoteData(l_indexFrom, l_indexTo);
        }
    }

}
