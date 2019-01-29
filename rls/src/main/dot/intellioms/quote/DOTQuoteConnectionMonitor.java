/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteConnectionMonitor�N���X(DOTQuoteConnectionMonitor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/15 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;

/**
 * (�ڑ���ԃ��j�^�[)<BR>
 * <BR>
 * �����T�[�o�Ƃ̐ڑ���Ԃ��Ď�����B<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteConnectionMonitor
{

    /**
     * (�ڑ��ҋ@)<BR>
     * <BR>
     * �w�肵�������f�[�^�\�[�X�Ǝ����T�[�o�Ƃ̐ڑ����m������܂őҋ@����B
     * <BR>
     * �w�肵���^�C���A�E�g���Ԃ��o�߂��Ă������T�[�o�Ƃ̐ڑ����m�����Ȃ��ꍇ�A
     * ��莞�ԑҋ@��A���[���G���W�����~����B<BR>
     * 
     * @param l_dataSource �Ď��ΏۂƂȂ鎞���f�[�^�\�[�X
     * @return �����T�[�o�Ƃ̐ڑ����m�������ꍇ��<code>true</code>�A
     *         ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B
     */
    public boolean waitForConnectionToBeEstablished(DOTQuoteDataSource l_dataSource);
    
}