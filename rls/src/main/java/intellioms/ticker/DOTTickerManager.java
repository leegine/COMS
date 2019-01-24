/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TickerManager�N���X(DOTTickerManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.ticker;

import com.fitechlabs.fin.intellioms.ticker.Ticker;
import com.fitechlabs.fin.intellioms.ticker.TickersManager;
import com.fitechlabs.fin.intellioms.ticker.TickersManagerException;


/**
 * (�g���e�B�b�J�[�}�l�[�W��)<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTTickerManager extends TickersManager
{
    
    /**
     * (get�e�B�b�J�[)<BR>
     * <BR>
     * �w�肳�ꂽWEB3�p���ۃe�B�b�J�[�ɑΉ����郋�[���G���W���p�̃e�B�b�J�[���擾����B<BR>
     * <BR>
     * @param l_web3Ticker WEB3�p���ۃe�B�b�J�[ 
     * @return ���[���G���W���p�̃e�B�b�J�[
     * @throws TickersManagerException �w�肵��WEB3�p���ۃe�B�b�J�[�ɑΉ�����
     *  ���[���G���W���p�e�B�b�J�[�����݂��Ȃ��ꍇ�B
     */
    public Ticker getTicker(DOTTicker l_web3Ticker) throws TickersManagerException;
    
    /**
     * (load�e�B�b�J�[)<BR>
     * <BR>
     * �e�B�b�J�[�������[�h����B<BR>
     * <BR>
     * @throws TickersManagerException �e�B�b�J�[�������[�h����Ƃ��ɁA
     *  ���炩�̗�O�����������ꍇ�B
     */
    public void loadTickers() throws TickersManagerException;
    
}
