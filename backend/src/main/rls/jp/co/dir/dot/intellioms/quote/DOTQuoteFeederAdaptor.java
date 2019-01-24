/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteFeederAdaptor�N���X(DOTQuoteFeederAdaptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/21 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;


import com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor;
import com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptorException;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;


/**
 * (�g�������A�_�v�^)<BR>
 * <BR>
 * �����A�_�v�^��WEB3���[���G���W���p�g���C���^�[�t�F�[�X<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteFeederAdaptor extends QuoteFeederAdaptor
{
    
    /**
     * (register�����f�[�^�\�[�X)<BR>
     * <BR>
     * WEB3QuoteDataSource��o�^����B<BR>
     * 
     * @param l_dataSource DOTQuoteDataSource
     */
    public void registerQuoteDataSource(DOTQuoteDataSource l_dataSource);
    
    /**
     * (connect)<BR>
     * <BR>
     * �����T�[�o�ւ̐ڑ����J�n����B<BR>
     * 
     * @return �����T�[�o�ւ̐ڑ��ɐ��������ꍇ��<code>true</code>�A
     *         ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B
     */
    public boolean connect() throws QuoteFeederAdaptorException;
    
    /**
     * (get�ڑ����)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ���Ԃ��擾����B<BR>
     * 
     * @return �����T�[�o�Ƃ̐ڑ����
     */
    public QuoteStatus getStatus();
    
}
