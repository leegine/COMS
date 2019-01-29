/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RlsQuoteCacheStore�N���X(DOTRlsQuoteCacheStore.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.sql.Timestamp;
import java.util.List;


import com.fitechlabs.fin.intellioms.ticker.Ticker;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;



/**
 * (�g���������Ǘ��e�[�u��)<BR>
 * <BR>
 * �������Ǘ��e�[�u����WEB3���[���G���W���p�Ɋg�������C���^�[�t�F�[�X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTRlsQuoteCacheStore extends DOTQuoteCacheStore
{
    
    /**
     * (add�������(�������))<BR>
     * <BR>
     * �������Ǘ��e�[�u���Ɏ�������ǉ�����B<BR>
     * 
     * @param l_quoteData �������
     */
    public void addQuoteData(DOTQuoteData l_quoteData);
    
    /**
     * (add�������(�e�B�b�J�[, �������))<BR>
     * <BR>
     * �w�肵���e�B�b�J�[���L�[�Ƃ��āA
     * �������Ǘ��e�[�u���Ɏ�������ǉ�����B<BR>
     * 
     * @param l_ticker �e�B�b�J�[
     * @param l_quoteData �������
     */
    public void addQuoteData(Ticker l_ticker, DOTQuoteData l_quoteData);
    
    /**
     * (get�������)<BR>
     * <BR>
     * �w�肵�������̍ŐV�̎��������擾����B<BR>
     * �Ώۃf�[�^�����݂��Ȃ��ꍇ<code>null</code>��Ԃ��B<BR>
     * 
     * @param l_ticker �e�B�b�J�[
     * @return �������
     */
    public DOTQuoteData getQuoteData(Ticker l_ticker);
    
    /**
     * (get�������X�i�b�v�V���b�g)<BR>
     * <BR>
     * �w�肵�������̍X�V���Ԃ��X�V���ԁiFROM�j�ȏ�A
     * �X�V���ԁiTO�j�ȉ��̎������X�i�b�v�V���b�g���擾����B<BR>
     * �Ώۃf�[�^�����݂��Ȃ��ꍇ<code>null</code>��Ԃ��B<BR>
     * 
     * @param l_ticker �e�B�b�J�[
     * @param l_from �X�V���ԁiFROM�j
     * @param l_to �X�V���ԁiTO�j
     * @return �������
     */
    public DOTQuote getQuote(Ticker l_ticker, Timestamp l_from, Timestamp l_to);
    
    /**
     * (get�������X�i�b�v�V���b�g���X�g)<BR>
     * <BR>
     * �X�V���Ԃ��X�V���ԁiFROM�j�ȏ�A
     * �X�V���ԁiTO�j�ȉ��̎������X�i�b�v�V���b�g�̃��X�g���擾����B<BR>
     * �Ώۃf�[�^�����݂��Ȃ��ꍇ<code>null</code>��Ԃ��B<BR>
     * 
     * @param l_from �X�V���ԁiFROM�j
     * @param l_to �X�V���ԁiTO�j
     * @return <code>DOTQuote</code>��<code>List</code>
     */
    public List getQuotes(Timestamp l_from, Timestamp l_to);
    
    /**
     * (get������񃊃X�g)<BR>
     * <BR>
     * �X�V���Ԃ��X�V���ԁiFROM�j�ȏ�A
     * �X�V���ԁiTO�j�ȉ��̎������̃��X�g���擾����B<BR>
     * �Ώۃf�[�^�����݂��Ȃ��ꍇ<code>null</code>��Ԃ��B<BR>
     * 
     * @param l_from �X�V���ԁiFROM�j
     * @param l_to �X�V���ԁiTO�j
     * @return <code>DOTQuoteData</code>��<code>List</code>
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to);
    
    /**
     * (remove�������)<BR>
     * <BR>
     * �X�V���Ԃ��X�V���ԁiFROM�j�ȏ�A
     * �X�V���ԁiTO�j�ȉ��̎�����񂪑��݂���ꍇ�A
     * ���̎��������������Ǘ��e�[�u������폜����B<BR>
     * 
     * @param l_from �폜�Ώێ������̍X�V���ԁiFROM�j
     * @param l_to �폜�Ώێ������̍X�V���ԁiTO�j
     */
    public void remove(Timestamp l_from, Timestamp l_to);
    
}
