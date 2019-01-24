/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteDataSource�N���X(DOTQuoteDataSource.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor;

import java.sql.Timestamp;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;



/**
 * (�����f�[�^�\�[�X)<BR>
 * <BR>
 * �����T�[�o�ɐڑ�������������M����B<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteDataSource
{
    
    /**
     * (register�����C�x���g�n���h��)<BR>
     * <BR>
     * �����C�x���g�n���h����o�^����B<BR>
     * 
     * @param handler �����C�x���g�n���h��
     */
    public void registerHandler(DOTQuoteEventHandler handler);
    
    /**
     * (start)<BR>
     * <BR>
     * �����T�[�o�ւ̐ڑ����J�n����B<BR>
     * 
     */
    public void start();
    
    /**
     * (stop)<BR>
     * <BR>
     * �����T�[�o�ւ̐ڑ����~����B<BR>
     * 
     */
    public void stop();
    
    /**
     * (get�ڑ��X�e�[�^�X)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ��X�e�[�^�X���擾����B<BR>
     * 
     * @return �ڑ��X�e�[�^�X
     */
    public QuoteStatus getStatus();
    
    /**
     * (get�ڑ��m������)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ����m���������Ԃ�Ԃ��B<BR>
     * <BR>
     * �V�X�e�����N�����Ă���A�����T�[�o�Ƃ̐ڑ���1����m������
     * �ȓ��ꍇ��<code>null</code>��Ԃ��B<BR>
     * 2��ȏ�A�����T�[�o�Ƃ̐ڑ��|�ؒf���s�����ꍇ�A���̃��\�b�h
     * �͍Ō�ɐڑ����ꂽ���Ԃ�Ԃ��B<BR>
     * 
     * @return �ڑ��m������
     */
    public Timestamp getLastConnectedTime();
    
    /**
     * (get�ڑ��ؒf����)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ����ؒf���ꂽ���Ԃ�Ԃ��B<BR>
     * <BR>
     * �V�X�e�����N�����Ă���A�����T�[�o�Ƃ̐ڑ���1����m������
     * �ȓ��ꍇ��<code>null</code>��Ԃ��B<BR>
     * 2��ȏ�A�����T�[�o�Ƃ̐ڑ��|�ؒf���s�����ꍇ�A���̃��\�b�h
     * �͍Ō�ɐؒf���ꂽ���Ԃ�Ԃ��B<BR>
     * 
     * @return �ڑ��m������
     */
    public Timestamp getLastDisconnectedTime();
    
    /**
     * (is�ڑ���)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ����m�����Ă���ꍇ��<code>true</code>�A
     * ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B<BR>
     * 
     */
    public boolean isConnected();
    
    /**
     * (register�ڑ��G���[�n���h��)<BR>
     * <BR>
     * �ڑ��G���[�n���h�����擾����B<BR>
     * 
     * @param errorHandler �ڑ��G���[�n���h���|
     * @see DOTQuoteConnectionErrorHandler#onRegister(DOTQuoteDataSource)
     */
    public void registerErrorHandler(DOTQuoteConnectionErrorHandler errorHandler);

}
