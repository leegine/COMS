/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteEventHandler�N���X(DOTQuoteEventHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor;


/**
 * (�����C�x���g�n���h��)<BR>
 * <BR>
 * �����C�x���g����������C�x���g�n���h���[<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteEventHandler
{
    
    /**
     * (push)<BR>
     * <BR>
     * �������������C�x���g����������B<BR>
     * 
     * @param event �����C�x���g
     */
    public void push(DOTQuoteEvent event);

}
