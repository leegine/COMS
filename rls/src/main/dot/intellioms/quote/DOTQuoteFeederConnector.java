/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteFeederConnector�N���X(DOTQuoteFeederConnector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/10 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;


import com.fitechlabs.fin.intellioms.util.Startable;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteStatusManager;


/**
 * (�����T�[�o�ڑ��T�[�r�X)<BR>
 * <BR>
 * ���[���G���W���̋N�����Ɏ����T�[�o�Ƃ̐ڑ����J�n�E��~���邽�߂�
 * Startable�C���^�[�t�F�[�X���g�������C���^�[�t�F�[�X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteFeederConnector extends Startable
{
    
    /**
     * (register�����A�_�v�^)<BR>
     * <BR>
     * �����A�_�v�^��o�^����B<BR>
     * 
     * @param l_feederAdaptor �����A�_�v�^
     */
    public void registerQuoteFeederAdaptor(DOTQuoteFeederAdaptor l_feederAdaptor);
    
    /**
     * (register�ڑ���ԃ}�l�[�W��)<BR>
     * <BR>
     * �ڑ���ԃ}�l�[�W����o�^����B<BR>
     * 
     * @param l_statusManager �ڑ���ԃ}�l�[�W��
     */
    public void registerQuoteStatusManager(DOTQuoteStatusManager l_statusManager);

}
