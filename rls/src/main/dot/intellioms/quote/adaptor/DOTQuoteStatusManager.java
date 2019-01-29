/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteStatusManager�N���X(DOTQuoteStatusManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/14 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;

/**
 * (�ڑ���ԃ}�l�[�W��)<BR>
 * <BR>
 * �����T�[�o�Ƃ̐ڑ���Ԃ��Ǘ�����B<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteStatusManager
{

    /**
     * (get�ڑ����)<BR>
     * <BR>
     * ���݂̐ڑ���Ԃ��擾����B<BR>
     * 
     * @return ���݂̐ڑ����
     */
    public QuoteStatus getCurrentStatus();

    /**
     * (modify�ڑ����)<BR>
     * <BR>
     * �w�肵���X�e�[�^�X�ɕύX������B<BR>
     * 
     * @param l_newStatus �ڑ����
     */
    public void modifyStatus(QuoteStatus l_newStatus);
    
}