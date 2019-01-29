/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3Quote�N���X(DOTQuote.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.List;


import com.fitechlabs.fin.intellioms.quote.Quote;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;


/**
 * (�g���������X�i�b�v�V���b�g)<BR>
 * <BR>
 * �������X�i�b�v�V���b�g��WEB3���[���G���W���p�Ɋg�������C���^�[�t�F�[�X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuote extends Quote
{

    /**
     * (add�������)<BR>
     * <BR>
     * �������X�i�b�v�V���b�g�Ɏ�������ǉ�����B<BR>
     * 
     * @param l_quoteData
     */
    public void addQuoteData(DOTQuoteData l_quoteData);

    /**
     * (get�������y�ŐV�z)<BR>
     * <BR>
     * ���̎������X�i�b�v�V���b�g���ێ����鎞����񂩂�
     * �ŐV�̎��������擾����B<BR>
     * 
     * @return �ŐV�̎������
     */
    public DOTQuoteData getLastPriceQuoteData();

    /**
     * (get�������y�����l�z)<BR>
     * <BR>
     * ���̎������X�i�b�v�V���b�g���ێ����鎞����񂩂�
     * �ŏ��ɐݒ肳�ꂽ���������擾����B<BR>
     * 
     * @return �������
     */
    public DOTQuoteData getOpenPriceQuoteData();

    /**
     * (get�������y���l�z)<BR>
     * <BR>
     * ���̎������X�i�b�v�V���b�g���ێ����鎞����񂩂�
     * ���ݒl�������Ƃ��������̎��������擾����B<BR>
     * 
     * @return �������
     */
    public DOTQuoteData getHighPriceQuoteData();

    /**
     * (get�������y���l�z)<BR>
     * <BR>
     * ���̎������X�i�b�v�V���b�g���ێ����鎞����񂩂�
     * ���ݒl�������Ƃ��������̎��������擾����B<BR>
     * 
     * @return �������
     */
    public DOTQuoteData getLowPriceQuoteData();
    
    /**
     * (get������񃊃X�g)<BR>
     * <BR>
     * ���̎������X�i�b�v�V���b�g���ێ�����
     * �S�Ă̎������̃��X�g���擾����B<BR>
     * 
     * @return �������
     */
    public List getAllQuoteData();

}
