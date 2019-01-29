/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuotePoolImpl�N���X(DOTQuotePoolImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/17 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.fin.intellioms.ticker.Ticker;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;



/**
 * (�������v�[��Impl)<BR>
 * <BR>
 * �������v�[���̎����N���X<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuotePoolImpl implements DOTQuotePool
{

    /**
     * �������}�b�v
     * �L�[�F�e�B�b�J�[
     * �l�F�������X�i�b�v�V���b�gImpl
     */
    private Map quotePool;

    public DOTQuotePoolImpl()
    {
        this.quotePool = new HashMap();
    }

    /**
     * @see DOTQuotePool#addQuoteData(DOTQuoteData)
     */
    public synchronized void addQuoteData(Ticker l_ticker, DOTQuoteData l_quoteData)
    {

        DOTQuote l_quote = (DOTQuote) quotePool.get(l_ticker);

        if (l_quote != null)
        {

            l_quote.addQuoteData(l_quoteData);

        } else
        {

            l_quote = new DOTQuoteImpl(l_ticker, l_quoteData);
            quotePool.put(l_ticker, l_quote);

        }
    }

    /**
     * @see DOTQuotePool#getQuotes()
     */
    public synchronized List getQuotes()
    {

        List l_quotes = Collections.EMPTY_LIST;

        if (!quotePool.isEmpty())
        {

            l_quotes = new ArrayList();

            for (Iterator it = quotePool.values().iterator(); it.hasNext();)
            {
                l_quotes.add(it.next());
            }

            quotePool.clear();

        }

        return l_quotes;

    }

}
