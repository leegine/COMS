/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteConnectionErrorHandler�N���X(DOTQuoteConnectionErrorHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/10 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor;


/**
 * (�ڑ��G���[�n���h��)<BR>
 * <BR>
 * �����T�[�o�Ƃ̒ʐM���ɔ��������G���[����������B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 * @see DOTQuoteDataSource
 */
public interface DOTQuoteConnectionErrorHandler
{

    /**
     * (handle)<BR>
     * <BR>
     * �ʐM���ɔ��������G���[����������B<BR>
     *
     * �����f�[�^�\�[�X�́A�ʐM�G���[�����������ꍇ�A�o�^����Ă���G���[�n���h���|��
     * ���̃��\�b�h���Ăяo���B<BR>
     *
     */
    public void handle();

    /**
     * (onRegister)<BR>
     * <BR>
     * �����f�[�^�\�[�X�ɓo�^���ꂽ�Ƃ��Ɏ��s����鏈���B<BR>
     *
     * @param l_dataSource ���̃G���[�n���h���|��o�^���������f�[�^�\�[�X�̃C���X�^���X
     * @see DOTQuoteDataSource#registerErrorHandler(DOTQuoteConnectionErrorHandler)
     */
    public void onRegister(DOTQuoteDataSource l_dataSource);

}
