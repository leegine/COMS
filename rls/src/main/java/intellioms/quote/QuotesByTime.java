/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : QuotesByTime�N���X(QuotesByTime.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;



/**
 * (���ԁ|�������e�[�u��)<BR>
 * <BR>
 * �������C���f�b�N�X���L�[�Ƃ��āA���Ԗ��̎��������Ǘ�����B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class QuotesByTime
{
    
    /** 
     * ���ԁ|�������}�b�v<BR>
     * <BR>
     * �L�[ �F �������C���f�b�N�X<BR>
     * �l : ��������ێ�����List<BR>
     */
    private TreeMap quotesByTime;
    
    /**
     * �R���X�g���N�^
     */
    public QuotesByTime()
    {
        quotesByTime = new TreeMap();
    }
    
    /**
     * (add�������)<BR>
     * <BR>
     * ��������ǉ�����B<BR>
     * 
     * @param l_index �������C���f�b�N�X
     * @param l_quoteData �������
     */
    public void addQuoteData(QuoteIndex l_index, DOTQuoteData l_quoteData)
    {
        List l_quotes = (List) quotesByTime.get(l_index);
        if (l_quotes == null)
        {
            l_quotes = new ArrayList();
            quotesByTime.put(l_index, l_quotes);
        }
        l_quotes.add(l_quoteData);
    }
    
    /**
     * (get�ŐV�������)<BR>
     * <BR>
     * �ŐV�̎��������擾����B<BR>
     * ���ԁ|�������e�[�u������̏ꍇ<code>null</code>��Ԃ��B<BR>
     * 
     * @return �ŐV�̎������
     */
    public DOTQuoteData getLastQuoteData()
    {
        
        if (quotesByTime.isEmpty())
        {
            return null;
        }
        
        DOTQuoteData l_quoteData = null;
        Object l_lastKey = quotesByTime.lastKey();
        List l_quotes = (List) quotesByTime.get(l_lastKey);
        
        if (l_quotes != null && !l_quotes.isEmpty())
        {
            int l_intLastIndex = l_quotes.size() - 1;
            l_quoteData = (DOTQuoteData) l_quotes.get(l_intLastIndex);
        }
        
        return l_quoteData;
        
    }
    
    /**
     * (get������񃊃X�g)<BR>
     * <BR>
     * �X�V���Ԃ��������C���f�b�N�X�iFROM�j�ȏ�A
     * �������C���f�b�N�X�iTO�j�ȉ��̎������̃��X�g���擾����B<BR>
     * �Ώۃf�[�^�����݂��Ȃ��ꍇ<code>null</code>��Ԃ��B<BR>
     * 
     * @param l_indexFrom �������C���f�b�N�X�iFROM�j
     * @param l_indexTo �������C���f�b�N�X�iTO�j
     * @return <code>DOTQuoteData</code>��<code>List</code>
     */
    public List getQuoteData(QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        List l_quotes = null;
        SortedMap l_subQuotesByTime = getSubMap(l_indexFrom, l_indexTo);
        
        if (l_subQuotesByTime != null && !l_subQuotesByTime.isEmpty())
        {
            l_quotes = new ArrayList();
            for (Iterator it = l_subQuotesByTime.values().iterator(); it.hasNext();)
            {
                l_quotes.addAll((List) it.next());
            }
        }
        
        return l_quotes;
        
    }
    
    /**
     * (remove������񃊃X�g)<BR>
     * <BR>
     * �X�V���Ԃ��������C���f�b�N�X�iFROM�j�ȏ�A
     * �������C���f�b�N�X�iTO�j�ȉ��̎������̎�����񂪑��݂���ꍇ�A
     * ���̎����������ԁ|�������e�[�u������폜����B<BR>
     * 
     * @param l_indexFrom �������C���f�b�N�X�iFROM�j
     * @param l_indexTo �������C���f�b�N�X�iTO�j
     */
    public void removeQuoteData(QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        
        SortedMap l_subQuotesByTime = getSubMap(l_indexFrom, l_indexTo);
        if (l_subQuotesByTime != null && !l_subQuotesByTime.isEmpty())
        {
            List l_keyList = new ArrayList(l_subQuotesByTime.keySet());
            for (Iterator l_it = l_keyList.iterator(); l_it.hasNext();)
            {
                quotesByTime.remove(l_it.next());
            }
        }
    }
    
    /**
     * (get�T�u���ԁ|�������}�b�v)<BR>
     * <BR>
     * ���ԁ|�������}�b�v����A�X�V���Ԃ��������C���f�b�N�X�iFROM�j�ȏ�A
     * �������C���f�b�N�X�iTO�j�ȉ��͈̔͂ł��镔���r���[���擾����B<BR>
     * 
     * @param l_indexFrom �������C���f�b�N�X�iFROM�j
     * @param l_indexTo �������C���f�b�N�X�iTO�j
     */
    private SortedMap getSubMap(QuoteIndex l_indexFrom, QuoteIndex l_indexTo)
    {
        
        QuoteIndex l_indexToKey = null;
        SortedMap l_quotes = null;
        
        if (l_indexTo != null)
        {
            l_indexToKey = new QuoteIndex(l_indexTo.getUpdatedTime() + 1, l_indexTo.getSequenceNo());
        }
        
        if (l_indexFrom != null)
        {
            if (l_indexTo != null)
            {
                l_quotes = quotesByTime.subMap(l_indexFrom, l_indexToKey);
            } else
            {
                l_quotes = quotesByTime.tailMap(l_indexFrom);
            }
        } else
        {
            if (l_indexTo != null)
            {
                l_quotes = quotesByTime.headMap(l_indexToKey);
            } else
            {
                l_quotes = quotesByTime;
            }
        }
        return l_quotes;
    }
    
}
