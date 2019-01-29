/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuotePool�N���X(DOTQuotePool.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/17 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.List;

import com.fitechlabs.fin.intellioms.ticker.Ticker;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;



/**
 * (�������v�[��)<BR>
 * <BR>
 * �����T�[�o�����M�������������ꎞ�I�Ƀv�[������N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuotePool
{
    
    /**
     * (add�������)<BR>
     * <BR>
     * �w�肵���e�B�b�J�[���L�[�Ƃ��āA
     * ���������������v�[���ɒǉ�����B<BR>
     * 
     * @param l_quoteData �������
     */
    public void addQuoteData(Ticker l_ticker, DOTQuoteData l_quoteData);
    
    /**
     * (get�����X�i�b�v�V���b�g���X�g)<BR>
     * <BR>
     * �������v�[�����ێ����Ă���S�Ă̎�����񂩂�
     * �쐬�����������X�i�b�v�V���b�g�̃��X�g��Ԃ��A
     * �������v�[������ɂ���B<BR>
     * ������񂪃v�[������Ă��Ȃ��ꍇ�́A���<code>List</code>��Ԃ��B<BR>
     * 
     * @return <code>DOTQuote</code>��<code>List</code>
     */
    public List getQuotes();

}
