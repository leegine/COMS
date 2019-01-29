/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteConnectionErrorHandlerImpl�N���X(DOTQuoteConnectionErrorHandlerImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/14 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteConnectionErrorHandler;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;


/**
 * (�f�t�H���g�ڑ��G���[�n���h��Impl)<BR>
 * <BR>
 * �ڑ��G���[�n���h���̎����N���X�B<BR>
 * <BR>
 * �f�t�H���g�ڑ��G���[�n���h���ł́A���g�̐ڑ��G���[���̓��ꏈ���͎�������Ă��Ȃ��B
 * �������A�f�t�H���g�ڑ��G���[�n���h���ɂ́A�����̐ڑ��G���[�n���h����o�^����
 * ���Ƃ��\�ŁA�f�t�H���g�ڑ��G���[�́A�ڑ��G���[�������f�[�^�\�[�X����ʒm���ꂽ
 * �ꍇ�ɁA���g�ɓo�^����Ă��镡���̐ڑ��G���[�n���h���̃G���[���������s����B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteConnectionErrorHandlerImpl implements
    DOTQuoteConnectionErrorHandler
{

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** �o�^����Ă���ڑ��G���[�n���h���̃��X�g */
    private List handlers;

    /**
     * �R���X�g���N�^
     */
    public DOTQuoteConnectionErrorHandlerImpl()
    {
        this.handlers = new ArrayList();
    }

    /**
     * (add�ڑ��G���[�n���h��)<BR>
     * <BR>
     * �f�t�H���g�ڑ��G���[�n���h�����Ǘ�����ڑ��G���[�n���h���̈ꗗ��
     * �ڑ��G���[�n���h����ǉ�����B<BR>
     *
     * @param l_handler �ڑ��G���[�n���h��
     * @throws IllegalArgumentException
     *         �p�����[�^.l_handler��<code>null</code>�̏ꍇ
     */
    public void addHandler(DOTQuoteConnectionErrorHandler l_handler)
    {

        if (l_handler == null)
        {
            throw new IllegalArgumentException("l_handler must be not null.");
        }

        handlers.add(l_handler);

        log.info("QuoteConnectionErrorHandler added."
            + " [QuoteConnectionErrorHandler=" + l_handler + "]");

    }

    /**
     * �f�t�H���g�ڑ��G���[�n���h�����Ǘ�����ڑ��G���[�n���h����
     * �ڑ��G���[�n���h��#handle()���\�b�h���f�t�H���g�ڑ��G���[�n���h����
     * �o�^���ꂽ���Ɏ��s����B
     *
     * @see DOTQuoteConnectionErrorHandler#handle()
     */
    public void handle()
    {
        for (Iterator l_it = handlers.iterator(); l_it.hasNext();)
        {
            DOTQuoteConnectionErrorHandler l_handler =
                (DOTQuoteConnectionErrorHandler) l_it.next();
            l_handler.handle();
        }
    }

    /**
     * �f�t�H���g�ڑ��G���[�n���h�����Ǘ�����ڑ��G���[�n���h����
     * �ڑ��G���[�n���h��#onRegister(�����f�[�^�\�[�X)���\�b�h��
     * �f�t�H���g�ڑ��G���[�n���h���ɓo�^���ꂽ���Ɏ��s����B
     *
     * @see DOTQuoteConnectionErrorHandler#onRegister(DOTQuoteDataSource)
     */
    public void onRegister(DOTQuoteDataSource l_dataSource)
    {
        for (Iterator l_it = handlers.iterator(); l_it.hasNext();)
        {
            DOTQuoteConnectionErrorHandler l_handler =
                (DOTQuoteConnectionErrorHandler) l_it.next();
            l_handler.onRegister(l_dataSource);
        }
    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("DOTQuoteConnectionErrorHandlerImpl[");
        l_sb.append("handlers=").append(handlers);
        l_sb.append("]");
        return l_sb.toString();
    }
}
