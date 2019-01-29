/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteCacheStore�N���X(DOTQuoteCacheStore.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/08/23 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor;

import jp.co.dir.dot.intellioms.enums.IndexType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;
import jp.co.dir.dot.intellioms.enums.RealType;

/**
 * (�������Ǘ��e�[�u��)<BR>
 * <BR>
 * �����T�[�o����z�M���ꂽ���������Ǘ�����B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteCacheStore
{

    /**
     * (get�����������)<BR>
     * <BR>
     * �����̎��������擾����B<BR>
     *
     * @param realType ���A���敪
     * @param productCode �����R�[�h
     * @param marketCode �s��R�[�h
     * @return �����̎������
     */
    public DOTQuoteData getEquityQuote(RealType realType, String productCode,
                                       String marketCode);

    /**
     * (get�w���������)<BR>
     * <BR>
     * �����w���̎��������擾����B<BR>
     *
     * @param realType ���A���敪
     * @param indexType �w�����
     * @return �w���̎������
     */
    public DOTQuoteData getIndexQuote(RealType realType, IndexType indexType);

    /**
     * (get�����w���敨�������)<BR>
     * <BR>
     * �����w���敨�̎��������擾����B<BR>
     *
     * @param realType ���A���敪
     * @param underlyingProductCode �����Y�����R�[�h
     * @param monthOfDelivery ����
     * @param marketCode �s��R�[�h
     *
     * @return �����w���敨�̎������
     */
    public DOTQuoteData getIndexFutureQuote(RealType realType,
                                            String underlyingProductCode, String monthOfDelivery, String marketCode);

    /**
     * (get�����w���I�v�V�����������)<BR>
     * <BR>
     * �����w���I�v�V�����̎��������擾����B<BR>
     *
     * @param realType ���A���敪
     * @param underlyingProductCode �����Y�����R�[�h
     * @param monthOfDelivery ����
     * @param putAndCall �v�b�g���R�[��
     * @param strikePrice �s�g���i
     * @param marketCode �s��R�[�h
     * @return �����w���I�v�V�����̎������
     */
    public DOTQuoteData getIndexOptionQuote(RealType realType,
                                            String underlyingProductCode, String monthOfDelivery, PutAndCall putAndCall,
                                            double strikePrice, String marketCode);

    /**
     * (clear)<BR>
     * <BR>
     * �������Ǘ��e�[�u�����Ǘ�����S�Ă̎��������N���A����B<BR>
     *
     */
    public void clear();

}
